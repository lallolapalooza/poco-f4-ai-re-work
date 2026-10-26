/* Minimal bionic-compat shim so the Android arm64 libdigit_ocr.so can be
 * loaded by qemu-aarch64 + glibc for numeric verification.
 *
 * Provides the bionic-only symbols the library imports:
 *   __android_log_print, __errno, _ctype_, __sF
 * and interposes the stdio entry points that libstdc++'s iostream statics use
 * on top of bionic's __sF[] (Android's stdout/stderr are __sF+1 / __sF+2).
 * Writes to those fake FILE objects are discarded; every real FILE* is
 * forwarded to glibc via dlsym(RTLD_NEXT,...).
 */
#include <dlfcn.h>
#include <errno.h>
#include <stdarg.h>
#include <stddef.h>
#include <stdio.h>

int __android_log_print(int prio, const char *tag, const char *fmt, ...) {
    (void)prio; (void)tag; (void)fmt;
    return 0;
}

int *__errno(void) { return __errno_location(); }

/* bionic: extern const char _ctype_[1 + 256]; index c+1.
 * bit layout: _U 0x01 _L 0x02 _D 0x04 _S 0x08 _P 0x10 _C 0x20 _X 0x40 _B 0x80 */
char _ctype_[1 + 256];

__attribute__((constructor)) static void build_ctype(void) {
    _ctype_[0] = 0;
    for (int c = 0; c < 256; c++) {
        unsigned char v = 0;
        if (c >= 'A' && c <= 'Z') v |= 0x01;
        if (c >= 'a' && c <= 'z') v |= 0x02;
        if (c >= '0' && c <= '9') v |= 0x04;
        if (c == ' ' || (c >= 0x09 && c <= 0x0d)) v |= 0x08;
        if ((c > 0x20 && c < 0x7f) && !(v & 0x07)) v |= 0x10;
        if ((c < 0x20 && c != 0) || c == 0x7f) v |= 0x20;
        if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F')) v |= 0x40;
        if (c == ' ' || c == '\t') v |= 0x80;
        _ctype_[1 + c] = (char)v;
    }
}

/* bionic __sF[]: array of FILE objects for stdin/stdout/stderr. */
char __sF[4096];

static int is_fake(FILE *f) {
    return (char *)f >= __sF && (char *)f < __sF + sizeof(__sF);
}

#define NEXT(name) ((__typeof__(&name))dlsym(RTLD_NEXT, #name))

size_t fwrite(const void *p, size_t sz, size_t n, FILE *f) {
    if (is_fake(f)) return sz * n;
    return NEXT(fwrite)(p, sz, n, f);
}
int fputc(int c, FILE *f) { if (is_fake(f)) return c; return NEXT(fputc)(c, f); }
int putc(int c, FILE *f) { if (is_fake(f)) return c; return NEXT(putc)(c, f); }
int fputs(const char *s, FILE *f) { if (is_fake(f)) return 0; return NEXT(fputs)(s, f); }
int fprintf(FILE *f, const char *fmt, ...) {
    if (is_fake(f)) return 0;
    va_list ap; va_start(ap, fmt);
    int r = vfprintf(f, fmt, ap);
    va_end(ap);
    return r;
}
int fflush(FILE *f) { if (f && is_fake(f)) return 0; return NEXT(fflush)(f); }
int fclose(FILE *f) { if (is_fake(f)) return 0; return NEXT(fclose)(f); }
int ferror(FILE *f) { if (is_fake(f)) return 0; return NEXT(ferror)(f); }
