# reimpl — Kwai vision/beauty pipeline in JAX

Status: **runs, exit 0** (`verify.txt`).  See `../report.md` §6 and
`../ir/ycnn_io_contract.md` for the evidence behind every constant.

## Run

```bash
cd reports/kwai_video/reimpl
python pipeline.py                 # artifacts go to ./out
```

Requirements: `jax`, `numpy` (the workspace venv `/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312` has both).
CPU-only is fine.

Expected output (abridged):

```
[1] NV21 encode/decode  roundtrip_psnr=36.43 dB
[2] crop -> net input   x=(128, 128, 3)
[4] beauty mean/var     mean=(270,480,3) var=(270,480)
[5] Hald LUT (REAL)     lut=(64,64,3) from assets/default_enhance_lut.png
[V1] JAX vs numpy parity max_abs_err=0.000e+00
[V3] LUT identity check  max_delta=3.209e-07
RESULT: PASS
```

## What is real vs. what is a placeholder

| stage | fidelity | source of truth |
|---|---|---|
| NV21 → RGB (BT.601 limited, `ceil(x*255+0.5)/255`) | **real** | `libgorgeous.so` GLSL `yuv2rgb()`; `libykit.so` `NV21ToARGB` (libyuv) |
| crop/resize to net input | **real semantics, placeholder size** | `ycnn::YCNNCropDataMatrix` signature; size comes from the `.model` header (absent) |
| 5-tap mean + variance pass | **real** | pass-1 fragment shader @`0x12f75d` |
| 3×3 variance/mean blur (`*0.1111`) | **real** | pass-2 shader @`0x12fc3d` |
| `kMin` skin smoothing (`theta=0.1`, `p=clamp((min(in.r,blur.a)-0.2)*4)`) | **real** | beautify shaders @`0x1312c8`, `0x1319be` |
| Hald 3D LUT | **real weights** | `vec3 lookup()` GLSL + `assets/default_enhance_lut.png` (8×8×8 CLUT) |
| luma unsharp mask | **real** | spliced `vec3 sharpen(...)` rodata in `libgorgeous.so` |
| face detector / landmarks / matting | **ARCHITECTURE_ONLY** | YCNN `.model` weights and graphs are *not in the APK*; `ycnn_contract.py` implements the recovered I/O contract with random weights |

`ycnn_contract.py` additionally carries the recovered constants: the 35
`YCNNModel*` type ids (`Detector=21`, `Landmarks=5`, `HumanMatting=0`, …),
`YCNNModelIn.colorType=3`, and the `KSLandmarksParam` defaults
(`maxFaceNum=4, minFaceSize=50, detectIntervals=5, detectMode=4`).

## Files

* `pipeline.py` — driver; prints the shape table, writes `out/beauty_out.npy`, `out/nv21.npy`.
* `gorgeous_beauty.py` — JAX transcription of the Gorgeous beauty GLSL.
* `pnglut.py` — dependency-free PNG decoder + Hald CLUT sampler (`vec3 lookup()`).
* `ycnn_contract.py` — YCNN contract + placeholder detector head.
* `weights/default_enhance_lut.png` — symlink to `../models/default_enhance_lut.png` (extracted from the APK).
