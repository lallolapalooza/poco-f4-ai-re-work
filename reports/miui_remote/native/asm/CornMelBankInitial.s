; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : CornMelBankInitial (0x328e0)
; start   : 0x328e0
; size    : 0x270
; bytes   : 0x270   instructions: 156
; generated: 2026-10-23T23:30:08

0x000328e0: ff 03 03 d1              sub      sp, sp, #0xc0
0x000328e4: ef 3b 02 6d              stp      d15, d14, [sp, #0x20]
0x000328e8: ed 33 03 6d              stp      d13, d12, [sp, #0x30]
0x000328ec: eb 2b 04 6d              stp      d11, d10, [sp, #0x40]
0x000328f0: e9 23 05 6d              stp      d9, d8, [sp, #0x50]
0x000328f4: fc 6f 06 a9              stp      x28, x27, [sp, #0x60]
0x000328f8: fa 67 07 a9              stp      x26, x25, [sp, #0x70]
0x000328fc: f8 5f 08 a9              stp      x24, x23, [sp, #0x80]
0x00032900: f6 57 09 a9              stp      x22, x21, [sp, #0x90]
0x00032904: f4 4f 0a a9              stp      x20, x19, [sp, #0xa0]
0x00032908: fd 7b 0b a9              stp      x29, x30, [sp, #0xb0]
0x0003290c: fd c3 02 91              add      x29, sp, #0xb0
0x00032910: 6d 00 40 bd              ldr      s13, [x3]
0x00032914: 6a 30 40 b9              ldr      w10, [x3, #0x30]
0x00032918: 89 09 00 d0              adrp     x9, #0x164000
0x0003291c: 01 10 2c 1e              fmov     s1, #0.50000000
0x00032920: 2b f9 49 bd              ldr      s11, [x9, #0x9f8]
0x00032924: f6 03 01 aa              mov      x22, x1
0x00032928: 40 00 27 1e              fmov     s0, w2
0x0003292c: 5f 01 00 71              cmp      w10, #0
0x00032930: a1 09 21 1e              fmul     s1, s13, s1
0x00032934: c8 fe 60 d3              lsr      x8, x22, #0x20
0x00032938: 49 a5 8a 1a              cinc     w9, w10, lt
0x0003293c: 21 28 20 1e              fadd     s1, s1, s0
0x00032940: 08 20 20 1e              fcmp     s0, #0.0
0x00032944: 09 cc 21 1e              fcsel    s9, s0, s1, gt
0x00032948: 00 01 27 1e              fmov     s0, w8
0x0003294c: 0a 10 2e 1e              fmov     s10, #1.00000000
0x00032950: 00 18 2b 1e              fdiv     s0, s0, s11
0x00032954: 00 28 2a 1e              fadd     s0, s0, s10
0x00032958: f5 03 04 aa              mov      x21, x4
0x0003295c: f4 03 00 aa              mov      x20, x0
0x00032960: ea 1f 00 b9              str      w10, [sp, #0x1c]
0x00032964: 33 7d 01 13              asr      w19, w9, #1
0x00032968: 9e 6b ff 97              bl       #0xd7e0                    ; -> logf@plt
0x0003296c: 08 1c a0 4e              mov      v8.16b, v0.16b
0x00032970: 20 19 2b 1e              fdiv     s0, s9, s11
0x00032974: 00 28 2a 1e              fadd     s0, s0, s10
0x00032978: 9a 6b ff 97              bl       #0xd7e0                    ; -> logf@plt
0x0003297c: f5 0b 00 f9              str      x21, [sp, #0x10]
0x00032980: a0 86 41 f9              ldr      x0, [x21, #0x308]
0x00032984: 77 7e 7e 93              sbfiz    x23, x19, #2, #0x20
0x00032988: e1 03 17 aa              mov      x1, x23
0x0003298c: 09 1c a0 4e              mov      v9.16b, v0.16b
0x00032990: e0 69 ff 97              bl       #0xd110                    ; -> CornMpoolAlloc@plt
0x00032994: e1 03 1f 2a              mov      w1, wzr
0x00032998: e2 03 17 aa              mov      x2, x23
0x0003299c: f5 03 00 aa              mov      x21, x0
0x000329a0: 50 6c ff 97              bl       #0xdae0                    ; -> memset@plt
0x000329a4: df 06 00 71              cmp      w22, #1
0x000329a8: cb 0b 00 54              b.lt     #0x32b20
0x000329ac: e8 1f 40 b9              ldr      w8, [sp, #0x1c]
0x000329b0: c9 06 00 11              add      w9, w22, #1
0x000329b4: fc 03 1f aa              mov      x28, xzr
0x000329b8: f7 03 13 2a              mov      w23, w19
0x000329bc: 00 01 22 1e              scvtf    s0, w8
0x000329c0: 88 09 00 d0              adrp     x8, #0x164000
0x000329c4: 0c fd 49 bd              ldr      s12, [x8, #0x9fc]
0x000329c8: ad 19 20 1e              fdiv     s13, s13, s0
0x000329cc: 20 01 22 1e              scvtf    s0, w9
0x000329d0: d6 7e 40 92              and      x22, x22, #0xffffffff
0x000329d4: 08 09 2c 1e              fmul     s8, s8, s12
0x000329d8: 21 09 2c 1e              fmul     s1, s9, s12
0x000329dc: 21 38 28 1e              fsub     s1, s1, s8
0x000329e0: 29 18 20 1e              fdiv     s9, s1, s0
0x000329e4: f9 07 1d 32              mov      w25, #0x18
0x000329e8: e9 23 01 2d              stp      s9, s8, [sp, #8]
0x000329ec: 9b 07 00 91              add      x27, x28, #1
0x000329f0: 60 03 22 1e              scvtf    s0, w27
0x000329f4: 20 09 20 1e              fmul     s0, s9, s0
0x000329f8: 0f 29 20 1e              fadd     s15, s8, s0
0x000329fc: e0 19 2c 1e              fdiv     s0, s15, s12
0x00032a00: f8 68 ff 97              bl       #0xcde0                    ; -> expf@plt
0x00032a04: e9 1f 40 b9              ldr      w9, [sp, #0x1c]
0x00032a08: 01 10 3e 1e              fmov     s1, #-1.00000000
0x00032a0c: 00 28 21 1e              fadd     s0, s0, s1
0x00032a10: 88 53 19 9b              madd     x8, x28, x25, x20
0x00032a14: 00 08 2b 1e              fmul     s0, s0, s11
0x00032a18: 3f 09 00 71              cmp      w9, #2
0x00032a1c: 00 05 00 bd              str      s0, [x8, #4]
0x00032a20: cb 04 00 54              b.lt     #0x32ab8
0x00032a24: 80 03 22 1e              scvtf    s0, w28
0x00032a28: 88 0b 00 11              add      w8, w28, #2
0x00032a2c: 20 09 20 1e              fmul     s0, s9, s0
0x00032a30: 01 01 22 1e              scvtf    s1, w8
0x00032a34: 0e 29 20 1e              fadd     s14, s8, s0
0x00032a38: 20 09 21 1e              fmul     s0, s9, s1
0x00032a3c: 09 29 20 1e              fadd     s9, s8, s0
0x00032a40: f3 03 1f aa              mov      x19, xzr
0x00032a44: 18 00 80 12              mov      w24, #-1
0x00032a48: e8 39 2e 1e              fsub     s8, s15, s14
0x00032a4c: 2a 39 2f 1e              fsub     s10, s9, s15
0x00032a50: 1a 00 80 12              mov      w26, #-1
0x00032a54: 60 02 22 1e              scvtf    s0, w19
0x00032a58: a0 09 20 1e              fmul     s0, s13, s0
0x00032a5c: 00 18 2b 1e              fdiv     s0, s0, s11
0x00032a60: 01 10 2e 1e              fmov     s1, #1.00000000
0x00032a64: 00 28 21 1e              fadd     s0, s0, s1
0x00032a68: 5e 6b ff 97              bl       #0xd7e0                    ; -> logf@plt
0x00032a6c: 00 08 2c 1e              fmul     s0, s0, s12
0x00032a70: 00 20 2e 1e              fcmp     s0, s14
0x00032a74: ad 01 00 54              b.le     #0x32aa8
0x00032a78: 00 20 29 1e              fcmp     s0, s9
0x00032a7c: 65 01 00 54              b.pl     #0x32aa8
0x00032a80: 21 39 20 1e              fsub     s1, s9, s0
0x00032a84: 02 38 2e 1e              fsub     s2, s0, s14
0x00032a88: 00 20 2f 1e              fcmp     s0, s15
0x00032a8c: 40 8d 28 1e              fcsel    s0, s10, s8, hi
0x00032a90: 21 8c 22 1e              fcsel    s1, s1, s2, hi
0x00032a94: 5f 07 00 31              cmn      w26, #1
0x00032a98: 20 18 20 1e              fdiv     s0, s1, s0
0x00032a9c: 7a 02 9a 1a              csel     w26, w19, w26, eq
0x00032aa0: f8 03 13 2a              mov      w24, w19
0x00032aa4: a0 7a 33 bc              str      s0, [x21, x19, lsl #2]
0x00032aa8: 73 06 00 91              add      x19, x19, #1
0x00032aac: ff 02 13 eb              cmp      x23, x19
0x00032ab0: 21 fd ff 54              b.ne     #0x32a54
0x00032ab4: 03 00 00 14              b        #0x32ac0                   ; -> CornMelBankInitial+0x1e0
0x00032ab8: 1a 00 80 12              mov      w26, #-1
0x00032abc: 18 00 80 12              mov      w24, #-1
0x00032ac0: 93 53 19 9b              madd     x19, x28, x25, x20
0x00032ac4: 7a 02 00 b9              str      w26, [x19]
0x00032ac8: e9 0b 40 f9              ldr      x9, [sp, #0x10]
0x00032acc: 08 7f 40 93              sxtw     x8, w24
0x00032ad0: 20 85 41 f9              ldr      x0, [x9, #0x308]
0x00032ad4: e9 03 00 32              mov      w9, #1
0x00032ad8: 09 f5 7e b3              bfi      x9, x8, #2, #0x3e
0x00032adc: 48 7f 40 93              sxtw     x8, w26
0x00032ae0: 21 01 08 cb              sub      x1, x9, x8
0x00032ae4: 8b 69 ff 97              bl       #0xd110                    ; -> CornMpoolAlloc@plt
0x00032ae8: 60 0a 00 f9              str      x0, [x19, #0x10]
0x00032aec: 1f 00 00 b9              str      wzr, [x0]
0x00032af0: e8 03 00 32              mov      w8, #1
0x00032af4: 60 0a 40 f9              ldr      x0, [x19, #0x10]
0x00032af8: 08 01 1a 4b              sub      w8, w8, w26
0x00032afc: 08 01 18 0b              add      w8, w8, w24
0x00032b00: a1 ca 3a 8b              add      x1, x21, w26, sxtw #2
0x00032b04: 02 7d 7e 93              sbfiz    x2, x8, #2, #0x20
0x00032b08: 68 0a 00 b9              str      w8, [x19, #8]
0x00032b0c: dd 6b ff 97              bl       #0xda80                    ; -> memcpy@plt
0x00032b10: e9 23 41 2d              ldp      s9, s8, [sp, #8]
0x00032b14: 7f 03 16 eb              cmp      x27, x22
0x00032b18: fc 03 1b aa              mov      x28, x27
0x00032b1c: 81 f6 ff 54              b.ne     #0x329ec
0x00032b20: fd 7b 4b a9              ldp      x29, x30, [sp, #0xb0]
0x00032b24: f4 4f 4a a9              ldp      x20, x19, [sp, #0xa0]
0x00032b28: f6 57 49 a9              ldp      x22, x21, [sp, #0x90]
0x00032b2c: f8 5f 48 a9              ldp      x24, x23, [sp, #0x80]
0x00032b30: fa 67 47 a9              ldp      x26, x25, [sp, #0x70]
0x00032b34: fc 6f 46 a9              ldp      x28, x27, [sp, #0x60]
0x00032b38: e9 23 45 6d              ldp      d9, d8, [sp, #0x50]
0x00032b3c: eb 2b 44 6d              ldp      d11, d10, [sp, #0x40]
0x00032b40: ed 33 43 6d              ldp      d13, d12, [sp, #0x30]
0x00032b44: ef 3b 42 6d              ldp      d15, d14, [sp, #0x20]
0x00032b48: ff 03 03 91              add      sp, sp, #0xc0
0x00032b4c: c0 03 5f d6              ret      
