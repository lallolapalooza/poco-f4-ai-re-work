; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libqvajni.so
; target  : address 0x1302c
; start   : 0x1302c
; size    : 0x2d8
; bytes   : 0x2d8   instructions: 182
; generated: 2026-10-23T23:38:47

0x0001302c: 3f 23 03 d5              paciasp  
0x00013030: ff 43 02 d1              sub      sp, sp, #0x90
0x00013034: fd 7b 04 a9              stp      x29, x30, [sp, #0x40]
0x00013038: fa 67 05 a9              stp      x26, x25, [sp, #0x50]
0x0001303c: f8 5f 06 a9              stp      x24, x23, [sp, #0x60]
0x00013040: f6 57 07 a9              stp      x22, x21, [sp, #0x70]
0x00013044: f4 4f 08 a9              stp      x20, x19, [sp, #0x80]
0x00013048: fd 03 01 91              add      x29, sp, #0x40
0x0001304c: 59 d0 3b d5              mrs      x25, tpidr_el0
0x00013050: 13 40 05 91              add      x19, x0, #0x150
0x00013054: f4 03 00 aa              mov      x20, x0
0x00013058: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x0001305c: e0 03 13 aa              mov      x0, x19
0x00013060: f7 03 03 2a              mov      w23, w3
0x00013064: f5 03 02 2a              mov      w21, w2
0x00013068: f6 03 01 aa              mov      x22, x1
0x0001306c: a8 83 1f f8              stur     x8, [x29, #-8]
0x00013070: da 32 00 94              bl       #0x1fbd8
0x00013074: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x00013078: e8 16 00 51              sub      w8, w23, #5
0x0001307c: 1f 49 00 71              cmp      w8, #0x12
0x00013080: bf ff 3e a9              stp      xzr, xzr, [x29, #-0x18]
0x00013084: e0 03 00 ad              stp      q0, q0, [sp]
0x00013088: 88 01 00 54              b.hi     #0x130b8
0x0001308c: e9 c0 9e 52              mov      w9, #0xf607
0x00013090: e9 00 a0 72              movk     w9, #7, lsl #16
0x00013094: 29 25 c8 1a              lsr      w9, w9, w8
0x00013098: 09 01 00 36              tbz      w9, #0, #0x130b8
0x0001309c: 1f 20 03 d5              nop      
0x000130a0: 49 fb 06 10              adr      x9, #0x21008
0x000130a4: 80 52 00 91              add      x0, x20, #0x14
0x000130a8: 02 20 80 52              mov      w2, #0x100
0x000130ac: 21 d9 68 f8              ldr      x1, [x9, w8, sxtw #3]
0x000130b0: 03 20 80 52              mov      w3, #0x100
0x000130b4: 99 32 00 94              bl       #0x1fb18
0x000130b8: ff 5e 00 71              cmp      w23, #0x17
0x000130bc: 48 0c 00 54              b.hi     #0x13244
0x000130c0: 28 00 80 52              mov      w8, #1
0x000130c4: 09 70 80 52              mov      w9, #0x380
0x000130c8: 08 21 d7 1a              lsl      w8, w8, w23
0x000130cc: 09 1f a0 72              movk     w9, #0xf8, lsl #16
0x000130d0: 1f 01 09 6a              tst      w8, w9
0x000130d4: c0 00 00 54              b.eq     #0x130ec
0x000130d8: 48 00 80 52              mov      w8, #2
0x000130dc: 20 00 80 52              mov      w0, #1
0x000130e0: 01 05 80 52              mov      w1, #0x28
0x000130e4: 88 12 00 b9              str      w8, [x20, #0x10]
0x000130e8: 09 00 00 14              b        #0x1310c                   ; -> _ZN9Algorithm4initEPajj+0xe0
0x000130ec: 09 00 98 52              mov      w9, #0xc000
0x000130f0: c9 00 a0 72              movk     w9, #6, lsl #16
0x000130f4: 1f 01 09 6a              tst      w8, w9
0x000130f8: 80 07 00 54              b.eq     #0x131e8
0x000130fc: 28 00 80 52              mov      w8, #1
0x00013100: 20 00 80 52              mov      w0, #1
0x00013104: 01 02 80 52              mov      w1, #0x10
0x00013108: 88 12 00 b9              str      w8, [x20, #0x10]
0x0001310c: c9 31 00 94              bl       #0x1f830                   ; -> calloc@plt
0x00013110: 80 06 00 f9              str      x0, [x20, #8]
0x00013114: e0 09 00 b4              cbz      x0, #0x13250
0x00013118: 80 52 00 91              add      x0, x20, #0x14
0x0001311c: 41 00 80 52              mov      w1, #2
0x00013120: b4 32 00 94              bl       #0x1fbf0                   ; -> dlopen@plt
0x00013124: 80 02 00 f9              str      x0, [x20]
0x00013128: c0 04 00 b4              cbz      x0, #0x131c0
0x0001312c: a1 ff ff 90              adrp     x1, #0x7000
0x00013130: 21 0c 39 91              add      x1, x1, #0xe43
0x00013134: b5 32 00 94              bl       #0x1fc08
0x00013138: 80 06 00 b4              cbz      x0, #0x13208
0x0001313c: f8 03 15 2a              mov      w24, w21
0x00013140: f7 03 00 aa              mov      x23, x0
0x00013144: 20 00 80 52              mov      w0, #1
0x00013148: e1 03 18 aa              mov      x1, x24
0x0001314c: 3a 00 80 52              mov      w26, #1
0x00013150: b8 31 00 94              bl       #0x1f830                   ; -> calloc@plt
0x00013154: 80 a6 00 f9              str      x0, [x20, #0x148]
0x00013158: c0 06 00 b4              cbz      x0, #0x13230
0x0001315c: e1 03 18 aa              mov      x1, x24
0x00013160: e2 03 16 aa              mov      x2, x22
0x00013164: e3 03 18 aa              mov      x3, x24
0x00013168: 75 31 00 94              bl       #0x1f73c
0x0001316c: c8 00 80 52              mov      w8, #6
0x00013170: 89 a6 40 f9              ldr      x9, [x20, #0x148]
0x00013174: 28 00 a0 72              movk     w8, #1, lsl #16
0x00013178: ea 03 00 91              mov      x10, sp
0x0001317c: 80 06 40 f9              ldr      x0, [x20, #8]
0x00013180: a1 63 00 d1              sub      x1, x29, #0x18
0x00013184: f5 57 02 29              stp      w21, w21, [sp, #0x10]
0x00013188: e8 03 00 b9              str      w8, [sp]
0x0001318c: e9 07 00 f9              str      x9, [sp, #8]
0x00013190: aa 03 1f f8              stur     x10, [x29, #-0x10]
0x00013194: ba 83 1e b8              stur     w26, [x29, #-0x18]
0x00013198: e0 02 3f d6              blr      x23
0x0001319c: a0 09 00 34              cbz      w0, #0x132d0
0x000131a0: 81 ff ff d0              adrp     x1, #0x5000
0x000131a4: 21 cc 36 91              add      x1, x1, #0xdb3
0x000131a8: 82 ff ff d0              adrp     x2, #0x5000
0x000131ac: 42 88 26 91              add      x2, x2, #0x9a2
0x000131b0: c0 00 80 52              mov      w0, #6
0x000131b4: 8d 31 00 94              bl       #0x1f7e8
0x000131b8: 95 00 80 12              mov      w21, #-5
0x000131bc: 2c 00 00 14              b        #0x1326c                   ; -> _ZN9Algorithm4initEPajj+0x240
0x000131c0: 98 32 00 94              bl       #0x1fc20                   ; -> dlerror@plt
0x000131c4: e3 03 00 aa              mov      x3, x0
0x000131c8: 81 ff ff d0              adrp     x1, #0x5000
0x000131cc: 21 cc 36 91              add      x1, x1, #0xdb3
0x000131d0: a2 ff ff 90              adrp     x2, #0x7000
0x000131d4: 42 ac 2d 91              add      x2, x2, #0xb6b
0x000131d8: c0 00 80 52              mov      w0, #6
0x000131dc: 83 31 00 94              bl       #0x1f7e8
0x000131e0: 55 00 80 12              mov      w21, #-3
0x000131e4: 22 00 00 14              b        #0x1326c                   ; -> _ZN9Algorithm4initEPajj+0x240
0x000131e8: 28 00 80 52              mov      w8, #1
0x000131ec: 08 21 d7 1a              lsl      w8, w8, w23
0x000131f0: 1f 05 1b 72              tst      w8, #0x60
0x000131f4: 80 02 00 54              b.eq     #0x13244
0x000131f8: 68 00 80 52              mov      w8, #3
0x000131fc: 20 00 80 52              mov      w0, #1
0x00013200: 01 07 80 52              mov      w1, #0x38
0x00013204: b8 ff ff 17              b        #0x130e4                   ; -> _ZN9Algorithm4initEPajj+0xb8
0x00013208: 86 32 00 94              bl       #0x1fc20                   ; -> dlerror@plt
0x0001320c: e3 03 00 aa              mov      x3, x0
0x00013210: 81 ff ff d0              adrp     x1, #0x5000
0x00013214: 21 cc 36 91              add      x1, x1, #0xdb3
0x00013218: 82 ff ff f0              adrp     x2, #0x6000
0x0001321c: 42 84 28 91              add      x2, x2, #0xa21
0x00013220: c0 00 80 52              mov      w0, #6
0x00013224: 71 31 00 94              bl       #0x1f7e8
0x00013228: 75 00 80 12              mov      w21, #-4
0x0001322c: 10 00 00 14              b        #0x1326c                   ; -> _ZN9Algorithm4initEPajj+0x240
0x00013230: 81 ff ff d0              adrp     x1, #0x5000
0x00013234: 21 cc 36 91              add      x1, x1, #0xdb3
0x00013238: a2 ff ff b0              adrp     x2, #0x8000
0x0001323c: 42 a8 17 91              add      x2, x2, #0x5ea
0x00013240: 08 00 00 14              b        #0x13260                   ; -> _ZN9Algorithm4initEPajj+0x234
0x00013244: 80 06 40 f9              ldr      x0, [x20, #8]
0x00013248: 9f 12 00 b9              str      wzr, [x20, #0x10]
0x0001324c: 60 f6 ff b5              cbnz     x0, #0x13118
0x00013250: 81 ff ff d0              adrp     x1, #0x5000
0x00013254: 21 cc 36 91              add      x1, x1, #0xdb3
0x00013258: a2 ff ff b0              adrp     x2, #0x8000
0x0001325c: 42 c0 2f 91              add      x2, x2, #0xbf0
0x00013260: c0 00 80 52              mov      w0, #6
0x00013264: 61 31 00 94              bl       #0x1f7e8
0x00013268: d5 00 80 12              mov      w21, #-7
0x0001326c: 88 06 40 f9              ldr      x8, [x20, #8]
0x00013270: a8 00 00 b4              cbz      x8, #0x13284
0x00013274: 1f 01 00 f9              str      xzr, [x8]
0x00013278: 80 06 40 f9              ldr      x0, [x20, #8]
0x0001327c: 79 31 00 94              bl       #0x1f860                   ; -> free@plt
0x00013280: 9f 06 00 f9              str      xzr, [x20, #8]
0x00013284: 80 02 40 f9              ldr      x0, [x20]
0x00013288: 60 00 00 b4              cbz      x0, #0x13294
0x0001328c: 17 32 00 94              bl       #0x1fae8
0x00013290: 9f 02 00 f9              str      xzr, [x20]
0x00013294: e0 03 13 aa              mov      x0, x19
0x00013298: 68 32 00 94              bl       #0x1fc38
0x0001329c: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x000132a0: a9 83 5f f8              ldur     x9, [x29, #-8]
0x000132a4: 1f 01 09 eb              cmp      x8, x9
0x000132a8: c1 02 00 54              b.ne     #0x13300
0x000132ac: e0 03 15 2a              mov      w0, w21
0x000132b0: f4 4f 48 a9              ldp      x20, x19, [sp, #0x80]
0x000132b4: f6 57 47 a9              ldp      x22, x21, [sp, #0x70]
0x000132b8: f8 5f 46 a9              ldp      x24, x23, [sp, #0x60]
0x000132bc: fa 67 45 a9              ldp      x26, x25, [sp, #0x50]
0x000132c0: fd 7b 44 a9              ldp      x29, x30, [sp, #0x40]
0x000132c4: ff 43 02 91              add      sp, sp, #0x90
0x000132c8: bf 23 03 d5              autiasp  
0x000132cc: c0 03 5f d6              ret      
0x000132d0: 88 06 40 f9              ldr      x8, [x20, #8]
0x000132d4: 08 01 40 f9              ldr      x8, [x8]
0x000132d8: a8 00 00 b4              cbz      x8, #0x132ec
0x000132dc: 28 00 80 52              mov      w8, #1
0x000132e0: f5 03 1f 2a              mov      w21, wzr
0x000132e4: 88 46 01 b9              str      w8, [x20, #0x144]
0x000132e8: eb ff ff 17              b        #0x13294                   ; -> _ZN9Algorithm4initEPajj+0x268
0x000132ec: 81 ff ff d0              adrp     x1, #0x5000
0x000132f0: 21 cc 36 91              add      x1, x1, #0xdb3
0x000132f4: 82 ff ff f0              adrp     x2, #0x6000
0x000132f8: 42 20 29 91              add      x2, x2, #0xa48
0x000132fc: ad ff ff 17              b        #0x131b0                   ; -> _ZN9Algorithm4initEPajj+0x184
0x00013300: 76 31 00 94              bl       #0x1f8d8
