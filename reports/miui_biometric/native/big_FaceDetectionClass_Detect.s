; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : _ZN12stfaceunlock18FaceDetectionClass6DetectEPK10st_image_tRK19st_face_orientationjPS4_PP9st_face_tPii (0x144be8)
; start   : 0x144be8
; size    : 0xa9c
; bytes   : 0xa9c   instructions: 679
; generated: 2026-10-24T00:44:48

0x00144be8: ff 83 05 d1              sub      sp, sp, #0x160
0x00144bec: fd 7b 01 a9              stp      x29, x30, [sp, #0x10]
0x00144bf0: fd 43 00 91              add      x29, sp, #0x10
0x00144bf4: f3 53 02 a9              stp      x19, x20, [sp, #0x20]
0x00144bf8: a4 37 00 f9              str      x4, [x29, #0x68]
0x00144bfc: f3 03 02 aa              mov      x19, x2
0x00144c00: 04 00 40 f9              ldr      x4, [x0]
0x00144c04: fb 73 06 a9              stp      x27, x28, [sp, #0x60]
0x00144c08: f5 5b 03 a9              stp      x21, x22, [sp, #0x30]
0x00144c0c: f7 63 04 a9              stp      x23, x24, [sp, #0x40]
0x00144c10: f9 6b 05 a9              stp      x25, x26, [sp, #0x50]
0x00144c14: a5 43 00 f9              str      x5, [x29, #0x80]
0x00144c18: fb 03 00 aa              mov      x27, x0
0x00144c1c: a6 3f 00 f9              str      x6, [x29, #0x78]
0x00144c20: a7 8b 00 b9              str      w7, [x29, #0x88]
0x00144c24: 84 4c 00 b4              cbz      x4, #0x1455b4
0x00144c28: a0 a3 04 91              add      x0, x29, #0x128
0x00144c2c: a0 53 00 f9              str      x0, [x29, #0xa0]
0x00144c30: e2 03 00 aa              mov      x2, x0
0x00144c34: e0 03 01 aa              mov      x0, x1
0x00144c38: e1 03 02 aa              mov      x1, x2
0x00144c3c: a3 67 00 f9              str      x3, [x29, #0xc8]
0x00144c40: aa f0 ff 97              bl       #0x140ee8                  ; -> _ZN12stfaceunlock11ImageHepler21GetCvImageFromStImageEPK10st_image_tR10cv_image_t
0x00144c44: e4 03 00 2a              mov      w4, w0
0x00144c48: a3 67 40 f9              ldr      x3, [x29, #0xc8]
0x00144c4c: 40 34 00 35              cbnz     w0, #0x1452d4
0x00144c50: a0 37 41 b9              ldr      w0, [x29, #0x134]
0x00144c54: e2 03 03 2a              mov      w2, w3
0x00144c58: a1 3b 41 b9              ldr      w1, [x29, #0x138]
0x00144c5c: a3 53 03 91              add      x3, x29, #0xd4
0x00144c60: 00 10 2e 1e              fmov     s0, #1.00000000
0x00144c64: a4 63 03 91              add      x4, x29, #0xd8
0x00144c68: a5 73 03 91              add      x5, x29, #0xdc
0x00144c6c: bf 7b 00 f9              str      xzr, [x29, #0xf0]
0x00144c70: a0 df 00 bd              str      s0, [x29, #0xdc]
0x00144c74: 2a f0 ff 97              bl       #0x140d1c                  ; -> _ZN12stfaceunlock11ImageHepler12ShouldResizeEiijRiS1_Rf
0x00144c78: 00 1c 00 53              uxtb     w0, w0
0x00144c7c: 20 37 00 35              cbnz     w0, #0x145360
0x00144c80: a0 7b 40 f9              ldr      x0, [x29, #0xf0]
0x00144c84: fc 30 00 f0              adrp     x28, #0x763000
0x00144c88: a1 53 40 f9              ldr      x1, [x29, #0xa0]
0x00144c8c: 1f 00 1f eb              cmp      x0, xzr
0x00144c90: 74 02 40 b9              ldr      w20, [x19]
0x00144c94: 00 10 81 9a              csel     x0, x0, x1, ne
0x00144c98: bf e3 00 b9              str      wzr, [x29, #0xe0]
0x00144c9c: bf 7f 00 f9              str      xzr, [x29, #0xf8]
0x00144ca0: a0 4f 00 f9              str      x0, [x29, #0x98]
0x00144ca4: bf 83 00 f9              str      xzr, [x29, #0x100]
0x00144ca8: bf 87 00 f9              str      xzr, [x29, #0x108]
0x00144cac: bf 57 00 f9              str      xzr, [x29, #0xa8]
0x00144cb0: 74 37 00 34              cbz      w20, #0x14539c
0x00144cb4: e0 30 00 f0              adrp     x0, #0x763000
0x00144cb8: 00 00 15 91              add      x0, x0, #0x540
0x00144cbc: a0 3b 00 f9              str      x0, [x29, #0x70]
0x00144cc0: a0 43 04 91              add      x0, x29, #0x110
0x00144cc4: a0 63 00 f9              str      x0, [x29, #0xc0]
0x00144cc8: a0 63 04 91              add      x0, x29, #0x118
0x00144ccc: a0 67 00 f9              str      x0, [x29, #0xc8]
0x00144cd0: a0 e3 03 91              add      x0, x29, #0xf8
0x00144cd4: a0 4b 00 f9              str      x0, [x29, #0x90]
0x00144cd8: a0 b3 03 91              add      x0, x29, #0xec
0x00144cdc: a0 5b 00 f9              str      x0, [x29, #0xb0]
0x00144ce0: 74 26 00 37              tbnz     w20, #0, #0x1451ac
0x00144ce4: 34 28 08 37              tbnz     w20, #1, #0x1451e8
0x00144ce8: 94 2a 18 37              tbnz     w20, #3, #0x145238
0x00144cec: f4 2c 10 37              tbnz     w20, #2, #0x145288
0x00144cf0: b6 7f 40 f9              ldr      x22, [x29, #0xf8]
0x00144cf4: 16 27 00 b4              cbz      x22, #0x1451d4
0x00144cf8: e0 30 00 f0              adrp     x0, #0x763000
0x00144cfc: 81 03 0c 91              add      x1, x28, #0x300
0x00144d00: 00 80 15 91              add      x0, x0, #0x560
0x00144d04: 42 0d 80 52              mov      w2, #0x6a
0x00144d08: 30 e7 ff 97              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x00144d0c: f9 30 00 b0              adrp     x25, #0x761000
0x00144d10: f7 30 00 b0              adrp     x23, #0x761000
0x00144d14: f8 30 00 f0              adrp     x24, #0x763000
0x00144d18: 20 43 25 91              add      x0, x25, #0x950
0x00144d1c: e1 a2 25 91              add      x1, x23, #0x968
0x00144d20: 82 04 80 52              mov      w2, #0x24
0x00144d24: 03 43 16 91              add      x3, x24, #0x590
0x00144d28: bf 8b 00 f9              str      xzr, [x29, #0x110]
0x00144d2c: bf eb 00 b9              str      wzr, [x29, #0xe8]
0x00144d30: ac e7 ff 97              bl       #0x13ebe0                  ; -> _Z4LogWPKcz
0x00144d34: 3d 96 17 94              bl       #0x72a628                  ; -> _ZNSt6chrono3_V212steady_clock3nowEv
0x00144d38: ba a3 03 91              add      x26, x29, #0xe8
0x00144d3c: a1 67 40 f9              ldr      x1, [x29, #0xc8]
0x00144d40: f3 03 00 aa              mov      x19, x0
0x00144d44: a2 5b 40 f9              ldr      x2, [x29, #0xb0]
0x00144d48: 26 00 80 52              mov      w6, #1
0x00144d4c: a4 63 40 f9              ldr      x4, [x29, #0xc0]
0x00144d50: 03 00 80 52              mov      w3, #0
0x00144d54: 20 00 00 f9              str      x0, [x1]
0x00144d58: e5 03 1a aa              mov      x5, x26
0x00144d5c: 60 03 40 f9              ldr      x0, [x27]
0x00144d60: e1 03 16 aa              mov      x1, x22
0x00144d64: a6 ef 00 b9              str      w6, [x29, #0xec]
0x00144d68: 78 5f ff 97              bl       #0x11cb48                  ; -> _ZN12stfaceunlock20FaceDetectionManager6DetectEPK10cv_image_tRK19st_face_orientationjPP9st_rect_tPi
0x00144d6c: f5 03 00 2a              mov      w21, w0
0x00144d70: 2e 96 17 94              bl       #0x72a628                  ; -> _ZNSt6chrono3_V212steady_clock3nowEv
0x00144d74: 65 9b 86 d2              mov      x5, #0x34db
0x00144d78: 04 00 13 cb              sub      x4, x0, x19
0x00144d7c: c5 f6 ba f2              movk     x5, #0xd7b6, lsl #16
0x00144d80: f3 30 00 b0              adrp     x19, #0x761000
0x00144d84: 45 d0 db f2              movk     x5, #0xde82, lsl #32
0x00144d88: 60 42 2b 91              add      x0, x19, #0xad0
0x00144d8c: 65 63 e8 f2              movk     x5, #0x431b, lsl #48
0x00144d90: e1 a2 25 91              add      x1, x23, #0x968
0x00144d94: 62 05 80 52              mov      w2, #0x2b
0x00144d98: 03 43 16 91              add      x3, x24, #0x590
0x00144d9c: 86 7c 45 9b              smulh    x6, x4, x5
0x00144da0: c5 fc 52 93              asr      x5, x6, #0x12
0x00144da4: a4 fc 84 cb              sub      x4, x5, x4, asr #63
0x00144da8: 8e e7 ff 97              bl       #0x13ebe0                  ; -> _Z4LogWPKcz
0x00144dac: e0 30 00 f0              adrp     x0, #0x763000
0x00144db0: 81 03 0c 91              add      x1, x28, #0x300
0x00144db4: 00 80 16 91              add      x0, x0, #0x5a0
0x00144db8: 22 0e 80 52              mov      w2, #0x71
0x00144dbc: 03 e7 ff 97              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x00144dc0: f5 2a 00 35              cbnz     w21, #0x14531c
0x00144dc4: a0 eb 40 b9              ldr      w0, [x29, #0xe8]
0x00144dc8: b8 8b 40 f9              ldr      x24, [x29, #0x110]
0x00144dcc: a0 17 00 35              cbnz     w0, #0x1450c0
0x00144dd0: 78 00 00 b4              cbz      x24, #0x144ddc
0x00144dd4: e0 03 18 aa              mov      x0, x24
0x00144dd8: cf 5f ff 97              bl       #0x11cd14                  ; -> _ZN12stfaceunlock20FaceDetectionManager22ReleaseDetectionResultEP9st_rect_t
0x00144ddc: b3 e3 40 b9              ldr      w19, [x29, #0xe0]
0x00144de0: 54 00 00 34              cbz      w20, #0x144de8
0x00144de4: f3 f7 ff 34              cbz      w19, #0x144ce0
0x00144de8: b3 2d 00 34              cbz      w19, #0x14539c
0x00144dec: a0 83 40 f9              ldr      x0, [x29, #0x100]
0x00144df0: 01 00 cc d2              mov      x1, #0x600000000000
0x00144df4: a0 33 00 f9              str      x0, [x29, #0x60]
0x00144df8: 41 01 e0 f2              movk     x1, #0xa, lsl #48
0x00144dfc: 00 00 80 92              mov      x0, #-1
0x00144e00: 3f c0 33 eb              cmp      x1, w19, sxtw
0x00144e04: 63 00 00 54              b.lo     #0x144e10
0x00144e08: 00 87 81 52              mov      w0, #0xc38
0x00144e0c: 60 7e 20 9b              smull    x0, w19, w0
0x00144e10: 21 6f 02 d0              adrp     x1, #0x4f2a000
0x00144e14: 21 6c 46 f9              ldr      x1, [x1, #0xcd8]
0x00144e18: b4 97 16 94              bl       #0x6eace8                  ; -> _ZnamRKSt9nothrow_t
0x00144e1c: a0 47 00 f9              str      x0, [x29, #0x88]
0x00144e20: f4 03 00 aa              mov      x20, x0
0x00144e24: 40 2f 00 b4              cbz      x0, #0x14540c
0x00144e28: a3 e3 40 b9              ldr      w3, [x29, #0xe0]
0x00144e2c: 02 87 81 52              mov      w2, #0xc38
0x00144e30: 01 00 80 52              mov      w1, #0
0x00144e34: 62 7c 22 9b              smull    x2, w3, w2
0x00144e38: fa 0e fd 97              bl       #0x88a20                   ; -> memset@plt
0x00144e3c: a0 e3 40 b9              ldr      w0, [x29, #0xe0]
0x00144e40: a0 73 00 b9              str      w0, [x29, #0x70]
0x00144e44: 1f 00 1f 6b              cmp      w0, wzr
0x00144e48: ed 40 00 54              b.le     #0x145664
0x00144e4c: e0 30 00 f0              adrp     x0, #0x763000
0x00144e50: f5 03 14 aa              mov      x21, x20
0x00144e54: 00 80 1a 91              add      x0, x0, #0x6a0
0x00144e58: a0 53 00 f9              str      x0, [x29, #0xa0]
0x00144e5c: a0 93 03 91              add      x0, x29, #0xe4
0x00144e60: a0 4f 00 f9              str      x0, [x29, #0x98]
0x00144e64: e0 30 00 f0              adrp     x0, #0x763000
0x00144e68: 94 c2 00 91              add      x20, x20, #0x30
0x00144e6c: 00 60 1b 91              add      x0, x0, #0x6d8
0x00144e70: 17 00 80 d2              mov      x23, #0
0x00144e74: 18 00 80 d2              mov      x24, #0
0x00144e78: 19 00 80 52              mov      w25, #0
0x00144e7c: a0 4b 00 f9              str      x0, [x29, #0x90]
0x00144e80: a0 87 40 f9              ldr      x0, [x29, #0x108]
0x00144e84: 00 00 18 8b              add      x0, x0, x24
0x00144e88: 00 04 40 a9              ldp      x0, x1, [x0]
0x00144e8c: a0 06 00 a9              stp      x0, x1, [x21]
0x00144e90: a0 83 40 f9              ldr      x0, [x29, #0x100]
0x00144e94: 01 00 17 8b              add      x1, x0, x23
0x00144e98: 28 10 40 b9              ldr      w8, [x1, #0x10]            ; -> __G61__android16_bcl_binary_data@got
0x00144e9c: 88 42 1e b8              stur     w8, [x20, #-0x1c]
0x00144ea0: 1f 01 1f 6b              cmp      w8, wzr
0x00144ea4: ad 09 00 54              b.le     #0x144fd8
0x00144ea8: 02 68 77 f8              ldr      x2, [x0, x23]
0x00144eac: 80 c2 00 91              add      x0, x20, #0x30
0x00144eb0: 5f 00 00 eb              cmp      x2, x0
0x00144eb4: 40 c0 00 91              add      x0, x2, #0x30
0x00144eb8: e3 37 9f 1a              cset     w3, hs
0x00144ebc: 9f 02 00 eb              cmp      x20, x0
0x00144ec0: e0 37 9f 1a              cset     w0, hs
0x00144ec4: 60 00 00 2a              orr      w0, w3, w0
0x00144ec8: 00 28 00 34              cbz      w0, #0x1453c8
0x00144ecc: 1f 15 00 71              cmp      w8, #5
0x00144ed0: c9 27 00 54              b.ls     #0x1453c8
0x00144ed4: 0a 11 00 51              sub      w10, w8, #4
0x00144ed8: e3 03 02 aa              mov      x3, x2
0x00144edc: 4a 7d 02 53              lsr      w10, w10, #2
0x00144ee0: e0 03 14 aa              mov      x0, x20
0x00144ee4: 4a 05 00 11              add      w10, w10, #1
0x00144ee8: 07 00 80 52              mov      w7, #0
0x00144eec: 4b 75 1e 53              lsl      w11, w10, #2
0x00144ef0: e6 03 03 aa              mov      x6, x3
0x00144ef4: e5 03 00 aa              mov      x5, x0
0x00144ef8: 69 80 00 91              add      x9, x3, #0x20
0x00144efc: 04 80 00 91              add      x4, x0, #0x20
0x00144f00: c2 04 c1 3c              ldr      q2, [x6], #0x10
0x00144f04: e7 04 00 11              add      w7, w7, #1
0x00144f08: 20 01 c0 3d              ldr      q0, [x9]
0x00144f0c: ff 00 0a 6b              cmp      w7, w10
0x00144f10: 63 c0 00 91              add      x3, x3, #0x30
0x00144f14: 00 c0 00 91              add      x0, x0, #0x30
0x00144f18: c1 00 c0 3d              ldr      q1, [x6]
0x00144f1c: a2 04 81 3c              str      q2, [x5], #0x10
0x00144f20: a1 00 80 3d              str      q1, [x5]
0x00144f24: 80 00 80 3d              str      q0, [x4]
0x00144f28: 43 fe ff 54              b.lo     #0x144ef0
0x00144f2c: 1f 01 0b 6b              cmp      w8, w11
0x00144f30: 40 05 00 54              b.eq     #0x144fd8
0x00144f34: 60 7d 40 93              sxtw     x0, w11
0x00144f38: 63 05 00 11              add      w3, w11, #1
0x00144f3c: 04 ec 7c d3              lsl      x4, x0, #4
0x00144f40: 1f 01 03 6b              cmp      w8, w3
0x00144f44: 80 08 00 cb              sub      x0, x4, x0, lsl #2
0x00144f48: 46 00 00 8b              add      x6, x2, x0
0x00144f4c: a5 02 00 8b              add      x5, x21, x0
0x00144f50: 49 68 60 b8              ldr      w9, [x2, x0]
0x00144f54: c7 04 40 b9              ldr      w7, [x6, #4]
0x00144f58: c6 08 40 b9              ldr      w6, [x6, #8]
0x00144f5c: a9 30 00 b9              str      w9, [x5, #0x30]
0x00144f60: a7 34 00 b9              str      w7, [x5, #0x34]
0x00144f64: a6 38 00 b9              str      w6, [x5, #0x38]
0x00144f68: 8d 03 00 54              b.le     #0x144fd8
0x00144f6c: 04 30 00 91              add      x4, x0, #0xc
0x00144f70: 63 7c 40 93              sxtw     x3, w3
0x00144f74: 45 00 04 8b              add      x5, x2, x4
0x00144f78: 66 ec 7c d3              lsl      x6, x3, #4
0x00144f7c: 47 68 64 b8              ldr      w7, [x2, x4]
0x00144f80: c3 08 03 cb              sub      x3, x6, x3, lsl #2
0x00144f84: a6 04 40 b9              ldr      w6, [x5, #4]
0x00144f88: a3 02 03 8b              add      x3, x21, x3
0x00144f8c: a5 08 40 b9              ldr      w5, [x5, #8]
0x00144f90: 6b 09 00 11              add      w11, w11, #2
0x00144f94: 1f 01 0b 6b              cmp      w8, w11
0x00144f98: 67 30 00 b9              str      w7, [x3, #0x30]
0x00144f9c: 66 34 00 b9              str      w6, [x3, #0x34]
0x00144fa0: 65 38 00 b9              str      w5, [x3, #0x38]
0x00144fa4: ad 01 00 54              b.le     #0x144fd8
0x00144fa8: 00 60 00 91              add      x0, x0, #0x18
0x00144fac: 6b 7d 40 93              sxtw     x11, w11
0x00144fb0: 48 00 00 8b              add      x8, x2, x0
0x00144fb4: 63 ed 7c d3              lsl      x3, x11, #4
0x00144fb8: 45 68 60 b8              ldr      w5, [x2, x0]
0x00144fbc: 60 08 0b cb              sub      x0, x3, x11, lsl #2
0x00144fc0: 04 05 40 b9              ldr      w4, [x8, #4]
0x00144fc4: a0 02 00 8b              add      x0, x21, x0
0x00144fc8: 03 09 40 b9              ldr      w3, [x8, #8]
0x00144fcc: 05 30 00 b9              str      w5, [x0, #0x30]
0x00144fd0: 04 34 00 b9              str      w4, [x0, #0x34]
0x00144fd4: 03 38 00 b9              str      w3, [x0, #0x38]
0x00144fd8: 60 07 40 f9              ldr      x0, [x27, #8]
0x00144fdc: 82 22 00 d1              sub      x2, x20, #8
0x00144fe0: 9f 91 ff 97              bl       #0x12965c                  ; -> _ZN12stfaceunlock16FaceAlignManager18GetOcclusionStatusEP14cv_landmarks_tPi
0x00144fe4: a0 83 40 f9              ldr      x0, [x29, #0x100]
0x00144fe8: a1 67 40 f9              ldr      x1, [x29, #0xc8]
0x00144fec: 00 00 17 8b              add      x0, x0, x23
0x00144ff0: bf 8f 00 f9              str      xzr, [x29, #0x118]
0x00144ff4: 93 8f ff 97              bl       #0x128e40                  ; -> _ZN12stfaceunlock18FaceAlignInterFace13CopyLandmarksEP14cv_landmarks_tPS2_
0x00144ff8: a1 8f 40 f9              ldr      x1, [x29, #0x118]
0x00144ffc: e2 15 80 52              mov      w2, #0xaf
0x00145000: a0 53 40 f9              ldr      x0, [x29, #0xa0]
0x00145004: 81 02 06 f9              str      x1, [x20, #0xc00]
0x00145008: 81 03 0c 91              add      x1, x28, #0x300
0x0014500c: 6f e6 ff 97              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x00145010: 60 0b 40 f9              ldr      x0, [x27, #0x10]
0x00145014: e1 03 14 aa              mov      x1, x20
0x00145018: 82 42 5e b8              ldur     w2, [x20, #-0x1c]
0x0014501c: e4 03 1a aa              mov      x4, x26
0x00145020: a3 4f 40 f9              ldr      x3, [x29, #0x98]
0x00145024: a5 5b 40 f9              ldr      x5, [x29, #0xb0]
0x00145028: a6 63 40 f9              ldr      x6, [x29, #0xc0]
0x0014502c: 8f 92 ff 97              bl       #0x129a68                  ; -> _ZN12stfaceunlock12FaceCalcPose8CalcPoseEPK11st_pointf_tiPfS4_S4_S4_
0x00145030: f6 03 00 2a              mov      w22, w0
0x00145034: 00 1f 00 35              cbnz     w0, #0x145414
0x00145038: a0 4b 40 f9              ldr      x0, [x29, #0x90]
0x0014503c: 81 03 0c 91              add      x1, x28, #0x300
0x00145040: c2 16 80 52              mov      w2, #0xb6
0x00145044: 61 e6 ff 97              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x00145048: a1 e7 40 b9              ldr      w1, [x29, #0xe4]
0x0014504c: a0 e3 40 b9              ldr      w0, [x29, #0xe0]
0x00145050: 39 07 00 11              add      w25, w25, #1
0x00145054: 18 43 00 91              add      x24, x24, #0x10
0x00145058: 1f 00 19 6b              cmp      w0, w25
0x0014505c: b5 e2 30 91              add      x21, x21, #0xc38
0x00145060: f7 e2 00 91              add      x23, x23, #0x38
0x00145064: 81 82 1e b8              stur     w1, [x20, #-0x18]
0x00145068: a1 eb 40 b9              ldr      w1, [x29, #0xe8]
0x0014506c: 81 c2 1e b8              stur     w1, [x20, #-0x14]
0x00145070: a1 ef 40 b9              ldr      w1, [x29, #0xec]
0x00145074: 81 02 1f b8              stur     w1, [x20, #-0x10]
0x00145078: a1 13 41 b9              ldr      w1, [x29, #0x110]
0x0014507c: 81 42 1f b8              stur     w1, [x20, #-0xc]
0x00145080: 94 e2 30 91              add      x20, x20, #0xc38
0x00145084: ec ef ff 54              b.gt     #0x144e80
0x00145088: a1 37 40 f9              ldr      x1, [x29, #0x68]
0x0014508c: 16 00 80 52              mov      w22, #0
0x00145090: a2 bf 40 b9              ldr      w2, [x29, #0xbc]
0x00145094: 22 00 00 b9              str      w2, [x1]
0x00145098: a1 43 40 f9              ldr      x1, [x29, #0x80]
0x0014509c: a2 47 40 f9              ldr      x2, [x29, #0x88]
0x001450a0: 22 00 00 f9              str      x2, [x1]
0x001450a4: a1 3f 40 f9              ldr      x1, [x29, #0x78]
0x001450a8: 20 00 00 b9              str      w0, [x1]
0x001450ac: a0 33 40 f9              ldr      x0, [x29, #0x60]
0x001450b0: 40 14 00 b4              cbz      x0, #0x145338
0x001450b4: e1 03 13 2a              mov      w1, w19
0x001450b8: 55 a0 05 94              bl       #0x2ad20c                  ; -> cv_common_alignment_release_result
0x001450bc: 9f 00 00 14              b        #0x145338                  ; -> _ZN12stfaceunlock18FaceDetectionClass6DetectEPK10st_image_tRK19st_face_orientationjPS4_PP9st_face_tPii+0x750
0x001450c0: e0 30 00 d0              adrp     x0, #0x763000
0x001450c4: a3 bf 40 b9              ldr      w3, [x29, #0xbc]
0x001450c8: 81 03 0c 91              add      x1, x28, #0x300
0x001450cc: 00 00 18 91              add      x0, x0, #0x600
0x001450d0: 22 0f 80 52              mov      w2, #0x79
0x001450d4: 80 e6 ff 97              bl       #0x13ead4                  ; -> _Z4LogIPKcz
0x001450d8: e0 30 00 d0              adrp     x0, #0x763000
0x001450dc: 81 03 0c 91              add      x1, x28, #0x300
0x001450e0: 00 a0 18 91              add      x0, x0, #0x628
0x001450e4: 62 0f 80 52              mov      w2, #0x7b
0x001450e8: 38 e6 ff 97              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x001450ec: f5 30 00 d0              adrp     x21, #0x763000
0x001450f0: 20 43 25 91              add      x0, x25, #0x950
0x001450f4: e1 a2 25 91              add      x1, x23, #0x968
0x001450f8: 82 04 80 52              mov      w2, #0x24
0x001450fc: a3 62 19 91              add      x3, x21, #0x658
0x00145100: b8 e6 ff 97              bl       #0x13ebe0                  ; -> _Z4LogWPKcz
0x00145104: 49 95 17 94              bl       #0x72a628                  ; -> _ZNSt6chrono3_V212steady_clock3nowEv
0x00145108: f9 03 00 aa              mov      x25, x0
0x0014510c: a2 67 40 f9              ldr      x2, [x29, #0xc8]
0x00145110: e3 03 18 aa              mov      x3, x24
0x00145114: 61 07 40 f9              ldr      x1, [x27, #8]
0x00145118: a6 03 04 91              add      x6, x29, #0x100
0x0014511c: a0 df 40 bd              ldr      s0, [x29, #0xdc]
0x00145120: a4 eb 40 b9              ldr      w4, [x29, #0xe8]
0x00145124: 40 00 00 f9              str      x0, [x2]
0x00145128: a7 23 04 91              add      x7, x29, #0x108
0x0014512c: a0 8b 40 b9              ldr      w0, [x29, #0x88]
0x00145130: e0 0b 00 b9              str      w0, [sp, #8]
0x00145134: a0 83 03 91              add      x0, x29, #0xe0
0x00145138: a2 53 40 f9              ldr      x2, [x29, #0xa0]
0x0014513c: a5 bf 40 b9              ldr      w5, [x29, #0xbc]
0x00145140: e0 03 00 f9              str      x0, [sp]
0x00145144: e0 03 01 aa              mov      x0, x1
0x00145148: e1 03 16 aa              mov      x1, x22
0x0014514c: f7 90 ff 97              bl       #0x129528                  ; -> _ZN12stfaceunlock16FaceAlignManager5AlignEPK10cv_image_tPS1_P9st_rect_ti19st_face_orientationfPP14cv_landmarks_tPS6_Pii
0x00145150: f6 03 00 2a              mov      w22, w0
0x00145154: e0 1b 00 35              cbnz     w0, #0x1454d0
0x00145158: a1 87 40 f9              ldr      x1, [x29, #0x108]
0x0014515c: a1 00 00 b4              cbz      x1, #0x145170
0x00145160: a0 57 40 f9              ldr      x0, [x29, #0xa8]
0x00145164: a1 57 00 f9              str      x1, [x29, #0xa8]
0x00145168: 40 00 00 b4              cbz      x0, #0x145170
0x0014516c: 27 90 16 94              bl       #0x6e9208                  ; -> _ZdaPv
0x00145170: 76 9b 86 d2              mov      x22, #0x34db
0x00145174: 2d 95 17 94              bl       #0x72a628                  ; -> _ZNSt6chrono3_V212steady_clock3nowEv
0x00145178: d6 f6 ba f2              movk     x22, #0xd7b6, lsl #16
0x0014517c: 04 00 19 cb              sub      x4, x0, x25
0x00145180: 56 d0 db f2              movk     x22, #0xde82, lsl #32
0x00145184: 60 42 2b 91              add      x0, x19, #0xad0
0x00145188: 76 63 e8 f2              movk     x22, #0x431b, lsl #48
0x0014518c: e1 a2 25 91              add      x1, x23, #0x968
0x00145190: 62 05 80 52              mov      w2, #0x2b
0x00145194: a3 62 19 91              add      x3, x21, #0x658
0x00145198: 85 7c 56 9b              smulh    x5, x4, x22
0x0014519c: b6 fc 52 93              asr      x22, x5, #0x12
0x001451a0: c4 fe 84 cb              sub      x4, x22, x4, asr #63
0x001451a4: 8f e6 ff 97              bl       #0x13ebe0                  ; -> _Z4LogWPKcz
0x001451a8: 0a ff ff 17              b        #0x144dd0                  ; -> _ZN12stfaceunlock18FaceDetectionClass6DetectEPK10st_image_tRK19st_face_orientationjPS4_PP9st_face_tPii+0x1e8
0x001451ac: e0 30 00 d0              adrp     x0, #0x763000
0x001451b0: 81 03 0c 91              add      x1, x28, #0x300
0x001451b4: 00 60 13 91              add      x0, x0, #0x4d8
0x001451b8: 22 08 80 52              mov      w2, #0x41
0x001451bc: 46 e6 ff 97              bl       #0x13ead4                  ; -> _Z4LogIPKcz
0x001451c0: b6 7f 40 f9              ldr      x22, [x29, #0xf8]
0x001451c4: 20 00 80 52              mov      w0, #1
0x001451c8: a0 bf 00 b9              str      w0, [x29, #0xbc]
0x001451cc: 94 02 00 52              eor      w20, w20, #1
0x001451d0: 56 d9 ff b5              cbnz     x22, #0x144cf8
0x001451d4: b6 7b 40 f9              ldr      x22, [x29, #0xf0]
0x001451d8: a0 53 40 f9              ldr      x0, [x29, #0xa0]
0x001451dc: df 02 1f eb              cmp      x22, xzr
0x001451e0: d6 12 80 9a              csel     x22, x22, x0, ne
0x001451e4: c5 fe ff 17              b        #0x144cf8                  ; -> _ZN12stfaceunlock18FaceDetectionClass6DetectEPK10st_image_tRK19st_face_orientationjPS4_PP9st_face_tPii+0x110
0x001451e8: e0 30 00 d0              adrp     x0, #0x763000
0x001451ec: 81 03 0c 91              add      x1, x28, #0x300
0x001451f0: 00 e0 13 91              add      x0, x0, #0x4f8
0x001451f4: a2 08 80 52              mov      w2, #0x45
0x001451f8: 37 e6 ff 97              bl       #0x13ead4                  ; -> _Z4LogIPKcz
0x001451fc: 44 00 80 52              mov      w4, #2
0x00145200: a0 4f 40 f9              ldr      x0, [x29, #0x98]
0x00145204: a1 63 40 f9              ldr      x1, [x29, #0xc0]
0x00145208: 94 02 1f 52              eor      w20, w20, #2
0x0014520c: a2 67 40 f9              ldr      x2, [x29, #0xc8]
0x00145210: a3 4b 40 f9              ldr      x3, [x29, #0x90]
0x00145214: a4 13 01 b9              str      w4, [x29, #0x110]
0x00145218: 24 00 80 52              mov      w4, #1
0x0014521c: a4 1b 01 b9              str      w4, [x29, #0x118]
0x00145220: cb f0 ff 97              bl       #0x14154c                  ; -> _ZN12stfaceunlock11ImageHepler11RotateImageEPK10cv_image_tRK19st_face_orientationS6_RSt10unique_ptrIS1_NS_19cv_image_destructorEE
0x00145224: f6 03 00 2a              mov      w22, w0
0x00145228: 60 14 00 35              cbnz     w0, #0x1454b4
0x0014522c: 40 00 80 52              mov      w0, #2
0x00145230: a0 bf 00 b9              str      w0, [x29, #0xbc]
0x00145234: af fe ff 17              b        #0x144cf0                  ; -> _ZN12stfaceunlock18FaceDetectionClass6DetectEPK10st_image_tRK19st_face_orientationjPS4_PP9st_face_tPii+0x108
0x00145238: e0 30 00 d0              adrp     x0, #0x763000
0x0014523c: 81 03 0c 91              add      x1, x28, #0x300
0x00145240: 00 60 14 91              add      x0, x0, #0x518
0x00145244: c2 09 80 52              mov      w2, #0x4e
0x00145248: 23 e6 ff 97              bl       #0x13ead4                  ; -> _Z4LogIPKcz
0x0014524c: 04 01 80 52              mov      w4, #8
0x00145250: a0 4f 40 f9              ldr      x0, [x29, #0x98]
0x00145254: a1 63 40 f9              ldr      x1, [x29, #0xc0]
0x00145258: 94 02 1d 52              eor      w20, w20, #8
0x0014525c: a2 67 40 f9              ldr      x2, [x29, #0xc8]
0x00145260: a3 4b 40 f9              ldr      x3, [x29, #0x90]
0x00145264: a4 13 01 b9              str      w4, [x29, #0x110]
0x00145268: 24 00 80 52              mov      w4, #1
0x0014526c: a4 1b 01 b9              str      w4, [x29, #0x118]
0x00145270: b7 f0 ff 97              bl       #0x14154c                  ; -> _ZN12stfaceunlock11ImageHepler11RotateImageEPK10cv_image_tRK19st_face_orientationS6_RSt10unique_ptrIS1_NS_19cv_image_destructorEE
0x00145274: f6 03 00 2a              mov      w22, w0
0x00145278: a0 16 00 35              cbnz     w0, #0x14554c
0x0014527c: 00 01 80 52              mov      w0, #8
0x00145280: a0 bf 00 b9              str      w0, [x29, #0xbc]
0x00145284: 9b fe ff 17              b        #0x144cf0                  ; -> _ZN12stfaceunlock18FaceDetectionClass6DetectEPK10st_image_tRK19st_face_orientationjPS4_PP9st_face_tPii+0x108
0x00145288: a0 3b 40 f9              ldr      x0, [x29, #0x70]
0x0014528c: 81 03 0c 91              add      x1, x28, #0x300
0x00145290: e2 0a 80 52              mov      w2, #0x57
0x00145294: 10 e6 ff 97              bl       #0x13ead4                  ; -> _Z4LogIPKcz
0x00145298: 84 00 80 52              mov      w4, #4
0x0014529c: a0 4f 40 f9              ldr      x0, [x29, #0x98]
0x001452a0: a1 63 40 f9              ldr      x1, [x29, #0xc0]
0x001452a4: 94 02 1e 52              eor      w20, w20, #4
0x001452a8: a2 67 40 f9              ldr      x2, [x29, #0xc8]
0x001452ac: a3 4b 40 f9              ldr      x3, [x29, #0x90]
0x001452b0: a4 13 01 b9              str      w4, [x29, #0x110]
0x001452b4: 24 00 80 52              mov      w4, #1
0x001452b8: a4 1b 01 b9              str      w4, [x29, #0x118]
0x001452bc: a4 f0 ff 97              bl       #0x14154c                  ; -> _ZN12stfaceunlock11ImageHepler11RotateImageEPK10cv_image_tRK19st_face_orientationS6_RSt10unique_ptrIS1_NS_19cv_image_destructorEE
0x001452c0: f6 03 00 2a              mov      w22, w0
0x001452c4: 60 13 00 35              cbnz     w0, #0x145530
0x001452c8: 80 00 80 52              mov      w0, #4
0x001452cc: a0 bf 00 b9              str      w0, [x29, #0xbc]
0x001452d0: 88 fe ff 17              b        #0x144cf0                  ; -> _ZN12stfaceunlock18FaceDetectionClass6DetectEPK10st_image_tRK19st_face_orientationjPS4_PP9st_face_tPii+0x108
0x001452d4: a0 30 00 f0              adrp     x0, #0x75c000
0x001452d8: e1 30 00 d0              adrp     x1, #0x763000
0x001452dc: 00 60 15 91              add      x0, x0, #0x558
0x001452e0: 21 00 0c 91              add      x1, x1, #0x300
0x001452e4: 22 04 80 52              mov      w2, #0x21
0x001452e8: e3 03 04 2a              mov      w3, w4
0x001452ec: f6 03 04 2a              mov      w22, w4
0x001452f0: 7f e6 ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x001452f4: bf 43 00 d1              sub      sp, x29, #0x10
0x001452f8: e0 03 16 2a              mov      w0, w22
0x001452fc: f3 53 42 a9              ldp      x19, x20, [sp, #0x20]
0x00145300: f5 5b 43 a9              ldp      x21, x22, [sp, #0x30]
0x00145304: f7 63 44 a9              ldp      x23, x24, [sp, #0x40]
0x00145308: f9 6b 45 a9              ldp      x25, x26, [sp, #0x50]
0x0014530c: fb 73 46 a9              ldp      x27, x28, [sp, #0x60]
0x00145310: fd 7b 41 a9              ldp      x29, x30, [sp, #0x10]
0x00145314: ff 83 05 91              add      sp, sp, #0x160
0x00145318: c0 03 5f d6              ret      
0x0014531c: e0 30 00 d0              adrp     x0, #0x763000
0x00145320: 81 03 0c 91              add      x1, x28, #0x300
0x00145324: 00 40 17 91              add      x0, x0, #0x5d0
0x00145328: 62 0e 80 52              mov      w2, #0x73
0x0014532c: e3 03 15 2a              mov      w3, w21
0x00145330: 6f e6 ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x00145334: f6 03 15 2a              mov      w22, w21
0x00145338: a0 57 40 f9              ldr      x0, [x29, #0xa8]
0x0014533c: 40 00 00 b4              cbz      x0, #0x145344
0x00145340: b2 8f 16 94              bl       #0x6e9208                  ; -> _ZdaPv
0x00145344: a0 7f 40 f9              ldr      x0, [x29, #0xf8]
0x00145348: 40 00 00 b4              cbz      x0, #0x145350
0x0014534c: 7e 3a 06 94              bl       #0x2d3d44                  ; -> cv_image_release
0x00145350: a0 7b 40 f9              ldr      x0, [x29, #0xf0]
0x00145354: 00 fd ff b4              cbz      x0, #0x1452f4
0x00145358: 7b 3a 06 94              bl       #0x2d3d44                  ; -> cv_image_release
0x0014535c: e6 ff ff 17              b        #0x1452f4                  ; -> _ZN12stfaceunlock18FaceDetectionClass6DetectEPK10st_image_tRK19st_face_orientationjPS4_PP9st_face_tPii+0x70c
0x00145360: a1 d7 40 b9              ldr      w1, [x29, #0xd4]
0x00145364: a3 c3 03 91              add      x3, x29, #0xf0
0x00145368: a0 53 40 f9              ldr      x0, [x29, #0xa0]
0x0014536c: a2 db 40 b9              ldr      w2, [x29, #0xd8]
0x00145370: 26 ee ff 97              bl       #0x140c08                  ; -> _ZN12stfaceunlock11ImageHepler11ResizeImageEPK10cv_image_tiiRSt10unique_ptrIS1_NS_19cv_image_destructorEE
0x00145374: f6 03 00 2a              mov      w22, w0
0x00145378: 40 c8 ff 34              cbz      w0, #0x144c80
0x0014537c: a0 30 00 f0              adrp     x0, #0x75c000
0x00145380: e1 30 00 d0              adrp     x1, #0x763000
0x00145384: 00 20 1c 91              add      x0, x0, #0x708
0x00145388: 21 00 0c 91              add      x1, x1, #0x300
0x0014538c: 62 05 80 52              mov      w2, #0x2b
0x00145390: e3 03 16 2a              mov      w3, w22
0x00145394: 56 e6 ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x00145398: ee ff ff 17              b        #0x145350                  ; -> _ZN12stfaceunlock18FaceDetectionClass6DetectEPK10st_image_tRK19st_face_orientationjPS4_PP9st_face_tPii+0x768
0x0014539c: a0 43 40 f9              ldr      x0, [x29, #0x80]
0x001453a0: 81 03 0c 91              add      x1, x28, #0x300
0x001453a4: c2 11 80 52              mov      w2, #0x8e
0x001453a8: 1f 00 00 f9              str      xzr, [x0]
0x001453ac: a0 3f 40 f9              ldr      x0, [x29, #0x78]
0x001453b0: 1f 00 00 b9              str      wzr, [x0]
0x001453b4: e0 30 00 d0              adrp     x0, #0x763000
0x001453b8: 00 c0 19 91              add      x0, x0, #0x670
0x001453bc: 09 e6 ff 97              bl       #0x13ebe0                  ; -> _Z4LogWPKcz
0x001453c0: 16 00 80 52              mov      w22, #0
0x001453c4: dd ff ff 17              b        #0x145338                  ; -> _ZN12stfaceunlock18FaceDetectionClass6DetectEPK10st_image_tRK19st_face_orientationjPS4_PP9st_face_tPii+0x750
0x001453c8: 08 05 00 51              sub      w8, w8, #1
0x001453cc: 84 07 80 d2              mov      x4, #0x3c
0x001453d0: 83 01 80 52              mov      w3, #0xc
0x001453d4: e0 03 14 aa              mov      x0, x20
0x001453d8: 04 11 a3 9b              umaddl   x4, w8, w3, x4
0x001453dc: a4 02 04 8b              add      x4, x21, x4
0x001453e0: 43 00 40 b9              ldr      w3, [x2]
0x001453e4: 00 30 00 91              add      x0, x0, #0xc
0x001453e8: 42 30 00 91              add      x2, x2, #0xc
0x001453ec: 1f 00 04 eb              cmp      x0, x4
0x001453f0: 03 40 1f b8              stur     w3, [x0, #-0xc]
0x001453f4: 43 80 5f b8              ldur     w3, [x2, #-8]
0x001453f8: 03 80 1f b8              stur     w3, [x0, #-8]
0x001453fc: 43 c0 5f b8              ldur     w3, [x2, #-4]
0x00145400: 03 c0 1f b8              stur     w3, [x0, #-4]
0x00145404: e1 fe ff 54              b.ne     #0x1453e0
0x00145408: f4 fe ff 17              b        #0x144fd8                  ; -> _ZN12stfaceunlock18FaceDetectionClass6DetectEPK10st_image_tRK19st_face_orientationjPS4_PP9st_face_tPii+0x3f0
0x0014540c: 56 00 80 12              mov      w22, #-3
0x00145410: 27 ff ff 17              b        #0x1450ac                  ; -> _ZN12stfaceunlock18FaceDetectionClass6DetectEPK10st_image_tRK19st_face_orientationjPS4_PP9st_face_tPii+0x4c4
0x00145414: e0 30 00 d0              adrp     x0, #0x763000
0x00145418: 81 03 0c 91              add      x1, x28, #0x300
0x0014541c: 00 40 17 91              add      x0, x0, #0x5d0
0x00145420: 62 16 80 52              mov      w2, #0xb3
0x00145424: e3 03 16 2a              mov      w3, w22
0x00145428: 31 e6 ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0014542c: a0 73 40 b9              ldr      w0, [x29, #0x70]
0x00145430: 01 0d 83 d2              mov      x1, #0x1868
0x00145434: 17 87 81 52              mov      w23, #0xc38
0x00145438: a2 47 40 f9              ldr      x2, [x29, #0x88]
0x0014543c: 00 04 00 51              sub      w0, w0, #1
0x00145440: 55 c0 30 91              add      x21, x2, #0xc30
0x00145444: 17 04 b7 9b              umaddl   x23, w0, w23, x1
0x00145448: 57 00 17 8b              add      x23, x2, x23
0x0014544c: b4 02 40 f9              ldr      x20, [x21]
0x00145450: 74 02 00 b4              cbz      x20, #0x14549c
0x00145454: 80 02 40 f9              ldr      x0, [x20]
0x00145458: 40 00 00 b4              cbz      x0, #0x145460
0x0014545c: 6b 8f 16 94              bl       #0x6e9208                  ; -> _ZdaPv
0x00145460: 80 06 40 f9              ldr      x0, [x20, #8]
0x00145464: 40 00 00 b4              cbz      x0, #0x14546c
0x00145468: 68 8f 16 94              bl       #0x6e9208                  ; -> _ZdaPv
0x0014546c: 98 12 40 f9              ldr      x24, [x20, #0x20]
0x00145470: d8 00 00 b4              cbz      x24, #0x145488
0x00145474: 00 17 40 f9              ldr      x0, [x24, #0x28]
0x00145478: 40 00 00 b4              cbz      x0, #0x145480
0x0014547c: 63 8f 16 94              bl       #0x6e9208                  ; -> _ZdaPv
0x00145480: e0 03 18 aa              mov      x0, x24
0x00145484: 5f 8f 16 94              bl       #0x6e9200                  ; -> _ZdlPv
0x00145488: 80 16 40 f9              ldr      x0, [x20, #0x28]
0x0014548c: 40 00 00 b4              cbz      x0, #0x145494
0x00145490: 5e 8f 16 94              bl       #0x6e9208                  ; -> _ZdaPv
0x00145494: e0 03 14 aa              mov      x0, x20
0x00145498: 5a 8f 16 94              bl       #0x6e9200                  ; -> _ZdlPv
0x0014549c: b5 e2 30 91              add      x21, x21, #0xc38
0x001454a0: bf 02 17 eb              cmp      x21, x23
0x001454a4: 41 fd ff 54              b.ne     #0x14544c
0x001454a8: a0 47 40 f9              ldr      x0, [x29, #0x88]
0x001454ac: 57 8f 16 94              bl       #0x6e9208                  ; -> _ZdaPv
0x001454b0: ff fe ff 17              b        #0x1450ac                  ; -> _ZN12stfaceunlock18FaceDetectionClass6DetectEPK10st_image_tRK19st_face_orientationjPS4_PP9st_face_tPii+0x4c4
0x001454b4: a0 30 00 f0              adrp     x0, #0x75c000
0x001454b8: 81 03 0c 91              add      x1, x28, #0x300
0x001454bc: 00 60 3a 91              add      x0, x0, #0xe98
0x001454c0: 22 09 80 52              mov      w2, #0x49
0x001454c4: e3 03 16 2a              mov      w3, w22
0x001454c8: 09 e6 ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x001454cc: 9b ff ff 17              b        #0x145338                  ; -> _ZN12stfaceunlock18FaceDetectionClass6DetectEPK10st_image_tRK19st_face_orientationjPS4_PP9st_face_tPii+0x750
0x001454d0: e0 30 00 d0              adrp     x0, #0x763000
0x001454d4: 81 03 0c 91              add      x1, x28, #0x300
0x001454d8: 00 40 17 91              add      x0, x0, #0x5d0
0x001454dc: 22 10 80 52              mov      w2, #0x81
0x001454e0: e3 03 16 2a              mov      w3, w22
0x001454e4: 02 e6 ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x001454e8: 74 9b 86 d2              mov      x20, #0x34db
0x001454ec: 4f 94 17 94              bl       #0x72a628                  ; -> _ZNSt6chrono3_V212steady_clock3nowEv
0x001454f0: d4 f6 ba f2              movk     x20, #0xd7b6, lsl #16
0x001454f4: 04 00 19 cb              sub      x4, x0, x25
0x001454f8: 54 d0 db f2              movk     x20, #0xde82, lsl #32
0x001454fc: 60 42 2b 91              add      x0, x19, #0xad0
0x00145500: 74 63 e8 f2              movk     x20, #0x431b, lsl #48
0x00145504: e1 a2 25 91              add      x1, x23, #0x968
0x00145508: 62 05 80 52              mov      w2, #0x2b
0x0014550c: a3 62 19 91              add      x3, x21, #0x658
0x00145510: 9b 7c 54 9b              smulh    x27, x4, x20
0x00145514: 7a ff 52 93              asr      x26, x27, #0x12
0x00145518: 44 ff 84 cb              sub      x4, x26, x4, asr #63
0x0014551c: b1 e5 ff 97              bl       #0x13ebe0                  ; -> _Z4LogWPKcz
0x00145520: d8 f0 ff b4              cbz      x24, #0x145338
0x00145524: e0 03 18 aa              mov      x0, x24
0x00145528: fb 5d ff 97              bl       #0x11cd14                  ; -> _ZN12stfaceunlock20FaceDetectionManager22ReleaseDetectionResultEP9st_rect_t
0x0014552c: 83 ff ff 17              b        #0x145338                  ; -> _ZN12stfaceunlock18FaceDetectionClass6DetectEPK10st_image_tRK19st_face_orientationjPS4_PP9st_face_tPii+0x750
0x00145530: a0 30 00 f0              adrp     x0, #0x75c000
0x00145534: 81 03 0c 91              add      x1, x28, #0x300
0x00145538: 00 60 3a 91              add      x0, x0, #0xe98
0x0014553c: 62 0b 80 52              mov      w2, #0x5b
0x00145540: e3 03 16 2a              mov      w3, w22
0x00145544: ea e5 ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x00145548: 7c ff ff 17              b        #0x145338                  ; -> _ZN12stfaceunlock18FaceDetectionClass6DetectEPK10st_image_tRK19st_face_orientationjPS4_PP9st_face_tPii+0x750
0x0014554c: a0 30 00 f0              adrp     x0, #0x75c000
0x00145550: 81 03 0c 91              add      x1, x28, #0x300
0x00145554: 00 60 3a 91              add      x0, x0, #0xe98
0x00145558: 42 0a 80 52              mov      w2, #0x52
0x0014555c: e3 03 16 2a              mov      w3, w22
0x00145560: e3 e5 ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x00145564: 75 ff ff 17              b        #0x145338                  ; -> _ZN12stfaceunlock18FaceDetectionClass6DetectEPK10st_image_tRK19st_face_orientationjPS4_PP9st_face_tPii+0x750
0x00145568: f4 03 00 aa              mov      x20, x0
0x0014556c: a1 73 40 b9              ldr      w1, [x29, #0x70]
0x00145570: a0 47 40 f9              ldr      x0, [x29, #0x88]
0x00145574: 6c fd ff 97              bl       #0x144b24                  ; -> _ZN12stfaceunlock18FaceDetectionClass22ReleaseDetectionResultEP9st_face_ti
0x00145578: a0 33 40 f9              ldr      x0, [x29, #0x60]
0x0014557c: 60 00 00 b4              cbz      x0, #0x145588
0x00145580: e1 03 13 2a              mov      w1, w19
0x00145584: 22 9f 05 94              bl       #0x2ad20c                  ; -> cv_common_alignment_release_result
0x00145588: a0 57 40 f9              ldr      x0, [x29, #0xa8]
0x0014558c: 40 00 00 b4              cbz      x0, #0x145594
0x00145590: 1e 8f 16 94              bl       #0x6e9208                  ; -> _ZdaPv
0x00145594: a0 7f 40 f9              ldr      x0, [x29, #0xf8]
0x00145598: 40 00 00 b4              cbz      x0, #0x1455a0
0x0014559c: ea 39 06 94              bl       #0x2d3d44                  ; -> cv_image_release
0x001455a0: a0 7b 40 f9              ldr      x0, [x29, #0xf0]
0x001455a4: 40 00 00 b4              cbz      x0, #0x1455ac
0x001455a8: e7 39 06 94              bl       #0x2d3d44                  ; -> cv_image_release
0x001455ac: e0 03 14 aa              mov      x0, x20
0x001455b0: 41 2b 18 94              bl       #0x7502b4                  ; -> _Unwind_Resume
0x001455b4: e0 30 00 d0              adrp     x0, #0x763000
0x001455b8: e1 30 00 d0              adrp     x1, #0x763000
0x001455bc: 00 80 12 91              add      x0, x0, #0x4a0
0x001455c0: 21 00 0c 91              add      x1, x1, #0x300
0x001455c4: 22 03 80 52              mov      w2, #0x19
0x001455c8: 76 00 80 12              mov      w22, #-4
0x001455cc: c8 e5 ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x001455d0: 49 ff ff 17              b        #0x1452f4                  ; -> _ZN12stfaceunlock18FaceDetectionClass6DetectEPK10st_image_tRK19st_face_orientationjPS4_PP9st_face_tPii+0x70c
0x001455d4: f4 03 00 aa              mov      x20, x0
0x001455d8: 14 94 17 94              bl       #0x72a628                  ; -> _ZNSt6chrono3_V212steady_clock3nowEv
0x001455dc: 64 9b 86 d2              mov      x4, #0x34db
0x001455e0: 13 00 13 cb              sub      x19, x0, x19
0x001455e4: c4 f6 ba f2              movk     x4, #0xd7b6, lsl #16
0x001455e8: e0 30 00 90              adrp     x0, #0x761000
0x001455ec: 44 d0 db f2              movk     x4, #0xde82, lsl #32
0x001455f0: 00 40 2b 91              add      x0, x0, #0xad0
0x001455f4: 64 63 e8 f2              movk     x4, #0x431b, lsl #48
0x001455f8: e1 a2 25 91              add      x1, x23, #0x968
0x001455fc: 62 05 80 52              mov      w2, #0x2b
0x00145600: 03 43 16 91              add      x3, x24, #0x590
0x00145604: 65 7e 44 9b              smulh    x5, x19, x4
0x00145608: a4 fc 52 93              asr      x4, x5, #0x12
0x0014560c: 84 fc 93 cb              sub      x4, x4, x19, asr #63
0x00145610: 74 e5 ff 97              bl       #0x13ebe0                  ; -> _Z4LogWPKcz
0x00145614: dd ff ff 17              b        #0x145588                  ; -> _ZN12stfaceunlock18FaceDetectionClass6DetectEPK10st_image_tRK19st_face_orientationjPS4_PP9st_face_tPii+0x9a0
0x00145618: f4 03 00 aa              mov      x20, x0
0x0014561c: 03 94 17 94              bl       #0x72a628                  ; -> _ZNSt6chrono3_V212steady_clock3nowEv
0x00145620: 64 9b 86 d2              mov      x4, #0x34db
0x00145624: 19 00 19 cb              sub      x25, x0, x25
0x00145628: c4 f6 ba f2              movk     x4, #0xd7b6, lsl #16
0x0014562c: 60 42 2b 91              add      x0, x19, #0xad0
0x00145630: 44 d0 db f2              movk     x4, #0xde82, lsl #32
0x00145634: e1 a2 25 91              add      x1, x23, #0x968
0x00145638: 64 63 e8 f2              movk     x4, #0x431b, lsl #48
0x0014563c: 62 05 80 52              mov      w2, #0x2b
0x00145640: a3 62 19 91              add      x3, x21, #0x658
0x00145644: 25 7f 44 9b              smulh    x5, x25, x4
0x00145648: a4 fc 52 93              asr      x4, x5, #0x12
0x0014564c: 84 fc 99 cb              sub      x4, x4, x25, asr #63
0x00145650: 64 e5 ff 97              bl       #0x13ebe0                  ; -> _Z4LogWPKcz
0x00145654: b8 f9 ff b4              cbz      x24, #0x145588
0x00145658: e0 03 18 aa              mov      x0, x24
0x0014565c: ae 5d ff 97              bl       #0x11cd14                  ; -> _ZN12stfaceunlock20FaceDetectionManager22ReleaseDetectionResultEP9st_rect_t
0x00145660: ca ff ff 17              b        #0x145588                  ; -> _ZN12stfaceunlock18FaceDetectionClass6DetectEPK10st_image_tRK19st_face_orientationjPS4_PP9st_face_tPii+0x9a0
0x00145664: a0 73 40 b9              ldr      w0, [x29, #0x70]
0x00145668: 88 fe ff 17              b        #0x145088                  ; -> _ZN12stfaceunlock18FaceDetectionClass6DetectEPK10st_image_tRK19st_face_orientationjPS4_PP9st_face_tPii+0x4a0
0x0014566c: f4 03 00 aa              mov      x20, x0
0x00145670: f9 ff ff 17              b        #0x145654                  ; -> _ZN12stfaceunlock18FaceDetectionClass6DetectEPK10st_image_tRK19st_face_orientationjPS4_PP9st_face_tPii+0xa6c
0x00145674: f4 03 00 aa              mov      x20, x0
0x00145678: c4 ff ff 17              b        #0x145588                  ; -> _ZN12stfaceunlock18FaceDetectionClass6DetectEPK10st_image_tRK19st_face_orientationjPS4_PP9st_face_tPii+0x9a0
0x0014567c: f4 03 00 aa              mov      x20, x0
0x00145680: c8 ff ff 17              b        #0x1455a0                  ; -> _ZN12stfaceunlock18FaceDetectionClass6DetectEPK10st_image_tRK19st_face_orientationjPS4_PP9st_face_tPii+0x9b8
