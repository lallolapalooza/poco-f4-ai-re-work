; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libqvasml.so
; target  : address 0x5e540
; start   : 0x5e540
; size    : 0x188
; bytes   : 0x188   instructions: 98
; generated: 2026-10-23T23:25:23

0x0005e540: 3f 23 03 d5              paciasp  
0x0005e544: fd 7b be a9              stp      x29, x30, [sp, #-0x20]!
0x0005e548: f4 4f 01 a9              stp      x20, x19, [sp, #0x10]
0x0005e54c: fd 03 00 91              mov      x29, sp
0x0005e550: 34 58 17 53              lsl      w20, w1, #9
0x0005e554: 00 67 01 0f              movi     v0.2s, #0x38, lsl #24
0x0005e558: 89 16 01 4b              sub      w9, w20, w1, lsl #5
0x0005e55c: f3 03 00 aa              mov      x19, x0
0x0005e560: e8 03 1f aa              mov      x8, xzr
0x0005e564: 29 7d 7f 93              sbfiz    x9, x9, #1, #0x20
0x0005e568: 6a 16 40 f9              ldr      x10, [x19, #0x28]
0x0005e56c: 4a 01 09 8b              add      x10, x10, x9
0x0005e570: 4a 79 e8 78              ldrsh    w10, [x10, x8, lsl #1]
0x0005e574: 41 01 22 1e              scvtf    s1, w10
0x0005e578: 6a 0e 40 f9              ldr      x10, [x19, #0x18]
0x0005e57c: 21 08 20 1e              fmul     s1, s1, s0
0x0005e580: 41 79 28 bc              str      s1, [x10, x8, lsl #2]
0x0005e584: 08 05 00 91              add      x8, x8, #1
0x0005e588: 1f 81 07 f1              cmp      x8, #0x1e0
0x0005e58c: e1 fe ff 54              b.ne     #0x5e568
0x0005e590: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e594: a1 05 00 d0              adrp     x1, #0x114000
0x0005e598: 02 3c 80 52              mov      w2, #0x1e0
0x0005e59c: 1f 81 07 b9              str      wzr, [x8, #0x780]
0x0005e5a0: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e5a4: 1f 85 07 b9              str      wzr, [x8, #0x784]
0x0005e5a8: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e5ac: 1f 89 07 b9              str      wzr, [x8, #0x788]
0x0005e5b0: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e5b4: 1f 8d 07 b9              str      wzr, [x8, #0x78c]
0x0005e5b8: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e5bc: 1f 91 07 b9              str      wzr, [x8, #0x790]
0x0005e5c0: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e5c4: 1f 95 07 b9              str      wzr, [x8, #0x794]
0x0005e5c8: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e5cc: 1f 99 07 b9              str      wzr, [x8, #0x798]
0x0005e5d0: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e5d4: 1f 9d 07 b9              str      wzr, [x8, #0x79c]
0x0005e5d8: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e5dc: 1f a1 07 b9              str      wzr, [x8, #0x7a0]
0x0005e5e0: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e5e4: 1f a5 07 b9              str      wzr, [x8, #0x7a4]
0x0005e5e8: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e5ec: 1f a9 07 b9              str      wzr, [x8, #0x7a8]
0x0005e5f0: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e5f4: 1f ad 07 b9              str      wzr, [x8, #0x7ac]
0x0005e5f8: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e5fc: 1f b1 07 b9              str      wzr, [x8, #0x7b0]
0x0005e600: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e604: 1f b5 07 b9              str      wzr, [x8, #0x7b4]
0x0005e608: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e60c: 1f b9 07 b9              str      wzr, [x8, #0x7b8]
0x0005e610: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e614: 1f bd 07 b9              str      wzr, [x8, #0x7bc]
0x0005e618: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e61c: 1f c1 07 b9              str      wzr, [x8, #0x7c0]
0x0005e620: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e624: 1f c5 07 b9              str      wzr, [x8, #0x7c4]
0x0005e628: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e62c: 1f c9 07 b9              str      wzr, [x8, #0x7c8]
0x0005e630: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e634: 1f cd 07 b9              str      wzr, [x8, #0x7cc]
0x0005e638: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e63c: 1f d1 07 b9              str      wzr, [x8, #0x7d0]
0x0005e640: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e644: 1f d5 07 b9              str      wzr, [x8, #0x7d4]
0x0005e648: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e64c: 1f d9 07 b9              str      wzr, [x8, #0x7d8]
0x0005e650: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e654: 1f dd 07 b9              str      wzr, [x8, #0x7dc]
0x0005e658: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e65c: 1f e1 07 b9              str      wzr, [x8, #0x7e0]
0x0005e660: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e664: 1f e5 07 b9              str      wzr, [x8, #0x7e4]
0x0005e668: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e66c: 1f e9 07 b9              str      wzr, [x8, #0x7e8]
0x0005e670: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e674: 1f ed 07 b9              str      wzr, [x8, #0x7ec]
0x0005e678: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e67c: 1f f1 07 b9              str      wzr, [x8, #0x7f0]
0x0005e680: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e684: 1f f5 07 b9              str      wzr, [x8, #0x7f4]
0x0005e688: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e68c: 1f f9 07 b9              str      wzr, [x8, #0x7f8]
0x0005e690: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x0005e694: 1f fd 07 b9              str      wzr, [x8, #0x7fc]
0x0005e698: 60 0e 40 f9              ldr      x0, [x19, #0x18]
0x0005e69c: 21 a8 42 f9              ldr      x1, [x1, #0x550]           ; -> udk_hann_window@got
0x0005e6a0: 82 cd 02 94              bl       #0x111ca8
0x0005e6a4: 68 1a 40 f9              ldr      x8, [x19, #0x30]
0x0005e6a8: 60 0e 40 f9              ldr      x0, [x19, #0x18]
0x0005e6ac: 01 c9 34 8b              add      x1, x8, w20, sxtw #2
0x0005e6b0: 84 cd 02 94              bl       #0x111cc0                  ; -> realfft@plt
0x0005e6b4: e0 03 1f 2a              mov      w0, wzr
0x0005e6b8: f4 4f 41 a9              ldp      x20, x19, [sp, #0x10]
0x0005e6bc: fd 7b c2 a8              ldp      x29, x30, [sp], #0x20
0x0005e6c0: bf 23 03 d5              autiasp  
0x0005e6c4: c0 03 5f d6              ret      
