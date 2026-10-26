# Decompiled sources (jadx `--single-class`, targeted)

APK: `system_ext/app/ModemTestBox/ModemTestBox.apk`
DEX: `classes.dex` only (1.73 MB), package `com.xiaomi.mtb`.

Primary files for the AI feature (weak-signal forecast):

| File | Role |
|---|---|
| `classes/com.xiaomi.mtb.aiservices.AiWeakSignalForecastService.java` | Service: 100 ms measurement sampling, median filtering, `[1,400,1]` input construction, TFLite run, 0.5 threshold, broadcast/IAiWeakSignal result |
| `classes/com.xiaomi.mtb.MtbTFLiteLoader.java` | Loads `assets/0.3_new_0410_less.tflite` via `AssetFileDescriptor` + `FileChannel.map`, wraps `org.tensorflow.lite.e` (Interpreter); exposes `getInputTensor`, `getOutputTensor`, `run` |
| `classes/com.xiaomi.mtb.MtbBroadcastReceiver.java` | Entry points: secret-code host `24744625` and `com.xiaomi.mtb.aisignal.GAME_STATUS` start/stop the service, only on QCOM (`OemHookAgent.mHookType == 2`) |
| `classes/com.xiaomi.mtb.aiservices.IAiWeakSignal*.java` | Binder interface `getForecastRes(String)` |
| `classes/com.xiaomi.mtb.aiservices.AiWeakSignalForecastService$infoItem.java` | Measurement record (RSRP, RSRQ, SINR, PCI-change flag) |
| `classes/com.xiaomi.mtb.MtbHydraDataManager*.java` | Modem stats plumbing; not part of the model path |
| `classes/org.tensorflow.lite.e.java`, `g.java`, `h.g.java` | Bundled/obfuscated TFLite runtime: `e` is compiled from `Interpreter.java`; `e.c`=`getInputTensor`, `e.d`=`getOutputTensor`, `e.e`=`run(input, output)` |
| `classes/com.xiaomi.mtb.activity.*.java` | UI / diagnostics; no model call sites |

Pipeline-patch additions (entry / measurement source / lifecycle):

| File | Role |
|---|---|
| `classes/com.xiaomi.modem.OemHookAgent.java` | **Measurement source**. `getHook()` returns null until `mMiRilHookReady`; `getLteMeasInfo(int)` / `getNr5gMeasInfo(int)` (lines 566, 810) reflectively call `MiRilHook` in `/system_ext/framework/mirilhook.jar`; readiness callback via `QcRilHookCbMethodProxy` (line 189); `mHookType` resolved from `MiRilHook.getHookType()` (line 291) |
| `classes/com.xiaomi.modem.ModemUtils.java` | Property constants: `PROP_SAR_LOG_SWITCH_OPEN = "true"`, `PROP_SAR_LOG_SWITCH_CLOSE = "false"` (lines 220–221) — resolves the cloud-control comparison in `initService` |
| `framework/com.xiaomi.mirilhook.MiRilHook.java` | From `system_ext/framework/mirilhook.jar` (**outside the APK**, included as context): `getLteMeasInfo` (line 1011) and `getNr5gMeasInfo` (line 1516) delegate to `HydraDataManager` modem-stats getters and return `null` when the Hydra manager is absent |

`evidence/manifest.xml` (decoded by `evidence/manifest_dump.py`) documents the
exported state that the pipeline depends on: the service is
`exported="true"` with no permission, the receiver is `exported="true"` and
declares the `SECRET_CODE` hosts, and the manifest filters `GAME_START` /
`GAME_EXIT` while the code tests `GAME_STATUS`.


All other `com.xiaomi.mtb` classes selected by the AI-keyword scan are included for completeness; 52 files in `classes/` (< 6 MB) plus one file in `framework/`.
Generated with `tools/decompile_classes.py` and direct `jadx --single-class` invocations, one class per invocation; jadx logs for the pipeline patch are in `logs/jadx_modem_classes.log`.

Note on duplicates: jadx prints `WARN - Input class is inner, parent class will be saved`, so
`$`-suffixed requests (`AiWeakSignalForecastService$infoItem`,
`IAiWeakSignal$Stub$Proxy`, `OemHookAgent$QcRilHookCbMethodProxy`, …) contain the
**outer** class body. Nested classes are read from the outer file; no content is
missing.

