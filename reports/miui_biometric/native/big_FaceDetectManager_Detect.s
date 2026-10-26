; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : _ZN12stfaceunlock17FaceDetectManager6DetectEPK10st_image_t19st_face_orientationjPS4_PP9st_face_tPii (0x143ecc)
; start   : 0x143ecc
; size    : 0x44
; bytes   : 0x44   instructions: 17
; generated: 2026-10-24T00:34:11

0x00143ecc: fd 7b be a9              stp      x29, x30, [sp, #-0x20]!
0x00143ed0: fd 03 00 91              mov      x29, sp
0x00143ed4: 00 00 40 f9              ldr      x0, [x0]
0x00143ed8: a2 1f 00 b9              str      w2, [x29, #0x1c]
0x00143edc: a0 00 00 b4              cbz      x0, #0x143ef0
0x00143ee0: a2 73 00 91              add      x2, x29, #0x1c
0x00143ee4: 41 03 00 94              bl       #0x144be8                  ; -> _ZN12stfaceunlock18FaceDetectionClass6DetectEPK10st_image_tRK19st_face_orientationjPS4_PP9st_face_tPii
0x00143ee8: fd 7b c2 a8              ldp      x29, x30, [sp], #0x20
0x00143eec: c0 03 5f d6              ret      
0x00143ef0: 00 31 00 90              adrp     x0, #0x763000
0x00143ef4: 01 31 00 90              adrp     x1, #0x763000
0x00143ef8: 00 80 01 91              add      x0, x0, #0x60
0x00143efc: 21 60 02 91              add      x1, x1, #0x98
0x00143f00: c2 02 80 52              mov      w2, #0x16
0x00143f04: 7a eb ff 97              bl       #0x13ecec                  ; -> _Z4LogEPKcz
0x00143f08: 60 00 80 12              mov      w0, #-4
0x00143f0c: f7 ff ff 17              b        #0x143ee8                  ; -> _ZN12stfaceunlock17FaceDetectManager6DetectEPK10st_image_t19st_face_orientationjPS4_PP9st_face_tPii+0x1c
