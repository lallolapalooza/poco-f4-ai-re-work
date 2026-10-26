; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libdnnvad_jni.so
; target  : address 0x2fd0
; start   : 0x2fd0
; size    : 0x2bc
; bytes   : 0x2bc   instructions: 175
; generated: 2026-10-23T23:27:16

0x00002fd0: 3f 23 03 d5              paciasp  
0x00002fd4: ff c3 02 d1              sub      sp, sp, #0xb0
0x00002fd8: fd 7b 05 a9              stp      x29, x30, [sp, #0x50]
0x00002fdc: fb 33 00 f9              str      x27, [sp, #0x60]
0x00002fe0: fa 67 07 a9              stp      x26, x25, [sp, #0x70]
0x00002fe4: f8 5f 08 a9              stp      x24, x23, [sp, #0x80]
0x00002fe8: f6 57 09 a9              stp      x22, x21, [sp, #0x90]
0x00002fec: f4 4f 0a a9              stp      x20, x19, [sp, #0xa0]
0x00002ff0: fd 43 01 91              add      x29, sp, #0x50
0x00002ff4: 5b d0 3b d5              mrs      x27, tpidr_el0
0x00002ff8: 13 a0 00 91              add      x19, x0, #0x28
0x00002ffc: f4 03 00 aa              mov      x20, x0
0x00003000: 68 17 40 f9              ldr      x8, [x27, #0x28]
0x00003004: e0 03 13 aa              mov      x0, x19
0x00003008: f8 03 04 2a              mov      w24, w4
0x0000300c: f6 03 03 2a              mov      w22, w3
0x00003010: f7 03 02 aa              mov      x23, x2
0x00003014: f5 03 01 aa              mov      x21, x1
0x00003018: a8 83 1f f8              stur     x8, [x29, #-8]
0x0000301c: 2f 02 00 94              bl       #0x38d8
0x00003020: 00 e4 00 6f              movi     v0.2d, #0000000000000000
0x00003024: 20 00 80 52              mov      w0, #1
0x00003028: 01 04 80 52              mov      w1, #0x20
0x0000302c: bf ff 3e a9              stp      xzr, xzr, [x29, #-0x18]
0x00003030: e0 83 00 ad              stp      q0, q0, [sp, #0x10]
0x00003034: 2f 02 00 94              bl       #0x38f0                    ; -> calloc@plt
0x00003038: 80 06 00 f9              str      x0, [x20, #8]
0x0000303c: e0 05 00 b4              cbz      x0, #0x30f8
0x00003040: 1f 20 03 d5              nop      
0x00003044: 40 05 ff 10              adr      x0, #0x10ec
0x00003048: 41 00 80 52              mov      w1, #2
0x0000304c: 2f 02 00 94              bl       #0x3908
0x00003050: 80 02 00 f9              str      x0, [x20]
0x00003054: 60 06 00 b4              cbz      x0, #0x3120
0x00003058: e1 ff ff d0              adrp     x1, #0x1000
0x0000305c: 21 f8 13 91              add      x1, x1, #0x4fe
0x00003060: 30 02 00 94              bl       #0x3920                    ; -> dlsym@plt
0x00003064: 60 07 00 b4              cbz      x0, #0x3150
0x00003068: fa 03 16 2a              mov      w26, w22
0x0000306c: f9 03 00 aa              mov      x25, x0
0x00003070: 20 00 80 52              mov      w0, #1
0x00003074: e1 03 1a aa              mov      x1, x26
0x00003078: 98 12 00 b9              str      w24, [x20, #0x10]
0x0000307c: 38 00 80 52              mov      w24, #1
0x00003080: 1c 02 00 94              bl       #0x38f0                    ; -> calloc@plt
0x00003084: 80 12 00 f9              str      x0, [x20, #0x20]
0x00003088: 20 0a 00 b4              cbz      x0, #0x31cc
0x0000308c: e1 03 1a aa              mov      x1, x26
0x00003090: e2 03 17 aa              mov      x2, x23
0x00003094: e3 03 1a aa              mov      x3, x26
0x00003098: 86 01 00 94              bl       #0x36b0
0x0000309c: c8 00 80 52              mov      w8, #6
0x000030a0: 89 12 40 f9              ldr      x9, [x20, #0x20]
0x000030a4: 28 00 a0 72              movk     w8, #1, lsl #16
0x000030a8: ea 43 00 91              add      x10, sp, #0x10
0x000030ac: 80 06 40 f9              ldr      x0, [x20, #8]
0x000030b0: a1 63 00 d1              sub      x1, x29, #0x18
0x000030b4: f6 5b 04 29              stp      w22, w22, [sp, #0x20]
0x000030b8: e8 13 00 b9              str      w8, [sp, #0x10]
0x000030bc: e9 0f 00 f9              str      x9, [sp, #0x18]
0x000030c0: aa 03 1f f8              stur     x10, [x29, #-0x10]
0x000030c4: b8 83 1e b8              stur     w24, [x29, #-0x18]
0x000030c8: 20 03 3f d6              blr      x25
0x000030cc: a0 08 00 34              cbz      w0, #0x31e0
0x000030d0: e1 ff ff d0              adrp     x1, #0x1000
0x000030d4: 21 84 12 91              add      x1, x1, #0x4a1
0x000030d8: e2 ff ff d0              adrp     x2, #0x1000
0x000030dc: 42 98 1a 91              add      x2, x2, #0x6a6
0x000030e0: c0 00 80 52              mov      w0, #6
0x000030e4: 97 01 00 94              bl       #0x3740                    ; -> __android_log_print@plt
0x000030e8: 95 00 80 12              mov      w21, #-5
0x000030ec: 88 06 40 f9              ldr      x8, [x20, #8]
0x000030f0: 68 04 00 b5              cbnz     x8, #0x317c
0x000030f4: 26 00 00 14              b        #0x318c                    ; -> _ZN3Vad4initEPaPhji+0x1bc
0x000030f8: e1 ff ff d0              adrp     x1, #0x1000
0x000030fc: 21 84 12 91              add      x1, x1, #0x4a1
0x00003100: e2 ff ff b0              adrp     x2, #0
0x00003104: 42 8c 3a 91              add      x2, x2, #0xea3
0x00003108: c0 00 80 52              mov      w0, #6
0x0000310c: 8d 01 00 94              bl       #0x3740                    ; -> __android_log_print@plt
0x00003110: d5 00 80 12              mov      w21, #-7
0x00003114: 88 06 40 f9              ldr      x8, [x20, #8]
0x00003118: 28 03 00 b5              cbnz     x8, #0x317c
0x0000311c: 1c 00 00 14              b        #0x318c                    ; -> _ZN3Vad4initEPaPhji+0x1bc
0x00003120: 06 02 00 94              bl       #0x3938
0x00003124: e3 03 00 aa              mov      x3, x0
0x00003128: e1 ff ff d0              adrp     x1, #0x1000
0x0000312c: 21 84 12 91              add      x1, x1, #0x4a1
0x00003130: e2 ff ff b0              adrp     x2, #0
0x00003134: 42 34 3b 91              add      x2, x2, #0xecd
0x00003138: c0 00 80 52              mov      w0, #6
0x0000313c: 81 01 00 94              bl       #0x3740                    ; -> __android_log_print@plt
0x00003140: 55 00 80 12              mov      w21, #-3
0x00003144: 88 06 40 f9              ldr      x8, [x20, #8]
0x00003148: a8 01 00 b5              cbnz     x8, #0x317c
0x0000314c: 10 00 00 14              b        #0x318c                    ; -> _ZN3Vad4initEPaPhji+0x1bc
0x00003150: fa 01 00 94              bl       #0x3938
0x00003154: e3 03 00 aa              mov      x3, x0
0x00003158: e1 ff ff d0              adrp     x1, #0x1000
0x0000315c: 21 84 12 91              add      x1, x1, #0x4a1
0x00003160: e2 ff ff b0              adrp     x2, #0
0x00003164: 42 8c 3c 91              add      x2, x2, #0xf23
0x00003168: c0 00 80 52              mov      w0, #6
0x0000316c: 75 01 00 94              bl       #0x3740                    ; -> __android_log_print@plt
0x00003170: 75 00 80 12              mov      w21, #-4
0x00003174: 88 06 40 f9              ldr      x8, [x20, #8]
0x00003178: a8 00 00 b4              cbz      x8, #0x318c
0x0000317c: 1f 01 00 f9              str      xzr, [x8]
0x00003180: 80 06 40 f9              ldr      x0, [x20, #8]
0x00003184: 93 01 00 94              bl       #0x37d0                    ; -> free@plt
0x00003188: 9f 06 00 f9              str      xzr, [x20, #8]
0x0000318c: e0 03 13 aa              mov      x0, x19
0x00003190: f0 01 00 94              bl       #0x3950                    ; -> pthread_mutex_unlock@plt
0x00003194: 68 17 40 f9              ldr      x8, [x27, #0x28]
0x00003198: a9 83 5f f8              ldur     x9, [x29, #-8]
0x0000319c: 1f 01 09 eb              cmp      x8, x9
0x000031a0: 41 07 00 54              b.ne     #0x3288
0x000031a4: e0 03 15 2a              mov      w0, w21
0x000031a8: f4 4f 4a a9              ldp      x20, x19, [sp, #0xa0]
0x000031ac: f6 57 49 a9              ldp      x22, x21, [sp, #0x90]
0x000031b0: f8 5f 48 a9              ldp      x24, x23, [sp, #0x80]
0x000031b4: fa 67 47 a9              ldp      x26, x25, [sp, #0x70]
0x000031b8: fd 7b 45 a9              ldp      x29, x30, [sp, #0x50]
0x000031bc: fb 33 40 f9              ldr      x27, [sp, #0x60]
0x000031c0: ff c3 02 91              add      sp, sp, #0xb0
0x000031c4: bf 23 03 d5              autiasp  
0x000031c8: c0 03 5f d6              ret      
0x000031cc: e1 ff ff d0              adrp     x1, #0x1000
0x000031d0: 21 84 12 91              add      x1, x1, #0x4a1
0x000031d4: e2 ff ff d0              adrp     x2, #0x1000
0x000031d8: 42 1c 15 91              add      x2, x2, #0x547
0x000031dc: cb ff ff 17              b        #0x3108                    ; -> _ZN3Vad4initEPaPhji+0x138
0x000031e0: 88 06 40 f9              ldr      x8, [x20, #8]
0x000031e4: 08 01 40 f9              ldr      x8, [x8]
0x000031e8: 48 03 00 b4              cbz      x8, #0x3250
0x000031ec: 20 00 80 52              mov      w0, #1
0x000031f0: 01 01 80 52              mov      w1, #8
0x000031f4: bf 01 00 94              bl       #0x38f0                    ; -> calloc@plt
0x000031f8: 60 03 00 b4              cbz      x0, #0x3264
0x000031fc: 01 01 80 52              mov      w1, #8
0x00003200: e2 03 15 aa              mov      x2, x21
0x00003204: 03 01 80 52              mov      w3, #8
0x00003208: f6 03 00 aa              mov      x22, x0
0x0000320c: 29 01 00 94              bl       #0x36b0
0x00003210: 00 05 00 0f              movi     v0.2s, #8
0x00003214: 80 06 40 f9              ldr      x0, [x20, #8]
0x00003218: f6 03 00 f9              str      x22, [sp]
0x0000321c: e3 03 00 91              mov      x3, sp
0x00003220: c1 00 80 52              mov      w1, #6
0x00003224: e2 03 1f aa              mov      x2, xzr
0x00003228: e0 07 00 fd              str      d0, [sp, #8]
0x0000322c: 08 00 40 f9              ldr      x8, [x0]
0x00003230: 08 09 40 f9              ldr      x8, [x8, #0x10]
0x00003234: 00 01 3f d6              blr      x8
0x00003238: 00 02 00 34              cbz      w0, #0x3278
0x0000323c: e1 ff ff d0              adrp     x1, #0x1000
0x00003240: 21 84 12 91              add      x1, x1, #0x4a1
0x00003244: e2 ff ff d0              adrp     x2, #0x1000
0x00003248: 42 8c 17 91              add      x2, x2, #0x5e3
0x0000324c: a5 ff ff 17              b        #0x30e0                    ; -> _ZN3Vad4initEPaPhji+0x110
0x00003250: e1 ff ff d0              adrp     x1, #0x1000
0x00003254: 21 84 12 91              add      x1, x1, #0x4a1
0x00003258: e2 ff ff d0              adrp     x2, #0x1000
0x0000325c: 42 f4 07 91              add      x2, x2, #0x1fd
0x00003260: a0 ff ff 17              b        #0x30e0                    ; -> _ZN3Vad4initEPaPhji+0x110
0x00003264: e1 ff ff d0              adrp     x1, #0x1000
0x00003268: 21 84 12 91              add      x1, x1, #0x4a1
0x0000326c: e2 ff ff d0              adrp     x2, #0x1000
0x00003270: 42 40 0c 91              add      x2, x2, #0x310
0x00003274: a5 ff ff 17              b        #0x3108                    ; -> _ZN3Vad4initEPaPhji+0x138
0x00003278: e0 03 16 aa              mov      x0, x22
0x0000327c: 55 01 00 94              bl       #0x37d0                    ; -> free@plt
0x00003280: f5 03 1f 2a              mov      w21, wzr
0x00003284: c2 ff ff 17              b        #0x318c                    ; -> _ZN3Vad4initEPaPhji+0x1bc
0x00003288: 34 01 00 94              bl       #0x3758
