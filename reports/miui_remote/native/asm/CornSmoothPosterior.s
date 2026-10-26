; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : CornSmoothPosterior (0x35eec)
; start   : 0x35eec
; size    : 0x100
; bytes   : 0x100   instructions: 64
; generated: 2026-10-23T23:30:11

0x00035eec: 08 24 40 b9              ldr      w8, [x0, #0x24]
0x00035ef0: 09 4c 40 b9              ldr      w9, [x0, #0x4c]
0x00035ef4: 08 01 09 6b              subs     w8, w8, w9
0x00035ef8: 8d 07 00 54              b.le     #0x35fe8
0x00035efc: 09 20 40 b9              ldr      w9, [x0, #0x20]
0x00035f00: 3f 01 08 6b              cmp      w9, w8
0x00035f04: 2a 07 00 54              b.ge     #0x35fe8
0x00035f08: 6b 09 00 f0              adrp     x11, #0x164000
0x00035f0c: 0a 48 80 b9              ldrsw    x10, [x0, #0x48]
0x00035f10: 60 1d 4c bd              ldr      s0, [x11, #0xc1c]
0x00035f14: 5f 05 00 71              cmp      w10, #1
0x00035f18: eb 05 00 54              b.lt     #0x35fd4
0x00035f1c: 0d 30 40 b9              ldr      w13, [x0, #0x30]
0x00035f20: 0e 3c 47 29              ldp      w14, w15, [x0, #0x38]
0x00035f24: ec 03 1f aa              mov      x12, xzr
0x00035f28: eb 03 1f 2a              mov      w11, wzr
0x00035f2c: b0 7d 40 93              sxtw     x16, w13
0x00035f30: f1 03 10 cb              neg      x17, x16
0x00035f34: 01 1c a0 4e              mov      v1.16b, v0.16b
0x00035f38: 4f 02 00 34              cbz      w15, #0x35f80
0x00035f3c: df 09 00 71              cmp      w14, #2
0x00035f40: e2 03 27 1e              fmov     s2, wzr
0x00035f44: ab 03 00 54              b.lt     #0x35fb8
0x00035f48: 12 20 40 f9              ldr      x18, [x0, #0x40]
0x00035f4c: e2 03 27 1e              fmov     s2, wzr
0x00035f50: e1 03 00 32              mov      w1, #1
0x00035f54: e2 03 0d 2a              mov      w2, w13
0x00035f58: 44 ce 22 8b              add      x4, x18, w2, sxtw #3
0x00035f5c: 83 78 6c bc              ldr      s3, [x4, x12, lsl #2]
0x00035f60: 43 04 00 11              add      w3, w2, #1
0x00035f64: 7f 00 0e 6b              cmp      w3, w14
0x00035f68: 21 04 00 11              add      w1, w1, #1
0x00035f6c: e2 a7 82 1a              csinc    w2, wzr, w2, ge
0x00035f70: df 01 01 6b              cmp      w14, w1
0x00035f74: 42 28 23 1e              fadd     s2, s2, s3
0x00035f78: 01 ff ff 54              b.ne     #0x35f58
0x00035f7c: 0f 00 00 14              b        #0x35fb8                   ; -> CornSmoothPosterior+0xcc
0x00035f80: 12 34 80 b9              ldrsw    x18, [x0, #0x34]
0x00035f84: e2 03 27 1e              fmov     s2, wzr
0x00035f88: 52 06 00 d1              sub      x18, x18, #1
0x00035f8c: bf 01 12 6b              cmp      w13, w18
0x00035f90: 4a 01 00 54              b.ge     #0x35fb8
0x00035f94: 01 20 40 f9              ldr      x1, [x0, #0x40]
0x00035f98: 32 02 12 8b              add      x18, x17, x18
0x00035f9c: e2 03 27 1e              fmov     s2, wzr
0x00035fa0: 21 0c 10 8b              add      x1, x1, x16, lsl #3
0x00035fa4: 21 08 0c 8b              add      x1, x1, x12, lsl #2
0x00035fa8: 23 84 40 bc              ldr      s3, [x1], #8
0x00035fac: 52 06 00 d1              sub      x18, x18, #1
0x00035fb0: 42 28 23 1e              fadd     s2, s2, s3
0x00035fb4: b2 ff ff b5              cbnz     x18, #0x35fa8
0x00035fb8: 20 20 22 1e              fcmp     s1, s2
0x00035fbc: 8b 41 8b 1a              csel     w11, w12, w11, mi
0x00035fc0: 8c 05 00 91              add      x12, x12, #1
0x00035fc4: 41 4c 21 1e              fcsel    s1, s2, s1, mi
0x00035fc8: 9f 01 0a eb              cmp      x12, x10
0x00035fcc: 6b fb ff 54              b.lt     #0x35f38
0x00035fd0: 02 00 00 14              b        #0x35fd8                   ; -> CornSmoothPosterior+0xec
0x00035fd4: eb 03 1f 2a              mov      w11, wzr
0x00035fd8: 29 05 00 11              add      w9, w9, #1
0x00035fdc: 3f 01 08 6b              cmp      w9, w8
0x00035fe0: ab f9 ff 54              b.lt     #0x35f14
0x00035fe4: 0b 28 00 b9              str      w11, [x0, #0x28]
0x00035fe8: c0 03 5f d6              ret      
