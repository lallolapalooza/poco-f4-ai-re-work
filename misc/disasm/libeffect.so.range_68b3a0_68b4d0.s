; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_trill/native/libeffect.so
; target  : range 0x68b3a0:0x68b4d0
; start   : 0x68b3a0
; size    : 0x130
; bytes   : 0x130   instructions: 76
; generated: 2026-10-23T23:52:17

0x0068b3a0: 21 0c 20 91              add      x1, x1, #0x803
0x0068b3a4: b2 47 10 94              bl       #0xa9d26c
0x0068b3a8: 43 35 11 94              bl       #0xad88b4
0x0068b3ac: df 02 00 eb              cmp      x22, x0
0x0068b3b0: e0 03 00 54              b.eq     #0x68b42c
0x0068b3b4: c1 38 00 b0              adrp     x1, #0xda4000
0x0068b3b8: 93 97 10 94              bl       #0xab1204
0x0068b3bc: 44 35 11 94              bl       #0xad88cc
0x0068b3c0: f7 03 00 aa              mov      x23, x0
0x0068b3c4: a0 03 03 d1              sub      x0, x29, #0xc0
0x0068b3c8: b6 74 f1 97              bl       #0x2e86a0
0x0068b3cc: 86 28 10 94              bl       #0xa955e4
0x0068b3d0: df 02 17 eb              cmp      x22, x23
0x0068b3d4: e0 02 00 54              b.eq     #0x68b430
0x0068b3d8: c1 38 00 b0              adrp     x1, #0xda4000
0x0068b3dc: 21 0c 20 91              add      x1, x1, #0x803
0x0068b3e0: a3 47 10 94              bl       #0xa9d26c
0x0068b3e4: 37 35 11 94              bl       #0xad88c0
0x0068b3e8: c1 38 00 b0              adrp     x1, #0xda4000
0x0068b3ec: f7 03 00 aa              mov      x23, x0
0x0068b3f0: 85 97 10 94              bl       #0xab1204
0x0068b3f4: 39 35 11 94              bl       #0xad88d8
0x0068b3f8: 08 00 40 39              ldrb     w8, [x0]
0x0068b3fc: 09 08 40 f9              ldr      x9, [x0, #0x10]
0x0068b400: e3 43 0c 91              add      x3, sp, #0x310
0x0068b404: e4 43 06 91              add      x4, sp, #0x190
0x0068b408: 1f 01 00 72              tst      w8, #1
0x0068b40c: 22 15 80 9a              csinc    x2, x9, x0, ne
0x0068b410: d7 59 10 94              bl       #0xaa1b6c
0x0068b414: 1c 0f 0d 94              bl       #0x9cf084
0x0068b418: fc 03 00 2a              mov      w28, w0
0x0068b41c: a0 03 03 d1              sub      x0, x29, #0xc0
0x0068b420: a0 74 f1 97              bl       #0x2e86a0
0x0068b424: 70 28 10 94              bl       #0xa955e4
0x0068b428: 08 00 00 14              b        #0x68b448
0x0068b42c: 6e 28 10 94              bl       #0xa955e4
0x0068b430: c1 38 00 b0              adrp     x1, #0xda4000
0x0068b434: c2 38 00 b0              adrp     x2, #0xda4000
0x0068b438: 21 7c 1f 91              add      x1, x1, #0x7df
0x0068b43c: 42 44 20 91              add      x2, x2, #0x811
0x0068b440: 45 04 10 94              bl       #0xa8c554
0x0068b444: 9c 00 80 12              mov      w28, #-5
0x0068b448: 03 00 12 94              bl       #0xb0b454
0x0068b44c: 83 94 11 94              bl       #0xaf0658
0x0068b450: 99 c9 11 94              bl       #0xafdab4
0x0068b454: a0 d9 11 94              bl       #0xb01ad4
0x0068b458: 7c b9 ff 35              cbnz     w28, #0x68ab84
0x0068b45c: 88 92 40 f9              ldr      x8, [x20, #0x120]
0x0068b460: f5 68 11 94              bl       #0xae5834
0x0068b464: 41 bb ff 54              b.ne     #0x68abcc
0x0068b468: 09 18 80 52              mov      w9, #0xc0
0x0068b46c: 09 19 00 b9              str      w9, [x8, #0x18]
0x0068b470: d7 fd ff 17              b        #0x68abcc
0x0068b474: ef e7 f0 97              bl       #0x2c5430                  ; -> __stack_chk_fail@plt
0x0068b478: ff c3 06 d1              sub      sp, sp, #0x1b0
0x0068b47c: fc 5f 17 a9              stp      x28, x23, [sp, #0x170]
0x0068b480: f6 57 18 a9              stp      x22, x21, [sp, #0x180]
0x0068b484: f4 4f 19 a9              stp      x20, x19, [sp, #0x190]
0x0068b488: fd 7b 1a a9              stp      x29, x30, [sp, #0x1a0]
0x0068b48c: fd 83 06 91              add      x29, sp, #0x1a0
0x0068b490: a4 b8 11 94              bl       #0xaf9720
0x0068b494: 20 04 00 b4              cbz      x0, #0x68b518
0x0068b498: f5 03 02 aa              mov      x21, x2
0x0068b49c: 22 04 00 b4              cbz      x2, #0x68b520
0x0068b4a0: f3 03 01 aa              mov      x19, x1
0x0068b4a4: f4 03 00 aa              mov      x20, x0
0x0068b4a8: e0 03 00 91              mov      x0, sp
0x0068b4ac: 41 04 10 94              bl       #0xa8c5b0
0x0068b4b0: e0 63 00 91              add      x0, sp, #0x18
0x0068b4b4: 82 00 80 52              mov      w2, #4
0x0068b4b8: e1 03 15 aa              mov      x1, x21
0x0068b4bc: 10 b5 f7 97              bl       #0x4788fc
0x0068b4c0: e8 53 40 f9              ldr      x8, [sp, #0xa0]
0x0068b4c4: 28 03 00 b4              cbz      x8, #0x68b528
0x0068b4c8: e0 63 00 91              add      x0, sp, #0x18
0x0068b4cc: 41 00 80 52              mov      w1, #2
