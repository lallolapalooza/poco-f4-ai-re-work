; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/kwai_live/inventory/libs/lib/arm64-v8a/libFaceMagic.so
; target  : _ZN2FM15FMLiquifyEffect7restoreERKN3CGE3VecIfLi2EEES5_ff (0x3cfbac)
; start   : 0x3cfbac
; size    : 0x40
; bytes   : 0x40   instructions: 16
; generated: 2026-10-23T23:35:29

0x003cfbac: f3 0f 1e f8              str      x19, [sp, #-0x20]!
0x003cfbb0: fd 7b 01 a9              stp      x29, x30, [sp, #0x10]
0x003cfbb4: fd 43 00 91              add      x29, sp, #0x10
0x003cfbb8: f3 03 00 aa              mov      x19, x0
0x003cfbbc: 00 30 40 f9              ldr      x0, [x0, #0x60]
0x003cfbc0: 23 1c a1 4e              mov      v3.16b, v1.16b
0x003cfbc4: 02 1c a0 4e              mov      v2.16b, v0.16b
0x003cfbc8: 20 04 40 2d              ldp      s0, s1, [x1]
0x003cfbcc: 08 00 40 f9              ldr      x8, [x0]
0x003cfbd0: e1 03 02 aa              mov      x1, x2
0x003cfbd4: 08 49 40 f9              ldr      x8, [x8, #0x90]
0x003cfbd8: 00 01 3f d6              blr      x8
0x003cfbdc: 60 32 40 f9              ldr      x0, [x19, #0x60]
0x003cfbe0: fd 7b 41 a9              ldp      x29, x30, [sp, #0x10]
0x003cfbe4: f3 07 42 f8              ldr      x19, [sp], #0x20
0x003cfbe8: a6 45 f5 17              b        #0x121280                  ; -> _ZN3CGE16CGELiquifyFilter8pushMeshEv@plt
