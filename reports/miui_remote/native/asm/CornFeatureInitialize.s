; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : CornFeatureInitialize (0x32b50)
; start   : 0x32b50
; size    : 0x4e0
; bytes   : 0x4e0   instructions: 312
; generated: 2026-10-23T23:30:03

0x00032b50: ff 83 03 d1              sub      sp, sp, #0xe0
0x00032b54: ed 33 05 6d              stp      d13, d12, [sp, #0x50]
0x00032b58: eb 2b 06 6d              stp      d11, d10, [sp, #0x60]
0x00032b5c: e9 23 07 6d              stp      d9, d8, [sp, #0x70]
0x00032b60: fc 6f 08 a9              stp      x28, x27, [sp, #0x80]
0x00032b64: fa 67 09 a9              stp      x26, x25, [sp, #0x90]
0x00032b68: f8 5f 0a a9              stp      x24, x23, [sp, #0xa0]
0x00032b6c: f6 57 0b a9              stp      x22, x21, [sp, #0xb0]
0x00032b70: f4 4f 0c a9              stp      x20, x19, [sp, #0xc0]
0x00032b74: fd 7b 0d a9              stp      x29, x30, [sp, #0xd0]
0x00032b78: fd 43 03 91              add      x29, sp, #0xd0
0x00032b7c: 56 d0 3b d5              mrs      x22, tpidr_el0
0x00032b80: c8 16 40 f9              ldr      x8, [x22, #0x28]
0x00032b84: f3 03 00 aa              mov      x19, x0
0x00032b88: 49 cf a8 52              mov      w9, #0x467a0000
0x00032b8c: f7 03 13 aa              mov      x23, x19
0x00032b90: e8 27 00 f9              str      x8, [sp, #0x48]
0x00032b94: 08 24 a8 d2              mov      x8, #0x41200000
0x00032b98: ea 03 00 b2              mov      x10, #0x100000001
0x00032b9c: 08 39 e8 f2              movk     x8, #0x41c8, lsl #48
0x00032ba0: e9 0e 01 b8              str      w9, [x23, #0x10]!
0x00032ba4: f4 03 03 aa              mov      x20, x3
0x00032ba8: ea 2a 00 f9              str      x10, [x23, #0x50]
0x00032bac: e8 42 00 f8              stur     x8, [x23, #4]
0x00032bb0: 88 da 43 b9              ldr      w8, [x20, #0x3d8]
0x00032bb4: 89 3d 8a d2              mov      x9, #0x51ec
0x00032bb8: 09 ef a7 f2              movk     x9, #0x3f78, lsl #16
0x00032bbc: 29 00 c0 f2              movk     x9, #1, lsl #32
0x00032bc0: ea 07 00 32              mov      w10, #3
0x00032bc4: e9 0a 00 f9              str      x9, [x23, #0x10]
0x00032bc8: 09 14 80 52              mov      w9, #0xa0
0x00032bcc: ea 1a 00 b9              str      w10, [x23, #0x18]
0x00032bd0: 0a 32 80 52              mov      w10, #0x190
0x00032bd4: e8 0e 00 b9              str      w8, [x23, #0xc]
0x00032bd8: e8 03 17 32              mov      w8, #0x200
0x00032bdc: e9 2a 05 29              stp      w9, w10, [x23, #0x28]
0x00032be0: e8 32 00 b9              str      w8, [x23, #0x30]
0x00032be4: 80 86 41 f9              ldr      x0, [x20, #0x308]
0x00032be8: 01 c8 80 52              mov      w1, #0x640
0x00032bec: f5 03 02 aa              mov      x21, x2
0x00032bf0: 48 69 ff 97              bl       #0xd110                    ; -> CornMpoolAlloc@plt
0x00032bf4: e8 2e 80 b9              ldrsw    x8, [x23, #0x2c]
0x00032bf8: e1 03 1f 2a              mov      w1, wzr
0x00032bfc: e0 12 00 f9              str      x0, [x23, #0x20]
0x00032c00: 02 f5 7e d3              lsl      x2, x8, #2
0x00032c04: b7 6b ff 97              bl       #0xdae0                    ; -> memset@plt
0x00032c08: e8 2e 40 b9              ldr      w8, [x23, #0x2c]
0x00032c0c: 1f 05 00 71              cmp      w8, #1
0x00032c10: 2b 07 00 54              b.lt     #0x32cf4
0x00032c14: 89 09 00 d0              adrp     x9, #0x164000
0x00032c18: 8a 09 00 d0              adrp     x10, #0x164000
0x00032c1c: 8b 09 00 d0              adrp     x11, #0x164000
0x00032c20: 8c 09 00 d0              adrp     x12, #0x164000
0x00032c24: 29 e1 44 fd              ldr      d9, [x9, #0x9c0]
0x00032c28: 4a e9 44 fd              ldr      d10, [x10, #0x9d0]
0x00032c2c: 6b ed 44 fd              ldr      d11, [x11, #0x9d8]
0x00032c30: 88 e5 44 fd              ldr      d8, [x12, #0x9c8]
0x00032c34: 99 09 00 d0              adrp     x25, #0x164000
0x00032c38: f8 03 1f aa              mov      x24, xzr
0x00032c3c: 0c 10 2e 1e              fmov     s12, #1.00000000
0x00032c40: 39 43 28 91              add      x25, x25, #0xa10
0x00032c44: 0d 10 6c 1e              fmov     d13, #0.50000000
0x00032c48: 69 2a 40 b9              ldr      w9, [x19, #0x28]
0x00032c4c: 3f 0d 00 71              cmp      w9, #3
0x00032c50: c8 04 00 54              b.hi     #0x32ce8
0x00032c54: 29 7b a9 b8              ldrsw    x9, [x25, x9, lsl #2]
0x00032c58: 01 03 22 1e              scvtf    s1, w24
0x00032c5c: 80 1d ac 4e              mov      v0.16b, v12.16b
0x00032c60: 29 01 19 8b              add      x9, x9, x25
0x00032c64: 20 01 1f d6              br       x9
0x00032c68: 20 c0 22 1e              fcvt     d0, s1
0x00032c6c: 08 05 00 51              sub      w8, w8, #1
0x00032c70: 00 08 69 1e              fmul     d0, d0, d9
0x00032c74: 01 01 62 1e              scvtf    d1, w8
0x00032c78: 00 18 61 1e              fdiv     d0, d0, d1
0x00032c7c: 31 69 ff 97              bl       #0xd140                    ; -> cos@plt
0x00032c80: 00 08 6d 1e              fmul     d0, d0, d13
0x00032c84: a0 39 60 1e              fsub     d0, d13, d0
0x00032c88: 14 00 00 14              b        #0x32cd8                   ; -> CornFeatureInitialize+0x188
0x00032c8c: 20 c0 22 1e              fcvt     d0, s1
0x00032c90: 08 05 00 51              sub      w8, w8, #1
0x00032c94: 00 08 69 1e              fmul     d0, d0, d9
0x00032c98: 01 01 62 1e              scvtf    d1, w8
0x00032c9c: 00 18 61 1e              fdiv     d0, d0, d1
0x00032ca0: 28 69 ff 97              bl       #0xd140                    ; -> cos@plt
0x00032ca4: 00 08 6a 1e              fmul     d0, d0, d10
0x00032ca8: 00 28 6b 1e              fadd     d0, d0, d11
0x00032cac: 0b 00 00 14              b        #0x32cd8                   ; -> CornFeatureInitialize+0x188
0x00032cb0: 20 c0 22 1e              fcvt     d0, s1
0x00032cb4: 08 05 00 51              sub      w8, w8, #1
0x00032cb8: 00 08 69 1e              fmul     d0, d0, d9
0x00032cbc: 01 01 62 1e              scvtf    d1, w8
0x00032cc0: 00 18 61 1e              fdiv     d0, d0, d1
0x00032cc4: 1f 69 ff 97              bl       #0xd140                    ; -> cos@plt
0x00032cc8: 00 08 6d 1e              fmul     d0, d0, d13
0x00032ccc: a0 39 60 1e              fsub     d0, d13, d0
0x00032cd0: 01 1d a8 4e              mov      v1.16b, v8.16b
0x00032cd4: f3 67 ff 97              bl       #0xcca0                    ; -> pow@plt
0x00032cd8: 00 40 62 1e              fcvt     s0, d0
0x00032cdc: 68 1a 40 f9              ldr      x8, [x19, #0x30]
0x00032ce0: 00 79 38 bc              str      s0, [x8, x24, lsl #2]
0x00032ce4: 68 3e 40 b9              ldr      w8, [x19, #0x3c]
0x00032ce8: 18 07 00 91              add      x24, x24, #1
0x00032cec: 1f c3 28 eb              cmp      x24, w8, sxtw
0x00032cf0: cb fa ff 54              b.lt     #0x32c48
0x00032cf4: 69 4a 40 b9              ldr      w9, [x19, #0x48]
0x00032cf8: 7f 36 00 f9              str      xzr, [x19, #0x68]
0x00032cfc: 7f 7e 00 b9              str      wzr, [x19, #0x7c]
0x00032d00: 01 7d 7e 93              sbfiz    x1, x8, #2, #0x20
0x00032d04: 69 0a 00 b9              str      w9, [x19, #8]
0x00032d08: 80 86 41 f9              ldr      x0, [x20, #0x308]
0x00032d0c: 01 69 ff 97              bl       #0xd110                    ; -> CornMpoolAlloc@plt
0x00032d10: 68 3e 80 b9              ldrsw    x8, [x19, #0x3c]
0x00032d14: e1 03 1f 2a              mov      w1, wzr
0x00032d18: 60 3a 00 f9              str      x0, [x19, #0x70]
0x00032d1c: 02 f5 7e d3              lsl      x2, x8, #2
0x00032d20: 70 6b ff 97              bl       #0xdae0                    ; -> memset@plt
0x00032d24: 7f 7a 00 b9              str      wzr, [x19, #0x78]
0x00032d28: 80 86 41 f9              ldr      x0, [x20, #0x308]
0x00032d2c: 01 50 80 52              mov      w1, #0x280
0x00032d30: f8 68 ff 97              bl       #0xd110                    ; -> CornMpoolAlloc@plt
0x00032d34: 02 50 80 52              mov      w2, #0x280
0x00032d38: e1 03 1f 2a              mov      w1, wzr
0x00032d3c: 60 02 00 f9              str      x0, [x19]
0x00032d40: 68 6b ff 97              bl       #0xdae0                    ; -> memset@plt
0x00032d44: 61 42 80 b9              ldrsw    x1, [x19, #0x40]
0x00032d48: c1 14 f8 37              tbnz     w1, #0x1f, #0x32fe0
0x00032d4c: f6 07 00 f9              str      x22, [sp, #8]
0x00032d50: 80 86 41 f9              ldr      x0, [x20, #0x308]
0x00032d54: 21 f4 7e d3              lsl      x1, x1, #2
0x00032d58: ee 68 ff 97              bl       #0xd110                    ; -> CornMpoolAlloc@plt
0x00032d5c: 68 42 80 b9              ldrsw    x8, [x19, #0x40]
0x00032d60: e1 03 1f 2a              mov      w1, wzr
0x00032d64: 60 36 00 f9              str      x0, [x19, #0x68]
0x00032d68: 02 f5 7e d3              lsl      x2, x8, #2
0x00032d6c: 5d 6b ff 97              bl       #0xdae0                    ; -> memset@plt
0x00032d70: 68 42 80 b9              ldrsw    x8, [x19, #0x40]
0x00032d74: 80 86 41 f9              ldr      x0, [x20, #0x308]
0x00032d78: 01 f5 7f d3              ubfiz    x1, x8, #1, #0x3e
0x00032d7c: e5 68 ff 97              bl       #0xd110                    ; -> CornMpoolAlloc@plt
0x00032d80: 68 42 80 b9              ldrsw    x8, [x19, #0x40]
0x00032d84: e1 03 1f 2a              mov      w1, wzr
0x00032d88: 60 46 00 f9              str      x0, [x19, #0x88]
0x00032d8c: 02 f5 7f d3              ubfiz    x2, x8, #1, #0x3e
0x00032d90: 54 6b ff 97              bl       #0xdae0                    ; -> memset@plt
0x00032d94: 68 4a 80 b9              ldrsw    x8, [x19, #0x48]
0x00032d98: 80 86 41 f9              ldr      x0, [x20, #0x308]
0x00032d9c: 08 05 08 8b              add      x8, x8, x8, lsl #1
0x00032da0: 01 f1 7d d3              lsl      x1, x8, #3
0x00032da4: db 68 ff 97              bl       #0xd110                    ; -> CornMpoolAlloc@plt
0x00032da8: 68 4a 80 b9              ldrsw    x8, [x19, #0x48]
0x00032dac: e1 03 1f 2a              mov      w1, wzr
0x00032db0: 60 2e 00 f9              str      x0, [x19, #0x58]
0x00032db4: 08 05 08 8b              add      x8, x8, x8, lsl #1
0x00032db8: 02 f1 7d d3              lsl      x2, x8, #3
0x00032dbc: 49 6b ff 97              bl       #0xdae0                    ; -> memset@plt
0x00032dc0: e8 1a 40 f9              ldr      x8, [x23, #0x30]
0x00032dc4: 60 2e 40 f9              ldr      x0, [x19, #0x58]
0x00032dc8: 61 26 40 f9              ldr      x1, [x19, #0x48]
0x00032dcc: 62 52 40 b9              ldr      w2, [x19, #0x50]
0x00032dd0: e8 23 00 f9              str      x8, [sp, #0x40]
0x00032dd4: e0 0a c0 3d              ldr      q0, [x23, #0x20]
0x00032dd8: e3 43 00 91              add      x3, sp, #0x10
0x00032ddc: e4 03 14 aa              mov      x4, x20
0x00032de0: e0 0f 80 3d              str      q0, [sp, #0x30]
0x00032de4: e0 06 c0 3d              ldr      q0, [x23, #0x10]
0x00032de8: e0 0b 80 3d              str      q0, [sp, #0x20]
0x00032dec: e0 02 c0 3d              ldr      q0, [x23]
0x00032df0: e0 07 80 3d              str      q0, [sp, #0x10]
0x00032df4: a3 69 ff 97              bl       #0xd480                    ; -> CornMelBankInitial@plt
0x00032df8: 68 3e 80 b9              ldrsw    x8, [x19, #0x3c]
0x00032dfc: 80 86 41 f9              ldr      x0, [x20, #0x308]
0x00032e00: 01 f9 7f d3              lsl      x1, x8, #1
0x00032e04: c3 68 ff 97              bl       #0xd110                    ; -> CornMpoolAlloc@plt
0x00032e08: 08 05 80 52              mov      w8, #0x28
0x00032e0c: 60 7e 09 a9              stp      x0, xzr, [x19, #0x90]
0x00032e10: 7f 7e 00 b9              str      wzr, [x19, #0x7c]
0x00032e14: bf 02 00 f9              str      xzr, [x21]
0x00032e18: a8 0a 00 b9              str      w8, [x21, #8]
0x00032e1c: a8 1e 00 b9              str      w8, [x21, #0x1c]
0x00032e20: 80 86 41 f9              ldr      x0, [x20, #0x308]
0x00032e24: 01 50 80 52              mov      w1, #0x280
0x00032e28: ba 68 ff 97              bl       #0xd110                    ; -> CornMpoolAlloc@plt
0x00032e2c: 02 50 80 52              mov      w2, #0x280
0x00032e30: e1 03 1f 2a              mov      w1, wzr
0x00032e34: a0 02 00 f9              str      x0, [x21]
0x00032e38: 2a 6b ff 97              bl       #0xdae0                    ; -> memset@plt
0x00032e3c: 08 36 a8 52              mov      w8, #0x41b00000
0x00032e40: a9 0a 80 b9              ldrsw    x9, [x21, #8]
0x00032e44: b7 1e 80 b9              ldrsw    x23, [x21, #0x1c]
0x00032e48: a8 22 00 b9              str      w8, [x21, #0x20]
0x00032e4c: 80 86 41 f9              ldr      x0, [x20, #0x308]
0x00032e50: 38 7d 40 92              and      x24, x9, #0xffffffff
0x00032e54: 28 7d 17 9b              mul      x8, x9, x23
0x00032e58: 01 f5 7e d3              lsl      x1, x8, #2
0x00032e5c: ad 68 ff 97              bl       #0xd110                    ; -> CornMpoolAlloc@plt
0x00032e60: e9 02 22 1e              scvtf    s9, w23
0x00032e64: 00 10 2e 1e              fmov     s0, #1.00000000
0x00032e68: 00 18 29 1e              fdiv     s0, s0, s9
0x00032e6c: 08 c0 21 1e              fsqrt    s8, s0
0x00032e70: 00 21 28 1e              fcmp     s8, s8
0x00032e74: a0 1a 00 f9              str      x0, [x21, #0x30]
0x00032e78: 67 00 00 54              b.vc     #0x32e84
0x00032e7c: 51 67 ff 97              bl       #0xcbc0                    ; -> sqrtf@plt
0x00032e80: 08 1c a0 4e              mov      v8.16b, v0.16b
0x00032e84: 1f 07 00 71              cmp      w24, #1
0x00032e88: 6b 05 00 54              b.lt     #0x32f34
0x00032e8c: 88 09 00 d0              adrp     x8, #0x164000
0x00032e90: 02 f1 44 fd              ldr      d2, [x8, #0x9e0]
0x00032e94: 00 10 20 1e              fmov     s0, #2.00000000
0x00032e98: e1 02 62 1e              scvtf    d1, w23
0x00032e9c: 09 18 29 1e              fdiv     s9, s0, s9
0x00032ea0: f9 03 1f aa              mov      x25, xzr
0x00032ea4: fa 03 1f aa              mov      x26, xzr
0x00032ea8: fb 7e 40 92              and      x27, x23, #0xffffffff
0x00032eac: fc f6 7e d3              lsl      x28, x23, #2
0x00032eb0: 2a c1 21 1e              fsqrt    s10, s9
0x00032eb4: 4b 18 61 1e              fdiv     d11, d2, d1
0x00032eb8: 0c 10 2c 1e              fmov     s12, #0.50000000
0x00032ebc: ff 06 00 71              cmp      w23, #1
0x00032ec0: 2b 03 00 54              b.lt     #0x32f24
0x00032ec4: f6 03 1f aa              mov      x22, xzr
0x00032ec8: 4d 03 62 1e              scvtf    d13, w26
0x00032ecc: 00 1d a8 4e              mov      v0.16b, v8.16b
0x00032ed0: fa 01 00 b4              cbz      x26, #0x32f0c
0x00032ed4: 40 21 2a 1e              fcmp     s10, s10
0x00032ed8: 48 1d aa 4e              mov      v8.16b, v10.16b
0x00032edc: 87 00 00 54              b.vc     #0x32eec
0x00032ee0: 20 1d a9 4e              mov      v0.16b, v9.16b
0x00032ee4: 37 67 ff 97              bl       #0xcbc0                    ; -> sqrtf@plt
0x00032ee8: 08 1c a0 4e              mov      v8.16b, v0.16b
0x00032eec: c0 02 22 1e              scvtf    s0, w22
0x00032ef0: 00 28 2c 1e              fadd     s0, s0, s12
0x00032ef4: 00 c0 22 1e              fcvt     d0, s0
0x00032ef8: 60 09 60 1e              fmul     d0, d11, d0
0x00032efc: 00 08 6d 1e              fmul     d0, d0, d13
0x00032f00: 00 40 62 1e              fcvt     s0, d0
0x00032f04: db 67 ff 97              bl       #0xce70                    ; -> cosf@plt
0x00032f08: 00 09 20 1e              fmul     s0, s8, s0
0x00032f0c: a8 1a 40 f9              ldr      x8, [x21, #0x30]
0x00032f10: 08 01 19 8b              add      x8, x8, x25
0x00032f14: 00 79 36 bc              str      s0, [x8, x22, lsl #2]
0x00032f18: d6 06 00 91              add      x22, x22, #1
0x00032f1c: 7f 03 16 eb              cmp      x27, x22
0x00032f20: 61 fd ff 54              b.ne     #0x32ecc
0x00032f24: 5a 07 00 91              add      x26, x26, #1
0x00032f28: 5f 03 18 eb              cmp      x26, x24
0x00032f2c: 39 03 1c 8b              add      x25, x25, x28
0x00032f30: 61 fc ff 54              b.ne     #0x32ebc
0x00032f34: a0 22 40 bd              ldr      s0, [x21, #0x20]
0x00032f38: 08 20 20 1e              fcmp     s0, #0.0
0x00032f3c: e0 03 00 54              b.eq     #0x32fb8
0x00032f40: a8 0a 80 b9              ldrsw    x8, [x21, #8]
0x00032f44: 80 86 41 f9              ldr      x0, [x20, #0x308]
0x00032f48: 01 f5 7e d3              lsl      x1, x8, #2
0x00032f4c: 71 68 ff 97              bl       #0xd110                    ; -> CornMpoolAlloc@plt
0x00032f50: a8 0a 40 b9              ldr      w8, [x21, #8]
0x00032f54: a0 16 00 f9              str      x0, [x21, #0x28]
0x00032f58: 1f 05 00 71              cmp      w8, #1
0x00032f5c: eb 02 00 54              b.lt     #0x32fb8
0x00032f60: 88 09 00 d0              adrp     x8, #0x164000
0x00032f64: 08 f1 44 fd              ldr      d8, [x8, #0x9e0]
0x00032f68: f6 03 1f aa              mov      x22, xzr
0x00032f6c: 09 10 2c 1e              fmov     s9, #0.50000000
0x00032f70: 0a 10 6e 1e              fmov     d10, #1.00000000
0x00032f74: a0 22 40 bd              ldr      s0, [x21, #0x20]
0x00032f78: c1 02 62 1e              scvtf    d1, w22
0x00032f7c: 21 08 68 1e              fmul     d1, d1, d8
0x00032f80: 02 08 29 1e              fmul     s2, s0, s9
0x00032f84: 00 c0 22 1e              fcvt     d0, s0
0x00032f88: 20 18 60 1e              fdiv     d0, d1, d0
0x00032f8c: 4b c0 22 1e              fcvt     d11, s2
0x00032f90: 38 6a ff 97              bl       #0xd870                    ; -> sin@plt
0x00032f94: a8 16 40 f9              ldr      x8, [x21, #0x28]
0x00032f98: 00 08 6b 1e              fmul     d0, d0, d11
0x00032f9c: 00 28 6a 1e              fadd     d0, d0, d10
0x00032fa0: 00 40 62 1e              fcvt     s0, d0
0x00032fa4: 00 79 36 bc              str      s0, [x8, x22, lsl #2]
0x00032fa8: a8 0a 80 b9              ldrsw    x8, [x21, #8]
0x00032fac: d6 06 00 91              add      x22, x22, #1
0x00032fb0: df 02 08 eb              cmp      x22, x8
0x00032fb4: 0b fe ff 54              b.lt     #0x32f74
0x00032fb8: 68 42 40 b9              ldr      w8, [x19, #0x40]
0x00032fbc: e1 03 13 aa              mov      x1, x19
0x00032fc0: e2 03 14 aa              mov      x2, x20
0x00032fc4: 1f 01 00 71              cmp      w8, #0
0x00032fc8: 08 a5 88 1a              cinc     w8, w8, lt
0x00032fcc: 00 7d 01 13              asr      w0, w8, #1
0x00032fd0: ec 67 ff 97              bl       #0xcf80                    ; -> CornComputeTab@plt
0x00032fd4: f6 07 40 f9              ldr      x22, [sp, #8]
0x00032fd8: e0 03 1f 2a              mov      w0, wzr
0x00032fdc: 05 00 00 14              b        #0x32ff0                   ; -> CornFeatureInitialize+0x4a0
0x00032fe0: 80 09 00 d0              adrp     x0, #0x164000
0x00032fe4: 00 80 28 91              add      x0, x0, #0xa20
0x00032fe8: fa 66 ff 97              bl       #0xcbd0                    ; -> printf@plt
0x00032fec: 60 42 40 b9              ldr      w0, [x19, #0x40]
0x00032ff0: c8 16 40 f9              ldr      x8, [x22, #0x28]
0x00032ff4: e9 27 40 f9              ldr      x9, [sp, #0x48]
0x00032ff8: 1f 01 09 eb              cmp      x8, x9
0x00032ffc: 81 01 00 54              b.ne     #0x3302c
0x00033000: fd 7b 4d a9              ldp      x29, x30, [sp, #0xd0]
0x00033004: f4 4f 4c a9              ldp      x20, x19, [sp, #0xc0]
0x00033008: f6 57 4b a9              ldp      x22, x21, [sp, #0xb0]
0x0003300c: f8 5f 4a a9              ldp      x24, x23, [sp, #0xa0]
0x00033010: fa 67 49 a9              ldp      x26, x25, [sp, #0x90]
0x00033014: fc 6f 48 a9              ldp      x28, x27, [sp, #0x80]
0x00033018: e9 23 47 6d              ldp      d9, d8, [sp, #0x70]
0x0003301c: eb 2b 46 6d              ldp      d11, d10, [sp, #0x60]
0x00033020: ed 33 45 6d              ldp      d13, d12, [sp, #0x50]
0x00033024: ff 83 03 91              add      sp, sp, #0xe0
0x00033028: c0 03 5f d6              ret      
0x0003302c: e1 68 ff 97              bl       #0xd3b0                    ; -> __stack_chk_fail@plt
