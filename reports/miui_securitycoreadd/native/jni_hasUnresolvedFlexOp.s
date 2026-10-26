; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_securitycoreadd/native/libtensorflowlite_jni.so
; target  : Java_org_tensorflow_lite_NativeInterpreterWrapper_hasUnresolvedFlexOp (0x12cb0)
; start   : 0x12cb0
; size    : 0x118
; bytes   : 0x118   instructions: 70
; generated: 2026-10-23T23:27:54

0x00012cb0: fa 67 bb a9              stp      x26, x25, [sp, #-0x50]!
0x00012cb4: f8 5f 01 a9              stp      x24, x23, [sp, #0x10]
0x00012cb8: f6 57 02 a9              stp      x22, x21, [sp, #0x20]
0x00012cbc: f4 4f 03 a9              stp      x20, x19, [sp, #0x30]
0x00012cc0: fd 7b 04 a9              stp      x29, x30, [sp, #0x40]
0x00012cc4: fd 03 01 91              add      x29, sp, #0x40
0x00012cc8: 48 04 00 91              add      x8, x2, #1
0x00012ccc: 1f 05 00 f1              cmp      x8, #1
0x00012cd0: e8 00 00 54              b.hi     #0x12cec
0x00012cd4: e1 0b 00 d0              adrp     x1, #0x190000
0x00012cd8: 21 a8 45 f9              ldr      x1, [x1, #0xb50]
0x00012cdc: 22 0a 00 90              adrp     x2, #0x156000
0x00012ce0: 42 98 0d 91              add      x2, x2, #0x366
0x00012ce4: d9 fd ff 97              bl       #0x12448
0x00012ce8: 05 00 00 14              b        #0x12cfc                   ; -> Java_org_tensorflow_lite_NativeInterpreterWrapper_hasUnresolvedFlexOp+0x4c
0x00012cec: 48 a4 46 a9              ldp      x8, x9, [x2, #0x68]
0x00012cf0: f3 03 02 aa              mov      x19, x2
0x00012cf4: 3f 01 08 eb              cmp      x9, x8
0x00012cf8: 61 00 00 54              b.ne     #0x12d04
0x00012cfc: e0 03 1f 2a              mov      w0, wzr
0x00012d00: 2c 00 00 14              b        #0x12db0                   ; -> Java_org_tensorflow_lite_NativeInterpreterWrapper_hasUnresolvedFlexOp+0x100
0x00012d04: 35 c7 91 d2              mov      x21, #0x8e39
0x00012d08: 75 1c a7 f2              movk     x21, #0x38e3, lsl #16
0x00012d0c: d5 71 dc f2              movk     x21, #0xe38e, lsl #32
0x00012d10: f4 03 1f aa              mov      x20, xzr
0x00012d14: 15 c7 f1 f2              movk     x21, #0x8e38, lsl #48
0x00012d18: 16 12 80 52              mov      w22, #0x90
0x00012d1c: 06 00 00 14              b        #0x12d34                   ; -> Java_org_tensorflow_lite_NativeInterpreterWrapper_hasUnresolvedFlexOp+0x84
0x00012d20: 68 a6 46 a9              ldp      x8, x9, [x19, #0x68]
0x00012d24: 94 06 00 91              add      x20, x20, #1
0x00012d28: 2a 01 08 cb              sub      x10, x9, x8
0x00012d2c: 9f 0e 8a eb              cmp      x20, x10, asr #3
0x00012d30: 62 fe ff 54              b.hs     #0x12cfc
0x00012d34: 8a 7e 7d 93              sbfiz    x10, x20, #3, #0x20
0x00012d38: 17 69 6a f8              ldr      x23, [x8, x10]
0x00012d3c: f8 e6 56 a9              ldp      x24, x25, [x23, #0x168]
0x00012d40: 1f 03 19 eb              cmp      x24, x25
0x00012d44: 01 01 00 54              b.ne     #0x12d64
0x00012d48: f7 ff ff 17              b        #0x12d24                   ; -> Java_org_tensorflow_lite_NativeInterpreterWrapper_hasUnresolvedFlexOp+0x74
0x00012d4c: 40 43 40 f9              ldr      x0, [x26, #0x80]
0x00012d50: 6f bb 02 94              bl       #0xc1b0c
0x00012d54: c0 02 00 37              tbnz     w0, #0, #0x12dac
0x00012d58: 18 13 00 91              add      x24, x24, #4
0x00012d5c: 3f 03 18 eb              cmp      x25, x24
0x00012d60: 00 fe ff 54              b.eq     #0x12d20
0x00012d64: 08 03 80 b9              ldrsw    x8, [x24]
0x00012d68: 88 01 f8 37              tbnz     w8, #0x1f, #0x12d98
0x00012d6c: e9 aa 4e a9              ldp      x9, x10, [x23, #0xe8]
0x00012d70: 4a 01 09 cb              sub      x10, x10, x9
0x00012d74: 4a fd 44 93              asr      x10, x10, #4
0x00012d78: 4a 7d 15 9b              mul      x10, x10, x21
0x00012d7c: 5f 01 08 eb              cmp      x10, x8
0x00012d80: c9 00 00 54              b.ls     #0x12d98
0x00012d84: 1a 25 16 9b              madd     x26, x8, x22, x9
0x00012d88: 40 43 01 91              add      x0, x26, #0x50
0x00012d8c: 4a bc 02 94              bl       #0xc1eb4
0x00012d90: 40 fe 07 36              tbz      w0, #0, #0x12d58
0x00012d94: ee ff ff 17              b        #0x12d4c                   ; -> Java_org_tensorflow_lite_NativeInterpreterWrapper_hasUnresolvedFlexOp+0x9c
0x00012d98: fa 03 1f aa              mov      x26, xzr
0x00012d9c: 40 43 01 91              add      x0, x26, #0x50
0x00012da0: 45 bc 02 94              bl       #0xc1eb4
0x00012da4: 40 fd 07 37              tbnz     w0, #0, #0x12d4c
0x00012da8: ec ff ff 17              b        #0x12d58                   ; -> Java_org_tensorflow_lite_NativeInterpreterWrapper_hasUnresolvedFlexOp+0xa8
0x00012dac: 20 00 80 52              mov      w0, #1
0x00012db0: fd 7b 44 a9              ldp      x29, x30, [sp, #0x40]
0x00012db4: f4 4f 43 a9              ldp      x20, x19, [sp, #0x30]
0x00012db8: f6 57 42 a9              ldp      x22, x21, [sp, #0x20]
0x00012dbc: f8 5f 41 a9              ldp      x24, x23, [sp, #0x10]
0x00012dc0: fa 67 c5 a8              ldp      x26, x25, [sp], #0x50
0x00012dc4: c0 03 5f d6              ret      
