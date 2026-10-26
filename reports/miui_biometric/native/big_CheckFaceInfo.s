; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib (0x147264)
; start   : 0x147264
; size    : 0xa80
; bytes   : 0xa80   instructions: 672
; generated: 2026-10-24T00:34:01

0x00147264: ff 83 03 d1              sub      sp, sp, #0xe0
0x00147268: fd 7b 02 a9              stp      x29, x30, [sp, #0x20]
0x0014726c: fd 83 00 91              add      x29, sp, #0x20
0x00147270: f3 53 03 a9              stp      x19, x20, [sp, #0x30]
0x00147274: f5 5b 04 a9              stp      x21, x22, [sp, #0x40]
0x00147278: f7 63 05 a9              stp      x23, x24, [sp, #0x50]
0x0014727c: f9 6b 06 a9              stp      x25, x26, [sp, #0x60]
0x00147280: f3 03 00 aa              mov      x19, x0
0x00147284: f4 03 01 aa              mov      x20, x1
0x00147288: f8 03 03 2a              mov      w24, w3
0x0014728c: f9 03 04 aa              mov      x25, x4
0x00147290: d5 1c 00 53              uxtb     w21, w6
0x00147294: 44 00 40 b9              ldr      w4, [x2]
0x00147298: f6 30 00 b0              adrp     x22, #0x764000
0x0014729c: e0 30 00 b0              adrp     x0, #0x764000
0x001472a0: a5 3f 00 f9              str      x5, [x29, #0x78]
0x001472a4: 00 40 2e 91              add      x0, x0, #0xb90
0x001472a8: f7 03 02 aa              mov      x23, x2
0x001472ac: c1 e2 12 91              add      x1, x22, #0x4b8
0x001472b0: a2 32 80 52              mov      w2, #0x195
0x001472b4: e3 03 14 aa              mov      x3, x20
0x001472b8: e5 03 18 2a              mov      w5, w24
0x001472bc: e6 03 15 2a              mov      w6, w21
0x001472c0: fb 73 07 a9              stp      x27, x28, [sp, #0x70]
0x001472c4: c1 dd ff 97              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x001472c8: 60 02 40 f9              ldr      x0, [x19]
0x001472cc: e0 0b 00 b4              cbz      x0, #0x147448
0x001472d0: 61 06 40 f9              ldr      x1, [x19, #8]
0x001472d4: a1 0b 00 b4              cbz      x1, #0x147448
0x001472d8: 61 0e 40 f9              ldr      x1, [x19, #0x18]
0x001472dc: 61 0b 00 b4              cbz      x1, #0x147448
0x001472e0: 61 12 40 f9              ldr      x1, [x19, #0x20]
0x001472e4: 21 0b 00 b4              cbz      x1, #0x147448
0x001472e8: 14 15 00 b4              cbz      x20, #0x147588
0x001472ec: f9 14 00 b4              cbz      x25, #0x147588
0x001472f0: f5 01 00 35              cbnz     w21, #0x14732c
0x001472f4: 21 00 80 52              mov      w1, #1
0x001472f8: f0 f3 ff 97              bl       #0x1442b8                  ; -> _ZN12stfaceunlock17FaceDetectManager12SetFaceLimitEi
0x001472fc: fa 03 00 2a              mov      w26, w0
0x00147300: e0 01 00 34              cbz      w0, #0x14733c
0x00147304: bf 83 00 d1              sub      sp, x29, #0x20
0x00147308: e0 03 1a 2a              mov      w0, w26
0x0014730c: f3 53 43 a9              ldp      x19, x20, [sp, #0x30]
0x00147310: f5 5b 44 a9              ldp      x21, x22, [sp, #0x40]
0x00147314: f7 63 45 a9              ldp      x23, x24, [sp, #0x50]
0x00147318: f9 6b 46 a9              ldp      x25, x26, [sp, #0x60]
0x0014731c: fb 73 47 a9              ldp      x27, x28, [sp, #0x70]
0x00147320: fd 7b 42 a9              ldp      x29, x30, [sp, #0x20]
0x00147324: ff 83 03 91              add      sp, sp, #0xe0
0x00147328: c0 03 5f d6              ret      
0x0014732c: 01 04 80 52              mov      w1, #0x20
0x00147330: e2 f3 ff 97              bl       #0x1442b8                  ; -> _ZN12stfaceunlock17FaceDetectManager12SetFaceLimitEi
0x00147334: fa 03 00 2a              mov      w26, w0
0x00147338: 60 fe ff 35              cbnz     w0, #0x147304
0x0014733c: dc 30 00 d0              adrp     x28, #0x761000
0x00147340: fb 30 00 b0              adrp     x27, #0x764000
0x00147344: c0 30 00 d0              adrp     x0, #0x761000
0x00147348: 27 00 80 52              mov      w7, #1
0x0014734c: 81 a3 25 91              add      x1, x28, #0x968
0x00147350: 82 04 80 52              mov      w2, #0x24
0x00147354: 63 63 30 91              add      x3, x27, #0xc18
0x00147358: 00 40 25 91              add      x0, x0, #0x950
0x0014735c: a7 87 00 b9              str      w7, [x29, #0x84]
0x00147360: a7 3b 00 f9              str      x7, [x29, #0x70]
0x00147364: bf 4f 00 f9              str      xzr, [x29, #0x98]
0x00147368: bf 83 00 b9              str      wzr, [x29, #0x80]
0x0014736c: 1d de ff 97              bl       #0x13ebe0                  ; -> _Z4LogWPKcz
0x00147370: ae 8c 17 94              bl       #0x72a628                  ; -> _ZNSt6chrono3_V212steady_clock3nowEv
0x00147374: a0 53 00 f9              str      x0, [x29, #0xa0]
0x00147378: fa 03 00 aa              mov      x26, x0
0x0014737c: e2 02 40 b9              ldr      w2, [x23]
0x00147380: 60 02 40 f9              ldr      x0, [x19]
0x00147384: e1 03 14 aa              mov      x1, x20
0x00147388: a7 3b 40 f9              ldr      x7, [x29, #0x70]
0x0014738c: 83 00 80 52              mov      w3, #4
0x00147390: a4 13 02 91              add      x4, x29, #0x84
0x00147394: a5 63 02 91              add      x5, x29, #0x98
0x00147398: a6 03 02 91              add      x6, x29, #0x80
0x0014739c: cc f2 ff 97              bl       #0x143ecc                  ; -> _ZN12stfaceunlock17FaceDetectManager6DetectEPK10st_image_t19st_face_orientationjPS4_PP9st_face_tPii
0x001473a0: e5 03 00 2a              mov      w5, w0
0x001473a4: 40 09 00 35              cbnz     w0, #0x1474cc
0x001473a8: a0 8c 17 94              bl       #0x72a628                  ; -> _ZNSt6chrono3_V212steady_clock3nowEv
0x001473ac: 1a 00 1a cb              sub      x26, x0, x26
0x001473b0: 63 9b 86 d2              mov      x3, #0x34db
0x001473b4: c0 30 00 d0              adrp     x0, #0x761000
0x001473b8: c3 f6 ba f2              movk     x3, #0xd7b6, lsl #16
0x001473bc: 00 40 2b 91              add      x0, x0, #0xad0
0x001473c0: 43 d0 db f2              movk     x3, #0xde82, lsl #32
0x001473c4: 81 a3 25 91              add      x1, x28, #0x968
0x001473c8: 63 63 e8 f2              movk     x3, #0x431b, lsl #48
0x001473cc: 62 05 80 52              mov      w2, #0x2b
0x001473d0: 45 7f 43 9b              smulh    x5, x26, x3
0x001473d4: 63 63 30 91              add      x3, x27, #0xc18
0x001473d8: a4 fc 52 93              asr      x4, x5, #0x12
0x001473dc: 84 fc 9a cb              sub      x4, x4, x26, asr #63
0x001473e0: 00 de ff 97              bl       #0x13ebe0                  ; -> _Z4LogWPKcz
0x001473e4: 60 52 41 f9              ldr      x0, [x19, #0x2a0]
0x001473e8: 20 02 00 b4              cbz      x0, #0x14742c
0x001473ec: a0 83 40 b9              ldr      w0, [x29, #0x80]
0x001473f0: ba 83 02 91              add      x26, x29, #0xa0
0x001473f4: bf 7f 0a a9              stp      xzr, xzr, [x29, #0xa0]
0x001473f8: 1f 00 1f 6b              cmp      w0, wzr
0x001473fc: bf b3 00 b9              str      wzr, [x29, #0xb0]
0x00147400: e0 d7 9f 1a              cset     w0, gt
0x00147404: a0 83 02 39              strb     w0, [x29, #0xa0]
0x00147408: a0 00 00 34              cbz      w0, #0x14741c
0x0014740c: a1 4f 40 f9              ldr      x1, [x29, #0x98]
0x00147410: 40 13 00 91              add      x0, x26, #4
0x00147414: 22 0c 40 a9              ldp      x2, x3, [x1]
0x00147418: 02 0c 00 a9              stp      x2, x3, [x0]
0x0014741c: 60 42 0a 91              add      x0, x19, #0x290
0x00147420: 02 0c 40 f9              ldr      x2, [x0, #0x18]
0x00147424: e1 03 1a aa              mov      x1, x26
0x00147428: 40 00 3f d6              blr      x2
0x0014742c: bb 83 40 b9              ldr      w27, [x29, #0x80]
0x00147430: bb 01 00 35              cbnz     w27, #0x147464
0x00147434: 60 8a 42 b9              ldr      w0, [x19, #0x288]
0x00147438: 3a 00 80 52              mov      w26, #1
0x0014743c: 00 00 1a 0b              add      w0, w0, w26
0x00147440: 60 8a 02 b9              str      w0, [x19, #0x288]
0x00147444: b0 ff ff 17              b        #0x147304                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0xa0
0x00147448: e0 30 00 b0              adrp     x0, #0x764000
0x0014744c: c1 e2 12 91              add      x1, x22, #0x4b8
0x00147450: 00 40 3d 91              add      x0, x0, #0xf50
0x00147454: e2 32 80 52              mov      w2, #0x197
0x00147458: 7a 00 80 12              mov      w26, #-4
0x0014745c: 24 de ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x00147460: a9 ff ff 17              b        #0x147304                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0xa0
0x00147464: e0 03 27 1e              fmov     s0, wzr
0x00147468: a2 4f 40 f9              ldr      x2, [x29, #0x98]
0x0014746c: 7f 8a 02 b9              str      wzr, [x19, #0x288]
0x00147470: 80 22 00 91              add      x0, x20, #8
0x00147474: a1 43 02 91              add      x1, x29, #0x90
0x00147478: a2 3b 00 f9              str      x2, [x29, #0x70]
0x0014747c: a0 8b 00 bd              str      s0, [x29, #0x88]
0x00147480: a0 8f 00 bd              str      s0, [x29, #0x8c]
0x00147484: 1d e7 ff 97              bl       #0x1410f8                  ; -> _ZN12stfaceunlock11ImageHepler18StFormatToCvFormatERK15st_pixel_formatR15cv_pixel_format
0x00147488: a5 4f 40 f9              ldr      x5, [x29, #0x98]
0x0014748c: a6 23 02 91              add      x6, x29, #0x88
0x00147490: 80 02 40 f9              ldr      x0, [x20]
0x00147494: a7 33 02 91              add      x7, x29, #0x8c
0x00147498: 81 0e 40 b9              ldr      w1, [x20, #0xc]
0x0014749c: 82 12 40 b9              ldr      w2, [x20, #0x10]
0x001474a0: 83 16 40 b9              ldr      w3, [x20, #0x14]
0x001474a4: a4 93 40 b9              ldr      w4, [x29, #0x90]
0x001474a8: a5 18 46 f9              ldr      x5, [x5, #0xc30]
0x001474ac: bb ef ff 97              bl       #0x143398                  ; -> _ZN12stfaceunlock9CommonFun7GetMeanEPhiii15cv_pixel_formatPK14cv_landmarks_tPfS6_
0x001474b0: fa 03 00 2a              mov      w26, w0
0x001474b4: 20 03 00 34              cbz      w0, #0x147518
0x001474b8: a0 3b 40 f9              ldr      x0, [x29, #0x70]
0x001474bc: 40 f2 ff b4              cbz      x0, #0x147304
0x001474c0: e1 03 1b 2a              mov      w1, w27
0x001474c4: 9b f3 ff 97              bl       #0x144330                  ; -> _ZN12stfaceunlock17FaceDetectManager22ReleaseDetectionResultEP9st_face_ti
0x001474c8: 8f ff ff 17              b        #0x147304                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0xa0
0x001474cc: 73 9b 86 d2              mov      x19, #0x34db
0x001474d0: a5 3f 00 f9              str      x5, [x29, #0x78]
0x001474d4: d3 f6 ba f2              movk     x19, #0xd7b6, lsl #16
0x001474d8: 54 8c 17 94              bl       #0x72a628                  ; -> _ZNSt6chrono3_V212steady_clock3nowEv
0x001474dc: 53 d0 db f2              movk     x19, #0xde82, lsl #32
0x001474e0: 04 00 1a cb              sub      x4, x0, x26
0x001474e4: 73 63 e8 f2              movk     x19, #0x431b, lsl #48
0x001474e8: a5 3f 40 f9              ldr      x5, [x29, #0x78]
0x001474ec: c0 30 00 d0              adrp     x0, #0x761000
0x001474f0: 81 a3 25 91              add      x1, x28, #0x968
0x001474f4: 95 7c 53 9b              smulh    x21, x4, x19
0x001474f8: 00 40 2b 91              add      x0, x0, #0xad0
0x001474fc: 62 05 80 52              mov      w2, #0x2b
0x00147500: b4 fe 52 93              asr      x20, x21, #0x12
0x00147504: 63 63 30 91              add      x3, x27, #0xc18
0x00147508: 84 fe 84 cb              sub      x4, x20, x4, asr #63
0x0014750c: fa 03 05 2a              mov      w26, w5
0x00147510: b4 dd ff 97              bl       #0x13ebe0                  ; -> _Z4LogWPKcz
0x00147514: 7c ff ff 17              b        #0x147304                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0xa0
0x00147518: a0 8b 40 bd              ldr      s0, [x29, #0x88]
0x0014751c: e0 30 00 b0              adrp     x0, #0x764000
0x00147520: a1 8f 40 bd              ldr      s1, [x29, #0x8c]
0x00147524: 00 20 31 91              add      x0, x0, #0xc48
0x00147528: 00 c0 22 1e              fcvt     d0, s0
0x0014752c: c1 e2 12 91              add      x1, x22, #0x4b8
0x00147530: 21 c0 22 1e              fcvt     d1, s1
0x00147534: c2 3c 80 52              mov      w2, #0x1e6
0x00147538: 24 dd ff 97              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x0014753c: 95 03 00 34              cbz      w21, #0x1475ac
0x00147540: a0 8b 40 bd              ldr      s0, [x29, #0x88]
0x00147544: 01 3d 00 1c              ldr      s1, #0x147ce4
0x00147548: 10 20 21 1e              fcmpe    s0, s1
0x0014754c: 04 1e 00 54              b.mi     #0x14790c
0x00147550: a0 8f 40 bd              ldr      s0, [x29, #0x8c]
0x00147554: 01 3d 00 1c              ldr      s1, #0x147cf4
0x00147558: 10 20 21 1e              fcmpe    s0, s1
0x0014755c: 4c 1b 00 54              b.gt     #0x1478c4
0x00147560: a3 83 40 b9              ldr      w3, [x29, #0x80]
0x00147564: 7f 04 00 71              cmp      w3, #1
0x00147568: 4d 22 00 54              b.le     #0x1479b0
0x0014756c: e0 30 00 b0              adrp     x0, #0x764000
0x00147570: c1 e2 12 91              add      x1, x22, #0x4b8
0x00147574: 00 a0 33 91              add      x0, x0, #0xce8
0x00147578: c2 3f 80 52              mov      w2, #0x1fe
0x0014757c: 13 dd ff 97              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x00147580: 3a 10 80 52              mov      w26, #0x81
0x00147584: cd ff ff 17              b        #0x1474b8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x254
0x00147588: e0 30 00 b0              adrp     x0, #0x764000
0x0014758c: c1 e2 12 91              add      x1, x22, #0x4b8
0x00147590: 00 60 2f 91              add      x0, x0, #0xbd8
0x00147594: 82 33 80 52              mov      w2, #0x19c
0x00147598: e3 03 14 aa              mov      x3, x20
0x0014759c: e4 03 19 aa              mov      x4, x25
0x001475a0: 1a 00 80 12              mov      w26, #-1
0x001475a4: d2 dd ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x001475a8: 57 ff ff 17              b        #0x147304                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0xa0
0x001475ac: 61 2a 42 bd              ldr      s1, [x19, #0x228]
0x001475b0: a0 8b 40 bd              ldr      s0, [x29, #0x88]
0x001475b4: 30 20 20 1e              fcmpe    s1, s0
0x001475b8: 8c 19 00 54              b.gt     #0x1478e8
0x001475bc: a0 8f 40 bd              ldr      s0, [x29, #0x8c]
0x001475c0: a1 39 00 1c              ldr      s1, #0x147cf4
0x001475c4: 10 20 21 1e              fcmpe    s0, s1
0x001475c8: ec 17 00 54              b.gt     #0x1478c4
0x001475cc: a1 3f 40 f9              ldr      x1, [x29, #0x78]
0x001475d0: 62 1e 40 f9              ldr      x2, [x19, #0x38]
0x001475d4: a0 83 40 b9              ldr      w0, [x29, #0x80]
0x001475d8: 20 00 00 b9              str      w0, [x1]
0x001475dc: e2 1b 00 b4              cbz      x2, #0x147958
0x001475e0: 43 00 40 b9              ldr      w3, [x2]
0x001475e4: e0 30 00 b0              adrp     x0, #0x764000
0x001475e8: 44 04 40 b9              ldr      w4, [x2, #4]
0x001475ec: 00 60 35 91              add      x0, x0, #0xd58
0x001475f0: 45 08 40 b9              ldr      w5, [x2, #8]
0x001475f4: c1 e2 12 91              add      x1, x22, #0x4b8
0x001475f8: 46 0c 40 b9              ldr      w6, [x2, #0xc]
0x001475fc: a2 44 80 52              mov      w2, #0x225
0x00147600: f2 dc ff 97              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x00147604: e5 02 40 b9              ldr      w5, [x23]
0x00147608: a6 87 40 b9              ldr      w6, [x29, #0x84]
0x0014760c: bf 00 06 6b              cmp      w5, w6
0x00147610: a0 00 00 54              b.eq     #0x147624
0x00147614: bf 10 00 71              cmp      w5, #4
0x00147618: 60 19 00 54              b.eq     #0x147944
0x0014761c: bf 04 00 71              cmp      w5, #1
0x00147620: 20 19 00 54              b.eq     #0x147944
0x00147624: 80 0e 40 b9              ldr      w0, [x20, #0xc]
0x00147628: a0 7b 00 b9              str      w0, [x29, #0x78]
0x0014762c: 80 12 40 b9              ldr      w0, [x20, #0x10]
0x00147630: a0 6f 00 b9              str      w0, [x29, #0x6c]
0x00147634: e0 30 00 b0              adrp     x0, #0x764000
0x00147638: a3 7b 40 b9              ldr      w3, [x29, #0x78]
0x0014763c: a4 6f 40 b9              ldr      w4, [x29, #0x6c]
0x00147640: c1 e2 12 91              add      x1, x22, #0x4b8
0x00147644: 00 20 36 91              add      x0, x0, #0xd88
0x00147648: 22 46 80 52              mov      w2, #0x231
0x0014764c: df dc ff 97              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x00147650: a6 4f 40 f9              ldr      x6, [x29, #0x98]
0x00147654: ba 83 02 91              add      x26, x29, #0xa0
0x00147658: e0 30 00 b0              adrp     x0, #0x764000
0x0014765c: c1 e2 12 91              add      x1, x22, #0x4b8
0x00147660: 00 60 37 91              add      x0, x0, #0xdd8
0x00147664: 62 46 80 52              mov      w2, #0x233
0x00147668: c0 00 c0 3d              ldr      q0, [x6]
0x0014766c: c4 04 40 b9              ldr      w4, [x6, #4]
0x00147670: c5 08 40 b9              ldr      w5, [x6, #8]
0x00147674: c3 00 40 b9              ldr      w3, [x6]
0x00147678: c6 0c 40 b9              ldr      w6, [x6, #0xc]
0x0014767c: 40 03 80 3d              str      q0, [x26]
0x00147680: d2 dc ff 97              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x00147684: e4 02 40 b9              ldr      w4, [x23]
0x00147688: a3 87 40 b9              ldr      w3, [x29, #0x84]
0x0014768c: 9f 00 03 6b              cmp      w4, w3
0x00147690: a0 00 00 54              b.eq     #0x1476a4
0x00147694: a1 7b 40 b9              ldr      w1, [x29, #0x78]
0x00147698: e0 03 1a aa              mov      x0, x26
0x0014769c: a2 6f 40 b9              ldr      w2, [x29, #0x6c]
0x001476a0: cd e4 ff 97              bl       #0x1409d4                  ; -> _ZN12stfaceunlock11ImageHepler10RotateRectEP9st_rect_tii19st_face_orientationS3_
0x001476a4: e0 30 00 b0              adrp     x0, #0x764000
0x001476a8: a3 a3 40 b9              ldr      w3, [x29, #0xa0]
0x001476ac: a4 a7 40 b9              ldr      w4, [x29, #0xa4]
0x001476b0: 00 60 38 91              add      x0, x0, #0xe18
0x001476b4: a5 ab 40 b9              ldr      w5, [x29, #0xa8]
0x001476b8: c1 e2 12 91              add      x1, x22, #0x4b8
0x001476bc: a6 af 40 b9              ldr      w6, [x29, #0xac]
0x001476c0: 02 47 80 52              mov      w2, #0x238
0x001476c4: c1 dc ff 97              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x001476c8: 60 1e 40 f9              ldr      x0, [x19, #0x38]
0x001476cc: a1 a7 40 b9              ldr      w1, [x29, #0xa4]
0x001476d0: a2 a3 40 b9              ldr      w2, [x29, #0xa0]
0x001476d4: 05 04 40 b9              ldr      w5, [x0, #4]
0x001476d8: 04 00 40 b9              ldr      w4, [x0]
0x001476dc: bf 00 01 6b              cmp      w5, w1
0x001476e0: 8c 12 00 54              b.gt     #0x147930
0x001476e4: 9f 00 02 6b              cmp      w4, w2
0x001476e8: 4c 12 00 54              b.gt     #0x147930
0x001476ec: 06 08 40 b9              ldr      w6, [x0, #8]
0x001476f0: a9 ab 40 b9              ldr      w9, [x29, #0xa8]
0x001476f4: df 00 09 6b              cmp      w6, w9
0x001476f8: aa 1d 00 54              b.ge     #0x147aac
0x001476fc: a8 af 40 b9              ldr      w8, [x29, #0xac]
0x00147700: 07 0c 40 b9              ldr      w7, [x0, #0xc]
0x00147704: e3 02 40 b9              ldr      w3, [x23]
0x00147708: e0 30 00 b0              adrp     x0, #0x764000
0x0014770c: e2 03 00 b9              str      w2, [sp]
0x00147710: 00 60 39 91              add      x0, x0, #0xe58
0x00147714: e1 0b 00 b9              str      w1, [sp, #8]
0x00147718: c2 47 80 52              mov      w2, #0x23e
0x0014771c: c1 e2 12 91              add      x1, x22, #0x4b8
0x00147720: e9 13 00 b9              str      w9, [sp, #0x10]
0x00147724: e8 1b 00 b9              str      w8, [sp, #0x18]
0x00147728: a8 dc ff 97              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x0014772c: e0 02 40 b9              ldr      w0, [x23]
0x00147730: 00 04 00 51              sub      w0, w0, #1
0x00147734: 1f 1c 00 71              cmp      w0, #7
0x00147738: 69 00 00 54              b.ls     #0x147744
0x0014773c: 3a 0d 80 52              mov      w26, #0x69
0x00147740: 5e ff ff 17              b        #0x1474b8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x254
0x00147744: e1 30 00 90              adrp     x1, #0x763000
0x00147748: 21 80 1c 91              add      x1, x1, #0x720
0x0014774c: 20 48 60 38              ldrb     w0, [x1, w0, uxtw]
0x00147750: 61 00 00 10              adr      x1, #0x14775c
0x00147754: 20 88 20 8b              add      x0, x1, w0, sxtb #2
0x00147758: 00 00 1f d6              br       x0
0x0014775c: 60 1e 40 f9              ldr      x0, [x19, #0x38]
0x00147760: a1 a7 40 b9              ldr      w1, [x29, #0xa4]
0x00147764: 02 04 40 b9              ldr      w2, [x0, #4]
0x00147768: 5f 00 01 6b              cmp      w2, w1
0x0014776c: cc 19 00 54              b.gt     #0x147aa4
0x00147770: 02 00 40 b9              ldr      w2, [x0]
0x00147774: a1 a3 40 b9              ldr      w1, [x29, #0xa0]
0x00147778: 5f 00 01 6b              cmp      w2, w1
0x0014777c: 6c 07 00 54              b.gt     #0x147868
0x00147780: 02 08 40 b9              ldr      w2, [x0, #8]
0x00147784: a1 ab 40 b9              ldr      w1, [x29, #0xa8]
0x00147788: 5f 00 01 6b              cmp      w2, w1
0x0014778c: 0b 05 00 54              b.lt     #0x14782c
0x00147790: 01 0c 40 b9              ldr      w1, [x0, #0xc]
0x00147794: a0 af 40 b9              ldr      w0, [x29, #0xac]
0x00147798: 3f 00 00 6b              cmp      w1, w0
0x0014779c: 0b 09 00 54              b.lt     #0x1478bc
0x001477a0: d5 0d 00 34              cbz      w21, #0x147958
0x001477a4: e0 03 18 2a              mov      w0, w24
0x001477a8: 81 00 80 52              mov      w1, #4
0x001477ac: a7 eb ff 97              bl       #0x142648                  ; -> _ZN12stfaceunlock9CommonFun9CheckFlagEjj
0x001477b0: 00 1c 00 53              uxtb     w0, w0
0x001477b4: 40 15 00 35              cbnz     w0, #0x147a5c
0x001477b8: a3 4f 40 f9              ldr      x3, [x29, #0x98]
0x001477bc: e0 30 00 b0              adrp     x0, #0x764000
0x001477c0: 00 20 3b 91              add      x0, x0, #0xec8
0x001477c4: c1 e2 12 91              add      x1, x22, #0x4b8
0x001477c8: 62 4f 80 52              mov      w2, #0x27b
0x001477cc: 63 28 40 b9              ldr      w3, [x3, #0x28]
0x001477d0: c1 dc ff 97              bl       #0x13ead4                  ; -> _Z4LogIPKcz
0x001477d4: a0 4f 40 f9              ldr      x0, [x29, #0x98]
0x001477d8: 00 28 40 b9              ldr      w0, [x0, #0x28]
0x001477dc: 00 14 10 36              tbz      w0, #2, #0x147a5c
0x001477e0: 1a 0d 80 52              mov      w26, #0x68
0x001477e4: 35 ff ff 17              b        #0x1474b8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x254
0x001477e8: 60 1e 40 f9              ldr      x0, [x19, #0x38]
0x001477ec: a1 a7 40 b9              ldr      w1, [x29, #0xa4]
0x001477f0: 02 04 40 b9              ldr      w2, [x0, #4]
0x001477f4: 5f 00 01 6b              cmp      w2, w1
0x001477f8: 8c 03 00 54              b.gt     #0x147868
0x001477fc: 02 00 40 b9              ldr      w2, [x0]
0x00147800: a1 a3 40 b9              ldr      w1, [x29, #0xa0]
0x00147804: 5f 00 01 6b              cmp      w2, w1
0x00147808: ac 05 00 54              b.gt     #0x1478bc
0x0014780c: 02 08 40 b9              ldr      w2, [x0, #8]
0x00147810: a1 ab 40 b9              ldr      w1, [x29, #0xa8]
0x00147814: 5f 00 01 6b              cmp      w2, w1
0x00147818: 6b 14 00 54              b.lt     #0x147aa4
0x0014781c: 01 0c 40 b9              ldr      w1, [x0, #0xc]
0x00147820: a0 af 40 b9              ldr      w0, [x29, #0xac]
0x00147824: 3f 00 00 6b              cmp      w1, w0
0x00147828: ca fb ff 54              b.ge     #0x1477a0
0x0014782c: 9a 10 80 52              mov      w26, #0x84
0x00147830: 22 ff ff 17              b        #0x1474b8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x254
0x00147834: 60 1e 40 f9              ldr      x0, [x19, #0x38]
0x00147838: a1 a7 40 b9              ldr      w1, [x29, #0xa4]
0x0014783c: 02 04 40 b9              ldr      w2, [x0, #4]
0x00147840: 5f 00 01 6b              cmp      w2, w1
0x00147844: 0c 13 00 54              b.gt     #0x147aa4
0x00147848: 02 00 40 b9              ldr      w2, [x0]
0x0014784c: a1 a3 40 b9              ldr      w1, [x29, #0xa0]
0x00147850: 5f 00 01 6b              cmp      w2, w1
0x00147854: cc fe ff 54              b.gt     #0x14782c
0x00147858: 02 08 40 b9              ldr      w2, [x0, #8]
0x0014785c: a1 ab 40 b9              ldr      w1, [x29, #0xa8]
0x00147860: 5f 00 01 6b              cmp      w2, w1
0x00147864: 6a f9 ff 54              b.ge     #0x147790
0x00147868: da 10 80 52              mov      w26, #0x86
0x0014786c: 13 ff ff 17              b        #0x1474b8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x254
0x00147870: 60 1e 40 f9              ldr      x0, [x19, #0x38]
0x00147874: a1 a7 40 b9              ldr      w1, [x29, #0xa4]
0x00147878: 02 04 40 b9              ldr      w2, [x0, #4]
0x0014787c: 5f 00 01 6b              cmp      w2, w1
0x00147880: 6c fd ff 54              b.gt     #0x14782c
0x00147884: 02 00 40 b9              ldr      w2, [x0]
0x00147888: a1 a3 40 b9              ldr      w1, [x29, #0xa0]
0x0014788c: 5f 00 01 6b              cmp      w2, w1
0x00147890: 6c 01 00 54              b.gt     #0x1478bc
0x00147894: 02 08 40 b9              ldr      w2, [x0, #8]
0x00147898: a1 ab 40 b9              ldr      w1, [x29, #0xa8]
0x0014789c: 5f 00 01 6b              cmp      w2, w1
0x001478a0: 2b 10 00 54              b.lt     #0x147aa4
0x001478a4: 01 0c 40 b9              ldr      w1, [x0, #0xc]
0x001478a8: a0 af 40 b9              ldr      w0, [x29, #0xac]
0x001478ac: 3f 00 00 6b              cmp      w1, w0
0x001478b0: 8a f7 ff 54              b.ge     #0x1477a0
0x001478b4: da 10 80 52              mov      w26, #0x86
0x001478b8: 00 ff ff 17              b        #0x1474b8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x254
0x001478bc: 7a 10 80 52              mov      w26, #0x83
0x001478c0: fe fe ff 17              b        #0x1474b8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x254
0x001478c4: 21 21 00 5c              ldr      d1, #0x147ce8
0x001478c8: e0 30 00 b0              adrp     x0, #0x764000
0x001478cc: 00 c0 22 1e              fcvt     d0, s0
0x001478d0: 00 00 33 91              add      x0, x0, #0xcc0
0x001478d4: c1 e2 12 91              add      x1, x22, #0x4b8
0x001478d8: e2 3e 80 52              mov      w2, #0x1f7
0x001478dc: 04 dd ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x001478e0: fa 00 80 52              mov      w26, #7
0x001478e4: f5 fe ff 17              b        #0x1474b8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x254
0x001478e8: 00 c0 22 1e              fcvt     d0, s0
0x001478ec: e0 30 00 b0              adrp     x0, #0x764000
0x001478f0: 21 c0 22 1e              fcvt     d1, s1
0x001478f4: 00 40 32 91              add      x0, x0, #0xc90
0x001478f8: c1 e2 12 91              add      x1, x22, #0x4b8
0x001478fc: 22 3e 80 52              mov      w2, #0x1f1
0x00147900: fb dc ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x00147904: 7a 0f 80 52              mov      w26, #0x7b
0x00147908: ec fe ff 17              b        #0x1474b8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x254
0x0014790c: 00 c0 22 1e              fcvt     d0, s0
0x00147910: e0 30 00 b0              adrp     x0, #0x764000
0x00147914: 00 a0 31 91              add      x0, x0, #0xc68
0x00147918: c1 e2 12 91              add      x1, x22, #0x4b8
0x0014791c: 42 3d 80 52              mov      w2, #0x1ea
0x00147920: 63 05 80 52              mov      w3, #0x2b
0x00147924: f2 dc ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x00147928: 7a 0f 80 52              mov      w26, #0x7b
0x0014792c: e3 fe ff 17              b        #0x1474b8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x254
0x00147930: a9 ab 40 b9              ldr      w9, [x29, #0xa8]
0x00147934: a8 af 40 b9              ldr      w8, [x29, #0xac]
0x00147938: 06 08 40 b9              ldr      w6, [x0, #8]
0x0014793c: 07 0c 40 b9              ldr      w7, [x0, #0xc]
0x00147940: 71 ff ff 17              b        #0x147704                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x4a0
0x00147944: 80 12 40 b9              ldr      w0, [x20, #0x10]
0x00147948: a0 7b 00 b9              str      w0, [x29, #0x78]
0x0014794c: 80 0e 40 b9              ldr      w0, [x20, #0xc]
0x00147950: a0 6f 00 b9              str      w0, [x29, #0x6c]
0x00147954: 38 ff ff 17              b        #0x147634                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x3d0
0x00147958: e0 03 18 2a              mov      w0, w24
0x0014795c: 01 04 80 52              mov      w1, #0x20
0x00147960: 3a eb ff 97              bl       #0x142648                  ; -> _ZN12stfaceunlock9CommonFun9CheckFlagEjj
0x00147964: 00 1c 00 53              uxtb     w0, w0
0x00147968: c0 00 00 35              cbnz     w0, #0x147980
0x0014796c: b5 0a 00 35              cbnz     w21, #0x147ac0
0x00147970: a0 3b 40 f9              ldr      x0, [x29, #0x70]
0x00147974: 1a 00 80 52              mov      w26, #0
0x00147978: 20 03 00 f9              str      x0, [x25]
0x0014797c: 62 fe ff 17              b        #0x147304                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0xa0
0x00147980: a3 4f 40 f9              ldr      x3, [x29, #0x98]
0x00147984: e0 30 00 b0              adrp     x0, #0x764000
0x00147988: 00 20 3b 91              add      x0, x0, #0xec8
0x0014798c: c1 e2 12 91              add      x1, x22, #0x4b8
0x00147990: a2 51 80 52              mov      w2, #0x28d
0x00147994: 63 28 40 b9              ldr      w3, [x3, #0x28]
0x00147998: 4f dc ff 97              bl       #0x13ead4                  ; -> _Z4LogIPKcz
0x0014799c: a0 4f 40 f9              ldr      x0, [x29, #0x98]
0x001479a0: 00 28 40 b9              ldr      w0, [x0, #0x28]
0x001479a4: 40 fe 67 36              tbz      w0, #0xc, #0x14796c
0x001479a8: 5a 10 80 52              mov      w26, #0x82
0x001479ac: c3 fe ff 17              b        #0x1474b8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x254
0x001479b0: a5 4f 40 f9              ldr      x5, [x29, #0x98]
0x001479b4: e0 30 00 b0              adrp     x0, #0x764000
0x001479b8: 00 80 34 91              add      x0, x0, #0xd20
0x001479bc: c1 e2 12 91              add      x1, x22, #0x4b8
0x001479c0: a2 40 80 52              mov      w2, #0x205
0x001479c4: a7 08 40 b9              ldr      w7, [x5, #8]
0x001479c8: a3 00 40 b9              ldr      w3, [x5]
0x001479cc: a6 0c 40 b9              ldr      w6, [x5, #0xc]
0x001479d0: a4 04 40 b9              ldr      w4, [x5, #4]
0x001479d4: e3 00 03 4b              sub      w3, w7, w3
0x001479d8: c4 00 04 4b              sub      w4, w6, w4
0x001479dc: fb db ff 97              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x001479e0: a0 4f 40 f9              ldr      x0, [x29, #0x98]
0x001479e4: 04 0c 40 b9              ldr      w4, [x0, #0xc]
0x001479e8: 05 04 40 b9              ldr      w5, [x0, #4]
0x001479ec: 81 00 05 4b              sub      w1, w4, w5
0x001479f0: 3f 08 07 71              cmp      w1, #0x1c2
0x001479f4: 0c 03 00 54              b.gt     #0x147a54
0x001479f8: 02 08 40 b9              ldr      w2, [x0, #8]
0x001479fc: 03 00 40 b9              ldr      w3, [x0]
0x00147a00: 40 00 03 4b              sub      w0, w2, w3
0x00147a04: 1f 08 07 71              cmp      w0, #0x1c2
0x00147a08: 6c 02 00 54              b.gt     #0x147a54
0x00147a0c: a5 0b f8 37              tbnz     w5, #0x1f, #0x147b80
0x00147a10: 85 12 40 b9              ldr      w5, [x20, #0x10]
0x00147a14: 9f 00 05 6b              cmp      w4, w5
0x00147a18: 4c 0b 00 54              b.gt     #0x147b80
0x00147a1c: 23 0b f8 37              tbnz     w3, #0x1f, #0x147b80
0x00147a20: 83 0e 40 b9              ldr      w3, [x20, #0xc]
0x00147a24: 5f 00 03 6b              cmp      w2, w3
0x00147a28: cc 0a 00 54              b.gt     #0x147b80
0x00147a2c: 3f 14 01 71              cmp      w1, #0x45
0x00147a30: 0d 12 00 54              b.le     #0x147c70
0x00147a34: 1f 14 01 71              cmp      w0, #0x45
0x00147a38: cd 11 00 54              b.le     #0x147c70
0x00147a3c: a1 3f 40 f9              ldr      x1, [x29, #0x78]
0x00147a40: 62 1e 40 f9              ldr      x2, [x19, #0x38]
0x00147a44: a0 83 40 b9              ldr      w0, [x29, #0x80]
0x00147a48: 20 00 00 b9              str      w0, [x1]
0x00147a4c: a2 dc ff b5              cbnz     x2, #0x1475e0
0x00147a50: 55 ff ff 17              b        #0x1477a4                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x540
0x00147a54: 3a 0f 80 52              mov      w26, #0x79
0x00147a58: 98 fe ff 17              b        #0x1474b8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x254
0x00147a5c: e0 03 18 2a              mov      w0, w24
0x00147a60: 01 02 80 52              mov      w1, #0x10
0x00147a64: f9 ea ff 97              bl       #0x142648                  ; -> _ZN12stfaceunlock9CommonFun9CheckFlagEjj
0x00147a68: 00 1c 00 53              uxtb     w0, w0
0x00147a6c: 60 f7 ff 35              cbnz     w0, #0x147958
0x00147a70: a3 4f 40 f9              ldr      x3, [x29, #0x98]
0x00147a74: e0 30 00 b0              adrp     x0, #0x764000
0x00147a78: 00 20 3b 91              add      x0, x0, #0xec8
0x00147a7c: c1 e2 12 91              add      x1, x22, #0x4b8
0x00147a80: 62 50 80 52              mov      w2, #0x283
0x00147a84: 63 28 40 b9              ldr      w3, [x3, #0x28]
0x00147a88: 13 dc ff 97              bl       #0x13ead4                  ; -> _Z4LogIPKcz
0x00147a8c: a0 4f 40 f9              ldr      x0, [x29, #0x98]
0x00147a90: 00 28 40 b9              ldr      w0, [x0, #0x28]
0x00147a94: 00 04 00 12              and      w0, w0, #3
0x00147a98: 00 f6 ff 34              cbz      w0, #0x147958
0x00147a9c: fa 0c 80 52              mov      w26, #0x67
0x00147aa0: 86 fe ff 17              b        #0x1474b8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x254
0x00147aa4: ba 10 80 52              mov      w26, #0x85
0x00147aa8: 84 fe ff 17              b        #0x1474b8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x254
0x00147aac: 07 0c 40 b9              ldr      w7, [x0, #0xc]
0x00147ab0: a8 af 40 b9              ldr      w8, [x29, #0xac]
0x00147ab4: ff 00 08 6b              cmp      w7, w8
0x00147ab8: 6b e2 ff 54              b.lt     #0x147704
0x00147abc: 39 ff ff 17              b        #0x1477a0                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x53c
0x00147ac0: f7 30 00 b0              adrp     x23, #0x764000
0x00147ac4: c0 30 00 d0              adrp     x0, #0x761000
0x00147ac8: 00 40 25 91              add      x0, x0, #0x950
0x00147acc: 81 a3 25 91              add      x1, x28, #0x968
0x00147ad0: 82 04 80 52              mov      w2, #0x24
0x00147ad4: e3 82 3b 91              add      x3, x23, #0xee0
0x00147ad8: 42 dc ff 97              bl       #0x13ebe0                  ; -> _Z4LogWPKcz
0x00147adc: d3 8a 17 94              bl       #0x72a628                  ; -> _ZNSt6chrono3_V212steady_clock3nowEv
0x00147ae0: a0 53 00 f9              str      x0, [x29, #0xa0]
0x00147ae4: e0 03 27 1e              fmov     s0, wzr
0x00147ae8: f5 03 00 aa              mov      x21, x0
0x00147aec: a2 4f 40 f9              ldr      x2, [x29, #0x98]
0x00147af0: a3 03 03 91              add      x3, x29, #0xc0
0x00147af4: 60 1a 40 f9              ldr      x0, [x19, #0x30]
0x00147af8: e1 03 14 aa              mov      x1, x20
0x00147afc: 60 4c 1d bc              str      s0, [x3, #-0x2c]!
0x00147b00: 26 d3 00 94              bl       #0x17c798                  ; -> _ZN12stfaceunlock20FaceAttributeQuality12GetAttributeEP10st_image_tP9st_face_tPf
0x00147b04: 20 04 00 35              cbnz     w0, #0x147b88
0x00147b08: a0 97 40 bd              ldr      s0, [x29, #0x94]
0x00147b0c: e0 30 00 b0              adrp     x0, #0x764000
0x00147b10: 00 e0 3b 91              add      x0, x0, #0xef8
0x00147b14: c1 e2 12 91              add      x1, x22, #0x4b8
0x00147b18: 00 c0 22 1e              fcvt     d0, s0
0x00147b1c: 22 5a 80 52              mov      w2, #0x2d1
0x00147b20: ed db ff 97              bl       #0x13ead4                  ; -> _Z4LogIPKcz
0x00147b24: a0 97 40 bd              ldr      s0, [x29, #0x94]
0x00147b28: 41 0e 00 1c              ldr      s1, #0x147cf0
0x00147b2c: 10 20 21 1e              fcmpe    s0, s1
0x00147b30: 05 03 00 54              b.pl     #0x147b90
0x00147b34: 00 c0 22 1e              fcvt     d0, s0
0x00147b38: e0 30 00 b0              adrp     x0, #0x764000
0x00147b3c: 00 60 3c 91              add      x0, x0, #0xf18
0x00147b40: c1 e2 12 91              add      x1, x22, #0x4b8
0x00147b44: 62 5a 80 52              mov      w2, #0x2d3
0x00147b48: 69 dc ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x00147b4c: da 0c 80 52              mov      w26, #0x66
0x00147b50: 13 48 88 d2              mov      x19, #0x4240
0x00147b54: b5 8a 17 94              bl       #0x72a628                  ; -> _ZNSt6chrono3_V212steady_clock3nowEv
0x00147b58: f3 01 a0 f2              movk     x19, #0xf, lsl #16
0x00147b5c: 04 00 15 cb              sub      x4, x0, x21
0x00147b60: c0 30 00 d0              adrp     x0, #0x761000
0x00147b64: 81 a3 25 91              add      x1, x28, #0x968
0x00147b68: 84 0c d3 9a              sdiv     x4, x4, x19
0x00147b6c: 00 40 2b 91              add      x0, x0, #0xad0
0x00147b70: 62 05 80 52              mov      w2, #0x2b
0x00147b74: e3 82 3b 91              add      x3, x23, #0xee0
0x00147b78: 1a dc ff 97              bl       #0x13ebe0                  ; -> _Z4LogWPKcz
0x00147b7c: 4f fe ff 17              b        #0x1474b8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x254
0x00147b80: fa 0f 80 52              mov      w26, #0x7f
0x00147b84: 4d fe ff 17              b        #0x1474b8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x254
0x00147b88: fa 03 00 2a              mov      w26, w0
0x00147b8c: f1 ff ff 17              b        #0x147b50                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x8ec
0x00147b90: 16 48 88 d2              mov      x22, #0x4240
0x00147b94: a5 8a 17 94              bl       #0x72a628                  ; -> _ZNSt6chrono3_V212steady_clock3nowEv
0x00147b98: f6 01 a0 f2              movk     x22, #0xf, lsl #16
0x00147b9c: 04 00 15 cb              sub      x4, x0, x21
0x00147ba0: c0 30 00 d0              adrp     x0, #0x761000
0x00147ba4: 81 a3 25 91              add      x1, x28, #0x968
0x00147ba8: 84 0c d6 9a              sdiv     x4, x4, x22
0x00147bac: 00 40 2b 91              add      x0, x0, #0xad0
0x00147bb0: 62 05 80 52              mov      w2, #0x2b
0x00147bb4: e3 82 3b 91              add      x3, x23, #0xee0
0x00147bb8: 0a dc ff 97              bl       #0x13ebe0                  ; -> _Z4LogWPKcz
0x00147bbc: 60 42 41 f9              ldr      x0, [x19, #0x280]
0x00147bc0: c0 05 00 b4              cbz      x0, #0x147c78
0x00147bc4: 02 00 40 f9              ldr      x2, [x0]
0x00147bc8: e1 03 14 aa              mov      x1, x20
0x00147bcc: 43 0c 40 f9              ldr      x3, [x2, #0x18]
0x00147bd0: a2 4f 40 f9              ldr      x2, [x29, #0x98]
0x00147bd4: 42 18 46 f9              ldr      x2, [x2, #0xc30]
0x00147bd8: 60 00 3f d6              blr      x3
0x00147bdc: a0 ec ff 34              cbz      w0, #0x147970
0x00147be0: fa 03 00 2a              mov      w26, w0
0x00147be4: 35 fe ff 17              b        #0x1474b8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x254
0x00147be8: f3 03 00 aa              mov      x19, x0
0x00147bec: 8f 8a 17 94              bl       #0x72a628                  ; -> _ZNSt6chrono3_V212steady_clock3nowEv
0x00147bf0: 04 48 88 d2              mov      x4, #0x4240
0x00147bf4: 15 00 15 cb              sub      x21, x0, x21
0x00147bf8: e4 01 a0 f2              movk     x4, #0xf, lsl #16
0x00147bfc: c0 30 00 d0              adrp     x0, #0x761000
0x00147c00: 00 40 2b 91              add      x0, x0, #0xad0
0x00147c04: 81 a3 25 91              add      x1, x28, #0x968
0x00147c08: a4 0e c4 9a              sdiv     x4, x21, x4
0x00147c0c: 62 05 80 52              mov      w2, #0x2b
0x00147c10: e3 82 3b 91              add      x3, x23, #0xee0
0x00147c14: f3 db ff 97              bl       #0x13ebe0                  ; -> _Z4LogWPKcz
0x00147c18: a0 3b 40 f9              ldr      x0, [x29, #0x70]
0x00147c1c: 60 00 00 b4              cbz      x0, #0x147c28
0x00147c20: e1 03 1b 2a              mov      w1, w27
0x00147c24: c3 f1 ff 97              bl       #0x144330                  ; -> _ZN12stfaceunlock17FaceDetectManager22ReleaseDetectionResultEP9st_face_ti
0x00147c28: e0 03 13 aa              mov      x0, x19
0x00147c2c: a2 21 18 94              bl       #0x7502b4                  ; -> _Unwind_Resume
0x00147c30: f3 03 00 aa              mov      x19, x0
0x00147c34: f9 ff ff 17              b        #0x147c18                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x9b4
0x00147c38: f3 03 00 aa              mov      x19, x0
0x00147c3c: 7b 8a 17 94              bl       #0x72a628                  ; -> _ZNSt6chrono3_V212steady_clock3nowEv
0x00147c40: 04 48 88 d2              mov      x4, #0x4240
0x00147c44: 1a 00 1a cb              sub      x26, x0, x26
0x00147c48: e4 01 a0 f2              movk     x4, #0xf, lsl #16
0x00147c4c: c0 30 00 d0              adrp     x0, #0x761000
0x00147c50: 00 40 2b 91              add      x0, x0, #0xad0
0x00147c54: 81 a3 25 91              add      x1, x28, #0x968
0x00147c58: 44 0f c4 9a              sdiv     x4, x26, x4
0x00147c5c: 62 05 80 52              mov      w2, #0x2b
0x00147c60: 63 63 30 91              add      x3, x27, #0xc18
0x00147c64: df db ff 97              bl       #0x13ebe0                  ; -> _Z4LogWPKcz
0x00147c68: e0 03 13 aa              mov      x0, x19
0x00147c6c: 92 21 18 94              bl       #0x7502b4                  ; -> _Unwind_Resume
0x00147c70: 1a 0f 80 52              mov      w26, #0x78
0x00147c74: 11 fe ff 17              b        #0x1474b8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x254
0x00147c78: 16 6f 02 f0              adrp     x22, #0x4f2a000
0x00147c7c: 00 06 80 d2              mov      x0, #0x30
0x00147c80: c1 6e 46 f9              ldr      x1, [x22, #0xcd8]
0x00147c84: f8 8b 16 94              bl       #0x6eac64                  ; -> _ZnwmRKSt9nothrow_t
0x00147c88: f5 03 00 aa              mov      x21, x0
0x00147c8c: e0 01 00 b4              cbz      x0, #0x147cc8
0x00147c90: 28 2c 00 94              bl       #0x152d30                  ; -> _ZN12stfaceunlock7QualityC2Ev
0x00147c94: e0 03 13 aa              mov      x0, x19
0x00147c98: e1 03 15 aa              mov      x1, x21
0x00147c9c: 39 fd ff 97              bl       #0x147180                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace10SetQualityEPNS_7QualityE
0x00147ca0: 61 42 41 f9              ldr      x1, [x19, #0x280]
0x00147ca4: e0 03 15 aa              mov      x0, x21
0x00147ca8: 75 42 01 f9              str      x21, [x19, #0x280]
0x00147cac: c1 f8 ff b4              cbz      x1, #0x147bc4
0x00147cb0: 22 00 40 f9              ldr      x2, [x1]
0x00147cb4: e0 03 01 aa              mov      x0, x1
0x00147cb8: 41 04 40 f9              ldr      x1, [x2, #8]
0x00147cbc: 20 00 3f d6              blr      x1
0x00147cc0: 60 42 41 f9              ldr      x0, [x19, #0x280]
0x00147cc4: c0 ff ff 17              b        #0x147bc4                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x960
0x00147cc8: 5a 00 80 12              mov      w26, #-3
0x00147ccc: fb fd ff 17              b        #0x1474b8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x254
0x00147cd0: c1 6e 46 f9              ldr      x1, [x22, #0xcd8]
0x00147cd4: f3 03 00 aa              mov      x19, x0
0x00147cd8: e0 03 15 aa              mov      x0, x21
0x00147cdc: 4a 85 16 94              bl       #0x6e9204                  ; -> _ZdlPvRKSt9nothrow_t
0x00147ce0: ce ff ff 17              b        #0x147c18                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib+0x9b4
