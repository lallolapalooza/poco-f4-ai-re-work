# Data-pipeline integration contract — `com.miui.securitycenter`

App: MIUISecurityCenterGlobal 8.9.4-240516.1.1 (code 40000894) ·
APK sha256 `35eefc4d…c56c6` · classes: `classes.dex` … `classes4.dex`
· ABI arm64-v8a.

Companion: [`report.md`](report.md) (feature map, `assets/model` container decode,
JAX reimplementation) and [`findings.json`](findings.json) (`data_pipelines`).

Confidence tags used in every table: **confirmed** (read in artifact/code),
**inferred** (reasoned from confirmed evidence), **unknown** (not resolvable
statically; what was tried is stated).

Pipeline IDs:

| ID | Pipeline | In this APK? | Model location |
|---|---|---|---|
| P1 | SMS spam/phishing — legacy log-linear cascade | yes, full inference | `assets/model` (+ `assets/dict`, `assets/pattern`) |
| P2 | SMS spam/phishing — new TFLite engine | code yes, **weights absent** | `files/SmsEngineResource/{1.bin,2.bin,v.txt}` (downloaded) |
| P3 | SMS resource acquisition (feeds P1 override + P2) | yes, network client | `resource.zip` from `api.sec.intl.miui.com` |
| P4 | SMS URL/phishing cloud check (post-policy, same broadcast) | yes, network + GuardProvider binder | server-side classifier |
| P5 | Game Turbo AI subtitles / screen translation | **delegated** | none (`com.xiaomi.aiasst.vision`) |
| P6 | Game Turbo UltraClear (video super-resolution) | **delegated** | vendor video toolbox |
| P7 | Video-call beauty / privacy / portrait / pickup | **delegated** | vendor camera HAL |
| — | `assets/redmi_performance_mode/Ai-Model.json` | not AI (Lottie) | — |

---

## 1. Data sources / triggers

| ID | Trigger | Exact entry | Lifecycle | Conf |
|---|---|---|---|---|
| P1 | SMS received while the system SMS app asks Security Center for a verdict. The system SMS app (outside this APK) has already decoded the PDU to `address`+`body` and calls the provider. | `ContentResolver.query(content://antispam/sms_judge, selectionArgs={address, body, simId})` → `com.miui.antispam.db.AntiSpamProvider.query()` case `9` (also `update()` case 9) → `c(String[])` → `new j2.c(address, f.s(ctx,simId), 1, body,false,false,false)` → `d2.b.e(j2.c)` | per message; stateless except loaded whitelists | confirmed (provider + URI matcher); caller code unknown (see §8) |
| P1 gate | `!f4.z.A()` (`ro.miui.google.csp=false`) and `o2.f.E(ctx)` (non-international build, or international only if the default `smsto:` handler is `com.android.mms`) | `AntiSpamProvider.query()` case 9 | per query | confirmed |
| P1 gate 2 | `o2.a.j(ctx, simId)` (antispam enabled for that SIM) then policy priority order | `d2.b.e()` (`PolicyManager`) | per message | confirmed |
| P1 gate 3 | `!miui.os.Build.IS_INTERNATIONAL_BUILD`, else `handleData` returns `null` (policy disabled) | `SmartSmsFilterPolicy.handleData()` line “if (Build.IS_INTERNATIONAL_BUILD) return null” | per message | confirmed |
| P2 | same `j2.a.h`, only when `d9.a` initialised and `d9.a.c/d` decide | `d9.a.b(dir)` → `c()/d()` | per message | confirmed |
| P3 | background update job: `l2.a.d(callback)` (network + not international + not already updating) | `SmsEngineUpdateManager` AsyncTask; `AntiSpamProvider.call("initSmsEngine")` after install | on app start / periodic caller | confirmed |
| P4 | after all SMS policies in `d2.b.e`, `j2.b.d(i10, j2.c)` (URLFilterPolicy) | `com.miui.antispam.policy.b.d()` | per message that contains URLs | confirmed |
| P5 | Game Turbo / toolbox UI switch | `q7.a.j/b` ← `o7.h.onClick`, `l5.c.onClick`, `m7.a`, `g5.i` | on toggle + state query | confirmed |
| P6 | Game Turbo overlay toggle `iv_beauty_face` | `BeautyView.onClick` → `f5.f.J0(bool)` | per toggle | confirmed |
| P7 | Game in foreground / camera availability / screen on | `BeautyService` (`IActivityChangeListener.Stub`, `CameraManager.AvailabilityCallback`, `ACTION_SCREEN_ON/OFF`, `ACTION_USER_PRESENT`) | service lifetime | confirmed |

Boundary fact for P1: **there is no SMS receiver in this APK.** `dex_find` finds
no defined `SmsReceiver`; the strings `pdus`/`createFromPdu` occur only in
`com.miui.networkassistant.utils.TelephonyUtil.getMessagesFromIntent`
(traffic-correction SMS, unrelated), and `sms_judge` occurs only in
`AntiSpamProvider.<clinit>`. PDU decoding therefore happens in the caller
(the MIUI SMS app), and this APK's contract is the three selection args
(`address`, `body`, `simId`).

## 2. Transport and format

| ID | Wire form | Encoding / units | Thread | Conf |
|---|---|---|---|---|
| P1 | Binder `ContentProvider.query` (MatrixCursor with one `int` column `smsBlockType`), then `d2.b` → in-process objects | Java `String` address (already normalised by `o2.f.p`), `String` body (raw), `int` simId (arg is a decimal string, parsed with `Integer.parseInt(strArr[2])`), `int` query type 1=SMS/2=call (`j2.c` ctor arg; `d2.b.e` uses the empty-body flag, not the type, to pick the SMS vs MMS branch) | Binder thread from the provider; `Binder.clearCallingIdentity()` around judging | confirmed |
| P1 model | `assets/model` streamed through `DataInputStream` | big-endian; `int`/`double`/`u16` chars; 680,154 B; sha256 `b282b4ef…2a9f15` | same thread | confirmed (`ir/parse_model.py`) |
| P1 dict | `assets/dict` streamed by `bh.a.h(InputStream)` | big-endian mixed `int`/`double`/6-byte prefix records; 3,639,702 B; 348,982 words; sha256 `f9908624…9cc668` | same thread, **re-parsed per message** | confirmed (`ir/parse_dict.py`) |
| P1 pattern | `assets/pattern` read as text by `ah.c.a(ctx)` | JSON, 19 named regex features; 5,568 B; sha256 `f25e73d1…2bf595` | parsed once per `ah.d` instance | confirmed |
| P2 input | `float[1][128]` from `c9.a.a(String)` | 128 float32; vocabulary TSV `v.txt` (`token\tvalue`) | same thread | confirmed (code) / unknown (values) |
| P2 model | `File(dir+"/1.bin")`, `File(dir+"/2.bin")` | TFLite flatbuffer (assumed from `org.tensorflow.lite.b`), I/O `float[1][128]` → `float[1][1]` | same thread | inferred (code shape only) |
| P3 | HTTPS GET `https://api.sec.intl.miui.com/GuardProviderV1/GetResourceZipFile` (signed params) → JSON `data.fileUrl`; then `DownloadManager` GET of `fileUrl` → ZIP | `resource.zip` MD5 pinned `6206c9f17a64246498b52e117d0cff1e`; version string `2.1.2` | `AsyncTask` on `THREAD_POOL_EXECUTOR`; unzip on a single-thread `ExecutorService` | confirmed |
| P4 | binder to `com.miui.guardprovider` service + HTTPS | `IURLScanServer.b3("AVL", url)` → int; then HTTP GET redirect + signed JSON POST | caller thread; guard-provider `bindService` waits up to 8 s (`CountDownLatch.await(8,SECONDS)`) | confirmed |
| P5 | `startService` with explicit `ComponentName` + extras; `ContentResolver.query` for state | `Bundle` strings only (`from`, `extra`, `floatingWindowType`); state column `value` int | UI thread | confirmed |
| P6/P7 | `Settings.System`-style property writes via `p1.d(name,value)` + local `bindService` to `DockWindowManagerService`; remote `IGameBoosterWindow.Stub` | `persist.vendor.vcb.enable`, `persist.vendor.vcf.*`, `persist.vendor.camera.facetracker.*` (strings) | UI/service thread | confirmed |

## 3. Preprocessing

### P1 (legacy engine) — in-APK, confirmed

1. `j2.c.<init>` — sender normalisation: `o2.f.p(raw)`, then `o2.f.I()` →
   `PhoneNumberUtils.PhoneNumber.parse(str).getNormalizedNumber(false,true)`
   (special case: `[a-zA-Z]*-[a-zA-Z]*` keeps the `-suffix`). Country code from
   `CountryCode.getNetworkCountryCode()`; for `"62"` (Brazil) toggles a leading
   `"0"` (`StatManager.PARAMS_SWITCH_OFF == "0"`, inlined constant).
2. `ah.b.<init>(number, body)` — strips a `+86` prefix only when
   `len>3 && indexOf("+86")==0`; `f506i = dh.a.d(number)` = sender category
   index 0..7 (`Common, 100+5, 106Short, 106Common, 106Long, 9Pre5, Other, Null`);
   `f501d = ah.h.c(body)`.
3. `ah.h.c` — trailing-whitespace/NUL trim; split on `, . ; : ! ，。；、：！`;
   for each piece `ah.h.a` keeps only "obfuscation-like" characters when the
   piece has ≥3 such chars and their density ≥50 % (de-obfuscation of
   separator-laced spam), then re-joins with the original separators.
4. `dh.e.e(ah.b)` — builds the pieces list `b.l` (start = whole body, then
   `dh.j`/`dh.b`/`dh.c` each split it) and the 9 rule features `b.m`:
   `dh.j.a()=1`, `dh.b.a()=1`, `dh.c.a()=7` (bank card, phone, mobile, 400,
   range, tag, "Confus" counters).
5. Tokenisation `dh.e.f` → `a(ah.b)`: every piece is expanded by the
   `assets/dict` segmenter (`ah.g.a` → `bh.a.c`), a candidate is kept if it is
   all-CJK (`ah.i.a`) **or** present in the dictionary (`ah.g.c`), collected in
   a `TreeSet` (dedup, sorted) → `b.i()` = the exact token list; `b.h()` =
   ordered `List<String>`.
6. Feature vector per stage: `dh.e.f` packs
   `int[9 rules] ++ [char-dict counts] ++ [length≥threshold]`; `dh.i.b`
   increments one slot **per occurrence** of each dictionary char,
   `dh.h.b` sets one slot to 1 when `body.length() ≥ n_chars2`.
   `9 + n_chars + 1 == n_feat` for all five stages (68/23/10/10/16).
7. Flags before scoring: `ah.e.f` — `ah.i.b(url)` sets bit 1 (URL present),
   `ah.d.d()` (phish list, asset absent) sets bit 2; `ah.e.a` adds
   `ch.d.e(ah.b)` regex-driven bits from `assets/pattern`; `ah.e.g` shifts all
   three thresholds by `((active-inactive) * (thr[0]-thr[1])) / 4`.

### P2 (new engine) — confirmed code, model files unknown

1. `c9.a.a(String)`: remove `\n \r \t`, spaces, `\s+`, `"` and `'`, lowercase;
   `String.getBytes()` (platform default UTF-8); walk bytes, take UTF-8 lead
   byte (≥0xFC→6, ≥0xF8→5, ≥0xF0→4, ≥0xE0→3, ≥0xC0→2) and slice that many
   bytes → code-point tokens; for each token **in message order**, if it exists
   in `v.txt` append its float to slot `i` and `i++`; unknown tokens are simply
   dropped (no placeholder); remaining slots zero-filled (`Arrays.fill`).
2. No case normalisation beyond `toLowerCase()`, no stemming, no padding token,
   no attention mask — the models see a fixed 128-float bag-of-code-points.

### P3 (resource) — confirmed

Zip-slip guard: each entry's canonical path must start with the target dir
(`j2.a.i`), else `SecurityException("unsafe operation.")`; existing file
deleted, 64 KiB copy loop; `l()` revalidates every entry length against
`ZipEntry.getSize()` before re-init.

### P5/P6/P7 — no preprocessing in this APK

No frames, audio or text are prepared here; only app/activity identity,
booleans and channel names are passed to the delegate (see §4).

## 4. Model invocation

| ID | Engine / session | Model path & identity | I/O binding | Cadence | Conf |
|---|---|---|---|---|---|
| P1 | none — pure Java evaluator `ah.d.h(ah.b, Context)`; `DataInputStream` over the asset | `assets/model`, 680,154 B, sha256 `b282b4ef…2a9f15`; 5 stages | reads weights per stage, accumulates two `double` scores, no tensor runtime | **per SMS**, full re-parse | confirmed |
| P1 dict | `bh.a` trie built by `bh.a.h(InputStream)` | `assets/dict`, 3,639,702 B | `ah.g.d(ctx)` inside `ah.d.k(ctx)`; `dh.e.f`→`a()` reuses that same `ah.g` instance | **per SMS** (one full 3.6 MB parse, then discarded) | confirmed |
| P2-A | TFLite via `org.tensorflow.lite.b` = `NativeInterpreterWrapperExperimental(file.getAbsolutePath(), null)` — default `Interpreter.Options`, **CPU** (no NNAPI/XNNPACK delegate constructed) | `2.bin`, `a9.a` "ImportanRecognizer" | `Interpreter.run(input float[1][128], output float[1][1])` | per SMS | confirmed (wrapper) / unknown (graph) |
| P2-B | same | `1.bin`, `a9.c` "UnlawRecognizer" | same | per SMS | confirmed (wrapper) / unknown (graph) |
| P2-C | none — `a9.b` + `b9.b` hard-coded regex rules | 7 `Pattern`s for senders `10086`, `95555` and the `"0"` fallback (`10086`: monthly-0.0-yuan; `95555`: salary credit; fallback: JD shipping/pickup, Xiaomi visitor, WeChat/Alipay payment, generic e-mail domain regex) | `Pattern.matcher(body).matches()` | per SMS | confirmed |
| P3 | none (transport) | `resource.zip`, MD5 `6206c9f1…0cff1e`, version `2.1.2` | — | update job | confirmed |
| P4 | remote HTTP classifier + GuardProvider binder | server-side AVL/URL category | URL string in, int code out | per URL in the SMS | confirmed (client) / unknown (server) |
| P5 | remote service `com.xiaomi.aiasst.vision/.control.translation.AiTranslateService` | not in APK | `Intent` extras only | on toggle | confirmed |
| P6 | vendor video toolbox, driven by `persist.vendor.vcb.enable` | not in APK | property write | on toggle | confirmed |
| P7 | vendor camera/video toolbox, driven by `persist.vendor.vcf.*` / `persist.vendor.camera.facetracker.*` | not in APK | property writes + overlay binder | per activity change | confirmed |

Native: only `lib/arm64-v8a/libtensorflowlite_jni.so` (TFLite 2.8.4, 59 `Java_*`
exports) executes a model. The JNI `run` thunk is
`Java_org_tensorflow_lite_NativeInterpreterWrapper_run` @ `0x13234`
(43 instructions, `native/tflite_run_jni.s`); it validates the input/output
array arguments and branches into internal helpers at `0x9db90`, `0x11678` and
`0x113a8` (statically linked, stripped TFLite — no symbol names). No other
`.so` in the APK loads a model (all 11 were string-scanned).

### 4.1 Model matrix (required columns)

| Model | Source | Transport | Preprocess | Invoke | Postprocess | Consumer |
|---|---|---|---|---|---|---|
| `assets/model` (P1) | APK asset, sha256 `b282b4ef…2a9f15`, 680,154 B | `ah.c.b(ctx,"model")` → `DataInputStream` (big-endian) | §3 P1 steps 2–6 (normaliser, dict tokens, 9 rules + char dict + length, flags) | `ah.d.h` per-stage dot products, 5-stage cascade, no tensor runtime | thresholds + bias + feedback shift + vote (§5) | `ah.a.a` verdict 0/1/2 → `SmartSmsFilterPolicy` block type 4/8 |
| `assets/dict` (P1 support) | APK asset, sha256 `f9908624…9cc668`, 3,639,702 B | `ah.c.b(ctx,"dict")` → `bh.a.h` | none (loaded as trie, 6-byte prefix records + word table) | `bh.a.c` DAG segmentation per piece | token list (CJK or in-dict) | `dh.e.a` → `ah.b` token set |
| `assets/pattern` (P1 support) | APK asset, sha256 `f25e73d1…2bf595`, 5,568 B | `ah.c.a(ctx)` text read | JSON parse (`ch.d.d`) | regex match per feature (`ch.d.e`) | 32-bit flag word | `ah.e.g` threshold shift + `ah.e.b` early exit |
| `2.bin` (P2 "important") | downloaded, `files/SmsEngineResource/` | TFLite file on disk → `NativeInterpreterWrapperExperimental(path,null)` | `c9.a.a` → `float[1][128]` | `Interpreter.run(float[1][128] → float[1][1])` | `score > 0.6` | `d9.a.c` → `j2.a.h` returns 0 |
| `1.bin` (P2 "unlaw") | downloaded, same dir | same | same `float[1][128]` | same | `score > 0.4` | `d9.a.d` → `j2.a.h` returns 2 |
| `v.txt` (P2 vocab) | downloaded, same dir | `FileInputStream` + `BufferedReader` | TSV `token\tfloat`, trimmed key | map lookup (`Map<String,Float>`) | zero-pad to 128 | `c9.a` vector |
| URL/phishing classifier (P4) | remote, `api.sec.intl.miui.com` + `com.miui.guardprovider` | HTTPS + binder `IURLScanServer.b3("AVL", url)` | URL extract/normalise/whitelist | server-side | `urlCategoryId` → block bit 128 | `d2.b.e` verdict |
| ASR/translation (P5) | remote app `com.xiaomi.aiasst.vision` | `startService` intent extras + `content://…/subtitleState` | none here | remote | remote | floating subtitle window |
| SR/beauty (P6/P7) | vendor video toolbox / camera stack | `persist.vendor.vcb.*` / `persist.vendor.vcf.*` / `persist.vendor.camera.facetracker.*` | none here | vendor | vendor | camera preview / encoded video |

## 5. Output postprocessing

### P1 scoring (confirmed, `ah/d.java::h`)

```
for stage i in 0..4:                       # read in file order
  s[k] = Σ_feat  (f>0 ? w[k][f] : log1p(-exp(w[k][f])))       k=0,1
       + Σ_tokens( hit ? tw[k] : log1p(-exp(tw[k])) )
       + Σ_patterns( contains ? pw[k] : log1p(-exp(pw[k])) )
       + cat_bias[k][ dh.a.d(sender) ]
  d = s[0] - s[1]
  thr' = ah.e.g(thr, b)                    # ±((a-b)*(thr0-thr1))/4
  if d <  thr'[1] -> class 0, break
  if d >  thr'[0] -> class 1, break
  vote[i] = d - thr'[2]; positive++
if broke: return class
else: return (positive > negative) ? 1 : (positive < negative) ? 0 : (sum(vote) < 0 ? 0 : 1)
```

Stage thresholds `(upper, lower, centre)`:
`(10.100, -9.611, -0.082)`, `(5.453, -5.453, 0.647)`,
`(13.209, -11.360, -1.940)`, `(5.208, -5.208, -2.044)`,
`(3.543, -3.543, 0.165)`; sender-category bias is 2×8 doubles per stage.
Weights are log-probabilities (`ah.i.g(x)=log(1-exp(x))`).

Verdict mapping in `ah.a.a`: blocked + phish flag bit 2 → **1**, blocked → **2**,
allow → **0**. `SmartSmsFilterPolicy.handleData` turns those into block types
**1 ⇒ 8 (phishing), 2 ⇒ 4 (spam)** in `policy.a.C0115a`, returned to
`d2.b.e` (`i10`).

### P2 (confirmed code, unknown model)

`a9.a`: `score = out[0][0]` (logcat `"important score:"`), important iff
`score > 0.6f`. `a9.c`: logcat `"unlaw score:"`, unlawful iff `score > 0.4f`.
No softmax/sigmoid is applied in Java — the single output is used as a
probability (inferred). `d9.a.c` (important gate) returns `true` if any
`sender`-keyed rule (or the `"0"` fallback rule) matches, else the
`2.bin` score > 0.6. `d9.a.d` (unlaw) short-circuits on empty / pure-number
(`^-?[0-9]+(\.[0-9]*)?`) / 1–500 char alphanumeric-without-space bodies, then
`2.bin` > 0.6 ⇒ allow, else `1.bin` > 0.4 ⇒ block.

### P3/P4/P5/P6/P7

No numeric postprocessing in this APK for P5–P7 (delegates return rendered
output / apply properties). P4: the cloud POST returns `urlCategoryId`; `b.b(url)` maps `0 → 2`, `1 or 2 → 1`,
`-1 → 0`, and `b.c()` then maps `1 → 2` (block) / `2 → 0` (allow); a hit ORs
bit `128` into the existing block type (`b.e(i10) = i10 OR 128`).

## 6. Consumer / action

| ID | What the verdict does | User-visible effect | Conf |
|---|---|---|---|
| P1/P2 | `d2.b.e` returns `int` block type → `AntiSpamProvider.query` returns `MatrixCursor{"smsBlockType"}` → the calling SMS app moves/quarantines the message (blocked conversation `ExtraTelephony.MmsSms.BLOCKED_CONVERSATION_CONTENT_URI`, see `o2.f.d`) | message lands in the blocked list instead of the inbox | confirmed for the in-APK half; the move itself is the caller's (unknown code) |
| P1/P2 log | if `ExtraTelephony.getRealBlockType(type) >= 3`, `o2.f.a(ctx, number, type, keyword)` asynchronously inserts/updates `ExtraTelephony.FirewallLog.CONTENT_URI` with `number, read=1, type=2, reason=blockType, data1=keyword` (`o2.f$c.doInBackground`) | entry in Security Center → AntiSpam → block log; sync/analytics counters | confirmed |
| P3 | installs `files/SmsEngineResource/*`; `c.c(true)` forces the legacy assets; increments version in `SmsEnginePreference` | next messages use the updated engine | confirmed |
| P4 | raises the block bit to ≥128 and logs the URL via analytics | URL-containing SMS blocked as risky | confirmed |
| P5 | service shows the floating subtitle window and OCR/translate | on-screen subtitles in the delegate app | confirmed (dispatch) |
| P6 | `persist.vendor.vcb.enable=true` | video toolbox upscales game/video output | confirmed (dispatch) |
| P7 | `persist.vendor.vcf.*`, `persist.vendor.camera.facetracker.*`; overlay controls | beauty/privacy/portrait/pickup in camera path | confirmed (dispatch) |

## 7. Failure and fallback

| ID | Failure | Behaviour | Conf |
|---|---|---|---|
| P1 | `assets/model`/`dict` open error | `ah.c.b` logs `load Model file … failed` and returns `null`; `ah.d.h` throws → `j2.a.h` catches `Exception` → returns **0 (allow)**; new-engine path still evaluated first where available | confirmed |
| P1 | policy disabled | `IS_INTERNATIONAL_BUILD` ⇒ `handleData` returns `null`; SIM antispam off ⇒ `d2.b.e` returns 0 | confirmed |
| P1 | dict parse failure | `ah.g.d` logs `init failed`, dictionary null → NPE in `dh.e.f` propagates to `j2.a.h` → allow | inferred |
| P2 | any `IOException`/`RuntimeException` constructing engines | `d9.a` sets `f44135e=false`; `c()`/`d()` return `false` (fail-open) | confirmed |
| P2 | missing `1.bin`/`2.bin`/`v.txt` | `new org.tensorflow.lite.b(File)` throws → same as above | confirmed |
| P1→P2 | old engine is authoritative when the SMS app is old | `if (mmsVersion < 11000249 && ah.a.a(...) > 0) → block`; else new engine | confirmed |
| P1 | false positive guard | if blocked and sender category `Common` and `ah.f.a(ctx, number)` finds an existing SMS/call history → **unblock** | confirmed |
| P4 | GuardProvider/network failure | `c()` returns `-1`, `d()` returns `null` → verdict unchanged | confirmed |
| P5 | provider/service missing | `h7.c0.m` false ⇒ no toggle; `startService` wrapped in try/catch and only logs | confirmed |
| P6/P7 | property unsupported | `f5.f.F()`/`G()` read `persist.vendor.vcb.ability` / `persist.vendor.vcb.enable`; absent ⇒ feature hidden | confirmed |

## 8. Confidence and open questions

| Claim | Confidence | Evidence | What would settle it |
|---|---|---|---|
| The SMS app decodes PDUs and calls `content://antispam/sms_judge` with `{address, body, simId}` | inferred (contract confirmed; caller absent) | `AntiSpamProvider` URI matcher + `query` case 9; `sources/classes/com.miui.antispam.db.AntiSpamProvider.java:913-918,1311-1315`; `evidence/xref_sms_judge.jsonl` (string only in this class) | decompile `com.android.mms` of the same ROM |
| The `smsBlockType` return is used by the caller to quarantine the message | inferred | cursor column name + `o2.f.d` reading `BLOCKED_CONVERSATION_CONTENT_URI` | same |
| `1.bin`/`2.bin` are TFLite and take `float[1][128]` | inferred | `org.tensorflow.lite.b` construction in `a9.a`/`a9.c`; `c9.a` returns `float[1][128]` | fetch `resource.zip` (no network) |
| `v.txt` maps code-point → embedding/weight float; unknown tokens dropped | confirmed (mechanics), unknown (semantics) | `sources/classes/c9.a.java` | fetch `v.txt` |
| `StatManager.PARAMS_SWITCH_OFF == "0"` | confirmed | inlined `ldc "0"` in `j2.c.a`, `b9.b.<init>`, `a9.b.a`, `d9.a.d` (`evidence/xref_const_0.jsonl`) | — |
| Threshold adjustment `±((a-b)*(t0-t1))/4` | confirmed | `ah.e.g`, `ch.d.a/b` | — |
| The 5-stage cascade votes per §5 | confirmed | `ah.d.h`, `reimpl/model.py` verify | — |
| No NNAPI/XNNPACK delegate is constructed | confirmed | `xref_tflite_clean.jsonl` (only `a9.a`, `a9.c`, `d9.a` touch TFLite); `org.tensorflow.lite.b` passes `null` options | — |
| AI subtitle inference happens in `com.xiaomi.aiasst.vision` | confirmed | `q7.a` intent; `h7.c0.m` version check | — |
| UltraClear/beauty inference happens in vendor code | confirmed (dispatch), unknown (model) | `f5.f.J0`, `p7.b.A`; no SR/beauty model asset or `.so` in APK | vendor `.so`/BSP |
| `ah.e.b` early-exit bit is a strong block signal (`C.BUFFER_FLAG_FIRST_SAMPLE`) | confirmed (constant name), inferred (value) | `ah.e.b`, `ch.d` bit allocation | resolve `com.google.android.exoplayer2.C` constants |

## 9. Reimplementation mapping

| Stage | Reproduced by | Contract | Status |
|---|---|---|---|
| P1 header + stage parse | `ir/parse_model.py` → `ir/model_ir.json`, `ir/weights.npz` | 5 stages, real weights | verified (walks to EOF) |
| P1 feature vector | `reimpl/model.py::build_masks` | 9 rule bits + char-dict counts + length bit | verified (JAX vs NumPy) |
| P1 scoring + cascade | `reimpl/model.py::stage_scores/classify` | `apply(params, x)` per stage; `log1p(-exp(w))` for absent items | verified, agreement < 1e-9 |
| P1 normaliser/segmenter | **not reimplemented** — inputs are supplied as masks | `assets/dict` trie + `ah.h.c` | ARCHITECTURE_ONLY (documented in `reimpl/README.md` limitation) |
| P2 vectorizer | described, not run (no `v.txt`) | 128-float bag-of-code-points | unknown / ARCHITECTURE_ONLY |
| P2 models | **not reimplemented** (weights absent) | `float[1][128] → float[1][1]`, thresholds 0.6/0.4 | ARCHITECTURE_ONLY |
| P3–P7 | out of scope — transport/dispatch only | — | n/a |

`cd reports/miui_securitycenter && /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python reimpl/model.py models/assets_model`
exits 0 and prints per-stage shapes and both discriminative probes.
