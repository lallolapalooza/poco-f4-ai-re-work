; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/ttlibs/libeffect.so
; target  : address 0xad7c80
; start   : 0xad7c80
; size    : 0x600
; bytes   : 0x600   instructions: 384
; generated: 2026-10-23T23:49:30

0x00ad7c80: e0 07 40 91              add      x0, sp, #1, lsl #12
0x00ad7c84: e1 07 40 91              add      x1, sp, #1, lsl #12
0x00ad7c88: 08 e1 1b 91              add      x8, x8, #0x6f8
0x00ad7c8c: 00 40 3c 91              add      x0, x0, #0xf10
0x00ad7c90: 21 40 3d 91              add      x1, x1, #0xf50
0x00ad7c94: c0 03 5f d6              ret      
0x00ad7c98: 88 3e 40 f9              ldr      x8, [x20, #0x78]
0x00ad7c9c: e0 23 00 91              add      x0, sp, #8
0x00ad7ca0: 08 3d 40 f9              ldr      x8, [x8, #0x78]
0x00ad7ca4: 01 e1 00 91              add      x1, x8, #0x38
0x00ad7ca8: b6 15 e1 17              b        #0x31d380
0x00ad7cac: 88 3e 40 f9              ldr      x8, [x20, #0x78]
0x00ad7cb0: e0 43 01 91              add      x0, sp, #0x50
0x00ad7cb4: e1 43 00 91              add      x1, sp, #0x10
0x00ad7cb8: e8 0b 00 f9              str      x8, [sp, #0x10]
0x00ad7cbc: da 92 f4 17              b        #0x7fc824
0x00ad7cc0: 2c 00 80 52              mov      w12, #1
0x00ad7cc4: 08 01 00 32              orr      w8, w8, #1
0x00ad7cc8: 2a 09 00 b9              str      w10, [x9, #8]
0x00ad7ccc: 2b 01 00 f9              str      x11, [x9]
0x00ad7cd0: 6c 22 02 39              strb     w12, [x19, #0x88]
0x00ad7cd4: 68 c2 04 39              strb     w8, [x19, #0x130]
0x00ad7cd8: c0 03 5f d6              ret      
0x00ad7cdc: 01 7d 80 52              mov      w1, #0x3e8
0x00ad7ce0: e3 03 13 aa              mov      x3, x19
0x00ad7ce4: c6 f9 f2 17              b        #0x7963fc
0x00ad7ce8: 81 15 00 b0              adrp     x1, #0xd88000
0x00ad7cec: 21 e0 1a 91              add      x1, x1, #0x6b8
0x00ad7cf0: c0 03 5f d6              ret      
0x00ad7cf4: 00 d4 21 0e              fadd     v0.2s, v0.2s, v1.2s
0x00ad7cf8: 00 05 00 fd              str      d0, [x8, #8]
0x00ad7cfc: 69 02 40 f9              ldr      x9, [x19]
0x00ad7d00: 20 41 40 fd              ldr      d0, [x9, #0x80]
0x00ad7d04: 21 a1 42 fd              ldr      d1, [x9, #0x540]
0x00ad7d08: 00 dc 2b 2e              fmul     v0.2s, v0.2s, v11.2s
0x00ad7d0c: c0 03 5f d6              ret      
0x00ad7d10: 00 d4 21 0e              fadd     v0.2s, v0.2s, v1.2s
0x00ad7d14: 00 09 00 fd              str      d0, [x8, #0x10]
0x00ad7d18: 69 02 40 f9              ldr      x9, [x19]
0x00ad7d1c: 20 51 40 fd              ldr      d0, [x9, #0xa0]
0x00ad7d20: 21 b5 42 fd              ldr      d1, [x9, #0x568]
0x00ad7d24: 00 dc 2b 2e              fmul     v0.2s, v0.2s, v11.2s
0x00ad7d28: c0 03 5f d6              ret      
0x00ad7d2c: 0a 2d 41 a9              ldp      x10, x11, [x8, #0x10]
0x00ad7d30: 28 25 41 a9              ldp      x8, x9, [x9, #0x10]
0x00ad7d34: 6a 01 0a cb              sub      x10, x11, x10
0x00ad7d38: 4a fd 42 d3              lsr      x10, x10, #2
0x00ad7d3c: 29 01 08 cb              sub      x9, x9, x8
0x00ad7d40: 29 fd 42 d3              lsr      x9, x9, #2
0x00ad7d44: c0 03 5f d6              ret      
0x00ad7d48: fe 0f 1f f8              str      x30, [sp, #-0x10]!
0x00ad7d4c: f0 42 f3 97              bl       #0x7a890c
0x00ad7d50: f7 03 00 aa              mov      x23, x0
0x00ad7d54: e0 03 16 aa              mov      x0, x22
0x00ad7d58: ed 42 f3 97              bl       #0x7a890c
0x00ad7d5c: f6 03 00 aa              mov      x22, x0
0x00ad7d60: e0 03 14 aa              mov      x0, x20
0x00ad7d64: ea 42 f3 97              bl       #0x7a890c
0x00ad7d68: e5 03 00 aa              mov      x5, x0
0x00ad7d6c: fe 07 41 f8              ldr      x30, [sp], #0x10
0x00ad7d70: c0 03 5f d6              ret      
0x00ad7d74: a0 16 00 f9              str      x0, [x21, #0x28]
0x00ad7d78: e0 c3 00 91              add      x0, sp, #0x30
0x00ad7d7c: bf 62 00 79              strh     wzr, [x21, #0x30]
0x00ad7d80: f3 c3 00 91              add      x19, sp, #0x30
0x00ad7d84: 86 2e e9 17              b        #0x52379c
0x00ad7d88: 00 9c 40 b9              ldr      w0, [x0, #0x9c]
0x00ad7d8c: c0 03 5f d6              ret      
0x00ad7d90: 01 64 00 b9              str      w1, [x0, #0x64]
0x00ad7d94: c0 03 5f d6              ret      
0x00ad7d98: e0 23 11 91              add      x0, sp, #0x448
0x00ad7d9c: e1 33 00 91              add      x1, sp, #0xc
0x00ad7da0: 18 d3 ee 17              b        #0x68ca00
0x00ad7da4: 09 64 84 52              mov      w9, #0x2320
0x00ad7da8: 00 01 09 8b              add      x0, x8, x9
0x00ad7dac: d0 e0 ee 17              b        #0x6900ec
0x00ad7db0: e0 07 07 2d              stp      s0, s1, [sp, #0x38]
0x00ad7db4: e2 0f 08 2d              stp      s2, s3, [sp, #0x40]
0x00ad7db8: 59 e6 f4 17              b        #0x81171c
0x00ad7dbc: 00 08 40 bd              ldr      s0, [x0, #8]
0x00ad7dc0: e8 af 40 f9              ldr      x8, [sp, #0x158]
0x00ad7dc4: 94 06 00 91              add      x20, x20, #1
0x00ad7dc8: 00 c0 22 1e              fcvt     d0, s0
0x00ad7dcc: 00 59 35 fc              str      d0, [x8, w21, uxtw #3]
0x00ad7dd0: b5 16 00 11              add      w21, w21, #5
0x00ad7dd4: c0 03 5f d6              ret      
0x00ad7dd8: 00 8c 40 bd              ldr      s0, [x0, #0x8c]
0x00ad7ddc: c0 03 5f d6              ret      
0x00ad7de0: 00 30 02 91              add      x0, x0, #0x8c
0x00ad7de4: c0 03 5f d6              ret      
0x00ad7de8: 00 9c 40 bd              ldr      s0, [x0, #0x9c]
0x00ad7dec: c0 03 5f d6              ret      
0x00ad7df0: c0 15 00 90              adrp     x0, #0xd8f000
0x00ad7df4: 43 16 00 f0              adrp     x3, #0xda2000
0x00ad7df8: c4 15 00 90              adrp     x4, #0xd8f000
0x00ad7dfc: 00 68 1b 91              add      x0, x0, #0x6da
0x00ad7e00: 63 3c 38 91              add      x3, x3, #0xe0f
0x00ad7e04: 84 9c 1d 91              add      x4, x4, #0x767
0x00ad7e08: c0 03 5f d6              ret      
0x00ad7e0c: c0 15 00 90              adrp     x0, #0xd8f000
0x00ad7e10: 43 16 00 f0              adrp     x3, #0xda2000
0x00ad7e14: c4 15 00 90              adrp     x4, #0xd8f000
0x00ad7e18: 00 68 1b 91              add      x0, x0, #0x6da
0x00ad7e1c: 63 3c 38 91              add      x3, x3, #0xe0f
0x00ad7e20: 84 98 1c 91              add      x4, x4, #0x726
0x00ad7e24: c0 03 5f d6              ret      
0x00ad7e28: fe 0f 1f f8              str      x30, [sp, #-0x10]!
0x00ad7e2c: 5c 2e e9 97              bl       #0x52379c
0x00ad7e30: ff 43 00 39              strb     wzr, [sp, #0x10]
0x00ad7e34: fe 07 41 f8              ldr      x30, [sp], #0x10
0x00ad7e38: e7 52 f2 17              b        #0x76c9d4
0x00ad7e3c: fe 0f 1f f8              str      x30, [sp, #-0x10]!
0x00ad7e40: 57 2e e9 97              bl       #0x52379c
0x00ad7e44: ff 43 00 39              strb     wzr, [sp, #0x10]
0x00ad7e48: fe 07 41 f8              ldr      x30, [sp], #0x10
0x00ad7e4c: 2e 78 f4 17              b        #0x7f5f04
0x00ad7e50: fe 0f 1f f8              str      x30, [sp, #-0x10]!
0x00ad7e54: 52 2e e9 97              bl       #0x52379c
0x00ad7e58: ff 43 00 39              strb     wzr, [sp, #0x10]
0x00ad7e5c: fe 07 41 f8              ldr      x30, [sp], #0x10
0x00ad7e60: 54 30 f4 17              b        #0x7e3fb0
0x00ad7e64: fe 0f 1f f8              str      x30, [sp, #-0x10]!
0x00ad7e68: 4d 2e e9 97              bl       #0x52379c
0x00ad7e6c: f7 03 01 39              strb     w23, [sp, #0x40]
0x00ad7e70: fe 07 41 f8              ldr      x30, [sp], #0x10
0x00ad7e74: 82 02 f3 17              b        #0x79887c
0x00ad7e78: fe 0f 1f f8              str      x30, [sp, #-0x10]!
0x00ad7e7c: 48 2e e9 97              bl       #0x52379c
0x00ad7e80: fd 43 01 39              strb     w29, [sp, #0x50]
0x00ad7e84: fe 07 41 f8              ldr      x30, [sp], #0x10
0x00ad7e88: 35 03 f3 17              b        #0x798b5c
0x00ad7e8c: fe 0f 1f f8              str      x30, [sp, #-0x10]!
0x00ad7e90: 43 2e e9 97              bl       #0x52379c
0x00ad7e94: f7 63 00 39              strb     w23, [sp, #0x18]
0x00ad7e98: fe 07 41 f8              ldr      x30, [sp], #0x10
0x00ad7e9c: 49 02 f3 17              b        #0x7987c0
0x00ad7ea0: fe 0f 1f f8              str      x30, [sp, #-0x10]!
0x00ad7ea4: 3e 2e e9 97              bl       #0x52379c
0x00ad7ea8: fb 03 01 39              strb     w27, [sp, #0x40]
0x00ad7eac: fe 07 41 f8              ldr      x30, [sp], #0x10
0x00ad7eb0: c0 03 5f d6              ret      
0x00ad7eb4: fe 0f 1f f8              str      x30, [sp, #-0x10]!
0x00ad7eb8: 39 2e e9 97              bl       #0x52379c
0x00ad7ebc: 28 00 80 52              mov      w8, #1
0x00ad7ec0: e8 43 00 39              strb     w8, [sp, #0x10]
0x00ad7ec4: fe 07 41 f8              ldr      x30, [sp], #0x10
0x00ad7ec8: e7 9e f2 17              b        #0x77fa64
0x00ad7ecc: fe 0f 1f f8              str      x30, [sp, #-0x10]!
0x00ad7ed0: 33 2e e9 97              bl       #0x52379c
0x00ad7ed4: ff 43 01 39              strb     wzr, [sp, #0x50]
0x00ad7ed8: fe 07 41 f8              ldr      x30, [sp], #0x10
0x00ad7edc: e2 0b f3 17              b        #0x79ae64
0x00ad7ee0: fe 0f 1f f8              str      x30, [sp, #-0x10]!
0x00ad7ee4: 2e 2e e9 97              bl       #0x52379c
0x00ad7ee8: fc 03 01 39              strb     w28, [sp, #0x40]
0x00ad7eec: fe 07 41 f8              ldr      x30, [sp], #0x10
0x00ad7ef0: c0 03 5f d6              ret      
0x00ad7ef4: fe 0f 1f f8              str      x30, [sp, #-0x10]!
0x00ad7ef8: 29 2e e9 97              bl       #0x52379c
0x00ad7efc: ff 03 01 39              strb     wzr, [sp, #0x40]
0x00ad7f00: fe 07 41 f8              ldr      x30, [sp], #0x10
0x00ad7f04: 26 5f f2 17              b        #0x76fb9c
0x00ad7f08: fe 0f 1f f8              str      x30, [sp, #-0x10]!
0x00ad7f0c: 24 2e e9 97              bl       #0x52379c
0x00ad7f10: ff 03 01 39              strb     wzr, [sp, #0x40]
0x00ad7f14: fe 07 41 f8              ldr      x30, [sp], #0x10
0x00ad7f18: 11 03 f3 17              b        #0x798b5c
0x00ad7f1c: fe 0f 1f f8              str      x30, [sp, #-0x10]!
0x00ad7f20: 1f 2e e9 97              bl       #0x52379c
0x00ad7f24: ff 03 01 39              strb     wzr, [sp, #0x40]
0x00ad7f28: fe 07 41 f8              ldr      x30, [sp], #0x10
0x00ad7f2c: 21 30 f4 17              b        #0x7e3fb0
0x00ad7f30: fe 0f 1f f8              str      x30, [sp, #-0x10]!
0x00ad7f34: 1a 2e e9 97              bl       #0x52379c
0x00ad7f38: ff 03 01 39              strb     wzr, [sp, #0x40]
0x00ad7f3c: fe 07 41 f8              ldr      x30, [sp], #0x10
0x00ad7f40: ff 20 f4 17              b        #0x7e033c
0x00ad7f44: e1 d3 08 91              add      x1, sp, #0x234
0x00ad7f48: 22 00 80 52              mov      w2, #1
0x00ad7f4c: e0 03 16 aa              mov      x0, x22
0x00ad7f50: f9 37 02 b9              str      w25, [sp, #0x234]
0x00ad7f54: 94 d2 e4 17              b        #0x40c9a4
0x00ad7f58: 6b 79 69 b8              ldr      w11, [x11, x9, lsl #2]
0x00ad7f5c: 29 05 00 91              add      x9, x9, #1
0x00ad7f60: c0 03 5f d6              ret      
0x00ad7f64: 2a 41 40 39              ldrb     w10, [x9, #0x10]
0x00ad7f68: 2b 11 40 f9              ldr      x11, [x9, #0x20]
0x00ad7f6c: 28 45 00 91              add      x8, x9, #0x11
0x00ad7f70: 5f 01 00 72              tst      w10, #1
0x00ad7f74: 1b 01 8b 9a              csel     x27, x8, x11, eq
0x00ad7f78: e8 83 01 91              add      x8, sp, #0x60
0x00ad7f7c: c0 03 5f d6              ret      
0x00ad7f80: 00 79 69 bc              ldr      s0, [x8, x9, lsl #2]
0x00ad7f84: f8 13 00 b9              str      w24, [sp, #0x10]
0x00ad7f88: 00 c0 22 1e              fcvt     d0, s0
0x00ad7f8c: e0 07 00 fd              str      d0, [sp, #8]
0x00ad7f90: b2 ee f2 17              b        #0x793a58
0x00ad7f94: e1 15 00 d0              adrp     x1, #0xd95000
0x00ad7f98: 21 00 09 91              add      x1, x1, #0x240
0x00ad7f9c: c0 03 5f d6              ret      
0x00ad7fa0: 42 23 09 9b              madd     x2, x26, x9, x8
0x00ad7fa4: e4 c3 0c 91              add      x4, sp, #0x330
0x00ad7fa8: e5 63 0c 91              add      x5, sp, #0x318
0x00ad7fac: e3 03 19 2a              mov      w3, w25
0x00ad7fb0: 5c b5 ee 17              b        #0x685520
0x00ad7fb4: 20 04 0c 5e              mov      s0, v1.s[1]
0x00ad7fb8: 23 90 81 5f              fmul     s3, s1, v1.s[0]
0x00ad7fbc: 04 90 a1 5f              fmul     s4, s0, v1.s[1]
0x00ad7fc0: 63 28 24 1e              fadd     s3, s3, s4
0x00ad7fc4: 42 08 22 1e              fmul     s2, s2, s2
0x00ad7fc8: 60 20 22 1e              fcmp     s3, s2
0x00ad7fcc: c0 03 5f d6              ret      
0x00ad7fd0: e0 c3 10 91              add      x0, sp, #0x430
0x00ad7fd4: df d0 01 14              b        #0xb4c350
0x00ad7fd8: 77 06 13 8b              add      x23, x19, x19, lsl #1
0x00ad7fdc: e0 83 13 91              add      x0, sp, #0x4e0
0x00ad7fe0: 08 79 77 f8              ldr      x8, [x8, x23, lsl #3]
0x00ad7fe4: 01 01 15 8b              add      x1, x8, x21
0x00ad7fe8: 91 85 e7 17              b        #0x4b962c
0x00ad7fec: e2 83 13 91              add      x2, sp, #0x4e0
0x00ad7ff0: a1 02 80 52              mov      w1, #0x15
0x00ad7ff4: f6 e3 04 b9              str      w22, [sp, #0x4e0]
0x00ad7ff8: 00 01 14 8b              add      x0, x8, x20
0x00ad7ffc: 54 b4 ee 17              b        #0x68514c
0x00ad8000: 84 94 05 91              add      x4, x4, #0x165
0x00ad8004: 23 00 80 52              mov      w3, #1
0x00ad8008: c0 03 5f d6              ret      
0x00ad800c: e4 d3 00 91              add      x4, sp, #0x34
0x00ad8010: 81 00 80 52              mov      w1, #4
0x00ad8014: c0 03 5f d6              ret      
0x00ad8018: a1 03 04 d1              sub      x1, x29, #0x100
0x00ad801c: e0 03 1c aa              mov      x0, x28
0x00ad8020: be a8 f0 17              b        #0x702318
0x00ad8024: 0d 79 ac b8              ldrsw    x13, [x8, x12, lsl #2]
0x00ad8028: 20 79 6c bc              ldr      s0, [x9, x12, lsl #2]
0x00ad802c: 8c 05 00 91              add      x12, x12, #1
0x00ad8030: 41 79 6d bc              ldr      s1, [x10, x13, lsl #2]
0x00ad8034: 00 85 00 1f              fmsub    s0, s8, s0, s1
0x00ad8038: 40 79 2d bc              str      s0, [x10, x13, lsl #2]
0x00ad803c: c0 03 5f d6              ret      
0x00ad8040: 09 79 2a b8              str      w9, [x8, x10, lsl #2]
0x00ad8044: a8 23 00 d1              sub      x8, x29, #8
0x00ad8048: 00 01 50 f8              ldur     x0, [x8, #-0x100]
0x00ad804c: bf 83 13 b8              stur     wzr, [x29, #-0xc8]
0x00ad8050: f8 9e fe 17              b        #0xa7fc30
0x00ad8054: e0 43 1a 91              add      x0, sp, #0x690
0x00ad8058: 92 41 e0 17              b        #0x2e86a0
0x00ad805c: 01 e1 00 91              add      x1, x8, #0x38
0x00ad8060: c8 14 e1 17              b        #0x31d380
0x00ad8064: 74 3c 00 90              adrp     x20, #0x1264000
0x00ad8068: 83 e2 41 f9              ldr      x3, [x20, #0x3c0]
0x00ad806c: c0 03 5f d6              ret      
0x00ad8070: f3 07 42 f8              ldr      x19, [sp], #0x20
0x00ad8074: 6f 41 e0 17              b        #0x2e8630
0x00ad8078: f3 07 42 f8              ldr      x19, [sp], #0x20
0x00ad807c: a9 cf 01 14              b        #0xb4bf20
0x00ad8080: e0 14 00 b0              adrp     x0, #0xd75000
0x00ad8084: 43 16 00 d0              adrp     x3, #0xda2000
0x00ad8088: e4 14 00 b0              adrp     x4, #0xd75000
0x00ad808c: 00 18 20 91              add      x0, x0, #0x806
0x00ad8090: 63 74 35 91              add      x3, x3, #0xd5d
0x00ad8094: 84 0c 21 91              add      x4, x4, #0x843
0x00ad8098: c0 03 5f d6              ret      
0x00ad809c: fe 0f 1f f8              str      x30, [sp, #-0x10]!
0x00ad80a0: e0 1d af 4e              mov      v0.16b, v15.16b
0x00ad80a4: 21 1d a9 4e              mov      v1.16b, v9.16b
0x00ad80a8: 09 00 ed 97              bl       #0x6180cc
0x00ad80ac: e1 83 03 2d              stp      s1, s0, [sp, #0x1c]
0x00ad80b0: a9 39 2b 1e              fsub     s9, s13, s11
0x00ad80b4: cf 39 2c 1e              fsub     s15, s14, s12
0x00ad80b8: fe 07 41 f8              ldr      x30, [sp], #0x10
0x00ad80bc: 55 bc df 17              b        #0x2c7210                  ; -> _ZN13AmazingEngine8Vector2f4zeroEv@plt
0x00ad80c0: e0 43 0c 91              add      x0, sp, #0x310
0x00ad80c4: a3 d0 01 14              b        #0xb4c350
0x00ad80c8: e0 43 0c 91              add      x0, sp, #0x310
0x00ad80cc: e1 63 05 91              add      x1, sp, #0x158
0x00ad80d0: c0 03 5f d6              ret      
0x00ad80d4: e0 a3 12 91              add      x0, sp, #0x4a8
0x00ad80d8: 9e d0 01 14              b        #0xb4c350
0x00ad80dc: e0 a3 09 91              add      x0, sp, #0x268
0x00ad80e0: 9c d0 01 14              b        #0xb4c350
0x00ad80e4: 89 32 40 b9              ldr      w9, [x20, #0x30]
0x00ad80e8: 69 32 00 b9              str      w9, [x19, #0x30]
0x00ad80ec: c0 03 5f d6              ret      
0x00ad80f0: e9 c3 40 39              ldrb     w9, [sp, #0x30]
0x00ad80f4: ea 23 40 f9              ldr      x10, [sp, #0x40]
0x00ad80f8: c0 03 5f d6              ret      
0x00ad80fc: 01 0b 80 52              mov      w1, #0x58
0x00ad8100: e0 03 16 aa              mov      x0, x22
0x00ad8104: 40 4f ed 17              b        #0x62be04
0x00ad8108: c0 14 00 d0              adrp     x0, #0xd72000
0x00ad810c: 43 16 00 d0              adrp     x3, #0xda2000
0x00ad8110: c4 14 00 d0              adrp     x4, #0xd72000
0x00ad8114: 00 4c 25 91              add      x0, x0, #0x953
0x00ad8118: 63 74 35 91              add      x3, x3, #0xd5d
0x00ad811c: 84 2c 26 91              add      x4, x4, #0x98b
0x00ad8120: c0 03 5f d6              ret      
0x00ad8124: 84 5c 14 91              add      x4, x4, #0x517
0x00ad8128: 23 00 80 52              mov      w3, #1
0x00ad812c: c0 03 5f d6              ret      
0x00ad8130: 00 20 25 1e              fcmp     s0, s5
0x00ad8134: a0 4c 20 1e              fcsel    s0, s5, s0, mi
0x00ad8138: c0 03 5f d6              ret      
0x00ad813c: 84 20 2c 91              add      x4, x4, #0xb08
0x00ad8140: 23 00 80 52              mov      w3, #1
0x00ad8144: c0 03 5f d6              ret      
0x00ad8148: 84 78 20 91              add      x4, x4, #0x81e
0x00ad814c: 23 00 80 52              mov      w3, #1
0x00ad8150: c0 03 5f d6              ret      
0x00ad8154: 15 3e 00 f0              adrp     x21, #0x129b000
0x00ad8158: b5 42 2e 91              add      x21, x21, #0xb90
0x00ad815c: c0 03 5f d6              ret      
0x00ad8160: e9 6f 41 f9              ldr      x9, [sp, #0x2d8]
0x00ad8164: 1f 01 09 eb              cmp      x8, x9
0x00ad8168: c0 03 5f d6              ret      
0x00ad816c: 08 29 20 1e              fadd     s8, s8, s0
0x00ad8170: f7 06 00 11              add      w23, w23, #1
0x00ad8174: c0 03 5f d6              ret      
0x00ad8178: e1 1f 41 f9              ldr      x1, [sp, #0x238]
0x00ad817c: e0 1b 41 f9              ldr      x0, [sp, #0x230]
0x00ad8180: a2 63 03 d1              sub      x2, x29, #0xd8
0x00ad8184: bf 83 12 b8              stur     wzr, [x29, #-0xd8]
0x00ad8188: 1a bd ef 17              b        #0x6c75f0
0x00ad818c: a0 15 00 b0              adrp     x0, #0xd8d000
0x00ad8190: 43 16 00 d0              adrp     x3, #0xda2000
0x00ad8194: a4 15 00 b0              adrp     x4, #0xd8d000
0x00ad8198: 00 60 26 91              add      x0, x0, #0x998
0x00ad819c: 63 3c 38 91              add      x3, x3, #0xe0f
0x00ad81a0: 84 e4 28 91              add      x4, x4, #0xa39
0x00ad81a4: c0 03 5f d6              ret      
0x00ad81a8: 44 1d aa 4e              mov      v4.16b, v10.16b
0x00ad81ac: 65 1d ab 4e              mov      v5.16b, v11.16b
0x00ad81b0: 62 1c a3 4e              mov      v2.16b, v3.16b
0x00ad81b4: 23 1d a9 4e              mov      v3.16b, v9.16b
0x00ad81b8: 50 e5 f4 17              b        #0x8116f8
0x00ad81bc: 44 1d aa 4e              mov      v4.16b, v10.16b
0x00ad81c0: 25 1d a9 4e              mov      v5.16b, v9.16b
0x00ad81c4: c0 03 5f d6              ret      
0x00ad81c8: 28 21 15 9b              madd     x8, x9, x21, x8
0x00ad81cc: 09 01 40 f9              ldr      x9, [x8]
0x00ad81d0: 08 09 40 b9              ldr      w8, [x8, #8]
0x00ad81d4: e9 2b 00 f9              str      x9, [sp, #0x50]
0x00ad81d8: e8 5b 00 b9              str      w8, [sp, #0x58]
0x00ad81dc: e0 c3 c4 3c              ldur     q0, [sp, #0x4c]
0x00ad81e0: c0 03 5f d6              ret      
0x00ad81e4: e3 63 0f 91              add      x3, sp, #0x3d8
0x00ad81e8: e0 03 16 aa              mov      x0, x22
0x00ad81ec: 68 6e 02 f9              str      x8, [x19, #0x4d8]
0x00ad81f0: ff ef 01 f9              str      xzr, [sp, #0x3d8]
0x00ad81f4: af cf e8 17              b        #0x50c0b0
0x00ad81f8: ff 97 01 f9              str      xzr, [sp, #0x328]
0x00ad81fc: ff 93 01 f9              str      xzr, [sp, #0x320]
0x00ad8200: ff 8f 01 f9              str      xzr, [sp, #0x318]
0x00ad8204: ff 8b 01 f9              str      xzr, [sp, #0x310]
0x00ad8208: ff 87 01 f9              str      xzr, [sp, #0x308]
0x00ad820c: ff 83 01 f9              str      xzr, [sp, #0x300]
0x00ad8210: c0 03 5f d6              ret      
0x00ad8214: e1 14 00 f0              adrp     x1, #0xd77000
0x00ad8218: fc 03 00 aa              mov      x28, x0
0x00ad821c: e0 83 07 91              add      x0, sp, #0x1e0
0x00ad8220: 21 24 2f 91              add      x1, x1, #0xbc9
0x00ad8224: 8d 44 e0 17              b        #0x2e9458
0x00ad8228: ae 11 40 f9              ldr      x14, [x13, #0x20]
0x00ad822c: 6b 05 00 11              add      w11, w11, #1
0x00ad8230: ce 31 40 b9              ldr      w14, [x14, #0x30]
0x00ad8234: 0e 45 00 b8              str      w14, [x8], #4
0x00ad8238: ad 0d 40 f9              ldr      x13, [x13, #0x18]
0x00ad823c: bf 01 0c eb              cmp      x13, x12
0x00ad8240: c0 03 5f d6              ret      
0x00ad8244: e0 07 09 2d              stp      s0, s1, [sp, #0x48]
0x00ad8248: e2 53 00 bd              str      s2, [sp, #0x50]
0x00ad824c: ed bc df 17              b        #0x2c7600                  ; -> _ZN13AmazingEngine20FromToQuaternionSafeERKNS_8Vector3fES2_@plt
0x00ad8250: 09 12 80 52              mov      w9, #0x90
0x00ad8254: 2a 1f 83 52              mov      w10, #0x18f9
0x00ad8258: 09 09 c9 9a              udiv     x9, x8, x9
0x00ad825c: 2a 00 a0 72              movk     w10, #1, lsl #16
0x00ad8260: 1f 01 0a eb              cmp      x8, x10
0x00ad8264: 37 4d 1a 9b              madd     x23, x9, x26, x19
0x00ad8268: c0 03 5f d6              ret      
0x00ad826c: 75 12 00 91              add      x21, x19, #4
0x00ad8270: 61 0a 00 91              add      x1, x19, #2
0x00ad8274: e0 03 13 aa              mov      x0, x19
0x00ad8278: e2 03 15 aa              mov      x2, x21
0x00ad827c: a6 55 ee 17              b        #0x66d914
