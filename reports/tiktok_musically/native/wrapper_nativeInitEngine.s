; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_musically/native/libbytennwrapper.so
; target  : Java_com_ss_bytenn_API_nativeInitEngine (0x167c)
; start   : 0x167c
; size    : 0x704
; bytes   : 0x704   instructions: 449
; generated: 2026-10-23T23:35:20

0x0000167c: ff c3 06 d1              sub      sp, sp, #0x1b0
0x00001680: fc 6f 15 a9              stp      x28, x27, [sp, #0x150]
0x00001684: fa 67 16 a9              stp      x26, x25, [sp, #0x160]
0x00001688: f8 5f 17 a9              stp      x24, x23, [sp, #0x170]
0x0000168c: f6 57 18 a9              stp      x22, x21, [sp, #0x180]
0x00001690: f4 4f 19 a9              stp      x20, x19, [sp, #0x190]
0x00001694: fd 7b 1a a9              stp      x29, x30, [sp, #0x1a0]
0x00001698: fd 83 06 91              add      x29, sp, #0x1a0
0x0000169c: 5c d0 3b d5              mrs      x28, tpidr_el0
0x000016a0: 88 17 40 f9              ldr      x8, [x28, #0x28]
0x000016a4: a8 03 1a f8              stur     x8, [x29, #-0x60]
0x000016a8: 22 27 00 b4              cbz      x2, #0x1b8c
0x000016ac: 08 00 40 f9              ldr      x8, [x0]
0x000016b0: e1 03 03 aa              mov      x1, x3
0x000016b4: fa 03 03 aa              mov      x26, x3
0x000016b8: f3 03 00 aa              mov      x19, x0
0x000016bc: 08 7d 40 f9              ldr      x8, [x8, #0xf8]
0x000016c0: e2 17 00 f9              str      x2, [sp, #0x28]
0x000016c4: 00 01 3f d6              blr      x8
0x000016c8: 68 02 40 f9              ldr      x8, [x19]
0x000016cc: f4 03 00 aa              mov      x20, x0
0x000016d0: 22 00 00 b0              adrp     x2, #0x6000
0x000016d4: 23 00 00 b0              adrp     x3, #0x6000
0x000016d8: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000016dc: 42 c0 3b 91              add      x2, x2, #0xef0
0x000016e0: 63 20 3c 91              add      x3, x3, #0xf08
0x000016e4: e0 03 13 aa              mov      x0, x19
0x000016e8: e1 03 14 aa              mov      x1, x20
0x000016ec: 00 01 3f d6              blr      x8
0x000016f0: 68 02 40 f9              ldr      x8, [x19]
0x000016f4: 35 00 00 b0              adrp     x21, #0x6000
0x000016f8: 22 00 00 b0              adrp     x2, #0x6000
0x000016fc: b5 3e 3d 91              add      x21, x21, #0xf4f
0x00001700: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x00001704: fb 03 00 aa              mov      x27, x0
0x00001708: 42 30 3c 91              add      x2, x2, #0xf0c
0x0000170c: e0 03 13 aa              mov      x0, x19
0x00001710: e1 03 14 aa              mov      x1, x20
0x00001714: e3 03 15 aa              mov      x3, x21
0x00001718: 00 01 3f d6              blr      x8
0x0000171c: 68 02 40 f9              ldr      x8, [x19]
0x00001720: 22 00 00 b0              adrp     x2, #0x6000
0x00001724: f7 03 00 aa              mov      x23, x0
0x00001728: 42 74 3c 91              add      x2, x2, #0xf1d
0x0000172c: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x00001730: e0 03 13 aa              mov      x0, x19
0x00001734: e1 03 14 aa              mov      x1, x20
0x00001738: e3 03 15 aa              mov      x3, x21
0x0000173c: 00 01 3f d6              blr      x8
0x00001740: 68 02 40 f9              ldr      x8, [x19]
0x00001744: 22 00 00 b0              adrp     x2, #0x6000
0x00001748: 23 00 00 b0              adrp     x3, #0x6000
0x0000174c: f9 03 00 aa              mov      x25, x0
0x00001750: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x00001754: 42 9c 3c 91              add      x2, x2, #0xf27
0x00001758: 63 b4 3c 91              add      x3, x3, #0xf2d
0x0000175c: e0 03 13 aa              mov      x0, x19
0x00001760: e1 03 14 aa              mov      x1, x20
0x00001764: 00 01 3f d6              blr      x8
0x00001768: 68 02 40 f9              ldr      x8, [x19]
0x0000176c: 22 00 00 b0              adrp     x2, #0x6000
0x00001770: 23 00 00 b0              adrp     x3, #0x6000
0x00001774: f5 03 00 aa              mov      x21, x0
0x00001778: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x0000177c: 42 0c 3d 91              add      x2, x2, #0xf43
0x00001780: 63 38 3d 91              add      x3, x3, #0xf4e
0x00001784: e0 03 13 aa              mov      x0, x19
0x00001788: e1 03 14 aa              mov      x1, x20
0x0000178c: 00 01 3f d6              blr      x8
0x00001790: 68 02 40 f9              ldr      x8, [x19]
0x00001794: 22 00 00 b0              adrp     x2, #0x6000
0x00001798: 23 00 00 b0              adrp     x3, #0x6000
0x0000179c: f6 03 00 aa              mov      x22, x0
0x000017a0: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000017a4: 42 88 3d 91              add      x2, x2, #0xf62
0x000017a8: 63 28 3c 91              add      x3, x3, #0xf0a
0x000017ac: e0 03 13 aa              mov      x0, x19
0x000017b0: e1 03 14 aa              mov      x1, x20
0x000017b4: 00 01 3f d6              blr      x8
0x000017b8: f8 03 00 aa              mov      x24, x0
0x000017bc: e0 63 01 91              add      x0, sp, #0x58
0x000017c0: 70 01 00 94              bl       #0x1d80
0x000017c4: e0 03 13 aa              mov      x0, x19
0x000017c8: e1 03 1a aa              mov      x1, x26
0x000017cc: e2 03 1b aa              mov      x2, x27
0x000017d0: a3 01 00 94              bl       #0x1e5c
0x000017d4: e0 5b 00 b9              str      w0, [sp, #0x58]
0x000017d8: 68 02 40 f9              ldr      x8, [x19]
0x000017dc: f6 1b 00 f9              str      x22, [sp, #0x30]
0x000017e0: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000017e4: e0 03 13 aa              mov      x0, x19
0x000017e8: e1 03 1a aa              mov      x1, x26
0x000017ec: e2 03 17 aa              mov      x2, x23
0x000017f0: 00 01 3f d6              blr      x8
0x000017f4: 68 02 40 f9              ldr      x8, [x19]
0x000017f8: f7 03 00 aa              mov      x23, x0
0x000017fc: 08 a5 42 f9              ldr      x8, [x8, #0x548]
0x00001800: e0 03 13 aa              mov      x0, x19
0x00001804: e1 03 17 aa              mov      x1, x23
0x00001808: e2 03 1f aa              mov      x2, xzr
0x0000180c: 00 01 3f d6              blr      x8
0x00001810: e8 63 01 91              add      x8, sp, #0x58
0x00001814: f6 03 00 aa              mov      x22, x0
0x00001818: 1b 81 00 91              add      x27, x8, #0x20
0x0000181c: d9 fe ff 97              bl       #0x1380                    ; -> strlen@plt
0x00001820: e2 03 00 aa              mov      x2, x0
0x00001824: e0 03 1b aa              mov      x0, x27
0x00001828: e1 03 16 aa              mov      x1, x22
0x0000182c: 21 06 00 94              bl       #0x30b0
0x00001830: 68 02 40 f9              ldr      x8, [x19]
0x00001834: f6 1f 00 f9              str      x22, [sp, #0x38]
0x00001838: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x0000183c: e0 03 13 aa              mov      x0, x19
0x00001840: e1 03 1a aa              mov      x1, x26
0x00001844: e2 03 19 aa              mov      x2, x25
0x00001848: 00 01 3f d6              blr      x8
0x0000184c: 68 02 40 f9              ldr      x8, [x19]
0x00001850: f6 03 00 aa              mov      x22, x0
0x00001854: 08 a5 42 f9              ldr      x8, [x8, #0x548]
0x00001858: e0 03 13 aa              mov      x0, x19
0x0000185c: e1 03 16 aa              mov      x1, x22
0x00001860: e2 03 1f aa              mov      x2, xzr
0x00001864: 00 01 3f d6              blr      x8
0x00001868: e8 63 01 91              add      x8, sp, #0x58
0x0000186c: f9 03 00 aa              mov      x25, x0
0x00001870: 1b 01 02 91              add      x27, x8, #0x80
0x00001874: c3 fe ff 97              bl       #0x1380                    ; -> strlen@plt
0x00001878: e2 03 00 aa              mov      x2, x0
0x0000187c: e0 03 1b aa              mov      x0, x27
0x00001880: e1 03 19 aa              mov      x1, x25
0x00001884: 0b 06 00 94              bl       #0x30b0
0x00001888: 68 02 40 f9              ldr      x8, [x19]
0x0000188c: 08 91 41 f9              ldr      x8, [x8, #0x320]
0x00001890: e0 03 13 aa              mov      x0, x19
0x00001894: e1 03 1a aa              mov      x1, x26
0x00001898: e2 03 18 aa              mov      x2, x24
0x0000189c: 00 01 3f d6              blr      x8
0x000018a0: 08 04 00 51              sub      w8, w0, #1
0x000018a4: 1f 09 00 71              cmp      w8, #2
0x000018a8: 08 34 9f 1a              csinc    w8, w0, wzr, lo
0x000018ac: e8 73 00 b9              str      w8, [sp, #0x70]
0x000018b0: 68 02 40 f9              ldr      x8, [x19]
0x000018b4: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000018b8: e0 03 13 aa              mov      x0, x19
0x000018bc: e1 03 1a aa              mov      x1, x26
0x000018c0: e2 03 15 aa              mov      x2, x21
0x000018c4: 00 01 3f d6              blr      x8
0x000018c8: 68 02 40 f9              ldr      x8, [x19]
0x000018cc: f5 03 00 aa              mov      x21, x0
0x000018d0: 08 99 43 f9              ldr      x8, [x8, #0x730]
0x000018d4: e0 03 13 aa              mov      x0, x19
0x000018d8: e1 03 15 aa              mov      x1, x21
0x000018dc: 00 01 3f d6              blr      x8
0x000018e0: e0 33 00 f9              str      x0, [sp, #0x60]
0x000018e4: 80 15 00 b4              cbz      x0, #0x1b94
0x000018e8: 68 02 40 f9              ldr      x8, [x19]
0x000018ec: 08 9d 43 f9              ldr      x8, [x8, #0x738]
0x000018f0: e0 03 13 aa              mov      x0, x19
0x000018f4: e1 03 15 aa              mov      x1, x21
0x000018f8: 00 01 3f d6              blr      x8
0x000018fc: f9 db 00 a9              stp      x25, x22, [sp, #8]
0x00001900: f7 f3 01 a9              stp      x23, x28, [sp, #0x18]
0x00001904: e0 6b 00 b9              str      w0, [sp, #0x68]
0x00001908: 68 02 40 f9              ldr      x8, [x19]
0x0000190c: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x00001910: e2 1b 40 f9              ldr      x2, [sp, #0x30]
0x00001914: e0 03 13 aa              mov      x0, x19
0x00001918: e1 03 1a aa              mov      x1, x26
0x0000191c: 00 01 3f d6              blr      x8
0x00001920: fa 03 00 aa              mov      x26, x0
0x00001924: 00 10 00 b4              cbz      x0, #0x1b24
0x00001928: 68 02 40 f9              ldr      x8, [x19]
0x0000192c: 08 ad 42 f9              ldr      x8, [x8, #0x558]
0x00001930: e0 03 13 aa              mov      x0, x19
0x00001934: e1 03 1a aa              mov      x1, x26
0x00001938: 00 01 3f d6              blr      x8
0x0000193c: fb 03 00 2a              mov      w27, w0
0x00001940: 1f 04 00 71              cmp      w0, #1
0x00001944: 0b 0f 00 54              b.lt     #0x1b24
0x00001948: f7 f3 00 b2              mov      x23, #0x5555555555555555
0x0000194c: e8 63 01 91              add      x8, sp, #0x58
0x00001950: f9 f3 01 b2              mov      x25, #-0x5555555555555556
0x00001954: 97 aa 8a f2              movk     x23, #0x5554
0x00001958: fc 03 1f 2a              mov      w28, wzr
0x0000195c: 79 55 95 f2              movk     x25, #0xaaab
0x00001960: 09 41 01 91              add      x9, x8, #0x50
0x00001964: 16 81 01 91              add      x22, x8, #0x60
0x00001968: b7 aa e0 f2              movk     x23, #0x555, lsl #48
0x0000196c: e9 1b 00 f9              str      x9, [sp, #0x30]
0x00001970: 68 02 40 f9              ldr      x8, [x19]
0x00001974: 08 b5 42 f9              ldr      x8, [x8, #0x568]
0x00001978: e0 03 13 aa              mov      x0, x19
0x0000197c: e1 03 1a aa              mov      x1, x26
0x00001980: e2 03 1c 2a              mov      w2, w28
0x00001984: 00 01 3f d6              blr      x8
0x00001988: 68 02 40 f9              ldr      x8, [x19]
0x0000198c: f8 03 00 aa              mov      x24, x0
0x00001990: 08 a5 42 f9              ldr      x8, [x8, #0x548]
0x00001994: e0 03 13 aa              mov      x0, x19
0x00001998: e1 03 18 aa              mov      x1, x24
0x0000199c: e2 03 1f aa              mov      x2, xzr
0x000019a0: 00 01 3f d6              blr      x8
0x000019a4: f5 03 00 aa              mov      x21, x0
0x000019a8: e0 03 01 91              add      x0, sp, #0x40
0x000019ac: e1 03 15 aa              mov      x1, x21
0x000019b0: 52 01 00 94              bl       #0x1ef8
0x000019b4: e8 27 4b a9              ldp      x8, x9, [sp, #0xb0]
0x000019b8: 1f 01 09 eb              cmp      x8, x9
0x000019bc: 42 01 00 54              b.hs     #0x19e4
0x000019c0: e9 2b 40 f9              ldr      x9, [sp, #0x50]
0x000019c4: e0 13 c0 3d              ldr      q0, [sp, #0x40]
0x000019c8: 0a 61 00 91              add      x10, x8, #0x18
0x000019cc: 09 09 00 f9              str      x9, [x8, #0x10]
0x000019d0: 00 01 80 3d              str      q0, [x8]
0x000019d4: ff ff 04 a9              stp      xzr, xzr, [sp, #0x48]
0x000019d8: ff 23 00 f9              str      xzr, [sp, #0x40]
0x000019dc: ea 5b 00 f9              str      x10, [sp, #0xb0]
0x000019e0: 43 00 00 14              b        #0x1aec                    ; -> Java_com_ss_bytenn_API_nativeInitEngine+0x470
0x000019e4: ea 57 40 f9              ldr      x10, [sp, #0xa8]
0x000019e8: eb f3 01 b2              mov      x11, #-0x5555555555555556
0x000019ec: 4b 55 e1 f2              movk     x11, #0xaaa, lsl #48
0x000019f0: 08 01 0a cb              sub      x8, x8, x10
0x000019f4: 08 fd 43 93              asr      x8, x8, #3
0x000019f8: 02 7d 19 9b              mul      x2, x8, x25
0x000019fc: 48 04 00 91              add      x8, x2, #1
0x00001a00: 1f 01 0b eb              cmp      x8, x11
0x00001a04: 28 16 00 54              b.hi     #0x1cc8
0x00001a08: 29 01 0a cb              sub      x9, x9, x10
0x00001a0c: 29 fd 43 93              asr      x9, x9, #3
0x00001a10: 29 7d 19 9b              mul      x9, x9, x25
0x00001a14: e1 f3 01 b2              mov      x1, #-0x5555555555555556
0x00001a18: 3f 01 17 eb              cmp      x9, x23
0x00001a1c: 41 55 e1 f2              movk     x1, #0xaaa, lsl #48
0x00001a20: 88 00 00 54              b.hi     #0x1a30
0x00001a24: 29 f9 7f d3              lsl      x9, x9, #1
0x00001a28: 3f 01 08 eb              cmp      x9, x8
0x00001a2c: 01 31 89 9a              csel     x1, x8, x9, lo
0x00001a30: a0 23 02 d1              sub      x0, x29, #0x88
0x00001a34: e3 03 16 aa              mov      x3, x22
0x00001a38: 1d 06 00 94              bl       #0x32ac
0x00001a3c: a8 83 58 f8              ldur     x8, [x29, #-0x78]
0x00001a40: e9 2b 40 f9              ldr      x9, [sp, #0x50]
0x00001a44: e0 13 c0 3d              ldr      q0, [sp, #0x40]
0x00001a48: 09 09 00 f9              str      x9, [x8, #0x10]
0x00001a4c: 00 01 80 3d              str      q0, [x8]
0x00001a50: a8 83 58 f8              ldur     x8, [x29, #-0x78]
0x00001a54: e9 ab 4a a9              ldp      x9, x10, [sp, #0xa8]
0x00001a58: ff ff 04 a9              stp      xzr, xzr, [sp, #0x48]
0x00001a5c: ff 23 00 f9              str      xzr, [sp, #0x40]
0x00001a60: 0b 61 00 91              add      x11, x8, #0x18
0x00001a64: 5f 01 09 eb              cmp      x10, x9
0x00001a68: ab 83 18 f8              stur     x11, [x29, #-0x78]
0x00001a6c: 20 02 00 54              b.eq     #0x1ab0
0x00001a70: a8 03 58 f8              ldur     x8, [x29, #-0x80]
0x00001a74: 4b 81 5f f8              ldur     x11, [x10, #-8]
0x00001a78: 40 81 de 3c              ldur     q0, [x10, #-0x18]
0x00001a7c: 0b 81 1f f8              stur     x11, [x8, #-8]
0x00001a80: 00 81 9e 3c              stur     q0, [x8, #-0x18]
0x00001a84: 5f 7d 3f a9              stp      xzr, xzr, [x10, #-0x10]
0x00001a88: 5f 81 1e f8              stur     xzr, [x10, #-0x18]
0x00001a8c: a8 03 58 f8              ldur     x8, [x29, #-0x80]
0x00001a90: 4a 61 00 d1              sub      x10, x10, #0x18
0x00001a94: 3f 01 0a eb              cmp      x9, x10
0x00001a98: 08 61 00 d1              sub      x8, x8, #0x18
0x00001a9c: a8 03 18 f8              stur     x8, [x29, #-0x80]
0x00001aa0: a1 fe ff 54              b.ne     #0x1a74
0x00001aa4: e0 83 ca 3c              ldur     q0, [sp, #0xa8]
0x00001aa8: ab 83 58 f8              ldur     x11, [x29, #-0x78]
0x00001aac: 03 00 00 14              b        #0x1ab8                    ; -> Java_com_ss_bytenn_API_nativeInitEngine+0x43c
0x00001ab0: a8 03 58 f8              ldur     x8, [x29, #-0x80]
0x00001ab4: 20 0d 08 4e              dup      v0.2d, x9
0x00001ab8: e9 5f 40 f9              ldr      x9, [sp, #0xb8]
0x00001abc: aa 03 59 f8              ldur     x10, [x29, #-0x70]
0x00001ac0: a0 23 02 d1              sub      x0, x29, #0x88
0x00001ac4: e8 af 0a a9              stp      x8, x11, [sp, #0xa8]
0x00001ac8: a0 03 98 3c              stur     q0, [x29, #-0x80]
0x00001acc: ea 5f 00 f9              str      x10, [sp, #0xb8]
0x00001ad0: a9 03 19 f8              stur     x9, [x29, #-0x70]
0x00001ad4: a0 83 17 fc              stur     d0, [x29, #-0x88]
0x00001ad8: 14 06 00 94              bl       #0x3328
0x00001adc: e8 03 41 39              ldrb     w8, [sp, #0x40]
0x00001ae0: 68 00 00 36              tbz      w8, #0, #0x1aec
0x00001ae4: e0 2b 40 f9              ldr      x0, [sp, #0x50]
0x00001ae8: 0a fe ff 97              bl       #0x1310                    ; -> _ZdlPv@plt
0x00001aec: 68 02 40 f9              ldr      x8, [x19]
0x00001af0: 08 a9 42 f9              ldr      x8, [x8, #0x550]
0x00001af4: e0 03 13 aa              mov      x0, x19
0x00001af8: e1 03 18 aa              mov      x1, x24
0x00001afc: e2 03 15 aa              mov      x2, x21
0x00001b00: 00 01 3f d6              blr      x8
0x00001b04: 68 02 40 f9              ldr      x8, [x19]
0x00001b08: 08 5d 40 f9              ldr      x8, [x8, #0xb8]
0x00001b0c: e0 03 13 aa              mov      x0, x19
0x00001b10: e1 03 18 aa              mov      x1, x24
0x00001b14: 00 01 3f d6              blr      x8
0x00001b18: 9c 07 00 11              add      w28, w28, #1
0x00001b1c: 9f 03 1b 6b              cmp      w28, w27
0x00001b20: 81 f2 ff 54              b.ne     #0x1970
0x00001b24: e3 73 40 b9              ldr      w3, [sp, #0x70]
0x00001b28: 21 00 00 b0              adrp     x1, #0x6000
0x00001b2c: 22 00 00 d0              adrp     x2, #0x7000
0x00001b30: 21 b0 3d 91              add      x1, x1, #0xf6c
0x00001b34: 42 08 05 91              add      x2, x2, #0x142
0x00001b38: 60 00 80 52              mov      w0, #3
0x00001b3c: 15 fe ff 97              bl       #0x1390                    ; -> __android_log_print@plt
0x00001b40: f6 f3 41 a9              ldp      x22, x28, [sp, #0x18]
0x00001b44: f7 1f 40 f9              ldr      x23, [sp, #0x38]
0x00001b48: f9 e3 40 a9              ldp      x25, x24, [sp, #8]
0x00001b4c: e8 17 40 f9              ldr      x8, [sp, #0x28]
0x00001b50: 00 01 40 f9              ldr      x0, [x8]
0x00001b54: 08 00 40 f9              ldr      x8, [x0]
0x00001b58: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00001b5c: e1 63 01 91              add      x1, sp, #0x58
0x00001b60: 00 01 3f d6              blr      x8
0x00001b64: f5 03 00 2a              mov      w21, w0
0x00001b68: c0 03 00 34              cbz      w0, #0x1be0
0x00001b6c: 21 00 00 b0              adrp     x1, #0x6000
0x00001b70: 22 00 00 b0              adrp     x2, #0x6000
0x00001b74: 21 b0 3d 91              add      x1, x1, #0xf6c
0x00001b78: 42 d8 3d 91              add      x2, x2, #0xf76
0x00001b7c: c0 00 80 52              mov      w0, #6
0x00001b80: e3 03 15 2a              mov      w3, w21
0x00001b84: 03 fe ff 97              bl       #0x1390                    ; -> __android_log_print@plt
0x00001b88: 1d 00 00 14              b        #0x1bfc                    ; -> Java_com_ss_bytenn_API_nativeInitEngine+0x580
0x00001b8c: 75 00 80 52              mov      w21, #3
0x00001b90: 41 00 00 14              b        #0x1c94                    ; -> Java_com_ss_bytenn_API_nativeInitEngine+0x618
0x00001b94: 68 02 40 f9              ldr      x8, [x19]
0x00001b98: 08 5d 40 f9              ldr      x8, [x8, #0xb8]
0x00001b9c: e0 03 13 aa              mov      x0, x19
0x00001ba0: e1 03 14 aa              mov      x1, x20
0x00001ba4: 00 01 3f d6              blr      x8
0x00001ba8: 68 02 40 f9              ldr      x8, [x19]
0x00001bac: 08 a9 42 f9              ldr      x8, [x8, #0x550]
0x00001bb0: e2 1f 40 f9              ldr      x2, [sp, #0x38]
0x00001bb4: e0 03 13 aa              mov      x0, x19
0x00001bb8: e1 03 17 aa              mov      x1, x23
0x00001bbc: 00 01 3f d6              blr      x8
0x00001bc0: 68 02 40 f9              ldr      x8, [x19]
0x00001bc4: 08 a9 42 f9              ldr      x8, [x8, #0x550]
0x00001bc8: e0 03 13 aa              mov      x0, x19
0x00001bcc: e1 03 16 aa              mov      x1, x22
0x00001bd0: e2 03 19 aa              mov      x2, x25
0x00001bd4: 00 01 3f d6              blr      x8
0x00001bd8: 75 00 80 52              mov      w21, #3
0x00001bdc: 19 00 00 14              b        #0x1c40                    ; -> Java_com_ss_bytenn_API_nativeInitEngine+0x5c4
0x00001be0: 21 00 00 b0              adrp     x1, #0x6000
0x00001be4: 22 00 00 b0              adrp     x2, #0x6000
0x00001be8: 21 b0 3d 91              add      x1, x1, #0xf6c
0x00001bec: 42 d8 3d 91              add      x2, x2, #0xf76
0x00001bf0: 80 00 80 52              mov      w0, #4
0x00001bf4: e3 03 1f 2a              mov      w3, wzr
0x00001bf8: e6 fd ff 97              bl       #0x1390                    ; -> __android_log_print@plt
0x00001bfc: 68 02 40 f9              ldr      x8, [x19]
0x00001c00: 08 5d 40 f9              ldr      x8, [x8, #0xb8]
0x00001c04: e0 03 13 aa              mov      x0, x19
0x00001c08: e1 03 14 aa              mov      x1, x20
0x00001c0c: 00 01 3f d6              blr      x8
0x00001c10: 68 02 40 f9              ldr      x8, [x19]
0x00001c14: 08 a9 42 f9              ldr      x8, [x8, #0x550]
0x00001c18: e0 03 13 aa              mov      x0, x19
0x00001c1c: e1 03 16 aa              mov      x1, x22
0x00001c20: e2 03 17 aa              mov      x2, x23
0x00001c24: 00 01 3f d6              blr      x8
0x00001c28: 68 02 40 f9              ldr      x8, [x19]
0x00001c2c: 08 a9 42 f9              ldr      x8, [x8, #0x550]
0x00001c30: e0 03 13 aa              mov      x0, x19
0x00001c34: e1 03 18 aa              mov      x1, x24
0x00001c38: e2 03 19 aa              mov      x2, x25
0x00001c3c: 00 01 3f d6              blr      x8
0x00001c40: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x00001c44: 60 00 00 b4              cbz      x0, #0x1c50
0x00001c48: e0 83 00 f9              str      x0, [sp, #0x100]
0x00001c4c: b1 fd ff 97              bl       #0x1310                    ; -> _ZdlPv@plt
0x00001c50: e8 63 43 39              ldrb     w8, [sp, #0xd8]
0x00001c54: 68 00 00 36              tbz      w8, #0, #0x1c60
0x00001c58: e0 77 40 f9              ldr      x0, [sp, #0xe8]
0x00001c5c: ad fd ff 97              bl       #0x1310                    ; -> _ZdlPv@plt
0x00001c60: f3 63 01 91              add      x19, sp, #0x58
0x00001c64: 60 a2 01 91              add      x0, x19, #0x68
0x00001c68: 9b 04 00 94              bl       #0x2ed4
0x00001c6c: 60 42 01 91              add      x0, x19, #0x50
0x00001c70: 99 04 00 94              bl       #0x2ed4
0x00001c74: e8 43 42 39              ldrb     w8, [sp, #0x90]
0x00001c78: 68 00 00 36              tbz      w8, #0, #0x1c84
0x00001c7c: e0 53 40 f9              ldr      x0, [sp, #0xa0]
0x00001c80: a4 fd ff 97              bl       #0x1310                    ; -> _ZdlPv@plt
0x00001c84: e8 e3 41 39              ldrb     w8, [sp, #0x78]
0x00001c88: 68 00 00 36              tbz      w8, #0, #0x1c94
0x00001c8c: e0 47 40 f9              ldr      x0, [sp, #0x88]
0x00001c90: a0 fd ff 97              bl       #0x1310                    ; -> _ZdlPv@plt
0x00001c94: 88 17 40 f9              ldr      x8, [x28, #0x28]
0x00001c98: a9 03 5a f8              ldur     x9, [x29, #-0x60]
0x00001c9c: 1f 01 09 eb              cmp      x8, x9
0x00001ca0: 81 01 00 54              b.ne     #0x1cd0
0x00001ca4: e0 03 15 2a              mov      w0, w21
0x00001ca8: fd 7b 5a a9              ldp      x29, x30, [sp, #0x1a0]
0x00001cac: f4 4f 59 a9              ldp      x20, x19, [sp, #0x190]
0x00001cb0: f6 57 58 a9              ldp      x22, x21, [sp, #0x180]
0x00001cb4: f8 5f 57 a9              ldp      x24, x23, [sp, #0x170]
0x00001cb8: fa 67 56 a9              ldp      x26, x25, [sp, #0x160]
0x00001cbc: fc 6f 55 a9              ldp      x28, x27, [sp, #0x150]
0x00001cc0: ff c3 06 91              add      sp, sp, #0x1b0
0x00001cc4: c0 03 5f d6              ret      
0x00001cc8: e0 1b 40 f9              ldr      x0, [sp, #0x30]
0x00001ccc: a5 05 00 94              bl       #0x3360
0x00001cd0: dc fd ff 97              bl       #0x1440                    ; -> __stack_chk_fail@plt
0x00001cd4: 0b 00 00 14              b        #0x1d00                    ; -> Java_com_ss_bytenn_API_nativeInitEngine+0x684
0x00001cd8: 12 00 00 14              b        #0x1d20                    ; -> Java_com_ss_bytenn_API_nativeInitEngine+0x6a4
0x00001cdc: 11 00 00 14              b        #0x1d20                    ; -> Java_com_ss_bytenn_API_nativeInitEngine+0x6a4
0x00001ce0: 10 00 00 14              b        #0x1d20                    ; -> Java_com_ss_bytenn_API_nativeInitEngine+0x6a4
0x00001ce4: 0f 00 00 14              b        #0x1d20                    ; -> Java_com_ss_bytenn_API_nativeInitEngine+0x6a4
0x00001ce8: 0e 00 00 14              b        #0x1d20                    ; -> Java_com_ss_bytenn_API_nativeInitEngine+0x6a4
0x00001cec: 0d 00 00 14              b        #0x1d20                    ; -> Java_com_ss_bytenn_API_nativeInitEngine+0x6a4
0x00001cf0: 0c 00 00 14              b        #0x1d20                    ; -> Java_com_ss_bytenn_API_nativeInitEngine+0x6a4
0x00001cf4: 0b 00 00 14              b        #0x1d20                    ; -> Java_com_ss_bytenn_API_nativeInitEngine+0x6a4
0x00001cf8: 0a 00 00 14              b        #0x1d20                    ; -> Java_com_ss_bytenn_API_nativeInitEngine+0x6a4
0x00001cfc: 09 00 00 14              b        #0x1d20                    ; -> Java_com_ss_bytenn_API_nativeInitEngine+0x6a4
0x00001d00: e8 03 41 39              ldrb     w8, [sp, #0x40]
0x00001d04: f3 03 00 aa              mov      x19, x0
0x00001d08: e8 00 00 36              tbz      w8, #0, #0x1d24
0x00001d0c: e0 2b 40 f9              ldr      x0, [sp, #0x50]
0x00001d10: 80 fd ff 97              bl       #0x1310                    ; -> _ZdlPv@plt
0x00001d14: 04 00 00 14              b        #0x1d24                    ; -> Java_com_ss_bytenn_API_nativeInitEngine+0x6a8
0x00001d18: 02 00 00 14              b        #0x1d20                    ; -> Java_com_ss_bytenn_API_nativeInitEngine+0x6a4
0x00001d1c: 01 00 00 14              b        #0x1d20                    ; -> Java_com_ss_bytenn_API_nativeInitEngine+0x6a4
0x00001d20: f3 03 00 aa              mov      x19, x0
0x00001d24: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x00001d28: 60 00 00 b4              cbz      x0, #0x1d34
0x00001d2c: e0 83 00 f9              str      x0, [sp, #0x100]
0x00001d30: 78 fd ff 97              bl       #0x1310                    ; -> _ZdlPv@plt
0x00001d34: e8 63 43 39              ldrb     w8, [sp, #0xd8]
0x00001d38: 68 00 00 36              tbz      w8, #0, #0x1d44
0x00001d3c: e0 77 40 f9              ldr      x0, [sp, #0xe8]
0x00001d40: 74 fd ff 97              bl       #0x1310                    ; -> _ZdlPv@plt
0x00001d44: f4 63 01 91              add      x20, sp, #0x58
0x00001d48: 80 a2 01 91              add      x0, x20, #0x68
0x00001d4c: 62 04 00 94              bl       #0x2ed4
0x00001d50: 80 42 01 91              add      x0, x20, #0x50
0x00001d54: 60 04 00 94              bl       #0x2ed4
0x00001d58: e8 43 42 39              ldrb     w8, [sp, #0x90]
0x00001d5c: 68 00 00 36              tbz      w8, #0, #0x1d68
0x00001d60: e0 53 40 f9              ldr      x0, [sp, #0xa0]
0x00001d64: 6b fd ff 97              bl       #0x1310                    ; -> _ZdlPv@plt
0x00001d68: e8 e3 41 39              ldrb     w8, [sp, #0x78]
0x00001d6c: 68 00 00 36              tbz      w8, #0, #0x1d78
0x00001d70: e0 47 40 f9              ldr      x0, [sp, #0x88]
0x00001d74: 67 fd ff 97              bl       #0x1310                    ; -> _ZdlPv@plt
0x00001d78: e0 03 13 aa              mov      x0, x19
0x00001d7c: 89 0d 00 94              bl       #0x53a0
