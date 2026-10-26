; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : st_auth_enroll (0x10ca7c)
; start   : 0x10ca7c
; size    : 0x9c
; bytes   : 0x9c   instructions: 39
; generated: 2026-10-24T00:26:25

0x0010ca7c: fd 7b bd a9              stp      x29, x30, [sp, #-0x30]!
0x0010ca80: 23 32 00 d0              adrp     x3, #0x752000
0x0010ca84: fd 03 00 91              mov      x29, sp
0x0010ca88: f3 53 01 a9              stp      x19, x20, [sp, #0x10]
0x0010ca8c: 53 32 00 b0              adrp     x19, #0x755000
0x0010ca90: 63 00 03 91              add      x3, x3, #0xc0
0x0010ca94: f5 5b 02 a9              stp      x21, x22, [sp, #0x20]
0x0010ca98: 73 42 07 91              add      x19, x19, #0x1d0
0x0010ca9c: f6 03 00 aa              mov      x22, x0
0x0010caa0: 40 32 00 b0              adrp     x0, #0x755000
0x0010caa4: f5 03 01 2a              mov      w21, w1
0x0010caa8: 00 c0 06 91              add      x0, x0, #0x1b0
0x0010caac: f4 03 02 aa              mov      x20, x2
0x0010cab0: e1 03 13 aa              mov      x1, x19
0x0010cab4: c2 33 80 52              mov      w2, #0x19e
0x0010cab8: 63 00 06 91              add      x3, x3, #0x180
0x0010cabc: c3 c7 00 94              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x0010cac0: 20 ee 02 d0              adrp     x0, #0x5ed2000
0x0010cac4: 00 b4 47 f9              ldr      x0, [x0, #0xf68]
0x0010cac8: 40 01 00 b4              cbz      x0, #0x10caf0
0x0010cacc: e1 03 16 aa              mov      x1, x22
0x0010cad0: e2 03 15 2a              mov      w2, w21
0x0010cad4: e3 03 14 aa              mov      x3, x20
0x0010cad8: 00 00 40 f9              ldr      x0, [x0]
0x0010cadc: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x0010cae0: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x0010cae4: fd 7b c3 a8              ldp      x29, x30, [sp], #0x30
0x0010cae8: 04 00 80 d2              mov      x4, #0
0x0010caec: 00 f3 00 14              b        #0x1496ec                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace6EnrollEP10st_image_tjPiPP13st_template_t
0x0010caf0: 40 32 00 b0              adrp     x0, #0x755000
0x0010caf4: e1 03 13 aa              mov      x1, x19
0x0010caf8: 00 80 08 91              add      x0, x0, #0x220
0x0010cafc: 02 34 80 52              mov      w2, #0x1a0
0x0010cb00: 7b c8 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010cb04: 20 00 80 12              mov      w0, #-2
0x0010cb08: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x0010cb0c: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x0010cb10: fd 7b c3 a8              ldp      x29, x30, [sp], #0x30
0x0010cb14: c0 03 5f d6              ret      
