; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainsdk.so
; target  : vadCheckEnd (0x1711c)
; start   : 0x1711c
; size    : 0x110
; bytes   : 0x110   instructions: 68
; generated: 2026-10-23T23:42:37

0x0001711c: fd 7b b9 a9              stp      x29, x30, [sp, #-0x70]!
0x00017120: 42 7c 40 93              sxtw     x2, w2
0x00017124: fd 03 00 91              mov      x29, sp
0x00017128: f3 53 01 a9              stp      x19, x20, [sp, #0x10]
0x0001712c: f3 06 00 90              adrp     x19, #0xf3000
0x00017130: f4 03 00 aa              mov      x20, x0
0x00017134: 00 00 40 f9              ldr      x0, [x0]
0x00017138: 42 fc 41 d3              lsr      x2, x2, #1
0x0001713c: f5 5b 02 a9              stp      x21, x22, [sp, #0x20]
0x00017140: 64 5a 47 f9              ldr      x4, [x19, #0xeb0]          ; -> __stack_chk_guard@got
0x00017144: f5 03 03 2a              mov      w21, w3
0x00017148: 84 00 40 f9              ldr      x4, [x4]
0x0001714c: a4 37 00 f9              str      x4, [x29, #0x68]
0x00017150: 24 ab 00 94              bl       #0x41de0
0x00017154: e2 03 00 2a              mov      w2, w0
0x00017158: e0 01 00 34              cbz      w0, #0x17194
0x0001715c: 01 05 00 b0              adrp     x1, #0xb8000
0x00017160: 60 00 80 52              mov      w0, #3
0x00017164: 21 80 2b 91              add      x1, x1, #0xae0
0x00017168: 1a ce ff 97              bl       #0xa9d0                    ; -> mi_brain_log@plt
0x0001716c: 00 00 80 12              mov      w0, #-1
0x00017170: 73 5a 47 f9              ldr      x19, [x19, #0xeb0]         ; -> __stack_chk_guard@got
0x00017174: a2 37 40 f9              ldr      x2, [x29, #0x68]
0x00017178: 61 02 40 f9              ldr      x1, [x19]
0x0001717c: 5f 00 01 eb              cmp      x2, x1
0x00017180: 41 05 00 54              b.ne     #0x17228
0x00017184: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x00017188: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x0001718c: fd 7b c7 a8              ldp      x29, x30, [sp], #0x70
0x00017190: c0 03 5f d6              ret      
0x00017194: 82 02 40 f9              ldr      x2, [x20]
0x00017198: b6 e3 00 91              add      x22, x29, #0x38
0x0001719c: 80 06 40 f9              ldr      x0, [x20, #8]
0x000171a0: e1 03 16 aa              mov      x1, x22
0x000171a4: b5 4b 00 b9              str      w21, [x29, #0x48]
0x000171a8: 43 ac 4f b9              ldr      w3, [x2, #0xfac]
0x000171ac: 45 d0 47 f9              ldr      x5, [x2, #0xfa0]
0x000171b0: 44 a8 4f b9              ldr      w4, [x2, #0xfa8]
0x000171b4: 42 d8 47 f9              ldr      x2, [x2, #0xfb0]
0x000171b8: a5 1f 00 f9              str      x5, [x29, #0x38]
0x000171bc: a4 43 00 b9              str      w4, [x29, #0x40]
0x000171c0: a3 47 00 b9              str      w3, [x29, #0x44]
0x000171c4: a2 2f 00 f9              str      x2, [x29, #0x58]
0x000171c8: a3 63 00 b9              str      w3, [x29, #0x60]
0x000171cc: 68 b2 00 94              bl       #0x43b6c
0x000171d0: 20 01 00 35              cbnz     w0, #0x171f4
0x000171d4: a2 53 40 b9              ldr      w2, [x29, #0x50]
0x000171d8: 42 02 f8 37              tbnz     w2, #0x1f, #0x17220
0x000171dc: 01 05 00 b0              adrp     x1, #0xb8000
0x000171e0: 20 00 80 52              mov      w0, #1
0x000171e4: 21 c0 2c 91              add      x1, x1, #0xb30
0x000171e8: fa cd ff 97              bl       #0xa9d0                    ; -> mi_brain_log@plt
0x000171ec: c0 1a 40 b9              ldr      w0, [x22, #0x18]
0x000171f0: e0 ff ff 17              b        #0x17170                   ; -> vadCheckEnd+0x54
0x000171f4: 01 05 00 b0              adrp     x1, #0xb8000
0x000171f8: e2 03 00 2a              mov      w2, w0
0x000171fc: 21 e0 2b 91              add      x1, x1, #0xaf8
0x00017200: 60 00 80 52              mov      w0, #3
0x00017204: f3 cd ff 97              bl       #0xa9d0                    ; -> mi_brain_log@plt
0x00017208: 80 06 40 f9              ldr      x0, [x20, #8]
0x0001720c: 0f ae 00 94              bl       #0x42a48
0x00017210: 80 02 40 f9              ldr      x0, [x20]
0x00017214: 42 a5 00 94              bl       #0x4071c
0x00017218: 00 00 80 12              mov      w0, #-1
0x0001721c: d5 ff ff 17              b        #0x17170                   ; -> vadCheckEnd+0x54
0x00017220: 00 00 80 12              mov      w0, #-1
0x00017224: d3 ff ff 17              b        #0x17170                   ; -> vadCheckEnd+0x54
0x00017228: a2 cd ff 97              bl       #0xa8b0                    ; -> __stack_chk_fail@plt
