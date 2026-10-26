; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/xp/libdoc_photo_process.so
; target  : address 0x7884c
; start   : 0x7884c
; size    : 0x180
; bytes   : 0x180   instructions: 96
; generated: 2026-10-24T00:41:09

0x0007884c: 49 1c 00 51              sub      w9, w2, #7
0x00078850: 2c 7d a9 0a              bic      w12, w9, w9, asr #31
0x00078854: 8c 1d 00 11              add      w12, w12, #7
0x00078858: 8c 71 1d 12              and      w12, w12, #0xfffffff8
0x0007885c: e8 03 1f aa              mov      x8, xzr
0x00078860: 6a 7c 40 93              sxtw     x10, w3
0x00078864: 4b 7c 40 93              sxtw     x11, w2
0x00078868: 8d 45 2c 8b              add      x13, x12, w12, uxtw #1
0x0007886c: 8e f5 7e d3              lsl      x14, x12, #2
0x00078870: 22 00 00 14              b        #0x788f8
0x00078874: 0f 7d 0b 9b              mul      x15, x8, x11
0x00078878: f0 75 1e 53              lsl      w16, w15, #2
0x0007887c: f2 05 0f 8b              add      x18, x15, x15, lsl #1
0x00078880: 2f c0 30 8b              add      x15, x1, w16, sxtw
0x00078884: 10 00 12 8b              add      x16, x0, x18
0x00078888: f1 03 1f 2a              mov      w17, wzr
0x0007888c: f2 03 10 aa              mov      x18, x16
0x00078890: e3 03 0f aa              mov      x3, x15
0x00078894: 04 00 00 14              b        #0x788a4
0x00078898: 60 00 df 0c              ld4      {v0.8b, v1.8b, v2.8b, v3.8b}, [x3], #32
0x0007889c: 31 22 00 11              add      w17, w17, #8
0x000788a0: 40 42 9f 0c              st3      {v0.8b, v1.8b, v2.8b}, [x18], #24
0x000788a4: 3f 02 09 6b              cmp      w17, w9
0x000788a8: 8b ff ff 54              b.lt     #0x78898
0x000788ac: f1 03 0c 2a              mov      w17, w12
0x000788b0: f2 03 0e aa              mov      x18, x14
0x000788b4: e3 03 0d aa              mov      x3, x13
0x000788b8: 0d 00 00 14              b        #0x788ec
0x000788bc: 44 76 7e 92              and      x4, x18, #0xfffffffc
0x000788c0: e4 01 04 8b              add      x4, x15, x4
0x000788c4: 85 00 40 39              ldrb     w5, [x4]
0x000788c8: 06 42 23 8b              add      x6, x16, w3, uxtw
0x000788cc: 63 0c 00 91              add      x3, x3, #3
0x000788d0: 52 12 00 91              add      x18, x18, #4
0x000788d4: c5 00 00 39              strb     w5, [x6]
0x000788d8: 85 04 40 39              ldrb     w5, [x4, #1]
0x000788dc: 31 06 00 11              add      w17, w17, #1
0x000788e0: c5 04 00 39              strb     w5, [x6, #1]
0x000788e4: 84 08 40 39              ldrb     w4, [x4, #2]
0x000788e8: c4 08 00 39              strb     w4, [x6, #2]
0x000788ec: 3f 02 02 6b              cmp      w17, w2
0x000788f0: 6b fe ff 54              b.lt     #0x788bc
0x000788f4: 08 05 00 91              add      x8, x8, #1
0x000788f8: 1f 01 0a eb              cmp      x8, x10
0x000788fc: cb fb ff 54              b.lt     #0x78874
0x00078900: c0 03 5f d6              ret      
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
