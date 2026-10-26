; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainsdk.so
; target  : vadInit (0x16e20)
; start   : 0x16e20
; size    : 0x1b8
; bytes   : 0x1b8   instructions: 110
; generated: 2026-10-23T23:42:23

0x00016e20: fd 7b b7 a9              stp      x29, x30, [sp, #-0x90]!
0x00016e24: fd 03 00 91              mov      x29, sp
0x00016e28: f3 53 01 a9              stp      x19, x20, [sp, #0x10]
0x00016e2c: f4 06 00 b0              adrp     x20, #0xf3000
0x00016e30: f7 1b 00 f9              str      x23, [sp, #0x30]
0x00016e34: f7 03 01 2a              mov      w23, w1
0x00016e38: f3 03 00 aa              mov      x19, x0
0x00016e3c: e8 1f 00 fd              str      d8, [sp, #0x38]
0x00016e40: 00 0c 82 d2              mov      x0, #0x1060
0x00016e44: 81 5a 47 f9              ldr      x1, [x20, #0xeb0]          ; -> __stack_chk_guard@got
0x00016e48: 08 40 20 1e              fmov     s8, s0
0x00016e4c: f5 5b 02 a9              stp      x21, x22, [sp, #0x20]
0x00016e50: 21 00 40 f9              ldr      x1, [x1]
0x00016e54: f6 03 02 2a              mov      w22, w2
0x00016e58: 7f 7e 00 a9              stp      xzr, xzr, [x19]
0x00016e5c: a1 47 00 f9              str      x1, [x29, #0x88]
0x00016e60: d8 cd ff 97              bl       #0xa5c0                    ; -> _Znwm@plt
0x00016e64: 60 02 00 f9              str      x0, [x19]
0x00016e68: 00 5e 80 d2              mov      x0, #0x2f0
0x00016e6c: d5 cd ff 97              bl       #0xa5c0                    ; -> _Znwm@plt
0x00016e70: f5 03 00 aa              mov      x21, x0
0x00016e74: 14 ae 00 94              bl       #0x426c4
0x00016e78: 60 02 40 f9              ldr      x0, [x19]
0x00016e7c: 75 06 00 f9              str      x21, [x19, #8]
0x00016e80: 60 09 00 b4              cbz      x0, #0x16fac
0x00016e84: 03 d0 87 52              mov      w3, #0x3e80
0x00016e88: 35 00 80 52              mov      w21, #1
0x00016e8c: 01 05 00 d0              adrp     x1, #0xb8000
0x00016e90: e0 03 15 2a              mov      w0, w21
0x00016e94: 21 20 29 91              add      x1, x1, #0xa48
0x00016e98: e2 03 03 2a              mov      w2, w3
0x00016e9c: a3 43 00 b9              str      w3, [x29, #0x40]
0x00016ea0: a3 47 00 b9              str      w3, [x29, #0x44]
0x00016ea4: b5 4b 00 b9              str      w21, [x29, #0x48]
0x00016ea8: ca ce ff 97              bl       #0xa9d0                    ; -> mi_brain_log@plt
0x00016eac: 60 02 40 f9              ldr      x0, [x19]
0x00016eb0: a1 03 01 91              add      x1, x29, #0x40
0x00016eb4: ec a7 00 94              bl       #0x40e64
0x00016eb8: 20 02 00 34              cbz      w0, #0x16efc
0x00016ebc: 01 05 00 d0              adrp     x1, #0xb8000
0x00016ec0: 60 00 80 52              mov      w0, #3
0x00016ec4: 21 80 29 91              add      x1, x1, #0xa60
0x00016ec8: c2 ce ff 97              bl       #0xa9d0                    ; -> mi_brain_log@plt
0x00016ecc: 00 00 80 12              mov      w0, #-1
0x00016ed0: 94 5a 47 f9              ldr      x20, [x20, #0xeb0]         ; -> __stack_chk_guard@got
0x00016ed4: a2 47 40 f9              ldr      x2, [x29, #0x88]
0x00016ed8: 81 02 40 f9              ldr      x1, [x20]
0x00016edc: 5f 00 01 eb              cmp      x2, x1
0x00016ee0: 01 07 00 54              b.ne     #0x16fc0
0x00016ee4: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x00016ee8: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x00016eec: f7 1b 40 f9              ldr      x23, [sp, #0x30]
0x00016ef0: e8 1f 40 fd              ldr      d8, [sp, #0x38]
0x00016ef4: fd 7b c9 a8              ldp      x29, x30, [sp], #0x90
0x00016ef8: c0 03 5f d6              ret      
0x00016efc: 01 05 00 d0              adrp     x1, #0xb8000
0x00016f00: e0 03 15 2a              mov      w0, w21
0x00016f04: 21 e0 29 91              add      x1, x1, #0xa78
0x00016f08: b2 ce ff 97              bl       #0xa9d0                    ; -> mi_brain_log@plt
0x00016f0c: bf 3f 00 f9              str      xzr, [x29, #0x78]
0x00016f10: e1 02 22 1e              scvtf    s1, w23
0x00016f14: e3 06 00 b0              adrp     x3, #0xf3000
0x00016f18: c0 02 22 1e              scvtf    s0, w22
0x00016f1c: e2 06 00 b0              adrp     x2, #0xf3000
0x00016f20: c2 05 00 1c              ldr      s2, #0x16fd8
0x00016f24: 60 06 40 f9              ldr      x0, [x19, #8]
0x00016f28: 63 44 46 f9              ldr      x3, [x3, #0xc88]
0x00016f2c: a1 43 01 91              add      x1, x29, #0x50
0x00016f30: 21 18 22 1e              fdiv     s1, s1, s2
0x00016f34: 42 a8 47 f9              ldr      x2, [x2, #0xf50]
0x00016f38: a2 2b 00 f9              str      x2, [x29, #0x50]
0x00016f3c: 02 e8 83 52              mov      w2, #0x1f40
0x00016f40: a2 5f 00 bd              str      s2, [x29, #0x5c]
0x00016f44: 63 00 40 b9              ldr      w3, [x3]
0x00016f48: a3 5b 00 b9              str      w3, [x29, #0x58]
0x00016f4c: a2 67 00 b9              str      w2, [x29, #0x64]
0x00016f50: 00 18 22 1e              fdiv     s0, s0, s2
0x00016f54: bf 43 00 f9              str      xzr, [x29, #0x80]
0x00016f58: 02 10 2c 1e              fmov     s2, #0.50000000
0x00016f5c: a8 73 00 bd              str      s8, [x29, #0x70]
0x00016f60: a2 63 00 bd              str      s2, [x29, #0x60]
0x00016f64: e2 03 27 1e              fmov     s2, wzr
0x00016f68: a2 77 00 bd              str      s2, [x29, #0x74]
0x00016f6c: a1 6f 00 bd              str      s1, [x29, #0x6c]
0x00016f70: a0 6b 00 bd              str      s0, [x29, #0x68]
0x00016f74: 34 af 00 94              bl       #0x42c44
0x00016f78: e0 00 00 34              cbz      w0, #0x16f94
0x00016f7c: 01 05 00 d0              adrp     x1, #0xb8000
0x00016f80: 60 00 80 52              mov      w0, #3
0x00016f84: 21 60 2a 91              add      x1, x1, #0xa98
0x00016f88: 92 ce ff 97              bl       #0xa9d0                    ; -> mi_brain_log@plt
0x00016f8c: 00 00 80 12              mov      w0, #-1
0x00016f90: d0 ff ff 17              b        #0x16ed0                   ; -> vadInit+0xb0
0x00016f94: 01 05 00 d0              adrp     x1, #0xb8000
0x00016f98: e0 03 15 2a              mov      w0, w21
0x00016f9c: 21 c0 2a 91              add      x1, x1, #0xab0
0x00016fa0: 8c ce ff 97              bl       #0xa9d0                    ; -> mi_brain_log@plt
0x00016fa4: 00 00 80 52              mov      w0, #0
0x00016fa8: ca ff ff 17              b        #0x16ed0                   ; -> vadInit+0xb0
0x00016fac: e0 03 15 aa              mov      x0, x21
0x00016fb0: d6 ad 00 94              bl       #0x42708
0x00016fb4: e0 03 15 aa              mov      x0, x21
0x00016fb8: de ce ff 97              bl       #0xab30                    ; -> _ZdlPv@plt
0x00016fbc: b2 ff ff 17              b        #0x16e84                   ; -> vadInit+0x64
0x00016fc0: 3c ce ff 97              bl       #0xa8b0                    ; -> __stack_chk_fail@plt
0x00016fc4: f3 03 00 aa              mov      x19, x0
0x00016fc8: e0 03 15 aa              mov      x0, x21
0x00016fcc: d9 ce ff 97              bl       #0xab30                    ; -> _ZdlPv@plt
0x00016fd0: e0 03 13 aa              mov      x0, x19
0x00016fd4: ea 72 02 94              bl       #0xb3b7c
