# reimpl/ — JAX digit-OCR classifier (real weights)

```bash
python reimpl/pipeline.py        # ~20 s on CPU, prints shapes/values, exit 0
```

| File | Contents |
|---|---|
| `lsvm.py` | LIBSVM text-model parser + numpy reference: `SvmModel(path)` → `sv`, `sv_coef`, `rho`, `probA/B`, `label`, `nSV`, `start`; `decision_values`, `predict`, `predict_proba` (literal port of `svm.cpp` incl. `multiclass_probability`). |
| `hog.py` | numpy `extract(patch20x32)` → float32[144], the exact `CFEATURE_HOG::ExtractGradientOrientation` contract. |
| `jax_model.py` | JAX implementation: `load_params`, `hog_features`, `decision_values`, `predict_proba`, `predict`, `classify_digit`, `recognize`, `resize_to_patch`. |
| `pipeline.py` | Runnable demo/verifier (shapes, synthetic 28×28 & 32×32 inputs, 10-class + binary outputs, `1234` end-to-end, vendor cross-checks). |
| `verify.txt` | Captured run output (exit 0). |

## Contract implemented

```
patch (32x20 uint8, already segmented+normalised by the vendor)
   -> binarise >80
   -> gx = p[y][x]-p[y][x-1] (x>=1), gy = p[y][x]-p[y-1][x] (y>=1)
   -> ang = atan2(gy,gx)*180/pi + 180, wrap >=360
   -> bin = (x/5 + (y/8)*4)*9 + int(ang/40)         # 4x4 cells, 9 bins
   -> feat[bin] = hist[bin]/40                      # 144 float32
   -> RBF SVM:
        model9 (10 classes, gamma=2.0, 1076 SV): 45 one-vs-one decision values,
            sigmoid(probA,probB) + pairwise coupling -> prob[10]
            digit = argmax; conf = int(prob*100); extra candidates while prob > 0.1
        model2 (binary, gamma=0.5, 545 SV): prob[2]; gate = prob[0] > 0.5
```

## Numeric fidelity

`verify.txt` (all values taken from the shipped `libdigit_ocr.so` through
`native/harness`, see `../native/README.md`):

* HOG features: exact (max |diff| = 0.0 on 6 test images).
* 45 decision values: max |diff| 6.7e-08.
* class probabilities: max |diff| 4.7e-11 (numpy and JAX, float64).
* Vendor pipeline on a synthetic "1234" card image → `digit_array: 1 2 3 4`;
  JAX on the vendor's own four 20×32 patches → `"1234"`.

The segmentation stage that produces the 20×32 patch is *not* reimplemented
(see `../report.md` §8); `resize_to_patch` is only a convenience for feeding
28×28/32×32 images and is not claimed to match the vendor normalisation.
