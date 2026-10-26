#!/usr/bin/env python3
"""Dump AndroidManifest.xml (binary AXML) of ModemTestBox.apk as text.

Targeted: prints the full decoded manifest so the AI entrypoints
(receiver + AiWeakSignalForecastService) can be checked for exported /
permission / intent-filter attributes.  androguard only, nothing is modified.
"""
import sys
from loguru import logger

logger.remove()

from androguard.core.apk import APK  # noqa: E402

apk = APK(sys.argv[1])
print(apk.get_android_manifest_axml().get_xml().decode("utf-8", "replace"))
