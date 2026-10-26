; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_musically/native/libbytenn.so
; target  : _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv (0x36fec)
; start   : 0x36fec
; size    : 0xbc8
; bytes   : 0xbc8   instructions: 754
; generated: 2026-10-23T23:33:33

0x00036fec: ff 83 03 d1              sub      sp, sp, #0xe0
0x00036ff0: fc 6f 08 a9              stp      x28, x27, [sp, #0x80]
0x00036ff4: fa 67 09 a9              stp      x26, x25, [sp, #0x90]
0x00036ff8: f8 5f 0a a9              stp      x24, x23, [sp, #0xa0]
0x00036ffc: f6 57 0b a9              stp      x22, x21, [sp, #0xb0]
0x00037000: f4 4f 0c a9              stp      x20, x19, [sp, #0xc0]
0x00037004: fd 7b 0d a9              stp      x29, x30, [sp, #0xd0]
0x00037008: fd 43 03 91              add      x29, sp, #0xd0
0x0003700c: 59 d0 3b d5              mrs      x25, tpidr_el0
0x00037010: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x00037014: 14 80 06 91              add      x20, x0, #0x1a0
0x00037018: a8 83 1a f8              stur     x8, [x29, #-0x58]
0x0003701c: 1a cc 40 f9              ldr      x26, [x0, #0x198]
0x00037020: 9f 02 1a eb              cmp      x20, x26
0x00037024: 60 5a 00 54              b.eq     #0x37b70
0x00037028: 09 a0 05 91              add      x9, x0, #0x168
0x0003702c: e9 1f 00 f9              str      x9, [sp, #0x38]
0x00037030: 3b 0d 00 f0              adrp     x27, #0x1de000
0x00037034: 29 0d 00 f0              adrp     x9, #0x1de000
0x00037038: 7b bb 45 f9              ldr      x27, [x27, #0xb70]         ; -> _ZSt7nothrow@got
0x0003703c: 29 19 45 f9              ldr      x9, [x9, #0xa30]           ; -> _ZTVNSt6__ndk120__shared_ptr_pointerIPhNS_14default_deleteIA_hEENS_9allocatorIhEEEE@got
0x00037040: e8 83 01 91              add      x8, sp, #0x60
0x00037044: 08 01 40 b2              orr      x8, x8, #1
0x00037048: f3 03 00 aa              mov      x19, x0
0x0003704c: 1c 40 05 91              add      x28, x0, #0x150
0x00037050: e8 23 00 f9              str      x8, [sp, #0x40]
0x00037054: 28 41 00 91              add      x8, x9, #0x10
0x00037058: e8 1b 00 f9              str      x8, [sp, #0x30]
0x0003705c: 04 00 00 14              b        #0x3706c                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x80
0x00037060: fa 03 09 aa              mov      x26, x9
0x00037064: 9f 02 1a eb              cmp      x20, x26
0x00037068: 40 58 00 54              b.eq     #0x37b70
0x0003706c: ff ff 06 a9              stp      xzr, xzr, [sp, #0x68]
0x00037070: ff 33 00 f9              str      xzr, [sp, #0x60]
0x00037074: e8 03 1a aa              mov      x8, x26
0x00037078: 09 0d 42 38              ldrb     w9, [x8, #0x20]!
0x0003707c: c9 00 00 37              tbnz     w9, #0, #0x37094
0x00037080: 09 09 40 f9              ldr      x9, [x8, #0x10]
0x00037084: 00 01 c0 3d              ldr      q0, [x8]
0x00037088: e9 3b 00 f9              str      x9, [sp, #0x70]
0x0003708c: e0 1b 80 3d              str      q0, [sp, #0x60]
0x00037090: 19 00 00 14              b        #0x370f4                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x108
0x00037094: 56 17 40 f9              ldr      x22, [x26, #0x28]
0x00037098: df 42 00 b1              cmn      x22, #0x10
0x0003709c: 82 58 00 54              b.hs     #0x37bac
0x000370a0: 57 1b 40 f9              ldr      x23, [x26, #0x30]
0x000370a4: df 5e 00 f1              cmp      x22, #0x17
0x000370a8: c2 00 00 54              b.hs     #0x370c0
0x000370ac: f5 23 40 f9              ldr      x21, [sp, #0x40]
0x000370b0: c8 7a 1f 53              lsl      w8, w22, #1
0x000370b4: e8 83 01 39              strb     w8, [sp, #0x60]
0x000370b8: 56 01 00 b5              cbnz     x22, #0x370e0
0x000370bc: 0d 00 00 14              b        #0x370f0                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x104
0x000370c0: c8 42 00 91              add      x8, x22, #0x10
0x000370c4: 18 ed 7c 92              and      x24, x8, #0xfffffffffffffff0
0x000370c8: e0 03 18 aa              mov      x0, x24
0x000370cc: e1 e7 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x000370d0: 08 03 40 b2              orr      x8, x24, #1
0x000370d4: f5 03 00 aa              mov      x21, x0
0x000370d8: e8 5b 06 a9              stp      x8, x22, [sp, #0x60]
0x000370dc: e0 3b 00 f9              str      x0, [sp, #0x70]
0x000370e0: e0 03 15 aa              mov      x0, x21
0x000370e4: e1 03 17 aa              mov      x1, x23
0x000370e8: e2 03 16 aa              mov      x2, x22
0x000370ec: 6d e1 ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x000370f0: bf 6a 36 38              strb     wzr, [x21, x22]
0x000370f4: f5 03 1a aa              mov      x21, x26
0x000370f8: a8 8e 43 38              ldrb     w8, [x21, #0x38]!
0x000370fc: e8 00 00 37              tbnz     w8, #0, #0x37118
0x00037100: 09 fd 41 d3              lsr      x9, x8, #1
0x00037104: 3f 11 00 f1              cmp      x9, #4
0x00037108: 43 50 00 54              b.lo     #0x37b10
0x0003710c: e8 00 00 37              tbnz     w8, #0, #0x37128
0x00037110: a9 06 00 91              add      x9, x21, #1
0x00037114: 06 00 00 14              b        #0x3712c                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x140
0x00037118: 49 23 40 f9              ldr      x9, [x26, #0x40]
0x0003711c: 3f 11 00 f1              cmp      x9, #4
0x00037120: 62 ff ff 54              b.hs     #0x3710c
0x00037124: 7b 02 00 14              b        #0x37b10                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0xb24
0x00037128: 49 27 40 f9              ldr      x9, [x26, #0x48]
0x0003712c: 29 01 40 39              ldrb     w9, [x9]
0x00037130: 3f 15 01 71              cmp      w9, #0x45
0x00037134: e1 4e 00 54              b.ne     #0x37b10
0x00037138: 68 00 00 37              tbnz     w8, #0, #0x37144
0x0003713c: a9 06 00 91              add      x9, x21, #1
0x00037140: 02 00 00 14              b        #0x37148                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x15c
0x00037144: 49 27 40 f9              ldr      x9, [x26, #0x48]
0x00037148: 29 05 40 39              ldrb     w9, [x9, #1]
0x0003714c: 3f 71 01 71              cmp      w9, #0x5c
0x00037150: 01 4e 00 54              b.ne     #0x37b10
0x00037154: 68 00 00 37              tbnz     w8, #0, #0x37160
0x00037158: a9 06 00 91              add      x9, x21, #1
0x0003715c: 02 00 00 14              b        #0x37164                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x178
0x00037160: 49 27 40 f9              ldr      x9, [x26, #0x48]
0x00037164: 29 09 40 39              ldrb     w9, [x9, #2]
0x00037168: 3f b9 01 71              cmp      w9, #0x6e
0x0003716c: 21 4d 00 54              b.ne     #0x37b10
0x00037170: 68 00 00 37              tbnz     w8, #0, #0x3717c
0x00037174: a9 06 00 91              add      x9, x21, #1
0x00037178: 02 00 00 14              b        #0x37180                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x194
0x0003717c: 49 27 40 f9              ldr      x9, [x26, #0x48]
0x00037180: 29 0d 40 39              ldrb     w9, [x9, #3]
0x00037184: 3f 29 00 71              cmp      w9, #0xa
0x00037188: 41 4c 00 54              b.ne     #0x37b10
0x0003718c: 68 00 00 37              tbnz     w8, #0, #0x37198
0x00037190: 08 7d 41 d3              ubfx     x8, x8, #1, #0x1f
0x00037194: 02 00 00 14              b        #0x3719c                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x1b0
0x00037198: 48 23 40 f9              ldr      x8, [x26, #0x40]
0x0003719c: 03 11 00 d1              sub      x3, x8, #4
0x000371a0: e0 23 01 91              add      x0, sp, #0x48
0x000371a4: 82 00 80 52              mov      w2, #4
0x000371a8: e1 03 15 aa              mov      x1, x21
0x000371ac: e4 03 15 aa              mov      x4, x21
0x000371b0: 19 ff ff 97              bl       #0x36e14
0x000371b4: a8 02 40 39              ldrb     w8, [x21]
0x000371b8: 68 00 00 37              tbnz     w8, #0, #0x371c4
0x000371bc: bf 02 00 79              strh     wzr, [x21]
0x000371c0: 09 00 00 14              b        #0x371e4                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x1f8
0x000371c4: 48 27 40 f9              ldr      x8, [x26, #0x48]
0x000371c8: 1f 01 00 39              strb     wzr, [x8]
0x000371cc: 48 e3 40 39              ldrb     w8, [x26, #0x38]
0x000371d0: 5f 23 00 f9              str      xzr, [x26, #0x40]
0x000371d4: 88 00 00 36              tbz      w8, #0, #0x371e4
0x000371d8: 40 27 40 f9              ldr      x0, [x26, #0x48]
0x000371dc: b1 e1 ff 97              bl       #0x2f8a0                   ; -> _ZdlPv@plt
0x000371e0: 5f 1f 00 f9              str      xzr, [x26, #0x38]
0x000371e4: ea 2f 40 f9              ldr      x10, [sp, #0x58]
0x000371e8: e0 83 c4 3c              ldur     q0, [sp, #0x48]
0x000371ec: e9 03 1f aa              mov      x9, xzr
0x000371f0: e8 03 1f 2a              mov      w8, wzr
0x000371f4: aa 0a 00 f9              str      x10, [x21, #0x10]
0x000371f8: a0 02 80 3d              str      q0, [x21]
0x000371fc: 4a e3 40 39              ldrb     w10, [x26, #0x38]
0x00037200: ac 06 00 91              add      x12, x21, #1
0x00037204: 4b fd 41 d3              lsr      x11, x10, #1
0x00037208: ed 03 0b aa              mov      x13, x11
0x0003720c: 4a 01 00 36              tbz      w10, #0, #0x37234
0x00037210: 08 00 00 14              b        #0x37230                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x244
0x00037214: ad 01 09 8b              add      x13, x13, x9
0x00037218: ad 05 40 39              ldrb     w13, [x13, #1]
0x0003721c: bf b9 01 71              cmp      w13, #0x6e
0x00037220: 40 06 00 54              b.eq     #0x372e8
0x00037224: 29 05 00 91              add      x9, x9, #1
0x00037228: ed 03 0b aa              mov      x13, x11
0x0003722c: 4a 00 00 36              tbz      w10, #0, #0x37234
0x00037230: 4d 23 40 f9              ldr      x13, [x26, #0x40]
0x00037234: ad 05 00 d1              sub      x13, x13, #1
0x00037238: bf 01 09 eb              cmp      x13, x9
0x0003723c: 29 05 00 54              b.ls     #0x372e0
0x00037240: ed 03 0c aa              mov      x13, x12
0x00037244: c9 02 00 b4              cbz      x9, #0x3729c
0x00037248: 4a 00 00 36              tbz      w10, #0, #0x37250
0x0003724c: 4d 27 40 f9              ldr      x13, [x26, #0x48]
0x00037250: ad 01 09 8b              add      x13, x13, x9
0x00037254: ad f1 5f 38              ldurb    w13, [x13, #-1]
0x00037258: bf 81 00 71              cmp      w13, #0x20
0x0003725c: a1 02 00 54              b.ne     #0x372b0
0x00037260: ed 03 0c aa              mov      x13, x12
0x00037264: 4a 00 00 36              tbz      w10, #0, #0x3726c
0x00037268: 4d 27 40 f9              ldr      x13, [x26, #0x48]
0x0003726c: ad 69 69 38              ldrb     w13, [x13, x9]
0x00037270: bf 81 00 71              cmp      w13, #0x20
0x00037274: e0 01 00 54              b.eq     #0x372b0
0x00037278: ed 03 0c aa              mov      x13, x12
0x0003727c: 4a 00 00 36              tbz      w10, #0, #0x37284
0x00037280: 4d 27 40 f9              ldr      x13, [x26, #0x48]
0x00037284: ad 69 69 38              ldrb     w13, [x13, x9]
0x00037288: bf 71 01 71              cmp      w13, #0x5c
0x0003728c: 08 05 88 1a              cinc     w8, w8, ne
0x00037290: ed 03 0c aa              mov      x13, x12
0x00037294: 2a 01 00 37              tbnz     w10, #0, #0x372b8
0x00037298: 09 00 00 14              b        #0x372bc                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x2d0
0x0003729c: 4a 00 00 36              tbz      w10, #0, #0x372a4
0x000372a0: 4d 27 40 f9              ldr      x13, [x26, #0x48]
0x000372a4: ad 01 40 39              ldrb     w13, [x13]
0x000372a8: bf 81 00 71              cmp      w13, #0x20
0x000372ac: 61 01 00 54              b.ne     #0x372d8
0x000372b0: ed 03 0c aa              mov      x13, x12
0x000372b4: 4a 00 00 36              tbz      w10, #0, #0x372bc
0x000372b8: 4d 27 40 f9              ldr      x13, [x26, #0x48]
0x000372bc: ad 69 69 38              ldrb     w13, [x13, x9]
0x000372c0: bf 71 01 71              cmp      w13, #0x5c
0x000372c4: 01 fb ff 54              b.ne     #0x37224
0x000372c8: ed 03 0c aa              mov      x13, x12
0x000372cc: 4a fa 07 36              tbz      w10, #0, #0x37214
0x000372d0: 4d 27 40 f9              ldr      x13, [x26, #0x48]
0x000372d4: d0 ff ff 17              b        #0x37214                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x228
0x000372d8: 08 05 00 11              add      w8, w8, #1
0x000372dc: d2 ff ff 17              b        #0x37224                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x238
0x000372e0: f7 03 1f 2a              mov      w23, wzr
0x000372e4: 03 00 00 14              b        #0x372f0                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x304
0x000372e8: 1f 09 00 71              cmp      w8, #2
0x000372ec: f7 d7 9f 1a              cset     w23, gt
0x000372f0: 75 c2 40 f9              ldr      x21, [x19, #0x180]
0x000372f4: e1 03 1b aa              mov      x1, x27
0x000372f8: a0 fa 7f d3              lsl      x0, x21, #1
0x000372fc: d1 e3 ff 97              bl       #0x30240                   ; -> _ZnamRKSt9nothrow_t@plt
0x00037300: f8 03 00 aa              mov      x24, x0
0x00037304: 00 04 80 52              mov      w0, #0x20
0x00037308: 52 e7 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x0003730c: e8 1b 40 f9              ldr      x8, [sp, #0x30]
0x00037310: fb 03 00 aa              mov      x27, x0
0x00037314: f6 03 00 aa              mov      x22, x0
0x00037318: 1f 60 01 a9              stp      xzr, x24, [x0, #0x10]
0x0003731c: 7f 8f 00 f8              str      xzr, [x27, #8]!
0x00037320: 08 00 00 f9              str      x8, [x0]
0x00037324: f8 17 00 f9              str      x24, [sp, #0x28]
0x00037328: 38 03 00 b4              cbz      x24, #0x3738c
0x0003732c: f9 0f 00 f9              str      x25, [sp, #0x18]
0x00037330: 77 00 00 34              cbz      w23, #0x3733c
0x00037334: a8 12 00 d1              sub      x8, x21, #4
0x00037338: 68 c2 00 f9              str      x8, [x19, #0x180]
0x0003733c: e1 23 01 91              add      x1, sp, #0x48
0x00037340: e2 83 01 91              add      x2, sp, #0x60
0x00037344: e0 03 1c aa              mov      x0, x28
0x00037348: 46 e6 ff 97              bl       #0x30c60                   ; -> _ZNSt6__ndk16__treeINS_12__value_typeINS_12basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEEENS_10shared_ptrIhEEEENS_19__map_value_compareIS7_SA_NS_4lessIS7_EELb1EEENS5_ISA_EEE12__find_equalIS7_EERPNS_16__tree_node_baseIPvEERPNS_15__tree_end_nodeISL_EERKT_@plt
0x0003734c: 18 00 40 f9              ldr      x24, [x0]
0x00037350: 18 09 00 b5              cbnz     x24, #0x37470
0x00037354: f9 03 00 aa              mov      x25, x0
0x00037358: 00 09 80 52              mov      w0, #0x48
0x0003735c: 3d e7 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00037360: e9 83 41 39              ldrb     w9, [sp, #0x60]
0x00037364: e8 03 00 aa              mov      x8, x0
0x00037368: f8 03 00 aa              mov      x24, x0
0x0003736c: 1f 0d 02 f8              str      xzr, [x8, #0x20]!
0x00037370: 1f fc 02 a9              stp      xzr, xzr, [x0, #0x28]
0x00037374: c9 01 00 37              tbnz     w9, #0, #0x373ac
0x00037378: e9 3b 40 f9              ldr      x9, [sp, #0x70]
0x0003737c: e0 1b c0 3d              ldr      q0, [sp, #0x60]
0x00037380: 09 09 00 f9              str      x9, [x8, #0x10]
0x00037384: 00 01 80 3d              str      q0, [x8]
0x00037388: 29 00 00 14              b        #0x3742c                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x440
0x0003738c: 61 0a 00 90              adrp     x1, #0x183000
0x00037390: 62 0a 00 90              adrp     x2, #0x183000
0x00037394: 20 00 80 52              mov      w0, #1
0x00037398: 21 e0 21 91              add      x1, x1, #0x878
0x0003739c: 42 84 38 91              add      x2, x2, #0xe21
0x000373a0: 35 00 80 52              mov      w21, #1
0x000373a4: 8f e7 ff 97              bl       #0x311e0                   ; -> _ZN6BYTENN9ByteNNLogEiPKcS1_z@plt
0x000373a8: c8 01 00 14              b        #0x37ac8                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0xadc
0x000373ac: f5 03 16 aa              mov      x21, x22
0x000373b0: f6 03 1c aa              mov      x22, x28
0x000373b4: fc 37 40 f9              ldr      x28, [sp, #0x68]
0x000373b8: 9f 43 00 b1              cmn      x28, #0x10
0x000373bc: 82 3f 00 54              b.hs     #0x37bac
0x000373c0: e1 3b 40 f9              ldr      x1, [sp, #0x70]
0x000373c4: 9f 5f 00 f1              cmp      x28, #0x17
0x000373c8: f7 13 00 b9              str      w23, [sp, #0x10]
0x000373cc: c2 00 00 54              b.hs     #0x373e4
0x000373d0: 88 7b 1f 53              lsl      w8, w28, #1
0x000373d4: 17 87 00 91              add      x23, x24, #0x21
0x000373d8: 08 83 00 39              strb     w8, [x24, #0x20]
0x000373dc: bc 01 00 b5              cbnz     x28, #0x37410
0x000373e0: 0f 00 00 14              b        #0x3741c                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x430
0x000373e4: 88 43 00 91              add      x8, x28, #0x10
0x000373e8: 00 ed 7c 92              and      x0, x8, #0xfffffffffffffff0
0x000373ec: e0 13 00 f9              str      x0, [sp, #0x20]
0x000373f0: f7 03 01 aa              mov      x23, x1
0x000373f4: 17 e7 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x000373f8: e8 13 40 f9              ldr      x8, [sp, #0x20]
0x000373fc: e1 03 17 aa              mov      x1, x23
0x00037400: f7 03 00 aa              mov      x23, x0
0x00037404: 1c 83 02 a9              stp      x28, x0, [x24, #0x28]
0x00037408: 08 01 40 b2              orr      x8, x8, #1
0x0003740c: 08 13 00 f9              str      x8, [x24, #0x20]
0x00037410: e0 03 17 aa              mov      x0, x23
0x00037414: e2 03 1c aa              mov      x2, x28
0x00037418: a2 e0 ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x0003741c: ff 6a 3c 38              strb     wzr, [x23, x28]
0x00037420: f7 13 40 b9              ldr      w23, [sp, #0x10]
0x00037424: fc 03 16 aa              mov      x28, x22
0x00037428: f6 03 15 aa              mov      x22, x21
0x0003742c: e8 27 40 f9              ldr      x8, [sp, #0x48]
0x00037430: 1f ff 03 a9              stp      xzr, xzr, [x24, #0x38]
0x00037434: 1f 7f 00 a9              stp      xzr, xzr, [x24]
0x00037438: 08 0b 00 f9              str      x8, [x24, #0x10]
0x0003743c: 38 03 00 f9              str      x24, [x25]
0x00037440: 88 03 40 f9              ldr      x8, [x28]
0x00037444: 08 01 40 f9              ldr      x8, [x8]
0x00037448: 88 00 00 b4              cbz      x8, #0x37458
0x0003744c: 88 03 00 f9              str      x8, [x28]
0x00037450: 21 03 40 f9              ldr      x1, [x25]
0x00037454: 02 00 00 14              b        #0x3745c                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x470
0x00037458: e1 03 18 aa              mov      x1, x24
0x0003745c: 60 ae 40 f9              ldr      x0, [x19, #0x158]
0x00037460: 9a f7 ff 97              bl       #0x352c8
0x00037464: 68 b2 40 f9              ldr      x8, [x19, #0x160]
0x00037468: 08 05 00 91              add      x8, x8, #1
0x0003746c: 68 b2 00 f9              str      x8, [x19, #0x160]
0x00037470: 68 82 41 b9              ldr      w8, [x19, #0x180]
0x00037474: 00 1f 40 f9              ldr      x0, [x24, #0x38]
0x00037478: e1 17 40 f9              ldr      x1, [sp, #0x28]
0x0003747c: 1f 01 00 71              cmp      w8, #0
0x00037480: 08 a5 88 1a              cinc     w8, w8, lt
0x00037484: 02 7d 01 13              asr      w2, w8, #1
0x00037488: 46 e3 ff 97              bl       #0x301a0                   ; -> _ZN6BYTENN30float16buffer_to_float32bufferEPtPfi@plt
0x0003748c: 68 c2 40 f9              ldr      x8, [x19, #0x180]
0x00037490: e1 23 01 91              add      x1, sp, #0x48
0x00037494: e2 83 01 91              add      x2, sp, #0x60
0x00037498: e0 03 1c aa              mov      x0, x28
0x0003749c: 08 f9 7f d3              lsl      x8, x8, #1
0x000374a0: 68 c2 00 f9              str      x8, [x19, #0x180]
0x000374a4: fc 13 00 f9              str      x28, [sp, #0x20]
0x000374a8: ee e5 ff 97              bl       #0x30c60                   ; -> _ZNSt6__ndk16__treeINS_12__value_typeINS_12basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEEENS_10shared_ptrIhEEEENS_19__map_value_compareIS7_SA_NS_4lessIS7_EELb1EEENS5_ISA_EEE12__find_equalIS7_EERPNS_16__tree_node_baseIPvEERPNS_15__tree_end_nodeISL_EERKT_@plt
0x000374ac: 1c 00 40 f9              ldr      x28, [x0]
0x000374b0: 9c 07 00 b5              cbnz     x28, #0x375a0
0x000374b4: f8 03 00 aa              mov      x24, x0
0x000374b8: 00 09 80 52              mov      w0, #0x48
0x000374bc: e5 e6 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x000374c0: e9 83 41 39              ldrb     w9, [sp, #0x60]
0x000374c4: e8 03 00 aa              mov      x8, x0
0x000374c8: fc 03 00 aa              mov      x28, x0
0x000374cc: 1f 0d 02 f8              str      xzr, [x8, #0x20]!
0x000374d0: 1f fc 02 a9              stp      xzr, xzr, [x0, #0x28]
0x000374d4: c9 00 00 37              tbnz     w9, #0, #0x374ec
0x000374d8: e9 3b 40 f9              ldr      x9, [sp, #0x70]
0x000374dc: e0 1b c0 3d              ldr      q0, [sp, #0x60]
0x000374e0: 09 09 00 f9              str      x9, [x8, #0x10]
0x000374e4: 00 01 80 3d              str      q0, [x8]
0x000374e8: 1c 00 00 14              b        #0x37558                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x56c
0x000374ec: f9 37 40 f9              ldr      x25, [sp, #0x68]
0x000374f0: 3f 43 00 b1              cmn      x25, #0x10
0x000374f4: c2 35 00 54              b.hs     #0x37bac
0x000374f8: e1 3b 40 f9              ldr      x1, [sp, #0x70]
0x000374fc: 3f 5f 00 f1              cmp      x25, #0x17
0x00037500: f7 13 00 b9              str      w23, [sp, #0x10]
0x00037504: c2 00 00 54              b.hs     #0x3751c
0x00037508: 28 7b 1f 53              lsl      w8, w25, #1
0x0003750c: 97 87 00 91              add      x23, x28, #0x21
0x00037510: 88 83 00 39              strb     w8, [x28, #0x20]
0x00037514: 99 01 00 b5              cbnz     x25, #0x37544
0x00037518: 0e 00 00 14              b        #0x37550                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x564
0x0003751c: 28 43 00 91              add      x8, x25, #0x10
0x00037520: 15 ed 7c 92              and      x21, x8, #0xfffffffffffffff0
0x00037524: e0 03 15 aa              mov      x0, x21
0x00037528: f7 03 01 aa              mov      x23, x1
0x0003752c: c9 e6 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00037530: a8 02 40 b2              orr      x8, x21, #1
0x00037534: e1 03 17 aa              mov      x1, x23
0x00037538: f7 03 00 aa              mov      x23, x0
0x0003753c: 99 83 02 a9              stp      x25, x0, [x28, #0x28]
0x00037540: 88 13 00 f9              str      x8, [x28, #0x20]
0x00037544: e0 03 17 aa              mov      x0, x23
0x00037548: e2 03 19 aa              mov      x2, x25
0x0003754c: 55 e0 ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00037550: ff 6a 39 38              strb     wzr, [x23, x25]
0x00037554: f7 13 40 b9              ldr      w23, [sp, #0x10]
0x00037558: e8 27 40 f9              ldr      x8, [sp, #0x48]
0x0003755c: e9 13 40 f9              ldr      x9, [sp, #0x20]
0x00037560: 9f ff 03 a9              stp      xzr, xzr, [x28, #0x38]
0x00037564: 9f 7f 00 a9              stp      xzr, xzr, [x28]
0x00037568: 88 0b 00 f9              str      x8, [x28, #0x10]
0x0003756c: 1c 03 00 f9              str      x28, [x24]
0x00037570: 28 01 40 f9              ldr      x8, [x9]                   ; -> _ZNSt6__ndk116__copy_unalignedINS_6vectorIbNS_9allocatorIbEEEELb0EEENS_14__bit_iteratorIT_Lb0EXLi0EEEENS5_IS6_XT0_EXLi0EEEES8_S7_@got
0x00037574: 08 01 40 f9              ldr      x8, [x8]
0x00037578: 88 00 00 b4              cbz      x8, #0x37588
0x0003757c: 28 01 00 f9              str      x8, [x9]
0x00037580: 01 03 40 f9              ldr      x1, [x24]
0x00037584: 02 00 00 14              b        #0x3758c                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x5a0
0x00037588: e1 03 1c aa              mov      x1, x28
0x0003758c: 60 ae 40 f9              ldr      x0, [x19, #0x158]
0x00037590: 4e f7 ff 97              bl       #0x352c8
0x00037594: 68 b2 40 f9              ldr      x8, [x19, #0x160]
0x00037598: 08 05 00 91              add      x8, x8, #1
0x0003759c: 68 b2 00 f9              str      x8, [x19, #0x160]
0x000375a0: e0 1f 40 f9              ldr      x0, [sp, #0x38]
0x000375a4: e1 23 01 91              add      x1, sp, #0x48
0x000375a8: e2 83 01 91              add      x2, sp, #0x60
0x000375ac: ad e5 ff 97              bl       #0x30c60                   ; -> _ZNSt6__ndk16__treeINS_12__value_typeINS_12basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEEENS_10shared_ptrIhEEEENS_19__map_value_compareIS7_SA_NS_4lessIS7_EELb1EEENS5_ISA_EEE12__find_equalIS7_EERPNS_16__tree_node_baseIPvEERPNS_15__tree_end_nodeISL_EERKT_@plt
0x000375b0: 18 00 40 f9              ldr      x24, [x0]
0x000375b4: f8 07 00 b5              cbnz     x24, #0x376b0
0x000375b8: f9 03 00 aa              mov      x25, x0
0x000375bc: 00 09 80 52              mov      w0, #0x48
0x000375c0: a4 e6 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x000375c4: e9 83 41 39              ldrb     w9, [sp, #0x60]
0x000375c8: e8 03 00 aa              mov      x8, x0
0x000375cc: f8 03 00 aa              mov      x24, x0
0x000375d0: 1f 0d 02 f8              str      xzr, [x8, #0x20]!
0x000375d4: 1f fc 02 a9              stp      xzr, xzr, [x0, #0x28]
0x000375d8: c9 00 00 37              tbnz     w9, #0, #0x375f0
0x000375dc: e9 3b 40 f9              ldr      x9, [sp, #0x70]
0x000375e0: e0 1b c0 3d              ldr      q0, [sp, #0x60]
0x000375e4: 09 09 00 f9              str      x9, [x8, #0x10]
0x000375e8: 00 01 80 3d              str      q0, [x8]
0x000375ec: 1f 00 00 14              b        #0x37668                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x67c
0x000375f0: f7 13 00 b9              str      w23, [sp, #0x10]
0x000375f4: f7 03 16 aa              mov      x23, x22
0x000375f8: f6 37 40 f9              ldr      x22, [sp, #0x68]
0x000375fc: df 42 00 b1              cmn      x22, #0x10
0x00037600: 62 2d 00 54              b.hs     #0x37bac
0x00037604: e1 3b 40 f9              ldr      x1, [sp, #0x70]
0x00037608: df 5e 00 f1              cmp      x22, #0x17
0x0003760c: c2 00 00 54              b.hs     #0x37624
0x00037610: c8 7a 1f 53              lsl      w8, w22, #1
0x00037614: 15 87 00 91              add      x21, x24, #0x21
0x00037618: 08 83 00 39              strb     w8, [x24, #0x20]
0x0003761c: b6 01 00 b5              cbnz     x22, #0x37650
0x00037620: 0f 00 00 14              b        #0x3765c                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x670
0x00037624: c8 42 00 91              add      x8, x22, #0x10
0x00037628: 00 ed 7c 92              and      x0, x8, #0xfffffffffffffff0
0x0003762c: e0 07 00 f9              str      x0, [sp, #8]
0x00037630: f5 03 01 aa              mov      x21, x1
0x00037634: 87 e6 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00037638: e8 07 40 f9              ldr      x8, [sp, #8]
0x0003763c: e1 03 15 aa              mov      x1, x21
0x00037640: f5 03 00 aa              mov      x21, x0
0x00037644: 16 83 02 a9              stp      x22, x0, [x24, #0x28]
0x00037648: 08 01 40 b2              orr      x8, x8, #1
0x0003764c: 08 13 00 f9              str      x8, [x24, #0x20]
0x00037650: e0 03 15 aa              mov      x0, x21
0x00037654: e2 03 16 aa              mov      x2, x22
0x00037658: 12 e0 ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x0003765c: bf 6a 36 38              strb     wzr, [x21, x22]
0x00037660: f6 03 17 aa              mov      x22, x23
0x00037664: f7 13 40 b9              ldr      w23, [sp, #0x10]
0x00037668: e8 27 40 f9              ldr      x8, [sp, #0x48]
0x0003766c: e9 1f 40 f9              ldr      x9, [sp, #0x38]
0x00037670: 1f ff 03 a9              stp      xzr, xzr, [x24, #0x38]
0x00037674: 1f 7f 00 a9              stp      xzr, xzr, [x24]
0x00037678: 08 0b 00 f9              str      x8, [x24, #0x10]
0x0003767c: 38 03 00 f9              str      x24, [x25]
0x00037680: 28 01 40 f9              ldr      x8, [x9]                   ; -> _ZNSt6__ndk116__copy_unalignedINS_6vectorIbNS_9allocatorIbEEEELb0EEENS_14__bit_iteratorIT_Lb0EXLi0EEEENS5_IS6_XT0_EXLi0EEEES8_S7_@got
0x00037684: 08 01 40 f9              ldr      x8, [x8]
0x00037688: 88 00 00 b4              cbz      x8, #0x37698
0x0003768c: 28 01 00 f9              str      x8, [x9]
0x00037690: 21 03 40 f9              ldr      x1, [x25]
0x00037694: 02 00 00 14              b        #0x3769c                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x6b0
0x00037698: e1 03 18 aa              mov      x1, x24
0x0003769c: 60 ba 40 f9              ldr      x0, [x19, #0x170]
0x000376a0: 0a f7 ff 97              bl       #0x352c8
0x000376a4: 68 be 40 f9              ldr      x8, [x19, #0x178]
0x000376a8: 08 05 00 91              add      x8, x8, #1
0x000376ac: 68 be 00 f9              str      x8, [x19, #0x178]
0x000376b0: 89 a3 43 a9              ldp      x9, x8, [x28, #0x38]
0x000376b4: c8 00 00 b4              cbz      x8, #0x376cc
0x000376b8: 0a 21 00 91              add      x10, x8, #8
0x000376bc: 4b 7d 5f c8              ldxr     x11, [x10]
0x000376c0: 6b 05 00 91              add      x11, x11, #1
0x000376c4: 4b 7d 0c c8              stxr     w12, x11, [x10]
0x000376c8: ac ff ff 35              cbnz     w12, #0x376bc
0x000376cc: 15 23 40 f9              ldr      x21, [x24, #0x40]
0x000376d0: 09 a3 03 a9              stp      x9, x8, [x24, #0x38]
0x000376d4: fc 13 40 f9              ldr      x28, [sp, #0x20]
0x000376d8: b5 01 00 b4              cbz      x21, #0x3770c
0x000376dc: a8 22 00 91              add      x8, x21, #8
0x000376e0: 09 fd 5f c8              ldaxr    x9, [x8]
0x000376e4: 2a 05 00 d1              sub      x10, x9, #1
0x000376e8: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x000376ec: ab ff ff 35              cbnz     w11, #0x376e0
0x000376f0: e9 00 00 b5              cbnz     x9, #0x3770c
0x000376f4: a8 02 40 f9              ldr      x8, [x21]
0x000376f8: e0 03 15 aa              mov      x0, x21
0x000376fc: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00037700: 00 01 3f d6              blr      x8
0x00037704: e0 03 15 aa              mov      x0, x21
0x00037708: 12 e5 ff 97              bl       #0x30b50                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x0003770c: e1 23 01 91              add      x1, sp, #0x48
0x00037710: e2 83 01 91              add      x2, sp, #0x60
0x00037714: e0 03 1c aa              mov      x0, x28
0x00037718: 52 e5 ff 97              bl       #0x30c60                   ; -> _ZNSt6__ndk16__treeINS_12__value_typeINS_12basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEEENS_10shared_ptrIhEEEENS_19__map_value_compareIS7_SA_NS_4lessIS7_EELb1EEENS5_ISA_EEE12__find_equalIS7_EERPNS_16__tree_node_baseIPvEERPNS_15__tree_end_nodeISL_EERKT_@plt
0x0003771c: 18 00 40 f9              ldr      x24, [x0]
0x00037720: d8 07 00 b5              cbnz     x24, #0x37818
0x00037724: f9 03 00 aa              mov      x25, x0
0x00037728: 00 09 80 52              mov      w0, #0x48
0x0003772c: 49 e6 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00037730: e9 83 41 39              ldrb     w9, [sp, #0x60]
0x00037734: e8 03 00 aa              mov      x8, x0
0x00037738: f8 03 00 aa              mov      x24, x0
0x0003773c: 1f 0d 02 f8              str      xzr, [x8, #0x20]!
0x00037740: 1f fc 02 a9              stp      xzr, xzr, [x0, #0x28]
0x00037744: c9 00 00 37              tbnz     w9, #0, #0x3775c
0x00037748: e9 3b 40 f9              ldr      x9, [sp, #0x70]
0x0003774c: e0 1b c0 3d              ldr      q0, [sp, #0x60]
0x00037750: 09 09 00 f9              str      x9, [x8, #0x10]
0x00037754: 00 01 80 3d              str      q0, [x8]
0x00037758: 1f 00 00 14              b        #0x377d4                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x7e8
0x0003775c: f7 13 00 b9              str      w23, [sp, #0x10]
0x00037760: f7 03 16 aa              mov      x23, x22
0x00037764: f6 37 40 f9              ldr      x22, [sp, #0x68]
0x00037768: df 42 00 b1              cmn      x22, #0x10
0x0003776c: 02 22 00 54              b.hs     #0x37bac
0x00037770: e1 3b 40 f9              ldr      x1, [sp, #0x70]
0x00037774: df 5e 00 f1              cmp      x22, #0x17
0x00037778: c2 00 00 54              b.hs     #0x37790
0x0003777c: c8 7a 1f 53              lsl      w8, w22, #1
0x00037780: 15 87 00 91              add      x21, x24, #0x21
0x00037784: 08 83 00 39              strb     w8, [x24, #0x20]
0x00037788: b6 01 00 b5              cbnz     x22, #0x377bc
0x0003778c: 0f 00 00 14              b        #0x377c8                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x7dc
0x00037790: c8 42 00 91              add      x8, x22, #0x10
0x00037794: 1c ed 7c 92              and      x28, x8, #0xfffffffffffffff0
0x00037798: e0 03 1c aa              mov      x0, x28
0x0003779c: f5 03 01 aa              mov      x21, x1
0x000377a0: 2c e6 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x000377a4: 88 03 40 b2              orr      x8, x28, #1
0x000377a8: fc 13 40 f9              ldr      x28, [sp, #0x20]
0x000377ac: e1 03 15 aa              mov      x1, x21
0x000377b0: f5 03 00 aa              mov      x21, x0
0x000377b4: 16 83 02 a9              stp      x22, x0, [x24, #0x28]
0x000377b8: 08 13 00 f9              str      x8, [x24, #0x20]
0x000377bc: e0 03 15 aa              mov      x0, x21
0x000377c0: e2 03 16 aa              mov      x2, x22
0x000377c4: b7 df ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x000377c8: bf 6a 36 38              strb     wzr, [x21, x22]
0x000377cc: f6 03 17 aa              mov      x22, x23
0x000377d0: f7 13 40 b9              ldr      w23, [sp, #0x10]
0x000377d4: e8 27 40 f9              ldr      x8, [sp, #0x48]
0x000377d8: 1f ff 03 a9              stp      xzr, xzr, [x24, #0x38]
0x000377dc: 1f 7f 00 a9              stp      xzr, xzr, [x24]
0x000377e0: 08 0b 00 f9              str      x8, [x24, #0x10]
0x000377e4: 38 03 00 f9              str      x24, [x25]
0x000377e8: 88 03 40 f9              ldr      x8, [x28]
0x000377ec: 08 01 40 f9              ldr      x8, [x8]
0x000377f0: 88 00 00 b4              cbz      x8, #0x37800
0x000377f4: 88 03 00 f9              str      x8, [x28]
0x000377f8: 21 03 40 f9              ldr      x1, [x25]
0x000377fc: 02 00 00 14              b        #0x37804                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x818
0x00037800: e1 03 18 aa              mov      x1, x24
0x00037804: 60 ae 40 f9              ldr      x0, [x19, #0x158]
0x00037808: b0 f6 ff 97              bl       #0x352c8
0x0003780c: 68 b2 40 f9              ldr      x8, [x19, #0x160]
0x00037810: 08 05 00 91              add      x8, x8, #1
0x00037814: 68 b2 00 f9              str      x8, [x19, #0x160]
0x00037818: 68 7f 5f c8              ldxr     x8, [x27]
0x0003781c: 08 05 00 91              add      x8, x8, #1
0x00037820: 68 7f 09 c8              stxr     w9, x8, [x27]
0x00037824: a9 ff ff 35              cbnz     w9, #0x37818
0x00037828: e8 17 40 f9              ldr      x8, [sp, #0x28]
0x0003782c: 15 23 40 f9              ldr      x21, [x24, #0x40]
0x00037830: 08 db 03 a9              stp      x8, x22, [x24, #0x38]
0x00037834: f9 0f 40 f9              ldr      x25, [sp, #0x18]
0x00037838: f5 00 00 b4              cbz      x21, #0x37854
0x0003783c: a8 22 00 91              add      x8, x21, #8
0x00037840: 09 fd 5f c8              ldaxr    x9, [x8]
0x00037844: 2a 05 00 d1              sub      x10, x9, #1
0x00037848: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x0003784c: ab ff ff 35              cbnz     w11, #0x37840
0x00037850: c9 02 00 b4              cbz      x9, #0x378a8
0x00037854: d7 12 00 34              cbz      w23, #0x37aac
0x00037858: e1 23 01 91              add      x1, sp, #0x48
0x0003785c: e2 83 01 91              add      x2, sp, #0x60
0x00037860: e0 03 1c aa              mov      x0, x28
0x00037864: ff e4 ff 97              bl       #0x30c60                   ; -> _ZNSt6__ndk16__treeINS_12__value_typeINS_12basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEEENS_10shared_ptrIhEEEENS_19__map_value_compareIS7_SA_NS_4lessIS7_EELb1EEENS5_ISA_EEE12__find_equalIS7_EERPNS_16__tree_node_baseIPvEERPNS_15__tree_end_nodeISL_EERKT_@plt
0x00037868: 18 00 40 f9              ldr      x24, [x0]
0x0003786c: 98 08 00 b5              cbnz     x24, #0x3797c
0x00037870: f9 03 00 aa              mov      x25, x0
0x00037874: 00 09 80 52              mov      w0, #0x48
0x00037878: f6 e5 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x0003787c: e9 83 41 39              ldrb     w9, [sp, #0x60]
0x00037880: e8 03 00 aa              mov      x8, x0
0x00037884: f8 03 00 aa              mov      x24, x0
0x00037888: 1f 0d 02 f8              str      xzr, [x8, #0x20]!
0x0003788c: 1f fc 02 a9              stp      xzr, xzr, [x0, #0x28]
0x00037890: c9 01 00 37              tbnz     w9, #0, #0x378c8
0x00037894: e9 3b 40 f9              ldr      x9, [sp, #0x70]
0x00037898: e0 1b c0 3d              ldr      q0, [sp, #0x60]
0x0003789c: 09 09 00 f9              str      x9, [x8, #0x10]
0x000378a0: 00 01 80 3d              str      q0, [x8]
0x000378a4: 25 00 00 14              b        #0x37938                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x94c
0x000378a8: a8 02 40 f9              ldr      x8, [x21]
0x000378ac: e0 03 15 aa              mov      x0, x21
0x000378b0: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x000378b4: 00 01 3f d6              blr      x8
0x000378b8: e0 03 15 aa              mov      x0, x21
0x000378bc: a5 e4 ff 97              bl       #0x30b50                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x000378c0: d7 fc ff 35              cbnz     w23, #0x37858
0x000378c4: 7a 00 00 14              b        #0x37aac                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0xac0
0x000378c8: f7 03 16 aa              mov      x23, x22
0x000378cc: f6 37 40 f9              ldr      x22, [sp, #0x68]
0x000378d0: df 42 00 b1              cmn      x22, #0x10
0x000378d4: c2 16 00 54              b.hs     #0x37bac
0x000378d8: e1 3b 40 f9              ldr      x1, [sp, #0x70]
0x000378dc: df 5e 00 f1              cmp      x22, #0x17
0x000378e0: c2 00 00 54              b.hs     #0x378f8
0x000378e4: c8 7a 1f 53              lsl      w8, w22, #1
0x000378e8: 15 87 00 91              add      x21, x24, #0x21
0x000378ec: 08 83 00 39              strb     w8, [x24, #0x20]
0x000378f0: b6 01 00 b5              cbnz     x22, #0x37924
0x000378f4: 0f 00 00 14              b        #0x37930                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x944
0x000378f8: c8 42 00 91              add      x8, x22, #0x10
0x000378fc: 1c ed 7c 92              and      x28, x8, #0xfffffffffffffff0
0x00037900: e0 03 1c aa              mov      x0, x28
0x00037904: f5 03 01 aa              mov      x21, x1
0x00037908: d2 e5 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x0003790c: 88 03 40 b2              orr      x8, x28, #1
0x00037910: fc 13 40 f9              ldr      x28, [sp, #0x20]
0x00037914: e1 03 15 aa              mov      x1, x21
0x00037918: f5 03 00 aa              mov      x21, x0
0x0003791c: 16 83 02 a9              stp      x22, x0, [x24, #0x28]
0x00037920: 08 13 00 f9              str      x8, [x24, #0x20]
0x00037924: e0 03 15 aa              mov      x0, x21
0x00037928: e2 03 16 aa              mov      x2, x22
0x0003792c: 5d df ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00037930: bf 6a 36 38              strb     wzr, [x21, x22]
0x00037934: f6 03 17 aa              mov      x22, x23
0x00037938: e8 27 40 f9              ldr      x8, [sp, #0x48]
0x0003793c: 1f ff 03 a9              stp      xzr, xzr, [x24, #0x38]
0x00037940: 1f 7f 00 a9              stp      xzr, xzr, [x24]
0x00037944: 08 0b 00 f9              str      x8, [x24, #0x10]
0x00037948: 38 03 00 f9              str      x24, [x25]
0x0003794c: 88 03 40 f9              ldr      x8, [x28]
0x00037950: 08 01 40 f9              ldr      x8, [x8]
0x00037954: 88 00 00 b4              cbz      x8, #0x37964
0x00037958: 88 03 00 f9              str      x8, [x28]
0x0003795c: 21 03 40 f9              ldr      x1, [x25]
0x00037960: 02 00 00 14              b        #0x37968                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x97c
0x00037964: e1 03 18 aa              mov      x1, x24
0x00037968: 60 ae 40 f9              ldr      x0, [x19, #0x158]
0x0003796c: 57 f6 ff 97              bl       #0x352c8
0x00037970: 68 b2 40 f9              ldr      x8, [x19, #0x160]
0x00037974: 08 05 00 91              add      x8, x8, #1
0x00037978: 68 b2 00 f9              str      x8, [x19, #0x160]
0x0003797c: e0 1f 40 f9              ldr      x0, [sp, #0x38]
0x00037980: 17 1f 40 f9              ldr      x23, [x24, #0x38]
0x00037984: 7c c2 40 f9              ldr      x28, [x19, #0x180]
0x00037988: e1 23 01 91              add      x1, sp, #0x48
0x0003798c: e2 83 01 91              add      x2, sp, #0x60
0x00037990: b4 e4 ff 97              bl       #0x30c60                   ; -> _ZNSt6__ndk16__treeINS_12__value_typeINS_12basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEEENS_10shared_ptrIhEEEENS_19__map_value_compareIS7_SA_NS_4lessIS7_EELb1EEENS5_ISA_EEE12__find_equalIS7_EERPNS_16__tree_node_baseIPvEERPNS_15__tree_end_nodeISL_EERKT_@plt
0x00037994: 18 00 40 f9              ldr      x24, [x0]
0x00037998: b8 07 00 b5              cbnz     x24, #0x37a8c
0x0003799c: f9 03 00 aa              mov      x25, x0
0x000379a0: 00 09 80 52              mov      w0, #0x48
0x000379a4: ab e5 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x000379a8: e9 83 41 39              ldrb     w9, [sp, #0x60]
0x000379ac: e8 03 00 aa              mov      x8, x0
0x000379b0: f8 03 00 aa              mov      x24, x0
0x000379b4: 1f 0d 02 f8              str      xzr, [x8, #0x20]!
0x000379b8: 1f fc 02 a9              stp      xzr, xzr, [x0, #0x28]
0x000379bc: c9 00 00 37              tbnz     w9, #0, #0x379d4
0x000379c0: e9 3b 40 f9              ldr      x9, [sp, #0x70]
0x000379c4: e0 1b c0 3d              ldr      q0, [sp, #0x60]
0x000379c8: 09 09 00 f9              str      x9, [x8, #0x10]
0x000379cc: 00 01 80 3d              str      q0, [x8]
0x000379d0: 1d 00 00 14              b        #0x37a44                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0xa58
0x000379d4: f6 17 00 f9              str      x22, [sp, #0x28]
0x000379d8: f6 37 40 f9              ldr      x22, [sp, #0x68]
0x000379dc: df 42 00 b1              cmn      x22, #0x10
0x000379e0: 62 0e 00 54              b.hs     #0x37bac
0x000379e4: e1 3b 40 f9              ldr      x1, [sp, #0x70]
0x000379e8: df 5e 00 f1              cmp      x22, #0x17
0x000379ec: c2 00 00 54              b.hs     #0x37a04
0x000379f0: c8 7a 1f 53              lsl      w8, w22, #1
0x000379f4: 15 87 00 91              add      x21, x24, #0x21
0x000379f8: 08 83 00 39              strb     w8, [x24, #0x20]
0x000379fc: b6 01 00 b5              cbnz     x22, #0x37a30
0x00037a00: 0f 00 00 14              b        #0x37a3c                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0xa50
0x00037a04: c8 42 00 91              add      x8, x22, #0x10
0x00037a08: 00 ed 7c 92              and      x0, x8, #0xfffffffffffffff0
0x00037a0c: e0 0b 00 f9              str      x0, [sp, #0x10]
0x00037a10: f5 03 01 aa              mov      x21, x1
0x00037a14: 8f e5 ff 97              bl       #0x31050                   ; -> _Znwm@plt
0x00037a18: e8 0b 40 f9              ldr      x8, [sp, #0x10]
0x00037a1c: e1 03 15 aa              mov      x1, x21
0x00037a20: f5 03 00 aa              mov      x21, x0
0x00037a24: 16 83 02 a9              stp      x22, x0, [x24, #0x28]
0x00037a28: 08 01 40 b2              orr      x8, x8, #1
0x00037a2c: 08 13 00 f9              str      x8, [x24, #0x20]
0x00037a30: e0 03 15 aa              mov      x0, x21
0x00037a34: e2 03 16 aa              mov      x2, x22
0x00037a38: 1a df ff 97              bl       #0x2f6a0                   ; -> memcpy@plt
0x00037a3c: bf 6a 36 38              strb     wzr, [x21, x22]
0x00037a40: f6 17 40 f9              ldr      x22, [sp, #0x28]
0x00037a44: e8 27 40 f9              ldr      x8, [sp, #0x48]
0x00037a48: e9 1f 40 f9              ldr      x9, [sp, #0x38]
0x00037a4c: 1f ff 03 a9              stp      xzr, xzr, [x24, #0x38]
0x00037a50: 1f 7f 00 a9              stp      xzr, xzr, [x24]
0x00037a54: 08 0b 00 f9              str      x8, [x24, #0x10]
0x00037a58: 38 03 00 f9              str      x24, [x25]
0x00037a5c: 28 01 40 f9              ldr      x8, [x9]                   ; -> _ZNSt6__ndk116__copy_unalignedINS_6vectorIbNS_9allocatorIbEEEELb0EEENS_14__bit_iteratorIT_Lb0EXLi0EEEENS5_IS6_XT0_EXLi0EEEES8_S7_@got
0x00037a60: 08 01 40 f9              ldr      x8, [x8]
0x00037a64: 88 00 00 b4              cbz      x8, #0x37a74
0x00037a68: 28 01 00 f9              str      x8, [x9]
0x00037a6c: 21 03 40 f9              ldr      x1, [x25]
0x00037a70: 02 00 00 14              b        #0x37a78                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0xa8c
0x00037a74: e1 03 18 aa              mov      x1, x24
0x00037a78: 60 ba 40 f9              ldr      x0, [x19, #0x170]
0x00037a7c: 13 f6 ff 97              bl       #0x352c8
0x00037a80: 68 be 40 f9              ldr      x8, [x19, #0x178]
0x00037a84: 08 05 00 91              add      x8, x8, #1
0x00037a88: 68 be 00 f9              str      x8, [x19, #0x178]
0x00037a8c: 68 c2 40 f9              ldr      x8, [x19, #0x180]
0x00037a90: 09 1f 40 f9              ldr      x9, [x24, #0x38]
0x00037a94: 0a fd 41 d3              lsr      x10, x8, #1
0x00037a98: 29 69 6a b8              ldr      w9, [x9, x10]              ; -> _ZNSt6__ndk116__copy_unalignedINS_6vectorIbNS_9allocatorIbEEEELb0EEENS_14__bit_iteratorIT_Lb0EXLi0EEEENS5_IS6_XT0_EXLi0EEEES8_S7_@got
0x00037a9c: 08 11 00 91              add      x8, x8, #4
0x00037aa0: e9 6a 3c b8              str      w9, [x23, x28]
0x00037aa4: 68 c2 00 f9              str      x8, [x19, #0x180]
0x00037aa8: f9 f3 41 a9              ldp      x25, x28, [sp, #0x18]
0x00037aac: 61 0a 00 90              adrp     x1, #0x183000
0x00037ab0: 62 0a 00 90              adrp     x2, #0x183000
0x00037ab4: e0 03 1f 2a              mov      w0, wzr
0x00037ab8: 21 e0 21 91              add      x1, x1, #0x878
0x00037abc: 42 04 39 91              add      x2, x2, #0xe41
0x00037ac0: c8 e5 ff 97              bl       #0x311e0                   ; -> _ZN6BYTENN9ByteNNLogEiPKcS1_z@plt
0x00037ac4: f5 03 1f 2a              mov      w21, wzr
0x00037ac8: 68 ff 5f c8              ldaxr    x8, [x27]
0x00037acc: 09 05 00 d1              sub      x9, x8, #1
0x00037ad0: 69 ff 0a c8              stlxr    w10, x9, [x27]
0x00037ad4: aa ff ff 35              cbnz     w10, #0x37ac8
0x00037ad8: e8 00 00 b5              cbnz     x8, #0x37af4
0x00037adc: c8 02 40 f9              ldr      x8, [x22]
0x00037ae0: e0 03 16 aa              mov      x0, x22
0x00037ae4: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00037ae8: 00 01 3f d6              blr      x8
0x00037aec: e0 03 16 aa              mov      x0, x22
0x00037af0: 18 e4 ff 97              bl       #0x30b50                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00037af4: 3b 0d 00 f0              adrp     x27, #0x1de000
0x00037af8: 7b bb 45 f9              ldr      x27, [x27, #0xb70]         ; -> _ZSt7nothrow@got
0x00037afc: b5 00 00 34              cbz      w21, #0x37b10
0x00037b00: 35 00 80 52              mov      w21, #1
0x00037b04: e8 83 41 39              ldrb     w8, [sp, #0x60]
0x00037b08: a8 00 00 37              tbnz     w8, #0, #0x37b1c
0x00037b0c: 06 00 00 14              b        #0x37b24                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0xb38
0x00037b10: f5 03 1f 2a              mov      w21, wzr
0x00037b14: e8 83 41 39              ldrb     w8, [sp, #0x60]
0x00037b18: 68 00 00 36              tbz      w8, #0, #0x37b24
0x00037b1c: e0 3b 40 f9              ldr      x0, [sp, #0x70]
0x00037b20: 60 df ff 97              bl       #0x2f8a0                   ; -> _ZdlPv@plt
0x00037b24: b5 02 00 35              cbnz     w21, #0x37b78
0x00037b28: 48 07 40 f9              ldr      x8, [x26, #8]
0x00037b2c: a8 00 00 b4              cbz      x8, #0x37b40
0x00037b30: fa 03 08 aa              mov      x26, x8
0x00037b34: 08 01 40 f9              ldr      x8, [x8]
0x00037b38: c8 ff ff b5              cbnz     x8, #0x37b30
0x00037b3c: 4a fd ff 17              b        #0x37064                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x78
0x00037b40: e8 03 1a aa              mov      x8, x26
0x00037b44: 09 0d 41 f8              ldr      x9, [x8, #0x10]!
0x00037b48: 2a 01 40 f9              ldr      x10, [x9]                  ; -> _ZNSt6__ndk116__copy_unalignedINS_6vectorIbNS_9allocatorIbEEEELb0EEENS_14__bit_iteratorIT_Lb0EXLi0EEEENS5_IS6_XT0_EXLi0EEEES8_S7_@got
0x00037b4c: 5f 01 1a eb              cmp      x10, x26
0x00037b50: 80 a8 ff 54              b.eq     #0x37060
0x00037b54: 09 01 40 f9              ldr      x9, [x8]
0x00037b58: e8 03 09 aa              mov      x8, x9
0x00037b5c: 1a 0d 41 f8              ldr      x26, [x8, #0x10]!
0x00037b60: 4a 03 40 f9              ldr      x10, [x26]
0x00037b64: 5f 01 09 eb              cmp      x10, x9
0x00037b68: 61 ff ff 54              b.ne     #0x37b54
0x00037b6c: 3e fd ff 17              b        #0x37064                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0x78
0x00037b70: e0 03 1f 2a              mov      w0, wzr
0x00037b74: 02 00 00 14              b        #0x37b7c                   ; -> _ZN6BYTENN23ByteNNInternalConfigExt24CheckFp16AndConvertModelEv+0xb90
0x00037b78: a0 00 80 52              mov      w0, #5
0x00037b7c: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x00037b80: a9 83 5a f8              ldur     x9, [x29, #-0x58]
0x00037b84: 1f 01 09 eb              cmp      x8, x9
0x00037b88: 41 01 00 54              b.ne     #0x37bb0
0x00037b8c: fd 7b 4d a9              ldp      x29, x30, [sp, #0xd0]
0x00037b90: f4 4f 4c a9              ldp      x20, x19, [sp, #0xc0]
0x00037b94: f6 57 4b a9              ldp      x22, x21, [sp, #0xb0]
0x00037b98: f8 5f 4a a9              ldp      x24, x23, [sp, #0xa0]
0x00037b9c: fa 67 49 a9              ldp      x26, x25, [sp, #0x90]
0x00037ba0: fc 6f 48 a9              ldp      x28, x27, [sp, #0x80]
0x00037ba4: ff 83 03 91              add      sp, sp, #0xe0
0x00037ba8: c0 03 5f d6              ret      
0x00037bac: 21 e4 ff 97              bl       #0x30c30                   ; -> abort@plt
0x00037bb0: e4 e5 ff 97              bl       #0x31340                   ; -> __stack_chk_fail@plt
