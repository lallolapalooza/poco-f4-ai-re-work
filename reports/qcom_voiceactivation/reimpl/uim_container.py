"""Decoder for the Qualcomm SML "big sound model" .uim container used by
QualcommVoiceActivation (OneVoice / SVA).

Layout recovered from the artifacts and from the loaders in libqvasml.so:

  offset  size  field
  ------  ----  ------------------------------------------------------------
  0x000     4   magic 0x00180CC8                       (all 7 shipped files)
  0x004     4   payload size = filesize - 12
  0x008     4   container version word: 0x0500 (5.0), 0x0700 (7.0), 0x0800 (8.0)
  0x00c     4   number of individual models inside (2 for 1st-stage files, else 1)
  0x010     4   length of the model name
  0x014     4   reserved (0)
  0x018     N   model name, NUL padded up to 0x1a8

  0x1a8     ..  body: version-specific header + sub-model blobs.  Every
                sub-model is an "eai2" neural-network container whose total
                size is stored at its own offset +0x10.

Loader evidence for the version word / model magic:
  * sml::decodePdkXSHeader (libqvasml.so @0x901d4) compares the first u32 of a
    sub-model against 0x50444b34/35/36 == "PDK4"/"PDK5"/"PDK6" and returns 5 or 6.
  * ListenBigSoundModel::readFrom (@0x63890) accepts version words 0x300,
    0x4xx..0x7xx (mask 0xfffffcff == 0x400) and 0x800, rejects >= 0x5F5E101
    (100 MB) and re-dispatches on the sub-model type.
  * ListenBigSoundModel::readHeaderFrom (@0x63ab0) performs the same checks.

The per-sub-model field layout (thresholds, keyword tables, user models) is
produced by a function pointer dispatched from a jump table in readFrom and has
not been decoded, so only the container and the embedded DNN blobs are parsed.

Usage:  python uim_container.py FILE.uim [...]      (prints a table)
"""

from __future__ import annotations

import os
import struct
import sys

MAGIC = 0x00180CC8
HEADER_LEN = 0x1A8
PDK_MAGIC = {b"PDK4": 4, b"PDK5": 5, b"PDK6": 6}
EAI_MAGIC = b"eai2"


def parse(buf: bytes) -> dict:
    magic, payload, version, count, name_len, resv = struct.unpack_from("<IIIIII", buf, 0)
    name = buf[0x18:0x18 + name_len].decode("latin-1")
    out = {
        "magic": magic,
        "payload_size": payload,
        "size_ok": payload == len(buf) - 12,
        "version": version,
        "version_str": "%d.%d" % (version >> 8, version & 0xFF),
        "model_count": count,
        "name": name,
        "name_len": name_len,
        "header_len": HEADER_LEN,
        "body_offset": HEADER_LEN,
        "submodels": [],
    }
    i = HEADER_LEN
    while True:
        j = buf.find(EAI_MAGIC, i)
        if j < 0:
            break
        if j + 0x14 <= len(buf):
            total = struct.unpack_from("<I", buf, j + 0x10)[0]
            if 0x20 <= total <= len(buf) - j:
                ver16 = struct.unpack_from("<2H", buf, j + 4)
                kind = struct.unpack_from("<2H", buf, j + 8)
                out["submodels"].append({
                    "offset": j,
                    "size": total,
                    "end": j + total,
                    "format": "%d.%d" % ver16,
                    "kind": "%d.%d" % kind,
                })
        i = j + 4
    # PDK header magic carries the 1st-stage algorithm generation.
    pdk = []
    for m, v in PDK_MAGIC.items():
        k = buf.find(m, HEADER_LEN)
        while k >= 0:
            pdk.append({"offset": k, "magic": m.decode(), "generation": v})
            k = buf.find(m, k + 4)
    out["pdk_markers"] = pdk
    return out


def main(argv):
    if not argv:
        print(__doc__)
        return 2
    rc = 0
    for path in argv:
        buf = open(path, "rb").read()
        r = parse(buf)
        print("=" * 78)
        print("%s  %d bytes" % (path, len(buf)))
        print("  magic 0x%08X  payload %d (%s)  version %s   models=%d  name=%r"
              % (r["magic"], r["payload_size"], "ok" if r["size_ok"] else "MISMATCH",
                 r["version_str"], r["model_count"], r["name"]))
        if r["magic"] != MAGIC:
            print("  !! unexpected magic")
            rc = 1
        for s in r["submodels"]:
            print("  sub-model eai2 @0x%06x size %7d end 0x%06x  fmt %s kind %s"
                  % (s["offset"], s["size"], s["end"], s["format"], s["kind"]))
        for p in r["pdk_markers"]:
            print("  PDK marker %s @0x%06x -> generation %d"
                  % (p["magic"], p["offset"], p["generation"]))
    return rc


if __name__ == "__main__":
    sys.exit(main(sys.argv[1:]))
