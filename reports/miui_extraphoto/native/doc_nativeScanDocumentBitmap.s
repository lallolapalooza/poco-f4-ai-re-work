; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/xp/libdoc_photo_process.so
; target  : Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeScanDocumentBitmap (0x7dc54)
; start   : 0x7dc54
; size    : 0x24c
; bytes   : 0x24c   instructions: 147
; generated: 2026-10-24T00:27:02

0x0007dc54: fc 67 bb a9              stp      x28, x25, [sp, #-0x50]!
0x0007dc58: f8 5f 01 a9              stp      x24, x23, [sp, #0x10]
0x0007dc5c: f6 57 02 a9              stp      x22, x21, [sp, #0x20]
0x0007dc60: f4 4f 03 a9              stp      x20, x19, [sp, #0x30]
0x0007dc64: fd 7b 04 a9              stp      x29, x30, [sp, #0x40]
0x0007dc68: fd 03 01 91              add      x29, sp, #0x40
0x0007dc6c: ff 43 0a d1              sub      sp, sp, #0x290
0x0007dc70: 59 d0 3b d5              mrs      x25, tpidr_el0
0x0007dc74: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x0007dc78: f5 03 05 aa              mov      x21, x5
0x0007dc7c: f3 03 04 2a              mov      w19, w4
0x0007dc80: f8 03 03 aa              mov      x24, x3
0x0007dc84: f7 03 02 aa              mov      x23, x2
0x0007dc88: f6 03 00 aa              mov      x22, x0
0x0007dc8c: a8 83 1b f8              stur     x8, [x29, #-0x48]
0x0007dc90: 84 00 00 94              bl       #0x7dea0
0x0007dc94: f4 03 00 aa              mov      x20, x0
0x0007dc98: f7 03 00 b4              cbz      x23, #0x7dd14
0x0007dc9c: e2 83 00 91              add      x2, sp, #0x20
0x0007dca0: e3 73 00 91              add      x3, sp, #0x1c
0x0007dca4: e4 63 00 91              add      x4, sp, #0x18
0x0007dca8: e0 03 16 aa              mov      x0, x22
0x0007dcac: e1 03 18 aa              mov      x1, x24
0x0007dcb0: 54 eb ff 97              bl       #0x78a00
0x0007dcb4: 00 03 00 36              tbz      w0, #0, #0x7dd14
0x0007dcb8: ff ff 00 a9              stp      xzr, xzr, [sp, #8]
0x0007dcbc: ff 03 00 f9              str      xzr, [sp]
0x0007dcc0: e8 02 40 f9              ldr      x8, [x23]
0x0007dcc4: e1 13 40 f9              ldr      x1, [sp, #0x20]
0x0007dcc8: e3 0b 43 29              ldp      w3, w2, [sp, #0x18]
0x0007dccc: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x0007dcd0: e5 03 00 91              mov      x5, sp
0x0007dcd4: e0 03 17 aa              mov      x0, x23
0x0007dcd8: e4 03 13 2a              mov      w4, w19
0x0007dcdc: 00 01 3f d6              blr      x8
0x0007dce0: e8 02 40 f9              ldr      x8, [x23]
0x0007dce4: e2 07 43 29              ldp      w2, w1, [sp, #0x18]
0x0007dce8: f3 03 00 2a              mov      w19, w0
0x0007dcec: 08 2d 40 f9              ldr      x8, [x8, #0x58]
0x0007dcf0: e3 03 00 91              mov      x3, sp
0x0007dcf4: e0 03 17 aa              mov      x0, x23
0x0007dcf8: 00 01 3f d6              blr      x8
0x0007dcfc: e9 23 40 a9              ldp      x9, x8, [sp]
0x0007dd00: 08 01 09 cb              sub      x8, x8, x9
0x0007dd04: 1f 81 00 f1              cmp      x8, #0x20
0x0007dd08: a1 00 00 54              b.ne     #0x7dd1c
0x0007dd0c: f7 03 1d 32              mov      w23, #8
0x0007dd10: 14 00 00 14              b        #0x7dd60                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeScanDocumentBitmap+0x10c
0x0007dd14: f3 03 00 32              mov      w19, #1
0x0007dd18: 45 00 00 14              b        #0x7de2c                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeScanDocumentBitmap+0x1d8
0x0007dd1c: e1 03 43 2d              ldp      s1, s0, [sp, #0x18]
0x0007dd20: a8 a3 01 d1              sub      x8, x29, #0x68
0x0007dd24: bf 83 19 f8              stur     xzr, [x29, #-0x68]
0x0007dd28: bf 43 1a b8              stur     wzr, [x29, #-0x5c]
0x0007dd2c: 00 d8 21 5e              scvtf    s0, s0
0x0007dd30: 21 d8 21 5e              scvtf    s1, s1
0x0007dd34: a0 03 1a bc              stur     s0, [x29, #-0x60]
0x0007dd38: a0 07 35 2d              stp      s0, s1, [x29, #-0x58]
0x0007dd3c: bf 03 1b b8              stur     wzr, [x29, #-0x50]
0x0007dd40: a1 43 1b bc              stur     s1, [x29, #-0x4c]
0x0007dd44: 02 81 00 91              add      x2, x8, #0x20
0x0007dd48: e0 03 00 91              mov      x0, sp
0x0007dd4c: a1 a3 01 d1              sub      x1, x29, #0x68
0x0007dd50: db 0d 00 94              bl       #0x814bc
0x0007dd54: e9 23 40 a9              ldp      x9, x8, [sp]
0x0007dd58: 08 01 09 cb              sub      x8, x8, x9
0x0007dd5c: 17 fd 42 93              asr      x23, x8, #2
0x0007dd60: c8 02 40 f9              ldr      x8, [x22]
0x0007dd64: 08 ad 42 f9              ldr      x8, [x8, #0x558]
0x0007dd68: e0 03 16 aa              mov      x0, x22
0x0007dd6c: e1 03 15 aa              mov      x1, x21
0x0007dd70: 00 01 3f d6              blr      x8
0x0007dd74: ff c2 20 eb              cmp      x23, w0, sxtw
0x0007dd78: c9 01 00 54              b.ls     #0x7ddb0
0x0007dd7c: e0 23 05 91              add      x0, sp, #0x148
0x0007dd80: e1 0b 00 32              mov      w1, #7
0x0007dd84: f4 23 05 91              add      x20, sp, #0x148
0x0007dd88: f4 e3 ff 97              bl       #0x76d58
0x0007dd8c: 80 62 00 91              add      x0, x20, #0x18
0x0007dd90: c1 1d 00 b0              adrp     x1, #0x436000
0x0007dd94: 21 98 09 91              add      x1, x1, #0x266
0x0007dd98: 53 e8 ff 97              bl       #0x77ee4
0x0007dd9c: e0 23 05 91              add      x0, sp, #0x148
0x0007dda0: 29 e4 ff 97              bl       #0x76e44
0x0007dda4: e0 13 40 f9              ldr      x0, [sp, #0x20]
0x0007dda8: ff 7d 0e 94              bl       #0x41d5a4
0x0007ddac: 1e 00 00 14              b        #0x7de24                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeScanDocumentBitmap+0x1d0
0x0007ddb0: c8 02 40 f9              ldr      x8, [x22]
0x0007ddb4: e4 03 40 f9              ldr      x4, [sp]
0x0007ddb8: 08 55 43 f9              ldr      x8, [x8, #0x6a8]
0x0007ddbc: e0 03 16 aa              mov      x0, x22
0x0007ddc0: e1 03 15 aa              mov      x1, x21
0x0007ddc4: e2 03 1f 2a              mov      w2, wzr
0x0007ddc8: e3 03 17 2a              mov      w3, w23
0x0007ddcc: 00 01 3f d6              blr      x8
0x0007ddd0: e0 13 40 f9              ldr      x0, [sp, #0x20]
0x0007ddd4: f4 7d 0e 94              bl       #0x41d5a4
0x0007ddd8: e0 a3 00 91              add      x0, sp, #0x28
0x0007dddc: e1 03 1e 32              mov      w1, #4
0x0007dde0: f5 a3 00 91              add      x21, sp, #0x28
0x0007dde4: dd e3 ff 97              bl       #0x76d58
0x0007dde8: b5 62 00 91              add      x21, x21, #0x18
0x0007ddec: c1 1d 00 b0              adrp     x1, #0x436000
0x0007ddf0: 21 7c 0a 91              add      x1, x1, #0x29f
0x0007ddf4: e0 03 15 aa              mov      x0, x21
0x0007ddf8: 3b e8 ff 97              bl       #0x77ee4
0x0007ddfc: 29 00 00 94              bl       #0x7dea0
0x0007de00: 01 00 14 cb              sub      x1, x0, x20
0x0007de04: e0 03 15 aa              mov      x0, x21
0x0007de08: a2 e9 ff 97              bl       #0x78490
0x0007de0c: 81 b2 00 90              adrp     x1, #0x16cd000
0x0007de10: 21 1c 2f 91              add      x1, x1, #0xbc7
0x0007de14: e0 03 15 aa              mov      x0, x21
0x0007de18: 33 e8 ff 97              bl       #0x77ee4
0x0007de1c: e0 a3 00 91              add      x0, sp, #0x28
0x0007de20: 09 e4 ff 97              bl       #0x76e44
0x0007de24: e0 03 00 91              mov      x0, sp
0x0007de28: 2c 00 00 94              bl       #0x7ded8
0x0007de2c: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x0007de30: a9 83 5b f8              ldur     x9, [x29, #-0x48]
0x0007de34: 1f 01 09 eb              cmp      x8, x9
0x0007de38: 21 01 00 54              b.ne     #0x7de5c
0x0007de3c: e0 03 13 2a              mov      w0, w19
0x0007de40: ff 43 0a 91              add      sp, sp, #0x290
0x0007de44: fd 7b 44 a9              ldp      x29, x30, [sp, #0x40]
0x0007de48: f4 4f 43 a9              ldp      x20, x19, [sp, #0x30]
0x0007de4c: f6 57 42 a9              ldp      x22, x21, [sp, #0x20]
0x0007de50: f8 5f 41 a9              ldp      x24, x23, [sp, #0x10]
0x0007de54: fc 67 c5 a8              ldp      x28, x25, [sp], #0x50
0x0007de58: c0 03 5f d6              ret      
0x0007de5c: 75 a9 ff 97              bl       #0x68430                   ; -> __stack_chk_fail@plt
0x0007de60: 06 00 00 14              b        #0x7de78                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeScanDocumentBitmap+0x224
0x0007de64: f3 03 00 aa              mov      x19, x0
0x0007de68: e0 23 05 91              add      x0, sp, #0x148
0x0007de6c: 05 00 00 14              b        #0x7de80                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeScanDocumentBitmap+0x22c
0x0007de70: 07 00 00 14              b        #0x7de8c                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeScanDocumentBitmap+0x238
0x0007de74: 06 00 00 14              b        #0x7de8c                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeScanDocumentBitmap+0x238
0x0007de78: f3 03 00 aa              mov      x19, x0
0x0007de7c: e0 a3 00 91              add      x0, sp, #0x28
0x0007de80: f1 e3 ff 97              bl       #0x76e44
0x0007de84: 03 00 00 14              b        #0x7de90                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeScanDocumentBitmap+0x23c
0x0007de88: 01 00 00 14              b        #0x7de8c                   ; -> Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeScanDocumentBitmap+0x238
0x0007de8c: f3 03 00 aa              mov      x19, x0
0x0007de90: e0 03 00 91              mov      x0, sp
0x0007de94: 11 00 00 94              bl       #0x7ded8
0x0007de98: e0 03 13 aa              mov      x0, x19
0x0007de9c: 82 d6 0e 94              bl       #0x4338a4
