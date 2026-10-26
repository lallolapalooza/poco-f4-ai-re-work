; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : __app_protect_decipher (0x3a2904)
; start   : 0x3a2904
; size    : 0x22c
; bytes   : 0x22c   instructions: 139
; generated: 2026-10-23T23:21:43

0x003a2904: fd 7b b7 a9              stp      x29, x30, [sp, #-0x90]!
0x003a2908: fd 03 00 91              mov      x29, sp
0x003a290c: f3 53 01 a9              stp      x19, x20, [sp, #0x10]
0x003a2910: f5 5b 02 a9              stp      x21, x22, [sp, #0x20]
0x003a2914: f7 63 03 a9              stp      x23, x24, [sp, #0x30]
0x003a2918: f9 6b 04 a9              stp      x25, x26, [sp, #0x40]
0x003a291c: fb 73 05 a9              stp      x27, x28, [sp, #0x50]
0x003a2920: 80 0d 00 b4              cbz      x0, #0x3a2ad0
0x003a2924: 61 0d 00 b4              cbz      x1, #0x3a2ad0
0x003a2928: 9a da 02 90              adrp     x26, #0x5ef2000
0x003a292c: f8 03 00 aa              mov      x24, x0
0x003a2930: 40 c3 32 91              add      x0, x26, #0xcb0
0x003a2934: f9 03 01 aa              mov      x25, x1
0x003a2938: 00 20 00 91              add      x0, x0, #8
0x003a293c: e1 98 f3 97              bl       #0x88cc0                   ; -> pthread_mutex_lock@plt
0x003a2940: e0 03 84 52              mov      w0, #0x201f
0x003a2944: 01 03 40 b9              ldr      w1, [x24]
0x003a2948: 60 a0 ba 72              movk     w0, #0xd503, lsl #16
0x003a294c: 3f 00 00 6b              cmp      w1, w0
0x003a2950: a0 0b 00 54              b.eq     #0x3a2ac4
0x003a2954: 77 93 87 d2              mov      x23, #0x3c9b
0x003a2958: 3c 03 18 cb              sub      x28, x25, x24
0x003a295c: d7 9d a5 f2              movk     x23, #0x2cee, lsl #16
0x003a2960: 9c d3 03 91              add      x28, x28, #0xf4
0x003a2964: 37 ec c6 f2              movk     x23, #0x3761, lsl #32
0x003a2968: 76 93 87 d2              mov      x22, #0x3c9b
0x003a296c: f7 b7 f0 f2              movk     x23, #0x85bf, lsl #48
0x003a2970: d6 9d a5 f2              movk     x22, #0x2cee, lsl #16
0x003a2974: 36 ec c6 f2              movk     x22, #0x3761, lsl #32
0x003a2978: a0 43 02 91              add      x0, x29, #0x90
0x003a297c: 97 7f d7 9b              umulh    x23, x28, x23
0x003a2980: f6 b7 f0 f2              movk     x22, #0x85bf, lsl #48
0x003a2984: 7b d9 02 f0              adrp     x27, #0x5ed1000
0x003a2988: e3 fe 47 d3              lsr      x3, x23, #7
0x003a298c: 61 43 3a 91              add      x1, x27, #0xe90
0x003a2990: 62 e8 7b d3              lsl      x2, x3, #5
0x003a2994: 1f 8c 1e f8              str      xzr, [x0, #-0x18]!
0x003a2998: 42 08 03 8b              add      x2, x2, x3, lsl #2
0x003a299c: 21 20 00 91              add      x1, x1, #8
0x003a29a0: 57 00 03 cb              sub      x23, x2, x3
0x003a29a4: 22 00 80 52              mov      w2, #1
0x003a29a8: e3 f2 7d d3              lsl      x3, x23, #3
0x003a29ac: 77 00 17 cb              sub      x23, x3, x23
0x003a29b0: f6 7e d6 9b              umulh    x22, x23, x22
0x003a29b4: c3 fe 47 d3              lsr      x3, x22, #7
0x003a29b8: 76 dc 78 d3              lsl      x22, x3, #8
0x003a29bc: 5d 00 00 94              bl       #0x3a2b30                  ; -> _Z8read_pemPP7RSA_CTXPKcb
0x003a29c0: c0 0a 00 35              cbnz     w0, #0x3a2b18
0x003a29c4: a1 3f 40 f9              ldr      x1, [x29, #0x78]
0x003a29c8: e0 03 16 aa              mov      x0, x22
0x003a29cc: a1 37 00 f9              str      x1, [x29, #0x68]
0x003a29d0: 22 08 40 f9              ldr      x2, [x1, #0x10]
0x003a29d4: 23 04 40 f9              ldr      x3, [x1, #8]
0x003a29d8: 3c 40 80 b9              ldrsw    x28, [x1, #0x40]
0x003a29dc: 23 08 00 f9              str      x3, [x1, #0x10]
0x003a29e0: 22 04 00 f9              str      x2, [x1, #8]
0x003a29e4: 63 98 f3 97              bl       #0x88b70                   ; -> malloc@plt
0x003a29e8: f7 03 00 aa              mov      x23, x0
0x003a29ec: d6 0a dc 9a              udiv     x22, x22, x28
0x003a29f0: 16 09 00 b4              cbz      x22, #0x3a2b10
0x003a29f4: f5 03 18 aa              mov      x21, x24
0x003a29f8: 14 00 80 d2              mov      x20, #0
0x003a29fc: 13 00 80 52              mov      w19, #0
0x003a2a00: a1 37 40 f9              ldr      x1, [x29, #0x68]
0x003a2a04: 02 00 00 14              b        #0x3a2a0c                  ; -> __app_protect_decipher+0x108
0x003a2a08: a1 3f 40 f9              ldr      x1, [x29, #0x78]
0x003a2a0c: e0 03 01 aa              mov      x0, x1
0x003a2a10: 23 00 80 52              mov      w3, #1
0x003a2a14: e1 03 15 aa              mov      x1, x21
0x003a2a18: e2 c2 33 8b              add      x2, x23, w19, sxtw
0x003a2a1c: 57 01 00 94              bl       #0x3a2f78                  ; -> _Z17RSA_decrypt_clonePK7RSA_CTXPKhPhi
0x003a2a20: c0 07 f8 37              tbnz     w0, #0x1f, #0x3a2b18
0x003a2a24: 94 06 00 91              add      x20, x20, #1
0x003a2a28: 73 02 00 0b              add      w19, w19, w0
0x003a2a2c: 9f 02 16 eb              cmp      x20, x22
0x003a2a30: b5 02 1c 8b              add      x21, x21, x28
0x003a2a34: a1 fe ff 54              b.ne     #0x3a2a08
0x003a2a38: 73 7e 40 93              sxtw     x19, w19
0x003a2a3c: 14 03 13 8b              add      x20, x24, x19
0x003a2a40: 15 cf 74 92              and      x21, x24, #0xfffffffffffff000
0x003a2a44: 94 fe 3f 91              add      x20, x20, #0xfff
0x003a2a48: e0 03 15 aa              mov      x0, x21
0x003a2a4c: 94 ce 74 92              and      x20, x20, #0xfffffffffffff000
0x003a2a50: e2 00 80 52              mov      w2, #7
0x003a2a54: 94 02 15 cb              sub      x20, x20, x21
0x003a2a58: e1 03 14 aa              mov      x1, x20
0x003a2a5c: 11 98 f3 97              bl       #0x88aa0                   ; -> mprotect@plt
0x003a2a60: c0 05 00 35              cbnz     w0, #0x3a2b18
0x003a2a64: e2 03 13 aa              mov      x2, x19
0x003a2a68: e1 03 17 aa              mov      x1, x23
0x003a2a6c: e0 03 18 aa              mov      x0, x24
0x003a2a70: 58 97 f3 97              bl       #0x887d0                   ; -> memcpy@plt
0x003a2a74: a2 00 80 52              mov      w2, #5
0x003a2a78: e1 03 14 aa              mov      x1, x20
0x003a2a7c: e0 03 15 aa              mov      x0, x21
0x003a2a80: 08 98 f3 97              bl       #0x88aa0                   ; -> mprotect@plt
0x003a2a84: e0 03 17 aa              mov      x0, x23
0x003a2a88: 82 99 f3 97              bl       #0x89090                   ; -> free@plt
0x003a2a8c: a0 3f 40 f9              ldr      x0, [x29, #0x78]
0x003a2a90: f0 98 00 94              bl       #0x3c8e50                  ; -> sdk_protector_RSA_free
0x003a2a94: e0 03 18 aa              mov      x0, x24
0x003a2a98: e1 03 19 aa              mov      x1, x25
0x003a2a9c: 57 a9 0e 94              bl       #0x74cff8                  ; -> __clear_cache
0x003a2aa0: 62 93 4e b9              ldr      w2, [x27, #0xe90]
0x003a2aa4: 40 04 00 51              sub      w0, w2, #1
0x003a2aa8: 1f 1c 03 71              cmp      w0, #0xc7
0x003a2aac: 09 02 00 54              b.ls     #0x3a2aec
0x003a2ab0: e0 03 84 52              mov      w0, #0x201f
0x003a2ab4: 01 03 40 b9              ldr      w1, [x24]
0x003a2ab8: 60 a0 ba 72              movk     w0, #0xd503, lsl #16
0x003a2abc: 3f 00 00 6b              cmp      w1, w0
0x003a2ac0: e1 02 00 54              b.ne     #0x3a2b1c
0x003a2ac4: 40 c3 32 91              add      x0, x26, #0xcb0
0x003a2ac8: 00 20 00 91              add      x0, x0, #8
0x003a2acc: 55 97 f3 97              bl       #0x88820                   ; -> pthread_mutex_unlock@plt
0x003a2ad0: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x003a2ad4: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x003a2ad8: f7 63 43 a9              ldp      x23, x24, [sp, #0x30]
0x003a2adc: f9 6b 44 a9              ldp      x25, x26, [sp, #0x40]
0x003a2ae0: fb 73 45 a9              ldp      x27, x28, [sp, #0x50]
0x003a2ae4: fd 7b c9 a8              ldp      x29, x30, [sp], #0x90
0x003a2ae8: c0 03 5f d6              ret      
0x003a2aec: 03 48 88 52              mov      w3, #0x4240
0x003a2af0: a0 03 02 91              add      x0, x29, #0x80
0x003a2af4: e3 01 a0 72              movk     w3, #0xf, lsl #16
0x003a2af8: 01 00 80 d2              mov      x1, #0
0x003a2afc: bf 43 00 f9              str      xzr, [x29, #0x80]
0x003a2b00: 42 7c 23 9b              smull    x2, w2, w3
0x003a2b04: a2 47 00 f9              str      x2, [x29, #0x88]
0x003a2b08: 32 98 f3 97              bl       #0x88bd0                   ; -> nanosleep@plt
0x003a2b0c: e9 ff ff 17              b        #0x3a2ab0                  ; -> __app_protect_decipher+0x1ac
0x003a2b10: 13 00 80 d2              mov      x19, #0
0x003a2b14: ca ff ff 17              b        #0x3a2a3c                  ; -> __app_protect_decipher+0x138
0x003a2b18: fe 96 f3 97              bl       #0x88710                   ; -> abort@plt
0x003a2b1c: 40 c3 32 91              add      x0, x26, #0xcb0
0x003a2b20: 00 20 00 91              add      x0, x0, #8
0x003a2b24: 3f 97 f3 97              bl       #0x88820                   ; -> pthread_mutex_unlock@plt
0x003a2b28: 20 00 80 52              mov      w0, #1
0x003a2b2c: fd 98 f3 97              bl       #0x88f20                   ; -> exit@plt
