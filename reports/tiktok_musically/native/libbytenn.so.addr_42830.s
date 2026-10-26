; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_musically/native/libbytenn.so
; target  : address 0x42830
; start   : 0x42830
; size    : 0xa0
; bytes   : 0xa0   instructions: 40
; generated: 2026-10-23T23:51:51

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
