#!/usr/bin/env python3
"""Verification suite for the Snapchat / Looksery CV reimplementation.

Runs the reconstructed on-device stages on synthetic frames, cross-checks the
JAX cascade against an independent scalar evaluator, exercises the shipped
model assets, and records every result in ``verify.txt``.

    python verify.py            # writes verify.txt, exits 0 on success
"""

from __future__ import annotations

import hashlib
import os
import platform
import sys
import time

import numpy as np

import looksery_assets as la
import haar
import orb
import deform
import pipeline

HERE = os.path.dirname(os.path.abspath(__file__))
MODELS = os.path.join(os.path.dirname(HERE), "models")
RESULTS = []


def check(name, fn):
    t0 = time.time()
    try:
        detail = fn()
        RESULTS.append((name, True, detail, time.time() - t0))
        print(f"PASS {name}: {detail}  ({time.time() - t0:.1f}s)")
    except Exception as exc:                      # noqa: BLE001 - report all
        RESULTS.append((name, False, f"{type(exc).__name__}: {exc}",
                        time.time() - t0))
        print(f"FAIL {name}: {type(exc).__name__}: {exc}")


def sha256(path):
    h = hashlib.sha256()
    with open(path, "rb") as fh:
        for chunk in iter(lambda: fh.read(1 << 20), b""):
            h.update(chunk)
    return h.hexdigest()


# --------------------------------------------------------------------------
# checks
# --------------------------------------------------------------------------
def check_haar_asset():
    c = la.parse_haar()
    s = la.haar_summary(c)
    assert s["window"] == [20, 20]
    assert s["stages"] == 20, s["stages"]
    assert s["trees"] == 1047
    assert s["rects"] == 4535
    assert s["stage_thresholds"][0] == 0.350692
    assert s["stage_thresholds"][-1] == 53.755569
    return (f"20x20 cascade, {s['stages']} stages, {s['trees']} trees, "
            f"{s['nodes']} nodes, {s['rects']} weighted rects, "
            f"trees/stage {s['trees_per_stage']}")


def check_lzc_container():
    meta = la.parse_lzc(la.LZC_PATH)
    assert meta["version"] == 1 and meta["count"] == 6, meta
    assert meta["ct_offsets_cumulative_ok"], "entry offsets do not tile the stream"
    assert meta["payload_is_zstd"]
    assert meta["payload_len"] == meta["compressed_size"]
    blobs = la.lzc_extract(la.LZC_PATH)
    xml = blobs["/Tracking.xml"].decode()
    for stage in ("FaceDetectorStage", "FaceRegressor.bin", "CandideRegressor.bin",
                  "CandidePespectiveRegressor.bin", "EyesRegressor.bin",
                  "DenoiseRegressor.bin", "ConfidenceThreshold=\"0.76\""):
        assert stage in xml, stage
    sizes = {k: len(v) for k, v in blobs.items()}
    assert sum(sizes.values()) == meta["uncompressed_size"]
    return (f"LZC v1, {meta['count']} entries, zstd frame "
            f"{meta['compressed_size']}B -> {meta['uncompressed_size']}B, "
            f"ERT pipeline: {', '.join(sizes)}")


def check_orb_tree():
    path = os.path.join(la.ORB_TREE_DIR,
                        "tree-ORB-sharp-b-20-d-2-r-10-numSig-195777265.bin")
    info = la.parse_orb_tree(path)
    assert info["branching"] == 20 and info["depth"] == 2
    centroids, parent = la.orb_tree_flat(path)
    assert len(centroids) == 421, len(centroids)
    assert [len(c) for c in centroids[:3]] == [32, 32, 32]
    self_map = sum(1 for i in range(1, 21)
                   if orb.tree_traverse(centroids[i], centroids,
                                        np.asarray(parent)) == i)
    assert self_map >= 19, self_map
    leaves = [i for i in range(1, len(parent)) if i not in parent[1:]]
    return (f"1 root + 20 level-1 + 400 leaves, 32-byte ORB centroids, "
            f"level-1 self-map {self_map}/20, leaves {len(leaves)}")


def check_triangulation():
    t1 = la.parse_triangulation(la.TRIANGULATION)
    t2 = la.parse_triangulation(la.TRIANGULATION_MOUTH)
    assert len(t1) == 118 and len(t2) == 124, (len(t1), len(t2))
    assert max(max(t) for t in t1) == 75
    return f"{len(t1)} triangles (68-pt), {len(t2)} (with mouth), max index 75"


def _scalar_stage_sums(cascade_dict, ii, win):
    """Straightforward, loop-based evaluation used as an independent oracle."""
    def feat(rects):
        x, y, w, h = win
        s = 0.0
        for rx, ry, rw, rh, wt in rects:
            # OpenCV calc_offsets() rounds each scaled coordinate separately
            x0 = x + int(np.rint(rx * w / 20.0))
            y0 = y + int(np.rint(ry * h / 20.0))
            x1 = x0 + int(np.rint(rw * w / 20.0))
            y1 = y0 + int(np.rint(rh * h / 20.0))
            s += wt * (ii[y0, x0] - ii[y0, x1] - ii[y1, x0] + ii[y1, x1])
        return s / (w * h)

    out = []
    for st in cascade_dict["stages"]:
        acc = 0.0
        for t in st["trees"]:
            o0, n0 = t["feat_off"][0], t["feat_cnt"][0]
            o1, n1 = t["feat_off"][1], t["feat_cnt"][1]
            v0 = feat(t["rects"][o0:o0 + n0])
            v1 = feat(t["rects"][o1:o1 + n1])
            go_left = v0 < t["thresholds"][0]
            node1 = t["lval"][1] if v1 < t["thresholds"][1] else t["rval"][1]
            if t["left"][0] < 0:
                acc += t["lval"][0] if go_left else node1
            else:
                acc += node1 if go_left else t["rval"][0]
        out.append(acc)
    return np.array(out)


def check_cascade_agreement():
    cascade_dict = la.parse_haar()
    cascade = haar.HaarCascade(cascade_dict)
    rng = np.random.default_rng(5)
    frame = rng.integers(0, 255, (72, 72), dtype=np.uint8)
    ii_np = haar.integral_image(frame)
    ii_j = haar.jnp.asarray(ii_np)
    wins = [(4, 6, 24, 24), (10, 10, 30, 30), (2, 2, 20, 20), (20, 30, 40, 40)]
    max_diff = 0.0
    for win in wins:
        jax_scores = np.asarray(haar.stage_scores(cascade, ii_j,
                                                  haar.jnp.asarray(np.array([win], np.int32)), 0))
        ref = _scalar_stage_sums(cascade_dict, ii_np, win)
        max_diff = max(max_diff, abs(float(jax_scores[0]) - float(ref[0])))
    assert max_diff < 1e-4, max_diff
    return (f"JAX vs scalar stage-0 sum on {len(wins)} windows: "
            f"max |diff| = {max_diff:.2e}")


def check_face_detection():
    frame, origin = pipeline.make_detection_frame()
    boxes, info = pipeline.stage_face_detection(frame)
    assert boxes, "no detection"
    bx = min(boxes, key=lambda b: (b["x"] - origin[0]) ** 2 + (b["y"] - origin[1]) ** 2)
    err = max(abs(bx["x"] - origin[0]), abs(bx["y"] - origin[1]))
    assert err <= 2.5, (bx, origin)
    return (f"box ({bx['x']:.1f},{bx['y']:.1f},{bx['w']:.1f}) vs patch "
            f"{origin}, err {err:.1f}px, windows {info['windows']}, "
            f"stage survivors {info['stage_survivors'][:6]}...")


def check_no_false_positives():
    rng = np.random.default_rng(1)
    noise = rng.integers(0, 255, (72, 72), dtype=np.uint8)
    boxes, info = pipeline.stage_face_detection(noise, max_size=24)
    assert not boxes, boxes
    assert info["stage_survivors"][-1] == 0
    return f"random noise: 0 boxes, survivors {info['stage_survivors']}"


def check_orb_tracking():
    frame = pipeline.make_texture_frame()
    shift = (7, -5)
    frame_b = np.roll(np.roll(frame, shift[1], 0), shift[0], 1)
    tr = pipeline.stage_tracking(frame, frame_b)
    est = np.asarray(tr["translation"])
    assert np.allclose(est, [7.0, -5.0], atol=0.5), est
    assert tr["inliers"] > 100 and len(tr["matches"]) > 100
    return (f"{len(tr['keypoints_a'])} FAST-9 keypoints, "
            f"{tr['descriptors_a'].shape[1] * 8}-bit BRIEF, "
            f"{len(tr['matches'])} matches, {tr['inliers']} inliers, "
            f"translation {est} (truth [7,-5])")


def check_liquify_identity():
    img = np.zeros((48, 48), np.float32)
    img[16:32, 16:32] = 1.0
    out = deform.liquify(img, np.array([[0.2, -0.1]]), np.array([0.0]),
                         np.array([0.4]))
    d = float(np.abs(out - img).max())
    assert d < 1e-5, d
    return f"coefficient 0 -> displacement 0, max |diff| = {d:.1e}"


def check_mesh_identity():
    img = np.zeros((64, 64), np.float32)
    img[20:40, 20:40] = 1.0
    pts = deform.synthetic_landmarks(64, 64, 76)
    tris = la.parse_triangulation()
    out = deform.warp_mesh(img, pts, pts, tris)
    d = float(np.abs(out - img).max())
    assert d < 1e-6, d
    return f"identity warp over {len(tris)} triangles, max |diff| = {d:.1e}"


def check_mesh_deformation():
    img = np.zeros((64, 64), np.float32)
    img[20:40, 20:40] = 1.0
    pts = deform.synthetic_landmarks(64, 64, 76)
    tris = la.parse_triangulation()
    delta = np.array([3.0, -2.0])
    out = deform.warp_mesh(img, pts, pts + delta, tris)
    got = np.argwhere(out > 0.5).mean(0) - np.argwhere(img > 0.5).mean(0)
    assert np.allclose(got, (delta[1], delta[0]), atol=0.3), got
    return f"landmark shift {delta.tolist()} -> centroid shift {got.round(2).tolist()}"


def main():
    started = time.strftime("%Y-%m-%d %H:%M:%S")
    check("haar_asset", check_haar_asset)
    check("lzc_container", check_lzc_container)
    check("orb_vocabulary_tree", check_orb_tree)
    check("landmark_triangulation", check_triangulation)
    check("cascade_jax_vs_scalar", check_cascade_agreement)
    check("haar_face_detection", check_face_detection)
    check("haar_no_false_positives", check_no_false_positives)
    check("orb_frame_to_frame_tracking", check_orb_tracking)
    check("liquify_identity", check_liquify_identity)
    check("mesh_warp_identity", check_mesh_identity)
    check("mesh_landmark_deformation", check_mesh_deformation)

    lines = [
        "Snapchat / Looksery CV reimplementation -- verification",
        f"run at        : {started}",
        f"python        : {sys.version.split()[0]} ({platform.platform()})",
        f"numpy         : {np.__version__}",
        "assets (sha256):",
    ]
    for rel in ("looksery/core/HaarCascades/haarcascade_frontalface_alt2.xml",
                "looksery/core/LiteTrackingData_ert.lns",
                "looksery/core/NaturalFeatureTracker/SystemData/"
                "tree-ORB-sharp-b-20-d-2-r-10-numSig-195777265.bin",
                "looksery/core/Landmarks/triangulation.txt"):
        path = os.path.join(MODELS, rel)
        lines.append(f"  {sha256(path)}  {rel}")
    lines.append("")
    for name, ok, detail, secs in RESULTS:
        lines.append(f"[{'PASS' if ok else 'FAIL'}] {name} ({secs:.1f}s)")
        lines.append(f"       {detail}")
    failed = [r for r in RESULTS if not r[1]]
    lines += ["", f"result: {len(RESULTS) - len(failed)}/{len(RESULTS)} checks passed",
              f"exit: {0 if not failed else 1}"]
    with open(os.path.join(HERE, "verify.txt"), "w") as fh:
        fh.write("\n".join(lines) + "\n")
    print("\n".join(lines[-2:]))
    return 0 if not failed else 1


if __name__ == "__main__":
    sys.exit(main())
