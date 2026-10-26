# ir/ — empty: no TFLite flatbuffer exists in this APK

There is exactly one `.tflite` name in the whole APK (`voice_detect_mode.tflite`,
used by `com.kwai.kve.VoiceDetector`), and the model file is not shipped, so
`tools/tflite_ir.py` has nothing to parse.

The bundled `libtensorflow-lite.so` (sha256 6ef8898d…1167f) is a stock TFLite
runtime (2.4.1 version string; NNAPI + XNNPACK + Flex symbols, no custom ops,
47 JNI entry points). See `../evidence/jni_tables.txt`.

The YCNN models used by FaceMagic have no public container description; the
recovered facts about them (names, per-model paths, download endpoints,
backend selection) are in `../report.md` §5 and `../evidence/ycnn_strings.txt`.
