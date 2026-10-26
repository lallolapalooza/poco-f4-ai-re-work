# sources/video — decompiled Java behind the frame interface

Produced with `tools/jadx/bin/jadx --single-class <class> --single-class-output … --show-bad-code --no-res`
against the `musically` APK (one class per run), for the follow-up in `report.md` §11 / `PIPELINE.md`.

| file | source class (dex) | why |
|---|---|---|
| `com.bef.effectsdk.BEFEffectNative.java` | `classes2.dex` | JNI frame/threshold surface: `nativeProcessFrame(JIIIID)`, `nativeProcessAlgorithm(JIIID)`, `nativeSetFrameOrientation`, `nativeSetCameraPosition`, `nativeCreateHandle`, `nativeInitResourceFinder` |
| `com.ss.android.vesdk.faceinfo.VEFaceDetectInfo.java` | `classes6.dex` | consumer of the native face result; `readFromParcel()` (106-point + visibility arrays, `faceExtFlag`) and `covert(FaceDetectInfo)` field mapping |
| `X.C64698PZc.java` | `classes2.dex` | per-face record: `Rect`, `float score`, `PointF[106]`, `float[106]`, yaw/pitch/roll/eyeDistance, faceID/action/trackCount, ext info |
| `X.C64697PZb.java` | `classes2.dex` | ext info: eye (22 pts), eyebrow (13), lip (64), iris (20), selected by `LJIIL` mask bits 1/2/4/8 |
| `com.ss.android.vesdk.faceinfo.VEFaceAttributeInfo.java` | `classes6.dex` | attribute result container (`faceCount`, `VEFaceAttribute[]`) |
| `com.ss.android.vesdk.faceinfo.VEFaceAttribute.java` | `classes6.dex` | 22 attribute fields; `age` is parcel field 0 (matches the shipped `agenet` model) |
| `com.ss.android.medialib.model.FaceDetect.java` | `classes4.dex` | medialib face model (rect/points/visibility/yaw/pitch/roll/eyeDistance/action/faceID/trackCount + `FaceExtInfo`) |
| `com.ss.android.medialib.model.FaceDetectInfo.java` | `classes4.dex` | thin `FaceDetect[]` wrapper used by `VEFaceDetectInfo.covert` |
| `com.ss.android.medialib.model.FaceAttribute.java` | `classes4.dex` | medialib attribute model (same 22 fields as `VEFaceAttribute`) |

Variant note: `trill` uses `PVJ`/`PVI`/`C36340EMk`/`C36453EQt` for the four obfuscated
classes; field order, types and method bodies are identical.
