# JAX reimplementation — MIUI face unlock (SenseTime FaceAuth)

Status: **ARCHITECTURE_ONLY** (no recovered weights).

```bash
cd reports/miui_biometric/reimpl
python pipeline.py          # prints every stage's I/O shape + a demo run
python pipeline.py --json   # machine-readable shape table
```

`pipeline.py` is self-contained: it needs only `numpy` + `jax` (no flax, no
TensorFlow, no flatbuffers).

## What is real vs. random

| element | source | confidence |
|---|---|---|
| stage decomposition (detect → align/pose/eye → quality → liveness → embed → match) | `libstfaceunlockocl.so` class names + `__res_*` model symbols + Java flow | confirmed |
| 8 model blobs, their exact offsets/sizes | `nm -S` on `libstfaceunlockocl.so` | confirmed |
| embedding input 112x112x3 | `M_Verify_MobileNetV2T6_*` family + `DetectorHunter::PadBlobInput` | inferred |
| MobileNetV2 (t=6) trunk, 17 inverted-residual blocks | model name `MobileNetV2T6` | inferred |
| 512-d L2-normalised embedding | `verif_engine::Engine`, `FeatureCmp::loadNormFile` | inferred |
| 106 landmarks, pose 3-dof, eye-state 2-way | `M_Align_occlusion_106*`, `M_Align_CalcPose_Ann_106*`, `M_Align_Deepface_Eye_State_Analysis*` | confirmed (counts) |
| liveness is a full-frame RGB CNN | `IM_RGB_Liveness_General_Full_FP32.10.1.65` | confirmed |
| quality is a small attribute net (MTNet) | `M_Attribute_MTNet_Quality.1.0.0` | confirmed |
| cosine matcher + threshold | `stsdk::FeatureCmp`, `cv_common_feature_comparator_normalize_score_by_threshold` | inferred |
| **all numerical weights** | — | **unavailable (encrypted)** |

Any number the network actually produces here is meaningless. The value of the
module is the I/O contract and the op graph, which are the parts recovered from
the artifact.

## Preprocessing (`preprocess`, stage 0)

Recovered from `FaceAuthManager` (Java) + `st_image_t` (DWARF):

```
FrameCallback bytes (NV21, stride = width)     <-- CvPixelFormat.NV21 = 8
  -> nv21_to_rgb()                              (BT.601 studio-swing matrix)
  -> rotate_rgb(orientation)                   0/90/180/270, st_auth_set_face_orientation
  -> crop_roi(st_rect_t)                       st_auth_set_roi / setDetectArea
  -> resize_bilinear(112, 112)                 ImageHepler::ResizeImage
  -> normalize((x-127.5)/128)                  mean/scale set by st_auth_set_mean_value
```

The exact mean/scale constants are pushed at run time from the model net config
(`cv_model_net_config_t`) and are not recoverable while the model blobs stay
encrypted — that is why `normalize()` is marked inferred.

## Files

* `pipeline.py` — stages 0-7, `init_params()` / `apply(params, x)`.
* `verify.txt` — command, environment, shapes, exit status of the last run.
