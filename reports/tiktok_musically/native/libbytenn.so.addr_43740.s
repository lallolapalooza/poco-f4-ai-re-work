; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_musically/native/libbytenn.so
; target  : address 0x43740
; start   : 0x43740
; size    : 0xa0
; bytes   : 0xa0   instructions: 40
; generated: 2026-10-23T23:51:54

0x00043740: 6a 81 9f 9a              csel     x10, x11, xzr, hi
0x00043744: 3f 15 00 71              cmp      w9, #5
0x00043748: 96 81 9f 9a              csel     x22, x12, xzr, hi
0x0004374c: 1f 09 00 71              cmp      w8, #2
0x00043750: ea db 03 a9              stp      x10, x22, [sp, #0x38]
0x00043754: f7 2f 00 f9              str      x23, [sp, #0x58]
0x00043758: a0 00 00 54              b.eq     #0x4376c
0x0004375c: 1f 0d 00 71              cmp      w8, #3
0x00043760: 03 08 00 54              b.lo     #0x43860
0x00043764: 88 1e 40 b9              ldr      w8, [x20, #0x1c]
0x00043768: c8 07 00 34              cbz      w8, #0x43860
0x0004376c: 80 a2 41 29              ldp      w0, w8, [x20, #0xc]
0x00043770: 96 02 08 8b              add      x22, x20, x8
0x00043774: 53 b1 ff 97              bl       #0x2fcc0                   ; -> malloc@plt
0x00043778: 82 0e 40 b9              ldr      w2, [x20, #0xc]
0x0004377c: e1 03 16 aa              mov      x1, x22
0x00043780: f8 03 00 aa              mov      x24, x0
0x00043784: c7 af ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00043788: 88 0e 40 b9              ldr      w8, [x20, #0xc]
0x0004378c: 0e 0a 00 b0              adrp     x14, #0x184000
0x00043790: ce c9 2c 91              add      x14, x14, #0xb32
0x00043794: e8 13 00 34              cbz      w8, #0x43a10
0x00043798: 8b 22 40 b9              ldr      w11, [x20, #0x20]
0x0004379c: ea 03 1f aa              mov      x10, xzr
0x000437a0: 1f 81 00 71              cmp      w8, #0x20
0x000437a4: 89 02 0b 8b              add      x9, x20, x11
0x000437a8: e3 0e 00 54              b.lo     #0x43984
0x000437ac: 0c 05 00 d1              sub      x12, x8, #1
0x000437b0: 9f 1d 00 f1              cmp      x12, #7
0x000437b4: 88 0e 00 54              b.hi     #0x43984
0x000437b8: 0a 01 0b 8b              add      x10, x8, x11
0x000437bc: 8a 02 0a 8b              add      x10, x20, x10
0x000437c0: 1f 03 0a eb              cmp      x24, x10
0x000437c4: 62 0c 00 54              b.hs     #0x43950
0x000437c8: 0a 03 08 8b              add      x10, x24, x8
0x000437cc: 3f 01 0a eb              cmp      x9, x10
0x000437d0: 02 0c 00 54              b.hs     #0x43950
0x000437d4: ea 03 1f aa              mov      x10, xzr
0x000437d8: 6b 00 00 14              b        #0x43984                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x56c
0x000437dc: 08 43 00 91              add      x8, x24, #0x10
