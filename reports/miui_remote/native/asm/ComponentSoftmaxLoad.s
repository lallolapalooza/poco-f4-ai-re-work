; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : ComponentSoftmaxLoad (0x285d0)
; start   : 0x285d0
; size    : 0xdc
; bytes   : 0xdc   instructions: 55
; generated: 2026-10-23T23:48:36

0x000285d0: f6 57 bd a9              stp      x22, x21, [sp, #-0x30]!
0x000285d4: f4 4f 01 a9              stp      x20, x19, [sp, #0x10]
0x000285d8: fd 7b 02 a9              stp      x29, x30, [sp, #0x20]
0x000285dc: fd 83 00 91              add      x29, sp, #0x20
0x000285e0: f4 03 00 aa              mov      x20, x0
0x000285e4: 93 1a 40 f9              ldr      x19, [x20, #0x30]
0x000285e8: f5 03 02 2a              mov      w21, w2
0x000285ec: f6 03 01 aa              mov      x22, x1
0x000285f0: e0 03 13 aa              mov      x0, x19
0x000285f4: 5f 95 ff 97              bl       #0xdb70                    ; -> SoilMode@plt
0x000285f8: 1f 04 00 71              cmp      w0, #1
0x000285fc: 01 01 00 54              b.ne     #0x2861c
0x00028600: e0 03 13 aa              mov      x0, x19
0x00028604: 37 94 ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x00028608: 80 6a 00 b9              str      w0, [x20, #0x68]
0x0002860c: e0 03 13 aa              mov      x0, x19
0x00028610: 34 94 ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x00028614: 80 6e 00 b9              str      w0, [x20, #0x6c]
0x00028618: 21 00 00 14              b        #0x2869c                   ; -> ComponentSoftmaxLoad+0xcc
0x0002861c: 21 01 00 d0              adrp     x1, #0x4e000
0x00028620: 21 08 1e 91              add      x1, x1, #0x782
0x00028624: e0 03 16 aa              mov      x0, x22
0x00028628: c6 93 ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x0002862c: e1 03 15 2a              mov      w1, w21
0x00028630: 00 93 ff 97              bl       #0xd230                    ; -> SoilJsonGetArrayItem@plt
0x00028634: 21 01 00 d0              adrp     x1, #0x4e000
0x00028638: 21 30 2c 91              add      x1, x1, #0xb0c
0x0002863c: e2 03 1f 2a              mov      w2, wzr
0x00028640: f5 03 00 aa              mov      x21, x0
0x00028644: 17 94 ff 97              bl       #0xd6a0                    ; -> SoilJsonGetIntc@plt
0x00028648: 21 01 00 d0              adrp     x1, #0x4e000
0x0002864c: 80 8e 06 b8              str      w0, [x20, #0x68]!
0x00028650: 21 48 2c 91              add      x1, x1, #0xb12
0x00028654: e0 03 15 aa              mov      x0, x21
0x00028658: e2 03 1f 2a              mov      w2, wzr
0x0002865c: 11 94 ff 97              bl       #0xd6a0                    ; -> SoilJsonGetIntc@plt
0x00028660: 80 06 00 b9              str      w0, [x20, #4]
0x00028664: e0 03 13 aa              mov      x0, x19
0x00028668: 42 95 ff 97              bl       #0xdb70                    ; -> SoilMode@plt
0x0002866c: 1f 08 00 71              cmp      w0, #2
0x00028670: 61 01 00 54              b.ne     #0x2869c
0x00028674: e0 03 14 aa              mov      x0, x20
0x00028678: e1 03 13 aa              mov      x1, x19
0x0002867c: 95 12 00 91              add      x21, x20, #4
0x00028680: 94 92 ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x00028684: e1 03 13 aa              mov      x1, x19
0x00028688: fd 7b 42 a9              ldp      x29, x30, [sp, #0x20]
0x0002868c: f4 4f 41 a9              ldp      x20, x19, [sp, #0x10]
0x00028690: e0 03 15 aa              mov      x0, x21
0x00028694: f6 57 c3 a8              ldp      x22, x21, [sp], #0x30
0x00028698: 8e 92 ff 17              b        #0xd0d0                    ; -> SoilWriteBinInt@plt
0x0002869c: fd 7b 42 a9              ldp      x29, x30, [sp, #0x20]
0x000286a0: f4 4f 41 a9              ldp      x20, x19, [sp, #0x10]
0x000286a4: f6 57 c3 a8              ldp      x22, x21, [sp], #0x30
0x000286a8: c0 03 5f d6              ret      
