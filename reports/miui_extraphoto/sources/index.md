# Decompiled sources (jadx `--single-class`, targeted)

APK: `product/priv-app/ExtraPhotoGlobal/ExtraPhotoGlobal.apk`
(`com.miui.extraphoto`, 1.8.7.4.0-global, 228,682,351 B).
All classes come from `classes.dex` (single dex, 4785 defined classes).

## `refocus/` — algorithm dispatch and relighting processors

| file | why |
|---|---|
| `...manager.AlgoManager$*.java` | `AlgoManager` (jadx emitted the whole class for each inner class): loads `relight_only_extraphoto` + `mibrain_relighting_jni`, `refocus_mibokeh`, `miphone_refocus_bokeh` + `micamera_dual_bokeh_jni`, `wa_refocus_extraphoto` + `megvii_bokeh_jni`, `mialgo_portrait`, ArcSoft libs. Feature gating by portrait type (`mi_portrait`, `megvii_portrait[_google]`, `soft-portrait-enc`, `portrait`, `mialgo_portrait`). |
| `...core.relighting.MiBrainRelightingProcessor.java` | JNI call sequence `initBrainRelightingEffect` / `setBrainRelightingLightSource` / `enableBrainRelightingEffect` / `releaseBrainRelightingHandle`; blur image via `processMiCameraRefocus`. |
| `...core.relighting.MiBrainRelightingProcessorPortrait.java` | same JNI, blur image via `DualPhotoJni.processRefocus(..., shineLevel, shineThreshold, filterId, ...)`. |
| `...core.relighting.MiBrainRelightingProcessorSoftEncrypted.java` | `processMiRefocus` / `processMiRawDepth` variant for the `soft-portrait-enc` profile. |
| `...core.relighting.MegviiRelightingProcessor.java` | same relight backend; blur image via `wa::Refocus` (`MegviiDualPhotoJni.processRefocus`). |
| `...manager.MiDualPhotoJni.java` | native declarations: `initMiCameraRefocus(jsonPath)`, `processMiCameraRefocusWidthHandle`, `initBrainRelightingEffect`, `setBrainRelightingLightSource`, `enableBrainRelightingEffect`, `initDynamicSpot`, ... |
| `...manager.DualPhotoJni.java` | generic native image/data helpers + `processRefocus`, `processMiRefocus`, `processMiRawDepth`, `initRelightingEffect` (ArcSoft path). |
| `...manager.MegviiDualPhotoJni.java` | `initRefocus()` / `processRefocusWidthHandle(...)` / `releaseRefocus()` for `wa::Refocus`. |

## `relight_ui/` — UI → effect selection → processor calls

| file | why |
|---|---|
| `...relighting.RelightingOperatingFragment.java` | touch gesture `setLightSource(f,f2)` scales by `DualPhotoNativeContext.getScale()`, posts `SetLightSourceRunnable` → `processor.setLightSource(...)` + `processor.enableRelightingEffect(config.getEffectType(), ...)`; save path re-runs with `doSave`. Hides effect `0x3` for soft images and `0x18` outside India. |
| `...relighting.RelightingPresenter.java`, `MovieRelightingPresenter.java` | presenter wiring for photo/movie relighting. |
| `...relighting.RelightingGestureView.java` | drag gesture emitting light-source coordinates. |
| `...adjuest.MegviiAdjustProcessor.java` | `F_VALUE` / `ALG_F_VALUE` f-number ladders, `findFValueIndex(blurLevel)`, focus point scaling and `wa::Refocus` invocation (`focusX, focusY, algFValue, depthW, depthH, portraitType`). |

## `docphoto/` — document-photo feature and JNI

| file | why |
|---|---|
| `...manager.DocProcessor.java` | copies `assets/common/doc_photo/doc_model/dlc` and `assets/<DEVICE>/doc_photo/doc_model/cache` into `filesDir/doc_model/{dlc,cache}`; calls `DocumentProcessJNI.nativeInitAndRun(cacheDir, modelDir)` (first run, when no cache) and `nativeInit(cacheDir, modelDir)`; scan/enhance/crop/dewarp/de-handwrite entry points. |
| `...manager.DocPhotoManager.java`, `...DocPhotoManager$DocPhotoParser.java` | document-photo activity state, image parsing, calls into `DocProcessor`. |
| `...refocus.manager.MiDualPhotoJni.java` / `DualPhotoJni.java` | (same files as above, decompiled under the docphoto run) |

## `docphoto2/` — OCR SDK JNI surface

| file | why |
|---|---|
| `com.xiaomi.ocr.sdk.imgprocess.DocumentProcessJNI.java` | `System.loadLibrary("doc_photo_c++_shared"); loadLibrary("doc_photo_process")`; native methods `nativeInit`, `nativeInitAndRun`, `nativeScanDocumentBitmap`, `nativeCropAndEnhanceBitmap`, `nativeEnhanceBitmap`, `nativeDewarpBitmap`, `nativeHandwriteRemoveBitmap`, `nativeGetDelegateColor`, `nativeGetVersion`, `nativeSupportFeatures`. |
| `...docphoto.document.DocPhotoAccessUtils.java` | device/capability gating (`isNeedUseCache`, `isSupportHandwrite`, `isSupportMoer`). |

## `access/` — capability gating

| file | why |
|---|---|
| `com.miui.extraphoto.refocus.RefocusAccessUtils.java` + `$SupportDevice` | codename allow-list for each backend (`isSupport`, `isMi`, `isMiMTK`, `isMegvii`, `isBrianRelight`, `isSupportMiAlgo`, `isMeitu`, `isForbidRelight`). |
| `com.xiaomi.ocr.sdk.imgprocess.DocumentProcess$EnhanceType` | `RAW, COLOR, BIN, GRAY, FILTER_ID_CARD, DEMOIRE`. |
| `com.xiaomi.ocr.sdk.imgprocess.DocumentProcess$DocumentType` | `DEFAULT, ID_CARD, PPT`. |

## `docphoto_flow/` — document-photo consumer chain (patch round)

| file | why |
|---|---|
| `...docphoto.document.DocPhotoActivity.java` | activity entry: `Storage.fromIntent`, `DocPhotoManager.parsePhotoAsync`, `ExportTask`, `setResult(uri,"image/jpeg","extra_photo_editor_type_doc_photo")`, failure toast/exit. |
| `...docphoto.document.DocPhotoPostProcessingActivity.java` | camera post-processing entry (`DocPhotoManager(..., exporter=true)`). |
| `...docphoto.manager.PhotoManager.java` | `ParseTask`/`PreviewTask` AsyncTasks, `updateImageUri` MediaStore row construction. |
| `...docphoto.ExportTask.java` | background export, cancel/delete, `onExport(File,Uri)`/`onError`. |
| `...docphoto.manager.DocPhotoUtils.java` | `decodeBitmapWithSample`, JPEG **q97** + EXIF/ICC writer, `defaultExportExif` tag stripping. |
| `...docphoto.manager.PhotoMetaData.java`, `...DocPhotoMetaData.java` | EXIF orientation/size + XMP `docPhoto` parse (`version`, `enhanceType`, `cropPoints`, `rawLength`, `privacyWatermark`). |
| `...docphoto.Util.java`, `...common.utils.MatrixUtils.java` | delegate↔raw coordinate matrices, 8-float point (de)serialisation. |
| `...common.utils.ImageUtils.java`, `...BigBitmapLoadUtils.java`, `...InSampleInfo.java` | decode sampling, rotation/mirror, memory-budget inSampleSize. |

## `relight_core/` — relighting processors beyond the base class (patch round)

| file | why |
|---|---|
| `...core.relighting.ArcSoftRelightingProcessor.java` | the only processor that sends real gesture coordinates to native: `DualPhotoJni.setRelightingLightSource(handle,x,y,originW,originH)`; `supportAdjustLightSource()==true`; exposes `mFacePoint[4]`/`mLightingSource[2]`. |
| `...core.relighting.RelightingProcessorPortrait.java` | ArcSoft/mi-portrait init (`initRelightingEffect`) + `enableRelightingEffect` + save path using the same buffer contract. |
| `...core.relighting.RelightingProcessorSoftPortrait.java`, `...RelightingProcessorSoftEncrypted.java` | soft-portrait variants (`processMiRefocus`/`processMiRawDepth`). |
| `...refocus.DualPhotoOperatingFragment.java` | shared fragment: save/render plumbing and `SaveFinishRunnable`. |
| `...core.relighting.RelightingGestureFragment.java` | gesture overlay. |

## `refocus_core/` — bokeh/adjust context and save helpers (patch round)

| file | why |
|---|---|
| `...core.adjuest.AdjustProcessor.java`, `...AdjustOperatingFragment.java` | bokeh slider/save: `RefocusRunnable` posted on the **render handler**, `runOnRenderThread(SaveRunnable)`, full-res `processRefocusOnSave`, JPEG q100 write. |
| `...manager.DualPhotoNativeContext.java` | `mScale = originW/processW`, focus/blur rescaling, `generateNativeImage`, `configYUVData`/`configDisplayData`. |
| `...PhotoInfoProvider.java` | process/origin dimensions, depth dimensions, focus, blur level, profile, orientation/colour space. |
| `...model.NativeImage.java`, `...NativeData.java` | thin wrappers over native ASVL/depth pointers. |
| `...manager.DualPhotoFileUtils.java` | relight/bokeh JPEG **q100** + EXIF (strips `focusPoint`, `depthMapVersion`, `Xmp`) + watermark. |

Each subdirectory also contains jadx `logs/` and `hits.json`/`index.md` from
`tools/decompile_classes.py`.
