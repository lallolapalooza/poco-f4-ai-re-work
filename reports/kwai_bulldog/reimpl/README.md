# kwai_bulldog reimplementation

`pipeline.py` — JAX, status **ARCHITECTURE_ONLY**.

```
/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python pipeline.py
/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python pipeline.py --net 96 --width 320 --height 240 --seed 7 --json
```

Verified output is in `verify.txt` (exit 0). Stages:

| stage | function | contract source | weights |
|---|---|---|---|
| 0 camera frame → RGB | `nv21_to_rgb` | libykit NV21/NV12 + libyuv limited BT.601 | n/a |
| 1 preprocess | `preprocess` | ycnn `YCNNCropDataMatrix` (crop, bilinear resize, `inputMeanVals`) | n/a |
| 2 ycnn face detector | `init_params` / `apply` / `decode` | `KSJNILib.createModel/renderModel/updateModelOut` (libykit.so), config keys `net_w/net_h/stride_width/stride_height/score_thresh/nms_th` | random (deterministic) |
| 3 KVE video features | `kve_features` | `libvisionengine.so` feature enum (`kSharpness`, `kColorfulness`, `kLowLight`, `kPureColor`, `kIntraEntropy`, `kJunk`, `kSceneChange`, …) | none (image statistics) |

Why no real weights: the APK contains no `.tflite`/`.model` file. Every ycnn
model is a separately downloaded resource (`magic_ycnn_model_*`,
`voice_detect_mode.tflite`) whose URL comes from the config response
(`YlabModelConfigResponse`) and whose local copy is validated against
`check.json`. `libykit.so` carries the runtime (`ycnnnet` v3/v4) and embedded
JSON config tables only.

Claims marked ARCHITECTURE_ONLY must not be used for numeric parity. The
`apply` graph (3 stride-2 conv blocks → heatmap + 2K regression head) is a
documented stand-in for the real per-model graph, which is inside the
downloaded `.model` file.
