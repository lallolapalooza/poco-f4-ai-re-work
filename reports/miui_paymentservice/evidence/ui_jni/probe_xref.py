# Find every method whose bytecode references CameraIntentData or the entry id string "mibi.ocr".
import logging, zipfile
logging.disable(logging.DEBUG)
try:
    from loguru import logger; logger.remove()
except Exception:
    pass
from androguard.core.dex import DEX
APK_PATH = "/run/media/asdf/30620d0d-1e41-4d55-af08-b4de2fb9c517/xiaomi poco f4 system/product/app/PaymentService_Global/PaymentService_Global.apk"
z = zipfile.ZipFile(APK_PATH)
for dn in ("classes.dex", "classes2.dex"):
    d = DEX(z.read(dn)); hits = []
    for cls in d.get_classes():
        for m in cls.get_methods():
            off = m.get_code_off()
            if not off:
                continue
            try:
                c = d.CM.get_code(off)
                if c is None:
                    continue
                txt = "\n".join((i.get_output() or "") for i in c.get_bc().get_instructions())
            except Exception:
                continue
            if "CameraIntentData" in txt or "'mibi.ocr'" in txt:
                hits.append((m.get_class_name(), m.get_name(), m.get_descriptor()))
    print(f"== {dn}: {len(hits)} referencing methods")
    for h in sorted(hits):
        print("   ", *h)
