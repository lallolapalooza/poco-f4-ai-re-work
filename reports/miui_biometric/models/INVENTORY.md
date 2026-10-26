# Model inventory — miui_biometric

APK: `/run/media/asdf/30620d0d-1e41-4d55-af08-b4de2fb9c517/xiaomi poco f4 system/product/app/Biometric/Biometric.apk`

- Local model files: **10** (16.4 MB)
- TFLite files: **0**
- Bundle containers: **0**

## Extracted from APK

8 model blobs embedded in libstfaceunlockocl.so (STEF v3, encrypted)

## Reimplementation artifacts

Blob names, sizes and SHA-256 are in models/manifest.json; JAX pipeline is architecture-only.

## Not extractable / absent

- All 8 CNN payloads are encrypted; keys come from the SenseTime protector license chain at runtime. Extracted as .bin blobs for reimplementation, not decryptable statically.

## Files

- `IM_RGB_Liveness_General_Full_FP32_10_1_65_half_compression_v2_origin.bin` — 4,106,513 B
- `M_Align_CalcPose_Ann_106_2_3_0.bin` — 123,153 B
- `M_Align_Deepface_Eye_State_Analysis_3_7_8.bin` — 1,771,793 B
- `M_Align_occlusion_106_1_19_3.bin` — 1,782,033 B
- `M_Align_occlusion_106_360_1_7_1.bin` — 2,058,513 B
- `M_Attribute_MTNet_Quality_1_0_0.bin` — 471,313 B
- `M_Hunter_CommonFace_Gray_ppl_360_9_2_5_v2_origin.bin` — 983,313 B
- `M_Verify_MobileNetV2T6_Common_3_135_0_half_compression_v2_origin.bin` — 5,110,033 B
- `extract_blobs.py` — 4,407 B
- `manifest.json` — 5,320 B
