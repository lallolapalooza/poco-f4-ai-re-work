; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : CornDecode (0x360d0)
; start   : 0x360d0
; size    : 0x2c4
; bytes   : 0x2c4   instructions: 177
; generated: 2026-10-23T23:30:09

0x000360d0: ff c3 04 d1              sub      sp, sp, #0x130
0x000360d4: fc 67 0e a9              stp      x28, x25, [sp, #0xe0]
0x000360d8: f8 5f 0f a9              stp      x24, x23, [sp, #0xf0]
0x000360dc: f6 57 10 a9              stp      x22, x21, [sp, #0x100]
0x000360e0: f4 4f 11 a9              stp      x20, x19, [sp, #0x110]
0x000360e4: fd 7b 12 a9              stp      x29, x30, [sp, #0x120]
0x000360e8: fd 83 04 91              add      x29, sp, #0x120
0x000360ec: 56 d0 3b d5              mrs      x22, tpidr_el0
0x000360f0: c8 16 40 f9              ldr      x8, [x22, #0x28]
0x000360f4: f3 03 00 aa              mov      x19, x0
0x000360f8: e9 03 00 b2              mov      x9, #0x100000001
0x000360fc: 75 82 0f 91              add      x21, x19, #0x3e0
0x00036100: a8 83 1b f8              stur     x8, [x29, #-0x48]
0x00036104: 68 7a 41 f9              ldr      x8, [x19, #0x2f0]
0x00036108: f4 03 01 2a              mov      w20, w1
0x0003610c: 09 69 80 f2              movk     x9, #0x348
0x00036110: e1 23 00 91              add      x1, sp, #8
0x00036114: e2 03 00 32              mov      w2, #1
0x00036118: e0 03 15 aa              mov      x0, x21
0x0003611c: e3 03 1f aa              mov      x3, xzr
0x00036120: 7f f2 03 b9              str      wzr, [x19, #0x3f0]
0x00036124: f4 0b 00 b9              str      w20, [sp, #8]
0x00036128: e9 c3 00 f8              stur     x9, [sp, #0xc]
0x0003612c: e8 0f 00 f9              str      x8, [sp, #0x18]
0x00036130: c8 5e ff 97              bl       #0xdc50                    ; -> SoilCompute@plt
0x00036134: 01 00 80 12              mov      w1, #-1
0x00036138: e0 03 15 aa              mov      x0, x21
0x0003613c: 65 5b ff 97              bl       #0xced0                    ; -> SoilGetOutput@plt
0x00036140: 9f 06 00 71              cmp      w20, #1
0x00036144: 0b 11 00 54              b.lt     #0x36364
0x00036148: 08 08 40 f9              ldr      x8, [x0, #0x10]
0x0003614c: 9f 12 00 71              cmp      w20, #4
0x00036150: f5 03 14 2a              mov      w21, w20
0x00036154: 62 00 00 54              b.hs     #0x36160
0x00036158: e9 03 1f aa              mov      x9, xzr
0x0003615c: 19 00 00 14              b        #0x361c0                   ; -> CornDecode+0xf0
0x00036160: 8a 06 00 12              and      w10, w20, #3
0x00036164: e9 03 1f aa              mov      x9, xzr
0x00036168: ac 02 0a cb              sub      x12, x21, x10
0x0003616c: ac 02 00 b4              cbz      x12, #0x361c0
0x00036170: ad 06 00 d1              sub      x13, x21, #1
0x00036174: ae 01 0d 2b              adds     w14, w13, w13
0x00036178: eb 37 9f 1a              cset     w11, hs
0x0003617c: 2e 02 f8 37              tbnz     w14, #0x1f, #0x361c0
0x00036180: ad fd 60 d3              lsr      x13, x13, #0x20
0x00036184: ed 01 00 b5              cbnz     x13, #0x361c0
0x00036188: cb 01 00 37              tbnz     w11, #0, #0x361c0
0x0003618c: e9 03 0c aa              mov      x9, x12
0x00036190: ec e3 00 91              add      x12, sp, #0x38
0x00036194: eb 03 1f 2a              mov      w11, wzr
0x00036198: 8c 41 00 91              add      x12, x12, #0x10
0x0003619c: ed 03 09 aa              mov      x13, x9
0x000361a0: 0e c9 2b 8b              add      x14, x8, w11, sxtw #2
0x000361a4: c0 05 40 ad              ldp      q0, q1, [x14]
0x000361a8: ad 11 00 d1              sub      x13, x13, #4
0x000361ac: 6b 21 00 11              add      w11, w11, #8
0x000361b0: 80 85 3f ad              stp      q0, q1, [x12, #-0x10]
0x000361b4: 8c 81 00 91              add      x12, x12, #0x20
0x000361b8: 4d ff ff b5              cbnz     x13, #0x361a0
0x000361bc: 6a 01 00 34              cbz      w10, #0x361e8
0x000361c0: eb e3 00 91              add      x11, sp, #0x38
0x000361c4: 2a 79 1f 53              lsl      w10, w9, #1
0x000361c8: 6b 0d 09 8b              add      x11, x11, x9, lsl #3
0x000361cc: a9 02 09 cb              sub      x9, x21, x9
0x000361d0: 4c 7d 7e 93              sbfiz    x12, x10, #2, #0x20
0x000361d4: 0c 69 6c f8              ldr      x12, [x8, x12]
0x000361d8: 29 05 00 d1              sub      x9, x9, #1
0x000361dc: 4a 09 00 11              add      w10, w10, #2
0x000361e0: 6c 85 00 f8              str      x12, [x11], #8
0x000361e4: 69 ff ff b5              cbnz     x9, #0x361d0
0x000361e8: 9f 06 00 71              cmp      w20, #1
0x000361ec: cb 0b 00 54              b.lt     #0x36364
0x000361f0: 68 8e 43 b9              ldr      w8, [x19, #0x38c]
0x000361f4: f7 03 1f aa              mov      x23, xzr
0x000361f8: 74 a2 0d 91              add      x20, x19, #0x368
0x000361fc: f8 e3 00 91              add      x24, sp, #0x38
0x00036200: f9 03 00 32              mov      w25, #1
0x00036204: 05 00 00 14              b        #0x36218                   ; -> CornDecode+0x148
0x00036208: 69 a2 43 b9              ldr      w9, [x19, #0x3a0]
0x0003620c: 1f 01 09 6b              cmp      w8, w9
0x00036210: ca 02 00 54              b.ge     #0x36268
0x00036214: 17 00 00 14              b        #0x36270                   ; -> CornDecode+0x1a0
0x00036218: 08 05 00 11              add      w8, w8, #1
0x0003621c: 09 0f 17 8b              add      x9, x24, x23, lsl #3
0x00036220: 6a d6 41 f9              ldr      x10, [x19, #0x3a8]
0x00036224: 68 8e 03 b9              str      w8, [x19, #0x38c]
0x00036228: 68 9e 83 b9              ldrsw    x8, [x19, #0x39c]
0x0003622c: 2b 25 40 29              ldp      w11, w9, [x9]
0x00036230: 4a 0d 08 8b              add      x10, x10, x8, lsl #3
0x00036234: 4b 25 00 29              stp      w11, w9, [x10]
0x00036238: 69 a6 43 b9              ldr      w9, [x19, #0x3a4]
0x0003623c: 08 05 00 11              add      w8, w8, #1
0x00036240: 68 9e 03 b9              str      w8, [x19, #0x39c]
0x00036244: 29 fe ff 34              cbz      w9, #0x36208
0x00036248: 6a 9a 43 b9              ldr      w10, [x19, #0x398]
0x0003624c: 69 a2 43 b9              ldr      w9, [x19, #0x3a0]
0x00036250: 4b 05 00 11              add      w11, w10, #1
0x00036254: 7f 01 09 6b              cmp      w11, w9
0x00036258: ea a7 8a 1a              csinc    w10, wzr, w10, ge
0x0003625c: 6a 9a 03 b9              str      w10, [x19, #0x398]
0x00036260: 1f 01 09 6b              cmp      w8, w9
0x00036264: 6b 00 00 54              b.lt     #0x36270
0x00036268: 7f 9e 03 b9              str      wzr, [x19, #0x39c]
0x0003626c: 79 a6 03 b9              str      w25, [x19, #0x3a4]
0x00036270: e0 03 14 aa              mov      x0, x20
0x00036274: 8b 5c ff 97              bl       #0xd4a0                    ; -> CornSmoothPosterior@plt
0x00036278: 68 8e 43 b9              ldr      w8, [x19, #0x38c]
0x0003627c: 69 b6 43 b9              ldr      w9, [x19, #0x3b4]
0x00036280: 09 01 09 6b              subs     w9, w8, w9
0x00036284: ad 06 00 54              b.le     #0x36358
0x00036288: 6a 8a 43 b9              ldr      w10, [x19, #0x388]
0x0003628c: 5f 01 09 6b              cmp      w10, w9
0x00036290: 2a 06 00 54              b.ge     #0x36354
0x00036294: 6b 92 43 b9              ldr      w11, [x19, #0x390]
0x00036298: 6c ba 43 b9              ldr      w12, [x19, #0x3b8]
0x0003629c: 6d 72 43 b9              ldr      w13, [x19, #0x370]
0x000362a0: 0d 01 00 34              cbz      w13, #0x362c0
0x000362a4: 7f 01 0c 6b              cmp      w11, w12
0x000362a8: 0d 02 00 54              b.le     #0x362e8
0x000362ac: 6e 7e 43 b9              ldr      w14, [x19, #0x37c]
0x000362b0: 7f 76 03 b9              str      wzr, [x19, #0x374]
0x000362b4: ce 05 00 11              add      w14, w14, #1
0x000362b8: 6e 7e 03 b9              str      w14, [x19, #0x37c]
0x000362bc: 23 00 00 14              b        #0x36348                   ; -> CornDecode+0x278
0x000362c0: 7f 01 0c 6b              cmp      w11, w12
0x000362c4: 0d 02 00 54              b.le     #0x36304
0x000362c8: 6e 7a 43 b9              ldr      w14, [x19, #0x378]
0x000362cc: 6d be 43 b9              ldr      w13, [x19, #0x3bc]
0x000362d0: df 01 0d 6b              cmp      w14, w13
0x000362d4: ea 02 00 54              b.ge     #0x36330
0x000362d8: ce 05 00 11              add      w14, w14, #1
0x000362dc: ed 03 1f 2a              mov      w13, wzr
0x000362e0: 6e 7a 03 b9              str      w14, [x19, #0x378]
0x000362e4: 19 00 00 14              b        #0x36348                   ; -> CornDecode+0x278
0x000362e8: 6e 76 43 b9              ldr      w14, [x19, #0x374]
0x000362ec: 6f c2 43 b9              ldr      w15, [x19, #0x3c0]
0x000362f0: df 01 0f 6b              cmp      w14, w15
0x000362f4: ca 00 00 54              b.ge     #0x3630c
0x000362f8: ce 05 00 11              add      w14, w14, #1
0x000362fc: 6e 76 03 b9              str      w14, [x19, #0x374]
0x00036300: 12 00 00 14              b        #0x36348                   ; -> CornDecode+0x278
0x00036304: ed 03 1f 2a              mov      w13, wzr
0x00036308: 0f 00 00 14              b        #0x36344                   ; -> CornDecode+0x274
0x0003630c: 6d 86 43 b9              ldr      w13, [x19, #0x384]
0x00036310: 8d 00 f8 37              tbnz     w13, #0x1f, #0x36320
0x00036314: 6e 82 43 b9              ldr      w14, [x19, #0x380]
0x00036318: bf 01 0e 6b              cmp      w13, w14
0x0003631c: 4a 00 00 54              b.ge     #0x36324
0x00036320: 6a 86 03 b9              str      w10, [x19, #0x384]
0x00036324: ed 03 1f 2a              mov      w13, wzr
0x00036328: 7f ba 01 f9              str      xzr, [x19, #0x370]
0x0003632c: 07 00 00 14              b        #0x36348                   ; -> CornDecode+0x278
0x00036330: 4e 01 0d 4b              sub      w14, w10, w13
0x00036334: 6d 7e 03 b9              str      w13, [x19, #0x37c]
0x00036338: ed 03 00 32              mov      w13, #1
0x0003633c: 6e 82 03 b9              str      w14, [x19, #0x380]
0x00036340: 6d 72 03 b9              str      w13, [x19, #0x370]
0x00036344: 7f 7a 03 b9              str      wzr, [x19, #0x378]
0x00036348: 4a 05 00 11              add      w10, w10, #1
0x0003634c: 3f 01 0a 6b              cmp      w9, w10
0x00036350: 81 fa ff 54              b.ne     #0x362a0
0x00036354: 69 8a 03 b9              str      w9, [x19, #0x388]
0x00036358: f7 06 00 91              add      x23, x23, #1
0x0003635c: ff 02 15 eb              cmp      x23, x21
0x00036360: c1 f5 ff 54              b.ne     #0x36218
0x00036364: c8 16 40 f9              ldr      x8, [x22, #0x28]
0x00036368: a9 83 5b f8              ldur     x9, [x29, #-0x48]
0x0003636c: 1f 01 09 eb              cmp      x8, x9
0x00036370: 01 01 00 54              b.ne     #0x36390
0x00036374: fd 7b 52 a9              ldp      x29, x30, [sp, #0x120]
0x00036378: f4 4f 51 a9              ldp      x20, x19, [sp, #0x110]
0x0003637c: f6 57 50 a9              ldp      x22, x21, [sp, #0x100]
0x00036380: f8 5f 4f a9              ldp      x24, x23, [sp, #0xf0]
0x00036384: fc 67 4e a9              ldp      x28, x25, [sp, #0xe0]
0x00036388: ff c3 04 91              add      sp, sp, #0x130
0x0003638c: c0 03 5f d6              ret      
0x00036390: 08 5c ff 97              bl       #0xd3b0                    ; -> __stack_chk_fail@plt
