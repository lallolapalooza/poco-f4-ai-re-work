; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : st_auth_set_facedetect_callback (0x10dfd8)
; start   : 0x10dfd8
; size    : 0x10c
; bytes   : 0x10c   instructions: 67
; generated: 2026-10-24T00:26:32

0x0010dfd8: fd 7b bb a9              stp      x29, x30, [sp, #-0x50]!
0x0010dfdc: 23 32 00 b0              adrp     x3, #0x752000
0x0010dfe0: 22 69 80 52              mov      w2, #0x349
0x0010dfe4: fd 03 00 91              mov      x29, sp
0x0010dfe8: f3 53 01 a9              stp      x19, x20, [sp, #0x10]
0x0010dfec: 54 32 00 90              adrp     x20, #0x755000
0x0010dff0: f5 13 00 f9              str      x21, [sp, #0x20]
0x0010dff4: f3 03 00 aa              mov      x19, x0
0x0010dff8: 95 42 07 91              add      x21, x20, #0x1d0
0x0010dffc: 40 32 00 90              adrp     x0, #0x755000
0x0010e000: 63 00 03 91              add      x3, x3, #0xc0
0x0010e004: 00 c0 06 91              add      x0, x0, #0x1b0
0x0010e008: e1 03 15 aa              mov      x1, x21
0x0010e00c: 63 c0 1f 91              add      x3, x3, #0x7f0
0x0010e010: 6e c2 00 94              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x0010e014: 20 ee 02 90              adrp     x0, #0x5ed2000
0x0010e018: 00 b4 47 f9              ldr      x0, [x0, #0xf68]
0x0010e01c: 40 04 00 b4              cbz      x0, #0x10e0a4
0x0010e020: 00 00 40 f9              ldr      x0, [x0]
0x0010e024: bf 23 00 f9              str      xzr, [x29, #0x40]
0x0010e028: 13 01 00 b4              cbz      x19, #0x10e048
0x0010e02c: 01 00 00 d0              adrp     x1, #0x110000
0x0010e030: b3 1b 00 f9              str      x19, [x29, #0x30]
0x0010e034: 21 40 17 91              add      x1, x1, #0x5d0
0x0010e038: a1 27 00 f9              str      x1, [x29, #0x48]
0x0010e03c: c1 70 02 d0              adrp     x1, #0x4f28000
0x0010e040: 21 ac 45 f9              ldr      x1, [x1, #0xb58]
0x0010e044: a1 23 00 f9              str      x1, [x29, #0x40]
0x0010e048: b5 c3 00 91              add      x21, x29, #0x30
0x0010e04c: e1 03 15 aa              mov      x1, x21
0x0010e050: e4 e8 00 94              bl       #0x1483e0                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace21SetFaceDetectCallbackESt8functionIFvP11st_detect_tEE
0x0010e054: a3 23 40 f9              ldr      x3, [x29, #0x40]
0x0010e058: f3 03 00 2a              mov      w19, w0
0x0010e05c: a3 00 00 b4              cbz      x3, #0x10e070
0x0010e060: e0 03 15 aa              mov      x0, x21
0x0010e064: e1 03 15 aa              mov      x1, x21
0x0010e068: 62 00 80 52              mov      w2, #3
0x0010e06c: 60 00 3f d6              blr      x3
0x0010e070: 00 00 80 52              mov      w0, #0
0x0010e074: 13 01 00 34              cbz      w19, #0x10e094
0x0010e078: 20 32 00 f0              adrp     x0, #0x755000
0x0010e07c: 81 42 07 91              add      x1, x20, #0x1d0
0x0010e080: 00 c0 12 91              add      x0, x0, #0x4b0
0x0010e084: 02 6a 80 52              mov      w2, #0x350
0x0010e088: e3 03 13 2a              mov      w3, w19
0x0010e08c: 18 c3 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010e090: e0 03 13 2a              mov      w0, w19
0x0010e094: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x0010e098: f5 13 40 f9              ldr      x21, [sp, #0x20]
0x0010e09c: fd 7b c5 a8              ldp      x29, x30, [sp], #0x50
0x0010e0a0: c0 03 5f d6              ret      
0x0010e0a4: 20 32 00 f0              adrp     x0, #0x755000
0x0010e0a8: e1 03 15 aa              mov      x1, x21
0x0010e0ac: 00 80 08 91              add      x0, x0, #0x220
0x0010e0b0: 62 69 80 52              mov      w2, #0x34b
0x0010e0b4: 0e c3 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x0010e0b8: 20 00 80 12              mov      w0, #-2
0x0010e0bc: f6 ff ff 17              b        #0x10e094                  ; -> st_auth_set_facedetect_callback+0xbc
0x0010e0c0: a3 23 40 f9              ldr      x3, [x29, #0x40]
0x0010e0c4: f3 03 00 aa              mov      x19, x0
0x0010e0c8: a3 00 00 b4              cbz      x3, #0x10e0dc
0x0010e0cc: e0 03 15 aa              mov      x0, x21
0x0010e0d0: e1 03 15 aa              mov      x1, x21
0x0010e0d4: 62 00 80 52              mov      w2, #3
0x0010e0d8: 60 00 3f d6              blr      x3
0x0010e0dc: e0 03 13 aa              mov      x0, x19
0x0010e0e0: 75 08 19 94              bl       #0x7502b4                  ; -> _Unwind_Resume
