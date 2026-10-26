# ExtraPhotoGlobal (`com.miui.extraphoto`) — AI subsystem reverse engineering

## 1. Identity

| Field | Value | Evidence |
|---|---|---|
| APK | `product/priv-app/ExtraPhotoGlobal/ExtraPhotoGlobal.apk` | `scope.json` |
| Package / version | `com.miui.extraphoto` / `1.8.7.4.0-global` (code 108070400) | `inventory/inventory.json` (androguard) |
| Label | **Bokeh** | `inventory/inventory.json` |
| Size / SHA-256 | 228,682,351 B / `35c60240…462b2b` | `evidence/hashes.txt` |
| SDK | min 30 / target 33, arm64-v8a only | `inventory/inventory.md` |
| Structure | 1 dex (4.95 MB, 4,785 classes), 40 native libs (217.4 MB), 1217 config files, 1 model asset | `inventory/inventory.md` |
| Target machine | POCO F4 (`yunluo`) system image; the APK ships MACE OpenCL caches for `spes`, `spesn`, `xun`, `yunluo` | `evidence/01_apk_layout.txt` |

## 2. Feature map (all AI features found)

| # | Feature | Java entry point | Native backend | Model assets | Confidence |
|---|---|---|---|---|---|
| 1 | Portrait relighting ("brain relighting"), 8 photo effects `relightingType 0x03..0x18` (plus "none") | `RelightingOperatingFragment` → `MiBrainRelightingProcessor` | `librelight_only_extraphoto.so` (91.9 MB) + `libmibrain_relighting_jni.so` | light patterns in lib `.data` (not decoded); effect params in `assets/refocus/relighting/*/config.json` | confirmed (code+JNI+shader) |
| 2 | Movie relighting, 9 effects `0x20..0x28` | `MovieRelightingPresenter` | same two libs | `assets/refocus/movie_relighting/*/config.json` | confirmed (config + `AlgoManager.supportMovieRelighting`) |
| 3 | Dual-camera bokeh, MiCamera profile | `MiDualPhotoJni.processMiCameraRefocus` | `mialgo_utils_extraphoto`, `miphone_bokeh_dynSpot`, `miphone_refocus_bokeh`, `micamera_dual_bokeh_jni` | `assets/refocus/micamera_dual_bokeh/dualcam_bokeh_params.json` | confirmed |
| 4 | Dual-camera bokeh, Megvii `wa::Refocus` | `MegviiAdjustProcessor` → `MegviiDualPhotoJni` | `libwa_refocus_extraphoto.so` + `libmegvii_bokeh_jni.so` | none (classical CV) | confirmed |
| 5 | Single-cam bokeh `soft-portrait-enc` | `MiBrainRelightingProcessorSoftEncrypted` → `DualPhotoJni.processMiRefocus` / `processMiRawDepth` | `librefocus_mibokeh.so` (+`_mtk`) | unknown | confirmed (JNI), model unknown |
| 6 | MiAlgo portrait bokeh | `AlgoManager$MiAlgoBokeh` | `libmialgo_portrait.so` (+`miphone_bokeh_gpf/effect/proc`) | unknown | confirmed (loader strings) |
| 7 | ArcSoft portrait lighting / dual-cam refocus | `AlgoManager$ArcSoftAlgo`, `DualPhotoJni.initRelightingEffect` | `libgallery_arcsoft_portrait_lighting(_c).so`, `libgallery_arcsoft_dualcam_refocus.so`, `librefocus.so` | none carveable (weights compiled-in / opaque) | confirmed (API strings), model unknown |
| 8 | Document photo: scan (4 corners), crop, dewarp, enhance, de-handwrite, ID-card/PPT modes | `DocPhotoActivity` → `DocProcessor` → `DocumentProcessJNI` | `libdoc_photo_process.so` (25 MB, MACE + OpenCL) | `assets/common/doc_photo/doc_model/dlc/document_model.dlc` (SNPE) + per-SoC `mace_cl_compiled_program.bin`; MACE model names/graph embedded in the lib | confirmed (asset + JNI), loader partially unknown |
| 9 | Dynamic/motion refocus effects (non-ML) | `DynamicOperatingFragment` | GLES renderer | `assets/refocus/dynamic/*` | confirmed (no model) |
| 10 | Motion photo MACE runtime | `libmotion_photo_mace.so` | MACE | model file mmap'd at runtime; not shipped | confirmed (strings), model unknown |

### 2.1 Document-photo I/O (observed in `libdoc_photo_process.so`)

At file offset `0x436fec` the library holds a model-descriptor block:

```
"doEdgeDet forward net time use: " ... "hed_model" "hed_input" "img_output"
int64 dims: 1, 384, 384, 3      (input)
int64 dims: 1, 384, 384, 2      (output)
"ScanModel"
float params: 0.85 0.7 0.15 0.1 -90.0 ...
```

So the shipping MACE document-scan graph is `EdgeDet` (HED-style encoder with an
`ASPP` head and a `segment_head`), 384x384x3 -> 384x384x2, with node names such as
`EdgeDet/ASPP/conv_RF_17_depth/depthwise_weights:0`, `EdgeDet/ASPP/concat:0`
(198 `EdgeDet/*` strings confined to `0x160a038..0x160c599`).
The shipped **DLC** is the SNPE export of the same task at 512x512x3 -> 512x512x1
(metadata `out_node=['img_output']`, `input_dim=[['hed_input','1,512,512,3']]`).

Document enhancement modes (`DocumentProcess$EnhanceType`): `RAW, COLOR, BIN,
GRAY, FILTER_ID_CARD, DEMOIRE`; document types `DEFAULT, ID_CARD, PPT`.
The lib names nine MACE models: `hed_model`, `enhance_model`, `dewarp_coarse_model`,
`dewarp_fine_model`, `demoiring_model`, `segment_model`, `inpaint_coarse_model`,
`inpaint_fine_model`, `handwrite_model` (`evidence/06_docphoto_native.txt`).

## 3. `document_model.dlc` — container and decoded graph

### 3.1 Container (confirmed)

`assets/common/doc_photo/doc_model/dlc/document_model.dlc` is a **ZIP archive**
(`file` → "Zip archive data"), 357,758 B, sha256 `809a526c…88ed6e`:

| member | size | content |
|---|---:|---|
| `dlc.metadata` | 227 B | plain text |
| `model` | 357,151 B | 8-byte header + protobuf |

`dlc.metadata`:

```
converter-command=snpe-tensorflow-to-dlc verbose=False out_node=['img_output'] \
  allow_unconsumed_nodes=True model_version=None \
  input_dim=[['hed_input', '1,512,512,3']] in_type=None
converter-version=1.22.0.212
model-version=N/A
```

`model` header: `d5 0a 02 00 | 05 00 00 00` → u32 magic `0x00020AD5`, u32
version `5`; the remainder parses cleanly as protobuf wire format. Top level:
`field 1` = 100 `TensorDescriptor`, `field 5` = 100 `Layer`, `field 13` = 1
(model version). See `ir/dlc.py`, `evidence/02_dlc_container.txt`.

### 3.2 Layer schema (confirmed by decoding all 100 layers)

```
Layer      { 1 name, 2 type_id, 3 output_dims[], 6 index, 7 inputs[], 8 outputs[],
             9/10 tensor-id msgs, 11/12/13 empty blobs, op params in type field }
Tensor     { 1 name, 2 const 132, 3 dims[] }
Conv/Deconv params (field 30)
         { 1,2 kernel h,w; 3,4 stride; 5,6 pad-before; 7,8 dilation-like
           (for depthwise layers field 7 == channel count, field 8 == 1);
           9 group (always 1 here); 10 pad_type (0); 11 const 3 (not the
           output-channel count - that comes from the tensor dims);
           20 {1 weights TensorParam, 2 bias TensorParam} }
TensorParam { 1 dims[], 3 { 3 raw bytes, 4 Quantizer } }
Quantizer   { 1 bitwidth, 2 min, 3 max, 4 step, 5 offset }
Activation params (90) { 1 kind (2=sigmoid, 3=relu, 5=relu6), 2..5 floats }
Add params (60)        { 1 op=2, 2 repeated float coefficients (1.0, 1.0) }
Concat params (25)     { 1 axis = 3 }
Elementwise (60)       { 1 op = 4 (div) }
Const (161)            { 1 TensorParam }
```

Type IDs observed: `60 input`, `211 const`, `95 elementwise`, `40 conv2d`,
`150 activation`, `90 add`, `30 concat`, `70 deconv2d`.
Weights are **uint8** with per-tensor `{min,max,step,offset}`; the encoding was
verified for all 107 quantizers:

```
x = step * (q + offset)     # step*(0+offset)==min, step*(255+offset)==max
```

Example (first conv, 3x3x3x3, 81 B): `min=-1.292905, max=1.817385,
step=0.012197, offset=-106.0` → `0.012197*(q-106)`.

### 3.3 Graph (confirmed) — HED edge model, `mobilenet_v2_skip`

```
hed_input (1,512,512,3) ──truediv (/255)──► backbone
 backbone: block0_0..0_2 (3x3 convs, 3→3→6→12, stride 2 at 0_2)
           block1_0 .. block5_2  (mobilenet-v2 inverted residuals,
           26 depthwise 3x3 layers, expansion/projection 1x1, residual adds)
 decoder : 4 x Conv2DTranspose 4x4/stride 2 + skip concat(axis=3)
           concat_3 (9ch) → conv 3x3 → conv 1x1 → Sigmoid
img_output (1,512,512,1)
```

* 49 Conv2D (incl. 26 depthwise), 4 Conv2DTranspose, 26 ReLU6, 3 ReLU,
  1 Sigmoid, 10 Add, 4 Concat, 1 Div, 1 Const.
* 53 weighted layers, **314,289 bytes** of uint8 kernels/bias.
* Dequantised weights: `models/document_model_weights.npz`
  (`models/dlc_extract/model` keeps the raw payload; `ir/document_model_graph.json`
  is the parsed graph).
* Graph JSON: `ir/document_model_graph.json`, full layer dump:
  `evidence/03_dlc_graph.txt`.

### 3.4 How the asset is used (partially unknown)

`DocProcessor.SafeProcessor.init()` (`sources/docphoto/...DocProcessor.java`)
creates `filesDir/doc_model/dlc` and `filesDir/doc_model/cache`, copies
`assets/common/doc_photo/doc_model/dlc` into it and, when the per-device OpenCL
cache is absent, calls `DocumentProcessJNI.nativeInitAndRun(cacheDir, modelDir)`
before `nativeInit(cacheDir, modelDir)`.

The shipped `libdoc_photo_process.so` is a **MACE** runtime (`mace.pb.cc`,
`Create MaceEngine from model pb`, OpenCL kernels) and contains **no** SNPE or
`.dlc` parser strings (only `dlclose` and two incidental binary hits). The MACE
document models referenced by name are not present as files in the APK.
Therefore the consumer of `document_model.dlc` cannot be identified statically
from this build: it is likely the SNPE variant of the same OCR SDK, shipped as
an unused asset here, or consumed by a loader that leaves no string signature
(**inferred / unknown**, see §8).

## 4. Relighting pipeline — traced Java → JNI → GPU shader

### 4.1 Java call chain (confirmed)

`RelightingOperatingFragment` (touch → `setLightSource(f, f2)` scaled by
`DualPhotoNativeContext.getScale()`; effect selection → `ChangeEffectRunnable`)
calls `RelightingProcessor`:

```
initProcessor():
  mRelightingHandle = MiDualPhotoJni.initBrainRelightingEffect(
        photoInfoProvider.getDepthDataDegree(),
        originImage.pointer, depthData.pointer, depthData.length, facePoint[4]);
  initBlurImage():  MiDualPhotoJni.processMiCameraRefocus(... depth ...)
enableRelightingEffect(effectType):
  MiDualPhotoJni.setBrainRelightingLightSource(handle, W/2, H/2, W, H);
  MiDualPhotoJni.enableBrainRelightingEffect(handle, effectType,
        blurImage.pointer, effectImage.pointer, originImage.pointer,
        depthData.pointer, depthData.length, pointCache[2]);
doSave(): same with a freshly rendered blur image, then release.
```

`AlgoManager$MiBrainRelightingAlgo.load()` (confirmed) loads
`relight_only_extraphoto` then `mibrain_relighting_jni`; device gating in
`RefocusAccessUtils` (`isBrianRelight`).

### 4.2 JNI wrappers (confirmed, aarch64)

`libmibrain_relighting_jni.so` (211,088 B, sha256 `5c914d8e…`) exports exactly
four JNI methods, each a thin shim into `librelight_only.so`:

| JNI symbol | addr | calls (via PLT) |
|---|---:|---|
| `initBrainRelightingEffect` | `0xe7a0` | `AILAB_PLI_Init(&h, 0, w, h)`, `AILAB_PLI_PreProcess(h, img, orientation, out[12])`, fills `int[4]` facePoint, `ReleaseIntArrayElements` |
| `setBrainRelightingLightSource` | `0xe9c8` | `AILAB_PLI_SetLightSource(h, {w,h,x,y})` (4 ints on stack) |
| `enableBrainRelightingEffect` | `0xea50` | builds image descriptors, `AILAB_PLI_Process(h, {w,h,depth,blurImg}, origin, &effectType, effectImg)` (struct field order inferred from register/stack use), `AILAB_PLI_GetLightSource(h, out)` → `pointCache[0..1]`, `ReleaseIntArrayElements` |
| `releaseBrainRelightingHandle` | `0xec78` | `AILAB_PLI_Uninit` / free |

Log strings: `[RELIGHT] initBrainRelightingEffect`, `[RELIGHT] AILAB_PLI_PreProcess
result %ld`, `[RELIGHT] face center: %d x %d, radius %d`, `[RELIGHT]
AILAB_PLI_SetLightSource %d %d result %d`, `[RELIGHT] effect type %d`,
`[RELIGHT] AILAB_PLI_Process result %d light source %d %d`.
Disassembly: `native/initBrain.s`, `native/enableBrain.s`, `native/setBrainLight.s`.

### 4.3 Renderer (`librelight_only_extraphoto.so`, 91,925,616 B)

* ELF deps: `libGLESv3.so`, `libEGL.so` → GPU renderer.
* Exports only six relight entry points (`AILAB_PLI_Init/Uninit/PreProcess/
  SetLightSource/GetLightSource/Process`) — `native/librelight_exports.txt`.
* The library is a forked ijkplayer/media stack (x264, opus, mp4v2, webrtc,
  breakpad) that embeds the relight filter sources:
  `mediaprocess/filters/cpu_filters/relight/mi_portrait_3d_lighting_image.cpp`,
  `mi_relight_engine_image.cpp`, `mi_gpu_relight_filter.cc`.
* GPURelightFilter strings: `process effect_type_`, `pLightParam->i32LightMode`,
  `AdjustLightPos max_width_offset`, `light_width_`, `light_height_`,
  `GPURelightFilter::Set current light source x:`, `…GetLightSource`,
  `text_light`, `noise_light`, `low_brightness`, `m_3DFilterTexture`.
* The GLSL ES 3.0 fragment shader is embedded as a string
  (`native/relight_render_shader.glsl`, 11 KB). Render math (confirmed):

```glsl
vec2 pos = clamp(uv + moveLightPos, 0.0, 1.0);
vec4 light_color = texture(text_light, pos);
vec4 blendSource(vec4 s, vec4 l) { r=screen(s.r,l.r); ... }
float screen(a,b) { return 1.0 - (1.0-a)*(1.0-b); }
photoColor = blendSource(rgbdata, lightColor(uv));
```

  plus a 5x5 gaussian (`guassianBlur5`, weights in the source), optional
  colour-separation, noise layer, 3D-LUT (`filter_rgb`) and YUV matrix.
* Light source position (`AILAB_PLI_SetLightSource`) → `moveLightPos`, i.e. the
  light-pattern texture is shifted and screen-blended; `effect_type_` selects
  the pattern (inferred from `InitExtraFilterTexture:: Process effect_type_:`
  and the `relightingType` configs). `assets/refocus/relighting/*/config.json` maps
  `relightingType` → effect name (natural 0x03, blinds 0x10, dots 0x12,
  leaves 0x14, rainbow 0x15, stage 0x16, movie 0x17, holi 0x18); movie
  relighting 0x20..0x28 (`evidence/configs/`).
* `GPURelightFilter::Process inDepthInfo or pBlurImage is NULL` shows depth +
  blur image are both inputs, matching the `AILAB_PLI_Process` struct.
* Large opaque payload: `.data` = **82,944,760 B** at file offset `0x890000`
  (90 % of the library). It contains heterogeneous, partly high-entropy data
  (1 MB-window entropy 1.5–7.4), pointers into `.data.rel.ro`, long zero runs,
  but **no** TFLite/ZIP/ELF/PNG/TFL3/magic container and no `carve_models.py`
  hit. Its role (light-pattern textures / precomputed portrait 3D-lighting
  tables) is **unknown**; it is not claimed to be parsed.

## 5. Bokeh pipeline — traced Java → JNI → `wa::Refocus`

### 5.1 Java

`MegviiAdjustProcessor` (`sources/relight_ui/...`):
`F_VALUE` f-number ladder 1.0…16.0 and `ALG_F_VALUE` (1.0,1.4,2.0,2.8,4.0,
5.6,8.0,11.0,16.0); `findFValueIndex(blurLevel)` maps the app's blur level.
Each render calls:

```
MegviiDualPhotoJni.processRefocusWidthHandle(handle, targetImage, originImage,
      depthData, focusX, focusY, algFValue, depthW, depthH, portraitType)
```

`AlgoManager$MegviiBokehAlgo` loads `wa_refocus_extraphoto` +
`megvii_bokeh_jni`; `MiCameraDualBokehAlgo` loads `mialgo_utils_extraphoto`,
`miphone_bokeh_dynSpot`, `miphone_refocus_bokeh`, `micamera_dual_bokeh_jni`.
`MiDualPhotoJni.initMiCameraRefocus(w,h,jsonPath)` reads
`assets/refocus/micamera_dual_bokeh/dualcam_bokeh_params.json`
(`Util.getMiCameraDualBokehJsonPath()`).

### 5.2 JNI → `wa::Refocus` (confirmed)

`libmegvii_bokeh_jni.so` (260,048 B, sha256 `0515b710…`) exports 3 JNI
functions. `processRefocusWidthHandle` (`0x8a8c`, `native/megvii_process.s`)
constructs three `wa::Image` objects (two image buffers + depth buffer with
`ImageType=6`, `DataType=0`), then calls:

```
wa::Refocus::setImages(const Image&, const Image&)
wa::Refocus::run(int focusX, int focusY, float fNumber, Image& out)
```

`libwa_refocus_extraphoto.so` (8.9 MB) exports (demangled):
`wa::Refocus::setImages` (2 and 3 images), `setFaces`, `setBokehStyle`,
`setBokehSpotType`, `run`, `init(WRefocusConfig)`, `getVersion`.
`[RF] not support setModel anymore.` → **no neural network**; ROI/alpha modules
are classical CV: `ComputeCocDepth` / `ComputeCocDepthAndDisp`,
`AlphaRefineKepler` (matting), `ApertureCpu::RenderBokehColor` / `RenderYBokeh`,
`BlurWorker` (rotate/zoom, disk blur), `ImgMergerKepler` (median + Gauss),
SGBM block matching, WLS filter, shared matting, bokeh style/spot enums.
MTK mirror: `libmiphone_refocus_bokeh.so` modules
`depth_of_field_segmentation.cpp`, `scatter_blur.cpp`, `spot_proc.cpp`,
`edge_refinement.cpp`, `bokeh_pre_proc.cpp`.

## 6. JAX re-implementations (`reimpl/`)

| script | what runs | input → output | verification |
|---|---|---|---|
| `document_hed.py` | full decoded DLC graph with **real** dequantised weights | `(1,512,512,3)` → `(1,512,512,1)` sigmoid | 99/99 layer shapes equal the DLC's declared dims; output finite ∈ [0,1]; `doc_hed_output.pgm` |
| `relighting.py` | documented GLES relight renderer (screen-blend light map + 5x5 gaussian + depth-selected base) | origin/blur `(256,320,3)`, depth `(256,320)` → `(256,320,3)` | runs for all 8 effect types; `relighting_output.ppm` |
| `bokeh.py` | CoC (thin lens) + aperture spot + per-pixel radius-bin blur | image `(256,320,3)`, depth `(256,320)`, focus point → `(256,320,3)` | runs for f/1.4/2.8/5.6 and disk/hexagon spots; `bokeh_output.ppm` |

`run_all.py` writes `reimpl/verify.txt` (`OVERALL: PASS`, each exit code 0).

Status: `document_hed` = `runs_unverified` (architecture + weights decoded, no
SNPE runtime for a numeric reference); relighting/bokeh = `architecture_only`
(renderer/algorithm traced, proprietary light textures / CoC calibration
unavailable). No claim of bit-exactness.

## 7. Reproduction

```bash
PY=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python
WS=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work
APK="$APK_ROOT/product/priv-app/ExtraPhotoGlobal/ExtraPhotoGlobal.apk"
cd $WS/reports/miui_extraphoto

# inventory
$PY $WS/tools/apk_inventory.py "$APK" --out inventory

# DLC
unzip -l models/document_model.dlc           # dlc.metadata + model
$PY ir/dlc.py models/dlc_extract/model      # protobuf walk
$PY reimpl/run_all.py                        # all three pipelines + verify.txt

# native
$PY $WS/tools/elf_info.py native/libmibrain_relighting_jni.so
$PY $WS/tools/disasm_elf.py native/libmegvii_bokeh_jni.so \
    --symbol Java_com_miui_extraphoto_refocus_manager_MegviiDualPhotoJni_processRefocusWidthHandle
$PY $WS/tools/carve_models.py /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/xp/librelight_only_extraphoto.so
```

## 8. Unknowns / limits

1. **DLC loader**: no SNPE/`.dlc` string or parser exists in the shipped
   `libdoc_photo_process.so`; the file is copied to `filesDir/doc_model/dlc`
   and passed to `DocumentProcessJNI.nativeInit`. The actual consumer on this
   build is unidentified (the shipping backend is MACE; the DLC looks like the
   SNPE export of the same HED task for another OCR-SDK build). Inferred/unknown.
2. **MACE doc models** (`hed_model`, `enhance_model`, `dewarp_*`, `segment_model`,
   `inpaint_*`, `handwrite_model`, `demoiring_model`): names and parts of the
   `EdgeDet` graph are embedded in the lib, but no model file is shipped in the
   APK and no weights were parsed. Model files may be downloaded or built into
   the lib as unnamed blobs; unverified.
3. **Relighting internals**: `AILAB_PLI_PreProcess/Process` body, the 82.9 MB
   `.data` payload, the per-effect light-pattern textures and the exact
   light-position mapping (`AdjustLightPos`) are not decoded; the JAX renderer
   implements the documented shader math with procedural light maps.
4. **Relighting face/depth analysis**: facePoint/radius returned by
   `AILAB_PLI_PreProcess` is opaque (no model weights or graph recovered).
5. **Bokeh calibration**: the vendor CoC curve (`radius/clear_range/coc_curve/
   fixed_param`), `StructD/StructO` calibration constants, SGBM/WLS disparity
   and Kepler matting are not reimplemented; a strength gain stands in for the
   blur-level mapping.
6. **ArcSoft portrait lighting** (`libgallery_arcsoft_portrait_lighting.so`
   15.6 MB, `_c` 11.7 MB): API `PortraitLight_Init/Process/SetFaceInfo/
   SetStyle/SetSource/SetIntensity/SetMask` confirmed, but no carveable model
   container and no weights extracted → ARCHITECTURE_ONLY.
7. **`libmotion_photo_mace.so`**: MACE engine that mmaps a model data file
   (`Failed to open model data file`); the model path/file is not in the APK.
8. **`libmibokeh_gallery.so`**: references `bokeh_mace: { engine_= ... }` and
   MACE params; no bokeh model asset is shipped → model unknown.
9. **No runtime verification**: SNPE / MACE / `wa::Refocus` are unavailable on
   this host, so no numerical cross-check of the decoded DLC graph or the
   vendor pipelines was possible.

## 9. Evidence index

| path | content |
|---|---|
| `inventory/` | androguard inventory (package, components, groups, AI flags) |
| `evidence/01_apk_layout.txt` | all assets + 40 arm64 libs with sizes |
| `evidence/02_dlc_container.txt` | ZIP listing, metadata, header hexdump |
| `evidence/03_dlc_graph.txt` | parsed graph: type histogram, tensors, quantizers, 100-layer list |
| `evidence/04_native_elf.txt` | ELF/JNI summaries for all extracted libs + big libs |
| `evidence/05_relight_library.txt` | relight lib sections/exports and algorithm strings |
| `evidence/06_docphoto_native.txt` | MACE model names, EdgeDet node names, cache header |
| `evidence/07_reimpl_verify.txt` | copy of `reimpl/verify.txt` |
| `evidence/configs/` | relighting / movie relighting effect configs, dualcam bokeh params |
| `evidence/hashes.txt` | SHA-256 of APK, DLC, model payload, npz, libs, scripts |
| `native/*.s`, `native/*.glsl`, `native/librelight_*.txt` | JNI disassembly, GLSL shader, exports/strings |
| `ir/dlc.py`, `ir/parse_dlc.py`, `ir/document_model_graph.json` | DLC parser + graph |
| `models/` | DLC asset, extracted payload, dequantised weights |
| `reimpl/` | runnable JAX pipelines, README, verify.txt, output images |
| `PIPELINE.md` | **full data-pipeline contract** for document, relighting, bokeh: triggers, formats, preprocessing, JNI signatures, model I/O, postprocess, consumers, failure/fallback |
| `native/doc_jni_ghidra.c` | Ghidra pseudocode of all 20 `DocumentProcessJNI` entry points |
| `native/doc_core_ghidra.c` | Ghidra pseudocode of the document engine vtable methods (`FUN_00182f54` scan, `FUN_00183798` crop+warp, `FUN_00183eb4` enhance, `FUN_0018493c` quad validation, `FUN_00185118` point clamp) |
| `native/wa_refocus_ghidra.c` | Ghidra pseudocode of `wa::Refocus::init/setFaces/setImages/setBokehStyle/setBokehSpotType/run` |
| `native/doc_get_bitmap_info.s`, `doc_rgba_to_rgb.s`, `doc_rgb_to_rgba.s`, `doc_bitmap_class.s`, `doc_bitmap_create.s` | exact Bitmap ↔ RGB staging at the document JNI boundary |
| `native/doc_scan_model_init.s`, `doc_resize_helper.s`, `doc_model_descriptors.txt` | ScanModel 384×384×3 engine init, resize helper, model I/O descriptor hexdumps |
| `native/refocus_config_bitmap.s`, `refocus_config_yuv.s`, `refocus_generate_image.s` | ASVL_OFFSCREEN/NV21 conversion used by both relighting and bokeh |
| `evidence/09_doc_pipeline.txt`, `evidence/10_relight_bokeh_pipeline.txt` | command transcripts and address/offset evidence for the three pipelines |

## 10. Data-pipeline addendum (document / relighting / bokeh)

Full contract in **`PIPELINE.md`**; this section records the findings that
correct or extend §§2–5.

### 10.1 Document path — exact stages

1. **Source**: `DocPhotoActivity.onCheckPermissionSucceed` →
   `Storage.fromIntent` → `new DocPhotoManager(...)` → `parsePhotoAsync()`.
   `DocPhotoParser` reads EXIF + XMP (`docPhoto=1`, `cropPoints`,
   `enhanceType`, `rawLength`) and decodes the raw JPEG after skipping
   `fileLength − rawLength`; `PhotoParser` decodes the preview and runs
   `doScan` (`sources/docphoto_flow/classes/...DocPhotoActivity.java`,
   `...DocPhotoManager.java`, `...PhotoMetaData.java`).
2. **Bitmap decode**: `BitmapFactory.decodeStream` with
   `inSampleSize = ImageUtils.computeInSampleInfo` (screen-bound preview) or
   `BigBitmapLoadUtils.calculateInSampleSize` (memory-budget save), then EXIF
   rotation. **Native accepts only `ANDROID_BITMAP_FORMAT_RGBA_8888`**
   (`0x78a00`: `info.format != 1` → fail).
3. **JNI staging**: `RGBA8888 → RGB 3 bytes/px` (`0x7884c`, `ld4`/`st3`), wrapped
   as OpenCV `CV_8UC3` (`0x10`) via `FUN_00180f34`.
4. **Scan**: `nativeScanDocumentBitmap(handle, bitmap, docType, float[8], mode)
   -> int`; `hed_model` engine (`hed_input`/`img_output`) is created at
   `0x89084` with dims **1×384×384×3 → 1×384×384×2** (`0x437000`,`0x437028`);
   `doEdgeDet` (`0x8779c`) resizes to 384×384 (`mov w8,#0x180`, `cv::resize`
   `0x383520`) and runs MACE. Returned `float[8]` = 4 corners in the delegate
   bitmap space; an empty vector falls back to the full image and points are
   clamped to `[0,w-1]×[0,h-1]` (`0x85118`).
5. **Warp + enhance**: `nativeCropImageBitmap` / `nativeCropAndEnhanceBitmap`
   call engine vtable slot 3 (`0x83798`): validate polygon (30.0f angle
   threshold), compute output size + cached aspect ratio (`0x182750`), warp with
   `getPerspectiveTransform`/`warpPerspective`, then slot 4 (`0x83eb4`) runs
   `enhance_model` — **input 1×512×512×3** (`0x437b80`, name `img_input`) — in
   place. Dewarp uses `dewarp_coarse_model`/`dewarp_fine_model` (193/385),
   handwrite removal uses `handwrite_model`.
6. **Output bitmap**: `Bitmap.createBitmap(outW,outH,Config.ARGB_8888,true,
   sourceColorSpace)` then RGB→RGBA with alpha `0xFF`
   (`0x78be4`,`0x78ce4`,`0x78904`).
7. **Save**: `DocPhotoManager.render()` → JPEG **q97** (`DocPhotoUtils`),
   EXIF MIME 4 / orientation 1 / stripped depth+focus tags, optional XMP
   `docPhoto` block + appended raw JPEG for re-editing, MediaStore insert and
   `setResult` URI (`...extra_photo_editor_type_doc_photo`).
8. **Correction to §2.1**: the *shipping* scan model is 384×384×3→384×384×2;
   the shipped SNPE DLC is 512×512×3→512×512×1 and matches the **enhance**
   model's spatial size. The DLC is still not observably consumed (no SNPE
   parser in the lib).

### 10.2 Relighting path — exact stages

* `RelightingOperatingFragment` (effect selection / save) →
  `MiBrainRelightingProcessor` → `MiDualPhotoJni` → `libmibrain_relighting_jni`
  → `AILAB_PLI_*` → `librelight_only_extraphoto.so` GLES3.
* Buffers are **ASVL_OFFSCREEN** descriptors over **NV21** (Y plane +
  interleaved VU), created by `DualPhotoJni.generateNativeImage`
  (`convertBitmapToOffscreenData`); `configYUVData` de-interleaves VU into
  separate U/V display planes; `configBitmapByNativeImage` converts NV21→RGBA
  with `encodeyuvToRGBA` (`librefocus.so` `0xfb10`; `native/refocus_*.s`).
* JNI signatures:
  `initBrainRelightingEffect(int,long,long,int,int[])long`,
  `setBrainRelightingLightSource(long,int,int,int,int)long`,
  `enableBrainRelightingEffect(long,int,long,long,long,long,int,int[])void`,
  `releaseBrainRelightingHandle(long)void`.
* `AILAB_PLI_Init(handle,0,w,h)`; `PreProcess(handle,img,degree,int[3])` with
  degree mapping **90→2, 180→4, 270→3, else 1**; `Process(handle,{w,h,depth},
  origin,&effectType,effect)`; `GetLightSource(handle,int[4])`.
* **Gesture correction**: `MiBrainRelightingProcessor.setLightSource` is empty
  and `supportAdjustLightSource()` is false; the JNI always calls
  `setBrainRelightingLightSource(handle, W/2, H/2, W, H)` (packed
  `{w,h,x,y}` for `AILAB_PLI_SetLightSource`). The touch gesture only reaches
  native on the ArcSoft backend (`DualPhotoJni.setRelightingLightSource(handle,
  x, y, originW, originH)`). The shader's `moveLightPos` is therefore driven
  with zero offset in this build.
* Save: full-res re-init/render into the origin bitmap → JPEG **q100** +
  EXIF/watermark (`DualPhotoFileUtils.writeBitmap`); preview: YUV planes to the
  GLES display.

### 10.3 Bokeh path — exact stages

* `AdjustOperatingFragment` (slider → `RefocusRunnable`, save → `SaveRunnable`)
  → `MegviiAdjustProcessor` → `MegviiDualPhotoJni` → `libmegvii_bokeh_jni`
  (`0x8a8c`) → `wa::Refocus::setImages` (`0x1f07c0`) → `wa::Refocus::run`
  (`0x1f082c`, `int focusX,int focusY,float fNumber,Image& out`) in
  `libwa_refocus_extraphoto.so`.
* JNI signature:
  `processRefocusWidthHandle(long,long,long,long,int,int,int,float,int,int,int)void`.
  It builds three `wa::Image`s over the ASVL NV21 buffers: origin/effect with
  `ImageType=6,DataType=0`; depth with `ImageType=0,DataType=(portraitType!=1)`.
  Depth width/height fall back to the origin size when ≤0. The output image is
  the Java **effect** buffer.
* Focus space: process-bitmap pixels (`focus/(originW/processW)`); save
  multiplies back and runs at full resolution. f-number:
  `ALG_F_VALUE[findFValueIndex(blurLevel)]` (25-entry 1.0…16.0 ladder collapsed
  to 9 algorithmic apertures).
* No DNN: `[RF] not support setModel anymore.`; classical CoC/`ComputeCocDepth`,
  SGBM+WLS, `AlphaRefineKepler`, `ApertureCpu::RenderBokehColor`, `BlurWorker`,
  `ImgMergerKepler`.
* Preview/save on the display render handler; save JPEG **q100** via
  `DualPhotoFileUtils`; result converted NV21→RGBA by
  `configBitmapByNativeImage`.

### 10.4 Consumer (preview vs save) summary

| | Document | Relighting | Bokeh |
|---|---|---|---|
| Preview | `Bitmap` from `renderPreview()` on AsyncTask pool | YUV planes on render handler | YUV planes on render handler |
| Save | full-res re-decode + re-run, JPEG q97, EXIF+XMP+raw append | full-res re-render, JPEG q100 + watermark | full-res re-render, JPEG q100 |
| Delivery | `setResult(uri,"image/jpeg")` + MediaStore | MediaStore insert/scan + UI | MediaStore insert/scan + UI |
| Failure | unsupported toast + exit; export error → exit | init-fail UI / silent no-op | silent no-op |

### 10.5 Reproduction (new evidence)

```bash
PY=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python
WS=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work
cd $WS/reports/miui_extraphoto

$PY $WS/tools/disasm_elf.py /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/xp/libdoc_photo_process.so --list-jni
$PY $WS/tools/disasm_elf.py /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/xp/libdoc_photo_process.so \
    --addr 0x7884c --size 0x180            # RGBA→RGB
$PY $WS/tools/disasm_elf.py reports/miui_extraphoto/native/librefocus.so \
    --symbol Java_com_miui_extraphoto_refocus_manager_DualPhotoJni_configBitmapByNativeImage

flock $WS/logs/ghidra.lock -c \
  "$WS/tools/ghidra_decompile.sh /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/xp/libdoc_photo_process.so \
   reports/miui_extraphoto/native/doc_jni_ghidra.c \
   'Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_.*' \
   --timeout 2400 --max-functions 25 --heap 3G"
xxd -s 0x436f00 -l 0x180 /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/xp/libdoc_photo_process.so   # ScanModel I/O
```
