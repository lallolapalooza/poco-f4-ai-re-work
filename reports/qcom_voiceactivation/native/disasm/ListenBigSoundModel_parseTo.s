; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libqvasml.so
; target  : address 0x6408c
; start   : 0x6408c
; size    : 0xd4
; bytes   : 0xd4   instructions: 53
; generated: 2026-10-23T23:24:18

0x0006408c: 3f 23 03 d5              paciasp  
0x00064090: fd 7b bf a9              stp      x29, x30, [sp, #-0x10]!
0x00064094: fd 03 00 91              mov      x29, sp
0x00064098: 83 02 00 b4              cbz      x3, #0x640e8
0x0006409c: 08 04 40 f9              ldr      x8, [x0, #8]
0x000640a0: 09 11 40 b9              ldr      w9, [x8, #0x10]
0x000640a4: 49 01 00 34              cbz      w9, #0x640cc
0x000640a8: 0a d9 40 f9              ldr      x10, [x8, #0x1b0]
0x000640ac: 2b 3c 00 12              and      w11, w1, #0xffff
0x000640b0: 4a 21 00 91              add      x10, x10, #8
0x000640b4: 4c 05 40 b9              ldr      w12, [x10, #4]
0x000640b8: 9f 01 0b 6b              cmp      w12, w11
0x000640bc: 80 02 00 54              b.eq     #0x6410c
0x000640c0: 4a 41 00 91              add      x10, x10, #0x10
0x000640c4: 29 05 00 f1              subs     x9, x9, #1
0x000640c8: 61 ff ff 54              b.ne     #0x640b4
0x000640cc: 1f 20 03 d5              nop      
0x000640d0: 61 0f d7 70              adr      x1, #0x122bf
0x000640d4: 62 fd ff f0              adrp     x2, #0x13000
0x000640d8: 42 1c 34 91              add      x2, x2, #0xd07
0x000640dc: 20 00 80 52              mov      w0, #1
0x000640e0: 83 22 80 52              mov      w3, #0x114
0x000640e4: 07 00 00 14              b        #0x64100                   ; -> _ZN19ListenBigSoundModel7parseToEtjPh+0x74
0x000640e8: 1f 20 03 d5              nop      
0x000640ec: 81 0e d7 70              adr      x1, #0x122bf
0x000640f0: 62 fd ff f0              adrp     x2, #0x13000
0x000640f4: 42 1c 34 91              add      x2, x2, #0xd07
0x000640f8: 40 00 80 52              mov      w0, #2
0x000640fc: e3 20 80 52              mov      w3, #0x107
0x00064100: fd 7b c1 a8              ldp      x29, x30, [sp], #0x10
0x00064104: bf 23 03 d5              autiasp  
0x00064108: 32 b8 02 14              b        #0x1121d0                  ; -> _ZN3sml15sml_debug_printE18listen_status_enumPKcS2_i@plt
0x0006410c: 49 01 40 b9              ldr      w9, [x10]
0x00064110: 3f 01 02 6b              cmp      w9, w2
0x00064114: 81 01 00 54              b.ne     #0x64144
0x00064118: 49 c1 5f b8              ldur     w9, [x10, #-4]
0x0006411c: e1 03 02 2a              mov      w1, w2
0x00064120: 08 dd 40 f9              ldr      x8, [x8, #0x1b8]
0x00064124: e0 03 03 aa              mov      x0, x3
0x00064128: e3 03 01 aa              mov      x3, x1
0x0006412c: 02 01 09 8b              add      x2, x8, x9
0x00064130: 3d b6 02 94              bl       #0x111a24
0x00064134: e0 03 1f 2a              mov      w0, wzr
0x00064138: fd 7b c1 a8              ldp      x29, x30, [sp], #0x10
0x0006413c: bf 23 03 d5              autiasp  
0x00064140: c0 03 5f d6              ret      
0x00064144: 1f 20 03 d5              nop      
0x00064148: a1 0b d7 70              adr      x1, #0x122bf
0x0006414c: 62 fd ff f0              adrp     x2, #0x13000
0x00064150: 42 1c 34 91              add      x2, x2, #0xd07
0x00064154: 40 00 80 52              mov      w0, #2
0x00064158: a3 21 80 52              mov      w3, #0x10d
0x0006415c: e9 ff ff 17              b        #0x64100                   ; -> _ZN19ListenBigSoundModel7parseToEtjPh+0x74
