; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : _ZN12stfaceunlock21FaceUnlock2DInterFace11StartThreadEv (0x1484a0)
; start   : 0x1484a0
; size    : 0x340
; bytes   : 0x340   instructions: 208
; generated: 2026-10-24T00:43:36

0x001484a0: fd 7b bb a9              stp      x29, x30, [sp, #-0x50]!
0x001484a4: fd 03 00 91              mov      x29, sp
0x001484a8: f3 53 01 a9              stp      x19, x20, [sp, #0x10]
0x001484ac: f5 5b 02 a9              stp      x21, x22, [sp, #0x20]
0x001484b0: f3 03 00 aa              mov      x19, x0
0x001484b4: 00 f0 01 91              add      x0, x0, #0x7c
0x001484b8: 00 fc df 08              ldarb    w0, [x0]
0x001484bc: 00 1c 00 53              uxtb     w0, w0
0x001484c0: 20 01 00 35              cbnz     w0, #0x1484e4
0x001484c4: 60 72 05 91              add      x0, x19, #0x15c
0x001484c8: 00 fc df 08              ldarb    w0, [x0]
0x001484cc: 00 1c 00 53              uxtb     w0, w0
0x001484d0: 80 07 00 35              cbnz     w0, #0x1485c0
0x001484d4: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x001484d8: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x001484dc: fd 7b c5 a8              ldp      x29, x30, [sp], #0x50
0x001484e0: c0 03 5f d6              ret      
0x001484e4: e1 30 00 b0              adrp     x1, #0x765000
0x001484e8: 60 e2 04 91              add      x0, x19, #0x138
0x001484ec: 21 a0 02 91              add      x1, x1, #0xa8
0x001484f0: c2 00 80 d2              mov      x2, #6
0x001484f4: 76 82 01 91              add      x22, x19, #0x60
0x001484f8: fb b8 17 94              bl       #0x7368e4                  ; -> _ZNSs6assignEPKcm
0x001484fc: 60 f2 01 91              add      x0, x19, #0x7c
0x00148500: 1f fc 9f 08              stlrb    wzr, [x0]
0x00148504: 00 06 80 d2              mov      x0, #0x30
0x00148508: bf 1b 00 f9              str      xzr, [x29, #0x30]
0x0014850c: b5 03 00 8b              add      x21, x29, x0
0x00148510: b9 89 16 94              bl       #0x6eabf4                  ; -> _Znwm
0x00148514: e2 03 00 aa              mov      x2, x0
0x00148518: 21 00 80 52              mov      w1, #1
0x0014851c: 01 08 00 b9              str      w1, [x0, #8]
0x00148520: 01 0c 00 b9              str      w1, [x0, #0xc]
0x00148524: 01 6f 02 90              adrp     x1, #0x4f28000
0x00148528: f4 03 00 aa              mov      x20, x0
0x0014852c: 21 80 45 f9              ldr      x1, [x1, #0xb00]
0x00148530: 21 40 00 91              add      x1, x1, #0x10
0x00148534: 41 04 01 f8              str      x1, [x2], #0x10
0x00148538: 01 6f 02 d0              adrp     x1, #0x4f2a000
0x0014853c: 5f 04 00 f9              str      xzr, [x2, #8]
0x00148540: 5f 08 00 f9              str      xzr, [x2, #0x10]
0x00148544: 21 04 46 f9              ldr      x1, [x1, #0xc08]
0x00148548: 16 14 00 f9              str      x22, [x0, #0x28]
0x0014854c: 21 40 00 91              add      x1, x1, #0x10
0x00148550: 01 08 00 f9              str      x1, [x0, #0x10]
0x00148554: 01 6f 02 d0              adrp     x1, #0x4f2a000
0x00148558: 21 14 41 f9              ldr      x1, [x1, #0x228]
0x0014855c: 7f 0f 00 94              bl       #0x14c358                  ; -> _ZNSt23_Sp_counted_ptr_inplaceINSt6thread5_ImplISt12_Bind_simpleIFZN13ThreadManagerISt5_BindIFSt7_Mem_fnIMN12stfaceunlock17FaceVerifyManagerEFiPK10st_image_tPK9st_face_tPP12st_feature_tPjEESt10shared_ptrIS7_ESt12_PlaceholderILi1EESN_ILi2EESN_ILi3EESN_ILi4EEEEJSA_SD_SG_SH_EE11StartThreadEPKcEUlvE_vEEEESaIS10_ELN9__gnu_cxx12_Lock_policyE2EE14_M_get_deleterERKSt9type_info
0x00148560: a0 23 00 f9              str      x0, [x29, #0x40]
0x00148564: a1 03 01 91              add      x1, x29, #0x40
0x00148568: e0 03 15 aa              mov      x0, x21
0x0014856c: b4 27 00 f9              str      x20, [x29, #0x48]
0x00148570: 0f c2 17 94              bl       #0x738dac                  ; -> _ZNSt6thread15_M_start_threadESt10shared_ptrINS_10_Impl_baseEE
0x00148574: b4 27 40 f9              ldr      x20, [x29, #0x48]
0x00148578: 74 01 00 b4              cbz      x20, #0x1485a4
0x0014857c: 00 6f 02 b0              adrp     x0, #0x4f29000
0x00148580: 00 94 47 f9              ldr      x0, [x0, #0xf28]           ; -> pthread_create@got
0x00148584: c0 0a 00 b4              cbz      x0, #0x1486dc
0x00148588: 80 22 00 91              add      x0, x20, #8
0x0014858c: 01 fc 5f 88              ldaxr    w1, [x0]
0x00148590: 22 04 00 51              sub      w2, w1, #1
0x00148594: 02 fc 03 88              stlxr    w3, w2, [x0]
0x00148598: a3 ff ff 35              cbnz     w3, #0x14858c
0x0014859c: 3f 04 00 71              cmp      w1, #1
0x001485a0: 80 0b 00 54              b.eq     #0x148710
0x001485a4: e0 03 15 aa              mov      x0, x21
0x001485a8: 9b c1 17 94              bl       #0x738c14                  ; -> _ZNSt6thread6detachEv
0x001485ac: a0 1b 40 f9              ldr      x0, [x29, #0x30]
0x001485b0: 01 00 80 d2              mov      x1, #0
0x001485b4: df 01 fd 97              bl       #0x88d30                   ; -> pthread_equal@plt
0x001485b8: 60 f8 ff 35              cbnz     w0, #0x1484c4
0x001485bc: 04 88 16 94              bl       #0x6ea5cc                  ; -> _ZSt9terminatev
0x001485c0: e1 30 00 b0              adrp     x1, #0x765000
0x001485c4: 60 62 08 91              add      x0, x19, #0x218
0x001485c8: 21 c0 02 91              add      x1, x1, #0xb0
0x001485cc: 02 01 80 d2              mov      x2, #8
0x001485d0: 75 02 05 91              add      x21, x19, #0x140
0x001485d4: c4 b8 17 94              bl       #0x7368e4                  ; -> _ZNSs6assignEPKcm
0x001485d8: 60 72 05 91              add      x0, x19, #0x15c
0x001485dc: 1f fc 9f 08              stlrb    wzr, [x0]
0x001485e0: 00 06 80 d2              mov      x0, #0x30
0x001485e4: bf 1f 00 f9              str      xzr, [x29, #0x38]
0x001485e8: b4 e3 00 91              add      x20, x29, #0x38
0x001485ec: 82 89 16 94              bl       #0x6eabf4                  ; -> _Znwm
0x001485f0: e2 03 00 aa              mov      x2, x0
0x001485f4: 21 00 80 52              mov      w1, #1
0x001485f8: 01 08 00 b9              str      w1, [x0, #8]
0x001485fc: 01 0c 00 b9              str      w1, [x0, #0xc]
0x00148600: 01 6f 02 90              adrp     x1, #0x4f28000
0x00148604: f3 03 00 aa              mov      x19, x0
0x00148608: 21 d0 44 f9              ldr      x1, [x1, #0x9a0]
0x0014860c: 21 40 00 91              add      x1, x1, #0x10
0x00148610: 41 04 01 f8              str      x1, [x2], #0x10
0x00148614: 01 6f 02 d0              adrp     x1, #0x4f2a000
0x00148618: 5f 04 00 f9              str      xzr, [x2, #8]
0x0014861c: 5f 08 00 f9              str      xzr, [x2, #0x10]
0x00148620: 21 3c 47 f9              ldr      x1, [x1, #0xe78]
0x00148624: 15 14 00 f9              str      x21, [x0, #0x28]
0x00148628: 21 40 00 91              add      x1, x1, #0x10
0x0014862c: 01 08 00 f9              str      x1, [x0, #0x10]
0x00148630: 01 6f 02 d0              adrp     x1, #0x4f2a000
0x00148634: 21 14 41 f9              ldr      x1, [x1, #0x228]
0x00148638: 5f 0f 00 94              bl       #0x14c3b4                  ; -> _ZNSt23_Sp_counted_ptr_inplaceINSt6thread5_ImplISt12_Bind_simpleIFZN13ThreadManagerISt5_BindIFSt7_Mem_fnIMN12stfaceunlock19FaceHacknessManagerEFiPK10st_image_tPK9st_face_t19st_face_orientationPfEESt10shared_ptrIS7_ESt12_PlaceholderILi1EESL_ILi2EESL_ILi3EESL_ILi4EEEEJSA_SD_SE_SF_EE11StartThreadEPKcEUlvE_vEEEESaISY_ELN9__gnu_cxx12_Lock_policyE2EE14_M_get_deleterERKSt9type_info
0x0014863c: a0 23 00 f9              str      x0, [x29, #0x40]
0x00148640: a1 03 01 91              add      x1, x29, #0x40
0x00148644: e0 03 14 aa              mov      x0, x20
0x00148648: b3 27 00 f9              str      x19, [x29, #0x48]
0x0014864c: d8 c1 17 94              bl       #0x738dac                  ; -> _ZNSt6thread15_M_start_threadESt10shared_ptrINS_10_Impl_baseEE
0x00148650: b3 27 40 f9              ldr      x19, [x29, #0x48]
0x00148654: 73 01 00 b4              cbz      x19, #0x148680
0x00148658: 00 6f 02 b0              adrp     x0, #0x4f29000
0x0014865c: 00 94 47 f9              ldr      x0, [x0, #0xf28]           ; -> pthread_create@got
0x00148660: 40 02 00 b4              cbz      x0, #0x1486a8
0x00148664: 60 22 00 91              add      x0, x19, #8
0x00148668: 01 fc 5f 88              ldaxr    w1, [x0]
0x0014866c: 22 04 00 51              sub      w2, w1, #1
0x00148670: 02 fc 03 88              stlxr    w3, w2, [x0]
0x00148674: a3 ff ff 35              cbnz     w3, #0x148668
0x00148678: 3f 04 00 71              cmp      w1, #1
0x0014867c: a0 06 00 54              b.eq     #0x148750
0x00148680: e0 03 14 aa              mov      x0, x20
0x00148684: 64 c1 17 94              bl       #0x738c14                  ; -> _ZNSt6thread6detachEv
0x00148688: a0 1f 40 f9              ldr      x0, [x29, #0x38]
0x0014868c: 01 00 80 d2              mov      x1, #0
0x00148690: a8 01 fd 97              bl       #0x88d30                   ; -> pthread_equal@plt
0x00148694: 40 f9 ff 34              cbz      w0, #0x1485bc
0x00148698: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x0014869c: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x001486a0: fd 7b c5 a8              ldp      x29, x30, [sp], #0x50
0x001486a4: c0 03 5f d6              ret      
0x001486a8: 60 0a 40 b9              ldr      w0, [x19, #8]
0x001486ac: 01 04 00 51              sub      w1, w0, #1
0x001486b0: 1f 04 00 71              cmp      w0, #1
0x001486b4: 61 0a 00 b9              str      w1, [x19, #8]
0x001486b8: 41 fe ff 54              b.ne     #0x148680
0x001486bc: 61 02 40 f9              ldr      x1, [x19]
0x001486c0: e0 03 13 aa              mov      x0, x19
0x001486c4: 21 08 40 f9              ldr      x1, [x1, #0x10]            ; -> __G61__android16_bcl_binary_data@got
0x001486c8: 20 00 3f d6              blr      x1
0x001486cc: 60 0e 40 b9              ldr      w0, [x19, #0xc]
0x001486d0: 01 04 00 51              sub      w1, w0, #1
0x001486d4: 61 0e 00 b9              str      w1, [x19, #0xc]
0x001486d8: 27 00 00 14              b        #0x148774                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace11StartThreadEv+0x2d4
0x001486dc: 80 0a 40 b9              ldr      w0, [x20, #8]
0x001486e0: 01 04 00 51              sub      w1, w0, #1
0x001486e4: 1f 04 00 71              cmp      w0, #1
0x001486e8: 81 0a 00 b9              str      w1, [x20, #8]
0x001486ec: c1 f5 ff 54              b.ne     #0x1485a4
0x001486f0: 81 02 40 f9              ldr      x1, [x20]
0x001486f4: e0 03 14 aa              mov      x0, x20
0x001486f8: 21 08 40 f9              ldr      x1, [x1, #0x10]            ; -> __G61__android16_bcl_binary_data@got
0x001486fc: 20 00 3f d6              blr      x1
0x00148700: 80 0e 40 b9              ldr      w0, [x20, #0xc]
0x00148704: 01 04 00 51              sub      w1, w0, #1
0x00148708: 81 0e 00 b9              str      w1, [x20, #0xc]
0x0014870c: 0a 00 00 14              b        #0x148734                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace11StartThreadEv+0x294
0x00148710: 81 02 40 f9              ldr      x1, [x20]
0x00148714: e0 03 14 aa              mov      x0, x20
0x00148718: 21 08 40 f9              ldr      x1, [x1, #0x10]            ; -> __G61__android16_bcl_binary_data@got
0x0014871c: 20 00 3f d6              blr      x1
0x00148720: 81 32 00 91              add      x1, x20, #0xc
0x00148724: 20 fc 5f 88              ldaxr    w0, [x1]
0x00148728: 02 04 00 51              sub      w2, w0, #1
0x0014872c: 22 fc 03 88              stlxr    w3, w2, [x1]
0x00148730: a3 ff ff 35              cbnz     w3, #0x148724
0x00148734: 1f 04 00 71              cmp      w0, #1
0x00148738: 61 f3 ff 54              b.ne     #0x1485a4
0x0014873c: 81 02 40 f9              ldr      x1, [x20]
0x00148740: e0 03 14 aa              mov      x0, x20
0x00148744: 21 0c 40 f9              ldr      x1, [x1, #0x18]
0x00148748: 20 00 3f d6              blr      x1
0x0014874c: 96 ff ff 17              b        #0x1485a4                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace11StartThreadEv+0x104
0x00148750: 61 02 40 f9              ldr      x1, [x19]
0x00148754: e0 03 13 aa              mov      x0, x19
0x00148758: 21 08 40 f9              ldr      x1, [x1, #0x10]            ; -> __G61__android16_bcl_binary_data@got
0x0014875c: 20 00 3f d6              blr      x1
0x00148760: 61 32 00 91              add      x1, x19, #0xc
0x00148764: 20 fc 5f 88              ldaxr    w0, [x1]
0x00148768: 02 04 00 51              sub      w2, w0, #1
0x0014876c: 22 fc 03 88              stlxr    w3, w2, [x1]
0x00148770: a3 ff ff 35              cbnz     w3, #0x148764
0x00148774: 1f 04 00 71              cmp      w0, #1
0x00148778: 41 f8 ff 54              b.ne     #0x148680
0x0014877c: 61 02 40 f9              ldr      x1, [x19]
0x00148780: e0 03 13 aa              mov      x0, x19
0x00148784: 21 0c 40 f9              ldr      x1, [x1, #0x18]
0x00148788: 20 00 3f d6              blr      x1
0x0014878c: bd ff ff 17              b        #0x148680                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace11StartThreadEv+0x1e0
0x00148790: f3 03 00 aa              mov      x19, x0
0x00148794: a0 1f 40 f9              ldr      x0, [x29, #0x38]
0x00148798: 01 00 80 d2              mov      x1, #0
0x0014879c: 65 01 fd 97              bl       #0x88d30                   ; -> pthread_equal@plt
0x001487a0: e0 f0 ff 34              cbz      w0, #0x1485bc
0x001487a4: e0 03 13 aa              mov      x0, x19
0x001487a8: c3 1e 18 94              bl       #0x7502b4                  ; -> _Unwind_Resume
0x001487ac: a1 27 40 f9              ldr      x1, [x29, #0x48]
0x001487b0: f3 03 00 aa              mov      x19, x0
0x001487b4: 81 ff ff b4              cbz      x1, #0x1487a4
0x001487b8: e0 03 01 aa              mov      x0, x1
0x001487bc: 8d 26 ff 97              bl       #0x1121f0                  ; -> _ZNSt16_Sp_counted_baseILN9__gnu_cxx12_Lock_policyE2EE10_M_releaseEv
0x001487c0: f9 ff ff 17              b        #0x1487a4                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace11StartThreadEv+0x304
0x001487c4: fa ff ff 17              b        #0x1487ac                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace11StartThreadEv+0x30c
0x001487c8: f3 03 00 aa              mov      x19, x0
0x001487cc: a0 1b 40 f9              ldr      x0, [x29, #0x30]
0x001487d0: 01 00 80 d2              mov      x1, #0
0x001487d4: 57 01 fd 97              bl       #0x88d30                   ; -> pthread_equal@plt
0x001487d8: 60 fe ff 35              cbnz     w0, #0x1487a4
0x001487dc: 7c 87 16 94              bl       #0x6ea5cc                  ; -> _ZSt9terminatev
