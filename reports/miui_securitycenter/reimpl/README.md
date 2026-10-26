# reimpl — JAX implementation of the MIUI SMS model (`assets/model`)

`model.py` loads the **real extracted weights** from `../models/assets_model`
(container decoded by `../ir/parse_model.py`) and reproduces the scoring code of
`ah/d.java::h(ah.b, Context)`:

* feature vector per stage = 9 rule bits + stage character dictionary + message-length bit
* token weights (exact match against the message token set) and substring-pattern
  weights (`contains`), each contributing `w` when present and
  `log(1 - exp(w))` (= `ah.i.g`) when absent
* sender-category bias (`dh.a.d(number)` → 0…7)
* threshold cascade with early exit, then majority vote

Files:

* `model.py` — `load_stages`, `init_params`, `stage_scores`, `apply`, `build_masks`,
  `classify` (JAX) and `classify_numpy` (independent reference).
* `pipeline_sms.py` — **end-to-end SMS pipeline**: raw body + sender →
  `ah.h.c` normaliser → `dh.j`/`dh.b`/`dh.c` rule stages → `bh.a.c`
  segmentation over `assets/dict` → per-stage masks → the 5-stage cascade →
  `ah.a.a` verdict → `SmartSmsFilterPolicy` block type. `--demo` (spam + ham),
  `--selftest` (45 checks incl. a `classify_numpy` cross-check), `--json`.
* `GAP_CLOSURE.md` — confirmed vs inferred stages, consumer mapping, open items.
* `verify.txt` — captured runs (exit 0).
* `coverage_fragment.json` — status for the top-level coverage merge.

Run:

```bash
/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python reimpl/model.py models/assets_model
/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python reimpl/pipeline_sms.py --selftest
/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python reimpl/pipeline_sms.py --demo
/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python reimpl/pipeline_sms.py --sender 10086 --body '【中国移动】您本月话费余额为120元' --json
```

Expected output (abridged):

```
stages: 5
  stage 0: n_feat=68 rules=9 chars=58 len_thr=39 tokens=8000 patterns=414 …
  …
jax/numpy agree=True verdict_equal=True
spam probe (30 tokens, e.g. ['真人', '百家乐', …]): jax=1 numpy=1 stage0 d=136.83
ham  probe (30 tokens, e.g. ['发给', '证监会', …]): jax=0 numpy=0 stage0 d=-131.43
ALL CHECKS PASS=True
```

`jax_enable_x64` is enabled so the JAX and NumPy paths agree to < 1e-9.

Limitations: `pipeline_sms.py` derives the 9 rule features and the `ch.d`
feedback counters from the raw message, but `dh.c.i`'s non-separator branch is a
jadx "code duplicated" artifact (plain digit runs get no type, `BankCard` is
unreachable), `assets/phish` is not shipped (phishing verdict unreachable), and
the sender normaliser `o2.f.p`/`o2.f.I` and the history guard `ah.f.a` are not
reimplemented — see `GAP_CLOSURE.md`. The newer TFLite engine (`1.bin`,
`2.bin`, `v.txt`) cannot be rebuilt because those files are downloaded at
runtime and are not part of the APK.
