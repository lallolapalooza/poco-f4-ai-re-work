; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_musically/native/libeffect.so
; target  : address 0x2eabd4
; start   : 0x2eabd4
; size    : 0x94
; bytes   : 0x94   instructions: 37
; generated: 2026-10-24T01:20:13

0x002eabd4: ff c3 02 d1              sub      sp, sp, #0xb0
0x002eabd8: e8 3b 00 fd              str      d8, [sp, #0x70]
0x002eabdc: f7 5b 08 a9              stp      x23, x22, [sp, #0x80]
0x002eabe0: f5 53 09 a9              stp      x21, x20, [sp, #0x90]
0x002eabe4: f3 7b 0a a9              stp      x19, x30, [sp, #0xa0]
0x002eabe8: 01 ac 1f 94              bl       #0xad5bec
0x002eabec: e0 03 02 aa              mov      x0, x2
0x002eabf0: e1 03 04 2a              mov      w1, w4
0x002eabf4: e2 03 05 2a              mov      w2, w5
0x002eabf8: 08 1c a0 4e              mov      v8.16b, v0.16b
0x002eabfc: f3 03 05 2a              mov      w19, w5
0x002eac00: 41 77 1f 94              bl       #0xac8904
0x002eac04: e8 37 00 f9              str      x8, [sp, #0x68]
0x002eac08: a2 6c ff 97              bl       #0x2c5e90                  ; -> bef_effect_set_width_height@plt
0x002eac0c: 93 e9 1e 94              bl       #0xaa5258
0x002eac10: e1 03 00 91              mov      x1, sp
0x002eac14: e3 c3 00 91              add      x3, sp, #0x30
0x002eac18: 6b 40 1f 94              bl       #0xabadc4
0x002eac1c: ff 33 00 f9              str      xzr, [sp, #0x60]
0x002eac20: e0 83 01 ad              stp      q0, q0, [sp, #0x30]
0x002eac24: e0 17 80 3d              str      q0, [sp, #0x50]
0x002eac28: e8 1b 00 fd              str      d8, [sp, #0x30]
0x002eac2c: e8 e3 00 39              strb     w8, [sp, #0x38]
0x002eac30: e0 03 00 ad              stp      q0, q0, [sp]
0x002eac34: e0 0b 80 3d              str      q0, [sp, #0x20]
0x002eac38: f5 53 00 29              stp      w21, w20, [sp]
0x002eac3c: f3 0b 00 b9              str      w19, [sp, #8]
0x002eac40: 98 6c ff 97              bl       #0x2c5ea0                  ; -> bef_effect_algorithm_multi_texture_with_params@plt
0x002eac44: e8 16 40 f9              ldr      x8, [x23, #0x28]
0x002eac48: ff 87 1e 94              bl       #0xa8cc44
0x002eac4c: c1 00 00 54              b.ne     #0x2eac64
0x002eac50: f3 7b 4a a9              ldp      x19, x30, [sp, #0xa0]
0x002eac54: f5 53 49 a9              ldp      x21, x20, [sp, #0x90]
0x002eac58: f7 5b 48 a9              ldp      x23, x22, [sp, #0x80]
0x002eac5c: e8 3b 40 fd              ldr      d8, [sp, #0x70]
0x002eac60: 44 8c 1e 14              b        #0xa8dd70
0x002eac64: f3 69 ff 97              bl       #0x2c5430                  ; -> __stack_chk_fail@plt
