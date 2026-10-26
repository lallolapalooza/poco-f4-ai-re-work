; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : CornLoadModelBin (0x31a4c)
; start   : 0x31a4c
; size    : 0x500
; bytes   : 0x500   instructions: 320
; generated: 2026-10-23T23:27:57

0x00031a4c: fc 6f ba a9              stp      x28, x27, [sp, #-0x60]!
0x00031a50: fa 67 01 a9              stp      x26, x25, [sp, #0x10]
0x00031a54: f8 5f 02 a9              stp      x24, x23, [sp, #0x20]
0x00031a58: f6 57 03 a9              stp      x22, x21, [sp, #0x30]
0x00031a5c: f4 4f 04 a9              stp      x20, x19, [sp, #0x40]
0x00031a60: fd 7b 05 a9              stp      x29, x30, [sp, #0x50]
0x00031a64: fd 43 01 91              add      x29, sp, #0x50
0x00031a68: ff c3 0a d1              sub      sp, sp, #0x2b0
0x00031a6c: 48 d0 3b d5              mrs      x8, tpidr_el0
0x00031a70: e8 07 00 f9              str      x8, [sp, #8]
0x00031a74: 08 15 40 f9              ldr      x8, [x8, #0x28]
0x00031a78: a0 2e 89 52              mov      w0, #0x4975
0x00031a7c: 20 02 a0 72              movk     w0, #0x11, lsl #16
0x00031a80: f3 03 01 aa              mov      x19, x1
0x00031a84: a8 83 1a f8              stur     x8, [x29, #-0x58]
0x00031a88: f2 6f ff 97              bl       #0xda50                    ; -> malloc@plt
0x00031a8c: f4 03 00 aa              mov      x20, x0
0x00031a90: 74 fe 01 f9              str      x20, [x19, #0x3f8]
0x00031a94: 61 0a 00 d0              adrp     x1, #0x17f000
0x00031a98: 21 4c 47 f9              ldr      x1, [x1, #0xe98]           ; -> mdata_vad@got
0x00031a9c: 82 2e 89 52              mov      w2, #0x4974
0x00031aa0: 22 02 a0 72              movk     w2, #0x11, lsl #16
0x00031aa4: f7 6f ff 97              bl       #0xda80                    ; -> memcpy@plt
0x00031aa8: e0 03 14 aa              mov      x0, x20
0x00031aac: f5 6b ff 97              bl       #0xca80                    ; -> binn_open@plt
0x00031ab0: f4 03 00 aa              mov      x20, x0
0x00031ab4: e0 e3 04 91              add      x0, sp, #0x138
0x00031ab8: 02 0a 80 52              mov      w2, #0x50
0x00031abc: e1 03 1f 2a              mov      w1, wzr
0x00031ac0: 08 70 ff 97              bl       #0xdae0                    ; -> memset@plt
0x00031ac4: e0 a3 03 91              add      x0, sp, #0xe8
0x00031ac8: 02 0a 80 52              mov      w2, #0x50
0x00031acc: e1 03 1f 2a              mov      w1, wzr
0x00031ad0: 04 70 ff 97              bl       #0xdae0                    ; -> memset@plt
0x00031ad4: e0 23 06 91              add      x0, sp, #0x188
0x00031ad8: 42 1c 80 52              mov      w2, #0xe2
0x00031adc: e1 03 14 aa              mov      x1, x20
0x00031ae0: f4 0b 00 f9              str      x20, [sp, #0x10]
0x00031ae4: ef 6c ff 97              bl       #0xcea0                    ; -> binn_iter_init@plt
0x00031ae8: e0 23 06 91              add      x0, sp, #0x188
0x00031aec: e1 a3 06 91              add      x1, sp, #0x1a8
0x00031af0: e2 e3 04 91              add      x2, sp, #0x138
0x00031af4: cb 6e ff 97              bl       #0xd620                    ; -> binn_object_next@plt
0x00031af8: c0 20 00 34              cbz      w0, #0x31f10
0x00031afc: 68 82 0f 91              add      x8, x19, #0x3e0
0x00031b00: e8 4b 00 f9              str      x8, [sp, #0x90]
0x00031b04: 68 a2 00 91              add      x8, x19, #0x28
0x00031b08: e8 47 00 f9              str      x8, [sp, #0x88]
0x00031b0c: 68 62 0f 91              add      x8, x19, #0x3d8
0x00031b10: e8 43 00 f9              str      x8, [sp, #0x80]
0x00031b14: 68 c2 0e 91              add      x8, x19, #0x3b0
0x00031b18: e8 3f 00 f9              str      x8, [sp, #0x78]
0x00031b1c: 68 d2 0e 91              add      x8, x19, #0x3b4
0x00031b20: e8 3b 00 f9              str      x8, [sp, #0x70]
0x00031b24: 68 e2 0e 91              add      x8, x19, #0x3b8
0x00031b28: e8 37 00 f9              str      x8, [sp, #0x68]
0x00031b2c: 68 f2 0e 91              add      x8, x19, #0x3bc
0x00031b30: e8 33 00 f9              str      x8, [sp, #0x60]
0x00031b34: 68 02 0f 91              add      x8, x19, #0x3c0
0x00031b38: e8 2f 00 f9              str      x8, [sp, #0x58]
0x00031b3c: 68 12 0f 91              add      x8, x19, #0x3c4
0x00031b40: e8 2b 00 f9              str      x8, [sp, #0x50]
0x00031b44: 68 22 0f 91              add      x8, x19, #0x3c8
0x00031b48: e8 27 00 f9              str      x8, [sp, #0x48]
0x00031b4c: 68 42 07 91              add      x8, x19, #0x1d0
0x00031b50: e8 23 00 f9              str      x8, [sp, #0x40]
0x00031b54: 68 52 07 91              add      x8, x19, #0x1d4
0x00031b58: e8 1f 00 f9              str      x8, [sp, #0x38]
0x00031b5c: 68 62 07 91              add      x8, x19, #0x1d8
0x00031b60: e8 1b 00 f9              str      x8, [sp, #0x30]
0x00031b64: 68 72 07 91              add      x8, x19, #0x1dc
0x00031b68: e8 17 00 f9              str      x8, [sp, #0x28]
0x00031b6c: 68 62 04 91              add      x8, x19, #0x118
0x00031b70: e8 13 00 f9              str      x8, [sp, #0x20]
0x00031b74: 68 72 04 91              add      x8, x19, #0x11c
0x00031b78: 7c 82 04 91              add      x28, x19, #0x120
0x00031b7c: 74 82 00 91              add      x20, x19, #0x20
0x00031b80: f8 03 00 32              mov      w24, #1
0x00031b84: e8 0f 00 f9              str      x8, [sp, #0x18]
0x00031b88: e0 23 06 91              add      x0, sp, #0x188
0x00031b8c: e1 e3 04 91              add      x1, sp, #0x138
0x00031b90: e2 0b 1b 32              mov      w2, #0xe0
0x00031b94: c3 6c ff 97              bl       #0xcea0                    ; -> binn_iter_init@plt
0x00031b98: 06 00 00 14              b        #0x31bb0                   ; -> CornLoadModelBin+0x164
0x00031b9c: e1 63 40 f9              ldr      x1, [sp, #0xc0]
0x00031ba0: e0 4b 40 f9              ldr      x0, [sp, #0x90]
0x00031ba4: e2 0b 00 32              mov      w2, #7
0x00031ba8: e3 03 1f aa              mov      x3, xzr
0x00031bac: 91 6d ff 97              bl       #0xd1f0                    ; -> SoilInit@plt
0x00031bb0: e0 23 06 91              add      x0, sp, #0x188
0x00031bb4: e1 a3 03 91              add      x1, sp, #0xe8
0x00031bb8: ba 6f ff 97              bl       #0xdaa0                    ; -> binn_list_next@plt
0x00031bbc: 80 19 00 34              cbz      w0, #0x31eec
0x00031bc0: 81 09 00 f0              adrp     x1, #0x164000
0x00031bc4: e0 a3 03 91              add      x0, sp, #0xe8
0x00031bc8: e2 63 02 91              add      x2, sp, #0x98
0x00031bcc: 21 f4 1c 91              add      x1, x1, #0x73d
0x00031bd0: 18 6d ff 97              bl       #0xd030                    ; -> binn_object_get_value@plt
0x00031bd4: 60 0f 00 34              cbz      w0, #0x31dc0
0x00031bd8: e0 63 40 f9              ldr      x0, [sp, #0xc0]
0x00031bdc: a9 6b ff 97              bl       #0xca80                    ; -> binn_open@plt
0x00031be0: f5 03 00 aa              mov      x21, x0
0x00031be4: da 00 00 94              bl       #0x31f4c
0x00031be8: e2 47 40 f9              ldr      x2, [sp, #0x88]
0x00031bec: 81 09 00 f0              adrp     x1, #0x164000
0x00031bf0: 21 08 1d 91              add      x1, x1, #0x742
0x00031bf4: f6 03 00 aa              mov      x22, x0
0x00031bf8: 74 01 00 94              bl       #0x321c8
0x00031bfc: 7f da 03 b9              str      wzr, [x19, #0x3d8]
0x00031c00: e2 43 40 f9              ldr      x2, [sp, #0x80]
0x00031c04: 81 09 00 f0              adrp     x1, #0x164000
0x00031c08: e0 03 16 aa              mov      x0, x22
0x00031c0c: 21 44 1d 91              add      x1, x1, #0x751
0x00031c10: c1 01 00 94              bl       #0x32314
0x00031c14: e8 03 1f 32              mov      w8, #2
0x00031c18: 68 b2 03 b9              str      w8, [x19, #0x3b0]
0x00031c1c: e2 3f 40 f9              ldr      x2, [sp, #0x78]
0x00031c20: 81 09 00 f0              adrp     x1, #0x164000
0x00031c24: e0 03 16 aa              mov      x0, x22
0x00031c28: 21 68 1d 91              add      x1, x1, #0x75a
0x00031c2c: 12 02 00 94              bl       #0x32474
0x00031c30: a8 00 80 52              mov      w8, #5
0x00031c34: 68 b6 03 b9              str      w8, [x19, #0x3b4]
0x00031c38: e2 3b 40 f9              ldr      x2, [sp, #0x70]
0x00031c3c: 81 09 00 f0              adrp     x1, #0x164000
0x00031c40: e0 03 16 aa              mov      x0, x22
0x00031c44: 21 90 1d 91              add      x1, x1, #0x764
0x00031c48: 0b 02 00 94              bl       #0x32474
0x00031c4c: 7f ba 03 b9              str      wzr, [x19, #0x3b8]
0x00031c50: e2 37 40 f9              ldr      x2, [sp, #0x68]
0x00031c54: 81 09 00 f0              adrp     x1, #0x164000
0x00031c58: e0 03 16 aa              mov      x0, x22
0x00031c5c: 21 dc 1d 91              add      x1, x1, #0x777
0x00031c60: 05 02 00 94              bl       #0x32474
0x00031c64: 08 0a 80 52              mov      w8, #0x50
0x00031c68: 68 be 03 b9              str      w8, [x19, #0x3bc]
0x00031c6c: e2 33 40 f9              ldr      x2, [sp, #0x60]
0x00031c70: 81 09 00 f0              adrp     x1, #0x164000
0x00031c74: e0 03 16 aa              mov      x0, x22
0x00031c78: 21 64 1c 91              add      x1, x1, #0x719
0x00031c7c: fe 01 00 94              bl       #0x32474
0x00031c80: 48 06 80 52              mov      w8, #0x32
0x00031c84: 68 c2 03 b9              str      w8, [x19, #0x3c0]
0x00031c88: e2 2f 40 f9              ldr      x2, [sp, #0x58]
0x00031c8c: 81 09 00 f0              adrp     x1, #0x164000
0x00031c90: e0 03 16 aa              mov      x0, x22
0x00031c94: 21 b0 1c 91              add      x1, x1, #0x72c
0x00031c98: f7 01 00 94              bl       #0x32474
0x00031c9c: 7f c6 03 b9              str      wzr, [x19, #0x3c4]
0x00031ca0: e2 2b 40 f9              ldr      x2, [sp, #0x50]
0x00031ca4: 81 09 00 f0              adrp     x1, #0x164000
0x00031ca8: e0 03 16 aa              mov      x0, x22
0x00031cac: 21 00 1e 91              add      x1, x1, #0x780
0x00031cb0: f1 01 00 94              bl       #0x32474
0x00031cb4: 7f ca 03 b9              str      wzr, [x19, #0x3c8]
0x00031cb8: e2 27 40 f9              ldr      x2, [sp, #0x48]
0x00031cbc: 81 09 00 f0              adrp     x1, #0x164000
0x00031cc0: e0 03 16 aa              mov      x0, x22
0x00031cc4: 21 3c 1e 91              add      x1, x1, #0x78f
0x00031cc8: eb 01 00 94              bl       #0x32474
0x00031ccc: 08 4b 80 52              mov      w8, #0x258
0x00031cd0: 68 d2 01 b9              str      w8, [x19, #0x1d0]
0x00031cd4: e2 23 40 f9              ldr      x2, [sp, #0x40]
0x00031cd8: 81 09 00 f0              adrp     x1, #0x164000
0x00031cdc: e0 03 16 aa              mov      x0, x22
0x00031ce0: 21 7c 1e 91              add      x1, x1, #0x79f
0x00031ce4: e4 01 00 94              bl       #0x32474
0x00031ce8: 08 69 a8 52              mov      w8, #0x43480000
0x00031cec: 68 d6 01 b9              str      w8, [x19, #0x1d4]
0x00031cf0: e2 1f 40 f9              ldr      x2, [sp, #0x38]
0x00031cf4: 81 09 00 f0              adrp     x1, #0x164000
0x00031cf8: e0 03 16 aa              mov      x0, x22
0x00031cfc: 21 b0 1e 91              add      x1, x1, #0x7ac
0x00031d00: 85 01 00 94              bl       #0x32314
0x00031d04: 78 da 01 b9              str      w24, [x19, #0x1d8]
0x00031d08: e2 1b 40 f9              ldr      x2, [sp, #0x30]
0x00031d0c: 81 09 00 f0              adrp     x1, #0x164000
0x00031d10: e0 03 16 aa              mov      x0, x22
0x00031d14: 21 f0 1e 91              add      x1, x1, #0x7bc
0x00031d18: d7 01 00 94              bl       #0x32474
0x00031d1c: 7f de 01 b9              str      wzr, [x19, #0x1dc]
0x00031d20: e2 17 40 f9              ldr      x2, [sp, #0x28]
0x00031d24: 81 09 00 f0              adrp     x1, #0x164000
0x00031d28: e0 03 16 aa              mov      x0, x22
0x00031d2c: 21 24 1f 91              add      x1, x1, #0x7c9
0x00031d30: d1 01 00 94              bl       #0x32474
0x00031d34: 08 05 80 52              mov      w8, #0x28
0x00031d38: 68 1a 01 b9              str      w8, [x19, #0x118]
0x00031d3c: e2 13 40 f9              ldr      x2, [sp, #0x20]
0x00031d40: 81 09 00 f0              adrp     x1, #0x164000
0x00031d44: e0 03 16 aa              mov      x0, x22
0x00031d48: 21 54 1f 91              add      x1, x1, #0x7d5
0x00031d4c: ca 01 00 94              bl       #0x32474
0x00031d50: 08 44 a8 52              mov      w8, #0x42200000
0x00031d54: 68 1e 01 b9              str      w8, [x19, #0x11c]
0x00031d58: e2 0f 40 f9              ldr      x2, [sp, #0x18]
0x00031d5c: 81 09 00 f0              adrp     x1, #0x164000
0x00031d60: e0 03 16 aa              mov      x0, x22
0x00031d64: 21 90 1f 91              add      x1, x1, #0x7e4
0x00031d68: 6b 01 00 94              bl       #0x32314
0x00031d6c: 81 09 00 f0              adrp     x1, #0x164000
0x00031d70: 08 69 b8 52              mov      w8, #-0x3cb80000
0x00031d74: e0 03 16 aa              mov      x0, x22
0x00031d78: 21 bc 1f 91              add      x1, x1, #0x7ef
0x00031d7c: e2 03 1c aa              mov      x2, x28
0x00031d80: 68 22 01 b9              str      w8, [x19, #0x120]
0x00031d84: 64 01 00 94              bl       #0x32314
0x00031d88: 81 09 00 f0              adrp     x1, #0x164000
0x00031d8c: e0 03 16 aa              mov      x0, x22
0x00031d90: 21 ec 1f 91              add      x1, x1, #0x7fb
0x00031d94: e2 03 14 aa              mov      x2, x20
0x00031d98: 78 f6 03 b9              str      w24, [x19, #0x3f4]
0x00031d9c: 0b 01 00 94              bl       #0x321c8
0x00031da0: 61 12 40 f9              ldr      x1, [x19, #0x20]
0x00031da4: 80 09 00 f0              adrp     x0, #0x164000
0x00031da8: 00 2c 20 91              add      x0, x0, #0x80b
0x00031dac: 89 6b ff 97              bl       #0xcbd0                    ; -> printf@plt
0x00031db0: e0 03 15 aa              mov      x0, x21
0x00031db4: 43 6c ff 97              bl       #0xcec0                    ; -> binn_free@plt
0x00031db8: e0 03 16 aa              mov      x0, x22
0x00031dbc: f1 6e ff 97              bl       #0xd980                    ; -> cJSON_Delete@plt
0x00031dc0: 81 09 00 f0              adrp     x1, #0x164000
0x00031dc4: e0 a3 03 91              add      x0, sp, #0xe8
0x00031dc8: e2 63 02 91              add      x2, sp, #0x98
0x00031dcc: 21 94 20 91              add      x1, x1, #0x825
0x00031dd0: 98 6c ff 97              bl       #0xd030                    ; -> binn_object_get_value@plt
0x00031dd4: e0 07 00 34              cbz      w0, #0x31ed0
0x00031dd8: 60 86 41 f9              ldr      x0, [x19, #0x308]
0x00031ddc: f5 63 40 f9              ldr      x21, [sp, #0xc0]
0x00031de0: 01 05 80 52              mov      w1, #0x28
0x00031de4: cb 6c ff 97              bl       #0xd110                    ; -> CornMpoolAlloc@plt
0x00031de8: 60 62 00 f9              str      x0, [x19, #0xc0]
0x00031dec: a8 16 40 38              ldrb     w8, [x21], #1
0x00031df0: 1f 6d 01 71              cmp      w8, #0x5b
0x00031df4: c1 ff ff 54              b.ne     #0x31dec
0x00031df8: a8 16 40 38              ldrb     w8, [x21], #1
0x00031dfc: 1f 29 00 71              cmp      w8, #0xa
0x00031e00: c1 ff ff 54              b.ne     #0x31df8
0x00031e04: 60 86 41 f9              ldr      x0, [x19, #0x308]
0x00031e08: 01 32 80 52              mov      w1, #0x190
0x00031e0c: c1 6c ff 97              bl       #0xd110                    ; -> CornMpoolAlloc@plt
0x00031e10: 68 62 40 f9              ldr      x8, [x19, #0xc0]
0x00031e14: f9 03 1f aa              mov      x25, xzr
0x00031e18: 00 01 00 f9              str      x0, [x8]
0x00031e1c: 18 00 00 14              b        #0x31e7c                   ; -> CornLoadModelBin+0x430
0x00031e20: 60 86 41 f9              ldr      x0, [x19, #0x308]
0x00031e24: aa 06 00 11              add      w10, w21, #1
0x00031e28: 41 7d 40 93              sxtw     x1, w10
0x00031e2c: 3b 7d 40 93              sxtw     x27, w9
0x00031e30: 16 01 1b cb              sub      x22, x8, x27
0x00031e34: b7 6c ff 97              bl       #0xd110                    ; -> CornMpoolAlloc@plt
0x00031e38: e1 03 16 aa              mov      x1, x22
0x00031e3c: e2 03 15 aa              mov      x2, x21
0x00031e40: f7 03 00 aa              mov      x23, x0
0x00031e44: 0f 6f ff 97              bl       #0xda80                    ; -> memcpy@plt
0x00031e48: e0 03 17 aa              mov      x0, x23
0x00031e4c: e1 03 1f aa              mov      x1, xzr
0x00031e50: 55 03 1b cb              sub      x21, x26, x27
0x00031e54: d3 6e ff 97              bl       #0xd9a0                    ; -> strtod@plt
0x00031e58: 68 62 40 f9              ldr      x8, [x19, #0xc0]
0x00031e5c: 00 40 62 1e              fcvt     s0, d0
0x00031e60: 08 01 40 f9              ldr      x8, [x8]
0x00031e64: 00 79 39 bc              str      s0, [x8, x25, lsl #2]
0x00031e68: e8 03 15 aa              mov      x8, x21
0x00031e6c: 09 35 40 38              ldrb     w9, [x8], #3
0x00031e70: 39 07 00 91              add      x25, x25, #1
0x00031e74: 3f 29 00 71              cmp      w9, #0xa
0x00031e78: 15 01 95 9a              csel     x21, x8, x21, eq
0x00031e7c: a9 06 00 d1              sub      x9, x21, #1
0x00031e80: ab 06 00 91              add      x11, x21, #1
0x00031e84: 2a 1d 40 38              ldrb     w10, [x9, #1]!
0x00031e88: e8 03 0b aa              mov      x8, x11
0x00031e8c: 0b 05 00 91              add      x11, x8, #1
0x00031e90: 5f 81 00 71              cmp      w10, #0x20
0x00031e94: 80 ff ff 54              b.eq     #0x31e84
0x00031e98: f5 03 1f aa              mov      x21, xzr
0x00031e9c: e9 03 00 32              mov      w9, #1
0x00031ea0: fa 03 08 aa              mov      x26, x8
0x00031ea4: 05 00 00 14              b        #0x31eb8                   ; -> CornLoadModelBin+0x46c
0x00031ea8: 0a 15 40 38              ldrb     w10, [x8], #1
0x00031eac: 29 05 00 11              add      w9, w9, #1
0x00031eb0: 5a 0b 00 91              add      x26, x26, #2
0x00031eb4: b5 06 00 91              add      x21, x21, #1
0x00031eb8: 4a 1d 00 12              and      w10, w10, #0xff
0x00031ebc: 5f 81 00 71              cmp      w10, #0x20
0x00031ec0: 00 fb ff 54              b.eq     #0x31e20
0x00031ec4: 5f 75 01 71              cmp      w10, #0x5d
0x00031ec8: 01 ff ff 54              b.ne     #0x31ea8
0x00031ecc: 78 66 19 29              stp      w24, w25, [x19, #0xc8]
0x00031ed0: 81 09 00 f0              adrp     x1, #0x164000
0x00031ed4: e0 a3 03 91              add      x0, sp, #0xe8
0x00031ed8: e2 63 02 91              add      x2, sp, #0x98
0x00031edc: 21 a8 20 91              add      x1, x1, #0x82a
0x00031ee0: 54 6c ff 97              bl       #0xd030                    ; -> binn_object_get_value@plt
0x00031ee4: c0 e5 ff 35              cbnz     w0, #0x31b9c
0x00031ee8: 32 ff ff 17              b        #0x31bb0                   ; -> CornLoadModelBin+0x164
0x00031eec: e0 0b 40 f9              ldr      x0, [sp, #0x10]
0x00031ef0: f4 6b ff 97              bl       #0xcec0                    ; -> binn_free@plt
0x00031ef4: e0 23 06 91              add      x0, sp, #0x188
0x00031ef8: e1 a3 06 91              add      x1, sp, #0x1a8
0x00031efc: e2 e3 04 91              add      x2, sp, #0x138
0x00031f00: e8 e3 04 91              add      x8, sp, #0x138
0x00031f04: e8 0b 00 f9              str      x8, [sp, #0x10]
0x00031f08: c6 6d ff 97              bl       #0xd620                    ; -> binn_object_next@plt
0x00031f0c: e0 e3 ff 35              cbnz     w0, #0x31b88
0x00031f10: e8 07 40 f9              ldr      x8, [sp, #8]
0x00031f14: 08 15 40 f9              ldr      x8, [x8, #0x28]
0x00031f18: a9 83 5a f8              ldur     x9, [x29, #-0x58]
0x00031f1c: 1f 01 09 eb              cmp      x8, x9
0x00031f20: 41 01 00 54              b.ne     #0x31f48
0x00031f24: e0 03 1f 2a              mov      w0, wzr
0x00031f28: ff c3 0a 91              add      sp, sp, #0x2b0
0x00031f2c: fd 7b 45 a9              ldp      x29, x30, [sp, #0x50]
0x00031f30: f4 4f 44 a9              ldp      x20, x19, [sp, #0x40]
0x00031f34: f6 57 43 a9              ldp      x22, x21, [sp, #0x30]
0x00031f38: f8 5f 42 a9              ldp      x24, x23, [sp, #0x20]
0x00031f3c: fa 67 41 a9              ldp      x26, x25, [sp, #0x10]
0x00031f40: fc 6f c6 a8              ldp      x28, x27, [sp], #0x60
0x00031f44: c0 03 5f d6              ret      
0x00031f48: 1a 6d ff 97              bl       #0xd3b0                    ; -> __stack_chk_fail@plt
