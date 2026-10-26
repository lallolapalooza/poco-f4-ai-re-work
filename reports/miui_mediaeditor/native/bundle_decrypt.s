; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_mediaeditor/work/x/lib/arm64-v8a/libCNamaSDK.so
; target  : address 0x56fd68
; start   : 0x56fd68
; size    : 0x3fc
; bytes   : 0x3fc   instructions: 255
; generated: 2026-10-23T23:18:28

0x0056fd68: ff c3 02 d1              sub      sp, sp, #0xb0
0x0056fd6c: fd 7b 05 a9              stp      x29, x30, [sp, #0x50]
0x0056fd70: fc 6f 06 a9              stp      x28, x27, [sp, #0x60]
0x0056fd74: fa 67 07 a9              stp      x26, x25, [sp, #0x70]
0x0056fd78: f8 5f 08 a9              stp      x24, x23, [sp, #0x80]
0x0056fd7c: f6 57 09 a9              stp      x22, x21, [sp, #0x90]
0x0056fd80: f4 4f 0a a9              stp      x20, x19, [sp, #0xa0]
0x0056fd84: fd 43 01 91              add      x29, sp, #0x50
0x0056fd88: 59 d0 3b d5              mrs      x25, tpidr_el0
0x0056fd8c: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x0056fd90: f3 03 02 aa              mov      x19, x2
0x0056fd94: f5 03 01 2a              mov      w21, w1
0x0056fd98: f4 03 00 aa              mov      x20, x0
0x0056fd9c: a8 83 1f f8              stur     x8, [x29, #-8]
0x0056fda0: a1 43 1f b8              stur     w1, [x29, #-0xc]
0x0056fda4: 0f e1 1f 94              bl       #0xd681e0                  ; -> _ZN4nama3Log8InstanceEv@plt
0x0056fda8: 88 41 00 f0              adrp     x8, #0xda2000
0x0056fdac: 08 29 47 f9              ldr      x8, [x8, #0xe50]           ; -> _ZN4nama3Log13m_log_modulesE@got
0x0056fdb0: 08 05 40 39              ldrb     w8, [x8, #1]
0x0056fdb4: 08 03 20 36              tbz      w8, #4, #0x56fe14
0x0056fdb8: 88 41 00 f0              adrp     x8, #0xda2000
0x0056fdbc: 08 2d 47 f9              ldr      x8, [x8, #0xe58]           ; -> _ZGVZN8fuspdlog7details8registry8instanceEvE10s_instance@got
0x0056fdc0: 08 fd df 08              ldarb    w8, [x8]
0x0056fdc4: 88 18 00 36              tbz      w8, #0, #0x5700d4
0x0056fdc8: 88 41 00 f0              adrp     x8, #0xda2000
0x0056fdcc: 08 31 47 f9              ldr      x8, [x8, #0xe60]           ; -> _ZZN8fuspdlog7details8registry8instanceEvE10s_instance@got
0x0056fdd0: 89 ef ff b0              adrp     x9, #0x360000
0x0056fdd4: 23 ef ff 90              adrp     x3, #0x353000
0x0056fdd8: 29 01 1a 91              add      x9, x9, #0x680
0x0056fddc: 00 69 40 f9              ldr      x0, [x8, #0xd0]            ; -> _ZTIx@got
0x0056fde0: 08 0a 80 52              mov      w8, #0x50
0x0056fde4: e8 33 00 b9              str      w8, [sp, #0x30]
0x0056fde8: 88 ef ff b0              adrp     x8, #0x360000
0x0056fdec: 08 29 1b 91              add      x8, x8, #0x6ca
0x0056fdf0: 63 28 3b 91              add      x3, x3, #0xeca
0x0056fdf4: e1 a3 00 91              add      x1, sp, #0x28
0x0056fdf8: a5 33 00 d1              sub      x5, x29, #0xc
0x0056fdfc: 22 00 80 52              mov      w2, #1
0x0056fe00: c4 04 80 52              mov      w4, #0x26
0x0056fe04: e9 17 00 f9              str      x9, [sp, #0x28]
0x0056fe08: e8 1f 00 f9              str      x8, [sp, #0x38]
0x0056fe0c: 2d e1 1f 94              bl       #0xd682c0                  ; -> _ZN8fuspdlog6logger3logIJiEEEvNS_10source_locENS_5level10level_enumEN3fmt2v617basic_string_viewIcEEDpRKT_@plt
0x0056fe10: b5 43 5f b8              ldur     w21, [x29, #-0xc]
0x0056fe14: bf 82 00 71              cmp      w21, #0x20
0x0056fe18: 6a 00 00 54              b.ge     #0x56fe24
0x0056fe1c: 13 00 80 12              mov      w19, #-1
0x0056fe20: 3f 00 00 14              b        #0x56ff1c                  ; -> _ZN8CNamaSDK12BundleHelper24DecryptObfuscatedPackageEPKhiRNSt6__ndk16vectorIhNS3_9allocatorIhEEEE+0x1b4
0x0056fe24: 88 02 40 39              ldrb     w8, [x20]
0x0056fe28: 1f cd 03 71              cmp      w8, #0xf3
0x0056fe2c: 81 02 00 54              b.ne     #0x56fe7c
0x0056fe30: 88 06 40 39              ldrb     w8, [x20, #1]
0x0056fe34: 1f 6d 01 71              cmp      w8, #0x5b
0x0056fe38: 21 02 00 54              b.ne     #0x56fe7c
0x0056fe3c: 88 0a 40 39              ldrb     w8, [x20, #2]
0x0056fe40: 1f 19 00 71              cmp      w8, #6
0x0056fe44: c1 01 00 54              b.ne     #0x56fe7c
0x0056fe48: 88 0e 40 39              ldrb     w8, [x20, #3]
0x0056fe4c: 1f 49 00 71              cmp      w8, #0x12
0x0056fe50: 61 01 00 54              b.ne     #0x56fe7c
0x0056fe54: f5 43 00 91              add      x21, sp, #0x10
0x0056fe58: ff 7f 01 a9              stp      xzr, xzr, [sp, #0x10]
0x0056fe5c: ff 13 00 f9              str      xzr, [sp, #0x20]
0x0056fe60: e0 43 00 91              add      x0, sp, #0x10
0x0056fe64: 01 84 80 52              mov      w1, #0x420
0x0056fe68: 75 93 fd 97              bl       #0x4d4c3c
0x0056fe6c: ba 22 00 91              add      x26, x21, #8
0x0056fe70: 37 00 80 52              mov      w23, #1
0x0056fe74: 9b 00 80 52              mov      w27, #4
0x0056fe78: 0b 00 00 14              b        #0x56fea4                  ; -> _ZN8CNamaSDK12BundleHelper24DecryptObfuscatedPackageEPKhiRNSt6__ndk16vectorIhNS3_9allocatorIhEEEE+0x13c
0x0056fe7c: a8 22 00 51              sub      w8, w21, #8
0x0056fe80: f6 43 00 91              add      x22, sp, #0x10
0x0056fe84: 01 7d 40 93              sxtw     x1, w8
0x0056fe88: ff 7f 01 a9              stp      xzr, xzr, [sp, #0x10]
0x0056fe8c: ff 13 00 f9              str      xzr, [sp, #0x20]
0x0056fe90: e0 43 00 91              add      x0, sp, #0x10
0x0056fe94: 6a 93 fd 97              bl       #0x4d4c3c
0x0056fe98: fb 03 1f 2a              mov      w27, wzr
0x0056fe9c: f7 03 1f 2a              mov      w23, wzr
0x0056fea0: da 22 00 91              add      x26, x22, #8
0x0056fea4: 48 03 40 f9              ldr      x8, [x26]
0x0056fea8: e9 0b 40 f9              ldr      x9, [sp, #0x10]
0x0056feac: 6a 07 1d 32              orr      w10, w27, #0x18
0x0056feb0: 81 02 0a 8b              add      x1, x20, x10
0x0056feb4: 15 01 09 cb              sub      x21, x8, x9
0x0056feb8: 20 41 00 91              add      x0, x9, #0x10
0x0056febc: a2 42 00 d1              sub      x2, x21, #0x10
0x0056fec0: 4c e0 1f 94              bl       #0xd67ff0                  ; -> memcpy@plt
0x0056fec4: a8 41 00 90              adrp     x8, #0xda3000
0x0056fec8: e0 0b 40 f9              ldr      x0, [sp, #0x10]
0x0056fecc: 08 cd 45 f9              ldr      x8, [x8, #0xb98]           ; -> _ZN8CNamaSDK12BundleHelper16_obfuscation_keyE@got
0x0056fed0: 83 02 1b 8b              add      x3, x20, x27
0x0056fed4: e2 03 15 aa              mov      x2, x21
0x0056fed8: e1 03 00 aa              mov      x1, x0
0x0056fedc: 04 01 40 f9              ldr      x4, [x8]                   ; -> _ZTVN8fuspdlog7details11F_formatterINS0_13scoped_padderEEE@got
0x0056fee0: f9 fc ff 97              bl       #0x56f2c4
0x0056fee4: 60 03 00 34              cbz      w0, #0x56ff50
0x0056fee8: 60 02 40 f9              ldr      x0, [x19]
0x0056feec: a0 00 00 b4              cbz      x0, #0x56ff00
0x0056fef0: 60 06 00 f9              str      x0, [x19, #8]
0x0056fef4: 43 e0 1f 94              bl       #0xd68000                  ; -> _ZdlPv@plt
0x0056fef8: 7f 7e 00 a9              stp      xzr, xzr, [x19]
0x0056fefc: 7f 0a 00 f9              str      xzr, [x19, #0x10]
0x0056ff00: 7f 7e 00 a9              stp      xzr, xzr, [x19]
0x0056ff04: 7f 0a 00 f9              str      xzr, [x19, #0x10]
0x0056ff08: 33 00 80 12              mov      w19, #-2
0x0056ff0c: e0 0b 40 f9              ldr      x0, [sp, #0x10]
0x0056ff10: 60 00 00 b4              cbz      x0, #0x56ff1c
0x0056ff14: 40 03 00 f9              str      x0, [x26]
0x0056ff18: 3a e0 1f 94              bl       #0xd68000                  ; -> _ZdlPv@plt
0x0056ff1c: 28 17 40 f9              ldr      x8, [x25, #0x28]
0x0056ff20: a9 83 5f f8              ldur     x9, [x29, #-8]
0x0056ff24: 1f 01 09 eb              cmp      x8, x9
0x0056ff28: a1 0f 00 54              b.ne     #0x57011c
0x0056ff2c: e0 03 13 2a              mov      w0, w19
0x0056ff30: f4 4f 4a a9              ldp      x20, x19, [sp, #0xa0]
0x0056ff34: f6 57 49 a9              ldp      x22, x21, [sp, #0x90]
0x0056ff38: f8 5f 48 a9              ldp      x24, x23, [sp, #0x80]
0x0056ff3c: fa 67 47 a9              ldp      x26, x25, [sp, #0x70]
0x0056ff40: fc 6f 46 a9              ldp      x28, x27, [sp, #0x60]
0x0056ff44: fd 7b 45 a9              ldp      x29, x30, [sp, #0x50]
0x0056ff48: ff c3 02 91              add      sp, sp, #0xb0
0x0056ff4c: c0 03 5f d6              ret      
0x0056ff50: a8 43 5f b8              ldur     w8, [x29, #-0xc]
0x0056ff54: 69 0a 40 f9              ldr      x9, [x19, #0x10]
0x0056ff58: 76 02 40 f9              ldr      x22, [x19]
0x0056ff5c: 08 01 1b 4b              sub      w8, w8, w27
0x0056ff60: 08 a1 00 51              sub      w8, w8, #0x28
0x0056ff64: 18 7d 40 93              sxtw     x24, w8
0x0056ff68: 28 01 16 cb              sub      x8, x9, x22
0x0056ff6c: 1f 01 18 eb              cmp      x8, x24
0x0056ff70: a2 02 00 54              b.hs     #0x56ffc4
0x0056ff74: 7c 06 40 f9              ldr      x28, [x19, #8]
0x0056ff78: f7 0f 00 b9              str      w23, [sp, #0xc]
0x0056ff7c: e0 03 18 aa              mov      x0, x24
0x0056ff80: 18 e0 1f 94              bl       #0xd67fe0                  ; -> _Znwm@plt
0x0056ff84: 82 03 16 cb              sub      x2, x28, x22
0x0056ff88: f7 03 00 aa              mov      x23, x0
0x0056ff8c: 1c 00 02 8b              add      x28, x0, x2
0x0056ff90: 5f 04 00 f1              cmp      x2, #1
0x0056ff94: 18 00 18 8b              add      x24, x0, x24
0x0056ff98: ab 00 00 54              b.lt     #0x56ffac
0x0056ff9c: e0 03 17 aa              mov      x0, x23
0x0056ffa0: e1 03 16 aa              mov      x1, x22
0x0056ffa4: 13 e0 1f 94              bl       #0xd67ff0                  ; -> memcpy@plt
0x0056ffa8: 76 02 40 f9              ldr      x22, [x19]
0x0056ffac: 77 72 00 a9              stp      x23, x28, [x19]
0x0056ffb0: f7 0f 40 b9              ldr      w23, [sp, #0xc]
0x0056ffb4: 78 0a 00 f9              str      x24, [x19, #0x10]
0x0056ffb8: 76 00 00 b4              cbz      x22, #0x56ffc4
0x0056ffbc: e0 03 16 aa              mov      x0, x22
0x0056ffc0: 10 e0 1f 94              bl       #0xd68000                  ; -> _ZdlPv@plt
0x0056ffc4: 61 06 40 f9              ldr      x1, [x19, #8]
0x0056ffc8: e8 0b 40 f9              ldr      x8, [sp, #0x10]
0x0056ffcc: 43 03 40 f9              ldr      x3, [x26]
0x0056ffd0: 02 81 00 91              add      x2, x8, #0x20
0x0056ffd4: e0 03 13 aa              mov      x0, x19
0x0056ffd8: 92 f7 1f 94              bl       #0xd6de20                  ; -> _ZNSt6__ndk16vectorIhNS_9allocatorIhEEE6insertINS_13move_iteratorINS_11__wrap_iterIPhEEEEEENS_9enable_ifIXaasr27__is_cpp17_forward_iteratorIT_EE5valuesr16is_constructibleIhNS_15iterator_traitsISB_E9referenceEEE5valueES8_E4typeENS6_IPKhEESB_SB_@plt
0x0056ffdc: 57 07 00 34              cbz      w23, #0x5700c4
0x0056ffe0: 61 06 40 f9              ldr      x1, [x19, #8]
0x0056ffe4: a8 43 9f b8              ldursw   x8, [x29, #-0xc]
0x0056ffe8: 89 02 1b 8b              add      x9, x20, x27
0x0056ffec: 29 01 15 8b              add      x9, x9, x21
0x0056fff0: 22 21 00 91              add      x2, x9, #8
0x0056fff4: 83 02 08 8b              add      x3, x20, x8
0x0056fff8: e0 03 13 aa              mov      x0, x19
0x0056fffc: 8d f7 1f 94              bl       #0xd6de30                  ; -> _ZNSt6__ndk16vectorIhNS_9allocatorIhEEE6insertIPKhEENS_9enable_ifIXaasr27__is_cpp17_forward_iteratorIT_EE5valuesr16is_constructibleIhNS_15iterator_traitsIS8_E9referenceEEE5valueENS_11__wrap_iterIPhEEE4typeENSC_IS6_EES8_S8_@plt
0x00570000: 88 12 40 39              ldrb     w8, [x20, #4]
0x00570004: 8a 16 40 39              ldrb     w10, [x20, #5]
0x00570008: 6f 26 40 a9              ldp      x15, x9, [x19]
0x0057000c: 8b 1a 40 39              ldrb     w11, [x20, #6]
0x00570010: 48 01 08 4a              eor      w8, w10, w8
0x00570014: 2a 01 0f 4b              sub      w10, w9, w15
0x00570018: 4c 7d 02 13              asr      w12, w10, #2
0x0057001c: 08 01 0b 4a              eor      w8, w8, w11
0x00570020: ec 03 00 34              cbz      w12, #0x57009c
0x00570024: 0b 1d 00 12              and      w11, w8, #0xff
0x00570028: ee c3 00 32              mov      w14, #0x1010101
0x0057002c: 8d 05 00 51              sub      w13, w12, #1
0x00570030: bf 19 00 71              cmp      w13, #6
0x00570034: 6b 7d 0e 1b              mul      w11, w11, w14
0x00570038: 88 00 00 54              b.hi     #0x570048
0x0057003c: ee 03 1f 2a              mov      w14, wzr
0x00570040: ed 03 0f aa              mov      x13, x15
0x00570044: 10 00 00 14              b        #0x570084                  ; -> _ZN8CNamaSDK12BundleHelper24DecryptObfuscatedPackageEPKhiRNSt6__ndk16vectorIhNS3_9allocatorIhEEEE+0x31c
0x00570048: b0 05 00 91              add      x16, x13, #1
0x0057004c: 0e 76 7d 92              and      x14, x16, #0x1fffffff8
0x00570050: 60 0d 04 4e              dup      v0.4s, w11
0x00570054: ed 09 0e 8b              add      x13, x15, x14, lsl #2
0x00570058: ef 41 00 91              add      x15, x15, #0x10
0x0057005c: f1 03 0e aa              mov      x17, x14
0x00570060: e1 89 7f ad              ldp      q1, q2, [x15, #-0x10]
0x00570064: 31 22 00 f1              subs     x17, x17, #8
0x00570068: 21 1c 20 6e              eor      v1.16b, v1.16b, v0.16b
0x0057006c: 42 1c 20 6e              eor      v2.16b, v2.16b, v0.16b
0x00570070: e1 89 3f ad              stp      q1, q2, [x15, #-0x10]
0x00570074: ef 81 00 91              add      x15, x15, #0x20
0x00570078: 41 ff ff 54              b.ne     #0x570060
0x0057007c: 1f 02 0e eb              cmp      x16, x14
0x00570080: e0 00 00 54              b.eq     #0x57009c
0x00570084: 8c 01 0e 4b              sub      w12, w12, w14
0x00570088: ae 01 40 b9              ldr      w14, [x13]
0x0057008c: 8c 05 00 71              subs     w12, w12, #1
0x00570090: ce 01 0b 4a              eor      w14, w14, w11
0x00570094: ae 45 00 b8              str      w14, [x13], #4
0x00570098: 81 ff ff 54              b.ne     #0x570088
0x0057009c: 4a 05 00 72              ands     w10, w10, #3
0x005700a0: 20 01 00 54              b.eq     #0x5700c4
0x005700a4: 29 05 00 d1              sub      x9, x9, #1
0x005700a8: 4a 05 00 11              add      w10, w10, #1
0x005700ac: 2b 01 40 39              ldrb     w11, [x9]
0x005700b0: 4a 05 00 51              sub      w10, w10, #1
0x005700b4: 5f 05 00 71              cmp      w10, #1
0x005700b8: 6b 01 08 4a              eor      w11, w11, w8
0x005700bc: 2b f5 1f 38              strb     w11, [x9], #0xffffffffffffffff
0x005700c0: 6c ff ff 54              b.gt     #0x5700ac
0x005700c4: f3 03 1f 2a              mov      w19, wzr
0x005700c8: e0 0b 40 f9              ldr      x0, [sp, #0x10]
0x005700cc: 40 f2 ff b5              cbnz     x0, #0x56ff14
0x005700d0: 93 ff ff 17              b        #0x56ff1c                  ; -> _ZN8CNamaSDK12BundleHelper24DecryptObfuscatedPackageEPKhiRNSt6__ndk16vectorIhNS3_9allocatorIhEEEE+0x1b4
0x005700d4: 80 41 00 d0              adrp     x0, #0xda2000
0x005700d8: 00 2c 47 f9              ldr      x0, [x0, #0xe58]           ; -> _ZGVZN8fuspdlog7details8registry8instanceEvE10s_instance@got
0x005700dc: 4d e0 1f 94              bl       #0xd68210                  ; -> __cxa_guard_acquire@plt
0x005700e0: 40 e7 ff 34              cbz      w0, #0x56fdc8
0x005700e4: 80 41 00 d0              adrp     x0, #0xda2000
0x005700e8: 00 30 47 f9              ldr      x0, [x0, #0xe60]           ; -> _ZZN8fuspdlog7details8registry8instanceEvE10s_instance@got
0x005700ec: 4d e0 1f 94              bl       #0xd68220                  ; -> _ZN8fuspdlog7details8registryC2Ev@plt
0x005700f0: 80 41 00 d0              adrp     x0, #0xda2000
0x005700f4: 81 41 00 d0              adrp     x1, #0xda2000
0x005700f8: 00 34 47 f9              ldr      x0, [x0, #0xe68]           ; -> _ZN8fuspdlog7details8registryD2Ev@got
0x005700fc: 21 30 47 f9              ldr      x1, [x1, #0xe60]           ; -> _ZZN8fuspdlog7details8registry8instanceEvE10s_instance@got
0x00570100: 62 40 00 f0              adrp     x2, #0xd7f000
0x00570104: 42 c0 23 91              add      x2, x2, #0x8f0
0x00570108: b2 df 1f 94              bl       #0xd67fd0                  ; -> __cxa_atexit@plt
0x0057010c: 80 41 00 d0              adrp     x0, #0xda2000
0x00570110: 00 2c 47 f9              ldr      x0, [x0, #0xe58]           ; -> _ZGVZN8fuspdlog7details8registry8instanceEvE10s_instance@got
0x00570114: 47 e0 1f 94              bl       #0xd68230                  ; -> __cxa_guard_release@plt
0x00570118: 2c ff ff 17              b        #0x56fdc8                  ; -> _ZN8CNamaSDK12BundleHelper24DecryptObfuscatedPackageEPKhiRNSt6__ndk16vectorIhNS3_9allocatorIhEEEE+0x60
0x0057011c: dd df 1f 94              bl       #0xd68090                  ; -> __stack_chk_fail@plt
0x00570120: f3 03 00 aa              mov      x19, x0
0x00570124: 80 41 00 d0              adrp     x0, #0xda2000
0x00570128: 00 2c 47 f9              ldr      x0, [x0, #0xe58]           ; -> _ZGVZN8fuspdlog7details8registry8instanceEvE10s_instance@got
0x0057012c: 45 e0 1f 94              bl       #0xd68240                  ; -> __cxa_guard_abort@plt
0x00570130: 07 00 00 14              b        #0x57014c                  ; -> _ZN8CNamaSDK12BundleHelper24DecryptObfuscatedPackageEPKhiRNSt6__ndk16vectorIhNS3_9allocatorIhEEEE+0x3e4
0x00570134: 03 00 00 14              b        #0x570140                  ; -> _ZN8CNamaSDK12BundleHelper24DecryptObfuscatedPackageEPKhiRNSt6__ndk16vectorIhNS3_9allocatorIhEEEE+0x3d8
0x00570138: 02 00 00 14              b        #0x570140                  ; -> _ZN8CNamaSDK12BundleHelper24DecryptObfuscatedPackageEPKhiRNSt6__ndk16vectorIhNS3_9allocatorIhEEEE+0x3d8
0x0057013c: 01 00 00 14              b        #0x570140                  ; -> _ZN8CNamaSDK12BundleHelper24DecryptObfuscatedPackageEPKhiRNSt6__ndk16vectorIhNS3_9allocatorIhEEEE+0x3d8
0x00570140: f3 03 00 aa              mov      x19, x0
0x00570144: e0 0b 40 f9              ldr      x0, [sp, #0x10]
0x00570148: 60 00 00 b5              cbnz     x0, #0x570154
0x0057014c: e0 03 13 aa              mov      x0, x19
0x00570150: e5 d7 1f 94              bl       #0xd660e4
0x00570154: e0 0f 00 f9              str      x0, [sp, #0x18]
0x00570158: aa df 1f 94              bl       #0xd68000                  ; -> _ZdlPv@plt
0x0057015c: e0 03 13 aa              mov      x0, x19
0x00570160: e1 d7 1f 94              bl       #0xd660e4
