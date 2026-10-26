; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : SoilMode (0x307f8)
; start   : 0x307f8
; size    : 0x2c
; bytes   : 0x2c   instructions: 11
; generated: 2026-10-23T23:43:21

0x000307f8: 08 04 40 f9              ldr      x8, [x0, #8]
0x000307fc: 08 09 40 b9              ldr      w8, [x8, #8]
0x00030800: 08 15 00 51              sub      w8, w8, #5
0x00030804: 1f 09 00 71              cmp      w8, #2
0x00030808: a8 00 00 54              b.hi     #0x3081c
0x0003080c: e9 00 00 f0              adrp     x9, #0x4f000
0x00030810: 29 21 29 91              add      x9, x9, #0xa48
0x00030814: 20 d9 68 b8              ldr      w0, [x9, w8, sxtw #2]
0x00030818: c0 03 5f d6              ret      
0x0003081c: e0 03 1f 2a              mov      w0, wzr
0x00030820: c0 03 5f d6              ret      
