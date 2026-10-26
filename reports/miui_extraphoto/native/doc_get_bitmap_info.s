; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/xp/libdoc_photo_process.so
; target  : address 0x78a00
; start   : 0x78a00
; size    : auto (to next symbol/ret)
; bytes   : 0xc8   instructions: 50
; generated: 2026-10-24T00:40:01

0x00078a00: ff 03 02 d1              sub      sp, sp, #0x80
0x00078a04: f9 1b 00 f9              str      x25, [sp, #0x30]
0x00078a08: f8 5f 04 a9              stp      x24, x23, [sp, #0x40]
0x00078a0c: f6 57 05 a9              stp      x22, x21, [sp, #0x50]
0x00078a10: f4 4f 06 a9              stp      x20, x19, [sp, #0x60]
0x00078a14: fd 7b 07 a9              stp      x29, x30, [sp, #0x70]
0x00078a18: fd c3 01 91              add      x29, sp, #0x70
0x00078a1c: 59 d0 3b d5              mrs      x25, tpidr_el0
0x00078a20: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x00078a24: f7 03 04 aa              mov      x23, x4
0x00078a28: f6 03 03 aa              mov      x22, x3
0x00078a2c: f5 03 02 aa              mov      x21, x2
0x00078a30: f3 03 01 aa              mov      x19, x1
0x00078a34: f4 03 00 aa              mov      x20, x0
0x00078a38: e8 17 00 f9              str      x8, [sp, #0x28]
0x00078a3c: 61 04 00 b4              cbz      x1, #0x78ac8
0x00078a40: e2 23 00 91              add      x2, sp, #8
0x00078a44: e0 03 14 aa              mov      x0, x20
0x00078a48: e1 03 13 aa              mov      x1, x19
0x00078a4c: e5 bf ff 97              bl       #0x689e0                   ; -> AndroidBitmap_getInfo@plt
0x00078a50: c0 03 f8 37              tbnz     w0, #0x1f, #0x78ac8
0x00078a54: e8 17 40 b9              ldr      w8, [sp, #0x14]
0x00078a58: e0 03 1f 2a              mov      w0, wzr
0x00078a5c: 1f 05 00 71              cmp      w8, #1
0x00078a60: 61 03 00 54              b.ne     #0x78acc
0x00078a64: e2 83 00 91              add      x2, sp, #0x20
0x00078a68: e0 03 14 aa              mov      x0, x20
0x00078a6c: e1 03 13 aa              mov      x1, x19
0x00078a70: 70 bd ff 97              bl       #0x68030                   ; -> AndroidBitmap_lockPixels@plt
0x00078a74: a0 02 f8 37              tbnz     w0, #0x1f, #0x78ac8
0x00078a78: e8 63 41 29              ldp      w8, w24, [sp, #8]
0x00078a7c: c8 02 00 b9              str      w8, [x22]
0x00078a80: f8 02 00 b9              str      w24, [x23]
0x00078a84: d6 02 40 b9              ldr      w22, [x22]
0x00078a88: c8 7e 18 1b              mul      w8, w22, w24
0x00078a8c: 09 05 08 0b              add      w9, w8, w8, lsl #1
0x00078a90: 29 7d 40 93              sxtw     x9, w9
0x00078a94: 1f 01 00 71              cmp      w8, #0
0x00078a98: 20 a1 9f da              csinv    x0, x9, xzr, ge
0x00078a9c: c1 92 0e 94              bl       #0x41d5a0
0x00078aa0: a0 02 00 f9              str      x0, [x21]
0x00078aa4: e1 13 40 f9              ldr      x1, [sp, #0x20]
0x00078aa8: e2 03 16 2a              mov      w2, w22
0x00078aac: e3 03 18 2a              mov      w3, w24
0x00078ab0: 67 ff ff 97              bl       #0x7884c
0x00078ab4: e0 03 14 aa              mov      x0, x20
0x00078ab8: e1 03 13 aa              mov      x1, x19
0x00078abc: 91 be ff 97              bl       #0x68500                   ; -> AndroidBitmap_unlockPixels@plt
0x00078ac0: e0 03 00 32              mov      w0, #1
0x00078ac4: 02 00 00 14              b        #0x78acc
