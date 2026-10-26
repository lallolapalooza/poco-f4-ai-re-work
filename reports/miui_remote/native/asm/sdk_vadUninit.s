; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_remote/inventory/libs/lib/arm64-v8a/libmibrainsdk.so
; target  : vadUninit (0x1722c)
; start   : 0x1722c
; size    : 0x68
; bytes   : 0x68   instructions: 26
; generated: 2026-10-23T23:42:40

0x0001722c: fd 7b be a9              stp      x29, x30, [sp, #-0x20]!
0x00017230: fd 03 00 91              mov      x29, sp
0x00017234: f3 53 01 a9              stp      x19, x20, [sp, #0x10]
0x00017238: f3 03 00 aa              mov      x19, x0
0x0001723c: 00 00 40 f9              ldr      x0, [x0]
0x00017240: f2 a4 00 94              bl       #0x40608
0x00017244: 60 06 40 f9              ldr      x0, [x19, #8]
0x00017248: 32 ad 00 94              bl       #0x42710
0x0001724c: 01 05 00 b0              adrp     x1, #0xb8000
0x00017250: 20 00 80 52              mov      w0, #1
0x00017254: 21 40 2d 91              add      x1, x1, #0xb50
0x00017258: de cd ff 97              bl       #0xa9d0                    ; -> mi_brain_log@plt
0x0001725c: 74 06 40 f9              ldr      x20, [x19, #8]
0x00017260: b4 00 00 b4              cbz      x20, #0x17274
0x00017264: e0 03 14 aa              mov      x0, x20
0x00017268: 28 ad 00 94              bl       #0x42708
0x0001726c: e0 03 14 aa              mov      x0, x20
0x00017270: 30 ce ff 97              bl       #0xab30                    ; -> _ZdlPv@plt
0x00017274: 60 02 40 f9              ldr      x0, [x19]
0x00017278: 80 00 00 b4              cbz      x0, #0x17288
0x0001727c: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x00017280: fd 7b c2 a8              ldp      x29, x30, [sp], #0x20
0x00017284: 2b ce ff 17              b        #0xab30                    ; -> _ZdlPv@plt
0x00017288: f3 53 41 a9              ldp      x19, x20, [sp, #0x10]
0x0001728c: fd 7b c2 a8              ldp      x29, x30, [sp], #0x20
0x00017290: c0 03 5f d6              ret      
