; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/kwai_live/inventory/libs/lib/arm64-v8a/libkwaiaudio.so
; target  : address 0x12a56c
; start   : 0x12a56c
; size    : 0x128
; bytes   : 0x128   instructions: 74
; generated: 2026-10-24T00:57:35

0x0012a56c: fb 0f 1a f8              str      x27, [sp, #-0x60]!
0x0012a570: fa 67 01 a9              stp      x26, x25, [sp, #0x10]
0x0012a574: f8 5f 02 a9              stp      x24, x23, [sp, #0x20]
0x0012a578: f6 57 03 a9              stp      x22, x21, [sp, #0x30]
0x0012a57c: f4 4f 04 a9              stp      x20, x19, [sp, #0x40]
0x0012a580: fd 7b 05 a9              stp      x29, x30, [sp, #0x50]
0x0012a584: fd 43 01 91              add      x29, sp, #0x50
0x0012a588: 42 07 00 b4              cbz      x2, #0x12a670
0x0012a58c: 08 00 40 f9              ldr      x8, [x0]
0x0012a590: f8 03 02 aa              mov      x24, x2
0x0012a594: e1 03 03 aa              mov      x1, x3
0x0012a598: e2 03 1f aa              mov      x2, xzr
0x0012a59c: 08 e1 42 f9              ldr      x8, [x8, #0x5c0]
0x0012a5a0: f5 03 05 aa              mov      x21, x5
0x0012a5a4: f6 03 04 2a              mov      w22, w4
0x0012a5a8: f3 03 03 aa              mov      x19, x3
0x0012a5ac: f4 03 00 aa              mov      x20, x0
0x0012a5b0: 00 01 3f d6              blr      x8
0x0012a5b4: f7 03 00 aa              mov      x23, x0
0x0012a5b8: e0 03 18 aa              mov      x0, x24
0x0012a5bc: e1 03 17 aa              mov      x1, x23
0x0012a5c0: e2 03 16 2a              mov      w2, w22
0x0012a5c4: 2f 9f ff 97              bl       #0x112280
0x0012a5c8: f6 03 00 aa              mov      x22, x0
0x0012a5cc: 36 04 00 34              cbz      w22, #0x12a650
0x0012a5d0: 88 02 40 f9              ldr      x8, [x20]
0x0012a5d4: f8 03 01 aa              mov      x24, x1
0x0012a5d8: 3a fc 60 d3              lsr      x26, x1, #0x20
0x0012a5dc: e0 03 14 aa              mov      x0, x20
0x0012a5e0: 08 f5 42 f9              ldr      x8, [x8, #0x5e8]
0x0012a5e4: e1 03 15 aa              mov      x1, x21
0x0012a5e8: e2 03 1f aa              mov      x2, xzr
0x0012a5ec: db fe 60 d3              lsr      x27, x22, #0x20
0x0012a5f0: 00 01 3f d6              blr      x8
0x0012a5f4: 88 02 40 f9              ldr      x8, [x20]
0x0012a5f8: f9 03 00 aa              mov      x25, x0
0x0012a5fc: e0 03 14 aa              mov      x0, x20
0x0012a600: e1 03 15 aa              mov      x1, x21
0x0012a604: 08 ad 42 f9              ldr      x8, [x8, #0x558]
0x0012a608: 00 01 3f d6              blr      x8
0x0012a60c: 3b 63 00 29              stp      w27, w24, [x25]
0x0012a610: 3a 0b 00 b9              str      w26, [x25, #8]
0x0012a614: 88 02 40 f9              ldr      x8, [x20]
0x0012a618: e3 03 00 2a              mov      w3, w0
0x0012a61c: e0 03 14 aa              mov      x0, x20
0x0012a620: e1 03 15 aa              mov      x1, x21
0x0012a624: 08 55 43 f9              ldr      x8, [x8, #0x6a8]
0x0012a628: e2 03 1f 2a              mov      w2, wzr
0x0012a62c: e4 03 19 aa              mov      x4, x25
0x0012a630: 00 01 3f d6              blr      x8
0x0012a634: 88 02 40 f9              ldr      x8, [x20]
0x0012a638: e0 03 14 aa              mov      x0, x20
0x0012a63c: e1 03 15 aa              mov      x1, x21
0x0012a640: e2 03 19 aa              mov      x2, x25
0x0012a644: 08 15 43 f9              ldr      x8, [x8, #0x628]
0x0012a648: e3 03 1f 2a              mov      w3, wzr
0x0012a64c: 00 01 3f d6              blr      x8
0x0012a650: 88 02 40 f9              ldr      x8, [x20]
0x0012a654: e0 03 14 aa              mov      x0, x20
0x0012a658: e1 03 13 aa              mov      x1, x19
0x0012a65c: e2 03 17 aa              mov      x2, x23
0x0012a660: 08 01 43 f9              ldr      x8, [x8, #0x600]
0x0012a664: e3 03 1f 2a              mov      w3, wzr
0x0012a668: 00 01 3f d6              blr      x8
0x0012a66c: 02 00 00 14              b        #0x12a674                  ; -> Java_com_kwai_video_stannis_AudioProcess_nativeAudioVoiceDetectProcess+0x108
0x0012a670: f6 03 1f 2a              mov      w22, wzr
0x0012a674: e0 03 16 2a              mov      w0, w22
0x0012a678: fd 7b 45 a9              ldp      x29, x30, [sp, #0x50]
0x0012a67c: f4 4f 44 a9              ldp      x20, x19, [sp, #0x40]
0x0012a680: f6 57 43 a9              ldp      x22, x21, [sp, #0x30]
0x0012a684: f8 5f 42 a9              ldp      x24, x23, [sp, #0x20]
0x0012a688: fa 67 41 a9              ldp      x26, x25, [sp, #0x10]
0x0012a68c: fb 07 46 f8              ldr      x27, [sp], #0x60
0x0012a690: c0 03 5f d6              ret      
