# Data-pipeline integration index (15 non-Google APKs)

Scope: the intersection of the pasted table fragment and the on-device-model/runtime set, excluding Google apps.

Each report has `PIPELINE.md` (9 sections per pipeline: source/trigger, transport/format, preprocessing, model invocation, postprocessing, consumer/action, failure/fallback, confidence/open questions, reimplementation mapping), updated `findings.json` (`data_pipelines`), and appended evidence.

| slug | app | AI pipeline | data_pipelines | features | reimpl |
|---|---|---|---:|---:|---|
| [`miui_securitycenter`](reports/miui_securitycenter/PIPELINE.md) | MIUI Security (com.miui.securitycenter) | SMS spam/phishing cascade, new TFLite engine, delegated AI subtitles/UltraClear | 7 | 6 | verified |
| [`miui_securitycoreadd`](reports/miui_securitycoreadd/PIPELINE.md) | Security Core Add (com.miui.securitycore) | AI auto-brightness inference + on-device training | 2 | 5 | verified |
| [`miui_mediaeditor`](reports/miui_mediaeditor/PIPELINE.md) | Mi Media Editor (com.miui.mediaeditor) | Face/body beauty, scene recognition, makeup, inpaint | 4 | 7 | verified |
| [`miui_extraphoto`](reports/miui_extraphoto/PIPELINE.md) | ExtraPhoto (com.miui.extraphoto) | Document scan, relighting, dual-cam bokeh | 3 | 10 | runs_unverified |
| [`miui_biometric`](reports/miui_biometric/PIPELINE.md) | Biometric face unlock | Enroll/auth, liveness, embedding, template match | 2 | 4 | architecture_only |
| [`miui_paymentservice`](reports/miui_paymentservice/PIPELINE.md) | Payment Service | Card digit OCR (HOG + LIBSVM) | 1 | 1 | verified |
| [`miui_remote`](reports/miui_remote/PIPELINE.md) | Mi Remote (XMRemoteController) | Voice capture -> VAD -> cloud ASR/NLU -> IR/TTS | 2 | 5 | runs_unverified |
| [`qcom_voiceactivation`](reports/qcom_voiceactivation/PIPELINE.md) | Qualcomm Voice Activation | Always-on keyword detection/enrollment (OneVoice) | 2 | 6 | architecture_only |
| [`mtb_modemtestbox`](reports/mtb_modemtestbox/PIPELINE.md) | Modem Test Box | Radio-metric weak-signal forecast (TFLite) | 1 | 1 | verified |
| [`snapchat`](reports/snapchat/PIPELINE.md) | Snapchat | Haar/ERT/ORB lens CV, ML Kit, deferred .dnn.enc | 5 | 8 | verified |
| [`tiktok_musically`](reports/tiktok_musically/PIPELINE.md) | TikTok (musically) | Face detect/landmark/age -> video effects | 2 | 6 | architecture_only |
| [`tiktok_trill`](reports/tiktok_trill/PIPELINE.md) | TikTok (trill) | Face detect/landmark/age -> video effects | 2 | 5 | architecture_only |
| [`kwai_video`](reports/kwai_video/PIPELINE.md) | Kwai | Camera -> YCNN face/beauty -> encode | 4 | 6 | architecture_only |
| [`kwai_bulldog`](reports/kwai_bulldog/PIPELINE.md) | Kwai Bulldog | Camera -> YCNN face/beauty -> encode | 3 | 6 | architecture_only |
| [`kwai_live`](reports/kwai_live/PIPELINE.md) | Kwai Live | Live camera+audio -> effects -> push | 3 | 9 | runs_unverified |

## Key corrections made by the pipeline pass

| app | correction |
|---|---|
| MIUI Security | SMS entry is exported `AntiSpamProvider` (`content://antispam/sms_judge`, case 9), not `SmsReceiver`/`AntiSpamService`; per-message re-parse of assets/dict+model. |
| Security Core Add | The framework never calls `predictBrightness` for live brightness; the model output (nits) is used to build per-category splines after training. Input is raw lux / lux bucket / app category with 3 s debounce. |
| ExtraPhoto | Shipping MACE document model is 384x384x3 -> 384x384x2; the 512x512 DLC descriptor belongs to the enhance model. MiBrain relight gesture is a no-op (light always centered). |
| Media Editor | `com.miui.mediaeditor.algo.*` IS present in the APK (prior U3 was wrong); only the FaceUnity layer is obfuscated. |
| Biometric | Camera2 640x480 YUV_420_888 -> NV21, angle 90; unlock status 203 to SystemUI; template is a `pb.TemplateFile` protobuf. |
| Payment Service | Camera1 NV21 Y-plane only, direct buffer, 11 JNI exports; confidence gate before autofill. |
| Mi Remote | TTS decoder has no PCM sink in this build (prior "TTS played" wrong); auth is APPIDTOKEN, OAuth helper never created. |
| Qualcomm VA | No always-on AudioRecord; detection is sound-trigger HAL/DSP -> `WakewordToggleService` -> `SoundTriggerModule`; clients are binder-only. |
| Modem Test Box | Entry is secret code `*#*#24744625#*#*`; measurements are pulled via OemHook, not callbacks; exact LTE/NR byte offsets in PIPELINE. |
| Kwai | Missing `libdaenerys.so`/`libwesteros.so` added; camera YUV format chosen by plane pixelStride; live audio runs through Stannis. |
| TikTok | Confirmed caller is `NaviGLSurfaceView.onDrawFrame` in both variants; `processFrame(handle, inputTex, outputTex, w, h, t)`; musically's "no Java caller" claim corrected; adaptive cadence 2->4->interval/3 and letterbox resize recovered. |
| Snapchat | No DNN ships; per-frame path uses OES texture + Haar/ERT/ORB classical CV; AI Factory `.dnn.enc` models are downloaded, not bundled. |

## Running the reimplementations

All JAX examples live under `reports/<slug>/reimpl/`. Example:

```bash
cd reports/<slug>
/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python reimpl/verify.py     # or pipeline.py / model.py --demo
```

Status `verified` = numerical check against vendor/TFLite reference or bit-exact stage check; `runs_unverified` = runs on synthetic input, no vendor runtime reference; `architecture_only` = exact contract/architecture, weights unavailable/encrypted.
