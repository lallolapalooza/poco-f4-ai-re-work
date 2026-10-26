#!/usr/bin/env python3
"""Find Java/Kotlin methods that invoke a given external method (DEX invoke scan).

Usage:
  dex_callers.py APK.apk 'Lcom/bef/effectsdk/BEFEffectNative;' processFrame

Parses each dex in the APK (no androguard), builds method_ids, then walks every
class_def -> class_data -> code_item and decodes invoke-{virtual,super,direct,
static,interface} (35c and 3rc) to find callers. Prints one JSON object per
caller: {"dex":..., "class":..., "method":..., "descriptor":..., "pc":...}
"""
import json
import struct
import sys
import zipfile

INVOKE_35C = {0x6E, 0x6F, 0x70, 0x71, 0x72}
INVOKE_3RC = {0x74, 0x75, 0x76, 0x77, 0x78}


def uleb(buf, off):
    r = 0
    s = 0
    while True:
        b = buf[off]
        off += 1
        r |= (b & 0x7F) << s
        if b < 0x80:
            return r, off
        s += 7


def mutf8(buf, off):
    n, off = uleb(buf, off)
    end = off + n
    return buf[off:end].decode("utf-8", "replace"), end


class Dex:
    def __init__(self, buf):
        self.b = buf
        (self.str_n, self.str_off, self.type_n, self.type_off, self.proto_n,
         self.proto_off, self.field_n, self.field_off, self.method_n,
         self.method_off, self.class_n, self.class_off) = struct.unpack_from(
            "<12I", buf, 0x38)
        self._str_off = [struct.unpack_from("<I", buf, self.str_off + 4 * i)[0]
                         for i in range(self.str_n)]
        self._type_idx = [struct.unpack_from("<I", buf, self.type_off + 4 * i)[0]
                          for i in range(self.type_n)]

    def s(self, idx):
        return mutf8(self.b, self._str_off[idx])[0]

    def type_name(self, idx):
        return self.s(self._type_idx[idx])

    def method(self, idx):
        cls, proto, name = struct.unpack_from("<HHI", self.b, self.method_off + 8 * idx)
        p_off = self.proto_off + 12 * proto
        ret_idx, par_off = struct.unpack_from("<II", self.b, p_off + 4)
        n = struct.unpack_from("<I", self.b, par_off)[0] if par_off else 0
        params = [self.type_name(struct.unpack_from("<H", self.b, par_off + 4 + 2 * i)[0])
                  for i in range(n)] if par_off else []
        desc = "(" + "".join(params) + ")" + self.type_name(ret_idx)
        return self.type_name(cls), self.s(name), desc


def scan_dex(name, buf, want_cls, want_name, out):
    d = Dex(buf)
    # resolve target method indices
    targets = {}
    for i in range(d.method_n):
        c, nm, desc = d.method(i)
        if c == want_cls and (want_name is None or nm == want_name):
            targets[i] = (nm, desc)
    if not targets:
        return 0
    hits = 0
    for ci in range(d.class_n):
        (class_idx, _acc, _sup, _if, _src, _ann, cdata_off, _sv) = struct.unpack_from(
            "<8I", buf, d.class_off + 32 * ci)
        if cdata_off == 0:
            continue
        off = cdata_off
        sf, off = uleb(buf, off)
        inf, off = uleb(buf, off)
        dm, off = uleb(buf, off)
        vm, off = uleb(buf, off)
        for _ in range(sf + inf):
            _, off = uleb(buf, off)
            _, off = uleb(buf, off)
        for count, _kind in ((dm, "direct"), (vm, "virtual")):
            midx = 0
            for _ in range(count):
                diff, off = uleb(buf, off)
                acc, off = uleb(buf, off)
                code_off, off = uleb(buf, off)
                midx += diff
                if code_off == 0:
                    continue
                insns_size = struct.unpack_from("<I", buf, code_off + 12)[0]
                base = code_off + 16
                for k in range(insns_size):
                    code = struct.unpack_from("<H", buf, base + 2 * k)[0]
                    op = code & 0xFF
                    m = None
                    if op in INVOKE_35C:
                        m = struct.unpack_from("<H", buf, base + 2 * k + 2)[0]
                    elif op in INVOKE_3RC:
                        m = struct.unpack_from("<H", buf, base + 2 * k + 2)[0]
                    if m in targets:
                        cc, nm, desc = d.method(midx)
                        out.append({"dex": name, "caller_class": d.type_name(class_idx),
                                    "caller_method": nm, "caller_desc": desc,
                                    "callee": targets[m][0], "callee_desc": targets[m][1],
                                    "pc": 2 * k})
                        hits += 1
    return hits


def main(argv):
    apk, want_cls = argv[1], argv[2]
    want_name = argv[3] if len(argv) > 3 and argv[3] != "*" else None
    out = []
    with zipfile.ZipFile(apk) as z:
        for n in z.namelist():
            if n.endswith(".dex"):
                try:
                    scan_dex(n, z.read(n), want_cls, want_name, out)
                except Exception as e:  # keep going on odd dex
                    print("ERR", n, e, file=sys.stderr)
    for rec in out:
        print(json.dumps(rec))
    return 0


if __name__ == "__main__":
    sys.exit(main(sys.argv))
