; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : SoilLoadBinRaw (0x30630)
; start   : 0x30630
; size    : 0x100
; bytes   : 0x100   instructions: 64
; generated: 2026-10-23T23:30:00

0x00030630: f5 0f 1d f8              str      x21, [sp, #-0x30]!
0x00030634: f4 4f 01 a9              stp      x20, x19, [sp, #0x10]
0x00030638: fd 7b 02 a9              stp      x29, x30, [sp, #0x20]
0x0003063c: fd 83 00 91              add      x29, sp, #0x20
0x00030640: f4 03 02 aa              mov      x20, x2
0x00030644: 95 06 40 f9              ldr      x21, [x20, #8]
0x00030648: f3 03 00 aa              mov      x19, x0
0x0003064c: 3f 1c 00 71              cmp      w1, #7
0x00030650: a0 04 00 54              b.eq     #0x306e4
0x00030654: 3f 18 00 71              cmp      w1, #6
0x00030658: c1 04 00 54              b.ne     #0x306f0
0x0003065c: e1 00 00 90              adrp     x1, #0x4c000
0x00030660: e8 07 1f 32              mov      w8, #6
0x00030664: 21 84 3d 91              add      x1, x1, #0xf61
0x00030668: e0 03 13 aa              mov      x0, x19
0x0003066c: a8 0a 00 b9              str      w8, [x21, #8]
0x00030670: bf 0a 00 f9              str      xzr, [x21, #0x10]
0x00030674: 23 75 ff 97              bl       #0xdb00                    ; -> fopen@plt
0x00030678: a0 02 00 f9              str      x0, [x21]
0x0003067c: 40 04 00 b4              cbz      x0, #0x30704
0x00030680: e2 03 1f 32              mov      w2, #2
0x00030684: e1 03 1f aa              mov      x1, xzr
0x00030688: da 73 ff 97              bl       #0xd5f0                    ; -> fseek@plt
0x0003068c: a0 02 40 f9              ldr      x0, [x21]
0x00030690: 74 71 ff 97              bl       #0xcc60                    ; -> ftell@plt
0x00030694: a8 02 40 f9              ldr      x8, [x21]
0x00030698: a0 1a 00 b9              str      w0, [x21, #0x18]
0x0003069c: e1 03 1f aa              mov      x1, xzr
0x000306a0: e2 03 1f 2a              mov      w2, wzr
0x000306a4: e0 03 08 aa              mov      x0, x8
0x000306a8: d2 73 ff 97              bl       #0xd5f0                    ; -> fseek@plt
0x000306ac: a8 1a 80 b9              ldrsw    x8, [x21, #0x18]
0x000306b0: 80 02 40 f9              ldr      x0, [x20]
0x000306b4: 01 05 00 91              add      x1, x8, #1
0x000306b8: 8a 72 ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x000306bc: a2 1a 80 b9              ldrsw    x2, [x21, #0x18]
0x000306c0: a3 02 40 f9              ldr      x3, [x21]
0x000306c4: e1 03 00 32              mov      w1, #1
0x000306c8: a0 0a 00 f9              str      x0, [x21, #0x10]
0x000306cc: 99 71 ff 97              bl       #0xcd30                    ; -> fread@plt
0x000306d0: 60 02 00 34              cbz      w0, #0x3071c
0x000306d4: a8 1a 80 b9              ldrsw    x8, [x21, #0x18]
0x000306d8: a9 0a 40 f9              ldr      x9, [x21, #0x10]
0x000306dc: 3f 69 28 38              strb     wzr, [x9, x8]
0x000306e0: 04 00 00 14              b        #0x306f0                   ; -> SoilLoadBinRaw+0xc0
0x000306e4: e8 0b 00 32              mov      w8, #7
0x000306e8: a8 7e 01 29              stp      w8, wzr, [x21, #8]
0x000306ec: b3 0a 00 f9              str      x19, [x21, #0x10]
0x000306f0: e0 03 13 aa              mov      x0, x19
0x000306f4: fd 7b 42 a9              ldp      x29, x30, [sp, #0x20]
0x000306f8: f4 4f 41 a9              ldp      x20, x19, [sp, #0x10]
0x000306fc: f5 07 43 f8              ldr      x21, [sp], #0x30
0x00030700: c0 03 5f d6              ret      
0x00030704: e0 00 00 f0              adrp     x0, #0x4f000
0x00030708: 00 08 25 91              add      x0, x0, #0x942
0x0003070c: e1 03 13 aa              mov      x1, x19
0x00030710: 30 71 ff 97              bl       #0xcbd0                    ; -> printf@plt
0x00030714: 00 00 80 12              mov      w0, #-1
0x00030718: 7e 74 ff 97              bl       #0xd910                    ; -> exit@plt
0x0003071c: e0 00 00 f0              adrp     x0, #0x4f000
0x00030720: 00 c0 28 91              add      x0, x0, #0xa30
0x00030724: eb 73 ff 97              bl       #0xd6d0                    ; -> puts@plt
0x00030728: 00 00 80 12              mov      w0, #-1
0x0003072c: 79 74 ff 97              bl       #0xd910                    ; -> exit@plt
