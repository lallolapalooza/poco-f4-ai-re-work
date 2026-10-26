; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libqvasml.so
; target  : address 0x6c054
; start   : 0x6c054
; size    : 0x114
; bytes   : 0x114   instructions: 69
; generated: 2026-10-23T23:24:18

0x0006c054: 3f 23 03 d5              paciasp  
0x0006c058: fd 7b bd a9              stp      x29, x30, [sp, #-0x30]!
0x0006c05c: f5 0b 00 f9              str      x21, [sp, #0x10]
0x0006c060: f4 4f 02 a9              stp      x20, x19, [sp, #0x20]
0x0006c064: fd 03 00 91              mov      x29, sp
0x0006c068: f5 03 02 2a              mov      w21, w2
0x0006c06c: f3 03 00 aa              mov      x19, x0
0x0006c070: 00 08 40 f9              ldr      x0, [x0, #0x10]
0x0006c074: f4 03 01 aa              mov      x20, x1
0x0006c078: 40 00 00 b4              cbz      x0, #0x6c080
0x0006c07c: c5 97 02 94              bl       #0x111f90                  ; -> _ZdaPv@plt
0x0006c080: 60 06 40 f9              ldr      x0, [x19, #8]
0x0006c084: 7f 0a 00 f9              str      xzr, [x19, #0x10]
0x0006c088: 40 00 00 b4              cbz      x0, #0x6c090
0x0006c08c: db 55 02 94              bl       #0x1017f8
0x0006c090: a0 00 80 52              mov      w0, #5
0x0006c094: 1f 45 02 94              bl       #0xfd510
0x0006c098: 60 06 00 f9              str      x0, [x19, #8]
0x0006c09c: e0 00 00 b4              cbz      x0, #0x6c0b8
0x0006c0a0: 08 48 88 52              mov      w8, #0x4240
0x0006c0a4: e8 01 a0 72              movk     w8, #0xf, lsl #16
0x0006c0a8: bf 02 08 6b              cmp      w21, w8
0x0006c0ac: 49 01 00 54              b.ls     #0x6c0d4
0x0006c0b0: 40 00 80 52              mov      w0, #2
0x0006c0b4: 1d 00 00 14              b        #0x6c128                   ; -> _ZN16ListenSoundModel8readFromEPhj+0xd4
0x0006c0b8: 1f 20 03 d5              nop      
0x0006c0bc: 01 6e d4 70              adr      x1, #0x14e7f
0x0006c0c0: 42 fd ff b0              adrp     x2, #0x15000
0x0006c0c4: 42 18 33 91              add      x2, x2, #0xcc6
0x0006c0c8: 40 00 80 52              mov      w0, #2
0x0006c0cc: 43 10 80 52              mov      w3, #0x82
0x0006c0d0: 21 00 00 14              b        #0x6c154                   ; -> _ZN16ListenSoundModel8readFromEPhj+0x100
0x0006c0d4: f5 03 15 2a              mov      w21, w21
0x0006c0d8: e0 03 15 aa              mov      x0, x21
0x0006c0dc: c5 97 02 94              bl       #0x111ff0                  ; -> _Znam@plt
0x0006c0e0: e1 03 15 aa              mov      x1, x21
0x0006c0e4: e2 03 14 aa              mov      x2, x20
0x0006c0e8: e3 03 15 aa              mov      x3, x21
0x0006c0ec: 60 0a 00 f9              str      x0, [x19, #0x10]
0x0006c0f0: 4d 96 02 94              bl       #0x111a24
0x0006c0f4: 61 82 40 a9              ldp      x1, x0, [x19, #8]
0x0006c0f8: b7 2c 02 94              bl       #0xf73d4
0x0006c0fc: 68 06 40 f9              ldr      x8, [x19, #8]
0x0006c100: 08 09 40 b9              ldr      w8, [x8, #8]
0x0006c104: 1f 15 00 71              cmp      w8, #5
0x0006c108: e1 00 00 54              b.ne     #0x6c124
0x0006c10c: 1f 4c 00 71              cmp      w0, #0x13
0x0006c110: 68 01 00 54              b.hi     #0x6c13c
0x0006c114: 1f 20 03 d5              nop      
0x0006c118: 68 39 d6 10              adr      x8, #0x18844
0x0006c11c: 00 d9 60 b8              ldr      w0, [x8, w0, sxtw #2]
0x0006c120: 08 00 00 14              b        #0x6c140                   ; -> _ZN16ListenSoundModel8readFromEPhj+0xec
0x0006c124: c0 00 80 52              mov      w0, #6
0x0006c128: f4 4f 42 a9              ldp      x20, x19, [sp, #0x20]
0x0006c12c: f5 0b 40 f9              ldr      x21, [sp, #0x10]
0x0006c130: fd 7b c3 a8              ldp      x29, x30, [sp], #0x30
0x0006c134: bf 23 03 d5              autiasp  
0x0006c138: c0 03 5f d6              ret      
0x0006c13c: 20 00 80 52              mov      w0, #1
0x0006c140: 1f 20 03 d5              nop      
0x0006c144: c1 69 d4 70              adr      x1, #0x14e7f
0x0006c148: 42 fd ff b0              adrp     x2, #0x15000
0x0006c14c: 42 18 33 91              add      x2, x2, #0xcc6
0x0006c150: e3 11 80 52              mov      w3, #0x8f
0x0006c154: f4 4f 42 a9              ldp      x20, x19, [sp, #0x20]
0x0006c158: f5 0b 40 f9              ldr      x21, [sp, #0x10]
0x0006c15c: fd 7b c3 a8              ldp      x29, x30, [sp], #0x30
0x0006c160: bf 23 03 d5              autiasp  
0x0006c164: 1b 98 02 14              b        #0x1121d0                  ; -> _ZN3sml15sml_debug_printE18listen_status_enumPKcS2_i@plt
