; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libqvajni.so
; target  : address 0x1293c
; start   : 0x1293c
; size    : 0x274
; bytes   : 0x274   instructions: 157
; generated: 2026-10-23T23:38:48

0x0001293c: 3f 23 03 d5              paciasp  
0x00012940: ff c3 01 d1              sub      sp, sp, #0x70
0x00012944: fd 7b 02 a9              stp      x29, x30, [sp, #0x20]
0x00012948: f9 1b 00 f9              str      x25, [sp, #0x30]
0x0001294c: f8 5f 04 a9              stp      x24, x23, [sp, #0x40]
0x00012950: f6 57 05 a9              stp      x22, x21, [sp, #0x50]
0x00012954: f4 4f 06 a9              stp      x20, x19, [sp, #0x60]
0x00012958: fd 83 00 91              add      x29, sp, #0x20
0x0001295c: 59 d0 3b d5              mrs      x25, tpidr_el0
0x00012960: f4 03 04 2a              mov      w20, w4
0x00012964: f5 03 03 aa              mov      x21, x3
0x00012968: f7 03 02 2a              mov      w23, w2
0x0001296c: f6 03 01 aa              mov      x22, x1
0x00012970: f3 03 00 aa              mov      x19, x0
0x00012974: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x00012978: 89 78 1f 12              and      w9, w4, #0xfffffffe
0x0001297c: 3f 09 00 71              cmp      w9, #2
0x00012980: a8 83 1f f8              stur     x8, [x29, #-8]
0x00012984: ff ff 00 a9              stp      xzr, xzr, [sp, #8]
0x00012988: a1 00 00 54              b.ne     #0x1299c
0x0001298c: 68 8e 40 f9              ldr      x8, [x19, #0x118]
0x00012990: 88 03 00 b4              cbz      x8, #0x12a00
0x00012994: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00012998: 48 03 00 b4              cbz      x8, #0x12a00
0x0001299c: 9f 06 00 71              cmp      w20, #1
0x000129a0: 60 00 00 54              b.eq     #0x129ac
0x000129a4: 9f 12 00 71              cmp      w20, #4
0x000129a8: 61 03 00 54              b.ne     #0x12a14
0x000129ac: 20 00 80 52              mov      w0, #1
0x000129b0: 01 04 80 52              mov      w1, #0x20
0x000129b4: 9f 33 00 94              bl       #0x1f830                   ; -> calloc@plt
0x000129b8: 60 8e 00 f9              str      x0, [x19, #0x118]
0x000129bc: c0 05 00 b4              cbz      x0, #0x12a74
0x000129c0: f8 03 00 aa              mov      x24, x0
0x000129c4: 20 00 80 52              mov      w0, #1
0x000129c8: 01 02 80 52              mov      w1, #0x10
0x000129cc: 99 33 00 94              bl       #0x1f830                   ; -> calloc@plt
0x000129d0: 00 0b 00 f9              str      x0, [x24, #0x10]
0x000129d4: c0 07 00 b4              cbz      x0, #0x12acc
0x000129d8: 20 00 80 52              mov      w0, #1
0x000129dc: 81 03 80 52              mov      w1, #0x1c
0x000129e0: 94 33 00 94              bl       #0x1f830                   ; -> calloc@plt
0x000129e4: 60 92 00 f9              str      x0, [x19, #0x120]
0x000129e8: 80 01 00 b5              cbnz     x0, #0x12a18
0x000129ec: 81 ff ff f0              adrp     x1, #0x5000
0x000129f0: 21 cc 36 91              add      x1, x1, #0xdb3
0x000129f4: 82 ff ff d0              adrp     x2, #0x4000
0x000129f8: 42 04 20 91              add      x2, x2, #0x801
0x000129fc: 38 00 00 14              b        #0x12adc                   ; -> _ZN9Algorithm25keyword_detection_processEPsjP24algorithm_process_resultj+0x1a0
0x00012a00: 81 ff ff f0              adrp     x1, #0x5000
0x00012a04: 21 cc 36 91              add      x1, x1, #0xdb3
0x00012a08: a2 ff ff f0              adrp     x2, #0x9000
0x00012a0c: 42 68 2b 91              add      x2, x2, #0xada
0x00012a10: 33 00 00 14              b        #0x12adc                   ; -> _ZN9Algorithm25keyword_detection_processEPsjP24algorithm_process_resultj+0x1a0
0x00012a14: 78 8e 40 f9              ldr      x24, [x19, #0x118]
0x00012a18: 28 00 80 52              mov      w8, #1
0x00012a1c: 61 62 04 91              add      x1, x19, #0x118
0x00012a20: e2 03 1f aa              mov      x2, xzr
0x00012a24: 08 1b 00 b9              str      w8, [x24, #0x18]
0x00012a28: 68 8e 40 f9              ldr      x8, [x19, #0x118]
0x00012a2c: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00012a30: 17 0d 00 b9              str      w23, [x8, #0xc]
0x00012a34: 68 8e 40 f9              ldr      x8, [x19, #0x118]
0x00012a38: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00012a3c: 17 09 00 b9              str      w23, [x8, #8]
0x00012a40: 68 8e 40 f9              ldr      x8, [x19, #0x118]
0x00012a44: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00012a48: 16 01 00 f9              str      x22, [x8]
0x00012a4c: 60 06 40 f9              ldr      x0, [x19, #8]
0x00012a50: 08 00 40 f9              ldr      x8, [x0]
0x00012a54: 08 01 40 f9              ldr      x8, [x8]
0x00012a58: 00 01 3f d6              blr      x8
0x00012a5c: 60 01 00 34              cbz      w0, #0x12a88
0x00012a60: 81 ff ff f0              adrp     x1, #0x5000
0x00012a64: 21 cc 36 91              add      x1, x1, #0xdb3
0x00012a68: a2 ff ff f0              adrp     x2, #0x9000
0x00012a6c: 42 74 35 91              add      x2, x2, #0xd5d
0x00012a70: 1b 00 00 14              b        #0x12adc                   ; -> _ZN9Algorithm25keyword_detection_processEPsjP24algorithm_process_resultj+0x1a0
0x00012a74: 81 ff ff f0              adrp     x1, #0x5000
0x00012a78: 21 cc 36 91              add      x1, x1, #0xdb3
0x00012a7c: a2 ff ff b0              adrp     x2, #0x7000
0x00012a80: 42 5c 28 91              add      x2, x2, #0xa17
0x00012a84: 16 00 00 14              b        #0x12adc                   ; -> _ZN9Algorithm25keyword_detection_processEPsjP24algorithm_process_resultj+0x1a0
0x00012a88: 80 07 00 0f              movi     v0.2s, #0x1c
0x00012a8c: 68 92 40 f9              ldr      x8, [x19, #0x120]
0x00012a90: 60 06 40 f9              ldr      x0, [x19, #8]
0x00012a94: e3 23 00 91              add      x3, sp, #8
0x00012a98: e1 00 80 52              mov      w1, #7
0x00012a9c: e2 03 1f aa              mov      x2, xzr
0x00012aa0: e8 07 00 f9              str      x8, [sp, #8]
0x00012aa4: e0 0b 00 fd              str      d0, [sp, #0x10]
0x00012aa8: 08 00 40 f9              ldr      x8, [x0]
0x00012aac: 08 0d 40 f9              ldr      x8, [x8, #0x18]
0x00012ab0: 00 01 3f d6              blr      x8
0x00012ab4: c0 04 00 34              cbz      w0, #0x12b4c
0x00012ab8: 81 ff ff f0              adrp     x1, #0x5000
0x00012abc: 21 cc 36 91              add      x1, x1, #0xdb3
0x00012ac0: a2 ff ff 90              adrp     x2, #0x6000
0x00012ac4: 42 7c 02 91              add      x2, x2, #0x9f
0x00012ac8: 05 00 00 14              b        #0x12adc                   ; -> _ZN9Algorithm25keyword_detection_processEPsjP24algorithm_process_resultj+0x1a0
0x00012acc: 81 ff ff f0              adrp     x1, #0x5000
0x00012ad0: 21 cc 36 91              add      x1, x1, #0xdb3
0x00012ad4: a2 ff ff f0              adrp     x2, #0x9000
0x00012ad8: 42 2c 17 91              add      x2, x2, #0x5cb
0x00012adc: c0 00 80 52              mov      w0, #6
0x00012ae0: 42 33 00 94              bl       #0x1f7e8
0x00012ae4: 28 00 80 52              mov      w8, #1
0x00012ae8: 89 0e 00 51              sub      w9, w20, #3
0x00012aec: 3f 09 00 71              cmp      w9, #2
0x00012af0: e3 00 00 54              b.lo     #0x12b0c
0x00012af4: 69 92 40 f9              ldr      x9, [x19, #0x120]
0x00012af8: 29 09 40 b9              ldr      w9, [x9, #8]
0x00012afc: 3f 01 00 71              cmp      w9, #0
0x00012b00: e9 17 9f 1a              cset     w9, eq
0x00012b04: 28 01 28 0a              bic      w8, w9, w8
0x00012b08: 88 00 00 37              tbnz     w8, #0, #0x12b18
0x00012b0c: e0 03 13 aa              mov      x0, x19
0x00012b10: 1a 34 00 94              bl       #0x1fb78
0x00012b14: 02 00 00 14              b        #0x12b1c                   ; -> _ZN9Algorithm25keyword_detection_processEPsjP24algorithm_process_resultj+0x1e0
0x00012b18: e0 03 1f 2a              mov      w0, wzr
0x00012b1c: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x00012b20: a9 83 5f f8              ldur     x9, [x29, #-8]
0x00012b24: 1f 01 09 eb              cmp      x8, x9
0x00012b28: 21 04 00 54              b.ne     #0x12bac
0x00012b2c: f4 4f 46 a9              ldp      x20, x19, [sp, #0x60]
0x00012b30: f6 57 45 a9              ldp      x22, x21, [sp, #0x50]
0x00012b34: f8 5f 44 a9              ldp      x24, x23, [sp, #0x40]
0x00012b38: fd 7b 42 a9              ldp      x29, x30, [sp, #0x20]
0x00012b3c: f9 1b 40 f9              ldr      x25, [sp, #0x30]
0x00012b40: ff c3 01 91              add      sp, sp, #0x70
0x00012b44: bf 23 03 d5              autiasp  
0x00012b48: c0 03 5f d6              ret      
0x00012b4c: 68 92 40 f9              ldr      x8, [x19, #0x120]
0x00012b50: 08 09 40 b9              ldr      w8, [x8, #8]
0x00012b54: a8 01 00 34              cbz      w8, #0x12b88
0x00012b58: 28 00 80 52              mov      w8, #1
0x00012b5c: a8 02 00 b9              str      w8, [x21]
0x00012b60: 68 92 40 f9              ldr      x8, [x19, #0x120]
0x00012b64: 08 11 40 b9              ldr      w8, [x8, #0x10]
0x00012b68: 08 09 08 0b              add      w8, w8, w8, lsl #2
0x00012b6c: 08 65 1a 53              lsl      w8, w8, #6
0x00012b70: a8 3e 00 b9              str      w8, [x21, #0x3c]
0x00012b74: 68 92 40 f9              ldr      x8, [x19, #0x120]
0x00012b78: 08 15 40 b9              ldr      w8, [x8, #0x14]
0x00012b7c: 08 09 08 0b              add      w8, w8, w8, lsl #2
0x00012b80: 08 65 1a 53              lsl      w8, w8, #6
0x00012b84: 04 00 00 14              b        #0x12b94                   ; -> _ZN9Algorithm25keyword_detection_processEPsjP24algorithm_process_resultj+0x258
0x00012b88: 08 00 80 12              mov      w8, #-1
0x00012b8c: bf 02 00 b9              str      wzr, [x21]
0x00012b90: a8 3e 00 b9              str      w8, [x21, #0x3c]
0x00012b94: a8 42 00 b9              str      w8, [x21, #0x40]
0x00012b98: 68 92 40 f9              ldr      x8, [x19, #0x120]
0x00012b9c: 09 0d 40 b9              ldr      w9, [x8, #0xc]
0x00012ba0: e8 03 1f 2a              mov      w8, wzr
0x00012ba4: a9 3a 00 b9              str      w9, [x21, #0x38]
0x00012ba8: d0 ff ff 17              b        #0x12ae8                   ; -> _ZN9Algorithm25keyword_detection_processEPsjP24algorithm_process_resultj+0x1ac
0x00012bac: 4b 33 00 94              bl       #0x1f8d8
