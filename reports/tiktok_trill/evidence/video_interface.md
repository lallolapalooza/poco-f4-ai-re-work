# TikTok DNN ↔ video interface — evidence map

Follow-up added after the main report. Every claim below is tied to an artifact in this
directory or to a reproducible command against the APK.

## Reproduce

```bash
PY=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python
APK="$APK_ROOT/cust/app/customized/partner-com.ss.android.ugc.trill_101/partner-com.ss.android.ugc.trill_101.apk"
JADX=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tools/jadx/bin/jadx
$JADX --single-class com.bef.effectsdk.BEFEffectNative --single-class-output out.java --show-bad-code --no-res "$APK"
$JADX --single-class com.ss.android.vesdk.faceinfo.VEFaceDetectInfo --single-class-output vfdi.java --show-bad-code --no-res "$APK"
$PY /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tools/disasm_elf.py native/libeffect.so --addr 0x2eac68 --size 0x40 -o -
$PY /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tools/disasm_elf.py native/libeffect.so --addr 0x2eabd4 --size 0x94 -o -
nm -D --defined-only native/libeffect.so | grep -E 'BEFEffectNative|bef_effect_process_texture|bef_effect_set_width_height'
```

## Confirmed

| Fact | Evidence |
|---|---|
| Frame entry point is `BEFEffectNative.nativeProcessFrame(long, int, int, int, int, double)` | `sources/classes/BEFEffectNative.java` |
| Frame is passed as a GL texture, not YUV | `native/libeffect_nativeProcessFrame_2eac68.s`: wrapper calls `bef_effect_set_width_height` then tail-calls `bef_effect_process_texture`; `native/libeffect_video_exports.txt` lists `bef_effect_process_texture*` |
| Width/height are the last two int args | JNI mapping: x5→w1, x6→w2 immediately before `bef_effect_set_width_height` in `nativeProcessFrame_2eac68.s` |
| Detection-only path is texture-based | `native/libeffect_nativeProcessAlgorithm_2eabd4.s` calls `bef_effect_algorithm_multi_texture_with_params` |
| Orientation / camera facing are separate setters | `native/libeffect_nativeSetFrameOrientation_2ead50.s`, `native/libeffect_nativeSetCameraPosition_2ead0c.s`; exports in `native/libeffect_video_exports.txt` |
| Face results are 106-point `VEFaceDetectInfo` with rect/score/yaw/pitch/roll/eye-distance/action/trackCount/faceID/visibility/iris/eye/lip/eyebrow | `sources/classes/VEFaceDetectInfo.java` (`LIZ(106)`, `LIZLLL(106)`), `VEFaceDetectInfo.covert(FaceDetectInfo)` |
| Effect graph returns face/attribute results as `algorithm_result_face_st`, `algorithm_result_face_attrs_st` | `evidence/libeffect_face_strings.txt`; `report.md` §5 |
| The three shipped models feed `FaceDetectTaskTT` / `FaceAttributesTask` in `libeffect.so` | `report.md` §3–5; `native/libeefect_string_xref_68b360.s` |

## Inferred (do not treat as confirmed)

| Claim | Why inferred |
|---|---|
| `nativeProcessFrame` first two ints are texture ID / frame flags | They are not referenced in the 0x2eac68–0x2eaca8 window; exact semantics require tracing `bef_effect_process_texture` or its caller |
| Input is resized to 120×120 | `base120` record name + `faceDetect240/280` strings; no tensor shape in the container |
| DNN runs once per rendered frame | `processFrame` is called from the render path, but `skipFrame`/`skipFrameAll`, fps counters and live `drop frame` paths mean cadence can differ |
| Tracking is classical, not a temporal DNN | Only three still-image bundles ship; `trackCount`/`faceID` come from the effect SDK tracker, but its implementation was not decompiled |
| `pout`/`base120det`/`partface`/`eyecls` map to the VEFaceDetectInfo fields | record names + result struct field order; the descriptor schema is still unknown |

## Boundary

The main subagent report (§5) already stated the high-level chain
`VESDK → FaceDetectTaskTT → VEFaceInfo` but did **not** contain the JNI entry
point, the texture-vs-YUV fact, the 106-point result layout, or the video-side
orientation/camera setters. Those were recovered in the parent session and are
now stored here plus in the `sources/classes/` and `native/` files added above.

## Update (data-flow patch): the first two ints are resolved

The "Inferred" row about `nativeProcessFrame`'s first two int arguments is now
**confirmed** and supersedes that row:

| fact | evidence |
|---|---|
| Java call is `processFrame(handle, inputTextureId, outputTextureId, width, height, System.nanoTime()/1.0E9)` | `sources/classes/NaviGLSurfaceView.java:245` (vertical) and `:265` (horizontal); caller found with `evidence/scripts/dex_callers.py` → `evidence/beffectnative_callers.txt` |
| the two ids are GL textures of the render context, not flags | `sources/classes/C36620EXe.java:167-290` — input = texture `LJJJJJL` bound to FBO `LJJJJJ`, output = texture `LJJJIL`; both `GL_TEXTURE_2D`, `GL_RGBA`, `GL_UNSIGNED_BYTE`, `GL_LINEAR` |
| native forwards them unchanged to `bef_effect_process_texture(handle, i1, i2, ts)` | 0x2eac68-0x2eaca4 with the register shuttles 0xaf58d4 / 0xa9f91c; `native/libeffect_fs_decompiled.c` (Ghidra) shows the same call order |
| timestamp is seconds from `System.nanoTime()`, `resourceLoadingTimeoutUs` is forced to 0 | `bef_effect_process_texture` 0x3097f0 (`mov w3,wzr`); log format `"…timeStamp=%f resourceLoadingTimeoutUs=%d"` at 0xc9ef2c |
| camera facing is inverted on the way into C and is set once | `nativeSetCameraPosition` 0x2ead0c: shuttle 0xad33c4 (`mov w19,w3; mov x20,x2`) then `tst w19,#0xff ; cset w1,eq` before `bef_effect_set_camera_device_position` |

Remaining from the original "Inferred" table: input resize geometry, per-frame
cadence defaults, classical tracker internals and the record→field attribution
are still inferred/unknown — see `../PIPELINE.md` §8.
