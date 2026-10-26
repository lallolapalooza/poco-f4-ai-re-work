; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libqvasml.so
; target  : address 0x5e788
; start   : 0x5e788
; size    : 0x1a4
; bytes   : 0x1a4   instructions: 105
; generated: 2026-10-23T23:30:12

0x0005e788: 3f 23 03 d5              paciasp  
0x0005e78c: fd 7b bc a9              stp      x29, x30, [sp, #-0x40]!
0x0005e790: fc 0b 00 f9              str      x28, [sp, #0x10]
0x0005e794: f6 57 02 a9              stp      x22, x21, [sp, #0x20]
0x0005e798: f4 4f 03 a9              stp      x20, x19, [sp, #0x30]
0x0005e79c: fd 03 00 91              mov      x29, sp
0x0005e7a0: ff 0b 40 d1              sub      sp, sp, #2, lsl #12
0x0005e7a4: ff 43 00 d1              sub      sp, sp, #0x10
0x0005e7a8: 55 d0 3b d5              mrs      x21, tpidr_el0
0x0005e7ac: f4 03 00 aa              mov      x20, x0
0x0005e7b0: e0 07 40 91              add      x0, sp, #1, lsl #12
0x0005e7b4: f3 03 01 aa              mov      x19, x1
0x0005e7b8: a8 16 40 f9              ldr      x8, [x21, #0x28]
0x0005e7bc: 00 20 00 91              add      x0, x0, #8
0x0005e7c0: e1 03 1f 2a              mov      w1, wzr
0x0005e7c4: 02 00 82 52              mov      w2, #0x1000
0x0005e7c8: f6 07 40 91              add      x22, sp, #1, lsl #12
0x0005e7cc: a8 83 1f f8              stur     x8, [x29, #-8]
0x0005e7d0: d6 22 00 91              add      x22, x22, #8
0x0005e7d4: 11 cd 02 94              bl       #0x111c18
0x0005e7d8: e0 23 00 91              add      x0, sp, #8
0x0005e7dc: e1 03 1f 2a              mov      w1, wzr
0x0005e7e0: 02 00 82 52              mov      w2, #0x1000
0x0005e7e4: 0d cd 02 94              bl       #0x111c18
0x0005e7e8: e8 03 1f aa              mov      x8, xzr
0x0005e7ec: 89 12 00 91              add      x9, x20, #4
0x0005e7f0: 20 85 7f 2d              ldp      s0, s1, [x9, #-4]
0x0005e7f4: ca 02 08 8b              add      x10, x22, x8
0x0005e7f8: 29 21 00 91              add      x9, x9, #8
0x0005e7fc: 08 41 00 91              add      x8, x8, #0x10
0x0005e800: 1f 01 3c f1              cmp      x8, #0xf00
0x0005e804: 40 01 00 bd              str      s0, [x10]
0x0005e808: 41 09 00 bd              str      s1, [x10, #8]
0x0005e80c: 21 ff ff 54              b.ne     #0x5e7f0
0x0005e810: e9 07 40 91              add      x9, sp, #1, lsl #12
0x0005e814: e8 03 1f aa              mov      x8, xzr
0x0005e818: 29 21 00 91              add      x9, x9, #8
0x0005e81c: ff 0b 1f b9              str      wzr, [sp, #0x1f08]
0x0005e820: ff 13 1f b9              str      wzr, [sp, #0x1f10]
0x0005e824: ff 1b 1f b9              str      wzr, [sp, #0x1f18]
0x0005e828: ff 23 1f b9              str      wzr, [sp, #0x1f20]
0x0005e82c: ff 2b 1f b9              str      wzr, [sp, #0x1f28]
0x0005e830: ff 33 1f b9              str      wzr, [sp, #0x1f30]
0x0005e834: ff 3b 1f b9              str      wzr, [sp, #0x1f38]
0x0005e838: ff 43 1f b9              str      wzr, [sp, #0x1f40]
0x0005e83c: ff 4b 1f b9              str      wzr, [sp, #0x1f48]
0x0005e840: ff 53 1f b9              str      wzr, [sp, #0x1f50]
0x0005e844: ff 5b 1f b9              str      wzr, [sp, #0x1f58]
0x0005e848: ff 63 1f b9              str      wzr, [sp, #0x1f60]
0x0005e84c: ff 6b 1f b9              str      wzr, [sp, #0x1f68]
0x0005e850: ff 73 1f b9              str      wzr, [sp, #0x1f70]
0x0005e854: ff 7b 1f b9              str      wzr, [sp, #0x1f78]
0x0005e858: ff 83 1f b9              str      wzr, [sp, #0x1f80]
0x0005e85c: ff 8b 1f b9              str      wzr, [sp, #0x1f88]
0x0005e860: ff 93 1f b9              str      wzr, [sp, #0x1f90]
0x0005e864: ff 9b 1f b9              str      wzr, [sp, #0x1f98]
0x0005e868: ff a3 1f b9              str      wzr, [sp, #0x1fa0]
0x0005e86c: ff ab 1f b9              str      wzr, [sp, #0x1fa8]
0x0005e870: ff b3 1f b9              str      wzr, [sp, #0x1fb0]
0x0005e874: ff bb 1f b9              str      wzr, [sp, #0x1fb8]
0x0005e878: ff c3 1f b9              str      wzr, [sp, #0x1fc0]
0x0005e87c: ff cb 1f b9              str      wzr, [sp, #0x1fc8]
0x0005e880: ff d3 1f b9              str      wzr, [sp, #0x1fd0]
0x0005e884: ff db 1f b9              str      wzr, [sp, #0x1fd8]
0x0005e888: ff e3 1f b9              str      wzr, [sp, #0x1fe0]
0x0005e88c: ff eb 1f b9              str      wzr, [sp, #0x1fe8]
0x0005e890: ff f3 1f b9              str      wzr, [sp, #0x1ff0]
0x0005e894: ff fb 1f b9              str      wzr, [sp, #0x1ff8]
0x0005e898: ff 03 20 b9              str      wzr, [sp, #0x2000]
0x0005e89c: 2a 01 08 8b              add      x10, x9, x8
0x0005e8a0: 08 41 00 91              add      x8, x8, #0x10
0x0005e8a4: 1f 05 40 f1              cmp      x8, #1, lsl #12
0x0005e8a8: 5f 05 00 b9              str      wzr, [x10, #4]
0x0005e8ac: 5f 0d 00 b9              str      wzr, [x10, #0xc]
0x0005e8b0: 61 ff ff 54              b.ne     #0x5e89c
0x0005e8b4: e0 07 40 91              add      x0, sp, #1, lsl #12
0x0005e8b8: e2 23 00 91              add      x2, sp, #8
0x0005e8bc: 00 20 00 91              add      x0, x0, #8
0x0005e8c0: 01 40 80 52              mov      w1, #0x200
0x0005e8c4: f4 07 40 91              add      x20, sp, #1, lsl #12
0x0005e8c8: 94 22 00 91              add      x20, x20, #8
0x0005e8cc: 03 cd 02 94              bl       #0x111cd8
0x0005e8d0: e8 03 1f aa              mov      x8, xzr
0x0005e8d4: 89 02 08 8b              add      x9, x20, x8
0x0005e8d8: 6a 02 08 8b              add      x10, x19, x8
0x0005e8dc: 08 01 01 91              add      x8, x8, #0x40
0x0005e8e0: 1f 01 20 f1              cmp      x8, #0x800
0x0005e8e4: 21 01 40 ad              ldp      q1, q0, [x9]
0x0005e8e8: 23 09 41 ad              ldp      q3, q2, [x9, #0x20]
0x0005e8ec: 41 01 00 ad              stp      q1, q0, [x10]
0x0005e8f0: 43 09 01 ad              stp      q3, q2, [x10, #0x20]
0x0005e8f4: 01 ff ff 54              b.ne     #0x5e8d4
0x0005e8f8: a8 16 40 f9              ldr      x8, [x21, #0x28]
0x0005e8fc: a9 83 5f f8              ldur     x9, [x29, #-8]
0x0005e900: 1f 01 09 eb              cmp      x8, x9
0x0005e904: 21 01 00 54              b.ne     #0x5e928
0x0005e908: ff 0b 40 91              add      sp, sp, #2, lsl #12
0x0005e90c: ff 43 00 91              add      sp, sp, #0x10
0x0005e910: f4 4f 43 a9              ldp      x20, x19, [sp, #0x30]
0x0005e914: f6 57 42 a9              ldp      x22, x21, [sp, #0x20]
0x0005e918: fc 0b 40 f9              ldr      x28, [sp, #0x10]
0x0005e91c: fd 7b c4 a8              ldp      x29, x30, [sp], #0x40
0x0005e920: bf 23 03 d5              autiasp  
0x0005e924: c0 03 5f d6              ret      
0x0005e928: 7a cc 02 94              bl       #0x111b10                  ; -> __stack_chk_fail@plt
