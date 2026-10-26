; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : _ZN12stfaceunlock10FaceVerify14CompareFeatureEPK12st_feature_tS3_Pf (0x123bd0)
; start   : 0x123bd0
; size    : 0x12c
; bytes   : 0x12c   instructions: 75
; generated: 2026-10-24T00:34:39

0x00123bd0: fd 7b bc a9              stp      x29, x30, [sp, #-0x40]!
0x00123bd4: e7 03 01 aa              mov      x7, x1
0x00123bd8: e6 03 02 aa              mov      x6, x2
0x00123bdc: fd 03 00 91              mov      x29, sp
0x00123be0: f5 13 00 f9              str      x21, [sp, #0x20]
0x00123be4: f5 03 00 aa              mov      x21, x0
0x00123be8: 00 28 40 f9              ldr      x0, [x0, #0x50]
0x00123bec: f3 53 01 a9              stp      x19, x20, [sp, #0x10]
0x00123bf0: f4 03 03 aa              mov      x20, x3
0x00123bf4: 40 07 00 b4              cbz      x0, #0x123cdc
0x00123bf8: 5f 00 1f eb              cmp      x2, xzr
0x00123bfc: e1 17 9f 1a              cset     w1, eq
0x00123c00: 7f 00 1f eb              cmp      x3, xzr
0x00123c04: e0 17 9f 1a              cset     w0, eq
0x00123c08: 20 00 00 2a              orr      w0, w1, w0
0x00123c0c: 20 05 00 35              cbnz     w0, #0x123cb0
0x00123c10: 07 05 00 b4              cbz      x7, #0x123cb0
0x00123c14: e3 08 40 b9              ldr      w3, [x7, #8]
0x00123c18: 44 08 40 b9              ldr      w4, [x2, #8]
0x00123c1c: 7f 00 04 6b              cmp      w3, w4
0x00123c20: 81 03 00 54              b.ne     #0x123c90
0x00123c24: e0 03 27 1e              fmov     s0, wzr
0x00123c28: a2 03 01 91              add      x2, x29, #0x40
0x00123c2c: e0 03 07 aa              mov      x0, x7
0x00123c30: e1 03 06 aa              mov      x1, x6
0x00123c34: 40 cc 1f bc              str      s0, [x2, #-4]!
0x00123c38: 10 32 04 94              bl       #0x230478                  ; -> cv_common_feature_comparator_get_distance
0x00123c3c: f3 03 00 2a              mov      w19, w0
0x00123c40: c0 01 00 34              cbz      w0, #0x123c78
0x00123c44: 80 02 40 bd              ldr      s0, [x20]
0x00123c48: c0 31 00 f0              adrp     x0, #0x75e000
0x00123c4c: c1 31 00 f0              adrp     x1, #0x75e000
0x00123c50: 00 60 1f 91              add      x0, x0, #0x7d8
0x00123c54: 00 c0 22 1e              fcvt     d0, s0
0x00123c58: 21 80 1c 91              add      x1, x1, #0x720
0x00123c5c: 82 19 80 52              mov      w2, #0xcc
0x00123c60: 5a 6b 00 94              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x00123c64: e0 03 13 2a              mov      w0, w19
0x00123c68: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x00123c6c: f5 13 40 f9              ldr      x21, [sp, #0x20]
0x00123c70: fd 7b c4 a8              ldp      x29, x30, [sp], #0x40
0x00123c74: c0 03 5f d6              ret      
0x00123c78: a0 3f 40 bd              ldr      s0, [x29, #0x3c]
0x00123c7c: a0 2a 40 f9              ldr      x0, [x21, #0x50]
0x00123c80: e1 03 14 aa              mov      x1, x20
0x00123c84: 22 34 04 94              bl       #0x230d0c                  ; -> cv_common_feature_comparator_normalize_score_by_model
0x00123c88: f3 03 00 2a              mov      w19, w0
0x00123c8c: ee ff ff 17              b        #0x123c44                  ; -> _ZN12stfaceunlock10FaceVerify14CompareFeatureEPK12st_feature_tS3_Pf+0x74
0x00123c90: c0 31 00 f0              adrp     x0, #0x75e000
0x00123c94: c1 31 00 f0              adrp     x1, #0x75e000
0x00123c98: 00 80 1e 91              add      x0, x0, #0x7a0
0x00123c9c: 21 80 1c 91              add      x1, x1, #0x720
0x00123ca0: 82 18 80 52              mov      w2, #0xc4
0x00123ca4: 12 6c 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x00123ca8: 60 00 80 12              mov      w0, #-4
0x00123cac: ef ff ff 17              b        #0x123c68                  ; -> _ZN12stfaceunlock10FaceVerify14CompareFeatureEPK12st_feature_tS3_Pf+0x98
0x00123cb0: c0 31 00 f0              adrp     x0, #0x75e000
0x00123cb4: c1 31 00 f0              adrp     x1, #0x75e000
0x00123cb8: 00 c0 1d 91              add      x0, x0, #0x770
0x00123cbc: 21 80 1c 91              add      x1, x1, #0x720
0x00123cc0: c2 17 80 52              mov      w2, #0xbe
0x00123cc4: e3 03 07 aa              mov      x3, x7
0x00123cc8: e4 03 06 aa              mov      x4, x6
0x00123ccc: e5 03 14 aa              mov      x5, x20
0x00123cd0: 07 6c 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x00123cd4: 00 00 80 12              mov      w0, #-1
0x00123cd8: e4 ff ff 17              b        #0x123c68                  ; -> _ZN12stfaceunlock10FaceVerify14CompareFeatureEPK12st_feature_tS3_Pf+0x98
0x00123cdc: c0 31 00 f0              adrp     x0, #0x75e000
0x00123ce0: c1 31 00 f0              adrp     x1, #0x75e000
0x00123ce4: 00 80 1b 91              add      x0, x0, #0x6e0
0x00123ce8: 21 80 1c 91              add      x1, x1, #0x720
0x00123cec: 42 17 80 52              mov      w2, #0xba
0x00123cf0: ff 6b 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x00123cf4: 60 00 80 12              mov      w0, #-4
0x00123cf8: dc ff ff 17              b        #0x123c68                  ; -> _ZN12stfaceunlock10FaceVerify14CompareFeatureEPK12st_feature_tS3_Pf+0x98
