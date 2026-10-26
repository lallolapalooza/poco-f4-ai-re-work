; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_paymentservice/inventory/libs/lib/arm64-v8a/libdigit_ocr.so
; target  : address 0x15468
; start   : 0x15468
; size    : 0x8cc
; bytes   : 0x8cc   instructions: 563
; generated: 2026-10-23T23:19:38

0x00015468: ff 43 00 d1              sub      sp, sp, #0x10
0x0001546c: f3 7b 00 a9              stp      x19, x30, [sp]
0x00015470: f3 03 00 aa              mov      x19, x0
0x00015474: 20 00 40 f9              ldr      x0, [x1]
0x00015478: 40 38 00 94              bl       #0x23578                   ; -> svm_load_model
0x0001547c: 60 02 00 f9              str      x0, [x19]
0x00015480: 1f 00 1f eb              cmp      x0, xzr
0x00015484: e0 13 9f 5a              csetm    w0, eq
0x00015488: f3 7b 40 a9              ldp      x19, x30, [sp]
0x0001548c: ff 43 00 91              add      sp, sp, #0x10
0x00015490: c0 03 5f d6              ret      
0x00015494: ff 83 00 d1              sub      sp, sp, #0x20
0x00015498: f3 7b 00 a9              stp      x19, x30, [sp]
0x0001549c: f3 03 00 aa              mov      x19, x0
0x000154a0: 20 00 40 f9              ldr      x0, [x1]
0x000154a4: e2 0f 00 f9              str      x2, [sp, #0x18]
0x000154a8: 9e 3d 00 94              bl       #0x24b20                   ; -> svm_load_cvt_model
0x000154ac: 60 02 00 f9              str      x0, [x19]
0x000154b0: 40 01 00 b4              cbz      x0, #0x154d8
0x000154b4: e2 0f 40 f9              ldr      x2, [sp, #0x18]
0x000154b8: 40 00 40 f9              ldr      x0, [x2]
0x000154bc: 99 3d 00 94              bl       #0x24b20                   ; -> svm_load_cvt_model
0x000154c0: 1f 00 1f eb              cmp      x0, xzr
0x000154c4: 60 06 00 f9              str      x0, [x19, #8]
0x000154c8: e0 13 9f 5a              csetm    w0, eq
0x000154cc: f3 7b 40 a9              ldp      x19, x30, [sp]
0x000154d0: ff 83 00 91              add      sp, sp, #0x20
0x000154d4: c0 03 5f d6              ret      
0x000154d8: 00 00 80 12              mov      w0, #-1
0x000154dc: fc ff ff 17              b        #0x154cc                   ; -> _ZN11CDigitalOCR9LoadModelESsSs+0x38
0x000154e0: ff 03 26 d1              sub      sp, sp, #0x980
0x000154e4: ff c3 00 d1              sub      sp, sp, #0x30
0x000154e8: f3 53 00 a9              stp      x19, x20, [sp]
0x000154ec: f3 e3 00 91              add      x19, sp, #0x38
0x000154f0: f5 5b 01 a9              stp      x21, x22, [sp, #0x10]
0x000154f4: f7 7b 02 a9              stp      x23, x30, [sp, #0x20]
0x000154f8: f6 03 04 aa              mov      x22, x4
0x000154fc: f7 03 00 aa              mov      x23, x0
0x00015500: e4 03 13 aa              mov      x4, x19
0x00015504: e0 c3 00 91              add      x0, sp, #0x30
0x00015508: 7f 02 00 f9              str      xzr, [x19]
0x0001550c: 7f 06 00 f9              str      xzr, [x19, #8]
0x00015510: 7f 0a 00 f9              str      xzr, [x19, #0x10]
0x00015514: b1 fe ff 97              bl       #0x14fd8                   ; -> _ZN12CFEATURE_HOG26ExtractGradientOrientationEPhiiRSt6vectorIfSaIfEE
0x00015518: f5 83 02 91              add      x21, sp, #0xa0
0x0001551c: 02 22 81 d2              mov      x2, #0x910
0x00015520: 01 00 80 52              mov      w1, #0
0x00015524: e0 03 15 aa              mov      x0, x21
0x00015528: 16 fa ff 97              bl       #0x13d80                   ; -> memset@plt
0x0001552c: 66 02 40 f9              ldr      x6, [x19]
0x00015530: 62 06 40 f9              ldr      x2, [x19, #8]
0x00015534: 42 00 06 cb              sub      x2, x2, x6
0x00015538: 47 fc 42 93              asr      x7, x2, #2
0x0001553c: c7 01 00 b4              cbz      x7, #0x15574
0x00015540: e3 03 15 aa              mov      x3, x21
0x00015544: 20 00 80 52              mov      w0, #1
0x00015548: 02 00 80 d2              mov      x2, #0
0x0001554c: 60 84 00 b8              str      w0, [x3], #8
0x00015550: 03 00 00 14              b        #0x1555c                   ; -> _ZN11CDigitalOCR13PredictNegPosEPhiiRi+0x7c
0x00015554: 45 04 00 11              add      w5, w2, #1
0x00015558: 65 80 1f b8              stur     w5, [x3, #-8]
0x0001555c: c0 78 62 bc              ldr      s0, [x6, x2, lsl #2]
0x00015560: 42 04 00 91              add      x2, x2, #1
0x00015564: ff 00 02 eb              cmp      x7, x2
0x00015568: 00 c0 22 1e              fcvt     d0, s0
0x0001556c: 60 04 01 fc              str      d0, [x3], #0x10
0x00015570: 21 ff ff 54              b.ne     #0x15554
0x00015574: 00 f6 07 6f              fmov     v0.2d, #-1.00000000
0x00015578: f4 43 01 91              add      x20, sp, #0x50
0x0001557c: e3 03 14 aa              mov      x3, x20
0x00015580: 86 82 00 91              add      x6, x20, #0x20
0x00015584: 85 c2 00 91              add      x5, x20, #0x30
0x00015588: 84 02 01 91              add      x4, x20, #0x40
0x0001558c: e0 06 40 f9              ldr      x0, [x23, #8]
0x00015590: e1 03 15 aa              mov      x1, x21
0x00015594: 60 04 81 3c              str      q0, [x3], #0x10
0x00015598: e2 03 14 aa              mov      x2, x20
0x0001559c: c0 00 80 3d              str      q0, [x6]
0x000155a0: 06 00 80 12              mov      w6, #-1
0x000155a4: a0 00 80 3d              str      q0, [x5]
0x000155a8: e6 a3 09 b9              str      w6, [sp, #0x9a0]
0x000155ac: 80 00 80 3d              str      q0, [x4]
0x000155b0: 60 00 80 3d              str      q0, [x3]
0x000155b4: 39 35 00 94              bl       #0x22a98                   ; -> svm_predict_probability
0x000155b8: 81 02 40 fd              ldr      d1, [x20]
0x000155bc: 60 02 40 f9              ldr      x0, [x19]
0x000155c0: 00 10 6c 1e              fmov     d0, #0.50000000
0x000155c4: 30 20 60 1e              fcmpe    d1, d0
0x000155c8: e1 d7 9f 1a              cset     w1, gt
0x000155cc: c1 02 00 b9              str      w1, [x22]
0x000155d0: 40 00 00 b4              cbz      x0, #0x155d8
0x000155d4: 95 66 00 94              bl       #0x2f028                   ; -> _ZdlPv
0x000155d8: f3 53 40 a9              ldp      x19, x20, [sp]
0x000155dc: f5 5b 41 a9              ldp      x21, x22, [sp, #0x10]
0x000155e0: f7 7b 42 a9              ldp      x23, x30, [sp, #0x20]
0x000155e4: ff c3 00 91              add      sp, sp, #0x30
0x000155e8: 00 00 80 52              mov      w0, #0
0x000155ec: ff 03 26 91              add      sp, sp, #0x980
0x000155f0: c0 03 5f d6              ret      
0x000155f4: 61 02 40 f9              ldr      x1, [x19]
0x000155f8: f3 03 00 aa              mov      x19, x0
0x000155fc: 61 00 00 b4              cbz      x1, #0x15608
0x00015600: e0 03 01 aa              mov      x0, x1
0x00015604: 89 66 00 94              bl       #0x2f028                   ; -> _ZdlPv
0x00015608: e0 03 13 aa              mov      x0, x19
0x0001560c: 91 e1 01 94              bl       #0x8dc50                   ; -> _Unwind_Resume
0x00015610: ff 43 01 d1              sub      sp, sp, #0x50
0x00015614: f3 53 00 a9              stp      x19, x20, [sp]
0x00015618: f5 5b 01 a9              stp      x21, x22, [sp, #0x10]
0x0001561c: f7 63 02 a9              stp      x23, x24, [sp, #0x20]
0x00015620: fe 1b 00 f9              str      x30, [sp, #0x30]
0x00015624: f4 03 00 aa              mov      x20, x0
0x00015628: f3 03 01 aa              mov      x19, x1
0x0001562c: 03 04 40 f9              ldr      x3, [x0, #8]
0x00015630: 00 08 40 f9              ldr      x0, [x0, #0x10]
0x00015634: 7f 00 00 eb              cmp      x3, x0
0x00015638: 20 03 00 54              b.eq     #0x1569c
0x0001563c: 63 00 00 b4              cbz      x3, #0x15648
0x00015640: 60 80 5f f8              ldur     x0, [x3, #-8]
0x00015644: 60 00 00 f9              str      x0, [x3]
0x00015648: 64 20 00 d1              sub      x4, x3, #8
0x0001564c: 63 20 00 91              add      x3, x3, #8
0x00015650: 84 00 13 cb              sub      x4, x4, x19
0x00015654: 83 06 00 f9              str      x3, [x20, #8]
0x00015658: 80 fc 43 93              asr      x0, x4, #3
0x0001565c: 55 00 40 b9              ldr      w21, [x2]
0x00015660: 54 04 40 b9              ldr      w20, [x2, #4]
0x00015664: c0 00 00 b4              cbz      x0, #0x1567c
0x00015668: 60 00 04 cb              sub      x0, x3, x4
0x0001566c: e1 03 13 aa              mov      x1, x19
0x00015670: 00 20 00 d1              sub      x0, x0, #8
0x00015674: e2 03 04 aa              mov      x2, x4
0x00015678: ce f9 ff 97              bl       #0x13db0                   ; -> memmove@plt
0x0001567c: 75 02 00 b9              str      w21, [x19]
0x00015680: 74 06 00 b9              str      w20, [x19, #4]
0x00015684: f3 53 40 a9              ldp      x19, x20, [sp]
0x00015688: f5 5b 41 a9              ldp      x21, x22, [sp, #0x10]
0x0001568c: f7 63 42 a9              ldp      x23, x24, [sp, #0x20]
0x00015690: fe 1b 40 f9              ldr      x30, [sp, #0x30]
0x00015694: ff 43 01 91              add      sp, sp, #0x50
0x00015698: c0 03 5f d6              ret      
0x0001569c: 81 02 40 f9              ldr      x1, [x20]
0x000156a0: 63 00 01 cb              sub      x3, x3, x1
0x000156a4: 60 fc 43 93              asr      x0, x3, #3
0x000156a8: 40 04 00 b4              cbz      x0, #0x15730
0x000156ac: 17 f8 7f d3              lsl      x23, x0, #1
0x000156b0: 1f 00 17 eb              cmp      x0, x23
0x000156b4: 69 05 00 54              b.ls     #0x15760
0x000156b8: 75 02 01 cb              sub      x21, x19, x1
0x000156bc: f8 00 80 92              mov      x24, #-8
0x000156c0: e0 03 18 aa              mov      x0, x24
0x000156c4: e2 27 00 f9              str      x2, [sp, #0x48]
0x000156c8: fb 69 00 94              bl       #0x2feb4                   ; -> _Znwm
0x000156cc: f7 03 00 aa              mov      x23, x0
0x000156d0: 81 02 40 f9              ldr      x1, [x20]
0x000156d4: e2 27 40 f9              ldr      x2, [sp, #0x48]
0x000156d8: 76 02 01 cb              sub      x22, x19, x1
0x000156dc: ff 02 15 ab              cmn      x23, x21
0x000156e0: 60 00 00 54              b.eq     #0x156ec
0x000156e4: 40 00 40 f9              ldr      x0, [x2]
0x000156e8: e0 6a 35 f8              str      x0, [x23, x21]
0x000156ec: c0 fe 43 93              asr      x0, x22, #3
0x000156f0: 00 03 00 b5              cbnz     x0, #0x15750
0x000156f4: 95 06 40 f9              ldr      x21, [x20, #8]
0x000156f8: d6 22 00 91              add      x22, x22, #8
0x000156fc: f6 02 16 8b              add      x22, x23, x22
0x00015700: b5 02 13 cb              sub      x21, x21, x19
0x00015704: a0 fe 43 93              asr      x0, x21, #3
0x00015708: a0 01 00 b5              cbnz     x0, #0x1573c
0x0001570c: 80 02 40 f9              ldr      x0, [x20]
0x00015710: d5 02 15 8b              add      x21, x22, x21
0x00015714: 40 00 00 b4              cbz      x0, #0x1571c
0x00015718: 44 66 00 94              bl       #0x2f028                   ; -> _ZdlPv
0x0001571c: e0 02 18 8b              add      x0, x23, x24
0x00015720: 97 02 00 f9              str      x23, [x20]
0x00015724: 95 06 00 f9              str      x21, [x20, #8]
0x00015728: 80 0a 00 f9              str      x0, [x20, #0x10]
0x0001572c: d6 ff ff 17              b        #0x15684                   ; -> _ZNSt6vectorI7OCR_STRSaIS0_EE13_M_insert_auxEN9__gnu_cxx17__normal_iteratorIPS0_S2_EERKS0_+0x74
0x00015730: 75 02 01 cb              sub      x21, x19, x1
0x00015734: 18 01 80 d2              mov      x24, #8
0x00015738: e2 ff ff 17              b        #0x156c0                   ; -> _ZNSt6vectorI7OCR_STRSaIS0_EE13_M_insert_auxEN9__gnu_cxx17__normal_iteratorIPS0_S2_EERKS0_+0xb0
0x0001573c: e0 03 16 aa              mov      x0, x22
0x00015740: e1 03 13 aa              mov      x1, x19
0x00015744: e2 03 15 aa              mov      x2, x21
0x00015748: 9a f9 ff 97              bl       #0x13db0                   ; -> memmove@plt
0x0001574c: f0 ff ff 17              b        #0x1570c                   ; -> _ZNSt6vectorI7OCR_STRSaIS0_EE13_M_insert_auxEN9__gnu_cxx17__normal_iteratorIPS0_S2_EERKS0_+0xfc
0x00015750: e0 03 17 aa              mov      x0, x23
0x00015754: e2 03 16 aa              mov      x2, x22
0x00015758: 96 f9 ff 97              bl       #0x13db0                   ; -> memmove@plt
0x0001575c: e6 ff ff 17              b        #0x156f4                   ; -> _ZNSt6vectorI7OCR_STRSaIS0_EE13_M_insert_auxEN9__gnu_cxx17__normal_iteratorIPS0_S2_EERKS0_+0xe4
0x00015760: 03 00 fc 92              mov      x3, #0x1fffffffffffffff
0x00015764: ff 02 03 eb              cmp      x23, x3
0x00015768: 88 fa ff 54              b.hi     #0x156b8
0x0001576c: 75 02 01 cb              sub      x21, x19, x1
0x00015770: 18 ec 7c d3              lsl      x24, x0, #4
0x00015774: f6 03 15 aa              mov      x22, x21
0x00015778: 37 fb ff b4              cbz      x23, #0x156dc
0x0001577c: d1 ff ff 17              b        #0x156c0                   ; -> _ZNSt6vectorI7OCR_STRSaIS0_EE13_M_insert_auxEN9__gnu_cxx17__normal_iteratorIPS0_S2_EERKS0_+0xb0
0x00015780: ff 43 26 d1              sub      sp, sp, #0x990
0x00015784: ff 43 01 d1              sub      sp, sp, #0x50
0x00015788: e8 27 03 6d              stp      d8, d9, [sp, #0x30]
0x0001578c: f3 53 00 a9              stp      x19, x20, [sp]
0x00015790: ea 2f 04 6d              stp      d10, d11, [sp, #0x40]
0x00015794: f4 a3 01 91              add      x20, sp, #0x68
0x00015798: f5 5b 01 a9              stp      x21, x22, [sp, #0x10]
0x0001579c: f7 7b 02 a9              stp      x23, x30, [sp, #0x20]
0x000157a0: f5 03 04 aa              mov      x21, x4
0x000157a4: f7 03 00 aa              mov      x23, x0
0x000157a8: e4 03 14 aa              mov      x4, x20
0x000157ac: e0 63 01 91              add      x0, sp, #0x58
0x000157b0: 9f 02 00 f9              str      xzr, [x20]
0x000157b4: 9f 06 00 f9              str      xzr, [x20, #8]
0x000157b8: 9f 0a 00 f9              str      xzr, [x20, #0x10]
0x000157bc: 07 fe ff 97              bl       #0x14fd8                   ; -> _ZN12CFEATURE_HOG26ExtractGradientOrientationEPhiiRSt6vectorIfSaIfEE
0x000157c0: f6 43 03 91              add      x22, sp, #0xd0
0x000157c4: 02 22 81 d2              mov      x2, #0x910
0x000157c8: 01 00 80 52              mov      w1, #0
0x000157cc: e0 03 16 aa              mov      x0, x22
0x000157d0: 6c f9 ff 97              bl       #0x13d80                   ; -> memset@plt
0x000157d4: 86 02 40 f9              ldr      x6, [x20]
0x000157d8: 82 06 40 f9              ldr      x2, [x20, #8]
0x000157dc: 42 00 06 cb              sub      x2, x2, x6
0x000157e0: 47 fc 42 93              asr      x7, x2, #2
0x000157e4: c7 01 00 b4              cbz      x7, #0x1581c
0x000157e8: e3 03 16 aa              mov      x3, x22
0x000157ec: 20 00 80 52              mov      w0, #1
0x000157f0: 02 00 80 d2              mov      x2, #0
0x000157f4: 60 84 00 b8              str      w0, [x3], #8
0x000157f8: 03 00 00 14              b        #0x15804                   ; -> _ZN11CDigitalOCR7PredictEPhiiRSt6vectorI7OCR_STRSaIS2_EE+0x84
0x000157fc: 45 04 00 11              add      w5, w2, #1
0x00015800: 65 80 1f b8              stur     w5, [x3, #-8]
0x00015804: c0 78 62 bc              ldr      s0, [x6, x2, lsl #2]
0x00015808: 42 04 00 91              add      x2, x2, #1
0x0001580c: ff 00 02 eb              cmp      x7, x2
0x00015810: 00 c0 22 1e              fcvt     d0, s0
0x00015814: 60 04 01 fc              str      d0, [x3], #0x10
0x00015818: 21 ff ff 54              b.ne     #0x157fc
0x0001581c: 00 f6 07 6f              fmov     v0.2d, #-1.00000000
0x00015820: f3 03 02 91              add      x19, sp, #0x80
0x00015824: e3 03 13 aa              mov      x3, x19
0x00015828: 66 82 00 91              add      x6, x19, #0x20
0x0001582c: 65 c2 00 91              add      x5, x19, #0x30
0x00015830: 64 02 01 91              add      x4, x19, #0x40
0x00015834: e0 02 40 f9              ldr      x0, [x23]
0x00015838: e1 03 16 aa              mov      x1, x22
0x0001583c: 60 04 81 3c              str      q0, [x3], #0x10
0x00015840: e2 03 13 aa              mov      x2, x19
0x00015844: c0 00 80 3d              str      q0, [x6]
0x00015848: 06 00 80 12              mov      w6, #-1
0x0001584c: a0 00 80 3d              str      q0, [x5]
0x00015850: e6 d3 09 b9              str      w6, [sp, #0x9d0]
0x00015854: 80 00 80 3d              str      q0, [x4]
0x00015858: 60 00 80 3d              str      q0, [x3]
0x0001585c: 8f 34 00 94              bl       #0x22a98                   ; -> svm_predict_probability
0x00015860: 68 02 40 fd              ldr      d8, [x19]
0x00015864: e0 03 27 1e              fmov     s0, wzr
0x00015868: 18 21 60 1e              fcmpe    d8, #0.0
0x0001586c: 4d 00 00 54              b.le     #0x15874
0x00015870: 00 41 62 1e              fcvt     s0, d8
0x00015874: 01 c0 22 1e              fcvt     d1, s0
0x00015878: 03 00 80 52              mov      w3, #0
0x0001587c: 72 06 40 fd              ldr      d18, [x19, #8]
0x00015880: 30 20 72 1e              fcmpe    d1, d18
0x00015884: 85 00 00 54              b.pl     #0x15894
0x00015888: 40 42 62 1e              fcvt     s0, d18
0x0001588c: 23 00 80 52              mov      w3, #1
0x00015890: 01 c0 22 1e              fcvt     d1, s0
0x00015894: 71 0a 40 fd              ldr      d17, [x19, #0x10]
0x00015898: 30 20 71 1e              fcmpe    d1, d17
0x0001589c: 85 00 00 54              b.pl     #0x158ac
0x000158a0: 20 42 62 1e              fcvt     s0, d17
0x000158a4: 43 00 80 52              mov      w3, #2
0x000158a8: 01 c0 22 1e              fcvt     d1, s0
0x000158ac: 70 0e 40 fd              ldr      d16, [x19, #0x18]
0x000158b0: 30 20 70 1e              fcmpe    d1, d16
0x000158b4: 85 00 00 54              b.pl     #0x158c4
0x000158b8: 00 42 62 1e              fcvt     s0, d16
0x000158bc: 63 00 80 52              mov      w3, #3
0x000158c0: 01 c0 22 1e              fcvt     d1, s0
0x000158c4: 67 12 40 fd              ldr      d7, [x19, #0x20]
0x000158c8: 30 20 67 1e              fcmpe    d1, d7
0x000158cc: 85 00 00 54              b.pl     #0x158dc
0x000158d0: e0 40 62 1e              fcvt     s0, d7
0x000158d4: 83 00 80 52              mov      w3, #4
0x000158d8: 01 c0 22 1e              fcvt     d1, s0
0x000158dc: 66 16 40 fd              ldr      d6, [x19, #0x28]
0x000158e0: 30 20 66 1e              fcmpe    d1, d6
0x000158e4: 85 00 00 54              b.pl     #0x158f4
0x000158e8: c0 40 62 1e              fcvt     s0, d6
0x000158ec: a3 00 80 52              mov      w3, #5
0x000158f0: 01 c0 22 1e              fcvt     d1, s0
0x000158f4: 65 1a 40 fd              ldr      d5, [x19, #0x30]
0x000158f8: 30 20 65 1e              fcmpe    d1, d5
0x000158fc: 85 00 00 54              b.pl     #0x1590c
0x00015900: a0 40 62 1e              fcvt     s0, d5
0x00015904: c3 00 80 52              mov      w3, #6
0x00015908: 01 c0 22 1e              fcvt     d1, s0
0x0001590c: 64 1e 40 fd              ldr      d4, [x19, #0x38]
0x00015910: 30 20 64 1e              fcmpe    d1, d4
0x00015914: 85 00 00 54              b.pl     #0x15924
0x00015918: 80 40 62 1e              fcvt     s0, d4
0x0001591c: e3 00 80 52              mov      w3, #7
0x00015920: 01 c0 22 1e              fcvt     d1, s0
0x00015924: 63 22 40 fd              ldr      d3, [x19, #0x40]
0x00015928: 30 20 63 1e              fcmpe    d1, d3
0x0001592c: 45 01 00 54              b.pl     #0x15954
0x00015930: 60 40 62 1e              fcvt     s0, d3
0x00015934: 03 01 80 52              mov      w3, #8
0x00015938: 62 26 40 fd              ldr      d2, [x19, #0x48]
0x0001593c: 01 c0 22 1e              fcvt     d1, s0
0x00015940: 30 20 62 1e              fcmpe    d1, d2
0x00015944: 05 01 00 54              b.pl     #0x15964
0x00015948: 40 40 62 1e              fcvt     s0, d2
0x0001594c: 23 01 80 52              mov      w3, #9
0x00015950: 05 00 00 14              b        #0x15964                   ; -> _ZN11CDigitalOCR7PredictEPhiiRSt6vectorI7OCR_STRSaIS2_EE+0x1e4
0x00015954: 62 26 40 fd              ldr      d2, [x19, #0x48]
0x00015958: 30 20 62 1e              fcmpe    d1, d2
0x0001595c: 64 ff ff 54              b.mi     #0x15948
0x00015960: 63 1e 00 34              cbz      w3, #0x15d2c
0x00015964: 18 21 60 1e              fcmpe    d8, #0.0
0x00015968: e9 03 27 1e              fmov     s9, wzr
0x0001596c: 4d 00 00 54              b.le     #0x15974
0x00015970: 09 41 62 1e              fcvt     s9, d8
0x00015974: 7f 04 00 71              cmp      w3, #1
0x00015978: 60 1c 00 54              b.eq     #0x15d04
0x0001597c: 21 c1 22 1e              fcvt     d1, s9
0x00015980: 13 00 80 52              mov      w19, #0
0x00015984: 30 20 72 1e              fcmpe    d1, d18
0x00015988: 85 00 00 54              b.pl     #0x15998
0x0001598c: 49 42 62 1e              fcvt     s9, d18
0x00015990: 33 00 80 52              mov      w19, #1
0x00015994: 21 c1 22 1e              fcvt     d1, s9
0x00015998: 7f 08 00 71              cmp      w3, #2
0x0001599c: 00 01 00 54              b.eq     #0x159bc
0x000159a0: 30 20 71 1e              fcmpe    d1, d17
0x000159a4: 85 00 00 54              b.pl     #0x159b4
0x000159a8: 29 42 62 1e              fcvt     s9, d17
0x000159ac: 53 00 80 52              mov      w19, #2
0x000159b0: 21 c1 22 1e              fcvt     d1, s9
0x000159b4: 7f 0c 00 71              cmp      w3, #3
0x000159b8: 00 01 00 54              b.eq     #0x159d8
0x000159bc: 30 20 70 1e              fcmpe    d1, d16
0x000159c0: 85 00 00 54              b.pl     #0x159d0
0x000159c4: 09 42 62 1e              fcvt     s9, d16
0x000159c8: 73 00 80 52              mov      w19, #3
0x000159cc: 21 c1 22 1e              fcvt     d1, s9
0x000159d0: 7f 10 00 71              cmp      w3, #4
0x000159d4: 00 01 00 54              b.eq     #0x159f4
0x000159d8: 30 20 67 1e              fcmpe    d1, d7
0x000159dc: 85 00 00 54              b.pl     #0x159ec
0x000159e0: e9 40 62 1e              fcvt     s9, d7
0x000159e4: 93 00 80 52              mov      w19, #4
0x000159e8: 21 c1 22 1e              fcvt     d1, s9
0x000159ec: 7f 14 00 71              cmp      w3, #5
0x000159f0: 00 01 00 54              b.eq     #0x15a10
0x000159f4: 30 20 66 1e              fcmpe    d1, d6
0x000159f8: 85 00 00 54              b.pl     #0x15a08
0x000159fc: c9 40 62 1e              fcvt     s9, d6
0x00015a00: b3 00 80 52              mov      w19, #5
0x00015a04: 21 c1 22 1e              fcvt     d1, s9
0x00015a08: 7f 18 00 71              cmp      w3, #6
0x00015a0c: 00 01 00 54              b.eq     #0x15a2c
0x00015a10: 30 20 65 1e              fcmpe    d1, d5
0x00015a14: 85 00 00 54              b.pl     #0x15a24
0x00015a18: a9 40 62 1e              fcvt     s9, d5
0x00015a1c: d3 00 80 52              mov      w19, #6
0x00015a20: 21 c1 22 1e              fcvt     d1, s9
0x00015a24: 7f 1c 00 71              cmp      w3, #7
0x00015a28: 00 01 00 54              b.eq     #0x15a48
0x00015a2c: 30 20 64 1e              fcmpe    d1, d4
0x00015a30: 85 00 00 54              b.pl     #0x15a40
0x00015a34: 89 40 62 1e              fcvt     s9, d4
0x00015a38: f3 00 80 52              mov      w19, #7
0x00015a3c: 21 c1 22 1e              fcvt     d1, s9
0x00015a40: 7f 20 00 71              cmp      w3, #8
0x00015a44: 00 01 00 54              b.eq     #0x15a64
0x00015a48: 30 20 63 1e              fcmpe    d1, d3
0x00015a4c: 65 00 00 54              b.pl     #0x15a58
0x00015a50: 69 40 62 1e              fcvt     s9, d3
0x00015a54: 13 01 80 52              mov      w19, #8
0x00015a58: 7f 24 00 71              cmp      w3, #9
0x00015a5c: c0 00 00 54              b.eq     #0x15a74
0x00015a60: 21 c1 22 1e              fcvt     d1, s9
0x00015a64: 50 20 61 1e              fcmpe    d2, d1
0x00015a68: 6d 00 00 54              b.le     #0x15a74
0x00015a6c: 49 40 62 1e              fcvt     s9, d2
0x00015a70: 33 01 80 52              mov      w19, #9
0x00015a74: 73 12 00 34              cbz      w19, #0x15cc0
0x00015a78: 43 12 00 34              cbz      w3, #0x15cc0
0x00015a7c: 18 21 60 1e              fcmpe    d8, #0.0
0x00015a80: 0d 12 00 54              b.le     #0x15cc0
0x00015a84: 7f 06 00 71              cmp      w19, #1
0x00015a88: 08 41 62 1e              fcvt     s8, d8
0x00015a8c: 00 12 00 54              b.eq     #0x15ccc
0x00015a90: 7f 04 00 71              cmp      w3, #1
0x00015a94: f7 17 9f 1a              cset     w23, eq
0x00015a98: b7 11 00 35              cbnz     w23, #0x15ccc
0x00015a9c: 01 c1 22 1e              fcvt     d1, s8
0x00015aa0: 30 20 72 1e              fcmpe    d1, d18
0x00015aa4: 65 00 00 54              b.pl     #0x15ab0
0x00015aa8: 48 42 62 1e              fcvt     s8, d18
0x00015aac: 37 00 80 52              mov      w23, #1
0x00015ab0: 7f 0a 00 71              cmp      w19, #2
0x00015ab4: 00 01 00 54              b.eq     #0x15ad4
0x00015ab8: 7f 08 00 71              cmp      w3, #2
0x00015abc: c0 00 00 54              b.eq     #0x15ad4
0x00015ac0: 01 c1 22 1e              fcvt     d1, s8
0x00015ac4: 30 20 71 1e              fcmpe    d1, d17
0x00015ac8: 65 00 00 54              b.pl     #0x15ad4
0x00015acc: 28 42 62 1e              fcvt     s8, d17
0x00015ad0: 57 00 80 52              mov      w23, #2
0x00015ad4: 7f 0e 00 71              cmp      w19, #3
0x00015ad8: 00 01 00 54              b.eq     #0x15af8
0x00015adc: 7f 0c 00 71              cmp      w3, #3
0x00015ae0: c0 00 00 54              b.eq     #0x15af8
0x00015ae4: 01 c1 22 1e              fcvt     d1, s8
0x00015ae8: 30 20 70 1e              fcmpe    d1, d16
0x00015aec: 65 00 00 54              b.pl     #0x15af8
0x00015af0: 08 42 62 1e              fcvt     s8, d16
0x00015af4: 77 00 80 52              mov      w23, #3
0x00015af8: 7f 12 00 71              cmp      w19, #4
0x00015afc: 00 01 00 54              b.eq     #0x15b1c
0x00015b00: 7f 10 00 71              cmp      w3, #4
0x00015b04: c0 00 00 54              b.eq     #0x15b1c
0x00015b08: 01 c1 22 1e              fcvt     d1, s8
0x00015b0c: 30 20 67 1e              fcmpe    d1, d7
0x00015b10: 65 00 00 54              b.pl     #0x15b1c
0x00015b14: e8 40 62 1e              fcvt     s8, d7
0x00015b18: 97 00 80 52              mov      w23, #4
0x00015b1c: 7f 16 00 71              cmp      w19, #5
0x00015b20: 00 01 00 54              b.eq     #0x15b40
0x00015b24: 7f 14 00 71              cmp      w3, #5
0x00015b28: c0 00 00 54              b.eq     #0x15b40
0x00015b2c: 01 c1 22 1e              fcvt     d1, s8
0x00015b30: 30 20 66 1e              fcmpe    d1, d6
0x00015b34: 65 00 00 54              b.pl     #0x15b40
0x00015b38: c8 40 62 1e              fcvt     s8, d6
0x00015b3c: b7 00 80 52              mov      w23, #5
0x00015b40: 7f 1a 00 71              cmp      w19, #6
0x00015b44: 00 01 00 54              b.eq     #0x15b64
0x00015b48: 7f 18 00 71              cmp      w3, #6
0x00015b4c: c0 00 00 54              b.eq     #0x15b64
0x00015b50: 01 c1 22 1e              fcvt     d1, s8
0x00015b54: 30 20 65 1e              fcmpe    d1, d5
0x00015b58: 65 00 00 54              b.pl     #0x15b64
0x00015b5c: a8 40 62 1e              fcvt     s8, d5
0x00015b60: d7 00 80 52              mov      w23, #6
0x00015b64: 7f 1e 00 71              cmp      w19, #7
0x00015b68: 00 01 00 54              b.eq     #0x15b88
0x00015b6c: 7f 1c 00 71              cmp      w3, #7
0x00015b70: c0 00 00 54              b.eq     #0x15b88
0x00015b74: 01 c1 22 1e              fcvt     d1, s8
0x00015b78: 30 20 64 1e              fcmpe    d1, d4
0x00015b7c: 65 00 00 54              b.pl     #0x15b88
0x00015b80: 88 40 62 1e              fcvt     s8, d4
0x00015b84: f7 00 80 52              mov      w23, #7
0x00015b88: 7f 22 00 71              cmp      w19, #8
0x00015b8c: 00 01 00 54              b.eq     #0x15bac
0x00015b90: 7f 20 00 71              cmp      w3, #8
0x00015b94: c0 00 00 54              b.eq     #0x15bac
0x00015b98: 01 c1 22 1e              fcvt     d1, s8
0x00015b9c: 30 20 63 1e              fcmpe    d1, d3
0x00015ba0: 65 00 00 54              b.pl     #0x15bac
0x00015ba4: 68 40 62 1e              fcvt     s8, d3
0x00015ba8: 17 01 80 52              mov      w23, #8
0x00015bac: 7f 26 00 71              cmp      w19, #9
0x00015bb0: 00 01 00 54              b.eq     #0x15bd0
0x00015bb4: 7f 24 00 71              cmp      w3, #9
0x00015bb8: c0 00 00 54              b.eq     #0x15bd0
0x00015bbc: 01 c1 22 1e              fcvt     d1, s8
0x00015bc0: 50 20 61 1e              fcmpe    d2, d1
0x00015bc4: 6d 00 00 54              b.le     #0x15bd0
0x00015bc8: 48 40 62 1e              fcvt     s8, d2
0x00015bcc: 37 01 80 52              mov      w23, #9
0x00015bd0: 2a 0b 00 1c              ldr      s10, #0x15d34
0x00015bd4: f6 83 01 91              add      x22, sp, #0x60
0x00015bd8: c3 02 00 b9              str      w3, [x22]
0x00015bdc: 00 08 2a 1e              fmul     s0, s0, s10
0x00015be0: a1 06 40 f9              ldr      x1, [x21, #8]
0x00015be4: a0 0a 40 f9              ldr      x0, [x21, #0x10]
0x00015be8: 3f 00 00 eb              cmp      x1, x0
0x00015bec: 03 00 38 1e              fcvtzs   w3, s0
0x00015bf0: c3 06 00 b9              str      w3, [x22, #4]
0x00015bf4: 00 07 00 54              b.eq     #0x15cd4
0x00015bf8: 61 00 00 b4              cbz      x1, #0x15c04
0x00015bfc: c0 02 40 f9              ldr      x0, [x22]
0x00015c00: 20 00 00 f9              str      x0, [x1]
0x00015c04: 21 20 00 91              add      x1, x1, #8
0x00015c08: a1 06 00 f9              str      x1, [x21, #8]
0x00015c0c: 20 c1 22 1e              fcvt     d0, s9
0x00015c10: 4b 09 00 5c              ldr      d11, #0x15d38
0x00015c14: 10 20 6b 1e              fcmpe    d0, d11
0x00015c18: cd 01 00 54              b.le     #0x15c50
0x00015c1c: 29 09 2a 1e              fmul     s9, s9, s10
0x00015c20: a0 0a 40 f9              ldr      x0, [x21, #0x10]
0x00015c24: a1 06 40 f9              ldr      x1, [x21, #8]
0x00015c28: d3 02 00 b9              str      w19, [x22]
0x00015c2c: 3f 00 00 eb              cmp      x1, x0
0x00015c30: 20 01 38 1e              fcvtzs   w0, s9
0x00015c34: c0 06 00 b9              str      w0, [x22, #4]
0x00015c38: e0 05 00 54              b.eq     #0x15cf4
0x00015c3c: 61 00 00 b4              cbz      x1, #0x15c48
0x00015c40: c0 02 40 f9              ldr      x0, [x22]
0x00015c44: 20 00 00 f9              str      x0, [x1]
0x00015c48: 21 20 00 91              add      x1, x1, #8
0x00015c4c: a1 06 00 f9              str      x1, [x21, #8]
0x00015c50: 00 c1 22 1e              fcvt     d0, s8
0x00015c54: 10 20 6b 1e              fcmpe    d0, d11
0x00015c58: cd 01 00 54              b.le     #0x15c90
0x00015c5c: 08 09 2a 1e              fmul     s8, s8, s10
0x00015c60: a0 0a 40 f9              ldr      x0, [x21, #0x10]
0x00015c64: a1 06 40 f9              ldr      x1, [x21, #8]
0x00015c68: d7 02 00 b9              str      w23, [x22]
0x00015c6c: 3f 00 00 eb              cmp      x1, x0
0x00015c70: 00 01 38 1e              fcvtzs   w0, s8
0x00015c74: c0 06 00 b9              str      w0, [x22, #4]
0x00015c78: 60 03 00 54              b.eq     #0x15ce4
0x00015c7c: 61 00 00 b4              cbz      x1, #0x15c88
0x00015c80: c0 02 40 f9              ldr      x0, [x22]
0x00015c84: 20 00 00 f9              str      x0, [x1]
0x00015c88: 21 20 00 91              add      x1, x1, #8
0x00015c8c: a1 06 00 f9              str      x1, [x21, #8]
0x00015c90: 80 02 40 f9              ldr      x0, [x20]
0x00015c94: 40 00 00 b4              cbz      x0, #0x15c9c
0x00015c98: e4 64 00 94              bl       #0x2f028                   ; -> _ZdlPv
0x00015c9c: f3 53 40 a9              ldp      x19, x20, [sp]
0x00015ca0: e8 27 43 6d              ldp      d8, d9, [sp, #0x30]
0x00015ca4: f5 5b 41 a9              ldp      x21, x22, [sp, #0x10]
0x00015ca8: ea 2f 44 6d              ldp      d10, d11, [sp, #0x40]
0x00015cac: f7 7b 42 a9              ldp      x23, x30, [sp, #0x20]
0x00015cb0: ff 43 01 91              add      sp, sp, #0x50
0x00015cb4: 00 00 80 52              mov      w0, #0
0x00015cb8: ff 43 26 91              add      sp, sp, #0x990
0x00015cbc: c0 03 5f d6              ret      
0x00015cc0: 7f 06 00 71              cmp      w19, #1
0x00015cc4: e8 03 27 1e              fmov     s8, wzr
0x00015cc8: 41 ee ff 54              b.ne     #0x15a90
0x00015ccc: 17 00 80 52              mov      w23, #0
0x00015cd0: 78 ff ff 17              b        #0x15ab0                   ; -> _ZN11CDigitalOCR7PredictEPhiiRSt6vectorI7OCR_STRSaIS2_EE+0x330
0x00015cd4: e0 03 15 aa              mov      x0, x21
0x00015cd8: e2 03 16 aa              mov      x2, x22
0x00015cdc: 4d fe ff 97              bl       #0x15610                   ; -> _ZNSt6vectorI7OCR_STRSaIS0_EE13_M_insert_auxEN9__gnu_cxx17__normal_iteratorIPS0_S2_EERKS0_
0x00015ce0: cb ff ff 17              b        #0x15c0c                   ; -> _ZN11CDigitalOCR7PredictEPhiiRSt6vectorI7OCR_STRSaIS2_EE+0x48c
0x00015ce4: e0 03 15 aa              mov      x0, x21
0x00015ce8: e2 03 16 aa              mov      x2, x22
0x00015cec: 49 fe ff 97              bl       #0x15610                   ; -> _ZNSt6vectorI7OCR_STRSaIS0_EE13_M_insert_auxEN9__gnu_cxx17__normal_iteratorIPS0_S2_EERKS0_
0x00015cf0: e8 ff ff 17              b        #0x15c90                   ; -> _ZN11CDigitalOCR7PredictEPhiiRSt6vectorI7OCR_STRSaIS2_EE+0x510
0x00015cf4: e0 03 15 aa              mov      x0, x21
0x00015cf8: e2 03 16 aa              mov      x2, x22
0x00015cfc: 45 fe ff 97              bl       #0x15610                   ; -> _ZNSt6vectorI7OCR_STRSaIS0_EE13_M_insert_auxEN9__gnu_cxx17__normal_iteratorIPS0_S2_EERKS0_
0x00015d00: d4 ff ff 17              b        #0x15c50                   ; -> _ZN11CDigitalOCR7PredictEPhiiRSt6vectorI7OCR_STRSaIS2_EE+0x4d0
0x00015d04: 21 c1 22 1e              fcvt     d1, s9
0x00015d08: 13 00 80 52              mov      w19, #0
0x00015d0c: 25 ff ff 17              b        #0x159a0                   ; -> _ZN11CDigitalOCR7PredictEPhiiRSt6vectorI7OCR_STRSaIS2_EE+0x220
0x00015d10: 81 02 40 f9              ldr      x1, [x20]
0x00015d14: f3 03 00 aa              mov      x19, x0
0x00015d18: 61 00 00 b4              cbz      x1, #0x15d24
0x00015d1c: e0 03 01 aa              mov      x0, x1
0x00015d20: c2 64 00 94              bl       #0x2f028                   ; -> _ZdlPv
0x00015d24: e0 03 13 aa              mov      x0, x19
0x00015d28: ca df 01 94              bl       #0x8dc50                   ; -> _Unwind_Resume
0x00015d2c: e9 03 27 1e              fmov     s9, wzr
0x00015d30: 13 ff ff 17              b        #0x1597c                   ; -> _ZN11CDigitalOCR7PredictEPhiiRSt6vectorI7OCR_STRSaIS2_EE+0x1fc
