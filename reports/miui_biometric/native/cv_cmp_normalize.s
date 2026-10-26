; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : cv_common_feature_comparator_normalize_score_by_model (0x230d0c)
; start   : 0x230d0c
; size    : 0x5c
; bytes   : 0x5c   instructions: 23
; generated: 2026-10-24T00:35:17

0x00230d0c: fd 7b bd a9              stp      x29, x30, [sp, #-0x30]!
0x00230d10: fd 03 00 91              mov      x29, sp
0x00230d14: f3 0b 00 f9              str      x19, [sp, #0x10]
0x00230d18: 40 02 00 b4              cbz      x0, #0x230d60
0x00230d1c: e1 01 00 b4              cbz      x1, #0x230d58
0x00230d20: f3 03 01 aa              mov      x19, x1
0x00230d24: a1 c3 00 91              add      x1, x29, #0x30
0x00230d28: 20 cc 1f bc              str      s0, [x1, #-4]!
0x00230d2c: 62 4e 00 94              bl       #0x2446b4                  ; -> _ZN5stsdk10FeatureCmp14normalizeScoreERf
0x00230d30: 00 1c 00 53              uxtb     w0, w0
0x00230d34: e0 00 00 34              cbz      w0, #0x230d50
0x00230d38: a1 2f 40 b9              ldr      w1, [x29, #0x2c]
0x00230d3c: 00 00 80 52              mov      w0, #0
0x00230d40: 61 02 00 b9              str      w1, [x19]
0x00230d44: f3 0b 40 f9              ldr      x19, [sp, #0x10]
0x00230d48: fd 7b c3 a8              ldp      x29, x30, [sp], #0x30
0x00230d4c: c0 03 5f d6              ret      
0x00230d50: 60 00 80 12              mov      w0, #-4
0x00230d54: fc ff ff 17              b        #0x230d44                  ; -> cv_common_feature_comparator_normalize_score_by_model+0x38
0x00230d58: 00 00 80 12              mov      w0, #-1
0x00230d5c: fa ff ff 17              b        #0x230d44                  ; -> cv_common_feature_comparator_normalize_score_by_model+0x38
0x00230d60: 20 00 80 12              mov      w0, #-2
0x00230d64: f8 ff ff 17              b        #0x230d44                  ; -> cv_common_feature_comparator_normalize_score_by_model+0x38
