; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : address 0x2b83c0
; start   : 0x2b83c0
; size    : 0x1d4
; bytes   : 0x1d4   instructions: 117
; generated: 2026-10-23T23:34:53

0x002b83c0: fd 7b ba a9              stp      x29, x30, [sp, #-0x60]!
0x002b83c4: fd 03 00 91              mov      x29, sp
0x002b83c8: f3 53 01 a9              stp      x19, x20, [sp, #0x10]
0x002b83cc: f5 5b 02 a9              stp      x21, x22, [sp, #0x20]
0x002b83d0: 21 0b 00 b4              cbz      x1, #0x2b8534
0x002b83d4: 02 0b 00 b4              cbz      x2, #0x2b8534
0x002b83d8: f5 03 00 aa              mov      x21, x0
0x002b83dc: a0 83 01 91              add      x0, x29, #0x60
0x002b83e0: f3 03 01 aa              mov      x19, x1
0x002b83e4: 01 00 80 12              mov      w1, #-1
0x002b83e8: f4 03 02 aa              mov      x20, x2
0x002b83ec: 01 cc 1e b8              str      w1, [x0, #-0x14]!
0x002b83f0: 7f 66 00 94              bl       #0x2d1dec                  ; -> _Z38get_model_loader_bypass_license_statusPi
0x002b83f4: a0 4f 40 b9              ldr      w0, [x29, #0x4c]
0x002b83f8: 20 05 00 34              cbz      w0, #0x2b849c
0x002b83fc: 00 08 80 d2              mov      x0, #0x40
0x002b8400: fd c9 10 94              bl       #0x6eabf4                  ; -> _Znwm
0x002b8404: f6 03 00 aa              mov      x22, x0
0x002b8408: e1 03 13 aa              mov      x1, x19
0x002b840c: e2 03 14 aa              mov      x2, x20
0x002b8410: b8 11 00 94              bl       #0x2bcaf0                  ; -> _ZN9protector19MemoryModelResourceC2EPhS1_
0x002b8414: 00 03 80 d2              mov      x0, #0x18
0x002b8418: b6 2b 00 f9              str      x22, [x29, #0x50]
0x002b841c: bf 2f 00 f9              str      xzr, [x29, #0x58]
0x002b8420: f5 c9 10 94              bl       #0x6eabf4                  ; -> _Znwm
0x002b8424: e2 03 00 aa              mov      x2, x0
0x002b8428: 23 00 80 52              mov      w3, #1
0x002b842c: e0 03 15 aa              mov      x0, x21
0x002b8430: a1 43 01 91              add      x1, x29, #0x50
0x002b8434: 43 08 00 b9              str      w3, [x2, #8]
0x002b8438: 43 0c 00 b9              str      w3, [x2, #0xc]
0x002b843c: 43 62 02 f0              adrp     x3, #0x4f03000
0x002b8440: 63 40 12 91              add      x3, x3, #0x490
0x002b8444: 56 08 00 f9              str      x22, [x2, #0x10]
0x002b8448: 43 00 00 f9              str      x3, [x2]
0x002b844c: a2 2f 00 f9              str      x2, [x29, #0x58]
0x002b8450: 29 fd ff 97              bl       #0x2b78f4                  ; -> _ZN9protector11ModelLoader4LoadESt10shared_ptrINS_13ModelResourceEE
0x002b8454: b3 2f 40 f9              ldr      x19, [x29, #0x58]
0x002b8458: f4 03 00 2a              mov      w20, w0
0x002b845c: 73 01 00 b4              cbz      x19, #0x2b8488
0x002b8460: 80 63 02 b0              adrp     x0, #0x4f29000
0x002b8464: 00 94 47 f9              ldr      x0, [x0, #0xf28]           ; -> pthread_create@got
0x002b8468: c0 02 00 b4              cbz      x0, #0x2b84c0
0x002b846c: 60 22 00 91              add      x0, x19, #8
0x002b8470: 01 fc 5f 88              ldaxr    w1, [x0]
0x002b8474: 22 04 00 51              sub      w2, w1, #1
0x002b8478: 02 fc 03 88              stlxr    w3, w2, [x0]
0x002b847c: a3 ff ff 35              cbnz     w3, #0x2b8470
0x002b8480: 3f 04 00 71              cmp      w1, #1
0x002b8484: 80 03 00 54              b.eq     #0x2b84f4
0x002b8488: e0 03 14 2a              mov      w0, w20
0x002b848c: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x002b8490: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x002b8494: fd 7b c6 a8              ldp      x29, x30, [sp], #0x60
0x002b8498: c0 03 5f d6              ret      
0x002b849c: 00 00 80 d2              mov      x0, #0
0x002b84a0: b4 18 04 94              bl       #0x3be770                  ; -> sdk_protector_has_license
0x002b84a4: c0 fa ff 34              cbz      w0, #0x2b83fc
0x002b84a8: 94 01 80 12              mov      w20, #-0xd
0x002b84ac: e0 03 14 2a              mov      w0, w20
0x002b84b0: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x002b84b4: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x002b84b8: fd 7b c6 a8              ldp      x29, x30, [sp], #0x60
0x002b84bc: c0 03 5f d6              ret      
0x002b84c0: 60 0a 40 b9              ldr      w0, [x19, #8]
0x002b84c4: 01 04 00 51              sub      w1, w0, #1
0x002b84c8: 1f 04 00 71              cmp      w0, #1
0x002b84cc: 61 0a 00 b9              str      w1, [x19, #8]
0x002b84d0: c1 fd ff 54              b.ne     #0x2b8488
0x002b84d4: 61 02 40 f9              ldr      x1, [x19]
0x002b84d8: e0 03 13 aa              mov      x0, x19
0x002b84dc: 21 08 40 f9              ldr      x1, [x1, #0x10]
0x002b84e0: 20 00 3f d6              blr      x1
0x002b84e4: 60 0e 40 b9              ldr      w0, [x19, #0xc]
0x002b84e8: 01 04 00 51              sub      w1, w0, #1
0x002b84ec: 61 0e 00 b9              str      w1, [x19, #0xc]
0x002b84f0: 0a 00 00 14              b        #0x2b8518                  ; -> _ZN9protector11ModelLoader4LoadEPhS1_b+0x158
0x002b84f4: 61 02 40 f9              ldr      x1, [x19]
0x002b84f8: e0 03 13 aa              mov      x0, x19
0x002b84fc: 21 08 40 f9              ldr      x1, [x1, #0x10]
0x002b8500: 20 00 3f d6              blr      x1
0x002b8504: 61 32 00 91              add      x1, x19, #0xc
0x002b8508: 20 fc 5f 88              ldaxr    w0, [x1]
0x002b850c: 02 04 00 51              sub      w2, w0, #1
0x002b8510: 22 fc 03 88              stlxr    w3, w2, [x1]
0x002b8514: a3 ff ff 35              cbnz     w3, #0x2b8508
0x002b8518: 1f 04 00 71              cmp      w0, #1
0x002b851c: 61 fb ff 54              b.ne     #0x2b8488
0x002b8520: 61 02 40 f9              ldr      x1, [x19]
0x002b8524: e0 03 13 aa              mov      x0, x19
0x002b8528: 21 0c 40 f9              ldr      x1, [x1, #0x18]
0x002b852c: 20 00 3f d6              blr      x1
0x002b8530: d6 ff ff 17              b        #0x2b8488                  ; -> _ZN9protector11ModelLoader4LoadEPhS1_b+0xc8
0x002b8534: 14 00 80 12              mov      w20, #-1
0x002b8538: d4 ff ff 17              b        #0x2b8488                  ; -> _ZN9protector11ModelLoader4LoadEPhS1_b+0xc8
0x002b853c: a3 c4 10 94              bl       #0x6e97c8                  ; -> __cxa_begin_catch
0x002b8540: c1 02 40 f9              ldr      x1, [x22]
0x002b8544: e0 03 16 aa              mov      x0, x22
0x002b8548: 21 04 40 f9              ldr      x1, [x1, #8]
0x002b854c: 20 00 3f d6              blr      x1
0x002b8550: 72 c8 10 94              bl       #0x6ea718                  ; -> __cxa_rethrow
0x002b8554: f3 03 00 aa              mov      x19, x0
0x002b8558: e0 03 16 aa              mov      x0, x22
0x002b855c: 29 c3 10 94              bl       #0x6e9200                  ; -> _ZdlPv
0x002b8560: e0 03 13 aa              mov      x0, x19
0x002b8564: 54 5f 12 94              bl       #0x7502b4                  ; -> _Unwind_Resume
0x002b8568: a1 2f 40 f9              ldr      x1, [x29, #0x58]
0x002b856c: f3 03 00 aa              mov      x19, x0
0x002b8570: 61 00 00 b4              cbz      x1, #0x2b857c
0x002b8574: e0 03 01 aa              mov      x0, x1
0x002b8578: 1e 67 f9 97              bl       #0x1121f0                  ; -> _ZNSt16_Sp_counted_baseILN9__gnu_cxx12_Lock_policyE2EE10_M_releaseEv
0x002b857c: e0 03 13 aa              mov      x0, x19
0x002b8580: 4d 5f 12 94              bl       #0x7502b4                  ; -> _Unwind_Resume
0x002b8584: a0 1f 00 f9              str      x0, [x29, #0x38]
0x002b8588: b8 c4 10 94              bl       #0x6e9868                  ; -> __cxa_end_catch
0x002b858c: a0 1f 40 f9              ldr      x0, [x29, #0x38]
0x002b8590: 49 5f 12 94              bl       #0x7502b4                  ; -> _Unwind_Resume
