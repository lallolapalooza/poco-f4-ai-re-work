; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : ComponentFixedscaleLoad (0x2d07c)
; start   : 0x2d07c
; size    : 0x1dc
; bytes   : 0x1dc   instructions: 119
; generated: 2026-10-23T23:48:37

0x0002d07c: fb 0f 1a f8              str      x27, [sp, #-0x60]!
0x0002d080: fa 67 01 a9              stp      x26, x25, [sp, #0x10]
0x0002d084: f8 5f 02 a9              stp      x24, x23, [sp, #0x20]
0x0002d088: f6 57 03 a9              stp      x22, x21, [sp, #0x30]
0x0002d08c: f4 4f 04 a9              stp      x20, x19, [sp, #0x40]
0x0002d090: fd 7b 05 a9              stp      x29, x30, [sp, #0x50]
0x0002d094: fd 43 01 91              add      x29, sp, #0x50
0x0002d098: f3 03 00 aa              mov      x19, x0
0x0002d09c: 74 1a 40 f9              ldr      x20, [x19, #0x30]
0x0002d0a0: f6 03 02 2a              mov      w22, w2
0x0002d0a4: f7 03 01 aa              mov      x23, x1
0x0002d0a8: e0 03 14 aa              mov      x0, x20
0x0002d0ac: b1 82 ff 97              bl       #0xdb70                    ; -> SoilMode@plt
0x0002d0b0: 1f 04 00 71              cmp      w0, #1
0x0002d0b4: 41 01 00 54              b.ne     #0x2d0dc
0x0002d0b8: e0 03 14 aa              mov      x0, x20
0x0002d0bc: 89 81 ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x0002d0c0: e8 03 00 2a              mov      w8, w0
0x0002d0c4: e0 03 14 aa              mov      x0, x20
0x0002d0c8: e1 03 08 2a              mov      w1, w8
0x0002d0cc: 68 6a 00 b9              str      w8, [x19, #0x68]
0x0002d0d0: a0 7f ff 97              bl       #0xcf50                    ; -> SoilLoadBinArrayFloat@plt
0x0002d0d4: 60 3a 00 f9              str      x0, [x19, #0x70]
0x0002d0d8: 51 00 00 14              b        #0x2d21c                   ; -> ComponentFixedscaleLoad+0x1a0
0x0002d0dc: 01 01 00 b0              adrp     x1, #0x4e000
0x0002d0e0: 21 08 1e 91              add      x1, x1, #0x782
0x0002d0e4: e0 03 17 aa              mov      x0, x23
0x0002d0e8: 16 81 ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x0002d0ec: e1 03 16 2a              mov      w1, w22
0x0002d0f0: 50 80 ff 97              bl       #0xd230                    ; -> SoilJsonGetArrayItem@plt
0x0002d0f4: 01 01 00 d0              adrp     x1, #0x4f000
0x0002d0f8: 21 dc 0b 91              add      x1, x1, #0x2f7
0x0002d0fc: 1d 7f ff 97              bl       #0xcd70                    ; -> SoilJsonGetInt@plt
0x0002d100: f5 03 13 aa              mov      x21, x19
0x0002d104: a0 8e 06 b8              str      w0, [x21, #0x68]!
0x0002d108: a8 82 5c f8              ldur     x8, [x21, #-0x38]
0x0002d10c: 01 7c 7e 93              sbfiz    x1, x0, #2, #0x20
0x0002d110: 08 01 40 f9              ldr      x8, [x8]
0x0002d114: e0 03 08 aa              mov      x0, x8
0x0002d118: f2 7f ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x0002d11c: 01 01 00 b0              adrp     x1, #0x4e000
0x0002d120: a0 06 00 f9              str      x0, [x21, #8]
0x0002d124: 21 fc 31 91              add      x1, x1, #0xc7f
0x0002d128: e0 03 17 aa              mov      x0, x23
0x0002d12c: 05 81 ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x0002d130: f7 03 00 aa              mov      x23, x0
0x0002d134: 53 7f ff 97              bl       #0xce80                    ; -> SoilJsonGetArraySize@plt
0x0002d138: f8 03 00 2a              mov      w24, w0
0x0002d13c: 1f 07 00 71              cmp      w24, #1
0x0002d140: cb 04 00 54              b.lt     #0x2d1d8
0x0002d144: ba 09 00 f0              adrp     x26, #0x164000
0x0002d148: f9 03 1f 2a              mov      w25, wzr
0x0002d14c: 5a f7 1d 91              add      x26, x26, #0x77d
0x0002d150: e0 03 17 aa              mov      x0, x23
0x0002d154: e1 03 19 2a              mov      w1, w25
0x0002d158: 36 80 ff 97              bl       #0xd230                    ; -> SoilJsonGetArrayItem@plt
0x0002d15c: e1 03 1a aa              mov      x1, x26
0x0002d160: fb 03 00 aa              mov      x27, x0
0x0002d164: f7 80 ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x0002d168: 08 28 40 b9              ldr      w8, [x0, #0x28]
0x0002d16c: 1f 01 16 6b              cmp      w8, w22
0x0002d170: a0 00 00 54              b.eq     #0x2d184
0x0002d174: 39 07 00 11              add      w25, w25, #1
0x0002d178: 3f 03 18 6b              cmp      w25, w24
0x0002d17c: ab fe ff 54              b.lt     #0x2d150
0x0002d180: 16 00 00 14              b        #0x2d1d8                   ; -> ComponentFixedscaleLoad+0x15c
0x0002d184: 01 01 00 b0              adrp     x1, #0x4e000
0x0002d188: 21 14 32 91              add      x1, x1, #0xc85
0x0002d18c: e0 03 1b aa              mov      x0, x27
0x0002d190: ec 80 ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x0002d194: f6 03 00 aa              mov      x22, x0
0x0002d198: 3a 7f ff 97              bl       #0xce80                    ; -> SoilJsonGetArraySize@plt
0x0002d19c: a8 02 40 b9              ldr      w8, [x21]
0x0002d1a0: 1f 00 08 6b              cmp      w0, w8
0x0002d1a4: a1 04 00 54              b.ne     #0x2d238
0x0002d1a8: 1f 04 00 71              cmp      w0, #1
0x0002d1ac: 6b 01 00 54              b.lt     #0x2d1d8
0x0002d1b0: f7 03 1f aa              mov      x23, xzr
0x0002d1b4: f8 03 00 2a              mov      w24, w0
0x0002d1b8: e0 03 16 aa              mov      x0, x22
0x0002d1bc: e1 03 17 2a              mov      w1, w23
0x0002d1c0: 64 81 ff 97              bl       #0xd750                    ; -> SoilJsonGetArrayFloat@plt
0x0002d1c4: 68 3a 40 f9              ldr      x8, [x19, #0x70]
0x0002d1c8: 00 79 37 bc              str      s0, [x8, x23, lsl #2]
0x0002d1cc: f7 06 00 91              add      x23, x23, #1
0x0002d1d0: 1f 03 17 6b              cmp      w24, w23
0x0002d1d4: 21 ff ff 54              b.ne     #0x2d1b8
0x0002d1d8: e0 03 14 aa              mov      x0, x20
0x0002d1dc: 65 82 ff 97              bl       #0xdb70                    ; -> SoilMode@plt
0x0002d1e0: 1f 08 00 71              cmp      w0, #2
0x0002d1e4: c1 01 00 54              b.ne     #0x2d21c
0x0002d1e8: e0 03 15 aa              mov      x0, x21
0x0002d1ec: e1 03 14 aa              mov      x1, x20
0x0002d1f0: b8 7f ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x0002d1f4: 60 3a 40 f9              ldr      x0, [x19, #0x70]
0x0002d1f8: 61 6a 40 b9              ldr      w1, [x19, #0x68]
0x0002d1fc: e2 03 14 aa              mov      x2, x20
0x0002d200: fd 7b 45 a9              ldp      x29, x30, [sp, #0x50]
0x0002d204: f4 4f 44 a9              ldp      x20, x19, [sp, #0x40]
0x0002d208: f6 57 43 a9              ldp      x22, x21, [sp, #0x30]
0x0002d20c: f8 5f 42 a9              ldp      x24, x23, [sp, #0x20]
0x0002d210: fa 67 41 a9              ldp      x26, x25, [sp, #0x10]
0x0002d214: fb 07 46 f8              ldr      x27, [sp], #0x60
0x0002d218: f6 7e ff 17              b        #0xcdf0                    ; -> SoilWriteBinArrayFloat@plt
0x0002d21c: fd 7b 45 a9              ldp      x29, x30, [sp, #0x50]
0x0002d220: f4 4f 44 a9              ldp      x20, x19, [sp, #0x40]
0x0002d224: f6 57 43 a9              ldp      x22, x21, [sp, #0x30]
0x0002d228: f8 5f 42 a9              ldp      x24, x23, [sp, #0x20]
0x0002d22c: fa 67 41 a9              ldp      x26, x25, [sp, #0x10]
0x0002d230: fb 07 46 f8              ldr      x27, [sp], #0x60
0x0002d234: c0 03 5f d6              ret      
0x0002d238: 00 01 00 d0              adrp     x0, #0x4f000
0x0002d23c: 02 01 00 d0              adrp     x2, #0x4f000
0x0002d240: 03 01 00 d0              adrp     x3, #0x4f000
0x0002d244: 00 3c 0a 91              add      x0, x0, #0x28f
0x0002d248: 42 44 0b 91              add      x2, x2, #0x2d1
0x0002d24c: 63 a4 0b 91              add      x3, x3, #0x2e9
0x0002d250: 41 04 80 52              mov      w1, #0x22
0x0002d254: d7 7e ff 97              bl       #0xcdb0                    ; -> __assert2@plt
