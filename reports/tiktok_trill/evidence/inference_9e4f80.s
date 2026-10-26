; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_trill/native/libeffect.so
; target  : address 0x9e4f80
; start   : 0x9e4f80
; size    : 0x1a0
; bytes   : 0x1a0   instructions: 104
; generated: 2026-10-24T01:18:43

0x009e4f80: e3 83 3f 91              add      x3, sp, #0xfe0
0x009e4f84: e7 e8 00 94              bl       #0xa1f320
0x009e4f88: 74 0f 40 f9              ldr      x20, [x27, #0x18]
0x009e4f8c: 01 1e 00 90              adrp     x1, #0xda4000
0x009e4f90: e0 83 1b 91              add      x0, sp, #0x6e0
0x009e4f94: 70 15 03 94              bl       #0xaaa554
0x009e4f98: e8 c3 07 91              add      x8, sp, #0x1f0
0x009e4f9c: e1 83 1b 91              add      x1, sp, #0x6e0
0x009e4fa0: a4 00 03 94              bl       #0xaa5230
0x009e4fa4: 90 e6 03 94              bl       #0xade9e4
0x009e4fa8: e8 eb 4f b9              ldr      w8, [sp, #0xfe8]
0x009e4fac: e9 03 42 b9              ldr      w9, [sp, #0x200]
0x009e4fb0: e4 6f a8 52              mov      w4, #0x437f0000
0x009e4fb4: 1f 01 09 6b              cmp      w8, w9
0x009e4fb8: a1 07 00 54              b.ne     #0x9e50ac
0x009e4fbc: e9 ef 4f b9              ldr      w9, [sp, #0xfec]
0x009e4fc0: ea ff 41 b9              ldr      w10, [sp, #0x1fc]
0x009e4fc4: 3f 01 0a 6b              cmp      w9, w10
0x009e4fc8: 21 07 00 54              b.ne     #0x9e50ac
0x009e4fcc: ea e3 4f b9              ldr      w10, [sp, #0xfe0]
0x009e4fd0: eb 07 42 b9              ldr      w11, [sp, #0x204]
0x009e4fd4: 4a 2d 43 d3              ubfx     x10, x10, #3, #9
0x009e4fd8: 4a 05 00 91              add      x10, x10, #1
0x009e4fdc: 5f 01 0b 6b              cmp      w10, w11
0x009e4fe0: 61 06 00 54              b.ne     #0x9e50ac
0x009e4fe4: eb 0b 42 b9              ldr      w11, [sp, #0x208]
0x009e4fe8: 7f 09 00 71              cmp      w11, #2
0x009e4fec: 00 01 00 54              b.eq     #0x9e500c
0x009e4ff0: 7f 05 00 71              cmp      w11, #1
0x009e4ff4: 61 01 00 54              b.ne     #0x9e5020
0x009e4ff8: e1 fb 40 f9              ldr      x1, [sp, #0x1f0]
0x009e4ffc: e0 83 3f 91              add      x0, sp, #0xfe0
0x009e5000: 02 10 80 52              mov      w2, #0x80
0x009e5004: 58 d1 f3 97              bl       #0x6d9564
0x009e5008: 29 00 00 14              b        #0x9e50ac
0x009e500c: e1 fb 40 f9              ldr      x1, [sp, #0x1f0]
0x009e5010: e0 83 3f 91              add      x0, sp, #0xfe0
0x009e5014: 02 10 80 52              mov      w2, #0x80
0x009e5018: 99 d1 f3 97              bl       #0x6d967c
0x009e501c: 24 00 00 14              b        #0x9e50ac
0x009e5020: 6f 23 40 f9              ldr      x15, [x27, #0x40]
0x009e5024: ed fb 47 f9              ldr      x13, [sp, #0xff0]
0x009e5028: ee fb 40 f9              ldr      x14, [sp, #0x1f0]
0x009e502c: eb 03 1f 2a              mov      w11, wzr
0x009e5030: ec 03 1f 2a              mov      w12, wzr
0x009e5034: ef d1 04 91              add      x15, x15, #0x134
0x009e5038: 30 7d 0a 1b              mul      w16, w9, w10
0x009e503c: 9f 01 08 6b              cmp      w12, w8
0x009e5040: 6a 03 00 54              b.ge     #0x9e50ac
0x009e5044: f1 03 1f 2a              mov      w17, wzr
0x009e5048: e0 03 0b 2a              mov      w0, w11
0x009e504c: 3f 02 09 6b              cmp      w17, w9
0x009e5050: 8a 02 00 54              b.ge     #0x9e50a0
0x009e5054: e1 03 00 2a              mov      w1, w0
0x009e5058: e2 03 0f aa              mov      x2, x15
0x009e505c: e3 03 0a aa              mov      x3, x10
0x009e5060: a3 01 00 b4              cbz      x3, #0x9e5094
0x009e5064: a0 c9 61 3c              ldr      b0, [x13, w1, sxtw]
0x009e5068: 42 40 5f bc              ldur     s2, [x2, #-0xc]
0x009e506c: 43 44 40 bc              ldr      s3, [x2], #4
0x009e5070: 81 00 27 1e              fmov     s1, w4
0x009e5074: 00 d8 21 7e              ucvtf    s0, s0
0x009e5078: 00 18 21 1e              fdiv     s0, s0, s1
0x009e507c: 00 38 22 1e              fsub     s0, s0, s2
0x009e5080: 00 18 23 1e              fdiv     s0, s0, s3
0x009e5084: 63 04 00 d1              sub      x3, x3, #1
0x009e5088: c0 d9 21 bc              str      s0, [x14, w1, sxtw #2]
0x009e508c: 21 04 00 11              add      w1, w1, #1
0x009e5090: a3 fe ff b5              cbnz     x3, #0x9e5064
0x009e5094: 31 06 00 11              add      w17, w17, #1
0x009e5098: 00 00 0a 0b              add      w0, w0, w10
0x009e509c: ec ff ff 17              b        #0x9e504c
0x009e50a0: 8c 05 00 11              add      w12, w12, #1
0x009e50a4: 6b 01 10 0b              add      w11, w11, w16
0x009e50a8: e5 ff ff 17              b        #0x9e503c
0x009e50ac: 32 cf f3 97              bl       #0x6d8d74
0x009e50b0: 68 0f 40 f9              ldr      x8, [x27, #0x18]
0x009e50b4: 4c c6 02 94              bl       #0xa969e4
0x009e50b8: 12 8c e3 97              bl       #0x2c8100                  ; -> _ZN8espresso8Thrustor9InferenceEv@plt
0x009e50bc: 2e cf f3 97              bl       #0x6d8d74
0x009e50c0: e1 1d 00 f0              adrp     x1, #0xda4000
0x009e50c4: a2 1d 00 90              adrp     x2, #0xd99000
0x009e50c8: 03 00 14 cb              sub      x3, x0, x20
0x009e50cc: c1 c7 02 94              bl       #0xa96fd0
0x009e50d0: 42 b0 3d 91              add      x2, x2, #0xf6c
0x009e50d4: 3b 81 e3 97              bl       #0x2c55c0                  ; -> __android_log_print@plt
0x009e50d8: 68 77 49 b9              ldr      w8, [x27, #0x974]
0x009e50dc: 60 0f 40 f9              ldr      x0, [x27, #0x18]
0x009e50e0: c8 00 00 34              cbz      w8, #0x9e50f8
0x009e50e4: 68 27 40 f9              ldr      x8, [x27, #0x48]
0x009e50e8: 01 21 40 f9              ldr      x1, [x8, #0x40]
0x009e50ec: e8 43 07 91              add      x8, sp, #0x1d0
0x009e50f0: 00 8c e3 97              bl       #0x2c80f0                  ; -> _ZN8espresso8Thrustor7ExtractERKNSt6__ndk112basic_stringIcNS1_11char_traitsIcEENS1_9allocatorIcEEEE@plt
0x009e50f4: 03 00 00 14              b        #0x9e5100
0x009e50f8: e8 43 07 91              add      x8, sp, #0x1d0
0x009e50fc: 05 8c e3 97              bl       #0x2c8110                  ; -> _ZN8espresso8Thrustor9getOutputEv@plt
0x009e5100: e8 eb 40 f9              ldr      x8, [sp, #0x1d0]
0x009e5104: a8 13 00 b4              cbz      x8, #0x9e5378
0x009e5108: 68 27 40 f9              ldr      x8, [x27, #0x48]
0x009e510c: 69 77 49 b9              ldr      w9, [x27, #0x974]
0x009e5110: 60 0f 40 f9              ldr      x0, [x27, #0x18]
0x009e5114: 08 21 40 f9              ldr      x8, [x8, #0x40]
0x009e5118: 3f 01 00 71              cmp      w9, #0
0x009e511c: 0a 61 00 91              add      x10, x8, #0x18
