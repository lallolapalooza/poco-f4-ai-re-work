; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : ComponentPaddingLoad (0x29c40)
; start   : 0x29c40
; size    : 0x160
; bytes   : 0x160   instructions: 88
; generated: 2026-10-23T23:44:25

0x00029c40: f7 0f 1c f8              str      x23, [sp, #-0x40]!
0x00029c44: f6 57 01 a9              stp      x22, x21, [sp, #0x10]
0x00029c48: f4 4f 02 a9              stp      x20, x19, [sp, #0x20]
0x00029c4c: fd 7b 03 a9              stp      x29, x30, [sp, #0x30]
0x00029c50: fd c3 00 91              add      x29, sp, #0x30
0x00029c54: f4 03 00 aa              mov      x20, x0
0x00029c58: 93 1a 40 f9              ldr      x19, [x20, #0x30]
0x00029c5c: f5 03 02 2a              mov      w21, w2
0x00029c60: f6 03 01 aa              mov      x22, x1
0x00029c64: e0 03 13 aa              mov      x0, x19
0x00029c68: c2 8f ff 97              bl       #0xdb70                    ; -> SoilMode@plt
0x00029c6c: 1f 04 00 71              cmp      w0, #1
0x00029c70: 01 02 00 54              b.ne     #0x29cb0
0x00029c74: e0 03 13 aa              mov      x0, x19
0x00029c78: 9a 8e ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x00029c7c: 80 6a 00 b9              str      w0, [x20, #0x68]
0x00029c80: e0 03 13 aa              mov      x0, x19
0x00029c84: 97 8e ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x00029c88: 80 6e 00 b9              str      w0, [x20, #0x6c]
0x00029c8c: e0 03 13 aa              mov      x0, x19
0x00029c90: 94 8e ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x00029c94: 80 72 00 b9              str      w0, [x20, #0x70]
0x00029c98: e0 03 13 aa              mov      x0, x19
0x00029c9c: 91 8e ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x00029ca0: 1f 00 00 71              cmp      w0, #0
0x00029ca4: e8 07 9f 1a              cset     w8, ne
0x00029ca8: 88 d2 01 39              strb     w8, [x20, #0x74]
0x00029cac: 38 00 00 14              b        #0x29d8c                   ; -> ComponentPaddingLoad+0x14c
0x00029cb0: 21 01 00 b0              adrp     x1, #0x4e000
0x00029cb4: 21 08 1e 91              add      x1, x1, #0x782
0x00029cb8: e0 03 16 aa              mov      x0, x22
0x00029cbc: 21 8e ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x00029cc0: e1 03 15 2a              mov      w1, w21
0x00029cc4: 5b 8d ff 97              bl       #0xd230                    ; -> SoilJsonGetArrayItem@plt
0x00029cc8: 21 01 00 b0              adrp     x1, #0x4e000
0x00029ccc: 21 e8 23 91              add      x1, x1, #0x8fa
0x00029cd0: f6 03 00 aa              mov      x22, x0
0x00029cd4: 27 8c ff 97              bl       #0xcd70                    ; -> SoilJsonGetInt@plt
0x00029cd8: f5 03 14 aa              mov      x21, x20
0x00029cdc: 21 01 00 b0              adrp     x1, #0x4e000
0x00029ce0: a0 8e 06 b8              str      w0, [x21, #0x68]!
0x00029ce4: 21 1c 24 91              add      x1, x1, #0x907
0x00029ce8: e0 03 16 aa              mov      x0, x22
0x00029cec: 21 8c ff 97              bl       #0xcd70                    ; -> SoilJsonGetInt@plt
0x00029cf0: 21 01 00 b0              adrp     x1, #0x4e000
0x00029cf4: a0 06 00 b9              str      w0, [x21, #4]
0x00029cf8: 21 18 38 91              add      x1, x1, #0xe06
0x00029cfc: e0 03 16 aa              mov      x0, x22
0x00029d00: 10 8e ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x00029d04: c0 00 00 b4              cbz      x0, #0x29d1c
0x00029d08: 00 10 40 f9              ldr      x0, [x0, #0x20]
0x00029d0c: 21 01 00 b0              adrp     x1, #0x4e000
0x00029d10: 21 4c 38 91              add      x1, x1, #0xe13
0x00029d14: 3b 8e ff 97              bl       #0xd600                    ; -> strcmp@plt
0x00029d18: 80 00 00 34              cbz      w0, #0x29d28
0x00029d1c: e8 03 00 32              mov      w8, #1
0x00029d20: 88 72 00 b9              str      w8, [x20, #0x70]
0x00029d24: 02 00 00 14              b        #0x29d2c                   ; -> ComponentPaddingLoad+0xec
0x00029d28: 9f 72 00 b9              str      wzr, [x20, #0x70]
0x00029d2c: e8 03 00 32              mov      w8, #1
0x00029d30: f6 03 14 aa              mov      x22, x20
0x00029d34: e0 03 13 aa              mov      x0, x19
0x00029d38: c8 4e 07 38              strb     w8, [x22, #0x74]!
0x00029d3c: 8d 8f ff 97              bl       #0xdb70                    ; -> SoilMode@plt
0x00029d40: 1f 08 00 71              cmp      w0, #2
0x00029d44: 41 02 00 54              b.ne     #0x29d8c
0x00029d48: e0 03 15 aa              mov      x0, x21
0x00029d4c: e1 03 13 aa              mov      x1, x19
0x00029d50: b7 12 00 91              add      x23, x21, #4
0x00029d54: df 8c ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x00029d58: e0 03 17 aa              mov      x0, x23
0x00029d5c: e1 03 13 aa              mov      x1, x19
0x00029d60: dc 8c ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x00029d64: 80 c2 01 91              add      x0, x20, #0x70
0x00029d68: e1 03 13 aa              mov      x1, x19
0x00029d6c: d9 8c ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x00029d70: e0 03 16 aa              mov      x0, x22
0x00029d74: e1 03 13 aa              mov      x1, x19
0x00029d78: fd 7b 43 a9              ldp      x29, x30, [sp, #0x30]
0x00029d7c: f4 4f 42 a9              ldp      x20, x19, [sp, #0x20]
0x00029d80: f6 57 41 a9              ldp      x22, x21, [sp, #0x10]
0x00029d84: f7 07 44 f8              ldr      x23, [sp], #0x40
0x00029d88: d2 8c ff 17              b        #0xd0d0                    ; -> SoilWriteBinInt@plt
0x00029d8c: fd 7b 43 a9              ldp      x29, x30, [sp, #0x30]
0x00029d90: f4 4f 42 a9              ldp      x20, x19, [sp, #0x20]
0x00029d94: f6 57 41 a9              ldp      x22, x21, [sp, #0x10]
0x00029d98: f7 07 44 f8              ldr      x23, [sp], #0x40
0x00029d9c: c0 03 5f d6              ret      
