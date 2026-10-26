; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libdnnvad_jni.so
; target  : Java_com_qualcomm_qti_vad_DNNVAD_process (0x2c84)
; start   : 0x2c84
; size    : 0x188
; bytes   : 0x188   instructions: 98
; generated: 2026-10-23T23:26:52

0x00002c84: 3f 23 03 d5              paciasp  
0x00002c88: ff 83 04 d1              sub      sp, sp, #0x120
0x00002c8c: fd 7b 0e a9              stp      x29, x30, [sp, #0xe0]
0x00002c90: fc 5f 0f a9              stp      x28, x23, [sp, #0xf0]
0x00002c94: f6 57 10 a9              stp      x22, x21, [sp, #0x100]
0x00002c98: f4 4f 11 a9              stp      x20, x19, [sp, #0x110]
0x00002c9c: fd 83 03 91              add      x29, sp, #0xe0
0x00002ca0: 57 d0 3b d5              mrs      x23, tpidr_el0
0x00002ca4: f3 03 02 aa              mov      x19, x2
0x00002ca8: e8 16 40 f9              ldr      x8, [x23, #0x28]
0x00002cac: f4 03 03 aa              mov      x20, x3
0x00002cb0: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x00002cb4: f5 03 00 aa              mov      x21, x0
0x00002cb8: a8 83 1f f8              stur     x8, [x29, #-8]
0x00002cbc: 08 00 00 f0              adrp     x8, #0x5000
0x00002cc0: 09 00 40 f9              ldr      x9, [x0]
0x00002cc4: ff 7f 00 a9              stp      xzr, xzr, [sp]
0x00002cc8: e0 83 03 ad              stp      q0, q0, [sp, #0x70]
0x00002ccc: 02 71 41 f9              ldr      x2, [x8, #0x2e0]
0x00002cd0: e0 83 04 ad              stp      q0, q0, [sp, #0x90]
0x00002cd4: 28 95 41 f9              ldr      x8, [x9, #0x328]
0x00002cd8: e0 83 05 ad              stp      q0, q0, [sp, #0xb0]
0x00002cdc: 00 01 3f d6              blr      x8
0x00002ce0: 60 02 00 b4              cbz      x0, #0x2d2c
0x00002ce4: e8 03 00 91              mov      x8, sp
0x00002ce8: f6 03 00 aa              mov      x22, x0
0x00002cec: 03 21 00 91              add      x3, x8, #8
0x00002cf0: e2 03 00 91              mov      x2, sp
0x00002cf4: e0 03 15 aa              mov      x0, x21
0x00002cf8: e1 03 13 aa              mov      x1, x19
0x00002cfc: bb 02 00 94              bl       #0x37e8
0x00002d00: 60 02 00 34              cbz      w0, #0x2d4c
0x00002d04: e1 ff ff f0              adrp     x1, #0x1000
0x00002d08: 21 fc 1c 91              add      x1, x1, #0x73f
0x00002d0c: e2 ff ff f0              adrp     x2, #0x1000
0x00002d10: 42 9c 13 91              add      x2, x2, #0x4e7
0x00002d14: c0 00 80 52              mov      w0, #6
0x00002d18: 8a 02 00 94              bl       #0x3740                    ; -> __android_log_print@plt
0x00002d1c: f3 07 40 f9              ldr      x19, [sp, #8]
0x00002d20: 14 00 80 12              mov      w20, #-1
0x00002d24: 13 05 00 b5              cbnz     x19, #0x2dc4
0x00002d28: 29 00 00 14              b        #0x2dcc                    ; -> Java_com_qualcomm_qti_vad_DNNVAD_process+0x148
0x00002d2c: e1 ff ff f0              adrp     x1, #0x1000
0x00002d30: 21 fc 1c 91              add      x1, x1, #0x73f
0x00002d34: e2 ff ff f0              adrp     x2, #0x1000
0x00002d38: 42 48 1d 91              add      x2, x2, #0x752
0x00002d3c: c0 00 80 52              mov      w0, #6
0x00002d40: 80 02 00 94              bl       #0x3740                    ; -> __android_log_print@plt
0x00002d44: 14 00 80 12              mov      w20, #-1
0x00002d48: 21 00 00 14              b        #0x2dcc                    ; -> Java_com_qualcomm_qti_vad_DNNVAD_process+0x148
0x00002d4c: e1 4f 40 a9              ldp      x1, x19, [sp]
0x00002d50: e3 c3 01 91              add      x3, sp, #0x70
0x00002d54: e0 03 16 aa              mov      x0, x22
0x00002d58: e2 03 13 aa              mov      x2, x19
0x00002d5c: af 02 00 94              bl       #0x3818
0x00002d60: c0 00 00 34              cbz      w0, #0x2d78
0x00002d64: e1 ff ff f0              adrp     x1, #0x1000
0x00002d68: 21 fc 1c 91              add      x1, x1, #0x73f
0x00002d6c: e2 ff ff f0              adrp     x2, #0x1000
0x00002d70: 42 94 19 91              add      x2, x2, #0x665
0x00002d74: 10 00 00 14              b        #0x2db4                    ; -> Java_com_qualcomm_qti_vad_DNNVAD_process+0x130
0x00002d78: e0 87 44 ad              ldp      q0, q1, [sp, #0x90]
0x00002d7c: e1 43 00 91              add      x1, sp, #0x10
0x00002d80: e0 03 15 aa              mov      x0, x21
0x00002d84: e2 03 14 aa              mov      x2, x20
0x00002d88: e2 8f 45 ad              ldp      q2, q3, [sp, #0xb0]
0x00002d8c: e0 87 01 ad              stp      q0, q1, [sp, #0x30]
0x00002d90: e0 87 43 ad              ldp      q0, q1, [sp, #0x70]
0x00002d94: e2 8f 02 ad              stp      q2, q3, [sp, #0x50]
0x00002d98: e0 87 00 ad              stp      q0, q1, [sp, #0x10]
0x00002d9c: 99 02 00 94              bl       #0x3800                    ; -> fillVADBundleResult@plt
0x00002da0: e0 02 00 34              cbz      w0, #0x2dfc
0x00002da4: e1 ff ff f0              adrp     x1, #0x1000
0x00002da8: 21 fc 1c 91              add      x1, x1, #0x73f
0x00002dac: e2 ff ff f0              adrp     x2, #0x1000
0x00002db0: 42 5c 0d 91              add      x2, x2, #0x357
0x00002db4: c0 00 80 52              mov      w0, #6
0x00002db8: 62 02 00 94              bl       #0x3740                    ; -> __android_log_print@plt
0x00002dbc: 14 00 80 12              mov      w20, #-1
0x00002dc0: 73 00 00 b4              cbz      x19, #0x2dcc
0x00002dc4: e0 03 13 aa              mov      x0, x19
0x00002dc8: 82 02 00 94              bl       #0x37d0                    ; -> free@plt
0x00002dcc: e8 16 40 f9              ldr      x8, [x23, #0x28]
0x00002dd0: a9 83 5f f8              ldur     x9, [x29, #-8]
0x00002dd4: 1f 01 09 eb              cmp      x8, x9
0x00002dd8: 81 01 00 54              b.ne     #0x2e08
0x00002ddc: e0 03 14 2a              mov      w0, w20
0x00002de0: f4 4f 51 a9              ldp      x20, x19, [sp, #0x110]
0x00002de4: f6 57 50 a9              ldp      x22, x21, [sp, #0x100]
0x00002de8: fc 5f 4f a9              ldp      x28, x23, [sp, #0xf0]
0x00002dec: fd 7b 4e a9              ldp      x29, x30, [sp, #0xe0]
0x00002df0: ff 83 04 91              add      sp, sp, #0x120
0x00002df4: bf 23 03 d5              autiasp  
0x00002df8: c0 03 5f d6              ret      
0x00002dfc: f4 03 1f 2a              mov      w20, wzr
0x00002e00: 33 fe ff b5              cbnz     x19, #0x2dc4
0x00002e04: f2 ff ff 17              b        #0x2dcc                    ; -> Java_com_qualcomm_qti_vad_DNNVAD_process+0x148
0x00002e08: 54 02 00 94              bl       #0x3758
