#!/usr/bin/env python3
"""Architecture-level JAX re-implementation of the MiBrain "brain relighting"
pipeline (com.miui.extraphoto.refocus.core.relighting.MiBrainRelightingProcessor
-> MiDualPhotoJni -> libmibrain_relighting_jni.so -> librelight_only.so).

Evidence for every stage
------------------------
Java (sources/docphoto/classes/...MiDualPhotoJni.java,
      sources/refocus/classes/...MiBrainRelightingProcessor.java):
  initProcessor():
      handle = initBrainRelightingEffect(depthDegree, originImage,
                                         depthData, depthLen, facePoint[4])
  enableRelightingEffect(effectType):
      setBrainRelightingLightSource(handle, W/2, H/2, W, H)
      enableBrainRelightingEffect(handle, effectType, blurImage,
                                  effectImage, originImage,
                                  depthData, depthLen, pointCache[2])
  doSave(): same calls with the ORIGINAL effect type and a freshly blurred image.

JNI (native/initBrain.s, native/enableBrain.s, native/setBrainLight.s):
  init   -> AILAB_PLI_Init(&handle, 0, img.w, img.h)
            AILAB_PLI_PreProcess(handle, imgdesc, orientation, out{status, faceXY, radius})
  light  -> AILAB_PLI_SetLightSource(handle, {w, h, x, y})
  enable -> AILAB_PLI_Process(handle, {w, h, depthPtr, blurImageDesc},
                              originDesc, &effectType, effectDesc)
            AILAB_PLI_GetLightSource(handle, out{.., x, y})
  Images are RGBA_8888 Android bitmaps (getImageByPointer), depth is the raw
  MiCamera depth buffer (NativeData).

librelight_only.so (91 MB, GLESv3/EGL):
  * inside: mi_portrait_3d_lighting_image.cpp / mi_gpu_relight_filter.cc and
    the AILAB_PLI_* implementation (strings "AILAB_PLI_Init!" ...).
  * render pass is a GLSL ES 3.0 fragment shader (native/relight_render_shader.glsl):
        photoColor(uv) = screen( base(uv), lightColor(uv) )
        lightColor(uv) = texture(text_light, clamp(uv + moveLightPos))
        base = gaussianBlur5(origin) blended 0.5 when needBlur
        optional colour separation, 3D-LUT colour filter, noise layer
    light source position -> moveLightPos; light map is shifted, then screened.
  * effect_type values come from assets/refocus/relighting/*/config.json
    (relightingType 0x03..0x18: natural / stage / movie / rainbow / blinds /
     dots / leaves / holi ...), i.e. one light-pattern texture per effect.

The exact light-pattern textures and the AILAB_PLI preprocessing (face/depth
analysis) live in the opaque 82.9 MB `.data` payload of librelight_only.so and
are NOT decoded here.  We therefore re-implement the *documented renderer*
(screen blend with a positional light map + gaussian blur + depth-based base
selection) and synthesise the light maps procedurally.  Marked inferred.
"""
import os
import numpy as np
import jax
import jax.numpy as jnp

# relightingType from assets/refocus/relighting/*/config.json
EFFECTS = {
    0x03: "natural", 0x10: "blinds", 0x12: "dots", 0x14: "leaves",
    0x15: "rainbow", 0x16: "stage", 0x17: "movie", 0x18: "holi",
}


def _grid(h, w):
    y, x = jnp.meshgrid(jnp.linspace(0, 1, h), jnp.linspace(0, 1, w), indexing="ij")
    return x, y


def gaussian_blur5(img):
    """5x5 gaussian exactly as in native/relight_render_shader.glsl
    (guassianBlur5, sigma normalised by width)."""
    k = jnp.array([
        [0.0030, 0.0133, 0.0219, 0.0133, 0.0030],
        [0.0133, 0.0596, 0.0983, 0.0596, 0.0133],
        [0.0219, 0.0983, 0.1621, 0.0983, 0.0219],
        [0.0133, 0.0596, 0.0983, 0.0596, 0.0133],
        [0.0030, 0.0133, 0.0219, 0.0133, 0.0030],
    ], dtype=jnp.float32)
    k = k / k.sum()
    # depthwise: group = 3 channels, kernel [kh, kw, 1, 3]
    kh = jnp.broadcast_to(k[:, :, None, None], (5, 5, 1, 3))
    return jax.lax.conv_general_dilated(
        img[None], kh, window_strides=(1, 1), padding=((2, 2), (2, 2)),
        feature_group_count=3, dimension_numbers=("NHWC", "HWIO", "NHWC"))[0]


def light_map(effect_type, h, w, cx=0.5, cy=0.5):
    """Procedural stand-in for the per-effect `text_light` texture.

    `relightingType` selects the pattern; only the *names* (natural, stage,
    movie, rainbow, blinds, dots, leaves, holi) are known from the shipped
    config.json files, not the textures themselves.
    """
    x, y = _grid(h, w)
    dx, dy = x - cx, y - cy
    r = jnp.sqrt(dx * dx + dy * dy)
    name = EFFECTS.get(effect_type, "natural")
    if name == "blinds":
        pat = 0.5 + 0.5 * jnp.sign(jnp.sin((x + 0.3 * y) * 40.0))
    elif name == "dots":
        pat = (jnp.cos(2 * jnp.pi * x * 24.0) * jnp.cos(2 * jnp.pi * y * 24.0))
        pat = jnp.clip(pat, 0.0, 1.0) ** 0.5
    elif name == "leaves":
        pat = (jnp.sin(2 * jnp.pi * (x * 7 + y * 3)) *
               jnp.cos(2 * jnp.pi * (x * 3 - y * 6)))
        pat = jnp.clip(pat, 0.0, 1.0) ** 1.5
    elif name == "rainbow":
        pat = 0.5 + 0.5 * jnp.cos(2 * jnp.pi * r * 4.0)
    elif name == "stage":
        pat = jnp.clip(1.0 - r * 2.2, 0.0, 1.0) ** 2
    elif name == "movie":
        pat = jnp.clip(1.0 - jnp.abs(dx) * 1.8, 0.0, 1.0)
    elif name == "holi":
        pat = (jnp.sin(2 * jnp.pi * (x * 9 + y * 5)) +
               jnp.sin(2 * jnp.pi * (x * 13 - y * 11))) * 0.25 + 0.5
        pat = jnp.clip(pat, 0.0, 1.0)
    else:  # natural / soft fill
        pat = jnp.clip(1.0 - r * 1.4, 0.0, 1.0) ** 1.5
    radial = jnp.clip(1.0 - r * 1.1, 0.0, 1.0)
    pat = pat * (0.35 + 0.65 * radial)

    if name == "rainbow":
        col = jnp.stack([0.5 + 0.5 * jnp.sin(6.28 * (r * 3 + 0.0)),
                         0.5 + 0.5 * jnp.sin(6.28 * (r * 3 + 0.33)),
                         0.5 + 0.5 * jnp.sin(6.28 * (r * 3 + 0.66))], axis=-1)
    elif name in ("stage", "movie"):
        col = jnp.broadcast_to(jnp.array([1.0, 0.86, 0.68], jnp.float32), (h, w, 3))
    elif name in ("holi", "rainbow", "leaves"):
        col = jnp.stack([jnp.ones_like(x), 0.55 + 0.45 * x, 0.35 + 0.65 * y], axis=-1)
    else:
        col = jnp.broadcast_to(jnp.array([1.0, 0.95, 0.85], jnp.float32), (h, w, 3))
    return col * pat[..., None]


def _screen(a, b):
    """SCREEN(a,b) = 1 - (1-a)(1-b); exactly the shader's screen() helper."""
    return 1.0 - (1.0 - a) * (1.0 - b)


def run(origin, blur, depth, light_xy=None, effect_type=0x03,
        src_w=None, src_h=None, blur_mix=0.5):
    """Relighting render.

    origin/blur : (H, W, 3) float32 in [0,1]  (origin image + bokeh-blurred image)
    depth       : (H, W) float32             (MiCamera depth buffer)
    light_xy    : light source in bitmap pixels (default: image centre)
    src_w/src_h : bitmap size passed to AILAB_PLI_SetLightSource
    """
    origin = np.asarray(origin, np.float32)
    blur = np.asarray(blur, np.float32)
    depth = np.asarray(depth, np.float32)
    h, w = origin.shape[:2]
    if light_xy is None:
        light_xy = (w / 2.0, h / 2.0)
    src_w = src_w or w
    src_h = src_h or h

    img = jnp.asarray(origin)
    # 1. base: origin with the shader's optional 5x5 gaussian at 50 %
    blurred = gaussian_blur5(img)
    base = img * (1.0 - blur_mix) + blurred * blur_mix

    # 2. depth drives how much of the already-rendered blur image is used
    #    (foreground = origin, background = bokeh image).  The AILAB_PLI
    #    network/preprocess that chooses this split is opaque; a smooth
    #    depth ramp is used here.
    d = jnp.asarray(depth)
    dn = (d - d.min()) / jnp.maximum(d.max() - d.min(), 1e-6)
    alpha = jnp.clip(dn * 1.4 - 0.2, 0.0, 1.0)      # 1 = background
    base = base * (1 - alpha[..., None]) + jnp.asarray(blur) * alpha[..., None]

    # 3. light position -> moveLightPos, then shift the light texture.
    lx = float(light_xy[0]) / float(src_w)
    ly = float(light_xy[1]) / float(src_h)
    # shader: pos = clamp(uv + moveLightPos); "AdjustLightPos max_width_offset"
    mx = (lx - 0.5) * 0.5
    my = (ly - 0.5) * 0.5
    lm = light_map(effect_type, h, w, cx=0.5 - mx, cy=0.5 - my)

    # 4. screen blend of light over the base (shader blendSource)
    out = _screen(base, lm)

    # 5. shader clamps before YUV conversion
    out = jnp.clip(out, 0.0, 1.0)
    return np.asarray(out), {"light_uv": (lx, ly), "moveLightPos": (mx, my),
                             "effect": EFFECTS.get(effect_type, "natural")}


def _write_ppm(path, rgb):
    rgb = np.clip(rgb * 255.0 + 0.5, 0, 255).astype(np.uint8)
    h, w = rgb.shape[:2]
    with open(path, "wb") as f:
        f.write(f"P6\n{w} {h}\n255\n".encode())
        f.write(rgb.tobytes())


def main():
    rng = np.random.default_rng(1)
    h, w = 256, 320
    yy, xx = np.mgrid[0:h, 0:w].astype(np.float32)
    origin = np.stack([0.35 + 0.3 * xx / w, 0.30 + 0.25 * yy / h,
                       np.full_like(xx, 0.30)], axis=-1)
    origin = np.clip(origin + 0.03 * rng.standard_normal(origin.shape), 0, 1)
    depth = 800.0 + 600.0 * (yy / h) + 40.0 * rng.standard_normal((h, w))
    blur = np.asarray(gaussian_blur5(jnp.asarray(origin))) * 0.9

    out, meta = run(origin, blur, depth, light_xy=(0.35 * w, 0.40 * h),
                    effect_type=0x16, src_w=w, src_h=h)
    print(f"origin {origin.shape} blur {blur.shape} depth {depth.shape}")
    print(f"light {meta}")
    print(f"output {out.shape} range=[{out.min():.3f},{out.max():.3f}] "
          f"mean={out.mean():.3f}")
    assert out.shape == origin.shape
    assert np.isfinite(out).all()
    for et in (0x03, 0x10, 0x12, 0x14, 0x15, 0x17, 0x18):
        o, _ = run(origin, blur, depth, (w / 2, h / 2), et, w, h)
        print(f"  effect 0x{et:02x} ({EFFECTS[et]:8s}) -> {o.shape} mean={o.mean():.3f}")
    _write_ppm(os.path.join(os.path.dirname(os.path.abspath(__file__)),
                            "relighting_output.ppm"), out)
    print("wrote relighting_output.ppm")


if __name__ == "__main__":
    main()
