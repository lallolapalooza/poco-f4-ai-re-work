; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/kwai_bulldog/native/libs/libfacedetectcontext.so
; target  : address 0x18e28
; start   : 0x18e28
; size    : 0x130
; bytes   : 0x130   instructions: 76
; generated: 2026-10-24T00:59:33

0x00018e28: ff 43 01 d1              sub      sp, sp, #0x50
0x00018e2c: f6 57 02 a9              stp      x22, x21, [sp, #0x20]
0x00018e30: f4 4f 03 a9              stp      x20, x19, [sp, #0x30]
0x00018e34: fd 7b 04 a9              stp      x29, x30, [sp, #0x40]
0x00018e38: fd 03 01 91              add      x29, sp, #0x40
0x00018e3c: 56 d0 3b d5              mrs      x22, tpidr_el0
0x00018e40: c8 16 40 f9              ldr      x8, [x22, #0x28]
0x00018e44: e8 0f 00 f9              str      x8, [sp, #0x18]
0x00018e48: 62 04 00 b4              cbz      x2, #0x18ed4
0x00018e4c: 55 4c 40 a9              ldp      x21, x19, [x2]
0x00018e50: f4 03 03 2a              mov      w20, w3
0x00018e54: d3 00 00 b4              cbz      x19, #0x18e6c
0x00018e58: 68 22 00 91              add      x8, x19, #8
0x00018e5c: 09 7d 5f c8              ldxr     x9, [x8]
0x00018e60: 29 05 00 91              add      x9, x9, #1
0x00018e64: 09 7d 0a c8              stxr     w10, x9, [x8]
0x00018e68: aa ff ff 35              cbnz     w10, #0x18e5c
0x00018e6c: e8 03 00 91              mov      x8, sp
0x00018e70: e1 03 04 aa              mov      x1, x4
0x00018e74: 03 c6 ff 97              bl       #0xa680                    ; -> _ZN8kuaishou5dbase8base_jni11JString2StrEP7_JNIEnvP8_jstring@plt
0x00018e78: a8 02 40 f9              ldr      x8, [x21]
0x00018e7c: 08 15 40 f9              ldr      x8, [x8, #0x28]
0x00018e80: e2 03 00 91              mov      x2, sp
0x00018e84: e0 03 15 aa              mov      x0, x21
0x00018e88: e1 03 14 2a              mov      w1, w20
0x00018e8c: 00 01 3f d6              blr      x8
0x00018e90: e8 03 40 39              ldrb     w8, [sp]
0x00018e94: 68 00 00 36              tbz      w8, #0, #0x18ea0
0x00018e98: e0 0b 40 f9              ldr      x0, [sp, #0x10]
0x00018e9c: c1 c4 ff 97              bl       #0xa1a0                    ; -> _ZdlPv@plt
0x00018ea0: b3 01 00 b4              cbz      x19, #0x18ed4
0x00018ea4: 68 22 00 91              add      x8, x19, #8
0x00018ea8: 09 fd 5f c8              ldaxr    x9, [x8]
0x00018eac: 2a 05 00 d1              sub      x10, x9, #1
0x00018eb0: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00018eb4: ab ff ff 35              cbnz     w11, #0x18ea8
0x00018eb8: e9 00 00 b5              cbnz     x9, #0x18ed4
0x00018ebc: 68 02 40 f9              ldr      x8, [x19]
0x00018ec0: e0 03 13 aa              mov      x0, x19
0x00018ec4: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00018ec8: 00 01 3f d6              blr      x8
0x00018ecc: e0 03 13 aa              mov      x0, x19
0x00018ed0: 84 c4 ff 97              bl       #0xa0e0                    ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00018ed4: c8 16 40 f9              ldr      x8, [x22, #0x28]
0x00018ed8: e9 0f 40 f9              ldr      x9, [sp, #0x18]
0x00018edc: 1f 01 09 eb              cmp      x8, x9
0x00018ee0: c1 00 00 54              b.ne     #0x18ef8
0x00018ee4: fd 7b 44 a9              ldp      x29, x30, [sp, #0x40]
0x00018ee8: f4 4f 43 a9              ldp      x20, x19, [sp, #0x30]
0x00018eec: f6 57 42 a9              ldp      x22, x21, [sp, #0x20]
0x00018ef0: ff 43 01 91              add      sp, sp, #0x50
0x00018ef4: c0 03 5f d6              ret      
0x00018ef8: 8a c4 ff 97              bl       #0xa120                    ; -> __stack_chk_fail@plt
0x00018efc: e8 03 40 39              ldrb     w8, [sp]
0x00018f00: f4 03 00 aa              mov      x20, x0
0x00018f04: c8 00 00 36              tbz      w8, #0, #0x18f1c
0x00018f08: e0 0b 40 f9              ldr      x0, [sp, #0x10]
0x00018f0c: a5 c4 ff 97              bl       #0xa1a0                    ; -> _ZdlPv@plt
0x00018f10: 93 00 00 b5              cbnz     x19, #0x18f20
0x00018f14: 0f 00 00 14              b        #0x18f50                   ; -> Java_com_kwai_camerasdk_face_FaceDetectorContext_nativeSetData+0x128
0x00018f18: f4 03 00 aa              mov      x20, x0
0x00018f1c: b3 01 00 b4              cbz      x19, #0x18f50
0x00018f20: 68 22 00 91              add      x8, x19, #8
0x00018f24: 09 fd 5f c8              ldaxr    x9, [x8]
0x00018f28: 2a 05 00 d1              sub      x10, x9, #1
0x00018f2c: 0a fd 0b c8              stlxr    w11, x10, [x8]
0x00018f30: ab ff ff 35              cbnz     w11, #0x18f24
0x00018f34: e9 00 00 b5              cbnz     x9, #0x18f50
0x00018f38: 68 02 40 f9              ldr      x8, [x19]
0x00018f3c: e0 03 13 aa              mov      x0, x19
0x00018f40: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00018f44: 00 01 3f d6              blr      x8
0x00018f48: e0 03 13 aa              mov      x0, x19
0x00018f4c: 65 c4 ff 97              bl       #0xa0e0                    ; -> _ZNSt6__ndk119__shared_weak_count14__release_weakEv@plt
0x00018f50: e0 03 14 aa              mov      x0, x20
0x00018f54: e7 c4 ff 97              bl       #0xa2f0                    ; -> _Unwind_Resume@plt
