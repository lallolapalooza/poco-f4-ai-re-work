; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_musically/native/libeffect.so
; target  : address 0x2eac68
; start   : 0x2eac68
; size    : 0x40
; bytes   : 0x40   instructions: 16
; generated: 2026-10-24T01:06:53

0x002eac68: e8 0f 1d fc              str      d8, [sp, #-0x30]!
0x002eac6c: f5 53 01 a9              stp      x21, x20, [sp, #0x10]
0x002eac70: f3 7b 02 a9              stp      x19, x30, [sp, #0x20]
0x002eac74: e1 03 05 2a              mov      w1, w5
0x002eac78: f5 03 02 aa              mov      x21, x2
0x002eac7c: e0 03 02 aa              mov      x0, x2
0x002eac80: e2 03 06 2a              mov      w2, w6
0x002eac84: 08 1c a0 4e              mov      v8.16b, v0.16b
0x002eac88: 13 2b 20 94              bl       #0xaf58d4
0x002eac8c: 81 6c ff 97              bl       #0x2c5e90                  ; -> bef_effect_set_width_height@plt
0x002eac90: 23 d3 1e 94              bl       #0xa9f91c
0x002eac94: f3 7b 42 a9              ldp      x19, x30, [sp, #0x20]
0x002eac98: f5 53 41 a9              ldp      x21, x20, [sp, #0x10]
0x002eac9c: 00 1d a8 4e              mov      v0.16b, v8.16b
0x002eaca0: e8 07 43 fc              ldr      d8, [sp], #0x30
0x002eaca4: 83 6c ff 17              b        #0x2c5eb0                  ; -> bef_effect_process_texture@plt
