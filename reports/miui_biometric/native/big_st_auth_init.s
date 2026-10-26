; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : st_auth_init (0x10e3e8)
; start   : 0x10e3e8
; size    : 0x738
; bytes   : 0x738   instructions: 462
; generated: 2026-10-24T00:26:15

0x0010e3e8: ff 43 02 d1              sub      sp, sp, #0x90
0x0010e3ec: 23 32 00 90              adrp     x3, #0x752000
0x0010e3f0: 63 00 03 91              add      x3, x3, #0xc0
0x0010e3f4: e2 13 80 52              mov      w2, #0x9f
0x0010e3f8: 63 c0 20 91              add      x3, x3, #0x830
0x0010e3fc: fd 7b 01 a9              stp      x29, x30, [sp, #0x10]
0x0010e400: fd 43 00 91              add      x29, sp, #0x10
0x0010e404: f7 63 04 a9              stp      x23, x24, [sp, #0x40]
0x0010e408: 37 32 00 f0              adrp     x23, #0x755000
0x0010e40c: f8 03 00 aa              mov      x24, x0
0x0010e410: f5 5b 03 a9              stp      x21, x22, [sp, #0x30]
0x0010e414: 20 32 00 f0              adrp     x0, #0x755000
0x0010e418: f5 42 07 91              add      x21, x23, #0x1d0
0x0010e41c: f9 2b 00 f9              str      x25, [sp, #0x50]
0x0010e420: 00 c0 06 91              add      x0, x0, #0x1b0
0x0010e424: f9 03 01 2a              mov      w25, w1
0x0010e428: e1 03 15 aa              mov      x1, x21
0x0010e42c: f3 53 02 a9              stp      x19, x20, [sp, #0x20]
0x0010e430: 36 ee 02 90              adrp     x22, #0x5ed2000
0x0010e434: 65 c1 00 94              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x0010e438: c0 82 3d 91              add      x0, x22, #0xf60
0x0010e43c: 13 04 40 f9              ldr      x19, [x0, #8]
0x0010e440: 73 08 00 b4              cbz      x19, #0x10e54c
0x0010e444: 60 56 40 39              ldrb     w0, [x19, #0x15]
0x0010e448: 14 00 80 52              mov      w20, #0
0x0010e44c: e0 06 00 35              cbnz     w0, #0x10e528
0x0010e450: c6 70 02 f0              adrp     x6, #0x4f29000
0x0010e454: e0 70 02 90              adrp     x0, #0x4f2a000
0x0010e458: c3 70 02 f0              adrp     x3, #0x4f29000
0x0010e45c: c5 70 02 f0              adrp     x5, #0x4f29000
0x0010e460: e4 70 02 90              adrp     x4, #0x4f2a000
0x0010e464: e2 70 02 90              adrp     x2, #0x4f2a000
0x0010e468: c6 3c 40 f9              ldr      x6, [x6, #0x78]
0x0010e46c: 00 bc 41 f9              ldr      x0, [x0, #0x378]
0x0010e470: 63 6c 46 f9              ldr      x3, [x3, #0xcd8]
0x0010e474: c7 00 40 f9              ldr      x7, [x6]                   ; -> __6xx__android11_bcl_binary_data_size@got
0x0010e478: c6 70 02 f0              adrp     x6, #0x4f29000
0x0010e47c: a5 cc 40 f9              ldr      x5, [x5, #0x198]
0x0010e480: 84 b8 45 f9              ldr      x4, [x4, #0xb70]
0x0010e484: 01 00 40 f9              ldr      x1, [x0]                   ; -> __G78__android17_bcl_binary_data@got
0x0010e488: 63 00 40 f9              ldr      x3, [x3]                   ; -> __6xx__android11_bcl_binary_data_size@got
0x0010e48c: c6 54 41 f9              ldr      x6, [x6, #0x2a8]
0x0010e490: 60 02 40 f9              ldr      x0, [x19]
0x0010e494: a5 00 40 f9              ldr      x5, [x5]                   ; -> __6xx__android11_bcl_binary_data_size@got
0x0010e498: 42 4c 47 f9              ldr      x2, [x2, #0xe98]
0x0010e49c: e6 03 00 f9              str      x6, [sp]
0x0010e4a0: e6 03 04 aa              mov      x6, x4
0x0010e4a4: 98 dc 00 94              bl       #0x145704                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace18CreateDetectHandleEPKNS_5ModelEP21cv_model_net_config_tS3_S5_S3_S5_S3_S5_
0x0010e4a8: e3 03 00 2a              mov      w3, w0
0x0010e4ac: c0 0f 00 35              cbnz     w0, #0x10e6a4
0x0010e4b0: e1 70 02 90              adrp     x1, #0x4f2a000
0x0010e4b4: e2 70 02 90              adrp     x2, #0x4f2a000
0x0010e4b8: 60 02 40 f9              ldr      x0, [x19]
0x0010e4bc: 21 b8 40 f9              ldr      x1, [x1, #0x170]
0x0010e4c0: 42 e8 41 f9              ldr      x2, [x2, #0x3d0]
0x0010e4c4: 21 00 40 f9              ldr      x1, [x1]                   ; -> __G78__android17_bcl_binary_data@got
0x0010e4c8: 5e dd 00 94              bl       #0x145a40                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace20CreatehacknessHandleEPKNS_5ModelEP21cv_model_net_config_t
0x0010e4cc: e3 03 00 2a              mov      w3, w0
0x0010e4d0: 80 0f 00 35              cbnz     w0, #0x10e6c0
0x0010e4d4: e1 70 02 90              adrp     x1, #0x4f2a000
0x0010e4d8: 60 02 40 f9              ldr      x0, [x19]
0x0010e4dc: 21 08 41 f9              ldr      x1, [x1, #0x210]
0x0010e4e0: 20 00 40 bd              ldr      s0, [x1]                   ; -> __G78__android17_bcl_binary_data@got
0x0010e4e4: d9 e2 00 94              bl       #0x147048                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace17SetVerifyThreholdEf
0x0010e4e8: e3 03 00 2a              mov      w3, w0
0x0010e4ec: 80 0f 00 35              cbnz     w0, #0x10e6dc
0x0010e4f0: c1 70 02 f0              adrp     x1, #0x4f29000
0x0010e4f4: 60 02 40 f9              ldr      x0, [x19]
0x0010e4f8: 21 18 46 f9              ldr      x1, [x1, #0xc30]
0x0010e4fc: 20 00 40 bd              ldr      s0, [x1]                   ; -> __6xx__android11_bcl_binary_data_size@got
0x0010e500: eb e2 00 94              bl       #0x1470ac                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace19SetLivenessThreholdEf
0x0010e504: e3 03 00 2a              mov      w3, w0
0x0010e508: 60 10 00 35              cbnz     w0, #0x10e714
0x0010e50c: 60 02 40 f9              ldr      x0, [x19]
0x0010e510: e4 e7 00 94              bl       #0x1484a0                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace11StartThreadEv
0x0010e514: d6 82 3d 91              add      x22, x22, #0xf60
0x0010e518: 21 00 80 52              mov      w1, #1
0x0010e51c: 14 00 80 52              mov      w20, #0
0x0010e520: c0 06 40 f9              ldr      x0, [x22, #8]
0x0010e524: 01 54 00 39              strb     w1, [x0, #0x15]
0x0010e528: bf 43 00 d1              sub      sp, x29, #0x10
0x0010e52c: e0 03 14 2a              mov      w0, w20
0x0010e530: f3 53 42 a9              ldp      x19, x20, [sp, #0x20]
0x0010e534: f5 5b 43 a9              ldp      x21, x22, [sp, #0x30]
0x0010e538: f7 63 44 a9              ldp      x23, x24, [sp, #0x40]
0x0010e53c: f9 2b 40 f9              ldr      x25, [sp, #0x50]
0x0010e540: fd 7b 41 a9              ldp      x29, x30, [sp, #0x10]
0x0010e544: ff 43 02 91              add      sp, sp, #0x90
0x0010e548: c0 03 5f d6              ret      
0x0010e54c: c0 b2 47 f9              ldr      x0, [x22, #0xf60]
0x0010e550: 00 01 00 b4              cbz      x0, #0x10e570
0x0010e554: 01 28 40 f9              ldr      x1, [x0, #0x50]
0x0010e558: c1 0e 00 b4              cbz      x1, #0x10e730
0x0010e55c: 01 08 40 f9              ldr      x1, [x0, #0x10]            ; -> __G61__android16_bcl_binary_data@got
0x0010e560: 34 7e 80 12              mov      w20, #-0x3f2
0x0010e564: 21 fe ff b5              cbnz     x1, #0x10e528
0x0010e568: 00 18 40 f9              ldr      x0, [x0, #0x30]
0x0010e56c: e0 fd ff b5              cbnz     x0, #0x10e528
0x0010e570: c3 b8 ff 97              bl       #0xfc87c                   ; -> st_face_get_version
0x0010e574: e1 03 00 aa              mov      x1, x0
0x0010e578: e0 03 18 aa              mov      x0, x24
0x0010e57c: e7 09 01 94              bl       #0x150d18                  ; -> _ZN12stfaceunlock11AlgoManager17InitAlgorithmInfoEPKcS2_
0x0010e580: f4 03 00 2a              mov      w20, w0
0x0010e584: 20 fd ff 35              cbnz     w0, #0x10e528
0x0010e588: f4 70 02 90              adrp     x20, #0x4f2a000
0x0010e58c: 00 03 80 d2              mov      x0, #0x18
0x0010e590: 93 6e 46 f9              ldr      x19, [x20, #0xcd8]
0x0010e594: e1 03 13 aa              mov      x1, x19
0x0010e598: b3 71 17 94              bl       #0x6eac64                  ; -> _ZnwmRKSt9nothrow_t
0x0010e59c: f5 03 00 aa              mov      x21, x0
0x0010e5a0: c0 10 00 b4              cbz      x0, #0x10e7b8
0x0010e5a4: 1f 00 00 f9              str      xzr, [x0]
0x0010e5a8: e1 03 13 aa              mov      x1, x19
0x0010e5ac: 00 56 80 d2              mov      x0, #0x2b0
0x0010e5b0: bf 06 00 f9              str      xzr, [x21, #8]
0x0010e5b4: bf 52 00 39              strb     wzr, [x21, #0x14]
0x0010e5b8: bf 56 00 39              strb     wzr, [x21, #0x15]
0x0010e5bc: aa 71 17 94              bl       #0x6eac64                  ; -> _ZnwmRKSt9nothrow_t
0x0010e5c0: f3 03 00 aa              mov      x19, x0
0x0010e5c4: 20 0c 00 b4              cbz      x0, #0x10e748
0x0010e5c8: 68 eb 00 94              bl       #0x149368                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFaceC2Ev
0x0010e5cc: e0 03 13 aa              mov      x0, x19
0x0010e5d0: e1 03 18 aa              mov      x1, x24
0x0010e5d4: 2d e1 00 94              bl       #0x146a88                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace19SetTemplateFilePathEPKc
0x0010e5d8: f4 03 00 2a              mov      w20, w0
0x0010e5dc: e0 08 00 35              cbnz     w0, #0x10e6f8
0x0010e5e0: c6 70 02 f0              adrp     x6, #0x4f29000
0x0010e5e4: e1 70 02 90              adrp     x1, #0x4f2a000
0x0010e5e8: c3 70 02 f0              adrp     x3, #0x4f29000
0x0010e5ec: c5 70 02 f0              adrp     x5, #0x4f29000
0x0010e5f0: e4 70 02 90              adrp     x4, #0x4f2a000
0x0010e5f4: e2 70 02 90              adrp     x2, #0x4f2a000
0x0010e5f8: c6 3c 40 f9              ldr      x6, [x6, #0x78]
0x0010e5fc: e0 03 13 aa              mov      x0, x19
0x0010e600: 21 bc 41 f9              ldr      x1, [x1, #0x378]
0x0010e604: 63 6c 46 f9              ldr      x3, [x3, #0xcd8]
0x0010e608: c7 00 40 f9              ldr      x7, [x6]                   ; -> __6xx__android11_bcl_binary_data_size@got
0x0010e60c: c6 70 02 f0              adrp     x6, #0x4f29000
0x0010e610: a5 cc 40 f9              ldr      x5, [x5, #0x198]
0x0010e614: 84 b8 45 f9              ldr      x4, [x4, #0xb70]
0x0010e618: c6 54 41 f9              ldr      x6, [x6, #0x2a8]
0x0010e61c: 21 00 40 f9              ldr      x1, [x1]                   ; -> __G78__android17_bcl_binary_data@got
0x0010e620: 63 00 40 f9              ldr      x3, [x3]                   ; -> __6xx__android11_bcl_binary_data_size@got
0x0010e624: a5 00 40 f9              ldr      x5, [x5]                   ; -> __6xx__android11_bcl_binary_data_size@got
0x0010e628: 42 4c 47 f9              ldr      x2, [x2, #0xe98]
0x0010e62c: e6 03 00 f9              str      x6, [sp]
0x0010e630: e6 03 04 aa              mov      x6, x4
0x0010e634: 34 dc 00 94              bl       #0x145704                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace18CreateDetectHandleEPKNS_5ModelEP21cv_model_net_config_tS3_S5_S3_S5_S3_S5_
0x0010e638: f4 03 00 2a              mov      w20, w0
0x0010e63c: 40 09 00 34              cbz      w0, #0x10e764
0x0010e640: 20 32 00 f0              adrp     x0, #0x755000
0x0010e644: e1 42 07 91              add      x1, x23, #0x1d0
0x0010e648: 00 00 19 91              add      x0, x0, #0x640
0x0010e64c: e2 19 80 52              mov      w2, #0xcf
0x0010e650: e3 03 14 2a              mov      w3, w20
0x0010e654: a6 c1 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010e658: e0 03 13 aa              mov      x0, x19
0x0010e65c: 87 f0 00 94              bl       #0x14a878                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFaceD2Ev
0x0010e660: e0 03 13 aa              mov      x0, x19
0x0010e664: e7 6a 17 94              bl       #0x6e9200                  ; -> _ZdlPv
0x0010e668: b3 06 40 f9              ldr      x19, [x21, #8]
0x0010e66c: 73 01 00 b4              cbz      x19, #0x10e698
0x0010e670: c0 70 02 f0              adrp     x0, #0x4f29000
0x0010e674: 00 94 47 f9              ldr      x0, [x0, #0xf28]           ; -> pthread_create@got
0x0010e678: e0 0a 00 b4              cbz      x0, #0x10e7d4
0x0010e67c: 60 22 00 91              add      x0, x19, #8
0x0010e680: 01 fc 5f 88              ldaxr    w1, [x0]
0x0010e684: 22 04 00 51              sub      w2, w1, #1
0x0010e688: 02 fc 03 88              stlxr    w3, w2, [x0]
0x0010e68c: a3 ff ff 35              cbnz     w3, #0x10e680
0x0010e690: 3f 04 00 71              cmp      w1, #1
0x0010e694: 60 0e 00 54              b.eq     #0x10e860
0x0010e698: e0 03 15 aa              mov      x0, x21
0x0010e69c: d9 6a 17 94              bl       #0x6e9200                  ; -> _ZdlPv
0x0010e6a0: a2 ff ff 17              b        #0x10e528                  ; -> st_auth_init+0x140
0x0010e6a4: 20 32 00 f0              adrp     x0, #0x755000
0x0010e6a8: e1 03 15 aa              mov      x1, x21
0x0010e6ac: 00 00 19 91              add      x0, x0, #0x640
0x0010e6b0: 62 23 80 52              mov      w2, #0x11b
0x0010e6b4: f4 03 03 2a              mov      w20, w3
0x0010e6b8: 8d c1 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010e6bc: 9b ff ff 17              b        #0x10e528                  ; -> st_auth_init+0x140
0x0010e6c0: 20 32 00 f0              adrp     x0, #0x755000
0x0010e6c4: e1 03 15 aa              mov      x1, x21
0x0010e6c8: 00 c0 1a 91              add      x0, x0, #0x6b0
0x0010e6cc: 42 24 80 52              mov      w2, #0x122
0x0010e6d0: f4 03 03 2a              mov      w20, w3
0x0010e6d4: 86 c1 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010e6d8: 94 ff ff 17              b        #0x10e528                  ; -> st_auth_init+0x140
0x0010e6dc: 20 32 00 f0              adrp     x0, #0x755000
0x0010e6e0: e1 03 15 aa              mov      x1, x21
0x0010e6e4: 00 a0 1c 91              add      x0, x0, #0x728
0x0010e6e8: 02 25 80 52              mov      w2, #0x128
0x0010e6ec: f4 03 03 2a              mov      w20, w3
0x0010e6f0: 7f c1 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010e6f4: 8d ff ff 17              b        #0x10e528                  ; -> st_auth_init+0x140
0x0010e6f8: 20 32 00 f0              adrp     x0, #0x755000
0x0010e6fc: e1 42 07 91              add      x1, x23, #0x1d0
0x0010e700: 00 00 18 91              add      x0, x0, #0x600
0x0010e704: e2 18 80 52              mov      w2, #0xc7
0x0010e708: e3 03 14 2a              mov      w3, w20
0x0010e70c: 78 c1 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010e710: d2 ff ff 17              b        #0x10e658                  ; -> st_auth_init+0x270
0x0010e714: 20 32 00 f0              adrp     x0, #0x755000
0x0010e718: e1 03 15 aa              mov      x1, x21
0x0010e71c: 00 80 1d 91              add      x0, x0, #0x760
0x0010e720: a2 25 80 52              mov      w2, #0x12d
0x0010e724: f4 03 03 2a              mov      w20, w3
0x0010e728: 71 c1 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010e72c: 7f ff ff 17              b        #0x10e528                  ; -> st_auth_init+0x140
0x0010e730: 01 08 40 f9              ldr      x1, [x0, #0x10]
0x0010e734: 61 00 00 b4              cbz      x1, #0x10e740
0x0010e738: 00 18 40 f9              ldr      x0, [x0, #0x30]
0x0010e73c: a0 f1 ff b5              cbnz     x0, #0x10e570
0x0010e740: d4 7d 80 12              mov      w20, #-0x3ef
0x0010e744: 79 ff ff 17              b        #0x10e528                  ; -> st_auth_init+0x140
0x0010e748: 20 32 00 f0              adrp     x0, #0x755000
0x0010e74c: e1 42 07 91              add      x1, x23, #0x1d0
0x0010e750: 00 c0 21 91              add      x0, x0, #0x870
0x0010e754: e2 17 80 52              mov      w2, #0xbf
0x0010e758: 65 c1 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010e75c: 54 00 80 12              mov      w20, #-3
0x0010e760: c2 ff ff 17              b        #0x10e668                  ; -> st_auth_init+0x280
0x0010e764: c1 70 02 d0              adrp     x1, #0x4f28000
0x0010e768: c3 70 02 f0              adrp     x3, #0x4f29000
0x0010e76c: c2 70 02 f0              adrp     x2, #0x4f29000
0x0010e770: c4 70 02 f0              adrp     x4, #0x4f29000
0x0010e774: e0 03 13 aa              mov      x0, x19
0x0010e778: 21 e8 44 f9              ldr      x1, [x1, #0x9d0]
0x0010e77c: 63 bc 44 f9              ldr      x3, [x3, #0x978]
0x0010e780: 42 18 40 f9              ldr      x2, [x2, #0x30]
0x0010e784: 21 00 40 f9              ldr      x1, [x1]
0x0010e788: 63 00 40 f9              ldr      x3, [x3]                   ; -> __6xx__android11_bcl_binary_data_size@got
0x0010e78c: 84 b4 43 f9              ldr      x4, [x4, #0x768]
0x0010e790: 14 dd 00 94              bl       #0x145be0                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace18CreateOcularHandleEPKNS_5ModelEP21cv_model_net_config_tS3_S5_
0x0010e794: f4 03 00 2a              mov      w20, w0
0x0010e798: 40 04 00 34              cbz      w0, #0x10e820
0x0010e79c: 20 32 00 f0              adrp     x0, #0x755000
0x0010e7a0: e1 42 07 91              add      x1, x23, #0x1d0
0x0010e7a4: 00 e0 19 91              add      x0, x0, #0x678
0x0010e7a8: 02 1b 80 52              mov      w2, #0xd8
0x0010e7ac: e3 03 14 2a              mov      w3, w20
0x0010e7b0: 4f c1 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010e7b4: a9 ff ff 17              b        #0x10e658                  ; -> st_auth_init+0x270
0x0010e7b8: 20 32 00 f0              adrp     x0, #0x755000
0x0010e7bc: e1 42 07 91              add      x1, x23, #0x1d0
0x0010e7c0: 00 20 21 91              add      x0, x0, #0x848
0x0010e7c4: 02 17 80 52              mov      w2, #0xb8
0x0010e7c8: 54 00 80 12              mov      w20, #-3
0x0010e7cc: 48 c1 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010e7d0: 56 ff ff 17              b        #0x10e528                  ; -> st_auth_init+0x140
0x0010e7d4: 60 0a 40 b9              ldr      w0, [x19, #8]
0x0010e7d8: 01 04 00 51              sub      w1, w0, #1
0x0010e7dc: 1f 04 00 71              cmp      w0, #1
0x0010e7e0: 61 0a 00 b9              str      w1, [x19, #8]
0x0010e7e4: a1 f5 ff 54              b.ne     #0x10e698
0x0010e7e8: 61 02 40 f9              ldr      x1, [x19]
0x0010e7ec: e0 03 13 aa              mov      x0, x19
0x0010e7f0: 21 08 40 f9              ldr      x1, [x1, #0x10]
0x0010e7f4: 20 00 3f d6              blr      x1
0x0010e7f8: 60 0e 40 b9              ldr      w0, [x19, #0xc]
0x0010e7fc: 01 04 00 51              sub      w1, w0, #1
0x0010e800: 61 0e 00 b9              str      w1, [x19, #0xc]
0x0010e804: 1f 04 00 71              cmp      w0, #1
0x0010e808: 81 f4 ff 54              b.ne     #0x10e698
0x0010e80c: 61 02 40 f9              ldr      x1, [x19]
0x0010e810: e0 03 13 aa              mov      x0, x19
0x0010e814: 21 0c 40 f9              ldr      x1, [x1, #0x18]
0x0010e818: 20 00 3f d6              blr      x1
0x0010e81c: 9f ff ff 17              b        #0x10e698                  ; -> st_auth_init+0x2b0
0x0010e820: e1 70 02 90              adrp     x1, #0x4f2a000
0x0010e824: e2 70 02 90              adrp     x2, #0x4f2a000
0x0010e828: e0 03 13 aa              mov      x0, x19
0x0010e82c: 21 b8 40 f9              ldr      x1, [x1, #0x170]
0x0010e830: 42 e8 41 f9              ldr      x2, [x2, #0x3d0]
0x0010e834: 21 00 40 f9              ldr      x1, [x1]                   ; -> __G78__android17_bcl_binary_data@got
0x0010e838: 82 dc 00 94              bl       #0x145a40                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace20CreatehacknessHandleEPKNS_5ModelEP21cv_model_net_config_t
0x0010e83c: f4 03 00 2a              mov      w20, w0
0x0010e840: 40 02 00 34              cbz      w0, #0x10e888
0x0010e844: 20 32 00 f0              adrp     x0, #0x755000
0x0010e848: e1 42 07 91              add      x1, x23, #0x1d0
0x0010e84c: 00 c0 1a 91              add      x0, x0, #0x6b0
0x0010e850: e2 1b 80 52              mov      w2, #0xdf
0x0010e854: e3 03 14 2a              mov      w3, w20
0x0010e858: 25 c1 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010e85c: 7f ff ff 17              b        #0x10e658                  ; -> st_auth_init+0x270
0x0010e860: 61 02 40 f9              ldr      x1, [x19]
0x0010e864: e0 03 13 aa              mov      x0, x19
0x0010e868: 21 08 40 f9              ldr      x1, [x1, #0x10]            ; -> __G61__android16_bcl_binary_data@got
0x0010e86c: 20 00 3f d6              blr      x1
0x0010e870: 61 32 00 91              add      x1, x19, #0xc
0x0010e874: 20 fc 5f 88              ldaxr    w0, [x1]
0x0010e878: 02 04 00 51              sub      w2, w0, #1
0x0010e87c: 22 fc 03 88              stlxr    w3, w2, [x1]
0x0010e880: 23 fc ff 34              cbz      w3, #0x10e804
0x0010e884: fc ff ff 17              b        #0x10e874                  ; -> st_auth_init+0x48c
0x0010e888: e1 70 02 90              adrp     x1, #0x4f2a000
0x0010e88c: c2 70 02 f0              adrp     x2, #0x4f29000
0x0010e890: e0 03 13 aa              mov      x0, x19
0x0010e894: 21 c0 46 f9              ldr      x1, [x1, #0xd80]
0x0010e898: 42 a4 44 f9              ldr      x2, [x2, #0x948]
0x0010e89c: 21 00 40 f9              ldr      x1, [x1]                   ; -> __G78__android17_bcl_binary_data@got
0x0010e8a0: 01 dc 00 94              bl       #0x1458a4                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace18CreateVeriyfHandleEPKNS_5ModelEP21cv_model_net_config_t
0x0010e8a4: f4 03 00 2a              mov      w20, w0
0x0010e8a8: 00 01 00 34              cbz      w0, #0x10e8c8
0x0010e8ac: 20 32 00 f0              adrp     x0, #0x755000
0x0010e8b0: e1 42 07 91              add      x1, x23, #0x1d0
0x0010e8b4: 00 c0 1b 91              add      x0, x0, #0x6f0
0x0010e8b8: a2 1c 80 52              mov      w2, #0xe5
0x0010e8bc: e3 03 14 2a              mov      w3, w20
0x0010e8c0: 0b c1 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010e8c4: 65 ff ff 17              b        #0x10e658                  ; -> st_auth_init+0x270
0x0010e8c8: e1 70 02 90              adrp     x1, #0x4f2a000
0x0010e8cc: e0 03 13 aa              mov      x0, x19
0x0010e8d0: 21 08 41 f9              ldr      x1, [x1, #0x210]
0x0010e8d4: 20 00 40 bd              ldr      s0, [x1]                   ; -> __G78__android17_bcl_binary_data@got
0x0010e8d8: dc e1 00 94              bl       #0x147048                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace17SetVerifyThreholdEf
0x0010e8dc: f4 03 00 2a              mov      w20, w0
0x0010e8e0: 00 01 00 34              cbz      w0, #0x10e900
0x0010e8e4: 20 32 00 f0              adrp     x0, #0x755000
0x0010e8e8: e1 42 07 91              add      x1, x23, #0x1d0
0x0010e8ec: 00 a0 1c 91              add      x0, x0, #0x728
0x0010e8f0: 62 1d 80 52              mov      w2, #0xeb
0x0010e8f4: e3 03 14 2a              mov      w3, w20
0x0010e8f8: fd c0 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010e8fc: 57 ff ff 17              b        #0x10e658                  ; -> st_auth_init+0x270
0x0010e900: c1 70 02 f0              adrp     x1, #0x4f29000
0x0010e904: e0 03 13 aa              mov      x0, x19
0x0010e908: 21 18 46 f9              ldr      x1, [x1, #0xc30]
0x0010e90c: 20 00 40 bd              ldr      s0, [x1]                   ; -> __6xx__android11_bcl_binary_data_size@got
0x0010e910: e7 e1 00 94              bl       #0x1470ac                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace19SetLivenessThreholdEf
0x0010e914: f4 03 00 2a              mov      w20, w0
0x0010e918: e0 01 00 35              cbnz     w0, #0x10e954
0x0010e91c: c1 b2 47 f9              ldr      x1, [x22, #0xf60]
0x0010e920: 41 04 00 b4              cbz      x1, #0x10e9a8
0x0010e924: 22 30 40 f9              ldr      x2, [x1, #0x60]
0x0010e928: e0 03 13 aa              mov      x0, x19
0x0010e92c: 35 e1 00 94              bl       #0x146e00                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace12SetFetchFuncERKSt8functionIFiPKciPvEES4_
0x0010e930: f4 03 00 2a              mov      w20, w0
0x0010e934: e0 01 00 34              cbz      w0, #0x10e970
0x0010e938: 20 32 00 f0              adrp     x0, #0x755000
0x0010e93c: e1 42 07 91              add      x1, x23, #0x1d0
0x0010e940: 00 80 1e 91              add      x0, x0, #0x7a0
0x0010e944: e2 1e 80 52              mov      w2, #0xf7
0x0010e948: e3 03 14 2a              mov      w3, w20
0x0010e94c: e8 c0 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010e950: 42 ff ff 17              b        #0x10e658                  ; -> st_auth_init+0x270
0x0010e954: 20 32 00 f0              adrp     x0, #0x755000
0x0010e958: e1 42 07 91              add      x1, x23, #0x1d0
0x0010e95c: 00 80 1d 91              add      x0, x0, #0x760
0x0010e960: 02 1e 80 52              mov      w2, #0xf0
0x0010e964: e3 03 14 2a              mov      w3, w20
0x0010e968: e1 c0 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010e96c: 3b ff ff 17              b        #0x10e658                  ; -> st_auth_init+0x270
0x0010e970: c2 b2 47 f9              ldr      x2, [x22, #0xf60]
0x0010e974: e0 03 13 aa              mov      x0, x19
0x0010e978: 41 80 00 91              add      x1, x2, #0x20
0x0010e97c: 42 34 40 f9              ldr      x2, [x2, #0x68]
0x0010e980: 2f e1 00 94              bl       #0x146e3c                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace14SetProvideFuncERKSt8functionIFiPPKcPiPPFvPvES6_EES6_
0x0010e984: f4 03 00 2a              mov      w20, w0
0x0010e988: 60 02 00 34              cbz      w0, #0x10e9d4
0x0010e98c: 20 32 00 f0              adrp     x0, #0x755000
0x0010e990: e1 42 07 91              add      x1, x23, #0x1d0
0x0010e994: 00 60 1f 91              add      x0, x0, #0x7d8
0x0010e998: c2 1f 80 52              mov      w2, #0xfe
0x0010e99c: e3 03 14 2a              mov      w3, w20
0x0010e9a0: d3 c0 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010e9a4: 2d ff ff 17              b        #0x10e658                  ; -> st_auth_init+0x270
0x0010e9a8: e0 03 13 aa              mov      x0, x19
0x0010e9ac: 82 e0 00 94              bl       #0x146bb4                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13UpdataFeatureEv
0x0010e9b0: f4 03 00 2a              mov      w20, w0
0x0010e9b4: e0 04 00 34              cbz      w0, #0x10ea50
0x0010e9b8: 20 32 00 f0              adrp     x0, #0x755000
0x0010e9bc: e1 42 07 91              add      x1, x23, #0x1d0
0x0010e9c0: 00 40 20 91              add      x0, x0, #0x810
0x0010e9c4: 62 21 80 52              mov      w2, #0x10b
0x0010e9c8: e3 03 14 2a              mov      w3, w20
0x0010e9cc: c8 c0 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010e9d0: 22 ff ff 17              b        #0x10e658                  ; -> st_auth_init+0x270
0x0010e9d4: d4 b2 47 f9              ldr      x20, [x22, #0xf60]
0x0010e9d8: b8 83 01 91              add      x24, x29, #0x60
0x0010e9dc: bf 3b 00 f9              str      xzr, [x29, #0x70]
0x0010e9e0: 83 2a 40 f9              ldr      x3, [x20, #0x50]
0x0010e9e4: 23 01 00 b4              cbz      x3, #0x10ea08
0x0010e9e8: e0 03 18 aa              mov      x0, x24
0x0010e9ec: 81 02 01 91              add      x1, x20, #0x40
0x0010e9f0: 42 00 80 52              mov      w2, #2
0x0010e9f4: 60 00 3f d6              blr      x3
0x0010e9f8: 94 42 01 91              add      x20, x20, #0x50
0x0010e9fc: 00 43 00 91              add      x0, x24, #0x10
0x0010ea00: 80 02 c0 3d              ldr      q0, [x20]                  ; -> __G78__android17_bcl_binary_data@got
0x0010ea04: 00 00 80 3d              str      q0, [x0]
0x0010ea08: e0 03 13 aa              mov      x0, x19
0x0010ea0c: e1 03 18 aa              mov      x1, x24
0x0010ea10: 1a e1 00 94              bl       #0x146e78                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace23SetTEECommunicationFuncESt8functionIFiPviS2_iEE
0x0010ea14: a3 3b 40 f9              ldr      x3, [x29, #0x70]
0x0010ea18: f4 03 00 2a              mov      w20, w0
0x0010ea1c: a3 00 00 b4              cbz      x3, #0x10ea30
0x0010ea20: e0 03 18 aa              mov      x0, x24
0x0010ea24: e1 03 18 aa              mov      x1, x24
0x0010ea28: 62 00 80 52              mov      w2, #3
0x0010ea2c: 60 00 3f d6              blr      x3
0x0010ea30: d4 fb ff 34              cbz      w20, #0x10e9a8
0x0010ea34: 20 32 00 f0              adrp     x0, #0x755000
0x0010ea38: e1 42 07 91              add      x1, x23, #0x1d0
0x0010ea3c: 00 60 1f 91              add      x0, x0, #0x7d8
0x0010ea40: 82 20 80 52              mov      w2, #0x104
0x0010ea44: e3 03 14 2a              mov      w3, w20
0x0010ea48: a9 c0 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010ea4c: 03 ff ff 17              b        #0x10e658                  ; -> st_auth_init+0x270
0x0010ea50: e0 03 13 aa              mov      x0, x19
0x0010ea54: 93 e6 00 94              bl       #0x1484a0                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace11StartThreadEv
0x0010ea58: a0 63 01 91              add      x0, x29, #0x58
0x0010ea5c: e1 03 13 aa              mov      x1, x19
0x0010ea60: b3 2b 00 f9              str      x19, [x29, #0x50]
0x0010ea64: 25 16 00 94              bl       #0x1142f8                  ; -> _ZNSt14__shared_countILN9__gnu_cxx12_Lock_policyE2EEC2IPN12stfaceunlock21FaceUnlock2DInterFaceEEET_
0x0010ea68: a1 02 40 f9              ldr      x1, [x21]
0x0010ea6c: a0 2b 40 f9              ldr      x0, [x29, #0x50]
0x0010ea70: a1 2b 00 f9              str      x1, [x29, #0x50]
0x0010ea74: a0 02 00 f9              str      x0, [x21]
0x0010ea78: a0 06 40 f9              ldr      x0, [x21, #8]
0x0010ea7c: a1 2f 40 f9              ldr      x1, [x29, #0x58]
0x0010ea80: a1 06 00 f9              str      x1, [x21, #8]
0x0010ea84: a0 2f 00 f9              str      x0, [x29, #0x58]
0x0010ea88: 40 00 00 b4              cbz      x0, #0x10ea90
0x0010ea8c: d9 0d 00 94              bl       #0x1121f0                  ; -> _ZNSt16_Sp_counted_baseILN9__gnu_cxx12_Lock_policyE2EE10_M_releaseEv
0x0010ea90: c0 82 3d 91              add      x0, x22, #0xf60
0x0010ea94: b9 12 00 b9              str      w25, [x21, #0x10]
0x0010ea98: 15 04 00 f9              str      x21, [x0, #8]
0x0010ea9c: 9e fe ff 17              b        #0x10e514                  ; -> st_auth_init+0x12c
0x0010eaa0: 81 6e 46 f9              ldr      x1, [x20, #0xcd8]
0x0010eaa4: f4 03 00 aa              mov      x20, x0
0x0010eaa8: e0 03 13 aa              mov      x0, x19
0x0010eaac: d6 69 17 94              bl       #0x6e9204                  ; -> _ZdlPvRKSt9nothrow_t
0x0010eab0: e0 03 15 aa              mov      x0, x21
0x0010eab4: f7 e9 fd 97              bl       #0x89290                   ; -> _ZNKSt14default_deleteI17_ST_FACE_UNLOCK_TEclEPS0_.isra.663
0x0010eab8: e0 03 14 aa              mov      x0, x20
0x0010eabc: fe 05 19 94              bl       #0x7502b4                  ; -> _Unwind_Resume
0x0010eac0: a3 3b 40 f9              ldr      x3, [x29, #0x70]
0x0010eac4: f4 03 00 aa              mov      x20, x0
0x0010eac8: a3 00 00 b4              cbz      x3, #0x10eadc
0x0010eacc: e0 03 18 aa              mov      x0, x24
0x0010ead0: e1 03 18 aa              mov      x1, x24
0x0010ead4: 62 00 80 52              mov      w2, #3
0x0010ead8: 60 00 3f d6              blr      x3
0x0010eadc: e0 03 13 aa              mov      x0, x19
0x0010eae0: 66 ef 00 94              bl       #0x14a878                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFaceD2Ev
0x0010eae4: e0 03 13 aa              mov      x0, x19
0x0010eae8: c6 69 17 94              bl       #0x6e9200                  ; -> _ZdlPv
0x0010eaec: f1 ff ff 17              b        #0x10eab0                  ; -> st_auth_init+0x6c8
0x0010eaf0: a3 3b 40 f9              ldr      x3, [x29, #0x70]
0x0010eaf4: f4 03 00 aa              mov      x20, x0
0x0010eaf8: 23 ff ff b4              cbz      x3, #0x10eadc
0x0010eafc: e0 03 18 aa              mov      x0, x24
0x0010eb00: e1 03 18 aa              mov      x1, x24
0x0010eb04: 62 00 80 52              mov      w2, #3
0x0010eb08: 60 00 3f d6              blr      x3
0x0010eb0c: f4 ff ff 17              b        #0x10eadc                  ; -> st_auth_init+0x6f4
0x0010eb10: f4 03 00 aa              mov      x20, x0
0x0010eb14: f2 ff ff 17              b        #0x10eadc                  ; -> st_auth_init+0x6f4
0x0010eb18: f4 03 00 aa              mov      x20, x0
0x0010eb1c: e5 ff ff 17              b        #0x10eab0                  ; -> st_auth_init+0x6c8
