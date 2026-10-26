# MIUI Security Center (`com.miui.securitycenter`) — AI/ML subsystem

APK: `product/priv-app/MIUISecurityCenterGlobal/MIUISecurityCenterGlobal.apk`
size 93,987,501 B, sha256 `35eefc4d8bbf03bbf0b759199ab258b5113c4dfdba59188fa8136c2b187c56c6`,
version 8.9.4-240516.1.1 (code 40000894), targetSdk 33, ABI arm64-v8a only.

Confidence tags: **confirmed** = read from artifact/code; **inferred** = reasoned
from evidence; **unknown** = not resolvable statically here.

---

## 1. Headline

1. The only on-device model shipped in this APK is `assets/model` (680,154 B).
   It is **not** TFLite/SNPE/MACE/encrypted — it is a **custom big-endian binary
   containing a 5-stage cascade of log-linear (Naive-Bayes style) SMS
   spam/phishing classifiers** with 8000/8000/5752/4759/2573 token weights,
   414/292/193/139/171 substring-pattern weights, per-stage character-set
   features, a message-length feature, 9 rule features and an 8-way
   sender-number bias table. **The container was decoded completely and parses
   to the last byte** (`ir/parse_model.py`, `ir/model_ir.json`).
2. The model serves **SMS spam/phishing filtering** (`SmartSmsFilterPolicy`),
   not the Game Turbo features. Loader: `ah.c.b(Context,"model")`
   (classes3.dex) called from `ah.d.h(ah.b,Context)`.
3. TFLite (`lib/arm64-v8a/libtensorflowlite_jni.so`, TFLite **2.8.4**) *is*
   used, but by a **second, newer SMS engine** (`d9.a` / `a9.a` / `a9.c`) that
   loads `1.bin` and `2.bin` from a **downloaded** `SmsEngineResource`
   directory (`resource.zip` from `api.sec.intl.miui.com`). Those model files
   are **not in the APK** (`unknown`, network acquisition out of scope).
4. Game Turbo "AI" features (AI subtitles, UltraClear, video-call beauty /
   privacy / portrait / pickup) are **UI + allow-list configuration only** in
   this APK; the inference runs in other processes
   (`com.xiaomi.aiasst.vision`, vendor camera/video-toolbox via
   `persist.vendor.vcb.*`). `assets/redmi_performance_mode/Ai-Model.json` is a
   **Lottie animation**, not a model.

---

## 2. Inventory

`tools/apk_inventory.py … --extract-models --extract-libs` →
`inventory/inventory.md`, `inventory/inventory.json`.

| Item | Value |
|---|---|
| dex files | 4 (`classes.dex` 8.3 MB … `classes4.dex` 8.1 MB) |
| native libs | 11, all `lib/arm64-v8a/` |
| AI-relevant lib | `libtensorflowlite_jni.so` 1,700,448 B sha256 `3e3eee41…62c6f0` |
| model assets | `assets/model`, `assets/dict`, `assets/pattern` (see §4/§5) |
| DEX marker counts | `Lorg/tensorflow/lite` ×16, `mibrain` ×1 (`com.xiaomi.mibrain.speech`) |

Zip entry facts (`evidence/` transcript):

| asset | uncompressed | stored method | header off | sha256 |
|---|---:|---|---:|---|
| `assets/model` | 680,154 | deflate (8) | `0x4882ed1` | `b282b4ef…2a9f15` |
| `assets/dict` | 3,639,702 | deflate | `0x463fdf9` | `f9908624…9cc668` |
| `assets/pattern` | 5,568 | deflate | `0x48b7c32` | `f25e73d1…2bf595` |
| `lib/arm64-v8a/libtensorflowlite_jni.so` | 1,700,448 | stored (0) | `0x718be8` | `3e3eee41…62c6f0` |

`assets/model` file magic is `00 00 00 01 00 00 00 00` (u32 version=1,
u32 reserved=0) — the pre-scan's "not TFL3" observation is correct and the file
is not a flatbuffer at all.

---

## 3. AI feature map (entrypoints → models)

| # | Feature | Entrypoint(s) in this APK | Model actually used | Confidence |
|---|---|---|---|---|
| 1 | **SMS phishing/spam blocking — legacy engine** | `com.miui.antispam.policy.SmartSmsFilterPolicy.handleData` → `shouldFilter` → `j2.a.h` (`SmsEngineHandler.getSMSJudgeResult`) → `ah.a.a` → `ah.d.h` | **`assets/model`** (+ `assets/dict` tokenizer, `assets/pattern` feature flags) | confirmed |
| 2 | SMS filtering — new engine | same `j2.a.h`, uses `d9.a` (`SmsRecognizer`) → `a9.a` (`ImportanRecognizer`, `2.bin`), `a9.c` (`UnlawRecognizer`, `1.bin`), `c9.a` 128-dim vectorizer (`v.txt`), `a9.b`/`b9.b` | TFLite models **downloaded** in `resource.zip` (`l2.a`, `SmsEngineUpdateManager`) — **not in APK** | confirmed code / models unknown |
| 3 | Game Turbo **AI subtitles / screen translation** | `q7.a` (`AiSubTitleUtils`) `i/j` → `Intent` to `com.xiaomi.aiasst.vision/.control.translation.AiTranslateService`, `floatingWindowType=startAiSubtitlesWindow`; state via `content://com.xiaomi.aiasst.vision/subtitleState`; support check `h7.c0.m` (requires `com.xiaomi.aiasst.vision` ≥413) | none local | confirmed |
| 4 | Game Turbo **UltraClear** (video super-resolution toggle) | config list `assets/ultraclear_support_activity_list.json` loaded by `f5.f.z(...)`/`f5.f.H0`; cloud override `p7.b.A` → `f.H0` | inference in vendor video toolbox (`persist.vendor.vcb.enable`, `f5.f.J0`) | confirmed (dispatch), unknown (model) |
| 5 | Video-call **beauty / privacy / portrait / pickup** | `BeautyService` (foreground monitor: `beauty_action_monitor_activity` broadcast, `ACTION_SCREEN_ON/OFF/USER_PRESENT`, `CameraManager.AvailabilityCallback`, `IActivityChangeListener`, `TaskStackListener`; binds `DockWindowManagerService`/`IGameBoosterWindow` for the overlay), `BeautyView`/`BeautySoftLightView`, `BeautySettingsActivity`, `conversation.ConversationSettingsActivity`; allow-lists `beauty_face_support_activity_list.json`, `beauty_light_default_support_list.json`, `privacy_single/all_support_activity_list.json`, `protrait_support_activity_list.json`, `pickup_support_activity_list.json` (all via `f5.f.z`) | vendor-side; no model here | confirmed (dispatch) |
| 6 | "AI performance mode" | `a8.r0.y` loads `assets/redmi_performance_mode/Ai-Model.json` | **Lottie JSON animation** (`"v":"5.8.1","fr":60,"ip":61,"op":176 …"nm":"lottie"`), not a model | confirmed |

Negative results worth recording: there is **no** model loader in any of the 11
native libs (`strings` of every `.so` has no `AAsset*`/model path; only
`libtensorflowlite_jni.so` contains TFLite text), and no Java `const-string`
equal to `"assets/model"`; the asset is opened as bare `"model"` by `ah.c.b`.

---

## 4. `assets/model` — container decoded (confirmed)

### 4.1 Reader in the APK

`ah/c.java` (classes3.dex, log tag `ah.c`):

```java
public static final String[] f517b = {"dict", "model", "pattern", "phish"};
public static InputStream b(Context ctx, String name) {
    boolean exists = /* name in ctx.fileList() */;
    return (!exists || f518c) ? ctx.getAssets().open(name)      // APK asset
                              : ctx.openFileInput(name);        // downloaded override
}
```

So `assets/model` can be **shadowed at runtime** by a file named `model` pushed
into the app's files dir by the resource-update path (`j2.a` unzips
`resource.zip` into `files/SmsEngineResource`, `l2.a` downloads it).

`ah/d.java::h(ah.b, Context)` is the evaluator; `dh/e.java` (`RuleManager`) and
`dh/{g,h,i}.java` define the pre-model feature layout.

### 4.2 Format identification (what it is **not**)

* `carve_models.py --types tflite,zip,caffe,flatbuffer --scan-flatbuffers` →
  **"no model candidates found"** (`evidence/carve_model.txt`): no `TFL3`
  identifier, no ZIP/DLC local header + EOCD, no Caffe NetParameter, no
  identifier-less flatbuffer root.
* Entropy 7.178 bit/byte overall (0x0: 7.09 … 0xa0000: 6.37), 4.7 % zero bytes,
  but the file deflates to **29.8 %** (`evidence/entropy_model.txt`) — high byte
  entropy with strong long-range repetition, i.e. packed numeric arrays (many
  identical byte patterns such as `c01c1f252face8ab` recurring 1330×), **not
  ciphertext**: an encrypted blob does not compress to 30 %.
* No native loader exists: all 11 `lib/arm64-v8a/*.so` were string-scanned; the
  only TFLite/model text is inside `libtensorflowlite_jni.so`, and no library
  imports `AAsset*`. The asset is opened only by Java (`ah.c.b`).
* The decisive evidence is the reader itself: `ah.d.h` consumes exactly the
  structure reproduced below, and the structure walks to the final byte.

### 4.3 Binary layout

All integers/doubles are **big-endian**. Verified offsets come from
`ir/parse_model.py` and `evidence/parse_model.txt`.

```
0x000  u32   version            = 1
0x004  u32   reserved           = 0
0x008  u32   n_models           = 5          (cascade stages, read in order)
stage i (offsets below for i = 0; see table for all):
       i32   n_feat
       i32   n_chars1 ; n_chars1 × u16be      (dh.i character dictionary)
       i32   n_chars2                         (dh.h minimum-length, no payload)
       f64   weights[2][n_feat]               INTERLEAVED: w0[0],w1[0],w0[1],…
       i32   n_tokens
         n_tokens × { u16be chars … 0x000A ; f64 w0 ; f64 w1 }
       i32   n_patterns
         n_patterns × { u16be chars … 0x000A ; f64 w0 ; f64 w1 }
       f64   cat_bias[2][8]                   INTERLEAVED; index = dh.a.d(sender)
       f64   thr[3]                           {upper, lower, centre}
```

`ah/d.java` reads the weight pairs as
`dArr3[0][i] = readDouble(); dArr3[1][i] = readDouble();` — i.e. interleaved.
The same holds for `cat_bias` (`dArr4[0][i]`, `dArr4[1][i]`). Token/pattern
weights are sequential pairs, thresholds are 3 sequential doubles.

### 4.4 Stage table (from `ir/model_ir.json`, verified walk ends exactly at EOF)

| stage | offset–end | n_feat | char dict | len thr | tokens | patterns | thresholds (up, lo, centre) |
|---|---:|---:|---:|---:|---:|---:|---|
| 0 | `0xc`–`0x2e122` | 68 | 58 | 39 | 8000 | 414 | 10.100, −9.611, −0.082 |
| 1 | `0x2e122`–`0x5b852` | 23 | 13 | 18 | 8000 | 292 | 5.453, −5.453, 0.647 |
| 2 | `0x5b852`–`0x7c1ac` | 10 | 0 | 24 | 5752 | 193 | 13.209, −11.360, −1.940 |
| 3 | `0x7c1ac`–`0x96f84` | 10 | 0 | 23 | 4759 | 139 | 5.208, −5.208, −2.044 |
| 4 | `0x96f84`–`0xa60da` | 16 | 6 | 104 | 2573 | 171 | 3.543, −3.543, 0.165 |

Feature-vector layout per stage (`dh.e.f` + `dh.i.a` + `dh.h.a`):

```
[0..8]           9 rule features  (dh.j ×1 + dh.b ×1 + dh.c ×7)
[9 .. 9+n_chars) character-presence for the stage's character dictionary
[last]           (len(normalised text) >= n_chars2) ? 1 : 0
9 + n_chars + 1 == n_feat   (checked for all 5 stages)
```

Character dictionary stage 0 = code points
`13, 36, 96, 123, 125, 183, 924 … 0xff5e` (punctuation, Greek/Cyrillic letters,
full-width forms) — i.e. "weird / obfuscated character" indicators.

### 4.5 Scoring semantics (`ah/d.java::h`)

```
for each stage:
  s[k] = Σ_features ( feat ? w[k][f] : log1p(-exp(w[k][f])) )
       + Σ_tokens   ( hit  ? tw[k]   : log1p(-exp(tw[k]))   )
       + Σ_patterns ( hit  ? pw[k]   : log1p(-exp(pw[k]))   )
       + cat_bias[k][ dh.a.d(sender) ]
  thr' = ah.e.g(thr, b)          # ±((a-b)*(thr0-thr1))/4 from ch.d feedback
  d = s[0] - s[1]
  if d < thr'[1]  -> class 0 (not spam), stop
  if d > thr'[0]  -> class 1 (spam),     stop
  else            -> vote += d - thr'[2]; continue
after all stages: majority vote of the accumulated votes
```

`ah/i.java::g(x) = Math.log(1.0 - Math.exp(x))` confirms the weights are
**log-probabilities** (all extracted weights are negative, −10.32…−0.002).
`dh/a.java` category table: `{"Common","100+5","106Short","106Common","106Long",
"9Pre5","Other","Null"}`; `dh.a.d(number)` classifies the sender number.

Token matching is exact string equality against the message's token set
(`ah.d.j`), patterns are substring `contains` on the normalised body
(`ah.d.i`); the normaliser is `ah.h.c` (trims, splits on CJK/ASCII punctuation,
de-obfuscates full-width digits).

### 4.6 Sanity check of the decoded model

`reimpl/model.py` (real weights) on synthetic inputs:

| input | stage-0 log-odds `s0−s1` | verdict |
|---|---:|---|
| 30 most spam-indicative tokens (`真人`, `百家乐`, `娱乐城`, `代开`, `首存`, `返水` …) | **+136.83** (thr 10.100) | spam (1) |
| 30 most ham-indicative tokens (`嗯`, `好的`, `呵呵`, `回去`) | **−131.43** (thr −9.611) | not spam (0) |

The token tables are unmistakably Chinese SMS-spam lexicons (gambling, loan,
real-estate, medical spam), which independently confirms the model's purpose.

---

## 5. Supporting assets in the same pipeline

### 5.1 `assets/dict` — word segmenter (decoded, `ir/parse_dict.py`)

`bh/a.java::h(InputStream)` reads: `i32 version=1, i32 0`, `f64[4]`
char-class weights, `f64[4][4]` transition weights, `i32[4]` prefix-group
counts `(6857,7439,6409,14519)`, `i32 n_lengths=16`, `i32[16]` word counts,
`i32 total_freq=60,101,983`, `i32 min_freq=2`, then a 211,344-byte prefix table
(6-byte records) followed by the word table: per length L, `count[L]` records of
`L × u16be chars + i32be frequency`.

Parsed result: **348,982 words** (length 1…16, e.g. `一/217830`, `不/360331`,
`侵华日军南京大屠杀遇难同胞纪念馆/3`), walks exactly to EOF
(`evidence/parse_dict.txt`, `ir/dict_ir.json`). Used by `ah.g.d(context)` →
`bh.a` for DAG segmentation, which produces the token list consumed by the
model.

### 5.2 `assets/pattern` — auxiliary pattern features (JSON, `ch.d`)

19 named features (`Fei_xin`, `sms_body_length`, `bracket_title_not_personal_address`,
`important_info_body`, `linking_up`, `fa_piao`, `bank_card_remittance`, `phish`,
`subscription`, `official_phone`, `xiaomi_related`, `map_guide`, …) with regex
rules, `sms_type` (1=address rules, 2=body rules, 0/absent = assist) → `ch.d`
builds a 32-bit flag word (`ch.d.e`), which `ah.e.f` stores in `ah.b` flags and
`ah.e.g` uses to shift the model thresholds. `ch.d.d` parses the JSON; the
`"name"`-indexed bit weights come from `ch.c.b()`.

### 5.3 `assets/phish` — **not shipped**

`ah.d.d()` opens `"phish"` (a newline list of phishy URLs) to set flag bit 2.
The asset is absent from the APK and `ah.c.b` returns null → the phish list is
inactive unless delivered inside `resource.zip`. (`unknown` content.)

---

## 6. End-to-end pipeline: SMS → model → block decision (confirmed)

> **Correction (pipeline patch, §11.1):** the first two boxes below are wrong —
> the in-APK entry is `com.miui.antispam.db.AntiSpamProvider.query()` case 9
> (`content://antispam/sms_judge`), and `AntiSpamService` only does cloud
> phone-list updates. The rest of the chain (from
> `SmartSmsFilterPolicy.handleData` down) is confirmed. PDU→text decoding
> happens in the calling SMS app, outside this APK.

```
SMS arrives (MIUI antispam)
  └─ com.miui.antispam.service (AntiSpamService) → policy chain   [superseded, see §11.1]
     └─ SmartSmsFilterPolicy.handleData(j2.c sms)                 [classes2.dex]
        skip if Build.IS_INTERNATIONAL_BUILD
        skip unless ExtraTelephony.isServiceNumber(addr) ? service_sms_mode==1
                                                     : stranger_sms_mode==1
        └─ shouldFilter(ctx, address, body)
             skip if body empty / isMiuiPattern (bracket-title template) /
                     isInSmsWhiteList (YellowPageContract.AntiSpamWhiteList)
             └─ j2.a.h(address, body)          "SmsEngineHandler.getSMSJudgeResult"
                  d9.a engine = d9.a.b(files/SmsEngineResource)      [TFLite, downloaded]
                  ├─ d9.a.c(addr, body)  (z8.a regex → a9.b → a9.a "important")
                  │     → return 0 (important SMS, not spam)
                  ├─ if MIUI MMS version < 11000249 and ah.a.a(ctx, addr, body) > 0
                  │     → "old sms engine blocked" → return 2
                  ├─ else if d9.a.d(body) (a9.a gate → a9.c "unlaw") → return 2
                  └─ else return 0
        └─ handleData: shouldFilter==1 → block type 8 (phishing)
                       shouldFilter==2 → block type 4 (spam)

ah.a.a(ctx, addr, body):                                       [classes3.dex]
  1. ah.b b = new ah.b(addr, body)
       number: strip "+86"; category = dh.a.d(number)   → bias column 0..7
       body  : ah.h.c(body)                             → normalised text
  2. new dh.e().e(b):
       dh.j/dh.b/dh.c split the body (e-mail/USD/number regexes) → b.l(pieces)
       dh.j (1) + dh.b (1) + dh.c (7) rule features → b.m(int[9])
  3. ah.d.h(b, ctx):
       ah.e.f(b): URL heuristic (ah.i.b → flag 1), phish list (ah.d.d → flag 2)
       ch.d pattern flags → b flags
       early exits on flags
       dh.e.f(b) → a(b): each piece from b.l is expanded with the ah.g dictionary
            (assets/dict, 348,982 words) and kept if all-CJK (ah.i.a) or known
            (ah.g.c); sorted set → b.i() = the token list matched against the model
       ah.g dict reload → dh.e with per-stage dictionaries
       InputStream = ah.c.b(ctx,"model")  ← assets/model  (or files/model override)
       DataInputStream: read header/stages exactly as in §4.2
       per stage: feature vector (9 rules + char dictionary + length)
                  token/pattern lookups, bias, thresholds, cascade vote
       → 0/1
  4. if blocked and category=="Common" and ah.f.a(ctx,number) (existing SMS/call
     history with that number) → unblock
  5. return 2 (blocked) / 1 (blocked + phish flag bit 2) / 0
```

Verdict mapping: `1` ⇒ block reason 8 (phishing), `2` ⇒ block reason 4 (spam).

---

## 7. TFLite library and the new engine

`libtensorflowlite_jni.so`: ELF64 AArch64, NDK r19c, 59 `Java_*` JNI exports
(`native/jni_list.txt`), version string `2.8.4`, statically linked
TFLite+XNNPACK+NNAPI delegate.

* NNAPI: `Java_org_tensorflow_lite_nnapi_NnApiDelegate_createDelegate` is
  exported, but **no application code instantiates `NnApiDelegate`** (invoke
  xref over `org/tensorflow/lite` shows only `a9.a`, `a9.c`, `d9.a` calling
  `org.tensorflow.lite.b` and nothing else). `org.tensorflow.lite.b`
  (`Interpreter`) is built as `new NativeInterpreterWrapperExperimental(path, null)`
  — default options, CPU.
* Flex: no `Java_org_tensorflow_lite_flex_*` symbol; only
  `hasUnresolvedFlexOp` + the "apply/link the Flex delegate" error string ⇒ the
  Flex delegate is **not** linked (the pre-scan's `FlexDelegate` hit is the
  Java class + this message).
* Model I/O (`a9/a.java`, `a9/c.java`, `c9/a.java`):
  `c9.a` builds `float[1][128]` from the message: lowercase, strip
  space/quote/`\s`, split into UTF-8 code points, each mapped through a
  tab-separated `v.txt` vocabulary to a float weight, padded with 0 to 128.
  `a9.c` (`UnlawRecognizer`, `1.bin`, threshold 0.4) and `a9.a`
  (`ImportanRecognizer`, `2.bin`, threshold 0.6) run that vector and read
  `float[1][1]`.
* All of `1.bin`, `2.bin`, `v.txt` (and any dict/model/pattern override) live in
  `files/SmsEngineResource` after `resource.zip` is downloaded from
  `https://api.sec.intl.miui.com/GuardProviderV1/GetResourceZipFile`
  (`l2.a`, "SmsEngineUpdateManager", version 2.1.2) — **absent from the APK**.

---

## 8. JAX reimplementation

`reimpl/model.py` (runs under `/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python`):

* `load_stages()` parses the *real* container (`ir/parse_model.py`), so the
  parameters are the extracted weights, not random init.
* `init_params()` → JAX arrays per stage: `w (2,F)`, `token_w (T,2)`,
  `pattern_w (P,2)`, `bias (2,8)`, `thr (3)`.
* `stage_scores()` / `apply()` implement §4.4 exactly, including
  `log1p(-exp(w))` for absent items and the `ah.e.g` feedback shift.
* `build_masks()` composes the feature vector (9 rules, character dictionary,
  length) and token/pattern hit masks.
* `classify()` reproduces the 5-stage cascade with early exit + majority vote.
* `classify_numpy()` is an independent NumPy transcription of the same Java code
  used as the verification reference.

`reimpl/verify.txt` (exit 0): shapes for all 5 stages, JAX vs NumPy agreement
`< 1e-9` on every stage score, early-exit traces, and the two discriminative
probes from §4.5 (spam probe → 1, ham probe → 0). `jax_enable_x64` is set so
both paths use float64.

Limitation: verification is against an independent re-implementation of the
decompiled Java, not against a running Android runtime; the rule-feature
functions (dh.j/dh.b/dh.c) are modelled as caller-supplied booleans rather than
re-derived, and the `ch.d` feedback counters are inputs.

---

## 9. Unknowns / blockers

1. `1.bin`, `2.bin`, `v.txt` of the new TFLite engine are downloaded at runtime
   (`resource.zip`); contents (op graph, input dtype, class head) unknown.
2. `assets/phish` is absent; its list cannot be recovered from the APK.
3. `dh.c`'s 7 rule features are read from decompiled code but their exact
   numeric encodings (bank-card/phone regexp scoring, "Confus" counter) were not
   exhaustively traced; they enter the model only as 0/1 counts.
4. The 6-byte prefix records at the head of `assets/dict` (`u16 char,u16 node,u16 freq`)
   and the 4×4 double transition matrix in the dict header are used by `bh.a`
   for segmentation cost; their exact cost formula was not re-implemented.
5. Server-side (cloud) models referenced by `getResourceZipFile` were not
   fetched (no network, out of scope).

---

## 10. File map

```
report.md                     this file
PIPELINE.md                   data-pipeline contract (P1..P7, template §1..§9)
findings.json                 machine-readable summary (SCHEMA.json) + data_pipelines
inventory/                    apk_inventory output + extracted model/lib
models/assets_model           extracted assets/model (680,154 B)
models/assets_dict            extracted assets/dict  (3,639,702 B)
models/assets_pattern         extracted assets/pattern (5,568 B)
ir/parse_model.py             decoder for assets/model  (walks to EOF)
ir/model_ir.json              decoded IR (stages, dicts, thresholds, heads)
ir/weights.npz                extracted weights (w, token_w, pattern_w, bias, thr)
ir/parse_dict.py              decoder for assets/dict
ir/dict_ir.json               decoded dictionary IR
reimpl/model.py               JAX pipeline (real weights) + NumPy reference
reimpl/verify.txt             run log (exit 0)
sources/classes/*.java        48 jadx single-class decompilations (index.md)
native/                       elf_info, JNI export list, delegate strings
evidence/                     xref scans, parser transcripts, tooling
```

---

## 11. Pipeline patch — exact SMS entry, threading and delegated boundaries

Full contract tables: [`PIPELINE.md`](PIPELINE.md). This section records what
the first pass left implicit or got wrong.

### 11.1 Correction: the in-APK SMS entry is the provider, not `AntiSpamService`

`AntiSpamService` only handles `action="timingUpdate"` (cloud phone-list pull,
`m2.a`). The policy chain is reached through the **exported ContentProvider**
`com.miui.antispam.db.AntiSpamProvider` (`authority=antispam`,
`process=:remote`):

```
system SMS app (outside APK) decodes the PDU -> SmsMessage.createFromPdu
  -> ContentResolver.query(content://antispam/sms_judge,
        selectionArgs = { address, body, simId })          [contract: 3 args]
     AntiSpamProvider.query()  case 9   (also update() case 9)
       gate: !f4.z.A()            # ro.miui.google.csp == false
       gate:  o2.f.E(ctx)         # non-international, or default smsto: app is com.android.mms
       -> c(String[])             # Binder.clearCallingIdentity()
            new j2.c(address, o2.f.s(ctx, simId), 1, body, false, false, false)
            -> d2.b.e(j2.c)                                   # PolicyManager
       -> MatrixCursor{"smsBlockType"}  (single int)
```

`d2.b.e` walks the 20-policy `TreeMap` in enum-id order, but only policies whose
`j2.e.f47873e` is `SMS` or `BOTH`; `SmartSmsFilterPolicy` has id 18 and is the
last SMS policy before the URL policy. Each policy returns
`policy.a.C0115a{handled, blockType, keyword}`; the first `handled` breaks the
loop. After the loop `com.miui.antispam.policy.b.d(type, cVar)` (URL filter) may
raise the type, then `ExtraTelephony.getRealBlockType(type) >= 3` triggers
`o2.f.a()` → async `FirewallLog` insert (`number, read=1, type=2, reason, data1`).

This corrects the §6 diagram, which routed the message through
`AntiSpamService`; that service is unrelated. **PDU→text extraction is not in
this APK** — `dex_find` shows no defined `SmsReceiver`, and `sms_judge` appears
only in `AntiSpamProvider.<clinit>`. The `pdus`/`createFromPdu` strings belong
to `com.miui.networkassistant.utils.TelephonyUtil` (traffic-correction SMS).

### 11.2 What is actually passed between stages

| Hop | Object | Fields consumed |
|---|---|---|
| provider → manager | `j2.c` | `f47835b` normalised number, `f47840g` raw body, `f47838e` simId, `f47839f` type=1, `f47834a/45l` raw/normalised variants, `f47844k` = body empty |
| manager → policy | same `j2.c` | address for whitelists, body for the engine |
| policy → engine | `(String number, String body)` | `j2.a.h` |
| engine → legacy | `ah.b` | `f505h` number, `f506i` sender category 0..7, `f501d` normalised body, `f504g` pieces, `f503f` `int[]` features, `f499b` sorted token `List<String>`, `f498a` token `Set<String>`, `f512o` flag word |
| legacy → model | `DataInputStream` over `assets/model` + `dh.e` | per stage: `n_feat`, char dict `char[]`, length threshold, interleaved `[2][n_feat]` weights, token/pattern tables, `[2][8]` bias, `3` thresholds |
| engine → new engine | `float[1][128]` | `c9.a.a(body)` bag-of-code-points |

### 11.3 Invocation plumbing, caching, threading, cadence

* `ah.c.b(ctx,name)` is the only asset opener: it prefers `files/<name>` when
  that file exists in `ctx.fileList()` **and** `ah.c.f518c == false`, otherwise
  `getAssets().open(name)`. There is **no cache**: `model`, `dict`, `pattern`
  and `phish` are re-opened per call; `l2.a` calls `ah.c.c(true)` after a
  resource install, which forces the APK assets for these four names.
* Per SMS the legacy path re-parses **`assets/dict` once** (`ah.d.k(ctx)` builds
  a fresh `ah.g`; `dh.e.f`→`a()` reuses it) and streams the whole `assets/model`
  from the beginning — cost is proportional to the message count, not amortised.
* The new engine is a process singleton (`d9.a.f44130f`) but `j2.a.h` calls
  `d9.a.a()` in **every** exit path (success, important, blocked, exception,
  `finally`), which nulls the singleton, clears the vocabulary map and closes
  both TFLite interpreters. The next message therefore re-parses `v.txt`,
  re-opens `1.bin`/`2.bin` and rebuilds the interpreters.
* Thread: `AntiSpamProvider.query` runs on the caller's Binder thread; the
  engine is synchronous there (the `"thread is main = …"` log in `j2.a.h`).
  Resource init is the only async part: `j2.a.k()` posts to
  `Executors.newSingleThreadExecutor`; `l2.a` downloads via `AsyncTask` and then
  re-enters through `ContentResolver.call(content://antispam, "initSmsEngine")`.
* Order inside `j2.a.h`: `d9.a.b(dir)` → `d9.a.c()` (important ⇒ return 0) →
  if `com.android.mms` versionCode `< 11000249` and `ah.a.a() > 0` ⇒ return 2
  (`"old sms engine blocked."`) → else `d9.a.d()` ⇒ return 2
  (`"new sms engine blocked."`) → else 0. Failures return 0 (allow).

### 11.4 New engine (P2): exact vector and threshold path

`c9.a.a(body)` strips `\n \r \t`, spaces, `\s+`, `\"`, `'`, lowercases, takes
`String.getBytes()` and slices UTF-8 sequences by lead byte. Tokens that exist
in `v.txt` (`"<token>\t<float>"` per line) are placed **in message order** at
successive slots; unknown tokens are skipped, not padded. Result `float[1][128]`
(0.0 beyond the last hit). `a9.a` (`2.bin`) returns `out[0][0] > 0.6` =
"important"; `a9.c` (`1.bin`) returns `out[0][0] > 0.4` = "unlawful". No
softmax/sigmoid in Java. `a9.b` adds 7 hard-coded regex rules keyed by sender
`10086`, `95555` and a `"0"` fallback (constant
`StatManager.PARAMS_SWITCH_OFF`, inlined `"0"`).

Runtime: `new org.tensorflow.lite.b(new File(dir+name))` →
`NativeInterpreterWrapperExperimental(path, null)` (default CPU options, no
NNAPI/XNNPACK delegate constructed); `b(Object,Object)` → JNI
`Java_org_tensorflow_lite_NativeInterpreterWrapper_run` @ `0x13234`
(`native/tflite_run_jni.s`). Weights are **not in the APK**; see P3.

### 11.5 Where the downloaded files come from

`l2.a` (`SmsEngineUpdateManager`, version `2.1.2`):
`GET https://api.sec.intl.miui.com/GuardProviderV1/GetResourceZipFile`
(params `appVersion`, `ver=2.1.2`, signed by `ce.j.p` with key
`7htr5238-a8cf-3k79-ec73-75382145ns5c`) → `data.fileUrl` →
`DownloadManager` → `Downloads/resource.zip.tmp` → status must be `8`,
**MD5 must equal `6206c9f17a64246498b52e117d0cff1e`** → copied to
`files/resource.zip` → prefs `SmsEnginePreference.version=2.1.2` →
`ah.c.c(true)` → `ContentResolver.call("initSmsEngine")` → `j2.a.k(true)` →
unzip into `files/SmsEngineResource/` (zip-slip guarded, entry-size checked) →
`d9.a.b(dir)`. The archive contents (and therefore `1.bin`/`2.bin`/`v.txt`) are
**unknown**: no network, not in the APK.

### 11.6 Delegated AI features — exact boundary

| Feature | Outbound call | Payload | Return/state |
|---|---|---|---|
| AI subtitles / screen translation | `startService(Intent{ComponentName("com.xiaomi.aiasst.vision", "com.xiaomi.aiasst.vision.control.translation.AiTranslateService")})` from `q7.a.i/j`; close via `q7.a.b` with `extra="close"` | `Bundle`: `from=<channel>` (`video_toolbox`, `conference_toolbox`, `conference_toolbox_screen_translate`), `floatingWindowType="startAiSubtitlesWindow"` | `ContentResolver.query(content://com.xiaomi.aiasst.vision/subtitleState` or `/screenTranslateState`, column `value` int) — `q7.a.c/f`; support gate `h7.c0.m` (provider value, else version ≥413 on non-international builds) |
| UltraClear | `p1.d("persist.vendor.vcb.enable", "true"/"false")` from `f5.f.J0` (called by `BeautyView.onClick` `iv_beauty_face`) | one property | `f5.f.G()/F()` read `persist.vendor.vcb.enable` / `persist.vendor.vcb.ability` (false on `taoyao`, region `IN`) |
| Beauty / privacy / portrait / pickup | `persist.vendor.vcf.enable` bitfield (`f5.f.M0`), `persist.vendor.camera.facetracker.active/rrzosize` (`f5.f.y0`), overlay via `IGameBoosterWindow` binder + `DockWindowManagerService` (`BeautyService`) | properties + package/activity names from `beauty_face_support_activity_list.json` etc., cloud overrides via `p7.b` | none; effect is applied by camera/video toolbox |

No image, audio or video buffer is ever handed to another process by this APK;
the only cross-process payloads are intent extras and system properties.

### 11.7 New evidence files

```
PIPELINE.md                              per-stage contract, 9 sections
sources/classes/com.miui.antispam.db.AntiSpamProvider.java
sources/classes/d2.b.java, d2.a.java       PolicyManager + constants
sources/classes/j2.c.java, j2.b.java, j2.d.java, j2.e.java, j2.f.java
sources/classes/com.miui.antispam.policy.a.java, .b.java
sources/classes/com.miui.antispam.service.AntiSpamService.java, m2.a.java
sources/classes/a9.b.java, b9.a.java, b9.b.java, z8.a.java
sources/classes/o2.f.java, f4.z.java
evidence/xref_sms_judge.jsonl            sms_judge string only in AntiSpamProvider
evidence/xref_policy_dispatch.jsonl      handleData dispatch + policy construction
evidence/xref_policymanager.jsonl        AntiSpamProvider/h2.* callers of d2.b
evidence/xref_aisubtitle_callers.jsonl   q7.a callers
evidence/xref_vtb_callers.jsonl          f5.f callers (UltraClear/property writes)
evidence/xref_const_0.jsonl              "0" inlined for StatManager.PARAMS_SWITCH_OFF
native/tflite_run_jni.s                  JNI run thunk disassembly
```
