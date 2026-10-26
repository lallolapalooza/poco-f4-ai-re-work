; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : _ZN12stfaceunlock17FaceVerifyManager14CompareFeatureEPK12st_feature_tS3_Pf (0x126064)
; start   : 0x126064
; size    : 0x44
; bytes   : 0x44   instructions: 17
; generated: 2026-10-24T00:34:34

0x00126064: fd 7b bf a9              stp      x29, x30, [sp, #-0x10]!
0x00126068: fd 03 00 91              mov      x29, sp
0x0012606c: 00 00 40 f9              ldr      x0, [x0]
0x00126070: c0 00 00 b4              cbz      x0, #0x126088
0x00126074: 04 00 40 f9              ldr      x4, [x0]
0x00126078: 84 10 40 f9              ldr      x4, [x4, #0x20]
0x0012607c: 80 00 3f d6              blr      x4
0x00126080: fd 7b c1 a8              ldp      x29, x30, [sp], #0x10
0x00126084: c0 03 5f d6              ret      
0x00126088: c0 31 00 90              adrp     x0, #0x75e000
0x0012608c: c1 31 00 90              adrp     x1, #0x75e000
0x00126090: 00 60 3a 91              add      x0, x0, #0xe98
0x00126094: 21 a0 35 91              add      x1, x1, #0xd68
0x00126098: e2 10 80 52              mov      w2, #0x87
0x0012609c: 14 63 00 94              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x001260a0: 60 00 80 12              mov      w0, #-4
0x001260a4: f7 ff ff 17              b        #0x126080                  ; -> _ZN12stfaceunlock17FaceVerifyManager14CompareFeatureEPK12st_feature_tS3_Pf+0x1c
