; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : st_auth_evaluate (0x10cdd0)
; start   : 0x10cdd0
; size    : 0x2c4
; bytes   : 0x2c4   instructions: 177
; generated: 2026-10-24T00:26:38

0x0010cdd0: fd 7b a7 a9              stp      x29, x30, [sp, #-0x190]!
0x0010cdd4: fd 03 00 91              mov      x29, sp
0x0010cdd8: f5 5b 02 a9              stp      x21, x22, [sp, #0x20]
0x0010cddc: e8 27 05 6d              stp      d8, d9, [sp, #0x50]
0x0010cde0: f6 03 03 2a              mov      w22, w3
0x0010cde4: 23 32 00 d0              adrp     x3, #0x752000
0x0010cde8: f3 53 01 a9              stp      x19, x20, [sp, #0x10]
0x0010cdec: f9 6b 04 a9              stp      x25, x26, [sp, #0x40]
0x0010cdf0: 54 32 00 b0              adrp     x20, #0x755000
0x0010cdf4: fa 03 00 aa              mov      x26, x0
0x0010cdf8: 63 00 03 91              add      x3, x3, #0xc0
0x0010cdfc: 40 32 00 b0              adrp     x0, #0x755000
0x0010ce00: f7 63 03 a9              stp      x23, x24, [sp, #0x30]
0x0010ce04: f3 03 01 aa              mov      x19, x1
0x0010ce08: 00 c0 06 91              add      x0, x0, #0x1b0
0x0010ce0c: 81 42 07 91              add      x1, x20, #0x1d0
0x0010ce10: 63 40 0b 91              add      x3, x3, #0x2d0
0x0010ce14: f7 03 02 2a              mov      w23, w2
0x0010ce18: 42 3c 80 52              mov      w2, #0x1e2
0x0010ce1c: f8 03 04 2a              mov      w24, w4
0x0010ce20: f9 03 05 2a              mov      w25, w5
0x0010ce24: a6 37 00 f9              str      x6, [x29, #0x68]
0x0010ce28: e8 c6 00 94              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x0010ce2c: 94 be ff 97              bl       #0xfc87c                   ; -> st_face_get_version
0x0010ce30: e1 03 00 aa              mov      x1, x0
0x0010ce34: e0 03 13 aa              mov      x0, x19
0x0010ce38: b8 0f 01 94              bl       #0x150d18                  ; -> _ZN12stfaceunlock11AlgoManager17InitAlgorithmInfoEPKcS2_
0x0010ce3c: a0 07 00 35              cbnz     w0, #0x10cf30
0x0010ce40: da 0d 00 b4              cbz      x26, #0x10cff8
0x0010ce44: b3 0d 00 b4              cbz      x19, #0x10cff8
0x0010ce48: 96 0d f8 37              tbnz     w22, #0x1f, #0x10cff8
0x0010ce4c: a6 37 40 f9              ldr      x6, [x29, #0x68]
0x0010ce50: 58 0d f8 37              tbnz     w24, #0x1f, #0x10cff8
0x0010ce54: e1 70 02 d0              adrp     x1, #0x4f2a000
0x0010ce58: e0 70 02 b0              adrp     x0, #0x4f29000
0x0010ce5c: df 04 00 71              cmp      w6, #1
0x0010ce60: 21 08 41 f9              ldr      x1, [x1, #0x210]
0x0010ce64: 00 18 46 f9              ldr      x0, [x0, #0xc30]
0x0010ce68: 29 00 40 bd              ldr      s9, [x1]                   ; -> __G78__android17_bcl_binary_data@got
0x0010ce6c: 08 00 40 bd              ldr      s8, [x0]                   ; -> __6xx__android11_bcl_binary_data_size@got
0x0010ce70: 20 07 00 54              b.eq     #0x10cf54
0x0010ce74: b5 03 02 91              add      x21, x29, #0x80
0x0010ce78: b4 83 02 91              add      x20, x29, #0xa0
0x0010ce7c: e0 03 15 aa              mov      x0, x21
0x0010ce80: e1 03 1a aa              mov      x1, x26
0x0010ce84: e2 03 14 aa              mov      x2, x20
0x0010ce88: 22 9d 18 94              bl       #0x734310                  ; -> _ZNSsC2EPKcRKSaIcE
0x0010ce8c: a0 22 00 91              add      x0, x21, #8
0x0010ce90: e1 03 13 aa              mov      x1, x19
0x0010ce94: e2 03 14 aa              mov      x2, x20
0x0010ce98: 1e 9d 18 94              bl       #0x734310                  ; -> _ZNSsC2EPKcRKSaIcE
0x0010ce9c: e0 03 14 aa              mov      x0, x20
0x0010cea0: e1 03 15 aa              mov      x1, x21
0x0010cea4: b7 93 00 b9              str      w23, [x29, #0x90]
0x0010cea8: b6 97 00 b9              str      w22, [x29, #0x94]
0x0010ceac: b8 9b 00 b9              str      w24, [x29, #0x98]
0x0010ceb0: b9 9f 00 b9              str      w25, [x29, #0x9c]
0x0010ceb4: 1c 1f 01 94              bl       #0x154b24                  ; -> _ZN12stfaceunlock14FaceEvaluationC2ERKNS_16EvaluationConfigE
0x0010ceb8: 20 41 20 1e              fmov     s0, s9
0x0010cebc: e0 03 14 aa              mov      x0, x20
0x0010cec0: 01 41 20 1e              fmov     s1, s8
0x0010cec4: e9 1d 01 94              bl       #0x154668                  ; -> _ZN12stfaceunlock14FaceEvaluation13SetThresholdsEff
0x0010cec8: e0 02 00 34              cbz      w0, #0x10cf24
0x0010cecc: f3 03 00 2a              mov      w19, w0
0x0010ced0: e0 03 14 aa              mov      x0, x20
0x0010ced4: f4 70 02 d0              adrp     x20, #0x4f2a000
0x0010ced8: 97 1f 01 94              bl       #0x154d34                  ; -> _ZN12stfaceunlock14FaceEvaluationD2Ev
0x0010cedc: a1 06 40 f9              ldr      x1, [x21, #8]
0x0010cee0: 82 fa 47 f9              ldr      x2, [x20, #0xff0]
0x0010cee4: 20 60 00 d1              sub      x0, x1, #0x18
0x0010cee8: 1f 00 02 eb              cmp      x0, x2
0x0010ceec: 21 04 00 54              b.ne     #0x10cf70
0x0010cef0: a1 02 40 f9              ldr      x1, [x21]
0x0010cef4: 94 fa 47 f9              ldr      x20, [x20, #0xff0]
0x0010cef8: 20 60 00 d1              sub      x0, x1, #0x18
0x0010cefc: 1f 00 14 eb              cmp      x0, x20
0x0010cf00: 21 05 00 54              b.ne     #0x10cfa4
0x0010cf04: e0 03 13 2a              mov      w0, w19
0x0010cf08: e8 27 45 6d              ldp      d8, d9, [sp, #0x50]
0x0010cf0c: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x0010cf10: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x0010cf14: f7 63 43 a9              ldp      x23, x24, [sp, #0x30]
0x0010cf18: f9 6b 44 a9              ldp      x25, x26, [sp, #0x40]
0x0010cf1c: fd 7b d9 a8              ldp      x29, x30, [sp], #0x190
0x0010cf20: c0 03 5f d6              ret      
0x0010cf24: e0 03 14 aa              mov      x0, x20
0x0010cf28: 1f 3c 01 94              bl       #0x15bfa4                  ; -> _ZN12stfaceunlock14FaceEvaluation8EvaluateEv
0x0010cf2c: e8 ff ff 17              b        #0x10cecc                  ; -> st_auth_evaluate+0xfc
0x0010cf30: f3 03 00 2a              mov      w19, w0
0x0010cf34: e8 27 45 6d              ldp      d8, d9, [sp, #0x50]
0x0010cf38: e0 03 13 2a              mov      w0, w19
0x0010cf3c: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x0010cf40: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x0010cf44: f7 63 43 a9              ldp      x23, x24, [sp, #0x30]
0x0010cf48: f9 6b 44 a9              ldp      x25, x26, [sp, #0x40]
0x0010cf4c: fd 7b d9 a8              ldp      x29, x30, [sp], #0x190
0x0010cf50: c0 03 5f d6              ret      
0x0010cf54: e1 70 02 b0              adrp     x1, #0x4f29000
0x0010cf58: e0 70 02 b0              adrp     x0, #0x4f29000
0x0010cf5c: 21 44 46 f9              ldr      x1, [x1, #0xc88]
0x0010cf60: 00 b8 42 f9              ldr      x0, [x0, #0x570]
0x0010cf64: 29 00 40 bd              ldr      s9, [x1]                   ; -> __6xx__android11_bcl_binary_data_size@got
0x0010cf68: 08 00 40 bd              ldr      s8, [x0]                   ; -> __6xx__android11_bcl_binary_data_size@got
0x0010cf6c: c2 ff ff 17              b        #0x10ce74                  ; -> st_auth_evaluate+0xa4
0x0010cf70: e2 70 02 b0              adrp     x2, #0x4f29000
0x0010cf74: 42 94 47 f9              ldr      x2, [x2, #0xf28]           ; -> pthread_create@got
0x0010cf78: 02 03 00 b4              cbz      x2, #0x10cfd8
0x0010cf7c: 21 20 00 d1              sub      x1, x1, #8
0x0010cf80: 22 fc 5f 88              ldaxr    w2, [x1]
0x0010cf84: 43 04 00 51              sub      w3, w2, #1
0x0010cf88: 23 fc 04 88              stlxr    w4, w3, [x1]
0x0010cf8c: a4 ff ff 35              cbnz     w4, #0x10cf80
0x0010cf90: 5f 00 1f 6b              cmp      w2, wzr
0x0010cf94: ec fa ff 54              b.gt     #0x10cef0
0x0010cf98: a1 e3 01 91              add      x1, x29, #0x78
0x0010cf9c: d8 aa 18 94              bl       #0x737afc                  ; -> _ZNSs4_Rep10_M_destroyERKSaIcE
0x0010cfa0: d4 ff ff 17              b        #0x10cef0                  ; -> st_auth_evaluate+0x120
0x0010cfa4: e2 70 02 b0              adrp     x2, #0x4f29000
0x0010cfa8: 42 94 47 f9              ldr      x2, [x2, #0xf28]           ; -> pthread_create@got
0x0010cfac: e2 01 00 b4              cbz      x2, #0x10cfe8
0x0010cfb0: 21 20 00 d1              sub      x1, x1, #8
0x0010cfb4: 22 fc 5f 88              ldaxr    w2, [x1]
0x0010cfb8: 43 04 00 51              sub      w3, w2, #1
0x0010cfbc: 23 fc 04 88              stlxr    w4, w3, [x1]
0x0010cfc0: a4 ff ff 35              cbnz     w4, #0x10cfb4
0x0010cfc4: 5f 00 1f 6b              cmp      w2, wzr
0x0010cfc8: ec f9 ff 54              b.gt     #0x10cf04
0x0010cfcc: a1 e3 01 91              add      x1, x29, #0x78
0x0010cfd0: cb aa 18 94              bl       #0x737afc                  ; -> _ZNSs4_Rep10_M_destroyERKSaIcE
0x0010cfd4: cc ff ff 17              b        #0x10cf04                  ; -> st_auth_evaluate+0x134
0x0010cfd8: 22 80 5f b8              ldur     w2, [x1, #-8]
0x0010cfdc: 43 04 00 51              sub      w3, w2, #1
0x0010cfe0: 23 80 1f b8              stur     w3, [x1, #-8]
0x0010cfe4: eb ff ff 17              b        #0x10cf90                  ; -> st_auth_evaluate+0x1c0
0x0010cfe8: 22 80 5f b8              ldur     w2, [x1, #-8]
0x0010cfec: 43 04 00 51              sub      w3, w2, #1
0x0010cff0: 23 80 1f b8              stur     w3, [x1, #-8]
0x0010cff4: f4 ff ff 17              b        #0x10cfc4                  ; -> st_auth_evaluate+0x1f4
0x0010cff8: 40 32 00 b0              adrp     x0, #0x755000
0x0010cffc: 81 42 07 91              add      x1, x20, #0x1d0
0x0010d000: 00 40 09 91              add      x0, x0, #0x250
0x0010d004: 42 3d 80 52              mov      w2, #0x1ea
0x0010d008: 13 00 80 12              mov      w19, #-1
0x0010d00c: 38 c7 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010d010: bd ff ff 17              b        #0x10cf04                  ; -> st_auth_evaluate+0x134
0x0010d014: f3 03 00 aa              mov      x19, x0
0x0010d018: e0 03 14 aa              mov      x0, x20
0x0010d01c: 46 1f 01 94              bl       #0x154d34                  ; -> _ZN12stfaceunlock14FaceEvaluationD2Ev
0x0010d020: f4 70 02 b0              adrp     x20, #0x4f2a000
0x0010d024: a0 06 40 f9              ldr      x0, [x21, #8]
0x0010d028: 00 60 00 d1              sub      x0, x0, #0x18
0x0010d02c: 81 fa 47 f9              ldr      x1, [x20, #0xff0]
0x0010d030: 1f 00 01 eb              cmp      x0, x1
0x0010d034: 60 00 00 54              b.eq     #0x10d040
0x0010d038: a1 e3 01 91              add      x1, x29, #0x78
0x0010d03c: 85 f0 fd 97              bl       #0x89250                   ; -> _ZNSs4_Rep10_M_disposeERKSaIcE.part.95
0x0010d040: a0 02 40 f9              ldr      x0, [x21]
0x0010d044: 94 fa 47 f9              ldr      x20, [x20, #0xff0]
0x0010d048: 00 60 00 d1              sub      x0, x0, #0x18
0x0010d04c: 1f 00 14 eb              cmp      x0, x20
0x0010d050: 60 00 00 54              b.eq     #0x10d05c
0x0010d054: a1 e3 01 91              add      x1, x29, #0x78
0x0010d058: 7e f0 fd 97              bl       #0x89250                   ; -> _ZNSs4_Rep10_M_disposeERKSaIcE.part.95
0x0010d05c: e0 03 13 aa              mov      x0, x19
0x0010d060: 95 0c 19 94              bl       #0x7502b4                  ; -> _Unwind_Resume
0x0010d064: f3 03 00 aa              mov      x19, x0
0x0010d068: ee ff ff 17              b        #0x10d020                  ; -> st_auth_evaluate+0x250
0x0010d06c: a1 02 40 f9              ldr      x1, [x21]
0x0010d070: f3 03 00 aa              mov      x19, x0
0x0010d074: 20 60 00 d1              sub      x0, x1, #0x18
0x0010d078: e1 70 02 b0              adrp     x1, #0x4f2a000
0x0010d07c: 21 f8 47 f9              ldr      x1, [x1, #0xff0]
0x0010d080: 1f 00 01 eb              cmp      x0, x1
0x0010d084: c0 fe ff 54              b.eq     #0x10d05c
0x0010d088: e1 03 14 aa              mov      x1, x20
0x0010d08c: 71 f0 fd 97              bl       #0x89250                   ; -> _ZNSs4_Rep10_M_disposeERKSaIcE.part.95
0x0010d090: f3 ff ff 17              b        #0x10d05c                  ; -> st_auth_evaluate+0x28c
