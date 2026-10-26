# JAX reimplementation — QualcommVoiceActivation (OneVoice)

```
python reimpl/pipeline.py          # writes reimpl/verify.txt, exit 0
python reimpl/uim_container.py models/uim/*/*.uim
```

`pipeline.py` needs only `numpy` + `jax`; it loads the extracted front-end
coefficients from `../models/frontend/`.

## What is exact

The whole front-end is reproduced from `libqvasml.so` (disassembly in
`../native/disasm/`) with the real coefficient arrays:

| stage | implementation | source of truth |
|---|---|---|
| input | 16 kHz, mono, PCM16 → `x/32768` | `udk7_feature_spectrum` (`movi v0.2s,#0x38,lsl#24`) |
| framing | 480 samples (30 ms), hop 160 (10 ms) | `udk7_feature_process` (counter `0x1e0`, `memmove` `0x280` from `+0x140`, `sub #0xa0`) |
| window | `hann_480.npy` = symmetric Hann N=400, zero padded | `.data udk_hann_window` @0x1162a0 |
| FFT | 512-point real | `realfft` → `udk7_q_fft` |
| magnitude | `sqrt(re²+im²)`, 256 bins | `udk7_q_complex_vector_mag_f` @0x5f6c0 |
| mel | 80 bands, 470 taps, bins 5…243 | `.data udk_mel_filter_{start,len,offset,80}`, `udk7_feature_mel` |
| log | **not** in the native path | no `logf` on the mel path; `log10` only in EPD/noise/GMM |

`logmel()` (numpy) and `logmel_jax()` (JAX) agree to `7.6e-06` max abs on the
synthetic utterance in `verify.txt`.

## What is ARCHITECTURE_ONLY

`vad_apply`, `keyword_apply` (used for both 1st and 1.5 stage) and the absent
2nd-stage UV scorer are shaped from the evidence but have **random** weights:

| module | input | output | evidence for the shape |
|---|---|---|---|
| `vad_apply` | 80-mel × 5-frame context | 1 speech logit | `nnvad.*` config, `speech_float.eai` (eai2) |
| `keyword_apply(1st)` | 80-mel × 40-frame context (400 ms) | 5 logits | "fivekeywords_us", `FirstStageKW.thresholds` = 5 values |
| `keyword_apply(1.5)` | same | 5 logits | `MiddleStageKW.thresholds` = `50,52,49,37,35` |
| 2nd stage UV | keyword slice features | 1 score/keyword | `SecondStageUV.threshold.KW.0..4` |

Weights are `jax.random` with `1/sqrt(fan_in)` scaling, seeded per stage, so
runs are reproducible. Scores from these modules carry no meaning; only shapes
and the plumbing are validated.

**Superseded**: the EAI2 container is decoded now.  `eai_loader.py` parses all
16 blobs (sections, tensor descriptors, layer records, layer I/O lists, op ids),
`eai_run.py` executes them with the real weights, and `verify_eai.py` checks both
against the real `libeai_floating.so` (see `GAP_CLOSURE.md` and `verify.txt`).
The DNN VAD (`speech_float.eai`, ops gemm/relu/no_op/lstm/softmax) is verified
numerically to 3e-08 against the native interpreter; run `python run_vad.py`.

The random-weight `vad_apply`/`keyword_apply` helpers below are kept only for
the JAX front-end demonstration.  They must not be used for scores: use
`eai_run.py`, and note that the real VAD input is a 160-float qvavad feature
frame, not the 80-bin mel of `logmel()`.

Still open for the EAI2 path: `framer`/`gru`/`gather`/`conv`/`batchnorm`/
`globalavgpool`/`matmul` op semantics are transcribed but not yet
numerically verified, so the keyword (1st/1.5 stage) graphs are parsed, not
runnable; see `GAP_CLOSURE.md` §5.

## Decision layer (exact values)

`DEFAULTS` in `pipeline.py` mirrors `assets/qvaconfig_v4.xml`:
`nnvad.threshold = 891289` (0.85 × 2²⁰), `hangover 70`, `future 0`,
`cmd.end.detection.threshold 30`, `FirstStageKW.threshold 26`,
`MiddleStageKW.thresholds [50,52,49,37,35]`,
`SecondStageUV.threshold 55.0 / kwcmd 45.0` (general_UV_024) and `13.0 / 19.0`
(general_UV_015), `KW.pre/postTolerance 400/400 ms`, `UV.pre/post 200/200 ms`,
`Enrollment.threshold 30`.

## `uim_container.py`

Decodes the `.uim` header (magic 0x00180CC8, payload size, version word,
individual-model count, name fields at 0x18/0xE0) and lists every embedded
`eai2` blob with its declared size, matching the loader in
`libqvasml.so` (`sml_model_decoder` @0xf7450 → 0x102fd0, header validator
@0xf7514). Verified against all 7 shipped files.
