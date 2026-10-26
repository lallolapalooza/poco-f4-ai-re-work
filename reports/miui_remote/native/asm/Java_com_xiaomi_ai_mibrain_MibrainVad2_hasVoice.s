; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : Java_com_xiaomi_ai_mibrain_MibrainVad2_hasVoice (0xddec)
; start   : 0xddec
; size    : 0x124
; bytes   : 0x124   instructions: 73
; generated: 2026-10-23T23:27:53

0x0000ddec: fd 7b b9 a9              stp      x29, x30, [sp, #-0x70]!
0x0000ddf0: fd 03 00 91              mov      x29, sp
0x0000ddf4: f3 53 01 a9              stp      x19, x20, [sp, #0x10]
0x0000ddf8: f5 5b 02 a9              stp      x21, x22, [sp, #0x20]
0x0000ddfc: f7 63 03 a9              stp      x23, x24, [sp, #0x30]
0x0000de00: f9 23 00 f9              str      x25, [sp, #0x40]
0x0000de04: f3 03 00 aa              mov      x19, x0
0x0000de08: 62 07 00 b4              cbz      x2, #0xdef4
0x0000de0c: f9 03 03 aa              mov      x25, x3
0x0000de10: 03 00 40 f9              ldr      x3, [x0]
0x0000de14: f4 03 02 aa              mov      x20, x2
0x0000de18: e1 03 04 aa              mov      x1, x4
0x0000de1c: 02 00 80 d2              mov      x2, #0
0x0000de20: f6 03 06 aa              mov      x22, x6
0x0000de24: 63 e0 42 f9              ldr      x3, [x3, #0x5c0]
0x0000de28: f7 03 05 2a              mov      w23, w5
0x0000de2c: f5 03 04 aa              mov      x21, x4
0x0000de30: 60 00 3f d6              blr      x3
0x0000de34: f8 03 00 aa              mov      x24, x0
0x0000de38: e0 05 00 b4              cbz      x0, #0xdef4
0x0000de3c: a8 63 01 91              add      x8, x29, #0x58
0x0000de40: e0 03 14 aa              mov      x0, x20
0x0000de44: e1 03 19 aa              mov      x1, x25
0x0000de48: e2 03 18 aa              mov      x2, x24
0x0000de4c: e3 7e 40 93              sxtw     x3, w23
0x0000de50: 6c fb ff 97              bl       #0xcc00                    ; -> _ZN7CornVad10CornEngine11ProcessTaskEPvPcm@plt
0x0000de54: 64 02 40 f9              ldr      x4, [x19]
0x0000de58: e0 03 13 aa              mov      x0, x19
0x0000de5c: e1 03 15 aa              mov      x1, x21
0x0000de60: e2 03 18 aa              mov      x2, x24
0x0000de64: 03 00 80 52              mov      w3, #0
0x0000de68: 84 00 43 f9              ldr      x4, [x4, #0x600]
0x0000de6c: 80 00 3f d6              blr      x4
0x0000de70: 63 02 40 f9              ldr      x3, [x19]
0x0000de74: e1 03 16 aa              mov      x1, x22
0x0000de78: 02 00 80 d2              mov      x2, #0
0x0000de7c: e0 03 13 aa              mov      x0, x19
0x0000de80: 63 ec 42 f9              ldr      x3, [x3, #0x5d8]
0x0000de84: 60 00 3f d6              blr      x3
0x0000de88: e4 03 00 aa              mov      x4, x0
0x0000de8c: 65 02 40 f9              ldr      x5, [x19]
0x0000de90: e0 03 13 aa              mov      x0, x19
0x0000de94: a6 73 41 39              ldrb     w6, [x29, #0x5c]
0x0000de98: e1 03 16 aa              mov      x1, x22
0x0000de9c: a7 5b 40 b9              ldr      w7, [x29, #0x58]
0x0000dea0: e2 03 04 aa              mov      x2, x4
0x0000dea4: a5 0c 43 f9              ldr      x5, [x5, #0x618]
0x0000dea8: 03 00 80 52              mov      w3, #0
0x0000deac: 86 04 00 b9              str      w6, [x4, #4]
0x0000deb0: a6 63 40 b9              ldr      w6, [x29, #0x60]
0x0000deb4: 86 08 00 b9              str      w6, [x4, #8]
0x0000deb8: a6 67 40 b9              ldr      w6, [x29, #0x64]
0x0000debc: 86 0c 00 b9              str      w6, [x4, #0xc]
0x0000dec0: a6 6b 40 b9              ldr      w6, [x29, #0x68]
0x0000dec4: 86 10 00 b9              str      w6, [x4, #0x10]
0x0000dec8: a6 6f 40 b9              ldr      w6, [x29, #0x6c]
0x0000decc: 87 00 00 b9              str      w7, [x4]
0x0000ded0: 86 14 00 b9              str      w6, [x4, #0x14]
0x0000ded4: a0 00 3f d6              blr      x5
0x0000ded8: f9 23 40 f9              ldr      x25, [sp, #0x40]
0x0000dedc: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x0000dee0: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x0000dee4: f7 63 43 a9              ldp      x23, x24, [sp, #0x30]
0x0000dee8: 20 00 80 52              mov      w0, #1
0x0000deec: fd 7b c7 a8              ldp      x29, x30, [sp], #0x70
0x0000def0: c0 03 5f d6              ret      
0x0000def4: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x0000def8: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x0000defc: f7 63 43 a9              ldp      x23, x24, [sp, #0x30]
0x0000df00: f9 23 40 f9              ldr      x25, [sp, #0x40]
0x0000df04: 00 00 80 52              mov      w0, #0
0x0000df08: fd 7b c7 a8              ldp      x29, x30, [sp], #0x70
0x0000df0c: c0 03 5f d6              ret      
