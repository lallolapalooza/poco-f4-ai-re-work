; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/samples/android/libstfaceunlockocl.so
; target  : _Z26sdk_protector_fast_decryptPjPKhPhi (0x3a3298)
; start   : 0x3a3298
; size    : 0x68
; bytes   : 0x68   instructions: 26
; generated: 2026-10-23T23:21:52

0x003a3298: 63 7c 40 93              sxtw     x3, w3
0x003a329c: 05 00 80 52              mov      w5, #0
0x003a32a0: 63 fc 44 d3              lsr      x3, x3, #4
0x003a32a4: c3 02 00 b4              cbz      x3, #0x3a32fc
0x003a32a8: 42 40 00 91              add      x2, x2, #0x10
0x003a32ac: 26 00 40 b9              ldr      w6, [x1]
0x003a32b0: 04 00 40 b9              ldr      w4, [x0]
0x003a32b4: 21 40 00 91              add      x1, x1, #0x10
0x003a32b8: a5 04 00 11              add      w5, w5, #1
0x003a32bc: 7f 40 25 eb              cmp      x3, w5, uxtw
0x003a32c0: c4 00 04 4a              eor      w4, w6, w4
0x003a32c4: 44 00 1f b8              stur     w4, [x2, #-0x10]
0x003a32c8: 26 40 5f b8              ldur     w6, [x1, #-0xc]
0x003a32cc: 04 04 40 b9              ldr      w4, [x0, #4]
0x003a32d0: c4 00 04 4a              eor      w4, w6, w4
0x003a32d4: 44 40 1f b8              stur     w4, [x2, #-0xc]
0x003a32d8: 26 80 5f b8              ldur     w6, [x1, #-8]
0x003a32dc: 04 08 40 b9              ldr      w4, [x0, #8]
0x003a32e0: c4 00 04 4a              eor      w4, w6, w4
0x003a32e4: 44 80 1f b8              stur     w4, [x2, #-8]
0x003a32e8: 26 c0 5f b8              ldur     w6, [x1, #-4]
0x003a32ec: 04 0c 40 b9              ldr      w4, [x0, #0xc]
0x003a32f0: c4 00 04 4a              eor      w4, w6, w4
0x003a32f4: 44 c0 1f b8              stur     w4, [x2, #-4]
0x003a32f8: 88 fd ff 54              b.hi     #0x3a32a8
0x003a32fc: c0 03 5f d6              ret      
