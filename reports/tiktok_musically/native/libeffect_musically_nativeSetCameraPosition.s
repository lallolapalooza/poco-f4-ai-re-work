; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_musically/native/libeffect.so
; target  : address 0x2ead0c
; start   : 0x2ead0c
; size    : 0x44
; bytes   : 0x44   instructions: 17
; generated: 2026-10-24T01:20:10

0x002ead0c: f4 0f 1e f8              str      x20, [sp, #-0x20]!
0x002ead10: f3 7b 01 a9              stp      x19, x30, [sp, #0x10]
0x002ead14: e0 03 02 aa              mov      x0, x2
0x002ead18: e1 03 1f 2a              mov      w1, wzr
0x002ead1c: aa a1 1f 94              bl       #0xad33c4
0x002ead20: 6c 6c ff 97              bl       #0x2c5ed0                  ; -> bef_effect_set_orientation@plt
0x002ead24: 13 a8 1e 94              bl       #0xa94d70
0x002ead28: 6e 6c ff 97              bl       #0x2c5ee0                  ; -> bef_effect_set_frame_device_orientation@plt
0x002ead2c: 7f 1e 00 72              tst      w19, #0xff
0x002ead30: f3 7b 41 a9              ldp      x19, x30, [sp, #0x10]
0x002ead34: e1 17 9f 1a              cset     w1, eq
0x002ead38: e0 03 14 aa              mov      x0, x20
0x002ead3c: f4 07 42 f8              ldr      x20, [sp], #0x20
0x002ead40: 6c 6c ff 17              b        #0x2c5ef0                  ; -> bef_effect_set_camera_device_position@plt
0x002ead44: e1 03 03 2a              mov      w1, w3
0x002ead48: e0 03 02 aa              mov      x0, x2
0x002ead4c: 61 6c ff 17              b        #0x2c5ed0                  ; -> bef_effect_set_orientation@plt
