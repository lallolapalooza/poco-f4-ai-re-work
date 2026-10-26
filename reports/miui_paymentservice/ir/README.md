# ir/ — model IR

The shipped models are LIBSVM text files, not TFLite/ONNX, so there is no graph
IR.  `svm_9_ir.json` / `svm_2_ir.json` capture the parsed structure (header,
tensor shapes, rho/probA/probB/start values) of
`models/Digital_OCR_svm_9.model` and `..._2.model`.

The op sequence implied by the native code (see `../report.md` §4) is:

```
input[32,20] uint8
  -> binarize(>80)                       # CFEATURE_HOG::ExtractGradientOrientation
  -> diff_x, diff_y                      # backward differences, first row/col = 0
  -> atan2(gy,gx)*180/pi+180, mod 360
  -> cell = (x/5) + (y/8)*4 ; obin = floor(ang/40)
  -> bincount(cell*9+obin, 144) / 40
  -> rbf_kernel(gamma) vs SV[total_sv,144]
  -> one_vs_one decision values (45 for model9) - rho
  -> sigmoid(probA,probB) -> pairwise_coupling -> probs[nr_class]
  -> argmax (+ 2nd/3rd while prob>0.1)
```
