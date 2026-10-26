; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : st_auth_pre_init (0x10eb20)
; start   : 0x10eb20
; size    : 0x4cc
; bytes   : 0x4cc   instructions: 307
; generated: 2026-10-24T00:26:18

0x0010eb20: fd 7b b8 a9              stp      x29, x30, [sp, #-0x80]!
0x0010eb24: 62 27 80 52              mov      w2, #0x13b
0x0010eb28: fd 03 00 91              mov      x29, sp
0x0010eb2c: f3 53 01 a9              stp      x19, x20, [sp, #0x10]
0x0010eb30: f7 63 03 a9              stp      x23, x24, [sp, #0x30]
0x0010eb34: 34 32 00 f0              adrp     x20, #0x755000
0x0010eb38: 38 32 00 f0              adrp     x24, #0x755000
0x0010eb3c: 33 32 00 90              adrp     x19, #0x752000
0x0010eb40: 94 c2 06 91              add      x20, x20, #0x1b0
0x0010eb44: f5 5b 02 a9              stp      x21, x22, [sp, #0x20]
0x0010eb48: 73 02 03 91              add      x19, x19, #0xc0
0x0010eb4c: 16 43 07 91              add      x22, x24, #0x1d0
0x0010eb50: f9 6b 04 a9              stp      x25, x26, [sp, #0x40]
0x0010eb54: 63 c2 21 91              add      x3, x19, #0x870
0x0010eb58: f9 03 00 aa              mov      x25, x0
0x0010eb5c: fa 03 01 2a              mov      w26, w1
0x0010eb60: e0 03 14 aa              mov      x0, x20
0x0010eb64: e1 03 16 aa              mov      x1, x22
0x0010eb68: 37 ee 02 90              adrp     x23, #0x5ed2000
0x0010eb6c: 15 00 80 52              mov      w21, #0
0x0010eb70: 96 bf 00 94              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x0010eb74: e0 03 14 aa              mov      x0, x20
0x0010eb78: e1 03 16 aa              mov      x1, x22
0x0010eb7c: a2 08 80 52              mov      w2, #0x45
0x0010eb80: 63 c2 22 91              add      x3, x19, #0x8b0
0x0010eb84: 91 bf 00 94              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x0010eb88: e0 82 3d 91              add      x0, x23, #0xf60
0x0010eb8c: 00 04 40 f9              ldr      x0, [x0, #8]
0x0010eb90: 00 01 00 b4              cbz      x0, #0x10ebb0
0x0010eb94: e0 03 15 2a              mov      w0, w21
0x0010eb98: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x0010eb9c: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x0010eba0: f7 63 43 a9              ldp      x23, x24, [sp, #0x30]
0x0010eba4: f9 6b 44 a9              ldp      x25, x26, [sp, #0x40]
0x0010eba8: fd 7b c8 a8              ldp      x29, x30, [sp], #0x80
0x0010ebac: c0 03 5f d6              ret      
0x0010ebb0: e0 b2 47 f9              ldr      x0, [x23, #0xf60]
0x0010ebb4: 00 01 00 b4              cbz      x0, #0x10ebd4
0x0010ebb8: 01 28 40 f9              ldr      x1, [x0, #0x50]
0x0010ebbc: e1 0a 00 b4              cbz      x1, #0x10ed18
0x0010ebc0: 01 08 40 f9              ldr      x1, [x0, #0x10]
0x0010ebc4: 35 7e 80 12              mov      w21, #-0x3f2
0x0010ebc8: 61 fe ff b5              cbnz     x1, #0x10eb94
0x0010ebcc: 00 18 40 f9              ldr      x0, [x0, #0x30]
0x0010ebd0: 20 fe ff b5              cbnz     x0, #0x10eb94
0x0010ebd4: 2a b7 ff 97              bl       #0xfc87c                   ; -> st_face_get_version
0x0010ebd8: e1 03 00 aa              mov      x1, x0
0x0010ebdc: e0 03 19 aa              mov      x0, x25
0x0010ebe0: 4e 08 01 94              bl       #0x150d18                  ; -> _ZN12stfaceunlock11AlgoManager17InitAlgorithmInfoEPKcS2_
0x0010ebe4: f5 03 00 2a              mov      w21, w0
0x0010ebe8: 60 fd ff 35              cbnz     w0, #0x10eb94
0x0010ebec: f5 70 02 90              adrp     x21, #0x4f2a000
0x0010ebf0: 00 03 80 d2              mov      x0, #0x18
0x0010ebf4: b3 6e 46 f9              ldr      x19, [x21, #0xcd8]
0x0010ebf8: e1 03 13 aa              mov      x1, x19
0x0010ebfc: 1a 70 17 94              bl       #0x6eac64                  ; -> _ZnwmRKSt9nothrow_t
0x0010ec00: f4 03 00 aa              mov      x20, x0
0x0010ec04: 40 0a 00 b4              cbz      x0, #0x10ed4c
0x0010ec08: 1f 00 00 f9              str      xzr, [x0]
0x0010ec0c: e1 03 13 aa              mov      x1, x19
0x0010ec10: 00 56 80 d2              mov      x0, #0x2b0
0x0010ec14: 9f 06 00 f9              str      xzr, [x20, #8]
0x0010ec18: 9f 52 00 39              strb     wzr, [x20, #0x14]
0x0010ec1c: 9f 56 00 39              strb     wzr, [x20, #0x15]
0x0010ec20: 11 70 17 94              bl       #0x6eac64                  ; -> _ZnwmRKSt9nothrow_t
0x0010ec24: f3 03 00 aa              mov      x19, x0
0x0010ec28: 40 08 00 b4              cbz      x0, #0x10ed30
0x0010ec2c: cf e9 00 94              bl       #0x149368                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFaceC2Ev
0x0010ec30: e0 03 13 aa              mov      x0, x19
0x0010ec34: e1 03 19 aa              mov      x1, x25
0x0010ec38: 94 df 00 94              bl       #0x146a88                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace19SetTemplateFilePathEPKc
0x0010ec3c: f5 03 00 2a              mov      w21, w0
0x0010ec40: e0 05 00 35              cbnz     w0, #0x10ecfc
0x0010ec44: c1 70 02 d0              adrp     x1, #0x4f28000
0x0010ec48: c3 70 02 f0              adrp     x3, #0x4f29000
0x0010ec4c: c2 70 02 f0              adrp     x2, #0x4f29000
0x0010ec50: c4 70 02 f0              adrp     x4, #0x4f29000
0x0010ec54: e0 03 13 aa              mov      x0, x19
0x0010ec58: 21 e8 44 f9              ldr      x1, [x1, #0x9d0]
0x0010ec5c: 63 bc 44 f9              ldr      x3, [x3, #0x978]
0x0010ec60: 42 18 40 f9              ldr      x2, [x2, #0x30]
0x0010ec64: 21 00 40 f9              ldr      x1, [x1]
0x0010ec68: 63 00 40 f9              ldr      x3, [x3]                   ; -> __6xx__android11_bcl_binary_data_size@got
0x0010ec6c: 84 b4 43 f9              ldr      x4, [x4, #0x768]
0x0010ec70: dc db 00 94              bl       #0x145be0                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace18CreateOcularHandleEPKNS_5ModelEP21cv_model_net_config_tS3_S5_
0x0010ec74: f5 03 00 2a              mov      w21, w0
0x0010ec78: a0 0b 00 35              cbnz     w0, #0x10edec
0x0010ec7c: e1 b2 47 f9              ldr      x1, [x23, #0xf60]
0x0010ec80: 41 0c 00 b4              cbz      x1, #0x10ee08
0x0010ec84: 22 30 40 f9              ldr      x2, [x1, #0x60]
0x0010ec88: e0 03 13 aa              mov      x0, x19
0x0010ec8c: 5d e0 00 94              bl       #0x146e00                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace12SetFetchFuncERKSt8functionIFiPKciPvEES4_
0x0010ec90: f5 03 00 2a              mov      w21, w0
0x0010ec94: 00 09 00 34              cbz      w0, #0x10edb4
0x0010ec98: 20 32 00 f0              adrp     x0, #0x755000
0x0010ec9c: 01 43 07 91              add      x1, x24, #0x1d0
0x0010eca0: 00 80 1e 91              add      x0, x0, #0x7a0
0x0010eca4: 22 0f 80 52              mov      w2, #0x79
0x0010eca8: e3 03 15 2a              mov      w3, w21
0x0010ecac: 10 c0 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010ecb0: e0 03 13 aa              mov      x0, x19
0x0010ecb4: f1 ee 00 94              bl       #0x14a878                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFaceD2Ev
0x0010ecb8: e0 03 13 aa              mov      x0, x19
0x0010ecbc: 51 69 17 94              bl       #0x6e9200                  ; -> _ZdlPv
0x0010ecc0: 93 06 40 f9              ldr      x19, [x20, #8]
0x0010ecc4: 73 01 00 b4              cbz      x19, #0x10ecf0
0x0010ecc8: c0 70 02 f0              adrp     x0, #0x4f29000
0x0010eccc: 00 94 47 f9              ldr      x0, [x0, #0xf28]           ; -> pthread_create@got
0x0010ecd0: c0 04 00 b4              cbz      x0, #0x10ed68
0x0010ecd4: 60 22 00 91              add      x0, x19, #8
0x0010ecd8: 01 fc 5f 88              ldaxr    w1, [x0]
0x0010ecdc: 22 04 00 51              sub      w2, w1, #1
0x0010ece0: 02 fc 03 88              stlxr    w3, w2, [x0]
0x0010ece4: a3 ff ff 35              cbnz     w3, #0x10ecd8
0x0010ece8: 3f 04 00 71              cmp      w1, #1
0x0010ecec: e0 0a 00 54              b.eq     #0x10ee48
0x0010ecf0: e0 03 14 aa              mov      x0, x20
0x0010ecf4: 43 69 17 94              bl       #0x6e9200                  ; -> _ZdlPv
0x0010ecf8: a7 ff ff 17              b        #0x10eb94                  ; -> st_auth_pre_init+0x74
0x0010ecfc: 20 32 00 f0              adrp     x0, #0x755000
0x0010ed00: 01 43 07 91              add      x1, x24, #0x1d0
0x0010ed04: 00 00 18 91              add      x0, x0, #0x600
0x0010ed08: 62 0d 80 52              mov      w2, #0x6b
0x0010ed0c: e3 03 15 2a              mov      w3, w21
0x0010ed10: f7 bf 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010ed14: e7 ff ff 17              b        #0x10ecb0                  ; -> st_auth_pre_init+0x190
0x0010ed18: 01 08 40 f9              ldr      x1, [x0, #0x10]
0x0010ed1c: 61 00 00 b4              cbz      x1, #0x10ed28
0x0010ed20: 00 18 40 f9              ldr      x0, [x0, #0x30]
0x0010ed24: 80 f5 ff b5              cbnz     x0, #0x10ebd4
0x0010ed28: d5 7d 80 12              mov      w21, #-0x3ef
0x0010ed2c: 9a ff ff 17              b        #0x10eb94                  ; -> st_auth_pre_init+0x74
0x0010ed30: 20 32 00 f0              adrp     x0, #0x755000
0x0010ed34: 01 43 07 91              add      x1, x24, #0x1d0
0x0010ed38: 00 c0 21 91              add      x0, x0, #0x870
0x0010ed3c: 62 0c 80 52              mov      w2, #0x63
0x0010ed40: eb bf 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010ed44: 55 00 80 12              mov      w21, #-3
0x0010ed48: de ff ff 17              b        #0x10ecc0                  ; -> st_auth_pre_init+0x1a0
0x0010ed4c: 20 32 00 f0              adrp     x0, #0x755000
0x0010ed50: 01 43 07 91              add      x1, x24, #0x1d0
0x0010ed54: 00 20 21 91              add      x0, x0, #0x848
0x0010ed58: 82 0b 80 52              mov      w2, #0x5c
0x0010ed5c: 55 00 80 12              mov      w21, #-3
0x0010ed60: e3 bf 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010ed64: 8c ff ff 17              b        #0x10eb94                  ; -> st_auth_pre_init+0x74
0x0010ed68: 60 0a 40 b9              ldr      w0, [x19, #8]
0x0010ed6c: 01 04 00 51              sub      w1, w0, #1
0x0010ed70: 1f 04 00 71              cmp      w0, #1
0x0010ed74: 61 0a 00 b9              str      w1, [x19, #8]
0x0010ed78: c1 fb ff 54              b.ne     #0x10ecf0
0x0010ed7c: 61 02 40 f9              ldr      x1, [x19]
0x0010ed80: e0 03 13 aa              mov      x0, x19
0x0010ed84: 21 08 40 f9              ldr      x1, [x1, #0x10]
0x0010ed88: 20 00 3f d6              blr      x1
0x0010ed8c: 60 0e 40 b9              ldr      w0, [x19, #0xc]
0x0010ed90: 01 04 00 51              sub      w1, w0, #1
0x0010ed94: 61 0e 00 b9              str      w1, [x19, #0xc]
0x0010ed98: 1f 04 00 71              cmp      w0, #1
0x0010ed9c: a1 fa ff 54              b.ne     #0x10ecf0
0x0010eda0: 61 02 40 f9              ldr      x1, [x19]
0x0010eda4: e0 03 13 aa              mov      x0, x19
0x0010eda8: 21 0c 40 f9              ldr      x1, [x1, #0x18]
0x0010edac: 20 00 3f d6              blr      x1
0x0010edb0: d0 ff ff 17              b        #0x10ecf0                  ; -> st_auth_pre_init+0x1d0
0x0010edb4: e2 b2 47 f9              ldr      x2, [x23, #0xf60]
0x0010edb8: e0 03 13 aa              mov      x0, x19
0x0010edbc: 41 80 00 91              add      x1, x2, #0x20
0x0010edc0: 42 34 40 f9              ldr      x2, [x2, #0x68]
0x0010edc4: 1e e0 00 94              bl       #0x146e3c                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace14SetProvideFuncERKSt8functionIFiPPKcPiPPFvPvES6_EES6_
0x0010edc8: f5 03 00 2a              mov      w21, w0
0x0010edcc: 20 05 00 34              cbz      w0, #0x10ee70
0x0010edd0: 20 32 00 f0              adrp     x0, #0x755000
0x0010edd4: 01 43 07 91              add      x1, x24, #0x1d0
0x0010edd8: 00 60 1f 91              add      x0, x0, #0x7d8
0x0010eddc: e2 0f 80 52              mov      w2, #0x7f
0x0010ede0: e3 03 15 2a              mov      w3, w21
0x0010ede4: c2 bf 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010ede8: b2 ff ff 17              b        #0x10ecb0                  ; -> st_auth_pre_init+0x190
0x0010edec: 20 32 00 f0              adrp     x0, #0x755000
0x0010edf0: 01 43 07 91              add      x1, x24, #0x1d0
0x0010edf4: 00 e0 19 91              add      x0, x0, #0x678
0x0010edf8: 62 0e 80 52              mov      w2, #0x73
0x0010edfc: e3 03 15 2a              mov      w3, w21
0x0010ee00: bb bf 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010ee04: ab ff ff 17              b        #0x10ecb0                  ; -> st_auth_pre_init+0x190
0x0010ee08: e1 70 02 90              adrp     x1, #0x4f2a000
0x0010ee0c: c2 70 02 f0              adrp     x2, #0x4f29000
0x0010ee10: e0 03 13 aa              mov      x0, x19
0x0010ee14: 21 c0 46 f9              ldr      x1, [x1, #0xd80]
0x0010ee18: 42 a4 44 f9              ldr      x2, [x2, #0x948]
0x0010ee1c: 21 00 40 f9              ldr      x1, [x1]                   ; -> __G78__android17_bcl_binary_data@got
0x0010ee20: a1 da 00 94              bl       #0x1458a4                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace18CreateVeriyfHandleEPKNS_5ModelEP21cv_model_net_config_t
0x0010ee24: f5 03 00 2a              mov      w21, w0
0x0010ee28: 20 06 00 34              cbz      w0, #0x10eeec
0x0010ee2c: 20 32 00 f0              adrp     x0, #0x755000
0x0010ee30: 01 43 07 91              add      x1, x24, #0x1d0
0x0010ee34: 00 c0 1b 91              add      x0, x0, #0x6f0
0x0010ee38: a2 11 80 52              mov      w2, #0x8d
0x0010ee3c: e3 03 15 2a              mov      w3, w21
0x0010ee40: ab bf 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010ee44: 9b ff ff 17              b        #0x10ecb0                  ; -> st_auth_pre_init+0x190
0x0010ee48: 61 02 40 f9              ldr      x1, [x19]
0x0010ee4c: e0 03 13 aa              mov      x0, x19
0x0010ee50: 21 08 40 f9              ldr      x1, [x1, #0x10]            ; -> __G61__android16_bcl_binary_data@got
0x0010ee54: 20 00 3f d6              blr      x1
0x0010ee58: 61 32 00 91              add      x1, x19, #0xc
0x0010ee5c: 20 fc 5f 88              ldaxr    w0, [x1]
0x0010ee60: 02 04 00 51              sub      w2, w0, #1
0x0010ee64: 22 fc 03 88              stlxr    w3, w2, [x1]
0x0010ee68: 83 f9 ff 34              cbz      w3, #0x10ed98
0x0010ee6c: fc ff ff 17              b        #0x10ee5c                  ; -> st_auth_pre_init+0x33c
0x0010ee70: f5 b2 47 f9              ldr      x21, [x23, #0xf60]
0x0010ee74: b6 83 01 91              add      x22, x29, #0x60
0x0010ee78: bf 3b 00 f9              str      xzr, [x29, #0x70]
0x0010ee7c: a3 2a 40 f9              ldr      x3, [x21, #0x50]
0x0010ee80: 23 01 00 b4              cbz      x3, #0x10eea4
0x0010ee84: e0 03 16 aa              mov      x0, x22
0x0010ee88: a1 02 01 91              add      x1, x21, #0x40
0x0010ee8c: 42 00 80 52              mov      w2, #2
0x0010ee90: 60 00 3f d6              blr      x3
0x0010ee94: b5 42 01 91              add      x21, x21, #0x50
0x0010ee98: c0 42 00 91              add      x0, x22, #0x10
0x0010ee9c: a0 02 c0 3d              ldr      q0, [x21]                  ; -> __G78__android17_bcl_binary_data@got
0x0010eea0: 00 00 80 3d              str      q0, [x0]
0x0010eea4: e0 03 13 aa              mov      x0, x19
0x0010eea8: e1 03 16 aa              mov      x1, x22
0x0010eeac: f3 df 00 94              bl       #0x146e78                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace23SetTEECommunicationFuncESt8functionIFiPviS2_iEE
0x0010eeb0: a3 3b 40 f9              ldr      x3, [x29, #0x70]
0x0010eeb4: f5 03 00 2a              mov      w21, w0
0x0010eeb8: a3 00 00 b4              cbz      x3, #0x10eecc
0x0010eebc: e0 03 16 aa              mov      x0, x22
0x0010eec0: e1 03 16 aa              mov      x1, x22
0x0010eec4: 62 00 80 52              mov      w2, #3
0x0010eec8: 60 00 3f d6              blr      x3
0x0010eecc: f5 f9 ff 34              cbz      w21, #0x10ee08
0x0010eed0: 20 32 00 f0              adrp     x0, #0x755000
0x0010eed4: 01 43 07 91              add      x1, x24, #0x1d0
0x0010eed8: 00 60 1f 91              add      x0, x0, #0x7d8
0x0010eedc: a2 10 80 52              mov      w2, #0x85
0x0010eee0: e3 03 15 2a              mov      w3, w21
0x0010eee4: 82 bf 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010eee8: 72 ff ff 17              b        #0x10ecb0                  ; -> st_auth_pre_init+0x190
0x0010eeec: e0 03 13 aa              mov      x0, x19
0x0010eef0: 31 df 00 94              bl       #0x146bb4                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace13UpdataFeatureEv
0x0010eef4: f5 03 00 2a              mov      w21, w0
0x0010eef8: 00 01 00 34              cbz      w0, #0x10ef18
0x0010eefc: 20 32 00 f0              adrp     x0, #0x755000
0x0010ef00: 01 43 07 91              add      x1, x24, #0x1d0
0x0010ef04: 00 40 20 91              add      x0, x0, #0x810
0x0010ef08: 62 12 80 52              mov      w2, #0x93
0x0010ef0c: e3 03 15 2a              mov      w3, w21
0x0010ef10: 77 bf 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010ef14: 67 ff ff 17              b        #0x10ecb0                  ; -> st_auth_pre_init+0x190
0x0010ef18: a0 63 01 91              add      x0, x29, #0x58
0x0010ef1c: e1 03 13 aa              mov      x1, x19
0x0010ef20: b3 2b 00 f9              str      x19, [x29, #0x50]
0x0010ef24: f5 14 00 94              bl       #0x1142f8                  ; -> _ZNSt14__shared_countILN9__gnu_cxx12_Lock_policyE2EEC2IPN12stfaceunlock21FaceUnlock2DInterFaceEEET_
0x0010ef28: 81 02 40 f9              ldr      x1, [x20]
0x0010ef2c: a0 2b 40 f9              ldr      x0, [x29, #0x50]
0x0010ef30: a1 2b 00 f9              str      x1, [x29, #0x50]
0x0010ef34: 80 02 00 f9              str      x0, [x20]
0x0010ef38: 80 06 40 f9              ldr      x0, [x20, #8]
0x0010ef3c: a1 2f 40 f9              ldr      x1, [x29, #0x58]
0x0010ef40: 81 06 00 f9              str      x1, [x20, #8]
0x0010ef44: a0 2f 00 f9              str      x0, [x29, #0x58]
0x0010ef48: 40 00 00 b4              cbz      x0, #0x10ef50
0x0010ef4c: a9 0c 00 94              bl       #0x1121f0                  ; -> _ZNSt16_Sp_counted_baseILN9__gnu_cxx12_Lock_policyE2EE10_M_releaseEv
0x0010ef50: f7 82 3d 91              add      x23, x23, #0xf60
0x0010ef54: 20 00 80 52              mov      w0, #1
0x0010ef58: 9a 12 00 b9              str      w26, [x20, #0x10]
0x0010ef5c: 15 00 80 52              mov      w21, #0
0x0010ef60: 80 52 00 39              strb     w0, [x20, #0x14]
0x0010ef64: f4 06 00 f9              str      x20, [x23, #8]
0x0010ef68: 0b ff ff 17              b        #0x10eb94                  ; -> st_auth_pre_init+0x74
0x0010ef6c: f5 03 00 aa              mov      x21, x0
0x0010ef70: e0 03 14 aa              mov      x0, x20
0x0010ef74: c7 e8 fd 97              bl       #0x89290                   ; -> _ZNKSt14default_deleteI17_ST_FACE_UNLOCK_TEclEPS0_.isra.663
0x0010ef78: e0 03 15 aa              mov      x0, x21
0x0010ef7c: ce 04 19 94              bl       #0x7502b4                  ; -> _Unwind_Resume
0x0010ef80: a3 3b 40 f9              ldr      x3, [x29, #0x70]
0x0010ef84: f5 03 00 aa              mov      x21, x0
0x0010ef88: a3 00 00 b4              cbz      x3, #0x10ef9c
0x0010ef8c: e0 03 16 aa              mov      x0, x22
0x0010ef90: e1 03 16 aa              mov      x1, x22
0x0010ef94: 62 00 80 52              mov      w2, #3
0x0010ef98: 60 00 3f d6              blr      x3
0x0010ef9c: e0 03 13 aa              mov      x0, x19
0x0010efa0: 36 ee 00 94              bl       #0x14a878                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFaceD2Ev
0x0010efa4: e0 03 13 aa              mov      x0, x19
0x0010efa8: 96 68 17 94              bl       #0x6e9200                  ; -> _ZdlPv
0x0010efac: f1 ff ff 17              b        #0x10ef70                  ; -> st_auth_pre_init+0x450
0x0010efb0: a3 3b 40 f9              ldr      x3, [x29, #0x70]
0x0010efb4: f5 03 00 aa              mov      x21, x0
0x0010efb8: 23 ff ff b4              cbz      x3, #0x10ef9c
0x0010efbc: e0 03 16 aa              mov      x0, x22
0x0010efc0: e1 03 16 aa              mov      x1, x22
0x0010efc4: 62 00 80 52              mov      w2, #3
0x0010efc8: 60 00 3f d6              blr      x3
0x0010efcc: f4 ff ff 17              b        #0x10ef9c                  ; -> st_auth_pre_init+0x47c
0x0010efd0: f5 03 00 aa              mov      x21, x0
0x0010efd4: f2 ff ff 17              b        #0x10ef9c                  ; -> st_auth_pre_init+0x47c
0x0010efd8: a1 6e 46 f9              ldr      x1, [x21, #0xcd8]
0x0010efdc: f5 03 00 aa              mov      x21, x0
0x0010efe0: e0 03 13 aa              mov      x0, x19
0x0010efe4: 88 68 17 94              bl       #0x6e9204                  ; -> _ZdlPvRKSt9nothrow_t
0x0010efe8: e2 ff ff 17              b        #0x10ef70                  ; -> st_auth_pre_init+0x450
