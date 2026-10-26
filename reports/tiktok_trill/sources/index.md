# sources/

Targeted decompilation (jadx 1.5.6 `--single-class`, per-dex to avoid loading
the whole 119 MB APK). Only classes on the model → inference path.

| file | class | dex | why |
|---|---|---|---|
| `classes/ModelsList.java` | `com.bef.effectsdk.ModelsList` | classes2.dex | hard-codes the three model asset paths |
| `classes/EffectSDKUtils.java` | `com.bef.effectsdk.EffectSDKUtils` | classes2.dex | copies `assets/model/**` to the effect model dir at first run |
| `classes/InferenceEngineServiceImpl.java` | `com.ss.android.ugc.aweme.inferenceengine.InferenceEngineServiceImpl` (+ anonymous `$1`) | classes5.dex | the ML-SDK evaluator: ByteNN create/init/inference glue |
| `classes/X_8Kf.instructions.txt` | `X.8Kf` | classes5.dex | same evaluator (jadx marks the class DONT_GENERATE; Dalvik listing via androguard, identical logic) |
| `classes/API.java` | `com.ss.bytenn.API` | classes6.dex | 9 JNI entry points implemented by `libbytennwrapper.so` |
| `classes/ByteNNConfig.java` | `com.ss.bytenn.ByteNNConfig` | classes6.dex | `init(forwardType, ByteBuffer model, String[] , String, String)` + thread count |
| `classes/Tensor.java` | `com.ss.bytenn.Tensor` | classes6.dex | `setData(ByteBuffer)`, `getData()`, dtype/format enums |

Notes
* `X.8Kf` is the object returned by `InterfaceC01778Jz.LIZ()` inside
  `InferenceEngineServiceImpl`: `LIZ(MappedByteBuffer, MLConfigModel)` builds the
  engine, `LIZ(ByteBuffer, float[][])` runs one inference.
* Model bytes reach the engine as a memory-mapped model file
  (`MappedByteBuffer`) or as a `ByteBuffer`; `ByteNNConfig.init(...)` receives it.
* No Java code parses the `.model` bytes; the container is consumed natively.

## Patch follow-up classes (frame/data-flow path)

| file | class | dex | why |
|---|---|---|---|
| `classes/NaviGLSurfaceView.java` | `com.ss.android.ugc.aweme.view.customView.NaviGLSurfaceView` | classes6.dex | the only caller of `BEFEffectNative.processFrame`; shows (inTex, outTex, w, h, System.nanoTime()/1e9) |
| `classes/C36620EXe.java` | `X.C36620EXe` (alias of `X.EXe`) | classes6.dex | render context: creates the 5 GL_TEXTURE_2D/RGBA8 textures + FBOs used as effect input/output |
| `classes/BEFEffectNative.java` | `com.bef.effectsdk.BEFEffectNative` | classes2.dex | JNI surface incl. `nativeProcessFrame(JIIIID)I`, orientation/camera setters |
| `classes/VEFaceDetectInfo.java` | `com.ss.android.vesdk.faceinfo.VEFaceDetectInfo` | classes6.dex | wire order of per-face fields; `covert(FaceDetectInfo)` |
| `classes/PVJ.java` | `X.PVJ` | classes2.dex | per-face struct: rect, score, 106 points, visibility, yaw/pitch/roll, eyeDistance, faceID, action, trackCount, PVI |
| `classes/X.PVI.java` | `X.PVI` | classes2.dex | face extension: eye/eyebrow/lip/iris point sets and their counts (22/13/64/20) |
| `classes/com.ss.android.medialib.model.FaceDetect.java` | `…medialib.model.FaceDetect` (+`FaceExtInfo`) | classes4.dex | named getters for the same fields (mapping key) |
| `classes/com.ss.android.medialib.model.FaceDetectInfo.java` | `…medialib.model.FaceDetectInfo` | classes4.dex | `FaceDetect[]` container |
| `classes/VEEffectParams.java` | `com.ss.android.vesdk.VEEffectParams` | classes6.dex | effect param holder (context) |
| `classes/com.bef.effectsdk.view.ViewControllerInterface.java` | `…view.ViewControllerInterface` | classes2.dex | sibling frame API `processFrame(J,I,I,I,[F,[F,D)` (view-controller path) |
| `classes/ModelnamesAssigner.java` | `com.bef.effectsdk.ModelnamesAssigner` | classes2.dex | `setAssignedModelNames(handle, names, paths)` — model-name override for the resource finder |
| `classes/RequirementResourceMapper.java` / `RequirementDefine.java` | `com.bef.effectsdk.*` | classes2.dex | requirement → resource mapping (`peekResourcesNeededByRequirements`) |
| `classes/com.ss.android.ttve.nativePort.TEEffectUtils.java` | `…ttve.nativePort.TEEffectUtils` | classes4.dex | effect utils (no frame call; checked while locating the caller) |
