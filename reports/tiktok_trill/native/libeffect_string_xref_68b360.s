; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_trill/native/libeffect.so
; target  : range 0x68b360:0x68b3c0
; start   : 0x68b360
; size    : 0x60
; bytes   : 0x60   instructions: 24
; generated: 2026-10-23T23:55:41

0x0068b360: f6 b7 01 f9              str      x22, [sp, #0x368]
0x0068b364: 3d 78 f1 97              bl       #0x2e9458
0x0068b368: 35 ef 10 94              bl       #0xac703c
0x0068b36c: f7 03 00 2a              mov      w23, w0
0x0068b370: 9d 28 10 94              bl       #0xa955e4
0x0068b374: 57 01 00 34              cbz      w23, #0x68b39c
0x0068b378: c1 38 00 b0              adrp     x1, #0xda4000
0x0068b37c: c2 36 00 d0              adrp     x2, #0xd65000
0x0068b380: 21 7c 1f 91              add      x1, x1, #0x7df
0x0068b384: 42 00 3a 91              add      x2, x2, #0xe80
0x0068b388: 73 04 10 94              bl       #0xa8c554
0x0068b38c: 1c 00 80 12              mov      w28, #-1
0x0068b390: 2e 00 00 14              b        #0x68b448
0x0068b394: 9f 92 00 f9              str      xzr, [x20, #0x120]
0x0068b398: d0 fd ff 17              b        #0x68aad8
0x0068b39c: c1 38 00 b0              adrp     x1, #0xda4000
0x0068b3a0: 21 0c 20 91              add      x1, x1, #0x803
0x0068b3a4: b2 47 10 94              bl       #0xa9d26c
0x0068b3a8: 43 35 11 94              bl       #0xad88b4
0x0068b3ac: df 02 00 eb              cmp      x22, x0
0x0068b3b0: e0 03 00 54              b.eq     #0x68b42c
0x0068b3b4: c1 38 00 b0              adrp     x1, #0xda4000
0x0068b3b8: 93 97 10 94              bl       #0xab1204
0x0068b3bc: 44 35 11 94              bl       #0xad88cc
