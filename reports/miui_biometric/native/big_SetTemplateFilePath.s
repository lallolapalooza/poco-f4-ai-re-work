; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : _ZN12stfaceunlock21FaceUnlock2DInterFace19SetTemplateFilePathEPKc (0x146a88)
; start   : 0x146a88
; size    : 0x12c
; bytes   : 0x12c   instructions: 75
; generated: 2026-10-24T00:41:44

0x00146a88: fd 7b bd a9              stp      x29, x30, [sp, #-0x30]!
0x00146a8c: fd 03 00 91              mov      x29, sp
0x00146a90: f3 53 01 a9              stp      x19, x20, [sp, #0x10]
0x00146a94: f5 5b 02 a9              stp      x21, x22, [sp, #0x20]
0x00146a98: f3 03 01 aa              mov      x19, x1
0x00146a9c: f5 03 00 aa              mov      x21, x0
0x00146aa0: e1 06 00 b4              cbz      x1, #0x146b7c
0x00146aa4: e0 03 01 aa              mov      x0, x1
0x00146aa8: be f0 ff 97              bl       #0x142da0                  ; -> _ZN12stfaceunlock9CommonFun9CreateDirEPKc
0x00146aac: 00 1c 00 53              uxtb     w0, w0
0x00146ab0: 40 05 00 34              cbz      w0, #0x146b58
0x00146ab4: e0 03 13 aa              mov      x0, x19
0x00146ab8: 1a 08 fd 97              bl       #0x88b20                   ; -> strlen@plt
0x00146abc: e2 03 00 aa              mov      x2, x0
0x00146ac0: e1 03 13 aa              mov      x1, x19
0x00146ac4: a0 02 01 91              add      x0, x21, #0x40
0x00146ac8: 87 bf 17 94              bl       #0x7368e4                  ; -> _ZNSs6assignEPKcm
0x00146acc: a0 26 40 f9              ldr      x0, [x21, #0x48]
0x00146ad0: c0 00 00 b4              cbz      x0, #0x146ae8
0x00146ad4: e1 03 13 aa              mov      x1, x19
0x00146ad8: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x00146adc: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x00146ae0: fd 7b c3 a8              ldp      x29, x30, [sp], #0x30
0x00146ae4: 01 b7 ff 17              b        #0x1346e8                  ; -> _ZN12stfaceunlock14FeatureManager20SetTempldateFilePathEPKc
0x00146ae8: 36 6f 02 90              adrp     x22, #0x4f2a000
0x00146aec: 00 09 80 d2              mov      x0, #0x48
0x00146af0: c1 6e 46 f9              ldr      x1, [x22, #0xcd8]
0x00146af4: 5c 90 16 94              bl       #0x6eac64                  ; -> _ZnwmRKSt9nothrow_t
0x00146af8: f4 03 00 aa              mov      x20, x0
0x00146afc: 80 01 00 b4              cbz      x0, #0x146b2c
0x00146b00: 27 bc ff 97              bl       #0x135b9c                  ; -> _ZN12stfaceunlock14FeatureManagerC2Ev
0x00146b04: b6 26 40 f9              ldr      x22, [x21, #0x48]
0x00146b08: e0 03 14 aa              mov      x0, x20
0x00146b0c: b4 26 00 f9              str      x20, [x21, #0x48]
0x00146b10: 36 fe ff b4              cbz      x22, #0x146ad4
0x00146b14: e0 03 16 aa              mov      x0, x22
0x00146b18: 2a ba ff 97              bl       #0x1353c0                  ; -> _ZN12stfaceunlock14FeatureManagerD2Ev
0x00146b1c: e0 03 16 aa              mov      x0, x22
0x00146b20: b8 89 16 94              bl       #0x6e9200                  ; -> _ZdlPv
0x00146b24: a0 26 40 f9              ldr      x0, [x21, #0x48]
0x00146b28: eb ff ff 17              b        #0x146ad4                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace19SetTemplateFilePathEPKc+0x4c
0x00146b2c: e0 30 00 d0              adrp     x0, #0x764000
0x00146b30: e1 30 00 d0              adrp     x1, #0x764000
0x00146b34: 00 a0 27 91              add      x0, x0, #0x9e8
0x00146b38: 21 e0 12 91              add      x1, x1, #0x4b8
0x00146b3c: 42 b2 80 52              mov      w2, #0x592
0x00146b40: 6b e0 ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x00146b44: 40 00 80 12              mov      w0, #-3
0x00146b48: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x00146b4c: f5 5b 42 a9              ldp      x21, x22, [sp, #0x20]
0x00146b50: fd 7b c3 a8              ldp      x29, x30, [sp], #0x30
0x00146b54: c0 03 5f d6              ret      
0x00146b58: e0 30 00 d0              adrp     x0, #0x764000
0x00146b5c: e1 30 00 d0              adrp     x1, #0x764000
0x00146b60: 00 80 26 91              add      x0, x0, #0x9a0
0x00146b64: 21 e0 12 91              add      x1, x1, #0x4b8
0x00146b68: 62 b1 80 52              mov      w2, #0x58b
0x00146b6c: e3 03 13 aa              mov      x3, x19
0x00146b70: 5f e0 ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x00146b74: 20 7d 80 12              mov      w0, #-0x3ea
0x00146b78: f4 ff ff 17              b        #0x146b48                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace19SetTemplateFilePathEPKc+0xc0
0x00146b7c: e0 30 00 d0              adrp     x0, #0x764000
0x00146b80: e1 30 00 d0              adrp     x1, #0x764000
0x00146b84: 00 60 25 91              add      x0, x0, #0x958
0x00146b88: 21 e0 12 91              add      x1, x1, #0x4b8
0x00146b8c: e2 b0 80 52              mov      w2, #0x587
0x00146b90: 57 e0 ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x00146b94: 00 00 80 12              mov      w0, #-1
0x00146b98: ec ff ff 17              b        #0x146b48                  ; -> _ZN12stfaceunlock21FaceUnlock2DInterFace19SetTemplateFilePathEPKc+0xc0
0x00146b9c: c1 6e 46 f9              ldr      x1, [x22, #0xcd8]
0x00146ba0: f3 03 00 aa              mov      x19, x0
0x00146ba4: e0 03 14 aa              mov      x0, x20
0x00146ba8: 97 89 16 94              bl       #0x6e9204                  ; -> _ZdlPvRKSt9nothrow_t
0x00146bac: e0 03 13 aa              mov      x0, x19
0x00146bb0: c1 25 18 94              bl       #0x7502b4                  ; -> _Unwind_Resume
