; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/ttlibs/libeffect.so
; target  : address 0x696200
; start   : 0x696200
; size    : 0x500
; bytes   : 0x500   instructions: 320
; generated: 2026-10-23T23:54:35

0x00696200: c0 62 01 91              add      x0, x22, #0x58
0x00696204: e1 03 15 aa              mov      x1, x21
0x00696208: 83 1b f2 97              bl       #0x31d014
0x0069620c: df c2 01 39              strb     wzr, [x22, #0x70]
0x00696210: fd 7b 42 a9              ldp      x29, x30, [sp, #0x20]
0x00696214: 56 e1 0f 14              b        #0xa8e76c
0x00696218: ff c3 07 d1              sub      sp, sp, #0x1f0
0x0069621c: fc 57 1c a9              stp      x28, x21, [sp, #0x1c0]
0x00696220: f4 4f 1d a9              stp      x20, x19, [sp, #0x1d0]
0x00696224: fd 7b 1e a9              stp      x29, x30, [sp, #0x1e0]
0x00696228: fd 83 07 91              add      x29, sp, #0x1e0
0x0069622c: 55 d0 3b d5              mrs      x21, tpidr_el0
0x00696230: a8 16 40 f9              ldr      x8, [x21, #0x28]
0x00696234: f4 03 01 2a              mov      w20, w1
0x00696238: df 99 10 94              bl       #0xabc9b4
0x0069623c: fe 00 00 94              bl       #0x696634
0x00696240: 9f 4a 00 71              cmp      w20, #0x12
0x00696244: 88 1e 00 54              b.hi     #0x696614
0x00696248: 09 30 00 f0              adrp     x9, #0xc99000
0x0069624c: e8 03 14 2a              mov      w8, w20
0x00696250: 29 b1 2c 91              add      x9, x9, #0xb2c
0x00696254: 8a 00 00 10              adr      x10, #0x696264
0x00696258: 2b 79 68 78              ldrh     w11, [x9, x8, lsl #1]
0x0069625c: 4a 09 0b 8b              add      x10, x10, x11, lsl #2
0x00696260: 40 01 1f d6              br       x10
0x00696264: 81 36 00 90              adrp     x1, #0xd66000
0x00696268: 21 84 05 91              add      x1, x1, #0x161
0x0069626c: c0 8b 10 94              bl       #0xab916c
0x00696270: 21 38 00 f0              adrp     x1, #0xd9d000
0x00696274: 21 90 32 91              add      x1, x1, #0xca4
0x00696278: 58 1e 10 94              bl       #0xa9dbd8
0x0069627c: a5 4c 10 94              bl       #0xaa9510
0x00696280: fe b4 10 94              bl       #0xac3678
0x00696284: f9 b4 10 94              bl       #0xac3668
0x00696288: f4 b4 10 94              bl       #0xac3658
0x0069628c: 61 38 00 d0              adrp     x1, #0xda4000
0x00696290: 21 80 23 91              add      x1, x1, #0x8e0
0x00696294: 57 05 10 94              bl       #0xa977f0
0x00696298: a0 a3 03 d1              sub      x0, x29, #0xe8
0x0069629c: a1 e3 02 d1              sub      x1, x29, #0xb8
0x006962a0: 08 eb 0f 94              bl       #0xa90ec0
0x006962a4: 3c 60 11 94              bl       #0xaee394
0x006962a8: a4 a3 03 d1              sub      x4, x29, #0xe8
0x006962ac: ac 3c 10 94              bl       #0xaa555c
0x006962b0: a0 a3 03 d1              sub      x0, x29, #0xe8
0x006962b4: df 48 f1 97              bl       #0x2e8630
0x006962b8: 5d 67 10 94              bl       #0xab002c
0x006962bc: 13 09 80 52              mov      w19, #0x48
0x006962c0: 97 11 10 94              bl       #0xa9a91c
0x006962c4: e1 ff ff 54              b.ne     #0x6962c0
0x006962c8: b6 00 00 14              b        #0x6965a0
0x006962cc: 81 36 00 90              adrp     x1, #0xd66000
0x006962d0: 21 40 06 91              add      x1, x1, #0x190
0x006962d4: a6 8b 10 94              bl       #0xab916c
0x006962d8: 01 38 00 f0              adrp     x1, #0xd99000
0x006962dc: 21 08 13 91              add      x1, x1, #0x4c2
0x006962e0: 3e 1e 10 94              bl       #0xa9dbd8
0x006962e4: 8b 4c 10 94              bl       #0xaa9510
0x006962e8: a0 03 01 d1              sub      x0, x29, #0x40
0x006962ec: a1 83 02 d1              sub      x1, x29, #0xa0
0x006962f0: 5e 37 10 94              bl       #0xaa4068
0x006962f4: 61 38 00 d0              adrp     x1, #0xda4000
0x006962f8: 21 80 23 91              add      x1, x1, #0x8e0
0x006962fc: 3d 05 10 94              bl       #0xa977f0
0x00696300: e0 83 00 91              add      x0, sp, #0x20
0x00696304: a1 e3 02 d1              sub      x1, x29, #0xb8
0x00696308: ee ea 0f 94              bl       #0xa90ec0
0x0069630c: 22 60 11 94              bl       #0xaee394
0x00696310: e4 83 00 91              add      x4, sp, #0x20
0x00696314: 92 3c 10 94              bl       #0xaa555c
0x00696318: 13 04 10 94              bl       #0xa97364
0x0069631c: 44 67 10 94              bl       #0xab002c
0x00696320: 13 03 80 52              mov      w19, #0x18
0x00696324: 7e 11 10 94              bl       #0xa9a91c
0x00696328: e1 ff ff 54              b.ne     #0x696324
0x0069632c: 9d 00 00 14              b        #0x6965a0
0x00696330: 81 36 00 90              adrp     x1, #0xd66000
0x00696334: 21 a4 05 91              add      x1, x1, #0x169
0x00696338: 8d 8b 10 94              bl       #0xab916c
0x0069633c: 21 38 00 f0              adrp     x1, #0xd9d000
0x00696340: 21 90 32 91              add      x1, x1, #0xca4
0x00696344: 25 1e 10 94              bl       #0xa9dbd8
0x00696348: 72 4c 10 94              bl       #0xaa9510
0x0069634c: cb b4 10 94              bl       #0xac3678
0x00696350: c6 b4 10 94              bl       #0xac3668
0x00696354: c1 b4 10 94              bl       #0xac3658
0x00696358: 61 38 00 d0              adrp     x1, #0xda4000
0x0069635c: 21 80 23 91              add      x1, x1, #0x8e0
0x00696360: 24 05 10 94              bl       #0xa977f0
0x00696364: e0 83 03 91              add      x0, sp, #0xe0
0x00696368: a1 e3 02 d1              sub      x1, x29, #0xb8
0x0069636c: d5 ea 0f 94              bl       #0xa90ec0
0x00696370: 09 60 11 94              bl       #0xaee394
0x00696374: e4 83 03 91              add      x4, sp, #0xe0
0x00696378: 79 3c 10 94              bl       #0xaa555c
0x0069637c: e0 83 03 91              add      x0, sp, #0xe0
0x00696380: ac 48 f1 97              bl       #0x2e8630
0x00696384: 2a 67 10 94              bl       #0xab002c
0x00696388: 13 09 80 52              mov      w19, #0x48
0x0069638c: 64 11 10 94              bl       #0xa9a91c
0x00696390: e1 ff ff 54              b.ne     #0x69638c
0x00696394: 83 00 00 14              b        #0x6965a0
0x00696398: 21 38 00 90              adrp     x1, #0xd9a000
0x0069639c: 21 70 2f 91              add      x1, x1, #0xbdc
0x006963a0: 0e 1e 10 94              bl       #0xa9dbd8
0x006963a4: 46 6b 10 94              bl       #0xab10bc
0x006963a8: e0 03 02 91              add      x0, sp, #0x80
0x006963ac: a1 03 01 d1              sub      x1, x29, #0x40
0x006963b0: c4 ea 0f 94              bl       #0xa90ec0
0x006963b4: e8 09 11 94              bl       #0xad8b54
0x006963b8: e4 03 02 91              add      x4, sp, #0x80
0x006963bc: 03 01 80 52              mov      w3, #8
0x006963c0: 5e 72 10 94              bl       #0xab2d38
0x006963c4: e0 03 02 91              add      x0, sp, #0x80
0x006963c8: 8e 00 00 14              b        #0x696600
0x006963cc: 61 37 00 f0              adrp     x1, #0xd85000
0x006963d0: 21 c4 15 91              add      x1, x1, #0x571
0x006963d4: 01 1e 10 94              bl       #0xa9dbd8
0x006963d8: 3d 3d 11 94              bl       #0xae58cc
0x006963dc: a0 43 03 d1              sub      x0, x29, #0xd0
0x006963e0: a1 03 01 d1              sub      x1, x29, #0x40
0x006963e4: b7 ea 0f 94              bl       #0xa90ec0
0x006963e8: 61 38 00 d0              adrp     x1, #0xda4000
0x006963ec: 21 80 23 91              add      x1, x1, #0x8e0
0x006963f0: 00 05 10 94              bl       #0xa977f0
0x006963f4: e0 63 02 91              add      x0, sp, #0x98
0x006963f8: a1 e3 02 d1              sub      x1, x29, #0xb8
0x006963fc: b1 ea 0f 94              bl       #0xa90ec0
0x00696400: d5 09 11 94              bl       #0xad8b54
0x00696404: e4 63 02 91              add      x4, sp, #0x98
0x00696408: 03 01 80 52              mov      w3, #8
0x0069640c: 4b 72 10 94              bl       #0xab2d38
0x00696410: e0 63 02 91              add      x0, sp, #0x98
0x00696414: 28 00 00 14              b        #0x6964b4
0x00696418: 81 36 00 90              adrp     x1, #0xd66000
0x0069641c: 21 d0 05 91              add      x1, x1, #0x174
0x00696420: ee 1d 10 94              bl       #0xa9dbd8
0x00696424: 21 38 00 90              adrp     x1, #0xd9a000
0x00696428: 21 64 1f 91              add      x1, x1, #0x7d9
0x0069642c: a0 03 01 d1              sub      x0, x29, #0x40
0x00696430: 0a 4c f1 97              bl       #0x2e9458
0x00696434: a0 43 03 d1              sub      x0, x29, #0xd0
0x00696438: a1 03 01 d1              sub      x1, x29, #0x40
0x0069643c: a1 ea 0f 94              bl       #0xa90ec0
0x00696440: 61 38 00 d0              adrp     x1, #0xda4000
0x00696444: 21 80 23 91              add      x1, x1, #0x8e0
0x00696448: ea 04 10 94              bl       #0xa977f0
0x0069644c: e0 23 03 91              add      x0, sp, #0xc8
0x00696450: a1 e3 02 d1              sub      x1, x29, #0xb8
0x00696454: 9b ea 0f 94              bl       #0xa90ec0
0x00696458: bf 09 11 94              bl       #0xad8b54
0x0069645c: e4 23 03 91              add      x4, sp, #0xc8
0x00696460: 3f 3c 10 94              bl       #0xaa555c
0x00696464: e0 23 03 91              add      x0, sp, #0xc8
0x00696468: 13 00 00 14              b        #0x6964b4
0x0069646c: 81 36 00 90              adrp     x1, #0xd66000
0x00696470: 21 e4 05 91              add      x1, x1, #0x179
0x00696474: d9 1d 10 94              bl       #0xa9dbd8
0x00696478: 15 3d 11 94              bl       #0xae58cc
0x0069647c: a0 43 03 d1              sub      x0, x29, #0xd0
0x00696480: a1 03 01 d1              sub      x1, x29, #0x40
0x00696484: 8f ea 0f 94              bl       #0xa90ec0
0x00696488: 61 38 00 d0              adrp     x1, #0xda4000
0x0069648c: 21 80 23 91              add      x1, x1, #0x8e0
0x00696490: d8 04 10 94              bl       #0xa977f0
0x00696494: e0 c3 02 91              add      x0, sp, #0xb0
0x00696498: a1 e3 02 d1              sub      x1, x29, #0xb8
0x0069649c: 89 ea 0f 94              bl       #0xa90ec0
0x006964a0: ad 09 11 94              bl       #0xad8b54
0x006964a4: e4 c3 02 91              add      x4, sp, #0xb0
0x006964a8: 03 01 80 52              mov      w3, #8
0x006964ac: 23 72 10 94              bl       #0xab2d38
0x006964b0: e0 c3 02 91              add      x0, sp, #0xb0
0x006964b4: 5f 48 f1 97              bl       #0x2e8630
0x006964b8: a0 e3 02 d1              sub      x0, x29, #0xb8
0x006964bc: 79 48 f1 97              bl       #0x2e86a0
0x006964c0: a0 43 03 d1              sub      x0, x29, #0xd0
0x006964c4: 5b 48 f1 97              bl       #0x2e8630
0x006964c8: a6 9a 11 94              bl       #0xafcf60
0x006964cc: 51 00 00 14              b        #0x696610
0x006964d0: 81 36 00 90              adrp     x1, #0xd66000
0x006964d4: 21 28 06 91              add      x1, x1, #0x18a
0x006964d8: 25 8b 10 94              bl       #0xab916c
0x006964dc: 01 38 00 f0              adrp     x1, #0xd99000
0x006964e0: 21 08 13 91              add      x1, x1, #0x4c2
0x006964e4: bd 1d 10 94              bl       #0xa9dbd8
0x006964e8: 0a 4c 10 94              bl       #0xaa9510
0x006964ec: 21 38 00 f0              adrp     x1, #0xd9d000
0x006964f0: 80 c2 00 91              add      x0, x20, #0x30
0x006964f4: 8a 9a 11 94              bl       #0xafcf1c
0x006964f8: a0 03 01 d1              sub      x0, x29, #0x40
0x006964fc: a1 83 02 d1              sub      x1, x29, #0xa0
0x00696500: d0 14 10 94              bl       #0xa9b840
0x00696504: 61 38 00 d0              adrp     x1, #0xda4000
0x00696508: 21 80 23 91              add      x1, x1, #0x8e0
0x0069650c: b9 04 10 94              bl       #0xa977f0
0x00696510: e0 e3 00 91              add      x0, sp, #0x38
0x00696514: a1 e3 02 d1              sub      x1, x29, #0xb8
0x00696518: 6a ea 0f 94              bl       #0xa90ec0
0x0069651c: 9e 5f 11 94              bl       #0xaee394
0x00696520: e4 e3 00 91              add      x4, sp, #0x38
0x00696524: 0e 3c 10 94              bl       #0xaa555c
0x00696528: 79 48 10 94              bl       #0xaa870c
0x0069652c: c0 66 10 94              bl       #0xab002c
0x00696530: 13 06 80 52              mov      w19, #0x30
0x00696534: fa 10 10 94              bl       #0xa9a91c
0x00696538: e1 ff ff 54              b.ne     #0x696534
0x0069653c: 19 00 00 14              b        #0x6965a0
0x00696540: 81 36 00 90              adrp     x1, #0xd66000
0x00696544: 21 68 06 91              add      x1, x1, #0x19a
0x00696548: 09 8b 10 94              bl       #0xab916c
0x0069654c: 21 38 00 f0              adrp     x1, #0xd9d000
0x00696550: 21 90 32 91              add      x1, x1, #0xca4
0x00696554: a1 1d 10 94              bl       #0xa9dbd8
0x00696558: ee 4b 10 94              bl       #0xaa9510
0x0069655c: 47 b4 10 94              bl       #0xac3678
0x00696560: 42 b4 10 94              bl       #0xac3668
0x00696564: 3d b4 10 94              bl       #0xac3658
0x00696568: 61 38 00 d0              adrp     x1, #0xda4000
0x0069656c: 21 80 23 91              add      x1, x1, #0x8e0
0x00696570: a0 04 10 94              bl       #0xa977f0
0x00696574: e0 23 00 91              add      x0, sp, #8
0x00696578: a1 e3 02 d1              sub      x1, x29, #0xb8
0x0069657c: 51 ea 0f 94              bl       #0xa90ec0
0x00696580: 85 5f 11 94              bl       #0xaee394
0x00696584: e4 23 00 91              add      x4, sp, #8
0x00696588: f5 3b 10 94              bl       #0xaa555c
0x0069658c: 04 29 10 94              bl       #0xaa099c
0x00696590: a7 66 10 94              bl       #0xab002c
0x00696594: 13 09 80 52              mov      w19, #0x48
0x00696598: e1 10 10 94              bl       #0xa9a91c
0x0069659c: e1 ff ff 54              b.ne     #0x696598
0x006965a0: b4 43 03 d1              sub      x20, x29, #0xd0
0x006965a4: 1b 00 00 14              b        #0x696610
0x006965a8: 95 12 11 94              bl       #0xadaffc
0x006965ac: 8b 1d 10 94              bl       #0xa9dbd8
0x006965b0: c3 6a 10 94              bl       #0xab10bc
0x006965b4: e0 a3 01 91              add      x0, sp, #0x68
0x006965b8: a1 03 01 d1              sub      x1, x29, #0x40
0x006965bc: 41 ea 0f 94              bl       #0xa90ec0
0x006965c0: 65 09 11 94              bl       #0xad8b54
0x006965c4: e4 a3 01 91              add      x4, sp, #0x68
0x006965c8: e5 3b 10 94              bl       #0xaa555c
0x006965cc: e0 a3 01 91              add      x0, sp, #0x68
0x006965d0: 0c 00 00 14              b        #0x696600
0x006965d4: 81 36 00 90              adrp     x1, #0xd66000
0x006965d8: 21 00 06 91              add      x1, x1, #0x180
0x006965dc: 7f 1d 10 94              bl       #0xa9dbd8
0x006965e0: b7 6a 10 94              bl       #0xab10bc
0x006965e4: e0 43 01 91              add      x0, sp, #0x50
0x006965e8: a1 03 01 d1              sub      x1, x29, #0x40
0x006965ec: 35 ea 0f 94              bl       #0xa90ec0
0x006965f0: 59 09 11 94              bl       #0xad8b54
0x006965f4: e4 43 01 91              add      x4, sp, #0x50
0x006965f8: d9 3b 10 94              bl       #0xaa555c
0x006965fc: e0 43 01 91              add      x0, sp, #0x50
0x00696600: 0c 48 f1 97              bl       #0x2e8630
0x00696604: 57 9a 11 94              bl       #0xafcf60
0x00696608: a0 43 03 d1              sub      x0, x29, #0xd0
0x0069660c: 09 48 f1 97              bl       #0x2e8630
0x00696610: 77 e4 0f 94              bl       #0xa8f7ec
0x00696614: a8 16 40 f9              ldr      x8, [x21, #0x28]
0x00696618: 09 e0 0f 94              bl       #0xa8e63c
0x0069661c: a1 00 00 54              b.ne     #0x696630
0x00696620: fd 7b 5e a9              ldp      x29, x30, [sp, #0x1e0]
0x00696624: f4 4f 5d a9              ldp      x20, x19, [sp, #0x1d0]
0x00696628: fc 57 5c a9              ldp      x28, x21, [sp, #0x1c0]
0x0069662c: 0e 18 10 14              b        #0xa9c664
0x00696630: 80 bb f0 97              bl       #0x2c5430                  ; -> __stack_chk_fail@plt
0x00696634: f3 0f 1e f8              str      x19, [sp, #-0x20]!
0x00696638: fd 7b 01 a9              stp      x29, x30, [sp, #0x10]
0x0069663c: fd 43 00 91              add      x29, sp, #0x10
0x00696640: b7 72 10 94              bl       #0xab311c
0x00696644: 60 e2 01 91              add      x0, x19, #0x78
0x00696648: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x0069664c: 60 82 02 ad              stp      q0, q0, [x19, #0x50]
0x00696650: 60 12 80 3d              str      q0, [x19, #0x40]
0x00696654: 16 43 f1 97              bl       #0x2e72ac
0x00696658: 7f 42 02 39              strb     wzr, [x19, #0x90]
0x0069665c: fd 7b 41 a9              ldp      x29, x30, [sp, #0x10]
0x00696660: 58 db 0f 14              b        #0xa8d3c0
0x00696664: ff 43 07 d1              sub      sp, sp, #0x1d0
0x00696668: fc bb 00 f9              str      x28, [sp, #0x170]
0x0069666c: fa 67 18 a9              stp      x26, x25, [sp, #0x180]
0x00696670: f8 5f 19 a9              stp      x24, x23, [sp, #0x190]
0x00696674: f6 57 1a a9              stp      x22, x21, [sp, #0x1a0]
0x00696678: f4 4f 1b a9              stp      x20, x19, [sp, #0x1b0]
0x0069667c: fd 7b 1c a9              stp      x29, x30, [sp, #0x1c0]
0x00696680: fd 03 07 91              add      x29, sp, #0x1c0
0x00696684: fb e8 0f 94              bl       #0xa90a70
0x00696688: 18 60 00 91              add      x24, x0, #0x18
0x0069668c: f3 03 00 aa              mov      x19, x0
0x00696690: f4 03 04 aa              mov      x20, x4
0x00696694: a8 83 1a f8              stur     x8, [x29, #-0x58]
0x00696698: 1f 08 00 f9              str      xzr, [x0, #0x10]
0x0069669c: e0 03 18 aa              mov      x0, x24
0x006966a0: 74 f3 10 94              bl       #0xad3470
0x006966a4: e4 ff ff 97              bl       #0x696634
0x006966a8: 79 c2 02 91              add      x25, x19, #0xb0
0x006966ac: ec 8f 10 94              bl       #0xaba65c
0x006966b0: bf 02 00 72              tst      w21, #1
0x006966b4: e8 01 80 52              mov      w8, #0xf
0x006966b8: 89 01 80 52              mov      w9, #0xc
0x006966bc: 6a 01 80 52              mov      w10, #0xb
0x006966c0: 08 11 9f 1a              csel     w8, w8, wzr, ne
0x006966c4: df 02 00 72              tst      w22, #1
0x006966c8: 29 15 9f 1a              csinc    w9, w9, wzr, ne
0x006966cc: 48 11 88 1a              csel     w8, w10, w8, ne
0x006966d0: ff 02 00 72              tst      w23, #1
0x006966d4: 21 11 88 1a              csel     w1, w9, w8, ne
0x006966d8: e0 23 00 91              add      x0, sp, #8
0x006966dc: cf fe ff 97              bl       #0x696218
0x006966e0: 01 97 10 94              bl       #0xabc2e4
0x006966e4: 3f 00 00 94              bl       #0x6967e0
0x006966e8: e0 23 00 91              add      x0, sp, #8
0x006966ec: be e0 ff 97              bl       #0x68e9e4
0x006966f0: e8 23 00 91              add      x8, sp, #8
0x006966f4: 40 00 80 52              mov      w0, #2
0x006966f8: 01 23 80 52              mov      w1, #0x118
0x006966fc: 40 e5 0f 94              bl       #0xa8fbfc
