; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_musically/native/libbytenn.so
; target  : _ZN6BYTENN23ByteNNInternalConfigExt22CheckFp16AndResetModelEv (0x37bb4)
; start   : 0x37bb4
; size    : 0x3c8
; bytes   : 0x3c8   instructions: 242
; generated: 2026-10-23T23:33:31

0x00037bb4: ff 83 02 d1              sub      sp, sp, #0xa0
0x00037bb8: fc 6f 04 a9              stp      x28, x27, [sp, #0x40]
0x00037bbc: fa 67 05 a9              stp      x26, x25, [sp, #0x50]
0x00037bc0: f8 5f 06 a9              stp      x24, x23, [sp, #0x60]
0x00037bc4: f6 57 07 a9              stp      x22, x21, [sp, #0x70]
0x00037bc8: f4 4f 08 a9              stp      x20, x19, [sp, #0x80]
0x00037bcc: fd 7b 09 a9              stp      x29, x30, [sp, #0x90]
0x00037bd0: fd 43 02 91              add      x29, sp, #0x90
0x00037bd4: 49 d0 3b d5              mrs      x9, tpidr_el0
0x00037bd8: 28 15 40 f9              ldr      x8, [x9, #0x28]
0x00037bdc: e8 1f 00 f9              str      x8, [sp, #0x38]
0x00037be0: 08 bc 40 f9              ldr      x8, [x0, #0x178]
0x00037be4: 08 1b 00 b4              cbz      x8, #0x37f44
0x00037be8: 1c b4 40 f9              ldr      x28, [x0, #0x168]
0x00037bec: 1b c0 05 91              add      x27, x0, #0x170
0x00037bf0: f3 03 00 aa              mov      x19, x0
0x00037bf4: e9 07 00 f9              str      x9, [sp, #8]
0x00037bf8: 7f 03 1c eb              cmp      x27, x28
0x00037bfc: 20 19 00 54              b.eq     #0x37f20
0x00037c00: e8 63 00 91              add      x8, sp, #0x18
0x00037c04: b9 88 8b 52              mov      w25, #0x5c45
0x00037c08: 74 42 05 91              add      x20, x19, #0x150
0x00037c0c: 75 62 06 91              add      x21, x19, #0x198
0x00037c10: d9 4d a1 72              movk     w25, #0xa6e, lsl #16
0x00037c14: 08 01 40 b2              orr      x8, x8, #1
0x00037c18: e8 0b 00 f9              str      x8, [sp, #0x10]
0x00037c1c: 04 00 00 14              b        #0x37c2c                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt22CheckFp16AndResetModelEv+0x78
0x00037c20: fc 03 09 aa              mov      x28, x9
0x00037c24: 7f 03 1c eb              cmp      x27, x28
0x00037c28: c0 17 00 54              b.eq     #0x37f20
0x00037c2c: 96 83 00 91              add      x22, x28, #0x20
0x00037c30: e1 63 00 91              add      x1, sp, #0x18
0x00037c34: e0 03 14 aa              mov      x0, x20
0x00037c38: e2 03 16 aa              mov      x2, x22
0x00037c3c: 09 e4 ff 97              bl       #0x30c60                   ; -> _ZNSt6__ndk16__treeINS_12__value_typeINS_12basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEEENS_10shared_ptrIhEEEENS_19__map_value_compareIS7_SA_NS_4lessIS7_EELb1EEENS5_ISA_EEE12__find_equalIS7_EERPNS_16__tree_node_baseIPvEERPNS_15__tree_end_nodeISL_EERKT_@plt
0x00037c40: 17 00 40 f9              ldr      x23, [x0]
0x00037c44: 97 00 00 b4              cbz      x23, #0x37c54
0x00037c48: 89 a3 43 a9              ldp      x9, x8, [x28, #0x38]
0x00037c4c: 88 03 00 b5              cbnz     x8, #0x37cbc
0x00037c50: 20 00 00 14              b        #0x37cd0                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt22CheckFp16AndResetModelEv+0x11c
0x00037c54: f8 03 00 aa              mov      x24, x0
0x00037c58: 00 09 80 52              mov      w0, #0x48
0x00037c5c: fd e4 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00037c60: f7 03 00 aa              mov      x23, x0
0x00037c64: 00 80 00 91              add      x0, x0, #0x20
0x00037c68: e1 03 16 aa              mov      x1, x22
0x00037c6c: 7c ed ff 97              bl       #0x3325c
0x00037c70: e8 0f 40 f9              ldr      x8, [sp, #0x18]
0x00037c74: ff fe 03 a9              stp      xzr, xzr, [x23, #0x38]
0x00037c78: ff 7e 00 a9              stp      xzr, xzr, [x23]
0x00037c7c: e8 0a 00 f9              str      x8, [x23, #0x10]
0x00037c80: 17 03 00 f9              str      x23, [x24]
0x00037c84: 88 02 40 f9              ldr      x8, [x20]
0x00037c88: 08 01 40 f9              ldr      x8, [x8]
0x00037c8c: 88 00 00 b4              cbz      x8, #0x37c9c
0x00037c90: 88 02 00 f9              str      x8, [x20]
0x00037c94: 01 03 40 f9              ldr      x1, [x24]
0x00037c98: 02 00 00 14              b        #0x37ca0                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt22CheckFp16AndResetModelEv+0xec
0x00037c9c: e1 03 17 aa              mov      x1, x23
0x00037ca0: 60 ae 40 f9              ldr      x0, [x19, #0x158]
0x00037ca4: 89 f5 ff 97              bl       #0x352c8
0x00037ca8: 68 b2 40 f9              ldr      x8, [x19, #0x160]
0x00037cac: 08 05 00 91              add      x8, x8, #1
0x00037cb0: 68 b2 00 f9              str      x8, [x19, #0x160]
0x00037cb4: 89 a3 43 a9              ldp      x9, x8, [x28, #0x38]
0x00037cb8: c8 00 00 b4              cbz      x8, #0x37cd0
0x00037cbc: 0a 21 00 91              add      x10, x8, #8
0x00037cc0: 4b 7d 5f c8              ldxr     x11, [x10]
0x00037cc4: 6b 05 00 91              add      x11, x11, #1
0x00037cc8: 4b 7d 0c c8              stxr     w12, x11, [x10]
0x00037ccc: ac ff ff 35              cbnz     w12, #0x37cc0
0x00037cd0: f8 22 40 f9              ldr      x24, [x23, #0x40]
0x00037cd4: e9 a2 03 a9              stp      x9, x8, [x23, #0x38]
0x00037cd8: b8 01 00 b4              cbz      x24, #0x37d0c
0x00037cdc: 08 23 00 91              add      x8, x24, #8
0x00037ce0: 09 fd 5f c8              ldaxr    x9, [x8]
0x00037ce4: 2a 05 00 d1              sub      x10, x9, #1
0x00037ce8: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00037cec: ab ff ff 35              cbnz     w11, #0x37ce0
0x00037cf0: e9 00 00 b5              cbnz     x9, #0x37d0c
0x00037cf4: 08 03 40 f9              ldr      x8, [x24]
0x00037cf8: e0 03 18 aa              mov      x0, x24
0x00037cfc: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00037d00: 00 01 3f d6              blr      x8
0x00037d04: e0 03 18 aa              mov      x0, x24
0x00037d08: 92 e3 ff 97              bl       #0x30b50                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00037d0c: e1 c3 00 91              add      x1, sp, #0x30
0x00037d10: e0 03 15 aa              mov      x0, x21
0x00037d14: e2 03 16 aa              mov      x2, x22
0x00037d18: e1 00 00 94              bl       #0x3809c
0x00037d1c: 1a 00 40 f9              ldr      x26, [x0]
0x00037d20: 5a 03 00 b5              cbnz     x26, #0x37d88
0x00037d24: f8 03 00 aa              mov      x24, x0
0x00037d28: 00 0a 80 52              mov      w0, #0x50
0x00037d2c: c9 e4 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00037d30: fa 03 00 aa              mov      x26, x0
0x00037d34: 00 80 00 91              add      x0, x0, #0x20
0x00037d38: e1 03 16 aa              mov      x1, x22
0x00037d3c: 48 ed ff 97              bl       #0x3325c
0x00037d40: e8 1b 40 f9              ldr      x8, [sp, #0x30]
0x00037d44: 5f ff 03 a9              stp      xzr, xzr, [x26, #0x38]
0x00037d48: 5f 27 00 f9              str      xzr, [x26, #0x48]
0x00037d4c: 5f 7f 00 a9              stp      xzr, xzr, [x26]
0x00037d50: 48 0b 00 f9              str      x8, [x26, #0x10]
0x00037d54: 1a 03 00 f9              str      x26, [x24]
0x00037d58: a8 02 40 f9              ldr      x8, [x21]
0x00037d5c: 08 01 40 f9              ldr      x8, [x8]
0x00037d60: 88 00 00 b4              cbz      x8, #0x37d70
0x00037d64: a8 02 00 f9              str      x8, [x21]
0x00037d68: 01 03 40 f9              ldr      x1, [x24]
0x00037d6c: 02 00 00 14              b        #0x37d74                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt22CheckFp16AndResetModelEv+0x1c0
0x00037d70: e1 03 1a aa              mov      x1, x26
0x00037d74: 60 d2 40 f9              ldr      x0, [x19, #0x1a0]
0x00037d78: 54 f5 ff 97              bl       #0x352c8
0x00037d7c: 68 d6 40 f9              ldr      x8, [x19, #0x1a8]
0x00037d80: 08 05 00 91              add      x8, x8, #1
0x00037d84: 68 d6 00 f9              str      x8, [x19, #0x1a8]
0x00037d88: ff ff 01 a9              stp      xzr, xzr, [sp, #0x18]
0x00037d8c: ff 17 00 f9              str      xzr, [sp, #0x28]
0x00037d90: f7 03 1a aa              mov      x23, x26
0x00037d94: e8 8e 43 38              ldrb     w8, [x23, #0x38]!
0x00037d98: e9 06 40 f9              ldr      x9, [x23, #8]
0x00037d9c: 0a fd 41 d3              lsr      x10, x8, #1
0x00037da0: 1f 01 00 72              tst      w8, #1
0x00037da4: 58 01 89 9a              csel     x24, x10, x9, eq
0x00037da8: 08 13 00 91              add      x8, x24, #4
0x00037dac: 1f 41 00 b1              cmn      x8, #0x10
0x00037db0: 22 0e 00 54              b.hs     #0x37f74
0x00037db4: 1f 59 00 f1              cmp      x8, #0x16
0x00037db8: a8 00 00 54              b.hi     #0x37dcc
0x00037dbc: e0 0b 40 f9              ldr      x0, [sp, #0x10]
0x00037dc0: 08 01 80 52              mov      w8, #8
0x00037dc4: e8 63 00 39              strb     w8, [sp, #0x18]
0x00037dc8: 0b 00 00 14              b        #0x37df4                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt22CheckFp16AndResetModelEv+0x240
0x00037dcc: 08 53 00 91              add      x8, x24, #0x14
0x00037dd0: 19 ed 7c 92              and      x25, x8, #0xfffffffffffffff0
0x00037dd4: e0 03 19 aa              mov      x0, x25
0x00037dd8: 9e e4 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00037ddc: 28 03 40 b2              orr      x8, x25, #1
0x00037de0: b9 88 8b 52              mov      w25, #0x5c45
0x00037de4: d9 4d a1 72              movk     w25, #0xa6e, lsl #16
0x00037de8: e8 0f 00 f9              str      x8, [sp, #0x18]
0x00037dec: 88 00 80 52              mov      w8, #4
0x00037df0: e8 03 02 a9              stp      x8, x0, [sp, #0x20]
0x00037df4: 19 00 00 b9              str      w25, [x0]
0x00037df8: 1f 10 00 39              strb     wzr, [x0, #4]
0x00037dfc: 48 e3 40 39              ldrb     w8, [x26, #0x38]
0x00037e00: 49 27 40 f9              ldr      x9, [x26, #0x48]
0x00037e04: e0 63 00 91              add      x0, sp, #0x18
0x00037e08: e2 03 18 aa              mov      x2, x24
0x00037e0c: 1f 01 00 72              tst      w8, #1
0x00037e10: 21 15 97 9a              csinc    x1, x9, x23, ne
0x00037e14: 45 fc ff 97              bl       #0x36f28
0x00037e18: e1 c3 00 91              add      x1, sp, #0x30
0x00037e1c: e0 03 15 aa              mov      x0, x21
0x00037e20: e2 03 16 aa              mov      x2, x22
0x00037e24: 9e 00 00 94              bl       #0x3809c
0x00037e28: 17 00 40 f9              ldr      x23, [x0]
0x00037e2c: 57 03 00 b5              cbnz     x23, #0x37e94
0x00037e30: f8 03 00 aa              mov      x24, x0
0x00037e34: 00 0a 80 52              mov      w0, #0x50
0x00037e38: 86 e4 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00037e3c: f7 03 00 aa              mov      x23, x0
0x00037e40: 00 80 00 91              add      x0, x0, #0x20
0x00037e44: e1 03 16 aa              mov      x1, x22
0x00037e48: 05 ed ff 97              bl       #0x3325c
0x00037e4c: e8 1b 40 f9              ldr      x8, [sp, #0x30]
0x00037e50: ff fe 03 a9              stp      xzr, xzr, [x23, #0x38]
0x00037e54: ff 26 00 f9              str      xzr, [x23, #0x48]
0x00037e58: ff 7e 00 a9              stp      xzr, xzr, [x23]
0x00037e5c: e8 0a 00 f9              str      x8, [x23, #0x10]
0x00037e60: 17 03 00 f9              str      x23, [x24]
0x00037e64: a8 02 40 f9              ldr      x8, [x21]
0x00037e68: 08 01 40 f9              ldr      x8, [x8]
0x00037e6c: 88 00 00 b4              cbz      x8, #0x37e7c
0x00037e70: a8 02 00 f9              str      x8, [x21]
0x00037e74: 01 03 40 f9              ldr      x1, [x24]
0x00037e78: 02 00 00 14              b        #0x37e80                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt22CheckFp16AndResetModelEv+0x2cc
0x00037e7c: e1 03 17 aa              mov      x1, x23
0x00037e80: 60 d2 40 f9              ldr      x0, [x19, #0x1a0]
0x00037e84: 11 f5 ff 97              bl       #0x352c8
0x00037e88: 68 d6 40 f9              ldr      x8, [x19, #0x1a8]
0x00037e8c: 08 05 00 91              add      x8, x8, #1
0x00037e90: 68 d6 00 f9              str      x8, [x19, #0x1a8]
0x00037e94: f6 03 17 aa              mov      x22, x23
0x00037e98: c8 8e 43 38              ldrb     w8, [x22, #0x38]!
0x00037e9c: 68 00 00 37              tbnz     w8, #0, #0x37ea8
0x00037ea0: df 02 00 79              strh     wzr, [x22]
0x00037ea4: 09 00 00 14              b        #0x37ec8                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt22CheckFp16AndResetModelEv+0x314
0x00037ea8: e8 26 40 f9              ldr      x8, [x23, #0x48]
0x00037eac: 1f 01 00 39              strb     wzr, [x8]
0x00037eb0: e8 e2 40 39              ldrb     w8, [x23, #0x38]
0x00037eb4: ff 22 00 f9              str      xzr, [x23, #0x40]
0x00037eb8: 88 00 00 36              tbz      w8, #0, #0x37ec8
0x00037ebc: e0 26 40 f9              ldr      x0, [x23, #0x48]
0x00037ec0: 78 de ff 97              bl       #0x2f8a0                   ; -> _ZdlPv@plt
0x00037ec4: ff 1e 00 f9              str      xzr, [x23, #0x38]
0x00037ec8: e8 17 40 f9              ldr      x8, [sp, #0x28]
0x00037ecc: e0 83 c1 3c              ldur     q0, [sp, #0x18]
0x00037ed0: c8 0a 00 f9              str      x8, [x22, #0x10]
0x00037ed4: c0 02 80 3d              str      q0, [x22]
0x00037ed8: 88 07 40 f9              ldr      x8, [x28, #8]
0x00037edc: a8 00 00 b4              cbz      x8, #0x37ef0
0x00037ee0: fc 03 08 aa              mov      x28, x8
0x00037ee4: 08 01 40 f9              ldr      x8, [x8]
0x00037ee8: c8 ff ff b5              cbnz     x8, #0x37ee0
0x00037eec: 4e ff ff 17              b        #0x37c24                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt22CheckFp16AndResetModelEv+0x70
0x00037ef0: e8 03 1c aa              mov      x8, x28
0x00037ef4: 09 0d 41 f8              ldr      x9, [x8, #0x10]!
0x00037ef8: 2a 01 40 f9              ldr      x10, [x9]
0x00037efc: 5f 01 1c eb              cmp      x10, x28
0x00037f00: 00 e9 ff 54              b.eq     #0x37c20
0x00037f04: 09 01 40 f9              ldr      x9, [x8]
0x00037f08: e8 03 09 aa              mov      x8, x9
0x00037f0c: 1c 0d 41 f8              ldr      x28, [x8, #0x10]!
0x00037f10: 8a 03 40 f9              ldr      x10, [x28]
0x00037f14: 5f 01 09 eb              cmp      x10, x9
0x00037f18: 61 ff ff 54              b.ne     #0x37f04
0x00037f1c: 42 ff ff 17              b        #0x37c24                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt22CheckFp16AndResetModelEv+0x70
0x00037f20: 68 c2 40 f9              ldr      x8, [x19, #0x180]
0x00037f24: 61 ba 40 f9              ldr      x1, [x19, #0x170]
0x00037f28: 60 a2 05 91              add      x0, x19, #0x168
0x00037f2c: 08 fd 41 d3              lsr      x8, x8, #1
0x00037f30: 68 c2 00 f9              str      x8, [x19, #0x180]
0x00037f34: 3b e1 ff 97              bl       #0x30420                   ; -> _ZNSt6__ndk16__treeINS_12__value_typeINS_12basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEEENS_10shared_ptrIhEEEENS_19__map_value_compareIS7_SA_NS_4lessIS7_EELb1EEENS5_ISA_EEE7destroyEPNS_11__tree_nodeISA_PvEE@plt
0x00037f38: 7f 7e 17 a9              stp      xzr, xzr, [x19, #0x170]
0x00037f3c: e9 07 40 f9              ldr      x9, [sp, #8]
0x00037f40: 7b b6 00 f9              str      x27, [x19, #0x168]
0x00037f44: 28 15 40 f9              ldr      x8, [x9, #0x28]
0x00037f48: e9 1f 40 f9              ldr      x9, [sp, #0x38]
0x00037f4c: 1f 01 09 eb              cmp      x8, x9
0x00037f50: 41 01 00 54              b.ne     #0x37f78
0x00037f54: fd 7b 49 a9              ldp      x29, x30, [sp, #0x90]
0x00037f58: f4 4f 48 a9              ldp      x20, x19, [sp, #0x80]
0x00037f5c: f6 57 47 a9              ldp      x22, x21, [sp, #0x70]
0x00037f60: f8 5f 46 a9              ldp      x24, x23, [sp, #0x60]
0x00037f64: fa 67 45 a9              ldp      x26, x25, [sp, #0x50]
0x00037f68: fc 6f 44 a9              ldp      x28, x27, [sp, #0x40]
0x00037f6c: ff 83 02 91              add      sp, sp, #0xa0
0x00037f70: c0 03 5f d6              ret      
0x00037f74: 2f e3 ff 97              bl       #0x30c30                   ; -> abort@plt
0x00037f78: f2 e4 ff 97              bl       #0x31340                   ; -> __stack_chk_fail@plt
