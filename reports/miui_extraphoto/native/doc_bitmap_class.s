; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/xp/libdoc_photo_process.so
; target  : address 0x178be4
; start   : 0x178be4
; size    : auto (to next symbol/ret)
; bytes   : 0x34   instructions: 13
; generated: 2026-10-24T00:45:08

0x00178be4: f7 06 00 91              add      x23, x23, #1
0x00178be8: ff 02 16 eb              cmp      x23, x22
0x00178bec: 0b ff ff 54              b.lt     #0x178bcc
0x00178bf0: b5 06 18 37              tbnz     w21, #3, #0x178cc4
0x00178bf4: 35 07 20 37              tbnz     w21, #4, #0x178cd8
0x00178bf8: b5 00 28 36              tbz      w21, #5, #0x178c0c
0x00178bfc: 81 f6 40 b9              ldr      w1, [x20, #0xf4]
0x00178c00: c0 0c 80 52              mov      w0, #0x66
0x00178c04: e2 03 13 aa              mov      x2, x19
0x00178c08: f3 8c 00 94              bl       #0x19bfd4
0x00178c0c: 95 ba 40 b9              ldr      w21, [x20, #0xb8]
0x00178c10: f6 03 1f 2a              mov      w22, wzr
0x00178c14: 08 00 00 14              b        #0x178c34
