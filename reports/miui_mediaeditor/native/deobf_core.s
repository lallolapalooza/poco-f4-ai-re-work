; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_mediaeditor/work/x/lib/arm64-v8a/libCNamaSDK.so
; target  : range 0x56f2c4:0x56fd68
; start   : 0x56f2c4
; size    : 0xaa4
; bytes   : 0xaa4   instructions: 681
; generated: 2026-10-23T23:04:32

0x0056f2c4: fd 7b ba a9              stp      x29, x30, [sp, #-0x60]!
0x0056f2c8: fc 6f 01 a9              stp      x28, x27, [sp, #0x10]
0x0056f2cc: fa 67 02 a9              stp      x26, x25, [sp, #0x20]
0x0056f2d0: f8 5f 03 a9              stp      x24, x23, [sp, #0x30]
0x0056f2d4: f6 57 04 a9              stp      x22, x21, [sp, #0x40]
0x0056f2d8: f4 4f 05 a9              stp      x20, x19, [sp, #0x50]
0x0056f2dc: fd 03 00 91              mov      x29, sp
0x0056f2e0: ff 03 0d d1              sub      sp, sp, #0x340
0x0056f2e4: 49 d0 3b d5              mrs      x9, tpidr_el0
0x0056f2e8: 28 15 40 f9              ldr      x8, [x9, #0x28]
0x0056f2ec: 5f 80 00 f1              cmp      x2, #0x20
0x0056f2f0: a8 03 1f f8              stur     x8, [x29, #-0x10]
0x0056f2f4: 62 00 00 54              b.hs     #0x56f300
0x0056f2f8: 00 00 80 12              mov      w0, #-1
0x0056f2fc: 8e 02 00 14              b        #0x56fd34
0x0056f300: f4 03 03 aa              mov      x20, x3
0x0056f304: f3 03 02 aa              mov      x19, x2
0x0056f308: f5 03 01 aa              mov      x21, x1
0x0056f30c: e0 0f 00 f9              str      x0, [sp, #0x18]
0x0056f310: e0 83 08 91              add      x0, sp, #0x220
0x0056f314: 23 00 80 52              mov      w3, #1
0x0056f318: e1 03 14 aa              mov      x1, x20
0x0056f31c: e2 03 04 aa              mov      x2, x4
0x0056f320: e9 7b 00 f9              str      x9, [sp, #0xf0]
0x0056f324: e4 03 00 f9              str      x4, [sp]
0x0056f328: e5 1c 00 94              bl       #0x5766bc
0x0056f32c: 88 0a 40 f9              ldr      x8, [x20, #0x10]
0x0056f330: a0 63 02 d1              sub      x0, x29, #0x98
0x0056f334: e1 43 06 91              add      x1, sp, #0x190
0x0056f338: e2 83 08 91              add      x2, sp, #0x220
0x0056f33c: e3 03 1f 2a              mov      w3, wzr
0x0056f340: f4 07 00 f9              str      x20, [sp, #8]
0x0056f344: e8 7f 19 a9              stp      x8, xzr, [sp, #0x190]
0x0056f348: b9 63 02 d1              sub      x25, x29, #0x98
0x0056f34c: fa 43 06 91              add      x26, sp, #0x190
0x0056f350: db 1c 00 94              bl       #0x5766bc
0x0056f354: ae 83 56 38              ldurb    w14, [x29, #-0x98]
0x0056f358: af 93 56 38              ldurb    w15, [x29, #-0x97]
0x0056f35c: a9 b3 56 38              ldurb    w9, [x29, #-0x95]
0x0056f360: a8 c3 56 38              ldurb    w8, [x29, #-0x94]
0x0056f364: ee 13 01 f9              str      x14, [sp, #0x220]
0x0056f368: ae 93 57 38              ldurb    w14, [x29, #-0x87]
0x0056f36c: ef 17 01 f9              str      x15, [sp, #0x228]
0x0056f370: af a3 57 38              ldurb    w15, [x29, #-0x86]
0x0056f374: 29 0d 40 92              and      x9, x9, #0xf
0x0056f378: ee 6b 00 f9              str      x14, [sp, #0xd0]
0x0056f37c: ae b3 57 38              ldurb    w14, [x29, #-0x85]
0x0056f380: e9 1f 01 f9              str      x9, [sp, #0x238]
0x0056f384: a9 d3 57 38              ldurb    w9, [x29, #-0x83]
0x0056f388: 08 15 7e 92              and      x8, x8, #0xfc
0x0056f38c: ee 3f 0c a9              stp      x14, x15, [sp, #0xc0]
0x0056f390: ae c3 57 38              ldurb    w14, [x29, #-0x84]
0x0056f394: ad f3 56 38              ldurb    w13, [x29, #-0x91]
0x0056f398: e8 23 01 f9              str      x8, [sp, #0x240]
0x0056f39c: a8 f3 57 38              ldurb    w8, [x29, #-0x81]
0x0056f3a0: e9 3b 0b a9              stp      x9, x14, [sp, #0xb0]
0x0056f3a4: a9 e3 57 38              ldurb    w9, [x29, #-0x82]
0x0056f3a8: ac 03 57 38              ldurb    w12, [x29, #-0x90]
0x0056f3ac: a2 33 57 38              ldurb    w2, [x29, #-0x8d]
0x0056f3b0: a1 43 57 38              ldurb    w1, [x29, #-0x8c]
0x0056f3b4: e8 27 0a a9              stp      x8, x9, [sp, #0xa0]
0x0056f3b8: a8 0d 40 92              and      x8, x13, #0xf
0x0056f3bc: e8 2f 01 f9              str      x8, [sp, #0x258]
0x0056f3c0: a9 03 58 38              ldurb    w9, [x29, #-0x80]
0x0056f3c4: a8 13 58 38              ldurb    w8, [x29, #-0x7f]
0x0056f3c8: a5 73 57 38              ldurb    w5, [x29, #-0x89]
0x0056f3cc: b1 a3 56 38              ldurb    w17, [x29, #-0x96]
0x0056f3d0: ab d3 56 38              ldurb    w11, [x29, #-0x93]
0x0056f3d4: e8 27 09 a9              stp      x8, x9, [sp, #0x90]
0x0056f3d8: 88 15 7e 92              and      x8, x12, #0xfc
0x0056f3dc: e8 33 01 f9              str      x8, [sp, #0x260]
0x0056f3e0: a9 23 58 38              ldurb    w9, [x29, #-0x7e]
0x0056f3e4: a8 33 58 38              ldurb    w8, [x29, #-0x7d]
0x0056f3e8: aa e3 56 38              ldurb    w10, [x29, #-0x92]
0x0056f3ec: a0 13 57 38              ldurb    w0, [x29, #-0x8f]
0x0056f3f0: b0 23 57 38              ldurb    w16, [x29, #-0x8e]
0x0056f3f4: e8 27 08 a9              stp      x8, x9, [sp, #0x80]
0x0056f3f8: 48 0c 40 92              and      x8, x2, #0xf
0x0056f3fc: e8 3f 01 f9              str      x8, [sp, #0x278]
0x0056f400: a9 43 58 38              ldurb    w9, [x29, #-0x7c]
0x0056f404: a8 53 58 38              ldurb    w8, [x29, #-0x7b]
0x0056f408: a4 53 57 38              ldurb    w4, [x29, #-0x8b]
0x0056f40c: a3 63 57 38              ldurb    w3, [x29, #-0x8a]
0x0056f410: a6 83 57 38              ldurb    w6, [x29, #-0x88]
0x0056f414: e8 27 07 a9              stp      x8, x9, [sp, #0x70]
0x0056f418: 28 14 7e 92              and      x8, x1, #0xfc
0x0056f41c: e8 43 01 f9              str      x8, [sp, #0x280]
0x0056f420: a8 63 58 38              ldurb    w8, [x29, #-0x7a]
0x0056f424: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x0056f428: e6 73 00 f9              str      x6, [sp, #0xe0]
0x0056f42c: f1 1b 01 f9              str      x17, [sp, #0x230]
0x0056f430: e8 6f 00 f9              str      x8, [sp, #0xd8]
0x0056f434: a8 73 58 38              ldurb    w8, [x29, #-0x79]
0x0056f438: eb 27 01 f9              str      x11, [sp, #0x248]
0x0056f43c: ea 2b 01 f9              str      x10, [sp, #0x250]
0x0056f440: e0 37 01 f9              str      x0, [sp, #0x268]
0x0056f444: e8 ef 00 b9              str      w8, [sp, #0xec]
0x0056f448: a8 0c 40 92              and      x8, x5, #0xf
0x0056f44c: e8 4f 01 f9              str      x8, [sp, #0x298]
0x0056f450: 68 82 00 f1              subs     x8, x19, #0x20
0x0056f454: f0 3b 01 f9              str      x16, [sp, #0x270]
0x0056f458: e4 47 01 f9              str      x4, [sp, #0x288]
0x0056f45c: e3 4b 01 f9              str      x3, [sp, #0x290]
0x0056f460: f3 0b 00 f9              str      x19, [sp, #0x10]
0x0056f464: e8 7f 00 f9              str      x8, [sp, #0xf8]
0x0056f468: ff 0b 01 f9              str      xzr, [sp, #0x210]
0x0056f46c: e0 83 0c ad              stp      q0, q0, [sp, #0x190]
0x0056f470: e0 83 0d ad              stp      q0, q0, [sp, #0x1b0]
0x0056f474: e0 83 0e ad              stp      q0, q0, [sp, #0x1d0]
0x0056f478: e0 83 0f ad              stp      q0, q0, [sp, #0x1f0]
0x0056f47c: ff 53 01 f9              str      xzr, [sp, #0x2a0]
0x0056f480: c0 1e 00 54              b.eq     #0x56f858
0x0056f484: f7 03 1f aa              mov      x23, xzr
0x0056f488: fb 03 1f aa              mov      x27, xzr
0x0056f48c: ea 03 1f aa              mov      x10, xzr
0x0056f490: eb 03 1f aa              mov      x11, xzr
0x0056f494: ec 03 1f aa              mov      x12, xzr
0x0056f498: ed 03 1f aa              mov      x13, xzr
0x0056f49c: ef 03 1f aa              mov      x15, xzr
0x0056f4a0: f0 03 1f aa              mov      x16, xzr
0x0056f4a4: e9 03 1f aa              mov      x9, xzr
0x0056f4a8: e0 03 1f aa              mov      x0, xzr
0x0056f4ac: e1 03 1f aa              mov      x1, xzr
0x0056f4b0: e2 03 1f aa              mov      x2, xzr
0x0056f4b4: e3 03 1f aa              mov      x3, xzr
0x0056f4b8: e4 03 1f aa              mov      x4, xzr
0x0056f4bc: e5 03 1f aa              mov      x5, xzr
0x0056f4c0: ee 03 1f aa              mov      x14, xzr
0x0056f4c4: f3 03 1f aa              mov      x19, xzr
0x0056f4c8: b1 82 00 91              add      x17, x21, #0x20
0x0056f4cc: 6c 00 00 14              b        #0x56f67c
0x0056f4d0: a1 83 dc 3c              ldur     q1, [x29, #-0x38]
0x0056f4d4: a2 83 dd 3c              ldur     q2, [x29, #-0x28]
0x0056f4d8: e8 7f 40 f9              ldr      x8, [sp, #0xf8]
0x0056f4dc: a9 83 5e f8              ldur     x9, [x29, #-0x18]
0x0056f4e0: a3 83 d8 3c              ldur     q3, [x29, #-0x78]
0x0056f4e4: e1 8b 0f ad              stp      q1, q2, [sp, #0x1f0]
0x0056f4e8: a1 83 db 3c              ldur     q1, [x29, #-0x48]
0x0056f4ec: a2 83 da 3c              ldur     q2, [x29, #-0x58]
0x0056f4f0: a4 83 d9 3c              ldur     q4, [x29, #-0x68]
0x0056f4f4: 08 01 07 eb              subs     x8, x8, x7
0x0056f4f8: e8 7f 00 f9              str      x8, [sp, #0xf8]
0x0056f4fc: e2 87 0e ad              stp      q2, q1, [sp, #0x1d0]
0x0056f500: a1 83 d7 3c              ldur     q1, [x29, #-0x88]
0x0056f504: a2 83 d6 3c              ldur     q2, [x29, #-0x98]
0x0056f508: e9 0b 01 f9              str      x9, [sp, #0x210]
0x0056f50c: e3 93 0d ad              stp      q3, q4, [sp, #0x1b0]
0x0056f510: ec 37 5b a9              ldp      x12, x13, [sp, #0x1b0]
0x0056f514: e2 87 0c ad              stp      q2, q1, [sp, #0x190]
0x0056f518: e8 27 59 a9              ldp      x8, x9, [sp, #0x190]
0x0056f51c: ea 2f 5a a9              ldp      x10, x11, [sp, #0x1a0]
0x0056f520: ee 3f 5c a9              ldp      x14, x15, [sp, #0x1c0]
0x0056f524: f0 47 5d a9              ldp      x16, x17, [sp, #0x1d0]
0x0056f528: 29 21 48 8b              add      x9, x9, x8, lsr #8
0x0056f52c: 4a 21 49 8b              add      x10, x10, x9, lsr #8
0x0056f530: 6b 21 4a 8b              add      x11, x11, x10, lsr #8
0x0056f534: 8c 21 4b 8b              add      x12, x12, x11, lsr #8
0x0056f538: ad 21 4c 8b              add      x13, x13, x12, lsr #8
0x0056f53c: e0 07 5e a9              ldp      x0, x1, [sp, #0x1e0]
0x0056f540: d4 21 4d 8b              add      x20, x14, x13, lsr #8
0x0056f544: ef 21 54 8b              add      x15, x15, x20, lsr #8
0x0056f548: e2 0f 5f a9              ldp      x2, x3, [sp, #0x1f0]
0x0056f54c: 10 22 4f 8b              add      x16, x16, x15, lsr #8
0x0056f550: 36 22 50 8b              add      x22, x17, x16, lsr #8
0x0056f554: e4 03 41 f9              ldr      x4, [sp, #0x200]
0x0056f558: 17 20 56 8b              add      x23, x0, x22, lsr #8
0x0056f55c: e5 07 41 f9              ldr      x5, [sp, #0x208]
0x0056f560: 38 20 57 8b              add      x24, x1, x23, lsr #8
0x0056f564: f3 0b 41 f9              ldr      x19, [sp, #0x210]
0x0056f568: 5b 20 58 8b              add      x27, x2, x24, lsr #8
0x0056f56c: 7c 20 5b 8b              add      x28, x3, x27, lsr #8
0x0056f570: 9e 20 5c 8b              add      x30, x4, x28, lsr #8
0x0056f574: c6 00 07 8b              add      x6, x6, x7
0x0056f578: a7 20 5e 8b              add      x7, x5, x30, lsr #8
0x0056f57c: 6e 22 47 8b              add      x14, x19, x7, lsr #8
0x0056f580: f1 03 06 aa              mov      x17, x6
0x0056f584: c6 05 40 92              and      x6, x14, #3
0x0056f588: ce fd 42 d3              lsr      x14, x14, #2
0x0056f58c: ce 09 0e 8b              add      x14, x14, x14, lsl #2
0x0056f590: c8 01 28 8b              add      x8, x14, w8, uxtb
0x0056f594: 13 1d 40 92              and      x19, x8, #0xff
0x0056f598: 08 fd 48 d3              lsr      x8, x8, #8
0x0056f59c: 08 01 29 8b              add      x8, x8, w9, uxtb
0x0056f5a0: 0e 1d 40 92              and      x14, x8, #0xff
0x0056f5a4: 08 fd 48 d3              lsr      x8, x8, #8
0x0056f5a8: 08 01 2a 8b              add      x8, x8, w10, uxtb
0x0056f5ac: 05 1d 40 92              and      x5, x8, #0xff
0x0056f5b0: 08 fd 48 d3              lsr      x8, x8, #8
0x0056f5b4: 08 01 2b 8b              add      x8, x8, w11, uxtb
0x0056f5b8: 04 1d 40 92              and      x4, x8, #0xff
0x0056f5bc: 08 fd 48 d3              lsr      x8, x8, #8
0x0056f5c0: 08 01 2c 8b              add      x8, x8, w12, uxtb
0x0056f5c4: 03 1d 40 92              and      x3, x8, #0xff
0x0056f5c8: 08 fd 48 d3              lsr      x8, x8, #8
0x0056f5cc: 08 01 2d 8b              add      x8, x8, w13, uxtb
0x0056f5d0: 02 1d 40 92              and      x2, x8, #0xff
0x0056f5d4: 08 fd 48 d3              lsr      x8, x8, #8
0x0056f5d8: 08 01 34 8b              add      x8, x8, w20, uxtb
0x0056f5dc: 01 1d 40 92              and      x1, x8, #0xff
0x0056f5e0: 08 fd 48 d3              lsr      x8, x8, #8
0x0056f5e4: 08 01 2f 8b              add      x8, x8, w15, uxtb
0x0056f5e8: 00 1d 40 92              and      x0, x8, #0xff
0x0056f5ec: 08 fd 48 d3              lsr      x8, x8, #8
0x0056f5f0: 08 01 30 8b              add      x8, x8, w16, uxtb
0x0056f5f4: 09 1d 40 92              and      x9, x8, #0xff
0x0056f5f8: 08 fd 48 d3              lsr      x8, x8, #8
0x0056f5fc: 08 01 36 8b              add      x8, x8, w22, uxtb
0x0056f600: 10 1d 40 92              and      x16, x8, #0xff
0x0056f604: 08 fd 48 d3              lsr      x8, x8, #8
0x0056f608: 08 01 37 8b              add      x8, x8, w23, uxtb
0x0056f60c: 0f 1d 40 92              and      x15, x8, #0xff
0x0056f610: 08 fd 48 d3              lsr      x8, x8, #8
0x0056f614: 08 01 38 8b              add      x8, x8, w24, uxtb
0x0056f618: 0d 1d 40 92              and      x13, x8, #0xff
0x0056f61c: 08 fd 48 d3              lsr      x8, x8, #8
0x0056f620: 08 01 3b 8b              add      x8, x8, w27, uxtb
0x0056f624: 0c 1d 40 92              and      x12, x8, #0xff
0x0056f628: 08 fd 48 d3              lsr      x8, x8, #8
0x0056f62c: 08 01 3c 8b              add      x8, x8, w28, uxtb
0x0056f630: 0b 1d 40 92              and      x11, x8, #0xff
0x0056f634: 08 fd 48 d3              lsr      x8, x8, #8
0x0056f638: 08 01 3e 8b              add      x8, x8, w30, uxtb
0x0056f63c: 0a 1d 40 92              and      x10, x8, #0xff
0x0056f640: 08 fd 48 d3              lsr      x8, x8, #8
0x0056f644: 08 01 27 8b              add      x8, x8, w7, uxtb
0x0056f648: 1b 1d 40 92              and      x27, x8, #0xff
0x0056f64c: d7 20 48 8b              add      x23, x6, x8, lsr #8
0x0056f650: f3 3b 19 a9              stp      x19, x14, [sp, #0x190]
0x0056f654: e5 13 1a a9              stp      x5, x4, [sp, #0x1a0]
0x0056f658: e3 0b 1b a9              stp      x3, x2, [sp, #0x1b0]
0x0056f65c: e1 03 1c a9              stp      x1, x0, [sp, #0x1c0]
0x0056f660: e9 43 1d a9              stp      x9, x16, [sp, #0x1d0]
0x0056f664: ef 37 1e a9              stp      x15, x13, [sp, #0x1e0]
0x0056f668: ec 2f 1f a9              stp      x12, x11, [sp, #0x1f0]
0x0056f66c: ea 03 01 f9              str      x10, [sp, #0x200]
0x0056f670: fb 07 01 f9              str      x27, [sp, #0x208]
0x0056f674: f7 0b 01 f9              str      x23, [sp, #0x210]
0x0056f678: a0 0e 00 54              b.eq     #0x56f84c
0x0056f67c: e6 7f 40 f9              ldr      x6, [sp, #0xf8]
0x0056f680: f6 03 04 91              add      x22, sp, #0x100
0x0056f684: f8 03 11 aa              mov      x24, x17
0x0056f688: ff c3 00 f9              str      xzr, [sp, #0x180]
0x0056f68c: c8 04 00 d1              sub      x8, x6, #1
0x0056f690: 1f 3d 00 f1              cmp      x8, #0xf
0x0056f694: 08 02 80 52              mov      w8, #0x10
0x0056f698: c7 30 88 9a              csel     x7, x6, x8, lo
0x0056f69c: e8 03 07 aa              mov      x8, x7
0x0056f6a0: e6 03 11 aa              mov      x6, x17
0x0056f6a4: e0 03 0b ad              stp      q0, q0, [sp, #0x160]
0x0056f6a8: e0 03 0a ad              stp      q0, q0, [sp, #0x140]
0x0056f6ac: e0 03 09 ad              stp      q0, q0, [sp, #0x120]
0x0056f6b0: e0 03 08 ad              stp      q0, q0, [sp, #0x100]
0x0056f6b4: 1c 17 40 38              ldrb     w28, [x24], #1
0x0056f6b8: 08 05 00 f1              subs     x8, x8, #1
0x0056f6bc: dc 86 00 f8              str      x28, [x22], #8
0x0056f6c0: a1 ff ff 54              b.ne     #0x56f6b4
0x0056f6c4: 28 00 80 52              mov      w8, #1
0x0056f6c8: f1 03 04 91              add      x17, sp, #0x100
0x0056f6cc: 28 7a 27 f8              str      x8, [x17, x7, lsl #3]
0x0056f6d0: e8 5b 50 a9              ldp      x8, x22, [sp, #0x100]
0x0056f6d4: fc 7b 51 a9              ldp      x28, x30, [sp, #0x110]
0x0056f6d8: f8 03 1f aa              mov      x24, xzr
0x0056f6dc: 13 01 13 8b              add      x19, x8, x19
0x0056f6e0: e8 53 52 a9              ldp      x8, x20, [sp, #0x120]
0x0056f6e4: ce 21 53 8b              add      x14, x14, x19, lsr #8
0x0056f6e8: 85 03 05 8b              add      x5, x28, x5
0x0056f6ec: c4 03 04 8b              add      x4, x30, x4
0x0056f6f0: fc 7b 53 a9              ldp      x28, x30, [sp, #0x130]
0x0056f6f4: ce 01 16 8b              add      x14, x14, x22
0x0056f6f8: a5 20 4e 8b              add      x5, x5, x14, lsr #8
0x0056f6fc: 08 01 03 8b              add      x8, x8, x3
0x0056f700: 82 02 02 8b              add      x2, x20, x2
0x0056f704: e3 53 54 a9              ldp      x3, x20, [sp, #0x140]
0x0056f708: 73 1e 40 92              and      x19, x19, #0xff
0x0056f70c: ce 1d 40 92              and      x14, x14, #0xff
0x0056f710: 84 20 45 8b              add      x4, x4, x5, lsr #8
0x0056f714: f3 3b 19 a9              stp      x19, x14, [sp, #0x190]
0x0056f718: ae 1c 40 92              and      x14, x5, #0xff
0x0056f71c: 85 1c 40 92              and      x5, x4, #0xff
0x0056f720: 08 21 44 8b              add      x8, x8, x4, lsr #8
0x0056f724: 81 03 01 8b              add      x1, x28, x1
0x0056f728: c0 03 00 8b              add      x0, x30, x0
0x0056f72c: fc 7b 55 a9              ldp      x28, x30, [sp, #0x150]
0x0056f730: ee 17 1a a9              stp      x14, x5, [sp, #0x1a0]
0x0056f734: 0e 1d 40 92              and      x14, x8, #0xff
0x0056f738: 48 20 48 8b              add      x8, x2, x8, lsr #8
0x0056f73c: 02 1d 40 92              and      x2, x8, #0xff
0x0056f740: 28 20 48 8b              add      x8, x1, x8, lsr #8
0x0056f744: 71 00 09 8b              add      x17, x3, x9
0x0056f748: 90 02 10 8b              add      x16, x20, x16
0x0056f74c: e3 53 56 a9              ldp      x3, x20, [sp, #0x160]
0x0056f750: ee 0b 1b a9              stp      x14, x2, [sp, #0x1b0]
0x0056f754: 0e 1d 40 92              and      x14, x8, #0xff
0x0056f758: 08 20 48 8b              add      x8, x0, x8, lsr #8
0x0056f75c: 00 1d 40 92              and      x0, x8, #0xff
0x0056f760: 28 22 48 8b              add      x8, x17, x8, lsr #8
0x0056f764: 8f 03 0f 8b              add      x15, x28, x15
0x0056f768: cd 03 0d 8b              add      x13, x30, x13
0x0056f76c: fc 7b 57 a9              ldp      x28, x30, [sp, #0x170]
0x0056f770: ee 03 1c a9              stp      x14, x0, [sp, #0x1c0]
0x0056f774: 0e 1d 40 92              and      x14, x8, #0xff
0x0056f778: 08 22 48 8b              add      x8, x16, x8, lsr #8
0x0056f77c: 10 1d 40 92              and      x16, x8, #0xff
0x0056f780: e8 21 48 8b              add      x8, x15, x8, lsr #8
0x0056f784: 6c 00 0c 8b              add      x12, x3, x12
0x0056f788: e3 83 41 b9              ldr      w3, [sp, #0x180]
0x0056f78c: ee 43 1d a9              stp      x14, x16, [sp, #0x1d0]
0x0056f790: 0e 1d 40 92              and      x14, x8, #0xff
0x0056f794: a8 21 48 8b              add      x8, x13, x8, lsr #8
0x0056f798: 8b 02 0b 8b              add      x11, x20, x11
0x0056f79c: 0d 1d 40 92              and      x13, x8, #0xff
0x0056f7a0: 88 21 48 8b              add      x8, x12, x8, lsr #8
0x0056f7a4: 8a 03 0a 8b              add      x10, x28, x10
0x0056f7a8: 0c 1d 40 92              and      x12, x8, #0xff
0x0056f7ac: 68 21 48 8b              add      x8, x11, x8, lsr #8
0x0056f7b0: c9 03 1b 8b              add      x9, x30, x27
0x0056f7b4: 0b 1d 40 92              and      x11, x8, #0xff
0x0056f7b8: 48 21 48 8b              add      x8, x10, x8, lsr #8
0x0056f7bc: 63 00 17 0b              add      w3, w3, w23
0x0056f7c0: 0a 1d 40 92              and      x10, x8, #0xff
0x0056f7c4: 28 21 48 8b              add      x8, x9, x8, lsr #8
0x0056f7c8: 09 1d 40 92              and      x9, x8, #0xff
0x0056f7cc: 68 20 48 0b              add      w8, w3, w8, lsr #8
0x0056f7d0: e9 07 01 f9              str      x9, [sp, #0x208]
0x0056f7d4: 08 1d 40 92              and      x8, x8, #0xff
0x0056f7d8: e9 83 08 91              add      x9, sp, #0x220
0x0056f7dc: ee 37 1e a9              stp      x14, x13, [sp, #0x1e0]
0x0056f7e0: ec 2f 1f a9              stp      x12, x11, [sp, #0x1f0]
0x0056f7e4: ea 03 01 f9              str      x10, [sp, #0x200]
0x0056f7e8: e8 0b 01 f9              str      x8, [sp, #0x210]
0x0056f7ec: 06 00 00 14              b        #0x56f804
0x0056f7f0: 2a 7b 38 f8              str      x10, [x25, x24, lsl #3]
0x0056f7f4: 18 07 00 91              add      x24, x24, #1
0x0056f7f8: 1f 47 00 f1              cmp      x24, #0x11
0x0056f7fc: 29 21 00 91              add      x9, x9, #8
0x0056f800: 80 e6 ff 54              b.eq     #0x56f4d0
0x0056f804: ea 03 1f aa              mov      x10, xzr
0x0056f808: eb 03 1f aa              mov      x11, xzr
0x0056f80c: e8 03 09 aa              mov      x8, x9
0x0056f810: 3f 7b 38 f8              str      xzr, [x25, x24, lsl #3]
0x0056f814: 09 00 00 14              b        #0x56f838
0x0056f818: 0d 45 40 f9              ldr      x13, [x8, #0x88]
0x0056f81c: ad 09 0d 8b              add      x13, x13, x13, lsl #2
0x0056f820: ad e5 7a d3              lsl      x13, x13, #6
0x0056f824: 6b 05 00 91              add      x11, x11, #1
0x0056f828: aa 29 0c 9b              madd     x10, x13, x12, x10
0x0056f82c: 7f 45 00 f1              cmp      x11, #0x11
0x0056f830: 08 21 00 d1              sub      x8, x8, #8
0x0056f834: e0 fd ff 54              b.eq     #0x56f7f0
0x0056f838: 4c 7b 6b f8              ldr      x12, [x26, x11, lsl #3]
0x0056f83c: 1f 03 0b eb              cmp      x24, x11
0x0056f840: c3 fe ff 54              b.lo     #0x56f818
0x0056f844: 0d 01 40 f9              ldr      x13, [x8]
0x0056f848: f7 ff ff 17              b        #0x56f824
0x0056f84c: 66 16 00 91              add      x6, x19, #5
0x0056f850: e8 f2 03 91              add      x8, x23, #0xfc
0x0056f854: 13 00 00 14              b        #0x56f8a0
0x0056f858: f3 03 1f aa              mov      x19, xzr
0x0056f85c: fb 03 1f aa              mov      x27, xzr
0x0056f860: ea 03 1f aa              mov      x10, xzr
0x0056f864: eb 03 1f aa              mov      x11, xzr
0x0056f868: ec 03 1f aa              mov      x12, xzr
0x0056f86c: ed 03 1f aa              mov      x13, xzr
0x0056f870: ef 03 1f aa              mov      x15, xzr
0x0056f874: f0 03 1f aa              mov      x16, xzr
0x0056f878: e9 03 1f aa              mov      x9, xzr
0x0056f87c: e0 03 1f aa              mov      x0, xzr
0x0056f880: e1 03 1f aa              mov      x1, xzr
0x0056f884: e2 03 1f aa              mov      x2, xzr
0x0056f888: e3 03 1f aa              mov      x3, xzr
0x0056f88c: e4 03 1f aa              mov      x4, xzr
0x0056f890: e5 03 1f aa              mov      x5, xzr
0x0056f894: ee 03 1f aa              mov      x14, xzr
0x0056f898: a6 00 80 52              mov      w6, #5
0x0056f89c: 88 1f 80 52              mov      w8, #0xfc
0x0056f8a0: d4 21 46 8b              add      x20, x14, x6, lsr #8
0x0056f8a4: b6 20 54 8b              add      x22, x5, x20, lsr #8
0x0056f8a8: 97 20 56 8b              add      x23, x4, x22, lsr #8
0x0056f8ac: 78 20 57 8b              add      x24, x3, x23, lsr #8
0x0056f8b0: 59 20 58 8b              add      x25, x2, x24, lsr #8
0x0056f8b4: 31 20 59 8b              add      x17, x1, x25, lsr #8
0x0056f8b8: 1c 20 51 8b              add      x28, x0, x17, lsr #8
0x0056f8bc: 3e 21 5c 8b              add      x30, x9, x28, lsr #8
0x0056f8c0: 1a 22 5e 8b              add      x26, x16, x30, lsr #8
0x0056f8c4: e8 7f 00 f9              str      x8, [sp, #0xf8]
0x0056f8c8: e8 21 5a 8b              add      x8, x15, x26, lsr #8
0x0056f8cc: e8 13 00 f9              str      x8, [sp, #0x20]
0x0056f8d0: a8 21 48 8b              add      x8, x13, x8, lsr #8
0x0056f8d4: e8 17 00 f9              str      x8, [sp, #0x28]
0x0056f8d8: 88 21 48 8b              add      x8, x12, x8, lsr #8
0x0056f8dc: e8 1b 00 f9              str      x8, [sp, #0x30]
0x0056f8e0: 68 21 48 8b              add      x8, x11, x8, lsr #8
0x0056f8e4: e8 1f 00 f9              str      x8, [sp, #0x38]
0x0056f8e8: 48 21 48 8b              add      x8, x10, x8, lsr #8
0x0056f8ec: 07 fd 48 d3              lsr      x7, x8, #8
0x0056f8f0: e8 23 00 f9              str      x8, [sp, #0x40]
0x0056f8f4: e8 00 1b 0b              add      w8, w7, w27
0x0056f8f8: e7 7f 40 f9              ldr      x7, [sp, #0xf8]
0x0056f8fc: e7 20 48 0b              add      w7, w7, w8, lsr #8
0x0056f900: e7 1c 47 d3              ubfx     x7, x7, #7, #1
0x0056f904: e7 03 07 cb              neg      x7, x7
0x0056f908: c6 00 27 0a              bic      w6, w6, w7
0x0056f90c: e6 7f 00 f9              str      x6, [sp, #0xf8]
0x0056f910: 86 02 27 0a              bic      w6, w20, w7
0x0056f914: 34 02 27 0a              bic      w20, w17, w7
0x0056f918: f1 13 40 f9              ldr      x17, [sp, #0x20]
0x0056f91c: e6 37 00 f9              str      x6, [sp, #0x68]
0x0056f920: c6 02 27 0a              bic      w6, w22, w7
0x0056f924: e6 33 00 f9              str      x6, [sp, #0x60]
0x0056f928: e6 02 27 0a              bic      w6, w23, w7
0x0056f92c: e6 2f 00 f9              str      x6, [sp, #0x58]
0x0056f930: 06 03 27 0a              bic      w6, w24, w7
0x0056f934: 38 02 27 0a              bic      w24, w17, w7
0x0056f938: f1 17 40 f9              ldr      x17, [sp, #0x28]
0x0056f93c: e6 2b 00 f9              str      x6, [sp, #0x50]
0x0056f940: 26 03 27 0a              bic      w6, w25, w7
0x0056f944: e6 27 00 f9              str      x6, [sp, #0x48]
0x0056f948: 37 02 27 0a              bic      w23, w17, w7
0x0056f94c: f1 1b 40 f9              ldr      x17, [sp, #0x30]
0x0056f950: 59 03 27 0a              bic      w25, w26, w7
0x0056f954: 08 01 27 0a              bic      w8, w8, w7
0x0056f958: 9c 03 27 0a              bic      w28, w28, w7
0x0056f95c: 36 02 27 0a              bic      w22, w17, w7
0x0056f960: f1 1f 40 f9              ldr      x17, [sp, #0x38]
0x0056f964: de 03 27 0a              bic      w30, w30, w7
0x0056f968: 73 02 07 8a              and      x19, x19, x7
0x0056f96c: ce 01 07 8a              and      x14, x14, x7
0x0056f970: 26 02 27 0a              bic      w6, w17, w7
0x0056f974: f1 23 40 f9              ldr      x17, [sp, #0x40]
0x0056f978: a5 00 07 8a              and      x5, x5, x7
0x0056f97c: 84 00 07 8a              and      x4, x4, x7
0x0056f980: 63 00 07 8a              and      x3, x3, x7
0x0056f984: 3a 02 27 0a              bic      w26, w17, w7
0x0056f988: 31 01 07 8a              and      x17, x9, x7
0x0056f98c: 69 03 07 0a              and      w9, w27, w7
0x0056f990: 42 00 07 8a              and      x2, x2, x7
0x0056f994: 21 00 07 8a              and      x1, x1, x7
0x0056f998: 00 00 07 8a              and      x0, x0, x7
0x0056f99c: 10 02 07 8a              and      x16, x16, x7
0x0056f9a0: ef 01 07 8a              and      x15, x15, x7
0x0056f9a4: ad 01 07 8a              and      x13, x13, x7
0x0056f9a8: 8c 01 07 8a              and      x12, x12, x7
0x0056f9ac: 6b 01 07 8a              and      x11, x11, x7
0x0056f9b0: 4a 01 07 0a              and      w10, w10, w7
0x0056f9b4: 28 01 08 2a              orr      w8, w9, w8
0x0056f9b8: e9 7f 40 f9              ldr      x9, [sp, #0xf8]
0x0056f9bc: e7 37 40 f9              ldr      x7, [sp, #0x68]
0x0056f9c0: c6 1c 40 92              and      x6, x6, #0xff
0x0056f9c4: 6b 01 06 aa              orr      x11, x11, x6
0x0056f9c8: 46 1f 00 12              and      w6, w26, #0xff
0x0056f9cc: 4a 01 06 2a              orr      w10, w10, w6
0x0056f9d0: e6 73 40 f9              ldr      x6, [sp, #0xe0]
0x0056f9d4: 29 1d 40 92              and      x9, x9, #0xff
0x0056f9d8: e7 1c 40 92              and      x7, x7, #0xff
0x0056f9dc: 69 02 09 aa              orr      x9, x19, x9
0x0056f9e0: ce 01 07 aa              orr      x14, x14, x7
0x0056f9e4: e7 33 40 f9              ldr      x7, [sp, #0x60]
0x0056f9e8: 29 01 06 8b              add      x9, x9, x6
0x0056f9ec: e6 6b 40 f9              ldr      x6, [sp, #0xd0]
0x0056f9f0: e7 1c 40 92              and      x7, x7, #0xff
0x0056f9f4: a5 00 07 aa              orr      x5, x5, x7
0x0056f9f8: e7 2f 40 f9              ldr      x7, [sp, #0x58]
0x0056f9fc: ce 01 06 8b              add      x14, x14, x6
0x0056fa00: e6 67 40 f9              ldr      x6, [sp, #0xc8]
0x0056fa04: ce 21 49 8b              add      x14, x14, x9, lsr #8
0x0056fa08: e7 1c 40 92              and      x7, x7, #0xff
0x0056fa0c: 84 00 07 aa              orr      x4, x4, x7
0x0056fa10: a5 00 06 8b              add      x5, x5, x6
0x0056fa14: e6 63 40 f9              ldr      x6, [sp, #0xc0]
0x0056fa18: e7 2b 40 f9              ldr      x7, [sp, #0x50]
0x0056fa1c: a5 20 4e 8b              add      x5, x5, x14, lsr #8
0x0056fa20: 84 00 06 8b              add      x4, x4, x6
0x0056fa24: e6 5f 40 f9              ldr      x6, [sp, #0xb8]
0x0056fa28: e7 1c 40 92              and      x7, x7, #0xff
0x0056fa2c: 63 00 07 aa              orr      x3, x3, x7
0x0056fa30: e7 27 40 f9              ldr      x7, [sp, #0x48]
0x0056fa34: 63 00 06 8b              add      x3, x3, x6
0x0056fa38: e6 5b 40 f9              ldr      x6, [sp, #0xb0]
0x0056fa3c: 84 20 45 8b              add      x4, x4, x5, lsr #8
0x0056fa40: e7 1c 40 92              and      x7, x7, #0xff
0x0056fa44: 42 00 07 aa              orr      x2, x2, x7
0x0056fa48: 42 00 06 8b              add      x2, x2, x6
0x0056fa4c: e6 57 40 f9              ldr      x6, [sp, #0xa8]
0x0056fa50: 87 1e 40 92              and      x7, x20, #0xff
0x0056fa54: 21 00 07 aa              orr      x1, x1, x7
0x0056fa58: 87 1f 40 92              and      x7, x28, #0xff
0x0056fa5c: 21 00 06 8b              add      x1, x1, x6
0x0056fa60: e6 53 40 f9              ldr      x6, [sp, #0xa0]
0x0056fa64: 00 00 07 aa              orr      x0, x0, x7
0x0056fa68: c7 1f 40 92              and      x7, x30, #0xff
0x0056fa6c: 31 02 07 aa              orr      x17, x17, x7
0x0056fa70: 00 00 06 8b              add      x0, x0, x6
0x0056fa74: e6 4f 40 f9              ldr      x6, [sp, #0x98]
0x0056fa78: 27 1f 40 92              and      x7, x25, #0xff
0x0056fa7c: 10 02 07 aa              orr      x16, x16, x7
0x0056fa80: 07 1f 40 92              and      x7, x24, #0xff
0x0056fa84: 31 02 06 8b              add      x17, x17, x6
0x0056fa88: e6 4b 40 f9              ldr      x6, [sp, #0x90]
0x0056fa8c: ef 01 07 aa              orr      x15, x15, x7
0x0056fa90: e7 1e 40 92              and      x7, x23, #0xff
0x0056fa94: ad 01 07 aa              orr      x13, x13, x7
0x0056fa98: 10 02 06 8b              add      x16, x16, x6
0x0056fa9c: e6 47 40 f9              ldr      x6, [sp, #0x88]
0x0056faa0: c7 1e 40 92              and      x7, x22, #0xff
0x0056faa4: 8c 01 07 aa              orr      x12, x12, x7
0x0056faa8: e7 6f 40 f9              ldr      x7, [sp, #0xd8]
0x0056faac: ef 01 06 8b              add      x15, x15, x6
0x0056fab0: e6 43 40 f9              ldr      x6, [sp, #0x80]
0x0056fab4: 63 20 44 8b              add      x3, x3, x4, lsr #8
0x0056fab8: 4a 01 07 0b              add      w10, w10, w7
0x0056fabc: a7 46 40 39              ldrb     w7, [x21, #0x11]
0x0056fac0: ad 01 06 8b              add      x13, x13, x6
0x0056fac4: e6 3f 40 f9              ldr      x6, [sp, #0x78]
0x0056fac8: 42 20 43 8b              add      x2, x2, x3, lsr #8
0x0056facc: ee 00 0e 4a              eor      w14, w7, w14
0x0056fad0: 21 20 42 8b              add      x1, x1, x2, lsr #8
0x0056fad4: 8c 01 06 8b              add      x12, x12, x6
0x0056fad8: e6 3b 40 f9              ldr      x6, [sp, #0x70]
0x0056fadc: 00 20 41 8b              add      x0, x0, x1, lsr #8
0x0056fae0: 31 22 40 8b              add      x17, x17, x0, lsr #8
0x0056fae4: 10 22 51 8b              add      x16, x16, x17, lsr #8
0x0056fae8: 6b 01 06 8b              add      x11, x11, x6
0x0056faec: a6 42 40 39              ldrb     w6, [x21, #0x10]
0x0056faf0: ef 21 50 8b              add      x15, x15, x16, lsr #8
0x0056faf4: ad 21 4f 8b              add      x13, x13, x15, lsr #8
0x0056faf8: 8c 21 4d 8b              add      x12, x12, x13, lsr #8
0x0056fafc: c9 00 09 4a              eor      w9, w6, w9
0x0056fb00: a6 4a 40 39              ldrb     w6, [x21, #0x12]
0x0056fb04: c9 01 09 2a              orr      w9, w14, w9
0x0056fb08: ae 4e 40 39              ldrb     w14, [x21, #0x13]
0x0056fb0c: 6b 21 4c 8b              add      x11, x11, x12, lsr #8
0x0056fb10: c5 00 05 4a              eor      w5, w6, w5
0x0056fb14: 29 01 05 2a              orr      w9, w9, w5
0x0056fb18: ce 01 04 4a              eor      w14, w14, w4
0x0056fb1c: a4 52 40 39              ldrb     w4, [x21, #0x14]
0x0056fb20: 29 01 0e 2a              orr      w9, w9, w14
0x0056fb24: ae 56 40 39              ldrb     w14, [x21, #0x15]
0x0056fb28: 83 00 03 4a              eor      w3, w4, w3
0x0056fb2c: 29 01 03 2a              orr      w9, w9, w3
0x0056fb30: ce 01 02 4a              eor      w14, w14, w2
0x0056fb34: a2 5a 40 39              ldrb     w2, [x21, #0x16]
0x0056fb38: 29 01 0e 2a              orr      w9, w9, w14
0x0056fb3c: ae 5e 40 39              ldrb     w14, [x21, #0x17]
0x0056fb40: 41 00 01 4a              eor      w1, w2, w1
0x0056fb44: 29 01 01 2a              orr      w9, w9, w1
0x0056fb48: ce 01 00 4a              eor      w14, w14, w0
0x0056fb4c: a0 62 40 39              ldrb     w0, [x21, #0x18]
0x0056fb50: 29 01 0e 2a              orr      w9, w9, w14
0x0056fb54: ae 66 40 39              ldrb     w14, [x21, #0x19]
0x0056fb58: 11 00 11 4a              eor      w17, w0, w17
0x0056fb5c: 29 01 11 2a              orr      w9, w9, w17
0x0056fb60: ce 01 10 4a              eor      w14, w14, w16
0x0056fb64: b0 6a 40 39              ldrb     w16, [x21, #0x1a]
0x0056fb68: 29 01 0e 2a              orr      w9, w9, w14
0x0056fb6c: ae 6e 40 39              ldrb     w14, [x21, #0x1b]
0x0056fb70: 0f 02 0f 4a              eor      w15, w16, w15
0x0056fb74: 29 01 0f 2a              orr      w9, w9, w15
0x0056fb78: cd 01 0d 4a              eor      w13, w14, w13
0x0056fb7c: ae 72 40 39              ldrb     w14, [x21, #0x1c]
0x0056fb80: 29 01 0d 2a              orr      w9, w9, w13
0x0056fb84: ad 76 40 39              ldrb     w13, [x21, #0x1d]
0x0056fb88: cc 01 0c 4a              eor      w12, w14, w12
0x0056fb8c: 29 01 0c 2a              orr      w9, w9, w12
0x0056fb90: ac 01 0b 4a              eor      w12, w13, w11
0x0056fb94: 6b fd 48 d3              lsr      x11, x11, #8
0x0056fb98: 4a 01 0b 0b              add      w10, w10, w11
0x0056fb9c: ab 7a 40 39              ldrb     w11, [x21, #0x1e]
0x0056fba0: 08 21 4a 0b              add      w8, w8, w10, lsr #8
0x0056fba4: 29 01 0c 2a              orr      w9, w9, w12
0x0056fba8: ac 7e 40 39              ldrb     w12, [x21, #0x1f]
0x0056fbac: 6a 01 0a 4a              eor      w10, w11, w10
0x0056fbb0: eb ef 40 b9              ldr      w11, [sp, #0xec]
0x0056fbb4: 29 01 0a 2a              orr      w9, w9, w10
0x0056fbb8: 68 01 08 0b              add      w8, w11, w8
0x0056fbbc: 08 01 0c 4a              eor      w8, w8, w12
0x0056fbc0: 28 01 08 2a              orr      w8, w9, w8
0x0056fbc4: 08 1d 00 12              and      w8, w8, #0xff
0x0056fbc8: 08 05 00 51              sub      w8, w8, #1
0x0056fbcc: 68 00 40 37              tbnz     w8, #8, #0x56fbd8
0x0056fbd0: 00 00 80 12              mov      w0, #-1
0x0056fbd4: 57 00 00 14              b        #0x56fd30
0x0056fbd8: e2 4f 40 a9              ldp      x2, x19, [sp]
0x0056fbdc: e0 83 08 91              add      x0, sp, #0x220
0x0056fbe0: 23 00 80 52              mov      w3, #1
0x0056fbe4: e1 03 13 aa              mov      x1, x19
0x0056fbe8: b5 1a 00 94              bl       #0x5766bc
0x0056fbec: f7 0b 40 f9              ldr      x23, [sp, #0x10]
0x0056fbf0: 68 0a 40 f9              ldr      x8, [x19, #0x10]
0x0056fbf4: ff 02 01 f1              cmp      x23, #0x40
0x0056fbf8: e8 7f 19 a9              stp      x8, xzr, [sp, #0x190]
0x0056fbfc: a3 06 00 54              b.lo     #0x56fcd0
0x0056fc00: f4 0f 40 f9              ldr      x20, [sp, #0x18]
0x0056fc04: b6 63 02 d1              sub      x22, x29, #0x98
0x0056fc08: f3 03 14 aa              mov      x19, x20
0x0056fc0c: 20 00 00 14              b        #0x56fc8c
0x0056fc10: e8 63 46 39              ldrb     w8, [sp, #0x198]
0x0056fc14: e9 67 46 39              ldrb     w9, [sp, #0x199]
0x0056fc18: ea 6b 46 39              ldrb     w10, [sp, #0x19a]
0x0056fc1c: f7 02 01 d1              sub      x23, x23, #0x40
0x0056fc20: 08 05 00 91              add      x8, x8, #1
0x0056fc24: e8 63 06 39              strb     w8, [sp, #0x198]
0x0056fc28: 28 21 48 8b              add      x8, x9, x8, lsr #8
0x0056fc2c: e9 6f 46 39              ldrb     w9, [sp, #0x19b]
0x0056fc30: e8 67 06 39              strb     w8, [sp, #0x199]
0x0056fc34: 48 21 48 8b              add      x8, x10, x8, lsr #8
0x0056fc38: ea 73 46 39              ldrb     w10, [sp, #0x19c]
0x0056fc3c: e8 6b 06 39              strb     w8, [sp, #0x19a]
0x0056fc40: 28 21 48 8b              add      x8, x9, x8, lsr #8
0x0056fc44: e9 77 46 39              ldrb     w9, [sp, #0x19d]
0x0056fc48: e8 6f 06 39              strb     w8, [sp, #0x19b]
0x0056fc4c: 48 21 48 8b              add      x8, x10, x8, lsr #8
0x0056fc50: ea 7b 46 39              ldrb     w10, [sp, #0x19e]
0x0056fc54: e8 73 06 39              strb     w8, [sp, #0x19c]
0x0056fc58: 28 21 48 8b              add      x8, x9, x8, lsr #8
0x0056fc5c: e9 7f 46 39              ldrb     w9, [sp, #0x19f]
0x0056fc60: e8 77 06 39              strb     w8, [sp, #0x19d]
0x0056fc64: 48 21 48 8b              add      x8, x10, x8, lsr #8
0x0056fc68: bf 02 00 f1              cmp      x21, #0
0x0056fc6c: aa 02 01 91              add      x10, x21, #0x40
0x0056fc70: f5 03 8a 9a              csel     x21, xzr, x10, eq
0x0056fc74: ff fe 00 f1              cmp      x23, #0x3f
0x0056fc78: e8 7b 06 39              strb     w8, [sp, #0x19e]
0x0056fc7c: 28 21 48 0b              add      w8, w9, w8, lsr #8
0x0056fc80: 73 02 01 91              add      x19, x19, #0x40
0x0056fc84: e8 7f 06 39              strb     w8, [sp, #0x19f]
0x0056fc88: 89 02 00 54              b.ls     #0x56fcd8
0x0056fc8c: a0 63 02 d1              sub      x0, x29, #0x98
0x0056fc90: e1 43 06 91              add      x1, sp, #0x190
0x0056fc94: e2 83 08 91              add      x2, sp, #0x220
0x0056fc98: e3 03 1f 2a              mov      w3, wzr
0x0056fc9c: 88 1a 00 94              bl       #0x5766bc
0x0056fca0: e8 03 1f aa              mov      x8, xzr
0x0056fca4: 08 00 00 14              b        #0x56fcc4
0x0056fca8: a9 6a 68 38              ldrb     w9, [x21, x8]
0x0056fcac: ca 6a 68 38              ldrb     w10, [x22, x8]
0x0056fcb0: 49 01 09 4a              eor      w9, w10, w9
0x0056fcb4: 69 6a 28 38              strb     w9, [x19, x8]
0x0056fcb8: 08 05 00 91              add      x8, x8, #1
0x0056fcbc: 1f 01 01 f1              cmp      x8, #0x40
0x0056fcc0: 80 fa ff 54              b.eq     #0x56fc10
0x0056fcc4: 35 ff ff b5              cbnz     x21, #0x56fca8
0x0056fcc8: e9 03 1f 2a              mov      w9, wzr
0x0056fccc: f8 ff ff 17              b        #0x56fcac
0x0056fcd0: f4 0f 40 f9              ldr      x20, [sp, #0x18]
0x0056fcd4: f3 03 14 aa              mov      x19, x20
0x0056fcd8: 77 02 00 b4              cbz      x23, #0x56fd24
0x0056fcdc: a0 63 02 d1              sub      x0, x29, #0x98
0x0056fce0: e1 43 06 91              add      x1, sp, #0x190
0x0056fce4: e2 83 08 91              add      x2, sp, #0x220
0x0056fce8: e3 03 1f 2a              mov      w3, wzr
0x0056fcec: b6 63 02 d1              sub      x22, x29, #0x98
0x0056fcf0: 73 1a 00 94              bl       #0x5766bc
0x0056fcf4: e8 03 15 aa              mov      x8, x21
0x0056fcf8: 08 00 00 14              b        #0x56fd18
0x0056fcfc: 09 01 40 39              ldrb     w9, [x8]
0x0056fd00: ca 16 40 38              ldrb     w10, [x22], #1
0x0056fd04: f7 06 00 f1              subs     x23, x23, #1
0x0056fd08: 08 05 00 91              add      x8, x8, #1
0x0056fd0c: 49 01 09 4a              eor      w9, w10, w9
0x0056fd10: 69 16 00 38              strb     w9, [x19], #1
0x0056fd14: 80 00 00 54              b.eq     #0x56fd24
0x0056fd18: 35 ff ff b5              cbnz     x21, #0x56fcfc
0x0056fd1c: e9 03 1f 2a              mov      w9, wzr
0x0056fd20: f8 ff ff 17              b        #0x56fd00
0x0056fd24: e0 03 1f 2a              mov      w0, wzr
0x0056fd28: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x0056fd2c: 80 02 00 ad              stp      q0, q0, [x20]
0x0056fd30: e9 7b 40 f9              ldr      x9, [sp, #0xf0]
0x0056fd34: 28 15 40 f9              ldr      x8, [x9, #0x28]
0x0056fd38: a9 03 5f f8              ldur     x9, [x29, #-0x10]
0x0056fd3c: 1f 01 09 eb              cmp      x8, x9
0x0056fd40: 21 01 00 54              b.ne     #0x56fd64
0x0056fd44: ff 03 0d 91              add      sp, sp, #0x340
0x0056fd48: f4 4f 45 a9              ldp      x20, x19, [sp, #0x50]
0x0056fd4c: f6 57 44 a9              ldp      x22, x21, [sp, #0x40]
0x0056fd50: f8 5f 43 a9              ldp      x24, x23, [sp, #0x30]
0x0056fd54: fa 67 42 a9              ldp      x26, x25, [sp, #0x20]
0x0056fd58: fc 6f 41 a9              ldp      x28, x27, [sp, #0x10]
0x0056fd5c: fd 7b c6 a8              ldp      x29, x30, [sp], #0x60
0x0056fd60: c0 03 5f d6              ret      
0x0056fd64: cb e0 1f 94              bl       #0xd68090                  ; -> __stack_chk_fail@plt
