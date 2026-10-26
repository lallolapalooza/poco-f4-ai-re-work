; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libqvajni.so
; target  : address 0x12c60
; start   : 0x12c60
; size    : 0x310
; bytes   : 0x310   instructions: 196
; generated: 2026-10-23T23:38:52

0x00012c60: 3f 23 03 d5              paciasp  
0x00012c64: ff 03 02 d1              sub      sp, sp, #0x80
0x00012c68: fd 7b 03 a9              stp      x29, x30, [sp, #0x30]
0x00012c6c: fa 67 04 a9              stp      x26, x25, [sp, #0x40]
0x00012c70: f8 5f 05 a9              stp      x24, x23, [sp, #0x50]
0x00012c74: f6 57 06 a9              stp      x22, x21, [sp, #0x60]
0x00012c78: f4 4f 07 a9              stp      x20, x19, [sp, #0x70]
0x00012c7c: fd c3 00 91              add      x29, sp, #0x30
0x00012c80: 5a d0 3b d5              mrs      x26, tpidr_el0
0x00012c84: f4 03 05 2a              mov      w20, w5
0x00012c88: 48 17 40 f9              ldr      x8, [x26, #0x28]
0x00012c8c: f5 03 04 aa              mov      x21, x4
0x00012c90: f8 03 03 aa              mov      x24, x3
0x00012c94: f6 03 02 2a              mov      w22, w2
0x00012c98: f7 03 01 aa              mov      x23, x1
0x00012c9c: f3 03 00 aa              mov      x19, x0
0x00012ca0: a8 83 1f f8              stur     x8, [x29, #-8]
0x00012ca4: a8 78 1f 12              and      w8, w5, #0xfffffffe
0x00012ca8: 1f 09 00 71              cmp      w8, #2
0x00012cac: ff ff 01 a9              stp      xzr, xzr, [sp, #0x18]
0x00012cb0: ff ff 00 a9              stp      xzr, xzr, [sp, #8]
0x00012cb4: a1 00 00 54              b.ne     #0x12cc8
0x00012cb8: 68 8e 40 f9              ldr      x8, [x19, #0x118]
0x00012cbc: 28 06 00 b4              cbz      x8, #0x12d80
0x00012cc0: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00012cc4: e8 05 00 b4              cbz      x8, #0x12d80
0x00012cc8: 9f 12 00 71              cmp      w20, #4
0x00012ccc: 60 00 00 54              b.eq     #0x12cd8
0x00012cd0: 9f 06 00 71              cmp      w20, #1
0x00012cd4: 01 06 00 54              b.ne     #0x12d94
0x00012cd8: 20 00 80 52              mov      w0, #1
0x00012cdc: 01 04 80 52              mov      w1, #0x20
0x00012ce0: d4 32 00 94              bl       #0x1f830                   ; -> calloc@plt
0x00012ce4: 60 8e 00 f9              str      x0, [x19, #0x118]
0x00012ce8: 60 08 00 b4              cbz      x0, #0x12df4
0x00012cec: f9 03 00 aa              mov      x25, x0
0x00012cf0: 20 00 80 52              mov      w0, #1
0x00012cf4: 01 02 80 52              mov      w1, #0x10
0x00012cf8: ce 32 00 94              bl       #0x1f830                   ; -> calloc@plt
0x00012cfc: 20 0b 00 f9              str      x0, [x25, #0x10]
0x00012d00: 40 08 00 b4              cbz      x0, #0x12e08
0x00012d04: 20 00 80 52              mov      w0, #1
0x00012d08: 01 07 80 52              mov      w1, #0x38
0x00012d0c: c9 32 00 94              bl       #0x1f830                   ; -> calloc@plt
0x00012d10: 60 96 00 f9              str      x0, [x19, #0x128]
0x00012d14: 60 0a 00 b4              cbz      x0, #0x12e60
0x00012d18: 20 00 80 52              mov      w0, #1
0x00012d1c: 81 00 80 52              mov      w1, #4
0x00012d20: c4 32 00 94              bl       #0x1f830                   ; -> calloc@plt
0x00012d24: 60 9e 00 f9              str      x0, [x19, #0x138]
0x00012d28: 60 0a 00 b4              cbz      x0, #0x12e74
0x00012d2c: 08 03 40 b9              ldr      w8, [x24]
0x00012d30: 28 03 00 34              cbz      w8, #0x12d94
0x00012d34: 80 04 00 0f              movi     v0.2s, #4
0x00012d38: 68 06 40 f9              ldr      x8, [x19, #8]
0x00012d3c: e0 07 00 f9              str      x0, [sp, #8]
0x00012d40: 0a 07 40 b9              ldr      w10, [x24, #4]
0x00012d44: e3 23 00 91              add      x3, sp, #8
0x00012d48: 41 01 80 52              mov      w1, #0xa
0x00012d4c: e2 03 1f aa              mov      x2, xzr
0x00012d50: e0 0b 00 fd              str      d0, [sp, #0x10]
0x00012d54: 09 01 40 f9              ldr      x9, [x8]
0x00012d58: 0a 00 00 b9              str      w10, [x0]
0x00012d5c: e0 03 08 aa              mov      x0, x8
0x00012d60: 29 09 40 f9              ldr      x9, [x9, #0x10]
0x00012d64: 20 01 3f d6              blr      x9
0x00012d68: 60 01 00 34              cbz      w0, #0x12d94
0x00012d6c: 81 ff ff f0              adrp     x1, #0x5000
0x00012d70: 21 cc 36 91              add      x1, x1, #0xdb3
0x00012d74: a2 ff ff d0              adrp     x2, #0x8000
0x00012d78: 42 dc 16 91              add      x2, x2, #0x5b7
0x00012d7c: 42 00 00 14              b        #0x12e84                   ; -> _ZN9Algorithm25user_verification_processEPsjP28algorithm_process_parametersP24algorithm_process_resultj+0x224
0x00012d80: 81 ff ff f0              adrp     x1, #0x5000
0x00012d84: 21 cc 36 91              add      x1, x1, #0xdb3
0x00012d88: a2 ff ff f0              adrp     x2, #0x9000
0x00012d8c: 42 04 36 91              add      x2, x2, #0xd81
0x00012d90: 3d 00 00 14              b        #0x12e84                   ; -> _ZN9Algorithm25user_verification_processEPsjP28algorithm_process_parametersP24algorithm_process_resultj+0x224
0x00012d94: 68 8e 40 f9              ldr      x8, [x19, #0x118]
0x00012d98: 29 00 80 52              mov      w9, #1
0x00012d9c: 61 62 04 91              add      x1, x19, #0x118
0x00012da0: e2 03 1f aa              mov      x2, xzr
0x00012da4: 09 19 00 b9              str      w9, [x8, #0x18]
0x00012da8: 68 8e 40 f9              ldr      x8, [x19, #0x118]
0x00012dac: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00012db0: 17 01 00 f9              str      x23, [x8]
0x00012db4: 68 8e 40 f9              ldr      x8, [x19, #0x118]
0x00012db8: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00012dbc: 16 0d 00 b9              str      w22, [x8, #0xc]
0x00012dc0: 68 8e 40 f9              ldr      x8, [x19, #0x118]
0x00012dc4: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00012dc8: 16 09 00 b9              str      w22, [x8, #8]
0x00012dcc: 60 06 40 f9              ldr      x0, [x19, #8]
0x00012dd0: 08 00 40 f9              ldr      x8, [x0]
0x00012dd4: 08 01 40 f9              ldr      x8, [x8]
0x00012dd8: 00 01 3f d6              blr      x8
0x00012ddc: 00 02 00 34              cbz      w0, #0x12e1c
0x00012de0: 81 ff ff f0              adrp     x1, #0x5000
0x00012de4: 21 cc 36 91              add      x1, x1, #0xdb3
0x00012de8: a2 ff ff 90              adrp     x2, #0x6000
0x00012dec: 42 dc 1d 91              add      x2, x2, #0x777
0x00012df0: 25 00 00 14              b        #0x12e84                   ; -> _ZN9Algorithm25user_verification_processEPsjP28algorithm_process_parametersP24algorithm_process_resultj+0x224
0x00012df4: 81 ff ff f0              adrp     x1, #0x5000
0x00012df8: 21 cc 36 91              add      x1, x1, #0xdb3
0x00012dfc: 82 ff ff d0              adrp     x2, #0x4000
0x00012e00: 42 9c 20 91              add      x2, x2, #0x827
0x00012e04: 20 00 00 14              b        #0x12e84                   ; -> _ZN9Algorithm25user_verification_processEPsjP28algorithm_process_parametersP24algorithm_process_resultj+0x224
0x00012e08: 81 ff ff f0              adrp     x1, #0x5000
0x00012e0c: 21 cc 36 91              add      x1, x1, #0xdb3
0x00012e10: a2 ff ff b0              adrp     x2, #0x7000
0x00012e14: 42 f8 1d 91              add      x2, x2, #0x77e
0x00012e18: 1b 00 00 14              b        #0x12e84                   ; -> _ZN9Algorithm25user_verification_processEPsjP28algorithm_process_parametersP24algorithm_process_resultj+0x224
0x00012e1c: 00 07 01 0f              movi     v0.2s, #0x38
0x00012e20: 68 96 40 f9              ldr      x8, [x19, #0x128]
0x00012e24: 60 06 40 f9              ldr      x0, [x19, #8]
0x00012e28: e3 63 00 91              add      x3, sp, #0x18
0x00012e2c: 81 01 80 52              mov      w1, #0xc
0x00012e30: e2 03 1f aa              mov      x2, xzr
0x00012e34: e8 0f 00 f9              str      x8, [sp, #0x18]
0x00012e38: e0 13 00 fd              str      d0, [sp, #0x20]
0x00012e3c: 08 00 40 f9              ldr      x8, [x0]
0x00012e40: 08 0d 40 f9              ldr      x8, [x8, #0x18]
0x00012e44: 00 01 3f d6              blr      x8
0x00012e48: 60 05 00 34              cbz      w0, #0x12ef4
0x00012e4c: 81 ff ff f0              adrp     x1, #0x5000
0x00012e50: 21 cc 36 91              add      x1, x1, #0xdb3
0x00012e54: a2 ff ff f0              adrp     x2, #0x9000
0x00012e58: 42 e0 2b 91              add      x2, x2, #0xaf8
0x00012e5c: 0a 00 00 14              b        #0x12e84                   ; -> _ZN9Algorithm25user_verification_processEPsjP28algorithm_process_parametersP24algorithm_process_resultj+0x224
0x00012e60: 81 ff ff f0              adrp     x1, #0x5000
0x00012e64: 21 cc 36 91              add      x1, x1, #0xdb3
0x00012e68: a2 ff ff b0              adrp     x2, #0x7000
0x00012e6c: 42 1c 07 91              add      x2, x2, #0x1c7
0x00012e70: 05 00 00 14              b        #0x12e84                   ; -> _ZN9Algorithm25user_verification_processEPsjP28algorithm_process_parametersP24algorithm_process_resultj+0x224
0x00012e74: 81 ff ff f0              adrp     x1, #0x5000
0x00012e78: 21 cc 36 91              add      x1, x1, #0xdb3
0x00012e7c: a2 ff ff f0              adrp     x2, #0x9000
0x00012e80: 42 b4 1f 91              add      x2, x2, #0x7ed
0x00012e84: c0 00 80 52              mov      w0, #6
0x00012e88: 58 32 00 94              bl       #0x1f7e8
0x00012e8c: 28 00 80 52              mov      w8, #1
0x00012e90: 89 0e 00 51              sub      w9, w20, #3
0x00012e94: 3f 09 00 71              cmp      w9, #2
0x00012e98: e3 00 00 54              b.lo     #0x12eb4
0x00012e9c: 69 96 40 f9              ldr      x9, [x19, #0x128]
0x00012ea0: 29 21 40 b9              ldr      w9, [x9, #0x20]
0x00012ea4: 3f 01 00 71              cmp      w9, #0
0x00012ea8: e9 17 9f 1a              cset     w9, eq
0x00012eac: 28 01 28 0a              bic      w8, w9, w8
0x00012eb0: 88 00 00 37              tbnz     w8, #0, #0x12ec0
0x00012eb4: e0 03 13 aa              mov      x0, x19
0x00012eb8: 3c 33 00 94              bl       #0x1fba8
0x00012ebc: 02 00 00 14              b        #0x12ec4                   ; -> _ZN9Algorithm25user_verification_processEPsjP28algorithm_process_parametersP24algorithm_process_resultj+0x264
0x00012ec0: e0 03 1f 2a              mov      w0, wzr
0x00012ec4: 48 17 40 f9              ldr      x8, [x26, #0x28]
0x00012ec8: a9 83 5f f8              ldur     x9, [x29, #-8]
0x00012ecc: 1f 01 09 eb              cmp      x8, x9
0x00012ed0: e1 04 00 54              b.ne     #0x12f6c
0x00012ed4: f4 4f 47 a9              ldp      x20, x19, [sp, #0x70]
0x00012ed8: f6 57 46 a9              ldp      x22, x21, [sp, #0x60]
0x00012edc: f8 5f 45 a9              ldp      x24, x23, [sp, #0x50]
0x00012ee0: fa 67 44 a9              ldp      x26, x25, [sp, #0x40]
0x00012ee4: fd 7b 43 a9              ldp      x29, x30, [sp, #0x30]
0x00012ee8: ff 03 02 91              add      sp, sp, #0x80
0x00012eec: bf 23 03 d5              autiasp  
0x00012ef0: c0 03 5f d6              ret      
0x00012ef4: 68 96 40 f9              ldr      x8, [x19, #0x128]
0x00012ef8: 81 ff ff f0              adrp     x1, #0x5000
0x00012efc: 21 e8 00 91              add      x1, x1, #0x3a
0x00012f00: c2 ff ff 90              adrp     x2, #0xa000
0x00012f04: 42 54 07 91              add      x2, x2, #0x1d5
0x00012f08: 60 00 80 52              mov      w0, #3
0x00012f0c: 08 21 40 b9              ldr      w8, [x8, #0x20]
0x00012f10: 1f 01 00 71              cmp      w8, #0
0x00012f14: e8 07 9f 1a              cset     w8, ne
0x00012f18: a8 02 00 b9              str      w8, [x21]
0x00012f1c: 68 96 40 f9              ldr      x8, [x19, #0x128]
0x00012f20: 03 29 40 b9              ldr      w3, [x8, #0x28]
0x00012f24: 31 32 00 94              bl       #0x1f7e8
0x00012f28: 68 96 40 f9              ldr      x8, [x19, #0x128]
0x00012f2c: 08 29 40 b9              ldr      w8, [x8, #0x28]
0x00012f30: a8 06 00 b9              str      w8, [x21, #4]
0x00012f34: 68 96 40 f9              ldr      x8, [x19, #0x128]
0x00012f38: 00 31 40 bd              ldr      s0, [x8, #0x30]
0x00012f3c: a0 0a 00 bd              str      s0, [x21, #8]
0x00012f40: 68 96 40 f9              ldr      x8, [x19, #0x128]
0x00012f44: 00 2d 40 bd              ldr      s0, [x8, #0x2c]
0x00012f48: a0 0e 00 bd              str      s0, [x21, #0xc]
0x00012f4c: 68 96 40 f9              ldr      x8, [x19, #0x128]
0x00012f50: 00 35 40 bd              ldr      s0, [x8, #0x34]
0x00012f54: a0 12 00 bd              str      s0, [x21, #0x10]
0x00012f58: 68 96 40 f9              ldr      x8, [x19, #0x128]
0x00012f5c: 09 25 40 b9              ldr      w9, [x8, #0x24]
0x00012f60: e8 03 1f 2a              mov      w8, wzr
0x00012f64: a9 16 00 b9              str      w9, [x21, #0x14]
0x00012f68: ca ff ff 17              b        #0x12e90                   ; -> _ZN9Algorithm25user_verification_processEPsjP28algorithm_process_parametersP24algorithm_process_resultj+0x230
0x00012f6c: 5b 32 00 94              bl       #0x1f8d8
