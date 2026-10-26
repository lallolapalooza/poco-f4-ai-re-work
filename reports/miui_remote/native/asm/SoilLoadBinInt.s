; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : SoilLoadBinInt (0x30824)
; start   : 0x30824
; size    : 0x1c
; bytes   : 0x1c   instructions: 7
; generated: 2026-10-23T23:43:24

0x00030824: 08 04 40 f9              ldr      x8, [x0, #8]
0x00030828: 09 09 40 f9              ldr      x9, [x8, #0x10]
0x0003082c: 0a 0d 80 b9              ldrsw    x10, [x8, #0xc]
0x00030830: 20 69 6a b8              ldr      w0, [x9, x10]
0x00030834: 49 11 00 11              add      w9, w10, #4
0x00030838: 09 0d 00 b9              str      w9, [x8, #0xc]
0x0003083c: c0 03 5f d6              ret      
