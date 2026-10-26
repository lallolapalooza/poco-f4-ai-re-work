# GAP_CLOSURE — `miui_securitycenter` P1 SMS ingestion pipeline

Status: **complete for the in-APK legacy engine (P1)**. `reimpl/pipeline_sms.py`
takes a raw SMS body plus an optional sender, runs the APK's own normaliser,
rule stages and `assets/dict` segmenter, builds the per-stage masks, and runs
the real 5-stage cascade from `models/assets_model` (via `reimpl/model.py`).
Exit 0 on 2 spam-like and 2 ham-like messages (`--demo`), and on 45
self-checks (`--selftest`).

Not in scope / not attempted: the downloaded new-engine TFLite files
(`1.bin`, `2.bin`, `v.txt`), the resource download (P3) and the cloud URL check
(P4).

## What the entrypoint covers

| APK stage | code | status |
|---|---|---|
| sender category `dh.a.d` + `ah.b` "+86" strip | `dh_a_d`, `SmsMessage` | confirmed |
| body normaliser `ah.h.c` (trim, separator split, de-obfuscation `ah.h.a`) | `ah_h_c`, `ah_h_a` | confirmed |
| rule stage `dh.j` (URL / domain / IPv4) — rule feature 0 | `UrlRule` | confirmed |
| rule stage `dh.b` (e-mail) — rule feature 1 | `EmailRule` | confirmed |
| rule stage `dh.c` (numeric structures) — rule features 2..8 | `NumberRule` | **partly inferred** (see below) |
| pieces list `dh.e.e` / `dh.e.f`, token collection `dh.e.a` | `SmsPipeline.prepare` | confirmed |
| dict trie `bh.a.h` (header, 4 char-class tables, word table) | `DictTrie.__init__` | confirmed |
| DAG Viterbi segmentation `bh.a.c` + char-class state machine `bh.a.b` + word lookup `bh.a.d`/`bh.a.f` | `DictTrie.segment`, `DictTrie._run`, `DictTrie.freq`, `DictTrie.contains` | confirmed |
| URL heuristic bit 0 `ah.i.b` | `i_b` | confirmed |
| phish bit 1 `ah.d.d` | `SmsPipeline.rule_bits` | **stub** (`assets/phish` absent from the APK → always false) |
| `assets/pattern` features `ch.a`/`ch.b`/`ch.c`/`ch.d`/`ch.e` → 32-bit pattern word | `load_pattern_json`, `PatternMatcher`, `AddressRule`, `BodyRule`, `PatternFeature` | confirmed, except the `num_types` branch (below) |
| threshold shift `ah.e.g` ← `ch.d.a`/`ch.d.b` | `shift_thresholds`, `ch_d_a`, `ch_d_b` | confirmed (`MenuBuilder.USER_MASK = 0xFFFF`, `C.BUFFER_FLAG_FIRST_SAMPLE = 0x08000000` resolved from the APK dex, not guessed) |
| per-stage masks (9 rules + stage chars + length bit), token hits, substring-pattern hits | `model.build_masks` | confirmed |
| scoring `ah.d.h` (log-odds, bias, shifted thresholds, early exit, majority vote) | `model.stage_scores` + `SmsPipeline.score` | confirmed; `--selftest` cross-checks every case against `model.classify_numpy` (< 1e-6) |
| verdict `ah.a.a` (0 allow / 2 spam / 1 phishing) | `SmsPipeline.score` | confirmed (verdict 1 needs the absent phish bit) |
| false-positive guard `ah.f.a` (SMS/call history) | not implemented | **stub**: no telephony DB here, guard treated as inactive |

Two conventions were resolved from the APK instead of being inferred, because
the report listed them as open:

* `com.google.android.exoplayer2.C.BUFFER_FLAG_FIRST_SAMPLE` = `134217728`
  (`0x08000000`) — `jadx --single-class` of `com.google.android.exoplayer2.C`.
  It is the `ah.e.b` early-exit bit; four `assets/pattern` features
  (`fa_piao`, `fa_piao_1`, `bank_card_remittance`, `phish`) are tagged with it.
* `miuix.appcompat.internal.view.menu.MenuBuilder.USER_MASK` = `65535`
  (`0xFFFF`) — used by `ch.d.a` (bits 4..15 weighted 12..1, "active");
  `ch.d.b` uses the literal `0x0FFF0000` (bits 16..27 weighted 1..12,
  "inactive"). The shift is `((active - inactive) * (thr0 - thr1)) / 4`.

## Inferred / degraded regions (explicit)

1. **`dh.c.i` numeric classifier — jadx-degraded.** `dh.c` classifies digit
   runs into `BankCard/Phone/PhoneMobile/Phone400/Range/Tag/Confus`. The
   decompiled `i()` carries jadx "code duplicated" warnings over two mirrored
   blocks; the separator branch is readable (types `4` Range by default,
   `1` Phone for `0xxx-xxxxxxx`, `3` Phone400 for `100-xxxxxxx`), but in the
   non-separator branch *every* assignment of the length chain collapsed to
   `-1`. We implement the decompiled control flow literally, so:
   * grouped numbers (space / dash / comma) do get a type,
   * plain digit runs (e.g. an unseparated 11-digit mobile, a 16-digit card)
     get **no** type, hence no rule counter and no `num_types` structure;
   * `BankCard` (type 0) is unreachable in the decompiled chain at all.
   Consequence: rule features 2..8 are only ever set by separated numbers, and
   the `num_types` half of `fa_piao_1` / `bank_card_remittance` cannot fire.
   Everything else in `dh.c` (scanner `d()`, digit-alphabet normalisation
   `g()`/`h()`, splitting, counters, `num_types` plumbing) is ported exactly.
2. **`ch.b` `num_types` branch.** jadx marks it "code restructure failed"; we
   read it as "no matching structure → feature false", which is also what the
   surviving `body_len` branch does. Affected features are already dead per
   item 1.
3. **Sender normalisation `o2.f.p` / `o2.f.I`.** The APK normalises the address
   with `PhoneNumberUtils.PhoneNumber.parse(...).getNormalizedNumber(false,true)`
   (miui telephony, plus a `"62"` country-code rule in `j2.c`). We use the
   sender string as supplied (after the `ah.b` `+86` strip); for plain digit
   senders this is identity, for exotic formats it may pick a different
   `dh.a.d` category.
4. **UTF-16 vs code points.** All length tests (`dh.h` length bit, `ch.b`
   `body_len`) use `String.length()` semantics (`java_len`), and feature/pattern
   matching is performed on a Java-code-unit copy of the body (`java_str`), so
   surrogate pairs behave like the APK. `bh.a.c` iterates code points instead of
   code units, and `TreeSet` ordering is approximated by Python's code-point
   order; both differ only for astral characters (emoji), which the dictionary
   does not contain.
5. **`dh.c` number classification is per message** (`f44576c` is reset per
   stage), matching the APK's per-message instance.

## Consumer mapping (`PIPELINE.md` §6)

```
SMS app (com.android.mms, outside this APK)
  ContentResolver.query("content://antispam/sms_judge", {address, body, simId})
    -> com.miui.antispam.db.AntiSpamProvider.query() case 9
       -> c(String[]) -> new j2.c(address, ...) -> d2.b.e(j2.c)      [policy chain]
          -> SmartSmsFilterPolicy.handleData(j2.c)
             -> shouldFilter(ctx, number, body)  == j2.a.h(number, body)
                -> d9.a.c(...)  new engine (P2, absent)             -> 0
                -> ah.a.a(ctx, number, body)  legacy engine (P1)    -> 0 / 2
                   ^^^ pipeline_sms.py reproduces exactly this call
             -> iShouldFilter == 1 ? 8 : 4      (policy.a.C0115a)
    -> MatrixCursor{"smsBlockType"} back to the SMS app
```

* `pipeline_sms.py` result keys `verdict` / `should_filter` / `block_type`
  correspond to `ah.a.a` (0 allow, 1 phish, 2 spam), `j2.a.h` (0 / 2) and
  `policy.a.C0115a.b` (8 / 4) respectively.
* `ah.a.a` maps "blocked + phish bit" to **1**, blocked to **2**; `j2.a.h`
  collapses both to **2** (`return 2` on any legacy block), so with the legacy
  engine the returned block type is always **4** (spam). 8 (phishing) is only
  reachable if `shouldFilter` ever returns 1, which the P1/P2 paths do not do.
  The pipeline therefore reports `verdict` (raw `ah.a.a`) plus
  `should_filter = 2` and `block_type = 4`.
* Exceptions mirror `j2.a.h`'s `catch (Exception)` → verdict 0 (allow); the
  pipeline catches the same way and reports `error` in its result dict.
* `--sender 10086` shows the "important" suppression path end to end: sender
  category `100+5` → pattern feature `important_phone` (`sms_type 0x1000`,
  active weight 4) → all three stage thresholds shift by `+19.711` → the
  message is effectively whitelisted. Pattern bit 0 (`sms_body_length`,
  `Fei_xin`, `important_info`, `linking_up`, `telephone_fee`) returns a hard
  allow *before* scoring, which is why the short ham example never reaches the
  cascade.

## Verification

`reimpl/verify.txt` (all commands run under
`.../tooling/venv312/bin/python`, cwd `reports/miui_securitycenter`):

| command | exit | result |
|---|---|---|
| `$PY reimpl/model.py models/assets_model` | 0 | pre-existing: JAX/NumPy agree, probes spam=1 ham=0 |
| `$PY reimpl/pipeline_sms.py --selftest` | 0 | 45/45 checks (dict to EOF, 348,982 words, char tables, segmentation partitions, rule stages, bit accounting, UTF-16 length, 4 demo messages + NumPy agreement) |
| `$PY reimpl/pipeline_sms.py --demo` | 0 | verdicts `[2, 2, 0, 0]` as expected |
| `$PY reimpl/pipeline_sms.py --sender 10086 --body '【中国移动】您本月话费余额为120元' --json` | 0 | verdict 0, `pattern_word=0x1000`, thresholds 29.81/10.10 |

The demo cases are: (a) 京东 prize/loan/link SMS → `d=+10.244 > thr0=10.100` at
stage 0 → block type 4; (b) 免息贷款 link SMS → votes through stages 0..2 and
blocks at stage 3 (`d=+5.744 > thr0=5.208`); (c) short personal SMS → pattern
bit 0 (`sms_body_length`) → allow; (d) 会议 SMS → stage 0 `d=-13.962 < thr1`
after the `all_chinese` shift → allow.

## Stale statements the parent should merge

* `REIMPL_COVERAGE.md` §SecurityCenter: "no SMS ingestion/tokenizer wrapper"
  and "the ingestion wrapper is not implemented" are now closed;
  `reimpl/pipeline_sms.py` covers raw text → tokens → masks → verdict.
* `PIPELINE.md` §9 (line 261) row "P1 normaliser/segmenter — **not
  reimplemented** … ARCHITECTURE_ONLY" is closed; `PIPELINE.md` §8 (line 252),
  "`ah.e.b` early-exit bit … inferred (value)", is now confirmed as
  `0x08000000`, and `ch.d.a`'s `MenuBuilder.USER_MASK` is `0xFFFF`.
* `reimpl/README.md` limitation paragraph updated to point at
  `pipeline_sms.py`.
