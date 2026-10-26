"""End-to-end Looksery-style lens pipeline on synthetic frames.

The pipeline mirrors what libcamplat does for every camera frame
(``CoreManager::processFrame`` / ``processFrameToTexture``):

    camera frame (NV21/YUV -> gray)
      -> Haar cascade face detection       (/HaarCascades/haarcascade_frontalface_alt2.xml)
      -> ORB natural-feature tracking      (/NaturalFeatureTracker/SystemData/tree-ORB-*)
      -> ERT landmark regression           (LiteTrackingData_ert.lns; weights not decoded)
      -> AR deformation (liquify shader + landmark triangulation)
      -> GLES render                       (Scenarium shaders)

Stages 1, 2 and 4 are reimplemented here in JAX with the shipped assets.
Stage 3 (ERT regressors) uses a documented placeholder shape: the shipped
``*.bin`` regressors are a proprietary packed format (see report) and are not
decoded, so this module reports the landmark topology only and does not
pretend to run the real ERT weights.
"""

from __future__ import annotations

import os
import numpy as np

import looksery_assets as la
import haar
import orb
import deform

ASSETS = os.path.join(os.path.dirname(os.path.abspath(__file__)), "assets")


# --------------------------------------------------------------------------
# Synthetic inputs
# --------------------------------------------------------------------------
def load_haar_patch() -> np.ndarray:
    """20x20 template that satisfies every cascade stage (synthetic)."""
    path = os.path.join(ASSETS, "patch20.pgm")
    data = open(path, "rb").read()
    _magic, dims, _maxval, body = data.split(b"\n", 3)
    w, h = (int(v) for v in dims.split())
    return np.frombuffer(body, np.uint8)[:w * h].reshape(h, w)


def make_detection_frame(canvas: int = 96, origin: tuple[int, int] = (44, 34),
                         background: int = 40) -> tuple[np.ndarray, tuple[int, int]]:
    """Place the 20x20 cascade template at pixel (x, y)."""
    frame = np.full((canvas, canvas), background, np.uint8)
    patch = load_haar_patch()
    x, y = origin
    frame[y:y + patch.shape[0], x:x + patch.shape[1]] = patch
    return frame, origin


def make_texture_frame(size: int = 200, seed: int = 3) -> np.ndarray:
    rng = np.random.default_rng(seed)
    base = rng.normal(0, 1, (size, size)).astype(np.float32)
    k = np.array([1, 2, 3, 2, 1], np.float32)
    k /= k.sum()
    base = np.apply_along_axis(lambda r: np.convolve(r, k, "same"), 1, base)
    base = np.apply_along_axis(lambda r: np.convolve(r, k, "same"), 0, base)
    return np.clip(base * 60 + 128, 0, 255).astype(np.uint8)


# --------------------------------------------------------------------------
# Stages
# --------------------------------------------------------------------------
def stage_face_detection(frame: np.ndarray, min_size: int = 20,
                         max_size: int = 32, stride: int = 1):
    """Haar cascade scan; returns grouped boxes (dicts) and raw window count."""
    cascade = haar.HaarCascade()
    wins, scores, counts = haar.detect(cascade, frame, 1.1, min_size,
                                       max_size=max_size, stride=stride)
    boxes = haar.group_rects(wins, scores, min_neighbors=1)
    return boxes, dict(windows=len(wins), stage_survivors=counts,
                       max_window=wins.shape[0])


def stage_tracking(frame_a: np.ndarray, frame_b: np.ndarray,
                   thr: float = 15.0) -> dict:
    """ORB: FAST-9 + intensity-centroid orientation + steered BRIEF-256."""
    kp_a, _ = orb.detect_fast(frame_a, thr=thr)
    kp_b, _ = orb.detect_fast(frame_b, thr=thr)
    ang_a = orb.orientations(frame_a.astype(np.float32), kp_a)
    ang_b = orb.orientations(frame_b.astype(np.float32), kp_b)
    pattern = orb.brief_pattern(seed=1)
    desc_a = orb.describe(frame_a, kp_a, ang_a, pattern)
    desc_b = orb.describe(frame_b, kp_b, ang_b, pattern)
    matches, dist = orb.match(desc_a, desc_b)
    translation, inliers = orb.estimate_translation(kp_a, kp_b, matches)
    return dict(keypoints_a=kp_a, keypoints_b=kp_b, descriptors_a=desc_a,
                descriptors_b=desc_b, matches=matches, distances=dist,
                translation=translation, inliers=inliers)


def stage_landmarks(width: int, height: int) -> np.ndarray:
    """ERT output placeholder with the shipped 68/98-point topology."""
    return deform.synthetic_landmarks(width, height, 76)


def stage_deform(frame: np.ndarray, points: np.ndarray,
                 target: np.ndarray) -> np.ndarray:
    """Liquify displacement (facestretchLiquify.glsl) on a landmark mesh."""
    img = frame.astype(np.float32)
    warped = deform.warp_mesh(img, points, target,
                              la.parse_triangulation())
    # one liquify control at the mesh centre, radius 0.35, coefficient 1.0
    c = points.mean(0)
    h, w = warped.shape
    norm = np.array([c[0] / (w - 1) * 2 - 1, c[1] / (h - 1) * 2 - 1])
    return deform.liquify(warped, norm[None], np.array([1.0]), np.array([0.35]))


def run(verbose: bool = True) -> dict:
    """Run the reconstructed pipeline on synthetic frames and report shapes."""
    frame, origin = make_detection_frame()
    boxes, info = stage_face_detection(frame)
    tex = make_texture_frame()
    shift = np.array([7, -5], np.int32)
    tex_b = np.roll(np.roll(tex, int(shift[1]), 0), int(shift[0]), 1)
    track = stage_tracking(tex, tex_b)
    pts = stage_landmarks(tex.shape[1], tex.shape[0])
    warped = stage_deform(tex, pts, pts + np.array([4.0, -3.0]))
    out = dict(frame=frame.shape, patch_origin=origin,
               detections=boxes, detector_info=info,
               tracking={k: (v.shape if hasattr(v, "shape") else v)
                         for k, v in track.items()},
               translation=track["translation"], inliers=track["inliers"],
               warp=warped.shape)
    if verbose:
        print("frame          ", frame.shape)
        print("detections     ", [(round(b['x']), round(b['y']), round(b['w']))
                                   for b in boxes], "expected", origin)
        print("raw windows    ", info["windows"], "survivors/stage",
              info["stage_survivors"])
        print("ORB keypoints  ", track["keypoints_a"].shape,
              track["keypoints_b"].shape)
        print("ORB descriptors", track["descriptors_a"].shape,
              track["descriptors_b"].shape)
        print("ORB matches    ", track["matches"].shape,
              "translation", track["translation"], "inliers", track["inliers"])
        print("landmarks      ", pts.shape, "warped", warped.shape)
    return out


if __name__ == "__main__":
    run()
