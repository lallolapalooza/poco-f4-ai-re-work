# Native evidence (ExtraPhotoGlobal)

| file | content |
|---|---|
| `elf_info.txt` | `tools/elf_info.py` output for every extracted AI lib + `librelight_only_extraphoto.so` |
| `*.so` | eight extracted APK libraries (JNI shims / MiAlgo wrapper / MACE motion photo / refocus stubs) |
| `initBrain.s`, `enableBrain.s`, `setBrainLight.s` | `tools/disasm_elf.py` aarch64 disassembly of the three `MiDualPhotoJni` relighting JNI entry points (AILAB_PLI call sequence, image-descriptor construction, light-source array handling) |
| `megvii_process.s` | aarch64 disassembly of `MegviiDualPhotoJni.processRefocusWidthHandle` (three `wa::Image` constructors + `wa::Refocus::setImages` + `run`) |
| `librelight_exports.txt` | all 1265 dynamic symbols of `librelight_only_extraphoto.so` (six `AILAB_PLI_*` exports among media codecs) |
| `librelight_rodata_selected.txt` | relight/shader-related `.rodata` strings with virtual addresses |
| `relight_render_shader.glsl` | the complete GLSL ES 3.0 relight fragment shader extracted from `.rodata` (11 KB; screen blend with `text_light`, 5x5 gaussian, 3D LUT, YUV matrix) |
| `glsl_shaders.txt` | the smaller YUV/vertex shader strings |
| `doc_jni_ghidra.c` | Ghidra pseudocode of all 20 `DocumentProcessJNI` entry points in `libdoc_photo_process.so` (init/initAndRun/scan/crop/enhance/dewarp/handwrite/YUV variants) |
| `doc_core_ghidra.c` | Ghidra pseudocode of the document engine vtable methods: `FUN_00182f54` scan, `FUN_00183798` crop+warp+aspect ratio, `FUN_00183eb4` enhance, `FUN_0018493c` quad validation, `FUN_00185118` point clamp |
| `doc_native*.s` | aarch64 disassembly of the five document JNI wrappers used by this dex |
| `doc_get_bitmap_info.s`, `doc_rgba_to_rgb.s`, `doc_rgb_to_rgba.s`, `doc_bitmap_class.s`, `doc_bitmap_create.s` | Bitmap ↔ RGB staging: format check (`RGBA_8888`), `ld4/st3` RGBA→RGB, RGB→RGBA with alpha 0xFF, `Bitmap.createBitmap(...,ARGB_8888,...,colorSpace)` |
| `doc_scan_model_init.s`, `doc_resize_helper.s`, `doc_model_descriptors.txt` | `ScanModel` engine creation (`hed_model`/`hed_input`/`img_output`, 1×384×384×3 → 1×384×384×2), resize helper, hexdumps of Scan/Enhance/Dewarp/HandWrite descriptors |
| `wa_refocus_ghidra.c` | Ghidra pseudocode of `wa::Refocus::{init,setFaces,setImages,setBokehStyle,setBokehSpotType,run}` (thin dispatch to the internal pipeline) |
| `refocus_config_bitmap.s`, `refocus_config_yuv.s`, `refocus_generate_image.s` | `DualPhotoJni` ASVL_OFFSCREEN/NV21 conversion: `encodeyuvToRGBA`, Y/U/V de-interleave, `convertBitmapToOffscreenData` |
| `librefocus.so`, `libmibrain_relighting_jni.so`, `libmegvii_bokeh_jni.so` | JNI shim libraries actually shipped in the APK (kept small) |

Big libraries analysed from `/tmp` and intentionally **not** copied into the report
(`librelight_only_extraphoto.so` 91.9 MB, `libwa_refocus_extraphoto.so` 8.9 MB,
`libdoc_photo_process.so` 25 MB): their section tables, export lists and key
strings are in `../evidence/04_native_elf.txt`, `05_relight_library.txt`,
`06_docphoto_native.txt`.
