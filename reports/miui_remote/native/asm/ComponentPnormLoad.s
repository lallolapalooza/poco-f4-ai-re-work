; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : ComponentPnormLoad (0x29a70)
; start   : 0x29a70
; size    : 0xa0
; bytes   : 0xa0   instructions: 40
; generated: 2026-10-23T23:51:24

0x00029a70: f6 57 bd a9              stp      x22, x21, [sp, #-0x30]!
0x00029a74: f4 4f 01 a9              stp      x20, x19, [sp, #0x10]
0x00029a78: fd 7b 02 a9              stp      x29, x30, [sp, #0x20]
0x00029a7c: fd 83 00 91              add      x29, sp, #0x20
0x00029a80: f3 03 00 aa              mov      x19, x0
0x00029a84: 74 1a 40 f9              ldr      x20, [x19, #0x30]
0x00029a88: f5 03 02 2a              mov      w21, w2
0x00029a8c: f6 03 01 aa              mov      x22, x1
0x00029a90: e0 03 14 aa              mov      x0, x20
0x00029a94: 37 90 ff 97              bl       #0xdb70                    ; -> SoilMode@plt
0x00029a98: 1f 04 00 71              cmp      w0, #1
0x00029a9c: a1 00 00 54              b.ne     #0x29ab0
0x00029aa0: e0 03 14 aa              mov      x0, x20
0x00029aa4: 0f 8f ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x00029aa8: 60 6a 00 b9              str      w0, [x19, #0x68]
0x00029aac: 15 00 00 14              b        #0x29b00                   ; -> ComponentPnormLoad+0x90
0x00029ab0: 21 01 00 b0              adrp     x1, #0x4e000
0x00029ab4: 21 08 1e 91              add      x1, x1, #0x782
0x00029ab8: e0 03 16 aa              mov      x0, x22
0x00029abc: a1 8e ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x00029ac0: e1 03 15 2a              mov      w1, w21
0x00029ac4: db 8d ff 97              bl       #0xd230                    ; -> SoilJsonGetArrayItem@plt
0x00029ac8: 21 01 00 d0              adrp     x1, #0x4f000
0x00029acc: 21 dc 0b 91              add      x1, x1, #0x2f7
0x00029ad0: a8 8c ff 97              bl       #0xcd70                    ; -> SoilJsonGetInt@plt
0x00029ad4: 60 8e 06 b8              str      w0, [x19, #0x68]!
0x00029ad8: e0 03 14 aa              mov      x0, x20
0x00029adc: 25 90 ff 97              bl       #0xdb70                    ; -> SoilMode@plt
0x00029ae0: 1f 08 00 71              cmp      w0, #2
0x00029ae4: e1 00 00 54              b.ne     #0x29b00
0x00029ae8: e0 03 13 aa              mov      x0, x19
0x00029aec: e1 03 14 aa              mov      x1, x20
0x00029af0: fd 7b 42 a9              ldp      x29, x30, [sp, #0x20]
0x00029af4: f4 4f 41 a9              ldp      x20, x19, [sp, #0x10]
0x00029af8: f6 57 c3 a8              ldp      x22, x21, [sp], #0x30
0x00029afc: 75 8d ff 17              b        #0xd0d0                    ; -> SoilWriteBinInt@plt
0x00029b00: fd 7b 42 a9              ldp      x29, x30, [sp, #0x20]
0x00029b04: f4 4f 41 a9              ldp      x20, x19, [sp, #0x10]
0x00029b08: f6 57 c3 a8              ldp      x22, x21, [sp], #0x30
0x00029b0c: c0 03 5f d6              ret      
