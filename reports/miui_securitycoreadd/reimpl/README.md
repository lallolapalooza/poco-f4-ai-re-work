# reimpl — JAX reimplementation of `assets/aiautobrt.tflite`

Target: `com.miui.securitycore` ("System features plug-in"), MIUI individual
auto-brightness. Model sha256 `f6d75f1411398fd1a4dee77bd43409d47ac0a011f979c831f9a3e6b7735146d3`.

## Files

| file | purpose |
|---|---|
| `model.py` | inference (both MLPs), loss, one exact TF1-Adam training step |
| `pipeline_lux.py` | **P1 app pipeline**: `(ambientLux, luxSpanIndex, appCategoryId)` → `x[1,3]` → model → nits (+ normalized), with the service's validation/fallback gates |
| `reference_train.py` | independent NumPy transcription (hand-written backprop + Adam) |
| `verify.py` | runs all checks, writes `verify.txt` |
| `weights.npz` | real weights, byte-exact from subgraph 6 (`ir/weights.npz`) |
| `verify.txt` | last model-level verification transcript (§F points at `verify_lux.txt`) |
| `verify_lux.txt` | pipeline transcript of `pipeline_lux.py` |
| `GAP_CLOSURE.md` | gap closure: pipeline ↔ `IndividualModelService` / `CustomBrightnessModeController` mapping |

## Run

```bash
PY=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python
cd reports/miui_securitycoreadd/reimpl
$PY model.py       # shapes + one train step
$PY verify.py      # numeric comparison against the LiteRT interpreter -> verify.txt
$PY pipeline_lux.py                            # P1 lux -> nits table, exit 0
$PY pipeline_lux.py --nit-bounds 0 1740        # + framework normalizer bounds
```

Requirements: `jax`, `numpy`, `ai_edge_litert` (2.3.0 used here).

## Architecture (confirmed from the op list)

```
x[.,3] -> Dense(3)  ReLU -> Dense(128) ReLU -> Dense(64) ReLU -> Dense(1)
```
Keras layout (`kernel` is `[in,out]`); TFLite transposes/reshapes it before
`FULLY_CONNECTED`, which is `x @ kernel + bias`. 8,845 parameters per model.

Two such networks live in the flatbuffer:
* `new_model_1/*` — student, ships with zero biases and Glorot kernels,
* `dnn_model/*`   — teacher, ships trained (used for serving).

## Training step (subgraph 5)

```
loss = 0.9 * mean(100*|(y - new(x))/max(|y|,1e-7)|)
     + 0.1 * mean(100*|(old(x) - new(x))/max(|old(x)|,1e-7)|)

t    = iter + 1
lr_t = 0.001 * sqrt(1 - 0.999**t) / (1 - 0.9**t)
m    = 0.9*m + 0.1*g
v    = 0.999*v + 0.001*g*g
w    = w - lr_t * m / (sqrt(v) + 1e-7)          # dnn_model_1/* only
```

`iter` is incremented after the update (`Adam/iter`), so the first step uses `t = 1`.

## Verification status

* **Inference: numerically verified** against `ai_edge_litert`
  max relative error ≤ 4.8e-7 over 256 app-domain rows and 128 wide-range rows.
* **Training: reimplemented and cross-checked**, but *not* against the reference
  interpreter: subgraph 5 contains Select-TF (Flex) ops (`FlexBroadcastGradientArgs`,
  `FlexSign`) that `ai_edge_litert` 2.3.0 cannot execute without the Flex delegate
  shipped as `lib/arm64-v8a/libtensorflowlite_flex_jni.so`. Checks used instead:
  independent NumPy transcription (weights agree to 1.5e-8 after 8 steps) and
  central finite differences (max |Δ| 1.6e-9).

## Not reimplemented

Subgraphs 2 (`restore`), 3 (`save`), 4 (`save_for_replace`) are file-side-effect
Flex ops; 6 is the initializer that only supplies the values in `weights.npz`.
