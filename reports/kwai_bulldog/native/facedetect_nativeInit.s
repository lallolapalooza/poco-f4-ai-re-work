; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/kwai_bulldog/native/libs/libfacedetectcontext.so
; target  : address 0x184b0
; start   : 0x184b0
; size    : 0x660
; bytes   : 0x660   instructions: 408
; generated: 2026-10-23T23:37:21

0x000184b0: ff 43 04 d1              sub      sp, sp, #0x110
0x000184b4: fc 6f 0b a9              stp      x28, x27, [sp, #0xb0]
0x000184b8: fa 67 0c a9              stp      x26, x25, [sp, #0xc0]
0x000184bc: f8 5f 0d a9              stp      x24, x23, [sp, #0xd0]
0x000184c0: f6 57 0e a9              stp      x22, x21, [sp, #0xe0]
0x000184c4: f4 4f 0f a9              stp      x20, x19, [sp, #0xf0]
0x000184c8: fd 7b 10 a9              stp      x29, x30, [sp, #0x100]
0x000184cc: fd 03 04 91              add      x29, sp, #0x100
0x000184d0: 5a d0 3b d5              mrs      x26, tpidr_el0
0x000184d4: 48 17 40 f9              ldr      x8, [x26, #0x28]
0x000184d8: a8 03 1a f8              stur     x8, [x29, #-0x60]
0x000184dc: a2 06 00 b4              cbz      x2, #0x185b0
0x000184e0: f7 03 00 aa              mov      x23, x0
0x000184e4: e0 03 1c 32              mov      w0, #0x10
0x000184e8: f5 03 04 2a              mov      w21, w4
0x000184ec: f8 03 03 2a              mov      w24, w3
0x000184f0: f9 03 02 aa              mov      x25, x2
0x000184f4: f6 03 01 aa              mov      x22, x1
0x000184f8: ee c6 ff 97              bl       #0xa0b0                    ; -> _Znwm@plt
0x000184fc: f3 03 00 aa              mov      x19, x0
0x00018500: 00 0b 80 52              mov      w0, #0x58
0x00018504: eb c6 ff 97              bl       #0xa0b0                    ; -> _Znwm@plt
0x00018508: 28 03 40 f9              ldr      x8, [x25]
0x0001850c: f4 03 00 aa              mov      x20, x0
0x00018510: e8 1b 00 f9              str      x8, [sp, #0x30]
0x00018514: 28 07 40 f9              ldr      x8, [x25, #8]
0x00018518: e8 1f 00 f9              str      x8, [sp, #0x38]
0x0001851c: c8 00 00 b4              cbz      x8, #0x18534
0x00018520: 08 21 00 91              add      x8, x8, #8
0x00018524: 09 7d 5f c8              ldxr     x9, [x8]
0x00018528: 29 05 00 91              add      x9, x9, #1
0x0001852c: 09 7d 0a c8              stxr     w10, x9, [x8]
0x00018530: aa ff ff 35              cbnz     w10, #0x18524
0x00018534: e1 c3 00 91              add      x1, sp, #0x30
0x00018538: e0 03 14 aa              mov      x0, x20
0x0001853c: e2 03 18 2a              mov      w2, w24
0x00018540: e3 03 15 2a              mov      w3, w21
0x00018544: ce c8 ff 97              bl       #0xa87c
0x00018548: 74 02 00 f9              str      x20, [x19]
0x0001854c: e0 03 1b 32              mov      w0, #0x20
0x00018550: d8 c6 ff 97              bl       #0xa0b0                    ; -> _Znwm@plt
0x00018554: f8 1f 40 f9              ldr      x24, [sp, #0x38]
0x00018558: 28 01 00 90              adrp     x8, #0x3c000
0x0001855c: f5 03 00 aa              mov      x21, x0
0x00018560: 08 c1 35 91              add      x8, x8, #0xd70
0x00018564: 08 7c 00 a9              stp      x8, xzr, [x0]
0x00018568: 1f 50 01 a9              stp      xzr, x20, [x0, #0x10]
0x0001856c: 60 06 00 f9              str      x0, [x19, #8]
0x00018570: 38 01 00 b4              cbz      x24, #0x18594
0x00018574: 08 23 00 91              add      x8, x24, #8
0x00018578: 09 fd 5f c8              ldaxr    x9, [x8]
0x0001857c: 2a 05 00 d1              sub      x10, x9, #1
0x00018580: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00018584: ab ff ff 35              cbnz     w11, #0x18578
0x00018588: 89 01 00 b4              cbz      x9, #0x185b8
0x0001858c: 74 56 40 a9              ldp      x20, x21, [x19]
0x00018590: 55 02 00 b4              cbz      x21, #0x185d8
0x00018594: a8 22 00 91              add      x8, x21, #8
0x00018598: 09 7d 5f c8              ldxr     x9, [x8]
0x0001859c: 29 05 00 91              add      x9, x9, #1
0x000185a0: 09 7d 0a c8              stxr     w10, x9, [x8]
0x000185a4: aa ff ff 35              cbnz     w10, #0x18598
0x000185a8: f8 03 1f 2a              mov      w24, wzr
0x000185ac: 0c 00 00 14              b        #0x185dc                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeInit+0x12c
0x000185b0: f3 03 1f aa              mov      x19, xzr
0x000185b4: c2 00 00 14              b        #0x188bc                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeInit+0x40c
0x000185b8: 08 03 40 f9              ldr      x8, [x24]
0x000185bc: e0 03 18 aa              mov      x0, x24
0x000185c0: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x000185c4: 00 01 3f d6              blr      x8
0x000185c8: e0 03 18 aa              mov      x0, x24
0x000185cc: c5 c6 ff 97              bl       #0xa0e0                    ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x000185d0: 74 56 40 a9              ldp      x20, x21, [x19]
0x000185d4: 15 fe ff b5              cbnz     x21, #0x18594
0x000185d8: f8 03 00 32              mov      w24, #1
0x000185dc: 88 02 40 f9              ldr      x8, [x20]
0x000185e0: 09 09 40 f9              ldr      x9, [x8, #0x10]
0x000185e4: e8 63 00 91              add      x8, sp, #0x18
0x000185e8: e0 03 14 aa              mov      x0, x20
0x000185ec: 20 01 3f d6              blr      x9
0x000185f0: e0 13 40 f9              ldr      x0, [sp, #0x20]
0x000185f4: 60 01 00 b4              cbz      x0, #0x18620
0x000185f8: 9e c7 ff 97              bl       #0xa470                    ; -> _ZNSt6__ndk119__shared_weak_count4lockEv@plt
0x000185fc: e9 a3 41 a9              ldp      x9, x8, [sp, #0x18]
0x00018600: 1f 00 00 f1              cmp      x0, #0
0x00018604: e0 0b 00 f9              str      x0, [sp, #0x10]
0x00018608: f9 03 89 9a              csel     x25, xzr, x9, eq
0x0001860c: 68 00 00 b4              cbz      x8, #0x18618
0x00018610: e0 03 08 aa              mov      x0, x8
0x00018614: b3 c6 ff 97              bl       #0xa0e0                    ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00018618: b8 00 00 36              tbz      w24, #0, #0x1862c
0x0001861c: 10 00 00 14              b        #0x1865c                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeInit+0x1ac
0x00018620: ff 0b 00 f9              str      xzr, [sp, #0x10]
0x00018624: f9 03 1f aa              mov      x25, xzr
0x00018628: b8 01 00 37              tbnz     w24, #0, #0x1865c
0x0001862c: a8 22 00 91              add      x8, x21, #8
0x00018630: 09 fd 5f c8              ldaxr    x9, [x8]
0x00018634: 2a 05 00 d1              sub      x10, x9, #1
0x00018638: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x0001863c: ab ff ff 35              cbnz     w11, #0x18630
0x00018640: e9 00 00 b5              cbnz     x9, #0x1865c
0x00018644: a8 02 40 f9              ldr      x8, [x21]
0x00018648: e0 03 15 aa              mov      x0, x21
0x0001864c: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00018650: 00 01 3f d6              blr      x8
0x00018654: e0 03 15 aa              mov      x0, x21
0x00018658: a2 c6 ff 97              bl       #0xa0e0                    ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x0001865c: e8 02 40 f9              ldr      x8, [x23]
0x00018660: 08 7d 40 f9              ldr      x8, [x8, #0xf8]
0x00018664: e0 03 17 aa              mov      x0, x23
0x00018668: e1 03 16 aa              mov      x1, x22
0x0001866c: 00 01 3f d6              blr      x8
0x00018670: fb 03 00 aa              mov      x27, x0
0x00018674: e0 63 00 91              add      x0, sp, #0x18
0x00018678: 66 c8 ff 97              bl       #0xa810                    ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededC1Ev@plt
0x0001867c: f8 13 40 f9              ldr      x24, [sp, #0x20]
0x00018680: f3 07 00 f9              str      x19, [sp, #8]
0x00018684: e0 07 1c 32              mov      w0, #0x30
0x00018688: 8a c6 ff 97              bl       #0xa0b0                    ; -> _Znwm@plt
0x0001868c: 28 01 00 90              adrp     x8, #0x3c000
0x00018690: 29 01 00 90              adrp     x9, #0x3c000
0x00018694: f5 03 00 aa              mov      x21, x0
0x00018698: 08 01 37 91              add      x8, x8, #0xdc0
0x0001869c: 29 41 38 91              add      x9, x9, #0xe10
0x000186a0: f4 03 00 aa              mov      x20, x0
0x000186a4: bf 8e 00 f8              str      xzr, [x21, #8]!
0x000186a8: 08 00 00 f9              str      x8, [x0]
0x000186ac: 1f 24 01 a9              stp      xzr, x9, [x0, #0x10]
0x000186b0: 18 6c 02 a9              stp      x24, x27, [x0, #0x20]
0x000186b4: e0 63 00 91              add      x0, sp, #0x18
0x000186b8: f3 03 1a aa              mov      x19, x26
0x000186bc: 7d c7 ff 97              bl       #0xa4b0                    ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000186c0: e0 0f 1d 32              mov      w0, #0x78
0x000186c4: 7b c6 ff 97              bl       #0xa0b0                    ; -> _Znwm@plt
0x000186c8: f8 03 00 aa              mov      x24, x0
0x000186cc: 28 01 00 90              adrp     x8, #0x3c000
0x000186d0: fc 03 00 aa              mov      x28, x0
0x000186d4: 08 01 1c 91              add      x8, x8, #0x700
0x000186d8: 1f 8f 00 f8              str      xzr, [x24, #8]!
0x000186dc: 1a 60 00 91              add      x26, x0, #0x18
0x000186e0: 1f 08 00 f9              str      xzr, [x0, #0x10]
0x000186e4: 08 00 00 f9              str      x8, [x0]
0x000186e8: e0 03 1a aa              mov      x0, x26
0x000186ec: e1 03 17 aa              mov      x1, x23
0x000186f0: e2 03 16 aa              mov      x2, x22
0x000186f4: e3 03 1b aa              mov      x3, x27
0x000186f8: 9e c7 ff 97              bl       #0xa570                    ; -> _ZN8kuaishou5dbase12AndroidClassC1EP7_JNIEnvP8_jobjectP7_jclass@plt
0x000186fc: f9 02 00 b4              cbz      x25, #0x18758
0x00018700: 08 7f 5f c8              ldxr     x8, [x24]
0x00018704: 08 05 00 91              add      x8, x8, #1
0x00018708: 08 7f 09 c8              stxr     w9, x8, [x24]
0x0001870c: a9 ff ff 35              cbnz     w9, #0x18700
0x00018710: 28 01 00 90              adrp     x8, #0x3c000
0x00018714: 08 01 39 91              add      x8, x8, #0xe40
0x00018718: f6 c3 01 91              add      x22, sp, #0x70
0x0001871c: e8 6b 07 a9              stp      x8, x26, [sp, #0x70]
0x00018720: fc 43 00 f9              str      x28, [sp, #0x80]
0x00018724: f6 4b 00 f9              str      x22, [sp, #0x90]
0x00018728: 28 03 40 f9              ldr      x8, [x25]
0x0001872c: 08 45 40 f9              ldr      x8, [x8, #0x88]
0x00018730: e1 c3 01 91              add      x1, sp, #0x70
0x00018734: e0 03 19 aa              mov      x0, x25
0x00018738: 00 01 3f d6              blr      x8
0x0001873c: e0 4b 40 f9              ldr      x0, [sp, #0x90]
0x00018740: df 02 00 eb              cmp      x22, x0
0x00018744: 00 01 00 54              b.eq     #0x18764
0x00018748: 40 01 00 b4              cbz      x0, #0x18770
0x0001874c: 08 00 40 f9              ldr      x8, [x0]
0x00018750: 08 15 40 f9              ldr      x8, [x8, #0x28]
0x00018754: 06 00 00 14              b        #0x1876c                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeInit+0x2bc
0x00018758: fa 03 13 aa              mov      x26, x19
0x0001875c: f3 07 40 f9              ldr      x19, [sp, #8]
0x00018760: 33 00 00 14              b        #0x1882c                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeInit+0x37c
0x00018764: 08 00 40 f9              ldr      x8, [x0]
0x00018768: 08 11 40 f9              ldr      x8, [x8, #0x20]
0x0001876c: 00 01 3f d6              blr      x8
0x00018770: 69 00 00 90              adrp     x9, #0x24000
0x00018774: 29 81 26 91              add      x9, x9, #0x9a0
0x00018778: 29 01 40 f9              ldr      x9, [x9]
0x0001877c: 88 02 80 52              mov      w8, #0x14
0x00018780: 8a 6c 8d 52              mov      w10, #0x6b64
0x00018784: ff ff 01 a9              stp      xzr, xzr, [sp, #0x18]
0x00018788: ff 17 00 f9              str      xzr, [sp, #0x28]
0x0001878c: e8 63 00 39              strb     w8, [sp, #0x18]
0x00018790: ea 13 02 78              sturh    w10, [sp, #0x21]
0x00018794: e9 93 01 f8              stur     x9, [sp, #0x19]
0x00018798: ff 8f 00 39              strb     wzr, [sp, #0x23]
0x0001879c: 08 7f 5f c8              ldxr     x8, [x24]
0x000187a0: 08 05 00 91              add      x8, x8, #1
0x000187a4: 08 7f 09 c8              stxr     w9, x8, [x24]
0x000187a8: a9 ff ff 35              cbnz     w9, #0x1879c
0x000187ac: 28 01 00 90              adrp     x8, #0x3c000
0x000187b0: 08 41 3b 91              add      x8, x8, #0xed0
0x000187b4: f6 03 01 91              add      x22, sp, #0x40
0x000187b8: e8 6b 04 a9              stp      x8, x26, [sp, #0x40]
0x000187bc: fc 2b 00 f9              str      x28, [sp, #0x50]
0x000187c0: f6 33 00 f9              str      x22, [sp, #0x60]
0x000187c4: 28 03 40 f9              ldr      x8, [x25]
0x000187c8: 08 49 40 f9              ldr      x8, [x8, #0x90]
0x000187cc: e1 03 01 91              add      x1, sp, #0x40
0x000187d0: e2 63 00 91              add      x2, sp, #0x18
0x000187d4: e0 03 19 aa              mov      x0, x25
0x000187d8: 00 01 3f d6              blr      x8
0x000187dc: e0 33 40 f9              ldr      x0, [sp, #0x60]
0x000187e0: fa 03 13 aa              mov      x26, x19
0x000187e4: df 02 00 eb              cmp      x22, x0
0x000187e8: 20 01 00 54              b.eq     #0x1880c
0x000187ec: f3 07 40 f9              ldr      x19, [sp, #8]
0x000187f0: 80 00 00 b4              cbz      x0, #0x18800
0x000187f4: 08 00 40 f9              ldr      x8, [x0]
0x000187f8: 08 15 40 f9              ldr      x8, [x8, #0x28]
0x000187fc: 00 01 3f d6              blr      x8
0x00018800: e8 63 40 39              ldrb     w8, [sp, #0x18]
0x00018804: 48 01 00 36              tbz      w8, #0, #0x1882c
0x00018808: 07 00 00 14              b        #0x18824                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeInit+0x374
0x0001880c: 08 00 40 f9              ldr      x8, [x0]
0x00018810: 08 11 40 f9              ldr      x8, [x8, #0x20]
0x00018814: 00 01 3f d6              blr      x8
0x00018818: f3 07 40 f9              ldr      x19, [sp, #8]
0x0001881c: e8 63 40 39              ldrb     w8, [sp, #0x18]
0x00018820: 68 00 00 36              tbz      w8, #0, #0x1882c
0x00018824: e0 17 40 f9              ldr      x0, [sp, #0x28]
0x00018828: 5e c6 ff 97              bl       #0xa1a0                    ; -> _ZdlPv@plt
0x0001882c: 08 ff 5f c8              ldaxr    x8, [x24]
0x00018830: 09 05 00 d1              sub      x9, x8, #1
0x00018834: 09 ff 0a c8              stlxr    w10, x9, [x24]
0x00018838: aa ff ff 35              cbnz     w10, #0x1882c
0x0001883c: e8 00 00 b5              cbnz     x8, #0x18858
0x00018840: 88 03 40 f9              ldr      x8, [x28]
0x00018844: e0 03 1c aa              mov      x0, x28
0x00018848: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x0001884c: 00 01 3f d6              blr      x8
0x00018850: e0 03 1c aa              mov      x0, x28
0x00018854: 23 c6 ff 97              bl       #0xa0e0                    ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00018858: a8 fe 5f c8              ldaxr    x8, [x21]
0x0001885c: 09 05 00 d1              sub      x9, x8, #1
0x00018860: a9 fe 0a c8              stlxr    w10, x9, [x21]
0x00018864: aa ff ff 35              cbnz     w10, #0x18858
0x00018868: e8 00 00 b5              cbnz     x8, #0x18884
0x0001886c: 88 02 40 f9              ldr      x8, [x20]
0x00018870: e0 03 14 aa              mov      x0, x20
0x00018874: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00018878: 00 01 3f d6              blr      x8
0x0001887c: e0 03 14 aa              mov      x0, x20
0x00018880: 18 c6 ff 97              bl       #0xa0e0                    ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00018884: f4 0b 40 f9              ldr      x20, [sp, #0x10]
0x00018888: b4 01 00 b4              cbz      x20, #0x188bc
0x0001888c: 89 22 00 91              add      x9, x20, #8
0x00018890: 28 fd 5f c8              ldaxr    x8, [x9]
0x00018894: 0a 05 00 d1              sub      x10, x8, #1
0x00018898: 2a fd 0b c8              stlxr    w11, x10, [x9]
0x0001889c: ab ff ff 35              cbnz     w11, #0x18890
0x000188a0: e8 00 00 b5              cbnz     x8, #0x188bc
0x000188a4: 88 02 40 f9              ldr      x8, [x20]
0x000188a8: e0 03 14 aa              mov      x0, x20
0x000188ac: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x000188b0: 00 01 3f d6              blr      x8
0x000188b4: e0 03 14 aa              mov      x0, x20
0x000188b8: 0a c6 ff 97              bl       #0xa0e0                    ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x000188bc: 48 17 40 f9              ldr      x8, [x26, #0x28]
0x000188c0: a9 03 5a f8              ldur     x9, [x29, #-0x60]
0x000188c4: 1f 01 09 eb              cmp      x8, x9
0x000188c8: 41 01 00 54              b.ne     #0x188f0
0x000188cc: e0 03 13 aa              mov      x0, x19
0x000188d0: fd 7b 50 a9              ldp      x29, x30, [sp, #0x100]
0x000188d4: f4 4f 4f a9              ldp      x20, x19, [sp, #0xf0]
0x000188d8: f6 57 4e a9              ldp      x22, x21, [sp, #0xe0]
0x000188dc: f8 5f 4d a9              ldp      x24, x23, [sp, #0xd0]
0x000188e0: fa 67 4c a9              ldp      x26, x25, [sp, #0xc0]
0x000188e4: fc 6f 4b a9              ldp      x28, x27, [sp, #0xb0]
0x000188e8: ff 43 04 91              add      sp, sp, #0x110
0x000188ec: c0 03 5f d6              ret      
0x000188f0: 0c c6 ff 97              bl       #0xa120                    ; -> __stack_chk_fail@plt
0x000188f4: e8 33 40 f9              ldr      x8, [sp, #0x60]
0x000188f8: df 02 08 eb              cmp      x22, x8
0x000188fc: f6 03 00 aa              mov      x22, x0
0x00018900: a0 00 00 54              b.eq     #0x18914
0x00018904: 08 01 00 b4              cbz      x8, #0x18924
0x00018908: 09 01 40 f9              ldr      x9, [x8]
0x0001890c: 29 15 40 f9              ldr      x9, [x9, #0x28]
0x00018910: 03 00 00 14              b        #0x1891c                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeInit+0x46c
0x00018914: 09 01 40 f9              ldr      x9, [x8]
0x00018918: 29 11 40 f9              ldr      x9, [x9, #0x20]
0x0001891c: e0 03 08 aa              mov      x0, x8
0x00018920: 20 01 3f d6              blr      x9
0x00018924: e8 63 40 39              ldrb     w8, [sp, #0x18]
0x00018928: 08 02 00 36              tbz      w8, #0, #0x18968
0x0001892c: e0 17 40 f9              ldr      x0, [sp, #0x28]
0x00018930: 1c c6 ff 97              bl       #0xa1a0                    ; -> _ZdlPv@plt
0x00018934: 0d 00 00 14              b        #0x18968                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeInit+0x4b8
0x00018938: e8 4b 40 f9              ldr      x8, [sp, #0x90]
0x0001893c: df 02 08 eb              cmp      x22, x8
0x00018940: f6 03 00 aa              mov      x22, x0
0x00018944: a0 00 00 54              b.eq     #0x18958
0x00018948: 08 01 00 b4              cbz      x8, #0x18968
0x0001894c: 09 01 40 f9              ldr      x9, [x8]
0x00018950: 29 15 40 f9              ldr      x9, [x9, #0x28]
0x00018954: 03 00 00 14              b        #0x18960                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeInit+0x4b0
0x00018958: 09 01 40 f9              ldr      x9, [x8]
0x0001895c: 29 11 40 f9              ldr      x9, [x9, #0x20]
0x00018960: e0 03 08 aa              mov      x0, x8
0x00018964: 20 01 3f d6              blr      x9
0x00018968: 08 ff 5f c8              ldaxr    x8, [x24]
0x0001896c: 09 05 00 d1              sub      x9, x8, #1
0x00018970: 09 ff 0a c8              stlxr    w10, x9, [x24]
0x00018974: aa ff ff 35              cbnz     w10, #0x18968
0x00018978: e8 01 00 b5              cbnz     x8, #0x189b4
0x0001897c: 88 03 40 f9              ldr      x8, [x28]
0x00018980: e0 03 1c aa              mov      x0, x28
0x00018984: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00018988: 00 01 3f d6              blr      x8
0x0001898c: e0 03 1c aa              mov      x0, x28
0x00018990: d4 c5 ff 97              bl       #0xa0e0                    ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00018994: 08 00 00 14              b        #0x189b4                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeInit+0x504
0x00018998: f6 03 00 aa              mov      x22, x0
0x0001899c: e0 03 1c aa              mov      x0, x28
0x000189a0: 80 c7 ff 97              bl       #0xa7a0                    ; -> _ZNSt6__ndk119__shared_weak_countD2Ev@plt
0x000189a4: e0 03 1c aa              mov      x0, x28
0x000189a8: fe c5 ff 97              bl       #0xa1a0                    ; -> _ZdlPv@plt
0x000189ac: 02 00 00 14              b        #0x189b4                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeInit+0x504
0x000189b0: f6 03 00 aa              mov      x22, x0
0x000189b4: a8 fe 5f c8              ldaxr    x8, [x21]
0x000189b8: 09 05 00 d1              sub      x9, x8, #1
0x000189bc: a9 fe 0a c8              stlxr    w10, x9, [x21]
0x000189c0: aa ff ff 35              cbnz     w10, #0x189b4
0x000189c4: 28 08 00 b5              cbnz     x8, #0x18ac8
0x000189c8: 88 02 40 f9              ldr      x8, [x20]
0x000189cc: e0 03 14 aa              mov      x0, x20
0x000189d0: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x000189d4: 00 01 3f d6              blr      x8
0x000189d8: e0 03 14 aa              mov      x0, x20
0x000189dc: c1 c5 ff 97              bl       #0xa0e0                    ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x000189e0: e8 0b 40 f9              ldr      x8, [sp, #0x10]
0x000189e4: 68 07 00 b5              cbnz     x8, #0x18ad0
0x000189e8: 48 00 00 14              b        #0x18b08                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeInit+0x658
0x000189ec: f6 03 00 aa              mov      x22, x0
0x000189f0: e0 63 00 91              add      x0, sp, #0x18
0x000189f4: af c6 ff 97              bl       #0xa4b0                    ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000189f8: e8 0b 40 f9              ldr      x8, [sp, #0x10]
0x000189fc: a8 06 00 b5              cbnz     x8, #0x18ad0
0x00018a00: 42 00 00 14              b        #0x18b08                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeInit+0x658
0x00018a04: f6 03 00 aa              mov      x22, x0
0x00018a08: 18 08 00 37              tbnz     w24, #0, #0x18b08
0x00018a0c: a8 22 00 91              add      x8, x21, #8
0x00018a10: 09 fd 5f c8              ldaxr    x9, [x8]
0x00018a14: 2a 05 00 d1              sub      x10, x9, #1
0x00018a18: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00018a1c: ab ff ff 35              cbnz     w11, #0x18a10
0x00018a20: 49 07 00 b5              cbnz     x9, #0x18b08
0x00018a24: a8 02 40 f9              ldr      x8, [x21]
0x00018a28: e0 03 15 aa              mov      x0, x21
0x00018a2c: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00018a30: 00 01 3f d6              blr      x8
0x00018a34: e0 03 15 aa              mov      x0, x21
0x00018a38: 33 00 00 14              b        #0x18b04                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeInit+0x654
0x00018a3c: 88 02 40 f9              ldr      x8, [x20]
0x00018a40: f6 03 00 aa              mov      x22, x0
0x00018a44: e0 03 14 aa              mov      x0, x20
0x00018a48: 08 05 40 f9              ldr      x8, [x8, #8]
0x00018a4c: 00 01 3f d6              blr      x8
0x00018a50: f7 03 1f 2a              mov      w23, wzr
0x00018a54: f5 1f 40 f9              ldr      x21, [sp, #0x38]
0x00018a58: d5 00 00 b5              cbnz     x21, #0x18a70
0x00018a5c: 11 00 00 14              b        #0x18aa0                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeInit+0x5f0
0x00018a60: f6 03 00 aa              mov      x22, x0
0x00018a64: f7 03 00 32              mov      w23, #1
0x00018a68: f5 1f 40 f9              ldr      x21, [sp, #0x38]
0x00018a6c: b5 01 00 b4              cbz      x21, #0x18aa0
0x00018a70: a8 22 00 91              add      x8, x21, #8
0x00018a74: 09 fd 5f c8              ldaxr    x9, [x8]
0x00018a78: 2a 05 00 d1              sub      x10, x9, #1
0x00018a7c: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00018a80: ab ff ff 35              cbnz     w11, #0x18a74
0x00018a84: e9 00 00 b5              cbnz     x9, #0x18aa0
0x00018a88: a8 02 40 f9              ldr      x8, [x21]
0x00018a8c: e0 03 15 aa              mov      x0, x21
0x00018a90: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00018a94: 00 01 3f d6              blr      x8
0x00018a98: e0 03 15 aa              mov      x0, x21
0x00018a9c: 91 c5 ff 97              bl       #0xa0e0                    ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00018aa0: b7 00 00 34              cbz      w23, #0x18ab4
0x00018aa4: e0 03 14 aa              mov      x0, x20
0x00018aa8: be c5 ff 97              bl       #0xa1a0                    ; -> _ZdlPv@plt
0x00018aac: 02 00 00 14              b        #0x18ab4                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeInit+0x604
0x00018ab0: f6 03 00 aa              mov      x22, x0
0x00018ab4: e0 03 13 aa              mov      x0, x19
0x00018ab8: ba c5 ff 97              bl       #0xa1a0                    ; -> _ZdlPv@plt
0x00018abc: e0 03 16 aa              mov      x0, x22
0x00018ac0: 0c c6 ff 97              bl       #0xa2f0                    ; -> _Unwind_Resume@plt
0x00018ac4: f6 03 00 aa              mov      x22, x0
0x00018ac8: e8 0b 40 f9              ldr      x8, [sp, #0x10]
0x00018acc: e8 01 00 b4              cbz      x8, #0x18b08
0x00018ad0: e8 0b 40 f9              ldr      x8, [sp, #0x10]
0x00018ad4: 08 21 00 91              add      x8, x8, #8
0x00018ad8: 09 fd 5f c8              ldaxr    x9, [x8]
0x00018adc: 2a 05 00 d1              sub      x10, x9, #1
0x00018ae0: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00018ae4: ab ff ff 35              cbnz     w11, #0x18ad8
0x00018ae8: 09 01 00 b5              cbnz     x9, #0x18b08
0x00018aec: f3 0b 40 f9              ldr      x19, [sp, #0x10]
0x00018af0: 68 02 40 f9              ldr      x8, [x19]
0x00018af4: e0 03 13 aa              mov      x0, x19
0x00018af8: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00018afc: 00 01 3f d6              blr      x8
0x00018b00: e0 03 13 aa              mov      x0, x19
0x00018b04: 77 c5 ff 97              bl       #0xa0e0                    ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00018b08: e0 03 16 aa              mov      x0, x22
0x00018b0c: f9 c5 ff 97              bl       #0xa2f0                    ; -> _Unwind_Resume@plt
