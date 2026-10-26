; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libqvajni.so
; target  : address 0x15230
; start   : 0x15230
; size    : 0x650
; bytes   : 0x650   instructions: 404
; generated: 2026-10-23T23:38:30

0x00015230: 3f 23 03 d5              paciasp  
0x00015234: ff c3 04 d1              sub      sp, sp, #0x130
0x00015238: fd 7b 0d a9              stp      x29, x30, [sp, #0xd0]
0x0001523c: fc 6f 0e a9              stp      x28, x27, [sp, #0xe0]
0x00015240: fa 67 0f a9              stp      x26, x25, [sp, #0xf0]
0x00015244: f8 5f 10 a9              stp      x24, x23, [sp, #0x100]
0x00015248: f6 57 11 a9              stp      x22, x21, [sp, #0x110]
0x0001524c: f4 4f 12 a9              stp      x20, x19, [sp, #0x120]
0x00015250: fd 43 03 91              add      x29, sp, #0xd0
0x00015254: 5c d0 3b d5              mrs      x28, tpidr_el0
0x00015258: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x0001525c: 88 17 40 f9              ldr      x8, [x28, #0x28]
0x00015260: a8 03 1f f8              stur     x8, [x29, #-0x10]
0x00015264: bf 03 1e b8              stur     wzr, [x29, #-0x20]
0x00015268: a0 03 3d ad              stp      q0, q0, [x29, #-0x60]
0x0001526c: a0 03 3e ad              stp      q0, q0, [x29, #-0x40]
0x00015270: 82 0e 00 b4              cbz      x2, #0x15440
0x00015274: f3 03 04 aa              mov      x19, x4
0x00015278: 44 0e 00 b4              cbz      x4, #0x15440
0x0001527c: 08 00 40 f9              ldr      x8, [x0]
0x00015280: f9 03 01 aa              mov      x25, x1
0x00015284: 81 ff ff d0              adrp     x1, #0x7000
0x00015288: 21 38 27 91              add      x1, x1, #0x9ce
0x0001528c: f6 03 05 2a              mov      w22, w5
0x00015290: f7 03 03 aa              mov      x23, x3
0x00015294: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x00015298: f5 03 02 aa              mov      x21, x2
0x0001529c: f4 03 00 aa              mov      x20, x0
0x000152a0: 00 01 3f d6              blr      x8
0x000152a4: e0 0d 00 b4              cbz      x0, #0x15460
0x000152a8: 88 02 40 f9              ldr      x8, [x20]
0x000152ac: fa 03 00 aa              mov      x26, x0
0x000152b0: 62 ff ff f0              adrp     x2, #0x4000
0x000152b4: 42 54 35 91              add      x2, x2, #0xd55
0x000152b8: 83 ff ff b0              adrp     x3, #0x6000
0x000152bc: 63 5c 28 91              add      x3, x3, #0xa17
0x000152c0: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000152c4: e0 03 14 aa              mov      x0, x20
0x000152c8: e1 03 1a aa              mov      x1, x26
0x000152cc: 00 01 3f d6              blr      x8
0x000152d0: 88 02 40 f9              ldr      x8, [x20]
0x000152d4: f8 03 00 aa              mov      x24, x0
0x000152d8: 82 ff ff b0              adrp     x2, #0x6000
0x000152dc: 42 6c 28 91              add      x2, x2, #0xa1b
0x000152e0: 83 ff ff d0              adrp     x3, #0x7000
0x000152e4: 63 98 2d 91              add      x3, x3, #0xb66
0x000152e8: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000152ec: e0 03 14 aa              mov      x0, x20
0x000152f0: e1 03 1a aa              mov      x1, x26
0x000152f4: 00 01 3f d6              blr      x8
0x000152f8: f8 0b 00 b4              cbz      x24, #0x15474
0x000152fc: fa 03 00 aa              mov      x26, x0
0x00015300: a0 0b 00 b4              cbz      x0, #0x15474
0x00015304: e0 03 14 aa              mov      x0, x20
0x00015308: e1 03 15 aa              mov      x1, x21
0x0001530c: e2 03 18 aa              mov      x2, x24
0x00015310: 00 e4 ff 97              bl       #0xe310
0x00015314: 1f 1c 00 72              tst      w0, #0xff
0x00015318: 80 0d 00 54              b.eq     #0x154c8
0x0001531c: e0 03 14 aa              mov      x0, x20
0x00015320: e1 03 15 aa              mov      x1, x21
0x00015324: e2 03 1a aa              mov      x2, x26
0x00015328: da e5 ff 97              bl       #0xea90
0x0001532c: 88 02 40 f9              ldr      x8, [x20]
0x00015330: fa 03 00 aa              mov      x26, x0
0x00015334: e0 03 14 aa              mov      x0, x20
0x00015338: e1 03 1a aa              mov      x1, x26
0x0001533c: 08 ad 42 f9              ldr      x8, [x8, #0x558]
0x00015340: 00 01 3f d6              blr      x8
0x00015344: 18 78 1f 53              lsl      w24, w0, #1
0x00015348: fb 03 00 2a              mov      w27, w0
0x0001534c: 00 7f 40 93              sxtw     x0, w24
0x00015350: 6e 29 00 94              bl       #0x1f908
0x00015354: 88 02 40 f9              ldr      x8, [x20]
0x00015358: f5 03 00 aa              mov      x21, x0
0x0001535c: e0 03 14 aa              mov      x0, x20
0x00015360: e1 03 1a aa              mov      x1, x26
0x00015364: e2 03 1f 2a              mov      w2, wzr
0x00015368: e3 03 1b 2a              mov      w3, w27
0x0001536c: 08 29 43 f9              ldr      x8, [x8, #0x650]
0x00015370: e4 03 15 aa              mov      x4, x21
0x00015374: 00 01 3f d6              blr      x8
0x00015378: 88 02 40 f9              ldr      x8, [x20]
0x0001537c: 81 ff ff d0              adrp     x1, #0x7000
0x00015380: 21 3c 1b 91              add      x1, x1, #0x6cf
0x00015384: e0 03 14 aa              mov      x0, x20
0x00015388: ff 37 00 f9              str      xzr, [sp, #0x68]
0x0001538c: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x00015390: 00 01 3f d6              blr      x8
0x00015394: 40 0a 00 b4              cbz      x0, #0x154dc
0x00015398: 68 00 00 b0              adrp     x8, #0x22000
0x0001539c: 89 02 40 f9              ldr      x9, [x20]
0x000153a0: fb 03 00 aa              mov      x27, x0
0x000153a4: e0 03 14 aa              mov      x0, x20
0x000153a8: e1 03 19 aa              mov      x1, x25
0x000153ac: 02 f1 42 f9              ldr      x2, [x8, #0x5e0]
0x000153b0: 28 95 41 f9              ldr      x8, [x9, #0x328]
0x000153b4: 00 01 3f d6              blr      x8
0x000153b8: fa 03 00 aa              mov      x26, x0
0x000153bc: 43 2a 00 94              bl       #0x1fcc8
0x000153c0: f9 03 00 2a              mov      w25, w0
0x000153c4: 1f 08 00 71              cmp      w0, #2
0x000153c8: 61 0a 00 54              b.ne     #0x15514
0x000153cc: 88 02 40 f9              ldr      x8, [x20]
0x000153d0: 62 ff ff f0              adrp     x2, #0x4000
0x000153d4: 42 40 33 91              add      x2, x2, #0xcd0
0x000153d8: 63 ff ff f0              adrp     x3, #0x4000
0x000153dc: 63 c4 2b 91              add      x3, x3, #0xaf1
0x000153e0: e0 03 14 aa              mov      x0, x20
0x000153e4: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000153e8: e1 03 1b aa              mov      x1, x27
0x000153ec: fa 33 00 f9              str      x26, [sp, #0x60]
0x000153f0: fa 03 15 aa              mov      x26, x21
0x000153f4: f5 03 19 2a              mov      w21, w25
0x000153f8: 00 01 3f d6              blr      x8
0x000153fc: a0 07 00 b4              cbz      x0, #0x154f0
0x00015400: 37 08 00 b4              cbz      x23, #0x15504
0x00015404: 88 02 40 f9              ldr      x8, [x20]
0x00015408: f9 03 00 aa              mov      x25, x0
0x0001540c: 81 ff ff d0              adrp     x1, #0x7000
0x00015410: 21 90 1e 91              add      x1, x1, #0x7a4
0x00015414: e0 03 14 aa              mov      x0, x20
0x00015418: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x0001541c: 00 01 3f d6              blr      x8
0x00015420: e3 03 00 aa              mov      x3, x0
0x00015424: e0 03 14 aa              mov      x0, x20
0x00015428: e1 03 17 aa              mov      x1, x23
0x0001542c: e2 03 19 aa              mov      x2, x25
0x00015430: c2 e5 ff 97              bl       #0xeb38
0x00015434: 28 00 80 52              mov      w8, #1
0x00015438: e8 03 0d 29              stp      w8, w0, [sp, #0x68]
0x0001543c: 33 00 00 14              b        #0x15508                   ; -> Java_com_quicinc_voice_activation_algorithm_Algorithm_process+0x2d8
0x00015440: 81 ff ff f0              adrp     x1, #0x8000
0x00015444: 21 a8 0b 91              add      x1, x1, #0x2ea
0x00015448: a2 ff ff 90              adrp     x2, #0x9000
0x0001544c: 42 00 39 91              add      x2, x2, #0xe40
0x00015450: c0 00 80 52              mov      w0, #6
0x00015454: e5 28 00 94              bl       #0x1f7e8
0x00015458: 36 00 80 12              mov      w22, #-2
0x0001545c: 0d 00 00 14              b        #0x15490                   ; -> Java_com_quicinc_voice_activation_algorithm_Algorithm_process+0x260
0x00015460: 81 ff ff f0              adrp     x1, #0x8000
0x00015464: 21 a8 0b 91              add      x1, x1, #0x2ea
0x00015468: 62 ff ff f0              adrp     x2, #0x4000
0x0001546c: 42 1c 10 91              add      x2, x2, #0x407
0x00015470: 05 00 00 14              b        #0x15484                   ; -> Java_com_quicinc_voice_activation_algorithm_Algorithm_process+0x254
0x00015474: 81 ff ff f0              adrp     x1, #0x8000
0x00015478: 21 a8 0b 91              add      x1, x1, #0x2ea
0x0001547c: 82 ff ff d0              adrp     x2, #0x7000
0x00015480: 42 88 12 91              add      x2, x2, #0x4a2
0x00015484: c0 00 80 52              mov      w0, #6
0x00015488: d8 28 00 94              bl       #0x1f7e8
0x0001548c: 16 00 80 12              mov      w22, #-1
0x00015490: 88 17 40 f9              ldr      x8, [x28, #0x28]
0x00015494: a9 03 5f f8              ldur     x9, [x29, #-0x10]
0x00015498: 1f 01 09 eb              cmp      x8, x9
0x0001549c: 01 1f 00 54              b.ne     #0x1587c
0x000154a0: e0 03 16 2a              mov      w0, w22
0x000154a4: f4 4f 52 a9              ldp      x20, x19, [sp, #0x120]
0x000154a8: f6 57 51 a9              ldp      x22, x21, [sp, #0x110]
0x000154ac: f8 5f 50 a9              ldp      x24, x23, [sp, #0x100]
0x000154b0: fa 67 4f a9              ldp      x26, x25, [sp, #0xf0]
0x000154b4: fc 6f 4e a9              ldp      x28, x27, [sp, #0xe0]
0x000154b8: fd 7b 4d a9              ldp      x29, x30, [sp, #0xd0]
0x000154bc: ff c3 04 91              add      sp, sp, #0x130
0x000154c0: bf 23 03 d5              autiasp  
0x000154c4: c0 03 5f d6              ret      
0x000154c8: 81 ff ff f0              adrp     x1, #0x8000
0x000154cc: 21 a8 0b 91              add      x1, x1, #0x2ea
0x000154d0: 62 ff ff f0              adrp     x2, #0x4000
0x000154d4: 42 d8 2d 91              add      x2, x2, #0xb76
0x000154d8: de ff ff 17              b        #0x15450                   ; -> Java_com_quicinc_voice_activation_algorithm_Algorithm_process+0x220
0x000154dc: 81 ff ff f0              adrp     x1, #0x8000
0x000154e0: 21 a8 0b 91              add      x1, x1, #0x2ea
0x000154e4: 82 ff ff f0              adrp     x2, #0x8000
0x000154e8: 42 50 25 91              add      x2, x2, #0x954
0x000154ec: e6 ff ff 17              b        #0x15484                   ; -> Java_com_quicinc_voice_activation_algorithm_Algorithm_process+0x254
0x000154f0: 81 ff ff f0              adrp     x1, #0x8000
0x000154f4: 21 a8 0b 91              add      x1, x1, #0x2ea
0x000154f8: 82 ff ff b0              adrp     x2, #0x6000
0x000154fc: 42 a4 12 91              add      x2, x2, #0x4a9
0x00015500: e1 ff ff 17              b        #0x15484                   ; -> Java_com_quicinc_voice_activation_algorithm_Algorithm_process+0x254
0x00015504: ff 6b 00 b9              str      wzr, [sp, #0x68]
0x00015508: f9 03 15 2a              mov      w25, w21
0x0001550c: f5 03 1a aa              mov      x21, x26
0x00015510: fa 33 40 f9              ldr      x26, [sp, #0x60]
0x00015514: 88 02 40 f9              ldr      x8, [x20]
0x00015518: 82 ff ff 90              adrp     x2, #0x5000
0x0001551c: 42 ec 01 91              add      x2, x2, #0x7b
0x00015520: 83 ff ff f0              adrp     x3, #0x8000
0x00015524: 63 04 32 91              add      x3, x3, #0xc81
0x00015528: e0 03 14 aa              mov      x0, x20
0x0001552c: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x00015530: e1 03 1b aa              mov      x1, x27
0x00015534: 00 01 3f d6              blr      x8
0x00015538: 88 02 40 f9              ldr      x8, [x20]
0x0001553c: f7 03 00 aa              mov      x23, x0
0x00015540: 82 ff ff b0              adrp     x2, #0x6000
0x00015544: 42 28 2a 91              add      x2, x2, #0xa8a
0x00015548: 83 ff ff f0              adrp     x3, #0x8000
0x0001554c: 63 78 09 91              add      x3, x3, #0x25e
0x00015550: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x00015554: e0 03 14 aa              mov      x0, x20
0x00015558: e1 03 1b aa              mov      x1, x27
0x0001555c: 00 01 3f d6              blr      x8
0x00015560: 57 0c 00 b4              cbz      x23, #0x156e8
0x00015564: 20 0c 00 b4              cbz      x0, #0x156e8
0x00015568: 88 02 40 f9              ldr      x8, [x20]
0x0001556c: e0 0f 00 f9              str      x0, [sp, #0x18]
0x00015570: a1 ff ff 90              adrp     x1, #0x9000
0x00015574: 21 80 2d 91              add      x1, x1, #0xb60
0x00015578: e0 03 14 aa              mov      x0, x20
0x0001557c: f7 2f 00 f9              str      x23, [sp, #0x58]
0x00015580: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x00015584: 00 01 3f d6              blr      x8
0x00015588: 88 02 40 f9              ldr      x8, [x20]
0x0001558c: e0 33 00 f9              str      x0, [sp, #0x60]
0x00015590: a1 ff ff 90              adrp     x1, #0x9000
0x00015594: 21 a4 2d 91              add      x1, x1, #0xb69
0x00015598: e0 03 14 aa              mov      x0, x20
0x0001559c: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x000155a0: 00 01 3f d6              blr      x8
0x000155a4: 88 02 40 f9              ldr      x8, [x20]
0x000155a8: e0 2b 00 f9              str      x0, [sp, #0x50]
0x000155ac: 81 ff ff 90              adrp     x1, #0x5000
0x000155b0: 21 0c 39 91              add      x1, x1, #0xe43
0x000155b4: e0 03 14 aa              mov      x0, x20
0x000155b8: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x000155bc: 00 01 3f d6              blr      x8
0x000155c0: 88 02 40 f9              ldr      x8, [x20]
0x000155c4: e0 0b 00 f9              str      x0, [sp, #0x10]
0x000155c8: 81 ff ff 90              adrp     x1, #0x5000
0x000155cc: 21 40 39 91              add      x1, x1, #0xe50
0x000155d0: e0 03 14 aa              mov      x0, x20
0x000155d4: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x000155d8: 00 01 3f d6              blr      x8
0x000155dc: 88 02 40 f9              ldr      x8, [x20]
0x000155e0: e0 13 00 f9              str      x0, [sp, #0x20]
0x000155e4: 81 ff ff b0              adrp     x1, #0x6000
0x000155e8: 21 98 13 91              add      x1, x1, #0x4e6
0x000155ec: e0 03 14 aa              mov      x0, x20
0x000155f0: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x000155f4: 00 01 3f d6              blr      x8
0x000155f8: 88 02 40 f9              ldr      x8, [x20]
0x000155fc: e0 17 00 f9              str      x0, [sp, #0x28]
0x00015600: 81 ff ff b0              adrp     x1, #0x6000
0x00015604: 21 a0 04 91              add      x1, x1, #0x128
0x00015608: e0 03 14 aa              mov      x0, x20
0x0001560c: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x00015610: 00 01 3f d6              blr      x8
0x00015614: 88 02 40 f9              ldr      x8, [x20]
0x00015618: e0 1b 00 f9              str      x0, [sp, #0x30]
0x0001561c: 81 ff ff d0              adrp     x1, #0x7000
0x00015620: 21 6c 14 91              add      x1, x1, #0x51b
0x00015624: e0 03 14 aa              mov      x0, x20
0x00015628: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x0001562c: 00 01 3f d6              blr      x8
0x00015630: 88 02 40 f9              ldr      x8, [x20]
0x00015634: e0 27 00 f9              str      x0, [sp, #0x48]
0x00015638: 61 ff ff f0              adrp     x1, #0x4000
0x0001563c: 21 88 39 91              add      x1, x1, #0xe62
0x00015640: e0 03 14 aa              mov      x0, x20
0x00015644: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x00015648: 00 01 3f d6              blr      x8
0x0001564c: 88 02 40 f9              ldr      x8, [x20]
0x00015650: e0 23 00 f9              str      x0, [sp, #0x40]
0x00015654: 81 ff ff d0              adrp     x1, #0x7000
0x00015658: 21 b0 14 91              add      x1, x1, #0x52c
0x0001565c: e0 03 14 aa              mov      x0, x20
0x00015660: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x00015664: 00 01 3f d6              blr      x8
0x00015668: 88 02 40 f9              ldr      x8, [x20]
0x0001566c: e0 1f 00 f9              str      x0, [sp, #0x38]
0x00015670: 61 ff ff f0              adrp     x1, #0x4000
0x00015674: 21 d8 39 91              add      x1, x1, #0xe76
0x00015678: e0 03 14 aa              mov      x0, x20
0x0001567c: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x00015680: 00 01 3f d6              blr      x8
0x00015684: 88 02 40 f9              ldr      x8, [x20]
0x00015688: f7 03 00 aa              mov      x23, x0
0x0001568c: 81 ff ff d0              adrp     x1, #0x7000
0x00015690: 21 24 30 91              add      x1, x1, #0xc09
0x00015694: e0 03 14 aa              mov      x0, x20
0x00015698: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x0001569c: 00 01 3f d6              blr      x8
0x000156a0: fb 03 00 aa              mov      x27, x0
0x000156a4: e3 a3 01 91              add      x3, sp, #0x68
0x000156a8: a4 83 01 d1              sub      x4, x29, #0x60
0x000156ac: e0 03 1a aa              mov      x0, x26
0x000156b0: e1 03 15 aa              mov      x1, x21
0x000156b4: e2 03 18 2a              mov      w2, w24
0x000156b8: e5 03 16 2a              mov      w5, w22
0x000156bc: 6b 29 00 94              bl       #0x1fc68
0x000156c0: f6 03 00 2a              mov      w22, w0
0x000156c4: c0 01 00 34              cbz      w0, #0x156fc
0x000156c8: 81 ff ff f0              adrp     x1, #0x8000
0x000156cc: 21 a8 0b 91              add      x1, x1, #0x2ea
0x000156d0: 82 ff ff f0              adrp     x2, #0x8000
0x000156d4: 42 84 33 91              add      x2, x2, #0xce1
0x000156d8: c0 00 80 52              mov      w0, #6
0x000156dc: e3 03 16 2a              mov      w3, w22
0x000156e0: 42 28 00 94              bl       #0x1f7e8
0x000156e4: 62 00 00 14              b        #0x1586c                   ; -> Java_com_quicinc_voice_activation_algorithm_Algorithm_process+0x63c
0x000156e8: 81 ff ff f0              adrp     x1, #0x8000
0x000156ec: 21 a8 0b 91              add      x1, x1, #0x2ea
0x000156f0: 82 ff ff d0              adrp     x2, #0x7000
0x000156f4: 42 84 13 91              add      x2, x2, #0x4e1
0x000156f8: 63 ff ff 17              b        #0x15484                   ; -> Java_com_quicinc_voice_activation_algorithm_Algorithm_process+0x254
0x000156fc: fb 5f 00 a9              stp      x27, x23, [sp]
0x00015700: a4 03 5a b8              ldur     w4, [x29, #-0x60]
0x00015704: fb 5f 45 a9              ldp      x27, x23, [sp, #0x50]
0x00015708: e0 03 14 aa              mov      x0, x20
0x0001570c: e1 03 13 aa              mov      x1, x19
0x00015710: f8 6b 44 a9              ldp      x24, x26, [sp, #0x40]
0x00015714: e3 33 40 f9              ldr      x3, [sp, #0x60]
0x00015718: e2 03 17 aa              mov      x2, x23
0x0001571c: 7a e2 ff 97              bl       #0xe104
0x00015720: 3f 0f 00 71              cmp      w25, #3
0x00015724: 20 02 00 54              b.eq     #0x15768
0x00015728: 3f 07 00 71              cmp      w25, #1
0x0001572c: a1 05 00 54              b.ne     #0x157e0
0x00015730: a4 83 5d b8              ldur     w4, [x29, #-0x28]
0x00015734: e0 03 14 aa              mov      x0, x20
0x00015738: e1 03 13 aa              mov      x1, x19
0x0001573c: e2 03 17 aa              mov      x2, x23
0x00015740: e3 03 1a aa              mov      x3, x26
0x00015744: 70 e2 ff 97              bl       #0xe104
0x00015748: a4 c3 5d b8              ldur     w4, [x29, #-0x24]
0x0001574c: e0 03 14 aa              mov      x0, x20
0x00015750: e1 03 13 aa              mov      x1, x19
0x00015754: e2 03 17 aa              mov      x2, x23
0x00015758: e3 03 18 aa              mov      x3, x24
0x0001575c: 6a e2 ff 97              bl       #0xe104
0x00015760: a4 03 5e b8              ldur     w4, [x29, #-0x20]
0x00015764: 1a 00 00 14              b        #0x157cc                   ; -> Java_com_quicinc_voice_activation_algorithm_Algorithm_process+0x59c
0x00015768: a4 83 5c b8              ldur     w4, [x29, #-0x38]
0x0001576c: e0 03 14 aa              mov      x0, x20
0x00015770: e1 03 13 aa              mov      x1, x19
0x00015774: e2 03 17 aa              mov      x2, x23
0x00015778: e3 03 1a aa              mov      x3, x26
0x0001577c: 62 e2 ff 97              bl       #0xe104
0x00015780: a4 83 5b b8              ldur     w4, [x29, #-0x48]
0x00015784: e0 03 14 aa              mov      x0, x20
0x00015788: e1 03 13 aa              mov      x1, x19
0x0001578c: e2 03 17 aa              mov      x2, x23
0x00015790: e3 07 40 f9              ldr      x3, [sp, #8]
0x00015794: 5c e2 ff 97              bl       #0xe104
0x00015798: a4 c3 5b b8              ldur     w4, [x29, #-0x44]
0x0001579c: e0 03 14 aa              mov      x0, x20
0x000157a0: e1 03 13 aa              mov      x1, x19
0x000157a4: e2 03 17 aa              mov      x2, x23
0x000157a8: e3 03 40 f9              ldr      x3, [sp]
0x000157ac: 56 e2 ff 97              bl       #0xe104
0x000157b0: a4 c3 5c b8              ldur     w4, [x29, #-0x34]
0x000157b4: e0 03 14 aa              mov      x0, x20
0x000157b8: e1 03 13 aa              mov      x1, x19
0x000157bc: e2 03 17 aa              mov      x2, x23
0x000157c0: e3 03 18 aa              mov      x3, x24
0x000157c4: 50 e2 ff 97              bl       #0xe104
0x000157c8: a4 03 5d b8              ldur     w4, [x29, #-0x30]
0x000157cc: e3 1f 40 f9              ldr      x3, [sp, #0x38]
0x000157d0: e0 03 14 aa              mov      x0, x20
0x000157d4: e1 03 13 aa              mov      x1, x19
0x000157d8: e2 03 17 aa              mov      x2, x23
0x000157dc: 23 00 00 14              b        #0x15868                   ; -> Java_com_quicinc_voice_activation_algorithm_Algorithm_process+0x638
0x000157e0: 3f 0b 00 71              cmp      w25, #2
0x000157e4: 41 04 00 54              b.ne     #0x1586c
0x000157e8: a4 43 5a b8              ldur     w4, [x29, #-0x5c]
0x000157ec: e0 03 14 aa              mov      x0, x20
0x000157f0: e1 03 13 aa              mov      x1, x19
0x000157f4: e2 03 17 aa              mov      x2, x23
0x000157f8: e3 03 1b aa              mov      x3, x27
0x000157fc: 42 e2 ff 97              bl       #0xe104
0x00015800: e3 67 41 a9              ldp      x3, x25, [sp, #0x10]
0x00015804: e0 03 14 aa              mov      x0, x20
0x00015808: e1 03 13 aa              mov      x1, x19
0x0001580c: a0 83 5a bc              ldur     s0, [x29, #-0x58]
0x00015810: 00 c0 22 1e              fcvt     d0, s0
0x00015814: e2 03 19 aa              mov      x2, x25
0x00015818: 3b e2 ff 97              bl       #0xe104
0x0001581c: a0 c3 5a bc              ldur     s0, [x29, #-0x54]
0x00015820: e0 03 14 aa              mov      x0, x20
0x00015824: e1 03 13 aa              mov      x1, x19
0x00015828: e2 03 19 aa              mov      x2, x25
0x0001582c: e3 13 40 f9              ldr      x3, [sp, #0x20]
0x00015830: 00 c0 22 1e              fcvt     d0, s0
0x00015834: 34 e2 ff 97              bl       #0xe104
0x00015838: a0 03 5b bc              ldur     s0, [x29, #-0x50]
0x0001583c: e0 03 14 aa              mov      x0, x20
0x00015840: e1 03 13 aa              mov      x1, x19
0x00015844: e2 03 19 aa              mov      x2, x25
0x00015848: e3 17 40 f9              ldr      x3, [sp, #0x28]
0x0001584c: 00 c0 22 1e              fcvt     d0, s0
0x00015850: 2d e2 ff 97              bl       #0xe104
0x00015854: a4 43 5b b8              ldur     w4, [x29, #-0x4c]
0x00015858: e0 03 14 aa              mov      x0, x20
0x0001585c: e1 03 13 aa              mov      x1, x19
0x00015860: e2 03 17 aa              mov      x2, x23
0x00015864: e3 1b 40 f9              ldr      x3, [sp, #0x30]
0x00015868: 27 e2 ff 97              bl       #0xe104
0x0001586c: 35 e1 ff b4              cbz      x21, #0x15490
0x00015870: e0 03 15 aa              mov      x0, x21
0x00015874: fb 27 00 94              bl       #0x1f860                   ; -> free@plt
0x00015878: 06 ff ff 17              b        #0x15490                   ; -> Java_com_quicinc_voice_activation_algorithm_Algorithm_process+0x260
0x0001587c: 17 28 00 94              bl       #0x1f8d8
