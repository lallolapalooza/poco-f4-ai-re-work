; disasm_elf.py (capstone 5.0.7)
; file    : /tmp/kwai_libs/lib/arm64-v8a/libvisionengine.so
; target  : Java_com_kwai_kve_SmartUploadAnalyzer_openWithModelTypeNative (0x169d18)
; start   : 0x169d18
; size    : 0x6c
; bytes   : 0x6c   instructions: 27
; generated: 2026-10-23T23:30:53

0x00169d18: ff 43 01 d1              sub      sp, sp, #0x50
0x00169d1c: f3 1b 00 f9              str      x19, [sp, #0x30]
0x00169d20: fd 7b 04 a9              stp      x29, x30, [sp, #0x40]
0x00169d24: fd 03 01 91              add      x29, sp, #0x40
0x00169d28: 53 d0 3b d5              mrs      x19, tpidr_el0
0x00169d2c: 68 16 40 f9              ldr      x8, [x19, #0x28]
0x00169d30: e0 03 02 aa              mov      x0, x2
0x00169d34: e1 23 00 91              add      x1, sp, #8
0x00169d38: a8 83 1e f8              stur     x8, [x29, #-0x18]
0x00169d3c: e8 03 1f 32              mov      w8, #2
0x00169d40: e3 13 01 29              stp      w3, w4, [sp, #8]
0x00169d44: e5 1b 02 29              stp      w5, w6, [sp, #0x10]
0x00169d48: e8 1b 00 b9              str      w8, [sp, #0x18]
0x00169d4c: e0 1f 00 bd              str      s0, [sp, #0x1c]
0x00169d50: e7 23 00 b9              str      w7, [sp, #0x20]
0x00169d54: 7b 10 fb 97              bl       #0x2df40                   ; -> kveOpenCape@plt
0x00169d58: 68 16 40 f9              ldr      x8, [x19, #0x28]
0x00169d5c: a9 83 5e f8              ldur     x9, [x29, #-0x18]
0x00169d60: 1f 00 00 71              cmp      w0, #0
0x00169d64: e0 17 9f 1a              cset     w0, eq
0x00169d68: 1f 01 09 eb              cmp      x8, x9
0x00169d6c: a1 00 00 54              b.ne     #0x169d80
0x00169d70: fd 7b 44 a9              ldp      x29, x30, [sp, #0x40]
0x00169d74: f3 1b 40 f9              ldr      x19, [sp, #0x30]
0x00169d78: ff 43 01 91              add      sp, sp, #0x50
0x00169d7c: c0 03 5f d6              ret      
0x00169d80: f8 0e fb 97              bl       #0x2d960                   ; -> __stack_chk_fail@plt
