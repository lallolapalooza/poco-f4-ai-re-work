; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_musically/native/libbytenn.so
; target  : address 0x345fc
; start   : 0x345fc
; size    : 0x2a0
; bytes   : 0x2a0   instructions: 168
; generated: 2026-10-23T23:34:43

0x000345fc: fc 0f 1b f8              str      x28, [sp, #-0x50]!
0x00034600: f8 5f 01 a9              stp      x24, x23, [sp, #0x10]
0x00034604: f6 57 02 a9              stp      x22, x21, [sp, #0x20]
0x00034608: f4 4f 03 a9              stp      x20, x19, [sp, #0x30]
0x0003460c: fd 7b 04 a9              stp      x29, x30, [sp, #0x40]
0x00034610: fd 03 01 91              add      x29, sp, #0x40
0x00034614: ff 83 07 d1              sub      sp, sp, #0x1e0
0x00034618: 55 d0 3b d5              mrs      x21, tpidr_el0
0x0003461c: a8 16 40 f9              ldr      x8, [x21, #0x28]
0x00034620: f4 03 00 aa              mov      x20, x0
0x00034624: f3 03 01 aa              mov      x19, x1
0x00034628: e0 03 00 91              mov      x0, sp
0x0003462c: e1 03 14 aa              mov      x1, x20
0x00034630: a8 83 1b f8              stur     x8, [x29, #-0x48]
0x00034634: f7 03 00 91              mov      x23, sp
0x00034638: 16 f4 ff 97              bl       #0x31690                   ; -> _ZN6BYTENN9ConfigExtC2ERKS0_@plt
0x0003463c: 56 0d 00 d0              adrp     x22, #0x1de000
0x00034640: d6 52 46 f9              ldr      x22, [x22, #0xca0]         ; -> _ZTVN6BYTENN23ByteNNInternalConfigExtE@got
0x00034644: e9 62 05 91              add      x9, x23, #0x158
0x00034648: c8 42 00 91              add      x8, x22, #0x10
0x0003464c: e8 03 00 f9              str      x8, [sp]
0x00034650: 88 aa 40 f9              ldr      x8, [x20, #0x150]
0x00034654: e8 ab 00 f9              str      x8, [sp, #0x150]
0x00034658: 88 aa 55 a9              ldp      x8, x10, [x20, #0x158]
0x0003465c: e8 ab 15 a9              stp      x8, x10, [sp, #0x158]
0x00034660: ca 00 00 b4              cbz      x10, #0x34678
0x00034664: 09 09 00 f9              str      x9, [x8, #0x10]
0x00034668: 88 62 05 91              add      x8, x20, #0x158
0x0003466c: 9f fe 15 a9              stp      xzr, xzr, [x20, #0x158]
0x00034670: 88 aa 00 f9              str      x8, [x20, #0x150]
0x00034674: 02 00 00 14              b        #0x3467c
0x00034678: e9 ab 00 f9              str      x9, [sp, #0x150]
0x0003467c: 88 b6 40 f9              ldr      x8, [x20, #0x168]
0x00034680: e9 03 00 91              mov      x9, sp
0x00034684: 29 c1 05 91              add      x9, x9, #0x170
0x00034688: e8 b7 00 f9              str      x8, [sp, #0x168]
0x0003468c: 88 2a 57 a9              ldp      x8, x10, [x20, #0x170]
0x00034690: e8 2b 17 a9              stp      x8, x10, [sp, #0x170]
0x00034694: ca 00 00 b4              cbz      x10, #0x346ac
0x00034698: 09 09 00 f9              str      x9, [x8, #0x10]
0x0003469c: 88 c2 05 91              add      x8, x20, #0x170
0x000346a0: 9f 7e 17 a9              stp      xzr, xzr, [x20, #0x170]
0x000346a4: 88 b6 00 f9              str      x8, [x20, #0x168]
0x000346a8: 02 00 00 14              b        #0x346b0
0x000346ac: e9 b7 00 f9              str      x9, [sp, #0x168]
0x000346b0: 88 ca 40 f9              ldr      x8, [x20, #0x190]
0x000346b4: 80 62 c0 3d              ldr      q0, [x20, #0x180]
0x000346b8: e9 03 00 91              mov      x9, sp
0x000346bc: 29 81 06 91              add      x9, x9, #0x1a0
0x000346c0: e8 cb 00 f9              str      x8, [sp, #0x190]
0x000346c4: e0 63 80 3d              str      q0, [sp, #0x180]
0x000346c8: 88 ce 40 f9              ldr      x8, [x20, #0x198]
0x000346cc: e8 cf 00 f9              str      x8, [sp, #0x198]
0x000346d0: 88 2a 5a a9              ldp      x8, x10, [x20, #0x1a0]
0x000346d4: e8 2b 1a a9              stp      x8, x10, [sp, #0x1a0]
0x000346d8: ca 00 00 b4              cbz      x10, #0x346f0
0x000346dc: 09 09 00 f9              str      x9, [x8, #0x10]
0x000346e0: 88 82 06 91              add      x8, x20, #0x1a0
0x000346e4: 9f 7e 1a a9              stp      xzr, xzr, [x20, #0x1a0]
0x000346e8: 88 ce 00 f9              str      x8, [x20, #0x198]
0x000346ec: 02 00 00 14              b        #0x346f4
0x000346f0: e9 cf 00 f9              str      x9, [sp, #0x198]
0x000346f4: 80 6e c0 3d              ldr      q0, [x20, #0x1b0]
0x000346f8: f7 03 00 91              mov      x23, sp
0x000346fc: e0 03 14 aa              mov      x0, x20
0x00034700: e1 03 13 aa              mov      x1, x19
0x00034704: e0 6f 80 3d              str      q0, [sp, #0x1b0]
0x00034708: 88 ea 40 f9              ldr      x8, [x20, #0x1d0]
0x0003470c: 80 72 c0 3d              ldr      q0, [x20, #0x1c0]
0x00034710: f8 c2 06 91              add      x24, x23, #0x1b0
0x00034714: 9f 7e 1b a9              stp      xzr, xzr, [x20, #0x1b0]
0x00034718: e8 eb 00 f9              str      x8, [sp, #0x1d0]
0x0003471c: e0 73 80 3d              str      q0, [sp, #0x1c0]
0x00034720: 4c f1 ff 97              bl       #0x30c50                   ; -> _ZN6BYTENN23ByteNNInternalConfigExtaSEOS0_@plt
0x00034724: e1 03 00 91              mov      x1, sp
0x00034728: e0 03 13 aa              mov      x0, x19
0x0003472c: 49 f1 ff 97              bl       #0x30c50                   ; -> _ZN6BYTENN23ByteNNInternalConfigExtaSEOS0_@plt
0x00034730: c8 42 00 91              add      x8, x22, #0x10
0x00034734: e8 03 00 f9              str      x8, [sp]
0x00034738: 13 07 40 f9              ldr      x19, [x24, #8]
0x0003473c: b3 01 00 b4              cbz      x19, #0x34770
0x00034740: 68 22 00 91              add      x8, x19, #8
0x00034744: 09 fd 5f c8              ldaxr    x9, [x8]
0x00034748: 2a 05 00 d1              sub      x10, x9, #1
0x0003474c: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00034750: ab ff ff 35              cbnz     w11, #0x34744
0x00034754: e9 00 00 b5              cbnz     x9, #0x34770
0x00034758: 68 02 40 f9              ldr      x8, [x19]
0x0003475c: e0 03 13 aa              mov      x0, x19
0x00034760: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00034764: 00 01 3f d6              blr      x8
0x00034768: e0 03 13 aa              mov      x0, x19
0x0003476c: f9 f0 ff 97              bl       #0x30b50                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00034770: e1 d3 40 f9              ldr      x1, [sp, #0x1a0]
0x00034774: e0 62 06 91              add      x0, x23, #0x198
0x00034778: 62 01 00 94              bl       #0x34d00
0x0003477c: e1 bb 40 f9              ldr      x1, [sp, #0x170]
0x00034780: e0 a2 05 91              add      x0, x23, #0x168
0x00034784: 27 ef ff 97              bl       #0x30420                   ; -> _ZNSt6__ndk16__treeINS_12__value_typeINS_12basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEEENS_10shared_ptrIhEEEENS_19__map_value_compareIS7_SA_NS_4lessIS7_EELb1EEENS5_ISA_EEE7destroyEPNS_11__tree_nodeISA_PvEE@plt
0x00034788: e1 af 40 f9              ldr      x1, [sp, #0x158]
0x0003478c: e0 42 05 91              add      x0, x23, #0x150
0x00034790: 24 ef ff 97              bl       #0x30420                   ; -> _ZNSt6__ndk16__treeINS_12__value_typeINS_12basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEEENS_10shared_ptrIhEEEENS_19__map_value_compareIS7_SA_NS_4lessIS7_EELb1EEENS5_ISA_EEE7destroyEPNS_11__tree_nodeISA_PvEE@plt
0x00034794: e0 03 00 91              mov      x0, sp
0x00034798: 66 f0 ff 97              bl       #0x30930                   ; -> _ZN6BYTENN9ConfigExtD2Ev@plt
0x0003479c: a8 16 40 f9              ldr      x8, [x21, #0x28]
0x000347a0: a9 83 5b f8              ldur     x9, [x29, #-0x48]
0x000347a4: 1f 01 09 eb              cmp      x8, x9
0x000347a8: 01 01 00 54              b.ne     #0x347c8
0x000347ac: ff 83 07 91              add      sp, sp, #0x1e0
0x000347b0: fd 7b 44 a9              ldp      x29, x30, [sp, #0x40]
0x000347b4: f4 4f 43 a9              ldp      x20, x19, [sp, #0x30]
0x000347b8: f6 57 42 a9              ldp      x22, x21, [sp, #0x20]
0x000347bc: f8 5f 41 a9              ldp      x24, x23, [sp, #0x10]
0x000347c0: fc 07 45 f8              ldr      x28, [sp], #0x50
0x000347c4: c0 03 5f d6              ret      
0x000347c8: de f2 ff 97              bl       #0x31340                   ; -> __stack_chk_fail@plt
0x000347cc: f3 0f 1e f8              str      x19, [sp, #-0x20]!
0x000347d0: fd 7b 01 a9              stp      x29, x30, [sp, #0x10]
0x000347d4: fd 43 00 91              add      x29, sp, #0x10
0x000347d8: 03 3c 43 b9              ldr      w3, [x0, #0x33c]
0x000347dc: f3 03 00 aa              mov      x19, x0
0x000347e0: 63 01 00 34              cbz      w3, #0x3480c
0x000347e4: 61 0a 00 f0              adrp     x1, #0x183000
0x000347e8: 62 0a 00 f0              adrp     x2, #0x183000
0x000347ec: 64 0a 00 f0              adrp     x4, #0x183000
0x000347f0: 21 e0 21 91              add      x1, x1, #0x878
0x000347f4: 42 fc 21 91              add      x2, x2, #0x87f
0x000347f8: 84 60 28 91              add      x4, x4, #0xa18
0x000347fc: 20 00 80 52              mov      w0, #1
0x00034800: 78 f2 ff 97              bl       #0x311e0                   ; -> _ZN6BYTENN9ByteNNLogEiPKcS1_z@plt
0x00034804: 60 3e 43 b9              ldr      w0, [x19, #0x33c]
0x00034808: 27 00 00 14              b        #0x348a4                   ; -> _ZN6BYTENN16ByteNNEngineImpl8SetInputERKNSt6__ndk16vectorINS1_10shared_ptrINS_9TensorExtEEENS1_9allocatorIS5_EEEE+0xd8
0x0003480c: 60 06 40 f9              ldr      x0, [x19, #8]
0x00034810: 80 02 00 b4              cbz      x0, #0x34860
0x00034814: 28 24 40 a9              ldp      x8, x9, [x1]
0x00034818: 1f 01 09 eb              cmp      x8, x9
0x0003481c: e0 00 00 54              b.eq     #0x34838
0x00034820: 0a 05 41 f8              ldr      x10, [x8], #0x10
0x00034824: 4a 11 40 b9              ldr      w10, [x10, #0x10]
0x00034828: 5f 05 00 71              cmp      w10, #1
0x0003482c: e1 02 00 54              b.ne     #0x34888
0x00034830: 3f 01 08 eb              cmp      x9, x8
0x00034834: 61 ff ff 54              b.ne     #0x34820
0x00034838: 08 00 40 f9              ldr      x8, [x0]
0x0003483c: 08 2d 40 f9              ldr      x8, [x8, #0x58]
0x00034840: 00 01 3f d6              blr      x8
0x00034844: 68 0e 40 f9              ldr      x8, [x19, #0x18]
0x00034848: 60 3e 03 b9              str      w0, [x19, #0x33c]
0x0003484c: 1f 01 00 f1              cmp      x8, #0
0x00034850: 48 00 80 52              mov      w8, #2
0x00034854: 08 00 88 1a              csel     w8, w0, w8, eq
0x00034858: e0 03 08 2a              mov      w0, w8
0x0003485c: 12 00 00 14              b        #0x348a4                   ; -> _ZN6BYTENN16ByteNNEngineImpl8SetInputERKNSt6__ndk16vectorINS1_10shared_ptrINS_9TensorExtEEENS1_9allocatorIS5_EEEE+0xd8
0x00034860: 61 0a 00 f0              adrp     x1, #0x183000
0x00034864: 62 0a 00 f0              adrp     x2, #0x183000
0x00034868: 63 0a 00 f0              adrp     x3, #0x183000
0x0003486c: 21 e0 21 91              add      x1, x1, #0x878
0x00034870: 42 e8 27 91              add      x2, x2, #0x9fa
0x00034874: 63 60 28 91              add      x3, x3, #0xa18
0x00034878: 20 00 80 52              mov      w0, #1
0x0003487c: 59 f2 ff 97              bl       #0x311e0                   ; -> _ZN6BYTENN9ByteNNLogEiPKcS1_z@plt
0x00034880: 00 01 80 52              mov      w0, #8
0x00034884: 08 00 00 14              b        #0x348a4                   ; -> _ZN6BYTENN16ByteNNEngineImpl8SetInputERKNSt6__ndk16vectorINS1_10shared_ptrINS_9TensorExtEEENS1_9allocatorIS5_EEEE+0xd8
0x00034888: 61 0a 00 f0              adrp     x1, #0x183000
0x0003488c: 62 0a 00 f0              adrp     x2, #0x183000
0x00034890: 21 e0 21 91              add      x1, x1, #0x878
0x00034894: 42 fc 28 91              add      x2, x2, #0xa3f
0x00034898: 20 00 80 52              mov      w0, #1
