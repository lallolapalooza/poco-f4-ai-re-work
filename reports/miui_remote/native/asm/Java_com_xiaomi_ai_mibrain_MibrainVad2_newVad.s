; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : Java_com_xiaomi_ai_mibrain_MibrainVad2_newVad (0xdd64)
; start   : 0xdd64
; size    : 0x74
; bytes   : 0x74   instructions: 29
; generated: 2026-10-23T23:27:52

0x0000dd64: fd 7b bd a9              stp      x29, x30, [sp, #-0x30]!
0x0000dd68: 00 01 80 d2              mov      x0, #8
0x0000dd6c: fd 03 00 91              mov      x29, sp
0x0000dd70: f3 53 01 a9              stp      x19, x20, [sp, #0x10]
0x0000dd74: f5 13 00 f9              str      x21, [sp, #0x20]
0x0000dd78: f4 03 03 2a              mov      w20, w3
0x0000dd7c: f5 03 02 2a              mov      w21, w2
0x0000dd80: 10 fc ff 97              bl       #0xcdc0                    ; -> _Znwm@plt
0x0000dd84: f3 03 00 aa              mov      x19, x0
0x0000dd88: 2a ff ff 97              bl       #0xda30                    ; -> _ZN7CornVad10CornEngineC1Ev@plt
0x0000dd8c: e1 01 00 90              adrp     x1, #0x49000
0x0000dd90: e0 03 13 aa              mov      x0, x19
0x0000dd94: 21 c0 30 91              add      x1, x1, #0xc30
0x0000dd98: 22 00 80 d2              mov      x2, #1
0x0000dd9c: 91 ff ff 97              bl       #0xdbe0                    ; -> _ZN7CornVad10CornEngine4InitEPKcm@plt
0x0000dda0: e0 03 13 aa              mov      x0, x19
0x0000dda4: a1 7e 40 93              sxtw     x1, w21
0x0000dda8: 82 7e 40 93              sxtw     x2, w20
0x0000ddac: ad ff ff 97              bl       #0xdc60                    ; -> _ZN7CornVad10CornEngine17SetVoiceSilLengthEmm@plt
0x0000ddb0: f5 13 40 f9              ldr      x21, [sp, #0x20]
0x0000ddb4: e0 03 13 aa              mov      x0, x19
0x0000ddb8: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x0000ddbc: fd 7b c3 a8              ldp      x29, x30, [sp], #0x30
0x0000ddc0: c0 03 5f d6              ret      
0x0000ddc4: f4 03 00 aa              mov      x20, x0
0x0000ddc8: e0 03 13 aa              mov      x0, x19
0x0000ddcc: 5d fe ff 97              bl       #0xd740                    ; -> _ZdlPv@plt
0x0000ddd0: e0 03 14 aa              mov      x0, x20
0x0000ddd4: 83 e7 00 94              bl       #0x47be0
