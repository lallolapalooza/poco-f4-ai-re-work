# Evidence 07 — camera frame → native frame → face detector (JNI/buffer trace)

Libraries (extracted from `partner-com.kwai.video_33.apk`, `lib/arm64-v8a/`):

| lib | size | sha256 |
|---|---:|---|
| `libdaenerys.so` | 2,580,368 | `3b0758bf5b7343f38e5ba7b3746896f13dbb3821e2b5bdd8752738f733b146ee` |
| `libwesteros.so` | 1,395,688 | `add716bd946fb63c46cd6e087028113e0dc5441cd8467f264fa1ae3f1372d305` |
| `libykit.so` | 10,292,232 | `f0723a105fa946512501e048f08d6dbed1584231f6a6c399989a59174d558b69` |
| `libykit_core_plugin.so` | 592,000 | `41693ab1572190d6dbafd92dbf451052664739986d528020544f55c2e11584ee` |

`libdaenerys.so` was **not** in the previous lib extraction (the 90 MB cap
skipped it); it is the Daenerys camera pipeline core that owns the
`Java → C++` frame bridge and every `Video*Workshop`/`*Processor` class.

---

## 1. `kuaishou::android::VideoRawFrameFromJni(JNIEnv*, jobject)` — 0xece3c, 6048 B

Disassembly: `native/daenerys_videorawframe.s` (1512 instructions).
Resolved `adrp`+`add` literal targets: `native/daenerys_videorawframe.strings.txt`.

The function reads the `com.kwai.camerasdk.video.VideoFrame` object field by
field through `kuaishou::dbase::AndroidClass` (`GetIi` = `GetIntField`,
`GetIh` = `GetBooleanField`, `GetIl` = `GetLongField`, `Get` = `GetFieldID`,
`Call<T>` = `Call<Type>Method`):

| pc | Java member | JNI signature | use |
|---|---|---|---|
| `0xece84` | class `com/kwai/camerasdk/video/VideoFrame` | — | `AndroidClass` ctor |
| `0xecea4` | `type` | `I` | discriminator (`VideoFrame.YUV=1`, `TEXTURE=2`, `RGBA=3`, `BITMAP=4`, `None=0`) |
| `0xecec0` | `yuv_format` | `I` | pixel layout of the CPU buffer (see §3) |
| `0xeceec` | `bitmap` | `Landroid/graphics/Bitmap;` | `type==4` branch → `AndroidBitmap` (`AndroidBitmap_lockPixels`) |
| `0xecf1c` / `0xecf30` | `width` / `height` | `I` | bitmap branch dimensions |
| `0xed0a8` | `textureNotUsedRunnable` | `Ljava/lang/Runnable;` | `type==2` branch, callback if texture unused |
| `0xed0f8` | `textureId` | `I` | GL texture name |
| `0xed10c` | `isOesTexture` | `Z` | external OES vs 2D |
| `0xed288` / `0xed29c` | `width` / `height` | `I` | texture branch dimensions |
| `0xed2b0` | `timestamp` | `J` | frame PTS |
| `0xed2d0` | `getSerializedAttributes` | `()[B` | protobuf `VideoFrameAttributes` → `VideoFrameAttributes::ParseFromArray` (0xed32c) |
| `0xed428` | `data` | `Lcom/kwai/camerasdk/videoCapture/FrameBuffer;` | CPU branch |
| `0xed450` | class `com/kwai/camerasdk/videoCapture/FrameBuffer` | — | |
| `0xed4a0` | `isDirect` | `()Z` | direct ByteBuffer fast path |
| `0xed4c8` | `byteBuffer` | `Ljava/nio/ByteBuffer;` | `GetDirectBufferAddress` |
| `0xed604` | `getByteArray` | `()[B` | fallback copy for heap buffers |
| `0xed7c4`… | `timestamp`, `getSerializedAttributes` | `J`, `()[B` | CPU branch metadata |
| `0xed9cc` | `release` | `()V` | `FrameBuffer.release()` — buffer-pool return |
| `0xedcdc` | `originalFrame` | `Lcom/kwai/camerasdk/video/VideoFrame;` | recursive unwrap |

Constructors reached:

* texture: `kuaishou::VideoRawFrame::VideoRawFrame(MediaDataType,
  shared_ptr<render::gl::Texture>, uint32 w, uint32 h, uint64 ts,
  VideoFrameAttributes&&)` @ `0xed384` (plt `0x8aa80`)
* CPU: `kuaishou::VideoRawFrame::VideoRawFrame(MediaDataType, PixelbufferType,
  shared_ptr<void>, uint32 w, uint32 h, uint64 ts,
  const VideoFrameAttributes&)` (exported, `.dynsym` #1145 `0xf6a78`)

Every access is wrapped in `kuaishou::dbase::AttachCurrentThreadIfNeeded`
(0xed0c0, 0xed130 …) → the frame can be read from a **non-Java thread**
(GL/cpu worker); this is the attach/detach pattern for the render thread.

## 2. JNI signatures of the camera-side entry points

From `evidence/04_jni_map.txt` + `tools/dex_find.py` method records:

```
FaceDetectorContext.nativeInit                 (JII)J              libfacedetectcontext.so
FaceDetectorContext.nativeInitSensorManager    ()J                 @0x183b4
FaceDetectorContext.nativeDetectFacesFromFrame (JLcom/kwai/camerasdk/video/VideoFrame;I)[[B
FaceDetectorContext.nativeDestroy              (JJ)V
FaceDetectorContext.nativePrepare              (JI)V
FaceDetectorContext.nativeSetFaceDetectConfig  (JI[B)V
FaceDetectorContext.nativeSetData              (JILjava/lang/String;)V
Westeros.nativeSetFaceDetectorContext          (JJJI)V             libwesteros.so @0x3feb0
Westeros.nativeApplyPlugin                     (JJ)V                @0x4036c
WesterosGlProcessor.nativeGetDaenerysGlProcessor (J)J               @0x3e858
```

`Westeros.setFaceDetectorContext(ctx, mode)` (Westeros.java:339-347) passes
`ctx.getNativeContext()` and `ctx.getNativeSensorManager()` — i.e. the raw C++
`BaseFaceDetector*` **and** the sensor-manager pointer are shared with
Westeros, so the per-frame detect on the pixel path is driven natively and no
Java call is required per frame. `FaceDetectorContext.detectFacesFromFrame`
(Java) is the marshalling wrapper for the same native call used by image mode /
app callers; it locks on `FaceDetectorContext.lock` and converts each returned
`byte[]` with `do.g0.f(byte[])` (protobuf `FaceInfo`).

`nativeDetectFacesFromFrame` body (`native/fdc_detectFaces.s`):
`VideoRawFrameFromJni` (0x1a1dc) → `vtable+0x68` detect → per-face
`kuaishou::model::FaceData` copy → `vtable+0x570` (proto serialize) → `byte[][]`;
returns `null` when the face list is empty.

## 3. `VideoFrame` buffer formats (Java, `classes15.dex`)

`com.kwai.camerasdk.video.VideoFrame` (decompiled at
`sources/classes/com.kwai.camerasdk.video.VideoFrame.java`):

| field | meaning |
|---|---|
| `type` | `1 YUV`, `2 TEXTURE`, `3 RGBA`, `4 BITMAP`, `0 None` |
| `yuv_format` | `0` I420/YUV420P, `1` NV12, `2` NV21, `3` RGBA32 (see below) |
| `data` | `FrameBuffer` (direct `ByteBuffer` from a pool) |
| `textureId`,`isOesTexture` | GL path |
| `width`,`height` | buffer dims **before** rotation/crop |
| `timestamp` | ms (`TimeUnit.NANOSECONDS.toMillis`) |
| `attributes` | protobuf `VideoFrameAttributes` (transform, crop, facing, color space, frame source) |
| `nativeContext` | unused for the pipeline (0) |

`yuv_format` codes are produced by
`com.kwai.camerasdk.videoCapture.i` ("YUVFrameBufferReader.java"), method
`f(Image, Size, FrameBuffer)`:

```java
if (plane[2].getPixelStride() == 2)      yuv_format = 2;  // NV21: Y + VU
else if (plane[1].getPixelStride() == 2) yuv_format = 1;  // NV12: Y + UV
else                                     yuv_format = 0;  // I420: Y,U,V
```

Buffer size is `ImageFormat.getBitsPerPixel(ImageFormat.YUV_420_888) * w * h / 8`
= `1.5 * w * h` bytes. **Stride**: if `width == plane[0].getRowStride()` the
planes are bulk-copied; otherwise `ByteBufferUtils.a(dst, dstRowBytes, plane,
srcRowStride, rows)` copies row by row, so the `FrameBuffer` handed to native
is always **tightly packed, stride == width** (`FrameBuffer.byteBuffer.remaining()
!= 1.5*w*h` triggers a pool re-create and a `Log.w("frame buffer size is not
equal to rowStride * height!!!")`).

> Do not confuse the two enums: `VideoFrame.yuv_format` (0=I420, 1=NV12,
> 2=NV21, 3=RGBA) vs YCNN `YCNNComm.KSY_COLOR_*` (0=BGRA, 1=RGBA, 2=NV12,
> 3=NV21, 4=YUV420P, 5=Y, 6=YUVA444). `YCNNModelIn.colorType` defaults to **3 =
> NV21** in the YCNN enum.

## 4. Producer: `CameraKitSession` (Camera2 via Huawei CameraKit) and Camera1/2 fallback

`com.kwai.camerasdk.videoCapture.cameras.camerakit.d`
("CameraKitSession.java", abstract, implements `CameraSession`, `@TargetApi(28)`)
inner class `e implements ImageReader.OnImageAvailableListener`:

```java
long ts = System.nanoTime();                      // or Image.getTimestamp() + offset
FrameMonitor.e(o0.kCameraFrameProcessThread, ms)  // frame arrival timestamp
FrameBuffer fb = yuvReader.e(image, previewSize); // deinterleave, see §3
int fmt = yuvReader.c();   int w = yuvReader.d(); int h = previewSize.c();
VideoFrame f = VideoFrame.fromCpuFrame(fb, w, h, fmt, ms)
    .withTransform(o1.p().f(h0()).e(mirror).build());   // rotation + horizontal flip
f.attributes.r(sensorTs).k(cropRatio).p(firstFrame)
            .m(q1.kFrameSourcePreview).h(deviceOrientation)
            .i(s.kBt601FullRange).n(isFrontCamera);
sink.onVideoFrameCaptured(session, f);            // CameraSession.CameraDataListener
```

* `h0()` = preview rotation in degrees (from the session's display/orientation
  computation), `o1` = protobuf `Transform{rotation, mirror}`.
* All Camera2 sessions land in `CameraControllerImpl$b.onVideoFrameCaptured`
  (`sources/classes/com.kwai.camerasdk.videoCapture.CameraControllerImpl.java:155-176`):
  stale-session frames run `textureNotUsedRunnable` and are dropped, then
  `dataListener.onVideoFrameCaptured` or `publishMediaFrame(videoFrame)`.
* API selection: `cameraApiVersion` `kAndroidCamera1` / `kAndroidCamera2` /
  `kAndroidCameraUnit`; on `CAMERA_UNIT_AUTHENTICATION_FAILED` the controller
  re-opens with `kAndroidCamera1` (`CameraControllerImpl.java:882-886`).
* `FrameMonitor` (libdaenerys `kuaishou::monitor::FrameMonitor`,
  `CameraProcessor.receive_time/publish_time`, `EncodeProcessor.*`,
  `is_drop`, `drop_frame_position`, evidence/08) records per-thread latency.

## 5. Where the face result lands

`YcnnPluginUtils.nativeGetFaceData` (libykit_core_plugin.so @0x15910, 2016 B)
and the `YlabProvider`/`YlabPostProvider` classes attach results to the
Westeros frame: `kuaishou::westeros::VideoFrame::face_data()` (symbol
`_ZN8kuaishou8westeros10VideoFrame9face_dataEv` @0x7b32) and the protobufs
`kuaishou.westeros.model.{YCNNFace, YCNNFaces, YCNNPoint, YCNNRect,
YCNNModelInConfig, YCNNResourceConfig, YcnnFaceAttributeInfo,
YcnnFaceAttributeResult}` (strings 0x72851-0x72b59).

## 6. Commands used

```bash
unzip -p "$APK" lib/arm64-v8a/libdaenerys.so > /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/kwpatch/libs/libdaenerys.so
$PY $TOOLS/disasm_elf.py /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/kwpatch/libs/libdaenerys.so \
    --symbol _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject \
    --out native/daenerys_videorawframe.s
# adrp+add literal resolver over the .s  → native/daenerys_videorawframe.strings.txt
$PY $TOOLS/decompile_classes.py "$APK" \
    --regex '^com\.kwai\.camerasdk\.videoCapture\.i$' --out /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/kwpatch/d7 --max 2
```
