; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/kwai_live/inventory/libs/lib/arm64-v8a/libFaceMagic.so
; target  : _ZN2FM15FMLiquifyEffect7forwardERKN3CGE3VecIfLi2EEES5_S5_ffbb (0x3cfb20)
; start   : 0x3cfb20
; size    : 0x8c
; bytes   : 0x8c   instructions: 35
; generated: 2026-10-23T23:35:25

0x003cfb20: f5 0f 1d f8              str      x21, [sp, #-0x30]!
0x003cfb24: f4 4f 01 a9              stp      x20, x19, [sp, #0x10]
0x003cfb28: fd 7b 02 a9              stp      x29, x30, [sp, #0x20]
0x003cfb2c: fd 83 00 91              add      x29, sp, #0x20
0x003cfb30: f3 03 05 2a              mov      w19, w5
0x003cfb34: 23 1c a1 4e              mov      v3.16b, v1.16b
0x003cfb38: 02 1c a0 4e              mov      v2.16b, v0.16b
0x003cfb3c: f5 03 02 aa              mov      x21, x2
0x003cfb40: f4 03 00 aa              mov      x20, x0
0x003cfb44: a4 00 00 36              tbz      w4, #0, #0x3cfb58
0x003cfb48: a8 02 40 b9              ldr      w8, [x21]
0x003cfb4c: 88 56 00 b9              str      w8, [x20, #0x54]
0x003cfb50: a8 06 40 b9              ldr      w8, [x21, #4]
0x003cfb54: 88 5a 00 b9              str      w8, [x20, #0x58]
0x003cfb58: 80 32 40 f9              ldr      x0, [x20, #0x60]
0x003cfb5c: 20 04 40 2d              ldp      s0, s1, [x1]
0x003cfb60: 81 52 01 91              add      x1, x20, #0x54
0x003cfb64: e2 03 15 aa              mov      x2, x21
0x003cfb68: 08 00 40 f9              ldr      x8, [x0]
0x003cfb6c: 08 41 40 f9              ldr      x8, [x8, #0x80]
0x003cfb70: 00 01 3f d6              blr      x8
0x003cfb74: a8 02 40 b9              ldr      w8, [x21]
0x003cfb78: 88 56 00 b9              str      w8, [x20, #0x54]
0x003cfb7c: a8 06 40 b9              ldr      w8, [x21, #4]
0x003cfb80: 88 5a 00 b9              str      w8, [x20, #0x58]
0x003cfb84: d3 00 00 36              tbz      w19, #0, #0x3cfb9c
0x003cfb88: 80 32 40 f9              ldr      x0, [x20, #0x60]
0x003cfb8c: fd 7b 42 a9              ldp      x29, x30, [sp, #0x20]
0x003cfb90: f4 4f 41 a9              ldp      x20, x19, [sp, #0x10]
0x003cfb94: f5 07 43 f8              ldr      x21, [sp], #0x30
0x003cfb98: ba 45 f5 17              b        #0x121280                  ; -> _ZN3CGE16CGELiquifyFilter8pushMeshEv@plt
0x003cfb9c: fd 7b 42 a9              ldp      x29, x30, [sp, #0x20]
0x003cfba0: f4 4f 41 a9              ldp      x20, x19, [sp, #0x10]
0x003cfba4: f5 07 43 f8              ldr      x21, [sp], #0x30
0x003cfba8: c0 03 5f d6              ret      
