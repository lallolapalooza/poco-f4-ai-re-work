; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_musically/native/libbytenn.so
; target  : address 0x42f10
; start   : 0x42f10
; size    : 0x80
; bytes   : 0x80   instructions: 32
; generated: 2026-10-23T23:51:45

0x00042f10: 5f 01 08 eb              cmp      x10, x8
0x00042f14: 20 01 00 54              b.eq     #0x42f38
0x00042f18: 4c 09 40 92              and      x12, x10, #7
0x00042f1c: 8b 6a 6a 38              ldrb     w11, [x20, x10]
0x00042f20: 2c 69 6c 38              ldrb     w12, [x9, x12]
0x00042f24: 8b 01 0b 4a              eor      w11, w12, w11
0x00042f28: 8b 6a 2a 38              strb     w11, [x20, x10]
0x00042f2c: 4a 05 00 91              add      x10, x10, #1
0x00042f30: 1f 01 0a eb              cmp      x8, x10
0x00042f34: 21 ff ff 54              b.ne     #0x42f18
0x00042f38: 49 0f 40 b9              ldr      w9, [x26, #0xc]
0x00042f3c: 3f 05 00 71              cmp      w9, #1
0x00042f40: 6b 03 00 54              b.lt     #0x42fac
0x00042f44: 08 0a 00 d0              adrp     x8, #0x184000
0x00042f48: 3f 05 00 71              cmp      w9, #1
0x00042f4c: 08 c9 2c 91              add      x8, x8, #0xb32
0x00042f50: 61 00 00 54              b.ne     #0x42f5c
0x00042f54: ea 03 1f aa              mov      x10, xzr
0x00042f58: 0e 00 00 14              b        #0x42f90                   ; -> _ZN6BYTENN20EspressoDisassembler22disassembleModelBufferERNS_23ByteNNInternalConfigExtE+0x4a0
0x00042f5c: 2a 79 7f 92              and      x10, x9, #0xfffffffe
0x00042f60: 8b 06 00 91              add      x11, x20, #1
0x00042f64: ec 03 0a aa              mov      x12, x10
0x00042f68: 6d f1 5f 38              ldurb    w13, [x11, #-1]
0x00042f6c: 6e 01 40 39              ldrb     w14, [x11]
0x00042f70: 8c 09 00 f1              subs     x12, x12, #2
0x00042f74: 0d 69 6d 38              ldrb     w13, [x8, x13]
0x00042f78: 0e 69 6e 38              ldrb     w14, [x8, x14]
0x00042f7c: 6d f1 1f 38              sturb    w13, [x11, #-1]
0x00042f80: 6e 25 00 38              strb     w14, [x11], #2
0x00042f84: 21 ff ff 54              b.ne     #0x42f68
0x00042f88: 5f 01 09 eb              cmp      x10, x9
0x00042f8c: 00 01 00 54              b.eq     #0x42fac
