; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_musically/native/libbytenn.so
; target  : _ZN6BYTENN20EspressoDisassembler22disassembleModelBufferERNS_23ByteNNInternalConfigExtE (0x42af0)
; start   : 0x42af0
; size    : 0x928
; bytes   : 0x928   instructions: 586
; generated: 2026-10-23T23:27:23

0x00042af0: ff 03 03 d1              sub      sp, sp, #0xc0
0x00042af4: fc 6f 06 a9              stp      x28, x27, [sp, #0x60]
0x00042af8: fa 67 07 a9              stp      x26, x25, [sp, #0x70]
0x00042afc: f8 5f 08 a9              stp      x24, x23, [sp, #0x80]
0x00042b00: f6 57 09 a9              stp      x22, x21, [sp, #0x90]
0x00042b04: f4 4f 0a a9              stp      x20, x19, [sp, #0xa0]
0x00042b08: fd 7b 0b a9              stp      x29, x30, [sp, #0xb0]
0x00042b0c: fd c3 02 91              add      x29, sp, #0xb0
0x00042b10: 59 d0 3b d5              mrs      x25, tpidr_el0
0x00042b14: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x00042b18: e8 2f 00 f9              str      x8, [sp, #0x58]
0x00042b1c: 16 08 40 f9              ldr      x22, [x0, #0x10]
0x00042b20: 04 18 40 b9              ldr      w4, [x0, #0x18]
0x00042b24: c3 06 40 b9              ldr      w3, [x22, #4]
0x00042b28: 7f 00 04 6b              cmp      w3, w4
0x00042b2c: 81 04 00 54              b.ne     #0x42bbc
0x00042b30: 08 70 40 39              ldrb     w8, [x0, #0x1c]
0x00042b34: d4 0e 40 39              ldrb     w20, [x22, #3]
0x00042b38: f3 03 00 aa              mov      x19, x0
0x00042b3c: c8 00 00 34              cbz      w8, #0x42b54
0x00042b40: e0 03 16 aa              mov      x0, x22
0x00042b44: e1 03 14 2a              mov      w1, w20
0x00042b48: 76 b3 ff 97              bl       #0x2f920                   ; -> _ZN6BYTENN11CheckMd5sumEPKhi@plt
0x00042b4c: f5 03 00 2a              mov      w21, w0
0x00042b50: 40 04 00 35              cbnz     w0, #0x42bd8
0x00042b54: c8 02 40 39              ldrb     w8, [x22]
0x00042b58: 1f 09 01 71              cmp      w8, #0x42
0x00042b5c: c1 03 00 54              b.ne     #0x42bd4
0x00042b60: c8 06 40 39              ldrb     w8, [x22, #1]
0x00042b64: 1f 35 01 71              cmp      w8, #0x4d
0x00042b68: 61 03 00 54              b.ne     #0x42bd4
0x00042b6c: 7a 0a 40 f9              ldr      x26, [x19, #0x10]
0x00042b70: 9f 06 00 71              cmp      w20, #1
0x00042b74: c1 04 00 54              b.ne     #0x42c0c
0x00042b78: 48 13 40 b9              ldr      w8, [x26, #0x10]
0x00042b7c: ff 7f 04 a9              stp      xzr, xzr, [sp, #0x40]
0x00042b80: ff 1f 00 f9              str      xzr, [sp, #0x38]
0x00042b84: 55 03 08 8b              add      x21, x26, x8
0x00042b88: e0 03 15 aa              mov      x0, x21
0x00042b8c: 11 b5 ff 97              bl       #0x2ffd0                   ; -> strlen@plt
0x00042b90: 1f 40 00 b1              cmn      x0, #0x10
0x00042b94: 02 44 00 54              b.hs     #0x43414
0x00042b98: f4 03 00 aa              mov      x20, x0
0x00042b9c: 1f 5c 00 f1              cmp      x0, #0x17
0x00042ba0: c2 08 00 54              b.hs     #0x42cb8
0x00042ba4: e9 e3 00 91              add      x9, sp, #0x38
0x00042ba8: 88 7a 1f 53              lsl      w8, w20, #1
0x00042bac: 36 01 40 b2              orr      x22, x9, #1
0x00042bb0: e8 e3 00 39              strb     w8, [sp, #0x38]
0x00042bb4: 34 09 00 b5              cbnz     x20, #0x42cd8
0x00042bb8: 4c 00 00 14              b        #0x42ce8                   ; -> _ZN6BYTENN20EspressoDisassembler22disassembleModelBufferERNS_23ByteNNInternalConfigExtE+0x1f8
0x00042bbc: 01 0a 00 b0              adrp     x1, #0x183000
0x00042bc0: 02 0a 00 d0              adrp     x2, #0x184000
0x00042bc4: 21 e0 21 91              add      x1, x1, #0x878
0x00042bc8: 42 38 2c 91              add      x2, x2, #0xb0e
0x00042bcc: 20 00 80 52              mov      w0, #1
0x00042bd0: 84 b9 ff 97              bl       #0x311e0                   ; -> _ZN6BYTENN9ByteNNLogEiPKcS1_z@plt
0x00042bd4: 55 01 80 52              mov      w21, #0xa
0x00042bd8: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x00042bdc: e9 2f 40 f9              ldr      x9, [sp, #0x58]
0x00042be0: 1f 01 09 eb              cmp      x8, x9
0x00042be4: 61 41 00 54              b.ne     #0x43410
0x00042be8: e0 03 15 2a              mov      w0, w21
0x00042bec: fd 7b 4b a9              ldp      x29, x30, [sp, #0xb0]
0x00042bf0: f4 4f 4a a9              ldp      x20, x19, [sp, #0xa0]
0x00042bf4: f6 57 49 a9              ldp      x22, x21, [sp, #0x90]
0x00042bf8: f8 5f 48 a9              ldp      x24, x23, [sp, #0x80]
0x00042bfc: fa 67 47 a9              ldp      x26, x25, [sp, #0x70]
0x00042c00: fc 6f 46 a9              ldp      x28, x27, [sp, #0x60]
0x00042c04: ff 03 03 91              add      sp, sp, #0xc0
0x00042c08: c0 03 5f d6              ret      
0x00042c0c: c8 0a 40 b9              ldr      w8, [x22, #8]
0x00042c10: 49 93 00 91              add      x9, x26, #0x24
0x00042c14: 4a b3 00 91              add      x10, x26, #0x2c
0x00042c18: 4b d3 00 91              add      x11, x26, #0x34
0x00042c1c: 1f 0d 00 71              cmp      w8, #3
0x00042c20: 29 81 9f 9a              csel     x9, x9, xzr, hi
0x00042c24: 1f 11 00 71              cmp      w8, #4
0x00042c28: 56 81 9f 9a              csel     x22, x10, xzr, hi
0x00042c2c: 1f 15 00 71              cmp      w8, #5
0x00042c30: 78 81 9f 9a              csel     x24, x11, xzr, hi
0x00042c34: 9f 0a 00 71              cmp      w20, #2
0x00042c38: f6 27 01 a9              stp      x22, x9, [sp, #0x10]
0x00042c3c: a0 00 00 54              b.eq     #0x42c50
0x00042c40: 9f 0e 00 71              cmp      w20, #3
0x00042c44: 03 09 00 54              b.lo     #0x42d64
0x00042c48: 48 1f 40 b9              ldr      w8, [x26, #0x1c]
0x00042c4c: c8 08 00 34              cbz      w8, #0x42d64
0x00042c50: 40 a3 41 29              ldp      w0, w8, [x26, #0xc]
0x00042c54: 55 03 08 8b              add      x21, x26, x8
0x00042c58: 1a b4 ff 97              bl       #0x2fcc0                   ; -> malloc@plt
0x00042c5c: 42 0f 40 b9              ldr      w2, [x26, #0xc]
0x00042c60: e1 03 15 aa              mov      x1, x21
0x00042c64: f4 03 00 aa              mov      x20, x0
0x00042c68: 8e b2 ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00042c6c: 48 0f 40 b9              ldr      w8, [x26, #0xc]
0x00042c70: e8 19 00 34              cbz      w8, #0x42fac
0x00042c74: 4b 23 40 b9              ldr      w11, [x26, #0x20]
0x00042c78: ea 03 1f aa              mov      x10, xzr
0x00042c7c: 1f 81 00 71              cmp      w8, #0x20
0x00042c80: 49 03 0b 8b              add      x9, x26, x11
0x00042c84: a3 14 00 54              b.lo     #0x42f18
0x00042c88: 0c 05 00 d1              sub      x12, x8, #1
0x00042c8c: 9f 1d 00 f1              cmp      x12, #7
0x00042c90: 48 14 00 54              b.hi     #0x42f18
0x00042c94: 0a 01 0b 8b              add      x10, x8, x11
0x00042c98: 4a 03 0a 8b              add      x10, x26, x10
0x00042c9c: 9f 02 0a eb              cmp      x20, x10
0x00042ca0: 22 12 00 54              b.hs     #0x42ee4
0x00042ca4: 8a 02 08 8b              add      x10, x20, x8
0x00042ca8: 3f 01 0a eb              cmp      x9, x10
0x00042cac: c2 11 00 54              b.hs     #0x42ee4
0x00042cb0: ea 03 1f aa              mov      x10, xzr
0x00042cb4: 99 00 00 14              b        #0x42f18                   ; -> _ZN6BYTENN20EspressoDisassembler22disassembleModelBufferERNS_23ByteNNInternalConfigExtE+0x428
0x00042cb8: 88 42 00 91              add      x8, x20, #0x10
0x00042cbc: 17 ed 7c 92              and      x23, x8, #0xfffffffffffffff0
0x00042cc0: e0 03 17 aa              mov      x0, x23
0x00042cc4: e3 b8 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00042cc8: e8 02 40 b2              orr      x8, x23, #1
0x00042ccc: f6 03 00 aa              mov      x22, x0
0x00042cd0: e8 d3 03 a9              stp      x8, x20, [sp, #0x38]
0x00042cd4: e0 27 00 f9              str      x0, [sp, #0x48]
0x00042cd8: e0 03 16 aa              mov      x0, x22
0x00042cdc: e1 03 15 aa              mov      x1, x21
0x00042ce0: e2 03 14 aa              mov      x2, x20
0x00042ce4: 6f b2 ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00042ce8: 75 62 06 91              add      x21, x19, #0x198
0x00042cec: e1 43 01 91              add      x1, sp, #0x50
0x00042cf0: e2 83 00 91              add      x2, sp, #0x20
0x00042cf4: e0 03 15 aa              mov      x0, x21
0x00042cf8: df 6a 34 38              strb     wzr, [x22, x20]
0x00042cfc: ff ff 02 a9              stp      xzr, xzr, [sp, #0x28]
0x00042d00: ff 13 00 f9              str      xzr, [sp, #0x20]
0x00042d04: e6 d4 ff 97              bl       #0x3809c
0x00042d08: 14 00 40 f9              ldr      x20, [x0]
0x00042d0c: 14 0b 00 b5              cbnz     x20, #0x42e6c
0x00042d10: f6 03 00 aa              mov      x22, x0
0x00042d14: 00 0a 80 52              mov      w0, #0x50
0x00042d18: ce b8 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00042d1c: e8 1b 40 f9              ldr      x8, [sp, #0x30]
0x00042d20: e0 0b c0 3d              ldr      q0, [sp, #0x20]
0x00042d24: e9 2b 40 f9              ldr      x9, [sp, #0x50]
0x00042d28: ff ff 02 a9              stp      xzr, xzr, [sp, #0x28]
0x00042d2c: ff 13 00 f9              str      xzr, [sp, #0x20]
0x00042d30: 1f 7c 04 a9              stp      xzr, xzr, [x0, #0x40]
0x00042d34: 1f 7c 00 a9              stp      xzr, xzr, [x0]
0x00042d38: 00 08 80 3d              str      q0, [x0, #0x20]
0x00042d3c: 08 7c 03 a9              stp      x8, xzr, [x0, #0x30]
0x00042d40: 09 08 00 f9              str      x9, [x0, #0x10]
0x00042d44: c0 02 00 f9              str      x0, [x22]
0x00042d48: a8 02 40 f9              ldr      x8, [x21]
0x00042d4c: f4 03 00 aa              mov      x20, x0
0x00042d50: 08 01 40 f9              ldr      x8, [x8]
0x00042d54: 08 08 00 b4              cbz      x8, #0x42e54
0x00042d58: a8 02 00 f9              str      x8, [x21]
0x00042d5c: c1 02 40 f9              ldr      x1, [x22]
0x00042d60: 3e 00 00 14              b        #0x42e58                   ; -> _ZN6BYTENN20EspressoDisassembler22disassembleModelBufferERNS_23ByteNNInternalConfigExtE+0x368
0x00042d64: 48 13 40 b9              ldr      w8, [x26, #0x10]
0x00042d68: ff 7f 04 a9              stp      xzr, xzr, [sp, #0x40]
0x00042d6c: ff 1f 00 f9              str      xzr, [sp, #0x38]
0x00042d70: 55 03 08 8b              add      x21, x26, x8
0x00042d74: e0 03 15 aa              mov      x0, x21
0x00042d78: 96 b4 ff 97              bl       #0x2ffd0                   ; -> strlen@plt
0x00042d7c: 1f 40 00 b1              cmn      x0, #0x10
0x00042d80: a2 34 00 54              b.hs     #0x43414
0x00042d84: f4 03 00 aa              mov      x20, x0
0x00042d88: 1f 5c 00 f1              cmp      x0, #0x17
0x00042d8c: e2 00 00 54              b.hs     #0x42da8
0x00042d90: e9 e3 00 91              add      x9, sp, #0x38
0x00042d94: 88 7a 1f 53              lsl      w8, w20, #1
0x00042d98: 36 01 40 b2              orr      x22, x9, #1
0x00042d9c: e8 e3 00 39              strb     w8, [sp, #0x38]
0x00042da0: 54 01 00 b5              cbnz     x20, #0x42dc8
0x00042da4: 0d 00 00 14              b        #0x42dd8                   ; -> _ZN6BYTENN20EspressoDisassembler22disassembleModelBufferERNS_23ByteNNInternalConfigExtE+0x2e8
0x00042da8: 88 42 00 91              add      x8, x20, #0x10
0x00042dac: 17 ed 7c 92              and      x23, x8, #0xfffffffffffffff0
0x00042db0: e0 03 17 aa              mov      x0, x23
0x00042db4: a7 b8 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00042db8: e8 02 40 b2              orr      x8, x23, #1
0x00042dbc: f6 03 00 aa              mov      x22, x0
0x00042dc0: e8 d3 03 a9              stp      x8, x20, [sp, #0x38]
0x00042dc4: e0 27 00 f9              str      x0, [sp, #0x48]
0x00042dc8: e0 03 16 aa              mov      x0, x22
0x00042dcc: e1 03 15 aa              mov      x1, x21
0x00042dd0: e2 03 14 aa              mov      x2, x20
0x00042dd4: 33 b2 ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00042dd8: 75 62 06 91              add      x21, x19, #0x198
0x00042ddc: e1 43 01 91              add      x1, sp, #0x50
0x00042de0: e2 83 00 91              add      x2, sp, #0x20
0x00042de4: e0 03 15 aa              mov      x0, x21
0x00042de8: df 6a 34 38              strb     wzr, [x22, x20]
0x00042dec: ff ff 02 a9              stp      xzr, xzr, [sp, #0x28]
0x00042df0: ff 13 00 f9              str      xzr, [sp, #0x20]
0x00042df4: aa d4 ff 97              bl       #0x3809c
0x00042df8: 14 00 40 f9              ldr      x20, [x0]
0x00042dfc: 14 1a 00 b5              cbnz     x20, #0x4313c
0x00042e00: f6 03 00 aa              mov      x22, x0
0x00042e04: 00 0a 80 52              mov      w0, #0x50
0x00042e08: 92 b8 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00042e0c: e8 1b 40 f9              ldr      x8, [sp, #0x30]
0x00042e10: e0 0b c0 3d              ldr      q0, [sp, #0x20]
0x00042e14: e9 2b 40 f9              ldr      x9, [sp, #0x50]
0x00042e18: ff ff 02 a9              stp      xzr, xzr, [sp, #0x28]
0x00042e1c: ff 13 00 f9              str      xzr, [sp, #0x20]
0x00042e20: 1f 7c 04 a9              stp      xzr, xzr, [x0, #0x40]
0x00042e24: 1f 7c 00 a9              stp      xzr, xzr, [x0]
0x00042e28: 00 08 80 3d              str      q0, [x0, #0x20]
0x00042e2c: 08 7c 03 a9              stp      x8, xzr, [x0, #0x30]
0x00042e30: 09 08 00 f9              str      x9, [x0, #0x10]
0x00042e34: c0 02 00 f9              str      x0, [x22]
0x00042e38: a8 02 40 f9              ldr      x8, [x21]
0x00042e3c: f4 03 00 aa              mov      x20, x0
0x00042e40: 08 01 40 f9              ldr      x8, [x8]
0x00042e44: 08 17 00 b4              cbz      x8, #0x43124
0x00042e48: a8 02 00 f9              str      x8, [x21]
0x00042e4c: c1 02 40 f9              ldr      x1, [x22]
0x00042e50: b6 00 00 14              b        #0x43128                   ; -> _ZN6BYTENN20EspressoDisassembler22disassembleModelBufferERNS_23ByteNNInternalConfigExtE+0x638
0x00042e54: e1 03 14 aa              mov      x1, x20
0x00042e58: 60 d2 40 f9              ldr      x0, [x19, #0x1a0]
0x00042e5c: 1b c9 ff 97              bl       #0x352c8
0x00042e60: 68 d6 40 f9              ldr      x8, [x19, #0x1a8]
0x00042e64: 08 05 00 91              add      x8, x8, #1
0x00042e68: 68 d6 00 f9              str      x8, [x19, #0x1a8]
0x00042e6c: f5 03 14 aa              mov      x21, x20
0x00042e70: a8 8e 43 38              ldrb     w8, [x21, #0x38]!
0x00042e74: 68 00 00 37              tbnz     w8, #0, #0x42e80
0x00042e78: bf 02 00 79              strh     wzr, [x21]
0x00042e7c: 09 00 00 14              b        #0x42ea0                   ; -> _ZN6BYTENN20EspressoDisassembler22disassembleModelBufferERNS_23ByteNNInternalConfigExtE+0x3b0
0x00042e80: 88 26 40 f9              ldr      x8, [x20, #0x48]
0x00042e84: 1f 01 00 39              strb     wzr, [x8]
0x00042e88: 88 e2 40 39              ldrb     w8, [x20, #0x38]
0x00042e8c: 9f 22 00 f9              str      xzr, [x20, #0x40]
0x00042e90: 88 00 00 36              tbz      w8, #0, #0x42ea0
0x00042e94: 80 26 40 f9              ldr      x0, [x20, #0x48]
0x00042e98: 82 b2 ff 97              bl       #0x2f8a0                   ; -> _ZdlPv@plt
0x00042e9c: 9f 1e 00 f9              str      xzr, [x20, #0x38]
0x00042ea0: e8 27 40 f9              ldr      x8, [sp, #0x48]
0x00042ea4: e0 83 c3 3c              ldur     q0, [sp, #0x38]
0x00042ea8: a8 0a 00 f9              str      x8, [x21, #0x10]
0x00042eac: a0 02 80 3d              str      q0, [x21]
0x00042eb0: e8 83 40 39              ldrb     w8, [sp, #0x20]
0x00042eb4: ff 7f 04 a9              stp      xzr, xzr, [sp, #0x40]
0x00042eb8: ff 1f 00 f9              str      xzr, [sp, #0x38]
0x00042ebc: e8 00 00 36              tbz      w8, #0, #0x42ed8
0x00042ec0: e0 1b 40 f9              ldr      x0, [sp, #0x30]
0x00042ec4: 77 b2 ff 97              bl       #0x2f8a0                   ; -> _ZdlPv@plt
0x00042ec8: e8 e3 40 39              ldrb     w8, [sp, #0x38]
0x00042ecc: 68 00 00 36              tbz      w8, #0, #0x42ed8
0x00042ed0: e0 27 40 f9              ldr      x0, [sp, #0x48]
0x00042ed4: 73 b2 ff 97              bl       #0x2f8a0                   ; -> _ZdlPv@plt
0x00042ed8: f8 03 1f aa              mov      x24, xzr
0x00042edc: ff 7f 01 a9              stp      xzr, xzr, [sp, #0x10]
0x00042ee0: b2 00 00 14              b        #0x431a8                   ; -> _ZN6BYTENN20EspressoDisassembler22disassembleModelBufferERNS_23ByteNNInternalConfigExtE+0x6b8
0x00042ee4: 20 05 40 ad              ldp      q0, q1, [x9]
0x00042ee8: 0a 69 7b 92              and      x10, x8, #0xffffffe0
0x00042eec: 8b 42 00 91              add      x11, x20, #0x10
0x00042ef0: ec 03 0a aa              mov      x12, x10
0x00042ef4: 62 8d 7f ad              ldp      q2, q3, [x11, #-0x10]
0x00042ef8: 8c 81 00 f1              subs     x12, x12, #0x20
0x00042efc: 02 1c 22 6e              eor      v2.16b, v0.16b, v2.16b
0x00042f00: 23 1c 23 6e              eor      v3.16b, v1.16b, v3.16b
0x00042f04: 62 8d 3f ad              stp      q2, q3, [x11, #-0x10]
0x00042f08: 6b 81 00 91              add      x11, x11, #0x20
0x00042f0c: 41 ff ff 54              b.ne     #0x42ef4
0x00042f10: 5f 01 08 eb              cmp      x10, x8
0x00042f14: 20 01 00 54              b.eq     #0x42f38
0x00042f18: 4c 09 40 92              and      x12, x10, #7
0x00042f1c: 8b 6a 6a 38              ldrb     w11, [x20, x10]
0x00042f20: 2c 69 6c 38              ldrb     w12, [x9, x12]
0x00042f24: 8b 01 0b 4a              eor      w11, w12, w11
0x00042f28: 8b 6a 2a 38              strb     w11, [x20, x10]
0x00042f2c: 4a 05 00 91              add      x10, x10, #1
0x00042f30: 1f 01 0a eb              cmp      x8, x10
0x00042f34: 21 ff ff 54              b.ne     #0x42f18
0x00042f38: 49 0f 40 b9              ldr      w9, [x26, #0xc]
0x00042f3c: 3f 05 00 71              cmp      w9, #1
0x00042f40: 6b 03 00 54              b.lt     #0x42fac
0x00042f44: 08 0a 00 d0              adrp     x8, #0x184000
0x00042f48: 3f 05 00 71              cmp      w9, #1
0x00042f4c: 08 c9 2c 91              add      x8, x8, #0xb32
0x00042f50: 61 00 00 54              b.ne     #0x42f5c
0x00042f54: ea 03 1f aa              mov      x10, xzr
0x00042f58: 0e 00 00 14              b        #0x42f90                   ; -> _ZN6BYTENN20EspressoDisassembler22disassembleModelBufferERNS_23ByteNNInternalConfigExtE+0x4a0
0x00042f5c: 2a 79 7f 92              and      x10, x9, #0xfffffffe
0x00042f60: 8b 06 00 91              add      x11, x20, #1
0x00042f64: ec 03 0a aa              mov      x12, x10
0x00042f68: 6d f1 5f 38              ldurb    w13, [x11, #-1]
0x00042f6c: 6e 01 40 39              ldrb     w14, [x11]
0x00042f70: 8c 09 00 f1              subs     x12, x12, #2
0x00042f74: 0d 69 6d 38              ldrb     w13, [x8, x13]
0x00042f78: 0e 69 6e 38              ldrb     w14, [x8, x14]
0x00042f7c: 6d f1 1f 38              sturb    w13, [x11, #-1]
0x00042f80: 6e 25 00 38              strb     w14, [x11], #2
0x00042f84: 21 ff ff 54              b.ne     #0x42f68
0x00042f88: 5f 01 09 eb              cmp      x10, x9
0x00042f8c: 00 01 00 54              b.eq     #0x42fac
0x00042f90: 29 01 0a cb              sub      x9, x9, x10
0x00042f94: 8a 02 0a 8b              add      x10, x20, x10
0x00042f98: 4b 01 40 39              ldrb     w11, [x10]
0x00042f9c: 29 05 00 f1              subs     x9, x9, #1
0x00042fa0: 0b 69 6b 38              ldrb     w11, [x8, x11]
0x00042fa4: 4b 15 00 38              strb     w11, [x10], #1
0x00042fa8: 81 ff ff 54              b.ne     #0x42f98
0x00042fac: e0 03 14 aa              mov      x0, x20
0x00042fb0: ff 7f 04 a9              stp      xzr, xzr, [sp, #0x40]
0x00042fb4: ff 1f 00 f9              str      xzr, [sp, #0x38]
0x00042fb8: 06 b4 ff 97              bl       #0x2ffd0                   ; -> strlen@plt
0x00042fbc: 1f 40 00 b1              cmn      x0, #0x10
0x00042fc0: a2 22 00 54              b.hs     #0x43414
0x00042fc4: f5 03 00 aa              mov      x21, x0
0x00042fc8: 1f 5c 00 f1              cmp      x0, #0x17
0x00042fcc: e2 00 00 54              b.hs     #0x42fe8
0x00042fd0: e9 e3 00 91              add      x9, sp, #0x38
0x00042fd4: a8 7a 1f 53              lsl      w8, w21, #1
0x00042fd8: 36 01 40 b2              orr      x22, x9, #1
0x00042fdc: e8 e3 00 39              strb     w8, [sp, #0x38]
0x00042fe0: 55 01 00 b5              cbnz     x21, #0x43008
0x00042fe4: 0d 00 00 14              b        #0x43018                   ; -> _ZN6BYTENN20EspressoDisassembler22disassembleModelBufferERNS_23ByteNNInternalConfigExtE+0x528
0x00042fe8: a8 42 00 91              add      x8, x21, #0x10
0x00042fec: 17 ed 7c 92              and      x23, x8, #0xfffffffffffffff0
0x00042ff0: e0 03 17 aa              mov      x0, x23
0x00042ff4: 17 b8 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00042ff8: e8 02 40 b2              orr      x8, x23, #1
0x00042ffc: f6 03 00 aa              mov      x22, x0
0x00043000: e8 d7 03 a9              stp      x8, x21, [sp, #0x38]
0x00043004: e0 27 00 f9              str      x0, [sp, #0x48]
0x00043008: e0 03 16 aa              mov      x0, x22
0x0004300c: e1 03 14 aa              mov      x1, x20
0x00043010: e2 03 15 aa              mov      x2, x21
0x00043014: a3 b1 ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00043018: df 6a 35 38              strb     wzr, [x22, x21]
0x0004301c: 76 62 06 91              add      x22, x19, #0x198
0x00043020: e1 43 01 91              add      x1, sp, #0x50
0x00043024: e2 83 00 91              add      x2, sp, #0x20
0x00043028: e0 03 16 aa              mov      x0, x22
0x0004302c: ff ff 02 a9              stp      xzr, xzr, [sp, #0x28]
0x00043030: ff 13 00 f9              str      xzr, [sp, #0x20]
0x00043034: 1a d4 ff 97              bl       #0x3809c
0x00043038: 15 00 40 f9              ldr      x21, [x0]
0x0004303c: 95 03 00 b5              cbnz     x21, #0x430ac
0x00043040: f7 03 00 aa              mov      x23, x0
0x00043044: 00 0a 80 52              mov      w0, #0x50
0x00043048: 02 b8 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x0004304c: e8 1b 40 f9              ldr      x8, [sp, #0x30]
0x00043050: e0 0b c0 3d              ldr      q0, [sp, #0x20]
0x00043054: e9 2b 40 f9              ldr      x9, [sp, #0x50]
0x00043058: ff ff 02 a9              stp      xzr, xzr, [sp, #0x28]
0x0004305c: ff 13 00 f9              str      xzr, [sp, #0x20]
0x00043060: 1f 7c 04 a9              stp      xzr, xzr, [x0, #0x40]
0x00043064: 1f 7c 00 a9              stp      xzr, xzr, [x0]
0x00043068: 00 08 80 3d              str      q0, [x0, #0x20]
0x0004306c: 08 7c 03 a9              stp      x8, xzr, [x0, #0x30]
0x00043070: 09 08 00 f9              str      x9, [x0, #0x10]
0x00043074: e0 02 00 f9              str      x0, [x23]
0x00043078: c8 02 40 f9              ldr      x8, [x22]
0x0004307c: f5 03 00 aa              mov      x21, x0
0x00043080: 08 01 40 f9              ldr      x8, [x8]
0x00043084: 88 00 00 b4              cbz      x8, #0x43094
0x00043088: c8 02 00 f9              str      x8, [x22]
0x0004308c: e1 02 40 f9              ldr      x1, [x23]
0x00043090: 02 00 00 14              b        #0x43098                   ; -> _ZN6BYTENN20EspressoDisassembler22disassembleModelBufferERNS_23ByteNNInternalConfigExtE+0x5a8
0x00043094: e1 03 15 aa              mov      x1, x21
0x00043098: 60 d2 40 f9              ldr      x0, [x19, #0x1a0]
0x0004309c: 8b c8 ff 97              bl       #0x352c8
0x000430a0: 68 d6 40 f9              ldr      x8, [x19, #0x1a8]
0x000430a4: 08 05 00 91              add      x8, x8, #1
0x000430a8: 68 d6 00 f9              str      x8, [x19, #0x1a8]
0x000430ac: f6 03 15 aa              mov      x22, x21
0x000430b0: c8 8e 43 38              ldrb     w8, [x22, #0x38]!
0x000430b4: 68 00 00 37              tbnz     w8, #0, #0x430c0
0x000430b8: df 02 00 79              strh     wzr, [x22]
0x000430bc: 09 00 00 14              b        #0x430e0                   ; -> _ZN6BYTENN20EspressoDisassembler22disassembleModelBufferERNS_23ByteNNInternalConfigExtE+0x5f0
0x000430c0: a8 26 40 f9              ldr      x8, [x21, #0x48]
0x000430c4: 1f 01 00 39              strb     wzr, [x8]
0x000430c8: a8 e2 40 39              ldrb     w8, [x21, #0x38]
0x000430cc: bf 22 00 f9              str      xzr, [x21, #0x40]
0x000430d0: 88 00 00 36              tbz      w8, #0, #0x430e0
0x000430d4: a0 26 40 f9              ldr      x0, [x21, #0x48]
0x000430d8: f2 b1 ff 97              bl       #0x2f8a0                   ; -> _ZdlPv@plt
0x000430dc: bf 1e 00 f9              str      xzr, [x21, #0x38]
0x000430e0: e8 27 40 f9              ldr      x8, [sp, #0x48]
0x000430e4: e0 83 c3 3c              ldur     q0, [sp, #0x38]
0x000430e8: c8 0a 00 f9              str      x8, [x22, #0x10]
0x000430ec: c0 02 80 3d              str      q0, [x22]
0x000430f0: e8 83 40 39              ldrb     w8, [sp, #0x20]
0x000430f4: ff 7f 04 a9              stp      xzr, xzr, [sp, #0x40]
0x000430f8: ff 1f 00 f9              str      xzr, [sp, #0x38]
0x000430fc: e8 00 00 36              tbz      w8, #0, #0x43118
0x00043100: e0 1b 40 f9              ldr      x0, [sp, #0x30]
0x00043104: e7 b1 ff 97              bl       #0x2f8a0                   ; -> _ZdlPv@plt
0x00043108: e8 e3 40 39              ldrb     w8, [sp, #0x38]
0x0004310c: 68 00 00 36              tbz      w8, #0, #0x43118
0x00043110: e0 27 40 f9              ldr      x0, [sp, #0x48]
0x00043114: e3 b1 ff 97              bl       #0x2f8a0                   ; -> _ZdlPv@plt
0x00043118: e0 03 14 aa              mov      x0, x20
0x0004311c: c9 b2 ff 97              bl       #0x2fc40                   ; -> free@plt
0x00043120: 22 00 00 14              b        #0x431a8                   ; -> _ZN6BYTENN20EspressoDisassembler22disassembleModelBufferERNS_23ByteNNInternalConfigExtE+0x6b8
0x00043124: e1 03 14 aa              mov      x1, x20
0x00043128: 60 d2 40 f9              ldr      x0, [x19, #0x1a0]
0x0004312c: 67 c8 ff 97              bl       #0x352c8
0x00043130: 68 d6 40 f9              ldr      x8, [x19, #0x1a8]
0x00043134: 08 05 00 91              add      x8, x8, #1
0x00043138: 68 d6 00 f9              str      x8, [x19, #0x1a8]
0x0004313c: f5 03 14 aa              mov      x21, x20
0x00043140: a8 8e 43 38              ldrb     w8, [x21, #0x38]!
0x00043144: 68 00 00 37              tbnz     w8, #0, #0x43150
0x00043148: bf 02 00 79              strh     wzr, [x21]
0x0004314c: 09 00 00 14              b        #0x43170                   ; -> _ZN6BYTENN20EspressoDisassembler22disassembleModelBufferERNS_23ByteNNInternalConfigExtE+0x680
0x00043150: 88 26 40 f9              ldr      x8, [x20, #0x48]
0x00043154: 1f 01 00 39              strb     wzr, [x8]
0x00043158: 88 e2 40 39              ldrb     w8, [x20, #0x38]
0x0004315c: 9f 22 00 f9              str      xzr, [x20, #0x40]
0x00043160: 88 00 00 36              tbz      w8, #0, #0x43170
0x00043164: 80 26 40 f9              ldr      x0, [x20, #0x48]
0x00043168: ce b1 ff 97              bl       #0x2f8a0                   ; -> _ZdlPv@plt
0x0004316c: 9f 1e 00 f9              str      xzr, [x20, #0x38]
0x00043170: e8 27 40 f9              ldr      x8, [sp, #0x48]
0x00043174: e0 83 c3 3c              ldur     q0, [sp, #0x38]
0x00043178: a8 0a 00 f9              str      x8, [x21, #0x10]
0x0004317c: a0 02 80 3d              str      q0, [x21]
0x00043180: e8 83 40 39              ldrb     w8, [sp, #0x20]
0x00043184: ff 7f 04 a9              stp      xzr, xzr, [sp, #0x40]
0x00043188: ff 1f 00 f9              str      xzr, [sp, #0x38]
0x0004318c: e8 00 00 36              tbz      w8, #0, #0x431a8
0x00043190: e0 1b 40 f9              ldr      x0, [sp, #0x30]
0x00043194: c3 b1 ff 97              bl       #0x2f8a0                   ; -> _ZdlPv@plt
0x00043198: e8 e3 40 39              ldrb     w8, [sp, #0x38]
0x0004319c: 68 00 00 36              tbz      w8, #0, #0x431a8
0x000431a0: e0 27 40 f9              ldr      x0, [sp, #0x48]
0x000431a4: bf b1 ff 97              bl       #0x2f8a0                   ; -> _ZdlPv@plt
0x000431a8: c1 0c 00 f0              adrp     x1, #0x1de000
0x000431ac: 76 0a 40 f9              ldr      x22, [x19, #0x10]
0x000431b0: 40 df 42 29              ldp      w0, w23, [x26, #0x14]
0x000431b4: 21 b8 45 f9              ldr      x1, [x1, #0xb70]           ; -> _ZSt7nothrow@got
0x000431b8: 22 b4 ff 97              bl       #0x30240                   ; -> _ZnamRKSt9nothrow_t@plt
0x000431bc: f5 03 00 aa              mov      x21, x0
0x000431c0: 00 04 80 52              mov      w0, #0x20
0x000431c4: a3 b7 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x000431c8: 1f 08 00 f9              str      xzr, [x0, #0x10]
0x000431cc: db 0c 00 f0              adrp     x27, #0x1de000
0x000431d0: 7b 1b 45 f9              ldr      x27, [x27, #0xa30]         ; -> _ZTVNSt6__ndk120__shared_ptr_pointerIPhNS_14default_deleteIA_hEENS_9allocatorIhEEEE@got
0x000431d4: f4 03 00 aa              mov      x20, x0
0x000431d8: fc 03 00 aa              mov      x28, x0
0x000431dc: 9f 8e 00 f8              str      xzr, [x20, #8]!
0x000431e0: 68 43 00 91              add      x8, x27, #0x10
0x000431e4: 08 00 00 f9              str      x8, [x0]
0x000431e8: 15 0c 00 f9              str      x21, [x0, #0x18]
0x000431ec: 95 04 00 b4              cbz      x21, #0x4327c
0x000431f0: 42 17 40 b9              ldr      w2, [x26, #0x14]
0x000431f4: c1 02 17 8b              add      x1, x22, x23
0x000431f8: e0 03 15 aa              mov      x0, x21
0x000431fc: f8 07 00 f9              str      x24, [sp, #8]
0x00043200: 28 b1 ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00043204: 77 42 05 91              add      x23, x19, #0x150
0x00043208: e1 83 00 91              add      x1, sp, #0x20
0x0004320c: e2 e3 00 91              add      x2, sp, #0x38
0x00043210: e0 03 17 aa              mov      x0, x23
0x00043214: ff 7f 04 a9              stp      xzr, xzr, [sp, #0x40]
0x00043218: ff 1f 00 f9              str      xzr, [sp, #0x38]
0x0004321c: 91 b6 ff 97              bl       #0x30c60                   ; -> _ZNSt6__ndk16__treeINS_12__value_typeINS_12basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEEENS_10shared_ptrIhEEEENS_19__map_value_compareIS7_SA_NS_4lessIS7_EELb1EEENS5_ISA_EEE12__find_equalIS7_EERPNS_16__tree_node_baseIPvEERPNS_15__tree_end_nodeISL_EERKT_@plt
0x00043220: 16 00 40 f9              ldr      x22, [x0]
0x00043224: d6 03 00 b5              cbnz     x22, #0x4329c
0x00043228: f8 03 00 aa              mov      x24, x0
0x0004322c: 00 09 80 52              mov      w0, #0x48
0x00043230: 88 b7 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00043234: e8 27 40 f9              ldr      x8, [sp, #0x48]
0x00043238: e0 83 c3 3c              ldur     q0, [sp, #0x38]
0x0004323c: e9 13 40 f9              ldr      x9, [sp, #0x20]
0x00043240: ff 7f 04 a9              stp      xzr, xzr, [sp, #0x40]
0x00043244: ff 1f 00 f9              str      xzr, [sp, #0x38]
0x00043248: 1f fc 03 a9              stp      xzr, xzr, [x0, #0x38]
0x0004324c: 1f 7c 00 a9              stp      xzr, xzr, [x0]
0x00043250: 00 08 80 3d              str      q0, [x0, #0x20]
0x00043254: 08 18 00 f9              str      x8, [x0, #0x30]
0x00043258: 09 08 00 f9              str      x9, [x0, #0x10]
0x0004325c: 00 03 00 f9              str      x0, [x24]
0x00043260: e8 02 40 f9              ldr      x8, [x23]
0x00043264: f6 03 00 aa              mov      x22, x0
0x00043268: 08 01 40 f9              ldr      x8, [x8]
0x0004326c: c8 00 00 b4              cbz      x8, #0x43284
0x00043270: e8 02 00 f9              str      x8, [x23]
0x00043274: 01 03 40 f9              ldr      x1, [x24]
0x00043278: 04 00 00 14              b        #0x43288                   ; -> _ZN6BYTENN20EspressoDisassembler22disassembleModelBufferERNS_23ByteNNInternalConfigExtE+0x798
0x0004327c: 35 00 80 52              mov      w21, #1
0x00043280: 58 00 00 14              b        #0x433e0                   ; -> _ZN6BYTENN20EspressoDisassembler22disassembleModelBufferERNS_23ByteNNInternalConfigExtE+0x8f0
0x00043284: e1 03 16 aa              mov      x1, x22
0x00043288: 60 ae 40 f9              ldr      x0, [x19, #0x158]
0x0004328c: 0f c8 ff 97              bl       #0x352c8
0x00043290: 68 b2 40 f9              ldr      x8, [x19, #0x160]
0x00043294: 08 05 00 91              add      x8, x8, #1
0x00043298: 68 b2 00 f9              str      x8, [x19, #0x160]
0x0004329c: 88 7e 5f c8              ldxr     x8, [x20]
0x000432a0: 08 05 00 91              add      x8, x8, #1
0x000432a4: 88 7e 09 c8              stxr     w9, x8, [x20]
0x000432a8: a9 ff ff 35              cbnz     w9, #0x4329c
0x000432ac: d7 22 40 f9              ldr      x23, [x22, #0x40]
0x000432b0: d5 f2 03 a9              stp      x21, x28, [x22, #0x38]
0x000432b4: b7 01 00 b4              cbz      x23, #0x432e8
0x000432b8: e8 22 00 91              add      x8, x23, #8
0x000432bc: 09 fd 5f c8              ldaxr    x9, [x8]
0x000432c0: 2a 05 00 d1              sub      x10, x9, #1
0x000432c4: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x000432c8: ab ff ff 35              cbnz     w11, #0x432bc
0x000432cc: e9 00 00 b5              cbnz     x9, #0x432e8
0x000432d0: e8 02 40 f9              ldr      x8, [x23]
0x000432d4: e0 03 17 aa              mov      x0, x23
0x000432d8: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x000432dc: 00 01 3f d6              blr      x8
0x000432e0: e0 03 17 aa              mov      x0, x23
0x000432e4: 1b b6 ff 97              bl       #0x30b50                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x000432e8: e8 e3 40 39              ldrb     w8, [sp, #0x38]
0x000432ec: 68 00 00 36              tbz      w8, #0, #0x432f8
0x000432f0: e0 27 40 f9              ldr      x0, [sp, #0x48]
0x000432f4: 6b b1 ff 97              bl       #0x2f8a0                   ; -> _ZdlPv@plt
0x000432f8: 48 17 40 b9              ldr      w8, [x26, #0x14]
0x000432fc: fa 0f 40 f9              ldr      x26, [sp, #0x18]
0x00043300: 68 c2 00 f9              str      x8, [x19, #0x180]
0x00043304: 5a 04 00 b4              cbz      x26, #0x4338c
0x00043308: c1 0c 00 f0              adrp     x1, #0x1de000
0x0004330c: 77 0a 40 f9              ldr      x23, [x19, #0x10]
0x00043310: 40 63 40 29              ldp      w0, w24, [x26]
0x00043314: 21 b8 45 f9              ldr      x1, [x1, #0xb70]           ; -> _ZSt7nothrow@got
0x00043318: ca b3 ff 97              bl       #0x30240                   ; -> _ZnamRKSt9nothrow_t@plt
0x0004331c: f6 03 00 aa              mov      x22, x0
0x00043320: 00 04 80 52              mov      w0, #0x20
0x00043324: 4b b7 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00043328: 68 43 00 91              add      x8, x27, #0x10
0x0004332c: 08 7c 00 a9              stp      x8, xzr, [x0]
0x00043330: 1f 58 01 a9              stp      xzr, x22, [x0, #0x10]
0x00043334: 75 de 40 f9              ldr      x21, [x19, #0x1b8]
0x00043338: 76 02 1b a9              stp      x22, x0, [x19, #0x1b0]
0x0004333c: b5 01 00 b4              cbz      x21, #0x43370
0x00043340: a8 22 00 91              add      x8, x21, #8
0x00043344: 09 fd 5f c8              ldaxr    x9, [x8]
0x00043348: 2a 05 00 d1              sub      x10, x9, #1
0x0004334c: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00043350: ab ff ff 35              cbnz     w11, #0x43344
0x00043354: e9 00 00 b5              cbnz     x9, #0x43370
0x00043358: a8 02 40 f9              ldr      x8, [x21]
0x0004335c: e0 03 15 aa              mov      x0, x21
0x00043360: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00043364: 00 01 3f d6              blr      x8
0x00043368: e0 03 15 aa              mov      x0, x21
0x0004336c: f9 b5 ff 97              bl       #0x30b50                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00043370: 60 da 40 f9              ldr      x0, [x19, #0x1b0]
0x00043374: 40 03 00 b4              cbz      x0, #0x433dc
0x00043378: 42 03 40 b9              ldr      w2, [x26]
0x0004337c: e1 02 18 8b              add      x1, x23, x24
0x00043380: c8 b0 ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00043384: 48 03 40 b9              ldr      w8, [x26]
0x00043388: 68 e2 00 f9              str      x8, [x19, #0x1c0]
0x0004338c: ea 0b 40 f9              ldr      x10, [sp, #0x10]
0x00043390: ea 00 00 b4              cbz      x10, #0x433ac
0x00043394: 68 0a 40 f9              ldr      x8, [x19, #0x10]
0x00043398: 49 05 40 b9              ldr      w9, [x10, #4]
0x0004339c: 08 01 09 8b              add      x8, x8, x9
0x000433a0: 68 e6 00 f9              str      x8, [x19, #0x1c8]
0x000433a4: 48 01 40 b9              ldr      w8, [x10]
0x000433a8: 68 ea 00 f9              str      x8, [x19, #0x1d0]
0x000433ac: e9 07 40 f9              ldr      x9, [sp, #8]
0x000433b0: e9 00 00 b4              cbz      x9, #0x433cc
0x000433b4: 28 01 40 b9              ldr      w8, [x9]
0x000433b8: 68 ca 00 f9              str      x8, [x19, #0x190]
0x000433bc: 68 0a 40 f9              ldr      x8, [x19, #0x10]
0x000433c0: 29 05 40 b9              ldr      w9, [x9, #4]
0x000433c4: 08 01 09 8b              add      x8, x8, x9
0x000433c8: 68 c6 00 f9              str      x8, [x19, #0x188]
0x000433cc: e0 03 13 aa              mov      x0, x19
0x000433d0: 4c b4 ff 97              bl       #0x30500                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv@plt
0x000433d4: f5 03 1f 2a              mov      w21, wzr
0x000433d8: 02 00 00 14              b        #0x433e0                   ; -> _ZN6BYTENN20EspressoDisassembler22disassembleModelBufferERNS_23ByteNNInternalConfigExtE+0x8f0
0x000433dc: 35 00 80 52              mov      w21, #1
0x000433e0: 88 fe 5f c8              ldaxr    x8, [x20]
0x000433e4: 09 05 00 d1              sub      x9, x8, #1
0x000433e8: 89 fe 0a c8              stlxr    w10, x9, [x20]
0x000433ec: aa ff ff 35              cbnz     w10, #0x433e0
0x000433f0: 48 bf ff b5              cbnz     x8, #0x42bd8
0x000433f4: 88 03 40 f9              ldr      x8, [x28]
0x000433f8: e0 03 1c aa              mov      x0, x28
0x000433fc: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00043400: 00 01 3f d6              blr      x8
0x00043404: e0 03 1c aa              mov      x0, x28
0x00043408: d2 b5 ff 97              bl       #0x30b50                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x0004340c: f3 fd ff 17              b        #0x42bd8                   ; -> _ZN6BYTENN20EspressoDisassembler22disassembleModelBufferERNS_23ByteNNInternalConfigExtE+0xe8
0x00043410: cc b7 ff 97              bl       #0x31340                   ; -> __stack_chk_fail@plt
0x00043414: 07 b6 ff 97              bl       #0x30c30                   ; -> abort@plt
