; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libjni_stfaceunlock_api.so
; target  : Java_com_ts_faceauth_FaceAuthNative_evaluate (0x36dc)
; start   : 0x36dc
; size    : 0xc4
; bytes   : 0xc4   instructions: 49
; generated: 2026-10-23T23:24:36

0x000036dc: fa 67 bb a9              stp      x26, x25, [sp, #-0x50]!
0x000036e0: f8 5f 01 a9              stp      x24, x23, [sp, #0x10]
0x000036e4: f6 57 02 a9              stp      x22, x21, [sp, #0x20]
0x000036e8: f4 4f 03 a9              stp      x20, x19, [sp, #0x30]
0x000036ec: fd 7b 04 a9              stp      x29, x30, [sp, #0x40]
0x000036f0: fd 03 01 91              add      x29, sp, #0x40
0x000036f4: 82 04 00 b4              cbz      x2, #0x3784
0x000036f8: f8 03 03 aa              mov      x24, x3
0x000036fc: 43 04 00 b4              cbz      x3, #0x3784
0x00003700: 08 00 40 f9              ldr      x8, [x0]
0x00003704: b7 13 40 b9              ldr      w23, [x29, #0x10]
0x00003708: e1 03 02 aa              mov      x1, x2
0x0000370c: e2 03 1f aa              mov      x2, xzr
0x00003710: 08 a5 42 f9              ldr      x8, [x8, #0x548]
0x00003714: f3 03 07 2a              mov      w19, w7
0x00003718: f4 03 06 2a              mov      w20, w6
0x0000371c: f5 03 05 2a              mov      w21, w5
0x00003720: f6 03 04 2a              mov      w22, w4
0x00003724: f9 03 00 aa              mov      x25, x0
0x00003728: 00 01 3f d6              blr      x8
0x0000372c: 28 03 40 f9              ldr      x8, [x25]
0x00003730: fa 03 00 aa              mov      x26, x0
0x00003734: e0 03 19 aa              mov      x0, x25
0x00003738: e1 03 18 aa              mov      x1, x24
0x0000373c: 08 a5 42 f9              ldr      x8, [x8, #0x548]
0x00003740: e2 03 1f aa              mov      x2, xzr
0x00003744: 00 01 3f d6              blr      x8
0x00003748: fa 01 00 b4              cbz      x26, #0x3784
0x0000374c: e1 03 00 aa              mov      x1, x0
0x00003750: a0 01 00 b4              cbz      x0, #0x3784
0x00003754: e2 03 16 2a              mov      w2, w22
0x00003758: e3 03 15 2a              mov      w3, w21
0x0000375c: e4 03 14 2a              mov      w4, w20
0x00003760: e5 03 13 2a              mov      w5, w19
0x00003764: e6 03 17 2a              mov      w6, w23
0x00003768: fd 7b 44 a9              ldp      x29, x30, [sp, #0x40]
0x0000376c: f4 4f 43 a9              ldp      x20, x19, [sp, #0x30]
0x00003770: f6 57 42 a9              ldp      x22, x21, [sp, #0x20]
0x00003774: f8 5f 41 a9              ldp      x24, x23, [sp, #0x10]
0x00003778: e0 03 1a aa              mov      x0, x26
0x0000377c: fa 67 c5 a8              ldp      x26, x25, [sp], #0x50
0x00003780: 18 fb ff 17              b        #0x23e0                    ; -> st_auth_evaluate@plt
0x00003784: fd 7b 44 a9              ldp      x29, x30, [sp, #0x40]
0x00003788: f4 4f 43 a9              ldp      x20, x19, [sp, #0x30]
0x0000378c: f6 57 42 a9              ldp      x22, x21, [sp, #0x20]
0x00003790: f8 5f 41 a9              ldp      x24, x23, [sp, #0x10]
0x00003794: 00 00 80 12              mov      w0, #-1
0x00003798: fa 67 c5 a8              ldp      x26, x25, [sp], #0x50
0x0000379c: c0 03 5f d6              ret      
