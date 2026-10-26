# ExtraPhoto JAX re-implementations

All three pipelines are runnable CPU-JAX code; run everything with:

```bash
cd reimpl
/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python run_all.py    # writes verify.txt
```

| file | pipeline | weights | status |
|---|---|---|---|
| `document_hed.py` | document scan / edge model from `document_model.dlc` | **real** uint8 DLC weights (dequantised, 314,289 B) | `runs_unverified` — all 99 layer shapes reproduce the declared DLC dims; no SNPE runtime available for a numeric reference |
| `relighting.py` | MiBrain portrait relighting renderer | none (GPU shader + light textures, textures opaque) | `architecture_only` / inferred |
| `bokeh.py` | Megvii `wa::Refocus` dual-camera bokeh | none (classical CoC + aperture blur) | `architecture_only` / inferred |
| `mace_loader.py` | embedded MACE document models in `libdoc_photo_process.so` | **real** fp16 weights (6 buffers, 18,650,700 B, 466 const tensors) | `metadata_and_weights` — names/tensors/payloads recovered and verified, operator graph absent (`graph_decoded=false`) |

Shared files: `../ir/dlc.py` (protobuf DLC parser), `../models/dlc_extract/model`
(the SNPE payload), `../models/document_model_weights.npz` (dequantised weights).

## document_hed.py

* Parses `document_model.dlc` -> `model` (SNPE protobuf), 100 tensors / 100 layers.
* Dequantises every kernel/bias: `x = step * (q + offset)` (uint8 storage,
  per-tensor `{bitwidth,min,max,step,offset}`; formula verified for all 107
  quantizers).
* Executes the graph in declared order with JAX:
  Conv2D / depthwise Conv2D / Conv2DTranspose / ReLU / ReLU6 / Sigmoid /
  add / concat(axis=3) / `truediv` by the constant 255.
* Input `hed_input:0` = `(1,512,512,3)`; output `img_output:0` = `(1,512,512,1)`.
* Asserts the computed shape of **every** layer against the DLC's declared
  output dims (99 checks), prints output statistics and writes
  `doc_hed_output.pgm`.

The model is weight-quantised only (activation tensors carry no Quantizer), so
float32 activations are used, matching SNPE's float-activation path.

## mace_loader.py

The shipping document engine is MACE, not SNPE: `libDocumentProcess.so` embeds
five model description blocks (name streams + `u64 byte_offset, u64
fp16_element_count` tables) and six fp16 weight buffers. `mace_loader.py` parses
that grammar, joins each block to its payload through the constant-returning
getters in `.text`, decodes every constant tensor, and carves the result to
`../models/embedded/mace/` (`reimpl/mace_report.json`, `MANIFEST.json`).
It is deliberately **not** an inference engine: the MACE `NetDef` is built at
run time and is not present in the image, so there is no operator graph. See
`GAP_CLOSURE.md` §6 and `../evidence/mace_refscan.txt` for the evidence.

```bash
$PY reimpl/mace_loader.py --report      # blocks, payloads, shapes
$PY reimpl/mace_loader.py --check ../models/embedded/mace
$PY reimpl/mace_loader.py --self-test   # synthetic grammar test
```

## relighting.py

Re-implements the renderer documented by `native/relight_render_shader.glsl`
and `native/librelight_exports.txt` / `native/initBrain.s`:

```
handle = AILAB_PLI_Init(w,h)
         AILAB_PLI_PreProcess(origin, orientation) -> face/centre/radius
AILAB_PLI_SetLightSource(handle, {w,h,x,y})
AILAB_PLI_Process(handle, {w,h,depth,blurImage}, origin, effectType, effectImage)
         AILAB_PLI_GetLightSource -> (x,y)
render:  base  = gaussianBlur5(origin) mixed 0.5 when needBlur
         light = texture(text_light, uv + moveLightPos)
         out   = screen(base, light)        # 1-(1-a)(1-b)
```

`effect_type` (0x03..0x18) selects the light pattern; only effect *names* are
known from `assets/refocus/relighting/*/config.json`, so light maps are
generated procedurally (`light_map()`), documented as inferred.
Shapes: origin/blur `(H,W,3)` (native `NativeImage`/RGBA_8888 bitmap), depth
`(H,W)`, light source `(x,y)` in bitmap pixels. Production H,W come from
`NativeImage` at runtime (no fixed size in the code); the self-test uses
256x320.

## bokeh.py

Re-implements the documented `wa::Refocus` CPU pipeline
(`native/megvii_process.s`, exports `wa::Refocus::setImages/run/...`):

```
coc(d)  = A*f*|d-d_f| / (d*(d_f-f)) * pixel_pitch * strength
spot(r) = disk or n-blade polygon  (setBokehSpotType 5/6)
blur    = per-pixel CoC bin -> depthwise disk/polygon convolution
out     = mix(image, blurred, clip(coc/1.5))
```

`f_number` uses the Java ladder `MegviiAdjustProcessor.ALG_F_VALUE`
(1.0 .. 16.0); `focus_xy` is the touch point passed through
`processRefocusWidthHandle(..., focusX, focusY, algFValue, depthW, depthH,
portraitType)`. The vendor's calibrated CoC curve (`[coc dslr] radius:...,
clear_range:..., coc_curve:...`) and the AlphaRefine/SGBM/WLS front-end are
not public, so a `strength` gain calibrates the physical CoC to the app's
render radius (inferred). Shapes: image `(H,W,3)`, depth `(H,W)`; H,W come from
the `NativeImage`/depth buffers at runtime (self-test 256x320).

## Limitations

* No SNPE / MACE / wa::Refocus runtime is available on this host, so no
  numeric reference comparison is possible.
* `librelight_only_extraphoto.so` keeps its light maps / 82.9 MB `.data`
  payload opaque; the procedural light maps are stand-ins.
* The Megvii/MTK bokeh depth front-end (SGBM + WLS + matting) is skipped; the
  depth map is taken as input.
