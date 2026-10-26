# Model inventory — miui_extraphoto

APK: `/run/media/asdf/30620d0d-1e41-4d55-af08-b4de2fb9c517/xiaomi poco f4 system/product/priv-app/ExtraPhotoGlobal/ExtraPhotoGlobal.apk`

- Local model files: **10** (24.3 MB)
- TFLite files: **0**
- Bundle containers: **0**

## Extracted from APK

document_model.dlc (357,758 B SNPE), 4 x MACE compiled caches (per SoC), libdoc_photo_process.so (25,029,048 B, model payload embedded in .rodata)

## Reimplementation artifacts

document_model.dlc graph and weights decoded; 4 MACE CL caches extracted; libdoc_photo_process.so + .rodata extracted for reimplementation.

## Not extractable / absent

- MACE doc models (hed_model, enhance_model, dewarp_*, segment, inpaint, handwrite) are embedded in libdoc_photo_process.so and are not standalone files; the .rodata payload (19.5 MB) was extracted but model boundaries are not separable.
- Megvii/ArcSoft/relighting weights are embedded/opaque; libmotion_photo_mace.so mmaps a model file that is not shipped.

## Files

- `apk_assets/assets/common/doc_photo/doc_model/dlc/document_model.dlc` — 357,758 B
- `apk_assets/assets/spes/doc_photo/doc_model/cache/mace_cl_compiled_program.bin` — 603,315 B
- `apk_assets/assets/spesn/doc_photo/doc_model/cache/mace_cl_compiled_program.bin` — 603,315 B
- `apk_assets/assets/xun/doc_photo/doc_model/cache/mace_cl_compiled_program.bin` — 577,905 B
- `apk_assets/assets/yunluo/doc_photo/doc_model/cache/mace_cl_compiled_program.bin` — 534,518 B
- `dlc_extract/dlc.metadata` — 227 B
- `dlc_extract/model` — 357,151 B
- `document_model.dlc` — 357,758 B
- `document_model_weights.npz` — 1,319,632 B
- `embedded/libdoc_photo_process.rodata.bin` — 19,540,397 B
