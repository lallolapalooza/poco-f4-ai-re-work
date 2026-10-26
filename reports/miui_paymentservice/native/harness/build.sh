#!/bin/bash
# Rebuild the qemu-aarch64 verification harness for libdigit_ocr.so.
# NOTE: this was used ONCE to capture the reference numbers in
# ../evidence/ and ../../reimpl/verify.txt.  All outputs are already saved;
# re-running is optional and needs the cross toolchain + qemu-user.
set -e
SYSROOT=/usr/aarch64-linux-gnu
HERE=$(cd "$(dirname "$0")" && pwd)
cd "$HERE"

# 1. the shipped library (extracted copy from tools/apk_inventory.py)
cp -f ../../inventory/libs/lib/arm64-v8a/libdigit_ocr.so .

# 2. bionic shim (__android_log_print, __errno, _ctype_, __sF + stdio for __sF)
aarch64-linux-gnu-gcc -shared -fPIC -O2 -o libs/liblog.so shim.c -Wl,-soname,liblog.so -ldl

# 3. guest libc from the cross sysroot
for f in libc.so.6 libm.so.6 libdl.so.2 libstdc++.so.6 libgcc_s.so.1; do cp -f $SYSROOT/lib/$f libs/; done
ln -sf libc.so.6 libs/libc.so; ln -sf libm.so.6 libs/libm.so
ln -sf libdl.so.2 libs/libdl.so; ln -sf libstdc++.so.6 libs/libstdc++.so

# 4. patch the *copy* so glibc's loader does not call the 9 NULL .init_array
#    slots (bionic skips them) nor run the iostream finalisers.
python3 - <<'PY'
import struct
d = bytearray(open('libdigit_ocr.so','rb').read())
off = 0xba4f0                     # .dynamic
for i in range(0, 496, 16):
    tag, val = struct.unpack('<QQ', d[off+i:off+i+16])
    if tag == 27: d[off+i+8:off+i+16] = struct.pack('<Q', 0x20)   # DT_INIT_ARRAYSZ: 4 real ctors
    if tag == 28: d[off+i+8:off+i+16] = struct.pack('<Q', 0)      # DT_FINI_ARRAYSZ: 0
    if tag == 0 and i: break
open('libs/libdigit_ocr.so','wb').write(d)
PY

# 5. harness
aarch64-linux-gnu-g++ -O1 -o harness harness.cpp -L. -ldigit_ocr -llog -lstdc++ -lm -lc \
    -Wl,--disable-new-dtags -Wl,-rpath,'$ORIGIN/libs' -Wl,--allow-shlib-undefined

# 6. run (example)
# qemu-aarch64 $SYSROOT/lib/ld-linux-aarch64.so.1 --library-path libs ./harness cls \
#      ../../models/Digital_OCR_svm_9.model ../../models/Digital_OCR_svm_2.model img20x32.bin 20 32
