"""Smoke tests for the APK/DEX tooling against the mounted image corpus.

Run from tools/:
    $PY -m unittest tests.test_smoke -v
"""

from __future__ import annotations

import json
import os
import shutil
import subprocess
import sys
import tempfile
import unittest

TOOLS = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
PY = sys.executable
WS = os.environ.get(
    "WS", "/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work"
)
APK_ROOT = os.environ.get(
    "APK_ROOT",
    "/run/media/asdf/30620d0d-1e41-4d55-af08-b4de2fb9c517/xiaomi poco f4 system",
)

SCA = os.path.join(APK_ROOT, "product/app/SecurityCoreAdd/SecurityCoreAdd.apk")
SC = os.path.join(
    APK_ROOT, "product/priv-app/MIUISecurityCenterGlobal/MIUISecurityCenterGlobal.apk"
)
KWAI = os.path.join(
    APK_ROOT,
    "cust/app/customized/partner-com.kwai.video_33/partner-com.kwai.video_33.apk",
)
WORK = os.path.join(WS, "work/ApkTooling/tests")


def run(args: list[str], timeout: int = 300) -> subprocess.CompletedProcess:
    proc = subprocess.run(
        [PY, *args],
        cwd=TOOLS,
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE,
        text=True,
        timeout=timeout,
        errors="replace",
    )
    return proc


def dir_bytes(path: str) -> int:
    total = 0
    for root, _dirs, files in os.walk(path):
        for name in files:
            total += os.path.getsize(os.path.join(root, name))
    return total


class TestApkTools(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        os.makedirs(WORK, exist_ok=True)
        cls.tmp = tempfile.mkdtemp(prefix="smoke_", dir=WORK)

    @classmethod
    def tearDownClass(cls):
        shutil.rmtree(cls.tmp, ignore_errors=True)

    @unittest.skipUnless(os.path.exists(SCA), "SecurityCoreAdd.apk missing")
    def test_inventory(self):
        out = os.path.join(self.tmp, "inv")
        proc = run([os.path.join(TOOLS, "apk_inventory.py"), SCA, "--out", out])
        self.assertEqual(proc.returncode, 0, proc.stderr)
        with open(os.path.join(out, "inventory.json"), encoding="utf-8") as f:
            inv = json.load(f)
        self.assertEqual(inv["package"], "com.miui.securitycore")
        self.assertIn("arm64-v8a", inv["abis"])
        models = {m["name"]: m for m in inv["ai"]["model_assets"]}
        self.assertIn("assets/aiautobrt.tflite", models)
        self.assertTrue(models["assets/aiautobrt.tflite"]["tflite"])
        lib_names = {l["name"] for l in inv["ai"]["libs"]}
        self.assertIn("lib/arm64-v8a/libtensorflowlite_jni.so", lib_names)
        self.assertGreaterEqual(inv["ai"]["dex_markers"].get("Lorg/tensorflow/lite", 0), 1)
        self.assertEqual(inv["extraction"]["models"], [])
        self.assertLess(dir_bytes(out), 100 * 1024 * 1024)
        self.assertTrue(os.path.exists(os.path.join(out, "inventory.md")))

    @unittest.skipUnless(os.path.exists(SCA), "SecurityCoreAdd.apk missing")
    def test_dex_find_defined_and_referenced(self):
        proc = run(
            [
                os.path.join(TOOLS, "dex_find.py"),
                SCA,
                "--regex",
                r"com\.xiaomi\.aiautobrt|org\.tensorflow\.lite",
                "--classes-only",
            ]
        )
        self.assertEqual(proc.returncode, 0, proc.stderr)
        rows = [json.loads(line) for line in proc.stdout.splitlines() if line.strip()]
        names = {r["class"] for r in rows}
        self.assertTrue(any(n.startswith("com.xiaomi.aiautobrt.") for n in names), names)
        self.assertTrue(any(n.startswith("org.tensorflow.lite") for n in names), names)
        self.assertTrue(any(r.get("defined") is False for r in rows))
        self.assertTrue(any(r.get("defined") is True for r in rows))

    @unittest.skipUnless(os.path.exists(SCA), "SecurityCoreAdd.apk missing")
    def test_dex_find_methods(self):
        proc = run(
            [
                os.path.join(TOOLS, "dex_find.py"),
                SCA,
                "--regex",
                r"aiautobrt",
                "--limit",
                "300",
            ]
        )
        self.assertEqual(proc.returncode, 0, proc.stderr)
        rows = [json.loads(line) for line in proc.stdout.splitlines() if line.strip()]
        self.assertTrue(any(r["kind"] == "method" and r["class"].endswith("TrainJobService") for r in rows))
        self.assertLessEqual(len(rows), 300)

    @unittest.skipUnless(os.path.exists(SCA), "SecurityCoreAdd.apk missing")
    def test_strings(self):
        proc = run(
            [
                os.path.join(TOOLS, "strings_dex.py"),
                SCA,
                "--regex",
                r"aiautobrt",
                "--limit",
                "20",
            ]
        )
        self.assertEqual(proc.returncode, 0, proc.stderr)
        rows = [json.loads(line) for line in proc.stdout.splitlines() if line.strip()]
        self.assertTrue(any("aiautobrt" in r["s"] for r in rows))
        self.assertLessEqual(len(rows), 20)

    @unittest.skipUnless(os.path.exists(SCA), "SecurityCoreAdd.apk missing")
    def test_decompile(self):
        out = os.path.join(self.tmp, "dec")
        proc = run(
            [
                os.path.join(TOOLS, "decompile_classes.py"),
                SCA,
                "--regex",
                r"aiautobrt",
                "--out",
                out,
                "--max",
                "2",
                "--timeout",
                "120",
            ],
            timeout=300,
        )
        self.assertEqual(proc.returncode, 0, proc.stderr)
        with open(os.path.join(out, "hits.json"), encoding="utf-8") as f:
            hits = json.load(f)
        self.assertEqual(hits["decompiled"], 2)
        for c in hits["candidates"][:2]:
            self.assertEqual(c["status"], "ok")
            path = os.path.join(out, c["file"])
            self.assertTrue(os.path.exists(path))
            with open(path, encoding="utf-8", errors="replace") as f:
                src = f.read()
            self.assertTrue("class " in src or "interface " in src)
        with open(os.path.join(out, "index.md"), encoding="utf-8") as f:
            self.assertIn("classes/", f.read())

    @unittest.skipUnless(os.path.exists(SC), "MIUISecurityCenterGlobal.apk missing")
    def test_big_apk_gamebooster(self):
        proc = run(
            [
                os.path.join(TOOLS, "dex_find.py"),
                SC,
                "--regex",
                r"com\.miui\.gamebooster\.beauty",
                "--classes-only",
            ]
        )
        self.assertEqual(proc.returncode, 0, proc.stderr)
        rows = [json.loads(line) for line in proc.stdout.splitlines() if line.strip()]
        self.assertTrue(any(r["class"].startswith("com.miui.gamebooster.beauty.") for r in rows))

    @unittest.skipUnless(os.path.exists(KWAI), "Kwai APK missing")
    def test_big_apk_kwai(self):
        proc = run(
            [
                os.path.join(TOOLS, "dex_find.py"),
                KWAI,
                "--regex",
                r"org\.tensorflow\.lite",
                "--classes-only",
            ]
        )
        self.assertEqual(proc.returncode, 0, proc.stderr)
        rows = [json.loads(line) for line in proc.stdout.splitlines() if line.strip()]
        self.assertTrue(any(r["class"].startswith("org.tensorflow.lite") for r in rows))


if __name__ == "__main__":
    unittest.main()
