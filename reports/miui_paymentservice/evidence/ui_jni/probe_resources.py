# Resolve camera-mask geometry / OCR UI strings and decode res/layout/mibi_capture.xml.
import logging, sys, zipfile
logging.disable(logging.DEBUG)
try:
    from loguru import logger; logger.remove()
except Exception:
    pass
from androguard.core.apk import APK
from androguard.core.axml import AXMLPrinter
APK_PATH = "/run/media/asdf/30620d0d-1e41-4d55-af08-b4de2fb9c517/xiaomi poco f4 system/product/app/PaymentService_Global/PaymentService_Global.apk"
a = APK(APK_PATH); ar = a.get_android_resources(); pkg = "com.xiaomi.payment"
for typ, key in [("dimen","mibi_camera_frame_rect_width"), ("dimen","mibi_camera_frame_rect_height"),
                 ("string","mibi_scan_prompt_voucher"), ("string","mibi_voucher_error_password"),
                 ("string","mibi_camera_open_failed"), ("layout","mibi_capture")]:
    rid = ar.get_res_id_by_key(pkg, typ, key)
    print(f"{typ}/{key} = 0x{rid:08x}" if rid else f"{typ}/{key} = <missing>")
    if rid and typ != "layout":
        for cfg, val in ar.get_resolved_res_configs(rid):
            c = str(cfg)
            if typ == "string" and not (c == "''=(0, 0, 0, 0, 0, 0, 0, 0, 0)" or "zh-rCN" in c):
                continue
            print(f"    [{c or 'default'}] {val!r}")
z = zipfile.ZipFile(APK_PATH)
print("\n--- res/layout/mibi_capture.xml ---")
print(AXMLPrinter(z.read("res/layout/mibi_capture.xml")).get_xml().decode("utf-8", "replace"))
