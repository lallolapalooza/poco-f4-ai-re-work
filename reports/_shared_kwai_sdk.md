# Kwai SDK — shared native findings (from `kwai_video` / `com.kwai.video` 6.0.20.524206)

Applies to the sibling variants with identical `lib/arm64-v8a` sets
(`com.kwai.bulldog`, `com.kwai.kuaishou.video.live`).  Verified against
`partner-com.kwai.video_33.apk`
(sha256 `a0050c589bf6d748e2c721c86e5e0f5e254e594aa88822fe304810af9611f98e`).
Full evidence: `reports/kwai_video/` (`report.md`, `ir/`, `native/`, `sources/`).

## 1. No models inside the package (confirmed)

* 0 assets matching `.tflite/.onnx/.fbm/.model/.pb`; `apk_inventory.py --extract-models` → 0.
* `carve_models.py` over **all 80** libs → no `TFL3` payload
  (`evidence/carve_all_libs.txt`); the only hit is the literal string `debug.tflite`.
* Every network is a **downloaded resource pack**, handed to native as a path.

## 2. Inference stack

| lib | role | JNI |
|---|---|---|
| `libykit.so` (10.3 MB) | **YCNN engine** (`ycnn::`, `ycnn_net::`, 3103 exports) | 30 (`com.kwai.kscnnrenderlib.KSJNILib`) |
| `libykit_module.so` (15.8 MB) | AI-edit generator, links **TFLite 2.4.1** | 11 (`com.kwai.aieditlib.AIEditJNILib`) |
| `libykit_core_plugin.so` | per-frame providers: salient / general-recog / face-attribute | 12 |
| `libfacedetectcontext.so` | camera face detect (`kuaishou::face::YcnnFaceDetector`) | 26 |
| `libFaceMagic.so` (7.7 MB) | AR/beauty renderer (consumes landmarks/masks) | 1802 |
| `libskwai.so` (6.5 MB) | `SKwai::VirtualFaceProcessor` GL/Lua AR scene graph | 0 (C++ API) |
| `libgorgeous.so` | beauty SDK — **all GLSL is plain rodata** | – |
| `libvisionengine.so` | KVE smart-edit analyzer (DAGs), bitrate predictor | 95 |
| `libtensorflow-lite.so` | TF Lite 2.4.1, 215 builtin ops, NNAPI+XNNPACK | 2612 exports |

## 3. YCNN I/O contract (reuse verbatim)

```java
YCNNModelIn{ byte[] data_0; int colorType=3; int rotate, flipHor; long frame_time;
             boolean single_image; int width, height;
             LinkedList<KSRectf> faces; int normOut, normRotate, normFlipHor, normFlipVer;
             String param_info; }
```
* `KSJNILib.renderModel(Object, byte[])` — **one raw frame**; engine accepts only
  `grey` / `NV21` / `NV12` and converts via `ycnn::YCNNCropDataMatrix`.
* Outputs: `KSFaceDetectOut{faces: KSFaceInfo{index, left, top, width, height,
  confidence, yaw, pitch, roll, pos: KSPtInfo{xPos,yPos,zPos,valid}[]}}`,
  `KSMattingOut{mask: KSImage, contours}`, `KSHeadSegOut{seg: KSImage}`,
  `KSSceneClassOut{infos: [[{type:int, conf:float}]]}`, `KSHumanPoseOut`,
  `KSInstanceSegOut`, `KSTaskDetectOut`, `KSGeneralRecogOut{out_json}`,
  `KSInpaintingOut{out: KSImage}`.  `KSImage{width,height,channel,ByteBuffer,range}`.
* `YCNNModelConfig{model_type, model_sub_type, model_files, model_path, param,
  model_type_string, model_type_param, assetManager}`.
* Model type ids: HumanMatting 0, HandGesture 1, Hair 2, Finger 3, Humanpose 4,
  **Landmarks 5**, Handpose 6, SceneClass 7, Salient 8, Sky 9, FaceAttribute 10,
  DepthCamera 11, GeneralHandpose 12, Landmarks3D 13, HumanKeyPose 14,
  Benchmark 15, Portrait 16, Plane 17, HandSeg 18, StyleTransfer 19, FaceSeg 20,
  **Detector 21**, HDRNet 22, InstanceSeg 23, TaskDetect 24, TaskPoint 25,
  TaskImage 26, HeadSeg 28, NailSeg 29, MakeupSeg 30, HumanParsing 31,
  FaceSimilarity 32, FaceCluster 33, Inpainting 53, GeneralRecog 54.
* `KSLandmarksParam` defaults: `maxFaceNum=4, minFaceSize=50, detectIntervals=5,
  detectMode=4 (SuperFast), useImageTrack=2 (TRACK_128)`.
* Container: YCNN **v3/v4**, binary or JSON (`net_w`, `net_h`, layer list);
  kernels `ycnn_net::*OpNeon` (fp16/ARMv8.2, Winograd), `ksnet::KSOpenCL*`,
  MTK APU via `libycnn_tflite_plugin.so` + `ANeuroPilotTFLite_*`.

## 4. Model catalogue (132 names, `libykit.so` + `libykit_module.so`)

`KSModelDetector`, `KSModelLandmark01..15`, `KSModelFaceAttributes01..04`,
`KSModelMatting01..08`, `KSModelHumanparsing01..03`, `KSModelHeadSeg01`,
`KSModelHair01`, `KSModelMakeup01/02`, `KSModelMakeupSeg01..03`,
`KSModelHumanpose01`, `KSModelHumanKeypoint01/02`, `KSModelHumanKeyPose01`,
`KSModelHumanMesh01/Half`, `KSModelHandpose01..10`, `KSModelHandSeg01/02`,
`KSModelGeneralHandpose01`, `KSModelGesture01`, `KSModelFinger01/02`,
`KSModelInstanceSeg01/02`, `KSModelDepth01..03`, `KSModelHdr01`,
`KSModelSky01/HBKSModelSky01B`, `KSModelClothSeg01`, `KSModelFaceSeg01`,
`KSModelFaceSimilarity01..03`, `KSModelGeneralRecog01`, `KSModelGeneralSeg`,
`KSModelInpainting(.Video)`, `KSModelAR01..06/ARPlane02`,
`KSModelAnimalLandmarks01..04`, `KSModelDogLandmarks01..04`.
Runtime ids: `magic_ycnn_model_{head_seg, matting, face_seg, humanmesh_half,
ycnnv3, heart_estimation, …}` with `{"missing_models": [...]}` /
`{"finish_load_models": [...]}` status JSON.
AI-edit: `AIEdit_FaceSeg_01..04.fbm`, `AIEdit_HairSeg_02..09.fbm`.

## 5. Acquisition path (confirmed)

* Resource enum `com.yxcorp.gifshow.util.resource.model.a`:
  `CUT_MATTING("model_matting_v1", "ks://download_model_matting")`,
  `VIDEO_LIKE_EFFECTS("effects_like_motion", …)`,
  `VOICE_DETECT("voice_detect_mode", …)`, `STICKER`, `THEME`, `EMOJI`, `EDITOR`;
  each has `useYcnnModelConfig()`.
* `com.yxcorp.gifshow.util.resource.model.c` ("MagicModel.java"): resource type 4,
  CDN URL from `response.getDownloadUrlSuffix(resource, cdnIndex)` (multi-CDN retry),
  MD5 check `com.yxcorp.gifshow.util.magic.b.k(dir)`, unzip dir
  `com.yxcorp.gifshow.util.magic.c.e(resource)`, `addToMagicModelsResourceList()`.
* Paths / endpoints in dex: `/rest/o/production/ycnn/models`,
  `/facerecognition/ycnnmodel`, `/Ycnn/modelA`, `/.magic_model/magic_model`,
  `%s/model_asset`, `duguModelPath`.
* Native receives only paths: `AE2WesterosWrapperCreator$Resource.{ycnnModelPath,
  ycnnModelPathMap, mmuModelPath, dirtyLensProcessorModelPath, face3dResPath,
  deformJsonPath}`, `westeros.ResourceManager.nativeSetDirtyLensDetectModelPath`,
  `nativeSetYlabModelPathConfig`, `YcnnPlugin.setAttrLocalPath`,
  `aiedit.pbs.AIEditParamGan.{model_path, model_dir, gan_model_path,
  landmark_model_path}`.
* ykit stores: `IDataStore` impls `MemDataStore`, `DiskDataStore`, `YFileDataStore`,
  `YCfgDataStore`; "**YCNN model only supports DiskDataStore**";
  `tflite_from_filepath`, `tflite_from_datastore`.

## 6. Beauty stack facts (renderer, no NN)

`libgorgeous.so` rodata contains the full GLSL:
5-tap mean (`sum*0.2`) + detail energy `min(((i-mean)*7)^2,1)`, 3×3 blur `*0.1111`,
`kMin=(1-meanVar/(meanVar+0.1))*clamp((min(in.r,blur.a)-0.2)*4,0,1)*blurAlpha[*skinMask]`,
Hald LUT (`lookup()`, 8×8 tiles, 512², 64 blue levels), luma unsharp
`high=(Yori-Ylow)*(1-base.x²)*sharpness`.
Real weight in the APK: `assets/default_enhance_lut.png` (64×64 RGBA8 = 8×8×8 Hald CLUT,
sha256 `832f73e1d47c55627dadb03ea7820b07d6198703e0667d67f7284c95554bf9f1`).
A JAX transcription + identity/parity checks live in
`reports/kwai_video/reimpl/` (`python pipeline.py`, RESULT: PASS).

## 7. Useful symbol addresses

| symbol | lib | address |
|---|---|---|
| `ycnn::YCNNGetConfig2Model` | libykit.so | `0x5585dc` (19,228 B) |
| `ykit::IModel::createModel` | libykit.so | `0xe9e80` (7,172 B) |
| `Java_..._KSJNILib_renderModel` | libykit.so | `0xc8360` (1,780 B) |
| `Java_..._KSJNILib_updateModelOut` | libykit.so | `0xc8a54` (13,300 B) |
| `Java_..._SmartUploadAnalyzer_openWithModelTypeNative` | libvisionengine.so | `0x169d18` (108 B) → `kveOpenCape` |
| `Java_..._FaceDetectorContext_nativeDetectFacesFromFrame` | libfacedetectcontext.so | see `native/fdc_detectFaces.s` |
