; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : SoilInit (0x2f69c)
; start   : 0x2f69c
; size    : 0x98
; bytes   : 0x98   instructions: 38
; generated: 2026-10-23T23:30:01

0x0002f69c: f8 5f bc a9              stp      x24, x23, [sp, #-0x40]!
0x0002f6a0: f6 57 01 a9              stp      x22, x21, [sp, #0x10]
0x0002f6a4: f4 4f 02 a9              stp      x20, x19, [sp, #0x20]
0x0002f6a8: fd 7b 03 a9              stp      x29, x30, [sp, #0x30]
0x0002f6ac: fd c3 00 91              add      x29, sp, #0x30
0x0002f6b0: f6 03 00 aa              mov      x22, x0
0x0002f6b4: 00 05 80 52              mov      w0, #0x28
0x0002f6b8: f3 03 03 aa              mov      x19, x3
0x0002f6bc: f4 03 02 2a              mov      w20, w2
0x0002f6c0: f5 03 01 aa              mov      x21, x1
0x0002f6c4: e3 78 ff 97              bl       #0xda50                    ; -> malloc@plt
0x0002f6c8: f7 03 00 aa              mov      x23, x0
0x0002f6cc: e0 03 1c 32              mov      w0, #0x10
0x0002f6d0: d7 02 00 f9              str      x23, [x22]
0x0002f6d4: df 78 ff 97              bl       #0xda50                    ; -> malloc@plt
0x0002f6d8: f8 03 00 aa              mov      x24, x0
0x0002f6dc: e0 03 1b 32              mov      w0, #0x20
0x0002f6e0: f8 0e 00 f9              str      x24, [x23, #0x18]
0x0002f6e4: db 78 ff 97              bl       #0xda50                    ; -> malloc@plt
0x0002f6e8: 00 07 00 f9              str      x0, [x24, #8]
0x0002f6ec: e9 74 ff 97              bl       #0xca90                    ; -> SoilInitModelFile@plt
0x0002f6f0: e0 03 18 aa              mov      x0, x24
0x0002f6f4: 13 03 00 f9              str      x19, [x24]
0x0002f6f8: 96 78 ff 97              bl       #0xd950                    ; -> PlatformBufMalloc@plt
0x0002f6fc: 81 1e 00 12              and      w1, w20, #0xff
0x0002f700: e0 03 15 aa              mov      x0, x21
0x0002f704: e2 03 18 aa              mov      x2, x24
0x0002f708: 26 76 ff 97              bl       #0xcfa0                    ; -> SoilLoadBinRaw@plt
0x0002f70c: e0 03 16 aa              mov      x0, x22
0x0002f710: e1 03 15 aa              mov      x1, x21
0x0002f714: e2 03 14 2a              mov      w2, w20
0x0002f718: 07 00 00 94              bl       #0x2f734
0x0002f71c: df 0a 00 b9              str      wzr, [x22, #8]
0x0002f720: fd 7b 43 a9              ldp      x29, x30, [sp, #0x30]
0x0002f724: f4 4f 42 a9              ldp      x20, x19, [sp, #0x20]
0x0002f728: f6 57 41 a9              ldp      x22, x21, [sp, #0x10]
0x0002f72c: f8 5f c4 a8              ldp      x24, x23, [sp], #0x40
0x0002f730: c0 03 5f d6              ret      
