; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : address 0x2b78f4
; start   : 0x2b78f4
; size    : 0xacc
; bytes   : 0xacc   instructions: 691
; generated: 2026-10-23T23:35:51

0x002b78f4: fd 7b b5 a9              stp      x29, x30, [sp, #-0xb0]!
0x002b78f8: fd 03 00 91              mov      x29, sp
0x002b78fc: f3 53 01 a9              stp      x19, x20, [sp, #0x10]
0x002b7900: f3 03 01 aa              mov      x19, x1
0x002b7904: 21 00 40 f9              ldr      x1, [x1]
0x002b7908: f7 63 03 a9              stp      x23, x24, [sp, #0x30]
0x002b790c: f7 03 00 aa              mov      x23, x0
0x002b7910: e0 03 01 aa              mov      x0, x1
0x002b7914: 21 00 40 f9              ldr      x1, [x1]
0x002b7918: f5 5b 02 a9              stp      x21, x22, [sp, #0x20]
0x002b791c: f9 6b 04 a9              stp      x25, x26, [sp, #0x40]
0x002b7920: 21 1c 40 f9              ldr      x1, [x1, #0x38]
0x002b7924: 20 00 3f d6              blr      x1
0x002b7928: 1f 08 00 71              cmp      w0, #2
0x002b792c: 40 0a 00 54              b.eq     #0x2b7a74
0x002b7930: 75 06 40 f9              ldr      x21, [x19, #8]
0x002b7934: 79 02 40 f9              ldr      x25, [x19]
0x002b7938: 35 01 00 b4              cbz      x21, #0x2b795c
0x002b793c: 80 63 02 d0              adrp     x0, #0x4f29000
0x002b7940: 00 94 47 f9              ldr      x0, [x0, #0xf28]           ; -> pthread_create@got
0x002b7944: a0 2d 00 b4              cbz      x0, #0x2b7ef8
0x002b7948: a0 22 00 91              add      x0, x21, #8
0x002b794c: 01 fc 5f 88              ldaxr    w1, [x0]
0x002b7950: 21 04 00 11              add      w1, w1, #1
0x002b7954: 01 fc 02 88              stlxr    w2, w1, [x0]
0x002b7958: a2 ff ff 35              cbnz     w2, #0x2b794c
0x002b795c: 00 06 80 d2              mov      x0, #0x30
0x002b7960: a5 cc 10 94              bl       #0x6eabf4                  ; -> _Znwm
0x002b7964: b8 43 02 91              add      x24, x29, #0x90
0x002b7968: f4 03 00 aa              mov      x20, x0
0x002b796c: 21 43 00 91              add      x1, x25, #0x10
0x002b7970: e0 03 18 aa              mov      x0, x24
0x002b7974: c3 00 12 94              bl       #0x737c80                  ; -> _ZNSsC2ERKSs
0x002b7978: a1 62 02 90              adrp     x1, #0x4f0b000
0x002b797c: 9f 0a 00 b9              str      wzr, [x20, #8]
0x002b7980: 21 c0 0a 91              add      x1, x1, #0x2b0
0x002b7984: 80 42 00 91              add      x0, x20, #0x10
0x002b7988: 81 02 00 f9              str      x1, [x20]
0x002b798c: e1 03 18 aa              mov      x1, x24
0x002b7990: bc 00 12 94              bl       #0x737c80                  ; -> _ZNSsC2ERKSs
0x002b7994: 96 63 02 f0              adrp     x22, #0x4f2a000
0x002b7998: a1 4b 40 f9              ldr      x1, [x29, #0x90]
0x002b799c: 20 60 00 d1              sub      x0, x1, #0x18
0x002b79a0: d6 fa 47 f9              ldr      x22, [x22, #0xff0]
0x002b79a4: 1f 00 16 eb              cmp      x0, x22
0x002b79a8: 01 3e 00 54              b.ne     #0x2b8168
0x002b79ac: 80 63 02 d0              adrp     x0, #0x4f29000
0x002b79b0: 00 c0 42 f9              ldr      x0, [x0, #0x580]
0x002b79b4: 99 0e 00 f9              str      x25, [x20, #0x18]
0x002b79b8: 00 40 00 91              add      x0, x0, #0x10
0x002b79bc: 95 12 00 f9              str      x21, [x20, #0x20]
0x002b79c0: 80 02 00 f9              str      x0, [x20]
0x002b79c4: 35 01 00 b4              cbz      x21, #0x2b79e8
0x002b79c8: 80 63 02 d0              adrp     x0, #0x4f29000
0x002b79cc: 00 94 47 f9              ldr      x0, [x0, #0xf28]           ; -> pthread_create@got
0x002b79d0: e0 20 00 b4              cbz      x0, #0x2b7dec
0x002b79d4: a0 22 00 91              add      x0, x21, #8
0x002b79d8: 01 fc 5f 88              ldaxr    w1, [x0]
0x002b79dc: 21 04 00 11              add      w1, w1, #1
0x002b79e0: 01 fc 02 88              stlxr    w2, w1, [x0]
0x002b79e4: a2 ff ff 35              cbnz     w2, #0x2b79d8
0x002b79e8: 9f a2 00 39              strb     wzr, [x20, #0x28]
0x002b79ec: 00 03 80 d2              mov      x0, #0x18
0x002b79f0: 81 cc 10 94              bl       #0x6eabf4                  ; -> _Znwm
0x002b79f4: 21 00 80 52              mov      w1, #1
0x002b79f8: 76 06 40 f9              ldr      x22, [x19, #8]
0x002b79fc: 01 08 00 b9              str      w1, [x0, #8]
0x002b7a00: 01 0c 00 b9              str      w1, [x0, #0xc]
0x002b7a04: 61 62 02 90              adrp     x1, #0x4f03000
0x002b7a08: 14 08 00 f9              str      x20, [x0, #0x10]
0x002b7a0c: 21 40 11 91              add      x1, x1, #0x450
0x002b7a10: 01 00 00 f9              str      x1, [x0]
0x002b7a14: 74 02 00 f9              str      x20, [x19]
0x002b7a18: 60 06 00 f9              str      x0, [x19, #8]
0x002b7a1c: 76 01 00 b4              cbz      x22, #0x2b7a48
0x002b7a20: 80 63 02 d0              adrp     x0, #0x4f29000
0x002b7a24: 00 94 47 f9              ldr      x0, [x0, #0xf28]           ; -> pthread_create@got
0x002b7a28: c0 2a 00 b4              cbz      x0, #0x2b7f80
0x002b7a2c: c0 22 00 91              add      x0, x22, #8
0x002b7a30: 01 fc 5f 88              ldaxr    w1, [x0]
0x002b7a34: 22 04 00 51              sub      w2, w1, #1
0x002b7a38: 02 fc 03 88              stlxr    w3, w2, [x0]
0x002b7a3c: a3 ff ff 35              cbnz     w3, #0x2b7a30
0x002b7a40: 3f 04 00 71              cmp      w1, #1
0x002b7a44: 00 2c 00 54              b.eq     #0x2b7fc4
0x002b7a48: 75 01 00 b4              cbz      x21, #0x2b7a74
0x002b7a4c: 80 63 02 d0              adrp     x0, #0x4f29000
0x002b7a50: 00 94 47 f9              ldr      x0, [x0, #0xf28]           ; -> pthread_create@got
0x002b7a54: c0 27 00 b4              cbz      x0, #0x2b7f4c
0x002b7a58: a0 22 00 91              add      x0, x21, #8
0x002b7a5c: 01 fc 5f 88              ldaxr    w1, [x0]
0x002b7a60: 22 04 00 51              sub      w2, w1, #1
0x002b7a64: 02 fc 03 88              stlxr    w3, w2, [x0]
0x002b7a68: a3 ff ff 35              cbnz     w3, #0x2b7a5c
0x002b7a6c: 3f 04 00 71              cmp      w1, #1
0x002b7a70: a0 2c 00 54              b.eq     #0x2b8004
0x002b7a74: 61 02 40 f9              ldr      x1, [x19]
0x002b7a78: e0 03 01 aa              mov      x0, x1
0x002b7a7c: 21 00 40 f9              ldr      x1, [x1]
0x002b7a80: 21 08 40 f9              ldr      x1, [x1, #0x10]
0x002b7a84: 20 00 3f d6              blr      x1
0x002b7a88: f9 03 00 2a              mov      w25, w0
0x002b7a8c: c0 21 00 35              cbnz     w0, #0x2b7ec4
0x002b7a90: 75 06 40 f9              ldr      x21, [x19, #8]
0x002b7a94: f4 0e 40 f9              ldr      x20, [x23, #0x18]
0x002b7a98: 60 02 40 f9              ldr      x0, [x19]
0x002b7a9c: e0 0a 00 f9              str      x0, [x23, #0x10]
0x002b7aa0: bf 02 14 eb              cmp      x21, x20
0x002b7aa4: a0 02 00 54              b.eq     #0x2b7af8
0x002b7aa8: 95 09 00 b4              cbz      x21, #0x2b7bd8
0x002b7aac: 81 63 02 d0              adrp     x1, #0x4f29000
0x002b7ab0: 21 94 47 f9              ldr      x1, [x1, #0xf28]           ; -> pthread_create@got
0x002b7ab4: a1 22 00 b4              cbz      x1, #0x2b7f08
0x002b7ab8: a0 22 00 91              add      x0, x21, #8
0x002b7abc: 01 fc 5f 88              ldaxr    w1, [x0]
0x002b7ac0: 21 04 00 11              add      w1, w1, #1
0x002b7ac4: 01 fc 02 88              stlxr    w2, w1, [x0]
0x002b7ac8: a2 ff ff 35              cbnz     w2, #0x2b7abc
0x002b7acc: f4 0e 40 f9              ldr      x20, [x23, #0x18]
0x002b7ad0: 80 22 00 91              add      x0, x20, #8
0x002b7ad4: f4 00 00 b4              cbz      x20, #0x2b7af0
0x002b7ad8: 01 fc 5f 88              ldaxr    w1, [x0]
0x002b7adc: 22 04 00 51              sub      w2, w1, #1
0x002b7ae0: 02 fc 03 88              stlxr    w3, w2, [x0]
0x002b7ae4: a3 ff ff 35              cbnz     w3, #0x2b7ad8
0x002b7ae8: 3f 04 00 71              cmp      w1, #1
0x002b7aec: 80 2e 00 54              b.eq     #0x2b80bc
0x002b7af0: e0 0a 40 f9              ldr      x0, [x23, #0x10]
0x002b7af4: f5 0e 00 f9              str      x21, [x23, #0x18]
0x002b7af8: a0 4b 00 f9              str      x0, [x29, #0x90]
0x002b7afc: b5 4f 00 f9              str      x21, [x29, #0x98]
0x002b7b00: 35 01 00 b4              cbz      x21, #0x2b7b24
0x002b7b04: 80 63 02 d0              adrp     x0, #0x4f29000
0x002b7b08: 00 94 47 f9              ldr      x0, [x0, #0xf28]           ; -> pthread_create@got
0x002b7b0c: 40 25 00 b4              cbz      x0, #0x2b7fb4
0x002b7b10: b5 22 00 91              add      x21, x21, #8
0x002b7b14: a0 fe 5f 88              ldaxr    w0, [x21]
0x002b7b18: 00 04 00 11              add      w0, w0, #1
0x002b7b1c: a0 fe 01 88              stlxr    w1, w0, [x21]
0x002b7b20: a1 ff ff 35              cbnz     w1, #0x2b7b14
0x002b7b24: 00 03 80 d2              mov      x0, #0x18
0x002b7b28: 33 cc 10 94              bl       #0x6eabf4                  ; -> _Znwm
0x002b7b2c: b8 43 02 91              add      x24, x29, #0x90
0x002b7b30: f3 03 00 aa              mov      x19, x0
0x002b7b34: e1 03 18 aa              mov      x1, x24
0x002b7b38: 7b 18 00 94              bl       #0x2bdd24                  ; -> _ZN9protector16TarModelResourceC2ESt10shared_ptrINS_13ModelResourceEE
0x002b7b3c: f6 06 40 f9              ldr      x22, [x23, #8]
0x002b7b40: f3 06 00 f9              str      x19, [x23, #8]
0x002b7b44: 16 09 00 b4              cbz      x22, #0x2b7c64
0x002b7b48: d3 02 40 f9              ldr      x19, [x22]                 ; -> __G78__android17_bcl_binary_data@got
0x002b7b4c: d5 06 40 f9              ldr      x21, [x22, #8]
0x002b7b50: 7f 02 15 eb              cmp      x19, x21
0x002b7b54: e0 07 00 54              b.eq     #0x2b7c50
0x002b7b58: 80 63 02 d0              adrp     x0, #0x4f29000
0x002b7b5c: 00 94 47 f9              ldr      x0, [x0, #0xf28]           ; -> pthread_create@got
0x002b7b60: a0 00 00 b5              cbnz     x0, #0x2b7b74
0x002b7b64: 23 00 00 14              b        #0x2b7bf0                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x2fc
0x002b7b68: 73 42 00 91              add      x19, x19, #0x10
0x002b7b6c: bf 02 13 eb              cmp      x21, x19
0x002b7b70: e0 06 00 54              b.eq     #0x2b7c4c
0x002b7b74: 74 06 40 f9              ldr      x20, [x19, #8]
0x002b7b78: 94 ff ff b4              cbz      x20, #0x2b7b68
0x002b7b7c: 80 22 00 91              add      x0, x20, #8
0x002b7b80: 01 fc 5f 88              ldaxr    w1, [x0]
0x002b7b84: 22 04 00 51              sub      w2, w1, #1
0x002b7b88: 02 fc 03 88              stlxr    w3, w2, [x0]
0x002b7b8c: a3 ff ff 35              cbnz     w3, #0x2b7b80
0x002b7b90: 3f 04 00 71              cmp      w1, #1
0x002b7b94: a1 fe ff 54              b.ne     #0x2b7b68
0x002b7b98: 81 02 40 f9              ldr      x1, [x20]
0x002b7b9c: e0 03 14 aa              mov      x0, x20
0x002b7ba0: 21 08 40 f9              ldr      x1, [x1, #0x10]
0x002b7ba4: 20 00 3f d6              blr      x1
0x002b7ba8: 80 32 00 91              add      x0, x20, #0xc
0x002b7bac: 01 fc 5f 88              ldaxr    w1, [x0]
0x002b7bb0: 22 04 00 51              sub      w2, w1, #1
0x002b7bb4: 02 fc 03 88              stlxr    w3, w2, [x0]
0x002b7bb8: a3 ff ff 35              cbnz     w3, #0x2b7bac
0x002b7bbc: 3f 04 00 71              cmp      w1, #1
0x002b7bc0: 41 fd ff 54              b.ne     #0x2b7b68
0x002b7bc4: 81 02 40 f9              ldr      x1, [x20]
0x002b7bc8: e0 03 14 aa              mov      x0, x20
0x002b7bcc: 21 0c 40 f9              ldr      x1, [x1, #0x18]            ; -> __bcl_binary_data_size@got
0x002b7bd0: 20 00 3f d6              blr      x1
0x002b7bd4: e5 ff ff 17              b        #0x2b7b68                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x274
0x002b7bd8: f4 f8 ff b4              cbz      x20, #0x2b7af4
0x002b7bdc: 81 63 02 d0              adrp     x1, #0x4f29000
0x002b7be0: 21 94 47 f9              ldr      x1, [x1, #0xf28]           ; -> pthread_create@got
0x002b7be4: a1 19 00 b4              cbz      x1, #0x2b7f18
0x002b7be8: 80 22 00 91              add      x0, x20, #8
0x002b7bec: bb ff ff 17              b        #0x2b7ad8                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x1e4
0x002b7bf0: 74 06 40 f9              ldr      x20, [x19, #8]
0x002b7bf4: 74 02 00 b4              cbz      x20, #0x2b7c40
0x002b7bf8: 80 0a 40 b9              ldr      w0, [x20, #8]
0x002b7bfc: 01 04 00 51              sub      w1, w0, #1
0x002b7c00: 1f 04 00 71              cmp      w0, #1
0x002b7c04: 81 0a 00 b9              str      w1, [x20, #8]
0x002b7c08: c1 01 00 54              b.ne     #0x2b7c40
0x002b7c0c: 81 02 40 f9              ldr      x1, [x20]
0x002b7c10: e0 03 14 aa              mov      x0, x20
0x002b7c14: 21 08 40 f9              ldr      x1, [x1, #0x10]
0x002b7c18: 20 00 3f d6              blr      x1
0x002b7c1c: 80 0e 40 b9              ldr      w0, [x20, #0xc]
0x002b7c20: 01 04 00 51              sub      w1, w0, #1
0x002b7c24: 1f 04 00 71              cmp      w0, #1
0x002b7c28: 81 0e 00 b9              str      w1, [x20, #0xc]
0x002b7c2c: a1 00 00 54              b.ne     #0x2b7c40
0x002b7c30: 81 02 40 f9              ldr      x1, [x20]
0x002b7c34: e0 03 14 aa              mov      x0, x20
0x002b7c38: 21 0c 40 f9              ldr      x1, [x1, #0x18]            ; -> __bcl_binary_data_size@got
0x002b7c3c: 20 00 3f d6              blr      x1
0x002b7c40: 73 42 00 91              add      x19, x19, #0x10
0x002b7c44: bf 02 13 eb              cmp      x21, x19
0x002b7c48: 41 fd ff 54              b.ne     #0x2b7bf0
0x002b7c4c: d5 02 40 f9              ldr      x21, [x22]                 ; -> __G78__android17_bcl_binary_data@got
0x002b7c50: 75 00 00 b4              cbz      x21, #0x2b7c5c
0x002b7c54: e0 03 15 aa              mov      x0, x21
0x002b7c58: 6a c5 10 94              bl       #0x6e9200                  ; -> _ZdlPv
0x002b7c5c: e0 03 16 aa              mov      x0, x22
0x002b7c60: 68 c5 10 94              bl       #0x6e9200                  ; -> _ZdlPv
0x002b7c64: b3 4f 40 f9              ldr      x19, [x29, #0x98]
0x002b7c68: 73 01 00 b4              cbz      x19, #0x2b7c94
0x002b7c6c: 80 63 02 d0              adrp     x0, #0x4f29000
0x002b7c70: 00 94 47 f9              ldr      x0, [x0, #0xf28]           ; -> pthread_create@got
0x002b7c74: 60 24 00 b4              cbz      x0, #0x2b8100
0x002b7c78: 60 22 00 91              add      x0, x19, #8
0x002b7c7c: 01 fc 5f 88              ldaxr    w1, [x0]
0x002b7c80: 22 04 00 51              sub      w2, w1, #1
0x002b7c84: 02 fc 03 88              stlxr    w3, w2, [x0]
0x002b7c88: a3 ff ff 35              cbnz     w3, #0x2b7c7c
0x002b7c8c: 3f 04 00 71              cmp      w1, #1
0x002b7c90: 60 1f 00 54              b.eq     #0x2b807c
0x002b7c94: 00 0d 80 d2              mov      x0, #0x68
0x002b7c98: d7 cb 10 94              bl       #0x6eabf4                  ; -> _Znwm
0x002b7c9c: f5 03 00 aa              mov      x21, x0
0x002b7ca0: 45 da 00 94              bl       #0x2ee5b4                  ; -> _ZN8st_model9ModelMetaC2Ev
0x002b7ca4: b4 c3 01 91              add      x20, x29, #0x70
0x002b7ca8: a1 58 02 d0              adrp     x1, #0x4dcd000
0x002b7cac: e0 03 14 aa              mov      x0, x20
0x002b7cb0: 21 00 14 91              add      x1, x1, #0x500
0x002b7cb4: a2 83 01 91              add      x2, x29, #0x60
0x002b7cb8: f6 06 40 f9              ldr      x22, [x23, #8]
0x002b7cbc: 95 f1 11 94              bl       #0x734310                  ; -> _ZNSsC2EPKcRKSaIcE
0x002b7cc0: b3 e3 01 91              add      x19, x29, #0x78
0x002b7cc4: e1 03 14 aa              mov      x1, x20
0x002b7cc8: e0 03 13 aa              mov      x0, x19
0x002b7ccc: ed ff 11 94              bl       #0x737c80                  ; -> _ZNSsC2ERKSs
0x002b7cd0: a1 58 02 d0              adrp     x1, #0x4dcd000
0x002b7cd4: e0 03 13 aa              mov      x0, x19
0x002b7cd8: 21 40 14 91              add      x1, x1, #0x510
0x002b7cdc: 82 00 80 d2              mov      x2, #4
0x002b7ce0: 93 f4 11 94              bl       #0x734f2c                  ; -> _ZNSs6appendEPKcm
0x002b7ce4: a8 03 02 91              add      x8, x29, #0x80
0x002b7ce8: e0 03 16 aa              mov      x0, x22
0x002b7cec: e1 03 13 aa              mov      x1, x19
0x002b7cf0: d3 0f 00 94              bl       #0x2bbc3c                  ; -> _ZNK9protector16TarModelResource14FindFileByNameERKSs
0x002b7cf4: a0 43 40 f9              ldr      x0, [x29, #0x80]
0x002b7cf8: 20 08 00 b4              cbz      x0, #0x2b7dfc
0x002b7cfc: 01 00 40 f9              ldr      x1, [x0]                   ; -> __6xx__android11_bcl_binary_data_size@got
0x002b7d00: 21 08 40 f9              ldr      x1, [x1, #0x10]
0x002b7d04: 20 00 3f d6              blr      x1
0x002b7d08: a0 07 00 35              cbnz     w0, #0x2b7dfc
0x002b7d0c: b3 43 40 f9              ldr      x19, [x29, #0x80]
0x002b7d10: e0 03 13 aa              mov      x0, x19
0x002b7d14: 61 02 40 f9              ldr      x1, [x19]
0x002b7d18: 21 0c 40 f9              ldr      x1, [x1, #0x18]
0x002b7d1c: 20 00 3f d6              blr      x1
0x002b7d20: 61 02 40 f9              ldr      x1, [x19]
0x002b7d24: f4 03 00 aa              mov      x20, x0
0x002b7d28: e0 03 13 aa              mov      x0, x19
0x002b7d2c: 21 18 40 f9              ldr      x1, [x1, #0x30]
0x002b7d30: 20 00 3f d6              blr      x1
0x002b7d34: e1 03 00 aa              mov      x1, x0
0x002b7d38: e2 03 14 2a              mov      w2, w20
0x002b7d3c: e0 03 18 aa              mov      x0, x24
0x002b7d40: 03 00 80 12              mov      w3, #-1
0x002b7d44: 42 ad 10 94              bl       #0x6e324c                  ; -> _ZN6google8protobuf2io16ArrayInputStreamC2EPKvii
0x002b7d48: 00 0a 80 d2              mov      x0, #0x50
0x002b7d4c: aa cb 10 94              bl       #0x6eabf4                  ; -> _Znwm
0x002b7d50: 01 00 b0 12              mov      w1, #0x7fffffff
0x002b7d54: 01 28 00 b9              str      w1, [x0, #0x28]
0x002b7d58: 01 80 a0 52              mov      w1, #0x4000000
0x002b7d5c: 01 30 00 b9              str      w1, [x0, #0x30]
0x002b7d60: 01 40 a0 52              mov      w1, #0x2000000
0x002b7d64: 18 00 00 f9              str      x24, [x0]
0x002b7d68: 01 34 00 b9              str      w1, [x0, #0x34]
0x002b7d6c: 01 08 80 52              mov      w1, #0x40
0x002b7d70: 1f 04 00 f9              str      xzr, [x0, #8]
0x002b7d74: f3 03 00 aa              mov      x19, x0
0x002b7d78: 1f 08 00 f9              str      xzr, [x0, #0x10]
0x002b7d7c: 1f 18 00 b9              str      wzr, [x0, #0x18]
0x002b7d80: 1f 1c 00 b9              str      wzr, [x0, #0x1c]
0x002b7d84: 1f 20 00 b9              str      wzr, [x0, #0x20]
0x002b7d88: 1f 90 00 39              strb     wzr, [x0, #0x24]
0x002b7d8c: 1f 94 00 39              strb     wzr, [x0, #0x25]
0x002b7d90: 1f 2c 00 b9              str      wzr, [x0, #0x2c]
0x002b7d94: 1f 38 00 b9              str      wzr, [x0, #0x38]
0x002b7d98: 01 3c 00 b9              str      w1, [x0, #0x3c]
0x002b7d9c: 1f 20 00 f9              str      xzr, [x0, #0x40]
0x002b7da0: 1f 24 00 f9              str      xzr, [x0, #0x48]
0x002b7da4: 52 b7 10 94              bl       #0x6e5aec                  ; -> _ZN6google8protobuf2io16CodedInputStream7RefreshEv
0x002b7da8: e0 03 13 aa              mov      x0, x19
0x002b7dac: 01 00 a8 52              mov      w1, #0x40000000
0x002b7db0: 02 00 a4 52              mov      w2, #0x20000000
0x002b7db4: 78 ae 10 94              bl       #0x6e3794                  ; -> _ZN6google8protobuf2io16CodedInputStream18SetTotalBytesLimitEii
0x002b7db8: e0 03 15 aa              mov      x0, x21
0x002b7dbc: e1 03 13 aa              mov      x1, x19
0x002b7dc0: a2 bd 10 94              bl       #0x6e7448                  ; -> _ZN6google8protobuf11MessageLite20ParseFromCodedStreamEPNS0_2io16CodedInputStreamE
0x002b7dc4: 61 02 40 f9              ldr      x1, [x19]
0x002b7dc8: 1a 1c 00 53              uxtb     w26, w0
0x002b7dcc: 61 00 00 b4              cbz      x1, #0x2b7dd8
0x002b7dd0: e0 03 13 aa              mov      x0, x19
0x002b7dd4: 0e ae 10 94              bl       #0x6e360c                  ; -> _ZN6google8protobuf2io16CodedInputStream28BackUpInputToCurrentPositionEv
0x002b7dd8: e0 03 13 aa              mov      x0, x19
0x002b7ddc: 09 c5 10 94              bl       #0x6e9200                  ; -> _ZdlPv
0x002b7de0: e0 03 18 aa              mov      x0, x24
0x002b7de4: 71 a9 10 94              bl       #0x6e23a8                  ; -> _ZN6google8protobuf2io16ArrayInputStreamD2Ev
0x002b7de8: 06 00 00 14              b        #0x2b7e00                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x50c
0x002b7dec: a0 0a 40 b9              ldr      w0, [x21, #8]
0x002b7df0: 00 04 00 11              add      w0, w0, #1
0x002b7df4: a0 0a 00 b9              str      w0, [x21, #8]
0x002b7df8: fc fe ff 17              b        #0x2b79e8                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0xf4
0x002b7dfc: 1a 00 80 52              mov      w26, #0
0x002b7e00: b6 47 40 f9              ldr      x22, [x29, #0x88]
0x002b7e04: 76 01 00 b4              cbz      x22, #0x2b7e30
0x002b7e08: 80 63 02 d0              adrp     x0, #0x4f29000
0x002b7e0c: 00 94 47 f9              ldr      x0, [x0, #0xf28]           ; -> pthread_create@got
0x002b7e10: 20 19 00 b4              cbz      x0, #0x2b8134
0x002b7e14: d3 22 00 91              add      x19, x22, #8
0x002b7e18: 60 fe 5f 88              ldaxr    w0, [x19]
0x002b7e1c: 01 04 00 51              sub      w1, w0, #1
0x002b7e20: 61 fe 02 88              stlxr    w2, w1, [x19]
0x002b7e24: a2 ff ff 35              cbnz     w2, #0x2b7e18
0x002b7e28: 1f 04 00 71              cmp      w0, #1
0x002b7e2c: 20 1d 00 54              b.eq     #0x2b81d0
0x002b7e30: 96 63 02 f0              adrp     x22, #0x4f2a000
0x002b7e34: b3 3f 40 f9              ldr      x19, [x29, #0x78]
0x002b7e38: 60 62 00 d1              sub      x0, x19, #0x18
0x002b7e3c: d4 fa 47 f9              ldr      x20, [x22, #0xff0]
0x002b7e40: 1f 00 14 eb              cmp      x0, x20
0x002b7e44: c1 1a 00 54              b.ne     #0x2b819c
0x002b7e48: b3 3b 40 f9              ldr      x19, [x29, #0x70]
0x002b7e4c: d6 fa 47 f9              ldr      x22, [x22, #0xff0]
0x002b7e50: 74 62 00 d1              sub      x20, x19, #0x18
0x002b7e54: 9f 02 16 eb              cmp      x20, x22
0x002b7e58: 61 0f 00 54              b.ne     #0x2b8044
0x002b7e5c: da 01 00 34              cbz      w26, #0x2b7e94
0x002b7e60: e0 02 40 f9              ldr      x0, [x23]
0x002b7e64: f5 02 00 f9              str      x21, [x23]
0x002b7e68: 80 00 00 b4              cbz      x0, #0x2b7e78
0x002b7e6c: 01 00 40 f9              ldr      x1, [x0]                   ; -> __6xx__android11_bcl_binary_data_size@got
0x002b7e70: 21 04 40 f9              ldr      x1, [x1, #8]
0x002b7e74: 20 00 3f d6              blr      x1
0x002b7e78: e0 03 19 2a              mov      w0, w25
0x002b7e7c: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x002b7e80: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x002b7e84: f7 63 43 a9              ldp      x23, x24, [sp, #0x30]
0x002b7e88: f9 6b 44 a9              ldp      x25, x26, [sp, #0x40]
0x002b7e8c: fd 7b cb a8              ldp      x29, x30, [sp], #0xb0
0x002b7e90: c0 03 5f d6              ret      
0x002b7e94: a1 02 40 f9              ldr      x1, [x21]
0x002b7e98: e0 03 15 aa              mov      x0, x21
0x002b7e9c: f9 00 80 12              mov      w25, #-8
0x002b7ea0: 21 04 40 f9              ldr      x1, [x1, #8]
0x002b7ea4: 20 00 3f d6              blr      x1
0x002b7ea8: e0 03 19 2a              mov      w0, w25
0x002b7eac: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x002b7eb0: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x002b7eb4: f7 63 43 a9              ldp      x23, x24, [sp, #0x30]
0x002b7eb8: f9 6b 44 a9              ldp      x25, x26, [sp, #0x40]
0x002b7ebc: fd 7b cb a8              ldp      x29, x30, [sp], #0xb0
0x002b7ec0: c0 03 5f d6              ret      
0x002b7ec4: 61 02 40 f9              ldr      x1, [x19]
0x002b7ec8: e0 03 01 aa              mov      x0, x1
0x002b7ecc: 21 00 40 f9              ldr      x1, [x1]
0x002b7ed0: 21 08 40 f9              ldr      x1, [x1, #0x10]
0x002b7ed4: 20 00 3f d6              blr      x1
0x002b7ed8: f9 03 00 2a              mov      w25, w0
0x002b7edc: e0 03 19 2a              mov      w0, w25
0x002b7ee0: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x002b7ee4: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x002b7ee8: f7 63 43 a9              ldp      x23, x24, [sp, #0x30]
0x002b7eec: f9 6b 44 a9              ldp      x25, x26, [sp, #0x40]
0x002b7ef0: fd 7b cb a8              ldp      x29, x30, [sp], #0xb0
0x002b7ef4: c0 03 5f d6              ret      
0x002b7ef8: a0 0a 40 b9              ldr      w0, [x21, #8]
0x002b7efc: 00 04 00 11              add      w0, w0, #1
0x002b7f00: a0 0a 00 b9              str      w0, [x21, #8]
0x002b7f04: 96 fe ff 17              b        #0x2b795c                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x68
0x002b7f08: a1 0a 40 b9              ldr      w1, [x21, #8]
0x002b7f0c: 21 04 00 11              add      w1, w1, #1
0x002b7f10: a1 0a 00 b9              str      w1, [x21, #8]
0x002b7f14: 14 df ff b4              cbz      x20, #0x2b7af4
0x002b7f18: 81 0a 40 b9              ldr      w1, [x20, #8]
0x002b7f1c: 22 04 00 51              sub      w2, w1, #1
0x002b7f20: 3f 04 00 71              cmp      w1, #1
0x002b7f24: 82 0a 00 b9              str      w2, [x20, #8]
0x002b7f28: 61 de ff 54              b.ne     #0x2b7af4
0x002b7f2c: 81 02 40 f9              ldr      x1, [x20]
0x002b7f30: e0 03 14 aa              mov      x0, x20
0x002b7f34: 21 08 40 f9              ldr      x1, [x1, #0x10]
0x002b7f38: 20 00 3f d6              blr      x1
0x002b7f3c: 80 0e 40 b9              ldr      w0, [x20, #0xc]
0x002b7f40: 01 04 00 51              sub      w1, w0, #1
0x002b7f44: 81 0e 00 b9              str      w1, [x20, #0xc]
0x002b7f48: 66 00 00 14              b        #0x2b80e0                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x7ec
0x002b7f4c: a0 0a 40 b9              ldr      w0, [x21, #8]
0x002b7f50: 01 04 00 51              sub      w1, w0, #1
0x002b7f54: 1f 04 00 71              cmp      w0, #1
0x002b7f58: a1 0a 00 b9              str      w1, [x21, #8]
0x002b7f5c: c1 d8 ff 54              b.ne     #0x2b7a74
0x002b7f60: a1 02 40 f9              ldr      x1, [x21]
0x002b7f64: e0 03 15 aa              mov      x0, x21
0x002b7f68: 21 08 40 f9              ldr      x1, [x1, #0x10]
0x002b7f6c: 20 00 3f d6              blr      x1
0x002b7f70: a0 0e 40 b9              ldr      w0, [x21, #0xc]
0x002b7f74: 01 04 00 51              sub      w1, w0, #1
0x002b7f78: a1 0e 00 b9              str      w1, [x21, #0xc]
0x002b7f7c: 2b 00 00 14              b        #0x2b8028                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x734
0x002b7f80: c0 0a 40 b9              ldr      w0, [x22, #8]
0x002b7f84: 01 04 00 51              sub      w1, w0, #1
0x002b7f88: 1f 04 00 71              cmp      w0, #1
0x002b7f8c: c1 0a 00 b9              str      w1, [x22, #8]
0x002b7f90: c1 d5 ff 54              b.ne     #0x2b7a48
0x002b7f94: c1 02 40 f9              ldr      x1, [x22]                  ; -> __G78__android17_bcl_binary_data@got
0x002b7f98: e0 03 16 aa              mov      x0, x22
0x002b7f9c: 21 08 40 f9              ldr      x1, [x1, #0x10]
0x002b7fa0: 20 00 3f d6              blr      x1
0x002b7fa4: c0 0e 40 b9              ldr      w0, [x22, #0xc]
0x002b7fa8: 01 04 00 51              sub      w1, w0, #1
0x002b7fac: c1 0e 00 b9              str      w1, [x22, #0xc]
0x002b7fb0: 0e 00 00 14              b        #0x2b7fe8                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x6f4
0x002b7fb4: a0 0a 40 b9              ldr      w0, [x21, #8]
0x002b7fb8: 00 04 00 11              add      w0, w0, #1
0x002b7fbc: a0 0a 00 b9              str      w0, [x21, #8]
0x002b7fc0: d9 fe ff 17              b        #0x2b7b24                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x230
0x002b7fc4: c1 02 40 f9              ldr      x1, [x22]                  ; -> __G78__android17_bcl_binary_data@got
0x002b7fc8: e0 03 16 aa              mov      x0, x22
0x002b7fcc: 21 08 40 f9              ldr      x1, [x1, #0x10]
0x002b7fd0: 20 00 3f d6              blr      x1
0x002b7fd4: c1 32 00 91              add      x1, x22, #0xc
0x002b7fd8: 20 fc 5f 88              ldaxr    w0, [x1]
0x002b7fdc: 02 04 00 51              sub      w2, w0, #1
0x002b7fe0: 22 fc 03 88              stlxr    w3, w2, [x1]
0x002b7fe4: a3 ff ff 35              cbnz     w3, #0x2b7fd8
0x002b7fe8: 1f 04 00 71              cmp      w0, #1
0x002b7fec: e1 d2 ff 54              b.ne     #0x2b7a48
0x002b7ff0: c1 02 40 f9              ldr      x1, [x22]                  ; -> __G78__android17_bcl_binary_data@got
0x002b7ff4: e0 03 16 aa              mov      x0, x22
0x002b7ff8: 21 0c 40 f9              ldr      x1, [x1, #0x18]
0x002b7ffc: 20 00 3f d6              blr      x1
0x002b8000: 92 fe ff 17              b        #0x2b7a48                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x154
0x002b8004: a1 02 40 f9              ldr      x1, [x21]
0x002b8008: e0 03 15 aa              mov      x0, x21
0x002b800c: 21 08 40 f9              ldr      x1, [x1, #0x10]
0x002b8010: 20 00 3f d6              blr      x1
0x002b8014: a1 32 00 91              add      x1, x21, #0xc
0x002b8018: 20 fc 5f 88              ldaxr    w0, [x1]
0x002b801c: 02 04 00 51              sub      w2, w0, #1
0x002b8020: 22 fc 03 88              stlxr    w3, w2, [x1]
0x002b8024: a3 ff ff 35              cbnz     w3, #0x2b8018
0x002b8028: 1f 04 00 71              cmp      w0, #1
0x002b802c: 41 d2 ff 54              b.ne     #0x2b7a74
0x002b8030: a1 02 40 f9              ldr      x1, [x21]
0x002b8034: e0 03 15 aa              mov      x0, x21
0x002b8038: 21 0c 40 f9              ldr      x1, [x1, #0x18]
0x002b803c: 20 00 3f d6              blr      x1
0x002b8040: 8d fe ff 17              b        #0x2b7a74                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x180
0x002b8044: 80 63 02 b0              adrp     x0, #0x4f29000
0x002b8048: 00 94 47 f9              ldr      x0, [x0, #0xf28]           ; -> pthread_create@got
0x002b804c: 20 0e 00 b4              cbz      x0, #0x2b8210
0x002b8050: 73 22 00 d1              sub      x19, x19, #8
0x002b8054: 60 fe 5f 88              ldaxr    w0, [x19]
0x002b8058: 01 04 00 51              sub      w1, w0, #1
0x002b805c: 61 fe 02 88              stlxr    w2, w1, [x19]
0x002b8060: a2 ff ff 35              cbnz     w2, #0x2b8054
0x002b8064: 1f 00 1f 6b              cmp      w0, wzr
0x002b8068: ac ef ff 54              b.gt     #0x2b7e5c
0x002b806c: e0 03 14 aa              mov      x0, x20
0x002b8070: e1 03 18 aa              mov      x1, x24
0x002b8074: a2 fe 11 94              bl       #0x737afc                  ; -> _ZNSs4_Rep10_M_destroyERKSaIcE
0x002b8078: 79 ff ff 17              b        #0x2b7e5c                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x568
0x002b807c: 61 02 40 f9              ldr      x1, [x19]
0x002b8080: e0 03 13 aa              mov      x0, x19
0x002b8084: 21 08 40 f9              ldr      x1, [x1, #0x10]
0x002b8088: 20 00 3f d6              blr      x1
0x002b808c: 61 32 00 91              add      x1, x19, #0xc
0x002b8090: 20 fc 5f 88              ldaxr    w0, [x1]
0x002b8094: 02 04 00 51              sub      w2, w0, #1
0x002b8098: 22 fc 03 88              stlxr    w3, w2, [x1]
0x002b809c: a3 ff ff 35              cbnz     w3, #0x2b8090
0x002b80a0: 1f 04 00 71              cmp      w0, #1
0x002b80a4: 81 df ff 54              b.ne     #0x2b7c94
0x002b80a8: 61 02 40 f9              ldr      x1, [x19]
0x002b80ac: e0 03 13 aa              mov      x0, x19
0x002b80b0: 21 0c 40 f9              ldr      x1, [x1, #0x18]
0x002b80b4: 20 00 3f d6              blr      x1
0x002b80b8: f7 fe ff 17              b        #0x2b7c94                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x3a0
0x002b80bc: 81 02 40 f9              ldr      x1, [x20]
0x002b80c0: e0 03 14 aa              mov      x0, x20
0x002b80c4: 21 08 40 f9              ldr      x1, [x1, #0x10]
0x002b80c8: 20 00 3f d6              blr      x1
0x002b80cc: 81 32 00 91              add      x1, x20, #0xc
0x002b80d0: 20 fc 5f 88              ldaxr    w0, [x1]
0x002b80d4: 02 04 00 51              sub      w2, w0, #1
0x002b80d8: 22 fc 03 88              stlxr    w3, w2, [x1]
0x002b80dc: a3 ff ff 35              cbnz     w3, #0x2b80d0
0x002b80e0: 1f 04 00 71              cmp      w0, #1
0x002b80e4: 61 d0 ff 54              b.ne     #0x2b7af0
0x002b80e8: 81 02 40 f9              ldr      x1, [x20]
0x002b80ec: e0 03 14 aa              mov      x0, x20
0x002b80f0: 21 0c 40 f9              ldr      x1, [x1, #0x18]
0x002b80f4: 20 00 3f d6              blr      x1
0x002b80f8: e0 0a 40 f9              ldr      x0, [x23, #0x10]
0x002b80fc: 7e fe ff 17              b        #0x2b7af4                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x200
0x002b8100: 60 0a 40 b9              ldr      w0, [x19, #8]
0x002b8104: 01 04 00 51              sub      w1, w0, #1
0x002b8108: 1f 04 00 71              cmp      w0, #1
0x002b810c: 61 0a 00 b9              str      w1, [x19, #8]
0x002b8110: 21 dc ff 54              b.ne     #0x2b7c94
0x002b8114: 61 02 40 f9              ldr      x1, [x19]
0x002b8118: e0 03 13 aa              mov      x0, x19
0x002b811c: 21 08 40 f9              ldr      x1, [x1, #0x10]
0x002b8120: 20 00 3f d6              blr      x1
0x002b8124: 60 0e 40 b9              ldr      w0, [x19, #0xc]
0x002b8128: 01 04 00 51              sub      w1, w0, #1
0x002b812c: 61 0e 00 b9              str      w1, [x19, #0xc]
0x002b8130: dc ff ff 17              b        #0x2b80a0                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x7ac
0x002b8134: c0 0a 40 b9              ldr      w0, [x22, #8]
0x002b8138: 01 04 00 51              sub      w1, w0, #1
0x002b813c: 1f 04 00 71              cmp      w0, #1
0x002b8140: c1 0a 00 b9              str      w1, [x22, #8]
0x002b8144: 61 e7 ff 54              b.ne     #0x2b7e30
0x002b8148: c1 02 40 f9              ldr      x1, [x22]                  ; -> __G78__android17_bcl_binary_data@got
0x002b814c: e0 03 16 aa              mov      x0, x22
0x002b8150: 21 08 40 f9              ldr      x1, [x1, #0x10]
0x002b8154: 20 00 3f d6              blr      x1
0x002b8158: c0 0e 40 b9              ldr      w0, [x22, #0xc]
0x002b815c: 01 04 00 51              sub      w1, w0, #1
0x002b8160: c1 0e 00 b9              str      w1, [x22, #0xc]
0x002b8164: 24 00 00 14              b        #0x2b81f4                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x900
0x002b8168: 82 63 02 b0              adrp     x2, #0x4f29000
0x002b816c: 42 94 47 f9              ldr      x2, [x2, #0xf28]           ; -> pthread_create@got
0x002b8170: 02 06 00 b4              cbz      x2, #0x2b8230
0x002b8174: 21 20 00 d1              sub      x1, x1, #8
0x002b8178: 22 fc 5f 88              ldaxr    w2, [x1]
0x002b817c: 43 04 00 51              sub      w3, w2, #1
0x002b8180: 23 fc 04 88              stlxr    w4, w3, [x1]
0x002b8184: a4 ff ff 35              cbnz     w4, #0x2b8178
0x002b8188: 5f 00 1f 6b              cmp      w2, wzr
0x002b818c: 0c c1 ff 54              b.gt     #0x2b79ac
0x002b8190: a1 03 02 91              add      x1, x29, #0x80
0x002b8194: 5a fe 11 94              bl       #0x737afc                  ; -> _ZNSs4_Rep10_M_destroyERKSaIcE
0x002b8198: 05 fe ff 17              b        #0x2b79ac                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0xb8
0x002b819c: 81 63 02 b0              adrp     x1, #0x4f29000
0x002b81a0: 21 94 47 f9              ldr      x1, [x1, #0xf28]           ; -> pthread_create@got
0x002b81a4: e1 03 00 b4              cbz      x1, #0x2b8220
0x002b81a8: 73 22 00 d1              sub      x19, x19, #8
0x002b81ac: 61 fe 5f 88              ldaxr    w1, [x19]
0x002b81b0: 22 04 00 51              sub      w2, w1, #1
0x002b81b4: 62 fe 03 88              stlxr    w3, w2, [x19]
0x002b81b8: a3 ff ff 35              cbnz     w3, #0x2b81ac
0x002b81bc: 3f 00 1f 6b              cmp      w1, wzr
0x002b81c0: 4c e4 ff 54              b.gt     #0x2b7e48
0x002b81c4: e1 03 18 aa              mov      x1, x24
0x002b81c8: 4d fe 11 94              bl       #0x737afc                  ; -> _ZNSs4_Rep10_M_destroyERKSaIcE
0x002b81cc: 1f ff ff 17              b        #0x2b7e48                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x554
0x002b81d0: c1 02 40 f9              ldr      x1, [x22]                  ; -> __G78__android17_bcl_binary_data@got
0x002b81d4: e0 03 16 aa              mov      x0, x22
0x002b81d8: 21 08 40 f9              ldr      x1, [x1, #0x10]
0x002b81dc: 20 00 3f d6              blr      x1
0x002b81e0: c1 32 00 91              add      x1, x22, #0xc
0x002b81e4: 20 fc 5f 88              ldaxr    w0, [x1]
0x002b81e8: 02 04 00 51              sub      w2, w0, #1
0x002b81ec: 22 fc 03 88              stlxr    w3, w2, [x1]
0x002b81f0: a3 ff ff 35              cbnz     w3, #0x2b81e4
0x002b81f4: 1f 04 00 71              cmp      w0, #1
0x002b81f8: c1 e1 ff 54              b.ne     #0x2b7e30
0x002b81fc: c1 02 40 f9              ldr      x1, [x22]                  ; -> __G78__android17_bcl_binary_data@got
0x002b8200: e0 03 16 aa              mov      x0, x22
0x002b8204: 21 0c 40 f9              ldr      x1, [x1, #0x18]            ; -> __bcl_binary_data_size@got
0x002b8208: 20 00 3f d6              blr      x1
0x002b820c: 09 ff ff 17              b        #0x2b7e30                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x53c
0x002b8210: 60 82 5f b8              ldur     w0, [x19, #-8]
0x002b8214: 01 04 00 51              sub      w1, w0, #1
0x002b8218: 61 82 1f b8              stur     w1, [x19, #-8]
0x002b821c: 92 ff ff 17              b        #0x2b8064                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x770
0x002b8220: 61 82 5f b8              ldur     w1, [x19, #-8]
0x002b8224: 22 04 00 51              sub      w2, w1, #1
0x002b8228: 62 82 1f b8              stur     w2, [x19, #-8]
0x002b822c: e4 ff ff 17              b        #0x2b81bc                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x8c8
0x002b8230: 22 80 5f b8              ldur     w2, [x1, #-8]
0x002b8234: 43 04 00 51              sub      w3, w2, #1
0x002b8238: 23 80 1f b8              stur     w3, [x1, #-8]
0x002b823c: d3 ff ff 17              b        #0x2b8188                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x894
0x002b8240: f3 03 00 aa              mov      x19, x0
0x002b8244: 96 63 02 d0              adrp     x22, #0x4f2a000
0x002b8248: a0 3b 40 f9              ldr      x0, [x29, #0x70]
0x002b824c: d6 fa 47 f9              ldr      x22, [x22, #0xff0]
0x002b8250: 00 60 00 d1              sub      x0, x0, #0x18
0x002b8254: 1f 00 16 eb              cmp      x0, x22
0x002b8258: 60 00 00 54              b.eq     #0x2b8264
0x002b825c: a1 a3 01 91              add      x1, x29, #0x68
0x002b8260: cf f1 ff 97              bl       #0x2b499c                  ; -> _ZNSs4_Rep10_M_disposeERKSaIcE.part.5
0x002b8264: a1 02 40 f9              ldr      x1, [x21]
0x002b8268: e0 03 15 aa              mov      x0, x21
0x002b826c: 21 04 40 f9              ldr      x1, [x1, #8]
0x002b8270: 20 00 3f d6              blr      x1
0x002b8274: e0 03 13 aa              mov      x0, x19
0x002b8278: 0f 60 12 94              bl       #0x7502b4                  ; -> _Unwind_Resume
0x002b827c: f3 03 00 aa              mov      x19, x0
0x002b8280: f9 ff ff 17              b        #0x2b8264                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x970
0x002b8284: 81 63 02 d0              adrp     x1, #0x4f2a000
0x002b8288: f3 03 00 aa              mov      x19, x0
0x002b828c: a0 4b 40 f9              ldr      x0, [x29, #0x90]
0x002b8290: 21 f8 47 f9              ldr      x1, [x1, #0xff0]
0x002b8294: 00 60 00 d1              sub      x0, x0, #0x18
0x002b8298: 1f 00 01 eb              cmp      x0, x1
0x002b829c: 60 00 00 54              b.eq     #0x2b82a8
0x002b82a0: a1 03 02 91              add      x1, x29, #0x80
0x002b82a4: be f1 ff 97              bl       #0x2b499c                  ; -> _ZNSs4_Rep10_M_disposeERKSaIcE.part.5
0x002b82a8: e0 03 14 aa              mov      x0, x20
0x002b82ac: d5 c3 10 94              bl       #0x6e9200                  ; -> _ZdlPv
0x002b82b0: 75 00 00 b4              cbz      x21, #0x2b82bc
0x002b82b4: e0 03 15 aa              mov      x0, x21
0x002b82b8: ce 67 f9 97              bl       #0x1121f0                  ; -> _ZNSt16_Sp_counted_baseILN9__gnu_cxx12_Lock_policyE2EE10_M_releaseEv
0x002b82bc: e0 03 13 aa              mov      x0, x19
0x002b82c0: fd 5f 12 94              bl       #0x7502b4                  ; -> _Unwind_Resume
0x002b82c4: f3 03 00 aa              mov      x19, x0
0x002b82c8: f8 ff ff 17              b        #0x2b82a8                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x9b4
0x002b82cc: f3 03 00 aa              mov      x19, x0
0x002b82d0: f8 ff ff 17              b        #0x2b82b0                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x9bc
0x002b82d4: f3 03 00 aa              mov      x19, x0
0x002b82d8: 96 63 02 d0              adrp     x22, #0x4f2a000
0x002b82dc: a0 3f 40 f9              ldr      x0, [x29, #0x78]
0x002b82e0: 00 60 00 d1              sub      x0, x0, #0x18
0x002b82e4: c1 fa 47 f9              ldr      x1, [x22, #0xff0]
0x002b82e8: 1f 00 01 eb              cmp      x0, x1
0x002b82ec: e0 fa ff 54              b.eq     #0x2b8248
0x002b82f0: a1 a3 01 91              add      x1, x29, #0x68
0x002b82f4: aa f1 ff 97              bl       #0x2b499c                  ; -> _ZNSs4_Rep10_M_disposeERKSaIcE.part.5
0x002b82f8: d4 ff ff 17              b        #0x2b8248                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x954
0x002b82fc: 33 c5 10 94              bl       #0x6e97c8                  ; -> __cxa_begin_catch
0x002b8300: 81 02 40 f9              ldr      x1, [x20]
0x002b8304: e0 03 14 aa              mov      x0, x20
0x002b8308: 21 04 40 f9              ldr      x1, [x1, #8]
0x002b830c: 20 00 3f d6              blr      x1
0x002b8310: 02 c9 10 94              bl       #0x6ea718                  ; -> __cxa_rethrow
0x002b8314: f4 03 00 aa              mov      x20, x0
0x002b8318: e0 03 13 aa              mov      x0, x19
0x002b831c: b9 c3 10 94              bl       #0x6e9200                  ; -> _ZdlPv
0x002b8320: a0 4f 40 f9              ldr      x0, [x29, #0x98]
0x002b8324: 40 00 00 b4              cbz      x0, #0x2b832c
0x002b8328: b2 67 f9 97              bl       #0x1121f0                  ; -> _ZNSt16_Sp_counted_baseILN9__gnu_cxx12_Lock_policyE2EE10_M_releaseEv
0x002b832c: e0 03 14 aa              mov      x0, x20
0x002b8330: e1 5f 12 94              bl       #0x7502b4                  ; -> _Unwind_Resume
0x002b8334: a0 2f 00 f9              str      x0, [x29, #0x58]
0x002b8338: 4c c5 10 94              bl       #0x6e9868                  ; -> __cxa_end_catch
0x002b833c: a0 2f 40 f9              ldr      x0, [x29, #0x58]
0x002b8340: f3 03 00 aa              mov      x19, x0
0x002b8344: db ff ff 17              b        #0x2b82b0                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x9bc
0x002b8348: f4 03 00 aa              mov      x20, x0
0x002b834c: f5 ff ff 17              b        #0x2b8320                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0xa2c
0x002b8350: 96 63 02 d0              adrp     x22, #0x4f2a000
0x002b8354: f3 03 00 aa              mov      x19, x0
0x002b8358: a0 3f 40 f9              ldr      x0, [x29, #0x78]
0x002b835c: c1 fa 47 f9              ldr      x1, [x22, #0xff0]
0x002b8360: 00 60 00 d1              sub      x0, x0, #0x18
0x002b8364: 1f 00 01 eb              cmp      x0, x1
0x002b8368: 00 f7 ff 54              b.eq     #0x2b8248
0x002b836c: e1 03 18 aa              mov      x1, x24
0x002b8370: 8b f1 ff 97              bl       #0x2b499c                  ; -> _ZNSs4_Rep10_M_disposeERKSaIcE.part.5
0x002b8374: b5 ff ff 17              b        #0x2b8248                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x954
0x002b8378: f3 03 00 aa              mov      x19, x0
0x002b837c: e0 03 18 aa              mov      x0, x24
0x002b8380: 0a a8 10 94              bl       #0x6e23a8                  ; -> _ZN6google8protobuf2io16ArrayInputStreamD2Ev
0x002b8384: a0 47 40 f9              ldr      x0, [x29, #0x88]
0x002b8388: 80 fa ff b4              cbz      x0, #0x2b82d8
0x002b838c: 99 67 f9 97              bl       #0x1121f0                  ; -> _ZNSt16_Sp_counted_baseILN9__gnu_cxx12_Lock_policyE2EE10_M_releaseEv
0x002b8390: d2 ff ff 17              b        #0x2b82d8                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x9e4
0x002b8394: e1 03 00 aa              mov      x1, x0
0x002b8398: e0 03 13 aa              mov      x0, x19
0x002b839c: f3 03 01 aa              mov      x19, x1
0x002b83a0: 98 c3 10 94              bl       #0x6e9200                  ; -> _ZdlPv
0x002b83a4: f6 ff ff 17              b        #0x2b837c                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0xa88
0x002b83a8: f3 03 00 aa              mov      x19, x0
0x002b83ac: f6 ff ff 17              b        #0x2b8384                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0xa90
0x002b83b0: f3 03 00 aa              mov      x19, x0
0x002b83b4: e0 03 15 aa              mov      x0, x21
0x002b83b8: 92 c3 10 94              bl       #0x6e9200                  ; -> _ZdlPv
0x002b83bc: c0 ff ff 17              b        #0x2b82bc                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE+0x9c8
