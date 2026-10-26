; disasm_elf.py (capstone 5.0.7)
; file    : /tmp/kwai_libs/lib/arm64-v8a/libykit.so
; target  : Java_com_kwai_kscnnrenderlib_KSJNILib_renderModel (0xc8360)
; start   : 0xc8360
; size    : 0x6f4
; bytes   : 0x6f4   instructions: 445
; generated: 2026-10-23T23:49:10

0x000c8360: ff 03 05 d1              sub      sp, sp, #0x140
0x000c8364: fc 73 00 f9              str      x28, [sp, #0xe0]
0x000c8368: fa 67 0f a9              stp      x26, x25, [sp, #0xf0]
0x000c836c: f8 5f 10 a9              stp      x24, x23, [sp, #0x100]
0x000c8370: f6 57 11 a9              stp      x22, x21, [sp, #0x110]
0x000c8374: f4 4f 12 a9              stp      x20, x19, [sp, #0x120]
0x000c8378: fd 7b 13 a9              stp      x29, x30, [sp, #0x130]
0x000c837c: fd c3 04 91              add      x29, sp, #0x130
0x000c8380: 5a d0 3b d5              mrs      x26, tpidr_el0
0x000c8384: 48 17 40 f9              ldr      x8, [x26, #0x28]
0x000c8388: 41 3b 00 f0              adrp     x1, #0x833000
0x000c838c: 21 40 2d 91              add      x1, x1, #0xb50
0x000c8390: f7 03 03 aa              mov      x23, x3
0x000c8394: e8 03 00 f9              str      x8, [sp]
0x000c8398: 08 00 40 f9              ldr      x8, [x0]
0x000c839c: f3 03 00 aa              mov      x19, x0
0x000c83a0: f4 03 02 aa              mov      x20, x2
0x000c83a4: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000c83a8: 00 01 3f d6              blr      x8
0x000c83ac: 68 02 40 f9              ldr      x8, [x19]
0x000c83b0: 42 3b 00 f0              adrp     x2, #0x833000
0x000c83b4: 43 3b 00 f0              adrp     x3, #0x833000
0x000c83b8: e1 03 00 aa              mov      x1, x0
0x000c83bc: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c83c0: 42 74 30 91              add      x2, x2, #0xc1d
0x000c83c4: 63 b4 30 91              add      x3, x3, #0xc2d
0x000c83c8: e0 03 13 aa              mov      x0, x19
0x000c83cc: 00 01 3f d6              blr      x8
0x000c83d0: 68 02 40 f9              ldr      x8, [x19]
0x000c83d4: e2 03 00 aa              mov      x2, x0
0x000c83d8: e0 03 13 aa              mov      x0, x19
0x000c83dc: e1 03 14 aa              mov      x1, x20
0x000c83e0: 08 95 41 f9              ldr      x8, [x8, #0x328]
0x000c83e4: 00 01 3f d6              blr      x8
0x000c83e8: c0 2b 00 b4              cbz      x0, #0xc8960
0x000c83ec: 08 9c 40 f9              ldr      x8, [x0, #0x138]
0x000c83f0: f6 03 00 aa              mov      x22, x0
0x000c83f4: 68 2b 00 b4              cbz      x8, #0xc8960
0x000c83f8: 68 02 40 f9              ldr      x8, [x19]
0x000c83fc: 41 3b 00 f0              adrp     x1, #0x833000
0x000c8400: 21 40 2d 91              add      x1, x1, #0xb50
0x000c8404: e0 03 13 aa              mov      x0, x19
0x000c8408: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000c840c: f9 83 00 91              add      x25, sp, #0x20
0x000c8410: 00 01 3f d6              blr      x8
0x000c8414: 68 02 40 f9              ldr      x8, [x19]
0x000c8418: f8 03 00 aa              mov      x24, x0
0x000c841c: 62 3b 00 90              adrp     x2, #0x834000
0x000c8420: 43 3b 00 f0              adrp     x3, #0x833000
0x000c8424: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c8428: 42 58 16 91              add      x2, x2, #0x596
0x000c842c: 63 fc 33 91              add      x3, x3, #0xcff
0x000c8430: e0 03 13 aa              mov      x0, x19
0x000c8434: e1 03 18 aa              mov      x1, x24
0x000c8438: 00 01 3f d6              blr      x8
0x000c843c: 68 02 40 f9              ldr      x8, [x19]
0x000c8440: 62 3b 00 90              adrp     x2, #0x834000
0x000c8444: 63 3b 00 90              adrp     x3, #0x834000
0x000c8448: f5 03 00 aa              mov      x21, x0
0x000c844c: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c8450: 42 94 16 91              add      x2, x2, #0x5a5
0x000c8454: 63 b8 16 91              add      x3, x3, #0x5ae
0x000c8458: e0 03 13 aa              mov      x0, x19
0x000c845c: e1 03 18 aa              mov      x1, x24
0x000c8460: 00 01 3f d6              blr      x8
0x000c8464: 68 02 40 f9              ldr      x8, [x19]
0x000c8468: e2 03 00 aa              mov      x2, x0
0x000c846c: e0 03 13 aa              mov      x0, x19
0x000c8470: e1 03 14 aa              mov      x1, x20
0x000c8474: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000c8478: 00 01 3f d6              blr      x8
0x000c847c: f8 03 00 aa              mov      x24, x0
0x000c8480: e0 03 13 aa              mov      x0, x19
0x000c8484: e2 03 14 aa              mov      x2, x20
0x000c8488: e3 03 16 aa              mov      x3, x22
0x000c848c: e4 03 1f 2a              mov      w4, wzr
0x000c8490: 2d f7 ff 97              bl       #0xc6144
0x000c8494: c8 22 40 f9              ldr      x8, [x22, #0x40]
0x000c8498: 61 3b 00 90              adrp     x1, #0x834000
0x000c849c: 21 88 17 91              add      x1, x1, #0x5e2
0x000c84a0: e0 03 13 aa              mov      x0, x19
0x000c84a4: 08 05 00 91              add      x8, x8, #1
0x000c84a8: c8 22 00 f9              str      x8, [x22, #0x40]
0x000c84ac: 68 02 40 f9              ldr      x8, [x19]
0x000c84b0: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000c84b4: 00 01 3f d6              blr      x8
0x000c84b8: 08 d3 9d 52              mov      w8, #0xee98
0x000c84bc: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x000c84c0: 68 4e a8 72              movk     w8, #0x4273, lsl #16
0x000c84c4: ff 7f 07 a9              stp      xzr, xzr, [sp, #0x70]
0x000c84c8: ff 43 00 f9              str      xzr, [sp, #0x80]
0x000c84cc: ff ff 04 29              stp      wzr, wzr, [sp, #0x24]
0x000c84d0: ff e3 00 39              strb     wzr, [sp, #0x38]
0x000c84d4: 20 83 04 ad              stp      q0, q0, [x25, #0x90]
0x000c84d8: 20 23 80 3d              str      q0, [x25, #0x80]
0x000c84dc: e8 d3 00 b9              str      w8, [sp, #0xd0]
0x000c84e0: 68 02 40 f9              ldr      x8, [x19]
0x000c84e4: f9 03 00 aa              mov      x25, x0
0x000c84e8: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c84ec: 62 3b 00 90              adrp     x2, #0x834000
0x000c84f0: 43 3b 00 f0              adrp     x3, #0x833000
0x000c84f4: 42 50 18 91              add      x2, x2, #0x614
0x000c84f8: 63 9c 32 91              add      x3, x3, #0xca7
0x000c84fc: e0 03 13 aa              mov      x0, x19
0x000c8500: e1 03 19 aa              mov      x1, x25
0x000c8504: 00 01 3f d6              blr      x8
0x000c8508: 68 02 40 f9              ldr      x8, [x19]
0x000c850c: e2 03 00 aa              mov      x2, x0
0x000c8510: 08 91 41 f9              ldr      x8, [x8, #0x320]
0x000c8514: e0 03 13 aa              mov      x0, x19
0x000c8518: e1 03 18 aa              mov      x1, x24
0x000c851c: 00 01 3f d6              blr      x8
0x000c8520: e0 23 00 b9              str      w0, [sp, #0x20]
0x000c8524: 68 02 40 f9              ldr      x8, [x19]
0x000c8528: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c852c: 42 3c 00 d0              adrp     x2, #0x852000
0x000c8530: 43 3b 00 f0              adrp     x3, #0x833000
0x000c8534: 42 44 21 91              add      x2, x2, #0x851
0x000c8538: 63 9c 32 91              add      x3, x3, #0xca7
0x000c853c: e0 03 13 aa              mov      x0, x19
0x000c8540: e1 03 19 aa              mov      x1, x25
0x000c8544: 00 01 3f d6              blr      x8
0x000c8548: 68 02 40 f9              ldr      x8, [x19]
0x000c854c: e2 03 00 aa              mov      x2, x0
0x000c8550: 08 91 41 f9              ldr      x8, [x8, #0x320]
0x000c8554: e0 03 13 aa              mov      x0, x19
0x000c8558: e1 03 18 aa              mov      x1, x24
0x000c855c: 00 01 3f d6              blr      x8
0x000c8560: e0 27 00 b9              str      w0, [sp, #0x24]
0x000c8564: 68 02 40 f9              ldr      x8, [x19]
0x000c8568: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c856c: 62 3b 00 90              adrp     x2, #0x834000
0x000c8570: 43 3b 00 f0              adrp     x3, #0x833000
0x000c8574: 42 78 18 91              add      x2, x2, #0x61e
0x000c8578: 63 9c 32 91              add      x3, x3, #0xca7
0x000c857c: e0 03 13 aa              mov      x0, x19
0x000c8580: e1 03 19 aa              mov      x1, x25
0x000c8584: 00 01 3f d6              blr      x8
0x000c8588: 68 02 40 f9              ldr      x8, [x19]
0x000c858c: e2 03 00 aa              mov      x2, x0
0x000c8590: 08 91 41 f9              ldr      x8, [x8, #0x320]
0x000c8594: e0 03 13 aa              mov      x0, x19
0x000c8598: e1 03 18 aa              mov      x1, x24
0x000c859c: 00 01 3f d6              blr      x8
0x000c85a0: e0 2b 00 b9              str      w0, [sp, #0x28]
0x000c85a4: 68 02 40 f9              ldr      x8, [x19]
0x000c85a8: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c85ac: 02 3e 00 90              adrp     x2, #0x888000
0x000c85b0: 43 3b 00 f0              adrp     x3, #0x833000
0x000c85b4: 42 cc 0b 91              add      x2, x2, #0x2f3
0x000c85b8: 63 9c 32 91              add      x3, x3, #0xca7
0x000c85bc: e0 03 13 aa              mov      x0, x19
0x000c85c0: e1 03 19 aa              mov      x1, x25
0x000c85c4: 00 01 3f d6              blr      x8
0x000c85c8: 68 02 40 f9              ldr      x8, [x19]
0x000c85cc: e2 03 00 aa              mov      x2, x0
0x000c85d0: 08 91 41 f9              ldr      x8, [x8, #0x320]
0x000c85d4: e0 03 13 aa              mov      x0, x19
0x000c85d8: e1 03 18 aa              mov      x1, x24
0x000c85dc: 00 01 3f d6              blr      x8
0x000c85e0: e0 5b 00 b9              str      w0, [sp, #0x58]
0x000c85e4: 68 02 40 f9              ldr      x8, [x19]
0x000c85e8: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c85ec: 02 3e 00 90              adrp     x2, #0x888000
0x000c85f0: 43 3b 00 f0              adrp     x3, #0x833000
0x000c85f4: 42 d0 0f 91              add      x2, x2, #0x3f4
0x000c85f8: 63 9c 32 91              add      x3, x3, #0xca7
0x000c85fc: e0 03 13 aa              mov      x0, x19
0x000c8600: e1 03 19 aa              mov      x1, x25
0x000c8604: 00 01 3f d6              blr      x8
0x000c8608: 68 02 40 f9              ldr      x8, [x19]
0x000c860c: e2 03 00 aa              mov      x2, x0
0x000c8610: 08 91 41 f9              ldr      x8, [x8, #0x320]
0x000c8614: e0 03 13 aa              mov      x0, x19
0x000c8618: e1 03 18 aa              mov      x1, x24
0x000c861c: 00 01 3f d6              blr      x8
0x000c8620: e0 5f 00 b9              str      w0, [sp, #0x5c]
0x000c8624: 68 02 40 f9              ldr      x8, [x19]
0x000c8628: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c862c: 62 3b 00 90              adrp     x2, #0x834000
0x000c8630: 43 3b 00 f0              adrp     x3, #0x833000
0x000c8634: 42 98 18 91              add      x2, x2, #0x626
0x000c8638: 63 b4 30 91              add      x3, x3, #0xc2d
0x000c863c: e0 03 13 aa              mov      x0, x19
0x000c8640: e1 03 19 aa              mov      x1, x25
0x000c8644: 00 01 3f d6              blr      x8
0x000c8648: 68 02 40 f9              ldr      x8, [x19]
0x000c864c: e2 03 00 aa              mov      x2, x0
0x000c8650: 08 95 41 f9              ldr      x8, [x8, #0x328]
0x000c8654: e0 03 13 aa              mov      x0, x19
0x000c8658: e1 03 18 aa              mov      x1, x24
0x000c865c: 00 01 3f d6              blr      x8
0x000c8660: e0 1b 00 f9              str      x0, [sp, #0x30]
0x000c8664: 68 02 40 f9              ldr      x8, [x19]
0x000c8668: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c866c: 62 3b 00 90              adrp     x2, #0x834000
0x000c8670: 63 3b 00 90              adrp     x3, #0x834000
0x000c8674: 42 c4 18 91              add      x2, x2, #0x631
0x000c8678: 63 84 1d 91              add      x3, x3, #0x761
0x000c867c: e0 03 13 aa              mov      x0, x19
0x000c8680: e1 03 19 aa              mov      x1, x25
0x000c8684: 00 01 3f d6              blr      x8
0x000c8688: 68 02 40 f9              ldr      x8, [x19]
0x000c868c: e2 03 00 aa              mov      x2, x0
0x000c8690: 08 81 41 f9              ldr      x8, [x8, #0x300]
0x000c8694: e0 03 13 aa              mov      x0, x19
0x000c8698: e1 03 18 aa              mov      x1, x24
0x000c869c: 00 01 3f d6              blr      x8
0x000c86a0: 1f 1c 00 72              tst      w0, #0xff
0x000c86a4: e8 07 9f 1a              cset     w8, ne
0x000c86a8: e8 e3 00 39              strb     w8, [sp, #0x38]
0x000c86ac: 68 02 40 f9              ldr      x8, [x19]
0x000c86b0: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c86b4: 62 3b 00 90              adrp     x2, #0x834000
0x000c86b8: 43 3b 00 f0              adrp     x3, #0x833000
0x000c86bc: 42 f8 18 91              add      x2, x2, #0x63e
0x000c86c0: 63 9c 32 91              add      x3, x3, #0xca7
0x000c86c4: e0 03 13 aa              mov      x0, x19
0x000c86c8: e1 03 19 aa              mov      x1, x25
0x000c86cc: 00 01 3f d6              blr      x8
0x000c86d0: 68 02 40 f9              ldr      x8, [x19]
0x000c86d4: e2 03 00 aa              mov      x2, x0
0x000c86d8: 08 91 41 f9              ldr      x8, [x8, #0x320]
0x000c86dc: e0 03 13 aa              mov      x0, x19
0x000c86e0: e1 03 18 aa              mov      x1, x24
0x000c86e4: 00 01 3f d6              blr      x8
0x000c86e8: e0 bb 00 b9              str      w0, [sp, #0xb8]
0x000c86ec: 68 02 40 f9              ldr      x8, [x19]
0x000c86f0: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c86f4: 62 3b 00 90              adrp     x2, #0x834000
0x000c86f8: 43 3b 00 f0              adrp     x3, #0x833000
0x000c86fc: 42 18 19 91              add      x2, x2, #0x646
0x000c8700: 63 9c 32 91              add      x3, x3, #0xca7
0x000c8704: e0 03 13 aa              mov      x0, x19
0x000c8708: e1 03 19 aa              mov      x1, x25
0x000c870c: 00 01 3f d6              blr      x8
0x000c8710: 68 02 40 f9              ldr      x8, [x19]
0x000c8714: e2 03 00 aa              mov      x2, x0
0x000c8718: 08 91 41 f9              ldr      x8, [x8, #0x320]
0x000c871c: e0 03 13 aa              mov      x0, x19
0x000c8720: e1 03 18 aa              mov      x1, x24
0x000c8724: 00 01 3f d6              blr      x8
0x000c8728: e0 c7 00 b9              str      w0, [sp, #0xc4]
0x000c872c: 68 02 40 f9              ldr      x8, [x19]
0x000c8730: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c8734: 62 3b 00 90              adrp     x2, #0x834000
0x000c8738: 43 3b 00 f0              adrp     x3, #0x833000
0x000c873c: 42 44 19 91              add      x2, x2, #0x651
0x000c8740: 63 9c 32 91              add      x3, x3, #0xca7
0x000c8744: e0 03 13 aa              mov      x0, x19
0x000c8748: e1 03 19 aa              mov      x1, x25
0x000c874c: 00 01 3f d6              blr      x8
0x000c8750: 68 02 40 f9              ldr      x8, [x19]
0x000c8754: e2 03 00 aa              mov      x2, x0
0x000c8758: 08 91 41 f9              ldr      x8, [x8, #0x320]
0x000c875c: e0 03 13 aa              mov      x0, x19
0x000c8760: e1 03 18 aa              mov      x1, x24
0x000c8764: 00 01 3f d6              blr      x8
0x000c8768: e0 bf 00 b9              str      w0, [sp, #0xbc]
0x000c876c: 68 02 40 f9              ldr      x8, [x19]
0x000c8770: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c8774: 62 3b 00 90              adrp     x2, #0x834000
0x000c8778: 43 3b 00 f0              adrp     x3, #0x833000
0x000c877c: 42 74 19 91              add      x2, x2, #0x65d
0x000c8780: 63 9c 32 91              add      x3, x3, #0xca7
0x000c8784: e0 03 13 aa              mov      x0, x19
0x000c8788: e1 03 19 aa              mov      x1, x25
0x000c878c: 00 01 3f d6              blr      x8
0x000c8790: 68 02 40 f9              ldr      x8, [x19]
0x000c8794: e2 03 00 aa              mov      x2, x0
0x000c8798: 08 91 41 f9              ldr      x8, [x8, #0x320]
0x000c879c: e0 03 13 aa              mov      x0, x19
0x000c87a0: e1 03 18 aa              mov      x1, x24
0x000c87a4: 00 01 3f d6              blr      x8
0x000c87a8: e0 c3 00 b9              str      w0, [sp, #0xc0]
0x000c87ac: 68 02 40 f9              ldr      x8, [x19]
0x000c87b0: 08 79 43 f9              ldr      x8, [x8, #0x6f0]
0x000c87b4: e0 03 13 aa              mov      x0, x19
0x000c87b8: e1 03 17 aa              mov      x1, x23
0x000c87bc: e2 03 1f aa              mov      x2, xzr
0x000c87c0: 00 01 3f d6              blr      x8
0x000c87c4: e8 23 40 b9              ldr      w8, [sp, #0x20]
0x000c87c8: e2 03 00 aa              mov      x2, x0
0x000c87cc: 1f 19 00 71              cmp      w8, #6
0x000c87d0: 08 04 00 54              b.hi     #0xc8850
0x000c87d4: 49 3b 00 f0              adrp     x9, #0x833000
0x000c87d8: 29 39 2b 91              add      x9, x9, #0xace
0x000c87dc: 8a 00 00 10              adr      x10, #0xc87ec
0x000c87e0: 2b 69 68 38              ldrb     w11, [x9, x8]
0x000c87e4: 4a 09 0b 8b              add      x10, x10, x11, lsl #2
0x000c87e8: 40 01 1f d6              br       x10
0x000c87ec: e8 5b 40 b9              ldr      w8, [sp, #0x58]
0x000c87f0: e2 23 00 f9              str      x2, [sp, #0x40]
0x000c87f4: 08 75 1e 53              lsl      w8, w8, #2
0x000c87f8: 15 00 00 14              b        #0xc884c                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_renderModel+0x4ec
0x000c87fc: e8 27 4b 29              ldp      w8, w9, [sp, #0x58]
0x000c8800: 29 7d 08 1b              mul      w9, w9, w8
0x000c8804: 49 00 09 8b              add      x9, x2, x9
0x000c8808: e2 27 04 a9              stp      x2, x9, [sp, #0x40]
0x000c880c: e8 23 0c 29              stp      w8, w8, [sp, #0x60]
0x000c8810: 10 00 00 14              b        #0xc8850                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_renderModel+0x4f0
0x000c8814: e8 27 4b 29              ldp      w8, w9, [sp, #0x58]
0x000c8818: 0b 7d 01 53              lsr      w11, w8, #1
0x000c881c: 2a 7d 08 1b              mul      w10, w9, w8
0x000c8820: 69 7d 09 1b              mul      w9, w11, w9
0x000c8824: 4a 00 0a 8b              add      x10, x2, x10
0x000c8828: 29 7d 01 53              lsr      w9, w9, #1
0x000c882c: 49 01 09 8b              add      x9, x10, x9
0x000c8830: e2 2b 04 a9              stp      x2, x10, [sp, #0x40]
0x000c8834: e9 2b 00 f9              str      x9, [sp, #0x50]
0x000c8838: e8 2f 0c 29              stp      w8, w11, [sp, #0x60]
0x000c883c: eb 6b 00 b9              str      w11, [sp, #0x68]
0x000c8840: 04 00 00 14              b        #0xc8850                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_renderModel+0x4f0
0x000c8844: e8 5b 40 b9              ldr      w8, [sp, #0x58]
0x000c8848: e2 23 00 f9              str      x2, [sp, #0x40]
0x000c884c: e8 63 00 b9              str      w8, [sp, #0x60]
0x000c8850: 68 02 40 f9              ldr      x8, [x19]
0x000c8854: 08 7d 43 f9              ldr      x8, [x8, #0x6f8]
0x000c8858: e0 03 13 aa              mov      x0, x19
0x000c885c: e1 03 17 aa              mov      x1, x23
0x000c8860: e3 03 1f 2a              mov      w3, wzr
0x000c8864: 00 01 3f d6              blr      x8
0x000c8868: c0 9e 40 f9              ldr      x0, [x22, #0x138]
0x000c886c: e1 83 00 91              add      x1, sp, #0x20
0x000c8870: 3c d5 ff 97              bl       #0xbdd60                   ; -> _ZN5ycnn29YCNNModel14runModelBufferEPNS_11YCNNModelInE@plt
0x000c8874: d7 42 05 91              add      x23, x22, #0x150
0x000c8878: e0 03 17 aa              mov      x0, x23
0x000c887c: 55 cd ff 97              bl       #0xbbdd0                   ; -> _ZNSt6__ndk15mutex4lockEv@plt
0x000c8880: c0 9e 40 f9              ldr      x0, [x22, #0x138]
0x000c8884: c1 be 40 f9              ldr      x1, [x22, #0x178]
0x000c8888: ba ce ff 97              bl       #0xbc370                   ; -> _ZN5ycnn29YCNNModel11getModelOutEPNS_12YCNNModelOutE@plt
0x000c888c: e0 03 17 aa              mov      x0, x23
0x000c8890: 50 ce ff 97              bl       #0xbc1d0                   ; -> _ZNSt6__ndk15mutex6unlockEv@plt
0x000c8894: c0 9e 40 f9              ldr      x0, [x22, #0x138]
0x000c8898: e8 23 00 91              add      x8, sp, #8
0x000c889c: f6 23 00 91              add      x22, sp, #8
0x000c88a0: 98 cf ff 97              bl       #0xbc700                   ; -> _ZN5ycnn29YCNNModel7getInfoEv@plt
0x000c88a4: 68 02 40 f9              ldr      x8, [x19]
0x000c88a8: e9 23 40 39              ldrb     w9, [sp, #8]
0x000c88ac: ea 0f 40 f9              ldr      x10, [sp, #0x18]
0x000c88b0: cb 02 40 b2              orr      x11, x22, #1
0x000c88b4: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x000c88b8: 3f 01 00 72              tst      w9, #1
0x000c88bc: 61 01 8a 9a              csel     x1, x11, x10, eq
0x000c88c0: e0 03 13 aa              mov      x0, x19
0x000c88c4: 00 01 3f d6              blr      x8
0x000c88c8: e8 23 40 39              ldrb     w8, [sp, #8]
0x000c88cc: f6 03 00 aa              mov      x22, x0
0x000c88d0: 68 00 00 36              tbz      w8, #0, #0xc88dc
0x000c88d4: e0 0f 40 f9              ldr      x0, [sp, #0x18]
0x000c88d8: 86 c9 ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000c88dc: 68 02 40 f9              ldr      x8, [x19]
0x000c88e0: 08 a1 41 f9              ldr      x8, [x8, #0x340]
0x000c88e4: e0 03 13 aa              mov      x0, x19
0x000c88e8: e1 03 14 aa              mov      x1, x20
0x000c88ec: e2 03 15 aa              mov      x2, x21
0x000c88f0: e3 03 16 aa              mov      x3, x22
0x000c88f4: 00 01 3f d6              blr      x8
0x000c88f8: f3 53 40 f9              ldr      x19, [sp, #0xa0]
0x000c88fc: b3 02 00 b4              cbz      x19, #0xc8950
0x000c8900: f4 57 40 f9              ldr      x20, [sp, #0xa8]
0x000c8904: 9f 02 13 eb              cmp      x20, x19
0x000c8908: e0 01 00 54              b.eq     #0xc8944
0x000c890c: 80 82 5e f8              ldur     x0, [x20, #-0x18]
0x000c8910: 60 00 00 b4              cbz      x0, #0xc891c
0x000c8914: 80 02 1f f8              stur     x0, [x20, #-0x10]
0x000c8918: 76 c9 ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000c891c: 80 82 59 f8              ldur     x0, [x20, #-0x68]
0x000c8920: 95 c2 01 d1              sub      x21, x20, #0x70
0x000c8924: 60 00 00 b4              cbz      x0, #0xc8930
0x000c8928: 80 02 1a f8              stur     x0, [x20, #-0x60]
0x000c892c: 71 c9 ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000c8930: 7f 02 15 eb              cmp      x19, x21
0x000c8934: f4 03 15 aa              mov      x20, x21
0x000c8938: a1 fe ff 54              b.ne     #0xc890c
0x000c893c: e0 53 40 f9              ldr      x0, [sp, #0xa0]
0x000c8940: 02 00 00 14              b        #0xc8948                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_renderModel+0x5e8
0x000c8944: e0 03 13 aa              mov      x0, x19
0x000c8948: f3 57 00 f9              str      x19, [sp, #0xa8]
0x000c894c: 69 c9 ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000c8950: e0 3b 40 f9              ldr      x0, [sp, #0x70]
0x000c8954: 60 00 00 b4              cbz      x0, #0xc8960
0x000c8958: e0 3f 00 f9              str      x0, [sp, #0x78]
0x000c895c: 65 c9 ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000c8960: 48 17 40 f9              ldr      x8, [x26, #0x28]
0x000c8964: e9 03 40 f9              ldr      x9, [sp]
0x000c8968: 1f 01 09 eb              cmp      x8, x9
0x000c896c: 41 01 00 54              b.ne     #0xc8994
0x000c8970: fd 7b 53 a9              ldp      x29, x30, [sp, #0x130]
0x000c8974: f4 4f 52 a9              ldp      x20, x19, [sp, #0x120]
0x000c8978: f6 57 51 a9              ldp      x22, x21, [sp, #0x110]
0x000c897c: f8 5f 50 a9              ldp      x24, x23, [sp, #0x100]
0x000c8980: fa 67 4f a9              ldp      x26, x25, [sp, #0xf0]
0x000c8984: fc 73 40 f9              ldr      x28, [sp, #0xe0]
0x000c8988: e0 03 1f 2a              mov      w0, wzr
0x000c898c: ff 03 05 91              add      sp, sp, #0x140
0x000c8990: c0 03 5f d6              ret      
0x000c8994: f7 cb ff 97              bl       #0xbb970                   ; -> __stack_chk_fail@plt
0x000c8998: 12 00 00 14              b        #0xc89e0                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_renderModel+0x680
0x000c899c: e8 23 40 39              ldrb     w8, [sp, #8]
0x000c89a0: f3 03 00 aa              mov      x19, x0
0x000c89a4: 08 02 00 36              tbz      w8, #0, #0xc89e4
0x000c89a8: e0 0f 40 f9              ldr      x0, [sp, #0x18]
0x000c89ac: 51 c9 ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000c89b0: f4 53 40 f9              ldr      x20, [sp, #0xa0]
0x000c89b4: d4 01 00 b5              cbnz     x20, #0xc89ec
0x000c89b8: 1f 00 00 14              b        #0xc8a34                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_renderModel+0x6d4
0x000c89bc: 09 00 00 14              b        #0xc89e0                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_renderModel+0x680
0x000c89c0: f3 03 00 aa              mov      x19, x0
0x000c89c4: e0 03 17 aa              mov      x0, x23
0x000c89c8: 02 ce ff 97              bl       #0xbc1d0                   ; -> _ZNSt6__ndk15mutex6unlockEv@plt
0x000c89cc: f4 53 40 f9              ldr      x20, [sp, #0xa0]
0x000c89d0: f4 00 00 b5              cbnz     x20, #0xc89ec
0x000c89d4: 18 00 00 14              b        #0xc8a34                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_renderModel+0x6d4
0x000c89d8: 02 00 00 14              b        #0xc89e0                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_renderModel+0x680
0x000c89dc: 01 00 00 14              b        #0xc89e0                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_renderModel+0x680
0x000c89e0: f3 03 00 aa              mov      x19, x0
0x000c89e4: f4 53 40 f9              ldr      x20, [sp, #0xa0]
0x000c89e8: 74 02 00 b4              cbz      x20, #0xc8a34
0x000c89ec: f5 57 40 f9              ldr      x21, [sp, #0xa8]
0x000c89f0: bf 02 14 eb              cmp      x21, x20
0x000c89f4: c0 02 00 54              b.eq     #0xc8a4c
0x000c89f8: a0 82 5e f8              ldur     x0, [x21, #-0x18]
0x000c89fc: 60 00 00 b4              cbz      x0, #0xc8a08
0x000c8a00: a0 02 1f f8              stur     x0, [x21, #-0x10]
0x000c8a04: 3b c9 ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000c8a08: a0 82 59 f8              ldur     x0, [x21, #-0x68]
0x000c8a0c: b6 c2 01 d1              sub      x22, x21, #0x70
0x000c8a10: 60 00 00 b4              cbz      x0, #0xc8a1c
0x000c8a14: a0 02 1a f8              stur     x0, [x21, #-0x60]
0x000c8a18: 36 c9 ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000c8a1c: 9f 02 16 eb              cmp      x20, x22
0x000c8a20: f5 03 16 aa              mov      x21, x22
0x000c8a24: a1 fe ff 54              b.ne     #0xc89f8
0x000c8a28: e0 53 40 f9              ldr      x0, [sp, #0xa0]
0x000c8a2c: f4 57 00 f9              str      x20, [sp, #0xa8]
0x000c8a30: 30 c9 ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000c8a34: e0 3b 40 f9              ldr      x0, [sp, #0x70]
0x000c8a38: 60 00 00 b4              cbz      x0, #0xc8a44
0x000c8a3c: e0 3f 00 f9              str      x0, [sp, #0x78]
0x000c8a40: 2c c9 ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000c8a44: e0 03 13 aa              mov      x0, x19
0x000c8a48: e2 c4 ff 97              bl       #0xb9dd0                   ; -> _Unwind_Resume@plt
0x000c8a4c: e0 03 14 aa              mov      x0, x20
0x000c8a50: f7 ff ff 17              b        #0xc8a2c                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_renderModel+0x6cc
