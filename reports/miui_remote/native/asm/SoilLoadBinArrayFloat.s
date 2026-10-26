; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : address 0x308c0
; start   : 0x308c0
; size    : 0x1c
; bytes   : 0x1c   instructions: 7
; generated: 2026-10-23T23:48:03

0x000308c0: 08 04 40 f9              ldr      x8, [x0, #8]
0x000308c4: 09 09 40 f9              ldr      x9, [x8, #0x10]
0x000308c8: 0a 0d 80 b9              ldrsw    x10, [x8, #0xc]
0x000308cc: 20 01 0a 8b              add      x0, x9, x10
0x000308d0: 49 09 01 0b              add      w9, w10, w1, lsl #2
0x000308d4: 09 0d 00 b9              str      w9, [x8, #0xc]
0x000308d8: c0 03 5f d6              ret      
