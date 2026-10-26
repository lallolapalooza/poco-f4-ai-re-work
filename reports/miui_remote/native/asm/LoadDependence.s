; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : LoadDependence (0x30960)
; start   : 0x30960
; size    : 0x1a4
; bytes   : 0x1a4   instructions: 105
; generated: 2026-10-23T23:51:12

0x00030960: f7 0f 1c f8              str      x23, [sp, #-0x40]!
0x00030964: f6 57 01 a9              stp      x22, x21, [sp, #0x10]
0x00030968: f4 4f 02 a9              stp      x20, x19, [sp, #0x20]
0x0003096c: fd 7b 03 a9              stp      x29, x30, [sp, #0x30]
0x00030970: fd c3 00 91              add      x29, sp, #0x30
0x00030974: f4 03 03 aa              mov      x20, x3
0x00030978: f3 03 00 aa              mov      x19, x0
0x0003097c: e0 03 14 aa              mov      x0, x20
0x00030980: f5 03 02 2a              mov      w21, w2
0x00030984: f6 03 01 aa              mov      x22, x1
0x00030988: 7a 74 ff 97              bl       #0xdb70                    ; -> SoilMode@plt
0x0003098c: 1f 04 00 71              cmp      w0, #1
0x00030990: 01 03 00 54              b.ne     #0x309f0
0x00030994: e0 03 14 aa              mov      x0, x20
0x00030998: 52 73 ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x0003099c: e8 03 00 2a              mov      w8, w0
0x000309a0: 68 12 00 b9              str      w8, [x19, #0x10]
0x000309a4: 1f 05 00 71              cmp      w8, #1
0x000309a8: 6b 09 00 54              b.lt     #0x30ad4
0x000309ac: e0 03 14 aa              mov      x0, x20
0x000309b0: e1 03 08 2a              mov      w1, w8
0x000309b4: 07 72 ff 97              bl       #0xd1d0                    ; -> SoilLoadBinArrayInt@plt
0x000309b8: 68 12 40 b9              ldr      w8, [x19, #0x10]
0x000309bc: 60 06 00 f9              str      x0, [x19, #8]
0x000309c0: 1f 05 00 71              cmp      w8, #1
0x000309c4: 8b 08 00 54              b.lt     #0x30ad4
0x000309c8: f4 03 1f aa              mov      x20, xzr
0x000309cc: 75 62 00 91              add      x21, x19, #0x18
0x000309d0: 60 1a 40 f9              ldr      x0, [x19, #0x30]
0x000309d4: 7b 73 ff 97              bl       #0xd7c0                    ; -> TensorNew@plt
0x000309d8: a0 7a 34 f8              str      x0, [x21, x20, lsl #3]
0x000309dc: 68 12 80 b9              ldrsw    x8, [x19, #0x10]
0x000309e0: 94 06 00 91              add      x20, x20, #1
0x000309e4: 9f 02 08 eb              cmp      x20, x8
0x000309e8: 4b ff ff 54              b.lt     #0x309d0
0x000309ec: 3a 00 00 14              b        #0x30ad4                   ; -> LoadDependence+0x174
0x000309f0: e1 00 00 d0              adrp     x1, #0x4e000
0x000309f4: 21 08 1e 91              add      x1, x1, #0x782
0x000309f8: e0 03 16 aa              mov      x0, x22
0x000309fc: d1 72 ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x00030a00: e1 03 15 2a              mov      w1, w21
0x00030a04: 0b 72 ff 97              bl       #0xd230                    ; -> SoilJsonGetArrayItem@plt
0x00030a08: e1 00 00 f0              adrp     x1, #0x4f000
0x00030a0c: 21 7c 22 91              add      x1, x1, #0x89f
0x00030a10: cc 72 ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x00030a14: f5 03 00 aa              mov      x21, x0
0x00030a18: b5 03 00 b4              cbz      x21, #0x30a8c
0x00030a1c: e0 03 15 aa              mov      x0, x21
0x00030a20: 18 71 ff 97              bl       #0xce80                    ; -> SoilJsonGetArraySize@plt
0x00030a24: 60 12 00 b9              str      w0, [x19, #0x10]
0x00030a28: 1f 04 00 71              cmp      w0, #1
0x00030a2c: 0b 03 00 54              b.lt     #0x30a8c
0x00030a30: 68 1a 40 f9              ldr      x8, [x19, #0x30]
0x00030a34: 01 7c 7e 93              sbfiz    x1, x0, #2, #0x20
0x00030a38: 08 01 40 f9              ldr      x8, [x8]
0x00030a3c: e0 03 08 aa              mov      x0, x8
0x00030a40: a8 71 ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x00030a44: 68 12 40 b9              ldr      w8, [x19, #0x10]
0x00030a48: 60 06 00 f9              str      x0, [x19, #8]
0x00030a4c: 1f 05 00 71              cmp      w8, #1
0x00030a50: eb 01 00 54              b.lt     #0x30a8c
0x00030a54: f6 03 1f aa              mov      x22, xzr
0x00030a58: 77 62 00 91              add      x23, x19, #0x18
0x00030a5c: e0 03 15 aa              mov      x0, x21
0x00030a60: e1 03 16 2a              mov      w1, w22
0x00030a64: ef 73 ff 97              bl       #0xda20                    ; -> SoilJsonGetArrayInt@plt
0x00030a68: 68 06 40 f9              ldr      x8, [x19, #8]
0x00030a6c: 00 79 36 b8              str      w0, [x8, x22, lsl #2]
0x00030a70: 60 1a 40 f9              ldr      x0, [x19, #0x30]
0x00030a74: 53 73 ff 97              bl       #0xd7c0                    ; -> TensorNew@plt
0x00030a78: e0 7a 36 f8              str      x0, [x23, x22, lsl #3]
0x00030a7c: 68 12 80 b9              ldrsw    x8, [x19, #0x10]
0x00030a80: d6 06 00 91              add      x22, x22, #1
0x00030a84: df 02 08 eb              cmp      x22, x8
0x00030a88: ab fe ff 54              b.lt     #0x30a5c
0x00030a8c: e0 03 14 aa              mov      x0, x20
0x00030a90: 38 74 ff 97              bl       #0xdb70                    ; -> SoilMode@plt
0x00030a94: 1f 08 00 71              cmp      w0, #2
0x00030a98: e1 01 00 54              b.ne     #0x30ad4
0x00030a9c: e0 03 13 aa              mov      x0, x19
0x00030aa0: 08 0c 41 b8              ldr      w8, [x0, #0x10]!
0x00030aa4: 1f 05 00 71              cmp      w8, #1
0x00030aa8: 0b 02 00 54              b.lt     #0x30ae8
0x00030aac: e1 03 14 aa              mov      x1, x20
0x00030ab0: 88 71 ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x00030ab4: 60 06 40 f9              ldr      x0, [x19, #8]
0x00030ab8: 61 12 40 b9              ldr      w1, [x19, #0x10]
0x00030abc: e2 03 14 aa              mov      x2, x20
0x00030ac0: fd 7b 43 a9              ldp      x29, x30, [sp, #0x30]
0x00030ac4: f4 4f 42 a9              ldp      x20, x19, [sp, #0x20]
0x00030ac8: f6 57 41 a9              ldp      x22, x21, [sp, #0x10]
0x00030acc: f7 07 44 f8              ldr      x23, [sp], #0x40
0x00030ad0: 7c 73 ff 17              b        #0xd8c0                    ; -> SoilWriteBinArrayInt@plt
0x00030ad4: fd 7b 43 a9              ldp      x29, x30, [sp, #0x30]
0x00030ad8: f4 4f 42 a9              ldp      x20, x19, [sp, #0x20]
0x00030adc: f6 57 41 a9              ldp      x22, x21, [sp, #0x10]
0x00030ae0: f7 07 44 f8              ldr      x23, [sp], #0x40
0x00030ae4: c0 03 5f d6              ret      
0x00030ae8: 1f 00 00 b9              str      wzr, [x0]
0x00030aec: e1 03 14 aa              mov      x1, x20
0x00030af0: fd 7b 43 a9              ldp      x29, x30, [sp, #0x30]
0x00030af4: f4 4f 42 a9              ldp      x20, x19, [sp, #0x20]
0x00030af8: f6 57 41 a9              ldp      x22, x21, [sp, #0x10]
0x00030afc: f7 07 44 f8              ldr      x23, [sp], #0x40
0x00030b00: 74 71 ff 17              b        #0xd0d0                    ; -> SoilWriteBinInt@plt
