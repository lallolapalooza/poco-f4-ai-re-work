; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : GetComponentNum (0x30730)
; start   : 0x30730
; size    : 0xc8
; bytes   : 0xc8   instructions: 50
; generated: 2026-10-23T23:37:43

0x00030730: ff c3 00 d1              sub      sp, sp, #0x30
0x00030734: f3 0b 00 f9              str      x19, [sp, #0x10]
0x00030738: fd 7b 02 a9              stp      x29, x30, [sp, #0x20]
0x0003073c: fd 83 00 91              add      x29, sp, #0x20
0x00030740: 48 d0 3b d5              mrs      x8, tpidr_el0
0x00030744: 08 15 40 f9              ldr      x8, [x8, #0x28]
0x00030748: f3 03 01 aa              mov      x19, x1
0x0003074c: e8 07 00 f9              str      x8, [sp, #8]
0x00030750: 68 06 40 f9              ldr      x8, [x19, #8]
0x00030754: 09 05 40 f9              ldr      x9, [x8, #8]
0x00030758: 2a 15 00 51              sub      w10, w9, #5
0x0003075c: 5f 09 00 71              cmp      w10, #2
0x00030760: a8 01 00 54              b.hi     #0x30794
0x00030764: 4a 7d 40 93              sxtw     x10, w10
0x00030768: 4a 05 00 d1              sub      x10, x10, #1
0x0003076c: 5f 05 00 f1              cmp      x10, #1
0x00030770: 28 01 00 54              b.hi     #0x30794
0x00030774: 0a 09 40 f9              ldr      x10, [x8, #0x10]
0x00030778: 2b fd 60 93              asr      x11, x9, #0x20
0x0003077c: 29 fd 60 d3              lsr      x9, x9, #0x20
0x00030780: 29 11 00 11              add      w9, w9, #4
0x00030784: 40 69 6b b8              ldr      w0, [x10, x11]
0x00030788: 09 0d 00 b9              str      w9, [x8, #0xc]
0x0003078c: e0 07 00 b9              str      w0, [sp, #4]
0x00030790: 10 00 00 14              b        #0x307d0                   ; -> GetComponentNum+0xa0
0x00030794: e1 00 00 d0              adrp     x1, #0x4e000
0x00030798: 21 08 1e 91              add      x1, x1, #0x782
0x0003079c: 69 73 ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x000307a0: b8 71 ff 97              bl       #0xce80                    ; -> SoilJsonGetArraySize@plt
0x000307a4: e0 07 00 b9              str      w0, [sp, #4]
0x000307a8: 68 06 40 f9              ldr      x8, [x19, #8]
0x000307ac: 09 09 40 b9              ldr      w9, [x8, #8]
0x000307b0: 3f 15 00 71              cmp      w9, #5
0x000307b4: e1 00 00 54              b.ne     #0x307d0
0x000307b8: 03 01 40 f9              ldr      x3, [x8]
0x000307bc: e0 13 00 91              add      x0, sp, #4
0x000307c0: e1 03 1e 32              mov      w1, #4
0x000307c4: e2 03 00 32              mov      w2, #1
0x000307c8: f2 74 ff 97              bl       #0xdb90                    ; -> fwrite@plt
0x000307cc: e0 07 40 b9              ldr      w0, [sp, #4]
0x000307d0: 48 d0 3b d5              mrs      x8, tpidr_el0
0x000307d4: 08 15 40 f9              ldr      x8, [x8, #0x28]
0x000307d8: e9 07 40 f9              ldr      x9, [sp, #8]
0x000307dc: 1f 01 09 eb              cmp      x8, x9
0x000307e0: a1 00 00 54              b.ne     #0x307f4
0x000307e4: fd 7b 42 a9              ldp      x29, x30, [sp, #0x20]
0x000307e8: f3 0b 40 f9              ldr      x19, [sp, #0x10]
0x000307ec: ff c3 00 91              add      sp, sp, #0x30
0x000307f0: c0 03 5f d6              ret      
0x000307f4: ef 72 ff 97              bl       #0xd3b0                    ; -> __stack_chk_fail@plt
