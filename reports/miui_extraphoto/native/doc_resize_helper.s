; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/xp/libdoc_photo_process.so
; target  : address 0x78fe0
; start   : 0x78fe0
; size    : 0x340
; bytes   : 0x340   instructions: 208
; generated: 2026-10-24T00:54:09

0x00078fe0: f3 0f 1e f8              str      x19, [sp, #-0x20]!
0x00078fe4: fd 7b 01 a9              stp      x29, x30, [sp, #0x10]
0x00078fe8: fd 43 00 91              add      x29, sp, #0x10
0x00078fec: f3 03 00 aa              mov      x19, x0
0x00078ff0: 7b 02 00 94              bl       #0x799dc
0x00078ff4: 60 26 40 f9              ldr      x0, [x19, #0x48]
0x00078ff8: 68 42 01 91              add      x8, x19, #0x50
0x00078ffc: 1f 00 08 eb              cmp      x0, x8
0x00079000: 40 00 00 54              b.eq     #0x79008
0x00079004: e7 28 08 94              bl       #0x2833a0
0x00079008: fd 7b 41 a9              ldp      x29, x30, [sp, #0x10]
0x0007900c: f3 07 42 f8              ldr      x19, [sp], #0x20
0x00079010: c0 03 5f d6              ret      
0x00079014: a9 fa ff 97              bl       #0x77ab8
0x00079018: ff 83 01 d1              sub      sp, sp, #0x60
0x0007901c: f4 4f 04 a9              stp      x20, x19, [sp, #0x40]
0x00079020: fd 7b 05 a9              stp      x29, x30, [sp, #0x50]
0x00079024: fd 43 01 91              add      x29, sp, #0x50
0x00079028: 54 d0 3b d5              mrs      x20, tpidr_el0
0x0007902c: f3 03 08 aa              mov      x19, x8
0x00079030: 88 16 40 f9              ldr      x8, [x20, #0x28]
0x00079034: e9 5f a8 52              mov      w9, #0x42ff0000
0x00079038: 6a 22 00 91              add      x10, x19, #8
0x0007903c: a8 83 1e f8              stur     x8, [x29, #-0x18]
0x00079040: e8 03 13 aa              mov      x8, x19
0x00079044: 69 02 00 b9              str      w9, [x19]
0x00079048: 7f 3e 00 b9              str      wzr, [x19, #0x3c]
0x0007904c: 7f 42 03 f8              stur     xzr, [x19, #0x34]
0x00079050: 7f c2 02 f8              stur     xzr, [x19, #0x2c]
0x00079054: 7f 42 02 f8              stur     xzr, [x19, #0x24]
0x00079058: 7f c2 01 f8              stur     xzr, [x19, #0x1c]
0x0007905c: 7f 42 01 f8              stur     xzr, [x19, #0x14]
0x00079060: 7f c2 00 f8              stur     xzr, [x19, #0xc]
0x00079064: 7f 42 00 f8              stur     xzr, [x19, #4]
0x00079068: 1f 0d 05 f8              str      xzr, [x8, #0x50]!
0x0007906c: 7f 2e 00 f9              str      xzr, [x19, #0x58]
0x00079070: 6a 22 04 a9              stp      x10, x8, [x19, #0x40]
0x00079074: 08 24 41 29              ldp      w8, w9, [x0, #8]
0x00079078: 3f 01 08 6b              cmp      w9, w8
0x0007907c: 0a b1 89 1a              csel     w10, w8, w9, lt
0x00079080: 5f 01 01 6b              cmp      w10, w1
0x00079084: ad 00 00 54              b.le     #0x79098
0x00079088: 29 7d 01 1b              mul      w9, w9, w1
0x0007908c: 08 7d 01 1b              mul      w8, w8, w1
0x00079090: 29 0d ca 1a              sdiv     w9, w9, w10
0x00079094: 08 0d ca 1a              sdiv     w8, w8, w10
0x00079098: 4a 04 00 51              sub      w10, w2, #1
0x0007909c: 49 01 09 0b              add      w9, w10, w9
0x000790a0: 48 01 08 0b              add      w8, w10, w8
0x000790a4: 29 0d c2 1a              sdiv     w9, w9, w2
0x000790a8: 08 0d c2 1a              sdiv     w8, w8, w2
0x000790ac: 29 7d 02 1b              mul      w9, w9, w2
0x000790b0: 08 7d 02 1b              mul      w8, w8, w2
0x000790b4: 3f 01 01 6b              cmp      w9, w1
0x000790b8: 2a 20 a0 52              mov      w10, #0x1010000
0x000790bc: 29 c0 89 1a              csel     w9, w1, w9, gt
0x000790c0: 1f 01 01 6b              cmp      w8, w1
0x000790c4: ea 23 00 b9              str      w10, [sp, #0x20]
0x000790c8: 2a 40 a0 52              mov      w10, #0x2010000
0x000790cc: 28 c0 88 1a              csel     w8, w1, w8, gt
0x000790d0: e0 ff 02 a9              stp      x0, xzr, [sp, #0x28]
0x000790d4: f3 7f 01 a9              stp      x19, xzr, [sp, #0x10]
0x000790d8: e9 03 00 b9              str      w9, [sp]
0x000790dc: e8 ab 00 29              stp      w8, w10, [sp, #4]
0x000790e0: e0 83 00 91              add      x0, sp, #0x20
0x000790e4: e1 23 00 91              add      x1, sp, #8
0x000790e8: e2 03 00 91              mov      x2, sp
0x000790ec: e0 03 67 9e              fmov     d0, xzr
0x000790f0: e1 03 67 9e              fmov     d1, xzr
0x000790f4: e3 07 00 32              mov      w3, #3
0x000790f8: 0a 29 0c 94              bl       #0x383520
0x000790fc: 88 16 40 f9              ldr      x8, [x20, #0x28]
0x00079100: a9 83 5e f8              ldur     x9, [x29, #-0x18]
0x00079104: 1f 01 09 eb              cmp      x8, x9
0x00079108: a1 00 00 54              b.ne     #0x7911c
0x0007910c: fd 7b 45 a9              ldp      x29, x30, [sp, #0x50]
0x00079110: f4 4f 44 a9              ldp      x20, x19, [sp, #0x40]
0x00079114: ff 83 01 91              add      sp, sp, #0x60
0x00079118: c0 03 5f d6              ret      
0x0007911c: c5 bc ff 97              bl       #0x68430                   ; -> __stack_chk_fail@plt
0x00079120: f4 03 00 aa              mov      x20, x0
0x00079124: e0 03 13 aa              mov      x0, x19
0x00079128: ae ff ff 97              bl       #0x78fe0
0x0007912c: e0 03 14 aa              mov      x0, x20
0x00079130: dd e9 0e 94              bl       #0x4338a4
0x00079134: fc 0f 1c f8              str      x28, [sp, #-0x40]!
0x00079138: f6 57 01 a9              stp      x22, x21, [sp, #0x10]
0x0007913c: f4 4f 02 a9              stp      x20, x19, [sp, #0x20]
0x00079140: fd 7b 03 a9              stp      x29, x30, [sp, #0x30]
0x00079144: fd c3 00 91              add      x29, sp, #0x30
0x00079148: ff 03 0a d1              sub      sp, sp, #0x280
0x0007914c: 55 d0 3b d5              mrs      x21, tpidr_el0
0x00079150: a8 16 40 f9              ldr      x8, [x21, #0x28]
0x00079154: f4 03 01 aa              mov      x20, x1
0x00079158: f3 03 00 aa              mov      x19, x0
0x0007915c: a8 83 1c f8              stur     x8, [x29, #-0x38]
0x00079160: 08 20 40 f9              ldr      x8, [x0, #0x40]
0x00079164: 29 20 40 f9              ldr      x9, [x1, #0x40]
0x00079168: 0a 21 40 29              ldp      w10, w8, [x8]
0x0007916c: 29 2d 40 29              ldp      w9, w11, [x9]
0x00079170: 1f 01 0b 6b              cmp      w8, w11
0x00079174: 40 01 49 7a              ccmp     w10, w9, #0, eq
0x00079178: 20 06 00 54              b.eq     #0x7923c
0x0007917c: e0 63 05 91              add      x0, sp, #0x158
0x00079180: e1 03 1e 32              mov      w1, #4
0x00079184: f6 63 05 91              add      x22, sp, #0x158
0x00079188: f4 f6 ff 97              bl       #0x76d58
0x0007918c: c0 62 00 91              add      x0, x22, #0x18
0x00079190: e1 1d 00 90              adrp     x1, #0x435000
0x00079194: 21 44 2d 91              add      x1, x1, #0xb51
0x00079198: 53 fb ff 97              bl       #0x77ee4
0x0007919c: 68 22 40 f9              ldr      x8, [x19, #0x40]
0x000791a0: 08 25 40 29              ldp      w8, w9, [x8]
0x000791a4: e9 23 04 29              stp      w9, w8, [sp, #0x20]
0x000791a8: e0 63 05 91              add      x0, sp, #0x158
0x000791ac: e1 83 00 91              add      x1, sp, #0x20
0x000791b0: 3a 00 00 94              bl       #0x79298
0x000791b4: e0 63 05 91              add      x0, sp, #0x158
0x000791b8: 23 f7 ff 97              bl       #0x76e44
0x000791bc: 28 20 a0 52              mov      w8, #0x1010000
0x000791c0: 29 40 a0 52              mov      w9, #0x2010000
0x000791c4: f3 ff 02 a9              stp      x19, xzr, [sp, #0x28]
0x000791c8: f3 7f 01 a9              stp      x19, xzr, [sp, #0x10]
0x000791cc: e8 23 00 b9              str      w8, [sp, #0x20]
0x000791d0: e9 0b 00 b9              str      w9, [sp, #8]
0x000791d4: 88 22 40 f9              ldr      x8, [x20, #0x40]
0x000791d8: 08 25 40 29              ldp      w8, w9, [x8]
0x000791dc: e9 23 00 29              stp      w9, w8, [sp]
0x000791e0: e0 83 00 91              add      x0, sp, #0x20
0x000791e4: e1 23 00 91              add      x1, sp, #8
0x000791e8: e2 03 00 91              mov      x2, sp
0x000791ec: e0 03 67 9e              fmov     d0, xzr
0x000791f0: e1 03 67 9e              fmov     d1, xzr
0x000791f4: e3 03 00 32              mov      w3, #1
0x000791f8: ca 28 0c 94              bl       #0x383520
0x000791fc: e0 e3 00 91              add      x0, sp, #0x38
0x00079200: e1 03 1e 32              mov      w1, #4
0x00079204: f4 e3 00 91              add      x20, sp, #0x38
0x00079208: d4 f6 ff 97              bl       #0x76d58
0x0007920c: 80 62 00 91              add      x0, x20, #0x18
0x00079210: e1 1d 00 90              adrp     x1, #0x435000
0x00079214: 21 98 2d 91              add      x1, x1, #0xb66
0x00079218: 33 fb ff 97              bl       #0x77ee4
0x0007921c: 68 22 40 f9              ldr      x8, [x19, #0x40]
0x00079220: 08 25 40 29              ldp      w8, w9, [x8]
0x00079224: e9 23 04 29              stp      w9, w8, [sp, #0x20]
0x00079228: e0 e3 00 91              add      x0, sp, #0x38
0x0007922c: e1 83 00 91              add      x1, sp, #0x20
0x00079230: 1a 00 00 94              bl       #0x79298
0x00079234: e0 e3 00 91              add      x0, sp, #0x38
0x00079238: 03 f7 ff 97              bl       #0x76e44
0x0007923c: a8 16 40 f9              ldr      x8, [x21, #0x28]
0x00079240: a9 83 5c f8              ldur     x9, [x29, #-0x38]
0x00079244: 1f 01 09 eb              cmp      x8, x9
0x00079248: e1 00 00 54              b.ne     #0x79264
0x0007924c: ff 03 0a 91              add      sp, sp, #0x280
0x00079250: fd 7b 43 a9              ldp      x29, x30, [sp, #0x30]
0x00079254: f4 4f 42 a9              ldp      x20, x19, [sp, #0x20]
0x00079258: f6 57 41 a9              ldp      x22, x21, [sp, #0x10]
0x0007925c: fc 07 44 f8              ldr      x28, [sp], #0x40
0x00079260: c0 03 5f d6              ret      
0x00079264: 73 bc ff 97              bl       #0x68430                   ; -> __stack_chk_fail@plt
0x00079268: 01 00 00 14              b        #0x7926c
0x0007926c: f3 03 00 aa              mov      x19, x0
0x00079270: e0 e3 00 91              add      x0, sp, #0x38
0x00079274: 06 00 00 14              b        #0x7928c
0x00079278: f3 03 00 aa              mov      x19, x0
0x0007927c: 05 00 00 14              b        #0x79290
0x00079280: 01 00 00 14              b        #0x79284
0x00079284: f3 03 00 aa              mov      x19, x0
0x00079288: e0 63 05 91              add      x0, sp, #0x158
0x0007928c: ee f6 ff 97              bl       #0x76e44
0x00079290: e0 03 13 aa              mov      x0, x19
0x00079294: 84 e9 0e 94              bl       #0x4338a4
0x00079298: f4 4f be a9              stp      x20, x19, [sp, #-0x20]!
0x0007929c: fd 7b 01 a9              stp      x29, x30, [sp, #0x10]
0x000792a0: fd 43 00 91              add      x29, sp, #0x10
0x000792a4: f3 03 01 aa              mov      x19, x1
0x000792a8: 01 ad 00 f0              adrp     x1, #0x161c000
0x000792ac: f4 03 00 aa              mov      x20, x0
0x000792b0: 00 60 00 91              add      x0, x0, #0x18
0x000792b4: 21 08 07 91              add      x1, x1, #0x1c2
0x000792b8: 0b fb ff 97              bl       #0x77ee4
0x000792bc: 61 02 40 b9              ldr      w1, [x19]
0x000792c0: 42 02 00 94              bl       #0x79bc8
0x000792c4: e1 1d 00 90              adrp     x1, #0x435000
0x000792c8: 21 1c 2e 91              add      x1, x1, #0xb87
0x000792cc: 06 fb ff 97              bl       #0x77ee4
0x000792d0: 61 06 40 b9              ldr      w1, [x19, #4]
0x000792d4: 3d 02 00 94              bl       #0x79bc8
0x000792d8: 01 af 00 b0              adrp     x1, #0x165a000
0x000792dc: 21 7c 3c 91              add      x1, x1, #0xf1f
0x000792e0: 01 fb ff 97              bl       #0x77ee4
0x000792e4: fd 7b 41 a9              ldp      x29, x30, [sp, #0x10]
0x000792e8: e0 03 14 aa              mov      x0, x20
0x000792ec: f4 4f c2 a8              ldp      x20, x19, [sp], #0x20
0x000792f0: c0 03 5f d6              ret      
0x000792f4: ff 83 01 d1              sub      sp, sp, #0x60
0x000792f8: f4 4f 04 a9              stp      x20, x19, [sp, #0x40]
0x000792fc: fd 7b 05 a9              stp      x29, x30, [sp, #0x50]
0x00079300: fd 43 01 91              add      x29, sp, #0x50
0x00079304: 54 d0 3b d5              mrs      x20, tpidr_el0
0x00079308: f3 03 08 aa              mov      x19, x8
0x0007930c: 88 16 40 f9              ldr      x8, [x20, #0x28]
0x00079310: e9 03 00 aa              mov      x9, x0
0x00079314: 3f 38 04 31              cmn      w1, #0x10e
0x00079318: a8 83 1e f8              stur     x8, [x29, #-0x18]
0x0007931c: e8 5f a8 52              mov      w8, #0x42ff0000
