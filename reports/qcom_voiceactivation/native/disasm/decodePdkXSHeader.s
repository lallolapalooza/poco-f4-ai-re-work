; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libqvasml.so
; target  : address 0x901d4
; start   : 0x901d4
; size    : 0xf4
; bytes   : 0xf4   instructions: 61
; generated: 2026-10-23T23:25:37

0x000901d4: 3f 23 03 d5              paciasp  
0x000901d8: fd 7b bf a9              stp      x29, x30, [sp, #-0x10]!
0x000901dc: fd 03 00 91              mov      x29, sp
0x000901e0: 40 02 00 b4              cbz      x0, #0x90228
0x000901e4: 81 03 00 b4              cbz      x1, #0x90254
0x000901e8: 08 00 40 b9              ldr      w8, [x0]
0x000901ec: 89 66 89 52              mov      w9, #0x4b34
0x000901f0: 89 08 aa 72              movk     w9, #0x5044, lsl #16
0x000901f4: 1f 01 09 6b              cmp      w8, w9
0x000901f8: 8c 04 00 54              b.gt     #0x90288
0x000901fc: 09 8a 88 52              mov      w9, #0x4450
0x00090200: 69 a9 a6 72              movk     w9, #0x354b, lsl #16
0x00090204: 1f 01 09 6b              cmp      w8, w9
0x00090208: 40 05 00 54              b.eq     #0x902b0
0x0009020c: 09 8a 88 52              mov      w9, #0x4450
0x00090210: 69 c9 a6 72              movk     w9, #0x364b, lsl #16
0x00090214: 1f 01 09 6b              cmp      w8, w9
0x00090218: 80 04 00 54              b.eq     #0x902a8
0x0009021c: e0 03 1f 2a              mov      w0, wzr
0x00090220: 3f 00 00 b9              str      wzr, [x1]
0x00090224: 26 00 00 14              b        #0x902bc                   ; -> _ZN3sml17decodePdkXSHeaderEPhPi+0xe8
0x00090228: 01 fc ff 90              adrp     x1, #0x10000
0x0009022c: 21 f8 1c 91              add      x1, x1, #0x73e
0x00090230: 22 fc ff 90              adrp     x2, #0x14000
0x00090234: 42 c0 3d 91              add      x2, x2, #0xf70
0x00090238: 1f 20 03 d5              nop      
0x0009023c: 83 63 c3 10              adr      x3, #0x16eac
0x00090240: 24 fc ff d0              adrp     x4, #0x16000
0x00090244: 84 38 3d 91              add      x4, x4, #0xf4e
0x00090248: c0 00 80 52              mov      w0, #6
0x0009024c: 65 6e 81 52              mov      w5, #0xb73
0x00090250: 0b 00 00 14              b        #0x9027c                   ; -> _ZN3sml17decodePdkXSHeaderEPhPi+0xa8
0x00090254: 01 fc ff 90              adrp     x1, #0x10000
0x00090258: 21 f8 1c 91              add      x1, x1, #0x73e
0x0009025c: 22 fc ff 90              adrp     x2, #0x14000
0x00090260: 42 c0 3d 91              add      x2, x2, #0xf70
0x00090264: 1f 20 03 d5              nop      
0x00090268: 23 62 c3 10              adr      x3, #0x16eac
0x0009026c: 24 fc ff d0              adrp     x4, #0x16000
0x00090270: 84 38 3d 91              add      x4, x4, #0xf4e
0x00090274: c0 00 80 52              mov      w0, #6
0x00090278: 85 6e 81 52              mov      w5, #0xb74
0x0009027c: 07 06 02 94              bl       #0x111a98
0x00090280: 40 00 80 52              mov      w0, #2
0x00090284: 0e 00 00 14              b        #0x902bc                   ; -> _ZN3sml17decodePdkXSHeaderEPhPi+0xe8
0x00090288: a9 66 89 52              mov      w9, #0x4b35
0x0009028c: 89 08 aa 72              movk     w9, #0x5044, lsl #16
0x00090290: 1f 01 09 6b              cmp      w8, w9
0x00090294: e0 00 00 54              b.eq     #0x902b0
0x00090298: c9 66 89 52              mov      w9, #0x4b36
0x0009029c: 89 08 aa 72              movk     w9, #0x5044, lsl #16
0x000902a0: 1f 01 09 6b              cmp      w8, w9
0x000902a4: c1 fb ff 54              b.ne     #0x9021c
0x000902a8: c8 00 80 52              mov      w8, #6
0x000902ac: 02 00 00 14              b        #0x902b4                   ; -> _ZN3sml17decodePdkXSHeaderEPhPi+0xe0
0x000902b0: a8 00 80 52              mov      w8, #5
0x000902b4: e0 03 1f 2a              mov      w0, wzr
0x000902b8: 28 00 00 b9              str      w8, [x1]
0x000902bc: fd 7b c1 a8              ldp      x29, x30, [sp], #0x10
0x000902c0: bf 23 03 d5              autiasp  
0x000902c4: c0 03 5f d6              ret      
