; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_musically/native/libbytenn.so
; target  : _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE (0x43418)
; start   : 0x43418
; size    : 0xcc8
; bytes   : 0xcc8   instructions: 818
; generated: 2026-10-23T23:28:23

0x00043418: ff c3 04 d1              sub      sp, sp, #0x130
0x0004341c: fc 6f 0d a9              stp      x28, x27, [sp, #0xd0]
0x00043420: fa 67 0e a9              stp      x26, x25, [sp, #0xe0]
0x00043424: f8 5f 0f a9              stp      x24, x23, [sp, #0xf0]
0x00043428: f6 57 10 a9              stp      x22, x21, [sp, #0x100]
0x0004342c: f4 4f 11 a9              stp      x20, x19, [sp, #0x110]
0x00043430: fd 7b 12 a9              stp      x29, x30, [sp, #0x120]
0x00043434: fd 83 04 91              add      x29, sp, #0x120
0x00043438: 5c d0 3b d5              mrs      x28, tpidr_el0
0x0004343c: 88 17 40 f9              ldr      x8, [x28, #0x28]
0x00043440: 15 40 03 91              add      x21, x0, #0xd0
0x00043444: fa 03 00 aa              mov      x26, x0
0x00043448: a8 03 1a f8              stur     x8, [x29, #-0x60]
0x0004344c: 13 64 40 f9              ldr      x19, [x0, #0xc8]
0x00043450: bf 02 13 eb              cmp      x21, x19
0x00043454: a0 03 00 54              b.eq     #0x434c8
0x00043458: 44 1b 40 b9              ldr      w4, [x26, #0x18]
0x0004345c: e8 03 13 aa              mov      x8, x19
0x00043460: 04 00 00 14              b        #0x43470                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x58
0x00043464: e8 03 0a aa              mov      x8, x10
0x00043468: bf 02 08 eb              cmp      x21, x8
0x0004346c: e0 02 00 54              b.eq     #0x434c8
0x00043470: 09 1d 40 f9              ldr      x9, [x8, #0x38]
0x00043474: 23 05 40 b9              ldr      w3, [x9, #4]
0x00043478: 7f 00 04 6b              cmp      w3, w4
0x0004347c: 21 60 00 54              b.ne     #0x44080
0x00043480: 09 05 40 f9              ldr      x9, [x8, #8]
0x00043484: a9 00 00 b4              cbz      x9, #0x43498
0x00043488: e8 03 09 aa              mov      x8, x9
0x0004348c: 29 01 40 f9              ldr      x9, [x9]
0x00043490: c9 ff ff b5              cbnz     x9, #0x43488
0x00043494: f5 ff ff 17              b        #0x43468                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x50
0x00043498: e9 03 08 aa              mov      x9, x8
0x0004349c: 2a 0d 41 f8              ldr      x10, [x9, #0x10]!
0x000434a0: 4b 01 40 f9              ldr      x11, [x10]
0x000434a4: 7f 01 08 eb              cmp      x11, x8
0x000434a8: e0 fd ff 54              b.eq     #0x43464
0x000434ac: 2a 01 40 f9              ldr      x10, [x9]
0x000434b0: e9 03 0a aa              mov      x9, x10
0x000434b4: 28 0d 41 f8              ldr      x8, [x9, #0x10]!
0x000434b8: 0b 01 40 f9              ldr      x11, [x8]
0x000434bc: 7f 01 0a eb              cmp      x11, x10
0x000434c0: 61 ff ff 54              b.ne     #0x434ac
0x000434c4: e9 ff ff 17              b        #0x43468                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x50
0x000434c8: 48 73 40 39              ldrb     w8, [x26, #0x1c]
0x000434cc: 48 05 00 34              cbz      w8, #0x43574
0x000434d0: a8 03 02 d1              sub      x8, x29, #0x80
0x000434d4: 14 21 00 91              add      x20, x8, #8
0x000434d8: bf 02 13 eb              cmp      x21, x19
0x000434dc: bf ff 38 a9              stp      xzr, xzr, [x29, #-0x78]
0x000434e0: b4 03 18 f8              stur     x20, [x29, #-0x80]
0x000434e4: a1 00 00 54              b.ne     #0x434f8
0x000434e8: 1b 00 00 14              b        #0x43554                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x13c
0x000434ec: f3 03 09 aa              mov      x19, x9
0x000434f0: 7f 02 15 eb              cmp      x19, x21
0x000434f4: 00 03 00 54              b.eq     #0x43554
0x000434f8: 62 82 00 91              add      x2, x19, #0x20
0x000434fc: a0 03 02 d1              sub      x0, x29, #0x80
0x00043500: e1 03 14 aa              mov      x1, x20
0x00043504: e3 03 02 aa              mov      x3, x2
0x00043508: 86 b3 ff 97              bl       #0x30320                   ; -> _ZNSt6__ndk16__treeINS_12__value_typeINS_12basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEEEPKhEENS_19__map_value_compareIS7_SA_NS_4lessIS7_EELb1EEENS5_ISA_EEE30__emplace_hint_unique_key_argsIS7_JRKNS_4pairIKS7_S9_EEEEENS_15__tree_iteratorISA_PNS_11__tree_nodeISA_PvEElEENS_21__tree_const_iteratorISA_SR_lEERKT_DpOT0_@plt
0x0004350c: 68 06 40 f9              ldr      x8, [x19, #8]
0x00043510: a8 00 00 b4              cbz      x8, #0x43524
0x00043514: f3 03 08 aa              mov      x19, x8
0x00043518: 08 01 40 f9              ldr      x8, [x8]
0x0004351c: c8 ff ff b5              cbnz     x8, #0x43514
0x00043520: f4 ff ff 17              b        #0x434f0                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0xd8
0x00043524: e8 03 13 aa              mov      x8, x19
0x00043528: 09 0d 41 f8              ldr      x9, [x8, #0x10]!
0x0004352c: 2a 01 40 f9              ldr      x10, [x9]
0x00043530: 5f 01 13 eb              cmp      x10, x19
0x00043534: c0 fd ff 54              b.eq     #0x434ec
0x00043538: 09 01 40 f9              ldr      x9, [x8]
0x0004353c: e8 03 09 aa              mov      x8, x9
0x00043540: 13 0d 41 f8              ldr      x19, [x8, #0x10]!
0x00043544: 6a 02 40 f9              ldr      x10, [x19]
0x00043548: 5f 01 09 eb              cmp      x10, x9
0x0004354c: 61 ff ff 54              b.ne     #0x43538
0x00043550: e8 ff ff 17              b        #0x434f0                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0xd8
0x00043554: a0 03 02 d1              sub      x0, x29, #0x80
0x00043558: 56 b6 ff 97              bl       #0x30eb0                   ; -> _ZN6BYTENN11CheckMd5sumENSt6__ndk13mapINS0_12basic_stringIcNS0_11char_traitsIcEENS0_9allocatorIcEEEEPKhNS0_4lessIS7_EENS5_INS0_4pairIKS7_S9_EEEEEE@plt
0x0004355c: a1 83 58 f8              ldur     x1, [x29, #-0x78]
0x00043560: fb 03 00 2a              mov      w27, w0
0x00043564: a0 03 02 d1              sub      x0, x29, #0x80
0x00043568: ba b2 ff 97              bl       #0x30050                   ; -> _ZNSt6__ndk16__treeINS_12__value_typeINS_12basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEEEPKhEENS_19__map_value_compareIS7_SA_NS_4lessIS7_EELb1EEENS5_ISA_EEE7destroyEPNS_11__tree_nodeISA_PvEE@plt
0x0004356c: db 59 00 35              cbnz     w27, #0x440a4
0x00043570: 53 67 40 f9              ldr      x19, [x26, #0xc8]
0x00043574: bf 02 13 eb              cmp      x21, x19
0x00043578: 40 59 00 54              b.eq     #0x440a0
0x0004357c: 4a 63 06 91              add      x10, x26, #0x198
0x00043580: ea 27 00 f9              str      x10, [sp, #0x48]
0x00043584: 4a 43 05 91              add      x10, x26, #0x150
0x00043588: ea 13 00 f9              str      x10, [sp, #0x20]
0x0004358c: ca 0c 00 f0              adrp     x10, #0x1de000
0x00043590: 4a 19 45 f9              ldr      x10, [x10, #0xa30]         ; -> _ZTVNSt6__ndk120__shared_ptr_pointerIPhNS_14default_deleteIA_hEENS_9allocatorIhEEEE@got
0x00043594: e8 03 02 91              add      x8, sp, #0x80
0x00043598: e9 a3 01 91              add      x9, sp, #0x68
0x0004359c: 08 01 40 b2              orr      x8, x8, #1
0x000435a0: e8 1b 00 f9              str      x8, [sp, #0x30]
0x000435a4: 28 01 40 b2              orr      x8, x9, #1
0x000435a8: 5b 01 80 52              mov      w27, #0xa
0x000435ac: e8 0f 00 f9              str      x8, [sp, #0x18]
0x000435b0: 48 41 00 91              add      x8, x10, #0x10
0x000435b4: e8 17 00 f9              str      x8, [sp, #0x28]
0x000435b8: fa 2b 00 f9              str      x26, [sp, #0x50]
0x000435bc: 04 00 00 14              b        #0x435cc                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x1b4
0x000435c0: f3 03 09 aa              mov      x19, x9
0x000435c4: bf 02 13 eb              cmp      x21, x19
0x000435c8: c0 56 00 54              b.eq     #0x440a0
0x000435cc: 74 1e 40 f9              ldr      x20, [x19, #0x38]
0x000435d0: ff ff 08 a9              stp      xzr, xzr, [sp, #0x88]
0x000435d4: ff 43 00 f9              str      xzr, [sp, #0x80]
0x000435d8: e8 03 13 aa              mov      x8, x19
0x000435dc: 09 0d 42 38              ldrb     w9, [x8, #0x20]!
0x000435e0: c9 00 00 37              tbnz     w9, #0, #0x435f8
0x000435e4: 09 09 40 f9              ldr      x9, [x8, #0x10]
0x000435e8: 00 01 c0 3d              ldr      q0, [x8]
0x000435ec: e9 4b 00 f9              str      x9, [sp, #0x90]
0x000435f0: e0 23 80 3d              str      q0, [sp, #0x80]
0x000435f4: 19 00 00 14              b        #0x43658                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x240
0x000435f8: 78 16 40 f9              ldr      x24, [x19, #0x28]
0x000435fc: 1f 43 00 b1              cmn      x24, #0x10
0x00043600: c2 56 00 54              b.hs     #0x440d8
0x00043604: 77 1a 40 f9              ldr      x23, [x19, #0x30]
0x00043608: 1f 5f 00 f1              cmp      x24, #0x17
0x0004360c: c2 00 00 54              b.hs     #0x43624
0x00043610: f6 1b 40 f9              ldr      x22, [sp, #0x30]
0x00043614: 08 7b 1f 53              lsl      w8, w24, #1
0x00043618: e8 03 02 39              strb     w8, [sp, #0x80]
0x0004361c: 58 01 00 b5              cbnz     x24, #0x43644
0x00043620: 0d 00 00 14              b        #0x43654                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x23c
0x00043624: 08 43 00 91              add      x8, x24, #0x10
0x00043628: 19 ed 7c 92              and      x25, x8, #0xfffffffffffffff0
0x0004362c: e0 03 19 aa              mov      x0, x25
0x00043630: 88 b6 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00043634: 28 03 40 b2              orr      x8, x25, #1
0x00043638: f6 03 00 aa              mov      x22, x0
0x0004363c: e8 63 08 a9              stp      x8, x24, [sp, #0x80]
0x00043640: e0 4b 00 f9              str      x0, [sp, #0x90]
0x00043644: e0 03 16 aa              mov      x0, x22
0x00043648: e1 03 17 aa              mov      x1, x23
0x0004364c: e2 03 18 aa              mov      x2, x24
0x00043650: 14 b0 ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00043654: df 6a 38 38              strb     wzr, [x22, x24]
0x00043658: 88 02 40 39              ldrb     w8, [x20]
0x0004365c: 1f 09 01 71              cmp      w8, #0x42
0x00043660: 01 03 00 54              b.ne     #0x436c0
0x00043664: 88 06 40 39              ldrb     w8, [x20, #1]
0x00043668: 1f 35 01 71              cmp      w8, #0x4d
0x0004366c: a1 02 00 54              b.ne     #0x436c0
0x00043670: 88 0e 40 39              ldrb     w8, [x20, #3]
0x00043674: fb 67 00 b9              str      w27, [sp, #0x64]
0x00043678: 1f 05 00 71              cmp      w8, #1
0x0004367c: 41 05 00 54              b.ne     #0x43724
0x00043680: 88 12 40 b9              ldr      w8, [x20, #0x10]
0x00043684: ff 7f 07 a9              stp      xzr, xzr, [sp, #0x70]
0x00043688: ff 37 00 f9              str      xzr, [sp, #0x68]
0x0004368c: 99 02 08 8b              add      x25, x20, x8
0x00043690: e0 03 19 aa              mov      x0, x25
0x00043694: 4f b2 ff 97              bl       #0x2ffd0                   ; -> strlen@plt
0x00043698: 1f 40 00 b1              cmn      x0, #0x10
0x0004369c: e2 51 00 54              b.hs     #0x440d8
0x000436a0: f8 03 00 aa              mov      x24, x0
0x000436a4: 1f 5c 00 f1              cmp      x0, #0x17
0x000436a8: a2 09 00 54              b.hs     #0x437dc
0x000436ac: f6 0f 40 f9              ldr      x22, [sp, #0x18]
0x000436b0: 08 7b 1f 53              lsl      w8, w24, #1
0x000436b4: e8 a3 01 39              strb     w8, [sp, #0x68]
0x000436b8: 38 0a 00 b5              cbnz     x24, #0x437fc
0x000436bc: 54 00 00 14              b        #0x4380c                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x3f4
0x000436c0: 5b 01 80 52              mov      w27, #0xa
0x000436c4: 34 00 80 52              mov      w20, #1
0x000436c8: e8 03 42 39              ldrb     w8, [sp, #0x80]
0x000436cc: 68 00 00 36              tbz      w8, #0, #0x436d8
0x000436d0: e0 4b 40 f9              ldr      x0, [sp, #0x90]
0x000436d4: 73 b0 ff 97              bl       #0x2f8a0                   ; -> _ZdlPv@plt
0x000436d8: 74 4e 00 35              cbnz     w20, #0x440a4
0x000436dc: 68 06 40 f9              ldr      x8, [x19, #8]
0x000436e0: a8 00 00 b4              cbz      x8, #0x436f4
0x000436e4: f3 03 08 aa              mov      x19, x8
0x000436e8: 08 01 40 f9              ldr      x8, [x8]
0x000436ec: c8 ff ff b5              cbnz     x8, #0x436e4
0x000436f0: b5 ff ff 17              b        #0x435c4                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x1ac
0x000436f4: e8 03 13 aa              mov      x8, x19
0x000436f8: 09 0d 41 f8              ldr      x9, [x8, #0x10]!
0x000436fc: 2a 01 40 f9              ldr      x10, [x9]
0x00043700: 5f 01 13 eb              cmp      x10, x19
0x00043704: e0 f5 ff 54              b.eq     #0x435c0
0x00043708: 09 01 40 f9              ldr      x9, [x8]
0x0004370c: e8 03 09 aa              mov      x8, x9
0x00043710: 13 0d 41 f8              ldr      x19, [x8, #0x10]!
0x00043714: 6a 02 40 f9              ldr      x10, [x19]
0x00043718: 5f 01 09 eb              cmp      x10, x9
0x0004371c: 61 ff ff 54              b.ne     #0x43708
0x00043720: a9 ff ff 17              b        #0x435c4                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x1ac
0x00043724: 89 0a 40 b9              ldr      w9, [x20, #8]
0x00043728: 8a 92 00 91              add      x10, x20, #0x24
0x0004372c: 8b b2 00 91              add      x11, x20, #0x2c
0x00043730: 8c d2 00 91              add      x12, x20, #0x34
0x00043734: 3f 0d 00 71              cmp      w9, #3
0x00043738: 57 81 9f 9a              csel     x23, x10, xzr, hi
0x0004373c: 3f 11 00 71              cmp      w9, #4
0x00043740: 6a 81 9f 9a              csel     x10, x11, xzr, hi
0x00043744: 3f 15 00 71              cmp      w9, #5
0x00043748: 96 81 9f 9a              csel     x22, x12, xzr, hi
0x0004374c: 1f 09 00 71              cmp      w8, #2
0x00043750: ea db 03 a9              stp      x10, x22, [sp, #0x38]
0x00043754: f7 2f 00 f9              str      x23, [sp, #0x58]
0x00043758: a0 00 00 54              b.eq     #0x4376c
0x0004375c: 1f 0d 00 71              cmp      w8, #3
0x00043760: 03 08 00 54              b.lo     #0x43860
0x00043764: 88 1e 40 b9              ldr      w8, [x20, #0x1c]
0x00043768: c8 07 00 34              cbz      w8, #0x43860
0x0004376c: 80 a2 41 29              ldp      w0, w8, [x20, #0xc]
0x00043770: 96 02 08 8b              add      x22, x20, x8
0x00043774: 53 b1 ff 97              bl       #0x2fcc0                   ; -> malloc@plt
0x00043778: 82 0e 40 b9              ldr      w2, [x20, #0xc]
0x0004377c: e1 03 16 aa              mov      x1, x22
0x00043780: f8 03 00 aa              mov      x24, x0
0x00043784: c7 af ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00043788: 88 0e 40 b9              ldr      w8, [x20, #0xc]
0x0004378c: 0e 0a 00 b0              adrp     x14, #0x184000
0x00043790: ce c9 2c 91              add      x14, x14, #0xb32
0x00043794: e8 13 00 34              cbz      w8, #0x43a10
0x00043798: 8b 22 40 b9              ldr      w11, [x20, #0x20]
0x0004379c: ea 03 1f aa              mov      x10, xzr
0x000437a0: 1f 81 00 71              cmp      w8, #0x20
0x000437a4: 89 02 0b 8b              add      x9, x20, x11
0x000437a8: e3 0e 00 54              b.lo     #0x43984
0x000437ac: 0c 05 00 d1              sub      x12, x8, #1
0x000437b0: 9f 1d 00 f1              cmp      x12, #7
0x000437b4: 88 0e 00 54              b.hi     #0x43984
0x000437b8: 0a 01 0b 8b              add      x10, x8, x11
0x000437bc: 8a 02 0a 8b              add      x10, x20, x10
0x000437c0: 1f 03 0a eb              cmp      x24, x10
0x000437c4: 62 0c 00 54              b.hs     #0x43950
0x000437c8: 0a 03 08 8b              add      x10, x24, x8
0x000437cc: 3f 01 0a eb              cmp      x9, x10
0x000437d0: 02 0c 00 54              b.hs     #0x43950
0x000437d4: ea 03 1f aa              mov      x10, xzr
0x000437d8: 6b 00 00 14              b        #0x43984                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x56c
0x000437dc: 08 43 00 91              add      x8, x24, #0x10
0x000437e0: 17 ed 7c 92              and      x23, x8, #0xfffffffffffffff0
0x000437e4: e0 03 17 aa              mov      x0, x23
0x000437e8: 1a b6 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x000437ec: e8 02 40 b2              orr      x8, x23, #1
0x000437f0: f6 03 00 aa              mov      x22, x0
0x000437f4: e8 e3 06 a9              stp      x8, x24, [sp, #0x68]
0x000437f8: e0 3f 00 f9              str      x0, [sp, #0x78]
0x000437fc: e0 03 16 aa              mov      x0, x22
0x00043800: e1 03 19 aa              mov      x1, x25
0x00043804: e2 03 18 aa              mov      x2, x24
0x00043808: a6 af ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x0004380c: e0 27 40 f9              ldr      x0, [sp, #0x48]
0x00043810: a1 a3 01 d1              sub      x1, x29, #0x68
0x00043814: e2 03 02 91              add      x2, sp, #0x80
0x00043818: df 6a 38 38              strb     wzr, [x22, x24]
0x0004381c: 20 d2 ff 97              bl       #0x3809c
0x00043820: 18 00 40 f9              ldr      x24, [x0]
0x00043824: f8 1a 00 b5              cbnz     x24, #0x43b80
0x00043828: f9 03 00 aa              mov      x25, x0
0x0004382c: 00 0a 80 52              mov      w0, #0x50
0x00043830: 08 b6 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00043834: e9 03 42 39              ldrb     w9, [sp, #0x80]
0x00043838: e8 03 00 aa              mov      x8, x0
0x0004383c: f8 03 00 aa              mov      x24, x0
0x00043840: 1f 0d 02 f8              str      xzr, [x8, #0x20]!
0x00043844: 1f fc 02 a9              stp      xzr, xzr, [x0, #0x28]
0x00043848: c9 02 00 37              tbnz     w9, #0, #0x438a0
0x0004384c: e9 4b 40 f9              ldr      x9, [sp, #0x90]
0x00043850: e0 23 c0 3d              ldr      q0, [sp, #0x80]
0x00043854: 09 09 00 f9              str      x9, [x8, #0x10]
0x00043858: 00 01 80 3d              str      q0, [x8]
0x0004385c: b6 00 00 14              b        #0x43b34                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x71c
0x00043860: 88 12 40 b9              ldr      w8, [x20, #0x10]
0x00043864: ff 7f 07 a9              stp      xzr, xzr, [sp, #0x70]
0x00043868: ff 37 00 f9              str      xzr, [sp, #0x68]
0x0004386c: 99 02 08 8b              add      x25, x20, x8
0x00043870: e0 03 19 aa              mov      x0, x25
0x00043874: d7 b1 ff 97              bl       #0x2ffd0                   ; -> strlen@plt
0x00043878: 1f 40 00 b1              cmn      x0, #0x10
0x0004387c: e2 42 00 54              b.hs     #0x440d8
0x00043880: f8 03 00 aa              mov      x24, x0
0x00043884: 1f 5c 00 f1              cmp      x0, #0x17
0x00043888: 22 02 00 54              b.hs     #0x438cc
0x0004388c: f6 0f 40 f9              ldr      x22, [sp, #0x18]
0x00043890: 08 7b 1f 53              lsl      w8, w24, #1
0x00043894: e8 a3 01 39              strb     w8, [sp, #0x68]
0x00043898: b8 02 00 b5              cbnz     x24, #0x438ec
0x0004389c: 18 00 00 14              b        #0x438fc                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x4e4
0x000438a0: fa 47 40 f9              ldr      x26, [sp, #0x88]
0x000438a4: 5f 43 00 b1              cmn      x26, #0x10
0x000438a8: 82 41 00 54              b.hs     #0x440d8
0x000438ac: e1 4b 40 f9              ldr      x1, [sp, #0x90]
0x000438b0: 5f 5f 00 f1              cmp      x26, #0x17
0x000438b4: 22 12 00 54              b.hs     #0x43af8
0x000438b8: 48 7b 1f 53              lsl      w8, w26, #1
0x000438bc: 17 87 00 91              add      x23, x24, #0x21
0x000438c0: 08 83 00 39              strb     w8, [x24, #0x20]
0x000438c4: fa 12 00 b5              cbnz     x26, #0x43b20
0x000438c8: 99 00 00 14              b        #0x43b2c                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x714
0x000438cc: 08 43 00 91              add      x8, x24, #0x10
0x000438d0: 17 ed 7c 92              and      x23, x8, #0xfffffffffffffff0
0x000438d4: e0 03 17 aa              mov      x0, x23
0x000438d8: de b5 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x000438dc: e8 02 40 b2              orr      x8, x23, #1
0x000438e0: f6 03 00 aa              mov      x22, x0
0x000438e4: e8 e3 06 a9              stp      x8, x24, [sp, #0x68]
0x000438e8: e0 3f 00 f9              str      x0, [sp, #0x78]
0x000438ec: e0 03 16 aa              mov      x0, x22
0x000438f0: e1 03 19 aa              mov      x1, x25
0x000438f4: e2 03 18 aa              mov      x2, x24
0x000438f8: 6a af ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x000438fc: e0 27 40 f9              ldr      x0, [sp, #0x48]
0x00043900: a1 a3 01 d1              sub      x1, x29, #0x68
0x00043904: e2 03 02 91              add      x2, sp, #0x80
0x00043908: df 6a 38 38              strb     wzr, [x22, x24]
0x0004390c: e4 d1 ff 97              bl       #0x3809c
0x00043910: 18 00 40 f9              ldr      x24, [x0]
0x00043914: b8 39 00 b5              cbnz     x24, #0x44048
0x00043918: f9 03 00 aa              mov      x25, x0
0x0004391c: 00 0a 80 52              mov      w0, #0x50
0x00043920: cc b5 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00043924: e9 03 42 39              ldrb     w9, [sp, #0x80]
0x00043928: e8 03 00 aa              mov      x8, x0
0x0004392c: f8 03 00 aa              mov      x24, x0
0x00043930: 1f 0d 02 f8              str      xzr, [x8, #0x20]!
0x00043934: 1f fc 02 a9              stp      xzr, xzr, [x0, #0x28]
0x00043938: c9 14 00 37              tbnz     w9, #0, #0x43bd0
0x0004393c: e9 4b 40 f9              ldr      x9, [sp, #0x90]
0x00043940: e0 23 c0 3d              ldr      q0, [sp, #0x80]
0x00043944: 09 09 00 f9              str      x9, [x8, #0x10]
0x00043948: 00 01 80 3d              str      q0, [x8]
0x0004394c: ac 01 00 14              b        #0x43ffc                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0xbe4
0x00043950: 20 05 40 ad              ldp      q0, q1, [x9]
0x00043954: 0a 69 7b 92              and      x10, x8, #0xffffffe0
0x00043958: 0b 43 00 91              add      x11, x24, #0x10
0x0004395c: ec 03 0a aa              mov      x12, x10
0x00043960: 62 8d 7f ad              ldp      q2, q3, [x11, #-0x10]
0x00043964: 8c 81 00 f1              subs     x12, x12, #0x20
0x00043968: 02 1c 22 6e              eor      v2.16b, v0.16b, v2.16b
0x0004396c: 23 1c 23 6e              eor      v3.16b, v1.16b, v3.16b
0x00043970: 62 8d 3f ad              stp      q2, q3, [x11, #-0x10]
0x00043974: 6b 81 00 91              add      x11, x11, #0x20
0x00043978: 41 ff ff 54              b.ne     #0x43960
0x0004397c: 5f 01 08 eb              cmp      x10, x8
0x00043980: 20 01 00 54              b.eq     #0x439a4
0x00043984: 4c 09 40 92              and      x12, x10, #7
0x00043988: 0b 6b 6a 38              ldrb     w11, [x24, x10]
0x0004398c: 2c 69 6c 38              ldrb     w12, [x9, x12]
0x00043990: 8b 01 0b 4a              eor      w11, w12, w11
0x00043994: 0b 6b 2a 38              strb     w11, [x24, x10]
0x00043998: 4a 05 00 91              add      x10, x10, #1
0x0004399c: 1f 01 0a eb              cmp      x8, x10
0x000439a0: 21 ff ff 54              b.ne     #0x43984
0x000439a4: 88 0e 40 b9              ldr      w8, [x20, #0xc]
0x000439a8: 1f 05 00 71              cmp      w8, #1
0x000439ac: 2b 03 00 54              b.lt     #0x43a10
0x000439b0: 1f 05 00 71              cmp      w8, #1
0x000439b4: 61 00 00 54              b.ne     #0x439c0
0x000439b8: e9 03 1f aa              mov      x9, xzr
0x000439bc: 0e 00 00 14              b        #0x439f4                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x5dc
0x000439c0: 09 79 7f 92              and      x9, x8, #0xfffffffe
0x000439c4: 0a 07 00 91              add      x10, x24, #1
0x000439c8: eb 03 09 aa              mov      x11, x9
0x000439cc: 4c f1 5f 38              ldurb    w12, [x10, #-1]
0x000439d0: 4d 01 40 39              ldrb     w13, [x10]
0x000439d4: 6b 09 00 f1              subs     x11, x11, #2
0x000439d8: cc 69 6c 38              ldrb     w12, [x14, x12]
0x000439dc: cd 69 6d 38              ldrb     w13, [x14, x13]
0x000439e0: 4c f1 1f 38              sturb    w12, [x10, #-1]
0x000439e4: 4d 25 00 38              strb     w13, [x10], #2
0x000439e8: 21 ff ff 54              b.ne     #0x439cc
0x000439ec: 3f 01 08 eb              cmp      x9, x8
0x000439f0: 00 01 00 54              b.eq     #0x43a10
0x000439f4: 08 01 09 cb              sub      x8, x8, x9
0x000439f8: 09 03 09 8b              add      x9, x24, x9
0x000439fc: 2a 01 40 39              ldrb     w10, [x9]
0x00043a00: 08 05 00 f1              subs     x8, x8, #1
0x00043a04: ca 69 6a 38              ldrb     w10, [x14, x10]
0x00043a08: 2a 15 00 38              strb     w10, [x9], #1
0x00043a0c: 81 ff ff 54              b.ne     #0x439fc
0x00043a10: e0 03 18 aa              mov      x0, x24
0x00043a14: ff 7f 07 a9              stp      xzr, xzr, [sp, #0x70]
0x00043a18: ff 37 00 f9              str      xzr, [sp, #0x68]
0x00043a1c: 6d b1 ff 97              bl       #0x2ffd0                   ; -> strlen@plt
0x00043a20: 1f 40 00 b1              cmn      x0, #0x10
0x00043a24: a2 35 00 54              b.hs     #0x440d8
0x00043a28: f9 03 00 aa              mov      x25, x0
0x00043a2c: 1f 5c 00 f1              cmp      x0, #0x17
0x00043a30: c2 00 00 54              b.hs     #0x43a48
0x00043a34: f6 0f 40 f9              ldr      x22, [sp, #0x18]
0x00043a38: 28 7b 1f 53              lsl      w8, w25, #1
0x00043a3c: e8 a3 01 39              strb     w8, [sp, #0x68]
0x00043a40: 59 01 00 b5              cbnz     x25, #0x43a68
0x00043a44: 0d 00 00 14              b        #0x43a78                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x660
0x00043a48: 28 43 00 91              add      x8, x25, #0x10
0x00043a4c: 17 ed 7c 92              and      x23, x8, #0xfffffffffffffff0
0x00043a50: e0 03 17 aa              mov      x0, x23
0x00043a54: 7f b5 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00043a58: e8 02 40 b2              orr      x8, x23, #1
0x00043a5c: f6 03 00 aa              mov      x22, x0
0x00043a60: e8 e7 06 a9              stp      x8, x25, [sp, #0x68]
0x00043a64: e0 3f 00 f9              str      x0, [sp, #0x78]
0x00043a68: e0 03 16 aa              mov      x0, x22
0x00043a6c: e1 03 18 aa              mov      x1, x24
0x00043a70: e2 03 19 aa              mov      x2, x25
0x00043a74: 0b af ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00043a78: e0 27 40 f9              ldr      x0, [sp, #0x48]
0x00043a7c: a1 a3 01 d1              sub      x1, x29, #0x68
0x00043a80: e2 03 02 91              add      x2, sp, #0x80
0x00043a84: df 6a 39 38              strb     wzr, [x22, x25]
0x00043a88: 85 d1 ff 97              bl       #0x3809c
0x00043a8c: 19 00 40 f9              ldr      x25, [x0]
0x00043a90: f9 0f 00 b5              cbnz     x25, #0x43c8c
0x00043a94: fa 03 00 aa              mov      x26, x0
0x00043a98: 00 0a 80 52              mov      w0, #0x50
0x00043a9c: 6d b5 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00043aa0: e9 03 42 39              ldrb     w9, [sp, #0x80]
0x00043aa4: e8 03 00 aa              mov      x8, x0
0x00043aa8: f9 03 00 aa              mov      x25, x0
0x00043aac: 1f 0d 02 f8              str      xzr, [x8, #0x20]!
0x00043ab0: 1f fc 02 a9              stp      xzr, xzr, [x0, #0x28]
0x00043ab4: c9 00 00 37              tbnz     w9, #0, #0x43acc
0x00043ab8: e9 4b 40 f9              ldr      x9, [sp, #0x90]
0x00043abc: e0 23 c0 3d              ldr      q0, [sp, #0x80]
0x00043ac0: 09 09 00 f9              str      x9, [x8, #0x10]
0x00043ac4: 00 01 80 3d              str      q0, [x8]
0x00043ac8: 5d 00 00 14              b        #0x43c3c                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x824
0x00043acc: fb 47 40 f9              ldr      x27, [sp, #0x88]
0x00043ad0: 7f 43 00 b1              cmn      x27, #0x10
0x00043ad4: 22 30 00 54              b.hs     #0x440d8
0x00043ad8: e1 4b 40 f9              ldr      x1, [sp, #0x90]
0x00043adc: 7f 5f 00 f1              cmp      x27, #0x17
0x00043ae0: 02 09 00 54              b.hs     #0x43c00
0x00043ae4: 68 7b 1f 53              lsl      w8, w27, #1
0x00043ae8: 37 87 00 91              add      x23, x25, #0x21
0x00043aec: 28 83 00 39              strb     w8, [x25, #0x20]
0x00043af0: fb 09 00 b5              cbnz     x27, #0x43c2c
0x00043af4: 51 00 00 14              b        #0x43c38                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x820
0x00043af8: 48 43 00 91              add      x8, x26, #0x10
0x00043afc: 16 ed 7c 92              and      x22, x8, #0xfffffffffffffff0
0x00043b00: e0 03 16 aa              mov      x0, x22
0x00043b04: f7 03 01 aa              mov      x23, x1
0x00043b08: 52 b5 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00043b0c: c8 02 40 b2              orr      x8, x22, #1
0x00043b10: e1 03 17 aa              mov      x1, x23
0x00043b14: f7 03 00 aa              mov      x23, x0
0x00043b18: 1a 83 02 a9              stp      x26, x0, [x24, #0x28]
0x00043b1c: 08 13 00 f9              str      x8, [x24, #0x20]
0x00043b20: e0 03 17 aa              mov      x0, x23
0x00043b24: e2 03 1a aa              mov      x2, x26
0x00043b28: de ae ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00043b2c: ff 6a 3a 38              strb     wzr, [x23, x26]
0x00043b30: fa 2b 40 f9              ldr      x26, [sp, #0x50]
0x00043b34: a8 83 59 f8              ldur     x8, [x29, #-0x68]
0x00043b38: e9 27 40 f9              ldr      x9, [sp, #0x48]
0x00043b3c: 1f ff 03 a9              stp      xzr, xzr, [x24, #0x38]
0x00043b40: 1f 27 00 f9              str      xzr, [x24, #0x48]
0x00043b44: 1f 7f 00 a9              stp      xzr, xzr, [x24]
0x00043b48: 08 0b 00 f9              str      x8, [x24, #0x10]
0x00043b4c: 38 03 00 f9              str      x24, [x25]
0x00043b50: 28 01 40 f9              ldr      x8, [x9]
0x00043b54: 08 01 40 f9              ldr      x8, [x8]
0x00043b58: 88 00 00 b4              cbz      x8, #0x43b68
0x00043b5c: 28 01 00 f9              str      x8, [x9]
0x00043b60: 21 03 40 f9              ldr      x1, [x25]
0x00043b64: 02 00 00 14              b        #0x43b6c                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x754
0x00043b68: e1 03 18 aa              mov      x1, x24
0x00043b6c: 40 d3 40 f9              ldr      x0, [x26, #0x1a0]
0x00043b70: d6 c5 ff 97              bl       #0x352c8
0x00043b74: 48 d7 40 f9              ldr      x8, [x26, #0x1a8]
0x00043b78: 08 05 00 91              add      x8, x8, #1
0x00043b7c: 48 d7 00 f9              str      x8, [x26, #0x1a8]
0x00043b80: f6 03 18 aa              mov      x22, x24
0x00043b84: c8 8e 43 38              ldrb     w8, [x22, #0x38]!
0x00043b88: 68 00 00 37              tbnz     w8, #0, #0x43b94
0x00043b8c: df 02 00 79              strh     wzr, [x22]
0x00043b90: 09 00 00 14              b        #0x43bb4                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x79c
0x00043b94: 08 27 40 f9              ldr      x8, [x24, #0x48]
0x00043b98: 1f 01 00 39              strb     wzr, [x8]
0x00043b9c: 08 e3 40 39              ldrb     w8, [x24, #0x38]
0x00043ba0: 1f 23 00 f9              str      xzr, [x24, #0x40]
0x00043ba4: 88 00 00 36              tbz      w8, #0, #0x43bb4
0x00043ba8: 00 27 40 f9              ldr      x0, [x24, #0x48]
0x00043bac: 3d af ff 97              bl       #0x2f8a0                   ; -> _ZdlPv@plt
0x00043bb0: 1f 1f 00 f9              str      xzr, [x24, #0x38]
0x00043bb4: ff ff 03 a9              stp      xzr, xzr, [sp, #0x38]
0x00043bb8: ff 2f 00 f9              str      xzr, [sp, #0x58]
0x00043bbc: e8 3f 40 f9              ldr      x8, [sp, #0x78]
0x00043bc0: e0 83 c6 3c              ldur     q0, [sp, #0x68]
0x00043bc4: c8 0a 00 f9              str      x8, [x22, #0x10]
0x00043bc8: c0 02 80 3d              str      q0, [x22]
0x00043bcc: 43 00 00 14              b        #0x43cd8                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x8c0
0x00043bd0: fa 47 40 f9              ldr      x26, [sp, #0x88]
0x00043bd4: 5f 43 00 b1              cmn      x26, #0x10
0x00043bd8: 02 28 00 54              b.hs     #0x440d8
0x00043bdc: e1 4b 40 f9              ldr      x1, [sp, #0x90]
0x00043be0: f6 03 1c aa              mov      x22, x28
0x00043be4: 5f 5f 00 f1              cmp      x26, #0x17
0x00043be8: a2 1e 00 54              b.hs     #0x43fbc
0x00043bec: 48 7b 1f 53              lsl      w8, w26, #1
0x00043bf0: 17 87 00 91              add      x23, x24, #0x21
0x00043bf4: 08 83 00 39              strb     w8, [x24, #0x20]
0x00043bf8: 7a 1f 00 b5              cbnz     x26, #0x43fe4
0x00043bfc: fd 00 00 14              b        #0x43ff0                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0xbd8
0x00043c00: 68 43 00 91              add      x8, x27, #0x10
0x00043c04: 00 ed 7c 92              and      x0, x8, #0xfffffffffffffff0
0x00043c08: e0 0b 00 f9              str      x0, [sp, #0x10]
0x00043c0c: f7 03 01 aa              mov      x23, x1
0x00043c10: 10 b5 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00043c14: e8 0b 40 f9              ldr      x8, [sp, #0x10]
0x00043c18: e1 03 17 aa              mov      x1, x23
0x00043c1c: f7 03 00 aa              mov      x23, x0
0x00043c20: 3b 83 02 a9              stp      x27, x0, [x25, #0x28]
0x00043c24: 08 01 40 b2              orr      x8, x8, #1
0x00043c28: 28 13 00 f9              str      x8, [x25, #0x20]
0x00043c2c: e0 03 17 aa              mov      x0, x23
0x00043c30: e2 03 1b aa              mov      x2, x27
0x00043c34: 9b ae ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00043c38: ff 6a 3b 38              strb     wzr, [x23, x27]
0x00043c3c: a8 83 59 f8              ldur     x8, [x29, #-0x68]
0x00043c40: e9 27 40 f9              ldr      x9, [sp, #0x48]
0x00043c44: 3f ff 03 a9              stp      xzr, xzr, [x25, #0x38]
0x00043c48: 3f 27 00 f9              str      xzr, [x25, #0x48]
0x00043c4c: 3f 7f 00 a9              stp      xzr, xzr, [x25]
0x00043c50: 28 0b 00 f9              str      x8, [x25, #0x10]
0x00043c54: 59 03 00 f9              str      x25, [x26]
0x00043c58: 28 01 40 f9              ldr      x8, [x9]
0x00043c5c: 08 01 40 f9              ldr      x8, [x8]
0x00043c60: 88 00 00 b4              cbz      x8, #0x43c70
0x00043c64: 28 01 00 f9              str      x8, [x9]
0x00043c68: 41 03 40 f9              ldr      x1, [x26]
0x00043c6c: 02 00 00 14              b        #0x43c74                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x85c
0x00043c70: e1 03 19 aa              mov      x1, x25
0x00043c74: f6 2b 40 f9              ldr      x22, [sp, #0x50]
0x00043c78: c0 d2 40 f9              ldr      x0, [x22, #0x1a0]
0x00043c7c: 93 c5 ff 97              bl       #0x352c8
0x00043c80: c8 d6 40 f9              ldr      x8, [x22, #0x1a8]
0x00043c84: 08 05 00 91              add      x8, x8, #1
0x00043c88: c8 d6 00 f9              str      x8, [x22, #0x1a8]
0x00043c8c: f6 03 19 aa              mov      x22, x25
0x00043c90: c8 8e 43 38              ldrb     w8, [x22, #0x38]!
0x00043c94: 68 00 00 37              tbnz     w8, #0, #0x43ca0
0x00043c98: df 02 00 79              strh     wzr, [x22]
0x00043c9c: 09 00 00 14              b        #0x43cc0                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x8a8
0x00043ca0: 28 27 40 f9              ldr      x8, [x25, #0x48]
0x00043ca4: 1f 01 00 39              strb     wzr, [x8]
0x00043ca8: 28 e3 40 39              ldrb     w8, [x25, #0x38]
0x00043cac: 3f 23 00 f9              str      xzr, [x25, #0x40]
0x00043cb0: 88 00 00 36              tbz      w8, #0, #0x43cc0
0x00043cb4: 20 27 40 f9              ldr      x0, [x25, #0x48]
0x00043cb8: fa ae ff 97              bl       #0x2f8a0                   ; -> _ZdlPv@plt
0x00043cbc: 3f 1f 00 f9              str      xzr, [x25, #0x38]
0x00043cc0: e8 3f 40 f9              ldr      x8, [sp, #0x78]
0x00043cc4: e0 83 c6 3c              ldur     q0, [sp, #0x68]
0x00043cc8: e0 03 18 aa              mov      x0, x24
0x00043ccc: c8 0a 00 f9              str      x8, [x22, #0x10]
0x00043cd0: c0 02 80 3d              str      q0, [x22]
0x00043cd4: db af ff 97              bl       #0x2fc40                   ; -> free@plt
0x00043cd8: c1 0c 00 f0              adrp     x1, #0x1de000
0x00043cdc: 80 da 42 29              ldp      w0, w22, [x20, #0x14]
0x00043ce0: 21 b8 45 f9              ldr      x1, [x1, #0xb70]           ; -> _ZSt7nothrow@got
0x00043ce4: 57 b1 ff 97              bl       #0x30240                   ; -> _ZnamRKSt9nothrow_t@plt
0x00043ce8: f9 03 00 aa              mov      x25, x0
0x00043cec: 00 04 80 52              mov      w0, #0x20
0x00043cf0: d8 b4 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00043cf4: e8 17 40 f9              ldr      x8, [sp, #0x28]
0x00043cf8: f8 03 00 aa              mov      x24, x0
0x00043cfc: fb 03 00 aa              mov      x27, x0
0x00043d00: 1f 64 01 a9              stp      xzr, x25, [x0, #0x10]
0x00043d04: 1f 8f 00 f8              str      xzr, [x24, #8]!
0x00043d08: 08 00 00 f9              str      x8, [x0]
0x00043d0c: 39 03 00 b4              cbz      x25, #0x43d70
0x00043d10: 82 16 40 b9              ldr      w2, [x20, #0x14]
0x00043d14: 81 02 16 8b              add      x1, x20, x22
0x00043d18: e0 03 19 aa              mov      x0, x25
0x00043d1c: 61 ae ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00043d20: e0 13 40 f9              ldr      x0, [sp, #0x20]
0x00043d24: e1 a3 01 91              add      x1, sp, #0x68
0x00043d28: e2 03 02 91              add      x2, sp, #0x80
0x00043d2c: cd b3 ff 97              bl       #0x30c60                   ; -> _ZNSt6__ndk16__treeINS_12__value_typeINS_12basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEEENS_10shared_ptrIhEEEENS_19__map_value_compareIS7_SA_NS_4lessIS7_EELb1EEENS5_ISA_EEE12__find_equalIS7_EERPNS_16__tree_node_baseIPvEERPNS_15__tree_end_nodeISL_EERKT_@plt
0x00043d30: 1a 00 40 f9              ldr      x26, [x0]
0x00043d34: 7a 08 00 b5              cbnz     x26, #0x43e40
0x00043d38: f7 03 00 aa              mov      x23, x0
0x00043d3c: 00 09 80 52              mov      w0, #0x48
0x00043d40: c4 b4 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00043d44: e9 03 42 39              ldrb     w9, [sp, #0x80]
0x00043d48: e8 03 00 aa              mov      x8, x0
0x00043d4c: fa 03 00 aa              mov      x26, x0
0x00043d50: 1f 0d 02 f8              str      xzr, [x8, #0x20]!
0x00043d54: 1f fc 02 a9              stp      xzr, xzr, [x0, #0x28]
0x00043d58: 69 01 00 37              tbnz     w9, #0, #0x43d84
0x00043d5c: e9 4b 40 f9              ldr      x9, [sp, #0x90]
0x00043d60: e0 23 c0 3d              ldr      q0, [sp, #0x80]
0x00043d64: 09 09 00 f9              str      x9, [x8, #0x10]
0x00043d68: 00 01 80 3d              str      q0, [x8]
0x00043d6c: 22 00 00 14              b        #0x43df4                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x9dc
0x00043d70: fa 2b 40 f9              ldr      x26, [sp, #0x50]
0x00043d74: 28 00 80 52              mov      w8, #1
0x00043d78: 34 00 80 52              mov      w20, #1
0x00043d7c: e8 67 00 b9              str      w8, [sp, #0x64]
0x00043d80: 80 00 00 14              b        #0x43f80                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0xb68
0x00043d84: fc 0b 00 f9              str      x28, [sp, #0x10]
0x00043d88: fc 47 40 f9              ldr      x28, [sp, #0x88]
0x00043d8c: 9f 43 00 b1              cmn      x28, #0x10
0x00043d90: 42 1a 00 54              b.hs     #0x440d8
0x00043d94: e1 4b 40 f9              ldr      x1, [sp, #0x90]
0x00043d98: 9f 5f 00 f1              cmp      x28, #0x17
0x00043d9c: c2 00 00 54              b.hs     #0x43db4
0x00043da0: 88 7b 1f 53              lsl      w8, w28, #1
0x00043da4: 56 87 00 91              add      x22, x26, #0x21
0x00043da8: 48 83 00 39              strb     w8, [x26, #0x20]
0x00043dac: bc 01 00 b5              cbnz     x28, #0x43de0
0x00043db0: 0f 00 00 14              b        #0x43dec                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x9d4
0x00043db4: 88 43 00 91              add      x8, x28, #0x10
0x00043db8: 00 ed 7c 92              and      x0, x8, #0xfffffffffffffff0
0x00043dbc: e0 07 00 f9              str      x0, [sp, #8]
0x00043dc0: f6 03 01 aa              mov      x22, x1
0x00043dc4: a3 b4 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00043dc8: e8 07 40 f9              ldr      x8, [sp, #8]
0x00043dcc: e1 03 16 aa              mov      x1, x22
0x00043dd0: f6 03 00 aa              mov      x22, x0
0x00043dd4: 5c 83 02 a9              stp      x28, x0, [x26, #0x28]
0x00043dd8: 08 01 40 b2              orr      x8, x8, #1
0x00043ddc: 48 13 00 f9              str      x8, [x26, #0x20]
0x00043de0: e0 03 16 aa              mov      x0, x22
0x00043de4: e2 03 1c aa              mov      x2, x28
0x00043de8: 2e ae ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00043dec: df 6a 3c 38              strb     wzr, [x22, x28]
0x00043df0: fc 0b 40 f9              ldr      x28, [sp, #0x10]
0x00043df4: e8 37 40 f9              ldr      x8, [sp, #0x68]
0x00043df8: e9 13 40 f9              ldr      x9, [sp, #0x20]
0x00043dfc: 5f ff 03 a9              stp      xzr, xzr, [x26, #0x38]
0x00043e00: 5f 7f 00 a9              stp      xzr, xzr, [x26]
0x00043e04: 48 0b 00 f9              str      x8, [x26, #0x10]
0x00043e08: fa 02 00 f9              str      x26, [x23]
0x00043e0c: 28 01 40 f9              ldr      x8, [x9]
0x00043e10: 08 01 40 f9              ldr      x8, [x8]
0x00043e14: 88 00 00 b4              cbz      x8, #0x43e24
0x00043e18: 28 01 00 f9              str      x8, [x9]
0x00043e1c: e1 02 40 f9              ldr      x1, [x23]
0x00043e20: 02 00 00 14              b        #0x43e28                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0xa10
0x00043e24: e1 03 1a aa              mov      x1, x26
0x00043e28: f6 2b 40 f9              ldr      x22, [sp, #0x50]
0x00043e2c: c0 ae 40 f9              ldr      x0, [x22, #0x158]
0x00043e30: 26 c5 ff 97              bl       #0x352c8
0x00043e34: c8 b2 40 f9              ldr      x8, [x22, #0x160]
0x00043e38: 08 05 00 91              add      x8, x8, #1
0x00043e3c: c8 b2 00 f9              str      x8, [x22, #0x160]
0x00043e40: 08 7f 5f c8              ldxr     x8, [x24]
0x00043e44: 08 05 00 91              add      x8, x8, #1
0x00043e48: 08 7f 09 c8              stxr     w9, x8, [x24]
0x00043e4c: a9 ff ff 35              cbnz     w9, #0x43e40
0x00043e50: 56 23 40 f9              ldr      x22, [x26, #0x40]
0x00043e54: 59 ef 03 a9              stp      x25, x27, [x26, #0x38]
0x00043e58: fa 5f 45 a9              ldp      x26, x23, [sp, #0x50]
0x00043e5c: b6 01 00 b4              cbz      x22, #0x43e90
0x00043e60: c8 22 00 91              add      x8, x22, #8
0x00043e64: 09 fd 5f c8              ldaxr    x9, [x8]
0x00043e68: 2a 05 00 d1              sub      x10, x9, #1
0x00043e6c: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00043e70: ab ff ff 35              cbnz     w11, #0x43e64
0x00043e74: e9 00 00 b5              cbnz     x9, #0x43e90
0x00043e78: c8 02 40 f9              ldr      x8, [x22]
0x00043e7c: e0 03 16 aa              mov      x0, x22
0x00043e80: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00043e84: 00 01 3f d6              blr      x8
0x00043e88: e0 03 16 aa              mov      x0, x22
0x00043e8c: 31 b3 ff 97              bl       #0x30b50                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00043e90: 88 16 40 b9              ldr      w8, [x20, #0x14]
0x00043e94: 48 c3 00 f9              str      x8, [x26, #0x180]
0x00043e98: b7 04 00 b4              cbz      x23, #0x43f2c
0x00043e9c: c1 0c 00 f0              adrp     x1, #0x1de000
0x00043ea0: e0 66 40 29              ldp      w0, w25, [x23]
0x00043ea4: 21 b8 45 f9              ldr      x1, [x1, #0xb70]           ; -> _ZSt7nothrow@got
0x00043ea8: e6 b0 ff 97              bl       #0x30240                   ; -> _ZnamRKSt9nothrow_t@plt
0x00043eac: f7 03 00 aa              mov      x23, x0
0x00043eb0: 00 04 80 52              mov      w0, #0x20
0x00043eb4: 67 b4 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00043eb8: c8 0c 00 f0              adrp     x8, #0x1de000
0x00043ebc: 08 19 45 f9              ldr      x8, [x8, #0xa30]           ; -> _ZTVNSt6__ndk120__shared_ptr_pointerIPhNS_14default_deleteIA_hEENS_9allocatorIhEEEE@got
0x00043ec0: 1f 5c 01 a9              stp      xzr, x23, [x0, #0x10]
0x00043ec4: 08 41 00 91              add      x8, x8, #0x10
0x00043ec8: 08 7c 00 a9              stp      x8, xzr, [x0]
0x00043ecc: 56 df 40 f9              ldr      x22, [x26, #0x1b8]
0x00043ed0: 57 03 1b a9              stp      x23, x0, [x26, #0x1b0]
0x00043ed4: d6 01 00 b4              cbz      x22, #0x43f0c
0x00043ed8: c8 22 00 91              add      x8, x22, #8
0x00043edc: 09 fd 5f c8              ldaxr    x9, [x8]
0x00043ee0: 2a 05 00 d1              sub      x10, x9, #1
0x00043ee4: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00043ee8: ab ff ff 35              cbnz     w11, #0x43edc
0x00043eec: e9 00 00 b5              cbnz     x9, #0x43f08
0x00043ef0: c8 02 40 f9              ldr      x8, [x22]
0x00043ef4: e0 03 16 aa              mov      x0, x22
0x00043ef8: 08 09 40 f9              ldr      x8, [x8, #0x10]            ; -> _ZN5IESNN25clCreateProgramWithSourceEP11_cl_contextjPPKcPKmPi@got
0x00043efc: 00 01 3f d6              blr      x8
0x00043f00: e0 03 16 aa              mov      x0, x22
0x00043f04: 13 b3 ff 97              bl       #0x30b50                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00043f08: 57 db 40 f9              ldr      x23, [x26, #0x1b0]
0x00043f0c: 57 03 00 b4              cbz      x23, #0x43f74
0x00043f10: f6 2f 40 f9              ldr      x22, [sp, #0x58]
0x00043f14: 81 02 19 8b              add      x1, x20, x25
0x00043f18: e0 03 17 aa              mov      x0, x23
0x00043f1c: c2 02 40 b9              ldr      w2, [x22]
0x00043f20: e0 ad ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00043f24: c8 02 40 b9              ldr      w8, [x22]
0x00043f28: 48 e3 00 f9              str      x8, [x26, #0x1c0]
0x00043f2c: e9 1f 40 f9              ldr      x9, [sp, #0x38]
0x00043f30: c9 00 00 b4              cbz      x9, #0x43f48
0x00043f34: 28 05 40 b9              ldr      w8, [x9, #4]
0x00043f38: 88 02 08 8b              add      x8, x20, x8
0x00043f3c: 48 e7 00 f9              str      x8, [x26, #0x1c8]
0x00043f40: 28 01 40 b9              ldr      w8, [x9]
0x00043f44: 48 eb 00 f9              str      x8, [x26, #0x1d0]
0x00043f48: e9 23 40 f9              ldr      x9, [sp, #0x40]
0x00043f4c: c9 00 00 b4              cbz      x9, #0x43f64
0x00043f50: 28 01 40 b9              ldr      w8, [x9]
0x00043f54: 48 cb 00 f9              str      x8, [x26, #0x190]
0x00043f58: 28 05 40 b9              ldr      w8, [x9, #4]
0x00043f5c: 88 02 08 8b              add      x8, x20, x8
0x00043f60: 48 c7 00 f9              str      x8, [x26, #0x188]
0x00043f64: e0 03 1a aa              mov      x0, x26
0x00043f68: 66 b1 ff 97              bl       #0x30500                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv@plt
0x00043f6c: f4 03 1f 2a              mov      w20, wzr
0x00043f70: 04 00 00 14              b        #0x43f80                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0xb68
0x00043f74: 28 00 80 52              mov      w8, #1
0x00043f78: 34 00 80 52              mov      w20, #1
0x00043f7c: e8 67 00 b9              str      w8, [sp, #0x64]
0x00043f80: 08 ff 5f c8              ldaxr    x8, [x24]
0x00043f84: 09 05 00 d1              sub      x9, x8, #1
0x00043f88: 09 ff 0a c8              stlxr    w10, x9, [x24]
0x00043f8c: aa ff ff 35              cbnz     w10, #0x43f80
0x00043f90: e8 00 00 b5              cbnz     x8, #0x43fac
0x00043f94: 68 03 40 f9              ldr      x8, [x27]
0x00043f98: e0 03 1b aa              mov      x0, x27
0x00043f9c: 08 09 40 f9              ldr      x8, [x8, #0x10]            ; -> _ZN5IESNN25clCreateProgramWithSourceEP11_cl_contextjPPKcPKmPi@got
0x00043fa0: 00 01 3f d6              blr      x8
0x00043fa4: e0 03 1b aa              mov      x0, x27
0x00043fa8: ea b2 ff 97              bl       #0x30b50                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00043fac: fb 67 40 b9              ldr      w27, [sp, #0x64]
0x00043fb0: e8 03 42 39              ldrb     w8, [sp, #0x80]
0x00043fb4: e8 b8 07 37              tbnz     w8, #0, #0x436d0
0x00043fb8: c8 fd ff 17              b        #0x436d8                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x2c0
0x00043fbc: 48 43 00 91              add      x8, x26, #0x10
0x00043fc0: 1b ed 7c 92              and      x27, x8, #0xfffffffffffffff0
0x00043fc4: e0 03 1b aa              mov      x0, x27
0x00043fc8: f7 03 01 aa              mov      x23, x1
0x00043fcc: 21 b4 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00043fd0: 68 03 40 b2              orr      x8, x27, #1
0x00043fd4: e1 03 17 aa              mov      x1, x23
0x00043fd8: f7 03 00 aa              mov      x23, x0
0x00043fdc: 1a 83 02 a9              stp      x26, x0, [x24, #0x28]
0x00043fe0: 08 13 00 f9              str      x8, [x24, #0x20]
0x00043fe4: e0 03 17 aa              mov      x0, x23
0x00043fe8: e2 03 1a aa              mov      x2, x26
0x00043fec: ad ad ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00043ff0: ff 6a 3a 38              strb     wzr, [x23, x26]
0x00043ff4: fa 2b 40 f9              ldr      x26, [sp, #0x50]
0x00043ff8: fc 03 16 aa              mov      x28, x22
0x00043ffc: a8 83 59 f8              ldur     x8, [x29, #-0x68]
0x00044000: e9 27 40 f9              ldr      x9, [sp, #0x48]
0x00044004: 1f ff 03 a9              stp      xzr, xzr, [x24, #0x38]
0x00044008: 1f 27 00 f9              str      xzr, [x24, #0x48]
0x0004400c: 1f 7f 00 a9              stp      xzr, xzr, [x24]
0x00044010: 08 0b 00 f9              str      x8, [x24, #0x10]
0x00044014: 38 03 00 f9              str      x24, [x25]
0x00044018: 28 01 40 f9              ldr      x8, [x9]
0x0004401c: 08 01 40 f9              ldr      x8, [x8]                   ; -> _ZNSt6__ndk116__copy_unalignedINS_6vectorIbNS_9allocatorIbEEEELb0EEENS_14__bit_iteratorIT_Lb0EXLi0EEEENS5_IS6_XT0_EXLi0EEEES8_S7_@got
0x00044020: 88 00 00 b4              cbz      x8, #0x44030
0x00044024: 28 01 00 f9              str      x8, [x9]
0x00044028: 21 03 40 f9              ldr      x1, [x25]
0x0004402c: 02 00 00 14              b        #0x44034                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0xc1c
0x00044030: e1 03 18 aa              mov      x1, x24
0x00044034: 40 d3 40 f9              ldr      x0, [x26, #0x1a0]
0x00044038: a4 c4 ff 97              bl       #0x352c8
0x0004403c: 48 d7 40 f9              ldr      x8, [x26, #0x1a8]
0x00044040: 08 05 00 91              add      x8, x8, #1
0x00044044: 48 d7 00 f9              str      x8, [x26, #0x1a8]
0x00044048: f6 03 18 aa              mov      x22, x24
0x0004404c: c8 8e 43 38              ldrb     w8, [x22, #0x38]!
0x00044050: 68 00 00 37              tbnz     w8, #0, #0x4405c
0x00044054: df 02 00 79              strh     wzr, [x22]
0x00044058: d9 fe ff 17              b        #0x43bbc                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x7a4
0x0004405c: 08 27 40 f9              ldr      x8, [x24, #0x48]
0x00044060: 1f 01 00 39              strb     wzr, [x8]
0x00044064: 08 e3 40 39              ldrb     w8, [x24, #0x38]
0x00044068: 1f 23 00 f9              str      xzr, [x24, #0x40]
0x0004406c: 88 da 07 36              tbz      w8, #0, #0x43bbc
0x00044070: 00 27 40 f9              ldr      x0, [x24, #0x48]
0x00044074: 0b ae ff 97              bl       #0x2f8a0                   ; -> _ZdlPv@plt
0x00044078: 1f 1f 00 f9              str      xzr, [x24, #0x38]
0x0004407c: d0 fe ff 17              b        #0x43bbc                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0x7a4
0x00044080: e1 09 00 f0              adrp     x1, #0x183000
0x00044084: 02 0a 00 90              adrp     x2, #0x184000
0x00044088: 21 e0 21 91              add      x1, x1, #0x878
0x0004408c: 42 38 2c 91              add      x2, x2, #0xb0e
0x00044090: 20 00 80 52              mov      w0, #1
0x00044094: 53 b4 ff 97              bl       #0x311e0                   ; -> _ZN6BYTENN9ByteNNLogEiPKcS1_z@plt
0x00044098: 5b 01 80 52              mov      w27, #0xa
0x0004409c: 02 00 00 14              b        #0x440a4                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE+0xc8c
0x000440a0: fb 03 1f 2a              mov      w27, wzr
0x000440a4: 88 17 40 f9              ldr      x8, [x28, #0x28]
0x000440a8: a9 03 5a f8              ldur     x9, [x29, #-0x60]
0x000440ac: 1f 01 09 eb              cmp      x8, x9
0x000440b0: 61 01 00 54              b.ne     #0x440dc
0x000440b4: e0 03 1b 2a              mov      w0, w27
0x000440b8: fd 7b 52 a9              ldp      x29, x30, [sp, #0x120]
0x000440bc: f4 4f 51 a9              ldp      x20, x19, [sp, #0x110]
0x000440c0: f6 57 50 a9              ldp      x22, x21, [sp, #0x100]
0x000440c4: f8 5f 4f a9              ldp      x24, x23, [sp, #0xf0]
0x000440c8: fa 67 4e a9              ldp      x26, x25, [sp, #0xe0]
0x000440cc: fc 6f 4d a9              ldp      x28, x27, [sp, #0xd0]
0x000440d0: ff c3 04 91              add      sp, sp, #0x130
0x000440d4: c0 03 5f d6              ret      
0x000440d8: d6 b2 ff 97              bl       #0x30c30                   ; -> abort@plt
0x000440dc: 99 b4 ff 97              bl       #0x31340                   ; -> __stack_chk_fail@plt
