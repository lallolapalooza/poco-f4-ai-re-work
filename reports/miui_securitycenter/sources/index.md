# Decompiled sources (jadx `--single-class`, APK classes*.dex)

All files below were produced with
`tools/decompile_classes.py "<apk>" --regex '<class>' --max N --timeout T`
(jadx single-class mode, `--show-bad-code --no-res`, one JVM class at a time).

## SMS model pipeline (classes3.dex unless noted)

| Class | Role |
|---|---|
| `ah.c` | asset loader: `{"dict","model","pattern","phish"}`; prefers `files/<name>` over `getAssets()` |
| `ah.d` | model evaluator: `h(ah.b, Context)` — opens `assets/model`, reads 5 stages, scores, thresholds, votes; `d()` = phish list check |
| `ah.a` | orchestrator `a(Context, number, body)` → 0/1/2 verdict |
| `ah.b` | message record (number, category, normalised body, tokens, rule features, flags) |
| `ah.e` | base filter: flag handling (`ah.i.b`, phish list) + threshold feedback `g()` |
| `ah.f` | contact-history whitelist (SMS + call log) |
| `ah.g` | loads `assets/dict` into `bh.a`; token expansion helpers |
| `ah.h` | normaliser helpers |
| `ah.i` | char classifiers, URL heuristic `b()`, `g(x)=log(1-exp(x))` |
| `dh.a` | sender-number category (8 classes) + bias index |
| `dh.b` | rule feature: e-mail address regex hit |
| `dh.c` | rule features ×7: bank card / phone / mobile / 400 / range / tag / confusion |
| `dh.d` | rule base class (`a()=1`) |
| `dh.e` | RuleManager: feature layout (rules + 2 `g` features) |
| `dh.f` | rule base: `c()`, `e()` (text splitting) |
| `dh.g` | `g` feature base, `d(DataInputStream)` |
| `dh.h` | `g` feature: message-length threshold (`readInt`) |
| `dh.i` | `g` feature: character dictionary (`readInt` + chars) |
| `dh.j` | rule feature (numeric/decimal pattern) |
| `bh.a` | segmentation dictionary/trie; `h(InputStream)` = `assets/dict` parser |
| `bh.b` | dictionary helper |
| `ch.a`/`ch.b` | phish/URL helpers used by `ah.e` |
| `ch.c`/`ch.d`/`ch.e` | `assets/pattern` feature model: JSON → bit flags, `e(ah.b)` returns flag word |
| `com.miui.antispam.policy.SmartSmsFilterPolicy` | SMS policy entry: `handleData`, `shouldFilter`, whitelists |
| `j2.a` | `SmsEngineHandler`: new TFLite engine + legacy fallback; `getSMSJudgeResult` |
| `l2.a`, `l2.a$a` | `SmsEngineUpdateManager`: downloads `resource.zip`, installs `SmsEngineResource` |
| `d9.a` | `SmsRecognizer`: owns `c9.a` vectorizer + `a9.a`/`a9.c`/`a9.b` TFLite models |
| `a9.a` | `ImportanRecognizer` (`2.bin`, threshold 0.6) |
| `a9.c` | `UnlawRecognizer` (`1.bin`, threshold 0.4) |
| `c9.a` | vectorizer: `v.txt` vocab → `float[1][128]` |
| `org.tensorflow.lite.b` | obfuscated `Interpreter` wrapper (`NativeInterpreterWrapperExperimental`, null options) |

## Game Turbo / video toolbox configuration (classes2.dex)

| Class | Role |
|---|---|
| `q7.a` | `AiSubTitleUtils`: starts `com.xiaomi.aiasst.vision` translate service, reads subtitle state provider |
| `p7.b` | `CloudControlHelper`: cloud overrides for `aisubtitle_activities`, `ultraclear_activities`, `beauty_face`, … |
| `f5.f` | video-toolbox allow-list manager (`beauty_face`, `ultraclear`, `privacy_*`, `portrait`, `pickup`, `aisubtitle`) |
| `h7.c0` | `GameBoosterFeatureUtils`: `checkAISubtitle` (aiasst ≥413), feature probes |
| `n7.a` | enum of video-toolbox feature ids (incl. `ULTRACLEAR`, `AI_SUBTITLE`) |
| `l5.e`, `l5.h`, `v5.a`, `ke.e` | UI/preference classes whose `"model"` string is unrelated (false positives) |
| `com.miui.gamebooster.beauty.BeautyService` | foreground monitor service: broadcasts, camera availability, activity/task-stack listeners, `IGameBoosterWindow` binding for the beauty overlay |
| `com.miui.gamebooster.beauty.BeautyView` | overlay view for the beauty controls |
| `com.miui.gamebooster.beauty.a` | helper/controller used by `BeautyService`/`BeautyView` (settings, sensors) |

## Data-path entry points, policy dispatch and new-engine helpers (added in pipeline patch)

| Class | Role |
|---|---|
| `com.miui.antispam.db.AntiSpamProvider` | exported provider (`authority=antispam`, process `:remote`); `query`/`update` case 9 = `sms_judge` → `c(String[])` → `d2.b.e(j2.c)`; `call("initSmsEngine")`; also `call_judge`, `url_judge`, `service_num_judge` |
| `com.miui.antispam.policy.a` | abstract policy base; `C0115a{handled, blockType, keyword}` result record; `handleData(j2.c)` |
| `com.miui.antispam.policy.b` | URLFilterPolicy: GuardProvider binder (`IURLScanServer`), browser redirect probe, signed cloud URL-category POST, per-SIM whitelist |
| `d2.b` | `PolicyManager`: builds all 20 policies by reflection from `j2.e`, `b(c)` = call chain, `e(c)` = SMS/MMS chain (priority order), then URLFilterPolicy, then fwlog insert |
| `d2.a` | policy constants (debug flag, phonelist types) |
| `j2.c` | SMS/call record passed between provider and policies (`number`, `normalized`, `country`, `simId`, `type`, `body`, flags) |
| `j2.b` | `Judge`: in-memory phonelist + keyword caches loaded from `ExtraTelephony` providers in a background thread |
| `j2.e` | policy enum: id, class, scope (`CALL`/`SMS`/`BOTH`) — reflection source for `d2.b` |
| `j2.f` | `URLFilterManager`: binds `com.miui.guardprovider` (`action.urlscan`) and calls `IURLScanServer.b3("AVL", url)` |
| `com.miui.antispam.service.AntiSpamService` | exported service, `timingUpdate` action → cloud phone-list pull (`m2.a.h`) |
| `m2.a` | cloud phone-list/keyword update task |
| `a9.b` / `b9.a` / `b9.b` | rule matcher for the new engine; `b9.b` holds the 7 hard-coded patterns keyed by `10086`, `95555`, `"0"` |
| `z8.a` | regexes used by `d9.a` (`CJK`, pure-number, 1-500 alnum) |
| `o2.f` | block-log helper: `a()` = async insert/update of `ExtraTelephony.FirewallLog`; number normalisation (`I`) and build gates (`E`) |
| `f4.z` | `A()` = `ro.miui.google.csp` gate used by the provider |
