; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_extraphoto/native/libmibrain_relighting_jni.so
; target  : Java_com_miui_extraphoto_refocus_manager_MiDualPhotoJni_setBrainRelightingLightSource (0xe9c8)
; start   : 0xe9c8
; size    : 0x88
; bytes   : 0x88   instructions: 34
; generated: 2026-10-23T23:06:02

0x0000e9c8: ff 43 01 d1              sub      sp, sp, #0x50
0x0000e9cc: f5 13 00 f9              str      x21, [sp, #0x20]
0x0000e9d0: f4 4f 03 a9              stp      x20, x19, [sp, #0x30]
0x0000e9d4: fd 7b 04 a9              stp      x29, x30, [sp, #0x40]
0x0000e9d8: fd 03 01 91              add      x29, sp, #0x40
0x0000e9dc: 55 d0 3b d5              mrs      x21, tpidr_el0
0x0000e9e0: a8 16 40 f9              ldr      x8, [x21, #0x28]
0x0000e9e4: e0 03 02 aa              mov      x0, x2
0x0000e9e8: e1 23 00 91              add      x1, sp, #8
0x0000e9ec: f3 03 04 2a              mov      w19, w4
0x0000e9f0: f4 03 03 2a              mov      w20, w3
0x0000e9f4: e8 0f 00 f9              str      x8, [sp, #0x18]
0x0000e9f8: e3 13 02 29              stp      w3, w4, [sp, #0x10]
0x0000e9fc: e5 1b 01 29              stp      w5, w6, [sp, #8]
0x0000ea00: d0 fc ff 97              bl       #0xdd40                    ; -> AILAB_PLI_SetLightSource@plt
0x0000ea04: c1 00 00 f0              adrp     x1, #0x29000
0x0000ea08: c2 00 00 f0              adrp     x2, #0x29000
0x0000ea0c: e5 03 00 aa              mov      x5, x0
0x0000ea10: 21 58 00 91              add      x1, x1, #0x16
0x0000ea14: 42 98 03 91              add      x2, x2, #0xe6
0x0000ea18: e0 07 00 32              mov      w0, #3
0x0000ea1c: e3 03 14 2a              mov      w3, w20
0x0000ea20: e4 03 13 2a              mov      w4, w19
0x0000ea24: e7 fc ff 97              bl       #0xddc0                    ; -> __android_log_print@plt
0x0000ea28: a8 16 40 f9              ldr      x8, [x21, #0x28]
0x0000ea2c: e9 0f 40 f9              ldr      x9, [sp, #0x18]
0x0000ea30: 1f 01 09 eb              cmp      x8, x9
0x0000ea34: c1 00 00 54              b.ne     #0xea4c
0x0000ea38: fd 7b 44 a9              ldp      x29, x30, [sp, #0x40]
0x0000ea3c: f4 4f 43 a9              ldp      x20, x19, [sp, #0x30]
0x0000ea40: f5 13 40 f9              ldr      x21, [sp, #0x20]
0x0000ea44: ff 43 01 91              add      sp, sp, #0x50
0x0000ea48: c0 03 5f d6              ret      
0x0000ea4c: b9 fc ff 97              bl       #0xdd30                    ; -> __stack_chk_fail@plt
