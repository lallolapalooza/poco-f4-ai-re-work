; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/xp/libdoc_photo_process.so
; target  : Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeInitAndRun (0x7d9a8)
; start   : 0x7d9a8
; size    : 0x130
; bytes   : 0x130   instructions: 76
; generated: 2026-10-24T00:27:05

0x0007d9a8: ff 43 06 d1              sub      sp, sp, #0x190
0x0007d9ac: fc 57 16 a9              stp      x28, x21, [sp, #0x160]
0x0007d9b0: f4 4f 17 a9              stp      x20, x19, [sp, #0x170]
0x0007d9b4: fd 7b 18 a9              stp      x29, x30, [sp, #0x180]
0x0007d9b8: fd 03 06 91              add      x29, sp, #0x180
0x0007d9bc: 55 d0 3b d5              mrs      x21, tpidr_el0
0x0007d9c0: a8 16 40 f9              ldr      x8, [x21, #0x28]
0x0007d9c4: e1 03 02 aa              mov      x1, x2
0x0007d9c8: f3 03 03 aa              mov      x19, x3
0x0007d9cc: f4 03 00 aa              mov      x20, x0
0x0007d9d0: a8 83 1d f8              stur     x8, [x29, #-0x28]
0x0007d9d4: e8 83 00 91              add      x8, sp, #0x20
0x0007d9d8: d7 e2 ff 97              bl       #0x76534
0x0007d9dc: e8 23 00 91              add      x8, sp, #8
0x0007d9e0: e0 03 14 aa              mov      x0, x20
0x0007d9e4: e1 03 13 aa              mov      x1, x19
0x0007d9e8: d3 e2 ff 97              bl       #0x76534
0x0007d9ec: 88 10 00 94              bl       #0x81c0c
0x0007d9f0: e8 83 40 39              ldrb     w8, [sp, #0x20]
0x0007d9f4: 0d 00 40 f9              ldr      x13, [x0]
0x0007d9f8: e9 1b 40 f9              ldr      x9, [sp, #0x30]
0x0007d9fc: eb 23 40 39              ldrb     w11, [sp, #8]
0x0007da00: ec 0f 40 f9              ldr      x12, [sp, #0x18]
0x0007da04: ea 83 00 91              add      x10, sp, #0x20
0x0007da08: 1f 01 00 72              tst      w8, #1
0x0007da0c: a8 05 40 f9              ldr      x8, [x13, #8]
0x0007da10: ee 23 00 91              add      x14, sp, #8
0x0007da14: 4a 01 40 b2              orr      x10, x10, #1
0x0007da18: f3 03 00 aa              mov      x19, x0
0x0007da1c: 41 01 89 9a              csel     x1, x10, x9, eq
0x0007da20: c9 01 40 b2              orr      x9, x14, #1
0x0007da24: 7f 01 00 72              tst      w11, #1
0x0007da28: 22 01 8c 9a              csel     x2, x9, x12, eq
0x0007da2c: 00 01 3f d6              blr      x8
0x0007da30: 60 01 00 34              cbz      w0, #0x7da5c
0x0007da34: e0 e3 00 91              add      x0, sp, #0x38
0x0007da38: e1 07 1f 32              mov      w1, #6
0x0007da3c: f4 e3 00 91              add      x20, sp, #0x38
0x0007da40: c6 e4 ff 97              bl       #0x76d58
0x0007da44: 80 62 00 91              add      x0, x20, #0x18
0x0007da48: c1 1d 00 b0              adrp     x1, #0x436000
0x0007da4c: 21 4c 09 91              add      x1, x1, #0x253
0x0007da50: 25 e9 ff 97              bl       #0x77ee4
0x0007da54: e0 e3 00 91              add      x0, sp, #0x38
0x0007da58: fb e4 ff 97              bl       #0x76e44
0x0007da5c: 68 02 40 f9              ldr      x8, [x19]
0x0007da60: e0 03 13 aa              mov      x0, x19
0x0007da64: 08 35 40 f9              ldr      x8, [x8, #0x68]
0x0007da68: 00 01 3f d6              blr      x8
0x0007da6c: e0 23 00 91              add      x0, sp, #8
0x0007da70: fe e2 ff 97              bl       #0x76668
0x0007da74: e0 83 00 91              add      x0, sp, #0x20
0x0007da78: fc e2 ff 97              bl       #0x76668
0x0007da7c: a8 16 40 f9              ldr      x8, [x21, #0x28]
0x0007da80: a9 83 5d f8              ldur     x9, [x29, #-0x28]
0x0007da84: 1f 01 09 eb              cmp      x8, x9
0x0007da88: c1 00 00 54              b.ne     #0x7daa0
0x0007da8c: fd 7b 58 a9              ldp      x29, x30, [sp, #0x180]
0x0007da90: f4 4f 57 a9              ldp      x20, x19, [sp, #0x170]
0x0007da94: fc 57 56 a9              ldp      x28, x21, [sp, #0x160]
0x0007da98: ff 43 06 91              add      sp, sp, #0x190
0x0007da9c: c0 03 5f d6              ret      
0x0007daa0: 64 aa ff 97              bl       #0x68430                   ; -> __stack_chk_fail@plt
0x0007daa4: f3 03 00 aa              mov      x19, x0
0x0007daa8: e0 e3 00 91              add      x0, sp, #0x38
0x0007daac: e6 e4 ff 97              bl       #0x76e44
0x0007dab0: 04 00 00 14              b        #0x7dac0                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeInitAndRun+0x118
0x0007dab4: f3 03 00 aa              mov      x19, x0
0x0007dab8: 04 00 00 14              b        #0x7dac8                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeInitAndRun+0x120
0x0007dabc: f3 03 00 aa              mov      x19, x0
0x0007dac0: e0 23 00 91              add      x0, sp, #8
0x0007dac4: e9 e2 ff 97              bl       #0x76668
0x0007dac8: e0 83 00 91              add      x0, sp, #0x20
0x0007dacc: e7 e2 ff 97              bl       #0x76668
0x0007dad0: e0 03 13 aa              mov      x0, x19
0x0007dad4: 74 d7 0e 94              bl       #0x4338a4
