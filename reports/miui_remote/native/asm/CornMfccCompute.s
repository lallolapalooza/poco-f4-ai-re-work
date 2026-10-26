; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : CornMfccCompute (0x33884)
; start   : 0x33884
; size    : 0x14c
; bytes   : 0x14c   instructions: 83
; generated: 2026-10-23T23:30:06

0x00033884: 3f 40 01 71              cmp      w1, #0x50
0x00033888: 6d 00 00 54              b.le     #0x33894
0x0003388c: 60 0b 80 12              mov      w0, #-0x5c
0x00033890: c0 03 5f d6              ret      
0x00033894: 48 08 40 b9              ldr      w8, [x2, #8]
0x00033898: 41 1c 00 b9              str      w1, [x2, #0x1c]
0x0003389c: 1f 05 00 71              cmp      w8, #1
0x000338a0: 4b 03 00 54              b.lt     #0x33908
0x000338a4: 4b 00 40 f9              ldr      x11, [x2]
0x000338a8: e9 03 1f aa              mov      x9, xzr
0x000338ac: ea 03 1f aa              mov      x10, xzr
0x000338b0: 2c 7c 40 93              sxtw     x12, w1
0x000338b4: ed 03 01 2a              mov      w13, w1
0x000338b8: 3f 04 00 71              cmp      w1, #1
0x000338bc: ab 01 00 54              b.lt     #0x338f0
0x000338c0: 4e 18 40 f9              ldr      x14, [x2, #0x30]
0x000338c4: e0 03 27 1e              fmov     s0, wzr
0x000338c8: ef 03 0d aa              mov      x15, x13
0x000338cc: f0 03 00 aa              mov      x16, x0
0x000338d0: ce 09 09 8b              add      x14, x14, x9, lsl #2
0x000338d4: c1 45 40 bc              ldr      s1, [x14], #4
0x000338d8: 02 46 40 bc              ldr      s2, [x16], #4
0x000338dc: ef 05 00 d1              sub      x15, x15, #1
0x000338e0: 21 08 22 1e              fmul     s1, s1, s2
0x000338e4: 00 28 21 1e              fadd     s0, s0, s1
0x000338e8: 6f ff ff b5              cbnz     x15, #0x338d4
0x000338ec: 02 00 00 14              b        #0x338f4                   ; -> CornMfccCompute+0x70
0x000338f0: e0 03 27 1e              fmov     s0, wzr
0x000338f4: 60 79 2a bc              str      s0, [x11, x10, lsl #2]
0x000338f8: 4a 05 00 91              add      x10, x10, #1
0x000338fc: 5f 01 08 eb              cmp      x10, x8
0x00033900: 29 01 0c 8b              add      x9, x9, x12
0x00033904: a1 fd ff 54              b.ne     #0x338b8
0x00033908: 40 20 40 bd              ldr      s0, [x2, #0x20]
0x0003390c: e0 03 1f 2a              mov      w0, wzr
0x00033910: 08 20 20 1e              fcmp     s0, #0.0
0x00033914: e0 fb ff 54              b.eq     #0x33890
0x00033918: 1f 05 00 71              cmp      w8, #1
0x0003391c: ab fb ff 54              b.lt     #0x33890
0x00033920: 08 7d 40 93              sxtw     x8, w8
0x00033924: 49 14 40 f9              ldr      x9, [x2, #0x28]
0x00033928: 4a 00 40 f9              ldr      x10, [x2]
0x0003392c: 1f 05 00 f1              cmp      x8, #1
0x00033930: 0c c5 9f 9a              csinc    x12, x8, xzr, gt
0x00033934: 9f 1d 00 f1              cmp      x12, #7
0x00033938: 89 01 00 54              b.ls     #0x33968
0x0003393c: 8b ed 7d 92              and      x11, x12, #0x7ffffffffffffff8
0x00033940: 6b 01 00 b4              cbz      x11, #0x3396c
0x00033944: 1f 05 00 f1              cmp      x8, #1
0x00033948: 0d c5 9f 9a              csinc    x13, x8, xzr, gt
0x0003394c: ad f5 7e d3              lsl      x13, x13, #2
0x00033950: 2e 01 0d 8b              add      x14, x9, x13
0x00033954: 5f 01 0e eb              cmp      x10, x14
0x00033958: e2 01 00 54              b.hs     #0x33994
0x0003395c: 4d 01 0d 8b              add      x13, x10, x13
0x00033960: 3f 01 0d eb              cmp      x9, x13
0x00033964: 82 01 00 54              b.hs     #0x33994
0x00033968: eb 03 1f aa              mov      x11, xzr
0x0003396c: 6c f5 7e d3              lsl      x12, x11, #2
0x00033970: 20 69 6c bc              ldr      s0, [x9, x12]
0x00033974: 41 69 6c bc              ldr      s1, [x10, x12]
0x00033978: 6b 05 00 91              add      x11, x11, #1
0x0003397c: 7f 01 08 eb              cmp      x11, x8
0x00033980: 00 08 21 1e              fmul     s0, s0, s1
0x00033984: 40 69 2c bc              str      s0, [x10, x12]
0x00033988: 2b ff ff 54              b.lt     #0x3396c
0x0003398c: e0 03 1f 2a              mov      w0, wzr
0x00033990: c0 03 5f d6              ret      
0x00033994: 2d 41 00 91              add      x13, x9, #0x10
0x00033998: 4e 41 00 91              add      x14, x10, #0x10
0x0003399c: ef 03 0b aa              mov      x15, x11
0x000339a0: a0 85 7f ad              ldp      q0, q1, [x13, #-0x10]
0x000339a4: c2 8d 7f ad              ldp      q2, q3, [x14, #-0x10]
0x000339a8: ad 81 00 91              add      x13, x13, #0x20
0x000339ac: ef 21 00 d1              sub      x15, x15, #8
0x000339b0: 00 dc 22 6e              fmul     v0.4s, v0.4s, v2.4s
0x000339b4: 21 dc 23 6e              fmul     v1.4s, v1.4s, v3.4s
0x000339b8: c0 85 3f ad              stp      q0, q1, [x14, #-0x10]
0x000339bc: ce 81 00 91              add      x14, x14, #0x20
0x000339c0: 0f ff ff b5              cbnz     x15, #0x339a0
0x000339c4: 9f 01 0b eb              cmp      x12, x11
0x000339c8: 21 fd ff 54              b.ne     #0x3396c
0x000339cc: f0 ff ff 17              b        #0x3398c                   ; -> CornMfccCompute+0x108
