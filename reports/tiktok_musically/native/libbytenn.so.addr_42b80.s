; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_musically/native/libbytenn.so
; target  : address 0x42b80
; start   : 0x42b80
; size    : 0xa0
; bytes   : 0xa0   instructions: 40
; generated: 2026-10-23T23:51:52

0x00042b80: ff 1f 00 f9              str      xzr, [sp, #0x38]
0x00042b84: 55 03 08 8b              add      x21, x26, x8
0x00042b88: e0 03 15 aa              mov      x0, x21
0x00042b8c: 11 b5 ff 97              bl       #0x2ffd0                   ; -> strlen@plt
0x00042b90: 1f 40 00 b1              cmn      x0, #0x10
0x00042b94: 02 44 00 54              b.hs     #0x43414
0x00042b98: f4 03 00 aa              mov      x20, x0
0x00042b9c: 1f 5c 00 f1              cmp      x0, #0x17
0x00042ba0: c2 08 00 54              b.hs     #0x42cb8
0x00042ba4: e9 e3 00 91              add      x9, sp, #0x38
0x00042ba8: 88 7a 1f 53              lsl      w8, w20, #1
0x00042bac: 36 01 40 b2              orr      x22, x9, #1
0x00042bb0: e8 e3 00 39              strb     w8, [sp, #0x38]
0x00042bb4: 34 09 00 b5              cbnz     x20, #0x42cd8
0x00042bb8: 4c 00 00 14              b        #0x42ce8                   ; -> _ZN6BYTENN20EspressoDisassembler22disassembleModelBufferERNS_23ByteNNInternalConfigExtE+0x1f8
0x00042bbc: 01 0a 00 b0              adrp     x1, #0x183000
0x00042bc0: 02 0a 00 d0              adrp     x2, #0x184000
0x00042bc4: 21 e0 21 91              add      x1, x1, #0x878
0x00042bc8: 42 38 2c 91              add      x2, x2, #0xb0e
0x00042bcc: 20 00 80 52              mov      w0, #1
0x00042bd0: 84 b9 ff 97              bl       #0x311e0                   ; -> _ZN6BYTENN9ByteNNLogEiPKcS1_z@plt
0x00042bd4: 55 01 80 52              mov      w21, #0xa
0x00042bd8: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x00042bdc: e9 2f 40 f9              ldr      x9, [sp, #0x58]
0x00042be0: 1f 01 09 eb              cmp      x8, x9
0x00042be4: 61 41 00 54              b.ne     #0x43410
0x00042be8: e0 03 15 2a              mov      w0, w21
0x00042bec: fd 7b 4b a9              ldp      x29, x30, [sp, #0xb0]
0x00042bf0: f4 4f 4a a9              ldp      x20, x19, [sp, #0xa0]
0x00042bf4: f6 57 49 a9              ldp      x22, x21, [sp, #0x90]
0x00042bf8: f8 5f 48 a9              ldp      x24, x23, [sp, #0x80]
0x00042bfc: fa 67 47 a9              ldp      x26, x25, [sp, #0x70]
0x00042c00: fc 6f 46 a9              ldp      x28, x27, [sp, #0x60]
0x00042c04: ff 03 03 91              add      sp, sp, #0xc0
0x00042c08: c0 03 5f d6              ret      
0x00042c0c: c8 0a 40 b9              ldr      w8, [x22, #8]
0x00042c10: 49 93 00 91              add      x9, x26, #0x24
0x00042c14: 4a b3 00 91              add      x10, x26, #0x2c
0x00042c18: 4b d3 00 91              add      x11, x26, #0x34
0x00042c1c: 1f 0d 00 71              cmp      w8, #3
