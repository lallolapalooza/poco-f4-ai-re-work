#!/usr/bin/env python3
"""Architecture-level JAX re-implementation of the ExtraPhoto dual-camera
refocus / bokeh renderer.

Two vendor backends ship in the APK:

1. Megvii "wa::Refocus" (libwa_refocus_extraphoto.so, 8.9 MB, plain C++/OpenCV)
   JNI  : Java_com_miui_extraphoto_refocus_manager_MegviiDualPhotoJni_*
          (native/megvii_process.s)
   Java : com.miui.extraphoto.refocus.core.adjuest.MegviiAdjustProcessor
          -> processRefocusWidthHandle(handle, targetImage, originImage,
             depthData, focusX, focusY, algFValue, depthW, depthH, portraitType)
   Exported C++ API (native exports):
       wa::Refocus::setImages(Image, Image[, Image])
       wa::Refocus::setFaces(WFace*, int, Rotation)
       wa::Refocus::setBokehStyle(int) / setBokehSpotType(int)
       wa::Refocus::run(int focusX, int focusY, float fNumber, Image& out)
   Internal modules named in .rodata:
       CocDepth / CocDepthDisp -> ComputeCocDepth / ComputeCocDepthAndDisp
       AlphaRefineKepler (matting), ApertureCpu::RenderBokehColor /
       RenderYBokeh, BlurWorker (rotate+zoom / disk blur), ImgMergerKepler
       (median + Gauss), Wa SGBM block-matching for the disparity, WLS filter.
   "cpu model" / "[RF] not support setModel anymore." -> no DNN weights.

2. MediaTek/MiAlgo stack (libmialgo_portrait.so ->
   libmiphone_refocus_bokeh.so): same CoC idea, modules
   depth_of_field_segmentation.cpp, block match, scatter_blur.cpp,
   spot_proc.cpp, edge_refinement.cpp, bokeh_pre_proc.cpp.

Default parameters are the shipped assets/refocus/micamera_dual_bokeh/
dualcam_bokeh_params.json (StructD: aperture radius/smooth params; StructO:
calibration f=1.05..4.0 etc.).

This file reproduces the documented CoC -> aperture-spot -> blur -> merge
pipeline.  It is NOT a bit-exact port: the proprietary CoC curve calibration,
matting network (AlphaRefineKepler is classical matting, not a DNN) and the
SGBM/WLS disparity front-end are not reimplemented.
"""
import os
import numpy as np
import jax
import jax.numpy as jnp

# MegviiAdjustProcessor.F_VALUE / ALG_F_VALUE (f-number ladder)
F_VALUE = [1.0, 1.1, 1.2, 1.4, 1.6, 1.8, 2.0, 2.2, 2.5, 2.8, 3.2, 3.5,
           4.0, 4.5, 5.0, 5.6, 6.3, 7.1, 8.0, 9.0, 10.0, 11.0, 13.0, 14.0, 16.0]
ALG_F_VALUE = [1.0, 1.0, 1.4, 1.4, 1.4, 2.0, 2.0, 2.0, 2.8, 2.8, 2.8, 4.0,
               4.0, 4.0, 5.6, 5.6, 5.6, 8.0, 8.0, 8.0, 11.0, 11.0, 11.0,
               16.0, 16.0]

# config/calibration defaults (assets/refocus/micamera_dual_bokeh/
# dualcam_bokeh_params.json -> StructO/StructD); inferred interpretation
CAL = {
    "focal_mm": 4.7,          # StructO paramD = 4.0 / paramB = 1.05 calibration
    "sensor_width_mm": 5.6,   # phone main sensor; pixel pitch derived from W
    "spot_style": "disk",     # setBokehStyle: 0 disk / 1 hexagon-ish spots
    "spot_blades": 6,         # setBokehSpotType 5/6-blade
    "max_coc_px": 24.0,
}


def coc_from_depth(depth_mm, focus_xy, f_number, focal_mm=None,
                   sensor_width_mm=None, width=None, strength=40.0):
    """Thin-lens circle of confusion in pixels.

    coc_mm(d) = A * f * |d - d_f| / (d * (d_f - f)),   A = f / N
    coc_px    = coc_mm / (sensor_width_mm / W)

    (The vendor uses a calibrated CoC curve: "[coc dslr] radius:...,
    clear_range:..., coc_curve:..., fixed_param:..." - this is the textbook
    approximation of that curve.)
    """
    focal_mm = focal_mm or CAL["focal_mm"]
    sensor_width_mm = sensor_width_mm or CAL["sensor_width_mm"]
    h, w = depth_mm.shape
    width = width or w
    fx, fy = focus_xy
    fx = int(np.clip(fx, 0, w - 1))
    fy = int(np.clip(fy, 0, h - 1))
    d_f = float(depth_mm[fy, fx])
    aperture = focal_mm / float(f_number)
    d = np.maximum(depth_mm.astype(np.float32), 1.0)
    coc_mm = aperture * focal_mm * np.abs(d - d_f) / (d * np.maximum(d_f - focal_mm, 1e-3))
    px_per_mm = width / sensor_width_mm
    # The vendor applies a calibrated CoC curve and the app's `blurLevel`
    # (MegviiAdjustProcessor.findFValueIndex(blurLevel)); the physical thin-lens
    # value is far smaller than the rendered bokeh radius, so a `strength`
    # gain reproduces the app's blur-level scaling (inferred).
    return np.clip(coc_mm * px_per_mm * strength, 0.0,
                   CAL["max_coc_px"]).astype(np.float32)


def aperture_kernel(radius, blades=None, style=None):
    """Aperture spot kernel: disk, or a polygonal (5/6-blade) spot.

    wa::Refocus exposes setBokehStyle / setBokehSpotType; the MTK module is
    spot_proc.cpp ("not a supported bokeh spot type").
    """
    style = style or CAL["spot_style"]
    blades = blades or CAL["spot_blades"]
    r = int(np.ceil(radius))
    yy, xx = np.mgrid[-r:r + 1, -r:r + 1].astype(np.float32)
    rr = np.sqrt(xx * xx + yy * yy)
    if style == "disk" or blades < 3:
        k = (rr <= radius).astype(np.float32)
    else:
        th = np.arctan2(yy, xx)
        edge = radius * (0.86 + 0.14 * np.cos(blades * th))
        k = (rr <= edge).astype(np.float32)
    s = k.sum()
    return k / s if s > 0 else k


def _blur(img, kernel):
    # depthwise convolution: one shared kernel per channel -> [k, k, 1, 3]
    kh = jnp.asarray(kernel[:, :, None, None], jnp.float32)
    kh = jnp.broadcast_to(kh, kernel.shape + (1, 3))
    k = kernel.shape[0]
    p = k // 2
    y = jax.lax.conv_general_dilated(
        img[None], kh, window_strides=(1, 1), padding=((p, p), (p, p)),
        feature_group_count=3, dimension_numbers=("NHWC", "HWIO", "NHWC"))[0]
    return y


def run(image, depth_mm, focus_xy, f_number, spot_style=None, n_levels=12,
        strength=40.0):
    """Depth-dependent bokeh render.

    image    : (H, W, 3) float32 [0,1]
    depth_mm : (H, W) float32 millimetres (MiCamera depth / SGBM disparity
               converted upstream)
    focus_xy : touch point in image pixels (Java refocusData.focusX/focusY)
    f_number : ALG_F_VALUE[index] passed as `float f` to wa::Refocus::run
    """
    image = np.asarray(image, np.float32)
    depth_mm = np.asarray(depth_mm, np.float32)
    h, w = image.shape[:2]
    coc = coc_from_depth(depth_mm, focus_xy, f_number, width=w,
                         strength=strength)

    # quantise CoC into n_levels radius bins (coarse-to-fine scatter blur)
    max_r = float(CAL["max_coc_px"])
    radii = np.linspace(0.0, max_r, n_levels)
    levels = np.clip(np.searchsorted(radii, coc), 0, n_levels - 1)

    img = jnp.asarray(image)
    acc = jnp.zeros_like(img)
    for li in range(1, n_levels):
        r = float(radii[li])
        k = aperture_kernel(r, style=spot_style)
        blurred = _blur(img, k)
        wgt = (levels == li).astype(jnp.float32)[..., None]
        acc = acc + blurred * wgt
    zero = (levels == 0).astype(jnp.float32)[..., None]
    acc = acc + img * zero

    # edge/alpha refinement stand-in: fully in-focus pixels stay untouched
    alpha = jnp.clip(jnp.asarray(coc) / 1.5, 0.0, 1.0)[..., None]
    out = img * (1.0 - alpha) + acc * alpha
    out = jnp.clip(out, 0.0, 1.0)
    return np.asarray(out), {
        "focus_depth_mm": float(depth_mm[int(focus_xy[1]), int(focus_xy[0])]),
        "coc_px_range": (float(coc.min()), float(coc.max())),
        "f_number": f_number,
        "levels": n_levels,
        "coc_strength": strength,
    }


def _write_ppm(path, rgb):
    rgb = np.clip(rgb * 255.0 + 0.5, 0, 255).astype(np.uint8)
    h, w = rgb.shape[:2]
    with open(path, "wb") as f:
        f.write(f"P6\n{w} {h}\n255\n".encode())
        f.write(rgb.tobytes())


def main():
    rng = np.random.default_rng(2)
    h, w = 256, 320
    yy, xx = np.mgrid[0:h, 0:w].astype(np.float32)
    # synthetic scene: foreground disc at 900 mm, background ramp 1500-4000 mm
    disc = ((xx - w * 0.5) ** 2 + (yy - h * 0.45) ** 2) < (h * 0.22) ** 2
    depth = np.where(disc, 900.0, 1500.0 + 2500.0 * (yy / h)).astype(np.float32)
    depth += 10.0 * rng.standard_normal(depth.shape)
    image = np.stack([
        0.25 + 0.6 * (xx / w),
        0.2 + 0.5 * (yy / h),
        0.35 + 0.2 * disc,
    ], axis=-1).astype(np.float32)
    image = np.clip(image + 0.02 * rng.standard_normal(image.shape), 0, 1)

    focus = (w // 2, int(h * 0.45))
    out, meta = run(image, depth, focus, f_number=2.8)
    print(f"image {image.shape} depth {depth.shape} focus={focus} "
          f"f/{meta['f_number']}")
    print(f"focus depth {meta['focus_depth_mm']:.1f} mm  "
          f"coc {meta['coc_px_range'][0]:.2f}..{meta['coc_px_range'][1]:.2f} px")
    print(f"output {out.shape} range=[{out.min():.3f},{out.max():.3f}] "
          f"mean={out.mean():.3f}")
    assert out.shape == image.shape
    assert np.isfinite(out).all()
    for fnum in (1.4, 2.8, 5.6):
        o, m = run(image, depth, focus, f_number=fnum)
        print(f"  f/{fnum:<4} coc max {m['coc_px_range'][1]:5.2f} px  "
              f"mean {o.mean():.3f}")
    for style in ("disk", "hexagon"):
        o, _ = run(image, depth, focus, 2.8, spot_style=style)
        print(f"  spot {style:8s} mean {o.mean():.3f}")
    _write_ppm(os.path.join(os.path.dirname(os.path.abspath(__file__)),
                            "bokeh_output.ppm"), out)
    print("wrote bokeh_output.ppm")


if __name__ == "__main__":
    main()
