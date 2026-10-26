# miui_securitycoreadd — `com.miui.securitycore` "System features plug-in"

## Identity

| Field | Value |
|---|---|
| APK | `/run/media/asdf/.../product/app/SecurityCoreAdd/SecurityCoreAdd.apk` |
| Package / label | `com.miui.securitycore` / *System features plug-in* |
| Version | 1.4.2-15-240924 (code 40000142), minSdk 30, targetSdk 33 |
| Size / sha256 | 32,473,507 B / `e87d13eb…0ece3761` (`evidence/hashes.txt`) |
| ABI | arm64-v8a only |
| AI artifact | `assets/aiautobrt.tflite`, 197,836 B, sha256 `f6d75f14…35146d3` |
| AI native libs | `lib/arm64-v8a/libtensorflowlite_jni.so` (1.64 MB), `lib/arm64-v8a/libtensorflowlite_flex_jni.so` (10.6 MB) |
| DEX | one `classes.dex` (3.5 MB), 4,143 classes |

**What the AI does:** MIUI's *individual / personalized auto-brightness* plug-in. It
keeps a per-device `brightness_model_event` history in Room, predicts a screen
brightness for the current ambient-light context with a small MLP, and periodically
re-trains a second ("new") MLP on-device with Adam to imitate both the user's chosen
brightness and the shipped ("old") model. A trained model replaces the old one only
if it beats both the old model and the stock brightness curve on held-out data.

All of this lives in one TFLite flatbuffer with **7 subgraphs and 6 signatures**,
plus ~1.2 k lines of (obfuscated) Java; there is no other model in the APK.

---

## 1. Feature map

| # | Feature | Confidence | Evidence |
|---|---|---|---|
| F1 | Per-event brightness prediction (`predictBrightness` AIDL call) using the TFLite `old_model_infer` signature | confirmed | `IndividualModelService$a.predictBrightness`, `g3.a.a()` |
| F2 | Event logging into `brightness_model_event` on every `onEventChanged` AIDL call | confirmed | `f3.c.f()`, `h3.c.b()` |
| F3 | Periodic on-device training job (JobScheduler 93197, 24 h, device-idle) that runs `train` (Adam) on 90 % of the stored events | confirmed | `TrainJobService`, `i3.a.run()` |
| F4 | Model acceptance / replacement policy (validation MAPE vs old model, 4-bucket check vs the stock curve) and checkpoint save/restore | confirmed | `i3.a.run()` ops 180-230 |
| F5 | AB-test hook that can push an experiment id / brightness value to the framework (`onAbTestExperimentUpdated`) | inferred (code present, payload semantics unknown) | `f3.b`, `f3.c.e()`, `y6/z6` SDK |
| F6 | Test-only replay of `filesDir/userdata.csv` on `userdebug` builds | confirmed | `f3.f`, `f3.e` |

Components (from the binary manifest): `com.miui.aiautobrt.service.IndividualModelService`
(`exported=true`, action `com.miui.aiautobrt.service.AiService`) and
`com.miui.aiautobrt.service.TrainJobService` (not exported).

The `com.xiaomi.aiautobrt.*` types (`IIndividualBrightnessService`, `IIndividualCallback`,
`IndividualModelEvent`, `IndividualTrainEvent`, `InterfaceVersionHelper`) are **not**
defined in this APK — they are dex *references* resolved from the Xiaomi framework.

---

## 2. End-to-end pipeline (inference)

```
framework brightness service
   │  bindService(action=com.miui.aiautobrt.service.AiService)
   ▼
IndividualModelService.onBind → IIndividualBrightnessService.Stub  (IndividualModelService$a)
   │  attach(callback) / provideInterfaceVersion(v)
   │  onEventChanged(IndividualModelEvent)        → f3.c.f(event)  → Room insert
   │  predictBrightness(IndividualModelEvent)
   ▼
f3.c.a(event)                              ← feature builder (h3.a)
   x0 = ln(ambientLux + 1)
   x1 = ln(ambientLuxSpan + 1)
   x2 = ln(appCategoryId + 1)              (mixedOrientationApp is stored but unused)
   ▼
f3.d  (ModelController, singleton per process)
   • mmaps assets/aiautobrt.tflite
   • checkpoint = new File(context.getFilesDir(), "checkpoint")
   • if that file exists → g3.a.e()  → signature "restore"
   ▼
g3.a.a("old_model_infer", h3.a)            ← only the OLD model predicts
   inputs  {"x": float[1][3]}   outputs {"pre": FloatBuffer(1)}
   ▼
IndividualModelService$a.predictBrightness
   if output == -1.0f → fall back to event.getCurrentBrightness()
   callback.onPredictFinished(ambientLux, appCategoryId, brightness)
```

Facts and their source:

* Feature vector: `f3.c.a(IndividualModelEvent)` sets
  `aVar.c = log(lux+1)`, `aVar.f4046d = log(luxSpan+1)`, `aVar.f4048f = log(appCategoryId+1)`,
  `aVar.f4047e = log(mixedOrientationApp+1)`, plus `f4049g = currentBrightness`,
  `f4050h = currentBrightnessSpan`, `f4051i = defaultConfigBrightness`.
  `g3.a.a()` copies only **`c`, `f4046d`, `f4048f`** into `float[1][3]` → the model input is
  `[ln(lux+1), ln(luxSpan+1), ln(appCategoryId+1)]` (confirmed).
* Gating: prediction only runs when `f3605e >= 2`, where `f3605e` is the framework
  interface version delivered by `provideInterfaceVersion(int)`. Below that the service
  returns the event's own brightness (confirmed).
* Output: the raw `pre` float is *the brightness value* (same units as
  `IndividualModelEvent.getCurrentBrightness()`); the old model returns ≈3.9 at total
  darkness and up to ≈1735 at `lux=50000` (`evidence/interpreter_reference.txt`).
* Signature `new_model_infer` is **not** used for serving — only `old_model_infer` is
  (confirmed by grep of all signature string literals: `old_model_infer` in the service,
  `new_model_infer`/`old_model_infer` in `i3.a` validation, `train`, `save_for_replace`,
  `restore`).
* Log tag `IndividualBrt-ModelController` prints `input=<event>, output=<brightness>`
  for every prediction.

Model loading/threading: the model is mmapped from assets at controller construction
(process-wide singleton `f3.c.c(ctx)`); inference runs synchronously on the Binder
thread that delivers `predictBrightness`. `org.tensorflow.lite.a` is the stock
`Interpreter` with an added `runSignature(Map inputs, Map outputs, String key)`;
`NativeInterpreterWrapper` auto-loads `FlexDelegate` when
`hasUnresolvedFlexOp()` is true.

---

## 3. The model file

`assets/aiautobrt.tflite` — TFLite v3 flatbuffer, 7 subgraphs, 6 signatures,
35 operator codes: 30 builtins + 5 Flex custom ops (`FlexRestore`, `FlexSave`, `FlexBroadcastGradientArgs`, `FlexSign`, `FlexReluGrad`).

| sg | name | role | I/O | ops | used by Java? |
|---:|---|---|---|---:|---|
| 0 | `new_model_infer` | inference | `x[1,3]` → `pre[1,1]` | 29 | validation only |
| 1 | `old_model_infer` | inference | `x[1,3]` → `pre[1,1]` | 29 | **serving** |
| 2 | `restore` | restore | `checkpoint_path` → 16 tensors | 49 | startup / after training |
| 3 | `save` | save | `checkpoint_path` → `checkpoint_path` | 34 | **not referenced in code** |
| 4 | `save_for_replace` | save | `checkpoint_path` → `checkpoint_path` | 26 | after successful training |
| 5 | `train` | train | `train_y[·,1]`, `train_x[·,3]` → `loss` | 355 | JobScheduler job |
| 6 | `NoOp` | init | – | 66 | `CALL_ONCE` target of sg 0/1/2/5 |

Signatures (`ir/aiautobrt.ir.json`):

```
new_model_infer : x        -> pre
old_model_infer : x        -> pre
restore         : checkpoint_path -> 16 × "<var>:0"
save            : checkpoint_path -> checkpoint_path
save_for_replace: checkpoint_path -> checkpoint_path
train           : x, y     -> loss
```

Subgraphs 0, 1 and 5 begin with `CALL_ONCE(InitSubgraphIndex=6)`; subgraph 6 assigns
every variable's **initial value** from a float32 constant buffer (see §5).

---

## 4. Inference MLP (subgraphs 0 and 1)

Both networks are the same shape; only the variable names differ
(`dnn_model_1/dense_4…dense_7` vs `dnn_model/dense…dense_3`).

| layer | shape (in→out) | kernel | bias | activation | ops (sg0 / sg1) |
|---|---|---|---|---|---|
| 1 | 3 → 3 | [3,3] | [3] | ReLU | 17-19 / 17-19 |
| 2 | 3 → 128 | [3,128] | [128] | ReLU | 20-22 / 20-22 |
| 3 | 128 → 64 | [128,64] | [64] | ReLU | 23-25 / 23-25 |
| 4 | 64 → 1 | [64,1] | [1] | none | 26-28 / 26-28 |

So: **3 → 3 → 128 → 64 → 1, ReLU on the first three layers, linear output**
(8,845 parameters per model). The kernels are stored Keras-style `[in, out]`; the
graph applies `TRANSPOSE` (ops 17/20/23) or `RESHAPE [64,1]→[1,64]` (op 26) before
each `FULLY_CONNECTED`, which is exactly `x @ kernel + bias`. ReLU is carried on the
`ADD` bias op (`options {'FusedActivationFunction': 'RELU'}`, tensor names
`…/Relu;…/BiasAdd`), so `FULLY_CONNECTED` itself reports `NONE`
(`ir/ops_sg0.txt`, `ir/ops_sg1.txt`, `ir/tensor_table.json`).

Variable read order (op 9-16) maps the 8 `READ_VARIABLE` results directly onto the
graph; the kernels/biases are plain float32 with no quantization
(`quantization.scheme = "none"`).

---

## 5. Weights — extraction and provenance

The variables are **not** encrypted or opaque: each is a TFLite *variable* tensor
(`RESOURCE`, empty buffer) whose initial value is a constant ASSIGN_VARIABLE'd by
subgraph 6. `ir/extract_weights.py` resolves `VAR_HANDLE.shared_name → ASSIGN_VARIABLE
← constant buffer` and writes `ir/weights.npz` / `ir/weights.json` (33 variables,
byte-exact float32; `ir/ops_sg6.txt` has all 66 init ops).

| variable | shape | min | max | mean |
|---|---|---|---|---|
| `dnn_model/dense/kernel` | 3×3 | −0.998058 | +0.743749 | −0.192550 |
| `dnn_model/dense/bias` | 3 | −0.696458 | 0.000000 | −0.232153 |
| `dnn_model/dense_1/kernel` | 3×128 | −0.211813 | +0.460339 | +0.017365 |
| `dnn_model/dense_1/bias` | 128 | −2.524004 | +0.046538 | −0.166359 |
| `dnn_model/dense_2/kernel` | 128×64 | −0.204381 | +3.158593 | +0.058679 |
| `dnn_model/dense_2/bias` | 64 | −0.029398 | +0.071395 | +0.015513 |
| `dnn_model/dense_3/kernel` | 64×1 | −0.301952 | +1.541082 | +0.285198 |
| `dnn_model/dense_3/bias` | 1 | 0.075260 | 0.075260 | 0.075260 |
| `dnn_model_1/dense_4/kernel` | 3×3 | −0.532129 | +0.898112 | +0.224948 |
| `dnn_model_1/dense_4/bias` | 3 | 0 | 0 | 0 |
| `dnn_model_1/dense_5/kernel` | 3×128 | −0.211813 | +0.213211 | +0.007516 |
| `dnn_model_1/dense_5/bias` | 128 | 0 | 0 | 0 |
| `dnn_model_1/dense_6/kernel` | 128×64 | −0.176669 | +0.176773 | −0.000122 |
| `dnn_model_1/dense_6/bias` | 64 | 0 | 0 | 0 |
| `dnn_model_1/dense_7/kernel` | 64×1 | −0.289809 | +0.301954 | +0.007216 |
| `dnn_model_1/dense_7/bias` | 1 | 0 | 0 | 0 |
| 16 × `Adam/<new var>/{m,v}` | as weight | 0 | 0 | 0 |
| `Adam/iter` | scalar int64 | 0 | 0 | 0 |

Reading: the **old** model carries a trained checkpoint (non-zero biases, a 3.16
outlier in `dense_2/kernel`), while the **new** model has exactly zero biases and
Glorot-like kernels — i.e. the asset ships "pretrained teacher + freshly initialised
student", and the student's Adam slots start at zero. Confidence: confirmed for the
values, inferred for the "fresh Keras init" interpretation.

Folding: subgraph 6 is the only source of weight constants. Subgraph 2
(`restore`) contributes **no** constants — it reads its 16 values from an external
TF checkpoint at run time — so with no checkpoint present (the packaged state,
`filesDir/checkpoint` absent) the folded subgraph-6 values *are* the effective
weights. That is the configuration the interpreter below runs.

Verification that these constants are what the runtime sees: `reimpl/verify.py`
loads them into JAX and compares against the LiteRT interpreter on 256 app-domain
rows, 128 wide-range rows, single rows and zeros — **max relative error 4.8e-7
(new model) / 3.6e-7 (old model)** (`reimpl/verify.txt`). Note that the Python
LiteRT API exposes `reset_all_variables()` but no variable *getter*, so the
equivalence is established through 386 outputs × 2 subgraphs rather than by reading
the variable tensors directly; a systematic weight error would show up as a large
output error immediately (the old model's outputs span 3.86 … 1735).

---

## 6. Training graph (subgraph 5) — loss, optimizer, gradient

355 operators, dynamic batch (`train_x` shape `[1,3]`, `shape_signature [-1,3]`;
`train_y [1,1]`, `[-1,1]`), one Adam step per invocation, returns `loss`
(`ir/ops_sg5.txt`, excerpt in `evidence/train_graph_key_ops.txt`).

### 6.1 Loss

Two MAPE terms are combined (op 353-354):

```
loss = 0.9 * MAPE(y_true = train_y,  y_pred = new_out)
     + 0.1 * MAPE(y_true = old_out,   y_pred = new_out)

MAPE(t, p) = mean_over_batch( 100 * |(t - p) / max(|t|, 1e-7)| )
```

Op-index evidence:

| step | ops |
|---|---|
| old-model forward (teacher) | 55-66 (`dnn_model/*` weights, `FULLY_CONNECTED`+`ADD(RELU)`) |
| new-model forward (student) | 71-82 |
| distillation `Abs(old−new)` | 85 (SUB 86,102), 88 ABS, 89 MAXIMUM(·,1e-7), 106 SUB, 110 DIV, 112 ABS |
| `Mean(axis=-1)` × 100 | 96 MEAN, 103 MUL(·, 100.0) |
| `Sum` / `num_elements` | 105 SUM → 129; 127 DIV → 151; then × **0.1** (op 129 → tensor `mul_1`) |
| supervised `Abs(train_y−new)` | 135 SUB(0:train_y, 102), 157 ABS, 158 MAXIMUM(·,1e-7), 160 SUB, 164 DIV, 166 ABS |
| `Mean` × 100, `Sum`/`num_elements` | 145 MEAN, 152 MUL(·,100), 154 SUM → 182, 155-156 num_elements |
| final mix | 351 DIV, 352 SELECT (`div_no_nan`), **353 MUL(·, 0.9)**, 354 ADD( + `mul_1`) → `loss` |

Constants read from the flatbuffer: `mean_absolute_percentage_error/mul/x = 100.0`,
`mul/x = 0.9`, `mul_1/x = 0.1`, `Adam/Const = 1e-7` (the `max(|t|, ε)` clip).

### 6.2 Optimizer — `tf.compat.v1.train.AdamOptimizer`, unfused

Graph structure (ops 199-221 for `dense_4/kernel`, repeated for the other 7 tensors;
variable list in ops 0-33):

```
t     = iter + 1                                   (ops 34-36, iter at 348-350)
lr_t  = 0.001 * sqrt(1 - 0.999**t) / (1 - 0.9**t)  (ops 37-38 POW, 199-203)
m     = 0.9 * m + 0.1 * g                          (ops 205-207)
v     = 0.999 * v + 0.001 * g*g                    (ops 209-212)
w     = w - lr_t * m / (sqrt(v) + 1e-7)            (ops 213-218)
```

Constants: learning rate `0.001` (`Adam/Cast/ReadVariableOp`), β1 `0.9` (`mul/x`),
β2 `0.999` (`Adam/Cast_3/ReadVariableOp`), `1−β1 = 0.10000002`, `1−β2 = 0.00099999`,
ε `1e-7` — all as float32 scalars in subgraph 5 (`ir/weights.json` provenance and
`evidence/train_graph_key_ops.txt`).

**Only `dnn_model_1/*` gets gradients** (op 176-198 backprop chain: `BiasAddGrad`
→ `ReluGrad` → `MatMul` grads through dense_7…dense_4); the old model is a constant
teacher that also contributes the 10 % distillation term. `Adam/iter` is incremented
after the update (ops 348-350), so the first step uses `t = 1`.

Flex custom ops used only here: `FlexBroadcastGradientArgs` (op 84, 87, 102, 131,
137, 151), `FlexSign` (ops 89, 139), `FlexReluGrad` (ops 179, 182, 185, …) and Flex ops inside the gradient tape. This is why the
Python LiteRT interpreter cannot execute the `train` signature (see §9).

---

## 7. Training orchestration (Java) and data lifecycle

`TrainJobService.onStartJob` → `new Thread(new i3.a(this, jobParameters)).start()`
(`i3.a` is the runnable; `TrainJobService` schedules it as job **93197**, periodic
24 h, `setRequiresDeviceIdle(true)` — created in `IndividualModelService.onCreate`).

`i3.a.run()` (line-referenced in `sources/train/i3.a.java`):

1. Require `f3605e >= 2` (framework interface version).
2. `newEvents = c.d(f3604d)` (rows after the in-memory offset `f3604d`),
   `all = c.d(0)` (the whole table).
3. Train only if `newEvents >= 10000`, or `Settings.Secure force_train_enable == 1`
   and `newEvents >= 1000`. On trigger, `f3604d += newEvents.size()`.
4. `Collections.shuffle(all)`; 90 % → `d.f3610d` (train), 10 % → `d.f3611e` (validation).
5. Up to **5 epochs**, each epoch shuffles the train list and iterates **batches of
   128** (a short tail re-uses the *last* 128 samples); each batch fills
   `x[128][3] = {ln(lux+1), ln(luxSpan+1), ln(appCat+1)}` and `y[128][1] = cur_brt`,
   then calls signature `"train"` with outputs `{"loss": FloatBuffer(1)}`.
   The last batch loss is kept (`f11`); 0 batches ⇒ abort.
6. Validation (one sample at a time): `newMAPE = mean(|(cur − new_pred)/cur|)`,
   `oldMAPE = mean(|(cur − old_pred)/cur|)`; log `Model train result newFinalMape = …
   oldFinalMape = …`; fill `IndividualTrainEvent(trainSampleNum, modelTrainLoss,
   curModelMAPE, preModelMAPE)`.
7. **Replace** if `newMAPE < oldMAPE`: ensure `filesDir/checkpoint` exists, run
   `"save_for_replace"`, then `g3.a.e()` (`"restore"`).
8. **Bucket check** (only if replaced): current brightness buckets
   `<8`, `8–50`, `50–200`, `≥200` (`j8.m.f4504d = {8.0, 50.0, 200.0}`); accumulate
   `|(cur − defaultConfigBrightness)/cur|` (stock curve) vs `|(cur − old_pred)/cur|`
   per bucket. The model is accepted only if it is not worse in *every* bucket
   (the accumulator compares per-bucket **sums** over the same samples, which is
   equivalent to comparing bucket means; empty buckets compare 0 vs 0), or if
   `SystemProperties debug.miui.display.dbg` has bit `128` set. jadx emits the
   accumulate statement twice in the `else` branch ("code duplicated" warning) -
   that is a decompiler artifact; the bytecode accumulates once per sample.
9. On acceptance: `IIndividualCallback.onTrainFinished()` then `f3.c.b()` →
   `f3604d = 0`, `DELETE FROM brightness_model_event`, `sqlite_sequence.seq = 0`.
   Always: `onTrainIndicatorsFinished(IndividualTrainEvent)`.
10. Retention: if the offset `f3604d >= 50000` → if the newest row id is
    `2147473647` clear everything, else `f3604d = 0` and delete the oldest 10000 rows
    repeatedly until fewer than 50000 remain. Finally `jobFinished(params, false)`.

The DAO (`h3.c`) is a generated Room DAO:

```
table brightness_model_event(
  mId INTEGER PRIMARY KEY AUTOINCREMENT,
  ambient_lux REAL NOT NULL, ambient_lux_span REAL NOT NULL,
  orientation_and_app_industry REAL NOT NULL, app_code REAL NOT NULL,
  cur_brt REAL NOT NULL, cur_brt_span REAL NOT NULL, curve_brt REAL NOT NULL)
```
Database name `AiBrightnessModel.db` (`gb.c.g(Context)`), Room version 1
(`BrightnessModelDatabase_Impl`, identity hash in the generated code). Inserts are
batched (`b(a...)`), `d()` deletes the 10 000 oldest, `e()` resets the autoincrement,
`f()` returns the newest row.

Selection bias worth naming: the training labels `y = cur_brt` are the brightness the
user/system settled on, i.e. the model is trained to reproduce observed behaviour
(90 %) while staying close to the shipped model (10 %). Confidence: confirmed for the
maths, inferred for that interpretation.

---

## 8. Persistence and "versioning"

* **Checkpoint**: a single TF checkpoint *prefix* `filesDir/checkpoint`
  (`f3.d` constructor). It only exists after a successful training run; on process
  start `g3.a` calls `restore(path)` if `new File(filesDir,"checkpoint").exists()`.
* **Key sets** (TFLite string tensors, decoded from the flatbuffer):
  * `restore` (sg 2): 16 names `dnn_model/dense/kernel:0 … dnn_model_1/dense_7/bias:0`,
    each read by its own `FlexRestore` op (op 17,19,…,47) and assigned to the
    matching variable.
  * `save` (sg 3): writes the same 16 names from the 16 current variables.
  * `save_for_replace` (sg 4): writes the same 16 names, but **all 16 values are read
    from the 8 `dnn_model_1/*` variables** (each read twice, ops 9-24). That is the
    "replace" semantic: the freshly trained student overwrites *both* the old-model
    and new-model keys, so after `restore` the old model equals the trained one and
    the student continues from there.
* Java calls `restore` and `save_for_replace` only; `save` is dead code in this APK.
* There is **no model version number**; the only version in play is the framework
  interface version (`f3605e`, set by `provideInterfaceVersion`, gate `>= 2`) and the
  Room schema version. Model freshness is tracked implicitly by the `f3604d` row
  offset (in-memory, reset when the table is cleared).
* Weights are stored uncompressed as float32 in both the asset and the checkpoint —
  no encryption, no per-device wrapping was found.

---

## 9. Subgraphs 2-6 and why they are out of the inference reimplementation

| sg | content | why excluded / status |
|---:|---|---|
| 2 `restore` | `VAR_HANDLE` ×16, `CUSTOM FlexRestore` ×16, `ASSIGN_VARIABLE` ×16 (ops 17-48) | I/O is a file path; needs the Flex delegate and TF checkpoint format. Documented; not needed to reproduce predictions. |
| 3 `save` | `CUSTOM FlexSave` with 16 values + 16 names (op 33) | Side-effect op, unused by the app. |
| 4 `save_for_replace` | 8 reads duplicated → 16 values + 16 names (op 25) | Side-effect op; semantics captured in §8. |
| 5 `train` | 355 ops: forward (teacher+student), MAPE loss, gradient tape, Adam | Reimplemented in JAX (`reimpl/model.py::train_step`) and cross-checked; cannot be run in the reference interpreter (Flex). |
| 6 `NoOp` (init) | 33 `VAR_HANDLE` + 33 `ASSIGN_VARIABLE` | Executed once by `CALL_ONCE`; its constants *are* the initial weights and are folded into `ir/weights.npz`. |

None of 2-6 influences a single inference: subgraphs 0/1 only read variables.

---

## 10. JAX reimplementation

`reimpl/model.py` — `load_params()`, `apply()` (new), `apply_old()` (old),
`total_loss()`, `train_step()` (one exact TF1-Adam step). `reimpl/reference_train.py`
is an independent NumPy transcription (hand-written backprop) used as a cross-check.
`reimpl/weights.npz` holds the real extracted weights.

```
$ python reimpl/model.py
input (1, 3)
new_model_infer (1, 1) [0.2089428]
old_model_infer (1, 1) [27.061157]
one train step loss 98.9825439453125

$ python reimpl/verify.py        # writes reimpl/verify.txt, exit 0
```

`reimpl/verify.txt` (abridged):

```
--- A. inference vs LiteRT interpreter ---
  new_model_infer app-domain 256x3  max|err|=1.192e-06  max|rel|=4.768e-07
  old_model_infer app-domain 256x3  max|err|=3.662e-04  max|rel|=2.895e-07
  new_model_infer wide 128x3        max|err|=9.537e-07  max|rel|=4.768e-07
  old_model_infer wide 128x3        max|err|=4.883e-04  max|rel|=3.554e-07
  new_model_infer single row 1x3    max|err|=2.384e-07  max|rel|=9.607e-08
  old_model_infer  zeros 1x3        max|err|=2.384e-07  max|rel|=6.180e-08
--- C. training step: JAX vs independent NumPy transcription ---
  step 0..7 losses agree to 7.6e-06; weights+m+v agree to 1.49e-08 after 8 steps
--- D. gradient vs central differences (float64) ---
  max |fd-analytic| = 1.640e-09, max relative (|g|>=1e-2) = 1.156e-07
```

**Numerical match is achieved for inference**; the residual is float32 summation order
inside the 128-wide layer (relative ≈3-5e-7). The **train** signature cannot be run by
`ai_edge_litert` 2.3.0 because subgraph 5 contains Select-TF (Flex) ops
(`FlexBroadcastGradientArgs`, `FlexSign`, `FlexReluGrad`) that only the APK's
`libtensorflowlite_flex_jni.so` provides — the exact interpreter error is captured in
`reimpl/verify.txt` check B. The training maths is therefore validated against an
independent NumPy transcription and finite differences instead.

---

## 11. Confidence summary and unknowns

Confirmed (observed in artifact/code): model identity/format, 7 subgraphs and their
I/O, MLP shapes/activations, weight values and their initializer provenance,
serving path (`old_model_infer`), feature definition, training trigger/loop/
hyper-parameters/loss, checkpoint key sets and replace semantics, DB schema,
bucket thresholds, JAX numerical match for inference.

Inferred: that `dnn_model` is the shipped trained teacher and `dnn_model_1` a fresh
student; that `cur_brt` is a user-settled brightness (labels); purpose of the
`save` signature (unused); AB-test payload semantics.

Unknown: the framework-side AIDL (transaction codes / event field population) since
`com.xiaomi.aiautobrt.*` is not in this APK; whether `force_train_enable` /
`debug.miui.display.dbg` are used in production builds; the exact meaning of
`ambient_lux_span`, `orientation_and_app_industry`, `app_code`,
`current_brightness_span`, `curve_brt` beyond their DB names; device-specific
checkpoint contents (we only see the packaged asset, which has the student untrained).

> **Superseded by §13/§14.** The AIDL transaction codes, the field population and the
> units of all seven event fields were resolved from the device system image
> (`miui-framework.jar`, `miui-services.jar`, `services.jar`). See
> `evidence/framework_paths.txt` and `PIPELINE.md`. The remaining genuine unknowns
> are the framework-res numeric config (lux horizons, light-sensor rate, validation
> lux grid) and the AXML `android:permission` attribute of the service.

## 12. Evidence index

| Path | Contents |
|---|---|
| `evidence/hashes.txt` | sha256 of APK, model, both `.so` |
| `evidence/interpreter_reference.txt` (+ `.py`) | reference outputs of sg 0/1 over a lux×app grid |
| `evidence/train_graph_key_ops.txt` | annotated op excerpts of subgraph 5 |
| `evidence/commands.txt` | command transcripts used to build this report |
| `ir/aiautobrt.ir.json` | full IR (all subgraphs, tensors, ops, options) |
| `ir/ops_sg0.txt` … `ops_sg6.txt` | one line per operator, resolved tensor names/shapes |
| `ir/tensor_table.json` | every tensor: index, name, shape, shape_signature, dtype, buffer |
| `ir/weights.npz`, `ir/weights.json`, `ir/extract_weights.py` | byte-exact variable values + provenance |
| `reimpl/verify.txt` | verification transcript (exit 0) |
| `sources/…` | targeted jadx output (see `sources/index.md`) |
| `sources/framework/` | framework dex files (`miui-framework.dex`, `miui-services.dex`, `services/classes*.dex`) + jadx output for the AIDL types and the `com.android.server.display.aiautobrt.*` engine (§13, §14) |
| `PIPELINE.md` | the two data pipelines (inference, training) in the required 9-section form |
| `evidence/framework_paths.txt` | extraction commands, transaction table, field/unit table, spline and job constants |
| `native/*.elfinfo.{txt,json}` | ELF metadata + JNI exports of the two TFLite libs |
| `native/flex_nativeCreateDelegate.s`, `native/flex_nativeInitTensorFlow.s`, `native/jni_hasUnresolvedFlexOp.s` | aarch64 disassembly of the Flex-delegate and flex-detection JNI entry points |
| `inventory/` | APK inventory (manifest, zip groups, AI flags) |

---

## 13. Framework side: input construction, AIDL plumbing, output consumption

The APK is one half of the loop. The other half lives in the **system image**, not
in the APK, and was recovered for this report:

| Artifact | Classes |
|---|---|
| `system_ext/framework/miui-framework.jar` (`classes.dex`) | `com.xiaomi.aiautobrt.{IIndividualBrightnessService, IIndividualCallback, IndividualModelEvent, IndividualTrainEvent, InterfaceVersionHelper}` |
| `system_ext/framework/miui-services.jar` (`classes.dex`) | `com.android.server.display.aiautobrt.{IndividualBrightnessEngine, IndividualEventNormalizer, AppClassifier, CustomBrightnessModeController, CustomPersistentDataStore, CbmStateTracker, UserBrightnessStatsJob}`, `com.android.server.display.statistics.BrightnessDataProcessor`, `com.android.server.display.{DisplayPowerControllerImpl, AutomaticBrightnessControllerImpl}` |
| `system/framework/services.jar` (`classes.dex`) | AOSP `com.android.server.display.AutomaticBrightnessController` (the live brightness loop) |

Extraction commands are in `evidence/framework_paths.txt`; decompiled classes are in
`sources/framework/classes/`. Full tables per pipeline stage are in **`PIPELINE.md`**;
this section summarises what changed relative to §2.

### 13.1 Event fields are raw physical units, not 0..1

`BrightnessDataProcessor.createModelEvent(float lux, int categoryId, float newBrightness,
int orientation, int sceneState)` (`miui-services.dex`, line 502) fills the event as:

```
currentBrightness      = DisplayDeviceConfig.getNitsFromBacklight(newBrightness)   // nits
currentBrightnessSpan  = getBrightnessSpanByNit(newBrightness)                     // bucket 0..44
ambientLux             = lux                                                       // physical lux
ambientLuxSpan         = getAmbientLuxSpanIndex(lux)                                // bucket 0..44
defaultConfigBrightness= getConfigBrightness(lux, sceneState)                       // nits (stock/config spline)
appCategoryId          = AppClassifier.getAppCategoryId(packageName)                 // 0..9
orientation            = display rotation
mixedOrientationApp    = IndividualEventNormalizer.getMixedOrientApp(cat, rotation)  // {0,1,2}
timestamp              = System.currentTimeMillis()
```

So `ln(ambientLuxSpan + 1)` is the log of a **quantised bucket index** (0…44), not of
a lux delta, and the model output is in **nits** (`onPredictFinished` converts with
`DisplayDeviceConfig.getBrightnessFromNit`). `ambientLux` itself is not a raw sensor
sample: it is `AutomaticBrightnessController.mAmbientLux`, a duration-weighted average
of the light-sensor ring buffer over `mAmbientLightHorizonLong`
(`calculateAmbientLux`, `services.jar`), refreshed on brightness changes.

`IndividualEventNormalizer` does provide `normalizeLux/normalizeNit/normalizeAppId`
(0..1 mapping against `hbmMinLux-1`), but **nothing on the model path calls them** —
only `getMixedOrientApp` is used. `IndividualModelEvent.isValidEvent()` (the 0..1
sanity check) is likewise unused; the path uses `isValidRawEvent()` (NaN-only).
This supersedes the "units unknown" note for these fields in §7/§11.

Cadence: event-driven, one DB row per settled brightness change (types 1/2 only),
debounced by `debounceBrightnessEvent(3000)` in `BrightnessDataProcessor`; there is no
periodic sampler and no app-side timer. Sensor registration uses
`registerListener(listener, sensor, 3, handler)` (literal 3 µs, i.e. max rate) while the
AOSP controller re-registers the same sensor at `lightSensorRate * 1000` µs, with
`lightSensorRate` a device-config value in ms.

### 13.2 AIDL transaction table (resolved)

`IIndividualBrightnessService.Stub` (miui-framework.jar, `:51-55`):

| code | method | parcel | mode |
|---:|---|---|---|
| 1 | `onEventChanged(IndividualModelEvent)` | `writeTypedObject(event, 0)` | oneway |
| 2 | `predictBrightness(IndividualModelEvent)` | `writeTypedObject(event, 0)` | oneway |
| 3 | `attach(IIndividualCallback)` | `writeStrongInterface` | oneway |
| 4 | `detach()` | — | oneway |
| 5 | `provideInterfaceVersion(int)` | `writeInt` | oneway |

`IIndividualCallback.Stub`: 1 `onTrainFinished`, 2
`onPredictFinished(lux, appId, brightness)`, 3 `onUpdateExperiment`, 4
`onTrainIndicatorsFinished(IndividualTrainEvent)`, 5 `onAbTestExperimentUpdated` —
all oneway as well.

Binding (`IndividualBrightnessEngine`): action
`com.miui.aiautobrt.service.AiService`, `bindServiceAsUser(..., 67108865, currentUser)`
(`BIND_AUTO_CREATE | BIND_FOREGROUND_SERVICE`, flag-name decomposition *inferred*), then `attach(cb)` +
`provideInterfaceVersion(2)`, with `linkToDeath`/`binderDied` teardown. Threading:
oneway calls from the system_server display thread; the APK runs inference inline on
the Binder thread (no queue, no worker thread); results come back on a system_server
binder thread and are re-posted to `mBgHandler`. No runtime permission check exists on
either side; the caller is `system_server` and the service is exported with the action
above (no `android:permission` attribute observed — *inferred*, AXML not decoded).

**Important**: `predictBrightness` is *not* called for live brightness. It is called
only by `CustomBrightnessModeController.uploadValidatedEventToModel()`, which sweeps
`mDefaultLuxLevels × {category 0,1,2}` at 200 ms intervals after a training run.

### 13.3 Where the predicted brightness ends up

```
sweep predictBrightness (3 categories × lux grid)      → nits per point
  → smoothCurve(): clamp to [minBrightness, normalMaxBrightness]; per point
    [prev, prev*((lux+0.25)/(prevLux+0.25))] and ≥ prev+0.004; points above
    hbmMinLux-1 replaced by the stock nits
  → isMonotonic / ≥ minimum spline / minimum-slope test (100/(hbmMinLux-1));
    the minimum-slope branch returns success *without* building that category's
    spline, which keeps isIndividualAllowed() false for it
  → Spline.createSpline(mDefaultLuxLevels, nits)  per category 0/1/2
  → CustomPersistentDataStore.storeIndividualSpline → /data/system/displayconfig/
    custom_brightness_backup.xml
```

Live path (no binder, spline interpolation only):

```
AutomaticBrightnessController.updateAutoBrightness
  → CustomBrightnessModeController.getCustomBrightness(lux, pkg, category, …)
      isIndividualAllowed() ? getIndividualBrightness(pkg, lux) : custom curve : stock
  → spline.interpolate(lux) [nits] → getBrightnessFromNit → clampScreenBrightness
  → mScreenAutoBrightness → mCallbacks.updateBrightness() → framework applies backlight
```

Consequences: (a) the model influences brightness only through a **per-category
lux→backlight curve** rebuilt after each successful training; (b) a reboot needs
neither the APK process nor the TFLite model — the curve is restored from
`/data/system/displayconfig/custom_brightness_backup.xml` if monotonic; (c) the
per-user selection is `Settings.Secure custom_brightness_mode` read with
`getIntForUser(…, -2)` (0 off, 1 custom curve, 2 individual), while the XML is global;
(d) any manual brightness tweak invalidates the model with reason `user_operate`
until the next successful training.

Failure/fallback chain: model missing → `g3.a` logs and `c=false` → service returns
`event.getCurrentBrightness()`; run error → `-1.0f` sentinel → same fallback;
framework sees NaN/negative → drops the point; validation 200 ms timeout or
non-monotonic curve → model not promoted; no spline → custom/stock curve.

## 14. Training job plumbing (framework ↔ app)

* Job `93197` is scheduled from `IndividualModelService.onCreate` only, with
  `setRequiresDeviceIdle(true)` and `setPeriodic(24 h)`; no network/charging
  constraints, not persisted. An existing identical pending job is reused.
* `TrainJobService.onStartJob` spawns `new Thread(i3.a).start()`; `onStopJob` returns
  `false` (no retry). The runnable ends with `jobFinished(params, false)`.
* Data: `SELECT * FROM brightness_model_event LIMIT -1 OFFSET <offset>` (no `ORDER BY`).
  Train iff `new ≥ 10 000` rows, or `force_train_enable == 1` and `new ≥ 1 000`.
* Shuffle → first 90 % train, last 10 % validation; ≤ 5 epochs of 128-sample batches
  (tail batch re-uses the last 128), one Adam step per batch through signature `train`.
* After training: `newMAPE < oldMAPE` → `save_for_replace` + `restore` (the student
  overwrites both key sets, so `old_model_infer` == trained net immediately);
  4-bucket check against the stock curve (`<8, 8-50, 50-200, ≥200`) decides whether
  `onTrainFinished` is sent; on acceptance the app clears the table and resets
  `sqlite_sequence`.
* Activation is **two-stage**: weights swap in-process at `restore`; the user-visible
  curve changes only when the framework's monotonicity sweep succeeds
  (`setModelValid(true, "train_finished")`). No version number exists beyond AIDL
  interface version 2 and Room schema 1; freshness is the in-memory row offset.

Open items that remain `unknown`: exact `mAmbientLightHorizonLong/Short` and light
sensor rate values (framework-res integers), the lux grid in
`config_autoKeyboardBacklightDecreaseLuxThreshold`, whether OEM power policy honours
the 24 h idle job, and the AXML `android:permission` attribute of the service.
