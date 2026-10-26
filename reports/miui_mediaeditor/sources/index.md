# Decompiled classes (jadx `--single-class`)

APK: `product/data-app/MIMediaEditorGlobal/MIMediaEditorGlobal.apk` (only the
classes below were decompiled; no full-APK decompilation).

| class | why it matters |
|---|---|
| `com.google.mlkit.nl.languageid.bundled.internal.ThickLanguageIdentifier` | loads `assets/tflite_langid.tflite.jpg` via `nativeInitFromBuffer`, calls `nativeIdentifyPossibleLanguages(handle, str.getBytes(cs), threshold)`; `IdentifiedLanguage(String,float)` result |
| `com.faceunity.core.support.FURenderBridge` | Java→JNI bridge into `libCNamaSDK.so` (`Faceunity.fuImageBeautyPreview`, `fuRenderBundles…`), drives the beauty render loop |
| `com.faceunity.core.controller.facebeauty.FaceBeautyController` | `loadBundleFile path:` / `applyControllerSubBundleAction` — the controller that applies a `.bundle` |
| `com.faceunity.core.enumeration.FUAITypeEnum` | AI type ids used with the FuAI API (`FUAITYPE_FACEPROCESSOR_IMAGE_BEAUTY=65536`, `…_DETECT_LANDMARKS=8388608`, `…_BINDI_SEG_PROCESSOR=67108864`, …) |
| `com.miui.gallery.editor.photo.app.remover.Inpaint` | loads `libvis/libinpaint/libinpaint_jni`, native `inpaintBmpData`, `upsampleBmpData`, `releaseNNF` |
| `com.miui.mediaeditor.aigc.remover.InpaintProJni` | `nativeInit(j,j2,path,path2)`, `nativeInpaint(bitmap,bitmap,bitmap,…)`, `nativeUpsample` — external model dirs |
| `com.miui.mediaeditor.aigc.remover.InpaintProAlgorithm` | singleton wrapper, object/people/line masks (`TypeManual/People/Line/Objects`) |
| `com.miui.mediaeditor.aigc.cloud.inpaint.InpaintEdgeCloudJni` | cloud/edge inpaint JNI wrapper |
| `com.milab.aieditsdk.AiEdit` | `System.load(getDir("libs")/libMNN.so)` then `libaiedit.so`; `init(String path)`, `process(handle,int[],float[])` |
| `com.miui.gallery.editor.photo.app.beauty.animalbeauty.AnimalBeautyJni` | pet beauty: `getLibDir`, `runSegmentModels(Bitmap,Bitmap)`, `adjustAllBeauty(...)` |
| `com.miui.mediaeditor.detection.scene.SceneDetector` | declares `libyuv/libcamera_scene/libFaceDetLmd/libscene_detection` (none shipped in this APK) |

`hits.json` holds the ranking data of both `decompile_classes.py` runs.

---

## Patch 2026-10-24 — data-path decompiles (`sources/classes2/`)

jadx `--single-class`, one class per file, logs in `sources/logs2/`. FaceUnity SDK
class names are obfuscated in the APK; the role column is the reconstructed meaning.

| class | role |
|---|---|
| `com.faceunity.core.biz.FUCoreBizApiProxy` | biz proxy: `a(0)`=IFUCoreBeautyImpl, `a(1)`=IFUCoreEditorImpl, `a(2)`=IFUCoreVideoImpl (default/other -> beauty) |
| `com.faceunity.core.biz.impl.IFUCoreBeautyImpl` | beauty biz impl: smart style table, per-face ImageBeautyData build (fuIsTracking/image_auto_style) |
| `com.faceunity.core.biz.impl.IFUCoreEditorImpl` | photo biz impl: preprocess (mode AUTO), result bitmap list, render request |
| `com.faceunity.core.biz.impl.IFUCoreVideoImpl` | video biz impl: fuRenderImg path, release sequence |
| `com.faceunity.core.cache.CacheData` | export payload {ImageBeautyData, buffer, ProcessMode} |
| `com.faceunity.core.controller.BaseSingleController` | controller lifecycle: HandlerThread, bundle load/bind, fuItemSetParam/fuImageBeautySetParam |
| `com.faceunity.core.entity.ImageBeautyData` | persisted per-face state (5 slots, image_face_data JSON) |
| `com.faceunity.core.enumeration.FUInputBufferEnum` | FU_FORMAT_* buffer enum (999 bitmap, 2 NV21, 0 YUV, 4 RGBA) |
| `com.faceunity.core.enumeration.ProcessMode` | FU_IMAGE_BEAUTY_MODE_* / FU_IMAGE_EDITOR_MODE_* values |
| `com.faceunity.core.faceunity.FURenderKit` | singleton tying FURenderBridge, FUCoreBizApiProxy and the ImageBeauty model |
| `com.faceunity.core.model.BaseSingleModel` | per-face param plumbing ({"faceid":N,"param":…}) |
| `com.faceunity.core.renderer.gltexturerender.BaseGLTextureRenderer` | GL thread: onSurfaceCreated preprocess, onDrawFrame action lists, pause/resume cache |
| `com.faceunity.core.renderer.gltexturerender.a` | EGL helper |
| `com.faceunity.core.renderer.gltexturerender.c` | save/export runnable: manual-grid commit + fuImageBeautyGetResultBitmap |
| `com.faceunity.core.renderer.gltexturerender.d` | GLTexturePhotoRendererNoCache: bitmap/NV21 upload, EXIF rotate, preview draw |
| `com.faceunity.core.support.SDKController` | thin fu* wrappers + fuGetSystemError handling |
| `com.faceunity.wrapper.Faceunity` | all 1000+ JNI declarations of libCNamaSDK (fuImageBeauty*/fuRender*/fuCreateItemFromPackage…), format + status constants, library load order (libfuai.so then libCNamaSDK.so) |
| `com.miui.gallery.editor_common.library.a` | dynamic-feature library id table (sFaceBeautyBundles=5002005 …) + getDir("libs") |
| `com.miui.mediaeditor.algo.base.BaseAlgorithm` | algo dispatch base (init h(), process a()/e()) |
| `com.miui.mediaeditor.algo.beauty.body.BodyBeautyAlgorithm` | body reshape algo |
| `com.miui.mediaeditor.algo.beauty.common.BeautyBaseManager` | engine init/release (paths, detect mode, max faces) |
| `com.miui.mediaeditor.algo.beauty.common.b` | ProcessMode data-source singletons (auto/face beauty/face warp/body slim) |
| `com.miui.mediaeditor.algo.beauty.common.core.render.GlTexturePhotoRenderer` | editor-side renderer wrapper (saveData executor, crop/zoom matrices) |
| `com.miui.mediaeditor.algo.beauty.common.core.widgets.BeautyGlView` | GLSurfaceView + init(bitmap, mode) + saveData callback |
| `com.miui.mediaeditor.algo.beauty.common.processer.a` | BeautyExportDataParam processor → BeautyExportDataResult bitmap |
| `com.miui.mediaeditor.algo.beauty.face.FaceBeautyAlgorithm` | face beauty algo: processor map |
| `com.miui.mediaeditor.algo.beauty.face.a` | FaceBeautySDKManager: bundle list + wrinkle remover |
| `com.miui.mediaeditor.algo.beauty.face.processer.a` | slider → m2.a param (image_blur/aver/white/threed/red…, /100 vs /200 scaling) |
| `com.miui.mediaeditor.algo.beauty.faceshape.FaceShapeAlgorithm` | face reshape algo (facewarp bundle) |
| `com.miui.mediaeditor.algo.detect.FaceUnityDetectAlgorithm` | detection gate: landmark/body bundle load + release |
| `com.miui.mediaeditor.beauty.module.facebeauty.FaceBeautyViewModel` | beauty menu/parameter model (FaceBeautyCategory/FaceBeautyMenuEntity) |
| `com.miui.mediaeditor.beauty.module.facebeauty.render.FaceBeautyRenderFragment` | beauty UI: GL view, magnifier, export trigger |
| `com.miui.mediaeditor.photo.remover.RemoverRenderFragment` | removal UI/render fragment |
| `com.miui.mediaeditor.photo.remover.a` | removal helper |
| `com.miui.mediaeditor.photo.remover.model.RemoverNNFData` | NNF correspondence map parcelable {w,h,index,count,nnf} |
| `com.miui.mediaeditor.photo.remover_pro.InpaintAIEngine` | inpaint AI engine (local vs edge-cloud selection) |
| `com.xiaomi.milab.videosdk.XmsContext` | video SDK context incl. setupBeauty/shutDownBeauty native API |
| `com.xiaomi.milab.videosdk.XmsVideoFilter` | video filter node (beauty/filter in the MLM timeline) |
| `f2.b` | BundleManager: fuCreateItemFromPackage, render-order list, fuDestroyAllItems |
| `j2.a` | bundle descriptor {path,name,type} |
| `j2.c` | render input data (buffer, dimensions, process mode, transform meta) |
| `j2.d` | render result {texId,width,height} |
| `k2.a` | FuAI controller: fuLoadAIModelFromPackage / fuReleaseAIModel, max faces/humans |
| `kg.c` | body-slim manager: graphics/image_beauty_bodyslim.bundle + ai_human_processor_beautybody_mb |
| `l2.a` | renderer callback interface |
| `m2.a` | ImageBeauty model: per-face param setters (image_white/aver/blur/threed/red…), image_face_data |
| `pg.a` | face-shape manager: graphics/image_beauty_facewarp.bundle + ai_beauty_type/ai_face_beauty |

