; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/kwai_live/inventory/libs/lib/arm64-v8a/libFaceMagic.so
; target  : _ZN2FM15FMLiquifyEffect10updateMeshERKN3CGE3VecIfLi2EEEf (0x3cfae0)
; start   : 0x3cfae0
; size    : 0x40
; bytes   : 0x40   instructions: 16
; generated: 2026-10-23T23:35:26

0x003cfae0: f3 0f 1e f8              str      x19, [sp, #-0x20]!
0x003cfae4: fd 7b 01 a9              stp      x29, x30, [sp, #0x10]
0x003cfae8: fd 43 00 91              add      x29, sp, #0x10
0x003cfaec: f3 03 00 aa              mov      x19, x0
0x003cfaf0: 00 30 40 f9              ldr      x0, [x0, #0x60]
0x003cfaf4: 00 01 00 b4              cbz      x0, #0x3cfb14
0x003cfaf8: 02 1c a0 4e              mov      v2.16b, v0.16b
0x003cfafc: 20 04 40 2d              ldp      s0, s1, [x1]
0x003cfb00: 10 3f f5 97              bl       #0x11f740                  ; -> _ZN3CGE16CGELiquifyFilter12initWithMeshEfff@plt
0x003cfb04: 60 32 40 f9              ldr      x0, [x19, #0x60]
0x003cfb08: fd 7b 41 a9              ldp      x29, x30, [sp, #0x10]
0x003cfb0c: f3 07 42 f8              ldr      x19, [sp], #0x20
0x003cfb10: dc 45 f5 17              b        #0x121280                  ; -> _ZN3CGE16CGELiquifyFilter8pushMeshEv@plt
0x003cfb14: fd 7b 41 a9              ldp      x29, x30, [sp, #0x10]
0x003cfb18: f3 07 42 f8              ldr      x19, [sp], #0x20
0x003cfb1c: c0 03 5f d6              ret      
