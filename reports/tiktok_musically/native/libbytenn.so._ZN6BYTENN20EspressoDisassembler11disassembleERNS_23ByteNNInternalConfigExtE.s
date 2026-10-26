; disasm_elf.py (capstone 5.0.7)
; file    : /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_musically/native/libbytenn.so
; target  : _ZN6BYTENN20EspressoDisassembler11disassembleERNS_23ByteNNInternalConfigExtE (0x440e0)
; start   : 0x440e0
; size    : 0x10
; bytes   : 0x10   instructions: 4
; generated: 2026-10-23T23:28:22

0x000440e0: 08 08 40 f9              ldr      x8, [x0, #0x10]
0x000440e4: 48 00 00 b4              cbz      x8, #0x440ec
0x000440e8: 9a af ff 17              b        #0x2ff50                   ; -> _ZN6BYTENN20EspressoDisassembler22disassembleModelBufferERNS_23ByteNNInternalConfigExtE@plt
0x000440ec: ed b1 ff 17              b        #0x308a0                   ; -> _ZN6BYTENN20EspressoDisassembler17disassembleModelsERNS_23ByteNNInternalConfigExtE@plt
