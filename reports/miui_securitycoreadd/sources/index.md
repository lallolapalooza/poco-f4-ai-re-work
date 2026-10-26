# Decompiled sources (jadx `--single-class`, targeted)

APK: `SecurityCoreAdd.apk` (com.miui.securitycore, 1.4.2-15-240924).
All files were produced with `tools/decompile_classes.py` (one jadx invocation per
class, `--show-bad-code --no-res`). jadx renames obfuscated members; the original
names survive in `/* JADX INFO: renamed from: ... */` comments and are quoted in
`report.md` when it matters.

## `classes/` — the feature itself (first batch, regex `aiautobrt|Brightness|tensorflow`)

| File | Role |
|---|---|
| `com.miui.aiautobrt.service.IndividualModelService.java` | Android `Service`, action `com.miui.aiautobrt.service.AiService`; returns `IIndividualBrightnessService.Stub` on bind; schedules `TrainJobService` (job 93197, 24 h, idle). |
| `com.miui.aiautobrt.service.IndividualModelService$a.java` | AIDL stub body: `attach/detach/provideInterfaceVersion/onEventChanged/predictBrightness` (identical listing to the outer class because jadx inlined it). |
| `com.miui.aiautobrt.service.TrainJobService.java` | `JobService`; `onStartJob` spawns `i3.a` (the training runnable). |
| `com.miui.aiautobrt.database.BrightnessModelDatabase.java` | Room database singleton (`BrightnessModelDatabase.p(ctx)`). |
| `com.miui.aiautobrt.database.BrightnessModelDatabase_Impl.java` | Generated Room impl: table `brightness_model_event`, 8 columns. |
| `com.miui.aiautobrt.database.BrightnessModelDatabase_Impl$a.java` | Room schema validation for the same table. |
| `org.tensorflow.lite.a.java` | Obfuscated `Interpreter` subclass exposing `runSignature(inputs, outputs, key)`. |
| `org.tensorflow.lite.b.java` / `NativeInterpreterWrapper*.java` / `TensorImpl.java` | Stock TFLite Java API (14-class slice needed to read the signature API). |
| `org.tensorflow.lite.flex.FlexDelegate.java` | Loads `libtensorflowlite_flex_jni.so`; auto-attached by `NativeInterpreterWrapper` when `hasUnresolvedFlexOp`. |

## `controller/` — inference / controller layer (regex `^f3\.|^g3\.|^h3\.|^t9\.`)

| File | Role |
|---|---|
| `f3.c.java` | `MainController`: builds the 7-field event record (`h3.a`) from `IndividualModelEvent`, stores it, calls the model controller. |
| `f3.d.java` | `ModelController`: mmaps `assets/aiautobrt.tflite`, constructs `g3.a` with `filesDir/checkpoint`; holds train/validation lists. |
| `g3.a.java` | `BrightnessLiteModel`: `a(signature, h3.a)` = predict; `e()` = restore from checkpoint. |
| `h3.a.java`, `h3.a$a.java` | `BrightnessModelEvent` P/arcelable: 8 fields (id + 7 floats). |
| `h3.b.java`, `h3.c.java` (+ inner) | Room DAO interface and implementation for `brightness_model_event`. |
| `f3.b.java`, `f3.b$a.java`, `f3.a.java` | AB-test polling handler (2 h) — not part of the model path. |
| `f3.e.java`, `f3.f.java` | `userdebug`-only `FileObserver` test harness that replays `filesDir/userdata.csv`. |
| `t9.a.java`, `t9.b.java` | log-level enum/helpers. |

## `train/`

| File | Role |
|---|---|
| `i3.a.java` | The `Runnable` executed by `TrainJobService`: data selection, 90/10 split, 5 epochs × batch 128, validation MAPE, `save_for_replace` + `restore`, bucket check, callbacks. |

## `threshold/`, `support/`

| File | Role |
|---|---|
| `j8.m.java` | Brightness bucket thresholds `{8.0f, 50.0f, 200.0f}` used by the post-training check. |
| `gb.c.java` | Logging helper; `g(Context)` is the Room builder for DB name `AiBrightnessModel.db`. |

## Not present in this APK

`com.xiaomi.aiautobrt.IIndividualBrightnessService`, `IIndividualCallback`,
`IndividualModelEvent`, `IndividualTrainEvent`, `InterfaceVersionHelper` are
*referenced* (dex `type_id` only) and live in the Xiaomi framework, not here.
They were recovered afterwards from the device image — see the `framework/`
section below for the decompiled AIDL and the server-side engine.

## `framework/` — the system-side half (from the device image, not the APK)

Inputs: `miui-framework.dex` (from `system_ext/framework/miui-framework.jar`),
`miui-services.dex` (from `system_ext/framework/miui-services.jar`),
`services/classes{1,2,3}.dex` (from `system/framework/services.jar`).
Same jadx invocation style (`--single-class`, `--show-bad-code --no-res`) directly
on the dex file. Extraction transcript: `evidence/framework_paths.txt`.

| File | Role |
|---|---|
| `IIndividualBrightnessService.java` | AIDL: transactions 1 `onEventChanged`, 2 `predictBrightness`, 3 `attach`, 4 `detach`, 5 `provideInterfaceVersion`; all `oneway` (Proxy uses FLAG_ONEWAY). |
| `com.xiaomi.aiautobrt.IIndividualCallback.java` | Callback AIDL: 1 `onTrainFinished`, 2 `onPredictFinished(lux, appId, brt)`, 3 `onUpdateExperiment`, 4 `onTrainIndicatorsFinished`, 5 `onAbTestExperimentUpdated`. |
| `com.xiaomi.aiautobrt.IndividualModelEvent.java` | Parcelable payload (8 floats + long) with the field order used on the wire; `isValidEvent` (0..1) and `isValidRawEvent` (NaN-only). |
| `com.xiaomi.aiautobrt.IndividualTrainEvent.java` | `trainSampleNum`, `modelTrainLoss`, `curModelMAPE`, `preModelMAPE` (+ MAE fields). |
| `com.xiaomi.aiautobrt.InterfaceVersionHelper.java` | Per-method interface versions; `RELEASED_INTERFACE_VERSION = 2`. |
| `BrightnessDataProcessor.java` (`com.android.server.display.statistics`) | Builds every `IndividualModelEvent` (`createModelEvent`, bucketisation, config spline), light/accel/FOV sensor plumbing, 3 s report debounce. |
| `com.android.server.display.aiautobrt.IndividualBrightnessEngine.java` | Binder client: `bindServiceAsUser`, `attach`/`provideInterfaceVersion(2)`, `onEventChanged` upload, `preparePredictBrightness`, model-valid state. |
| `com.android.server.display.aiautobrt.CustomBrightnessModeController.java` | Orchestrator: live `getCustomBrightness`, post-training sweep, `smoothCurve`, monotonicity/min-slope checks, per-category splines, 200 ms timeout. |
| `com.android.server.display.aiautobrt.CustomPersistentDataStore.java` | `/data/system/displayconfig/custom_brightness_backup.xml` (enabled flags + 3 splines). |
| `com.android.server.display.aiautobrt.IndividualEventNormalizer.java` | normalisation helpers (unused on the model path) + `getMixedOrientApp`. |
| `com.android.server.display.aiautobrt.AppClassifier.java` | package → category 0..9 from `displayconfig/app_brightness_category.xml` / cloud file. |
| `com.android.server.display.aiautobrt.CbmStateTracker.java`, `UserBrightnessStatsJob.java` | usage statistics, "best indicator" model selection, 24 h idle stats job. |
| `DisplayPowerControllerImpl.java`, `AutomaticBrightnessControllerImpl.java` | MIUI display-power layer that owns the CBM controller. |
| `AutomaticBrightnessController.java` (from `services/classes.dex`) | AOSP auto-brightness loop: calls `getCustomBrightness`, clamps, sets `mScreenAutoBrightness`. |
| `MiuiMultiWindowRecommendController.java` | source of the `0.0f` constant jadx prints for many literals. |
