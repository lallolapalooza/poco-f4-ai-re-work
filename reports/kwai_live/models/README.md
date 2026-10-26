# models/ — empty: the APK bundles no model file

`apk_inventory.py` reports `model_assets = 0` for all 8 130 zip entries, and
`tools/carve_models.py` finds no embedded TFLite/Caffe/DLC blob in any of the
AI libraries (only the `TFL3` magic literal inside `libtensorflow-lite.so` and
the string `debug.tflite`).

Everything learned is downloaded at runtime (YCNN models via
`/rest/o/production/ycnn/models` + `YlabModelConfigResponse`, effect packages
via `MagicDownloadPlugin`, makeup via `MakeupDownloadHelper`), together with
the vendor inference backends (`ycnn`, `ycnn2`, `hiai_*`, `tflite_mtk_static`,
`SNPE`, `ycnn_ext_snpe`) — none of which exist in the APK.

Bundled AI parameters (not weights) are kept in `../evidence/assets/` and
copied into `../reimpl/data/`.
