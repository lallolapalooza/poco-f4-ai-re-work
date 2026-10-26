; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_musically/native/libbytenn.so
; target  : address 0x35898
; start   : 0x35898
; size    : 0x200
; bytes   : 0x200   instructions: 128
; generated: 2026-10-23T23:34:49

0x00035898: f5 0f 1d f8              str      x21, [sp, #-0x30]!
0x0003589c: f4 4f 01 a9              stp      x20, x19, [sp, #0x10]
0x000358a0: fd 7b 02 a9              stp      x29, x30, [sp, #0x20]
0x000358a4: fd 83 00 91              add      x29, sp, #0x20
0x000358a8: 08 00 40 39              ldrb     w8, [x0]
0x000358ac: f4 03 02 aa              mov      x20, x2
0x000358b0: f3 03 00 aa              mov      x19, x0
0x000358b4: e7 03 01 aa              mov      x7, x1
0x000358b8: 08 01 00 37              tbnz     w8, #0, #0x358d8
0x000358bc: c1 02 80 52              mov      w1, #0x16
0x000358c0: 3f 00 14 eb              cmp      x1, x20
0x000358c4: 43 01 00 54              b.lo     #0x358ec
0x000358c8: 88 01 00 37              tbnz     w8, #0, #0x358f8
0x000358cc: 75 06 00 91              add      x21, x19, #1
0x000358d0: 94 01 00 b5              cbnz     x20, #0x35900
0x000358d4: 0f 00 00 14              b        #0x35910
0x000358d8: 69 02 40 f9              ldr      x9, [x19]
0x000358dc: 29 f9 7f 92              and      x9, x9, #0xfffffffffffffffe
0x000358e0: 21 05 00 d1              sub      x1, x9, #1
0x000358e4: 3f 00 14 eb              cmp      x1, x20
0x000358e8: 02 ff ff 54              b.hs     #0x358c8
0x000358ec: e8 01 00 37              tbnz     w8, #0, #0x35928
0x000358f0: 03 fd 41 d3              lsr      x3, x8, #1
0x000358f4: 0e 00 00 14              b        #0x3592c
0x000358f8: 75 0a 40 f9              ldr      x21, [x19, #0x10]
0x000358fc: b4 00 00 b4              cbz      x20, #0x35910
0x00035900: e0 03 15 aa              mov      x0, x21
0x00035904: e1 03 07 aa              mov      x1, x7
0x00035908: e2 03 14 aa              mov      x2, x20
0x0003590c: 71 e9 ff 97              bl       #0x2fed0                   ; -> memmove@plt
0x00035910: bf 6a 34 38              strb     wzr, [x21, x20]
0x00035914: 68 02 40 39              ldrb     w8, [x19]
0x00035918: 88 01 00 37              tbnz     w8, #0, #0x35948
0x0003591c: 88 7a 1f 53              lsl      w8, w20, #1
0x00035920: 68 02 00 39              strb     w8, [x19]
0x00035924: 0a 00 00 14              b        #0x3594c
0x00035928: 63 06 40 f9              ldr      x3, [x19, #8]
0x0003592c: 82 02 01 cb              sub      x2, x20, x1
0x00035930: e0 03 13 aa              mov      x0, x19
0x00035934: e4 03 1f aa              mov      x4, xzr
0x00035938: e5 03 03 aa              mov      x5, x3
0x0003593c: e6 03 14 aa              mov      x6, x20
0x00035940: 08 00 00 94              bl       #0x35960
0x00035944: 02 00 00 14              b        #0x3594c
0x00035948: 74 06 00 f9              str      x20, [x19, #8]
0x0003594c: e0 03 13 aa              mov      x0, x19
0x00035950: fd 7b 42 a9              ldp      x29, x30, [sp, #0x20]
0x00035954: f4 4f 41 a9              ldp      x20, x19, [sp, #0x10]
0x00035958: f5 07 43 f8              ldr      x21, [sp], #0x30
0x0003595c: c0 03 5f d6              ret      
0x00035960: fc 6f ba a9              stp      x28, x27, [sp, #-0x60]!
0x00035964: fa 67 01 a9              stp      x26, x25, [sp, #0x10]
0x00035968: f8 5f 02 a9              stp      x24, x23, [sp, #0x20]
0x0003596c: f6 57 03 a9              stp      x22, x21, [sp, #0x30]
0x00035970: f4 4f 04 a9              stp      x20, x19, [sp, #0x40]
0x00035974: fd 7b 05 a9              stp      x29, x30, [sp, #0x50]
0x00035978: fd 43 01 91              add      x29, sp, #0x50
0x0003597c: 28 02 80 92              mov      x8, #-0x12
0x00035980: 08 01 01 cb              sub      x8, x8, x1
0x00035984: 1f 01 02 eb              cmp      x8, x2
0x00035988: 43 08 00 54              b.lo     #0x35a90
0x0003598c: 08 00 40 39              ldrb     w8, [x0]
0x00035990: fa 03 07 aa              mov      x26, x7
0x00035994: f3 03 06 aa              mov      x19, x6
0x00035998: f6 03 05 aa              mov      x22, x5
0x0003599c: f7 03 04 aa              mov      x23, x4
0x000359a0: f9 03 03 aa              mov      x25, x3
0x000359a4: f5 03 01 aa              mov      x21, x1
0x000359a8: f4 03 00 aa              mov      x20, x0
0x000359ac: 68 00 00 37              tbnz     w8, #0, #0x359b8
0x000359b0: 98 06 00 91              add      x24, x20, #1
0x000359b4: 02 00 00 14              b        #0x359bc
0x000359b8: 98 0a 40 f9              ldr      x24, [x20, #0x10]
0x000359bc: 28 03 80 92              mov      x8, #-0x1a
0x000359c0: e8 ff ef f2              movk     x8, #0x7fff, lsl #48
0x000359c4: bf 02 08 eb              cmp      x21, x8
0x000359c8: 28 01 00 54              b.hi     #0x359ec
0x000359cc: 48 00 15 8b              add      x8, x2, x21
0x000359d0: a9 fa 7f d3              lsl      x9, x21, #1
0x000359d4: 1f 01 09 eb              cmp      x8, x9
0x000359d8: 28 31 88 9a              csel     x8, x9, x8, lo
0x000359dc: 1f 5d 00 f1              cmp      x8, #0x17
0x000359e0: a2 00 00 54              b.hs     #0x359f4
0x000359e4: fb 02 80 52              mov      w27, #0x17
0x000359e8: 05 00 00 14              b        #0x359fc
0x000359ec: 1b 02 80 92              mov      x27, #-0x11
0x000359f0: 03 00 00 14              b        #0x359fc
0x000359f4: 08 41 00 91              add      x8, x8, #0x10
0x000359f8: 1b ed 7c 92              and      x27, x8, #0xfffffffffffffff0
0x000359fc: e0 03 1b aa              mov      x0, x27
0x00035a00: 94 ed ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00035a04: fc 03 00 aa              mov      x28, x0
0x00035a08: b7 00 00 b4              cbz      x23, #0x35a1c
0x00035a0c: e0 03 1c aa              mov      x0, x28
0x00035a10: e1 03 18 aa              mov      x1, x24
0x00035a14: e2 03 17 aa              mov      x2, x23
0x00035a18: 22 e7 ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00035a1c: b3 00 00 b4              cbz      x19, #0x35a30
0x00035a20: 80 03 17 8b              add      x0, x28, x23
0x00035a24: e1 03 1a aa              mov      x1, x26
0x00035a28: e2 03 13 aa              mov      x2, x19
0x00035a2c: 1d e7 ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00035a30: 39 03 16 cb              sub      x25, x25, x22
0x00035a34: 22 03 17 eb              subs     x2, x25, x23
0x00035a38: c0 00 00 54              b.eq     #0x35a50
0x00035a3c: 88 03 17 8b              add      x8, x28, x23
0x00035a40: 09 03 17 8b              add      x9, x24, x23
0x00035a44: 00 01 13 8b              add      x0, x8, x19
0x00035a48: 21 01 16 8b              add      x1, x9, x22
0x00035a4c: 15 e7 ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00035a50: bf 5a 00 f1              cmp      x21, #0x16
0x00035a54: 60 00 00 54              b.eq     #0x35a60
0x00035a58: e0 03 18 aa              mov      x0, x24
0x00035a5c: 91 e7 ff 97              bl       #0x2f8a0                   ; -> _ZdlPv@plt
0x00035a60: 68 03 40 b2              orr      x8, x27, #1
0x00035a64: 29 03 13 8b              add      x9, x25, x19
0x00035a68: 88 02 00 f9              str      x8, [x20]
0x00035a6c: 89 f2 00 a9              stp      x9, x28, [x20, #8]
0x00035a70: fd 7b 45 a9              ldp      x29, x30, [sp, #0x50]
0x00035a74: f4 4f 44 a9              ldp      x20, x19, [sp, #0x40]
0x00035a78: f6 57 43 a9              ldp      x22, x21, [sp, #0x30]
0x00035a7c: f8 5f 42 a9              ldp      x24, x23, [sp, #0x20]
0x00035a80: fa 67 41 a9              ldp      x26, x25, [sp, #0x10]
0x00035a84: 9f 6b 29 38              strb     wzr, [x28, x9]
0x00035a88: fc 6f c6 a8              ldp      x28, x27, [sp], #0x60
0x00035a8c: c0 03 5f d6              ret      
0x00035a90: 68 ec ff 97              bl       #0x30c30                   ; -> abort@plt
0x00035a94: f6 57 bd a9              stp      x22, x21, [sp, #-0x30]!
