; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : cv_common_feature_comparator_get_distance (0x230478)
; start   : 0x230478
; size    : 0x88c
; bytes   : 0x88c   instructions: 547
; generated: 2026-10-24T00:35:16

0x00230478: fd 7b b6 a9              stp      x29, x30, [sp, #-0xa0]!
0x0023047c: fd 03 00 91              mov      x29, sp
0x00230480: f3 53 01 a9              stp      x19, x20, [sp, #0x10]
0x00230484: f5 5b 02 a9              stp      x21, x22, [sp, #0x20]
0x00230488: f7 63 03 a9              stp      x23, x24, [sp, #0x30]
0x0023048c: f9 6b 04 a9              stp      x25, x26, [sp, #0x40]
0x00230490: fb 73 05 a9              stp      x27, x28, [sp, #0x50]
0x00230494: 60 2b 00 b4              cbz      x0, #0x230a00
0x00230498: 41 2b 00 b4              cbz      x1, #0x230a00
0x0023049c: 05 00 40 b9              ldr      w5, [x0]
0x002304a0: 23 00 40 b9              ldr      w3, [x1]
0x002304a4: 64 00 05 4a              eor      w4, w3, w5
0x002304a8: 93 4c 00 12              and      w19, w4, #0xfffff
0x002304ac: b3 2a 00 35              cbnz     w19, #0x230a00
0x002304b0: 82 2a 00 b4              cbz      x2, #0x230a00
0x002304b4: f6 03 02 aa              mov      x22, x2
0x002304b8: f4 03 01 aa              mov      x20, x1
0x002304bc: f7 03 00 aa              mov      x23, x0
0x002304c0: a5 23 f8 36              tbz      w5, #0x1f, #0x230934
0x002304c4: e3 2a f8 37              tbnz     w3, #0x1f, #0x230a20
0x002304c8: e0 0a 80 b9              ldrsw    x0, [x23, #8]
0x002304cc: 1f 30 00 f1              cmp      x0, #0xc
0x002304d0: 89 29 00 54              b.ls     #0x230a00
0x002304d4: fc e9 12 94              bl       #0x6eacc4                  ; -> _Znam
0x002304d8: f8 03 00 aa              mov      x24, x0
0x002304dc: e0 02 40 f9              ldr      x0, [x23]
0x002304e0: 00 03 00 f9              str      x0, [x24]
0x002304e4: e0 0a 40 b9              ldr      w0, [x23, #8]
0x002304e8: 00 0b 00 b9              str      w0, [x24, #8]
0x002304ec: 00 03 40 b9              ldr      w0, [x24]
0x002304f0: e5 0a 80 b9              ldrsw    x5, [x23, #8]
0x002304f4: 00 4c 00 12              and      w0, w0, #0xfffff
0x002304f8: 00 03 00 b9              str      w0, [x24]
0x002304fc: a5 30 00 d1              sub      x5, x5, #0xc
0x00230500: a5 fc 42 d3              lsr      x5, x5, #2
0x00230504: bf 00 1f 6b              cmp      w5, wzr
0x00230508: 6d 03 00 54              b.le     #0x230574
0x0023050c: e0 0e 40 bd              ldr      s0, [x23, #0xc]
0x00230510: 00 00 38 1e              fcvtzs   w0, s0
0x00230514: 1f fc 07 71              cmp      w0, #0x1ff
0x00230518: 68 37 00 54              b.hi     #0x230c04
0x0023051c: 03 33 00 91              add      x3, x24, #0xc
0x00230520: e4 42 00 91              add      x4, x23, #0x10
0x00230524: 02 00 80 52              mov      w2, #0
0x00230528: c6 5c 02 b0              adrp     x6, #0x4dc9000
0x0023052c: e1 3e 00 1c              ldr      s1, #0x230d08
0x00230530: 02 10 2c 1e              fmov     s2, #0.50000000
0x00230534: 06 00 00 14              b        #0x23054c                  ; -> cv_common_feature_comparator_get_distance+0xd4
0x00230538: 80 44 40 bc              ldr      s0, [x4], #4
0x0023053c: 63 10 00 91              add      x3, x3, #4
0x00230540: 00 00 38 1e              fcvtzs   w0, s0
0x00230544: 1f fc 07 71              cmp      w0, #0x1ff
0x00230548: e8 35 00 54              b.hi     #0x230c04
0x0023054c: c1 00 0e 91              add      x1, x6, #0x380
0x00230550: 42 04 00 11              add      w2, w2, #1
0x00230554: 5f 00 05 6b              cmp      w2, w5
0x00230558: 20 d8 60 b8              ldr      w0, [x1, w0, sxtw #2]
0x0023055c: 00 00 04 51              sub      w0, w0, #0x100
0x00230560: 00 00 22 1e              scvtf    s0, w0
0x00230564: 00 28 22 1e              fadd     s0, s0, s2
0x00230568: 00 08 21 1e              fmul     s0, s0, s1
0x0023056c: 60 00 00 bd              str      s0, [x3]
0x00230570: 41 fe ff 54              b.ne     #0x230538
0x00230574: 83 02 40 b9              ldr      w3, [x20]
0x00230578: 23 1e f8 37              tbnz     w3, #0x1f, #0x23093c
0x0023057c: f9 03 14 aa              mov      x25, x20
0x00230580: 9a 32 00 91              add      x26, x20, #0xc
0x00230584: 15 0b 40 b9              ldr      w21, [x24, #8]
0x00230588: 1c 33 00 91              add      x28, x24, #0xc
0x0023058c: 20 0b 40 b9              ldr      w0, [x25, #8]
0x00230590: b5 32 00 51              sub      w21, w21, #0xc
0x00230594: 00 30 00 51              sub      w0, w0, #0xc
0x00230598: bf 02 00 6b              cmp      w21, w0
0x0023059c: c1 39 00 54              b.ne     #0x230cd4
0x002305a0: e0 03 27 1e              fmov     s0, wzr
0x002305a4: b5 7e 42 d3              ubfx     x21, x21, #2, #0x1e
0x002305a8: bf 3b 00 f9              str      xzr, [x29, #0x70]
0x002305ac: bb c3 01 91              add      x27, x29, #0x70
0x002305b0: c0 02 00 bd              str      s0, [x22]
0x002305b4: 55 2d 00 b4              cbz      x21, #0x230b5c
0x002305b8: a0 f6 7e d3              lsl      x0, x21, #2
0x002305bc: 00 40 00 91              add      x0, x0, #0x10
0x002305c0: 6c 61 f9 97              bl       #0x88b70                   ; -> malloc@plt
0x002305c4: c0 2e 00 b4              cbz      x0, #0x230b9c
0x002305c8: 01 ec 7c 92              and      x1, x0, #0xfffffffffffffff0
0x002305cc: 21 40 00 91              add      x1, x1, #0x10
0x002305d0: 61 03 00 f9              str      x1, [x27]
0x002305d4: fb 03 01 aa              mov      x27, x1
0x002305d8: 20 80 1f f8              stur     x0, [x1, #-8]
0x002305dc: a2 fe 42 93              asr      x2, x21, #2
0x002305e0: 53 f4 7e d3              lsl      x19, x2, #2
0x002305e4: 33 01 00 b4              cbz      x19, #0x230608
0x002305e8: e5 03 1c aa              mov      x5, x28
0x002305ec: e4 03 1b aa              mov      x4, x27
0x002305f0: 03 00 80 d2              mov      x3, #0
0x002305f4: a0 04 c1 3c              ldr      q0, [x5], #0x10
0x002305f8: 63 10 00 91              add      x3, x3, #4
0x002305fc: 7f 02 03 eb              cmp      x19, x3
0x00230600: 80 04 81 3c              str      q0, [x4], #0x10
0x00230604: 8c ff ff 54              b.gt     #0x2305f4
0x00230608: bf 02 13 eb              cmp      x21, x19
0x0023060c: 6d 09 00 54              b.le     #0x230738
0x00230610: 40 04 00 91              add      x0, x2, #1
0x00230614: a4 02 13 cb              sub      x4, x21, x19
0x00230618: 00 ec 7c d3              lsl      x0, x0, #4
0x0023061c: 06 40 00 d1              sub      x6, x0, #0x10
0x00230620: 01 30 00 91              add      x1, x0, #0xc
0x00230624: 03 10 00 d1              sub      x3, x0, #4
0x00230628: 01 03 01 8b              add      x1, x24, x1
0x0023062c: 65 03 06 8b              add      x5, x27, x6
0x00230630: 60 03 00 8b              add      x0, x27, x0
0x00230634: bf 00 01 eb              cmp      x5, x1
0x00230638: 03 03 03 8b              add      x3, x24, x3
0x0023063c: e1 37 9f 1a              cset     w1, hs
0x00230640: 7f 00 00 eb              cmp      x3, x0
0x00230644: e0 37 9f 1a              cset     w0, hs
0x00230648: 20 00 00 2a              orr      w0, w1, w0
0x0023064c: c0 28 00 34              cbz      w0, #0x230b64
0x00230650: 9f 2c 00 f1              cmp      x4, #0xb
0x00230654: 89 28 00 54              b.ls     #0x230b64
0x00230658: 63 0c 42 d3              ubfx     x3, x3, #2, #2
0x0023065c: e0 03 03 cb              neg      x0, x3
0x00230660: 00 04 40 92              and      x0, x0, #3
0x00230664: 9f 00 00 eb              cmp      x4, x0
0x00230668: 80 90 80 9a              csel     x0, x4, x0, ls
0x0023066c: 00 25 00 b4              cbz      x0, #0x230b0c
0x00230670: 83 7b 73 b8              ldr      w3, [x28, x19, lsl #2]
0x00230674: 1f 04 00 f1              cmp      x0, #1
0x00230678: 65 f6 7e d3              lsl      x5, x19, #2
0x0023067c: 61 06 00 91              add      x1, x19, #1
0x00230680: 63 7b 33 b8              str      w3, [x27, x19, lsl #2]
0x00230684: 60 24 00 54              b.eq     #0x230b10
0x00230688: a3 10 00 91              add      x3, x5, #4
0x0023068c: 1f 0c 00 f1              cmp      x0, #3
0x00230690: 61 0a 00 91              add      x1, x19, #2
0x00230694: 87 6b 63 b8              ldr      w7, [x28, x3]
0x00230698: 67 6b 23 b8              str      w7, [x27, x3]
0x0023069c: a1 23 00 54              b.ne     #0x230b10
0x002306a0: a5 20 00 91              add      x5, x5, #8
0x002306a4: 63 0e 00 91              add      x3, x19, #3
0x002306a8: e1 03 03 aa              mov      x1, x3
0x002306ac: 87 6b 65 b8              ldr      w7, [x28, x5]
0x002306b0: 67 6b 25 b8              str      w7, [x27, x5]
0x002306b4: 84 00 00 cb              sub      x4, x4, x0
0x002306b8: 63 00 00 8b              add      x3, x3, x0
0x002306bc: 85 10 00 d1              sub      x5, x4, #4
0x002306c0: c0 08 00 8b              add      x0, x6, x0, lsl #2
0x002306c4: a5 fc 42 d3              lsr      x5, x5, #2
0x002306c8: 03 0b 03 8b              add      x3, x24, x3, lsl #2
0x002306cc: a5 04 00 91              add      x5, x5, #1
0x002306d0: 60 03 00 8b              add      x0, x27, x0
0x002306d4: a7 f4 7e d3              lsl      x7, x5, #2
0x002306d8: 06 00 80 d2              mov      x6, #0
0x002306dc: 60 04 c1 3c              ldr      q0, [x3], #0x10
0x002306e0: c6 04 00 91              add      x6, x6, #1
0x002306e4: df 00 05 eb              cmp      x6, x5
0x002306e8: 00 04 81 3c              str      q0, [x0], #0x10
0x002306ec: 83 ff ff 54              b.lo     #0x2306dc
0x002306f0: 9f 00 07 eb              cmp      x4, x7
0x002306f4: e0 00 01 8b              add      x0, x7, x1
0x002306f8: 00 02 00 54              b.eq     #0x230738
0x002306fc: 83 7b 60 b8              ldr      w3, [x28, x0, lsl #2]
0x00230700: 01 04 00 91              add      x1, x0, #1
0x00230704: bf 02 01 eb              cmp      x21, x1
0x00230708: 01 f4 7e d3              lsl      x1, x0, #2
0x0023070c: 63 7b 20 b8              str      w3, [x27, x0, lsl #2]
0x00230710: 4d 01 00 54              b.le     #0x230738
0x00230714: 23 10 00 91              add      x3, x1, #4
0x00230718: 00 08 00 91              add      x0, x0, #2
0x0023071c: bf 02 00 eb              cmp      x21, x0
0x00230720: 80 6b 63 b8              ldr      w0, [x28, x3]
0x00230724: 60 6b 23 b8              str      w0, [x27, x3]
0x00230728: 8d 00 00 54              b.le     #0x230738
0x0023072c: 21 20 00 91              add      x1, x1, #8
0x00230730: 80 6b 61 b8              ldr      w0, [x28, x1]
0x00230734: 60 6b 21 b8              str      w0, [x27, x1]
0x00230738: bf 43 00 f9              str      xzr, [x29, #0x80]
0x0023073c: bc 03 02 91              add      x28, x29, #0x80
0x00230740: 01 00 80 d2              mov      x1, #0
0x00230744: 95 01 00 b4              cbz      x21, #0x230774
0x00230748: a0 f6 7e d3              lsl      x0, x21, #2
0x0023074c: a2 37 00 f9              str      x2, [x29, #0x68]
0x00230750: 00 40 00 91              add      x0, x0, #0x10
0x00230754: 07 61 f9 97              bl       #0x88b70                   ; -> malloc@plt
0x00230758: 80 2a 00 b4              cbz      x0, #0x230ca8
0x0023075c: 03 ec 7c 92              and      x3, x0, #0xfffffffffffffff0
0x00230760: a2 37 40 f9              ldr      x2, [x29, #0x68]
0x00230764: 63 40 00 91              add      x3, x3, #0x10
0x00230768: 83 03 00 f9              str      x3, [x28]
0x0023076c: e1 03 03 aa              mov      x1, x3
0x00230770: 60 80 1f f8              stur     x0, [x3, #-8]
0x00230774: 33 01 00 b4              cbz      x19, #0x230798
0x00230778: e5 03 1a aa              mov      x5, x26
0x0023077c: e4 03 01 aa              mov      x4, x1
0x00230780: 03 00 80 d2              mov      x3, #0
0x00230784: a0 04 c1 3c              ldr      q0, [x5], #0x10
0x00230788: 63 10 00 91              add      x3, x3, #4
0x0023078c: 7f 02 03 eb              cmp      x19, x3
0x00230790: 80 04 81 3c              str      q0, [x4], #0x10
0x00230794: 8c ff ff 54              b.gt     #0x230784
0x00230798: bf 02 13 eb              cmp      x21, x19
0x0023079c: 6d 09 00 54              b.le     #0x2308c8
0x002307a0: 42 04 00 91              add      x2, x2, #1
0x002307a4: a5 02 13 cb              sub      x5, x21, x19
0x002307a8: 42 ec 7c d3              lsl      x2, x2, #4
0x002307ac: 47 40 00 d1              sub      x7, x2, #0x10
0x002307b0: 40 30 00 91              add      x0, x2, #0xc
0x002307b4: 43 10 00 d1              sub      x3, x2, #4
0x002307b8: 20 03 00 8b              add      x0, x25, x0
0x002307bc: 24 00 07 8b              add      x4, x1, x7
0x002307c0: 23 03 03 8b              add      x3, x25, x3
0x002307c4: 1f 00 04 eb              cmp      x0, x4
0x002307c8: 22 00 02 8b              add      x2, x1, x2
0x002307cc: e4 87 9f 1a              cset     w4, ls
0x002307d0: 7f 00 02 eb              cmp      x3, x2
0x002307d4: e0 37 9f 1a              cset     w0, hs
0x002307d8: 80 00 00 2a              orr      w0, w4, w0
0x002307dc: 20 1d 00 34              cbz      w0, #0x230b80
0x002307e0: bf 2c 00 f1              cmp      x5, #0xb
0x002307e4: e9 1c 00 54              b.ls     #0x230b80
0x002307e8: 60 0c 42 d3              ubfx     x0, x3, #2, #2
0x002307ec: e0 03 00 cb              neg      x0, x0
0x002307f0: 00 04 40 92              and      x0, x0, #3
0x002307f4: bf 00 00 eb              cmp      x5, x0
0x002307f8: a0 90 80 9a              csel     x0, x5, x0, ls
0x002307fc: e0 18 00 b4              cbz      x0, #0x230b18
0x00230800: 43 7b 73 b8              ldr      w3, [x26, x19, lsl #2]
0x00230804: 1f 04 00 f1              cmp      x0, #1
0x00230808: 62 f6 7e d3              lsl      x2, x19, #2
0x0023080c: 64 06 00 91              add      x4, x19, #1
0x00230810: 23 78 33 b8              str      w3, [x1, x19, lsl #2]
0x00230814: 40 18 00 54              b.eq     #0x230b1c
0x00230818: 43 10 00 91              add      x3, x2, #4
0x0023081c: 1f 0c 00 f1              cmp      x0, #3
0x00230820: 64 0a 00 91              add      x4, x19, #2
0x00230824: 46 6b 63 b8              ldr      w6, [x26, x3]
0x00230828: 26 68 23 b8              str      w6, [x1, x3]
0x0023082c: 81 17 00 54              b.ne     #0x230b1c
0x00230830: 42 20 00 91              add      x2, x2, #8
0x00230834: 63 0e 00 91              add      x3, x19, #3
0x00230838: e4 03 03 aa              mov      x4, x3
0x0023083c: 46 6b 62 b8              ldr      w6, [x26, x2]
0x00230840: 26 68 22 b8              str      w6, [x1, x2]
0x00230844: a5 00 00 cb              sub      x5, x5, x0
0x00230848: 62 00 00 8b              add      x2, x3, x0
0x0023084c: a6 10 00 d1              sub      x6, x5, #4
0x00230850: e3 08 00 8b              add      x3, x7, x0, lsl #2
0x00230854: c6 fc 42 d3              lsr      x6, x6, #2
0x00230858: 20 00 03 8b              add      x0, x1, x3
0x0023085c: c6 04 00 91              add      x6, x6, #1
0x00230860: 22 0b 02 8b              add      x2, x25, x2, lsl #2
0x00230864: c7 f4 7e d3              lsl      x7, x6, #2
0x00230868: 03 00 80 d2              mov      x3, #0
0x0023086c: 40 04 c1 3c              ldr      q0, [x2], #0x10
0x00230870: 63 04 00 91              add      x3, x3, #1
0x00230874: df 00 03 eb              cmp      x6, x3
0x00230878: 00 04 81 3c              str      q0, [x0], #0x10
0x0023087c: 88 ff ff 54              b.hi     #0x23086c
0x00230880: ff 00 05 eb              cmp      x7, x5
0x00230884: e0 00 04 8b              add      x0, x7, x4
0x00230888: 00 02 00 54              b.eq     #0x2308c8
0x0023088c: 43 7b 60 b8              ldr      w3, [x26, x0, lsl #2]
0x00230890: 02 04 00 91              add      x2, x0, #1
0x00230894: bf 02 02 eb              cmp      x21, x2
0x00230898: 02 f4 7e d3              lsl      x2, x0, #2
0x0023089c: 23 78 20 b8              str      w3, [x1, x0, lsl #2]
0x002308a0: 4d 01 00 54              b.le     #0x2308c8
0x002308a4: 43 10 00 91              add      x3, x2, #4
0x002308a8: 00 08 00 91              add      x0, x0, #2
0x002308ac: bf 02 00 eb              cmp      x21, x0
0x002308b0: 40 6b 63 b8              ldr      w0, [x26, x3]
0x002308b4: 20 68 23 b8              str      w0, [x1, x3]
0x002308b8: 8d 00 00 54              b.le     #0x2308c8
0x002308bc: 42 20 00 91              add      x2, x2, #8
0x002308c0: 40 6b 62 b8              ldr      w0, [x26, x2]
0x002308c4: 20 68 22 b8              str      w0, [x1, x2]
0x002308c8: a0 73 02 91              add      x0, x29, #0x9c
0x002308cc: a0 4b 00 f9              str      x0, [x29, #0x90]
0x002308d0: e0 03 27 1e              fmov     s0, wzr
0x002308d4: 95 12 00 b5              cbnz     x21, #0x230b24
0x002308d8: c0 02 00 bd              str      s0, [x22]
0x002308dc: 61 00 00 b4              cbz      x1, #0x2308e8
0x002308e0: 20 80 5f f8              ldur     x0, [x1, #-8]
0x002308e4: eb 61 f9 97              bl       #0x89090                   ; -> free@plt
0x002308e8: 7b 00 00 b4              cbz      x27, #0x2308f4
0x002308ec: 60 83 5f f8              ldur     x0, [x27, #-8]
0x002308f0: e8 61 f9 97              bl       #0x89090                   ; -> free@plt
0x002308f4: ff 02 18 eb              cmp      x23, x24
0x002308f8: 60 00 00 54              b.eq     #0x230904
0x002308fc: e0 03 18 aa              mov      x0, x24
0x00230900: 06 f3 ff 97              bl       #0x22d518                  ; -> cv_common_feature_release
0x00230904: 9f 02 19 eb              cmp      x20, x25
0x00230908: 60 00 00 54              b.eq     #0x230914
0x0023090c: e0 03 19 aa              mov      x0, x25
0x00230910: 02 f3 ff 97              bl       #0x22d518                  ; -> cv_common_feature_release
0x00230914: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x00230918: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x0023091c: f7 63 43 a9              ldp      x23, x24, [sp, #0x30]
0x00230920: f9 6b 44 a9              ldp      x25, x26, [sp, #0x40]
0x00230924: fb 73 45 a9              ldp      x27, x28, [sp, #0x50]
0x00230928: 00 00 80 52              mov      w0, #0
0x0023092c: fd 7b ca a8              ldp      x29, x30, [sp], #0xa0
0x00230930: c0 03 5f d6              ret      
0x00230934: f8 03 17 aa              mov      x24, x23
0x00230938: 23 e2 ff 36              tbz      w3, #0x1f, #0x23057c
0x0023093c: 80 0a 80 b9              ldrsw    x0, [x20, #8]
0x00230940: 1f 30 00 f1              cmp      x0, #0xc
0x00230944: e9 05 00 54              b.ls     #0x230a00
0x00230948: df e8 12 94              bl       #0x6eacc4                  ; -> _Znam
0x0023094c: f9 03 00 aa              mov      x25, x0
0x00230950: 80 02 40 f9              ldr      x0, [x20]
0x00230954: 3a 33 00 91              add      x26, x25, #0xc
0x00230958: 20 03 00 f9              str      x0, [x25]
0x0023095c: 80 0a 40 b9              ldr      w0, [x20, #8]
0x00230960: 20 0b 00 b9              str      w0, [x25, #8]
0x00230964: 20 03 40 b9              ldr      w0, [x25]
0x00230968: 85 0a 80 b9              ldrsw    x5, [x20, #8]
0x0023096c: 00 4c 00 12              and      w0, w0, #0xfffff
0x00230970: 20 03 00 b9              str      w0, [x25]
0x00230974: a5 30 00 d1              sub      x5, x5, #0xc
0x00230978: a5 fc 42 d3              lsr      x5, x5, #2
0x0023097c: bf 00 1f 6b              cmp      w5, wzr
0x00230980: 2d e0 ff 54              b.le     #0x230584
0x00230984: 80 0e 40 bd              ldr      s0, [x20, #0xc]
0x00230988: 03 00 38 1e              fcvtzs   w3, s0
0x0023098c: 7f fc 07 71              cmp      w3, #0x1ff
0x00230990: e8 02 00 54              b.hi     #0x2309ec
0x00230994: a1 1b 00 1c              ldr      s1, #0x230d08
0x00230998: 3a 33 00 91              add      x26, x25, #0xc
0x0023099c: 84 42 00 91              add      x4, x20, #0x10
0x002309a0: e2 03 1a aa              mov      x2, x26
0x002309a4: 01 00 80 52              mov      w1, #0
0x002309a8: c6 5c 02 b0              adrp     x6, #0x4dc9000
0x002309ac: 02 10 2c 1e              fmov     s2, #0.50000000
0x002309b0: c0 00 0e 91              add      x0, x6, #0x380
0x002309b4: 21 04 00 11              add      w1, w1, #1
0x002309b8: 3f 00 05 6b              cmp      w1, w5
0x002309bc: 03 d8 63 b8              ldr      w3, [x0, w3, sxtw #2]
0x002309c0: 63 00 04 51              sub      w3, w3, #0x100
0x002309c4: 60 00 22 1e              scvtf    s0, w3
0x002309c8: 00 28 22 1e              fadd     s0, s0, s2
0x002309cc: 00 08 21 1e              fmul     s0, s0, s1
0x002309d0: 40 00 00 bd              str      s0, [x2]
0x002309d4: 80 dd ff 54              b.eq     #0x230584
0x002309d8: 80 44 40 bc              ldr      s0, [x4], #4
0x002309dc: 42 10 00 91              add      x2, x2, #4
0x002309e0: 03 00 38 1e              fcvtzs   w3, s0
0x002309e4: 7f fc 07 71              cmp      w3, #0x1ff
0x002309e8: 49 fe ff 54              b.ls     #0x2309b0
0x002309ec: e0 03 19 aa              mov      x0, x25
0x002309f0: ca f2 ff 97              bl       #0x22d518                  ; -> cv_common_feature_release
0x002309f4: 80 00 80 52              mov      w0, #4
0x002309f8: 00 32 b0 72              movk     w0, #0x8190, lsl #16
0x002309fc: 02 00 00 14              b        #0x230a04                  ; -> cv_common_feature_comparator_get_distance+0x58c
0x00230a00: 00 00 80 12              mov      w0, #-1
0x00230a04: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x00230a08: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x00230a0c: f7 63 43 a9              ldp      x23, x24, [sp, #0x30]
0x00230a10: f9 6b 44 a9              ldp      x25, x26, [sp, #0x40]
0x00230a14: fb 73 45 a9              ldp      x27, x28, [sp, #0x50]
0x00230a18: fd 7b ca a8              ldp      x29, x30, [sp], #0xa0
0x00230a1c: c0 03 5f d6              ret      
0x00230a20: f3 0a 80 b9              ldrsw    x19, [x23, #8]
0x00230a24: 20 e5 02 90              adrp     x0, #0x5ed4000
0x00230a28: 73 32 00 d1              sub      x19, x19, #0xc
0x00230a2c: 73 fe 42 d3              lsr      x19, x19, #2
0x00230a30: 00 e8 41 f9              ldr      x0, [x0, #0x3d0]
0x00230a34: 7f 02 1f 6b              cmp      w19, wzr
0x00230a38: 0d 0f 00 54              b.le     #0x230c18
0x00230a3c: e0 0e 40 bd              ldr      s0, [x23, #0xc]
0x00230a40: 21 0c 40 bd              ldr      s1, [x1, #0xc]
0x00230a44: 03 00 38 1e              fcvtzs   w3, s0
0x00230a48: 24 00 38 1e              fcvtzs   w4, s1
0x00230a4c: 7f fc 07 71              cmp      w3, #0x1ff
0x00230a50: c8 04 00 54              b.hi     #0x230ae8
0x00230a54: 9f fc 07 71              cmp      w4, #0x1ff
0x00230a58: 88 04 00 54              b.hi     #0x230ae8
0x00230a5c: 73 06 00 51              sub      w19, w19, #1
0x00230a60: e5 42 00 91              add      x5, x23, #0x10
0x00230a64: 73 12 00 91              add      x19, x19, #4
0x00230a68: 21 40 00 91              add      x1, x1, #0x10
0x00230a6c: e2 03 27 1e              fmov     s2, wzr
0x00230a70: f3 0a 13 8b              add      x19, x23, x19, lsl #2
0x00230a74: 0b 00 00 14              b        #0x230aa0                  ; -> cv_common_feature_comparator_get_distance+0x628
0x00230a78: a0 00 40 bd              ldr      s0, [x5]
0x00230a7c: 21 00 40 bd              ldr      s1, [x1]
0x00230a80: 03 00 38 1e              fcvtzs   w3, s0
0x00230a84: 24 00 38 1e              fcvtzs   w4, s1
0x00230a88: 7f fc 07 71              cmp      w3, #0x1ff
0x00230a8c: e8 02 00 54              b.hi     #0x230ae8
0x00230a90: 9f fc 07 71              cmp      w4, #0x1ff
0x00230a94: a5 10 00 91              add      x5, x5, #4
0x00230a98: 21 10 00 91              add      x1, x1, #4
0x00230a9c: 68 02 00 54              b.hi     #0x230ae8
0x00230aa0: 63 7c 75 93              sbfiz    x3, x3, #0xb, #0x20
0x00230aa4: 04 c8 24 8b              add      x4, x0, w4, sxtw #2
0x00230aa8: bf 00 13 eb              cmp      x5, x19
0x00230aac: 83 68 63 b8              ldr      w3, [x4, x3]
0x00230ab0: 60 00 22 1e              scvtf    s0, w3
0x00230ab4: 42 28 20 1e              fadd     s2, s2, s0
0x00230ab8: 01 fe ff 54              b.ne     #0x230a78
0x00230abc: 40 12 00 1c              ldr      s0, #0x230d04
0x00230ac0: 42 08 20 1e              fmul     s2, s2, s0
0x00230ac4: c2 02 00 bd              str      s2, [x22]
0x00230ac8: 00 00 80 52              mov      w0, #0
0x00230acc: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x00230ad0: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x00230ad4: f7 63 43 a9              ldp      x23, x24, [sp, #0x30]
0x00230ad8: f9 6b 44 a9              ldp      x25, x26, [sp, #0x40]
0x00230adc: fb 73 45 a9              ldp      x27, x28, [sp, #0x50]
0x00230ae0: fd 7b ca a8              ldp      x29, x30, [sp], #0xa0
0x00230ae4: c0 03 5f d6              ret      
0x00230ae8: 80 00 80 52              mov      w0, #4
0x00230aec: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x00230af0: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x00230af4: f7 63 43 a9              ldp      x23, x24, [sp, #0x30]
0x00230af8: f9 6b 44 a9              ldp      x25, x26, [sp, #0x40]
0x00230afc: fb 73 45 a9              ldp      x27, x28, [sp, #0x50]
0x00230b00: 00 32 b0 72              movk     w0, #0x8190, lsl #16
0x00230b04: fd 7b ca a8              ldp      x29, x30, [sp], #0xa0
0x00230b08: c0 03 5f d6              ret      
0x00230b0c: e1 03 13 aa              mov      x1, x19
0x00230b10: 63 0e 00 91              add      x3, x19, #3
0x00230b14: e8 fe ff 17              b        #0x2306b4                  ; -> cv_common_feature_comparator_get_distance+0x23c
0x00230b18: e4 03 13 aa              mov      x4, x19
0x00230b1c: 63 0e 00 91              add      x3, x19, #3
0x00230b20: 49 ff ff 17              b        #0x230844                  ; -> cv_common_feature_comparator_get_distance+0x3cc
0x00230b24: d3 04 00 b5              cbnz     x19, #0x230bbc
0x00230b28: 60 03 40 bd              ldr      s0, [x27]
0x00230b2c: bf 06 00 f1              cmp      x21, #1
0x00230b30: 21 00 40 bd              ldr      s1, [x1]
0x00230b34: 00 08 21 1e              fmul     s0, s0, s1
0x00230b38: 00 ed ff 54              b.eq     #0x2308d8
0x00230b3c: 20 00 80 d2              mov      x0, #1
0x00230b40: 62 7b 60 bc              ldr      s2, [x27, x0, lsl #2]
0x00230b44: 21 78 60 bc              ldr      s1, [x1, x0, lsl #2]
0x00230b48: 00 04 00 91              add      x0, x0, #1
0x00230b4c: bf 02 00 eb              cmp      x21, x0
0x00230b50: 40 00 01 1f              fmadd    s0, s2, s1, s0
0x00230b54: 61 ff ff 54              b.ne     #0x230b40
0x00230b58: 60 ff ff 17              b        #0x2308d8                  ; -> cv_common_feature_comparator_get_distance+0x460
0x00230b5c: 1b 00 80 d2              mov      x27, #0
0x00230b60: 9f fe ff 17              b        #0x2305dc                  ; -> cv_common_feature_comparator_get_distance+0x164
0x00230b64: e0 03 13 aa              mov      x0, x19
0x00230b68: 81 7b 60 b8              ldr      w1, [x28, x0, lsl #2]
0x00230b6c: 61 7b 20 b8              str      w1, [x27, x0, lsl #2]
0x00230b70: 00 04 00 91              add      x0, x0, #1
0x00230b74: 1f 00 15 eb              cmp      x0, x21
0x00230b78: 81 ff ff 54              b.ne     #0x230b68
0x00230b7c: ef fe ff 17              b        #0x230738                  ; -> cv_common_feature_comparator_get_distance+0x2c0
0x00230b80: e0 03 13 aa              mov      x0, x19
0x00230b84: 42 7b 60 b8              ldr      w2, [x26, x0, lsl #2]
0x00230b88: 22 78 20 b8              str      w2, [x1, x0, lsl #2]
0x00230b8c: 00 04 00 91              add      x0, x0, #1
0x00230b90: bf 02 00 eb              cmp      x21, x0
0x00230b94: 81 ff ff 54              b.ne     #0x230b84
0x00230b98: 4c ff ff 17              b        #0x2308c8                  ; -> cv_common_feature_comparator_get_distance+0x450
0x00230b9c: 71 f8 fd 97              bl       #0x1aed60                  ; -> _ZN5Eigen8internal19throw_std_bad_allocEv
0x00230ba0: 61 03 40 f9              ldr      x1, [x27]
0x00230ba4: f3 03 00 aa              mov      x19, x0
0x00230ba8: 61 00 00 b4              cbz      x1, #0x230bb4
0x00230bac: 20 80 5f f8              ldur     x0, [x1, #-8]
0x00230bb0: 38 61 f9 97              bl       #0x89090                   ; -> free@plt
0x00230bb4: e0 03 13 aa              mov      x0, x19
0x00230bb8: bf 7d 14 94              bl       #0x7502b4                  ; -> _Unwind_Resume
0x00230bbc: 60 03 c0 3d              ldr      q0, [x27]
0x00230bc0: 7f 12 00 f1              cmp      x19, #4
0x00230bc4: 22 00 c0 3d              ldr      q2, [x1]
0x00230bc8: 00 dc 22 6e              fmul     v0.4s, v0.4s, v2.4s
0x00230bcc: ac 02 00 54              b.gt     #0x230c20
0x00230bd0: bf 02 13 eb              cmp      x21, x19
0x00230bd4: 01 04 08 5e              mov      d1, v0.d[0]
0x00230bd8: 00 44 08 6e              mov      v0.d[0], v0.d[1]
0x00230bdc: 20 d4 20 2e              faddp    v0.2s, v1.2s, v0.2s
0x00230be0: 00 d4 20 2e              faddp    v0.2s, v0.2s, v0.2s
0x00230be4: ad e7 ff 54              b.le     #0x2308d8
0x00230be8: 62 7b 73 bc              ldr      s2, [x27, x19, lsl #2]
0x00230bec: 21 78 73 bc              ldr      s1, [x1, x19, lsl #2]
0x00230bf0: 73 06 00 91              add      x19, x19, #1
0x00230bf4: bf 02 13 eb              cmp      x21, x19
0x00230bf8: 40 00 01 1f              fmadd    s0, s2, s1, s0
0x00230bfc: 61 ff ff 54              b.ne     #0x230be8
0x00230c00: 36 ff ff 17              b        #0x2308d8                  ; -> cv_common_feature_comparator_get_distance+0x460
0x00230c04: e0 03 18 aa              mov      x0, x24
0x00230c08: 44 f2 ff 97              bl       #0x22d518                  ; -> cv_common_feature_release
0x00230c0c: 80 00 80 52              mov      w0, #4
0x00230c10: 00 32 b0 72              movk     w0, #0x8190, lsl #16
0x00230c14: 7c ff ff 17              b        #0x230a04                  ; -> cv_common_feature_comparator_get_distance+0x58c
0x00230c18: e2 03 27 1e              fmov     s2, wzr
0x00230c1c: aa ff ff 17              b        #0x230ac4                  ; -> cv_common_feature_comparator_get_distance+0x64c
0x00230c20: 62 43 00 91              add      x2, x27, #0x10
0x00230c24: 20 40 00 91              add      x0, x1, #0x10
0x00230c28: a6 6a 7d 92              and      x6, x21, #0x3ffffff8
0x00230c2c: 41 00 c0 3d              ldr      q1, [x2]
0x00230c30: df 20 00 f1              cmp      x6, #8
0x00230c34: 02 00 c0 3d              ldr      q2, [x0]
0x00230c38: 21 dc 22 6e              fmul     v1.4s, v1.4s, v2.4s
0x00230c3c: 2d 02 00 54              b.le     #0x230c80
0x00230c40: 60 c3 00 91              add      x0, x27, #0x30
0x00230c44: 24 80 00 91              add      x4, x1, #0x20
0x00230c48: 03 01 80 d2              mov      x3, #8
0x00230c4c: e2 03 04 aa              mov      x2, x4
0x00230c50: 05 40 00 d1              sub      x5, x0, #0x10
0x00230c54: 03 00 c0 3d              ldr      q3, [x0]
0x00230c58: 63 20 00 91              add      x3, x3, #8
0x00230c5c: 44 04 c1 3c              ldr      q4, [x2], #0x10
0x00230c60: df 00 03 eb              cmp      x6, x3
0x00230c64: a5 00 c0 3d              ldr      q5, [x5]
0x00230c68: 00 80 00 91              add      x0, x0, #0x20
0x00230c6c: 84 80 00 91              add      x4, x4, #0x20
0x00230c70: 42 00 c0 3d              ldr      q2, [x2]
0x00230c74: a0 cc 24 4e              fmla     v0.4s, v5.4s, v4.4s
0x00230c78: 61 cc 22 4e              fmla     v1.4s, v3.4s, v2.4s
0x00230c7c: 8c fe ff 54              b.gt     #0x230c4c
0x00230c80: 7f 02 06 eb              cmp      x19, x6
0x00230c84: 00 d4 21 4e              fadd     v0.4s, v0.4s, v1.4s
0x00230c88: 4d fa ff 54              b.le     #0x230bd0
0x00230c8c: c6 f4 7e d3              lsl      x6, x6, #2
0x00230c90: 60 03 06 8b              add      x0, x27, x6
0x00230c94: 26 00 06 8b              add      x6, x1, x6
0x00230c98: 02 00 c0 3d              ldr      q2, [x0]
0x00230c9c: c1 00 c0 3d              ldr      q1, [x6]
0x00230ca0: 40 cc 21 4e              fmla     v0.4s, v2.4s, v1.4s
0x00230ca4: cb ff ff 17              b        #0x230bd0                  ; -> cv_common_feature_comparator_get_distance+0x758
0x00230ca8: 2e f8 fd 97              bl       #0x1aed60                  ; -> _ZN5Eigen8internal19throw_std_bad_allocEv
0x00230cac: 81 03 40 f9              ldr      x1, [x28]
0x00230cb0: f3 03 00 aa              mov      x19, x0
0x00230cb4: 61 00 00 b4              cbz      x1, #0x230cc0
0x00230cb8: 20 80 5f f8              ldur     x0, [x1, #-8]
0x00230cbc: f5 60 f9 97              bl       #0x89090                   ; -> free@plt
0x00230cc0: a0 3b 40 f9              ldr      x0, [x29, #0x70]
0x00230cc4: 80 f7 ff b4              cbz      x0, #0x230bb4
0x00230cc8: 00 80 5f f8              ldur     x0, [x0, #-8]
0x00230ccc: f1 60 f9 97              bl       #0x89090                   ; -> free@plt
0x00230cd0: b9 ff ff 17              b        #0x230bb4                  ; -> cv_common_feature_comparator_get_distance+0x73c
0x00230cd4: 21 29 00 90              adrp     x1, #0x754000
0x00230cd8: 22 29 00 90              adrp     x2, #0x754000
0x00230cdc: c3 5c 02 b0              adrp     x3, #0x4dc9000
0x00230ce0: c5 5c 02 b0              adrp     x5, #0x4dc9000
0x00230ce4: 21 00 13 91              add      x1, x1, #0x4c0
0x00230ce8: 42 40 13 91              add      x2, x2, #0x4d0
0x00230cec: 63 80 2f 91              add      x3, x3, #0xbe0
0x00230cf0: a4 02 80 52              mov      w4, #0x15
0x00230cf4: a5 40 31 91              add      x5, x5, #0xc50
0x00230cf8: 80 00 80 52              mov      w0, #4
0x00230cfc: 29 5f f9 97              bl       #0x889a0                   ; -> __android_log_print@plt
0x00230d00: 84 5e f9 97              bl       #0x88710                   ; -> abort@plt
