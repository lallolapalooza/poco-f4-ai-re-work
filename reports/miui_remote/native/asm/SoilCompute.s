; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : SoilCompute (0x2fb50)
; start   : 0x2fb50
; size    : 0x314
; bytes   : 0x314   instructions: 197
; generated: 2026-10-23T23:30:02

0x0002fb50: ff c3 01 d1              sub      sp, sp, #0x70
0x0002fb54: fc 6f 01 a9              stp      x28, x27, [sp, #0x10]
0x0002fb58: fa 67 02 a9              stp      x26, x25, [sp, #0x20]
0x0002fb5c: f8 5f 03 a9              stp      x24, x23, [sp, #0x30]
0x0002fb60: f6 57 04 a9              stp      x22, x21, [sp, #0x40]
0x0002fb64: f4 4f 05 a9              stp      x20, x19, [sp, #0x50]
0x0002fb68: fd 7b 06 a9              stp      x29, x30, [sp, #0x60]
0x0002fb6c: fd 83 01 91              add      x29, sp, #0x60
0x0002fb70: 1b 00 40 f9              ldr      x27, [x0]
0x0002fb74: f8 03 03 aa              mov      x24, x3
0x0002fb78: f6 03 02 2a              mov      w22, w2
0x0002fb7c: f5 03 01 aa              mov      x21, x1
0x0002fb80: f4 03 18 aa              mov      x20, x24
0x0002fb84: 58 04 00 b5              cbnz     x24, #0x2fc0c
0x0002fb88: 68 0f 40 f9              ldr      x8, [x27, #0x18]
0x0002fb8c: c1 7e 7e 93              sbfiz    x1, x22, #2, #0x20
0x0002fb90: 00 01 40 f9              ldr      x0, [x8]
0x0002fb94: 53 75 ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x0002fb98: f4 03 00 aa              mov      x20, x0
0x0002fb9c: c8 06 00 71              subs     w8, w22, #1
0x0002fba0: 6b 06 00 54              b.lt     #0x2fc6c
0x0002fba4: 09 05 00 91              add      x9, x8, #1
0x0002fba8: 3f 21 00 f1              cmp      x9, #8
0x0002fbac: 62 00 00 54              b.hs     #0x2fbb8
0x0002fbb0: e8 03 1f aa              mov      x8, xzr
0x0002fbb4: 11 00 00 14              b        #0x2fbf8                   ; -> SoilCompute+0xa8
0x0002fbb8: 28 75 7d 92              and      x8, x9, #0x1fffffff8
0x0002fbbc: e8 01 00 b4              cbz      x8, #0x2fbf8
0x0002fbc0: 0a 01 00 90              adrp     x10, #0x4f000
0x0002fbc4: 42 e9 c1 3d              ldr      q2, [x10, #0x7a0]
0x0002fbc8: 8a 42 00 91              add      x10, x20, #0x10
0x0002fbcc: 80 04 00 4f              movi     v0.4s, #4
0x0002fbd0: 01 05 00 4f              movi     v1.4s, #8
0x0002fbd4: eb 03 08 aa              mov      x11, x8
0x0002fbd8: 43 84 a0 4e              add      v3.4s, v2.4s, v0.4s
0x0002fbdc: 42 8d 3f ad              stp      q2, q3, [x10, #-0x10]
0x0002fbe0: 42 84 a1 4e              add      v2.4s, v2.4s, v1.4s
0x0002fbe4: 6b 21 00 d1              sub      x11, x11, #8
0x0002fbe8: 4a 81 00 91              add      x10, x10, #0x20
0x0002fbec: 6b ff ff b5              cbnz     x11, #0x2fbd8
0x0002fbf0: 3f 01 08 eb              cmp      x9, x8
0x0002fbf4: c0 00 00 54              b.eq     #0x2fc0c
0x0002fbf8: e9 03 16 2a              mov      w9, w22
0x0002fbfc: 88 7a 28 b8              str      w8, [x20, x8, lsl #2]
0x0002fc00: 08 05 00 91              add      x8, x8, #1
0x0002fc04: 3f 01 08 6b              cmp      w9, w8
0x0002fc08: a1 ff ff 54              b.ne     #0x2fbfc
0x0002fc0c: df 06 00 71              cmp      w22, #1
0x0002fc10: eb 02 00 54              b.lt     #0x2fc6c
0x0002fc14: f3 03 1f aa              mov      x19, xzr
0x0002fc18: d6 7e 40 93              sxtw     x22, w22
0x0002fc1c: 88 7a b3 b8              ldrsw    x8, [x20, x19, lsl #2]
0x0002fc20: 69 03 40 f9              ldr      x9, [x27]
0x0002fc24: 37 79 68 f8              ldr      x23, [x9, x8, lsl #3]
0x0002fc28: e8 02 40 b9              ldr      w8, [x23]
0x0002fc2c: 1f 05 00 71              cmp      w8, #1
0x0002fc30: a1 0e 00 54              b.ne     #0x2fe04
0x0002fc34: e8 12 40 b9              ldr      w8, [x23, #0x10]
0x0002fc38: 1f 05 00 71              cmp      w8, #1
0x0002fc3c: 41 0f 00 54              b.ne     #0x2fe24
0x0002fc40: e0 0e 40 f9              ldr      x0, [x23, #0x18]
0x0002fc44: 80 00 00 b5              cbnz     x0, #0x2fc54
0x0002fc48: 60 0f 40 f9              ldr      x0, [x27, #0x18]
0x0002fc4c: dd 76 ff 97              bl       #0xd7c0                    ; -> TensorNew@plt
0x0002fc50: e0 0e 00 f9              str      x0, [x23, #0x18]
0x0002fc54: e1 03 15 aa              mov      x1, x21
0x0002fc58: c6 75 ff 97              bl       #0xd370                    ; -> TensorLink@plt
0x0002fc5c: 73 06 00 91              add      x19, x19, #1
0x0002fc60: b5 62 00 91              add      x21, x21, #0x18
0x0002fc64: 7f 02 16 eb              cmp      x19, x22
0x0002fc68: ab fd ff 54              b.lt     #0x2fc1c
0x0002fc6c: f8 07 00 f9              str      x24, [sp, #8]
0x0002fc70: 68 0b 40 b9              ldr      w8, [x27, #8]
0x0002fc74: 1f 05 00 71              cmp      w8, #1
0x0002fc78: cb 09 00 54              b.lt     #0x2fdb0
0x0002fc7c: 88 0a 00 90              adrp     x8, #0x17f000
0x0002fc80: 08 89 47 f9              ldr      x8, [x8, #0xf10]           ; -> __sF@got
0x0002fc84: 18 01 00 90              adrp     x24, #0x4f000
0x0002fc88: f5 03 1f aa              mov      x21, xzr
0x0002fc8c: 18 67 22 91              add      x24, x24, #0x899
0x0002fc90: 17 c1 04 91              add      x23, x8, #0x130
0x0002fc94: 68 03 40 f9              ldr      x8, [x27]
0x0002fc98: 19 79 75 f8              ldr      x25, [x8, x21, lsl #3]
0x0002fc9c: 2a 03 40 b9              ldr      w10, [x25]
0x0002fca0: 29 13 40 b9              ldr      w9, [x25, #0x10]
0x0002fca4: 5f 05 00 71              cmp      w10, #1
0x0002fca8: e1 00 00 54              b.ne     #0x2fcc4
0x0002fcac: 3f 05 00 71              cmp      w9, #1
0x0002fcb0: a1 0c 00 54              b.ne     #0x2fe44
0x0002fcb4: 20 17 40 f9              ldr      x0, [x25, #0x28]
0x0002fcb8: 21 0f 40 f9              ldr      x1, [x25, #0x18]
0x0002fcbc: ad 75 ff 97              bl       #0xd370                    ; -> TensorLink@plt
0x0002fcc0: 26 00 00 14              b        #0x2fd58                   ; -> SoilCompute+0x208
0x0002fcc4: 3f 05 00 71              cmp      w9, #1
0x0002fcc8: 2b 04 00 54              b.lt     #0x2fd4c
0x0002fccc: fc 03 1f aa              mov      x28, xzr
0x0002fcd0: 33 63 00 91              add      x19, x25, #0x18
0x0002fcd4: 02 00 00 14              b        #0x2fcdc                   ; -> SoilCompute+0x18c
0x0002fcd8: 68 03 40 f9              ldr      x8, [x27]
0x0002fcdc: 29 07 40 f9              ldr      x9, [x25, #8]
0x0002fce0: 7a 7a 7c f8              ldr      x26, [x19, x28, lsl #3]
0x0002fce4: 29 79 bc b8              ldrsw    x9, [x9, x28, lsl #2]
0x0002fce8: 16 79 69 f8              ldr      x22, [x8, x9, lsl #3]
0x0002fcec: 48 0b 40 f9              ldr      x8, [x26, #0x10]
0x0002fcf0: 08 02 00 b4              cbz      x8, #0x2fd30
0x0002fcf4: c1 16 40 f9              ldr      x1, [x22, #0x28]
0x0002fcf8: 48 a7 40 29              ldp      w8, w9, [x26, #4]
0x0002fcfc: 22 8c 40 29              ldp      w2, w3, [x1, #4]
0x0002fd00: 28 7d 08 1b              mul      w8, w9, w8
0x0002fd04: 69 7c 02 1b              mul      w9, w3, w2
0x0002fd08: 1f 01 09 6b              cmp      w8, w9
0x0002fd0c: 4d 01 00 54              b.le     #0x2fd34
0x0002fd10: 21 00 40 b9              ldr      w1, [x1]
0x0002fd14: 64 0f 40 f9              ldr      x4, [x27, #0x18]
0x0002fd18: e0 03 1a aa              mov      x0, x26
0x0002fd1c: 91 75 ff 97              bl       #0xd360                    ; -> TensorRealloc@plt
0x0002fd20: c1 16 40 f9              ldr      x1, [x22, #0x28]
0x0002fd24: e0 03 1a aa              mov      x0, x26
0x0002fd28: da 77 ff 97              bl       #0xdc90                    ; -> TensorCopy@plt
0x0002fd2c: 04 00 00 14              b        #0x2fd3c                   ; -> SoilCompute+0x1ec
0x0002fd30: c1 16 40 f9              ldr      x1, [x22, #0x28]
0x0002fd34: e0 03 1a aa              mov      x0, x26
0x0002fd38: 8e 75 ff 97              bl       #0xd370                    ; -> TensorLink@plt
0x0002fd3c: 28 13 80 b9              ldrsw    x8, [x25, #0x10]
0x0002fd40: 9c 07 00 91              add      x28, x28, #1
0x0002fd44: 9f 03 08 eb              cmp      x28, x8
0x0002fd48: 8b fc ff 54              b.lt     #0x2fcd8
0x0002fd4c: 28 1f 40 f9              ldr      x8, [x25, #0x38]
0x0002fd50: e0 03 19 aa              mov      x0, x25
0x0002fd54: 00 01 3f d6              blr      x8
0x0002fd58: 68 13 40 f9              ldr      x8, [x27, #0x20]
0x0002fd5c: 08 09 40 b9              ldr      w8, [x8, #8]
0x0002fd60: 08 02 00 34              cbz      w8, #0x2fda0
0x0002fd64: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x0002fd68: 01 01 00 90              adrp     x1, #0x4f000
0x0002fd6c: e0 03 17 aa              mov      x0, x23
0x0002fd70: 21 c4 21 91              add      x1, x1, #0x871
0x0002fd74: 03 11 40 29              ldp      w3, w4, [x8]
0x0002fd78: 05 09 40 b9              ldr      w5, [x8, #8]
0x0002fd7c: e2 03 15 2a              mov      w2, w21
0x0002fd80: 30 77 ff 97              bl       #0xda40                    ; -> fprintf@plt
0x0002fd84: 20 17 40 f9              ldr      x0, [x25, #0x28]
0x0002fd88: e6 73 ff 97              bl       #0xcd20                    ; -> TensorPrint@plt
0x0002fd8c: a1 00 80 52              mov      w1, #5
0x0002fd90: e2 03 00 32              mov      w2, #1
0x0002fd94: e0 03 18 aa              mov      x0, x24
0x0002fd98: e3 03 17 aa              mov      x3, x23
0x0002fd9c: 7d 77 ff 97              bl       #0xdb90                    ; -> fwrite@plt
0x0002fda0: 68 0b 80 b9              ldrsw    x8, [x27, #8]
0x0002fda4: b5 06 00 91              add      x21, x21, #1
0x0002fda8: bf 02 08 eb              cmp      x21, x8
0x0002fdac: 4b f7 ff 54              b.lt     #0x2fc94
0x0002fdb0: e8 07 40 f9              ldr      x8, [sp, #8]
0x0002fdb4: 28 01 00 b4              cbz      x8, #0x2fdd8
0x0002fdb8: fd 7b 46 a9              ldp      x29, x30, [sp, #0x60]
0x0002fdbc: f4 4f 45 a9              ldp      x20, x19, [sp, #0x50]
0x0002fdc0: f6 57 44 a9              ldp      x22, x21, [sp, #0x40]
0x0002fdc4: f8 5f 43 a9              ldp      x24, x23, [sp, #0x30]
0x0002fdc8: fa 67 42 a9              ldp      x26, x25, [sp, #0x20]
0x0002fdcc: fc 6f 41 a9              ldp      x28, x27, [sp, #0x10]
0x0002fdd0: ff c3 01 91              add      sp, sp, #0x70
0x0002fdd4: c0 03 5f d6              ret      
0x0002fdd8: 68 0f 40 f9              ldr      x8, [x27, #0x18]
0x0002fddc: e1 03 14 aa              mov      x1, x20
0x0002fde0: fd 7b 46 a9              ldp      x29, x30, [sp, #0x60]
0x0002fde4: f4 4f 45 a9              ldp      x20, x19, [sp, #0x50]
0x0002fde8: 00 01 40 f9              ldr      x0, [x8]
0x0002fdec: f6 57 44 a9              ldp      x22, x21, [sp, #0x40]
0x0002fdf0: f8 5f 43 a9              ldp      x24, x23, [sp, #0x30]
0x0002fdf4: fa 67 42 a9              ldp      x26, x25, [sp, #0x20]
0x0002fdf8: fc 6f 41 a9              ldp      x28, x27, [sp, #0x10]
0x0002fdfc: ff c3 01 91              add      sp, sp, #0x70
0x0002fe00: 18 75 ff 17              b        #0xd260                    ; -> soil_mempool_free@plt
0x0002fe04: 00 01 00 90              adrp     x0, #0x4f000
0x0002fe08: 02 01 00 90              adrp     x2, #0x4f000
0x0002fe0c: 03 01 00 90              adrp     x3, #0x4f000
0x0002fe10: 00 c0 1e 91              add      x0, x0, #0x7b0
0x0002fe14: 42 58 20 91              add      x2, x2, #0x816
0x0002fe18: 63 88 20 91              add      x3, x3, #0x822
0x0002fe1c: e1 1b 80 52              mov      w1, #0xdf
0x0002fe20: e4 73 ff 97              bl       #0xcdb0                    ; -> __assert2@plt
0x0002fe24: 00 01 00 90              adrp     x0, #0x4f000
0x0002fe28: 02 01 00 90              adrp     x2, #0x4f000
0x0002fe2c: 03 01 00 90              adrp     x3, #0x4f000
0x0002fe30: 00 c0 1e 91              add      x0, x0, #0x7b0
0x0002fe34: 42 58 20 91              add      x2, x2, #0x816
0x0002fe38: 63 d4 20 91              add      x3, x3, #0x835
0x0002fe3c: e1 0b 1b 32              mov      w1, #0xe0
0x0002fe40: dc 73 ff 97              bl       #0xcdb0                    ; -> __assert2@plt
0x0002fe44: 00 01 00 90              adrp     x0, #0x4f000
0x0002fe48: 02 01 00 90              adrp     x2, #0x4f000
0x0002fe4c: 03 01 00 90              adrp     x3, #0x4f000
0x0002fe50: 00 c0 1e 91              add      x0, x0, #0x7b0
0x0002fe54: 42 80 21 91              add      x2, x2, #0x860
0x0002fe58: 63 d4 20 91              add      x3, x3, #0x835
0x0002fe5c: 81 12 80 52              mov      w1, #0x94
0x0002fe60: d4 73 ff 97              bl       #0xcdb0                    ; -> __assert2@plt
