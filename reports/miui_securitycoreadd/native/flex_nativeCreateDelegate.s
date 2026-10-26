; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_securitycoreadd/native/libtensorflowlite_flex_jni.so
; target  : Java_org_tensorflow_lite_flex_FlexDelegate_nativeCreateDelegate (0xcf204)
; start   : 0xcf204
; size    : 0x6c
; bytes   : 0x6c   instructions: 27
; generated: 2026-10-23T23:27:51

0x000cf204: ff 03 01 d1              sub      sp, sp, #0x40
0x000cf208: f3 13 00 f9              str      x19, [sp, #0x20]
0x000cf20c: fd 7b 03 a9              stp      x29, x30, [sp, #0x30]
0x000cf210: fd c3 00 91              add      x29, sp, #0x30
0x000cf214: 53 d0 3b d5              mrs      x19, tpidr_el0
0x000cf218: 68 16 40 f9              ldr      x8, [x19, #0x28]
0x000cf21c: e0 43 00 91              add      x0, sp, #0x10
0x000cf220: e8 0f 00 f9              str      x8, [sp, #0x18]
0x000cf224: e8 03 00 91              mov      x8, sp
0x000cf228: ff 0b 00 f9              str      xzr, [sp, #0x10]
0x000cf22c: 10 d6 03 94              bl       #0x1c4a6c
0x000cf230: e0 0b 40 f9              ldr      x0, [sp, #0x10]
0x000cf234: ff 0b 00 f9              str      xzr, [sp, #0x10]
0x000cf238: 80 00 00 b4              cbz      x0, #0xcf248
0x000cf23c: 08 00 40 f9              ldr      x8, [x0]
0x000cf240: 08 05 40 f9              ldr      x8, [x8, #8]
0x000cf244: 00 01 3f d6              blr      x8
0x000cf248: e0 03 40 f9              ldr      x0, [sp]
0x000cf24c: 68 16 40 f9              ldr      x8, [x19, #0x28]
0x000cf250: e9 0f 40 f9              ldr      x9, [sp, #0x18]
0x000cf254: 1f 01 09 eb              cmp      x8, x9
0x000cf258: a1 00 00 54              b.ne     #0xcf26c
0x000cf25c: fd 7b 43 a9              ldp      x29, x30, [sp, #0x30]
0x000cf260: f3 13 40 f9              ldr      x19, [sp, #0x20]
0x000cf264: ff 03 01 91              add      sp, sp, #0x40
0x000cf268: c0 03 5f d6              ret      
0x000cf26c: e5 d0 ff 97              bl       #0xc3600                   ; -> __stack_chk_fail@plt
