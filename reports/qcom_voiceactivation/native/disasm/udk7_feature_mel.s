; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libqvasml.so
; target  : address 0x5e6c8
; start   : 0x5e6c8
; size    : 0xc0
; bytes   : 0xc0   instructions: 48
; generated: 2026-10-23T23:25:22

0x0005e6c8: 3f 23 03 d5              paciasp  
0x0005e6cc: fd 7b bb a9              stp      x29, x30, [sp, #-0x50]!
0x0005e6d0: f9 0b 00 f9              str      x25, [sp, #0x10]
0x0005e6d4: f8 5f 02 a9              stp      x24, x23, [sp, #0x20]
0x0005e6d8: f6 57 03 a9              stp      x22, x21, [sp, #0x30]
0x0005e6dc: f4 4f 04 a9              stp      x20, x19, [sp, #0x40]
0x0005e6e0: fd 03 00 91              mov      x29, sp
0x0005e6e4: 09 18 40 f9              ldr      x9, [x0, #0x30]
0x0005e6e8: 28 58 17 53              lsl      w8, w1, #9
0x0005e6ec: f4 03 01 2a              mov      w20, w1
0x0005e6f0: f3 03 00 aa              mov      x19, x0
0x0005e6f4: 00 10 40 f9              ldr      x0, [x0, #0x20]
0x0005e6f8: 02 20 80 52              mov      w2, #0x100
0x0005e6fc: 21 c9 28 8b              add      x1, x9, w8, sxtw #2
0x0005e700: 5e cd 02 94              bl       #0x111c78
0x0005e704: 88 0a 14 0b              add      w8, w20, w20, lsl #2
0x0005e708: b4 05 00 d0              adrp     x20, #0x114000
0x0005e70c: b7 05 00 d0              adrp     x23, #0x114000
0x0005e710: b8 05 00 d0              adrp     x24, #0x114000
0x0005e714: b9 05 00 d0              adrp     x25, #0x114000
0x0005e718: 08 6d 1c 53              lsl      w8, w8, #4
0x0005e71c: f5 03 1f aa              mov      x21, xzr
0x0005e720: 94 9a 42 f9              ldr      x20, [x20, #0x530]         ; -> udk_mel_filter_start_80@got
0x0005e724: 16 7d 7e 93              sbfiz    x22, x8, #2, #0x20
0x0005e728: f7 9e 42 f9              ldr      x23, [x23, #0x538]         ; -> udk_mel_filter_offset_80@got
0x0005e72c: 18 a3 42 f9              ldr      x24, [x24, #0x540]         ; -> udk_mel_filter_80@got
0x0005e730: 39 a7 42 f9              ldr      x25, [x25, #0x548]         ; -> udk_mel_filter_len_80@got
0x0005e734: 88 6a b5 b8              ldrsw    x8, [x20, x21]
0x0005e738: e9 6a b5 b8              ldrsw    x9, [x23, x21]
0x0005e73c: 6a 12 40 f9              ldr      x10, [x19, #0x20]
0x0005e740: 22 6b 75 b8              ldr      w2, [x25, x21]
0x0005e744: 40 09 08 8b              add      x0, x10, x8, lsl #2
0x0005e748: 01 0b 09 8b              add      x1, x24, x9, lsl #2
0x0005e74c: 51 cd 02 94              bl       #0x111c90                  ; -> udk7_q_vector_dot_f@plt
0x0005e750: 68 1e 40 f9              ldr      x8, [x19, #0x38]
0x0005e754: 08 01 16 8b              add      x8, x8, x22
0x0005e758: 00 69 35 bc              str      s0, [x8, x21]
0x0005e75c: b5 12 00 91              add      x21, x21, #4
0x0005e760: bf 02 05 f1              cmp      x21, #0x140
0x0005e764: 81 fe ff 54              b.ne     #0x5e734
0x0005e768: e0 03 1f 2a              mov      w0, wzr
0x0005e76c: f4 4f 44 a9              ldp      x20, x19, [sp, #0x40]
0x0005e770: f6 57 43 a9              ldp      x22, x21, [sp, #0x30]
0x0005e774: f8 5f 42 a9              ldp      x24, x23, [sp, #0x20]
0x0005e778: f9 0b 40 f9              ldr      x25, [sp, #0x10]
0x0005e77c: fd 7b c5 a8              ldp      x29, x30, [sp], #0x50
0x0005e780: bf 23 03 d5              autiasp  
0x0005e784: c0 03 5f d6              ret      
