; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libqvasml.so
; target  : address 0x641c0
; start   : 0x641c0
; size    : 0xbc
; bytes   : 0xbc   instructions: 47
; generated: 2026-10-23T23:24:17

0x000641c0: 5f 24 03 d5              bti      c
0x000641c4: 09 04 40 f9              ldr      x9, [x0, #8]
0x000641c8: 2b 11 40 b9              ldr      w11, [x9, #0x10]
0x000641cc: 8b 01 00 34              cbz      w11, #0x641fc
0x000641d0: e8 03 1f aa              mov      x8, xzr
0x000641d4: 29 d9 40 f9              ldr      x9, [x9, #0x1b0]
0x000641d8: 2a 3c 00 12              and      w10, w1, #0xffff
0x000641dc: 6b ed 7c d3              lsl      x11, x11, #4
0x000641e0: 2c 01 08 8b              add      x12, x9, x8
0x000641e4: 8c 0d 40 b9              ldr      w12, [x12, #0xc]
0x000641e8: 9f 01 0a 6b              cmp      w12, w10
0x000641ec: 60 01 00 54              b.eq     #0x64218
0x000641f0: 08 41 00 91              add      x8, x8, #0x10
0x000641f4: 7f 01 08 eb              cmp      x11, x8
0x000641f8: 41 ff ff 54              b.ne     #0x641e0
0x000641fc: 1f 20 03 d5              nop      
0x00064200: e1 05 d7 70              adr      x1, #0x122bf
0x00064204: 82 fd ff 90              adrp     x2, #0x14000
0x00064208: 42 10 02 91              add      x2, x2, #0x84
0x0006420c: 40 00 80 52              mov      w0, #2
0x00064210: e3 25 80 52              mov      w3, #0x12f
0x00064214: ef b7 02 14              b        #0x1121d0                  ; -> _ZN3sml15sml_debug_printE18listen_status_enumPKcS2_i@plt
0x00064218: 29 69 68 78              ldrh     w9, [x9, x8]
0x0006421c: 49 20 00 79              strh     w9, [x2, #0x10]
0x00064220: 09 04 40 f9              ldr      x9, [x0, #8]
0x00064224: 29 d9 40 f9              ldr      x9, [x9, #0x1b0]
0x00064228: 29 01 08 8b              add      x9, x9, x8
0x0006422c: 29 05 40 79              ldrh     w9, [x9, #2]
0x00064230: 49 24 00 79              strh     w9, [x2, #0x12]
0x00064234: 09 04 40 f9              ldr      x9, [x0, #8]
0x00064238: 29 d9 40 f9              ldr      x9, [x9, #0x1b0]
0x0006423c: 29 01 08 8b              add      x9, x9, x8
0x00064240: 29 0d 40 b9              ldr      w9, [x9, #0xc]
0x00064244: 49 0c 00 b9              str      w9, [x2, #0xc]
0x00064248: 09 04 40 f9              ldr      x9, [x0, #8]
0x0006424c: 29 d9 40 f9              ldr      x9, [x9, #0x1b0]
0x00064250: 29 01 08 8b              add      x9, x9, x8
0x00064254: 29 09 40 b9              ldr      w9, [x9, #8]
0x00064258: 49 08 00 b9              str      w9, [x2, #8]
0x0006425c: 09 04 40 f9              ldr      x9, [x0, #8]
0x00064260: e0 03 1f 2a              mov      w0, wzr
0x00064264: 2a 25 5b a9              ldp      x10, x9, [x9, #0x1b0]
0x00064268: 48 01 08 8b              add      x8, x10, x8
0x0006426c: 08 05 40 b9              ldr      w8, [x8, #4]
0x00064270: 28 01 08 8b              add      x8, x9, x8
0x00064274: 48 00 00 f9              str      x8, [x2]
0x00064278: c0 03 5f d6              ret      
