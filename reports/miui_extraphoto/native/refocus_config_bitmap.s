; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_extraphoto/native/librefocus.so
; target  : Java_com_miui_extraphoto_refocus_manager_DualPhotoJni_configBitmapByNativeImage (0xfb10)
; start   : 0xfb10
; size    : 0x8c
; bytes   : 0x8c   instructions: 35
; generated: 2026-10-24T00:50:24

0x0000fb10: ff 03 01 d1              sub      sp, sp, #0x40
0x0000fb14: f6 57 01 a9              stp      x22, x21, [sp, #0x10]
0x0000fb18: f4 4f 02 a9              stp      x20, x19, [sp, #0x20]
0x0000fb1c: fd 7b 03 a9              stp      x29, x30, [sp, #0x30]
0x0000fb20: fd c3 00 91              add      x29, sp, #0x30
0x0000fb24: 56 d0 3b d5              mrs      x22, tpidr_el0
0x0000fb28: c8 16 40 f9              ldr      x8, [x22, #0x28]
0x0000fb2c: f4 03 00 aa              mov      x20, x0
0x0000fb30: e0 03 03 aa              mov      x0, x3
0x0000fb34: f3 03 02 aa              mov      x19, x2
0x0000fb38: e8 07 00 f9              str      x8, [sp, #8]
0x0000fb3c: 49 fc ff 97              bl       #0xec60                    ; -> _Z17getImageByPointerl@plt
0x0000fb40: f5 03 00 aa              mov      x21, x0
0x0000fb44: e2 03 00 91              mov      x2, sp
0x0000fb48: e0 03 14 aa              mov      x0, x20
0x0000fb4c: e1 03 13 aa              mov      x1, x19
0x0000fb50: ff 03 00 f9              str      xzr, [sp]
0x0000fb54: 33 fb ff 97              bl       #0xe820                    ; -> _Z8getPixelP7_JNIEnvP8_jobjectPPi@plt
0x0000fb58: e0 03 40 f9              ldr      x0, [sp]
0x0000fb5c: a1 0a 40 f9              ldr      x1, [x21, #0x10]
0x0000fb60: a2 8e 40 29              ldp      w2, w3, [x21, #4]
0x0000fb64: 3b fc ff 97              bl       #0xec50                    ; -> _Z15encodeyuvToRGBAPiPhii@plt
0x0000fb68: e0 03 14 aa              mov      x0, x20
0x0000fb6c: e1 03 13 aa              mov      x1, x19
0x0000fb70: a0 fb ff 97              bl       #0xe9f0                    ; -> AndroidBitmap_unlockPixels@plt
0x0000fb74: c8 16 40 f9              ldr      x8, [x22, #0x28]
0x0000fb78: e9 07 40 f9              ldr      x9, [sp, #8]
0x0000fb7c: 1f 01 09 eb              cmp      x8, x9
0x0000fb80: c1 00 00 54              b.ne     #0xfb98
0x0000fb84: fd 7b 43 a9              ldp      x29, x30, [sp, #0x30]
0x0000fb88: f4 4f 42 a9              ldp      x20, x19, [sp, #0x20]
0x0000fb8c: f6 57 41 a9              ldp      x22, x21, [sp, #0x10]
0x0000fb90: ff 03 01 91              add      sp, sp, #0x40
0x0000fb94: c0 03 5f d6              ret      
0x0000fb98: 56 fb ff 97              bl       #0xe8f0                    ; -> __stack_chk_fail@plt
