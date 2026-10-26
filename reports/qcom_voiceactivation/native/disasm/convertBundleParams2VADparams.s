; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libdnnvad_jni.so
; target  : address 0x2020
; start   : 0x2020
; size    : 0x23c
; bytes   : 0x23c   instructions: 143
; generated: 2026-10-23T23:27:17

0x00002020: 3f 23 03 d5              paciasp  
0x00002024: fd 7b bb a9              stp      x29, x30, [sp, #-0x50]!
0x00002028: f9 0b 00 f9              str      x25, [sp, #0x10]
0x0000202c: f8 5f 02 a9              stp      x24, x23, [sp, #0x20]
0x00002030: f6 57 03 a9              stp      x22, x21, [sp, #0x30]
0x00002034: f4 4f 04 a9              stp      x20, x19, [sp, #0x40]
0x00002038: fd 03 00 91              mov      x29, sp
0x0000203c: 08 00 40 f9              ldr      x8, [x0]
0x00002040: f4 03 01 aa              mov      x20, x1
0x00002044: 1f 20 03 d5              nop      
0x00002048: 01 9e ff 30              adr      x1, #0x1409
0x0000204c: f3 03 02 aa              mov      x19, x2
0x00002050: f5 03 00 aa              mov      x21, x0
0x00002054: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x00002058: 00 01 3f d6              blr      x8
0x0000205c: 60 08 00 b4              cbz      x0, #0x2168
0x00002060: a8 02 40 f9              ldr      x8, [x21]
0x00002064: e1 03 00 aa              mov      x1, x0
0x00002068: e2 ff ff d0              adrp     x2, #0
0x0000206c: 42 38 3d 91              add      x2, x2, #0xf4e
0x00002070: e3 ff ff d0              adrp     x3, #0
0x00002074: 63 34 3c 91              add      x3, x3, #0xf0d
0x00002078: 08 85 40 f9              ldr      x8, [x8, #0x108]
0x0000207c: e0 03 15 aa              mov      x0, x21
0x00002080: 00 01 3f d6              blr      x8
0x00002084: c0 07 00 b4              cbz      x0, #0x217c
0x00002088: a8 02 40 f9              ldr      x8, [x21]
0x0000208c: f6 03 00 aa              mov      x22, x0
0x00002090: e1 ff ff f0              adrp     x1, #0x1000
0x00002094: 21 9c 18 91              add      x1, x1, #0x627
0x00002098: e0 03 15 aa              mov      x0, x21
0x0000209c: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x000020a0: 00 01 3f d6              blr      x8
0x000020a4: a8 02 40 f9              ldr      x8, [x21]
0x000020a8: f7 03 00 aa              mov      x23, x0
0x000020ac: e1 ff ff f0              adrp     x1, #0x1000
0x000020b0: 21 d8 1b 91              add      x1, x1, #0x6f6
0x000020b4: e0 03 15 aa              mov      x0, x21
0x000020b8: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x000020bc: 00 01 3f d6              blr      x8
0x000020c0: a8 02 40 f9              ldr      x8, [x21]
0x000020c4: f8 03 00 aa              mov      x24, x0
0x000020c8: e1 ff ff d0              adrp     x1, #0
0x000020cc: 21 6c 39 91              add      x1, x1, #0xe5b
0x000020d0: e0 03 15 aa              mov      x0, x21
0x000020d4: 08 9d 42 f9              ldr      x8, [x8, #0x538]
0x000020d8: 00 01 3f d6              blr      x8
0x000020dc: f9 03 00 aa              mov      x25, x0
0x000020e0: e0 03 15 aa              mov      x0, x21
0x000020e4: e1 03 14 aa              mov      x1, x20
0x000020e8: e2 03 16 aa              mov      x2, x22
0x000020ec: e3 03 17 aa              mov      x3, x23
0x000020f0: 31 00 00 94              bl       #0x21b4
0x000020f4: 60 06 00 b9              str      w0, [x19, #4]
0x000020f8: e0 03 15 aa              mov      x0, x21
0x000020fc: e1 03 14 aa              mov      x1, x20
0x00002100: e2 03 16 aa              mov      x2, x22
0x00002104: e3 03 18 aa              mov      x3, x24
0x00002108: 2b 00 00 94              bl       #0x21b4
0x0000210c: 60 02 00 79              strh     w0, [x19]
0x00002110: e0 03 15 aa              mov      x0, x21
0x00002114: e1 03 14 aa              mov      x1, x20
0x00002118: e2 03 16 aa              mov      x2, x22
0x0000211c: e3 03 19 aa              mov      x3, x25
0x00002120: 25 00 00 94              bl       #0x21b4
0x00002124: f4 ff ff f0              adrp     x20, #0x1000
0x00002128: 94 fe 1c 91              add      x20, x20, #0x73f
0x0000212c: 03 3c 00 13              sxth     w3, w0
0x00002130: 60 06 00 79              strh     w0, [x19, #2]
0x00002134: e2 ff ff f0              adrp     x2, #0x1000
0x00002138: 42 0c 07 91              add      x2, x2, #0x1c3
0x0000213c: 60 00 80 52              mov      w0, #3
0x00002140: e1 03 14 aa              mov      x1, x20
0x00002144: 7f 05 00 94              bl       #0x3740                    ; -> __android_log_print@plt
0x00002148: 63 06 40 b9              ldr      w3, [x19, #4]
0x0000214c: e2 ff ff f0              adrp     x2, #0x1000
0x00002150: 42 98 16 91              add      x2, x2, #0x5a6
0x00002154: 60 00 80 52              mov      w0, #3
0x00002158: e1 03 14 aa              mov      x1, x20
0x0000215c: 79 05 00 94              bl       #0x3740                    ; -> __android_log_print@plt
0x00002160: e0 03 1f 2a              mov      w0, wzr
0x00002164: 0d 00 00 14              b        #0x2198                    ; -> convertBundleParams2VADparams+0x178
0x00002168: e1 ff ff f0              adrp     x1, #0x1000
0x0000216c: 21 fc 1c 91              add      x1, x1, #0x73f
0x00002170: e2 ff ff d0              adrp     x2, #0
0x00002174: 42 c4 3e 91              add      x2, x2, #0xfb1
0x00002178: 05 00 00 14              b        #0x218c                    ; -> convertBundleParams2VADparams+0x16c
0x0000217c: e1 ff ff f0              adrp     x1, #0x1000
0x00002180: 21 fc 1c 91              add      x1, x1, #0x73f
0x00002184: e2 ff ff f0              adrp     x2, #0x1000
0x00002188: 42 c0 09 91              add      x2, x2, #0x270
0x0000218c: c0 00 80 52              mov      w0, #6
0x00002190: 6c 05 00 94              bl       #0x3740                    ; -> __android_log_print@plt
0x00002194: 00 00 80 12              mov      w0, #-1
0x00002198: f4 4f 44 a9              ldp      x20, x19, [sp, #0x40]
0x0000219c: f6 57 43 a9              ldp      x22, x21, [sp, #0x30]
0x000021a0: f8 5f 42 a9              ldp      x24, x23, [sp, #0x20]
0x000021a4: f9 0b 40 f9              ldr      x25, [sp, #0x10]
0x000021a8: fd 7b c5 a8              ldp      x29, x30, [sp], #0x50
0x000021ac: bf 23 03 d5              autiasp  
0x000021b0: c0 03 5f d6              ret      
0x000021b4: 3f 23 03 d5              paciasp  
0x000021b8: ff 83 04 d1              sub      sp, sp, #0x120
0x000021bc: fd 7b 10 a9              stp      x29, x30, [sp, #0x100]
0x000021c0: fc 4f 11 a9              stp      x28, x19, [sp, #0x110]
0x000021c4: fd 03 04 91              add      x29, sp, #0x100
0x000021c8: a3 93 38 a9              stp      x3, x4, [x29, #-0x78]
0x000021cc: ea 03 00 91              mov      x10, sp
0x000021d0: a5 9b 39 a9              stp      x5, x6, [x29, #-0x68]
0x000021d4: eb 04 80 92              mov      x11, #-0x28
0x000021d8: a7 83 1a f8              stur     x7, [x29, #-0x58]
0x000021dc: a9 83 00 91              add      x9, x29, #0x20
0x000021e0: e0 07 00 ad              stp      q0, q1, [sp]
0x000021e4: 4a 01 02 91              add      x10, x10, #0x80
0x000021e8: e2 0f 01 ad              stp      q2, q3, [sp, #0x20]
0x000021ec: 0b f0 df f2              movk     x11, #0xff80, lsl #32
0x000021f0: e4 17 02 ad              stp      q4, q5, [sp, #0x40]
0x000021f4: a3 43 01 d1              sub      x3, x29, #0x50
0x000021f8: e6 1f 03 ad              stp      q6, q7, [sp, #0x60]
0x000021fc: 53 d0 3b d5              mrs      x19, tpidr_el0
0x00002200: 68 16 40 f9              ldr      x8, [x19, #0x28]
0x00002204: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x00002208: a8 83 1f f8              stur     x8, [x29, #-8]
0x0000220c: a8 e3 01 d1              sub      x8, x29, #0x78
0x00002210: 08 a1 00 91              add      x8, x8, #0x28
0x00002214: a0 83 3e ad              stp      q0, q0, [x29, #-0x30]
0x00002218: aa 2f 3e a9              stp      x10, x11, [x29, #-0x20]
0x0000221c: a9 23 3d a9              stp      x9, x8, [x29, #-0x30]
0x00002220: 08 00 40 f9              ldr      x8, [x0]
0x00002224: a0 87 7e ad              ldp      q0, q1, [x29, #-0x30]
0x00002228: 08 c9 40 f9              ldr      x8, [x8, #0x190]
0x0000222c: a0 87 3d ad              stp      q0, q1, [x29, #-0x50]
0x00002230: 00 01 3f d6              blr      x8
0x00002234: 68 16 40 f9              ldr      x8, [x19, #0x28]
0x00002238: a9 83 5f f8              ldur     x9, [x29, #-8]
0x0000223c: 1f 01 09 eb              cmp      x8, x9
0x00002240: c1 00 00 54              b.ne     #0x2258
0x00002244: fc 4f 51 a9              ldp      x28, x19, [sp, #0x110]
0x00002248: fd 7b 50 a9              ldp      x29, x30, [sp, #0x100]
0x0000224c: ff 83 04 91              add      sp, sp, #0x120
0x00002250: bf 23 03 d5              autiasp  
0x00002254: c0 03 5f d6              ret      
0x00002258: 40 05 00 94              bl       #0x3758
