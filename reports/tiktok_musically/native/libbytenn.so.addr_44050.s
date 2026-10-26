; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_musically/native/libbytenn.so
; target  : address 0x44050
; start   : 0x44050
; size    : 0x80
; bytes   : 0x80   instructions: 32
; generated: 2026-10-23T23:51:47

0x00044050: 68 00 00 37              tbnz     w8, #0, #0x4405c
0x00044054: df 02 00 79              strh     wzr, [x22]
0x00044058: d9 fe ff 17              b        #0x43bbc                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x7a4
0x0004405c: 08 27 40 f9              ldr      x8, [x24, #0x48]
0x00044060: 1f 01 00 39              strb     wzr, [x8]
0x00044064: 08 e3 40 39              ldrb     w8, [x24, #0x38]
0x00044068: 1f 23 00 f9              str      xzr, [x24, #0x40]
0x0004406c: 88 da 07 36              tbz      w8, #0, #0x43bbc
0x00044070: 00 27 40 f9              ldr      x0, [x24, #0x48]
0x00044074: 0b ae ff 97              bl       #0x2f8a0                   ; -> _ZdlPv@plt
0x00044078: 1f 1f 00 f9              str      xzr, [x24, #0x38]
0x0004407c: d0 fe ff 17              b        #0x43bbc                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x7a4
0x00044080: e1 09 00 f0              adrp     x1, #0x183000
0x00044084: 02 0a 00 90              adrp     x2, #0x184000
0x00044088: 21 e0 21 91              add      x1, x1, #0x878
0x0004408c: 42 38 2c 91              add      x2, x2, #0xb0e
0x00044090: 20 00 80 52              mov      w0, #1
0x00044094: 53 b4 ff 97              bl       #0x311e0                   ; -> _ZN6BYTENN9ByteNNLogEiPKcS1_z@plt
0x00044098: 5b 01 80 52              mov      w27, #0xa
0x0004409c: 02 00 00 14              b        #0x440a4                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0xc8c
0x000440a0: fb 03 1f 2a              mov      w27, wzr
0x000440a4: 88 17 40 f9              ldr      x8, [x28, #0x28]
0x000440a8: a9 03 5a f8              ldur     x9, [x29, #-0x60]
0x000440ac: 1f 01 09 eb              cmp      x8, x9
0x000440b0: 61 01 00 54              b.ne     #0x440dc
0x000440b4: e0 03 1b 2a              mov      w0, w27
0x000440b8: fd 7b 52 a9              ldp      x29, x30, [sp, #0x120]
0x000440bc: f4 4f 51 a9              ldp      x20, x19, [sp, #0x110]
0x000440c0: f6 57 50 a9              ldp      x22, x21, [sp, #0x100]
0x000440c4: f8 5f 4f a9              ldp      x24, x23, [sp, #0xf0]
0x000440c8: fa 67 4e a9              ldp      x26, x25, [sp, #0xe0]
0x000440cc: fc 6f 4d a9              ldp      x28, x27, [sp, #0xd0]
