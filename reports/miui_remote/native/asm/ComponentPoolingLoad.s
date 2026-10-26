; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : ComponentPoolingLoad (0x295ac)
; start   : 0x295ac
; size    : 0x274
; bytes   : 0x274   instructions: 157
; generated: 2026-10-23T23:48:33

0x000295ac: ff 83 01 d1              sub      sp, sp, #0x60
0x000295b0: f7 13 00 f9              str      x23, [sp, #0x20]
0x000295b4: f6 57 03 a9              stp      x22, x21, [sp, #0x30]
0x000295b8: f4 4f 04 a9              stp      x20, x19, [sp, #0x40]
0x000295bc: fd 7b 05 a9              stp      x29, x30, [sp, #0x50]
0x000295c0: fd 43 01 91              add      x29, sp, #0x50
0x000295c4: 48 d0 3b d5              mrs      x8, tpidr_el0
0x000295c8: 08 15 40 f9              ldr      x8, [x8, #0x28]
0x000295cc: f3 03 00 aa              mov      x19, x0
0x000295d0: f5 03 02 2a              mov      w21, w2
0x000295d4: f6 03 01 aa              mov      x22, x1
0x000295d8: e8 0f 00 f9              str      x8, [sp, #0x18]
0x000295dc: 74 1a 40 f9              ldr      x20, [x19, #0x30]
0x000295e0: e0 03 14 aa              mov      x0, x20
0x000295e4: 63 91 ff 97              bl       #0xdb70                    ; -> SoilMode@plt
0x000295e8: 1f 04 00 71              cmp      w0, #1
0x000295ec: e1 02 00 54              b.ne     #0x29648
0x000295f0: e0 03 14 aa              mov      x0, x20
0x000295f4: 3b 90 ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x000295f8: f5 03 00 2a              mov      w21, w0
0x000295fc: e0 03 14 aa              mov      x0, x20
0x00029600: 38 90 ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x00029604: f6 03 00 2a              mov      w22, w0
0x00029608: e0 03 14 aa              mov      x0, x20
0x0002960c: 35 90 ff 97              bl       #0xd6e0                    ; -> SoilLoadBinInt@plt
0x00029610: f7 03 00 2a              mov      w23, w0
0x00029614: e0 03 14 aa              mov      x0, x20
0x00029618: e1 03 15 2a              mov      w1, w21
0x0002961c: ed 8e ff 97              bl       #0xd1d0                    ; -> SoilLoadBinArrayInt@plt
0x00029620: 60 3a 00 f9              str      x0, [x19, #0x70]
0x00029624: e0 03 14 aa              mov      x0, x20
0x00029628: e1 03 16 2a              mov      w1, w22
0x0002962c: e9 8e ff 97              bl       #0xd1d0                    ; -> SoilLoadBinArrayInt@plt
0x00029630: 60 4a 00 f9              str      x0, [x19, #0x90]
0x00029634: e0 03 14 aa              mov      x0, x20
0x00029638: e1 03 17 2a              mov      w1, w23
0x0002963c: e5 8e ff 97              bl       #0xd1d0                    ; -> SoilLoadBinArrayInt@plt
0x00029640: 60 46 00 f9              str      x0, [x19, #0x88]
0x00029644: 6b 00 00 14              b        #0x297f0                   ; -> ComponentPoolingLoad+0x244
0x00029648: 21 01 00 b0              adrp     x1, #0x4e000
0x0002964c: 21 08 1e 91              add      x1, x1, #0x782
0x00029650: e0 03 16 aa              mov      x0, x22
0x00029654: bb 8f ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x00029658: e1 03 15 2a              mov      w1, w21
0x0002965c: f5 8e ff 97              bl       #0xd230                    ; -> SoilJsonGetArrayItem@plt
0x00029660: 21 01 00 b0              adrp     x1, #0x4e000
0x00029664: 21 80 3d 91              add      x1, x1, #0xf60
0x00029668: f5 03 00 aa              mov      x21, x0
0x0002966c: b5 8f ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x00029670: f6 03 00 aa              mov      x22, x0
0x00029674: 03 8e ff 97              bl       #0xce80                    ; -> SoilJsonGetArraySize@plt
0x00029678: f7 03 00 2a              mov      w23, w0
0x0002967c: f7 17 00 b9              str      w23, [sp, #0x14]
0x00029680: 68 1a 40 f9              ldr      x8, [x19, #0x30]
0x00029684: e1 7e 7e 93              sbfiz    x1, x23, #2, #0x20
0x00029688: 00 01 40 f9              ldr      x0, [x8]
0x0002968c: 95 8e ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x00029690: 60 3a 00 f9              str      x0, [x19, #0x70]
0x00029694: ff 06 00 71              cmp      w23, #1
0x00029698: 6b 01 00 54              b.lt     #0x296c4
0x0002969c: f7 03 1f aa              mov      x23, xzr
0x000296a0: e0 03 16 aa              mov      x0, x22
0x000296a4: e1 03 17 2a              mov      w1, w23
0x000296a8: de 90 ff 97              bl       #0xda20                    ; -> SoilJsonGetArrayInt@plt
0x000296ac: 68 3a 40 f9              ldr      x8, [x19, #0x70]
0x000296b0: 00 79 37 b8              str      w0, [x8, x23, lsl #2]
0x000296b4: e8 17 80 b9              ldrsw    x8, [sp, #0x14]
0x000296b8: f7 06 00 91              add      x23, x23, #1
0x000296bc: ff 02 08 eb              cmp      x23, x8
0x000296c0: 0b ff ff 54              b.lt     #0x296a0
0x000296c4: 21 01 00 b0              adrp     x1, #0x4e000
0x000296c8: 21 38 35 91              add      x1, x1, #0xd4e
0x000296cc: e0 03 15 aa              mov      x0, x21
0x000296d0: 9c 8f ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x000296d4: f6 03 00 aa              mov      x22, x0
0x000296d8: ea 8d ff 97              bl       #0xce80                    ; -> SoilJsonGetArraySize@plt
0x000296dc: f7 03 00 2a              mov      w23, w0
0x000296e0: f7 13 00 b9              str      w23, [sp, #0x10]
0x000296e4: 68 1a 40 f9              ldr      x8, [x19, #0x30]
0x000296e8: e1 7e 7e 93              sbfiz    x1, x23, #2, #0x20
0x000296ec: 00 01 40 f9              ldr      x0, [x8]
0x000296f0: 7c 8e ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x000296f4: 60 4a 00 f9              str      x0, [x19, #0x90]
0x000296f8: ff 06 00 71              cmp      w23, #1
0x000296fc: 6b 01 00 54              b.lt     #0x29728
0x00029700: f7 03 1f aa              mov      x23, xzr
0x00029704: e0 03 16 aa              mov      x0, x22
0x00029708: e1 03 17 2a              mov      w1, w23
0x0002970c: c5 90 ff 97              bl       #0xda20                    ; -> SoilJsonGetArrayInt@plt
0x00029710: 68 4a 40 f9              ldr      x8, [x19, #0x90]
0x00029714: 00 79 37 b8              str      w0, [x8, x23, lsl #2]
0x00029718: e8 13 80 b9              ldrsw    x8, [sp, #0x10]
0x0002971c: f7 06 00 91              add      x23, x23, #1
0x00029720: ff 02 08 eb              cmp      x23, x8
0x00029724: 0b ff ff 54              b.lt     #0x29704
0x00029728: 21 01 00 b0              adrp     x1, #0x4e000
0x0002972c: 21 58 35 91              add      x1, x1, #0xd56
0x00029730: e0 03 15 aa              mov      x0, x21
0x00029734: 83 8f ff 97              bl       #0xd540                    ; -> SoilJsonGetObjectItem@plt
0x00029738: f5 03 00 aa              mov      x21, x0
0x0002973c: d1 8d ff 97              bl       #0xce80                    ; -> SoilJsonGetArraySize@plt
0x00029740: f6 03 00 2a              mov      w22, w0
0x00029744: f6 0f 00 b9              str      w22, [sp, #0xc]
0x00029748: 68 1a 40 f9              ldr      x8, [x19, #0x30]
0x0002974c: c1 7e 7e 93              sbfiz    x1, x22, #2, #0x20
0x00029750: 00 01 40 f9              ldr      x0, [x8]
0x00029754: 63 8e ff 97              bl       #0xd0e0                    ; -> soil_mempool_alloc@plt
0x00029758: 60 46 00 f9              str      x0, [x19, #0x88]
0x0002975c: df 06 00 71              cmp      w22, #1
0x00029760: 6b 01 00 54              b.lt     #0x2978c
0x00029764: f6 03 1f aa              mov      x22, xzr
0x00029768: e0 03 15 aa              mov      x0, x21
0x0002976c: e1 03 16 2a              mov      w1, w22
0x00029770: ac 90 ff 97              bl       #0xda20                    ; -> SoilJsonGetArrayInt@plt
0x00029774: 68 46 40 f9              ldr      x8, [x19, #0x88]
0x00029778: 00 79 36 b8              str      w0, [x8, x22, lsl #2]
0x0002977c: e8 0f 80 b9              ldrsw    x8, [sp, #0xc]
0x00029780: d6 06 00 91              add      x22, x22, #1
0x00029784: df 02 08 eb              cmp      x22, x8
0x00029788: 0b ff ff 54              b.lt     #0x29768
0x0002978c: e0 03 14 aa              mov      x0, x20
0x00029790: f8 90 ff 97              bl       #0xdb70                    ; -> SoilMode@plt
0x00029794: 1f 08 00 71              cmp      w0, #2
0x00029798: c1 02 00 54              b.ne     #0x297f0
0x0002979c: e0 53 00 91              add      x0, sp, #0x14
0x000297a0: e1 03 14 aa              mov      x1, x20
0x000297a4: 4b 8e ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x000297a8: e0 43 00 91              add      x0, sp, #0x10
0x000297ac: e1 03 14 aa              mov      x1, x20
0x000297b0: 48 8e ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x000297b4: e0 33 00 91              add      x0, sp, #0xc
0x000297b8: e1 03 14 aa              mov      x1, x20
0x000297bc: 45 8e ff 97              bl       #0xd0d0                    ; -> SoilWriteBinInt@plt
0x000297c0: 60 3a 40 f9              ldr      x0, [x19, #0x70]
0x000297c4: e1 17 40 b9              ldr      w1, [sp, #0x14]
0x000297c8: e2 03 14 aa              mov      x2, x20
0x000297cc: 3d 90 ff 97              bl       #0xd8c0                    ; -> SoilWriteBinArrayInt@plt
0x000297d0: 60 4a 40 f9              ldr      x0, [x19, #0x90]
0x000297d4: e1 13 40 b9              ldr      w1, [sp, #0x10]
0x000297d8: e2 03 14 aa              mov      x2, x20
0x000297dc: 39 90 ff 97              bl       #0xd8c0                    ; -> SoilWriteBinArrayInt@plt
0x000297e0: 60 46 40 f9              ldr      x0, [x19, #0x88]
0x000297e4: e1 0f 40 b9              ldr      w1, [sp, #0xc]
0x000297e8: e2 03 14 aa              mov      x2, x20
0x000297ec: 35 90 ff 97              bl       #0xd8c0                    ; -> SoilWriteBinArrayInt@plt
0x000297f0: 48 d0 3b d5              mrs      x8, tpidr_el0
0x000297f4: 08 15 40 f9              ldr      x8, [x8, #0x28]
0x000297f8: e9 0f 40 f9              ldr      x9, [sp, #0x18]
0x000297fc: 1f 01 09 eb              cmp      x8, x9
0x00029800: e1 00 00 54              b.ne     #0x2981c
0x00029804: fd 7b 45 a9              ldp      x29, x30, [sp, #0x50]
0x00029808: f4 4f 44 a9              ldp      x20, x19, [sp, #0x40]
0x0002980c: f6 57 43 a9              ldp      x22, x21, [sp, #0x30]
0x00029810: f7 13 40 f9              ldr      x23, [sp, #0x20]
0x00029814: ff 83 01 91              add      sp, sp, #0x60
0x00029818: c0 03 5f d6              ret      
0x0002981c: e5 8e ff 97              bl       #0xd3b0                    ; -> __stack_chk_fail@plt
