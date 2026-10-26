; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/ttlibs/libeffect.so
; target  : address 0x951540
; start   : 0x951540
; size    : 0x260
; bytes   : 0x260   instructions: 152
; generated: 2026-10-23T23:49:40

0x00951540: c4 e9 04 94              bl       #0xa8bc50
0x00951544: 60 06 00 f9              str      x0, [x19, #8]
0x00951548: f3 7b 41 a9              ldp      x19, x30, [sp, #0x10]
0x0095154c: 68 46 00 b0              adrp     x8, #0x121e000
0x00951550: 08 21 3c 91              add      x8, x8, #0xf08
0x00951554: 1f 50 01 a9              stp      xzr, x20, [x0, #0x10]
0x00951558: 08 7c 00 a9              stp      x8, xzr, [x0]
0x0095155c: 47 e7 04 14              b        #0xa8b278
0x00951560: f8 0f 1c f8              str      x24, [sp, #-0x40]!
0x00951564: f7 5b 01 a9              stp      x23, x22, [sp, #0x10]
0x00951568: f5 53 02 a9              stp      x21, x20, [sp, #0x20]
0x0095156c: f3 7b 03 a9              stp      x19, x30, [sp, #0x30]
0x00951570: 16 24 80 b9              ldrsw    x22, [x0, #0x24]
0x00951574: f0 42 05 94              bl       #0xaa2134
0x00951578: 14 70 05 91              add      x20, x0, #0x15c
0x0095157c: 97 24 80 52              mov      w23, #0x124
0x00951580: bf 02 16 eb              cmp      x21, x22
0x00951584: 6a 01 00 54              b.ge     #0x9515b0
0x00951588: 68 1a 40 f9              ldr      x8, [x19, #0x30]
0x0095158c: 18 01 17 8b              add      x24, x8, x23
0x00951590: 00 03 40 f9              ldr      x0, [x24]
0x00951594: 57 d0 e5 97              bl       #0x2c56f0                  ; -> free@plt
0x00951598: 00 07 40 f9              ldr      x0, [x24, #8]
0x0095159c: 55 d0 e5 97              bl       #0x2c56f0                  ; -> free@plt
0x009515a0: b5 06 00 91              add      x21, x21, #1
0x009515a4: 1f 7f 00 a9              stp      xzr, xzr, [x24]
0x009515a8: f7 d2 04 91              add      x23, x23, #0x134
0x009515ac: f5 ff ff 17              b        #0x951580
0x009515b0: 60 16 40 f9              ldr      x0, [x19, #0x28]
0x009515b4: 4f d0 e5 97              bl       #0x2c56f0                  ; -> free@plt
0x009515b8: 60 1a 40 f9              ldr      x0, [x19, #0x30]
0x009515bc: 4d d0 e5 97              bl       #0x2c56f0                  ; -> free@plt
0x009515c0: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x009515c4: 7f 1a 00 f9              str      xzr, [x19, #0x30]
0x009515c8: 07 7a 06 94              bl       #0xaefde4
0x009515cc: c4 92 05 94              bl       #0xab60dc
0x009515d0: 80 06 40 f9              ldr      x0, [x20, #8]
0x009515d4: 47 d0 e5 97              bl       #0x2c56f0                  ; -> free@plt
0x009515d8: 60 e2 00 91              add      x0, x19, #0x38
0x009515dc: f3 7b 43 a9              ldp      x19, x30, [sp, #0x30]
0x009515e0: f5 53 42 a9              ldp      x21, x20, [sp, #0x20]
0x009515e4: f7 5b 41 a9              ldp      x23, x22, [sp, #0x10]
0x009515e8: 82 26 80 52              mov      w2, #0x134
0x009515ec: e1 03 1f 2a              mov      w1, wzr
0x009515f0: f8 07 44 f8              ldr      x24, [sp], #0x40
0x009515f4: fb cf e5 17              b        #0x2c55e0                  ; -> memset@plt
0x009515f8: fe 0f 1f f8              str      x30, [sp, #-0x10]!
0x009515fc: e5 03 01 aa              mov      x5, x1
0x00951600: 3f 8c 00 f1              cmp      x1, #0x23
0x00951604: 0c 01 00 54              b.gt     #0x951624
0x00951608: c0 af 06 94              bl       #0xafd508
0x0095160c: e4 21 00 d0              adrp     x4, #0xd8f000
0x00951610: bb af 06 94              bl       #0xafd4fc
0x00951614: 84 48 20 91              add      x4, x4, #0x812
0x00951618: e1 10 80 52              mov      w1, #0x87
0x0095161c: a5 ea 04 94              bl       #0xa8c0b0
0x00951620: 0c 00 00 14              b        #0x951650
0x00951624: 06 00 40 b9              ldr      w6, [x0]
0x00951628: df 00 05 eb              cmp      x6, x5
0x0095162c: 61 00 00 54              b.ne     #0x951638
0x00951630: 20 00 80 52              mov      w0, #1
0x00951634: 08 00 00 14              b        #0x951654
0x00951638: b4 af 06 94              bl       #0xafd508
0x0095163c: e4 21 00 d0              adrp     x4, #0xd8f000
0x00951640: af af 06 94              bl       #0xafd4fc
0x00951644: 84 40 21 91              add      x4, x4, #0x850
0x00951648: e1 11 80 52              mov      w1, #0x8f
0x0095164c: 99 ea 04 94              bl       #0xa8c0b0
0x00951650: e0 03 1f 2a              mov      w0, wzr
0x00951654: fe 07 41 f8              ldr      x30, [sp], #0x10
0x00951658: c0 03 5f d6              ret      
0x0095165c: ff c3 01 d1              sub      sp, sp, #0x70
0x00951660: e4 03 1e aa              mov      x4, x30
0x00951664: 95 ea 04 94              bl       #0xa8c0b8
0x00951668: fe 03 04 aa              mov      x30, x4
0x0095166c: fd 7b 06 a9              stp      x29, x30, [sp, #0x60]
0x00951670: a2 05 00 b4              cbz      x2, #0x951724
0x00951674: 7f 00 00 f1              cmp      x3, #0
0x00951678: 6d 05 00 54              b.le     #0x951724
0x0095167c: c4 01 05 94              bl       #0xa91d8c
0x00951680: e0 03 02 aa              mov      x0, x2
0x00951684: e1 03 03 aa              mov      x1, x3
0x00951688: f3 03 02 aa              mov      x19, x2
0x0095168c: db ff ff 97              bl       #0x9515f8
0x00951690: 00 05 00 36              tbz      w0, #0, #0x951730
0x00951694: 7d 26 40 b9              ldr      w29, [x19, #0x24]
0x00951698: bf 07 00 71              cmp      w29, #1
0x0095169c: 0b 05 00 54              b.lt     #0x95173c
0x009516a0: 88 72 05 91              add      x8, x20, #0x15c
0x009516a4: e0 03 14 aa              mov      x0, x20
0x009516a8: f4 23 00 a9              stp      x20, x8, [sp]
0x009516ac: ad ff ff 97              bl       #0x951560
0x009516b0: a8 77 1e 53              lsl      w8, w29, #2
0x009516b4: fc 21 00 d0              adrp     x28, #0xd8f000
0x009516b8: f8 03 1f aa              mov      x24, xzr
0x009516bc: 7a a2 00 91              add      x26, x19, #0x28
0x009516c0: bb 06 00 91              add      x27, x21, #1
0x009516c4: 14 a1 00 11              add      w20, w8, #0x28
0x009516c8: 9c db 1e 91              add      x28, x28, #0x7b6
0x009516cc: bf 03 18 eb              cmp      x29, x24
0x009516d0: 60 03 00 54              b.eq     #0x95173c
0x009516d4: 56 7b 78 b8              ldr      w22, [x26, x24, lsl #2]
0x009516d8: 8c af 06 94              bl       #0xafd508
0x009516dc: 61 17 80 52              mov      w1, #0xbb
0x009516e0: 42 06 80 52              mov      w2, #0x32
0x009516e4: 86 af 06 94              bl       #0xafd4fc
0x009516e8: e4 03 1c aa              mov      x4, x28
0x009516ec: e5 03 18 2a              mov      w5, w24
0x009516f0: e6 03 16 2a              mov      w6, w22
0x009516f4: f3 d2 e5 97              bl       #0x2c62c0                  ; -> _ZN13AmazingEngine8g_aeLogTEPKciiS1_S1_z@plt
0x009516f8: 24 32 05 94              bl       #0xa9df88
0x009516fc: 79 c2 34 8b              add      x25, x19, w20, sxtw
0x00951700: e1 03 19 aa              mov      x1, x25
0x00951704: 1f 01 00 72              tst      w8, #1
0x00951708: 77 03 89 9a              csel     x23, x27, x9, eq
0x0095170c: e0 03 17 aa              mov      x0, x23
0x00951710: 78 d0 e5 97              bl       #0x2c58f0                  ; -> strcmp@plt
0x00951714: 60 02 00 34              cbz      w0, #0x951760
0x00951718: d4 02 14 0b              add      w20, w22, w20
0x0095171c: 18 07 00 91              add      x24, x24, #1
0x00951720: eb ff ff 17              b        #0x9516cc
0x00951724: ba 19 06 94              bl       #0xad7e0c
0x00951728: 41 13 80 52              mov      w1, #0x9a
0x0095172c: 03 00 00 14              b        #0x951738
0x00951730: b0 19 06 94              bl       #0xad7df0
0x00951734: 21 14 80 52              mov      w1, #0xa1
0x00951738: 5e ea 04 94              bl       #0xa8c0b0
0x0095173c: f5 03 1f aa              mov      x21, xzr
0x00951740: e0 03 15 aa              mov      x0, x21
0x00951744: fd 7b 46 a9              ldp      x29, x30, [sp, #0x60]
0x00951748: f4 4f 45 a9              ldp      x20, x19, [sp, #0x50]
0x0095174c: f6 57 44 a9              ldp      x22, x21, [sp, #0x40]
0x00951750: f8 5f 43 a9              ldp      x24, x23, [sp, #0x30]
0x00951754: fa 67 42 a9              ldp      x26, x25, [sp, #0x20]
0x00951758: fc 6f 41 a9              ldp      x28, x27, [sp, #0x10]
0x0095175c: 68 e7 04 14              b        #0xa8b4fc
0x00951760: 68 12 40 39              ldrb     w8, [x19, #4]
0x00951764: c8 01 00 34              cbz      w8, #0x95179c
0x00951768: e8 03 1f 2a              mov      w8, wzr
0x0095176c: e9 03 14 2a              mov      w9, w20
0x00951770: 8a 24 80 52              mov      w10, #0x124
0x00951774: 5f 01 16 eb              cmp      x10, x22
0x00951778: c2 00 00 54              b.hs     #0x951790
0x0095177c: 2b 01 0a 0b              add      w11, w9, w10
0x00951780: 6b 4a 6b 38              ldrb     w11, [x19, w11, uxtw]
0x00951784: 4a 05 00 91              add      x10, x10, #1
0x00951788: 68 01 08 4a              eor      w8, w11, w8
0x0095178c: fa ff ff 17              b        #0x951774
0x00951790: 29 03 44 39              ldrb     w9, [x25, #0x100]
0x00951794: 3f 01 28 6b              cmp      w9, w8, uxtb
0x00951798: 41 07 00 54              b.ne     #0x951880
0x0095179c: f6 03 40 f9              ldr      x22, [sp]
