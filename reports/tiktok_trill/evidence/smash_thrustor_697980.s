; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_trill/native/libeffect.so
; target  : address 0x697980
; start   : 0x697980
; size    : 0x230
; bytes   : 0x230   instructions: 140
; generated: 2026-10-24T01:16:45

0x00697980: 14 b8 ff 97              bl       #0x6859d0
0x00697984: 39 23 00 91              add      x25, x25, #8
0x00697988: f9 ff ff 17              b        #0x69796c
0x0069798c: 88 02 40 f9              ldr      x8, [x20]
0x00697990: e0 03 16 aa              mov      x0, x22
0x00697994: 01 01 19 8b              add      x1, x8, x25
0x00697998: 0e b8 ff 97              bl       #0x6859d0
0x0069799c: 39 23 00 91              add      x25, x25, #8
0x006979a0: 3f 43 0d f1              cmp      x25, #0x350
0x006979a4: 41 ff ff 54              b.ne     #0x69798c
0x006979a8: e0 03 17 aa              mov      x0, x23
0x006979ac: 73 51 10 94              bl       #0xaabf78
0x006979b0: 18 ff ff 97              bl       #0x697610
0x006979b4: e0 02 04 91              add      x0, x23, #0x100
0x006979b8: 92 17 10 94              bl       #0xa9d800
0x006979bc: 15 ff ff 97              bl       #0x697610
0x006979c0: e8 03 1f aa              mov      x8, xzr
0x006979c4: 1f 21 04 f1              cmp      x8, #0x108
0x006979c8: a0 00 00 54              b.eq     #0x6979dc
0x006979cc: a9 02 40 f9              ldr      x9, [x21]
0x006979d0: 8a 02 40 f9              ldr      x10, [x20]
0x006979d4: 43 28 10 94              bl       #0xaa1ae0
0x006979d8: fb ff ff 17              b        #0x6979c4
0x006979dc: e8 03 1f aa              mov      x8, xzr
0x006979e0: 1f 21 09 f1              cmp      x8, #0x248
0x006979e4: 80 00 00 54              b.eq     #0x6979f4
0x006979e8: 69 02 40 f9              ldr      x9, [x19]
0x006979ec: 29 cb 10 94              bl       #0xaca690
0x006979f0: fc ff ff 17              b        #0x6979e0
0x006979f4: fd 7b 44 a9              ldp      x29, x30, [sp, #0x40]
0x006979f8: 7c dd 0f 14              b        #0xa8efe8
0x006979fc: f4 4f be a9              stp      x20, x19, [sp, #-0x20]!
0x00697a00: fd 7b 01 a9              stp      x29, x30, [sp, #0x10]
0x00697a04: fd 43 00 91              add      x29, sp, #0x10
0x00697a08: f4 03 00 aa              mov      x20, x0
0x00697a0c: 00 08 40 f9              ldr      x0, [x0, #0x10]
0x00697a10: f3 03 01 aa              mov      x19, x1
0x00697a14: 80 00 00 b5              cbnz     x0, #0x697a24
0x00697a18: 80 62 00 91              add      x0, x20, #0x18
0x00697a1c: 6a 00 00 94              bl       #0x697bc4
0x00697a20: 80 0a 00 f9              str      x0, [x20, #0x10]
0x00697a24: fd 7b 41 a9              ldp      x29, x30, [sp, #0x10]
0x00697a28: 11 27 11 14              b        #0xae166c
0x00697a2c: ff 83 01 d1              sub      sp, sp, #0x60
0x00697a30: f5 1b 00 f9              str      x21, [sp, #0x30]
0x00697a34: f4 4f 04 a9              stp      x20, x19, [sp, #0x40]
0x00697a38: fd 7b 05 a9              stp      x29, x30, [sp, #0x50]
0x00697a3c: fd 43 01 91              add      x29, sp, #0x50
0x00697a40: 55 d0 3b d5              mrs      x21, tpidr_el0
0x00697a44: a8 16 40 f9              ldr      x8, [x21, #0x28]
0x00697a48: f3 03 00 aa              mov      x19, x0
0x00697a4c: f0 13 10 94              bl       #0xa9ca0c
0x00697a50: 69 06 40 f9              ldr      x9, [x19, #8]
0x00697a54: 00 00 40 f9              ldr      x0, [x0]
0x00697a58: e8 23 00 91              add      x8, sp, #8
0x00697a5c: e1 03 09 aa              mov      x1, x9
0x00697a60: a4 c1 f0 97              bl       #0x2c80f0                  ; -> _ZN8espresso8Thrustor7ExtractERKNSt6__ndk112basic_stringIcNS1_11char_traitsIcEENS1_9allocatorIcEEEE@plt
0x00697a64: 68 3a 40 b9              ldr      w8, [x19, #0x38]
0x00697a68: 1f 41 00 71              cmp      w8, #0x10
0x00697a6c: c0 00 00 54              b.eq     #0x697a84
0x00697a70: 1f 21 00 71              cmp      w8, #8
0x00697a74: c1 00 00 54              b.ne     #0x697a8c
0x00697a78: 40 af 10 94              bl       #0xac3778
0x00697a7c: a0 06 01 94              bl       #0x6d94fc
0x00697a80: 03 00 00 14              b        #0x697a8c
0x00697a84: 3d af 10 94              bl       #0xac3778
0x00697a88: e4 06 01 94              bl       #0x6d9618
0x00697a8c: 60 02 40 f9              ldr      x0, [x19]
0x00697a90: 9c c1 f0 97              bl       #0x2c8100                  ; -> _ZN8espresso8Thrustor9InferenceEv@plt
0x00697a94: a8 16 40 f9              ldr      x8, [x21, #0x28]
0x00697a98: 05 ce 0f 94              bl       #0xa8b2ac
0x00697a9c: a1 00 00 54              b.ne     #0x697ab0
0x00697aa0: fd 7b 45 a9              ldp      x29, x30, [sp, #0x50]
0x00697aa4: f4 4f 44 a9              ldp      x20, x19, [sp, #0x40]
0x00697aa8: f5 1b 40 f9              ldr      x21, [sp, #0x30]
0x00697aac: e5 ce 0f 14              b        #0xa8b640
0x00697ab0: 60 b6 f0 97              bl       #0x2c5430                  ; -> __stack_chk_fail@plt
0x00697ab4: ff 83 02 d1              sub      sp, sp, #0xa0
0x00697ab8: fb 23 00 f9              str      x27, [sp, #0x40]
0x00697abc: e2 03 1e aa              mov      x2, x30
0x00697ac0: 9b d6 0f 94              bl       #0xa8d52c
0x00697ac4: fe 03 02 aa              mov      x30, x2
0x00697ac8: fd 7b 09 a9              stp      x29, x30, [sp, #0x90]
0x00697acc: fd 43 02 91              add      x29, sp, #0x90
0x00697ad0: 55 d0 3b d5              mrs      x21, tpidr_el0
0x00697ad4: a8 16 40 f9              ldr      x8, [x21, #0x28]
0x00697ad8: f3 03 01 aa              mov      x19, x1
0x00697adc: f4 03 00 aa              mov      x20, x0
0x00697ae0: 96 78 10 94              bl       #0xab5d38
0x00697ae4: 36 08 40 f9              ldr      x22, [x1, #0x10]
0x00697ae8: 21 38 00 d0              adrp     x1, #0xd9d000
0x00697aec: 21 90 32 91              add      x1, x1, #0xca4
0x00697af0: 5a 46 f1 97              bl       #0x2e9458
0x00697af4: c1 02 40 f9              ldr      x1, [x22]
0x00697af8: e0 03 00 91              mov      x0, sp
0x00697afc: e2 83 00 91              add      x2, sp, #0x20
0x00697b00: b6 fc ff 97              bl       #0x696dd8
0x00697b04: f6 03 40 f9              ldr      x22, [sp]
0x00697b08: f7 67 41 29              ldp      w23, w25, [sp, #8]
0x00697b0c: f8 6f 42 29              ldp      w24, w27, [sp, #0x10]
0x00697b10: fa 1f 40 b9              ldr      w26, [sp, #0x1c]
0x00697b14: 30 d0 0f 94              bl       #0xa8bbd4
0x00697b18: e0 03 1a 4b              neg      w0, w26
0x00697b1c: ad d6 10 94              bl       #0xacd5d0
0x00697b20: 2a 7f 17 1b              mul      w10, w25, w23
0x00697b24: 4a 7d 18 1b              mul      w10, w10, w24
0x00697b28: 4b 00 80 52              mov      w11, #2
0x00697b2c: 4a 7d 1b 1b              mul      w10, w10, w27
0x00697b30: 4a 0d cb 1a              sdiv     w10, w10, w11
0x00697b34: 4b 60 00 f0              adrp     x11, #0x12a2000
0x00697b38: 17 93 10 94              bl       #0xabc794
0x00697b3c: c9 12 00 91              add      x9, x22, #4
0x00697b40: 4a 7d 40 93              sxtw     x10, w10
0x00697b44: 6b 41 15 91              add      x11, x11, #0x550
0x00697b48: 1f 01 0a eb              cmp      x8, x10
0x00697b4c: 0a 02 00 54              b.ge     #0x697b8c
0x00697b50: 21 c1 5f bc              ldur     s1, [x9, #-4]
0x00697b54: 6c 09 08 8b              add      x12, x11, x8, lsl #2
0x00697b58: 6d 62 40 f9              ldr      x13, [x19, #0xc0]
0x00697b5c: 8c a1 86 b9              ldrsw    x12, [x12, #0x6a0]
0x00697b60: 21 08 20 1e              fmul     s1, s1, s0
0x00697b64: 08 05 00 91              add      x8, x8, #1
0x00697b68: a1 79 2c bc              str      s1, [x13, x12, lsl #2]
0x00697b6c: 6d 7e 40 f9              ldr      x13, [x19, #0xf8]
0x00697b70: 21 85 40 bc              ldr      s1, [x9], #8
0x00697b74: 6e 62 40 f9              ldr      x14, [x19, #0xc0]
0x00697b78: ad 01 40 f9              ldr      x13, [x13]
0x00697b7c: 21 08 20 1e              fmul     s1, s1, s0
0x00697b80: cd 01 0d 8b              add      x13, x14, x13
0x00697b84: a1 79 2c bc              str      s1, [x13, x12, lsl #2]
0x00697b88: f0 ff ff 17              b        #0x697b48
0x00697b8c: 61 c2 02 91              add      x1, x19, #0xb0
0x00697b90: e0 03 14 aa              mov      x0, x20
0x00697b94: 2e d1 12 94              bl       #0xb4c04c
0x00697b98: a8 16 40 f9              ldr      x8, [x21, #0x28]
0x00697b9c: 91 ce 0f 94              bl       #0xa8b5e0
0x00697ba0: 01 01 00 54              b.ne     #0x697bc0
0x00697ba4: fd 7b 49 a9              ldp      x29, x30, [sp, #0x90]
0x00697ba8: f4 4f 48 a9              ldp      x20, x19, [sp, #0x80]
0x00697bac: f6 57 47 a9              ldp      x22, x21, [sp, #0x70]
