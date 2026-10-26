; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_extraphoto/native/libmibrain_relighting_jni.so
; target  : Java_com_miui_extraphoto_refocus_manager_MiDualPhotoJni_initBrainRelightingEffect (0xe7a0)
; start   : 0xe7a0
; size    : 0x228
; bytes   : 0x228   instructions: 138
; generated: 2026-10-23T22:58:01

0x0000e7a0: ff c3 01 d1              sub      sp, sp, #0x70
0x0000e7a4: fb 0b 00 f9              str      x27, [sp, #0x10]
0x0000e7a8: fa 67 02 a9              stp      x26, x25, [sp, #0x20]
0x0000e7ac: f8 5f 03 a9              stp      x24, x23, [sp, #0x30]
0x0000e7b0: f6 57 04 a9              stp      x22, x21, [sp, #0x40]
0x0000e7b4: f4 4f 05 a9              stp      x20, x19, [sp, #0x50]
0x0000e7b8: fd 7b 06 a9              stp      x29, x30, [sp, #0x60]
0x0000e7bc: fd 83 01 91              add      x29, sp, #0x60
0x0000e7c0: 5b d0 3b d5              mrs      x27, tpidr_el0
0x0000e7c4: 68 17 40 f9              ldr      x8, [x27, #0x28]
0x0000e7c8: f6 03 02 2a              mov      w22, w2
0x0000e7cc: c1 00 00 f0              adrp     x1, #0x29000
0x0000e7d0: c2 00 00 f0              adrp     x2, #0x29000
0x0000e7d4: f4 03 00 aa              mov      x20, x0
0x0000e7d8: 21 58 00 91              add      x1, x1, #0x16
0x0000e7dc: 42 ac 00 91              add      x2, x2, #0x2b
0x0000e7e0: e0 07 00 32              mov      w0, #3
0x0000e7e4: f3 03 06 aa              mov      x19, x6
0x0000e7e8: f8 03 03 aa              mov      x24, x3
0x0000e7ec: e8 07 00 f9              str      x8, [sp, #8]
0x0000e7f0: 74 fd ff 97              bl       #0xddc0                    ; -> __android_log_print@plt
0x0000e7f4: 88 02 40 f9              ldr      x8, [x20]
0x0000e7f8: e0 03 14 aa              mov      x0, x20
0x0000e7fc: e1 03 13 aa              mov      x1, x19
0x0000e800: e2 03 1f aa              mov      x2, xzr
0x0000e804: 08 ed 42 f9              ldr      x8, [x8, #0x5d8]
0x0000e808: 00 01 3f d6              blr      x8
0x0000e80c: f5 03 00 aa              mov      x21, x0
0x0000e810: e0 07 1e 32              mov      w0, #0xc
0x0000e814: ff 03 00 f9              str      xzr, [sp]
0x0000e818: fa fc ff 97              bl       #0xdc00                    ; -> _Znwm@plt
0x0000e81c: a8 06 40 b9              ldr      w8, [x21, #4]
0x0000e820: f7 03 00 aa              mov      x23, x0
0x0000e824: df 3a 04 71              cmp      w22, #0x10e
0x0000e828: 08 00 00 b9              str      w8, [x0]
0x0000e82c: a8 0a 40 b9              ldr      w8, [x21, #8]
0x0000e830: 08 04 00 b9              str      w8, [x0, #4]
0x0000e834: 20 01 00 54              b.eq     #0xe858
0x0000e838: df d2 02 71              cmp      w22, #0xb4
0x0000e83c: a0 00 00 54              b.eq     #0xe850
0x0000e840: df 6a 01 71              cmp      w22, #0x5a
0x0000e844: e1 00 00 54              b.ne     #0xe860
0x0000e848: e4 03 1f 32              mov      w4, #2
0x0000e84c: 06 00 00 14              b        #0xe864                    ; -> Java_com_miui_extraphoto_refocus_manager_MiDualPhotoJni_initBrainRelightingEffect+0xc4
0x0000e850: e4 03 1e 32              mov      w4, #4
0x0000e854: 04 00 00 14              b        #0xe864                    ; -> Java_com_miui_extraphoto_refocus_manager_MiDualPhotoJni_initBrainRelightingEffect+0xc4
0x0000e858: e4 07 00 32              mov      w4, #3
0x0000e85c: 02 00 00 14              b        #0xe864                    ; -> Java_com_miui_extraphoto_refocus_manager_MiDualPhotoJni_initBrainRelightingEffect+0xc4
0x0000e860: e4 03 00 32              mov      w4, #1
0x0000e864: c1 00 00 f0              adrp     x1, #0x29000
0x0000e868: c2 00 00 f0              adrp     x2, #0x29000
0x0000e86c: 21 58 00 91              add      x1, x1, #0x16
0x0000e870: 42 3c 01 91              add      x2, x2, #0x4f
0x0000e874: e0 07 00 32              mov      w0, #3
0x0000e878: e3 03 16 2a              mov      w3, w22
0x0000e87c: 51 fd ff 97              bl       #0xddc0                    ; -> __android_log_print@plt
0x0000e880: d8 02 00 b4              cbz      x24, #0xe8d8
0x0000e884: e0 03 18 aa              mov      x0, x24
0x0000e888: f2 fd ff 97              bl       #0xe050                    ; -> _Z17getImageByPointerl@plt
0x0000e88c: fa 03 00 aa              mov      x26, x0
0x0000e890: e0 03 1a 32              mov      w0, #0x40
0x0000e894: db fc ff 97              bl       #0xdc00                    ; -> _Znwm@plt
0x0000e898: e8 03 00 32              mov      w8, #1
0x0000e89c: 08 00 00 b9              str      w8, [x0]
0x0000e8a0: 48 07 40 b9              ldr      w8, [x26, #4]
0x0000e8a4: f9 03 00 aa              mov      x25, x0
0x0000e8a8: 08 04 00 b9              str      w8, [x0, #4]
0x0000e8ac: 48 0b 40 b9              ldr      w8, [x26, #8]
0x0000e8b0: 08 20 01 29              stp      w8, w8, [x0, #8]
0x0000e8b4: 48 0b 40 f9              ldr      x8, [x26, #0x10]
0x0000e8b8: 08 08 00 f9              str      x8, [x0, #0x10]
0x0000e8bc: 48 0f 40 f9              ldr      x8, [x26, #0x18]
0x0000e8c0: 08 0c 00 f9              str      x8, [x0, #0x18]
0x0000e8c4: 48 33 40 b9              ldr      w8, [x26, #0x30]
0x0000e8c8: 08 30 00 b9              str      w8, [x0, #0x30]
0x0000e8cc: 48 37 40 b9              ldr      w8, [x26, #0x34]
0x0000e8d0: 08 34 00 b9              str      w8, [x0, #0x34]
0x0000e8d4: 02 00 00 14              b        #0xe8dc                    ; -> Java_com_miui_extraphoto_refocus_manager_MiDualPhotoJni_initBrainRelightingEffect+0x13c
0x0000e8d8: f9 03 1f aa              mov      x25, xzr
0x0000e8dc: da 00 00 f0              adrp     x26, #0x29000
0x0000e8e0: 5a 5b 00 91              add      x26, x26, #0x16
0x0000e8e4: c2 00 00 f0              adrp     x2, #0x29000
0x0000e8e8: 42 c8 01 91              add      x2, x2, #0x72
0x0000e8ec: e0 07 00 32              mov      w0, #3
0x0000e8f0: e1 03 1a aa              mov      x1, x26
0x0000e8f4: e3 03 18 aa              mov      x3, x24
0x0000e8f8: 32 fd ff 97              bl       #0xddc0                    ; -> __android_log_print@plt
0x0000e8fc: 22 8f 40 29              ldp      w2, w3, [x25, #4]
0x0000e900: e0 03 00 91              mov      x0, sp
0x0000e904: e1 03 1f 2a              mov      w1, wzr
0x0000e908: ce fd ff 97              bl       #0xe040                    ; -> AILAB_PLI_Init@plt
0x0000e90c: e0 03 40 f9              ldr      x0, [sp]
0x0000e910: e1 03 19 aa              mov      x1, x25
0x0000e914: e2 03 16 2a              mov      w2, w22
0x0000e918: e3 03 17 aa              mov      x3, x23
0x0000e91c: bd fd ff 97              bl       #0xe010                    ; -> AILAB_PLI_PreProcess@plt
0x0000e920: f6 03 00 aa              mov      x22, x0
0x0000e924: c2 00 00 f0              adrp     x2, #0x29000
0x0000e928: 42 48 02 91              add      x2, x2, #0x92
0x0000e92c: e0 07 00 32              mov      w0, #3
0x0000e930: e1 03 1a aa              mov      x1, x26
0x0000e934: e3 03 16 aa              mov      x3, x22
0x0000e938: 22 fd ff 97              bl       #0xddc0                    ; -> __android_log_print@plt
0x0000e93c: e3 12 40 29              ldp      w3, w4, [x23]
0x0000e940: e5 0a 40 b9              ldr      w5, [x23, #8]
0x0000e944: c2 00 00 f0              adrp     x2, #0x29000
0x0000e948: 42 f0 02 91              add      x2, x2, #0xbc
0x0000e94c: e0 07 00 32              mov      w0, #3
0x0000e950: e1 03 1a aa              mov      x1, x26
0x0000e954: 1b fd ff 97              bl       #0xddc0                    ; -> __android_log_print@plt
0x0000e958: b6 02 00 b9              str      w22, [x21]
0x0000e95c: e8 02 40 b9              ldr      w8, [x23]
0x0000e960: e0 03 14 aa              mov      x0, x20
0x0000e964: e1 03 13 aa              mov      x1, x19
0x0000e968: e2 03 15 aa              mov      x2, x21
0x0000e96c: a8 06 00 b9              str      w8, [x21, #4]
0x0000e970: e8 06 40 b9              ldr      w8, [x23, #4]
0x0000e974: e3 03 1f 2a              mov      w3, wzr
0x0000e978: a8 0a 00 b9              str      w8, [x21, #8]
0x0000e97c: e8 0a 40 b9              ldr      w8, [x23, #8]
0x0000e980: a8 0e 00 b9              str      w8, [x21, #0xc]
0x0000e984: 88 02 40 f9              ldr      x8, [x20]
0x0000e988: 08 0d 43 f9              ldr      x8, [x8, #0x618]
0x0000e98c: 00 01 3f d6              blr      x8
0x0000e990: e0 03 40 f9              ldr      x0, [sp]
0x0000e994: 68 17 40 f9              ldr      x8, [x27, #0x28]
0x0000e998: e9 07 40 f9              ldr      x9, [sp, #8]
0x0000e99c: 1f 01 09 eb              cmp      x8, x9
0x0000e9a0: 21 01 00 54              b.ne     #0xe9c4
0x0000e9a4: fd 7b 46 a9              ldp      x29, x30, [sp, #0x60]
0x0000e9a8: f4 4f 45 a9              ldp      x20, x19, [sp, #0x50]
0x0000e9ac: f6 57 44 a9              ldp      x22, x21, [sp, #0x40]
0x0000e9b0: f8 5f 43 a9              ldp      x24, x23, [sp, #0x30]
0x0000e9b4: fa 67 42 a9              ldp      x26, x25, [sp, #0x20]
0x0000e9b8: fb 0b 40 f9              ldr      x27, [sp, #0x10]
0x0000e9bc: ff c3 01 91              add      sp, sp, #0x70
0x0000e9c0: c0 03 5f d6              ret      
0x0000e9c4: db fc ff 97              bl       #0xdd30                    ; -> __stack_chk_fail@plt
