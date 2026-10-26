; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_paymentservice/inventory/libs/lib/arm64-v8a/libdigit_ocr.so
; target  : address 0x14fd8
; start   : 0x14fd8
; size    : 0x478
; bytes   : 0x478   instructions: 286
; generated: 2026-10-23T23:16:29

0x00014fd8: ff 43 02 d1              sub      sp, sp, #0x90
0x00014fdc: 5f 50 00 71              cmp      w2, #0x14
0x00014fe0: e8 a7 05 6d              stp      d8, d9, [sp, #0x58]
0x00014fe4: ec 3f 00 fd              str      d12, [sp, #0x78]
0x00014fe8: ea af 06 6d              stp      d10, d11, [sp, #0x68]
0x00014fec: f3 53 00 a9              stp      x19, x20, [sp]
0x00014ff0: f5 5b 01 a9              stp      x21, x22, [sp, #0x10]
0x00014ff4: f7 63 02 a9              stp      x23, x24, [sp, #0x20]
0x00014ff8: f9 6b 03 a9              stp      x25, x26, [sp, #0x30]
0x00014ffc: fb 73 04 a9              stp      x27, x28, [sp, #0x40]
0x00015000: fe 2b 00 f9              str      x30, [sp, #0x50]
0x00015004: 21 22 00 54              b.ne     #0x15448
0x00015008: 7f 80 00 71              cmp      w3, #0x20
0x0001500c: f4 07 9f 1a              cset     w20, ne
0x00015010: d4 21 00 35              cbnz     w20, #0x15448
0x00015014: 00 48 80 d2              mov      x0, #0x240
0x00015018: fc 03 04 aa              mov      x28, x4
0x0001501c: f3 03 01 aa              mov      x19, x1
0x00015020: 5c fb ff 97              bl       #0x13d90                   ; -> malloc@plt
0x00015024: f8 03 00 aa              mov      x24, x0
0x00015028: 00 40 81 d2              mov      x0, #0xa00
0x0001502c: 59 fb ff 97              bl       #0x13d90                   ; -> malloc@plt
0x00015030: f9 03 00 aa              mov      x25, x0
0x00015034: 00 40 81 d2              mov      x0, #0xa00
0x00015038: 56 fb ff 97              bl       #0x13d90                   ; -> malloc@plt
0x0001503c: f5 03 00 aa              mov      x21, x0
0x00015040: 0c 00 67 9e              fmov     d12, x0
0x00015044: e1 03 14 2a              mov      w1, w20
0x00015048: 02 40 81 d2              mov      x2, #0xa00
0x0001504c: e0 03 19 aa              mov      x0, x25
0x00015050: 4c fb ff 97              bl       #0x13d80                   ; -> memset@plt
0x00015054: e1 03 14 2a              mov      w1, w20
0x00015058: 02 40 81 d2              mov      x2, #0xa00
0x0001505c: e0 03 15 aa              mov      x0, x21
0x00015060: 48 fb ff 97              bl       #0x13d80                   ; -> memset@plt
0x00015064: e0 03 18 aa              mov      x0, x24
0x00015068: e1 03 14 2a              mov      w1, w20
0x0001506c: 02 48 80 d2              mov      x2, #0x240
0x00015070: 44 fb ff 97              bl       #0x13d80                   ; -> memset@plt
0x00015074: 02 e6 02 4f              movi     v2.16b, #0x50
0x00015078: e0 03 13 aa              mov      x0, x19
0x0001507c: 21 e4 00 4f              movi     v1.16b, #1
0x00015080: 68 02 0a 91              add      x8, x19, #0x280
0x00015084: 00 00 c0 3d              ldr      q0, [x0]
0x00015088: 01 40 40 39              ldrb     w1, [x0, #0x10]
0x0001508c: 04 44 40 39              ldrb     w4, [x0, #0x11]
0x00015090: 02 48 40 39              ldrb     w2, [x0, #0x12]
0x00015094: 3f 40 01 71              cmp      w1, #0x50
0x00015098: 05 4c 40 39              ldrb     w5, [x0, #0x13]
0x0001509c: e1 93 9f 5a              csetm    w1, hi
0x000150a0: 00 34 22 6e              cmhi     v0.16b, v0.16b, v2.16b
0x000150a4: 9f 40 01 71              cmp      w4, #0x50
0x000150a8: e4 93 9f 5a              csetm    w4, hi
0x000150ac: 5f 40 01 71              cmp      w2, #0x50
0x000150b0: e2 93 9f 5a              csetm    w2, hi
0x000150b4: bf 40 01 71              cmp      w5, #0x50
0x000150b8: 01 40 00 39              strb     w1, [x0, #0x10]
0x000150bc: e1 93 9f 5a              csetm    w1, hi
0x000150c0: 00 1c 21 4e              and      v0.16b, v0.16b, v1.16b
0x000150c4: 04 44 00 39              strb     w4, [x0, #0x11]
0x000150c8: 02 48 00 39              strb     w2, [x0, #0x12]
0x000150cc: 01 4c 00 39              strb     w1, [x0, #0x13]
0x000150d0: 00 b8 20 6e              neg      v0.16b, v0.16b
0x000150d4: 00 00 80 3d              str      q0, [x0]
0x000150d8: 00 50 00 91              add      x0, x0, #0x14
0x000150dc: 1f 00 08 eb              cmp      x0, x8
0x000150e0: 21 fd ff 54              b.ne     #0x15084
0x000150e4: 60 06 00 91              add      x0, x19, #1
0x000150e8: 21 13 00 91              add      x1, x25, #4
0x000150ec: 6b 06 0a 91              add      x11, x19, #0x281
0x000150f0: 02 04 00 d1              sub      x2, x0, #1
0x000150f4: 00 00 c0 3d              ldr      q0, [x0]
0x000150f8: e6 03 01 aa              mov      x6, x1
0x000150fc: 2a 80 00 91              add      x10, x1, #0x20
0x00015100: 41 00 c0 3d              ldr      q1, [x2]
0x00015104: 00 50 00 91              add      x0, x0, #0x14
0x00015108: 02 c0 5f 38              ldurb    w2, [x0, #-4]
0x0001510c: 29 c0 00 91              add      x9, x1, #0x30
0x00015110: 21 40 01 91              add      x1, x1, #0x50
0x00015114: 1f 00 0b eb              cmp      x0, x11
0x00015118: 02 a4 08 2f              ushll    v2.8h, v0.8b, #0
0x0001511c: 00 a4 08 6f              ushll2   v0.8h, v0.16b, #0
0x00015120: 07 d0 5f 38              ldurb    w7, [x0, #-3]
0x00015124: 23 a4 08 2f              ushll    v3.8h, v1.8b, #0
0x00015128: 05 b0 5f 38              ldurb    w5, [x0, #-5]
0x0001512c: 21 a4 08 6f              ushll2   v1.8h, v1.16b, #0
0x00015130: 04 e0 5f 38              ldurb    w4, [x0, #-2]
0x00015134: 45 00 05 4b              sub      w5, w2, w5
0x00015138: e2 00 02 4b              sub      w2, w7, w2
0x0001513c: 84 00 07 4b              sub      w4, w4, w7
0x00015140: 44 20 63 2e              usubl    v4.4s, v2.4h, v3.4h
0x00015144: 42 20 63 6e              usubl2   v2.4s, v2.8h, v3.8h
0x00015148: 03 20 61 2e              usubl    v3.4s, v0.4h, v1.4h
0x0001514c: 00 20 61 6e              usubl2   v0.4s, v0.8h, v1.8h
0x00015150: c4 04 81 3c              str      q4, [x6], #0x10
0x00015154: c2 00 80 3d              str      q2, [x6]
0x00015158: 43 01 80 3d              str      q3, [x10]
0x0001515c: 20 01 80 3d              str      q0, [x9]
0x00015160: 25 00 1f b8              stur     w5, [x1, #-0x10]
0x00015164: 22 40 1f b8              stur     w2, [x1, #-0xc]
0x00015168: 24 80 1f b8              stur     w4, [x1, #-8]
0x0001516c: 21 fc ff 54              b.ne     #0x150f0
0x00015170: 81 01 66 9e              fmov     x1, d12
0x00015174: 60 52 00 91              add      x0, x19, #0x14
0x00015178: 21 40 01 91              add      x1, x1, #0x50
0x0001517c: 02 50 00 d1              sub      x2, x0, #0x14
0x00015180: 00 00 c0 3d              ldr      q0, [x0]
0x00015184: e7 03 01 aa              mov      x7, x1
0x00015188: 06 40 40 39              ldrb     w6, [x0, #0x10]
0x0001518c: 41 00 c0 3d              ldr      q1, [x2]
0x00015190: 00 50 00 91              add      x0, x0, #0x14
0x00015194: 2a 80 00 91              add      x10, x1, #0x20
0x00015198: 29 c0 00 91              add      x9, x1, #0x30
0x0001519c: 21 40 01 91              add      x1, x1, #0x50
0x000151a0: 1f 00 08 eb              cmp      x0, x8
0x000151a4: 02 a4 08 2f              ushll    v2.8h, v0.8b, #0
0x000151a8: 0b 80 5e 38              ldurb    w11, [x0, #-0x18]
0x000151ac: 00 a4 08 6f              ushll2   v0.8h, v0.16b, #0
0x000151b0: 05 d0 5f 38              ldurb    w5, [x0, #-3]
0x000151b4: 23 a4 08 2f              ushll    v3.8h, v1.8b, #0
0x000151b8: c6 00 0b 4b              sub      w6, w6, w11
0x000151bc: 21 a4 08 6f              ushll2   v1.8h, v1.16b, #0
0x000151c0: 0d 90 5e 38              ldurb    w13, [x0, #-0x17]
0x000151c4: 04 e0 5f 38              ldurb    w4, [x0, #-2]
0x000151c8: 0c a0 5e 38              ldurb    w12, [x0, #-0x16]
0x000151cc: a5 00 0d 4b              sub      w5, w5, w13
0x000151d0: 44 20 63 2e              usubl    v4.4s, v2.4h, v3.4h
0x000151d4: 02 f0 5f 38              ldurb    w2, [x0, #-1]
0x000151d8: 42 20 63 6e              usubl2   v2.4s, v2.8h, v3.8h
0x000151dc: 0b b0 5e 38              ldurb    w11, [x0, #-0x15]
0x000151e0: 03 20 61 2e              usubl    v3.4s, v0.4h, v1.4h
0x000151e4: 84 00 0c 4b              sub      w4, w4, w12
0x000151e8: 00 20 61 6e              usubl2   v0.4s, v0.8h, v1.8h
0x000151ec: 42 00 0b 4b              sub      w2, w2, w11
0x000151f0: e4 04 81 3c              str      q4, [x7], #0x10
0x000151f4: e2 00 80 3d              str      q2, [x7]
0x000151f8: 43 01 80 3d              str      q3, [x10]
0x000151fc: 20 01 80 3d              str      q0, [x9]
0x00015200: 26 00 1f b8              stur     w6, [x1, #-0x10]
0x00015204: 25 40 1f b8              stur     w5, [x1, #-0xc]
0x00015208: 24 80 1f b8              stur     w4, [x1, #-8]
0x0001520c: 22 c0 1f b8              stur     w2, [x1, #-4]
0x00015210: 61 fb ff 54              b.ne     #0x1517c
0x00015214: eb 11 00 5c              ldr      d11, #0x15450
0x00015218: 16 00 80 d2              mov      x22, #0
0x0001521c: ea 11 00 5c              ldr      d10, #0x15458
0x00015220: f7 03 16 2a              mov      w23, w22
0x00015224: e9 11 00 1c              ldr      s9, #0x15460
0x00015228: e8 11 00 1c              ldr      s8, #0x15464
0x0001522c: 80 01 66 9e              fmov     x0, d12
0x00015230: f3 7e 03 13              asr      w19, w23, #3
0x00015234: 1b 00 80 d2              mov      x27, #0
0x00015238: 34 03 16 8b              add      x20, x25, x22
0x0001523c: 73 76 1e 53              lsl      w19, w19, #2
0x00015240: 15 00 16 8b              add      x21, x0, x22
0x00015244: a0 7a 7b b8              ldr      w0, [x21, x27, lsl #2]
0x00015248: fa 03 1b 2a              mov      w26, w27
0x0001524c: 81 7a 7b b8              ldr      w1, [x20, x27, lsl #2]
0x00015250: 00 00 22 1e              scvtf    s0, w0
0x00015254: 21 00 62 1e              scvtf    d1, w1
0x00015258: 00 c0 22 1e              fcvt     d0, s0
0x0001525c: cd fb ff 97              bl       #0x14190                   ; -> atan2@plt
0x00015260: 00 28 4b 1f              fmadd    d0, d0, d11, d10
0x00015264: e2 cc 8c 52              mov      w2, #0x6667
0x00015268: c2 cc ac 72              movk     w2, #0x6666, lsl #16
0x0001526c: 62 7f 22 9b              smull    x2, w27, w2
0x00015270: 7b 07 00 91              add      x27, x27, #1
0x00015274: 42 fc 60 d3              lsr      x2, x2, #0x20
0x00015278: 40 7c 01 13              asr      w0, w2, #1
0x0001527c: 01 7c 9a 4b              sub      w1, w0, w26, asr #31
0x00015280: 00 40 62 1e              fcvt     s0, d0
0x00015284: 21 00 13 0b              add      w1, w1, w19
0x00015288: 21 0c 01 0b              add      w1, w1, w1, lsl #3
0x0001528c: 10 20 29 1e              fcmpe    s0, s9
0x00015290: 4b 00 00 54              b.lt     #0x15298
0x00015294: 00 38 29 1e              fsub     s0, s0, s9
0x00015298: 00 18 28 1e              fdiv     s0, s0, s8
0x0001529c: 7f 53 00 f1              cmp      x27, #0x14
0x000152a0: 02 00 38 1e              fcvtzs   w2, s0
0x000152a4: 21 00 02 0b              add      w1, w1, w2
0x000152a8: 21 7c 7e 93              sbfiz    x1, x1, #2, #0x20
0x000152ac: 02 6b 61 b8              ldr      w2, [x24, x1]
0x000152b0: 42 04 00 11              add      w2, w2, #1
0x000152b4: 02 6b 21 b8              str      w2, [x24, x1]
0x000152b8: 61 fc ff 54              b.ne     #0x15244
0x000152bc: f7 06 00 11              add      w23, w23, #1
0x000152c0: d6 42 01 91              add      x22, x22, #0x50
0x000152c4: ff 82 00 71              cmp      w23, #0x20
0x000152c8: 21 fb ff 54              b.ne     #0x1522c
0x000152cc: f4 03 18 aa              mov      x20, x24
0x000152d0: 15 03 09 91              add      x21, x24, #0x240
0x000152d4: 17 00 f8 92              mov      x23, #0x3fffffffffffffff
0x000152d8: 08 00 00 14              b        #0x152f8                   ; -> _ZN12CFEATURE_HOG26ExtractGradientOrientationEPhiiRSt6vectorIfSaIfEE+0x320
0x000152dc: 53 00 00 b4              cbz      x19, #0x152e4
0x000152e0: 69 02 00 bd              str      s9, [x19]
0x000152e4: 94 12 00 91              add      x20, x20, #4
0x000152e8: 73 12 00 91              add      x19, x19, #4
0x000152ec: 9f 02 15 eb              cmp      x20, x21
0x000152f0: 93 07 00 f9              str      x19, [x28, #8]
0x000152f4: 40 05 00 54              b.eq     #0x1539c
0x000152f8: 81 02 40 b9              ldr      w1, [x20]
0x000152fc: 93 07 40 f9              ldr      x19, [x28, #8]
0x00015300: 80 0b 40 f9              ldr      x0, [x28, #0x10]
0x00015304: 20 00 22 1e              scvtf    s0, w1
0x00015308: 7f 02 00 eb              cmp      x19, x0
0x0001530c: 09 18 28 1e              fdiv     s9, s0, s8
0x00015310: 61 fe ff 54              b.ne     #0x152dc
0x00015314: 81 03 40 f9              ldr      x1, [x28]
0x00015318: 76 02 01 cb              sub      x22, x19, x1
0x0001531c: c2 fe 42 93              asr      x2, x22, #2
0x00015320: 22 06 00 b4              cbz      x2, #0x153e4
0x00015324: 5b f8 7f d3              lsl      x27, x2, #1
0x00015328: 7a 00 80 92              mov      x26, #-4
0x0001532c: 5f 00 1b eb              cmp      x2, x27
0x00015330: c8 05 00 54              b.hi     #0x153e8
0x00015334: 7f 03 17 eb              cmp      x27, x23
0x00015338: 88 05 00 54              b.hi     #0x153e8
0x0001533c: 5a f0 7d d3              lsl      x26, x2, #3
0x00015340: e4 03 16 aa              mov      x4, x22
0x00015344: 3b 05 00 b5              cbnz     x27, #0x153e8
0x00015348: 7f 03 16 ab              cmn      x27, x22
0x0001534c: 40 00 00 54              b.eq     #0x15354
0x00015350: 69 6b 36 bc              str      s9, [x27, x22]
0x00015354: 82 05 00 b5              cbnz     x2, #0x15404
0x00015358: 96 07 40 f9              ldr      x22, [x28, #8]
0x0001535c: 84 10 00 91              add      x4, x4, #4
0x00015360: 64 03 04 8b              add      x4, x27, x4
0x00015364: d6 02 13 cb              sub      x22, x22, x19
0x00015368: c0 fe 42 93              asr      x0, x22, #2
0x0001536c: 20 06 00 b5              cbnz     x0, #0x15430
0x00015370: 80 03 40 f9              ldr      x0, [x28]
0x00015374: 96 00 16 8b              add      x22, x4, x22
0x00015378: 40 00 00 b4              cbz      x0, #0x15380
0x0001537c: 2b 67 00 94              bl       #0x2f028                   ; -> _ZdlPv
0x00015380: 94 12 00 91              add      x20, x20, #4
0x00015384: 7a 03 1a 8b              add      x26, x27, x26
0x00015388: 9b 03 00 f9              str      x27, [x28]
0x0001538c: 9f 02 15 eb              cmp      x20, x21
0x00015390: 96 07 00 f9              str      x22, [x28, #8]
0x00015394: 9a 0b 00 f9              str      x26, [x28, #0x10]
0x00015398: 01 fb ff 54              b.ne     #0x152f8
0x0001539c: e0 03 19 aa              mov      x0, x25
0x000153a0: 50 fb ff 97              bl       #0x140e0                   ; -> free@plt
0x000153a4: 80 01 66 9e              fmov     x0, d12
0x000153a8: 4e fb ff 97              bl       #0x140e0                   ; -> free@plt
0x000153ac: e0 03 18 aa              mov      x0, x24
0x000153b0: 4c fb ff 97              bl       #0x140e0                   ; -> free@plt
0x000153b4: 00 00 80 52              mov      w0, #0
0x000153b8: f3 53 40 a9              ldp      x19, x20, [sp]
0x000153bc: e8 a7 45 6d              ldp      d8, d9, [sp, #0x58]
0x000153c0: f5 5b 41 a9              ldp      x21, x22, [sp, #0x10]
0x000153c4: ea af 46 6d              ldp      d10, d11, [sp, #0x68]
0x000153c8: f7 63 42 a9              ldp      x23, x24, [sp, #0x20]
0x000153cc: f9 6b 43 a9              ldp      x25, x26, [sp, #0x30]
0x000153d0: fb 73 44 a9              ldp      x27, x28, [sp, #0x40]
0x000153d4: fe 2b 40 f9              ldr      x30, [sp, #0x50]
0x000153d8: ec 3f 40 fd              ldr      d12, [sp, #0x78]
0x000153dc: ff 43 02 91              add      sp, sp, #0x90
0x000153e0: c0 03 5f d6              ret      
0x000153e4: 9a 00 80 d2              mov      x26, #4
0x000153e8: e0 03 1a aa              mov      x0, x26
0x000153ec: b2 6a 00 94              bl       #0x2feb4                   ; -> _Znwm
0x000153f0: fb 03 00 aa              mov      x27, x0
0x000153f4: 81 03 40 f9              ldr      x1, [x28]
0x000153f8: 64 02 01 cb              sub      x4, x19, x1
0x000153fc: 82 fc 42 93              asr      x2, x4, #2
0x00015400: d2 ff ff 17              b        #0x15348                   ; -> _ZN12CFEATURE_HOG26ExtractGradientOrientationEPhiiRSt6vectorIfSaIfEE+0x370
0x00015404: e2 03 04 aa              mov      x2, x4
0x00015408: e0 03 1b aa              mov      x0, x27
0x0001540c: e4 47 00 f9              str      x4, [sp, #0x88]
0x00015410: 68 fa ff 97              bl       #0x13db0                   ; -> memmove@plt
0x00015414: 96 07 40 f9              ldr      x22, [x28, #8]
0x00015418: e4 47 40 f9              ldr      x4, [sp, #0x88]
0x0001541c: d6 02 13 cb              sub      x22, x22, x19
0x00015420: c0 fe 42 93              asr      x0, x22, #2
0x00015424: 84 10 00 91              add      x4, x4, #4
0x00015428: 64 03 04 8b              add      x4, x27, x4
0x0001542c: 20 fa ff b4              cbz      x0, #0x15370
0x00015430: e0 03 04 aa              mov      x0, x4
0x00015434: e1 03 13 aa              mov      x1, x19
0x00015438: e2 03 16 aa              mov      x2, x22
0x0001543c: 5d fa ff 97              bl       #0x13db0                   ; -> memmove@plt
0x00015440: e4 03 00 aa              mov      x4, x0
0x00015444: cb ff ff 17              b        #0x15370                   ; -> _ZN12CFEATURE_HOG26ExtractGradientOrientationEPhiiRSt6vectorIfSaIfEE+0x398
0x00015448: 00 00 80 12              mov      w0, #-1
0x0001544c: db ff ff 17              b        #0x153b8                   ; -> _ZN12CFEATURE_HOG26ExtractGradientOrientationEPhiiRSt6vectorIfSaIfEE+0x3e0
