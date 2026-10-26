; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libqvasml.so
; target  : address 0x5f61c
; start   : 0x5f61c
; size    : 0xbc
; bytes   : 0xbc   instructions: 47
; generated: 2026-10-23T23:30:53

0x0005f61c: 5f 24 03 d5              bti      c
0x0005f620: 5f 04 00 71              cmp      w2, #1
0x0005f624: cb 02 00 54              b.lt     #0x5f67c
0x0005f628: e8 03 02 2a              mov      w8, w2
0x0005f62c: 5f 20 00 71              cmp      w2, #8
0x0005f630: 03 01 00 54              b.lo     #0x5f650
0x0005f634: 09 f5 7e d3              lsl      x9, x8, #2
0x0005f638: 2a 00 09 8b              add      x10, x1, x9
0x0005f63c: 5f 01 00 eb              cmp      x10, x0
0x0005f640: 09 02 00 54              b.ls     #0x5f680
0x0005f644: 09 00 09 8b              add      x9, x0, x9
0x0005f648: 3f 01 01 eb              cmp      x9, x1
0x0005f64c: a9 01 00 54              b.ls     #0x5f680
0x0005f650: e9 03 1f aa              mov      x9, xzr
0x0005f654: 2b f5 7e d3              lsl      x11, x9, #2
0x0005f658: 08 01 09 cb              sub      x8, x8, x9
0x0005f65c: 0a 00 0b 8b              add      x10, x0, x11
0x0005f660: 2b 00 0b 8b              add      x11, x1, x11
0x0005f664: 60 45 40 bc              ldr      s0, [x11], #4
0x0005f668: 41 01 40 bd              ldr      s1, [x10]
0x0005f66c: 08 05 00 f1              subs     x8, x8, #1
0x0005f670: 00 08 21 1e              fmul     s0, s0, s1
0x0005f674: 40 45 00 bc              str      s0, [x10], #4
0x0005f678: 61 ff ff 54              b.ne     #0x5f664
0x0005f67c: c0 03 5f d6              ret      
0x0005f680: 09 71 7d 92              and      x9, x8, #0xfffffff8
0x0005f684: 2a 40 00 91              add      x10, x1, #0x10
0x0005f688: 0b 40 00 91              add      x11, x0, #0x10
0x0005f68c: ec 03 09 aa              mov      x12, x9
0x0005f690: 40 85 7f ad              ldp      q0, q1, [x10, #-0x10]
0x0005f694: 4a 81 00 91              add      x10, x10, #0x20
0x0005f698: 8c 21 00 f1              subs     x12, x12, #8
0x0005f69c: 62 8d 7f ad              ldp      q2, q3, [x11, #-0x10]
0x0005f6a0: 00 dc 22 6e              fmul     v0.4s, v0.4s, v2.4s
0x0005f6a4: 21 dc 23 6e              fmul     v1.4s, v1.4s, v3.4s
0x0005f6a8: 60 85 3f ad              stp      q0, q1, [x11, #-0x10]
0x0005f6ac: 6b 81 00 91              add      x11, x11, #0x20
0x0005f6b0: 01 ff ff 54              b.ne     #0x5f690
0x0005f6b4: 3f 01 08 eb              cmp      x9, x8
0x0005f6b8: 20 fe ff 54              b.eq     #0x5f67c
0x0005f6bc: e6 ff ff 17              b        #0x5f654                   ; -> udk7_q_vector_mul_vector_inplace_f+0x38
0x0005f6c0: 5f 24 03 d5              bti      c
0x0005f6c4: 5f 04 00 71              cmp      w2, #1
0x0005f6c8: ab 02 00 54              b.lt     #0x5f71c
0x0005f6cc: e8 03 02 2a              mov      w8, w2
0x0005f6d0: 5f 20 00 71              cmp      w2, #8
0x0005f6d4: e3 00 00 54              b.lo     #0x5f6f0
