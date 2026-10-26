# YCNN inference contract (Kwai "skwai/ykit" NN engine)

Status: **contract confirmed from code**, **model files absent from the APK**.

Everything below is quoted or transcribed from artifacts in this report:

| Item | Source |
|---|---|
| JNI surface | `libykit.so` exports (`Java_com_kwai_kscnnrenderlib_KSJNILib_*`), `native/ykit_renderModel.s`, `native/ykit_createModel.s` |
| Java structs | `sources/classes/com.kwai.kscnnrenderlib.YCNNModelInfo.java` (decompiled, classes14.dex) |
| Python wrappers | `sources/classes/com.kwai.kscnnrenderlib.KSRenderObj.java`, `YCNNModel.java`, `KSJNILib.java` |
| Engine internals | `libykit.so` exported `ycnn::*` symbols, rodata strings (`ycnn net binary model version : %d, %d, %d`, `net_w`, `net_h`, `magic_ycnn_model_*`, `KSModel*.model`) |

## 1. Call sequence

```
KSRenderObj.createRender(YCNNModelConfig)          -> Java handle
KSJNILib.YCNNGetConfig2Model(path, cfg)            -> parses the .model header into cfg
KSJNILib.createModel(obj)   / createCPUModel()     -> ycnn::YCNNModel::createModel / createModel2
KSRenderObj.runModelBuffer(YCNNModelIn)            -> KSJNILib.renderModel(obj, in.data_0)
                                                      then KSJNILib.updateModelOut(obj)
KSRenderObj.getLandmarks/getHumanMattingOut/...    -> typed output copies
KSJNILib.releaseModel(obj)
```

`renderModel` takes a **single `byte[]`** (the raw camera frame).  The engine
crops/scales it in `ycnn::YCNNCropDataMatrix(srcY, srcUV, dst, w, h, stride,
KSY_COLOR_TYPE, KSY_COLOR_RANGE_TYPE, mean, …, KSCNNInputColorType, ksrect,
float scale, bool)`; only `grey`, `NV21` and `NV12` are accepted
(`libykit.so` rodata: "only support nv21/nv12", "only support grey/nv21/nv12
for grey crop").

## 2. Input tensor contract (`YCNNModelInfo.YCNNModelIn`)

| field | type | note |
|---|---|---|
| `data_0` | `byte[]` | packed frame; size = `w*h` (grey) or `w*h*3/2` (NV21/NV12) |
| `colorType` | `int` | default **3** in the decompiled class; the enum values are the `KSCNNInputColorType` used by `YCNNCropDataMatrix` |
| `width`, `height` | `int` | frame dimensions |
| `rotate`, `flipHor` | `int` | orientation applied before inference |
| `single_image` | `boolean` | still vs. stream |
| `faces` | `LinkedList<KSRectf>` | crop rects (landmark refinement / ROI) |
| `normOut`, `normRotate`, `normFlipHor`, `normFlipVer` | `int` | normalised-output transform |
| `frame_time` | `long` | stream timestamp |
| `param_info` | `String` | extra JSON |

Network input resolution is **not** in the Java layer: it comes from the
model file header (`net_w`, `net_h` keys handled by
`ycnn::YCNNGetConfig2Model`).  Nothing in the APK fixes it, so every
resolution claim would be a guess -> listed under "unknowns".

## 3. Model configuration (`YCNNModelInfo.YCNNModelConfig`)

```java
public int model_type = -1;
public int model_sub_type = 0;
public LinkedList<String> model_files = new LinkedList<>();
public String model_path  = "";
public String param       = "";
public String model_type_string = "";
public String model_type_param  = "";
public AssetManager assetManager = null;
```

`model_type` values (exact constants, `YCNNModelInfo`):

```
0 HumanMatting   1 HandGesture  2 Hair        3 Finger        4 Humanpose
5 Landmarks      6 Handpose     7 SceneClass  8 Salient       9 Sky
10 FaceAttribute 11 DepthCamera 12 GeneralHandpose 13 Landmarks3D 14 HumanKeyPose
15 Benchmark     16 Portrait   17 Plane      18 HandSeg      19 StyleTransfer
20 FaceSeg       21 Detector   22 HDRNet     23 InstanceSeg  24 TaskDetect
25 TaskPoint     26 TaskImage  28 HeadSeg    29 NailSeg      30 MakeupSeg
31 HumanParsing  32 FaceSimilarity 33 FaceCluster 53 Inpainting 54 GeneralRecog
```

## 4. Output tensors

| Accessor | Struct | Contents |
|---|---|---|
| `getLandmarks()` | `KSFaceDetectOut` | `inputInfo`, `LinkedList<KSFaceInfo> faces` |
| | `KSFaceInfo` | `index:long, pos:LinkedList<KSPtInfo>, left/top/width/height/confidence/yaw/pitch/roll: float` |
| | `KSPtInfo` | `xPos, yPos, zPos: float, valid: int` |
| `getHumanMattingOut()` | `KSMattingOut` | `mask: KSImage`, `contours: LinkedList<KSContourInfo>` |
| `getHeadSegOut()` | `KSHeadSegOut` | `seg: KSImage` |
| `getHumanposeOut()` | `KSHumanPoseOut` | `ArrayList<KSPoseInfo>{pos, range:KSRectf, index, pose_type}` |
| `getSceneClassOut()` | `KSSceneClassOut` | `ArrayList<ArrayList<KSSceneClassInfo{type:int, conf:float}>>` (top-N per frame) |
| `getGeneralRecogOut()` | `KSGeneralRecogOut` | `out_json: String` |
| `getInstanceSegOut()` | `KSInstanceSegOut` | `LinkedList<KSInstanceSegInfo{index, img:KSImage}>` |
| `getTaskDetectOut()` | `KSTaskDetectOut` | `LinkedList<KSTaskDetectInfo{index,left,top,width,height,confidence,type}>` |
| `getInpaintingOut()` | `KSInpaintingOut` | `out: KSImage` |

`KSImage` (from `YCNNComm`) is `{data buffer, width, height, channel,
range:KSRect}` — see `KSRenderObj.addKSImage` (lines 70-83), which allocates a
direct `ByteBuffer` and fills `range = (left, top, width, height)`.

Landmark **count** is model-specific (`pos` is a `LinkedList`, no constant in
the APK) -> unknown.

## 5. Detector / landmark parameters (`KSLandmarksParam`, defaults)

```
maxFaceNum=4  minFaceSize=50  detectIntervals=5  detectMode=4 (SuperFast)
detectSync=0  firstFrameValid=0  detectEar/Tongue/Eyeball=0
useAdjustMode=1 useSubEye=1 useSubMouth=1 useCorrectMode=1
useEyeAutoSwitch=1 useDetect360=1 useRobust3D=0 useSmoothCropRect=0
stickFaceCropRect=true stickTrackRect=true useSmoothMode=0 useImageTrack=2 (TRACK_128)
```

`KSLandmarksType`: `Robust=0, Fast=1, Image=2, RECT=3, SuperFast=4`.
`KSMaskOutType`: `Norm=0, Source=1, Raw=2, Smooth=3, Post=4`.

## 6. Execution backends

From `libykit.so` exported symbols / rodata:

* CPU: `ycnn_net::*OpNeon` (fp16, ARMv8.2), Winograd conv, DWConv, OpenMP.
* GPU: `ksnet::KSOpenCL*`, `BufferNHWCToImage`/`ImageToBufferNHWC` kernels.
* MediaTek APU: `ANeuroPilotTFLite_*` shim + `libycnn_tflite_plugin.so`,
  `libtflite_mtk.so` (`tflite_from_filepath`, `tflite_from_datastore`).
* Model container: YCNN **v3/v4**, binary **or** JSON
  ("ycnn net binary model version : %d, %d, %d",
  "ycnn net json model version : %d, %d, %d", `Load Binary/Json Model Version : %s`).
  Layer types present in the engine: Convolution / DWConv / Pooling / BatchNorm /
  PReLU / Sigmoid / Eltwise / Concat / Reshape / Deconv / Slice / Reorg / Sample.

## 7. Why there is no IR dump here

`tools/tflite_ir.py` / `tflite2jax.py` need an actual model.  This APK contains
**zero** `.tflite`/`.model`/`.onnx` files (see `report.md` §models and
`evidence/02_carve_all_libs.txt`), so no flatbuffer IR exists to dump; the
YCNN container is a proprietary format whose parser
(`ycnn::YCNNGetConfig2Model`, 19,228 bytes at `libykit.so:0x5585dc`) would need
a model to exercise.
