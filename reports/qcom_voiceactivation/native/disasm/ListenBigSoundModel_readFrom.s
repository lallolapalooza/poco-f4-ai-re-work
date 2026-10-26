; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libqvasml.so
; target  : address 0x63890
; start   : 0x63890
; size    : 0x220
; bytes   : 0x220   instructions: 136
; generated: 2026-10-23T23:24:16

0x00063890: 3f 23 03 d5              paciasp  
0x00063894: fd 7b bd a9              stp      x29, x30, [sp, #-0x30]!
0x00063898: f6 57 01 a9              stp      x22, x21, [sp, #0x10]
0x0006389c: f4 4f 02 a9              stp      x20, x19, [sp, #0x20]
0x000638a0: fd 03 00 91              mov      x29, sp
0x000638a4: f3 03 00 aa              mov      x19, x0
0x000638a8: 00 04 40 f9              ldr      x0, [x0, #8]
0x000638ac: f5 03 02 2a              mov      w21, w2
0x000638b0: f4 03 01 aa              mov      x20, x1
0x000638b4: 68 0a 40 f9              ldr      x8, [x19, #0x10]
0x000638b8: 16 08 40 b9              ldr      w22, [x0, #8]
0x000638bc: e8 00 00 b4              cbz      x8, #0x638d8
0x000638c0: e0 03 08 aa              mov      x0, x8
0x000638c4: b3 b9 02 94              bl       #0x111f90                  ; -> _ZdaPv@plt
0x000638c8: 60 06 40 f9              ldr      x0, [x19, #8]
0x000638cc: 7f 0a 00 f9              str      xzr, [x19, #0x10]
0x000638d0: 60 00 00 b5              cbnz     x0, #0x638dc
0x000638d4: 03 00 00 14              b        #0x638e0                   ; -> _ZN19ListenBigSoundModel8readFromEPhj+0x50
0x000638d8: 7f 0a 00 f9              str      xzr, [x19, #0x10]
0x000638dc: 21 78 02 94              bl       #0x101960
0x000638e0: e0 03 16 2a              mov      w0, w22
0x000638e4: 19 67 02 94              bl       #0xfd548
0x000638e8: 60 06 00 f9              str      x0, [x19, #8]
0x000638ec: 80 02 00 b4              cbz      x0, #0x6393c
0x000638f0: 08 08 40 b9              ldr      w8, [x0, #8]
0x000638f4: 09 75 16 12              and      w9, w8, #0xfffffcff
0x000638f8: 3f 01 10 71              cmp      w9, #0x400
0x000638fc: a0 00 00 54              b.eq     #0x63910
0x00063900: 1f 01 0c 71              cmp      w8, #0x300
0x00063904: 60 00 00 54              b.eq     #0x63910
0x00063908: 1f 01 20 71              cmp      w8, #0x800
0x0006390c: 61 05 00 54              b.ne     #0x639b8
0x00063910: 28 20 9c 52              mov      w8, #0xe101
0x00063914: a8 be a0 72              movk     w8, #0x5f5, lsl #16
0x00063918: bf 02 08 6b              cmp      w21, w8
0x0006391c: e3 01 00 54              b.lo     #0x63958
0x00063920: 1f 20 03 d5              nop      
0x00063924: c1 4c d7 70              adr      x1, #0x122bf
0x00063928: 82 fd ff d0              adrp     x2, #0x15000
0x0006392c: 42 18 33 91              add      x2, x2, #0xcc6
0x00063930: 40 00 80 52              mov      w0, #2
0x00063934: e3 13 80 52              mov      w3, #0x9f
0x00063938: 52 00 00 14              b        #0x63a80                   ; -> _ZN19ListenBigSoundModel8readFromEPhj+0x1f0
0x0006393c: 1f 20 03 d5              nop      
0x00063940: e1 4b d7 70              adr      x1, #0x122bf
0x00063944: 82 fd ff d0              adrp     x2, #0x15000
0x00063948: 42 18 33 91              add      x2, x2, #0xcc6
0x0006394c: 40 00 80 52              mov      w0, #2
0x00063950: 83 13 80 52              mov      w3, #0x9c
0x00063954: 4b 00 00 14              b        #0x63a80                   ; -> _ZN19ListenBigSoundModel8readFromEPhj+0x1f0
0x00063958: f5 03 15 2a              mov      w21, w21
0x0006395c: e0 03 15 aa              mov      x0, x21
0x00063960: a4 b9 02 94              bl       #0x111ff0                  ; -> _Znam@plt
0x00063964: e1 03 15 aa              mov      x1, x21
0x00063968: e2 03 14 aa              mov      x2, x20
0x0006396c: e3 03 15 aa              mov      x3, x21
0x00063970: 60 0a 00 f9              str      x0, [x19, #0x10]
0x00063974: 2c b8 02 94              bl       #0x111a24
0x00063978: 61 82 40 a9              ldp      x1, x0, [x19, #8]
0x0006397c: b5 4e 02 94              bl       #0xf7450
0x00063980: e8 03 00 2a              mov      w8, w0
0x00063984: 20 00 80 52              mov      w0, #1
0x00063988: 1f 4d 00 71              cmp      w8, #0x13
0x0006398c: e8 06 00 54              b.hi     #0x63a68
0x00063990: e8 03 08 2a              mov      w8, w8
0x00063994: 1f 20 03 d5              nop      
0x00063998: 89 73 da 10              adr      x9, #0x18808
0x0006399c: 8a 00 00 10              adr      x10, #0x639ac
0x000639a0: 2b 69 68 38              ldrb     w11, [x9, x8]
0x000639a4: 4a 09 0b 8b              add      x10, x10, x11, lsl #2
0x000639a8: 40 01 1f d6              br       x10
0x000639ac: 9f 24 03 d5              bti      j
0x000639b0: 40 00 80 52              mov      w0, #2
0x000639b4: 2d 00 00 14              b        #0x63a68                   ; -> _ZN19ListenBigSoundModel8readFromEPhj+0x1d8
0x000639b8: 1f 20 03 d5              nop      
0x000639bc: 01 48 d7 70              adr      x1, #0x122bf
0x000639c0: 82 fd ff d0              adrp     x2, #0x15000
0x000639c4: 42 18 33 91              add      x2, x2, #0xcc6
0x000639c8: c0 00 80 52              mov      w0, #6
0x000639cc: c3 13 80 52              mov      w3, #0x9e
0x000639d0: 2c 00 00 14              b        #0x63a80                   ; -> _ZN19ListenBigSoundModel8readFromEPhj+0x1f0
0x000639d4: 9f 24 03 d5              bti      j
0x000639d8: 00 01 80 52              mov      w0, #8
0x000639dc: 23 00 00 14              b        #0x63a68                   ; -> _ZN19ListenBigSoundModel8readFromEPhj+0x1d8
0x000639e0: 9f 24 03 d5              bti      j
0x000639e4: 68 06 40 f9              ldr      x8, [x19, #8]
0x000639e8: 08 dd 40 f9              ldr      x8, [x8, #0x1b8]
0x000639ec: 48 05 00 b4              cbz      x8, #0x63a94
0x000639f0: 1f 20 03 d5              nop      
0x000639f4: 41 46 d7 70              adr      x1, #0x122bf
0x000639f8: 82 fd ff d0              adrp     x2, #0x15000
0x000639fc: 42 18 33 91              add      x2, x2, #0xcc6
0x00063a00: e0 03 1f 2a              mov      w0, wzr
0x00063a04: 23 15 80 52              mov      w3, #0xa9
0x00063a08: 1e 00 00 14              b        #0x63a80                   ; -> _ZN19ListenBigSoundModel8readFromEPhj+0x1f0
0x00063a0c: 9f 24 03 d5              bti      j
0x00063a10: c0 00 80 52              mov      w0, #6
0x00063a14: 15 00 00 14              b        #0x63a68                   ; -> _ZN19ListenBigSoundModel8readFromEPhj+0x1d8
0x00063a18: 9f 24 03 d5              bti      j
0x00063a1c: e0 02 80 52              mov      w0, #0x17
0x00063a20: 12 00 00 14              b        #0x63a68                   ; -> _ZN19ListenBigSoundModel8readFromEPhj+0x1d8
0x00063a24: 9f 24 03 d5              bti      j
0x00063a28: a0 01 80 52              mov      w0, #0xd
0x00063a2c: 0f 00 00 14              b        #0x63a68                   ; -> _ZN19ListenBigSoundModel8readFromEPhj+0x1d8
0x00063a30: 9f 24 03 d5              bti      j
0x00063a34: 60 00 80 52              mov      w0, #3
0x00063a38: 0c 00 00 14              b        #0x63a68                   ; -> _ZN19ListenBigSoundModel8readFromEPhj+0x1d8
0x00063a3c: 9f 24 03 d5              bti      j
0x00063a40: 80 00 80 52              mov      w0, #4
0x00063a44: 09 00 00 14              b        #0x63a68                   ; -> _ZN19ListenBigSoundModel8readFromEPhj+0x1d8
0x00063a48: 9f 24 03 d5              bti      j
0x00063a4c: e0 00 80 52              mov      w0, #7
0x00063a50: 06 00 00 14              b        #0x63a68                   ; -> _ZN19ListenBigSoundModel8readFromEPhj+0x1d8
0x00063a54: 9f 24 03 d5              bti      j
0x00063a58: a0 00 80 52              mov      w0, #5
0x00063a5c: 03 00 00 14              b        #0x63a68                   ; -> _ZN19ListenBigSoundModel8readFromEPhj+0x1d8
0x00063a60: 9f 24 03 d5              bti      j
0x00063a64: 60 02 80 52              mov      w0, #0x13
0x00063a68: 9f 24 03 d5              bti      j
0x00063a6c: 1f 20 03 d5              nop      
0x00063a70: 61 42 d7 70              adr      x1, #0x122bf
0x00063a74: 82 fd ff d0              adrp     x2, #0x15000
0x00063a78: 42 18 33 91              add      x2, x2, #0xcc6
0x00063a7c: c3 14 80 52              mov      w3, #0xa6
0x00063a80: f4 4f 42 a9              ldp      x20, x19, [sp, #0x20]
0x00063a84: f6 57 41 a9              ldp      x22, x21, [sp, #0x10]
0x00063a88: fd 7b c3 a8              ldp      x29, x30, [sp], #0x30
0x00063a8c: bf 23 03 d5              autiasp  
0x00063a90: d0 b9 02 14              b        #0x1121d0                  ; -> _ZN3sml15sml_debug_printE18listen_status_enumPKcS2_i@plt
0x00063a94: 1f 20 03 d5              nop      
0x00063a98: 21 41 d7 70              adr      x1, #0x122bf
0x00063a9c: 82 fd ff d0              adrp     x2, #0x15000
0x00063aa0: 42 18 33 91              add      x2, x2, #0xcc6
0x00063aa4: 40 00 80 52              mov      w0, #2
0x00063aa8: 03 15 80 52              mov      w3, #0xa8
0x00063aac: f5 ff ff 17              b        #0x63a80                   ; -> _ZN19ListenBigSoundModel8readFromEPhj+0x1f0
