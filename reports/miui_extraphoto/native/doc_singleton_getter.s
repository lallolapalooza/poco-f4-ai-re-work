; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/xp/libdoc_photo_process.so
; target  : address 0x181c0c
; start   : 0x181c0c
; size    : auto (to next symbol/ret)
; bytes   : 0x2c   instructions: 11
; generated: 2026-10-24T00:43:26

0x00181c0c: 28 0d 40 f9              ldr      x8, [x9, #0x18]
0x00181c10: 29 11 40 f9              ldr      x9, [x9, #0x20]
0x00181c14: e8 a7 00 a9              stp      x8, x9, [sp, #8]
0x00181c18: 09 01 00 b4              cbz      x9, #0x181c38
0x00181c1c: 29 21 00 91              add      x9, x9, #8
0x00181c20: 2a 7d 5f c8              ldxr     x10, [x9]
0x00181c24: 4a 05 00 91              add      x10, x10, #1
0x00181c28: 2a 7d 0b c8              stxr     w11, x10, [x9]
0x00181c2c: ab ff ff 35              cbnz     w11, #0x181c20
0x00181c30: ea 0b 40 f9              ldr      x10, [sp, #0x10]
0x00181c34: 02 00 00 14              b        #0x181c3c
