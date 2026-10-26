# Model inventory — miui_securitycenter

APK: `/run/media/asdf/30620d0d-1e41-4d55-af08-b4de2fb9c517/xiaomi poco f4 system/product/priv-app/MIUISecurityCenterGlobal/MIUISecurityCenterGlobal.apk`

- Local model files: **3** (4.3 MB)
- TFLite files: **0**
- Bundle containers: **0**

## Extracted from APK

assets/model (680,154 B custom 5-stage log-linear), assets/dict (3,639,702 B tokenizer), assets/pattern (5,568 B rules)

## Reimplementation artifacts

The 680 KB asset is not TFLite; it is the legacy SMS log-linear cascade, parsed and JAX-verified.

## Not extractable / absent

- Downloaded new-engine TFLite models 1.bin / 2.bin / v.txt (resource.zip from api.sec.intl.miui.com) are not in the APK.
- assets/phish URL list is absent.

## Files

- `assets_dict` — 3,639,702 B
- `assets_model` — 680,154 B
- `assets_pattern` — 5,568 B
