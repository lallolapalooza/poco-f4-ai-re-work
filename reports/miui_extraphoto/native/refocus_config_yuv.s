; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_extraphoto/native/librefocus.so
; target  : Java_com_miui_extraphoto_refocus_manager_DualPhotoJni_configYUVData (0xf8d8)
; start   : 0xf8d8
; size    : 0x238
; bytes   : 0x238   instructions: 142
; generated: 2026-10-24T00:50:24

0x0000f8d8: ff c3 01 d1              sub      sp, sp, #0x70
0x0000f8dc: fc 6f 01 a9              stp      x28, x27, [sp, #0x10]
0x0000f8e0: fa 67 02 a9              stp      x26, x25, [sp, #0x20]
0x0000f8e4: f8 5f 03 a9              stp      x24, x23, [sp, #0x30]
0x0000f8e8: f6 57 04 a9              stp      x22, x21, [sp, #0x40]
0x0000f8ec: f4 4f 05 a9              stp      x20, x19, [sp, #0x50]
0x0000f8f0: fd 7b 06 a9              stp      x29, x30, [sp, #0x60]
0x0000f8f4: fd 83 01 91              add      x29, sp, #0x60
0x0000f8f8: f4 03 00 aa              mov      x20, x0
0x0000f8fc: e0 03 02 aa              mov      x0, x2
0x0000f900: f3 03 05 aa              mov      x19, x5
0x0000f904: f5 03 04 aa              mov      x21, x4
0x0000f908: f6 03 03 aa              mov      x22, x3
0x0000f90c: d5 fc ff 97              bl       #0xec60                    ; -> _Z17getImageByPointerl@plt
0x0000f910: 88 02 40 f9              ldr      x8, [x20]
0x0000f914: fa 03 00 aa              mov      x26, x0
0x0000f918: e0 03 14 aa              mov      x0, x20
0x0000f91c: e1 03 16 aa              mov      x1, x22
0x0000f920: 08 e1 42 f9              ldr      x8, [x8, #0x5c0]
0x0000f924: e2 03 1f aa              mov      x2, xzr
0x0000f928: 00 01 3f d6              blr      x8
0x0000f92c: 88 02 40 f9              ldr      x8, [x20]
0x0000f930: f8 03 00 aa              mov      x24, x0
0x0000f934: e0 03 14 aa              mov      x0, x20
0x0000f938: e1 03 15 aa              mov      x1, x21
0x0000f93c: 08 e1 42 f9              ldr      x8, [x8, #0x5c0]
0x0000f940: e2 03 1f aa              mov      x2, xzr
0x0000f944: 00 01 3f d6              blr      x8
0x0000f948: 88 02 40 f9              ldr      x8, [x20]
0x0000f94c: f9 03 00 aa              mov      x25, x0
0x0000f950: e0 03 14 aa              mov      x0, x20
0x0000f954: e1 03 13 aa              mov      x1, x19
0x0000f958: 08 e1 42 f9              ldr      x8, [x8, #0x5c0]
0x0000f95c: e2 03 1f aa              mov      x2, xzr
0x0000f960: f3 07 00 f9              str      x19, [sp, #8]
0x0000f964: 00 01 3f d6              blr      x8
0x0000f968: 48 a7 40 29              ldp      w8, w9, [x26, #4]
0x0000f96c: 5a 0b 40 f9              ldr      x26, [x26, #0x10]
0x0000f970: f7 03 00 aa              mov      x23, x0
0x0000f974: e0 03 18 aa              mov      x0, x24
0x0000f978: 33 7d 08 1b              mul      w19, w9, w8
0x0000f97c: 68 06 13 0b              add      w8, w19, w19, lsl #1
0x0000f980: 1f 01 00 71              cmp      w8, #0
0x0000f984: 7b 7e 40 93              sxtw     x27, w19
0x0000f988: 08 a5 88 1a              cinc     w8, w8, lt
0x0000f98c: e1 03 1a aa              mov      x1, x26
0x0000f990: e2 03 1b aa              mov      x2, x27
0x0000f994: 1c 7d 01 13              asr      w28, w8, #1
0x0000f998: 9e fc ff 97              bl       #0xec10                    ; -> memcpy@plt
0x0000f99c: 7f 02 1c 6b              cmp      w19, w28
0x0000f9a0: 0a 08 00 54              b.ge     #0xfaa0
0x0000f9a4: e8 03 3b 2a              mvn      w8, w27
0x0000f9a8: 88 03 08 0b              add      w8, w28, w8
0x0000f9ac: 08 7d 01 53              lsr      w8, w8, #1
0x0000f9b0: 08 05 00 11              add      w8, w8, #1
0x0000f9b4: 1f 7d 00 71              cmp      w8, #0x1f
0x0000f9b8: 68 00 00 54              b.hi     #0xf9c4
0x0000f9bc: e9 03 1f aa              mov      x9, xzr
0x0000f9c0: 2c 00 00 14              b        #0xfa70                    ; -> Java_com_miui_extraphoto_refocus_manager_DualPhotoJni_configYUVData+0x198
0x0000f9c4: ed 02 08 8b              add      x13, x23, x8
0x0000f9c8: 2b 03 08 8b              add      x11, x25, x8
0x0000f9cc: 6a 07 08 8b              add      x10, x27, x8, lsl #1
0x0000f9d0: 3f 03 0d eb              cmp      x25, x13
0x0000f9d4: 4f 03 0a 8b              add      x15, x26, x10
0x0000f9d8: f0 27 9f 1a              cset     w16, lo
0x0000f9dc: ff 02 0b eb              cmp      x23, x11
0x0000f9e0: 4e 03 1b 8b              add      x14, x26, x27
0x0000f9e4: f1 27 9f 1a              cset     w17, lo
0x0000f9e8: 3f 03 0f eb              cmp      x25, x15
0x0000f9ec: ea 27 9f 1a              cset     w10, lo
0x0000f9f0: df 01 0b eb              cmp      x14, x11
0x0000f9f4: ec 27 9f 1a              cset     w12, lo
0x0000f9f8: ff 02 0f eb              cmp      x23, x15
0x0000f9fc: eb 27 9f 1a              cset     w11, lo
0x0000fa00: df 01 0d eb              cmp      x14, x13
0x0000fa04: e9 03 1f aa              mov      x9, xzr
0x0000fa08: 0e 02 11 0a              and      w14, w16, w17
0x0000fa0c: ed 27 9f 1a              cset     w13, lo
0x0000fa10: 0e 03 00 37              tbnz     w14, #0, #0xfa70
0x0000fa14: 4a 01 0c 0a              and      w10, w10, w12
0x0000fa18: ca 02 00 37              tbnz     w10, #0, #0xfa70
0x0000fa1c: 6a 01 0d 0a              and      w10, w11, w13
0x0000fa20: 8a 02 00 37              tbnz     w10, #0, #0xfa70
0x0000fa24: 09 69 7b 92              and      x9, x8, #0xffffffe0
0x0000fa28: 4c 03 1b 8b              add      x12, x26, x27
0x0000fa2c: 2a 43 00 91              add      x10, x25, #0x10
0x0000fa30: eb 42 00 91              add      x11, x23, #0x10
0x0000fa34: 7b 07 09 8b              add      x27, x27, x9, lsl #1
0x0000fa38: 8c 81 00 91              add      x12, x12, #0x20
0x0000fa3c: ed 03 09 aa              mov      x13, x9
0x0000fa40: 8e 81 00 d1              sub      x14, x12, #0x20
0x0000fa44: 80 81 40 4c              ld2      {v0.16b, v1.16b}, [x12]
0x0000fa48: c2 81 40 4c              ld2      {v2.16b, v3.16b}, [x14]
0x0000fa4c: ad 81 00 f1              subs     x13, x13, #0x20
0x0000fa50: 8c 01 01 91              add      x12, x12, #0x40
0x0000fa54: 43 85 3f ad              stp      q3, q1, [x10, #-0x10]
0x0000fa58: 62 81 3f ad              stp      q2, q0, [x11, #-0x10]
0x0000fa5c: 4a 81 00 91              add      x10, x10, #0x20
0x0000fa60: 6b 81 00 91              add      x11, x11, #0x20
0x0000fa64: e1 fe ff 54              b.ne     #0xfa40
0x0000fa68: 3f 01 08 eb              cmp      x9, x8
0x0000fa6c: a0 01 00 54              b.eq     #0xfaa0
0x0000fa70: 4c 03 1b 8b              add      x12, x26, x27
0x0000fa74: ea 02 09 8b              add      x10, x23, x9
0x0000fa78: 2b 03 09 8b              add      x11, x25, x9
0x0000fa7c: 8c 05 00 91              add      x12, x12, #1
0x0000fa80: 08 01 09 cb              sub      x8, x8, x9
0x0000fa84: 89 01 40 39              ldrb     w9, [x12]
0x0000fa88: 8d f1 5f 38              ldurb    w13, [x12, #-1]
0x0000fa8c: 08 05 00 f1              subs     x8, x8, #1
0x0000fa90: 8c 09 00 91              add      x12, x12, #2
0x0000fa94: 69 15 00 38              strb     w9, [x11], #1
0x0000fa98: 4d 15 00 38              strb     w13, [x10], #1
0x0000fa9c: 41 ff ff 54              b.ne     #0xfa84
0x0000faa0: 88 02 40 f9              ldr      x8, [x20]
0x0000faa4: e0 03 14 aa              mov      x0, x20
0x0000faa8: e1 03 16 aa              mov      x1, x22
0x0000faac: e2 03 18 aa              mov      x2, x24
0x0000fab0: 08 01 43 f9              ldr      x8, [x8, #0x600]
0x0000fab4: e3 03 1f 2a              mov      w3, wzr
0x0000fab8: 00 01 3f d6              blr      x8
0x0000fabc: 88 02 40 f9              ldr      x8, [x20]
0x0000fac0: e0 03 14 aa              mov      x0, x20
0x0000fac4: e1 03 15 aa              mov      x1, x21
0x0000fac8: e2 03 19 aa              mov      x2, x25
0x0000facc: 08 01 43 f9              ldr      x8, [x8, #0x600]
0x0000fad0: e3 03 1f 2a              mov      w3, wzr
0x0000fad4: 00 01 3f d6              blr      x8
0x0000fad8: 88 02 40 f9              ldr      x8, [x20]
0x0000fadc: e0 03 14 aa              mov      x0, x20
0x0000fae0: e1 07 40 f9              ldr      x1, [sp, #8]
0x0000fae4: e2 03 17 aa              mov      x2, x23
0x0000fae8: 04 01 43 f9              ldr      x4, [x8, #0x600]
0x0000faec: fd 7b 46 a9              ldp      x29, x30, [sp, #0x60]
0x0000faf0: f4 4f 45 a9              ldp      x20, x19, [sp, #0x50]
0x0000faf4: f6 57 44 a9              ldp      x22, x21, [sp, #0x40]
0x0000faf8: f8 5f 43 a9              ldp      x24, x23, [sp, #0x30]
0x0000fafc: fa 67 42 a9              ldp      x26, x25, [sp, #0x20]
0x0000fb00: fc 6f 41 a9              ldp      x28, x27, [sp, #0x10]
0x0000fb04: e3 03 1f 2a              mov      w3, wzr
0x0000fb08: ff c3 01 91              add      sp, sp, #0x70
0x0000fb0c: 80 00 1f d6              br       x4
