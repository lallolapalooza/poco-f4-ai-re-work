; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/xp/libdoc_photo_process.so
; target  : Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeEnhanceBitmap (0x7efd0)
; start   : 0x7efd0
; size    : 0x294
; bytes   : 0x294   instructions: 165
; generated: 2026-10-24T00:27:03

0x0007efd0: fc 6f ba a9              stp      x28, x27, [sp, #-0x60]!
0x0007efd4: fa 67 01 a9              stp      x26, x25, [sp, #0x10]
0x0007efd8: f8 5f 02 a9              stp      x24, x23, [sp, #0x20]
0x0007efdc: f6 57 03 a9              stp      x22, x21, [sp, #0x30]
0x0007efe0: f4 4f 04 a9              stp      x20, x19, [sp, #0x40]
0x0007efe4: fd 7b 05 a9              stp      x29, x30, [sp, #0x50]
0x0007efe8: fd 43 01 91              add      x29, sp, #0x50
0x0007efec: ff 03 0f d1              sub      sp, sp, #0x3c0
0x0007eff0: 5a d0 3b d5              mrs      x26, tpidr_el0
0x0007eff4: 48 17 40 f9              ldr      x8, [x26, #0x28]
0x0007eff8: f9 63 00 91              add      x25, sp, #0x18
0x0007effc: f8 03 05 2a              mov      w24, w5
0x0007f000: f6 03 04 2a              mov      w22, w4
0x0007f004: f4 03 03 aa              mov      x20, x3
0x0007f008: f7 03 02 aa              mov      x23, x2
0x0007f00c: f5 03 00 aa              mov      x21, x0
0x0007f010: 28 03 00 f9              str      x8, [x25]
0x0007f014: e4 4f 00 b9              str      w4, [sp, #0x4c]
0x0007f018: a2 fb ff 97              bl       #0x7dea0
0x0007f01c: f3 03 00 aa              mov      x19, x0
0x0007f020: f7 0e 00 b4              cbz      x23, #0x7f1fc
0x0007f024: e0 43 0a 91              add      x0, sp, #0x290
0x0007f028: e1 03 1e 32              mov      w1, #4
0x0007f02c: fb 43 0a 91              add      x27, sp, #0x290
0x0007f030: 4a df ff 97              bl       #0x76d58
0x0007f034: 60 63 00 91              add      x0, x27, #0x18
0x0007f038: a1 1d 00 f0              adrp     x1, #0x436000
0x0007f03c: 21 90 0f 91              add      x1, x1, #0x3e4
0x0007f040: a9 e3 ff 97              bl       #0x77ee4
0x0007f044: e0 43 0a 91              add      x0, sp, #0x290
0x0007f048: 7f df ff 97              bl       #0x76e44
0x0007f04c: 94 0d 00 b4              cbz      x20, #0x7f1fc
0x0007f050: e2 a3 00 91              add      x2, sp, #0x28
0x0007f054: e0 03 15 aa              mov      x0, x21
0x0007f058: e1 03 14 aa              mov      x1, x20
0x0007f05c: 61 a6 ff 97              bl       #0x689e0                   ; -> AndroidBitmap_getInfo@plt
0x0007f060: e0 0c f8 37              tbnz     w0, #0x1f, #0x7f1fc
0x0007f064: e8 37 40 b9              ldr      w8, [sp, #0x34]
0x0007f068: 1f 05 00 71              cmp      w8, #1
0x0007f06c: 81 0c 00 54              b.ne     #0x7f1fc
0x0007f070: e2 03 01 91              add      x2, sp, #0x40
0x0007f074: e0 03 15 aa              mov      x0, x21
0x0007f078: e1 03 14 aa              mov      x1, x20
0x0007f07c: ed a3 ff 97              bl       #0x68030                   ; -> AndroidBitmap_lockPixels@plt
0x0007f080: e0 0b f8 37              tbnz     w0, #0x1f, #0x7f1fc
0x0007f084: e8 2b 40 b9              ldr      w8, [sp, #0x28]
0x0007f088: e0 c3 05 91              add      x0, sp, #0x170
0x0007f08c: e1 03 1e 32              mov      w1, #4
0x0007f090: f3 07 00 f9              str      x19, [sp, #8]
0x0007f094: e8 17 00 b9              str      w8, [sp, #0x14]
0x0007f098: e8 ef 45 29              ldp      w8, w27, [sp, #0x2c]
0x0007f09c: fc c3 05 91              add      x28, sp, #0x170
0x0007f0a0: e8 13 00 b9              str      w8, [sp, #0x10]
0x0007f0a4: 2d df ff 97              bl       #0x76d58
0x0007f0a8: 9c 63 00 91              add      x28, x28, #0x18
0x0007f0ac: a1 1d 00 f0              adrp     x1, #0x436000
0x0007f0b0: 21 30 10 91              add      x1, x1, #0x40c
0x0007f0b4: e0 03 1c aa              mov      x0, x28
0x0007f0b8: 8b e3 ff 97              bl       #0x77ee4
0x0007f0bc: e1 17 40 b9              ldr      w1, [sp, #0x14]
0x0007f0c0: e0 03 1c aa              mov      x0, x28
0x0007f0c4: c1 ea ff 97              bl       #0x79bc8
0x0007f0c8: a1 1d 00 f0              adrp     x1, #0x436000
0x0007f0cc: 21 94 10 91              add      x1, x1, #0x425
0x0007f0d0: e0 03 1c aa              mov      x0, x28
0x0007f0d4: 84 e3 ff 97              bl       #0x77ee4
0x0007f0d8: e1 13 40 b9              ldr      w1, [sp, #0x10]
0x0007f0dc: e0 03 1c aa              mov      x0, x28
0x0007f0e0: ba ea ff 97              bl       #0x79bc8
0x0007f0e4: a1 1d 00 f0              adrp     x1, #0x436000
0x0007f0e8: 21 bc 10 91              add      x1, x1, #0x42f
0x0007f0ec: e0 03 1c aa              mov      x0, x28
0x0007f0f0: 7d e3 ff 97              bl       #0x77ee4
0x0007f0f4: e0 03 1c aa              mov      x0, x28
0x0007f0f8: e1 03 1b 2a              mov      w1, w27
0x0007f0fc: b3 ea ff 97              bl       #0x79bc8
0x0007f100: e0 c3 05 91              add      x0, sp, #0x170
0x0007f104: 50 df ff 97              bl       #0x76e44
0x0007f108: fb 23 40 f9              ldr      x27, [sp, #0x40]
0x0007f10c: f3 73 42 29              ldp      w19, w28, [sp, #0x10]
0x0007f110: e0 03 1b aa              mov      x0, x27
0x0007f114: e1 03 1b aa              mov      x1, x27
0x0007f118: e2 03 1c 2a              mov      w2, w28
0x0007f11c: e3 03 13 2a              mov      w3, w19
0x0007f120: cb e5 ff 97              bl       #0x7884c
0x0007f124: e8 02 40 f9              ldr      x8, [x23]
0x0007f128: 1f 1f 00 72              tst      w24, #0xff
0x0007f12c: e7 07 9f 1a              cset     w7, ne
0x0007f130: e6 93 00 91              add      x6, sp, #0x24
0x0007f134: 08 11 40 f9              ldr      x8, [x8, #0x20]
0x0007f138: e0 03 17 aa              mov      x0, x23
0x0007f13c: e1 03 1b aa              mov      x1, x27
0x0007f140: e2 03 1c 2a              mov      w2, w28
0x0007f144: e3 03 13 2a              mov      w3, w19
0x0007f148: e4 03 16 2a              mov      w4, w22
0x0007f14c: e5 03 1b aa              mov      x5, x27
0x0007f150: 00 01 3f d6              blr      x8
0x0007f154: e0 03 1b aa              mov      x0, x27
0x0007f158: e1 03 1b aa              mov      x1, x27
0x0007f15c: e2 03 1c 2a              mov      w2, w28
0x0007f160: e3 03 13 2a              mov      w3, w19
0x0007f164: e8 e5 ff 97              bl       #0x78904
0x0007f168: e0 03 15 aa              mov      x0, x21
0x0007f16c: e1 03 14 aa              mov      x1, x20
0x0007f170: e4 a4 ff 97              bl       #0x68500                   ; -> AndroidBitmap_unlockPixels@plt
0x0007f174: e0 43 01 91              add      x0, sp, #0x50
0x0007f178: e1 03 1e 32              mov      w1, #4
0x0007f17c: f4 43 01 91              add      x20, sp, #0x50
0x0007f180: f6 de ff 97              bl       #0x76d58
0x0007f184: 94 62 00 91              add      x20, x20, #0x18
0x0007f188: a1 1d 00 f0              adrp     x1, #0x436000
0x0007f18c: 21 e4 10 91              add      x1, x1, #0x439
0x0007f190: e0 03 14 aa              mov      x0, x20
0x0007f194: 54 e3 ff 97              bl       #0x77ee4
0x0007f198: 68 bb 00 f0              adrp     x8, #0x17ee000
0x0007f19c: 08 81 02 91              add      x8, x8, #0xa0
0x0007f1a0: 00 61 00 91              add      x0, x8, #0x18
0x0007f1a4: e1 33 01 91              add      x1, sp, #0x4c
0x0007f1a8: 2f 00 00 94              bl       #0x7f264
0x0007f1ac: e1 03 00 aa              mov      x1, x0
0x0007f1b0: e0 03 14 aa              mov      x0, x20
0x0007f1b4: b0 e4 ff 97              bl       #0x78474
0x0007f1b8: c1 1d 00 90              adrp     x1, #0x437000
0x0007f1bc: 21 ac 0a 91              add      x1, x1, #0x2ab
0x0007f1c0: e0 03 14 aa              mov      x0, x20
0x0007f1c4: 48 e3 ff 97              bl       #0x77ee4
0x0007f1c8: 36 fb ff 97              bl       #0x7dea0
0x0007f1cc: e8 07 40 f9              ldr      x8, [sp, #8]
0x0007f1d0: 01 00 08 cb              sub      x1, x0, x8
0x0007f1d4: e0 03 14 aa              mov      x0, x20
0x0007f1d8: ae e4 ff 97              bl       #0x78490
0x0007f1dc: 61 b2 00 d0              adrp     x1, #0x16cd000
0x0007f1e0: 21 1c 2f 91              add      x1, x1, #0xbc7
0x0007f1e4: e0 03 14 aa              mov      x0, x20
0x0007f1e8: 3f e3 ff 97              bl       #0x77ee4
0x0007f1ec: e0 43 01 91              add      x0, sp, #0x50
0x0007f1f0: 15 df ff 97              bl       #0x76e44
0x0007f1f4: e0 03 1f 2a              mov      w0, wzr
0x0007f1f8: 02 00 00 14              b        #0x7f200                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeEnhanceBitmap+0x230
0x0007f1fc: e0 03 00 32              mov      w0, #1
0x0007f200: 48 17 40 f9              ldr      x8, [x26, #0x28]
0x0007f204: 29 03 40 f9              ldr      x9, [x25]
0x0007f208: 1f 01 09 eb              cmp      x8, x9
0x0007f20c: 21 01 00 54              b.ne     #0x7f230
0x0007f210: ff 03 0f 91              add      sp, sp, #0x3c0
0x0007f214: fd 7b 45 a9              ldp      x29, x30, [sp, #0x50]
0x0007f218: f4 4f 44 a9              ldp      x20, x19, [sp, #0x40]
0x0007f21c: f6 57 43 a9              ldp      x22, x21, [sp, #0x30]
0x0007f220: f8 5f 42 a9              ldp      x24, x23, [sp, #0x20]
0x0007f224: fa 67 41 a9              ldp      x26, x25, [sp, #0x10]
0x0007f228: fc 6f c6 a8              ldp      x28, x27, [sp], #0x60
0x0007f22c: c0 03 5f d6              ret      
0x0007f230: 80 a4 ff 97              bl       #0x68430                   ; -> __stack_chk_fail@plt
0x0007f234: 01 00 00 14              b        #0x7f238                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeEnhanceBitmap+0x268
0x0007f238: f3 03 00 aa              mov      x19, x0
0x0007f23c: e0 43 01 91              add      x0, sp, #0x50
0x0007f240: 06 00 00 14              b        #0x7f258                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeEnhanceBitmap+0x288
0x0007f244: f3 03 00 aa              mov      x19, x0
0x0007f248: e0 c3 05 91              add      x0, sp, #0x170
0x0007f24c: 03 00 00 14              b        #0x7f258                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeEnhanceBitmap+0x288
0x0007f250: f3 03 00 aa              mov      x19, x0
0x0007f254: e0 43 0a 91              add      x0, sp, #0x290
0x0007f258: fb de ff 97              bl       #0x76e44
0x0007f25c: e0 03 13 aa              mov      x0, x19
0x0007f260: 91 d1 0e 94              bl       #0x4338a4
