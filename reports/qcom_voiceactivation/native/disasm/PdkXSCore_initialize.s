; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libqvasml.so
; target  : address 0x6abe0
; start   : 0x6abe0
; size    : 0x62c
; bytes   : 0x62c   instructions: 395
; generated: 2026-10-23T23:38:21

0x0006abe0: 3f 23 03 d5              paciasp  
0x0006abe4: ff 83 03 d1              sub      sp, sp, #0xe0
0x0006abe8: fd 7b 09 a9              stp      x29, x30, [sp, #0x90]
0x0006abec: f9 53 00 f9              str      x25, [sp, #0xa0]
0x0006abf0: f8 5f 0b a9              stp      x24, x23, [sp, #0xb0]
0x0006abf4: f6 57 0c a9              stp      x22, x21, [sp, #0xc0]
0x0006abf8: f4 4f 0d a9              stp      x20, x19, [sp, #0xd0]
0x0006abfc: fd 43 02 91              add      x29, sp, #0x90
0x0006ac00: 59 d0 3b d5              mrs      x25, tpidr_el0
0x0006ac04: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x0006ac08: a8 83 1f f8              stur     x8, [x29, #-8]
0x0006ac0c: c1 03 00 b4              cbz      x1, #0x6ac84
0x0006ac10: f6 03 02 2a              mov      w22, w2
0x0006ac14: e2 04 00 34              cbz      w2, #0x6acb0
0x0006ac18: f4 03 01 aa              mov      x20, x1
0x0006ac1c: f3 03 00 aa              mov      x19, x0
0x0006ac20: 52 9f 02 94              bl       #0x112968
0x0006ac24: f7 03 16 2a              mov      w23, w22
0x0006ac28: bf 43 1f b8              stur     wzr, [x29, #-0xc]
0x0006ac2c: e0 03 17 aa              mov      x0, x23
0x0006ac30: f0 9c 02 94              bl       #0x111ff0                  ; -> _Znam@plt
0x0006ac34: e1 03 17 aa              mov      x1, x23
0x0006ac38: e2 03 14 aa              mov      x2, x20
0x0006ac3c: e3 03 17 aa              mov      x3, x23
0x0006ac40: f5 03 00 aa              mov      x21, x0
0x0006ac44: 78 9b 02 94              bl       #0x111a24
0x0006ac48: 60 42 00 91              add      x0, x19, #0x10
0x0006ac4c: e9 33 01 94              bl       #0xb7bf0
0x0006ac50: 58 9f 02 94              bl       #0x1129b0                  ; -> _ZN3sml21convertPdkXsErrorCodeEi@plt
0x0006ac54: 20 05 00 34              cbz      w0, #0x6acf8
0x0006ac58: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x0006ac5c: a9 83 5f f8              ldur     x9, [x29, #-8]
0x0006ac60: 1f 01 09 eb              cmp      x8, x9
0x0006ac64: 21 2d 00 54              b.ne     #0x6b208
0x0006ac68: 1f 20 03 d5              nop      
0x0006ac6c: e1 b8 d3 50              adr      x1, #0x1238a
0x0006ac70: 62 fd ff 90              adrp     x2, #0x16000
0x0006ac74: 42 e4 12 91              add      x2, x2, #0x4b9
0x0006ac78: 20 00 80 52              mov      w0, #1
0x0006ac7c: a3 0d 80 52              mov      w3, #0x6d
0x0006ac80: 16 00 00 14              b        #0x6acd8                   ; -> _ZN9PdkXSCore10initializeEPhj+0xf8
0x0006ac84: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x0006ac88: a9 83 5f f8              ldur     x9, [x29, #-8]
0x0006ac8c: 1f 01 09 eb              cmp      x8, x9
0x0006ac90: c1 2b 00 54              b.ne     #0x6b208
0x0006ac94: 1f 20 03 d5              nop      
0x0006ac98: 81 b7 d3 50              adr      x1, #0x1238a
0x0006ac9c: 62 fd ff 90              adrp     x2, #0x16000
0x0006aca0: 42 e4 12 91              add      x2, x2, #0x4b9
0x0006aca4: 40 00 80 52              mov      w0, #2
0x0006aca8: e3 0b 80 52              mov      w3, #0x5f
0x0006acac: 0b 00 00 14              b        #0x6acd8                   ; -> _ZN9PdkXSCore10initializeEPhj+0xf8
0x0006acb0: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x0006acb4: a9 83 5f f8              ldur     x9, [x29, #-8]
0x0006acb8: 1f 01 09 eb              cmp      x8, x9
0x0006acbc: 61 2a 00 54              b.ne     #0x6b208
0x0006acc0: 1f 20 03 d5              nop      
0x0006acc4: 21 b6 d3 50              adr      x1, #0x1238a
0x0006acc8: 62 fd ff 90              adrp     x2, #0x16000
0x0006accc: 42 e4 12 91              add      x2, x2, #0x4b9
0x0006acd0: 40 00 80 52              mov      w0, #2
0x0006acd4: 03 0c 80 52              mov      w3, #0x60
0x0006acd8: f4 4f 4d a9              ldp      x20, x19, [sp, #0xd0]
0x0006acdc: f6 57 4c a9              ldp      x22, x21, [sp, #0xc0]
0x0006ace0: f8 5f 4b a9              ldp      x24, x23, [sp, #0xb0]
0x0006ace4: fd 7b 49 a9              ldp      x29, x30, [sp, #0x90]
0x0006ace8: f9 53 40 f9              ldr      x25, [sp, #0xa0]
0x0006acec: ff 83 03 91              add      sp, sp, #0xe0
0x0006acf0: bf 23 03 d5              autiasp  
0x0006acf4: 37 9d 02 14              b        #0x1121d0                  ; -> _ZN3sml15sml_debug_printE18listen_status_enumPKcS2_i@plt
0x0006acf8: 74 0e 40 f9              ldr      x20, [x19, #0x18]
0x0006acfc: 94 02 00 b4              cbz      x20, #0x6ad4c
0x0006ad00: 28 fd ff b0              adrp     x8, #0xf000
0x0006ad04: a2 83 00 d1              sub      x2, x29, #0x20
0x0006ad08: a4 33 00 d1              sub      x4, x29, #0xc
0x0006ad0c: e0 03 14 aa              mov      x0, x20
0x0006ad10: 61 02 80 52              mov      w1, #0x13
0x0006ad14: 03 02 80 52              mov      w3, #0x10
0x0006ad18: 00 85 45 fd              ldr      d0, [x8, #0xb08]
0x0006ad1c: 68 aa 40 b9              ldr      w8, [x19, #0xa8]
0x0006ad20: a0 03 1e fc              stur     d0, [x29, #-0x20]
0x0006ad24: a8 7f 3d 29              stp      w8, wzr, [x29, #-0x18]
0x0006ad28: 0e 34 01 94              bl       #0xb7d60
0x0006ad2c: 21 9f 02 94              bl       #0x1129b0                  ; -> _ZN3sml21convertPdkXsErrorCodeEi@plt
0x0006ad30: 40 02 00 34              cbz      w0, #0x6ad78
0x0006ad34: 1f 20 03 d5              nop      
0x0006ad38: 81 b2 d3 50              adr      x1, #0x1238a
0x0006ad3c: 62 fd ff 90              adrp     x2, #0x16000
0x0006ad40: 42 e4 12 91              add      x2, x2, #0x4b9
0x0006ad44: e3 0f 80 52              mov      w3, #0x7f
0x0006ad48: 06 01 00 14              b        #0x6b160                   ; -> _ZN9PdkXSCore10initializeEPhj+0x580
0x0006ad4c: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x0006ad50: a9 83 5f f8              ldur     x9, [x29, #-8]
0x0006ad54: 1f 01 09 eb              cmp      x8, x9
0x0006ad58: 81 25 00 54              b.ne     #0x6b208
0x0006ad5c: 1f 20 03 d5              nop      
0x0006ad60: 41 b1 d3 50              adr      x1, #0x1238a
0x0006ad64: 62 fd ff 90              adrp     x2, #0x16000
0x0006ad68: 42 e4 12 91              add      x2, x2, #0x4b9
0x0006ad6c: 20 00 80 52              mov      w0, #1
0x0006ad70: e3 0d 80 52              mov      w3, #0x6f
0x0006ad74: d9 ff ff 17              b        #0x6acd8                   ; -> _ZN9PdkXSCore10initializeEPhj+0xf8
0x0006ad78: b7 c3 9e b8              ldursw   x23, [x29, #-0x14]
0x0006ad7c: f8 03 13 aa              mov      x24, x19
0x0006ad80: e0 03 17 aa              mov      x0, x23
0x0006ad84: 17 8f 02 b8              str      w23, [x24, #0x28]!
0x0006ad88: 9a 9c 02 94              bl       #0x111ff0                  ; -> _Znam@plt
0x0006ad8c: e1 03 1f 2a              mov      w1, wzr
0x0006ad90: e2 03 17 aa              mov      x2, x23
0x0006ad94: 00 07 00 f9              str      x0, [x24, #8]
0x0006ad98: a0 9b 02 94              bl       #0x111c18
0x0006ad9c: e0 03 14 aa              mov      x0, x20
0x0006ada0: 61 02 80 52              mov      w1, #0x13
0x0006ada4: e2 03 18 aa              mov      x2, x24
0x0006ada8: 03 02 80 52              mov      w3, #0x10
0x0006adac: eb 33 01 94              bl       #0xb7d58
0x0006adb0: 00 9f 02 94              bl       #0x1129b0                  ; -> _ZN3sml21convertPdkXsErrorCodeEi@plt
0x0006adb4: e0 00 00 34              cbz      w0, #0x6add0
0x0006adb8: 1f 20 03 d5              nop      
0x0006adbc: 61 ae d3 50              adr      x1, #0x1238a
0x0006adc0: 62 fd ff 90              adrp     x2, #0x16000
0x0006adc4: 42 e4 12 91              add      x2, x2, #0x4b9
0x0006adc8: e3 10 80 52              mov      w3, #0x87
0x0006adcc: e5 00 00 14              b        #0x6b160                   ; -> _ZN9PdkXSCore10initializeEPhj+0x580
0x0006add0: 68 0a 40 b9              ldr      w8, [x19, #8]
0x0006add4: a2 a3 00 d1              sub      x2, x29, #0x28
0x0006add8: e0 03 14 aa              mov      x0, x20
0x0006addc: 81 02 80 52              mov      w1, #0x14
0x0006ade0: 83 00 80 52              mov      w3, #4
0x0006ade4: a8 83 1d b8              stur     w8, [x29, #-0x28]
0x0006ade8: dc 33 01 94              bl       #0xb7d58
0x0006adec: f1 9e 02 94              bl       #0x1129b0                  ; -> _ZN3sml21convertPdkXsErrorCodeEi@plt
0x0006adf0: e0 00 00 34              cbz      w0, #0x6ae0c
0x0006adf4: 1f 20 03 d5              nop      
0x0006adf8: 81 ac d3 50              adr      x1, #0x1238a
0x0006adfc: 62 fd ff 90              adrp     x2, #0x16000
0x0006ae00: 42 e4 12 91              add      x2, x2, #0x4b9
0x0006ae04: c3 11 80 52              mov      w3, #0x8e
0x0006ae08: d6 00 00 14              b        #0x6b160                   ; -> _ZN9PdkXSCore10initializeEPhj+0x580
0x0006ae0c: 68 0a 40 b9              ldr      w8, [x19, #8]
0x0006ae10: f7 03 13 aa              mov      x23, x19
0x0006ae14: a4 33 00 d1              sub      x4, x29, #0xc
0x0006ae18: e0 03 14 aa              mov      x0, x20
0x0006ae1c: 01 02 80 52              mov      w1, #0x10
0x0006ae20: 03 02 80 52              mov      w3, #0x10
0x0006ae24: e8 8e 08 b8              str      w8, [x23, #0x88]!
0x0006ae28: e2 03 17 aa              mov      x2, x23
0x0006ae2c: cd 33 01 94              bl       #0xb7d60
0x0006ae30: e0 9e 02 94              bl       #0x1129b0                  ; -> _ZN3sml21convertPdkXsErrorCodeEi@plt
0x0006ae34: e0 00 00 34              cbz      w0, #0x6ae50
0x0006ae38: 1f 20 03 d5              nop      
0x0006ae3c: 61 aa d3 50              adr      x1, #0x1238a
0x0006ae40: 62 fd ff 90              adrp     x2, #0x16000
0x0006ae44: 42 e4 12 91              add      x2, x2, #0x4b9
0x0006ae48: e3 12 80 52              mov      w3, #0x97
0x0006ae4c: c5 00 00 14              b        #0x6b160                   ; -> _ZN9PdkXSCore10initializeEPhj+0x580
0x0006ae50: 78 8e 80 b9              ldrsw    x24, [x19, #0x8c]
0x0006ae54: e0 03 18 aa              mov      x0, x24
0x0006ae58: 66 9c 02 94              bl       #0x111ff0                  ; -> _Znam@plt
0x0006ae5c: e1 03 1f 2a              mov      w1, wzr
0x0006ae60: e2 03 18 aa              mov      x2, x24
0x0006ae64: 60 4a 00 f9              str      x0, [x19, #0x90]
0x0006ae68: 6c 9b 02 94              bl       #0x111c18
0x0006ae6c: e0 03 14 aa              mov      x0, x20
0x0006ae70: 01 02 80 52              mov      w1, #0x10
0x0006ae74: e2 03 17 aa              mov      x2, x23
0x0006ae78: 03 02 80 52              mov      w3, #0x10
0x0006ae7c: b7 33 01 94              bl       #0xb7d58
0x0006ae80: cc 9e 02 94              bl       #0x1129b0                  ; -> _ZN3sml21convertPdkXsErrorCodeEi@plt
0x0006ae84: e0 00 00 34              cbz      w0, #0x6aea0
0x0006ae88: 1f 20 03 d5              nop      
0x0006ae8c: e1 a7 d3 50              adr      x1, #0x1238a
0x0006ae90: 62 fd ff 90              adrp     x2, #0x16000
0x0006ae94: 42 e4 12 91              add      x2, x2, #0x4b9
0x0006ae98: e3 13 80 52              mov      w3, #0x9f
0x0006ae9c: b1 00 00 14              b        #0x6b160                   ; -> _ZN9PdkXSCore10initializeEPhj+0x580
0x0006aea0: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x0006aea4: 68 0a 40 b9              ldr      w8, [x19, #8]
0x0006aea8: e2 03 01 91              add      x2, sp, #0x40
0x0006aeac: a4 33 00 d1              sub      x4, x29, #0xc
0x0006aeb0: e0 03 14 aa              mov      x0, x20
0x0006aeb4: 21 02 80 52              mov      w1, #0x11
0x0006aeb8: 03 05 80 52              mov      w3, #0x28
0x0006aebc: f5 33 00 f9              str      x21, [sp, #0x60]
0x0006aec0: e0 03 02 ad              stp      q0, q0, [sp, #0x40]
0x0006aec4: e8 43 00 b9              str      w8, [sp, #0x40]
0x0006aec8: f6 5b 00 b9              str      w22, [sp, #0x58]
0x0006aecc: a5 33 01 94              bl       #0xb7d60
0x0006aed0: b8 9e 02 94              bl       #0x1129b0                  ; -> _ZN3sml21convertPdkXsErrorCodeEi@plt
0x0006aed4: e0 00 00 34              cbz      w0, #0x6aef0
0x0006aed8: 1f 20 03 d5              nop      
0x0006aedc: 61 a5 d3 50              adr      x1, #0x1238a
0x0006aee0: 62 fd ff 90              adrp     x2, #0x16000
0x0006aee4: 42 e4 12 91              add      x2, x2, #0x4b9
0x0006aee8: 03 15 80 52              mov      w3, #0xa8
0x0006aeec: 9d 00 00 14              b        #0x6b160                   ; -> _ZN9PdkXSCore10initializeEPhj+0x580
0x0006aef0: e0 c3 44 fc              ldur     d0, [sp, #0x4c]
0x0006aef4: f6 03 13 aa              mov      x22, x19
0x0006aef8: 68 0a 40 b9              ldr      w8, [x19, #8]
0x0006aefc: 29 00 80 52              mov      w9, #1
0x0006af00: 17 00 26 1e              fmov     w23, s0
0x0006af04: c8 8e 03 b8              str      w8, [x22, #0x38]!
0x0006af08: c0 42 00 fc              stur     d0, [x22, #4]
0x0006af0c: e0 03 17 aa              mov      x0, x23
0x0006af10: c9 32 00 79              strh     w9, [x22, #0x18]
0x0006af14: 37 9c 02 94              bl       #0x111ff0                  ; -> _Znam@plt
0x0006af18: e1 03 1f 2a              mov      w1, wzr
0x0006af1c: e2 03 17 aa              mov      x2, x23
0x0006af20: f8 03 00 aa              mov      x24, x0
0x0006af24: c0 12 00 f9              str      x0, [x22, #0x20]
0x0006af28: 3c 9b 02 94              bl       #0x111c18
0x0006af2c: e8 47 40 b9              ldr      w8, [sp, #0x44]
0x0006af30: e0 03 18 aa              mov      x0, x24
0x0006af34: e1 03 17 aa              mov      x1, x23
0x0006af38: e3 03 17 aa              mov      x3, x23
0x0006af3c: a2 02 08 8b              add      x2, x21, x8
0x0006af40: b9 9a 02 94              bl       #0x111a24
0x0006af44: d7 0a 40 b9              ldr      w23, [x22, #8]
0x0006af48: e0 03 17 aa              mov      x0, x23
0x0006af4c: 29 9c 02 94              bl       #0x111ff0                  ; -> _Znam@plt
0x0006af50: e1 03 1f 2a              mov      w1, wzr
0x0006af54: e2 03 17 aa              mov      x2, x23
0x0006af58: f8 03 00 aa              mov      x24, x0
0x0006af5c: c0 16 00 f9              str      x0, [x22, #0x28]
0x0006af60: 2e 9b 02 94              bl       #0x111c18
0x0006af64: e8 4b 40 b9              ldr      w8, [sp, #0x48]
0x0006af68: e0 03 18 aa              mov      x0, x24
0x0006af6c: e1 03 17 aa              mov      x1, x23
0x0006af70: e3 03 17 aa              mov      x3, x23
0x0006af74: a2 02 08 8b              add      x2, x21, x8
0x0006af78: ab 9a 02 94              bl       #0x111a24
0x0006af7c: e0 03 15 aa              mov      x0, x21
0x0006af80: 04 9c 02 94              bl       #0x111f90                  ; -> _ZdaPv@plt
0x0006af84: 68 0a 40 b9              ldr      w8, [x19, #8]
0x0006af88: e2 83 00 91              add      x2, sp, #0x20
0x0006af8c: c0 42 40 fc              ldur     d0, [x22, #4]
0x0006af90: a4 33 00 d1              sub      x4, x29, #0xc
0x0006af94: c1 0a c0 3d              ldr      q1, [x22, #0x20]
0x0006af98: e0 03 14 aa              mov      x0, x20
0x0006af9c: 41 02 80 52              mov      w1, #0x12
0x0006afa0: 03 04 80 52              mov      w3, #0x20
0x0006afa4: ff 13 00 f9              str      xzr, [sp, #0x20]
0x0006afa8: e8 23 00 b9              str      w8, [sp, #0x20]
0x0006afac: e0 17 00 fd              str      d0, [sp, #0x28]
0x0006afb0: e1 0f 80 3d              str      q1, [sp, #0x30]
0x0006afb4: 6b 33 01 94              bl       #0xb7d60
0x0006afb8: 7e 9e 02 94              bl       #0x1129b0                  ; -> _ZN3sml21convertPdkXsErrorCodeEi@plt
0x0006afbc: e0 00 00 34              cbz      w0, #0x6afd8
0x0006afc0: 1f 20 03 d5              nop      
0x0006afc4: 21 9e d3 50              adr      x1, #0x1238a
0x0006afc8: 62 fd ff 90              adrp     x2, #0x16000
0x0006afcc: 42 e4 12 91              add      x2, x2, #0x4b9
0x0006afd0: 83 19 80 52              mov      w3, #0xcc
0x0006afd4: 63 00 00 14              b        #0x6b160                   ; -> _ZN9PdkXSCore10initializeEPhj+0x580
0x0006afd8: f5 27 40 b9              ldr      w21, [sp, #0x24]
0x0006afdc: e0 03 15 aa              mov      x0, x21
0x0006afe0: 04 9c 02 94              bl       #0x111ff0                  ; -> _Znam@plt
0x0006afe4: e1 03 1f 2a              mov      w1, wzr
0x0006afe8: e2 03 15 aa              mov      x2, x21
0x0006afec: 75 46 00 b9              str      w21, [x19, #0x44]
0x0006aff0: 60 36 00 f9              str      x0, [x19, #0x68]
0x0006aff4: 09 9b 02 94              bl       #0x111c18
0x0006aff8: e0 03 14 aa              mov      x0, x20
0x0006affc: c1 02 80 52              mov      w1, #0x16
0x0006b000: e2 03 16 aa              mov      x2, x22
0x0006b004: 03 07 80 52              mov      w3, #0x38
0x0006b008: 54 33 01 94              bl       #0xb7d58
0x0006b00c: 69 9e 02 94              bl       #0x1129b0                  ; -> _ZN3sml21convertPdkXsErrorCodeEi@plt
0x0006b010: e0 00 00 34              cbz      w0, #0x6b02c
0x0006b014: 1f 20 03 d5              nop      
0x0006b018: 81 9b d3 50              adr      x1, #0x1238a
0x0006b01c: 42 fd ff f0              adrp     x2, #0x16000
0x0006b020: 42 e4 12 91              add      x2, x2, #0x4b9
0x0006b024: c3 1a 80 52              mov      w3, #0xd6
0x0006b028: 4e 00 00 14              b        #0x6b160                   ; -> _ZN9PdkXSCore10initializeEPhj+0x580
0x0006b02c: 75 c2 01 91              add      x21, x19, #0x70
0x0006b030: a4 33 00 d1              sub      x4, x29, #0xc
0x0006b034: e0 03 14 aa              mov      x0, x20
0x0006b038: c1 00 80 52              mov      w1, #6
0x0006b03c: e2 03 15 aa              mov      x2, x21
0x0006b040: 03 02 80 52              mov      w3, #0x10
0x0006b044: 47 33 01 94              bl       #0xb7d60
0x0006b048: 5a 9e 02 94              bl       #0x1129b0                  ; -> _ZN3sml21convertPdkXsErrorCodeEi@plt
0x0006b04c: e0 00 00 34              cbz      w0, #0x6b068
0x0006b050: 1f 20 03 d5              nop      
0x0006b054: a1 99 d3 50              adr      x1, #0x1238a
0x0006b058: 42 fd ff f0              adrp     x2, #0x16000
0x0006b05c: 42 e4 12 91              add      x2, x2, #0x4b9
0x0006b060: 63 1b 80 52              mov      w3, #0xdb
0x0006b064: 3f 00 00 14              b        #0x6b160                   ; -> _ZN9PdkXSCore10initializeEPhj+0x580
0x0006b068: 76 72 40 b9              ldr      w22, [x19, #0x70]
0x0006b06c: e0 03 16 aa              mov      x0, x22
0x0006b070: e0 9b 02 94              bl       #0x111ff0                  ; -> _Znam@plt
0x0006b074: e1 03 1f 2a              mov      w1, wzr
0x0006b078: e2 03 16 aa              mov      x2, x22
0x0006b07c: 60 3e 00 f9              str      x0, [x19, #0x78]
0x0006b080: e6 9a 02 94              bl       #0x111c18
0x0006b084: e0 03 14 aa              mov      x0, x20
0x0006b088: c1 00 80 52              mov      w1, #6
0x0006b08c: e2 03 15 aa              mov      x2, x21
0x0006b090: 03 02 80 52              mov      w3, #0x10
0x0006b094: 31 33 01 94              bl       #0xb7d58
0x0006b098: 46 9e 02 94              bl       #0x1129b0                  ; -> _ZN3sml21convertPdkXsErrorCodeEi@plt
0x0006b09c: e0 00 00 34              cbz      w0, #0x6b0b8
0x0006b0a0: 1f 20 03 d5              nop      
0x0006b0a4: 21 97 d3 50              adr      x1, #0x1238a
0x0006b0a8: 42 fd ff f0              adrp     x2, #0x16000
0x0006b0ac: 42 e4 12 91              add      x2, x2, #0x4b9
0x0006b0b0: 83 1c 80 52              mov      w3, #0xe4
0x0006b0b4: 2b 00 00 14              b        #0x6b160                   ; -> _ZN9PdkXSCore10initializeEPhj+0x580
0x0006b0b8: 68 0a 40 b9              ldr      w8, [x19, #8]
0x0006b0bc: e2 43 00 91              add      x2, sp, #0x10
0x0006b0c0: a4 33 00 d1              sub      x4, x29, #0xc
0x0006b0c4: e0 03 14 aa              mov      x0, x20
0x0006b0c8: 21 00 80 52              mov      w1, #1
0x0006b0cc: 03 02 80 52              mov      w3, #0x10
0x0006b0d0: ff 7f 01 a9              stp      xzr, xzr, [sp, #0x10]
0x0006b0d4: e8 13 00 b9              str      w8, [sp, #0x10]
0x0006b0d8: 22 33 01 94              bl       #0xb7d60
0x0006b0dc: 35 9e 02 94              bl       #0x1129b0                  ; -> _ZN3sml21convertPdkXsErrorCodeEi@plt
0x0006b0e0: e0 00 00 34              cbz      w0, #0x6b0fc
0x0006b0e4: 1f 20 03 d5              nop      
0x0006b0e8: 01 95 d3 50              adr      x1, #0x1238a
0x0006b0ec: 42 fd ff f0              adrp     x2, #0x16000
0x0006b0f0: 42 e4 12 91              add      x2, x2, #0x4b9
0x0006b0f4: 43 1d 80 52              mov      w3, #0xea
0x0006b0f8: 1a 00 00 14              b        #0x6b160                   ; -> _ZN9PdkXSCore10initializeEPhj+0x580
0x0006b0fc: e0 03 13 aa              mov      x0, x19
0x0006b100: 82 02 80 52              mov      w2, #0x14
0x0006b104: 31 9e 02 94              bl       #0x1129c8
0x0006b108: e0 00 00 34              cbz      w0, #0x6b124
0x0006b10c: 1f 20 03 d5              nop      
0x0006b110: c1 93 d3 50              adr      x1, #0x1238a
0x0006b114: 42 fd ff f0              adrp     x2, #0x16000
0x0006b118: 42 e4 12 91              add      x2, x2, #0x4b9
0x0006b11c: 03 1e 80 52              mov      w3, #0xf0
0x0006b120: 10 00 00 14              b        #0x6b160                   ; -> _ZN9PdkXSCore10initializeEPhj+0x580
0x0006b124: e2 03 00 91              mov      x2, sp
0x0006b128: a4 33 00 d1              sub      x4, x29, #0xc
0x0006b12c: e0 03 14 aa              mov      x0, x20
0x0006b130: c1 01 80 52              mov      w1, #0xe
0x0006b134: 83 01 80 52              mov      w3, #0xc
0x0006b138: ff 0b 00 b9              str      wzr, [sp, #8]
0x0006b13c: ff 03 00 f9              str      xzr, [sp]
0x0006b140: 08 33 01 94              bl       #0xb7d60
0x0006b144: 1b 9e 02 94              bl       #0x1129b0                  ; -> _ZN3sml21convertPdkXsErrorCodeEi@plt
0x0006b148: 60 02 00 34              cbz      w0, #0x6b194
0x0006b14c: 1f 20 03 d5              nop      
0x0006b150: c1 91 d3 50              adr      x1, #0x1238a
0x0006b154: 42 fd ff f0              adrp     x2, #0x16000
0x0006b158: 42 e4 12 91              add      x2, x2, #0x4b9
0x0006b15c: e3 1e 80 52              mov      w3, #0xf7
0x0006b160: 1c 9c 02 94              bl       #0x1121d0                  ; -> _ZN3sml15sml_debug_printE18listen_status_enumPKcS2_i@plt
0x0006b164: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x0006b168: a9 83 5f f8              ldur     x9, [x29, #-8]
0x0006b16c: 1f 01 09 eb              cmp      x8, x9
0x0006b170: c1 04 00 54              b.ne     #0x6b208
0x0006b174: f4 4f 4d a9              ldp      x20, x19, [sp, #0xd0]
0x0006b178: f6 57 4c a9              ldp      x22, x21, [sp, #0xc0]
0x0006b17c: f8 5f 4b a9              ldp      x24, x23, [sp, #0xb0]
0x0006b180: fd 7b 49 a9              ldp      x29, x30, [sp, #0x90]
0x0006b184: f9 53 40 f9              ldr      x25, [sp, #0xa0]
0x0006b188: ff 83 03 91              add      sp, sp, #0xe0
0x0006b18c: bf 23 03 d5              autiasp  
0x0006b190: c0 03 5f d6              ret      
0x0006b194: f6 03 40 b9              ldr      w22, [sp]
0x0006b198: e0 03 16 aa              mov      x0, x22
0x0006b19c: 4b 9a 02 94              bl       #0x111ac8
0x0006b1a0: 60 42 00 f9              str      x0, [x19, #0x80]
0x0006b1a4: 40 02 00 b4              cbz      x0, #0x6b1ec
0x0006b1a8: e1 03 1f 2a              mov      w1, wzr
0x0006b1ac: e2 03 16 aa              mov      x2, x22
0x0006b1b0: f5 03 00 aa              mov      x21, x0
0x0006b1b4: 99 9a 02 94              bl       #0x111c18
0x0006b1b8: e0 03 14 aa              mov      x0, x20
0x0006b1bc: c1 01 80 52              mov      w1, #0xe
0x0006b1c0: e2 03 15 aa              mov      x2, x21
0x0006b1c4: 03 01 80 52              mov      w3, #8
0x0006b1c8: e4 32 01 94              bl       #0xb7d58
0x0006b1cc: f9 9d 02 94              bl       #0x1129b0                  ; -> _ZN3sml21convertPdkXsErrorCodeEi@plt
0x0006b1d0: a0 fc ff 34              cbz      w0, #0x6b164
0x0006b1d4: 1f 20 03 d5              nop      
0x0006b1d8: 81 8d d3 50              adr      x1, #0x1238a
0x0006b1dc: 42 fd ff f0              adrp     x2, #0x16000
0x0006b1e0: 42 e4 12 91              add      x2, x2, #0x4b9
0x0006b1e4: 43 20 80 52              mov      w3, #0x102
0x0006b1e8: de ff ff 17              b        #0x6b160                   ; -> _ZN9PdkXSCore10initializeEPhj+0x580
0x0006b1ec: 1f 20 03 d5              nop      
0x0006b1f0: c1 8c d3 50              adr      x1, #0x1238a
0x0006b1f4: 42 fd ff f0              adrp     x2, #0x16000
0x0006b1f8: 42 e4 12 91              add      x2, x2, #0x4b9
0x0006b1fc: 20 00 80 52              mov      w0, #1
0x0006b200: 83 1f 80 52              mov      w3, #0xfc
0x0006b204: d7 ff ff 17              b        #0x6b160                   ; -> _ZN9PdkXSCore10initializeEPhj+0x580
0x0006b208: 42 9a 02 94              bl       #0x111b10                  ; -> __stack_chk_fail@plt
