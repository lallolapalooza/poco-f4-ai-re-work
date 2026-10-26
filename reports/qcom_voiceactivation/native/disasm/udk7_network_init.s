; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libqvasml.so
; target  : address 0x5c044
; start   : 0x5c044
; size    : 0x68c
; bytes   : 0x68c   instructions: 419
; generated: 2026-10-23T23:25:24

0x0005c044: 3f 23 03 d5              paciasp  
0x0005c048: fd 7b ba a9              stp      x29, x30, [sp, #-0x60]!
0x0005c04c: fb 0b 00 f9              str      x27, [sp, #0x10]
0x0005c050: fa 67 02 a9              stp      x26, x25, [sp, #0x20]
0x0005c054: f8 5f 03 a9              stp      x24, x23, [sp, #0x30]
0x0005c058: f6 57 04 a9              stp      x22, x21, [sp, #0x40]
0x0005c05c: f4 4f 05 a9              stp      x20, x19, [sp, #0x50]
0x0005c060: fd 03 00 91              mov      x29, sp
0x0005c064: e8 03 02 2a              mov      w8, w2
0x0005c068: e1 03 00 b4              cbz      x1, #0x5c0e4
0x0005c06c: 1f 3d 00 71              cmp      w8, #0xf
0x0005c070: a9 03 00 54              b.ls     #0x5c0e4
0x0005c074: 23 00 40 b9              ldr      w3, [x1]
0x0005c078: f3 03 01 aa              mov      x19, x1
0x0005c07c: e9 66 89 52              mov      w9, #0x4b37
0x0005c080: 89 a8 aa 72              movk     w9, #0x5544, lsl #16
0x0005c084: 7f 00 09 6b              cmp      w3, w9
0x0005c088: e1 03 00 54              b.ne     #0x5c104
0x0005c08c: 64 0a 40 b9              ldr      w4, [x19, #8]
0x0005c090: 9f 00 08 6b              cmp      w4, w8
0x0005c094: c1 04 00 54              b.ne     #0x5c12c
0x0005c098: f7 03 13 aa              mov      x23, x19
0x0005c09c: e4 0e 41 b8              ldr      w4, [x23, #0x10]!
0x0005c0a0: 9f 20 06 71              cmp      w4, #0x188
0x0005c0a4: 21 05 00 54              b.ne     #0x5c148
0x0005c0a8: 68 3a 40 b9              ldr      w8, [x19, #0x38]
0x0005c0ac: 08 07 00 34              cbz      w8, #0x5c18c
0x0005c0b0: f4 03 00 aa              mov      x20, x0
0x0005c0b4: 60 62 05 91              add      x0, x19, #0x158
0x0005c0b8: 21 00 80 52              mov      w1, #1
0x0005c0bc: cd 2f 02 94              bl       #0xe7ff0
0x0005c0c0: 60 07 00 34              cbz      w0, #0x5c1ac
0x0005c0c4: 1f 20 03 d5              nop      
0x0005c0c8: 21 6a dc 10              adr      x1, #0x14e0c
0x0005c0cc: a2 fd ff b0              adrp     x2, #0x11000
0x0005c0d0: 42 70 21 91              add      x2, x2, #0x85c
0x0005c0d4: c0 00 80 52              mov      w0, #6
0x0005c0d8: 70 d6 02 94              bl       #0x111a98
0x0005c0dc: 36 00 80 52              mov      w22, #1
0x0005c0e0: 22 00 00 14              b        #0x5c168                   ; -> udk7_network_init+0x124
0x0005c0e4: 1f 20 03 d5              nop      
0x0005c0e8: 21 69 dc 10              adr      x1, #0x14e0c
0x0005c0ec: a2 fd ff b0              adrp     x2, #0x11000
0x0005c0f0: 42 b8 2f 91              add      x2, x2, #0xbee
0x0005c0f4: c0 00 80 52              mov      w0, #6
0x0005c0f8: e3 03 08 2a              mov      w3, w8
0x0005c0fc: 67 d6 02 94              bl       #0x111a98
0x0005c100: 19 00 00 14              b        #0x5c164                   ; -> udk7_network_init+0x120
0x0005c104: e4 66 89 52              mov      w4, #0x4b37
0x0005c108: 1f 20 03 d5              nop      
0x0005c10c: 01 68 dc 10              adr      x1, #0x14e0c
0x0005c110: c2 fd ff d0              adrp     x2, #0x16000
0x0005c114: 42 94 36 91              add      x2, x2, #0xda5
0x0005c118: c0 00 80 52              mov      w0, #6
0x0005c11c: 84 a8 aa 72              movk     w4, #0x5544, lsl #16
0x0005c120: 5e d6 02 94              bl       #0x111a98
0x0005c124: 16 01 80 52              mov      w22, #8
0x0005c128: 10 00 00 14              b        #0x5c168                   ; -> udk7_network_init+0x124
0x0005c12c: 1f 20 03 d5              nop      
0x0005c130: e1 66 dc 10              adr      x1, #0x14e0c
0x0005c134: a2 fd ff b0              adrp     x2, #0x11000
0x0005c138: 42 7c 0d 91              add      x2, x2, #0x35f
0x0005c13c: c0 00 80 52              mov      w0, #6
0x0005c140: e3 03 08 2a              mov      w3, w8
0x0005c144: 07 00 00 14              b        #0x5c160                   ; -> udk7_network_init+0x11c
0x0005c148: 1f 20 03 d5              nop      
0x0005c14c: 01 66 dc 10              adr      x1, #0x14e0c
0x0005c150: a2 fd ff 90              adrp     x2, #0x10000
0x0005c154: 42 98 3b 91              add      x2, x2, #0xee6
0x0005c158: c0 00 80 52              mov      w0, #6
0x0005c15c: 03 31 80 52              mov      w3, #0x188
0x0005c160: 4e d6 02 94              bl       #0x111a98
0x0005c164: f6 00 80 52              mov      w22, #7
0x0005c168: e0 03 16 2a              mov      w0, w22
0x0005c16c: f4 4f 45 a9              ldp      x20, x19, [sp, #0x50]
0x0005c170: f6 57 44 a9              ldp      x22, x21, [sp, #0x40]
0x0005c174: f8 5f 43 a9              ldp      x24, x23, [sp, #0x30]
0x0005c178: fa 67 42 a9              ldp      x26, x25, [sp, #0x20]
0x0005c17c: fb 0b 40 f9              ldr      x27, [sp, #0x10]
0x0005c180: fd 7b c6 a8              ldp      x29, x30, [sp], #0x60
0x0005c184: bf 23 03 d5              autiasp  
0x0005c188: c0 03 5f d6              ret      
0x0005c18c: 1f 20 03 d5              nop      
0x0005c190: e1 63 dc 10              adr      x1, #0x14e0c
0x0005c194: c2 fd ff d0              adrp     x2, #0x16000
0x0005c198: 42 5c 1e 91              add      x2, x2, #0x797
0x0005c19c: c0 00 80 52              mov      w0, #6
0x0005c1a0: 3e d6 02 94              bl       #0x111a98
0x0005c1a4: 36 01 80 52              mov      w22, #9
0x0005c1a8: f0 ff ff 17              b        #0x5c168                   ; -> udk7_network_init+0x124
0x0005c1ac: 75 62 06 91              add      x21, x19, #0x198
0x0005c1b0: 68 b2 40 f9              ldr      x8, [x19, #0x160]
0x0005c1b4: 60 a2 03 91              add      x0, x19, #0xe8
0x0005c1b8: 62 16 40 b9              ldr      w2, [x19, #0x14]
0x0005c1bc: e1 03 15 aa              mov      x1, x21
0x0005c1c0: e3 03 1f 2a              mov      w3, wzr
0x0005c1c4: e4 03 1f aa              mov      x4, xzr
0x0005c1c8: 00 01 3f d6              blr      x8
0x0005c1cc: c0 00 00 34              cbz      w0, #0x5c1e4
0x0005c1d0: 1f 20 03 d5              nop      
0x0005c1d4: c1 61 dc 10              adr      x1, #0x14e0c
0x0005c1d8: a2 fd ff b0              adrp     x2, #0x11000
0x0005c1dc: 42 f4 21 91              add      x2, x2, #0x87d
0x0005c1e0: 1c 00 00 14              b        #0x5c250                   ; -> udk7_network_init+0x20c
0x0005c1e4: 68 8a 42 29              ldp      w8, w2, [x19, #0x14]
0x0005c1e8: 60 02 04 91              add      x0, x19, #0x100
0x0005c1ec: e3 03 1f 2a              mov      w3, wzr
0x0005c1f0: e4 03 1f aa              mov      x4, xzr
0x0005c1f4: b5 02 08 8b              add      x21, x21, x8
0x0005c1f8: 68 b2 40 f9              ldr      x8, [x19, #0x160]
0x0005c1fc: e1 03 15 aa              mov      x1, x21
0x0005c200: 00 01 3f d6              blr      x8
0x0005c204: c0 00 00 34              cbz      w0, #0x5c21c
0x0005c208: 1f 20 03 d5              nop      
0x0005c20c: 01 60 dc 10              adr      x1, #0x14e0c
0x0005c210: c2 fd ff d0              adrp     x2, #0x16000
0x0005c214: 42 38 10 91              add      x2, x2, #0x40e
0x0005c218: 0e 00 00 14              b        #0x5c250                   ; -> udk7_network_init+0x20c
0x0005c21c: 68 0a 43 29              ldp      w8, w2, [x19, #0x18]
0x0005c220: 60 62 04 91              add      x0, x19, #0x118
0x0005c224: e3 03 1f 2a              mov      w3, wzr
0x0005c228: e4 03 1f aa              mov      x4, xzr
0x0005c22c: b5 02 08 8b              add      x21, x21, x8
0x0005c230: 68 b2 40 f9              ldr      x8, [x19, #0x160]
0x0005c234: e1 03 15 aa              mov      x1, x21
0x0005c238: 00 01 3f d6              blr      x8
0x0005c23c: 20 01 00 34              cbz      w0, #0x5c260
0x0005c240: 1f 20 03 d5              nop      
0x0005c244: 41 5e dc 10              adr      x1, #0x14e0c
0x0005c248: a2 fd ff d0              adrp     x2, #0x12000
0x0005c24c: 42 98 09 91              add      x2, x2, #0x266
0x0005c250: c0 00 80 52              mov      w0, #6
0x0005c254: 11 d6 02 94              bl       #0x111a98
0x0005c258: 16 01 80 52              mov      w22, #8
0x0005c25c: c3 ff ff 17              b        #0x5c168                   ; -> udk7_network_init+0x124
0x0005c260: 00 09 80 52              mov      w0, #0x48
0x0005c264: 21 00 80 52              mov      w1, #1
0x0005c268: 7a 1e 40 b9              ldr      w26, [x19, #0x1c]
0x0005c26c: 36 00 80 52              mov      w22, #1
0x0005c270: 10 d6 02 94              bl       #0x111ab0                  ; -> calloc@plt
0x0005c274: 60 7a 00 f9              str      x0, [x19, #0xf0]
0x0005c278: 00 09 80 52              mov      w0, #0x48
0x0005c27c: 21 00 80 52              mov      w1, #1
0x0005c280: 0c d6 02 94              bl       #0x111ab0                  ; -> calloc@plt
0x0005c284: 60 7e 00 f9              str      x0, [x19, #0xf8]
0x0005c288: 00 09 80 52              mov      w0, #0x48
0x0005c28c: 21 00 80 52              mov      w1, #1
0x0005c290: 08 d6 02 94              bl       #0x111ab0                  ; -> calloc@plt
0x0005c294: 60 86 00 f9              str      x0, [x19, #0x108]
0x0005c298: 00 09 80 52              mov      w0, #0x48
0x0005c29c: 21 00 80 52              mov      w1, #1
0x0005c2a0: 04 d6 02 94              bl       #0x111ab0                  ; -> calloc@plt
0x0005c2a4: 60 8a 00 f9              str      x0, [x19, #0x110]
0x0005c2a8: 00 09 80 52              mov      w0, #0x48
0x0005c2ac: 21 00 80 52              mov      w1, #1
0x0005c2b0: 00 d6 02 94              bl       #0x111ab0                  ; -> calloc@plt
0x0005c2b4: 60 92 00 f9              str      x0, [x19, #0x120]
0x0005c2b8: 00 09 80 52              mov      w0, #0x48
0x0005c2bc: 21 00 80 52              mov      w1, #1
0x0005c2c0: fc d5 02 94              bl       #0x111ab0                  ; -> calloc@plt
0x0005c2c4: 60 96 00 f9              str      x0, [x19, #0x128]
0x0005c2c8: 00 09 80 52              mov      w0, #0x48
0x0005c2cc: 21 00 80 52              mov      w1, #1
0x0005c2d0: f8 d5 02 94              bl       #0x111ab0                  ; -> calloc@plt
0x0005c2d4: 60 9a 00 f9              str      x0, [x19, #0x130]
0x0005c2d8: 00 09 80 52              mov      w0, #0x48
0x0005c2dc: 21 00 80 52              mov      w1, #1
0x0005c2e0: f4 d5 02 94              bl       #0x111ab0                  ; -> calloc@plt
0x0005c2e4: 60 9e 00 f9              str      x0, [x19, #0x138]
0x0005c2e8: 00 09 80 52              mov      w0, #0x48
0x0005c2ec: 21 00 80 52              mov      w1, #1
0x0005c2f0: f0 d5 02 94              bl       #0x111ab0                  ; -> calloc@plt
0x0005c2f4: 60 a2 00 f9              str      x0, [x19, #0x140]
0x0005c2f8: 00 09 80 52              mov      w0, #0x48
0x0005c2fc: 21 00 80 52              mov      w1, #1
0x0005c300: ec d5 02 94              bl       #0x111ab0                  ; -> calloc@plt
0x0005c304: 60 a6 00 f9              str      x0, [x19, #0x148]
0x0005c308: 00 09 80 52              mov      w0, #0x48
0x0005c30c: 21 00 80 52              mov      w1, #1
0x0005c310: e8 d5 02 94              bl       #0x111ab0                  ; -> calloc@plt
0x0005c314: 7b 42 80 b9              ldrsw    x27, [x19, #0x40]
0x0005c318: 60 aa 00 f9              str      x0, [x19, #0x150]
0x0005c31c: 78 3a 80 b9              ldrsw    x24, [x19, #0x38]
0x0005c320: 68 f3 7d d3              lsl      x8, x27, #3
0x0005c324: e0 03 08 aa              mov      x0, x8
0x0005c328: e8 d5 02 94              bl       #0x111ac8
0x0005c32c: 60 72 00 f9              str      x0, [x19, #0xe0]
0x0005c330: c0 f1 ff b4              cbz      x0, #0x5c168
0x0005c334: 19 7f 40 92              and      x25, x24, #0xffffffff
0x0005c338: 7f 07 00 71              cmp      w27, #1
0x0005c33c: 0b 02 00 54              b.lt     #0x5c37c
0x0005c340: e8 03 1f aa              mov      x8, xzr
0x0005c344: 09 00 80 12              mov      w9, #-1
0x0005c348: 6a 03 09 0b              add      w10, w27, w9
0x0005c34c: 6b 36 40 b9              ldr      w11, [x19, #0x34]
0x0005c350: 29 05 00 51              sub      w9, w9, #1
0x0005c354: 4a 7d 19 1b              mul      w10, w10, w25
0x0005c358: ea 03 2a 2a              mvn      w10, w10
0x0005c35c: 6a 29 1b 1b              madd     w10, w11, w27, w10
0x0005c360: 6b 72 40 f9              ldr      x11, [x19, #0xe0]
0x0005c364: 4a 7d 40 93              sxtw     x10, w10
0x0005c368: 6a 79 28 f8              str      x10, [x11, x8, lsl #3]
0x0005c36c: 08 05 00 91              add      x8, x8, #1
0x0005c370: 7b 42 80 b9              ldrsw    x27, [x19, #0x40]
0x0005c374: 1f 01 1b eb              cmp      x8, x27
0x0005c378: 8b fe ff 54              b.lt     #0x5c348
0x0005c37c: 16 f3 7d d3              lsl      x22, x24, #3
0x0005c380: e0 03 16 aa              mov      x0, x22
0x0005c384: d1 d5 02 94              bl       #0x111ac8
0x0005c388: 60 62 00 f9              str      x0, [x19, #0xc0]
0x0005c38c: 00 07 00 b4              cbz      x0, #0x5c46c
0x0005c390: e0 03 16 aa              mov      x0, x22
0x0005c394: cd d5 02 94              bl       #0x111ac8
0x0005c398: 60 66 00 f9              str      x0, [x19, #0xc8]
0x0005c39c: 80 06 00 b4              cbz      x0, #0x5c46c
0x0005c3a0: e0 03 16 aa              mov      x0, x22
0x0005c3a4: c9 d5 02 94              bl       #0x111ac8
0x0005c3a8: 60 6a 00 f9              str      x0, [x19, #0xd0]
0x0005c3ac: 00 06 00 b4              cbz      x0, #0x5c46c
0x0005c3b0: e0 03 16 aa              mov      x0, x22
0x0005c3b4: c5 d5 02 94              bl       #0x111ac8
0x0005c3b8: 60 6e 00 f9              str      x0, [x19, #0xd8]
0x0005c3bc: 80 05 00 b4              cbz      x0, #0x5c46c
0x0005c3c0: 3f 07 00 71              cmp      w25, #1
0x0005c3c4: 6b 15 00 54              b.lt     #0x5c670
0x0005c3c8: b6 02 1a 8b              add      x22, x21, x26
0x0005c3cc: fa 03 1f aa              mov      x26, xzr
0x0005c3d0: 35 f3 7d d3              lsl      x21, x25, #3
0x0005c3d4: e8 03 1f aa              mov      x8, xzr
0x0005c3d8: 68 22 40 b9              ldr      w8, [x19, #0x20]
0x0005c3dc: 08 09 d9 1a              udiv     w8, w8, w25
0x0005c3e0: 00 7d 40 93              sxtw     x0, w8
0x0005c3e4: b9 d5 02 94              bl       #0x111ac8
0x0005c3e8: 68 62 40 f9              ldr      x8, [x19, #0xc0]
0x0005c3ec: 00 69 3a f8              str      x0, [x8, x26]
0x0005c3f0: 5a 23 00 91              add      x26, x26, #8
0x0005c3f4: bf 02 1a eb              cmp      x21, x26
0x0005c3f8: 01 ff ff 54              b.ne     #0x5c3d8
0x0005c3fc: 3f 07 00 71              cmp      w25, #1
0x0005c400: 8b 13 00 54              b.lt     #0x5c670
0x0005c404: 69 22 40 b9              ldr      w9, [x19, #0x20]
0x0005c408: e8 03 1f aa              mov      x8, xzr
0x0005c40c: 29 09 d9 1a              udiv     w9, w9, w25
0x0005c410: 2a 7d 02 53              lsr      w10, w9, #2
0x0005c414: 5f 05 00 71              cmp      w10, #1
0x0005c418: 4b 85 9f 1a              csinc    w11, w10, wzr, hi
0x0005c41c: 6b 7d 7e d3              ubfiz    x11, x11, #2, #0x20
0x0005c420: 04 00 00 14              b        #0x5c430                   ; -> udk7_network_init+0x3ec
0x0005c424: 08 05 00 91              add      x8, x8, #1
0x0005c428: 1f 01 19 eb              cmp      x8, x25
0x0005c42c: 40 02 00 54              b.eq     #0x5c474
0x0005c430: 3f 11 00 71              cmp      w9, #4
0x0005c434: 83 ff ff 54              b.lo     #0x5c424
0x0005c438: 4d 7d 08 1b              mul      w13, w10, w8
0x0005c43c: ec 03 1f aa              mov      x12, xzr
0x0005c440: ee 03 1f aa              mov      x14, xzr
0x0005c444: cf 01 0d 8b              add      x15, x14, x13
0x0005c448: 70 62 40 f9              ldr      x16, [x19, #0xc0]
0x0005c44c: ce 05 00 91              add      x14, x14, #1
0x0005c450: 10 7a 68 f8              ldr      x16, [x16, x8, lsl #3]
0x0005c454: c0 7a 6f bc              ldr      s0, [x22, x15, lsl #2]
0x0005c458: 00 6a 2c bc              str      s0, [x16, x12]
0x0005c45c: 8c 11 00 91              add      x12, x12, #4
0x0005c460: 7f 01 0c eb              cmp      x11, x12
0x0005c464: 01 ff ff 54              b.ne     #0x5c444
0x0005c468: ef ff ff 17              b        #0x5c424                   ; -> udk7_network_init+0x3e0
0x0005c46c: 36 00 80 52              mov      w22, #1
0x0005c470: 3e ff ff 17              b        #0x5c168                   ; -> udk7_network_init+0x124
0x0005c474: 3f 07 00 71              cmp      w25, #1
0x0005c478: cb 0f 00 54              b.lt     #0x5c670
0x0005c47c: 68 22 40 b9              ldr      w8, [x19, #0x20]
0x0005c480: fa 03 1f aa              mov      x26, xzr
0x0005c484: d6 02 08 8b              add      x22, x22, x8
0x0005c488: 68 26 40 b9              ldr      w8, [x19, #0x24]
0x0005c48c: 08 09 d8 1a              udiv     w8, w8, w24
0x0005c490: 00 7d 40 93              sxtw     x0, w8
0x0005c494: 8d d5 02 94              bl       #0x111ac8
0x0005c498: 68 66 40 f9              ldr      x8, [x19, #0xc8]
0x0005c49c: 00 69 3a f8              str      x0, [x8, x26]
0x0005c4a0: 5a 23 00 91              add      x26, x26, #8
0x0005c4a4: bf 02 1a eb              cmp      x21, x26
0x0005c4a8: 01 ff ff 54              b.ne     #0x5c488
0x0005c4ac: 3f 07 00 71              cmp      w25, #1
0x0005c4b0: 0b 0e 00 54              b.lt     #0x5c670
0x0005c4b4: 69 26 40 b9              ldr      w9, [x19, #0x24]
0x0005c4b8: e8 03 1f aa              mov      x8, xzr
0x0005c4bc: 29 09 d9 1a              udiv     w9, w9, w25
0x0005c4c0: 2a 7d 02 53              lsr      w10, w9, #2
0x0005c4c4: 5f 05 00 71              cmp      w10, #1
0x0005c4c8: 4b 85 9f 1a              csinc    w11, w10, wzr, hi
0x0005c4cc: 6b 7d 7e d3              ubfiz    x11, x11, #2, #0x20
0x0005c4d0: 04 00 00 14              b        #0x5c4e0                   ; -> udk7_network_init+0x49c
0x0005c4d4: 08 05 00 91              add      x8, x8, #1
0x0005c4d8: 1f 01 19 eb              cmp      x8, x25
0x0005c4dc: 00 02 00 54              b.eq     #0x5c51c
0x0005c4e0: 3f 11 00 71              cmp      w9, #4
0x0005c4e4: 83 ff ff 54              b.lo     #0x5c4d4
0x0005c4e8: 4d 7d 08 1b              mul      w13, w10, w8
0x0005c4ec: ec 03 1f aa              mov      x12, xzr
0x0005c4f0: ee 03 1f aa              mov      x14, xzr
0x0005c4f4: cf 01 0d 8b              add      x15, x14, x13
0x0005c4f8: 70 66 40 f9              ldr      x16, [x19, #0xc8]
0x0005c4fc: ce 05 00 91              add      x14, x14, #1
0x0005c500: 10 7a 68 f8              ldr      x16, [x16, x8, lsl #3]
0x0005c504: c0 7a 6f bc              ldr      s0, [x22, x15, lsl #2]
0x0005c508: 00 6a 2c bc              str      s0, [x16, x12]
0x0005c50c: 8c 11 00 91              add      x12, x12, #4
0x0005c510: 7f 01 0c eb              cmp      x11, x12
0x0005c514: 01 ff ff 54              b.ne     #0x5c4f4
0x0005c518: ef ff ff 17              b        #0x5c4d4                   ; -> udk7_network_init+0x490
0x0005c51c: 3f 07 00 71              cmp      w25, #1
0x0005c520: 8b 0a 00 54              b.lt     #0x5c670
0x0005c524: 68 26 40 b9              ldr      w8, [x19, #0x24]
0x0005c528: fa 03 1f aa              mov      x26, xzr
0x0005c52c: d6 02 08 8b              add      x22, x22, x8
0x0005c530: 68 2a 40 b9              ldr      w8, [x19, #0x28]
0x0005c534: 08 09 d9 1a              udiv     w8, w8, w25
0x0005c538: 00 7d 40 93              sxtw     x0, w8
0x0005c53c: 63 d5 02 94              bl       #0x111ac8
0x0005c540: 68 6a 40 f9              ldr      x8, [x19, #0xd0]
0x0005c544: 00 69 3a f8              str      x0, [x8, x26]
0x0005c548: 5a 23 00 91              add      x26, x26, #8
0x0005c54c: bf 02 1a eb              cmp      x21, x26
0x0005c550: 01 ff ff 54              b.ne     #0x5c530
0x0005c554: 3f 07 00 71              cmp      w25, #1
0x0005c558: cb 08 00 54              b.lt     #0x5c670
0x0005c55c: 69 2a 40 b9              ldr      w9, [x19, #0x28]
0x0005c560: e8 03 1f aa              mov      x8, xzr
0x0005c564: 29 09 d9 1a              udiv     w9, w9, w25
0x0005c568: 2a 7d 02 53              lsr      w10, w9, #2
0x0005c56c: 5f 05 00 71              cmp      w10, #1
0x0005c570: 4b 85 9f 1a              csinc    w11, w10, wzr, hi
0x0005c574: 6b 7d 7e d3              ubfiz    x11, x11, #2, #0x20
0x0005c578: 04 00 00 14              b        #0x5c588                   ; -> udk7_network_init+0x544
0x0005c57c: 08 05 00 91              add      x8, x8, #1
0x0005c580: 1f 01 19 eb              cmp      x8, x25
0x0005c584: 00 02 00 54              b.eq     #0x5c5c4
0x0005c588: 3f 11 00 71              cmp      w9, #4
0x0005c58c: 83 ff ff 54              b.lo     #0x5c57c
0x0005c590: 4d 7d 08 1b              mul      w13, w10, w8
0x0005c594: ec 03 1f aa              mov      x12, xzr
0x0005c598: ee 03 1f aa              mov      x14, xzr
0x0005c59c: cf 01 0d 8b              add      x15, x14, x13
0x0005c5a0: 70 6a 40 f9              ldr      x16, [x19, #0xd0]
0x0005c5a4: ce 05 00 91              add      x14, x14, #1
0x0005c5a8: 10 7a 68 f8              ldr      x16, [x16, x8, lsl #3]
0x0005c5ac: c0 7a 6f bc              ldr      s0, [x22, x15, lsl #2]
0x0005c5b0: 00 6a 2c bc              str      s0, [x16, x12]
0x0005c5b4: 8c 11 00 91              add      x12, x12, #4
0x0005c5b8: 7f 01 0c eb              cmp      x11, x12
0x0005c5bc: 01 ff ff 54              b.ne     #0x5c59c
0x0005c5c0: ef ff ff 17              b        #0x5c57c                   ; -> udk7_network_init+0x538
0x0005c5c4: 3f 07 00 71              cmp      w25, #1
0x0005c5c8: 4b 05 00 54              b.lt     #0x5c670
0x0005c5cc: 68 2a 40 b9              ldr      w8, [x19, #0x28]
0x0005c5d0: fa 03 1f aa              mov      x26, xzr
0x0005c5d4: d6 02 08 8b              add      x22, x22, x8
0x0005c5d8: 68 2e 40 b9              ldr      w8, [x19, #0x2c]
0x0005c5dc: 08 09 d9 1a              udiv     w8, w8, w25
0x0005c5e0: 00 7d 40 93              sxtw     x0, w8
0x0005c5e4: 39 d5 02 94              bl       #0x111ac8
0x0005c5e8: 68 6e 40 f9              ldr      x8, [x19, #0xd8]
0x0005c5ec: 00 69 3a f8              str      x0, [x8, x26]
0x0005c5f0: 5a 23 00 91              add      x26, x26, #8
0x0005c5f4: bf 02 1a eb              cmp      x21, x26
0x0005c5f8: 01 ff ff 54              b.ne     #0x5c5d8
0x0005c5fc: 3f 07 00 71              cmp      w25, #1
0x0005c600: 8b 03 00 54              b.lt     #0x5c670
0x0005c604: 69 2e 40 b9              ldr      w9, [x19, #0x2c]
0x0005c608: e8 03 1f aa              mov      x8, xzr
0x0005c60c: 0b 7f 40 92              and      x11, x24, #0xffffffff
0x0005c610: 29 09 d8 1a              udiv     w9, w9, w24
0x0005c614: 2a 7d 02 53              lsr      w10, w9, #2
0x0005c618: 5f 05 00 71              cmp      w10, #1
0x0005c61c: 4c 85 9f 1a              csinc    w12, w10, wzr, hi
0x0005c620: 8c 7d 7e d3              ubfiz    x12, x12, #2, #0x20
0x0005c624: 04 00 00 14              b        #0x5c634                   ; -> udk7_network_init+0x5f0
0x0005c628: 08 05 00 91              add      x8, x8, #1
0x0005c62c: 1f 01 0b eb              cmp      x8, x11
0x0005c630: 00 02 00 54              b.eq     #0x5c670
0x0005c634: 3f 11 00 71              cmp      w9, #4
0x0005c638: 83 ff ff 54              b.lo     #0x5c628
0x0005c63c: 4e 7d 08 1b              mul      w14, w10, w8
0x0005c640: ed 03 1f aa              mov      x13, xzr
0x0005c644: ef 03 1f aa              mov      x15, xzr
0x0005c648: f0 01 0e 8b              add      x16, x15, x14
0x0005c64c: 71 6e 40 f9              ldr      x17, [x19, #0xd8]
0x0005c650: ef 05 00 91              add      x15, x15, #1
0x0005c654: 31 7a 68 f8              ldr      x17, [x17, x8, lsl #3]
0x0005c658: c0 7a 70 bc              ldr      s0, [x22, x16, lsl #2]
0x0005c65c: 20 6a 2d bc              str      s0, [x17, x13]
0x0005c660: ad 11 00 91              add      x13, x13, #4
0x0005c664: 9f 01 0d eb              cmp      x12, x13
0x0005c668: 01 ff ff 54              b.ne     #0x5c648
0x0005c66c: ef ff ff 17              b        #0x5c628                   ; -> udk7_network_init+0x5e4
0x0005c670: 75 3a 80 b9              ldrsw    x21, [x19, #0x38]
0x0005c674: 80 00 80 52              mov      w0, #4
0x0005c678: e1 03 15 aa              mov      x1, x21
0x0005c67c: 0d d5 02 94              bl       #0x111ab0                  ; -> calloc@plt
0x0005c680: 60 52 00 f9              str      x0, [x19, #0xa0]
0x0005c684: 80 00 80 52              mov      w0, #4
0x0005c688: e1 03 15 aa              mov      x1, x21
0x0005c68c: 09 d5 02 94              bl       #0x111ab0                  ; -> calloc@plt
0x0005c690: 60 56 00 f9              str      x0, [x19, #0xa8]
0x0005c694: 80 00 80 52              mov      w0, #4
0x0005c698: e1 03 15 aa              mov      x1, x21
0x0005c69c: 05 d5 02 94              bl       #0x111ab0                  ; -> calloc@plt
0x0005c6a0: 60 4e 00 f9              str      x0, [x19, #0x98]
0x0005c6a4: 80 00 80 52              mov      w0, #4
0x0005c6a8: e1 03 15 aa              mov      x1, x21
0x0005c6ac: 01 d5 02 94              bl       #0x111ab0                  ; -> calloc@plt
0x0005c6b0: 60 5a 00 f9              str      x0, [x19, #0xb0]
0x0005c6b4: 80 00 80 52              mov      w0, #4
0x0005c6b8: e1 03 15 aa              mov      x1, x21
0x0005c6bc: fd d4 02 94              bl       #0x111ab0                  ; -> calloc@plt
0x0005c6c0: f6 03 1f 2a              mov      w22, wzr
0x0005c6c4: 60 5e 00 f9              str      x0, [x19, #0xb8]
0x0005c6c8: 97 02 00 f9              str      x23, [x20]
0x0005c6cc: a7 fe ff 17              b        #0x5c168                   ; -> udk7_network_init+0x124
