; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/xp/libdoc_photo_process.so
; target  : Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeCropAndEnhanceBitmap (0x7f2c0)
; start   : 0x7f2c0
; size    : 0x4e4
; bytes   : 0x4e4   instructions: 313
; generated: 2026-10-24T00:27:02

0x0007f2c0: fc 6f ba a9              stp      x28, x27, [sp, #-0x60]!
0x0007f2c4: fa 67 01 a9              stp      x26, x25, [sp, #0x10]
0x0007f2c8: f8 5f 02 a9              stp      x24, x23, [sp, #0x20]
0x0007f2cc: f6 57 03 a9              stp      x22, x21, [sp, #0x30]
0x0007f2d0: f4 4f 04 a9              stp      x20, x19, [sp, #0x40]
0x0007f2d4: fd 7b 05 a9              stp      x29, x30, [sp, #0x50]
0x0007f2d8: fd 43 01 91              add      x29, sp, #0x50
0x0007f2dc: ff c3 1d d1              sub      sp, sp, #0x770
0x0007f2e0: e3 17 00 f9              str      x3, [sp, #0x28]
0x0007f2e4: 56 d0 3b d5              mrs      x22, tpidr_el0
0x0007f2e8: c8 16 40 f9              ldr      x8, [x22, #0x28]
0x0007f2ec: f7 c3 00 91              add      x23, sp, #0x30
0x0007f2f0: f9 03 07 2a              mov      w25, w7
0x0007f2f4: fb 03 06 2a              mov      w27, w6
0x0007f2f8: f4 03 04 aa              mov      x20, x4
0x0007f2fc: f8 03 02 aa              mov      x24, x2
0x0007f300: f5 03 00 aa              mov      x21, x0
0x0007f304: e8 02 00 f9              str      x8, [x23]
0x0007f308: e5 9f 00 b9              str      w5, [sp, #0x9c]
0x0007f30c: e5 fa ff 97              bl       #0x7dea0
0x0007f310: fa 03 00 aa              mov      x26, x0
0x0007f314: 38 1d 00 b4              cbz      x24, #0x7f6b8
0x0007f318: e0 03 19 91              add      x0, sp, #0x640
0x0007f31c: e1 03 1e 32              mov      w1, #4
0x0007f320: f3 03 19 91              add      x19, sp, #0x640
0x0007f324: 8d de ff 97              bl       #0x76d58
0x0007f328: 73 62 00 91              add      x19, x19, #0x18
0x0007f32c: a1 1d 00 f0              adrp     x1, #0x436000
0x0007f330: 21 34 11 91              add      x1, x1, #0x44d
0x0007f334: e0 03 13 aa              mov      x0, x19
0x0007f338: eb e2 ff 97              bl       #0x77ee4
0x0007f33c: e0 03 13 aa              mov      x0, x19
0x0007f340: e1 03 1b 2a              mov      w1, w27
0x0007f344: 21 ea ff 97              bl       #0x79bc8
0x0007f348: e0 03 19 91              add      x0, sp, #0x640
0x0007f34c: be de ff 97              bl       #0x76e44
0x0007f350: a8 02 40 f9              ldr      x8, [x21]
0x0007f354: e0 03 15 aa              mov      x0, x21
0x0007f358: e1 03 14 aa              mov      x1, x20
0x0007f35c: 08 ad 42 f9              ldr      x8, [x8, #0x558]
0x0007f360: 00 01 3f d6              blr      x8
0x0007f364: a8 02 40 f9              ldr      x8, [x21]
0x0007f368: f3 03 00 2a              mov      w19, w0
0x0007f36c: e0 03 15 aa              mov      x0, x21
0x0007f370: e1 03 14 aa              mov      x1, x20
0x0007f374: 08 f5 42 f9              ldr      x8, [x8, #0x5e8]
0x0007f378: e2 03 1f aa              mov      x2, xzr
0x0007f37c: 00 01 3f d6              blr      x8
0x0007f380: fc 03 00 aa              mov      x28, x0
0x0007f384: 02 c8 33 8b              add      x2, x0, w19, sxtw #2
0x0007f388: e0 03 02 91              add      x0, sp, #0x80
0x0007f38c: e1 03 1c aa              mov      x1, x28
0x0007f390: 5e fc ff 97              bl       #0x7e508
0x0007f394: ff 37 00 f9              str      xzr, [sp, #0x68]
0x0007f398: e1 17 40 f9              ldr      x1, [sp, #0x28]
0x0007f39c: e2 e3 01 91              add      x2, sp, #0x78
0x0007f3a0: e3 d3 01 91              add      x3, sp, #0x74
0x0007f3a4: e4 c3 01 91              add      x4, sp, #0x70
0x0007f3a8: e0 03 15 aa              mov      x0, x21
0x0007f3ac: 95 e5 ff 97              bl       #0x78a00
0x0007f3b0: f3 03 1f aa              mov      x19, xzr
0x0007f3b4: e0 18 00 36              tbz      w0, #0, #0x7f6d0
0x0007f3b8: fa f3 01 a9              stp      x26, x28, [sp, #0x18]
0x0007f3bc: b9 fa ff 97              bl       #0x7dea0
0x0007f3c0: fc 3f 40 f9              ldr      x28, [sp, #0x78]
0x0007f3c4: f7 4f 4e 29              ldp      w23, w19, [sp, #0x70]
0x0007f3c8: fa 03 00 aa              mov      x26, x0
0x0007f3cc: e0 03 01 91              add      x0, sp, #0x40
0x0007f3d0: e1 03 02 91              add      x1, sp, #0x80
0x0007f3d4: e0 fe ff 97              bl       #0x7ef54
0x0007f3d8: 08 03 40 f9              ldr      x8, [x24]
0x0007f3dc: 3f 1f 00 72              tst      w25, #0xff
0x0007f3e0: e9 07 9f 1a              cset     w9, ne
0x0007f3e4: 08 0d 40 f9              ldr      x8, [x8, #0x18]
0x0007f3e8: e4 03 01 91              add      x4, sp, #0x40
0x0007f3ec: e5 63 01 91              add      x5, sp, #0x58
0x0007f3f0: e6 93 01 91              add      x6, sp, #0x64
0x0007f3f4: e7 83 01 91              add      x7, sp, #0x60
0x0007f3f8: e0 03 18 aa              mov      x0, x24
0x0007f3fc: e1 03 1c aa              mov      x1, x28
0x0007f400: e2 03 13 2a              mov      w2, w19
0x0007f404: e3 03 17 2a              mov      w3, w23
0x0007f408: e9 23 00 39              strb     w9, [sp, #8]
0x0007f40c: fb 03 00 b9              str      w27, [sp]
0x0007f410: 00 01 3f d6              blr      x8
0x0007f414: fb 03 00 2a              mov      w27, w0
0x0007f418: e0 03 01 91              add      x0, sp, #0x40
0x0007f41c: af fa ff 97              bl       #0x7ded8
0x0007f420: e0 83 14 91              add      x0, sp, #0x520
0x0007f424: e1 03 1e 32              mov      w1, #4
0x0007f428: f3 83 14 91              add      x19, sp, #0x520
0x0007f42c: 4b de ff 97              bl       #0x76d58
0x0007f430: 7c 62 00 91              add      x28, x19, #0x18
0x0007f434: a1 1d 00 f0              adrp     x1, #0x436000
0x0007f438: 21 2c 12 91              add      x1, x1, #0x48b
0x0007f43c: e0 03 1c aa              mov      x0, x28
0x0007f440: a9 e2 ff 97              bl       #0x77ee4
0x0007f444: e1 67 40 b9              ldr      w1, [sp, #0x64]
0x0007f448: e0 03 1c aa              mov      x0, x28
0x0007f44c: df e9 ff 97              bl       #0x79bc8
0x0007f450: a1 1d 00 f0              adrp     x1, #0x436000
0x0007f454: 21 c8 12 91              add      x1, x1, #0x4b2
0x0007f458: e0 03 1c aa              mov      x0, x28
0x0007f45c: a2 e2 ff 97              bl       #0x77ee4
0x0007f460: e1 63 40 b9              ldr      w1, [sp, #0x60]
0x0007f464: e0 03 1c aa              mov      x0, x28
0x0007f468: d8 e9 ff 97              bl       #0x79bc8
0x0007f46c: c1 1d 00 90              adrp     x1, #0x437000
0x0007f470: 21 ac 0a 91              add      x1, x1, #0x2ab
0x0007f474: e0 03 1c aa              mov      x0, x28
0x0007f478: 9b e2 ff 97              bl       #0x77ee4
0x0007f47c: 89 fa ff 97              bl       #0x7dea0
0x0007f480: 01 00 1a cb              sub      x1, x0, x26
0x0007f484: e0 03 1c aa              mov      x0, x28
0x0007f488: 02 e4 ff 97              bl       #0x78490
0x0007f48c: 61 b2 00 d0              adrp     x1, #0x16cd000
0x0007f490: 21 1c 2f 91              add      x1, x1, #0xbc7
0x0007f494: e0 03 1c aa              mov      x0, x28
0x0007f498: 93 e2 ff 97              bl       #0x77ee4
0x0007f49c: e0 83 14 91              add      x0, sp, #0x520
0x0007f4a0: 69 de ff 97              bl       #0x76e44
0x0007f4a4: e0 03 10 91              add      x0, sp, #0x400
0x0007f4a8: e1 03 1e 32              mov      w1, #4
0x0007f4ac: f3 03 10 91              add      x19, sp, #0x400
0x0007f4b0: 2a de ff 97              bl       #0x76d58
0x0007f4b4: 73 62 00 91              add      x19, x19, #0x18
0x0007f4b8: a1 1d 00 f0              adrp     x1, #0x436000
0x0007f4bc: 21 ec 12 91              add      x1, x1, #0x4bb
0x0007f4c0: e0 03 13 aa              mov      x0, x19
0x0007f4c4: 88 e2 ff 97              bl       #0x77ee4
0x0007f4c8: 76 fa ff 97              bl       #0x7dea0
0x0007f4cc: 01 00 1a cb              sub      x1, x0, x26
0x0007f4d0: e0 03 13 aa              mov      x0, x19
0x0007f4d4: ef e3 ff 97              bl       #0x78490
0x0007f4d8: 61 b2 00 d0              adrp     x1, #0x16cd000
0x0007f4dc: 21 1c 2f 91              add      x1, x1, #0xbc7
0x0007f4e0: e0 03 13 aa              mov      x0, x19
0x0007f4e4: 80 e2 ff 97              bl       #0x77ee4
0x0007f4e8: e0 03 10 91              add      x0, sp, #0x400
0x0007f4ec: 56 de ff 97              bl       #0x76e44
0x0007f4f0: 9b 0e 00 36              tbz      w27, #0, #0x7f6c0
0x0007f4f4: 6b fa ff 97              bl       #0x7dea0
0x0007f4f8: fb 13 40 f9              ldr      x27, [sp, #0x20]
0x0007f4fc: 08 03 40 f9              ldr      x8, [x24]
0x0007f500: e1 2f 40 f9              ldr      x1, [sp, #0x58]
0x0007f504: e3 0b 4c 29              ldp      w3, w2, [sp, #0x60]
0x0007f508: e4 9f 40 b9              ldr      w4, [sp, #0x9c]
0x0007f50c: 08 11 40 f9              ldr      x8, [x8, #0x20]
0x0007f510: fa 03 00 aa              mov      x26, x0
0x0007f514: 3f 1f 00 72              tst      w25, #0xff
0x0007f518: e7 07 9f 1a              cset     w7, ne
0x0007f51c: e6 f3 00 91              add      x6, sp, #0x3c
0x0007f520: e0 03 18 aa              mov      x0, x24
0x0007f524: e5 03 01 aa              mov      x5, x1
0x0007f528: 00 01 3f d6              blr      x8
0x0007f52c: e0 83 0b 91              add      x0, sp, #0x2e0
0x0007f530: e1 03 1e 32              mov      w1, #4
0x0007f534: f3 83 0b 91              add      x19, sp, #0x2e0
0x0007f538: 08 de ff 97              bl       #0x76d58
0x0007f53c: 78 62 00 91              add      x24, x19, #0x18
0x0007f540: a1 1d 00 f0              adrp     x1, #0x436000
0x0007f544: 21 7c 13 91              add      x1, x1, #0x4df
0x0007f548: e0 03 18 aa              mov      x0, x24
0x0007f54c: 66 e2 ff 97              bl       #0x77ee4
0x0007f550: 68 bb 00 f0              adrp     x8, #0x17ee000
0x0007f554: 08 81 02 91              add      x8, x8, #0xa0
0x0007f558: 00 61 00 91              add      x0, x8, #0x18
0x0007f55c: e1 73 02 91              add      x1, sp, #0x9c
0x0007f560: 41 ff ff 97              bl       #0x7f264
0x0007f564: e1 03 00 aa              mov      x1, x0
0x0007f568: e0 03 18 aa              mov      x0, x24
0x0007f56c: c2 e3 ff 97              bl       #0x78474
0x0007f570: c1 1d 00 90              adrp     x1, #0x437000
0x0007f574: 21 ac 0a 91              add      x1, x1, #0x2ab
0x0007f578: e0 03 18 aa              mov      x0, x24
0x0007f57c: 5a e2 ff 97              bl       #0x77ee4
0x0007f580: 48 fa ff 97              bl       #0x7dea0
0x0007f584: 01 00 1a cb              sub      x1, x0, x26
0x0007f588: e0 03 18 aa              mov      x0, x24
0x0007f58c: c1 e3 ff 97              bl       #0x78490
0x0007f590: 61 b2 00 d0              adrp     x1, #0x16cd000
0x0007f594: 21 1c 2f 91              add      x1, x1, #0xbc7
0x0007f598: e0 03 18 aa              mov      x0, x24
0x0007f59c: 52 e2 ff 97              bl       #0x77ee4
0x0007f5a0: e0 83 0b 91              add      x0, sp, #0x2e0
0x0007f5a4: 28 de ff 97              bl       #0x76e44
0x0007f5a8: 3e fa ff 97              bl       #0x7dea0
0x0007f5ac: f8 03 00 aa              mov      x24, x0
0x0007f5b0: e1 17 40 f9              ldr      x1, [sp, #0x28]
0x0007f5b4: e0 03 15 aa              mov      x0, x21
0x0007f5b8: 8b e5 ff 97              bl       #0x78be4
0x0007f5bc: f9 0f 40 f9              ldr      x25, [sp, #0x18]
0x0007f5c0: f7 c3 00 91              add      x23, sp, #0x30
0x0007f5c4: e2 07 4c 29              ldp      w2, w1, [sp, #0x60]
0x0007f5c8: e3 03 00 aa              mov      x3, x0
0x0007f5cc: e0 03 15 aa              mov      x0, x21
0x0007f5d0: c5 e5 ff 97              bl       #0x78ce4
0x0007f5d4: e1 2f 40 f9              ldr      x1, [sp, #0x58]
0x0007f5d8: e3 0b 4c 29              ldp      w3, w2, [sp, #0x60]
0x0007f5dc: e0 37 00 f9              str      x0, [sp, #0x68]
0x0007f5e0: e4 a3 01 91              add      x4, sp, #0x68
0x0007f5e4: e0 03 15 aa              mov      x0, x21
0x0007f5e8: 45 e5 ff 97              bl       #0x78afc
0x0007f5ec: e0 03 07 91              add      x0, sp, #0x1c0
0x0007f5f0: e1 03 1e 32              mov      w1, #4
0x0007f5f4: f3 03 07 91              add      x19, sp, #0x1c0
0x0007f5f8: d8 dd ff 97              bl       #0x76d58
0x0007f5fc: 73 62 00 91              add      x19, x19, #0x18
0x0007f600: a1 1d 00 f0              adrp     x1, #0x436000
0x0007f604: 21 e8 13 91              add      x1, x1, #0x4fa
0x0007f608: e0 03 13 aa              mov      x0, x19
0x0007f60c: 36 e2 ff 97              bl       #0x77ee4
0x0007f610: 24 fa ff 97              bl       #0x7dea0
0x0007f614: 01 00 18 cb              sub      x1, x0, x24
0x0007f618: e0 03 13 aa              mov      x0, x19
0x0007f61c: 9d e3 ff 97              bl       #0x78490
0x0007f620: 61 b2 00 d0              adrp     x1, #0x16cd000
0x0007f624: 21 1c 2f 91              add      x1, x1, #0xbc7
0x0007f628: e0 03 13 aa              mov      x0, x19
0x0007f62c: 2e e2 ff 97              bl       #0x77ee4
0x0007f630: e0 03 07 91              add      x0, sp, #0x1c0
0x0007f634: 04 de ff 97              bl       #0x76e44
0x0007f638: a8 02 40 f9              ldr      x8, [x21]
0x0007f63c: 08 15 43 f9              ldr      x8, [x8, #0x628]
0x0007f640: e0 03 15 aa              mov      x0, x21
0x0007f644: e1 03 14 aa              mov      x1, x20
0x0007f648: e2 03 1b aa              mov      x2, x27
0x0007f64c: e3 03 1f 2a              mov      w3, wzr
0x0007f650: 00 01 3f d6              blr      x8
0x0007f654: e0 2f 40 f9              ldr      x0, [sp, #0x58]
0x0007f658: d6 77 0e 94              bl       #0x41d5b0
0x0007f65c: e0 3f 40 f9              ldr      x0, [sp, #0x78]
0x0007f660: d4 77 0e 94              bl       #0x41d5b0
0x0007f664: e0 83 02 91              add      x0, sp, #0xa0
0x0007f668: e1 03 1e 32              mov      w1, #4
0x0007f66c: f3 83 02 91              add      x19, sp, #0xa0
0x0007f670: ba dd ff 97              bl       #0x76d58
0x0007f674: 73 62 00 91              add      x19, x19, #0x18
0x0007f678: a1 1d 00 f0              adrp     x1, #0x436000
0x0007f67c: 21 94 14 91              add      x1, x1, #0x525
0x0007f680: e0 03 13 aa              mov      x0, x19
0x0007f684: 18 e2 ff 97              bl       #0x77ee4
0x0007f688: 06 fa ff 97              bl       #0x7dea0
0x0007f68c: 01 00 19 cb              sub      x1, x0, x25
0x0007f690: e0 03 13 aa              mov      x0, x19
0x0007f694: 7f e3 ff 97              bl       #0x78490
0x0007f698: 61 b2 00 d0              adrp     x1, #0x16cd000
0x0007f69c: 21 1c 2f 91              add      x1, x1, #0xbc7
0x0007f6a0: e0 03 13 aa              mov      x0, x19
0x0007f6a4: 10 e2 ff 97              bl       #0x77ee4
0x0007f6a8: e0 83 02 91              add      x0, sp, #0xa0
0x0007f6ac: e6 dd ff 97              bl       #0x76e44
0x0007f6b0: f3 37 40 f9              ldr      x19, [sp, #0x68]
0x0007f6b4: 07 00 00 14              b        #0x7f6d0                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeCropAndEnhanceBitmap+0x410
0x0007f6b8: f3 03 1f aa              mov      x19, xzr
0x0007f6bc: 07 00 00 14              b        #0x7f6d8                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeCropAndEnhanceBitmap+0x418
0x0007f6c0: e0 3f 40 f9              ldr      x0, [sp, #0x78]
0x0007f6c4: bb 77 0e 94              bl       #0x41d5b0
0x0007f6c8: f3 03 1f aa              mov      x19, xzr
0x0007f6cc: f7 c3 00 91              add      x23, sp, #0x30
0x0007f6d0: e0 03 02 91              add      x0, sp, #0x80
0x0007f6d4: 01 fa ff 97              bl       #0x7ded8
0x0007f6d8: c8 16 40 f9              ldr      x8, [x22, #0x28]
0x0007f6dc: e9 02 40 f9              ldr      x9, [x23]
0x0007f6e0: 1f 01 09 eb              cmp      x8, x9
0x0007f6e4: 41 01 00 54              b.ne     #0x7f70c
0x0007f6e8: e0 03 13 aa              mov      x0, x19
0x0007f6ec: ff c3 1d 91              add      sp, sp, #0x770
0x0007f6f0: fd 7b 45 a9              ldp      x29, x30, [sp, #0x50]
0x0007f6f4: f4 4f 44 a9              ldp      x20, x19, [sp, #0x40]
0x0007f6f8: f6 57 43 a9              ldp      x22, x21, [sp, #0x30]
0x0007f6fc: f8 5f 42 a9              ldp      x24, x23, [sp, #0x20]
0x0007f700: fa 67 41 a9              ldp      x26, x25, [sp, #0x10]
0x0007f704: fc 6f c6 a8              ldp      x28, x27, [sp], #0x60
0x0007f708: c0 03 5f d6              ret      
0x0007f70c: 49 a3 ff 97              bl       #0x68430                   ; -> __stack_chk_fail@plt
0x0007f710: 07 00 00 14              b        #0x7f72c                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeCropAndEnhanceBitmap+0x46c
0x0007f714: 09 00 00 14              b        #0x7f738                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeCropAndEnhanceBitmap+0x478
0x0007f718: 12 00 00 14              b        #0x7f760                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeCropAndEnhanceBitmap+0x4a0
0x0007f71c: f3 03 00 aa              mov      x19, x0
0x0007f720: e0 03 01 91              add      x0, sp, #0x40
0x0007f724: ed f9 ff 97              bl       #0x7ded8
0x0007f728: 1b 00 00 14              b        #0x7f794                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeCropAndEnhanceBitmap+0x4d4
0x0007f72c: f3 03 00 aa              mov      x19, x0
0x0007f730: e0 83 02 91              add      x0, sp, #0xa0
0x0007f734: 17 00 00 14              b        #0x7f790                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeCropAndEnhanceBitmap+0x4d0
0x0007f738: f3 03 00 aa              mov      x19, x0
0x0007f73c: e0 03 07 91              add      x0, sp, #0x1c0
0x0007f740: 14 00 00 14              b        #0x7f790                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeCropAndEnhanceBitmap+0x4d0
0x0007f744: 02 00 00 14              b        #0x7f74c                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeCropAndEnhanceBitmap+0x48c
0x0007f748: 0a 00 00 14              b        #0x7f770                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeCropAndEnhanceBitmap+0x4b0
0x0007f74c: f3 03 00 aa              mov      x19, x0
0x0007f750: e0 83 0b 91              add      x0, sp, #0x2e0
0x0007f754: 0f 00 00 14              b        #0x7f790                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeCropAndEnhanceBitmap+0x4d0
0x0007f758: 06 00 00 14              b        #0x7f770                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeCropAndEnhanceBitmap+0x4b0
0x0007f75c: 05 00 00 14              b        #0x7f770                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeCropAndEnhanceBitmap+0x4b0
0x0007f760: f3 03 00 aa              mov      x19, x0
0x0007f764: e0 03 10 91              add      x0, sp, #0x400
0x0007f768: 0a 00 00 14              b        #0x7f790                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeCropAndEnhanceBitmap+0x4d0
0x0007f76c: 07 00 00 14              b        #0x7f788                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeCropAndEnhanceBitmap+0x4c8
0x0007f770: f3 03 00 aa              mov      x19, x0
0x0007f774: 08 00 00 14              b        #0x7f794                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeCropAndEnhanceBitmap+0x4d4
0x0007f778: f3 03 00 aa              mov      x19, x0
0x0007f77c: e0 03 19 91              add      x0, sp, #0x640
0x0007f780: b1 dd ff 97              bl       #0x76e44
0x0007f784: 06 00 00 14              b        #0x7f79c                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeCropAndEnhanceBitmap+0x4dc
0x0007f788: f3 03 00 aa              mov      x19, x0
0x0007f78c: e0 83 14 91              add      x0, sp, #0x520
0x0007f790: ad dd ff 97              bl       #0x76e44
0x0007f794: e0 03 02 91              add      x0, sp, #0x80
0x0007f798: d0 f9 ff 97              bl       #0x7ded8
0x0007f79c: e0 03 13 aa              mov      x0, x19
0x0007f7a0: 41 d0 0e 94              bl       #0x4338a4
