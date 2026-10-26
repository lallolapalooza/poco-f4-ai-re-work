; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainsdk.so
; target  : address 0x41de0
; start   : 0x41de0
; size    : 0x300
; bytes   : 0x300   instructions: 192
; generated: 2026-10-23T23:52:09

0x00041de0: fd 7b bc a9              stp      x29, x30, [sp, #-0x40]!
0x00041de4: fd 03 00 91              mov      x29, sp
0x00041de8: f3 53 01 a9              stp      x19, x20, [sp, #0x10]
0x00041dec: f5 5b 02 a9              stp      x21, x22, [sp, #0x20]
0x00041df0: f4 03 00 aa              mov      x20, x0
0x00041df4: f6 03 03 2a              mov      w22, w3
0x00041df8: f3 03 02 2a              mov      w19, w2
0x00041dfc: 00 24 50 b9              ldr      w0, [x0, #0x1024]
0x00041e00: 63 06 00 35              cbnz     w3, #0x41ecc
0x00041e04: 1f 04 00 71              cmp      w0, #1
0x00041e08: 20 10 00 54              b.eq     #0x4200c
0x00041e0c: 84 42 50 b9              ldr      w4, [x20, #0x1040]
0x00041e10: 9f 22 10 b9              str      wzr, [x20, #0x1020]
0x00041e14: 9f 26 10 b9              str      wzr, [x20, #0x1024]
0x00041e18: a4 0e 00 35              cbnz     w4, #0x41fec
0x00041e1c: 20 00 80 52              mov      w0, #1
0x00041e20: 80 26 10 b9              str      w0, [x20, #0x1024]
0x00041e24: 04 06 00 34              cbz      w4, #0x41ee4
0x00041e28: e2 03 13 2a              mov      w2, w19
0x00041e2c: e0 03 14 aa              mov      x0, x20
0x00041e30: e3 03 16 2a              mov      w3, w22
0x00041e34: 73 02 04 0b              add      w19, w19, w4
0x00041e38: 9e fa ff 97              bl       #0x408b0
0x00041e3c: f5 03 00 2a              mov      w21, w0
0x00041e40: f5 05 f8 37              tbnz     w21, #0x1f, #0x41efc
0x00041e44: bf 12 00 71              cmp      w21, #4
0x00041e48: 4c 00 00 54              b.gt     #0x41e50
0x00041e4c: 96 0e 00 34              cbz      w22, #0x4201c
0x00041e50: 80 42 50 b9              ldr      w0, [x20, #0x1040]
0x00041e54: 95 ae 0f b9              str      w21, [x20, #0xfac]
0x00041e58: c0 0a 00 34              cbz      w0, #0x41fb0
0x00041e5c: bf 16 00 71              cmp      w21, #5
0x00041e60: ed 0a 00 54              b.le     #0x41fbc
0x00041e64: 80 26 48 f9              ldr      x0, [x20, #0x1048]
0x00041e68: e2 03 13 2a              mov      w2, w19
0x00041e6c: 81 1a 48 f9              ldr      x1, [x20, #0x1030]
0x00041e70: e3 03 15 2a              mov      w3, w21
0x00041e74: 72 09 00 94              bl       #0x4443c
0x00041e78: e0 0d 00 35              cbnz     w0, #0x42034
0x00041e7c: d5 04 00 34              cbz      w21, #0x41f14
0x00041e80: 13 00 80 52              mov      w19, #0
0x00041e84: 02 00 00 14              b        #0x41e8c
0x00041e88: 6d 04 00 54              b.le     #0x41f14
0x00041e8c: 81 1a 48 f9              ldr      x1, [x20, #0x1030]
0x00041e90: e2 03 13 2a              mov      w2, w19
0x00041e94: e0 03 14 aa              mov      x0, x20
0x00041e98: 73 06 00 11              add      w19, w19, #1
0x00041e9c: f5 fe ff 97              bl       #0x41a70
0x00041ea0: bf 02 13 6b              cmp      w21, w19
0x00041ea4: 20 ff ff 36              tbz      w0, #0x1f, #0x41e88
0x00041ea8: e0 03 00 d0              adrp     x0, #0xbf000
0x00041eac: e1 0b 80 12              mov      w1, #-0x60
0x00041eb0: 00 40 1a 91              add      x0, x0, #0x690
0x00041eb4: 83 21 ff 97              bl       #0xa4c0                    ; -> printf@plt
0x00041eb8: e0 0b 80 12              mov      w0, #-0x60
0x00041ebc: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x00041ec0: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x00041ec4: fd 7b c4 a8              ldp      x29, x30, [sp], #0x40
0x00041ec8: c0 03 5f d6              ret      
0x00041ecc: 7f 04 00 71              cmp      w3, #1
0x00041ed0: a0 07 00 54              b.eq     #0x41fc4
0x00041ed4: c0 09 00 34              cbz      w0, #0x4200c
0x00041ed8: 84 22 50 b9              ldr      w4, [x20, #0x1020]
0x00041edc: 9f 26 10 b9              str      wzr, [x20, #0x1024]
0x00041ee0: 44 fa ff 35              cbnz     w4, #0x41e28
0x00041ee4: e0 03 14 aa              mov      x0, x20
0x00041ee8: e2 03 13 2a              mov      w2, w19
0x00041eec: e3 03 16 2a              mov      w3, w22
0x00041ef0: 15 fa ff 97              bl       #0x40744
0x00041ef4: f5 03 00 2a              mov      w21, w0
0x00041ef8: 75 fa ff 36              tbz      w21, #0x1f, #0x41e44
0x00041efc: bf 62 01 31              cmn      w21, #0x58
0x00041f00: a0 12 9f 1a              csel     w0, w21, wzr, ne
0x00041f04: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x00041f08: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x00041f0c: fd 7b c4 a8              ldp      x29, x30, [sp], #0x40
0x00041f10: c0 03 5f d6              ret      
0x00041f14: 80 42 50 b9              ldr      w0, [x20, #0x1040]
0x00041f18: 20 04 00 34              cbz      w0, #0x41f9c
0x00041f1c: 80 5a 50 b9              ldr      w0, [x20, #0x1058]
0x00041f20: 80 05 00 35              cbnz     w0, #0x41fd0
0x00041f24: d5 03 00 34              cbz      w21, #0x41f9c
0x00041f28: a1 06 00 51              sub      w1, w21, #1
0x00041f2c: a0 12 00 51              sub      w0, w21, #4
0x00041f30: 00 7c 02 53              lsr      w0, w0, #2
0x00041f34: 3f 08 00 71              cmp      w1, #2
0x00041f38: 00 04 00 11              add      w0, w0, #1
0x00041f3c: 82 da 47 f9              ldr      x2, [x20, #0xfb0]
0x00041f40: 01 74 1e 53              lsl      w1, w0, #2
0x00041f44: 89 06 00 54              b.ls     #0x42014
0x00041f48: 00 04 00 4f              movi     v0.4s, #0
0x00041f4c: e4 03 02 aa              mov      x4, x2
0x00041f50: 03 00 80 52              mov      w3, #0
0x00041f54: 80 04 81 3c              str      q0, [x4], #0x10
0x00041f58: 63 04 00 11              add      w3, w3, #1
0x00041f5c: 1f 00 03 6b              cmp      w0, w3
0x00041f60: a8 ff ff 54              b.hi     #0x41f54
0x00041f64: 3f 00 15 6b              cmp      w1, w21
0x00041f68: a0 01 00 54              b.eq     #0x41f9c
0x00041f6c: e0 03 27 1e              fmov     s0, wzr
0x00041f70: 20 04 00 11              add      w0, w1, #1
0x00041f74: bf 02 00 6b              cmp      w21, w0
0x00041f78: 20 7c 7e 93              sbfiz    x0, x1, #2, #0x20
0x00041f7c: 40 d8 21 bc              str      s0, [x2, w1, sxtw #2]
0x00041f80: ed 00 00 54              b.le     #0x41f9c
0x00041f84: 40 00 00 8b              add      x0, x2, x0
0x00041f88: 21 08 00 11              add      w1, w1, #2
0x00041f8c: bf 02 01 6b              cmp      w21, w1
0x00041f90: 00 04 00 bd              str      s0, [x0, #4]
0x00041f94: 4d 00 00 54              b.le     #0x41f9c
0x00041f98: 00 08 00 bd              str      s0, [x0, #8]
0x00041f9c: 00 00 80 52              mov      w0, #0
0x00041fa0: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x00041fa4: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x00041fa8: fd 7b c4 a8              ldp      x29, x30, [sp], #0x40
0x00041fac: c0 03 5f d6              ret      
0x00041fb0: 95 f6 ff 35              cbnz     w21, #0x41e80
0x00041fb4: 00 00 80 52              mov      w0, #0
0x00041fb8: fa ff ff 17              b        #0x41fa0
0x00041fbc: 9f 5a 10 b9              str      wzr, [x20, #0x1058]
0x00041fc0: a9 ff ff 17              b        #0x41e64
0x00041fc4: 40 02 00 34              cbz      w0, #0x4200c
0x00041fc8: 84 22 50 b9              ldr      w4, [x20, #0x1020]
0x00041fcc: 96 ff ff 17              b        #0x41e24
0x00041fd0: 80 26 48 f9              ldr      x0, [x20, #0x1048]
0x00041fd4: e2 03 15 2a              mov      w2, w21
0x00041fd8: 81 da 47 f9              ldr      x1, [x20, #0xfb0]
0x00041fdc: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x00041fe0: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x00041fe4: fd 7b c4 a8              ldp      x29, x30, [sp], #0x40
0x00041fe8: e7 09 00 14              b        #0x44784
0x00041fec: 80 26 48 f9              ldr      x0, [x20, #0x1048]
0x00041ff0: 22 00 80 52              mov      w2, #1
0x00041ff4: 82 5a 10 b9              str      w2, [x20, #0x1058]
0x00041ff8: a1 1f 00 f9              str      x1, [x29, #0x38]
0x00041ffc: 09 09 00 94              bl       #0x44420
0x00042000: 84 22 50 b9              ldr      w4, [x20, #0x1020]
0x00042004: a1 1f 40 f9              ldr      x1, [x29, #0x38]
0x00042008: 85 ff ff 17              b        #0x41e1c
0x0004200c: 00 0b 80 12              mov      w0, #-0x59
0x00042010: ab ff ff 17              b        #0x41ebc
0x00042014: 01 00 80 52              mov      w1, #0
0x00042018: d5 ff ff 17              b        #0x41f6c
0x0004201c: e0 03 00 b0              adrp     x0, #0xbf000
0x00042020: 01 0c 80 12              mov      w1, #-0x61
0x00042024: 00 60 18 91              add      x0, x0, #0x618
0x00042028: 26 21 ff 97              bl       #0xa4c0                    ; -> printf@plt
0x0004202c: 00 0c 80 12              mov      w0, #-0x61
0x00042030: a3 ff ff 17              b        #0x41ebc
0x00042034: e0 03 00 b0              adrp     x0, #0xbf000
0x00042038: e1 0b 80 12              mov      w1, #-0x60
0x0004203c: 00 a0 19 91              add      x0, x0, #0x668
0x00042040: 20 21 ff 97              bl       #0xa4c0                    ; -> printf@plt
0x00042044: e0 0b 80 12              mov      w0, #-0x60
0x00042048: 9d ff ff 17              b        #0x41ebc
0x0004204c: fd 7b be a9              stp      x29, x30, [sp, #-0x20]!
0x00042050: 10 20 21 1e              fcmpe    s0, s1
0x00042054: fd 03 00 91              mov      x29, sp
0x00042058: e8 0b 00 fd              str      d8, [sp, #0x10]
0x0004205c: 85 00 00 54              b.pl     #0x4206c
0x00042060: 02 40 20 1e              fmov     s2, s0
0x00042064: 20 40 20 1e              fmov     s0, s1
0x00042068: 41 40 20 1e              fmov     s1, s2
0x0004206c: 21 38 20 1e              fsub     s1, s1, s0
0x00042070: 80 05 00 f0              adrp     x0, #0xf5000
0x00042074: 08 40 20 1e              fmov     s8, s0
0x00042078: 00 b0 40 bd              ldr      s0, [x0, #0xb0]
0x0004207c: 30 20 20 1e              fcmpe    s1, s0
0x00042080: 04 01 00 54              b.mi     #0x420a0
0x00042084: 20 c0 22 1e              fcvt     d0, s1
0x00042088: fa 20 ff 97              bl       #0xa470                    ; -> exp@plt
0x0004208c: 01 10 6e 1e              fmov     d1, #1.00000000
0x00042090: 00 28 61 1e              fadd     d0, d0, d1
0x00042094: 00 40 62 1e              fcvt     s0, d0
0x00042098: ba 22 ff 97              bl       #0xab80                    ; -> logf@plt
0x0004209c: 08 29 20 1e              fadd     s8, s8, s0
0x000420a0: 00 41 20 1e              fmov     s0, s8
0x000420a4: e8 0b 40 fd              ldr      d8, [sp, #0x10]
0x000420a8: fd 7b c2 a8              ldp      x29, x30, [sp], #0x20
0x000420ac: c0 03 5f d6              ret      
0x000420b0: 01 e0 02 91              add      x1, x0, #0xb8
0x000420b4: 1f 58 00 f9              str      xzr, [x0, #0xb0]
0x000420b8: 1f 7c 00 a9              stp      xzr, xzr, [x0]
0x000420bc: 1f 7c 01 a9              stp      xzr, xzr, [x0, #0x10]
0x000420c0: 1f 7c 02 a9              stp      xzr, xzr, [x0, #0x20]
0x000420c4: 1f 7c 03 a9              stp      xzr, xzr, [x0, #0x30]
0x000420c8: 1f 7c 04 a9              stp      xzr, xzr, [x0, #0x40]
0x000420cc: 1f 7c 05 a9              stp      xzr, xzr, [x0, #0x50]
0x000420d0: 1f 7c 06 a9              stp      xzr, xzr, [x0, #0x60]
0x000420d4: 1f 7c 07 a9              stp      xzr, xzr, [x0, #0x70]
0x000420d8: 1f 7c 08 a9              stp      xzr, xzr, [x0, #0x80]
0x000420dc: 1f 7c 09 a9              stp      xzr, xzr, [x0, #0x90]
