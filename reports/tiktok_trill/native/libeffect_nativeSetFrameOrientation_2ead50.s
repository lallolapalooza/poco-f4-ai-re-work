; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_trill/native/libeffect.so
; target  : address 0x2ead50
; start   : 0x2ead50
; size    : 0xc
; bytes   : 0xc   instructions: 3
; generated: 2026-10-24T00:12:22

0x002ead50: e1 03 03 2a              mov      w1, w3
0x002ead54: e0 03 02 aa              mov      x0, x2
0x002ead58: 62 6c ff 17              b        #0x2c5ee0                  ; -> bef_effect_set_frame_device_orientation@plt
