; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/xp/libdoc_photo_process.so
; target  : address 0x88f80
; start   : 0x88f80
; size    : 0x280
; bytes   : 0x280   instructions: 160
; generated: 2026-10-24T00:53:30

0x00088f80: 18 c0 ff 97              bl       #0x78fe0
0x00088f84: e0 e3 17 91              add      x0, sp, #0x5f8
0x00088f88: 16 c0 ff 97              bl       #0x78fe0
0x00088f8c: e0 63 19 91              add      x0, sp, #0x658
0x00088f90: 14 c0 ff 97              bl       #0x78fe0
0x00088f94: a0 e3 02 d1              sub      x0, x29, #0xb8
0x00088f98: 12 c0 ff 97              bl       #0x78fe0
0x00088f9c: e0 03 13 aa              mov      x0, x19
0x00088fa0: 41 aa 0e 94              bl       #0x4338a4
0x00088fa4: f3 0f 1e f8              str      x19, [sp, #-0x20]!
0x00088fa8: fd 7b 01 a9              stp      x29, x30, [sp, #0x10]
0x00088fac: fd 43 00 91              add      x29, sp, #0x10
0x00088fb0: f3 03 00 aa              mov      x19, x0
0x00088fb4: 00 40 03 91              add      x0, x0, #0xd0
0x00088fb8: 0a c0 ff 97              bl       #0x78fe0
0x00088fbc: 60 c2 01 91              add      x0, x19, #0x70
0x00088fc0: 08 c0 ff 97              bl       #0x78fe0
0x00088fc4: fd 7b 41 a9              ldp      x29, x30, [sp, #0x10]
0x00088fc8: 60 42 00 91              add      x0, x19, #0x10
0x00088fcc: f3 07 42 f8              ldr      x19, [sp], #0x20
0x00088fd0: 04 c0 ff 17              b        #0x78fe0
0x00088fd4: f4 4f be a9              stp      x20, x19, [sp, #-0x20]!
0x00088fd8: fd 7b 01 a9              stp      x29, x30, [sp, #0x10]
0x00088fdc: fd 43 00 91              add      x29, sp, #0x10
0x00088fe0: e8 5f a8 52              mov      w8, #0x42ff0000
0x00088fe4: 08 00 00 b9              str      w8, [x0]
0x00088fe8: 08 20 00 91              add      x8, x0, #8
0x00088fec: 08 20 00 f9              str      x8, [x0, #0x40]
0x00088ff0: e8 03 00 aa              mov      x8, x0
0x00088ff4: 1f 3c 00 b9              str      wzr, [x0, #0x3c]
0x00088ff8: 1f 40 03 f8              stur     xzr, [x0, #0x34]
0x00088ffc: 1f c0 02 f8              stur     xzr, [x0, #0x2c]
0x00089000: 1f 40 02 f8              stur     xzr, [x0, #0x24]
0x00089004: 1f c0 01 f8              stur     xzr, [x0, #0x1c]
0x00089008: 1f 40 01 f8              stur     xzr, [x0, #0x14]
0x0008900c: 1f c0 00 f8              stur     xzr, [x0, #0xc]
0x00089010: 1f 40 00 f8              stur     xzr, [x0, #4]
0x00089014: 1f 0d 05 f8              str      xzr, [x8, #0x50]!
0x00089018: 1f 2c 00 f9              str      xzr, [x0, #0x58]
0x0008901c: 08 24 00 f9              str      x8, [x0, #0x48]
0x00089020: 28 04 40 29              ldp      w8, w1, [x1]
0x00089024: f3 03 03 aa              mov      x19, x3
0x00089028: e3 03 02 2a              mov      w3, w2
0x0008902c: f4 03 00 aa              mov      x20, x0
0x00089030: e2 03 08 2a              mov      w2, w8
0x00089034: fe e0 ff 97              bl       #0x8142c
0x00089038: fd 7b 41 a9              ldp      x29, x30, [sp, #0x10]
0x0008903c: e0 03 14 aa              mov      x0, x20
0x00089040: e1 03 13 aa              mov      x1, x19
0x00089044: f4 4f c2 a8              ldp      x20, x19, [sp], #0x20
0x00089048: 84 56 08 14              b        #0x29ea58
0x0008904c: ff 83 01 d1              sub      sp, sp, #0x60
0x00089050: f5 1b 00 f9              str      x21, [sp, #0x30]
0x00089054: f4 4f 04 a9              stp      x20, x19, [sp, #0x40]
0x00089058: fd 7b 05 a9              stp      x29, x30, [sp, #0x50]
0x0008905c: fd 43 01 91              add      x29, sp, #0x50
0x00089060: 55 d0 3b d5              mrs      x21, tpidr_el0
0x00089064: a8 16 40 f9              ldr      x8, [x21, #0x28]
0x00089068: 00 17 80 52              mov      w0, #0xb8
0x0008906c: e8 17 00 f9              str      x8, [sp, #0x28]
0x00089070: 33 51 0e 94              bl       #0x41d53c
0x00089074: f3 03 00 aa              mov      x19, x0
0x00089078: 2b 00 00 94              bl       #0x89124
0x0008907c: 61 1d 00 b0              adrp     x1, #0x436000
0x00089080: 60 22 00 91              add      x0, x19, #8
0x00089084: 21 88 3f 91              add      x1, x1, #0xfe2
0x00089088: de b5 ff 97              bl       #0x76800
0x0008908c: 61 1d 00 b0              adrp     x1, #0x436000
0x00089090: 60 82 00 91              add      x0, x19, #0x20
0x00089094: 21 b0 3f 91              add      x1, x1, #0xfec
0x00089098: da b5 ff 97              bl       #0x76800
0x0008909c: 68 1d 00 d0              adrp     x8, #0x437000
0x000890a0: 08 21 00 91              add      x8, x8, #8
0x000890a4: 01 01 40 ad              ldp      q1, q0, [x8]
0x000890a8: e8 03 00 91              mov      x8, sp
0x000890ac: 14 81 00 91              add      x20, x8, #0x20
0x000890b0: 60 62 01 91              add      x0, x19, #0x58
0x000890b4: e1 03 00 91              mov      x1, sp
0x000890b8: e2 03 14 aa              mov      x2, x20
0x000890bc: e1 03 00 ad              stp      q1, q0, [sp]
0x000890c0: 8e 00 00 94              bl       #0x892f8
0x000890c4: 61 1d 00 b0              adrp     x1, #0x436000
0x000890c8: 60 e2 00 91              add      x0, x19, #0x38
0x000890cc: 21 d8 3f 91              add      x1, x1, #0xff6
0x000890d0: cc b5 ff 97              bl       #0x76800
0x000890d4: 68 1d 00 d0              adrp     x8, #0x437000
0x000890d8: 08 a1 00 91              add      x8, x8, #0x28
0x000890dc: 01 01 40 ad              ldp      q1, q0, [x8]
0x000890e0: 60 c2 01 91              add      x0, x19, #0x70
0x000890e4: e1 03 00 91              mov      x1, sp
0x000890e8: e2 03 14 aa              mov      x2, x20
0x000890ec: e1 03 00 ad              stp      q1, q0, [sp]
0x000890f0: 82 00 00 94              bl       #0x892f8
0x000890f4: 7f 8a 00 b9              str      wzr, [x19, #0x88]
0x000890f8: a8 16 40 f9              ldr      x8, [x21, #0x28]
0x000890fc: e9 17 40 f9              ldr      x9, [sp, #0x28]
0x00089100: 1f 01 09 eb              cmp      x8, x9
0x00089104: e1 00 00 54              b.ne     #0x89120
0x00089108: e0 03 13 aa              mov      x0, x19
0x0008910c: fd 7b 45 a9              ldp      x29, x30, [sp, #0x50]
0x00089110: f4 4f 44 a9              ldp      x20, x19, [sp, #0x40]
0x00089114: f5 1b 40 f9              ldr      x21, [sp, #0x30]
0x00089118: ff 83 01 91              add      sp, sp, #0x60
0x0008911c: c0 03 5f d6              ret      
0x00089120: c4 7c ff 97              bl       #0x68430                   ; -> __stack_chk_fail@plt
0x00089124: e8 03 1d 32              mov      w8, #8
0x00089128: 1f fc 00 a9              stp      xzr, xzr, [x0, #8]
0x0008912c: 1f 0c 00 f9              str      xzr, [x0, #0x18]
0x00089130: 03 00 00 14              b        #0x8913c
0x00089134: 1f 68 28 f8              str      xzr, [x0, x8]
0x00089138: 08 21 00 91              add      x8, x8, #8
0x0008913c: 1f 81 00 f1              cmp      x8, #0x20
0x00089140: a1 ff ff 54              b.ne     #0x89134
0x00089144: e9 03 00 aa              mov      x9, x0
0x00089148: e8 03 1f aa              mov      x8, xzr
0x0008914c: 3f 0d 02 f8              str      xzr, [x9, #0x20]!
0x00089150: 3f fd 00 a9              stp      xzr, xzr, [x9, #8]
0x00089154: 03 00 00 14              b        #0x89160
0x00089158: 3f 69 28 f8              str      xzr, [x9, x8]
0x0008915c: 08 21 00 91              add      x8, x8, #8
0x00089160: 1f 61 00 f1              cmp      x8, #0x18
0x00089164: a1 ff ff 54              b.ne     #0x89158
0x00089168: e9 03 00 aa              mov      x9, x0
0x0008916c: e8 03 1f aa              mov      x8, xzr
0x00089170: 3f 8d 03 f8              str      xzr, [x9, #0x38]!
0x00089174: 3f fd 00 a9              stp      xzr, xzr, [x9, #8]
0x00089178: 03 00 00 14              b        #0x89184
0x0008917c: 3f 69 28 f8              str      xzr, [x9, x8]
0x00089180: 08 21 00 91              add      x8, x8, #8
0x00089184: 1f 61 00 f1              cmp      x8, #0x18
0x00089188: a1 ff ff 54              b.ne     #0x8917c
0x0008918c: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x00089190: e1 e7 07 2f              movi     d1, #0xffffffffffffffff
0x00089194: 1f 88 00 b9              str      wzr, [x0, #0x88]
0x00089198: 00 80 87 3c              stur     q0, [x0, #0x78]
0x0008919c: 00 80 86 3c              stur     q0, [x0, #0x68]
0x000891a0: 00 80 85 3c              stur     q0, [x0, #0x58]
0x000891a4: 01 58 00 fd              str      d1, [x0, #0xb0]
0x000891a8: c0 03 5f d6              ret      
0x000891ac: f3 0f 1e f8              str      x19, [sp, #-0x20]!
0x000891b0: fd 7b 01 a9              stp      x29, x30, [sp, #0x10]
0x000891b4: fd 43 00 91              add      x29, sp, #0x10
0x000891b8: f3 03 00 aa              mov      x19, x0
0x000891bc: 5d f2 ff 97              bl       #0x85b30
0x000891c0: fd 7b 41 a9              ldp      x29, x30, [sp, #0x10]
0x000891c4: e0 03 13 aa              mov      x0, x19
0x000891c8: f3 07 42 f8              ldr      x19, [sp], #0x20
0x000891cc: f6 50 0e 14              b        #0x41d5a4
0x000891d0: f6 57 bd a9              stp      x22, x21, [sp, #-0x30]!
0x000891d4: f4 4f 01 a9              stp      x20, x19, [sp, #0x10]
0x000891d8: fd 7b 02 a9              stp      x29, x30, [sp, #0x20]
0x000891dc: fd 83 00 91              add      x29, sp, #0x20
0x000891e0: 08 08 40 f9              ldr      x8, [x0, #0x10]
0x000891e4: 09 00 40 f9              ldr      x9, [x0]
0x000891e8: 4a 00 01 cb              sub      x10, x2, x1
0x000891ec: 56 fd 43 93              asr      x22, x10, #3
0x000891f0: f4 03 02 aa              mov      x20, x2
0x000891f4: 08 01 09 cb              sub      x8, x8, x9
0x000891f8: f5 03 01 aa              mov      x21, x1
0x000891fc: f3 03 00 aa              mov      x19, x0
