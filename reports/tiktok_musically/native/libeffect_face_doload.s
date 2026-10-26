; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/ttlibs/libeffect.so
; target  : address 0x962c00
; start   : 0x962c00
; size    : 0x700
; bytes   : 0x700   instructions: 448
; generated: 2026-10-23T23:47:47

0x00962c00: 08 51 03 91              add      x8, x8, #0xd4
0x00962c04: 09 a0 04 a9              stp      x9, x8, [x0, #0x48]
0x00962c08: 48 e9 ff f0              adrp     x8, #0x68d000
0x00962c0c: 08 81 19 91              add      x8, x8, #0x660
0x00962c10: 08 2c 00 f9              str      x8, [x0, #0x58]
0x00962c14: 48 e9 ff d0              adrp     x8, #0x68c000
0x00962c18: 4a e9 ff b0              adrp     x10, #0x68b000
0x00962c1c: 4b e9 ff b0              adrp     x11, #0x68b000
0x00962c20: 08 01 36 91              add      x8, x8, #0xd80
0x00962c24: 4a e1 11 91              add      x10, x10, #0x478
0x00962c28: 6b 91 15 91              add      x11, x11, #0x564
0x00962c2c: 00 1d 18 4e              mov      v0.d[1], x8
0x00962c30: 0a 2c 01 a9              stp      x10, x11, [x0, #0x10]
0x00962c34: 00 80 83 3c              stur     q0, [x0, #0x38]
0x00962c38: 60 82 01 f9              str      x0, [x19, #0x300]
0x00962c3c: 5a b3 04 94              bl       #0xa8f9a4
0x00962c40: 08 00 00 94              bl       #0x962c60
0x00962c44: 88 16 40 f9              ldr      x8, [x20, #0x28]
0x00962c48: ab a2 04 94              bl       #0xa8b6f4
0x00962c4c: 81 00 00 54              b.ne     #0x962c5c
0x00962c50: f3 7b 42 a9              ldp      x19, x30, [sp, #0x20]
0x00962c54: f4 0b 40 f9              ldr      x20, [sp, #0x10]
0x00962c58: ce a3 04 14              b        #0xa8bb90
0x00962c5c: f5 89 e5 97              bl       #0x2c5430                  ; -> __stack_chk_fail@plt
0x00962c60: ff 43 01 d1              sub      sp, sp, #0x50
0x00962c64: f5 53 03 a9              stp      x21, x20, [sp, #0x30]
0x00962c68: f3 7b 04 a9              stp      x19, x30, [sp, #0x40]
0x00962c6c: 63 ad 04 94              bl       #0xa8e1f8
0x00962c70: 61 21 00 f0              adrp     x1, #0xd91000
0x00962c74: f4 03 00 aa              mov      x20, x0
0x00962c78: 21 d8 1a 91              add      x1, x1, #0x6b6
0x00962c7c: 5d ce 04 94              bl       #0xa965f0
0x00962c80: 48 00 a0 52              mov      w8, #0x20000
0x00962c84: e2 43 00 91              add      x2, sp, #0x10
0x00962c88: bf ae 04 94              bl       #0xa8e784
0x00962c8c: 6b a2 04 94              bl       #0xa8b638
0x00962c90: 61 21 00 f0              adrp     x1, #0xd91000
0x00962c94: 21 1c 1b 91              add      x1, x1, #0x6c7
0x00962c98: c1 a6 04 94              bl       #0xa8c79c
0x00962c9c: e2 43 00 91              add      x2, sp, #0x10
0x00962ca0: cd b1 04 94              bl       #0xa8f3d4
0x00962ca4: 65 a2 04 94              bl       #0xa8b638
0x00962ca8: a0 70 06 94              bl       #0xafef28
0x00962cac: bc a6 04 94              bl       #0xa8c79c
0x00962cb0: 28 00 80 52              mov      w8, #1
0x00962cb4: e2 43 00 91              add      x2, sp, #0x10
0x00962cb8: b3 ae 04 94              bl       #0xa8e784
0x00962cbc: e0 43 00 91              add      x0, sp, #0x10
0x00962cc0: 78 16 e6 97              bl       #0x2e86a0
0x00962cc4: 61 21 00 f0              adrp     x1, #0xd91000
0x00962cc8: 21 a8 1b 91              add      x1, x1, #0x6ea
0x00962ccc: b4 a6 04 94              bl       #0xa8c79c
0x00962cd0: e2 43 00 91              add      x2, sp, #0x10
0x00962cd4: c0 b1 04 94              bl       #0xa8f3d4
0x00962cd8: e0 43 00 91              add      x0, sp, #0x10
0x00962cdc: 71 16 e6 97              bl       #0x2e86a0
0x00962ce0: a8 16 40 f9              ldr      x8, [x21, #0x28]
0x00962ce4: 72 a1 04 94              bl       #0xa8b2ac
0x00962ce8: a1 00 00 54              b.ne     #0x962cfc
0x00962cec: f3 7b 44 a9              ldp      x19, x30, [sp, #0x40]
0x00962cf0: f5 53 43 a9              ldp      x21, x20, [sp, #0x30]
0x00962cf4: 20 00 80 52              mov      w0, #1
0x00962cf8: 17 a2 04 14              b        #0xa8b554
0x00962cfc: cd 89 e5 97              bl       #0x2c5430                  ; -> __stack_chk_fail@plt
0x00962d00: f3 7b bf a9              stp      x19, x30, [sp, #-0x10]!
0x00962d04: f3 03 00 aa              mov      x19, x0
0x00962d08: 00 80 41 f9              ldr      x0, [x0, #0x300]
0x00962d0c: e8 45 00 d0              adrp     x8, #0x1220000
0x00962d10: 08 81 12 91              add      x8, x8, #0x4a0
0x00962d14: 68 02 00 f9              str      x8, [x19]
0x00962d18: 60 00 00 b4              cbz      x0, #0x962d24
0x00962d1c: b5 89 e5 97              bl       #0x2c53f0                  ; -> _ZdlPv@plt
0x00962d20: 7f 82 01 f9              str      xzr, [x19, #0x300]
0x00962d24: 60 82 0b 91              add      x0, x19, #0x2e0
0x00962d28: 5e 16 e6 97              bl       #0x2e86a0
0x00962d2c: 60 22 0b 91              add      x0, x19, #0x2c8
0x00962d30: 88 a5 04 94              bl       #0xa8c350
0x00962d34: f3 7b c1 a8              ldp      x19, x30, [sp], #0x10
0x00962d38: 65 8b f3 17              b        #0x645acc
0x00962d3c: f3 7b bf a9              stp      x19, x30, [sp, #-0x10]!
0x00962d40: f3 03 00 aa              mov      x19, x0
0x00962d44: ef ff ff 97              bl       #0x962d00
0x00962d48: e0 03 13 aa              mov      x0, x19
0x00962d4c: f3 7b c1 a8              ldp      x19, x30, [sp], #0x10
0x00962d50: a8 89 e5 17              b        #0x2c53f0                  ; -> _ZdlPv@plt
0x00962d54: ff c3 00 d1              sub      sp, sp, #0x30
0x00962d58: f4 0b 00 f9              str      x20, [sp, #0x10]
0x00962d5c: f3 7b 02 a9              stp      x19, x30, [sp, #0x20]
0x00962d60: 28 00 40 39              ldrb     w8, [x1]
0x00962d64: 25 0c 40 f9              ldr      x5, [x1, #0x18]
0x00962d68: 85 a3 04 94              bl       #0xa8bb7c
0x00962d6c: 68 00 00 37              tbnz     w8, #0, #0x962d78
0x00962d70: 66 06 00 91              add      x6, x19, #1
0x00962d74: 02 00 00 14              b        #0x962d7c
0x00962d78: 66 0a 40 f9              ldr      x6, [x19, #0x10]
0x00962d7c: 2a 9a 05 94              bl       #0xac9624
0x00962d80: 69 00 00 37              tbnz     w9, #0, #0x962d8c
0x00962d84: 07 05 00 91              add      x7, x8, #1
0x00962d88: 02 00 00 14              b        #0x962d90
0x00962d8c: 67 26 40 f9              ldr      x7, [x19, #0x48]
0x00962d90: 88 e2 4b 39              ldrb     w8, [x20, #0x2f8]
0x00962d94: 69 1a 40 f9              ldr      x9, [x19, #0x30]
0x00962d98: ea 21 00 b0              adrp     x10, #0xd9f000
0x00962d9c: 0b 22 00 90              adrp     x11, #0xda2000
0x00962da0: 4a 3d 27 91              add      x10, x10, #0x9cf
0x00962da4: 6b 35 24 91              add      x11, x11, #0x90d
0x00962da8: 1f 01 00 71              cmp      w8, #0
0x00962dac: 60 21 00 f0              adrp     x0, #0xd91000
0x00962db0: 03 22 00 90              adrp     x3, #0xda2000
0x00962db4: 64 21 00 f0              adrp     x4, #0xd91000
0x00962db8: 68 01 8a 9a              csel     x8, x11, x10, eq
0x00962dbc: 2b a6 05 94              bl       #0xacc668
0x00962dc0: 84 74 23 91              add      x4, x4, #0x8dd
0x00962dc4: 21 0e 80 52              mov      w1, #0x71
0x00962dc8: 02 05 80 52              mov      w2, #0x28
0x00962dcc: e9 23 00 a9              stp      x9, x8, [sp]
0x00962dd0: 3c 8d e5 97              bl       #0x2c62c0                  ; -> _ZN13AmazingEngine8g_aeLogTEPKciiS1_S1_z@plt
0x00962dd4: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x00962dd8: 1f 09 00 f1              cmp      x8, #2
0x00962ddc: a0 01 00 54              b.eq     #0x962e10
0x00962de0: 1f 05 00 f1              cmp      x8, #1
0x00962de4: 80 02 00 54              b.eq     #0x962e34
0x00962de8: 88 03 00 b5              cbnz     x8, #0x962e58
0x00962dec: 88 82 41 f9              ldr      x8, [x20, #0x300]
0x00962df0: 60 06 42 a9              ldp      x0, x1, [x19, #0x20]
0x00962df4: 62 32 40 b9              ldr      w2, [x19, #0x30]
0x00962df8: 83 62 04 91              add      x3, x20, #0x118
0x00962dfc: 04 05 40 f9              ldr      x4, [x8, #8]
0x00962e00: f3 7b 42 a9              ldp      x19, x30, [sp, #0x20]
0x00962e04: f4 0b 40 f9              ldr      x20, [sp, #0x10]
0x00962e08: ff c3 00 91              add      sp, sp, #0x30
0x00962e0c: 80 00 1f d6              br       x4
0x00962e10: 88 82 41 f9              ldr      x8, [x20, #0x300]
0x00962e14: 80 8e 40 f9              ldr      x0, [x20, #0x118]
0x00962e18: 61 16 40 f9              ldr      x1, [x19, #0x28]
0x00962e1c: 62 32 40 b9              ldr      w2, [x19, #0x30]
0x00962e20: 03 25 40 f9              ldr      x3, [x8, #0x48]
0x00962e24: f3 7b 42 a9              ldp      x19, x30, [sp, #0x20]
0x00962e28: f4 0b 40 f9              ldr      x20, [sp, #0x10]
0x00962e2c: ff c3 00 91              add      sp, sp, #0x30
0x00962e30: 60 00 1f d6              br       x3
0x00962e34: 88 82 41 f9              ldr      x8, [x20, #0x300]
0x00962e38: 80 8e 40 f9              ldr      x0, [x20, #0x118]
0x00962e3c: 61 0a 42 a9              ldp      x1, x2, [x19, #0x20]
0x00962e40: 63 32 40 b9              ldr      w3, [x19, #0x30]
0x00962e44: 04 0d 40 f9              ldr      x4, [x8, #0x18]
0x00962e48: f3 7b 42 a9              ldp      x19, x30, [sp, #0x20]
0x00962e4c: f4 0b 40 f9              ldr      x20, [sp, #0x10]
0x00962e50: ff c3 00 91              add      sp, sp, #0x30
0x00962e54: 80 00 1f d6              br       x4
0x00962e58: f3 7b 42 a9              ldp      x19, x30, [sp, #0x20]
0x00962e5c: f4 0b 40 f9              ldr      x20, [sp, #0x10]
0x00962e60: c0 7c 80 12              mov      w0, #-0x3e7
0x00962e64: 4b a3 04 14              b        #0xa8bb90
0x00962e68: ff 83 06 d1              sub      sp, sp, #0x1a0
0x00962e6c: fc 63 16 a9              stp      x28, x24, [sp, #0x160]
0x00962e70: f7 5b 17 a9              stp      x23, x22, [sp, #0x170]
0x00962e74: f5 53 18 a9              stp      x21, x20, [sp, #0x180]
0x00962e78: f3 7b 19 a9              stp      x19, x30, [sp, #0x190]
0x00962e7c: 74 ae 04 94              bl       #0xa8e84c
0x00962e80: e8 af 00 f9              str      x8, [sp, #0x158]
0x00962e84: 08 8c 40 f9              ldr      x8, [x0, #0x118]
0x00962e88: 68 00 00 b4              cbz      x8, #0x962e94
0x00962e8c: 33 00 80 52              mov      w19, #1
0x00962e90: a7 00 00 14              b        #0x96312c
0x00962e94: 61 21 00 f0              adrp     x1, #0xd91000
0x00962e98: dd a2 05 94              bl       #0xacba0c
0x00962e9c: 21 04 1f 91              add      x1, x1, #0x7c1
0x00962ea0: 1d 39 05 94              bl       #0xab1314
0x00962ea4: e1 e3 03 91              add      x1, sp, #0xf8
0x00962ea8: 61 aa 04 94              bl       #0xa8d82c
0x00962eac: 28 00 80 52              mov      w8, #1
0x00962eb0: 08 00 00 f9              str      x8, [x0]
0x00962eb4: e0 e3 03 91              add      x0, sp, #0xf8
0x00962eb8: fa 15 e6 97              bl       #0x2e86a0
0x00962ebc: e0 e3 03 91              add      x0, sp, #0xf8
0x00962ec0: 78 e6 04 94              bl       #0xa9c8a0
0x00962ec4: 81 04 00 94              bl       #0x9640c8
0x00962ec8: 75 22 0b 91              add      x21, x19, #0x2c8
0x00962ecc: e1 e3 03 91              add      x1, sp, #0xf8
0x00962ed0: d3 f6 04 94              bl       #0xaa0a1c
0x00962ed4: 28 d3 04 94              bl       #0xa97b74
0x00962ed8: 19 70 06 94              bl       #0xafef3c
0x00962edc: 92 e9 05 94              bl       #0xadd524
0x00962ee0: e1 e3 03 91              add      x1, sp, #0xf8
0x00962ee4: 52 aa 04 94              bl       #0xa8d82c
0x00962ee8: 08 00 40 f9              ldr      x8, [x0]
0x00962eec: e9 0f 80 52              mov      w9, #0x7f
0x00962ef0: 09 04 a0 72              movk     w9, #0x20, lsl #16
0x00962ef4: e0 e3 03 91              add      x0, sp, #0xf8
0x00962ef8: 18 01 09 aa              orr      x24, x8, x9
0x00962efc: e9 15 e6 97              bl       #0x2e86a0
0x00962f00: e0 e3 03 91              add      x0, sp, #0xf8
0x00962f04: f4 bb 04 94              bl       #0xa91ed4
0x00962f08: c0 e2 00 91              add      x0, x22, #0x38
0x00962f0c: ff 63 11 a9              stp      xzr, x24, [sp, #0x110]
0x00962f10: ff 7f 12 a9              stp      xzr, xzr, [sp, #0x120]
0x00962f14: e6 10 e6 97              bl       #0x2e72ac
0x00962f18: f9 a5 05 94              bl       #0xacc6fc
0x00962f1c: ff a7 00 f9              str      xzr, [sp, #0x148]
0x00962f20: ff 43 05 39              strb     wzr, [sp, #0x150]
0x00962f24: e0 3b ff 97              bl       #0x931ea4
0x00962f28: f5 03 00 2a              mov      w21, w0
0x00962f2c: 40 01 00 34              cbz      w0, #0x962f54
0x00962f30: c1 d2 05 94              bl       #0xad7a34
0x00962f34: 64 21 00 f0              adrp     x4, #0xd91000
0x00962f38: cc a5 05 94              bl       #0xacc668
0x00962f3c: 84 b4 20 91              add      x4, x4, #0x82d
0x00962f40: c1 45 80 52              mov      w1, #0x22e
0x00962f44: fa f6 04 94              bl       #0xaa0b2c
0x00962f48: dd 49 05 94              bl       #0xab56bc
0x00962f4c: 93 00 80 52              mov      w19, #4
0x00962f50: 75 00 00 14              b        #0x963124
0x00962f54: 68 8e 40 f9              ldr      x8, [x19, #0x118]
0x00962f58: c8 fe ff b4              cbz      x8, #0x962f30
0x00962f5c: e0 63 02 91              add      x0, sp, #0x98
0x00962f60: 3a 4d 05 94              bl       #0xab6448
0x00962f64: f6 63 02 91              add      x22, sp, #0x98
0x00962f68: 58 04 00 94              bl       #0x9640c8
0x00962f6c: 75 82 0b 91              add      x21, x19, #0x2e0
0x00962f70: e1 63 02 91              add      x1, sp, #0x98
0x00962f74: aa f6 04 94              bl       #0xaa0a1c
0x00962f78: e0 63 02 91              add      x0, sp, #0x98
0x00962f7c: c9 15 e6 97              bl       #0x2e86a0
0x00962f80: e0 63 02 91              add      x0, sp, #0x98
0x00962f84: d4 bb 04 94              bl       #0xa91ed4
0x00962f88: a8 19 00 b0              adrp     x8, #0xc97000
0x00962f8c: 00 f9 c2 3d              ldr      q0, [x8, #0xbe0]
0x00962f90: c0 e2 00 91              add      x0, x22, #0x38
0x00962f94: ff 7f 0c a9              stp      xzr, xzr, [sp, #0xc0]
0x00962f98: e0 03 8b 3c              stur     q0, [sp, #0xb0]
0x00962f9c: c4 10 e6 97              bl       #0x2e72ac
0x00962fa0: e1 63 02 91              add      x1, sp, #0x98
0x00962fa4: e0 03 13 aa              mov      x0, x19
0x00962fa8: ff 77 00 f9              str      xzr, [sp, #0xe8]
0x00962fac: ff c3 03 39              strb     wzr, [sp, #0xf0]
0x00962fb0: bd 3b ff 97              bl       #0x931ea4
0x00962fb4: 60 01 00 34              cbz      w0, #0x962fe0
0x00962fb8: f5 03 00 2a              mov      w21, w0
0x00962fbc: 9e d2 05 94              bl       #0xad7a34
0x00962fc0: 64 21 00 f0              adrp     x4, #0xd91000
0x00962fc4: a9 a5 05 94              bl       #0xacc668
0x00962fc8: 84 48 21 91              add      x4, x4, #0x852
0x00962fcc: a1 47 80 52              mov      w1, #0x23d
0x00962fd0: d7 f6 04 94              bl       #0xaa0b2c
0x00962fd4: ba 49 05 94              bl       #0xab56bc
0x00962fd8: 13 00 81 52              mov      w19, #0x800
0x00962fdc: 50 00 00 14              b        #0x96311c
0x00962fe0: 61 21 00 f0              adrp     x1, #0xd91000
0x00962fe4: 21 1c 1b 91              add      x1, x1, #0x6c7
0x00962fe8: 3c f0 05 94              bl       #0xadf0d8
0x00962fec: e1 e3 00 91              add      x1, sp, #0x38
0x00962ff0: 0f aa 04 94              bl       #0xa8d82c
0x00962ff4: 16 00 40 f9              ldr      x22, [x0]
0x00962ff8: 69 aa 04 94              bl       #0xa8d99c
0x00962ffc: 76 05 00 b4              cbz      x22, #0x9630a8
0x00963000: e0 83 00 91              add      x0, sp, #0x20
0x00963004: 42 00 80 52              mov      w2, #2
0x00963008: e1 03 13 aa              mov      x1, x19
0x0096300c: 56 00 80 52              mov      w22, #2
0x00963010: 2e 04 00 94              bl       #0x9640c8
0x00963014: e0 e3 00 91              add      x0, sp, #0x38
0x00963018: ff 75 05 94              bl       #0xac0814
0x0096301c: a0 e2 00 91              add      x0, x21, #0x38
0x00963020: f6 7f 05 a9              stp      x22, xzr, [sp, #0x50]
0x00963024: ff 7f 06 a9              stp      xzr, xzr, [sp, #0x60]
0x00963028: a1 10 e6 97              bl       #0x2e72ac
0x0096302c: 71 cf 04 94              bl       #0xa96df0
0x00963030: 80 03 00 34              cbz      w0, #0x9630a0
0x00963034: f5 03 00 2a              mov      w21, w0
0x00963038: 7f d2 05 94              bl       #0xad7a34
0x0096303c: 64 21 00 d0              adrp     x4, #0xd91000
0x00963040: 8a a5 05 94              bl       #0xacc668
0x00963044: 84 e0 21 91              add      x4, x4, #0x878
0x00963048: 81 49 80 52              mov      w1, #0x24c
0x0096304c: b8 f6 04 94              bl       #0xaa0b2c
0x00963050: 76 21 00 d0              adrp     x22, #0xd91000
0x00963054: d6 1e 1b 91              add      x22, x22, #0x6c7
0x00963058: e0 23 00 91              add      x0, sp, #8
0x0096305c: ec aa 04 94              bl       #0xa8dc0c
0x00963060: e1 23 00 91              add      x1, sp, #8
0x00963064: f2 a9 04 94              bl       #0xa8d82c
0x00963068: 1f 00 00 f9              str      xzr, [x0]
0x0096306c: e0 23 00 91              add      x0, sp, #8
0x00963070: 8c 15 e6 97              bl       #0x2e86a0
0x00963074: e0 23 00 91              add      x0, sp, #8
0x00963078: e1 03 16 aa              mov      x1, x22
0x0096307c: 0a de 04 94              bl       #0xa9a8a4
0x00963080: e1 23 00 91              add      x1, sp, #8
0x00963084: cd bd 04 94              bl       #0xa927b8
0x00963088: 1f 00 00 39              strb     wzr, [x0]
0x0096308c: cd a3 04 94              bl       #0xa8bfc0
0x00963090: 8b 49 05 94              bl       #0xab56bc
0x00963094: ac f0 04 94              bl       #0xa9f344
0x00963098: cf a2 04 94              bl       #0xa8bbd4
0x0096309c: cf ff ff 17              b        #0x962fd8
0x009630a0: a9 f0 04 94              bl       #0xa9f344
0x009630a4: cc a2 04 94              bl       #0xa8bbd4
0x009630a8: 61 21 00 d0              adrp     x1, #0xd91000
0x009630ac: 21 24 1c 91              add      x1, x1, #0x709
0x009630b0: e0 e3 00 91              add      x0, sp, #0x38
0x009630b4: 4d da 04 94              bl       #0xa999e8
0x009630b8: e1 e3 00 91              add      x1, sp, #0x38
0x009630bc: b7 ca 04 94              bl       #0xa95b98
0x009630c0: 1f 00 00 39              strb     wzr, [x0]
0x009630c4: 36 aa 04 94              bl       #0xa8d99c
0x009630c8: 61 21 00 d0              adrp     x1, #0xd91000
0x009630cc: 21 74 1c 91              add      x1, x1, #0x71d
0x009630d0: 2d b0 04 94              bl       #0xa8f184
0x009630d4: e1 e3 00 91              add      x1, sp, #0x38
0x009630d8: b0 ca 04 94              bl       #0xa95b98
0x009630dc: 1f 00 00 39              strb     wzr, [x0]
0x009630e0: 2f aa 04 94              bl       #0xa8d99c
0x009630e4: 61 21 00 d0              adrp     x1, #0xd91000
0x009630e8: 21 98 22 91              add      x1, x1, #0x8a6
0x009630ec: 26 b0 04 94              bl       #0xa8f184
0x009630f0: e1 e3 00 91              add      x1, sp, #0x38
0x009630f4: a9 ca 04 94              bl       #0xa95b98
0x009630f8: 1f 00 00 39              strb     wzr, [x0]
0x009630fc: 28 aa 04 94              bl       #0xa8d99c
0x00963100: 4d d2 05 94              bl       #0xad7a34
0x00963104: 64 21 00 d0              adrp     x4, #0xd91000
0x00963108: 58 a5 05 94              bl       #0xacc668
0x0096310c: 84 04 23 91              add      x4, x4, #0x8c1
0x00963110: 01 4b 80 52              mov      w1, #0x258
0x00963114: 08 b4 04 94              bl       #0xa90134
0x00963118: 33 00 80 52              mov      w19, #1
0x0096311c: e0 63 02 91              add      x0, sp, #0x98
0x00963120: c2 8e f3 97              bl       #0x646c28
0x00963124: e0 e3 03 91              add      x0, sp, #0xf8
0x00963128: c0 8e f3 97              bl       #0x646c28
0x0096312c: e8 16 40 f9              ldr      x8, [x23, #0x28]
0x00963130: f3 ea 04 94              bl       #0xa9dcfc
0x00963134: e1 00 00 54              b.ne     #0x963150
0x00963138: e0 03 13 2a              mov      w0, w19
0x0096313c: f3 7b 59 a9              ldp      x19, x30, [sp, #0x190]
0x00963140: f5 53 58 a9              ldp      x21, x20, [sp, #0x180]
0x00963144: f7 5b 57 a9              ldp      x23, x22, [sp, #0x170]
0x00963148: fc 63 56 a9              ldp      x28, x24, [sp, #0x160]
0x0096314c: 48 d6 04 14              b        #0xa98a6c
0x00963150: b8 88 e5 97              bl       #0x2c5430                  ; -> __stack_chk_fail@plt
0x00963154: f3 7b bf a9              stp      x19, x30, [sp, #-0x10]!
0x00963158: 46 e6 04 94              bl       #0xa9ca70
0x0096315c: a0 00 00 b4              cbz      x0, #0x963170
0x00963160: 68 82 41 f9              ldr      x8, [x19, #0x300]
0x00963164: 68 00 00 b4              cbz      x8, #0x963170
0x00963168: 1f b6 04 94              bl       #0xa909e4
0x0096316c: 7f 8e 00 f9              str      xzr, [x19, #0x118]
0x00963170: 20 00 80 52              mov      w0, #1
0x00963174: f3 7b c1 a8              ldp      x19, x30, [sp], #0x10
0x00963178: c0 03 5f d6              ret      
0x0096317c: ff 83 03 d1              sub      sp, sp, #0xe0
0x00963180: f7 5b 0b a9              stp      x23, x22, [sp, #0xb0]
0x00963184: f5 53 0c a9              stp      x21, x20, [sp, #0xc0]
0x00963188: f3 7b 0d a9              stp      x19, x30, [sp, #0xd0]
0x0096318c: 54 d0 3b d5              mrs      x20, tpidr_el0
0x00963190: 88 16 40 f9              ldr      x8, [x20, #0x28]
0x00963194: e8 57 00 f9              str      x8, [sp, #0xa8]
0x00963198: 08 8c 40 f9              ldr      x8, [x0, #0x118]
0x0096319c: 48 01 00 b4              cbz      x8, #0x9631c4
0x009631a0: c8 49 00 90              adrp     x8, #0x129b000
0x009631a4: 08 e1 10 91              add      x8, x8, #0x438
0x009631a8: 08 fd df 08              ldarb    w8, [x8]
0x009631ac: 08 03 00 36              tbz      w8, #0, #0x96320c
0x009631b0: c8 49 00 90              adrp     x8, #0x129b000
0x009631b4: 01 21 42 f9              ldr      x1, [x8, #0x440]
0x009631b8: d9 3c ff 97              bl       #0x93251c
0x009631bc: 20 00 80 52              mov      w0, #1
0x009631c0: 0c 00 00 14              b        #0x9631f0
0x009631c4: 60 21 00 d0              adrp     x0, #0xd91000
0x009631c8: e3 21 00 f0              adrp     x3, #0xda2000
0x009631cc: e4 21 00 f0              adrp     x4, #0xda2000
0x009631d0: a5 21 00 90              adrp     x5, #0xd97000
0x009631d4: 00 c8 1c 91              add      x0, x0, #0x732
0x009631d8: a2 ae 04 94              bl       #0xa8ec60
0x009631dc: 01 4d 80 52              mov      w1, #0x268
0x009631e0: 42 01 80 52              mov      w2, #0xa
0x009631e4: 07 4d 80 52              mov      w7, #0x268
0x009631e8: 8e a1 04 94              bl       #0xa8b820
0x009631ec: 80 00 80 52              mov      w0, #4
0x009631f0: 88 16 40 f9              ldr      x8, [x20, #0x28]
0x009631f4: fc b0 04 94              bl       #0xa8f5e4
0x009631f8: 81 06 00 54              b.ne     #0x9632c8
0x009631fc: f3 7b 4d a9              ldp      x19, x30, [sp, #0xd0]
0x00963200: f5 53 4c a9              ldp      x21, x20, [sp, #0xc0]
0x00963204: f7 5b 4b a9              ldp      x23, x22, [sp, #0xb0]
0x00963208: 49 ad 04 14              b        #0xa8e72c
0x0096320c: d5 49 00 90              adrp     x21, #0x129b000
0x00963210: b5 e2 10 91              add      x21, x21, #0x438
0x00963214: f3 03 00 aa              mov      x19, x0
0x00963218: 55 9f 05 94              bl       #0xacaf6c
0x0096321c: e8 03 00 2a              mov      w8, w0
0x00963220: e0 03 13 aa              mov      x0, x19
0x00963224: 68 fc ff 34              cbz      w8, #0x9631b0
0x00963228: 00 05 80 52              mov      w0, #0x28
0x0096322c: 78 95 06 94              bl       #0xb0880c
0x00963230: 62 21 00 d0              adrp     x2, #0xd91000
0x00963234: f4 03 00 aa              mov      x20, x0
0x00963238: 42 f0 1b 91              add      x2, x2, #0x6fc
0x0096323c: e0 23 00 91              add      x0, sp, #8
0x00963240: 41 00 80 52              mov      w1, #2
0x00963244: f6 23 00 91              add      x22, sp, #8
0x00963248: 9c 45 f3 97              bl       #0x6348b8
0x0096324c: 62 21 00 d0              adrp     x2, #0xd91000
0x00963250: c0 82 00 91              add      x0, x22, #0x20
0x00963254: 42 60 20 91              add      x2, x2, #0x818
0x00963258: 21 00 80 52              mov      w1, #1
0x0096325c: 97 45 f3 97              bl       #0x6348b8
0x00963260: 62 21 00 d0              adrp     x2, #0xd91000
0x00963264: c0 02 01 91              add      x0, x22, #0x40
0x00963268: 42 04 20 91              add      x2, x2, #0x801
0x0096326c: 81 00 80 52              mov      w1, #4
0x00963270: 92 45 f3 97              bl       #0x6348b8
0x00963274: 62 21 00 d0              adrp     x2, #0xd91000
0x00963278: c0 82 01 91              add      x0, x22, #0x60
0x0096327c: 42 a4 1f 91              add      x2, x2, #0x7e9
0x00963280: a1 00 80 52              mov      w1, #5
0x00963284: 8d 45 f3 97              bl       #0x6348b8
0x00963288: 62 21 00 d0              adrp     x2, #0xd91000
0x0096328c: c0 02 02 91              add      x0, x22, #0x80
0x00963290: 42 48 1f 91              add      x2, x2, #0x7d2
0x00963294: 21 01 80 52              mov      w1, #9
0x00963298: 88 45 f3 97              bl       #0x6348b8
0x0096329c: 88 5a 06 94              bl       #0xaf9cbc
0x009632a0: 17 11 80 52              mov      w23, #0x88
0x009632a4: c0 02 17 8b              add      x0, x22, x23
0x009632a8: fe 14 e6 97              bl       #0x2e86a0
0x009632ac: f7 82 00 d1              sub      x23, x23, #0x20
0x009632b0: ff 62 00 b1              cmn      x23, #0x18
0x009632b4: 81 ff ff 54              b.ne     #0x9632a4
0x009632b8: 5f 9f 05 94              bl       #0xacb034
0x009632bc: f4 03 40 f9              ldr      x20, [sp]
0x009632c0: e0 03 13 aa              mov      x0, x19
0x009632c4: bb ff ff 17              b        #0x9631b0
0x009632c8: 5a 88 e5 97              bl       #0x2c5430                  ; -> __stack_chk_fail@plt
0x009632cc: ec 0f 17 fc              str      d12, [sp, #-0x90]!
0x009632d0: eb 2b 01 6d              stp      d11, d10, [sp, #0x10]
0x009632d4: e9 23 02 6d              stp      d9, d8, [sp, #0x20]
0x009632d8: e2 03 1e aa              mov      x2, x30
0x009632dc: cf a6 04 94              bl       #0xa8ce18
0x009632e0: fe 03 02 aa              mov      x30, x2
0x009632e4: fd 7b 08 a9              stp      x29, x30, [sp, #0x80]
0x009632e8: ff c3 19 d1              sub      sp, sp, #0x670
0x009632ec: e1 b5 04 94              bl       #0xa90a70
0x009632f0: e8 37 03 f9              str      x8, [sp, #0x668]
0x009632f4: 08 8c 40 f9              ldr      x8, [x0, #0x118]
0x009632f8: 68 03 00 b4              cbz      x8, #0x963364
0x009632fc: f3 03 00 aa              mov      x19, x0
