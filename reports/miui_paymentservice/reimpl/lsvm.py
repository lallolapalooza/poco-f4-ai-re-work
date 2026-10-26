"""Parser + numpy reference implementation for the LIBSVM text models shipped in
PaymentService_Global.apk (assets/Digital_OCR_svm_2.model, _9.model).

Format (confirmed): standard libsvm `svm_save_model` text dump with CRLF line
endings.  Header keys: svm_type, kernel_type, degree, gamma, coef0, nr_class,
total_sv, rho, label, probA, probB, nr_sv; then a line "SV" followed by
total_sv support vectors "<coef_1> ... <coef_k> idx:val idx:val ..."
where k = nr_class-1 (one-vs-one) and the coefficients are in libsvm's
pairwise (i<j) order.

Everything here was cross-checked against the vendor's own libsvm build
(libdigit_ocr.so, svm_load_cvt_model + svm_predict_probability) under
qemu-aarch64; see reimpl/verify.txt.
"""
import numpy as np


class SvmModel:
    def __init__(self, path):
        self.path = path
        hdr = {}
        raw = open(path, "rb").read().decode("ascii")
        lines = raw.split("\r\n")
        i = 0
        while i < len(lines) and not lines[i].startswith("SV"):
            line = lines[i].strip()
            if line:
                k, _, v = line.partition(" ")
                hdr[k] = v.split()
            i += 1
        assert lines[i].startswith("SV"), "missing SV section"
        self.svm_type = hdr["svm_type"][0]          # c_svc
        self.kernel_type = hdr["kernel_type"][0]    # rbf
        self.gamma = float(hdr["gamma"][0])
        self.nr_class = int(hdr["nr_class"][0])
        self.total_sv = int(hdr["total_sv"][0])
        self.rho = np.array([float(x) for x in hdr["rho"]], dtype=np.float64)
        self.label = np.array([int(x) for x in hdr["label"]], dtype=np.int32)
        self.probA = np.array([float(x) for x in hdr.get("probA", [])], dtype=np.float64)
        self.probB = np.array([float(x) for x in hdr.get("probB", [])], dtype=np.float64)
        self.nr_sv = np.array([int(x) for x in hdr.get("nr_sv", [])], dtype=np.int32)
        self.degree = float(hdr.get("degree", ["3"])[0])
        self.coef0 = float(hdr.get("coef0", ["0"])[0])

        k = self.nr_class - 1
        sv_lines = [l for l in lines[i + 1:] if l.strip()]
        assert len(sv_lines) == self.total_sv, (len(sv_lines), self.total_sv)

        maxidx = 0
        parsed = []
        for l in sv_lines:
            t = l.split()
            coefs = [float(x) for x in t[:k]]
            feats = []
            for p in t[k:]:
                j, v = p.split(":")
                j = int(j)
                feats.append((j, float(v)))
                maxidx = max(maxidx, j)
            parsed.append((coefs, feats))
        self.n_sv_features = maxidx
        self.n_features = 144          # ExtractGradientOrientation -> 144 bins

        self.sv_coef = np.zeros((self.total_sv, k), dtype=np.float64)
        self.sv = np.zeros((self.total_sv, self.n_features), dtype=np.float64)
        for r, (coefs, feats) in enumerate(parsed):
            self.sv_coef[r, :] = coefs
            for j, v in feats:
                self.sv[r, j - 1] = v

        # libsvm start[]/nSV[] per class in label order
        self.start = np.zeros(self.nr_class, dtype=np.int64)
        if len(self.nr_sv) == self.nr_class:
            c = 0
            for c_i in range(self.nr_class):
                self.start[c_i] = c
                c += int(self.nr_sv[c_i])

    # ---- kernels -------------------------------------------------------
    def kernel_matrix(self, X):
        """RBF kernel between each row of X and every SV."""
        X = np.atleast_2d(np.asarray(X, dtype=np.float64))
        # ||x-y||^2 = |x|^2 + |y|^2 - 2 x.y
        xn = (X ** 2).sum(1)[:, None]
        yn = (self.sv ** 2).sum(1)[None, :]
        d = xn + yn - 2.0 * X @ self.sv.T
        np.maximum(d, 0.0, out=d)
        if self.kernel_type == "rbf":
            return np.exp(-self.gamma * d)
        if self.kernel_type == "linear":
            return X @ self.sv.T
        raise NotImplementedError(self.kernel_type)

    # ---- decision values (one-vs-one, libsvm layout) --------------------
    def decision_values(self, X):
        K = self.kernel_matrix(X)
        n = K.shape[0]
        dec = np.zeros((n, self.nr_class * (self.nr_class - 1) // 2), dtype=np.float64)
        p = 0
        for i in range(self.nr_class):
            for j in range(i + 1, self.nr_class):
                si, sj = self.start[i], self.start[j]
                ni, nj = int(self.nr_sv[i]), int(self.nr_sv[j])
                # libsvm: sum over SVs of class i uses coeff[i], class j uses coeff[j]
                s = K[:, si:si + ni] @ self.sv_coef[si:si + ni, j - 1]
                s += K[:, sj:sj + nj] @ self.sv_coef[sj:sj + nj, i]
                dec[:, p] = s - self.rho[p]
                p += 1
        return dec

    def predict(self, X):
        X = np.atleast_2d(np.asarray(X, dtype=np.float64))
        dec = self.decision_values(X)
        votes = np.zeros((X.shape[0], self.nr_class), dtype=np.int64)
        p = 0
        for i in range(self.nr_class):
            for j in range(i + 1, self.nr_class):
                win = dec[:, p] > 0
                votes[:, i] += win
                votes[:, j] += ~win
                p += 1
        idx = np.argmax(votes, axis=1)
        return self.label[idx]

    # ---- probability estimates (pairwise coupling, Wu/Lin/Weng) --------
    @staticmethod
    def _sigmoid_predict(dec, A, B):
        """libsvm sigmoid_predict: returns P(class i) = 1/(1+exp(A*dec+B)),
        evaluated with the numerically stable branch of svm.cpp."""
        f = dec * A + B
        return np.where(f >= 0, np.exp(-f) / (1.0 + np.exp(-f)), 1.0 / (1.0 + np.exp(f)))

    def predict_proba(self, X):
        """Literal port of libsvm svm_predict_probability (Wu/Lin/Weng pairwise
        coupling).  Structure follows svm.cpp exactly, including the
        numerically stable sigmoid branch and the 1e-7 clamp."""
        X = np.atleast_2d(np.asarray(X, dtype=np.float64))
        dec = self.decision_values(X)
        k = self.nr_class
        if len(self.probA) == 0:
            raise ValueError("model has no probability parameters")
        min_prob = 1e-7
        out = np.empty((X.shape[0], k), dtype=np.float64)
        for s in range(X.shape[0]):
            r = np.zeros((k, k))
            p = 0
            for i in range(k):
                for j in range(i + 1, k):
                    v = self._sigmoid_predict(dec[s, p], self.probA[p], self.probB[p])
                    r[i, j] = min(max(v, min_prob), 1.0 - min_prob)
                    r[j, i] = 1.0 - r[i, j]
                    p += 1
            Q = np.zeros((k, k))
            pp = np.full(k, 1.0 / k)
            for t in range(k):
                Q[t, t] = 0.0
                for j in range(t):
                    Q[t, t] += r[j, t] * r[j, t]
                    Q[t, j] = Q[j, t]
                for j in range(t + 1, k):
                    Q[t, t] += r[j, t] * r[j, t]
                    Q[t, j] = -r[j, t] * r[t, j]
            eps = 0.005 / k
            Qp = np.zeros(k)
            pQp = 0.0
            for _ in range(max(100, k)):
                pQp = 0.0
                for t in range(k):
                    Qp[t] = 0.0
                    for j in range(k):
                        Qp[t] += Q[t, j] * pp[j]
                    pQp += pp[t] * Qp[t]
                max_error = float(np.abs(Qp - pQp).max())
                if max_error < eps:
                    break
                for t in range(k):
                    diff = (-Qp[t] + pQp) / Q[t, t]
                    pp[t] += diff
                    pQp = (pQp + diff * (diff * Q[t, t] + 2.0 * Qp[t])) / (1.0 + diff) / (1.0 + diff)
                    Qp = (Qp + diff * Q[t, :]) / (1.0 + diff)
                    pp = pp / (1.0 + diff)
            out[s] = pp
        return out
