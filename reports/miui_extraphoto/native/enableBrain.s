; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_extraphoto/native/libmibrain_relighting_jni.so
; target  : Java_com_miui_extraphoto_refocus_manager_MiDualPhotoJni_enableBrainRelightingEffect (0xea50)
; start   : 0xea50
; size    : 0x228
; bytes   : 0x228   instructions: 138
; generated: 2026-10-23T22:58:02

0x0000ea50: fc 6f ba a9              stp      x28, x27, [sp, #-0x60]!
0x0000ea54: fa 67 01 a9              stp      x26, x25, [sp, #0x10]
0x0000ea58: f8 5f 02 a9              stp      x24, x23, [sp, #0x20]
0x0000ea5c: f6 57 03 a9              stp      x22, x21, [sp, #0x30]
0x0000ea60: f4 4f 04 a9              stp      x20, x19, [sp, #0x40]
0x0000ea64: fd 7b 05 a9              stp      x29, x30, [sp, #0x50]
0x0000ea68: fd 43 01 91              add      x29, sp, #0x50
0x0000ea6c: 08 00 40 f9              ldr      x8, [x0]
0x0000ea70: b4 0f 40 f9              ldr      x20, [x29, #0x18]
0x0000ea74: f6 03 02 aa              mov      x22, x2
0x0000ea78: e2 03 1f aa              mov      x2, xzr
0x0000ea7c: 08 ed 42 f9              ldr      x8, [x8, #0x5d8]
0x0000ea80: e1 03 14 aa              mov      x1, x20
0x0000ea84: f8 03 07 aa              mov      x24, x7
0x0000ea88: fa 03 06 aa              mov      x26, x6
0x0000ea8c: fb 03 05 aa              mov      x27, x5
0x0000ea90: f9 03 04 aa              mov      x25, x4
0x0000ea94: f7 03 03 2a              mov      w23, w3
0x0000ea98: f3 03 00 aa              mov      x19, x0
0x0000ea9c: 00 01 3f d6              blr      x8
0x0000eaa0: f5 03 00 aa              mov      x21, x0
0x0000eaa4: b9 02 00 b4              cbz      x25, #0xeaf8
0x0000eaa8: e0 03 19 aa              mov      x0, x25
0x0000eaac: 69 fd ff 97              bl       #0xe050                    ; -> _Z17getImageByPointerl@plt
0x0000eab0: fc 03 00 aa              mov      x28, x0
0x0000eab4: e0 03 1a 32              mov      w0, #0x40
0x0000eab8: 52 fc ff 97              bl       #0xdc00                    ; -> _Znwm@plt
0x0000eabc: e8 03 00 32              mov      w8, #1
0x0000eac0: 08 00 00 b9              str      w8, [x0]
0x0000eac4: 88 07 40 b9              ldr      w8, [x28, #4]
0x0000eac8: f9 03 00 aa              mov      x25, x0
0x0000eacc: 08 04 00 b9              str      w8, [x0, #4]
0x0000ead0: 88 0b 40 b9              ldr      w8, [x28, #8]
0x0000ead4: 08 20 01 29              stp      w8, w8, [x0, #8]
0x0000ead8: 88 0b 40 f9              ldr      x8, [x28, #0x10]
0x0000eadc: 08 08 00 f9              str      x8, [x0, #0x10]
0x0000eae0: 88 0f 40 f9              ldr      x8, [x28, #0x18]
0x0000eae4: 08 0c 00 f9              str      x8, [x0, #0x18]
0x0000eae8: 88 33 40 b9              ldr      w8, [x28, #0x30]
0x0000eaec: 08 30 00 b9              str      w8, [x0, #0x30]
0x0000eaf0: 88 37 40 b9              ldr      w8, [x28, #0x34]
0x0000eaf4: 08 34 00 b9              str      w8, [x0, #0x34]
0x0000eaf8: bb 02 00 b4              cbz      x27, #0xeb4c
0x0000eafc: e0 03 1b aa              mov      x0, x27
0x0000eb00: 54 fd ff 97              bl       #0xe050                    ; -> _Z17getImageByPointerl@plt
0x0000eb04: fc 03 00 aa              mov      x28, x0
0x0000eb08: e0 03 1a 32              mov      w0, #0x40
0x0000eb0c: 3d fc ff 97              bl       #0xdc00                    ; -> _Znwm@plt
0x0000eb10: e8 03 00 32              mov      w8, #1
0x0000eb14: 08 00 00 b9              str      w8, [x0]
0x0000eb18: 88 07 40 b9              ldr      w8, [x28, #4]
0x0000eb1c: fb 03 00 aa              mov      x27, x0
0x0000eb20: 08 04 00 b9              str      w8, [x0, #4]
0x0000eb24: 88 0b 40 b9              ldr      w8, [x28, #8]
0x0000eb28: 08 20 01 29              stp      w8, w8, [x0, #8]
0x0000eb2c: 88 0b 40 f9              ldr      x8, [x28, #0x10]
0x0000eb30: 08 08 00 f9              str      x8, [x0, #0x10]
0x0000eb34: 88 0f 40 f9              ldr      x8, [x28, #0x18]
0x0000eb38: 08 0c 00 f9              str      x8, [x0, #0x18]
0x0000eb3c: 88 33 40 b9              ldr      w8, [x28, #0x30]
0x0000eb40: 08 30 00 b9              str      w8, [x0, #0x30]
0x0000eb44: 88 37 40 b9              ldr      w8, [x28, #0x34]
0x0000eb48: 08 34 00 b9              str      w8, [x0, #0x34]
0x0000eb4c: ba 02 00 b4              cbz      x26, #0xeba0
0x0000eb50: e0 03 1a aa              mov      x0, x26
0x0000eb54: 3f fd ff 97              bl       #0xe050                    ; -> _Z17getImageByPointerl@plt
0x0000eb58: fc 03 00 aa              mov      x28, x0
0x0000eb5c: e0 03 1a 32              mov      w0, #0x40
0x0000eb60: 28 fc ff 97              bl       #0xdc00                    ; -> _Znwm@plt
0x0000eb64: e8 03 00 32              mov      w8, #1
0x0000eb68: 08 00 00 b9              str      w8, [x0]
0x0000eb6c: 88 07 40 b9              ldr      w8, [x28, #4]
0x0000eb70: fa 03 00 aa              mov      x26, x0
0x0000eb74: 08 04 00 b9              str      w8, [x0, #4]
0x0000eb78: 88 0b 40 b9              ldr      w8, [x28, #8]
0x0000eb7c: 08 20 01 29              stp      w8, w8, [x0, #8]
0x0000eb80: 88 0b 40 f9              ldr      x8, [x28, #0x10]
0x0000eb84: 08 08 00 f9              str      x8, [x0, #0x10]
0x0000eb88: 88 0f 40 f9              ldr      x8, [x28, #0x18]
0x0000eb8c: 08 0c 00 f9              str      x8, [x0, #0x18]
0x0000eb90: 88 33 40 b9              ldr      w8, [x28, #0x30]
0x0000eb94: 08 30 00 b9              str      w8, [x0, #0x30]
0x0000eb98: 88 37 40 b9              ldr      w8, [x28, #0x34]
0x0000eb9c: 08 34 00 b9              str      w8, [x0, #0x34]
0x0000eba0: e0 07 1d 32              mov      w0, #0x18
0x0000eba4: 17 fc ff 97              bl       #0xdc00                    ; -> _Znwm@plt
0x0000eba8: 48 43 40 f8              ldur     x8, [x26, #4]
0x0000ebac: fc 03 00 aa              mov      x28, x0
0x0000ebb0: 19 08 00 f9              str      x25, [x0, #0x10]
0x0000ebb4: 08 60 00 a9              stp      x8, x24, [x0]
0x0000ebb8: e0 03 1e 32              mov      w0, #4
0x0000ebbc: 11 fc ff 97              bl       #0xdc00                    ; -> _Znwm@plt
0x0000ebc0: d9 00 00 f0              adrp     x25, #0x29000
0x0000ebc4: 39 5b 00 91              add      x25, x25, #0x16
0x0000ebc8: c2 00 00 f0              adrp     x2, #0x29000
0x0000ebcc: f8 03 00 aa              mov      x24, x0
0x0000ebd0: 17 00 00 b9              str      w23, [x0]
0x0000ebd4: 42 64 04 91              add      x2, x2, #0x119
0x0000ebd8: e0 07 00 32              mov      w0, #3
0x0000ebdc: e1 03 19 aa              mov      x1, x25
0x0000ebe0: e3 03 17 2a              mov      w3, w23
0x0000ebe4: 77 fc ff 97              bl       #0xddc0                    ; -> __android_log_print@plt
0x0000ebe8: e0 03 16 aa              mov      x0, x22
0x0000ebec: e1 03 1c aa              mov      x1, x28
0x0000ebf0: e2 03 1a aa              mov      x2, x26
0x0000ebf4: e3 03 18 aa              mov      x3, x24
0x0000ebf8: e4 03 1b aa              mov      x4, x27
0x0000ebfc: 1d fc ff 97              bl       #0xdc70                    ; -> AILAB_PLI_Process@plt
0x0000ec00: f7 03 00 aa              mov      x23, x0
0x0000ec04: e0 03 1c 32              mov      w0, #0x10
0x0000ec08: fe fb ff 97              bl       #0xdc00                    ; -> _Znwm@plt
0x0000ec0c: f8 03 00 aa              mov      x24, x0
0x0000ec10: e0 03 16 aa              mov      x0, x22
0x0000ec14: e1 03 18 aa              mov      x1, x24
0x0000ec18: ee fc ff 97              bl       #0xdfd0                    ; -> AILAB_PLI_GetLightSource@plt
0x0000ec1c: 04 0b 40 b9              ldr      w4, [x24, #8]
0x0000ec20: c2 00 00 f0              adrp     x2, #0x29000
0x0000ec24: 42 c8 04 91              add      x2, x2, #0x132
0x0000ec28: e0 07 00 32              mov      w0, #3
0x0000ec2c: a4 02 00 b9              str      w4, [x21]
0x0000ec30: 05 0f 40 b9              ldr      w5, [x24, #0xc]
0x0000ec34: e1 03 19 aa              mov      x1, x25
0x0000ec38: e3 03 17 2a              mov      w3, w23
0x0000ec3c: a5 06 00 b9              str      w5, [x21, #4]
0x0000ec40: 60 fc ff 97              bl       #0xddc0                    ; -> __android_log_print@plt
0x0000ec44: 68 02 40 f9              ldr      x8, [x19]
0x0000ec48: e0 03 13 aa              mov      x0, x19
0x0000ec4c: e1 03 14 aa              mov      x1, x20
0x0000ec50: e2 03 15 aa              mov      x2, x21
0x0000ec54: 04 0d 43 f9              ldr      x4, [x8, #0x618]
0x0000ec58: fd 7b 45 a9              ldp      x29, x30, [sp, #0x50]
0x0000ec5c: f4 4f 44 a9              ldp      x20, x19, [sp, #0x40]
0x0000ec60: f6 57 43 a9              ldp      x22, x21, [sp, #0x30]
0x0000ec64: f8 5f 42 a9              ldp      x24, x23, [sp, #0x20]
0x0000ec68: fa 67 41 a9              ldp      x26, x25, [sp, #0x10]
0x0000ec6c: e3 03 1f 2a              mov      w3, wzr
0x0000ec70: fc 6f c6 a8              ldp      x28, x27, [sp], #0x60
0x0000ec74: 80 00 1f d6              br       x4
