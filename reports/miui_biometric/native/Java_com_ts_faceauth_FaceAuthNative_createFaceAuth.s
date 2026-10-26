; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libjni_stfaceunlock_api.so
; target  : Java_com_ts_faceauth_FaceAuthNative_createFaceAuth (0x2f64)
; start   : 0x2f64
; size    : 0xec
; bytes   : 0xec   instructions: 59
; generated: 2026-10-23T23:24:34

0x00002f64: f8 5f bc a9              stp      x24, x23, [sp, #-0x40]!
0x00002f68: f6 57 01 a9              stp      x22, x21, [sp, #0x10]
0x00002f6c: f4 4f 02 a9              stp      x20, x19, [sp, #0x20]
0x00002f70: fd 7b 03 a9              stp      x29, x30, [sp, #0x30]
0x00002f74: fd c3 00 91              add      x29, sp, #0x30
0x00002f78: f6 03 04 aa              mov      x22, x4
0x00002f7c: f7 03 03 2a              mov      w23, w3
0x00002f80: f3 03 02 aa              mov      x19, x2
0x00002f84: f4 03 00 aa              mov      x20, x0
0x00002f88: e2 04 00 b4              cbz      x2, #0x3024
0x00002f8c: 88 02 40 f9              ldr      x8, [x20]
0x00002f90: e0 03 14 aa              mov      x0, x20
0x00002f94: e1 03 13 aa              mov      x1, x19
0x00002f98: e2 03 1f aa              mov      x2, xzr
0x00002f9c: 08 a5 42 f9              ldr      x8, [x8, #0x548]
0x00002fa0: 00 01 3f d6              blr      x8
0x00002fa4: f5 03 00 aa              mov      x21, x0
0x00002fa8: 36 04 00 b4              cbz      x22, #0x302c
0x00002fac: 88 02 40 f9              ldr      x8, [x20]
0x00002fb0: e0 03 14 aa              mov      x0, x20
0x00002fb4: e1 03 16 aa              mov      x1, x22
0x00002fb8: e2 03 1f aa              mov      x2, xzr
0x00002fbc: 08 ed 42 f9              ldr      x8, [x8, #0x5d8]
0x00002fc0: 00 01 3f d6              blr      x8
0x00002fc4: f8 03 00 aa              mov      x24, x0
0x00002fc8: e0 03 15 aa              mov      x0, x21
0x00002fcc: e1 03 17 2a              mov      w1, w23
0x00002fd0: 3c fd ff 97              bl       #0x24c0                    ; -> st_auth_init@plt
0x00002fd4: 38 01 00 b4              cbz      x24, #0x2ff8
0x00002fd8: 00 03 00 b9              str      w0, [x24]
0x00002fdc: 88 02 40 f9              ldr      x8, [x20]
0x00002fe0: e0 03 14 aa              mov      x0, x20
0x00002fe4: e1 03 16 aa              mov      x1, x22
0x00002fe8: e2 03 18 aa              mov      x2, x24
0x00002fec: 08 0d 43 f9              ldr      x8, [x8, #0x618]
0x00002ff0: e3 03 1f 2a              mov      w3, wzr
0x00002ff4: 00 01 3f d6              blr      x8
0x00002ff8: 35 02 00 b4              cbz      x21, #0x303c
0x00002ffc: 88 02 40 f9              ldr      x8, [x20]
0x00003000: e0 03 14 aa              mov      x0, x20
0x00003004: e1 03 13 aa              mov      x1, x19
0x00003008: e2 03 15 aa              mov      x2, x21
0x0000300c: 03 a9 42 f9              ldr      x3, [x8, #0x550]
0x00003010: fd 7b 43 a9              ldp      x29, x30, [sp, #0x30]
0x00003014: f4 4f 42 a9              ldp      x20, x19, [sp, #0x20]
0x00003018: f6 57 41 a9              ldp      x22, x21, [sp, #0x10]
0x0000301c: f8 5f c4 a8              ldp      x24, x23, [sp], #0x40
0x00003020: 60 00 1f d6              br       x3
0x00003024: f5 03 1f aa              mov      x21, xzr
0x00003028: 36 fc ff b5              cbnz     x22, #0x2fac
0x0000302c: e0 03 15 aa              mov      x0, x21
0x00003030: e1 03 17 2a              mov      w1, w23
0x00003034: 23 fd ff 97              bl       #0x24c0                    ; -> st_auth_init@plt
0x00003038: 35 fe ff b5              cbnz     x21, #0x2ffc
0x0000303c: fd 7b 43 a9              ldp      x29, x30, [sp, #0x30]
0x00003040: f4 4f 42 a9              ldp      x20, x19, [sp, #0x20]
0x00003044: f6 57 41 a9              ldp      x22, x21, [sp, #0x10]
0x00003048: f8 5f c4 a8              ldp      x24, x23, [sp], #0x40
0x0000304c: c0 03 5f d6              ret      
