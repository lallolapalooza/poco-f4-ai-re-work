// qemu-aarch64 harness for Android arm64 libdigit_ocr.so (Xiaomi MiPay digit OCR).
// Modes:
//   svmtext <model>                        : svm_load_cvt_model header dump
//   svm     <model> <featfile>             : predict per line (144 floats)
//   hog     <imgfile> <w> <h>              : CFEATURE_HOG::ExtractGradientOrientation
//   cls     <m9> <m2> <imgfile> <w> <h>    : HOG + 10-class probs + neg/pos
//   pipeline <m9> <m2> <imgfile> <w> <h> <fmt> : full MainOCR (segment+recognise)
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <cstdint>
#include <vector>
#include <cmath>

typedef struct { int index; double value; } svm_node_t;   // 16 B, matches libsvm LP64

extern "C" {
void *svm_load_model(const char *) __asm__("svm_load_model");
void *svm_load_cvt_model(const char *) __asm__("svm_load_cvt_model");
double svm_predict_probability(void *, const svm_node_t *, double *) __asm__("svm_predict_probability");
double svm_predict(void *, const svm_node_t *) __asm__("svm_predict");
double svm_predict_values(void *, const svm_node_t *, double *) __asm__("svm_predict_values");
int svm_get_nr_class(const void *) __asm__("svm_get_nr_class");
void svm_get_labels(const void *, int *) __asm__("svm_get_labels");
int svm_check_probability_model(const void *) __asm__("svm_check_probability_model");
int svm_get_svm_type(const void *) __asm__("svm_get_svm_type");

int hog_extract(void *self, unsigned char *img, int w, int h, void *vec)
    __asm__("_ZN12CFEATURE_HOG26ExtractGradientOrientationEPhiiRSt6vectorIfSaIfEE");
int cdo_predict_negpos(void *self, unsigned char *img, int w, int h, int *out)
    __asm__("_ZN11CDigitalOCR13PredictNegPosEPhiiRi");

void mainocr_ctor(void *) __asm__("_ZN7MainOCRC1Ev");
int mainocr_begin(void *, const char *, const char *) __asm__("_ZN7MainOCR12beginProcessEPKcS1_");
int mainocr_setimage(void *, unsigned char *, int, int, int) __asm__("_ZN7MainOCR8setImageEPhiii");
int mainocr_process(void *, unsigned char *, int, int, int) __asm__("_ZN7MainOCR14process_OneImgEPhiii");
void mainocr_recog(void *) __asm__("_ZN7MainOCR13recogdigitOCREv");
int mainocr_nresult(void *) __asm__("_ZN7MainOCR17getDigitResultNumEv");
struct FakeStr { void *p; unsigned long len; union { char buf[16]; unsigned long cap; }; };
FakeStr mainocr_resultstr(void *) __asm__("_ZN7MainOCR26getRecognitionResultStringEv");
FakeStr mainocr_beststr(void *) __asm__("_ZN7MainOCR21getBestFrameResultStrEv");
void mainocr_clear(void *) __asm__("_ZN7MainOCR16clearResultQueueEv");
void mainocr_setrange(void *, int, int) __asm__("_ZN7MainOCR19setDigitNumberRangeEii");
void mainocr_setcammode(void *, int) __asm__("_ZN7MainOCR13setCameraModeEi");
void mainocr_setconf(void *) __asm__("_ZN7MainOCR21setAllWordAverageConfEv");
void *digitmat_buffer(void *) __asm__("_ZN22DigitSegmentManagement17getDigitMatBufferEv");
}

static std::vector<unsigned char> read_file(const char *p) {
    FILE *f = fopen(p, "rb");
    if (!f) { fprintf(stderr, "cannot open %s\n", p); exit(2); }
    fseek(f, 0, SEEK_END); long n = ftell(f); fseek(f, 0, SEEK_SET);
    std::vector<unsigned char> d(n);
    if (n && fread(d.data(), 1, n, f) != (size_t)n) { fprintf(stderr, "short read\n"); exit(2); }
    fclose(f);
    return d;
}
static const char *cxx_str_data(void *s) { return *(char **)s; }   // libstdc++ old ABI

static int run_hog(const char *path, int w, int h, float **out, size_t *n) {
    std::vector<unsigned char> img = read_file(path);
    if ((int)img.size() < w * h) { fprintf(stderr, "img too small\n"); exit(2); }
    unsigned char self[64] = {0};
    struct Vec { void *b, *e, *cap; } vec = {nullptr, nullptr, nullptr};
    int rc = hog_extract(self, img.data(), w, h, &vec);
    if (rc != 0) { fprintf(stderr, "hog rc=%d (need 20x32)\n", rc); exit(3); }
    *out = (float *)vec.b;
    *n = (float *)vec.e - (float *)vec.b;
    return 0;
}

int main(int argc, char **argv) {
    setvbuf(stdout, NULL, _IONBF, 0);
    if (argc < 3) { fprintf(stderr, "see header\n"); return 2; }
    const char *mode = argv[1];

    if (!strcmp(mode, "svmtext")) {
        void *m = svm_load_cvt_model(argv[2]);
        printf("model=%p nr_class=%d svm_type=%d prob_model=%d\n", m,
               m ? svm_get_nr_class(m) : -1, m ? svm_get_svm_type(m) : -1,
               m ? svm_check_probability_model(m) : -1);
        return 0;
    }

    if (!strcmp(mode, "svm")) {
        void *m = svm_load_cvt_model(argv[2]);
        if (!m) { fprintf(stderr, "load failed\n"); return 3; }
        int nr = svm_get_nr_class(m);
        std::vector<int> lab(nr, 0); svm_get_labels(m, lab.data());
        FILE *f = fopen(argv[3], "r");
        if (!f) { fprintf(stderr, "no featfile\n"); return 2; }
        char line[16384];
        std::vector<double> prob(nr, 0.0);
        std::vector<svm_node_t> nodes(145);
        while (fgets(line, sizeof line, f)) {
            int n = 0; char *p = line;
            for (int i = 0; i < 144; i++) {
                char *e = nullptr; double v = strtod(p, &e);
                if (e == p) break;
                nodes[n].index = i + 1; nodes[n].value = v; n++; p = e;
            }
            if (n == 0) continue;
            nodes[n].index = -1; nodes[n].value = 0.0;
            double lab_pred = svm_predict(m, nodes.data());
            svm_predict_probability(m, nodes.data(), prob.data());
            printf("label=%.0f |", lab_pred);
            for (int i = 0; i < nr; i++) printf(" %.10f", prob[i]);
            printf("\n");
        }
        fclose(f);
        return 0;
    }

    if (!strcmp(mode, "dec")) {
        void *m = svm_load_cvt_model(argv[2]);
        if (!m) { fprintf(stderr, "load failed\n"); return 3; }
        int nr = svm_get_nr_class(m);
        int npair = nr * (nr - 1) / 2;
        float *feat; size_t n;
        run_hog(argv[3], atoi(argv[4]), atoi(argv[5]), &feat, &n);
        std::vector<svm_node_t> nodes(145);
        for (size_t i = 0; i < n; i++) { nodes[i].index = (int)i + 1; nodes[i].value = (double)feat[i]; }
        nodes[n].index = -1; nodes[n].value = 0.0;
        std::vector<double> dec(npair + 1, 0.0);
        double lab = svm_predict_values(m, nodes.data(), dec.data());
        printf("label=%.0f dec:", lab);
        for (int i = 0; i < npair; i++) printf(" %.10f", dec[i]);
        printf("\n");
        return 0;
    }

    if (!strcmp(mode, "hog")) {
        float *feat; size_t n;
        run_hog(argv[2], atoi(argv[3]), atoi(argv[4]), &feat, &n);
        printf("n=%zu\n", n);
        for (size_t i = 0; i < n; i++) printf("%.6f%s", feat[i], (i + 1) % 16 ? " " : "\n");
        printf("\n");
        return 0;
    }

    if (!strcmp(mode, "cls")) {
        void *m9 = svm_load_cvt_model(argv[2]);
        void *m2 = svm_load_cvt_model(argv[3]);
        float *feat; size_t n;
        run_hog(argv[4], atoi(argv[5]), atoi(argv[6]), &feat, &n);
        std::vector<svm_node_t> nodes(145);
        for (size_t i = 0; i < n; i++) { nodes[i].index = (int)i + 1; nodes[i].value = (double)feat[i]; }
        nodes[n].index = -1; nodes[n].value = 0.0;
        double p9[10], p2[2];
        double l9 = svm_predict_probability(m9, nodes.data(), p9);
        double l2 = svm_predict_probability(m2, nodes.data(), p2);
        printf("feat:");
        for (size_t i = 0; i < n; i++) printf(" %.17g", feat[i]);
        printf("\nprobs:");
        for (int i = 0; i < 10; i++) printf(" %.10f", p9[i]);
        printf("\nlabel9=%.0f negpos_prob0=%.10f prob1=%.10f label2=%.0f\n", l9, p2[0], p2[1], l2);
        return 0;
    }

    if (!strcmp(mode, "pipeline")) {
        int w = atoi(argv[5]), h = atoi(argv[6]), fmt = atoi(argv[7]);
        std::vector<unsigned char> img = read_file(argv[4]);
        void *self = calloc(1, 0x4000);
        mainocr_ctor(self);
        int ok = mainocr_begin(self, argv[2], argv[3]);
        printf("beginProcess=%d (img %dx%d fmt=%d, bytes=%zu)\n", ok, w, h, fmt, img.size());
        if (!ok) return 3;
        mainocr_setcammode(self, 0);
        printf("setImage=%d\n", mainocr_setimage(self, img.data(), w, h, fmt));
        int pr = mainocr_process(self, img.data(), w, h, fmt);
        printf("process_OneImg=%d\n", pr);
        mainocr_recog(self);
        int nd = mainocr_nresult(self);
        printf("digits=%d\n", nd);
        printf("digit_array:");
        for (int i = 0; i < nd && i < 32; i++) printf(" %d", ((int *)self)[0x7d8 / 4 + i]);
        printf("\n");
        {   // dump the vendor-normalised 20x32 patches (vector<_mapbuffer>, 16 B each)
            struct Vec { void *b, *e, *cap; };
            Vec *v = (Vec *)digitmat_buffer(self);
            long n = v ? ((char *)v->e - (char *)v->b) / 16 : 0;
            printf("patches=%ld\n", n);
            for (long i = 0; i < n; i++) {
                unsigned char *el = (unsigned char *)v->b + i * 16;
                unsigned char *data = *(unsigned char **)el;
                int pw = *(int *)(el + 8), ph = *(int *)(el + 12);
                printf("patch %ld ptr=%p w=%d h=%d\n", i, (void *)data, pw, ph);
                if (data) {
                    FILE *pf = fopen(argv[8] ? argv[8] : "/tmp/patch.bin", "ab");
                    if (pf) { fwrite(data, 1, 640, pf); fclose(pf); }
                }
            }
        }
        FakeStr r = mainocr_resultstr(self);
        printf("getRecognitionResultString=%.*s (len=%lu)\n", (int)r.len, (char *)r.p, r.len);
        FakeStr b = mainocr_beststr(self);
        printf("getBestFrameResultStr=%.*s (len=%lu)\n", (int)b.len, (char *)b.p, b.len);
        return 0;
    }

    fprintf(stderr, "unknown mode\n");
    return 2;
}
