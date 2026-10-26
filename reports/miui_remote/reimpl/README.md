# reimpl — MiBrain on-device VAD (`vad_2.1.0_0`) in JAX

Reconstruction of the only neural component in `XMRemoteController.apk`:
the CornVad / Soil VAD embedded in `libmibrainvad2.so`.

## Files

| file | purpose |
|---|---|
| `soil_binn.py` | parser for the "soil-binn" container that wraps the model (`object > list > {am, conf, cmvn}`) |
| `vad_model.py` | walks the Soil binary weight stream exactly like `ComponentLoad`+`LoadDependence`, extracts config/weights, and implements the JAX forward pass |
| `frontend.py` | Kaldi-style MFCC front-end (16 kHz, 25/10 ms, pre-emphasis 0.97, Povey window, 512-pt FFT, 40 mel, 40 ceps), CMVN, ±10-frame stacking |
| `pipeline.py` | end-to-end demo: synthetic PCM → features → network → posterior smoothing → segment decoder |

## Run

```bash
cd reimpl
/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python pipeline.py            # uses ../models/vad_2.1.0_0.binn
/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python vad_model.py ../models/vad_2.1.0_0.binn   # model IR to stdout
```
`verify.txt` holds the recorded run (exit 0).

## Recovered graph

```
input(840 = 40 ceps x 21 frames)
  affine 840->300   W1[300,840] + b1[300]   (real weights, blob offset 51)
  pnorm  out_size=100                        (300 -> 100, window 3)
  affine 100->300   W3[300,100] + b3[300]   (real weights, offset 1009287)
  pnorm  out_size=100                        (300 -> 100)
  normalize(size=100, need_divide=1, rms=1.0, source=internal)
  normalize(same)
  affine 100->2     W7[2,100] + b7[2]       (real weights, offset 1130595)
  softmax
```

## Status / limitations

* **Weights**: extracted, not approximated. The three affine layers are the real floats.
* **Graph**: recovered from the binary stream; the parser consumes the 1,131,400-byte
  payload exactly (`end == am_offset + am_length`).
* **ARCHITECTURE_ONLY**: `pnorm` and `normalize` arithmetic. Only their configuration is
  in the model file (`out_size`, `need_divide`, `target_rms`); the propagate functions
  were not decompiled to source-level certainty. Current implementation:
  `pnorm(x) = (sum_{window} |x|^p)^(1/p)`, `normalize(x) = x / rms(x) * target_rms`.
* **CMVN**: the blob stores 2 x 41 floats whose magnitudes (~1e10) are incompatible with
  log-cepstra; `frontend.cmvn()` detects this and falls back to on-line CMVN
  (`mode=online` in `verify.txt`). The true feature domain (log vs power) is unresolved.
* **Numerics not verified** against the ARM library: no aarch64 execution environment was
  available, so `verify.txt` proves shapes/plumbing and exit status, not output equality.

## Cloud part (not implemented here)

ASR/NLU/TTS are cloud services. See `report.md` §4.2 for the WebSocket endpoints,
`AI_CMD_*`/`RESULT_*` message ids, auth headers and the Opus/MP3 codec contract.
