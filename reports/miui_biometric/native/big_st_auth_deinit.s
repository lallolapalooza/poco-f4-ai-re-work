; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : st_auth_deinit (0x10c7a4)
; start   : 0x10c7a4
; size    : 0x164
; bytes   : 0x164   instructions: 89
; generated: 2026-10-24T00:26:29

0x0010c7a4: fd 7b bd a9              stp      x29, x30, [sp, #-0x30]!
0x0010c7a8: 23 32 00 d0              adrp     x3, #0x752000
0x0010c7ac: 40 32 00 b0              adrp     x0, #0x755000
0x0010c7b0: 41 32 00 b0              adrp     x1, #0x755000
0x0010c7b4: 63 00 03 91              add      x3, x3, #0xc0
0x0010c7b8: fd 03 00 91              mov      x29, sp
0x0010c7bc: 00 c0 06 91              add      x0, x0, #0x1b0
0x0010c7c0: 21 40 07 91              add      x1, x1, #0x1d0
0x0010c7c4: 62 2e 80 52              mov      w2, #0x173
0x0010c7c8: 63 00 03 91              add      x3, x3, #0xc0
0x0010c7cc: f3 53 01 a9              stp      x19, x20, [sp, #0x10]
0x0010c7d0: f5 13 00 f9              str      x21, [sp, #0x20]
0x0010c7d4: 34 ee 02 d0              adrp     x20, #0x5ed2000
0x0010c7d8: 7c c8 00 94              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x0010c7dc: 80 82 3d 91              add      x0, x20, #0xf60
0x0010c7e0: 13 04 40 f9              ldr      x19, [x0, #8]
0x0010c7e4: 33 02 00 b4              cbz      x19, #0x10c828
0x0010c7e8: 75 06 40 f9              ldr      x21, [x19, #8]
0x0010c7ec: 75 01 00 b4              cbz      x21, #0x10c818
0x0010c7f0: e0 70 02 b0              adrp     x0, #0x4f29000
0x0010c7f4: 00 94 47 f9              ldr      x0, [x0, #0xf28]           ; -> pthread_create@got
0x0010c7f8: e0 04 00 b4              cbz      x0, #0x10c894
0x0010c7fc: a0 22 00 91              add      x0, x21, #8
0x0010c800: 01 fc 5f 88              ldaxr    w1, [x0]
0x0010c804: 22 04 00 51              sub      w2, w1, #1
0x0010c808: 02 fc 03 88              stlxr    w3, w2, [x0]
0x0010c80c: a3 ff ff 35              cbnz     w3, #0x10c800
0x0010c810: 3f 04 00 71              cmp      w1, #1
0x0010c814: a0 05 00 54              b.eq     #0x10c8c8
0x0010c818: e0 03 13 aa              mov      x0, x19
0x0010c81c: 79 72 17 94              bl       #0x6e9200                  ; -> _ZdlPv
0x0010c820: 80 82 3d 91              add      x0, x20, #0xf60
0x0010c824: 1f 04 00 f9              str      xzr, [x0, #8]
0x0010c828: 93 b2 47 f9              ldr      x19, [x20, #0xf60]
0x0010c82c: d3 02 00 b4              cbz      x19, #0x10c884
0x0010c830: 60 02 01 91              add      x0, x19, #0x40
0x0010c834: 03 08 40 f9              ldr      x3, [x0, #0x10]
0x0010c838: 83 00 00 b4              cbz      x3, #0x10c848
0x0010c83c: e1 03 00 aa              mov      x1, x0
0x0010c840: 62 00 80 52              mov      w2, #3
0x0010c844: 60 00 3f d6              blr      x3
0x0010c848: 60 82 00 91              add      x0, x19, #0x20
0x0010c84c: 03 08 40 f9              ldr      x3, [x0, #0x10]
0x0010c850: 83 00 00 b4              cbz      x3, #0x10c860
0x0010c854: e1 03 00 aa              mov      x1, x0
0x0010c858: 62 00 80 52              mov      w2, #3
0x0010c85c: 60 00 3f d6              blr      x3
0x0010c860: 63 0a 40 f9              ldr      x3, [x19, #0x10]
0x0010c864: a3 00 00 b4              cbz      x3, #0x10c878
0x0010c868: e0 03 13 aa              mov      x0, x19
0x0010c86c: e1 03 13 aa              mov      x1, x19
0x0010c870: 62 00 80 52              mov      w2, #3
0x0010c874: 60 00 3f d6              blr      x3
0x0010c878: e0 03 13 aa              mov      x0, x19
0x0010c87c: 61 72 17 94              bl       #0x6e9200                  ; -> _ZdlPv
0x0010c880: 9f b2 07 f9              str      xzr, [x20, #0xf60]
0x0010c884: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x0010c888: f5 13 40 f9              ldr      x21, [sp, #0x20]
0x0010c88c: fd 7b c3 a8              ldp      x29, x30, [sp], #0x30
0x0010c890: c0 03 5f d6              ret      
0x0010c894: a0 0a 40 b9              ldr      w0, [x21, #8]
0x0010c898: 01 04 00 51              sub      w1, w0, #1
0x0010c89c: 1f 04 00 71              cmp      w0, #1
0x0010c8a0: a1 0a 00 b9              str      w1, [x21, #8]
0x0010c8a4: a1 fb ff 54              b.ne     #0x10c818
0x0010c8a8: a1 02 40 f9              ldr      x1, [x21]
0x0010c8ac: e0 03 15 aa              mov      x0, x21
0x0010c8b0: 21 08 40 f9              ldr      x1, [x1, #0x10]
0x0010c8b4: 20 00 3f d6              blr      x1
0x0010c8b8: a0 0e 40 b9              ldr      w0, [x21, #0xc]
0x0010c8bc: 01 04 00 51              sub      w1, w0, #1
0x0010c8c0: a1 0e 00 b9              str      w1, [x21, #0xc]
0x0010c8c4: 0a 00 00 14              b        #0x10c8ec                  ; -> st_auth_deinit+0x148
0x0010c8c8: a1 02 40 f9              ldr      x1, [x21]
0x0010c8cc: e0 03 15 aa              mov      x0, x21
0x0010c8d0: 21 08 40 f9              ldr      x1, [x1, #0x10]
0x0010c8d4: 20 00 3f d6              blr      x1
0x0010c8d8: a1 32 00 91              add      x1, x21, #0xc
0x0010c8dc: 20 fc 5f 88              ldaxr    w0, [x1]
0x0010c8e0: 02 04 00 51              sub      w2, w0, #1
0x0010c8e4: 22 fc 03 88              stlxr    w3, w2, [x1]
0x0010c8e8: a3 ff ff 35              cbnz     w3, #0x10c8dc
0x0010c8ec: 1f 04 00 71              cmp      w0, #1
0x0010c8f0: 41 f9 ff 54              b.ne     #0x10c818
0x0010c8f4: a1 02 40 f9              ldr      x1, [x21]
0x0010c8f8: e0 03 15 aa              mov      x0, x21
0x0010c8fc: 21 0c 40 f9              ldr      x1, [x1, #0x18]
0x0010c900: 20 00 3f d6              blr      x1
0x0010c904: c5 ff ff 17              b        #0x10c818                  ; -> st_auth_deinit+0x74
