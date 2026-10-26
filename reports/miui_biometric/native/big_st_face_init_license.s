; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : st_face_init_license (0xfc800)
; start   : 0xfc800
; size    : 0x58
; bytes   : 0x58   instructions: 22
; generated: 2026-10-24T00:26:35

0x000fc800: fd 7b bc a9              stp      x29, x30, [sp, #-0x40]!
0x000fc804: fd 03 00 91              mov      x29, sp
0x000fc808: f3 53 01 a9              stp      x19, x20, [sp, #0x10]
0x000fc80c: b3 e3 00 91              add      x19, x29, #0x38
0x000fc810: a0 17 00 f9              str      x0, [x29, #0x28]
0x000fc814: e0 03 13 aa              mov      x0, x19
0x000fc818: 71 ba 00 94              bl       #0x12b1dc                  ; -> _ZN12stfaceunlock13LicenseLoaderC2Ev
0x000fc81c: a1 17 40 f9              ldr      x1, [x29, #0x28]
0x000fc820: e0 03 13 aa              mov      x0, x19
0x000fc824: 70 ba 00 94              bl       #0x12b1e4                  ; -> _ZN12stfaceunlock13LicenseLoader11InitLicenseEPKc
0x000fc828: f4 03 00 2a              mov      w20, w0
0x000fc82c: e0 03 13 aa              mov      x0, x19
0x000fc830: 6c ba 00 94              bl       #0x12b1e0                  ; -> _ZN12stfaceunlock13LicenseLoaderD2Ev
0x000fc834: e0 03 14 2a              mov      w0, w20
0x000fc838: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x000fc83c: fd 7b c4 a8              ldp      x29, x30, [sp], #0x40
0x000fc840: c0 03 5f d6              ret      
0x000fc844: f4 03 00 aa              mov      x20, x0
0x000fc848: e0 03 13 aa              mov      x0, x19
0x000fc84c: 65 ba 00 94              bl       #0x12b1e0                  ; -> _ZN12stfaceunlock13LicenseLoaderD2Ev
0x000fc850: e0 03 14 aa              mov      x0, x20
0x000fc854: 98 4e 19 94              bl       #0x7502b4                  ; -> _Unwind_Resume
