# Native analysis

APK ships one ABI (arm64-v8a) and 14 libraries; none of them is a dedicated
inference runtime by filename.  The relevant one is:

* `lib/arm64-v8a/libcamplat+.production.e6302221764648a97415467f3352d2c06a35bd64.so`
  (29,910,024 B, NDK r23b, 683 exports / 417 imports / 677 `Java_*` JNI entries)

It is the Looksery/Lens Core engine and embeds: OpenCV (JNI `org_opencv_*`,
cascade parser strings), the `LS::` C++ namespace (`LS::Tracking::NaturalFeatureTracker`,
`LS::FaceDetector`, `LS::Candide`, `LS::Scenarium`, `LS::NnRegressor`), MACE
(`/var/lib/jenkins/.../mace/...` source paths, OpenCL + NNAPI + Hexagon + SNPE
backend strings), and Snap's PreviewCV filters (`SnapCut`, `PortraitFilter`,
`SkyFilter`, `MomentFilter`, `ManyTargetTracker`).

Files here:

| file | content |
|---|---|
| `camplat_elf.json` | `elf_info.py` JSON (sections, symbols, string categories). |
| `camplat_jni_exports.txt` | all 677 `Java_*` exports. |
| `camplat_relevant_strings.txt` | filtered strings (asset paths, `LS::` types, NNAPI/MACE/TfLite markers). |
| `jni_binding_table.txt` | recovered `JNINativeMethod` tables (name, signature, fn address) for `LSCoreManagerWrapper`, `FaceTrackerWrapper`, tracking/audio/quality helpers. |
| `libs/libcamplat+….so`, `libs/libclient.so` | extracted arm64 binaries used for the objdump/RELA analysis (read-only copies). |
| `libclient_strings.txt` | `strings -t x libclient.so` (learned_search / voiceml / network markers). |
| `elf_camplat_summary.txt` | human summary of the ELF metadata. |

Not shipped (referenced only): `libbloops.so` / `aifactory_native_sdk` (NLOader
component), `libtnn.so` (manifest `tnn_shared_lib`), `libtensorflow-lite.so`
(dlopen name in libcamplat), `libhexagon_nn_skel*.so` (manifest meta-data),
`libmodelcrypto`.  Consequently no `.so` here implements the AI Factory models;
libcamplat's MACE/NNAPI paths are used by Looksery lens DNNs, whose weights are
downloaded at runtime.
