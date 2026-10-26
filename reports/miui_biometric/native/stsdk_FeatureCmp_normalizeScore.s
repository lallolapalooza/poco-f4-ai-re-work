; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : _ZN5stsdk10FeatureCmp14normalizeScoreERf (0x2446b4)
; start   : 0x2446b4
; size    : 0x2c
; bytes   : 0x2c   instructions: 11
; generated: 2026-10-24T00:35:48

0x002446b4: fd 7b bf a9              stp      x29, x30, [sp, #-0x10]!
0x002446b8: fd 03 00 91              mov      x29, sp
0x002446bc: 00 00 40 f9              ldr      x0, [x0]
0x002446c0: a0 00 00 b4              cbz      x0, #0x2446d4
0x002446c4: e2 29 02 94              bl       #0x2cee4c                  ; -> _ZN9protector9NormModel3RunERf
0x002446c8: 20 00 80 52              mov      w0, #1
0x002446cc: fd 7b c1 a8              ldp      x29, x30, [sp], #0x10
0x002446d0: c0 03 5f d6              ret      
0x002446d4: 00 00 80 52              mov      w0, #0
0x002446d8: fd 7b c1 a8              ldp      x29, x30, [sp], #0x10
0x002446dc: c0 03 5f d6              ret      
