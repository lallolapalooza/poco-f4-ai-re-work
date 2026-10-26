# miui_securitycoreadd — data pipelines

Two pipelines use the same TFLite flatbuffer:

* **P1 (inference / serving)** — `system_server` display stack builds a
  `IndividualModelEvent`, `com.miui.securitycore` turns it into 3 floats, runs
  `old_model_infer`, and returns a brightness in nits. The framework uses that
  number **only to derive a per-app-category lux→nits spline after training**; the
  live brightness path interpolates that spline (see §P1.6).
* **P2 (training)** — the same APK's `TrainJobService` reads its Room table,
  trains `dnn_model_1` with the flatbuffer's own TF1 graph, and hot-swaps the
  checkpoint when it beats the teacher on held-out data.

Evidence root: `sources/framework/` (framework dex + jadx `--single-class` output),
`sources/controller|train|classes/` (APK dex), `ir/`, `reimpl/`.
Framework artifacts: `system_ext/framework/miui-framework.jar` (AIDL types),
`system_ext/framework/miui-services.jar` (`com.android.server.display.aiautobrt.*`),
`system/framework/services.jar` (`AutomaticBrightnessController`).

Notation: **[C]** confirmed, **[I]** inferred, **[U]** unknown.

---

# P1 — inference / serving

## P1.1 Data sources and triggers

| Item | Value | Confidence | Evidence |
|---|---|---|---|
| Event producer | `BrightnessDataProcessor.notifyBrightnessEventIfNeeded(...)` called from the display power pipeline on every brightness change | C | `BrightnessDataProcessor.java:339` (miui-services.dex) |
| Model-event callback | `readyToReportEvent()` → `reportIndividualModelEvent(item)` → `ModelEventCallback.onBrightnessModelEvent` | C | `BrightnessDataProcessor.java:449-457`, method at `:483` |
| Trigger filter | only event types 1 (auto-brightness) and 2 (auto + user-initiated); lux must not be NaN | C | `BrightnessDataProcessor.java:484`, `getBrightnessType` at `:702` |
| Report debounce | `debounceBrightnessEvent(3000)` — collapses bursts to one report 3 s after the last change; 0 ms when `mForcedReportTrainDataEnabled` | C | `BrightnessDataProcessor.java:393-399`, debounce armed in `handleBrightnessChangeEvent`, const `DEBOUNCE_REPORT_BRIGHTNESS=3000` at `:74` |
| Lux sensor | `Sensor.TYPE_LIGHT` (`mLightSensor = getDefaultSensor(5)`), plus `TYPE_ACCELEROMETER` and vendor `SENSOR_TYPE_LIGHT_FOV=33172111` | C | `BrightnessDataProcessor.java:303-305,1580-1604` |
| Sensor period | `registerListener(listener, mLightSensor, 3, mBackgroundHandler)` — literal `3` µs, i.e. effectively the sensor's minimum period; the AOSP controller registers the *same* sensor at `lightSensorRate * 1000` µs where `lightSensorRate` is a device-config value in **ms** | C for the literal, I for the unit reading | `BrightnessDataProcessor.java:2054-2062`; `AutomaticBrightnessController.java:606-613` |
| Lux value used | `AutomaticBrightnessController.mAmbientLux` = duration-weighted average of the light ring buffer over the *long* horizon (`calculateAmbientLux(now, mAmbientLightHorizonLong)`, buffer pruned at the same horizon) | C | `AutomaticBrightnessController.java:591-601,643-690` |
| Cadence (net) | event-driven, not periodic: one record per settled brightness change, ≥3 s apart; nothing is sampled on a timer | C | `:393-399`, `:449-457` |
| Lux window | `mAmbientLightHorizonLong` / `mAmbientLightHorizonShort` are framework-res config integers, not readable from the dex | U | `AutomaticBrightnessController.java:233-243` (values passed in by `DisplayPowerController`); value not recovered from `framework-res.apk` (binary resource) |
| Teardown | `registerLightSensor(false)` / `unregisterListener` when brightness type changes or reporting is disabled | C | `BrightnessDataProcessor.java:2054-2072` |

The APK itself never samples a sensor and has no timer: every `onEventChanged` /
`predictBrightness` call comes from `system_server`.

## P1.2 Transport and format

| Item | Value | Confidence | Evidence |
|---|---|---|---|
| Binder interface | `com.xiaomi.aiautobrt.IIndividualBrightnessService`, descriptor `com.xiaomi.aiautobrt.IIndividualBrightnessService` | C | `sources/framework/classes/IIndividualBrightnessService.java:11,51-55` |
| Transactions | 1 `onEventChanged`, 2 `predictBrightness`, 3 `attach`, 4 `detach`, 5 `provideInterfaceVersion` | C | same file, `Stub.onTransact` `:99-133` |
| Call mode | **all five are `oneway`** — `Proxy` passes `transact(code, data, null, 1)` (FLAG_ONEWAY); no reply parcel | C | same file, `Proxy` methods (`transact(..., 1)`) |
| Payload | `IndividualModelEvent` (`Parcelable`, `writeTypedObject(event, 0)`): 8 floats + 1 long, order `curBrt, curBrtSpan, lux, luxSpan, defaultConfigBrt, appCategoryId, orientation, mixedOrientationApp, timeStamp` | C | `com.xiaomi.aiautobrt.IndividualModelEvent.java:36-59,202-213` |
| Service binding | `Intent(action="com.miui.aiautobrt.service.AiService")`, `setComponent(mModelComponent)`, `bindServiceAsUser(intent, record, 67108865, UserHandle(ActivityManager.getCurrentUser()))` — flag value = `BIND_AUTO_CREATE(1) | BIND_FOREGROUND_SERVICE(0x04000000)` | C for the literal flag value, I for the flag-name decomposition | `IndividualBrightnessEngine.java:104-116` |
| Post-connect handshake | `attach(callback)` then `provideInterfaceVersion(2)`; `linkToDeath` on the binder | C | `IndividualBrightnessEngine.java:122-133` |
| Death handling | `binderDied()` → `detach()` (unlink, `unbindService`, null the proxy) | C | `IndividualBrightnessEngine.java:266-273` (`binderDied`), `:136-150` (`detach`) |
| Version gate | app rejects everything while `f3605e < 2`; framework always sends 2; `InterfaceVersionHelper.RELEASED_INTERFACE_VERSION = 2` | C | `IndividualModelService$a.java:60-96`; `InterfaceVersionHelper.java:7-19` |
| Permissions | service is `exported=true` with action `com.miui.aiautobrt.service.AiService`; no runtime permission check in either side; caller is `system_server` | C for no check in code; I for "no `android:permission` guard" (AXML attribute not readable with the available tooling) | `IndividualModelService.java` (no permission code); inventory permissions list has no BIND_* guard |
| Threading — caller | system_server display thread issues the oneway transact; returns immediately | C | `IndividualBrightnessEngine.preparePredictBrightness:89` + oneway AIDL |
| Threading — callee | app executes `predictBrightness` synchronously on the Binder thread that delivers it (mmapped `Interpreter`, no queue, no worker); one run per transaction | C | `IndividualModelService$a.predictBrightness`; `g3.a.a` |
| Threading — reply | `IIndividualCallback.onPredictFinished` (transaction 2, also oneway) arrives on a system_server binder thread and is re-posted to `mBgHandler` (validation) | C | `IIndividualCallback.java:51-55`; `IndividualBrightnessEngine.java:177-190` |

## P1.3 Preprocessing

All in `f3.c.a(IndividualModelEvent)` (`sources/controller/f3.c.java:64-73`):

```
aVar.c      = ln(getAmbientLux() + 1)            → x0
aVar.f4046d = ln(getAmbientLuxSpan() + 1)        → x1
aVar.f4048f = ln(getAppCategoryId() + 1)         → x2
aVar.f4047e = ln(getMixedOrientationApp() + 1)   (stored, NOT fed to the model)
aVar.f4049g = getCurrentBrightness()
aVar.f4050h = getCurrentBrightnessSpan()
aVar.f4051i = getDefaultConfigBrightness()
```

`g3.a.a(String sig, h3.a ev)` (`sources/controller/g3.a.java:50-70`) builds
`float[1][3]` from exactly `c, f4046d, f4048f`:

```
{{ ln(ambientLux+1), ln(ambientLuxSpan+1), ln(appCategoryId+1) }}
```

Field semantics and units (all set in
`BrightnessDataProcessor.createModelEvent(...)`, `BrightnessDataProcessor.java:493-511`):

| Field | Source expression | Unit / range | Confidence |
|---|---|---|---|
| `ambientLux` | `item.ambientLux` = `mAmbientLux` of the AOSP controller (weighted sensor average) | lux, physical (0 … ~10^5; HBM edge `hbmMinLux-1`) | C |
| `ambientLuxSpan` | `getAmbientLuxSpanIndex(lux)` (`:722-746`) | **integer bucket 0…44**, not a lux delta: `lux/5` (<30), `lux/100+6` (<900), `(lux-900)/200+15` (<3500), `(lux-3500)/500+28` (<10 000), then 41 / 42 / 43 / 44 for <15 k / <35 k / <65 k / <100 k, 44 above | C |
| `appCategoryId` | `AppClassifier.getAppCategoryId(packageName)` (`:105`) | int 0…9: 0 undefined, 1 game, 2 video, 3 music/read, 4 finance/learn, 5 news, 6 shopping, 7 photo, 8 travel, 9 social (`AppClassifier.java:33-43`) | C |
| `currentBrightness` | `getNitsFromBacklight(originalBrightness)` | **nits** | C |
| `currentBrightnessSpan` | `getBrightnessSpanByNit(originalBrightness)` (`:749-780`) | bucket index 0…44 (nit breakpoints 3.5 / 8 / 50 / 80 / 500 / 1000, steps 7 / 10 / 20 / 50) | C |
| `defaultConfigBrightness` | `getConfigBrightness(lux, sceneState)` (`:515-534`) | nits from the stock/config spline for the current scene; `NaN` if the spline is missing | C |
| `orientation` | display rotation | int | C |
| `mixedOrientationApp` | `IndividualEventNormalizer.getMixedOrientApp(cat, orientation)` | {0,1,2}: 1 for game/video in landscape (rot 1/3), 2 for social in portrait, else 0 | C |
| `timeStamp` | `System.currentTimeMillis()` | ms wall clock | C |

`IndividualEventNormalizer` (`sources/framework/classes/com.android.server.display.aiautobrt.IndividualEventNormalizer.java`)
also exposes `normalizeLux/normalizeNit/normalizeAppId` (min→max → 0..1) but **no
model-path code calls them**; only `getMixedOrientApp` is used (`:72-82`). The
`isValidEvent()` 0..1 sanity check in `IndividualModelEvent` is also unused here —
the path uses `isValidRawEvent()` (NaN-only). So the model consumes **raw physical
units**, not the normalized forms. [C]

No mean/scale, no quantization: the flatbuffer's `x` tensor is float32
`[1,3]`, `quantization.scheme = "none"` (`ir/tensor_table.json`).

## P1.4 Model invocation

| Item | Value | Confidence | Evidence |
|---|---|---|---|
| Runtime | TFLite `Interpreter` (obfuscated `org.tensorflow.lite.a`) constructed from an mmapped asset; Flex delegate auto-loaded only if unresolved Flex ops exist (subgraphs 0/1 have none) | C | `f3.d` ctor (`sources/controller/f3.d.java:36-60`), `NativeInterpreterWrapper` |
| Model | `assets/aiautobrt.tflite`, 197 836 B, sha256 `f6d75f14…35146d3` | C | `evidence/hashes.txt` |
| Signature | `"old_model_infer"` — the only signature the service path calls | C | `IndividualModelService$a.predictBrightness` |
| Inputs / outputs | `{"x": float[1][3]}` → `{"pre": FloatBuffer(1)}` | C | `g3.a.a`, `ir/aiautobrt.ir.json` |
| Session | one interpreter per process, created in the `f3.c` singleton ctor via `f3.d`; no per-call session, no batching, default threads | C | `f3.c.c(Context)` |
| Weight state | subgraph 6 (`CALL_ONCE`) assigns initial constants; if `filesDir/checkpoint` exists at construction, `restore` overwrites them | C | `f3.d:52-60`, `g3.a` ctor |
| Cadence | only when the framework asks (validation sweep, 200 ms pacing) — not per frame, not per event | C | `CustomBrightnessModeController.uploadValidatedEventToModel:562-573` |

## P1.5 Output postprocessing

* Raw `pre[0]` is returned unchanged — no clamp, no scale, no smoothing in the app
  (`g3.a.a` returns `floatBuffer.get(0)`). Units = **nits**, because it is compared
  against the training label `currentBrightness` (also nits) and converted with
  `getBrightnessFromNit` by the consumer. [C]
* `-1.0f` is the app's error sentinel (`catch IllegalArgumentException` in
  `g3.a.a`); the stub then falls back to `event.getCurrentBrightness()` before
  calling back. [C] (`IndividualModelService$a.predictBrightness`)
* Callback: `IIndividualCallback.onPredictFinished(ambientLux, appCategoryId,
  brightness)` — the first two are echoed from the request. [C]
* Framework side (`CustomBrightnessModeController.onPredictFinished:463-474`):
  `NaN` or `< 0.0f` → log + drop; else
  `dbv = DisplayDeviceConfig.getBrightnessFromNit(brightness)` (nits → 0..1
  backlight), stats `noteIndividualResult(lux, appId, dbv)`, cancel prediction
  timeout, `updateAutoBrightness(dbv)`.
* **Two-stage postprocessing**: the single prediction is not the final curve. The
  framework sweeps `mDefaultLuxLevels × {category 0,1,2}` (a `predictBrightness`
  per point, 200 ms apart), stores the nits arrays in
  `mBrightnessValidationMapper`, then `isMonotonicModel()` (`:575-604`):
  1. `smoothCurve` (`:606-637`): first point clamped to
     `[mMinBrightness, mNormalMaxBrightness]`; each later point constrained to
     `[prev, min(prev * ((lux+0.25)/(prevLux+0.25)), prev + 0.004,
     mNormalMaxBrightness)]` — i.e. max slope ratio 1.0, min step 0.004 backlight;
     points above `hbmMinLux-1` are replaced by `mDefaultNitsLevels[i]`.
  2. `isMonotonic` → reject; `isBelowMinimumSpline` (vs `mMinimumBrightnessSpline`
     from resources) → reject; `isBelowMinimumSlope` (min slope ≤
     `100/(hbmMinLux-1)`) → **return `true` immediately**, i.e. validation passes
     but that category's spline is neither built nor persisted.
  3. Otherwise the smoothed array is posted as
     `lambda$isMonotonicModel$5(category, nits)` (builds
     `mIndividualDefaultSpline` = 0, `mIndividualGameSpline` = 1,
     `mIndividualVideoSpline` = 2) and persisted
     (`mDataStore.storeIndividualSpline(key, newArray)`). Because
     `isIndividualAllowed()` requires all three splines non-null, a
     minimum-slope early return leaves the individual curve unusable until a later
     sweep builds it.

## P1.6 Consumer / action — who applies the brightness

Live path (every auto-brightness update, **no binder involved**):

```
AutomaticBrightnessController.updateAutoBrightness(sendUpdate, isManuallySet)
  value = mAutomaticBrightnessControllerImpl.getCustomBrightness(
              mAmbientLux, mForegroundAppPackageName, mForegroundAppCategory,
              mScreenAutoBrightness,
              mCurrentBrightnessMapper.getBrightness(lux, pkg, cat), isManuallySet)
  newScreenAutoBrightness = clampScreenBrightness(value)
  … hysteresis (brighten/darken thresholds) …
  mScreenAutoBrightness = newScreenAutoBrightness; mCallbacks.updateBrightness()
```
`AutomaticBrightnessController.java:830-861` (services.jar).

```
CustomBrightnessModeController.getCustomBrightness(lux, pkg, cat, old, newAuto, orientation, manual)
  if (isIndividualAllowed())      → getIndividualBrightness(pkg, lux), state = 2
  else if (isCustomAllowed())     → mBrightnessMapper.getBrightness(...), state = 1
  else                            → newAuto (stock/config curve), state = 0
```
`CustomBrightnessModeController.java:250-280`; `isIndividualAllowed():303-305` =
support flag ∧ component present ∧ not user-disabled ∧ `isModelValid()` ∧ not
cloud-disabled ∧ experiment enabled ∧ all three splines non-null.

`getIndividualBrightness(pkg, lux)` (`:656-669`): category → spline
(1 game / 2 video / else default) → `spline.interpolate(lux)` **nits** →
`getBrightnessFromNit` → backlight 0..1.

Clamping chain: spline output clamped by construction (`smoothCurve`) and then by
`clampScreenBrightness` = `constrain(value, min(hbmMin, throttlerCap),
min(hbmMax, throttlerCap))` (`AutomaticBrightnessController.java:863-867`).
The final `updateBrightness()` reaches `DisplayPowerController` →
`DisplayPowerState`/backlight; brightness is applied by the framework, the APK only
supplies the number. [C for the chain up to `mCallbacks.updateBrightness()`; I for
the last hop into `DisplayPowerState` — not decompiled.]

Model validity state machine (`setModelValid`, reason strings in
`IndividualBrightnessEngine.java:282-309`): `default`, `forced_operate`,
`train_finished`, `user_operate`, `best_indicator`, `backup`.
A manual brightness adjustment by the user calls
`disableIndividualEngine(true)` and (if the model was valid) sets it invalid with
reason `user_operate` (`:402-404`); re-enable happens on the next successful
training (`lambda$onValidatedBrightness$4`).

## P1.7 Failure and fallback

| Case | Behaviour | Confidence |
|---|---|---|
| Asset missing / mmap fails | `g3.a` ctor logs `Lite Model file is null` / `Error loading lite model file`, `c = false`; `predictBrightness` then returns the event's own `currentBrightness` | C (`f3.d`, `g3.a:35-49`, stub) |
| `IllegalArgumentException` during run | `g3.a.a` returns `-1.0f` → stub substitutes `currentBrightness` | C |
| Framework interface version < 2 | app serves `getCurrentBrightness()` without inference | C |
| Service not bound yet | `preparePredictBrightness`/`uploadBrightnessModelEvent` call `tryToBindModelService()` and **drop** the event | C (`IndividualBrightnessEngine.java:72-96`) |
| Model returns NaN / negative | framework logs `Predict error` and keeps the previous curve | C (`:463-466`) |
| Validation timeout | 200 ms per point; if a point never arrives, `lambda$new$0` aborts validation (`Model cannot complete verification due to predict time out`), model NOT promoted | C (`:127-140`) |
| Non-monotonic / below-minimum curve | validation fails, model not promoted, log `non-monotonicity` | C |
| Prediction timeout inside `CbmHandler` (msg 1, 200 ms) | would fall back to the pending auto-brightness value and count `model_predict_timeout_count`; however `setPendingToWaitPredict` has **no caller** in the decompiled classes, so this is dead code in this build | C for the handler, I for the dead-code reading (`:681-686,779-785`) |
| No spline (first boot after model invalidation) | `getCustomBrightness` falls through to the custom-config curve or the stock mapper | C |

## P1.8 Confidence and open questions

| Claim | Confidence | Evidence | What would settle it |
|---|---|---|---|
| Event fields are raw lux / nits / bucket indices (not 0..1) | C | `createModelEvent`, `getAmbientLuxSpanIndex`, `getBrightnessSpanByNit` | — |
| Model output is nits | C | label `curBrt` = nits; consumer `getBrightnessFromNit` | — |
| Live brightness is served from a spline, not a per-event model call | C | `getIndividualBrightness`, `preparePredictBrightness` only called in `uploadValidatedEventToModel` | — |
| `setPendingToWaitPredict` / `mPendingIndividualBrightness` are dead in this build | I | only `updateAutoBrightness` reads `mPending...`; `setPendingToWaitPredict` has no caller in the decompiled classes | full smali xref of `miui-services.dex` |
| Sensor period literal `3` means µs (max rate) | I | Android `registerListener` signature `(listener, sensor, samplingPeriodUs, handler)` | device `dumpsys sensorservice` |
| Lux horizons / sensor rate config values | U | passed from `DisplayPowerController` ctor | `framework-res.apk` resource table |
| Service permission guard | I | no check in code, caller is system_server | AXML decode of the manifest |

---

# P2 — training

## P2.1 Data sources and triggers

| Item | Value | Confidence | Evidence |
|---|---|---|---|
| Job service | `com.miui.aiautobrt.service.TrainJobService` (not exported) | C | manifest/inventory, `TrainJobService.java` |
| Scheduling | `IndividualModelService.onCreate` → `JobScheduler.getPendingJob(93197)`; build `JobInfo.Builder(93197, TrainJobService).setRequiresDeviceIdle(true).setPeriodic(24 h)`; cancel+reschedule only if an existing pending job differs | C | `IndividualModelService.java:172-186` |
| Constraints | device-idle, periodic 24 h, **no** network/charging/battery constraints, not persisted (`setPersisted` absent) | C | same |
| Side effect | the job exists only after the service has been bound once (job scheduled in `onCreate`) | C | same |
| Execution | `onStartJob` → `new Thread(new i3.a(this, params)).start()`, returns `true`; `onStopJob` returns `false` (SDK ≥ 31 logs `getStopReason()`) | C | `TrainJobService.java:25-40` |
| Data | Room table `brightness_model_event` in `AiBrightnessModel.db` (v1), 8 columns | C | `BrightnessModelDatabase_Impl`, `h3.c` |
| Query | `SELECT * FROM brightness_model_event LIMIT -1 OFFSET ?` (no `ORDER BY`; insertion order assumed) | C | `h3.c.a(int)` |
| Sample count | `newEvents = d(f3604d)`, `all = d(0)`; in-memory offset `f3604d` = rows already consumed | C | `f3.c.d`, `i3.a.run` |
| Trigger | train iff `newEvents ≥ 10000`, or `Settings.Secure force_train_enable == 1` and `newEvents ≥ 1000`; on trigger `f3604d += newEvents.size()` | C | `i3.a.run` |
| Gate | `c.f3605e ≥ 2` (framework interface version) and model loaded (`dVar.c.c`) | C | `i3.a.run` |
| Retention | if `f3604d ≥ 50000`: newest `mId == 2147473647` → wipe table; else reset offset and delete oldest 10 000 rows until < 50 000 remain | C | `i3.a.run` tail |

## P2.2 Transport and format

No binder: the job runs in-process. Batch tensors are JVM arrays handed to the
signature runner:

* `x`: `float[128][3]` filled with `{c, f4046d, f4048f}` = `{ln(lux+1), ln(span+1), ln(cat+1)}` (same
  transform as P1, from the *stored* `h3.a` row, not recomputed from the event).
* `y`: `float[128][1]` = `cur_brt` (nits).
* Output `loss`: `FloatBuffer(1)`.
* `train_x`/`train_y` shape signatures are dynamic (`[-1,3]`, `[-1,1]`), so the 128
  is a Java-side choice, not a model constraint (`ir/tensor_table.json`).

When the next 128-sample window would run past the end (`i+128 ≥ size`), the loop
uses the **last 128 samples again** instead of a short tail, so an epoch issues
`ceil(N/128)` steps (at least 1) and the final window overlaps the previous one. [C]

## P2.3 Preprocessing

Same three `ln(x+1)` features, already materialised at **ingestion** time
(`f3.c.a`) and persisted in the DB — training does not re-derive them, so the DB
holds log-domain features while `cur_brt`/`curve_brt` stay in nits. [C]
No normalization, no shuffling of the 90 % slice beyond per-epoch
`Collections.shuffle(train)`. [C]

## P2.4 Model invocation

| Item | Value | Confidence |
|---|---|---|
| Signature | `"train"`, one Adam step per call | C |
| Inputs | `{"x": float[128][3], "y": float[128][1]}` → `{"loss": FloatBuffer(1)}` | C |
| Graph | 355 ops: teacher forward, student forward, `0.9*MAPE(y,new) + 0.1*MAPE(old,new)`, gradient tape, TF1 Adam (lr 0.001, β1 0.9, β2 0.999, ε 1e-7) | C (`ir/ops_sg5.txt`, `evidence/train_graph_key_ops.txt`) |
| Epochs | ≤ 5 outer iterations; abort early if an epoch produced 0 batches | C |
| Batch | 128 | C |
| Only student updated | `dnn_model_1/*` (8 tensors) + their Adam slots | C |
| Runtime | same in-process interpreter; requires the Flex delegate (`FlexSign`, `FlexBroadcastGradientArgs`, `FlexReluGrad`) | C |
| Thread | one plain thread started by `onStartJob`, no handler hop | C |

## P2.5 Output postprocessing

`i3.a.run()` after the epochs:

1. **Validation** (only if the 10 % split is non-empty), one sample at a time:
   `newMAPE = mean(|(cur − new)/cur|)`, `oldMAPE = mean(|(cur − old)/cur|)`
   (both via `new_model_infer` / `old_model_infer`). Log
   `Model train result newFinalMape = …, oldFinalMape = …`. [C]
2. **Promotion (app level)**: if `newMAPE < oldMAPE` → ensure
   `filesDir/checkpoint` exists (`createNewFile`) → `save_for_replace` →
   `restore`. `save_for_replace` writes all 16 keys from the 8 student variables,
   so the teacher is overwritten with the student *in the same process*. [C]
3. **Bucket check** (only if promoted): for each validation sample, accumulate
   `|(cur − defaultConfigBrt)/cur|` (stock curve, `fArr6`) and
   `|(cur − old_pred)/cur|` (label `fArr7`) into the four `cur_brt` buckets
   `<8`, `8–50`, `50–200`, `≥200` (`j8.m.f4504d = {8.0f, 50.0f, 200.0f}`).
   The second term calls `old_model_infer`, which after
   `save_for_replace`+`restore` **is the newly trained net**, so the check compares
   the new model against the stock curve. Accept iff the model's per-bucket sum is
   ≤ the default's in **all four** buckets, or
   `SystemProperties.getInt("debug.miui.display.dbg", 0) & 128`. [C]
4. **Accept** → `IIndividualCallback.onTrainFinished()` then `f3.c.b()`
   (offset = 0, `DELETE FROM brightness_model_event`,
   `UPDATE sqlite_sequence SET seq = 0`). Always:
   `onTrainIndicatorsFinished(IndividualTrainEvent{trainSampleNum,
   modelTrainLoss, curModelMAPE, preModelMAPE})` if the callback supports version 1. [C]
5. `jobFinished(params, false)` — never rescheduled by the runnable; the periodic
   job fires again in 24 h. [C]

## P2.6 Consumer / action — when the new model becomes active

Two distinct switches:

| Stage | Effect | Latency | Confidence |
|---|---|---|---|
| App-internal | `restore` replaces the variables in the live interpreter → every later `old_model_infer` (validation sweep and any future prediction) uses the trained net; no process restart, no file copy | immediate, in-process | C |
| Framework curve | `onTrainFinished` → `IndividualBrightnessEngine` posts to `mBgHandler` → `mEngineCallback.validateModelMonotonicity()` → sweep of `mDefaultLuxLevels × {0,1,2}` → `isMonotonicModel()` → `setModelValid(true, "train_finished")` → splines built and persisted → `isIndividualAllowed()` turns true → `getCustomBrightness` returns the model curve on the next brightness update | one sweep (~N_lux × 3 × 200 ms) after training | C |

Not a hot swap of the live brightness: while the sweep runs, the old curve keeps
serving; if validation fails, the model stays invalid and the old/custom curve is
used even though the app's weights already changed. [C]

The bucket check in step 3 governs whether the framework is *told* to start the
sweep at all (`onTrainFinished` is only sent when it passes).

## P2.7 Failure and fallback

| Case | Behaviour | Confidence |
|---|---|---|
| `newEvents` below threshold | nothing trained; `jobFinished(false)`; offset unchanged | C |
| Model not loaded | logs `Lite model is not available!`, skips training | C |
| `train` throws `IllegalArgumentException` | logs `Model train error!`, loss forced to 1.0, batch skipped (counted) | C |
| 0 batches in an epoch | loop breaks; still validates with whatever weights exist | C |
| Validation worse than teacher | no `save_for_replace`; no framework promotion; teacher untouched | C |
| `save_for_replace` / `restore` throws | `Model save error!`; checkpoint may be partially written; next process start would `restore` from it (TF writer semantics) | C for the catch, I for partial-file behaviour |
| No callback | `Can't get callback!`; DB is **not** cleared, offset not reset | C |
| Job killed by system | `onStopJob` returns `false` (no retry); training thread is not interrupted | C |

## P2.8 Persistence and versioning (both pipelines)

| Artifact | Path | Written by | Read by | Confidence |
|---|---|---|---|---|
| Model asset | `assets/aiautobrt.tflite` (read-only, mmapped) | build | `f3.d` ctor | C |
| TF checkpoint prefix | `<filesDir>/checkpoint` (`/data/user/<u>/com.miui.securitycore/files/checkpoint`) | `FlexSave` in `save_for_replace`; empty file created by `i3.a` | `restore` at `g3.a` ctor if the file exists | C for the path; I for the `.index` / `.data-00000-of-00001` siblings TF writes |
| Event history | `<filesDir>/../databases/AiBrightnessModel.db`, table `brightness_model_event` | `f3.c.f` (each `onEventChanged`) | `i3.a` (job) | C |
| Derived curve | `/data/system/displayconfig/custom_brightness_backup.xml` (`AtomicFile`, root `custom-config`) | `CustomPersistentDataStore.saveToXml` on `storeIndividualSpline`/`setModelValid` + `startWrite()` | `loadFromXml` in the controller ctor → `buildConfigurationFromXml` | C |
| User selection | `Settings.Secure custom_brightness_mode` (`getIntForUser(..., -2)`): 0 off, 1 custom curve, 2 individual model | settings UI / `setIndividualModelEnabledOnCommand` | `updateCustomBrightnessEnabled` | C |
| Cloud toggles | `MiuiDisplayCloudController` bits: `& 8` → reload app-category XML, `& 32` → disable individual model | cloud config | `onCloudUpdated` | C |
| Per-user model | service bound as `ActivityManager.getCurrentUser()`; DB + checkpoint live in the per-user app data dir, so each user trains their own net. The derived spline XML in `/data/system` is **global** (last writer wins) | — | — | C for bind/data dirs, I for the "global file shared across users" consequence |
| Version number | none. Only the AIDL interface version (2) and Room schema version (1); freshness is implicit in the `f3604d` offset | — | — | C |

## P2.9 Confidence and open questions

| Claim | Confidence | Evidence | What would settle it |
|---|---|---|---|
| 5 epochs × 128, 90/10 split, tail duplication | C | `i3.a.run` | — |
| Checkpoint has no version field | C | flatbuffer + Java surface | — |
| Model promotion is two-stage (weights now, curve after validation) | C | `onTrainFinished`→`validateModelMonotonicity`→`isMonotonicModel` | — |
| `setPeriodic(24 h)` survives OEM power policy | U | JobScheduler call is the only evidence | on-device `dumpsys jobscheduler` |
| Exact lux grid used by the validation sweep | U | `mDefaultLuxLevels` = `R.array.config_autoKeyboardBacklightDecreaseLuxThreshold` | `framework-res.apk` values |

---

# Reimplementation mapping

| Stage | Repro | Status |
|---|---|---|
| `ln(x+1)` features | trivial; `reimpl/model.py` takes the 3-vector directly | works on any `[lux, span_idx, category]` triple |
| `old_model_infer` / `new_model_infer` | `reimpl/model.py::apply/apply_old` on extracted weights | numerically verified (max rel 4.8e-7, `reimpl/verify.txt`) |
| one Adam step (loss, grads, update) | `reimpl/model.py::train_step`, `reimpl/reference_train.py` | cross-checked vs NumPy + finite differences; not runnable in `ai_edge_litert` (Flex ops) |
| restore / save_for_replace | not reimplemented (TF checkpoint container) | ARCHITECTURE_ONLY |
| framework spline construction (`smoothCurve`, monotonicity, minimum-slope) | not reimplemented; documented above | ARCHITECTURE_ONLY — pure Java, no model |
