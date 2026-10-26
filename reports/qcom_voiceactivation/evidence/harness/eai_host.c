/*
 * eai_host.c - self-contained arm64 host for libeai_floating.so
 *
 * Instead of dlopen() (which fails: the library is an Android bionic DSO with
 * DT_RELR using the pre-standard tag numbers and LIBC symbol versions), this
 * file mmaps the DSO, applies its own relocations and resolves its 13 imported
 * libc/libm symbols against the statically linked glibc.
 *
 * It then drives the recovered EAI API to produce reference ("golden") tensors
 * for the pure-python reimplementation:
 *
 *   eai_init_ex(&h, model, size, 0, NULL)
 *   eai_preapply(h)                       -> computes required buffer sizes
 *   eai_set_property(h, 0, {buf,sz})      -> activation/scratch pool
 *   eai_apply(h)                          -> relocate scratch+persistent tensors
 *   bind input tensors, eai_execute(h,&req,1)
 *
 * Usage: harness <model.eai> <out_prefix>
 * Output: text report on stdout + <prefix>_inN.bin / <prefix>_Tnn.bin dumps.
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <stdarg.h>
#include <math.h>
#include <sys/mman.h>
#include <elf.h>
#include <signal.h>
#include <ucontext.h>

/* ---- stubs for the bionic-only import -------------------------------- */
int __android_log_print(int prio, const char *tag, const char *fmt, ...)
{
    va_list ap;
    (void)prio; (void)tag;
    va_start(ap, fmt);
    fprintf(stderr, "[android_log] ");
    vfprintf(stderr, fmt, ap);
    va_end(ap);
    fprintf(stderr, "\n");
    return 0;
}
void __cxa_finalize(void *d) { (void)d; }

/* ---- symbol resolution ----------------------------------------------- */
struct stubent { const char *name; void *fn; };

static void *resolve_import(const char *name)
{
    static const struct stubent tab[] = {
        {"malloc", (void *)malloc}, {"free", (void *)free},
        {"memcpy", (void *)memcpy}, {"memset", (void *)memset},
        {"memmove", (void *)memmove},
        {"tanhf", (void *)tanhf}, {"expf", (void *)expf}, {"logf", (void *)logf},
        {"powf", (void *)powf}, {"ldexpf", (void *)ldexpf},
        {"__android_log_print", (void *)__android_log_print},
        {"__cxa_finalize", (void *)__cxa_finalize},
        {"__stack_chk_fail", (void *)abort},
    };
    for (size_t i = 0; i < sizeof tab / sizeof *tab; i++)
        if (!strcmp(tab[i].name, name)) return tab[i].fn;
    fprintf(stderr, "unresolved import: %s\n", name);
    exit(9);
}

/* ---- minimal ELF64 loader -------------------------------------------- */
struct loaded {
    uint8_t *base;
    Elf64_Sym *symtab;
    const char *strtab;
    uint32_t nsym;
    /* dynamic tables */
    uint64_t *relr; uint64_t relr_sz;
    Elf64_Rela *rela; uint64_t rela_sz;
    Elf64_Rela *jmprel; uint64_t jmprel_sz;
};

static void *load_so(const char *path, struct loaded *L)
{
    FILE *f = fopen(path, "rb");
    if (!f) { perror(path); exit(2); }
    fseek(f, 0, SEEK_END); long n = ftell(f); fseek(f, 0, SEEK_SET);
    uint8_t *img = malloc((size_t)n);
    if (fread(img, 1, (size_t)n, f) != (size_t)n) exit(2);
    fclose(f);
    Elf64_Ehdr *eh = (Elf64_Ehdr *)img;
    if (memcmp(eh->e_ident, ELFMAG, 4) || eh->e_machine != EM_AARCH64) {
        fprintf(stderr, "not an arm64 ELF\n"); exit(2);
    }
    Elf64_Phdr *ph = (Elf64_Phdr *)(img + eh->e_phoff);
    uint64_t maxend = 0;
    for (int i = 0; i < eh->e_phnum; i++)
        if (ph[i].p_type == PT_LOAD && ph[i].p_vaddr + ph[i].p_memsz > maxend)
            maxend = ph[i].p_vaddr + ph[i].p_memsz;
    size_t span = (size_t)maxend + 0x10000;
    uint8_t *base = mmap(NULL, span, PROT_READ | PROT_WRITE | PROT_EXEC,
                         MAP_PRIVATE | MAP_ANONYMOUS, -1, 0);
    if (base == MAP_FAILED) { perror("mmap"); exit(2); }
    memset(base, 0, span);
    L->base = base;
    for (int i = 0; i < eh->e_phnum; i++) {
        if (ph[i].p_type != PT_LOAD) continue;
        memcpy(base + ph[i].p_vaddr, img + ph[i].p_offset, (size_t)ph[i].p_filesz);
    }
    /* dynamic section */
    Elf64_Dyn *dyn = NULL;
    for (int i = 0; i < eh->e_phnum; i++)
        if (ph[i].p_type == PT_DYNAMIC) dyn = (Elf64_Dyn *)(img + ph[i].p_offset);
    if (!dyn) { fprintf(stderr, "no PT_DYNAMIC\n"); exit(2); }
    uint64_t d_hash = 0, d_strtab = 0, d_symtab = 0, d_strsz = 0, d_syment = 24;
    L->relr = NULL; L->relr_sz = 0; L->rela = NULL; L->rela_sz = 0;
    L->jmprel = NULL; L->jmprel_sz = 0;
    for (Elf64_Dyn *d = dyn; d->d_tag != DT_NULL; d++) {
        switch (d->d_tag) {
        case DT_HASH: d_hash = d->d_un.d_ptr; break;
        case DT_STRTAB: d_strtab = d->d_un.d_ptr; break;
        case DT_SYMTAB: d_symtab = d->d_un.d_ptr; break;
        case DT_STRSZ: d_strsz = d->d_un.d_val; break;
        case DT_SYMENT: d_syment = d->d_un.d_val; break;
        case DT_RELA: L->rela = (Elf64_Rela *)(base + d->d_un.d_ptr); break;
        case DT_RELASZ: L->rela_sz = d->d_un.d_val; break;
        case DT_JMPREL: L->jmprel = (Elf64_Rela *)(base + d->d_un.d_ptr); break;
        case DT_PLTRELSZ: L->jmprel_sz = d->d_un.d_val; break;
        case 36: case 0x6fffe000:                                       /* DT_RELR */
            L->relr = (uint64_t *)(base + d->d_un.d_ptr); break;
        case 35: case 0x6fffe001:                                       /* DT_RELRSZ */
            L->relr_sz = d->d_un.d_val; break;
        default: break;
        }
    }
    (void)d_strsz; (void)d_syment;
    if (!d_hash || !d_strtab || !d_symtab) { fprintf(stderr, "missing dynsym info\n"); exit(2); }
    /* SysV hash: nbucket, nchain -> nchain == number of dynsym entries */
    uint32_t *hash = (uint32_t *)(base + d_hash);
    L->nsym = hash[1];
    L->symtab = (Elf64_Sym *)(base + d_symtab);
    L->strtab = (const char *)(base + d_strtab);
    return base;
}

static void apply_relocs(struct loaded *L)
{
    uint8_t *base = L->base;
    /* RELR (image-relative words) */
    if (L->relr) {
        uint64_t *addr = NULL;
        for (uint64_t i = 0; i < L->relr_sz / 8; i++) {
            uint64_t e = L->relr[i];
            if ((e & 1) == 0) {
                addr = (uint64_t *)(base + e);
                *addr++ += (uint64_t)(uintptr_t)base;
            } else {
                for (int j = 1; j < 64; j++) {
                    if (e & (1ull << j)) *addr += (uint64_t)(uintptr_t)base;
                    addr++;
                }
            }
        }
    }
    Elf64_Rela *sets[2] = { L->rela, L->jmprel };
    uint64_t sizes[2] = { L->rela_sz, L->jmprel_sz };
    for (int s = 0; s < 2; s++) {
        if (!sets[s]) continue;
        for (uint64_t i = 0; i < sizes[s] / sizeof(Elf64_Rela); i++) {
            Elf64_Rela *r = &sets[s][i];
            uint32_t type = (uint32_t)(r->r_info & 0xffffffff);
            uint32_t symi = (uint32_t)(r->r_info >> 32);
            uint64_t *where = (uint64_t *)(base + r->r_offset);
            uint64_t val = 0;
            switch (type) {
            case R_AARCH64_RELATIVE:
                val = (uint64_t)(uintptr_t)base + (uint64_t)r->r_addend;
                break;
            case R_AARCH64_ABS64:
            case R_AARCH64_GLOB_DAT:
            case R_AARCH64_JUMP_SLOT: {
                Elf64_Sym *sy = &L->symtab[symi];
                const char *nm = L->strtab + sy->st_name;
                if (sy->st_shndx == SHN_UNDEF) val = (uint64_t)(uintptr_t)resolve_import(nm);
                else val = (uint64_t)(uintptr_t)(base + sy->st_value);
                val += (uint64_t)r->r_addend;
                break;
            }
            default:
                fprintf(stderr, "reloc type %u at %llx not handled\n", type,
                        (unsigned long long)r->r_offset);
                exit(9);
            }
            *where = val;
        }
    }
}

static void *sym_addr(struct loaded *L, const char *name)
{
    for (uint32_t i = 0; i < L->nsym; i++) {
        if (L->symtab[i].st_shndx == SHN_UNDEF) continue;
        if (!strcmp(L->strtab + L->symtab[i].st_name, name))
            return L->base + L->symtab[i].st_value;
    }
    return NULL;
}

/* ---- EAI context / model structures ---------------------------------- */
struct binding { uint32_t index; uint32_t pad; uint64_t ptr; uint64_t size; };
struct request {
    struct binding *in; uint32_t n_in; uint32_t pad0;
    struct binding *out; uint32_t n_out; uint32_t pad1;
};
struct tensor_desc {
    uint64_t dims_ptr;
    uint32_t flags;
    uint32_t ndim;
    uint32_t rsvd;
    uint32_t magic;
    uint32_t dims[5];
    uint32_t data_off;
    uint64_t data_ptr;
    uint32_t nbytes;
    uint32_t dtype;
    uint32_t extra;
    uint32_t one;
    uint32_t tail[2];
};

#define CTX_MODEL     0x08
#define CTX_TENSORS   0x18
#define CTX_LAYERLIST 0xa8
#define CTX_IN_PTRS   0x80
#define CTX_IN_N      0x90
#define CTX_OUT_PTRS  0x88
#define CTX_OUT_N     0x94
#define CTX_FLAGS     0x98

static uint8_t *read_file(const char *path, size_t *len)
{
    FILE *f = fopen(path, "rb");
    if (!f) { perror(path); exit(2); }
    fseek(f, 0, SEEK_END); long n = ftell(f); fseek(f, 0, SEEK_SET);
    uint8_t *buf = aligned_alloc(4096, (size_t)n + 4096);
    memset(buf, 0, (size_t)n + 4096);
    if (fread(buf, 1, (size_t)n, f) != (size_t)n) exit(2);
    fclose(f);
    *len = (size_t)n;
    return buf;
}

static float pattern(uint32_t tid, uint32_t i)
{
    return (float)(sin((double)i * 0.017 + (double)tid * 1.7) * 0.9);
}

static uintptr_t g_base;
static void segv(int sig, siginfo_t *si, void *uc_)
{
    (void)sig;
    ucontext_t *uc = (ucontext_t *)uc_;
    fprintf(stderr, "SIGSEGV addr=%p pc=%p (lib+0x%llx) lr=%p (lib+0x%llx)\n",
            si->si_addr, (void *)uc->uc_mcontext.pc,
            (unsigned long long)(uc->uc_mcontext.pc - g_base),
            (void *)uc->uc_mcontext.regs[30],
            (unsigned long long)(uc->uc_mcontext.regs[30] - g_base));
    exit(11);
}

int main(int argc, char **argv)
{
    setvbuf(stdout, NULL, _IONBF, 0);
    struct sigaction sa; memset(&sa, 0, sizeof sa);
    sa.sa_sigaction = segv; sa.sa_flags = SA_SIGINFO;
    sigaction(SIGSEGV, &sa, NULL);
    sigaction(SIGBUS, &sa, NULL);
    if (argc < 4) {
        fprintf(stderr, "usage: %s libeai.so model.eai out_prefix\n", argv[0]);
        return 2;
    }
    struct loaded L;
    load_so(argv[1], &L);
    g_base = (uintptr_t)L.base;
    apply_relocs(&L);

    typedef int (*init_ex_fn)(void **, void *, int, unsigned, void *);
    typedef int (*void_h_fn)(void *);
    typedef int (*prop_fn)(void *, int, void *);
    typedef int (*exec_fn)(void *, void *, int);
    init_ex_fn eai_init_ex = (init_ex_fn)sym_addr(&L, "eai_init_ex");
    void_h_fn eai_preapply = (void_h_fn)sym_addr(&L, "eai_preapply");
    void_h_fn eai_apply = (void_h_fn)sym_addr(&L, "eai_apply");
    prop_fn eai_set_property = (prop_fn)sym_addr(&L, "eai_set_property");
    exec_fn eai_execute = (exec_fn)sym_addr(&L, "eai_execute");
    if (!eai_init_ex || !eai_preapply || !eai_apply || !eai_set_property || !eai_execute) {
        fprintf(stderr, "export lookup failed\n");
        return 3;
    }

    size_t mlen;
    uint8_t *model = read_file(argv[2], &mlen);
    const char *prefix = argv[3];

    void *h = NULL;
    int rc = eai_init_ex(&h, model, (int)mlen, 0, NULL);
    printf("init rc=%d handle=%p\n", rc, h);
    if (rc != 0 || !h) return 4;

    uint8_t *ctx = (uint8_t *)h;
    uint8_t *mb = *(uint8_t **)(ctx + CTX_MODEL);
    uint32_t nsect = *(uint32_t *)(mb + 0x1c);
    printf("model=%s size=%zu hdr=%u sections=%u ver=%u.%u scratch_req=%u persist_req=%u\n",
           argv[2], mlen, *(uint32_t *)(mb + 0x10), nsect,
           *(uint32_t *)(mb + 4) >> 16, *(uint32_t *)(mb + 4) & 0xffff,
           *(uint32_t *)(mb + 0x14), *(uint32_t *)(mb + 0x18));
    for (uint32_t i = 0; i < nsect; i++) {
        uint32_t *s = (uint32_t *)(mb + 0x28 + 16 * i);
        printf("section[%u] type=%u size=%u off=%u\n", i, s[0], s[1], s[2]);
    }

    rc = eai_preapply(h);
    printf("preapply rc=%d\n", rc);
    uint32_t scratch_size = *(uint32_t *)(mb + 0x14) + 4096;
    uint8_t *scratch = aligned_alloc(64, scratch_size);
    memset(scratch, 0, scratch_size);
    struct { uint64_t ptr; uint32_t size; uint32_t rsvd; } prop0;
    prop0.ptr = (uint64_t)(uintptr_t)scratch;
    prop0.size = scratch_size;
    prop0.rsvd = 0;
    rc = eai_set_property(h, 0, &prop0);
    printf("set_property(0) rc=%d scratch=%u\n", rc, scratch_size);
    rc = eai_apply(h);
    printf("apply rc=%d flags=0x%08x\n", rc, *(uint32_t *)(ctx + CTX_FLAGS));
    if (rc != 0) return 5;

    uint32_t n_in = *(uint32_t *)(ctx + CTX_IN_N);
    uint32_t n_out = *(uint32_t *)(ctx + CTX_OUT_N);
    uint64_t *in_t = *(uint64_t **)(ctx + CTX_IN_PTRS);
    uint64_t *out_t = *(uint64_t **)(ctx + CTX_OUT_PTRS);
    printf("io n_in=%u n_out=%u\n", n_in, n_out);

    uint64_t tens_base = *(uint64_t *)(ctx + CTX_TENSORS);
    uint32_t n_tensors = *(uint32_t *)tens_base;
    printf("tensors=%u\n", n_tensors);
    struct tensor_desc *td = (struct tensor_desc *)(tens_base + 0x20);
    for (uint32_t i = 0; i < n_tensors; i++) {
        struct tensor_desc *t = &td[i];
        printf("T%02u ndim=%u dims=[", i, t->ndim & 0xf);
        for (uint32_t d = 0; d < (t->ndim & 0xf); d++)
            printf("%s%u", d ? "," : "", t->dims[d]);
        printf("] bytes=%u dtype=0x%x off=%d flags=0x%08x magic=0x%x",
               t->nbytes, t->dtype, (int32_t)t->data_off, t->flags, t->magic);
        for (uint32_t k = 0; k < n_in; k++) if (in_t[k] == (uint64_t)(uintptr_t)t) printf(" IN%u", k);
        for (uint32_t k = 0; k < n_out; k++) if (out_t[k] == (uint64_t)(uintptr_t)t) printf(" OUT%u", k);
        printf("\n");
    }

    uint64_t layers = *(uint64_t *)(ctx + CTX_LAYERLIST);
    uint32_t n_layers = *(uint32_t *)layers;
    printf("layers=%u\n", n_layers);
    for (uint32_t i = 0; i < n_layers; i++) {
        uint8_t *l = *(uint8_t **)(layers + 8 + 8 * i);
        uint32_t type = *(uint32_t *)l;
        uint32_t f2 = *(uint32_t *)(l + 8);
        uint16_t nin = *(uint16_t *)(l + 0xc);
        uint16_t nout = *(uint16_t *)(l + 0xe);
        uint64_t *pin = *(uint64_t **)(l + 0x40);
        uint64_t *pout = *(uint64_t **)(l + 0x48);
        printf("L%02u type=%u flags=0x%08x in=%u out=%u in_ids=", i, type, f2, nin, nout);
        for (int k = 0; k < nin; k++) {
            uint64_t p = pin[k];
            int idx = -1;
            for (uint32_t t = 0; t < n_tensors; t++)
                if ((uint64_t)(uintptr_t)&td[t] == p) idx = (int)t;
            printf("%d,", idx);
        }
        printf(" out_ids=");
        for (int k = 0; k < nout; k++) {
            uint64_t p = pout[k];
            int idx = -1;
            for (uint32_t t = 0; t < n_tensors; t++)
                if ((uint64_t)(uintptr_t)&td[t] == p) idx = (int)t;
            printf("%d,", idx);
        }
        printf("\n");
    }

    struct binding *bi = calloc(n_in ? n_in : 1, sizeof *bi);
    struct binding *bo = calloc(n_out ? n_out : 1, sizeof *bo);
    float *buf = calloc(1, 4u << 20);
    for (uint32_t k = 0; k < n_in; k++) {
        struct tensor_desc *t = (struct tensor_desc *)in_t[k];
        uint32_t n = t->nbytes / 4;
        for (uint32_t i = 0; i < n; i++) buf[i] = pattern(k, i);
        memcpy((void *)(uintptr_t)t->data_ptr, buf, t->nbytes);
        bi[k].index = k; bi[k].ptr = t->data_ptr; bi[k].size = t->nbytes;
        printf("input[%u] elems=%u head=%g,%g,%g\n", k, n, buf[0], n > 1 ? buf[1] : 0.f,
               n > 2 ? buf[2] : 0.f);
        char p[512];
        snprintf(p, sizeof p, "%s_in%u.bin", prefix, k);
        FILE *fp = fopen(p, "wb");
        fwrite((void *)(uintptr_t)t->data_ptr, 1, t->nbytes, fp);
        fclose(fp);
    }
    for (uint32_t k = 0; k < n_out; k++) {
        struct tensor_desc *t = (struct tensor_desc *)out_t[k];
        bo[k].index = k; bo[k].ptr = (uint64_t)(uintptr_t)buf + 2u * 1024 * 1024; bo[k].size = t->nbytes;
    }
    /* optional state injection: EAI_FILL="tid=value;tid=value" (fills whole tensor) */
    const char *fill = getenv("EAI_FILL");
    if (fill) {
        char tmp[512];
        snprintf(tmp, sizeof tmp, "%s", fill);
        for (char *tok = strtok(tmp, ";"); tok; tok = strtok(NULL, ";")) {
            unsigned tid; float val;
            if (sscanf(tok, "%u=%f", &tid, &val) == 2 && tid < n_tensors) {
                struct tensor_desc *t = &td[tid];
                float *fp = (float *)(uintptr_t)t->data_ptr;
                for (uint32_t i = 0; i < t->nbytes / 4; i++) fp[i] = val;
                printf("fill T%02u = %g (%u elems)\n", tid, val, t->nbytes / 4);
            }
        }
    }
    int steps = 1;
    const char *st = getenv("EAI_STEPS");
    if (st) steps = atoi(st);
    if (steps < 1) steps = 1;
    struct request req = { bi, n_in, 0, bo, n_out, 0 };
    for (int step = 0; step < steps; step++) {
        if (step) {
            for (uint32_t k = 0; k < n_in; k++) {
                struct tensor_desc *t = (struct tensor_desc *)in_t[k];
                uint32_t n = t->nbytes / 4;
                for (uint32_t i = 0; i < n; i++) buf[i] = pattern(k + step * 7, i);
                memcpy((void *)(uintptr_t)t->data_ptr, buf, t->nbytes);
            }
        }
        rc = eai_execute(h, &req, 1);
        printf("execute step=%d rc=%d\n", step, rc);
        if (steps > 1) {
            for (uint32_t i = 0; i < n_tensors; i++) {
                struct tensor_desc *t = &td[i];
                if (!t->data_ptr) continue;
                char p[512];
                snprintf(p, sizeof p, "%s_s%d_T%02u.bin", prefix, step, i);
                FILE *f = fopen(p, "wb");
                fwrite((void *)(uintptr_t)t->data_ptr, 1, t->nbytes, f);
                fclose(f);
            }
            printf("step %d: out0 head", step);
            float *of = (float *)(uintptr_t)bo[0].ptr;
            for (uint32_t k = 0; k < 6 && k < bo[0].size / 4; k++) printf(" %.7g", of[k]);
            printf("\n");
        }
    }

    for (uint32_t i = 0; i < n_tensors; i++) {
        struct tensor_desc *t = &td[i];
        if (!t->data_ptr) { printf("T%02u data=<null>\n", i); continue; }
        float *fp = (float *)(uintptr_t)t->data_ptr;
        uint32_t n = t->nbytes / 4;
        printf("T%02u first8:", i);
        for (uint32_t k = 0; k < (n < 8 ? n : 8); k++) printf(" %.8g", fp[k]);
        printf("\n");
        char p[512];
        snprintf(p, sizeof p, "%s_T%02u.bin", prefix, i);
        FILE *f = fopen(p, "wb");
        fwrite((void *)(uintptr_t)t->data_ptr, 1, t->nbytes, f);
        fclose(f);
    }
    printf("done\n");
    return 0;
}
