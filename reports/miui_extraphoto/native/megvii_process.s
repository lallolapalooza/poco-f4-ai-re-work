; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_extraphoto/native/libmegvii_bokeh_jni.so
; target  : Java_com_miui_extraphoto_refocus_manager_MegviiDualPhotoJni_processRefocusWidthHandle (0x8a8c)
; start   : 0x8a8c
; size    : 0x1b0
; bytes   : 0x1b0   instructions: 108
; generated: 2026-10-23T23:06:03

0x00008a8c: ff 43 02 d1              sub      sp, sp, #0x90
0x00008a90: e8 23 00 fd              str      d8, [sp, #0x40]
0x00008a94: f9 27 00 f9              str      x25, [sp, #0x48]
0x00008a98: f8 5f 05 a9              stp      x24, x23, [sp, #0x50]
0x00008a9c: f6 57 06 a9              stp      x22, x21, [sp, #0x60]
0x00008aa0: f4 4f 07 a9              stp      x20, x19, [sp, #0x70]
0x00008aa4: fd 7b 08 a9              stp      x29, x30, [sp, #0x80]
0x00008aa8: fd 03 02 91              add      x29, sp, #0x80
0x00008aac: 59 d0 3b d5              mrs      x25, tpidr_el0
0x00008ab0: b5 13 40 b9              ldr      w21, [x29, #0x10]
0x00008ab4: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x00008ab8: 08 1c a0 4e              mov      v8.16b, v0.16b
0x00008abc: f3 03 07 2a              mov      w19, w7
0x00008ac0: f4 03 02 aa              mov      x20, x2
0x00008ac4: 61 01 00 b0              adrp     x1, #0x35000
0x00008ac8: 62 01 00 b0              adrp     x2, #0x35000
0x00008acc: f7 03 04 aa              mov      x23, x4
0x00008ad0: f8 03 03 aa              mov      x24, x3
0x00008ad4: 00 c1 22 1e              fcvt     d0, s8
0x00008ad8: 21 98 30 91              add      x1, x1, #0xc26
0x00008adc: 42 3c 31 91              add      x2, x2, #0xc4f
0x00008ae0: e0 07 00 32              mov      w0, #3
0x00008ae4: e3 03 13 2a              mov      w3, w19
0x00008ae8: e4 03 15 2a              mov      w4, w21
0x00008aec: f6 03 05 aa              mov      x22, x5
0x00008af0: e8 1f 00 f9              str      x8, [sp, #0x38]
0x00008af4: 7b fd ff 97              bl       #0x80e0                    ; -> __android_log_print@plt
0x00008af8: e1 8a 40 29              ldp      w1, w2, [x23, #4]
0x00008afc: e3 0a 40 f9              ldr      x3, [x23, #0x10]
0x00008b00: e0 a3 00 91              add      x0, sp, #0x28
0x00008b04: e4 07 1f 32              mov      w4, #6
0x00008b08: e5 03 1f 2a              mov      w5, wzr
0x00008b0c: e6 03 1f 2a              mov      w6, wzr
0x00008b10: 58 fd ff 97              bl       #0x8070                    ; -> _ZN2wa5ImageC1EiiPvNS0_9ImageTypeENS0_8DataTypeENS0_8RotationE@plt
0x00008b14: 01 8b 40 29              ldp      w1, w2, [x24, #4]
0x00008b18: 03 0b 40 f9              ldr      x3, [x24, #0x10]
0x00008b1c: e0 63 00 91              add      x0, sp, #0x18
0x00008b20: e4 07 1f 32              mov      w4, #6
0x00008b24: e5 03 1f 2a              mov      w5, wzr
0x00008b28: e6 03 1f 2a              mov      w6, wzr
0x00008b2c: 51 fd ff 97              bl       #0x8070                    ; -> _ZN2wa5ImageC1EiiPvNS0_9ImageTypeENS0_8DataTypeENS0_8RotationE@plt
0x00008b30: a8 1b 40 b9              ldr      w8, [x29, #0x18]
0x00008b34: a2 23 40 b9              ldr      w2, [x29, #0x20]
0x00008b38: 1f 01 00 71              cmp      w8, #0
0x00008b3c: 4c 00 00 54              b.gt     #0x8b44
0x00008b40: e8 06 40 b9              ldr      w8, [x23, #4]
0x00008b44: a9 2b 40 b9              ldr      w9, [x29, #0x28]
0x00008b48: 5f 00 00 71              cmp      w2, #0
0x00008b4c: 4c 00 00 54              b.gt     #0x8b54
0x00008b50: e2 0a 40 b9              ldr      w2, [x23, #8]
0x00008b54: 3f 05 00 71              cmp      w9, #1
0x00008b58: e5 07 9f 1a              cset     w5, ne
0x00008b5c: e0 23 00 91              add      x0, sp, #8
0x00008b60: e1 03 08 2a              mov      w1, w8
0x00008b64: e3 03 16 aa              mov      x3, x22
0x00008b68: e4 03 1f 2a              mov      w4, wzr
0x00008b6c: e6 03 1f 2a              mov      w6, wzr
0x00008b70: 40 fd ff 97              bl       #0x8070                    ; -> _ZN2wa5ImageC1EiiPvNS0_9ImageTypeENS0_8DataTypeENS0_8RotationE@plt
0x00008b74: e1 a3 00 91              add      x1, sp, #0x28
0x00008b78: e2 23 00 91              add      x2, sp, #8
0x00008b7c: e0 03 14 aa              mov      x0, x20
0x00008b80: f4 fd ff 97              bl       #0x8350                    ; -> _ZN2wa7Refocus9setImagesERKNS_5ImageES3_@plt
0x00008b84: e3 63 00 91              add      x3, sp, #0x18
0x00008b88: e0 03 14 aa              mov      x0, x20
0x00008b8c: e1 03 13 2a              mov      w1, w19
0x00008b90: e2 03 15 2a              mov      w2, w21
0x00008b94: 00 1d a8 4e              mov      v0.16b, v8.16b
0x00008b98: 32 fd ff 97              bl       #0x8060                    ; -> _ZN2wa7Refocus3runEiifRNS_5ImageE@plt
0x00008b9c: e3 03 00 2a              mov      w3, w0
0x00008ba0: 61 01 00 b0              adrp     x1, #0x35000
0x00008ba4: 62 01 00 b0              adrp     x2, #0x35000
0x00008ba8: 21 98 30 91              add      x1, x1, #0xc26
0x00008bac: 42 d8 31 91              add      x2, x2, #0xc76
0x00008bb0: e0 07 00 32              mov      w0, #3
0x00008bb4: 4b fd ff 97              bl       #0x80e0                    ; -> __android_log_print@plt
0x00008bb8: e0 23 00 91              add      x0, sp, #8
0x00008bbc: 05 fe ff 97              bl       #0x83d0                    ; -> _ZN2wa5ImageD1Ev@plt
0x00008bc0: e0 63 00 91              add      x0, sp, #0x18
0x00008bc4: 03 fe ff 97              bl       #0x83d0                    ; -> _ZN2wa5ImageD1Ev@plt
0x00008bc8: e0 a3 00 91              add      x0, sp, #0x28
0x00008bcc: 01 fe ff 97              bl       #0x83d0                    ; -> _ZN2wa5ImageD1Ev@plt
0x00008bd0: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x00008bd4: e9 1f 40 f9              ldr      x9, [sp, #0x38]
0x00008bd8: 1f 01 09 eb              cmp      x8, x9
0x00008bdc: 21 01 00 54              b.ne     #0x8c00
0x00008be0: fd 7b 48 a9              ldp      x29, x30, [sp, #0x80]
0x00008be4: f4 4f 47 a9              ldp      x20, x19, [sp, #0x70]
0x00008be8: f6 57 46 a9              ldp      x22, x21, [sp, #0x60]
0x00008bec: f8 5f 45 a9              ldp      x24, x23, [sp, #0x50]
0x00008bf0: f9 27 40 f9              ldr      x25, [sp, #0x48]
0x00008bf4: e8 23 40 fd              ldr      d8, [sp, #0x40]
0x00008bf8: ff 43 02 91              add      sp, sp, #0x90
0x00008bfc: c0 03 5f d6              ret      
0x00008c00: 14 fd ff 97              bl       #0x8050                    ; -> __stack_chk_fail@plt
0x00008c04: 05 00 00 14              b        #0x8c18                    ; -> Java_com_miui_extraphoto_refocus_manager_MegviiDualPhotoJni_processRefocusWidthHandle+0x18c
0x00008c08: f3 03 00 aa              mov      x19, x0
0x00008c0c: 06 00 00 14              b        #0x8c24                    ; -> Java_com_miui_extraphoto_refocus_manager_MegviiDualPhotoJni_processRefocusWidthHandle+0x198
0x00008c10: f3 03 00 aa              mov      x19, x0
0x00008c14: 06 00 00 14              b        #0x8c2c                    ; -> Java_com_miui_extraphoto_refocus_manager_MegviiDualPhotoJni_processRefocusWidthHandle+0x1a0
0x00008c18: f3 03 00 aa              mov      x19, x0
0x00008c1c: e0 23 00 91              add      x0, sp, #8
0x00008c20: ec fd ff 97              bl       #0x83d0                    ; -> _ZN2wa5ImageD1Ev@plt
0x00008c24: e0 63 00 91              add      x0, sp, #0x18
0x00008c28: ea fd ff 97              bl       #0x83d0                    ; -> _ZN2wa5ImageD1Ev@plt
0x00008c2c: e0 a3 00 91              add      x0, sp, #0x28
0x00008c30: e8 fd ff 97              bl       #0x83d0                    ; -> _ZN2wa5ImageD1Ev@plt
0x00008c34: e0 03 13 aa              mov      x0, x19
0x00008c38: b6 fd ff 97              bl       #0x8310                    ; -> _Unwind_Resume@plt
