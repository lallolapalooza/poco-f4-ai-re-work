; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : ComponentAffineLoad (0x2e3bc)
; start   : 0x2e3bc
; size    : 0x374
; bytes   : 0x374   instructions: 221
; generated: 2026-10-23T23:37:50

0x0002e3bc: ff 03 02 d1              sub      sp, sp, #0x80
0x0002e3c0: fc 6f 02 a9              stp      x28, x27, [sp, #0x20]
0x0002e3c4: fa 67 03 a9              stp      x26, x25, [sp, #0x30]
0x0002e3c8: f8 5f 04 a9              stp      x24, x23, [sp, #0x40]
0x0002e3cc: f6 57 05 a9              stp      x22, x21, [sp, #0x50]
0x0002e3d0: f4 4f 06 a9              stp      x20, x19, [sp, #0x60]
0x0002e3d4: fd 7b 07 a9              stp      x29, x30, [sp, #0x70]
0x0002e3d8: fd c3 01 91              add      x29, sp, #0x70
0x0002e3dc: f3 03 00 aa              mov      x19, x0
0x0002e3e0: 75 1a 40 f9              ldr      x21, [x19, #0x30]
0x0002e3e4: f7 03 02 2a              mov      w23, w2
0x0002e3e8: f8 03 01 aa              mov      x24, x1
0x0002e3ec: e0 03 15 aa              mov      x0, x21
0x0002e3f0: e0 7d ff 97              bl       #0xdb70                    ; -> SoilMode@plt
0x0002e3f4: 1f 04 00 71              cmp      w0, #1
0x0002e3f8: 21 02 00 54              b.ne     #0x2e43c
0x0002e3fc: e0 03 15 aa              mov      x0, x21
0x0002e400: b8 7c ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x0002e404: 60 6a 00 b9              str      w0, [x19, #0x68]
0x0002e408: e0 03 15 aa              mov      x0, x21
0x0002e40c: b5 7c ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x0002e410: 68 6a 40 b9              ldr      w8, [x19, #0x68]
0x0002e414: 60 6e 00 b9              str      w0, [x19, #0x6c]
0x0002e418: 01 7d 00 1b              mul      w1, w8, w0
0x0002e41c: e0 03 15 aa              mov      x0, x21
0x0002e420: cc 7a ff 97              bl       #0xcf50                    ; -> SoilLoadBinArrayFloat@plt
0x0002e424: 61 6e 40 b9              ldr      w1, [x19, #0x6c]
0x0002e428: 60 3a 00 f9              str      x0, [x19, #0x70]
0x0002e42c: e0 03 15 aa              mov      x0, x21
0x0002e430: c8 7a ff 97              bl       #0xcf50                    ; -> SoilLoadBinArrayFloat@plt
0x0002e434: 60 3e 00 f9              str      x0, [x19, #0x78]
0x0002e438: 9e 00 00 14              b        #0x2e6b0                   ; -> ComponentAffineLoad+0x2f4
0x0002e43c: 01 01 00 90              adrp     x1, #0x4e000
0x0002e440: 21 08 1e 91              add      x1, x1, #0x782
0x0002e444: e0 03 18 aa              mov      x0, x24
0x0002e448: 3e 7c ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x0002e44c: e1 03 17 2a              mov      w1, w23
0x0002e450: 78 7b ff 97              bl       #0xd230                    ; -> SoilJsonGetArrayItem@plt
0x0002e454: 01 01 00 b0              adrp     x1, #0x4f000
0x0002e458: 21 98 13 91              add      x1, x1, #0x4e6
0x0002e45c: f6 03 00 aa              mov      x22, x0
0x0002e460: 44 7a ff 97              bl       #0xcd70                    ; -> SoilJsonGetInt@plt
0x0002e464: f4 03 13 aa              mov      x20, x19
0x0002e468: 01 01 00 b0              adrp     x1, #0x4f000
0x0002e46c: 80 8e 06 b8              str      w0, [x20, #0x68]!
0x0002e470: 21 dc 0b 91              add      x1, x1, #0x2f7
0x0002e474: e0 03 16 aa              mov      x0, x22
0x0002e478: 3e 7a ff 97              bl       #0xcd70                    ; -> SoilJsonGetInt@plt
0x0002e47c: 88 82 5c f8              ldur     x8, [x20, #-0x38]
0x0002e480: 89 12 00 91              add      x9, x20, #4
0x0002e484: e9 07 00 f9              str      x9, [sp, #8]
0x0002e488: 89 02 80 b9              ldrsw    x9, [x20]
0x0002e48c: 80 06 00 b9              str      w0, [x20, #4]
0x0002e490: 08 01 40 f9              ldr      x8, [x8]
0x0002e494: 0a 7c 40 93              sxtw     x10, w0
0x0002e498: 49 7d 09 9b              mul      x9, x10, x9
0x0002e49c: 21 f5 7e d3              lsl      x1, x9, #2
0x0002e4a0: e0 03 08 aa              mov      x0, x8
0x0002e4a4: 0f 7b ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x0002e4a8: 88 82 5c f8              ldur     x8, [x20, #-0x38]
0x0002e4ac: 80 06 00 f9              str      x0, [x20, #8]
0x0002e4b0: 89 06 80 b9              ldrsw    x9, [x20, #4]
0x0002e4b4: 00 01 40 f9              ldr      x0, [x8]
0x0002e4b8: 21 f5 7e d3              lsl      x1, x9, #2
0x0002e4bc: 09 7b ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x0002e4c0: 01 01 00 90              adrp     x1, #0x4e000
0x0002e4c4: f4 0b 00 f9              str      x20, [sp, #0x10]
0x0002e4c8: 80 0a 00 f9              str      x0, [x20, #0x10]
0x0002e4cc: 21 fc 31 91              add      x1, x1, #0xc7f
0x0002e4d0: e0 03 18 aa              mov      x0, x24
0x0002e4d4: 1b 7c ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x0002e4d8: f9 03 00 aa              mov      x25, x0
0x0002e4dc: 69 7a ff 97              bl       #0xce80                    ; -> SoilJsonGetArraySize@plt
0x0002e4e0: fa 03 00 2a              mov      w26, w0
0x0002e4e4: 5f 07 00 71              cmp      w26, #1
0x0002e4e8: 0b 0b 00 54              b.lt     #0x2e648
0x0002e4ec: bc 09 00 d0              adrp     x28, #0x164000
0x0002e4f0: fb 03 1f 2a              mov      w27, wzr
0x0002e4f4: 9c f7 1d 91              add      x28, x28, #0x77d
0x0002e4f8: e0 03 19 aa              mov      x0, x25
0x0002e4fc: e1 03 1b 2a              mov      w1, w27
0x0002e500: 4c 7b ff 97              bl       #0xd230                    ; -> SoilJsonGetArrayItem@plt
0x0002e504: e1 03 1c aa              mov      x1, x28
0x0002e508: f8 03 00 aa              mov      x24, x0
0x0002e50c: 0d 7c ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x0002e510: 08 28 40 b9              ldr      w8, [x0, #0x28]
0x0002e514: 1f 01 17 6b              cmp      w8, w23
0x0002e518: a0 00 00 54              b.eq     #0x2e52c
0x0002e51c: 7b 07 00 11              add      w27, w27, #1
0x0002e520: 7f 03 1a 6b              cmp      w27, w26
0x0002e524: ab fe ff 54              b.lt     #0x2e4f8
0x0002e528: 48 00 00 14              b        #0x2e648                   ; -> ComponentAffineLoad+0x28c
0x0002e52c: 01 01 00 90              adrp     x1, #0x4e000
0x0002e530: 21 14 32 91              add      x1, x1, #0xc85
0x0002e534: e0 03 18 aa              mov      x0, x24
0x0002e538: 02 7c ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x0002e53c: f4 03 00 aa              mov      x20, x0
0x0002e540: 50 7a ff 97              bl       #0xce80                    ; -> SoilJsonGetArraySize@plt
0x0002e544: f4 0f 00 f9              str      x20, [sp, #0x18]
0x0002e548: 88 0a 40 f9              ldr      x8, [x20, #0x10]
0x0002e54c: f9 03 00 2a              mov      w25, w0
0x0002e550: e0 03 08 aa              mov      x0, x8
0x0002e554: 4b 7a ff 97              bl       #0xce80                    ; -> SoilJsonGetArraySize@plt
0x0002e558: e8 0b 40 f9              ldr      x8, [sp, #0x10]
0x0002e55c: fa 03 00 2a              mov      w26, w0
0x0002e560: 08 01 40 f9              ldr      x8, [x8]
0x0002e564: 5f 03 08 6b              cmp      w26, w8
0x0002e568: 41 0b 00 54              b.ne     #0x2e6d0
0x0002e56c: 08 fd 60 d3              lsr      x8, x8, #0x20
0x0002e570: f5 03 00 f9              str      x21, [sp]
0x0002e574: 3f 03 08 6b              cmp      w25, w8
0x0002e578: c1 0b 00 54              b.ne     #0x2e6f0
0x0002e57c: 3f 07 00 71              cmp      w25, #1
0x0002e580: 2b 03 00 54              b.lt     #0x2e5e4
0x0002e584: f7 03 1f aa              mov      x23, xzr
0x0002e588: fb 03 1f aa              mov      x27, xzr
0x0002e58c: 55 7f 7e 93              sbfiz    x21, x26, #2, #0x20
0x0002e590: e0 0f 40 f9              ldr      x0, [sp, #0x18]
0x0002e594: e1 03 1b 2a              mov      w1, w27
0x0002e598: 26 7b ff 97              bl       #0xd230                    ; -> SoilJsonGetArrayItem@plt
0x0002e59c: fc 03 00 aa              mov      x28, x0
0x0002e5a0: 5f 07 00 71              cmp      w26, #1
0x0002e5a4: 8b 01 00 54              b.lt     #0x2e5d4
0x0002e5a8: f6 03 1f 2a              mov      w22, wzr
0x0002e5ac: f4 03 17 aa              mov      x20, x23
0x0002e5b0: e0 03 1c aa              mov      x0, x28
0x0002e5b4: e1 03 16 2a              mov      w1, w22
0x0002e5b8: 66 7c ff 97              bl       #0xd750                    ; -> SoilJsonGetArrayFloat@plt
0x0002e5bc: 68 3a 40 f9              ldr      x8, [x19, #0x70]
0x0002e5c0: d6 06 00 11              add      w22, w22, #1
0x0002e5c4: 5f 03 16 6b              cmp      w26, w22
0x0002e5c8: 00 69 34 bc              str      s0, [x8, x20]
0x0002e5cc: 94 12 00 91              add      x20, x20, #4
0x0002e5d0: 01 ff ff 54              b.ne     #0x2e5b0
0x0002e5d4: 7b 07 00 91              add      x27, x27, #1
0x0002e5d8: f7 02 15 8b              add      x23, x23, x21
0x0002e5dc: 7f 03 19 6b              cmp      w27, w25
0x0002e5e0: 81 fd ff 54              b.ne     #0x2e590
0x0002e5e4: 01 01 00 b0              adrp     x1, #0x4f000
0x0002e5e8: 21 50 12 91              add      x1, x1, #0x494
0x0002e5ec: e0 03 18 aa              mov      x0, x24
0x0002e5f0: d4 7b ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x0002e5f4: f5 03 40 f9              ldr      x21, [sp]
0x0002e5f8: f7 03 00 aa              mov      x23, x0
0x0002e5fc: 77 02 00 b4              cbz      x23, #0x2e648
0x0002e600: e0 03 17 aa              mov      x0, x23
0x0002e604: 1f 7a ff 97              bl       #0xce80                    ; -> SoilJsonGetArraySize@plt
0x0002e608: e8 07 40 f9              ldr      x8, [sp, #8]
0x0002e60c: 08 01 40 b9              ldr      w8, [x8]
0x0002e610: 1f 00 08 6b              cmp      w0, w8
0x0002e614: e1 07 00 54              b.ne     #0x2e710
0x0002e618: 1f 04 00 71              cmp      w0, #1
0x0002e61c: 6b 01 00 54              b.lt     #0x2e648
0x0002e620: f6 03 1f aa              mov      x22, xzr
0x0002e624: f4 03 00 2a              mov      w20, w0
0x0002e628: e0 03 17 aa              mov      x0, x23
0x0002e62c: e1 03 16 2a              mov      w1, w22
0x0002e630: 48 7c ff 97              bl       #0xd750                    ; -> SoilJsonGetArrayFloat@plt
0x0002e634: 68 3e 40 f9              ldr      x8, [x19, #0x78]
0x0002e638: 00 79 36 bc              str      s0, [x8, x22, lsl #2]
0x0002e63c: d6 06 00 91              add      x22, x22, #1
0x0002e640: 9f 02 16 6b              cmp      w20, w22
0x0002e644: 21 ff ff 54              b.ne     #0x2e628
0x0002e648: e0 03 15 aa              mov      x0, x21
0x0002e64c: 49 7d ff 97              bl       #0xdb70                    ; -> SoilMode@plt
0x0002e650: 1f 08 00 71              cmp      w0, #2
0x0002e654: e1 02 00 54              b.ne     #0x2e6b0
0x0002e658: e0 0b 40 f9              ldr      x0, [sp, #0x10]
0x0002e65c: e1 03 15 aa              mov      x1, x21
0x0002e660: 9c 7a ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x0002e664: e0 07 40 f9              ldr      x0, [sp, #8]
0x0002e668: e1 03 15 aa              mov      x1, x21
0x0002e66c: 99 7a ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x0002e670: 68 26 4d 29              ldp      w8, w9, [x19, #0x68]
0x0002e674: 60 3a 40 f9              ldr      x0, [x19, #0x70]
0x0002e678: e2 03 15 aa              mov      x2, x21
0x0002e67c: 21 7d 08 1b              mul      w1, w9, w8
0x0002e680: dc 79 ff 97              bl       #0xcdf0                    ; -> SoilWriteBinArrayFloat@plt
0x0002e684: 60 3e 40 f9              ldr      x0, [x19, #0x78]
0x0002e688: 61 6e 40 b9              ldr      w1, [x19, #0x6c]
0x0002e68c: e2 03 15 aa              mov      x2, x21
0x0002e690: fd 7b 47 a9              ldp      x29, x30, [sp, #0x70]
0x0002e694: f4 4f 46 a9              ldp      x20, x19, [sp, #0x60]
0x0002e698: f6 57 45 a9              ldp      x22, x21, [sp, #0x50]
0x0002e69c: f8 5f 44 a9              ldp      x24, x23, [sp, #0x40]
0x0002e6a0: fa 67 43 a9              ldp      x26, x25, [sp, #0x30]
0x0002e6a4: fc 6f 42 a9              ldp      x28, x27, [sp, #0x20]
0x0002e6a8: ff 03 02 91              add      sp, sp, #0x80
0x0002e6ac: d1 79 ff 17              b        #0xcdf0                    ; -> SoilWriteBinArrayFloat@plt
0x0002e6b0: fd 7b 47 a9              ldp      x29, x30, [sp, #0x70]
0x0002e6b4: f4 4f 46 a9              ldp      x20, x19, [sp, #0x60]
0x0002e6b8: f6 57 45 a9              ldp      x22, x21, [sp, #0x50]
0x0002e6bc: f8 5f 44 a9              ldp      x24, x23, [sp, #0x40]
0x0002e6c0: fa 67 43 a9              ldp      x26, x25, [sp, #0x30]
0x0002e6c4: fc 6f 42 a9              ldp      x28, x27, [sp, #0x20]
0x0002e6c8: ff 03 02 91              add      sp, sp, #0x80
0x0002e6cc: c0 03 5f d6              ret      
0x0002e6d0: 00 01 00 b0              adrp     x0, #0x4f000
0x0002e6d4: 02 01 00 b0              adrp     x2, #0x4f000
0x0002e6d8: 03 01 00 90              adrp     x3, #0x4e000
0x0002e6dc: 00 08 11 91              add      x0, x0, #0x442
0x0002e6e0: 42 00 12 91              add      x2, x2, #0x480
0x0002e6e4: 63 98 29 91              add      x3, x3, #0xa66
0x0002e6e8: 81 05 80 52              mov      w1, #0x2c
0x0002e6ec: b1 79 ff 97              bl       #0xcdb0                    ; -> __assert2@plt
0x0002e6f0: 00 01 00 b0              adrp     x0, #0x4f000
0x0002e6f4: 02 01 00 b0              adrp     x2, #0x4f000
0x0002e6f8: 03 01 00 b0              adrp     x3, #0x4f000
0x0002e6fc: 00 08 11 91              add      x0, x0, #0x442
0x0002e700: 42 00 12 91              add      x2, x2, #0x480
0x0002e704: 63 28 03 91              add      x3, x3, #0xca
0x0002e708: a1 05 80 52              mov      w1, #0x2d
0x0002e70c: a9 79 ff 97              bl       #0xcdb0                    ; -> __assert2@plt
0x0002e710: 00 01 00 b0              adrp     x0, #0x4f000
0x0002e714: 02 01 00 b0              adrp     x2, #0x4f000
0x0002e718: 03 01 00 b0              adrp     x3, #0x4f000
0x0002e71c: 00 08 11 91              add      x0, x0, #0x442
0x0002e720: 42 00 12 91              add      x2, x2, #0x480
0x0002e724: 63 a4 0b 91              add      x3, x3, #0x2e9
0x0002e728: e1 0f 1e 32              mov      w1, #0x3c
0x0002e72c: a1 79 ff 97              bl       #0xcdb0                    ; -> __assert2@plt
