; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : CornEngineInit (0x3128c)
; start   : 0x3128c
; size    : 0xe0
; bytes   : 0xe0   instructions: 56
; generated: 2026-10-23T23:27:56

0x0003128c: f4 4f be a9              stp      x20, x19, [sp, #-0x20]!
0x00031290: fd 7b 01 a9              stp      x29, x30, [sp, #0x10]
0x00031294: fd 43 00 91              add      x29, sp, #0x10
0x00031298: f4 03 00 aa              mov      x20, x0
0x0003129c: 00 4c 9d 52              mov      w0, #0xea60
0x000312a0: f3 03 01 aa              mov      x19, x1
0x000312a4: 5b 72 ff 97              bl       #0xdc10                    ; -> CornMpoolCreate@plt
0x000312a8: 60 86 01 f9              str      x0, [x19, #0x308]
0x000312ac: e0 03 1f aa              mov      x0, xzr
0x000312b0: e1 03 13 aa              mov      x1, x19
0x000312b4: 27 6e ff 97              bl       #0xcb50                    ; -> CornLoadModelBin@plt
0x000312b8: 40 05 00 35              cbnz     w0, #0x31360
0x000312bc: e0 03 14 aa              mov      x0, x20
0x000312c0: e1 03 13 aa              mov      x1, x19
0x000312c4: 67 6f ff 97              bl       #0xd060                    ; -> CornLoadGlobalConf@plt
0x000312c8: 60 42 03 91              add      x0, x19, #0xd0
0x000312cc: 61 c2 05 91              add      x1, x19, #0x170
0x000312d0: 62 22 06 91              add      x2, x19, #0x188
0x000312d4: e3 03 13 aa              mov      x3, x19
0x000312d8: 5a 71 ff 97              bl       #0xd840                    ; -> CornFeatureInitialize@plt
0x000312dc: 20 04 00 35              cbnz     w0, #0x31360
0x000312e0: 68 62 40 f9              ldr      x8, [x19, #0xc0]
0x000312e4: 62 02 07 91              add      x2, x19, #0x1c0
0x000312e8: 21 05 80 52              mov      w1, #0x29
0x000312ec: e3 03 13 aa              mov      x3, x19
0x000312f0: 00 01 40 f9              ldr      x0, [x8]
0x000312f4: 23 6e ff 97              bl       #0xcb80                    ; -> CornCmvnInitialize@plt
0x000312f8: 40 03 00 35              cbnz     w0, #0x31360
0x000312fc: 60 86 41 f9              ldr      x0, [x19, #0x308]
0x00031300: 01 e8 83 52              mov      w1, #0x1f40
0x00031304: 7f 22 03 b9              str      wzr, [x19, #0x320]
0x00031308: 7f d2 03 b9              str      wzr, [x19, #0x3d0]
0x0003130c: 81 6f ff 97              bl       #0xd110                    ; -> CornMpoolAlloc@plt
0x00031310: 02 f4 81 52              mov      w2, #0xfa0
0x00031314: e1 03 1f 2a              mov      w1, wzr
0x00031318: 60 ae 01 f9              str      x0, [x19, #0x358]
0x0003131c: 7f 62 03 b9              str      wzr, [x19, #0x360]
0x00031320: 7f d6 03 b9              str      wzr, [x19, #0x3d4]
0x00031324: ef 71 ff 97              bl       #0xdae0                    ; -> memset@plt
0x00031328: e8 03 00 32              mov      w8, #1
0x0003132c: 69 f2 0b 91              add      x9, x19, #0x2fc
0x00031330: ea 03 1f 32              mov      w10, #2
0x00031334: 60 a2 0d 91              add      x0, x19, #0x368
0x00031338: 68 f2 03 b9              str      w8, [x19, #0x3f0]
0x0003133c: 2a 01 00 f9              str      x10, [x9]
0x00031340: 7f b6 0c 39              strb     wzr, [x19, #0x32d]
0x00031344: 3b 72 ff 97              bl       #0xdc30                    ; -> CornDecoderInit@plt
0x00031348: 61 be 43 b9              ldr      w1, [x19, #0x3bc]
0x0003134c: 62 c2 43 b9              ldr      w2, [x19, #0x3c0]
0x00031350: e0 00 00 d0              adrp     x0, #0x4f000
0x00031354: 00 40 2b 91              add      x0, x0, #0xad0
0x00031358: 1e 6e ff 97              bl       #0xcbd0                    ; -> printf@plt
0x0003135c: e0 03 1f 2a              mov      w0, wzr
0x00031360: fd 7b 41 a9              ldp      x29, x30, [sp, #0x10]
0x00031364: f4 4f c2 a8              ldp      x20, x19, [sp], #0x20
0x00031368: c0 03 5f d6              ret      
