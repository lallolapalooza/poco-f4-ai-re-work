; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : ComponentSpliceLoad (0x27c24)
; start   : 0x27c24
; size    : 0x3b8
; bytes   : 0x3b8   instructions: 238
; generated: 2026-10-23T23:48:35

0x00027c24: ff c3 01 d1              sub      sp, sp, #0x70
0x00027c28: fc 6f 01 a9              stp      x28, x27, [sp, #0x10]
0x00027c2c: fa 67 02 a9              stp      x26, x25, [sp, #0x20]
0x00027c30: f8 5f 03 a9              stp      x24, x23, [sp, #0x30]
0x00027c34: f6 57 04 a9              stp      x22, x21, [sp, #0x40]
0x00027c38: f4 4f 05 a9              stp      x20, x19, [sp, #0x50]
0x00027c3c: fd 7b 06 a9              stp      x29, x30, [sp, #0x60]
0x00027c40: fd 83 01 91              add      x29, sp, #0x60
0x00027c44: 48 d0 3b d5              mrs      x8, tpidr_el0
0x00027c48: 08 15 40 f9              ldr      x8, [x8, #0x28]
0x00027c4c: f3 03 00 aa              mov      x19, x0
0x00027c50: f8 03 00 32              mov      w24, #1
0x00027c54: f5 03 02 2a              mov      w21, w2
0x00027c58: e8 07 00 f9              str      x8, [sp, #8]
0x00027c5c: 74 1a 40 f9              ldr      x20, [x19, #0x30]
0x00027c60: f6 03 01 aa              mov      x22, x1
0x00027c64: 78 82 00 b9              str      w24, [x19, #0x80]
0x00027c68: e0 03 14 aa              mov      x0, x20
0x00027c6c: c1 97 ff 97              bl       #0xdb70                    ; -> SoilMode@plt
0x00027c70: 1f 04 00 71              cmp      w0, #1
0x00027c74: 01 06 00 54              b.ne     #0x27d34
0x00027c78: e0 03 14 aa              mov      x0, x20
0x00027c7c: 99 96 ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x00027c80: 60 6a 00 b9              str      w0, [x19, #0x68]
0x00027c84: e0 03 14 aa              mov      x0, x20
0x00027c88: 96 96 ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x00027c8c: 60 6e 00 b9              str      w0, [x19, #0x6c]
0x00027c90: e0 03 14 aa              mov      x0, x20
0x00027c94: 93 96 ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x00027c98: 60 8a 00 b9              str      w0, [x19, #0x88]
0x00027c9c: e0 03 14 aa              mov      x0, x20
0x00027ca0: 90 96 ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x00027ca4: 60 7e 00 b9              str      w0, [x19, #0x7c]
0x00027ca8: e0 03 14 aa              mov      x0, x20
0x00027cac: 8d 96 ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x00027cb0: 60 7a 00 b9              str      w0, [x19, #0x78]
0x00027cb4: e0 03 14 aa              mov      x0, x20
0x00027cb8: 8a 96 ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x00027cbc: 60 86 00 b9              str      w0, [x19, #0x84]
0x00027cc0: e0 03 14 aa              mov      x0, x20
0x00027cc4: 87 96 ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x00027cc8: 1f 00 00 71              cmp      w0, #0
0x00027ccc: e8 07 9f 1a              cset     w8, ne
0x00027cd0: e0 03 14 aa              mov      x0, x20
0x00027cd4: 68 82 02 39              strb     w8, [x19, #0xa0]
0x00027cd8: 82 96 ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x00027cdc: 40 16 00 34              cbz      w0, #0x27fa4
0x00027ce0: e0 03 14 aa              mov      x0, x20
0x00027ce4: 7f 96 ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x00027ce8: 60 72 00 b9              str      w0, [x19, #0x70]
0x00027cec: 1f 04 00 71              cmp      w0, #1
0x00027cf0: ab 15 00 54              b.lt     #0x27fa4
0x00027cf4: e0 03 14 aa              mov      x0, x20
0x00027cf8: 7a 96 ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x00027cfc: 61 72 40 b9              ldr      w1, [x19, #0x70]
0x00027d00: 60 76 00 b9              str      w0, [x19, #0x74]
0x00027d04: e0 03 14 aa              mov      x0, x20
0x00027d08: 32 95 ff 97              bl       #0xd1d0                    ; -> SoilLoadBinArrayInt@plt
0x00027d0c: 68 1a 40 f9              ldr      x8, [x19, #0x30]
0x00027d10: 69 76 80 b9              ldrsw    x9, [x19, #0x74]
0x00027d14: 6a 6a 80 b9              ldrsw    x10, [x19, #0x68]
0x00027d18: 60 4a 00 f9              str      x0, [x19, #0x90]
0x00027d1c: 00 01 40 f9              ldr      x0, [x8]
0x00027d20: 28 7d 0a 9b              mul      x8, x9, x10
0x00027d24: 01 f5 7e d3              lsl      x1, x8, #2
0x00027d28: ee 94 ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x00027d2c: 60 4e 00 f9              str      x0, [x19, #0x98]
0x00027d30: 9d 00 00 14              b        #0x27fa4                   ; -> ComponentSpliceLoad+0x380
0x00027d34: 21 01 00 f0              adrp     x1, #0x4e000
0x00027d38: 21 08 1e 91              add      x1, x1, #0x782
0x00027d3c: e0 03 16 aa              mov      x0, x22
0x00027d40: 00 96 ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x00027d44: e1 03 15 2a              mov      w1, w21
0x00027d48: 3a 95 ff 97              bl       #0xd230                    ; -> SoilJsonGetArrayItem@plt
0x00027d4c: 41 01 00 90              adrp     x1, #0x4f000
0x00027d50: 21 98 13 91              add      x1, x1, #0x4e6
0x00027d54: f7 03 00 aa              mov      x23, x0
0x00027d58: 06 94 ff 97              bl       #0xcd70                    ; -> SoilJsonGetInt@plt
0x00027d5c: f5 03 13 aa              mov      x21, x19
0x00027d60: 41 01 00 90              adrp     x1, #0x4f000
0x00027d64: a0 8e 06 b8              str      w0, [x21, #0x68]!
0x00027d68: 21 dc 0b 91              add      x1, x1, #0x2f7
0x00027d6c: e0 03 17 aa              mov      x0, x23
0x00027d70: 00 94 ff 97              bl       #0xcd70                    ; -> SoilJsonGetInt@plt
0x00027d74: 21 01 00 f0              adrp     x1, #0x4e000
0x00027d78: 60 6e 00 b9              str      w0, [x19, #0x6c]
0x00027d7c: 21 e4 26 91              add      x1, x1, #0x9b9
0x00027d80: e2 03 00 32              mov      w2, #1
0x00027d84: e0 03 17 aa              mov      x0, x23
0x00027d88: 46 96 ff 97              bl       #0xd6a0                    ; -> SoilJsonGetIntc@plt
0x00027d8c: 21 01 00 f0              adrp     x1, #0x4e000
0x00027d90: 60 8a 00 b9              str      w0, [x19, #0x88]
0x00027d94: 21 48 27 91              add      x1, x1, #0x9d2
0x00027d98: e0 03 17 aa              mov      x0, x23
0x00027d9c: e2 03 1f 2a              mov      w2, wzr
0x00027da0: 40 96 ff 97              bl       #0xd6a0                    ; -> SoilJsonGetIntc@plt
0x00027da4: 21 01 00 f0              adrp     x1, #0x4e000
0x00027da8: 60 7e 00 b9              str      w0, [x19, #0x7c]
0x00027dac: 21 78 27 91              add      x1, x1, #0x9de
0x00027db0: e2 03 00 32              mov      w2, #1
0x00027db4: e0 03 17 aa              mov      x0, x23
0x00027db8: 3a 96 ff 97              bl       #0xd6a0                    ; -> SoilJsonGetIntc@plt
0x00027dbc: 21 01 00 f0              adrp     x1, #0x4e000
0x00027dc0: 60 7a 00 b9              str      w0, [x19, #0x78]
0x00027dc4: 21 b8 27 91              add      x1, x1, #0x9ee
0x00027dc8: e0 03 17 aa              mov      x0, x23
0x00027dcc: e2 03 1f 2a              mov      w2, wzr
0x00027dd0: 34 96 ff 97              bl       #0xd6a0                    ; -> SoilJsonGetIntc@plt
0x00027dd4: 21 01 00 f0              adrp     x1, #0x4e000
0x00027dd8: 60 86 00 b9              str      w0, [x19, #0x84]
0x00027ddc: f6 03 13 aa              mov      x22, x19
0x00027de0: 21 ec 27 91              add      x1, x1, #0x9fb
0x00027de4: e0 03 17 aa              mov      x0, x23
0x00027de8: d8 0e 0a 38              strb     w24, [x22, #0xa0]!
0x00027dec: d5 95 ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x00027df0: f7 03 00 aa              mov      x23, x0
0x00027df4: 17 07 00 b4              cbz      x23, #0x27ed4
0x00027df8: e0 03 17 aa              mov      x0, x23
0x00027dfc: 21 94 ff 97              bl       #0xce80                    ; -> SoilJsonGetArraySize@plt
0x00027e00: 60 72 00 b9              str      w0, [x19, #0x70]
0x00027e04: 1f 04 00 71              cmp      w0, #1
0x00027e08: 6b 06 00 54              b.lt     #0x27ed4
0x00027e0c: 68 1a 40 f9              ldr      x8, [x19, #0x30]
0x00027e10: 01 7c 7e 93              sbfiz    x1, x0, #2, #0x20
0x00027e14: 08 01 40 f9              ldr      x8, [x8]
0x00027e18: e0 03 08 aa              mov      x0, x8
0x00027e1c: b1 94 ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x00027e20: 69 72 40 b9              ldr      w9, [x19, #0x70]
0x00027e24: e8 03 00 aa              mov      x8, x0
0x00027e28: 68 4a 00 f9              str      x8, [x19, #0x90]
0x00027e2c: 3f 05 00 71              cmp      w9, #1
0x00027e30: 6b 01 00 54              b.lt     #0x27e5c
0x00027e34: f8 03 1f aa              mov      x24, xzr
0x00027e38: e0 03 17 aa              mov      x0, x23
0x00027e3c: e1 03 18 2a              mov      w1, w24
0x00027e40: f8 96 ff 97              bl       #0xda20                    ; -> SoilJsonGetArrayInt@plt
0x00027e44: 68 4a 40 f9              ldr      x8, [x19, #0x90]
0x00027e48: 00 79 38 b8              str      w0, [x8, x24, lsl #2]
0x00027e4c: 69 72 80 b9              ldrsw    x9, [x19, #0x70]
0x00027e50: 18 07 00 91              add      x24, x24, #1
0x00027e54: 1f 03 09 eb              cmp      x24, x9
0x00027e58: 0b ff ff 54              b.lt     #0x27e38
0x00027e5c: 29 05 00 51              sub      w9, w9, #1
0x00027e60: 2b 7d 7e 93              sbfiz    x11, x9, #2, #0x20
0x00027e64: 0a 01 40 b9              ldr      w10, [x8]
0x00027e68: 09 69 6b b8              ldr      w9, [x8, x11]
0x00027e6c: 29 01 0a 4b              sub      w9, w9, w10
0x00027e70: 69 76 00 b9              str      w9, [x19, #0x74]
0x00027e74: 0a 01 40 b9              ldr      w10, [x8]
0x00027e78: 08 69 6b b8              ldr      w8, [x8, x11]
0x00027e7c: 5f 01 00 71              cmp      w10, #0
0x00027e80: 4c 55 8a 5a              cneg     w12, w10, mi
0x00027e84: 1f 01 00 71              cmp      w8, #0
0x00027e88: 0b 55 88 5a              cneg     w11, w8, mi
0x00027e8c: 3f 01 0c 6b              cmp      w9, w12
0x00027e90: 8d 00 00 54              b.le     #0x27ea0
0x00027e94: 3f 01 0b 6b              cmp      w9, w11
0x00027e98: 28 c1 8b 1a              csel     w8, w9, w11, gt
0x00027e9c: 05 00 00 14              b        #0x27eb0                   ; -> ComponentSpliceLoad+0x28c
0x00027ea0: 9f 01 0b 6b              cmp      w12, w11
0x00027ea4: 48 c1 88 1a              csel     w8, w10, w8, gt
0x00027ea8: 1f 01 00 71              cmp      w8, #0
0x00027eac: 08 55 88 5a              cneg     w8, w8, mi
0x00027eb0: 69 1a 40 f9              ldr      x9, [x19, #0x30]
0x00027eb4: 6a 6a 80 b9              ldrsw    x10, [x19, #0x68]
0x00027eb8: 68 76 00 b9              str      w8, [x19, #0x74]
0x00027ebc: 08 7d 40 93              sxtw     x8, w8
0x00027ec0: 20 01 40 f9              ldr      x0, [x9]
0x00027ec4: 08 7d 0a 9b              mul      x8, x8, x10
0x00027ec8: 01 f5 7e d3              lsl      x1, x8, #2
0x00027ecc: 85 94 ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x00027ed0: 60 4e 00 f9              str      x0, [x19, #0x98]
0x00027ed4: e0 03 14 aa              mov      x0, x20
0x00027ed8: 26 97 ff 97              bl       #0xdb70                    ; -> SoilMode@plt
0x00027edc: 1f 08 00 71              cmp      w0, #2
0x00027ee0: 21 06 00 54              b.ne     #0x27fa4
0x00027ee4: e0 03 15 aa              mov      x0, x21
0x00027ee8: e1 03 14 aa              mov      x1, x20
0x00027eec: 78 b2 01 91              add      x24, x19, #0x6c
0x00027ef0: 79 22 02 91              add      x25, x19, #0x88
0x00027ef4: 7a f2 01 91              add      x26, x19, #0x7c
0x00027ef8: 7b e2 01 91              add      x27, x19, #0x78
0x00027efc: 7c 12 02 91              add      x28, x19, #0x84
0x00027f00: ff 07 00 b9              str      wzr, [sp, #4]
0x00027f04: 73 94 ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x00027f08: e0 03 18 aa              mov      x0, x24
0x00027f0c: e1 03 14 aa              mov      x1, x20
0x00027f10: 70 94 ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x00027f14: e0 03 19 aa              mov      x0, x25
0x00027f18: e1 03 14 aa              mov      x1, x20
0x00027f1c: 6d 94 ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x00027f20: e0 03 1a aa              mov      x0, x26
0x00027f24: e1 03 14 aa              mov      x1, x20
0x00027f28: 6a 94 ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x00027f2c: e0 03 1b aa              mov      x0, x27
0x00027f30: e1 03 14 aa              mov      x1, x20
0x00027f34: 67 94 ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x00027f38: e0 03 1c aa              mov      x0, x28
0x00027f3c: e1 03 14 aa              mov      x1, x20
0x00027f40: 64 94 ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x00027f44: e0 03 16 aa              mov      x0, x22
0x00027f48: e1 03 14 aa              mov      x1, x20
0x00027f4c: 61 94 ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x00027f50: 77 00 00 b4              cbz      x23, #0x27f5c
0x00027f54: e8 03 00 32              mov      w8, #1
0x00027f58: e8 07 00 b9              str      w8, [sp, #4]
0x00027f5c: e0 13 00 91              add      x0, sp, #4
0x00027f60: e1 03 14 aa              mov      x1, x20
0x00027f64: 5b 94 ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x00027f68: e8 07 40 b9              ldr      w8, [sp, #4]
0x00027f6c: c8 01 00 34              cbz      w8, #0x27fa4
0x00027f70: 60 c2 01 91              add      x0, x19, #0x70
0x00027f74: e1 03 14 aa              mov      x1, x20
0x00027f78: 56 94 ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x00027f7c: 61 72 40 b9              ldr      w1, [x19, #0x70]
0x00027f80: 3f 04 00 71              cmp      w1, #1
0x00027f84: ab 00 00 54              b.lt     #0x27f98
0x00027f88: 60 d2 01 91              add      x0, x19, #0x74
0x00027f8c: e1 03 14 aa              mov      x1, x20
0x00027f90: 50 94 ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x00027f94: 61 72 40 b9              ldr      w1, [x19, #0x70]
0x00027f98: 60 4a 40 f9              ldr      x0, [x19, #0x90]
0x00027f9c: e2 03 14 aa              mov      x2, x20
0x00027fa0: 48 96 ff 97              bl       #0xd8c0                    ; -> SoilWriteBinArrayInt@plt
0x00027fa4: 48 d0 3b d5              mrs      x8, tpidr_el0
0x00027fa8: 08 15 40 f9              ldr      x8, [x8, #0x28]
0x00027fac: e9 07 40 f9              ldr      x9, [sp, #8]
0x00027fb0: 1f 01 09 eb              cmp      x8, x9
0x00027fb4: 21 01 00 54              b.ne     #0x27fd8
0x00027fb8: fd 7b 46 a9              ldp      x29, x30, [sp, #0x60]
0x00027fbc: f4 4f 45 a9              ldp      x20, x19, [sp, #0x50]
0x00027fc0: f6 57 44 a9              ldp      x22, x21, [sp, #0x40]
0x00027fc4: f8 5f 43 a9              ldp      x24, x23, [sp, #0x30]
0x00027fc8: fa 67 42 a9              ldp      x26, x25, [sp, #0x20]
0x00027fcc: fc 6f 41 a9              ldp      x28, x27, [sp, #0x10]
0x00027fd0: ff c3 01 91              add      sp, sp, #0x70
0x00027fd4: c0 03 5f d6              ret      
0x00027fd8: f6 94 ff 97              bl       #0xd3b0                    ; -> __stack_chk_fail@plt
