; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/kwpatch/libs/libykit_core_plugin.so
; target  : address 0x33e00
; start   : 0x33e00
; size    : 0x700
; bytes   : 0x700   instructions: 448
; generated: 2026-10-24T01:02:14

0x00033e00: f0 7b ff 97              bl       #0x12dc0                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00033e04: c8 02 40 f9              ldr      x8, [x22]
0x00033e08: e8 01 00 b4              cbz      x8, #0x33e44
0x00033e0c: 68 c2 49 39              ldrb     w8, [x19, #0x270]
0x00033e10: 74 c2 09 91              add      x20, x19, #0x270
0x00033e14: 08 02 00 37              tbnz     w8, #0, #0x33e54
0x00033e18: 82 06 00 91              add      x2, x20, #1
0x00033e1c: 0f 00 00 14              b        #0x33e58
0x00033e20: a8 02 40 f9              ldr      x8, [x21]
0x00033e24: e0 03 15 aa              mov      x0, x21
0x00033e28: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00033e2c: 00 01 3f d6              blr      x8
0x00033e30: e0 03 15 aa              mov      x0, x21
0x00033e34: e3 7b ff 97              bl       #0x12dc0                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00033e38: 60 1a 41 f9              ldr      x0, [x19, #0x230]
0x00033e3c: e0 f5 ff b5              cbnz     x0, #0x33cf8
0x00033e40: b4 00 00 14              b        #0x34110
0x00033e44: e0 c3 01 91              add      x0, sp, #0x70
0x00033e48: a0 e0 ff 97              bl       #0x2c0c8
0x00033e4c: e0 03 1f 2a              mov      w0, wzr
0x00033e50: b0 00 00 14              b        #0x34110
0x00033e54: 62 42 41 f9              ldr      x2, [x19, #0x280]
0x00033e58: 01 02 00 90              adrp     x1, #0x73000
0x00033e5c: 21 fc 26 91              add      x1, x1, #0x9bf
0x00033e60: 20 00 80 52              mov      w0, #1
0x00033e64: 8f 7d ff 97              bl       #0x134a0                   ; -> _ZN8kuaishou8westeros11WesterosLogENS0_16WesterosLogLevelEPKcz@plt
0x00033e68: 60 32 41 f9              ldr      x0, [x19, #0x260]
0x00033e6c: 75 1a 41 f9              ldr      x21, [x19, #0x230]
0x00033e70: 20 01 00 b4              cbz      x0, #0x33e94
0x00033e74: 68 02 09 91              add      x8, x19, #0x240
0x00033e78: 1f 01 00 eb              cmp      x8, x0
0x00033e7c: 00 01 00 54              b.eq     #0x33e9c
0x00033e80: 08 00 40 f9              ldr      x8, [x0]
0x00033e84: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00033e88: 00 01 3f d6              blr      x8
0x00033e8c: e0 33 00 f9              str      x0, [sp, #0x60]
0x00033e90: 09 00 00 14              b        #0x33eb4
0x00033e94: ff 33 00 f9              str      xzr, [sp, #0x60]
0x00033e98: 07 00 00 14              b        #0x33eb4
0x00033e9c: e8 03 01 91              add      x8, sp, #0x40
0x00033ea0: e8 33 00 f9              str      x8, [sp, #0x60]
0x00033ea4: 08 00 40 f9              ldr      x8, [x0]
0x00033ea8: 08 0d 40 f9              ldr      x8, [x8, #0x18]
0x00033eac: e1 03 01 91              add      x1, sp, #0x40
0x00033eb0: 00 01 3f d6              blr      x8
0x00033eb4: e1 03 01 91              add      x1, sp, #0x40
0x00033eb8: e0 03 15 aa              mov      x0, x21
0x00033ebc: e2 03 14 aa              mov      x2, x20
0x00033ec0: f8 03 01 91              add      x24, sp, #0x40
0x00033ec4: c7 7e ff 97              bl       #0x139e0                   ; -> _ZN5ycnn213YCNNModelPost22setUploadStatsCallBackENSt6__ndk18functionIFvRKNS1_12basic_stringIcNS1_11char_traitsIcEENS1_9allocatorIcEEEEEEESA_@plt
0x00033ec8: e0 33 40 f9              ldr      x0, [sp, #0x60]
0x00033ecc: 1f 03 00 eb              cmp      x24, x0
0x00033ed0: a0 00 00 54              b.eq     #0x33ee4
0x00033ed4: e0 00 00 b4              cbz      x0, #0x33ef0
0x00033ed8: 08 00 40 f9              ldr      x8, [x0]
0x00033edc: 08 15 40 f9              ldr      x8, [x8, #0x28]
0x00033ee0: 03 00 00 14              b        #0x33eec
0x00033ee4: 08 00 40 f9              ldr      x8, [x0]
0x00033ee8: 08 11 40 f9              ldr      x8, [x8, #0x20]
0x00033eec: 00 01 3f d6              blr      x8
0x00033ef0: e0 c3 01 91              add      x0, sp, #0x70
0x00033ef4: 75 e0 ff 97              bl       #0x2c0c8
0x00033ef8: c0 02 40 f9              ldr      x0, [x22]
0x00033efc: e8 c3 01 91              add      x8, sp, #0x70
0x00033f00: 50 7e ff 97              bl       #0x13840                   ; -> _ZN5ycnn213YCNNModelPost13getSystemInfoEv@plt
0x00033f04: 60 52 40 f9              ldr      x0, [x19, #0xa0]
0x00033f08: e8 23 00 91              add      x8, sp, #8
0x00033f0c: 41 7c ff 97              bl       #0x13010                   ; -> _ZN8kuaishou8westeros15WesterosContext15device_propertyEv@plt
0x00033f10: e0 07 40 f9              ldr      x0, [sp, #8]
0x00033f14: 08 00 40 f9              ldr      x8, [x0]
0x00033f18: 09 11 40 f9              ldr      x9, [x8, #0x20]
0x00033f1c: e8 63 00 91              add      x8, sp, #0x18
0x00033f20: 20 01 3f d6              blr      x9
0x00033f24: e8 c3 41 39              ldrb     w8, [sp, #0x70]
0x00033f28: 68 00 00 37              tbnz     w8, #0, #0x33f34
0x00033f2c: ff e3 00 79              strh     wzr, [sp, #0x70]
0x00033f30: 09 00 00 14              b        #0x33f54
0x00033f34: e8 43 40 f9              ldr      x8, [sp, #0x80]
0x00033f38: 1f 01 00 39              strb     wzr, [x8]
0x00033f3c: e8 c3 41 39              ldrb     w8, [sp, #0x70]
0x00033f40: ff 3f 00 f9              str      xzr, [sp, #0x78]
0x00033f44: 88 00 00 36              tbz      w8, #0, #0x33f54
0x00033f48: e0 43 40 f9              ldr      x0, [sp, #0x80]
0x00033f4c: 05 7c ff 97              bl       #0x12f60                   ; -> _ZdlPv@plt
0x00033f50: ff 3b 00 f9              str      xzr, [sp, #0x70]
0x00033f54: e0 83 c1 3c              ldur     q0, [sp, #0x18]
0x00033f58: e8 17 40 f9              ldr      x8, [sp, #0x28]
0x00033f5c: f4 0b 40 f9              ldr      x20, [sp, #0x10]
0x00033f60: ff ff 01 a9              stp      xzr, xzr, [sp, #0x18]
0x00033f64: e0 1f 80 3d              str      q0, [sp, #0x70]
0x00033f68: e8 43 00 f9              str      x8, [sp, #0x80]
0x00033f6c: ff 17 00 f9              str      xzr, [sp, #0x28]
0x00033f70: b4 01 00 b4              cbz      x20, #0x33fa4
0x00033f74: 88 22 00 91              add      x8, x20, #8
0x00033f78: 09 fd 5f c8              ldaxr    x9, [x8]
0x00033f7c: 2a 05 00 d1              sub      x10, x9, #1
0x00033f80: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00033f84: ab ff ff 35              cbnz     w11, #0x33f78
0x00033f88: e9 00 00 b5              cbnz     x9, #0x33fa4
0x00033f8c: 88 02 40 f9              ldr      x8, [x20]
0x00033f90: e0 03 14 aa              mov      x0, x20
0x00033f94: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00033f98: 00 01 3f d6              blr      x8
0x00033f9c: e0 03 14 aa              mov      x0, x20
0x00033fa0: 88 7b ff 97              bl       #0x12dc0                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00033fa4: 60 52 40 f9              ldr      x0, [x19, #0xa0]
0x00033fa8: e8 23 00 91              add      x8, sp, #8
0x00033fac: 19 7c ff 97              bl       #0x13010                   ; -> _ZN8kuaishou8westeros15WesterosContext15device_propertyEv@plt
0x00033fb0: e0 07 40 f9              ldr      x0, [sp, #8]
0x00033fb4: 08 00 40 f9              ldr      x8, [x0]
0x00033fb8: 09 0d 40 f9              ldr      x9, [x8, #0x18]
0x00033fbc: e8 63 00 91              add      x8, sp, #0x18
0x00033fc0: 20 01 3f d6              blr      x9
0x00033fc4: e8 23 42 39              ldrb     w8, [sp, #0x88]
0x00033fc8: e9 c3 01 91              add      x9, sp, #0x70
0x00033fcc: 35 61 00 91              add      x21, x9, #0x18
0x00033fd0: 68 00 00 37              tbnz     w8, #0, #0x33fdc
0x00033fd4: ff 13 01 79              strh     wzr, [sp, #0x88]
0x00033fd8: 09 00 00 14              b        #0x33ffc
0x00033fdc: e8 4f 40 f9              ldr      x8, [sp, #0x98]
0x00033fe0: 1f 01 00 39              strb     wzr, [x8]
0x00033fe4: e8 23 42 39              ldrb     w8, [sp, #0x88]
0x00033fe8: ff 4b 00 f9              str      xzr, [sp, #0x90]
0x00033fec: 88 00 00 36              tbz      w8, #0, #0x33ffc
0x00033ff0: e0 4f 40 f9              ldr      x0, [sp, #0x98]
0x00033ff4: db 7b ff 97              bl       #0x12f60                   ; -> _ZdlPv@plt
0x00033ff8: ff 47 00 f9              str      xzr, [sp, #0x88]
0x00033ffc: e8 17 40 f9              ldr      x8, [sp, #0x28]
0x00034000: e0 83 c1 3c              ldur     q0, [sp, #0x18]
0x00034004: f4 0b 40 f9              ldr      x20, [sp, #0x10]
0x00034008: ff 7f 02 a9              stp      xzr, xzr, [sp, #0x20]
0x0003400c: a8 0a 00 f9              str      x8, [x21, #0x10]
0x00034010: a0 02 80 3d              str      q0, [x21]
0x00034014: ff 0f 00 f9              str      xzr, [sp, #0x18]
0x00034018: b4 01 00 b4              cbz      x20, #0x3404c
0x0003401c: 88 22 00 91              add      x8, x20, #8
0x00034020: 09 fd 5f c8              ldaxr    x9, [x8]
0x00034024: 2a 05 00 d1              sub      x10, x9, #1
0x00034028: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x0003402c: ab ff ff 35              cbnz     w11, #0x34020
0x00034030: e9 00 00 b5              cbnz     x9, #0x3404c
0x00034034: 88 02 40 f9              ldr      x8, [x20]
0x00034038: e0 03 14 aa              mov      x0, x20
0x0003403c: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00034040: 00 01 3f d6              blr      x8
0x00034044: e0 03 14 aa              mov      x0, x20
0x00034048: 5e 7b ff 97              bl       #0x12dc0                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x0003404c: c0 02 40 f9              ldr      x0, [x22]
0x00034050: e1 c3 01 91              add      x1, sp, #0x70
0x00034054: 33 7c ff 97              bl       #0x13120                   ; -> _ZN5ycnn213YCNNModelPost13setSystemInfoERKNS_14YCNNSystemInfoE@plt
0x00034058: 74 46 41 f9              ldr      x20, [x19, #0x288]
0x0003405c: 78 4a 41 f9              ldr      x24, [x19, #0x290]
0x00034060: 9f 02 18 eb              cmp      x20, x24
0x00034064: 20 04 00 54              b.eq     #0x340e8
0x00034068: f5 01 00 f0              adrp     x21, #0x73000
0x0003406c: b5 b2 28 91              add      x21, x21, #0xa2c
0x00034070: 88 02 40 39              ldrb     w8, [x20]
0x00034074: 62 2a 42 b9              ldr      w2, [x19, #0x228]
0x00034078: 68 00 00 37              tbnz     w8, #0, #0x34084
0x0003407c: 83 06 00 91              add      x3, x20, #1
0x00034080: 02 00 00 14              b        #0x34088
0x00034084: 83 0a 40 f9              ldr      x3, [x20, #0x10]
0x00034088: 20 00 80 52              mov      w0, #1
0x0003408c: e1 03 15 aa              mov      x1, x21
0x00034090: 04 7d ff 97              bl       #0x134a0                   ; -> _ZN8kuaishou8westeros11WesterosLogENS0_16WesterosLogLevelEPKcz@plt
0x00034094: c0 02 40 f9              ldr      x0, [x22]
0x00034098: e1 03 14 aa              mov      x1, x20
0x0003409c: c9 7a ff 97              bl       #0x12bc0                   ; -> _ZN5ycnn213YCNNModelPost12setParamInfoERKNSt6__ndk112basic_stringIcNS1_11char_traitsIcEENS1_9allocatorIcEEEE@plt
0x000340a0: 94 62 00 91              add      x20, x20, #0x18
0x000340a4: 1f 03 14 eb              cmp      x24, x20
0x000340a8: 41 fe ff 54              b.ne     #0x34070
0x000340ac: 74 46 41 f9              ldr      x20, [x19, #0x288]
0x000340b0: 68 4a 41 f9              ldr      x8, [x19, #0x290]
0x000340b4: 1f 01 14 eb              cmp      x8, x20
0x000340b8: 80 01 00 54              b.eq     #0x340e8
0x000340bc: f5 03 08 aa              mov      x21, x8
0x000340c0: a9 8e 5e 38              ldrb     w9, [x21, #-0x18]!
0x000340c4: c9 00 00 37              tbnz     w9, #0, #0x340dc
0x000340c8: 9f 02 15 eb              cmp      x20, x21
0x000340cc: e8 03 15 aa              mov      x8, x21
0x000340d0: c0 00 00 54              b.eq     #0x340e8
0x000340d4: a9 8e 5e 38              ldrb     w9, [x21, #-0x18]!
0x000340d8: 89 ff 07 36              tbz      w9, #0, #0x340c8
0x000340dc: 00 81 5f f8              ldur     x0, [x8, #-8]
0x000340e0: a0 7b ff 97              bl       #0x12f60                   ; -> _ZdlPv@plt
0x000340e4: f9 ff ff 17              b        #0x340c8
0x000340e8: 74 4a 01 f9              str      x20, [x19, #0x290]
0x000340ec: e8 23 42 39              ldrb     w8, [sp, #0x88]
0x000340f0: 68 00 00 36              tbz      w8, #0, #0x340fc
0x000340f4: e0 4f 40 f9              ldr      x0, [sp, #0x98]
0x000340f8: 9a 7b ff 97              bl       #0x12f60                   ; -> _ZdlPv@plt
0x000340fc: e8 c3 41 39              ldrb     w8, [sp, #0x70]
0x00034100: 68 00 00 36              tbz      w8, #0, #0x3410c
0x00034104: e0 43 40 f9              ldr      x0, [sp, #0x80]
0x00034108: 96 7b ff 97              bl       #0x12f60                   ; -> _ZdlPv@plt
0x0003410c: 20 00 80 52              mov      w0, #1
0x00034110: e8 16 40 f9              ldr      x8, [x23, #0x28]
0x00034114: a9 83 5b f8              ldur     x9, [x29, #-0x48]
0x00034118: 1f 01 09 eb              cmp      x8, x9
0x0003411c: 01 01 00 54              b.ne     #0x3413c
0x00034120: fd 7b 5a a9              ldp      x29, x30, [sp, #0x1a0]
0x00034124: f4 4f 59 a9              ldp      x20, x19, [sp, #0x190]
0x00034128: f6 57 58 a9              ldp      x22, x21, [sp, #0x180]
0x0003412c: f8 5f 57 a9              ldp      x24, x23, [sp, #0x170]
0x00034130: fc b3 40 f9              ldr      x28, [sp, #0x160]
0x00034134: ff c3 06 91              add      sp, sp, #0x1b0
0x00034138: c0 03 5f d6              ret      
0x0003413c: 45 7b ff 97              bl       #0x12e50                   ; -> __stack_chk_fail@plt
0x00034140: e8 33 40 f9              ldr      x8, [sp, #0x60]
0x00034144: f3 03 00 aa              mov      x19, x0
0x00034148: 1f 03 08 eb              cmp      x24, x8
0x0003414c: 81 00 00 54              b.ne     #0x3415c
0x00034150: 09 01 40 f9              ldr      x9, [x8]
0x00034154: 29 11 40 f9              ldr      x9, [x9, #0x20]
0x00034158: 04 00 00 14              b        #0x34168
0x0003415c: 28 09 00 b4              cbz      x8, #0x34280
0x00034160: 09 01 40 f9              ldr      x9, [x8]
0x00034164: 29 15 40 f9              ldr      x9, [x9, #0x28]
0x00034168: e0 03 08 aa              mov      x0, x8
0x0003416c: 20 01 3f d6              blr      x9
0x00034170: 44 00 00 14              b        #0x34280
0x00034174: f3 03 00 aa              mov      x19, x0
0x00034178: 54 08 00 b4              cbz      x20, #0x34280
0x0003417c: 88 02 40 f9              ldr      x8, [x20]
0x00034180: e0 03 14 aa              mov      x0, x20
0x00034184: 08 05 40 f9              ldr      x8, [x8, #8]
0x00034188: 00 01 3f d6              blr      x8
0x0003418c: 3d 00 00 14              b        #0x34280
0x00034190: 3b 00 00 14              b        #0x3427c
0x00034194: f4 1f 40 f9              ldr      x20, [sp, #0x38]
0x00034198: f3 03 00 aa              mov      x19, x0
0x0003419c: 34 07 00 b4              cbz      x20, #0x34280
0x000341a0: 88 22 00 91              add      x8, x20, #8
0x000341a4: 09 fd 5f c8              ldaxr    x9, [x8]
0x000341a8: 2a 05 00 d1              sub      x10, x9, #1
0x000341ac: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x000341b0: ab ff ff 35              cbnz     w11, #0x341a4
0x000341b4: 69 06 00 b5              cbnz     x9, #0x34280
0x000341b8: 88 02 40 f9              ldr      x8, [x20]
0x000341bc: e0 03 14 aa              mov      x0, x20
0x000341c0: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x000341c4: 00 01 3f d6              blr      x8
0x000341c8: e0 03 14 aa              mov      x0, x20
0x000341cc: fd 7a ff 97              bl       #0x12dc0                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x000341d0: 2c 00 00 14              b        #0x34280
0x000341d4: f3 03 00 aa              mov      x19, x0
0x000341d8: e0 03 14 aa              mov      x0, x20
0x000341dc: a5 7e ff 97              bl       #0x13c70                   ; -> _ZNSt6__ndk119__shared_weak_countD2Ev@plt
0x000341e0: e0 03 14 aa              mov      x0, x20
0x000341e4: 22 00 00 14              b        #0x3426c
0x000341e8: 2a 00 00 14              b        #0x34290
0x000341ec: f4 0b 40 f9              ldr      x20, [sp, #0x10]
0x000341f0: f3 03 00 aa              mov      x19, x0
0x000341f4: 14 05 00 b4              cbz      x20, #0x34294
0x000341f8: 88 22 00 91              add      x8, x20, #8
0x000341fc: 09 fd 5f c8              ldaxr    x9, [x8]
0x00034200: 2a 05 00 d1              sub      x10, x9, #1
0x00034204: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00034208: ab ff ff 35              cbnz     w11, #0x341fc
0x0003420c: 0a 00 00 14              b        #0x34234
0x00034210: 20 00 00 14              b        #0x34290
0x00034214: f4 0b 40 f9              ldr      x20, [sp, #0x10]
0x00034218: f3 03 00 aa              mov      x19, x0
0x0003421c: d4 03 00 b4              cbz      x20, #0x34294
0x00034220: 88 22 00 91              add      x8, x20, #8
0x00034224: 09 fd 5f c8              ldaxr    x9, [x8]
0x00034228: 2a 05 00 d1              sub      x10, x9, #1
0x0003422c: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00034230: ab ff ff 35              cbnz     w11, #0x34224
0x00034234: 09 03 00 b5              cbnz     x9, #0x34294
0x00034238: 88 02 40 f9              ldr      x8, [x20]
0x0003423c: e0 03 14 aa              mov      x0, x20
0x00034240: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00034244: 00 01 3f d6              blr      x8
0x00034248: e0 03 14 aa              mov      x0, x20
0x0003424c: dd 7a ff 97              bl       #0x12dc0                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00034250: e8 23 42 39              ldrb     w8, [sp, #0x88]
0x00034254: 48 02 00 36              tbz      w8, #0, #0x3429c
0x00034258: e0 4f 40 f9              ldr      x0, [sp, #0x98]
0x0003425c: 41 7b ff 97              bl       #0x12f60                   ; -> _ZdlPv@plt
0x00034260: e8 c3 41 39              ldrb     w8, [sp, #0x70]
0x00034264: 08 02 00 36              tbz      w8, #0, #0x342a4
0x00034268: e0 43 40 f9              ldr      x0, [sp, #0x80]
0x0003426c: 3d 7b ff 97              bl       #0x12f60                   ; -> _ZdlPv@plt
0x00034270: e0 03 13 aa              mov      x0, x19
0x00034274: 60 ea 00 94              bl       #0x6ebf4
0x00034278: 06 00 00 14              b        #0x34290
0x0003427c: f3 03 00 aa              mov      x19, x0
0x00034280: e0 c3 01 91              add      x0, sp, #0x70
0x00034284: 91 df ff 97              bl       #0x2c0c8
0x00034288: e0 03 13 aa              mov      x0, x19
0x0003428c: 5a ea 00 94              bl       #0x6ebf4
0x00034290: f3 03 00 aa              mov      x19, x0
0x00034294: e8 23 42 39              ldrb     w8, [sp, #0x88]
0x00034298: 08 fe 07 37              tbnz     w8, #0, #0x34258
0x0003429c: e8 c3 41 39              ldrb     w8, [sp, #0x70]
0x000342a0: 48 fe 07 37              tbnz     w8, #0, #0x34268
0x000342a4: e0 03 13 aa              mov      x0, x19
0x000342a8: 53 ea 00 94              bl       #0x6ebf4
0x000342ac: ff 03 02 d1              sub      sp, sp, #0x80
0x000342b0: e8 23 00 fd              str      d8, [sp, #0x40]
0x000342b4: f6 57 05 a9              stp      x22, x21, [sp, #0x50]
0x000342b8: f4 4f 06 a9              stp      x20, x19, [sp, #0x60]
0x000342bc: fd 7b 07 a9              stp      x29, x30, [sp, #0x70]
0x000342c0: fd c3 01 91              add      x29, sp, #0x70
0x000342c4: 55 d0 3b d5              mrs      x21, tpidr_el0
0x000342c8: a8 16 40 f9              ldr      x8, [x21, #0x28]
0x000342cc: f3 03 00 aa              mov      x19, x0
0x000342d0: f4 03 01 2a              mov      w20, w1
0x000342d4: e8 1f 00 f9              str      x8, [sp, #0x38]
0x000342d8: 00 44 40 f9              ldr      x0, [x0, #0x88]
0x000342dc: 40 00 00 b5              cbnz     x0, #0x342e4
0x000342e0: 60 4e 40 f9              ldr      x0, [x19, #0x98]
0x000342e4: 08 00 40 f9              ldr      x8, [x0]
0x000342e8: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000342ec: 00 01 3f d6              blr      x8
0x000342f0: 08 00 40 f9              ldr      x8, [x0]
0x000342f4: 21 00 80 52              mov      w1, #1
0x000342f8: 36 00 80 52              mov      w22, #1
0x000342fc: 08 2d 40 f9              ldr      x8, [x8, #0x58]
0x00034300: 00 01 3f d6              blr      x8
0x00034304: 68 b2 08 91              add      x8, x19, #0x22c
0x00034308: 69 2a 0b 91              add      x9, x19, #0x2ca
0x0003430c: 60 22 01 91              add      x0, x19, #0x48
0x00034310: 21 00 80 52              mov      w1, #1
0x00034314: 16 fd 9f 08              stlrb    w22, [x8]
0x00034318: 3f fd 9f 08              stlrb    wzr, [x9]
0x0003431c: d5 7a ff 97              bl       #0x12e70                   ; -> _ZN8kuaishou8westeros19WesterosModuleStats16StatsMarkEnabledEb@plt
0x00034320: 60 52 40 f9              ldr      x0, [x19, #0xa0]
0x00034324: a8 99 99 52              mov      w8, #0xcccd
0x00034328: 49 e1 9a 52              mov      w9, #0xd70a
0x0003432c: 88 b9 a7 72              movk     w8, #0x3dcc, lsl #16
0x00034330: 69 84 a7 72              movk     w9, #0x3c23, lsl #16
0x00034334: 9f 6a 10 71              cmp      w20, #0x41a
0x00034338: 00 01 27 1e              fmov     s0, w8
0x0003433c: 21 01 27 1e              fmov     s1, w9
0x00034340: e8 43 00 91              add      x8, sp, #0x10
0x00034344: 28 0c 20 1e              fcsel    s8, s1, s0, eq
0x00034348: 9e 7b ff 97              bl       #0x131c0                   ; -> _ZN8kuaishou8westeros15WesterosContext12deps_managerEv@plt
0x0003434c: f4 0b 40 f9              ldr      x20, [sp, #0x10]
0x00034350: e8 23 00 bd              str      s8, [sp, #0x20]
0x00034354: f3 17 00 f9              str      x19, [sp, #0x28]
0x00034358: ff 33 00 b9              str      wzr, [sp, #0x30]
0x0003435c: e8 03 00 91              mov      x8, sp
0x00034360: e0 83 00 91              add      x0, sp, #0x20
0x00034364: 8b 7d ff 97              bl       #0x13990                   ; -> _ZN8kuaishou8westeros3Any6CreateINS0_12SensorConfigEEES1_T_@plt
0x00034368: 88 02 40 f9              ldr      x8, [x20]
0x0003436c: 08 1d 40 f9              ldr      x8, [x8, #0x38]
0x00034370: e2 03 00 91              mov      x2, sp
0x00034374: 21 00 80 52              mov      w1, #1
0x00034378: e0 03 14 aa              mov      x0, x20
0x0003437c: 00 01 3f d6              blr      x8
0x00034380: f3 07 40 f9              ldr      x19, [sp, #8]
0x00034384: b3 01 00 b4              cbz      x19, #0x343b8
0x00034388: 68 22 00 91              add      x8, x19, #8
0x0003438c: 09 fd 5f c8              ldaxr    x9, [x8]
0x00034390: 2a 05 00 d1              sub      x10, x9, #1
0x00034394: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00034398: ab ff ff 35              cbnz     w11, #0x3438c
0x0003439c: e9 00 00 b5              cbnz     x9, #0x343b8
0x000343a0: 68 02 40 f9              ldr      x8, [x19]
0x000343a4: e0 03 13 aa              mov      x0, x19
0x000343a8: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x000343ac: 00 01 3f d6              blr      x8
0x000343b0: e0 03 13 aa              mov      x0, x19
0x000343b4: 83 7a ff 97              bl       #0x12dc0                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x000343b8: f3 0f 40 f9              ldr      x19, [sp, #0x18]
0x000343bc: b3 01 00 b4              cbz      x19, #0x343f0
0x000343c0: 68 22 00 91              add      x8, x19, #8
0x000343c4: 09 fd 5f c8              ldaxr    x9, [x8]
0x000343c8: 2a 05 00 d1              sub      x10, x9, #1
0x000343cc: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x000343d0: ab ff ff 35              cbnz     w11, #0x343c4
0x000343d4: e9 00 00 b5              cbnz     x9, #0x343f0
0x000343d8: 68 02 40 f9              ldr      x8, [x19]
0x000343dc: e0 03 13 aa              mov      x0, x19
0x000343e0: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x000343e4: 00 01 3f d6              blr      x8
0x000343e8: e0 03 13 aa              mov      x0, x19
0x000343ec: 75 7a ff 97              bl       #0x12dc0                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x000343f0: a8 16 40 f9              ldr      x8, [x21, #0x28]
0x000343f4: e9 1f 40 f9              ldr      x9, [sp, #0x38]
0x000343f8: 1f 01 09 eb              cmp      x8, x9
0x000343fc: e1 00 00 54              b.ne     #0x34418
0x00034400: fd 7b 47 a9              ldp      x29, x30, [sp, #0x70]
0x00034404: f4 4f 46 a9              ldp      x20, x19, [sp, #0x60]
0x00034408: f6 57 45 a9              ldp      x22, x21, [sp, #0x50]
0x0003440c: e8 23 40 fd              ldr      d8, [sp, #0x40]
0x00034410: ff 03 02 91              add      sp, sp, #0x80
0x00034414: c0 03 5f d6              ret      
0x00034418: 8e 7a ff 97              bl       #0x12e50                   ; -> __stack_chk_fail@plt
0x0003441c: f4 07 40 f9              ldr      x20, [sp, #8]
0x00034420: f3 03 00 aa              mov      x19, x0
0x00034424: 34 02 00 b4              cbz      x20, #0x34468
0x00034428: 88 22 00 91              add      x8, x20, #8
0x0003442c: 09 fd 5f c8              ldaxr    x9, [x8]
0x00034430: 2a 05 00 d1              sub      x10, x9, #1
0x00034434: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00034438: ab ff ff 35              cbnz     w11, #0x3442c
0x0003443c: 69 01 00 b5              cbnz     x9, #0x34468
0x00034440: 88 02 40 f9              ldr      x8, [x20]
0x00034444: e0 03 14 aa              mov      x0, x20
0x00034448: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x0003444c: 00 01 3f d6              blr      x8
0x00034450: e0 03 14 aa              mov      x0, x20
0x00034454: 5b 7a ff 97              bl       #0x12dc0                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00034458: f4 0f 40 f9              ldr      x20, [sp, #0x18]
0x0003445c: b4 00 00 b5              cbnz     x20, #0x34470
0x00034460: 10 00 00 14              b        #0x344a0
0x00034464: f3 03 00 aa              mov      x19, x0
0x00034468: f4 0f 40 f9              ldr      x20, [sp, #0x18]
0x0003446c: b4 01 00 b4              cbz      x20, #0x344a0
0x00034470: 88 22 00 91              add      x8, x20, #8
0x00034474: 09 fd 5f c8              ldaxr    x9, [x8]
0x00034478: 2a 05 00 d1              sub      x10, x9, #1
0x0003447c: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00034480: ab ff ff 35              cbnz     w11, #0x34474
0x00034484: e9 00 00 b5              cbnz     x9, #0x344a0
0x00034488: 88 02 40 f9              ldr      x8, [x20]
0x0003448c: e0 03 14 aa              mov      x0, x20
0x00034490: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00034494: 00 01 3f d6              blr      x8
0x00034498: e0 03 14 aa              mov      x0, x20
0x0003449c: 49 7a ff 97              bl       #0x12dc0                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x000344a0: e0 03 13 aa              mov      x0, x19
0x000344a4: d4 e9 00 94              bl       #0x6ebf4
0x000344a8: 08 28 42 b9              ldr      w8, [x0, #0x228]
0x000344ac: 1f 01 01 6b              cmp      w8, w1
0x000344b0: a1 00 00 54              b.ne     #0x344c4
0x000344b4: 08 b0 08 91              add      x8, x0, #0x22c
0x000344b8: 08 fd df 08              ldarb    w8, [x8]
0x000344bc: 00 01 00 12              and      w0, w8, #1
0x000344c0: c0 03 5f d6              ret      
0x000344c4: e0 03 1f 2a              mov      w0, wzr
0x000344c8: c0 03 5f d6              ret      
0x000344cc: ff c3 02 d1              sub      sp, sp, #0xb0
0x000344d0: f6 57 08 a9              stp      x22, x21, [sp, #0x80]
0x000344d4: f4 4f 09 a9              stp      x20, x19, [sp, #0x90]
0x000344d8: fd 7b 0a a9              stp      x29, x30, [sp, #0xa0]
0x000344dc: fd 83 02 91              add      x29, sp, #0xa0
0x000344e0: 55 d0 3b d5              mrs      x21, tpidr_el0
0x000344e4: a8 16 40 f9              ldr      x8, [x21, #0x28]
0x000344e8: 09 b0 08 91              add      x9, x0, #0x22c
0x000344ec: 0a 28 0b 91              add      x10, x0, #0x2ca
0x000344f0: f3 03 00 aa              mov      x19, x0
0x000344f4: a8 83 1d f8              stur     x8, [x29, #-0x28]
0x000344f8: 3f fd 9f 08              stlrb    wzr, [x9]
0x000344fc: 5f fd 9f 08              stlrb    wzr, [x10]
