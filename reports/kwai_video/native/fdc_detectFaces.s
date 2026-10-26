; disasm_elf.py (capstone 5.0.7)
; file    : /tmp/kwai_libs/lib/arm64-v8a/libfacedetectcontext.so
; target  : Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeDetectFacesFromFrame (0x1a19c)
; start   : 0x1a19c
; size    : 0x520
; bytes   : 0x520   instructions: 328
; generated: 2026-10-23T23:49:13

0x0001a19c: ff c3 03 d1              sub      sp, sp, #0xf0
0x0001a1a0: fb 4b 00 f9              str      x27, [sp, #0x90]
0x0001a1a4: fa 67 0a a9              stp      x26, x25, [sp, #0xa0]
0x0001a1a8: f8 5f 0b a9              stp      x24, x23, [sp, #0xb0]
0x0001a1ac: f6 57 0c a9              stp      x22, x21, [sp, #0xc0]
0x0001a1b0: f4 4f 0d a9              stp      x20, x19, [sp, #0xd0]
0x0001a1b4: fd 7b 0e a9              stp      x29, x30, [sp, #0xe0]
0x0001a1b8: fd 83 03 91              add      x29, sp, #0xe0
0x0001a1bc: 5a d0 3b d5              mrs      x26, tpidr_el0
0x0001a1c0: 48 17 40 f9              ldr      x8, [x26, #0x28]
0x0001a1c4: a8 83 1a f8              stur     x8, [x29, #-0x58]
0x0001a1c8: 82 16 00 b4              cbz      x2, #0x1a498
0x0001a1cc: e1 03 03 aa              mov      x1, x3
0x0001a1d0: f6 03 04 2a              mov      w22, w4
0x0001a1d4: f8 03 02 aa              mov      x24, x2
0x0001a1d8: f4 03 00 aa              mov      x20, x0
0x0001a1dc: c9 bf ff 97              bl       #0xa100                    ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject@plt
0x0001a1e0: f7 03 00 aa              mov      x23, x0
0x0001a1e4: e0 03 1b 32              mov      w0, #0x20
0x0001a1e8: b2 bf ff 97              bl       #0xa0b0                    ; -> _Znwm@plt
0x0001a1ec: 08 01 00 d0              adrp     x8, #0x3c000
0x0001a1f0: f9 03 00 aa              mov      x25, x0
0x0001a1f4: 08 81 3d 91              add      x8, x8, #0xf60
0x0001a1f8: 1f 5c 01 a9              stp      xzr, x23, [x0, #0x10]
0x0001a1fc: 3f 8f 00 f8              str      xzr, [x25, #8]!
0x0001a200: 08 00 00 f9              str      x8, [x0]
0x0001a204: f3 03 00 aa              mov      x19, x0
0x0001a208: 00 57 40 a9              ldp      x0, x21, [x24]
0x0001a20c: d5 00 00 b4              cbz      x21, #0x1a224
0x0001a210: a8 22 00 91              add      x8, x21, #8
0x0001a214: 09 7d 5f c8              ldxr     x9, [x8]
0x0001a218: 29 05 00 91              add      x9, x9, #1
0x0001a21c: 09 7d 0a c8              stxr     w10, x9, [x8]
0x0001a220: aa ff ff 35              cbnz     w10, #0x1a214
0x0001a224: f7 cf 00 a9              stp      x23, x19, [sp, #8]
0x0001a228: 28 7f 5f c8              ldxr     x8, [x25]
0x0001a22c: 08 05 00 91              add      x8, x8, #1
0x0001a230: 28 7f 09 c8              stxr     w9, x8, [x25]
0x0001a234: a9 ff ff 35              cbnz     w9, #0x1a228
0x0001a238: 08 00 40 f9              ldr      x8, [x0]
0x0001a23c: 09 35 40 f9              ldr      x9, [x8, #0x68]
0x0001a240: e8 63 00 91              add      x8, sp, #0x18
0x0001a244: e1 23 00 91              add      x1, sp, #8
0x0001a248: e2 03 16 2a              mov      w2, w22
0x0001a24c: 20 01 3f d6              blr      x9
0x0001a250: f6 0b 40 f9              ldr      x22, [sp, #0x10]
0x0001a254: f6 00 00 b4              cbz      x22, #0x1a270
0x0001a258: c8 22 00 91              add      x8, x22, #8
0x0001a25c: 09 fd 5f c8              ldaxr    x9, [x8]
0x0001a260: 2a 05 00 d1              sub      x10, x9, #1
0x0001a264: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x0001a268: ab ff ff 35              cbnz     w11, #0x1a25c
0x0001a26c: 49 0e 00 b4              cbz      x9, #0x1a434
0x0001a270: f5 00 00 b4              cbz      x21, #0x1a28c
0x0001a274: a8 22 00 91              add      x8, x21, #8
0x0001a278: 09 fd 5f c8              ldaxr    x9, [x8]
0x0001a27c: 2a 05 00 d1              sub      x10, x9, #1
0x0001a280: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x0001a284: ab ff ff 35              cbnz     w11, #0x1a278
0x0001a288: 69 0e 00 b4              cbz      x9, #0x1a454
0x0001a28c: f5 23 40 b9              ldr      w21, [sp, #0x20]
0x0001a290: 35 0f 00 34              cbz      w21, #0x1a474
0x0001a294: 88 02 40 f9              ldr      x8, [x20]
0x0001a298: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x0001a29c: 41 00 00 b0              adrp     x1, #0x23000
0x0001a2a0: 21 88 2b 91              add      x1, x1, #0xae2
0x0001a2a4: e0 03 14 aa              mov      x0, x20
0x0001a2a8: 00 01 3f d6              blr      x8
0x0001a2ac: f7 03 00 aa              mov      x23, x0
0x0001a2b0: e0 c3 00 91              add      x0, sp, #0x30
0x0001a2b4: 57 c1 ff 97              bl       #0xa810                    ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededC1Ev@plt
0x0001a2b8: fb 1f 40 f9              ldr      x27, [sp, #0x38]
0x0001a2bc: e0 07 1c 32              mov      w0, #0x30
0x0001a2c0: 7c bf ff 97              bl       #0xa0b0                    ; -> _Znwm@plt
0x0001a2c4: 08 01 00 d0              adrp     x8, #0x3c000
0x0001a2c8: 09 01 00 d0              adrp     x9, #0x3c000
0x0001a2cc: f8 03 00 aa              mov      x24, x0
0x0001a2d0: 08 01 37 91              add      x8, x8, #0xdc0
0x0001a2d4: 29 41 38 91              add      x9, x9, #0xe10
0x0001a2d8: f6 03 00 aa              mov      x22, x0
0x0001a2dc: 1f 8f 00 f8              str      xzr, [x24, #8]!
0x0001a2e0: 08 00 00 f9              str      x8, [x0]
0x0001a2e4: 1f 24 01 a9              stp      xzr, x9, [x0, #0x10]
0x0001a2e8: 1b 5c 02 a9              stp      x27, x23, [x0, #0x20]
0x0001a2ec: e0 c3 00 91              add      x0, sp, #0x30
0x0001a2f0: 70 c0 ff 97              bl       #0xa4b0                    ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x0001a2f4: 88 02 40 f9              ldr      x8, [x20]
0x0001a2f8: c2 16 40 f9              ldr      x2, [x22, #0x28]
0x0001a2fc: 08 b1 42 f9              ldr      x8, [x8, #0x560]
0x0001a300: e0 03 14 aa              mov      x0, x20
0x0001a304: e1 03 15 2a              mov      w1, w21
0x0001a308: e3 03 1f aa              mov      x3, xzr
0x0001a30c: 00 01 3f d6              blr      x8
0x0001a310: f5 03 00 aa              mov      x21, x0
0x0001a314: 08 ff 5f c8              ldaxr    x8, [x24]
0x0001a318: 09 05 00 d1              sub      x9, x8, #1
0x0001a31c: 09 ff 0a c8              stlxr    w10, x9, [x24]
0x0001a320: aa ff ff 35              cbnz     w10, #0x1a314
0x0001a324: e8 00 00 b5              cbnz     x8, #0x1a340
0x0001a328: c8 02 40 f9              ldr      x8, [x22]
0x0001a32c: e0 03 16 aa              mov      x0, x22
0x0001a330: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x0001a334: 00 01 3f d6              blr      x8
0x0001a338: e0 03 16 aa              mov      x0, x22
0x0001a33c: 69 bf ff 97              bl       #0xa0e0                    ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x0001a340: e8 23 40 b9              ldr      w8, [sp, #0x20]
0x0001a344: 1f 05 00 71              cmp      w8, #1
0x0001a348: eb 04 00 54              b.lt     #0x1a3e4
0x0001a34c: f6 03 1f aa              mov      x22, xzr
0x0001a350: e8 17 40 f9              ldr      x8, [sp, #0x28]
0x0001a354: 08 0d 16 8b              add      x8, x8, x22, lsl #3
0x0001a358: 01 05 40 f9              ldr      x1, [x8, #8]
0x0001a35c: e0 03 01 91              add      x0, sp, #0x40
0x0001a360: 14 c0 ff 97              bl       #0xa3b0                    ; -> _ZN8kuaishou5model8FaceDataC1ERKS1_@plt
0x0001a364: e8 c3 00 91              add      x8, sp, #0x30
0x0001a368: e1 03 01 91              add      x1, sp, #0x40
0x0001a36c: e0 03 14 aa              mov      x0, x20
0x0001a370: d3 00 00 94              bl       #0x1a6bc
0x0001a374: e8 1b 40 f9              ldr      x8, [sp, #0x30]
0x0001a378: 89 02 40 f9              ldr      x9, [x20]
0x0001a37c: 03 09 40 f9              ldr      x3, [x8, #0x10]
0x0001a380: 28 b9 42 f9              ldr      x8, [x9, #0x570]
0x0001a384: e0 03 14 aa              mov      x0, x20
0x0001a388: e1 03 15 aa              mov      x1, x21
0x0001a38c: e2 03 16 2a              mov      w2, w22
0x0001a390: 00 01 3f d6              blr      x8
0x0001a394: f7 1f 40 f9              ldr      x23, [sp, #0x38]
0x0001a398: b7 01 00 b4              cbz      x23, #0x1a3cc
0x0001a39c: e8 22 00 91              add      x8, x23, #8
0x0001a3a0: 09 fd 5f c8              ldaxr    x9, [x8]
0x0001a3a4: 2a 05 00 d1              sub      x10, x9, #1
0x0001a3a8: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x0001a3ac: ab ff ff 35              cbnz     w11, #0x1a3a0
0x0001a3b0: e9 00 00 b5              cbnz     x9, #0x1a3cc
0x0001a3b4: e8 02 40 f9              ldr      x8, [x23]
0x0001a3b8: e0 03 17 aa              mov      x0, x23
0x0001a3bc: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x0001a3c0: 00 01 3f d6              blr      x8
0x0001a3c4: e0 03 17 aa              mov      x0, x23
0x0001a3c8: 46 bf ff 97              bl       #0xa0e0                    ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x0001a3cc: e0 03 01 91              add      x0, sp, #0x40
0x0001a3d0: 70 c0 ff 97              bl       #0xa590                    ; -> _ZN8kuaishou5model8FaceDataD1Ev@plt
0x0001a3d4: e8 23 80 b9              ldrsw    x8, [sp, #0x20]
0x0001a3d8: d6 06 00 91              add      x22, x22, #1
0x0001a3dc: df 02 08 eb              cmp      x22, x8
0x0001a3e0: 8b fb ff 54              b.lt     #0x1a350
0x0001a3e4: f4 03 1f 2a              mov      w20, wzr
0x0001a3e8: e0 17 40 f9              ldr      x0, [sp, #0x28]
0x0001a3ec: a0 04 00 b4              cbz      x0, #0x1a480
0x0001a3f0: e8 0f 40 f9              ldr      x8, [sp, #0x18]
0x0001a3f4: 68 04 00 b5              cbnz     x8, #0x1a480
0x0001a3f8: 16 00 40 b9              ldr      w22, [x0]
0x0001a3fc: df 06 00 71              cmp      w22, #1
0x0001a400: 6b 01 00 54              b.lt     #0x1a42c
0x0001a404: 17 20 00 91              add      x23, x0, #8
0x0001a408: e0 02 40 f9              ldr      x0, [x23]
0x0001a40c: 80 00 00 b4              cbz      x0, #0x1a41c
0x0001a410: 08 00 40 f9              ldr      x8, [x0]
0x0001a414: 08 05 40 f9              ldr      x8, [x8, #8]
0x0001a418: 00 01 3f d6              blr      x8
0x0001a41c: d6 06 00 f1              subs     x22, x22, #1
0x0001a420: f7 22 00 91              add      x23, x23, #8
0x0001a424: 21 ff ff 54              b.ne     #0x1a408
0x0001a428: e0 17 40 f9              ldr      x0, [sp, #0x28]
0x0001a42c: 5d bf ff 97              bl       #0xa1a0                    ; -> _ZdlPv@plt
0x0001a430: 14 00 00 14              b        #0x1a480                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeDetectFacesFromFrame+0x2e4
0x0001a434: c8 02 40 f9              ldr      x8, [x22]
0x0001a438: e0 03 16 aa              mov      x0, x22
0x0001a43c: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x0001a440: 00 01 3f d6              blr      x8
0x0001a444: e0 03 16 aa              mov      x0, x22
0x0001a448: 26 bf ff 97              bl       #0xa0e0                    ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x0001a44c: 55 f1 ff b5              cbnz     x21, #0x1a274
0x0001a450: 8f ff ff 17              b        #0x1a28c                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeDetectFacesFromFrame+0xf0
0x0001a454: a8 02 40 f9              ldr      x8, [x21]
0x0001a458: e0 03 15 aa              mov      x0, x21
0x0001a45c: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x0001a460: 00 01 3f d6              blr      x8
0x0001a464: e0 03 15 aa              mov      x0, x21
0x0001a468: 1e bf ff 97              bl       #0xa0e0                    ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x0001a46c: f5 23 40 b9              ldr      w21, [sp, #0x20]
0x0001a470: 35 f1 ff 35              cbnz     w21, #0x1a294
0x0001a474: f4 03 00 32              mov      w20, #1
0x0001a478: e0 17 40 f9              ldr      x0, [sp, #0x28]
0x0001a47c: a0 fb ff b5              cbnz     x0, #0x1a3f0
0x0001a480: 28 ff 5f c8              ldaxr    x8, [x25]
0x0001a484: 09 05 00 d1              sub      x9, x8, #1
0x0001a488: 29 ff 0a c8              stlxr    w10, x9, [x25]
0x0001a48c: aa ff ff 35              cbnz     w10, #0x1a480
0x0001a490: 08 02 00 b4              cbz      x8, #0x1a4d0
0x0001a494: 54 00 00 36              tbz      w20, #0, #0x1a49c
0x0001a498: f5 03 1f aa              mov      x21, xzr
0x0001a49c: 48 17 40 f9              ldr      x8, [x26, #0x28]
0x0001a4a0: a9 83 5a f8              ldur     x9, [x29, #-0x58]
0x0001a4a4: 1f 01 09 eb              cmp      x8, x9
0x0001a4a8: 41 02 00 54              b.ne     #0x1a4f0
0x0001a4ac: e0 03 15 aa              mov      x0, x21
0x0001a4b0: fd 7b 4e a9              ldp      x29, x30, [sp, #0xe0]
0x0001a4b4: f4 4f 4d a9              ldp      x20, x19, [sp, #0xd0]
0x0001a4b8: f6 57 4c a9              ldp      x22, x21, [sp, #0xc0]
0x0001a4bc: f8 5f 4b a9              ldp      x24, x23, [sp, #0xb0]
0x0001a4c0: fa 67 4a a9              ldp      x26, x25, [sp, #0xa0]
0x0001a4c4: fb 4b 40 f9              ldr      x27, [sp, #0x90]
0x0001a4c8: ff c3 03 91              add      sp, sp, #0xf0
0x0001a4cc: c0 03 5f d6              ret      
0x0001a4d0: 68 02 40 f9              ldr      x8, [x19]
0x0001a4d4: e0 03 13 aa              mov      x0, x19
0x0001a4d8: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x0001a4dc: 00 01 3f d6              blr      x8
0x0001a4e0: e0 03 13 aa              mov      x0, x19
0x0001a4e4: ff be ff 97              bl       #0xa0e0                    ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x0001a4e8: 94 fd 07 37              tbnz     w20, #0, #0x1a498
0x0001a4ec: ec ff ff 17              b        #0x1a49c                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeDetectFacesFromFrame+0x300
0x0001a4f0: 0c bf ff 97              bl       #0xa120                    ; -> __stack_chk_fail@plt
0x0001a4f4: f4 03 00 aa              mov      x20, x0
0x0001a4f8: 08 ff 5f c8              ldaxr    x8, [x24]
0x0001a4fc: 09 05 00 d1              sub      x9, x8, #1
0x0001a500: 09 ff 0a c8              stlxr    w10, x9, [x24]
0x0001a504: aa ff ff 35              cbnz     w10, #0x1a4f8
0x0001a508: a8 09 00 b5              cbnz     x8, #0x1a63c
0x0001a50c: c8 02 40 f9              ldr      x8, [x22]
0x0001a510: e0 03 16 aa              mov      x0, x22
0x0001a514: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x0001a518: 00 01 3f d6              blr      x8
0x0001a51c: e0 03 16 aa              mov      x0, x22
0x0001a520: f0 be ff 97              bl       #0xa0e0                    ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x0001a524: e0 17 40 f9              ldr      x0, [sp, #0x28]
0x0001a528: e0 08 00 b5              cbnz     x0, #0x1a644
0x0001a52c: 56 00 00 14              b        #0x1a684                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeDetectFacesFromFrame+0x4e8
0x0001a530: f4 03 00 aa              mov      x20, x0
0x0001a534: e0 c3 00 91              add      x0, sp, #0x30
0x0001a538: de bf ff 97              bl       #0xa4b0                    ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x0001a53c: e0 17 40 f9              ldr      x0, [sp, #0x28]
0x0001a540: 20 08 00 b5              cbnz     x0, #0x1a644
0x0001a544: 50 00 00 14              b        #0x1a684                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeDetectFacesFromFrame+0x4e8
0x0001a548: f6 0b 40 f9              ldr      x22, [sp, #0x10]
0x0001a54c: f4 03 00 aa              mov      x20, x0
0x0001a550: b6 01 00 b4              cbz      x22, #0x1a584
0x0001a554: c8 22 00 91              add      x8, x22, #8
0x0001a558: 09 fd 5f c8              ldaxr    x9, [x8]
0x0001a55c: 2a 05 00 d1              sub      x10, x9, #1
0x0001a560: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x0001a564: ab ff ff 35              cbnz     w11, #0x1a558
0x0001a568: e9 00 00 b5              cbnz     x9, #0x1a584
0x0001a56c: c8 02 40 f9              ldr      x8, [x22]
0x0001a570: e0 03 16 aa              mov      x0, x22
0x0001a574: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x0001a578: 00 01 3f d6              blr      x8
0x0001a57c: e0 03 16 aa              mov      x0, x22
0x0001a580: d8 be ff 97              bl       #0xa0e0                    ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x0001a584: 35 08 00 b4              cbz      x21, #0x1a688
0x0001a588: a8 22 00 91              add      x8, x21, #8
0x0001a58c: 09 fd 5f c8              ldaxr    x9, [x8]
0x0001a590: 2a 05 00 d1              sub      x10, x9, #1
0x0001a594: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x0001a598: ab ff ff 35              cbnz     w11, #0x1a58c
0x0001a59c: 69 07 00 b5              cbnz     x9, #0x1a688
0x0001a5a0: a8 02 40 f9              ldr      x8, [x21]
0x0001a5a4: e0 03 15 aa              mov      x0, x21
0x0001a5a8: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x0001a5ac: 00 01 3f d6              blr      x8
0x0001a5b0: e0 03 15 aa              mov      x0, x21
0x0001a5b4: cb be ff 97              bl       #0xa0e0                    ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x0001a5b8: 34 00 00 14              b        #0x1a688                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeDetectFacesFromFrame+0x4ec
0x0001a5bc: f4 03 00 aa              mov      x20, x0
0x0001a5c0: b7 07 00 b4              cbz      x23, #0x1a6b4
0x0001a5c4: e8 02 40 f9              ldr      x8, [x23]
0x0001a5c8: e0 03 17 aa              mov      x0, x23
0x0001a5cc: 08 05 40 f9              ldr      x8, [x8, #8]
0x0001a5d0: 00 01 3f d6              blr      x8
0x0001a5d4: e0 03 14 aa              mov      x0, x20
0x0001a5d8: 46 bf ff 97              bl       #0xa2f0                    ; -> _Unwind_Resume@plt
0x0001a5dc: 17 00 00 14              b        #0x1a638                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeDetectFacesFromFrame+0x49c
0x0001a5e0: f5 1f 40 f9              ldr      x21, [sp, #0x38]
0x0001a5e4: f4 03 00 aa              mov      x20, x0
0x0001a5e8: f5 01 00 b4              cbz      x21, #0x1a624
0x0001a5ec: a8 22 00 91              add      x8, x21, #8
0x0001a5f0: 09 fd 5f c8              ldaxr    x9, [x8]
0x0001a5f4: 2a 05 00 d1              sub      x10, x9, #1
0x0001a5f8: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x0001a5fc: ab ff ff 35              cbnz     w11, #0x1a5f0
0x0001a600: 29 01 00 b5              cbnz     x9, #0x1a624
0x0001a604: a8 02 40 f9              ldr      x8, [x21]
0x0001a608: e0 03 15 aa              mov      x0, x21
0x0001a60c: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x0001a610: 00 01 3f d6              blr      x8
0x0001a614: e0 03 15 aa              mov      x0, x21
0x0001a618: b2 be ff 97              bl       #0xa0e0                    ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x0001a61c: 02 00 00 14              b        #0x1a624                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeDetectFacesFromFrame+0x488
0x0001a620: f4 03 00 aa              mov      x20, x0
0x0001a624: e0 03 01 91              add      x0, sp, #0x40
0x0001a628: da bf ff 97              bl       #0xa590                    ; -> _ZN8kuaishou5model8FaceDataD1Ev@plt
0x0001a62c: e0 17 40 f9              ldr      x0, [sp, #0x28]
0x0001a630: a0 00 00 b5              cbnz     x0, #0x1a644
0x0001a634: 14 00 00 14              b        #0x1a684                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeDetectFacesFromFrame+0x4e8
0x0001a638: f4 03 00 aa              mov      x20, x0
0x0001a63c: e0 17 40 f9              ldr      x0, [sp, #0x28]
0x0001a640: 20 02 00 b4              cbz      x0, #0x1a684
0x0001a644: e8 0f 40 f9              ldr      x8, [sp, #0x18]
0x0001a648: e8 01 00 b5              cbnz     x8, #0x1a684
0x0001a64c: 15 00 40 b9              ldr      w21, [x0]
0x0001a650: bf 06 00 71              cmp      w21, #1
0x0001a654: 6b 01 00 54              b.lt     #0x1a680
0x0001a658: 16 20 00 91              add      x22, x0, #8
0x0001a65c: c0 02 40 f9              ldr      x0, [x22]
0x0001a660: 80 00 00 b4              cbz      x0, #0x1a670
0x0001a664: 08 00 40 f9              ldr      x8, [x0]
0x0001a668: 08 05 40 f9              ldr      x8, [x8, #8]
0x0001a66c: 00 01 3f d6              blr      x8
0x0001a670: b5 06 00 f1              subs     x21, x21, #1
0x0001a674: d6 22 00 91              add      x22, x22, #8
0x0001a678: 21 ff ff 54              b.ne     #0x1a65c
0x0001a67c: e0 17 40 f9              ldr      x0, [sp, #0x28]
0x0001a680: c8 be ff 97              bl       #0xa1a0                    ; -> _ZdlPv@plt
0x0001a684: ff 17 00 f9              str      xzr, [sp, #0x28]
0x0001a688: 28 ff 5f c8              ldaxr    x8, [x25]
0x0001a68c: 09 05 00 d1              sub      x9, x8, #1
0x0001a690: 29 ff 0a c8              stlxr    w10, x9, [x25]
0x0001a694: aa ff ff 35              cbnz     w10, #0x1a688
0x0001a698: e8 00 00 b5              cbnz     x8, #0x1a6b4
0x0001a69c: 68 02 40 f9              ldr      x8, [x19]
0x0001a6a0: e0 03 13 aa              mov      x0, x19
0x0001a6a4: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x0001a6a8: 00 01 3f d6              blr      x8
0x0001a6ac: e0 03 13 aa              mov      x0, x19
0x0001a6b0: 8c be ff 97              bl       #0xa0e0                    ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x0001a6b4: e0 03 14 aa              mov      x0, x20
0x0001a6b8: 0e bf ff 97              bl       #0xa2f0                    ; -> _Unwind_Resume@plt
