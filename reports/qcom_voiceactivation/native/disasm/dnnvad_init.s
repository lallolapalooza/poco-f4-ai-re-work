; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libdnnvad_jni.so
; target  : Java_com_qualcomm_qti_vad_DNNVAD_init (0x233c)
; start   : 0x233c
; size    : 0x434
; bytes   : 0x434   instructions: 269
; generated: 2026-10-23T23:26:51

0x0000233c: 3f 23 03 d5              paciasp  
0x00002340: ff c3 01 d1              sub      sp, sp, #0x70
0x00002344: fd 7b 01 a9              stp      x29, x30, [sp, #0x10]
0x00002348: fc 6f 02 a9              stp      x28, x27, [sp, #0x20]
0x0000234c: fa 67 03 a9              stp      x26, x25, [sp, #0x30]
0x00002350: f8 5f 04 a9              stp      x24, x23, [sp, #0x40]
0x00002354: f6 57 05 a9              stp      x22, x21, [sp, #0x50]
0x00002358: f4 4f 06 a9              stp      x20, x19, [sp, #0x60]
0x0000235c: fd 43 00 91              add      x29, sp, #0x10
0x00002360: 5b d0 3b d5              mrs      x27, tpidr_el0
0x00002364: f6 03 02 aa              mov      x22, x2
0x00002368: f4 03 01 aa              mov      x20, x1
0x0000236c: 68 17 40 f9              ldr      x8, [x27, #0x28]
0x00002370: f5 03 00 aa              mov      x21, x0
0x00002374: e1 ff ff f0              adrp     x1, #0x1000
0x00002378: 21 fc 1c 91              add      x1, x1, #0x73f
0x0000237c: e2 ff ff f0              adrp     x2, #0x1000
0x00002380: 42 d8 0a 91              add      x2, x2, #0x2b6
0x00002384: 60 00 80 52              mov      w0, #3
0x00002388: f7 03 04 aa              mov      x23, x4
0x0000238c: f3 03 03 2a              mov      w19, w3
0x00002390: e8 07 00 f9              str      x8, [sp, #8]
0x00002394: eb 04 00 94              bl       #0x3740                    ; -> __android_log_print@plt
0x00002398: a8 02 40 f9              ldr      x8, [x21]
0x0000239c: e0 03 15 aa              mov      x0, x21
0x000023a0: e1 03 14 aa              mov      x1, x20
0x000023a4: 08 7d 40 f9              ldr      x8, [x8, #0xf8]
0x000023a8: 00 01 3f d6              blr      x8
0x000023ac: a8 02 40 f9              ldr      x8, [x21]
0x000023b0: e1 03 00 aa              mov      x1, x0
0x000023b4: e2 ff ff f0              adrp     x2, #0x1000
0x000023b8: 42 f4 16 91              add      x2, x2, #0x5bd
0x000023bc: e3 ff ff f0              adrp     x3, #0x1000
0x000023c0: 63 f4 06 91              add      x3, x3, #0x1bd
0x000023c4: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000023c8: e0 03 15 aa              mov      x0, x21
0x000023cc: 00 01 3f d6              blr      x8
0x000023d0: 1c 00 00 f0              adrp     x28, #0x5000
0x000023d4: 80 73 01 f9              str      x0, [x28, #0x2e0]
0x000023d8: c0 01 00 b4              cbz      x0, #0x2410
0x000023dc: b7 02 00 b4              cbz      x23, #0x2430
0x000023e0: 36 03 00 b4              cbz      x22, #0x2444
0x000023e4: e2 03 00 91              mov      x2, sp
0x000023e8: e0 03 15 aa              mov      x0, x21
0x000023ec: e1 03 17 aa              mov      x1, x23
0x000023f0: ff 03 00 f9              str      xzr, [sp]
0x000023f4: cd 04 00 94              bl       #0x3728
0x000023f8: 00 05 00 34              cbz      w0, #0x2498
0x000023fc: e1 ff ff f0              adrp     x1, #0x1000
0x00002400: 21 fc 1c 91              add      x1, x1, #0x73f
0x00002404: e2 ff ff f0              adrp     x2, #0x1000
0x00002408: 42 6c 10 91              add      x2, x2, #0x41b
0x0000240c: 05 00 00 14              b        #0x2420                    ; -> Java_com_qualcomm_qti_vad_DNNVAD_init+0xe4
0x00002410: e1 ff ff f0              adrp     x1, #0x1000
0x00002414: 21 fc 1c 91              add      x1, x1, #0x73f
0x00002418: e2 ff ff f0              adrp     x2, #0x1000
0x0000241c: 42 48 02 91              add      x2, x2, #0x92
0x00002420: c0 00 80 52              mov      w0, #6
0x00002424: c7 04 00 94              bl       #0x3740                    ; -> __android_log_print@plt
0x00002428: 13 00 80 12              mov      w19, #-1
0x0000242c: 0d 00 00 14              b        #0x2460                    ; -> Java_com_qualcomm_qti_vad_DNNVAD_init+0x124
0x00002430: e1 ff ff f0              adrp     x1, #0x1000
0x00002434: 21 fc 1c 91              add      x1, x1, #0x73f
0x00002438: e2 ff ff f0              adrp     x2, #0x1000
0x0000243c: 42 e0 11 91              add      x2, x2, #0x478
0x00002440: 05 00 00 14              b        #0x2454                    ; -> Java_com_qualcomm_qti_vad_DNNVAD_init+0x118
0x00002444: e1 ff ff f0              adrp     x1, #0x1000
0x00002448: 21 fc 1c 91              add      x1, x1, #0x73f
0x0000244c: e2 ff ff f0              adrp     x2, #0x1000
0x00002450: 42 60 05 91              add      x2, x2, #0x158
0x00002454: c0 00 80 52              mov      w0, #6
0x00002458: ba 04 00 94              bl       #0x3740                    ; -> __android_log_print@plt
0x0000245c: 33 00 80 12              mov      w19, #-2
0x00002460: 68 17 40 f9              ldr      x8, [x27, #0x28]
0x00002464: e9 07 40 f9              ldr      x9, [sp, #8]
0x00002468: 1f 01 09 eb              cmp      x8, x9
0x0000246c: 01 18 00 54              b.ne     #0x276c
0x00002470: e0 03 13 2a              mov      w0, w19
0x00002474: f4 4f 46 a9              ldp      x20, x19, [sp, #0x60]
0x00002478: f6 57 45 a9              ldp      x22, x21, [sp, #0x50]
0x0000247c: f8 5f 44 a9              ldp      x24, x23, [sp, #0x40]
0x00002480: fa 67 43 a9              ldp      x26, x25, [sp, #0x30]
0x00002484: fc 6f 42 a9              ldp      x28, x27, [sp, #0x20]
0x00002488: fd 7b 41 a9              ldp      x29, x30, [sp, #0x10]
0x0000248c: ff c3 01 91              add      sp, sp, #0x70
0x00002490: bf 23 03 d5              autiasp  
0x00002494: c0 03 5f d6              ret      
0x00002498: a8 02 40 f9              ldr      x8, [x21]
0x0000249c: 1f 20 03 d5              nop      
0x000024a0: 41 7b ff 30              adr      x1, #0x1409
0x000024a4: e0 03 15 aa              mov      x0, x21
0x000024a8: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000024ac: 00 01 3f d6              blr      x8
0x000024b0: 20 03 00 b4              cbz      x0, #0x2514
0x000024b4: a8 02 40 f9              ldr      x8, [x21]
0x000024b8: e1 03 00 aa              mov      x1, x0
0x000024bc: e2 ff ff d0              adrp     x2, #0
0x000024c0: 42 38 3d 91              add      x2, x2, #0xf4e
0x000024c4: e3 ff ff d0              adrp     x3, #0
0x000024c8: 63 34 3c 91              add      x3, x3, #0xf0d
0x000024cc: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000024d0: e0 03 15 aa              mov      x0, x21
0x000024d4: 00 01 3f d6              blr      x8
0x000024d8: 80 02 00 b4              cbz      x0, #0x2528
0x000024dc: a8 02 40 f9              ldr      x8, [x21]
0x000024e0: f8 03 00 aa              mov      x24, x0
0x000024e4: e1 ff ff f0              adrp     x1, #0x1000
0x000024e8: 21 4c 07 91              add      x1, x1, #0x1d3
0x000024ec: e0 03 15 aa              mov      x0, x21
0x000024f0: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x000024f4: 00 01 3f d6              blr      x8
0x000024f8: e3 03 00 aa              mov      x3, x0
0x000024fc: e0 03 15 aa              mov      x0, x21
0x00002500: e1 03 17 aa              mov      x1, x23
0x00002504: e2 03 18 aa              mov      x2, x24
0x00002508: 2b ff ff 97              bl       #0x21b4
0x0000250c: f7 03 00 2a              mov      w23, w0
0x00002510: 0d 00 00 14              b        #0x2544                    ; -> Java_com_qualcomm_qti_vad_DNNVAD_init+0x208
0x00002514: e1 ff ff f0              adrp     x1, #0x1000
0x00002518: 21 fc 1c 91              add      x1, x1, #0x73f
0x0000251c: e2 ff ff d0              adrp     x2, #0
0x00002520: 42 c8 3f 91              add      x2, x2, #0xff2
0x00002524: 05 00 00 14              b        #0x2538                    ; -> Java_com_qualcomm_qti_vad_DNNVAD_init+0x1fc
0x00002528: e1 ff ff f0              adrp     x1, #0x1000
0x0000252c: 21 fc 1c 91              add      x1, x1, #0x73f
0x00002530: e2 ff ff f0              adrp     x2, #0x1000
0x00002534: 42 4c 08 91              add      x2, x2, #0x213
0x00002538: c0 00 80 52              mov      w0, #6
0x0000253c: 81 04 00 94              bl       #0x3740                    ; -> __android_log_print@plt
0x00002540: 17 00 80 12              mov      w23, #-1
0x00002544: e1 ff ff f0              adrp     x1, #0x1000
0x00002548: 21 fc 1c 91              add      x1, x1, #0x73f
0x0000254c: e2 ff ff f0              adrp     x2, #0x1000
0x00002550: 42 1c 1b 91              add      x2, x2, #0x6c7
0x00002554: 60 00 80 52              mov      w0, #3
0x00002558: e3 03 17 2a              mov      w3, w23
0x0000255c: 79 04 00 94              bl       #0x3740                    ; -> __android_log_print@plt
0x00002560: a8 02 40 f9              ldr      x8, [x21]
0x00002564: e0 03 15 aa              mov      x0, x21
0x00002568: e1 03 16 aa              mov      x1, x22
0x0000256c: 08 99 43 f9              ldr      x8, [x8, #0x730]
0x00002570: 00 01 3f d6              blr      x8
0x00002574: 00 0c 00 b4              cbz      x0, #0x26f4
0x00002578: a8 02 40 f9              ldr      x8, [x21]
0x0000257c: e1 ff ff f0              adrp     x1, #0x1000
0x00002580: 21 a4 06 91              add      x1, x1, #0x1a9
0x00002584: e0 03 15 aa              mov      x0, x21
0x00002588: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x0000258c: 00 01 3f d6              blr      x8
0x00002590: c0 0b 00 b4              cbz      x0, #0x2708
0x00002594: a8 02 40 f9              ldr      x8, [x21]
0x00002598: fa 03 00 aa              mov      x26, x0
0x0000259c: e2 ff ff d0              adrp     x2, #0
0x000025a0: 42 54 3d 91              add      x2, x2, #0xf55
0x000025a4: e3 ff ff f0              adrp     x3, #0x1000
0x000025a8: 63 cc 07 91              add      x3, x3, #0x1f3
0x000025ac: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000025b0: e0 03 15 aa              mov      x0, x21
0x000025b4: e1 03 1a aa              mov      x1, x26
0x000025b8: 00 01 3f d6              blr      x8
0x000025bc: a8 02 40 f9              ldr      x8, [x21]
0x000025c0: f9 03 00 aa              mov      x25, x0
0x000025c4: e2 ff ff f0              adrp     x2, #0x1000
0x000025c8: 42 dc 07 91              add      x2, x2, #0x1f7
0x000025cc: e3 ff ff d0              adrp     x3, #0
0x000025d0: 63 78 3d 91              add      x3, x3, #0xf5e
0x000025d4: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x000025d8: e0 03 15 aa              mov      x0, x21
0x000025dc: e1 03 1a aa              mov      x1, x26
0x000025e0: 00 01 3f d6              blr      x8
0x000025e4: a8 02 40 f9              ldr      x8, [x21]
0x000025e8: f8 03 00 aa              mov      x24, x0
0x000025ec: e2 ff ff d0              adrp     x2, #0
0x000025f0: 42 8c 3d 91              add      x2, x2, #0xf63
0x000025f4: e3 ff ff f0              adrp     x3, #0x1000
0x000025f8: 63 fc 06 91              add      x3, x3, #0x1bf
0x000025fc: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x00002600: e0 03 15 aa              mov      x0, x21
0x00002604: e1 03 1a aa              mov      x1, x26
0x00002608: 00 01 3f d6              blr      x8
0x0000260c: 99 08 00 b4              cbz      x25, #0x271c
0x00002610: 78 08 00 b4              cbz      x24, #0x271c
0x00002614: fa 03 00 aa              mov      x26, x0
0x00002618: 20 08 00 b4              cbz      x0, #0x271c
0x0000261c: e0 03 15 aa              mov      x0, x21
0x00002620: e1 03 16 aa              mov      x1, x22
0x00002624: e2 03 19 aa              mov      x2, x25
0x00002628: 52 00 00 94              bl       #0x2770
0x0000262c: 1f 1c 00 72              tst      w0, #0xff
0x00002630: 00 08 00 54              b.eq     #0x2730
0x00002634: e0 03 15 aa              mov      x0, x21
0x00002638: e1 03 16 aa              mov      x1, x22
0x0000263c: e2 03 1a aa              mov      x2, x26
0x00002640: dd fe ff 97              bl       #0x21b4
0x00002644: f9 03 00 2a              mov      w25, w0
0x00002648: e0 03 15 aa              mov      x0, x21
0x0000264c: e1 03 16 aa              mov      x1, x22
0x00002650: e2 03 18 aa              mov      x2, x24
0x00002654: 71 00 00 94              bl       #0x2818
0x00002658: 60 07 00 b4              cbz      x0, #0x2744
0x0000265c: f8 03 00 aa              mov      x24, x0
0x00002660: e0 03 13 2a              mov      w0, w19
0x00002664: 43 04 00 94              bl       #0x3770                    ; -> malloc@plt
0x00002668: a8 02 40 f9              ldr      x8, [x21]
0x0000266c: f6 03 00 aa              mov      x22, x0
0x00002670: e0 03 15 aa              mov      x0, x21
0x00002674: e1 03 18 aa              mov      x1, x24
0x00002678: e2 03 19 2a              mov      w2, w25
0x0000267c: e3 03 13 2a              mov      w3, w19
0x00002680: 08 21 43 f9              ldr      x8, [x8, #0x640]
0x00002684: e4 03 16 aa              mov      x4, x22
0x00002688: 00 01 3f d6              blr      x8
0x0000268c: 00 0a 80 52              mov      w0, #0x50
0x00002690: 3e 04 00 94              bl       #0x3788
0x00002694: f8 03 00 aa              mov      x24, x0
0x00002698: 42 04 00 94              bl       #0x37a0                    ; -> _ZN3VadC1Ev@plt
0x0000269c: a8 02 40 f9              ldr      x8, [x21]
0x000026a0: e0 03 15 aa              mov      x0, x21
0x000026a4: 82 73 41 f9              ldr      x2, [x28, #0x2e0]
0x000026a8: e1 03 14 aa              mov      x1, x20
0x000026ac: e3 03 18 aa              mov      x3, x24
0x000026b0: 08 b9 41 f9              ldr      x8, [x8, #0x370]
0x000026b4: 00 01 3f d6              blr      x8
0x000026b8: e1 03 00 91              mov      x1, sp
0x000026bc: e0 03 18 aa              mov      x0, x24
0x000026c0: e2 03 16 aa              mov      x2, x22
0x000026c4: e3 03 13 2a              mov      w3, w19
0x000026c8: e4 03 17 2a              mov      w4, w23
0x000026cc: 3b 04 00 94              bl       #0x37b8
0x000026d0: 40 04 00 34              cbz      w0, #0x2758
0x000026d4: f3 03 00 2a              mov      w19, w0
0x000026d8: e1 ff ff f0              adrp     x1, #0x1000
0x000026dc: 21 fc 1c 91              add      x1, x1, #0x73f
0x000026e0: e2 ff ff f0              adrp     x2, #0x1000
0x000026e4: 42 60 09 91              add      x2, x2, #0x258
0x000026e8: c0 00 80 52              mov      w0, #6
0x000026ec: 15 04 00 94              bl       #0x3740                    ; -> __android_log_print@plt
0x000026f0: 5c ff ff 17              b        #0x2460                    ; -> Java_com_qualcomm_qti_vad_DNNVAD_init+0x124
0x000026f4: e1 ff ff f0              adrp     x1, #0x1000
0x000026f8: 21 fc 1c 91              add      x1, x1, #0x73f
0x000026fc: e2 ff ff f0              adrp     x2, #0x1000
0x00002700: 42 08 06 91              add      x2, x2, #0x182
0x00002704: 54 ff ff 17              b        #0x2454                    ; -> Java_com_qualcomm_qti_vad_DNNVAD_init+0x118
0x00002708: e1 ff ff f0              adrp     x1, #0x1000
0x0000270c: 21 fc 1c 91              add      x1, x1, #0x73f
0x00002710: e2 ff ff f0              adrp     x2, #0x1000
0x00002714: 42 a0 04 91              add      x2, x2, #0x128
0x00002718: 42 ff ff 17              b        #0x2420                    ; -> Java_com_qualcomm_qti_vad_DNNVAD_init+0xe4
0x0000271c: e1 ff ff f0              adrp     x1, #0x1000
0x00002720: 21 fc 1c 91              add      x1, x1, #0x73f
0x00002724: e2 ff ff f0              adrp     x2, #0x1000
0x00002728: 42 c4 15 91              add      x2, x2, #0x571
0x0000272c: 3d ff ff 17              b        #0x2420                    ; -> Java_com_qualcomm_qti_vad_DNNVAD_init+0xe4
0x00002730: e1 ff ff f0              adrp     x1, #0x1000
0x00002734: 21 fc 1c 91              add      x1, x1, #0x73f
0x00002738: e2 ff ff f0              adrp     x2, #0x1000
0x0000273c: 42 ec 18 91              add      x2, x2, #0x63b
0x00002740: 45 ff ff 17              b        #0x2454                    ; -> Java_com_qualcomm_qti_vad_DNNVAD_init+0x118
0x00002744: e1 ff ff f0              adrp     x1, #0x1000
0x00002748: 21 fc 1c 91              add      x1, x1, #0x73f
0x0000274c: e2 ff ff f0              adrp     x2, #0x1000
0x00002750: 42 08 03 91              add      x2, x2, #0xc2
0x00002754: 40 ff ff 17              b        #0x2454                    ; -> Java_com_qualcomm_qti_vad_DNNVAD_init+0x118
0x00002758: 76 00 00 b4              cbz      x22, #0x2764
0x0000275c: e0 03 16 aa              mov      x0, x22
0x00002760: 1c 04 00 94              bl       #0x37d0                    ; -> free@plt
0x00002764: f3 03 1f 2a              mov      w19, wzr
0x00002768: 3e ff ff 17              b        #0x2460                    ; -> Java_com_qualcomm_qti_vad_DNNVAD_init+0x124
0x0000276c: fb 03 00 94              bl       #0x3758
