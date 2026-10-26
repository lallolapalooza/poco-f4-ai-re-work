; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libjni_stfaceunlock_api.so
; target  : Java_com_ts_faceauth_FaceAuthNative_auth (0x3530)
; start   : 0x3530
; size    : 0x19c
; bytes   : 0x19c   instructions: 103
; generated: 2026-10-23T23:24:35

0x00003530: ff 03 02 d1              sub      sp, sp, #0x80
0x00003534: fb 13 00 f9              str      x27, [sp, #0x20]
0x00003538: fa 67 03 a9              stp      x26, x25, [sp, #0x30]
0x0000353c: f8 5f 04 a9              stp      x24, x23, [sp, #0x40]
0x00003540: f6 57 05 a9              stp      x22, x21, [sp, #0x50]
0x00003544: f4 4f 06 a9              stp      x20, x19, [sp, #0x60]
0x00003548: fd 7b 07 a9              stp      x29, x30, [sp, #0x70]
0x0000354c: fd c3 01 91              add      x29, sp, #0x70
0x00003550: 5b d0 3b d5              mrs      x27, tpidr_el0
0x00003554: 68 17 40 f9              ldr      x8, [x27, #0x28]
0x00003558: f3 03 05 aa              mov      x19, x5
0x0000355c: f5 03 04 aa              mov      x21, x4
0x00003560: f7 03 03 2a              mov      w23, w3
0x00003564: f8 03 02 aa              mov      x24, x2
0x00003568: f4 03 00 aa              mov      x20, x0
0x0000356c: e8 0f 00 f9              str      x8, [sp, #0x18]
0x00003570: ff ff 00 a9              stp      xzr, xzr, [sp, #8]
0x00003574: ff 03 00 f9              str      xzr, [sp]
0x00003578: e5 02 00 b4              cbz      x5, #0x35d4
0x0000357c: 88 02 40 f9              ldr      x8, [x20]
0x00003580: e0 03 14 aa              mov      x0, x20
0x00003584: e1 03 13 aa              mov      x1, x19
0x00003588: e2 03 1f aa              mov      x2, xzr
0x0000358c: 08 ed 42 f9              ldr      x8, [x8, #0x5d8]
0x00003590: 00 01 3f d6              blr      x8
0x00003594: f6 03 00 aa              mov      x22, x0
0x00003598: 35 02 00 b4              cbz      x21, #0x35dc
0x0000359c: 88 02 40 f9              ldr      x8, [x20]
0x000035a0: e0 03 14 aa              mov      x0, x20
0x000035a4: e1 03 15 aa              mov      x1, x21
0x000035a8: e2 03 1f aa              mov      x2, xzr
0x000035ac: 08 ed 42 f9              ldr      x8, [x8, #0x5d8]
0x000035b0: 00 01 3f d6              blr      x8
0x000035b4: 88 02 40 f9              ldr      x8, [x20]
0x000035b8: f9 03 00 aa              mov      x25, x0
0x000035bc: e0 03 14 aa              mov      x0, x20
0x000035c0: e1 03 15 aa              mov      x1, x21
0x000035c4: 08 ad 42 f9              ldr      x8, [x8, #0x558]
0x000035c8: 00 01 3f d6              blr      x8
0x000035cc: fa 03 00 2a              mov      w26, w0
0x000035d0: 05 00 00 14              b        #0x35e4                    ; -> Java_com_ts_faceauth_FaceAuthNative_auth+0xb4
0x000035d4: f6 03 1f aa              mov      x22, xzr
0x000035d8: 35 fe ff b5              cbnz     x21, #0x359c
0x000035dc: f9 03 1f aa              mov      x25, xzr
0x000035e0: fa 03 1f 2a              mov      w26, wzr
0x000035e4: 08 00 00 d0              adrp     x8, #0x5000
0x000035e8: 08 fd 47 f9              ldr      x8, [x8, #0xff8]           ; -> g_enable_facedetect_callback@got
0x000035ec: 08 01 40 39              ldrb     w8, [x8]
0x000035f0: 88 00 00 34              cbz      w8, #0x3600
0x000035f4: 00 00 00 90              adrp     x0, #0x3000
0x000035f8: 00 80 10 91              add      x0, x0, #0x420
0x000035fc: 02 00 00 14              b        #0x3604                    ; -> Java_com_ts_faceauth_FaceAuthNative_auth+0xd4
0x00003600: e0 03 1f aa              mov      x0, xzr
0x00003604: a7 fb ff 97              bl       #0x24a0                    ; -> st_auth_set_facedetect_callback@plt
0x00003608: e2 03 00 91              mov      x2, sp
0x0000360c: e0 03 14 aa              mov      x0, x20
0x00003610: e1 03 18 aa              mov      x1, x24
0x00003614: cf fb ff 97              bl       #0x2550                    ; -> _Z26java_stimage_to_st_image_tP7_JNIEnvP8_jobjectP10st_image_t@plt
0x00003618: f8 03 00 aa              mov      x24, x0
0x0000361c: e0 03 00 91              mov      x0, sp
0x00003620: e1 03 17 2a              mov      w1, w23
0x00003624: e2 03 19 aa              mov      x2, x25
0x00003628: e3 03 1a 2a              mov      w3, w26
0x0000362c: 41 fb ff 97              bl       #0x2330                    ; -> st_auth_unlock@plt
0x00003630: f7 03 00 2a              mov      w23, w0
0x00003634: 18 01 00 b4              cbz      x24, #0x3654
0x00003638: 88 02 40 f9              ldr      x8, [x20]
0x0000363c: e2 03 40 f9              ldr      x2, [sp]
0x00003640: e0 03 14 aa              mov      x0, x20
0x00003644: e1 03 18 aa              mov      x1, x24
0x00003648: 08 01 43 f9              ldr      x8, [x8, #0x600]
0x0000364c: e3 03 1f 2a              mov      w3, wzr
0x00003650: 00 01 3f d6              blr      x8
0x00003654: 19 01 00 b4              cbz      x25, #0x3674
0x00003658: 88 02 40 f9              ldr      x8, [x20]
0x0000365c: e0 03 14 aa              mov      x0, x20
0x00003660: e1 03 15 aa              mov      x1, x21
0x00003664: e2 03 19 aa              mov      x2, x25
0x00003668: 08 0d 43 f9              ldr      x8, [x8, #0x618]
0x0000366c: e3 03 1f 2a              mov      w3, wzr
0x00003670: 00 01 3f d6              blr      x8
0x00003674: 36 01 00 b4              cbz      x22, #0x3698
0x00003678: d7 02 00 b9              str      w23, [x22]
0x0000367c: 88 02 40 f9              ldr      x8, [x20]
0x00003680: e0 03 14 aa              mov      x0, x20
0x00003684: e1 03 13 aa              mov      x1, x19
0x00003688: e2 03 16 aa              mov      x2, x22
0x0000368c: 08 0d 43 f9              ldr      x8, [x8, #0x618]
0x00003690: e3 03 1f 2a              mov      w3, wzr
0x00003694: 00 01 3f d6              blr      x8
0x00003698: 68 17 40 f9              ldr      x8, [x27, #0x28]
0x0000369c: e9 0f 40 f9              ldr      x9, [sp, #0x18]
0x000036a0: 1f 01 09 eb              cmp      x8, x9
0x000036a4: 21 01 00 54              b.ne     #0x36c8
0x000036a8: fd 7b 47 a9              ldp      x29, x30, [sp, #0x70]
0x000036ac: f4 4f 46 a9              ldp      x20, x19, [sp, #0x60]
0x000036b0: f6 57 45 a9              ldp      x22, x21, [sp, #0x50]
0x000036b4: f8 5f 44 a9              ldp      x24, x23, [sp, #0x40]
0x000036b8: fa 67 43 a9              ldp      x26, x25, [sp, #0x30]
0x000036bc: fb 13 40 f9              ldr      x27, [sp, #0x20]
0x000036c0: ff 03 02 91              add      sp, sp, #0x80
0x000036c4: c0 03 5f d6              ret      
0x000036c8: 5e fb ff 97              bl       #0x2440                    ; -> __stack_chk_fail@plt
