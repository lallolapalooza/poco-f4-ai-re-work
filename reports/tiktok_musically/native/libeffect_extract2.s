; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/ttlibs/libeffect.so
; target  : address 0x696900
; start   : 0x696900
; size    : 0x300
; bytes   : 0x300   instructions: 192
; generated: 2026-10-23T23:54:41

0x00696900: e1 03 13 aa              mov      x1, x19
0x00696904: bb 70 00 94              bl       #0x6b2bf0
0x00696908: a0 00 00 34              cbz      w0, #0x69691c
0x0069690c: 68 02 40 39              ldrb     w8, [x19]
0x00696910: 08 06 00 37              tbnz     w8, #0, #0x6969d0
0x00696914: 63 06 00 91              add      x3, x19, #1
0x00696918: 2f 00 00 14              b        #0x6969d4
0x0069691c: 61 38 00 d0              adrp     x1, #0xda4000
0x00696920: 21 0c 20 91              add      x1, x1, #0x803
0x00696924: 44 de 0f 94              bl       #0xa8e234
0x00696928: 85 1f 10 94              bl       #0xa9e73c
0x0069692c: b5 69 f2 97              bl       #0x331000
0x00696930: ff 02 00 eb              cmp      x23, x0
0x00696934: c0 05 00 54              b.eq     #0x6969ec
0x00696938: 99 0c 10 94              bl       #0xa99b9c
0x0069693c: 18 d4 0f 94              bl       #0xa8b99c
0x00696940: 59 39 10 94              bl       #0xaa4ea4
0x00696944: af 69 f2 97              bl       #0x331000
0x00696948: f6 03 00 aa              mov      x22, x0
0x0069694c: e0 03 00 91              mov      x0, sp
0x00696950: 54 47 f1 97              bl       #0x2e86a0
0x00696954: eb d8 0f 94              bl       #0xa8cd00
0x00696958: ff 02 16 eb              cmp      x23, x22
0x0069695c: a0 04 00 54              b.eq     #0x6969f0
0x00696960: b3 e6 0f 94              bl       #0xa9042c
0x00696964: 60 06 00 b4              cbz      x0, #0x696a30
0x00696968: 20 ab 10 94              bl       #0xac15e8
0x0069696c: 61 38 00 d0              adrp     x1, #0xda4000
0x00696970: 21 0c 20 91              add      x1, x1, #0x803
0x00696974: e0 63 00 91              add      x0, sp, #0x18
0x00696978: b6 02 00 f9              str      x22, [x21]
0x0069697c: b7 4a f1 97              bl       #0x2e9458
0x00696980: 6f 1f 10 94              bl       #0xa9e73c
0x00696984: 60 59 f6 97              bl       #0x42cf04
0x00696988: e6 5c 10 94              bl       #0xaadd20
0x0069698c: 04 d4 0f 94              bl       #0xa8b99c
0x00696990: 45 39 10 94              bl       #0xaa4ea4
0x00696994: 5c 59 f6 97              bl       #0x42cf04
0x00696998: 08 00 40 39              ldrb     w8, [x0]
0x0069699c: 09 08 40 f9              ldr      x9, [x0, #0x10]
0x006969a0: e1 03 15 aa              mov      x1, x21
0x006969a4: e3 03 14 aa              mov      x3, x20
0x006969a8: 1e 81 10 94              bl       #0xab6e20
0x006969ac: f4 03 00 2a              mov      w20, w0
0x006969b0: e0 03 00 91              mov      x0, sp
0x006969b4: 3b 47 f1 97              bl       #0x2e86a0
0x006969b8: d2 d8 0f 94              bl       #0xa8cd00
0x006969bc: 54 02 00 34              cbz      w20, #0x696a04
0x006969c0: 68 02 40 39              ldrb     w8, [x19]
0x006969c4: 08 04 00 37              tbnz     w8, #0, #0x696a44
0x006969c8: 63 06 00 91              add      x3, x19, #1
0x006969cc: 1f 00 00 14              b        #0x696a48
0x006969d0: 63 0a 40 f9              ldr      x3, [x19, #0x10]
0x006969d4: 61 38 00 d0              adrp     x1, #0xda4000
0x006969d8: 22 38 00 f0              adrp     x2, #0xd9d000
0x006969dc: 21 7c 1f 91              add      x1, x1, #0x7df
0x006969e0: 42 cc 32 91              add      x2, x2, #0xcb3
0x006969e4: dc d6 0f 94              bl       #0xa8c554
0x006969e8: 07 00 00 14              b        #0x696a04
0x006969ec: c5 d8 0f 94              bl       #0xa8cd00
0x006969f0: 61 38 00 d0              adrp     x1, #0xda4000
0x006969f4: 62 38 00 d0              adrp     x2, #0xda4000
0x006969f8: 21 7c 1f 91              add      x1, x1, #0x7df
0x006969fc: 42 44 20 91              add      x2, x2, #0x811
0x00696a00: d5 d6 0f 94              bl       #0xa8c554
0x00696a04: e0 1f 40 f9              ldr      x0, [sp, #0x38]
0x00696a08: e7 83 f1 97              bl       #0x2f79a4
0x00696a0c: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x00696a10: 10 d4 0f 94              bl       #0xa8ba50
0x00696a14: 01 03 00 54              b.ne     #0x696a74
0x00696a18: fd 7b 49 a9              ldp      x29, x30, [sp, #0x90]
0x00696a1c: f4 4f 48 a9              ldp      x20, x19, [sp, #0x80]
0x00696a20: f6 57 47 a9              ldp      x22, x21, [sp, #0x70]
0x00696a24: f8 5f 46 a9              ldp      x24, x23, [sp, #0x60]
0x00696a28: f9 2b 40 f9              ldr      x25, [sp, #0x50]
0x00696a2c: 68 d7 0f 14              b        #0xa8c7cc
0x00696a30: bf 02 00 f9              str      xzr, [x21]
0x00696a34: 68 02 40 39              ldrb     w8, [x19]
0x00696a38: 28 01 00 37              tbnz     w8, #0, #0x696a5c
0x00696a3c: 63 06 00 91              add      x3, x19, #1
0x00696a40: 08 00 00 14              b        #0x696a60
0x00696a44: 63 0a 40 f9              ldr      x3, [x19, #0x10]
0x00696a48: 61 38 00 d0              adrp     x1, #0xda4000
0x00696a4c: 22 38 00 f0              adrp     x2, #0xd9d000
0x00696a50: 21 7c 1f 91              add      x1, x1, #0x7df
0x00696a54: 42 b8 33 91              add      x2, x2, #0xcee
0x00696a58: e3 ff ff 17              b        #0x6969e4
0x00696a5c: 63 0a 40 f9              ldr      x3, [x19, #0x10]
0x00696a60: 61 38 00 d0              adrp     x1, #0xda4000
0x00696a64: 22 38 00 f0              adrp     x2, #0xd9d000
0x00696a68: 21 7c 1f 91              add      x1, x1, #0x7df
0x00696a6c: 42 44 33 91              add      x2, x2, #0xcd1
0x00696a70: dd ff ff 17              b        #0x6969e4
0x00696a74: 6f ba f0 97              bl       #0x2c5430                  ; -> __stack_chk_fail@plt
0x00696a78: e3 03 1e aa              mov      x3, x30
0x00696a7c: 3e 83 10 94              bl       #0xab7774
0x00696a80: fe 03 03 aa              mov      x30, x3
0x00696a84: fd 7b 0d a9              stp      x29, x30, [sp, #0xd0]
0x00696a88: fd 43 03 91              add      x29, sp, #0xd0
0x00696a8c: 6a 23 10 94              bl       #0xa9f834
0x00696a90: 29 92 10 94              bl       #0xabb334
0x00696a94: a8 83 1c f8              stur     x8, [x29, #-0x38]
0x00696a98: 01 00 00 b9              str      w1, [x0]
0x00696a9c: 1f 04 00 f9              str      xzr, [x0, #8]
0x00696aa0: 00 40 00 91              add      x0, x0, #0x10
0x00696aa4: 1f d5 12 94              bl       #0xb4bf20
0x00696aa8: b6 c2 01 91              add      x22, x21, #0x70
0x00696aac: e0 03 16 aa              mov      x0, x22
0x00696ab0: e1 fe ff 97              bl       #0x696634
0x00696ab4: 48 39 00 f0              adrp     x8, #0xdc1000
0x00696ab8: 08 c1 3a 91              add      x8, x8, #0xeb0
0x00696abc: 01 d9 74 b8              ldr      w1, [x8, w20, sxtw #2]
0x00696ac0: e0 03 00 91              mov      x0, sp
0x00696ac4: d5 fd ff 97              bl       #0x696218
0x00696ac8: 55 e5 11 94              bl       #0xb1001c
0x00696acc: e0 03 00 91              mov      x0, sp
0x00696ad0: c5 df ff 97              bl       #0x68e9e4
0x00696ad4: 97 f0 11 94              bl       #0xb12d30
0x00696ad8: a0 06 00 f9              str      x0, [x21, #8]
0x00696adc: e0 03 15 aa              mov      x0, x21
0x00696ae0: 48 00 00 94              bl       #0x696c00
0x00696ae4: e8 16 40 f9              ldr      x8, [x23, #0x28]
0x00696ae8: f1 dd 0f 94              bl       #0xa8e2ac
0x00696aec: c1 00 00 54              b.ne     #0x696b04
0x00696af0: fd 7b 4d a9              ldp      x29, x30, [sp, #0xd0]
0x00696af4: f4 4f 4c a9              ldp      x20, x19, [sp, #0xc0]
0x00696af8: f6 57 4b a9              ldp      x22, x21, [sp, #0xb0]
0x00696afc: f7 53 40 f9              ldr      x23, [sp, #0xa0]
0x00696b00: 0b df 0f 14              b        #0xa8e72c
0x00696b04: 4b ba f0 97              bl       #0x2c5430                  ; -> __stack_chk_fail@plt
0x00696b08: f6 57 bd a9              stp      x22, x21, [sp, #-0x30]!
0x00696b0c: f4 4f 01 a9              stp      x20, x19, [sp, #0x10]
0x00696b10: fd 7b 02 a9              stp      x29, x30, [sp, #0x20]
0x00696b14: fd 83 00 91              add      x29, sp, #0x20
0x00696b18: b1 f6 0f 94              bl       #0xa945dc
0x00696b1c: 60 01 00 54              b.eq     #0x696b48
0x00696b20: 09 03 80 52              mov      w9, #0x18
0x00696b24: 16 0d c9 9a              sdiv     x22, x8, x9
0x00696b28: 07 42 10 94              bl       #0xaa7344
0x00696b2c: f6 98 f1 97              bl       #0x2fcf04
0x00696b30: bc e3 0f 94              bl       #0xa8fa20
0x00696b34: fd 7b 42 a9              ldp      x29, x30, [sp, #0x20]
0x00696b38: e4 03 1e aa              mov      x4, x30
0x00696b3c: db 41 11 94              bl       #0xae72a8
0x00696b40: fe 03 04 aa              mov      x30, x4
0x00696b44: 75 47 f2 17              b        #0x328918
0x00696b48: fd 7b 42 a9              ldp      x29, x30, [sp, #0x20]
0x00696b4c: 08 df 0f 14              b        #0xa8e76c
0x00696b50: e2 03 1e aa              mov      x2, x30
0x00696b54: 24 7f 11 94              bl       #0xaf67e4
0x00696b58: fe 03 02 aa              mov      x30, x2
0x00696b5c: fd 7b 08 a9              stp      x29, x30, [sp, #0x80]
0x00696b60: fd 03 02 91              add      x29, sp, #0x80
0x00696b64: 56 d0 3b d5              mrs      x22, tpidr_el0
0x00696b68: c9 16 40 f9              ldr      x9, [x22, #0x28]
0x00696b6c: f3 03 01 aa              mov      x19, x1
0x00696b70: db 41 10 94              bl       #0xaa72dc
0x00696b74: 17 00 80 92              mov      x23, #-1
0x00696b78: 18 03 80 52              mov      w24, #0x18
0x00696b7c: e9 1f 00 f9              str      x9, [sp, #0x38]
0x00696b80: 69 2a 40 a9              ldp      x9, x10, [x19]
0x00696b84: f8 ce 11 94              bl       #0xb0a764
0x00696b88: 69 02 00 54              b.ls     #0x696bd4
0x00696b8c: 95 06 40 f9              ldr      x21, [x20, #8]
0x00696b90: 21 01 08 8b              add      x1, x9, x8
0x00696b94: e0 23 00 91              add      x0, sp, #8
0x00696b98: 19 61 00 91              add      x25, x8, #0x18
0x00696b9c: 55 48 f1 97              bl       #0x2e8cf0
0x00696ba0: b5 02 40 f9              ldr      x21, [x21]
0x00696ba4: e0 83 00 91              add      x0, sp, #0x20
0x00696ba8: ac 31 11 94              bl       #0xae3258
0x00696bac: 07 ff 0f 94              bl       #0xa967c8
0x00696bb0: 4c c5 f0 97              bl       #0x2c80e0                  ; -> _ZN8espresso8Thrustor9SkipLayerENSt6__ndk112basic_stringIcNS1_11char_traitsIcEENS1_9allocatorIcEEEE@plt
0x00696bb4: f5 03 00 2a              mov      w21, w0
0x00696bb8: e0 83 00 91              add      x0, sp, #0x20
0x00696bbc: b9 46 f1 97              bl       #0x2e86a0
0x00696bc0: 00 d5 0f 94              bl       #0xa8bfc0
0x00696bc4: e8 03 19 aa              mov      x8, x25
0x00696bc8: d5 fd ff 34              cbz      w21, #0x696b80
0x00696bcc: e0 03 1f 2a              mov      w0, wzr
0x00696bd0: 02 00 00 14              b        #0x696bd8
0x00696bd4: 20 00 80 52              mov      w0, #1
0x00696bd8: c8 16 40 f9              ldr      x8, [x22, #0x28]
0x00696bdc: 81 d2 0f 94              bl       #0xa8b5e0
0x00696be0: e1 00 00 54              b.ne     #0x696bfc
0x00696be4: fd 7b 48 a9              ldp      x29, x30, [sp, #0x80]
0x00696be8: f4 4f 47 a9              ldp      x20, x19, [sp, #0x70]
0x00696bec: f6 57 46 a9              ldp      x22, x21, [sp, #0x60]
0x00696bf0: f8 5f 45 a9              ldp      x24, x23, [sp, #0x50]
0x00696bf4: f9 23 40 f9              ldr      x25, [sp, #0x40]
0x00696bf8: c2 d5 0f 14              b        #0xa8c300
0x00696bfc: 0d ba f0 97              bl       #0x2c5430                  ; -> __stack_chk_fail@plt
