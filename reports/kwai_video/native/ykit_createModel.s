; disasm_elf.py (capstone 5.0.7)
; file    : /tmp/kwai_libs/lib/arm64-v8a/libykit.so
; target  : Java_com_kwai_kscnnrenderlib_KSJNILib_createModel (0xc81e8)
; start   : 0xc81e8
; size    : 0xbc
; bytes   : 0xbc   instructions: 47
; generated: 2026-10-23T23:49:11

0x000c81e8: ff 03 01 d1              sub      sp, sp, #0x40
0x000c81ec: f5 0b 00 f9              str      x21, [sp, #0x10]
0x000c81f0: f4 4f 02 a9              stp      x20, x19, [sp, #0x20]
0x000c81f4: fd 7b 03 a9              stp      x29, x30, [sp, #0x30]
0x000c81f8: fd c3 00 91              add      x29, sp, #0x30
0x000c81fc: 55 d0 3b d5              mrs      x21, tpidr_el0
0x000c8200: a8 16 40 f9              ldr      x8, [x21, #0x28]
0x000c8204: 41 3b 00 f0              adrp     x1, #0x833000
0x000c8208: 21 40 2d 91              add      x1, x1, #0xb50
0x000c820c: f3 03 00 aa              mov      x19, x0
0x000c8210: e8 07 00 f9              str      x8, [sp, #8]
0x000c8214: 08 00 40 f9              ldr      x8, [x0]
0x000c8218: f4 03 02 aa              mov      x20, x2
0x000c821c: 08 19 40 f9              ldr      x8, [x8, #0x30]
0x000c8220: 00 01 3f d6              blr      x8
0x000c8224: 68 02 40 f9              ldr      x8, [x19]
0x000c8228: 42 3b 00 f0              adrp     x2, #0x833000
0x000c822c: 43 3b 00 f0              adrp     x3, #0x833000
0x000c8230: e1 03 00 aa              mov      x1, x0
0x000c8234: 08 79 41 f9              ldr      x8, [x8, #0x2f0]
0x000c8238: 42 74 30 91              add      x2, x2, #0xc1d
0x000c823c: 63 b4 30 91              add      x3, x3, #0xc2d
0x000c8240: e0 03 13 aa              mov      x0, x19
0x000c8244: 00 01 3f d6              blr      x8
0x000c8248: 68 02 40 f9              ldr      x8, [x19]
0x000c824c: e2 03 00 aa              mov      x2, x0
0x000c8250: e0 03 13 aa              mov      x0, x19
0x000c8254: e1 03 14 aa              mov      x1, x20
0x000c8258: 08 95 41 f9              ldr      x8, [x8, #0x328]
0x000c825c: 00 01 3f d6              blr      x8
0x000c8260: e0 03 00 f9              str      x0, [sp]
0x000c8264: e3 03 00 91              mov      x3, sp
0x000c8268: e0 03 13 aa              mov      x0, x19
0x000c826c: e2 03 14 aa              mov      x2, x20
0x000c8270: e4 03 1f 2a              mov      w4, wzr
0x000c8274: 37 f6 ff 97              bl       #0xc5b50
0x000c8278: a8 16 40 f9              ldr      x8, [x21, #0x28]
0x000c827c: e9 07 40 f9              ldr      x9, [sp, #8]
0x000c8280: 1f 01 09 eb              cmp      x8, x9
0x000c8284: e1 00 00 54              b.ne     #0xc82a0
0x000c8288: fd 7b 43 a9              ldp      x29, x30, [sp, #0x30]
0x000c828c: f4 4f 42 a9              ldp      x20, x19, [sp, #0x20]
0x000c8290: f5 0b 40 f9              ldr      x21, [sp, #0x10]
0x000c8294: e0 03 1f 2a              mov      w0, wzr
0x000c8298: ff 03 01 91              add      sp, sp, #0x40
0x000c829c: c0 03 5f d6              ret      
0x000c82a0: b4 cd ff 97              bl       #0xbb970                   ; -> __stack_chk_fail@plt
