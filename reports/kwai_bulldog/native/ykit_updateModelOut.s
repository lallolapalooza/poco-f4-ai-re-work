; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/kwai_bulldog/native/libs/libykit.so
; target  : address 0xc8a54
; start   : 0xc8a54
; size    : 0x33f4
; bytes   : 0x33f4   instructions: 3325
; generated: 2026-10-24T01:06:39

0x000c8a54: ff c3 07 d1              sub      sp, sp, #0x1f0
0x000c8a58: fc 6f 19 a9              stp      x28, x27, [sp, #0x190]
0x000c8a5c: fa 67 1a a9              stp      x26, x25, [sp, #0x1a0]
0x000c8a60: f8 5f 1b a9              stp      x24, x23, [sp, #0x1b0]
0x000c8a64: f6 57 1c a9              stp      x22, x21, [sp, #0x1c0]
0x000c8a68: f4 4f 1d a9              stp      x20, x19, [sp, #0x1d0]
0x000c8a6c: fd 7b 1e a9              stp      x29, x30, [sp, #0x1e0]
0x000c8a70: fd 83 07 91              add      x29, sp, #0x1e0
0x000c8a74: 53 d0 3b d5              mrs      x19, tpidr_el0
0x000c8a78: 68 16 40 f9              ldr      x8, [x19, #0x28]
0x000c8a7c: 41 3b 00 f0              adrp     x1, #0x833000
0x000c8a80: 21 40 2d 91              add      x1, x1, #0xb50
0x000c8a84: f4 03 00 aa              mov      x20, x0
0x000c8a88: e8 5b 00 f9              str      x8, [sp, #0xb0]
0x000c8a8c: 08 00 40 f9              ldr      x8, [x0]
0x000c8a90: f7 03 02 aa              mov      x23, x2
0x000c8a94: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000c8a98: 00 01 3f d6              blr      x8
0x000c8a9c: 88 02 40 f9              ldr      x8, [x20]
0x000c8aa0: 42 3b 00 f0              adrp     x2, #0x833000
0x000c8aa4: 43 3b 00 f0              adrp     x3, #0x833000
0x000c8aa8: e1 03 00 aa              mov      x1, x0
0x000c8aac: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c8ab0: 42 74 30 91              add      x2, x2, #0xc1d
0x000c8ab4: 63 b4 30 91              add      x3, x3, #0xc2d
0x000c8ab8: e0 03 14 aa              mov      x0, x20
0x000c8abc: 00 01 3f d6              blr      x8
0x000c8ac0: 88 02 40 f9              ldr      x8, [x20]
0x000c8ac4: e2 03 00 aa              mov      x2, x0
0x000c8ac8: e0 03 14 aa              mov      x0, x20
0x000c8acc: e1 03 17 aa              mov      x1, x23
0x000c8ad0: 08 95 41 f9              ldr      x8, [x8, #0x328]
0x000c8ad4: 00 01 3f d6              blr      x8
0x000c8ad8: 60 3d 01 b4              cbz      x0, #0xcb284
0x000c8adc: 08 9c 40 f9              ldr      x8, [x0, #0x138]
0x000c8ae0: f5 03 00 aa              mov      x21, x0
0x000c8ae4: 08 3d 01 b4              cbz      x8, #0xcb284
0x000c8ae8: a0 22 00 91              add      x0, x21, #8
0x000c8aec: f3 83 04 a9              stp      x19, x0, [sp, #0x48]
0x000c8af0: b8 cc ff 97              bl       #0xbbdd0                   ; -> _ZNSt6__ndk15mutex4lockEv@plt
0x000c8af4: 88 02 40 f9              ldr      x8, [x20]
0x000c8af8: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000c8afc: 41 3b 00 f0              adrp     x1, #0x833000
0x000c8b00: 21 40 2d 91              add      x1, x1, #0xb50
0x000c8b04: e0 03 14 aa              mov      x0, x20
0x000c8b08: 00 01 3f d6              blr      x8
0x000c8b0c: 88 02 40 f9              ldr      x8, [x20]
0x000c8b10: f3 03 00 aa              mov      x19, x0
0x000c8b14: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c8b18: 62 3b 00 90              adrp     x2, #0x834000
0x000c8b1c: 43 3b 00 f0              adrp     x3, #0x833000
0x000c8b20: 42 58 16 91              add      x2, x2, #0x596
0x000c8b24: 63 fc 33 91              add      x3, x3, #0xcff
0x000c8b28: e0 03 14 aa              mov      x0, x20
0x000c8b2c: e1 03 13 aa              mov      x1, x19
0x000c8b30: 00 01 3f d6              blr      x8
0x000c8b34: 88 02 40 f9              ldr      x8, [x20]
0x000c8b38: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c8b3c: 62 3b 00 90              adrp     x2, #0x834000
0x000c8b40: 63 3b 00 90              adrp     x3, #0x834000
0x000c8b44: 42 94 16 91              add      x2, x2, #0x5a5
0x000c8b48: 63 b8 16 91              add      x3, x3, #0x5ae
0x000c8b4c: e0 03 14 aa              mov      x0, x20
0x000c8b50: e1 03 13 aa              mov      x1, x19
0x000c8b54: 00 01 3f d6              blr      x8
0x000c8b58: 88 02 40 f9              ldr      x8, [x20]
0x000c8b5c: e2 03 00 aa              mov      x2, x0
0x000c8b60: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000c8b64: e0 03 14 aa              mov      x0, x20
0x000c8b68: e1 03 17 aa              mov      x1, x23
0x000c8b6c: 00 01 3f d6              blr      x8
0x000c8b70: a8 52 40 b9              ldr      w8, [x21, #0x50]
0x000c8b74: 1f d9 00 71              cmp      w8, #0x36
0x000c8b78: 08 38 01 54              b.hi     #0xcb278
0x000c8b7c: 49 3b 00 f0              adrp     x9, #0x833000
0x000c8b80: 29 59 2b 91              add      x9, x9, #0xad6
0x000c8b84: 8a 00 00 10              adr      x10, #0xc8b94
0x000c8b88: 2b 79 68 78              ldrh     w11, [x9, x8, lsl #1]
0x000c8b8c: 4a 09 0b 8b              add      x10, x10, x11, lsl #2
0x000c8b90: 40 01 1f d6              br       x10
0x000c8b94: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x000c8b98: e8 67 00 91              add      x8, sp, #0x19
0x000c8b9c: ff f3 00 b9              str      wzr, [sp, #0xf0]
0x000c8ba0: e0 83 06 ad              stp      q0, q0, [sp, #0xd0]
0x000c8ba4: 00 f1 8f 3c              stur     q0, [x8, #0xff]
0x000c8ba8: e8 27 00 91              add      x8, sp, #9
0x000c8bac: ff 97 00 f9              str      xzr, [sp, #0x128]
0x000c8bb0: 00 f1 8f 3c              stur     q0, [x8, #0xff]
0x000c8bb4: e0 83 8f 3c              stur     q0, [sp, #0xf8]
0x000c8bb8: a0 9e 40 f9              ldr      x0, [x21, #0x138]
0x000c8bbc: e1 e3 02 91              add      x1, sp, #0xb8
0x000c8bc0: c4 d2 ff 97              bl       #0xbd6d0                   ; -> _ZN5ycnn29YCNNModel10getMattingEPNS_12KSMattingOutE@plt
0x000c8bc4: 88 02 40 f9              ldr      x8, [x20]
0x000c8bc8: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000c8bcc: 61 3b 00 90              adrp     x1, #0x834000
0x000c8bd0: 21 f4 20 91              add      x1, x1, #0x83d
0x000c8bd4: e0 03 14 aa              mov      x0, x20
0x000c8bd8: 00 01 3f d6              blr      x8
0x000c8bdc: 88 02 40 f9              ldr      x8, [x20]
0x000c8be0: f8 03 00 aa              mov      x24, x0
0x000c8be4: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000c8be8: 61 3b 00 90              adrp     x1, #0x834000
0x000c8bec: 21 c0 21 91              add      x1, x1, #0x870
0x000c8bf0: e0 03 14 aa              mov      x0, x20
0x000c8bf4: 00 01 3f d6              blr      x8
0x000c8bf8: 88 02 40 f9              ldr      x8, [x20]
0x000c8bfc: f6 03 00 aa              mov      x22, x0
0x000c8c00: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000c8c04: 61 3b 00 90              adrp     x1, #0x834000
0x000c8c08: 21 90 22 91              add      x1, x1, #0x8a4
0x000c8c0c: e0 03 14 aa              mov      x0, x20
0x000c8c10: 00 01 3f d6              blr      x8
0x000c8c14: 88 02 40 f9              ldr      x8, [x20]
0x000c8c18: fc 03 00 aa              mov      x28, x0
0x000c8c1c: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c8c20: 62 3b 00 90              adrp     x2, #0x834000
0x000c8c24: 63 3b 00 90              adrp     x3, #0x834000
0x000c8c28: 42 34 23 91              add      x2, x2, #0x8cd
0x000c8c2c: 63 64 23 91              add      x3, x3, #0x8d9
0x000c8c30: e0 03 14 aa              mov      x0, x20
0x000c8c34: e1 03 13 aa              mov      x1, x19
0x000c8c38: 00 01 3f d6              blr      x8
0x000c8c3c: 88 02 40 f9              ldr      x8, [x20]
0x000c8c40: e2 03 00 aa              mov      x2, x0
0x000c8c44: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000c8c48: e0 03 14 aa              mov      x0, x20
0x000c8c4c: e1 03 17 aa              mov      x1, x23
0x000c8c50: 00 01 3f d6              blr      x8
0x000c8c54: 88 02 40 f9              ldr      x8, [x20]
0x000c8c58: f9 03 00 aa              mov      x25, x0
0x000c8c5c: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c8c60: 62 3b 00 90              adrp     x2, #0x834000
0x000c8c64: 43 3b 00 f0              adrp     x3, #0x833000
0x000c8c68: 42 38 24 91              add      x2, x2, #0x90e
0x000c8c6c: 63 e4 31 91              add      x3, x3, #0xc79
0x000c8c70: e0 03 14 aa              mov      x0, x20
0x000c8c74: e1 03 18 aa              mov      x1, x24
0x000c8c78: 00 01 3f d6              blr      x8
0x000c8c7c: 88 02 40 f9              ldr      x8, [x20]
0x000c8c80: e2 03 00 aa              mov      x2, x0
0x000c8c84: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000c8c88: e0 03 14 aa              mov      x0, x20
0x000c8c8c: e1 03 19 aa              mov      x1, x25
0x000c8c90: 00 01 3f d6              blr      x8
0x000c8c94: e0 43 00 f9              str      x0, [sp, #0x80]
0x000c8c98: 88 02 40 f9              ldr      x8, [x20]
0x000c8c9c: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c8ca0: 02 3d 00 b0              adrp     x2, #0x869000
0x000c8ca4: 43 3b 00 f0              adrp     x3, #0x833000
0x000c8ca8: 42 cc 3e 91              add      x2, x2, #0xfb3
0x000c8cac: 63 e4 31 91              add      x3, x3, #0xc79
0x000c8cb0: e0 03 14 aa              mov      x0, x20
0x000c8cb4: e1 03 16 aa              mov      x1, x22
0x000c8cb8: 00 01 3f d6              blr      x8
0x000c8cbc: e0 3b 00 f9              str      x0, [sp, #0x70]
0x000c8cc0: 88 02 40 f9              ldr      x8, [x20]
0x000c8cc4: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c8cc8: 82 3b 00 d0              adrp     x2, #0x83a000
0x000c8ccc: 43 3b 00 f0              adrp     x3, #0x833000
0x000c8cd0: 42 28 28 91              add      x2, x2, #0xa0a
0x000c8cd4: 63 b4 30 91              add      x3, x3, #0xc2d
0x000c8cd8: e0 03 14 aa              mov      x0, x20
0x000c8cdc: e1 03 16 aa              mov      x1, x22
0x000c8ce0: 00 01 3f d6              blr      x8
0x000c8ce4: e0 37 00 f9              str      x0, [sp, #0x68]
0x000c8ce8: 88 02 40 f9              ldr      x8, [x20]
0x000c8cec: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c8cf0: 82 3b 00 b0              adrp     x2, #0x839000
0x000c8cf4: 63 3b 00 f0              adrp     x3, #0x837000
0x000c8cf8: 42 10 21 91              add      x2, x2, #0x844
0x000c8cfc: 63 d4 26 91              add      x3, x3, #0x9b5
0x000c8d00: e0 03 14 aa              mov      x0, x20
0x000c8d04: e1 03 1c aa              mov      x1, x28
0x000c8d08: 00 01 3f d6              blr      x8
0x000c8d0c: e0 4f 00 f9              str      x0, [sp, #0x98]
0x000c8d10: 88 02 40 f9              ldr      x8, [x20]
0x000c8d14: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c8d18: a2 3c 00 f0              adrp     x2, #0x85f000
0x000c8d1c: 63 3b 00 f0              adrp     x3, #0x837000
0x000c8d20: 42 bc 3a 91              add      x2, x2, #0xeaf
0x000c8d24: 63 d4 26 91              add      x3, x3, #0x9b5
0x000c8d28: e0 03 14 aa              mov      x0, x20
0x000c8d2c: e1 03 1c aa              mov      x1, x28
0x000c8d30: 00 01 3f d6              blr      x8
0x000c8d34: e0 4b 00 f9              str      x0, [sp, #0x90]
0x000c8d38: 88 02 40 f9              ldr      x8, [x20]
0x000c8d3c: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000c8d40: 41 3b 00 f0              adrp     x1, #0x833000
0x000c8d44: 21 40 32 91              add      x1, x1, #0xc90
0x000c8d48: e0 03 14 aa              mov      x0, x20
0x000c8d4c: 00 01 3f d6              blr      x8
0x000c8d50: 88 02 40 f9              ldr      x8, [x20]
0x000c8d54: f5 03 00 aa              mov      x21, x0
0x000c8d58: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000c8d5c: 62 3b 00 90              adrp     x2, #0x834000
0x000c8d60: 43 3b 00 f0              adrp     x3, #0x833000
0x000c8d64: 42 1c 1d 91              add      x2, x2, #0x747
0x000c8d68: 63 0c 30 91              add      x3, x3, #0xc03
0x000c8d6c: e0 03 14 aa              mov      x0, x20
0x000c8d70: e1 03 15 aa              mov      x1, x21
0x000c8d74: 00 01 3f d6              blr      x8
0x000c8d78: 88 02 40 f9              ldr      x8, [x20]
0x000c8d7c: f3 03 00 aa              mov      x19, x0
0x000c8d80: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000c8d84: 62 3b 00 d0              adrp     x2, #0x836000
0x000c8d88: 43 3b 00 f0              adrp     x3, #0x833000
0x000c8d8c: 42 3c 02 91              add      x2, x2, #0x8f
0x000c8d90: 63 94 32 91              add      x3, x3, #0xca5
0x000c8d94: e0 03 14 aa              mov      x0, x20
0x000c8d98: e1 03 15 aa              mov      x1, x21
0x000c8d9c: 00 01 3f d6              blr      x8
0x000c8da0: 88 02 40 f9              ldr      x8, [x20]
0x000c8da4: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000c8da8: c2 3d 00 90              adrp     x2, #0x880000
0x000c8dac: 43 3b 00 f0              adrp     x3, #0x833000
0x000c8db0: 42 78 3b 91              add      x2, x2, #0xede
0x000c8db4: 63 a4 32 91              add      x3, x3, #0xca9
0x000c8db8: e0 03 14 aa              mov      x0, x20
0x000c8dbc: e1 03 15 aa              mov      x1, x21
0x000c8dc0: 00 01 3f d6              blr      x8
0x000c8dc4: 88 02 40 f9              ldr      x8, [x20]
0x000c8dc8: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000c8dcc: 02 3e 00 90              adrp     x2, #0x888000
0x000c8dd0: 63 3b 00 90              adrp     x3, #0x834000
0x000c8dd4: 42 40 30 91              add      x2, x2, #0xc10
0x000c8dd8: 63 34 1d 91              add      x3, x3, #0x74d
0x000c8ddc: e0 03 14 aa              mov      x0, x20
0x000c8de0: e1 03 15 aa              mov      x1, x21
0x000c8de4: 00 01 3f d6              blr      x8
0x000c8de8: e0 57 00 f9              str      x0, [sp, #0xa8]
0x000c8dec: 88 02 40 f9              ldr      x8, [x20]
0x000c8df0: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000c8df4: 42 3b 00 f0              adrp     x2, #0x833000
0x000c8df8: 43 3b 00 f0              adrp     x3, #0x833000
0x000c8dfc: 42 f0 2f 91              add      x2, x2, #0xbfc
0x000c8e00: 63 0c 30 91              add      x3, x3, #0xc03
0x000c8e04: e0 03 14 aa              mov      x0, x20
0x000c8e08: e1 03 16 aa              mov      x1, x22
0x000c8e0c: 00 01 3f d6              blr      x8
0x000c8e10: e0 3f 00 f9              str      x0, [sp, #0x78]
0x000c8e14: 88 02 40 f9              ldr      x8, [x20]
0x000c8e18: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000c8e1c: 42 3b 00 f0              adrp     x2, #0x833000
0x000c8e20: 43 3b 00 f0              adrp     x3, #0x833000
0x000c8e24: 42 f0 2f 91              add      x2, x2, #0xbfc
0x000c8e28: 63 0c 30 91              add      x3, x3, #0xc03
0x000c8e2c: e0 03 14 aa              mov      x0, x20
0x000c8e30: e1 03 1c aa              mov      x1, x28
0x000c8e34: 00 01 3f d6              blr      x8
0x000c8e38: e0 53 00 f9              str      x0, [sp, #0xa0]
0x000c8e3c: e1 43 40 f9              ldr      x1, [sp, #0x80]
0x000c8e40: e0 03 14 aa              mov      x0, x20
0x000c8e44: e2 03 13 aa              mov      x2, x19
0x000c8e48: 7b f0 ff 97              bl       #0xc5034
0x000c8e4c: 88 02 40 f9              ldr      x8, [x20]
0x000c8e50: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000c8e54: 41 3b 00 f0              adrp     x1, #0x833000
0x000c8e58: 21 4c 2f 91              add      x1, x1, #0xbd3
0x000c8e5c: e0 03 14 aa              mov      x0, x20
0x000c8e60: 00 01 3f d6              blr      x8
0x000c8e64: 88 02 40 f9              ldr      x8, [x20]
0x000c8e68: f7 03 00 aa              mov      x23, x0
0x000c8e6c: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c8e70: 42 3c 00 f0              adrp     x2, #0x853000
0x000c8e74: 63 3b 00 90              adrp     x3, #0x834000
0x000c8e78: 42 28 36 91              add      x2, x2, #0xd8a
0x000c8e7c: 63 5c 24 91              add      x3, x3, #0x917
0x000c8e80: e0 03 14 aa              mov      x0, x20
0x000c8e84: e1 03 18 aa              mov      x1, x24
0x000c8e88: 00 01 3f d6              blr      x8
0x000c8e8c: 88 02 40 f9              ldr      x8, [x20]
0x000c8e90: e2 03 00 aa              mov      x2, x0
0x000c8e94: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000c8e98: e0 03 14 aa              mov      x0, x20
0x000c8e9c: e1 03 19 aa              mov      x1, x25
0x000c8ea0: 00 01 3f d6              blr      x8
0x000c8ea4: 88 02 40 f9              ldr      x8, [x20]
0x000c8ea8: f3 03 00 aa              mov      x19, x0
0x000c8eac: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c8eb0: 02 3e 00 90              adrp     x2, #0x888000
0x000c8eb4: 43 3b 00 f0              adrp     x3, #0x833000
0x000c8eb8: 42 cc 0b 91              add      x2, x2, #0x2f3
0x000c8ebc: 63 9c 32 91              add      x3, x3, #0xca7
0x000c8ec0: e0 03 14 aa              mov      x0, x20
0x000c8ec4: e1 03 17 aa              mov      x1, x23
0x000c8ec8: 00 01 3f d6              blr      x8
0x000c8ecc: 88 02 40 f9              ldr      x8, [x20]
0x000c8ed0: e3 eb 40 b9              ldr      w3, [sp, #0xe8]
0x000c8ed4: e2 03 00 aa              mov      x2, x0
0x000c8ed8: 08 b5 41 f9              ldr      x8, [x8, #0x368]
0x000c8edc: e0 03 14 aa              mov      x0, x20
0x000c8ee0: e1 03 13 aa              mov      x1, x19
0x000c8ee4: 00 01 3f d6              blr      x8
0x000c8ee8: 88 02 40 f9              ldr      x8, [x20]
0x000c8eec: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c8ef0: 02 3e 00 90              adrp     x2, #0x888000
0x000c8ef4: 43 3b 00 f0              adrp     x3, #0x833000
0x000c8ef8: 42 d0 0f 91              add      x2, x2, #0x3f4
0x000c8efc: 63 9c 32 91              add      x3, x3, #0xca7
0x000c8f00: e0 03 14 aa              mov      x0, x20
0x000c8f04: e1 03 17 aa              mov      x1, x23
0x000c8f08: 00 01 3f d6              blr      x8
0x000c8f0c: 88 02 40 f9              ldr      x8, [x20]
0x000c8f10: e3 ef 40 b9              ldr      w3, [sp, #0xec]
0x000c8f14: e2 03 00 aa              mov      x2, x0
0x000c8f18: 08 b5 41 f9              ldr      x8, [x8, #0x368]
0x000c8f1c: e0 03 14 aa              mov      x0, x20
0x000c8f20: e1 03 13 aa              mov      x1, x19
0x000c8f24: 00 01 3f d6              blr      x8
0x000c8f28: 88 02 40 f9              ldr      x8, [x20]
0x000c8f2c: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c8f30: 62 3b 00 f0              adrp     x2, #0x837000
0x000c8f34: 43 3b 00 f0              adrp     x3, #0x833000
0x000c8f38: 42 c4 20 91              add      x2, x2, #0x831
0x000c8f3c: 63 9c 32 91              add      x3, x3, #0xca7
0x000c8f40: e0 03 14 aa              mov      x0, x20
0x000c8f44: e1 03 17 aa              mov      x1, x23
0x000c8f48: 00 01 3f d6              blr      x8
0x000c8f4c: 88 02 40 f9              ldr      x8, [x20]
0x000c8f50: e3 f3 40 b9              ldr      w3, [sp, #0xf0]
0x000c8f54: e2 03 00 aa              mov      x2, x0
0x000c8f58: 08 b5 41 f9              ldr      x8, [x8, #0x368]
0x000c8f5c: e0 03 14 aa              mov      x0, x20
0x000c8f60: e1 03 13 aa              mov      x1, x19
0x000c8f64: 00 01 3f d6              blr      x8
0x000c8f68: e1 a3 4f a9              ldp      x1, x8, [sp, #0xf8]
0x000c8f6c: 08 01 01 cb              sub      x8, x8, x1
0x000c8f70: 1f 05 00 71              cmp      w8, #1
0x000c8f74: ab 08 01 54              b.lt     #0xcb088
0x000c8f78: 89 02 40 f9              ldr      x9, [x20]
0x000c8f7c: 02 7d 40 93              sxtw     x2, w8
0x000c8f80: 29 95 43 f9              ldr      x9, [x9, #0x728]
0x000c8f84: e0 03 14 aa              mov      x0, x20
0x000c8f88: 20 01 3f d6              blr      x9
0x000c8f8c: f8 03 00 aa              mov      x24, x0
0x000c8f90: 60 06 01 b4              cbz      x0, #0xcb05c
0x000c8f94: 88 02 40 f9              ldr      x8, [x20]
0x000c8f98: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000c8f9c: 62 3b 00 90              adrp     x2, #0x834000
0x000c8fa0: 63 3b 00 90              adrp     x3, #0x834000
0x000c8fa4: 42 08 25 91              add      x2, x2, #0x942
0x000c8fa8: 63 28 25 91              add      x3, x3, #0x94a
0x000c8fac: e0 03 14 aa              mov      x0, x20
0x000c8fb0: e1 03 17 aa              mov      x1, x23
0x000c8fb4: 00 01 3f d6              blr      x8
0x000c8fb8: e2 03 00 aa              mov      x2, x0
0x000c8fbc: e0 03 14 aa              mov      x0, x20
0x000c8fc0: e1 03 13 aa              mov      x1, x19
0x000c8fc4: e3 03 18 aa              mov      x3, x24
0x000c8fc8: 1b f0 ff 97              bl       #0xc5034
0x000c8fcc: 2a 08 00 14              b        #0xcb074                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x2620
0x000c8fd0: ff ff 0d a9              stp      xzr, xzr, [sp, #0xd8]
0x000c8fd4: ff 6b 00 f9              str      xzr, [sp, #0xd0]
0x000c8fd8: a0 9e 40 f9              ldr      x0, [x21, #0x138]
0x000c8fdc: e1 e3 02 91              add      x1, sp, #0xb8
0x000c8fe0: 40 cc ff 97              bl       #0xbc0e0                   ; -> _ZN5ycnn29YCNNModel12getLandmarksEPNS_15KSFaceDetectOutE@plt
0x000c8fe4: 88 02 40 f9              ldr      x8, [x20]
0x000c8fe8: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000c8fec: 61 3b 00 90              adrp     x1, #0x834000
0x000c8ff0: 21 a4 19 91              add      x1, x1, #0x669
0x000c8ff4: e0 03 14 aa              mov      x0, x20
0x000c8ff8: 00 01 3f d6              blr      x8
0x000c8ffc: 88 02 40 f9              ldr      x8, [x20]
0x000c9000: f8 03 00 aa              mov      x24, x0
0x000c9004: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000c9008: 41 3b 00 f0              adrp     x1, #0x834000
0x000c900c: 21 7c 1a 91              add      x1, x1, #0x69f
0x000c9010: e0 03 14 aa              mov      x0, x20
0x000c9014: 00 01 3f d6              blr      x8
0x000c9018: 88 02 40 f9              ldr      x8, [x20]
0x000c901c: f5 03 00 aa              mov      x21, x0
0x000c9020: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000c9024: 41 3b 00 f0              adrp     x1, #0x834000
0x000c9028: 21 40 1b 91              add      x1, x1, #0x6d0
0x000c902c: e0 03 14 aa              mov      x0, x20
0x000c9030: 00 01 3f d6              blr      x8
0x000c9034: 88 02 40 f9              ldr      x8, [x20]
0x000c9038: f6 03 00 aa              mov      x22, x0
0x000c903c: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9040: 42 3b 00 f0              adrp     x2, #0x834000
0x000c9044: 43 3b 00 f0              adrp     x3, #0x834000
0x000c9048: 42 e8 1b 91              add      x2, x2, #0x6fa
0x000c904c: 63 24 1c 91              add      x3, x3, #0x709
0x000c9050: e0 03 14 aa              mov      x0, x20
0x000c9054: e1 03 13 aa              mov      x1, x19
0x000c9058: 00 01 3f d6              blr      x8
0x000c905c: 88 02 40 f9              ldr      x8, [x20]
0x000c9060: e2 03 00 aa              mov      x2, x0
0x000c9064: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000c9068: e0 03 14 aa              mov      x0, x20
0x000c906c: e1 03 17 aa              mov      x1, x23
0x000c9070: 00 01 3f d6              blr      x8
0x000c9074: 88 02 40 f9              ldr      x8, [x20]
0x000c9078: f3 03 00 aa              mov      x19, x0
0x000c907c: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9080: 42 3b 00 f0              adrp     x2, #0x834000
0x000c9084: 43 3b 00 d0              adrp     x3, #0x833000
0x000c9088: 42 04 1d 91              add      x2, x2, #0x741
0x000c908c: 63 e4 31 91              add      x3, x3, #0xc79
0x000c9090: e0 03 14 aa              mov      x0, x20
0x000c9094: e1 03 18 aa              mov      x1, x24
0x000c9098: 00 01 3f d6              blr      x8
0x000c909c: 88 02 40 f9              ldr      x8, [x20]
0x000c90a0: e2 03 00 aa              mov      x2, x0
0x000c90a4: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000c90a8: e0 03 14 aa              mov      x0, x20
0x000c90ac: e1 03 13 aa              mov      x1, x19
0x000c90b0: 00 01 3f d6              blr      x8
0x000c90b4: e0 4b 00 f9              str      x0, [sp, #0x90]
0x000c90b8: 88 02 40 f9              ldr      x8, [x20]
0x000c90bc: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000c90c0: 41 3b 00 d0              adrp     x1, #0x833000
0x000c90c4: 21 40 32 91              add      x1, x1, #0xc90
0x000c90c8: e0 03 14 aa              mov      x0, x20
0x000c90cc: 00 01 3f d6              blr      x8
0x000c90d0: 88 02 40 f9              ldr      x8, [x20]
0x000c90d4: f3 03 00 aa              mov      x19, x0
0x000c90d8: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000c90dc: 42 3b 00 f0              adrp     x2, #0x834000
0x000c90e0: 43 3b 00 d0              adrp     x3, #0x833000
0x000c90e4: 42 1c 1d 91              add      x2, x2, #0x747
0x000c90e8: 63 0c 30 91              add      x3, x3, #0xc03
0x000c90ec: e0 03 14 aa              mov      x0, x20
0x000c90f0: e1 03 13 aa              mov      x1, x19
0x000c90f4: 00 01 3f d6              blr      x8
0x000c90f8: 88 02 40 f9              ldr      x8, [x20]
0x000c90fc: f7 03 00 aa              mov      x23, x0
0x000c9100: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000c9104: 62 3b 00 b0              adrp     x2, #0x836000
0x000c9108: 43 3b 00 d0              adrp     x3, #0x833000
0x000c910c: 42 3c 02 91              add      x2, x2, #0x8f
0x000c9110: 63 94 32 91              add      x3, x3, #0xca5
0x000c9114: e0 03 14 aa              mov      x0, x20
0x000c9118: e1 03 13 aa              mov      x1, x19
0x000c911c: 00 01 3f d6              blr      x8
0x000c9120: 88 02 40 f9              ldr      x8, [x20]
0x000c9124: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000c9128: a2 3d 00 f0              adrp     x2, #0x880000
0x000c912c: 43 3b 00 d0              adrp     x3, #0x833000
0x000c9130: 42 78 3b 91              add      x2, x2, #0xede
0x000c9134: 63 a4 32 91              add      x3, x3, #0xca9
0x000c9138: e0 03 14 aa              mov      x0, x20
0x000c913c: e1 03 13 aa              mov      x1, x19
0x000c9140: 00 01 3f d6              blr      x8
0x000c9144: 88 02 40 f9              ldr      x8, [x20]
0x000c9148: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000c914c: e2 3d 00 f0              adrp     x2, #0x888000
0x000c9150: 43 3b 00 f0              adrp     x3, #0x834000
0x000c9154: 42 40 30 91              add      x2, x2, #0xc10
0x000c9158: 63 34 1d 91              add      x3, x3, #0x74d
0x000c915c: e0 03 14 aa              mov      x0, x20
0x000c9160: e1 03 13 aa              mov      x1, x19
0x000c9164: 00 01 3f d6              blr      x8
0x000c9168: e0 57 00 f9              str      x0, [sp, #0xa8]
0x000c916c: e1 4b 40 f9              ldr      x1, [sp, #0x90]
0x000c9170: e0 03 14 aa              mov      x0, x20
0x000c9174: e2 03 17 aa              mov      x2, x23
0x000c9178: af ef ff 97              bl       #0xc5034
0x000c917c: f8 4f 4d a9              ldp      x24, x19, [sp, #0xd0]
0x000c9180: e9 b6 8d 52              mov      w9, #0x6db7
0x000c9184: 69 db b6 72              movk     w9, #0xb6db, lsl #16
0x000c9188: 68 02 18 cb              sub      x8, x19, x24
0x000c918c: 08 fd 44 d3              lsr      x8, x8, #4
0x000c9190: 08 7d 09 1b              mul      w8, w8, w9
0x000c9194: 1f 05 00 71              cmp      w8, #1
0x000c9198: 8b 28 00 54              b.lt     #0xc96a8
0x000c919c: 7b 3b 00 d0              adrp     x27, #0x837000
0x000c91a0: f9 03 1f aa              mov      x25, xzr
0x000c91a4: fa 0b 1c 32              mov      w26, #0x70
0x000c91a8: 7b d7 26 91              add      x27, x27, #0x9b5
0x000c91ac: f5 47 00 f9              str      x21, [sp, #0x88]
0x000c91b0: 88 02 40 f9              ldr      x8, [x20]
0x000c91b4: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000c91b8: 42 3b 00 d0              adrp     x2, #0x833000
0x000c91bc: 43 3b 00 d0              adrp     x3, #0x833000
0x000c91c0: e0 03 14 aa              mov      x0, x20
0x000c91c4: e1 03 15 aa              mov      x1, x21
0x000c91c8: 42 f0 2f 91              add      x2, x2, #0xbfc
0x000c91cc: 63 0c 30 91              add      x3, x3, #0xc03
0x000c91d0: 00 01 3f d6              blr      x8
0x000c91d4: e2 03 00 aa              mov      x2, x0
0x000c91d8: e0 03 14 aa              mov      x0, x20
0x000c91dc: e1 03 15 aa              mov      x1, x21
0x000c91e0: bc ef ff 97              bl       #0xc50d0
0x000c91e4: 88 02 40 f9              ldr      x8, [x20]
0x000c91e8: f7 03 00 aa              mov      x23, x0
0x000c91ec: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c91f0: 02 3d 00 90              adrp     x2, #0x869000
0x000c91f4: 43 3b 00 d0              adrp     x3, #0x833000
0x000c91f8: e0 03 14 aa              mov      x0, x20
0x000c91fc: e1 03 15 aa              mov      x1, x21
0x000c9200: 42 cc 3e 91              add      x2, x2, #0xfb3
0x000c9204: 63 e4 31 91              add      x3, x3, #0xc79
0x000c9208: 00 01 3f d6              blr      x8
0x000c920c: 88 02 40 f9              ldr      x8, [x20]
0x000c9210: e2 03 00 aa              mov      x2, x0
0x000c9214: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000c9218: e0 03 14 aa              mov      x0, x20
0x000c921c: e1 03 17 aa              mov      x1, x23
0x000c9220: 00 01 3f d6              blr      x8
0x000c9224: f9 e3 09 a9              stp      x25, x24, [sp, #0x98]
0x000c9228: 1f 20 03 d5              nop      
0x000c922c: 35 63 1a 9b              madd     x21, x25, x26, x24
0x000c9230: a9 8e 40 f8              ldr      x9, [x21, #8]!
0x000c9234: f9 03 15 aa              mov      x25, x21
0x000c9238: 2a 8f 40 f8              ldr      x10, [x25, #8]!
0x000c923c: 88 02 40 f9              ldr      x8, [x20]
0x000c9240: 5f 01 09 eb              cmp      x10, x9
0x000c9244: 60 0d 00 54              b.eq     #0xc93f0
0x000c9248: f3 03 00 aa              mov      x19, x0
0x000c924c: f8 03 1f aa              mov      x24, xzr
0x000c9250: fa 03 1f aa              mov      x26, xzr
0x000c9254: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000c9258: 42 3b 00 d0              adrp     x2, #0x833000
0x000c925c: 43 3b 00 d0              adrp     x3, #0x833000
0x000c9260: e0 03 14 aa              mov      x0, x20
0x000c9264: e1 03 16 aa              mov      x1, x22
0x000c9268: 42 f0 2f 91              add      x2, x2, #0xbfc
0x000c926c: 63 0c 30 91              add      x3, x3, #0xc03
0x000c9270: 00 01 3f d6              blr      x8
0x000c9274: e2 03 00 aa              mov      x2, x0
0x000c9278: e0 03 14 aa              mov      x0, x20
0x000c927c: e1 03 16 aa              mov      x1, x22
0x000c9280: 94 ef ff 97              bl       #0xc50d0
0x000c9284: 88 02 40 f9              ldr      x8, [x20]
0x000c9288: fc 03 00 aa              mov      x28, x0
0x000c928c: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9290: 42 3b 00 f0              adrp     x2, #0x834000
0x000c9294: e0 03 14 aa              mov      x0, x20
0x000c9298: e1 03 16 aa              mov      x1, x22
0x000c929c: 42 8c 1d 91              add      x2, x2, #0x763
0x000c92a0: e3 03 1b aa              mov      x3, x27
0x000c92a4: 00 01 3f d6              blr      x8
0x000c92a8: a8 02 40 f9              ldr      x8, [x21]
0x000c92ac: 89 02 40 f9              ldr      x9, [x20]
0x000c92b0: e2 03 00 aa              mov      x2, x0
0x000c92b4: 00 69 78 bc              ldr      s0, [x8, x24]
0x000c92b8: 28 bd 41 f9              ldr      x8, [x9, #0x378]
0x000c92bc: e0 03 14 aa              mov      x0, x20
0x000c92c0: e1 03 1c aa              mov      x1, x28
0x000c92c4: 00 01 3f d6              blr      x8
0x000c92c8: 88 02 40 f9              ldr      x8, [x20]
0x000c92cc: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c92d0: 42 3b 00 f0              adrp     x2, #0x834000
0x000c92d4: e0 03 14 aa              mov      x0, x20
0x000c92d8: e1 03 16 aa              mov      x1, x22
0x000c92dc: 42 a0 1d 91              add      x2, x2, #0x768
0x000c92e0: e3 03 1b aa              mov      x3, x27
0x000c92e4: 00 01 3f d6              blr      x8
0x000c92e8: a8 02 40 f9              ldr      x8, [x21]
0x000c92ec: 89 02 40 f9              ldr      x9, [x20]
0x000c92f0: e2 03 00 aa              mov      x2, x0
0x000c92f4: 08 01 18 8b              add      x8, x8, x24
0x000c92f8: 00 05 40 bd              ldr      s0, [x8, #4]
0x000c92fc: 28 bd 41 f9              ldr      x8, [x9, #0x378]
0x000c9300: e0 03 14 aa              mov      x0, x20
0x000c9304: e1 03 1c aa              mov      x1, x28
0x000c9308: 00 01 3f d6              blr      x8
0x000c930c: 88 02 40 f9              ldr      x8, [x20]
0x000c9310: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9314: 42 3b 00 f0              adrp     x2, #0x834000
0x000c9318: e0 03 14 aa              mov      x0, x20
0x000c931c: e1 03 16 aa              mov      x1, x22
0x000c9320: 42 b4 1d 91              add      x2, x2, #0x76d
0x000c9324: e3 03 1b aa              mov      x3, x27
0x000c9328: 00 01 3f d6              blr      x8
0x000c932c: a8 02 40 f9              ldr      x8, [x21]
0x000c9330: 89 02 40 f9              ldr      x9, [x20]
0x000c9334: e2 03 00 aa              mov      x2, x0
0x000c9338: 08 01 18 8b              add      x8, x8, x24
0x000c933c: 00 09 40 bd              ldr      s0, [x8, #8]
0x000c9340: 28 bd 41 f9              ldr      x8, [x9, #0x378]
0x000c9344: e0 03 14 aa              mov      x0, x20
0x000c9348: e1 03 1c aa              mov      x1, x28
0x000c934c: 00 01 3f d6              blr      x8
0x000c9350: 88 02 40 f9              ldr      x8, [x20]
0x000c9354: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9358: 42 3c 00 d0              adrp     x2, #0x853000
0x000c935c: 43 3b 00 d0              adrp     x3, #0x833000
0x000c9360: e0 03 14 aa              mov      x0, x20
0x000c9364: e1 03 16 aa              mov      x1, x22
0x000c9368: 42 e0 39 91              add      x2, x2, #0xe78
0x000c936c: 63 9c 32 91              add      x3, x3, #0xca7
0x000c9370: 00 01 3f d6              blr      x8
0x000c9374: a8 02 40 f9              ldr      x8, [x21]
0x000c9378: 89 02 40 f9              ldr      x9, [x20]
0x000c937c: e2 03 00 aa              mov      x2, x0
0x000c9380: 08 01 18 8b              add      x8, x8, x24
0x000c9384: 03 11 40 b9              ldr      w3, [x8, #0x10]
0x000c9388: 28 b5 41 f9              ldr      x8, [x9, #0x368]
0x000c938c: e0 03 14 aa              mov      x0, x20
0x000c9390: e1 03 1c aa              mov      x1, x28
0x000c9394: 00 01 3f d6              blr      x8
0x000c9398: e2 57 40 f9              ldr      x2, [sp, #0xa8]
0x000c939c: e0 03 14 aa              mov      x0, x20
0x000c93a0: e1 03 13 aa              mov      x1, x19
0x000c93a4: e3 03 1c aa              mov      x3, x28
0x000c93a8: a8 0a 00 94              bl       #0xcbe48
0x000c93ac: 88 02 40 f9              ldr      x8, [x20]
0x000c93b0: 08 5d 40 f9              ldr      x8, [x8, #0xb8]
0x000c93b4: e0 03 14 aa              mov      x0, x20
0x000c93b8: e1 03 1c aa              mov      x1, x28
0x000c93bc: 00 01 3f d6              blr      x8
0x000c93c0: 28 03 40 f9              ldr      x8, [x25]
0x000c93c4: a9 02 40 f9              ldr      x9, [x21]
0x000c93c8: ea f3 01 b2              mov      x10, #-0x5555555555555556
0x000c93cc: 6a 55 95 f2              movk     x10, #0xaaab
0x000c93d0: 5a 07 00 91              add      x26, x26, #1
0x000c93d4: 08 01 09 cb              sub      x8, x8, x9
0x000c93d8: 09 fd 43 93              asr      x9, x8, #3
0x000c93dc: 88 02 40 f9              ldr      x8, [x20]
0x000c93e0: 29 7d 0a 9b              mul      x9, x9, x10
0x000c93e4: 3f 01 1a eb              cmp      x9, x26
0x000c93e8: 18 63 00 91              add      x24, x24, #0x18
0x000c93ec: 48 f3 ff 54              b.hi     #0xc9254
0x000c93f0: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c93f4: f5 47 40 f9              ldr      x21, [sp, #0x88]
0x000c93f8: 82 3c 00 b0              adrp     x2, #0x85a000
0x000c93fc: e0 03 14 aa              mov      x0, x20
0x000c9400: 42 6c 1b 91              add      x2, x2, #0x6db
0x000c9404: e1 03 15 aa              mov      x1, x21
0x000c9408: e3 03 1b aa              mov      x3, x27
0x000c940c: 00 01 3f d6              blr      x8
0x000c9410: f9 cf 49 a9              ldp      x25, x19, [sp, #0x98]
0x000c9414: fa 0b 1c 32              mov      w26, #0x70
0x000c9418: 88 02 40 f9              ldr      x8, [x20]
0x000c941c: 1f 20 03 d5              nop      
0x000c9420: 29 4f 1a 9b              madd     x9, x25, x26, x19
0x000c9424: 20 31 40 bd              ldr      s0, [x9, #0x30]
0x000c9428: e2 03 00 aa              mov      x2, x0
0x000c942c: 08 bd 41 f9              ldr      x8, [x8, #0x378]
0x000c9430: e0 03 14 aa              mov      x0, x20
0x000c9434: e1 03 17 aa              mov      x1, x23
0x000c9438: 00 01 3f d6              blr      x8
0x000c943c: 88 02 40 f9              ldr      x8, [x20]
0x000c9440: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9444: 42 3b 00 f0              adrp     x2, #0x834000
0x000c9448: e0 03 14 aa              mov      x0, x20
0x000c944c: e1 03 15 aa              mov      x1, x21
0x000c9450: 42 c8 1d 91              add      x2, x2, #0x772
0x000c9454: e3 03 1b aa              mov      x3, x27
0x000c9458: 00 01 3f d6              blr      x8
0x000c945c: 88 02 40 f9              ldr      x8, [x20]
0x000c9460: 1f 20 03 d5              nop      
0x000c9464: 29 4f 1a 9b              madd     x9, x25, x26, x19
0x000c9468: 20 35 40 bd              ldr      s0, [x9, #0x34]
0x000c946c: e2 03 00 aa              mov      x2, x0
0x000c9470: 08 bd 41 f9              ldr      x8, [x8, #0x378]
0x000c9474: e0 03 14 aa              mov      x0, x20
0x000c9478: e1 03 17 aa              mov      x1, x23
0x000c947c: 00 01 3f d6              blr      x8
0x000c9480: 88 02 40 f9              ldr      x8, [x20]
0x000c9484: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9488: 42 3b 00 f0              adrp     x2, #0x834000
0x000c948c: e0 03 14 aa              mov      x0, x20
0x000c9490: e1 03 15 aa              mov      x1, x21
0x000c9494: 42 d8 1d 91              add      x2, x2, #0x776
0x000c9498: e3 03 1b aa              mov      x3, x27
0x000c949c: 00 01 3f d6              blr      x8
0x000c94a0: 88 02 40 f9              ldr      x8, [x20]
0x000c94a4: 1f 20 03 d5              nop      
0x000c94a8: 29 4f 1a 9b              madd     x9, x25, x26, x19
0x000c94ac: 20 39 40 bd              ldr      s0, [x9, #0x38]
0x000c94b0: e2 03 00 aa              mov      x2, x0
0x000c94b4: 08 bd 41 f9              ldr      x8, [x8, #0x378]
0x000c94b8: e0 03 14 aa              mov      x0, x20
0x000c94bc: e1 03 17 aa              mov      x1, x23
0x000c94c0: 00 01 3f d6              blr      x8
0x000c94c4: 88 02 40 f9              ldr      x8, [x20]
0x000c94c8: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c94cc: 42 3b 00 f0              adrp     x2, #0x834000
0x000c94d0: e0 03 14 aa              mov      x0, x20
0x000c94d4: e1 03 15 aa              mov      x1, x21
0x000c94d8: 42 f0 1d 91              add      x2, x2, #0x77c
0x000c94dc: e3 03 1b aa              mov      x3, x27
0x000c94e0: 00 01 3f d6              blr      x8
0x000c94e4: 88 02 40 f9              ldr      x8, [x20]
0x000c94e8: 1f 20 03 d5              nop      
0x000c94ec: 29 4f 1a 9b              madd     x9, x25, x26, x19
0x000c94f0: 20 3d 40 bd              ldr      s0, [x9, #0x3c]
0x000c94f4: e2 03 00 aa              mov      x2, x0
0x000c94f8: 08 bd 41 f9              ldr      x8, [x8, #0x378]
0x000c94fc: e0 03 14 aa              mov      x0, x20
0x000c9500: e1 03 17 aa              mov      x1, x23
0x000c9504: 00 01 3f d6              blr      x8
0x000c9508: 88 02 40 f9              ldr      x8, [x20]
0x000c950c: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9510: 82 3b 00 b0              adrp     x2, #0x83a000
0x000c9514: 43 3b 00 d0              adrp     x3, #0x833000
0x000c9518: e0 03 14 aa              mov      x0, x20
0x000c951c: e1 03 15 aa              mov      x1, x21
0x000c9520: 42 28 28 91              add      x2, x2, #0xa0a
0x000c9524: 63 b4 30 91              add      x3, x3, #0xc2d
0x000c9528: 00 01 3f d6              blr      x8
0x000c952c: 88 02 40 f9              ldr      x8, [x20]
0x000c9530: 29 7f 1a 9b              mul      x9, x25, x26
0x000c9534: 63 6a 69 f8              ldr      x3, [x19, x9]
0x000c9538: e2 03 00 aa              mov      x2, x0
0x000c953c: 08 b9 41 f9              ldr      x8, [x8, #0x370]
0x000c9540: e0 03 14 aa              mov      x0, x20
0x000c9544: e1 03 17 aa              mov      x1, x23
0x000c9548: 00 01 3f d6              blr      x8
0x000c954c: 88 02 40 f9              ldr      x8, [x20]
0x000c9550: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9554: e2 3d 00 f0              adrp     x2, #0x888000
0x000c9558: e0 03 14 aa              mov      x0, x20
0x000c955c: e1 03 15 aa              mov      x1, x21
0x000c9560: 42 d0 0f 91              add      x2, x2, #0x3f4
0x000c9564: e3 03 1b aa              mov      x3, x27
0x000c9568: 00 01 3f d6              blr      x8
0x000c956c: 88 02 40 f9              ldr      x8, [x20]
0x000c9570: 1f 20 03 d5              nop      
0x000c9574: 29 4f 1a 9b              madd     x9, x25, x26, x19
0x000c9578: 20 2d 40 bd              ldr      s0, [x9, #0x2c]
0x000c957c: e2 03 00 aa              mov      x2, x0
0x000c9580: 08 bd 41 f9              ldr      x8, [x8, #0x378]
0x000c9584: e0 03 14 aa              mov      x0, x20
0x000c9588: e1 03 17 aa              mov      x1, x23
0x000c958c: 00 01 3f d6              blr      x8
0x000c9590: 88 02 40 f9              ldr      x8, [x20]
0x000c9594: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9598: e2 3d 00 f0              adrp     x2, #0x888000
0x000c959c: e0 03 14 aa              mov      x0, x20
0x000c95a0: e1 03 15 aa              mov      x1, x21
0x000c95a4: 42 cc 0b 91              add      x2, x2, #0x2f3
0x000c95a8: e3 03 1b aa              mov      x3, x27
0x000c95ac: 00 01 3f d6              blr      x8
0x000c95b0: 88 02 40 f9              ldr      x8, [x20]
0x000c95b4: 1f 20 03 d5              nop      
0x000c95b8: 29 4f 1a 9b              madd     x9, x25, x26, x19
0x000c95bc: 20 29 40 bd              ldr      s0, [x9, #0x28]
0x000c95c0: e2 03 00 aa              mov      x2, x0
0x000c95c4: 08 bd 41 f9              ldr      x8, [x8, #0x378]
0x000c95c8: e0 03 14 aa              mov      x0, x20
0x000c95cc: e1 03 17 aa              mov      x1, x23
0x000c95d0: 00 01 3f d6              blr      x8
0x000c95d4: 88 02 40 f9              ldr      x8, [x20]
0x000c95d8: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c95dc: 22 3c 00 b0              adrp     x2, #0x84e000
0x000c95e0: e0 03 14 aa              mov      x0, x20
0x000c95e4: e1 03 15 aa              mov      x1, x21
0x000c95e8: 42 d4 2b 91              add      x2, x2, #0xaf5
0x000c95ec: e3 03 1b aa              mov      x3, x27
0x000c95f0: 00 01 3f d6              blr      x8
0x000c95f4: 88 02 40 f9              ldr      x8, [x20]
0x000c95f8: 1f 20 03 d5              nop      
0x000c95fc: 29 4f 1a 9b              madd     x9, x25, x26, x19
0x000c9600: 20 25 40 bd              ldr      s0, [x9, #0x24]
0x000c9604: e2 03 00 aa              mov      x2, x0
0x000c9608: 08 bd 41 f9              ldr      x8, [x8, #0x378]
0x000c960c: e0 03 14 aa              mov      x0, x20
0x000c9610: e1 03 17 aa              mov      x1, x23
0x000c9614: 00 01 3f d6              blr      x8
0x000c9618: 88 02 40 f9              ldr      x8, [x20]
0x000c961c: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9620: e2 3d 00 f0              adrp     x2, #0x888000
0x000c9624: e0 03 14 aa              mov      x0, x20
0x000c9628: e1 03 15 aa              mov      x1, x21
0x000c962c: 42 08 0d 91              add      x2, x2, #0x342
0x000c9630: e3 03 1b aa              mov      x3, x27
0x000c9634: 00 01 3f d6              blr      x8
0x000c9638: 88 02 40 f9              ldr      x8, [x20]
0x000c963c: 1f 20 03 d5              nop      
0x000c9640: 29 4f 1a 9b              madd     x9, x25, x26, x19
0x000c9644: 20 21 40 bd              ldr      s0, [x9, #0x20]
0x000c9648: e2 03 00 aa              mov      x2, x0
0x000c964c: 08 bd 41 f9              ldr      x8, [x8, #0x378]
0x000c9650: e0 03 14 aa              mov      x0, x20
0x000c9654: e1 03 17 aa              mov      x1, x23
0x000c9658: 00 01 3f d6              blr      x8
0x000c965c: e1 4b 40 f9              ldr      x1, [sp, #0x90]
0x000c9660: e2 57 40 f9              ldr      x2, [sp, #0xa8]
0x000c9664: e0 03 14 aa              mov      x0, x20
0x000c9668: e3 03 17 aa              mov      x3, x23
0x000c966c: f7 09 00 94              bl       #0xcbe48
0x000c9670: 88 02 40 f9              ldr      x8, [x20]
0x000c9674: 08 5d 40 f9              ldr      x8, [x8, #0xb8]
0x000c9678: e0 03 14 aa              mov      x0, x20
0x000c967c: e1 03 17 aa              mov      x1, x23
0x000c9680: 00 01 3f d6              blr      x8
0x000c9684: f8 4f 4d a9              ldp      x24, x19, [sp, #0xd0]
0x000c9688: e9 b6 8d 52              mov      w9, #0x6db7
0x000c968c: 69 db b6 72              movk     w9, #0xb6db, lsl #16
0x000c9690: 39 07 00 91              add      x25, x25, #1
0x000c9694: 68 02 18 cb              sub      x8, x19, x24
0x000c9698: 08 fd 44 d3              lsr      x8, x8, #4
0x000c969c: 08 7d 09 1b              mul      w8, w8, w9
0x000c96a0: 3f c3 28 eb              cmp      x25, w8, sxtw
0x000c96a4: 6b d8 ff 54              b.lt     #0xc91b0
0x000c96a8: 98 de 00 b4              cbz      x24, #0xcb278
0x000c96ac: 1f 03 13 eb              cmp      x24, x19
0x000c96b0: e0 dd 00 54              b.eq     #0xcb26c
0x000c96b4: 60 82 5e f8              ldur     x0, [x19, #-0x18]
0x000c96b8: 60 00 00 b4              cbz      x0, #0xc96c4
0x000c96bc: 60 02 1f f8              stur     x0, [x19, #-0x10]
0x000c96c0: 0c c6 ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000c96c4: 60 82 59 f8              ldur     x0, [x19, #-0x68]
0x000c96c8: 74 c2 01 d1              sub      x20, x19, #0x70
0x000c96cc: 60 00 00 b4              cbz      x0, #0xc96d8
0x000c96d0: 60 02 1a f8              stur     x0, [x19, #-0x60]
0x000c96d4: 07 c6 ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000c96d8: 1f 03 14 eb              cmp      x24, x20
0x000c96dc: f3 03 14 aa              mov      x19, x20
0x000c96e0: a1 fe ff 54              b.ne     #0xc96b4
0x000c96e4: e0 6b 40 f9              ldr      x0, [sp, #0xd0]
0x000c96e8: e2 06 00 14              b        #0xcb270                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x281c
0x000c96ec: ff 7f 0c a9              stp      xzr, xzr, [sp, #0xc0]
0x000c96f0: ff 5f 00 f9              str      xzr, [sp, #0xb8]
0x000c96f4: a0 9e 40 f9              ldr      x0, [x21, #0x138]
0x000c96f8: e1 e3 02 91              add      x1, sp, #0xb8
0x000c96fc: 89 cd ff 97              bl       #0xbcd20                   ; -> _ZN5ycnn29YCNNModel16getTaskDetectOutEPNS_15KSTaskDetectOutE@plt
0x000c9700: 88 02 40 f9              ldr      x8, [x20]
0x000c9704: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000c9708: 41 3b 00 f0              adrp     x1, #0x834000
0x000c970c: 21 04 1e 91              add      x1, x1, #0x781
0x000c9710: e0 03 14 aa              mov      x0, x20
0x000c9714: 00 01 3f d6              blr      x8
0x000c9718: 88 02 40 f9              ldr      x8, [x20]
0x000c971c: f6 03 00 aa              mov      x22, x0
0x000c9720: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000c9724: 41 3b 00 f0              adrp     x1, #0x834000
0x000c9728: 21 dc 1e 91              add      x1, x1, #0x7b7
0x000c972c: e0 03 14 aa              mov      x0, x20
0x000c9730: 00 01 3f d6              blr      x8
0x000c9734: 88 02 40 f9              ldr      x8, [x20]
0x000c9738: f5 03 00 aa              mov      x21, x0
0x000c973c: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9740: 42 3b 00 f0              adrp     x2, #0x834000
0x000c9744: 43 3b 00 f0              adrp     x3, #0x834000
0x000c9748: 42 b8 1f 91              add      x2, x2, #0x7ee
0x000c974c: 63 f4 1f 91              add      x3, x3, #0x7fd
0x000c9750: e0 03 14 aa              mov      x0, x20
0x000c9754: e1 03 13 aa              mov      x1, x19
0x000c9758: 00 01 3f d6              blr      x8
0x000c975c: 88 02 40 f9              ldr      x8, [x20]
0x000c9760: e2 03 00 aa              mov      x2, x0
0x000c9764: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000c9768: e0 03 14 aa              mov      x0, x20
0x000c976c: e1 03 17 aa              mov      x1, x23
0x000c9770: 00 01 3f d6              blr      x8
0x000c9774: 88 02 40 f9              ldr      x8, [x20]
0x000c9778: f3 03 00 aa              mov      x19, x0
0x000c977c: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9780: 42 3b 00 f0              adrp     x2, #0x834000
0x000c9784: 43 3b 00 d0              adrp     x3, #0x833000
0x000c9788: 42 d4 20 91              add      x2, x2, #0x835
0x000c978c: 63 e4 31 91              add      x3, x3, #0xc79
0x000c9790: e0 03 14 aa              mov      x0, x20
0x000c9794: e1 03 16 aa              mov      x1, x22
0x000c9798: 00 01 3f d6              blr      x8
0x000c979c: 88 02 40 f9              ldr      x8, [x20]
0x000c97a0: e2 03 00 aa              mov      x2, x0
0x000c97a4: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000c97a8: e0 03 14 aa              mov      x0, x20
0x000c97ac: e1 03 13 aa              mov      x1, x19
0x000c97b0: 00 01 3f d6              blr      x8
0x000c97b4: 88 02 40 f9              ldr      x8, [x20]
0x000c97b8: f3 03 00 aa              mov      x19, x0
0x000c97bc: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000c97c0: 41 3b 00 d0              adrp     x1, #0x833000
0x000c97c4: 21 40 32 91              add      x1, x1, #0xc90
0x000c97c8: e0 03 14 aa              mov      x0, x20
0x000c97cc: 00 01 3f d6              blr      x8
0x000c97d0: 88 02 40 f9              ldr      x8, [x20]
0x000c97d4: f6 03 00 aa              mov      x22, x0
0x000c97d8: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000c97dc: 42 3b 00 f0              adrp     x2, #0x834000
0x000c97e0: 43 3b 00 d0              adrp     x3, #0x833000
0x000c97e4: 42 1c 1d 91              add      x2, x2, #0x747
0x000c97e8: 63 0c 30 91              add      x3, x3, #0xc03
0x000c97ec: e0 03 14 aa              mov      x0, x20
0x000c97f0: e1 03 16 aa              mov      x1, x22
0x000c97f4: 00 01 3f d6              blr      x8
0x000c97f8: 88 02 40 f9              ldr      x8, [x20]
0x000c97fc: f7 03 00 aa              mov      x23, x0
0x000c9800: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000c9804: 62 3b 00 b0              adrp     x2, #0x836000
0x000c9808: 43 3b 00 d0              adrp     x3, #0x833000
0x000c980c: 42 3c 02 91              add      x2, x2, #0x8f
0x000c9810: 63 94 32 91              add      x3, x3, #0xca5
0x000c9814: e0 03 14 aa              mov      x0, x20
0x000c9818: e1 03 16 aa              mov      x1, x22
0x000c981c: 00 01 3f d6              blr      x8
0x000c9820: 88 02 40 f9              ldr      x8, [x20]
0x000c9824: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000c9828: a2 3d 00 f0              adrp     x2, #0x880000
0x000c982c: 43 3b 00 d0              adrp     x3, #0x833000
0x000c9830: 42 78 3b 91              add      x2, x2, #0xede
0x000c9834: 63 a4 32 91              add      x3, x3, #0xca9
0x000c9838: e0 03 14 aa              mov      x0, x20
0x000c983c: e1 03 16 aa              mov      x1, x22
0x000c9840: 00 01 3f d6              blr      x8
0x000c9844: 88 02 40 f9              ldr      x8, [x20]
0x000c9848: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000c984c: e2 3d 00 f0              adrp     x2, #0x888000
0x000c9850: 43 3b 00 f0              adrp     x3, #0x834000
0x000c9854: 42 40 30 91              add      x2, x2, #0xc10
0x000c9858: 63 34 1d 91              add      x3, x3, #0x74d
0x000c985c: e0 03 14 aa              mov      x0, x20
0x000c9860: e1 03 16 aa              mov      x1, x22
0x000c9864: 00 01 3f d6              blr      x8
0x000c9868: f6 03 00 aa              mov      x22, x0
0x000c986c: e0 03 14 aa              mov      x0, x20
0x000c9870: e1 03 13 aa              mov      x1, x19
0x000c9874: e2 03 17 aa              mov      x2, x23
0x000c9878: ef ed ff 97              bl       #0xc5034
0x000c987c: f9 a3 4b a9              ldp      x25, x8, [sp, #0xb8]
0x000c9880: e9 b6 8d 52              mov      w9, #0x6db7
0x000c9884: 69 db b6 72              movk     w9, #0xb6db, lsl #16
0x000c9888: 08 01 19 cb              sub      x8, x8, x25
0x000c988c: 08 fd 42 d3              lsr      x8, x8, #2
0x000c9890: 08 7d 09 1b              mul      w8, w8, w9
0x000c9894: 1f 05 00 71              cmp      w8, #1
0x000c9898: 2b 13 00 54              b.lt     #0xc9afc
0x000c989c: 7c 3b 00 d0              adrp     x28, #0x837000
0x000c98a0: 58 3b 00 d0              adrp     x24, #0x833000
0x000c98a4: fb 03 1f aa              mov      x27, xzr
0x000c98a8: f7 03 1f aa              mov      x23, xzr
0x000c98ac: 9c d7 26 91              add      x28, x28, #0x9b5
0x000c98b0: 18 9f 32 91              add      x24, x24, #0xca7
0x000c98b4: 88 02 40 f9              ldr      x8, [x20]
0x000c98b8: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000c98bc: 42 3b 00 d0              adrp     x2, #0x833000
0x000c98c0: 43 3b 00 d0              adrp     x3, #0x833000
0x000c98c4: e0 03 14 aa              mov      x0, x20
0x000c98c8: e1 03 15 aa              mov      x1, x21
0x000c98cc: 42 f0 2f 91              add      x2, x2, #0xbfc
0x000c98d0: 63 0c 30 91              add      x3, x3, #0xc03
0x000c98d4: 00 01 3f d6              blr      x8
0x000c98d8: e2 03 00 aa              mov      x2, x0
0x000c98dc: e0 03 14 aa              mov      x0, x20
0x000c98e0: e1 03 15 aa              mov      x1, x21
0x000c98e4: fb ed ff 97              bl       #0xc50d0
0x000c98e8: 88 02 40 f9              ldr      x8, [x20]
0x000c98ec: fa 03 00 aa              mov      x26, x0
0x000c98f0: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c98f4: 82 3b 00 b0              adrp     x2, #0x83a000
0x000c98f8: 43 3b 00 d0              adrp     x3, #0x833000
0x000c98fc: e0 03 14 aa              mov      x0, x20
0x000c9900: e1 03 15 aa              mov      x1, x21
0x000c9904: 42 28 28 91              add      x2, x2, #0xa0a
0x000c9908: 63 b4 30 91              add      x3, x3, #0xc2d
0x000c990c: 00 01 3f d6              blr      x8
0x000c9910: 88 02 40 f9              ldr      x8, [x20]
0x000c9914: 23 6b bb b8              ldrsw    x3, [x25, x27]
0x000c9918: e2 03 00 aa              mov      x2, x0
0x000c991c: 08 b9 41 f9              ldr      x8, [x8, #0x370]
0x000c9920: e0 03 14 aa              mov      x0, x20
0x000c9924: e1 03 1a aa              mov      x1, x26
0x000c9928: 00 01 3f d6              blr      x8
0x000c992c: 88 02 40 f9              ldr      x8, [x20]
0x000c9930: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9934: e2 3d 00 f0              adrp     x2, #0x888000
0x000c9938: e0 03 14 aa              mov      x0, x20
0x000c993c: e1 03 15 aa              mov      x1, x21
0x000c9940: 42 08 0d 91              add      x2, x2, #0x342
0x000c9944: e3 03 1c aa              mov      x3, x28
0x000c9948: 00 01 3f d6              blr      x8
0x000c994c: 88 02 40 f9              ldr      x8, [x20]
0x000c9950: 29 03 1b 8b              add      x9, x25, x27
0x000c9954: 20 05 40 bd              ldr      s0, [x9, #4]
0x000c9958: e2 03 00 aa              mov      x2, x0
0x000c995c: 08 bd 41 f9              ldr      x8, [x8, #0x378]
0x000c9960: e0 03 14 aa              mov      x0, x20
0x000c9964: e1 03 1a aa              mov      x1, x26
0x000c9968: 00 01 3f d6              blr      x8
0x000c996c: 88 02 40 f9              ldr      x8, [x20]
0x000c9970: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9974: 22 3c 00 b0              adrp     x2, #0x84e000
0x000c9978: e0 03 14 aa              mov      x0, x20
0x000c997c: e1 03 15 aa              mov      x1, x21
0x000c9980: 42 d4 2b 91              add      x2, x2, #0xaf5
0x000c9984: e3 03 1c aa              mov      x3, x28
0x000c9988: 00 01 3f d6              blr      x8
0x000c998c: 88 02 40 f9              ldr      x8, [x20]
0x000c9990: 29 03 1b 8b              add      x9, x25, x27
0x000c9994: 20 09 40 bd              ldr      s0, [x9, #8]
0x000c9998: e2 03 00 aa              mov      x2, x0
0x000c999c: 08 bd 41 f9              ldr      x8, [x8, #0x378]
0x000c99a0: e0 03 14 aa              mov      x0, x20
0x000c99a4: e1 03 1a aa              mov      x1, x26
0x000c99a8: 00 01 3f d6              blr      x8
0x000c99ac: 88 02 40 f9              ldr      x8, [x20]
0x000c99b0: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c99b4: e2 3d 00 f0              adrp     x2, #0x888000
0x000c99b8: e0 03 14 aa              mov      x0, x20
0x000c99bc: e1 03 15 aa              mov      x1, x21
0x000c99c0: 42 cc 0b 91              add      x2, x2, #0x2f3
0x000c99c4: e3 03 1c aa              mov      x3, x28
0x000c99c8: 00 01 3f d6              blr      x8
0x000c99cc: 88 02 40 f9              ldr      x8, [x20]
0x000c99d0: 29 03 1b 8b              add      x9, x25, x27
0x000c99d4: 20 0d 40 bd              ldr      s0, [x9, #0xc]
0x000c99d8: e2 03 00 aa              mov      x2, x0
0x000c99dc: 08 bd 41 f9              ldr      x8, [x8, #0x378]
0x000c99e0: e0 03 14 aa              mov      x0, x20
0x000c99e4: e1 03 1a aa              mov      x1, x26
0x000c99e8: 00 01 3f d6              blr      x8
0x000c99ec: 88 02 40 f9              ldr      x8, [x20]
0x000c99f0: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c99f4: e2 3d 00 f0              adrp     x2, #0x888000
0x000c99f8: e0 03 14 aa              mov      x0, x20
0x000c99fc: e1 03 15 aa              mov      x1, x21
0x000c9a00: 42 d0 0f 91              add      x2, x2, #0x3f4
0x000c9a04: e3 03 1c aa              mov      x3, x28
0x000c9a08: 00 01 3f d6              blr      x8
0x000c9a0c: 88 02 40 f9              ldr      x8, [x20]
0x000c9a10: 29 03 1b 8b              add      x9, x25, x27
0x000c9a14: 20 11 40 bd              ldr      s0, [x9, #0x10]
0x000c9a18: e2 03 00 aa              mov      x2, x0
0x000c9a1c: 08 bd 41 f9              ldr      x8, [x8, #0x378]
0x000c9a20: e0 03 14 aa              mov      x0, x20
0x000c9a24: e1 03 1a aa              mov      x1, x26
0x000c9a28: 00 01 3f d6              blr      x8
0x000c9a2c: 88 02 40 f9              ldr      x8, [x20]
0x000c9a30: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9a34: 82 3c 00 b0              adrp     x2, #0x85a000
0x000c9a38: e0 03 14 aa              mov      x0, x20
0x000c9a3c: e1 03 15 aa              mov      x1, x21
0x000c9a40: 42 6c 1b 91              add      x2, x2, #0x6db
0x000c9a44: e3 03 1c aa              mov      x3, x28
0x000c9a48: 00 01 3f d6              blr      x8
0x000c9a4c: 88 02 40 f9              ldr      x8, [x20]
0x000c9a50: 29 03 1b 8b              add      x9, x25, x27
0x000c9a54: 20 15 40 bd              ldr      s0, [x9, #0x14]
0x000c9a58: e2 03 00 aa              mov      x2, x0
0x000c9a5c: 08 bd 41 f9              ldr      x8, [x8, #0x378]
0x000c9a60: e0 03 14 aa              mov      x0, x20
0x000c9a64: e1 03 1a aa              mov      x1, x26
0x000c9a68: 00 01 3f d6              blr      x8
0x000c9a6c: 88 02 40 f9              ldr      x8, [x20]
0x000c9a70: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9a74: e2 3c 00 d0              adrp     x2, #0x867000
0x000c9a78: e0 03 14 aa              mov      x0, x20
0x000c9a7c: e1 03 15 aa              mov      x1, x21
0x000c9a80: 42 08 3d 91              add      x2, x2, #0xf42
0x000c9a84: e3 03 18 aa              mov      x3, x24
0x000c9a88: 00 01 3f d6              blr      x8
0x000c9a8c: 88 02 40 f9              ldr      x8, [x20]
0x000c9a90: 29 03 1b 8b              add      x9, x25, x27
0x000c9a94: 23 19 40 b9              ldr      w3, [x9, #0x18]
0x000c9a98: e2 03 00 aa              mov      x2, x0
0x000c9a9c: 08 b5 41 f9              ldr      x8, [x8, #0x368]
0x000c9aa0: e0 03 14 aa              mov      x0, x20
0x000c9aa4: e1 03 1a aa              mov      x1, x26
0x000c9aa8: 00 01 3f d6              blr      x8
0x000c9aac: e0 03 14 aa              mov      x0, x20
0x000c9ab0: e1 03 13 aa              mov      x1, x19
0x000c9ab4: e2 03 16 aa              mov      x2, x22
0x000c9ab8: e3 03 1a aa              mov      x3, x26
0x000c9abc: e3 08 00 94              bl       #0xcbe48
0x000c9ac0: 88 02 40 f9              ldr      x8, [x20]
0x000c9ac4: 08 5d 40 f9              ldr      x8, [x8, #0xb8]
0x000c9ac8: e0 03 14 aa              mov      x0, x20
0x000c9acc: e1 03 1a aa              mov      x1, x26
0x000c9ad0: 00 01 3f d6              blr      x8
0x000c9ad4: f9 a3 4b a9              ldp      x25, x8, [sp, #0xb8]
0x000c9ad8: e9 b6 8d 52              mov      w9, #0x6db7
0x000c9adc: 69 db b6 72              movk     w9, #0xb6db, lsl #16
0x000c9ae0: f7 06 00 91              add      x23, x23, #1
0x000c9ae4: 08 01 19 cb              sub      x8, x8, x25
0x000c9ae8: 08 fd 42 d3              lsr      x8, x8, #2
0x000c9aec: 08 7d 09 1b              mul      w8, w8, w9
0x000c9af0: ff c2 28 eb              cmp      x23, w8, sxtw
0x000c9af4: 7b 73 00 91              add      x27, x27, #0x1c
0x000c9af8: eb ed ff 54              b.lt     #0xc98b4
0x000c9afc: f9 bb 00 b4              cbz      x25, #0xcb278
0x000c9b00: f9 63 00 f9              str      x25, [sp, #0xc0]
0x000c9b04: e0 03 19 aa              mov      x0, x25
0x000c9b08: db 05 00 14              b        #0xcb274                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x2820
0x000c9b0c: ff ff 0d a9              stp      xzr, xzr, [sp, #0xd8]
0x000c9b10: ff 6b 00 f9              str      xzr, [sp, #0xd0]
0x000c9b14: a0 9e 40 f9              ldr      x0, [x21, #0x138]
0x000c9b18: e1 e3 02 91              add      x1, sp, #0xb8
0x000c9b1c: cd c0 ff 97              bl       #0xb9e50                   ; -> _ZN5ycnn29YCNNModel12getHumanPoseEPNS_15KSPoseDetectOutE@plt
0x000c9b20: 88 02 40 f9              ldr      x8, [x20]
0x000c9b24: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000c9b28: 41 3b 00 f0              adrp     x1, #0x834000
0x000c9b2c: 21 08 26 91              add      x1, x1, #0x982
0x000c9b30: e0 03 14 aa              mov      x0, x20
0x000c9b34: 00 01 3f d6              blr      x8
0x000c9b38: 88 02 40 f9              ldr      x8, [x20]
0x000c9b3c: f5 03 00 aa              mov      x21, x0
0x000c9b40: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9b44: 42 3b 00 f0              adrp     x2, #0x834000
0x000c9b48: 43 3b 00 f0              adrp     x3, #0x834000
0x000c9b4c: 42 dc 26 91              add      x2, x2, #0x9b7
0x000c9b50: 63 14 27 91              add      x3, x3, #0x9c5
0x000c9b54: e0 03 14 aa              mov      x0, x20
0x000c9b58: e1 03 13 aa              mov      x1, x19
0x000c9b5c: 00 01 3f d6              blr      x8
0x000c9b60: 88 02 40 f9              ldr      x8, [x20]
0x000c9b64: e2 03 00 aa              mov      x2, x0
0x000c9b68: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000c9b6c: e0 03 14 aa              mov      x0, x20
0x000c9b70: e1 03 17 aa              mov      x1, x23
0x000c9b74: 00 01 3f d6              blr      x8
0x000c9b78: 88 02 40 f9              ldr      x8, [x20]
0x000c9b7c: f3 03 00 aa              mov      x19, x0
0x000c9b80: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9b84: 42 3b 00 f0              adrp     x2, #0x834000
0x000c9b88: 43 3b 00 f0              adrp     x3, #0x834000
0x000c9b8c: 42 f0 27 91              add      x2, x2, #0x9fc
0x000c9b90: 63 08 28 91              add      x3, x3, #0xa02
0x000c9b94: e0 03 14 aa              mov      x0, x20
0x000c9b98: e1 03 15 aa              mov      x1, x21
0x000c9b9c: 00 01 3f d6              blr      x8
0x000c9ba0: 88 02 40 f9              ldr      x8, [x20]
0x000c9ba4: e2 03 00 aa              mov      x2, x0
0x000c9ba8: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000c9bac: e0 03 14 aa              mov      x0, x20
0x000c9bb0: e1 03 13 aa              mov      x1, x19
0x000c9bb4: 00 01 3f d6              blr      x8
0x000c9bb8: e0 33 00 f9              str      x0, [sp, #0x60]
0x000c9bbc: 88 02 40 f9              ldr      x8, [x20]
0x000c9bc0: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000c9bc4: 41 3b 00 f0              adrp     x1, #0x834000
0x000c9bc8: 21 60 28 91              add      x1, x1, #0xa18
0x000c9bcc: e0 03 14 aa              mov      x0, x20
0x000c9bd0: 00 01 3f d6              blr      x8
0x000c9bd4: 88 02 40 f9              ldr      x8, [x20]
0x000c9bd8: f5 03 00 aa              mov      x21, x0
0x000c9bdc: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000c9be0: 42 3b 00 d0              adrp     x2, #0x833000
0x000c9be4: 43 3b 00 d0              adrp     x3, #0x833000
0x000c9be8: 42 f0 2f 91              add      x2, x2, #0xbfc
0x000c9bec: 63 0c 30 91              add      x3, x3, #0xc03
0x000c9bf0: e0 03 14 aa              mov      x0, x20
0x000c9bf4: e1 03 15 aa              mov      x1, x21
0x000c9bf8: 00 01 3f d6              blr      x8
0x000c9bfc: e0 2f 00 f9              str      x0, [sp, #0x58]
0x000c9c00: 88 02 40 f9              ldr      x8, [x20]
0x000c9c04: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9c08: 82 3b 00 b0              adrp     x2, #0x83a000
0x000c9c0c: 43 3b 00 d0              adrp     x3, #0x833000
0x000c9c10: 42 28 28 91              add      x2, x2, #0xa0a
0x000c9c14: 63 9c 32 91              add      x3, x3, #0xca7
0x000c9c18: e0 03 14 aa              mov      x0, x20
0x000c9c1c: e1 03 15 aa              mov      x1, x21
0x000c9c20: 00 01 3f d6              blr      x8
0x000c9c24: e0 0f 00 f9              str      x0, [sp, #0x18]
0x000c9c28: 88 02 40 f9              ldr      x8, [x20]
0x000c9c2c: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9c30: a2 3c 00 b0              adrp     x2, #0x85e000
0x000c9c34: 43 3b 00 d0              adrp     x3, #0x833000
0x000c9c38: 42 98 21 91              add      x2, x2, #0x866
0x000c9c3c: 63 9c 32 91              add      x3, x3, #0xca7
0x000c9c40: e0 03 14 aa              mov      x0, x20
0x000c9c44: e1 03 15 aa              mov      x1, x21
0x000c9c48: 00 01 3f d6              blr      x8
0x000c9c4c: e0 07 00 f9              str      x0, [sp, #8]
0x000c9c50: 88 02 40 f9              ldr      x8, [x20]
0x000c9c54: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9c58: 02 3d 00 90              adrp     x2, #0x869000
0x000c9c5c: 43 3b 00 f0              adrp     x3, #0x834000
0x000c9c60: 42 cc 3e 91              add      x2, x2, #0xfb3
0x000c9c64: 63 08 28 91              add      x3, x3, #0xa02
0x000c9c68: e0 03 14 aa              mov      x0, x20
0x000c9c6c: e1 03 15 aa              mov      x1, x21
0x000c9c70: 00 01 3f d6              blr      x8
0x000c9c74: e0 23 00 f9              str      x0, [sp, #0x40]
0x000c9c78: 88 02 40 f9              ldr      x8, [x20]
0x000c9c7c: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9c80: 82 3b 00 b0              adrp     x2, #0x83a000
0x000c9c84: 43 3b 00 f0              adrp     x3, #0x834000
0x000c9c88: 42 98 2b 91              add      x2, x2, #0xae6
0x000c9c8c: 63 24 29 91              add      x3, x3, #0xa49
0x000c9c90: e0 03 14 aa              mov      x0, x20
0x000c9c94: e1 03 15 aa              mov      x1, x21
0x000c9c98: 00 01 3f d6              blr      x8
0x000c9c9c: e0 1f 00 f9              str      x0, [sp, #0x38]
0x000c9ca0: 88 02 40 f9              ldr      x8, [x20]
0x000c9ca4: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000c9ca8: 41 3b 00 f0              adrp     x1, #0x834000
0x000c9cac: 21 d0 29 91              add      x1, x1, #0xa74
0x000c9cb0: e0 03 14 aa              mov      x0, x20
0x000c9cb4: 00 01 3f d6              blr      x8
0x000c9cb8: 88 02 40 f9              ldr      x8, [x20]
0x000c9cbc: f3 03 00 aa              mov      x19, x0
0x000c9cc0: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000c9cc4: 42 3b 00 f0              adrp     x2, #0x834000
0x000c9cc8: 43 3b 00 d0              adrp     x3, #0x833000
0x000c9ccc: 42 1c 1d 91              add      x2, x2, #0x747
0x000c9cd0: 63 0c 30 91              add      x3, x3, #0xc03
0x000c9cd4: e0 03 14 aa              mov      x0, x20
0x000c9cd8: e1 03 13 aa              mov      x1, x19
0x000c9cdc: 00 01 3f d6              blr      x8
0x000c9ce0: 88 02 40 f9              ldr      x8, [x20]
0x000c9ce4: fb 03 00 aa              mov      x27, x0
0x000c9ce8: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000c9cec: 62 3b 00 b0              adrp     x2, #0x836000
0x000c9cf0: 43 3b 00 d0              adrp     x3, #0x833000
0x000c9cf4: 42 3c 02 91              add      x2, x2, #0x8f
0x000c9cf8: 63 94 32 91              add      x3, x3, #0xca5
0x000c9cfc: e0 03 14 aa              mov      x0, x20
0x000c9d00: e1 03 13 aa              mov      x1, x19
0x000c9d04: 00 01 3f d6              blr      x8
0x000c9d08: 88 02 40 f9              ldr      x8, [x20]
0x000c9d0c: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000c9d10: 42 3b 00 d0              adrp     x2, #0x833000
0x000c9d14: 43 3b 00 d0              adrp     x3, #0x833000
0x000c9d18: 42 f0 2f 91              add      x2, x2, #0xbfc
0x000c9d1c: 63 f0 2d 91              add      x3, x3, #0xb7c
0x000c9d20: e0 03 14 aa              mov      x0, x20
0x000c9d24: e1 03 13 aa              mov      x1, x19
0x000c9d28: 00 01 3f d6              blr      x8
0x000c9d2c: 88 02 40 f9              ldr      x8, [x20]
0x000c9d30: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000c9d34: e2 3d 00 f0              adrp     x2, #0x888000
0x000c9d38: 43 3b 00 f0              adrp     x3, #0x834000
0x000c9d3c: 42 40 30 91              add      x2, x2, #0xc10
0x000c9d40: 63 34 1d 91              add      x3, x3, #0x74d
0x000c9d44: e0 03 14 aa              mov      x0, x20
0x000c9d48: e1 03 13 aa              mov      x1, x19
0x000c9d4c: 00 01 3f d6              blr      x8
0x000c9d50: e0 57 00 f9              str      x0, [sp, #0xa8]
0x000c9d54: 88 02 40 f9              ldr      x8, [x20]
0x000c9d58: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000c9d5c: 41 3b 00 f0              adrp     x1, #0x834000
0x000c9d60: 21 40 1b 91              add      x1, x1, #0x6d0
0x000c9d64: e0 03 14 aa              mov      x0, x20
0x000c9d68: 00 01 3f d6              blr      x8
0x000c9d6c: 88 02 40 f9              ldr      x8, [x20]
0x000c9d70: fc 03 00 aa              mov      x28, x0
0x000c9d74: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000c9d78: 42 3b 00 d0              adrp     x2, #0x833000
0x000c9d7c: 43 3b 00 d0              adrp     x3, #0x833000
0x000c9d80: 42 f0 2f 91              add      x2, x2, #0xbfc
0x000c9d84: 63 0c 30 91              add      x3, x3, #0xc03
0x000c9d88: e0 03 14 aa              mov      x0, x20
0x000c9d8c: e1 03 1c aa              mov      x1, x28
0x000c9d90: 00 01 3f d6              blr      x8
0x000c9d94: e0 53 00 f9              str      x0, [sp, #0xa0]
0x000c9d98: 88 02 40 f9              ldr      x8, [x20]
0x000c9d9c: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9da0: 42 3b 00 f0              adrp     x2, #0x834000
0x000c9da4: 63 3b 00 d0              adrp     x3, #0x837000
0x000c9da8: 42 8c 1d 91              add      x2, x2, #0x763
0x000c9dac: 63 d4 26 91              add      x3, x3, #0x9b5
0x000c9db0: e0 03 14 aa              mov      x0, x20
0x000c9db4: e1 03 1c aa              mov      x1, x28
0x000c9db8: 00 01 3f d6              blr      x8
0x000c9dbc: e0 4f 00 f9              str      x0, [sp, #0x98]
0x000c9dc0: 88 02 40 f9              ldr      x8, [x20]
0x000c9dc4: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9dc8: 42 3b 00 f0              adrp     x2, #0x834000
0x000c9dcc: 63 3b 00 d0              adrp     x3, #0x837000
0x000c9dd0: 42 a0 1d 91              add      x2, x2, #0x768
0x000c9dd4: 63 d4 26 91              add      x3, x3, #0x9b5
0x000c9dd8: e0 03 14 aa              mov      x0, x20
0x000c9ddc: e1 03 1c aa              mov      x1, x28
0x000c9de0: 00 01 3f d6              blr      x8
0x000c9de4: e0 4b 00 f9              str      x0, [sp, #0x90]
0x000c9de8: 88 02 40 f9              ldr      x8, [x20]
0x000c9dec: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9df0: 42 3b 00 f0              adrp     x2, #0x834000
0x000c9df4: 63 3b 00 d0              adrp     x3, #0x837000
0x000c9df8: 42 b4 1d 91              add      x2, x2, #0x76d
0x000c9dfc: 63 d4 26 91              add      x3, x3, #0x9b5
0x000c9e00: e0 03 14 aa              mov      x0, x20
0x000c9e04: e1 03 1c aa              mov      x1, x28
0x000c9e08: 00 01 3f d6              blr      x8
0x000c9e0c: e0 47 00 f9              str      x0, [sp, #0x88]
0x000c9e10: 88 02 40 f9              ldr      x8, [x20]
0x000c9e14: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9e18: 42 3c 00 d0              adrp     x2, #0x853000
0x000c9e1c: 43 3b 00 d0              adrp     x3, #0x833000
0x000c9e20: 42 e0 39 91              add      x2, x2, #0xe78
0x000c9e24: 63 9c 32 91              add      x3, x3, #0xca7
0x000c9e28: e0 03 14 aa              mov      x0, x20
0x000c9e2c: e1 03 1c aa              mov      x1, x28
0x000c9e30: 00 01 3f d6              blr      x8
0x000c9e34: e0 43 00 f9              str      x0, [sp, #0x80]
0x000c9e38: 88 02 40 f9              ldr      x8, [x20]
0x000c9e3c: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000c9e40: 41 3b 00 f0              adrp     x1, #0x834000
0x000c9e44: 21 20 2a 91              add      x1, x1, #0xa88
0x000c9e48: e0 03 14 aa              mov      x0, x20
0x000c9e4c: 00 01 3f d6              blr      x8
0x000c9e50: 88 02 40 f9              ldr      x8, [x20]
0x000c9e54: f6 03 00 aa              mov      x22, x0
0x000c9e58: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000c9e5c: 42 3b 00 d0              adrp     x2, #0x833000
0x000c9e60: 43 3b 00 d0              adrp     x3, #0x833000
0x000c9e64: 42 f0 2f 91              add      x2, x2, #0xbfc
0x000c9e68: 63 0c 30 91              add      x3, x3, #0xc03
0x000c9e6c: e0 03 14 aa              mov      x0, x20
0x000c9e70: e1 03 16 aa              mov      x1, x22
0x000c9e74: 00 01 3f d6              blr      x8
0x000c9e78: 88 02 40 f9              ldr      x8, [x20]
0x000c9e7c: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9e80: e2 3d 00 f0              adrp     x2, #0x888000
0x000c9e84: 63 3b 00 d0              adrp     x3, #0x837000
0x000c9e88: 42 08 0d 91              add      x2, x2, #0x342
0x000c9e8c: 63 d4 26 91              add      x3, x3, #0x9b5
0x000c9e90: e0 03 14 aa              mov      x0, x20
0x000c9e94: e1 03 16 aa              mov      x1, x22
0x000c9e98: 00 01 3f d6              blr      x8
0x000c9e9c: e0 1b 00 f9              str      x0, [sp, #0x30]
0x000c9ea0: 88 02 40 f9              ldr      x8, [x20]
0x000c9ea4: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9ea8: 22 3c 00 b0              adrp     x2, #0x84e000
0x000c9eac: 63 3b 00 d0              adrp     x3, #0x837000
0x000c9eb0: 42 d4 2b 91              add      x2, x2, #0xaf5
0x000c9eb4: 63 d4 26 91              add      x3, x3, #0x9b5
0x000c9eb8: e0 03 14 aa              mov      x0, x20
0x000c9ebc: e1 03 16 aa              mov      x1, x22
0x000c9ec0: 00 01 3f d6              blr      x8
0x000c9ec4: e0 17 00 f9              str      x0, [sp, #0x28]
0x000c9ec8: 88 02 40 f9              ldr      x8, [x20]
0x000c9ecc: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9ed0: e2 3d 00 f0              adrp     x2, #0x888000
0x000c9ed4: 63 3b 00 d0              adrp     x3, #0x837000
0x000c9ed8: 42 cc 0b 91              add      x2, x2, #0x2f3
0x000c9edc: 63 d4 26 91              add      x3, x3, #0x9b5
0x000c9ee0: e0 03 14 aa              mov      x0, x20
0x000c9ee4: e1 03 16 aa              mov      x1, x22
0x000c9ee8: 00 01 3f d6              blr      x8
0x000c9eec: e0 13 00 f9              str      x0, [sp, #0x20]
0x000c9ef0: 88 02 40 f9              ldr      x8, [x20]
0x000c9ef4: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9ef8: e2 3d 00 f0              adrp     x2, #0x888000
0x000c9efc: 63 3b 00 d0              adrp     x3, #0x837000
0x000c9f00: 42 d0 0f 91              add      x2, x2, #0x3f4
0x000c9f04: 63 d4 26 91              add      x3, x3, #0x9b5
0x000c9f08: e0 03 14 aa              mov      x0, x20
0x000c9f0c: e1 03 16 aa              mov      x1, x22
0x000c9f10: 00 01 3f d6              blr      x8
0x000c9f14: e0 0b 00 f9              str      x0, [sp, #0x10]
0x000c9f18: e1 33 40 f9              ldr      x1, [sp, #0x60]
0x000c9f1c: e0 03 14 aa              mov      x0, x20
0x000c9f20: e2 03 1b aa              mov      x2, x27
0x000c9f24: 44 ec ff 97              bl       #0xc5034
0x000c9f28: f7 23 4d a9              ldp      x23, x8, [sp, #0xd0]
0x000c9f2c: 1f 01 17 eb              cmp      x8, x23
0x000c9f30: 81 6c 00 54              b.ne     #0xcacc0
0x000c9f34: e0 03 08 aa              mov      x0, x8
0x000c9f38: a0 81 00 b5              cbnz     x0, #0xcaf6c
0x000c9f3c: cf 04 00 14              b        #0xcb278                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x2824
0x000c9f40: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x000c9f44: e8 07 00 91              add      x8, sp, #1
0x000c9f48: ff 8b 00 f9              str      xzr, [sp, #0x110]
0x000c9f4c: 00 f1 8f 3c              stur     q0, [x8, #0xff]
0x000c9f50: e0 03 8f 3c              stur     q0, [sp, #0xf0]
0x000c9f54: ff eb 00 b9              str      wzr, [sp, #0xe8]
0x000c9f58: ff ff 0d a9              stp      xzr, xzr, [sp, #0xd8]
0x000c9f5c: ff 6b 00 f9              str      xzr, [sp, #0xd0]
0x000c9f60: a0 9e 40 f9              ldr      x0, [x21, #0x138]
0x000c9f64: e1 e3 02 91              add      x1, sp, #0xb8
0x000c9f68: c2 bf ff 97              bl       #0xb9e70                   ; -> _ZN5ycnn29YCNNModel10getHeadSegEPNS_12KSHeadSegOutE@plt
0x000c9f6c: 88 02 40 f9              ldr      x8, [x20]
0x000c9f70: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000c9f74: 41 3b 00 f0              adrp     x1, #0x834000
0x000c9f78: 21 c4 2a 91              add      x1, x1, #0xab1
0x000c9f7c: e0 03 14 aa              mov      x0, x20
0x000c9f80: 00 01 3f d6              blr      x8
0x000c9f84: 88 02 40 f9              ldr      x8, [x20]
0x000c9f88: f5 03 00 aa              mov      x21, x0
0x000c9f8c: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9f90: 42 3b 00 f0              adrp     x2, #0x834000
0x000c9f94: 43 3b 00 f0              adrp     x3, #0x834000
0x000c9f98: 42 90 2b 91              add      x2, x2, #0xae4
0x000c9f9c: 63 c0 2b 91              add      x3, x3, #0xaf0
0x000c9fa0: e0 03 14 aa              mov      x0, x20
0x000c9fa4: e1 03 13 aa              mov      x1, x19
0x000c9fa8: 00 01 3f d6              blr      x8
0x000c9fac: 88 02 40 f9              ldr      x8, [x20]
0x000c9fb0: e2 03 00 aa              mov      x2, x0
0x000c9fb4: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000c9fb8: e0 03 14 aa              mov      x0, x20
0x000c9fbc: e1 03 17 aa              mov      x1, x23
0x000c9fc0: 00 01 3f d6              blr      x8
0x000c9fc4: 88 02 40 f9              ldr      x8, [x20]
0x000c9fc8: f6 03 00 aa              mov      x22, x0
0x000c9fcc: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000c9fd0: 41 3b 00 d0              adrp     x1, #0x833000
0x000c9fd4: 21 4c 2f 91              add      x1, x1, #0xbd3
0x000c9fd8: e0 03 14 aa              mov      x0, x20
0x000c9fdc: 00 01 3f d6              blr      x8
0x000c9fe0: 88 02 40 f9              ldr      x8, [x20]
0x000c9fe4: f3 03 00 aa              mov      x19, x0
0x000c9fe8: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c9fec: 82 3b 00 90              adrp     x2, #0x839000
0x000c9ff0: 43 3b 00 f0              adrp     x3, #0x834000
0x000c9ff4: 42 50 1a 91              add      x2, x2, #0x694
0x000c9ff8: 63 5c 24 91              add      x3, x3, #0x917
0x000c9ffc: e0 03 14 aa              mov      x0, x20
0x000ca000: e1 03 15 aa              mov      x1, x21
0x000ca004: 00 01 3f d6              blr      x8
0x000ca008: 88 02 40 f9              ldr      x8, [x20]
0x000ca00c: e2 03 00 aa              mov      x2, x0
0x000ca010: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000ca014: e0 03 14 aa              mov      x0, x20
0x000ca018: e1 03 16 aa              mov      x1, x22
0x000ca01c: 00 01 3f d6              blr      x8
0x000ca020: 88 02 40 f9              ldr      x8, [x20]
0x000ca024: f5 03 00 aa              mov      x21, x0
0x000ca028: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000ca02c: e2 3d 00 d0              adrp     x2, #0x888000
0x000ca030: 43 3b 00 b0              adrp     x3, #0x833000
0x000ca034: 42 cc 0b 91              add      x2, x2, #0x2f3
0x000ca038: 63 9c 32 91              add      x3, x3, #0xca7
0x000ca03c: e0 03 14 aa              mov      x0, x20
0x000ca040: e1 03 13 aa              mov      x1, x19
0x000ca044: 00 01 3f d6              blr      x8
0x000ca048: 88 02 40 f9              ldr      x8, [x20]
0x000ca04c: e3 e3 40 b9              ldr      w3, [sp, #0xe0]
0x000ca050: e2 03 00 aa              mov      x2, x0
0x000ca054: 08 b5 41 f9              ldr      x8, [x8, #0x368]
0x000ca058: e0 03 14 aa              mov      x0, x20
0x000ca05c: e1 03 15 aa              mov      x1, x21
0x000ca060: 00 01 3f d6              blr      x8
0x000ca064: 88 02 40 f9              ldr      x8, [x20]
0x000ca068: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000ca06c: e2 3d 00 d0              adrp     x2, #0x888000
0x000ca070: 43 3b 00 b0              adrp     x3, #0x833000
0x000ca074: 42 d0 0f 91              add      x2, x2, #0x3f4
0x000ca078: 63 9c 32 91              add      x3, x3, #0xca7
0x000ca07c: e0 03 14 aa              mov      x0, x20
0x000ca080: e1 03 13 aa              mov      x1, x19
0x000ca084: 00 01 3f d6              blr      x8
0x000ca088: 88 02 40 f9              ldr      x8, [x20]
0x000ca08c: e3 e7 40 b9              ldr      w3, [sp, #0xe4]
0x000ca090: e2 03 00 aa              mov      x2, x0
0x000ca094: 08 b5 41 f9              ldr      x8, [x8, #0x368]
0x000ca098: e0 03 14 aa              mov      x0, x20
0x000ca09c: e1 03 15 aa              mov      x1, x21
0x000ca0a0: 00 01 3f d6              blr      x8
0x000ca0a4: 88 02 40 f9              ldr      x8, [x20]
0x000ca0a8: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000ca0ac: 62 3b 00 b0              adrp     x2, #0x837000
0x000ca0b0: 43 3b 00 b0              adrp     x3, #0x833000
0x000ca0b4: 42 c4 20 91              add      x2, x2, #0x831
0x000ca0b8: 63 9c 32 91              add      x3, x3, #0xca7
0x000ca0bc: e0 03 14 aa              mov      x0, x20
0x000ca0c0: e1 03 13 aa              mov      x1, x19
0x000ca0c4: 00 01 3f d6              blr      x8
0x000ca0c8: 88 02 40 f9              ldr      x8, [x20]
0x000ca0cc: e3 eb 40 b9              ldr      w3, [sp, #0xe8]
0x000ca0d0: e2 03 00 aa              mov      x2, x0
0x000ca0d4: 08 b5 41 f9              ldr      x8, [x8, #0x368]
0x000ca0d8: e0 03 14 aa              mov      x0, x20
0x000ca0dc: e1 03 15 aa              mov      x1, x21
0x000ca0e0: 00 01 3f d6              blr      x8
0x000ca0e4: e1 23 4f a9              ldp      x1, x8, [sp, #0xf0]
0x000ca0e8: 08 01 01 cb              sub      x8, x8, x1
0x000ca0ec: 1f 05 00 71              cmp      w8, #1
0x000ca0f0: 0b 77 00 54              b.lt     #0xcafd0
0x000ca0f4: 89 02 40 f9              ldr      x9, [x20]
0x000ca0f8: 02 7d 40 93              sxtw     x2, w8
0x000ca0fc: 29 95 43 f9              ldr      x9, [x9, #0x728]
0x000ca100: e0 03 14 aa              mov      x0, x20
0x000ca104: 20 01 3f d6              blr      x9
0x000ca108: f6 03 00 aa              mov      x22, x0
0x000ca10c: a0 74 00 b4              cbz      x0, #0xcafa0
0x000ca110: 88 02 40 f9              ldr      x8, [x20]
0x000ca114: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000ca118: 42 3b 00 d0              adrp     x2, #0x834000
0x000ca11c: 43 3b 00 d0              adrp     x3, #0x834000
0x000ca120: 42 08 25 91              add      x2, x2, #0x942
0x000ca124: 63 28 25 91              add      x3, x3, #0x94a
0x000ca128: e0 03 14 aa              mov      x0, x20
0x000ca12c: e1 03 13 aa              mov      x1, x19
0x000ca130: 00 01 3f d6              blr      x8
0x000ca134: e2 03 00 aa              mov      x2, x0
0x000ca138: e0 03 14 aa              mov      x0, x20
0x000ca13c: e1 03 15 aa              mov      x1, x21
0x000ca140: e3 03 16 aa              mov      x3, x22
0x000ca144: bc eb ff 97              bl       #0xc5034
0x000ca148: 9c 03 00 14              b        #0xcafb8                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x2564
0x000ca14c: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x000ca150: e9 a7 00 91              add      x9, sp, #0x29
0x000ca154: ff 43 0d f8              stur     xzr, [sp, #0xd4]
0x000ca158: ff c3 0c f8              stur     xzr, [sp, #0xcc]
0x000ca15c: ff e3 02 39              strb     wzr, [sp, #0xb8]
0x000ca160: ff fb 00 b9              str      wzr, [sp, #0xf8]
0x000ca164: ff ff 0e a9              stp      xzr, xzr, [sp, #0xe8]
0x000ca168: ff 73 00 f9              str      xzr, [sp, #0xe0]
0x000ca16c: 20 f1 8f 3c              stur     q0, [x9, #0xff]
0x000ca170: e9 67 00 91              add      x9, sp, #0x19
0x000ca174: e8 e3 02 91              add      x8, sp, #0xb8
0x000ca178: 20 f1 8f 3c              stur     q0, [x9, #0xff]
0x000ca17c: e9 27 00 91              add      x9, sp, #9
0x000ca180: 20 f1 8f 3c              stur     q0, [x9, #0xff]
0x000ca184: 00 81 8b 3c              stur     q0, [x8, #0xb8]
0x000ca188: 00 81 8a 3c              stur     q0, [x8, #0xa8]
0x000ca18c: 00 81 89 3c              stur     q0, [x8, #0x98]
0x000ca190: a0 9e 40 f9              ldr      x0, [x21, #0x138]
0x000ca194: e1 e3 02 91              add      x1, sp, #0xb8
0x000ca198: 76 c8 ff 97              bl       #0xbc370                   ; -> _ZN5ycnn29YCNNModel11getModelOutEPNS_12YCNNModelOutE@plt
0x000ca19c: 88 02 40 f9              ldr      x8, [x20]
0x000ca1a0: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000ca1a4: 41 3b 00 d0              adrp     x1, #0x834000
0x000ca1a8: 21 8c 34 91              add      x1, x1, #0xd23
0x000ca1ac: e0 03 14 aa              mov      x0, x20
0x000ca1b0: 00 01 3f d6              blr      x8
0x000ca1b4: 88 02 40 f9              ldr      x8, [x20]
0x000ca1b8: f5 03 00 aa              mov      x21, x0
0x000ca1bc: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000ca1c0: 42 3b 00 d0              adrp     x2, #0x834000
0x000ca1c4: 43 3b 00 d0              adrp     x3, #0x834000
0x000ca1c8: 42 64 35 91              add      x2, x2, #0xd59
0x000ca1cc: 63 a0 35 91              add      x3, x3, #0xd68
0x000ca1d0: e0 03 14 aa              mov      x0, x20
0x000ca1d4: e1 03 13 aa              mov      x1, x19
0x000ca1d8: 00 01 3f d6              blr      x8
0x000ca1dc: 88 02 40 f9              ldr      x8, [x20]
0x000ca1e0: e2 03 00 aa              mov      x2, x0
0x000ca1e4: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000ca1e8: e0 03 14 aa              mov      x0, x20
0x000ca1ec: e1 03 17 aa              mov      x1, x23
0x000ca1f0: 00 01 3f d6              blr      x8
0x000ca1f4: 88 02 40 f9              ldr      x8, [x20]
0x000ca1f8: f6 03 00 aa              mov      x22, x0
0x000ca1fc: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000ca200: 41 3b 00 b0              adrp     x1, #0x833000
0x000ca204: 21 4c 2f 91              add      x1, x1, #0xbd3
0x000ca208: e0 03 14 aa              mov      x0, x20
0x000ca20c: 00 01 3f d6              blr      x8
0x000ca210: 88 02 40 f9              ldr      x8, [x20]
0x000ca214: f3 03 00 aa              mov      x19, x0
0x000ca218: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000ca21c: c2 3c 00 d0              adrp     x2, #0x864000
0x000ca220: 43 3b 00 d0              adrp     x3, #0x834000
0x000ca224: 42 c0 3a 91              add      x2, x2, #0xeb0
0x000ca228: 63 5c 24 91              add      x3, x3, #0x917
0x000ca22c: e0 03 14 aa              mov      x0, x20
0x000ca230: e1 03 15 aa              mov      x1, x21
0x000ca234: 00 01 3f d6              blr      x8
0x000ca238: 88 02 40 f9              ldr      x8, [x20]
0x000ca23c: e2 03 00 aa              mov      x2, x0
0x000ca240: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000ca244: e0 03 14 aa              mov      x0, x20
0x000ca248: e1 03 16 aa              mov      x1, x22
0x000ca24c: 00 01 3f d6              blr      x8
0x000ca250: 88 02 40 f9              ldr      x8, [x20]
0x000ca254: f5 03 00 aa              mov      x21, x0
0x000ca258: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000ca25c: e2 3d 00 d0              adrp     x2, #0x888000
0x000ca260: 43 3b 00 b0              adrp     x3, #0x833000
0x000ca264: 42 cc 0b 91              add      x2, x2, #0x2f3
0x000ca268: 63 9c 32 91              add      x3, x3, #0xca7
0x000ca26c: e0 03 14 aa              mov      x0, x20
0x000ca270: e1 03 13 aa              mov      x1, x19
0x000ca274: 00 01 3f d6              blr      x8
0x000ca278: 88 02 40 f9              ldr      x8, [x20]
0x000ca27c: e3 fb 40 b9              ldr      w3, [sp, #0xf8]
0x000ca280: e2 03 00 aa              mov      x2, x0
0x000ca284: 08 b5 41 f9              ldr      x8, [x8, #0x368]
0x000ca288: e0 03 14 aa              mov      x0, x20
0x000ca28c: e1 03 15 aa              mov      x1, x21
0x000ca290: 00 01 3f d6              blr      x8
0x000ca294: 88 02 40 f9              ldr      x8, [x20]
0x000ca298: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000ca29c: e2 3d 00 d0              adrp     x2, #0x888000
0x000ca2a0: 43 3b 00 b0              adrp     x3, #0x833000
0x000ca2a4: 42 d0 0f 91              add      x2, x2, #0x3f4
0x000ca2a8: 63 9c 32 91              add      x3, x3, #0xca7
0x000ca2ac: e0 03 14 aa              mov      x0, x20
0x000ca2b0: e1 03 13 aa              mov      x1, x19
0x000ca2b4: 00 01 3f d6              blr      x8
0x000ca2b8: 88 02 40 f9              ldr      x8, [x20]
0x000ca2bc: e3 ff 40 b9              ldr      w3, [sp, #0xfc]
0x000ca2c0: e2 03 00 aa              mov      x2, x0
0x000ca2c4: 08 b5 41 f9              ldr      x8, [x8, #0x368]
0x000ca2c8: e0 03 14 aa              mov      x0, x20
0x000ca2cc: e1 03 15 aa              mov      x1, x21
0x000ca2d0: 00 01 3f d6              blr      x8
0x000ca2d4: 88 02 40 f9              ldr      x8, [x20]
0x000ca2d8: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000ca2dc: 62 3b 00 b0              adrp     x2, #0x837000
0x000ca2e0: 43 3b 00 b0              adrp     x3, #0x833000
0x000ca2e4: 42 c4 20 91              add      x2, x2, #0x831
0x000ca2e8: 63 9c 32 91              add      x3, x3, #0xca7
0x000ca2ec: e0 03 14 aa              mov      x0, x20
0x000ca2f0: e1 03 13 aa              mov      x1, x19
0x000ca2f4: 00 01 3f d6              blr      x8
0x000ca2f8: 88 02 40 f9              ldr      x8, [x20]
0x000ca2fc: e3 03 41 b9              ldr      w3, [sp, #0x100]
0x000ca300: e2 03 00 aa              mov      x2, x0
0x000ca304: 08 b5 41 f9              ldr      x8, [x8, #0x368]
0x000ca308: e0 03 14 aa              mov      x0, x20
0x000ca30c: e1 03 15 aa              mov      x1, x21
0x000ca310: 00 01 3f d6              blr      x8
0x000ca314: e1 a3 50 a9              ldp      x1, x8, [sp, #0x108]
0x000ca318: 08 01 01 cb              sub      x8, x8, x1
0x000ca31c: 1f 05 00 71              cmp      w8, #1
0x000ca320: 6b 67 00 54              b.lt     #0xcb00c
0x000ca324: 89 02 40 f9              ldr      x9, [x20]
0x000ca328: 02 7d 40 93              sxtw     x2, w8
0x000ca32c: 29 95 43 f9              ldr      x9, [x9, #0x728]
0x000ca330: e0 03 14 aa              mov      x0, x20
0x000ca334: 20 01 3f d6              blr      x9
0x000ca338: f6 03 00 aa              mov      x22, x0
0x000ca33c: 20 65 00 b4              cbz      x0, #0xcafe0
0x000ca340: 88 02 40 f9              ldr      x8, [x20]
0x000ca344: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000ca348: 42 3b 00 d0              adrp     x2, #0x834000
0x000ca34c: 43 3b 00 d0              adrp     x3, #0x834000
0x000ca350: 42 08 25 91              add      x2, x2, #0x942
0x000ca354: 63 28 25 91              add      x3, x3, #0x94a
0x000ca358: e0 03 14 aa              mov      x0, x20
0x000ca35c: e1 03 13 aa              mov      x1, x19
0x000ca360: 00 01 3f d6              blr      x8
0x000ca364: e2 03 00 aa              mov      x2, x0
0x000ca368: e0 03 14 aa              mov      x0, x20
0x000ca36c: e1 03 15 aa              mov      x1, x21
0x000ca370: e3 03 16 aa              mov      x3, x22
0x000ca374: 30 eb ff 97              bl       #0xc5034
0x000ca378: 20 03 00 14              b        #0xcaff8                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x25a4
0x000ca37c: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x000ca380: e8 87 00 91              add      x8, sp, #0x21
0x000ca384: 00 f1 8f 3c              stur     q0, [x8, #0xff]
0x000ca388: e8 47 00 91              add      x8, sp, #0x11
0x000ca38c: 00 f1 8f 3c              stur     q0, [x8, #0xff]
0x000ca390: e8 07 00 91              add      x8, sp, #1
0x000ca394: 00 f1 8f 3c              stur     q0, [x8, #0xff]
0x000ca398: e0 03 07 ad              stp      q0, q0, [sp, #0xe0]
0x000ca39c: e0 03 8d 3c              stur     q0, [sp, #0xd0]
0x000ca3a0: a0 9e 40 f9              ldr      x0, [x21, #0x138]
0x000ca3a4: e1 e3 02 91              add      x1, sp, #0xb8
0x000ca3a8: ae c3 ff 97              bl       #0xbb260                   ; -> _ZN5ycnn29YCNNModel14getScenceClassEPNS_15KSSceneClassOutE@plt
0x000ca3ac: 88 02 40 f9              ldr      x8, [x20]
0x000ca3b0: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000ca3b4: 41 3b 00 d0              adrp     x1, #0x834000
0x000ca3b8: 21 88 2f 91              add      x1, x1, #0xbe2
0x000ca3bc: e0 03 14 aa              mov      x0, x20
0x000ca3c0: 00 01 3f d6              blr      x8
0x000ca3c4: 88 02 40 f9              ldr      x8, [x20]
0x000ca3c8: f6 03 00 aa              mov      x22, x0
0x000ca3cc: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000ca3d0: 41 3b 00 d0              adrp     x1, #0x834000
0x000ca3d4: 21 60 30 91              add      x1, x1, #0xc18
0x000ca3d8: e0 03 14 aa              mov      x0, x20
0x000ca3dc: 00 01 3f d6              blr      x8
0x000ca3e0: 88 02 40 f9              ldr      x8, [x20]
0x000ca3e4: f5 03 00 aa              mov      x21, x0
0x000ca3e8: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000ca3ec: 42 3b 00 d0              adrp     x2, #0x834000
0x000ca3f0: 43 3b 00 d0              adrp     x3, #0x834000
0x000ca3f4: 42 3c 31 91              add      x2, x2, #0xc4f
0x000ca3f8: 63 64 31 91              add      x3, x3, #0xc59
0x000ca3fc: e0 03 14 aa              mov      x0, x20
0x000ca400: e1 03 13 aa              mov      x1, x19
0x000ca404: 00 01 3f d6              blr      x8
0x000ca408: 88 02 40 f9              ldr      x8, [x20]
0x000ca40c: e2 03 00 aa              mov      x2, x0
0x000ca410: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000ca414: e0 03 14 aa              mov      x0, x20
0x000ca418: e1 03 17 aa              mov      x1, x23
0x000ca41c: 00 01 3f d6              blr      x8
0x000ca420: 88 02 40 f9              ldr      x8, [x20]
0x000ca424: f3 03 00 aa              mov      x19, x0
0x000ca428: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000ca42c: 42 3b 00 d0              adrp     x2, #0x834000
0x000ca430: 43 3b 00 d0              adrp     x3, #0x834000
0x000ca434: 42 44 32 91              add      x2, x2, #0xc91
0x000ca438: 63 08 28 91              add      x3, x3, #0xa02
0x000ca43c: e0 03 14 aa              mov      x0, x20
0x000ca440: e1 03 16 aa              mov      x1, x22
0x000ca444: 00 01 3f d6              blr      x8
0x000ca448: 88 02 40 f9              ldr      x8, [x20]
0x000ca44c: e2 03 00 aa              mov      x2, x0
0x000ca450: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000ca454: e0 03 14 aa              mov      x0, x20
0x000ca458: e1 03 13 aa              mov      x1, x19
0x000ca45c: 00 01 3f d6              blr      x8
0x000ca460: e0 4b 00 f9              str      x0, [sp, #0x90]
0x000ca464: 88 02 40 f9              ldr      x8, [x20]
0x000ca468: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000ca46c: e2 3c 00 b0              adrp     x2, #0x867000
0x000ca470: 43 3b 00 b0              adrp     x3, #0x833000
0x000ca474: 42 08 3d 91              add      x2, x2, #0xf42
0x000ca478: 63 9c 32 91              add      x3, x3, #0xca7
0x000ca47c: e0 03 14 aa              mov      x0, x20
0x000ca480: e1 03 15 aa              mov      x1, x21
0x000ca484: 00 01 3f d6              blr      x8
0x000ca488: e0 53 00 f9              str      x0, [sp, #0xa0]
0x000ca48c: 88 02 40 f9              ldr      x8, [x20]
0x000ca490: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000ca494: a2 3c 00 90              adrp     x2, #0x85e000
0x000ca498: 63 3b 00 b0              adrp     x3, #0x837000
0x000ca49c: 42 44 33 91              add      x2, x2, #0xcd1
0x000ca4a0: 63 d4 26 91              add      x3, x3, #0x9b5
0x000ca4a4: e0 03 14 aa              mov      x0, x20
0x000ca4a8: e1 03 15 aa              mov      x1, x21
0x000ca4ac: 00 01 3f d6              blr      x8
0x000ca4b0: e0 57 00 f9              str      x0, [sp, #0xa8]
0x000ca4b4: 88 02 40 f9              ldr      x8, [x20]
0x000ca4b8: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000ca4bc: 42 3b 00 b0              adrp     x2, #0x833000
0x000ca4c0: 43 3b 00 b0              adrp     x3, #0x833000
0x000ca4c4: 42 f0 2f 91              add      x2, x2, #0xbfc
0x000ca4c8: 63 0c 30 91              add      x3, x3, #0xc03
0x000ca4cc: e0 03 14 aa              mov      x0, x20
0x000ca4d0: e1 03 15 aa              mov      x1, x21
0x000ca4d4: 00 01 3f d6              blr      x8
0x000ca4d8: 88 02 40 f9              ldr      x8, [x20]
0x000ca4dc: f8 03 00 aa              mov      x24, x0
0x000ca4e0: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000ca4e4: 41 3b 00 d0              adrp     x1, #0x834000
0x000ca4e8: 21 d0 29 91              add      x1, x1, #0xa74
0x000ca4ec: e0 03 14 aa              mov      x0, x20
0x000ca4f0: 00 01 3f d6              blr      x8
0x000ca4f4: 88 02 40 f9              ldr      x8, [x20]
0x000ca4f8: f6 03 00 aa              mov      x22, x0
0x000ca4fc: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000ca500: 42 3b 00 d0              adrp     x2, #0x834000
0x000ca504: 43 3b 00 b0              adrp     x3, #0x833000
0x000ca508: 42 1c 1d 91              add      x2, x2, #0x747
0x000ca50c: 63 0c 30 91              add      x3, x3, #0xc03
0x000ca510: e0 03 14 aa              mov      x0, x20
0x000ca514: e1 03 16 aa              mov      x1, x22
0x000ca518: 00 01 3f d6              blr      x8
0x000ca51c: 88 02 40 f9              ldr      x8, [x20]
0x000ca520: fc 03 00 aa              mov      x28, x0
0x000ca524: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000ca528: 62 3b 00 90              adrp     x2, #0x836000
0x000ca52c: 43 3b 00 b0              adrp     x3, #0x833000
0x000ca530: 42 3c 02 91              add      x2, x2, #0x8f
0x000ca534: 63 94 32 91              add      x3, x3, #0xca5
0x000ca538: e0 03 14 aa              mov      x0, x20
0x000ca53c: e1 03 16 aa              mov      x1, x22
0x000ca540: 00 01 3f d6              blr      x8
0x000ca544: 88 02 40 f9              ldr      x8, [x20]
0x000ca548: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000ca54c: 42 3b 00 b0              adrp     x2, #0x833000
0x000ca550: 43 3b 00 b0              adrp     x3, #0x833000
0x000ca554: 42 f0 2f 91              add      x2, x2, #0xbfc
0x000ca558: 63 f0 2d 91              add      x3, x3, #0xb7c
0x000ca55c: e0 03 14 aa              mov      x0, x20
0x000ca560: e1 03 16 aa              mov      x1, x22
0x000ca564: 00 01 3f d6              blr      x8
0x000ca568: e0 47 00 f9              str      x0, [sp, #0x88]
0x000ca56c: 88 02 40 f9              ldr      x8, [x20]
0x000ca570: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000ca574: e2 3d 00 d0              adrp     x2, #0x888000
0x000ca578: 43 3b 00 d0              adrp     x3, #0x834000
0x000ca57c: 42 40 30 91              add      x2, x2, #0xc10
0x000ca580: 63 34 1d 91              add      x3, x3, #0x74d
0x000ca584: e0 03 14 aa              mov      x0, x20
0x000ca588: e1 03 16 aa              mov      x1, x22
0x000ca58c: 00 01 3f d6              blr      x8
0x000ca590: fb 03 00 aa              mov      x27, x0
0x000ca594: e1 4b 40 f9              ldr      x1, [sp, #0x90]
0x000ca598: e0 03 14 aa              mov      x0, x20
0x000ca59c: e2 03 1c aa              mov      x2, x28
0x000ca5a0: a5 ea ff 97              bl       #0xc5034
0x000ca5a4: f3 a3 51 a9              ldp      x19, x8, [sp, #0x118]
0x000ca5a8: 1f 01 13 eb              cmp      x8, x19
0x000ca5ac: 41 2b 00 54              b.ne     #0xcab14
0x000ca5b0: e0 03 08 aa              mov      x0, x8
0x000ca5b4: 40 35 00 b5              cbnz     x0, #0xcac5c
0x000ca5b8: b6 01 00 14              b        #0xcac90                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x223c
0x000ca5bc: ff ff 0d a9              stp      xzr, xzr, [sp, #0xd8]
0x000ca5c0: ff 6b 00 f9              str      xzr, [sp, #0xd0]
0x000ca5c4: a0 9e 40 f9              ldr      x0, [x21, #0x138]
0x000ca5c8: e1 e3 02 91              add      x1, sp, #0xb8
0x000ca5cc: b5 cf ff 97              bl       #0xbe4a0                   ; -> _ZN5ycnn29YCNNModel14getInstanceSegEPNS_16KSInstanceSegOutE@plt
0x000ca5d0: 88 02 40 f9              ldr      x8, [x20]
0x000ca5d4: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000ca5d8: 41 3b 00 d0              adrp     x1, #0x834000
0x000ca5dc: 21 94 2c 91              add      x1, x1, #0xb25
0x000ca5e0: e0 03 14 aa              mov      x0, x20
0x000ca5e4: 00 01 3f d6              blr      x8
0x000ca5e8: 88 02 40 f9              ldr      x8, [x20]
0x000ca5ec: f5 03 00 aa              mov      x21, x0
0x000ca5f0: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000ca5f4: 42 3b 00 d0              adrp     x2, #0x834000
0x000ca5f8: 43 3b 00 d0              adrp     x3, #0x834000
0x000ca5fc: 42 70 2d 91              add      x2, x2, #0xb5c
0x000ca600: 63 b0 2d 91              add      x3, x3, #0xb6c
0x000ca604: e0 03 14 aa              mov      x0, x20
0x000ca608: e1 03 13 aa              mov      x1, x19
0x000ca60c: 00 01 3f d6              blr      x8
0x000ca610: 88 02 40 f9              ldr      x8, [x20]
0x000ca614: e2 03 00 aa              mov      x2, x0
0x000ca618: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000ca61c: e0 03 14 aa              mov      x0, x20
0x000ca620: e1 03 17 aa              mov      x1, x23
0x000ca624: 00 01 3f d6              blr      x8
0x000ca628: 88 02 40 f9              ldr      x8, [x20]
0x000ca62c: f3 03 00 aa              mov      x19, x0
0x000ca630: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000ca634: 42 3b 00 d0              adrp     x2, #0x834000
0x000ca638: 43 3b 00 b0              adrp     x3, #0x833000
0x000ca63c: 42 94 2e 91              add      x2, x2, #0xba5
0x000ca640: 63 e4 31 91              add      x3, x3, #0xc79
0x000ca644: e0 03 14 aa              mov      x0, x20
0x000ca648: e1 03 15 aa              mov      x1, x21
0x000ca64c: 00 01 3f d6              blr      x8
0x000ca650: 88 02 40 f9              ldr      x8, [x20]
0x000ca654: e2 03 00 aa              mov      x2, x0
0x000ca658: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000ca65c: e0 03 14 aa              mov      x0, x20
0x000ca660: e1 03 13 aa              mov      x1, x19
0x000ca664: 00 01 3f d6              blr      x8
0x000ca668: e0 57 00 f9              str      x0, [sp, #0xa8]
0x000ca66c: 88 02 40 f9              ldr      x8, [x20]
0x000ca670: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000ca674: 41 3b 00 d0              adrp     x1, #0x834000
0x000ca678: 21 a8 2e 91              add      x1, x1, #0xbaa
0x000ca67c: e0 03 14 aa              mov      x0, x20
0x000ca680: 00 01 3f d6              blr      x8
0x000ca684: 88 02 40 f9              ldr      x8, [x20]
0x000ca688: f5 03 00 aa              mov      x21, x0
0x000ca68c: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000ca690: 41 3b 00 b0              adrp     x1, #0x833000
0x000ca694: 21 40 32 91              add      x1, x1, #0xc90
0x000ca698: e0 03 14 aa              mov      x0, x20
0x000ca69c: 00 01 3f d6              blr      x8
0x000ca6a0: 88 02 40 f9              ldr      x8, [x20]
0x000ca6a4: f6 03 00 aa              mov      x22, x0
0x000ca6a8: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000ca6ac: 42 3b 00 d0              adrp     x2, #0x834000
0x000ca6b0: 43 3b 00 b0              adrp     x3, #0x833000
0x000ca6b4: 42 1c 1d 91              add      x2, x2, #0x747
0x000ca6b8: 63 0c 30 91              add      x3, x3, #0xc03
0x000ca6bc: e0 03 14 aa              mov      x0, x20
0x000ca6c0: e1 03 16 aa              mov      x1, x22
0x000ca6c4: 00 01 3f d6              blr      x8
0x000ca6c8: 88 02 40 f9              ldr      x8, [x20]
0x000ca6cc: f7 03 00 aa              mov      x23, x0
0x000ca6d0: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000ca6d4: 62 3b 00 90              adrp     x2, #0x836000
0x000ca6d8: 43 3b 00 b0              adrp     x3, #0x833000
0x000ca6dc: 42 3c 02 91              add      x2, x2, #0x8f
0x000ca6e0: 63 94 32 91              add      x3, x3, #0xca5
0x000ca6e4: e0 03 14 aa              mov      x0, x20
0x000ca6e8: e1 03 16 aa              mov      x1, x22
0x000ca6ec: 00 01 3f d6              blr      x8
0x000ca6f0: 88 02 40 f9              ldr      x8, [x20]
0x000ca6f4: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000ca6f8: a2 3d 00 d0              adrp     x2, #0x880000
0x000ca6fc: 43 3b 00 b0              adrp     x3, #0x833000
0x000ca700: 42 78 3b 91              add      x2, x2, #0xede
0x000ca704: 63 a4 32 91              add      x3, x3, #0xca9
0x000ca708: e0 03 14 aa              mov      x0, x20
0x000ca70c: e1 03 16 aa              mov      x1, x22
0x000ca710: 00 01 3f d6              blr      x8
0x000ca714: 88 02 40 f9              ldr      x8, [x20]
0x000ca718: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000ca71c: e2 3d 00 d0              adrp     x2, #0x888000
0x000ca720: 43 3b 00 d0              adrp     x3, #0x834000
0x000ca724: 42 40 30 91              add      x2, x2, #0xc10
0x000ca728: 63 34 1d 91              add      x3, x3, #0x74d
0x000ca72c: e0 03 14 aa              mov      x0, x20
0x000ca730: e1 03 16 aa              mov      x1, x22
0x000ca734: 00 01 3f d6              blr      x8
0x000ca738: f6 03 00 aa              mov      x22, x0
0x000ca73c: e1 57 40 f9              ldr      x1, [sp, #0xa8]
0x000ca740: e0 03 14 aa              mov      x0, x20
0x000ca744: e2 03 17 aa              mov      x2, x23
0x000ca748: 3b ea ff 97              bl       #0xc5034
0x000ca74c: f9 23 4d a9              ldp      x25, x8, [sp, #0xd0]
0x000ca750: 09 01 19 cb              sub      x9, x8, x25
0x000ca754: 29 fd 46 d3              lsr      x9, x9, #6
0x000ca758: 3f 05 00 71              cmp      w9, #1
0x000ca75c: eb 13 00 54              b.lt     #0xca9d8
0x000ca760: 5a 3b 00 b0              adrp     x26, #0x833000
0x000ca764: fb 03 1f aa              mov      x27, xzr
0x000ca768: f3 03 1f aa              mov      x19, xzr
0x000ca76c: 5a 9f 32 91              add      x26, x26, #0xca7
0x000ca770: 88 02 40 f9              ldr      x8, [x20]
0x000ca774: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000ca778: 42 3b 00 b0              adrp     x2, #0x833000
0x000ca77c: 43 3b 00 b0              adrp     x3, #0x833000
0x000ca780: e0 03 14 aa              mov      x0, x20
0x000ca784: e1 03 15 aa              mov      x1, x21
0x000ca788: 42 f0 2f 91              add      x2, x2, #0xbfc
0x000ca78c: 63 0c 30 91              add      x3, x3, #0xc03
0x000ca790: 00 01 3f d6              blr      x8
0x000ca794: e2 03 00 aa              mov      x2, x0
0x000ca798: e0 03 14 aa              mov      x0, x20
0x000ca79c: e1 03 15 aa              mov      x1, x21
0x000ca7a0: 4c ea ff 97              bl       #0xc50d0
0x000ca7a4: 88 02 40 f9              ldr      x8, [x20]
0x000ca7a8: fc 03 00 aa              mov      x28, x0
0x000ca7ac: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000ca7b0: 82 3b 00 90              adrp     x2, #0x83a000
0x000ca7b4: e0 03 14 aa              mov      x0, x20
0x000ca7b8: e1 03 15 aa              mov      x1, x21
0x000ca7bc: 42 28 28 91              add      x2, x2, #0xa0a
0x000ca7c0: e3 03 1a aa              mov      x3, x26
0x000ca7c4: 00 01 3f d6              blr      x8
0x000ca7c8: 88 02 40 f9              ldr      x8, [x20]
0x000ca7cc: 23 6b 7b b8              ldr      w3, [x25, x27]
0x000ca7d0: e2 03 00 aa              mov      x2, x0
0x000ca7d4: 08 b5 41 f9              ldr      x8, [x8, #0x368]
0x000ca7d8: e0 03 14 aa              mov      x0, x20
0x000ca7dc: e1 03 1c aa              mov      x1, x28
0x000ca7e0: 00 01 3f d6              blr      x8
0x000ca7e4: 88 02 40 f9              ldr      x8, [x20]
0x000ca7e8: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000ca7ec: 41 3b 00 b0              adrp     x1, #0x833000
0x000ca7f0: e0 03 14 aa              mov      x0, x20
0x000ca7f4: 21 4c 2f 91              add      x1, x1, #0xbd3
0x000ca7f8: 00 01 3f d6              blr      x8
0x000ca7fc: 88 02 40 f9              ldr      x8, [x20]
0x000ca800: f7 03 00 aa              mov      x23, x0
0x000ca804: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000ca808: e2 3c 00 f0              adrp     x2, #0x869000
0x000ca80c: 43 3b 00 d0              adrp     x3, #0x834000
0x000ca810: e0 03 14 aa              mov      x0, x20
0x000ca814: e1 03 15 aa              mov      x1, x21
0x000ca818: 42 24 1d 91              add      x2, x2, #0x749
0x000ca81c: 63 5c 24 91              add      x3, x3, #0x917
0x000ca820: 00 01 3f d6              blr      x8
0x000ca824: 88 02 40 f9              ldr      x8, [x20]
0x000ca828: e2 03 00 aa              mov      x2, x0
0x000ca82c: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000ca830: e0 03 14 aa              mov      x0, x20
0x000ca834: e1 03 1c aa              mov      x1, x28
0x000ca838: 00 01 3f d6              blr      x8
0x000ca83c: 88 02 40 f9              ldr      x8, [x20]
0x000ca840: f8 03 00 aa              mov      x24, x0
0x000ca844: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000ca848: e2 3d 00 d0              adrp     x2, #0x888000
0x000ca84c: e0 03 14 aa              mov      x0, x20
0x000ca850: e1 03 17 aa              mov      x1, x23
0x000ca854: 42 cc 0b 91              add      x2, x2, #0x2f3
0x000ca858: e3 03 1a aa              mov      x3, x26
0x000ca85c: 00 01 3f d6              blr      x8
0x000ca860: 88 02 40 f9              ldr      x8, [x20]
0x000ca864: 29 03 1b 8b              add      x9, x25, x27
0x000ca868: 23 09 40 b9              ldr      w3, [x9, #8]
0x000ca86c: e2 03 00 aa              mov      x2, x0
0x000ca870: 08 b5 41 f9              ldr      x8, [x8, #0x368]
0x000ca874: e0 03 14 aa              mov      x0, x20
0x000ca878: e1 03 18 aa              mov      x1, x24
0x000ca87c: 00 01 3f d6              blr      x8
0x000ca880: 88 02 40 f9              ldr      x8, [x20]
0x000ca884: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000ca888: e2 3d 00 d0              adrp     x2, #0x888000
0x000ca88c: e0 03 14 aa              mov      x0, x20
0x000ca890: e1 03 17 aa              mov      x1, x23
0x000ca894: 42 d0 0f 91              add      x2, x2, #0x3f4
0x000ca898: e3 03 1a aa              mov      x3, x26
0x000ca89c: 00 01 3f d6              blr      x8
0x000ca8a0: 88 02 40 f9              ldr      x8, [x20]
0x000ca8a4: 29 03 1b 8b              add      x9, x25, x27
0x000ca8a8: 23 0d 40 b9              ldr      w3, [x9, #0xc]
0x000ca8ac: e2 03 00 aa              mov      x2, x0
0x000ca8b0: 08 b5 41 f9              ldr      x8, [x8, #0x368]
0x000ca8b4: e0 03 14 aa              mov      x0, x20
0x000ca8b8: e1 03 18 aa              mov      x1, x24
0x000ca8bc: 00 01 3f d6              blr      x8
0x000ca8c0: 88 02 40 f9              ldr      x8, [x20]
0x000ca8c4: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000ca8c8: 62 3b 00 b0              adrp     x2, #0x837000
0x000ca8cc: e0 03 14 aa              mov      x0, x20
0x000ca8d0: e1 03 17 aa              mov      x1, x23
0x000ca8d4: 42 c4 20 91              add      x2, x2, #0x831
0x000ca8d8: e3 03 1a aa              mov      x3, x26
0x000ca8dc: 00 01 3f d6              blr      x8
0x000ca8e0: 88 02 40 f9              ldr      x8, [x20]
0x000ca8e4: 39 03 1b 8b              add      x25, x25, x27
0x000ca8e8: 23 13 40 b9              ldr      w3, [x25, #0x10]
0x000ca8ec: e2 03 00 aa              mov      x2, x0
0x000ca8f0: 08 b5 41 f9              ldr      x8, [x8, #0x368]
0x000ca8f4: e0 03 14 aa              mov      x0, x20
0x000ca8f8: e1 03 18 aa              mov      x1, x24
0x000ca8fc: 00 01 3f d6              blr      x8
0x000ca900: 21 a3 41 a9              ldp      x1, x8, [x25, #0x18]
0x000ca904: 08 01 01 cb              sub      x8, x8, x1
0x000ca908: 1f 05 00 71              cmp      w8, #1
0x000ca90c: 4b 04 00 54              b.lt     #0xca994
0x000ca910: 89 02 40 f9              ldr      x9, [x20]
0x000ca914: 02 7d 40 93              sxtw     x2, w8
0x000ca918: 29 95 43 f9              ldr      x9, [x9, #0x728]
0x000ca91c: e0 03 14 aa              mov      x0, x20
0x000ca920: 20 01 3f d6              blr      x9
0x000ca924: f9 03 00 aa              mov      x25, x0
0x000ca928: 00 02 00 b4              cbz      x0, #0xca968
0x000ca92c: 88 02 40 f9              ldr      x8, [x20]
0x000ca930: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000ca934: 42 3b 00 d0              adrp     x2, #0x834000
0x000ca938: 43 3b 00 d0              adrp     x3, #0x834000
0x000ca93c: e0 03 14 aa              mov      x0, x20
0x000ca940: e1 03 17 aa              mov      x1, x23
0x000ca944: 42 08 25 91              add      x2, x2, #0x942
0x000ca948: 63 28 25 91              add      x3, x3, #0x94a
0x000ca94c: 00 01 3f d6              blr      x8
0x000ca950: e2 03 00 aa              mov      x2, x0
0x000ca954: e0 03 14 aa              mov      x0, x20
0x000ca958: e1 03 18 aa              mov      x1, x24
0x000ca95c: e3 03 19 aa              mov      x3, x25
0x000ca960: b5 e9 ff 97              bl       #0xc5034
0x000ca964: 07 00 00 14              b        #0xca980                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x1f2c
0x000ca968: 41 3b 00 b0              adrp     x1, #0x833000
0x000ca96c: 42 3b 00 d0              adrp     x2, #0x834000
0x000ca970: e0 07 1f 32              mov      w0, #6
0x000ca974: 21 24 31 91              add      x1, x1, #0xc49
0x000ca978: 42 8c 25 91              add      x2, x2, #0x963
0x000ca97c: 11 c0 ff 97              bl       #0xba9c0                   ; -> __android_log_print@plt
0x000ca980: 88 02 40 f9              ldr      x8, [x20]
0x000ca984: 08 5d 40 f9              ldr      x8, [x8, #0xb8]
0x000ca988: e0 03 14 aa              mov      x0, x20
0x000ca98c: e1 03 19 aa              mov      x1, x25
0x000ca990: 00 01 3f d6              blr      x8
0x000ca994: e1 57 40 f9              ldr      x1, [sp, #0xa8]
0x000ca998: e0 03 14 aa              mov      x0, x20
0x000ca99c: e2 03 16 aa              mov      x2, x22
0x000ca9a0: e3 03 1c aa              mov      x3, x28
0x000ca9a4: 29 05 00 94              bl       #0xcbe48
0x000ca9a8: 88 02 40 f9              ldr      x8, [x20]
0x000ca9ac: 08 5d 40 f9              ldr      x8, [x8, #0xb8]
0x000ca9b0: e0 03 14 aa              mov      x0, x20
0x000ca9b4: e1 03 1c aa              mov      x1, x28
0x000ca9b8: 00 01 3f d6              blr      x8
0x000ca9bc: f9 23 4d a9              ldp      x25, x8, [sp, #0xd0]
0x000ca9c0: 73 06 00 91              add      x19, x19, #1
0x000ca9c4: 7b 03 01 91              add      x27, x27, #0x40
0x000ca9c8: 09 01 19 cb              sub      x9, x8, x25
0x000ca9cc: 29 fd 46 d3              lsr      x9, x9, #6
0x000ca9d0: 7f c2 29 eb              cmp      x19, w9, sxtw
0x000ca9d4: eb ec ff 54              b.lt     #0xca770
0x000ca9d8: 19 45 00 b4              cbz      x25, #0xcb278
0x000ca9dc: 3f 03 08 eb              cmp      x25, x8
0x000ca9e0: a0 43 00 54              b.eq     #0xcb254
0x000ca9e4: 00 81 5d f8              ldur     x0, [x8, #-0x28]
0x000ca9e8: 13 01 01 d1              sub      x19, x8, #0x40
0x000ca9ec: 60 00 00 b4              cbz      x0, #0xca9f8
0x000ca9f0: 00 01 1e f8              stur     x0, [x8, #-0x20]
0x000ca9f4: 3f c1 ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000ca9f8: 3f 03 13 eb              cmp      x25, x19
0x000ca9fc: e8 03 13 aa              mov      x8, x19
0x000caa00: 21 ff ff 54              b.ne     #0xca9e4
0x000caa04: e0 6b 40 f9              ldr      x0, [sp, #0xd0]
0x000caa08: f9 6f 00 f9              str      x25, [sp, #0xd8]
0x000caa0c: 1a 02 00 14              b        #0xcb274                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x2820
0x000caa10: ff ff 0d a9              stp      xzr, xzr, [sp, #0xd8]
0x000caa14: ff 6b 00 f9              str      xzr, [sp, #0xd0]
0x000caa18: a0 9e 40 f9              ldr      x0, [x21, #0x138]
0x000caa1c: f6 e3 02 91              add      x22, sp, #0xb8
0x000caa20: e1 e3 02 91              add      x1, sp, #0xb8
0x000caa24: df c6 ff 97              bl       #0xbc5a0                   ; -> _ZN5ycnn29YCNNModel18getGeneralRecogOutEPNS_17KSGeneralRecogOutE@plt
0x000caa28: 88 02 40 f9              ldr      x8, [x20]
0x000caa2c: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000caa30: 41 3b 00 d0              adrp     x1, #0x834000
0x000caa34: 21 5c 32 91              add      x1, x1, #0xc97
0x000caa38: e0 03 14 aa              mov      x0, x20
0x000caa3c: 00 01 3f d6              blr      x8
0x000caa40: 88 02 40 f9              ldr      x8, [x20]
0x000caa44: f5 03 00 aa              mov      x21, x0
0x000caa48: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000caa4c: 42 3b 00 d0              adrp     x2, #0x834000
0x000caa50: 43 3b 00 d0              adrp     x3, #0x834000
0x000caa54: 42 3c 33 91              add      x2, x2, #0xccf
0x000caa58: 63 80 33 91              add      x3, x3, #0xce0
0x000caa5c: e0 03 14 aa              mov      x0, x20
0x000caa60: e1 03 13 aa              mov      x1, x19
0x000caa64: 00 01 3f d6              blr      x8
0x000caa68: 88 02 40 f9              ldr      x8, [x20]
0x000caa6c: e2 03 00 aa              mov      x2, x0
0x000caa70: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000caa74: e0 03 14 aa              mov      x0, x20
0x000caa78: e1 03 17 aa              mov      x1, x23
0x000caa7c: 00 01 3f d6              blr      x8
0x000caa80: 88 02 40 f9              ldr      x8, [x20]
0x000caa84: f3 03 00 aa              mov      x19, x0
0x000caa88: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000caa8c: 42 3b 00 d0              adrp     x2, #0x834000
0x000caa90: 43 3b 00 b0              adrp     x3, #0x833000
0x000caa94: 42 68 34 91              add      x2, x2, #0xd1a
0x000caa98: 63 fc 33 91              add      x3, x3, #0xcff
0x000caa9c: e0 03 14 aa              mov      x0, x20
0x000caaa0: e1 03 15 aa              mov      x1, x21
0x000caaa4: 00 01 3f d6              blr      x8
0x000caaa8: 88 02 40 f9              ldr      x8, [x20]
0x000caaac: e9 43 43 39              ldrb     w9, [sp, #0xd0]
0x000caab0: ea 73 40 f9              ldr      x10, [sp, #0xe0]
0x000caab4: cb 62 00 91              add      x11, x22, #0x18
0x000caab8: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x000caabc: 3f 01 00 72              tst      w9, #1
0x000caac0: f5 03 00 aa              mov      x21, x0
0x000caac4: 41 15 8b 9a              csinc    x1, x10, x11, ne
0x000caac8: e0 03 14 aa              mov      x0, x20
0x000caacc: 00 01 3f d6              blr      x8
0x000caad0: 88 02 40 f9              ldr      x8, [x20]
0x000caad4: f6 03 00 aa              mov      x22, x0
0x000caad8: 08 a1 41 f9              ldr      x8, [x8, #0x340]
0x000caadc: e0 03 14 aa              mov      x0, x20
0x000caae0: e1 03 13 aa              mov      x1, x19
0x000caae4: e2 03 15 aa              mov      x2, x21
0x000caae8: e3 03 16 aa              mov      x3, x22
0x000caaec: 00 01 3f d6              blr      x8
0x000caaf0: 88 02 40 f9              ldr      x8, [x20]
0x000caaf4: 08 5d 40 f9              ldr      x8, [x8, #0xb8]
0x000caaf8: e0 03 14 aa              mov      x0, x20
0x000caafc: e1 03 16 aa              mov      x1, x22
0x000cab00: 00 01 3f d6              blr      x8
0x000cab04: e8 43 43 39              ldrb     w8, [sp, #0xd0]
0x000cab08: 88 3b 00 36              tbz      w8, #0, #0xcb278
0x000cab0c: e0 73 40 f9              ldr      x0, [sp, #0xe0]
0x000cab10: d9 01 00 14              b        #0xcb274                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x2820
0x000cab14: f7 03 15 aa              mov      x23, x21
0x000cab18: ea 03 1f aa              mov      x10, xzr
0x000cab1c: f6 43 00 f9              str      x22, [sp, #0x80]
0x000cab20: e8 07 1d 32              mov      w8, #0x18
0x000cab24: 59 4d 08 9b              madd     x25, x10, x8, x19
0x000cab28: ea 4f 00 f9              str      x10, [sp, #0x98]
0x000cab2c: f5 03 19 aa              mov      x21, x25
0x000cab30: a8 8e 40 f8              ldr      x8, [x21, #8]!
0x000cab34: 29 03 40 f9              ldr      x9, [x25]
0x000cab38: 08 01 09 cb              sub      x8, x8, x9
0x000cab3c: 03 fd 43 93              asr      x3, x8, #3
0x000cab40: e1 0b 48 a9              ldp      x1, x2, [sp, #0x80]
0x000cab44: e0 03 14 aa              mov      x0, x20
0x000cab48: 62 e9 ff 97              bl       #0xc50d0
0x000cab4c: a8 02 40 f9              ldr      x8, [x21]
0x000cab50: 29 03 40 f9              ldr      x9, [x25]
0x000cab54: fc 03 00 aa              mov      x28, x0
0x000cab58: 1f 01 09 eb              cmp      x8, x9
0x000cab5c: 40 05 00 54              b.eq     #0xcac04
0x000cab60: fa 03 1f aa              mov      x26, xzr
0x000cab64: f6 03 1f aa              mov      x22, xzr
0x000cab68: e0 03 14 aa              mov      x0, x20
0x000cab6c: e1 03 17 aa              mov      x1, x23
0x000cab70: e2 03 18 aa              mov      x2, x24
0x000cab74: 57 e9 ff 97              bl       #0xc50d0
0x000cab78: 28 03 40 f9              ldr      x8, [x25]
0x000cab7c: 89 02 40 f9              ldr      x9, [x20]
0x000cab80: f3 03 00 aa              mov      x19, x0
0x000cab84: 08 01 1a 8b              add      x8, x8, x26
0x000cab88: 00 05 40 bd              ldr      s0, [x8, #4]
0x000cab8c: 28 bd 41 f9              ldr      x8, [x9, #0x378]
0x000cab90: e2 57 40 f9              ldr      x2, [sp, #0xa8]
0x000cab94: e0 03 14 aa              mov      x0, x20
0x000cab98: e1 03 13 aa              mov      x1, x19
0x000cab9c: 00 01 3f d6              blr      x8
0x000caba0: 28 03 40 f9              ldr      x8, [x25]
0x000caba4: 89 02 40 f9              ldr      x9, [x20]
0x000caba8: 03 69 7a b8              ldr      w3, [x8, x26]
0x000cabac: 28 b5 41 f9              ldr      x8, [x9, #0x368]
0x000cabb0: e2 53 40 f9              ldr      x2, [sp, #0xa0]
0x000cabb4: e0 03 14 aa              mov      x0, x20
0x000cabb8: e1 03 13 aa              mov      x1, x19
0x000cabbc: 00 01 3f d6              blr      x8
0x000cabc0: e0 03 14 aa              mov      x0, x20
0x000cabc4: e1 03 1c aa              mov      x1, x28
0x000cabc8: e2 03 1b aa              mov      x2, x27
0x000cabcc: e3 03 13 aa              mov      x3, x19
0x000cabd0: 9e 04 00 94              bl       #0xcbe48
0x000cabd4: 88 02 40 f9              ldr      x8, [x20]
0x000cabd8: 08 5d 40 f9              ldr      x8, [x8, #0xb8]
0x000cabdc: e0 03 14 aa              mov      x0, x20
0x000cabe0: e1 03 13 aa              mov      x1, x19
0x000cabe4: 00 01 3f d6              blr      x8
0x000cabe8: a8 02 40 f9              ldr      x8, [x21]
0x000cabec: 29 03 40 f9              ldr      x9, [x25]
0x000cabf0: d6 06 00 91              add      x22, x22, #1
0x000cabf4: 5a 23 00 91              add      x26, x26, #8
0x000cabf8: 08 01 09 cb              sub      x8, x8, x9
0x000cabfc: df 0e 88 eb              cmp      x22, x8, asr #3
0x000cac00: 43 fb ff 54              b.lo     #0xcab68
0x000cac04: e1 4b 40 f9              ldr      x1, [sp, #0x90]
0x000cac08: e0 03 14 aa              mov      x0, x20
0x000cac0c: e2 03 1b aa              mov      x2, x27
0x000cac10: e3 03 1c aa              mov      x3, x28
0x000cac14: 8d 04 00 94              bl       #0xcbe48
0x000cac18: 88 02 40 f9              ldr      x8, [x20]
0x000cac1c: 08 5d 40 f9              ldr      x8, [x8, #0xb8]
0x000cac20: e0 03 14 aa              mov      x0, x20
0x000cac24: e1 03 1c aa              mov      x1, x28
0x000cac28: 00 01 3f d6              blr      x8
0x000cac2c: f3 a3 51 a9              ldp      x19, x8, [sp, #0x118]
0x000cac30: ea 4f 40 f9              ldr      x10, [sp, #0x98]
0x000cac34: eb f3 01 b2              mov      x11, #-0x5555555555555556
0x000cac38: 6b 55 95 f2              movk     x11, #0xaaab
0x000cac3c: 09 01 13 cb              sub      x9, x8, x19
0x000cac40: 29 fd 43 93              asr      x9, x9, #3
0x000cac44: 4a 05 00 91              add      x10, x10, #1
0x000cac48: 29 7d 0b 9b              mul      x9, x9, x11
0x000cac4c: 3f 01 0a eb              cmp      x9, x10
0x000cac50: 88 f6 ff 54              b.hi     #0xcab20
0x000cac54: e0 03 13 aa              mov      x0, x19
0x000cac58: c0 01 00 b4              cbz      x0, #0xcac90
0x000cac5c: 7f 02 08 eb              cmp      x19, x8
0x000cac60: 40 01 00 54              b.eq     #0xcac88
0x000cac64: f4 03 08 aa              mov      x20, x8
0x000cac68: 80 8e 5e f8              ldr      x0, [x20, #-0x18]!
0x000cac6c: 60 00 00 b4              cbz      x0, #0xcac78
0x000cac70: 00 01 1f f8              stur     x0, [x8, #-0x10]
0x000cac74: 9f c0 ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000cac78: 7f 02 14 eb              cmp      x19, x20
0x000cac7c: e8 03 14 aa              mov      x8, x20
0x000cac80: 41 ff ff 54              b.ne     #0xcac68
0x000cac84: e0 8f 40 f9              ldr      x0, [sp, #0x118]
0x000cac88: f3 93 00 f9              str      x19, [sp, #0x120]
0x000cac8c: 99 c0 ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000cac90: e0 83 40 f9              ldr      x0, [sp, #0x100]
0x000cac94: 60 00 00 b4              cbz      x0, #0xcaca0
0x000cac98: e0 87 00 f9              str      x0, [sp, #0x108]
0x000cac9c: 95 c0 ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000caca0: e0 77 40 f9              ldr      x0, [sp, #0xe8]
0x000caca4: 60 00 00 b4              cbz      x0, #0xcacb0
0x000caca8: e0 7b 00 f9              str      x0, [sp, #0xf0]
0x000cacac: 91 c0 ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000cacb0: e0 6b 40 f9              ldr      x0, [sp, #0xd0]
0x000cacb4: 20 2e 00 b4              cbz      x0, #0xcb278
0x000cacb8: e0 6f 00 f9              str      x0, [sp, #0xd8]
0x000cacbc: 6e 01 00 14              b        #0xcb274                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x2820
0x000cacc0: f8 03 1f aa              mov      x24, xzr
0x000cacc4: f9 0b 1d 32              mov      w25, #0x38
0x000cacc8: f5 03 00 f9              str      x21, [sp]
0x000caccc: e2 2f 40 f9              ldr      x2, [sp, #0x58]
0x000cacd0: e0 03 14 aa              mov      x0, x20
0x000cacd4: e1 03 15 aa              mov      x1, x21
0x000cacd8: fe e8 ff 97              bl       #0xc50d0
0x000cacdc: e1 03 00 aa              mov      x1, x0
0x000cace0: 88 02 40 f9              ldr      x8, [x20]
0x000cace4: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000cace8: e2 23 40 f9              ldr      x2, [sp, #0x40]
0x000cacec: e0 03 14 aa              mov      x0, x20
0x000cacf0: e1 3f 00 f9              str      x1, [sp, #0x78]
0x000cacf4: 00 01 3f d6              blr      x8
0x000cacf8: 13 5f 19 9b              madd     x19, x24, x25, x23
0x000cacfc: f8 df 06 a9              stp      x24, x23, [sp, #0x68]
0x000cad00: 1f 20 03 d5              nop      
0x000cad04: 15 5f 19 9b              madd     x21, x24, x25, x23
0x000cad08: a8 8e 40 f8              ldr      x8, [x21, #8]!
0x000cad0c: 7a 02 40 f9              ldr      x26, [x19]
0x000cad10: 1f 01 1a eb              cmp      x8, x26
0x000cad14: c0 07 00 54              b.eq     #0xcae0c
0x000cad18: f6 03 00 aa              mov      x22, x0
0x000cad1c: f8 03 1f aa              mov      x24, xzr
0x000cad20: f9 03 1f aa              mov      x25, xzr
0x000cad24: e2 53 40 f9              ldr      x2, [sp, #0xa0]
0x000cad28: e0 03 14 aa              mov      x0, x20
0x000cad2c: e1 03 1c aa              mov      x1, x28
0x000cad30: f7 03 1c aa              mov      x23, x28
0x000cad34: e7 e8 ff 97              bl       #0xc50d0
0x000cad38: 88 02 40 f9              ldr      x8, [x20]
0x000cad3c: 40 6b 78 bc              ldr      s0, [x26, x24]
0x000cad40: fc 03 00 aa              mov      x28, x0
0x000cad44: 08 bd 41 f9              ldr      x8, [x8, #0x378]
0x000cad48: e2 4f 40 f9              ldr      x2, [sp, #0x98]
0x000cad4c: e0 03 14 aa              mov      x0, x20
0x000cad50: e1 03 1c aa              mov      x1, x28
0x000cad54: 00 01 3f d6              blr      x8
0x000cad58: 88 02 40 f9              ldr      x8, [x20]
0x000cad5c: 5b 03 18 8b              add      x27, x26, x24
0x000cad60: 60 07 40 bd              ldr      s0, [x27, #4]
0x000cad64: 08 bd 41 f9              ldr      x8, [x8, #0x378]
0x000cad68: e2 4b 40 f9              ldr      x2, [sp, #0x90]
0x000cad6c: e0 03 14 aa              mov      x0, x20
0x000cad70: e1 03 1c aa              mov      x1, x28
0x000cad74: 00 01 3f d6              blr      x8
0x000cad78: 88 02 40 f9              ldr      x8, [x20]
0x000cad7c: 60 0b 40 bd              ldr      s0, [x27, #8]
0x000cad80: 08 bd 41 f9              ldr      x8, [x8, #0x378]
0x000cad84: e2 47 40 f9              ldr      x2, [sp, #0x88]
0x000cad88: e0 03 14 aa              mov      x0, x20
0x000cad8c: e1 03 1c aa              mov      x1, x28
0x000cad90: 00 01 3f d6              blr      x8
0x000cad94: 88 02 40 f9              ldr      x8, [x20]
0x000cad98: 49 03 18 8b              add      x9, x26, x24
0x000cad9c: 23 11 40 b9              ldr      w3, [x9, #0x10]
0x000cada0: 08 b5 41 f9              ldr      x8, [x8, #0x368]
0x000cada4: e2 43 40 f9              ldr      x2, [sp, #0x80]
0x000cada8: e0 03 14 aa              mov      x0, x20
0x000cadac: e1 03 1c aa              mov      x1, x28
0x000cadb0: 00 01 3f d6              blr      x8
0x000cadb4: e2 57 40 f9              ldr      x2, [sp, #0xa8]
0x000cadb8: e0 03 14 aa              mov      x0, x20
0x000cadbc: e1 03 16 aa              mov      x1, x22
0x000cadc0: e3 03 1c aa              mov      x3, x28
0x000cadc4: 21 04 00 94              bl       #0xcbe48
0x000cadc8: 88 02 40 f9              ldr      x8, [x20]
0x000cadcc: 08 5d 40 f9              ldr      x8, [x8, #0xb8]
0x000cadd0: e0 03 14 aa              mov      x0, x20
0x000cadd4: e1 03 1c aa              mov      x1, x28
0x000cadd8: 00 01 3f d6              blr      x8
0x000caddc: a8 02 40 f9              ldr      x8, [x21]
0x000cade0: 7a 02 40 f9              ldr      x26, [x19]
0x000cade4: e9 f3 01 b2              mov      x9, #-0x5555555555555556
0x000cade8: 69 55 95 f2              movk     x9, #0xaaab
0x000cadec: 39 07 00 91              add      x25, x25, #1
0x000cadf0: 08 01 1a cb              sub      x8, x8, x26
0x000cadf4: 08 fd 43 93              asr      x8, x8, #3
0x000cadf8: 08 7d 09 9b              mul      x8, x8, x9
0x000cadfc: 1f 01 19 eb              cmp      x8, x25
0x000cae00: 18 63 00 91              add      x24, x24, #0x18
0x000cae04: fc 03 17 aa              mov      x28, x23
0x000cae08: e8 f8 ff 54              b.hi     #0xcad24
0x000cae0c: 88 02 40 f9              ldr      x8, [x20]
0x000cae10: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000cae14: f7 3f 40 f9              ldr      x23, [sp, #0x78]
0x000cae18: e2 1f 40 f9              ldr      x2, [sp, #0x38]
0x000cae1c: e0 03 14 aa              mov      x0, x20
0x000cae20: e1 03 17 aa              mov      x1, x23
0x000cae24: 00 01 3f d6              blr      x8
0x000cae28: f5 03 40 f9              ldr      x21, [sp]
0x000cae2c: f8 cf 46 a9              ldp      x24, x19, [sp, #0x68]
0x000cae30: f9 0b 1d 32              mov      w25, #0x38
0x000cae34: 88 02 40 f9              ldr      x8, [x20]
0x000cae38: 1f 20 03 d5              nop      
0x000cae3c: 09 4f 19 9b              madd     x9, x24, x25, x19
0x000cae40: 20 19 40 bd              ldr      s0, [x9, #0x18]
0x000cae44: f6 03 00 aa              mov      x22, x0
0x000cae48: 08 bd 41 f9              ldr      x8, [x8, #0x378]
0x000cae4c: e2 1b 40 f9              ldr      x2, [sp, #0x30]
0x000cae50: e0 03 14 aa              mov      x0, x20
0x000cae54: e1 03 16 aa              mov      x1, x22
0x000cae58: 00 01 3f d6              blr      x8
0x000cae5c: 88 02 40 f9              ldr      x8, [x20]
0x000cae60: 1f 20 03 d5              nop      
0x000cae64: 09 4f 19 9b              madd     x9, x24, x25, x19
0x000cae68: 20 1d 40 bd              ldr      s0, [x9, #0x1c]
0x000cae6c: 08 bd 41 f9              ldr      x8, [x8, #0x378]
0x000cae70: e2 17 40 f9              ldr      x2, [sp, #0x28]
0x000cae74: e0 03 14 aa              mov      x0, x20
0x000cae78: e1 03 16 aa              mov      x1, x22
0x000cae7c: 00 01 3f d6              blr      x8
0x000cae80: 88 02 40 f9              ldr      x8, [x20]
0x000cae84: 1f 20 03 d5              nop      
0x000cae88: 09 4f 19 9b              madd     x9, x24, x25, x19
0x000cae8c: 20 21 40 bd              ldr      s0, [x9, #0x20]
0x000cae90: 08 bd 41 f9              ldr      x8, [x8, #0x378]
0x000cae94: e2 13 40 f9              ldr      x2, [sp, #0x20]
0x000cae98: e0 03 14 aa              mov      x0, x20
0x000cae9c: e1 03 16 aa              mov      x1, x22
0x000caea0: 00 01 3f d6              blr      x8
0x000caea4: 88 02 40 f9              ldr      x8, [x20]
0x000caea8: 1f 20 03 d5              nop      
0x000caeac: 09 4f 19 9b              madd     x9, x24, x25, x19
0x000caeb0: 20 25 40 bd              ldr      s0, [x9, #0x24]
0x000caeb4: 08 bd 41 f9              ldr      x8, [x8, #0x378]
0x000caeb8: e2 0b 40 f9              ldr      x2, [sp, #0x10]
0x000caebc: e0 03 14 aa              mov      x0, x20
0x000caec0: e1 03 16 aa              mov      x1, x22
0x000caec4: 00 01 3f d6              blr      x8
0x000caec8: 88 02 40 f9              ldr      x8, [x20]
0x000caecc: 1f 20 03 d5              nop      
0x000caed0: 09 4f 19 9b              madd     x9, x24, x25, x19
0x000caed4: 23 29 40 b9              ldr      w3, [x9, #0x28]
0x000caed8: 08 b5 41 f9              ldr      x8, [x8, #0x368]
0x000caedc: e2 0f 40 f9              ldr      x2, [sp, #0x18]
0x000caee0: e0 03 14 aa              mov      x0, x20
0x000caee4: e1 03 17 aa              mov      x1, x23
0x000caee8: 00 01 3f d6              blr      x8
0x000caeec: 88 02 40 f9              ldr      x8, [x20]
0x000caef0: 1f 20 03 d5              nop      
0x000caef4: 09 4f 19 9b              madd     x9, x24, x25, x19
0x000caef8: 23 31 40 b9              ldr      w3, [x9, #0x30]
0x000caefc: 08 b5 41 f9              ldr      x8, [x8, #0x368]
0x000caf00: e2 07 40 f9              ldr      x2, [sp, #8]
0x000caf04: e0 03 14 aa              mov      x0, x20
0x000caf08: e1 03 17 aa              mov      x1, x23
0x000caf0c: 00 01 3f d6              blr      x8
0x000caf10: e1 33 40 f9              ldr      x1, [sp, #0x60]
0x000caf14: e2 57 40 f9              ldr      x2, [sp, #0xa8]
0x000caf18: e0 03 14 aa              mov      x0, x20
0x000caf1c: e3 03 17 aa              mov      x3, x23
0x000caf20: ca 03 00 94              bl       #0xcbe48
0x000caf24: 88 02 40 f9              ldr      x8, [x20]
0x000caf28: 08 5d 40 f9              ldr      x8, [x8, #0xb8]
0x000caf2c: e0 03 14 aa              mov      x0, x20
0x000caf30: e1 03 17 aa              mov      x1, x23
0x000caf34: 00 01 3f d6              blr      x8
0x000caf38: f7 23 4d a9              ldp      x23, x8, [sp, #0xd0]
0x000caf3c: ea b6 8d d2              mov      x10, #0x6db7
0x000caf40: 6a db b6 f2              movk     x10, #0xb6db, lsl #16
0x000caf44: aa 6d db f2              movk     x10, #0xdb6d, lsl #32
0x000caf48: 09 01 17 cb              sub      x9, x8, x23
0x000caf4c: 29 fd 43 93              asr      x9, x9, #3
0x000caf50: ca b6 ed f2              movk     x10, #0x6db6, lsl #48
0x000caf54: 18 07 00 91              add      x24, x24, #1
0x000caf58: 29 7d 0a 9b              mul      x9, x9, x10
0x000caf5c: 3f 01 18 eb              cmp      x9, x24
0x000caf60: 68 eb ff 54              b.hi     #0xcaccc
0x000caf64: e0 03 17 aa              mov      x0, x23
0x000caf68: 80 18 00 b4              cbz      x0, #0xcb278
0x000caf6c: ff 02 08 eb              cmp      x23, x8
0x000caf70: 40 01 00 54              b.eq     #0xcaf98
0x000caf74: f3 03 08 aa              mov      x19, x8
0x000caf78: 60 8e 5c f8              ldr      x0, [x19, #-0x38]!
0x000caf7c: 60 00 00 b4              cbz      x0, #0xcaf88
0x000caf80: 00 01 1d f8              stur     x0, [x8, #-0x30]
0x000caf84: db bf ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000caf88: ff 02 13 eb              cmp      x23, x19
0x000caf8c: e8 03 13 aa              mov      x8, x19
0x000caf90: 41 ff ff 54              b.ne     #0xcaf78
0x000caf94: e0 6b 40 f9              ldr      x0, [sp, #0xd0]
0x000caf98: f7 6f 00 f9              str      x23, [sp, #0xd8]
0x000caf9c: b6 00 00 14              b        #0xcb274                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x2820
0x000cafa0: 41 3b 00 b0              adrp     x1, #0x833000
0x000cafa4: 42 3b 00 d0              adrp     x2, #0x834000
0x000cafa8: 21 24 31 91              add      x1, x1, #0xc49
0x000cafac: 42 8c 25 91              add      x2, x2, #0x963
0x000cafb0: e0 07 1f 32              mov      w0, #6
0x000cafb4: 83 be ff 97              bl       #0xba9c0                   ; -> __android_log_print@plt
0x000cafb8: 88 02 40 f9              ldr      x8, [x20]
0x000cafbc: 08 5d 40 f9              ldr      x8, [x8, #0xb8]
0x000cafc0: e0 03 14 aa              mov      x0, x20
0x000cafc4: e1 03 16 aa              mov      x1, x22
0x000cafc8: 00 01 3f d6              blr      x8
0x000cafcc: e1 7b 40 f9              ldr      x1, [sp, #0xf0]
0x000cafd0: 41 15 00 b4              cbz      x1, #0xcb278
0x000cafd4: e1 7f 00 f9              str      x1, [sp, #0xf8]
0x000cafd8: e0 03 01 aa              mov      x0, x1
0x000cafdc: a6 00 00 14              b        #0xcb274                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x2820
0x000cafe0: 41 3b 00 b0              adrp     x1, #0x833000
0x000cafe4: 42 3b 00 d0              adrp     x2, #0x834000
0x000cafe8: 21 24 31 91              add      x1, x1, #0xc49
0x000cafec: 42 8c 25 91              add      x2, x2, #0x963
0x000caff0: e0 07 1f 32              mov      w0, #6
0x000caff4: 73 be ff 97              bl       #0xba9c0                   ; -> __android_log_print@plt
0x000caff8: 88 02 40 f9              ldr      x8, [x20]
0x000caffc: 08 5d 40 f9              ldr      x8, [x8, #0xb8]
0x000cb000: e0 03 14 aa              mov      x0, x20
0x000cb004: e1 03 16 aa              mov      x1, x22
0x000cb008: 00 01 3f d6              blr      x8
0x000cb00c: e0 b7 40 f9              ldr      x0, [sp, #0x168]
0x000cb010: 60 00 00 b4              cbz      x0, #0xcb01c
0x000cb014: e0 bb 00 f9              str      x0, [sp, #0x170]
0x000cb018: b6 bf ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000cb01c: e8 43 45 39              ldrb     w8, [sp, #0x150]
0x000cb020: 68 00 00 36              tbz      w8, #0, #0xcb02c
0x000cb024: e0 b3 40 f9              ldr      x0, [sp, #0x160]
0x000cb028: b2 bf ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000cb02c: e0 93 40 f9              ldr      x0, [sp, #0x120]
0x000cb030: 60 00 00 b4              cbz      x0, #0xcb03c
0x000cb034: e0 97 00 f9              str      x0, [sp, #0x128]
0x000cb038: ae bf ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000cb03c: e0 87 40 f9              ldr      x0, [sp, #0x108]
0x000cb040: 60 00 00 b4              cbz      x0, #0xcb04c
0x000cb044: e0 8b 00 f9              str      x0, [sp, #0x110]
0x000cb048: aa bf ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000cb04c: e0 73 40 f9              ldr      x0, [sp, #0xe0]
0x000cb050: 40 11 00 b4              cbz      x0, #0xcb278
0x000cb054: e0 77 00 f9              str      x0, [sp, #0xe8]
0x000cb058: 87 00 00 14              b        #0xcb274                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x2820
0x000cb05c: 41 3b 00 90              adrp     x1, #0x833000
0x000cb060: 42 3b 00 b0              adrp     x2, #0x834000
0x000cb064: 21 24 31 91              add      x1, x1, #0xc49
0x000cb068: 42 8c 25 91              add      x2, x2, #0x963
0x000cb06c: e0 07 1f 32              mov      w0, #6
0x000cb070: 54 be ff 97              bl       #0xba9c0                   ; -> __android_log_print@plt
0x000cb074: 88 02 40 f9              ldr      x8, [x20]
0x000cb078: 08 5d 40 f9              ldr      x8, [x8, #0xb8]
0x000cb07c: e0 03 14 aa              mov      x0, x20
0x000cb080: e1 03 18 aa              mov      x1, x24
0x000cb084: 00 01 3f d6              blr      x8
0x000cb088: e9 23 4d a9              ldp      x9, x8, [sp, #0xd0]
0x000cb08c: 08 01 09 cb              sub      x8, x8, x9
0x000cb090: e9 f3 01 b2              mov      x9, #-0x5555555555555556
0x000cb094: 08 fd 43 93              asr      x8, x8, #3
0x000cb098: 69 55 95 f2              movk     x9, #0xaaab
0x000cb09c: 08 7d 09 9b              mul      x8, x8, x9
0x000cb0a0: 1f 05 00 71              cmp      w8, #1
0x000cb0a4: 0b 0b 00 54              b.lt     #0xcb204
0x000cb0a8: f7 03 1f aa              mov      x23, xzr
0x000cb0ac: 08 7d 40 93              sxtw     x8, w8
0x000cb0b0: e8 db 05 a9              stp      x8, x22, [sp, #0x58]
0x000cb0b4: e2 3f 40 f9              ldr      x2, [sp, #0x78]
0x000cb0b8: e0 03 14 aa              mov      x0, x20
0x000cb0bc: e1 03 16 aa              mov      x1, x22
0x000cb0c0: 04 e8 ff 97              bl       #0xc50d0
0x000cb0c4: e1 03 00 aa              mov      x1, x0
0x000cb0c8: 88 02 40 f9              ldr      x8, [x20]
0x000cb0cc: 08 7d 41 f9              ldr      x8, [x8, #0x2f8]
0x000cb0d0: e2 3b 40 f9              ldr      x2, [sp, #0x70]
0x000cb0d4: e0 03 14 aa              mov      x0, x20
0x000cb0d8: e1 47 00 f9              str      x1, [sp, #0x88]
0x000cb0dc: 00 01 3f d6              blr      x8
0x000cb0e0: e8 6b 40 f9              ldr      x8, [sp, #0xd0]
0x000cb0e4: e9 07 1d 32              mov      w9, #0x18
0x000cb0e8: f9 22 09 9b              madd     x25, x23, x9, x8
0x000cb0ec: f6 03 19 aa              mov      x22, x25
0x000cb0f0: c8 8e 40 f8              ldr      x8, [x22, #8]!
0x000cb0f4: 35 03 40 f9              ldr      x21, [x25]
0x000cb0f8: 1f 01 15 eb              cmp      x8, x21
0x000cb0fc: 60 05 00 54              b.eq     #0xcb1a8
0x000cb100: f8 03 00 aa              mov      x24, x0
0x000cb104: fa 03 1f aa              mov      x26, xzr
0x000cb108: fb 03 1f aa              mov      x27, xzr
0x000cb10c: e2 53 40 f9              ldr      x2, [sp, #0xa0]
0x000cb110: e0 03 14 aa              mov      x0, x20
0x000cb114: e1 03 1c aa              mov      x1, x28
0x000cb118: f3 03 1c aa              mov      x19, x28
0x000cb11c: ed e7 ff 97              bl       #0xc50d0
0x000cb120: 88 02 40 f9              ldr      x8, [x20]
0x000cb124: a0 6a 7a bc              ldr      s0, [x21, x26]
0x000cb128: fc 03 00 aa              mov      x28, x0
0x000cb12c: 08 bd 41 f9              ldr      x8, [x8, #0x378]
0x000cb130: e2 4f 40 f9              ldr      x2, [sp, #0x98]
0x000cb134: e0 03 14 aa              mov      x0, x20
0x000cb138: e1 03 1c aa              mov      x1, x28
0x000cb13c: 00 01 3f d6              blr      x8
0x000cb140: 88 02 40 f9              ldr      x8, [x20]
0x000cb144: a9 02 1a 8b              add      x9, x21, x26
0x000cb148: 20 05 40 bd              ldr      s0, [x9, #4]
0x000cb14c: 08 bd 41 f9              ldr      x8, [x8, #0x378]
0x000cb150: e2 4b 40 f9              ldr      x2, [sp, #0x90]
0x000cb154: e0 03 14 aa              mov      x0, x20
0x000cb158: e1 03 1c aa              mov      x1, x28
0x000cb15c: 00 01 3f d6              blr      x8
0x000cb160: e2 57 40 f9              ldr      x2, [sp, #0xa8]
0x000cb164: e0 03 14 aa              mov      x0, x20
0x000cb168: e1 03 18 aa              mov      x1, x24
0x000cb16c: e3 03 1c aa              mov      x3, x28
0x000cb170: 36 03 00 94              bl       #0xcbe48
0x000cb174: 88 02 40 f9              ldr      x8, [x20]
0x000cb178: 08 5d 40 f9              ldr      x8, [x8, #0xb8]
0x000cb17c: e0 03 14 aa              mov      x0, x20
0x000cb180: e1 03 1c aa              mov      x1, x28
0x000cb184: 00 01 3f d6              blr      x8
0x000cb188: c8 02 40 f9              ldr      x8, [x22]
0x000cb18c: 35 03 40 f9              ldr      x21, [x25]
0x000cb190: 7b 07 00 91              add      x27, x27, #1
0x000cb194: 5a 23 00 91              add      x26, x26, #8
0x000cb198: fc 03 13 aa              mov      x28, x19
0x000cb19c: 08 01 15 cb              sub      x8, x8, x21
0x000cb1a0: 7f 0f 88 eb              cmp      x27, x8, asr #3
0x000cb1a4: 43 fb ff 54              b.lo     #0xcb10c
0x000cb1a8: 88 02 40 f9              ldr      x8, [x20]
0x000cb1ac: 08 b9 41 f9              ldr      x8, [x8, #0x370]
0x000cb1b0: f3 47 40 f9              ldr      x19, [sp, #0x88]
0x000cb1b4: e2 37 40 f9              ldr      x2, [sp, #0x68]
0x000cb1b8: e0 03 14 aa              mov      x0, x20
0x000cb1bc: e3 03 17 aa              mov      x3, x23
0x000cb1c0: e1 03 13 aa              mov      x1, x19
0x000cb1c4: 00 01 3f d6              blr      x8
0x000cb1c8: f6 33 40 f9              ldr      x22, [sp, #0x60]
0x000cb1cc: e1 43 40 f9              ldr      x1, [sp, #0x80]
0x000cb1d0: e2 57 40 f9              ldr      x2, [sp, #0xa8]
0x000cb1d4: e0 03 14 aa              mov      x0, x20
0x000cb1d8: e3 03 13 aa              mov      x3, x19
0x000cb1dc: 1b 03 00 94              bl       #0xcbe48
0x000cb1e0: 88 02 40 f9              ldr      x8, [x20]
0x000cb1e4: 08 5d 40 f9              ldr      x8, [x8, #0xb8]
0x000cb1e8: e0 03 14 aa              mov      x0, x20
0x000cb1ec: e1 03 13 aa              mov      x1, x19
0x000cb1f0: 00 01 3f d6              blr      x8
0x000cb1f4: e8 2f 40 f9              ldr      x8, [sp, #0x58]
0x000cb1f8: f7 06 00 91              add      x23, x23, #1
0x000cb1fc: ff 02 08 eb              cmp      x23, x8
0x000cb200: ab f5 ff 54              b.lt     #0xcb0b4
0x000cb204: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cb208: 60 00 00 b4              cbz      x0, #0xcb214
0x000cb20c: e0 83 00 f9              str      x0, [sp, #0x100]
0x000cb210: 38 bf ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000cb214: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb218: 13 03 00 b4              cbz      x19, #0xcb278
0x000cb21c: e8 6f 40 f9              ldr      x8, [sp, #0xd8]
0x000cb220: 1f 01 13 eb              cmp      x8, x19
0x000cb224: e0 01 00 54              b.eq     #0xcb260
0x000cb228: f4 03 08 aa              mov      x20, x8
0x000cb22c: 80 8e 5e f8              ldr      x0, [x20, #-0x18]!
0x000cb230: 60 00 00 b4              cbz      x0, #0xcb23c
0x000cb234: 00 01 1f f8              stur     x0, [x8, #-0x10]
0x000cb238: 2e bf ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000cb23c: 7f 02 14 eb              cmp      x19, x20
0x000cb240: e8 03 14 aa              mov      x8, x20
0x000cb244: 41 ff ff 54              b.ne     #0xcb22c
0x000cb248: e0 6b 40 f9              ldr      x0, [sp, #0xd0]
0x000cb24c: f3 6f 00 f9              str      x19, [sp, #0xd8]
0x000cb250: 09 00 00 14              b        #0xcb274                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x2820
0x000cb254: e0 03 19 aa              mov      x0, x25
0x000cb258: f9 6f 00 f9              str      x25, [sp, #0xd8]
0x000cb25c: 06 00 00 14              b        #0xcb274                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x2820
0x000cb260: e0 03 13 aa              mov      x0, x19
0x000cb264: f3 6f 00 f9              str      x19, [sp, #0xd8]
0x000cb268: 03 00 00 14              b        #0xcb274                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x2820
0x000cb26c: e0 03 18 aa              mov      x0, x24
0x000cb270: f8 6f 00 f9              str      x24, [sp, #0xd8]
0x000cb274: 1f bf ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000cb278: e0 2b 40 f9              ldr      x0, [sp, #0x50]
0x000cb27c: d5 c3 ff 97              bl       #0xbc1d0                   ; -> _ZNSt6__ndk15mutex6unlockEv@plt
0x000cb280: f3 27 40 f9              ldr      x19, [sp, #0x48]
0x000cb284: 68 16 40 f9              ldr      x8, [x19, #0x28]
0x000cb288: e9 5b 40 f9              ldr      x9, [sp, #0xb0]
0x000cb28c: 1f 01 09 eb              cmp      x8, x9
0x000cb290: 41 01 00 54              b.ne     #0xcb2b8
0x000cb294: fd 7b 5e a9              ldp      x29, x30, [sp, #0x1e0]
0x000cb298: f4 4f 5d a9              ldp      x20, x19, [sp, #0x1d0]
0x000cb29c: f6 57 5c a9              ldp      x22, x21, [sp, #0x1c0]
0x000cb2a0: f8 5f 5b a9              ldp      x24, x23, [sp, #0x1b0]
0x000cb2a4: fa 67 5a a9              ldp      x26, x25, [sp, #0x1a0]
0x000cb2a8: fc 6f 59 a9              ldp      x28, x27, [sp, #0x190]
0x000cb2ac: e0 03 1f 2a              mov      w0, wzr
0x000cb2b0: ff c3 07 91              add      sp, sp, #0x1f0
0x000cb2b4: c0 03 5f d6              ret      
0x000cb2b8: ae c1 ff 97              bl       #0xbb970                   ; -> __stack_chk_fail@plt
0x000cb2bc: f4 03 00 aa              mov      x20, x0
0x000cb2c0: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cb2c4: e0 54 00 b5              cbnz     x0, #0xcbd60
0x000cb2c8: a8 02 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cb2cc: f4 03 00 aa              mov      x20, x0
0x000cb2d0: e0 b7 40 f9              ldr      x0, [sp, #0x168]
0x000cb2d4: 40 3f 00 b5              cbnz     x0, #0xcbabc
0x000cb2d8: fb 01 00 14              b        #0xcbac4                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3070
0x000cb2dc: f4 03 00 aa              mov      x20, x0
0x000cb2e0: e0 7b 40 f9              ldr      x0, [sp, #0xf0]
0x000cb2e4: 40 42 00 b5              cbnz     x0, #0xcbb2c
0x000cb2e8: d2 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb2ec: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb2f0: f4 03 00 aa              mov      x20, x0
0x000cb2f4: d3 55 00 b5              cbnz     x19, #0xcbdac
0x000cb2f8: ce 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb2fc: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb300: f4 03 00 aa              mov      x20, x0
0x000cb304: 53 55 00 b5              cbnz     x19, #0xcbdac
0x000cb308: ca 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb30c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb310: f4 03 00 aa              mov      x20, x0
0x000cb314: d3 54 00 b5              cbnz     x19, #0xcbdac
0x000cb318: c6 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb31c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb320: f4 03 00 aa              mov      x20, x0
0x000cb324: 53 54 00 b5              cbnz     x19, #0xcbdac
0x000cb328: c2 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb32c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb330: f4 03 00 aa              mov      x20, x0
0x000cb334: d3 53 00 b5              cbnz     x19, #0xcbdac
0x000cb338: be 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb33c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb340: f4 03 00 aa              mov      x20, x0
0x000cb344: 53 53 00 b5              cbnz     x19, #0xcbdac
0x000cb348: ba 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb34c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb350: f4 03 00 aa              mov      x20, x0
0x000cb354: d3 52 00 b5              cbnz     x19, #0xcbdac
0x000cb358: b6 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb35c: f4 03 00 aa              mov      x20, x0
0x000cb360: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cb364: e0 4f 00 b5              cbnz     x0, #0xcbd60
0x000cb368: 80 02 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cb36c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb370: f4 03 00 aa              mov      x20, x0
0x000cb374: d3 51 00 b5              cbnz     x19, #0xcbdac
0x000cb378: ae 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb37c: f4 03 00 aa              mov      x20, x0
0x000cb380: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cb384: e0 4e 00 b5              cbnz     x0, #0xcbd60
0x000cb388: 78 02 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cb38c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb390: f4 03 00 aa              mov      x20, x0
0x000cb394: d3 50 00 b5              cbnz     x19, #0xcbdac
0x000cb398: a6 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb39c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb3a0: f4 03 00 aa              mov      x20, x0
0x000cb3a4: 53 50 00 b5              cbnz     x19, #0xcbdac
0x000cb3a8: a2 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb3ac: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb3b0: f4 03 00 aa              mov      x20, x0
0x000cb3b4: d3 4f 00 b5              cbnz     x19, #0xcbdac
0x000cb3b8: 9e 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb3bc: f4 03 00 aa              mov      x20, x0
0x000cb3c0: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cb3c4: e0 4c 00 b5              cbnz     x0, #0xcbd60
0x000cb3c8: 68 02 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cb3cc: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb3d0: f4 03 00 aa              mov      x20, x0
0x000cb3d4: d3 4e 00 b5              cbnz     x19, #0xcbdac
0x000cb3d8: 96 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb3dc: f4 03 00 aa              mov      x20, x0
0x000cb3e0: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cb3e4: e0 4b 00 b5              cbnz     x0, #0xcbd60
0x000cb3e8: 60 02 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cb3ec: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb3f0: f4 03 00 aa              mov      x20, x0
0x000cb3f4: d3 4d 00 b5              cbnz     x19, #0xcbdac
0x000cb3f8: 8e 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb3fc: f4 03 00 aa              mov      x20, x0
0x000cb400: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cb404: e0 4a 00 b5              cbnz     x0, #0xcbd60
0x000cb408: 58 02 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cb40c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb410: f4 03 00 aa              mov      x20, x0
0x000cb414: d3 4c 00 b5              cbnz     x19, #0xcbdac
0x000cb418: 86 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb41c: f4 03 00 aa              mov      x20, x0
0x000cb420: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cb424: e0 49 00 b5              cbnz     x0, #0xcbd60
0x000cb428: 50 02 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cb42c: f3 8f 40 f9              ldr      x19, [sp, #0x118]
0x000cb430: f4 03 00 aa              mov      x20, x0
0x000cb434: f3 44 00 b5              cbnz     x19, #0xcbcd0
0x000cb438: 34 02 00 14              b        #0xcbd08                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x32b4
0x000cb43c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb440: f4 03 00 aa              mov      x20, x0
0x000cb444: 53 4b 00 b5              cbnz     x19, #0xcbdac
0x000cb448: 7a 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb44c: f4 03 00 aa              mov      x20, x0
0x000cb450: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cb454: 60 48 00 b5              cbnz     x0, #0xcbd60
0x000cb458: 44 02 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cb45c: f3 8f 40 f9              ldr      x19, [sp, #0x118]
0x000cb460: f4 03 00 aa              mov      x20, x0
0x000cb464: 73 43 00 b5              cbnz     x19, #0xcbcd0
0x000cb468: 28 02 00 14              b        #0xcbd08                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x32b4
0x000cb46c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb470: f4 03 00 aa              mov      x20, x0
0x000cb474: d3 49 00 b5              cbnz     x19, #0xcbdac
0x000cb478: 6e 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb47c: f4 03 00 aa              mov      x20, x0
0x000cb480: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cb484: e0 46 00 b5              cbnz     x0, #0xcbd60
0x000cb488: 38 02 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cb48c: f3 8f 40 f9              ldr      x19, [sp, #0x118]
0x000cb490: f4 03 00 aa              mov      x20, x0
0x000cb494: f3 41 00 b5              cbnz     x19, #0xcbcd0
0x000cb498: 1c 02 00 14              b        #0xcbd08                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x32b4
0x000cb49c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb4a0: f4 03 00 aa              mov      x20, x0
0x000cb4a4: 53 48 00 b5              cbnz     x19, #0xcbdac
0x000cb4a8: 62 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb4ac: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb4b0: f4 03 00 aa              mov      x20, x0
0x000cb4b4: b3 49 00 b5              cbnz     x19, #0xcbde8
0x000cb4b8: 5e 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb4bc: f4 03 00 aa              mov      x20, x0
0x000cb4c0: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cb4c4: e0 44 00 b5              cbnz     x0, #0xcbd60
0x000cb4c8: 28 02 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cb4cc: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb4d0: f4 03 00 aa              mov      x20, x0
0x000cb4d4: 73 3c 00 b5              cbnz     x19, #0xcbc60
0x000cb4d8: 56 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb4dc: f3 8f 40 f9              ldr      x19, [sp, #0x118]
0x000cb4e0: f4 03 00 aa              mov      x20, x0
0x000cb4e4: 73 3f 00 b5              cbnz     x19, #0xcbcd0
0x000cb4e8: 08 02 00 14              b        #0xcbd08                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x32b4
0x000cb4ec: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb4f0: f4 03 00 aa              mov      x20, x0
0x000cb4f4: d3 45 00 b5              cbnz     x19, #0xcbdac
0x000cb4f8: 4e 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb4fc: f4 03 00 aa              mov      x20, x0
0x000cb500: e0 5f 40 f9              ldr      x0, [sp, #0xb8]
0x000cb504: 40 3d 00 b5              cbnz     x0, #0xcbcac
0x000cb508: 4a 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb50c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb510: f4 03 00 aa              mov      x20, x0
0x000cb514: b3 46 00 b5              cbnz     x19, #0xcbde8
0x000cb518: 46 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb51c: f4 03 00 aa              mov      x20, x0
0x000cb520: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cb524: e0 41 00 b5              cbnz     x0, #0xcbd60
0x000cb528: 10 02 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cb52c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb530: f4 03 00 aa              mov      x20, x0
0x000cb534: 73 39 00 b5              cbnz     x19, #0xcbc60
0x000cb538: 3e 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb53c: f3 8f 40 f9              ldr      x19, [sp, #0x118]
0x000cb540: f4 03 00 aa              mov      x20, x0
0x000cb544: 73 3c 00 b5              cbnz     x19, #0xcbcd0
0x000cb548: f0 01 00 14              b        #0xcbd08                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x32b4
0x000cb54c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb550: f4 03 00 aa              mov      x20, x0
0x000cb554: d3 42 00 b5              cbnz     x19, #0xcbdac
0x000cb558: 36 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb55c: f4 03 00 aa              mov      x20, x0
0x000cb560: e0 5f 40 f9              ldr      x0, [sp, #0xb8]
0x000cb564: 40 3a 00 b5              cbnz     x0, #0xcbcac
0x000cb568: 32 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb56c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb570: f4 03 00 aa              mov      x20, x0
0x000cb574: b3 43 00 b5              cbnz     x19, #0xcbde8
0x000cb578: 2e 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb57c: f4 03 00 aa              mov      x20, x0
0x000cb580: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cb584: e0 3e 00 b5              cbnz     x0, #0xcbd60
0x000cb588: f8 01 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cb58c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb590: f4 03 00 aa              mov      x20, x0
0x000cb594: 73 36 00 b5              cbnz     x19, #0xcbc60
0x000cb598: 26 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb59c: f3 8f 40 f9              ldr      x19, [sp, #0x118]
0x000cb5a0: f4 03 00 aa              mov      x20, x0
0x000cb5a4: 73 39 00 b5              cbnz     x19, #0xcbcd0
0x000cb5a8: d8 01 00 14              b        #0xcbd08                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x32b4
0x000cb5ac: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb5b0: f4 03 00 aa              mov      x20, x0
0x000cb5b4: d3 3f 00 b5              cbnz     x19, #0xcbdac
0x000cb5b8: 1e 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb5bc: f4 03 00 aa              mov      x20, x0
0x000cb5c0: e0 5f 40 f9              ldr      x0, [sp, #0xb8]
0x000cb5c4: 40 37 00 b5              cbnz     x0, #0xcbcac
0x000cb5c8: 1a 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb5cc: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb5d0: f4 03 00 aa              mov      x20, x0
0x000cb5d4: b3 40 00 b5              cbnz     x19, #0xcbde8
0x000cb5d8: 16 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb5dc: f4 03 00 aa              mov      x20, x0
0x000cb5e0: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cb5e4: e0 3b 00 b5              cbnz     x0, #0xcbd60
0x000cb5e8: e0 01 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cb5ec: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb5f0: f4 03 00 aa              mov      x20, x0
0x000cb5f4: 73 33 00 b5              cbnz     x19, #0xcbc60
0x000cb5f8: 0e 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb5fc: f3 8f 40 f9              ldr      x19, [sp, #0x118]
0x000cb600: f4 03 00 aa              mov      x20, x0
0x000cb604: 73 36 00 b5              cbnz     x19, #0xcbcd0
0x000cb608: c0 01 00 14              b        #0xcbd08                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x32b4
0x000cb60c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb610: f4 03 00 aa              mov      x20, x0
0x000cb614: d3 3c 00 b5              cbnz     x19, #0xcbdac
0x000cb618: 06 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb61c: f4 03 00 aa              mov      x20, x0
0x000cb620: e0 5f 40 f9              ldr      x0, [sp, #0xb8]
0x000cb624: 40 34 00 b5              cbnz     x0, #0xcbcac
0x000cb628: 02 02 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb62c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb630: f4 03 00 aa              mov      x20, x0
0x000cb634: b3 3d 00 b5              cbnz     x19, #0xcbde8
0x000cb638: fe 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb63c: f4 03 00 aa              mov      x20, x0
0x000cb640: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cb644: e0 38 00 b5              cbnz     x0, #0xcbd60
0x000cb648: c8 01 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cb64c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb650: f4 03 00 aa              mov      x20, x0
0x000cb654: 73 30 00 b5              cbnz     x19, #0xcbc60
0x000cb658: f6 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb65c: f3 8f 40 f9              ldr      x19, [sp, #0x118]
0x000cb660: f4 03 00 aa              mov      x20, x0
0x000cb664: 73 33 00 b5              cbnz     x19, #0xcbcd0
0x000cb668: a8 01 00 14              b        #0xcbd08                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x32b4
0x000cb66c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb670: f4 03 00 aa              mov      x20, x0
0x000cb674: d3 39 00 b5              cbnz     x19, #0xcbdac
0x000cb678: ee 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb67c: f4 03 00 aa              mov      x20, x0
0x000cb680: e0 5f 40 f9              ldr      x0, [sp, #0xb8]
0x000cb684: 40 31 00 b5              cbnz     x0, #0xcbcac
0x000cb688: ea 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb68c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb690: f4 03 00 aa              mov      x20, x0
0x000cb694: b3 3a 00 b5              cbnz     x19, #0xcbde8
0x000cb698: e6 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb69c: f4 03 00 aa              mov      x20, x0
0x000cb6a0: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cb6a4: e0 35 00 b5              cbnz     x0, #0xcbd60
0x000cb6a8: b0 01 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cb6ac: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb6b0: f4 03 00 aa              mov      x20, x0
0x000cb6b4: 73 2d 00 b5              cbnz     x19, #0xcbc60
0x000cb6b8: de 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb6bc: f3 8f 40 f9              ldr      x19, [sp, #0x118]
0x000cb6c0: f4 03 00 aa              mov      x20, x0
0x000cb6c4: 73 30 00 b5              cbnz     x19, #0xcbcd0
0x000cb6c8: 90 01 00 14              b        #0xcbd08                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x32b4
0x000cb6cc: f4 03 00 aa              mov      x20, x0
0x000cb6d0: e0 b7 40 f9              ldr      x0, [sp, #0x168]
0x000cb6d4: 40 1f 00 b5              cbnz     x0, #0xcbabc
0x000cb6d8: fb 00 00 14              b        #0xcbac4                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3070
0x000cb6dc: f4 03 00 aa              mov      x20, x0
0x000cb6e0: e0 7b 40 f9              ldr      x0, [sp, #0xf0]
0x000cb6e4: 40 22 00 b5              cbnz     x0, #0xcbb2c
0x000cb6e8: d2 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb6ec: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb6f0: f4 03 00 aa              mov      x20, x0
0x000cb6f4: d3 35 00 b5              cbnz     x19, #0xcbdac
0x000cb6f8: ce 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb6fc: f4 03 00 aa              mov      x20, x0
0x000cb700: e0 5f 40 f9              ldr      x0, [sp, #0xb8]
0x000cb704: 40 2d 00 b5              cbnz     x0, #0xcbcac
0x000cb708: ca 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb70c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb710: f4 03 00 aa              mov      x20, x0
0x000cb714: b3 36 00 b5              cbnz     x19, #0xcbde8
0x000cb718: c6 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb71c: f4 03 00 aa              mov      x20, x0
0x000cb720: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cb724: e0 31 00 b5              cbnz     x0, #0xcbd60
0x000cb728: 90 01 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cb72c: e8 43 43 39              ldrb     w8, [sp, #0xd0]
0x000cb730: f4 03 00 aa              mov      x20, x0
0x000cb734: 28 1b 00 37              tbnz     w8, #0, #0xcba98
0x000cb738: be 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb73c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb740: f4 03 00 aa              mov      x20, x0
0x000cb744: f3 28 00 b5              cbnz     x19, #0xcbc60
0x000cb748: ba 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb74c: f3 8f 40 f9              ldr      x19, [sp, #0x118]
0x000cb750: f4 03 00 aa              mov      x20, x0
0x000cb754: f3 2b 00 b5              cbnz     x19, #0xcbcd0
0x000cb758: 6c 01 00 14              b        #0xcbd08                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x32b4
0x000cb75c: f4 03 00 aa              mov      x20, x0
0x000cb760: e0 b7 40 f9              ldr      x0, [sp, #0x168]
0x000cb764: c0 1a 00 b5              cbnz     x0, #0xcbabc
0x000cb768: d7 00 00 14              b        #0xcbac4                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3070
0x000cb76c: f4 03 00 aa              mov      x20, x0
0x000cb770: e0 7b 40 f9              ldr      x0, [sp, #0xf0]
0x000cb774: c0 1d 00 b5              cbnz     x0, #0xcbb2c
0x000cb778: ae 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb77c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb780: f4 03 00 aa              mov      x20, x0
0x000cb784: 53 31 00 b5              cbnz     x19, #0xcbdac
0x000cb788: aa 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb78c: f4 03 00 aa              mov      x20, x0
0x000cb790: e0 5f 40 f9              ldr      x0, [sp, #0xb8]
0x000cb794: c0 28 00 b5              cbnz     x0, #0xcbcac
0x000cb798: a6 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb79c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb7a0: f4 03 00 aa              mov      x20, x0
0x000cb7a4: 33 32 00 b5              cbnz     x19, #0xcbde8
0x000cb7a8: a2 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb7ac: f4 03 00 aa              mov      x20, x0
0x000cb7b0: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cb7b4: 60 2d 00 b5              cbnz     x0, #0xcbd60
0x000cb7b8: 6c 01 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cb7bc: e8 43 43 39              ldrb     w8, [sp, #0xd0]
0x000cb7c0: f4 03 00 aa              mov      x20, x0
0x000cb7c4: a8 16 00 37              tbnz     w8, #0, #0xcba98
0x000cb7c8: 9a 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb7cc: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb7d0: f4 03 00 aa              mov      x20, x0
0x000cb7d4: 73 24 00 b5              cbnz     x19, #0xcbc60
0x000cb7d8: 96 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb7dc: f3 8f 40 f9              ldr      x19, [sp, #0x118]
0x000cb7e0: f4 03 00 aa              mov      x20, x0
0x000cb7e4: 73 27 00 b5              cbnz     x19, #0xcbcd0
0x000cb7e8: 48 01 00 14              b        #0xcbd08                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x32b4
0x000cb7ec: f4 03 00 aa              mov      x20, x0
0x000cb7f0: e0 b7 40 f9              ldr      x0, [sp, #0x168]
0x000cb7f4: 40 16 00 b5              cbnz     x0, #0xcbabc
0x000cb7f8: b3 00 00 14              b        #0xcbac4                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3070
0x000cb7fc: f4 03 00 aa              mov      x20, x0
0x000cb800: e0 7b 40 f9              ldr      x0, [sp, #0xf0]
0x000cb804: 40 19 00 b5              cbnz     x0, #0xcbb2c
0x000cb808: 8a 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb80c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb810: f4 03 00 aa              mov      x20, x0
0x000cb814: d3 2c 00 b5              cbnz     x19, #0xcbdac
0x000cb818: 86 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb81c: f4 03 00 aa              mov      x20, x0
0x000cb820: e0 5f 40 f9              ldr      x0, [sp, #0xb8]
0x000cb824: 40 24 00 b5              cbnz     x0, #0xcbcac
0x000cb828: 82 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb82c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb830: f4 03 00 aa              mov      x20, x0
0x000cb834: b3 2d 00 b5              cbnz     x19, #0xcbde8
0x000cb838: 7e 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb83c: f4 03 00 aa              mov      x20, x0
0x000cb840: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cb844: e0 28 00 b5              cbnz     x0, #0xcbd60
0x000cb848: 48 01 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cb84c: e8 43 43 39              ldrb     w8, [sp, #0xd0]
0x000cb850: f4 03 00 aa              mov      x20, x0
0x000cb854: 28 12 00 37              tbnz     w8, #0, #0xcba98
0x000cb858: 76 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb85c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb860: f4 03 00 aa              mov      x20, x0
0x000cb864: f3 1f 00 b5              cbnz     x19, #0xcbc60
0x000cb868: 72 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb86c: f3 8f 40 f9              ldr      x19, [sp, #0x118]
0x000cb870: f4 03 00 aa              mov      x20, x0
0x000cb874: f3 22 00 b5              cbnz     x19, #0xcbcd0
0x000cb878: 24 01 00 14              b        #0xcbd08                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x32b4
0x000cb87c: f4 03 00 aa              mov      x20, x0
0x000cb880: e0 b7 40 f9              ldr      x0, [sp, #0x168]
0x000cb884: c0 11 00 b5              cbnz     x0, #0xcbabc
0x000cb888: 8f 00 00 14              b        #0xcbac4                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3070
0x000cb88c: f4 03 00 aa              mov      x20, x0
0x000cb890: e0 7b 40 f9              ldr      x0, [sp, #0xf0]
0x000cb894: c0 14 00 b5              cbnz     x0, #0xcbb2c
0x000cb898: 66 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb89c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb8a0: f4 03 00 aa              mov      x20, x0
0x000cb8a4: 53 28 00 b5              cbnz     x19, #0xcbdac
0x000cb8a8: 62 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb8ac: f4 03 00 aa              mov      x20, x0
0x000cb8b0: e0 5f 40 f9              ldr      x0, [sp, #0xb8]
0x000cb8b4: c0 1f 00 b5              cbnz     x0, #0xcbcac
0x000cb8b8: 5e 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb8bc: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb8c0: f4 03 00 aa              mov      x20, x0
0x000cb8c4: 33 29 00 b5              cbnz     x19, #0xcbde8
0x000cb8c8: 5a 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb8cc: f4 03 00 aa              mov      x20, x0
0x000cb8d0: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cb8d4: 60 24 00 b5              cbnz     x0, #0xcbd60
0x000cb8d8: 24 01 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cb8dc: e8 43 43 39              ldrb     w8, [sp, #0xd0]
0x000cb8e0: f4 03 00 aa              mov      x20, x0
0x000cb8e4: a8 0d 00 37              tbnz     w8, #0, #0xcba98
0x000cb8e8: 52 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb8ec: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb8f0: f4 03 00 aa              mov      x20, x0
0x000cb8f4: 73 1b 00 b5              cbnz     x19, #0xcbc60
0x000cb8f8: 4e 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb8fc: f3 8f 40 f9              ldr      x19, [sp, #0x118]
0x000cb900: f4 03 00 aa              mov      x20, x0
0x000cb904: 73 1e 00 b5              cbnz     x19, #0xcbcd0
0x000cb908: 00 01 00 14              b        #0xcbd08                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x32b4
0x000cb90c: f4 03 00 aa              mov      x20, x0
0x000cb910: e0 b7 40 f9              ldr      x0, [sp, #0x168]
0x000cb914: 40 0d 00 b5              cbnz     x0, #0xcbabc
0x000cb918: 6b 00 00 14              b        #0xcbac4                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3070
0x000cb91c: f4 03 00 aa              mov      x20, x0
0x000cb920: e0 7b 40 f9              ldr      x0, [sp, #0xf0]
0x000cb924: 40 10 00 b5              cbnz     x0, #0xcbb2c
0x000cb928: 42 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb92c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb930: f4 03 00 aa              mov      x20, x0
0x000cb934: d3 23 00 b5              cbnz     x19, #0xcbdac
0x000cb938: 3e 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb93c: f4 03 00 aa              mov      x20, x0
0x000cb940: e0 5f 40 f9              ldr      x0, [sp, #0xb8]
0x000cb944: 40 1b 00 b5              cbnz     x0, #0xcbcac
0x000cb948: 3a 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb94c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb950: f4 03 00 aa              mov      x20, x0
0x000cb954: b3 24 00 b5              cbnz     x19, #0xcbde8
0x000cb958: 36 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb95c: f4 03 00 aa              mov      x20, x0
0x000cb960: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cb964: e0 1f 00 b5              cbnz     x0, #0xcbd60
0x000cb968: 00 01 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cb96c: e8 43 43 39              ldrb     w8, [sp, #0xd0]
0x000cb970: f4 03 00 aa              mov      x20, x0
0x000cb974: 28 09 00 37              tbnz     w8, #0, #0xcba98
0x000cb978: 2e 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb97c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb980: f4 03 00 aa              mov      x20, x0
0x000cb984: f3 16 00 b5              cbnz     x19, #0xcbc60
0x000cb988: 2a 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb98c: f3 8f 40 f9              ldr      x19, [sp, #0x118]
0x000cb990: f4 03 00 aa              mov      x20, x0
0x000cb994: f3 19 00 b5              cbnz     x19, #0xcbcd0
0x000cb998: dc 00 00 14              b        #0xcbd08                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x32b4
0x000cb99c: f4 03 00 aa              mov      x20, x0
0x000cb9a0: e0 b7 40 f9              ldr      x0, [sp, #0x168]
0x000cb9a4: c0 08 00 b5              cbnz     x0, #0xcbabc
0x000cb9a8: 47 00 00 14              b        #0xcbac4                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3070
0x000cb9ac: f4 03 00 aa              mov      x20, x0
0x000cb9b0: e0 7b 40 f9              ldr      x0, [sp, #0xf0]
0x000cb9b4: c0 0b 00 b5              cbnz     x0, #0xcbb2c
0x000cb9b8: 1e 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb9bc: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb9c0: f4 03 00 aa              mov      x20, x0
0x000cb9c4: 53 1f 00 b5              cbnz     x19, #0xcbdac
0x000cb9c8: 1a 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb9cc: f4 03 00 aa              mov      x20, x0
0x000cb9d0: e0 5f 40 f9              ldr      x0, [sp, #0xb8]
0x000cb9d4: c0 16 00 b5              cbnz     x0, #0xcbcac
0x000cb9d8: 16 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb9dc: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cb9e0: f4 03 00 aa              mov      x20, x0
0x000cb9e4: 33 20 00 b5              cbnz     x19, #0xcbde8
0x000cb9e8: 12 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cb9ec: f4 03 00 aa              mov      x20, x0
0x000cb9f0: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cb9f4: 60 1b 00 b5              cbnz     x0, #0xcbd60
0x000cb9f8: dc 00 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cb9fc: f4 03 00 aa              mov      x20, x0
0x000cba00: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cba04: e0 1a 00 b5              cbnz     x0, #0xcbd60
0x000cba08: d8 00 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cba0c: f4 03 00 aa              mov      x20, x0
0x000cba10: e0 b7 40 f9              ldr      x0, [sp, #0x168]
0x000cba14: 40 05 00 b5              cbnz     x0, #0xcbabc
0x000cba18: 2b 00 00 14              b        #0xcbac4                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3070
0x000cba1c: f4 03 00 aa              mov      x20, x0
0x000cba20: e0 7b 40 f9              ldr      x0, [sp, #0xf0]
0x000cba24: 40 08 00 b5              cbnz     x0, #0xcbb2c
0x000cba28: 02 01 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cba2c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cba30: f4 03 00 aa              mov      x20, x0
0x000cba34: d3 1b 00 b5              cbnz     x19, #0xcbdac
0x000cba38: fe 00 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cba3c: f4 03 00 aa              mov      x20, x0
0x000cba40: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cba44: e0 18 00 b5              cbnz     x0, #0xcbd60
0x000cba48: c8 00 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cba4c: f3 8f 40 f9              ldr      x19, [sp, #0x118]
0x000cba50: f4 03 00 aa              mov      x20, x0
0x000cba54: f3 13 00 b5              cbnz     x19, #0xcbcd0
0x000cba58: ac 00 00 14              b        #0xcbd08                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x32b4
0x000cba5c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cba60: f4 03 00 aa              mov      x20, x0
0x000cba64: 33 1c 00 b5              cbnz     x19, #0xcbde8
0x000cba68: f2 00 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cba6c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cba70: f4 03 00 aa              mov      x20, x0
0x000cba74: 73 0f 00 b5              cbnz     x19, #0xcbc60
0x000cba78: ee 00 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cba7c: f4 03 00 aa              mov      x20, x0
0x000cba80: e0 5f 40 f9              ldr      x0, [sp, #0xb8]
0x000cba84: 40 11 00 b5              cbnz     x0, #0xcbcac
0x000cba88: ea 00 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cba8c: e8 43 43 39              ldrb     w8, [sp, #0xd0]
0x000cba90: f4 03 00 aa              mov      x20, x0
0x000cba94: e8 1c 00 36              tbz      w8, #0, #0xcbe30
0x000cba98: e0 73 40 f9              ldr      x0, [sp, #0xe0]
0x000cba9c: e4 00 00 14              b        #0xcbe2c                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33d8
0x000cbaa0: f4 03 00 aa              mov      x20, x0
0x000cbaa4: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cbaa8: c0 15 00 b5              cbnz     x0, #0xcbd60
0x000cbaac: af 00 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cbab0: f4 03 00 aa              mov      x20, x0
0x000cbab4: e0 b7 40 f9              ldr      x0, [sp, #0x168]
0x000cbab8: 60 00 00 b4              cbz      x0, #0xcbac4
0x000cbabc: e0 bb 00 f9              str      x0, [sp, #0x170]
0x000cbac0: 0c bd ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000cbac4: e8 43 45 39              ldrb     w8, [sp, #0x150]
0x000cbac8: 08 01 00 37              tbnz     w8, #0, #0xcbae8
0x000cbacc: e0 93 40 f9              ldr      x0, [sp, #0x120]
0x000cbad0: 40 01 00 b5              cbnz     x0, #0xcbaf8
0x000cbad4: e0 87 40 f9              ldr      x0, [sp, #0x108]
0x000cbad8: 80 01 00 b5              cbnz     x0, #0xcbb08
0x000cbadc: e0 73 40 f9              ldr      x0, [sp, #0xe0]
0x000cbae0: c0 01 00 b5              cbnz     x0, #0xcbb18
0x000cbae4: d3 00 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cbae8: e0 b3 40 f9              ldr      x0, [sp, #0x160]
0x000cbaec: 01 bd ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000cbaf0: e0 93 40 f9              ldr      x0, [sp, #0x120]
0x000cbaf4: 00 ff ff b4              cbz      x0, #0xcbad4
0x000cbaf8: e0 97 00 f9              str      x0, [sp, #0x128]
0x000cbafc: fd bc ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000cbb00: e0 87 40 f9              ldr      x0, [sp, #0x108]
0x000cbb04: c0 fe ff b4              cbz      x0, #0xcbadc
0x000cbb08: e0 8b 00 f9              str      x0, [sp, #0x110]
0x000cbb0c: f9 bc ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000cbb10: e0 73 40 f9              ldr      x0, [sp, #0xe0]
0x000cbb14: e0 18 00 b4              cbz      x0, #0xcbe30
0x000cbb18: e0 77 00 f9              str      x0, [sp, #0xe8]
0x000cbb1c: c4 00 00 14              b        #0xcbe2c                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33d8
0x000cbb20: f4 03 00 aa              mov      x20, x0
0x000cbb24: e0 7b 40 f9              ldr      x0, [sp, #0xf0]
0x000cbb28: 40 18 00 b4              cbz      x0, #0xcbe30
0x000cbb2c: e0 7f 00 f9              str      x0, [sp, #0xf8]
0x000cbb30: bf 00 00 14              b        #0xcbe2c                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33d8
0x000cbb34: f4 03 00 aa              mov      x20, x0
0x000cbb38: e0 2b 40 f9              ldr      x0, [sp, #0x50]
0x000cbb3c: a5 c1 ff 97              bl       #0xbc1d0                   ; -> _ZNSt6__ndk15mutex6unlockEv@plt
0x000cbb40: e0 03 14 aa              mov      x0, x20
0x000cbb44: a3 b8 ff 97              bl       #0xb9dd0                   ; -> _Unwind_Resume@plt
0x000cbb48: f4 03 00 aa              mov      x20, x0
0x000cbb4c: e0 2b 40 f9              ldr      x0, [sp, #0x50]
0x000cbb50: a0 c1 ff 97              bl       #0xbc1d0                   ; -> _ZNSt6__ndk15mutex6unlockEv@plt
0x000cbb54: e0 03 14 aa              mov      x0, x20
0x000cbb58: 9e b8 ff 97              bl       #0xb9dd0                   ; -> _Unwind_Resume@plt
0x000cbb5c: f4 03 00 aa              mov      x20, x0
0x000cbb60: e0 2b 40 f9              ldr      x0, [sp, #0x50]
0x000cbb64: 9b c1 ff 97              bl       #0xbc1d0                   ; -> _ZNSt6__ndk15mutex6unlockEv@plt
0x000cbb68: e0 03 14 aa              mov      x0, x20
0x000cbb6c: 99 b8 ff 97              bl       #0xb9dd0                   ; -> _Unwind_Resume@plt
0x000cbb70: f4 03 00 aa              mov      x20, x0
0x000cbb74: e0 2b 40 f9              ldr      x0, [sp, #0x50]
0x000cbb78: 96 c1 ff 97              bl       #0xbc1d0                   ; -> _ZNSt6__ndk15mutex6unlockEv@plt
0x000cbb7c: e0 03 14 aa              mov      x0, x20
0x000cbb80: 94 b8 ff 97              bl       #0xb9dd0                   ; -> _Unwind_Resume@plt
0x000cbb84: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cbb88: f4 03 00 aa              mov      x20, x0
0x000cbb8c: b3 06 00 b5              cbnz     x19, #0xcbc60
0x000cbb90: a8 00 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cbb94: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cbb98: f4 03 00 aa              mov      x20, x0
0x000cbb9c: 93 10 00 b5              cbnz     x19, #0xcbdac
0x000cbba0: a4 00 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cbba4: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cbba8: f4 03 00 aa              mov      x20, x0
0x000cbbac: 13 10 00 b5              cbnz     x19, #0xcbdac
0x000cbbb0: a0 00 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cbbb4: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cbbb8: f4 03 00 aa              mov      x20, x0
0x000cbbbc: 73 11 00 b5              cbnz     x19, #0xcbde8
0x000cbbc0: 9c 00 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cbbc4: f4 03 00 aa              mov      x20, x0
0x000cbbc8: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cbbcc: a0 0c 00 b5              cbnz     x0, #0xcbd60
0x000cbbd0: 66 00 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cbbd4: f4 03 00 aa              mov      x20, x0
0x000cbbd8: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cbbdc: 20 0c 00 b5              cbnz     x0, #0xcbd60
0x000cbbe0: 62 00 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cbbe4: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cbbe8: f4 03 00 aa              mov      x20, x0
0x000cbbec: b3 03 00 b5              cbnz     x19, #0xcbc60
0x000cbbf0: 90 00 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cbbf4: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cbbf8: f4 03 00 aa              mov      x20, x0
0x000cbbfc: 33 03 00 b5              cbnz     x19, #0xcbc60
0x000cbc00: 8c 00 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cbc04: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cbc08: f4 03 00 aa              mov      x20, x0
0x000cbc0c: b3 02 00 b5              cbnz     x19, #0xcbc60
0x000cbc10: 88 00 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cbc14: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cbc18: f4 03 00 aa              mov      x20, x0
0x000cbc1c: 73 0e 00 b5              cbnz     x19, #0xcbde8
0x000cbc20: 84 00 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cbc24: f3 8f 40 f9              ldr      x19, [sp, #0x118]
0x000cbc28: f4 03 00 aa              mov      x20, x0
0x000cbc2c: 33 05 00 b5              cbnz     x19, #0xcbcd0
0x000cbc30: 36 00 00 14              b        #0xcbd08                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x32b4
0x000cbc34: f4 03 00 aa              mov      x20, x0
0x000cbc38: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cbc3c: 20 09 00 b5              cbnz     x0, #0xcbd60
0x000cbc40: 4a 00 00 14              b        #0xcbd68                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x3314
0x000cbc44: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cbc48: f4 03 00 aa              mov      x20, x0
0x000cbc4c: b3 00 00 b5              cbnz     x19, #0xcbc60
0x000cbc50: 78 00 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cbc54: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cbc58: f4 03 00 aa              mov      x20, x0
0x000cbc5c: b3 0e 00 b4              cbz      x19, #0xcbe30
0x000cbc60: e8 6f 40 f9              ldr      x8, [sp, #0xd8]
0x000cbc64: 1f 01 13 eb              cmp      x8, x19
0x000cbc68: c0 0e 00 54              b.eq     #0xcbe40
0x000cbc6c: 00 81 5d f8              ldur     x0, [x8, #-0x28]
0x000cbc70: 15 01 01 d1              sub      x21, x8, #0x40
0x000cbc74: 60 00 00 b4              cbz      x0, #0xcbc80
0x000cbc78: 00 01 1e f8              stur     x0, [x8, #-0x20]
0x000cbc7c: 9d bc ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000cbc80: 7f 02 15 eb              cmp      x19, x21
0x000cbc84: e8 03 15 aa              mov      x8, x21
0x000cbc88: 21 ff ff 54              b.ne     #0xcbc6c
0x000cbc8c: 66 00 00 14              b        #0xcbe24                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33d0
0x000cbc90: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cbc94: f4 03 00 aa              mov      x20, x0
0x000cbc98: b3 08 00 b5              cbnz     x19, #0xcbdac
0x000cbc9c: 65 00 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cbca0: f4 03 00 aa              mov      x20, x0
0x000cbca4: e0 5f 40 f9              ldr      x0, [sp, #0xb8]
0x000cbca8: 40 0c 00 b4              cbz      x0, #0xcbe30
0x000cbcac: e0 63 00 f9              str      x0, [sp, #0xc0]
0x000cbcb0: 5f 00 00 14              b        #0xcbe2c                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33d8
0x000cbcb4: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cbcb8: f4 03 00 aa              mov      x20, x0
0x000cbcbc: 73 09 00 b5              cbnz     x19, #0xcbde8
0x000cbcc0: 5c 00 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cbcc4: f3 8f 40 f9              ldr      x19, [sp, #0x118]
0x000cbcc8: f4 03 00 aa              mov      x20, x0
0x000cbccc: f3 01 00 b4              cbz      x19, #0xcbd08
0x000cbcd0: e8 93 40 f9              ldr      x8, [sp, #0x120]
0x000cbcd4: 1f 01 13 eb              cmp      x8, x19
0x000cbcd8: a0 03 00 54              b.eq     #0xcbd4c
0x000cbcdc: f5 03 08 aa              mov      x21, x8
0x000cbce0: a0 8e 5e f8              ldr      x0, [x21, #-0x18]!
0x000cbce4: 60 00 00 b4              cbz      x0, #0xcbcf0
0x000cbce8: 00 01 1f f8              stur     x0, [x8, #-0x10]
0x000cbcec: 81 bc ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000cbcf0: 7f 02 15 eb              cmp      x19, x21
0x000cbcf4: e8 03 15 aa              mov      x8, x21
0x000cbcf8: 41 ff ff 54              b.ne     #0xcbce0
0x000cbcfc: e0 8f 40 f9              ldr      x0, [sp, #0x118]
0x000cbd00: f3 93 00 f9              str      x19, [sp, #0x120]
0x000cbd04: 7b bc ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000cbd08: e0 83 40 f9              ldr      x0, [sp, #0x100]
0x000cbd0c: c0 00 00 b5              cbnz     x0, #0xcbd24
0x000cbd10: e0 77 40 f9              ldr      x0, [sp, #0xe8]
0x000cbd14: 00 01 00 b5              cbnz     x0, #0xcbd34
0x000cbd18: e0 6b 40 f9              ldr      x0, [sp, #0xd0]
0x000cbd1c: 40 01 00 b5              cbnz     x0, #0xcbd44
0x000cbd20: 44 00 00 14              b        #0xcbe30                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33dc
0x000cbd24: e0 87 00 f9              str      x0, [sp, #0x108]
0x000cbd28: 72 bc ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000cbd2c: e0 77 40 f9              ldr      x0, [sp, #0xe8]
0x000cbd30: 40 ff ff b4              cbz      x0, #0xcbd18
0x000cbd34: e0 7b 00 f9              str      x0, [sp, #0xf0]
0x000cbd38: 6e bc ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000cbd3c: e0 6b 40 f9              ldr      x0, [sp, #0xd0]
0x000cbd40: 80 07 00 b4              cbz      x0, #0xcbe30
0x000cbd44: e0 6f 00 f9              str      x0, [sp, #0xd8]
0x000cbd48: 39 00 00 14              b        #0xcbe2c                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33d8
0x000cbd4c: e0 03 13 aa              mov      x0, x19
0x000cbd50: ec ff ff 17              b        #0xcbd00                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x32ac
0x000cbd54: f4 03 00 aa              mov      x20, x0
0x000cbd58: e0 7f 40 f9              ldr      x0, [sp, #0xf8]
0x000cbd5c: 60 00 00 b4              cbz      x0, #0xcbd68
0x000cbd60: e0 83 00 f9              str      x0, [sp, #0x100]
0x000cbd64: 63 bc ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000cbd68: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cbd6c: 33 06 00 b4              cbz      x19, #0xcbe30
0x000cbd70: e8 6f 40 f9              ldr      x8, [sp, #0xd8]
0x000cbd74: 1f 01 13 eb              cmp      x8, x19
0x000cbd78: 40 06 00 54              b.eq     #0xcbe40
0x000cbd7c: f5 03 08 aa              mov      x21, x8
0x000cbd80: a0 8e 5e f8              ldr      x0, [x21, #-0x18]!
0x000cbd84: 60 00 00 b4              cbz      x0, #0xcbd90
0x000cbd88: 00 01 1f f8              stur     x0, [x8, #-0x10]
0x000cbd8c: 59 bc ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000cbd90: 7f 02 15 eb              cmp      x19, x21
0x000cbd94: e8 03 15 aa              mov      x8, x21
0x000cbd98: 41 ff ff 54              b.ne     #0xcbd80
0x000cbd9c: 22 00 00 14              b        #0xcbe24                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33d0
0x000cbda0: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cbda4: f4 03 00 aa              mov      x20, x0
0x000cbda8: 53 04 00 b4              cbz      x19, #0xcbe30
0x000cbdac: e8 6f 40 f9              ldr      x8, [sp, #0xd8]
0x000cbdb0: 1f 01 13 eb              cmp      x8, x19
0x000cbdb4: 60 04 00 54              b.eq     #0xcbe40
0x000cbdb8: f5 03 08 aa              mov      x21, x8
0x000cbdbc: a0 8e 5c f8              ldr      x0, [x21, #-0x38]!
0x000cbdc0: 60 00 00 b4              cbz      x0, #0xcbdcc
0x000cbdc4: 00 01 1d f8              stur     x0, [x8, #-0x30]
0x000cbdc8: 4a bc ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000cbdcc: 7f 02 15 eb              cmp      x19, x21
0x000cbdd0: e8 03 15 aa              mov      x8, x21
0x000cbdd4: 41 ff ff 54              b.ne     #0xcbdbc
0x000cbdd8: 13 00 00 14              b        #0xcbe24                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33d0
0x000cbddc: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000cbde0: f4 03 00 aa              mov      x20, x0
0x000cbde4: 73 02 00 b4              cbz      x19, #0xcbe30
0x000cbde8: f5 6f 40 f9              ldr      x21, [sp, #0xd8]
0x000cbdec: bf 02 13 eb              cmp      x21, x19
0x000cbdf0: 80 02 00 54              b.eq     #0xcbe40
0x000cbdf4: a0 82 5e f8              ldur     x0, [x21, #-0x18]
0x000cbdf8: 60 00 00 b4              cbz      x0, #0xcbe04
0x000cbdfc: a0 02 1f f8              stur     x0, [x21, #-0x10]
0x000cbe00: 3c bc ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000cbe04: a0 82 59 f8              ldur     x0, [x21, #-0x68]
0x000cbe08: b6 c2 01 d1              sub      x22, x21, #0x70
0x000cbe0c: 60 00 00 b4              cbz      x0, #0xcbe18
0x000cbe10: a0 02 1a f8              stur     x0, [x21, #-0x60]
0x000cbe14: 37 bc ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000cbe18: 7f 02 16 eb              cmp      x19, x22
0x000cbe1c: f5 03 16 aa              mov      x21, x22
0x000cbe20: a1 fe ff 54              b.ne     #0xcbdf4
0x000cbe24: e0 6b 40 f9              ldr      x0, [sp, #0xd0]
0x000cbe28: f3 6f 00 f9              str      x19, [sp, #0xd8]
0x000cbe2c: 31 bc ff 97              bl       #0xbaef0                   ; -> _ZdlPv@plt
0x000cbe30: e0 2b 40 f9              ldr      x0, [sp, #0x50]
0x000cbe34: e7 c0 ff 97              bl       #0xbc1d0                   ; -> _ZNSt6__ndk15mutex6unlockEv@plt
0x000cbe38: e0 03 14 aa              mov      x0, x20
0x000cbe3c: e5 b7 ff 97              bl       #0xb9dd0                   ; -> _Unwind_Resume@plt
0x000cbe40: e0 03 13 aa              mov      x0, x19
0x000cbe44: f9 ff ff 17              b        #0xcbe28                   ; -> Java_com_kwai_kscnnrenderlib_KSJNILib_updateModelOut+0x33d4
