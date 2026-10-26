; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_musically/native/libbytenn.so
; target  : _ZN6BYTENN16ByteNNEngineImpl4InitERKNS_9ConfigExtE (0x33b58)
; start   : 0x33b58
; size    : 0x85c
; bytes   : 0x85c   instructions: 535
; generated: 2026-10-23T23:27:24

0x00033b58: fc 67 bb a9              stp      x28, x25, [sp, #-0x50]!
0x00033b5c: f8 5f 01 a9              stp      x24, x23, [sp, #0x10]
0x00033b60: f6 57 02 a9              stp      x22, x21, [sp, #0x20]
0x00033b64: f4 4f 03 a9              stp      x20, x19, [sp, #0x30]
0x00033b68: fd 7b 04 a9              stp      x29, x30, [sp, #0x40]
0x00033b6c: fd 03 01 91              add      x29, sp, #0x40
0x00033b70: ff 03 18 d1              sub      sp, sp, #0x600
0x00033b74: 57 d0 3b d5              mrs      x23, tpidr_el0
0x00033b78: e8 16 40 f9              ldr      x8, [x23, #0x28]
0x00033b7c: f5 03 01 aa              mov      x21, x1
0x00033b80: f3 03 00 aa              mov      x19, x0
0x00033b84: f4 83 10 91              add      x20, sp, #0x420
0x00033b88: a8 83 1b f8              stur     x8, [x29, #-0x48]
0x00033b8c: e8 83 10 91              add      x8, sp, #0x420
0x00033b90: 7c f4 ff 97              bl       #0x30d80                   ; -> _ZN6BYTENN11ByteNNUtils10GetVersionEv@plt
0x00033b94: e8 83 50 39              ldrb     w8, [sp, #0x420]
0x00033b98: e9 1b 42 f9              ldr      x9, [sp, #0x430]
0x00033b9c: 8a 02 40 b2              orr      x10, x20, #1
0x00033ba0: 81 0a 00 90              adrp     x1, #0x183000
0x00033ba4: 1f 01 00 72              tst      w8, #1
0x00033ba8: 82 0a 00 90              adrp     x2, #0x183000
0x00033bac: 43 01 89 9a              csel     x3, x10, x9, eq
0x00033bb0: 21 e0 21 91              add      x1, x1, #0x878
0x00033bb4: 42 f0 22 91              add      x2, x2, #0x8bc
0x00033bb8: 60 00 80 52              mov      w0, #3
0x00033bbc: 3d f2 ff 97              bl       #0x304b0                   ; -> __android_log_print@plt
0x00033bc0: e8 83 50 39              ldrb     w8, [sp, #0x420]
0x00033bc4: 68 00 00 36              tbz      w8, #0, #0x33bd0
0x00033bc8: e0 1b 42 f9              ldr      x0, [sp, #0x430]
0x00033bcc: 35 ef ff 97              bl       #0x2f8a0                   ; -> _ZdlPv@plt
0x00033bd0: 63 3e 43 b9              ldr      w3, [x19, #0x33c]
0x00033bd4: 63 01 00 34              cbz      w3, #0x33c00
0x00033bd8: 81 0a 00 90              adrp     x1, #0x183000
0x00033bdc: 82 0a 00 90              adrp     x2, #0x183000
0x00033be0: 84 0a 00 90              adrp     x4, #0x183000
0x00033be4: 21 e0 21 91              add      x1, x1, #0x878
0x00033be8: 42 fc 21 91              add      x2, x2, #0x87f
0x00033bec: 84 44 23 91              add      x4, x4, #0x8d1
0x00033bf0: 20 00 80 52              mov      w0, #1
0x00033bf4: 7b f5 ff 97              bl       #0x311e0                   ; -> _ZN6BYTENN9ByteNNLogEiPKcS1_z@plt
0x00033bf8: 76 3e 43 b9              ldr      w22, [x19, #0x33c]
0x00033bfc: e1 01 00 14              b        #0x34380                   ; -> _ZN6BYTENN16ByteNNEngineImpl4InitERKNS_9ConfigExtE+0x828
0x00033c00: a8 6e 40 f9              ldr      x8, [x21, #0xd8]
0x00033c04: a8 03 00 b4              cbz      x8, #0x33c78
0x00033c08: a9 66 40 f9              ldr      x9, [x21, #0xc8]
0x00033c0c: a8 42 03 91              add      x8, x21, #0xd0
0x00033c10: 1f 01 09 eb              cmp      x8, x9
0x00033c14: a1 00 00 54              b.ne     #0x33c28
0x00033c18: 1c 00 00 14              b        #0x33c88                   ; -> _ZN6BYTENN16ByteNNEngineImpl4InitERKNS_9ConfigExtE+0x130
0x00033c1c: e9 03 0b aa              mov      x9, x11
0x00033c20: 1f 01 09 eb              cmp      x8, x9
0x00033c24: 20 03 00 54              b.eq     #0x33c88
0x00033c28: 2a 1d 40 f9              ldr      x10, [x9, #0x38]
0x00033c2c: 6a 02 00 b4              cbz      x10, #0x33c78
0x00033c30: 2a 05 40 f9              ldr      x10, [x9, #8]
0x00033c34: aa 00 00 b4              cbz      x10, #0x33c48
0x00033c38: e9 03 0a aa              mov      x9, x10
0x00033c3c: 4a 01 40 f9              ldr      x10, [x10]
0x00033c40: ca ff ff b5              cbnz     x10, #0x33c38
0x00033c44: f7 ff ff 17              b        #0x33c20                   ; -> _ZN6BYTENN16ByteNNEngineImpl4InitERKNS_9ConfigExtE+0xc8
0x00033c48: ea 03 09 aa              mov      x10, x9
0x00033c4c: 4b 0d 41 f8              ldr      x11, [x10, #0x10]!
0x00033c50: 6c 01 40 f9              ldr      x12, [x11]
0x00033c54: 9f 01 09 eb              cmp      x12, x9
0x00033c58: 20 fe ff 54              b.eq     #0x33c1c
0x00033c5c: 4b 01 40 f9              ldr      x11, [x10]
0x00033c60: ea 03 0b aa              mov      x10, x11
0x00033c64: 49 0d 41 f8              ldr      x9, [x10, #0x10]!
0x00033c68: 2c 01 40 f9              ldr      x12, [x9]
0x00033c6c: 9f 01 0b eb              cmp      x12, x11
0x00033c70: 61 ff ff 54              b.ne     #0x33c5c
0x00033c74: eb ff ff 17              b        #0x33c20                   ; -> _ZN6BYTENN16ByteNNEngineImpl4InitERKNS_9ConfigExtE+0xc8
0x00033c78: a3 0a 40 f9              ldr      x3, [x21, #0x10]
0x00033c7c: 83 00 00 b5              cbnz     x3, #0x33c8c
0x00033c80: a4 1a 40 b9              ldr      w4, [x21, #0x18]
0x00033c84: 35 00 00 14              b        #0x33d58                   ; -> _ZN6BYTENN16ByteNNEngineImpl4InitERKNS_9ConfigExtE+0x200
0x00033c88: a3 0a 40 f9              ldr      x3, [x21, #0x10]
0x00033c8c: a4 1a 40 b9              ldr      w4, [x21, #0x18]
0x00033c90: 9f 00 00 71              cmp      w4, #0
0x00033c94: 2d 06 00 54              b.le     #0x33d58
0x00033c98: 28 00 80 52              mov      w8, #1
0x00033c9c: e0 43 0b 91              add      x0, sp, #0x2d0
0x00033ca0: e1 03 15 aa              mov      x1, x21
0x00033ca4: 68 e2 0c 39              strb     w8, [x19, #0x338]
0x00033ca8: 7a f6 ff 97              bl       #0x31690                   ; -> _ZN6BYTENN9ConfigExtC2ERKS0_@plt
0x00033cac: e0 83 10 91              add      x0, sp, #0x420
0x00033cb0: e1 43 0b 91              add      x1, sp, #0x2d0
0x00033cb4: f6 83 10 91              add      x22, sp, #0x420
0x00033cb8: 76 f6 ff 97              bl       #0x31690                   ; -> _ZN6BYTENN9ConfigExtC2ERKS0_@plt
0x00033cbc: 58 0d 00 f0              adrp     x24, #0x1de000
0x00033cc0: 18 53 46 f9              ldr      x24, [x24, #0xca0]         ; -> _ZTVN6BYTENN23ByteNNInternalConfigExtE@got
0x00033cc4: c8 c2 05 91              add      x8, x22, #0x170
0x00033cc8: c9 82 06 91              add      x9, x22, #0x1a0
0x00033ccc: e8 c7 02 f9              str      x8, [sp, #0x588]
0x00033cd0: c8 62 05 91              add      x8, x22, #0x158
0x00033cd4: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x00033cd8: e9 df 02 f9              str      x9, [sp, #0x5b8]
0x00033cdc: 09 43 00 91              add      x9, x24, #0x10
0x00033ce0: ff cb 02 f9              str      xzr, [sp, #0x590]
0x00033ce4: ff c3 02 f9              str      xzr, [sp, #0x580]
0x00033ce8: ff bf 02 f9              str      xzr, [sp, #0x578]
0x00033cec: ff d3 02 f9              str      xzr, [sp, #0x5a0]
0x00033cf0: ff cf 02 f9              str      xzr, [sp, #0x598]
0x00033cf4: ff e7 02 f9              str      xzr, [sp, #0x5c8]
0x00033cf8: ff e3 02 f9              str      xzr, [sp, #0x5c0]
0x00033cfc: e8 bb 02 f9              str      x8, [sp, #0x570]
0x00033d00: e9 13 02 f9              str      x9, [sp, #0x420]
0x00033d04: 80 82 0d ad              stp      q0, q0, [x20, #0x1b0]
0x00033d08: e8 2b 44 b9              ldr      w8, [sp, #0x428]
0x00033d0c: ff fb 02 f9              str      xzr, [sp, #0x5f0]
0x00033d10: 1f 11 00 71              cmp      w8, #4
0x00033d14: 60 00 00 54              b.eq     #0x33d20
0x00033d18: 1f 05 00 71              cmp      w8, #1
0x00033d1c: 61 00 00 54              b.ne     #0x33d28
0x00033d20: c8 00 80 52              mov      w8, #6
0x00033d24: e8 2b 04 b9              str      w8, [sp, #0x428]
0x00033d28: e0 43 0b 91              add      x0, sp, #0x2d0
0x00033d2c: 01 f3 ff 97              bl       #0x30930                   ; -> _ZN6BYTENN9ConfigExtD2Ev@plt
0x00033d30: 74 82 05 91              add      x20, x19, #0x160
0x00033d34: e0 83 10 91              add      x0, sp, #0x420
0x00033d38: e1 03 14 aa              mov      x1, x20
0x00033d3c: 30 02 00 94              bl       #0x345fc
0x00033d40: 68 22 46 39              ldrb     w8, [x19, #0x188]
0x00033d44: 29 00 80 52              mov      w9, #1
0x00033d48: 69 f2 05 39              strb     w9, [x19, #0x17c]
0x00033d4c: 68 01 00 37              tbnz     w8, #0, #0x33d78
0x00033d50: 09 fd 41 d3              lsr      x9, x8, #1
0x00033d54: 0a 00 00 14              b        #0x33d7c                   ; -> _ZN6BYTENN16ByteNNEngineImpl4InitERKNS_9ConfigExtE+0x224
0x00033d58: 81 0a 00 90              adrp     x1, #0x183000
0x00033d5c: 82 0a 00 90              adrp     x2, #0x183000
0x00033d60: 21 e0 21 91              add      x1, x1, #0x878
0x00033d64: 42 2c 32 91              add      x2, x2, #0xc8b
0x00033d68: 20 00 80 52              mov      w0, #1
0x00033d6c: 1d f5 ff 97              bl       #0x311e0                   ; -> _ZN6BYTENN9ByteNNLogEiPKcS1_z@plt
0x00033d70: b6 00 80 52              mov      w22, #5
0x00033d74: 83 01 00 14              b        #0x34380                   ; -> _ZN6BYTENN16ByteNNEngineImpl4InitERKNS_9ConfigExtE+0x828
0x00033d78: 69 ca 40 f9              ldr      x9, [x19, #0x190]
0x00033d7c: 59 0d 00 f0              adrp     x25, #0x1de000
0x00033d80: 39 ab 46 f9              ldr      x25, [x25, #0xd50]         ; -> _ZN6BYTENN15gSystemSettingsE@got
0x00033d84: 76 22 06 91              add      x22, x19, #0x188
0x00033d88: a9 01 00 b4              cbz      x9, #0x33dbc
0x00033d8c: 69 ca 40 f9              ldr      x9, [x19, #0x190]
0x00033d90: 0a 7d 41 d3              ubfx     x10, x8, #1, #0x1f
0x00033d94: 1f 01 00 72              tst      w8, #1
0x00033d98: 49 01 89 9a              csel     x9, x10, x9, eq
0x00033d9c: 3f 05 00 f1              cmp      x9, #1
0x00033da0: 81 02 00 54              b.ne     #0x33df0
0x00033da4: 69 ce 40 f9              ldr      x9, [x19, #0x198]
0x00033da8: 1f 01 00 72              tst      w8, #1
0x00033dac: 28 15 96 9a              csinc    x8, x9, x22, ne
0x00033db0: 08 01 40 39              ldrb     w8, [x8]
0x00033db4: 1f b9 00 71              cmp      w8, #0x2e
0x00033db8: c1 01 00 54              b.ne     #0x33df0
0x00033dbc: 28 03 40 39              ldrb     w8, [x25]
0x00033dc0: 29 07 40 f9              ldr      x9, [x25, #8]              ; -> ftell@got
0x00033dc4: 0a fd 41 d3              lsr      x10, x8, #1
0x00033dc8: 1f 01 00 72              tst      w8, #1
0x00033dcc: 42 01 89 9a              csel     x2, x10, x9, eq
0x00033dd0: df 02 19 eb              cmp      x22, x25
0x00033dd4: e0 00 00 54              b.eq     #0x33df0
0x00033dd8: c2 00 00 b4              cbz      x2, #0x33df0
0x00033ddc: 29 0b 40 f9              ldr      x9, [x25, #0x10]           ; -> _ZN5IESNN25clCreateProgramWithSourceEP11_cl_contextjPPKcPKmPi@got
0x00033de0: 1f 01 00 72              tst      w8, #1
0x00033de4: e0 03 16 aa              mov      x0, x22
0x00033de8: 21 15 99 9a              csinc    x1, x9, x25, ne
0x00033dec: ab 06 00 94              bl       #0x35898
0x00033df0: 68 82 46 39              ldrb     w8, [x19, #0x1a0]
0x00033df4: 88 00 00 37              tbnz     w8, #0, #0x33e04
0x00033df8: 08 fd 41 d3              lsr      x8, x8, #1
0x00033dfc: 88 00 00 b5              cbnz     x8, #0x33e0c
0x00033e00: 10 00 00 14              b        #0x33e40                   ; -> _ZN6BYTENN16ByteNNEngineImpl4InitERKNS_9ConfigExtE+0x2e8
0x00033e04: 68 d6 40 f9              ldr      x8, [x19, #0x1a8]
0x00033e08: c8 01 00 b4              cbz      x8, #0x33e40
0x00033e0c: 68 22 46 39              ldrb     w8, [x19, #0x188]
0x00033e10: 69 ca 40 f9              ldr      x9, [x19, #0x190]
0x00033e14: 0a fd 41 d3              lsr      x10, x8, #1
0x00033e18: 1f 01 00 72              tst      w8, #1
0x00033e1c: 49 01 89 9a              csel     x9, x10, x9, eq
0x00033e20: 3f 05 00 f1              cmp      x9, #1
0x00033e24: c1 02 00 54              b.ne     #0x33e7c
0x00033e28: 69 ce 40 f9              ldr      x9, [x19, #0x198]
0x00033e2c: 1f 01 00 72              tst      w8, #1
0x00033e30: 28 15 96 9a              csinc    x8, x9, x22, ne
0x00033e34: 08 01 40 39              ldrb     w8, [x8]
0x00033e38: 1f b9 00 71              cmp      w8, #0x2e
0x00033e3c: 01 02 00 54              b.ne     #0x33e7c
0x00033e40: e9 03 19 aa              mov      x9, x25
0x00033e44: 28 8d 41 38              ldrb     w8, [x9, #0x18]!
0x00033e48: 60 82 06 91              add      x0, x19, #0x1a0
0x00033e4c: 2a 05 40 f9              ldr      x10, [x9, #8]
0x00033e50: 0b fd 41 d3              lsr      x11, x8, #1
0x00033e54: 1f 01 00 72              tst      w8, #1
0x00033e58: 62 01 8a 9a              csel     x2, x11, x10, eq
0x00033e5c: 1f 00 09 eb              cmp      x0, x9
0x00033e60: e0 00 00 54              b.eq     #0x33e7c
0x00033e64: c2 00 00 b4              cbz      x2, #0x33e7c
0x00033e68: 29 17 40 f9              ldr      x9, [x25, #0x28]           ; -> _ZNSt6__ndk114__split_bufferIN5IESNN12IESGPUTensorERNS_9allocatorIS2_EEED2Ev@got
0x00033e6c: 2a 67 00 91              add      x10, x25, #0x19
0x00033e70: 1f 01 00 72              tst      w8, #1
0x00033e74: 41 01 89 9a              csel     x1, x10, x9, eq
0x00033e78: 88 06 00 94              bl       #0x35898
0x00033e7c: a8 0a 40 b9              ldr      w8, [x21, #8]
0x00033e80: 1f 29 00 71              cmp      w8, #0xa
0x00033e84: 61 01 00 54              b.ne     #0x33eb0
0x00033e88: 63 3e 43 b9              ldr      w3, [x19, #0x33c]
0x00033e8c: 81 0a 00 90              adrp     x1, #0x183000
0x00033e90: 82 0a 00 90              adrp     x2, #0x183000
0x00033e94: 21 e0 21 91              add      x1, x1, #0x878
0x00033e98: 42 68 33 91              add      x2, x2, #0xcda
0x00033e9c: 20 00 80 52              mov      w0, #1
0x00033ea0: d0 f4 ff 97              bl       #0x311e0                   ; -> _ZN6BYTENN9ByteNNLogEiPKcS1_z@plt
0x00033ea4: 48 01 80 52              mov      w8, #0xa
0x00033ea8: 68 6a 01 b9              str      w8, [x19, #0x168]
0x00033eac: 0e 00 00 14              b        #0x33ee4                   ; -> _ZN6BYTENN16ByteNNEngineImpl4InitERKNS_9ConfigExtE+0x38c
0x00033eb0: e0 03 14 aa              mov      x0, x20
0x00033eb4: 27 f4 ff 97              bl       #0x30f50                   ; -> _ZN6BYTENN20EspressoDisassembler11disassembleERNS_23ByteNNInternalConfigExtE@plt
0x00033eb8: 60 3e 03 b9              str      w0, [x19, #0x33c]
0x00033ebc: 40 01 00 34              cbz      w0, #0x33ee4
0x00033ec0: 81 0a 00 90              adrp     x1, #0x183000
0x00033ec4: 82 0a 00 90              adrp     x2, #0x183000
0x00033ec8: e3 03 00 2a              mov      w3, w0
0x00033ecc: 21 e0 21 91              add      x1, x1, #0x878
0x00033ed0: 42 00 34 91              add      x2, x2, #0xd00
0x00033ed4: 20 00 80 52              mov      w0, #1
0x00033ed8: c2 f4 ff 97              bl       #0x311e0                   ; -> _ZN6BYTENN9ByteNNLogEiPKcS1_z@plt
0x00033edc: 76 3e 43 b9              ldr      w22, [x19, #0x33c]
0x00033ee0: 0c 01 00 14              b        #0x34310                   ; -> _ZN6BYTENN16ByteNNEngineImpl4InitERKNS_9ConfigExtE+0x7b8
0x00033ee4: 75 a2 05 91              add      x21, x19, #0x168
0x00033ee8: e0 23 03 91              add      x0, sp, #0xc8
0x00033eec: e1 03 15 aa              mov      x1, x21
0x00033ef0: b0 f1 ff 97              bl       #0x305b0                   ; -> _ZN6BYTENN6ConfigC2ERKS0_@plt
0x00033ef4: e0 23 06 91              add      x0, sp, #0x188
0x00033ef8: e1 23 03 91              add      x1, sp, #0xc8
0x00033efc: ad f1 ff 97              bl       #0x305b0                   ; -> _ZN6BYTENN6ConfigC2ERKS0_@plt
0x00033f00: e8 8b 41 b9              ldr      w8, [sp, #0x188]
0x00033f04: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x00033f08: e9 67 05 91              add      x9, sp, #0x159
0x00033f0c: ff 43 01 f9              str      xzr, [sp, #0x280]
0x00033f10: 20 f1 8f 3c              stur     q0, [x9, #0xff]
0x00033f14: e9 27 05 91              add      x9, sp, #0x149
0x00033f18: 20 f1 8f 3c              stur     q0, [x9, #0xff]
0x00033f1c: e9 a7 06 91              add      x9, sp, #0x1a9
0x00033f20: ff 37 01 f9              str      xzr, [sp, #0x268]
0x00033f24: ff 4b 01 f9              str      xzr, [sp, #0x290]
0x00033f28: ff 47 01 f9              str      xzr, [sp, #0x288]
0x00033f2c: 20 f1 8f 3c              stur     q0, [x9, #0xff]
0x00033f30: e9 67 06 91              add      x9, sp, #0x199
0x00033f34: 1f 11 00 71              cmp      w8, #4
0x00033f38: 20 f1 8f 3c              stur     q0, [x9, #0xff]
0x00033f3c: ff 5f 01 f9              str      xzr, [sp, #0x2b8]
0x00033f40: 60 00 00 54              b.eq     #0x33f4c
0x00033f44: 1f 05 00 71              cmp      w8, #1
0x00033f48: 61 00 00 54              b.ne     #0x33f54
0x00033f4c: c8 00 80 52              mov      w8, #6
0x00033f50: e8 8b 01 b9              str      w8, [sp, #0x188]
0x00033f54: e8 03 0b 91              add      x8, sp, #0x2c0
0x00033f58: e0 23 06 91              add      x0, sp, #0x188
0x00033f5c: e5 f5 ff 97              bl       #0x316f0                   ; -> _ZN6BYTENN14NetworkFactory13CreateNetworkERKNS_20ByteNNInternalConfigE@plt
0x00033f60: e8 67 41 f9              ldr      x8, [sp, #0x2c8]
0x00033f64: e9 63 41 f9              ldr      x9, [sp, #0x2c0]
0x00033f68: ff 67 01 f9              str      xzr, [sp, #0x2c8]
0x00033f6c: ff 63 01 f9              str      xzr, [sp, #0x2c0]
0x00033f70: 76 0a 40 f9              ldr      x22, [x19, #0x10]
0x00033f74: 69 a2 00 a9              stp      x9, x8, [x19, #8]
0x00033f78: b6 01 00 b4              cbz      x22, #0x33fac
0x00033f7c: c8 22 00 91              add      x8, x22, #8
0x00033f80: 09 fd 5f c8              ldaxr    x9, [x8]
0x00033f84: 2a 05 00 d1              sub      x10, x9, #1
0x00033f88: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00033f8c: ab ff ff 35              cbnz     w11, #0x33f80
0x00033f90: e9 00 00 b5              cbnz     x9, #0x33fac
0x00033f94: c8 02 40 f9              ldr      x8, [x22]
0x00033f98: e0 03 16 aa              mov      x0, x22
0x00033f9c: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00033fa0: 00 01 3f d6              blr      x8
0x00033fa4: e0 03 16 aa              mov      x0, x22
0x00033fa8: ea f2 ff 97              bl       #0x30b50                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00033fac: f6 67 41 f9              ldr      x22, [sp, #0x2c8]
0x00033fb0: b6 01 00 b4              cbz      x22, #0x33fe4
0x00033fb4: c8 22 00 91              add      x8, x22, #8
0x00033fb8: 09 fd 5f c8              ldaxr    x9, [x8]
0x00033fbc: 2a 05 00 d1              sub      x10, x9, #1
0x00033fc0: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00033fc4: ab ff ff 35              cbnz     w11, #0x33fb8
0x00033fc8: e9 00 00 b5              cbnz     x9, #0x33fe4
0x00033fcc: c8 02 40 f9              ldr      x8, [x22]
0x00033fd0: e0 03 16 aa              mov      x0, x22
0x00033fd4: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00033fd8: 00 01 3f d6              blr      x8
0x00033fdc: e0 03 16 aa              mov      x0, x22
0x00033fe0: dc f2 ff 97              bl       #0x30b50                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00033fe4: f6 53 41 f9              ldr      x22, [sp, #0x2a0]
0x00033fe8: b6 01 00 b4              cbz      x22, #0x3401c
0x00033fec: c8 22 00 91              add      x8, x22, #8
0x00033ff0: 09 fd 5f c8              ldaxr    x9, [x8]
0x00033ff4: 2a 05 00 d1              sub      x10, x9, #1
0x00033ff8: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00033ffc: ab ff ff 35              cbnz     w11, #0x33ff0
0x00034000: e9 00 00 b5              cbnz     x9, #0x3401c
0x00034004: c8 02 40 f9              ldr      x8, [x22]
0x00034008: e0 03 16 aa              mov      x0, x22
0x0003400c: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00034010: 00 01 3f d6              blr      x8
0x00034014: e0 03 16 aa              mov      x0, x22
0x00034018: ce f2 ff 97              bl       #0x30b50                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x0003401c: e8 03 4a 39              ldrb     w8, [sp, #0x280]
0x00034020: 68 00 00 36              tbz      w8, #0, #0x3402c
0x00034024: e0 4b 41 f9              ldr      x0, [sp, #0x290]
0x00034028: 1e ee ff 97              bl       #0x2f8a0                   ; -> _ZdlPv@plt
0x0003402c: f6 33 41 f9              ldr      x22, [sp, #0x260]
0x00034030: b6 01 00 b4              cbz      x22, #0x34064
0x00034034: c8 22 00 91              add      x8, x22, #8
0x00034038: 09 fd 5f c8              ldaxr    x9, [x8]
0x0003403c: 2a 05 00 d1              sub      x10, x9, #1
0x00034040: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00034044: ab ff ff 35              cbnz     w11, #0x34038
0x00034048: e9 00 00 b5              cbnz     x9, #0x34064
0x0003404c: c8 02 40 f9              ldr      x8, [x22]
0x00034050: e0 03 16 aa              mov      x0, x22
0x00034054: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00034058: 00 01 3f d6              blr      x8
0x0003405c: e0 03 16 aa              mov      x0, x22
0x00034060: bc f2 ff 97              bl       #0x30b50                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00034064: f6 2b 41 f9              ldr      x22, [sp, #0x250]
0x00034068: b6 01 00 b4              cbz      x22, #0x3409c
0x0003406c: c8 22 00 91              add      x8, x22, #8
0x00034070: 09 fd 5f c8              ldaxr    x9, [x8]
0x00034074: 2a 05 00 d1              sub      x10, x9, #1
0x00034078: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x0003407c: ab ff ff 35              cbnz     w11, #0x34070
0x00034080: e9 00 00 b5              cbnz     x9, #0x3409c
0x00034084: c8 02 40 f9              ldr      x8, [x22]
0x00034088: e0 03 16 aa              mov      x0, x22
0x0003408c: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00034090: 00 01 3f d6              blr      x8
0x00034094: e0 03 16 aa              mov      x0, x22
0x00034098: ae f2 ff 97              bl       #0x30b50                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x0003409c: e0 23 06 91              add      x0, sp, #0x188
0x000340a0: 28 f1 ff 97              bl       #0x30540                   ; -> _ZN6BYTENN6ConfigD2Ev@plt
0x000340a4: e0 23 03 91              add      x0, sp, #0xc8
0x000340a8: 26 f1 ff 97              bl       #0x30540                   ; -> _ZN6BYTENN6ConfigD2Ev@plt
0x000340ac: 60 06 40 f9              ldr      x0, [x19, #8]
0x000340b0: 40 11 00 b4              cbz      x0, #0x342d8
0x000340b4: 08 00 40 f9              ldr      x8, [x0]
0x000340b8: e1 03 14 aa              mov      x1, x20
0x000340bc: 08 29 40 f9              ldr      x8, [x8, #0x50]
0x000340c0: 00 01 3f d6              blr      x8
0x000340c4: f6 03 00 2a              mov      w22, w0
0x000340c8: 1f 1c 00 71              cmp      w0, #7
0x000340cc: 60 3e 03 b9              str      w0, [x19, #0x33c]
0x000340d0: 01 12 00 54              b.ne     #0x34310
0x000340d4: a3 02 40 b9              ldr      w3, [x21]
0x000340d8: 61 0a 00 f0              adrp     x1, #0x183000
0x000340dc: 62 0a 00 f0              adrp     x2, #0x183000
0x000340e0: 21 e0 21 91              add      x1, x1, #0x878
0x000340e4: 42 28 26 91              add      x2, x2, #0x98a
0x000340e8: e0 03 1f 2a              mov      w0, wzr
0x000340ec: 3d f4 ff 97              bl       #0x311e0                   ; -> _ZN6BYTENN9ByteNNLogEiPKcS1_z@plt
0x000340f0: e0 23 00 91              add      x0, sp, #8
0x000340f4: e1 03 15 aa              mov      x1, x21
0x000340f8: 2e f1 ff 97              bl       #0x305b0                   ; -> _ZN6BYTENN6ConfigC2ERKS0_@plt
0x000340fc: e0 23 06 91              add      x0, sp, #0x188
0x00034100: e1 23 00 91              add      x1, sp, #8
0x00034104: 2b f1 ff 97              bl       #0x305b0                   ; -> _ZN6BYTENN6ConfigC2ERKS0_@plt
0x00034108: e8 8b 41 b9              ldr      w8, [sp, #0x188]
0x0003410c: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x00034110: e9 67 05 91              add      x9, sp, #0x159
0x00034114: ff 43 01 f9              str      xzr, [sp, #0x280]
0x00034118: 20 f1 8f 3c              stur     q0, [x9, #0xff]
0x0003411c: e9 27 05 91              add      x9, sp, #0x149
0x00034120: 20 f1 8f 3c              stur     q0, [x9, #0xff]
0x00034124: e9 a7 06 91              add      x9, sp, #0x1a9
0x00034128: ff 37 01 f9              str      xzr, [sp, #0x268]
0x0003412c: ff 4b 01 f9              str      xzr, [sp, #0x290]
0x00034130: ff 47 01 f9              str      xzr, [sp, #0x288]
0x00034134: 20 f1 8f 3c              stur     q0, [x9, #0xff]
0x00034138: e9 67 06 91              add      x9, sp, #0x199
0x0003413c: 1f 11 00 71              cmp      w8, #4
0x00034140: 20 f1 8f 3c              stur     q0, [x9, #0xff]
0x00034144: ff 5f 01 f9              str      xzr, [sp, #0x2b8]
0x00034148: 60 00 00 54              b.eq     #0x34154
0x0003414c: 1f 05 00 71              cmp      w8, #1
0x00034150: 61 00 00 54              b.ne     #0x3415c
0x00034154: c8 00 80 52              mov      w8, #6
0x00034158: e8 8b 01 b9              str      w8, [sp, #0x188]
0x0003415c: e8 03 0b 91              add      x8, sp, #0x2c0
0x00034160: e0 23 06 91              add      x0, sp, #0x188
0x00034164: 63 f5 ff 97              bl       #0x316f0                   ; -> _ZN6BYTENN14NetworkFactory13CreateNetworkERKNS_20ByteNNInternalConfigE@plt
0x00034168: e8 67 41 f9              ldr      x8, [sp, #0x2c8]
0x0003416c: e9 63 41 f9              ldr      x9, [sp, #0x2c0]
0x00034170: ff 67 01 f9              str      xzr, [sp, #0x2c8]
0x00034174: ff 63 01 f9              str      xzr, [sp, #0x2c0]
0x00034178: 76 0a 40 f9              ldr      x22, [x19, #0x10]
0x0003417c: 69 a2 00 a9              stp      x9, x8, [x19, #8]
0x00034180: b6 01 00 b4              cbz      x22, #0x341b4
0x00034184: c8 22 00 91              add      x8, x22, #8
0x00034188: 09 fd 5f c8              ldaxr    x9, [x8]
0x0003418c: 2a 05 00 d1              sub      x10, x9, #1
0x00034190: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00034194: ab ff ff 35              cbnz     w11, #0x34188
0x00034198: e9 00 00 b5              cbnz     x9, #0x341b4
0x0003419c: c8 02 40 f9              ldr      x8, [x22]
0x000341a0: e0 03 16 aa              mov      x0, x22
0x000341a4: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x000341a8: 00 01 3f d6              blr      x8
0x000341ac: e0 03 16 aa              mov      x0, x22
0x000341b0: 68 f2 ff 97              bl       #0x30b50                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x000341b4: f6 67 41 f9              ldr      x22, [sp, #0x2c8]
0x000341b8: b6 01 00 b4              cbz      x22, #0x341ec
0x000341bc: c8 22 00 91              add      x8, x22, #8
0x000341c0: 09 fd 5f c8              ldaxr    x9, [x8]
0x000341c4: 2a 05 00 d1              sub      x10, x9, #1
0x000341c8: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x000341cc: ab ff ff 35              cbnz     w11, #0x341c0
0x000341d0: e9 00 00 b5              cbnz     x9, #0x341ec
0x000341d4: c8 02 40 f9              ldr      x8, [x22]
0x000341d8: e0 03 16 aa              mov      x0, x22
0x000341dc: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x000341e0: 00 01 3f d6              blr      x8
0x000341e4: e0 03 16 aa              mov      x0, x22
0x000341e8: 5a f2 ff 97              bl       #0x30b50                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x000341ec: f6 53 41 f9              ldr      x22, [sp, #0x2a0]
0x000341f0: b6 01 00 b4              cbz      x22, #0x34224
0x000341f4: c8 22 00 91              add      x8, x22, #8
0x000341f8: 09 fd 5f c8              ldaxr    x9, [x8]
0x000341fc: 2a 05 00 d1              sub      x10, x9, #1
0x00034200: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00034204: ab ff ff 35              cbnz     w11, #0x341f8
0x00034208: e9 00 00 b5              cbnz     x9, #0x34224
0x0003420c: c8 02 40 f9              ldr      x8, [x22]
0x00034210: e0 03 16 aa              mov      x0, x22
0x00034214: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00034218: 00 01 3f d6              blr      x8
0x0003421c: e0 03 16 aa              mov      x0, x22
0x00034220: 4c f2 ff 97              bl       #0x30b50                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00034224: e8 03 4a 39              ldrb     w8, [sp, #0x280]
0x00034228: 68 00 00 36              tbz      w8, #0, #0x34234
0x0003422c: e0 4b 41 f9              ldr      x0, [sp, #0x290]
0x00034230: 9c ed ff 97              bl       #0x2f8a0                   ; -> _ZdlPv@plt
0x00034234: f6 33 41 f9              ldr      x22, [sp, #0x260]
0x00034238: b6 01 00 b4              cbz      x22, #0x3426c
0x0003423c: c8 22 00 91              add      x8, x22, #8
0x00034240: 09 fd 5f c8              ldaxr    x9, [x8]
0x00034244: 2a 05 00 d1              sub      x10, x9, #1
0x00034248: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x0003424c: ab ff ff 35              cbnz     w11, #0x34240
0x00034250: e9 00 00 b5              cbnz     x9, #0x3426c
0x00034254: c8 02 40 f9              ldr      x8, [x22]
0x00034258: e0 03 16 aa              mov      x0, x22
0x0003425c: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00034260: 00 01 3f d6              blr      x8
0x00034264: e0 03 16 aa              mov      x0, x22
0x00034268: 3a f2 ff 97              bl       #0x30b50                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x0003426c: f6 2b 41 f9              ldr      x22, [sp, #0x250]
0x00034270: b6 01 00 b4              cbz      x22, #0x342a4
0x00034274: c8 22 00 91              add      x8, x22, #8
0x00034278: 09 fd 5f c8              ldaxr    x9, [x8]
0x0003427c: 2a 05 00 d1              sub      x10, x9, #1
0x00034280: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00034284: ab ff ff 35              cbnz     w11, #0x34278
0x00034288: e9 00 00 b5              cbnz     x9, #0x342a4
0x0003428c: c8 02 40 f9              ldr      x8, [x22]
0x00034290: e0 03 16 aa              mov      x0, x22
0x00034294: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00034298: 00 01 3f d6              blr      x8
0x0003429c: e0 03 16 aa              mov      x0, x22
0x000342a0: 2c f2 ff 97              bl       #0x30b50                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x000342a4: e0 23 06 91              add      x0, sp, #0x188
0x000342a8: a6 f0 ff 97              bl       #0x30540                   ; -> _ZN6BYTENN6ConfigD2Ev@plt
0x000342ac: e0 23 00 91              add      x0, sp, #8
0x000342b0: a4 f0 ff 97              bl       #0x30540                   ; -> _ZN6BYTENN6ConfigD2Ev@plt
0x000342b4: 60 06 40 f9              ldr      x0, [x19, #8]
0x000342b8: c0 01 00 b4              cbz      x0, #0x342f0
0x000342bc: 08 00 40 f9              ldr      x8, [x0]
0x000342c0: e1 03 14 aa              mov      x1, x20
0x000342c4: 08 29 40 f9              ldr      x8, [x8, #0x50]
0x000342c8: 00 01 3f d6              blr      x8
0x000342cc: f6 03 00 2a              mov      w22, w0
0x000342d0: 60 3e 03 b9              str      w0, [x19, #0x33c]
0x000342d4: 0f 00 00 14              b        #0x34310                   ; -> _ZN6BYTENN16ByteNNEngineImpl4InitERKNS_9ConfigExtE+0x7b8
0x000342d8: a3 02 40 b9              ldr      w3, [x21]
0x000342dc: 61 0a 00 f0              adrp     x1, #0x183000
0x000342e0: 62 0a 00 f0              adrp     x2, #0x183000
0x000342e4: 21 e0 21 91              add      x1, x1, #0x878
0x000342e8: 42 80 25 91              add      x2, x2, #0x960
0x000342ec: 06 00 00 14              b        #0x34304                   ; -> _ZN6BYTENN16ByteNNEngineImpl4InitERKNS_9ConfigExtE+0x7ac
0x000342f0: a3 02 40 b9              ldr      w3, [x21]
0x000342f4: 61 0a 00 f0              adrp     x1, #0x183000
0x000342f8: 62 0a 00 f0              adrp     x2, #0x183000
0x000342fc: 21 e0 21 91              add      x1, x1, #0x878
0x00034300: 42 d8 26 91              add      x2, x2, #0x9b6
0x00034304: 20 00 80 52              mov      w0, #1
0x00034308: b6 f3 ff 97              bl       #0x311e0                   ; -> _ZN6BYTENN9ByteNNLogEiPKcS1_z@plt
0x0003430c: 16 01 80 52              mov      w22, #8
0x00034310: f3 ef 42 f9              ldr      x19, [sp, #0x5d8]
0x00034314: 08 43 00 91              add      x8, x24, #0x10
0x00034318: e8 13 02 f9              str      x8, [sp, #0x420]
0x0003431c: b3 01 00 b4              cbz      x19, #0x34350
0x00034320: 68 22 00 91              add      x8, x19, #8
0x00034324: 09 fd 5f c8              ldaxr    x9, [x8]
0x00034328: 2a 05 00 d1              sub      x10, x9, #1
0x0003432c: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00034330: ab ff ff 35              cbnz     w11, #0x34324
0x00034334: e9 00 00 b5              cbnz     x9, #0x34350
0x00034338: 68 02 40 f9              ldr      x8, [x19]
0x0003433c: e0 03 13 aa              mov      x0, x19
0x00034340: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00034344: 00 01 3f d6              blr      x8
0x00034348: e0 03 13 aa              mov      x0, x19
0x0003434c: 01 f2 ff 97              bl       #0x30b50                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00034350: e1 e3 42 f9              ldr      x1, [sp, #0x5c0]
0x00034354: f3 83 10 91              add      x19, sp, #0x420
0x00034358: 60 62 06 91              add      x0, x19, #0x198
0x0003435c: 69 02 00 94              bl       #0x34d00
0x00034360: e1 cb 42 f9              ldr      x1, [sp, #0x590]
0x00034364: 60 a2 05 91              add      x0, x19, #0x168
0x00034368: 2e f0 ff 97              bl       #0x30420                   ; -> _ZNSt6__ndk16__treeINS_12__value_typeINS_12basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEEENS_10shared_ptrIhEEEENS_19__map_value_compareIS7_SA_NS_4lessIS7_EELb1EEENS5_ISA_EEE7destroyEPNS_11__tree_nodeISA_PvEE@plt
0x0003436c: e1 bf 42 f9              ldr      x1, [sp, #0x578]
0x00034370: 60 42 05 91              add      x0, x19, #0x150
0x00034374: 2b f0 ff 97              bl       #0x30420                   ; -> _ZNSt6__ndk16__treeINS_12__value_typeINS_12basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEEENS_10shared_ptrIhEEEENS_19__map_value_compareIS7_SA_NS_4lessIS7_EELb1EEENS5_ISA_EEE7destroyEPNS_11__tree_nodeISA_PvEE@plt
0x00034378: e0 83 10 91              add      x0, sp, #0x420
0x0003437c: 6d f1 ff 97              bl       #0x30930                   ; -> _ZN6BYTENN9ConfigExtD2Ev@plt
0x00034380: e8 16 40 f9              ldr      x8, [x23, #0x28]
0x00034384: a9 83 5b f8              ldur     x9, [x29, #-0x48]
0x00034388: 1f 01 09 eb              cmp      x8, x9
0x0003438c: 21 01 00 54              b.ne     #0x343b0
0x00034390: e0 03 16 2a              mov      w0, w22
0x00034394: ff 03 18 91              add      sp, sp, #0x600
0x00034398: fd 7b 44 a9              ldp      x29, x30, [sp, #0x40]
0x0003439c: f4 4f 43 a9              ldp      x20, x19, [sp, #0x30]
0x000343a0: f6 57 42 a9              ldp      x22, x21, [sp, #0x20]
0x000343a4: f8 5f 41 a9              ldp      x24, x23, [sp, #0x10]
0x000343a8: fc 67 c5 a8              ldp      x28, x25, [sp], #0x50
0x000343ac: c0 03 5f d6              ret      
0x000343b0: e4 f3 ff 97              bl       #0x31340                   ; -> __stack_chk_fail@plt
