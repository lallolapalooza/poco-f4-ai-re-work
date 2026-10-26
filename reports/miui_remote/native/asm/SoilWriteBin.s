; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : SoilWriteBin (0x2fed0)
; start   : 0x2fed0
; size    : 0xa0
; bytes   : 0xa0   instructions: 40
; generated: 2026-10-23T23:37:48

0x0002fed0: f8 5f bc a9              stp      x24, x23, [sp, #-0x40]!
0x0002fed4: f6 57 01 a9              stp      x22, x21, [sp, #0x10]
0x0002fed8: f4 4f 02 a9              stp      x20, x19, [sp, #0x20]
0x0002fedc: fd 7b 03 a9              stp      x29, x30, [sp, #0x30]
0x0002fee0: fd c3 00 91              add      x29, sp, #0x30
0x0002fee4: f6 03 00 aa              mov      x22, x0
0x0002fee8: 00 05 80 52              mov      w0, #0x28
0x0002feec: f3 03 03 2a              mov      w19, w3
0x0002fef0: f4 03 02 aa              mov      x20, x2
0x0002fef4: f5 03 01 aa              mov      x21, x1
0x0002fef8: d6 76 ff 97              bl       #0xda50                    ; -> malloc@plt
0x0002fefc: f7 03 00 aa              mov      x23, x0
0x0002ff00: e0 03 1c 32              mov      w0, #0x10
0x0002ff04: d7 02 00 f9              str      x23, [x22]
0x0002ff08: d2 76 ff 97              bl       #0xda50                    ; -> malloc@plt
0x0002ff0c: f8 03 00 aa              mov      x24, x0
0x0002ff10: e0 03 1b 32              mov      w0, #0x20
0x0002ff14: f8 0e 00 f9              str      x24, [x23, #0x18]
0x0002ff18: ce 76 ff 97              bl       #0xda50                    ; -> malloc@plt
0x0002ff1c: e1 00 00 b0              adrp     x1, #0x4c000
0x0002ff20: 1f 03 00 a9              stp      xzr, x0, [x24]
0x0002ff24: 21 90 3d 91              add      x1, x1, #0xf64
0x0002ff28: e0 03 14 aa              mov      x0, x20
0x0002ff2c: f5 76 ff 97              bl       #0xdb00                    ; -> fopen@plt
0x0002ff30: 08 07 40 f9              ldr      x8, [x24, #8]
0x0002ff34: 69 1e 00 12              and      w9, w19, #0xff
0x0002ff38: 3f 15 00 71              cmp      w9, #5
0x0002ff3c: e2 03 93 1a              csel     w2, wzr, w19, eq
0x0002ff40: 00 01 00 f9              str      x0, [x8]
0x0002ff44: e0 03 16 aa              mov      x0, x22
0x0002ff48: e1 03 15 aa              mov      x1, x21
0x0002ff4c: 09 09 00 b9              str      w9, [x8, #8]
0x0002ff50: 1f 09 00 f9              str      xzr, [x8, #0x10]
0x0002ff54: f8 fd ff 97              bl       #0x2f734
0x0002ff58: e0 03 16 aa              mov      x0, x22
0x0002ff5c: fd 7b 43 a9              ldp      x29, x30, [sp, #0x30]
0x0002ff60: f4 4f 42 a9              ldp      x20, x19, [sp, #0x20]
0x0002ff64: f6 57 41 a9              ldp      x22, x21, [sp, #0x10]
0x0002ff68: f8 5f c4 a8              ldp      x24, x23, [sp], #0x40
0x0002ff6c: 89 75 ff 17              b        #0xd590                    ; -> SoilDestroy@plt
