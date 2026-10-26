; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_trill/native/libeffect.so
; target  : range 0x68ab00:0x68b100
; start   : 0x68ab00
; size    : 0x600
; bytes   : 0x600   instructions: 384
; generated: 2026-10-23T23:52:46

0x0068ab00: 73 37 11 94              bl       #0xad88cc
0x0068ab04: fc 03 00 aa              mov      x28, x0
0x0068ab08: a0 03 03 d1              sub      x0, x29, #0xc0
0x0068ab0c: e5 76 f1 97              bl       #0x2e86a0
0x0068ab10: b5 2a 10 94              bl       #0xa955e4
0x0068ab14: 1f 03 1c eb              cmp      x24, x28
0x0068ab18: 00 02 00 54              b.eq     #0x68ab58
0x0068ab1c: c1 38 00 d0              adrp     x1, #0xda4000
0x0068ab20: 21 0c 20 91              add      x1, x1, #0x803
0x0068ab24: d2 49 10 94              bl       #0xa9d26c
0x0068ab28: 66 37 11 94              bl       #0xad88c0
0x0068ab2c: c1 38 00 d0              adrp     x1, #0xda4000
0x0068ab30: fc 03 00 aa              mov      x28, x0
0x0068ab34: b4 99 10 94              bl       #0xab1204
0x0068ab38: 68 37 11 94              bl       #0xad88d8
0x0068ab3c: a0 c2 10 94              bl       #0xabb5bc
0x0068ab40: fc 03 00 2a              mov      w28, w0
0x0068ab44: a0 03 03 d1              sub      x0, x29, #0xc0
0x0068ab48: d6 76 f1 97              bl       #0x2e86a0
0x0068ab4c: a6 2a 10 94              bl       #0xa955e4
0x0068ab50: 08 00 00 14              b        #0x68ab70
0x0068ab54: a4 2a 10 94              bl       #0xa955e4
0x0068ab58: c1 38 00 d0              adrp     x1, #0xda4000
0x0068ab5c: c2 38 00 d0              adrp     x2, #0xda4000
0x0068ab60: 21 7c 1f 91              add      x1, x1, #0x7df
0x0068ab64: 42 44 20 91              add      x2, x2, #0x811
0x0068ab68: 7b 06 10 94              bl       #0xa8c554
0x0068ab6c: 9c 00 80 12              mov      w28, #-5
0x0068ab70: 39 02 12 94              bl       #0xb0b454
0x0068ab74: b9 96 11 94              bl       #0xaf0658
0x0068ab78: cf cb 11 94              bl       #0xafdab4
0x0068ab7c: d6 db 11 94              bl       #0xb01ad4
0x0068ab80: 7c 01 00 34              cbz      w28, #0x68abac
0x0068ab84: e0 c3 05 91              add      x0, sp, #0x170
0x0068ab88: c8 9f 00 94              bl       #0x6b2aa8
0x0068ab8c: ea 54 10 94              bl       #0xa9ff34
0x0068ab90: 48 17 40 f9              ldr      x8, [x26, #0x28]
0x0068ab94: 5f 6a 10 94              bl       #0xaa5510
0x0068ab98: e1 46 00 54              b.ne     #0x68b474
0x0068ab9c: e0 03 1c 2a              mov      w0, w28
0x0068aba0: ff 03 10 91              add      sp, sp, #0x400
0x0068aba4: fd 7b 48 a9              ldp      x29, x30, [sp, #0x80]
0x0068aba8: d6 0d 10 14              b        #0xa8e300
0x0068abac: e8 02 40 f9              ldr      x8, [x23]
0x0068abb0: 21 6b 11 94              bl       #0xae5834
0x0068abb4: 61 00 00 54              b.ne     #0x68abc0
0x0068abb8: 09 10 80 52              mov      w9, #0x80
0x0068abbc: 09 19 00 b9              str      w9, [x8, #0x18]
0x0068abc0: 59 30 00 b0              adrp     x25, #0xc93000
0x0068abc4: 13 01 90 37              tbnz     w19, #0x12, #0x68abe4
0x0068abc8: 93 2d 98 37              tbnz     w19, #0x13, #0x68b178
0x0068abcc: 08 11 81 52              mov      w8, #0x888
0x0068abd0: 88 3a 05 b9              str      w8, [x20, #0x538]
0x0068abd4: 93 11 a0 37              tbnz     w19, #0x14, #0x68ae04
0x0068abd8: f3 11 a8 37              tbnz     w19, #0x15, #0x68ae14
0x0068abdc: 5c 0d 80 12              mov      w28, #-0x6b
0x0068abe0: e9 ff ff 17              b        #0x68ab84
0x0068abe4: 19 55 10 94              bl       #0xaa0048
0x0068abe8: 20 11 00 b4              cbz      x0, #0x68ae0c
0x0068abec: fb 03 00 aa              mov      x27, x0
0x0068abf0: 94 0f 00 94              bl       #0x68ea40
0x0068abf4: c8 59 00 f0              adrp     x8, #0x11c5000
0x0068abf8: 08 81 36 91              add      x8, x8, #0xda0
0x0068abfc: e0 03 09 91              add      x0, sp, #0x240
0x0068ac00: 01 02 80 52              mov      w1, #0x10
0x0068ac04: 68 03 00 f9              str      x8, [x27]
0x0068ac08: 9b 8e 00 f9              str      x27, [x20, #0x118]
0x0068ac0c: 83 2d 00 94              bl       #0x696218
0x0068ac10: 48 30 00 b0              adrp     x8, #0xc93000
0x0068ac14: 00 21 42 fd              ldr      d0, [x8, #0x440]
0x0068ac18: 17 06 80 52              mov      w23, #0x30
0x0068ac1c: e0 63 08 91              add      x0, sp, #0x218
0x0068ac20: a1 a3 02 d1              sub      x1, x29, #0xa8
0x0068ac24: 62 00 80 52              mov      w2, #3
0x0068ac28: a0 83 15 fc              stur     d0, [x29, #-0xa8]
0x0068ac2c: b7 03 16 b8              stur     w23, [x29, #-0xa0]
0x0068ac30: 7a ef f2 97              bl       #0x346a18
0x0068ac34: 18 04 80 52              mov      w24, #0x20
0x0068ac38: 9e 14 11 94              bl       #0xacfeb0
0x0068ac3c: b8 83 14 b8              stur     w24, [x29, #-0xb8]
0x0068ac40: 76 ef f2 97              bl       #0x346a18
0x0068ac44: a7 13 11 94              bl       #0xacfae0
0x0068ac48: b8 83 13 b8              stur     w24, [x29, #-0xc8]
0x0068ac4c: 73 ef f2 97              bl       #0x346a18
0x0068ac50: 2c 27 42 fd              ldr      d12, [x25, #0x448]
0x0068ac54: e0 a3 0d 91              add      x0, sp, #0x368
0x0068ac58: e1 43 06 91              add      x1, sp, #0x190
0x0068ac5c: 42 00 80 52              mov      w2, #2
0x0068ac60: ec cb 00 fd              str      d12, [sp, #0x190]
0x0068ac64: 50 07 f6 97              bl       #0x40c9a4
0x0068ac68: e0 b3 40 f9              ldr      x0, [sp, #0x160]
0x0068ac6c: e1 23 07 91              add      x1, sp, #0x1c8
0x0068ac70: 42 00 80 52              mov      w2, #2
0x0068ac74: ec e7 00 fd              str      d12, [sp, #0x1c8]
0x0068ac78: 4b 07 f6 97              bl       #0x40c9a4
0x0068ac7c: e0 b7 40 f9              ldr      x0, [sp, #0x168]
0x0068ac80: b5 ca 11 94              bl       #0xafd754
0x0068ac84: ec af 01 fd              str      d12, [sp, #0x358]
0x0068ac88: e8 63 03 b9              str      w8, [sp, #0x360]
0x0068ac8c: 46 07 f6 97              bl       #0x40c9a4
0x0068ac90: e0 43 07 91              add      x0, sp, #0x1d0
0x0068ac94: e1 a3 0d 91              add      x1, sp, #0x368
0x0068ac98: 28 59 10 94              bl       #0xaa1138
0x0068ac9c: 59 33 93 52              mov      w25, #0x999a
0x0068aca0: 39 f3 a7 72              movk     w25, #0x3f99, lsl #16
0x0068aca4: 7f cb 11 94              bl       #0xafdaa0
0x0068aca8: f8 43 0c 91              add      x24, sp, #0x310
0x0068acac: 3e 07 f6 97              bl       #0x40c9a4
0x0068acb0: e0 af 40 f9              ldr      x0, [sp, #0x158]
0x0068acb4: e1 e3 08 91              add      x1, sp, #0x238
0x0068acb8: 22 00 80 52              mov      w2, #1
0x0068acbc: f9 3b 02 b9              str      w25, [sp, #0x238]
0x0068acc0: 39 07 f6 97              bl       #0x40c9a4
0x0068acc4: a0 34 11 94              bl       #0xad7f44
0x0068acc8: e0 c3 06 91              add      x0, sp, #0x1b0
0x0068accc: e1 43 0c 91              add      x1, sp, #0x310
0x0068acd0: 1a 59 10 94              bl       #0xaa1138
0x0068acd4: 70 07 11 94              bl       #0xacca94
0x0068acd8: 06 7d 80 52              mov      w6, #0x3e8
0x0068acdc: 87 0c 80 52              mov      w7, #0x64
0x0068ace0: e0 03 1b aa              mov      x0, x27
0x0068ace4: 20 1d a9 4e              mov      v0.16b, v9.16b
0x0068ace8: 01 1d a8 4e              mov      v1.16b, v8.16b
0x0068acec: 00 bc 10 94              bl       #0xab9cec
0x0068acf0: 3c 4e 11 94              bl       #0xade5e0
0x0068acf4: 00 03 17 8b              add      x0, x24, x23
0x0068acf8: 58 76 f1 97              bl       #0x2e8658
0x0068acfc: 62 3d 10 94              bl       #0xa9a284
0x0068ad00: a1 ff ff 54              b.ne     #0x68acf4
0x0068ad04: 74 31 11 94              bl       #0xad72d4
0x0068ad08: 17 06 80 52              mov      w23, #0x30
0x0068ad0c: f8 a3 0d 91              add      x24, sp, #0x368
0x0068ad10: 00 03 17 8b              add      x0, x24, x23
0x0068ad14: 51 76 f1 97              bl       #0x2e8658
0x0068ad18: 5b 3d 10 94              bl       #0xa9a284
0x0068ad1c: a1 ff ff 54              b.ne     #0x68ad10
0x0068ad20: e0 a3 07 91              add      x0, sp, #0x1e8
0x0068ad24: 4d 76 f1 97              bl       #0x2e8658
0x0068ad28: e0 03 08 91              add      x0, sp, #0x200
0x0068ad2c: 4b 76 f1 97              bl       #0x2e8658
0x0068ad30: 8f 47 11 94              bl       #0xadcb6c
0x0068ad34: 1c 21 00 35              cbnz     w28, #0x68b154
0x0068ad38: 11 6c 11 94              bl       #0xae5d7c
0x0068ad3c: f7 a3 0d 91              add      x23, sp, #0x368
0x0068ad40: c6 79 f1 97              bl       #0x2e9458
0x0068ad44: 81 38 00 b0              adrp     x1, #0xd9b000
0x0068ad48: e0 62 00 91              add      x0, x23, #0x18
0x0068ad4c: 25 e3 10 94              bl       #0xac39e0
0x0068ad50: 81 38 00 b0              adrp     x1, #0xd9b000
0x0068ad54: e0 c2 00 91              add      x0, x23, #0x30
0x0068ad58: 20 e3 10 94              bl       #0xac39d8
0x0068ad5c: e0 43 0c 91              add      x0, sp, #0x310
0x0068ad60: e1 a3 0d 91              add      x1, sp, #0x368
0x0068ad64: b7 42 10 94              bl       #0xa9b840
0x0068ad68: 18 06 80 52              mov      w24, #0x30
0x0068ad6c: e0 02 18 8b              add      x0, x23, x24
0x0068ad70: 6a 3e 10 94              bl       #0xa9a718
0x0068ad74: c1 ff ff 54              b.ne     #0x68ad6c
0x0068ad78: fd 6b 11 94              bl       #0xae5d6c
0x0068ad7c: f7 a3 0d 91              add      x23, sp, #0x368
0x0068ad80: b6 79 f1 97              bl       #0x2e9458
0x0068ad84: 81 38 00 b0              adrp     x1, #0xd9b000
0x0068ad88: e0 62 00 91              add      x0, x23, #0x18
0x0068ad8c: 11 e3 10 94              bl       #0xac39d0
0x0068ad90: 81 38 00 b0              adrp     x1, #0xd9b000
0x0068ad94: e0 c2 00 91              add      x0, x23, #0x30
0x0068ad98: 0c e3 10 94              bl       #0xac39c8
0x0068ad9c: e0 43 06 91              add      x0, sp, #0x190
0x0068ada0: e1 a3 0d 91              add      x1, sp, #0x368
0x0068ada4: a7 42 10 94              bl       #0xa9b840
0x0068ada8: 18 06 80 52              mov      w24, #0x30
0x0068adac: e0 02 18 8b              add      x0, x23, x24
0x0068adb0: 5a 3e 10 94              bl       #0xa9a718
0x0068adb4: c1 ff ff 54              b.ne     #0x68adac
0x0068adb8: e8 a3 0d 91              add      x8, sp, #0x368
0x0068adbc: 21 38 00 b0              adrp     x1, #0xd8f000
0x0068adc0: 17 21 00 91              add      x23, x8, #8
0x0068adc4: 21 a0 27 91              add      x1, x1, #0x9e8
0x0068adc8: a0 a3 02 d1              sub      x0, x29, #0xa8
0x0068adcc: 38 0d 11 94              bl       #0xace2ac
0x0068add0: f7 b7 01 f9              str      x23, [sp, #0x368]
0x0068add4: a1 79 f1 97              bl       #0x2e9458
0x0068add8: 99 f0 10 94              bl       #0xac703c
0x0068addc: fc 03 00 2a              mov      w28, w0
0x0068ade0: 01 2a 10 94              bl       #0xa955e4
0x0068ade4: bc 16 00 34              cbz      w28, #0x68b0b8
0x0068ade8: c1 38 00 d0              adrp     x1, #0xda4000
0x0068adec: c2 36 00 f0              adrp     x2, #0xd65000
0x0068adf0: 21 7c 1f 91              add      x1, x1, #0x7df
0x0068adf4: 42 90 3a 91              add      x2, x2, #0xea4
0x0068adf8: d7 05 10 94              bl       #0xa8c554
0x0068adfc: 1c 00 80 12              mov      w28, #-1
0x0068ae00: d2 00 00 14              b        #0x68b148
0x0068ae04: 16 14 80 52              mov      w22, #0xa0
0x0068ae08: 04 00 00 14              b        #0x68ae18
0x0068ae0c: 9f 8e 00 f9              str      xzr, [x20, #0x118]
0x0068ae10: 32 ff ff 17              b        #0x68aad8
0x0068ae14: 16 0f 80 52              mov      w22, #0x78
0x0068ae18: 96 2a 03 b9              str      w22, [x20, #0x328]
0x0068ae1c: 8f 2d 10 94              bl       #0xa96458
0x0068ae20: 00 16 80 52              mov      w0, #0xb0
0x0068ae24: 37 ed f0 97              bl       #0x2c6300                  ; -> _ZnwmRKSt9nothrow_t@plt
0x0068ae28: e0 0d 00 b4              cbz      x0, #0x68afe4
0x0068ae2c: db b2 10 94              bl       #0xab7998
0x0068ae30: 1f fc 0c 29              stp      wzr, wzr, [x0, #0x64]
0x0068ae34: 16 60 00 b9              str      w22, [x0, #0x60]
0x0068ae38: 80 32 01 f9              str      x0, [x20, #0x260]
0x0068ae3c: df 82 02 71              cmp      w22, #0xa0
0x0068ae40: 00 00 02 ad              stp      q0, q0, [x0, #0x40]
0x0068ae44: 00 00 01 ad              stp      q0, q0, [x0, #0x20]
0x0068ae48: 00 00 00 ad              stp      q0, q0, [x0]
0x0068ae4c: 00 80 03 ad              stp      q0, q0, [x0, #0x70]
0x0068ae50: 00 80 04 ad              stp      q0, q0, [x0, #0x90]
0x0068ae54: 00 22 80 52              mov      w0, #0x110
0x0068ae58: f8 17 9f 1a              cset     w24, eq
0x0068ae5c: 59 e9 f0 97              bl       #0x2c53c0                  ; -> _Znwm@plt
0x0068ae60: e4 c3 05 91              add      x4, sp, #0x170
0x0068ae64: e1 03 18 2a              mov      w1, w24
0x0068ae68: f0 3d 10 94              bl       #0xa9a628
0x0068ae6c: 2c 12 12 94              bl       #0xb0f71c
0x0068ae70: e8 06 40 f9              ldr      x8, [x23, #8]
0x0068ae74: f9 02 00 f9              str      x25, [x23]
0x0068ae78: 08 01 00 b5              cbnz     x8, #0x68ae98
0x0068ae7c: 14 4b 11 94              bl       #0xaddacc
0x0068ae80: e4 c3 05 91              add      x4, sp, #0x170
0x0068ae84: 22 00 80 52              mov      w2, #1
0x0068ae88: e1 03 18 2a              mov      w1, w24
0x0068ae8c: e3 03 1f 2a              mov      w3, wzr
0x0068ae90: 23 12 12 94              bl       #0xb0f71c
0x0068ae94: f9 06 00 f9              str      x25, [x23, #8]
0x0068ae98: 33 01 08 36              tbz      w19, #1, #0x68aebc
0x0068ae9c: e8 2a 40 f9              ldr      x8, [x23, #0x50]
0x0068aea0: e8 00 00 b5              cbnz     x8, #0x68aebc
0x0068aea4: 11 21 10 94              bl       #0xa932e8
0x0068aea8: e2 c3 05 91              add      x2, sp, #0x170
0x0068aeac: e1 03 1f 2a              mov      w1, wzr
0x0068aeb0: f8 03 00 aa              mov      x24, x0
0x0068aeb4: 25 2e 00 94              bl       #0x696748
0x0068aeb8: f8 2a 00 f9              str      x24, [x23, #0x50]
0x0068aebc: e8 0a 40 f9              ldr      x8, [x23, #0x10]
0x0068aec0: 08 01 00 b5              cbnz     x8, #0x68aee0
0x0068aec4: 02 4b 11 94              bl       #0xaddacc
0x0068aec8: e4 c3 05 91              add      x4, sp, #0x170
0x0068aecc: 23 00 80 52              mov      w3, #1
0x0068aed0: e1 03 18 2a              mov      w1, w24
0x0068aed4: e2 03 1f 2a              mov      w2, wzr
0x0068aed8: 11 12 12 94              bl       #0xb0f71c
0x0068aedc: f9 0a 00 f9              str      x25, [x23, #0x10]
0x0068aee0: 33 01 30 36              tbz      w19, #6, #0x68af04
0x0068aee4: e8 2e 40 f9              ldr      x8, [x23, #0x58]
0x0068aee8: e8 00 00 b5              cbnz     x8, #0x68af04
0x0068aeec: ff 20 10 94              bl       #0xa932e8
0x0068aef0: e2 c3 05 91              add      x2, sp, #0x170
0x0068aef4: 21 00 80 52              mov      w1, #1
0x0068aef8: f8 03 00 aa              mov      x24, x0
0x0068aefc: 13 2e 00 94              bl       #0x696748
0x0068af00: f8 2e 00 f9              str      x24, [x23, #0x58]
0x0068af04: 81 ee 81 b9              ldrsw    x1, [x20, #0x1ec]
0x0068af08: 08 cf 8a 52              mov      w8, #0x5678
0x0068af0c: 88 46 a2 72              movk     w8, #0x1234, lsl #16
0x0068af10: e8 66 00 b9              str      w8, [x23, #0x64]
0x0068af14: 97 62 08 91              add      x23, x20, #0x218
0x0068af18: e0 03 17 aa              mov      x0, x23
0x0068af1c: 94 0a 00 94              bl       #0x68d96c
0x0068af20: 78 5d 00 f0              adrp     x24, #0x1239000
0x0068af24: 18 f3 45 f9              ldr      x24, [x24, #0xbe0]         ; -> _ZSt7nothrow@got
0x0068af28: f6 03 1f aa              mov      x22, xzr
0x0068af2c: 81 ee 81 b9              ldrsw    x1, [x20, #0x1ec]
0x0068af30: df 02 01 eb              cmp      x22, x1
0x0068af34: 2a 02 00 54              b.ge     #0x68af78
0x0068af38: 00 9f 8b 52              mov      w0, #0x5cf8
0x0068af3c: e1 03 18 aa              mov      x1, x24
0x0068af40: f0 ec f0 97              bl       #0x2c6300                  ; -> _ZnwmRKSt9nothrow_t@plt
0x0068af44: f9 03 00 aa              mov      x25, x0
0x0068af48: a0 00 00 b4              cbz      x0, #0x68af5c
0x0068af4c: 81 32 41 f9              ldr      x1, [x20, #0x260]
0x0068af50: 82 2a 43 b9              ldr      w2, [x20, #0x328]
0x0068af54: e0 03 19 aa              mov      x0, x25
0x0068af58: b1 0a 00 94              bl       #0x68da1c
0x0068af5c: e8 02 40 f9              ldr      x8, [x23]
0x0068af60: 19 79 36 f8              str      x25, [x8, x22, lsl #3]
0x0068af64: e8 02 40 f9              ldr      x8, [x23]
0x0068af68: 00 79 76 f8              ldr      x0, [x8, x22, lsl #3]
0x0068af6c: 08 17 00 94              bl       #0x690b8c
0x0068af70: d6 06 00 91              add      x22, x22, #1
0x0068af74: ee ff ff 17              b        #0x68af2c
0x0068af78: 80 c2 08 91              add      x0, x20, #0x230
0x0068af7c: fa de 11 94              bl       #0xb02b64
0x0068af80: 81 ee 81 b9              ldrsw    x1, [x20, #0x1ec]
0x0068af84: 80 22 09 91              add      x0, x20, #0x248
0x0068af88: f7 de 11 94              bl       #0xb02b64
0x0068af8c: 81 ee 81 b9              ldrsw    x1, [x20, #0x1ec]
0x0068af90: 80 a2 02 91              add      x0, x20, #0xa8
0x0068af94: 08 00 80 12              mov      w8, #-1
0x0068af98: e2 03 09 91              add      x2, sp, #0x240
0x0068af9c: e8 43 02 b9              str      w8, [sp, #0x240]
0x0068afa0: 50 0c 00 94              bl       #0x68e0e0
0x0068afa4: ff 43 02 b9              str      wzr, [sp, #0x240]
0x0068afa8: f7 03 14 aa              mov      x23, x20
0x0068afac: 98 ee 81 b9              ldrsw    x24, [x20, #0x1ec]
0x0068afb0: e8 0e 4c f8              ldr      x8, [x23, #0xc0]!
0x0068afb4: e9 0a 40 f9              ldr      x9, [x23, #0x10]
0x0068afb8: 07 18 11 94              bl       #0xad0fd4
0x0068afbc: 89 01 00 54              b.ls     #0x68afec
0x0068afc0: e0 03 17 aa              mov      x0, x23
0x0068afc4: 2d 0e 00 94              bl       #0x68e878
0x0068afc8: 4a 13 10 94              bl       #0xa8fcf0
0x0068afcc: 07 b7 fb 97              bl       #0x578be8
0x0068afd0: 7d 35 10 94              bl       #0xa985c4
0x0068afd4: 67 84 fc 97              bl       #0x5ac170
0x0068afd8: e2 03 09 91              add      x2, sp, #0x240
0x0068afdc: 45 13 10 94              bl       #0xa8fcf0
0x0068afe0: 11 00 00 14              b        #0x68b024
0x0068afe4: 9f 32 01 f9              str      xzr, [x20, #0x260]
0x0068afe8: bc fe ff 17              b        #0x68aad8
0x0068afec: 89 66 40 f9              ldr      x9, [x20, #0xc8]
0x0068aff0: 41 48 10 94              bl       #0xa9d0f4
0x0068aff4: 3f 01 18 eb              cmp      x9, x24
0x0068aff8: 2a 31 98 9a              csel     x10, x9, x24, lo
0x0068affc: aa 00 00 b4              cbz      x10, #0x68b010
0x0068b000: eb 43 42 b9              ldr      w11, [sp, #0x240]
0x0068b004: 4a 05 00 d1              sub      x10, x10, #1
0x0068b008: 0b 45 00 b8              str      w11, [x8], #4
0x0068b00c: aa ff ff b5              cbnz     x10, #0x68b000
0x0068b010: 3f 01 18 eb              cmp      x9, x24
0x0068b014: c2 00 00 54              b.hs     #0x68b02c
0x0068b018: 01 03 09 cb              sub      x1, x24, x9
0x0068b01c: e2 03 09 91              add      x2, sp, #0x240
0x0068b020: e0 03 17 aa              mov      x0, x23
0x0068b024: 0d 0e 00 94              bl       #0x68e858
0x0068b028: 04 00 00 14              b        #0x68b038
0x0068b02c: 88 62 40 f9              ldr      x8, [x20, #0xc0]
0x0068b030: 08 09 18 8b              add      x8, x8, x24, lsl #2
0x0068b034: 88 66 00 f9              str      x8, [x20, #0xc8]
0x0068b038: 81 ee 81 b9              ldrsw    x1, [x20, #0x1ec]
0x0068b03c: e0 ab 40 f9              ldr      x0, [sp, #0x150]
0x0068b040: 3f 28 f9 97              bl       #0x4d513c
0x0068b044: 81 ee 81 b9              ldrsw    x1, [x20, #0x1ec]
0x0068b048: e0 03 14 aa              mov      x0, x20
0x0068b04c: 4b 0c 00 94              bl       #0x68e178
0x0068b050: 81 ee 81 b9              ldrsw    x1, [x20, #0x1ec]
0x0068b054: 80 c2 00 91              add      x0, x20, #0x30
0x0068b058: 6d c9 fc 97              bl       #0x5bd60c
0x0068b05c: 81 ee 81 b9              ldrsw    x1, [x20, #0x1ec]
0x0068b060: 80 22 01 91              add      x0, x20, #0x48
0x0068b064: 6c 0c 00 94              bl       #0x68e214
0x0068b068: 81 ee 81 b9              ldrsw    x1, [x20, #0x1ec]
0x0068b06c: 80 42 02 91              add      x0, x20, #0x90
0x0068b070: 93 0c 00 94              bl       #0x68e2bc
0x0068b074: 81 ee 81 b9              ldrsw    x1, [x20, #0x1ec]
0x0068b078: 80 82 01 91              add      x0, x20, #0x60
0x0068b07c: 66 0c 00 94              bl       #0x68e214
0x0068b080: 81 ee 81 b9              ldrsw    x1, [x20, #0x1ec]
0x0068b084: 80 e2 01 91              add      x0, x20, #0x78
0x0068b088: 05 5b 10 94              bl       #0xaa1c9c
0x0068b08c: 81 ee 81 b9              ldrsw    x1, [x20, #0x1ec]
0x0068b090: 80 62 00 91              add      x0, x20, #0x18
0x0068b094: 14 0d 00 94              bl       #0x68e4e4
0x0068b098: 81 ee 81 b9              ldrsw    x1, [x20, #0x1ec]
0x0068b09c: e0 03 15 aa              mov      x0, x21
0x0068b0a0: 46 0d 00 94              bl       #0x68e5b8
0x0068b0a4: fc 03 1f 2a              mov      w28, wzr
0x0068b0a8: c8 cc 80 52              mov      w8, #0x666
0x0068b0ac: 93 ae 00 f9              str      x19, [x20, #0x158]
0x0068b0b0: 88 32 05 b9              str      w8, [x20, #0x530]
0x0068b0b4: b4 fe ff 17              b        #0x68ab84
0x0068b0b8: c1 38 00 b0              adrp     x1, #0xda4000
0x0068b0bc: 21 0c 20 91              add      x1, x1, #0x803
0x0068b0c0: 6b 48 10 94              bl       #0xa9d26c
0x0068b0c4: fc 35 11 94              bl       #0xad88b4
0x0068b0c8: ff 02 00 eb              cmp      x23, x0
0x0068b0cc: 00 03 00 54              b.eq     #0x68b12c
0x0068b0d0: c1 38 00 b0              adrp     x1, #0xda4000
0x0068b0d4: 4c 98 10 94              bl       #0xab1204
0x0068b0d8: fd 35 11 94              bl       #0xad88cc
0x0068b0dc: fc 03 00 aa              mov      x28, x0
0x0068b0e0: a0 03 03 d1              sub      x0, x29, #0xc0
0x0068b0e4: 6f 75 f1 97              bl       #0x2e86a0
0x0068b0e8: 3f 29 10 94              bl       #0xa955e4
0x0068b0ec: ff 02 1c eb              cmp      x23, x28
0x0068b0f0: 00 02 00 54              b.eq     #0x68b130
0x0068b0f4: c1 38 00 b0              adrp     x1, #0xda4000
0x0068b0f8: 21 0c 20 91              add      x1, x1, #0x803
0x0068b0fc: 5c 48 10 94              bl       #0xa9d26c
