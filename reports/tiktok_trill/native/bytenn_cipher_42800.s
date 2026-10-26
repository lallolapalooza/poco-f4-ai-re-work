; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_trill/native/libbytenn.so
; target  : range 0x42800:0x428e0
; start   : 0x42800
; size    : 0xe0
; bytes   : 0xe0   instructions: 56
; generated: 2026-10-23T23:58:40

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
