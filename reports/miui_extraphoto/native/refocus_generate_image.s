; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_extraphoto/native/librefocus.so
; target  : Java_com_miui_extraphoto_refocus_manager_DualPhotoJni_generateNativeImage (0xff48)
; start   : 0xff48
; size    : 0x5c
; bytes   : 0x5c   instructions: 23
; generated: 2026-10-24T00:50:25

0x0000ff48: f7 0f 1c f8              str      x23, [sp, #-0x40]!
0x0000ff4c: f6 57 01 a9              stp      x22, x21, [sp, #0x10]
0x0000ff50: f4 4f 02 a9              stp      x20, x19, [sp, #0x20]
0x0000ff54: fd 7b 03 a9              stp      x29, x30, [sp, #0x30]
0x0000ff58: fd c3 00 91              add      x29, sp, #0x30
0x0000ff5c: f6 03 00 aa              mov      x22, x0
0x0000ff60: e0 03 1a 32              mov      w0, #0x40
0x0000ff64: f3 03 04 2a              mov      w19, w4
0x0000ff68: f4 03 03 2a              mov      w20, w3
0x0000ff6c: f5 03 02 aa              mov      x21, x2
0x0000ff70: fc f9 ff 97              bl       #0xe760                    ; -> _Znwm@plt
0x0000ff74: e1 03 16 aa              mov      x1, x22
0x0000ff78: e2 03 15 aa              mov      x2, x21
0x0000ff7c: e3 03 14 2a              mov      w3, w20
0x0000ff80: e4 03 13 2a              mov      w4, w19
0x0000ff84: f7 03 00 aa              mov      x23, x0
0x0000ff88: 16 fa ff 97              bl       #0xe7e0                    ; -> _Z28convertBitmapToOffscreenDataP20__tag_ASVL_OFFSCREENP7_JNIEnvP8_jobjectii@plt
0x0000ff8c: fd 7b 43 a9              ldp      x29, x30, [sp, #0x30]
0x0000ff90: f4 4f 42 a9              ldp      x20, x19, [sp, #0x20]
0x0000ff94: f6 57 41 a9              ldp      x22, x21, [sp, #0x10]
0x0000ff98: e0 03 17 aa              mov      x0, x23
0x0000ff9c: f7 07 44 f8              ldr      x23, [sp], #0x40
0x0000ffa0: 3c fa ff 17              b        #0xe890                    ; -> _Z17getPointerByImageP20__tag_ASVL_OFFSCREEN@plt
