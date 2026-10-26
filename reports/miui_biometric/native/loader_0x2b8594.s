; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : address 0x2b8594
; start   : 0x2b8594
; size    : 0x1c8
; bytes   : 0x1c8   instructions: 114
; generated: 2026-10-23T23:35:20

0x002b8594: fd 7b ba a9              stp      x29, x30, [sp, #-0x60]!
0x002b8598: fd 03 00 91              mov      x29, sp
0x002b859c: f3 53 01 a9              stp      x19, x20, [sp, #0x10]
0x002b85a0: f5 13 00 f9              str      x21, [sp, #0x20]
0x002b85a4: c1 0a 00 b4              cbz      x1, #0x2b86fc
0x002b85a8: f4 03 00 aa              mov      x20, x0
0x002b85ac: a0 83 01 91              add      x0, x29, #0x60
0x002b85b0: f3 03 01 aa              mov      x19, x1
0x002b85b4: 01 00 80 12              mov      w1, #-1
0x002b85b8: 01 cc 1e b8              str      w1, [x0, #-0x14]!
0x002b85bc: 0c 66 00 94              bl       #0x2d1dec                  ; -> _Z38get_model_loader_bypass_license_statusPi
0x002b85c0: a0 4f 40 b9              ldr      w0, [x29, #0x4c]
0x002b85c4: 00 05 00 34              cbz      w0, #0x2b8664
0x002b85c8: 00 05 80 d2              mov      x0, #0x28
0x002b85cc: 8a c9 10 94              bl       #0x6eabf4                  ; -> _Znwm
0x002b85d0: f5 03 00 aa              mov      x21, x0
0x002b85d4: e1 03 13 aa              mov      x1, x19
0x002b85d8: e0 0c 00 94              bl       #0x2bb958                  ; -> _ZN9protector17FileModelResourceC2EPKc
0x002b85dc: 00 03 80 d2              mov      x0, #0x18
0x002b85e0: b5 2b 00 f9              str      x21, [x29, #0x50]
0x002b85e4: bf 2f 00 f9              str      xzr, [x29, #0x58]
0x002b85e8: 83 c9 10 94              bl       #0x6eabf4                  ; -> _Znwm
0x002b85ec: e2 03 00 aa              mov      x2, x0
0x002b85f0: 23 00 80 52              mov      w3, #1
0x002b85f4: e0 03 14 aa              mov      x0, x20
0x002b85f8: a1 43 01 91              add      x1, x29, #0x50
0x002b85fc: 43 08 00 b9              str      w3, [x2, #8]
0x002b8600: 43 0c 00 b9              str      w3, [x2, #0xc]
0x002b8604: 43 62 02 f0              adrp     x3, #0x4f03000
0x002b8608: 63 40 13 91              add      x3, x3, #0x4d0
0x002b860c: 55 08 00 f9              str      x21, [x2, #0x10]
0x002b8610: 43 00 00 f9              str      x3, [x2]
0x002b8614: a2 2f 00 f9              str      x2, [x29, #0x58]
0x002b8618: b7 fc ff 97              bl       #0x2b78f4                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE
0x002b861c: b3 2f 40 f9              ldr      x19, [x29, #0x58]
0x002b8620: f4 03 00 2a              mov      w20, w0
0x002b8624: 73 01 00 b4              cbz      x19, #0x2b8650
0x002b8628: 80 63 02 b0              adrp     x0, #0x4f29000
0x002b862c: 00 94 47 f9              ldr      x0, [x0, #0xf28]           ; -> pthread_create@got
0x002b8630: c0 02 00 b4              cbz      x0, #0x2b8688
0x002b8634: 60 22 00 91              add      x0, x19, #8
0x002b8638: 01 fc 5f 88              ldaxr    w1, [x0]
0x002b863c: 22 04 00 51              sub      w2, w1, #1
0x002b8640: 02 fc 03 88              stlxr    w3, w2, [x0]
0x002b8644: a3 ff ff 35              cbnz     w3, #0x2b8638
0x002b8648: 3f 04 00 71              cmp      w1, #1
0x002b864c: 80 03 00 54              b.eq     #0x2b86bc
0x002b8650: e0 03 14 2a              mov      w0, w20
0x002b8654: f5 13 40 f9              ldr      x21, [sp, #0x20]
0x002b8658: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x002b865c: fd 7b c6 a8              ldp      x29, x30, [sp], #0x60
0x002b8660: c0 03 5f d6              ret      
0x002b8664: 00 00 80 d2              mov      x0, #0
0x002b8668: 42 18 04 94              bl       #0x3be770                  ; -> sdk_protector_has_license
0x002b866c: e0 fa ff 34              cbz      w0, #0x2b85c8
0x002b8670: 94 01 80 12              mov      w20, #-0xd
0x002b8674: f5 13 40 f9              ldr      x21, [sp, #0x20]
0x002b8678: e0 03 14 2a              mov      w0, w20
0x002b867c: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x002b8680: fd 7b c6 a8              ldp      x29, x30, [sp], #0x60
0x002b8684: c0 03 5f d6              ret      
0x002b8688: 60 0a 40 b9              ldr      w0, [x19, #8]
0x002b868c: 01 04 00 51              sub      w1, w0, #1
0x002b8690: 1f 04 00 71              cmp      w0, #1
0x002b8694: 61 0a 00 b9              str      w1, [x19, #8]
0x002b8698: c1 fd ff 54              b.ne     #0x2b8650
0x002b869c: 61 02 40 f9              ldr      x1, [x19]
0x002b86a0: e0 03 13 aa              mov      x0, x19
0x002b86a4: 21 08 40 f9              ldr      x1, [x1, #0x10]
0x002b86a8: 20 00 3f d6              blr      x1
0x002b86ac: 60 0e 40 b9              ldr      w0, [x19, #0xc]
0x002b86b0: 01 04 00 51              sub      w1, w0, #1
0x002b86b4: 61 0e 00 b9              str      w1, [x19, #0xc]
0x002b86b8: 0a 00 00 14              b        #0x2b86e0                  ; -> _ZN9protector11ModelLoader4LoadEPKcb+0x14c
0x002b86bc: 61 02 40 f9              ldr      x1, [x19]
0x002b86c0: e0 03 13 aa              mov      x0, x19
0x002b86c4: 21 08 40 f9              ldr      x1, [x1, #0x10]
0x002b86c8: 20 00 3f d6              blr      x1
0x002b86cc: 61 32 00 91              add      x1, x19, #0xc
0x002b86d0: 20 fc 5f 88              ldaxr    w0, [x1]
0x002b86d4: 02 04 00 51              sub      w2, w0, #1
0x002b86d8: 22 fc 03 88              stlxr    w3, w2, [x1]
0x002b86dc: a3 ff ff 35              cbnz     w3, #0x2b86d0
0x002b86e0: 1f 04 00 71              cmp      w0, #1
0x002b86e4: 61 fb ff 54              b.ne     #0x2b8650
0x002b86e8: 61 02 40 f9              ldr      x1, [x19]
0x002b86ec: e0 03 13 aa              mov      x0, x19
0x002b86f0: 21 0c 40 f9              ldr      x1, [x1, #0x18]
0x002b86f4: 20 00 3f d6              blr      x1
0x002b86f8: d6 ff ff 17              b        #0x2b8650                  ; -> _ZN9protector11ModelLoader4LoadEPKcb+0xbc
0x002b86fc: 14 00 80 12              mov      w20, #-1
0x002b8700: d4 ff ff 17              b        #0x2b8650                  ; -> _ZN9protector11ModelLoader4LoadEPKcb+0xbc
0x002b8704: f3 03 00 aa              mov      x19, x0
0x002b8708: e0 03 15 aa              mov      x0, x21
0x002b870c: bd c2 10 94              bl       #0x6e9200                  ; -> _ZdlPv
0x002b8710: e0 03 13 aa              mov      x0, x19
0x002b8714: e8 5e 12 94              bl       #0x7502b4                  ; -> _Unwind_Resume
0x002b8718: a1 2f 40 f9              ldr      x1, [x29, #0x58]
0x002b871c: f3 03 00 aa              mov      x19, x0
0x002b8720: 61 00 00 b4              cbz      x1, #0x2b872c
0x002b8724: e0 03 01 aa              mov      x0, x1
0x002b8728: b2 66 f9 97              bl       #0x1121f0                  ; -> _ZNSt16_Sp_counted_baseILN9__gnu_cxx12_Lock_policyE2EE10_M_releaseEv
0x002b872c: e0 03 13 aa              mov      x0, x19
0x002b8730: e1 5e 12 94              bl       #0x7502b4                  ; -> _Unwind_Resume
0x002b8734: 25 c4 10 94              bl       #0x6e97c8                  ; -> __cxa_begin_catch
0x002b8738: a1 02 40 f9              ldr      x1, [x21]
0x002b873c: e0 03 15 aa              mov      x0, x21
0x002b8740: 21 04 40 f9              ldr      x1, [x1, #8]
0x002b8744: 20 00 3f d6              blr      x1
0x002b8748: f4 c7 10 94              bl       #0x6ea718                  ; -> __cxa_rethrow
0x002b874c: a0 1f 00 f9              str      x0, [x29, #0x38]
0x002b8750: 46 c4 10 94              bl       #0x6e9868                  ; -> __cxa_end_catch
0x002b8754: a0 1f 40 f9              ldr      x0, [x29, #0x38]
0x002b8758: d7 5e 12 94              bl       #0x7502b4                  ; -> _Unwind_Resume
