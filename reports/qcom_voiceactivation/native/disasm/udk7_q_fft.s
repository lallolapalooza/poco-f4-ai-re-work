; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libqvasml.so
; target  : address 0x5e9cc
; start   : 0x5e9cc
; size    : 0xc50
; bytes   : 0xc50   instructions: 788
; generated: 2026-10-23T23:30:05

0x0005e9cc: 3f 23 03 d5              paciasp  
0x0005e9d0: fd 7b ba a9              stp      x29, x30, [sp, #-0x60]!
0x0005e9d4: fc 6f 01 a9              stp      x28, x27, [sp, #0x10]
0x0005e9d8: fa 67 02 a9              stp      x26, x25, [sp, #0x20]
0x0005e9dc: f8 5f 03 a9              stp      x24, x23, [sp, #0x30]
0x0005e9e0: f6 57 04 a9              stp      x22, x21, [sp, #0x40]
0x0005e9e4: f4 4f 05 a9              stp      x20, x19, [sp, #0x50]
0x0005e9e8: fd 03 00 91              mov      x29, sp
0x0005e9ec: ff 83 07 d1              sub      sp, sp, #0x1e0
0x0005e9f0: 3f 08 00 71              cmp      w1, #2
0x0005e9f4: 8b 44 00 54              b.lt     #0x5f284
0x0005e9f8: 0a 00 80 12              mov      w10, #-1
0x0005e9fc: e8 03 01 2a              mov      w8, w1
0x0005ea00: 09 7d 01 53              lsr      w9, w8, #1
0x0005ea04: 1f 0d 00 71              cmp      w8, #3
0x0005ea08: 4a 05 00 11              add      w10, w10, #1
0x0005ea0c: e8 03 09 2a              mov      w8, w9
0x0005ea10: 88 ff ff 54              b.hi     #0x5ea00
0x0005ea14: 3f 00 00 71              cmp      w1, #0
0x0005ea18: e0 6b 00 f9              str      x0, [sp, #0xd0]
0x0005ea1c: 28 a4 81 1a              cinc     w8, w1, lt
0x0005ea20: e2 53 00 f9              str      x2, [sp, #0xa0]
0x0005ea24: 08 7d 01 13              asr      w8, w8, #1
0x0005ea28: e8 03 00 f9              str      x8, [sp]
0x0005ea2c: aa 39 00 34              cbz      w10, #0x5f160
0x0005ea30: 28 7c 7d d3              ubfiz    x8, x1, #3, #0x20
0x0005ea34: eb 03 01 2a              mov      w11, w1
0x0005ea38: 49 00 08 8b              add      x9, x2, x8
0x0005ea3c: 08 00 08 8b              add      x8, x0, x8
0x0005ea40: 1f 01 02 eb              cmp      x8, x2
0x0005ea44: 48 80 00 91              add      x8, x2, #0x20
0x0005ea48: 20 81 40 fa              ccmp     x9, x0, #0, hi
0x0005ea4c: 69 71 7d 92              and      x9, x11, #0xfffffff8
0x0005ea50: 24 00 22 1e              scvtf    s4, w1
0x0005ea54: 2d 00 80 52              mov      w13, #1
0x0005ea58: e8 0b 00 f9              str      x8, [sp, #0x10]
0x0005ea5c: 08 80 00 91              add      x8, x0, #0x20
0x0005ea60: e9 2f 02 a9              stp      x9, x11, [sp, #0x20]
0x0005ea64: 09 10 00 91              add      x9, x0, #4
0x0005ea68: 2b 00 80 52              mov      w11, #1
0x0005ea6c: e1 1f 00 f9              str      x1, [sp, #0x38]
0x0005ea70: e8 07 00 f9              str      x8, [sp, #8]
0x0005ea74: 48 10 00 91              add      x8, x2, #4
0x0005ea78: e9 6f 00 f9              str      x9, [sp, #0xd8]
0x0005ea7c: e9 97 9f 1a              cset     w9, hi
0x0005ea80: 85 04 04 4e              dup      v5.4s, v4.s[0]
0x0005ea84: a4 03 98 3c              stur     q4, [x29, #-0x80]
0x0005ea88: e8 23 00 f9              str      x8, [sp, #0x40]
0x0005ea8c: c8 fd ff d0              adrp     x8, #0x18000
0x0005ea90: e9 1f 00 b9              str      w9, [sp, #0x1c]
0x0005ea94: ea 37 00 b9              str      w10, [sp, #0x34]
0x0005ea98: 00 8d c1 3d              ldr      q0, [x8, #0x630]
0x0005ea9c: a5 03 93 3c              stur     q5, [x29, #-0xd0]
0x0005eaa0: e8 03 40 f9              ldr      x8, [sp]
0x0005eaa4: e0 17 80 3d              str      q0, [sp, #0x50]
0x0005eaa8: e9 03 08 2a              mov      w9, w8
0x0005eaac: e8 03 01 2a              mov      w8, w1
0x0005eab0: 10 00 00 14              b        #0x5eaf0                   ; -> udk7_q_fft+0x124
0x0005eab4: ac 03 52 f8              ldur     x12, [x29, #-0xe0]
0x0005eab8: ed 2f 49 29              ldp      w13, w11, [sp, #0x48]
0x0005eabc: ea 37 40 b9              ldr      w10, [sp, #0x34]
0x0005eac0: 9f 01 00 71              cmp      w12, #0
0x0005eac4: e0 6b 40 f9              ldr      x0, [sp, #0xd0]
0x0005eac8: 89 a5 8c 1a              cinc     w9, w12, lt
0x0005eacc: e1 1f 40 f9              ldr      x1, [sp, #0x38]
0x0005ead0: 68 05 00 11              add      w8, w11, #1
0x0005ead4: 5f 01 0b 6b              cmp      w10, w11
0x0005ead8: 29 7d 01 13              asr      w9, w9, #1
0x0005eadc: ad 79 1f 53              lsl      w13, w13, #1
0x0005eae0: eb 03 08 2a              mov      w11, w8
0x0005eae4: e8 03 0c 2a              mov      w8, w12
0x0005eae8: e2 53 40 f9              ldr      x2, [sp, #0xa0]
0x0005eaec: a0 33 00 54              b.eq     #0x5f160
0x0005eaf0: 3f 04 00 71              cmp      w1, #1
0x0005eaf4: eb 4f 00 b9              str      w11, [sp, #0x4c]
0x0005eaf8: a9 03 12 f8              stur     x9, [x29, #-0xe0]
0x0005eafc: 0b 04 00 54              b.lt     #0x5eb7c
0x0005eb00: e9 1f 40 b9              ldr      w9, [sp, #0x1c]
0x0005eb04: 3f 20 00 71              cmp      w1, #8
0x0005eb08: 29 25 9f 1a              csinc    w9, w9, wzr, hs
0x0005eb0c: 69 00 00 36              tbz      w9, #0, #0x5eb18
0x0005eb10: eb 03 1f aa              mov      x11, xzr
0x0005eb14: 0d 00 00 14              b        #0x5eb48                   ; -> udk7_q_fft+0x17c
0x0005eb18: ea af 40 a9              ldp      x10, x11, [sp, #8]
0x0005eb1c: e9 13 40 f9              ldr      x9, [sp, #0x20]
0x0005eb20: 40 05 7f ad              ldp      q0, q1, [x10, #-0x20]
0x0005eb24: 29 21 00 f1              subs     x9, x9, #8
0x0005eb28: 42 0d c2 ac              ldp      q2, q3, [x10], #0x40
0x0005eb2c: 60 05 3f ad              stp      q0, q1, [x11, #-0x20]
0x0005eb30: 62 0d 82 ac              stp      q2, q3, [x11], #0x40
0x0005eb34: 61 ff ff 54              b.ne     #0x5eb20
0x0005eb38: ea 27 42 a9              ldp      x10, x9, [sp, #0x20]
0x0005eb3c: eb 03 0a aa              mov      x11, x10
0x0005eb40: 5f 01 09 eb              cmp      x10, x9
0x0005eb44: c0 01 00 54              b.eq     #0x5eb7c
0x0005eb48: e9 23 40 f9              ldr      x9, [sp, #0x40]
0x0005eb4c: 6a f1 7d d3              lsl      x10, x11, #3
0x0005eb50: ec 6f 40 f9              ldr      x12, [sp, #0xd8]
0x0005eb54: 29 01 0a 8b              add      x9, x9, x10
0x0005eb58: 8a 01 0a 8b              add      x10, x12, x10
0x0005eb5c: ec 17 40 f9              ldr      x12, [sp, #0x28]
0x0005eb60: 8b 01 0b cb              sub      x11, x12, x11
0x0005eb64: 40 c1 5f bc              ldur     s0, [x10, #-4]
0x0005eb68: 6b 05 00 f1              subs     x11, x11, #1
0x0005eb6c: 20 c1 1f bc              stur     s0, [x9, #-4]
0x0005eb70: 40 85 40 bc              ldr      s0, [x10], #8
0x0005eb74: 20 85 00 bc              str      s0, [x9], #8
0x0005eb78: 61 ff ff 54              b.ne     #0x5eb64
0x0005eb7c: 69 fb 81 52              mov      w9, #0xfdb
0x0005eb80: bf 05 00 71              cmp      w13, #1
0x0005eb84: 29 19 a8 72              movk     w9, #0x40c9, lsl #16
0x0005eb88: a0 01 22 1e              scvtf    s0, w13
0x0005eb8c: ed 4b 00 b9              str      w13, [sp, #0x48]
0x0005eb90: ac 85 9f 1a              csinc    w12, w13, wzr, hi
0x0005eb94: 8f 00 80 52              mov      w15, #4
0x0005eb98: 08 7d 7d 93              sbfiz    x8, x8, #3, #0x20
0x0005eb9c: 21 01 27 1e              fmov     s1, w9
0x0005eba0: a9 03 52 f8              ldur     x9, [x29, #-0xe0]
0x0005eba4: ec 73 00 f9              str      x12, [sp, #0xe0]
0x0005eba8: f7 03 1f aa              mov      x23, xzr
0x0005ebac: f3 6f 40 f9              ldr      x19, [sp, #0xd8]
0x0005ebb0: f9 03 02 aa              mov      x25, x2
0x0005ebb4: ab 7d 29 9b              smull    x11, w13, w9
0x0005ebb8: 2a 7d 40 93              sxtw     x10, w9
0x0005ebbc: f6 03 09 2a              mov      w22, w9
0x0005ebc0: 2e 7d 7d 93              sbfiz    x14, x9, #3, #0x20
0x0005ebc4: 6d f1 7d d3              lsl      x13, x11, #3
0x0005ebc8: 29 7d 7d d3              ubfiz    x9, x9, #3, #0x20
0x0005ebcc: ea a3 0e a9              stp      x10, x8, [sp, #0xe8]
0x0005ebd0: c8 01 09 8b              add      x8, x14, x9
0x0005ebd4: ab 83 14 f8              stur     x11, [x29, #-0xb8]
0x0005ebd8: 6f f1 7d b3              bfi      x15, x11, #3, #0x3d
0x0005ebdc: ed 5f 00 f9              str      x13, [sp, #0xb8]
0x0005ebe0: ad 01 09 8b              add      x13, x13, x9
0x0005ebe4: ab 11 00 d1              sub      x11, x13, #4
0x0005ebe8: e9 4f 00 f9              str      x9, [sp, #0x98]
0x0005ebec: cc 06 00 d1              sub      x12, x22, #1
0x0005ebf0: 01 08 21 1e              fmul     s1, s0, s1
0x0005ebf4: a0 83 12 f8              stur     x0, [x29, #-0xd8]
0x0005ebf8: eb b7 08 a9              stp      x11, x13, [sp, #0x88]
0x0005ebfc: 2b 11 00 d1              sub      x11, x9, #4
0x0005ec00: 89 00 80 52              mov      w9, #4
0x0005ec04: ec bf 0a a9              stp      x12, x15, [sp, #0xa8]
0x0005ec08: 49 f1 7d b3              bfi      x9, x10, #3, #0x3d
0x0005ec0c: a1 03 99 3c              stur     q1, [x29, #-0x70]
0x0005ec10: e8 af 07 a9              stp      x8, x11, [sp, #0x78]
0x0005ec14: 08 11 00 d1              sub      x8, x8, #4
0x0005ec18: ae 83 11 f8              stur     x14, [x29, #-0xe8]
0x0005ec1c: e8 a7 06 a9              stp      x8, x9, [sp, #0x68]
0x0005ec20: 88 f1 7d d3              lsl      x8, x12, #3
0x0005ec24: e8 67 00 f9              str      x8, [sp, #0xc8]
0x0005ec28: c8 76 7e 92              and      x8, x22, #0xfffffffc
0x0005ec2c: e8 33 00 f9              str      x8, [sp, #0x60]
0x0005ec30: e8 23 40 f9              ldr      x8, [sp, #0x40]
0x0005ec34: 08 01 0e 8b              add      x8, x8, x14
0x0005ec38: a8 03 14 f8              stur     x8, [x29, #-0xc0]
0x0005ec3c: 12 00 00 14              b        #0x5ec84                   ; -> udk7_q_fft+0x2b8
0x0005ec40: ae 83 51 f8              ldur     x14, [x29, #-0xe8]
0x0005ec44: f7 06 00 91              add      x23, x23, #1
0x0005ec48: a8 83 52 f8              ldur     x8, [x29, #-0xd8]
0x0005ec4c: a9 83 54 f8              ldur     x9, [x29, #-0xb8]
0x0005ec50: ea 7b 40 f9              ldr      x10, [sp, #0xf0]
0x0005ec54: 73 02 0e 8b              add      x19, x19, x14
0x0005ec58: 08 01 0e 8b              add      x8, x8, x14
0x0005ec5c: 39 03 0a 8b              add      x25, x25, x10
0x0005ec60: a8 83 12 f8              stur     x8, [x29, #-0xd8]
0x0005ec64: e8 77 40 f9              ldr      x8, [sp, #0xe8]
0x0005ec68: 29 01 08 8b              add      x9, x9, x8
0x0005ec6c: a8 03 54 f8              ldur     x8, [x29, #-0xc0]
0x0005ec70: 08 01 0a 8b              add      x8, x8, x10
0x0005ec74: a8 27 34 a9              stp      x8, x9, [x29, #-0xc0]
0x0005ec78: e8 73 40 f9              ldr      x8, [sp, #0xe0]
0x0005ec7c: ff 02 08 eb              cmp      x23, x8
0x0005ec80: a0 f1 ff 54              b.eq     #0x5eab4
0x0005ec84: a8 03 52 f8              ldur     x8, [x29, #-0xe0]
0x0005ec88: 1f 05 00 71              cmp      w8, #1
0x0005ec8c: ab fd ff 54              b.lt     #0x5ec40
0x0005ec90: a8 03 52 f8              ldur     x8, [x29, #-0xe0]
0x0005ec94: 1f 41 00 71              cmp      w8, #0x10
0x0005ec98: 62 00 00 54              b.hs     #0x5eca4
0x0005ec9c: fc 03 1f aa              mov      x28, xzr
0x0005eca0: fa 00 00 14              b        #0x5f088                   ; -> udk7_q_fft+0x6bc
0x0005eca4: a8 83 51 f8              ldur     x8, [x29, #-0xe8]
0x0005eca8: fc 03 1f aa              mov      x28, xzr
0x0005ecac: ee b3 4c a9              ldp      x14, x12, [sp, #0xc8]
0x0005ecb0: 0d 7d 17 9b              mul      x13, x8, x23
0x0005ecb4: a8 01 7e b2              orr      x8, x13, #4
0x0005ecb8: 88 01 08 8b              add      x8, x12, x8
0x0005ecbc: 09 01 0e 8b              add      x9, x8, x14
0x0005ecc0: 3f 01 08 eb              cmp      x9, x8
0x0005ecc4: 23 1e 00 54              b.lo     #0x5f088
0x0005ecc8: 89 01 0d 8b              add      x9, x12, x13
0x0005eccc: 2a 01 0e 8b              add      x10, x9, x14
0x0005ecd0: 5f 01 09 eb              cmp      x10, x9
0x0005ecd4: a3 1d 00 54              b.lo     #0x5f088
0x0005ecd8: ea 5f 40 f9              ldr      x10, [sp, #0xb8]
0x0005ecdc: 8a 01 0a 8b              add      x10, x12, x10
0x0005ece0: 4a 01 0d 8b              add      x10, x10, x13
0x0005ece4: 4b 01 0e 8b              add      x11, x10, x14
0x0005ece8: 7f 01 0a eb              cmp      x11, x10
0x0005ecec: e3 1c 00 54              b.lo     #0x5f088
0x0005ecf0: eb 5b 40 f9              ldr      x11, [sp, #0xb0]
0x0005ecf4: 8b 01 0b 8b              add      x11, x12, x11
0x0005ecf8: 6b 01 0d 8b              add      x11, x11, x13
0x0005ecfc: 6c 01 0e 8b              add      x12, x11, x14
0x0005ed00: 9f 01 0b eb              cmp      x12, x11
0x0005ed04: 23 1c 00 54              b.lo     #0x5f088
0x0005ed08: ec 57 40 f9              ldr      x12, [sp, #0xa8]
0x0005ed0c: 8c fd 7d d3              lsr      x12, x12, #0x3d
0x0005ed10: cc 1b 00 b5              cbnz     x12, #0x5f088
0x0005ed14: ec 7b 40 f9              ldr      x12, [sp, #0xf0]
0x0005ed18: f1 53 40 f9              ldr      x17, [sp, #0xa0]
0x0005ed1c: e1 6b 40 f9              ldr      x1, [sp, #0xd0]
0x0005ed20: 8e 7d 17 9b              mul      x14, x12, x23
0x0005ed24: f0 37 40 f9              ldr      x16, [sp, #0x68]
0x0005ed28: e2 33 48 a9              ldp      x2, x12, [sp, #0x80]
0x0005ed2c: 35 02 0e 8b              add      x21, x17, x14
0x0005ed30: 30 02 10 8b              add      x16, x17, x16
0x0005ed34: e0 4f 40 f9              ldr      x0, [sp, #0x98]
0x0005ed38: 1a 02 0e 8b              add      x26, x16, x14
0x0005ed3c: 2f 02 02 8b              add      x15, x17, x2
0x0005ed40: 2c 00 0c 8b              add      x12, x1, x12
0x0005ed44: f4 01 0e 8b              add      x20, x15, x14
0x0005ed48: 8c 01 0d 8b              add      x12, x12, x13
0x0005ed4c: 5f 01 14 eb              cmp      x10, x20
0x0005ed50: af 83 51 f8              ldur     x15, [x29, #-0xe8]
0x0005ed54: a2 32 4c fa              ccmp     x21, x12, #2, lo
0x0005ed58: 30 02 00 8b              add      x16, x17, x0
0x0005ed5c: 20 00 00 8b              add      x0, x1, x0
0x0005ed60: 1c 02 0e 8b              add      x28, x16, x14
0x0005ed64: 2f 02 0f 8b              add      x15, x17, x15
0x0005ed68: f8 01 0e 8b              add      x24, x15, x14
0x0005ed6c: ef 27 9f 1a              cset     w15, lo
0x0005ed70: 5f 01 1a eb              cmp      x10, x26
0x0005ed74: 02 33 4c fa              ccmp     x24, x12, #2, lo
0x0005ed78: af 03 17 b8              stur     w15, [x29, #-0x90]
0x0005ed7c: cf 01 7e b2              orr      x15, x14, #4
0x0005ed80: 3b 02 0f 8b              add      x27, x17, x15
0x0005ed84: ef 27 9f 1a              cset     w15, lo
0x0005ed88: 5f 01 1c eb              cmp      x10, x28
0x0005ed8c: 62 33 4c fa              ccmp     x27, x12, #2, lo
0x0005ed90: af 03 16 b8              stur     w15, [x29, #-0xa0]
0x0005ed94: ef 3b 40 f9              ldr      x15, [sp, #0x70]
0x0005ed98: 30 02 0f 8b              add      x16, x17, x15
0x0005ed9c: ef 3f 40 f9              ldr      x15, [sp, #0x78]
0x0005eda0: 31 02 0f 8b              add      x17, x17, x15
0x0005eda4: 0f 02 0e 8b              add      x15, x16, x14
0x0005eda8: 2e 02 0e 8b              add      x14, x17, x14
0x0005edac: f0 27 9f 1a              cset     w16, lo
0x0005edb0: 5f 01 0e eb              cmp      x10, x14
0x0005edb4: 31 00 02 8b              add      x17, x1, x2
0x0005edb8: e2 31 4c fa              ccmp     x15, x12, #2, lo
0x0005edbc: 31 02 0d 8b              add      x17, x17, x13
0x0005edc0: b0 03 15 b8              stur     w16, [x29, #-0xb0]
0x0005edc4: f0 27 9f 1a              cset     w16, lo
0x0005edc8: 5f 01 11 eb              cmp      x10, x17
0x0005edcc: 22 31 4c fa              ccmp     x9, x12, #2, lo
0x0005edd0: f0 c7 00 b9              str      w16, [sp, #0xc4]
0x0005edd4: 10 00 0d 8b              add      x16, x0, x13
0x0005edd8: e0 27 9f 1a              cset     w0, lo
0x0005eddc: 5f 01 10 eb              cmp      x10, x16
0x0005ede0: 02 31 4c fa              ccmp     x8, x12, #2, lo
0x0005ede4: e0 c3 00 b9              str      w0, [sp, #0xc0]
0x0005ede8: e0 4b 40 f9              ldr      x0, [sp, #0x90]
0x0005edec: 21 00 00 8b              add      x1, x1, x0
0x0005edf0: 20 00 0d 8b              add      x0, x1, x13
0x0005edf4: ed 27 9f 1a              cset     w13, lo
0x0005edf8: 7f 01 14 eb              cmp      x11, x20
0x0005edfc: a2 32 40 fa              ccmp     x21, x0, #2, lo
0x0005ee00: e1 27 9f 1a              cset     w1, lo
0x0005ee04: 7f 01 1a eb              cmp      x11, x26
0x0005ee08: 02 33 40 fa              ccmp     x24, x0, #2, lo
0x0005ee0c: e2 27 9f 1a              cset     w2, lo
0x0005ee10: 7f 01 1c eb              cmp      x11, x28
0x0005ee14: 62 33 40 fa              ccmp     x27, x0, #2, lo
0x0005ee18: e3 27 9f 1a              cset     w3, lo
0x0005ee1c: 7f 01 0e eb              cmp      x11, x14
0x0005ee20: e2 31 40 fa              ccmp     x15, x0, #2, lo
0x0005ee24: e4 27 9f 1a              cset     w4, lo
0x0005ee28: 7f 01 11 eb              cmp      x11, x17
0x0005ee2c: 22 31 40 fa              ccmp     x9, x0, #2, lo
0x0005ee30: e5 27 9f 1a              cset     w5, lo
0x0005ee34: 7f 01 10 eb              cmp      x11, x16
0x0005ee38: 02 31 40 fa              ccmp     x8, x0, #2, lo
0x0005ee3c: e6 27 9f 1a              cset     w6, lo
0x0005ee40: bf 02 11 eb              cmp      x21, x17
0x0005ee44: 22 31 54 fa              ccmp     x9, x20, #2, lo
0x0005ee48: e7 27 9f 1a              cset     w7, lo
0x0005ee4c: bf 02 10 eb              cmp      x21, x16
0x0005ee50: 02 31 54 fa              ccmp     x8, x20, #2, lo
0x0005ee54: f4 27 9f 1a              cset     w20, lo
0x0005ee58: 1f 03 11 eb              cmp      x24, x17
0x0005ee5c: 22 31 5a fa              ccmp     x9, x26, #2, lo
0x0005ee60: f5 27 9f 1a              cset     w21, lo
0x0005ee64: 1f 03 10 eb              cmp      x24, x16
0x0005ee68: 02 31 5a fa              ccmp     x8, x26, #2, lo
0x0005ee6c: f8 27 9f 1a              cset     w24, lo
0x0005ee70: 7f 03 11 eb              cmp      x27, x17
0x0005ee74: 22 31 5c fa              ccmp     x9, x28, #2, lo
0x0005ee78: fa 27 9f 1a              cset     w26, lo
0x0005ee7c: 7f 03 10 eb              cmp      x27, x16
0x0005ee80: 02 31 5c fa              ccmp     x8, x28, #2, lo
0x0005ee84: fc 03 1f aa              mov      x28, xzr
0x0005ee88: fb 27 9f 1a              cset     w27, lo
0x0005ee8c: ff 01 11 eb              cmp      x15, x17
0x0005ee90: 22 31 4e fa              ccmp     x9, x14, #2, lo
0x0005ee94: fe 27 9f 1a              cset     w30, lo
0x0005ee98: ff 01 10 eb              cmp      x15, x16
0x0005ee9c: 02 31 4e fa              ccmp     x8, x14, #2, lo
0x0005eea0: ee 27 9f 1a              cset     w14, lo
0x0005eea4: 3f 01 10 eb              cmp      x9, x16
0x0005eea8: 02 31 51 fa              ccmp     x8, x17, #2, lo
0x0005eeac: e8 27 9f 1a              cset     w8, lo
0x0005eeb0: 7f 01 0c eb              cmp      x11, x12
0x0005eeb4: 42 31 40 fa              ccmp     x10, x0, #2, lo
0x0005eeb8: 83 0e 00 54              b.lo     #0x5f088
0x0005eebc: a9 03 57 b8              ldur     w9, [x29, #-0x90]
0x0005eec0: 49 0e 00 37              tbnz     w9, #0, #0x5f088
0x0005eec4: a9 03 56 b8              ldur     w9, [x29, #-0xa0]
0x0005eec8: 09 0e 00 37              tbnz     w9, #0, #0x5f088
0x0005eecc: a9 03 55 b8              ldur     w9, [x29, #-0xb0]
0x0005eed0: c9 0d 00 37              tbnz     w9, #0, #0x5f088
0x0005eed4: e9 c7 40 b9              ldr      w9, [sp, #0xc4]
0x0005eed8: 89 0d 00 37              tbnz     w9, #0, #0x5f088
0x0005eedc: e9 c3 40 b9              ldr      w9, [sp, #0xc0]
0x0005eee0: 49 0d 00 37              tbnz     w9, #0, #0x5f088
0x0005eee4: 2d 0d 00 37              tbnz     w13, #0, #0x5f088
0x0005eee8: 01 0d 00 37              tbnz     w1, #0, #0x5f088
0x0005eeec: e2 0c 00 37              tbnz     w2, #0, #0x5f088
0x0005eef0: c3 0c 00 37              tbnz     w3, #0, #0x5f088
0x0005eef4: a4 0c 00 37              tbnz     w4, #0, #0x5f088
0x0005eef8: 85 0c 00 37              tbnz     w5, #0, #0x5f088
0x0005eefc: 66 0c 00 37              tbnz     w6, #0, #0x5f088
0x0005ef00: 47 0c 00 37              tbnz     w7, #0, #0x5f088
0x0005ef04: 34 0c 00 37              tbnz     w20, #0, #0x5f088
0x0005ef08: 15 0c 00 37              tbnz     w21, #0, #0x5f088
0x0005ef0c: f8 0b 00 37              tbnz     w24, #0, #0x5f088
0x0005ef10: da 0b 00 37              tbnz     w26, #0, #0x5f088
0x0005ef14: bb 0b 00 37              tbnz     w27, #0, #0x5f088
0x0005ef18: 9e 0b 00 37              tbnz     w30, #0, #0x5f088
0x0005ef1c: 6e 0b 00 37              tbnz     w14, #0, #0x5f088
0x0005ef20: 48 0b 00 37              tbnz     w8, #0, #0x5f088
0x0005ef24: bb 83 52 f8              ldur     x27, [x29, #-0xd8]
0x0005ef28: fa 03 19 aa              mov      x26, x25
0x0005ef2c: fc 33 40 f9              ldr      x28, [sp, #0x60]
0x0005ef30: e0 17 c0 3d              ldr      q0, [sp, #0x50]
0x0005ef34: f8 03 1b aa              mov      x24, x27
0x0005ef38: f4 5f 40 f9              ldr      x20, [sp, #0xb8]
0x0005ef3c: b5 83 51 f8              ldur     x21, [x29, #-0xe8]
0x0005ef40: a0 03 96 3c              stur     q0, [x29, #-0xa0]
0x0005ef44: a0 83 00 d1              sub      x0, x29, #0x20
0x0005ef48: a0 03 d6 3c              ldur     q0, [x29, #-0xa0]
0x0005ef4c: a1 a3 00 d1              sub      x1, x29, #0x28
0x0005ef50: 00 d8 21 4e              scvtf    v0.4s, v0.4s
0x0005ef54: 00 90 81 4f              fmul     v0.4s, v0.4s, v1.s[0]
0x0005ef58: a1 03 d3 3c              ldur     q1, [x29, #-0xd0]
0x0005ef5c: 00 fc 21 6e              fdiv     v0.4s, v0.4s, v1.4s
0x0005ef60: 01 78 61 0e              fcvtl    v1.2d, v0.2s
0x0005ef64: 00 78 61 4e              fcvtl2   v0.2d, v0.4s
0x0005ef68: a1 03 95 3c              stur     q1, [x29, #-0xb0]
0x0005ef6c: a0 03 97 3c              stur     q0, [x29, #-0x90]
0x0005ef70: 60 cb 02 94              bl       #0x111cf0                  ; -> sincos@plt
0x0005ef74: a0 03 d7 3c              ldur     q0, [x29, #-0x90]
0x0005ef78: a0 43 00 d1              sub      x0, x29, #0x10
0x0005ef7c: a1 83 5d fc              ldur     d1, [x29, #-0x28]
0x0005ef80: a1 63 00 d1              sub      x1, x29, #0x18
0x0005ef84: 00 04 18 5e              mov      d0, v0.d[1]
0x0005ef88: a1 03 97 3c              stur     q1, [x29, #-0x90]
0x0005ef8c: 59 cb 02 94              bl       #0x111cf0                  ; -> sincos@plt
0x0005ef90: a8 63 00 d1              sub      x8, x29, #0x18
0x0005ef94: a0 03 d7 3c              ldur     q0, [x29, #-0x90]
0x0005ef98: a0 03 01 d1              sub      x0, x29, #0x40
0x0005ef9c: a1 23 01 d1              sub      x1, x29, #0x48
0x0005efa0: 00 85 40 4d              ld1      {v0.d}[1], [x8]
0x0005efa4: a0 03 97 3c              stur     q0, [x29, #-0x90]
0x0005efa8: a0 03 d5 3c              ldur     q0, [x29, #-0xb0]
0x0005efac: 51 cb 02 94              bl       #0x111cf0                  ; -> sincos@plt
0x0005efb0: a0 03 d5 3c              ldur     q0, [x29, #-0xb0]
0x0005efb4: a0 c3 00 d1              sub      x0, x29, #0x30
0x0005efb8: a1 83 5b fc              ldur     d1, [x29, #-0x48]
0x0005efbc: a1 e3 00 d1              sub      x1, x29, #0x38
0x0005efc0: 00 04 18 5e              mov      d0, v0.d[1]
0x0005efc4: a1 03 95 3c              stur     q1, [x29, #-0xb0]
0x0005efc8: 4a cb 02 94              bl       #0x111cf0                  ; -> sincos@plt
0x0005efcc: a0 03 5e fc              ldur     d0, [x29, #-0x20]
0x0005efd0: a8 e3 00 d1              sub      x8, x29, #0x38
0x0005efd4: b0 03 d5 3c              ldur     q16, [x29, #-0xb0]
0x0005efd8: a9 43 00 d1              sub      x9, x29, #0x10
0x0005efdc: a1 03 5c fc              ldur     d1, [x29, #-0x40]
0x0005efe0: 9c 13 00 f1              subs     x28, x28, #4
0x0005efe4: 10 85 40 4d              ld1      {v16.d}[1], [x8]
0x0005efe8: e8 03 1a aa              mov      x8, x26
0x0005efec: 20 85 40 4d              ld1      {v0.d}[1], [x9]
0x0005eff0: a9 c3 00 d1              sub      x9, x29, #0x30
0x0005eff4: 21 85 40 4d              ld1      {v1.d}[1], [x9]
0x0005eff8: 02 89 d5 4c              ld2      {v2.4s, v3.4s}, [x8], x21
0x0005effc: 21 68 61 0e              fcvtn    v1.2s, v1.2d
0x0005f000: 04 89 40 4c              ld2      {v4.4s, v5.4s}, [x8]
0x0005f004: 01 68 61 4e              fcvtn2   v1.4s, v0.2d
0x0005f008: 46 d4 24 4e              fadd     v6.4s, v2.4s, v4.4s
0x0005f00c: 67 d4 25 4e              fadd     v7.4s, v3.4s, v5.4s
0x0005f010: 06 8b 9f 4c              st2      {v6.4s, v7.4s}, [x24], #32
0x0005f014: 42 8b 40 4c              ld2      {v2.4s, v3.4s}, [x26]
0x0005f018: 06 6a 61 0e              fcvtn    v6.2s, v16.2d
0x0005f01c: 04 89 40 4c              ld2      {v4.4s, v5.4s}, [x8]
0x0005f020: 68 03 14 8b              add      x8, x27, x20
0x0005f024: fb 03 18 aa              mov      x27, x24
0x0005f028: a7 03 d7 3c              ldur     q7, [x29, #-0x90]
0x0005f02c: 50 8b df 4c              ld2      {v16.4s, v17.4s}, [x26], #32
0x0005f030: e6 68 61 4e              fcvtn2   v6.4s, v7.2d
0x0005f034: 40 d4 a4 4e              fsub     v0.4s, v2.4s, v4.4s
0x0005f038: 62 d4 a5 4e              fsub     v2.4s, v3.4s, v5.4s
0x0005f03c: 23 d6 a5 4e              fsub     v3.4s, v17.4s, v5.4s
0x0005f040: 04 d6 a4 4e              fsub     v4.4s, v16.4s, v4.4s
0x0005f044: 00 dc 26 6e              fmul     v0.4s, v0.4s, v6.4s
0x0005f048: 42 dc 21 6e              fmul     v2.4s, v2.4s, v1.4s
0x0005f04c: 63 dc 26 6e              fmul     v3.4s, v3.4s, v6.4s
0x0005f050: 81 dc 21 6e              fmul     v1.4s, v4.4s, v1.4s
0x0005f054: 04 d4 22 4e              fadd     v4.4s, v0.4s, v2.4s
0x0005f058: a0 03 d6 3c              ldur     q0, [x29, #-0xa0]
0x0005f05c: 82 04 00 4f              movi     v2.4s, #4
0x0005f060: 65 d4 a1 4e              fsub     v5.4s, v3.4s, v1.4s
0x0005f064: a1 03 d9 3c              ldur     q1, [x29, #-0x70]
0x0005f068: 00 84 a2 4e              add      v0.4s, v0.4s, v2.4s
0x0005f06c: 04 89 00 4c              st2      {v4.4s, v5.4s}, [x8]
0x0005f070: 81 f6 ff 54              b.ne     #0x5ef40
0x0005f074: e8 33 40 f9              ldr      x8, [sp, #0x60]
0x0005f078: a4 03 d8 3c              ldur     q4, [x29, #-0x80]
0x0005f07c: fc 03 08 aa              mov      x28, x8
0x0005f080: 1f 01 16 eb              cmp      x8, x22
0x0005f084: e0 dd ff 54              b.eq     #0x5ec40
0x0005f088: a8 83 54 f8              ldur     x8, [x29, #-0xb8]
0x0005f08c: 89 f3 7d d3              lsl      x9, x28, #3
0x0005f090: ea 6f 40 f9              ldr      x10, [sp, #0xd8]
0x0005f094: f8 03 1f aa              mov      x24, xzr
0x0005f098: 3a 03 09 8b              add      x26, x25, x9
0x0005f09c: 75 02 09 8b              add      x21, x19, x9
0x0005f0a0: 88 03 08 8b              add      x8, x28, x8
0x0005f0a4: 5b 0d 08 8b              add      x27, x10, x8, lsl #3
0x0005f0a8: a8 03 54 f8              ldur     x8, [x29, #-0xc0]
0x0005f0ac: 14 01 09 8b              add      x20, x8, x9
0x0005f0b0: 80 03 22 1e              scvtf    s0, w28
0x0005f0b4: a0 43 01 d1              sub      x0, x29, #0x50
0x0005f0b8: a1 63 01 d1              sub      x1, x29, #0x58
0x0005f0bc: 20 08 20 1e              fmul     s0, s1, s0
0x0005f0c0: 00 18 24 1e              fdiv     s0, s0, s4
0x0005f0c4: 00 c0 22 1e              fcvt     d0, s0
0x0005f0c8: 0a cb 02 94              bl       #0x111cf0                  ; -> sincos@plt
0x0005f0cc: 48 03 18 8b              add      x8, x26, x24
0x0005f0d0: 89 02 18 8b              add      x9, x20, x24
0x0005f0d4: aa 02 18 8b              add      x10, x21, x24
0x0005f0d8: 9c 07 00 91              add      x28, x28, #1
0x0005f0dc: df 02 1c eb              cmp      x22, x28
0x0005f0e0: 00 01 40 bd              ldr      s0, [x8]
0x0005f0e4: 21 c1 5f bc              ldur     s1, [x9, #-4]
0x0005f0e8: 00 28 21 1e              fadd     s0, s0, s1
0x0005f0ec: a2 87 7a 6d              ldp      d2, d1, [x29, #-0x58]
0x0005f0f0: 40 c1 1f bc              stur     s0, [x10, #-4]
0x0005f0f4: 00 05 40 bd              ldr      s0, [x8, #4]
0x0005f0f8: 42 40 62 1e              fcvt     s2, d2
0x0005f0fc: 23 01 40 bd              ldr      s3, [x9]
0x0005f100: 21 40 62 1e              fcvt     s1, d1
0x0005f104: 00 28 23 1e              fadd     s0, s0, s3
0x0005f108: 40 01 00 bd              str      s0, [x10]
0x0005f10c: 6a 03 18 8b              add      x10, x27, x24
0x0005f110: 20 95 7f 2d              ldp      s0, s5, [x9, #-4]
0x0005f114: 03 11 40 2d              ldp      s3, s4, [x8]
0x0005f118: 18 23 00 91              add      x24, x24, #8
0x0005f11c: 60 38 20 1e              fsub     s0, s3, s0
0x0005f120: 83 38 25 1e              fsub     s3, s4, s5
0x0005f124: 00 08 22 1e              fmul     s0, s0, s2
0x0005f128: 63 08 21 1e              fmul     s3, s3, s1
0x0005f12c: 00 28 23 1e              fadd     s0, s0, s3
0x0005f130: 40 c1 1f bc              stur     s0, [x10, #-4]
0x0005f134: 25 81 7f 2d              ldp      s5, s0, [x9, #-4]
0x0005f138: 04 0d 40 2d              ldp      s4, s3, [x8]
0x0005f13c: 60 38 20 1e              fsub     s0, s3, s0
0x0005f140: 83 38 25 1e              fsub     s3, s4, s5
0x0005f144: 00 08 22 1e              fmul     s0, s0, s2
0x0005f148: 61 08 21 1e              fmul     s1, s3, s1
0x0005f14c: 00 38 21 1e              fsub     s0, s0, s1
0x0005f150: a4 07 7c ad              ldp      q4, q1, [x29, #-0x80]
0x0005f154: 40 01 00 bd              str      s0, [x10]
0x0005f158: c1 fa ff 54              b.ne     #0x5f0b0
0x0005f15c: b9 fe ff 17              b        #0x5ec40                   ; -> udk7_q_fft+0x274
0x0005f160: 3f 08 00 71              cmp      w1, #2
0x0005f164: 0b 09 00 54              b.lt     #0x5f284
0x0005f168: eb 03 40 f9              ldr      x11, [sp]
0x0005f16c: 68 7d 40 93              sxtw     x8, w11
0x0005f170: 49 cc 2b 8b              add      x9, x2, w11, sxtw #3
0x0005f174: 7f 51 00 71              cmp      w11, #0x14
0x0005f178: 82 09 00 54              b.hs     #0x5f2a8
0x0005f17c: ea 03 1f aa              mov      x10, xzr
0x0005f180: ed 03 40 f9              ldr      x13, [sp]
0x0005f184: 0c 10 0a 8b              add      x12, x0, x10, lsl #4
0x0005f188: 4b 0c 0a 8b              add      x11, x2, x10, lsl #3
0x0005f18c: 8c 21 00 91              add      x12, x12, #8
0x0005f190: aa 01 0a cb              sub      x10, x13, x10
0x0005f194: 8d 81 5f f8              ldur     x13, [x12, #-8]
0x0005f198: 4a 05 00 f1              subs     x10, x10, #1
0x0005f19c: 6d 01 00 f9              str      x13, [x11]
0x0005f1a0: 8d 05 41 f8              ldr      x13, [x12], #0x10
0x0005f1a4: 6d 79 28 f8              str      x13, [x11, x8, lsl #3]
0x0005f1a8: 6b 21 00 91              add      x11, x11, #8
0x0005f1ac: 41 ff ff 54              b.ne     #0x5f194
0x0005f1b0: 3f 08 00 71              cmp      w1, #2
0x0005f1b4: 8b 06 00 54              b.lt     #0x5f284
0x0005f1b8: eb 03 40 f9              ldr      x11, [sp]
0x0005f1bc: 7f 91 00 71              cmp      w11, #0x24
0x0005f1c0: 22 0d 00 54              b.hs     #0x5f364
0x0005f1c4: ea 03 1f aa              mov      x10, xzr
0x0005f1c8: eb 03 40 f9              ldr      x11, [sp]
0x0005f1cc: 49 f1 7d d3              lsl      x9, x10, #3
0x0005f1d0: 28 0d 08 8b              add      x8, x9, x8, lsl #3
0x0005f1d4: 08 01 7e b2              orr      x8, x8, #4
0x0005f1d8: 6a 01 0a cb              sub      x10, x11, x10
0x0005f1dc: eb 03 00 aa              mov      x11, x0
0x0005f1e0: 4c 00 09 8b              add      x12, x2, x9
0x0005f1e4: 4d 00 08 8b              add      x13, x2, x8
0x0005f1e8: 6e 01 09 8b              add      x14, x11, x9
0x0005f1ec: 4a 05 00 f1              subs     x10, x10, #1
0x0005f1f0: 42 20 00 91              add      x2, x2, #8
0x0005f1f4: 80 01 40 bd              ldr      s0, [x12]
0x0005f1f8: a1 c1 5f bc              ldur     s1, [x13, #-4]
0x0005f1fc: 00 28 21 1e              fadd     s0, s0, s1
0x0005f200: c0 01 00 bd              str      s0, [x14]
0x0005f204: 80 05 40 bd              ldr      s0, [x12, #4]
0x0005f208: a1 01 40 bd              ldr      s1, [x13]
0x0005f20c: 00 28 21 1e              fadd     s0, s0, s1
0x0005f210: c0 05 00 bd              str      s0, [x14, #4]
0x0005f214: 6e 01 08 8b              add      x14, x11, x8
0x0005f218: 80 01 40 bd              ldr      s0, [x12]
0x0005f21c: 6b 21 00 91              add      x11, x11, #8
0x0005f220: a1 c1 5f bc              ldur     s1, [x13, #-4]
0x0005f224: 00 38 21 1e              fsub     s0, s0, s1
0x0005f228: c0 c1 1f bc              stur     s0, [x14, #-4]
0x0005f22c: 80 05 40 bd              ldr      s0, [x12, #4]
0x0005f230: a1 01 40 bd              ldr      s1, [x13]
0x0005f234: 00 38 21 1e              fsub     s0, s0, s1
0x0005f238: c0 01 00 bd              str      s0, [x14]
0x0005f23c: 21 fd ff 54              b.ne     #0x5f1e0
0x0005f240: 3f 10 00 71              cmp      w1, #4
0x0005f244: 0b 02 00 54              b.lt     #0x5f284
0x0005f248: e9 03 40 f9              ldr      x9, [sp]
0x0005f24c: 48 00 80 52              mov      w8, #2
0x0005f250: 0a 30 00 91              add      x10, x0, #0xc
0x0005f254: 3f 09 00 71              cmp      w9, #2
0x0005f258: 28 c1 88 1a              csel     w8, w9, w8, gt
0x0005f25c: 09 4c 21 8b              add      x9, x0, w1, uxtw #3
0x0005f260: 08 05 00 d1              sub      x8, x8, #1
0x0005f264: 29 11 00 d1              sub      x9, x9, #4
0x0005f268: 40 c1 5f bc              ldur     s0, [x10, #-4]
0x0005f26c: 08 05 00 d1              sub      x8, x8, #1
0x0005f270: 20 c1 1f bc              stur     s0, [x9, #-4]
0x0005f274: 40 85 40 bc              ldr      s0, [x10], #8
0x0005f278: 00 40 21 1e              fneg     s0, s0
0x0005f27c: 20 85 1f bc              str      s0, [x9], #0xfffffffffffffff8
0x0005f280: 48 ff ff b5              cbnz     x8, #0x5f268
0x0005f284: ff 83 07 91              add      sp, sp, #0x1e0
0x0005f288: f4 4f 45 a9              ldp      x20, x19, [sp, #0x50]
0x0005f28c: f6 57 44 a9              ldp      x22, x21, [sp, #0x40]
0x0005f290: f8 5f 43 a9              ldp      x24, x23, [sp, #0x30]
0x0005f294: fa 67 42 a9              ldp      x26, x25, [sp, #0x20]
0x0005f298: fc 6f 41 a9              ldp      x28, x27, [sp, #0x10]
0x0005f29c: fd 7b c6 a8              ldp      x29, x30, [sp], #0x60
0x0005f2a0: bf 23 03 d5              autiasp  
0x0005f2a4: c0 03 5f d6              ret      
0x0005f2a8: 0a 10 0b 8b              add      x10, x0, x11, lsl #4
0x0005f2ac: 4f 0c 0b 8b              add      x15, x2, x11, lsl #3
0x0005f2b0: 4d 21 00 d1              sub      x13, x10, #8
0x0005f2b4: 0e 20 00 91              add      x14, x0, #8
0x0005f2b8: bf 01 02 eb              cmp      x13, x2
0x0005f2bc: ec 03 0b aa              mov      x12, x11
0x0005f2c0: e0 81 40 fa              ccmp     x15, x0, #0, hi
0x0005f2c4: 0c 01 0c 8b              add      x12, x8, x12
0x0005f2c8: 50 0c 0c 8b              add      x16, x2, x12, lsl #3
0x0005f2cc: eb 97 9f 1a              cset     w11, hi
0x0005f2d0: 5f 01 02 eb              cmp      x10, x2
0x0005f2d4: c2 81 4f fa              ccmp     x14, x15, #2, hi
0x0005f2d8: ec 27 9f 1a              cset     w12, lo
0x0005f2dc: 3f 01 0d eb              cmp      x9, x13
0x0005f2e0: 00 32 40 fa              ccmp     x16, x0, #0, lo
0x0005f2e4: ed 97 9f 1a              cset     w13, hi
0x0005f2e8: 3f 01 0a eb              cmp      x9, x10
0x0005f2ec: c2 31 50 fa              ccmp     x14, x16, #2, lo
0x0005f2f0: ea 03 1f aa              mov      x10, xzr
0x0005f2f4: ee 27 9f 1a              cset     w14, lo
0x0005f2f8: 3f 01 0f eb              cmp      x9, x15
0x0005f2fc: 00 32 42 fa              ccmp     x16, x2, #0, lo
0x0005f300: 08 f4 ff 54              b.hi     #0x5f180
0x0005f304: eb f3 07 37              tbnz     w11, #0, #0x5f180
0x0005f308: cc f3 07 37              tbnz     w12, #0, #0x5f180
0x0005f30c: ad f3 07 37              tbnz     w13, #0, #0x5f180
0x0005f310: 8e f3 07 37              tbnz     w14, #0, #0x5f180
0x0005f314: ea 03 40 f9              ldr      x10, [sp]
0x0005f318: 0b f1 7d d3              lsl      x11, x8, #3
0x0005f31c: 4c 40 00 91              add      x12, x2, #0x10
0x0005f320: 0d 80 00 91              add      x13, x0, #0x20
0x0005f324: 4a 75 7e 92              and      x10, x10, #0xfffffffc
0x0005f328: ee 03 0a aa              mov      x14, x10
0x0005f32c: af 81 00 d1              sub      x15, x13, #0x20
0x0005f330: a0 8d 40 4c              ld2      {v0.2d, v1.2d}, [x13]
0x0005f334: ce 11 00 f1              subs     x14, x14, #4
0x0005f338: ad 01 01 91              add      x13, x13, #0x40
0x0005f33c: e2 8d 40 4c              ld2      {v2.2d, v3.2d}, [x15]
0x0005f340: 8f 01 0b 8b              add      x15, x12, x11
0x0005f344: 82 81 3f ad              stp      q2, q0, [x12, #-0x10]
0x0005f348: 8c 81 00 91              add      x12, x12, #0x20
0x0005f34c: e3 85 3f ad              stp      q3, q1, [x15, #-0x10]
0x0005f350: e1 fe ff 54              b.ne     #0x5f32c
0x0005f354: eb 03 40 f9              ldr      x11, [sp]
0x0005f358: 5f 01 0b eb              cmp      x10, x11
0x0005f35c: 21 f1 ff 54              b.ne     #0x5f180
0x0005f360: 94 ff ff 17              b        #0x5f1b0                   ; -> udk7_q_fft+0x7e4
0x0005f364: 8d 00 80 52              mov      w13, #4
0x0005f368: 6c 05 00 d1              sub      x12, x11, #1
0x0005f36c: 0d f1 7d b3              bfi      x13, x8, #3, #0x3d
0x0005f370: ea 03 1f aa              mov      x10, xzr
0x0005f374: 8e f1 7d d3              lsl      x14, x12, #3
0x0005f378: 4b 00 0d 8b              add      x11, x2, x13
0x0005f37c: 6f 01 0e 8b              add      x15, x11, x14
0x0005f380: ff 01 0b eb              cmp      x15, x11
0x0005f384: 23 f2 ff 54              b.lo     #0x5f1c8
0x0005f388: 2b 01 0e 8b              add      x11, x9, x14
0x0005f38c: 7f 01 09 eb              cmp      x11, x9
0x0005f390: c3 f1 ff 54              b.lo     #0x5f1c8
0x0005f394: 0b f1 7d d3              lsl      x11, x8, #3
0x0005f398: 0f 00 0b 8b              add      x15, x0, x11
0x0005f39c: f0 01 0e 8b              add      x16, x15, x14
0x0005f3a0: 1f 02 0f eb              cmp      x16, x15
0x0005f3a4: 23 f1 ff 54              b.lo     #0x5f1c8
0x0005f3a8: 0d 00 0d 8b              add      x13, x0, x13
0x0005f3ac: ae 01 0e 8b              add      x14, x13, x14
0x0005f3b0: df 01 0d eb              cmp      x14, x13
0x0005f3b4: a3 f0 ff 54              b.lo     #0x5f1c8
0x0005f3b8: 8c fd 7d d3              lsr      x12, x12, #0x3d
0x0005f3bc: 6c f0 ff b5              cbnz     x12, #0x5f1c8
0x0005f3c0: ea 03 40 f9              ldr      x10, [sp]
0x0005f3c4: 54 10 00 91              add      x20, x2, #4
0x0005f3c8: 17 00 0b 8b              add      x23, x0, x11
0x0005f3cc: e1 1f 00 f9              str      x1, [sp, #0x38]
0x0005f3d0: 51 f1 7d d3              lsl      x17, x10, #3
0x0005f3d4: 2a 12 00 d1              sub      x10, x17, #4
0x0005f3d8: 55 00 11 8b              add      x21, x2, x17
0x0005f3dc: 47 00 0a 8b              add      x7, x2, x10
0x0005f3e0: 0a 00 0a 8b              add      x10, x0, x10
0x0005f3e4: ff 00 00 eb              cmp      x7, x0
0x0005f3e8: 70 01 11 8b              add      x16, x11, x17
0x0005f3ec: 40 81 42 fa              ccmp     x10, x2, #0, hi
0x0005f3f0: 0f 12 00 d1              sub      x15, x16, #4
0x0005f3f4: 18 00 0f 8b              add      x24, x0, x15
0x0005f3f8: 1a 00 10 8b              add      x26, x0, x16
0x0005f3fc: 5b 00 0f 8b              add      x27, x2, x15
0x0005f400: 5e 00 10 8b              add      x30, x2, x16
0x0005f404: ec 97 9f 1a              cset     w12, hi
0x0005f408: bf 02 00 eb              cmp      x21, x0
0x0005f40c: 82 82 4a fa              ccmp     x20, x10, #2, hi
0x0005f410: ac 03 19 b8              stur     w12, [x29, #-0x70]
0x0005f414: ec 27 9f 1a              cset     w12, lo
0x0005f418: 1f 03 00 eb              cmp      x24, x0
0x0005f41c: e2 82 4a fa              ccmp     x23, x10, #2, hi
0x0005f420: ac 03 18 b8              stur     w12, [x29, #-0x80]
0x0005f424: 8c 00 80 52              mov      w12, #4
0x0005f428: 0c f1 7d b3              bfi      x12, x8, #3, #0x3d
0x0005f42c: ed 27 9f 1a              cset     w13, lo
0x0005f430: 19 00 0c 8b              add      x25, x0, x12
0x0005f434: 5f 03 00 eb              cmp      x26, x0
0x0005f438: 22 83 4a fa              ccmp     x25, x10, #2, hi
0x0005f43c: 5c 00 0c 8b              add      x28, x2, x12
0x0005f440: ad 03 17 b8              stur     w13, [x29, #-0x90]
0x0005f444: ec 6b 40 f9              ldr      x12, [sp, #0xd0]
0x0005f448: ed 27 9f 1a              cset     w13, lo
0x0005f44c: 7f 03 00 eb              cmp      x27, x0
0x0005f450: 22 81 4a fa              ccmp     x9, x10, #2, hi
0x0005f454: e0 6b 40 f9              ldr      x0, [sp, #0xd0]
0x0005f458: ad 03 16 b8              stur     w13, [x29, #-0xa0]
0x0005f45c: ed 6b 40 f9              ldr      x13, [sp, #0xd0]
0x0005f460: f0 27 9f 1a              cset     w16, lo
0x0005f464: df 03 0c eb              cmp      x30, x12
0x0005f468: ec 6b 40 f9              ldr      x12, [sp, #0xd0]
0x0005f46c: 82 83 4a fa              ccmp     x28, x10, #2, hi
0x0005f470: ad 01 11 8b              add      x13, x13, x17
0x0005f474: 8c 11 00 91              add      x12, x12, #4
0x0005f478: f1 27 9f 1a              cset     w17, lo
0x0005f47c: 9f 01 07 eb              cmp      x12, x7
0x0005f480: a0 31 42 fa              ccmp     x13, x2, #0, lo
0x0005f484: ef 97 9f 1a              cset     w15, hi
0x0005f488: 9f 01 15 eb              cmp      x12, x21
0x0005f48c: 82 32 4d fa              ccmp     x20, x13, #2, lo
0x0005f490: e1 27 9f 1a              cset     w1, lo
0x0005f494: 9f 01 18 eb              cmp      x12, x24
0x0005f498: e2 32 4d fa              ccmp     x23, x13, #2, lo
0x0005f49c: ee 27 9f 1a              cset     w14, lo
0x0005f4a0: 9f 01 1a eb              cmp      x12, x26
0x0005f4a4: 22 33 4d fa              ccmp     x25, x13, #2, lo
0x0005f4a8: e3 27 9f 1a              cset     w3, lo
0x0005f4ac: 9f 01 1b eb              cmp      x12, x27
0x0005f4b0: 22 31 4d fa              ccmp     x9, x13, #2, lo
0x0005f4b4: e4 27 9f 1a              cset     w4, lo
0x0005f4b8: 9f 01 1e eb              cmp      x12, x30
0x0005f4bc: 82 33 4d fa              ccmp     x28, x13, #2, lo
0x0005f4c0: e5 27 9f 1a              cset     w5, lo
0x0005f4c4: 1f 03 02 eb              cmp      x24, x2
0x0005f4c8: e2 82 47 fa              ccmp     x23, x7, #2, hi
0x0005f4cc: e6 27 9f 1a              cset     w6, lo
0x0005f4d0: 5f 03 02 eb              cmp      x26, x2
0x0005f4d4: 22 83 47 fa              ccmp     x25, x7, #2, hi
0x0005f4d8: e7 27 9f 1a              cset     w7, lo
0x0005f4dc: 9f 02 18 eb              cmp      x20, x24
0x0005f4e0: e2 32 55 fa              ccmp     x23, x21, #2, lo
0x0005f4e4: f3 27 9f 1a              cset     w19, lo
0x0005f4e8: 9f 02 1a eb              cmp      x20, x26
0x0005f4ec: 22 33 55 fa              ccmp     x25, x21, #2, lo
0x0005f4f0: f4 27 9f 1a              cset     w20, lo
0x0005f4f4: ff 02 1a eb              cmp      x23, x26
0x0005f4f8: 22 33 58 fa              ccmp     x25, x24, #2, lo
0x0005f4fc: f5 27 9f 1a              cset     w21, lo
0x0005f500: ff 02 1b eb              cmp      x23, x27
0x0005f504: 22 31 58 fa              ccmp     x9, x24, #2, lo
0x0005f508: f6 27 9f 1a              cset     w22, lo
0x0005f50c: ff 02 1e eb              cmp      x23, x30
0x0005f510: 82 33 58 fa              ccmp     x28, x24, #2, lo
0x0005f514: f7 27 9f 1a              cset     w23, lo
0x0005f518: 3f 03 1b eb              cmp      x25, x27
0x0005f51c: 22 31 5a fa              ccmp     x9, x26, #2, lo
0x0005f520: e9 27 9f 1a              cset     w9, lo
0x0005f524: 3f 03 1e eb              cmp      x25, x30
0x0005f528: 82 33 5a fa              ccmp     x28, x26, #2, lo
0x0005f52c: f8 27 9f 1a              cset     w24, lo
0x0005f530: 9f 01 0a eb              cmp      x12, x10
0x0005f534: a0 31 40 fa              ccmp     x13, x0, #0, lo
0x0005f538: ea 03 1f aa              mov      x10, xzr
0x0005f53c: a8 06 00 54              b.hi     #0x5f610
0x0005f540: ac 03 59 b8              ldur     w12, [x29, #-0x70]
0x0005f544: 6c 06 00 37              tbnz     w12, #0, #0x5f610
0x0005f548: ac 03 58 b8              ldur     w12, [x29, #-0x80]
0x0005f54c: 2c 06 00 37              tbnz     w12, #0, #0x5f610
0x0005f550: ac 03 57 b8              ldur     w12, [x29, #-0x90]
0x0005f554: ec 05 00 37              tbnz     w12, #0, #0x5f610
0x0005f558: ac 03 56 b8              ldur     w12, [x29, #-0xa0]
0x0005f55c: ac 05 00 37              tbnz     w12, #0, #0x5f610
0x0005f560: 90 05 00 37              tbnz     w16, #0, #0x5f610
0x0005f564: 71 05 00 37              tbnz     w17, #0, #0x5f610
0x0005f568: 4f 05 00 37              tbnz     w15, #0, #0x5f610
0x0005f56c: 21 05 00 37              tbnz     w1, #0, #0x5f610
0x0005f570: 0e 05 00 37              tbnz     w14, #0, #0x5f610
0x0005f574: e3 04 00 37              tbnz     w3, #0, #0x5f610
0x0005f578: c4 04 00 37              tbnz     w4, #0, #0x5f610
0x0005f57c: a5 04 00 37              tbnz     w5, #0, #0x5f610
0x0005f580: e2 53 40 f9              ldr      x2, [sp, #0xa0]
0x0005f584: 26 04 00 37              tbnz     w6, #0, #0x5f608
0x0005f588: 07 04 00 37              tbnz     w7, #0, #0x5f608
0x0005f58c: f3 03 00 37              tbnz     w19, #0, #0x5f608
0x0005f590: d4 03 00 37              tbnz     w20, #0, #0x5f608
0x0005f594: b5 03 00 37              tbnz     w21, #0, #0x5f608
0x0005f598: 96 03 00 37              tbnz     w22, #0, #0x5f608
0x0005f59c: 77 03 00 37              tbnz     w23, #0, #0x5f608
0x0005f5a0: 49 03 00 37              tbnz     w9, #0, #0x5f608
0x0005f5a4: e1 1f 40 f9              ldr      x1, [sp, #0x38]
0x0005f5a8: 18 e1 07 37              tbnz     w24, #0, #0x5f1c8
0x0005f5ac: e9 03 40 f9              ldr      x9, [sp]
0x0005f5b0: ec 03 02 aa              mov      x12, x2
0x0005f5b4: ed 03 00 aa              mov      x13, x0
0x0005f5b8: 2a 75 7e 92              and      x10, x9, #0xfffffffc
0x0005f5bc: e9 03 0a aa              mov      x9, x10
0x0005f5c0: ee 03 0c aa              mov      x14, x12
0x0005f5c4: af 01 0b 8b              add      x15, x13, x11
0x0005f5c8: 29 11 00 f1              subs     x9, x9, #4
0x0005f5cc: c0 89 cb 4c              ld2      {v0.4s, v1.4s}, [x14], x11
0x0005f5d0: c2 89 40 4c              ld2      {v2.4s, v3.4s}, [x14]
0x0005f5d4: 04 d4 22 4e              fadd     v4.4s, v0.4s, v2.4s
0x0005f5d8: 25 d4 23 4e              fadd     v5.4s, v1.4s, v3.4s
0x0005f5dc: a4 89 9f 4c              st2      {v4.4s, v5.4s}, [x13], #32
0x0005f5e0: 80 89 df 4c              ld2      {v0.4s, v1.4s}, [x12], #32
0x0005f5e4: c2 89 40 4c              ld2      {v2.4s, v3.4s}, [x14]
0x0005f5e8: 04 d4 a2 4e              fsub     v4.4s, v0.4s, v2.4s
0x0005f5ec: 25 d4 a3 4e              fsub     v5.4s, v1.4s, v3.4s
0x0005f5f0: e4 89 00 4c              st2      {v4.4s, v5.4s}, [x15]
0x0005f5f4: 61 fe ff 54              b.ne     #0x5f5c0
0x0005f5f8: e9 03 40 f9              ldr      x9, [sp]
0x0005f5fc: 5f 01 09 eb              cmp      x10, x9
0x0005f600: 41 de ff 54              b.ne     #0x5f1c8
0x0005f604: 0f ff ff 17              b        #0x5f240                   ; -> udk7_q_fft+0x874
0x0005f608: e1 1f 40 f9              ldr      x1, [sp, #0x38]
0x0005f60c: ef fe ff 17              b        #0x5f1c8                   ; -> udk7_q_fft+0x7fc
0x0005f610: e1 1f 40 f9              ldr      x1, [sp, #0x38]
0x0005f614: e2 53 40 f9              ldr      x2, [sp, #0xa0]
0x0005f618: ec fe ff 17              b        #0x5f1c8                   ; -> udk7_q_fft+0x7fc
