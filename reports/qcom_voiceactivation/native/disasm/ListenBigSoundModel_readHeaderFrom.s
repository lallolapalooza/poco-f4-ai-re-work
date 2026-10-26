; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libqvasml.so
; target  : address 0x63ab0
; start   : 0x63ab0
; size    : 0x1ac
; bytes   : 0x1ac   instructions: 107
; generated: 2026-10-23T23:24:16

0x00063ab0: 3f 23 03 d5              paciasp  
0x00063ab4: fd 7b bd a9              stp      x29, x30, [sp, #-0x30]!
0x00063ab8: f5 0b 00 f9              str      x21, [sp, #0x10]
0x00063abc: f4 4f 02 a9              stp      x20, x19, [sp, #0x20]
0x00063ac0: fd 03 00 91              mov      x29, sp
0x00063ac4: f4 03 00 aa              mov      x20, x0
0x00063ac8: 00 04 40 f9              ldr      x0, [x0, #8]
0x00063acc: f3 03 01 aa              mov      x19, x1
0x00063ad0: 88 0a 40 f9              ldr      x8, [x20, #0x10]
0x00063ad4: 15 08 40 b9              ldr      w21, [x0, #8]
0x00063ad8: e8 00 00 b4              cbz      x8, #0x63af4
0x00063adc: e0 03 08 aa              mov      x0, x8
0x00063ae0: 2c b9 02 94              bl       #0x111f90                  ; -> _ZdaPv@plt
0x00063ae4: 80 06 40 f9              ldr      x0, [x20, #8]
0x00063ae8: 9f 0a 00 f9              str      xzr, [x20, #0x10]
0x00063aec: 60 00 00 b5              cbnz     x0, #0x63af8
0x00063af0: 03 00 00 14              b        #0x63afc                   ; -> _ZN19ListenBigSoundModel14readHeaderFromEPKh+0x4c
0x00063af4: 9f 0a 00 f9              str      xzr, [x20, #0x10]
0x00063af8: 9a 77 02 94              bl       #0x101960
0x00063afc: e0 03 15 2a              mov      w0, w21
0x00063b00: 92 66 02 94              bl       #0xfd548
0x00063b04: 80 fe 00 a9              stp      x0, xzr, [x20, #8]
0x00063b08: 40 03 00 b4              cbz      x0, #0x63b70
0x00063b0c: 08 08 40 b9              ldr      w8, [x0, #8]
0x00063b10: e1 03 00 aa              mov      x1, x0
0x00063b14: 09 75 16 12              and      w9, w8, #0xfffffcff
0x00063b18: 3f 01 10 71              cmp      w9, #0x400
0x00063b1c: a0 00 00 54              b.eq     #0x63b30
0x00063b20: 1f 01 0c 71              cmp      w8, #0x300
0x00063b24: 60 00 00 54              b.eq     #0x63b30
0x00063b28: 1f 01 20 71              cmp      w8, #0x800
0x00063b2c: 01 03 00 54              b.ne     #0x63b8c
0x00063b30: e0 03 13 aa              mov      x0, x19
0x00063b34: 47 4e 02 94              bl       #0xf7450
0x00063b38: e8 03 00 2a              mov      w8, w0
0x00063b3c: 20 00 80 52              mov      w0, #1
0x00063b40: 1f 4d 00 71              cmp      w8, #0x13
0x00063b44: 68 07 00 54              b.hi     #0x63c30
0x00063b48: e8 03 08 2a              mov      w8, w8
0x00063b4c: 1f 20 03 d5              nop      
0x00063b50: 69 66 da 10              adr      x9, #0x1881c
0x00063b54: 8a 00 00 10              adr      x10, #0x63b64
0x00063b58: 2b 69 68 38              ldrb     w11, [x9, x8]
0x00063b5c: 4a 09 0b 8b              add      x10, x10, x11, lsl #2
0x00063b60: 40 01 1f d6              br       x10
0x00063b64: 9f 24 03 d5              bti      j
0x00063b68: 40 00 80 52              mov      w0, #2
0x00063b6c: 31 00 00 14              b        #0x63c30                   ; -> _ZN19ListenBigSoundModel14readHeaderFromEPKh+0x180
0x00063b70: 1f 20 03 d5              nop      
0x00063b74: 41 3a d7 70              adr      x1, #0x122bf
0x00063b78: 62 fd ff b0              adrp     x2, #0x10000
0x00063b7c: 42 bc 1c 91              add      x2, x2, #0x72f
0x00063b80: 40 00 80 52              mov      w0, #2
0x00063b84: 83 16 80 52              mov      w3, #0xb4
0x00063b88: 30 00 00 14              b        #0x63c48                   ; -> _ZN19ListenBigSoundModel14readHeaderFromEPKh+0x198
0x00063b8c: 1f 20 03 d5              nop      
0x00063b90: 61 39 d7 70              adr      x1, #0x122bf
0x00063b94: 62 fd ff b0              adrp     x2, #0x10000
0x00063b98: 42 bc 1c 91              add      x2, x2, #0x72f
0x00063b9c: c0 00 80 52              mov      w0, #6
0x00063ba0: c3 16 80 52              mov      w3, #0xb6
0x00063ba4: 29 00 00 14              b        #0x63c48                   ; -> _ZN19ListenBigSoundModel14readHeaderFromEPKh+0x198
0x00063ba8: 9f 24 03 d5              bti      j
0x00063bac: 00 01 80 52              mov      w0, #8
0x00063bb0: 20 00 00 14              b        #0x63c30                   ; -> _ZN19ListenBigSoundModel14readHeaderFromEPKh+0x180
0x00063bb4: 9f 24 03 d5              bti      j
0x00063bb8: 1f 20 03 d5              nop      
0x00063bbc: 01 38 d7 70              adr      x1, #0x122bf
0x00063bc0: 62 fd ff b0              adrp     x2, #0x10000
0x00063bc4: 42 bc 1c 91              add      x2, x2, #0x72f
0x00063bc8: e0 03 1f 2a              mov      w0, wzr
0x00063bcc: a3 17 80 52              mov      w3, #0xbd
0x00063bd0: 1e 00 00 14              b        #0x63c48                   ; -> _ZN19ListenBigSoundModel14readHeaderFromEPKh+0x198
0x00063bd4: 9f 24 03 d5              bti      j
0x00063bd8: c0 00 80 52              mov      w0, #6
0x00063bdc: 15 00 00 14              b        #0x63c30                   ; -> _ZN19ListenBigSoundModel14readHeaderFromEPKh+0x180
0x00063be0: 9f 24 03 d5              bti      j
0x00063be4: e0 02 80 52              mov      w0, #0x17
0x00063be8: 12 00 00 14              b        #0x63c30                   ; -> _ZN19ListenBigSoundModel14readHeaderFromEPKh+0x180
0x00063bec: 9f 24 03 d5              bti      j
0x00063bf0: a0 01 80 52              mov      w0, #0xd
0x00063bf4: 0f 00 00 14              b        #0x63c30                   ; -> _ZN19ListenBigSoundModel14readHeaderFromEPKh+0x180
0x00063bf8: 9f 24 03 d5              bti      j
0x00063bfc: 60 00 80 52              mov      w0, #3
0x00063c00: 0c 00 00 14              b        #0x63c30                   ; -> _ZN19ListenBigSoundModel14readHeaderFromEPKh+0x180
0x00063c04: 9f 24 03 d5              bti      j
0x00063c08: 80 00 80 52              mov      w0, #4
0x00063c0c: 09 00 00 14              b        #0x63c30                   ; -> _ZN19ListenBigSoundModel14readHeaderFromEPKh+0x180
0x00063c10: 9f 24 03 d5              bti      j
0x00063c14: e0 00 80 52              mov      w0, #7
0x00063c18: 06 00 00 14              b        #0x63c30                   ; -> _ZN19ListenBigSoundModel14readHeaderFromEPKh+0x180
0x00063c1c: 9f 24 03 d5              bti      j
0x00063c20: a0 00 80 52              mov      w0, #5
0x00063c24: 03 00 00 14              b        #0x63c30                   ; -> _ZN19ListenBigSoundModel14readHeaderFromEPKh+0x180
0x00063c28: 9f 24 03 d5              bti      j
0x00063c2c: 60 02 80 52              mov      w0, #0x13
0x00063c30: 9f 24 03 d5              bti      j
0x00063c34: 1f 20 03 d5              nop      
0x00063c38: 21 34 d7 70              adr      x1, #0x122bf
0x00063c3c: 62 fd ff b0              adrp     x2, #0x10000
0x00063c40: 42 bc 1c 91              add      x2, x2, #0x72f
0x00063c44: 63 17 80 52              mov      w3, #0xbb
0x00063c48: f4 4f 42 a9              ldp      x20, x19, [sp, #0x20]
0x00063c4c: f5 0b 40 f9              ldr      x21, [sp, #0x10]
0x00063c50: fd 7b c3 a8              ldp      x29, x30, [sp], #0x30
0x00063c54: bf 23 03 d5              autiasp  
0x00063c58: 5e b9 02 14              b        #0x1121d0                  ; -> _ZN3sml15sml_debug_printE18listen_status_enumPKcS2_i@plt
