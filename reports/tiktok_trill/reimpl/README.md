# reimpl/ — TikTok / musically smash-face models

The three `.model` files are **no longer opaque**. See `GAP_CLOSURE.md` for the
full reversed contract (container, crypto, net text format, weight layout) and
`verify.txt` for a runnable transcript (exit 0).

| file | role |
|---|---|
| `descriptor.py` | `ModelPackage` container reader, auth codes, AES-256-ECB field decryption, net-description parser, `mean` data record |
| `run_record.py` | carve a record's weight buffer and execute its graph (numpy) |
| `verify.py` | self-check: AES KAT, container, 11/12 descriptors, weight closure, execution |
| `GAP_CLOSURE.md` | recovered schema, evidence (ELF offsets), and the exact remaining blockers |
| `coverage_fragment.json` | machine-readable status per record |
| `model.py`, `model_container.py`, `pipeline.py`, `validate_findings.py` | **superseded** earlier placeholder work; kept only because `report.md` cites them |

Run:

```bash
python descriptor.py models/*.model                  # container dump
python run_record.py --model models/tt_face_v10.0.model --list
python run_record.py --model models/tt_face_v10.0.model --record detect --wscale 8
python run_record.py --model models/tt_face_v10.0.model --record mean
python verify.py                                     # exit 0
```

## Status

| item | state |
|---|---|
| container (`ModelPackage`) | **real**, byte-exact on all 3 files |
| crypto | **real**: AES-256-ECB, key = `auth_code[0:32]`, field = `[ct][u32 plain_len]` |
| auth codes | recovered for `tt_face_v10.0` and `v13.0`; **`agenet` missing** |
| net description text | **real**, 11/12 records parse (3…141 layers each) |
| weight buffer layout | int8/int16 weights (`ints[9]`) + int32 bias; **byte-exact closure for `detect`, `base120det`, `image`, `slowimage`** |
| executed with real weights | `detect`, `image`, `slowimage`, `pout`, `eyecls`, `mean` |
| dequantisation scale | **not recovered** — `--wscale` exponent, outputs not claimed bit-exact |
| `Slice`/`Split` multi-top layers | not modelled → `base120`, `partface`, `iris`, `extra` do not close |

Both `trill` and `musically` ship byte-identical models and libraries, so this
directory is the same in both reports.
