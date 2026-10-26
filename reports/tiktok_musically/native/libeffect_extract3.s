; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/ttlibs/libeffect.so
; target  : address 0x696634
; start   : 0x696634
; size    : 0x2d0
; bytes   : 0x2d0   instructions: 180
; generated: 2026-10-23T23:55:00

0x00696634: f3 0f 1e f8              str      x19, [sp, #-0x20]!
0x00696638: fd 7b 01 a9              stp      x29, x30, [sp, #0x10]
0x0069663c: fd 43 00 91              add      x29, sp, #0x10
0x00696640: b7 72 10 94              bl       #0xab311c
0x00696644: 60 e2 01 91              add      x0, x19, #0x78
0x00696648: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x0069664c: 60 82 02 ad              stp      q0, q0, [x19, #0x50]
0x00696650: 60 12 80 3d              str      q0, [x19, #0x40]
0x00696654: 16 43 f1 97              bl       #0x2e72ac
0x00696658: 7f 42 02 39              strb     wzr, [x19, #0x90]
0x0069665c: fd 7b 41 a9              ldp      x29, x30, [sp, #0x10]
0x00696660: 58 db 0f 14              b        #0xa8d3c0
0x00696664: ff 43 07 d1              sub      sp, sp, #0x1d0
0x00696668: fc bb 00 f9              str      x28, [sp, #0x170]
0x0069666c: fa 67 18 a9              stp      x26, x25, [sp, #0x180]
0x00696670: f8 5f 19 a9              stp      x24, x23, [sp, #0x190]
0x00696674: f6 57 1a a9              stp      x22, x21, [sp, #0x1a0]
0x00696678: f4 4f 1b a9              stp      x20, x19, [sp, #0x1b0]
0x0069667c: fd 7b 1c a9              stp      x29, x30, [sp, #0x1c0]
0x00696680: fd 03 07 91              add      x29, sp, #0x1c0
0x00696684: fb e8 0f 94              bl       #0xa90a70
0x00696688: 18 60 00 91              add      x24, x0, #0x18
0x0069668c: f3 03 00 aa              mov      x19, x0
0x00696690: f4 03 04 aa              mov      x20, x4
0x00696694: a8 83 1a f8              stur     x8, [x29, #-0x58]
0x00696698: 1f 08 00 f9              str      xzr, [x0, #0x10]
0x0069669c: e0 03 18 aa              mov      x0, x24
0x006966a0: 74 f3 10 94              bl       #0xad3470
0x006966a4: e4 ff ff 97              bl       #0x696634
0x006966a8: 79 c2 02 91              add      x25, x19, #0xb0
0x006966ac: ec 8f 10 94              bl       #0xaba65c
0x006966b0: bf 02 00 72              tst      w21, #1
0x006966b4: e8 01 80 52              mov      w8, #0xf
0x006966b8: 89 01 80 52              mov      w9, #0xc
0x006966bc: 6a 01 80 52              mov      w10, #0xb
0x006966c0: 08 11 9f 1a              csel     w8, w8, wzr, ne
0x006966c4: df 02 00 72              tst      w22, #1
0x006966c8: 29 15 9f 1a              csinc    w9, w9, wzr, ne
0x006966cc: 48 11 88 1a              csel     w8, w10, w8, ne
0x006966d0: ff 02 00 72              tst      w23, #1
0x006966d4: 21 11 88 1a              csel     w1, w9, w8, ne
0x006966d8: e0 23 00 91              add      x0, sp, #8
0x006966dc: cf fe ff 97              bl       #0x696218
0x006966e0: 01 97 10 94              bl       #0xabc2e4
0x006966e4: 3f 00 00 94              bl       #0x6967e0
0x006966e8: e0 23 00 91              add      x0, sp, #8
0x006966ec: be e0 ff 97              bl       #0x68e9e4
0x006966f0: e8 23 00 91              add      x8, sp, #8
0x006966f4: 40 00 80 52              mov      w0, #2
0x006966f8: 01 23 80 52              mov      w1, #0x118
0x006966fc: 40 e5 0f 94              bl       #0xa8fbfc
0x00696700: ab 7a 11 94              bl       #0xaf51ac
0x00696704: e2 03 19 aa              mov      x2, x25
0x00696708: 63 d4 0f 94              bl       #0xa8b894
0x0069670c: 69 e6 0f 94              bl       #0xa900b0
0x00696710: 1f 08 10 94              bl       #0xa9878c
0x00696714: 47 00 00 94              bl       #0x696830
0x00696718: 60 0a 00 f9              str      x0, [x19, #0x10]
0x0069671c: 48 17 40 f9              ldr      x8, [x26, #0x28]
0x00696720: d1 ea 0f 94              bl       #0xa91264
0x00696724: 01 01 00 54              b.ne     #0x696744
0x00696728: fd 7b 5c a9              ldp      x29, x30, [sp, #0x1c0]
0x0069672c: f4 4f 5b a9              ldp      x20, x19, [sp, #0x1b0]
0x00696730: f6 57 5a a9              ldp      x22, x21, [sp, #0x1a0]
0x00696734: f8 5f 59 a9              ldp      x24, x23, [sp, #0x190]
0x00696738: fa 67 58 a9              ldp      x26, x25, [sp, #0x180]
0x0069673c: fc bb 40 f9              ldr      x28, [sp, #0x170]
0x00696740: 00 fb 0f 14              b        #0xa95340
0x00696744: 3b bb f0 97              bl       #0x2c5430                  ; -> __stack_chk_fail@plt
0x00696748: e3 03 1e aa              mov      x3, x30
0x0069674c: 0a 84 10 94              bl       #0xab7774
0x00696750: fe 03 03 aa              mov      x30, x3
0x00696754: fd 7b 0d a9              stp      x29, x30, [sp, #0xd0]
0x00696758: fd 43 03 91              add      x29, sp, #0xd0
0x0069675c: 3c e0 0f 94              bl       #0xa8e84c
0x00696760: 16 a0 00 91              add      x22, x0, #0x28
0x00696764: e7 71 10 94              bl       #0xab2f00
0x00696768: a8 83 1c f8              stur     x8, [x29, #-0x38]
0x0069676c: 01 00 00 b9              str      w1, [x0]
0x00696770: c0 a9 10 94              bl       #0xac0e70
0x00696774: e0 03 16 aa              mov      x0, x22
0x00696778: ef 92 10 94              bl       #0xabb334
0x0069677c: ae ff ff 97              bl       #0x696634
0x00696780: 9f 06 00 71              cmp      w20, #1
0x00696784: 28 01 80 52              mov      w8, #9
0x00696788: 08 01 9f 5a              csinv    w8, w8, wzr, eq
0x0069678c: 9f 02 00 71              cmp      w20, #0
0x00696790: 09 01 80 52              mov      w9, #8
0x00696794: 21 01 88 1a              csel     w1, w9, w8, eq
0x00696798: e0 03 00 91              mov      x0, sp
0x0069679c: 9f fe ff 97              bl       #0x696218
0x006967a0: 1f e6 11 94              bl       #0xb1001c
0x006967a4: e0 03 00 91              mov      x0, sp
0x006967a8: 8f e0 ff 97              bl       #0x68e9e4
0x006967ac: a0 42 00 91              add      x0, x21, #0x10
0x006967b0: 02 c5 11 94              bl       #0xb07bb8
0x006967b4: 5f f1 11 94              bl       #0xb12d30
0x006967b8: a0 06 00 f9              str      x0, [x21, #8]
0x006967bc: e8 16 40 f9              ldr      x8, [x23, #0x28]
0x006967c0: bb de 0f 94              bl       #0xa8e2ac
0x006967c4: c1 00 00 54              b.ne     #0x6967dc
0x006967c8: fd 7b 4d a9              ldp      x29, x30, [sp, #0xd0]
0x006967cc: f4 4f 4c a9              ldp      x20, x19, [sp, #0xc0]
0x006967d0: f6 57 4b a9              ldp      x22, x21, [sp, #0xb0]
0x006967d4: f7 53 40 f9              ldr      x23, [sp, #0xa0]
0x006967d8: d5 df 0f 14              b        #0xa8e72c
0x006967dc: 15 bb f0 97              bl       #0x2c5430                  ; -> __stack_chk_fail@plt
0x006967e0: f4 4f be a9              stp      x20, x19, [sp, #-0x20]!
0x006967e4: fd 7b 01 a9              stp      x29, x30, [sp, #0x10]
0x006967e8: fd 43 00 91              add      x29, sp, #0x10
0x006967ec: d2 2b 11 94              bl       #0xae1734
0x006967f0: 0b e1 11 94              bl       #0xb0ec1c
0x006967f4: 60 0e c0 3d              ldr      q0, [x19, #0x30]
0x006967f8: 41 f9 10 94              bl       #0xad4cfc
0x006967fc: 80 0e 80 3d              str      q0, [x20, #0x30]
0x00696800: 12 6c f2 97              bl       #0x331848
0x00696804: 6b 3b 11 94              bl       #0xae55b0
0x00696808: 10 6c f2 97              bl       #0x331848
0x0069680c: 68 c2 41 39              ldrb     w8, [x19, #0x70]
0x00696810: a9 15 11 94              bl       #0xadbeb4
0x00696814: 88 c2 01 39              strb     w8, [x20, #0x70]
0x00696818: 9d 71 f1 97              bl       #0x2f2e8c
0x0069681c: 68 42 42 39              ldrb     w8, [x19, #0x90]
0x00696820: e0 03 14 aa              mov      x0, x20
0x00696824: 88 42 02 39              strb     w8, [x20, #0x90]
0x00696828: fd 7b 41 a9              ldp      x29, x30, [sp, #0x10]
0x0069682c: b8 d7 0f 14              b        #0xa8c70c
0x00696830: ff 43 01 d1              sub      sp, sp, #0x50
0x00696834: f6 57 02 a9              stp      x22, x21, [sp, #0x20]
0x00696838: f4 4f 03 a9              stp      x20, x19, [sp, #0x30]
0x0069683c: fd 7b 04 a9              stp      x29, x30, [sp, #0x40]
0x00696840: fd 03 01 91              add      x29, sp, #0x40
0x00696844: fe 2d 10 94              bl       #0xaa203c
0x00696848: 00 08 80 52              mov      w0, #0x40
0x0069684c: f4 03 01 aa              mov      x20, x1
0x00696850: ac 12 10 94              bl       #0xa9b300
0x00696854: f3 03 00 aa              mov      x19, x0
0x00696858: e0 03 00 91              mov      x0, sp
0x0069685c: 9c ed 0f 94              bl       #0xa91ecc
0x00696860: 85 3a 40 b9              ldr      w5, [x20, #0x38]
0x00696864: 83 02 01 91              add      x3, x20, #0x40
0x00696868: 84 62 01 91              add      x4, x20, #0x58
0x0069686c: e2 03 00 91              mov      x2, sp
0x00696870: f9 d6 0f 94              bl       #0xa8c454
0x00696874: 0c 00 00 94              bl       #0x6968a4
0x00696878: e0 03 00 91              mov      x0, sp
0x0069687c: 89 47 f1 97              bl       #0x2e86a0
0x00696880: c8 16 40 f9              ldr      x8, [x22, #0x28]
0x00696884: 75 d2 0f 94              bl       #0xa8b258
0x00696888: c1 00 00 54              b.ne     #0x6968a0
0x0069688c: e0 03 13 aa              mov      x0, x19
0x00696890: fd 7b 44 a9              ldp      x29, x30, [sp, #0x40]
0x00696894: f4 4f 43 a9              ldp      x20, x19, [sp, #0x30]
0x00696898: f6 57 42 a9              ldp      x22, x21, [sp, #0x20]
0x0069689c: 2e d3 0f 14              b        #0xa8b554
0x006968a0: e4 ba f0 97              bl       #0x2c5430                  ; -> __stack_chk_fail@plt
0x006968a4: e6 03 1e aa              mov      x6, x30
0x006968a8: f4 61 10 94              bl       #0xaaf078
0x006968ac: fe 03 06 aa              mov      x30, x6
0x006968b0: fd 7b 09 a9              stp      x29, x30, [sp, #0x90]
0x006968b4: fd 43 02 91              add      x29, sp, #0x90
0x006968b8: eb e3 0f 94              bl       #0xa8f864
0x006968bc: b5 1a 11 94              bl       #0xadd390
0x006968c0: 00 20 00 91              add      x0, x0, #8
0x006968c4: e1 03 03 aa              mov      x1, x3
0x006968c8: f6 03 05 2a              mov      w22, w5
0x006968cc: f7 03 04 aa              mov      x23, x4
0x006968d0: 24 29 11 94              bl       #0xae0d60
0x006968d4: 7d 99 f1 97              bl       #0x2fcec8
0x006968d8: b4 82 00 91              add      x20, x21, #0x20
0x006968dc: 60 e5 0f 94              bl       #0xa8fe5c
0x006968e0: 7a 99 f1 97              bl       #0x2fcec8
0x006968e4: e8 c3 00 91              add      x8, sp, #0x30
0x006968e8: 17 21 00 91              add      x23, x8, #8
0x006968ec: b6 3a 00 b9              str      w22, [x21, #0x38]
0x006968f0: ff ff 03 a9              stp      xzr, xzr, [sp, #0x38]
0x006968f4: f7 1b 00 f9              str      x23, [sp, #0x30]
0x006968f8: 00 03 40 f9              ldr      x0, [x24]
0x006968fc: e2 c3 00 91              add      x2, sp, #0x30
0x00696900: e1 03 13 aa              mov      x1, x19
