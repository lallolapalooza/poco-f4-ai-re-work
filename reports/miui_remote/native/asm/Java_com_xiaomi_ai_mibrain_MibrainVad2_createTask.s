; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : Java_com_xiaomi_ai_mibrain_MibrainVad2_createTask (0xddd8)
; start   : 0xddd8
; size    : 0x14
; bytes   : 0x14   instructions: 5
; generated: 2026-10-23T23:27:55

0x0000ddd8: 62 00 00 b4              cbz      x2, #0xdde4
0x0000dddc: e0 03 02 aa              mov      x0, x2
0x0000dde0: 38 ff ff 17              b        #0xdac0                    ; -> _ZN7CornVad10CornEngine10CreateTaskEv@plt
0x0000dde4: 00 00 80 92              mov      x0, #-1
0x0000dde8: c0 03 5f d6              ret      
