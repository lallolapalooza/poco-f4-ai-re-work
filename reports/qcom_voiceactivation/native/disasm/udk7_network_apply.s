; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libqvasml.so
; target  : address 0x5c8b0
; start   : 0x5c8b0
; size    : 0x2b4
; bytes   : 0x2b4   instructions: 173
; generated: 2026-10-23T23:25:25

0x0005c8b0: 3f 23 03 d5              paciasp  
0x0005c8b4: fd 7b be a9              stp      x29, x30, [sp, #-0x20]!
0x0005c8b8: f4 4f 01 a9              stp      x20, x19, [sp, #0x10]
0x0005c8bc: fd 03 00 91              mov      x29, sp
0x0005c8c0: f3 03 00 aa              mov      x19, x0
0x0005c8c4: 08 b4 40 f9              ldr      x8, [x0, #0x168]
0x0005c8c8: 00 6c 40 f9              ldr      x0, [x0, #0xd8]
0x0005c8cc: 00 01 3f d6              blr      x8
0x0005c8d0: c0 03 00 35              cbnz     w0, #0x5c948
0x0005c8d4: 68 72 40 f9              ldr      x8, [x19, #0xe0]
0x0005c8d8: 61 00 80 52              mov      w1, #3
0x0005c8dc: 1f 01 00 b9              str      wzr, [x8]
0x0005c8e0: 68 72 40 f9              ldr      x8, [x19, #0xe0]
0x0005c8e4: 1f 05 00 b9              str      wzr, [x8, #4]
0x0005c8e8: 60 8a 4d a9              ldp      x0, x2, [x19, #0xd8]
0x0005c8ec: 68 c2 40 f9              ldr      x8, [x19, #0x180]
0x0005c8f0: 00 01 3f d6              blr      x8
0x0005c8f4: 80 00 00 34              cbz      w0, #0x5c904
0x0005c8f8: a2 fd ff 90              adrp     x2, #0x10000
0x0005c8fc: 42 74 01 91              add      x2, x2, #0x5d
0x0005c900: 0e 00 00 14              b        #0x5c938                   ; -> udk7_network_apply+0x88
0x0005c904: 68 76 40 f9              ldr      x8, [x19, #0xe8]
0x0005c908: 61 00 80 52              mov      w1, #3
0x0005c90c: 1f 01 00 b9              str      wzr, [x8]
0x0005c910: 28 00 80 52              mov      w8, #1
0x0005c914: 69 76 40 f9              ldr      x9, [x19, #0xe8]
0x0005c918: 28 05 00 b9              str      w8, [x9, #4]
0x0005c91c: 68 c2 40 f9              ldr      x8, [x19, #0x180]
0x0005c920: 60 6e 40 f9              ldr      x0, [x19, #0xd8]
0x0005c924: 62 76 40 f9              ldr      x2, [x19, #0xe8]
0x0005c928: 00 01 3f d6              blr      x8
0x0005c92c: 80 01 00 34              cbz      w0, #0x5c95c
0x0005c930: a2 fd ff f0              adrp     x2, #0x13000
0x0005c934: 42 bc 21 91              add      x2, x2, #0x86f
0x0005c938: 1f 20 03 d5              nop      
0x0005c93c: 81 26 dc 10              adr      x1, #0x14e0c
0x0005c940: c0 00 80 52              mov      w0, #6
0x0005c944: 55 d4 02 94              bl       #0x111a98
0x0005c948: 20 00 80 52              mov      w0, #1
0x0005c94c: f4 4f 41 a9              ldp      x20, x19, [sp, #0x10]
0x0005c950: fd 7b c2 a8              ldp      x29, x30, [sp], #0x20
0x0005c954: bf 23 03 d5              autiasp  
0x0005c958: c0 03 5f d6              ret      
0x0005c95c: 68 b6 40 f9              ldr      x8, [x19, #0x168]
0x0005c960: 60 7a 40 f9              ldr      x0, [x19, #0xf0]
0x0005c964: 00 01 3f d6              blr      x8
0x0005c968: 00 ff ff 35              cbnz     w0, #0x5c948
0x0005c96c: 68 7e 40 f9              ldr      x8, [x19, #0xf8]
0x0005c970: 61 00 80 52              mov      w1, #3
0x0005c974: 1f 01 00 b9              str      wzr, [x8]
0x0005c978: 68 7e 40 f9              ldr      x8, [x19, #0xf8]
0x0005c97c: 1f 05 00 b9              str      wzr, [x8, #4]
0x0005c980: 60 0a 4f a9              ldp      x0, x2, [x19, #0xf0]
0x0005c984: 68 c2 40 f9              ldr      x8, [x19, #0x180]
0x0005c988: 00 01 3f d6              blr      x8
0x0005c98c: 80 00 00 34              cbz      w0, #0x5c99c
0x0005c990: a2 fd ff b0              adrp     x2, #0x11000
0x0005c994: 42 30 30 91              add      x2, x2, #0xc0c
0x0005c998: e8 ff ff 17              b        #0x5c938                   ; -> udk7_network_apply+0x88
0x0005c99c: 68 82 40 f9              ldr      x8, [x19, #0x100]
0x0005c9a0: 61 00 80 52              mov      w1, #3
0x0005c9a4: 1f 01 00 b9              str      wzr, [x8]
0x0005c9a8: 28 00 80 52              mov      w8, #1
0x0005c9ac: 69 82 40 f9              ldr      x9, [x19, #0x100]
0x0005c9b0: 28 05 00 b9              str      w8, [x9, #4]
0x0005c9b4: 68 c2 40 f9              ldr      x8, [x19, #0x180]
0x0005c9b8: 60 7a 40 f9              ldr      x0, [x19, #0xf0]
0x0005c9bc: 62 82 40 f9              ldr      x2, [x19, #0x100]
0x0005c9c0: 00 01 3f d6              blr      x8
0x0005c9c4: 80 00 00 34              cbz      w0, #0x5c9d4
0x0005c9c8: c2 fd ff d0              adrp     x2, #0x16000
0x0005c9cc: 42 d4 00 91              add      x2, x2, #0x35
0x0005c9d0: da ff ff 17              b        #0x5c938                   ; -> udk7_network_apply+0x88
0x0005c9d4: 68 b6 40 f9              ldr      x8, [x19, #0x168]
0x0005c9d8: 60 86 40 f9              ldr      x0, [x19, #0x108]
0x0005c9dc: 00 01 3f d6              blr      x8
0x0005c9e0: 40 fb ff 35              cbnz     w0, #0x5c948
0x0005c9e4: 68 8a 40 f9              ldr      x8, [x19, #0x110]
0x0005c9e8: 61 00 80 52              mov      w1, #3
0x0005c9ec: 1f 01 00 b9              str      wzr, [x8]
0x0005c9f0: 68 8a 40 f9              ldr      x8, [x19, #0x110]
0x0005c9f4: 1f 05 00 b9              str      wzr, [x8, #4]
0x0005c9f8: 60 8a 50 a9              ldp      x0, x2, [x19, #0x108]
0x0005c9fc: 68 c2 40 f9              ldr      x8, [x19, #0x180]
0x0005ca00: 00 01 3f d6              blr      x8
0x0005ca04: 80 00 00 34              cbz      w0, #0x5ca14
0x0005ca08: a2 fd ff 90              adrp     x2, #0x10000
0x0005ca0c: 42 a4 0e 91              add      x2, x2, #0x3a9
0x0005ca10: ca ff ff 17              b        #0x5c938                   ; -> udk7_network_apply+0x88
0x0005ca14: 68 8e 40 f9              ldr      x8, [x19, #0x118]
0x0005ca18: 29 00 80 52              mov      w9, #1
0x0005ca1c: 61 00 80 52              mov      w1, #3
0x0005ca20: 09 01 00 b9              str      w9, [x8]
0x0005ca24: 68 8e 40 f9              ldr      x8, [x19, #0x118]
0x0005ca28: 1f 05 00 b9              str      wzr, [x8, #4]
0x0005ca2c: 68 c2 40 f9              ldr      x8, [x19, #0x180]
0x0005ca30: 60 86 40 f9              ldr      x0, [x19, #0x108]
0x0005ca34: 62 8e 40 f9              ldr      x2, [x19, #0x118]
0x0005ca38: 00 01 3f d6              blr      x8
0x0005ca3c: 80 00 00 34              cbz      w0, #0x5ca4c
0x0005ca40: c2 fd ff d0              adrp     x2, #0x16000
0x0005ca44: 42 f4 1e 91              add      x2, x2, #0x7bd
0x0005ca48: bc ff ff 17              b        #0x5c938                   ; -> udk7_network_apply+0x88
0x0005ca4c: 68 92 40 f9              ldr      x8, [x19, #0x120]
0x0005ca50: 49 00 80 52              mov      w9, #2
0x0005ca54: 61 00 80 52              mov      w1, #3
0x0005ca58: 74 00 80 52              mov      w20, #3
0x0005ca5c: 09 01 00 b9              str      w9, [x8]
0x0005ca60: 68 92 40 f9              ldr      x8, [x19, #0x120]
0x0005ca64: 1f 05 00 b9              str      wzr, [x8, #4]
0x0005ca68: 68 c2 40 f9              ldr      x8, [x19, #0x180]
0x0005ca6c: 60 86 40 f9              ldr      x0, [x19, #0x108]
0x0005ca70: 62 92 40 f9              ldr      x2, [x19, #0x120]
0x0005ca74: 00 01 3f d6              blr      x8
0x0005ca78: 80 00 00 34              cbz      w0, #0x5ca88
0x0005ca7c: c2 fd ff 90              adrp     x2, #0x14000
0x0005ca80: 42 6c 38 91              add      x2, x2, #0xe1b
0x0005ca84: ad ff ff 17              b        #0x5c938                   ; -> udk7_network_apply+0x88
0x0005ca88: 68 96 40 f9              ldr      x8, [x19, #0x128]
0x0005ca8c: 61 00 80 52              mov      w1, #3
0x0005ca90: 14 01 00 b9              str      w20, [x8]
0x0005ca94: 68 96 40 f9              ldr      x8, [x19, #0x128]
0x0005ca98: 1f 05 00 b9              str      wzr, [x8, #4]
0x0005ca9c: 68 c2 40 f9              ldr      x8, [x19, #0x180]
0x0005caa0: 60 86 40 f9              ldr      x0, [x19, #0x108]
0x0005caa4: 62 96 40 f9              ldr      x2, [x19, #0x128]
0x0005caa8: 00 01 3f d6              blr      x8
0x0005caac: 80 00 00 34              cbz      w0, #0x5cabc
0x0005cab0: a2 fd ff 90              adrp     x2, #0x10000
0x0005cab4: 42 3c 1b 91              add      x2, x2, #0x6cf
0x0005cab8: a0 ff ff 17              b        #0x5c938                   ; -> udk7_network_apply+0x88
0x0005cabc: 68 9a 40 f9              ldr      x8, [x19, #0x130]
0x0005cac0: 89 00 80 52              mov      w9, #4
0x0005cac4: 61 00 80 52              mov      w1, #3
0x0005cac8: 09 01 00 b9              str      w9, [x8]
0x0005cacc: 68 9a 40 f9              ldr      x8, [x19, #0x130]
0x0005cad0: 1f 05 00 b9              str      wzr, [x8, #4]
0x0005cad4: 68 c2 40 f9              ldr      x8, [x19, #0x180]
0x0005cad8: 60 86 40 f9              ldr      x0, [x19, #0x108]
0x0005cadc: 62 9a 40 f9              ldr      x2, [x19, #0x130]
0x0005cae0: 00 01 3f d6              blr      x8
0x0005cae4: 80 00 00 34              cbz      w0, #0x5caf4
0x0005cae8: c2 fd ff b0              adrp     x2, #0x15000
0x0005caec: 42 2c 32 91              add      x2, x2, #0xc8b
0x0005caf0: 92 ff ff 17              b        #0x5c938                   ; -> udk7_network_apply+0x88
0x0005caf4: 68 9e 40 f9              ldr      x8, [x19, #0x138]
0x0005caf8: a9 00 80 52              mov      w9, #5
0x0005cafc: 61 00 80 52              mov      w1, #3
0x0005cb00: 09 01 00 b9              str      w9, [x8]
0x0005cb04: 68 9e 40 f9              ldr      x8, [x19, #0x138]
0x0005cb08: 1f 05 00 b9              str      wzr, [x8, #4]
0x0005cb0c: 68 c2 40 f9              ldr      x8, [x19, #0x180]
0x0005cb10: 60 86 40 f9              ldr      x0, [x19, #0x108]
0x0005cb14: 62 9e 40 f9              ldr      x2, [x19, #0x138]
0x0005cb18: 00 01 3f d6              blr      x8
0x0005cb1c: 80 00 00 34              cbz      w0, #0x5cb2c
0x0005cb20: a2 fd ff d0              adrp     x2, #0x12000
0x0005cb24: 42 10 3b 91              add      x2, x2, #0xec4
0x0005cb28: 84 ff ff 17              b        #0x5c938                   ; -> udk7_network_apply+0x88
0x0005cb2c: 68 a2 40 f9              ldr      x8, [x19, #0x140]
0x0005cb30: 61 00 80 52              mov      w1, #3
0x0005cb34: 1f 01 00 b9              str      wzr, [x8]
0x0005cb38: 28 00 80 52              mov      w8, #1
0x0005cb3c: 69 a2 40 f9              ldr      x9, [x19, #0x140]
0x0005cb40: 28 05 00 b9              str      w8, [x9, #4]
0x0005cb44: 68 c2 40 f9              ldr      x8, [x19, #0x180]
0x0005cb48: 60 86 40 f9              ldr      x0, [x19, #0x108]
0x0005cb4c: 62 a2 40 f9              ldr      x2, [x19, #0x140]
0x0005cb50: 00 01 3f d6              blr      x8
0x0005cb54: c0 ef ff 34              cbz      w0, #0x5c94c
0x0005cb58: c2 fd ff 90              adrp     x2, #0x14000
0x0005cb5c: 42 14 16 91              add      x2, x2, #0x585
0x0005cb60: 76 ff ff 17              b        #0x5c938                   ; -> udk7_network_apply+0x88
