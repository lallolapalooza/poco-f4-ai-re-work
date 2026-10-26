"""YCNN inference contract of the Kwai vision stack (ARCHITECTURE_ONLY).

What is *recovered* (confirmed from libykit.so / libykit_core_plugin.so /
libfacedetectcontext.so / classes14.dex):

  entry            com.kwai.kscnnrenderlib.KSJNILib.{createModel,renderModel,
                   updateModelOut,releaseModel}     (libykit.so JNI)
  model handle     created from a YCNN file path + YCNNModelInfo.YCNNModelConfig
                   (JNI: YCNNGetConfig2Model(String path, YCNNModelConfig cfg))
  frame input      renderModel(Object, byte[] frame)   -> raw camera bytes.
                   libykit only accepts  grey / NV21 / NV12
                   ("only support nv21/nv12", "only support grey/nv21/nv12 for
                   grey crop") and converts with
                   ycnn::YCNNCropDataMatrix(srcY, srcUV, dst, w, h, stride,
                        KSY_COLOR_TYPE, KSY_COLOR_RANGE_TYPE, mean, w, h, ...
                        KSCNNInputColorType, ksrect crop, float scale, bool)
  outputs          typed accessors on ycnn::YCNNModel:
                     getLandmarks(KSFaceDetectOut*)   -> faces[]: rect + points + score
                     getMatting(KSMattingOut*)        -> alpha mask
                     getHeadSeg / getHumanPose / getInstanceSeg /
                     getScenceClass(KSSceneClassOut*)  -> 1-of-N scene label
                     getModelOut(YCNNModelOut*)       -> generic blobs
  model files      KSModel<Name>NN.model, magic_ycnn_model_* ; YCNN v3/v4
                   binary or JSON (net_w / net_h / layer list), run on
                   CPU NEON-fp16, OpenCL GPU, or MTK APU via the
                   libycnn_tflite_plugin.so shim.

What is *not* recoverable statically: the weights and the per-model graph.
None of the .model files are inside the APK (see report §models), so the
detector/landmark modules below are ARCHITECTURE_ONLY: they implement the
recovered I/O contract with deterministic random weights.  They are not a
copy of Kuaishou's network.
"""

from __future__ import annotations

import jax
import jax.numpy as jnp

# Contract constants recovered from the code paths above.  The network
# resolution itself lives in the .model header (net_w / net_h keys parsed by
# ycnn::YCNNGetConfig2Model) and is therefore model-specific -> we expose it
# as a parameter with the value used by the camera detector wrapper
# (kuaishou::face::YcnnFaceDetector) left configurable.

SUPPORTED_INPUT_FORMATS = ("grey", "nv21", "nv12")
DEFAULT_NET_SIZE = (128, 128)  # (h, w) - NOT recovered, see report "unknowns"

# ---- constants recovered from com.kwai.kscnnrenderlib.YCNNModelInfo --------
# (decompiled: sources3/classes/YCNNModelInfo$KSLandmarksParam.java)
MODEL_TYPES = {
    "HumanMatting": 0,
    "HandGesture": 1,
    "Hair": 2,
    "Finger": 3,
    "Humanpose": 4,
    "Landmarks": 5,
    "Handpose": 6,
    "SceneClass": 7,
    "Salient": 8,
    "Sky": 9,
    "FaceAttribute": 10,
    "DepthCamera": 11,
    "GeneralHandpose": 12,
    "Landmarks3D": 13,
    "HumanKeyPose": 14,
    "Benchmark": 15,
    "Portrait": 16,
    "Plane": 17,
    "HandSeg": 18,
    "StyleTransfer": 19,
    "FaceSeg": 20,
    "Detector": 21,
    "HDRNet": 22,
    "InstanceSeg": 23,
    "TaskDetect": 24,
    "TaskPoint": 25,
    "TaskImage": 26,
    "HeadSeg": 28,
    "NailSeg": 29,
    "MakeupSeg": 30,
    "HumanParsing": 31,
    "FaceSimilarity": 32,
    "FaceCluster": 33,
    "Inpainting": 53,
    "GeneralRecog": 54,
}

# YCNNModelIn.colorType default == 3 in the decompiled class
DEFAULT_COLOR_TYPE = 3

# KSLandmarksParam defaults (exact values from the decompiled class)
LANDMARK_PARAMS = dict(
    maxFaceNum=4,
    minFaceSize=50,
    detectIntervals=5,
    detectMode=4,  # KSLandmarksType_SuperFast
    detectSync=0,
    useImageTrack=2,  # KSLANDMARKS_TRACK_128
    useSmoothMode=0,
    stickFaceCropRect=True,
    stickTrackRect=True,
)


def nv21_to_rgb(nv21: jnp.ndarray, width: int, height: int):
    """YUV420sp (NV21) -> RGB float32 in [0,1], BT.601 limited (libyuv order).

    Mirrors ycnn::YCNNCropDataMatrix(color_type=NV21, color_range=limited)
    followed by the GLSL `yuv2rgb()` rounding used downstream.
    """
    y_plane = nv21[: height * width].reshape(height, width)
    uv = nv21[height * width :].reshape(height // 2, width // 2, 2)
    # NV21 interleaves V first, then U (libyuv NV21ToARGB)
    v = jnp.repeat(jnp.repeat(uv[..., 0], 2, axis=0), 2, axis=1)
    u = jnp.repeat(jnp.repeat(uv[..., 1], 2, axis=0), 2, axis=1)
    y = y_plane.astype(jnp.float32) / 255.0
    u = u.astype(jnp.float32) / 255.0 - 0.5
    v = v.astype(jnp.float32) / 255.0 - 0.5
    y = y - 16.0 / 255.0
    r = 1.164383 * y + 1.596027 * v
    g = 1.164383 * y - 0.391762 * u - 0.812968 * v
    b = 1.164383 * y + 2.017232 * u
    rgb = jnp.stack([r, g, b], axis=-1)
    return jnp.clip(jnp.ceil(rgb * 255.0 + 0.5) / 255.0, 0.0, 1.0)


def crop_resize(img, crop, out_hw, scale=1.0):
    """`YCNNCropDataMatrix` semantics: crop a rect, scale it, resize to net."""
    import jax.image as jimg

    x0, y0, x1, y1 = [int(round(v)) for v in crop]
    x0 = max(0, min(x0, img.shape[1] - 1))
    y0 = max(0, min(y0, img.shape[0] - 1))
    x1 = max(x0 + 1, min(x1, img.shape[1]))
    y1 = max(y0 + 1, min(y1, img.shape[0]))
    patch = img[y0:y1, x0:x1]
    return jimg.resize(patch, out_hw + (img.shape[-1],), method="bilinear")


def init_detector(key, net_hw=DEFAULT_NET_SIZE, num_landmarks=106, width=32):
    """Deterministic random init for the architecture-only detector head."""
    h, w = net_hw
    k = jax.random.split(key, 4)
    return {
        "conv1": jax.random.normal(k[0], (3, 3, 3, width)) * 0.05,
        "conv2": jax.random.normal(k[1], (3, 3, width, width * 2)) * 0.03,
        "heat": jax.random.normal(k[2], (1, 1, width * 2, 1)) * 0.01,
        "lmk": jax.random.normal(k[3], (1, 1, width * 2, num_landmarks * 2)) * 0.01,
    }


def _conv(x, w, stride=2):
    x = jax.lax.conv_general_dilated(
        x,
        w,
        window_strides=(stride, stride),
        padding="SAME",
        dimension_numbers=("NHWC", "HWIO", "NHWC"),
    )
    return jax.nn.relu(x)


def detector_forward(params, x):
    """x: (1, H, W, 3) float32 -> (box_score (1,h,w,1), landmarks (1,h,w,2K))."""
    f = _conv(x, params["conv1"])
    f = _conv(f, params["conv2"])
    score = jax.nn.sigmoid(_conv(f, params["heat"], stride=1))
    lmk = _conv(f, params["lmk"], stride=1)
    return score, lmk


def landmarks_from_heat(score, lmk, num_landmarks=106, thresh=0.5):
    """Decode: argmax cell -> normalised (x, y) per landmark."""
    h, w = score.shape[1], score.shape[2]
    flat = score.reshape(-1)

    def one(i):
        idx = jnp.argmax(flat)
        y = idx // w
        x = idx % w
        vals = lmk.reshape(h, w, num_landmarks, 2)[y, x]
        return jnp.array([x / w, y / h]), vals, idx

    pts, offsets, _ = one(0)
    return pts, offsets


__all__ = [
    "SUPPORTED_INPUT_FORMATS",
    "DEFAULT_NET_SIZE",
    "nv21_to_rgb",
    "crop_resize",
    "init_detector",
    "detector_forward",
    "landmarks_from_heat",
]
