; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : CornEngineProcess (0x313b8)
; start   : 0x313b8
; size    : 0x444
; bytes   : 0x444   instructions: 273
; generated: 2026-10-23T23:27:58

0x000313b8: ff 43 03 d1              sub      sp, sp, #0xd0
0x000313bc: fc 6f 07 a9              stp      x28, x27, [sp, #0x70]
0x000313c0: fa 67 08 a9              stp      x26, x25, [sp, #0x80]
0x000313c4: f8 5f 09 a9              stp      x24, x23, [sp, #0x90]
0x000313c8: f6 57 0a a9              stp      x22, x21, [sp, #0xa0]
0x000313cc: f4 4f 0b a9              stp      x20, x19, [sp, #0xb0]
0x000313d0: fd 7b 0c a9              stp      x29, x30, [sp, #0xc0]
0x000313d4: fd 03 03 91              add      x29, sp, #0xc0
0x000313d8: f3 03 00 aa              mov      x19, x0
0x000313dc: 6a 22 43 b9              ldr      w10, [x19, #0x320]
0x000313e0: 69 c6 43 b9              ldr      w9, [x19, #0x3c4]
0x000313e4: 68 ca 43 b9              ldr      w8, [x19, #0x3c8]
0x000313e8: f5 03 02 aa              mov      x21, x2
0x000313ec: 4a 05 00 11              add      w10, w10, #1
0x000313f0: 6a 22 03 b9              str      w10, [x19, #0x320]
0x000313f4: f5 01 00 b4              cbz      x21, #0x31430
0x000313f8: 6a d6 83 b9              ldrsw    x10, [x19, #0x3d4]
0x000313fc: eb e7 02 b2              mov      x11, #-0x3333333333333334
0x00031400: ab 99 99 f2              movk     x11, #0xcccd
0x00031404: ab 7e cb 9b              umulh    x11, x21, x11
0x00031408: 6b fd 48 d3              lsr      x11, x11, #8
0x0003140c: 5f 01 15 eb              cmp      x10, x21
0x00031410: 6a 05 00 51              sub      w10, w11, #1
0x00031414: eb 13 00 f9              str      x11, [sp, #0x20]
0x00031418: c9 02 00 54              b.ls     #0x31470
0x0003141c: bf 7e 07 f1              cmp      x21, #0x1df
0x00031420: 69 09 00 54              b.ls     #0x3154c
0x00031424: ea 3f 00 b9              str      w10, [sp, #0x3c]
0x00031428: 15 50 80 52              mov      w21, #0x280
0x0003142c: 4f 00 00 14              b        #0x31568                   ; -> CornEngineProcess+0x1b0
0x00031430: 6a d6 43 b9              ldr      w10, [x19, #0x3d4]
0x00031434: 74 76 41 f9              ldr      x20, [x19, #0x2e8]
0x00031438: 76 7a 41 f9              ldr      x22, [x19, #0x2f0]
0x0003143c: 77 92 81 b9              ldrsw    x23, [x19, #0x190]
0x00031440: e9 03 00 32              mov      w9, #1
0x00031444: 0b 50 80 52              mov      w11, #0x280
0x00031448: 5f 01 0a 71              cmp      w10, #0x280
0x0003144c: 69 6e 03 b9              str      w9, [x19, #0x36c]
0x00031450: 49 31 8b 1a              csel     w9, w10, w11, lo
0x00031454: 3f fd 09 71              cmp      w9, #0x27f
0x00031458: 69 d6 03 b9              str      w9, [x19, #0x3d4]
0x0003145c: ec 00 00 54              b.gt     #0x31478
0x00031460: e8 7e 08 1b              mul      w8, w23, w8
0x00031464: c1 0a 17 8b              add      x1, x22, x23, lsl #2
0x00031468: 08 79 1f 53              lsl      w8, w8, #1
0x0003146c: 08 00 00 14              b        #0x3148c                   ; -> CornEngineProcess+0xd4
0x00031470: ea 3f 00 b9              str      w10, [sp, #0x3c]
0x00031474: 3d 00 00 14              b        #0x31568                   ; -> CornEngineProcess+0x1b0
0x00031478: 08 79 1f 53              lsl      w8, w8, #1
0x0003147c: 09 09 00 11              add      w9, w8, #2
0x00031480: 29 7d 17 1b              mul      w9, w9, w23
0x00031484: 08 7d 17 1b              mul      w8, w8, w23
0x00031488: c1 ca 29 8b              add      x1, x22, w9, sxtw #2
0x0003148c: 02 7d 7e 93              sbfiz    x2, x8, #2, #0x20
0x00031490: e0 03 14 aa              mov      x0, x20
0x00031494: 7b 71 ff 97              bl       #0xda80                    ; -> memcpy@plt
0x00031498: 61 ca 43 b9              ldr      w1, [x19, #0x3c8]
0x0003149c: 3f 04 00 71              cmp      w1, #1
0x000314a0: eb 04 00 54              b.lt     #0x3153c
0x000314a4: f9 03 1f aa              mov      x25, xzr
0x000314a8: f5 f6 7e d3              lsl      x21, x23, #2
0x000314ac: f8 03 00 32              mov      w24, #1
0x000314b0: fa 03 19 aa              mov      x26, x25
0x000314b4: 28 78 1f 53              lsl      w8, w1, #1
0x000314b8: e9 03 00 32              mov      w9, #1
0x000314bc: ea 7e 1a 1b              mul      w10, w23, w26
0x000314c0: 29 78 1f 33              bfi      w9, w1, #1, #0x1f
0x000314c4: 08 01 1a 4b              sub      w8, w8, w26
0x000314c8: 4b 7f 17 9b              mul      x11, x26, x23
0x000314cc: 49 7d 09 1b              mul      w9, w10, w9
0x000314d0: 08 7d 17 1b              mul      w8, w8, w23
0x000314d4: 81 0a 0b 8b              add      x1, x20, x11, lsl #2
0x000314d8: c0 ca 29 8b              add      x0, x22, w9, sxtw #2
0x000314dc: 02 7d 7e 93              sbfiz    x2, x8, #2, #0x20
0x000314e0: 59 07 00 91              add      x25, x26, #1
0x000314e4: 67 71 ff 97              bl       #0xda80                    ; -> memcpy@plt
0x000314e8: fb 03 1f 2a              mov      w27, wzr
0x000314ec: 68 ca 43 b9              ldr      w8, [x19, #0x3c8]
0x000314f0: e9 03 00 32              mov      w9, #1
0x000314f4: e2 03 15 aa              mov      x2, x21
0x000314f8: 0a 79 1f 53              lsl      w10, w8, #1
0x000314fc: 09 79 1f 33              bfi      w9, w8, #1, #0x1f
0x00031500: 48 2b 09 1b              madd     w8, w26, w9, w10
0x00031504: 49 05 00 51              sub      w9, w10, #1
0x00031508: 68 03 08 0b              add      w8, w27, w8
0x0003150c: 29 7d 17 1b              mul      w9, w9, w23
0x00031510: 08 7d 17 1b              mul      w8, w8, w23
0x00031514: c0 ca 28 8b              add      x0, x22, w8, sxtw #2
0x00031518: 81 ca 29 8b              add      x1, x20, w9, sxtw #2
0x0003151c: 59 71 ff 97              bl       #0xda80                    ; -> memcpy@plt
0x00031520: 7b 07 00 51              sub      w27, w27, #1
0x00031524: 08 03 1b 0b              add      w8, w24, w27
0x00031528: 28 fe ff 35              cbnz     w8, #0x314ec
0x0003152c: 61 ca 83 b9              ldrsw    x1, [x19, #0x3c8]
0x00031530: 18 07 00 11              add      w24, w24, #1
0x00031534: 3f 03 01 eb              cmp      x25, x1
0x00031538: cb fb ff 54              b.lt     #0x314b0
0x0003153c: e0 03 13 aa              mov      x0, x19
0x00031540: 60 6f ff 97              bl       #0xd2c0                    ; -> CornDecode@plt
0x00031544: e0 03 1f 2a              mov      w0, wzr
0x00031548: a5 00 00 14              b        #0x317dc                   ; -> CornEngineProcess+0x424
0x0003154c: bf 82 02 f1              cmp      x21, #0xa0
0x00031550: e0 03 1f 2a              mov      w0, wzr
0x00031554: 43 14 00 54              b.lo     #0x317dc
0x00031558: ea 03 00 32              mov      w10, #1
0x0003155c: 15 28 80 52              mov      w21, #0x140
0x00031560: e0 3f 00 b9              str      w0, [sp, #0x3c]
0x00031564: ea 13 00 f9              str      x10, [sp, #0x20]
0x00031568: 60 ae 41 f9              ldr      x0, [x19, #0x358]
0x0003156c: 08 01 09 0b              add      w8, w8, w9
0x00031570: e2 03 15 aa              mov      x2, x21
0x00031574: e8 3b 00 b9              str      w8, [sp, #0x38]
0x00031578: 14 05 00 11              add      w20, w8, #1
0x0003157c: 41 71 ff 97              bl       #0xda80                    ; -> memcpy@plt
0x00031580: 69 ae 41 f9              ldr      x9, [x19, #0x358]
0x00031584: 6a 42 03 91              add      x10, x19, #0xd0
0x00031588: 75 62 03 b9              str      w21, [x19, #0x360]
0x0003158c: bf 02 0a f1              cmp      x21, #0x280
0x00031590: a9 83 1a f8              stur     x9, [x29, #-0x58]
0x00031594: 09 50 80 52              mov      w9, #0x280
0x00031598: ea 2f 00 f9              str      x10, [sp, #0x58]
0x0003159c: 6a 22 06 91              add      x10, x19, #0x188
0x000315a0: ea 2b 00 f9              str      x10, [sp, #0x50]
0x000315a4: 36 81 95 9a              csel     x22, x9, x21, hi
0x000315a8: 8a 7e 40 93              sxtw     x10, w20
0x000315ac: e8 03 1f aa              mov      x8, xzr
0x000315b0: 79 02 07 91              add      x25, x19, #0x1c0
0x000315b4: 76 d6 03 b9              str      w22, [x19, #0x3d4]
0x000315b8: ea d7 00 a9              stp      x10, x21, [sp, #8]
0x000315bc: ea 03 14 2a              mov      w10, w20
0x000315c0: ea 0f 00 f9              str      x10, [sp, #0x18]
0x000315c4: 0a 01 0a 91              add      x10, x8, #0x280
0x000315c8: bf 02 0a eb              cmp      x21, x10
0x000315cc: a8 02 08 cb              sub      x8, x21, x8
0x000315d0: a0 32 49 fa              ccmp     x21, x9, #0, lo
0x000315d4: 16 81 96 9a              csel     x22, x8, x22, hi
0x000315d8: ea 17 00 f9              str      x10, [sp, #0x28]
0x000315dc: d6 0e 00 b4              cbz      x22, #0x317b4
0x000315e0: f7 03 1f aa              mov      x23, xzr
0x000315e4: e8 03 1f 2a              mov      w8, wzr
0x000315e8: d4 fe 41 d3              lsr      x20, x22, #1
0x000315ec: c9 fe 42 d3              lsr      x9, x22, #2
0x000315f0: e9 33 00 f9              str      x9, [sp, #0x60]
0x000315f4: f6 53 04 a9              stp      x22, x20, [sp, #0x40]
0x000315f8: 03 00 00 14              b        #0x31604                   ; -> CornEngineProcess+0x24c
0x000315fc: 18 07 00 11              add      w24, w24, #1
0x00031600: 67 00 00 14              b        #0x3179c                   ; -> CornEngineProcess+0x3e4
0x00031604: 69 d2 43 b9              ldr      w9, [x19, #0x3d0]
0x00031608: aa 83 5a f8              ldur     x10, [x29, #-0x58]
0x0003160c: 1f 01 00 71              cmp      w8, #0
0x00031610: 08 a5 88 1a              cinc     w8, w8, lt
0x00031614: 08 7d 01 13              asr      w8, w8, #1
0x00031618: 40 c5 28 8b              add      x0, x10, w8, sxtw #1
0x0003161c: 88 02 09 0b              add      w8, w20, w9
0x00031620: 68 d2 03 b9              str      w8, [x19, #0x3d0]
0x00031624: e2 87 45 a9              ldp      x2, x1, [sp, #0x58]
0x00031628: e3 03 13 aa              mov      x3, x19
0x0003162c: 99 6f ff 97              bl       #0xd490                    ; -> CornSpectProcessPack@plt
0x00031630: a0 0b 00 34              cbz      w0, #0x317a4
0x00031634: 60 6a 40 f9              ldr      x0, [x19, #0xd0]
0x00031638: 61 da 40 b9              ldr      w1, [x19, #0xd8]
0x0003163c: e2 2b 40 f9              ldr      x2, [sp, #0x50]
0x00031640: 76 c6 40 f9              ldr      x22, [x19, #0x188]
0x00031644: 7b 76 41 f9              ldr      x27, [x19, #0x2e8]
0x00031648: 75 7a 41 f9              ldr      x21, [x19, #0x2f0]
0x0003164c: 7a fa 42 b9              ldr      w26, [x19, #0x2f8]
0x00031650: 78 ca 43 b9              ldr      w24, [x19, #0x3c8]
0x00031654: 7c 92 81 b9              ldrsw    x28, [x19, #0x190]
0x00031658: e3 03 13 aa              mov      x3, x19
0x0003165c: d9 70 ff 97              bl       #0xd9c0                    ; -> CornMfccCompute@plt
0x00031660: e0 0b 00 35              cbnz     w0, #0x317dc
0x00031664: 62 96 41 b9              ldr      w2, [x19, #0x194]
0x00031668: e0 03 16 aa              mov      x0, x22
0x0003166c: e1 03 1c 2a              mov      w1, w28
0x00031670: e3 03 1c 2a              mov      w3, w28
0x00031674: e4 03 19 aa              mov      x4, x25
0x00031678: de 70 ff 97              bl       #0xd9f0                    ; -> CornCmvnCompute@plt
0x0003167c: 00 0b 00 35              cbnz     w0, #0x317dc
0x00031680: 7a 07 00 34              cbz      w26, #0x3176c
0x00031684: e8 3f 40 b9              ldr      w8, [sp, #0x3c]
0x00031688: 21 03 40 f9              ldr      x1, [x25]
0x0003168c: 96 f7 7e d3              lsl      x22, x28, #2
0x00031690: e2 03 16 aa              mov      x2, x22
0x00031694: 14 05 18 0b              add      w20, w8, w24, lsl #1
0x00031698: 88 7f 1a 1b              mul      w8, w28, w26
0x0003169c: 60 cb 28 8b              add      x0, x27, w8, sxtw #2
0x000316a0: f8 70 ff 97              bl       #0xda80                    ; -> memcpy@plt
0x000316a4: 5f 03 14 6b              cmp      w26, w20
0x000316a8: 58 07 00 11              add      w24, w26, #1
0x000316ac: 6b 07 00 54              b.lt     #0x31798
0x000316b0: 68 d2 43 b9              ldr      w8, [x19, #0x3d0]
0x000316b4: f4 27 40 f9              ldr      x20, [sp, #0x48]
0x000316b8: 1f 01 05 71              cmp      w8, #0x140
0x000316bc: 0b 07 00 54              b.lt     #0x3179c
0x000316c0: e8 3b 40 b9              ldr      w8, [sp, #0x38]
0x000316c4: f8 1b 00 f9              str      x24, [sp, #0x30]
0x000316c8: 28 03 f8 37              tbnz     w8, #0x1f, #0x3172c
0x000316cc: f4 0f 40 f9              ldr      x20, [sp, #0x18]
0x000316d0: f8 03 1f aa              mov      x24, xzr
0x000316d4: a0 02 18 8b              add      x0, x21, x24
0x000316d8: 61 03 18 8b              add      x1, x27, x24
0x000316dc: e2 03 16 aa              mov      x2, x22
0x000316e0: e8 70 ff 97              bl       #0xda80                    ; -> memcpy@plt
0x000316e4: 94 06 00 d1              sub      x20, x20, #1
0x000316e8: 18 03 16 8b              add      x24, x24, x22
0x000316ec: 54 ff ff b5              cbnz     x20, #0x316d4
0x000316f0: e8 3b 40 b9              ldr      w8, [sp, #0x38]
0x000316f4: c8 01 f8 37              tbnz     w8, #0x1f, #0x3172c
0x000316f8: e8 07 40 f9              ldr      x8, [sp, #8]
0x000316fc: fa 0f 40 f9              ldr      x26, [sp, #0x18]
0x00031700: f8 03 1f aa              mov      x24, xzr
0x00031704: 74 0b 1c 8b              add      x20, x27, x28, lsl #2
0x00031708: 08 7d 1c 9b              mul      x8, x8, x28
0x0003170c: b5 0a 08 8b              add      x21, x21, x8, lsl #2
0x00031710: a0 02 18 8b              add      x0, x21, x24
0x00031714: 81 02 18 8b              add      x1, x20, x24
0x00031718: e2 03 16 aa              mov      x2, x22
0x0003171c: d9 70 ff 97              bl       #0xda80                    ; -> memcpy@plt
0x00031720: 5a 07 00 d1              sub      x26, x26, #1
0x00031724: 18 03 16 8b              add      x24, x24, x22
0x00031728: 5a ff ff b5              cbnz     x26, #0x31710
0x0003172c: f4 13 40 f9              ldr      x20, [sp, #0x20]
0x00031730: e0 03 13 aa              mov      x0, x19
0x00031734: e1 03 14 2a              mov      w1, w20
0x00031738: e2 6e ff 97              bl       #0xd2c0                    ; -> CornDecode@plt
0x0003173c: f8 1b 40 f9              ldr      x24, [sp, #0x30]
0x00031740: 88 7f 14 1b              mul      w8, w28, w20
0x00031744: 61 cb 28 8b              add      x1, x27, w8, sxtw #2
0x00031748: e0 03 1b aa              mov      x0, x27
0x0003174c: 18 03 14 4b              sub      w24, w24, w20
0x00031750: 08 7f 1c 1b              mul      w8, w24, w28
0x00031754: 02 7d 7e 93              sbfiz    x2, x8, #2, #0x20
0x00031758: 02 70 ff 97              bl       #0xd760                    ; -> memmove@plt
0x0003175c: 68 d2 43 b9              ldr      w8, [x19, #0x3d0]
0x00031760: 08 01 05 51              sub      w8, w8, #0x140
0x00031764: 68 d2 03 b9              str      w8, [x19, #0x3d0]
0x00031768: 0c 00 00 14              b        #0x31798                   ; -> CornEngineProcess+0x3e0
0x0003176c: 98 f4 ff 37              tbnz     w24, #0x1f, #0x315fc
0x00031770: 18 07 00 11              add      w24, w24, #1
0x00031774: 96 f7 7e d3              lsl      x22, x28, #2
0x00031778: f4 03 18 aa              mov      x20, x24
0x0003177c: 21 03 40 f9              ldr      x1, [x25]
0x00031780: e0 03 1b aa              mov      x0, x27
0x00031784: e2 03 16 aa              mov      x2, x22
0x00031788: be 70 ff 97              bl       #0xda80                    ; -> memcpy@plt
0x0003178c: 94 06 00 d1              sub      x20, x20, #1
0x00031790: 7b 03 16 8b              add      x27, x27, x22
0x00031794: 54 ff ff b5              cbnz     x20, #0x3177c
0x00031798: f4 27 40 f9              ldr      x20, [sp, #0x48]
0x0003179c: 78 fa 02 b9              str      w24, [x19, #0x2f8]
0x000317a0: f6 23 40 f9              ldr      x22, [sp, #0x40]
0x000317a4: e8 02 14 8b              add      x8, x23, x20
0x000317a8: 17 7d 40 93              sxtw     x23, w8
0x000317ac: df 02 17 eb              cmp      x22, x23
0x000317b0: a8 f2 ff 54              b.hi     #0x31604
0x000317b4: a8 83 5a f8              ldur     x8, [x29, #-0x58]
0x000317b8: f5 0b 40 f9              ldr      x21, [sp, #0x10]
0x000317bc: e9 17 40 f9              ldr      x9, [sp, #0x28]
0x000317c0: 08 01 0a 91              add      x8, x8, #0x280
0x000317c4: a8 83 1a f8              stur     x8, [x29, #-0x58]
0x000317c8: bf 02 09 eb              cmp      x21, x9
0x000317cc: e8 03 09 aa              mov      x8, x9
0x000317d0: 09 50 80 52              mov      w9, #0x280
0x000317d4: 88 ef ff 54              b.hi     #0x315c4
0x000317d8: 5b ff ff 17              b        #0x31544                   ; -> CornEngineProcess+0x18c
0x000317dc: fd 7b 4c a9              ldp      x29, x30, [sp, #0xc0]
0x000317e0: f4 4f 4b a9              ldp      x20, x19, [sp, #0xb0]
0x000317e4: f6 57 4a a9              ldp      x22, x21, [sp, #0xa0]
0x000317e8: f8 5f 49 a9              ldp      x24, x23, [sp, #0x90]
0x000317ec: fa 67 48 a9              ldp      x26, x25, [sp, #0x80]
0x000317f0: fc 6f 47 a9              ldp      x28, x27, [sp, #0x70]
0x000317f4: ff 43 03 91              add      sp, sp, #0xd0
0x000317f8: c0 03 5f d6              ret      
