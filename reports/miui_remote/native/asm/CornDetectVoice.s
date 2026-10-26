; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainvad2.so
; target  : CornDetectVoice (0x35fec)
; start   : 0x35fec
; size    : 0xe4
; bytes   : 0xe4   instructions: 57
; generated: 2026-10-23T23:30:10

0x00035fec: 08 24 40 b9              ldr      w8, [x0, #0x24]
0x00035ff0: 09 4c 40 b9              ldr      w9, [x0, #0x4c]
0x00035ff4: 08 01 09 6b              subs     w8, w8, w9
0x00035ff8: ad 06 00 54              b.le     #0x360cc
0x00035ffc: 09 20 40 b9              ldr      w9, [x0, #0x20]
0x00036000: 3f 01 08 6b              cmp      w9, w8
0x00036004: 2a 06 00 54              b.ge     #0x360c8
0x00036008: 0a 28 40 b9              ldr      w10, [x0, #0x28]
0x0003600c: 0b 50 40 b9              ldr      w11, [x0, #0x50]
0x00036010: 0c 08 40 b9              ldr      w12, [x0, #8]
0x00036014: 0c 01 00 34              cbz      w12, #0x36034
0x00036018: 5f 01 0b 6b              cmp      w10, w11
0x0003601c: 0d 02 00 54              b.le     #0x3605c
0x00036020: 0d 14 40 b9              ldr      w13, [x0, #0x14]
0x00036024: 1f 0c 00 b9              str      wzr, [x0, #0xc]
0x00036028: ad 05 00 11              add      w13, w13, #1
0x0003602c: 0d 14 00 b9              str      w13, [x0, #0x14]
0x00036030: 23 00 00 14              b        #0x360bc                   ; -> CornDetectVoice+0xd0
0x00036034: 5f 01 0b 6b              cmp      w10, w11
0x00036038: 0d 02 00 54              b.le     #0x36078
0x0003603c: 0d 10 40 b9              ldr      w13, [x0, #0x10]
0x00036040: 0c 54 40 b9              ldr      w12, [x0, #0x54]
0x00036044: bf 01 0c 6b              cmp      w13, w12
0x00036048: 0a 03 00 54              b.ge     #0x360a8
0x0003604c: ad 05 00 11              add      w13, w13, #1
0x00036050: ec 03 1f 2a              mov      w12, wzr
0x00036054: 0d 10 00 b9              str      w13, [x0, #0x10]
0x00036058: 19 00 00 14              b        #0x360bc                   ; -> CornDetectVoice+0xd0
0x0003605c: 0d 0c 40 b9              ldr      w13, [x0, #0xc]
0x00036060: 0e 58 40 b9              ldr      w14, [x0, #0x58]
0x00036064: bf 01 0e 6b              cmp      w13, w14
0x00036068: ea 00 00 54              b.ge     #0x36084
0x0003606c: ad 05 00 11              add      w13, w13, #1
0x00036070: 0d 0c 00 b9              str      w13, [x0, #0xc]
0x00036074: 12 00 00 14              b        #0x360bc                   ; -> CornDetectVoice+0xd0
0x00036078: ec 03 1f 2a              mov      w12, wzr
0x0003607c: 1f 10 00 b9              str      wzr, [x0, #0x10]
0x00036080: 0f 00 00 14              b        #0x360bc                   ; -> CornDetectVoice+0xd0
0x00036084: 0c 1c 40 b9              ldr      w12, [x0, #0x1c]
0x00036088: 8c 00 f8 37              tbnz     w12, #0x1f, #0x36098
0x0003608c: 0d 18 40 b9              ldr      w13, [x0, #0x18]
0x00036090: 9f 01 0d 6b              cmp      w12, w13
0x00036094: 4a 00 00 54              b.ge     #0x3609c
0x00036098: 09 1c 00 b9              str      w9, [x0, #0x1c]
0x0003609c: ec 03 1f 2a              mov      w12, wzr
0x000360a0: 1f 04 00 f9              str      xzr, [x0, #8]
0x000360a4: 06 00 00 14              b        #0x360bc                   ; -> CornDetectVoice+0xd0
0x000360a8: 2d 01 0c 4b              sub      w13, w9, w12
0x000360ac: 1f 30 02 29              stp      wzr, w12, [x0, #0x10]
0x000360b0: ec 03 00 32              mov      w12, #1
0x000360b4: 0d 18 00 b9              str      w13, [x0, #0x18]
0x000360b8: 0c 08 00 b9              str      w12, [x0, #8]
0x000360bc: 29 05 00 11              add      w9, w9, #1
0x000360c0: 3f 01 08 6b              cmp      w9, w8
0x000360c4: 8b fa ff 54              b.lt     #0x36014
0x000360c8: 08 20 00 b9              str      w8, [x0, #0x20]
0x000360cc: c0 03 5f d6              ret      
