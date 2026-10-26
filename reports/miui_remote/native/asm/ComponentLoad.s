; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : ComponentLoad (0x30b04)
; start   : 0x30b04
; size    : 0x734
; bytes   : 0x734   instructions: 461
; generated: 2026-10-23T23:29:58

0x00030b04: ff 43 01 d1              sub      sp, sp, #0x50
0x00030b08: f7 0b 00 f9              str      x23, [sp, #0x10]
0x00030b0c: f6 57 02 a9              stp      x22, x21, [sp, #0x20]
0x00030b10: f4 4f 03 a9              stp      x20, x19, [sp, #0x30]
0x00030b14: fd 7b 04 a9              stp      x29, x30, [sp, #0x40]
0x00030b18: fd 03 01 91              add      x29, sp, #0x40
0x00030b1c: 48 d0 3b d5              mrs      x8, tpidr_el0
0x00030b20: 08 15 40 f9              ldr      x8, [x8, #0x28]
0x00030b24: f3 03 03 aa              mov      x19, x3
0x00030b28: f6 03 00 aa              mov      x22, x0
0x00030b2c: e0 03 13 aa              mov      x0, x19
0x00030b30: f4 03 02 2a              mov      w20, w2
0x00030b34: f5 03 01 aa              mov      x21, x1
0x00030b38: e8 07 00 f9              str      x8, [sp, #8]
0x00030b3c: 0d 74 ff 97              bl       #0xdb70                    ; -> SoilMode@plt
0x00030b40: 1f 04 00 71              cmp      w0, #1
0x00030b44: c1 00 00 54              b.ne     #0x30b5c
0x00030b48: e0 03 13 aa              mov      x0, x19
0x00030b4c: e5 72 ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x00030b50: f7 03 00 2a              mov      w23, w0
0x00030b54: f7 07 00 b9              str      w23, [sp, #4]
0x00030b58: 0e 00 00 14              b        #0x30b90                   ; -> ComponentLoad+0x8c
0x00030b5c: e0 03 15 aa              mov      x0, x21
0x00030b60: e1 03 14 2a              mov      w1, w20
0x00030b64: 3b 73 ff 97              bl       #0xd850                    ; -> ModelComponentType@plt
0x00030b68: f7 03 00 2a              mov      w23, w0
0x00030b6c: e0 03 13 aa              mov      x0, x19
0x00030b70: f7 07 00 b9              str      w23, [sp, #4]
0x00030b74: ff 73 ff 97              bl       #0xdb70                    ; -> SoilMode@plt
0x00030b78: 1f 08 00 71              cmp      w0, #2
0x00030b7c: a1 00 00 54              b.ne     #0x30b90
0x00030b80: e0 13 00 91              add      x0, sp, #4
0x00030b84: e1 03 13 aa              mov      x1, x19
0x00030b88: 52 71 ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x00030b8c: f7 07 40 b9              ldr      w23, [sp, #4]
0x00030b90: e8 03 17 2a              mov      w8, w23
0x00030b94: ff 56 00 71              cmp      w23, #0x15
0x00030b98: 88 20 00 54              b.hi     #0x30fa8
0x00030b9c: e9 00 00 f0              adrp     x9, #0x4f000
0x00030ba0: 29 51 29 91              add      x9, x9, #0xa54
0x00030ba4: 28 79 a8 b8              ldrsw    x8, [x9, x8, lsl #2]
0x00030ba8: 08 01 09 8b              add      x8, x8, x9
0x00030bac: 00 01 1f d6              br       x8
0x00030bb0: 60 02 40 f9              ldr      x0, [x19]
0x00030bb4: 01 0d 80 52              mov      w1, #0x68
0x00030bb8: 4a 71 ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x00030bbc: c0 02 00 f9              str      x0, [x22]
0x00030bc0: 1f 1c 00 f9              str      xzr, [x0, #0x38]
0x00030bc4: c8 02 40 f9              ldr      x8, [x22]
0x00030bc8: 1f 25 00 f9              str      xzr, [x8, #0x48]
0x00030bcc: c8 02 40 f9              ldr      x8, [x22]
0x00030bd0: 1f 21 00 f9              str      xzr, [x8, #0x40]
0x00030bd4: c8 02 40 f9              ldr      x8, [x22]
0x00030bd8: 1f 0d 00 f9              str      xzr, [x8, #0x18]
0x00030bdc: c8 02 40 f9              ldr      x8, [x22]
0x00030be0: 13 19 00 f9              str      x19, [x8, #0x30]
0x00030be4: 78 01 00 14              b        #0x311c4                   ; -> ComponentLoad+0x6c0
0x00030be8: 60 02 40 f9              ldr      x0, [x19]
0x00030bec: 01 1a 80 52              mov      w1, #0xd0
0x00030bf0: 3c 71 ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x00030bf4: c0 02 00 f9              str      x0, [x22]
0x00030bf8: 68 0a 00 f0              adrp     x8, #0x17f000
0x00030bfc: 08 41 47 f9              ldr      x8, [x8, #0xe80]           ; -> ComponentGruPropgate@got
0x00030c00: 69 0a 00 f0              adrp     x9, #0x17f000
0x00030c04: e1 03 15 aa              mov      x1, x21
0x00030c08: e2 03 14 2a              mov      w2, w20
0x00030c0c: 08 1c 00 f9              str      x8, [x0, #0x38]
0x00030c10: c8 02 40 f9              ldr      x8, [x22]
0x00030c14: 29 f5 47 f9              ldr      x9, [x9, #0xfe8]           ; -> ComponentGruReset@got
0x00030c18: 09 25 00 f9              str      x9, [x8, #0x48]
0x00030c1c: 69 0a 00 f0              adrp     x9, #0x17f000
0x00030c20: c8 02 40 f9              ldr      x8, [x22]
0x00030c24: 29 bd 47 f9              ldr      x9, [x9, #0xf78]           ; -> ComponentGruDestroy@got
0x00030c28: 09 21 00 f9              str      x9, [x8, #0x40]
0x00030c2c: c8 02 40 f9              ldr      x8, [x22]
0x00030c30: 13 19 00 f9              str      x19, [x8, #0x30]
0x00030c34: c0 02 40 f9              ldr      x0, [x22]
0x00030c38: 7e 70 ff 97              bl       #0xce30                    ; -> ComponentGruLoad@plt
0x00030c3c: 62 01 00 14              b        #0x311c4                   ; -> ComponentLoad+0x6c0
0x00030c40: 60 02 40 f9              ldr      x0, [x19]
0x00030c44: e1 03 18 32              mov      w1, #0x100
0x00030c48: 26 71 ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x00030c4c: c0 02 00 f9              str      x0, [x22]
0x00030c50: 68 0a 00 f0              adrp     x8, #0x17f000
0x00030c54: 08 d9 47 f9              ldr      x8, [x8, #0xfb0]           ; -> ComponentLstmPropgate@got
0x00030c58: 69 0a 00 f0              adrp     x9, #0x17f000
0x00030c5c: e1 03 15 aa              mov      x1, x21
0x00030c60: e2 03 14 2a              mov      w2, w20
0x00030c64: 08 1c 00 f9              str      x8, [x0, #0x38]
0x00030c68: c8 02 40 f9              ldr      x8, [x22]
0x00030c6c: 29 a1 47 f9              ldr      x9, [x9, #0xf40]           ; -> ComponentLstmReset@got
0x00030c70: 09 25 00 f9              str      x9, [x8, #0x48]
0x00030c74: 69 0a 00 f0              adrp     x9, #0x17f000
0x00030c78: c8 02 40 f9              ldr      x8, [x22]
0x00030c7c: 29 cd 47 f9              ldr      x9, [x9, #0xf98]           ; -> ComponentLstmDestroy@got
0x00030c80: 09 21 00 f9              str      x9, [x8, #0x40]
0x00030c84: c8 02 40 f9              ldr      x8, [x22]
0x00030c88: 13 19 00 f9              str      x19, [x8, #0x30]
0x00030c8c: c0 02 40 f9              ldr      x0, [x22]
0x00030c90: 48 73 ff 97              bl       #0xd9b0                    ; -> ComponentLstmLoad@plt
0x00030c94: 4c 01 00 14              b        #0x311c4                   ; -> ComponentLoad+0x6c0
0x00030c98: 60 02 40 f9              ldr      x0, [x19]
0x00030c9c: e1 07 1a 32              mov      w1, #0xc0
0x00030ca0: 10 71 ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x00030ca4: c0 02 00 f9              str      x0, [x22]
0x00030ca8: 68 0a 00 f0              adrp     x8, #0x17f000
0x00030cac: 08 65 47 f9              ldr      x8, [x8, #0xec8]           ; -> ComponentConvolutionPropgate@got
0x00030cb0: 69 0a 00 f0              adrp     x9, #0x17f000
0x00030cb4: e1 03 15 aa              mov      x1, x21
0x00030cb8: e2 03 14 2a              mov      w2, w20
0x00030cbc: 08 1c 00 f9              str      x8, [x0, #0x38]
0x00030cc0: c8 02 40 f9              ldr      x8, [x22]
0x00030cc4: 1f 25 00 f9              str      xzr, [x8, #0x48]
0x00030cc8: c8 02 40 f9              ldr      x8, [x22]
0x00030ccc: 29 31 47 f9              ldr      x9, [x9, #0xe60]           ; -> ComponentConvolutionDestroy@got
0x00030cd0: 09 21 00 f9              str      x9, [x8, #0x40]
0x00030cd4: c8 02 40 f9              ldr      x8, [x22]
0x00030cd8: 13 19 00 f9              str      x19, [x8, #0x30]
0x00030cdc: c0 02 40 f9              ldr      x0, [x22]
0x00030ce0: a4 6f ff 97              bl       #0xcb70                    ; -> ComponentConvolutionLoad@plt
0x00030ce4: 38 01 00 14              b        #0x311c4                   ; -> ComponentLoad+0x6c0
0x00030ce8: 60 02 40 f9              ldr      x0, [x19]
0x00030cec: 01 13 80 52              mov      w1, #0x98
0x00030cf0: fc 70 ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x00030cf4: c0 02 00 f9              str      x0, [x22]
0x00030cf8: 68 0a 00 f0              adrp     x8, #0x17f000
0x00030cfc: 08 d1 47 f9              ldr      x8, [x8, #0xfa0]           ; -> ComponentPoolingPropgate@got
0x00030d00: 69 0a 00 f0              adrp     x9, #0x17f000
0x00030d04: e1 03 15 aa              mov      x1, x21
0x00030d08: e2 03 14 2a              mov      w2, w20
0x00030d0c: 08 1c 00 f9              str      x8, [x0, #0x38]
0x00030d10: c8 02 40 f9              ldr      x8, [x22]
0x00030d14: 1f 25 00 f9              str      xzr, [x8, #0x48]
0x00030d18: c8 02 40 f9              ldr      x8, [x22]
0x00030d1c: 29 79 47 f9              ldr      x9, [x9, #0xef0]           ; -> ComponentPoolingDestroy@got
0x00030d20: 09 21 00 f9              str      x9, [x8, #0x40]
0x00030d24: c8 02 40 f9              ldr      x8, [x22]
0x00030d28: 13 19 00 f9              str      x19, [x8, #0x30]
0x00030d2c: c0 02 40 f9              ldr      x0, [x22]
0x00030d30: 84 73 ff 97              bl       #0xdb40                    ; -> ComponentPoolingLoad@plt
0x00030d34: 24 01 00 14              b        #0x311c4                   ; -> ComponentLoad+0x6c0
0x00030d38: 60 02 40 f9              ldr      x0, [x19]
0x00030d3c: 01 15 80 52              mov      w1, #0xa8
0x00030d40: e8 70 ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x00030d44: c0 02 00 f9              str      x0, [x22]
0x00030d48: 68 0a 00 f0              adrp     x8, #0x17f000
0x00030d4c: 08 f1 47 f9              ldr      x8, [x8, #0xfe0]           ; -> ComponentSplicePropgate@got
0x00030d50: 69 0a 00 f0              adrp     x9, #0x17f000
0x00030d54: e1 03 15 aa              mov      x1, x21
0x00030d58: e2 03 14 2a              mov      w2, w20
0x00030d5c: 08 1c 00 f9              str      x8, [x0, #0x38]
0x00030d60: c8 02 40 f9              ldr      x8, [x22]
0x00030d64: 29 ad 47 f9              ldr      x9, [x9, #0xf58]           ; -> ComponentSpliceReset@got
0x00030d68: 09 25 00 f9              str      x9, [x8, #0x48]
0x00030d6c: 69 0a 00 f0              adrp     x9, #0x17f000
0x00030d70: c8 02 40 f9              ldr      x8, [x22]
0x00030d74: 29 75 47 f9              ldr      x9, [x9, #0xee8]           ; -> ComponentSpliceDestroy@got
0x00030d78: 09 21 00 f9              str      x9, [x8, #0x40]
0x00030d7c: c8 02 40 f9              ldr      x8, [x22]
0x00030d80: 13 19 00 f9              str      x19, [x8, #0x30]
0x00030d84: c0 02 40 f9              ldr      x0, [x22]
0x00030d88: ee 6f ff 97              bl       #0xcd40                    ; -> ComponentSpliceLoad@plt
0x00030d8c: 0e 01 00 14              b        #0x311c4                   ; -> ComponentLoad+0x6c0
0x00030d90: 60 02 40 f9              ldr      x0, [x19]
0x00030d94: e1 0f 1d 32              mov      w1, #0x78
0x00030d98: d2 70 ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x00030d9c: c0 02 00 f9              str      x0, [x22]
0x00030da0: 68 0a 00 f0              adrp     x8, #0x17f000
0x00030da4: 08 81 47 f9              ldr      x8, [x8, #0xf00]           ; -> ComponentPaddingPropgate@got
0x00030da8: 69 0a 00 f0              adrp     x9, #0x17f000
0x00030dac: e1 03 15 aa              mov      x1, x21
0x00030db0: e2 03 14 2a              mov      w2, w20
0x00030db4: 08 1c 00 f9              str      x8, [x0, #0x38]
0x00030db8: c8 02 40 f9              ldr      x8, [x22]
0x00030dbc: 29 59 47 f9              ldr      x9, [x9, #0xeb0]           ; -> ComponentPaddingReset@got
0x00030dc0: 09 25 00 f9              str      x9, [x8, #0x48]
0x00030dc4: c8 02 40 f9              ldr      x8, [x22]
0x00030dc8: 1f 21 00 f9              str      xzr, [x8, #0x40]
0x00030dcc: c8 02 40 f9              ldr      x8, [x22]
0x00030dd0: 13 19 00 f9              str      x19, [x8, #0x30]
0x00030dd4: c0 02 40 f9              ldr      x0, [x22]
0x00030dd8: 7a 71 ff 97              bl       #0xd3c0                    ; -> ComponentPaddingLoad@plt
0x00030ddc: fa 00 00 14              b        #0x311c4                   ; -> ComponentLoad+0x6c0
0x00030de0: 60 02 40 f9              ldr      x0, [x19]
0x00030de4: e1 03 19 32              mov      w1, #0x80
0x00030de8: be 70 ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x00030dec: c0 02 00 f9              str      x0, [x22]
0x00030df0: 68 0a 00 f0              adrp     x8, #0x17f000
0x00030df4: 08 95 47 f9              ldr      x8, [x8, #0xf28]           ; -> ComponentAffinePropgate@got
0x00030df8: 69 0a 00 f0              adrp     x9, #0x17f000
0x00030dfc: e1 03 15 aa              mov      x1, x21
0x00030e00: e2 03 14 2a              mov      w2, w20
0x00030e04: 08 1c 00 f9              str      x8, [x0, #0x38]
0x00030e08: c8 02 40 f9              ldr      x8, [x22]
0x00030e0c: 1f 25 00 f9              str      xzr, [x8, #0x48]
0x00030e10: c8 02 40 f9              ldr      x8, [x22]
0x00030e14: 29 b5 47 f9              ldr      x9, [x9, #0xf68]           ; -> ComponentAffineDestroy@got
0x00030e18: 09 21 00 f9              str      x9, [x8, #0x40]
0x00030e1c: c8 02 40 f9              ldr      x8, [x22]
0x00030e20: 13 19 00 f9              str      x19, [x8, #0x30]
0x00030e24: c0 02 40 f9              ldr      x0, [x22]
0x00030e28: 6a 71 ff 97              bl       #0xd3d0                    ; -> ComponentAffineLoad@plt
0x00030e2c: e6 00 00 14              b        #0x311c4                   ; -> ComponentLoad+0x6c0
0x00030e30: 60 02 40 f9              ldr      x0, [x19]
0x00030e34: 01 0d 80 52              mov      w1, #0x68
0x00030e38: aa 70 ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x00030e3c: c0 02 00 f9              str      x0, [x22]
0x00030e40: 68 0a 00 f0              adrp     x8, #0x17f000
0x00030e44: 08 d5 47 f9              ldr      x8, [x8, #0xfa8]           ; -> ComponentReluPropgate@got
0x00030e48: e1 03 15 aa              mov      x1, x21
0x00030e4c: e2 03 14 2a              mov      w2, w20
0x00030e50: 08 1c 00 f9              str      x8, [x0, #0x38]
0x00030e54: c8 02 40 f9              ldr      x8, [x22]
0x00030e58: 1f 25 00 f9              str      xzr, [x8, #0x48]
0x00030e5c: c8 02 40 f9              ldr      x8, [x22]
0x00030e60: 1f 21 00 f9              str      xzr, [x8, #0x40]
0x00030e64: c8 02 40 f9              ldr      x8, [x22]
0x00030e68: 13 19 00 f9              str      x19, [x8, #0x30]
0x00030e6c: c0 02 40 f9              ldr      x0, [x22]
0x00030e70: 84 70 ff 97              bl       #0xd080                    ; -> ComponentReluLoad@plt
0x00030e74: d4 00 00 14              b        #0x311c4                   ; -> ComponentLoad+0x6c0
0x00030e78: 60 02 40 f9              ldr      x0, [x19]
0x00030e7c: 01 0d 80 52              mov      w1, #0x68
0x00030e80: 98 70 ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x00030e84: c0 02 00 f9              str      x0, [x22]
0x00030e88: 68 0a 00 f0              adrp     x8, #0x17f000
0x00030e8c: 08 c5 47 f9              ldr      x8, [x8, #0xf88]           ; -> ComponentTanhPropgate@got
0x00030e90: e1 03 15 aa              mov      x1, x21
0x00030e94: e2 03 14 2a              mov      w2, w20
0x00030e98: 08 1c 00 f9              str      x8, [x0, #0x38]
0x00030e9c: c8 02 40 f9              ldr      x8, [x22]
0x00030ea0: 1f 25 00 f9              str      xzr, [x8, #0x48]
0x00030ea4: c8 02 40 f9              ldr      x8, [x22]
0x00030ea8: 1f 21 00 f9              str      xzr, [x8, #0x40]
0x00030eac: c8 02 40 f9              ldr      x8, [x22]
0x00030eb0: 13 19 00 f9              str      x19, [x8, #0x30]
0x00030eb4: c0 02 40 f9              ldr      x0, [x22]
0x00030eb8: 02 6f ff 97              bl       #0xcac0                    ; -> ComponentTanhLoad@plt
0x00030ebc: c2 00 00 14              b        #0x311c4                   ; -> ComponentLoad+0x6c0
0x00030ec0: 60 02 40 f9              ldr      x0, [x19]
0x00030ec4: 01 0d 80 52              mov      w1, #0x68
0x00030ec8: 86 70 ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x00030ecc: c0 02 00 f9              str      x0, [x22]
0x00030ed0: 68 0a 00 f0              adrp     x8, #0x17f000
0x00030ed4: 08 e1 47 f9              ldr      x8, [x8, #0xfc0]           ; -> ComponentAddPropgate@got
0x00030ed8: e1 03 15 aa              mov      x1, x21
0x00030edc: e2 03 14 2a              mov      w2, w20
0x00030ee0: 08 1c 00 f9              str      x8, [x0, #0x38]
0x00030ee4: c8 02 40 f9              ldr      x8, [x22]
0x00030ee8: 1f 25 00 f9              str      xzr, [x8, #0x48]
0x00030eec: c8 02 40 f9              ldr      x8, [x22]
0x00030ef0: 1f 21 00 f9              str      xzr, [x8, #0x40]
0x00030ef4: c8 02 40 f9              ldr      x8, [x22]
0x00030ef8: 13 19 00 f9              str      x19, [x8, #0x30]
0x00030efc: c0 02 40 f9              ldr      x0, [x22]
0x00030f00: 44 72 ff 97              bl       #0xd810                    ; -> ComponentAddLoad@plt
0x00030f04: b0 00 00 14              b        #0x311c4                   ; -> ComponentLoad+0x6c0
0x00030f08: 60 02 40 f9              ldr      x0, [x19]
0x00030f0c: e1 03 19 32              mov      w1, #0x80
0x00030f10: 74 70 ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x00030f14: c0 02 00 f9              str      x0, [x22]
0x00030f18: 68 0a 00 f0              adrp     x8, #0x17f000
0x00030f1c: 08 dd 47 f9              ldr      x8, [x8, #0xfb8]           ; -> ComponentReduceSumPropgate@got
0x00030f20: 69 0a 00 f0              adrp     x9, #0x17f000
0x00030f24: e1 03 15 aa              mov      x1, x21
0x00030f28: e2 03 14 2a              mov      w2, w20
0x00030f2c: 08 1c 00 f9              str      x8, [x0, #0x38]
0x00030f30: c8 02 40 f9              ldr      x8, [x22]
0x00030f34: 1f 25 00 f9              str      xzr, [x8, #0x48]
0x00030f38: c8 02 40 f9              ldr      x8, [x22]
0x00030f3c: 1f 21 00 f9              str      xzr, [x8, #0x40]
0x00030f40: c8 02 40 f9              ldr      x8, [x22]
0x00030f44: 29 f9 47 f9              ldr      x9, [x9, #0xff0]           ; -> ComponentReduceAllocOutput@got
0x00030f48: 09 29 00 f9              str      x9, [x8, #0x50]
0x00030f4c: c8 02 40 f9              ldr      x8, [x22]
0x00030f50: 13 19 00 f9              str      x19, [x8, #0x30]
0x00030f54: c0 02 40 f9              ldr      x0, [x22]
0x00030f58: 92 71 ff 97              bl       #0xd5a0                    ; -> ComponentReduceSumLoad@plt
0x00030f5c: 9a 00 00 14              b        #0x311c4                   ; -> ComponentLoad+0x6c0
0x00030f60: 60 02 40 f9              ldr      x0, [x19]
0x00030f64: e1 0b 1c 32              mov      w1, #0x70
0x00030f68: 5e 70 ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x00030f6c: c0 02 00 f9              str      x0, [x22]
0x00030f70: 68 0a 00 f0              adrp     x8, #0x17f000
0x00030f74: 08 8d 47 f9              ldr      x8, [x8, #0xf18]           ; -> ComponentUnfixedScalePropgate@got
0x00030f78: e1 03 15 aa              mov      x1, x21
0x00030f7c: e2 03 14 2a              mov      w2, w20
0x00030f80: 08 1c 00 f9              str      x8, [x0, #0x38]
0x00030f84: c8 02 40 f9              ldr      x8, [x22]
0x00030f88: 1f 25 00 f9              str      xzr, [x8, #0x48]
0x00030f8c: c8 02 40 f9              ldr      x8, [x22]
0x00030f90: 1f 21 00 f9              str      xzr, [x8, #0x40]
0x00030f94: c8 02 40 f9              ldr      x8, [x22]
0x00030f98: 13 19 00 f9              str      x19, [x8, #0x30]
0x00030f9c: c0 02 40 f9              ldr      x0, [x22]
0x00030fa0: d0 70 ff 97              bl       #0xd2e0                    ; -> ComponentUnfixedScaleLoad@plt
0x00030fa4: 88 00 00 14              b        #0x311c4                   ; -> ComponentLoad+0x6c0
0x00030fa8: e0 00 00 f0              adrp     x0, #0x4f000
0x00030fac: 00 c0 2a 91              add      x0, x0, #0xab0
0x00030fb0: c8 71 ff 97              bl       #0xd6d0                    ; -> puts@plt
0x00030fb4: 95 00 00 14              b        #0x31208                   ; -> ComponentLoad+0x704
0x00030fb8: 60 02 40 f9              ldr      x0, [x19]
0x00030fbc: e1 0b 1c 32              mov      w1, #0x70
0x00030fc0: 48 70 ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x00030fc4: c0 02 00 f9              str      x0, [x22]
0x00030fc8: 68 0a 00 f0              adrp     x8, #0x17f000
0x00030fcc: 08 a9 47 f9              ldr      x8, [x8, #0xf50]           ; -> ComponentPnormPropgate@got
0x00030fd0: e1 03 15 aa              mov      x1, x21
0x00030fd4: e2 03 14 2a              mov      w2, w20
0x00030fd8: 08 1c 00 f9              str      x8, [x0, #0x38]
0x00030fdc: c8 02 40 f9              ldr      x8, [x22]
0x00030fe0: 1f 25 00 f9              str      xzr, [x8, #0x48]
0x00030fe4: c8 02 40 f9              ldr      x8, [x22]
0x00030fe8: 1f 21 00 f9              str      xzr, [x8, #0x40]
0x00030fec: c8 02 40 f9              ldr      x8, [x22]
0x00030ff0: 13 19 00 f9              str      x19, [x8, #0x30]
0x00030ff4: c0 02 40 f9              ldr      x0, [x22]
0x00030ff8: 06 6f ff 97              bl       #0xcc10                    ; -> ComponentPnormLoad@plt
0x00030ffc: 72 00 00 14              b        #0x311c4                   ; -> ComponentLoad+0x6c0
0x00031000: 60 02 40 f9              ldr      x0, [x19]
0x00031004: e1 0f 1d 32              mov      w1, #0x78
0x00031008: 36 70 ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x0003100c: c0 02 00 f9              str      x0, [x22]
0x00031010: 68 0a 00 d0              adrp     x8, #0x17f000
0x00031014: 08 7d 47 f9              ldr      x8, [x8, #0xef8]           ; -> ComponentFixedscalePropgate@got
0x00031018: 69 0a 00 d0              adrp     x9, #0x17f000
0x0003101c: e1 03 15 aa              mov      x1, x21
0x00031020: e2 03 14 2a              mov      w2, w20
0x00031024: 08 1c 00 f9              str      x8, [x0, #0x38]
0x00031028: c8 02 40 f9              ldr      x8, [x22]
0x0003102c: 1f 25 00 f9              str      xzr, [x8, #0x48]
0x00031030: c8 02 40 f9              ldr      x8, [x22]
0x00031034: 29 51 47 f9              ldr      x9, [x9, #0xea0]           ; -> ComponentFixedscaleDestroy@got
0x00031038: 09 21 00 f9              str      x9, [x8, #0x40]
0x0003103c: c8 02 40 f9              ldr      x8, [x22]
0x00031040: 13 19 00 f9              str      x19, [x8, #0x30]
0x00031044: c0 02 40 f9              ldr      x0, [x22]
0x00031048: ee 70 ff 97              bl       #0xd400                    ; -> ComponentFixedscaleLoad@plt
0x0003104c: 5e 00 00 14              b        #0x311c4                   ; -> ComponentLoad+0x6c0
0x00031050: 60 02 40 f9              ldr      x0, [x19]
0x00031054: 01 13 80 52              mov      w1, #0x98
0x00031058: 22 70 ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x0003105c: c0 02 00 f9              str      x0, [x22]
0x00031060: 68 0a 00 d0              adrp     x8, #0x17f000
0x00031064: 08 e5 47 f9              ldr      x8, [x8, #0xfc8]           ; -> ComponentNormalizePropgate@got
0x00031068: 69 0a 00 d0              adrp     x9, #0x17f000
0x0003106c: e1 03 15 aa              mov      x1, x21
0x00031070: e2 03 14 2a              mov      w2, w20
0x00031074: 08 1c 00 f9              str      x8, [x0, #0x38]
0x00031078: c8 02 40 f9              ldr      x8, [x22]
0x0003107c: 1f 25 00 f9              str      xzr, [x8, #0x48]
0x00031080: c8 02 40 f9              ldr      x8, [x22]
0x00031084: 29 55 47 f9              ldr      x9, [x9, #0xea8]           ; -> ComponentNormalizeDestroy@got
0x00031088: 09 21 00 f9              str      x9, [x8, #0x40]
0x0003108c: c8 02 40 f9              ldr      x8, [x22]
0x00031090: 13 19 00 f9              str      x19, [x8, #0x30]
0x00031094: c0 02 40 f9              ldr      x0, [x22]
0x00031098: 96 6f ff 97              bl       #0xcef0                    ; -> ComponentNormalizeLoad@plt
0x0003109c: 4a 00 00 14              b        #0x311c4                   ; -> ComponentLoad+0x6c0
0x000310a0: 60 02 40 f9              ldr      x0, [x19]
0x000310a4: 01 13 80 52              mov      w1, #0x98
0x000310a8: 0e 70 ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x000310ac: c0 02 00 f9              str      x0, [x22]
0x000310b0: 68 0a 00 d0              adrp     x8, #0x17f000
0x000310b4: 08 a5 47 f9              ldr      x8, [x8, #0xf48]           ; -> ComponentStatisticsPoolingPropgate@got
0x000310b8: e1 03 15 aa              mov      x1, x21
0x000310bc: e2 03 14 2a              mov      w2, w20
0x000310c0: 08 1c 00 f9              str      x8, [x0, #0x38]
0x000310c4: c8 02 40 f9              ldr      x8, [x22]
0x000310c8: 1f 25 00 f9              str      xzr, [x8, #0x48]
0x000310cc: c8 02 40 f9              ldr      x8, [x22]
0x000310d0: 1f 21 00 f9              str      xzr, [x8, #0x40]
0x000310d4: c8 02 40 f9              ldr      x8, [x22]
0x000310d8: 13 19 00 f9              str      x19, [x8, #0x30]
0x000310dc: c0 02 40 f9              ldr      x0, [x22]
0x000310e0: ec 6e ff 97              bl       #0xcc90                    ; -> ComponentStatisticsPoolingLoad@plt
0x000310e4: 38 00 00 14              b        #0x311c4                   ; -> ComponentLoad+0x6c0
0x000310e8: 60 02 40 f9              ldr      x0, [x19]
0x000310ec: e1 0b 1c 32              mov      w1, #0x70
0x000310f0: fc 6f ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x000310f4: c0 02 00 f9              str      x0, [x22]
0x000310f8: 68 0a 00 d0              adrp     x8, #0x17f000
0x000310fc: 08 b1 47 f9              ldr      x8, [x8, #0xf60]           ; -> ComponentSoftmaxPropgate@got
0x00031100: e1 03 15 aa              mov      x1, x21
0x00031104: e2 03 14 2a              mov      w2, w20
0x00031108: 08 1c 00 f9              str      x8, [x0, #0x38]
0x0003110c: c8 02 40 f9              ldr      x8, [x22]
0x00031110: 1f 25 00 f9              str      xzr, [x8, #0x48]
0x00031114: c8 02 40 f9              ldr      x8, [x22]
0x00031118: 1f 21 00 f9              str      xzr, [x8, #0x40]
0x0003111c: c8 02 40 f9              ldr      x8, [x22]
0x00031120: 13 19 00 f9              str      x19, [x8, #0x30]
0x00031124: c0 02 40 f9              ldr      x0, [x22]
0x00031128: e2 6f ff 97              bl       #0xd0b0                    ; -> ComponentSoftmaxLoad@plt
0x0003112c: 26 00 00 14              b        #0x311c4                   ; -> ComponentLoad+0x6c0
0x00031130: 60 02 40 f9              ldr      x0, [x19]
0x00031134: e1 0f 1d 32              mov      w1, #0x78
0x00031138: ea 6f ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x0003113c: c0 02 00 f9              str      x0, [x22]
0x00031140: 68 0a 00 d0              adrp     x8, #0x17f000
0x00031144: 08 c1 47 f9              ldr      x8, [x8, #0xf80]           ; -> ComponentEmbeddingPropgate@got
0x00031148: 69 0a 00 d0              adrp     x9, #0x17f000
0x0003114c: e1 03 15 aa              mov      x1, x21
0x00031150: e2 03 14 2a              mov      w2, w20
0x00031154: 08 1c 00 f9              str      x8, [x0, #0x38]
0x00031158: c8 02 40 f9              ldr      x8, [x22]
0x0003115c: 1f 25 00 f9              str      xzr, [x8, #0x48]
0x00031160: c8 02 40 f9              ldr      x8, [x22]
0x00031164: 29 61 47 f9              ldr      x9, [x9, #0xec0]           ; -> ComponentEmbeddingDestroy@got
0x00031168: 09 21 00 f9              str      x9, [x8, #0x40]
0x0003116c: c8 02 40 f9              ldr      x8, [x22]
0x00031170: 13 19 00 f9              str      x19, [x8, #0x30]
0x00031174: c0 02 40 f9              ldr      x0, [x22]
0x00031178: ca 71 ff 97              bl       #0xd8a0                    ; -> ComponentEmbeddingLoad@plt
0x0003117c: 12 00 00 14              b        #0x311c4                   ; -> ComponentLoad+0x6c0
0x00031180: 60 02 40 f9              ldr      x0, [x19]
0x00031184: e1 0b 1c 32              mov      w1, #0x70
0x00031188: d6 6f ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x0003118c: c0 02 00 f9              str      x0, [x22]
0x00031190: 68 0a 00 d0              adrp     x8, #0x17f000
0x00031194: 08 29 47 f9              ldr      x8, [x8, #0xe50]           ; -> ComponentReshapePropgate@got
0x00031198: e1 03 15 aa              mov      x1, x21
0x0003119c: e2 03 14 2a              mov      w2, w20
0x000311a0: 08 1c 00 f9              str      x8, [x0, #0x38]
0x000311a4: c8 02 40 f9              ldr      x8, [x22]
0x000311a8: 1f 25 00 f9              str      xzr, [x8, #0x48]
0x000311ac: c8 02 40 f9              ldr      x8, [x22]
0x000311b0: 1f 21 00 f9              str      xzr, [x8, #0x40]
0x000311b4: c8 02 40 f9              ldr      x8, [x22]
0x000311b8: 13 19 00 f9              str      x19, [x8, #0x30]
0x000311bc: c0 02 40 f9              ldr      x0, [x22]
0x000311c0: 88 6f ff 97              bl       #0xcfe0                    ; -> ComponentReshapeLoad@plt
0x000311c4: d6 02 40 f9              ldr      x22, [x22]
0x000311c8: e8 03 00 32              mov      w8, #1
0x000311cc: c0 1a 40 f9              ldr      x0, [x22, #0x30]
0x000311d0: d4 06 00 b9              str      w20, [x22, #4]
0x000311d4: df 06 00 f9              str      xzr, [x22, #8]
0x000311d8: c8 12 00 b9              str      w8, [x22, #0x10]
0x000311dc: 79 71 ff 97              bl       #0xd7c0                    ; -> TensorNew@plt
0x000311e0: c0 16 00 f9              str      x0, [x22, #0x28]
0x000311e4: e8 07 40 b9              ldr      w8, [sp, #4]
0x000311e8: c8 02 00 b9              str      w8, [x22]
0x000311ec: 1f 05 00 71              cmp      w8, #1
0x000311f0: c0 00 00 54              b.eq     #0x31208
0x000311f4: e0 03 16 aa              mov      x0, x22
0x000311f8: e1 03 15 aa              mov      x1, x21
0x000311fc: e2 03 14 2a              mov      w2, w20
0x00031200: e3 03 13 aa              mov      x3, x19
0x00031204: 7b 70 ff 97              bl       #0xd3f0                    ; -> LoadDependence@plt
0x00031208: 48 d0 3b d5              mrs      x8, tpidr_el0
0x0003120c: 08 15 40 f9              ldr      x8, [x8, #0x28]
0x00031210: e9 07 40 f9              ldr      x9, [sp, #8]
0x00031214: 1f 01 09 eb              cmp      x8, x9
0x00031218: e1 00 00 54              b.ne     #0x31234
0x0003121c: fd 7b 44 a9              ldp      x29, x30, [sp, #0x40]
0x00031220: f4 4f 43 a9              ldp      x20, x19, [sp, #0x30]
0x00031224: f6 57 42 a9              ldp      x22, x21, [sp, #0x20]
0x00031228: f7 0b 40 f9              ldr      x23, [sp, #0x10]
0x0003122c: ff 43 01 91              add      sp, sp, #0x50
0x00031230: c0 03 5f d6              ret      
0x00031234: 5f 70 ff 97              bl       #0xd3b0                    ; -> __stack_chk_fail@plt
