; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : _ZN12stfaceunlock14FeatureManager6UnlockEP12st_feature_tRKSsfRKSt10shared_ptrINS_17FaceVerifyManagerEEPiiRfRi (0x1358ec)
; start   : 0x1358ec
; size    : 0x2b0
; bytes   : 0x2b0   instructions: 172
; generated: 2026-10-24T00:34:10

0x001358ec: fd 7b b2 a9              stp      x29, x30, [sp, #-0xe0]!
0x001358f0: fd 03 00 91              mov      x29, sp
0x001358f4: e8 27 06 6d              stp      d8, d9, [sp, #0x60]
0x001358f8: 09 40 20 1e              fmov     s9, s0
0x001358fc: f9 6b 04 a9              stp      x25, x26, [sp, #0x40]
0x00135900: f9 03 01 aa              mov      x25, x1
0x00135904: e1 03 03 aa              mov      x1, x3
0x00135908: f3 53 01 a9              stp      x19, x20, [sp, #0x10]
0x0013590c: f5 5b 02 a9              stp      x21, x22, [sp, #0x20]
0x00135910: f7 63 03 a9              stp      x23, x24, [sp, #0x30]
0x00135914: fb 73 05 a9              stp      x27, x28, [sp, #0x50]
0x00135918: f8 03 03 aa              mov      x24, x3
0x0013591c: f6 03 00 aa              mov      x22, x0
0x00135920: f3 03 04 aa              mov      x19, x4
0x00135924: f5 03 05 2a              mov      w21, w5
0x00135928: a6 3f 00 f9              str      x6, [x29, #0x78]
0x0013592c: a7 3b 00 f9              str      x7, [x29, #0x70]
0x00135930: ab fb ff 97              bl       #0x1347dc                  ; -> _ZN12stfaceunlock14FeatureManager11ReadFeatureERKSt10shared_ptrINS_17FaceVerifyManagerEERKSs
0x00135934: f4 03 00 2a              mov      w20, w0
0x00135938: 40 01 00 34              cbz      w0, #0x135960
0x0013593c: e0 03 14 2a              mov      w0, w20
0x00135940: e8 27 46 6d              ldp      d8, d9, [sp, #0x60]
0x00135944: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x00135948: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x0013594c: f7 63 43 a9              ldp      x23, x24, [sp, #0x30]
0x00135950: f9 6b 44 a9              ldp      x25, x26, [sp, #0x40]
0x00135954: fb 73 45 a9              ldp      x27, x28, [sp, #0x50]
0x00135958: fd 7b ce a8              ldp      x29, x30, [sp], #0xe0
0x0013595c: c0 03 5f d6              ret      
0x00135960: a4 c3 02 91              add      x4, x29, #0xb0
0x00135964: 5c 31 00 f0              adrp     x28, #0x760000
0x00135968: 40 31 00 f0              adrp     x0, #0x760000
0x0013596c: c3 16 40 f9              ldr      x3, [x22, #0x28]
0x00135970: a4 47 00 f9              str      x4, [x29, #0x88]
0x00135974: 00 a0 22 91              add      x0, x0, #0x8a8
0x00135978: a4 e3 02 91              add      x4, x29, #0xb8
0x0013597c: 81 23 1b 91              add      x1, x28, #0x6c8
0x00135980: 00 10 3e 1e              fmov     s0, #-1.00000000
0x00135984: 02 17 80 52              mov      w2, #0xb8
0x00135988: bf bb 00 b9              str      wzr, [x29, #0xb8]
0x0013598c: a0 af 00 bd              str      s0, [x29, #0xac]
0x00135990: bf 63 00 f9              str      xzr, [x29, #0xc0]
0x00135994: bf 6f 00 f9              str      xzr, [x29, #0xd8]
0x00135998: a4 67 00 f9              str      x4, [x29, #0xc8]
0x0013599c: a4 6b 00 f9              str      x4, [x29, #0xd0]
0x001359a0: 0a 24 00 94              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x001359a4: bf 02 1f 6b              cmp      w21, wzr
0x001359a8: e1 d7 9f 1a              cset     w1, gt
0x001359ac: 7f 02 1f eb              cmp      x19, xzr
0x001359b0: e0 07 9f 1a              cset     w0, ne
0x001359b4: 20 00 00 6a              ands     w0, w1, w0
0x001359b8: a0 9f 00 b9              str      w0, [x29, #0x9c]
0x001359bc: 20 01 00 54              b.eq     #0x1359e0
0x001359c0: 14 00 80 52              mov      w20, #0
0x001359c4: a0 47 40 f9              ldr      x0, [x29, #0x88]
0x001359c8: e1 03 13 aa              mov      x1, x19
0x001359cc: 4d 03 00 94              bl       #0x136700                  ; -> _ZNSt8_Rb_treeIiiSt9_IdentityIiESt4lessIiESaIiEE16_M_insert_uniqueIRKiEESt4pairISt17_Rb_tree_iteratorIiEbEOT_
0x001359d0: 94 06 00 11              add      w20, w20, #1
0x001359d4: 73 12 00 91              add      x19, x19, #4
0x001359d8: bf 02 14 6b              cmp      w21, w20
0x001359dc: 4c ff ff 54              b.gt     #0x1359c4
0x001359e0: d4 0e 40 f9              ldr      x20, [x22, #0x18]
0x001359e4: c0 22 00 91              add      x0, x22, #8
0x001359e8: a0 4b 00 f9              str      x0, [x29, #0x90]
0x001359ec: 9f 02 00 eb              cmp      x20, x0
0x001359f0: e8 03 27 1e              fmov     s8, wzr
0x001359f4: 00 06 00 54              b.eq     #0x135ab4
0x001359f8: 57 31 00 f0              adrp     x23, #0x760000
0x001359fc: 40 31 00 f0              adrp     x0, #0x760000
0x00135a00: 00 a0 23 91              add      x0, x0, #0x8e8
0x00135a04: ba b3 02 91              add      x26, x29, #0xac
0x00135a08: f7 62 24 91              add      x23, x23, #0x918
0x00135a0c: a0 43 00 f9              str      x0, [x29, #0x80]
0x00135a10: a0 9f 40 b9              ldr      w0, [x29, #0x9c]
0x00135a14: 00 07 00 35              cbnz     w0, #0x135af4
0x00135a18: 80 16 40 f9              ldr      x0, [x20, #0x28]
0x00135a1c: 13 00 80 d2              mov      x19, #0
0x00135a20: 81 1a 40 f9              ldr      x1, [x20, #0x30]
0x00135a24: 96 23 1b 91              add      x22, x28, #0x6c8
0x00135a28: 21 00 00 cb              sub      x1, x1, x0
0x00135a2c: 21 fc 44 93              asr      x1, x1, #4
0x00135a30: 61 03 00 b4              cbz      x1, #0x135a9c
0x00135a34: 01 10 13 8b              add      x1, x0, x19, lsl #4
0x00135a38: 00 03 40 f9              ldr      x0, [x24]
0x00135a3c: e2 03 19 aa              mov      x2, x25
0x00135a40: e3 03 1a aa              mov      x3, x26
0x00135a44: 21 04 40 f9              ldr      x1, [x1, #8]
0x00135a48: 87 c1 ff 97              bl       #0x126064                  ; -> _ZN12stfaceunlock17FaceVerifyManager14CompareFeatureEPK12st_feature_tS3_Pf
0x00135a4c: a0 af 40 bd              ldr      s0, [x29, #0xac]
0x00135a50: 83 22 40 b9              ldr      w3, [x20, #0x20]
0x00135a54: e0 03 17 aa              mov      x0, x23
0x00135a58: e1 03 16 aa              mov      x1, x22
0x00135a5c: 00 c0 22 1e              fcvt     d0, s0
0x00135a60: e2 18 80 52              mov      w2, #0xc7
0x00135a64: e4 03 13 2a              mov      w4, w19
0x00135a68: 95 82 00 91              add      x21, x20, #0x20
0x00135a6c: d7 23 00 94              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x00135a70: a0 af 40 bd              ldr      s0, [x29, #0xac]
0x00135a74: 10 20 28 1e              fcmpe    s0, s8
0x00135a78: 6d 00 00 54              b.le     #0x135a84
0x00135a7c: 08 40 20 1e              fmov     s8, s0
0x00135a80: bb 02 40 b9              ldr      w27, [x21]
0x00135a84: 80 16 40 f9              ldr      x0, [x20, #0x28]
0x00135a88: 73 06 00 91              add      x19, x19, #1
0x00135a8c: 81 1a 40 f9              ldr      x1, [x20, #0x30]
0x00135a90: 21 00 00 cb              sub      x1, x1, x0
0x00135a94: 7f 12 81 eb              cmp      x19, x1, asr #4
0x00135a98: e3 fc ff 54              b.lo     #0x135a34
0x00135a9c: e0 03 14 aa              mov      x0, x20
0x00135aa0: a8 8e 17 94              bl       #0x719540                  ; -> _ZSt18_Rb_tree_incrementPSt18_Rb_tree_node_base
0x00135aa4: f4 03 00 aa              mov      x20, x0
0x00135aa8: a0 4b 40 f9              ldr      x0, [x29, #0x90]
0x00135aac: 1f 00 14 eb              cmp      x0, x20
0x00135ab0: 01 fb ff 54              b.ne     #0x135a10
0x00135ab4: a0 3f 40 f9              ldr      x0, [x29, #0x78]
0x00135ab8: 54 19 80 52              mov      w20, #0xca
0x00135abc: 30 21 28 1e              fcmpe    s9, s8
0x00135ac0: 08 00 00 bd              str      s8, [x0]
0x00135ac4: c9 03 00 54              b.ls     #0x135b3c
0x00135ac8: b3 63 40 f9              ldr      x19, [x29, #0xc0]
0x00135acc: 93 f3 ff b4              cbz      x19, #0x13593c
0x00135ad0: 61 0e 40 f9              ldr      x1, [x19, #0x18]
0x00135ad4: a0 47 40 f9              ldr      x0, [x29, #0x88]
0x00135ad8: b1 02 00 94              bl       #0x13659c                  ; -> _ZNSt8_Rb_treeIiiSt9_IdentityIiESt4lessIiESaIiEE8_M_eraseEPSt13_Rb_tree_nodeIiE
0x00135adc: 61 0a 40 f9              ldr      x1, [x19, #0x10]
0x00135ae0: e0 03 13 aa              mov      x0, x19
0x00135ae4: f3 03 01 aa              mov      x19, x1
0x00135ae8: c6 cd 16 94              bl       #0x6e9200                  ; -> _ZdlPv
0x00135aec: 33 ff ff b5              cbnz     x19, #0x135ad0
0x00135af0: 93 ff ff 17              b        #0x13593c                  ; -> _ZN12stfaceunlock14FeatureManager6UnlockEP12st_feature_tRKSsfRKSt10shared_ptrINS_17FaceVerifyManagerEEPiiRfRi+0x50
0x00135af4: 95 82 00 91              add      x21, x20, #0x20
0x00135af8: a0 47 40 f9              ldr      x0, [x29, #0x88]
0x00135afc: e1 03 15 aa              mov      x1, x21
0x00135b00: 00 03 00 94              bl       #0x136700                  ; -> _ZNSt8_Rb_treeIiiSt9_IdentityIiESt4lessIiESaIiEE16_M_insert_uniqueIRKiEESt4pairISt17_Rb_tree_iteratorIiEbEOT_
0x00135b04: 21 1c 00 53              uxtb     w1, w1
0x00135b08: 81 f8 ff 34              cbz      w1, #0x135a18
0x00135b0c: a3 02 40 b9              ldr      w3, [x21]
0x00135b10: 81 23 1b 91              add      x1, x28, #0x6c8
0x00135b14: a0 43 40 f9              ldr      x0, [x29, #0x80]
0x00135b18: 02 18 80 52              mov      w2, #0xc0
0x00135b1c: ee 23 00 94              bl       #0x13ead4                  ; -> _Z4LogIPKcz
0x00135b20: e0 03 14 aa              mov      x0, x20
0x00135b24: 87 8e 17 94              bl       #0x719540                  ; -> _ZSt18_Rb_tree_incrementPSt18_Rb_tree_node_base
0x00135b28: f4 03 00 aa              mov      x20, x0
0x00135b2c: a0 4b 40 f9              ldr      x0, [x29, #0x90]
0x00135b30: 1f 00 14 eb              cmp      x0, x20
0x00135b34: e1 f6 ff 54              b.ne     #0x135a10
0x00135b38: df ff ff 17              b        #0x135ab4                  ; -> _ZN12stfaceunlock14FeatureManager6UnlockEP12st_feature_tRKSsfRKSt10shared_ptrINS_17FaceVerifyManagerEEPiiRfRi+0x1c8
0x00135b3c: 00 c1 22 1e              fcvt     d0, s8
0x00135b40: 40 31 00 f0              adrp     x0, #0x760000
0x00135b44: 00 e0 25 91              add      x0, x0, #0x978
0x00135b48: 81 23 1b 91              add      x1, x28, #0x6c8
0x00135b4c: 22 1a 80 52              mov      w2, #0xd1
0x00135b50: e3 03 1b 2a              mov      w3, w27
0x00135b54: 9d 23 00 94              bl       #0x13e9c8                  ; -> _Z4LogDPKcz
0x00135b58: a0 3b 40 f9              ldr      x0, [x29, #0x70]
0x00135b5c: 34 19 80 52              mov      w20, #0xc9
0x00135b60: 1b 00 00 b9              str      w27, [x0]
0x00135b64: d9 ff ff 17              b        #0x135ac8                  ; -> _ZN12stfaceunlock14FeatureManager6UnlockEP12st_feature_tRKSsfRKSt10shared_ptrINS_17FaceVerifyManagerEEPiiRfRi+0x1dc
0x00135b68: b3 63 40 f9              ldr      x19, [x29, #0xc0]
0x00135b6c: f4 03 00 aa              mov      x20, x0
0x00135b70: 33 01 00 b4              cbz      x19, #0x135b94
0x00135b74: 61 0e 40 f9              ldr      x1, [x19, #0x18]
0x00135b78: a0 47 40 f9              ldr      x0, [x29, #0x88]
0x00135b7c: 88 02 00 94              bl       #0x13659c                  ; -> _ZNSt8_Rb_treeIiiSt9_IdentityIiESt4lessIiESaIiEE8_M_eraseEPSt13_Rb_tree_nodeIiE
0x00135b80: 61 0a 40 f9              ldr      x1, [x19, #0x10]
0x00135b84: e0 03 13 aa              mov      x0, x19
0x00135b88: f3 03 01 aa              mov      x19, x1
0x00135b8c: 9d cd 16 94              bl       #0x6e9200                  ; -> _ZdlPv
0x00135b90: 33 ff ff b5              cbnz     x19, #0x135b74
0x00135b94: e0 03 14 aa              mov      x0, x20
0x00135b98: c7 69 18 94              bl       #0x7502b4                  ; -> _Unwind_Resume
