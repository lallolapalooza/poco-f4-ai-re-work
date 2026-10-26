#!/usr/bin/env bash
# Reproduce the native EAI2 oracle: run the real libeai_floating.so under
# qemu-aarch64 and dump every tensor of every model.
#
#   ./run_harness.sh [model.eai ...]        (default: all 16 blobs)
#
# Output: ../native_dumps/<blob>.txt + <blob>_in0.bin + <blob>_Tnn.bin
#
# Why not dlopen: libeai_floating.so is an Android/bionic DSO that uses
# DT_RELR with the pre-standard tag numbers (0x6fffe000/1/3) and LIBC symbol
# versions; glibc's loader either refuses it (assertion `needed != NULL` after
# removing NEEDED, symbol versions otherwise unresolvable) or crashes while
# relocating.  eai_host.c therefore maps the DSO itself and applies
# RELR + RELA + JUMP_SLOT relocations, resolving the 13 imports
# (malloc/free/memcpy/memset/memmove, tanhf/expf/logf/powf/ldexpf,
# __android_log_print, __cxa_finalize, __stack_chk_fail) against glibc.
set -euo pipefail
HERE="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
WS="$(cd "$HERE/../../../.." && pwd)"
LIBSRC="$HERE/../../native/libs/libeai_floating.so"
OUT="$HERE/../native_dumps"
CC=aarch64-linux-gnu-gcc
QEMU=qemu-aarch64

cd "$HERE"
[ -f libeai_floating.orig.so ] || cp "$LIBSRC" libeai_floating.orig.so
$CC -O1 -static eai_host.c -o eai_host -lm

mkdir -p "$OUT"
models=("$@")
if [ ${#models[@]} -eq 0 ]; then
    models=("$HERE"/../../models/eai/*.eai)
fi
for f in "${models[@]}"; do
    n="$(basename "$f" .eai)"
    $QEMU ./eai_host libeai_floating.orig.so "$f" "$OUT/$n" > "$OUT/$n.txt" 2>&1 || true
    printf '%-32s %s\n' "$n" "$(grep -m1 'init rc' "$OUT/$n.txt" || true)"
done
echo "dumps in $OUT"

# Environment switches understood by eai_host:
#   EAI_STEPS=n          run n inference steps (per-step dumps <prefix>_s<k>_Tnn.bin)
#   EAI_FILL="17=1;18=0" fill a tensor's whole buffer (state experiments)
