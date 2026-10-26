; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/xp/libdoc_photo_process.so
; target  : Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeInit (0x7dad8)
; start   : 0x7dad8
; size    : 0x164
; bytes   : 0x164   instructions: 89
; generated: 2026-10-24T00:27:04

0x0007dad8: ff 83 06 d1              sub      sp, sp, #0x1a0
0x0007dadc: fc 5f 16 a9              stp      x28, x23, [sp, #0x160]
0x0007dae0: f6 57 17 a9              stp      x22, x21, [sp, #0x170]
0x0007dae4: f4 4f 18 a9              stp      x20, x19, [sp, #0x180]
0x0007dae8: fd 7b 19 a9              stp      x29, x30, [sp, #0x190]
0x0007daec: fd 43 06 91              add      x29, sp, #0x190
0x0007daf0: 56 d0 3b d5              mrs      x22, tpidr_el0
0x0007daf4: c8 16 40 f9              ldr      x8, [x22, #0x28]
0x0007daf8: f4 03 00 aa              mov      x20, x0
0x0007dafc: e0 e3 00 91              add      x0, sp, #0x38
0x0007db00: e1 03 1e 32              mov      w1, #4
0x0007db04: f3 03 03 aa              mov      x19, x3
0x0007db08: f5 03 02 aa              mov      x21, x2
0x0007db0c: a8 83 1c f8              stur     x8, [x29, #-0x38]
0x0007db10: f7 e3 00 91              add      x23, sp, #0x38
0x0007db14: 91 e4 ff 97              bl       #0x76d58
0x0007db18: e8 83 00 91              add      x8, sp, #0x20
0x0007db1c: 63 f3 ff 97              bl       #0x7a8a8
0x0007db20: e0 62 00 91              add      x0, x23, #0x18
0x0007db24: e1 83 00 91              add      x1, sp, #0x20
0x0007db28: 53 ea ff 97              bl       #0x78474
0x0007db2c: e0 83 00 91              add      x0, sp, #0x20
0x0007db30: ce e2 ff 97              bl       #0x76668
0x0007db34: e0 e3 00 91              add      x0, sp, #0x38
0x0007db38: c3 e4 ff 97              bl       #0x76e44
0x0007db3c: e8 83 00 91              add      x8, sp, #0x20
0x0007db40: e0 03 14 aa              mov      x0, x20
0x0007db44: e1 03 15 aa              mov      x1, x21
0x0007db48: 7b e2 ff 97              bl       #0x76534
0x0007db4c: e8 23 00 91              add      x8, sp, #8
0x0007db50: e0 03 14 aa              mov      x0, x20
0x0007db54: e1 03 13 aa              mov      x1, x19
0x0007db58: 77 e2 ff 97              bl       #0x76534
0x0007db5c: 2c 10 00 94              bl       #0x81c0c
0x0007db60: e8 83 40 39              ldrb     w8, [sp, #0x20]
0x0007db64: 0d 00 40 f9              ldr      x13, [x0]
0x0007db68: e9 1b 40 f9              ldr      x9, [sp, #0x30]
0x0007db6c: eb 23 40 39              ldrb     w11, [sp, #8]
0x0007db70: ec 0f 40 f9              ldr      x12, [sp, #0x18]
0x0007db74: ea 83 00 91              add      x10, sp, #0x20
0x0007db78: 1f 01 00 72              tst      w8, #1
0x0007db7c: a8 01 40 f9              ldr      x8, [x13]
0x0007db80: ee 23 00 91              add      x14, sp, #8
0x0007db84: 4a 01 40 b2              orr      x10, x10, #1
0x0007db88: f3 03 00 aa              mov      x19, x0
0x0007db8c: 41 01 89 9a              csel     x1, x10, x9, eq
0x0007db90: c9 01 40 b2              orr      x9, x14, #1
0x0007db94: 7f 01 00 72              tst      w11, #1
0x0007db98: 22 01 8c 9a              csel     x2, x9, x12, eq
0x0007db9c: 00 01 3f d6              blr      x8
0x0007dba0: c0 00 00 34              cbz      w0, #0x7dbb8
0x0007dba4: 68 02 40 f9              ldr      x8, [x19]
0x0007dba8: e0 03 13 aa              mov      x0, x19
0x0007dbac: 08 35 40 f9              ldr      x8, [x8, #0x68]
0x0007dbb0: 00 01 3f d6              blr      x8
0x0007dbb4: f3 03 1f aa              mov      x19, xzr
0x0007dbb8: e0 23 00 91              add      x0, sp, #8
0x0007dbbc: ab e2 ff 97              bl       #0x76668
0x0007dbc0: e0 83 00 91              add      x0, sp, #0x20
0x0007dbc4: a9 e2 ff 97              bl       #0x76668
0x0007dbc8: c8 16 40 f9              ldr      x8, [x22, #0x28]
0x0007dbcc: a9 83 5c f8              ldur     x9, [x29, #-0x38]
0x0007dbd0: 1f 01 09 eb              cmp      x8, x9
0x0007dbd4: 01 01 00 54              b.ne     #0x7dbf4
0x0007dbd8: e0 03 13 aa              mov      x0, x19
0x0007dbdc: fd 7b 59 a9              ldp      x29, x30, [sp, #0x190]
0x0007dbe0: f4 4f 58 a9              ldp      x20, x19, [sp, #0x180]
0x0007dbe4: f6 57 57 a9              ldp      x22, x21, [sp, #0x170]
0x0007dbe8: fc 5f 56 a9              ldp      x28, x23, [sp, #0x160]
0x0007dbec: ff 83 06 91              add      sp, sp, #0x1a0
0x0007dbf0: c0 03 5f d6              ret      
0x0007dbf4: 0f aa ff 97              bl       #0x68430                   ; -> __stack_chk_fail@plt
0x0007dbf8: f3 03 00 aa              mov      x19, x0
0x0007dbfc: 0c 00 00 14              b        #0x7dc2c                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeInit+0x154
0x0007dc00: f3 03 00 aa              mov      x19, x0
0x0007dc04: e0 83 00 91              add      x0, sp, #0x20
0x0007dc08: 98 e2 ff 97              bl       #0x76668
0x0007dc0c: 02 00 00 14              b        #0x7dc14                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeInit+0x13c
0x0007dc10: f3 03 00 aa              mov      x19, x0
0x0007dc14: e0 e3 00 91              add      x0, sp, #0x38
0x0007dc18: 8b e4 ff 97              bl       #0x76e44
0x0007dc1c: 06 00 00 14              b        #0x7dc34                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeInit+0x15c
0x0007dc20: f3 03 00 aa              mov      x19, x0
0x0007dc24: e0 23 00 91              add      x0, sp, #8
0x0007dc28: 90 e2 ff 97              bl       #0x76668
0x0007dc2c: e0 83 00 91              add      x0, sp, #0x20
0x0007dc30: 8e e2 ff 97              bl       #0x76668
0x0007dc34: e0 03 13 aa              mov      x0, x19
0x0007dc38: 1b d7 0e 94              bl       #0x4338a4
