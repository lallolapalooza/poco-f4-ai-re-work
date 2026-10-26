# Model inventory — miui_mediaeditor

APK: `/run/media/asdf/30620d0d-1e41-4d55-af08-b4de2fb9c517/xiaomi poco f4 system/product/data-app/MIMediaEditorGlobal/MIMediaEditorGlobal.apk`

- Local files under `models/`: **767** (187.1 MB)
- TFLite copies: **74** = **33 canonical** in `models/tflite/` + 41 duplicate copies inside `bundle_entries/`
- Bundle containers: **22**

## Extracted from APK

22 .bundle containers (70.99 MB) + assets/tflite_langid.tflite.jpg (315,520 B)

## Reimplementation artifacts

All 22 bundles extracted; 678/726 ZIP entries decrypted; 33/38 TFLite models extracted to models/tflite/. The missing 1024-byte prologue key is U1 in report.md.

## Not extractable / absent

- 5 TFLite models are in encrypted bundle prefixes and cannot be decrypted: acne_mole_acnenet_1206_3x24x24_quat.tflite, age_gender160_0422.tflite, Bdycntr.0406.Exp387c.x3e210_quan1.tflite, bindi_seg_tiny_2024_0924.tflite, MOBILEONE_S2_SIZE384_EXP30_DATA7.0.0_FP16.tflite.
- 3 config.json/metadata entries and 39 shader/image entries in the same encrypted prefixes are also skipped (47 entries total).

## Files

- `apk_assets/assets/graphics/image_beauty_basic.bundle` — 2,915,687 B
- `apk_assets/assets/graphics/image_beauty_bodyslim.bundle` — 18,457 B
- `apk_assets/assets/graphics/image_beauty_facebeauty.bundle` — 4,103,446 B
- `apk_assets/assets/graphics/image_beauty_facewarp.bundle` — 340,615 B
- `apk_assets/assets/graphics/image_editor_autocrop.bundle` — 4,769 B
- `apk_assets/assets/graphics/image_editor_enhance.bundle` — 3,994,454 B
- `apk_assets/assets/graphics/image_editor_idphoto.bundle` — 6,111 B
- `apk_assets/assets/graphics/image_editor_portrait.bundle` — 5,532 B
- `apk_assets/assets/graphics/image_editor_scenebeauty.bundle` — 174,160 B
- `apk_assets/assets/makeup/item_bundle/jingdian.bundle` — 374,406 B
- `apk_assets/assets/makeup/item_bundle/jingfu.bundle` — 123,080 B
- `apk_assets/assets/makeup/item_bundle/liti.bundle` — 231,584 B
- `apk_assets/assets/makeup/item_bundle/nanshi.bundle` — 84,719 B
- `apk_assets/assets/makeup/item_bundle/oumei.bundle` — 385,035 B
- `apk_assets/assets/makeup/item_bundle/wenrou.bundle` — 196,743 B
- `apk_assets/assets/model/ai_beauty_type_processor.bundle` — 10,299,493 B
- `apk_assets/assets/model/ai_face_attribute_processor.bundle` — 1,912,131 B
- `apk_assets/assets/model/ai_face_beauty_processor.bundle` — 21,870,544 B
- `apk_assets/assets/model/ai_face_bindi_seg_processor.bundle` — 512,057 B
- `apk_assets/assets/model/ai_face_processor_detect_landmark.bundle` — 6,450,804 B
- `apk_assets/assets/model/ai_human_processor_beautybody_mb.bundle` — 6,244,199 B
- `apk_assets/assets/model/ai_photo_editing_processor_scene_recog.bundle` — 10,741,303 B
- `bundle_extraction.json` — 4,829 B
- `bundles/Fprs.0003.Exp007e33_quan1_sm.tflite` — 1,196,952 B
- `bundles/config_ai_face_attribute_processor.json` — 3,982 B
- `bundles/config_ai_face_beauty_processor.json` — 17,992 B
- `bundles/config_ai_face_bindi_seg_processor.json` — 4,577 B
- `bundles/config_ai_human_processor_beautybody_mb.json` — 4,320 B
- `bundles/face_landmark_mb360_64.tflite` — 346,788 B
- `bundles/face_mask_del_spot_protect_mask_0815A.data` — 518,412 B
- `bundles/face_mask_mapper_face_mask.json` — 7,721 B
- `bundles/face_mask_mapper_skin_mask.data` — 518,412 B
- `bundles/facecheck40_dense_2000000_regress.tflite` — 155,144 B
- `bundles/hair_1209a_acnenet_crop0.1pos0.1_3x24x24_quat.tflite` — 117,360 B
- `bundles/head.tflite` — 7,804 B
- `bundles/image_beauty_facewarp_face_warp.json` — 111,979 B
- `bundles/image_chin_warp_fs.glsl` — 1,162 B
- `bundles/makeup_jingdian_config.json` — 2,144 B
- `bundles/makeup_jingdian_script.js` — 1,116 B
- `bundles/mole_seg_24x24x3_1206_quat.tflite` — 144,408 B
- `bundles/s1.tflite` — 672,836 B
- `tflite/model__ai_beauty_type_processor__dense_eb.tflite` — 436,824 B
- `tflite/model__ai_beauty_type_processor__dense_eye_iris.tflite` — 504,020 B
- `tflite/model__ai_beauty_type_processor__dense_iris.tflite` — 200,152 B
- `tflite/model__ai_beauty_type_processor__dense_mouth.tflite` — 453,380 B
- `tflite/model__ai_beauty_type_processor__face_landmark_lite_210818_360.tflite` — 484,264 B
- `tflite/model__ai_beauty_type_processor__model_hair.tflite` — 2,791,684 B
- `tflite/model__ai_beauty_type_processor__mouth_checker_14.tflite` — 32,332 B
- `tflite/model__ai_beauty_type_processor__n-0.5_416_0.05_4aspect6.tflite` — 1,864,936 B
- `tflite/model__ai_beauty_type_processor__n-small_256_0.05_only_a5.tflite` — 101,380 B
- `tflite/model__ai_beauty_type_processor__s1_112_98.tflite` — 2,106,152 B
- `tflite/model__ai_beauty_type_processor__s2.tflite` — 594,960 B
- `tflite/model__ai_beauty_type_processor__s3.tflite` — 987,788 B
- `tflite/model__ai_face_beauty_processor__Fprs.0003.Exp007e33_quan1_sm.tflite` — 1,196,952 B
- `tflite/model__ai_face_beauty_processor__FprsTeeth.0001.Exp002pe99_quan1_sm.tflite` — 598,456 B
- `tflite/model__ai_face_beauty_processor__hair_1209a_acnenet_crop0.1pos0.1_3x24x24_quat.tflite` — 117,360 B
- `tflite/model__ai_face_beauty_processor__highlight_1209a_acnenet_crop0.2pos0.1_3x24x24_quat.tflite` — 116,640 B
- `tflite/model__ai_face_beauty_processor__lines_1209a_acnenet_crop0.1pos0.1_3x24x24_quat.tflite` — 117,360 B
- `tflite/model__ai_face_beauty_processor__mobile_spade_256_ch16_from16_40000_ft16_ftsave_yunfu.tflite` — 9,968,196 B
- `tflite/model__ai_face_beauty_processor__mobile_spade_256_ch16_from16_ftsevere_lr0.0001_117_16001.tflite` — 9,968,400 B
- `tflite/model__ai_face_beauty_processor__mole_seg_24x24x3_1206_quat.tflite` — 144,408 B
- `tflite/model__ai_face_beauty_processor__skin_model_expand_supply_pta18000_quant_resizetrain_seg153.tflite` — 2,433,488 B
- `tflite/model__ai_face_processor_detect_landmark__Fpe.0007.Exp101.ERefine3e70_refine1.tflite` — 1,473,180 B
- `tflite/model__ai_face_processor_detect_landmark__Fpe.0007.Exp101.ERefine3e70_refine2.tflite` — 1,100,456 B
- `tflite/model__ai_face_processor_detect_landmark__Fpe.0007.Exp101.ERefine3e70_refine3.tflite` — 948,808 B
- `tflite/model__ai_face_processor_detect_landmark__Fpe.0007.Exp101.XScore.s76e150_base.tflite` — 735,956 B
- `tflite/model__ai_face_processor_detect_landmark__face_landmark_mb360_64.tflite` — 346,788 B
- `tflite/model__ai_face_processor_detect_landmark__facecheck40_dense_2000000_regress.tflite` — 155,144 B
- `tflite/model__ai_face_processor_detect_landmark__head.tflite` — 7,804 B
- `tflite/model__ai_face_processor_detect_landmark__n-0.5_416_0.05_4aspect6.tflite` — 1,864,936 B
- `tflite/model__ai_face_processor_detect_landmark__n-small_256_0.05_only_a5.tflite` — 101,380 B
- `tflite/model__ai_face_processor_detect_landmark__s1.tflite` — 672,836 B
- `tflite/model__ai_human_processor_beautybody_mb__FULLBODY_POFH2D_20210302_0001_e1604_q.tflite` — 1,268,024 B
- `tflite/model__ai_human_processor_beautybody_mb__human_detector_retinanet_mobilenetv2_1.0_quant.tflite` — 2,674,640 B
- `tflite_langid.tflite` — 315,520 B

- `bundle_entries/` — 692 decrypted entries (see bundle_extraction.json)

- `tflite/` — 74 extracted TFLite models
