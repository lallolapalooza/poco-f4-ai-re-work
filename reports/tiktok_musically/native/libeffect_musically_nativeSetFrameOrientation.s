; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_musically/native/libeffect.so
; target  : address 0x2ead50
; start   : 0x2ead50
; size    : 0x40
; bytes   : 0x40   instructions: 16
; generated: 2026-10-24T01:20:09

0x002ead50: e1 03 03 2a              mov      w1, w3
0x002ead54: e0 03 02 aa              mov      x0, x2
0x002ead58: 62 6c ff 17              b        #0x2c5ee0                  ; -> bef_effect_set_frame_device_orientation@plt
0x002ead5c: ff 03 02 d1              sub      sp, sp, #0x80
0x002ead60: e8 0b 00 fd              str      d8, [sp, #0x10]
0x002ead64: e1 03 1e aa              mov      x1, x30
0x002ead68: ed 85 1e 94              bl       #0xa8c51c
0x002ead6c: fe 03 01 aa              mov      x30, x1
0x002ead70: fd 7b 07 a9              stp      x29, x30, [sp, #0x70]
0x002ead74: 08 00 40 f9              ldr      x8, [x0]
0x002ead78: e1 03 04 aa              mov      x1, x4
0x002ead7c: f3 03 06 aa              mov      x19, x6
0x002ead80: f5 03 05 aa              mov      x21, x5
0x002ead84: 08 ad 42 f9              ldr      x8, [x8, #0x558]
0x002ead88: f6 03 04 aa              mov      x22, x4
0x002ead8c: f7 03 03 2a              mov      w23, w3
