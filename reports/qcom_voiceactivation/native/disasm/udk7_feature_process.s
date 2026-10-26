; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libqvasml.so
; target  : address 0x5e32c
; start   : 0x5e32c
; size    : 0x214
; bytes   : 0x214   instructions: 133
; generated: 2026-10-23T23:25:21

0x0005e32c: 3f 23 03 d5              paciasp  
0x0005e330: ff c3 01 d1              sub      sp, sp, #0x70
0x0005e334: fd 7b 01 a9              stp      x29, x30, [sp, #0x10]
0x0005e338: fc 6f 02 a9              stp      x28, x27, [sp, #0x20]
0x0005e33c: fa 67 03 a9              stp      x26, x25, [sp, #0x30]
0x0005e340: f8 5f 04 a9              stp      x24, x23, [sp, #0x40]
0x0005e344: f6 57 05 a9              stp      x22, x21, [sp, #0x50]
0x0005e348: f4 4f 06 a9              stp      x20, x19, [sp, #0x60]
0x0005e34c: fd 43 00 91              add      x29, sp, #0x10
0x0005e350: 15 00 40 b9              ldr      w21, [x0]
0x0005e354: 7f 80 02 71              cmp      w3, #0xa0
0x0005e358: 08 14 80 52              mov      w8, #0xa0
0x0005e35c: 78 30 88 1a              csel     w24, w3, w8, lo
0x0005e360: bf 06 00 71              cmp      w21, #1
0x0005e364: 8b 0d 00 54              b.lt     #0x5e514
0x0005e368: f7 03 02 2a              mov      w23, w2
0x0005e36c: f3 03 00 aa              mov      x19, x0
0x0005e370: fa 03 01 aa              mov      x26, x1
0x0005e374: 00 30 00 91              add      x0, x0, #0xc
0x0005e378: a2 f6 7e d3              lsl      x2, x21, #2
0x0005e37c: e1 03 1f 2a              mov      w1, wzr
0x0005e380: 26 ce 02 94              bl       #0x111c18
0x0005e384: a8 da 77 d3              lsl      x8, x21, #9
0x0005e388: f4 03 1f aa              mov      x20, xzr
0x0005e38c: 19 15 15 cb              sub      x25, x8, x21, lsl #5
0x0005e390: 96 00 80 52              mov      w22, #4
0x0005e394: 05 00 00 14              b        #0x5e3a8                   ; -> udk7_feature_process+0x7c
0x0005e398: 94 82 07 91              add      x20, x20, #0x1e0
0x0005e39c: d6 12 00 91              add      x22, x22, #4
0x0005e3a0: 3f 03 14 eb              cmp      x25, x20
0x0005e3a4: e0 01 00 54              b.eq     #0x5e3e0
0x0005e3a8: 98 ff ff 34              cbz      w24, #0x5e398
0x0005e3ac: 68 6a 76 b8              ldr      w8, [x19, x22]
0x0005e3b0: 1f 81 07 71              cmp      w8, #0x1e0
0x0005e3b4: 21 ff ff 54              b.ne     #0x5e398
0x0005e3b8: 68 16 40 f9              ldr      x8, [x19, #0x28]
0x0005e3bc: 89 6a 7b 92              and      x9, x20, #0xffffffe0
0x0005e3c0: 02 50 80 52              mov      w2, #0x280
0x0005e3c4: 00 05 09 8b              add      x0, x8, x9, lsl #1
0x0005e3c8: 01 00 05 91              add      x1, x0, #0x140
0x0005e3cc: 19 ce 02 94              bl       #0x111c30                  ; -> memmove@plt
0x0005e3d0: 68 6a 76 b8              ldr      w8, [x19, x22]
0x0005e3d4: 08 81 02 51              sub      w8, w8, #0xa0
0x0005e3d8: 68 6a 36 b8              str      w8, [x19, x22]
0x0005e3dc: ef ff ff 17              b        #0x5e398                   ; -> udk7_feature_process+0x6c
0x0005e3e0: bf 06 00 71              cmp      w21, #1
0x0005e3e4: 8b 09 00 54              b.lt     #0x5e514
0x0005e3e8: f5 6b 00 a9              stp      x21, x26, [sp]
0x0005e3ec: fa 03 1f 2a              mov      w26, wzr
0x0005e3f0: fb 03 1f aa              mov      x27, xzr
0x0005e3f4: f6 03 1f 2a              mov      w22, wzr
0x0005e3f8: 1c 7b 1f 53              lsl      w28, w24, #1
0x0005e3fc: f4 03 17 2a              mov      w20, w23
0x0005e400: 77 32 00 91              add      x23, x19, #0xc
0x0005e404: eb 07 40 f9              ldr      x11, [sp, #8]
0x0005e408: 6a 6b 7b 92              and      x10, x27, #0xffffffe0
0x0005e40c: e8 82 9f b8              ldursw   x8, [x23, #-8]
0x0005e410: 69 16 40 f9              ldr      x9, [x19, #0x28]
0x0005e414: 61 45 3a 8b              add      x1, x11, w26, uxtw #1
0x0005e418: 0b 3c 80 52              mov      w11, #0x1e0
0x0005e41c: 6b 01 08 cb              sub      x11, x11, x8
0x0005e420: 29 05 0a 8b              add      x9, x9, x10, lsl #1
0x0005e424: 6a f9 7f d3              lsl      x10, x11, #1
0x0005e428: 1f 03 0b 6b              cmp      w24, w11
0x0005e42c: 20 05 08 8b              add      x0, x9, x8, lsl #1
0x0005e430: 82 b3 8a 9a              csel     x2, x28, x10, lt
0x0005e434: 48 00 80 52              mov      w8, #2
0x0005e438: 15 b3 8b 1a              csel     w21, w24, w11, lt
0x0005e43c: 16 b1 96 1a              csel     w22, w8, w22, lt
0x0005e440: 02 ce 02 94              bl       #0x111c48
0x0005e444: e8 82 5f b8              ldur     w8, [x23, #-8]
0x0005e448: f5 02 00 b9              str      w21, [x23]
0x0005e44c: 7b 83 07 91              add      x27, x27, #0x1e0
0x0005e450: 5a 03 14 0b              add      w26, w26, w20
0x0005e454: 3f 03 1b eb              cmp      x25, x27
0x0005e458: 08 01 15 0b              add      w8, w8, w21
0x0005e45c: e8 82 1f b8              stur     w8, [x23, #-8]
0x0005e460: f7 12 00 91              add      x23, x23, #4
0x0005e464: 01 fd ff 54              b.ne     #0x5e404
0x0005e468: 96 05 00 35              cbnz     w22, #0x5e518
0x0005e46c: fc 03 40 f9              ldr      x28, [sp]
0x0005e470: 9f 07 00 71              cmp      w28, #1
0x0005e474: 2b 05 00 54              b.lt     #0x5e518
0x0005e478: b6 05 00 d0              adrp     x22, #0x114000
0x0005e47c: b8 05 00 d0              adrp     x24, #0x114000
0x0005e480: b9 05 00 d0              adrp     x25, #0x114000
0x0005e484: ba 05 00 d0              adrp     x26, #0x114000
0x0005e488: f5 03 1f 2a              mov      w21, wzr
0x0005e48c: f4 03 1f aa              mov      x20, xzr
0x0005e490: d6 9a 42 f9              ldr      x22, [x22, #0x530]         ; -> udk_mel_filter_start_80@got
0x0005e494: 18 9f 42 f9              ldr      x24, [x24, #0x538]         ; -> udk_mel_filter_offset_80@got
0x0005e498: 39 a3 42 f9              ldr      x25, [x25, #0x540]         ; -> udk_mel_filter_80@got
0x0005e49c: 5a a7 42 f9              ldr      x26, [x26, #0x548]         ; -> udk_mel_filter_len_80@got
0x0005e4a0: e0 03 13 aa              mov      x0, x19
0x0005e4a4: e1 03 14 2a              mov      w1, w20
0x0005e4a8: a8 6e 40 92              and      x8, x21, #0xfffffff
0x0005e4ac: 17 e5 7a d3              lsl      x23, x8, #6
0x0005e4b0: ec cd 02 94              bl       #0x111c60                  ; -> udk7_feature_spectrum@plt
0x0005e4b4: 68 1a 40 f9              ldr      x8, [x19, #0x30]
0x0005e4b8: 89 5a 17 53              lsl      w9, w20, #9
0x0005e4bc: 60 12 40 f9              ldr      x0, [x19, #0x20]
0x0005e4c0: 02 20 80 52              mov      w2, #0x100
0x0005e4c4: 01 49 29 8b              add      x1, x8, w9, uxtw #2
0x0005e4c8: ec cd 02 94              bl       #0x111c78
0x0005e4cc: fb 03 1f aa              mov      x27, xzr
0x0005e4d0: c8 6a bb b8              ldrsw    x8, [x22, x27]
0x0005e4d4: 09 6b bb b8              ldrsw    x9, [x24, x27]
0x0005e4d8: 6a 12 40 f9              ldr      x10, [x19, #0x20]
0x0005e4dc: 42 6b 7b b8              ldr      w2, [x26, x27]
0x0005e4e0: 40 09 08 8b              add      x0, x10, x8, lsl #2
0x0005e4e4: 21 0b 09 8b              add      x1, x25, x9, lsl #2
0x0005e4e8: ea cd 02 94              bl       #0x111c90                  ; -> udk7_q_vector_dot_f@plt
0x0005e4ec: 68 1e 40 f9              ldr      x8, [x19, #0x38]
0x0005e4f0: 08 01 17 8b              add      x8, x8, x23
0x0005e4f4: 00 69 3b bc              str      s0, [x8, x27]
0x0005e4f8: 7b 13 00 91              add      x27, x27, #4
0x0005e4fc: 7f 03 05 f1              cmp      x27, #0x140
0x0005e500: 81 fe ff 54              b.ne     #0x5e4d0
0x0005e504: 94 06 00 91              add      x20, x20, #1
0x0005e508: b5 16 00 11              add      w21, w21, #5
0x0005e50c: 9f 02 1c eb              cmp      x20, x28
0x0005e510: 81 fc ff 54              b.ne     #0x5e4a0
0x0005e514: f6 03 1f 2a              mov      w22, wzr
0x0005e518: e0 03 16 2a              mov      w0, w22
0x0005e51c: f4 4f 46 a9              ldp      x20, x19, [sp, #0x60]
0x0005e520: f6 57 45 a9              ldp      x22, x21, [sp, #0x50]
0x0005e524: f8 5f 44 a9              ldp      x24, x23, [sp, #0x40]
0x0005e528: fa 67 43 a9              ldp      x26, x25, [sp, #0x30]
0x0005e52c: fc 6f 42 a9              ldp      x28, x27, [sp, #0x20]
0x0005e530: fd 7b 41 a9              ldp      x29, x30, [sp, #0x10]
0x0005e534: ff c3 01 91              add      sp, sp, #0x70
0x0005e538: bf 23 03 d5              autiasp  
0x0005e53c: c0 03 5f d6              ret      
