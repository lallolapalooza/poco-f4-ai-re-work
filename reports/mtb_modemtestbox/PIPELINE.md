# Data-pipeline integration contract — ModemTestBox (`com.xiaomi.mtb`)

Scope: the single AI feature in the APK, "AI weak-signal forecast"
(`AiWeakSignalForecastService`). Follows `PIPELINE_TEMPLATE.md`.
Every row is tagged `confirmed` / `inferred` / `unknown` with a file/line,
class/method or manifest reference. Line numbers refer to the decompiled files
under `sources/`.

Short names used below:

| Alias | File |
|---|---|
| `SVC` | `sources/classes/com.xiaomi.mtb.aiservices.AiWeakSignalForecastService.java` |
| `RCV` | `sources/classes/com.xiaomi.mtb.MtbBroadcastReceiver.java` |
| `LOAD` | `sources/classes/com.xiaomi.mtb.MtbTFLiteLoader.java` |
| `IAI` | `sources/classes/com.xiaomi.mtb.aiservices.IAiWeakSignal$Stub$Proxy.java` |
| `OEM` | `sources/classes/com.xiaomi.modem.OemHookAgent.java` |
| `MIR` | `sources/framework/com.xiaomi.mirilhook.MiRilHook.java` (from `system_ext/framework/mirilhook.jar`, *not* inside the APK) |
| `MAN` | `evidence/manifest.xml` (decoded by `evidence/manifest_dump.py`) |

---

## 1. Data sources / triggers

### 1.1 Entry surfaces (all three reach the same service)

| # | Surface | Exact coordinates | Exported / protected | Confidence |
|---|---|---|---|---|
| A | Dial secret code `*#*#24744625#*#*` → system `android.provider.Telephony.SECRET_CODE` | `MAN` receiver filter `<data android:scheme="android_secret_code" android:host="24744625"/>`; `RCV.onReceive` line 66, host branch line 224 → `HOST_CODE_MTB_AI_SIGNAL` (`"24744625"`, line 25) → `AiWeakSignalForecastService.onAiSignalBcHandle(context)` (`SVC:270`) | Receiver `android:exported="true"`, **no permission** (`MAN` line 59). The broadcast itself is a framework protected broadcast → only the system can send it. | confirmed (manifest+code); protection level inferred |
| B | Broadcast action `com.xiaomi.mtb.aisignal.GAME_STATUS` | `RCV:88` compares against `XIAOMI_INTENT_ACTION_AI_SIGNAL_GAME_STATUS` (`"com.xiaomi.mtb.aisignal.GAME_STATUS"`, `RCV:35`) → `onAiSignalGameStatusBcHandle(context, intent)` (`SVC:285`) | Receiver exported, no permission. **The manifest does not declare this action** (it declares `…aisignal.GAME_START` and `…GAME_EXIT`, `MAN` lines 68–75) → the action can only arrive as an *explicit* broadcast to `com.xiaomi.mtb/.MtbBroadcastReceiver` (or via `GAME_START`/`GAME_EXIT`, which the code does not test and therefore ignores). | confirmed |
| C | Direct bind/start of the service | `MAN` line 26: `<service android:name="com.xiaomi.mtb.aiservices.AiWeakSignalForecastService" android:enabled="true" android:exported="true"/>` — no `android:permission` | Any app can `startService`/`bindService`. Binder `IAiWeakSignal` (`IAI`) enforces only `enforceInterface(DESCRIPTOR)` (an interface-type check, not a caller check) → no permission gate on `getForecastRes`. | confirmed |

`OemHookAgent.mHookType` must be `2` (QCOM) for A and B (`RCV:88-100`, `RCV:224-232`);
`1` = MTK logs and returns, anything else does nothing. `mHookType` is read
reflectively from `/system_ext/framework/mirilhook.jar`
(`com.xiaomi.mirilhook.MiRilHook.getHookType()`, `OEM:291-317`).
So on an MTK build the feature is unreachable even though the service is exported.

App identity for everything above: `android:sharedUserId="android.uid.system"`,
`coreApp="true"`, `usesNonSdkApi="true"` (`MAN` line 1, 5). The service therefore
runs as the system UID and is exempt from background-start/service limits
(system-UID exemption, `inferred`).

### 1.2 Start / stop semantics (`SVC:270-326`)

`onAiSignalBcHandle(Context)` is a **toggle** driven by a static field:

```
aiSignalIntent != null -> Toast "Ai_Signal service stop!", context.stopService(aiSignalIntent), aiSignalIntent = null
aiSignalIntent == null -> Toast "Ai_Signal service start!", new Intent(ctx, AiWeakSignalForecastService.class),
                          aiSignalIntent = it, context.startService(it)      # no extra "GAME_BroadCast"
```

`onAiSignalGameStatusBcHandle(Context, Intent)`:

```
intent.getIntExtra("gameStatus", 0) == 0 -> stop (if a start Intent is remembered), Toast "…stop!"/"…not run!"
otherwise                                 -> stop-then-start: Intent(extra "GAME_BroadCast" = "True"), startService
```

`aiSignalIntent` is `private static` and lives only in the app process: after a
process death the toggle loses its state, so the first secret code after a
restart always *starts* and never stops a service that is already running in a
new process.

### 1.3 Service lifecycle (`SVC:187-211`, `410-439`)

| Step | Behaviour | Confidence |
|---|---|---|
| `onStartCommand` (`SVC:428`) | if `!isServiceRunning`: `initService(intent)`; then `isfrombroadcast = intent.getExtra("GAME_BroadCast", "false").equals("True")` (`SVC:432`); `isServiceRunning = true`; finally `super.onStartCommand(...)` (returns `START_STICKY`). Note the order: sampling is already scheduled *before* `isfrombroadcast` is known. | confirmed |
| `onBind` (`SVC:410`) | if `!isServiceRunning`: `initService(intent)`, `isServiceRunning = true`; returns `mBinder`. **`isfrombroadcast` is never assigned on the bind path**, so a bind-only client never causes `FORECAST_RES` broadcasts. | confirmed |
| `onDestroy` (`SVC:420`) | `mExecutor.shutdown()`; `isServiceRunning = false`; log. No `shutdownNow`, no `Future.cancel`, no `Interpreter.close()`, no `MappedByteBuffer` unmap. Field access without null check. | confirmed |
| return code | `super.onStartCommand` → `START_STICKY`, so the system may recreate the service with a **null** Intent. | inferred (framework default) |
| foreground | No `startForeground`, no notification, no `stopSelf` on unbind, no idle timeout → a started service runs until surface A/B stops it or the process dies. | confirmed |
| wake locks | No `PowerManager`, `WifiLock`, `AlarmManager` or `JobScheduler` anywhere in `SVC` (imports at `SVC:3-21`; body never references them). Screen-off does **not** keep the CPU awake. | confirmed |
| cadence after suspend | `ScheduledThreadPoolExecutor` uses `System.nanoTime` (CLOCK_MONOTONIC, which stops while the device is suspended). After a long screen-off gap, `scheduleAtFixedRate` fires catch-up executions back-to-back until it realigns, so the 100 ms sampler can push a burst of samples that all carry *fresh* `System.currentTimeMillis()` values into the deque. | inferred (standard JDK/Android executor + clock semantics) |

### 1.4 Measurement source: pull, not callbacks

There is **no modem-stats callback** feeding this pipeline. Every 100 ms the
service *pulls* one snapshot:

| Stage | Coordinate | Confidence |
|---|---|---|
| timer | `mExecutor.scheduleAtFixedRate(this.getSignalInfo, 0, 100, MILLISECONDS)` (`SVC:52`, `SVC:209`) | confirmed |
| radio type | `TelephonyManager.getNetworkType()` (`SVC:166`); `13` → LTE, `20` → NR, anything else → **return without touching any metric** (`SVC:162-178`) | confirmed |
| slot | `getDefaultDataSlotId()` (`SVC:179`): `SubscriptionManager.getPhoneId(getActiveDataSubscriptionId())`, mapped to `0` unless it is exactly `1` | confirmed |
| QCOM hook object | `OemHookAgent.getHook()` (`SVC:192`, `OEM:278`) — returns the singleton **only if** `mMiRilHookReady` is true, else `null` | confirmed |
| readiness | `mMiRilHookReady` is set asynchronously by `QcRilHookCbMethodProxy.onQcRilHookReady` (`OEM:189-205`) via the `qcrilhook.jar` callback; `makeHook()` loads `mirilhook.jar` and constructs the hook (`OEM:291-345`) | confirmed |
| readiness probe in `initService` | `hook.getLteMeasInfo(0)` inside `try { } catch (NullPointerException)` (`SVC:192-197`) — a null hook is caught and *logged only*: `mMtbHookAgent` stays null and is never retried | confirmed |
| call | `mMtbHookAgent.getLteMeasInfo(slot)` / `getNr5gMeasInfo(slot)` (`SVC:169`, `175`; `OEM:566`, `OEM:810`) → reflective `MiRilHook.getLteMeasInfo(int)` (`OEM:1933-1952`) → `mirilhook.jar`: `MiRilHook.getLteMeasInfo` (`MIR:1011`) / `getNr5gMeasInfo` (`MIR:1516`) → `HydraDataManager.mStatsParaDiagLogLteSrvMeas` / `mModemStatsNr5gMl1MeasDatabaseUpdateExt` | confirmed up to `mirilhook.jar`; Hydra internals unknown |
| failure return | reflection failure / hook not ready / `mHydraMgr == null` → the default object, i.e. **`null` ByteBuffer** (`OEM:1933-1952`, `MIR:1011-1017`) | confirmed |

So the buffer is produced by the closed-source Hydra modem-stats stack on every
call — the caller never registers for updates, and there is no sample sequence
number, no modem timestamp and no validity flag in the result.

---

## 2. Transport and format

### 2.1 Sample record

`SVC.infoItem` (`SVC:104-145`): `String time; float RSRP, RSRQ, SINR, PCI`.
`PCI` actually holds the *PCI-change flag* (0.0/1.0), not the PCI value
(`SVC:63`: `current_PCI != lastPCI ? 1.0f : 0.0f`). `getTime()` is never
called by the pipeline.

| Field | Unit / range | Source offset | Confidence |
|---|---|---|---|
| `time` | `new Timestamp(System.currentTimeMillis()).toString().substring(11)` → `"HH:mm:ss.mmm"`, wall clock, no date, no monotonic clock | `SVC:60` | confirmed |
| RSRP | dBm; accepted only if `-150 ≤ v ≤ -30` | LTE off 57, NR off 41 | confirmed (derived offsets, §2.2) |
| RSRQ | dB; accepted only if `-40 ≤ v ≤ 10` | LTE off 61, NR off 17 | confirmed |
| SINR | dB; accepted only if `-20 ≤ v ≤ 40` | LTE off 65, NR off 45 | confirmed |
| PCI-change | 0.0 / 1.0 | derived from `current_PCI != lastPCI` (`SVC:63`, `70-71`) | confirmed |

### 2.2 ByteBuffer layouts actually consumed

`ByteBuffer` (big-endian default, no order change), read sequentially with
relative getters; nothing is validated against `remaining()`.

**LTE — `SVC.updateLteMeasInfo` (`SVC:376-407`), returned by `getLteMeasInfo`**

| Offset | Read | Consumed as |
|---|---|---|
| 0 | `get()` | skipped (1 byte) |
| 1 | `getInt()` | skipped |
| 5 … 56 | 13 × `getFloat()` | skipped |
| **57** | `getFloat()` → `f2` (`SVC:393`) | RSRP (validated) |
| **61** | `getFloat()` → `f3` (`SVC:394`) | RSRQ (validated) |
| **65** | `getFloat()` → `f4` (`SVC:395`) | SINR (validated) |
| **69** | `getInt()` (`SVC:396`) | PCI (stored in a `float`) |

Minimum buffer length consumed: 73 bytes.

**NR — `SVC.update5gMeasInfo` (`SVC:349-374`), returned by `getNr5gMeasInfo`**

| Offset | Read | Consumed as |
|---|---|---|
| 0 | `get()` | skipped |
| 1, 5, 9, 13 | 4 × `getFloat()` | skipped |
| **17** | `getFloat()` → `f2` (`SVC:356`) | RSRQ (validated) |
| 21, 25, 29, 33 | 4 × `getFloat()` | skipped |
| **37** | `getInt()` (`SVC:361`) | PCI (stored in a `float`) |
| **41** | `getFloat()` → `f3` (`SVC:362`) | RSRP (validated) |
| **45** | `getFloat()` → `f4` (`SVC:363`) | SINR (validated) |

Minimum buffer length consumed: 49 bytes. Note the NR struct puts RSRQ *before*
RSRP and the PCI word at a different offset than LTE — the two branches are not
interchangeable. Field *meanings* are inferred from the validation ranges and
the order of the assignment statements; the byte offsets themselves are
confirmed by the read sequence.

### 2.3 Queue / threading

| Property | Value | Confidence |
|---|---|---|
| Queue | `private final ArrayDeque inputDeque = new ArrayDeque()` (`SVC:47`) | confirmed |
| Depth | 100 (`AddInfoDeque`, `SVC:147-153`: `if (size == 100) removeFirst(); add(item)`) → oldest dropped, newest always appended | confirmed |
| Producers | 1 (`getSignalInfo` task, 10 Hz) | confirmed |
| Consumer | 1 (`forecastRes` task, 1 Hz): iterates the deque in `makeInputFromInfoItem` (`SVC:218-240`) and checks `size() == 100` (`SVC:81`) | confirmed |
| Executor | `Executors.newScheduledThreadPool(10)` (`SVC:205`) → two *different* worker threads, no synchronization, `ArrayDeque` is not thread-safe | confirmed |
| Race | The consumer's `for (infoItem it : this.inputDeque)` runs without a lock while the producer may `removeFirst()`/`add()`; possible `ConcurrentModificationException`, torn size checks, or a sample being consumed twice/never. An exception thrown inside either `Runnable` **permanently cancels that periodic task** (`ScheduledExecutorService` contract). | inferred (JDK semantics) applied to confirmed unsynchronised code |
| Drop policy | None at the queue level: a stalled sampler simply leaves stale entries at the head. The forecast task does not skip ticks; it runs every tick once `size()==100`. | confirmed |
| Overrun | Fixed-rate, no `scheduleWithFixedDelay`; a slow inference delays the next tick instead of being dropped (same thread). | confirmed |
| Buffer ownership | A new `ByteBuffer` per call, owned by the caller. `SVC` never `rewind()`s, `duplicate()`s, `clear()`s or reuses it — the 73/49 bytes are consumed once, then the buffer is garbage. No pooling, no `DirectByteBuffer` release. | confirmed |

### 2.4 Staleness / partial handling

| Case | Observed behaviour | Confidence |
|---|---|---|
| Hook not ready / reflection failure / Hydra manager null | `SignalInfoHandle` logs `byteBuf is null` and returns **without touching** `current_RSRP/RSRQ/SINR/PCI` (`SVC:148-160`, null check at `150`) — the previously cached values are re-appended, so a stale sample is indistinguishable from a fresh one | confirmed |
| Metric out of range | that one field keeps its previous value (each range check is independent, `SVC:364-373`, `397-406`) — a sample can mix fresh and stale fields | confirmed |
| Buffer shorter than expected | `BufferUnderflowException` escapes the `Runnable` → sampler task cancelled permanently | inferred |
| Radio type not LTE/NR | `getCurrentInfo` returns early at `SVC:171-178`; **a sample is still appended** (`SVC:61-63` calls `AddInfoDeque` unconditionally after `getCurrentInfo`) with fully stale values | confirmed |
| Timestamps | never compared, never used for gap detection or weighting; the model sees an evenly spaced 100-sample grid regardless of real timing | confirmed |

---

## 3. Preprocessing

`makeInputFromInfoItem()` (`SVC:218-240`) runs on the 1 s task, in place on the
pre-allocated `input` array (`float[1][400][1]`, `SVC:199-202`):

```
1. iterate deque (oldest→newest):  a[i]=RSRP, b[i]=RSRQ, c[i]=SINR,  input[0][300+i][0]=PCIflag
2. ma = medianFilter(a, 5); mb = medianFilter(b, 5); mc = medianFilter(c, 5)
3. for i in 0..99: input[0][i][0]=ma[i]; input[0][100+i][0]=mb[i]; input[0][200+i][0]=mc[i]
```

| Property | Value | Confidence |
|---|---|---|
| Window / stride | 100 samples × 100 ms = 10.0 s of history, stride 1 s (overlapping windows) | confirmed |
| Per-metric filter | centred median, window 5 (≈0.5 s), independently per metric | confirmed |
| Edge handling | `medianFilter` (`SVC:249-267`): for `i ≤ 2` uses `[0, i+2]`; for `i ≥ 98` uses `[i-2, 99]`; otherwise `[i-2, i+2]`. Window lengths 3,4,5,… and 5,4,3 at the ends — **no edge replication, no padding to constant length**; `median` averages the two middle values for even lengths (`SVC:242-247`, in-place `quickSort`). | confirmed |
| Channel order | RSRP[0:100], RSRQ[100:200], SINR[200:300], PCI-change[300:400] | confirmed |
| Normalisation | **none** — raw dBm/dB and 0/1 are written straight into `float32` | confirmed |
| PCI-change smoothing | none (raw flag, unlike the three dB channels) | confirmed |
| Tensor layout | `float32[1,400,1]`; the 400 axis is the concatenated time×channel axis, model reshapes to `[1,1,400,1]` and applies 1-D convs (see `ir/ops.txt`) | confirmed |
| Reuse | `input`/`output` allocated once in `initService`, re-written each tick — no per-inference allocation | confirmed |
| Contract match | the model's input is `[1,400,1]` f32 with no quantisation (`ir/io.json`); the Java code writes exactly that shape/dtype, so the contract matches | confirmed |
| Semantic risk | the model was trained on some ordering of the 4 channels; the Java order (RSRP, RSRQ, SINR, flag) is the only ordering present in the APK, and JAX reproduces it | confirmed (order), inferred (training-time provenance) |

`reimpl/preprocess.py` is a line-for-line port of `median`/`medianFilter`
(clipped windows + even-length averaging); `reimpl/verify_derived.py` compares
it against the TFLite reference on 58 synthetic series.

---

## 4. Model invocation

| Item | Value | Confidence |
|---|---|---|
| Runtime | bundled TFLite Java API, obfuscated: `org.tensorflow.lite.e` = `Interpreter` (jadx banner "compiled from: Interpreter.java"); `e.c` = `getInputTensor`, `e.d` = `getOutputTensor`, `e.e` = `run(Object,Object)` (`sources/classes/org.tensorflow.lite.e.java`, `g.java`) | confirmed |
| Loader | `new MtbTFLiteLoader(getApplicationContext(), "0.3_new_0410_less.tflite")` (`SVC:204`) | confirmed |
| Model binding | `AssetFileDescriptor` → `FileInputStream(fd).getChannel().map(READ_ONLY, startOffset, declaredLength)` → `new e(MappedByteBuffer)` (`LOAD:22-33`, `LOAD:36-109`) — zero-copy asset mapping, no temp file | confirmed |
| Model file | `assets/0.3_new_0410_less.tflite`, 112,544 B, sha256 `08e82b3c…b513` (`models/`, `evidence/hashes.txt`) | confirmed |
| Session lifetime | created once per `initService`, never re-created, never closed | confirmed |
| Options / delegates / threads | none: no `Interpreter.Options`, no NNAPI/GPU/XNNPACK delegate, no thread count, no `setNumThreads` anywhere in `LOAD` or `SVC` | confirmed |
| Inputs/outputs | `input float32[1,400,1]` filled by the arrays above; `output float32[1,2]` (`SVC:199-202`) passed by reference to `run` (`SVC:84`); `getInputTensor`/`getOutputTensor` are exposed by `LOAD:111-117` but unused | confirmed |
| Batching | none (batch 1) | confirmed |
| Cadence | `scheduleAtFixedRate(this.forecastRes, 0, 1000, MILLISECONDS)` (`SVC:210`); the body executes inference only when `inputDeque.size() == 100` (`SVC:81-87`) | confirmed |
| Warm-up | none; first inference happens only once 100 samples exist (≈10 s after start, less if stale samples were appended while the hook was absent) | confirmed |
| Per-run cost | not instrumented beyond `Log.d("forecastRes", "推演任务使用时间 %d ms")` (`SVC:88`) | confirmed |

---

## 5. Output postprocessing

| Step | Behaviour | Confidence |
|---|---|---|
| Decode | `output[0][1]` used directly; `output[0][0]` (p(WEAK)) is ignored | confirmed (`SVC:86`) |
| Threshold | `output[0][1] < 0.5f ? "WEAK" : "STRONG"` — a fixed 0.5 cut on the softmax probability of class 1; equivalent to argmax | confirmed |
| Softmax | already in-graph (`SOFTMAX beta=1.0`, `ir/ops.txt`); Java does not re-normalise | confirmed |
| Temporal smoothing | none; each second's label replaces `mForecastRes` | confirmed |
| Numeric formatting | none — the value is the literal `String` `"WEAK"`/`"STRONG"`; the probability never leaves the service | confirmed |
| Default / sentinel | field initialiser `mForecastRes = "NOT Ready"` (`SVC:43`); no timeout back to the sentinel, it is overwritten only by a successful run | confirmed |
| Result struct | just the `String` field `mForecastRes`; there is no result object, score array, timestamp or window id | confirmed |

---

## 6. Consumer / action

| Sink | Coordinate | Behaviour | Confidence |
|---|---|---|---|
| Log | `Log.d("forecastRes", "推演任务使用时间 …---当前预测结果为:<res>")` (`SVC:88`) | every tick (1 Hz) once scheduled | confirmed |
| Broadcast | `sendBroadcast(new Intent("com.xiaomi.mtb.aisignal.FORECAST_RES").putExtra("FORECAST_RES", mForecastRes))` (`SVC:89-93`), guarded by `isfrombroadcast` (only when started through `GAME_STATUS`) | **implicit, unprotected** broadcast; sent every second including `"NOT Ready"` | confirmed |
| Binder | `IAiWeakSignal.Stub.getForecastRes(String)` (`SVC:97-102`) returns `mForecastRes` and **ignores the argument**; callable by any app that binds the exported service; returns `"NOT Ready"` before the first successful inference | confirmed |
| UI | none in-APK: no activity reads `FORECAST_RES` or binds `IAiWeakSignal`; the only user feedback is the start/stop `Toast`s (`SVC:271-324`) | confirmed |
| Storage / network | none — no file, DB or HTTP write is part of this path | confirmed |

`FORECAST_RES` has no declared or runtime-registered receiver inside the APK:
`MAN` lists a single receiver, `SVC` never calls `registerReceiver`, and the
action literal occurs exactly **once** in the deduplicated DEX string table
(`evidence/all_strings.txt` line 8830), so no other class can hold its own copy
of the filter string. The consumer is therefore an **external** client
(game/performance service); which component that is cannot be determined from
this APK (`unknown`).

---

## 7. Failure and fallback

There is **no fallback path at all** — every failure degrades to "the periodic
task stops" or "the label freezes".

| Failure | Mechanism | User-visible result | Confidence |
|---|---|---|---|
| `OemHookAgent.getHook()` returns `null` at `initService` (hook not yet ready, no QCOM hook jar, MTK build) | The NPE from the readiness probe is swallowed (`SVC:192-197`); the *first* `getCurrentInfo()` on LTE/NR then throws NPE inside the sampler `Runnable` → that periodic task is cancelled for the life of the executor | sampler dead, `mForecastRes` stays `"NOT Ready"` and is still broadcast/bindable every second | confirmed code paths + inferred executor behaviour |
| Hook becomes ready *later* | Nothing re-probes; there is no `registerHookReadyEvent` in `SVC` | same as above | confirmed |
| `getLteMeasInfo`/`getNr5gMeasInfo` returns `null` or Hydra is null | `SignalInfoHandle` logs `byteBuf is null` and returns (`SVC:148-160`) | deque fills with stale values; a forecast is still produced from frozen data | confirmed |
| Buffer shorter than the 73/49 B layout | `BufferUnderflowException` inside the sampler | sampler dead | inferred |
| Asset missing / model fails to load | `MtbTFLiteLoader` ctor catches every exception and leaves `mTFLite == null` (`LOAD:22-33`); `run()` then NPEs on `this.mTFLite.e(...)` (`LOAD:119-122`, call at 121) inside the forecast `Runnable` | forecast task cancelled; `"NOT Ready"` persists and is broadcast/bindable | confirmed |
| Shape mismatch between the hardcoded `[1][400][1]` and the actual model | exception inside `run` | forecast task dead | inferred |
| Cloud-control property `persist.vendor.ai.signal.cloud.switch == "true"` | `initService` calls `stopSelf()` (`SVC:188-190`; `ModemUtils.PROP_SAR_LOG_SWITCH_OPEN = "true"`, `ModemUtils:220-221`) but **does not return** | the service stops *and* still schedules both tasks; `onDestroy` shuts the executor down, so behaviour depends on whether `stopSelf` destroys before the first scheduled run | confirmed |
| Unsupported radio type / no data subscription | `getCurrentInfo` returns early; sampling continues with stale values | forecast on frozen data | confirmed |
| Device suspended (screen off) | no wake lock; `CLOCK_MONOTONIC` pauses | gap, then a catch-up burst of samples stamped with near-identical wall-clock times | inferred |
| Process killed | `START_STICKY` restart with a **null** Intent → `initService(null)` runs first, then `intent.getExtra(...)` (`SVC:432`) NPEs before `isServiceRunning = true` | the newly created executor keeps running (sampling + forecasting) while a later start/bind calls `initService` again → **second executor**, first one leaked (never shut down); `isfrombroadcast` stays unset | inferred from confirmed ordering |
| Bind-only client | `isfrombroadcast` never set | `getForecastRes` works, `FORECAST_RES` broadcasts never happen | confirmed |
| Model/source unavailable *after* a successful run | nothing resets `mForecastRes` | the last successful label is served indefinitely | confirmed |

---

## 8. Confidence and open questions

| Claim | Confidence | Evidence | What would settle it |
|---|---|---|---|
| Secret code `24744625` and explicit `GAME_STATUS` both start/stop the same service, QCOM-only | confirmed | `MAN` receiver filters; `RCV:66-232`; `SVC:270-326` | — |
| `GAME_STATUS` is unreachable as an implicit broadcast (manifest declares `GAME_START`/`GAME_EXIT` instead) | confirmed | `MAN` lines 68–75 vs `RCV:35` | a sender APK to compare against |
| Service and binder are exported with no permission | confirmed | `MAN` line 26; `IAI.onTransact` | — |
| 10 Hz pull from `OemHookAgent` → `mirilhook.jar` → Hydra, `null` on any failure | confirmed | `OEM:278`, `1933`, `MIR:1011`, `1516` | Hydra source to confirm field semantics |
| Deque depth 100, drop-oldest, 1 s overlapping 10 s window | confirmed | `SVC:147-153`, `218-240` | — |
| Two tasks on an unsynchronised `ArrayDeque` from a 10-thread pool | confirmed | `SVC:47`, `205-210` | a runtime trace to show an actual race |
| LTE layout: RSRP@57, RSRQ@61, SINR@65, PCI@69 (≥73 B) | confirmed (read offsets) | `SVC:378-396` | Hydra struct definition to confirm semantics |
| NR layout: RSRQ@17, PCI@37, RSRP@41, SINR@45 (≥49 B) | confirmed (read offsets) | `SVC:351-363` | same |
| Stale values are re-appended as if fresh on every failure | confirmed | `SVC:148-160`, `364-373` | — |
| Threshold `p[1] < 0.5 → WEAK` | confirmed | `SVC:86` | a label file (none exists) |
| No wake lock / no foreground service / no fallback | confirmed | `SVC` imports + body; `MAN` | — |
| Executor cancellation on `Runnable` exception | inferred (JDK/Android contract) | `SVC:52-96` | a device logcat showing the sampler stopping |
| Catch-up burst after suspend | inferred | `SVC:205-210` | on-device timing |
| Which external component consumes `FORECAST_RES` | unknown | action literal appears once in `evidence/all_strings.txt`; no in-APK receiver | the consumer app's manifest |

Residual unknowns: Hydra's field semantics and units inside the 73/49-byte
buffers; whether the int argument to `getLteMeasInfo`/`getNr5gMeasInfo` is a
phone id or a slot index (the service passes the active-data phone id, which it
narrows to `0`/`1`); whether the system `SECRET_CODE` broadcast is registered as
a protected broadcast on this ROM; the model's training corpus and the
calibration of the 0.5 cut.

---

## 9. Reimplementation mapping

| Pipeline stage | Java reference | JAX/Python counterpart | Status |
|---|---|---|---|
| 1. Trigger / lifecycle (secret code, service, executors) | `RCV:66-232`, `SVC:187-211`, `270-326`, `410-439` | not modelled — the harness is invoked directly | out of scope (Android glue) |
| 2. Measurement source (10 Hz pull, 73/49-byte parse, range validation, stale retention) | `OEM:566`, `810`; `SVC:148-185`, `349-407` | `reimpl/pipeline.py` generates synthetic 100-sample series (strong / degrading / weak / improving) instead of reading a modem; the parse itself is not simulated | ARCHITECTURE_ONLY for the modem I/O; the *downstream* contract is exact |
| 3. Deque → 4-channel vector | `SVC:147-153`, `218-240` | `reimpl/preprocess.py::build_input` (channel order RSRP, RSRQ, SINR, flag; shape `[1,400,1]`) | verified against Java semantics; edge windows covered by `verify_derived.py` |
| 4. Median filter w=5, clipped edges, even-length averaging | `SVC:242-267` | `reimpl/preprocess.py::median_filter`, `median`, `quick_sort` | verified (line-for-line port) |
| 5. TFLite invocation | `LOAD:22-33`, `119-122`; `org.tensorflow.lite.e` | `reimpl/model.py::apply(params, x)` with real weights (`params.npz`) | verified (max_abs ≤ 1.4e-6) |
| 6. Threshold / label | `SVC:86` | `reimpl/pipeline.py` (`p[1] < 0.5 → WEAK`) | verified |
| 7. Output dispatch (broadcast / binder) | `SVC:88-102` | printed to stdout | out of scope (Android glue) |

Exact contracts:

```
input   : float32[1,400,1]   RSRP[0:100] RSRQ[100:200] SINR[200:300] PCIflag[300:400]
                              median-filtered (w=5, clipped), raw units (dBm/dB, 0/1)
output  : float32[1,2]       index1 = p(STRONG); label = "WEAK" if p[1] < 0.5 else "STRONG"
cadence : new window every 1.0 s over the most recent 10.0 s (100 × 100 ms)
```

ARCHITECTURE_ONLY parts and why: the trigger/binder/broadcast plumbing is
Android-only; the `ByteBuffer` structs come from the closed-source
`mirilhook.jar` + Hydra stack, so `pipeline.py` feeds synthetic series that
respect the observed value ranges rather than real QMI responses. Nothing in the
model or preprocessing is approximated — those stages use the extracted weights
and a verified filter port.
