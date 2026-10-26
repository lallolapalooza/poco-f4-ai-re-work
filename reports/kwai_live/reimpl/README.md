# Kwai Live — JAX reimplementation

Runnable, weight-free stages of the FaceMagic camera chain, built from the
APK's own binaries and parameters.

```
python verify.py      # 10 checks, writes verify.txt (exit 0)
python pipeline.py    # demo: synthetic frame -> beautified + body-slimmed frame
```

Requirements: `jax`, `numpy` (workspace venv: `/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python`).
CPU only; no TensorFlow, no flatbuffer access.

## API

```python
from reimpl import pipeline
params = pipeline.init_params()            # APK presets + body-slim op lists + identity LUT
out = pipeline.apply(params, frame, ctx)   # frame HxWx3|4 float32 in [0,1] -> HxWx4
# ctx: preset_index, skin_mask, soften_mask, adjust_mask, landmarks, body_intensities
```

## What is real vs. reconstructed

| Piece | Source | Status |
|---|---|---|
| G3 beautify blend (`beauty.beautify_g3`) | GLSL embedded in `libFaceMagic.so` (`evidence/shaders/facemagic_61b3c0.glsl`), 3x3 mean/variance passes (`…61815c`, `…617dba`), LUT sampler (`…5b89c0`) | confirmed, transcribed |
| Beauty presets | `assets/beauty_list` (copied to `data/beauty_list`) | confirmed |
| Body-slim / deform warp (`liquify.py`) | transfer functions recovered from `CGE::CGELiquifyFilter` (`native/libCGE_liquify.c`) | confirmed math, dense-field instead of GPU mesh |
| Warp parameters | `assets/BodySlimming/{Filter28p,Filter40p}/*.params` (copied to `data/`) | confirmed |
| Skin/soften/adjust masks | YCNN segmentation models | **not available** (downloaded at runtime); synthetic ellipse stand-in |
| Landmarks | YCNN human keypoint model | **not available**; synthetic 63-point layout |
| Face deform (`faceDeform` JSON keys) | requires the face mesh topology | not implemented |

`textureShift[0..5]` offsets come from a vertex shader that is not embedded
as text; the neighbour ordering in `beauty.TAPS_A/B/C` is documented as
inferred.

## Verification (`verify.txt`)

* JAX vs an independent NumPy transcription of the same GLSL: max abs error 7.5e-08.
* Identity when `blurAlpha = brightAlpha = sharpness = 0` (exact) and when
  body-slim sliders are 0 (1.9e-06, float32 bilinear rounding).
* Effect strength monotonic in the preset intensity; warp deterministic and
  bounded (`max_uv_displacement 0.0507`).
* Multi-resolution runs 32x32, 128x96, 256x144.

Status reported in `findings.json`: **runs_unverified** — matches the
recovered reference implementations, but no device GL render was available
for a numeric comparison, and the learned stages are `ARCHITECTURE_ONLY`.
