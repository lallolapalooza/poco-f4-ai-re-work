# Model inventory — qcom_voiceactivation

APK: `/run/media/asdf/30620d0d-1e41-4d55-af08-b4de2fb9c517/xiaomi poco f4 system/system_ext/priv-app/QualcommVoiceActivation/QualcommVoiceActivation.apk`

- Local model files: **31** (31.5 MB)
- TFLite files: **0**
- Bundle containers: **0**

## Extracted from APK

7 .uim sound models + 16 EAI2 blobs + audio front-end tables

## Reimplementation artifacts

All .uim containers and embedded EAI2 blobs extracted; front-end mel/Hann tables extracted; JAX architecture-only.

## Not extractable / absent

- EAI2 layer tables/tensor shapes are not decoded; blobs are extracted but opaque. ASR Whisper/DLC model is downloaded at runtime.

## Files

- `eai/63ff0ae5_en-US_001_198088.eai` — 8,492 B
- `eai/63ff0ae5_en-US_001_58312.eai` — 6,076 B
- `eai/63ff0ae5_en-US_001_64968.eai` — 132,752 B
- `eai/63ff0ae5_en-US_001_968.eai` — 56,928 B
- `eai/63ff0ae5_en-US_002_1208.eai` — 408,936 B
- `eai/63ff0ae5_en-US_002_410552.eai` — 41,772 B
- `eai/63ff0ae5_en-US_036_200136.eai` — 8,492 B
- `eai/63ff0ae5_en-US_036_67016.eai` — 132,752 B
- `eai/63ff0ae5_en-US_038_1224.eai` — 41,508 B
- `eai/63ff0ae5_en-US_038_161736.eai` — 13,708 B
- `eai/63ff0ae5_en-US_038_43208.eai` — 5,820 B
- `eai/63ff0ae5_en-US_038_49864.eai` — 111,372 B
- `eai/63ff0ae5_en-US_040_189384.eai` — 8,492 B
- `eai/63ff0ae5_en-US_040_56264.eai` — 132,752 B
- `eai/general_UV_015_526296.eai` — 10,572,796 B
- `eai/speech_float_speech_0.eai` — 36,632 B
- `frontend/frontend.json` — 203 B
- `frontend/hann_480.f32` — 1,920 B
- `frontend/hann_480.npy` — 2,048 B
- `frontend/mel_len.npy` — 448 B
- `frontend/mel_matrix_80x256.npy` — 82,048 B
- `frontend/mel_offset.npy` — 448 B
- `frontend/mel_start.npy` — 448 B
- `frontend/mel_taps.npy` — 2,008 B
- `uim/1.5_stage/63ff0ae5_en-US_002.uim` — 452,536 B
- `uim/1st_stage/63ff0ae5_en-US_001.uim` — 206,792 B
- `uim/1st_stage/63ff0ae5_en-US_036.uim` — 208,840 B
- `uim/1st_stage/63ff0ae5_en-US_038.uim` — 175,560 B
- `uim/1st_stage/63ff0ae5_en-US_040.uim` — 198,088 B
- `uim/ubm/general_UV_015.uim` — 11,099,104 B
- `uim/ubm/general_UV_024.uim` — 7,304,344 B
