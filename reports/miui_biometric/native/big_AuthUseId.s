; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii (0x14b3a8)
; start   : 0x14b3a8
; size    : 0xd34
; bytes   : 0xd34   instructions: 845
; generated: 2026-10-24T00:33:46

0x0014b3a8: fd 7b aa a9              stp      x29, x30, [sp, #-0x160]!
0x0014b3ac: fd 03 00 91              mov      x29, sp
0x0014b3b0: f3 53 01 a9              stp      x19, x20, [sp, #0x10]
0x0014b3b4: e8 27 06 6d              stp      d8, d9, [sp, #0x60]
0x0014b3b8: f5 5b 02 a9              stp      x21, x22, [sp, #0x20]
0x0014b3bc: f7 63 03 a9              stp      x23, x24, [sp, #0x30]
0x0014b3c0: f3 03 00 aa              mov      x19, x0
0x0014b3c4: b6 30 00 d0              adrp     x22, #0x761000
0x0014b3c8: d7 30 00 d0              adrp     x23, #0x765000
0x0014b3cc: a0 30 00 d0              adrp     x0, #0x761000
0x0014b3d0: fb 73 05 a9              stp      x27, x28, [sp, #0x50]
0x0014b3d4: 00 40 25 91              add      x0, x0, #0x950
0x0014b3d8: f5 03 01 aa              mov      x21, x1
0x0014b3dc: f8 03 02 2a              mov      w24, w2
0x0014b3e0: c1 a2 25 91              add      x1, x22, #0x968
0x0014b3e4: 82 04 80 52              mov      w2, #0x24
0x0014b3e8: fc 03 03 aa              mov      x28, x3
0x0014b3ec: e3 c2 0e 91              add      x3, x23, #0x3b0
0x0014b3f0: fb 03 04 2a              mov      w27, w4
0x0014b3f4: f9 6b 04 a9              stp      x25, x26, [sp, #0x40]
0x0014b3f8: fa cd ff 97              bl       #0x13ebe0                  ; -> _Z4LogWPKcz
0x0014b3fc: 8b 7c 17 94              bl       #0x72a628                  ; -> _ZNSt6chrono3_V212steady_clock3nowEv
0x0014b400: a0 6b 00 f9              str      x0, [x29, #0xd0]
0x0014b404: 62 02 40 f9              ldr      x2, [x19]
0x0014b408: 01 00 80 12              mov      w1, #-1
0x0014b40c: a1 b7 00 b9              str      w1, [x29, #0xb4]
0x0014b410: f4 03 00 aa              mov      x20, x0
0x0014b414: 61 8e 02 b9              str      w1, [x19, #0x28c]
0x0014b418: 82 1b 00 b4              cbz      x2, #0x14b788
0x0014b41c: 60 06 40 f9              ldr      x0, [x19, #8]
0x0014b420: 40 1b 00 b4              cbz      x0, #0x14b788
0x0014b424: 60 0e 40 f9              ldr      x0, [x19, #0x18]
0x0014b428: 00 1b 00 b4              cbz      x0, #0x14b788
0x0014b42c: 60 12 40 f9              ldr      x0, [x19, #0x20]
0x0014b430: c0 1a 00 b4              cbz      x0, #0x14b788
0x0014b434: 15 38 00 b4              cbz      x21, #0x14bb34
0x0014b438: a0 02 40 f9              ldr      x0, [x21]
0x0014b43c: 80 47 00 b4              cbz      x0, #0x14bd2c
0x0014b440: 60 8a 42 b9              ldr      w0, [x19, #0x288]
0x0014b444: 1f 10 00 71              cmp      w0, #4
0x0014b448: ac 04 00 54              b.gt     #0x14b4dc
0x0014b44c: e0 03 13 aa              mov      x0, x19
0x0014b450: e1 03 15 aa              mov      x1, x21
0x0014b454: 62 62 01 91              add      x2, x19, #0x58
0x0014b458: e3 03 18 2a              mov      w3, w24
0x0014b45c: a4 03 03 91              add      x4, x29, #0xc0
0x0014b460: a5 e3 02 91              add      x5, x29, #0xb8
0x0014b464: 06 00 80 52              mov      w6, #0
0x0014b468: bf 63 00 f9              str      xzr, [x29, #0xc0]
0x0014b46c: bf bb 00 b9              str      wzr, [x29, #0xb8]
0x0014b470: 7d ef ff 97              bl       #0x147264                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib
0x0014b474: fa 03 00 2a              mov      w26, w0
0x0014b478: 00 04 00 34              cbz      w0, #0x14b4f8
0x0014b47c: 73 9b 86 d2              mov      x19, #0x34db
0x0014b480: 6a 7c 17 94              bl       #0x72a628                  ; -> _ZNSt6chrono3_V212steady_clock3nowEv
0x0014b484: d3 f6 ba f2              movk     x19, #0xd7b6, lsl #16
0x0014b488: 04 00 14 cb              sub      x4, x0, x20
0x0014b48c: 53 d0 db f2              movk     x19, #0xde82, lsl #32
0x0014b490: a0 30 00 d0              adrp     x0, #0x761000
0x0014b494: 73 63 e8 f2              movk     x19, #0x431b, lsl #48
0x0014b498: c1 a2 25 91              add      x1, x22, #0x968
0x0014b49c: e3 c2 0e 91              add      x3, x23, #0x3b0
0x0014b4a0: 00 40 2b 91              add      x0, x0, #0xad0
0x0014b4a4: 93 7c 53 9b              smulh    x19, x4, x19
0x0014b4a8: 62 05 80 52              mov      w2, #0x2b
0x0014b4ac: 73 fe 52 93              asr      x19, x19, #0x12
0x0014b4b0: 64 fe 84 cb              sub      x4, x19, x4, asr #63
0x0014b4b4: cb cd ff 97              bl       #0x13ebe0                  ; -> _Z4LogWPKcz
0x0014b4b8: e0 03 1a 2a              mov      w0, w26
0x0014b4bc: e8 27 46 6d              ldp      d8, d9, [sp, #0x60]
0x0014b4c0: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x0014b4c4: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x0014b4c8: f7 63 43 a9              ldp      x23, x24, [sp, #0x30]
0x0014b4cc: f9 6b 44 a9              ldp      x25, x26, [sp, #0x40]
0x0014b4d0: fb 73 45 a9              ldp      x27, x28, [sp, #0x50]
0x0014b4d4: fd 7b d6 a8              ldp      x29, x30, [sp], #0x160
0x0014b4d8: c0 03 5f d6              ret      
0x0014b4dc: c0 30 00 d0              adrp     x0, #0x765000
0x0014b4e0: c1 30 00 b0              adrp     x1, #0x764000
0x0014b4e4: 00 20 11 91              add      x0, x0, #0x448
0x0014b4e8: 21 e0 12 91              add      x1, x1, #0x4b8
0x0014b4ec: c2 8e 80 52              mov      w2, #0x476
0x0014b4f0: 79 cd ff 97              bl       #0x13ead4                  ; -> _Z4LogIPKcz
0x0014b4f4: d6 ff ff 17              b        #0x14b44c                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0xa4
0x0014b4f8: b9 63 40 f9              ldr      x25, [x29, #0xc0]
0x0014b4fc: c0 30 00 b0              adrp     x0, #0x764000
0x0014b500: e1 03 27 1e              fmov     s1, wzr
0x0014b504: c1 30 00 b0              adrp     x1, #0x764000
0x0014b508: 00 40 1d 91              add      x0, x0, #0x750
0x0014b50c: 21 e0 12 91              add      x1, x1, #0x4b8
0x0014b510: 20 23 40 bd              ldr      s0, [x25, #0x20]
0x0014b514: 02 c5 80 52              mov      w2, #0x628
0x0014b518: 00 c0 22 1e              fcvt     d0, s0
0x0014b51c: a1 bf 00 bd              str      s1, [x29, #0xbc]
0x0014b520: 6d cd ff 97              bl       #0x13ead4                  ; -> _Z4LogIPKcz
0x0014b524: 20 23 40 bd              ldr      s0, [x25, #0x20]
0x0014b528: a8 5d 00 1c              ldr      s8, #0x14c0dc
0x0014b52c: a9 5d 00 1c              ldr      s9, #0x14c0e0
0x0014b530: 10 20 28 1e              fcmpe    s0, s8
0x0014b534: ab 13 00 54              b.lt     #0x14b7a8
0x0014b538: 10 20 29 1e              fcmpe    s0, s9
0x0014b53c: 20 00 80 52              mov      w0, #1
0x0014b540: a0 ab 00 b9              str      w0, [x29, #0xa8]
0x0014b544: 28 13 00 54              b.hi     #0x14b7a8
0x0014b548: e0 03 18 2a              mov      w0, w24
0x0014b54c: 01 01 80 52              mov      w1, #8
0x0014b550: 3e dc ff 97              bl       #0x142648                  ; -> _ZN12stfaceunlock9CommonFun9CheckFlagEjj
0x0014b554: 00 1c 00 53              uxtb     w0, w0
0x0014b558: a0 06 00 35              cbnz     w0, #0x14b62c
0x0014b55c: 60 06 40 f9              ldr      x0, [x19, #8]
0x0014b560: 7a 0a 40 f9              ldr      x26, [x19, #0x10]
0x0014b564: a0 4f 00 f9              str      x0, [x29, #0x98]
0x0014b568: 3a 01 00 b4              cbz      x26, #0x14b58c
0x0014b56c: e0 6e 02 d0              adrp     x0, #0x4f29000
0x0014b570: 00 94 47 f9              ldr      x0, [x0, #0xf28]           ; -> pthread_create@got
0x0014b574: c0 3e 00 b4              cbz      x0, #0x14bd4c
0x0014b578: 40 23 00 91              add      x0, x26, #8
0x0014b57c: 01 fc 5f 88              ldaxr    w1, [x0]
0x0014b580: 21 04 00 11              add      w1, w1, #1
0x0014b584: 01 fc 02 88              stlxr    w2, w1, [x0]
0x0014b588: a2 ff ff 35              cbnz     w2, #0x14b57c
0x0014b58c: a1 63 40 f9              ldr      x1, [x29, #0xc0]
0x0014b590: 00 08 80 d2              mov      x0, #0x40
0x0014b594: a1 53 00 f9              str      x1, [x29, #0xa0]
0x0014b598: bf 9b 00 f9              str      xzr, [x29, #0x130]
0x0014b59c: 96 7d 16 94              bl       #0x6eabf4                  ; -> _Znwm
0x0014b5a0: e2 6e 02 d0              adrp     x2, #0x4f29000
0x0014b5a4: e1 03 00 aa              mov      x1, x0
0x0014b5a8: b9 83 05 91              add      x25, x29, #0x160
0x0014b5ac: a3 4f 40 f9              ldr      x3, [x29, #0x98]
0x0014b5b0: 60 e2 06 91              add      x0, x19, #0x1b8
0x0014b5b4: 42 1c 44 f9              ldr      x2, [x2, #0x838]
0x0014b5b8: 22 00 00 f9              str      x2, [x1]
0x0014b5bc: a2 f3 02 91              add      x2, x29, #0xbc
0x0014b5c0: 23 08 00 f9              str      x3, [x1, #0x10]
0x0014b5c4: 22 10 00 f9              str      x2, [x1, #0x20]
0x0014b5c8: a3 ab 40 b9              ldr      w3, [x29, #0xa8]
0x0014b5cc: a2 53 40 f9              ldr      x2, [x29, #0xa0]
0x0014b5d0: 23 28 00 b9              str      w3, [x1, #0x28]
0x0014b5d4: 03 00 00 b0              adrp     x3, #0x14c000
0x0014b5d8: 22 18 00 f9              str      x2, [x1, #0x30]
0x0014b5dc: e2 6e 02 b0              adrp     x2, #0x4f28000
0x0014b5e0: 3f 04 00 f9              str      xzr, [x1, #8]
0x0014b5e4: 63 80 0a 91              add      x3, x3, #0x2a0
0x0014b5e8: 3a 0c 00 f9              str      x26, [x1, #0x18]
0x0014b5ec: 35 1c 00 f9              str      x21, [x1, #0x38]
0x0014b5f0: 21 0f 1c f8              str      x1, [x25, #-0x40]!
0x0014b5f4: a3 9f 00 f9              str      x3, [x29, #0x138]
0x0014b5f8: 42 70 47 f9              ldr      x2, [x2, #0xee0]
0x0014b5fc: e1 03 19 aa              mov      x1, x25
0x0014b600: a2 9b 00 f9              str      x2, [x29, #0x130]
0x0014b604: 15 0b 00 94              bl       #0x14e258                  ; -> _ZN13BlockingQueueISt8functionIFivEEE4pushEOKS2_
0x0014b608: 60 56 41 b9              ldr      w0, [x19, #0x154]
0x0014b60c: a3 9b 40 f9              ldr      x3, [x29, #0x130]
0x0014b610: 00 04 00 11              add      w0, w0, #1
0x0014b614: 60 56 01 b9              str      w0, [x19, #0x154]
0x0014b618: a3 00 00 b4              cbz      x3, #0x14b62c
0x0014b61c: e0 03 19 aa              mov      x0, x25
0x0014b620: e1 03 19 aa              mov      x1, x25
0x0014b624: 62 00 80 52              mov      w2, #3
0x0014b628: 60 00 3f d6              blr      x3
0x0014b62c: 60 2e 42 bd              ldr      s0, [x19, #0x22c]
0x0014b630: a3 bb 40 b9              ldr      w3, [x29, #0xb8]
0x0014b634: c0 30 00 d0              adrp     x0, #0x765000
0x0014b638: c1 30 00 b0              adrp     x1, #0x764000
0x0014b63c: 00 c0 22 1e              fcvt     d0, s0
0x0014b640: a3 8f 00 b9              str      w3, [x29, #0x8c]
0x0014b644: a3 63 40 f9              ldr      x3, [x29, #0xc0]
0x0014b648: 00 a0 11 91              add      x0, x0, #0x468
0x0014b64c: 21 e0 12 91              add      x1, x1, #0x4b8
0x0014b650: 82 95 80 52              mov      w2, #0x4ac
0x0014b654: a3 4b 00 f9              str      x3, [x29, #0x90]
0x0014b658: dc cc ff 97              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x0014b65c: 60 12 40 f9              ldr      x0, [x19, #0x20]
0x0014b660: 7a 16 40 f9              ldr      x26, [x19, #0x28]
0x0014b664: a0 53 00 f9              str      x0, [x29, #0xa0]
0x0014b668: 3a 01 00 b4              cbz      x26, #0x14b68c
0x0014b66c: e0 6e 02 d0              adrp     x0, #0x4f29000
0x0014b670: 00 94 47 f9              ldr      x0, [x0, #0xf28]           ; -> pthread_create@got
0x0014b674: 60 0d 00 b4              cbz      x0, #0x14b820
0x0014b678: 40 23 00 91              add      x0, x26, #8
0x0014b67c: 01 fc 5f 88              ldaxr    w1, [x0]
0x0014b680: 21 04 00 11              add      w1, w1, #1
0x0014b684: 01 fc 02 88              stlxr    w2, w1, [x0]
0x0014b688: a2 ff ff 35              cbnz     w2, #0x14b67c
0x0014b68c: a1 63 40 f9              ldr      x1, [x29, #0xc0]
0x0014b690: 00 08 80 d2              mov      x0, #0x40
0x0014b694: a1 57 00 f9              str      x1, [x29, #0xa8]
0x0014b698: bf ab 00 f9              str      xzr, [x29, #0x150]
0x0014b69c: 56 7d 16 94              bl       #0x6eabf4                  ; -> _Znwm
0x0014b6a0: e2 6e 02 f0              adrp     x2, #0x4f2a000
0x0014b6a4: e1 03 00 aa              mov      x1, x0
0x0014b6a8: b9 83 05 91              add      x25, x29, #0x160
0x0014b6ac: a3 53 40 f9              ldr      x3, [x29, #0xa0]
0x0014b6b0: 60 62 03 91              add      x0, x19, #0xd8
0x0014b6b4: 42 a0 42 f9              ldr      x2, [x2, #0x540]
0x0014b6b8: 22 00 00 f9              str      x2, [x1]
0x0014b6bc: a2 23 03 91              add      x2, x29, #0xc8
0x0014b6c0: 22 14 00 f9              str      x2, [x1, #0x28]
0x0014b6c4: a2 57 40 f9              ldr      x2, [x29, #0xa8]
0x0014b6c8: 23 08 00 f9              str      x3, [x1, #0x10]
0x0014b6cc: 03 00 00 b0              adrp     x3, #0x14c000
0x0014b6d0: 22 18 00 f9              str      x2, [x1, #0x30]
0x0014b6d4: e2 6e 02 d0              adrp     x2, #0x4f29000
0x0014b6d8: 3f 04 00 f9              str      xzr, [x1, #8]
0x0014b6dc: 63 f0 0b 91              add      x3, x3, #0x2fc
0x0014b6e0: 3a 0c 00 f9              str      x26, [x1, #0x18]
0x0014b6e4: 3f 10 00 f9              str      xzr, [x1, #0x20]
0x0014b6e8: 35 1c 00 f9              str      x21, [x1, #0x38]
0x0014b6ec: 21 0f 1e f8              str      x1, [x25, #-0x20]!
0x0014b6f0: a3 af 00 f9              str      x3, [x29, #0x158]
0x0014b6f4: 42 24 43 f9              ldr      x2, [x2, #0x648]
0x0014b6f8: e1 03 19 aa              mov      x1, x25
0x0014b6fc: a2 ab 00 f9              str      x2, [x29, #0x150]
0x0014b700: d6 0a 00 94              bl       #0x14e258                  ; -> _ZN13BlockingQueueISt8functionIFivEEE4pushEOKS2_
0x0014b704: 7a 82 01 91              add      x26, x19, #0x60
0x0014b708: 40 17 40 b9              ldr      w0, [x26, #0x14]
0x0014b70c: a3 ab 40 f9              ldr      x3, [x29, #0x150]
0x0014b710: 00 04 00 11              add      w0, w0, #1
0x0014b714: 40 17 00 b9              str      w0, [x26, #0x14]
0x0014b718: a3 00 00 b4              cbz      x3, #0x14b72c
0x0014b71c: e0 03 19 aa              mov      x0, x25
0x0014b720: e1 03 19 aa              mov      x1, x25
0x0014b724: 62 00 80 52              mov      w2, #3
0x0014b728: 60 00 3f d6              blr      x3
0x0014b72c: a3 63 40 f9              ldr      x3, [x29, #0xc0]
0x0014b730: e0 03 13 aa              mov      x0, x19
0x0014b734: e1 03 15 aa              mov      x1, x21
0x0014b738: e2 03 18 2a              mov      w2, w24
0x0014b73c: 04 00 80 52              mov      w4, #0
0x0014b740: 00 eb ff 97              bl       #0x146340                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace15CheckOcularInfoEP10st_image_tjP9st_face_tb
0x0014b744: a0 ab 00 b9              str      w0, [x29, #0xa8]
0x0014b748: 20 05 00 34              cbz      w0, #0x14b7ec
0x0014b74c: 60 02 05 91              add      x0, x19, #0x140
0x0014b750: a0 43 00 f9              str      x0, [x29, #0x80]
0x0014b754: 60 56 41 b9              ldr      w0, [x19, #0x154]
0x0014b758: 00 20 00 35              cbnz     w0, #0x14bb58
0x0014b75c: 40 17 40 b9              ldr      w0, [x26, #0x14]
0x0014b760: 80 24 00 35              cbnz     w0, #0x14bbf0
0x0014b764: a0 67 40 f9              ldr      x0, [x29, #0xc8]
0x0014b768: 40 00 00 b4              cbz      x0, #0x14b770
0x0014b76c: 94 6a ff 97              bl       #0x1261bc                  ; -> _ZN12stfaceunlock17FaceVerifyManager14ReleaseFeatureEP12st_feature_t
0x0014b770: ba ab 40 b9              ldr      w26, [x29, #0xa8]
0x0014b774: a0 4b 40 f9              ldr      x0, [x29, #0x90]
0x0014b778: 20 e8 ff b4              cbz      x0, #0x14b47c
0x0014b77c: a1 8f 40 b9              ldr      w1, [x29, #0x8c]
0x0014b780: ec e2 ff 97              bl       #0x144330                  ; -> _ZN12stfaceunlock17FaceDetectManager22ReleaseDetectionResultEP9st_face_ti
0x0014b784: 3e ff ff 17              b        #0x14b47c                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0xd4
0x0014b788: c0 30 00 b0              adrp     x0, #0x764000
0x0014b78c: c1 30 00 b0              adrp     x1, #0x764000
0x0014b790: 00 60 24 91              add      x0, x0, #0x918
0x0014b794: 21 e0 12 91              add      x1, x1, #0x4b8
0x0014b798: c2 8c 80 52              mov      w2, #0x466
0x0014b79c: 54 cd ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0014b7a0: 7a 00 80 12              mov      w26, #-4
0x0014b7a4: 36 ff ff 17              b        #0x14b47c                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0xd4
0x0014b7a8: 10 20 29 1e              fcmpe    s0, s9
0x0014b7ac: cb 00 00 54              b.lt     #0x14b7c4
0x0014b7b0: a1 49 00 1c              ldr      s1, #0x14c0e4
0x0014b7b4: 40 00 80 52              mov      w0, #2
0x0014b7b8: a0 ab 00 b9              str      w0, [x29, #0xa8]
0x0014b7bc: 10 20 21 1e              fcmpe    s0, s1
0x0014b7c0: 49 ec ff 54              b.ls     #0x14b548
0x0014b7c4: 21 49 00 1c              ldr      s1, #0x14c0e8
0x0014b7c8: 10 20 21 1e              fcmpe    s0, s1
0x0014b7cc: ab 00 00 54              b.lt     #0x14b7e0
0x0014b7d0: 10 20 28 1e              fcmpe    s0, s8
0x0014b7d4: 00 01 80 52              mov      w0, #8
0x0014b7d8: a0 ab 00 b9              str      w0, [x29, #0xa8]
0x0014b7dc: 69 eb ff 54              b.ls     #0x14b548
0x0014b7e0: 80 00 80 52              mov      w0, #4
0x0014b7e4: a0 ab 00 b9              str      w0, [x29, #0xa8]
0x0014b7e8: 58 ff ff 17              b        #0x14b548                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0x1a0
0x0014b7ec: e0 03 18 2a              mov      w0, w24
0x0014b7f0: 01 01 80 52              mov      w1, #8
0x0014b7f4: 95 db ff 97              bl       #0x142648                  ; -> _ZN12stfaceunlock9CommonFun9CheckFlagEjj
0x0014b7f8: 00 1c 00 53              uxtb     w0, w0
0x0014b7fc: a0 01 00 35              cbnz     w0, #0x14b830
0x0014b800: 60 02 05 91              add      x0, x19, #0x140
0x0014b804: a0 43 00 f9              str      x0, [x29, #0x80]
0x0014b808: e1 03 00 aa              mov      x1, x0
0x0014b80c: 60 56 41 b9              ldr      w0, [x19, #0x154]
0x0014b810: 60 12 00 35              cbnz     w0, #0x14ba5c
0x0014b814: 20 18 40 b9              ldr      w0, [x1, #0x18]
0x0014b818: a0 83 00 b9              str      w0, [x29, #0x80]
0x0014b81c: 06 00 00 14              b        #0x14b834                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0x48c
0x0014b820: 40 0b 40 b9              ldr      w0, [x26, #8]
0x0014b824: 00 04 00 11              add      w0, w0, #1
0x0014b828: 40 0b 00 b9              str      w0, [x26, #8]
0x0014b82c: 98 ff ff 17              b        #0x14b68c                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0x2e4
0x0014b830: bf 83 00 b9              str      wzr, [x29, #0x80]
0x0014b834: 40 17 40 b9              ldr      w0, [x26, #0x14]
0x0014b838: 60 22 00 35              cbnz     w0, #0x14bc84
0x0014b83c: 5a 1b 40 b9              ldr      w26, [x26, #0x18]
0x0014b840: a2 67 40 f9              ldr      x2, [x29, #0xc8]
0x0014b844: e0 03 18 2a              mov      w0, w24
0x0014b848: 01 01 80 52              mov      w1, #8
0x0014b84c: a2 53 00 f9              str      x2, [x29, #0xa0]
0x0014b850: 7e db ff 97              bl       #0x142648                  ; -> _ZN12stfaceunlock9CommonFun9CheckFlagEjj
0x0014b854: a1 83 40 b9              ldr      w1, [x29, #0x80]
0x0014b858: 3f 00 1f 6b              cmp      w1, wzr
0x0014b85c: e1 07 9f 1a              cset     w1, ne
0x0014b860: 3f 00 20 6b              cmp      w1, w0, uxtb
0x0014b864: e8 28 00 54              b.hi     #0x14bd80
0x0014b868: 9a 01 00 34              cbz      w26, #0x14b898
0x0014b86c: c0 30 00 b0              adrp     x0, #0x764000
0x0014b870: c2 9a 80 52              mov      w2, #0x4d6
0x0014b874: 01 e0 12 91              add      x1, x0, #0x4b8
0x0014b878: c0 30 00 d0              adrp     x0, #0x765000
0x0014b87c: 00 00 0d 91              add      x0, x0, #0x340
0x0014b880: e3 03 1a 2a              mov      w3, w26
0x0014b884: 1a cd ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0014b888: a0 53 40 f9              ldr      x0, [x29, #0xa0]
0x0014b88c: 40 f7 ff b4              cbz      x0, #0x14b774
0x0014b890: 4b 6a ff 97              bl       #0x1261bc                  ; -> _ZN12stfaceunlock17FaceVerifyManager14ReleaseFeatureEP12st_feature_t
0x0014b894: b8 ff ff 17              b        #0x14b774                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0x3cc
0x0014b898: e0 03 18 2a              mov      w0, w24
0x0014b89c: 01 01 80 52              mov      w1, #8
0x0014b8a0: 6a db ff 97              bl       #0x142648                  ; -> _ZN12stfaceunlock9CommonFun9CheckFlagEjj
0x0014b8a4: 60 26 40 f9              ldr      x0, [x19, #0x48]
0x0014b8a8: 60 13 00 b4              cbz      x0, #0x14bb14
0x0014b8ac: 60 26 41 f9              ldr      x0, [x19, #0x248]
0x0014b8b0: 40 2b 00 b4              cbz      x0, #0x14be18
0x0014b8b4: c0 30 00 b0              adrp     x0, #0x764000
0x0014b8b8: e2 9d 80 52              mov      w2, #0x4ef
0x0014b8bc: 01 e0 12 91              add      x1, x0, #0x4b8
0x0014b8c0: c0 30 00 d0              adrp     x0, #0x765000
0x0014b8c4: 00 40 12 91              add      x0, x0, #0x490
0x0014b8c8: 83 cc ff 97              bl       #0x13ead4                  ; -> _Z4LogIPKcz
0x0014b8cc: a0 67 40 f9              ldr      x0, [x29, #0xc8]
0x0014b8d0: c1 aa 8a 52              mov      w1, #0x5556
0x0014b8d4: a1 aa aa 72              movk     w1, #0x5555, lsl #16
0x0014b8d8: 00 08 40 b9              ldr      w0, [x0, #8]
0x0014b8dc: 00 08 00 11              add      w0, w0, #2
0x0014b8e0: 01 7c 21 9b              smull    x1, w0, w1
0x0014b8e4: 21 fc 60 d3              lsr      x1, x1, #0x20
0x0014b8e8: 20 7c 80 4b              sub      w0, w1, w0, asr #31
0x0014b8ec: 00 74 1e 53              lsl      w0, w0, #2
0x0014b8f0: 00 04 00 11              add      w0, w0, #1
0x0014b8f4: 00 7c 40 93              sxtw     x0, w0
0x0014b8f8: f3 7c 16 94              bl       #0x6eacc4                  ; -> _Znam
0x0014b8fc: fa 03 00 aa              mov      x26, x0
0x0014b900: a0 4f 00 f9              str      x0, [x29, #0x98]
0x0014b904: a0 67 40 f9              ldr      x0, [x29, #0xc8]
0x0014b908: e1 03 1a aa              mov      x1, x26
0x0014b90c: 2d 6a ff 97              bl       #0x1261c0                  ; -> _ZN12stfaceunlock17FaceVerifyManager16SerializeFeatureEPK12st_feature_tPc
0x0014b910: e0 03 1a aa              mov      x0, x26
0x0014b914: 83 f4 fc 97              bl       #0x88b20                   ; -> strlen@plt
0x0014b918: fa 03 00 aa              mov      x26, x0
0x0014b91c: 00 90 00 91              add      x0, x0, #0x24
0x0014b920: a0 43 00 f9              str      x0, [x29, #0x80]
0x0014b924: e8 7c 16 94              bl       #0x6eacc4                  ; -> _Znam
0x0014b928: a0 57 00 f9              str      x0, [x29, #0xa8]
0x0014b92c: 7f 03 1f 6b              cmp      w27, wzr
0x0014b930: 6d 21 00 54              b.le     #0x14bd5c
0x0014b934: 5c 21 00 b4              cbz      x28, #0x14bd5c
0x0014b938: a1 57 40 f9              ldr      x1, [x29, #0xa8]
0x0014b93c: 7f 07 00 71              cmp      w27, #1
0x0014b940: 80 03 40 b9              ldr      w0, [x28]
0x0014b944: 20 04 00 b9              str      w0, [x1, #4]
0x0014b948: a0 25 00 54              b.eq     #0x14bdfc
0x0014b94c: 80 07 40 b9              ldr      w0, [x28, #4]
0x0014b950: 7f 0b 00 71              cmp      w27, #2
0x0014b954: 20 08 00 b9              str      w0, [x1, #8]
0x0014b958: 0d 39 00 54              b.le     #0x14c078
0x0014b95c: 80 0b 40 b9              ldr      w0, [x28, #8]
0x0014b960: 7f 0f 00 71              cmp      w27, #3
0x0014b964: 20 0c 00 b9              str      w0, [x1, #0xc]
0x0014b968: 2d 38 00 54              b.le     #0x14c06c
0x0014b96c: 80 0f 40 b9              ldr      w0, [x28, #0xc]
0x0014b970: 7f 13 00 71              cmp      w27, #4
0x0014b974: 20 10 00 b9              str      w0, [x1, #0x10]
0x0014b978: 00 00 80 12              mov      w0, #-1
0x0014b97c: 4d 00 00 54              b.le     #0x14b984
0x0014b980: 80 13 40 b9              ldr      w0, [x28, #0x10]
0x0014b984: a1 57 40 f9              ldr      x1, [x29, #0xa8]
0x0014b988: fb 03 01 aa              mov      x27, x1
0x0014b98c: 20 14 00 b9              str      w0, [x1, #0x14]
0x0014b990: 60 00 80 52              mov      w0, #3
0x0014b994: a1 4f 40 f9              ldr      x1, [x29, #0x98]
0x0014b998: 60 03 00 b9              str      w0, [x27]
0x0014b99c: e2 03 1a aa              mov      x2, x26
0x0014b9a0: 7a 1b 00 b9              str      w26, [x27, #0x18]
0x0014b9a4: 60 93 00 91              add      x0, x27, #0x24
0x0014b9a8: 8a f3 fc 97              bl       #0x887d0                   ; -> memcpy@plt
0x0014b9ac: a1 bf 40 b9              ldr      w1, [x29, #0xbc]
0x0014b9b0: 60 7a 42 b9              ldr      w0, [x19, #0x278]
0x0014b9b4: 60 1f 00 b9              str      w0, [x27, #0x1c]
0x0014b9b8: 00 10 3e 1e              fmov     s0, #-1.00000000
0x0014b9bc: 60 26 41 f9              ldr      x0, [x19, #0x248]
0x0014b9c0: 61 23 00 b9              str      w1, [x27, #0x20]
0x0014b9c4: bf a3 00 f9              str      xzr, [x29, #0x140]
0x0014b9c8: a0 4b 01 bd              str      s0, [x29, #0x148]
0x0014b9cc: e0 34 00 b4              cbz      x0, #0x14c068
0x0014b9d0: 60 e2 08 91              add      x0, x19, #0x238
0x0014b9d4: a1 57 40 f9              ldr      x1, [x29, #0xa8]
0x0014b9d8: 05 0c 40 f9              ldr      x5, [x0, #0x18]
0x0014b9dc: e3 03 19 aa              mov      x3, x25
0x0014b9e0: a2 83 40 b9              ldr      w2, [x29, #0x80]
0x0014b9e4: 84 01 80 52              mov      w4, #0xc
0x0014b9e8: a0 00 3f d6              blr      x5
0x0014b9ec: fa 03 00 2a              mov      w26, w0
0x0014b9f0: 01 01 80 52              mov      w1, #8
0x0014b9f4: e0 03 18 2a              mov      w0, w24
0x0014b9f8: 14 db ff 97              bl       #0x142648                  ; -> _ZN12stfaceunlock9CommonFun9CheckFlagEjj
0x0014b9fc: 00 1c 00 53              uxtb     w0, w0
0x0014ba00: a0 00 00 35              cbnz     w0, #0x14ba14
0x0014ba04: 61 2e 42 bd              ldr      s1, [x19, #0x22c]
0x0014ba08: a0 bf 40 bd              ldr      s0, [x29, #0xbc]
0x0014ba0c: 30 20 20 1e              fcmpe    s1, s0
0x0014ba10: e4 07 00 54              b.mi     #0x14bb0c
0x0014ba14: a0 57 40 f9              ldr      x0, [x29, #0xa8]
0x0014ba18: 03 00 40 b9              ldr      w3, [x0]
0x0014ba1c: 5a 1c 00 34              cbz      w26, #0x14bda4
0x0014ba20: a0 30 00 d0              adrp     x0, #0x761000
0x0014ba24: c1 30 00 b0              adrp     x1, #0x764000
0x0014ba28: 00 a0 05 91              add      x0, x0, #0x168
0x0014ba2c: 21 e0 12 91              add      x1, x1, #0x4b8
0x0014ba30: c2 a3 80 52              mov      w2, #0x51e
0x0014ba34: e4 03 1a 2a              mov      w4, w26
0x0014ba38: ad cc ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0014ba3c: 5f 17 00 71              cmp      w26, #5
0x0014ba40: 60 06 00 54              b.eq     #0x14bb0c
0x0014ba44: 5a 19 80 52              mov      w26, #0xca
0x0014ba48: a0 57 40 f9              ldr      x0, [x29, #0xa8]
0x0014ba4c: ef 75 16 94              bl       #0x6e9208                  ; -> _ZdaPv
0x0014ba50: a0 4f 40 f9              ldr      x0, [x29, #0x98]
0x0014ba54: ed 75 16 94              bl       #0x6e9208                  ; -> _ZdaPv
0x0014ba58: 8c ff ff 17              b        #0x14b888                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0x4e0
0x0014ba5c: 60 a2 40 f9              ldr      x0, [x19, #0x140]
0x0014ba60: a0 83 00 f9              str      x0, [x29, #0x100]
0x0014ba64: bf 23 04 39              strb     wzr, [x29, #0x108]
0x0014ba68: a0 31 00 b4              cbz      x0, #0x14c09c
0x0014ba6c: e1 6e 02 d0              adrp     x1, #0x4f29000
0x0014ba70: a1 3f 00 f9              str      x1, [x29, #0x78]
0x0014ba74: 21 94 47 f9              ldr      x1, [x1, #0xf28]           ; -> pthread_create@got
0x0014ba78: 81 00 00 b4              cbz      x1, #0x14ba88
0x0014ba7c: 91 f4 fc 97              bl       #0x88cc0                   ; -> pthread_mutex_lock@plt
0x0014ba80: 40 32 00 35              cbnz     w0, #0x14c0c8
0x0014ba84: 60 a2 40 f9              ldr      x0, [x19, #0x140]
0x0014ba88: 00 a0 00 91              add      x0, x0, #0x28
0x0014ba8c: a0 4f 00 f9              str      x0, [x29, #0x98]
0x0014ba90: 20 00 80 52              mov      w0, #1
0x0014ba94: a0 23 04 39              strb     w0, [x29, #0x108]
0x0014ba98: 60 42 05 91              add      x0, x19, #0x150
0x0014ba9c: a0 57 00 f9              str      x0, [x29, #0xa8]
0x0014baa0: a0 03 04 91              add      x0, x29, #0x100
0x0014baa4: a0 53 00 f9              str      x0, [x29, #0xa0]
0x0014baa8: 04 00 00 14              b        #0x14bab8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0x710
0x0014baac: a0 4f 40 f9              ldr      x0, [x29, #0x98]
0x0014bab0: a1 53 40 f9              ldr      x1, [x29, #0xa0]
0x0014bab4: ef 7a 17 94              bl       #0x72a670                  ; -> _ZNSt18condition_variable4waitERSt11unique_lockISt5mutexE
0x0014bab8: a0 57 40 f9              ldr      x0, [x29, #0xa8]
0x0014babc: 00 fc df 08              ldarb    w0, [x0]
0x0014bac0: 00 1c 00 53              uxtb     w0, w0
0x0014bac4: 40 ff ff 34              cbz      w0, #0x14baac
0x0014bac8: 60 42 05 91              add      x0, x19, #0x150
0x0014bacc: 1f fc 9f 08              stlrb    wzr, [x0]
0x0014bad0: a2 43 40 f9              ldr      x2, [x29, #0x80]
0x0014bad4: a1 23 44 39              ldrb     w1, [x29, #0x108]
0x0014bad8: 40 14 40 b9              ldr      w0, [x2, #0x14]
0x0014badc: 00 04 00 51              sub      w0, w0, #1
0x0014bae0: 40 14 00 b9              str      w0, [x2, #0x14]
0x0014bae4: 40 18 40 b9              ldr      w0, [x2, #0x18]            ; -> __bcl_binary_data_size@got
0x0014bae8: a0 83 00 b9              str      w0, [x29, #0x80]
0x0014baec: 41 ea ff 34              cbz      w1, #0x14b834
0x0014baf0: a0 83 40 f9              ldr      x0, [x29, #0x100]
0x0014baf4: 00 ea ff b4              cbz      x0, #0x14b834
0x0014baf8: a1 3f 40 f9              ldr      x1, [x29, #0x78]
0x0014bafc: 21 94 47 f9              ldr      x1, [x1, #0xf28]           ; -> pthread_create@got
0x0014bb00: a1 e9 ff b4              cbz      x1, #0x14b834
0x0014bb04: 47 f3 fc 97              bl       #0x88820                   ; -> pthread_mutex_unlock@plt
0x0014bb08: 4b ff ff 17              b        #0x14b834                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0x48c
0x0014bb0c: 5a 00 80 52              mov      w26, #2
0x0014bb10: ce ff ff 17              b        #0x14ba48                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0x6a0
0x0014bb14: c0 30 00 b0              adrp     x0, #0x764000
0x0014bb18: 62 9d 80 52              mov      w2, #0x4eb
0x0014bb1c: 01 e0 12 91              add      x1, x0, #0x4b8
0x0014bb20: c0 30 00 b0              adrp     x0, #0x764000
0x0014bb24: 00 c0 23 91              add      x0, x0, #0x8f0
0x0014bb28: 71 cc ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0014bb2c: 7a 00 80 12              mov      w26, #-4
0x0014bb30: 56 ff ff 17              b        #0x14b888                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0x4e0
0x0014bb34: c0 30 00 d0              adrp     x0, #0x765000
0x0014bb38: c1 30 00 b0              adrp     x1, #0x764000
0x0014bb3c: 00 00 0f 91              add      x0, x0, #0x3c0
0x0014bb40: 21 e0 12 91              add      x1, x1, #0x4b8
0x0014bb44: 62 8d 80 52              mov      w2, #0x46b
0x0014bb48: 03 00 80 d2              mov      x3, #0
0x0014bb4c: 68 cc ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0014bb50: 1a 00 80 12              mov      w26, #-1
0x0014bb54: 4a fe ff 17              b        #0x14b47c                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0xd4
0x0014bb58: 60 a2 40 f9              ldr      x0, [x19, #0x140]
0x0014bb5c: a0 73 00 f9              str      x0, [x29, #0xe0]
0x0014bb60: bf a3 03 39              strb     wzr, [x29, #0xe8]
0x0014bb64: 80 2a 00 b4              cbz      x0, #0x14c0b4
0x0014bb68: e1 6e 02 d0              adrp     x1, #0x4f29000
0x0014bb6c: a1 3f 00 f9              str      x1, [x29, #0x78]
0x0014bb70: 21 94 47 f9              ldr      x1, [x1, #0xf28]           ; -> pthread_create@got
0x0014bb74: 81 00 00 b4              cbz      x1, #0x14bb84
0x0014bb78: 52 f4 fc 97              bl       #0x88cc0                   ; -> pthread_mutex_lock@plt
0x0014bb7c: 20 2a 00 35              cbnz     w0, #0x14c0c0
0x0014bb80: 60 a2 40 f9              ldr      x0, [x19, #0x140]
0x0014bb84: 15 a0 00 91              add      x21, x0, #0x28
0x0014bb88: 78 42 05 91              add      x24, x19, #0x150
0x0014bb8c: 20 00 80 52              mov      w0, #1
0x0014bb90: b9 83 03 91              add      x25, x29, #0xe0
0x0014bb94: a0 a3 03 39              strb     w0, [x29, #0xe8]
0x0014bb98: 04 00 00 14              b        #0x14bba8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0x800
0x0014bb9c: e0 03 15 aa              mov      x0, x21
0x0014bba0: e1 03 19 aa              mov      x1, x25
0x0014bba4: b3 7a 17 94              bl       #0x72a670                  ; -> _ZNSt18condition_variable4waitERSt11unique_lockISt5mutexE
0x0014bba8: 00 ff df 08              ldarb    w0, [x24]
0x0014bbac: 00 1c 00 53              uxtb     w0, w0
0x0014bbb0: 60 ff ff 34              cbz      w0, #0x14bb9c
0x0014bbb4: 60 42 05 91              add      x0, x19, #0x150
0x0014bbb8: 1f fc 9f 08              stlrb    wzr, [x0]
0x0014bbbc: a2 43 40 f9              ldr      x2, [x29, #0x80]
0x0014bbc0: a1 a3 43 39              ldrb     w1, [x29, #0xe8]
0x0014bbc4: 40 14 40 b9              ldr      w0, [x2, #0x14]
0x0014bbc8: 00 04 00 51              sub      w0, w0, #1
0x0014bbcc: 40 14 00 b9              str      w0, [x2, #0x14]
0x0014bbd0: 61 dc ff 34              cbz      w1, #0x14b75c
0x0014bbd4: a0 73 40 f9              ldr      x0, [x29, #0xe0]
0x0014bbd8: 20 dc ff b4              cbz      x0, #0x14b75c
0x0014bbdc: a1 3f 40 f9              ldr      x1, [x29, #0x78]
0x0014bbe0: 21 94 47 f9              ldr      x1, [x1, #0xf28]           ; -> pthread_create@got
0x0014bbe4: c1 db ff b4              cbz      x1, #0x14b75c
0x0014bbe8: 0e f3 fc 97              bl       #0x88820                   ; -> pthread_mutex_unlock@plt
0x0014bbec: dc fe ff 17              b        #0x14b75c                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0x3b4
0x0014bbf0: 60 32 40 f9              ldr      x0, [x19, #0x60]
0x0014bbf4: a0 7b 00 f9              str      x0, [x29, #0xf0]
0x0014bbf8: bf e3 03 39              strb     wzr, [x29, #0xf8]
0x0014bbfc: 40 24 00 b4              cbz      x0, #0x14c084
0x0014bc00: e1 6e 02 d0              adrp     x1, #0x4f29000
0x0014bc04: a1 3f 00 f9              str      x1, [x29, #0x78]
0x0014bc08: 21 94 47 f9              ldr      x1, [x1, #0xf28]           ; -> pthread_create@got
0x0014bc0c: 81 00 00 b4              cbz      x1, #0x14bc1c
0x0014bc10: 2c f4 fc 97              bl       #0x88cc0                   ; -> pthread_mutex_lock@plt
0x0014bc14: 80 25 00 35              cbnz     w0, #0x14c0c4
0x0014bc18: 60 32 40 f9              ldr      x0, [x19, #0x60]
0x0014bc1c: 15 a0 00 91              add      x21, x0, #0x28
0x0014bc20: 78 c2 01 91              add      x24, x19, #0x70
0x0014bc24: 20 00 80 52              mov      w0, #1
0x0014bc28: b9 c3 03 91              add      x25, x29, #0xf0
0x0014bc2c: a0 e3 03 39              strb     w0, [x29, #0xf8]
0x0014bc30: 04 00 00 14              b        #0x14bc40                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0x898
0x0014bc34: e0 03 15 aa              mov      x0, x21
0x0014bc38: e1 03 19 aa              mov      x1, x25
0x0014bc3c: 8d 7a 17 94              bl       #0x72a670                  ; -> _ZNSt18condition_variable4waitERSt11unique_lockISt5mutexE
0x0014bc40: 00 ff df 08              ldarb    w0, [x24]
0x0014bc44: 00 1c 00 53              uxtb     w0, w0
0x0014bc48: 60 ff ff 34              cbz      w0, #0x14bc34
0x0014bc4c: 60 c2 01 91              add      x0, x19, #0x70
0x0014bc50: 1f fc 9f 08              stlrb    wzr, [x0]
0x0014bc54: 40 17 40 b9              ldr      w0, [x26, #0x14]
0x0014bc58: a1 e3 43 39              ldrb     w1, [x29, #0xf8]
0x0014bc5c: 00 04 00 51              sub      w0, w0, #1
0x0014bc60: 40 17 00 b9              str      w0, [x26, #0x14]
0x0014bc64: 01 d8 ff 34              cbz      w1, #0x14b764
0x0014bc68: a0 7b 40 f9              ldr      x0, [x29, #0xf0]
0x0014bc6c: c0 d7 ff b4              cbz      x0, #0x14b764
0x0014bc70: a1 3f 40 f9              ldr      x1, [x29, #0x78]
0x0014bc74: 21 94 47 f9              ldr      x1, [x1, #0xf28]           ; -> pthread_create@got
0x0014bc78: 61 d7 ff b4              cbz      x1, #0x14b764
0x0014bc7c: e9 f2 fc 97              bl       #0x88820                   ; -> pthread_mutex_unlock@plt
0x0014bc80: b9 fe ff 17              b        #0x14b764                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0x3bc
0x0014bc84: 60 32 40 f9              ldr      x0, [x19, #0x60]
0x0014bc88: a0 8b 00 f9              str      x0, [x29, #0x110]
0x0014bc8c: bf 63 04 39              strb     wzr, [x29, #0x118]
0x0014bc90: 00 22 00 b4              cbz      x0, #0x14c0d0
0x0014bc94: e1 6e 02 d0              adrp     x1, #0x4f29000
0x0014bc98: a1 3f 00 f9              str      x1, [x29, #0x78]
0x0014bc9c: 21 94 47 f9              ldr      x1, [x1, #0xf28]           ; -> pthread_create@got
0x0014bca0: 81 00 00 b4              cbz      x1, #0x14bcb0
0x0014bca4: 07 f4 fc 97              bl       #0x88cc0                   ; -> pthread_mutex_lock@plt
0x0014bca8: 20 21 00 35              cbnz     w0, #0x14c0cc
0x0014bcac: 60 32 40 f9              ldr      x0, [x19, #0x60]
0x0014bcb0: 00 a0 00 91              add      x0, x0, #0x28
0x0014bcb4: a0 4f 00 f9              str      x0, [x29, #0x98]
0x0014bcb8: 20 00 80 52              mov      w0, #1
0x0014bcbc: a0 63 04 39              strb     w0, [x29, #0x118]
0x0014bcc0: 60 c2 01 91              add      x0, x19, #0x70
0x0014bcc4: a0 57 00 f9              str      x0, [x29, #0xa8]
0x0014bcc8: a0 43 04 91              add      x0, x29, #0x110
0x0014bccc: a0 53 00 f9              str      x0, [x29, #0xa0]
0x0014bcd0: 04 00 00 14              b        #0x14bce0                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0x938
0x0014bcd4: a0 4f 40 f9              ldr      x0, [x29, #0x98]
0x0014bcd8: a1 53 40 f9              ldr      x1, [x29, #0xa0]
0x0014bcdc: 65 7a 17 94              bl       #0x72a670                  ; -> _ZNSt18condition_variable4waitERSt11unique_lockISt5mutexE
0x0014bce0: a0 57 40 f9              ldr      x0, [x29, #0xa8]
0x0014bce4: 00 fc df 08              ldarb    w0, [x0]
0x0014bce8: 00 1c 00 53              uxtb     w0, w0
0x0014bcec: 40 ff ff 34              cbz      w0, #0x14bcd4
0x0014bcf0: 60 c2 01 91              add      x0, x19, #0x70
0x0014bcf4: 1f fc 9f 08              stlrb    wzr, [x0]
0x0014bcf8: 40 17 40 b9              ldr      w0, [x26, #0x14]
0x0014bcfc: a1 63 44 39              ldrb     w1, [x29, #0x118]
0x0014bd00: 00 04 00 51              sub      w0, w0, #1
0x0014bd04: 40 17 00 b9              str      w0, [x26, #0x14]
0x0014bd08: 5a 1b 40 b9              ldr      w26, [x26, #0x18]
0x0014bd0c: a1 d9 ff 34              cbz      w1, #0x14b840
0x0014bd10: a0 8b 40 f9              ldr      x0, [x29, #0x110]
0x0014bd14: 60 d9 ff b4              cbz      x0, #0x14b840
0x0014bd18: a1 3f 40 f9              ldr      x1, [x29, #0x78]
0x0014bd1c: 21 94 47 f9              ldr      x1, [x1, #0xf28]           ; -> pthread_create@got
0x0014bd20: 01 d9 ff b4              cbz      x1, #0x14b840
0x0014bd24: bf f2 fc 97              bl       #0x88820                   ; -> pthread_mutex_unlock@plt
0x0014bd28: c6 fe ff 17              b        #0x14b840                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0x498
0x0014bd2c: c0 30 00 d0              adrp     x0, #0x765000
0x0014bd30: c1 30 00 b0              adrp     x1, #0x764000
0x0014bd34: 00 00 10 91              add      x0, x0, #0x400
0x0014bd38: 21 e0 12 91              add      x1, x1, #0x4b8
0x0014bd3c: e2 8d 80 52              mov      w2, #0x46f
0x0014bd40: eb cb ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0014bd44: 1a 00 80 12              mov      w26, #-1
0x0014bd48: cd fd ff 17              b        #0x14b47c                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0xd4
0x0014bd4c: 40 0b 40 b9              ldr      w0, [x26, #8]
0x0014bd50: 00 04 00 11              add      w0, w0, #1
0x0014bd54: 40 0b 00 b9              str      w0, [x26, #8]
0x0014bd58: 0d fe ff 17              b        #0x14b58c                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0x1e4
0x0014bd5c: a1 57 40 f9              ldr      x1, [x29, #0xa8]
0x0014bd60: 00 00 80 12              mov      w0, #-1
0x0014bd64: fb 03 01 aa              mov      x27, x1
0x0014bd68: 20 04 00 b9              str      w0, [x1, #4]
0x0014bd6c: 20 08 00 b9              str      w0, [x1, #8]
0x0014bd70: 20 0c 00 b9              str      w0, [x1, #0xc]
0x0014bd74: 20 10 00 b9              str      w0, [x1, #0x10]
0x0014bd78: 20 14 00 b9              str      w0, [x1, #0x14]
0x0014bd7c: 05 ff ff 17              b        #0x14b990                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0x5e8
0x0014bd80: c0 30 00 b0              adrp     x0, #0x764000
0x0014bd84: a3 83 40 b9              ldr      w3, [x29, #0x80]
0x0014bd88: 01 e0 12 91              add      x1, x0, #0x4b8
0x0014bd8c: c0 30 00 d0              adrp     x0, #0x765000
0x0014bd90: 00 60 0c 91              add      x0, x0, #0x318
0x0014bd94: 02 9a 80 52              mov      w2, #0x4d0
0x0014bd98: d5 cb ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0014bd9c: ba 83 40 b9              ldr      w26, [x29, #0x80]
0x0014bda0: ba fe ff 17              b        #0x14b888                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0x4e0
0x0014bda4: c0 30 00 d0              adrp     x0, #0x765000
0x0014bda8: c1 30 00 b0              adrp     x1, #0x764000
0x0014bdac: a4 43 41 b9              ldr      w4, [x29, #0x140]
0x0014bdb0: 00 a0 12 91              add      x0, x0, #0x4a8
0x0014bdb4: 21 e0 12 91              add      x1, x1, #0x4b8
0x0014bdb8: a2 a4 80 52              mov      w2, #0x525
0x0014bdbc: 46 cb ff 97              bl       #0x13ead4                  ; -> _Z4LogIPKcz
0x0014bdc0: a0 43 41 b9              ldr      w0, [x29, #0x140]
0x0014bdc4: 5a 19 80 52              mov      w26, #0xca
0x0014bdc8: 1f 1c 00 71              cmp      w0, #7
0x0014bdcc: 40 0b 00 54              b.eq     #0x14bf34
0x0014bdd0: a0 57 40 f9              ldr      x0, [x29, #0xa8]
0x0014bdd4: 0d 75 16 94              bl       #0x6e9208                  ; -> _ZdaPv
0x0014bdd8: a0 4f 40 f9              ldr      x0, [x29, #0x98]
0x0014bddc: 0b 75 16 94              bl       #0x6e9208                  ; -> _ZdaPv
0x0014bde0: 5f 2b 03 71              cmp      w26, #0xca
0x0014bde4: a0 04 00 54              b.eq     #0x14be78
0x0014bde8: 5f 27 03 71              cmp      w26, #0xc9
0x0014bdec: e1 d4 ff 54              b.ne     #0x14b888
0x0014bdf0: a0 b7 40 b9              ldr      w0, [x29, #0xb4]
0x0014bdf4: 60 8e 02 b9              str      w0, [x19, #0x28c]
0x0014bdf8: a4 fe ff 17              b        #0x14b888                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0x4e0
0x0014bdfc: 00 00 80 12              mov      w0, #-1
0x0014be00: 20 08 00 b9              str      w0, [x1, #8]
0x0014be04: 20 0c 00 b9              str      w0, [x1, #0xc]
0x0014be08: 00 00 80 12              mov      w0, #-1
0x0014be0c: 20 10 00 b9              str      w0, [x1, #0x10]
0x0014be10: 00 00 80 12              mov      w0, #-1
0x0014be14: dc fe ff 17              b        #0x14b984                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0x5dc
0x0014be18: e0 03 18 2a              mov      w0, w24
0x0014be1c: 01 01 80 52              mov      w1, #8
0x0014be20: 0a da ff 97              bl       #0x142648                  ; -> _ZN12stfaceunlock9CommonFun9CheckFlagEjj
0x0014be24: 00 1c 00 53              uxtb     w0, w0
0x0014be28: c0 00 00 35              cbnz     w0, #0x14be40
0x0014be2c: 61 2e 42 bd              ldr      s1, [x19, #0x22c]
0x0014be30: 5a 00 80 52              mov      w26, #2
0x0014be34: a0 bf 40 bd              ldr      s0, [x29, #0xbc]
0x0014be38: 30 20 20 1e              fcmpe    s1, s0
0x0014be3c: 64 d2 ff 54              b.mi     #0x14b888
0x0014be40: 60 22 42 bd              ldr      s0, [x19, #0x220]
0x0014be44: 60 26 40 f9              ldr      x0, [x19, #0x48]
0x0014be48: a1 53 40 f9              ldr      x1, [x29, #0xa0]
0x0014be4c: 62 42 01 91              add      x2, x19, #0x50
0x0014be50: 01 10 3e 1e              fmov     s1, #-1.00000000
0x0014be54: 63 82 00 91              add      x3, x19, #0x20
0x0014be58: e4 03 1c aa              mov      x4, x28
0x0014be5c: e5 03 1b 2a              mov      w5, w27
0x0014be60: e6 03 19 aa              mov      x6, x25
0x0014be64: a7 d3 02 91              add      x7, x29, #0xb4
0x0014be68: a1 43 01 bd              str      s1, [x29, #0x140]
0x0014be6c: a0 a6 ff 97              bl       #0x1358ec                  ; -> _ZN12stfaceunlock14FeatureManager6UnlockEP12st_feature_tRKSsfRKSt10shared_ptrINS_17FaceVerifyManagerEEPiiRfRi
0x0014be70: fa 03 00 2a              mov      w26, w0
0x0014be74: db ff ff 17              b        #0x14bde0                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0xa38
0x0014be78: a0 63 40 f9              ldr      x0, [x29, #0xc0]
0x0014be7c: 7a 0e 80 52              mov      w26, #0x73
0x0014be80: 00 18 40 bd              ldr      s0, [x0, #0x18]
0x0014be84: 10 20 28 1e              fcmpe    s0, s8
0x0014be88: 04 d0 ff 54              b.mi     #0x14b888
0x0014be8c: 10 20 29 1e              fcmpe    s0, s9
0x0014be90: 9a 0e 80 52              mov      w26, #0x74
0x0014be94: ac cf ff 54              b.gt     #0x14b888
0x0014be98: 00 1c 40 bd              ldr      s0, [x0, #0x1c]
0x0014be9c: 5a 0e 80 52              mov      w26, #0x72
0x0014bea0: 10 20 29 1e              fcmpe    s0, s9
0x0014bea4: 2c cf ff 54              b.gt     #0x14b888
0x0014bea8: 10 20 28 1e              fcmpe    s0, s8
0x0014beac: 3a 0e 80 52              mov      w26, #0x71
0x0014beb0: c4 ce ff 54              b.mi     #0x14b888
0x0014beb4: e0 03 27 1e              fmov     s0, wzr
0x0014beb8: a0 22 00 91              add      x0, x21, #8
0x0014bebc: e1 03 19 aa              mov      x1, x25
0x0014bec0: a0 23 01 bd              str      s0, [x29, #0x120]
0x0014bec4: 8d d4 ff 97              bl       #0x1410f8                  ; -> _ZN12stfaceunlock11ImageHepler18StFormatToCvFormatERK15st_pixel_formatR15cv_pixel_format
0x0014bec8: a5 63 40 f9              ldr      x5, [x29, #0xc0]
0x0014becc: a6 83 04 91              add      x6, x29, #0x120
0x0014bed0: a0 02 40 f9              ldr      x0, [x21]
0x0014bed4: 07 00 80 d2              mov      x7, #0
0x0014bed8: a1 0e 40 b9              ldr      w1, [x21, #0xc]
0x0014bedc: a2 12 40 b9              ldr      w2, [x21, #0x10]
0x0014bee0: a3 16 40 b9              ldr      w3, [x21, #0x14]
0x0014bee4: a4 43 41 b9              ldr      w4, [x29, #0x140]
0x0014bee8: a5 18 46 f9              ldr      x5, [x5, #0xc30]
0x0014beec: 2b dd ff 97              bl       #0x143398                  ; -> _ZN12stfaceunlock9CommonFun7GetMeanEPhiii15cv_pixel_formatPK14cv_landmarks_tPfS6_
0x0014bef0: fa 03 00 2a              mov      w26, w0
0x0014bef4: a0 cc ff 35              cbnz     w0, #0x14b888
0x0014bef8: a0 23 41 bd              ldr      s0, [x29, #0x120]
0x0014befc: c0 30 00 d0              adrp     x0, #0x765000
0x0014bf00: c1 30 00 b0              adrp     x1, #0x764000
0x0014bf04: 00 a0 13 91              add      x0, x0, #0x4e8
0x0014bf08: 00 c0 22 1e              fcvt     d0, s0
0x0014bf0c: 21 e0 12 91              add      x1, x1, #0x4b8
0x0014bf10: 02 aa 80 52              mov      w2, #0x550
0x0014bf14: ad ca ff 97              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x0014bf18: a0 0e 00 1c              ldr      s0, #0x14c0ec
0x0014bf1c: 40 19 80 52              mov      w0, #0xca
0x0014bf20: a1 23 41 bd              ldr      s1, [x29, #0x120]
0x0014bf24: 7a 0f 80 52              mov      w26, #0x7b
0x0014bf28: 30 20 20 1e              fcmpe    s1, s0
0x0014bf2c: 5a 43 80 1a              csel     w26, w26, w0, mi
0x0014bf30: 56 fe ff 17              b        #0x14b888                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0x4e0
0x0014bf34: a0 47 41 b9              ldr      w0, [x29, #0x144]
0x0014bf38: 3a 19 80 52              mov      w26, #0xc9
0x0014bf3c: a0 b7 00 b9              str      w0, [x29, #0xb4]
0x0014bf40: a4 ff ff 17              b        #0x14bdd0                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0xa28
0x0014bf44: f3 03 00 aa              mov      x19, x0
0x0014bf48: b8 79 17 94              bl       #0x72a628                  ; -> _ZNSt6chrono3_V212steady_clock3nowEv
0x0014bf4c: 00 00 14 cb              sub      x0, x0, x20
0x0014bf50: 04 48 88 d2              mov      x4, #0x4240
0x0014bf54: c1 a2 25 91              add      x1, x22, #0x968
0x0014bf58: e4 01 a0 f2              movk     x4, #0xf, lsl #16
0x0014bf5c: 62 05 80 52              mov      w2, #0x2b
0x0014bf60: e3 c2 0e 91              add      x3, x23, #0x3b0
0x0014bf64: 04 0c c4 9a              sdiv     x4, x0, x4
0x0014bf68: a0 30 00 d0              adrp     x0, #0x761000
0x0014bf6c: 00 40 2b 91              add      x0, x0, #0xad0
0x0014bf70: 1c cb ff 97              bl       #0x13ebe0                  ; -> _Z4LogWPKcz
0x0014bf74: e0 03 13 aa              mov      x0, x19
0x0014bf78: cf 10 18 94              bl       #0x7502b4                  ; -> _Unwind_Resume
0x0014bf7c: a3 ab 40 f9              ldr      x3, [x29, #0x150]
0x0014bf80: f3 03 00 aa              mov      x19, x0
0x0014bf84: a3 00 00 b4              cbz      x3, #0x14bf98
0x0014bf88: a0 03 05 91              add      x0, x29, #0x140
0x0014bf8c: 62 00 80 52              mov      w2, #3
0x0014bf90: e1 03 00 aa              mov      x1, x0
0x0014bf94: 60 00 3f d6              blr      x3
0x0014bf98: 7a 00 00 b4              cbz      x26, #0x14bfa4
0x0014bf9c: e0 03 1a aa              mov      x0, x26
0x0014bfa0: 94 18 ff 97              bl       #0x1121f0                  ; -> _ZNSt16_Sp_counted_baseILN9__gnu_cxx12_Lock_policyE2EE10_M_releaseEv
0x0014bfa4: e0 03 13 aa              mov      x0, x19
0x0014bfa8: f3 03 00 aa              mov      x19, x0
0x0014bfac: a0 4b 40 f9              ldr      x0, [x29, #0x90]
0x0014bfb0: c0 fc ff b4              cbz      x0, #0x14bf48
0x0014bfb4: a1 8f 40 b9              ldr      w1, [x29, #0x8c]
0x0014bfb8: de e0 ff 97              bl       #0x144330                  ; -> _ZN12stfaceunlock17FaceDetectManager22ReleaseDetectionResultEP9st_face_ti
0x0014bfbc: e3 ff ff 17              b        #0x14bf48                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0xba0
0x0014bfc0: fa ff ff 17              b        #0x14bfa8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0xc00
0x0014bfc4: a3 9b 40 f9              ldr      x3, [x29, #0x130]
0x0014bfc8: f3 03 00 aa              mov      x19, x0
0x0014bfcc: e3 fb ff b4              cbz      x3, #0x14bf48
0x0014bfd0: e0 03 19 aa              mov      x0, x25
0x0014bfd4: e1 03 19 aa              mov      x1, x25
0x0014bfd8: 62 00 80 52              mov      w2, #3
0x0014bfdc: 60 00 3f d6              blr      x3
0x0014bfe0: da ff ff 17              b        #0x14bf48                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0xba0
0x0014bfe4: a3 9b 40 f9              ldr      x3, [x29, #0x130]
0x0014bfe8: f3 03 00 aa              mov      x19, x0
0x0014bfec: a3 00 00 b4              cbz      x3, #0x14c000
0x0014bff0: a0 83 04 91              add      x0, x29, #0x120
0x0014bff4: 62 00 80 52              mov      w2, #3
0x0014bff8: e1 03 00 aa              mov      x1, x0
0x0014bffc: 60 00 3f d6              blr      x3
0x0014c000: 5a fa ff b4              cbz      x26, #0x14bf48
0x0014c004: e0 03 1a aa              mov      x0, x26
0x0014c008: 7a 18 ff 97              bl       #0x1121f0                  ; -> _ZNSt16_Sp_counted_baseILN9__gnu_cxx12_Lock_policyE2EE10_M_releaseEv
0x0014c00c: cf ff ff 17              b        #0x14bf48                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0xba0
0x0014c010: f3 03 00 aa              mov      x19, x0
0x0014c014: a0 4f 40 f9              ldr      x0, [x29, #0x98]
0x0014c018: 7c 74 16 94              bl       #0x6e9208                  ; -> _ZdaPv
0x0014c01c: a0 53 40 f9              ldr      x0, [x29, #0xa0]
0x0014c020: 20 fc ff b4              cbz      x0, #0x14bfa4
0x0014c024: 66 68 ff 97              bl       #0x1261bc                  ; -> _ZN12stfaceunlock17FaceVerifyManager14ReleaseFeatureEP12st_feature_t
0x0014c028: df ff ff 17              b        #0x14bfa4                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0xbfc
0x0014c02c: f3 03 00 aa              mov      x19, x0
0x0014c030: fb ff ff 17              b        #0x14c01c                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0xc74
0x0014c034: dd ff ff 17              b        #0x14bfa8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0xc00
0x0014c038: a3 ab 40 f9              ldr      x3, [x29, #0x150]
0x0014c03c: f3 03 00 aa              mov      x19, x0
0x0014c040: 23 fb ff b4              cbz      x3, #0x14bfa4
0x0014c044: e0 03 19 aa              mov      x0, x25
0x0014c048: e1 03 19 aa              mov      x1, x25
0x0014c04c: 62 00 80 52              mov      w2, #3
0x0014c050: 60 00 3f d6              blr      x3
0x0014c054: d4 ff ff 17              b        #0x14bfa4                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0xbfc
0x0014c058: f3 03 00 aa              mov      x19, x0
0x0014c05c: a0 57 40 f9              ldr      x0, [x29, #0xa8]
0x0014c060: 6a 74 16 94              bl       #0x6e9208                  ; -> _ZdaPv
0x0014c064: ec ff ff 17              b        #0x14c014                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0xc6c
0x0014c068: 22 9b 17 94              bl       #0x732cf0                  ; -> _ZSt25__throw_bad_function_callv
0x0014c06c: 00 00 80 12              mov      w0, #-1
0x0014c070: a1 57 40 f9              ldr      x1, [x29, #0xa8]
0x0014c074: 66 ff ff 17              b        #0x14be0c                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0xa64
0x0014c078: 00 00 80 12              mov      w0, #-1
0x0014c07c: a1 57 40 f9              ldr      x1, [x29, #0xa8]
0x0014c080: 61 ff ff 17              b        #0x14be04                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0xa5c
0x0014c084: 20 00 80 52              mov      w0, #1
0x0014c088: ac 9a 17 94              bl       #0x732b38                  ; -> _ZSt20__throw_system_errori
0x0014c08c: f3 03 00 aa              mov      x19, x0
0x0014c090: e0 03 19 aa              mov      x0, x25
0x0014c094: dd 02 00 94              bl       #0x14cc08                  ; -> _ZNSt11unique_lockISt5mutexED2Ev
0x0014c098: c3 ff ff 17              b        #0x14bfa4                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0xbfc
0x0014c09c: 20 00 80 52              mov      w0, #1
0x0014c0a0: a6 9a 17 94              bl       #0x732b38                  ; -> _ZSt20__throw_system_errori
0x0014c0a4: f3 03 00 aa              mov      x19, x0
0x0014c0a8: a0 53 40 f9              ldr      x0, [x29, #0xa0]
0x0014c0ac: d7 02 00 94              bl       #0x14cc08                  ; -> _ZNSt11unique_lockISt5mutexED2Ev
0x0014c0b0: bd ff ff 17              b        #0x14bfa4                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0xbfc
0x0014c0b4: 20 00 80 52              mov      w0, #1
0x0014c0b8: a0 9a 17 94              bl       #0x732b38                  ; -> _ZSt20__throw_system_errori
0x0014c0bc: f4 ff ff 17              b        #0x14c08c                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0xce4
0x0014c0c0: 9e 9a 17 94              bl       #0x732b38                  ; -> _ZSt20__throw_system_errori
0x0014c0c4: 9d 9a 17 94              bl       #0x732b38                  ; -> _ZSt20__throw_system_errori
0x0014c0c8: 9c 9a 17 94              bl       #0x732b38                  ; -> _ZSt20__throw_system_errori
0x0014c0cc: 9b 9a 17 94              bl       #0x732b38                  ; -> _ZSt20__throw_system_errori
0x0014c0d0: 20 00 80 52              mov      w0, #1
0x0014c0d4: 99 9a 17 94              bl       #0x732b38                  ; -> _ZSt20__throw_system_errori
0x0014c0d8: f3 ff ff 17              b        #0x14c0a4                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii+0xcfc
