; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : address 0x2f734
; start   : 0x2f734
; size    : 0x188
; bytes   : 0x188   instructions: 98
; generated: 2026-10-23T23:35:50

0x0002f734: f8 5f bc a9              stp      x24, x23, [sp, #-0x40]!
0x0002f738: f6 57 01 a9              stp      x22, x21, [sp, #0x10]
0x0002f73c: f4 4f 02 a9              stp      x20, x19, [sp, #0x20]
0x0002f740: fd 7b 03 a9              stp      x29, x30, [sp, #0x30]
0x0002f744: fd c3 00 91              add      x29, sp, #0x30
0x0002f748: 16 00 40 f9              ldr      x22, [x0]
0x0002f74c: f3 03 02 2a              mov      w19, w2
0x0002f750: e8 03 01 aa              mov      x8, x1
0x0002f754: 69 1e 00 12              and      w9, w19, #0xff
0x0002f758: c0 12 00 f9              str      x0, [x22, #0x20]
0x0002f75c: 3f 1d 00 71              cmp      w9, #7
0x0002f760: e8 08 00 54              b.hi     #0x2f87c
0x0002f764: 0a 01 00 90              adrp     x10, #0x4f000
0x0002f768: 4a d1 1d 91              add      x10, x10, #0x774
0x0002f76c: 49 79 a9 b8              ldrsw    x9, [x10, x9, lsl #2]
0x0002f770: d4 0e 40 f9              ldr      x20, [x22, #0x18]
0x0002f774: 29 01 0a 8b              add      x9, x9, x10
0x0002f778: 20 01 1f d6              br       x9
0x0002f77c: e0 03 08 aa              mov      x0, x8
0x0002f780: e1 03 14 aa              mov      x1, x20
0x0002f784: bb 77 ff 97              bl       #0xd670                    ; -> TxtFile2Json@plt
0x0002f788: 0b 00 00 14              b        #0x2f7b4
0x0002f78c: e0 03 08 aa              mov      x0, x8
0x0002f790: e1 03 14 aa              mov      x1, x20
0x0002f794: af 75 ff 97              bl       #0xce50                    ; -> ZipFile2Json@plt
0x0002f798: 07 00 00 14              b        #0x2f7b4
0x0002f79c: e1 03 1f 32              mov      w1, #2
0x0002f7a0: 02 00 00 14              b        #0x2f7a8
0x0002f7a4: e1 07 00 32              mov      w1, #3
0x0002f7a8: e0 03 08 aa              mov      x0, x8
0x0002f7ac: e2 03 14 aa              mov      x2, x20
0x0002f7b0: ac 74 ff 97              bl       #0xca60                    ; -> Binn2Json@plt
0x0002f7b4: e8 03 00 aa              mov      x8, x0
0x0002f7b8: f7 03 16 aa              mov      x23, x22
0x0002f7bc: e8 0e 01 f8              str      x8, [x23, #0x10]!
0x0002f7c0: e8 06 00 b4              cbz      x8, #0x2f89c
0x0002f7c4: e0 03 08 aa              mov      x0, x8
0x0002f7c8: e1 03 14 aa              mov      x1, x20
0x0002f7cc: a9 78 ff 97              bl       #0xda70                    ; -> GetComponentNum@plt
0x0002f7d0: c0 0a 00 b9              str      w0, [x22, #8]
0x0002f7d4: 88 02 40 f9              ldr      x8, [x20]
0x0002f7d8: 01 7c 7d 93              sbfiz    x1, x0, #3, #0x20
0x0002f7dc: e0 03 08 aa              mov      x0, x8
0x0002f7e0: 40 76 ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x0002f7e4: c8 0a 40 b9              ldr      w8, [x22, #8]
0x0002f7e8: c0 02 00 f9              str      x0, [x22]
0x0002f7ec: 1f 05 00 71              cmp      w8, #1
0x0002f7f0: ab 02 00 54              b.lt     #0x2f844
0x0002f7f4: c1 0a 40 f9              ldr      x1, [x22, #0x10]
0x0002f7f8: e2 03 1f 2a              mov      w2, wzr
0x0002f7fc: e3 03 14 aa              mov      x3, x20
0x0002f800: d8 74 ff 97              bl       #0xcb60                    ; -> ComponentLoad@plt
0x0002f804: c8 0a 40 b9              ldr      w8, [x22, #8]
0x0002f808: 1f 09 00 71              cmp      w8, #2
0x0002f80c: cb 01 00 54              b.lt     #0x2f844
0x0002f810: f5 03 00 32              mov      w21, #1
0x0002f814: f8 03 1d 32              mov      w24, #8
0x0002f818: c8 02 40 f9              ldr      x8, [x22]
0x0002f81c: c1 0a 40 f9              ldr      x1, [x22, #0x10]
0x0002f820: e2 03 15 2a              mov      w2, w21
0x0002f824: e3 03 14 aa              mov      x3, x20
0x0002f828: 00 01 18 8b              add      x0, x8, x24
0x0002f82c: cd 74 ff 97              bl       #0xcb60                    ; -> ComponentLoad@plt
0x0002f830: c8 0a 80 b9              ldrsw    x8, [x22, #8]
0x0002f834: b5 06 00 91              add      x21, x21, #1
0x0002f838: 18 23 00 91              add      x24, x24, #8
0x0002f83c: bf 02 08 eb              cmp      x21, x8
0x0002f840: cb fe ff 54              b.lt     #0x2f818
0x0002f844: 68 1e 00 12              and      w8, w19, #0xff
0x0002f848: 1f 19 00 71              cmp      w8, #6
0x0002f84c: e2 00 00 54              b.hs     #0x2f868
0x0002f850: e0 02 40 f9              ldr      x0, [x23]
0x0002f854: fd 7b 43 a9              ldp      x29, x30, [sp, #0x30]
0x0002f858: f4 4f 42 a9              ldp      x20, x19, [sp, #0x20]
0x0002f85c: f6 57 41 a9              ldp      x22, x21, [sp, #0x10]
0x0002f860: f8 5f c4 a8              ldp      x24, x23, [sp], #0x40
0x0002f864: df 76 ff 17              b        #0xd3e0                    ; -> SoilJsonDelete@plt
0x0002f868: fd 7b 43 a9              ldp      x29, x30, [sp, #0x30]
0x0002f86c: f4 4f 42 a9              ldp      x20, x19, [sp, #0x20]
0x0002f870: f6 57 41 a9              ldp      x22, x21, [sp, #0x10]
0x0002f874: f8 5f c4 a8              ldp      x24, x23, [sp], #0x40
0x0002f878: c0 03 5f d6              ret      
0x0002f87c: 00 01 00 90              adrp     x0, #0x4f000
0x0002f880: 02 01 00 90              adrp     x2, #0x4f000
0x0002f884: e3 00 00 b0              adrp     x3, #0x4c000
0x0002f888: 00 c0 1e 91              add      x0, x0, #0x7b0
0x0002f88c: 42 24 21 91              add      x2, x2, #0x849
0x0002f890: 63 10 3f 91              add      x3, x3, #0xfc4
0x0002f894: e1 06 80 52              mov      w1, #0x37
0x0002f898: 46 75 ff 97              bl       #0xcdb0                    ; -> __assert2@plt
0x0002f89c: 00 01 00 90              adrp     x0, #0x4f000
0x0002f8a0: 02 01 00 90              adrp     x2, #0x4f000
0x0002f8a4: 03 01 00 90              adrp     x3, #0x4f000
0x0002f8a8: 00 c0 1e 91              add      x0, x0, #0x7b0
0x0002f8ac: 42 24 21 91              add      x2, x2, #0x849
0x0002f8b0: 63 5c 21 91              add      x3, x3, #0x857
0x0002f8b4: 21 07 80 52              mov      w1, #0x39
0x0002f8b8: 3e 75 ff 97              bl       #0xcdb0                    ; -> __assert2@plt
