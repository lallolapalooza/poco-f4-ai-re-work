; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_mediaeditor/work/x/lib/arm64-v8a/liblanguage_id_l2c_jni.so
; target  : range 0x3fc0c:0x40290
; start   : 0x3fc0c
; size    : 0x684
; bytes   : 0x684   instructions: 417
; generated: 2026-10-23T23:03:02

0x0003fc0c: ff c3 03 d1              sub      sp, sp, #0xf0
0x0003fc10: fd 7b 09 a9              stp      x29, x30, [sp, #0x90]
0x0003fc14: fc 6f 0a a9              stp      x28, x27, [sp, #0xa0]
0x0003fc18: fa 67 0b a9              stp      x26, x25, [sp, #0xb0]
0x0003fc1c: f8 5f 0c a9              stp      x24, x23, [sp, #0xc0]
0x0003fc20: f6 57 0d a9              stp      x22, x21, [sp, #0xd0]
0x0003fc24: f4 4f 0e a9              stp      x20, x19, [sp, #0xe0]
0x0003fc28: e2 03 1f 2a              mov      w2, wzr
0x0003fc2c: 37 10 40 f9              ldr      x23, [x1, #0x20]
0x0003fc30: f4 03 01 aa              mov      x20, x1
0x0003fc34: f3 03 00 aa              mov      x19, x0
0x0003fc38: 4c 42 01 94              bl       #0x90568
0x0003fc3c: 08 14 40 f9              ldr      x8, [x0, #0x28]
0x0003fc40: c8 06 00 b4              cbz      x8, #0x3fd18
0x0003fc44: e8 a6 43 a9              ldp      x8, x9, [x23, #0x38]
0x0003fc48: 1f 01 09 eb              cmp      x8, x9
0x0003fc4c: e0 06 00 54              b.eq     #0x3fd28
0x0003fc50: ea 2e 45 a9              ldp      x10, x11, [x23, #0x50]
0x0003fc54: 5f 01 0b eb              cmp      x10, x11
0x0003fc58: 00 07 00 54              b.eq     #0x3fd38
0x0003fc5c: 28 01 08 cb              sub      x8, x9, x8
0x0003fc60: 69 01 0a cb              sub      x9, x11, x10
0x0003fc64: 1f 01 09 eb              cmp      x8, x9
0x0003fc68: 01 07 00 54              b.ne     #0x3fd48
0x0003fc6c: e8 6a 40 b9              ldr      w8, [x23, #0x68]
0x0003fc70: 1f 01 00 71              cmp      w8, #0
0x0003fc74: 2d 07 00 54              b.le     #0x3fd58
0x0003fc78: e1 03 1f 2a              mov      w1, wzr
0x0003fc7c: 6a a7 01 94              bl       #0xa9a24
0x0003fc80: e8 b2 41 39              ldrb     w8, [x23, #0x6c]
0x0003fc84: f5 03 01 aa              mov      x21, x1
0x0003fc88: 08 07 00 34              cbz      w8, #0x3fd68
0x0003fc8c: e2 e3 01 91              add      x2, sp, #0x78
0x0003fc90: e1 03 15 2a              mov      w1, w21
0x0003fc94: ff ff 07 a9              stp      xzr, xzr, [sp, #0x78]
0x0003fc98: f6 e3 01 91              add      x22, sp, #0x78
0x0003fc9c: ff 47 00 f9              str      xzr, [sp, #0x88]
0x0003fca0: 24 02 00 94              bl       #0x40530
0x0003fca4: e8 e3 41 39              ldrb     w8, [sp, #0x78]
0x0003fca8: ca 02 40 b2              orr      x10, x22, #1
0x0003fcac: e9 47 40 f9              ldr      x9, [sp, #0x88]
0x0003fcb0: e1 03 15 2a              mov      w1, w21
0x0003fcb4: e2 6a 40 b9              ldr      w2, [x23, #0x68]
0x0003fcb8: 23 00 80 52              mov      w3, #1
0x0003fcbc: 1f 01 00 72              tst      w8, #1
0x0003fcc0: e8 23 01 91              add      x8, sp, #0x48
0x0003fcc4: 40 01 89 9a              csel     x0, x10, x9, eq
0x0003fcc8: f5 23 01 91              add      x21, sp, #0x48
0x0003fccc: 72 01 00 94              bl       #0x40294
0x0003fcd0: e8 02 40 39              ldrb     w8, [x23]
0x0003fcd4: 68 00 00 36              tbz      w8, #0, #0x3fce0
0x0003fcd8: e0 0a 40 f9              ldr      x0, [x23, #0x10]
0x0003fcdc: 27 9d 02 94              bl       #0xe7178
0x0003fce0: e8 2f 40 f9              ldr      x8, [sp, #0x58]
0x0003fce4: b5 62 00 91              add      x21, x21, #0x18
0x0003fce8: e0 83 c4 3c              ldur     q0, [sp, #0x48]
0x0003fcec: e0 0e 40 f9              ldr      x0, [x23, #0x18]
0x0003fcf0: e8 0a 00 f9              str      x8, [x23, #0x10]
0x0003fcf4: e0 02 80 3d              str      q0, [x23]
0x0003fcf8: ff 93 00 79              strh     wzr, [sp, #0x48]
0x0003fcfc: 60 06 00 b4              cbz      x0, #0x3fdc8
0x0003fd00: e0 12 00 f9              str      x0, [x23, #0x20]
0x0003fd04: 1d 9d 02 94              bl       #0xe7178
0x0003fd08: e8 23 41 39              ldrb     w8, [sp, #0x48]
0x0003fd0c: 1f 01 00 72              tst      w8, #1
0x0003fd10: e8 17 9f 1a              cset     w8, eq
0x0003fd14: 2e 00 00 14              b        #0x3fdcc
0x0003fd18: 68 16 40 f9              ldr      x8, [x19, #0x28]
0x0003fd1c: c1 fe ff 90              adrp     x1, #0x17000
0x0003fd20: 21 fc 00 91              add      x1, x1, #0x3f
0x0003fd24: 86 00 00 14              b        #0x3ff3c
0x0003fd28: 68 16 40 f9              ldr      x8, [x19, #0x28]
0x0003fd2c: c1 fe ff 90              adrp     x1, #0x17000
0x0003fd30: 21 bc 1c 91              add      x1, x1, #0x72f
0x0003fd34: 82 00 00 14              b        #0x3ff3c
0x0003fd38: 68 16 40 f9              ldr      x8, [x19, #0x28]
0x0003fd3c: a1 fe ff d0              adrp     x1, #0x15000
0x0003fd40: 21 00 2e 91              add      x1, x1, #0xb80
0x0003fd44: 7e 00 00 14              b        #0x3ff3c
0x0003fd48: 68 16 40 f9              ldr      x8, [x19, #0x28]
0x0003fd4c: a1 fe ff d0              adrp     x1, #0x15000
0x0003fd50: 21 84 2e 91              add      x1, x1, #0xba1
0x0003fd54: 7a 00 00 14              b        #0x3ff3c
0x0003fd58: 68 16 40 f9              ldr      x8, [x19, #0x28]
0x0003fd5c: a1 fe ff 90              adrp     x1, #0x13000
0x0003fd60: 21 c4 23 91              add      x1, x1, #0x8f1
0x0003fd64: 76 00 00 14              b        #0x3ff3c
0x0003fd68: e2 6a 40 b9              ldr      w2, [x23, #0x68]
0x0003fd6c: e8 23 01 91              add      x8, sp, #0x48
0x0003fd70: e1 03 15 2a              mov      w1, w21
0x0003fd74: 23 00 80 52              mov      w3, #1
0x0003fd78: f5 23 01 91              add      x21, sp, #0x48
0x0003fd7c: 46 01 00 94              bl       #0x40294
0x0003fd80: e8 02 40 39              ldrb     w8, [x23]
0x0003fd84: 68 00 00 36              tbz      w8, #0, #0x3fd90
0x0003fd88: e0 0a 40 f9              ldr      x0, [x23, #0x10]
0x0003fd8c: fb 9c 02 94              bl       #0xe7178
0x0003fd90: e8 2f 40 f9              ldr      x8, [sp, #0x58]
0x0003fd94: b5 62 00 91              add      x21, x21, #0x18
0x0003fd98: e0 83 c4 3c              ldur     q0, [sp, #0x48]
0x0003fd9c: e0 0e 40 f9              ldr      x0, [x23, #0x18]
0x0003fda0: e8 0a 00 f9              str      x8, [x23, #0x10]
0x0003fda4: e0 02 80 3d              str      q0, [x23]
0x0003fda8: ff 93 00 79              strh     wzr, [sp, #0x48]
0x0003fdac: e0 02 00 b4              cbz      x0, #0x3fe08
0x0003fdb0: e0 12 00 f9              str      x0, [x23, #0x20]
0x0003fdb4: f1 9c 02 94              bl       #0xe7178
0x0003fdb8: e8 23 41 39              ldrb     w8, [sp, #0x48]
0x0003fdbc: 1f 01 00 72              tst      w8, #1
0x0003fdc0: e8 17 9f 1a              cset     w8, eq
0x0003fdc4: 12 00 00 14              b        #0x3fe0c
0x0003fdc8: 28 00 80 52              mov      w8, #1
0x0003fdcc: e9 33 40 f9              ldr      x9, [sp, #0x60]
0x0003fdd0: e9 0e 00 f9              str      x9, [x23, #0x18]
0x0003fdd4: e9 37 40 f9              ldr      x9, [sp, #0x68]
0x0003fdd8: e9 12 00 f9              str      x9, [x23, #0x20]
0x0003fddc: e9 3b 40 f9              ldr      x9, [sp, #0x70]
0x0003fde0: bf 7e 00 a9              stp      xzr, xzr, [x21]
0x0003fde4: e9 16 00 f9              str      x9, [x23, #0x28]
0x0003fde8: bf 0a 00 f9              str      xzr, [x21, #0x10]
0x0003fdec: 68 00 00 37              tbnz     w8, #0, #0x3fdf8
0x0003fdf0: e0 2f 40 f9              ldr      x0, [sp, #0x58]
0x0003fdf4: e1 9c 02 94              bl       #0xe7178
0x0003fdf8: e8 e3 41 39              ldrb     w8, [sp, #0x78]
0x0003fdfc: e8 01 00 36              tbz      w8, #0, #0x3fe38
0x0003fe00: e0 47 40 f9              ldr      x0, [sp, #0x88]
0x0003fe04: 0c 00 00 14              b        #0x3fe34
0x0003fe08: 28 00 80 52              mov      w8, #1
0x0003fe0c: e9 33 40 f9              ldr      x9, [sp, #0x60]
0x0003fe10: e9 0e 00 f9              str      x9, [x23, #0x18]
0x0003fe14: e9 37 40 f9              ldr      x9, [sp, #0x68]
0x0003fe18: e9 12 00 f9              str      x9, [x23, #0x20]
0x0003fe1c: e9 3b 40 f9              ldr      x9, [sp, #0x70]
0x0003fe20: bf 7e 00 a9              stp      xzr, xzr, [x21]
0x0003fe24: e9 16 00 f9              str      x9, [x23, #0x28]
0x0003fe28: bf 0a 00 f9              str      xzr, [x21, #0x10]
0x0003fe2c: 68 00 00 37              tbnz     w8, #0, #0x3fe38
0x0003fe30: e0 2f 40 f9              ldr      x0, [sp, #0x58]
0x0003fe34: d1 9c 02 94              bl       #0xe7178
0x0003fe38: e0 03 13 aa              mov      x0, x19
0x0003fe3c: e1 03 14 aa              mov      x1, x20
0x0003fe40: e2 03 1f 2a              mov      w2, wzr
0x0003fe44: 03 42 01 94              bl       #0x90650
0x0003fe48: e0 05 00 b4              cbz      x0, #0x3ff04
0x0003fe4c: 08 20 40 b9              ldr      w8, [x0, #0x20]
0x0003fe50: f4 03 00 aa              mov      x20, x0
0x0003fe54: 1f 11 00 71              cmp      w8, #4
0x0003fe58: c1 06 00 54              b.ne     #0x3ff30
0x0003fe5c: 60 00 80 52              mov      w0, #3
0x0003fe60: d9 c3 01 94              bl       #0xb0dc4
0x0003fe64: 28 00 80 52              mov      w8, #1
0x0003fe68: e2 03 00 aa              mov      x2, x0
0x0003fe6c: e1 03 14 aa              mov      x1, x20
0x0003fe70: 08 04 00 b9              str      w8, [x0, #4]
0x0003fe74: e9 a2 43 a9              ldp      x9, x8, [x23, #0x38]
0x0003fe78: 08 01 09 cb              sub      x8, x8, x9
0x0003fe7c: 08 fd 42 d3              lsr      x8, x8, #2
0x0003fe80: 08 08 00 b9              str      w8, [x0, #8]
0x0003fe84: e9 a2 41 a9              ldp      x9, x8, [x23, #0x18]
0x0003fe88: 08 01 09 cb              sub      x8, x8, x9
0x0003fe8c: 08 fd 44 d3              lsr      x8, x8, #4
0x0003fe90: 08 0c 00 b9              str      w8, [x0, #0xc]
0x0003fe94: e0 03 13 aa              mov      x0, x19
0x0003fe98: 68 12 40 f9              ldr      x8, [x19, #0x20]
0x0003fe9c: 00 01 3f d6              blr      x8
0x0003fea0: 40 05 00 35              cbnz     w0, #0x3ff48
0x0003fea4: 88 02 40 b9              ldr      w8, [x20]
0x0003fea8: 1f 09 00 71              cmp      w8, #2
0x0003feac: e1 05 00 54              b.ne     #0x3ff68
0x0003feb0: f5 a2 43 a9              ldp      x21, x8, [x23, #0x38]
0x0003feb4: f9 e2 41 a9              ldp      x25, x24, [x23, #0x18]
0x0003feb8: 89 06 40 f9              ldr      x9, [x20, #8]
0x0003febc: ff 7f 05 a9              stp      xzr, xzr, [sp, #0x50]
0x0003fec0: 14 01 15 eb              subs     x20, x8, x21
0x0003fec4: e9 7f 04 a9              stp      x9, xzr, [sp, #0x40]
0x0003fec8: 80 05 00 54              b.eq     #0x3ff78
0x0003fecc: 74 1d f8 b7              tbnz     x20, #0x3f, #0x40278
0x0003fed0: e0 03 14 aa              mov      x0, x20
0x0003fed4: 93 fe 42 93              asr      x19, x20, #2
0x0003fed8: 8b 9c 02 94              bl       #0xe7104
0x0003fedc: 08 08 13 8b              add      x8, x0, x19, lsl #2
0x0003fee0: e1 03 15 aa              mov      x1, x21
0x0003fee4: e2 03 14 aa              mov      x2, x20
0x0003fee8: fc 03 00 aa              mov      x28, x0
0x0003feec: e0 27 00 f9              str      x0, [sp, #0x48]
0x0003fef0: e8 2f 00 f9              str      x8, [sp, #0x58]
0x0003fef4: cb c5 02 94              bl       #0xf1620                   ; -> memcpy@plt
0x0003fef8: 93 03 14 8b              add      x19, x28, x20
0x0003fefc: f3 2b 00 f9              str      x19, [sp, #0x50]
0x0003ff00: 20 00 00 14              b        #0x3ff80
0x0003ff04: 81 fe ff d0              adrp     x1, #0x11000
0x0003ff08: c2 fe ff 90              adrp     x2, #0x17000
0x0003ff0c: c4 fe ff 90              adrp     x4, #0x17000
0x0003ff10: 68 16 40 f9              ldr      x8, [x19, #0x28]
0x0003ff14: 21 e4 12 91              add      x1, x1, #0x4b9
0x0003ff18: 42 08 00 91              add      x2, x2, #2
0x0003ff1c: 84 04 1c 91              add      x4, x4, #0x701
0x0003ff20: e0 03 13 aa              mov      x0, x19
0x0003ff24: 63 1b 80 52              mov      w3, #0xdb
0x0003ff28: 00 01 3f d6              blr      x8
0x0003ff2c: 06 00 00 14              b        #0x3ff44
0x0003ff30: 68 16 40 f9              ldr      x8, [x19, #0x28]
0x0003ff34: a1 fe ff b0              adrp     x1, #0x14000
0x0003ff38: 21 08 3e 91              add      x1, x1, #0xf82
0x0003ff3c: e0 03 13 aa              mov      x0, x19
0x0003ff40: 00 01 3f d6              blr      x8
0x0003ff44: 20 00 80 52              mov      w0, #1
0x0003ff48: f4 4f 4e a9              ldp      x20, x19, [sp, #0xe0]
0x0003ff4c: f6 57 4d a9              ldp      x22, x21, [sp, #0xd0]
0x0003ff50: f8 5f 4c a9              ldp      x24, x23, [sp, #0xc0]
0x0003ff54: fa 67 4b a9              ldp      x26, x25, [sp, #0xb0]
0x0003ff58: fc 6f 4a a9              ldp      x28, x27, [sp, #0xa0]
0x0003ff5c: fd 7b 49 a9              ldp      x29, x30, [sp, #0x90]
0x0003ff60: ff c3 03 91              add      sp, sp, #0xf0
0x0003ff64: c0 03 5f d6              ret      
0x0003ff68: a1 fe ff 90              adrp     x1, #0x13000
0x0003ff6c: 68 16 40 f9              ldr      x8, [x19, #0x28]
0x0003ff70: 21 58 23 91              add      x1, x1, #0x8d6
0x0003ff74: f2 ff ff 17              b        #0x3ff3c
0x0003ff78: fc 03 1f aa              mov      x28, xzr
0x0003ff7c: f3 03 1f aa              mov      x19, xzr
0x0003ff80: f6 22 45 a9              ldp      x22, x8, [x23, #0x50]
0x0003ff84: ff ff 07 a9              stp      xzr, xzr, [sp, #0x78]
0x0003ff88: ff 47 00 f9              str      xzr, [sp, #0x88]
0x0003ff8c: 15 01 16 eb              subs     x21, x8, x22
0x0003ff90: e0 01 00 54              b.eq     #0x3ffcc
0x0003ff94: 75 17 f8 b7              tbnz     x21, #0x3f, #0x40280
0x0003ff98: e0 03 15 aa              mov      x0, x21
0x0003ff9c: b4 fe 42 93              asr      x20, x21, #2
0x0003ffa0: 59 9c 02 94              bl       #0xe7104
0x0003ffa4: 08 08 14 8b              add      x8, x0, x20, lsl #2
0x0003ffa8: e1 03 16 aa              mov      x1, x22
0x0003ffac: e2 03 15 aa              mov      x2, x21
0x0003ffb0: fb 03 00 aa              mov      x27, x0
0x0003ffb4: e0 3f 00 f9              str      x0, [sp, #0x78]
0x0003ffb8: e8 47 00 f9              str      x8, [sp, #0x88]
0x0003ffbc: 99 c5 02 94              bl       #0xf1620                   ; -> memcpy@plt
0x0003ffc0: 68 03 15 8b              add      x8, x27, x21
0x0003ffc4: e8 43 00 f9              str      x8, [sp, #0x80]
0x0003ffc8: 02 00 00 14              b        #0x3ffd0
0x0003ffcc: fb 03 1f aa              mov      x27, xzr
0x0003ffd0: 68 02 1c eb              subs     x8, x19, x28
0x0003ffd4: 20 14 00 54              b.eq     #0x40258
0x0003ffd8: 09 03 19 cb              sub      x9, x24, x25
0x0003ffdc: 08 fd 42 93              asr      x8, x8, #2
0x0003ffe0: 1f 05 00 f1              cmp      x8, #1
0x0003ffe4: 29 8d 44 93              sbfx     x9, x9, #4, #0x20
0x0003ffe8: 0b 85 9f 9a              csinc    x11, x8, xzr, hi
0x0003ffec: 28 f5 7e d3              lsl      x8, x9, #2
0x0003fff0: ea 03 1f aa              mov      x10, xzr
0x0003fff4: f5 1a 40 f9              ldr      x21, [x23, #0x30]
0x0003fff8: fa 06 00 91              add      x26, x23, #1
0x0003fffc: 33 00 c0 d2              mov      x19, #0x100000000
0x00040000: e8 2f 03 a9              stp      x8, x11, [sp, #0x30]
0x00040004: eb 23 40 f9              ldr      x11, [sp, #0x40]
0x00040008: e9 f3 00 a9              stp      x9, x28, [sp, #8]
0x0004000c: fb 0f 00 f9              str      x27, [sp, #0x18]
0x00040010: 09 00 00 14              b        #0x40034
0x00040014: fc 6f 41 a9              ldp      x28, x27, [sp, #0x10]
0x00040018: eb 2b 42 a9              ldp      x11, x10, [sp, #0x20]
0x0004001c: e8 1b 40 f9              ldr      x8, [sp, #0x30]
0x00040020: 4a 05 00 91              add      x10, x10, #1
0x00040024: 6b 01 08 8b              add      x11, x11, x8
0x00040028: e8 1f 40 f9              ldr      x8, [sp, #0x38]
0x0004002c: 5f 01 08 eb              cmp      x10, x8
0x00040030: 40 11 00 54              b.eq     #0x40258
0x00040034: 08 03 19 eb              subs     x8, x24, x25
0x00040038: 20 ff ff 54              b.eq     #0x4001c
0x0004003c: 49 f5 7e d3              lsl      x9, x10, #2
0x00040040: 9d 6b a9 b8              ldrsw    x29, [x28, x9]
0x00040044: 74 6b a9 b8              ldrsw    x20, [x27, x9]
0x00040048: bf 03 00 71              cmp      w29, #0
0x0004004c: 0d 0d 00 54              b.le     #0x401ec
0x00040050: e9 07 40 f9              ldr      x9, [sp, #8]
0x00040054: fb 03 1f aa              mov      x27, xzr
0x00040058: 08 fd 44 93              asr      x8, x8, #4
0x0004005c: 5c 00 80 52              mov      w28, #2
0x00040060: eb 2b 02 a9              stp      x11, x10, [sp, #0x20]
0x00040064: 56 7d 09 9b              mul      x22, x10, x9
0x00040068: 19 00 00 14              b        #0x400cc
0x0004006c: ea 03 1f 2a              mov      w10, wzr
0x00040070: 68 ef 7c d3              lsl      x8, x27, #4
0x00040074: e9 02 40 39              ldrb     w9, [x23]
0x00040078: eb 0a 40 f9              ldr      x11, [x23, #0x10]
0x0004007c: 41 7d 40 93              sxtw     x1, w10
0x00040080: e2 03 15 aa              mov      x2, x21
0x00040084: 28 6b 68 f8              ldr      x8, [x25, x8]
0x00040088: 3f 01 00 72              tst      w9, #1
0x0004008c: 49 03 8b 9a              csel     x9, x26, x11, eq
0x00040090: 20 01 08 8b              add      x0, x9, x8
0x00040094: cd 01 00 94              bl       #0x407c8
0x00040098: 09 08 d4 9a              udiv     x9, x0, x20
0x0004009c: 68 03 16 8b              add      x8, x27, x22
0x000400a0: ea 23 40 f9              ldr      x10, [sp, #0x40]
0x000400a4: 7b 07 00 91              add      x27, x27, #1
0x000400a8: 9c 07 00 91              add      x28, x28, #1
0x000400ac: 29 81 14 1b              msub     w9, w9, w20, w0
0x000400b0: 29 05 00 11              add      w9, w9, #1
0x000400b4: 49 79 28 b8              str      w9, [x10, x8, lsl #2]
0x000400b8: f9 e2 41 a9              ldp      x25, x24, [x23, #0x18]
0x000400bc: 08 03 19 cb              sub      x8, x24, x25
0x000400c0: 08 fd 44 93              asr      x8, x8, #4
0x000400c4: 1f 01 1b eb              cmp      x8, x27
0x000400c8: 69 fa ff 54              b.ls     #0x40014
0x000400cc: 6c 07 00 11              add      w12, w27, #1
0x000400d0: 69 03 1d 8b              add      x9, x27, x29
0x000400d4: 8d 7d 40 93              sxtw     x13, w12
0x000400d8: 3f 01 0d eb              cmp      x9, x13
0x000400dc: 2e c1 8d 9a              csel     x14, x9, x13, gt
0x000400e0: 1f 01 1b eb              cmp      x8, x27
0x000400e4: 49 fc ff 54              b.ls     #0x4006c
0x000400e8: 3f 01 0d eb              cmp      x9, x13
0x000400ec: ea 03 3b aa              mvn      x10, x27
0x000400f0: 2b c1 8d 9a              csel     x11, x9, x13, gt
0x000400f4: 0f 01 0a 8b              add      x15, x8, x10
0x000400f8: 6b 01 0d cb              sub      x11, x11, x13
0x000400fc: 7f 01 0f eb              cmp      x11, x15
0x00040100: 6a 31 8f 9a              csel     x10, x11, x15, lo
0x00040104: ff 01 00 f1              cmp      x15, #0
0x00040108: 2b 00 80 52              mov      w11, #1
0x0004010c: 6b 05 8a 9a              csinc    x11, x11, x10, eq
0x00040110: 7f 11 00 f1              cmp      x11, #4
0x00040114: 62 00 00 54              b.hs     #0x40120
0x00040118: ea 03 1f 2a              mov      w10, wzr
0x0004011c: 25 00 00 14              b        #0x401b0
0x00040120: cd 01 0d cb              sub      x13, x14, x13
0x00040124: ea 03 1f 2a              mov      w10, wzr
0x00040128: bf 01 0f eb              cmp      x13, x15
0x0004012c: ad 31 8f 9a              csel     x13, x13, x15, lo
0x00040130: ff 01 00 f1              cmp      x15, #0
0x00040134: ed 03 8d 9a              csel     x13, xzr, x13, eq
0x00040138: 8e 01 0d 0b              add      w14, w12, w13
0x0004013c: df 01 0c 6b              cmp      w14, w12
0x00040140: 8b 03 00 54              b.lt     #0x401b0
0x00040144: ad fd 60 d3              lsr      x13, x13, #0x20
0x00040148: ec 03 1b aa              mov      x12, x27
0x0004014c: 4d 03 00 b5              cbnz     x13, #0x401b4
0x00040150: 6d f5 7e 92              and      x13, x11, #0xfffffffffffffffc
0x00040154: 2a 13 1c 8b              add      x10, x25, x28, lsl #4
0x00040158: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x0004015c: 6c 03 0d 8b              add      x12, x27, x13
0x00040160: 01 e4 00 6f              movi     v1.2d, #0000000000000000
0x00040164: 4a 21 00 91              add      x10, x10, #8
0x00040168: ee 03 0d aa              mov      x14, x13
0x0004016c: 4f 01 5e b8              ldur     w15, [x10, #-0x20]
0x00040170: 50 41 00 d1              sub      x16, x10, #0x10
0x00040174: ce 11 00 f1              subs     x14, x14, #4
0x00040178: e2 01 27 1e              fmov     s2, w15
0x0004017c: 4f 41 00 91              add      x15, x10, #0x10
0x00040180: 43 05 44 bc              ldr      s3, [x10], #0x40
0x00040184: 02 92 40 0d              ld1      {v2.s}[1], [x16]
0x00040188: e3 91 40 0d              ld1      {v3.s}[1], [x15]
0x0004018c: 00 84 a2 0e              add      v0.2s, v0.2s, v2.2s
0x00040190: 21 84 a3 0e              add      v1.2s, v1.2s, v3.2s
0x00040194: c1 fe ff 54              b.ne     #0x4016c
0x00040198: 20 84 a0 0e              add      v0.2s, v1.2s, v0.2s
0x0004019c: 7f 01 0d eb              cmp      x11, x13
0x000401a0: 00 bc a0 0e              addp     v0.2s, v0.2s, v0.2s
0x000401a4: 0a 00 26 1e              fmov     w10, s0
0x000401a8: 40 f6 ff 54              b.eq     #0x40070
0x000401ac: 02 00 00 14              b        #0x401b4
0x000401b0: ec 03 1b aa              mov      x12, x27
0x000401b4: 2d 13 0c 8b              add      x13, x25, x12, lsl #4
0x000401b8: 6b 82 0c 8b              add      x11, x19, x12, lsl #32
0x000401bc: 8c 05 00 91              add      x12, x12, #1
0x000401c0: ad 21 00 91              add      x13, x13, #8
0x000401c4: ae 05 41 b8              ldr      w14, [x13], #0x10
0x000401c8: 1f 01 0c eb              cmp      x8, x12
0x000401cc: 4a 01 0e 0b              add      w10, w10, w14
0x000401d0: 09 f5 ff 54              b.ls     #0x40070
0x000401d4: 6e fd 60 93              asr      x14, x11, #0x20
0x000401d8: 6b 01 13 8b              add      x11, x11, x19
0x000401dc: 8c 05 00 91              add      x12, x12, #1
0x000401e0: df 01 09 eb              cmp      x14, x9
0x000401e4: 0b ff ff 54              b.lt     #0x401c4
0x000401e8: a2 ff ff 17              b        #0x40070
0x000401ec: fd 03 0a aa              mov      x29, x10
0x000401f0: f6 03 1f aa              mov      x22, xzr
0x000401f4: fb 03 1f aa              mov      x27, xzr
0x000401f8: e8 02 40 39              ldrb     w8, [x23]
0x000401fc: e1 03 1f aa              mov      x1, xzr
0x00040200: e9 0a 40 f9              ldr      x9, [x23, #0x10]
0x00040204: e2 03 15 aa              mov      x2, x21
0x00040208: 2a 6b 76 f8              ldr      x10, [x25, x22]
0x0004020c: f8 03 0b aa              mov      x24, x11
0x00040210: 1f 01 00 72              tst      w8, #1
0x00040214: 48 03 89 9a              csel     x8, x26, x9, eq
0x00040218: 00 01 0a 8b              add      x0, x8, x10
0x0004021c: 6b 01 00 94              bl       #0x407c8
0x00040220: 08 08 d4 9a              udiv     x8, x0, x20
0x00040224: eb 03 18 aa              mov      x11, x24
0x00040228: d6 42 00 91              add      x22, x22, #0x10
0x0004022c: 08 81 14 1b              msub     w8, w8, w20, w0
0x00040230: 08 05 00 11              add      w8, w8, #1
0x00040234: 08 7b 3b b8              str      w8, [x24, x27, lsl #2]
0x00040238: 7b 07 00 91              add      x27, x27, #1
0x0004023c: f9 e2 41 a9              ldp      x25, x24, [x23, #0x18]
0x00040240: 08 03 19 cb              sub      x8, x24, x25
0x00040244: 7f 13 88 eb              cmp      x27, x8, asr #4
0x00040248: 83 fd ff 54              b.lo     #0x401f8
0x0004024c: fb 0f 40 f9              ldr      x27, [sp, #0x18]
0x00040250: ea 03 1d aa              mov      x10, x29
0x00040254: 72 ff ff 17              b        #0x4001c
0x00040258: 7b 00 00 b4              cbz      x27, #0x40264
0x0004025c: e0 03 1b aa              mov      x0, x27
0x00040260: c6 9b 02 94              bl       #0xe7178
0x00040264: 7c 00 00 b4              cbz      x28, #0x40270
0x00040268: e0 03 1c aa              mov      x0, x28
0x0004026c: c3 9b 02 94              bl       #0xe7178
0x00040270: e0 03 1f 2a              mov      w0, wzr
0x00040274: 35 ff ff 17              b        #0x3ff48
0x00040278: e0 23 01 91              add      x0, sp, #0x48
0x0004027c: cb a6 02 94              bl       #0xe9da8
0x00040280: e0 e3 01 91              add      x0, sp, #0x78
0x00040284: c9 a6 02 94              bl       #0xe9da8
0x00040288: 1f 20 03 d5              nop      
0x0004028c: e0 5b 5e 10              adr      x0, #0xfce08
