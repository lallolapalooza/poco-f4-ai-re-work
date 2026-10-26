#!/usr/bin/env python3
"""Dump machine-readable IR for the assets parsed out of Snapchat.apk."""

from __future__ import annotations

import json
import os
import struct

import looksery_assets as la

HERE = os.path.dirname(os.path.abspath(__file__))
IR = os.path.join(os.path.dirname(HERE), "ir")
os.makedirs(IR, exist_ok=True)


def dump(name, obj):
    path = os.path.join(IR, name)
    with open(path, "w") as fh:
        json.dump(obj, fh, indent=1)
    print(path)


def main():
    cascade = la.parse_haar()
    summary = la.haar_summary(cascade)
    summary["stage0"] = [
        dict(threshold=t["thresholds"],
             left_node=t["left"], right_node=t["right"],
             left_val=t["lval"], right_val=t["rval"],
             features=[t["rects"][o:o + n] for o, n in zip(t["feat_off"], t["feat_cnt"])])
        for t in cascade["stages"][0]["trees"]
    ]
    summary["source"] = "assets/looksery/core/HaarCascades/haarcascade_frontalface_alt2.xml"
    summary["format"] = "OpenCV haar cascade XML (Lienhart 20x20 gentle AdaBoost)"
    dump("haar_cascade.json", summary)

    lzc = la.parse_lzc(la.LZC_PATH)
    lzc["source"] = "assets/looksery/core/LiteTrackingData_ert.lns"
    lzc["container"] = dict(magic="LZC\\0", header_bytes=72,
                            record="u32 namelen, name, 4 pad, u32 size, u32 offset",
                            trailer="u32 unknown(=1), u32 compressed_size",
                            payload="single zstd frame")
    dump("lzc_container.json", lzc)

    orb_path = os.path.join(la.ORB_TREE_DIR,
                           "tree-ORB-sharp-b-20-d-2-r-10-numSig-195777265.bin")
    info = la.parse_orb_tree(orb_path)
    centroids, parent = la.orb_tree_flat(orb_path)
    info.pop("leaves", None)
    info.update(source="assets/looksery/core/NaturalFeatureTracker/SystemData/",
                protobuf="Tree{Node root=1; repeated Node nodes=2;} "
                         "Node{Feature feature=1; repeated Node children=2;} "
                         "Feature{bytes signature=1;}",
                node_count=len(centroids),
                level_sizes={"root": 1, "level1": 20, "leaves": 400},
                centroid_bytes=32,
                level1_centroids=[c.hex() for c in centroids[1:21]])
    dump("orb_tree.json", info)

    tris = la.parse_triangulation(la.TRIANGULATION)
    dump("triangulation.json", dict(source="assets/looksery/core/Landmarks/triangulation.txt",
                                    triangles=len(tris), triples=tris,
                                    mouth_variant=len(la.parse_triangulation(la.TRIANGULATION_MOUTH))))

    # Candide-3 payload characterisation (not decoded; see report)
    cand = {}
    for name in ("candide3reduce", "candide3reduce14AU"):
        p = os.path.join(la.MODELS, "looksery/core/Candide", name)
        d = open(p, "rb").read()
        import collections, math
        cnt = collections.Counter(d)
        h = -sum(v / len(d) * math.log2(v / len(d)) for v in cnt.values())
        cand[name] = dict(size=len(d), entropy=round(h, 2),
                          common_prefix_with_other=None,
                          head=d[:16].hex(), tail=d[-16:].hex())
    a = open(os.path.join(la.MODELS, "looksery/core/Candide/candide3reduce"), "rb").read()
    b = open(os.path.join(la.MODELS, "looksery/core/Candide/candide3reduce14AU"), "rb").read()
    n = 0
    while n < min(len(a), len(b)) and a[n] == b[n]:
        n += 1
    for k in cand:
        cand[k]["common_prefix_with_other"] = n
    cand["interpretation"] = ("high-entropy payloads sharing a 5612-byte prefix; "
                              "no recognisable float/int structure -> encrypted or "
                              "obfuscated Candide-3 shape/action-unit model (inferred)")
    dump("candide_model.json", cand)

    ert = {}
    for name in os.listdir(la.ERT_DIR):
        if not name.endswith(".bin"):
            continue
        d = open(os.path.join(la.ERT_DIR, name), "rb").read()
        ert[name] = dict(size=len(d), magic=d[:1].hex(),
                         head=d[:16].hex(),
                         note='high entropy; tag byte 0x53; double constants present')
    dump("ert_regressors.json", dict(source="LiteTrackingData_ert.lns (zstd entries)",
                                     format="proprietary packed ERT regressor, first byte 0x53 ('S'), "
                                            "mixed int32/IEEE-754 doubles, no compression",
                                     decoded=False, files=ert))


if __name__ == "__main__":
    main()
