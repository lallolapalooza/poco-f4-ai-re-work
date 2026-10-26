; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libdnnvad_jni.so
; target  : address 0x328c
; start   : 0x328c
; size    : 0x238
; bytes   : 0x238   instructions: 142
; generated: 2026-10-23T23:27:17

0x0000328c: 3f 23 03 d5              paciasp  
0x00003290: ff c3 01 d1              sub      sp, sp, #0x70
0x00003294: fd 7b 02 a9              stp      x29, x30, [sp, #0x20]
0x00003298: fa 67 03 a9              stp      x26, x25, [sp, #0x30]
0x0000329c: f8 5f 04 a9              stp      x24, x23, [sp, #0x40]
0x000032a0: f6 57 05 a9              stp      x22, x21, [sp, #0x50]
0x000032a4: f4 4f 06 a9              stp      x20, x19, [sp, #0x60]
0x000032a8: fd 83 00 91              add      x29, sp, #0x20
0x000032ac: 59 d0 3b d5              mrs      x25, tpidr_el0
0x000032b0: 13 a0 00 91              add      x19, x0, #0x28
0x000032b4: f5 03 00 aa              mov      x21, x0
0x000032b8: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x000032bc: e0 03 13 aa              mov      x0, x19
0x000032c0: f4 03 03 aa              mov      x20, x3
0x000032c4: f7 03 02 aa              mov      x23, x2
0x000032c8: f6 03 01 aa              mov      x22, x1
0x000032cc: a8 83 1f f8              stur     x8, [x29, #-8]
0x000032d0: 82 01 00 94              bl       #0x38d8
0x000032d4: 20 00 80 52              mov      w0, #1
0x000032d8: 01 04 80 52              mov      w1, #0x20
0x000032dc: ff ff 00 a9              stp      xzr, xzr, [sp, #8]
0x000032e0: 84 01 00 94              bl       #0x38f0                    ; -> calloc@plt
0x000032e4: e0 03 00 f9              str      x0, [sp]
0x000032e8: 00 03 00 b4              cbz      x0, #0x3348
0x000032ec: f8 03 00 aa              mov      x24, x0
0x000032f0: 20 00 80 52              mov      w0, #1
0x000032f4: 01 02 80 52              mov      w1, #0x10
0x000032f8: 3a 00 80 52              mov      w26, #1
0x000032fc: 7d 01 00 94              bl       #0x38f0                    ; -> calloc@plt
0x00003300: 00 0b 00 f9              str      x0, [x24, #0x10]
0x00003304: 20 03 00 b4              cbz      x0, #0x3368
0x00003308: a8 06 40 f9              ldr      x8, [x21, #8]
0x0000330c: 1a 1b 00 b9              str      w26, [x24, #0x18]
0x00003310: 17 00 00 f9              str      x23, [x0]
0x00003314: e1 03 00 91              mov      x1, sp
0x00003318: 16 58 01 29              stp      w22, w22, [x0, #8]
0x0000331c: e2 03 1f aa              mov      x2, xzr
0x00003320: 09 01 40 f9              ldr      x9, [x8]
0x00003324: e0 03 08 aa              mov      x0, x8
0x00003328: 29 01 40 f9              ldr      x9, [x9]
0x0000332c: 20 01 3f d6              blr      x9
0x00003330: c0 02 00 34              cbz      w0, #0x3388
0x00003334: e1 ff ff d0              adrp     x1, #0x1000
0x00003338: 21 84 12 91              add      x1, x1, #0x4a1
0x0000333c: e2 ff ff b0              adrp     x2, #0
0x00003340: 42 20 38 91              add      x2, x2, #0xe08
0x00003344: 20 00 00 14              b        #0x33c4                    ; -> _ZN3Vad7processE24nnvad_wrapper_input_dataP20nnvad_wrapper_output+0x138
0x00003348: e1 ff ff d0              adrp     x1, #0x1000
0x0000334c: 21 84 12 91              add      x1, x1, #0x4a1
0x00003350: e2 ff ff d0              adrp     x2, #0x1000
0x00003354: 42 7c 1d 91              add      x2, x2, #0x75f
0x00003358: c0 00 80 52              mov      w0, #6
0x0000335c: f9 00 00 94              bl       #0x3740                    ; -> __android_log_print@plt
0x00003360: d5 00 80 12              mov      w21, #-7
0x00003364: 23 00 00 14              b        #0x33f0                    ; -> _ZN3Vad7processE24nnvad_wrapper_input_dataP20nnvad_wrapper_output+0x164
0x00003368: e1 ff ff d0              adrp     x1, #0x1000
0x0000336c: 21 84 12 91              add      x1, x1, #0x4a1
0x00003370: e2 ff ff d0              adrp     x2, #0x1000
0x00003374: 42 c0 12 91              add      x2, x2, #0x4b0
0x00003378: c0 00 80 52              mov      w0, #6
0x0000337c: f1 00 00 94              bl       #0x3740                    ; -> __android_log_print@plt
0x00003380: d5 00 80 12              mov      w21, #-7
0x00003384: 13 00 00 14              b        #0x33d0                    ; -> _ZN3Vad7processE24nnvad_wrapper_input_dataP20nnvad_wrapper_output+0x144
0x00003388: 00 04 03 0f              movi     v0.2s, #0x60
0x0000338c: a0 06 40 f9              ldr      x0, [x21, #8]
0x00003390: f4 07 00 f9              str      x20, [sp, #8]
0x00003394: e3 23 00 91              add      x3, sp, #8
0x00003398: 41 01 80 52              mov      w1, #0xa
0x0000339c: e2 03 1f aa              mov      x2, xzr
0x000033a0: e0 0b 00 fd              str      d0, [sp, #0x10]
0x000033a4: 08 00 40 f9              ldr      x8, [x0]
0x000033a8: 08 0d 40 f9              ldr      x8, [x8, #0x18]
0x000033ac: 00 01 3f d6              blr      x8
0x000033b0: e0 03 00 34              cbz      w0, #0x342c
0x000033b4: e1 ff ff d0              adrp     x1, #0x1000
0x000033b8: 21 84 12 91              add      x1, x1, #0x4a1
0x000033bc: e2 ff ff d0              adrp     x2, #0x1000
0x000033c0: 42 e0 19 91              add      x2, x2, #0x678
0x000033c4: c0 00 80 52              mov      w0, #6
0x000033c8: de 00 00 94              bl       #0x3740                    ; -> __android_log_print@plt
0x000033cc: 95 00 80 12              mov      w21, #-5
0x000033d0: e0 03 40 f9              ldr      x0, [sp]
0x000033d4: e0 00 00 b4              cbz      x0, #0x33f0
0x000033d8: 08 08 40 f9              ldr      x8, [x0, #0x10]
0x000033dc: 88 00 00 b4              cbz      x8, #0x33ec
0x000033e0: e0 03 08 aa              mov      x0, x8
0x000033e4: fb 00 00 94              bl       #0x37d0                    ; -> free@plt
0x000033e8: e0 03 40 f9              ldr      x0, [sp]
0x000033ec: f9 00 00 94              bl       #0x37d0                    ; -> free@plt
0x000033f0: e0 03 13 aa              mov      x0, x19
0x000033f4: 57 01 00 94              bl       #0x3950                    ; -> pthread_mutex_unlock@plt
0x000033f8: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x000033fc: a9 83 5f f8              ldur     x9, [x29, #-8]
0x00003400: 1f 01 09 eb              cmp      x8, x9
0x00003404: e1 05 00 54              b.ne     #0x34c0
0x00003408: e0 03 15 2a              mov      w0, w21
0x0000340c: f4 4f 46 a9              ldp      x20, x19, [sp, #0x60]
0x00003410: f6 57 45 a9              ldp      x22, x21, [sp, #0x50]
0x00003414: f8 5f 44 a9              ldp      x24, x23, [sp, #0x40]
0x00003418: fa 67 43 a9              ldp      x26, x25, [sp, #0x30]
0x0000341c: fd 7b 42 a9              ldp      x29, x30, [sp, #0x20]
0x00003420: ff c3 01 91              add      sp, sp, #0x70
0x00003424: bf 23 03 d5              autiasp  
0x00003428: c0 03 5f d6              ret      
0x0000342c: 88 3a 40 b9              ldr      w8, [x20, #0x38]
0x00003430: 1f 05 00 71              cmp      w8, #1
0x00003434: c1 03 00 54              b.ne     #0x34ac
0x00003438: 85 2e 40 b9              ldr      w5, [x20, #0x2c]
0x0000343c: 86 26 40 b9              ldr      w6, [x20, #0x24]
0x00003440: a3 12 40 b9              ldr      w3, [x21, #0x10]
0x00003444: a8 00 06 4b              sub      w8, w5, w6
0x00003448: 1f 01 03 6b              cmp      w8, w3
0x0000344c: 0a 03 00 54              b.ge     #0x34ac
0x00003450: e1 ff ff d0              adrp     x1, #0x1000
0x00003454: 21 84 12 91              add      x1, x1, #0x4a1
0x00003458: e2 ff ff d0              adrp     x2, #0x1000
0x0000345c: 42 c8 0d 91              add      x2, x2, #0x372
0x00003460: 60 00 80 52              mov      w0, #3
0x00003464: 24 00 80 52              mov      w4, #1
0x00003468: b6 00 00 94              bl       #0x3740                    ; -> __android_log_print@plt
0x0000346c: a0 06 40 f9              ldr      x0, [x21, #8]
0x00003470: 01 02 80 52              mov      w1, #0x10
0x00003474: e2 03 1f aa              mov      x2, xzr
0x00003478: e3 03 1f aa              mov      x3, xzr
0x0000347c: 08 00 40 f9              ldr      x8, [x0]
0x00003480: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00003484: 00 01 3f d6              blr      x8
0x00003488: 60 01 00 34              cbz      w0, #0x34b4
0x0000348c: e1 ff ff d0              adrp     x1, #0x1000
0x00003490: 21 84 12 91              add      x1, x1, #0x4a1
0x00003494: e2 ff ff b0              adrp     x2, #0
0x00003498: 42 c8 39 91              add      x2, x2, #0xe72
0x0000349c: c0 00 80 52              mov      w0, #6
0x000034a0: a8 00 00 94              bl       #0x3740                    ; -> __android_log_print@plt
0x000034a4: 95 00 80 12              mov      w21, #-5
0x000034a8: d2 ff ff 17              b        #0x33f0                    ; -> _ZN3Vad7processE24nnvad_wrapper_input_dataP20nnvad_wrapper_output+0x164
0x000034ac: f5 03 1f 2a              mov      w21, wzr
0x000034b0: c8 ff ff 17              b        #0x33d0                    ; -> _ZN3Vad7processE24nnvad_wrapper_input_dataP20nnvad_wrapper_output+0x144
0x000034b4: f5 03 1f 2a              mov      w21, wzr
0x000034b8: 9f 3a 00 b9              str      wzr, [x20, #0x38]
0x000034bc: c5 ff ff 17              b        #0x33d0                    ; -> _ZN3Vad7processE24nnvad_wrapper_input_dataP20nnvad_wrapper_output+0x144
0x000034c0: a6 00 00 94              bl       #0x3758
