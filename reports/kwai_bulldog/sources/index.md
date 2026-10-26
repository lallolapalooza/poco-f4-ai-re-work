# Decompiled sources (jadx --single-class)

APK: `partner-com.kwai.bulldog_112.apk` (com.kwai.bulldog 6.0.20.524206,
sha256 `d18888e3a450569b50769a9a550358e92e4bb158608f0ba04edaafa21c1f9c7d`).

| file | class | why it matters |
|---|---|---|
| `classes/com.kwai.camerasdk.face.FaceDetectorContext.java` | FaceDetectorContext (classes15.dex) | camera face detector JNI surface: `nativeInit(sensorMgr,type,mode)`, `nativeSetData(ctx,type,path)` (model path from Java), `nativeDetectFacesFromFrame(ctx, VideoFrame, type)`; type constant `kYcnnFaceDetect` |
| `classes/com.kwai.video.westeros.v2.ycnn.YcnnPlugin.java` | YcnnPlugin (classes13.dex) | effect-side ycnn plugin (`nativeCreateYcnnPlugin`, `nativeSetParamInfo` JSON `{"attr_local_path": ...}`), salient/face-attribute/general-recog callbacks |
| `classes/com.kwai.kve.VisionEngine.java` | VisionEngine (classes16.dex) | KVE entry: SmartEdit/SmartCover/SmartGallery task builders, loads `libvisionengine` |
| `classes/com.kwai.video.editorsdk2.kve.EditorKveVoiceDetectParam.java` | EditorKveVoiceDetectParam (classes16.dex) | carries `modelPath` for the voice detector (`voice_detect_mode.tflite`) |
| `classes/com.yxcorp.gifshow.util.magic.b.java` | MagicEmojiResourceHelper (classes5.dex) | model resource registry: `check.json` MD5 validation, `magic_ycnn_model_*` keys, cleanup of `mmu`/`ycnn_face_detect`/`ylab`/`face_3d_resource` |
| `classes/com.yxcorp.gifshow.util.magic.c.java` | ModelResourceUtil (classes5.dex) | local root resolution: `<cache>/.magic_model/magic_model` else `<cache>/magic_effect_resource/`; `e(resource)` = root+name |
| `classes/com.yxcorp.gifshow.util.resource.model.c.java` | MagicModel (classes5.dex) | one downloadable model: `getInitDownloadUrl` from CDN config, dir via ModelResourceUtil, `checkMd5`, `useYcnnModelConfig()==true` |
| `sources/classes/resource_model_a.java` | com.yxcorp.gifshow.util.resource.model.a (classes5.dex) | resource table incl. `VOICE_DETECT` → `voice_detect_mode.tflite`, `CUT_MATTING`, response field names |
| `classes/com.yxcorp.gifshow.model.response.i.java` | ConfigResponse (classes4.dex) | config payload: `modelResources`, `kwaiPreloadModels` (wifi/4G/2G lists), `voice_detect_mode_v1`, `model_matting_v1`, `modelResources` |
| `classes/com.yxcorp.gifshow.model.response.r0.java` | YlabModelConfigResponse (classes4.dex) | `modelUrls[{resourceKey, urls[{cdn,url}]}]` – ylab model URL mapping |
| `classes/com.yxcorp.gifshow.model.response.c.java` | BaseConfigResponse (classes4.dex) | interface `getCdnCount/getDownloadUrlSuffix` |
| `classes/com.yxcorp.plugin.magicemoji.download.MagicFaceDownloadManager.java` | MagicFaceDownloadManager (classes5.dex) | effect-package download into `<files>/.magic_emoji/<mid>_<urlLastSegment>`, requires `params.txt`/`params_720.txt` |

Extraction command pattern:

```
$JADX --single-class <class> --single-class-output <abs path>.java \
      --show-bad-code --no-res <apk>
```

jadx occasionally fails (`failed to redirect`) on inner classes/very large
classes; the list above is the successful subset used for the report.

## Added by the frame-pipeline patch (2026-10-24)

All files in `classes/`, produced with the same
`jadx --single-class … --show-bad-code --no-res` invocation (batched through
`tools/decompile_classes.py`, `FaceMagicController` run directly because the batch
wrapper timed it out).

| file | class | why it matters |
|---|---|---|
| `classes/com.kwai.kscnnrenderlib.KSJNILib.java` | KSJNILib (classes14.dex) | exact native surface of the ycnn runtime (32 methods), incl. `renderModel(Object,byte[])`, `updateModelOut`, `createModel`, `YCNNGetConfig2Model` |
| `classes/com.kwai.kscnnrenderlib.KSRenderObj.java` | KSRenderObj (classes14.dex) | Java driver of the engine: `runModelBuffer` passes `data_0` bytes then calls `updateModelOut` under `synchronized(this)` |
| `classes/com.kwai.kscnnrenderlib.YCNNModelInfo.java` | YCNNModelInfo (classes14.dex) | `YCNNModelIn`/`YCNNModelConfig`/`KSLandmarksParam`/`KSFaceInfo` and the model-type id table |
| `classes/com.kwai.kscnnrenderlib.YCNNModelInfo$YCNNModelIn.java`, `…$KSFaceInfo.java`, `…$KSPoseInfo.java`, `…$KSInputInfo.java`, `…$KSHeadSegOut.java` | inner structs | per-field input/output contract |
| `classes/com.kwai.kscnnrenderlib.YCNNComm.java` | YCNNComm (classes14.dex) | `KSImage` (`KSImage.setData` = direct ByteBuffer copy), `KSPtInfo`, `KSRectf` |
| `classes/com.kwai.kscnnrenderlib.{YCNNModel,KSCNN,YTTechDepth,YTTechMatting,ImgRecog}.java` | wrappers | alternate entry points over `KSRenderObj` |
| `classes/mo.g0.java` | FaceData (classes15.dex) | serialized result of `nativeDetectFacesFromFrame`; wire tags 1-8 |
| `classes/mo.e1.java`, `mo.n0.java`, `mo.m0.java`, `mo.q0.java` | Rect / FacePose / FaceLandmark / Gender | nested messages of FaceData |
| `classes/mo.h0.java` | FaceDetectConfig (classes15.dex) | detector config passed to `nativeSetFaceDetectConfig` |
| `classes/mo.{i0,j0,k0,l0}.java` | FaceDetectInitType / FaceDetectMode / FaceDetectType / FaceDetectorName | enum values used by the JNI ints |
| `classes/com.kwai.video.westeros.v2.faceless.FaceMagicController.java` | FaceMagicController (classes13.dex) | effect/slot control, `native*` table, `WesterosGlProcessor` subclass |
| `classes/com.kwai.video.westeros.v2.faceless.FacelessPlugin.java` | FacelessPlugin (classes13.dex) | plugin bootstrap and library load order |

Cross-variant reference (from `partner-com.kwai.video_33.apk`, **not** this APK):
`../evidence/gap_pipeline/video_variant/com.kwai.camerasdk.video.VideoFrame.java`
— the frame class that bulldog's DEX does not contain.
