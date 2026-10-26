; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/xp/libdoc_photo_process.so
; target  : address 0x78904
; start   : 0x78904
; size    : 0x120
; bytes   : 0x120   instructions: 72
; generated: 2026-10-24T00:54:49

0x00078904: ed 03 22 2a              mvn      w13, w2
0x00078908: ee 73 1d 32              mov      w14, #-8
0x0007890c: bf 21 00 31              cmn      w13, #8
0x00078910: cd d1 82 5a              csinv    w13, w14, w2, le
0x00078914: ad 01 02 0b              add      w13, w13, w2
0x00078918: 70 7c 02 1b              mul      w16, w3, w2
0x0007891c: 6b 7c 40 93              sxtw     x11, w3
0x00078920: a3 21 00 11              add      w3, w13, #8
0x00078924: ef eb 7b b2              mov      x15, #-0x20
0x00078928: f1 02 80 92              mov      x17, #-0x18
0x0007892c: 72 7c 03 53              lsr      w18, w3, #3
0x00078930: 4d 06 11 9b              madd     x13, x18, x17, x1
0x00078934: f1 15 12 cb              sub      x17, x15, x18, lsl #5
0x00078938: 28 60 00 d1              sub      x8, x1, #0x18
0x0007893c: 12 76 1e 53              lsl      w18, w16, #2
0x00078940: 01 06 10 0b              add      w1, w16, w16, lsl #1
0x00078944: 6f 70 1d 12              and      w15, w3, #0xfffffff8
0x00078948: 10 00 11 8b              add      x16, x0, x17
0x0007894c: 09 80 00 d1              sub      x9, x0, #0x20
0x00078950: 4a 74 1e 53              lsl      w10, w2, #2
0x00078954: 4c 7c 40 93              sxtw     x12, w2
0x00078958: 4e 08 02 4b              sub      w14, w2, w2, lsl #2
0x0007895c: e3 e7 07 2f              movi     d3, #0xffffffffffffffff
0x00078960: 4f 00 0f 4b              sub      w15, w2, w15
0x00078964: 10 82 00 91              add      x16, x16, #0x20
0x00078968: f1 1f 00 32              mov      w17, #0xff
0x0007896c: 22 00 00 14              b        #0x789f4
0x00078970: 20 7c 40 93              sxtw     x0, w1
0x00078974: 61 7d 0c 9b              mul      x1, x11, x12
0x00078978: 23 04 01 8b              add      x3, x1, x1, lsl #1
0x0007897c: 24 74 1e 53              lsl      w4, w1, #2
0x00078980: 52 7e 40 93              sxtw     x18, w18
0x00078984: 01 01 03 8b              add      x1, x8, x3
0x00078988: 23 c1 24 8b              add      x3, x9, w4, sxtw
0x0007898c: e4 03 02 2a              mov      w4, w2
0x00078990: 05 00 00 14              b        #0x789a4
0x00078994: 20 40 40 0c              ld3      {v0.8b, v1.8b, v2.8b}, [x1]
0x00078998: 21 60 00 d1              sub      x1, x1, #0x18
0x0007899c: 60 00 00 0c              st4      {v0.8b, v1.8b, v2.8b, v3.8b}, [x3]
0x000789a0: 63 80 00 d1              sub      x3, x3, #0x20
0x000789a4: 84 20 00 71              subs     w4, w4, #8
0x000789a8: 6a ff ff 54              b.ge     #0x78994
0x000789ac: 01 02 12 8b              add      x1, x16, x18
0x000789b0: a3 01 00 8b              add      x3, x13, x0
0x000789b4: e4 03 0f 2a              mov      w4, w15
0x000789b8: 0b 00 00 14              b        #0x789e4
0x000789bc: 65 00 40 39              ldrb     w5, [x3]
0x000789c0: 84 04 00 51              sub      w4, w4, #1
0x000789c4: 25 00 00 39              strb     w5, [x1]
0x000789c8: 65 04 40 39              ldrb     w5, [x3, #1]
0x000789cc: 25 04 00 39              strb     w5, [x1, #1]
0x000789d0: 65 08 40 39              ldrb     w5, [x3, #2]
0x000789d4: 31 0c 00 39              strb     w17, [x1, #3]
0x000789d8: 63 0c 00 d1              sub      x3, x3, #3
0x000789dc: 25 08 00 39              strb     w5, [x1, #2]
0x000789e0: 21 10 00 d1              sub      x1, x1, #4
0x000789e4: c4 fe ff 36              tbz      w4, #0x1f, #0x789bc
0x000789e8: 6b 05 00 d1              sub      x11, x11, #1
0x000789ec: 52 02 0a 4b              sub      w18, w18, w10
0x000789f0: 01 00 0e 0b              add      w1, w0, w14
0x000789f4: 7f 01 00 f1              cmp      x11, #0
0x000789f8: cc fb ff 54              b.gt     #0x78970
0x000789fc: c0 03 5f d6              ret      
0x00078a00: ff 03 02 d1              sub      sp, sp, #0x80
0x00078a04: f9 1b 00 f9              str      x25, [sp, #0x30]
0x00078a08: f8 5f 04 a9              stp      x24, x23, [sp, #0x40]
0x00078a0c: f6 57 05 a9              stp      x22, x21, [sp, #0x50]
0x00078a10: f4 4f 06 a9              stp      x20, x19, [sp, #0x60]
0x00078a14: fd 7b 07 a9              stp      x29, x30, [sp, #0x70]
0x00078a18: fd c3 01 91              add      x29, sp, #0x70
0x00078a1c: 59 d0 3b d5              mrs      x25, tpidr_el0
0x00078a20: 28 17 40 f9              ldr      x8, [x25, #0x28]
