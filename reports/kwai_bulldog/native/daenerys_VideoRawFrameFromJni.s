; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/kwai_bulldog/native/libs_extra/libdaenerys.so
; target  : _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject (0xece3c)
; start   : 0xece3c
; size    : 0x17a0
; bytes   : 0x17a0   instructions: 1512
; generated: 2026-10-24T00:56:24

0x000ece3c: fc 6f ba a9              stp      x28, x27, [sp, #-0x60]!
0x000ece40: fa 67 01 a9              stp      x26, x25, [sp, #0x10]
0x000ece44: f8 5f 02 a9              stp      x24, x23, [sp, #0x20]
0x000ece48: f6 57 03 a9              stp      x22, x21, [sp, #0x30]
0x000ece4c: f4 4f 04 a9              stp      x20, x19, [sp, #0x40]
0x000ece50: fd 7b 05 a9              stp      x29, x30, [sp, #0x50]
0x000ece54: fd 43 01 91              add      x29, sp, #0x50
0x000ece58: ff 03 09 d1              sub      sp, sp, #0x240
0x000ece5c: 48 d0 3b d5              mrs      x8, tpidr_el0
0x000ece60: e8 0b 00 f9              str      x8, [sp, #0x10]
0x000ece64: 08 15 40 f9              ldr      x8, [x8, #0x28]
0x000ece68: f4 03 00 aa              mov      x20, x0
0x000ece6c: e0 07 1b 32              mov      w0, #0x60
0x000ece70: f5 03 01 aa              mov      x21, x1
0x000ece74: e8 13 00 f9              str      x8, [sp, #0x20]
0x000ece78: b2 72 fe 97              bl       #0x89940                   ; -> _Znwm@plt
0x000ece7c: f3 03 00 aa              mov      x19, x0
0x000ece80: a3 07 00 90              adrp     x3, #0x1e0000
0x000ece84: 63 80 1c 91              add      x3, x3, #0x720
0x000ece88: e1 03 14 aa              mov      x1, x20
0x000ece8c: e2 03 15 aa              mov      x2, x21
0x000ece90: f4 0f 00 f9              str      x20, [sp, #0x18]
0x000ece94: 77 76 fe 97              bl       #0x8a870                   ; -> _ZN8kuaishou5dbase12AndroidClassC2EP7_JNIEnvP8_jobjectPKc@plt
0x000ece98: 08 0c 00 f0              adrp     x8, #0x26f000
0x000ece9c: 21 08 00 f0              adrp     x1, #0x1f3000
0x000ecea0: 08 21 10 91              add      x8, x8, #0x408
0x000ecea4: 21 ec 09 91              add      x1, x1, #0x27b
0x000ecea8: e0 03 13 aa              mov      x0, x19
0x000eceac: f4 a3 02 91              add      x20, sp, #0xa8
0x000eceb0: 68 02 00 f9              str      x8, [x19]
0x000eceb4: 8f 71 fe 97              bl       #0x894f0                   ; -> _ZN8kuaishou5dbase12AndroidClass3GetIiEET_PKc@plt
0x000eceb8: a1 07 00 90              adrp     x1, #0x1e0000
0x000ecebc: f5 03 00 2a              mov      w21, w0
0x000ecec0: 21 a0 22 91              add      x1, x1, #0x8a8
0x000ecec4: e0 03 13 aa              mov      x0, x19
0x000ecec8: 8a 71 fe 97              bl       #0x894f0                   ; -> _ZN8kuaishou5dbase12AndroidClass3GetIiEET_PKc@plt
0x000ececc: bf 06 00 71              cmp      w21, #1
0x000eced0: a0 0d 00 54              b.eq     #0xed084
0x000eced4: bf 0e 00 71              cmp      w21, #3
0x000eced8: 20 0d 00 54              b.eq     #0xed07c
0x000ecedc: bf 12 00 71              cmp      w21, #4
0x000ecee0: 01 0e 00 54              b.ne     #0xed0a0
0x000ecee4: a1 07 00 90              adrp     x1, #0x1e0000
0x000ecee8: a2 07 00 b0              adrp     x2, #0x1e1000
0x000eceec: 21 74 24 91              add      x1, x1, #0x91d
0x000ecef0: 42 d4 38 91              add      x2, x2, #0xe35
0x000ecef4: e0 03 13 aa              mov      x0, x19
0x000ecef8: 22 73 fe 97              bl       #0x89b80                   ; -> _ZN8kuaishou5dbase12AndroidClass3GetEPKcS3_@plt
0x000ecefc: f6 03 00 aa              mov      x22, x0
0x000ecf00: e0 07 1d 32              mov      w0, #0x18
0x000ecf04: 8f 72 fe 97              bl       #0x89940                   ; -> _Znwm@plt
0x000ecf08: f5 03 00 aa              mov      x21, x0
0x000ecf0c: e1 0f 40 f9              ldr      x1, [sp, #0x18]
0x000ecf10: e2 03 16 aa              mov      x2, x22
0x000ecf14: e5 05 00 94              bl       #0xee6a8
0x000ecf18: 21 08 00 f0              adrp     x1, #0x1f3000
0x000ecf1c: 21 00 10 91              add      x1, x1, #0x400
0x000ecf20: e0 03 13 aa              mov      x0, x19
0x000ecf24: 73 71 fe 97              bl       #0x894f0                   ; -> _ZN8kuaishou5dbase12AndroidClass3GetIiEET_PKc@plt
0x000ecf28: 21 08 00 f0              adrp     x1, #0x1f3000
0x000ecf2c: f6 03 00 2a              mov      w22, w0
0x000ecf30: 21 38 10 91              add      x1, x1, #0x40e
0x000ecf34: e0 03 13 aa              mov      x0, x19
0x000ecf38: 6e 71 fe 97              bl       #0x894f0                   ; -> _ZN8kuaishou5dbase12AndroidClass3GetIiEET_PKc@plt
0x000ecf3c: fc 0b 00 f0              adrp     x28, #0x26b000
0x000ecf40: f7 03 00 2a              mov      w23, w0
0x000ecf44: 9c 03 2c 91              add      x28, x28, #0xb00
0x000ecf48: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x000ecf4c: 00 05 80 52              mov      w0, #0x28
0x000ecf50: fc 57 00 f9              str      x28, [sp, #0xa8]
0x000ecf54: 80 82 81 3c              stur     q0, [x20, #0x18]
0x000ecf58: 80 82 80 3c              stur     q0, [x20, #8]
0x000ecf5c: 79 72 fe 97              bl       #0x89940                   ; -> _Znwm@plt
0x000ecf60: a8 02 40 f9              ldr      x8, [x21]
0x000ecf64: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x000ecf68: 1f 10 00 f9              str      xzr, [x0, #0x20]
0x000ecf6c: 00 00 00 ad              stp      q0, q0, [x0]
0x000ecf70: e0 6b 00 f9              str      x0, [sp, #0xd0]
0x000ecf74: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x000ecf78: e0 03 15 aa              mov      x0, x21
0x000ecf7c: 00 01 3f d6              blr      x8
0x000ecf80: f8 03 00 aa              mov      x24, x0
0x000ecf84: e0 4f 00 f9              str      x0, [sp, #0x98]
0x000ecf88: 00 05 80 52              mov      w0, #0x28
0x000ecf8c: 6d 72 fe 97              bl       #0x89940                   ; -> _Znwm@plt
0x000ecf90: 08 0c 00 f0              adrp     x8, #0x26f000
0x000ecf94: 08 61 18 91              add      x8, x8, #0x618
0x000ecf98: c9 7e 17 1b              mul      w9, w22, w23
0x000ecf9c: 08 7c 00 a9              stp      x8, xzr, [x0]
0x000ecfa0: 28 75 1e 53              lsl      w8, w9, #2
0x000ecfa4: 1f 60 01 a9              stp      xzr, x24, [x0, #0x10]
0x000ecfa8: 15 10 00 f9              str      x21, [x0, #0x20]
0x000ecfac: e0 53 00 f9              str      x0, [sp, #0xa0]
0x000ecfb0: 02 7d 40 93              sxtw     x2, w8
0x000ecfb4: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ecfb8: e1 63 02 91              add      x1, sp, #0x98
0x000ecfbc: 17 06 00 94              bl       #0xee818
0x000ecfc0: b5 03 53 f8              ldur     x21, [x29, #-0xd0]
0x000ecfc4: e0 03 15 aa              mov      x0, x21
0x000ecfc8: d2 74 fe 97              bl       #0x8a310                   ; -> _ZNSt6__ndk15mutex4lockEv@plt
0x000ecfcc: a1 0b 71 a9              ldp      x1, x2, [x29, #-0xf0]
0x000ecfd0: e8 a3 02 91              add      x8, sp, #0xa8
0x000ecfd4: 00 21 00 91              add      x0, x8, #8
0x000ecfd8: 89 3a ff 97              bl       #0xbb9fc
0x000ecfdc: a8 e3 03 d1              sub      x8, x29, #0xf8
0x000ecfe0: 14 81 00 91              add      x20, x8, #0x20
0x000ecfe4: 88 fe df c8              ldar     x8, [x20]
0x000ecfe8: e9 a3 02 91              add      x9, sp, #0xa8
0x000ecfec: 29 81 00 91              add      x9, x9, #0x20
0x000ecff0: 28 fd 9f c8              stlr     x8, [x9]
0x000ecff4: b9 63 71 a9              ldp      x25, x24, [x29, #-0xf0]
0x000ecff8: 1f 03 19 eb              cmp      x24, x25
0x000ecffc: e0 00 00 54              b.eq     #0xed018
0x000ed000: 08 8f 5d f8              ldr      x8, [x24, #-0x28]!
0x000ed004: 08 01 40 f9              ldr      x8, [x8]
0x000ed008: e0 03 18 aa              mov      x0, x24
0x000ed00c: 00 01 3f d6              blr      x8
0x000ed010: 3f 03 18 eb              cmp      x25, x24
0x000ed014: 61 ff ff 54              b.ne     #0xed000
0x000ed018: e0 03 15 aa              mov      x0, x21
0x000ed01c: b9 83 11 f8              stur     x25, [x29, #-0xe8]
0x000ed020: 9f fe 9f c8              stlr     xzr, [x20]
0x000ed024: 5f 75 fe 97              bl       #0x8a5a0                   ; -> _ZNSt6__ndk15mutex6unlockEv@plt
0x000ed028: b5 03 53 f8              ldur     x21, [x29, #-0xd0]
0x000ed02c: bc 83 10 f8              stur     x28, [x29, #-0xf8]
0x000ed030: bf 03 13 f8              stur     xzr, [x29, #-0xd0]
0x000ed034: b5 00 00 b4              cbz      x21, #0xed048
0x000ed038: e0 03 15 aa              mov      x0, x21
0x000ed03c: 51 71 fe 97              bl       #0x89580                   ; -> _ZNSt6__ndk15mutexD1Ev@plt
0x000ed040: e0 03 15 aa              mov      x0, x21
0x000ed044: fb 72 fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000ed048: b4 03 51 f8              ldur     x20, [x29, #-0xf0]
0x000ed04c: 34 39 00 b4              cbz      x20, #0xed770
0x000ed050: b5 83 51 f8              ldur     x21, [x29, #-0xe8]
0x000ed054: bf 02 14 eb              cmp      x21, x20
0x000ed058: 60 38 00 54              b.eq     #0xed764
0x000ed05c: a8 8e 5d f8              ldr      x8, [x21, #-0x28]!
0x000ed060: 08 01 40 f9              ldr      x8, [x8]
0x000ed064: e0 03 15 aa              mov      x0, x21
0x000ed068: 00 01 3f d6              blr      x8
0x000ed06c: 9f 02 15 eb              cmp      x20, x21
0x000ed070: 61 ff ff 54              b.ne     #0xed05c
0x000ed074: a0 03 51 f8              ldur     x0, [x29, #-0xf0]
0x000ed078: bc 01 00 14              b        #0xed768                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x92c
0x000ed07c: d6 04 80 52              mov      w22, #0x26
0x000ed080: e8 00 00 14              b        #0xed420                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x5e4
0x000ed084: 08 04 00 51              sub      w8, w0, #1
0x000ed088: 1f 0d 00 71              cmp      w8, #3
0x000ed08c: 82 1c 00 54              b.hs     #0xed41c
0x000ed090: 89 07 00 f0              adrp     x9, #0x1e0000
0x000ed094: 29 d1 3d 91              add      x9, x9, #0xf74
0x000ed098: 36 d9 68 b8              ldr      w22, [x9, w8, sxtw #2]
0x000ed09c: e1 00 00 14              b        #0xed420                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x5e4
0x000ed0a0: 81 07 00 f0              adrp     x1, #0x1e0000
0x000ed0a4: 82 07 00 f0              adrp     x2, #0x1e0000
0x000ed0a8: 21 cc 22 91              add      x1, x1, #0x8b3
0x000ed0ac: 42 28 23 91              add      x2, x2, #0x8ca
0x000ed0b0: e0 03 13 aa              mov      x0, x19
0x000ed0b4: b3 72 fe 97              bl       #0x89b80                   ; -> _ZN8kuaishou5dbase12AndroidClass3GetEPKcS3_@plt
0x000ed0b8: f5 03 00 aa              mov      x21, x0
0x000ed0bc: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ed0c0: f0 71 fe 97              bl       #0x89880                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededC1Ev@plt
0x000ed0c4: a0 03 51 f8              ldur     x0, [x29, #-0xf0]
0x000ed0c8: 08 00 40 f9              ldr      x8, [x0]
0x000ed0cc: 08 55 40 f9              ldr      x8, [x8, #0xa8]
0x000ed0d0: e1 03 15 aa              mov      x1, x21
0x000ed0d4: 00 01 3f d6              blr      x8
0x000ed0d8: f4 03 00 aa              mov      x20, x0
0x000ed0dc: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ed0e0: cc 74 fe 97              bl       #0x8a410                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000ed0e4: e0 07 1c 32              mov      w0, #0x30
0x000ed0e8: f4 07 00 f9              str      x20, [sp, #8]
0x000ed0ec: 15 72 fe 97              bl       #0x89940                   ; -> _Znwm@plt
0x000ed0f0: f8 03 00 aa              mov      x24, x0
0x000ed0f4: 81 07 00 f0              adrp     x1, #0x1e0000
0x000ed0f8: 21 7c 23 91              add      x1, x1, #0x8df
0x000ed0fc: e0 03 13 aa              mov      x0, x19
0x000ed100: fc 70 fe 97              bl       #0x894f0                   ; -> _ZN8kuaishou5dbase12AndroidClass3GetIiEET_PKc@plt
0x000ed104: f5 03 00 2a              mov      w21, w0
0x000ed108: 81 07 00 f0              adrp     x1, #0x1e0000
0x000ed10c: 21 a4 23 91              add      x1, x1, #0x8e9
0x000ed110: e0 03 13 aa              mov      x0, x19
0x000ed114: 3b 7a fe 97              bl       #0x8ba00                   ; -> _ZN8kuaishou5dbase12AndroidClass3GetIhEET_PKc@plt
0x000ed118: 1f 1c 00 72              tst      w0, #0xff
0x000ed11c: e2 07 9f 1a              cset     w2, ne
0x000ed120: e0 03 18 aa              mov      x0, x24
0x000ed124: e1 03 15 2a              mov      w1, w21
0x000ed128: be 72 fe 97              bl       #0x89c20                   ; -> _ZN8kuaishou6render2gl7TextureC2Ejb@plt
0x000ed12c: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ed130: d4 71 fe 97              bl       #0x89880                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededC1Ev@plt
0x000ed134: a0 03 51 f8              ldur     x0, [x29, #-0xf0]
0x000ed138: 08 00 40 f9              ldr      x8, [x0]
0x000ed13c: 08 55 40 f9              ldr      x8, [x8, #0xa8]
0x000ed140: e1 03 14 aa              mov      x1, x20
0x000ed144: 00 01 3f d6              blr      x8
0x000ed148: f5 03 00 aa              mov      x21, x0
0x000ed14c: e0 77 00 f9              str      x0, [sp, #0xe8]
0x000ed150: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ed154: af 74 fe 97              bl       #0x8a410                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000ed158: 00 05 80 52              mov      w0, #0x28
0x000ed15c: f9 71 fe 97              bl       #0x89940                   ; -> _Znwm@plt
0x000ed160: f4 03 00 aa              mov      x20, x0
0x000ed164: e0 a3 02 91              add      x0, sp, #0xa8
0x000ed168: c6 71 fe 97              bl       #0x89880                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededC1Ev@plt
0x000ed16c: e0 5b 40 f9              ldr      x0, [sp, #0xb0]
0x000ed170: 08 00 40 f9              ldr      x8, [x0]
0x000ed174: 08 55 40 f9              ldr      x8, [x8, #0xa8]
0x000ed178: e1 03 15 aa              mov      x1, x21
0x000ed17c: 00 01 3f d6              blr      x8
0x000ed180: f9 03 00 aa              mov      x25, x0
0x000ed184: e0 a3 02 91              add      x0, sp, #0xa8
0x000ed188: a2 74 fe 97              bl       #0x8a410                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000ed18c: f7 03 14 aa              mov      x23, x20
0x000ed190: 08 0c 00 d0              adrp     x8, #0x26f000
0x000ed194: 08 21 15 91              add      x8, x8, #0x548
0x000ed198: ff 8e 00 f8              str      xzr, [x23, #8]!
0x000ed19c: 9f 0a 00 f9              str      xzr, [x20, #0x10]
0x000ed1a0: 88 02 00 f9              str      x8, [x20]
0x000ed1a4: e0 a3 02 91              add      x0, sp, #0xa8
0x000ed1a8: b6 71 fe 97              bl       #0x89880                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededC1Ev@plt
0x000ed1ac: e0 5b 40 f9              ldr      x0, [sp, #0xb0]
0x000ed1b0: 08 00 40 f9              ldr      x8, [x0]
0x000ed1b4: 08 55 40 f9              ldr      x8, [x8, #0xa8]
0x000ed1b8: e1 03 19 aa              mov      x1, x25
0x000ed1bc: 00 01 3f d6              blr      x8
0x000ed1c0: fa 03 00 aa              mov      x26, x0
0x000ed1c4: e0 a3 02 91              add      x0, sp, #0xa8
0x000ed1c8: 92 74 fe 97              bl       #0x8a410                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000ed1cc: 98 0e 00 f9              str      x24, [x20, #0x18]
0x000ed1d0: e0 a3 02 91              add      x0, sp, #0xa8
0x000ed1d4: ab 71 fe 97              bl       #0x89880                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededC1Ev@plt
0x000ed1d8: e0 5b 40 f9              ldr      x0, [sp, #0xb0]
0x000ed1dc: 08 00 40 f9              ldr      x8, [x0]
0x000ed1e0: 08 55 40 f9              ldr      x8, [x8, #0xa8]
0x000ed1e4: e1 03 1a aa              mov      x1, x26
0x000ed1e8: 00 01 3f d6              blr      x8
0x000ed1ec: 80 12 00 f9              str      x0, [x20, #0x20]
0x000ed1f0: e0 a3 02 91              add      x0, sp, #0xa8
0x000ed1f4: 87 74 fe 97              bl       #0x8a410                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000ed1f8: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ed1fc: a1 71 fe 97              bl       #0x89880                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededC1Ev@plt
0x000ed200: a0 03 51 f8              ldur     x0, [x29, #-0xf0]
0x000ed204: 08 00 40 f9              ldr      x8, [x0]
0x000ed208: 08 59 40 f9              ldr      x8, [x8, #0xb0]            ; -> _ZTVN10__cxxabiv121__vmi_class_type_infoE@got
0x000ed20c: e1 03 1a aa              mov      x1, x26
0x000ed210: 00 01 3f d6              blr      x8
0x000ed214: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ed218: 7e 74 fe 97              bl       #0x8a410                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000ed21c: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ed220: 98 71 fe 97              bl       #0x89880                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededC1Ev@plt
0x000ed224: a0 03 51 f8              ldur     x0, [x29, #-0xf0]
0x000ed228: 08 00 40 f9              ldr      x8, [x0]
0x000ed22c: 08 59 40 f9              ldr      x8, [x8, #0xb0]            ; -> _ZTVN10__cxxabiv121__vmi_class_type_infoE@got
0x000ed230: e1 03 19 aa              mov      x1, x25
0x000ed234: 00 01 3f d6              blr      x8
0x000ed238: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ed23c: 75 74 fe 97              bl       #0x8a410                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000ed240: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ed244: 8f 71 fe 97              bl       #0x89880                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededC1Ev@plt
0x000ed248: a0 03 51 f8              ldur     x0, [x29, #-0xf0]
0x000ed24c: 08 00 40 f9              ldr      x8, [x0]
0x000ed250: 08 59 40 f9              ldr      x8, [x8, #0xb0]            ; -> _ZTVN10__cxxabiv121__vmi_class_type_infoE@got
0x000ed254: e1 03 15 aa              mov      x1, x21
0x000ed258: 00 01 3f d6              blr      x8
0x000ed25c: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ed260: 6c 74 fe 97              bl       #0x8a410                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000ed264: e0 0b 1a 32              mov      w0, #0x1c0
0x000ed268: b6 71 fe 97              bl       #0x89940                   ; -> _Znwm@plt
0x000ed26c: f5 03 00 aa              mov      x21, x0
0x000ed270: f8 d3 0d a9              stp      x24, x20, [sp, #0xd8]
0x000ed274: e8 7e 5f c8              ldxr     x8, [x23]
0x000ed278: 08 05 00 91              add      x8, x8, #1
0x000ed27c: e8 7e 09 c8              stxr     w9, x8, [x23]
0x000ed280: a9 ff ff 35              cbnz     w9, #0xed274
0x000ed284: 21 08 00 d0              adrp     x1, #0x1f3000
0x000ed288: 21 00 10 91              add      x1, x1, #0x400
0x000ed28c: e0 03 13 aa              mov      x0, x19
0x000ed290: 98 70 fe 97              bl       #0x894f0                   ; -> _ZN8kuaishou5dbase12AndroidClass3GetIiEET_PKc@plt
0x000ed294: f8 03 00 2a              mov      w24, w0
0x000ed298: 21 08 00 d0              adrp     x1, #0x1f3000
0x000ed29c: 21 38 10 91              add      x1, x1, #0x40e
0x000ed2a0: e0 03 13 aa              mov      x0, x19
0x000ed2a4: 93 70 fe 97              bl       #0x894f0                   ; -> _ZN8kuaishou5dbase12AndroidClass3GetIiEET_PKc@plt
0x000ed2a8: f9 03 00 2a              mov      w25, w0
0x000ed2ac: 81 07 00 f0              adrp     x1, #0x1e0000
0x000ed2b0: 21 d8 23 91              add      x1, x1, #0x8f6
0x000ed2b4: e0 03 13 aa              mov      x0, x19
0x000ed2b8: a6 70 fe 97              bl       #0x89550                   ; -> _ZN8kuaishou5dbase12AndroidClass3GetIlEET_PKc@plt
0x000ed2bc: fa 03 00 aa              mov      x26, x0
0x000ed2c0: e0 a3 02 91              add      x0, sp, #0xa8
0x000ed2c4: 6f 71 fe 97              bl       #0x89880                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededC1Ev@plt
0x000ed2c8: 81 07 00 f0              adrp     x1, #0x1e0000
0x000ed2cc: 82 07 00 f0              adrp     x2, #0x1e0000
0x000ed2d0: 21 00 24 91              add      x1, x1, #0x900
0x000ed2d4: 42 60 24 91              add      x2, x2, #0x918
0x000ed2d8: e0 03 13 aa              mov      x0, x19
0x000ed2dc: b8 0a 00 94              bl       #0xefdbc
0x000ed2e0: fc 5b 40 f9              ldr      x28, [sp, #0xb0]
0x000ed2e4: fb 03 00 aa              mov      x27, x0
0x000ed2e8: 88 03 40 f9              ldr      x8, [x28]
0x000ed2ec: 08 e1 42 f9              ldr      x8, [x8, #0x5c0]           ; -> _ZTVN10__cxxabiv120__si_class_type_infoE@got
0x000ed2f0: e0 03 1c aa              mov      x0, x28
0x000ed2f4: e1 03 1b aa              mov      x1, x27
0x000ed2f8: e2 03 1f aa              mov      x2, xzr
0x000ed2fc: 00 01 3f d6              blr      x8
0x000ed300: f6 03 00 aa              mov      x22, x0
0x000ed304: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ed308: 8a 79 fe 97              bl       #0x8b930                   ; -> _ZN8kuaishou5model20VideoFrameAttributesC1Ev@plt
0x000ed30c: 88 03 40 f9              ldr      x8, [x28]
0x000ed310: 08 ad 42 f9              ldr      x8, [x8, #0x558]
0x000ed314: e0 03 1c aa              mov      x0, x28
0x000ed318: e1 03 1b aa              mov      x1, x27
0x000ed31c: 00 01 3f d6              blr      x8
0x000ed320: e2 03 00 2a              mov      w2, w0
0x000ed324: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ed328: e1 03 16 aa              mov      x1, x22
0x000ed32c: 8d 71 fe 97              bl       #0x89960                   ; -> _ZN6google8protobuf11MessageLite14ParseFromArrayEPKvi@plt
0x000ed330: 88 03 40 f9              ldr      x8, [x28]
0x000ed334: 08 01 43 f9              ldr      x8, [x8, #0x600]
0x000ed338: e3 03 1f 32              mov      w3, #2
0x000ed33c: e0 03 1c aa              mov      x0, x28
0x000ed340: e1 03 1b aa              mov      x1, x27
0x000ed344: e2 03 16 aa              mov      x2, x22
0x000ed348: 00 01 3f d6              blr      x8
0x000ed34c: e0 03 04 91              add      x0, sp, #0x100
0x000ed350: a1 e3 03 d1              sub      x1, x29, #0xf8
0x000ed354: fb 78 fe 97              bl       #0x8b740                   ; -> _ZN8kuaishou5model20VideoFrameAttributesC1ERKS1_@plt
0x000ed358: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ed35c: 6d 73 fe 97              bl       #0x8a110                   ; -> _ZN8kuaishou5model20VideoFrameAttributesD1Ev@plt
0x000ed360: e0 a3 02 91              add      x0, sp, #0xa8
0x000ed364: 2b 74 fe 97              bl       #0x8a410                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000ed368: 21 04 80 52              mov      w1, #0x21
0x000ed36c: e2 63 03 91              add      x2, sp, #0xd8
0x000ed370: e6 03 04 91              add      x6, sp, #0x100
0x000ed374: e0 03 15 aa              mov      x0, x21
0x000ed378: e3 03 18 2a              mov      w3, w24
0x000ed37c: e4 03 19 2a              mov      w4, w25
0x000ed380: e5 03 1a aa              mov      x5, x26
0x000ed384: bf 75 fe 97              bl       #0x8aa80                   ; -> _ZN8kuaishou13VideoRawFrameC2ENS_13MediaDataTypeENSt6__ndk110shared_ptrINS_6render2gl7TextureEEEjjmONS_5model20VideoFrameAttributesE@plt
0x000ed388: e0 03 04 91              add      x0, sp, #0x100
0x000ed38c: 61 73 fe 97              bl       #0x8a110                   ; -> _ZN8kuaishou5model20VideoFrameAttributesD1Ev@plt
0x000ed390: f6 73 40 f9              ldr      x22, [sp, #0xe0]
0x000ed394: b6 01 00 b4              cbz      x22, #0xed3c8
0x000ed398: c8 22 00 91              add      x8, x22, #8
0x000ed39c: 09 fd 5f c8              ldaxr    x9, [x8]
0x000ed3a0: 2a 05 00 d1              sub      x10, x9, #1
0x000ed3a4: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x000ed3a8: ab ff ff 35              cbnz     w11, #0xed39c
0x000ed3ac: e9 00 00 b5              cbnz     x9, #0xed3c8
0x000ed3b0: c8 02 40 f9              ldr      x8, [x22]
0x000ed3b4: e0 03 16 aa              mov      x0, x22
0x000ed3b8: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x000ed3bc: 00 01 3f d6              blr      x8
0x000ed3c0: e0 03 16 aa              mov      x0, x22
0x000ed3c4: fb 74 fe 97              bl       #0x8a7b0                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x000ed3c8: e8 fe 5f c8              ldaxr    x8, [x23]
0x000ed3cc: 09 05 00 d1              sub      x9, x8, #1
0x000ed3d0: e9 fe 0a c8              stlxr    w10, x9, [x23]
0x000ed3d4: aa ff ff 35              cbnz     w10, #0xed3c8
0x000ed3d8: e8 00 00 b5              cbnz     x8, #0xed3f4
0x000ed3dc: 88 02 40 f9              ldr      x8, [x20]
0x000ed3e0: e0 03 14 aa              mov      x0, x20
0x000ed3e4: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x000ed3e8: 00 01 3f d6              blr      x8
0x000ed3ec: e0 03 14 aa              mov      x0, x20
0x000ed3f0: f0 74 fe 97              bl       #0x8a7b0                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x000ed3f4: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ed3f8: 22 71 fe 97              bl       #0x89880                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededC1Ev@plt
0x000ed3fc: a0 03 51 f8              ldur     x0, [x29, #-0xf0]
0x000ed400: 08 00 40 f9              ldr      x8, [x0]
0x000ed404: 08 59 40 f9              ldr      x8, [x8, #0xb0]            ; -> _ZTVN10__cxxabiv121__vmi_class_type_infoE@got
0x000ed408: e1 07 40 f9              ldr      x1, [sp, #8]
0x000ed40c: 00 01 3f d6              blr      x8
0x000ed410: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ed414: ff 73 fe 97              bl       #0x8a410                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000ed418: 2f 02 00 14              b        #0xedcd4                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0xe98
0x000ed41c: 56 04 80 52              mov      w22, #0x22
0x000ed420: 81 07 00 f0              adrp     x1, #0x1e0000
0x000ed424: 82 07 00 f0              adrp     x2, #0x1e0000
0x000ed428: 21 20 25 91              add      x1, x1, #0x948
0x000ed42c: 42 34 25 91              add      x2, x2, #0x94d
0x000ed430: e0 03 13 aa              mov      x0, x19
0x000ed434: d3 71 fe 97              bl       #0x89b80                   ; -> _ZN8kuaishou5dbase12AndroidClass3GetEPKcS3_@plt
0x000ed438: f7 03 00 aa              mov      x23, x0
0x000ed43c: e0 07 1b 32              mov      w0, #0x60
0x000ed440: 40 71 fe 97              bl       #0x89940                   ; -> _Znwm@plt
0x000ed444: f5 03 00 aa              mov      x21, x0
0x000ed448: e1 0f 40 f9              ldr      x1, [sp, #0x18]
0x000ed44c: 83 07 00 f0              adrp     x3, #0x1e0000
0x000ed450: 63 ec 25 91              add      x3, x3, #0x97b
0x000ed454: e2 03 17 aa              mov      x2, x23
0x000ed458: 06 75 fe 97              bl       #0x8a870                   ; -> _ZN8kuaishou5dbase12AndroidClassC2EP7_JNIEnvP8_jobjectPKc@plt
0x000ed45c: 08 0c 00 d0              adrp     x8, #0x26f000
0x000ed460: e9 0b 00 d0              adrp     x9, #0x26b000
0x000ed464: 08 81 12 91              add      x8, x8, #0x4a0
0x000ed468: 29 01 2c 91              add      x9, x9, #0xb00
0x000ed46c: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x000ed470: 00 05 80 52              mov      w0, #0x28
0x000ed474: a8 02 00 f9              str      x8, [x21]
0x000ed478: e9 57 00 f9              str      x9, [sp, #0xa8]
0x000ed47c: 80 82 81 3c              stur     q0, [x20, #0x18]
0x000ed480: 80 82 80 3c              stur     q0, [x20, #8]
0x000ed484: 2f 71 fe 97              bl       #0x89940                   ; -> _Znwm@plt
0x000ed488: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x000ed48c: 1f 10 00 f9              str      xzr, [x0, #0x20]
0x000ed490: 00 00 00 ad              stp      q0, q0, [x0]
0x000ed494: e0 6b 00 f9              str      x0, [sp, #0xd0]
0x000ed498: 81 07 00 f0              adrp     x1, #0x1e0000
0x000ed49c: 82 07 00 d0              adrp     x2, #0x1df000
0x000ed4a0: 21 9c 26 91              add      x1, x1, #0x9a7
0x000ed4a4: 42 34 38 91              add      x2, x2, #0xe0d
0x000ed4a8: e0 03 15 aa              mov      x0, x21
0x000ed4ac: a5 77 fe 97              bl       #0x8b340                   ; -> _ZN8kuaishou5dbase12AndroidClass4CallIhEET_PKcS5_z@plt
0x000ed4b0: e8 a3 02 91              add      x8, sp, #0xa8
0x000ed4b4: 1f 1c 00 72              tst      w0, #0xff
0x000ed4b8: 18 21 00 91              add      x24, x8, #8
0x000ed4bc: 00 0a 00 54              b.eq     #0xed5fc
0x000ed4c0: 81 07 00 f0              adrp     x1, #0x1e0000
0x000ed4c4: 02 08 00 f0              adrp     x2, #0x1f0000
0x000ed4c8: 21 c0 26 91              add      x1, x1, #0x9b0
0x000ed4cc: 42 ec 1a 91              add      x2, x2, #0x6bb
0x000ed4d0: e0 03 15 aa              mov      x0, x21
0x000ed4d4: ab 71 fe 97              bl       #0x89b80                   ; -> _ZN8kuaishou5dbase12AndroidClass3GetEPKcS3_@plt
0x000ed4d8: f7 03 00 aa              mov      x23, x0
0x000ed4dc: e0 0f 40 f9              ldr      x0, [sp, #0x18]
0x000ed4e0: 08 00 40 f9              ldr      x8, [x0]
0x000ed4e4: 08 99 43 f9              ldr      x8, [x8, #0x730]
0x000ed4e8: e1 03 17 aa              mov      x1, x23
0x000ed4ec: 00 01 3f d6              blr      x8
0x000ed4f0: f9 03 00 aa              mov      x25, x0
0x000ed4f4: e0 2f 00 f9              str      x0, [sp, #0x58]
0x000ed4f8: 00 05 80 52              mov      w0, #0x28
0x000ed4fc: 11 71 fe 97              bl       #0x89940                   ; -> _Znwm@plt
0x000ed500: 08 0c 00 d0              adrp     x8, #0x26f000
0x000ed504: 08 a1 19 91              add      x8, x8, #0x668
0x000ed508: 1f 64 01 a9              stp      xzr, x25, [x0, #0x10]
0x000ed50c: 15 10 00 f9              str      x21, [x0, #0x20]
0x000ed510: 08 7c 00 a9              stp      x8, xzr, [x0]
0x000ed514: e0 33 00 f9              str      x0, [sp, #0x60]
0x000ed518: e0 0f 40 f9              ldr      x0, [sp, #0x18]
0x000ed51c: 08 00 40 f9              ldr      x8, [x0]
0x000ed520: 08 9d 43 f9              ldr      x8, [x8, #0x738]
0x000ed524: e1 03 17 aa              mov      x1, x23
0x000ed528: 00 01 3f d6              blr      x8
0x000ed52c: e2 03 00 aa              mov      x2, x0
0x000ed530: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ed534: e1 63 01 91              add      x1, sp, #0x58
0x000ed538: b8 04 00 94              bl       #0xee818
0x000ed53c: b5 03 53 f8              ldur     x21, [x29, #-0xd0]
0x000ed540: e0 03 15 aa              mov      x0, x21
0x000ed544: 73 73 fe 97              bl       #0x8a310                   ; -> _ZNSt6__ndk15mutex4lockEv@plt
0x000ed548: a1 0b 71 a9              ldp      x1, x2, [x29, #-0xf0]
0x000ed54c: e0 03 18 aa              mov      x0, x24
0x000ed550: 2b 39 ff 97              bl       #0xbb9fc
0x000ed554: a8 e3 03 d1              sub      x8, x29, #0xf8
0x000ed558: 14 81 00 91              add      x20, x8, #0x20
0x000ed55c: 88 fe df c8              ldar     x8, [x20]
0x000ed560: e9 a3 02 91              add      x9, sp, #0xa8
0x000ed564: 29 81 00 91              add      x9, x9, #0x20
0x000ed568: 28 fd 9f c8              stlr     x8, [x9]
0x000ed56c: b8 5f 71 a9              ldp      x24, x23, [x29, #-0xf0]
0x000ed570: ff 02 18 eb              cmp      x23, x24
0x000ed574: e0 00 00 54              b.eq     #0xed590
0x000ed578: e8 8e 5d f8              ldr      x8, [x23, #-0x28]!
0x000ed57c: 08 01 40 f9              ldr      x8, [x8]
0x000ed580: e0 03 17 aa              mov      x0, x23
0x000ed584: 00 01 3f d6              blr      x8
0x000ed588: 1f 03 17 eb              cmp      x24, x23
0x000ed58c: 61 ff ff 54              b.ne     #0xed578
0x000ed590: e0 03 15 aa              mov      x0, x21
0x000ed594: b8 83 11 f8              stur     x24, [x29, #-0xe8]
0x000ed598: 9f fe 9f c8              stlr     xzr, [x20]
0x000ed59c: 01 74 fe 97              bl       #0x8a5a0                   ; -> _ZNSt6__ndk15mutex6unlockEv@plt
0x000ed5a0: b5 03 53 f8              ldur     x21, [x29, #-0xd0]
0x000ed5a4: e8 0b 00 d0              adrp     x8, #0x26b000
0x000ed5a8: 08 01 2c 91              add      x8, x8, #0xb00
0x000ed5ac: a8 83 10 f8              stur     x8, [x29, #-0xf8]
0x000ed5b0: bf 03 13 f8              stur     xzr, [x29, #-0xd0]
0x000ed5b4: b5 00 00 b4              cbz      x21, #0xed5c8
0x000ed5b8: e0 03 15 aa              mov      x0, x21
0x000ed5bc: f1 6f fe 97              bl       #0x89580                   ; -> _ZNSt6__ndk15mutexD1Ev@plt
0x000ed5c0: e0 03 15 aa              mov      x0, x21
0x000ed5c4: 9b 71 fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000ed5c8: b4 03 51 f8              ldur     x20, [x29, #-0xf0]
0x000ed5cc: f4 0a 00 b4              cbz      x20, #0xed728
0x000ed5d0: b5 83 51 f8              ldur     x21, [x29, #-0xe8]
0x000ed5d4: bf 02 14 eb              cmp      x21, x20
0x000ed5d8: 20 0a 00 54              b.eq     #0xed71c
0x000ed5dc: a8 8e 5d f8              ldr      x8, [x21, #-0x28]!
0x000ed5e0: 08 01 40 f9              ldr      x8, [x8]
0x000ed5e4: e0 03 15 aa              mov      x0, x21
0x000ed5e8: 00 01 3f d6              blr      x8
0x000ed5ec: 9f 02 15 eb              cmp      x20, x21
0x000ed5f0: 61 ff ff 54              b.ne     #0xed5dc
0x000ed5f4: a0 03 51 f8              ldur     x0, [x29, #-0xf0]
0x000ed5f8: 4a 00 00 14              b        #0xed720                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x8e4
0x000ed5fc: 81 07 00 f0              adrp     x1, #0x1e0000
0x000ed600: 82 07 00 f0              adrp     x2, #0x1e0000
0x000ed604: 21 ec 26 91              add      x1, x1, #0x9bb
0x000ed608: 42 60 24 91              add      x2, x2, #0x918
0x000ed60c: e0 03 15 aa              mov      x0, x21
0x000ed610: eb 09 00 94              bl       #0xefdbc
0x000ed614: f7 03 00 aa              mov      x23, x0
0x000ed618: e0 0f 40 f9              ldr      x0, [sp, #0x18]
0x000ed61c: 08 00 40 f9              ldr      x8, [x0]
0x000ed620: 08 e1 42 f9              ldr      x8, [x8, #0x5c0]           ; -> _ZNKSt6__ndk119__shared_weak_count13__get_deleterERKSt9type_info@got
0x000ed624: e1 03 17 aa              mov      x1, x23
0x000ed628: e2 03 1f aa              mov      x2, xzr
0x000ed62c: 00 01 3f d6              blr      x8
0x000ed630: f9 03 00 aa              mov      x25, x0
0x000ed634: e0 0f 40 f9              ldr      x0, [sp, #0x18]
0x000ed638: 08 00 40 f9              ldr      x8, [x0]
0x000ed63c: 08 ad 42 f9              ldr      x8, [x8, #0x558]           ; -> _ZTSN8kuaishou13VideoRawFrameE@got
0x000ed640: e1 03 17 aa              mov      x1, x23
0x000ed644: 00 01 3f d6              blr      x8
0x000ed648: 02 7c 40 93              sxtw     x2, w0
0x000ed64c: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ed650: e3 03 00 32              mov      w3, #1
0x000ed654: e1 03 19 aa              mov      x1, x25
0x000ed658: 06 05 00 94              bl       #0xeea70
0x000ed65c: ba 03 53 f8              ldur     x26, [x29, #-0xd0]
0x000ed660: e0 03 1a aa              mov      x0, x26
0x000ed664: 2b 73 fe 97              bl       #0x8a310                   ; -> _ZNSt6__ndk15mutex4lockEv@plt
0x000ed668: a1 0b 71 a9              ldp      x1, x2, [x29, #-0xf0]
0x000ed66c: e0 03 18 aa              mov      x0, x24
0x000ed670: e3 38 ff 97              bl       #0xbb9fc
0x000ed674: a8 e3 03 d1              sub      x8, x29, #0xf8
0x000ed678: 14 81 00 91              add      x20, x8, #0x20
0x000ed67c: 88 fe df c8              ldar     x8, [x20]
0x000ed680: e9 a3 02 91              add      x9, sp, #0xa8
0x000ed684: 29 81 00 91              add      x9, x9, #0x20
0x000ed688: 28 fd 9f c8              stlr     x8, [x9]
0x000ed68c: bb 63 71 a9              ldp      x27, x24, [x29, #-0xf0]
0x000ed690: 1f 03 1b eb              cmp      x24, x27
0x000ed694: e0 00 00 54              b.eq     #0xed6b0
0x000ed698: 08 8f 5d f8              ldr      x8, [x24, #-0x28]!
0x000ed69c: 08 01 40 f9              ldr      x8, [x8]
0x000ed6a0: e0 03 18 aa              mov      x0, x24
0x000ed6a4: 00 01 3f d6              blr      x8
0x000ed6a8: 7f 03 18 eb              cmp      x27, x24
0x000ed6ac: 61 ff ff 54              b.ne     #0xed698
0x000ed6b0: e0 03 1a aa              mov      x0, x26
0x000ed6b4: bb 83 11 f8              stur     x27, [x29, #-0xe8]
0x000ed6b8: 9f fe 9f c8              stlr     xzr, [x20]
0x000ed6bc: b9 73 fe 97              bl       #0x8a5a0                   ; -> _ZNSt6__ndk15mutex6unlockEv@plt
0x000ed6c0: b8 03 53 f8              ldur     x24, [x29, #-0xd0]
0x000ed6c4: e8 0b 00 d0              adrp     x8, #0x26b000
0x000ed6c8: 08 01 2c 91              add      x8, x8, #0xb00
0x000ed6cc: a8 83 10 f8              stur     x8, [x29, #-0xf8]
0x000ed6d0: bf 03 13 f8              stur     xzr, [x29, #-0xd0]
0x000ed6d4: b8 00 00 b4              cbz      x24, #0xed6e8
0x000ed6d8: e0 03 18 aa              mov      x0, x24
0x000ed6dc: a9 6f fe 97              bl       #0x89580                   ; -> _ZNSt6__ndk15mutexD1Ev@plt
0x000ed6e0: e0 03 18 aa              mov      x0, x24
0x000ed6e4: 53 71 fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000ed6e8: b4 03 51 f8              ldur     x20, [x29, #-0xf0]
0x000ed6ec: f4 15 00 b4              cbz      x20, #0xed9a8
0x000ed6f0: b8 83 51 f8              ldur     x24, [x29, #-0xe8]
0x000ed6f4: 1f 03 14 eb              cmp      x24, x20
0x000ed6f8: 20 15 00 54              b.eq     #0xed99c
0x000ed6fc: 08 8f 5d f8              ldr      x8, [x24, #-0x28]!
0x000ed700: 08 01 40 f9              ldr      x8, [x8]
0x000ed704: e0 03 18 aa              mov      x0, x24
0x000ed708: 00 01 3f d6              blr      x8
0x000ed70c: 9f 02 18 eb              cmp      x20, x24
0x000ed710: 61 ff ff 54              b.ne     #0xed6fc
0x000ed714: a0 03 51 f8              ldur     x0, [x29, #-0xf0]
0x000ed718: a2 00 00 14              b        #0xed9a0                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0xb64
0x000ed71c: e0 03 14 aa              mov      x0, x20
0x000ed720: b4 83 11 f8              stur     x20, [x29, #-0xe8]
0x000ed724: 43 71 fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000ed728: f5 33 40 f9              ldr      x21, [sp, #0x60]
0x000ed72c: 15 16 00 b4              cbz      x21, #0xed9ec
0x000ed730: a8 22 00 91              add      x8, x21, #8
0x000ed734: 09 fd 5f c8              ldaxr    x9, [x8]
0x000ed738: 2a 05 00 d1              sub      x10, x9, #1
0x000ed73c: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x000ed740: ab ff ff 35              cbnz     w11, #0xed734
0x000ed744: 49 15 00 b5              cbnz     x9, #0xed9ec
0x000ed748: a8 02 40 f9              ldr      x8, [x21]
0x000ed74c: e0 03 15 aa              mov      x0, x21
0x000ed750: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x000ed754: 00 01 3f d6              blr      x8
0x000ed758: e0 03 15 aa              mov      x0, x21
0x000ed75c: 15 74 fe 97              bl       #0x8a7b0                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x000ed760: a3 00 00 14              b        #0xed9ec                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0xbb0
0x000ed764: e0 03 14 aa              mov      x0, x20
0x000ed768: b4 83 11 f8              stur     x20, [x29, #-0xe8]
0x000ed76c: 31 71 fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000ed770: f5 53 40 f9              ldr      x21, [sp, #0xa0]
0x000ed774: b5 01 00 b4              cbz      x21, #0xed7a8
0x000ed778: a8 22 00 91              add      x8, x21, #8
0x000ed77c: 09 fd 5f c8              ldaxr    x9, [x8]
0x000ed780: 2a 05 00 d1              sub      x10, x9, #1
0x000ed784: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x000ed788: ab ff ff 35              cbnz     w11, #0xed77c
0x000ed78c: e9 00 00 b5              cbnz     x9, #0xed7a8
0x000ed790: a8 02 40 f9              ldr      x8, [x21]
0x000ed794: e0 03 15 aa              mov      x0, x21
0x000ed798: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x000ed79c: 00 01 3f d6              blr      x8
0x000ed7a0: e0 03 15 aa              mov      x0, x21
0x000ed7a4: 03 74 fe 97              bl       #0x8a7b0                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x000ed7a8: e0 0b 1a 32              mov      w0, #0x1c0
0x000ed7ac: 65 70 fe 97              bl       #0x89940                   ; -> _Znwm@plt
0x000ed7b0: f5 03 00 aa              mov      x21, x0
0x000ed7b4: e0 a3 01 91              add      x0, sp, #0x68
0x000ed7b8: e1 a3 02 91              add      x1, sp, #0xa8
0x000ed7bc: c7 58 ff 97              bl       #0xc3ad8
0x000ed7c0: 81 07 00 f0              adrp     x1, #0x1e0000
0x000ed7c4: 21 d8 23 91              add      x1, x1, #0x8f6
0x000ed7c8: e0 03 13 aa              mov      x0, x19
0x000ed7cc: 61 6f fe 97              bl       #0x89550                   ; -> _ZN8kuaishou5dbase12AndroidClass3GetIlEET_PKc@plt
0x000ed7d0: f8 03 00 aa              mov      x24, x0
0x000ed7d4: e0 c3 03 91              add      x0, sp, #0xf0
0x000ed7d8: 2a 70 fe 97              bl       #0x89880                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededC1Ev@plt
0x000ed7dc: 81 07 00 f0              adrp     x1, #0x1e0000
0x000ed7e0: 82 07 00 f0              adrp     x2, #0x1e0000
0x000ed7e4: 21 00 24 91              add      x1, x1, #0x900
0x000ed7e8: 42 60 24 91              add      x2, x2, #0x918
0x000ed7ec: e0 03 13 aa              mov      x0, x19
0x000ed7f0: 73 09 00 94              bl       #0xefdbc
0x000ed7f4: fa 7f 40 f9              ldr      x26, [sp, #0xf8]
0x000ed7f8: f9 03 00 aa              mov      x25, x0
0x000ed7fc: 48 03 40 f9              ldr      x8, [x26]
0x000ed800: 08 e1 42 f9              ldr      x8, [x8, #0x5c0]           ; -> _ZNKSt6__ndk119__shared_weak_count13__get_deleterERKSt9type_info@got
0x000ed804: e0 03 1a aa              mov      x0, x26
0x000ed808: e1 03 19 aa              mov      x1, x25
0x000ed80c: e2 03 1f aa              mov      x2, xzr
0x000ed810: 00 01 3f d6              blr      x8
0x000ed814: fb 03 00 aa              mov      x27, x0
0x000ed818: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ed81c: 45 78 fe 97              bl       #0x8b930                   ; -> _ZN8kuaishou5model20VideoFrameAttributesC1Ev@plt
0x000ed820: 48 03 40 f9              ldr      x8, [x26]
0x000ed824: 08 ad 42 f9              ldr      x8, [x8, #0x558]           ; -> _ZTSN8kuaishou13VideoRawFrameE@got
0x000ed828: e0 03 1a aa              mov      x0, x26
0x000ed82c: e1 03 19 aa              mov      x1, x25
0x000ed830: 00 01 3f d6              blr      x8
0x000ed834: e2 03 00 2a              mov      w2, w0
0x000ed838: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ed83c: e1 03 1b aa              mov      x1, x27
0x000ed840: 48 70 fe 97              bl       #0x89960                   ; -> _ZN6google8protobuf11MessageLite14ParseFromArrayEPKvi@plt
0x000ed844: 48 03 40 f9              ldr      x8, [x26]
0x000ed848: 08 01 43 f9              ldr      x8, [x8, #0x600]
0x000ed84c: e3 03 1f 32              mov      w3, #2
0x000ed850: e0 03 1a aa              mov      x0, x26
0x000ed854: e1 03 19 aa              mov      x1, x25
0x000ed858: e2 03 1b aa              mov      x2, x27
0x000ed85c: 00 01 3f d6              blr      x8
0x000ed860: e0 03 04 91              add      x0, sp, #0x100
0x000ed864: a1 e3 03 d1              sub      x1, x29, #0xf8
0x000ed868: b6 77 fe 97              bl       #0x8b740                   ; -> _ZN8kuaishou5model20VideoFrameAttributesC1ERKS1_@plt
0x000ed86c: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ed870: 28 72 fe 97              bl       #0x8a110                   ; -> _ZN8kuaishou5model20VideoFrameAttributesD1Ev@plt
0x000ed874: e0 c3 03 91              add      x0, sp, #0xf0
0x000ed878: e6 72 fe 97              bl       #0x8a410                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000ed87c: c1 04 80 52              mov      w1, #0x26
0x000ed880: e2 a3 01 91              add      x2, sp, #0x68
0x000ed884: e0 03 15 aa              mov      x0, x21
0x000ed888: 86 76 fe 97              bl       #0x8b2a0                   ; -> _ZN8kuaishou9MediaDataC2ENS_13MediaDataTypeERKNS_5dbase11MemoryChainIhEE@plt
0x000ed88c: a8 0c 00 f0              adrp     x8, #0x284000
0x000ed890: 08 25 46 f9              ldr      x8, [x8, #0xc48]           ; -> _ZTVN8kuaishou13VideoRawFrameE@got
0x000ed894: a0 82 01 91              add      x0, x21, #0x60
0x000ed898: b6 5e 08 29              stp      w22, w23, [x21, #0x40]
0x000ed89c: bf 7e 05 a9              stp      xzr, xzr, [x21, #0x50]
0x000ed8a0: 08 41 00 91              add      x8, x8, #0x10
0x000ed8a4: b8 26 00 f9              str      x24, [x21, #0x48]
0x000ed8a8: a8 02 00 f9              str      x8, [x21]
0x000ed8ac: e1 03 04 91              add      x1, sp, #0x100
0x000ed8b0: a4 77 fe 97              bl       #0x8b740                   ; -> _ZN8kuaishou5model20VideoFrameAttributesC1ERKS1_@plt
0x000ed8b4: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x000ed8b8: a8 a2 04 91              add      x8, x21, #0x128
0x000ed8bc: 00 01 80 3d              str      q0, [x8]
0x000ed8c0: a8 62 04 91              add      x8, x21, #0x118
0x000ed8c4: 00 01 80 3d              str      q0, [x8]
0x000ed8c8: a8 22 04 91              add      x8, x21, #0x108
0x000ed8cc: e0 03 04 91              add      x0, sp, #0x100
0x000ed8d0: a0 82 8f 3c              stur     q0, [x21, #0xf8]
0x000ed8d4: 00 01 80 3d              str      q0, [x8]
0x000ed8d8: a0 02 0d ad              stp      q0, q0, [x21, #0x1a0]
0x000ed8dc: a0 02 0c ad              stp      q0, q0, [x21, #0x180]
0x000ed8e0: a0 02 0b ad              stp      q0, q0, [x21, #0x160]
0x000ed8e4: a0 02 0a ad              stp      q0, q0, [x21, #0x140]
0x000ed8e8: 0a 72 fe 97              bl       #0x8a110                   ; -> _ZN8kuaishou5model20VideoFrameAttributesD1Ev@plt
0x000ed8ec: f6 4b 40 f9              ldr      x22, [sp, #0x90]
0x000ed8f0: fc 37 00 f9              str      x28, [sp, #0x68]
0x000ed8f4: ff 4b 00 f9              str      xzr, [sp, #0x90]
0x000ed8f8: b6 00 00 b4              cbz      x22, #0xed90c
0x000ed8fc: e0 03 16 aa              mov      x0, x22
0x000ed900: 20 6f fe 97              bl       #0x89580                   ; -> _ZNSt6__ndk15mutexD1Ev@plt
0x000ed904: e0 03 16 aa              mov      x0, x22
0x000ed908: ca 70 fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000ed90c: f4 3b 40 f9              ldr      x20, [sp, #0x70]
0x000ed910: f4 01 00 b4              cbz      x20, #0xed94c
0x000ed914: f6 3f 40 f9              ldr      x22, [sp, #0x78]
0x000ed918: df 02 14 eb              cmp      x22, x20
0x000ed91c: 20 01 00 54              b.eq     #0xed940
0x000ed920: c8 8e 5d f8              ldr      x8, [x22, #-0x28]!
0x000ed924: 08 01 40 f9              ldr      x8, [x8]                   ; -> _ZNSt6__ndk18ios_base5clearEj@got
0x000ed928: e0 03 16 aa              mov      x0, x22
0x000ed92c: 00 01 3f d6              blr      x8
0x000ed930: 9f 02 16 eb              cmp      x20, x22
0x000ed934: 61 ff ff 54              b.ne     #0xed920
0x000ed938: e0 3b 40 f9              ldr      x0, [sp, #0x70]
0x000ed93c: 02 00 00 14              b        #0xed944                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0xb08
0x000ed940: e0 03 14 aa              mov      x0, x20
0x000ed944: f4 3f 00 f9              str      x20, [sp, #0x78]
0x000ed948: ba 70 fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000ed94c: f6 6b 40 f9              ldr      x22, [sp, #0xd0]
0x000ed950: fc 57 00 f9              str      x28, [sp, #0xa8]
0x000ed954: ff 6b 00 f9              str      xzr, [sp, #0xd0]
0x000ed958: b6 00 00 b4              cbz      x22, #0xed96c
0x000ed95c: e0 03 16 aa              mov      x0, x22
0x000ed960: 08 6f fe 97              bl       #0x89580                   ; -> _ZNSt6__ndk15mutexD1Ev@plt
0x000ed964: e0 03 16 aa              mov      x0, x22
0x000ed968: b2 70 fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000ed96c: f4 5b 40 f9              ldr      x20, [sp, #0xb0]
0x000ed970: 34 1b 00 b4              cbz      x20, #0xedcd4
0x000ed974: f6 5f 40 f9              ldr      x22, [sp, #0xb8]
0x000ed978: df 02 14 eb              cmp      x22, x20
0x000ed97c: 60 1a 00 54              b.eq     #0xedcc8
0x000ed980: c8 8e 5d f8              ldr      x8, [x22, #-0x28]!
0x000ed984: 08 01 40 f9              ldr      x8, [x8]                   ; -> _ZNSt6__ndk18ios_base5clearEj@got
0x000ed988: e0 03 16 aa              mov      x0, x22
0x000ed98c: 00 01 3f d6              blr      x8
0x000ed990: 9f 02 16 eb              cmp      x20, x22
0x000ed994: 61 ff ff 54              b.ne     #0xed980
0x000ed998: ca 00 00 14              b        #0xedcc0                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0xe84
0x000ed99c: e0 03 14 aa              mov      x0, x20
0x000ed9a0: b4 83 11 f8              stur     x20, [x29, #-0xe8]
0x000ed9a4: a3 70 fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000ed9a8: e0 0f 40 f9              ldr      x0, [sp, #0x18]
0x000ed9ac: 08 00 40 f9              ldr      x8, [x0]
0x000ed9b0: 08 01 43 f9              ldr      x8, [x8, #0x600]           ; -> glFinish@got
0x000ed9b4: e3 03 1f 32              mov      w3, #2
0x000ed9b8: e1 03 17 aa              mov      x1, x23
0x000ed9bc: e2 03 19 aa              mov      x2, x25
0x000ed9c0: 00 01 3f d6              blr      x8
0x000ed9c4: 81 07 00 f0              adrp     x1, #0x1e0000
0x000ed9c8: 82 07 00 90              adrp     x2, #0x1dd000
0x000ed9cc: 21 20 27 91              add      x1, x1, #0x9c8
0x000ed9d0: 42 88 10 91              add      x2, x2, #0x422
0x000ed9d4: e0 03 15 aa              mov      x0, x21
0x000ed9d8: 6a 75 fe 97              bl       #0x8af80                   ; -> _ZN8kuaishou5dbase12AndroidClass4CallEPKcS3_z@plt
0x000ed9dc: a8 02 40 f9              ldr      x8, [x21]
0x000ed9e0: e0 03 15 aa              mov      x0, x21
0x000ed9e4: 08 05 40 f9              ldr      x8, [x8, #8]               ; -> _ZNSt6__ndk120__throw_system_errorEiPKc@got
0x000ed9e8: 00 01 3f d6              blr      x8
0x000ed9ec: e0 0b 1a 32              mov      w0, #0x1c0
0x000ed9f0: d4 6f fe 97              bl       #0x89940                   ; -> _Znwm@plt
0x000ed9f4: f5 03 00 aa              mov      x21, x0
0x000ed9f8: e0 a3 00 91              add      x0, sp, #0x28
0x000ed9fc: e1 a3 02 91              add      x1, sp, #0xa8
0x000eda00: 36 58 ff 97              bl       #0xc3ad8
0x000eda04: 21 08 00 d0              adrp     x1, #0x1f3000
0x000eda08: 21 00 10 91              add      x1, x1, #0x400
0x000eda0c: e0 03 13 aa              mov      x0, x19
0x000eda10: b8 6e fe 97              bl       #0x894f0                   ; -> _ZN8kuaishou5dbase12AndroidClass3GetIiEET_PKc@plt
0x000eda14: f7 03 00 2a              mov      w23, w0
0x000eda18: 21 08 00 d0              adrp     x1, #0x1f3000
0x000eda1c: 21 38 10 91              add      x1, x1, #0x40e
0x000eda20: e0 03 13 aa              mov      x0, x19
0x000eda24: b3 6e fe 97              bl       #0x894f0                   ; -> _ZN8kuaishou5dbase12AndroidClass3GetIiEET_PKc@plt
0x000eda28: f8 03 00 2a              mov      w24, w0
0x000eda2c: 81 07 00 f0              adrp     x1, #0x1e0000
0x000eda30: 21 d8 23 91              add      x1, x1, #0x8f6
0x000eda34: e0 03 13 aa              mov      x0, x19
0x000eda38: c6 6e fe 97              bl       #0x89550                   ; -> _ZN8kuaishou5dbase12AndroidClass3GetIlEET_PKc@plt
0x000eda3c: f9 03 00 aa              mov      x25, x0
0x000eda40: e0 c3 03 91              add      x0, sp, #0xf0
0x000eda44: 8f 6f fe 97              bl       #0x89880                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededC1Ev@plt
0x000eda48: 81 07 00 f0              adrp     x1, #0x1e0000
0x000eda4c: 82 07 00 f0              adrp     x2, #0x1e0000
0x000eda50: 21 00 24 91              add      x1, x1, #0x900
0x000eda54: 42 60 24 91              add      x2, x2, #0x918
0x000eda58: e0 03 13 aa              mov      x0, x19
0x000eda5c: d8 08 00 94              bl       #0xefdbc
0x000eda60: fb 7f 40 f9              ldr      x27, [sp, #0xf8]
0x000eda64: fa 03 00 aa              mov      x26, x0
0x000eda68: 68 03 40 f9              ldr      x8, [x27]
0x000eda6c: 08 e1 42 f9              ldr      x8, [x8, #0x5c0]           ; -> _ZN8kuaishou5model20VideoSourceYuvConfigC1Ev@got
0x000eda70: e0 03 1b aa              mov      x0, x27
0x000eda74: e1 03 1a aa              mov      x1, x26
0x000eda78: e2 03 1f aa              mov      x2, xzr
0x000eda7c: 00 01 3f d6              blr      x8
0x000eda80: fc 03 00 aa              mov      x28, x0
0x000eda84: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000eda88: aa 77 fe 97              bl       #0x8b930                   ; -> _ZN8kuaishou5model20VideoFrameAttributesC1Ev@plt
0x000eda8c: 68 03 40 f9              ldr      x8, [x27]
0x000eda90: 08 ad 42 f9              ldr      x8, [x8, #0x558]           ; -> _ZN8kuaishou5model17VideoSourceLayoutC1Ev@got
0x000eda94: e0 03 1b aa              mov      x0, x27
0x000eda98: e1 03 1a aa              mov      x1, x26
0x000eda9c: 00 01 3f d6              blr      x8
0x000edaa0: e2 03 00 2a              mov      w2, w0
0x000edaa4: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000edaa8: e1 03 1c aa              mov      x1, x28
0x000edaac: ad 6f fe 97              bl       #0x89960                   ; -> _ZN6google8protobuf11MessageLite14ParseFromArrayEPKvi@plt
0x000edab0: 68 03 40 f9              ldr      x8, [x27]
0x000edab4: 08 01 43 f9              ldr      x8, [x8, #0x600]           ; -> glFinish@got
0x000edab8: e3 03 1f 32              mov      w3, #2
0x000edabc: e0 03 1b aa              mov      x0, x27
0x000edac0: e1 03 1a aa              mov      x1, x26
0x000edac4: e2 03 1c aa              mov      x2, x28
0x000edac8: 00 01 3f d6              blr      x8
0x000edacc: e0 03 04 91              add      x0, sp, #0x100
0x000edad0: a1 e3 03 d1              sub      x1, x29, #0xf8
0x000edad4: 1b 77 fe 97              bl       #0x8b740                   ; -> _ZN8kuaishou5model20VideoFrameAttributesC1ERKS1_@plt
0x000edad8: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000edadc: 8d 71 fe 97              bl       #0x8a110                   ; -> _ZN8kuaishou5model20VideoFrameAttributesD1Ev@plt
0x000edae0: e0 c3 03 91              add      x0, sp, #0xf0
0x000edae4: 4b 72 fe 97              bl       #0x8a410                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000edae8: e2 a3 00 91              add      x2, sp, #0x28
0x000edaec: e0 03 15 aa              mov      x0, x21
0x000edaf0: e1 03 16 2a              mov      w1, w22
0x000edaf4: eb 75 fe 97              bl       #0x8b2a0                   ; -> _ZN8kuaishou9MediaDataC2ENS_13MediaDataTypeERKNS_5dbase11MemoryChainIhEE@plt
0x000edaf8: a8 0c 00 f0              adrp     x8, #0x284000
0x000edafc: 08 25 46 f9              ldr      x8, [x8, #0xc48]           ; -> _ZTVN8kuaishou13VideoRawFrameE@got
0x000edb00: a0 82 01 91              add      x0, x21, #0x60
0x000edb04: b7 62 08 29              stp      w23, w24, [x21, #0x40]
0x000edb08: bf 7e 05 a9              stp      xzr, xzr, [x21, #0x50]
0x000edb0c: 08 41 00 91              add      x8, x8, #0x10
0x000edb10: b9 26 00 f9              str      x25, [x21, #0x48]
0x000edb14: a8 02 00 f9              str      x8, [x21]
0x000edb18: e1 03 04 91              add      x1, sp, #0x100
0x000edb1c: 09 77 fe 97              bl       #0x8b740                   ; -> _ZN8kuaishou5model20VideoFrameAttributesC1ERKS1_@plt
0x000edb20: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x000edb24: a8 a2 04 91              add      x8, x21, #0x128
0x000edb28: 00 01 80 3d              str      q0, [x8]
0x000edb2c: a8 62 04 91              add      x8, x21, #0x118
0x000edb30: 00 01 80 3d              str      q0, [x8]
0x000edb34: a8 22 04 91              add      x8, x21, #0x108
0x000edb38: e0 03 04 91              add      x0, sp, #0x100
0x000edb3c: a0 82 8f 3c              stur     q0, [x21, #0xf8]
0x000edb40: 00 01 80 3d              str      q0, [x8]
0x000edb44: a0 02 0d ad              stp      q0, q0, [x21, #0x1a0]
0x000edb48: a0 02 0c ad              stp      q0, q0, [x21, #0x180]
0x000edb4c: a0 02 0b ad              stp      q0, q0, [x21, #0x160]
0x000edb50: a0 02 0a ad              stp      q0, q0, [x21, #0x140]
0x000edb54: 6f 71 fe 97              bl       #0x8a110                   ; -> _ZN8kuaishou5model20VideoFrameAttributesD1Ev@plt
0x000edb58: f6 2b 40 f9              ldr      x22, [sp, #0x50]
0x000edb5c: e8 0b 00 d0              adrp     x8, #0x26b000
0x000edb60: 08 01 2c 91              add      x8, x8, #0xb00
0x000edb64: e8 17 00 f9              str      x8, [sp, #0x28]
0x000edb68: ff 2b 00 f9              str      xzr, [sp, #0x50]
0x000edb6c: b6 00 00 b4              cbz      x22, #0xedb80
0x000edb70: e0 03 16 aa              mov      x0, x22
0x000edb74: 83 6e fe 97              bl       #0x89580                   ; -> _ZNSt6__ndk15mutexD1Ev@plt
0x000edb78: e0 03 16 aa              mov      x0, x22
0x000edb7c: 2d 70 fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000edb80: f4 1b 40 f9              ldr      x20, [sp, #0x30]
0x000edb84: f4 01 00 b4              cbz      x20, #0xedbc0
0x000edb88: f6 1f 40 f9              ldr      x22, [sp, #0x38]
0x000edb8c: df 02 14 eb              cmp      x22, x20
0x000edb90: 20 01 00 54              b.eq     #0xedbb4
0x000edb94: c8 8e 5d f8              ldr      x8, [x22, #-0x28]!
0x000edb98: 08 01 40 f9              ldr      x8, [x8]
0x000edb9c: e0 03 16 aa              mov      x0, x22
0x000edba0: 00 01 3f d6              blr      x8
0x000edba4: 9f 02 16 eb              cmp      x20, x22
0x000edba8: 61 ff ff 54              b.ne     #0xedb94
0x000edbac: e0 1b 40 f9              ldr      x0, [sp, #0x30]
0x000edbb0: 02 00 00 14              b        #0xedbb8                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0xd7c
0x000edbb4: e0 03 14 aa              mov      x0, x20
0x000edbb8: f4 1f 00 f9              str      x20, [sp, #0x38]
0x000edbbc: 1d 70 fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000edbc0: 81 07 00 f0              adrp     x1, #0x1e0000
0x000edbc4: 21 7c 23 91              add      x1, x1, #0x8df
0x000edbc8: e0 03 13 aa              mov      x0, x19
0x000edbcc: 49 6e fe 97              bl       #0x894f0                   ; -> _ZN8kuaishou5dbase12AndroidClass3GetIiEET_PKc@plt
0x000edbd0: e0 04 00 34              cbz      w0, #0xedc6c
0x000edbd4: 81 07 00 f0              adrp     x1, #0x1e0000
0x000edbd8: 21 7c 23 91              add      x1, x1, #0x8df
0x000edbdc: e0 03 13 aa              mov      x0, x19
0x000edbe0: 44 6e fe 97              bl       #0x894f0                   ; -> _ZN8kuaishou5dbase12AndroidClass3GetIiEET_PKc@plt
0x000edbe4: f6 03 00 2a              mov      w22, w0
0x000edbe8: 81 07 00 f0              adrp     x1, #0x1e0000
0x000edbec: 21 a4 23 91              add      x1, x1, #0x8e9
0x000edbf0: e0 03 13 aa              mov      x0, x19
0x000edbf4: 83 77 fe 97              bl       #0x8ba00                   ; -> _ZN8kuaishou5dbase12AndroidClass3GetIhEET_PKc@plt
0x000edbf8: f8 03 00 2a              mov      w24, w0
0x000edbfc: 00 09 80 52              mov      w0, #0x48
0x000edc00: 50 6f fe 97              bl       #0x89940                   ; -> _Znwm@plt
0x000edc04: 08 0c 00 90              adrp     x8, #0x26d000
0x000edc08: f7 03 00 aa              mov      x23, x0
0x000edc0c: 1f 1f 00 72              tst      w24, #0xff
0x000edc10: 08 c1 23 91              add      x8, x8, #0x8f0
0x000edc14: 18 60 00 91              add      x24, x0, #0x18
0x000edc18: 1f fc 00 a9              stp      xzr, xzr, [x0, #8]
0x000edc1c: e2 07 9f 1a              cset     w2, ne
0x000edc20: 08 00 00 f9              str      x8, [x0]
0x000edc24: e0 03 18 aa              mov      x0, x24
0x000edc28: e1 03 16 2a              mov      w1, w22
0x000edc2c: fd 6f fe 97              bl       #0x89c20                   ; -> _ZN8kuaishou6render2gl7TextureC2Ejb@plt
0x000edc30: b6 2e 40 f9              ldr      x22, [x21, #0x58]
0x000edc34: b8 5e 05 a9              stp      x24, x23, [x21, #0x50]
0x000edc38: b6 01 00 b4              cbz      x22, #0xedc6c
0x000edc3c: c8 22 00 91              add      x8, x22, #8
0x000edc40: 09 fd 5f c8              ldaxr    x9, [x8]
0x000edc44: 2a 05 00 d1              sub      x10, x9, #1
0x000edc48: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x000edc4c: ab ff ff 35              cbnz     w11, #0xedc40
0x000edc50: e9 00 00 b5              cbnz     x9, #0xedc6c
0x000edc54: c8 02 40 f9              ldr      x8, [x22]
0x000edc58: e0 03 16 aa              mov      x0, x22
0x000edc5c: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x000edc60: 00 01 3f d6              blr      x8
0x000edc64: e0 03 16 aa              mov      x0, x22
0x000edc68: d2 72 fe 97              bl       #0x8a7b0                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x000edc6c: f6 6b 40 f9              ldr      x22, [sp, #0xd0]
0x000edc70: e8 0b 00 d0              adrp     x8, #0x26b000
0x000edc74: 08 01 2c 91              add      x8, x8, #0xb00
0x000edc78: e8 57 00 f9              str      x8, [sp, #0xa8]
0x000edc7c: ff 6b 00 f9              str      xzr, [sp, #0xd0]
0x000edc80: b6 00 00 b4              cbz      x22, #0xedc94
0x000edc84: e0 03 16 aa              mov      x0, x22
0x000edc88: 3e 6e fe 97              bl       #0x89580                   ; -> _ZNSt6__ndk15mutexD1Ev@plt
0x000edc8c: e0 03 16 aa              mov      x0, x22
0x000edc90: e8 6f fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000edc94: f4 5b 40 f9              ldr      x20, [sp, #0xb0]
0x000edc98: f4 01 00 b4              cbz      x20, #0xedcd4
0x000edc9c: f6 5f 40 f9              ldr      x22, [sp, #0xb8]
0x000edca0: df 02 14 eb              cmp      x22, x20
0x000edca4: 20 01 00 54              b.eq     #0xedcc8
0x000edca8: c8 8e 5d f8              ldr      x8, [x22, #-0x28]!
0x000edcac: 08 01 40 f9              ldr      x8, [x8]
0x000edcb0: e0 03 16 aa              mov      x0, x22
0x000edcb4: 00 01 3f d6              blr      x8
0x000edcb8: 9f 02 16 eb              cmp      x20, x22
0x000edcbc: 61 ff ff 54              b.ne     #0xedca8
0x000edcc0: e0 5b 40 f9              ldr      x0, [sp, #0xb0]
0x000edcc4: 02 00 00 14              b        #0xedccc                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0xe90
0x000edcc8: e0 03 14 aa              mov      x0, x20
0x000edccc: f4 5f 00 f9              str      x20, [sp, #0xb8]
0x000edcd0: d8 6f fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000edcd4: 81 07 00 f0              adrp     x1, #0x1e0000
0x000edcd8: 82 07 00 f0              adrp     x2, #0x1e0000
0x000edcdc: 21 d8 21 91              add      x1, x1, #0x876
0x000edce0: 42 68 1e 91              add      x2, x2, #0x79a
0x000edce4: e0 03 13 aa              mov      x0, x19
0x000edce8: a6 6f fe 97              bl       #0x89b80                   ; -> _ZN8kuaishou5dbase12AndroidClass3GetEPKcS3_@plt
0x000edcec: 40 03 00 b4              cbz      x0, #0xedd54
0x000edcf0: e1 03 00 aa              mov      x1, x0
0x000edcf4: e0 0f 40 f9              ldr      x0, [sp, #0x18]
0x000edcf8: 51 fc ff 97              bl       #0xece3c                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject
0x000edcfc: f6 03 00 aa              mov      x22, x0
0x000edd00: e0 03 1b 32              mov      w0, #0x20
0x000edd04: 0f 6f fe 97              bl       #0x89940                   ; -> _Znwm@plt
0x000edd08: b4 92 40 f9              ldr      x20, [x21, #0x120]
0x000edd0c: e8 0b 00 f0              adrp     x8, #0x26c000
0x000edd10: 08 e1 25 91              add      x8, x8, #0x978
0x000edd14: 08 7c 00 a9              stp      x8, xzr, [x0]
0x000edd18: 1f 58 01 a9              stp      xzr, x22, [x0, #0x10]
0x000edd1c: b6 82 11 a9              stp      x22, x0, [x21, #0x118]
0x000edd20: b4 01 00 b4              cbz      x20, #0xedd54
0x000edd24: 88 22 00 91              add      x8, x20, #8
0x000edd28: 09 fd 5f c8              ldaxr    x9, [x8]
0x000edd2c: 2a 05 00 d1              sub      x10, x9, #1
0x000edd30: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x000edd34: ab ff ff 35              cbnz     w11, #0xedd28
0x000edd38: e9 00 00 b5              cbnz     x9, #0xedd54
0x000edd3c: 88 02 40 f9              ldr      x8, [x20]
0x000edd40: e0 03 14 aa              mov      x0, x20
0x000edd44: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x000edd48: 00 01 3f d6              blr      x8
0x000edd4c: e0 03 14 aa              mov      x0, x20
0x000edd50: 98 72 fe 97              bl       #0x8a7b0                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x000edd54: 68 02 40 f9              ldr      x8, [x19]
0x000edd58: e0 03 13 aa              mov      x0, x19
0x000edd5c: 08 05 40 f9              ldr      x8, [x8, #8]
0x000edd60: 00 01 3f d6              blr      x8
0x000edd64: e8 0b 40 f9              ldr      x8, [sp, #0x10]
0x000edd68: 08 15 40 f9              ldr      x8, [x8, #0x28]
0x000edd6c: e9 13 40 f9              ldr      x9, [sp, #0x20]
0x000edd70: 1f 01 09 eb              cmp      x8, x9
0x000edd74: 41 01 00 54              b.ne     #0xedd9c
0x000edd78: e0 03 15 aa              mov      x0, x21
0x000edd7c: ff 03 09 91              add      sp, sp, #0x240
0x000edd80: fd 7b 45 a9              ldp      x29, x30, [sp, #0x50]
0x000edd84: f4 4f 44 a9              ldp      x20, x19, [sp, #0x40]
0x000edd88: f6 57 43 a9              ldp      x22, x21, [sp, #0x30]
0x000edd8c: f8 5f 42 a9              ldp      x24, x23, [sp, #0x20]
0x000edd90: fa 67 41 a9              ldp      x26, x25, [sp, #0x10]
0x000edd94: fc 6f c6 a8              ldp      x28, x27, [sp], #0x60
0x000edd98: c0 03 5f d6              ret      
0x000edd9c: ed 70 fe 97              bl       #0x8a150                   ; -> __stack_chk_fail@plt
0x000edda0: f9 03 00 aa              mov      x25, x0
0x000edda4: e0 03 1a aa              mov      x0, x26
0x000edda8: fe 71 fe 97              bl       #0x8a5a0                   ; -> _ZNSt6__ndk15mutex6unlockEv@plt
0x000eddac: 02 00 00 14              b        #0xeddb4                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0xf78
0x000eddb0: f9 03 00 aa              mov      x25, x0
0x000eddb4: b3 03 53 f8              ldur     x19, [x29, #-0xd0]
0x000eddb8: e8 0b 00 d0              adrp     x8, #0x26b000
0x000eddbc: 08 01 2c 91              add      x8, x8, #0xb00
0x000eddc0: a8 83 10 f8              stur     x8, [x29, #-0xf8]
0x000eddc4: bf 03 13 f8              stur     xzr, [x29, #-0xd0]
0x000eddc8: b3 00 00 b4              cbz      x19, #0xedddc
0x000eddcc: e0 03 13 aa              mov      x0, x19
0x000eddd0: ec 6d fe 97              bl       #0x89580                   ; -> _ZNSt6__ndk15mutexD1Ev@plt
0x000eddd4: e0 03 13 aa              mov      x0, x19
0x000eddd8: 96 6f fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000edddc: b4 03 51 f8              ldur     x20, [x29, #-0xf0]
0x000edde0: 74 3c 00 b4              cbz      x20, #0xee56c
0x000edde4: b3 83 51 f8              ldur     x19, [x29, #-0xe8]
0x000edde8: 7f 02 14 eb              cmp      x19, x20
0x000eddec: 20 01 00 54              b.eq     #0xede10
0x000eddf0: 68 8e 5d f8              ldr      x8, [x19, #-0x28]!
0x000eddf4: 08 01 40 f9              ldr      x8, [x8]
0x000eddf8: e0 03 13 aa              mov      x0, x19
0x000eddfc: 00 01 3f d6              blr      x8
0x000ede00: 9f 02 13 eb              cmp      x20, x19
0x000ede04: 61 ff ff 54              b.ne     #0xeddf0
0x000ede08: a0 03 51 f8              ldur     x0, [x29, #-0xf0]
0x000ede0c: 02 00 00 14              b        #0xede14                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0xfd8
0x000ede10: e0 03 14 aa              mov      x0, x20
0x000ede14: b4 83 11 f8              stur     x20, [x29, #-0xe8]
0x000ede18: d4 01 00 14              b        #0xee568                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x172c
0x000ede1c: b1 01 00 14              b        #0xee4e0                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x16a4
0x000ede20: 25 00 00 14              b        #0xedeb4                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1078
0x000ede24: 98 af fe 97              bl       #0x99c84
0x000ede28: f9 03 00 aa              mov      x25, x0
0x000ede2c: e0 03 04 91              add      x0, sp, #0x100
0x000ede30: b8 70 fe 97              bl       #0x8a110                   ; -> _ZN8kuaishou5model20VideoFrameAttributesD1Ev@plt
0x000ede34: f3 73 40 f9              ldr      x19, [sp, #0xe0]
0x000ede38: 13 29 00 b5              cbnz     x19, #0xee358
0x000ede3c: 53 01 00 14              b        #0xee388                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x154c
0x000ede40: 35 01 00 14              b        #0xee314                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x14d8
0x000ede44: f9 03 00 aa              mov      x25, x0
0x000ede48: 52 01 00 14              b        #0xee390                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1554
0x000ede4c: 1a 00 00 14              b        #0xedeb4                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1078
0x000ede50: b3 2e 40 f9              ldr      x19, [x21, #0x58]
0x000ede54: f9 03 00 aa              mov      x25, x0
0x000ede58: b3 01 00 b4              cbz      x19, #0xede8c
0x000ede5c: 68 22 00 91              add      x8, x19, #8
0x000ede60: 09 fd 5f c8              ldaxr    x9, [x8]
0x000ede64: 2a 05 00 d1              sub      x10, x9, #1
0x000ede68: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x000ede6c: ab ff ff 35              cbnz     w11, #0xede60
0x000ede70: e9 00 00 b5              cbnz     x9, #0xede8c
0x000ede74: 68 02 40 f9              ldr      x8, [x19]
0x000ede78: e0 03 13 aa              mov      x0, x19
0x000ede7c: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x000ede80: 00 01 3f d6              blr      x8
0x000ede84: e0 03 13 aa              mov      x0, x19
0x000ede88: 4a 72 fe 97              bl       #0x8a7b0                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x000ede8c: e0 03 15 aa              mov      x0, x21
0x000ede90: 24 70 fe 97              bl       #0x89f20                   ; -> _ZN8kuaishou9MediaDataD2Ev@plt
0x000ede94: 03 00 00 14              b        #0xedea0                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1064
0x000ede98: 7b af fe 97              bl       #0x99c84
0x000ede9c: f9 03 00 aa              mov      x25, x0
0x000edea0: e0 03 04 91              add      x0, sp, #0x100
0x000edea4: 9b 70 fe 97              bl       #0x8a110                   ; -> _ZN8kuaishou5model20VideoFrameAttributesD1Ev@plt
0x000edea8: 59 01 00 14              b        #0xee40c                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x15d0
0x000edeac: 02 00 00 14              b        #0xedeb4                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1078
0x000edeb0: 75 af fe 97              bl       #0x99c84
0x000edeb4: f3 03 00 aa              mov      x19, x0
0x000edeb8: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000edebc: 55 71 fe 97              bl       #0x8a410                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000edec0: e0 03 13 aa              mov      x0, x19
0x000edec4: 70 af fe 97              bl       #0x99c84
0x000edec8: 6f af fe 97              bl       #0x99c84
0x000edecc: f3 03 00 aa              mov      x19, x0
0x000eded0: e0 a3 02 91              add      x0, sp, #0xa8
0x000eded4: 4f 71 fe 97              bl       #0x8a410                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000eded8: 02 00 00 14              b        #0xedee0                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x10a4
0x000ededc: f3 03 00 aa              mov      x19, x0
0x000edee0: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000edee4: 67 6e fe 97              bl       #0x89880                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededC1Ev@plt
0x000edee8: a0 03 51 f8              ldur     x0, [x29, #-0xf0]
0x000edeec: 08 00 40 f9              ldr      x8, [x0]
0x000edef0: 08 59 40 f9              ldr      x8, [x8, #0xb0]
0x000edef4: e1 03 1a aa              mov      x1, x26
0x000edef8: 00 01 3f d6              blr      x8
0x000edefc: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000edf00: 44 71 fe 97              bl       #0x8a410                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000edf04: 08 00 00 14              b        #0xedf24                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x10e8
0x000edf08: eb ff ff 17              b        #0xedeb4                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1078
0x000edf0c: 5e af fe 97              bl       #0x99c84
0x000edf10: f3 03 00 aa              mov      x19, x0
0x000edf14: e0 a3 02 91              add      x0, sp, #0xa8
0x000edf18: 3e 71 fe 97              bl       #0x8a410                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000edf1c: 02 00 00 14              b        #0xedf24                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x10e8
0x000edf20: f3 03 00 aa              mov      x19, x0
0x000edf24: e0 03 14 aa              mov      x0, x20
0x000edf28: 3a 6f fe 97              bl       #0x89c10                   ; -> _ZNSt6__ndk119__shared_weak_countD2Ev@plt
0x000edf2c: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000edf30: 54 6e fe 97              bl       #0x89880                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededC1Ev@plt
0x000edf34: a0 03 51 f8              ldur     x0, [x29, #-0xf0]
0x000edf38: 08 00 40 f9              ldr      x8, [x0]
0x000edf3c: 08 59 40 f9              ldr      x8, [x8, #0xb0]
0x000edf40: e1 03 19 aa              mov      x1, x25
0x000edf44: 00 01 3f d6              blr      x8
0x000edf48: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000edf4c: 31 71 fe 97              bl       #0x8a410                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000edf50: 0c 00 00 14              b        #0xedf80                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1144
0x000edf54: d8 ff ff 17              b        #0xedeb4                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1078
0x000edf58: 4b af fe 97              bl       #0x99c84
0x000edf5c: f9 03 00 aa              mov      x25, x0
0x000edf60: 2b 01 00 14              b        #0xee40c                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x15d0
0x000edf64: f3 03 00 aa              mov      x19, x0
0x000edf68: e0 a3 02 91              add      x0, sp, #0xa8
0x000edf6c: 29 71 fe 97              bl       #0x8a410                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000edf70: 04 00 00 14              b        #0xedf80                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1144
0x000edf74: f9 03 00 aa              mov      x25, x0
0x000edf78: 25 01 00 14              b        #0xee40c                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x15d0
0x000edf7c: f3 03 00 aa              mov      x19, x0
0x000edf80: e0 03 14 aa              mov      x0, x20
0x000edf84: 2b 6f fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000edf88: e0 03 13 aa              mov      x0, x19
0x000edf8c: fd 72 fe 97              bl       #0x8ab80                   ; -> __cxa_begin_catch@plt
0x000edf90: e0 a3 03 91              add      x0, sp, #0xe8
0x000edf94: e1 03 18 aa              mov      x1, x24
0x000edf98: d6 08 00 94              bl       #0xf02f0
0x000edf9c: 5d 6d fe 97              bl       #0x89510                   ; -> __cxa_rethrow@plt
0x000edfa0: f9 03 00 aa              mov      x25, x0
0x000edfa4: c3 73 fe 97              bl       #0x8aeb0                   ; -> __cxa_end_catch@plt
0x000edfa8: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000edfac: 35 6e fe 97              bl       #0x89880                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededC1Ev@plt
0x000edfb0: a0 03 51 f8              ldur     x0, [x29, #-0xf0]
0x000edfb4: 08 00 40 f9              ldr      x8, [x0]
0x000edfb8: 08 59 40 f9              ldr      x8, [x8, #0xb0]
0x000edfbc: e1 03 15 aa              mov      x1, x21
0x000edfc0: 00 01 3f d6              blr      x8
0x000edfc4: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000edfc8: 12 71 fe 97              bl       #0x8a410                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000edfcc: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000edfd0: 2c 6e fe 97              bl       #0x89880                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededC1Ev@plt
0x000edfd4: a0 03 51 f8              ldur     x0, [x29, #-0xf0]
0x000edfd8: 08 00 40 f9              ldr      x8, [x0]
0x000edfdc: 08 59 40 f9              ldr      x8, [x8, #0xb0]
0x000edfe0: e1 03 15 aa              mov      x1, x21
0x000edfe4: 00 01 3f d6              blr      x8
0x000edfe8: 0d 00 00 14              b        #0xee01c                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x11e0
0x000edfec: b2 ff ff 17              b        #0xedeb4                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1078
0x000edff0: 25 af fe 97              bl       #0x99c84
0x000edff4: b0 ff ff 17              b        #0xedeb4                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1078
0x000edff8: 23 af fe 97              bl       #0x99c84
0x000edffc: 22 af fe 97              bl       #0x99c84
0x000ee000: f9 03 00 aa              mov      x25, x0
0x000ee004: 1a 01 00 14              b        #0xee46c                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1630
0x000ee008: f3 03 00 aa              mov      x19, x0
0x000ee00c: df ff ff 17              b        #0xedf88                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x114c
0x000ee010: f9 03 00 aa              mov      x25, x0
0x000ee014: 18 01 00 14              b        #0xee474                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1638
0x000ee018: f9 03 00 aa              mov      x25, x0
0x000ee01c: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ee020: fc 70 fe 97              bl       #0x8a410                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000ee024: e6 00 00 14              b        #0xee3bc                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1580
0x000ee028: f9 03 00 aa              mov      x25, x0
0x000ee02c: e0 03 15 aa              mov      x0, x21
0x000ee030: 5c 71 fe 97              bl       #0x8a5a0                   ; -> _ZNSt6__ndk15mutex6unlockEv@plt
0x000ee034: 02 00 00 14              b        #0xee03c                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1200
0x000ee038: f9 03 00 aa              mov      x25, x0
0x000ee03c: b3 03 53 f8              ldur     x19, [x29, #-0xd0]
0x000ee040: bc 83 10 f8              stur     x28, [x29, #-0xf8]
0x000ee044: bf 03 13 f8              stur     xzr, [x29, #-0xd0]
0x000ee048: b3 00 00 b4              cbz      x19, #0xee05c
0x000ee04c: e0 03 13 aa              mov      x0, x19
0x000ee050: 4c 6d fe 97              bl       #0x89580                   ; -> _ZNSt6__ndk15mutexD1Ev@plt
0x000ee054: e0 03 13 aa              mov      x0, x19
0x000ee058: f6 6e fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000ee05c: b4 03 51 f8              ldur     x20, [x29, #-0xf0]
0x000ee060: 74 02 00 b4              cbz      x20, #0xee0ac
0x000ee064: b3 83 51 f8              ldur     x19, [x29, #-0xe8]
0x000ee068: 7f 02 14 eb              cmp      x19, x20
0x000ee06c: 20 01 00 54              b.eq     #0xee090
0x000ee070: 68 8e 5d f8              ldr      x8, [x19, #-0x28]!
0x000ee074: 08 01 40 f9              ldr      x8, [x8]
0x000ee078: e0 03 13 aa              mov      x0, x19
0x000ee07c: 00 01 3f d6              blr      x8
0x000ee080: 9f 02 13 eb              cmp      x20, x19
0x000ee084: 61 ff ff 54              b.ne     #0xee070
0x000ee088: a0 03 51 f8              ldur     x0, [x29, #-0xf0]
0x000ee08c: 02 00 00 14              b        #0xee094                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1258
0x000ee090: e0 03 14 aa              mov      x0, x20
0x000ee094: b4 83 11 f8              stur     x20, [x29, #-0xe8]
0x000ee098: e6 6e fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000ee09c: f3 53 40 f9              ldr      x19, [sp, #0xa0]
0x000ee0a0: b3 00 00 b5              cbnz     x19, #0xee0b4
0x000ee0a4: f4 00 00 14              b        #0xee474                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1638
0x000ee0a8: f9 03 00 aa              mov      x25, x0
0x000ee0ac: f3 53 40 f9              ldr      x19, [sp, #0xa0]
0x000ee0b0: 33 1e 00 b4              cbz      x19, #0xee474
0x000ee0b4: 68 22 00 91              add      x8, x19, #8
0x000ee0b8: 09 fd 5f c8              ldaxr    x9, [x8]
0x000ee0bc: 2a 05 00 d1              sub      x10, x9, #1
0x000ee0c0: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x000ee0c4: ab ff ff 35              cbnz     w11, #0xee0b8
0x000ee0c8: 69 1d 00 b5              cbnz     x9, #0xee474
0x000ee0cc: 68 02 40 f9              ldr      x8, [x19]
0x000ee0d0: e0 03 13 aa              mov      x0, x19
0x000ee0d4: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x000ee0d8: 00 01 3f d6              blr      x8
0x000ee0dc: e0 03 13 aa              mov      x0, x19
0x000ee0e0: b4 71 fe 97              bl       #0x8a7b0                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x000ee0e4: e4 00 00 14              b        #0xee474                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1638
0x000ee0e8: a6 72 fe 97              bl       #0x8ab80                   ; -> __cxa_begin_catch@plt
0x000ee0ec: a8 02 40 f9              ldr      x8, [x21]
0x000ee0f0: 08 0d 40 f9              ldr      x8, [x8, #0x18]            ; -> _ZTINSt6__ndk119__shared_weak_countE@got
0x000ee0f4: e0 03 15 aa              mov      x0, x21
0x000ee0f8: 00 01 3f d6              blr      x8
0x000ee0fc: a8 02 40 f9              ldr      x8, [x21]
0x000ee100: e0 03 15 aa              mov      x0, x21
0x000ee104: 08 05 40 f9              ldr      x8, [x8, #8]               ; -> _ZTVN10__cxxabiv120__si_class_type_infoE@got
0x000ee108: 00 01 3f d6              blr      x8
0x000ee10c: 01 6d fe 97              bl       #0x89510                   ; -> __cxa_rethrow@plt
0x000ee110: f9 03 00 aa              mov      x25, x0
0x000ee114: 67 73 fe 97              bl       #0x8aeb0                   ; -> __cxa_end_catch@plt
0x000ee118: d7 00 00 14              b        #0xee474                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1638
0x000ee11c: da ae fe 97              bl       #0x99c84
0x000ee120: f9 03 00 aa              mov      x25, x0
0x000ee124: d4 00 00 14              b        #0xee474                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1638
0x000ee128: f9 03 00 aa              mov      x25, x0
0x000ee12c: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ee130: b8 70 fe 97              bl       #0x8a410                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000ee134: e0 03 19 aa              mov      x0, x25
0x000ee138: 22 6c fe 97              bl       #0x891c0                   ; -> _Unwind_Resume@plt
0x000ee13c: 58 00 00 14              b        #0xee29c                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1460
0x000ee140: f9 03 00 aa              mov      x25, x0
0x000ee144: e0 03 17 aa              mov      x0, x23
0x000ee148: b2 6e fe 97              bl       #0x89c10                   ; -> _ZNSt6__ndk119__shared_weak_countD2Ev@plt
0x000ee14c: e0 03 17 aa              mov      x0, x23
0x000ee150: 06 01 00 14              b        #0xee568                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x172c
0x000ee154: e3 00 00 14              b        #0xee4e0                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x16a4
0x000ee158: f9 03 00 aa              mov      x25, x0
0x000ee15c: e0 03 15 aa              mov      x0, x21
0x000ee160: 10 71 fe 97              bl       #0x8a5a0                   ; -> _ZNSt6__ndk15mutex6unlockEv@plt
0x000ee164: 02 00 00 14              b        #0xee16c                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1330
0x000ee168: f9 03 00 aa              mov      x25, x0
0x000ee16c: b3 03 53 f8              ldur     x19, [x29, #-0xd0]
0x000ee170: e8 0b 00 b0              adrp     x8, #0x26b000
0x000ee174: 08 01 2c 91              add      x8, x8, #0xb00
0x000ee178: a8 83 10 f8              stur     x8, [x29, #-0xf8]
0x000ee17c: bf 03 13 f8              stur     xzr, [x29, #-0xd0]
0x000ee180: b3 00 00 b4              cbz      x19, #0xee194
0x000ee184: e0 03 13 aa              mov      x0, x19
0x000ee188: fe 6c fe 97              bl       #0x89580                   ; -> _ZNSt6__ndk15mutexD1Ev@plt
0x000ee18c: e0 03 13 aa              mov      x0, x19
0x000ee190: a8 6e fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000ee194: b4 03 51 f8              ldur     x20, [x29, #-0xf0]
0x000ee198: d4 09 00 b4              cbz      x20, #0xee2d0
0x000ee19c: b3 83 51 f8              ldur     x19, [x29, #-0xe8]
0x000ee1a0: 7f 02 14 eb              cmp      x19, x20
0x000ee1a4: 20 01 00 54              b.eq     #0xee1c8
0x000ee1a8: 68 8e 5d f8              ldr      x8, [x19, #-0x28]!
0x000ee1ac: 08 01 40 f9              ldr      x8, [x8]
0x000ee1b0: e0 03 13 aa              mov      x0, x19
0x000ee1b4: 00 01 3f d6              blr      x8
0x000ee1b8: 9f 02 13 eb              cmp      x20, x19
0x000ee1bc: 61 ff ff 54              b.ne     #0xee1a8
0x000ee1c0: a0 03 51 f8              ldur     x0, [x29, #-0xf0]
0x000ee1c4: 02 00 00 14              b        #0xee1cc                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1390
0x000ee1c8: e0 03 14 aa              mov      x0, x20
0x000ee1cc: b4 83 11 f8              stur     x20, [x29, #-0xe8]
0x000ee1d0: 98 6e fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000ee1d4: f3 33 40 f9              ldr      x19, [sp, #0x60]
0x000ee1d8: 13 08 00 b5              cbnz     x19, #0xee2d8
0x000ee1dc: e4 00 00 14              b        #0xee56c                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1730
0x000ee1e0: 68 72 fe 97              bl       #0x8ab80                   ; -> __cxa_begin_catch@plt
0x000ee1e4: 81 07 00 d0              adrp     x1, #0x1e0000
0x000ee1e8: 62 07 00 f0              adrp     x2, #0x1dd000
0x000ee1ec: 21 20 27 91              add      x1, x1, #0x9c8
0x000ee1f0: 42 88 10 91              add      x2, x2, #0x422
0x000ee1f4: e0 03 15 aa              mov      x0, x21
0x000ee1f8: 62 73 fe 97              bl       #0x8af80                   ; -> _ZN8kuaishou5dbase12AndroidClass4CallEPKcS3_z@plt
0x000ee1fc: a8 02 40 f9              ldr      x8, [x21]
0x000ee200: e0 03 15 aa              mov      x0, x21
0x000ee204: 08 05 40 f9              ldr      x8, [x8, #8]               ; -> _ZTVN10__cxxabiv120__si_class_type_infoE@got
0x000ee208: 00 01 3f d6              blr      x8
0x000ee20c: c1 6c fe 97              bl       #0x89510                   ; -> __cxa_rethrow@plt
0x000ee210: f9 03 00 aa              mov      x25, x0
0x000ee214: 27 73 fe 97              bl       #0x8aeb0                   ; -> __cxa_end_catch@plt
0x000ee218: d5 00 00 14              b        #0xee56c                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1730
0x000ee21c: 9a ae fe 97              bl       #0x99c84
0x000ee220: b0 00 00 14              b        #0xee4e0                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x16a4
0x000ee224: af 00 00 14              b        #0xee4e0                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x16a4
0x000ee228: ae 00 00 14              b        #0xee4e0                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x16a4
0x000ee22c: b3 2e 40 f9              ldr      x19, [x21, #0x58]
0x000ee230: f9 03 00 aa              mov      x25, x0
0x000ee234: b3 01 00 b4              cbz      x19, #0xee268
0x000ee238: 68 22 00 91              add      x8, x19, #8
0x000ee23c: 09 fd 5f c8              ldaxr    x9, [x8]
0x000ee240: 2a 05 00 d1              sub      x10, x9, #1
0x000ee244: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x000ee248: ab ff ff 35              cbnz     w11, #0xee23c
0x000ee24c: e9 00 00 b5              cbnz     x9, #0xee268
0x000ee250: 68 02 40 f9              ldr      x8, [x19]
0x000ee254: e0 03 13 aa              mov      x0, x19
0x000ee258: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x000ee25c: 00 01 3f d6              blr      x8
0x000ee260: e0 03 13 aa              mov      x0, x19
0x000ee264: 53 71 fe 97              bl       #0x8a7b0                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x000ee268: e0 03 15 aa              mov      x0, x21
0x000ee26c: 2d 6f fe 97              bl       #0x89f20                   ; -> _ZN8kuaishou9MediaDataD2Ev@plt
0x000ee270: 02 00 00 14              b        #0xee278                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x143c
0x000ee274: f9 03 00 aa              mov      x25, x0
0x000ee278: e0 03 04 91              add      x0, sp, #0x100
0x000ee27c: a5 6f fe 97              bl       #0x8a110                   ; -> _ZN8kuaishou5model20VideoFrameAttributesD1Ev@plt
0x000ee280: 9f 00 00 14              b        #0xee4fc                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x16c0
0x000ee284: f9 03 00 aa              mov      x25, x0
0x000ee288: 9d 00 00 14              b        #0xee4fc                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x16c0
0x000ee28c: f9 03 00 aa              mov      x25, x0
0x000ee290: b5 00 00 14              b        #0xee564                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1728
0x000ee294: f9 03 00 aa              mov      x25, x0
0x000ee298: 49 00 00 14              b        #0xee3bc                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1580
0x000ee29c: f9 03 00 aa              mov      x25, x0
0x000ee2a0: e0 03 15 aa              mov      x0, x21
0x000ee2a4: c9 00 00 14              b        #0xee5c8                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x178c
0x000ee2a8: 8e 00 00 14              b        #0xee4e0                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x16a4
0x000ee2ac: c8 02 40 f9              ldr      x8, [x22]
0x000ee2b0: f9 03 00 aa              mov      x25, x0
0x000ee2b4: e0 03 16 aa              mov      x0, x22
0x000ee2b8: 08 05 40 f9              ldr      x8, [x8, #8]               ; -> _ZTVN10__cxxabiv120__si_class_type_infoE@got
0x000ee2bc: 00 01 3f d6              blr      x8
0x000ee2c0: e0 03 19 aa              mov      x0, x25
0x000ee2c4: bf 6b fe 97              bl       #0x891c0                   ; -> _Unwind_Resume@plt
0x000ee2c8: 86 00 00 14              b        #0xee4e0                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x16a4
0x000ee2cc: f9 03 00 aa              mov      x25, x0
0x000ee2d0: f3 33 40 f9              ldr      x19, [sp, #0x60]
0x000ee2d4: d3 14 00 b4              cbz      x19, #0xee56c
0x000ee2d8: 68 22 00 91              add      x8, x19, #8
0x000ee2dc: 09 fd 5f c8              ldaxr    x9, [x8]
0x000ee2e0: 2a 05 00 d1              sub      x10, x9, #1
0x000ee2e4: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x000ee2e8: ab ff ff 35              cbnz     w11, #0xee2dc
0x000ee2ec: 09 14 00 b5              cbnz     x9, #0xee56c
0x000ee2f0: 68 02 40 f9              ldr      x8, [x19]
0x000ee2f4: e0 03 13 aa              mov      x0, x19
0x000ee2f8: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x000ee2fc: 00 01 3f d6              blr      x8
0x000ee300: e0 03 13 aa              mov      x0, x19
0x000ee304: 2b 71 fe 97              bl       #0x8a7b0                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x000ee308: 99 00 00 14              b        #0xee56c                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1730
0x000ee30c: f9 03 00 aa              mov      x25, x0
0x000ee310: 0e 00 00 14              b        #0xee348                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x150c
0x000ee314: f9 03 00 aa              mov      x25, x0
0x000ee318: f3 73 40 f9              ldr      x19, [sp, #0xe0]
0x000ee31c: f3 01 00 b5              cbnz     x19, #0xee358
0x000ee320: 1a 00 00 14              b        #0xee388                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x154c
0x000ee324: f9 03 00 aa              mov      x25, x0
0x000ee328: 37 00 00 14              b        #0xee404                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x15c8
0x000ee32c: f9 03 00 aa              mov      x25, x0
0x000ee330: e0 03 18 aa              mov      x0, x24
0x000ee334: 3f 6e fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000ee338: 21 00 00 14              b        #0xee3bc                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1580
0x000ee33c: f9 03 00 aa              mov      x25, x0
0x000ee340: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ee344: 73 6f fe 97              bl       #0x8a110                   ; -> _ZN8kuaishou5model20VideoFrameAttributesD1Ev@plt
0x000ee348: e0 a3 02 91              add      x0, sp, #0xa8
0x000ee34c: 31 70 fe 97              bl       #0x8a410                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000ee350: f3 73 40 f9              ldr      x19, [sp, #0xe0]
0x000ee354: b3 01 00 b4              cbz      x19, #0xee388
0x000ee358: 68 22 00 91              add      x8, x19, #8
0x000ee35c: 09 fd 5f c8              ldaxr    x9, [x8]
0x000ee360: 2a 05 00 d1              sub      x10, x9, #1
0x000ee364: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x000ee368: ab ff ff 35              cbnz     w11, #0xee35c
0x000ee36c: e9 00 00 b5              cbnz     x9, #0xee388
0x000ee370: 68 02 40 f9              ldr      x8, [x19]
0x000ee374: e0 03 13 aa              mov      x0, x19
0x000ee378: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x000ee37c: 00 01 3f d6              blr      x8
0x000ee380: e0 03 13 aa              mov      x0, x19
0x000ee384: 0b 71 fe 97              bl       #0x8a7b0                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x000ee388: e0 03 15 aa              mov      x0, x21
0x000ee38c: 29 6e fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000ee390: e8 fe 5f c8              ldaxr    x8, [x23]
0x000ee394: 09 05 00 d1              sub      x9, x8, #1
0x000ee398: e9 fe 0a c8              stlxr    w10, x9, [x23]
0x000ee39c: aa ff ff 35              cbnz     w10, #0xee390
0x000ee3a0: e8 00 00 b5              cbnz     x8, #0xee3bc
0x000ee3a4: 88 02 40 f9              ldr      x8, [x20]
0x000ee3a8: e0 03 14 aa              mov      x0, x20
0x000ee3ac: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x000ee3b0: 00 01 3f d6              blr      x8
0x000ee3b4: e0 03 14 aa              mov      x0, x20
0x000ee3b8: fe 70 fe 97              bl       #0x8a7b0                   ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x000ee3bc: e0 03 04 91              add      x0, sp, #0x100
0x000ee3c0: 30 6d fe 97              bl       #0x89880                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededC1Ev@plt
0x000ee3c4: e0 87 40 f9              ldr      x0, [sp, #0x108]
0x000ee3c8: 08 00 40 f9              ldr      x8, [x0]
0x000ee3cc: 08 59 40 f9              ldr      x8, [x8, #0xb0]
0x000ee3d0: e1 07 40 f9              ldr      x1, [sp, #8]
0x000ee3d4: 00 01 3f d6              blr      x8
0x000ee3d8: e0 03 04 91              add      x0, sp, #0x100
0x000ee3dc: 0d 70 fe 97              bl       #0x8a410                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000ee3e0: e0 03 19 aa              mov      x0, x25
0x000ee3e4: 77 6b fe 97              bl       #0x891c0                   ; -> _Unwind_Resume@plt
0x000ee3e8: f3 03 00 aa              mov      x19, x0
0x000ee3ec: e0 03 04 91              add      x0, sp, #0x100
0x000ee3f0: b3 fe ff 17              b        #0xedebc                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1080
0x000ee3f4: 24 ae fe 97              bl       #0x99c84
0x000ee3f8: f9 03 00 aa              mov      x25, x0
0x000ee3fc: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ee400: 44 6f fe 97              bl       #0x8a110                   ; -> _ZN8kuaishou5model20VideoFrameAttributesD1Ev@plt
0x000ee404: e0 c3 03 91              add      x0, sp, #0xf0
0x000ee408: 02 70 fe 97              bl       #0x8a410                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000ee40c: f3 4b 40 f9              ldr      x19, [sp, #0x90]
0x000ee410: fc 37 00 f9              str      x28, [sp, #0x68]
0x000ee414: ff 4b 00 f9              str      xzr, [sp, #0x90]
0x000ee418: b3 00 00 b4              cbz      x19, #0xee42c
0x000ee41c: e0 03 13 aa              mov      x0, x19
0x000ee420: 58 6c fe 97              bl       #0x89580                   ; -> _ZNSt6__ndk15mutexD1Ev@plt
0x000ee424: e0 03 13 aa              mov      x0, x19
0x000ee428: 02 6e fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000ee42c: f4 3b 40 f9              ldr      x20, [sp, #0x70]
0x000ee430: f4 01 00 b4              cbz      x20, #0xee46c
0x000ee434: f3 3f 40 f9              ldr      x19, [sp, #0x78]
0x000ee438: 7f 02 14 eb              cmp      x19, x20
0x000ee43c: 20 01 00 54              b.eq     #0xee460
0x000ee440: 68 8e 5d f8              ldr      x8, [x19, #-0x28]!
0x000ee444: 08 01 40 f9              ldr      x8, [x8]
0x000ee448: e0 03 13 aa              mov      x0, x19
0x000ee44c: 00 01 3f d6              blr      x8
0x000ee450: 9f 02 13 eb              cmp      x20, x19
0x000ee454: 61 ff ff 54              b.ne     #0xee440
0x000ee458: e0 3b 40 f9              ldr      x0, [sp, #0x70]
0x000ee45c: 02 00 00 14              b        #0xee464                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1628
0x000ee460: e0 03 14 aa              mov      x0, x20
0x000ee464: f4 3f 00 f9              str      x20, [sp, #0x78]
0x000ee468: f2 6d fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000ee46c: e0 03 15 aa              mov      x0, x21
0x000ee470: f0 6d fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000ee474: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000ee478: fc 57 00 f9              str      x28, [sp, #0xa8]
0x000ee47c: ff 6b 00 f9              str      xzr, [sp, #0xd0]
0x000ee480: b3 00 00 b4              cbz      x19, #0xee494
0x000ee484: e0 03 13 aa              mov      x0, x19
0x000ee488: 3e 6c fe 97              bl       #0x89580                   ; -> _ZNSt6__ndk15mutexD1Ev@plt
0x000ee48c: e0 03 13 aa              mov      x0, x19
0x000ee490: e8 6d fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000ee494: f4 5b 40 f9              ldr      x20, [sp, #0xb0]
0x000ee498: b4 09 00 b4              cbz      x20, #0xee5cc
0x000ee49c: f3 5f 40 f9              ldr      x19, [sp, #0xb8]
0x000ee4a0: 7f 02 14 eb              cmp      x19, x20
0x000ee4a4: 80 09 00 54              b.eq     #0xee5d4
0x000ee4a8: 68 8e 5d f8              ldr      x8, [x19, #-0x28]!
0x000ee4ac: 08 01 40 f9              ldr      x8, [x8]
0x000ee4b0: e0 03 13 aa              mov      x0, x19
0x000ee4b4: 00 01 3f d6              blr      x8
0x000ee4b8: 9f 02 13 eb              cmp      x20, x19
0x000ee4bc: 61 ff ff 54              b.ne     #0xee4a8
0x000ee4c0: 40 00 00 14              b        #0xee5c0                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1784
0x000ee4c4: f9 03 00 aa              mov      x25, x0
0x000ee4c8: e0 03 13 aa              mov      x0, x19
0x000ee4cc: 3f 00 00 14              b        #0xee5c8                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x178c
0x000ee4d0: f9 03 00 aa              mov      x25, x0
0x000ee4d4: 08 00 00 14              b        #0xee4f4                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x16b8
0x000ee4d8: f9 03 00 aa              mov      x25, x0
0x000ee4dc: 08 00 00 14              b        #0xee4fc                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x16c0
0x000ee4e0: f9 03 00 aa              mov      x25, x0
0x000ee4e4: 22 00 00 14              b        #0xee56c                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1730
0x000ee4e8: f9 03 00 aa              mov      x25, x0
0x000ee4ec: a0 e3 03 d1              sub      x0, x29, #0xf8
0x000ee4f0: 08 6f fe 97              bl       #0x8a110                   ; -> _ZN8kuaishou5model20VideoFrameAttributesD1Ev@plt
0x000ee4f4: e0 c3 03 91              add      x0, sp, #0xf0
0x000ee4f8: c6 6f fe 97              bl       #0x8a410                   ; -> _ZN8kuaishou5dbase27AttachCurrentThreadIfNeededD1Ev@plt
0x000ee4fc: f3 2b 40 f9              ldr      x19, [sp, #0x50]
0x000ee500: e8 0b 00 b0              adrp     x8, #0x26b000
0x000ee504: 08 01 2c 91              add      x8, x8, #0xb00
0x000ee508: e8 17 00 f9              str      x8, [sp, #0x28]
0x000ee50c: ff 2b 00 f9              str      xzr, [sp, #0x50]
0x000ee510: b3 00 00 b4              cbz      x19, #0xee524
0x000ee514: e0 03 13 aa              mov      x0, x19
0x000ee518: 1a 6c fe 97              bl       #0x89580                   ; -> _ZNSt6__ndk15mutexD1Ev@plt
0x000ee51c: e0 03 13 aa              mov      x0, x19
0x000ee520: c4 6d fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000ee524: f4 1b 40 f9              ldr      x20, [sp, #0x30]
0x000ee528: f4 01 00 b4              cbz      x20, #0xee564
0x000ee52c: f3 1f 40 f9              ldr      x19, [sp, #0x38]
0x000ee530: 7f 02 14 eb              cmp      x19, x20
0x000ee534: 20 01 00 54              b.eq     #0xee558
0x000ee538: 68 8e 5d f8              ldr      x8, [x19, #-0x28]!
0x000ee53c: 08 01 40 f9              ldr      x8, [x8]
0x000ee540: e0 03 13 aa              mov      x0, x19
0x000ee544: 00 01 3f d6              blr      x8
0x000ee548: 9f 02 13 eb              cmp      x20, x19
0x000ee54c: 61 ff ff 54              b.ne     #0xee538
0x000ee550: e0 1b 40 f9              ldr      x0, [sp, #0x30]
0x000ee554: 02 00 00 14              b        #0xee55c                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1720
0x000ee558: e0 03 14 aa              mov      x0, x20
0x000ee55c: f4 1f 00 f9              str      x20, [sp, #0x38]
0x000ee560: b4 6d fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000ee564: e0 03 15 aa              mov      x0, x21
0x000ee568: b2 6d fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000ee56c: f3 6b 40 f9              ldr      x19, [sp, #0xd0]
0x000ee570: e8 0b 00 b0              adrp     x8, #0x26b000
0x000ee574: 08 01 2c 91              add      x8, x8, #0xb00
0x000ee578: e8 57 00 f9              str      x8, [sp, #0xa8]
0x000ee57c: ff 6b 00 f9              str      xzr, [sp, #0xd0]
0x000ee580: b3 00 00 b4              cbz      x19, #0xee594
0x000ee584: e0 03 13 aa              mov      x0, x19
0x000ee588: fe 6b fe 97              bl       #0x89580                   ; -> _ZNSt6__ndk15mutexD1Ev@plt
0x000ee58c: e0 03 13 aa              mov      x0, x19
0x000ee590: a8 6d fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000ee594: f4 5b 40 f9              ldr      x20, [sp, #0xb0]
0x000ee598: b4 01 00 b4              cbz      x20, #0xee5cc
0x000ee59c: f3 5f 40 f9              ldr      x19, [sp, #0xb8]
0x000ee5a0: 7f 02 14 eb              cmp      x19, x20
0x000ee5a4: 80 01 00 54              b.eq     #0xee5d4
0x000ee5a8: 68 8e 5d f8              ldr      x8, [x19, #-0x28]!
0x000ee5ac: 08 01 40 f9              ldr      x8, [x8]
0x000ee5b0: e0 03 13 aa              mov      x0, x19
0x000ee5b4: 00 01 3f d6              blr      x8
0x000ee5b8: 9f 02 13 eb              cmp      x20, x19
0x000ee5bc: 61 ff ff 54              b.ne     #0xee5a8
0x000ee5c0: e0 5b 40 f9              ldr      x0, [sp, #0xb0]
0x000ee5c4: f4 5f 00 f9              str      x20, [sp, #0xb8]
0x000ee5c8: 9a 6d fe 97              bl       #0x89c30                   ; -> _ZdlPv@plt
0x000ee5cc: e0 03 19 aa              mov      x0, x25
0x000ee5d0: fc 6a fe 97              bl       #0x891c0                   ; -> _Unwind_Resume@plt
0x000ee5d4: e0 03 14 aa              mov      x0, x20
0x000ee5d8: fb ff ff 17              b        #0xee5c4                   ; -> _ZN8kuaishou7android20VideoRawFrameFromJniEP7_JNIEnvP8_jobject+0x1788
