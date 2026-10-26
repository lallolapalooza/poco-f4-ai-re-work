; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : ModelComponentType (0x2ff70)
; start   : 0x2ff70
; size    : 0x2b0
; bytes   : 0x2b0   instructions: 172
; generated: 2026-10-23T23:29:58

0x0002ff70: f3 0f 1e f8              str      x19, [sp, #-0x20]!
0x0002ff74: fd 7b 01 a9              stp      x29, x30, [sp, #0x10]
0x0002ff78: fd 43 00 91              add      x29, sp, #0x10
0x0002ff7c: f3 03 01 2a              mov      w19, w1
0x0002ff80: e1 00 00 f0              adrp     x1, #0x4e000
0x0002ff84: 21 08 1e 91              add      x1, x1, #0x782
0x0002ff88: 6e 75 ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x0002ff8c: e1 03 13 2a              mov      w1, w19
0x0002ff90: a8 74 ff 97              bl       #0xd230                    ; -> SoilJsonGetArrayItem@plt
0x0002ff94: a1 09 00 b0              adrp     x1, #0x164000
0x0002ff98: 21 30 1d 91              add      x1, x1, #0x74c
0x0002ff9c: 69 75 ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x0002ffa0: 13 10 40 f9              ldr      x19, [x0, #0x20]
0x0002ffa4: 01 01 00 90              adrp     x1, #0x4f000
0x0002ffa8: 21 7c 22 91              add      x1, x1, #0x89f
0x0002ffac: e0 03 13 aa              mov      x0, x19
0x0002ffb0: 94 75 ff 97              bl       #0xd600                    ; -> strcmp@plt
0x0002ffb4: e0 0d 00 34              cbz      w0, #0x30170
0x0002ffb8: 01 01 00 90              adrp     x1, #0x4f000
0x0002ffbc: 21 94 22 91              add      x1, x1, #0x8a5
0x0002ffc0: e0 03 13 aa              mov      x0, x19
0x0002ffc4: 8f 75 ff 97              bl       #0xd600                    ; -> strcmp@plt
0x0002ffc8: 80 0d 00 34              cbz      w0, #0x30178
0x0002ffcc: 01 01 00 90              adrp     x1, #0x4f000
0x0002ffd0: 21 b4 22 91              add      x1, x1, #0x8ad
0x0002ffd4: e0 03 13 aa              mov      x0, x19
0x0002ffd8: 8a 75 ff 97              bl       #0xd600                    ; -> strcmp@plt
0x0002ffdc: 20 0d 00 34              cbz      w0, #0x30180
0x0002ffe0: 01 01 00 90              adrp     x1, #0x4f000
0x0002ffe4: 21 d0 22 91              add      x1, x1, #0x8b4
0x0002ffe8: e0 03 13 aa              mov      x0, x19
0x0002ffec: 85 75 ff 97              bl       #0xd600                    ; -> strcmp@plt
0x0002fff0: c0 0c 00 34              cbz      w0, #0x30188
0x0002fff4: 01 01 00 90              adrp     x1, #0x4f000
0x0002fff8: 21 e0 22 91              add      x1, x1, #0x8b8
0x0002fffc: e0 03 13 aa              mov      x0, x19
0x00030000: 80 75 ff 97              bl       #0xd600                    ; -> strcmp@plt
0x00030004: 60 0c 00 34              cbz      w0, #0x30190
0x00030008: e1 00 00 f0              adrp     x1, #0x4f000
0x0003000c: 21 f4 22 91              add      x1, x1, #0x8bd
0x00030010: e0 03 13 aa              mov      x0, x19
0x00030014: 7b 75 ff 97              bl       #0xd600                    ; -> strcmp@plt
0x00030018: 00 0c 00 34              cbz      w0, #0x30198
0x0003001c: e1 00 00 f0              adrp     x1, #0x4f000
0x00030020: 21 24 23 91              add      x1, x1, #0x8c9
0x00030024: e0 03 13 aa              mov      x0, x19
0x00030028: 76 75 ff 97              bl       #0xd600                    ; -> strcmp@plt
0x0003002c: a0 0b 00 34              cbz      w0, #0x301a0
0x00030030: e1 00 00 f0              adrp     x1, #0x4f000
0x00030034: 21 54 23 91              add      x1, x1, #0x8d5
0x00030038: e0 03 13 aa              mov      x0, x19
0x0003003c: 71 75 ff 97              bl       #0xd600                    ; -> strcmp@plt
0x00030040: 40 0b 00 34              cbz      w0, #0x301a8
0x00030044: e1 00 00 f0              adrp     x1, #0x4f000
0x00030048: 21 70 23 91              add      x1, x1, #0x8dc
0x0003004c: e0 03 13 aa              mov      x0, x19
0x00030050: 6c 75 ff 97              bl       #0xd600                    ; -> strcmp@plt
0x00030054: e0 0a 00 34              cbz      w0, #0x301b0
0x00030058: e1 00 00 f0              adrp     x1, #0x4f000
0x0003005c: 21 84 23 91              add      x1, x1, #0x8e1
0x00030060: e0 03 13 aa              mov      x0, x19
0x00030064: 67 75 ff 97              bl       #0xd600                    ; -> strcmp@plt
0x00030068: 80 0a 00 34              cbz      w0, #0x301b8
0x0003006c: e1 00 00 f0              adrp     x1, #0x4f000
0x00030070: 21 98 23 91              add      x1, x1, #0x8e6
0x00030074: e0 03 13 aa              mov      x0, x19
0x00030078: 62 75 ff 97              bl       #0xd600                    ; -> strcmp@plt
0x0003007c: 20 0a 00 34              cbz      w0, #0x301c0
0x00030080: e1 00 00 f0              adrp     x1, #0x4f000
0x00030084: 21 a8 23 91              add      x1, x1, #0x8ea
0x00030088: e0 03 13 aa              mov      x0, x19
0x0003008c: 5d 75 ff 97              bl       #0xd600                    ; -> strcmp@plt
0x00030090: c0 09 00 34              cbz      w0, #0x301c8
0x00030094: e1 00 00 f0              adrp     x1, #0x4f000
0x00030098: 21 d0 23 91              add      x1, x1, #0x8f4
0x0003009c: e0 03 13 aa              mov      x0, x19
0x000300a0: 58 75 ff 97              bl       #0xd600                    ; -> strcmp@plt
0x000300a4: 60 09 00 34              cbz      w0, #0x301d0
0x000300a8: e1 00 00 f0              adrp     x1, #0x4f000
0x000300ac: 21 04 24 91              add      x1, x1, #0x901
0x000300b0: e0 03 13 aa              mov      x0, x19
0x000300b4: 53 75 ff 97              bl       #0xd600                    ; -> strcmp@plt
0x000300b8: 00 09 00 34              cbz      w0, #0x301d8
0x000300bc: e1 00 00 f0              adrp     x1, #0x4f000
0x000300c0: 21 24 24 91              add      x1, x1, #0x909
0x000300c4: e0 03 13 aa              mov      x0, x19
0x000300c8: 4e 75 ff 97              bl       #0xd600                    ; -> strcmp@plt
0x000300cc: a0 08 00 34              cbz      w0, #0x301e0
0x000300d0: e1 00 00 f0              adrp     x1, #0x4f000
0x000300d4: 21 d8 23 91              add      x1, x1, #0x8f6
0x000300d8: e0 03 13 aa              mov      x0, x19
0x000300dc: 49 75 ff 97              bl       #0xd600                    ; -> strcmp@plt
0x000300e0: 40 08 00 34              cbz      w0, #0x301e8
0x000300e4: e1 00 00 f0              adrp     x1, #0x4f000
0x000300e8: 21 3c 24 91              add      x1, x1, #0x90f
0x000300ec: e0 03 13 aa              mov      x0, x19
0x000300f0: 44 75 ff 97              bl       #0xd600                    ; -> strcmp@plt
0x000300f4: e0 07 00 34              cbz      w0, #0x301f0
0x000300f8: e1 00 00 f0              adrp     x1, #0x4f000
0x000300fc: 21 64 24 91              add      x1, x1, #0x919
0x00030100: e0 03 13 aa              mov      x0, x19
0x00030104: 3f 75 ff 97              bl       #0xd600                    ; -> strcmp@plt
0x00030108: 80 07 00 34              cbz      w0, #0x301f8
0x0003010c: e1 00 00 d0              adrp     x1, #0x4e000
0x00030110: 21 54 2c 91              add      x1, x1, #0xb15
0x00030114: e0 03 13 aa              mov      x0, x19
0x00030118: 3a 75 ff 97              bl       #0xd600                    ; -> strcmp@plt
0x0003011c: 20 07 00 34              cbz      w0, #0x30200
0x00030120: e1 00 00 f0              adrp     x1, #0x4f000
0x00030124: 21 9c 24 91              add      x1, x1, #0x927
0x00030128: e0 03 13 aa              mov      x0, x19
0x0003012c: 35 75 ff 97              bl       #0xd600                    ; -> strcmp@plt
0x00030130: c0 06 00 34              cbz      w0, #0x30208
0x00030134: e1 00 00 f0              adrp     x1, #0x4f000
0x00030138: 21 c4 24 91              add      x1, x1, #0x931
0x0003013c: e0 03 13 aa              mov      x0, x19
0x00030140: 30 75 ff 97              bl       #0xd600                    ; -> strcmp@plt
0x00030144: 60 06 00 34              cbz      w0, #0x30210
0x00030148: e1 00 00 f0              adrp     x1, #0x4f000
0x0003014c: 21 e4 24 91              add      x1, x1, #0x939
0x00030150: e0 03 13 aa              mov      x0, x19
0x00030154: 2b 75 ff 97              bl       #0xd600                    ; -> strcmp@plt
0x00030158: e0 05 00 34              cbz      w0, #0x30214
0x0003015c: e0 00 00 f0              adrp     x0, #0x4f000
0x00030160: 00 40 28 91              add      x0, x0, #0xa10
0x00030164: 5b 75 ff 97              bl       #0xd6d0                    ; -> puts@plt
0x00030168: c0 02 80 52              mov      w0, #0x16
0x0003016c: 2a 00 00 14              b        #0x30214                   ; -> ModelComponentType+0x2a4
0x00030170: e0 03 00 32              mov      w0, #1
0x00030174: 28 00 00 14              b        #0x30214                   ; -> ModelComponentType+0x2a4
0x00030178: e0 0b 00 32              mov      w0, #7
0x0003017c: 26 00 00 14              b        #0x30214                   ; -> ModelComponentType+0x2a4
0x00030180: e0 07 1f 32              mov      w0, #6
0x00030184: 24 00 00 14              b        #0x30214                   ; -> ModelComponentType+0x2a4
0x00030188: e0 03 1f 32              mov      w0, #2
0x0003018c: 22 00 00 14              b        #0x30214                   ; -> ModelComponentType+0x2a4
0x00030190: e0 07 00 32              mov      w0, #3
0x00030194: 20 00 00 14              b        #0x30214                   ; -> ModelComponentType+0x2a4
0x00030198: e0 03 1e 32              mov      w0, #4
0x0003019c: 1e 00 00 14              b        #0x30214                   ; -> ModelComponentType+0x2a4
0x000301a0: a0 00 80 52              mov      w0, #5
0x000301a4: 1c 00 00 14              b        #0x30214                   ; -> ModelComponentType+0x2a4
0x000301a8: e0 03 1d 32              mov      w0, #8
0x000301ac: 1a 00 00 14              b        #0x30214                   ; -> ModelComponentType+0x2a4
0x000301b0: 20 01 80 52              mov      w0, #9
0x000301b4: 18 00 00 14              b        #0x30214                   ; -> ModelComponentType+0x2a4
0x000301b8: 40 01 80 52              mov      w0, #0xa
0x000301bc: 16 00 00 14              b        #0x30214                   ; -> ModelComponentType+0x2a4
0x000301c0: 60 01 80 52              mov      w0, #0xb
0x000301c4: 14 00 00 14              b        #0x30214                   ; -> ModelComponentType+0x2a4
0x000301c8: e0 07 1e 32              mov      w0, #0xc
0x000301cc: 12 00 00 14              b        #0x30214                   ; -> ModelComponentType+0x2a4
0x000301d0: a0 01 80 52              mov      w0, #0xd
0x000301d4: 10 00 00 14              b        #0x30214                   ; -> ModelComponentType+0x2a4
0x000301d8: e0 0b 1f 32              mov      w0, #0xe
0x000301dc: 0e 00 00 14              b        #0x30214                   ; -> ModelComponentType+0x2a4
0x000301e0: e0 0f 00 32              mov      w0, #0xf
0x000301e4: 0c 00 00 14              b        #0x30214                   ; -> ModelComponentType+0x2a4
0x000301e8: e0 03 1c 32              mov      w0, #0x10
0x000301ec: 0a 00 00 14              b        #0x30214                   ; -> ModelComponentType+0x2a4
0x000301f0: 20 02 80 52              mov      w0, #0x11
0x000301f4: 08 00 00 14              b        #0x30214                   ; -> ModelComponentType+0x2a4
0x000301f8: 40 02 80 52              mov      w0, #0x12
0x000301fc: 06 00 00 14              b        #0x30214                   ; -> ModelComponentType+0x2a4
0x00030200: 60 02 80 52              mov      w0, #0x13
0x00030204: 04 00 00 14              b        #0x30214                   ; -> ModelComponentType+0x2a4
0x00030208: 80 02 80 52              mov      w0, #0x14
0x0003020c: 02 00 00 14              b        #0x30214                   ; -> ModelComponentType+0x2a4
0x00030210: a0 02 80 52              mov      w0, #0x15
0x00030214: fd 7b 41 a9              ldp      x29, x30, [sp, #0x10]
0x00030218: f3 07 42 f8              ldr      x19, [sp], #0x20
0x0003021c: c0 03 5f d6              ret      
