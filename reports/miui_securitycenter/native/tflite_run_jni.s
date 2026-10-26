; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_securitycenter/inventory/libs/lib/arm64-v8a/libtensorflowlite_jni.so
; target  : Java_org_tensorflow_lite_NativeInterpreterWrapper_run (0x13234)
; start   : 0x13234
; size    : 0xac
; bytes   : 0xac   instructions: 43
; generated: 2026-10-24T00:41:00

0x00013234: f5 0f 1d f8              str      x21, [sp, #-0x30]!
0x00013238: f4 4f 01 a9              stp      x20, x19, [sp, #0x10]
0x0001323c: fd 7b 02 a9              stp      x29, x30, [sp, #0x20]
0x00013240: fd 83 00 91              add      x29, sp, #0x20
0x00013244: f4 03 03 aa              mov      x20, x3
0x00013248: f5 03 02 aa              mov      x21, x2
0x0001324c: f3 03 00 aa              mov      x19, x0
0x00013250: ac f8 ff 97              bl       #0x11500
0x00013254: e0 03 00 36              tbz      w0, #0, #0x132d0
0x00013258: a8 06 00 91              add      x8, x21, #1
0x0001325c: 1f 05 00 f1              cmp      x8, #1
0x00013260: 89 00 00 54              b.ls     #0x13270
0x00013264: 88 06 00 91              add      x8, x20, #1
0x00013268: 1f 05 00 f1              cmp      x8, #1
0x0001326c: 48 01 00 54              b.hi     #0x13294
0x00013270: c1 0c 00 d0              adrp     x1, #0x1ad000
0x00013274: 21 c8 46 f9              ldr      x1, [x1, #0xd90]
0x00013278: e0 03 13 aa              mov      x0, x19
0x0001327c: fd 7b 42 a9              ldp      x29, x30, [sp, #0x20]
0x00013280: f4 4f 41 a9              ldp      x20, x19, [sp, #0x10]
0x00013284: 62 0a 00 d0              adrp     x2, #0x161000
0x00013288: 42 f8 39 91              add      x2, x2, #0xe7e
0x0001328c: f5 07 43 f8              ldr      x21, [sp], #0x30
0x00013290: 46 f8 ff 17              b        #0x113a8
0x00013294: e0 03 15 aa              mov      x0, x21
0x00013298: 3e 2a 02 94              bl       #0x9db90
0x0001329c: a0 01 00 34              cbz      w0, #0x132d0
0x000132a0: e0 03 14 aa              mov      x0, x20
0x000132a4: f5 f8 ff 97              bl       #0x11678
0x000132a8: c1 0c 00 d0              adrp     x1, #0x1ad000
0x000132ac: 21 c8 46 f9              ldr      x1, [x1, #0xd90]
0x000132b0: e3 03 00 aa              mov      x3, x0
0x000132b4: e0 03 13 aa              mov      x0, x19
0x000132b8: fd 7b 42 a9              ldp      x29, x30, [sp, #0x20]
0x000132bc: f4 4f 41 a9              ldp      x20, x19, [sp, #0x10]
0x000132c0: 62 0a 00 d0              adrp     x2, #0x161000
0x000132c4: 42 64 3e 91              add      x2, x2, #0xf99
0x000132c8: f5 07 43 f8              ldr      x21, [sp], #0x30
0x000132cc: 37 f8 ff 17              b        #0x113a8
0x000132d0: fd 7b 42 a9              ldp      x29, x30, [sp, #0x20]
0x000132d4: f4 4f 41 a9              ldp      x20, x19, [sp, #0x10]
0x000132d8: f5 07 43 f8              ldr      x21, [sp], #0x30
0x000132dc: c0 03 5f d6              ret      
