# sources — targeted decompiled Java

| file | source | why |
|---|---|---|
| `classes/ModelsList.java` | `classes2.dex` → `com.bef.effectsdk.ModelsList` | **the only Java reference** to the three `.model` assets (static `list`) |
| `classes/EffectSDKUtils.java` | `classes2.dex` → `com.bef.effectsdk.EffectSDKUtils` | takes `ModelsList.list` as `assetFiles` and copies the assets to the app model dir (`copyAssets`, `flushAlgorithmModelFiles`, `needUpdate`) |
| `classes/ModelnamesAssigner.java` | `classes2.dex` | JNI bridge `nativeSetAssignedModelNames*` used to bind model names to the effect resources |
| `classes/API.java` | `classes6.dex` → `com.ss.bytenn.API` | 9 native methods: CreateEngine/InitEngine/SetEngineInputs/Inference/GetEngineOutputs/… |
| `classes/ByteNNConfig.java` | `classes6.dex` | `init(ForwardType, ByteBuffer model, String[] inputNames, String oclKernelBinPath, String modelName)`, numThread, forwardType |
| `classes/Tensor.java` | `classes6.dex` | NCHW/NHWC, Int8/Float/Fp16, ByteBuffer data |
| `video/*.java` | follow-up §11: frame interface (see `sources/video/index.md`) | `BEFEffectNative`, `VEFaceDetectInfo`, `X.C64698PZc`, `X.C64697PZb`, `VEFaceAttributeInfo`, `VEFaceAttribute`, medialib face models |

Produced with the bundled jadx in single-class mode directly on the extracted
dex (`jadx --single-class <class> --no-res -d OUT classesN.dex`).  Full-APK
jadx runs failed (no class emitted), single-dex runs succeed.

