; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t (0x1496ec)
; start   : 0x1496ec
; size    : 0x118c
; bytes   : 0x118c   instructions: 1123
; generated: 2026-10-24T00:33:48

0x001496ec: fd 7b a3 a9              stp      x29, x30, [sp, #-0x1d0]!
0x001496f0: fd 03 00 91              mov      x29, sp
0x001496f4: f3 53 01 a9              stp      x19, x20, [sp, #0x10]
0x001496f8: f7 63 03 a9              stp      x23, x24, [sp, #0x30]
0x001496fc: f5 5b 02 a9              stp      x21, x22, [sp, #0x20]
0x00149700: d7 30 00 90              adrp     x23, #0x761000
0x00149704: f6 30 00 90              adrp     x22, #0x765000
0x00149708: f3 03 00 aa              mov      x19, x0
0x0014970c: c0 30 00 90              adrp     x0, #0x761000
0x00149710: f9 6b 04 a9              stp      x25, x26, [sp, #0x40]
0x00149714: fb 73 05 a9              stp      x27, x28, [sp, #0x50]
0x00149718: 00 40 25 91              add      x0, x0, #0x950
0x0014971c: f8 03 01 aa              mov      x24, x1
0x00149720: fb 03 02 2a              mov      w27, w2
0x00149724: e1 a2 25 91              add      x1, x23, #0x968
0x00149728: 82 04 80 52              mov      w2, #0x24
0x0014972c: fa 03 03 aa              mov      x26, x3
0x00149730: c3 42 09 91              add      x3, x22, #0x250
0x00149734: fc 03 04 aa              mov      x28, x4
0x00149738: d9 30 00 f0              adrp     x25, #0x764000
0x0014973c: 29 d5 ff 97              bl       #0x13ebe0                  ; -> _Z4LogWPKcz
0x00149740: ba 83 17 94              bl       #0x72a628                  ; -> _ZNSt6chrono3_V212steady_clock3nowEv
0x00149744: a0 7b 00 f9              str      x0, [x29, #0xf0]
0x00149748: f4 03 00 aa              mov      x20, x0
0x0014974c: e0 30 00 90              adrp     x0, #0x765000
0x00149750: 21 e3 12 91              add      x1, x25, #0x4b8
0x00149754: 00 60 09 91              add      x0, x0, #0x258
0x00149758: 22 6a 80 52              mov      w2, #0x351
0x0014975c: 9b d4 ff 97              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x00149760: 60 02 40 f9              ldr      x0, [x19]
0x00149764: c0 05 00 b4              cbz      x0, #0x14981c
0x00149768: 60 06 40 f9              ldr      x0, [x19, #8]
0x0014976c: 80 05 00 b4              cbz      x0, #0x14981c
0x00149770: 60 0e 40 f9              ldr      x0, [x19, #0x18]
0x00149774: 40 05 00 b4              cbz      x0, #0x14981c
0x00149778: 60 12 40 f9              ldr      x0, [x19, #0x20]
0x0014977c: 00 05 00 b4              cbz      x0, #0x14981c
0x00149780: d8 05 00 b4              cbz      x24, #0x149838
0x00149784: ba 05 00 b4              cbz      x26, #0x149838
0x00149788: 00 03 40 f9              ldr      x0, [x24]
0x0014978c: 80 06 00 b4              cbz      x0, #0x14985c
0x00149790: e0 03 13 aa              mov      x0, x19
0x00149794: e1 03 18 aa              mov      x1, x24
0x00149798: 62 62 01 91              add      x2, x19, #0x58
0x0014979c: e3 03 1b 2a              mov      w3, w27
0x001497a0: a4 83 03 91              add      x4, x29, #0xe0
0x001497a4: a5 43 03 91              add      x5, x29, #0xd0
0x001497a8: 26 00 80 52              mov      w6, #1
0x001497ac: bf 73 00 f9              str      xzr, [x29, #0xe0]
0x001497b0: bf d3 00 b9              str      wzr, [x29, #0xd0]
0x001497b4: ac f6 ff 97              bl       #0x147264                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13CheckFaceInfoEP10st_image_tR19st_face_orientationjPP9st_face_tRib
0x001497b8: f5 03 00 2a              mov      w21, w0
0x001497bc: e0 05 00 34              cbz      w0, #0x149878
0x001497c0: 73 9b 86 d2              mov      x19, #0x34db
0x001497c4: 99 83 17 94              bl       #0x72a628                  ; -> _ZNSt6chrono3_V212steady_clock3nowEv
0x001497c8: d3 f6 ba f2              movk     x19, #0xd7b6, lsl #16
0x001497cc: 04 00 14 cb              sub      x4, x0, x20
0x001497d0: 53 d0 db f2              movk     x19, #0xde82, lsl #32
0x001497d4: c0 30 00 90              adrp     x0, #0x761000
0x001497d8: 73 63 e8 f2              movk     x19, #0x431b, lsl #48
0x001497dc: e1 a2 25 91              add      x1, x23, #0x968
0x001497e0: c3 42 09 91              add      x3, x22, #0x250
0x001497e4: 00 40 2b 91              add      x0, x0, #0xad0
0x001497e8: 93 7c 53 9b              smulh    x19, x4, x19
0x001497ec: 62 05 80 52              mov      w2, #0x2b
0x001497f0: 73 fe 52 93              asr      x19, x19, #0x12
0x001497f4: 64 fe 84 cb              sub      x4, x19, x4, asr #63
0x001497f8: fa d4 ff 97              bl       #0x13ebe0                  ; -> _Z4LogWPKcz
0x001497fc: e0 03 15 2a              mov      w0, w21
0x00149800: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x00149804: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x00149808: f7 63 43 a9              ldp      x23, x24, [sp, #0x30]
0x0014980c: f9 6b 44 a9              ldp      x25, x26, [sp, #0x40]
0x00149810: fb 73 45 a9              ldp      x27, x28, [sp, #0x50]
0x00149814: fd 7b dd a8              ldp      x29, x30, [sp], #0x1d0
0x00149818: c0 03 5f d6              ret      
0x0014981c: c0 30 00 f0              adrp     x0, #0x764000
0x00149820: 21 e3 12 91              add      x1, x25, #0x4b8
0x00149824: 00 40 3d 91              add      x0, x0, #0xf50
0x00149828: 62 6a 80 52              mov      w2, #0x353
0x0014982c: 30 d5 ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x00149830: 75 00 80 12              mov      w21, #-4
0x00149834: e3 ff ff 17              b        #0x1497c0                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xd4
0x00149838: e0 30 00 90              adrp     x0, #0x765000
0x0014983c: 21 e3 12 91              add      x1, x25, #0x4b8
0x00149840: 00 20 0a 91              add      x0, x0, #0x288
0x00149844: 02 6b 80 52              mov      w2, #0x358
0x00149848: e3 03 18 aa              mov      x3, x24
0x0014984c: e4 03 1a aa              mov      x4, x26
0x00149850: 27 d5 ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x00149854: 15 00 80 12              mov      w21, #-1
0x00149858: da ff ff 17              b        #0x1497c0                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xd4
0x0014985c: e0 30 00 90              adrp     x0, #0x765000
0x00149860: 21 e3 12 91              add      x1, x25, #0x4b8
0x00149864: 00 40 0b 91              add      x0, x0, #0x2d0
0x00149868: 82 6b 80 52              mov      w2, #0x35c
0x0014986c: 20 d5 ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x00149870: 15 00 80 12              mov      w21, #-1
0x00149874: d3 ff ff 17              b        #0x1497c0                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xd4
0x00149878: e0 03 27 1e              fmov     s0, wzr
0x0014987c: e0 03 1b 2a              mov      w0, w27
0x00149880: 01 01 80 52              mov      w1, #8
0x00149884: a0 d7 00 bd              str      s0, [x29, #0xd4]
0x00149888: 70 e3 ff 97              bl       #0x142648                  ; -> _ZN12stfaceunlock9CommonFun9CheckFlagEjj
0x0014988c: 00 1c 00 53              uxtb     w0, w0
0x00149890: a0 10 00 34              cbz      w0, #0x149aa4
0x00149894: a0 73 40 f9              ldr      x0, [x29, #0xe0]
0x00149898: 75 0e 80 52              mov      w21, #0x73
0x0014989c: 01 90 36 1e              fmov     s1, #-20.00000000
0x001498a0: a0 67 00 f9              str      x0, [x29, #0xc8]
0x001498a4: 00 18 40 bd              ldr      s0, [x0, #0x18]
0x001498a8: 10 20 21 1e              fcmpe    s0, s1
0x001498ac: 25 05 00 54              b.pl     #0x149950
0x001498b0: 7b 02 05 91              add      x27, x19, #0x140
0x001498b4: 60 17 40 b9              ldr      w0, [x27, #0x14]
0x001498b8: 40 f8 ff 34              cbz      w0, #0x1497c0
0x001498bc: 60 a2 40 f9              ldr      x0, [x19, #0x140]
0x001498c0: a0 83 00 f9              str      x0, [x29, #0x100]
0x001498c4: bf 23 04 39              strb     wzr, [x29, #0x108]
0x001498c8: e0 76 00 b4              cbz      x0, #0x14a7a4
0x001498cc: 01 6f 02 90              adrp     x1, #0x4f29000
0x001498d0: a1 57 00 f9              str      x1, [x29, #0xa8]
0x001498d4: 21 94 47 f9              ldr      x1, [x1, #0xf28]           ; -> pthread_create@got
0x001498d8: 81 00 00 b4              cbz      x1, #0x1498e8
0x001498dc: f9 fc fc 97              bl       #0x88cc0                   ; -> pthread_mutex_lock@plt
0x001498e0: 20 7b 00 35              cbnz     w0, #0x14a844
0x001498e4: 60 a2 40 f9              ldr      x0, [x19, #0x140]
0x001498e8: 18 a0 00 91              add      x24, x0, #0x28
0x001498ec: 79 42 05 91              add      x25, x19, #0x150
0x001498f0: 20 00 80 52              mov      w0, #1
0x001498f4: ba 03 04 91              add      x26, x29, #0x100
0x001498f8: a0 23 04 39              strb     w0, [x29, #0x108]
0x001498fc: 04 00 00 14              b        #0x14990c                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x220
0x00149900: e0 03 18 aa              mov      x0, x24
0x00149904: e1 03 1a aa              mov      x1, x26
0x00149908: 5a 83 17 94              bl       #0x72a670                  ; -> _ZNSt18condition_variable4waitERSt11unique_lockISt5mutexE
0x0014990c: 20 ff df 08              ldarb    w0, [x25]
0x00149910: 00 1c 00 53              uxtb     w0, w0
0x00149914: 60 ff ff 34              cbz      w0, #0x149900
0x00149918: 60 42 05 91              add      x0, x19, #0x150
0x0014991c: 1f fc 9f 08              stlrb    wzr, [x0]
0x00149920: 60 17 40 b9              ldr      w0, [x27, #0x14]
0x00149924: a1 23 44 39              ldrb     w1, [x29, #0x108]
0x00149928: 00 04 00 51              sub      w0, w0, #1
0x0014992c: 60 17 00 b9              str      w0, [x27, #0x14]
0x00149930: 81 f4 ff 34              cbz      w1, #0x1497c0
0x00149934: a0 83 40 f9              ldr      x0, [x29, #0x100]
0x00149938: 40 f4 ff b4              cbz      x0, #0x1497c0
0x0014993c: a1 57 40 f9              ldr      x1, [x29, #0xa8]
0x00149940: 21 94 47 f9              ldr      x1, [x1, #0xf28]           ; -> pthread_create@got
0x00149944: e1 f3 ff b4              cbz      x1, #0x1497c0
0x00149948: b6 fb fc 97              bl       #0x88820                   ; -> pthread_mutex_unlock@plt
0x0014994c: 9d ff ff 17              b        #0x1497c0                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xd4
0x00149950: 02 90 26 1e              fmov     s2, #20.00000000
0x00149954: 95 0e 80 52              mov      w21, #0x74
0x00149958: 10 20 22 1e              fcmpe    s0, s2
0x0014995c: ac fa ff 54              b.gt     #0x1498b0
0x00149960: 00 1c 40 bd              ldr      s0, [x0, #0x1c]
0x00149964: 55 0e 80 52              mov      w21, #0x72
0x00149968: 10 20 22 1e              fcmpe    s0, s2
0x0014996c: 2c fa ff 54              b.gt     #0x1498b0
0x00149970: 10 20 21 1e              fcmpe    s0, s1
0x00149974: 35 0e 80 52              mov      w21, #0x71
0x00149978: c4 f9 ff 54              b.mi     #0x1498b0
0x0014997c: a0 67 40 f9              ldr      x0, [x29, #0xc8]
0x00149980: a1 d3 40 b9              ldr      w1, [x29, #0xd0]
0x00149984: a1 c3 00 b9              str      w1, [x29, #0xc0]
0x00149988: 00 28 40 b9              ldr      w0, [x0, #0x28]
0x0014998c: 00 08 00 52              eor      w0, w0, #7
0x00149990: c0 02 00 34              cbz      w0, #0x1499e8
0x00149994: b5 0e 80 52              mov      w21, #0x75
0x00149998: 00 01 18 36              tbz      w0, #3, #0x1499b8
0x0014999c: 7b 02 05 91              add      x27, x19, #0x140
0x001499a0: 60 17 40 b9              ldr      w0, [x27, #0x14]
0x001499a4: 80 18 00 35              cbnz     w0, #0x149cb4
0x001499a8: a0 67 40 f9              ldr      x0, [x29, #0xc8]
0x001499ac: a1 c3 40 b9              ldr      w1, [x29, #0xc0]
0x001499b0: 60 ea ff 97              bl       #0x144330                  ; -> _ZN12stfaceunlock17FaceDetectManager22ReleaseDetectionResultEP9st_face_ti
0x001499b4: 83 ff ff 17              b        #0x1497c0                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xd4
0x001499b8: d5 0e 80 52              mov      w21, #0x76
0x001499bc: 00 ff 27 37              tbnz     w0, #4, #0x14999c
0x001499c0: f5 0e 80 52              mov      w21, #0x77
0x001499c4: c0 fe 2f 37              tbnz     w0, #5, #0x14999c
0x001499c8: 15 0f 80 52              mov      w21, #0x78
0x001499cc: 80 fe 37 37              tbnz     w0, #6, #0x14999c
0x001499d0: 35 0f 80 52              mov      w21, #0x79
0x001499d4: 40 fe 3f 37              tbnz     w0, #7, #0x14999c
0x001499d8: 55 0f 80 52              mov      w21, #0x7a
0x001499dc: 00 fe 47 37              tbnz     w0, #8, #0x14999c
0x001499e0: 75 0f 80 52              mov      w21, #0x7b
0x001499e4: c0 fd 4f 37              tbnz     w0, #9, #0x14999c
0x001499e8: 60 12 40 f9              ldr      x0, [x19, #0x20]
0x001499ec: a1 63 03 91              add      x1, x29, #0xd8
0x001499f0: e2 71 ff 97              bl       #0x126178                  ; -> _ZN12stfaceunlock17FaceVerifyManager15GetModelVersionEPi
0x001499f4: a0 bb 00 b9              str      w0, [x29, #0xb8]
0x001499f8: a0 0c 00 34              cbz      w0, #0x149b8c
0x001499fc: 7b 02 05 91              add      x27, x19, #0x140
0x00149a00: 60 17 40 b9              ldr      w0, [x27, #0x14]
0x00149a04: b5 bb 40 b9              ldr      w21, [x29, #0xb8]
0x00149a08: 00 fd ff 34              cbz      w0, #0x1499a8
0x00149a0c: 60 a2 40 f9              ldr      x0, [x19, #0x140]
0x00149a10: a0 93 00 f9              str      x0, [x29, #0x120]
0x00149a14: bf a3 04 39              strb     wzr, [x29, #0x128]
0x00149a18: a0 6e 00 b4              cbz      x0, #0x14a7ec
0x00149a1c: 01 6f 02 90              adrp     x1, #0x4f29000
0x00149a20: a1 57 00 f9              str      x1, [x29, #0xa8]
0x00149a24: 21 94 47 f9              ldr      x1, [x1, #0xf28]           ; -> pthread_create@got
0x00149a28: 81 00 00 b4              cbz      x1, #0x149a38
0x00149a2c: a5 fc fc 97              bl       #0x88cc0                   ; -> pthread_mutex_lock@plt
0x00149a30: c0 6d 00 35              cbnz     w0, #0x14a7e8
0x00149a34: 60 a2 40 f9              ldr      x0, [x19, #0x140]
0x00149a38: 18 a0 00 91              add      x24, x0, #0x28
0x00149a3c: 75 42 05 91              add      x21, x19, #0x150
0x00149a40: 20 00 80 52              mov      w0, #1
0x00149a44: b9 83 04 91              add      x25, x29, #0x120
0x00149a48: a0 a3 04 39              strb     w0, [x29, #0x128]
0x00149a4c: 04 00 00 14              b        #0x149a5c                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x370
0x00149a50: e0 03 18 aa              mov      x0, x24
0x00149a54: e1 03 19 aa              mov      x1, x25
0x00149a58: 06 83 17 94              bl       #0x72a670                  ; -> _ZNSt18condition_variable4waitERSt11unique_lockISt5mutexE
0x00149a5c: a0 fe df 08              ldarb    w0, [x21]
0x00149a60: 00 1c 00 53              uxtb     w0, w0
0x00149a64: 60 ff ff 34              cbz      w0, #0x149a50
0x00149a68: 60 42 05 91              add      x0, x19, #0x150
0x00149a6c: 1f fc 9f 08              stlrb    wzr, [x0]
0x00149a70: 60 17 40 b9              ldr      w0, [x27, #0x14]
0x00149a74: a1 a3 44 39              ldrb     w1, [x29, #0x128]
0x00149a78: 00 04 00 51              sub      w0, w0, #1
0x00149a7c: 60 17 00 b9              str      w0, [x27, #0x14]
0x00149a80: e1 00 00 34              cbz      w1, #0x149a9c
0x00149a84: a0 93 40 f9              ldr      x0, [x29, #0x120]
0x00149a88: a0 00 00 b4              cbz      x0, #0x149a9c
0x00149a8c: a1 57 40 f9              ldr      x1, [x29, #0xa8]
0x00149a90: 21 94 47 f9              ldr      x1, [x1, #0xf28]           ; -> pthread_create@got
0x00149a94: 41 00 00 b4              cbz      x1, #0x149a9c
0x00149a98: 62 fb fc 97              bl       #0x88820                   ; -> pthread_mutex_unlock@plt
0x00149a9c: b5 bb 40 b9              ldr      w21, [x29, #0xb8]
0x00149aa0: c2 ff ff 17              b        #0x1499a8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x2bc
0x00149aa4: 60 06 40 f9              ldr      x0, [x19, #8]
0x00149aa8: 75 0a 40 f9              ldr      x21, [x19, #0x10]
0x00149aac: a0 5f 00 f9              str      x0, [x29, #0xb8]
0x00149ab0: 35 01 00 b4              cbz      x21, #0x149ad4
0x00149ab4: 00 6f 02 90              adrp     x0, #0x4f29000
0x00149ab8: 00 94 47 f9              ldr      x0, [x0, #0xf28]           ; -> pthread_create@got
0x00149abc: 60 14 00 b4              cbz      x0, #0x149d48
0x00149ac0: a0 22 00 91              add      x0, x21, #8
0x00149ac4: 01 fc 5f 88              ldaxr    w1, [x0]
0x00149ac8: 21 04 00 11              add      w1, w1, #1
0x00149acc: 01 fc 02 88              stlxr    w2, w1, [x0]
0x00149ad0: a2 ff ff 35              cbnz     w2, #0x149ac4
0x00149ad4: 61 5a 40 b9              ldr      w1, [x19, #0x58]
0x00149ad8: 00 08 80 d2              mov      x0, #0x40
0x00149adc: a3 73 40 f9              ldr      x3, [x29, #0xe0]
0x00149ae0: a1 cb 00 b9              str      w1, [x29, #0xc8]
0x00149ae4: a3 63 00 f9              str      x3, [x29, #0xc0]
0x00149ae8: bf c3 00 f9              str      xzr, [x29, #0x180]
0x00149aec: 42 84 16 94              bl       #0x6eabf4                  ; -> _Znwm
0x00149af0: a2 43 07 91              add      x2, x29, #0x1d0
0x00149af4: e1 03 00 aa              mov      x1, x0
0x00149af8: e3 03 02 aa              mov      x3, x2
0x00149afc: 02 6f 02 90              adrp     x2, #0x4f29000
0x00149b00: a4 5f 40 f9              ldr      x4, [x29, #0xb8]
0x00149b04: 60 e2 06 91              add      x0, x19, #0x1b8
0x00149b08: 42 1c 44 f9              ldr      x2, [x2, #0x838]
0x00149b0c: 22 00 00 f9              str      x2, [x1]
0x00149b10: a2 53 03 91              add      x2, x29, #0xd4
0x00149b14: 22 10 00 f9              str      x2, [x1, #0x20]
0x00149b18: a2 cb 40 b9              ldr      w2, [x29, #0xc8]
0x00149b1c: 22 28 00 b9              str      w2, [x1, #0x28]
0x00149b20: a2 63 40 f9              ldr      x2, [x29, #0xc0]
0x00149b24: 22 18 00 f9              str      x2, [x1, #0x30]
0x00149b28: e2 03 03 aa              mov      x2, x3
0x00149b2c: 3f 04 00 f9              str      xzr, [x1, #8]
0x00149b30: 03 00 00 f0              adrp     x3, #0x14c000
0x00149b34: 24 08 00 f9              str      x4, [x1, #0x10]
0x00149b38: 63 80 0a 91              add      x3, x3, #0x2a0
0x00149b3c: 35 0c 00 f9              str      x21, [x1, #0x18]
0x00149b40: 38 1c 00 f9              str      x24, [x1, #0x38]
0x00149b44: 41 0c 1a f8              str      x1, [x2, #-0x60]!
0x00149b48: a3 c7 00 f9              str      x3, [x29, #0x188]
0x00149b4c: e1 03 02 aa              mov      x1, x2
0x00149b50: a2 5f 00 f9              str      x2, [x29, #0xb8]
0x00149b54: e2 6e 02 f0              adrp     x2, #0x4f28000
0x00149b58: 42 70 47 f9              ldr      x2, [x2, #0xee0]
0x00149b5c: a2 c3 00 f9              str      x2, [x29, #0x180]
0x00149b60: be 11 00 94              bl       #0x14e258                  ; -> _ZN13BlockingQueueISt8functionIFivEEE4pushEOKS2_
0x00149b64: 60 56 41 b9              ldr      w0, [x19, #0x154]
0x00149b68: a3 c3 40 f9              ldr      x3, [x29, #0x180]
0x00149b6c: 00 04 00 11              add      w0, w0, #1
0x00149b70: 60 56 01 b9              str      w0, [x19, #0x154]
0x00149b74: 03 e9 ff b4              cbz      x3, #0x149894
0x00149b78: a0 5f 40 f9              ldr      x0, [x29, #0xb8]
0x00149b7c: 62 00 80 52              mov      w2, #3
0x00149b80: e1 03 00 aa              mov      x1, x0
0x00149b84: 60 00 3f d6              blr      x3
0x00149b88: 43 ff ff 17              b        #0x149894                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x1a8
0x00149b8c: 60 12 40 f9              ldr      x0, [x19, #0x20]
0x00149b90: 75 16 40 f9              ldr      x21, [x19, #0x28]
0x00149b94: bf 77 00 f9              str      xzr, [x29, #0xe8]
0x00149b98: a0 5b 00 f9              str      x0, [x29, #0xb0]
0x00149b9c: 35 01 00 b4              cbz      x21, #0x149bc0
0x00149ba0: 00 6f 02 90              adrp     x0, #0x4f29000
0x00149ba4: 00 94 47 f9              ldr      x0, [x0, #0xf28]           ; -> pthread_create@got
0x00149ba8: a0 12 00 b4              cbz      x0, #0x149dfc
0x00149bac: a0 22 00 91              add      x0, x21, #8
0x00149bb0: 01 fc 5f 88              ldaxr    w1, [x0]
0x00149bb4: 21 04 00 11              add      w1, w1, #1
0x00149bb8: 01 fc 02 88              stlxr    w2, w1, [x0]
0x00149bbc: a2 ff ff 35              cbnz     w2, #0x149bb0
0x00149bc0: a1 73 40 f9              ldr      x1, [x29, #0xe0]
0x00149bc4: 00 08 80 d2              mov      x0, #0x40
0x00149bc8: a1 5f 00 f9              str      x1, [x29, #0xb8]
0x00149bcc: bf d3 00 f9              str      xzr, [x29, #0x1a0]
0x00149bd0: 09 84 16 94              bl       #0x6eabf4                  ; -> _Znwm
0x00149bd4: a2 43 07 91              add      x2, x29, #0x1d0
0x00149bd8: e1 03 00 aa              mov      x1, x0
0x00149bdc: e3 03 02 aa              mov      x3, x2
0x00149be0: 02 6f 02 b0              adrp     x2, #0x4f2a000
0x00149be4: a4 5b 40 f9              ldr      x4, [x29, #0xb0]
0x00149be8: 60 62 03 91              add      x0, x19, #0xd8
0x00149bec: 42 a0 42 f9              ldr      x2, [x2, #0x540]
0x00149bf0: 22 00 00 f9              str      x2, [x1]
0x00149bf4: a2 a3 03 91              add      x2, x29, #0xe8
0x00149bf8: 22 14 00 f9              str      x2, [x1, #0x28]
0x00149bfc: a2 5f 40 f9              ldr      x2, [x29, #0xb8]
0x00149c00: 22 18 00 f9              str      x2, [x1, #0x30]
0x00149c04: e2 03 03 aa              mov      x2, x3
0x00149c08: 3f 04 00 f9              str      xzr, [x1, #8]
0x00149c0c: 03 00 00 f0              adrp     x3, #0x14c000
0x00149c10: 24 08 00 f9              str      x4, [x1, #0x10]
0x00149c14: 63 f0 0b 91              add      x3, x3, #0x2fc
0x00149c18: 35 0c 00 f9              str      x21, [x1, #0x18]
0x00149c1c: 3f 10 00 f9              str      xzr, [x1, #0x20]
0x00149c20: 38 1c 00 f9              str      x24, [x1, #0x38]
0x00149c24: 41 0c 1c f8              str      x1, [x2, #-0x40]!
0x00149c28: a3 d7 00 f9              str      x3, [x29, #0x1a8]
0x00149c2c: e1 03 02 aa              mov      x1, x2
0x00149c30: a2 53 00 f9              str      x2, [x29, #0xa0]
0x00149c34: 02 6f 02 90              adrp     x2, #0x4f29000
0x00149c38: 42 24 43 f9              ldr      x2, [x2, #0x648]
0x00149c3c: a2 d3 00 f9              str      x2, [x29, #0x1a0]
0x00149c40: 86 11 00 94              bl       #0x14e258                  ; -> _ZN13BlockingQueueISt8functionIFivEEE4pushEOKS2_
0x00149c44: 75 82 01 91              add      x21, x19, #0x60
0x00149c48: a0 16 40 b9              ldr      w0, [x21, #0x14]
0x00149c4c: a3 d3 40 f9              ldr      x3, [x29, #0x1a0]
0x00149c50: 00 04 00 11              add      w0, w0, #1
0x00149c54: a0 16 00 b9              str      w0, [x21, #0x14]
0x00149c58: a3 00 00 b4              cbz      x3, #0x149c6c
0x00149c5c: a1 53 40 f9              ldr      x1, [x29, #0xa0]
0x00149c60: 62 00 80 52              mov      w2, #3
0x00149c64: e0 03 01 aa              mov      x0, x1
0x00149c68: 60 00 3f d6              blr      x3
0x00149c6c: a3 73 40 f9              ldr      x3, [x29, #0xe0]
0x00149c70: e0 03 13 aa              mov      x0, x19
0x00149c74: e1 03 18 aa              mov      x1, x24
0x00149c78: e2 03 1b 2a              mov      w2, w27
0x00149c7c: 24 00 80 52              mov      w4, #1
0x00149c80: b0 f1 ff 97              bl       #0x146340                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace15CheckOcularInfoEP10st_image_tjP9st_face_tb
0x00149c84: a0 bb 00 b9              str      w0, [x29, #0xb8]
0x00149c88: 80 06 00 34              cbz      w0, #0x149d58
0x00149c8c: 7b 02 05 91              add      x27, x19, #0x140
0x00149c90: 60 17 40 b9              ldr      w0, [x27, #0x14]
0x00149c94: c0 15 00 35              cbnz     w0, #0x149f4c
0x00149c98: a0 16 40 b9              ldr      w0, [x21, #0x14]
0x00149c9c: c0 0b 00 35              cbnz     w0, #0x149e14
0x00149ca0: a0 77 40 f9              ldr      x0, [x29, #0xe8]
0x00149ca4: c0 ef ff b4              cbz      x0, #0x149a9c
0x00149ca8: 45 71 ff 97              bl       #0x1261bc                  ; -> _ZN12stfaceunlock17FaceVerifyManager14ReleaseFeatureEP12st_feature_t
0x00149cac: b5 bb 40 b9              ldr      w21, [x29, #0xb8]
0x00149cb0: 3e ff ff 17              b        #0x1499a8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x2bc
0x00149cb4: 60 a2 40 f9              ldr      x0, [x19, #0x140]
0x00149cb8: a0 8b 00 f9              str      x0, [x29, #0x110]
0x00149cbc: bf 63 04 39              strb     wzr, [x29, #0x118]
0x00149cc0: c0 5c 00 b4              cbz      x0, #0x14a858
0x00149cc4: 01 6f 02 90              adrp     x1, #0x4f29000
0x00149cc8: a1 57 00 f9              str      x1, [x29, #0xa8]
0x00149ccc: 21 94 47 f9              ldr      x1, [x1, #0xf28]           ; -> pthread_create@got
0x00149cd0: 81 00 00 b4              cbz      x1, #0x149ce0
0x00149cd4: fb fb fc 97              bl       #0x88cc0                   ; -> pthread_mutex_lock@plt
0x00149cd8: 60 5c 00 35              cbnz     w0, #0x14a864
0x00149cdc: 60 a2 40 f9              ldr      x0, [x19, #0x140]
0x00149ce0: 1a a0 00 91              add      x26, x0, #0x28
0x00149ce4: 78 42 05 91              add      x24, x19, #0x150
0x00149ce8: 20 00 80 52              mov      w0, #1
0x00149cec: b9 43 04 91              add      x25, x29, #0x110
0x00149cf0: a0 63 04 39              strb     w0, [x29, #0x118]
0x00149cf4: 04 00 00 14              b        #0x149d04                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x618
0x00149cf8: e0 03 1a aa              mov      x0, x26
0x00149cfc: e1 03 19 aa              mov      x1, x25
0x00149d00: 5c 82 17 94              bl       #0x72a670                  ; -> _ZNSt18condition_variable4waitERSt11unique_lockISt5mutexE
0x00149d04: 00 ff df 08              ldarb    w0, [x24]
0x00149d08: 00 1c 00 53              uxtb     w0, w0
0x00149d0c: 60 ff ff 34              cbz      w0, #0x149cf8
0x00149d10: 60 42 05 91              add      x0, x19, #0x150
0x00149d14: 1f fc 9f 08              stlrb    wzr, [x0]
0x00149d18: 60 17 40 b9              ldr      w0, [x27, #0x14]
0x00149d1c: a1 63 44 39              ldrb     w1, [x29, #0x118]
0x00149d20: 00 04 00 51              sub      w0, w0, #1
0x00149d24: 60 17 00 b9              str      w0, [x27, #0x14]
0x00149d28: 01 e4 ff 34              cbz      w1, #0x1499a8
0x00149d2c: a0 8b 40 f9              ldr      x0, [x29, #0x110]
0x00149d30: c0 e3 ff b4              cbz      x0, #0x1499a8
0x00149d34: a1 57 40 f9              ldr      x1, [x29, #0xa8]
0x00149d38: 21 94 47 f9              ldr      x1, [x1, #0xf28]           ; -> pthread_create@got
0x00149d3c: 61 e3 ff b4              cbz      x1, #0x1499a8
0x00149d40: b8 fa fc 97              bl       #0x88820                   ; -> pthread_mutex_unlock@plt
0x00149d44: 19 ff ff 17              b        #0x1499a8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x2bc
0x00149d48: a0 0a 40 b9              ldr      w0, [x21, #8]
0x00149d4c: 00 04 00 11              add      w0, w0, #1
0x00149d50: a0 0a 00 b9              str      w0, [x21, #8]
0x00149d54: 60 ff ff 17              b        #0x149ad4                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x3e8
0x00149d58: e0 03 1b 2a              mov      w0, w27
0x00149d5c: 01 01 80 52              mov      w1, #8
0x00149d60: 3a e2 ff 97              bl       #0x142648                  ; -> _ZN12stfaceunlock9CommonFun9CheckFlagEjj
0x00149d64: 00 1c 00 53              uxtb     w0, w0
0x00149d68: 20 05 00 35              cbnz     w0, #0x149e0c
0x00149d6c: 7b 02 05 91              add      x27, x19, #0x140
0x00149d70: 60 17 40 b9              ldr      w0, [x27, #0x14]
0x00149d74: 20 19 00 35              cbnz     w0, #0x14a098
0x00149d78: 60 1b 40 b9              ldr      w0, [x27, #0x18]
0x00149d7c: a0 9b 00 b9              str      w0, [x29, #0x98]
0x00149d80: a0 16 40 b9              ldr      w0, [x21, #0x14]
0x00149d84: 40 09 00 35              cbnz     w0, #0x149eac
0x00149d88: b5 1a 40 b9              ldr      w21, [x21, #0x18]
0x00149d8c: a0 77 40 f9              ldr      x0, [x29, #0xe8]
0x00149d90: c1 aa 8a 52              mov      w1, #0x5556
0x00149d94: a1 aa aa 72              movk     w1, #0x5555, lsl #16
0x00149d98: 00 08 40 b9              ldr      w0, [x0, #8]
0x00149d9c: 00 08 00 11              add      w0, w0, #2
0x00149da0: 01 7c 21 9b              smull    x1, w0, w1
0x00149da4: 21 fc 60 d3              lsr      x1, x1, #0x20
0x00149da8: 20 7c 80 4b              sub      w0, w1, w0, asr #31
0x00149dac: 00 74 1e 53              lsl      w0, w0, #2
0x00149db0: 00 04 00 11              add      w0, w0, #1
0x00149db4: 00 7c 40 93              sxtw     x0, w0
0x00149db8: c3 83 16 94              bl       #0x6eacc4                  ; -> _Znam
0x00149dbc: fb 03 00 aa              mov      x27, x0
0x00149dc0: a0 77 40 f9              ldr      x0, [x29, #0xe8]
0x00149dc4: e1 03 1b aa              mov      x1, x27
0x00149dc8: fe 70 ff 97              bl       #0x1261c0                  ; -> _ZN12stfaceunlock17FaceVerifyManager16SerializeFeatureEPK12st_feature_tPc
0x00149dcc: a0 9b 40 b9              ldr      w0, [x29, #0x98]
0x00149dd0: 40 15 00 35              cbnz     w0, #0x14a078
0x00149dd4: 75 10 00 34              cbz      w21, #0x149fe0
0x00149dd8: e0 30 00 90              adrp     x0, #0x765000
0x00149ddc: 21 e3 12 91              add      x1, x25, #0x4b8
0x00149de0: 00 00 0d 91              add      x0, x0, #0x340
0x00149de4: 42 79 80 52              mov      w2, #0x3ca
0x00149de8: e3 03 15 2a              mov      w3, w21
0x00149dec: c0 d3 ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x00149df0: e0 03 1b aa              mov      x0, x27
0x00149df4: 05 7d 16 94              bl       #0x6e9208                  ; -> _ZdaPv
0x00149df8: ec fe ff 17              b        #0x1499a8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x2bc
0x00149dfc: a0 0a 40 b9              ldr      w0, [x21, #8]
0x00149e00: 00 04 00 11              add      w0, w0, #1
0x00149e04: a0 0a 00 b9              str      w0, [x21, #8]
0x00149e08: 6e ff ff 17              b        #0x149bc0                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x4d4
0x00149e0c: bf 9b 00 b9              str      wzr, [x29, #0x98]
0x00149e10: dc ff ff 17              b        #0x149d80                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x694
0x00149e14: 60 32 40 f9              ldr      x0, [x19, #0x60]
0x00149e18: a0 a3 00 f9              str      x0, [x29, #0x140]
0x00149e1c: bf 23 05 39              strb     wzr, [x29, #0x148]
0x00149e20: 60 52 00 b4              cbz      x0, #0x14a86c
0x00149e24: 01 6f 02 90              adrp     x1, #0x4f29000
0x00149e28: a1 57 00 f9              str      x1, [x29, #0xa8]
0x00149e2c: 21 94 47 f9              ldr      x1, [x1, #0xf28]           ; -> pthread_create@got
0x00149e30: 81 00 00 b4              cbz      x1, #0x149e40
0x00149e34: a3 fb fc 97              bl       #0x88cc0                   ; -> pthread_mutex_lock@plt
0x00149e38: 80 51 00 35              cbnz     w0, #0x14a868
0x00149e3c: 60 32 40 f9              ldr      x0, [x19, #0x60]
0x00149e40: 1a a0 00 91              add      x26, x0, #0x28
0x00149e44: 78 c2 01 91              add      x24, x19, #0x70
0x00149e48: 20 00 80 52              mov      w0, #1
0x00149e4c: b9 03 05 91              add      x25, x29, #0x140
0x00149e50: a0 23 05 39              strb     w0, [x29, #0x148]
0x00149e54: 04 00 00 14              b        #0x149e64                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x778
0x00149e58: e0 03 1a aa              mov      x0, x26
0x00149e5c: e1 03 19 aa              mov      x1, x25
0x00149e60: 04 82 17 94              bl       #0x72a670                  ; -> _ZNSt18condition_variable4waitERSt11unique_lockISt5mutexE
0x00149e64: 00 ff df 08              ldarb    w0, [x24]
0x00149e68: 00 1c 00 53              uxtb     w0, w0
0x00149e6c: 60 ff ff 34              cbz      w0, #0x149e58
0x00149e70: 60 c2 01 91              add      x0, x19, #0x70
0x00149e74: 1f fc 9f 08              stlrb    wzr, [x0]
0x00149e78: a0 16 40 b9              ldr      w0, [x21, #0x14]
0x00149e7c: a1 23 45 39              ldrb     w1, [x29, #0x148]
0x00149e80: 00 04 00 51              sub      w0, w0, #1
0x00149e84: a0 16 00 b9              str      w0, [x21, #0x14]
0x00149e88: c1 f0 ff 34              cbz      w1, #0x149ca0
0x00149e8c: b5 a3 40 f9              ldr      x21, [x29, #0x140]
0x00149e90: 95 f0 ff b4              cbz      x21, #0x149ca0
0x00149e94: a0 57 40 f9              ldr      x0, [x29, #0xa8]
0x00149e98: 00 94 47 f9              ldr      x0, [x0, #0xf28]
0x00149e9c: 20 f0 ff b4              cbz      x0, #0x149ca0
0x00149ea0: e0 03 15 aa              mov      x0, x21
0x00149ea4: 5f fa fc 97              bl       #0x88820                   ; -> pthread_mutex_unlock@plt
0x00149ea8: 7e ff ff 17              b        #0x149ca0                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x5b4
0x00149eac: 60 32 40 f9              ldr      x0, [x19, #0x60]
0x00149eb0: a0 b3 00 f9              str      x0, [x29, #0x160]
0x00149eb4: bf a3 05 39              strb     wzr, [x29, #0x168]
0x00149eb8: 80 48 00 b4              cbz      x0, #0x14a7c8
0x00149ebc: 01 6f 02 90              adrp     x1, #0x4f29000
0x00149ec0: a1 57 00 f9              str      x1, [x29, #0xa8]
0x00149ec4: 21 94 47 f9              ldr      x1, [x1, #0xf28]           ; -> pthread_create@got
0x00149ec8: 81 00 00 b4              cbz      x1, #0x149ed8
0x00149ecc: 7d fb fc 97              bl       #0x88cc0                   ; -> pthread_mutex_lock@plt
0x00149ed0: 80 46 00 35              cbnz     w0, #0x14a7a0
0x00149ed4: 60 32 40 f9              ldr      x0, [x19, #0x60]
0x00149ed8: 00 a0 00 91              add      x0, x0, #0x28
0x00149edc: a0 5b 00 f9              str      x0, [x29, #0xb0]
0x00149ee0: 20 00 80 52              mov      w0, #1
0x00149ee4: 7b c2 01 91              add      x27, x19, #0x70
0x00149ee8: a0 a3 05 39              strb     w0, [x29, #0x168]
0x00149eec: a0 83 05 91              add      x0, x29, #0x160
0x00149ef0: a0 5f 00 f9              str      x0, [x29, #0xb8]
0x00149ef4: 04 00 00 14              b        #0x149f04                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x818
0x00149ef8: a0 5b 40 f9              ldr      x0, [x29, #0xb0]
0x00149efc: a1 5f 40 f9              ldr      x1, [x29, #0xb8]
0x00149f00: dc 81 17 94              bl       #0x72a670                  ; -> _ZNSt18condition_variable4waitERSt11unique_lockISt5mutexE
0x00149f04: 60 ff df 08              ldarb    w0, [x27]
0x00149f08: 00 1c 00 53              uxtb     w0, w0
0x00149f0c: 60 ff ff 34              cbz      w0, #0x149ef8
0x00149f10: 60 c2 01 91              add      x0, x19, #0x70
0x00149f14: 1f fc 9f 08              stlrb    wzr, [x0]
0x00149f18: a0 16 40 b9              ldr      w0, [x21, #0x14]
0x00149f1c: a1 a3 45 39              ldrb     w1, [x29, #0x168]
0x00149f20: 00 04 00 51              sub      w0, w0, #1
0x00149f24: a0 16 00 b9              str      w0, [x21, #0x14]
0x00149f28: b5 1a 40 b9              ldr      w21, [x21, #0x18]
0x00149f2c: 01 f3 ff 34              cbz      w1, #0x149d8c
0x00149f30: a0 b3 40 f9              ldr      x0, [x29, #0x160]
0x00149f34: c0 f2 ff b4              cbz      x0, #0x149d8c
0x00149f38: a1 57 40 f9              ldr      x1, [x29, #0xa8]
0x00149f3c: 21 94 47 f9              ldr      x1, [x1, #0xf28]           ; -> pthread_create@got
0x00149f40: 61 f2 ff b4              cbz      x1, #0x149d8c
0x00149f44: 37 fa fc 97              bl       #0x88820                   ; -> pthread_mutex_unlock@plt
0x00149f48: 91 ff ff 17              b        #0x149d8c                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x6a0
0x00149f4c: 60 a2 40 f9              ldr      x0, [x19, #0x140]
0x00149f50: a0 9b 00 f9              str      x0, [x29, #0x130]
0x00149f54: bf e3 04 39              strb     wzr, [x29, #0x138]
0x00149f58: c0 42 00 b4              cbz      x0, #0x14a7b0
0x00149f5c: 01 6f 02 90              adrp     x1, #0x4f29000
0x00149f60: a1 57 00 f9              str      x1, [x29, #0xa8]
0x00149f64: 21 94 47 f9              ldr      x1, [x1, #0xf28]           ; -> pthread_create@got
0x00149f68: 81 00 00 b4              cbz      x1, #0x149f78
0x00149f6c: 55 fb fc 97              bl       #0x88cc0                   ; -> pthread_mutex_lock@plt
0x00149f70: e0 41 00 35              cbnz     w0, #0x14a7ac
0x00149f74: 60 a2 40 f9              ldr      x0, [x19, #0x140]
0x00149f78: 1a a0 00 91              add      x26, x0, #0x28
0x00149f7c: 78 42 05 91              add      x24, x19, #0x150
0x00149f80: 20 00 80 52              mov      w0, #1
0x00149f84: b9 c3 04 91              add      x25, x29, #0x130
0x00149f88: a0 e3 04 39              strb     w0, [x29, #0x138]
0x00149f8c: 04 00 00 14              b        #0x149f9c                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x8b0
0x00149f90: e0 03 1a aa              mov      x0, x26
0x00149f94: e1 03 19 aa              mov      x1, x25
0x00149f98: b6 81 17 94              bl       #0x72a670                  ; -> _ZNSt18condition_variable4waitERSt11unique_lockISt5mutexE
0x00149f9c: 00 ff df 08              ldarb    w0, [x24]
0x00149fa0: 00 1c 00 53              uxtb     w0, w0
0x00149fa4: 60 ff ff 34              cbz      w0, #0x149f90
0x00149fa8: 60 42 05 91              add      x0, x19, #0x150
0x00149fac: 1f fc 9f 08              stlrb    wzr, [x0]
0x00149fb0: 60 17 40 b9              ldr      w0, [x27, #0x14]
0x00149fb4: a1 e3 44 39              ldrb     w1, [x29, #0x138]
0x00149fb8: 00 04 00 51              sub      w0, w0, #1
0x00149fbc: 60 17 00 b9              str      w0, [x27, #0x14]
0x00149fc0: c1 e6 ff 34              cbz      w1, #0x149c98
0x00149fc4: a0 9b 40 f9              ldr      x0, [x29, #0x130]
0x00149fc8: 80 e6 ff b4              cbz      x0, #0x149c98
0x00149fcc: a1 57 40 f9              ldr      x1, [x29, #0xa8]
0x00149fd0: 21 94 47 f9              ldr      x1, [x1, #0xf28]           ; -> pthread_create@got
0x00149fd4: 21 e6 ff b4              cbz      x1, #0x149c98
0x00149fd8: 12 fa fc 97              bl       #0x88820                   ; -> pthread_mutex_unlock@plt
0x00149fdc: 2f ff ff 17              b        #0x149c98                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x5ac
0x00149fe0: a0 d7 40 bd              ldr      s0, [x29, #0xd4]
0x00149fe4: e0 30 00 90              adrp     x0, #0x765000
0x00149fe8: a3 77 40 f9              ldr      x3, [x29, #0xe8]
0x00149fec: 00 60 0d 91              add      x0, x0, #0x358
0x00149ff0: 00 c0 22 1e              fcvt     d0, s0
0x00149ff4: 21 e3 12 91              add      x1, x25, #0x4b8
0x00149ff8: 62 7a 80 52              mov      w2, #0x3d3
0x00149ffc: a3 5b 00 f9              str      x3, [x29, #0xb0]
0x0014a000: b5 d2 ff 97              bl       #0x13ead4                  ; -> _Z4LogIPKcz
0x0014a004: 61 26 42 bd              ldr      s1, [x19, #0x224]
0x0014a008: a0 d7 40 bd              ldr      s0, [x29, #0xd4]
0x0014a00c: 30 20 20 1e              fcmpe    s1, s0
0x0014a010: a4 09 00 54              b.mi     #0x14a144
0x0014a014: 60 26 40 f9              ldr      x0, [x19, #0x48]
0x0014a018: 20 22 00 b4              cbz      x0, #0x14a45c
0x0014a01c: 60 5a 42 b9              ldr      w0, [x19, #0x258]
0x0014a020: 61 82 00 91              add      x1, x19, #0x20
0x0014a024: bf df 00 b9              str      wzr, [x29, #0xdc]
0x0014a028: a1 57 00 f9              str      x1, [x29, #0xa8]
0x0014a02c: 1f 04 00 71              cmp      w0, #1
0x0014a030: e0 15 00 54              b.eq     #0x14a2ec
0x0014a034: 63 32 41 f9              ldr      x3, [x19, #0x260]
0x0014a038: 60 36 41 f9              ldr      x0, [x19, #0x268]
0x0014a03c: 00 00 03 cb              sub      x0, x0, x3
0x0014a040: 00 fc 43 93              asr      x0, x0, #3
0x0014a044: 40 08 00 b4              cbz      x0, #0x14a14c
0x0014a048: 02 04 00 d1              sub      x2, x0, #1
0x0014a04c: e1 03 1b aa              mov      x1, x27
0x0014a050: e0 03 13 aa              mov      x0, x19
0x0014a054: 62 78 62 f8              ldr      x2, [x3, x2, lsl #3]
0x0014a058: 42 14 40 f9              ldr      x2, [x2, #0x28]
0x0014a05c: 80 f1 ff 97              bl       #0x14665c                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace7CompareEPKcS2_
0x0014a060: 60 07 00 34              cbz      w0, #0x14a14c
0x0014a064: f5 03 00 2a              mov      w21, w0
0x0014a068: a0 5b 40 f9              ldr      x0, [x29, #0xb0]
0x0014a06c: 20 ec ff b4              cbz      x0, #0x149df0
0x0014a070: 53 70 ff 97              bl       #0x1261bc                  ; -> _ZN12stfaceunlock17FaceVerifyManager14ReleaseFeatureEP12st_feature_t
0x0014a074: 5f ff ff 17              b        #0x149df0                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x704
0x0014a078: c0 30 00 f0              adrp     x0, #0x765000
0x0014a07c: a3 9b 40 b9              ldr      w3, [x29, #0x98]
0x0014a080: 21 e3 12 91              add      x1, x25, #0x4b8
0x0014a084: 00 60 0c 91              add      x0, x0, #0x318
0x0014a088: a2 78 80 52              mov      w2, #0x3c5
0x0014a08c: 18 d3 ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0014a090: b5 9b 40 b9              ldr      w21, [x29, #0x98]
0x0014a094: 57 ff ff 17              b        #0x149df0                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x704
0x0014a098: 60 a2 40 f9              ldr      x0, [x19, #0x140]
0x0014a09c: a0 ab 00 f9              str      x0, [x29, #0x150]
0x0014a0a0: bf 63 05 39              strb     wzr, [x29, #0x158]
0x0014a0a4: e0 27 00 b4              cbz      x0, #0x14a5a0
0x0014a0a8: e1 6e 02 f0              adrp     x1, #0x4f29000
0x0014a0ac: a1 57 00 f9              str      x1, [x29, #0xa8]
0x0014a0b0: 21 94 47 f9              ldr      x1, [x1, #0xf28]           ; -> pthread_create@got
0x0014a0b4: 81 00 00 b4              cbz      x1, #0x14a0c4
0x0014a0b8: 02 fb fc 97              bl       #0x88cc0                   ; -> pthread_mutex_lock@plt
0x0014a0bc: 60 27 00 35              cbnz     w0, #0x14a5a8
0x0014a0c0: 60 a2 40 f9              ldr      x0, [x19, #0x140]
0x0014a0c4: 00 a0 00 91              add      x0, x0, #0x28
0x0014a0c8: a0 4f 00 f9              str      x0, [x29, #0x98]
0x0014a0cc: 20 00 80 52              mov      w0, #1
0x0014a0d0: a0 63 05 39              strb     w0, [x29, #0x158]
0x0014a0d4: 60 42 05 91              add      x0, x19, #0x150
0x0014a0d8: a0 5f 00 f9              str      x0, [x29, #0xb8]
0x0014a0dc: a0 43 05 91              add      x0, x29, #0x150
0x0014a0e0: a0 5b 00 f9              str      x0, [x29, #0xb0]
0x0014a0e4: 04 00 00 14              b        #0x14a0f4                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xa08
0x0014a0e8: a0 4f 40 f9              ldr      x0, [x29, #0x98]
0x0014a0ec: a1 5b 40 f9              ldr      x1, [x29, #0xb0]
0x0014a0f0: 60 81 17 94              bl       #0x72a670                  ; -> _ZNSt18condition_variable4waitERSt11unique_lockISt5mutexE
0x0014a0f4: a0 5f 40 f9              ldr      x0, [x29, #0xb8]
0x0014a0f8: 00 fc df 08              ldarb    w0, [x0]
0x0014a0fc: 00 1c 00 53              uxtb     w0, w0
0x0014a100: 40 ff ff 34              cbz      w0, #0x14a0e8
0x0014a104: 60 42 05 91              add      x0, x19, #0x150
0x0014a108: 1f fc 9f 08              stlrb    wzr, [x0]
0x0014a10c: 60 17 40 b9              ldr      w0, [x27, #0x14]
0x0014a110: a1 63 45 39              ldrb     w1, [x29, #0x158]
0x0014a114: 00 04 00 51              sub      w0, w0, #1
0x0014a118: 60 17 00 b9              str      w0, [x27, #0x14]
0x0014a11c: 60 1b 40 b9              ldr      w0, [x27, #0x18]
0x0014a120: a0 9b 00 b9              str      w0, [x29, #0x98]
0x0014a124: e1 e2 ff 34              cbz      w1, #0x149d80
0x0014a128: a0 ab 40 f9              ldr      x0, [x29, #0x150]
0x0014a12c: a0 e2 ff b4              cbz      x0, #0x149d80
0x0014a130: a1 57 40 f9              ldr      x1, [x29, #0xa8]
0x0014a134: 21 94 47 f9              ldr      x1, [x1, #0xf28]           ; -> pthread_create@got
0x0014a138: 41 e2 ff b4              cbz      x1, #0x149d80
0x0014a13c: b9 f9 fc 97              bl       #0x88820                   ; -> pthread_mutex_unlock@plt
0x0014a140: 10 ff ff 17              b        #0x149d80                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x694
0x0014a144: 55 00 80 52              mov      w21, #2
0x0014a148: c8 ff ff 17              b        #0x14a068                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x97c
0x0014a14c: a9 c3 06 91              add      x9, x29, #0x1b0
0x0014a150: a0 83 06 91              add      x0, x29, #0x1a0
0x0014a154: a3 73 40 f9              ldr      x3, [x29, #0xe0]
0x0014a158: e1 03 1b aa              mov      x1, x27
0x0014a15c: 05 17 40 b9              ldr      w5, [x24, #0x14]
0x0014a160: 3f 7d 00 a9              stp      xzr, xzr, [x9]
0x0014a164: a9 03 07 91              add      x9, x29, #0x1c0
0x0014a168: 08 0b 40 b9              ldr      w8, [x24, #8]
0x0014a16c: 1f 7c 00 a9              stp      xzr, xzr, [x0]
0x0014a170: 07 0f 40 b9              ldr      w7, [x24, #0xc]
0x0014a174: a0 c3 05 91              add      x0, x29, #0x170
0x0014a178: 06 13 40 b9              ldr      w6, [x24, #0x10]
0x0014a17c: e2 03 00 aa              mov      x2, x0
0x0014a180: a5 a7 01 b9              str      w5, [x29, #0x1a4]
0x0014a184: 64 c0 00 91              add      x4, x3, #0x30
0x0014a188: 3f 7d 00 a9              stp      xzr, xzr, [x9]
0x0014a18c: a8 9b 01 b9              str      w8, [x29, #0x198]
0x0014a190: a7 9f 01 b9              str      w7, [x29, #0x19c]
0x0014a194: a6 a3 01 b9              str      w6, [x29, #0x1a0]
0x0014a198: a0 5f 00 f9              str      x0, [x29, #0xb8]
0x0014a19c: a0 53 40 f9              ldr      x0, [x29, #0xa0]
0x0014a1a0: 63 14 40 b9              ldr      w3, [x3, #0x14]
0x0014a1a4: 05 03 40 f9              ldr      x5, [x24]
0x0014a1a8: 00 a0 00 91              add      x0, x0, #0x28
0x0014a1ac: a5 cb 00 f9              str      x5, [x29, #0x190]
0x0014a1b0: a3 af 01 b9              str      w3, [x29, #0x1ac]
0x0014a1b4: a4 db 00 f9              str      x4, [x29, #0x1b0]
0x0014a1b8: 56 a8 17 94              bl       #0x734310                  ; -> _ZNSsC2EPKcRKSaIcE
0x0014a1bc: 60 42 01 91              add      x0, x19, #0x50
0x0014a1c0: a0 47 00 f9              str      x0, [x29, #0x88]
0x0014a1c4: e1 03 00 aa              mov      x1, x0
0x0014a1c8: a0 53 40 f9              ldr      x0, [x29, #0xa0]
0x0014a1cc: 00 c0 00 91              add      x0, x0, #0x30
0x0014a1d0: ac b6 17 94              bl       #0x737c80                  ; -> _ZNSsC2ERKSs
0x0014a1d4: 01 6f 02 90              adrp     x1, #0x4f2a000
0x0014a1d8: 00 08 80 d2              mov      x0, #0x40
0x0014a1dc: 21 6c 46 f9              ldr      x1, [x1, #0xcd8]
0x0014a1e0: a1 82 16 94              bl       #0x6eac64                  ; -> _ZnwmRKSt9nothrow_t
0x0014a1e4: f8 03 00 aa              mov      x24, x0
0x0014a1e8: 80 14 00 b4              cbz      x0, #0x14a478
0x0014a1ec: e2 03 00 aa              mov      x2, x0
0x0014a1f0: 00 04 00 4f              movi     v0.4s, #0
0x0014a1f4: a1 53 40 f9              ldr      x1, [x29, #0xa0]
0x0014a1f8: 5f 84 00 f8              str      xzr, [x2], #8
0x0014a1fc: 40 00 80 3d              str      q0, [x2]
0x0014a200: 02 6f 02 90              adrp     x2, #0x4f2a000
0x0014a204: 1f 18 00 b9              str      wzr, [x0, #0x18]
0x0014a208: 1f 1c 00 b9              str      wzr, [x0, #0x1c]
0x0014a20c: 1f 10 00 f9              str      xzr, [x0, #0x20]
0x0014a210: 1f e0 00 39              strb     wzr, [x0, #0x38]
0x0014a214: 42 f8 47 f9              ldr      x2, [x2, #0xff0]
0x0014a218: 42 60 00 91              add      x2, x2, #0x18
0x0014a21c: 02 14 00 f9              str      x2, [x0, #0x28]
0x0014a220: 02 18 00 f9              str      x2, [x0, #0x30]
0x0014a224: 51 b6 ff 97              bl       #0x137b68                  ; -> _ZN12stfaceunlock23SingleTempldateFileInfo8CopyFromEPKS0_
0x0014a228: 60 05 00 35              cbnz     w0, #0x14a2d4
0x0014a22c: 60 82 09 91              add      x0, x19, #0x260
0x0014a230: a0 4f 00 f9              str      x0, [x29, #0x98]
0x0014a234: e1 03 00 aa              mov      x1, x0
0x0014a238: 60 36 41 f9              ldr      x0, [x19, #0x268]
0x0014a23c: 21 08 40 f9              ldr      x1, [x1, #0x10]            ; -> __G61__android16_bcl_binary_data@got
0x0014a240: 3f 00 00 eb              cmp      x1, x0
0x0014a244: 80 1d 00 54              b.eq     #0x14a5f4
0x0014a248: 60 00 00 b4              cbz      x0, #0x14a254
0x0014a24c: 18 00 00 f9              str      x24, [x0]
0x0014a250: 18 00 80 d2              mov      x24, #0
0x0014a254: a1 4f 40 f9              ldr      x1, [x29, #0x98]
0x0014a258: 00 20 00 91              add      x0, x0, #8
0x0014a25c: 20 04 00 f9              str      x0, [x1, #8]
0x0014a260: 63 32 41 f9              ldr      x3, [x19, #0x260]
0x0014a264: 60 36 41 f9              ldr      x0, [x19, #0x268]
0x0014a268: 62 5a 42 b9              ldr      w2, [x19, #0x258]
0x0014a26c: 00 00 03 cb              sub      x0, x0, x3
0x0014a270: 00 fc 43 93              asr      x0, x0, #3
0x0014a274: 1f c0 22 eb              cmp      x0, w2, sxtw
0x0014a278: 40 10 00 54              b.eq     #0x14a480
0x0014a27c: 60 26 40 f9              ldr      x0, [x19, #0x48]
0x0014a280: a1 5f 40 f9              ldr      x1, [x29, #0xb8]
0x0014a284: bf 73 01 b9              str      wzr, [x29, #0x170]
0x0014a288: cb ab ff 97              bl       #0x1351b4                  ; -> _ZN12stfaceunlock14FeatureManager18GetEnrollFaceCountEPi
0x0014a28c: a0 73 41 b9              ldr      w0, [x29, #0x170]
0x0014a290: 1f 14 00 71              cmp      w0, #5
0x0014a294: 00 0e 00 54              b.eq     #0x14a454
0x0014a298: 62 36 41 f9              ldr      x2, [x19, #0x268]
0x0014a29c: c0 30 00 f0              adrp     x0, #0x765000
0x0014a2a0: 63 32 41 f9              ldr      x3, [x19, #0x260]
0x0014a2a4: 00 e0 0d 91              add      x0, x0, #0x378
0x0014a2a8: 64 5a 42 b9              ldr      w4, [x19, #0x258]
0x0014a2ac: 21 e3 12 91              add      x1, x25, #0x4b8
0x0014a2b0: 43 00 03 cb              sub      x3, x2, x3
0x0014a2b4: 82 83 80 52              mov      w2, #0x41c
0x0014a2b8: 63 fc 43 93              asr      x3, x3, #3
0x0014a2bc: c3 d1 ff 97              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x0014a2c0: b5 00 80 52              mov      w21, #5
0x0014a2c4: b8 00 00 b5              cbnz     x24, #0x14a2d8
0x0014a2c8: a0 53 40 f9              ldr      x0, [x29, #0xa0]
0x0014a2cc: e9 b5 ff 97              bl       #0x137a70                  ; -> _ZN12stfaceunlock23SingleTempldateFileInfoD2Ev
0x0014a2d0: 66 ff ff 17              b        #0x14a068                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x97c
0x0014a2d4: f5 03 00 2a              mov      w21, w0
0x0014a2d8: e0 03 18 aa              mov      x0, x24
0x0014a2dc: e5 b5 ff 97              bl       #0x137a70                  ; -> _ZN12stfaceunlock23SingleTempldateFileInfoD2Ev
0x0014a2e0: e0 03 18 aa              mov      x0, x24
0x0014a2e4: c7 7b 16 94              bl       #0x6e9200                  ; -> _ZdlPv
0x0014a2e8: f8 ff ff 17              b        #0x14a2c8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xbdc
0x0014a2ec: a9 c3 06 91              add      x9, x29, #0x1b0
0x0014a2f0: a0 83 06 91              add      x0, x29, #0x1a0
0x0014a2f4: a3 73 40 f9              ldr      x3, [x29, #0xe0]
0x0014a2f8: e1 03 1b aa              mov      x1, x27
0x0014a2fc: 05 17 40 b9              ldr      w5, [x24, #0x14]
0x0014a300: 3f 7d 00 a9              stp      xzr, xzr, [x9]
0x0014a304: a9 03 07 91              add      x9, x29, #0x1c0
0x0014a308: 08 0b 40 b9              ldr      w8, [x24, #8]
0x0014a30c: 1f 7c 00 a9              stp      xzr, xzr, [x0]
0x0014a310: 07 0f 40 b9              ldr      w7, [x24, #0xc]
0x0014a314: a0 c3 05 91              add      x0, x29, #0x170
0x0014a318: 06 13 40 b9              ldr      w6, [x24, #0x10]
0x0014a31c: f9 03 00 aa              mov      x25, x0
0x0014a320: a5 a7 01 b9              str      w5, [x29, #0x1a4]
0x0014a324: 64 c0 00 91              add      x4, x3, #0x30
0x0014a328: 3f 7d 00 a9              stp      xzr, xzr, [x9]
0x0014a32c: a8 9b 01 b9              str      w8, [x29, #0x198]
0x0014a330: e2 03 19 aa              mov      x2, x25
0x0014a334: a7 9f 01 b9              str      w7, [x29, #0x19c]
0x0014a338: a6 a3 01 b9              str      w6, [x29, #0x1a0]
0x0014a33c: a0 5f 00 f9              str      x0, [x29, #0xb8]
0x0014a340: a0 53 40 f9              ldr      x0, [x29, #0xa0]
0x0014a344: 63 14 40 b9              ldr      w3, [x3, #0x14]
0x0014a348: 05 03 40 f9              ldr      x5, [x24]
0x0014a34c: 00 a0 00 91              add      x0, x0, #0x28
0x0014a350: a5 cb 00 f9              str      x5, [x29, #0x190]
0x0014a354: a3 af 01 b9              str      w3, [x29, #0x1ac]
0x0014a358: a4 db 00 f9              str      x4, [x29, #0x1b0]
0x0014a35c: ed a7 17 94              bl       #0x734310                  ; -> _ZNSsC2EPKcRKSaIcE
0x0014a360: a0 53 40 f9              ldr      x0, [x29, #0xa0]
0x0014a364: 78 42 01 91              add      x24, x19, #0x50
0x0014a368: e1 03 18 aa              mov      x1, x24
0x0014a36c: 00 c0 00 91              add      x0, x0, #0x30
0x0014a370: 44 b6 17 94              bl       #0x737c80                  ; -> _ZNSsC2ERKSs
0x0014a374: a1 43 07 91              add      x1, x29, #0x1d0
0x0014a378: a2 53 40 f9              ldr      x2, [x29, #0xa0]
0x0014a37c: e0 03 19 aa              mov      x0, x25
0x0014a380: bf bb 00 f9              str      xzr, [x29, #0x170]
0x0014a384: bf bf 00 f9              str      xzr, [x29, #0x178]
0x0014a388: 22 0c 19 f8              str      x2, [x1, #-0x70]!
0x0014a38c: bf c3 00 f9              str      xzr, [x29, #0x180]
0x0014a390: 66 0d 00 94              bl       #0x14d928                  ; -> _ZNSt6vectorIPN12stfaceunlock23SingleTempldateFileInfoESaIS2_EE19_M_emplace_back_auxIJS2_EEEvDpOT_
0x0014a394: 60 26 40 f9              ldr      x0, [x19, #0x48]
0x0014a398: e2 03 19 aa              mov      x2, x25
0x0014a39c: a1 57 40 f9              ldr      x1, [x29, #0xa8]
0x0014a3a0: e3 03 18 aa              mov      x3, x24
0x0014a3a4: a4 db 40 b9              ldr      w4, [x29, #0xd8]
0x0014a3a8: a5 73 03 91              add      x5, x29, #0xdc
0x0014a3ac: e6 03 1c aa              mov      x6, x28
0x0014a3b0: 27 00 80 52              mov      w7, #1
0x0014a3b4: 5f ac ff 97              bl       #0x135530                  ; -> _ZN12stfaceunlock14FeatureManager7AddUserERKSt10shared_ptrINS_17FaceVerifyManagerEERKSt6vectorIPNS_23SingleTempldateFileInfoESaIS8_EERKSsiPiPP13st_template_tb
0x0014a3b8: f9 03 00 2a              mov      w25, w0
0x0014a3bc: a0 bb 40 f9              ldr      x0, [x29, #0x170]
0x0014a3c0: f9 00 00 34              cbz      w25, #0x14a3dc
0x0014a3c4: 40 00 00 b4              cbz      x0, #0x14a3cc
0x0014a3c8: 8e 7b 16 94              bl       #0x6e9200                  ; -> _ZdlPv
0x0014a3cc: a0 53 40 f9              ldr      x0, [x29, #0xa0]
0x0014a3d0: f5 03 19 2a              mov      w21, w25
0x0014a3d4: a7 b5 ff 97              bl       #0x137a70                  ; -> _ZN12stfaceunlock23SingleTempldateFileInfoD2Ev
0x0014a3d8: 24 ff ff 17              b        #0x14a068                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x97c
0x0014a3dc: 40 00 00 b4              cbz      x0, #0x14a3e4
0x0014a3e0: 88 7b 16 94              bl       #0x6e9200                  ; -> _ZdlPv
0x0014a3e4: a0 53 40 f9              ldr      x0, [x29, #0xa0]
0x0014a3e8: a2 b5 ff 97              bl       #0x137a70                  ; -> _ZN12stfaceunlock23SingleTempldateFileInfoD2Ev
0x0014a3ec: 61 32 41 f9              ldr      x1, [x19, #0x260]
0x0014a3f0: 62 36 41 f9              ldr      x2, [x19, #0x268]
0x0014a3f4: 3f 00 02 eb              cmp      x1, x2
0x0014a3f8: 40 02 00 54              b.eq     #0x14a440
0x0014a3fc: f8 03 01 aa              mov      x24, x1
0x0014a400: 1c 03 40 f9              ldr      x28, [x24]
0x0014a404: 3c 01 00 b4              cbz      x28, #0x14a428
0x0014a408: e0 03 1c aa              mov      x0, x28
0x0014a40c: a2 57 00 f9              str      x2, [x29, #0xa8]
0x0014a410: a1 5f 00 f9              str      x1, [x29, #0xb8]
0x0014a414: 97 b5 ff 97              bl       #0x137a70                  ; -> _ZN12stfaceunlock23SingleTempldateFileInfoD2Ev
0x0014a418: e0 03 1c aa              mov      x0, x28
0x0014a41c: 79 7b 16 94              bl       #0x6e9200                  ; -> _ZdlPv
0x0014a420: a2 57 40 f9              ldr      x2, [x29, #0xa8]
0x0014a424: a1 5f 40 f9              ldr      x1, [x29, #0xb8]
0x0014a428: 18 23 00 91              add      x24, x24, #8
0x0014a42c: 5f 00 18 eb              cmp      x2, x24
0x0014a430: 81 fe ff 54              b.ne     #0x14a400
0x0014a434: e0 03 01 aa              mov      x0, x1
0x0014a438: 60 36 01 f9              str      x0, [x19, #0x268]
0x0014a43c: 99 00 00 35              cbnz     w25, #0x14a44c
0x0014a440: a0 df 40 b9              ldr      w0, [x29, #0xdc]
0x0014a444: 40 03 00 b9              str      w0, [x26]
0x0014a448: 08 ff ff 17              b        #0x14a068                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x97c
0x0014a44c: f5 03 19 2a              mov      w21, w25
0x0014a450: 06 ff ff 17              b        #0x14a068                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x97c
0x0014a454: f5 7d 80 12              mov      w21, #-0x3f0
0x0014a458: 9b ff ff 17              b        #0x14a2c4                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xbd8
0x0014a45c: c0 30 00 d0              adrp     x0, #0x764000
0x0014a460: 21 e3 12 91              add      x1, x25, #0x4b8
0x0014a464: 00 c0 23 91              add      x0, x0, #0x8f0
0x0014a468: 22 7b 80 52              mov      w2, #0x3d9
0x0014a46c: 20 d2 ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0014a470: 75 00 80 12              mov      w21, #-4
0x0014a474: fd fe ff 17              b        #0x14a068                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x97c
0x0014a478: 55 00 80 12              mov      w21, #-3
0x0014a47c: 93 ff ff 17              b        #0x14a2c8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xbdc
0x0014a480: 44 00 80 52              mov      w4, #2
0x0014a484: a1 43 07 91              add      x1, x29, #0x1d0
0x0014a488: bf bb 00 f9              str      xzr, [x29, #0x170]
0x0014a48c: 42 0c c4 1a              sdiv     w2, w2, w4
0x0014a490: bf bf 00 f9              str      xzr, [x29, #0x178]
0x0014a494: bf c3 00 f9              str      xzr, [x29, #0x180]
0x0014a498: b9 5f 40 f9              ldr      x25, [x29, #0xb8]
0x0014a49c: e0 03 19 aa              mov      x0, x25
0x0014a4a0: 62 d8 62 f8              ldr      x2, [x3, w2, sxtw #3]
0x0014a4a4: 22 0c 19 f8              str      x2, [x1, #-0x70]!
0x0014a4a8: 20 0d 00 94              bl       #0x14d928                  ; -> _ZNSt6vectorIPN12stfaceunlock23SingleTempldateFileInfoESaIS2_EE19_M_emplace_back_auxIJS2_EEEvDpOT_
0x0014a4ac: 60 26 40 f9              ldr      x0, [x19, #0x48]
0x0014a4b0: e2 03 19 aa              mov      x2, x25
0x0014a4b4: a1 57 40 f9              ldr      x1, [x29, #0xa8]
0x0014a4b8: a5 73 03 91              add      x5, x29, #0xdc
0x0014a4bc: a4 db 40 b9              ldr      w4, [x29, #0xd8]
0x0014a4c0: e6 03 1c aa              mov      x6, x28
0x0014a4c4: a3 47 40 f9              ldr      x3, [x29, #0x88]
0x0014a4c8: 27 00 80 52              mov      w7, #1
0x0014a4cc: 19 ac ff 97              bl       #0x135530                  ; -> _ZN12stfaceunlock14FeatureManager7AddUserERKSt10shared_ptrINS_17FaceVerifyManagerEERKSt6vectorIPNS_23SingleTempldateFileInfoESaIS8_EERKSsiPiPP13st_template_tb
0x0014a4d0: f9 03 00 2a              mov      w25, w0
0x0014a4d4: a0 bb 40 f9              ldr      x0, [x29, #0x170]
0x0014a4d8: 40 00 00 b4              cbz      x0, #0x14a4e0
0x0014a4dc: 49 7b 16 94              bl       #0x6e9200                  ; -> _ZdlPv
0x0014a4e0: b8 00 00 b4              cbz      x24, #0x14a4f4
0x0014a4e4: e0 03 18 aa              mov      x0, x24
0x0014a4e8: 62 b5 ff 97              bl       #0x137a70                  ; -> _ZN12stfaceunlock23SingleTempldateFileInfoD2Ev
0x0014a4ec: e0 03 18 aa              mov      x0, x24
0x0014a4f0: 44 7b 16 94              bl       #0x6e9200                  ; -> _ZdlPv
0x0014a4f4: a0 53 40 f9              ldr      x0, [x29, #0xa0]
0x0014a4f8: 5e b5 ff 97              bl       #0x137a70                  ; -> _ZN12stfaceunlock23SingleTempldateFileInfoD2Ev
0x0014a4fc: 60 36 41 f9              ldr      x0, [x19, #0x268]
0x0014a500: 61 32 41 f9              ldr      x1, [x19, #0x260]
0x0014a504: e2 03 00 aa              mov      x2, x0
0x0014a508: 3f 00 00 eb              cmp      x1, x0
0x0014a50c: 81 f7 ff 54              b.ne     #0x14a3fc
0x0014a510: ca ff ff 17              b        #0x14a438                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xd4c
0x0014a514: a1 bb 40 f9              ldr      x1, [x29, #0x170]
0x0014a518: f3 03 00 aa              mov      x19, x0
0x0014a51c: 61 00 00 b4              cbz      x1, #0x14a528
0x0014a520: e0 03 01 aa              mov      x0, x1
0x0014a524: 37 7b 16 94              bl       #0x6e9200                  ; -> _ZdlPv
0x0014a528: b8 00 00 b4              cbz      x24, #0x14a53c
0x0014a52c: e0 03 18 aa              mov      x0, x24
0x0014a530: 50 b5 ff 97              bl       #0x137a70                  ; -> _ZN12stfaceunlock23SingleTempldateFileInfoD2Ev
0x0014a534: e0 03 18 aa              mov      x0, x24
0x0014a538: 32 7b 16 94              bl       #0x6e9200                  ; -> _ZdlPv
0x0014a53c: a0 53 40 f9              ldr      x0, [x29, #0xa0]
0x0014a540: 4c b5 ff 97              bl       #0x137a70                  ; -> _ZN12stfaceunlock23SingleTempldateFileInfoD2Ev
0x0014a544: a0 5b 40 f9              ldr      x0, [x29, #0xb0]
0x0014a548: 40 00 00 b4              cbz      x0, #0x14a550
0x0014a54c: 1c 6f ff 97              bl       #0x1261bc                  ; -> _ZN12stfaceunlock17FaceVerifyManager14ReleaseFeatureEP12st_feature_t
0x0014a550: e0 03 1b aa              mov      x0, x27
0x0014a554: 2d 7b 16 94              bl       #0x6e9208                  ; -> _ZdaPv
0x0014a558: a0 67 40 f9              ldr      x0, [x29, #0xc8]
0x0014a55c: a1 c3 40 b9              ldr      w1, [x29, #0xc0]
0x0014a560: 74 e7 ff 97              bl       #0x144330                  ; -> _ZN12stfaceunlock17FaceDetectManager22ReleaseDetectionResultEP9st_face_ti
0x0014a564: 31 80 17 94              bl       #0x72a628                  ; -> _ZNSt6chrono3_V212steady_clock3nowEv
0x0014a568: 00 00 14 cb              sub      x0, x0, x20
0x0014a56c: 04 48 88 d2              mov      x4, #0x4240
0x0014a570: e1 a2 25 91              add      x1, x23, #0x968
0x0014a574: e4 01 a0 f2              movk     x4, #0xf, lsl #16
0x0014a578: 62 05 80 52              mov      w2, #0x2b
0x0014a57c: c3 42 09 91              add      x3, x22, #0x250
0x0014a580: 04 0c c4 9a              sdiv     x4, x0, x4
0x0014a584: a0 30 00 f0              adrp     x0, #0x761000
0x0014a588: 00 40 2b 91              add      x0, x0, #0xad0
0x0014a58c: 95 d1 ff 97              bl       #0x13ebe0                  ; -> _Z4LogWPKcz
0x0014a590: e0 03 13 aa              mov      x0, x19
0x0014a594: 48 17 18 94              bl       #0x7502b4                  ; -> _Unwind_Resume
0x0014a598: f3 03 00 aa              mov      x19, x0
0x0014a59c: e3 ff ff 17              b        #0x14a528                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xe3c
0x0014a5a0: 20 00 80 52              mov      w0, #1
0x0014a5a4: 65 a1 17 94              bl       #0x732b38                  ; -> _ZSt20__throw_system_errori
0x0014a5a8: 64 a1 17 94              bl       #0x732b38                  ; -> _ZSt20__throw_system_errori
0x0014a5ac: 01 6f 02 90              adrp     x1, #0x4f2a000
0x0014a5b0: f3 03 00 aa              mov      x19, x0
0x0014a5b4: a0 df 40 f9              ldr      x0, [x29, #0x1b8]
0x0014a5b8: 21 f8 47 f9              ldr      x1, [x1, #0xff0]
0x0014a5bc: 00 60 00 d1              sub      x0, x0, #0x18
0x0014a5c0: 1f 00 01 eb              cmp      x0, x1
0x0014a5c4: 00 fc ff 54              b.eq     #0x14a544
0x0014a5c8: a1 5f 40 f9              ldr      x1, [x29, #0xb8]
0x0014a5cc: 3d ec ff 97              bl       #0x1456c0                  ; -> _ZNSs4_Rep10_M_disposeERKSaIcE.part.26
0x0014a5d0: dd ff ff 17              b        #0x14a544                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xe58
0x0014a5d4: f3 03 00 aa              mov      x19, x0
0x0014a5d8: d5 ff ff 17              b        #0x14a52c                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xe40
0x0014a5dc: a1 bb 40 f9              ldr      x1, [x29, #0x170]
0x0014a5e0: f3 03 00 aa              mov      x19, x0
0x0014a5e4: c1 fa ff b4              cbz      x1, #0x14a53c
0x0014a5e8: e0 03 01 aa              mov      x0, x1
0x0014a5ec: 05 7b 16 94              bl       #0x6e9200                  ; -> _ZdlPv
0x0014a5f0: d3 ff ff 17              b        #0x14a53c                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xe50
0x0014a5f4: 60 32 41 f9              ldr      x0, [x19, #0x260]
0x0014a5f8: 21 00 00 cb              sub      x1, x1, x0
0x0014a5fc: 21 fc 43 93              asr      x1, x1, #3
0x0014a600: c1 08 00 b4              cbz      x1, #0x14a718
0x0014a604: 22 f8 7f d3              lsl      x2, x1, #1
0x0014a608: 03 00 fc 92              mov      x3, #0x1fffffffffffffff
0x0014a60c: 3f 00 02 eb              cmp      x1, x2
0x0014a610: 89 07 00 54              b.ls     #0x14a700
0x0014a614: 60 f0 7d d3              lsl      x0, x3, #3
0x0014a618: a0 4b 00 f9              str      x0, [x29, #0x90]
0x0014a61c: 76 81 16 94              bl       #0x6eabf4                  ; -> _Znwm
0x0014a620: e1 03 00 aa              mov      x1, x0
0x0014a624: 60 32 41 f9              ldr      x0, [x19, #0x260]
0x0014a628: 64 36 41 f9              ldr      x4, [x19, #0x268]
0x0014a62c: 82 00 00 cb              sub      x2, x4, x0
0x0014a630: 3f 00 02 ab              cmn      x1, x2
0x0014a634: 60 00 00 54              b.eq     #0x14a640
0x0014a638: 38 68 22 f8              str      x24, [x1, x2]
0x0014a63c: 18 00 80 d2              mov      x24, #0
0x0014a640: e3 03 01 aa              mov      x3, x1
0x0014a644: 07 00 00 14              b        #0x14a660                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xf74
0x0014a648: 83 00 00 b4              cbz      x3, #0x14a658
0x0014a64c: 05 00 40 f9              ldr      x5, [x0]
0x0014a650: 1f 00 00 f9              str      xzr, [x0]
0x0014a654: 65 00 00 f9              str      x5, [x3]
0x0014a658: 00 20 00 91              add      x0, x0, #8
0x0014a65c: 63 20 00 91              add      x3, x3, #8
0x0014a660: 9f 00 00 eb              cmp      x4, x0
0x0014a664: 21 ff ff 54              b.ne     #0x14a648
0x0014a668: a0 4f 40 f9              ldr      x0, [x29, #0x98]
0x0014a66c: 42 f0 7d 92              and      x2, x2, #0xfffffffffffffff8
0x0014a670: 42 20 00 91              add      x2, x2, #8
0x0014a674: 63 32 41 f9              ldr      x3, [x19, #0x260]
0x0014a678: 04 04 40 f9              ldr      x4, [x0, #8]
0x0014a67c: 20 00 02 8b              add      x0, x1, x2
0x0014a680: a0 43 00 f9              str      x0, [x29, #0x80]
0x0014a684: 9f 00 03 eb              cmp      x4, x3
0x0014a688: 20 02 00 54              b.eq     #0x14a6cc
0x0014a68c: 62 00 40 f9              ldr      x2, [x3]
0x0014a690: a2 01 00 b4              cbz      x2, #0x14a6c4
0x0014a694: e0 03 02 aa              mov      x0, x2
0x0014a698: a3 33 00 f9              str      x3, [x29, #0x60]
0x0014a69c: a4 37 00 f9              str      x4, [x29, #0x68]
0x0014a6a0: a1 3b 00 f9              str      x1, [x29, #0x70]
0x0014a6a4: a2 3f 00 f9              str      x2, [x29, #0x78]
0x0014a6a8: f2 b4 ff 97              bl       #0x137a70                  ; -> _ZN12stfaceunlock23SingleTempldateFileInfoD2Ev
0x0014a6ac: a2 3f 40 f9              ldr      x2, [x29, #0x78]
0x0014a6b0: e0 03 02 aa              mov      x0, x2
0x0014a6b4: d3 7a 16 94              bl       #0x6e9200                  ; -> _ZdlPv
0x0014a6b8: a3 33 40 f9              ldr      x3, [x29, #0x60]
0x0014a6bc: a4 37 40 f9              ldr      x4, [x29, #0x68]
0x0014a6c0: a1 3b 40 f9              ldr      x1, [x29, #0x70]
0x0014a6c4: 63 20 00 91              add      x3, x3, #8
0x0014a6c8: ef ff ff 17              b        #0x14a684                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xf98
0x0014a6cc: 60 32 41 f9              ldr      x0, [x19, #0x260]
0x0014a6d0: 80 00 00 b4              cbz      x0, #0x14a6e0
0x0014a6d4: a1 3f 00 f9              str      x1, [x29, #0x78]
0x0014a6d8: ca 7a 16 94              bl       #0x6e9200                  ; -> _ZdlPv
0x0014a6dc: a1 3f 40 f9              ldr      x1, [x29, #0x78]
0x0014a6e0: a0 4b 40 f9              ldr      x0, [x29, #0x90]
0x0014a6e4: 61 32 01 f9              str      x1, [x19, #0x260]
0x0014a6e8: 21 00 00 8b              add      x1, x1, x0
0x0014a6ec: a0 4f 40 f9              ldr      x0, [x29, #0x98]
0x0014a6f0: a2 43 40 f9              ldr      x2, [x29, #0x80]
0x0014a6f4: 02 04 00 f9              str      x2, [x0, #8]
0x0014a6f8: 01 08 00 f9              str      x1, [x0, #0x10]
0x0014a6fc: d9 fe ff 17              b        #0x14a260                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xb74
0x0014a700: 5f 00 03 eb              cmp      x2, x3
0x0014a704: 88 f8 ff 54              b.hi     #0x14a614
0x0014a708: c2 00 00 b5              cbnz     x2, #0x14a720
0x0014a70c: 01 00 80 d2              mov      x1, #0
0x0014a710: bf 4b 00 f9              str      xzr, [x29, #0x90]
0x0014a714: c5 ff ff 17              b        #0x14a628                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xf3c
0x0014a718: 23 00 80 d2              mov      x3, #1
0x0014a71c: be ff ff 17              b        #0x14a614                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xf28
0x0014a720: e3 03 02 aa              mov      x3, x2
0x0014a724: bc ff ff 17              b        #0x14a614                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xf28
0x0014a728: f3 03 00 aa              mov      x19, x0
0x0014a72c: a0 5b 40 f9              ldr      x0, [x29, #0xb0]
0x0014a730: 36 09 00 94              bl       #0x14cc08                  ; -> _ZNSt11unique_lockISt5mutexED2Ev
0x0014a734: 89 ff ff 17              b        #0x14a558                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xe6c
0x0014a738: f3 03 00 aa              mov      x19, x0
0x0014a73c: 82 ff ff 17              b        #0x14a544                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xe58
0x0014a740: a3 d3 40 f9              ldr      x3, [x29, #0x1a0]
0x0014a744: f3 03 00 aa              mov      x19, x0
0x0014a748: 83 f0 ff b4              cbz      x3, #0x14a558
0x0014a74c: a0 53 40 f9              ldr      x0, [x29, #0xa0]
0x0014a750: 62 00 80 52              mov      w2, #3
0x0014a754: e1 03 00 aa              mov      x1, x0
0x0014a758: 60 00 3f d6              blr      x3
0x0014a75c: 7f ff ff 17              b        #0x14a558                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xe6c
0x0014a760: a3 d3 40 f9              ldr      x3, [x29, #0x1a0]
0x0014a764: f3 03 00 aa              mov      x19, x0
0x0014a768: a3 00 00 b4              cbz      x3, #0x14a77c
0x0014a76c: a0 43 06 91              add      x0, x29, #0x190
0x0014a770: 62 00 80 52              mov      w2, #3
0x0014a774: e1 03 00 aa              mov      x1, x0
0x0014a778: 60 00 3f d6              blr      x3
0x0014a77c: f5 ee ff b4              cbz      x21, #0x14a558
0x0014a780: e0 03 15 aa              mov      x0, x21
0x0014a784: 9b 1e ff 97              bl       #0x1121f0                  ; -> _ZNSt16_Sp_counted_baseILN9__gnu_cxx12_Lock_policyE2EE10_M_releaseEv
0x0014a788: 74 ff ff 17              b        #0x14a558                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xe6c
0x0014a78c: 88 ff ff 17              b        #0x14a5ac                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xec0
0x0014a790: f3 03 00 aa              mov      x19, x0
0x0014a794: 71 ff ff 17              b        #0x14a558                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xe6c
0x0014a798: f3 03 00 aa              mov      x19, x0
0x0014a79c: 6d ff ff 17              b        #0x14a550                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xe64
0x0014a7a0: e6 a0 17 94              bl       #0x732b38                  ; -> _ZSt20__throw_system_errori
0x0014a7a4: 20 00 80 52              mov      w0, #1
0x0014a7a8: e4 a0 17 94              bl       #0x732b38                  ; -> _ZSt20__throw_system_errori
0x0014a7ac: e3 a0 17 94              bl       #0x732b38                  ; -> _ZSt20__throw_system_errori
0x0014a7b0: 20 00 80 52              mov      w0, #1
0x0014a7b4: e1 a0 17 94              bl       #0x732b38                  ; -> _ZSt20__throw_system_errori
0x0014a7b8: f3 03 00 aa              mov      x19, x0
0x0014a7bc: e0 03 19 aa              mov      x0, x25
0x0014a7c0: 12 09 00 94              bl       #0x14cc08                  ; -> _ZNSt11unique_lockISt5mutexED2Ev
0x0014a7c4: 65 ff ff 17              b        #0x14a558                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xe6c
0x0014a7c8: 20 00 80 52              mov      w0, #1
0x0014a7cc: db a0 17 94              bl       #0x732b38                  ; -> _ZSt20__throw_system_errori
0x0014a7d0: f3 03 00 aa              mov      x19, x0
0x0014a7d4: a0 5f 40 f9              ldr      x0, [x29, #0xb8]
0x0014a7d8: 0c 09 00 94              bl       #0x14cc08                  ; -> _ZNSt11unique_lockISt5mutexED2Ev
0x0014a7dc: 5f ff ff 17              b        #0x14a558                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xe6c
0x0014a7e0: f3 03 00 aa              mov      x19, x0
0x0014a7e4: 60 ff ff 17              b        #0x14a564                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xe78
0x0014a7e8: d4 a0 17 94              bl       #0x732b38                  ; -> _ZSt20__throw_system_errori
0x0014a7ec: 20 00 80 52              mov      w0, #1
0x0014a7f0: d2 a0 17 94              bl       #0x732b38                  ; -> _ZSt20__throw_system_errori
0x0014a7f4: f1 ff ff 17              b        #0x14a7b8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x10cc
0x0014a7f8: a3 c3 40 f9              ldr      x3, [x29, #0x180]
0x0014a7fc: f3 03 00 aa              mov      x19, x0
0x0014a800: 23 eb ff b4              cbz      x3, #0x14a564
0x0014a804: a0 5f 40 f9              ldr      x0, [x29, #0xb8]
0x0014a808: 62 00 80 52              mov      w2, #3
0x0014a80c: e1 03 00 aa              mov      x1, x0
0x0014a810: 60 00 3f d6              blr      x3
0x0014a814: 54 ff ff 17              b        #0x14a564                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xe78
0x0014a818: a3 c3 40 f9              ldr      x3, [x29, #0x180]
0x0014a81c: f3 03 00 aa              mov      x19, x0
0x0014a820: a3 00 00 b4              cbz      x3, #0x14a834
0x0014a824: a0 c3 05 91              add      x0, x29, #0x170
0x0014a828: 62 00 80 52              mov      w2, #3
0x0014a82c: e1 03 00 aa              mov      x1, x0
0x0014a830: 60 00 3f d6              blr      x3
0x0014a834: 95 e9 ff b4              cbz      x21, #0x14a564
0x0014a838: e0 03 15 aa              mov      x0, x21
0x0014a83c: 6d 1e ff 97              bl       #0x1121f0                  ; -> _ZNSt16_Sp_counted_baseILN9__gnu_cxx12_Lock_policyE2EE10_M_releaseEv
0x0014a840: 49 ff ff 17              b        #0x14a564                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xe78
0x0014a844: bd a0 17 94              bl       #0x732b38                  ; -> _ZSt20__throw_system_errori
0x0014a848: f3 03 00 aa              mov      x19, x0
0x0014a84c: e0 03 1a aa              mov      x0, x26
0x0014a850: ee 08 00 94              bl       #0x14cc08                  ; -> _ZNSt11unique_lockISt5mutexED2Ev
0x0014a854: 44 ff ff 17              b        #0x14a564                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0xe78
0x0014a858: 20 00 80 52              mov      w0, #1
0x0014a85c: b7 a0 17 94              bl       #0x732b38                  ; -> _ZSt20__throw_system_errori
0x0014a860: d6 ff ff 17              b        #0x14a7b8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x10cc
0x0014a864: b5 a0 17 94              bl       #0x732b38                  ; -> _ZSt20__throw_system_errori
0x0014a868: b4 a0 17 94              bl       #0x732b38                  ; -> _ZSt20__throw_system_errori
0x0014a86c: 20 00 80 52              mov      w0, #1
0x0014a870: b2 a0 17 94              bl       #0x732b38                  ; -> _ZSt20__throw_system_errori
0x0014a874: d1 ff ff 17              b        #0x14a7b8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t+0x10cc
