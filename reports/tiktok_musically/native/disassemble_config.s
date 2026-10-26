; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_musically/native/libbytenn.so
; target  : _ZN6BYTENN20EspressoDisassembler11disassembleERNS_20ByteNNInternalConfigE (0x42500)
; start   : 0x42500
; size    : 0x5f0
; bytes   : 0x5f0   instructions: 380
; generated: 2026-10-23T23:37:24

0x00042500: ff 03 02 d1              sub      sp, sp, #0x80
0x00042504: fc 6f 02 a9              stp      x28, x27, [sp, #0x20]
0x00042508: fa 67 03 a9              stp      x26, x25, [sp, #0x30]
0x0004250c: f8 5f 04 a9              stp      x24, x23, [sp, #0x40]
0x00042510: f6 57 05 a9              stp      x22, x21, [sp, #0x50]
0x00042514: f4 4f 06 a9              stp      x20, x19, [sp, #0x60]
0x00042518: fd 7b 07 a9              stp      x29, x30, [sp, #0x70]
0x0004251c: fd c3 01 91              add      x29, sp, #0x70
0x00042520: 14 04 40 f9              ldr      x20, [x0, #8]
0x00042524: 04 10 40 b9              ldr      w4, [x0, #0x10]
0x00042528: 14 05 00 b4              cbz      x20, #0x425c8
0x0004252c: 9f 00 00 71              cmp      w4, #0
0x00042530: cd 04 00 54              b.le     #0x425c8
0x00042534: 83 06 40 b9              ldr      w3, [x20, #4]
0x00042538: 7f 00 04 6b              cmp      w3, w4
0x0004253c: 81 05 00 54              b.ne     #0x425ec
0x00042540: 08 50 40 39              ldrb     w8, [x0, #0x14]
0x00042544: 95 0e 40 39              ldrb     w21, [x20, #3]
0x00042548: f3 03 00 aa              mov      x19, x0
0x0004254c: a8 00 00 34              cbz      w8, #0x42560
0x00042550: e0 03 14 aa              mov      x0, x20
0x00042554: e1 03 15 2a              mov      w1, w21
0x00042558: f2 b4 ff 97              bl       #0x2f920                   ; -> _ZN6BYTENN11CheckMd5sumEPKhi@plt
0x0004255c: 60 05 00 35              cbnz     w0, #0x42608
0x00042560: 88 02 40 39              ldrb     w8, [x20]
0x00042564: 1f 09 01 71              cmp      w8, #0x42
0x00042568: e1 04 00 54              b.ne     #0x42604
0x0004256c: 88 06 40 39              ldrb     w8, [x20, #1]
0x00042570: 1f 35 01 71              cmp      w8, #0x4d
0x00042574: 81 04 00 54              b.ne     #0x42604
0x00042578: 78 06 40 f9              ldr      x24, [x19, #8]
0x0004257c: bf 06 00 71              cmp      w21, #1
0x00042580: 41 05 00 54              b.ne     #0x42628
0x00042584: 08 13 40 b9              ldr      w8, [x24, #0x10]
0x00042588: ff 7f 01 a9              stp      xzr, xzr, [sp, #0x10]
0x0004258c: ff 07 00 f9              str      xzr, [sp, #8]
0x00042590: 15 03 08 8b              add      x21, x24, x8
0x00042594: e0 03 15 aa              mov      x0, x21
0x00042598: 8e b6 ff 97              bl       #0x2ffd0                   ; -> strlen@plt
0x0004259c: 1f 40 00 b1              cmn      x0, #0x10
0x000425a0: 62 2a 00 54              b.hs     #0x42aec
0x000425a4: f4 03 00 aa              mov      x20, x0
0x000425a8: 1f 5c 00 f1              cmp      x0, #0x17
0x000425ac: 22 09 00 54              b.hs     #0x426d0
0x000425b0: e9 23 00 91              add      x9, sp, #8
0x000425b4: 88 7a 1f 53              lsl      w8, w20, #1
0x000425b8: 36 01 40 b2              orr      x22, x9, #1
0x000425bc: e8 23 00 39              strb     w8, [sp, #8]
0x000425c0: 94 09 00 b5              cbnz     x20, #0x426f0
0x000425c4: 4f 00 00 14              b        #0x42700                   ; -> _ZN6BYTENN20EspressoDisassembler11disassembleERNS_20ByteNNInternalConfigE+0x200
0x000425c8: 01 0a 00 b0              adrp     x1, #0x183000
0x000425cc: 02 0a 00 d0              adrp     x2, #0x184000
0x000425d0: 21 e0 21 91              add      x1, x1, #0x878
0x000425d4: 42 b8 2a 91              add      x2, x2, #0xaae
0x000425d8: 20 00 80 52              mov      w0, #1
0x000425dc: e3 03 14 aa              mov      x3, x20
0x000425e0: 00 bb ff 97              bl       #0x311e0                   ; -> _ZN6BYTENN9ByteNNLogEiPKcS1_z@plt
0x000425e4: 40 01 80 52              mov      w0, #0xa
0x000425e8: 08 00 00 14              b        #0x42608                   ; -> _ZN6BYTENN20EspressoDisassembler11disassembleERNS_20ByteNNInternalConfigE+0x108
0x000425ec: 01 0a 00 b0              adrp     x1, #0x183000
0x000425f0: 02 0a 00 d0              adrp     x2, #0x184000
0x000425f4: 21 e0 21 91              add      x1, x1, #0x878
0x000425f8: 42 b4 2b 91              add      x2, x2, #0xaed
0x000425fc: 20 00 80 52              mov      w0, #1
0x00042600: f8 ba ff 97              bl       #0x311e0                   ; -> _ZN6BYTENN9ByteNNLogEiPKcS1_z@plt
0x00042604: 40 01 80 52              mov      w0, #0xa
0x00042608: fd 7b 47 a9              ldp      x29, x30, [sp, #0x70]
0x0004260c: f4 4f 46 a9              ldp      x20, x19, [sp, #0x60]
0x00042610: f6 57 45 a9              ldp      x22, x21, [sp, #0x50]
0x00042614: f8 5f 44 a9              ldp      x24, x23, [sp, #0x40]
0x00042618: fa 67 43 a9              ldp      x26, x25, [sp, #0x30]
0x0004261c: fc 6f 42 a9              ldp      x28, x27, [sp, #0x20]
0x00042620: ff 03 02 91              add      sp, sp, #0x80
0x00042624: c0 03 5f d6              ret      
0x00042628: 88 0a 40 b9              ldr      w8, [x20, #8]
0x0004262c: 09 93 00 91              add      x9, x24, #0x24
0x00042630: 0a b3 00 91              add      x10, x24, #0x2c
0x00042634: 0b d3 00 91              add      x11, x24, #0x34
0x00042638: 1f 0d 00 71              cmp      w8, #3
0x0004263c: 3b 81 9f 9a              csel     x27, x9, xzr, hi
0x00042640: 1f 11 00 71              cmp      w8, #4
0x00042644: 5a 81 9f 9a              csel     x26, x10, xzr, hi
0x00042648: 1f 15 00 71              cmp      w8, #5
0x0004264c: 79 81 9f 9a              csel     x25, x11, xzr, hi
0x00042650: bf 0a 00 71              cmp      w21, #2
0x00042654: a0 00 00 54              b.eq     #0x42668
0x00042658: bf 0e 00 71              cmp      w21, #3
0x0004265c: e3 07 00 54              b.lo     #0x42758
0x00042660: 08 1f 40 b9              ldr      w8, [x24, #0x1c]
0x00042664: a8 07 00 34              cbz      w8, #0x42758
0x00042668: 00 a3 41 29              ldp      w0, w8, [x24, #0xc]
0x0004266c: 15 03 08 8b              add      x21, x24, x8
0x00042670: 94 b5 ff 97              bl       #0x2fcc0                   ; -> malloc@plt
0x00042674: 02 0f 40 b9              ldr      w2, [x24, #0xc]
0x00042678: e1 03 15 aa              mov      x1, x21
0x0004267c: f4 03 00 aa              mov      x20, x0
0x00042680: 08 b4 ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00042684: 08 0f 40 b9              ldr      w8, [x24, #0xc]
0x00042688: 48 12 00 34              cbz      w8, #0x428d0
0x0004268c: 0b 23 40 b9              ldr      w11, [x24, #0x20]
0x00042690: ea 03 1f aa              mov      x10, xzr
0x00042694: 1f 81 00 71              cmp      w8, #0x20
0x00042698: 09 03 0b 8b              add      x9, x24, x11
0x0004269c: 03 0d 00 54              b.lo     #0x4283c
0x000426a0: 0c 05 00 d1              sub      x12, x8, #1
0x000426a4: 9f 1d 00 f1              cmp      x12, #7
0x000426a8: a8 0c 00 54              b.hi     #0x4283c
0x000426ac: 0a 01 0b 8b              add      x10, x8, x11
0x000426b0: 0a 03 0a 8b              add      x10, x24, x10
0x000426b4: 9f 02 0a eb              cmp      x20, x10
0x000426b8: 82 0a 00 54              b.hs     #0x42808
0x000426bc: 8a 02 08 8b              add      x10, x20, x8
0x000426c0: 3f 01 0a eb              cmp      x9, x10
0x000426c4: 22 0a 00 54              b.hs     #0x42808
0x000426c8: ea 03 1f aa              mov      x10, xzr
0x000426cc: 5c 00 00 14              b        #0x4283c                   ; -> _ZN6BYTENN20EspressoDisassembler11disassembleERNS_20ByteNNInternalConfigE+0x33c
0x000426d0: 88 42 00 91              add      x8, x20, #0x10
0x000426d4: 17 ed 7c 92              and      x23, x8, #0xfffffffffffffff0
0x000426d8: e0 03 17 aa              mov      x0, x23
0x000426dc: 5d ba ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x000426e0: e8 02 40 b2              orr      x8, x23, #1
0x000426e4: f6 03 00 aa              mov      x22, x0
0x000426e8: e8 d3 00 a9              stp      x8, x20, [sp, #8]
0x000426ec: e0 0f 00 f9              str      x0, [sp, #0x18]
0x000426f0: e0 03 16 aa              mov      x0, x22
0x000426f4: e1 03 15 aa              mov      x1, x21
0x000426f8: e2 03 14 aa              mov      x2, x20
0x000426fc: e9 b3 ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00042700: df 6a 34 38              strb     wzr, [x22, x20]
0x00042704: f4 03 13 aa              mov      x20, x19
0x00042708: 88 8e 4f 38              ldrb     w8, [x20, #0xf8]!
0x0004270c: 68 00 00 37              tbnz     w8, #0, #0x42718
0x00042710: 9f 02 00 79              strh     wzr, [x20]
0x00042714: 09 00 00 14              b        #0x42738                   ; -> _ZN6BYTENN20EspressoDisassembler11disassembleERNS_20ByteNNInternalConfigE+0x238
0x00042718: 68 86 40 f9              ldr      x8, [x19, #0x108]
0x0004271c: 1f 01 00 39              strb     wzr, [x8]
0x00042720: 68 e2 43 39              ldrb     w8, [x19, #0xf8]
0x00042724: 7f 82 00 f9              str      xzr, [x19, #0x100]
0x00042728: 88 00 00 36              tbz      w8, #0, #0x42738
0x0004272c: 60 86 40 f9              ldr      x0, [x19, #0x108]
0x00042730: 5c b4 ff 97              bl       #0x2f8a0                   ; -> _ZdlPv@plt
0x00042734: 7f 7e 00 f9              str      xzr, [x19, #0xf8]
0x00042738: f9 03 1f aa              mov      x25, xzr
0x0004273c: fa 03 1f aa              mov      x26, xzr
0x00042740: fb 03 1f aa              mov      x27, xzr
0x00042744: e8 0f 40 f9              ldr      x8, [sp, #0x18]
0x00042748: e0 83 c0 3c              ldur     q0, [sp, #8]
0x0004274c: 88 0a 00 f9              str      x8, [x20, #0x10]
0x00042750: 80 02 80 3d              str      q0, [x20]
0x00042754: 8e 00 00 14              b        #0x4298c                   ; -> _ZN6BYTENN20EspressoDisassembler11disassembleERNS_20ByteNNInternalConfigE+0x48c
0x00042758: 08 13 40 b9              ldr      w8, [x24, #0x10]
0x0004275c: ff 7f 01 a9              stp      xzr, xzr, [sp, #0x10]
0x00042760: ff 07 00 f9              str      xzr, [sp, #8]
0x00042764: 15 03 08 8b              add      x21, x24, x8
0x00042768: e0 03 15 aa              mov      x0, x21
0x0004276c: 19 b6 ff 97              bl       #0x2ffd0                   ; -> strlen@plt
0x00042770: 1f 40 00 b1              cmn      x0, #0x10
0x00042774: c2 1b 00 54              b.hs     #0x42aec
0x00042778: f4 03 00 aa              mov      x20, x0
0x0004277c: 1f 5c 00 f1              cmp      x0, #0x17
0x00042780: e2 00 00 54              b.hs     #0x4279c
0x00042784: e9 23 00 91              add      x9, sp, #8
0x00042788: 88 7a 1f 53              lsl      w8, w20, #1
0x0004278c: 36 01 40 b2              orr      x22, x9, #1
0x00042790: e8 23 00 39              strb     w8, [sp, #8]
0x00042794: 54 01 00 b5              cbnz     x20, #0x427bc
0x00042798: 0d 00 00 14              b        #0x427cc                   ; -> _ZN6BYTENN20EspressoDisassembler11disassembleERNS_20ByteNNInternalConfigE+0x2cc
0x0004279c: 88 42 00 91              add      x8, x20, #0x10
0x000427a0: 17 ed 7c 92              and      x23, x8, #0xfffffffffffffff0
0x000427a4: e0 03 17 aa              mov      x0, x23
0x000427a8: 2a ba ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x000427ac: e8 02 40 b2              orr      x8, x23, #1
0x000427b0: f6 03 00 aa              mov      x22, x0
0x000427b4: e8 d3 00 a9              stp      x8, x20, [sp, #8]
0x000427b8: e0 0f 00 f9              str      x0, [sp, #0x18]
0x000427bc: e0 03 16 aa              mov      x0, x22
0x000427c0: e1 03 15 aa              mov      x1, x21
0x000427c4: e2 03 14 aa              mov      x2, x20
0x000427c8: b6 b3 ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x000427cc: df 6a 34 38              strb     wzr, [x22, x20]
0x000427d0: f4 03 13 aa              mov      x20, x19
0x000427d4: 88 8e 4f 38              ldrb     w8, [x20, #0xf8]!
0x000427d8: 68 00 00 37              tbnz     w8, #0, #0x427e4
0x000427dc: 9f 02 00 79              strh     wzr, [x20]
0x000427e0: d9 ff ff 17              b        #0x42744                   ; -> _ZN6BYTENN20EspressoDisassembler11disassembleERNS_20ByteNNInternalConfigE+0x244
0x000427e4: 68 86 40 f9              ldr      x8, [x19, #0x108]
0x000427e8: 1f 01 00 39              strb     wzr, [x8]
0x000427ec: 68 e2 43 39              ldrb     w8, [x19, #0xf8]
0x000427f0: 7f 82 00 f9              str      xzr, [x19, #0x100]
0x000427f4: 88 fa 07 36              tbz      w8, #0, #0x42744
0x000427f8: 60 86 40 f9              ldr      x0, [x19, #0x108]
0x000427fc: 29 b4 ff 97              bl       #0x2f8a0                   ; -> _ZdlPv@plt
0x00042800: 7f 7e 00 f9              str      xzr, [x19, #0xf8]
0x00042804: d0 ff ff 17              b        #0x42744                   ; -> _ZN6BYTENN20EspressoDisassembler11disassembleERNS_20ByteNNInternalConfigE+0x244
0x00042808: 20 05 40 ad              ldp      q0, q1, [x9]
0x0004280c: 0a 69 7b 92              and      x10, x8, #0xffffffe0
0x00042810: 8b 42 00 91              add      x11, x20, #0x10
0x00042814: ec 03 0a aa              mov      x12, x10
0x00042818: 62 8d 7f ad              ldp      q2, q3, [x11, #-0x10]
0x0004281c: 8c 81 00 f1              subs     x12, x12, #0x20
0x00042820: 02 1c 22 6e              eor      v2.16b, v0.16b, v2.16b
0x00042824: 23 1c 23 6e              eor      v3.16b, v1.16b, v3.16b
0x00042828: 62 8d 3f ad              stp      q2, q3, [x11, #-0x10]
0x0004282c: 6b 81 00 91              add      x11, x11, #0x20
0x00042830: 41 ff ff 54              b.ne     #0x42818
0x00042834: 5f 01 08 eb              cmp      x10, x8
0x00042838: 20 01 00 54              b.eq     #0x4285c
0x0004283c: 4c 09 40 92              and      x12, x10, #7
0x00042840: 8b 6a 6a 38              ldrb     w11, [x20, x10]
0x00042844: 2c 69 6c 38              ldrb     w12, [x9, x12]
0x00042848: 8b 01 0b 4a              eor      w11, w12, w11
0x0004284c: 8b 6a 2a 38              strb     w11, [x20, x10]
0x00042850: 4a 05 00 91              add      x10, x10, #1
0x00042854: 1f 01 0a eb              cmp      x8, x10
0x00042858: 21 ff ff 54              b.ne     #0x4283c
0x0004285c: 09 0f 40 b9              ldr      w9, [x24, #0xc]
0x00042860: 3f 05 00 71              cmp      w9, #1
0x00042864: 6b 03 00 54              b.lt     #0x428d0
0x00042868: 08 0a 00 d0              adrp     x8, #0x184000
0x0004286c: 3f 05 00 71              cmp      w9, #1
0x00042870: 08 c9 2c 91              add      x8, x8, #0xb32
0x00042874: 61 00 00 54              b.ne     #0x42880
0x00042878: ea 03 1f aa              mov      x10, xzr
0x0004287c: 0e 00 00 14              b        #0x428b4                   ; -> _ZN6BYTENN20EspressoDisassembler11disassembleERNS_20ByteNNInternalConfigE+0x3b4
0x00042880: 2a 79 7f 92              and      x10, x9, #0xfffffffe
0x00042884: 8b 06 00 91              add      x11, x20, #1
0x00042888: ec 03 0a aa              mov      x12, x10
0x0004288c: 6d f1 5f 38              ldurb    w13, [x11, #-1]
0x00042890: 6e 01 40 39              ldrb     w14, [x11]
0x00042894: 8c 09 00 f1              subs     x12, x12, #2
0x00042898: 0d 69 6d 38              ldrb     w13, [x8, x13]
0x0004289c: 0e 69 6e 38              ldrb     w14, [x8, x14]
0x000428a0: 6d f1 1f 38              sturb    w13, [x11, #-1]
0x000428a4: 6e 25 00 38              strb     w14, [x11], #2
0x000428a8: 21 ff ff 54              b.ne     #0x4288c
0x000428ac: 5f 01 09 eb              cmp      x10, x9
0x000428b0: 00 01 00 54              b.eq     #0x428d0
0x000428b4: 29 01 0a cb              sub      x9, x9, x10
0x000428b8: 8a 02 0a 8b              add      x10, x20, x10
0x000428bc: 4b 01 40 39              ldrb     w11, [x10]
0x000428c0: 29 05 00 f1              subs     x9, x9, #1
0x000428c4: 0b 69 6b 38              ldrb     w11, [x8, x11]
0x000428c8: 4b 15 00 38              strb     w11, [x10], #1
0x000428cc: 81 ff ff 54              b.ne     #0x428bc
0x000428d0: e0 03 14 aa              mov      x0, x20
0x000428d4: ff 7f 01 a9              stp      xzr, xzr, [sp, #0x10]
0x000428d8: ff 07 00 f9              str      xzr, [sp, #8]
0x000428dc: bd b5 ff 97              bl       #0x2ffd0                   ; -> strlen@plt
0x000428e0: 1f 40 00 b1              cmn      x0, #0x10
0x000428e4: 42 10 00 54              b.hs     #0x42aec
0x000428e8: f5 03 00 aa              mov      x21, x0
0x000428ec: 1f 5c 00 f1              cmp      x0, #0x17
0x000428f0: e2 00 00 54              b.hs     #0x4290c
0x000428f4: e9 23 00 91              add      x9, sp, #8
0x000428f8: a8 7a 1f 53              lsl      w8, w21, #1
0x000428fc: 36 01 40 b2              orr      x22, x9, #1
0x00042900: e8 23 00 39              strb     w8, [sp, #8]
0x00042904: 55 01 00 b5              cbnz     x21, #0x4292c
0x00042908: 0d 00 00 14              b        #0x4293c                   ; -> _ZN6BYTENN20EspressoDisassembler11disassembleERNS_20ByteNNInternalConfigE+0x43c
0x0004290c: a8 42 00 91              add      x8, x21, #0x10
0x00042910: 17 ed 7c 92              and      x23, x8, #0xfffffffffffffff0
0x00042914: e0 03 17 aa              mov      x0, x23
0x00042918: ce b9 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x0004291c: e8 02 40 b2              orr      x8, x23, #1
0x00042920: f6 03 00 aa              mov      x22, x0
0x00042924: e8 d7 00 a9              stp      x8, x21, [sp, #8]
0x00042928: e0 0f 00 f9              str      x0, [sp, #0x18]
0x0004292c: e0 03 16 aa              mov      x0, x22
0x00042930: e1 03 14 aa              mov      x1, x20
0x00042934: e2 03 15 aa              mov      x2, x21
0x00042938: 5a b3 ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x0004293c: df 6a 35 38              strb     wzr, [x22, x21]
0x00042940: f5 03 13 aa              mov      x21, x19
0x00042944: a8 8e 4f 38              ldrb     w8, [x21, #0xf8]!
0x00042948: 68 00 00 37              tbnz     w8, #0, #0x42954
0x0004294c: bf 02 00 79              strh     wzr, [x21]
0x00042950: 09 00 00 14              b        #0x42974                   ; -> _ZN6BYTENN20EspressoDisassembler11disassembleERNS_20ByteNNInternalConfigE+0x474
0x00042954: 68 86 40 f9              ldr      x8, [x19, #0x108]
0x00042958: 1f 01 00 39              strb     wzr, [x8]
0x0004295c: 68 e2 43 39              ldrb     w8, [x19, #0xf8]
0x00042960: 7f 82 00 f9              str      xzr, [x19, #0x100]
0x00042964: 88 00 00 36              tbz      w8, #0, #0x42974
0x00042968: 60 86 40 f9              ldr      x0, [x19, #0x108]
0x0004296c: cd b3 ff 97              bl       #0x2f8a0                   ; -> _ZdlPv@plt
0x00042970: 7f 7e 00 f9              str      xzr, [x19, #0xf8]
0x00042974: e8 0f 40 f9              ldr      x8, [sp, #0x18]
0x00042978: e0 83 c0 3c              ldur     q0, [sp, #8]
0x0004297c: e0 03 14 aa              mov      x0, x20
0x00042980: a8 0a 00 f9              str      x8, [x21, #0x10]
0x00042984: a0 02 80 3d              str      q0, [x21]
0x00042988: ae b4 ff 97              bl       #0x2fc40                   ; -> free@plt
0x0004298c: e1 0c 00 90              adrp     x1, #0x1de000
0x00042990: 77 06 40 f9              ldr      x23, [x19, #8]
0x00042994: 00 f3 42 29              ldp      w0, w28, [x24, #0x14]
0x00042998: 21 b8 45 f9              ldr      x1, [x1, #0xb70]           ; -> _ZSt7nothrow@got
0x0004299c: 29 b6 ff 97              bl       #0x30240                   ; -> _ZnamRKSt9nothrow_t@plt
0x000429a0: f5 03 00 aa              mov      x21, x0
0x000429a4: 00 04 80 52              mov      w0, #0x20
0x000429a8: aa b9 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x000429ac: f6 0c 00 90              adrp     x22, #0x1de000
0x000429b0: d6 1a 45 f9              ldr      x22, [x22, #0xa30]         ; -> _ZTVNSt6__ndk120__shared_ptr_pointerIPhNS_14default_deleteIA_hEENS_9allocatorIhEEEE@got
0x000429b4: 1f 54 01 a9              stp      xzr, x21, [x0, #0x10]
0x000429b8: c8 42 00 91              add      x8, x22, #0x10
0x000429bc: 08 7c 00 a9              stp      x8, xzr, [x0]
0x000429c0: 74 66 40 f9              ldr      x20, [x19, #0xc8]
0x000429c4: 75 02 0c a9              stp      x21, x0, [x19, #0xc0]
0x000429c8: b4 01 00 b4              cbz      x20, #0x429fc
0x000429cc: 88 22 00 91              add      x8, x20, #8
0x000429d0: 09 fd 5f c8              ldaxr    x9, [x8]
0x000429d4: 2a 05 00 d1              sub      x10, x9, #1
0x000429d8: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x000429dc: ab ff ff 35              cbnz     w11, #0x429d0
0x000429e0: e9 00 00 b5              cbnz     x9, #0x429fc
0x000429e4: 88 02 40 f9              ldr      x8, [x20]
0x000429e8: e0 03 14 aa              mov      x0, x20
0x000429ec: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x000429f0: 00 01 3f d6              blr      x8
0x000429f4: e0 03 14 aa              mov      x0, x20
0x000429f8: 56 b8 ff 97              bl       #0x30b50                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x000429fc: 60 62 40 f9              ldr      x0, [x19, #0xc0]
0x00042a00: 20 07 00 b4              cbz      x0, #0x42ae4
0x00042a04: 02 17 40 b9              ldr      w2, [x24, #0x14]
0x00042a08: e1 02 1c 8b              add      x1, x23, x28
0x00042a0c: 25 b3 ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00042a10: 08 17 40 b9              ldr      w8, [x24, #0x14]
0x00042a14: 68 72 00 f9              str      x8, [x19, #0xe0]
0x00042a18: 5b 04 00 b4              cbz      x27, #0x42aa0
0x00042a1c: e1 0c 00 90              adrp     x1, #0x1de000
0x00042a20: 77 06 40 f9              ldr      x23, [x19, #8]
0x00042a24: 60 63 40 29              ldp      w0, w24, [x27]
0x00042a28: 21 b8 45 f9              ldr      x1, [x1, #0xb70]           ; -> _ZSt7nothrow@got
0x00042a2c: 05 b6 ff 97              bl       #0x30240                   ; -> _ZnamRKSt9nothrow_t@plt
0x00042a30: f5 03 00 aa              mov      x21, x0
0x00042a34: 00 04 80 52              mov      w0, #0x20
0x00042a38: 86 b9 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00042a3c: c8 42 00 91              add      x8, x22, #0x10
0x00042a40: 08 7c 00 a9              stp      x8, xzr, [x0]
0x00042a44: 1f 54 01 a9              stp      xzr, x21, [x0, #0x10]
0x00042a48: 74 8e 40 f9              ldr      x20, [x19, #0x118]
0x00042a4c: 75 02 11 a9              stp      x21, x0, [x19, #0x110]
0x00042a50: b4 01 00 b4              cbz      x20, #0x42a84
0x00042a54: 88 22 00 91              add      x8, x20, #8
0x00042a58: 09 fd 5f c8              ldaxr    x9, [x8]
0x00042a5c: 2a 05 00 d1              sub      x10, x9, #1
0x00042a60: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00042a64: ab ff ff 35              cbnz     w11, #0x42a58
0x00042a68: e9 00 00 b5              cbnz     x9, #0x42a84
0x00042a6c: 88 02 40 f9              ldr      x8, [x20]
0x00042a70: e0 03 14 aa              mov      x0, x20
0x00042a74: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00042a78: 00 01 3f d6              blr      x8
0x00042a7c: e0 03 14 aa              mov      x0, x20
0x00042a80: 34 b8 ff 97              bl       #0x30b50                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00042a84: 60 8a 40 f9              ldr      x0, [x19, #0x110]
0x00042a88: e0 02 00 b4              cbz      x0, #0x42ae4
0x00042a8c: 62 03 40 b9              ldr      w2, [x27]
0x00042a90: e1 02 18 8b              add      x1, x23, x24
0x00042a94: 03 b3 ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00042a98: 68 03 40 b9              ldr      w8, [x27]
0x00042a9c: 68 92 00 f9              str      x8, [x19, #0x120]
0x00042aa0: fa 00 00 b4              cbz      x26, #0x42abc
0x00042aa4: 68 06 40 f9              ldr      x8, [x19, #8]
0x00042aa8: 49 07 40 b9              ldr      w9, [x26, #4]
0x00042aac: 08 01 09 8b              add      x8, x8, x9
0x00042ab0: 68 96 00 f9              str      x8, [x19, #0x128]
0x00042ab4: 48 03 40 b9              ldr      w8, [x26]
0x00042ab8: 68 9a 00 f9              str      x8, [x19, #0x130]
0x00042abc: f9 00 00 b4              cbz      x25, #0x42ad8
0x00042ac0: 28 03 40 b9              ldr      w8, [x25]
0x00042ac4: 68 7a 00 f9              str      x8, [x19, #0xf0]
0x00042ac8: 68 06 40 f9              ldr      x8, [x19, #8]
0x00042acc: 29 07 40 b9              ldr      w9, [x25, #4]
0x00042ad0: 08 01 09 8b              add      x8, x8, x9
0x00042ad4: 68 76 00 f9              str      x8, [x19, #0xe8]
0x00042ad8: e0 03 13 aa              mov      x0, x19
0x00042adc: f1 b7 ff 97              bl       #0x30aa0                   ; -> _ZN6BYTENN20ByteNNInternalConfig24CheckFp16AndConvertModelEv@plt
0x00042ae0: ca fe ff 17              b        #0x42608                   ; -> _ZN6BYTENN20EspressoDisassembler11disassembleERNS_20ByteNNInternalConfigE+0x108
0x00042ae4: 20 00 80 52              mov      w0, #1
0x00042ae8: c8 fe ff 17              b        #0x42608                   ; -> _ZN6BYTENN20EspressoDisassembler11disassembleERNS_20ByteNNInternalConfigE+0x108
0x00042aec: 51 b8 ff 97              bl       #0x30c30                   ; -> abort@plt
