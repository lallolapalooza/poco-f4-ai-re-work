; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : st_auth_unlock (0x10cb18)
; start   : 0x10cb18
; size    : 0xa4
; bytes   : 0xa4   instructions: 41
; generated: 2026-10-24T00:26:22

0x0010cb18: fd 7b bc a9              stp      x29, x30, [sp, #-0x40]!
0x0010cb1c: e4 03 03 2a              mov      w4, w3
0x0010cb20: 23 32 00 d0              adrp     x3, #0x752000
0x0010cb24: fd 03 00 91              mov      x29, sp
0x0010cb28: f3 53 01 a9              stp      x19, x20, [sp, #0x10]
0x0010cb2c: 53 32 00 b0              adrp     x19, #0x755000
0x0010cb30: 63 00 03 91              add      x3, x3, #0xc0
0x0010cb34: f5 5b 02 a9              stp      x21, x22, [sp, #0x20]
0x0010cb38: 73 42 07 91              add      x19, x19, #0x1d0
0x0010cb3c: f6 03 00 aa              mov      x22, x0
0x0010cb40: 40 32 00 b0              adrp     x0, #0x755000
0x0010cb44: f5 03 01 2a              mov      w21, w1
0x0010cb48: 00 c0 06 91              add      x0, x0, #0x1b0
0x0010cb4c: f4 03 02 aa              mov      x20, x2
0x0010cb50: e1 03 13 aa              mov      x1, x19
0x0010cb54: 02 35 80 52              mov      w2, #0x1a8
0x0010cb58: 63 00 07 91              add      x3, x3, #0x1c0
0x0010cb5c: a4 1f 00 f9              str      x4, [x29, #0x38]
0x0010cb60: 9a c7 00 94              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x0010cb64: 20 ee 02 d0              adrp     x0, #0x5ed2000
0x0010cb68: a4 1f 40 f9              ldr      x4, [x29, #0x38]
0x0010cb6c: 00 b4 47 f9              ldr      x0, [x0, #0xf68]
0x0010cb70: 20 01 00 b4              cbz      x0, #0x10cb94
0x0010cb74: 00 00 40 f9              ldr      x0, [x0]
0x0010cb78: e1 03 16 aa              mov      x1, x22
0x0010cb7c: e2 03 15 2a              mov      w2, w21
0x0010cb80: e3 03 14 aa              mov      x3, x20
0x0010cb84: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x0010cb88: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x0010cb8c: fd 7b c4 a8              ldp      x29, x30, [sp], #0x40
0x0010cb90: 06 fa 00 14              b        #0x14b3a8                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace9AuthUseIdEP10st_image_tjPii
0x0010cb94: 40 32 00 b0              adrp     x0, #0x755000
0x0010cb98: e1 03 13 aa              mov      x1, x19
0x0010cb9c: 00 80 08 91              add      x0, x0, #0x220
0x0010cba0: 42 35 80 52              mov      w2, #0x1aa
0x0010cba4: 52 c8 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010cba8: 20 00 80 12              mov      w0, #-2
0x0010cbac: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x0010cbb0: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x0010cbb4: fd 7b c4 a8              ldp      x29, x30, [sp], #0x40
0x0010cbb8: c0 03 5f d6              ret      
