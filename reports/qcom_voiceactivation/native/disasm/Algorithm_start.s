; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libqvajni.so
; target  : address 0x14af4
; start   : 0x14af4
; size    : 0x73c
; bytes   : 0x73c   instructions: 463
; generated: 2026-10-23T23:38:32

0x00014af4: 3f 23 03 d5              paciasp  
0x00014af8: ef 3b b6 6d              stp      d15, d14, [sp, #-0xa0]!
0x00014afc: ed 33 01 6d              stp      d13, d12, [sp, #0x10]
0x00014b00: eb 2b 02 6d              stp      d11, d10, [sp, #0x20]
0x00014b04: e9 23 03 6d              stp      d9, d8, [sp, #0x30]
0x00014b08: fd 7b 04 a9              stp      x29, x30, [sp, #0x40]
0x00014b0c: fc 6f 05 a9              stp      x28, x27, [sp, #0x50]
0x00014b10: fa 67 06 a9              stp      x26, x25, [sp, #0x60]
0x00014b14: f8 5f 07 a9              stp      x24, x23, [sp, #0x70]
0x00014b18: f6 57 08 a9              stp      x22, x21, [sp, #0x80]
0x00014b1c: f4 4f 09 a9              stp      x20, x19, [sp, #0x90]
0x00014b20: fd 03 01 91              add      x29, sp, #0x40
0x00014b24: ff 83 09 d1              sub      sp, sp, #0x260
0x00014b28: 5a d0 3b d5              mrs      x26, tpidr_el0
0x00014b2c: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x00014b30: 48 17 40 f9              ldr      x8, [x26, #0x28]
0x00014b34: a8 03 1b f8              stur     x8, [x29, #-0x50]
0x00014b38: ff 23 01 f9              str      xzr, [sp, #0x240]
0x00014b3c: e0 03 04 ad              stp      q0, q0, [sp, #0x80]
0x00014b40: e0 03 05 ad              stp      q0, q0, [sp, #0xa0]
0x00014b44: e0 03 06 ad              stp      q0, q0, [sp, #0xc0]
0x00014b48: e0 03 07 ad              stp      q0, q0, [sp, #0xe0]
0x00014b4c: e0 03 08 ad              stp      q0, q0, [sp, #0x100]
0x00014b50: e0 03 09 ad              stp      q0, q0, [sp, #0x120]
0x00014b54: e0 03 0a ad              stp      q0, q0, [sp, #0x140]
0x00014b58: e0 03 0b ad              stp      q0, q0, [sp, #0x160]
0x00014b5c: e0 03 0c ad              stp      q0, q0, [sp, #0x180]
0x00014b60: e0 03 0d ad              stp      q0, q0, [sp, #0x1a0]
0x00014b64: e0 03 0e ad              stp      q0, q0, [sp, #0x1c0]
0x00014b68: e0 03 0f ad              stp      q0, q0, [sp, #0x1e0]
0x00014b6c: e0 03 10 ad              stp      q0, q0, [sp, #0x200]
0x00014b70: e0 03 11 ad              stp      q0, q0, [sp, #0x220]
0x00014b74: e0 1f 80 3d              str      q0, [sp, #0x70]
0x00014b78: 42 06 00 b4              cbz      x2, #0x14c40
0x00014b7c: 08 00 40 f9              ldr      x8, [x0]
0x00014b80: f4 03 01 aa              mov      x20, x1
0x00014b84: 81 ff ff f0              adrp     x1, #0x7000
0x00014b88: 21 3c 1b 91              add      x1, x1, #0x6cf
0x00014b8c: f5 03 02 aa              mov      x21, x2
0x00014b90: f3 03 00 aa              mov      x19, x0
0x00014b94: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x00014b98: 00 01 3f d6              blr      x8
0x00014b9c: 20 06 00 b4              cbz      x0, #0x14c60
0x00014ba0: 68 00 00 d0              adrp     x8, #0x22000
0x00014ba4: 69 02 40 f9              ldr      x9, [x19]
0x00014ba8: f8 03 00 aa              mov      x24, x0
0x00014bac: e0 03 13 aa              mov      x0, x19
0x00014bb0: e1 03 14 aa              mov      x1, x20
0x00014bb4: 02 f1 42 f9              ldr      x2, [x8, #0x5e0]
0x00014bb8: 28 95 41 f9              ldr      x8, [x9, #0x328]
0x00014bbc: 00 01 3f d6              blr      x8
0x00014bc0: f4 03 00 aa              mov      x20, x0
0x00014bc4: 41 2c 00 94              bl       #0x1fcc8
0x00014bc8: 1f 0c 00 71              cmp      w0, #3
0x00014bcc: a0 05 00 54              b.eq     #0x14c80
0x00014bd0: 1f 08 00 71              cmp      w0, #2
0x00014bd4: 60 0b 00 54              b.eq     #0x14d40
0x00014bd8: 1f 04 00 71              cmp      w0, #1
0x00014bdc: 41 2c 00 54              b.ne     #0x15164
0x00014be0: 68 02 40 f9              ldr      x8, [x19]
0x00014be4: 82 ff ff 90              adrp     x2, #0x4000
0x00014be8: 42 40 33 91              add      x2, x2, #0xcd0
0x00014bec: 83 ff ff 90              adrp     x3, #0x4000
0x00014bf0: 63 c4 2b 91              add      x3, x3, #0xaf1
0x00014bf4: e0 03 13 aa              mov      x0, x19
0x00014bf8: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x00014bfc: e1 03 18 aa              mov      x1, x24
0x00014c00: 00 01 3f d6              blr      x8
0x00014c04: c0 2e 00 b4              cbz      x0, #0x151dc
0x00014c08: 68 02 40 f9              ldr      x8, [x19]
0x00014c0c: f6 03 00 aa              mov      x22, x0
0x00014c10: 61 ff ff f0              adrp     x1, #0x3000
0x00014c14: 21 64 3f 91              add      x1, x1, #0xfd9
0x00014c18: e0 03 13 aa              mov      x0, x19
0x00014c1c: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x00014c20: 00 01 3f d6              blr      x8
0x00014c24: e3 03 00 aa              mov      x3, x0
0x00014c28: e0 03 13 aa              mov      x0, x19
0x00014c2c: e1 03 15 aa              mov      x1, x21
0x00014c30: e2 03 16 aa              mov      x2, x22
0x00014c34: c1 e7 ff 97              bl       #0xeb38
0x00014c38: e0 73 00 b9              str      w0, [sp, #0x70]
0x00014c3c: 4a 01 00 14              b        #0x15164                   ; -> Java_com_quicinc_voice_activation_algorithm_Algorithm_start+0x670
0x00014c40: a1 ff ff 90              adrp     x1, #0x8000
0x00014c44: 21 a8 0b 91              add      x1, x1, #0x2ea
0x00014c48: 82 ff ff d0              adrp     x2, #0x6000
0x00014c4c: 42 a4 1e 91              add      x2, x2, #0x7a9
0x00014c50: c0 00 80 52              mov      w0, #6
0x00014c54: e5 2a 00 94              bl       #0x1f7e8
0x00014c58: 33 00 80 12              mov      w19, #-2
0x00014c5c: 4e 01 00 14              b        #0x15194                   ; -> Java_com_quicinc_voice_activation_algorithm_Algorithm_start+0x6a0
0x00014c60: a1 ff ff 90              adrp     x1, #0x8000
0x00014c64: 21 a8 0b 91              add      x1, x1, #0x2ea
0x00014c68: a2 ff ff d0              adrp     x2, #0xa000
0x00014c6c: 42 e0 0a 91              add      x2, x2, #0x2b8
0x00014c70: c0 00 80 52              mov      w0, #6
0x00014c74: dd 2a 00 94              bl       #0x1f7e8
0x00014c78: 13 00 80 12              mov      w19, #-1
0x00014c7c: 46 01 00 14              b        #0x15194                   ; -> Java_com_quicinc_voice_activation_algorithm_Algorithm_start+0x6a0
0x00014c80: 68 02 40 f9              ldr      x8, [x19]
0x00014c84: 82 ff ff f0              adrp     x2, #0x7000
0x00014c88: 42 68 2d 91              add      x2, x2, #0xb5a
0x00014c8c: 83 ff ff d0              adrp     x3, #0x6000
0x00014c90: 63 e8 35 91              add      x3, x3, #0xd7a
0x00014c94: e0 03 13 aa              mov      x0, x19
0x00014c98: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x00014c9c: e1 03 18 aa              mov      x1, x24
0x00014ca0: 00 01 3f d6              blr      x8
0x00014ca4: 60 2a 00 b4              cbz      x0, #0x151f0
0x00014ca8: 68 02 40 f9              ldr      x8, [x19]
0x00014cac: f6 03 00 aa              mov      x22, x0
0x00014cb0: 81 ff ff b0              adrp     x1, #0x5000
0x00014cb4: 21 64 12 91              add      x1, x1, #0x499
0x00014cb8: e0 03 13 aa              mov      x0, x19
0x00014cbc: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x00014cc0: 00 01 3f d6              blr      x8
0x00014cc4: e3 03 00 aa              mov      x3, x0
0x00014cc8: e0 03 13 aa              mov      x0, x19
0x00014ccc: e1 03 15 aa              mov      x1, x21
0x00014cd0: e2 03 16 aa              mov      x2, x22
0x00014cd4: 6f e7 ff 97              bl       #0xea90
0x00014cd8: 60 24 00 b4              cbz      x0, #0x15164
0x00014cdc: 68 02 40 f9              ldr      x8, [x19]
0x00014ce0: f5 03 00 aa              mov      x21, x0
0x00014ce4: e0 03 13 aa              mov      x0, x19
0x00014ce8: e1 03 15 aa              mov      x1, x21
0x00014cec: 08 ad 42 f9              ldr      x8, [x8, #0x558]
0x00014cf0: 00 01 3f d6              blr      x8
0x00014cf4: f6 03 00 2a              mov      w22, w0
0x00014cf8: 1f 14 00 71              cmp      w0, #5
0x00014cfc: e0 00 00 54              b.eq     #0x14d18
0x00014d00: a1 ff ff 90              adrp     x1, #0x8000
0x00014d04: 21 a8 0b 91              add      x1, x1, #0x2ea
0x00014d08: a2 ff ff b0              adrp     x2, #0x9000
0x00014d0c: 42 0c 21 91              add      x2, x2, #0x843
0x00014d10: c0 00 80 52              mov      w0, #6
0x00014d14: b5 2a 00 94              bl       #0x1f7e8
0x00014d18: 68 02 40 f9              ldr      x8, [x19]
0x00014d1c: e9 c3 01 91              add      x9, sp, #0x70
0x00014d20: 24 71 03 91              add      x4, x9, #0xdc
0x00014d24: e0 03 13 aa              mov      x0, x19
0x00014d28: e1 03 15 aa              mov      x1, x21
0x00014d2c: e2 03 1f 2a              mov      w2, wzr
0x00014d30: 08 2d 43 f9              ldr      x8, [x8, #0x658]
0x00014d34: e3 03 16 2a              mov      w3, w22
0x00014d38: 00 01 3f d6              blr      x8
0x00014d3c: 0a 01 00 14              b        #0x15164                   ; -> Java_com_quicinc_voice_activation_algorithm_Algorithm_start+0x670
0x00014d40: 68 02 40 f9              ldr      x8, [x19]
0x00014d44: 82 ff ff f0              adrp     x2, #0x7000
0x00014d48: 42 84 1b 91              add      x2, x2, #0x6e1
0x00014d4c: 83 ff ff d0              adrp     x3, #0x6000
0x00014d50: 63 90 1b 91              add      x3, x3, #0x6e4
0x00014d54: e0 03 13 aa              mov      x0, x19
0x00014d58: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x00014d5c: e1 03 18 aa              mov      x1, x24
0x00014d60: 00 01 3f d6              blr      x8
0x00014d64: 00 25 00 b4              cbz      x0, #0x15204
0x00014d68: 68 02 40 f9              ldr      x8, [x19]
0x00014d6c: f6 03 00 aa              mov      x22, x0
0x00014d70: 82 ff ff f0              adrp     x2, #0x7000
0x00014d74: 42 24 38 91              add      x2, x2, #0xe09
0x00014d78: 83 ff ff d0              adrp     x3, #0x6000
0x00014d7c: 63 38 1b 91              add      x3, x3, #0x6ce
0x00014d80: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x00014d84: e0 03 13 aa              mov      x0, x19
0x00014d88: e1 03 18 aa              mov      x1, x24
0x00014d8c: 00 01 3f d6              blr      x8
0x00014d90: 40 24 00 b4              cbz      x0, #0x15218
0x00014d94: 68 02 40 f9              ldr      x8, [x19]
0x00014d98: f7 03 00 aa              mov      x23, x0
0x00014d9c: 82 ff ff 90              adrp     x2, #0x4000
0x00014da0: 42 40 33 91              add      x2, x2, #0xcd0
0x00014da4: 83 ff ff 90              adrp     x3, #0x4000
0x00014da8: 63 c4 2b 91              add      x3, x3, #0xaf1
0x00014dac: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x00014db0: e0 03 13 aa              mov      x0, x19
0x00014db4: e1 03 18 aa              mov      x1, x24
0x00014db8: 00 01 3f d6              blr      x8
0x00014dbc: 00 21 00 b4              cbz      x0, #0x151dc
0x00014dc0: 68 02 40 f9              ldr      x8, [x19]
0x00014dc4: f8 03 00 aa              mov      x24, x0
0x00014dc8: 81 ff ff 90              adrp     x1, #0x4000
0x00014dcc: 21 50 23 91              add      x1, x1, #0x8d4
0x00014dd0: e0 03 13 aa              mov      x0, x19
0x00014dd4: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x00014dd8: 00 01 3f d6              blr      x8
0x00014ddc: 68 02 40 f9              ldr      x8, [x19]
0x00014de0: f9 03 00 aa              mov      x25, x0
0x00014de4: 81 ff ff 90              adrp     x1, #0x4000
0x00014de8: 21 98 2d 91              add      x1, x1, #0xb66
0x00014dec: e0 03 13 aa              mov      x0, x19
0x00014df0: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x00014df4: 00 01 3f d6              blr      x8
0x00014df8: 68 02 40 f9              ldr      x8, [x19]
0x00014dfc: e0 37 00 f9              str      x0, [sp, #0x68]
0x00014e00: 81 ff ff f0              adrp     x1, #0x7000
0x00014e04: 21 3c 12 91              add      x1, x1, #0x48f
0x00014e08: e0 03 13 aa              mov      x0, x19
0x00014e0c: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x00014e10: 00 01 3f d6              blr      x8
0x00014e14: 68 02 40 f9              ldr      x8, [x19]
0x00014e18: e0 33 00 f9              str      x0, [sp, #0x60]
0x00014e1c: a1 ff ff b0              adrp     x1, #0x9000
0x00014e20: 21 b0 21 91              add      x1, x1, #0x86c
0x00014e24: e0 03 13 aa              mov      x0, x19
0x00014e28: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x00014e2c: 00 01 3f d6              blr      x8
0x00014e30: 68 02 40 f9              ldr      x8, [x19]
0x00014e34: e0 2f 00 f9              str      x0, [sp, #0x58]
0x00014e38: 81 ff ff b0              adrp     x1, #0x5000
0x00014e3c: 21 b0 27 91              add      x1, x1, #0x9ec
0x00014e40: e0 03 13 aa              mov      x0, x19
0x00014e44: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x00014e48: 00 01 3f d6              blr      x8
0x00014e4c: 68 02 40 f9              ldr      x8, [x19]
0x00014e50: e0 2b 00 f9              str      x0, [sp, #0x50]
0x00014e54: 81 ff ff d0              adrp     x1, #0x6000
0x00014e58: 21 7c 12 91              add      x1, x1, #0x49f
0x00014e5c: e0 03 13 aa              mov      x0, x19
0x00014e60: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x00014e64: 00 01 3f d6              blr      x8
0x00014e68: 68 02 40 f9              ldr      x8, [x19]
0x00014e6c: e0 27 00 f9              str      x0, [sp, #0x48]
0x00014e70: a1 ff ff b0              adrp     x1, #0x9000
0x00014e74: 21 d8 38 91              add      x1, x1, #0xe36
0x00014e78: e0 03 13 aa              mov      x0, x19
0x00014e7c: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x00014e80: 00 01 3f d6              blr      x8
0x00014e84: 68 02 40 f9              ldr      x8, [x19]
0x00014e88: e0 23 00 f9              str      x0, [sp, #0x40]
0x00014e8c: 81 ff ff d0              adrp     x1, #0x6000
0x00014e90: 21 70 04 91              add      x1, x1, #0x11c
0x00014e94: e0 03 13 aa              mov      x0, x19
0x00014e98: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x00014e9c: 00 01 3f d6              blr      x8
0x00014ea0: 68 02 40 f9              ldr      x8, [x19]
0x00014ea4: e0 1f 00 f9              str      x0, [sp, #0x38]
0x00014ea8: 81 ff ff b0              adrp     x1, #0x5000
0x00014eac: 21 d4 27 91              add      x1, x1, #0x9f5
0x00014eb0: e0 03 13 aa              mov      x0, x19
0x00014eb4: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x00014eb8: 00 01 3f d6              blr      x8
0x00014ebc: 68 02 40 f9              ldr      x8, [x19]
0x00014ec0: e0 1b 00 f9              str      x0, [sp, #0x30]
0x00014ec4: a1 ff ff b0              adrp     x1, #0x9000
0x00014ec8: 21 c4 17 91              add      x1, x1, #0x5f1
0x00014ecc: e0 03 13 aa              mov      x0, x19
0x00014ed0: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x00014ed4: 00 01 3f d6              blr      x8
0x00014ed8: 68 02 40 f9              ldr      x8, [x19]
0x00014edc: e0 17 00 f9              str      x0, [sp, #0x28]
0x00014ee0: 81 ff ff b0              adrp     x1, #0x5000
0x00014ee4: 21 c8 03 91              add      x1, x1, #0xf2
0x00014ee8: e0 03 13 aa              mov      x0, x19
0x00014eec: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x00014ef0: 00 01 3f d6              blr      x8
0x00014ef4: 68 02 40 f9              ldr      x8, [x19]
0x00014ef8: e0 13 00 f9              str      x0, [sp, #0x20]
0x00014efc: 81 ff ff b0              adrp     x1, #0x5000
0x00014f00: 21 e0 38 91              add      x1, x1, #0xe38
0x00014f04: e0 03 13 aa              mov      x0, x19
0x00014f08: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x00014f0c: 00 01 3f d6              blr      x8
0x00014f10: 68 02 40 f9              ldr      x8, [x19]
0x00014f14: e0 0f 00 f9              str      x0, [sp, #0x18]
0x00014f18: 81 ff ff f0              adrp     x1, #0x7000
0x00014f1c: 21 ac 39 91              add      x1, x1, #0xe6b
0x00014f20: e0 03 13 aa              mov      x0, x19
0x00014f24: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x00014f28: 00 01 3f d6              blr      x8
0x00014f2c: 68 02 40 f9              ldr      x8, [x19]
0x00014f30: e0 0b 00 f9              str      x0, [sp, #0x10]
0x00014f34: a1 ff ff 90              adrp     x1, #0x8000
0x00014f38: 21 28 33 91              add      x1, x1, #0xcca
0x00014f3c: e0 03 13 aa              mov      x0, x19
0x00014f40: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x00014f44: 00 01 3f d6              blr      x8
0x00014f48: 68 02 40 f9              ldr      x8, [x19]
0x00014f4c: e0 07 00 f9              str      x0, [sp, #8]
0x00014f50: a1 ff ff b0              adrp     x1, #0x9000
0x00014f54: 21 f4 17 91              add      x1, x1, #0x5fd
0x00014f58: e0 03 13 aa              mov      x0, x19
0x00014f5c: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x00014f60: 00 01 3f d6              blr      x8
0x00014f64: 68 02 40 f9              ldr      x8, [x19]
0x00014f68: fb 03 00 aa              mov      x27, x0
0x00014f6c: a1 ff ff 90              adrp     x1, #0x8000
0x00014f70: 21 50 33 91              add      x1, x1, #0xcd4
0x00014f74: e0 03 13 aa              mov      x0, x19
0x00014f78: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x00014f7c: 00 01 3f d6              blr      x8
0x00014f80: fc 03 00 aa              mov      x28, x0
0x00014f84: e0 03 13 aa              mov      x0, x19
0x00014f88: e1 03 15 aa              mov      x1, x21
0x00014f8c: e2 03 17 aa              mov      x2, x23
0x00014f90: e3 03 19 aa              mov      x3, x25
0x00014f94: 43 e8 ff 97              bl       #0xf0a0
0x00014f98: e0 03 13 aa              mov      x0, x19
0x00014f9c: e1 03 15 aa              mov      x1, x21
0x00014fa0: e2 03 16 aa              mov      x2, x22
0x00014fa4: e3 37 40 f9              ldr      x3, [sp, #0x68]
0x00014fa8: e0 07 00 bd              str      s0, [sp, #4]
0x00014fac: d9 e4 ff 97              bl       #0xe310
0x00014fb0: f9 03 00 2a              mov      w25, w0
0x00014fb4: e0 03 13 aa              mov      x0, x19
0x00014fb8: e1 03 15 aa              mov      x1, x21
0x00014fbc: e2 03 17 aa              mov      x2, x23
0x00014fc0: e3 33 40 f9              ldr      x3, [sp, #0x60]
0x00014fc4: 37 e8 ff 97              bl       #0xf0a0
0x00014fc8: e0 03 13 aa              mov      x0, x19
0x00014fcc: e1 03 15 aa              mov      x1, x21
0x00014fd0: e2 03 17 aa              mov      x2, x23
0x00014fd4: e3 2f 40 f9              ldr      x3, [sp, #0x58]
0x00014fd8: e0 6b 00 bd              str      s0, [sp, #0x68]
0x00014fdc: 31 e8 ff 97              bl       #0xf0a0
0x00014fe0: e0 03 13 aa              mov      x0, x19
0x00014fe4: e1 03 15 aa              mov      x1, x21
0x00014fe8: e2 03 17 aa              mov      x2, x23
0x00014fec: e3 2b 40 f9              ldr      x3, [sp, #0x50]
0x00014ff0: e0 63 00 bd              str      s0, [sp, #0x60]
0x00014ff4: 2b e8 ff 97              bl       #0xf0a0
0x00014ff8: e0 03 13 aa              mov      x0, x19
0x00014ffc: e1 03 15 aa              mov      x1, x21
0x00015000: e2 03 17 aa              mov      x2, x23
0x00015004: e3 27 40 f9              ldr      x3, [sp, #0x48]
0x00015008: e0 5b 00 bd              str      s0, [sp, #0x58]
0x0001500c: 25 e8 ff 97              bl       #0xf0a0
0x00015010: e0 03 13 aa              mov      x0, x19
0x00015014: e1 03 15 aa              mov      x1, x21
0x00015018: e2 03 17 aa              mov      x2, x23
0x0001501c: e3 23 40 f9              ldr      x3, [sp, #0x40]
0x00015020: e0 53 00 bd              str      s0, [sp, #0x50]
0x00015024: 1f e8 ff 97              bl       #0xf0a0
0x00015028: e0 03 13 aa              mov      x0, x19
0x0001502c: e1 03 15 aa              mov      x1, x21
0x00015030: e2 03 17 aa              mov      x2, x23
0x00015034: e3 1f 40 f9              ldr      x3, [sp, #0x38]
0x00015038: 0d 40 20 1e              fmov     s13, s0
0x0001503c: 19 e8 ff 97              bl       #0xf0a0
0x00015040: e0 03 13 aa              mov      x0, x19
0x00015044: e1 03 15 aa              mov      x1, x21
0x00015048: e2 03 17 aa              mov      x2, x23
0x0001504c: e3 1b 40 f9              ldr      x3, [sp, #0x30]
0x00015050: 0e 40 20 1e              fmov     s14, s0
0x00015054: 13 e8 ff 97              bl       #0xf0a0
0x00015058: e0 03 13 aa              mov      x0, x19
0x0001505c: e1 03 15 aa              mov      x1, x21
0x00015060: e2 03 17 aa              mov      x2, x23
0x00015064: e3 17 40 f9              ldr      x3, [sp, #0x28]
0x00015068: 0f 40 20 1e              fmov     s15, s0
0x0001506c: 0d e8 ff 97              bl       #0xf0a0
0x00015070: e0 03 13 aa              mov      x0, x19
0x00015074: e1 03 15 aa              mov      x1, x21
0x00015078: e2 03 17 aa              mov      x2, x23
0x0001507c: e3 13 40 f9              ldr      x3, [sp, #0x20]
0x00015080: 08 40 20 1e              fmov     s8, s0
0x00015084: 07 e8 ff 97              bl       #0xf0a0
0x00015088: e0 03 13 aa              mov      x0, x19
0x0001508c: e1 03 15 aa              mov      x1, x21
0x00015090: e2 03 17 aa              mov      x2, x23
0x00015094: e3 0f 40 f9              ldr      x3, [sp, #0x18]
0x00015098: 09 40 20 1e              fmov     s9, s0
0x0001509c: 01 e8 ff 97              bl       #0xf0a0
0x000150a0: e0 03 13 aa              mov      x0, x19
0x000150a4: e1 03 15 aa              mov      x1, x21
0x000150a8: e2 03 17 aa              mov      x2, x23
0x000150ac: e3 0b 40 f9              ldr      x3, [sp, #0x10]
0x000150b0: 0a 40 20 1e              fmov     s10, s0
0x000150b4: fb e7 ff 97              bl       #0xf0a0
0x000150b8: e0 03 13 aa              mov      x0, x19
0x000150bc: e1 03 15 aa              mov      x1, x21
0x000150c0: e2 03 16 aa              mov      x2, x22
0x000150c4: e3 07 40 f9              ldr      x3, [sp, #8]
0x000150c8: 0b 40 20 1e              fmov     s11, s0
0x000150cc: 91 e4 ff 97              bl       #0xe310
0x000150d0: f6 03 00 2a              mov      w22, w0
0x000150d4: e0 03 13 aa              mov      x0, x19
0x000150d8: e1 03 15 aa              mov      x1, x21
0x000150dc: e2 03 17 aa              mov      x2, x23
0x000150e0: e3 03 1b aa              mov      x3, x27
0x000150e4: ef e7 ff 97              bl       #0xf0a0
0x000150e8: e0 03 13 aa              mov      x0, x19
0x000150ec: e1 03 15 aa              mov      x1, x21
0x000150f0: e2 03 18 aa              mov      x2, x24
0x000150f4: e3 03 1c aa              mov      x3, x28
0x000150f8: 0c 40 20 1e              fmov     s12, s0
0x000150fc: 8f e6 ff 97              bl       #0xeb38
0x00015100: e0 07 40 bd              ldr      s0, [sp, #4]
0x00015104: 3f 1f 00 72              tst      w25, #0xff
0x00015108: e9 07 9f 1a              cset     w9, ne
0x0001510c: df 1e 00 72              tst      w22, #0xff
0x00015110: ed 23 02 bd              str      s13, [sp, #0x220]
0x00015114: 08 00 38 1e              fcvtzs   w8, s0
0x00015118: e0 6b 40 bd              ldr      s0, [sp, #0x68]
0x0001511c: ee 27 02 bd              str      s14, [sp, #0x224]
0x00015120: ef 2b 02 bd              str      s15, [sp, #0x228]
0x00015124: e0 7f 00 bd              str      s0, [sp, #0x7c]
0x00015128: e0 63 40 bd              ldr      s0, [sp, #0x60]
0x0001512c: e8 a7 0e 29              stp      w8, w9, [sp, #0x74]
0x00015130: e8 07 9f 1a              cset     w8, ne
0x00015134: e8 2f 02 bd              str      s8, [sp, #0x22c]
0x00015138: e0 17 02 bd              str      s0, [sp, #0x214]
0x0001513c: e0 5b 40 bd              ldr      s0, [sp, #0x58]
0x00015140: e9 33 02 bd              str      s9, [sp, #0x230]
0x00015144: ea 37 02 bd              str      s10, [sp, #0x234]
0x00015148: e0 1b 02 bd              str      s0, [sp, #0x218]
0x0001514c: e0 53 40 bd              ldr      s0, [sp, #0x50]
0x00015150: eb 3b 02 bd              str      s11, [sp, #0x238]
0x00015154: e8 3f 02 b9              str      w8, [sp, #0x23c]
0x00015158: e0 1f 02 bd              str      s0, [sp, #0x21c]
0x0001515c: ec 43 02 bd              str      s12, [sp, #0x240]
0x00015160: e0 47 02 b9              str      w0, [sp, #0x244]
0x00015164: e1 c3 01 91              add      x1, sp, #0x70
0x00015168: e0 03 14 aa              mov      x0, x20
0x0001516c: b9 2a 00 94              bl       #0x1fc50                   ; -> _ZN9Algorithm5startEP20algorithm_parameters@plt
0x00015170: f3 03 00 2a              mov      w19, w0
0x00015174: 00 01 00 34              cbz      w0, #0x15194
0x00015178: 81 ff ff f0              adrp     x1, #0x8000
0x0001517c: 21 a8 0b 91              add      x1, x1, #0x2ea
0x00015180: 82 ff ff b0              adrp     x2, #0x6000
0x00015184: 42 d4 3b 91              add      x2, x2, #0xef5
0x00015188: c0 00 80 52              mov      w0, #6
0x0001518c: e3 03 13 2a              mov      w3, w19
0x00015190: 96 29 00 94              bl       #0x1f7e8
0x00015194: 48 17 40 f9              ldr      x8, [x26, #0x28]
0x00015198: a9 03 5b f8              ldur     x9, [x29, #-0x50]
0x0001519c: 1f 01 09 eb              cmp      x8, x9
0x000151a0: 61 04 00 54              b.ne     #0x1522c
0x000151a4: e0 03 13 2a              mov      w0, w19
0x000151a8: ff 83 09 91              add      sp, sp, #0x260
0x000151ac: f4 4f 49 a9              ldp      x20, x19, [sp, #0x90]
0x000151b0: f6 57 48 a9              ldp      x22, x21, [sp, #0x80]
0x000151b4: f8 5f 47 a9              ldp      x24, x23, [sp, #0x70]
0x000151b8: fa 67 46 a9              ldp      x26, x25, [sp, #0x60]
0x000151bc: fc 6f 45 a9              ldp      x28, x27, [sp, #0x50]
0x000151c0: fd 7b 44 a9              ldp      x29, x30, [sp, #0x40]
0x000151c4: e9 23 43 6d              ldp      d9, d8, [sp, #0x30]
0x000151c8: eb 2b 42 6d              ldp      d11, d10, [sp, #0x20]
0x000151cc: ed 33 41 6d              ldp      d13, d12, [sp, #0x10]
0x000151d0: ef 3b ca 6c              ldp      d15, d14, [sp], #0xa0
0x000151d4: bf 23 03 d5              autiasp  
0x000151d8: c0 03 5f d6              ret      
0x000151dc: 81 ff ff f0              adrp     x1, #0x8000
0x000151e0: 21 a8 0b 91              add      x1, x1, #0x2ea
0x000151e4: 82 ff ff b0              adrp     x2, #0x6000
0x000151e8: 42 e8 3a 91              add      x2, x2, #0xeba
0x000151ec: a1 fe ff 17              b        #0x14c70                   ; -> Java_com_quicinc_voice_activation_algorithm_Algorithm_start+0x17c
0x000151f0: 81 ff ff f0              adrp     x1, #0x8000
0x000151f4: 21 a8 0b 91              add      x1, x1, #0x2ea
0x000151f8: 82 ff ff 90              adrp     x2, #0x5000
0x000151fc: 42 c8 02 91              add      x2, x2, #0xb2
0x00015200: 9c fe ff 17              b        #0x14c70                   ; -> Java_com_quicinc_voice_activation_algorithm_Algorithm_start+0x17c
0x00015204: 81 ff ff f0              adrp     x1, #0x8000
0x00015208: 21 a8 0b 91              add      x1, x1, #0x2ea
0x0001520c: 62 ff ff f0              adrp     x2, #0x4000
0x00015210: 42 54 22 91              add      x2, x2, #0x895
0x00015214: 97 fe ff 17              b        #0x14c70                   ; -> Java_com_quicinc_voice_activation_algorithm_Algorithm_start+0x17c
0x00015218: 81 ff ff f0              adrp     x1, #0x8000
0x0001521c: 21 a8 0b 91              add      x1, x1, #0x2ea
0x00015220: a2 ff ff 90              adrp     x2, #0x9000
0x00015224: 42 e4 37 91              add      x2, x2, #0xdf9
0x00015228: 92 fe ff 17              b        #0x14c70                   ; -> Java_com_quicinc_voice_activation_algorithm_Algorithm_start+0x17c
0x0001522c: ab 29 00 94              bl       #0x1f8d8
