; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : CornSpectProcessPack (0x33040)
; start   : 0x33040
; size    : 0x844
; bytes   : 0x844   instructions: 529
; generated: 2026-10-23T23:30:05

0x00033040: e9 23 ba 6d              stp      d9, d8, [sp, #-0x60]!
0x00033044: f9 0b 00 f9              str      x25, [sp, #0x10]
0x00033048: f8 5f 02 a9              stp      x24, x23, [sp, #0x20]
0x0003304c: f6 57 03 a9              stp      x22, x21, [sp, #0x30]
0x00033050: f4 4f 04 a9              stp      x20, x19, [sp, #0x40]
0x00033054: fd 7b 05 a9              stp      x29, x30, [sp, #0x50]
0x00033058: fd 43 01 91              add      x29, sp, #0x50
0x0003305c: f3 03 02 aa              mov      x19, x2
0x00033060: 68 5e 47 29              ldp      w8, w23, [x19, #0x38]
0x00033064: 69 f4 43 b9              ldr      w9, [x3, #0x3f4]
0x00033068: f4 03 01 2a              mov      w20, w1
0x0003306c: f5 03 00 aa              mov      x21, x0
0x00033070: 08 7d 40 93              sxtw     x8, w8
0x00033074: c9 01 00 34              cbz      w9, #0x330ac
0x00033078: 69 9a 80 b9              ldrsw    x9, [x19, #0x98]
0x0003307c: 2a 01 14 0b              add      w10, w9, w20
0x00033080: 5f 01 17 6b              cmp      w10, w23
0x00033084: 8d 04 00 54              b.le     #0x33114
0x00033088: 6a 7e 40 b9              ldr      w10, [x19, #0x7c]
0x0003308c: ff 02 09 6b              cmp      w23, w9
0x00033090: cd 07 00 54              b.le     #0x33188
0x00033094: aa 07 00 35              cbnz     w10, #0x33188
0x00033098: 68 4a 40 f9              ldr      x8, [x19, #0x90]
0x0003309c: ea 02 09 4b              sub      w10, w23, w9
0x000330a0: 96 02 0a 4b              sub      w22, w20, w10
0x000330a4: 00 05 09 8b              add      x0, x8, x9, lsl #1
0x000330a8: 2e 00 00 14              b        #0x33160                   ; -> CornSpectProcessPack+0x120
0x000330ac: 69 9a 40 b9              ldr      w9, [x19, #0x98]
0x000330b0: ff 02 00 71              cmp      w23, #0
0x000330b4: ea a6 97 1a              cinc     w10, w23, lt
0x000330b8: 1f 01 00 71              cmp      w8, #0
0x000330bc: 0b a5 88 1a              cinc     w11, w8, lt
0x000330c0: a9 01 00 35              cbnz     w9, #0x330f4
0x000330c4: 49 7d 01 13              asr      w9, w10, #1
0x000330c8: 29 05 8b 4b              sub      w9, w9, w11, asr #1
0x000330cc: 3f 05 00 71              cmp      w9, #1
0x000330d0: 0b 01 00 54              b.lt     #0x330f0
0x000330d4: 6a 4a 40 f9              ldr      x10, [x19, #0x90]
0x000330d8: eb 03 09 2a              mov      w11, w9
0x000330dc: ec 03 15 aa              mov      x12, x21
0x000330e0: 8d 25 40 78              ldrh     w13, [x12], #2
0x000330e4: 6b 05 00 d1              sub      x11, x11, #1
0x000330e8: 4d d9 2b 78              strh     w13, [x10, w11, sxtw #1]
0x000330ec: ab ff ff b5              cbnz     x11, #0x330e0
0x000330f0: 69 9a 00 b9              str      w9, [x19, #0x98]
0x000330f4: 2a 01 14 0b              add      w10, w9, w20
0x000330f8: 5f 01 17 6b              cmp      w10, w23
0x000330fc: 4a 02 00 54              b.ge     #0x33144
0x00033100: 68 4a 40 f9              ldr      x8, [x19, #0x90]
0x00033104: 82 7e 7f 93              sbfiz    x2, x20, #1, #0x20
0x00033108: e1 03 15 aa              mov      x1, x21
0x0003310c: 00 c5 29 8b              add      x0, x8, w9, sxtw #1
0x00033110: 05 00 00 14              b        #0x33124                   ; -> CornSpectProcessPack+0xe4
0x00033114: 68 4a 40 f9              ldr      x8, [x19, #0x90]
0x00033118: 82 7e 7f 93              sbfiz    x2, x20, #1, #0x20
0x0003311c: e1 03 15 aa              mov      x1, x21
0x00033120: 00 05 09 8b              add      x0, x8, x9, lsl #1
0x00033124: 57 6a ff 97              bl       #0xda80                    ; -> memcpy@plt
0x00033128: 68 9a 40 b9              ldr      w8, [x19, #0x98]
0x0003312c: 08 01 14 0b              add      w8, w8, w20
0x00033130: 1f 01 17 6b              cmp      w8, w23
0x00033134: 68 9a 00 b9              str      w8, [x19, #0x98]
0x00033138: e0 07 00 54              b.eq     #0x33234
0x0003313c: f5 03 1f 2a              mov      w21, wzr
0x00033140: bb 01 00 14              b        #0x3382c                   ; -> CornSpectProcessPack+0x7ec
0x00033144: 6b 7e 40 b9              ldr      w11, [x19, #0x7c]
0x00033148: ea 02 09 6b              subs     w10, w23, w9
0x0003314c: 4d 02 00 54              b.le     #0x33194
0x00033150: 2b 02 00 35              cbnz     w11, #0x33194
0x00033154: 68 4a 40 f9              ldr      x8, [x19, #0x90]
0x00033158: 96 02 0a 4b              sub      w22, w20, w10
0x0003315c: 00 c5 29 8b              add      x0, x8, w9, sxtw #1
0x00033160: 54 7d 7f 93              sbfiz    x20, x10, #1, #0x20
0x00033164: e1 03 15 aa              mov      x1, x21
0x00033168: e2 03 14 aa              mov      x2, x20
0x0003316c: 45 6a ff 97              bl       #0xda80                    ; -> memcpy@plt
0x00033170: 60 3a 40 f9              ldr      x0, [x19, #0x70]
0x00033174: a1 02 14 8b              add      x1, x21, x20
0x00033178: c2 7e 7f 93              sbfiz    x2, x22, #1, #0x20
0x0003317c: 41 6a ff 97              bl       #0xda80                    ; -> memcpy@plt
0x00033180: 76 7e 00 b9              str      w22, [x19, #0x7c]
0x00033184: 2c 00 00 14              b        #0x33234                   ; -> CornSpectProcessPack+0x1f4
0x00033188: 60 4a 40 f9              ldr      x0, [x19, #0x90]
0x0003318c: 18 01 0a 4b              sub      w24, w8, w10
0x00033190: 03 00 00 14              b        #0x3319c                   ; -> CornSpectProcessPack+0x15c
0x00033194: 60 4a 40 f9              ldr      x0, [x19, #0x90]
0x00033198: 18 01 0b 4b              sub      w24, w8, w11
0x0003319c: f9 02 08 4b              sub      w25, w23, w8
0x000331a0: 36 7f 7f 93              sbfiz    x22, x25, #1, #0x20
0x000331a4: 01 04 08 8b              add      x1, x0, x8, lsl #1
0x000331a8: e2 03 16 aa              mov      x2, x22
0x000331ac: 6d 69 ff 97              bl       #0xd760                    ; -> memmove@plt
0x000331b0: 68 4a 40 f9              ldr      x8, [x19, #0x90]
0x000331b4: 69 7e 80 b9              ldrsw    x9, [x19, #0x7c]
0x000331b8: 61 3a 40 f9              ldr      x1, [x19, #0x70]
0x000331bc: 00 01 16 8b              add      x0, x8, x22
0x000331c0: 22 f9 7f d3              lsl      x2, x9, #1
0x000331c4: 2f 6a ff 97              bl       #0xda80                    ; -> memcpy@plt
0x000331c8: 68 7e 40 b9              ldr      w8, [x19, #0x7c]
0x000331cc: 69 4a 40 f9              ldr      x9, [x19, #0x90]
0x000331d0: 1f 03 14 6b              cmp      w24, w20
0x000331d4: 08 01 19 0b              add      w8, w8, w25
0x000331d8: 20 c5 28 8b              add      x0, x9, w8, sxtw #1
0x000331dc: 6d 01 00 54              b.le     #0x33208
0x000331e0: 82 7e 7f 93              sbfiz    x2, x20, #1, #0x20
0x000331e4: e1 03 15 aa              mov      x1, x21
0x000331e8: 26 6a ff 97              bl       #0xda80                    ; -> memcpy@plt
0x000331ec: 68 7e 40 b9              ldr      w8, [x19, #0x7c]
0x000331f0: 29 03 14 0b              add      w9, w25, w20
0x000331f4: f5 03 1f 2a              mov      w21, wzr
0x000331f8: 7f 7e 00 b9              str      wzr, [x19, #0x7c]
0x000331fc: 28 01 08 0b              add      w8, w9, w8
0x00033200: 68 9a 00 b9              str      w8, [x19, #0x98]
0x00033204: 8a 01 00 14              b        #0x3382c                   ; -> CornSpectProcessPack+0x7ec
0x00033208: 16 7f 7f 93              sbfiz    x22, x24, #1, #0x20
0x0003320c: e1 03 15 aa              mov      x1, x21
0x00033210: e2 03 16 aa              mov      x2, x22
0x00033214: 1b 6a ff 97              bl       #0xda80                    ; -> memcpy@plt
0x00033218: 60 3a 40 f9              ldr      x0, [x19, #0x70]
0x0003321c: 94 02 18 4b              sub      w20, w20, w24
0x00033220: a1 02 16 8b              add      x1, x21, x22
0x00033224: 82 7e 7f 93              sbfiz    x2, x20, #1, #0x20
0x00033228: 16 6a ff 97              bl       #0xda80                    ; -> memcpy@plt
0x0003322c: 74 7e 00 b9              str      w20, [x19, #0x7c]
0x00033230: 77 9a 00 b9              str      w23, [x19, #0x98]
0x00033234: 6b de 47 29              ldp      w11, w23, [x19, #0x3c]
0x00033238: 68 36 40 f9              ldr      x8, [x19, #0x68]
0x0003323c: 60 22 40 bd              ldr      s0, [x19, #0x20]
0x00033240: 6a 1a 40 f9              ldr      x10, [x19, #0x30]
0x00033244: 7f 05 00 71              cmp      w11, #1
0x00033248: f7 7e 40 93              sxtw     x23, w23
0x0003324c: 69 7d 40 93              sxtw     x9, w11
0x00033250: 0b 05 00 54              b.lt     #0x332f0
0x00033254: 6c 4a 40 f9              ldr      x12, [x19, #0x90]
0x00033258: 6d 26 40 b9              ldr      w13, [x19, #0x24]
0x0003325c: e1 03 27 1e              fmov     s1, wzr
0x00033260: ee 03 08 aa              mov      x14, x8
0x00033264: ef 03 0b aa              mov      x15, x11
0x00033268: 90 25 c0 78              ldrsh    w16, [x12], #2
0x0003326c: ef 05 00 d1              sub      x15, x15, #1
0x00033270: 02 02 22 1e              scvtf    s2, w16
0x00033274: c2 45 00 bc              str      s2, [x14], #4
0x00033278: 21 28 22 1e              fadd     s1, s1, s2
0x0003327c: 6f ff ff b5              cbnz     x15, #0x33268
0x00033280: 8d 03 00 34              cbz      w13, #0x332f0
0x00033284: 22 01 22 1e              scvtf    s2, w9
0x00033288: 7f 21 00 71              cmp      w11, #8
0x0003328c: 21 18 22 1e              fdiv     s1, s1, s2
0x00033290: 62 00 00 54              b.hs     #0x3329c
0x00033294: ec 03 1f aa              mov      x12, xzr
0x00033298: 0f 00 00 14              b        #0x332d4                   ; -> CornSpectProcessPack+0x294
0x0003329c: 2d 09 00 12              and      w13, w9, #7
0x000332a0: 6c 01 0d cb              sub      x12, x11, x13
0x000332a4: 8c 01 00 b4              cbz      x12, #0x332d4
0x000332a8: 22 04 04 4e              dup      v2.4s, v1.s[0]
0x000332ac: 0e 41 00 91              add      x14, x8, #0x10
0x000332b0: ef 03 0c aa              mov      x15, x12
0x000332b4: c3 91 7f ad              ldp      q3, q4, [x14, #-0x10]
0x000332b8: ef 21 00 d1              sub      x15, x15, #8
0x000332bc: 63 d4 a2 4e              fsub     v3.4s, v3.4s, v2.4s
0x000332c0: 84 d4 a2 4e              fsub     v4.4s, v4.4s, v2.4s
0x000332c4: c3 91 3f ad              stp      q3, q4, [x14, #-0x10]
0x000332c8: ce 81 00 91              add      x14, x14, #0x20
0x000332cc: 4f ff ff b5              cbnz     x15, #0x332b4
0x000332d0: 0d 01 00 34              cbz      w13, #0x332f0
0x000332d4: 6d 01 0c cb              sub      x13, x11, x12
0x000332d8: 0c 09 0c 8b              add      x12, x8, x12, lsl #2
0x000332dc: 82 01 40 bd              ldr      s2, [x12]
0x000332e0: ad 05 00 d1              sub      x13, x13, #1
0x000332e4: 42 38 21 1e              fsub     s2, s2, s1
0x000332e8: 82 45 00 bc              str      s2, [x12], #4
0x000332ec: 8d ff ff b5              cbnz     x13, #0x332dc
0x000332f0: 08 20 20 1e              fcmp     s0, #0.0
0x000332f4: 00 02 00 54              b.eq     #0x33334
0x000332f8: 7f 09 00 71              cmp      w11, #2
0x000332fc: 4b 01 00 54              b.lt     #0x33324
0x00033300: ec 03 09 aa              mov      x12, x9
0x00033304: 0d 09 0c 8b              add      x13, x8, x12, lsl #2
0x00033308: a1 09 7f 2d              ldp      s1, s2, [x13, #-8]
0x0003330c: 9f 09 00 f1              cmp      x12, #2
0x00033310: 8c 05 00 d1              sub      x12, x12, #1
0x00033314: 01 08 21 1e              fmul     s1, s0, s1
0x00033318: 41 38 21 1e              fsub     s1, s2, s1
0x0003331c: a1 c1 1f bc              stur     s1, [x13, #-4]
0x00033320: 2c ff ff 54              b.gt     #0x33304
0x00033324: 01 01 40 bd              ldr      s1, [x8]
0x00033328: 00 08 21 1e              fmul     s0, s0, s1
0x0003332c: 20 38 20 1e              fsub     s0, s1, s0
0x00033330: 00 01 00 bd              str      s0, [x8]
0x00033334: 7f 05 00 71              cmp      w11, #1
0x00033338: 0b 03 00 54              b.lt     #0x33398
0x0003333c: 7f 21 00 71              cmp      w11, #8
0x00033340: 63 01 00 54              b.lo     #0x3336c
0x00033344: 2d 09 00 12              and      w13, w9, #7
0x00033348: 6c 01 0d cb              sub      x12, x11, x13
0x0003334c: 2c 01 00 b4              cbz      x12, #0x33370
0x00033350: 6e f5 7e d3              lsl      x14, x11, #2
0x00033354: 4f 01 0e 8b              add      x15, x10, x14
0x00033358: 1f 01 0f eb              cmp      x8, x15
0x0003335c: 82 27 00 54              b.hs     #0x3384c
0x00033360: 0e 01 0e 8b              add      x14, x8, x14
0x00033364: 5f 01 0e eb              cmp      x10, x14
0x00033368: 22 27 00 54              b.hs     #0x3384c
0x0003336c: ec 03 1f aa              mov      x12, xzr
0x00033370: 8d f5 7e d3              lsl      x13, x12, #2
0x00033374: 6b 01 0c cb              sub      x11, x11, x12
0x00033378: 0c 01 0d 8b              add      x12, x8, x13
0x0003337c: 4a 01 0d 8b              add      x10, x10, x13
0x00033380: 40 45 40 bc              ldr      s0, [x10], #4
0x00033384: 81 01 40 bd              ldr      s1, [x12]
0x00033388: 6b 05 00 d1              sub      x11, x11, #1
0x0003338c: 00 08 21 1e              fmul     s0, s0, s1
0x00033390: 80 45 00 bc              str      s0, [x12], #4
0x00033394: 6b ff ff b5              cbnz     x11, #0x33380
0x00033398: 00 09 09 8b              add      x0, x8, x9, lsl #2
0x0003339c: e8 02 09 4b              sub      w8, w23, w9
0x000333a0: 02 7d 7e 93              sbfiz    x2, x8, #2, #0x20
0x000333a4: e1 03 1f 2a              mov      w1, wzr
0x000333a8: ce 69 ff 97              bl       #0xdae0                    ; -> memset@plt
0x000333ac: f6 03 13 aa              mov      x22, x19
0x000333b0: c8 0e 8a b8              ldrsw    x8, [x22, #0xa0]!
0x000333b4: ff 02 00 71              cmp      w23, #0
0x000333b8: f8 a6 97 1a              cinc     w24, w23, lt
0x000333bc: d4 82 5c f8              ldur     x20, [x22, #-0x38]
0x000333c0: d5 82 5e f8              ldur     x21, [x22, #-0x18]
0x000333c4: 1f 05 00 71              cmp      w8, #1
0x000333c8: 6b 01 00 54              b.lt     #0x333f4
0x000333cc: 89 12 00 91              add      x9, x20, #4
0x000333d0: ea 03 14 aa              mov      x10, x20
0x000333d4: eb 03 15 aa              mov      x11, x21
0x000333d8: ec 03 08 aa              mov      x12, x8
0x000333dc: 2d c1 5f b8              ldur     w13, [x9, #-4]
0x000333e0: 8c 05 00 d1              sub      x12, x12, #1
0x000333e4: 4d 45 00 b8              str      w13, [x10], #4
0x000333e8: 2d 85 40 b8              ldr      w13, [x9], #8
0x000333ec: 6d 45 00 b8              str      w13, [x11], #4
0x000333f0: 6c ff ff b5              cbnz     x12, #0x333dc
0x000333f4: 02 f5 7e d3              lsl      x2, x8, #2
0x000333f8: 80 02 02 8b              add      x0, x20, x2
0x000333fc: e1 03 15 aa              mov      x1, x21
0x00033400: a0 69 ff 97              bl       #0xda80                    ; -> memcpy@plt
0x00033404: 68 0a 54 29              ldp      w8, w2, [x19, #0xa0]
0x00033408: e0 03 14 aa              mov      x0, x20
0x0003340c: e3 03 16 aa              mov      x3, x22
0x00033410: 08 7d 40 93              sxtw     x8, w8
0x00033414: 81 0a 08 8b              add      x1, x20, x8, lsl #2
0x00033418: 00 04 00 94              bl       #0x34418
0x0003341c: 69 a6 40 b9              ldr      w9, [x19, #0xa4]
0x00033420: 3f 05 00 71              cmp      w9, #1
0x00033424: 8d 0a 00 54              b.le     #0x33574
0x00033428: 28 7d 01 53              lsr      w8, w9, #1
0x0003342c: ea 03 00 32              mov      w10, #1
0x00033430: 4d 21 c8 1a              lsl      w13, w10, w8
0x00033434: bf 09 00 71              cmp      w13, #2
0x00033438: 6b 04 00 54              b.lt     #0x334c4
0x0003343c: 6b 56 40 f9              ldr      x11, [x19, #0xa8]
0x00033440: ec 03 0d 2a              mov      w12, w13
0x00033444: ad 7d 7e 93              sbfiz    x13, x13, #2, #0x20
0x00033448: 8e 02 0d 8b              add      x14, x20, x13
0x0003344c: ce 11 00 91              add      x14, x14, #4
0x00033450: 6f 11 00 91              add      x15, x11, #4
0x00033454: f0 03 00 32              mov      w16, #1
0x00033458: 00 f6 7e d3              lsl      x0, x16, #2
0x0003345c: 72 69 60 b8              ldr      w18, [x11, x0]
0x00033460: 83 6a 60 b8              ldr      w3, [x20, x0]
0x00033464: 51 22 c8 1a              lsl      w17, w18, w8
0x00033468: 21 7e 7e 93              sbfiz    x1, x17, #2, #0x20
0x0003346c: 82 6a 61 b8              ldr      w2, [x20, x1]
0x00033470: 5f 0a 00 71              cmp      w18, #2
0x00033474: 82 6a 20 b8              str      w2, [x20, x0]
0x00033478: 83 6a 21 b8              str      w3, [x20, x1]
0x0003347c: cb 01 00 54              b.lt     #0x334b4
0x00033480: 52 06 00 51              sub      w18, w18, #1
0x00033484: e0 03 0e aa              mov      x0, x14
0x00033488: e1 03 0f aa              mov      x1, x15
0x0003348c: 22 44 40 b8              ldr      w2, [x1], #4
0x00033490: 04 00 40 b9              ldr      w4, [x0]
0x00033494: 52 06 00 51              sub      w18, w18, #1
0x00033498: 42 00 11 0b              add      w2, w2, w17
0x0003349c: 42 7c 7e 93              sbfiz    x2, x2, #2, #0x20
0x000334a0: 83 6a 62 b8              ldr      w3, [x20, x2]
0x000334a4: 03 00 00 b9              str      w3, [x0]
0x000334a8: 00 00 0d 8b              add      x0, x0, x13
0x000334ac: 84 6a 22 b8              str      w4, [x20, x2]
0x000334b0: f2 fe ff 35              cbnz     w18, #0x3348c
0x000334b4: 10 06 00 91              add      x16, x16, #1
0x000334b8: 1f 02 0c eb              cmp      x16, x12
0x000334bc: ce 11 00 91              add      x14, x14, #4
0x000334c0: c1 fc ff 54              b.ne     #0x33458
0x000334c4: c8 02 40 b9              ldr      w8, [x22]
0x000334c8: 29 7d 01 13              asr      w9, w9, #1
0x000334cc: 4d 21 c9 1a              lsl      w13, w10, w9
0x000334d0: bf 09 00 71              cmp      w13, #2
0x000334d4: 2b 05 00 54              b.lt     #0x33578
0x000334d8: 6b 56 40 f9              ldr      x11, [x19, #0xa8]
0x000334dc: 0e 7d 40 93              sxtw     x14, w8
0x000334e0: af 7d 40 93              sxtw     x15, w13
0x000334e4: ce 01 0f 8b              add      x14, x14, x15
0x000334e8: 8e 0a 0e 8b              add      x14, x20, x14, lsl #2
0x000334ec: 8a ca 28 8b              add      x10, x20, w8, sxtw #2
0x000334f0: ec 03 0d 2a              mov      w12, w13
0x000334f4: ad 7d 7e 93              sbfiz    x13, x13, #2, #0x20
0x000334f8: ce 11 00 91              add      x14, x14, #4
0x000334fc: 6f 11 00 91              add      x15, x11, #4
0x00033500: f0 03 00 32              mov      w16, #1
0x00033504: 00 f6 7e d3              lsl      x0, x16, #2
0x00033508: 72 69 60 b8              ldr      w18, [x11, x0]
0x0003350c: 43 69 60 b8              ldr      w3, [x10, x0]
0x00033510: 51 22 c9 1a              lsl      w17, w18, w9
0x00033514: 21 7e 7e 93              sbfiz    x1, x17, #2, #0x20
0x00033518: 42 69 61 b8              ldr      w2, [x10, x1]
0x0003351c: 5f 0a 00 71              cmp      w18, #2
0x00033520: 42 69 20 b8              str      w2, [x10, x0]
0x00033524: 43 69 21 b8              str      w3, [x10, x1]
0x00033528: cb 01 00 54              b.lt     #0x33560
0x0003352c: 52 06 00 51              sub      w18, w18, #1
0x00033530: e0 03 0e aa              mov      x0, x14
0x00033534: e1 03 0f aa              mov      x1, x15
0x00033538: 22 44 40 b8              ldr      w2, [x1], #4
0x0003353c: 04 00 40 b9              ldr      w4, [x0]
0x00033540: 52 06 00 51              sub      w18, w18, #1
0x00033544: 42 00 11 0b              add      w2, w2, w17
0x00033548: 42 7c 7e 93              sbfiz    x2, x2, #2, #0x20
0x0003354c: 43 69 62 b8              ldr      w3, [x10, x2]
0x00033550: 03 00 00 b9              str      w3, [x0]
0x00033554: 00 00 0d 8b              add      x0, x0, x13
0x00033558: 44 69 22 b8              str      w4, [x10, x2]
0x0003355c: f2 fe ff 35              cbnz     w18, #0x33538
0x00033560: 10 06 00 91              add      x16, x16, #1
0x00033564: 1f 02 0c eb              cmp      x16, x12
0x00033568: ce 11 00 91              add      x14, x14, #4
0x0003356c: c1 fc ff 54              b.ne     #0x33504
0x00033570: 02 00 00 14              b        #0x33578                   ; -> CornSpectProcessPack+0x538
0x00033574: c8 02 40 b9              ldr      w8, [x22]
0x00033578: 02 7d 7e 93              sbfiz    x2, x8, #2, #0x20
0x0003357c: 81 02 02 8b              add      x1, x20, x2
0x00033580: e0 03 15 aa              mov      x0, x21
0x00033584: 3f 69 ff 97              bl       #0xda80                    ; -> memcpy@plt
0x00033588: c8 02 80 b9              ldrsw    x8, [x22]
0x0003358c: 1f 09 00 71              cmp      w8, #2
0x00033590: 4b 02 00 54              b.lt     #0x335d8
0x00033594: 09 79 1f 53              lsl      w9, w8, #1
0x00033598: 2a 05 00 51              sub      w10, w9, #1
0x0003359c: 2b 09 00 51              sub      w11, w9, #2
0x000335a0: 49 7d 7e 93              sbfiz    x9, x10, #2, #0x20
0x000335a4: 6a 7d 7e 93              sbfiz    x10, x11, #2, #0x20
0x000335a8: 0b f5 7e d3              lsl      x11, x8, #2
0x000335ac: 8c 02 0b 8b              add      x12, x20, x11
0x000335b0: 8c c1 5f b8              ldur     w12, [x12, #-4]
0x000335b4: ab 02 0b 8b              add      x11, x21, x11
0x000335b8: 1f 09 00 f1              cmp      x8, #2
0x000335bc: 08 05 00 d1              sub      x8, x8, #1
0x000335c0: 8c 6a 2a b8              str      w12, [x20, x10]
0x000335c4: 6b c1 5f b8              ldur     w11, [x11, #-4]
0x000335c8: 4a 21 00 d1              sub      x10, x10, #8
0x000335cc: 8b 6a 29 b8              str      w11, [x20, x9]
0x000335d0: 29 21 00 d1              sub      x9, x9, #8
0x000335d4: ac fe ff 54              b.gt     #0x335a8
0x000335d8: 89 09 00 b0              adrp     x9, #0x164000
0x000335dc: 20 f5 44 fd              ldr      d0, [x9, #0x9e8]
0x000335e0: a8 02 40 b9              ldr      w8, [x21]
0x000335e4: e1 02 62 1e              scvtf    d1, w23
0x000335e8: 15 7f 01 13              asr      w21, w24, #1
0x000335ec: 00 18 61 1e              fdiv     d0, d0, d1
0x000335f0: 09 40 62 1e              fcvt     s9, d0
0x000335f4: 20 1d a9 4e              mov      v0.16b, v9.16b
0x000335f8: 88 06 00 b9              str      w8, [x20, #4]
0x000335fc: 1d 66 ff 97              bl       #0xce70                    ; -> cosf@plt
0x00033600: 08 1c a0 4e              mov      v8.16b, v0.16b
0x00033604: 20 1d a9 4e              mov      v0.16b, v9.16b
0x00033608: f2 65 ff 97              bl       #0xcdd0                    ; -> sinf@plt
0x0003360c: ff 1a 00 71              cmp      w23, #6
0x00033610: cb 07 00 54              b.lt     #0x33708
0x00033614: bf 12 00 71              cmp      w21, #4
0x00033618: e9 03 1e 32              mov      w9, #4
0x0003361c: a9 c2 89 1a              csel     w9, w21, w9, gt
0x00033620: 29 0d 00 51              sub      w9, w9, #3
0x00033624: 29 7d 01 53              lsr      w9, w9, #1
0x00033628: ec 03 15 2a              mov      w12, w21
0x0003362c: ea 03 1f 32              mov      w10, #2
0x00033630: ab 7a 1f 53              lsl      w11, w21, #1
0x00033634: 8d 0a 17 8b              add      x13, x20, x23, lsl #2
0x00033638: 29 09 00 11              add      w9, w9, #2
0x0003363c: 88 32 00 91              add      x8, x20, #0xc
0x00033640: e3 03 27 1e              fmov     s3, wzr
0x00033644: 04 10 2e 1e              fmov     s4, #1.00000000
0x00033648: 49 01 09 cb              sub      x9, x10, x9
0x0003364c: ea 03 00 32              mov      w10, #1
0x00033650: 01 10 2c 1e              fmov     s1, #0.50000000
0x00033654: 6b 05 00 51              sub      w11, w11, #1
0x00033658: 8c 05 00 d1              sub      x12, x12, #1
0x0003365c: ad 11 00 d1              sub      x13, x13, #4
0x00033660: 02 10 3c 1e              fmov     s2, #-0.50000000
0x00033664: 10 c9 7f 2d              ldp      s16, s18, [x8, #-4]
0x00033668: b1 cd 7f 2d              ldp      s17, s19, [x13, #-4]
0x0003366c: 05 09 24 1e              fmul     s5, s8, s4
0x00033670: 06 08 23 1e              fmul     s6, s0, s3
0x00033674: 07 08 24 1e              fmul     s7, s0, s4
0x00033678: 03 09 23 1e              fmul     s3, s8, s3
0x0003367c: a4 38 26 1e              fsub     s4, s5, s6
0x00033680: e3 28 23 1e              fadd     s3, s7, s3
0x00033684: 05 2a 31 1e              fadd     s5, s16, s17
0x00033688: 47 2a 33 1e              fadd     s7, s18, s19
0x0003368c: 10 3a 31 1e              fsub     s16, s16, s17
0x00033690: 46 3a 33 1e              fsub     s6, s18, s19
0x00033694: f1 08 21 1e              fmul     s17, s7, s1
0x00033698: 12 0a 22 1e              fmul     s18, s16, s2
0x0003369c: 87 08 31 1e              fmul     s7, s4, s17
0x000336a0: 70 08 32 1e              fmul     s16, s3, s18
0x000336a4: 92 08 32 1e              fmul     s18, s4, s18
0x000336a8: 71 08 31 1e              fmul     s17, s3, s17
0x000336ac: a5 08 21 1e              fmul     s5, s5, s1
0x000336b0: c6 08 21 1e              fmul     s6, s6, s1
0x000336b4: f3 38 30 1e              fsub     s19, s7, s16
0x000336b8: 51 2a 31 1e              fadd     s17, s18, s17
0x000336bc: 5f 41 2c eb              cmp      x10, w12, uxtw
0x000336c0: b2 28 33 1e              fadd     s18, s5, s19
0x000336c4: d3 28 31 1e              fadd     s19, s6, s17
0x000336c8: 12 cd 3f 2d              stp      s18, s19, [x8, #-4]
0x000336cc: e0 00 00 54              b.eq     #0x336e8
0x000336d0: 07 3a 27 1e              fsub     s7, s16, s7
0x000336d4: 6e 05 00 51              sub      w14, w11, #1
0x000336d8: 26 3a 26 1e              fsub     s6, s17, s6
0x000336dc: a5 28 27 1e              fadd     s5, s5, s7
0x000336e0: 85 da 2e bc              str      s5, [x20, w14, sxtw #2]
0x000336e4: 86 da 2b bc              str      s6, [x20, w11, sxtw #2]
0x000336e8: 4a 05 00 91              add      x10, x10, #1
0x000336ec: 2e 01 0a 8b              add      x14, x9, x10
0x000336f0: 6b 09 00 51              sub      w11, w11, #2
0x000336f4: 8c 05 00 d1              sub      x12, x12, #1
0x000336f8: 08 21 00 91              add      x8, x8, #8
0x000336fc: df 09 00 f1              cmp      x14, #2
0x00033700: ad 21 00 d1              sub      x13, x13, #8
0x00033704: 01 fb ff 54              b.ne     #0x33664
0x00033708: 80 06 40 2d              ldp      s0, s1, [x20]
0x0003370c: ff 12 00 71              cmp      w23, #4
0x00033710: a8 7e 40 93              sxtw     x8, w21
0x00033714: 02 28 21 1e              fadd     s2, s0, s1
0x00033718: 00 38 21 1e              fsub     s0, s0, s1
0x0003371c: 82 02 00 2d              stp      s2, s0, [x20]
0x00033720: 74 36 40 f9              ldr      x20, [x19, #0x68]
0x00033724: 80 0a 40 2d              ldp      s0, s2, [x20]
0x00033728: 01 08 20 1e              fmul     s1, s0, s0
0x0003372c: 40 08 22 1e              fmul     s0, s2, s2
0x00033730: 8b 01 00 54              b.lt     #0x33760
0x00033734: 89 32 00 91              add      x9, x20, #0xc
0x00033738: ea 03 00 32              mov      w10, #1
0x0003373c: 22 8d 7f 2d              ldp      s2, s3, [x9, #-4]
0x00033740: 29 21 00 91              add      x9, x9, #8
0x00033744: 42 08 22 1e              fmul     s2, s2, s2
0x00033748: 63 08 23 1e              fmul     s3, s3, s3
0x0003374c: 42 28 23 1e              fadd     s2, s2, s3
0x00033750: 82 7a 2a bc              str      s2, [x20, x10, lsl #2]
0x00033754: 4a 05 00 91              add      x10, x10, #1
0x00033758: 5f 01 08 eb              cmp      x10, x8
0x0003375c: 0b ff ff 54              b.lt     #0x3373c
0x00033760: 81 02 00 bd              str      s1, [x20]
0x00033764: 80 7a 28 bc              str      s0, [x20, x8, lsl #2]
0x00033768: 75 4a 40 b9              ldr      w21, [x19, #0x48]
0x0003376c: bf 06 00 71              cmp      w21, #1
0x00033770: eb 04 00 54              b.lt     #0x3380c
0x00033774: 88 09 00 b0              adrp     x8, #0x164000
0x00033778: 89 09 00 b0              adrp     x9, #0x164000
0x0003377c: 77 02 40 f9              ldr      x23, [x19]
0x00033780: 78 2e 40 f9              ldr      x24, [x19, #0x58]
0x00033784: 08 01 4a bd              ldr      s8, [x8, #0xa00]
0x00033788: 29 05 4a bd              ldr      s9, [x9, #0xa04]
0x0003378c: f6 03 1f aa              mov      x22, xzr
0x00033790: f9 07 1d 32              mov      w25, #0x18
0x00033794: c8 62 19 9b              madd     x8, x22, x25, x24
0x00033798: 08 09 40 b9              ldr      w8, [x8, #8]
0x0003379c: 01 1d a8 4e              mov      v1.16b, v8.16b
0x000337a0: 1f 05 00 71              cmp      w8, #1
0x000337a4: 2b 02 00 54              b.lt     #0x337e8
0x000337a8: c9 62 19 9b              madd     x9, x22, x25, x24
0x000337ac: 2a 01 80 b9              ldrsw    x10, [x9]
0x000337b0: 29 09 40 f9              ldr      x9, [x9, #0x10]
0x000337b4: e0 03 27 1e              fmov     s0, wzr
0x000337b8: 8a 0a 0a 8b              add      x10, x20, x10, lsl #2
0x000337bc: 41 45 40 bc              ldr      s1, [x10], #4
0x000337c0: 22 45 40 bc              ldr      s2, [x9], #4
0x000337c4: 08 05 00 d1              sub      x8, x8, #1
0x000337c8: 21 08 22 1e              fmul     s1, s1, s2
0x000337cc: 00 28 21 1e              fadd     s0, s0, s1
0x000337d0: 68 ff ff b5              cbnz     x8, #0x337bc
0x000337d4: 00 20 29 1e              fcmp     s0, s9
0x000337d8: 01 1d a8 4e              mov      v1.16b, v8.16b
0x000337dc: 64 00 00 54              b.mi     #0x337e8
0x000337e0: 00 68 ff 97              bl       #0xd7e0                    ; -> logf@plt
0x000337e4: 01 1c a0 4e              mov      v1.16b, v0.16b
0x000337e8: e1 7a 36 bc              str      s1, [x23, x22, lsl #2]
0x000337ec: d6 06 00 91              add      x22, x22, #1
0x000337f0: df 02 15 eb              cmp      x22, x21
0x000337f4: 01 fd ff 54              b.ne     #0x33794
0x000337f8: 68 4a 40 b9              ldr      w8, [x19, #0x48]
0x000337fc: bf 02 08 6b              cmp      w21, w8
0x00033800: c1 00 00 54              b.ne     #0x33818
0x00033804: f5 03 00 32              mov      w21, #1
0x00033808: 09 00 00 14              b        #0x3382c                   ; -> CornSpectProcessPack+0x7ec
0x0003380c: e8 03 15 2a              mov      w8, w21
0x00033810: bf 02 08 6b              cmp      w21, w8
0x00033814: 80 ff ff 54              b.eq     #0x33804
0x00033818: 80 09 00 b0              adrp     x0, #0x164000
0x0003381c: 00 54 29 91              add      x0, x0, #0xa55
0x00033820: e1 0b 80 12              mov      w1, #-0x60
0x00033824: f5 0b 80 12              mov      w21, #-0x60
0x00033828: ea 64 ff 97              bl       #0xcbd0                    ; -> printf@plt
0x0003382c: e0 03 15 2a              mov      w0, w21
0x00033830: fd 7b 45 a9              ldp      x29, x30, [sp, #0x50]
0x00033834: f4 4f 44 a9              ldp      x20, x19, [sp, #0x40]
0x00033838: f6 57 43 a9              ldp      x22, x21, [sp, #0x30]
0x0003383c: f8 5f 42 a9              ldp      x24, x23, [sp, #0x20]
0x00033840: f9 0b 40 f9              ldr      x25, [sp, #0x10]
0x00033844: e9 23 c6 6c              ldp      d9, d8, [sp], #0x60
0x00033848: c0 03 5f d6              ret      
0x0003384c: 4e 41 00 91              add      x14, x10, #0x10
0x00033850: 0f 41 00 91              add      x15, x8, #0x10
0x00033854: f0 03 0c aa              mov      x16, x12
0x00033858: c0 85 7f ad              ldp      q0, q1, [x14, #-0x10]
0x0003385c: e2 8d 7f ad              ldp      q2, q3, [x15, #-0x10]
0x00033860: ce 81 00 91              add      x14, x14, #0x20
0x00033864: 10 22 00 d1              sub      x16, x16, #8
0x00033868: 00 dc 22 6e              fmul     v0.4s, v0.4s, v2.4s
0x0003386c: 21 dc 23 6e              fmul     v1.4s, v1.4s, v3.4s
0x00033870: e0 85 3f ad              stp      q0, q1, [x15, #-0x10]
0x00033874: ef 81 00 91              add      x15, x15, #0x20
0x00033878: 10 ff ff b5              cbnz     x16, #0x33858
0x0003387c: ad d7 ff 35              cbnz     w13, #0x33370
0x00033880: c6 fe ff 17              b        #0x33398                   ; -> CornSpectProcessPack+0x358
