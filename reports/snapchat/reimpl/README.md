# Snapchat / Looksery CV reimplementation (JAX)

Reconstructs the on-device CV stages that are actually shipped in
`Snapchat.apk` 11.70.0.36.  **No DNN is reimplemented because no DNN model ships
in the APK** (see `../report.md`); the AI Factory `.dnn.enc` models and the
Looksery NN detector are downloaded at runtime and are external contracts.

## Files

| file | content |
|---|---|
| `looksery_assets.py` | parsers: OpenCV Haar cascade XML, LZC v1 container (+zstd), protobuf ORB vocabulary tree, triangulations |
| `haar.py` | JAX Haar cascade: integral image, batched rectangle sums, `/ (w*h)` normalisation, depth-2 tree traversal, stage-wise candidate pruning, multi-scale window scan, IoU grouping |
| `orb.py` | JAX FAST-9 + NMS, intensity-centroid orientation (radius 10), steered BRIEF-256, Hamming matching with ratio test, translation vote, traversal of the shipped ORB tree |
| `deform.py` | exact `facestretchLiquify.glsl` radial displacement + landmark-triangle piecewise-affine warp |
| `pipeline.py` | end-to-end synthetic-frame run: Haar -> ORB -> landmarks -> deform |
| `verify.py` | 11 checks; writes `verify.txt` |
| `dump_ir.py` | regenerates `../ir/*.json` from the shipped assets |
| `assets/patch20.pgm` | 20x20 synthetic template that satisfies all 20 cascade stages (generated, not from the APK) |
| `ref/ref_haar.cpp` | OpenCV 5 `CascadeClassifier` reference used for the cross-check |

## Run

```bash
cd reports/snapchat/reimpl
/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python verify.py     # writes verify.txt, exit 0
/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python pipeline.py   # prints stage shapes
```

Verified: 11/11 checks, `exit: 0` (see `verify.txt`).

## Assets expected in `../models/`

```
looksery/core/HaarCascades/haarcascade_frontalface_alt2.xml
looksery/core/LiteTrackingData_ert.lns
looksery/core/NaturalFeatureTracker/SystemData/tree-ORB-*.bin
looksery/core/Landmarks/triangulation*.txt
looksery/core/Candide/candide3reduce{14AU}
looksery_ert/{Tracking.xml,FaceRegressor.bin,CandideRegressor.bin,
               DenoiseRegressor.bin,CandidePespectiveRegressor.bin,EyesRegressor.bin}
```

## Limits

* ERT regressor payloads (`*.bin` inside the LZC container) use a proprietary
  packed encoding that was not decoded; the pipeline reports the landmark
  topology and uses synthetic landmark positions.  No fabricated weights.
* Candide-3 model files are encrypted/obfuscated; not used.
* The BRIEF sample pattern is a documented deterministic Gaussian pattern
  (OpenCV's exact 256-pair table is not present in the APK); the ORB *tree*
  centroids are the shipped ones.
