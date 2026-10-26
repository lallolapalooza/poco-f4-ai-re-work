; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libjni_stfaceunlock_api.so
; target  : Java_com_ts_faceauth_FaceAuthNative_enroll (0x32f4)
; start   : 0x32f4
; size    : 0x12c
; bytes   : 0x12c   instructions: 75
; generated: 2026-10-23T23:24:35

0x000032f4: ff c3 01 d1              sub      sp, sp, #0x70
0x000032f8: f8 5f 03 a9              stp      x24, x23, [sp, #0x30]
0x000032fc: f6 57 04 a9              stp      x22, x21, [sp, #0x40]
0x00003300: f4 4f 05 a9              stp      x20, x19, [sp, #0x50]
0x00003304: fd 7b 06 a9              stp      x29, x30, [sp, #0x60]
0x00003308: fd 83 01 91              add      x29, sp, #0x60
0x0000330c: 58 d0 3b d5              mrs      x24, tpidr_el0
0x00003310: 08 17 40 f9              ldr      x8, [x24, #0x28]
0x00003314: f3 03 04 aa              mov      x19, x4
0x00003318: f6 03 03 2a              mov      w22, w3
0x0000331c: f7 03 02 aa              mov      x23, x2
0x00003320: f4 03 00 aa              mov      x20, x0
0x00003324: 09 00 80 12              mov      w9, #-1
0x00003328: e8 17 00 f9              str      x8, [sp, #0x28]
0x0000332c: ff ff 01 a9              stp      xzr, xzr, [sp, #0x18]
0x00003330: ff 0b 00 f9              str      xzr, [sp, #0x10]
0x00003334: e9 0f 00 b9              str      w9, [sp, #0xc]
0x00003338: 24 01 00 b4              cbz      x4, #0x335c
0x0000333c: 88 02 40 f9              ldr      x8, [x20]
0x00003340: e0 03 14 aa              mov      x0, x20
0x00003344: e1 03 13 aa              mov      x1, x19
0x00003348: e2 03 1f aa              mov      x2, xzr
0x0000334c: 08 ed 42 f9              ldr      x8, [x8, #0x5d8]
0x00003350: 00 01 3f d6              blr      x8
0x00003354: f5 03 00 aa              mov      x21, x0
0x00003358: 02 00 00 14              b        #0x3360                    ; -> Java_com_ts_faceauth_FaceAuthNative_enroll+0x6c
0x0000335c: f5 03 1f aa              mov      x21, xzr
0x00003360: 08 00 00 d0              adrp     x8, #0x5000
0x00003364: 08 fd 47 f9              ldr      x8, [x8, #0xff8]           ; -> g_enable_facedetect_callback@got
0x00003368: 08 01 40 39              ldrb     w8, [x8]
0x0000336c: 88 00 00 34              cbz      w8, #0x337c
0x00003370: 00 00 00 90              adrp     x0, #0x3000
0x00003374: 00 80 10 91              add      x0, x0, #0x420
0x00003378: 02 00 00 14              b        #0x3380                    ; -> Java_com_ts_faceauth_FaceAuthNative_enroll+0x8c
0x0000337c: e0 03 1f aa              mov      x0, xzr
0x00003380: 48 fc ff 97              bl       #0x24a0                    ; -> st_auth_set_facedetect_callback@plt
0x00003384: e2 43 00 91              add      x2, sp, #0x10
0x00003388: e0 03 14 aa              mov      x0, x20
0x0000338c: e1 03 17 aa              mov      x1, x23
0x00003390: 70 fc ff 97              bl       #0x2550                    ; -> _Z26java_stimage_to_st_image_tP7_JNIEnvP8_jobjectP10st_image_t@plt
0x00003394: f7 03 00 aa              mov      x23, x0
0x00003398: e0 43 00 91              add      x0, sp, #0x10
0x0000339c: e2 33 00 91              add      x2, sp, #0xc
0x000033a0: e1 03 16 2a              mov      w1, w22
0x000033a4: df fb ff 97              bl       #0x2320                    ; -> st_auth_enroll@plt
0x000033a8: f6 03 00 2a              mov      w22, w0
0x000033ac: 17 01 00 b4              cbz      x23, #0x33cc
0x000033b0: 88 02 40 f9              ldr      x8, [x20]
0x000033b4: e2 0b 40 f9              ldr      x2, [sp, #0x10]
0x000033b8: e0 03 14 aa              mov      x0, x20
0x000033bc: e1 03 17 aa              mov      x1, x23
0x000033c0: 08 01 43 f9              ldr      x8, [x8, #0x600]
0x000033c4: e3 03 1f 2a              mov      w3, wzr
0x000033c8: 00 01 3f d6              blr      x8
0x000033cc: 35 01 00 b4              cbz      x21, #0x33f0
0x000033d0: b6 02 00 b9              str      w22, [x21]
0x000033d4: 88 02 40 f9              ldr      x8, [x20]
0x000033d8: e0 03 14 aa              mov      x0, x20
0x000033dc: e1 03 13 aa              mov      x1, x19
0x000033e0: e2 03 15 aa              mov      x2, x21
0x000033e4: 08 0d 43 f9              ldr      x8, [x8, #0x618]
0x000033e8: e3 03 1f 2a              mov      w3, wzr
0x000033ec: 00 01 3f d6              blr      x8
0x000033f0: e0 0f 40 b9              ldr      w0, [sp, #0xc]
0x000033f4: 08 17 40 f9              ldr      x8, [x24, #0x28]
0x000033f8: e9 17 40 f9              ldr      x9, [sp, #0x28]
0x000033fc: 1f 01 09 eb              cmp      x8, x9
0x00003400: e1 00 00 54              b.ne     #0x341c
0x00003404: fd 7b 46 a9              ldp      x29, x30, [sp, #0x60]
0x00003408: f4 4f 45 a9              ldp      x20, x19, [sp, #0x50]
0x0000340c: f6 57 44 a9              ldp      x22, x21, [sp, #0x40]
0x00003410: f8 5f 43 a9              ldp      x24, x23, [sp, #0x30]
0x00003414: ff c3 01 91              add      sp, sp, #0x70
0x00003418: c0 03 5f d6              ret      
0x0000341c: 09 fc ff 97              bl       #0x2440                    ; -> __stack_chk_fail@plt
