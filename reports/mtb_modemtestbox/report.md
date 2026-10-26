# ModemTestBox (`com.xiaomi.mtb`) — AI subsystem report

## 1. Identity

| Field | Value |
|---|---|
| APK | `system_ext/app/ModemTestBox/ModemTestBox.apk` (`$APK_ROOT/...`) |
| Package | `com.xiaomi.mtb` |
| Label / version | `Rueban(MTB)V6.6.20240130`, versionName 14, versionCode 34 |
| min/target SDK | 34 / 34 |
| APK sha256 | `26b04faec0b6b01a9f08b373c4138121bd81d5bbb419a59ddcd3975266dac0c9` |
| Size | 2,348,152 B (2.3 MB) |
| DEX | one `classes.dex`, 1,730,304 B, 1,588 defined classes |
| Native libs | none (`inventory` native_libs=0, ABIs=[]) |
| Model | `assets/0.3_new_0410_less.tflite`, 112,544 B, sha256 `08e82b3c635d4ebd6608a299b2bba30dc3d7bacdc91ab3fe70ae96ff2a10b513` |

The only AI model in the APK is the TFLite file above. It is **not** an audio
model despite the app shipping voice-call diagnostics; every `audio`/`voice`
string belongs to unrelated UI/diagnostic resources. The sole model call site
is the weak-signal forecast service.

## 2. AI feature map

| Feature | Entrypoint | Model | Status |
|---|---|---|---|
| Weak-signal forecast (LTE/NR RSRP/RSRQ/SINR trend → WEAK/STRONG) | `MtbBroadcastReceiver` → `AiWeakSignalForecastService` | `0.3_new_0410_less.tflite` | confirmed |
| Modem diagnostics / AT / NV / EFS / SAR / MBN / cloud | activities, `MtbService`, `MtbHydraDataManager` | none | confirmed (non-AI) |

No other `.tflite`, `.dlc`, `.model`, `.bundle`, TFLite delegates, or AI native
libraries exist in the APK.

## 3. Call chain (confirmed)

```
MtbBroadcastReceiver.onReceive
  ├─ TelephonyIntents.SECRET_CODE_ACTION, host == "24744625" (HOST_CODE_MTB_AI_SIGNAL)
  │    -> if OemHookAgent.mHookType == 2 (QCOM): AiWeakSignalForecastService.onAiSignalBcHandle
  └─ action "com.xiaomi.mtb.aisignal.GAME_STATUS"
       -> if mHookType == 2: AiWeakSignalForecastService.onAiSignalGameStatusBcHandle
            (extra "gameStatus" == 0 stops the service)

AiWeakSignalForecastService.initService
  - reads system property persist.vendor.ai.signal.cloud.switch; if it equals
    ModemUtils.PROP_SAR_LOG_SWITCH_OPEN the service stops itself
  - input  = new float[1][400][1]        (line 201)
  - output = new float[1][2]
  - mTFLiteInterpreter = new MtbTFLiteLoader(ctx, "0.3_new_0410_less.tflite")  (line 204)
  - schedule getSignalInfo @ 100 ms, forecastRes @ 1000 ms  (lines 209–210)

getSignalInfo (every 100 ms)
  - getCurrentInfo(): TelephonyManager.getNetworkType()
       13 (LTE) -> OemHookAgent.getLteMeasInfo(slot)  -> updateLteMeasInfo(ByteBuffer)
       20 (NR)  -> OemHookAgent.getNr5gMeasInfo(slot) -> update5gMeasInfo(ByteBuffer)
  - validates ranges: RSRP [-150,-30] dBm, RSRQ [-40,10] dB, SINR [-20,40] dB
  - AddInfoDeque(infoItem(RSRP, RSRQ, SINR, PCI-changed?1:0))   (deque max 100)

forecastRes (every 1000 ms)
  - if deque.size() == 100:
        makeInputFromInfoItem()
        mTFLiteInterpreter.run(input, output)
        mForecastRes = output[0][1] < 0.5 ? "WEAK" : "STRONG"      (line 86)
  - if started from broadcast, send broadcast
        action com.xiaomi.mtb.aisignal.FORECAST_RES, extra "FORECAST_RES"
  - also exposed via binder IAiWeakSignal.getForecastRes(String)
```

`MtbTFLiteLoader` (sources/classes/com.xiaomi.mtb.MtbTFLiteLoader.java) maps the
asset with `AssetFileDescriptor` + `FileChannel.map(READ_ONLY, startOffset,
declaredLength)` and constructs `org.tensorflow.lite.e`, whose jadx banner says
`compiled from: Interpreter.java`. Its inherited methods are
`c(i)=getInputTensor`, `d(i)=getOutputTensor`, `e(in,out)=run` (see
`sources/classes/org.tensorflow.lite.g.java` lines 23–47); `e` places the
first argument at input index 0 and the second at output index 0.
No options, threads, delegates, or NNAPI are configured.

## 4. Preprocessing contract (confirmed from Java)

`AiWeakSignalForecastService.makeInputFromInfoItem()` (line 218):

```
deque = 100 samples, one per 100 ms, each (RSRP, RSRQ, SINR, pciChanged)
x = float32[1][400][1]
x[0][  0:100][0] = medianFilter(RSRP, 5)
x[0][100:200][0] = medianFilter(RSRQ, 5)
x[0][200:300][0] = medianFilter(SINR, 5)
x[0][300:400][0] = pciChanged                      # 1.0 / 0.0, NOT smoothed
```

* Feature type: **raw radio metrics**, not audio/MFCC/spectrum.
* Window: 100 samples × 100 ms = 10 s history (measurement rate 10 Hz; no
  audio sample rate applies anywhere in this pipeline).
* `medianFilter` (line 249) uses a 5-point window with clipped windows at the
  edges (3, 4, 5, … points) and averages the two middle values for even-length
  windows (line 242 `median`). There is no edge replication.
* **No normalization / scaling / mean subtraction** anywhere: dB values and a
  0/1 flag are written directly into the float32 tensor.
* `pciChanged` is computed as `current_PCI != lastPCI ? 1.0 : 0.0`; the PCI
  value itself is never fed to the model. Failure to refresh PCI leaves the
  previous value, so the flag may stay 0 during stale reads.
* Threshold/postprocessing: class index 1 = STRONG; `p[1] < 0.5` → WEAK.
  This is equivalent to argmax and is the only label mapping in the APK.

Result consumption (confirmed): every forecast run logs
`Log.d("forecastRes", ... "当前预测结果为:" + WEAK/STRONG)`. When the service was
started by `GAME_STATUS`, it broadcasts `com.xiaomi.mtb.aisignal.FORECAST_RES`
with string extra `FORECAST_RES`; otherwise the value is available only through
the binder `IAiWeakSignal.getForecastRes(String)`. No in-APK UI consumer of
`FORECAST_RES` / `getForecastRes` exists (the expected client is an external
game/performance service). `MtbTFLiteLoader` has exactly one call site,
`AiWeakSignalForecastService`; no other class references it.

The `mtb_auto_anasys_tool_audio_uplink_analysis_*` /
`..._5g_voice_bearer` resource strings and the SAR-test `AudioTrack` are
unrelated to this model: the model never sees audio samples.

## 5. Model

### 5.1 Format and I/O

* FlatBuffer TFL3, model version 3, description `MLIR Converted.`,
  `min_runtime_version 1.10.0`, metadata `reduced_precision_support =
  fp16accfp32`.
* Signature `serving_default`: input `input_1` (tensor name
  `serving_default_input_1:0`), output `dense_1` (tensor
  `StatefulPartitionedCall:0`).
* Input: `float32 [1,400,1]`, no quantization.
* Output: `float32 [1,2]`, no quantization (softmax probabilities).
* 46 tensors, 39 buffers, 28 operators, 0 custom operators.

### 5.2 Graph

```
input [1,400,1]
 EXPAND_DIMS -> [1,1,400,1]
 CONV_2D  k=(1,3) out=4  SAME, ReLU        (conv1d)
 RESHAPE -> [1,400,4] -> EXPAND_DIMS -> [1,400,1,4]
 MAX_POOL_2D k=(3,1) stride=(2,1) SAME     -> [1,200,1,4]
 RESHAPE -> [1,200,4] -> EXPAND_DIMS
 CONV_2D  k=(1,5) out=8  SAME, ReLU        (conv1d_1)
 MAX_POOL_2D k=(3,1) stride=(2,1) SAME     -> [1,100,1,8]
 RESHAPE -> [1,100,8] -> EXPAND_DIMS
 CONV_2D  k=(1,9) out=16 SAME, ReLU        (conv1d_2)
 RESHAPE -> [1,1600]
 FULLY_CONNECTED 1600->32, ReLU            (dense)
 FULLY_CONNECTED 32->2, none               (dense_1)
 SOFTMAX beta=1.0                          -> [1,2]
```

All conv/pool paddings are SAME, strides/dilations 1. Weights are stored
**float16** and converted to float32 by explicit `DEQUANTIZE` ops (10 ops, one
per constant weight/bias tensor); all
activations are float32.

### 5.3 Weights

| Tensor | Shape | Params |
|---|---|---|
| `sequential/conv1d/Conv1D` | [4,1,3,1] | 12 |
| `conv1d/bias` | [4] | 4 |
| `sequential/conv1d_1/Conv1D` | [8,1,5,4] | 160 |
| `conv1d_1/bias` | [8] | 8 |
| `sequential/conv1d_2/Conv1D` | [16,1,9,8] | 1,152 |
| `conv1d_2/bias` | [16] | 16 |
| `sequential/dense/MatMul` | [32,1600] | 51,200 |
| `dense/bias` | [32] | 32 |
| `sequential/dense_1/MatMul` | [2,32] | 64 |
| `dense_1/bias` | [2] | 2 |
| **Total** | | **52,650 (105,300 B fp16)** |

Full per-tensor stats/hashes: `ir/weights.json`. Full IR: `ir/ir.json`;
I/O detail: `ir/io.json`; op table: `ir/ops.txt`.

## 6. JAX reimplementation and verification

`reimpl/model.py` (generated by `tools/tflite2jax.py`) uses the extracted
weights in `reimpl/params.npz`; `init_params()`/`apply(params, x)`.

* `tools/verify_tflite_jax.py`, random input: **PASS, max_abs 0**.
* Same tool with a median-filtered derived series: **PASS, max_abs 1.49e-08**.
* `reimpl/verify_derived.py`: 58 cases (zeros, uniform, normal, raw-dB
  constants, 54 synthetic 100-sample series) across both backends:
  **worst max_abs 1.416e-06, 0 failures, tol 1e-4**.
* `reimpl/preprocess.py` is a line-for-line port of `medianFilter`/`median`
  including clipped edge windows and even-length averaging.
* `reimpl/pipeline.py` runs synthetic measurement series end-to-end:

```
strong     -> p=[0.000000, 1.000000] STRONG
degrading  -> p=[0.849930, 0.150070] WEAK
weak       -> p=[0.998451, 0.001549] WEAK
improving  -> p=[0.000166, 0.999834] STRONG
```

JAX and LiteRT agree to ≤1e-6 on these cases. Outputs are consistently
monotonic in SINR/RSRP, which supports (but does not prove) that the model was
trained on the same raw-dB scale used by the Java code.

## 7. Evidence index

| Claim | Evidence |
|---|---|
| APK/package/components | `inventory/inventory.json`, `inventory/inventory.md` |
| Model file and hash | `models/0.3_new_0410_less.tflite`, `evidence/hashes.txt` |
| I/O shapes/ops/options | `ir/ir.json`, `ir/io.json`, `ir/ops.txt` |
| Weight tensors | `ir/weights.json`, `reimpl/params.npz` |
| Call site/preprocessing/threshold | `sources/classes/com.xiaomi.mtb.aiservices.AiWeakSignalForecastService.java` lines 52–90, 201–210, 218–247 |
| Loading mechanism | `sources/classes/com.xiaomi.mtb.MtbTFLiteLoader.java` |
| Entrypoints | `sources/classes/com.xiaomi.mtb.MtbBroadcastReceiver.java` lines 88–98, 224–236 |
| Strings / class inventory | `evidence/all_strings.txt`, `evidence/relevant_strings.txt`, `evidence/all_classes.jsonl` |
| Numerical verification | `reimpl/verify.txt`, `reimpl/verify_derived.py` |
| Manifest (exported state, filters, sharedUserId) | `evidence/manifest.xml` (`evidence/manifest_dump.py`) |
| Measurement source / hook wrapper | `sources/classes/com.xiaomi.modem.OemHookAgent.java` lines 135–136, 189–205, 278–345, 566, 810, 1933–1952 |
| Hydra indirection behind `getLteMeasInfo` | `sources/framework/com.xiaomi.mirilhook.MiRilHook.java` lines 1011, 1516 (`system_ext/framework/mirilhook.jar`, outside the APK) |
| Full pipeline contract | `PIPELINE.md` |
| Forecast API reference scan (only `aiservices` classes) | `evidence/consumer_refs.jsonl` |
| `findings.json` re-validation | `evidence/validate_findings.py`, `evidence/validate_findings_v2.txt` |
| Commands | `evidence/cmd.txt` |

## 8. Confidence and unknowns

Confirmed: model identity/format, full I/O and op graph, real weights in JAX,
preprocessing contract, threshold, entrypoints, absence of native AI code.

Inferred: class 1 = STRONG / class 0 = WEAK only from the Java branch (no label
file in the model); raw-dB training scale inferred from behavior, since Java
applies no normalization.

Remaining unknown: training dataset/date semantics of `0.3_new_0410_less`; whether the
fixed 0.5 threshold was calibrated offline; behaviour when `OemHookAgent`
returns stale/partial measurement buffers.

> Update (pipeline patch): two items are now resolved — the stale/partial-buffer
> behaviour is traced in §9.2 / `PIPELINE.md` (previous field values are silently
> re-appended, and a null hook kills the sampler), and
> `ModemUtils.PROP_SAR_LOG_SWITCH_OPEN` is the literal `"true"`
> (`PROP_SAR_LOG_SWITCH_CLOSE` = `"false"`,
> `sources/classes/com.xiaomi.modem.ModemUtils.java:220-221`; this constant was
> cited as unknown in an earlier draft of §3).
> The training corpus and the threshold calibration remain unknown.

## 9. Pipeline integration (entry → samples → model → output → lifecycle)

Full contract, byte layouts and failure matrix: **`PIPELINE.md`** (this section
is the summary and the corrections to §3 that the manifest forced).

### 9.1 Entry surface and exported state (confirmed)

Decoded manifest: `evidence/manifest.xml` (`evidence/manifest_dump.py`).

| Fact | Value |
|---|---|
| App identity | `android:sharedUserId="android.uid.system"`, `coreApp="true"`, `usesNonSdkApi="true"` |
| Service | `<service android:name="com.xiaomi.mtb.aiservices.AiWeakSignalForecastService" android:enabled="true" android:exported="true"/>` — **no `android:permission`** |
| Receiver | `<receiver android:name="com.xiaomi.mtb.MtbBroadcastReceiver" android:exported="true">`, filters: `ACTION_SHUTDOWN`, `xiaomi.intent.action.ACTION_MI_SERVER_CHECK`, `com.xiaomi.mtb.RFFE_TEST`, `com.xiaomi.mtb.aisignal.GAME_START`, `com.xiaomi.mtb.aisignal.GAME_EXIT`, `xiaomi.intent.action.ONLY_FOR_WAKEUP_MTB`, and `android.provider.Telephony.SECRET_CODE` with `android_secret_code` hosts `663368378, 68276278, 123456, 663364636, 73694364, 717, 284, 686564, 24744625` |
| Binder | `IAiWeakSignal.Stub.onTransact` only calls `parcel.enforceInterface(DESCRIPTOR)` — an interface check, not a caller check → any app may bind and read the forecast |

Two corrections to §3 that follow from the manifest:

* The action the code tests, `com.xiaomi.mtb.aisignal.GAME_STATUS`
  (`MtbBroadcastReceiver.java:35,88`), is **not** in the receiver's filters.
  `GAME_START`/`GAME_EXIT` *are* filtered but **not** tested by the code, so an
  implicit `GAME_STATUS` broadcast never matches and a `GAME_START`/`GAME_EXIT`
  broadcast falls through to "do nothing". The only working path for that entry
  is an explicit broadcast to `com.xiaomi.mtb/.MtbBroadcastReceiver`.
* `BOOT_COMPLETED` / `LOCKED_BOOT_COMPLETED` are handled at
  `MtbBroadcastReceiver.java:69-76` but no such filter is declared, so those
  branches are dead as well (only the `SECRET_CODE` and `GAME_STATUS` paths
  reach the AI service).

Secret code `*#*#24744625#*#*` → `SECRET_CODE` (host `24744625`) →
`MtbBroadcastReceiver.onReceive` (`:224`) → `AiWeakSignalForecastService.onAiSignalBcHandle`
(`AiWeakSignalForecastService.java:270`) — a **static-boolean toggle**: first
call `startService(new Intent(ctx, AiWeakSignalForecastService.class))`, second
call `stopService` + Toast. It only runs when `OemHookAgent.mHookType == 2`
(QCOM; `MtbBroadcastReceiver.java:224-232`).

### 9.2 Measurement source and the 10 Hz → 1 Hz buffers (confirmed)

There is no modem callback in this path — it is a 10 Hz pull:

```
ScheduledExecutorService s = Executors.newScheduledThreadPool(10)         (line 205)
s.scheduleAtFixedRate(getSignalInfo, 0, 100, MILLISECONDS)                (209)
s.scheduleAtFixedRate(forecastRes,   0, 1000, MILLISECONDS)               (210)
```

`getSignalInfo` (lines 52–72): `System.currentTimeMillis()` → `Timestamp`
`substring(11)` string (stored in `infoItem.time`, never read) →
`getCurrentInfo()` → `getNetworkType()` `13`→`OemHookAgent.getLteMeasInfo(slot)`,
`20`→`getNr5gMeasInfo(slot)`, anything else returns early (a sample is appended
anyway) → `updateLteMeasInfo`/`update5gMeasInfo` parse the `ByteBuffer` in place
→ `AddInfoDeque(new infoItem(..., current_PCI != lastPCI ? 1.0f : 0.0f))` →
`lastPCI = current_PCI`.

* Buffer layouts actually consumed (relative reads, no length check):
  LTE `get()`/`getInt()`/13×`getFloat()` → **RSRP@57, RSRQ@61, SINR@65, PCI@69**
  (≥73 B); NR `get()`/4×`getFloat()` → **RSRQ@17**, 4×`getFloat()` → **PCI@37**,
  **RSRP@41, SINR@45** (≥49 B) (lines 349–407). Values outside
  RSRP∈[-150,-30] dBm, RSRQ∈[-40,10] dB, SINR∈[-20,40] dB leave the previous
  field untouched; a null buffer only logs `byteBuf is null` — so **stale fields
  are re-appended as if fresh**, and no timestamp ever detects the gap.
* Queue: `ArrayDeque`, depth 100, drop-oldest (`AddInfoDeque`, lines 147–153);
  mutated by the sampler thread while the forecast thread iterates it (pool of
  10, no lock) — an unsynchronised producer/consumer.
* Source implementation: `OemHookAgent.getHook()` returns **null** until the
  `qcrilhook.jar` callback `onQcRilHookReady` flips `mMiRilHookReady`; the
  readiness probe in `initService` (`hook.getLteMeasInfo(0)` in a
  `catch (NullPointerException)`) swallows the NPE and never retries. The call
  itself is reflective into `/system_ext/framework/mirilhook.jar`
  (`com.xiaomi.mirilhook.MiRilHook`, `sources/framework/…:1011,1516`), which
  delegates to the Hydra modem-stats database; the app-side wrapper is
  `com.xiaomi.modem.OemHookAgent` (`sources/classes/…:566,810,1933-1952`).

### 9.3 Output path (confirmed)

`forecastRes` (lines 74–96): if `size()==100` → `makeInputFromInfoItem()` →
`mTFLiteInterpreter.run(input, output)` → `mForecastRes = output[0][1] < 0.5f ? "WEAK" : "STRONG"`;
then, **only if `isfrombroadcast`**, `sendBroadcast(action "com.xiaomi.mtb.aisignal.FORECAST_RES", extra "FORECAST_RES")`.
The binder (`IAiWeakSignal.getForecastRes(String)`, lines 97–102) returns the
same `mForecastRes` and ignores its argument. Initial value `"NOT Ready"`;
1 Hz `Log.d("forecastRes", …)` is the only other sink. No in-APK UI or receiver
consumes the broadcast, and `isfrombroadcast` is only assigned on the
`onStartCommand` path (never on `onBind`), so a bind-only client never sees a
broadcast.

### 9.4 Lifecycle defects (confirmed code, inferred runtime effect)

1. `initService` calls `stopSelf()` when `persist.vendor.ai.signal.cloud.switch
   == "true"` but **does not return** — the service stops *and* still schedules
   both periodic tasks.
2. An exception in either `Runnable` (null hook on LTE/NR, `BufferUnderflow`,
   null interpreter after a failed model load) cancels that `scheduleAtFixedRate`
   task permanently; there is no retry, no watchdog, and `mForecastRes` freezes
   at `"NOT Ready"` (or its last value) while still being broadcast and bindable.
3. `onStartCommand` reads the `GAME_BroadCast` extra (jadx prints
   `intent.getExtra("GAME_BroadCast", ModemUtils.PROP_SAR_LOG_SWITCH_CLOSE)`;
   the real call is `getStringExtra(String, String)`, default `"false"`)
   **after** `initService`, so a `START_STICKY` restart with a null Intent NPEs
   after the executor is already running; `isServiceRunning` stays false and the
   next start/bind calls `initService` again, creating a second executor while
   the first is never shut down (`mExecutor` is overwritten; only `onDestroy`
   shuts down the newest one).
4. `onDestroy` does `mExecutor.shutdown()` (no `shutdownNow`, no null check) and
   never closes the TFLite interpreter or unmaps the model.
5. No wake lock, no foreground service, no `stopSelf` on unbind: sampling pauses
   with the CPU during screen-off, and `scheduleAtFixedRate` then catches up in a
   burst (monotonic clock stops during suspend) — samples end up stamped with
   near-identical wall-clock times.

