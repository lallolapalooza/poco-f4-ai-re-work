"""Parser for the "soil-binn" container used by Xiaomi's Soil/CornVad engine.

Recovered empirically from libmibrainvad2.so's embedded `mdata_vad` blob and
cross-checked against the binn_* accessor calls in CornLoadModelBin.

Layout (all container sizes are *inclusive* of the header):
    0x20|0x21  int8 / int8(signed)
    0x40|0x41  int16 / int16(signed)      big-endian
    0x60|0x61  int32                      big-endian
    0x80|0x81  int64                      big-endian
    0xA0       string: [0xA0][len:u8][chars][NUL]
    0xC0       blob:   [0xC0][0x00][len:u24be] payload
    0xE0       list:   [0xE0][size][count:u8] then `count` values
    0xE2       object: [0xE2][size][count:u8] then `count` * ([klen:u8][key][value])
    size encoding: if the byte after the type byte is 0x80, a 3-byte
    big-endian size follows; otherwise that byte *is* the size (<= 0x7F).
"""


class SoilBinn:
    def __init__(self, data: bytes):
        self.d = data
        self.pos = 0

    def u8(self):
        v = self.d[self.pos]
        self.pos += 1
        return v

    def take(self, n):
        v = self.d[self.pos:self.pos + n]
        self.pos += n
        return v

    def parse(self):
        off = self.pos
        t = self.u8()
        if t in (0xE0, 0xE2):
            b = self.d[self.pos]
            if b == 0x80:
                self.pos += 1
                size = int.from_bytes(self.take(3), "big")
            else:
                size = b
                self.pos += 1
            count = self.u8()
            end = off + size
            if t == 0xE2:
                items = {}
                for _ in range(count):
                    klen = self.u8()
                    key = self.take(klen).decode("utf-8", "replace")
                    items[key] = self.parse()
            else:
                items = [self.parse() for _ in range(count)]
            self.pos = end
            return ("obj" if t == 0xE2 else "list", items)
        if t == 0xA0:
            n = self.u8()
            s = self.take(n).decode("utf-8", "replace")
            self.pos += 1  # NUL
            return s
        if t == 0xC0:
            flag = self.u8()
            if flag == 0x00:
                n = int.from_bytes(self.take(3), "big")
            else:
                self.pos -= 1
                n = int.from_bytes(self.take(4), "big")
            return ("blob", off + 5, n)  # (kind, payload_offset, payload_len)
        width = {0x20: 1, 0x40: 2, 0x60: 4, 0x80: 8}.get(t & 0xE0)
        if width:
            return int.from_bytes(self.take(width), "big", signed=bool(t & 1))
        raise ValueError("unknown soil-binn type 0x%02x at %d" % (t, off))

    def parse_at(self, pos):
        self.pos = pos
        return self.parse()


def parse_file(path):
    data = open(path, "rb").read()
    return SoilBinn(data).parse(), data
