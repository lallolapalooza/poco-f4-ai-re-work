; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainsdk.so
; target  : vadCheckBegin (0x16fdc)
; start   : 0x16fdc
; size    : 0x140
; bytes   : 0x140   instructions: 80
; generated: 2026-10-23T23:42:26

0x00016fdc: fd 7b b8 a9              stp      x29, x30, [sp, #-0x80]!
0x00016fe0: fd 03 00 91              mov      x29, sp
0x00016fe4: f3 53 01 a9              stp      x19, x20, [sp, #0x10]
0x00016fe8: f3 06 00 b0              adrp     x19, #0xf3000
0x00016fec: f4 03 00 aa              mov      x20, x0
0x00016ff0: f5 5b 02 a9              stp      x21, x22, [sp, #0x20]
0x00016ff4: f5 03 03 2a              mov      w21, w3
0x00016ff8: f6 03 01 aa              mov      x22, x1
0x00016ffc: 60 5a 47 f9              ldr      x0, [x19, #0xeb0]          ; -> __stack_chk_guard@got
0x00017000: 00 00 40 f9              ldr      x0, [x0]
0x00017004: a0 3f 00 f9              str      x0, [x29, #0x78]
0x00017008: 23 01 00 35              cbnz     w3, #0x1702c
0x0001700c: 01 05 00 b0              adrp     x1, #0xb8000
0x00017010: 20 00 80 52              mov      w0, #1
0x00017014: 21 20 2b 91              add      x1, x1, #0xac8
0x00017018: a2 1f 00 f9              str      x2, [x29, #0x38]
0x0001701c: 6d ce ff 97              bl       #0xa9d0                    ; -> mi_brain_log@plt
0x00017020: 80 06 40 f9              ldr      x0, [x20, #8]
0x00017024: 89 ae 00 94              bl       #0x42a48
0x00017028: a2 1f 40 f9              ldr      x2, [x29, #0x38]
0x0001702c: 42 7c 40 93              sxtw     x2, w2
0x00017030: 80 02 40 f9              ldr      x0, [x20]
0x00017034: 42 fc 41 d3              lsr      x2, x2, #1
0x00017038: e1 03 16 aa              mov      x1, x22
0x0001703c: e3 03 15 2a              mov      w3, w21
0x00017040: 68 ab 00 94              bl       #0x41de0
0x00017044: e2 03 00 2a              mov      w2, w0
0x00017048: e0 01 00 34              cbz      w0, #0x17084
0x0001704c: 01 05 00 b0              adrp     x1, #0xb8000
0x00017050: 20 00 80 52              mov      w0, #1
0x00017054: 21 80 2b 91              add      x1, x1, #0xae0
0x00017058: 5e ce ff 97              bl       #0xa9d0                    ; -> mi_brain_log@plt
0x0001705c: 00 00 80 12              mov      w0, #-1
0x00017060: 73 5a 47 f9              ldr      x19, [x19, #0xeb0]         ; -> __stack_chk_guard@got
0x00017064: a2 3f 40 f9              ldr      x2, [x29, #0x78]
0x00017068: 61 02 40 f9              ldr      x1, [x19]
0x0001706c: 5f 00 01 eb              cmp      x2, x1
0x00017070: 41 05 00 54              b.ne     #0x17118
0x00017074: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x00017078: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x0001707c: fd 7b c8 a8              ldp      x29, x30, [sp], #0x80
0x00017080: c0 03 5f d6              ret      
0x00017084: 82 02 40 f9              ldr      x2, [x20]
0x00017088: b6 23 01 91              add      x22, x29, #0x48
0x0001708c: 80 06 40 f9              ldr      x0, [x20, #8]
0x00017090: e1 03 16 aa              mov      x1, x22
0x00017094: b5 5b 00 b9              str      w21, [x29, #0x58]
0x00017098: 43 ac 4f b9              ldr      w3, [x2, #0xfac]
0x0001709c: 45 d0 47 f9              ldr      x5, [x2, #0xfa0]
0x000170a0: 44 a8 4f b9              ldr      w4, [x2, #0xfa8]
0x000170a4: 42 d8 47 f9              ldr      x2, [x2, #0xfb0]
0x000170a8: a5 27 00 f9              str      x5, [x29, #0x48]
0x000170ac: a4 53 00 b9              str      w4, [x29, #0x50]
0x000170b0: a3 57 00 b9              str      w3, [x29, #0x54]
0x000170b4: a2 37 00 f9              str      x2, [x29, #0x68]
0x000170b8: a3 73 00 b9              str      w3, [x29, #0x70]
0x000170bc: ac b2 00 94              bl       #0x43b6c
0x000170c0: 20 01 00 35              cbnz     w0, #0x170e4
0x000170c4: a2 5f 40 b9              ldr      w2, [x29, #0x5c]
0x000170c8: 42 02 f8 37              tbnz     w2, #0x1f, #0x17110
0x000170cc: 01 05 00 b0              adrp     x1, #0xb8000
0x000170d0: 20 00 80 52              mov      w0, #1
0x000170d4: 21 40 2c 91              add      x1, x1, #0xb10
0x000170d8: 3e ce ff 97              bl       #0xa9d0                    ; -> mi_brain_log@plt
0x000170dc: c0 16 40 b9              ldr      w0, [x22, #0x14]
0x000170e0: e0 ff ff 17              b        #0x17060                   ; -> vadCheckBegin+0x84
0x000170e4: 01 05 00 b0              adrp     x1, #0xb8000
0x000170e8: e2 03 00 2a              mov      w2, w0
0x000170ec: 21 e0 2b 91              add      x1, x1, #0xaf8
0x000170f0: 60 00 80 52              mov      w0, #3
0x000170f4: 37 ce ff 97              bl       #0xa9d0                    ; -> mi_brain_log@plt
0x000170f8: 80 06 40 f9              ldr      x0, [x20, #8]
0x000170fc: 53 ae 00 94              bl       #0x42a48
0x00017100: 80 02 40 f9              ldr      x0, [x20]
0x00017104: 86 a5 00 94              bl       #0x4071c
0x00017108: 00 00 80 12              mov      w0, #-1
0x0001710c: d5 ff ff 17              b        #0x17060                   ; -> vadCheckBegin+0x84
0x00017110: 00 00 80 12              mov      w0, #-1
0x00017114: d3 ff ff 17              b        #0x17060                   ; -> vadCheckBegin+0x84
0x00017118: e6 cd ff 97              bl       #0xa8b0                    ; -> __stack_chk_fail@plt
