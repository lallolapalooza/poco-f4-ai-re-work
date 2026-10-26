; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : ComponentConvolutionLoad (0x2d838)
; start   : 0x2d838
; size    : 0x728
; bytes   : 0x728   instructions: 458
; generated: 2026-10-23T23:37:52

0x0002d838: ff c3 03 d1              sub      sp, sp, #0xf0
0x0002d83c: fc 6f 09 a9              stp      x28, x27, [sp, #0x90]
0x0002d840: fa 67 0a a9              stp      x26, x25, [sp, #0xa0]
0x0002d844: f8 5f 0b a9              stp      x24, x23, [sp, #0xb0]
0x0002d848: f6 57 0c a9              stp      x22, x21, [sp, #0xc0]
0x0002d84c: f4 4f 0d a9              stp      x20, x19, [sp, #0xd0]
0x0002d850: fd 7b 0e a9              stp      x29, x30, [sp, #0xe0]
0x0002d854: fd 83 03 91              add      x29, sp, #0xe0
0x0002d858: 48 d0 3b d5              mrs      x8, tpidr_el0
0x0002d85c: 08 15 40 f9              ldr      x8, [x8, #0x28]
0x0002d860: f3 03 00 aa              mov      x19, x0
0x0002d864: f9 03 02 2a              mov      w25, w2
0x0002d868: f4 03 01 aa              mov      x20, x1
0x0002d86c: a8 83 1a f8              stur     x8, [x29, #-0x58]
0x0002d870: 75 1a 40 f9              ldr      x21, [x19, #0x30]
0x0002d874: e0 03 15 aa              mov      x0, x21
0x0002d878: be 80 ff 97              bl       #0xdb70                    ; -> SoilMode@plt
0x0002d87c: 1f 04 00 71              cmp      w0, #1
0x0002d880: a1 07 00 54              b.ne     #0x2d974
0x0002d884: e0 03 15 aa              mov      x0, x21
0x0002d888: 96 7f ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x0002d88c: f6 03 00 2a              mov      w22, w0
0x0002d890: e0 03 15 aa              mov      x0, x21
0x0002d894: 93 7f ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x0002d898: f7 03 00 2a              mov      w23, w0
0x0002d89c: e0 03 15 aa              mov      x0, x21
0x0002d8a0: 90 7f ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x0002d8a4: f4 03 00 2a              mov      w20, w0
0x0002d8a8: e0 03 15 aa              mov      x0, x21
0x0002d8ac: 8d 7f ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x0002d8b0: fb 03 15 aa              mov      x27, x21
0x0002d8b4: f5 03 00 2a              mov      w21, w0
0x0002d8b8: e0 03 1b aa              mov      x0, x27
0x0002d8bc: 89 7f ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x0002d8c0: f8 03 00 2a              mov      w24, w0
0x0002d8c4: e0 03 1b aa              mov      x0, x27
0x0002d8c8: 86 7f ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x0002d8cc: f9 03 00 2a              mov      w25, w0
0x0002d8d0: e0 03 1b aa              mov      x0, x27
0x0002d8d4: 83 7f ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x0002d8d8: fa 03 00 2a              mov      w26, w0
0x0002d8dc: e0 03 1b aa              mov      x0, x27
0x0002d8e0: 80 7f ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x0002d8e4: 60 72 00 b9              str      w0, [x19, #0x70]
0x0002d8e8: e0 03 1b aa              mov      x0, x27
0x0002d8ec: 7d 7f ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x0002d8f0: 60 76 00 b9              str      w0, [x19, #0x74]
0x0002d8f4: e0 03 1b aa              mov      x0, x27
0x0002d8f8: 7a 7f ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x0002d8fc: 60 7a 00 b9              str      w0, [x19, #0x78]
0x0002d900: e0 03 1b aa              mov      x0, x27
0x0002d904: 77 7f ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x0002d908: 60 7e 00 b9              str      w0, [x19, #0x7c]
0x0002d90c: e0 03 1b aa              mov      x0, x27
0x0002d910: 74 7f ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x0002d914: 60 82 00 b9              str      w0, [x19, #0x80]
0x0002d918: e0 03 1b aa              mov      x0, x27
0x0002d91c: e1 03 16 2a              mov      w1, w22
0x0002d920: 2c 7e ff 97              bl       #0xd1d0                    ; -> SoilLoadBinArrayInt@plt
0x0002d924: 60 46 00 f9              str      x0, [x19, #0x88]
0x0002d928: e0 03 1b aa              mov      x0, x27
0x0002d92c: e1 03 17 2a              mov      w1, w23
0x0002d930: 28 7e ff 97              bl       #0xd1d0                    ; -> SoilLoadBinArrayInt@plt
0x0002d934: 60 56 00 f9              str      x0, [x19, #0xa8]
0x0002d938: e0 03 1b aa              mov      x0, x27
0x0002d93c: e1 03 14 2a              mov      w1, w20
0x0002d940: 24 7e ff 97              bl       #0xd1d0                    ; -> SoilLoadBinArrayInt@plt
0x0002d944: 08 7f 15 1b              mul      w8, w24, w21
0x0002d948: 08 7d 19 1b              mul      w8, w8, w25
0x0002d94c: 60 52 00 f9              str      x0, [x19, #0xa0]
0x0002d950: 01 7d 1a 1b              mul      w1, w8, w26
0x0002d954: e0 03 1b aa              mov      x0, x27
0x0002d958: 7e 7d ff 97              bl       #0xcf50                    ; -> SoilLoadBinArrayFloat@plt
0x0002d95c: 61 72 40 b9              ldr      w1, [x19, #0x70]
0x0002d960: 60 5a 00 f9              str      x0, [x19, #0xb0]
0x0002d964: e0 03 1b aa              mov      x0, x27
0x0002d968: 7a 7d ff 97              bl       #0xcf50                    ; -> SoilLoadBinArrayFloat@plt
0x0002d96c: 60 5e 00 f9              str      x0, [x19, #0xb8]
0x0002d970: 6e 01 00 14              b        #0x2df28                   ; -> ComponentConvolutionLoad+0x6f0
0x0002d974: 01 01 00 b0              adrp     x1, #0x4e000
0x0002d978: 21 08 1e 91              add      x1, x1, #0x782
0x0002d97c: e0 03 14 aa              mov      x0, x20
0x0002d980: f5 0b 00 f9              str      x21, [sp, #0x10]
0x0002d984: ef 7e ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x0002d988: e1 03 19 2a              mov      w1, w25
0x0002d98c: 29 7e ff 97              bl       #0xd230                    ; -> SoilJsonGetArrayItem@plt
0x0002d990: 01 01 00 d0              adrp     x1, #0x4f000
0x0002d994: 21 20 09 91              add      x1, x1, #0x248
0x0002d998: f5 03 00 aa              mov      x21, x0
0x0002d99c: f5 7c ff 97              bl       #0xcd70                    ; -> SoilJsonGetInt@plt
0x0002d9a0: f8 03 13 aa              mov      x24, x19
0x0002d9a4: 01 01 00 b0              adrp     x1, #0x4e000
0x0002d9a8: 00 0f 07 b8              str      w0, [x24, #0x70]!
0x0002d9ac: 21 80 3d 91              add      x1, x1, #0xf60
0x0002d9b0: e0 03 15 aa              mov      x0, x21
0x0002d9b4: e3 7e ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x0002d9b8: f6 03 00 aa              mov      x22, x0
0x0002d9bc: 31 7d ff 97              bl       #0xce80                    ; -> SoilJsonGetArraySize@plt
0x0002d9c0: f7 03 00 2a              mov      w23, w0
0x0002d9c4: b7 43 1a b8              stur     w23, [x29, #-0x5c]
0x0002d9c8: 08 03 5c f8              ldur     x8, [x24, #-0x40]
0x0002d9cc: e1 7e 7e 93              sbfiz    x1, x23, #2, #0x20
0x0002d9d0: 00 01 40 f9              ldr      x0, [x8]
0x0002d9d4: c3 7d ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x0002d9d8: f8 07 00 f9              str      x24, [sp, #8]
0x0002d9dc: 00 0f 00 f9              str      x0, [x24, #0x18]
0x0002d9e0: ff 06 00 71              cmp      w23, #1
0x0002d9e4: 6b 01 00 54              b.lt     #0x2da10
0x0002d9e8: f7 03 1f aa              mov      x23, xzr
0x0002d9ec: e0 03 16 aa              mov      x0, x22
0x0002d9f0: e1 03 17 2a              mov      w1, w23
0x0002d9f4: 0b 80 ff 97              bl       #0xda20                    ; -> SoilJsonGetArrayInt@plt
0x0002d9f8: 68 46 40 f9              ldr      x8, [x19, #0x88]
0x0002d9fc: 00 79 37 b8              str      w0, [x8, x23, lsl #2]
0x0002da00: a8 43 9a b8              ldursw   x8, [x29, #-0x5c]
0x0002da04: f7 06 00 91              add      x23, x23, #1
0x0002da08: ff 02 08 eb              cmp      x23, x8
0x0002da0c: 0b ff ff 54              b.lt     #0x2d9ec
0x0002da10: 01 01 00 b0              adrp     x1, #0x4e000
0x0002da14: 21 38 35 91              add      x1, x1, #0xd4e
0x0002da18: e0 03 15 aa              mov      x0, x21
0x0002da1c: c9 7e ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x0002da20: f6 03 00 aa              mov      x22, x0
0x0002da24: 17 7d ff 97              bl       #0xce80                    ; -> SoilJsonGetArraySize@plt
0x0002da28: f7 03 00 2a              mov      w23, w0
0x0002da2c: b7 03 1a b8              stur     w23, [x29, #-0x60]
0x0002da30: 68 1a 40 f9              ldr      x8, [x19, #0x30]
0x0002da34: e1 7e 7e 93              sbfiz    x1, x23, #2, #0x20
0x0002da38: 00 01 40 f9              ldr      x0, [x8]
0x0002da3c: a9 7d ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x0002da40: 60 56 00 f9              str      x0, [x19, #0xa8]
0x0002da44: ff 06 00 71              cmp      w23, #1
0x0002da48: 6b 01 00 54              b.lt     #0x2da74
0x0002da4c: f7 03 1f aa              mov      x23, xzr
0x0002da50: e0 03 16 aa              mov      x0, x22
0x0002da54: e1 03 17 2a              mov      w1, w23
0x0002da58: f2 7f ff 97              bl       #0xda20                    ; -> SoilJsonGetArrayInt@plt
0x0002da5c: 68 56 40 f9              ldr      x8, [x19, #0xa8]
0x0002da60: 00 79 37 b8              str      w0, [x8, x23, lsl #2]
0x0002da64: a8 03 9a b8              ldursw   x8, [x29, #-0x60]
0x0002da68: f7 06 00 91              add      x23, x23, #1
0x0002da6c: ff 02 08 eb              cmp      x23, x8
0x0002da70: 0b ff ff 54              b.lt     #0x2da50
0x0002da74: 01 01 00 b0              adrp     x1, #0x4e000
0x0002da78: 21 58 35 91              add      x1, x1, #0xd56
0x0002da7c: e0 03 15 aa              mov      x0, x21
0x0002da80: b0 7e ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x0002da84: f5 03 00 aa              mov      x21, x0
0x0002da88: fe 7c ff 97              bl       #0xce80                    ; -> SoilJsonGetArraySize@plt
0x0002da8c: f6 03 00 2a              mov      w22, w0
0x0002da90: b6 c3 19 b8              stur     w22, [x29, #-0x64]
0x0002da94: 68 1a 40 f9              ldr      x8, [x19, #0x30]
0x0002da98: c1 7e 7e 93              sbfiz    x1, x22, #2, #0x20
0x0002da9c: 00 01 40 f9              ldr      x0, [x8]
0x0002daa0: 90 7d ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x0002daa4: 60 52 00 f9              str      x0, [x19, #0xa0]
0x0002daa8: df 06 00 71              cmp      w22, #1
0x0002daac: 6b 01 00 54              b.lt     #0x2dad8
0x0002dab0: f6 03 1f aa              mov      x22, xzr
0x0002dab4: e0 03 15 aa              mov      x0, x21
0x0002dab8: e1 03 16 2a              mov      w1, w22
0x0002dabc: d9 7f ff 97              bl       #0xda20                    ; -> SoilJsonGetArrayInt@plt
0x0002dac0: 68 52 40 f9              ldr      x8, [x19, #0xa0]
0x0002dac4: 00 79 36 b8              str      w0, [x8, x22, lsl #2]
0x0002dac8: a8 c3 99 b8              ldursw   x8, [x29, #-0x64]
0x0002dacc: d6 06 00 91              add      x22, x22, #1
0x0002dad0: df 02 08 eb              cmp      x22, x8
0x0002dad4: 0b ff ff 54              b.lt     #0x2dab4
0x0002dad8: 68 1a 40 f9              ldr      x8, [x19, #0x30]
0x0002dadc: 69 72 80 b9              ldrsw    x9, [x19, #0x70]
0x0002dae0: 00 01 40 f9              ldr      x0, [x8]
0x0002dae4: 21 f1 7d d3              lsl      x1, x9, #3
0x0002dae8: 7e 7d ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x0002daec: 68 1a 40 f9              ldr      x8, [x19, #0x30]
0x0002daf0: 60 5a 00 f9              str      x0, [x19, #0xb0]
0x0002daf4: 69 72 80 b9              ldrsw    x9, [x19, #0x70]
0x0002daf8: 00 01 40 f9              ldr      x0, [x8]
0x0002dafc: 21 f5 7e d3              lsl      x1, x9, #2
0x0002db00: 78 7d ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x0002db04: 01 01 00 b0              adrp     x1, #0x4e000
0x0002db08: 60 5e 00 f9              str      x0, [x19, #0xb8]
0x0002db0c: 21 fc 31 91              add      x1, x1, #0xc7f
0x0002db10: e0 03 14 aa              mov      x0, x20
0x0002db14: 8b 7e ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x0002db18: f7 03 00 aa              mov      x23, x0
0x0002db1c: d9 7c ff 97              bl       #0xce80                    ; -> SoilJsonGetArraySize@plt
0x0002db20: f8 03 00 2a              mov      w24, w0
0x0002db24: 1f 07 00 71              cmp      w24, #1
0x0002db28: eb 17 00 54              b.lt     #0x2de24
0x0002db2c: bb 09 00 f0              adrp     x27, #0x164000
0x0002db30: fa 03 1f 2a              mov      w26, wzr
0x0002db34: 7b f7 1d 91              add      x27, x27, #0x77d
0x0002db38: f9 2f 00 b9              str      w25, [sp, #0x2c]
0x0002db3c: f7 13 00 f9              str      x23, [sp, #0x20]
0x0002db40: f8 1f 00 b9              str      w24, [sp, #0x1c]
0x0002db44: e0 03 17 aa              mov      x0, x23
0x0002db48: e1 03 1a 2a              mov      w1, w26
0x0002db4c: b9 7d ff 97              bl       #0xd230                    ; -> SoilJsonGetArrayItem@plt
0x0002db50: e1 03 1b aa              mov      x1, x27
0x0002db54: f4 03 00 aa              mov      x20, x0
0x0002db58: 7a 7e ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x0002db5c: 08 28 40 b9              ldr      w8, [x0, #0x28]
0x0002db60: 1f 01 19 6b              cmp      w8, w25
0x0002db64: a1 15 00 54              b.ne     #0x2de18
0x0002db68: 01 01 00 d0              adrp     x1, #0x4f000
0x0002db6c: e0 03 14 aa              mov      x0, x20
0x0002db70: 21 44 09 91              add      x1, x1, #0x251
0x0002db74: fa 47 00 b9              str      w26, [sp, #0x44]
0x0002db78: f4 1f 00 f9              str      x20, [sp, #0x38]
0x0002db7c: 71 7e ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x0002db80: f4 03 00 aa              mov      x20, x0
0x0002db84: bf 7c ff 97              bl       #0xce80                    ; -> SoilJsonGetArraySize@plt
0x0002db88: f6 03 00 2a              mov      w22, w0
0x0002db8c: e0 03 14 aa              mov      x0, x20
0x0002db90: e1 03 1f 2a              mov      w1, wzr
0x0002db94: b6 83 19 b8              stur     w22, [x29, #-0x68]
0x0002db98: f4 27 00 f9              str      x20, [sp, #0x48]
0x0002db9c: a5 7d ff 97              bl       #0xd230                    ; -> SoilJsonGetArrayItem@plt
0x0002dba0: f4 03 00 aa              mov      x20, x0
0x0002dba4: b7 7c ff 97              bl       #0xce80                    ; -> SoilJsonGetArraySize@plt
0x0002dba8: f5 03 00 2a              mov      w21, w0
0x0002dbac: e0 03 14 aa              mov      x0, x20
0x0002dbb0: e1 03 1f 2a              mov      w1, wzr
0x0002dbb4: b5 43 19 b8              stur     w21, [x29, #-0x6c]
0x0002dbb8: 9e 7d ff 97              bl       #0xd230                    ; -> SoilJsonGetArrayItem@plt
0x0002dbbc: f4 03 00 aa              mov      x20, x0
0x0002dbc0: b0 7c ff 97              bl       #0xce80                    ; -> SoilJsonGetArraySize@plt
0x0002dbc4: fa 03 00 2a              mov      w26, w0
0x0002dbc8: e0 03 14 aa              mov      x0, x20
0x0002dbcc: e1 03 1f 2a              mov      w1, wzr
0x0002dbd0: fa 73 00 b9              str      w26, [sp, #0x70]
0x0002dbd4: 97 7d ff 97              bl       #0xd230                    ; -> SoilJsonGetArrayItem@plt
0x0002dbd8: aa 7c ff 97              bl       #0xce80                    ; -> SoilJsonGetArraySize@plt
0x0002dbdc: f4 03 00 2a              mov      w20, w0
0x0002dbe0: f4 6f 00 b9              str      w20, [sp, #0x6c]
0x0002dbe4: 68 72 40 b9              ldr      w8, [x19, #0x70]
0x0002dbe8: 7a d2 0f 29              stp      w26, w20, [x19, #0x7c]
0x0002dbec: df 06 00 71              cmp      w22, #1
0x0002dbf0: 68 76 00 b9              str      w8, [x19, #0x74]
0x0002dbf4: f5 33 00 f9              str      x21, [sp, #0x60]
0x0002dbf8: 75 7a 00 b9              str      w21, [x19, #0x78]
0x0002dbfc: ab 0f 00 54              b.lt     #0x2ddf0
0x0002dc00: e8 33 40 f9              ldr      x8, [sp, #0x60]
0x0002dc04: f9 03 1f aa              mov      x25, xzr
0x0002dc08: 5c 7f 40 93              sxtw     x28, w26
0x0002dc0c: 58 7f 7d 93              sbfiz    x24, x26, #3, #0x20
0x0002dc10: 1b 7d 40 93              sxtw     x27, w8
0x0002dc14: 08 7d 7d 93              sbfiz    x8, x8, #3, #0x20
0x0002dc18: e8 2f 00 f9              str      x8, [sp, #0x58]
0x0002dc1c: 95 7e 7e 93              sbfiz    x21, x20, #2, #0x20
0x0002dc20: c8 7e 40 93              sxtw     x8, w22
0x0002dc24: f6 1b 00 f9              str      x22, [sp, #0x30]
0x0002dc28: e8 2b 00 f9              str      x8, [sp, #0x50]
0x0002dc2c: 68 1a 40 f9              ldr      x8, [x19, #0x30]
0x0002dc30: e1 2f 40 f9              ldr      x1, [sp, #0x58]
0x0002dc34: 00 01 40 f9              ldr      x0, [x8]
0x0002dc38: 2a 7d ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x0002dc3c: 68 5a 40 f9              ldr      x8, [x19, #0xb0]
0x0002dc40: 00 79 39 f8              str      x0, [x8, x25, lsl #3]
0x0002dc44: e8 33 40 f9              ldr      x8, [sp, #0x60]
0x0002dc48: 1f 05 00 71              cmp      w8, #1
0x0002dc4c: 4b 03 00 54              b.lt     #0x2dcb4
0x0002dc50: f7 03 1f aa              mov      x23, xzr
0x0002dc54: 68 1a 40 f9              ldr      x8, [x19, #0x30]
0x0002dc58: e1 03 18 aa              mov      x1, x24
0x0002dc5c: 00 01 40 f9              ldr      x0, [x8]
0x0002dc60: 20 7d ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x0002dc64: 68 5a 40 f9              ldr      x8, [x19, #0xb0]
0x0002dc68: 5f 07 00 71              cmp      w26, #1
0x0002dc6c: 08 79 79 f8              ldr      x8, [x8, x25, lsl #3]
0x0002dc70: 00 79 37 f8              str      x0, [x8, x23, lsl #3]
0x0002dc74: ab 01 00 54              b.lt     #0x2dca8
0x0002dc78: f6 03 1f aa              mov      x22, xzr
0x0002dc7c: 68 1a 40 f9              ldr      x8, [x19, #0x30]
0x0002dc80: e1 03 15 aa              mov      x1, x21
0x0002dc84: 00 01 40 f9              ldr      x0, [x8]
0x0002dc88: 16 7d ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x0002dc8c: 68 5a 40 f9              ldr      x8, [x19, #0xb0]
0x0002dc90: 08 79 79 f8              ldr      x8, [x8, x25, lsl #3]
0x0002dc94: 08 79 77 f8              ldr      x8, [x8, x23, lsl #3]
0x0002dc98: 00 79 36 f8              str      x0, [x8, x22, lsl #3]
0x0002dc9c: d6 06 00 91              add      x22, x22, #1
0x0002dca0: df 02 1c eb              cmp      x22, x28
0x0002dca4: cb fe ff 54              b.lt     #0x2dc7c
0x0002dca8: f7 06 00 91              add      x23, x23, #1
0x0002dcac: ff 02 1b eb              cmp      x23, x27
0x0002dcb0: 2b fd ff 54              b.lt     #0x2dc54
0x0002dcb4: e8 2b 40 f9              ldr      x8, [sp, #0x50]
0x0002dcb8: 39 07 00 91              add      x25, x25, #1
0x0002dcbc: 3f 03 08 eb              cmp      x25, x8
0x0002dcc0: 6b fb ff 54              b.lt     #0x2dc2c
0x0002dcc4: e9 1b 40 f9              ldr      x9, [sp, #0x30]
0x0002dcc8: 3f 05 00 71              cmp      w9, #1
0x0002dccc: 2b 09 00 54              b.lt     #0x2ddf0
0x0002dcd0: f7 03 1f aa              mov      x23, xzr
0x0002dcd4: 28 7d 40 93              sxtw     x8, w9
0x0002dcd8: f6 03 14 2a              mov      w22, w20
0x0002dcdc: e8 2b 00 f9              str      x8, [sp, #0x50]
0x0002dce0: e0 27 40 f9              ldr      x0, [sp, #0x48]
0x0002dce4: e1 03 17 2a              mov      w1, w23
0x0002dce8: 52 7d ff 97              bl       #0xd230                    ; -> SoilJsonGetArrayItem@plt
0x0002dcec: e8 33 40 f9              ldr      x8, [sp, #0x60]
0x0002dcf0: e0 2f 00 f9              str      x0, [sp, #0x58]
0x0002dcf4: 1f 05 00 71              cmp      w8, #1
0x0002dcf8: 4b 04 00 54              b.lt     #0x2dd80
0x0002dcfc: f5 03 1f aa              mov      x21, xzr
0x0002dd00: e0 2f 40 f9              ldr      x0, [sp, #0x58]
0x0002dd04: e1 03 15 2a              mov      w1, w21
0x0002dd08: 4a 7d ff 97              bl       #0xd230                    ; -> SoilJsonGetArrayItem@plt
0x0002dd0c: f8 03 00 aa              mov      x24, x0
0x0002dd10: 5f 07 00 71              cmp      w26, #1
0x0002dd14: eb 02 00 54              b.lt     #0x2dd70
0x0002dd18: f9 03 1f aa              mov      x25, xzr
0x0002dd1c: e0 03 18 aa              mov      x0, x24
0x0002dd20: e1 03 19 2a              mov      w1, w25
0x0002dd24: 43 7d ff 97              bl       #0xd230                    ; -> SoilJsonGetArrayItem@plt
0x0002dd28: fb 03 00 aa              mov      x27, x0
0x0002dd2c: 9f 06 00 71              cmp      w20, #1
0x0002dd30: ab 01 00 54              b.lt     #0x2dd64
0x0002dd34: fc 03 1f aa              mov      x28, xzr
0x0002dd38: e0 03 1b aa              mov      x0, x27
0x0002dd3c: e1 03 1c 2a              mov      w1, w28
0x0002dd40: 84 7e ff 97              bl       #0xd750                    ; -> SoilJsonGetArrayFloat@plt
0x0002dd44: 68 5a 40 f9              ldr      x8, [x19, #0xb0]
0x0002dd48: 08 79 77 f8              ldr      x8, [x8, x23, lsl #3]
0x0002dd4c: 08 79 75 f8              ldr      x8, [x8, x21, lsl #3]
0x0002dd50: 08 79 79 f8              ldr      x8, [x8, x25, lsl #3]
0x0002dd54: 00 79 3c bc              str      s0, [x8, x28, lsl #2]
0x0002dd58: 9c 07 00 91              add      x28, x28, #1
0x0002dd5c: df 02 1c 6b              cmp      w22, w28
0x0002dd60: c1 fe ff 54              b.ne     #0x2dd38
0x0002dd64: 39 07 00 91              add      x25, x25, #1
0x0002dd68: 3f 03 1a 6b              cmp      w25, w26
0x0002dd6c: 81 fd ff 54              b.ne     #0x2dd1c
0x0002dd70: e8 33 40 f9              ldr      x8, [sp, #0x60]
0x0002dd74: b5 06 00 91              add      x21, x21, #1
0x0002dd78: bf 02 08 6b              cmp      w21, w8
0x0002dd7c: 21 fc ff 54              b.ne     #0x2dd00
0x0002dd80: e8 2b 40 f9              ldr      x8, [sp, #0x50]
0x0002dd84: f7 06 00 91              add      x23, x23, #1
0x0002dd88: ff 02 08 eb              cmp      x23, x8
0x0002dd8c: ab fa ff 54              b.lt     #0x2dce0
0x0002dd90: e0 1f 40 f9              ldr      x0, [sp, #0x38]
0x0002dd94: b6 83 99 b8              ldursw   x22, [x29, #-0x68]
0x0002dd98: 01 01 00 b0              adrp     x1, #0x4e000
0x0002dd9c: 21 cc 3e 91              add      x1, x1, #0xfb3
0x0002dda0: e8 7d ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x0002dda4: f9 2f 40 b9              ldr      w25, [sp, #0x2c]
0x0002dda8: f7 13 40 f9              ldr      x23, [sp, #0x20]
0x0002ddac: f8 1f 40 b9              ldr      w24, [sp, #0x1c]
0x0002ddb0: fa 47 40 b9              ldr      w26, [sp, #0x44]
0x0002ddb4: bb 09 00 f0              adrp     x27, #0x164000
0x0002ddb8: f4 03 00 aa              mov      x20, x0
0x0002ddbc: 7b f7 1d 91              add      x27, x27, #0x77d
0x0002ddc0: df 06 00 71              cmp      w22, #1
0x0002ddc4: ab 02 00 54              b.lt     #0x2de18
0x0002ddc8: f5 03 1f aa              mov      x21, xzr
0x0002ddcc: e0 03 14 aa              mov      x0, x20
0x0002ddd0: e1 03 15 2a              mov      w1, w21
0x0002ddd4: 5f 7e ff 97              bl       #0xd750                    ; -> SoilJsonGetArrayFloat@plt
0x0002ddd8: 68 5e 40 f9              ldr      x8, [x19, #0xb8]
0x0002dddc: 00 79 35 bc              str      s0, [x8, x21, lsl #2]
0x0002dde0: b5 06 00 91              add      x21, x21, #1
0x0002dde4: bf 02 16 eb              cmp      x21, x22
0x0002dde8: 2b ff ff 54              b.lt     #0x2ddcc
0x0002ddec: 0b 00 00 14              b        #0x2de18                   ; -> ComponentConvolutionLoad+0x5e0
0x0002ddf0: e0 1f 40 f9              ldr      x0, [sp, #0x38]
0x0002ddf4: 01 01 00 b0              adrp     x1, #0x4e000
0x0002ddf8: 21 cc 3e 91              add      x1, x1, #0xfb3
0x0002ddfc: d1 7d ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x0002de00: f9 2f 40 b9              ldr      w25, [sp, #0x2c]
0x0002de04: f7 13 40 f9              ldr      x23, [sp, #0x20]
0x0002de08: f8 1f 40 b9              ldr      w24, [sp, #0x1c]
0x0002de0c: fa 47 40 b9              ldr      w26, [sp, #0x44]
0x0002de10: bb 09 00 f0              adrp     x27, #0x164000
0x0002de14: 7b f7 1d 91              add      x27, x27, #0x77d
0x0002de18: 5a 07 00 11              add      w26, w26, #1
0x0002de1c: 5f 03 18 6b              cmp      w26, w24
0x0002de20: 21 e9 ff 54              b.ne     #0x2db44
0x0002de24: f4 0b 40 f9              ldr      x20, [sp, #0x10]
0x0002de28: e0 03 14 aa              mov      x0, x20
0x0002de2c: 51 7f ff 97              bl       #0xdb70                    ; -> SoilMode@plt
0x0002de30: 1f 08 00 71              cmp      w0, #2
0x0002de34: a1 07 00 54              b.ne     #0x2df28
0x0002de38: a0 73 01 d1              sub      x0, x29, #0x5c
0x0002de3c: e1 03 14 aa              mov      x1, x20
0x0002de40: a4 7c ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x0002de44: a0 83 01 d1              sub      x0, x29, #0x60
0x0002de48: e1 03 14 aa              mov      x1, x20
0x0002de4c: a1 7c ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x0002de50: a0 93 01 d1              sub      x0, x29, #0x64
0x0002de54: e1 03 14 aa              mov      x1, x20
0x0002de58: 9e 7c ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x0002de5c: a0 a3 01 d1              sub      x0, x29, #0x68
0x0002de60: e1 03 14 aa              mov      x1, x20
0x0002de64: 9b 7c ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x0002de68: a0 b3 01 d1              sub      x0, x29, #0x6c
0x0002de6c: e1 03 14 aa              mov      x1, x20
0x0002de70: 98 7c ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x0002de74: e0 c3 01 91              add      x0, sp, #0x70
0x0002de78: e1 03 14 aa              mov      x1, x20
0x0002de7c: 95 7c ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x0002de80: e0 b3 01 91              add      x0, sp, #0x6c
0x0002de84: e1 03 14 aa              mov      x1, x20
0x0002de88: 92 7c ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x0002de8c: e0 07 40 f9              ldr      x0, [sp, #8]
0x0002de90: e1 03 14 aa              mov      x1, x20
0x0002de94: 8f 7c ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x0002de98: 60 d2 01 91              add      x0, x19, #0x74
0x0002de9c: e1 03 14 aa              mov      x1, x20
0x0002dea0: 8c 7c ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x0002dea4: 60 e2 01 91              add      x0, x19, #0x78
0x0002dea8: e1 03 14 aa              mov      x1, x20
0x0002deac: 89 7c ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x0002deb0: 60 f2 01 91              add      x0, x19, #0x7c
0x0002deb4: e1 03 14 aa              mov      x1, x20
0x0002deb8: 86 7c ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x0002debc: 60 02 02 91              add      x0, x19, #0x80
0x0002dec0: e1 03 14 aa              mov      x1, x20
0x0002dec4: 83 7c ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x0002dec8: 60 46 40 f9              ldr      x0, [x19, #0x88]
0x0002decc: a1 43 5a b8              ldur     w1, [x29, #-0x5c]
0x0002ded0: e2 03 14 aa              mov      x2, x20
0x0002ded4: 7b 7e ff 97              bl       #0xd8c0                    ; -> SoilWriteBinArrayInt@plt
0x0002ded8: 60 56 40 f9              ldr      x0, [x19, #0xa8]
0x0002dedc: a1 03 5a b8              ldur     w1, [x29, #-0x60]
0x0002dee0: e2 03 14 aa              mov      x2, x20
0x0002dee4: 77 7e ff 97              bl       #0xd8c0                    ; -> SoilWriteBinArrayInt@plt
0x0002dee8: 60 52 40 f9              ldr      x0, [x19, #0xa0]
0x0002deec: a1 c3 59 b8              ldur     w1, [x29, #-0x64]
0x0002def0: e2 03 14 aa              mov      x2, x20
0x0002def4: 73 7e ff 97              bl       #0xd8c0                    ; -> SoilWriteBinArrayInt@plt
0x0002def8: a9 a3 72 29              ldp      w9, w8, [x29, #-0x6c]
0x0002defc: eb ab 4d 29              ldp      w11, w10, [sp, #0x6c]
0x0002df00: 60 5a 40 f9              ldr      x0, [x19, #0xb0]
0x0002df04: e2 03 14 aa              mov      x2, x20
0x0002df08: 28 7d 08 1b              mul      w8, w9, w8
0x0002df0c: 08 7d 0a 1b              mul      w8, w8, w10
0x0002df10: 01 7d 0b 1b              mul      w1, w8, w11
0x0002df14: b7 7b ff 97              bl       #0xcdf0                    ; -> SoilWriteBinArrayFloat@plt
0x0002df18: 60 5e 40 f9              ldr      x0, [x19, #0xb8]
0x0002df1c: 61 72 40 b9              ldr      w1, [x19, #0x70]
0x0002df20: e2 03 14 aa              mov      x2, x20
0x0002df24: b3 7b ff 97              bl       #0xcdf0                    ; -> SoilWriteBinArrayFloat@plt
0x0002df28: 48 d0 3b d5              mrs      x8, tpidr_el0
0x0002df2c: 08 15 40 f9              ldr      x8, [x8, #0x28]
0x0002df30: a9 83 5a f8              ldur     x9, [x29, #-0x58]
0x0002df34: 1f 01 09 eb              cmp      x8, x9
0x0002df38: 21 01 00 54              b.ne     #0x2df5c
0x0002df3c: fd 7b 4e a9              ldp      x29, x30, [sp, #0xe0]
0x0002df40: f4 4f 4d a9              ldp      x20, x19, [sp, #0xd0]
0x0002df44: f6 57 4c a9              ldp      x22, x21, [sp, #0xc0]
0x0002df48: f8 5f 4b a9              ldp      x24, x23, [sp, #0xb0]
0x0002df4c: fa 67 4a a9              ldp      x26, x25, [sp, #0xa0]
0x0002df50: fc 6f 49 a9              ldp      x28, x27, [sp, #0x90]
0x0002df54: ff c3 03 91              add      sp, sp, #0xf0
0x0002df58: c0 03 5f d6              ret      
0x0002df5c: 15 7d ff 97              bl       #0xd3b0                    ; -> __stack_chk_fail@plt
