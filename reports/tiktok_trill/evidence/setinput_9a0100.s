; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_trill/native/libeffect.so
; target  : address 0x9a0100
; start   : 0x9a0100
; size    : 0xc0
; bytes   : 0xc0   instructions: 48
; generated: 2026-10-24T01:18:43

0x009a0100: eb 6b 03 f9              str      x11, [sp, #0x6d0]
0x009a0104: e9 23 10 29              stp      w9, w8, [sp, #0x80]
0x009a0108: 8b a1 08 94              bl       #0xbc8734
0x009a010c: 89 17 80 b9              ldrsw    x9, [x28, #0x14]
0x009a0110: e8 03 1f aa              mov      x8, xzr
0x009a0114: 0a 80 a7 52              mov      w10, #0x3c000000
0x009a0118: 00 10 3e 1e              fmov     s0, #-1.00000000
0x009a011c: 1f 01 09 eb              cmp      x8, x9
0x009a0120: 6a 01 00 54              b.ge     #0x9a014c
0x009a0124: eb 2f 44 f9              ldr      x11, [sp, #0x858]
0x009a0128: 42 01 27 1e              fmov     s2, w10
0x009a012c: 61 69 68 3c              ldr      b1, [x11, x8]
0x009a0130: 8b 2f 40 f9              ldr      x11, [x28, #0x58]
0x009a0134: 21 d8 21 7e              ucvtf    s1, s1
0x009a0138: 21 08 22 1e              fmul     s1, s1, s2
0x009a013c: 21 28 20 1e              fadd     s1, s1, s0
0x009a0140: 61 79 28 bc              str      s1, [x11, x8, lsl #2]
0x009a0144: 08 05 00 91              add      x8, x8, #1
0x009a0148: f5 ff ff 17              b        #0x9a011c
0x009a014c: 97 2b 40 f9              ldr      x23, [x28, #0x50]
0x009a0150: f8 47 00 f0              adrp     x24, #0x129f000
0x009a0154: 18 63 01 91              add      x24, x24, #0x58
0x009a0158: e0 a3 01 91              add      x0, sp, #0x68
0x009a015c: 2d 13 04 94              bl       #0xaa4e10
0x009a0160: 85 a3 41 29              ldp      w5, w8, [x28, #0xc]
0x009a0164: 82 2f 40 f9              ldr      x2, [x28, #0x58]
0x009a0168: 84 0b 40 b9              ldr      w4, [x28, #8]
0x009a016c: e1 a3 01 91              add      x1, sp, #0x68
0x009a0170: 08 05 08 0b              add      w8, w8, w8, lsl #1
0x009a0174: 03 75 1e 53              lsl      w3, w8, #2
0x009a0178: e0 03 17 aa              mov      x0, x23
0x009a017c: 91 a3 e4 97              bl       #0x2c8fc0                  ; -> _ZN8espresso8Thrustor8SetInputENSt6__ndk112basic_stringIcNS1_11char_traitsIcEENS1_9allocatorIcEEEEPviii@plt
0x009a0180: 87 be 03 94              bl       #0xa8fb9c
0x009a0184: 80 2b 40 f9              ldr      x0, [x28, #0x50]
0x009a0188: de 9f e4 97              bl       #0x2c8100                  ; -> _ZN8espresso8Thrustor9InferenceEv@plt
0x009a018c: 80 2b 40 f9              ldr      x0, [x28, #0x50]
0x009a0190: 01 63 00 91              add      x1, x24, #0x18
0x009a0194: e8 23 01 91              add      x8, sp, #0x48
0x009a0198: d6 9f e4 97              bl       #0x2c80f0                  ; -> _ZN8espresso8Thrustor7ExtractERKNSt6__ndk112basic_stringIcNS1_11char_traitsIcEENS1_9allocatorIcEEEE@plt
0x009a019c: 82 07 41 29              ldp      w2, w1, [x28, #8]
0x009a01a0: f7 27 40 f9              ldr      x23, [sp, #0x48]
0x009a01a4: e0 23 1e 91              add      x0, sp, #0x788
0x009a01a8: 03 03 80 52              mov      w3, #0x18
0x009a01ac: 6a af 06 94              bl       #0xb4bf54
0x009a01b0: e8 03 1f aa              mov      x8, xzr
0x009a01b4: 09 60 a8 52              mov      w9, #0x43000000
0x009a01b8: ea 1f 80 52              mov      w10, #0xff
0x009a01bc: 8b 1b 80 b9              ldrsw    x11, [x28, #0x18]
