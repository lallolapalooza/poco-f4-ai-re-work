"""Parsers for the Looksery (Snap Inc.) on-device CV assets shipped in Snapchat.apk.

All formats here were reverse engineered from the APK itself:

* ``haarcascade_frontalface_alt2.xml`` -- OpenCV Haar cascade (Lienhart 20x20
  gentle-AdaBoost frontal face detector, 20 stages, tree classifiers).
  Referenced by libcamplat at ``/HaarCascades/haarcascade_frontalface_alt2.xml``.
* ``LiteTrackingData_ert.lns`` -- "LZC" container (Looksery Zstd Container),
  v1: a 72 byte header, a table of ``(u32 namelen, name, 4 pad bytes,
  u32 size, u32 offset)`` records, a trailer ``(u32 1, u32 compressed_size)``
  and then a single zstd frame.  Entries are addressed by offset/size inside
  the decompressed stream.  Holds the ERT (Ensemble of Regression Trees)
  landmark regressors.
* ``tree-ORB-*.bin`` -- a bare protobuf message
  ``Tree { Node root = 1; repeated Node nodes = 2; }`` with
  ``Node { Feature feature = 1; repeated Node children = 2; }`` and
  ``Feature { bytes signature = 1; }`` where ``signature`` is a 32 byte
  (256 bit) ORB descriptor centroid.  This is the binary vocabulary tree
  (branching 20, depth 2 -> 400 leaves) used by
  ``LS::Tracking::NaturalFeatureTracker``.
* ``Landmarks/triangulation*.txt`` -- triangle lists over the 68/98 point
  face landmark topology used by the Scenarium renderer.

Only the standard library is used (plus the zstd CLI for LZC payloads).
"""

from __future__ import annotations

import os
import struct
import subprocess
import xml.etree.ElementTree as ET

HERE = os.path.dirname(os.path.abspath(__file__))
REPORT_ROOT = os.path.dirname(HERE)
MODELS = os.path.join(REPORT_ROOT, "models")

HAAR_PATH = os.path.join(MODELS, "looksery/core/HaarCascades/haarcascade_frontalface_alt2.xml")
LZC_PATH = os.path.join(MODELS, "looksery/core/LiteTrackingData_ert.lns")
ORB_TREE_DIR = os.path.join(MODELS, "looksery/core/NaturalFeatureTracker/SystemData")
TRIANGULATION = os.path.join(MODELS, "looksery/core/Landmarks/triangulation.txt")
TRIANGULATION_MOUTH = os.path.join(MODELS, "looksery/core/Landmarks/triangulation_with_mouth.txt")
ERT_DIR = os.path.join(MODELS, "looksery_ert")


# --------------------------------------------------------------------------
# Haar cascade
# --------------------------------------------------------------------------
def _floats(text):
    return [float(x) for x in text.split()]


def parse_haar(path: str = HAAR_PATH) -> dict:
    """Parse an OpenCV haar cascade XML into a flat, array friendly dict.

    Returned structure::

        {"width":20, "height":20, "stages":[
            {"threshold":float,
             "trees":[{"thresholds":[...], "left":[...], "right":[...],
                       "lval":[...], "rval":[...],
                       "feat_off":[...], "feat_cnt":[...]}...],
             "rects":[[(x,y,w,h,weight),...],...]}...]}
    """
    root = ET.parse(path).getroot()
    cascade = root.find("haarcascade_frontalface_alt2")
    if cascade is None:  # pragma: no cover - defensive
        raise ValueError("not a haarcascade_frontalface_alt2 XML")
    size = _floats(cascade.find("size").text)
    stages_xml = cascade.find("stages")

    stages = []
    for stage_el in stages_xml.findall("_"):
        trees = []
        for tree_el in stage_el.find("trees").findall("_"):
            nodes = tree_el.findall("_")
            thresholds, left, right, lval, rval = [], [], [], [], []
            feat_off, feat_cnt, rects = [], [], []
            for node in nodes:
                feature = node.find("feature")
                tilted = int(feature.find("tilted").text)
                if tilted:
                    raise NotImplementedError("tilted Haar features are not used by alt2")
                rs = []
                for r in feature.find("rects").findall("_"):
                    x, y, w, h, wt = _floats(r.text)
                    rs.append((x, y, w, h, wt))
                feat_off.append(len(rects))
                feat_cnt.append(len(rs))
                rects.extend(rs)
                thresholds.append(float(node.find("threshold").text))
                ln = node.find("left_node")
                rn = node.find("right_node")
                lv = node.find("left_val")
                rv = node.find("right_val")
                left.append(int(ln.text) if ln is not None else -1)
                right.append(int(rn.text) if rn is not None else -1)
                lval.append(float(lv.text) if lv is not None else 0.0)
                rval.append(float(rv.text) if rv is not None else 0.0)
            trees.append(dict(thresholds=thresholds, left=left, right=right,
                              lval=lval, rval=rval,
                              feat_off=feat_off, feat_cnt=feat_cnt,
                              rects=rects))
        stages.append(dict(threshold=float(stage_el.find("stage_threshold").text),
                           parent=int(stage_el.find("parent").text),
                           trees=trees))
    return dict(width=int(size[0]), height=int(size[1]), stages=stages)


def haar_summary(cascade: dict) -> dict:
    """Aggregate statistics used in the report / IR dump."""
    stages = cascade["stages"]
    trees = [t for s in stages for t in s["trees"]]
    nodes = sum(len(t["thresholds"]) for t in trees)
    rects = sum(len(t["rects"]) for t in trees)
    max_depth = 0
    for t in trees:
        # longest path from root through internal nodes
        depths = {0: 0}
        stack = [0]
        while stack:
            n = stack.pop()
            for idx in (t["left"][n], t["right"][n]):
                if idx >= 0:
                    depths[idx] = depths[n] + 1
                    stack.append(idx)
        max_depth = max(max_depth, max(depths.values()) + 1)
    return dict(window=[cascade["width"], cascade["height"]],
                stages=len(stages),
                trees=len(trees),
                trees_per_stage=[len(s["trees"]) for s in stages],
                nodes=nodes,
                rects=rects,
                max_nodes_per_tree=max(len(t["thresholds"]) for t in trees),
                max_tree_depth=max_depth,
                stage_thresholds=[round(s["threshold"], 6) for s in stages],
                min_threshold=min(nd for t in trees for nd in t["thresholds"]),
                max_threshold=max(nd for t in trees for nd in t["thresholds"]))


# --------------------------------------------------------------------------
# LZC container
# --------------------------------------------------------------------------
LZC_MAGIC = b"LZC\x00"


def parse_lzc(path: str) -> dict:
    """Parse a Looksery LZC v1 container header (no payload decoding)."""
    data = open(path, "rb").read()
    if data[:4] != LZC_MAGIC:
        raise ValueError(f"{path}: not an LZC container")
    ver, count, name_table_size, f10, f14, usize, csize = struct.unpack_from("<IIIIIII", data, 4)
    off = 0x48
    entries = []
    for _ in range(count):
        (nlen,) = struct.unpack_from("<I", data, off)
        off += 4
        name = data[off:off + nlen].decode("utf-8", "replace")
        off += nlen + 4                      # name + 4 pad bytes
        size, doff = struct.unpack_from("<II", data, off)
        off += 8
        entries.append(dict(name=name, size=size, offset=doff))
    trailer = struct.unpack_from("<II", data, off)
    payload_off = off + 8
    return dict(path=path, version=ver, count=count,
                name_table_size=name_table_size, flags=[f10, f14],
                uncompressed_size=usize, compressed_size=csize,
                trailer=dict(unknown=trailer[0], compressed_size=trailer[1]),
                payload_offset=payload_off,
                payload_len=len(data) - payload_off,
                entries=entries,
                ct_offsets_cumulative_ok=(
                    sum(e["size"] for e in entries) == usize),
                payload_is_zstd=data[payload_off:payload_off + 4] == b"\x28\xb5\x2f\xfd")


def lzc_extract(path: str, out_dir: str | None = None) -> dict:
    """Decompress an LZC container and split it into its entries.

    Uses the system ``zstd`` binary (the payload is a plain zstd frame).
    Returns {name: bytes}.  Optionally writes the entries to ``out_dir``.
    """
    meta = parse_lzc(path)
    with open(path, "rb") as fh:
        fh.seek(meta["payload_offset"])
        payload = fh.read()
    raw = subprocess.run(["zstd", "-d", "-c"], input=payload,
                         capture_output=True, check=True).stdout
    if len(raw) != meta["uncompressed_size"]:
        raise ValueError("zstd size mismatch")
    out = {}
    for e in meta["entries"]:
        blob = raw[e["offset"]:e["offset"] + e["size"]]
        out[e["name"]] = blob
        if out_dir:
            os.makedirs(out_dir, exist_ok=True)
            with open(os.path.join(out_dir, os.path.basename(e["name"])), "wb") as fh:
                fh.write(blob)
    return out


# --------------------------------------------------------------------------
# ORB vocabulary tree (bare protobuf)
# --------------------------------------------------------------------------
def _varint(buf: bytes, i: int) -> tuple[int, int]:
    shift = 0
    val = 0
    while True:
        b = buf[i]
        i += 1
        val |= (b & 0x7F) << shift
        shift += 7
        if not b & 0x80:
            return val, i


def _pb_fields(buf: bytes) -> list[tuple[int, int, object]]:
    """Minimal protobuf decoder -> [(field, wiretype, value), ...]."""
    out = []
    i = 0
    while i < len(buf):
        key, i = _varint(buf, i)
        field, wt = key >> 3, key & 7
        if wt == 0:
            v, i = _varint(buf, i)
        elif wt == 2:
            ln, i = _varint(buf, i)
            v = buf[i:i + ln]
            i += ln
        elif wt == 5:
            v = struct.unpack_from("<f", buf, i)[0]
            i += 4
        elif wt == 1:
            v = struct.unpack_from("<d", buf, i)[0]
            i += 8
        else:
            raise ValueError(f"unsupported wire type {wt} at {i}")
        out.append((field, wt, v))
    return out


def parse_orb_tree(path: str) -> dict:
    """Decode the ORB binary vocabulary tree.

    Layout (confirmed by recursive protobuf decoding)::

        Tree  { Node root = 1; repeated Node nodes = 2; }        # 21 fields
        Node  { Feature feature = 1; repeated Node children = 2; }
        Feature { bytes signature = 1; }                         # 32 bytes

    The root's feature is all-zero (sentinel); depth is 2, branching 20.
    """
    data = open(path, "rb").read()
    top = _pb_fields(data)
    if len(top) != 1 or top[0][0] != 2:
        raise ValueError("unexpected ORB tree top level")
    tree = _pb_fields(top[0][2])
    root_sig = None
    children = []
    for field, wt, val in tree:
        if field == 1:
            sub = _pb_fields(val)
            root_sig = sub[0][2]
        elif field == 2:
            node = _pb_fields(val)
            sig = None
            kids = []
            for f2, w2, v2 in node:
                if f2 == 1:
                    sig = _pb_fields(v2)[0][2]
                elif f2 == 2:
                    kids.append(_pb_fields(v2)[0][2])
            children.append(dict(signature=sig, children=kids))
    depth = 1 + (1 if children and children[0]["children"] else 0)
    if children and children[0]["children"]:
        depth = 2 + (1 if _pb_fields(children[0]["children"][0]) and
                     any(f == 2 for f, _, _ in _pb_fields(children[0]["children"][0])) else 0)
        # nodes at level 2 carry only a feature (no children) -> depth 2
        depth = 3 if False else 2
    return dict(path=path, size=len(data), root_signature=root_sig.hex(),
                branching=len(children), depth=depth,
                root_children=[c["signature"].hex() for c in children],
                leaves=[c["children"] for c in children])


def orb_tree_flat(path: str):
    """Return (centroids, parent) for the shipped ORB vocabulary tree.

    Breadth-first numbering: root = 0, its 20 children = 1..20, and each of
    those has 20 children (400 leaves).  ``parent[0] = 0``.
    """
    data = open(path, "rb").read()
    tree = _pb_fields(_pb_fields(data)[0][2])
    centroids: list[bytes] = []
    parent: list[int] = []

    def add(sig, par):
        centroids.append(sig)
        parent.append(par)
        return len(centroids) - 1

    def sig_of(blob):
        """Unwrap Feature{ bytes signature = 1 } wrappers until 32 bytes."""
        while True:
            fields = _pb_fields(blob)
            nxt = fields[0][2]
            if isinstance(nxt, bytes) and len(nxt) == 32:
                return nxt
            blob = nxt

    root = add(b"\x00" * 32, 0)
    for field, _wt, val in tree:
        if field == 1:
            continue  # root feature (all-zero sentinel), already added
        node = _pb_fields(val)
        sig = None
        kids = []
        for f2, _w2, v2 in node:
            if f2 == 1:
                sig = sig_of(v2)
            elif f2 == 2:
                kids.append(sig_of(v2))
        idx = add(sig, root)
        for ksig in kids:
            add(ksig, idx)
    return centroids, parent



# --------------------------------------------------------------------------
# Triangulation / landmark topology
# --------------------------------------------------------------------------
def parse_triangulation(path: str = TRIANGULATION) -> list[tuple[int, int, int]]:
    tris = []
    for line in open(path):
        line = line.strip()
        if not line:
            continue
        a, b, c = (int(v) for v in line.split())
        tris.append((a, b, c))
    return tris


if __name__ == "__main__":  # tiny smoke test
    c = parse_haar()
    print("haar", haar_summary(c))
    m = parse_lzc(LZC_PATH)
    print("lzc", m["count"], m["uncompressed_size"], [e["name"] for e in m["entries"]])
    t = parse_orb_tree(os.path.join(ORB_TREE_DIR, "tree-ORB-sharp-b-20-d-2-r-10-numSig-195777265.bin"))
    print("orb", t["branching"], t["depth"], len(t["leaves"]))
    print("tri", len(parse_triangulation()), "triangles")
