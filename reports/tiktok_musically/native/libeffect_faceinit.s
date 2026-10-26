; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/ttlibs/libeffect.so
; target  : address 0x68a180
; start   : 0x68a180
; size    : 0x400
; bytes   : 0x400   instructions: 256
; generated: 2026-10-23T23:54:55

0x0068a180: f4 4f 18 a9              stp      x20, x19, [sp, #0x180]
0x0068a184: fd 7b 19 a9              stp      x29, x30, [sp, #0x190]
0x0068a188: fd 43 06 91              add      x29, sp, #0x190
0x0068a18c: 57 d0 3b d5              mrs      x23, tpidr_el0
0x0068a190: e8 16 40 f9              ldr      x8, [x23, #0x28]
0x0068a194: f3 03 02 aa              mov      x19, x2
0x0068a198: 44 3f 10 94              bl       #0xa99ea8
0x0068a19c: 82 00 80 52              mov      w2, #4
0x0068a1a0: d1 01 11 94              bl       #0xaca8e4
0x0068a1a4: d6 b9 f7 97              bl       #0x4788fc
0x0068a1a8: e8 4b 40 f9              ldr      x8, [sp, #0x90]
0x0068a1ac: a8 02 00 b4              cbz      x8, #0x68a200
0x0068a1b0: e0 23 00 91              add      x0, sp, #8
0x0068a1b4: 41 00 80 52              mov      w1, #2
0x0068a1b8: 20 00 00 94              bl       #0x68a238
0x0068a1bc: e0 23 00 91              add      x0, sp, #8
0x0068a1c0: 3a 00 00 94              bl       #0x68a2a8
0x0068a1c4: 42 86 10 94              bl       #0xaabacc
0x0068a1c8: e1 03 1f 2a              mov      w1, wzr
0x0068a1cc: 1b 00 00 94              bl       #0x68a238
0x0068a1d0: 12 13 11 94              bl       #0xacee18
0x0068a1d4: f6 03 00 aa              mov      x22, x0
0x0068a1d8: a2 7e 40 93              sxtw     x2, w21
0x0068a1dc: aa 44 10 94              bl       #0xa9b484
0x0068a1e0: 48 00 00 94              bl       #0x68a300
0x0068a1e4: db 13 10 94              bl       #0xa8f150
0x0068a1e8: e2 03 15 2a              mov      w2, w21
0x0068a1ec: e3 03 13 aa              mov      x3, x19
0x0068a1f0: 61 00 00 94              bl       #0x68a374
0x0068a1f4: f3 03 00 2a              mov      w19, w0
0x0068a1f8: 14 3f 10 94              bl       #0xa99e48
0x0068a1fc: 03 00 00 14              b        #0x68a208
0x0068a200: fa f1 10 94              bl       #0xac69e8
0x0068a204: 93 00 80 12              mov      w19, #-5
0x0068a208: e0 23 00 91              add      x0, sp, #8
0x0068a20c: 48 ba f7 97              bl       #0x478b2c
0x0068a210: e8 16 40 f9              ldr      x8, [x23, #0x28]
0x0068a214: 26 10 10 94              bl       #0xa8e2ac
0x0068a218: e1 00 00 54              b.ne     #0x68a234
0x0068a21c: e0 03 13 2a              mov      w0, w19
0x0068a220: fd 7b 59 a9              ldp      x29, x30, [sp, #0x190]
0x0068a224: f4 4f 58 a9              ldp      x20, x19, [sp, #0x180]
0x0068a228: f6 57 57 a9              ldp      x22, x21, [sp, #0x170]
0x0068a22c: fc 5f 56 a9              ldp      x28, x23, [sp, #0x160]
0x0068a230: 0f 3a 10 14              b        #0xa98a6c
0x0068a234: 7f ec f0 97              bl       #0x2c5430                  ; -> __stack_chk_fail@plt
0x0068a238: f5 0f 1d f8              str      x21, [sp, #-0x30]!
0x0068a23c: f4 4f 01 a9              stp      x20, x19, [sp, #0x10]
0x0068a240: fd 7b 02 a9              stp      x29, x30, [sp, #0x20]
0x0068a244: fd 83 00 91              add      x29, sp, #0x20
0x0068a248: 31 47 10 94              bl       #0xa9bf0c
0x0068a24c: f5 03 01 2a              mov      w21, w1
0x0068a250: 08 81 5e f8              ldur     x8, [x8, #-0x18]
0x0068a254: 00 00 08 8b              add      x0, x0, x8
0x0068a258: 08 20 40 b9              ldr      w8, [x0, #0x20]
0x0068a25c: 14 79 1e 12              and      w20, w8, #0xfffffffd
0x0068a260: e1 03 14 2a              mov      w1, w20
0x0068a264: db ef f0 97              bl       #0x2c61d0                  ; -> _ZNSt6__ndk18ios_base5clearEj@plt
0x0068a268: a1 b4 10 94              bl       #0xab74ec
0x0068a26c: e8 23 40 39              ldrb     w8, [sp, #8]
0x0068a270: 68 01 00 34              cbz      w8, #0x68a29c
0x0068a274: 68 02 40 f9              ldr      x8, [x19]
0x0068a278: 03 01 80 52              mov      w3, #8
0x0068a27c: e1 03 1f aa              mov      x1, xzr
0x0068a280: e2 03 15 2a              mov      w2, w21
0x0068a284: 64 1d 11 94              bl       #0xad1814
0x0068a288: 68 02 40 f9              ldr      x8, [x19]
0x0068a28c: 89 02 1e 32              orr      w9, w20, #4
0x0068a290: 3f 04 00 b1              cmn      x1, #1
0x0068a294: 21 01 94 1a              csel     w1, w9, w20, eq
0x0068a298: 1b 28 10 94              bl       #0xa94304
0x0068a29c: e0 03 13 aa              mov      x0, x19
0x0068a2a0: fd 7b 42 a9              ldp      x29, x30, [sp, #0x20]
0x0068a2a4: 5e 06 10 14              b        #0xa8bc1c
0x0068a2a8: ff c3 00 d1              sub      sp, sp, #0x30
0x0068a2ac: f4 4f 01 a9              stp      x20, x19, [sp, #0x10]
0x0068a2b0: fd 7b 02 a9              stp      x29, x30, [sp, #0x20]
0x0068a2b4: fd 83 00 91              add      x29, sp, #0x20
0x0068a2b8: f3 03 00 aa              mov      x19, x0
0x0068a2bc: 8c b4 10 94              bl       #0xab74ec
0x0068a2c0: e8 23 40 39              ldrb     w8, [sp, #8]
0x0068a2c4: 48 01 00 34              cbz      w8, #0x68a2ec
0x0068a2c8: 68 02 40 f9              ldr      x8, [x19]
0x0068a2cc: 22 00 80 52              mov      w2, #1
0x0068a2d0: 03 01 80 52              mov      w3, #8
0x0068a2d4: e1 03 1f aa              mov      x1, xzr
0x0068a2d8: 4f 1d 11 94              bl       #0xad1814
0x0068a2dc: 68 02 40 f9              ldr      x8, [x19]
0x0068a2e0: 8d 2f 11 94              bl       #0xad6114
0x0068a2e4: 08 28 10 94              bl       #0xa94304
0x0068a2e8: 02 00 00 14              b        #0x68a2f0
0x0068a2ec: 14 00 80 92              mov      x20, #-1
0x0068a2f0: e0 03 14 aa              mov      x0, x20
0x0068a2f4: fd 7b 42 a9              ldp      x29, x30, [sp, #0x20]
0x0068a2f8: f4 4f 41 a9              ldp      x20, x19, [sp, #0x10]
0x0068a2fc: 25 06 10 14              b        #0xa8bb90
0x0068a300: f5 0f 1d f8              str      x21, [sp, #-0x30]!
0x0068a304: f4 4f 01 a9              stp      x20, x19, [sp, #0x10]
0x0068a308: fd 7b 02 a9              stp      x29, x30, [sp, #0x20]
0x0068a30c: fd 83 00 91              add      x29, sp, #0x20
0x0068a310: f3 03 00 aa              mov      x19, x0
0x0068a314: c0 40 10 94              bl       #0xa9a614
0x0068a318: 1f 04 00 f9              str      xzr, [x0, #8]
0x0068a31c: 74 b4 10 94              bl       #0xab74ec
0x0068a320: e8 23 40 39              ldrb     w8, [sp, #8]
0x0068a324: c8 01 00 34              cbz      w8, #0x68a35c
0x0068a328: 68 02 40 f9              ldr      x8, [x19]
0x0068a32c: e1 03 15 aa              mov      x1, x21
0x0068a330: e2 03 14 aa              mov      x2, x20
0x0068a334: 08 81 5e f8              ldur     x8, [x8, #-0x18]
0x0068a338: 68 02 08 8b              add      x8, x19, x8
0x0068a33c: 00 15 40 f9              ldr      x0, [x8, #0x28]
0x0068a340: 3c dc 10 94              bl       #0xac1430
0x0068a344: 00 01 3f d6              blr      x8
0x0068a348: 1f 00 14 eb              cmp      x0, x20
0x0068a34c: c8 00 80 52              mov      w8, #6
0x0068a350: e1 03 88 1a              csel     w1, wzr, w8, eq
0x0068a354: 60 06 00 f9              str      x0, [x19, #8]
0x0068a358: 02 00 00 14              b        #0x68a360
0x0068a35c: 81 00 80 52              mov      w1, #4
0x0068a360: 68 02 40 f9              ldr      x8, [x19]
0x0068a364: e8 27 10 94              bl       #0xa94304
0x0068a368: e0 03 13 aa              mov      x0, x19
0x0068a36c: fd 7b 42 a9              ldp      x29, x30, [sp, #0x20]
0x0068a370: 2b 06 10 14              b        #0xa8bc1c
0x0068a374: ec 0f 17 fc              str      d12, [sp, #-0x90]!
0x0068a378: eb 2b 01 6d              stp      d11, d10, [sp, #0x10]
0x0068a37c: e9 23 02 6d              stp      d9, d8, [sp, #0x20]
0x0068a380: e4 03 1e aa              mov      x4, x30
0x0068a384: a5 0a 10 94              bl       #0xa8ce18
0x0068a388: fe 03 04 aa              mov      x30, x4
0x0068a38c: fd 7b 08 a9              stp      x29, x30, [sp, #0x80]
0x0068a390: fd 03 02 91              add      x29, sp, #0x80
0x0068a394: ff 03 10 d1              sub      sp, sp, #0x400
0x0068a398: 5a d0 3b d5              mrs      x26, tpidr_el0
0x0068a39c: 48 17 40 f9              ldr      x8, [x26, #0x28]
0x0068a3a0: f8 03 03 aa              mov      x24, x3
0x0068a3a4: f6 03 02 2a              mov      w22, w2
0x0068a3a8: f7 03 01 aa              mov      x23, x1
0x0068a3ac: c1 38 00 d0              adrp     x1, #0xda4000
0x0068a3b0: c2 36 00 f0              adrp     x2, #0xd65000
0x0068a3b4: c3 36 00 f0              adrp     x3, #0xd65000
0x0068a3b8: f3 03 00 aa              mov      x19, x0
0x0068a3bc: 21 7c 1f 91              add      x1, x1, #0x7df
0x0068a3c0: 42 4c 36 91              add      x2, x2, #0xd93
0x0068a3c4: 63 dc 36 91              add      x3, x3, #0xdb7
0x0068a3c8: c0 00 80 52              mov      w0, #6
0x0068a3cc: a8 03 17 f8              stur     x8, [x29, #-0x90]
0x0068a3d0: 7c ec f0 97              bl       #0x2c55c0                  ; -> __android_log_print@plt
0x0068a3d4: 21 30 10 94              bl       #0xa96458
0x0068a3d8: 00 a8 80 52              mov      w0, #0x540
0x0068a3dc: c9 ef f0 97              bl       #0x2c6300                  ; -> _ZnwmRKSt9nothrow_t@plt
0x0068a3e0: a0 14 00 b4              cbz      x0, #0x68a674
0x0068a3e4: 02 1b 80 52              mov      w2, #0xd8
0x0068a3e8: 1a c7 10 94              bl       #0xabc050
0x0068a3ec: 19 30 05 91              add      x25, x0, #0x14c
0x0068a3f0: 15 60 03 91              add      x21, x0, #0xd8
0x0068a3f4: 7b ec f0 97              bl       #0x2c55e0                  ; -> memset@plt
0x0068a3f8: c1 36 00 f0              adrp     x1, #0xd65000
0x0068a3fc: 21 2c 37 91              add      x1, x1, #0xdcb
0x0068a400: ab b4 10 94              bl       #0xab76ac
0x0068a404: c1 36 00 f0              adrp     x1, #0xd65000
0x0068a408: 80 c2 03 91              add      x0, x20, #0xf0
0x0068a40c: 21 60 37 91              add      x1, x1, #0xdd8
0x0068a410: 12 7c f1 97              bl       #0x2e9458
0x0068a414: 48 30 00 b0              adrp     x8, #0xc93000
0x0068a418: 00 09 42 fd              ldr      d0, [x8, #0x410]
0x0068a41c: 48 30 00 b0              adrp     x8, #0xc93000
0x0068a420: 01 0d 42 fd              ldr      d1, [x8, #0x418]
0x0068a424: 88 62 04 91              add      x8, x20, #0x118
0x0068a428: 02 e4 00 6f              movi     v2.2d, #0000000000000000
0x0068a42c: 80 86 00 fd              str      d0, [x20, #0x108]
0x0068a430: 02 01 80 3d              str      q2, [x8]
0x0068a434: 08 00 80 92              mov      x8, #-1
0x0068a438: 88 a2 12 a9              stp      x8, x8, [x20, #0x128]
0x0068a43c: 88 3a 01 b9              str      w8, [x20, #0x138]
0x0068a440: 08 10 80 52              mov      w8, #0x80
0x0068a444: 80 f4 01 0f              fmov     v0.2s, #10.00000000
0x0068a448: 80 c2 05 91              add      x0, x20, #0x170
0x0068a44c: 9f 8a 00 f9              str      xzr, [x20, #0x110]
0x0068a450: 9f a2 00 f9              str      xzr, [x20, #0x140]
0x0068a454: 88 4a 01 b9              str      w8, [x20, #0x148]
0x0068a458: 20 03 00 fd              str      d0, [x25]
0x0068a45c: 9f ae 00 f9              str      xzr, [x20, #0x158]
0x0068a460: 81 b2 00 fd              str      d1, [x20, #0x160]
0x0068a464: 9f a2 05 39              strb     wzr, [x20, #0x168]
0x0068a468: ae 06 13 94              bl       #0xb4bf20
0x0068a46c: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x0068a470: 01 e4 00 6f              movi     v1.2d, #0000000000000000
0x0068a474: 80 a2 09 91              add      x0, x20, #0x268
0x0068a478: 9f f2 00 f9              str      xzr, [x20, #0x1e0]
0x0068a47c: 80 76 80 3d              str      q0, [x20, #0x1d0]
0x0068a480: 21 57 00 fd              str      d1, [x25, #0xa8]
0x0068a484: 9f 32 01 f9              str      xzr, [x20, #0x260]
0x0068a488: 80 02 12 ad              stp      q0, q0, [x20, #0x240]
0x0068a48c: 80 02 11 ad              stp      q0, q0, [x20, #0x220]
0x0068a490: 80 02 10 ad              stp      q0, q0, [x20, #0x200]
0x0068a494: a3 06 13 94              bl       #0xb4bf20
0x0068a498: 80 22 0b 91              add      x0, x20, #0x2c8
0x0068a49c: a1 06 13 94              bl       #0xb4bf20
0x0068a4a0: 80 c2 0c 91              add      x0, x20, #0x330
0x0068a4a4: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x0068a4a8: 02 2d 80 52              mov      w2, #0x168
0x0068a4ac: e1 03 1f 2a              mov      w1, wzr
0x0068a4b0: 9f 62 02 f9              str      xzr, [x20, #0x4c0]
0x0068a4b4: 80 2e 81 3d              str      q0, [x20, #0x4b0]
0x0068a4b8: 95 42 14 91              add      x21, x20, #0x510
0x0068a4bc: 49 ec f0 97              bl       #0x2c55e0                  ; -> memset@plt
0x0068a4c0: 48 30 00 b0              adrp     x8, #0xc93000
0x0068a4c4: 00 11 42 fd              ldr      d0, [x8, #0x420]
0x0068a4c8: 01 e4 00 6f              movi     v1.2d, #0000000000000000
0x0068a4cc: 81 4a 81 3d              str      q1, [x20, #0x520]
0x0068a4d0: a1 e2 81 3c              stur     q1, [x21, #0x1e]
0x0068a4d4: 80 f6 00 fd              str      d0, [x20, #0x1e8]
0x0068a4d8: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x0068a4dc: 81 46 81 3d              str      q1, [x20, #0x510]
0x0068a4e0: 80 fa 00 fd              str      d0, [x20, #0x1f0]
0x0068a4e4: 14 03 00 f9              str      x20, [x24]
0x0068a4e8: b7 0c 00 b4              cbz      x23, #0x68a67c
0x0068a4ec: c8 0e 80 52              mov      w8, #0x76
0x0068a4f0: eb 08 80 52              mov      w11, #0x47
0x0068a4f4: 2f 07 80 52              mov      w15, #0x39
0x0068a4f8: eb 2b 01 b9              str      w11, [sp, #0x128]
0x0068a4fc: 4b 0f 80 52              mov      w11, #0x7a
0x0068a500: e8 43 01 b9              str      w8, [sp, #0x140]
0x0068a504: e8 0b 01 b9              str      w8, [sp, #0x108]
0x0068a508: 68 0d 80 52              mov      w8, #0x6b
0x0068a50c: e9 0d 80 52              mov      w9, #0x6f
0x0068a510: ef 03 01 b9              str      w15, [sp, #0x100]
0x0068a514: ef 0c 80 52              mov      w15, #0x67
0x0068a518: e8 eb 00 b9              str      w8, [sp, #0xe8]
0x0068a51c: 08 09 80 52              mov      w8, #0x48
0x0068a520: eb fb 00 b9              str      w11, [sp, #0xf8]
0x0068a524: eb d3 00 b9              str      w11, [sp, #0xd0]
0x0068a528: eb 06 80 52              mov      w11, #0x37
0x0068a52c: 2a 09 80 52              mov      w10, #0x49
0x0068a530: ed 0a 80 52              mov      w13, #0x57
0x0068a534: ef e3 00 b9              str      w15, [sp, #0xe0]
0x0068a538: 0f 0d 80 52              mov      w15, #0x68
0x0068a53c: e9 3b 01 b9              str      w9, [sp, #0x138]
0x0068a540: e9 cb 00 b9              str      w9, [sp, #0xc8]
0x0068a544: 89 06 80 52              mov      w9, #0x34
0x0068a548: e8 db 00 b9              str      w8, [sp, #0xd8]
0x0068a54c: e8 bb 00 b9              str      w8, [sp, #0xb8]
0x0068a550: 08 06 80 52              mov      w8, #0x30
0x0068a554: eb b3 00 b9              str      w11, [sp, #0xb0]
0x0068a558: ab 0d 80 52              mov      w11, #0x6d
0x0068a55c: ac 06 80 52              mov      w12, #0x35
0x0068a560: ed 1b 01 b9              str      w13, [sp, #0x118]
0x0068a564: 2d 06 80 52              mov      w13, #0x31
0x0068a568: ef c3 00 b9              str      w15, [sp, #0xc0]
0x0068a56c: 4f 0c 80 52              mov      w15, #0x62
0x0068a570: ea 33 01 b9              str      w10, [sp, #0x130]
0x0068a574: ea ab 00 b9              str      w10, [sp, #0xa8]
0x0068a578: 8a 0e 80 52              mov      w10, #0x74
0x0068a57c: e9 a3 00 b9              str      w9, [sp, #0xa0]
