; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libqvasml.so
; target  : address 0x5e144
; start   : 0x5e144
; size    : 0xb8
; bytes   : 0xb8   instructions: 46
; generated: 2026-10-23T23:25:21

0x0005e144: 3f 23 03 d5              paciasp  
0x0005e148: fd 7b bd a9              stp      x29, x30, [sp, #-0x30]!
0x0005e14c: f5 0b 00 f9              str      x21, [sp, #0x10]
0x0005e150: f4 4f 02 a9              stp      x20, x19, [sp, #0x20]
0x0005e154: fd 03 00 91              mov      x29, sp
0x0005e158: f5 03 00 aa              mov      x21, x0
0x0005e15c: 00 08 80 52              mov      w0, #0x40
0x0005e160: f4 03 01 2a              mov      w20, w1
0x0005e164: 59 ce 02 94              bl       #0x111ac8
0x0005e168: e0 03 00 b4              cbz      x0, #0x5e1e4
0x0005e16c: f3 03 00 aa              mov      x19, x0
0x0005e170: a0 02 00 f9              str      x0, [x21]
0x0005e174: 14 00 00 b9              str      w20, [x0]
0x0005e178: 80 00 80 52              mov      w0, #4
0x0005e17c: 01 20 80 52              mov      w1, #0x100
0x0005e180: 4c ce 02 94              bl       #0x111ab0                  ; -> calloc@plt
0x0005e184: 60 12 00 f9              str      x0, [x19, #0x20]
0x0005e188: 80 00 80 52              mov      w0, #4
0x0005e18c: 01 40 80 52              mov      w1, #0x200
0x0005e190: 48 ce 02 94              bl       #0x111ab0                  ; -> calloc@plt
0x0005e194: 95 5a 17 53              lsl      w21, w20, #9
0x0005e198: 60 0e 00 f9              str      x0, [x19, #0x18]
0x0005e19c: a8 16 14 4b              sub      w8, w21, w20, lsl #5
0x0005e1a0: 40 00 80 52              mov      w0, #2
0x0005e1a4: 01 7d 40 93              sxtw     x1, w8
0x0005e1a8: 42 ce 02 94              bl       #0x111ab0                  ; -> calloc@plt
0x0005e1ac: a1 7e 40 93              sxtw     x1, w21
0x0005e1b0: 60 16 00 f9              str      x0, [x19, #0x28]
0x0005e1b4: 80 00 80 52              mov      w0, #4
0x0005e1b8: 3e ce 02 94              bl       #0x111ab0                  ; -> calloc@plt
0x0005e1bc: 88 0a 14 0b              add      w8, w20, w20, lsl #2
0x0005e1c0: 60 1a 00 f9              str      x0, [x19, #0x30]
0x0005e1c4: 08 6d 1c 53              lsl      w8, w8, #4
0x0005e1c8: 80 00 80 52              mov      w0, #4
0x0005e1cc: 01 7d 40 93              sxtw     x1, w8
0x0005e1d0: 38 ce 02 94              bl       #0x111ab0                  ; -> calloc@plt
0x0005e1d4: e8 03 00 aa              mov      x8, x0
0x0005e1d8: e0 03 1f 2a              mov      w0, wzr
0x0005e1dc: 68 1e 00 f9              str      x8, [x19, #0x38]
0x0005e1e0: 02 00 00 14              b        #0x5e1e8                   ; -> udk7_feature_init+0xa4
0x0005e1e4: 20 00 80 52              mov      w0, #1
0x0005e1e8: f4 4f 42 a9              ldp      x20, x19, [sp, #0x20]
0x0005e1ec: f5 0b 40 f9              ldr      x21, [sp, #0x10]
0x0005e1f0: fd 7b c3 a8              ldp      x29, x30, [sp], #0x30
0x0005e1f4: bf 23 03 d5              autiasp  
0x0005e1f8: c0 03 5f d6              ret      
