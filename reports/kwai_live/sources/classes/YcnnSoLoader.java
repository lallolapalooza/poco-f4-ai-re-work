package com.kwai.video.westeros.v2.ycnn;

import com.kwai.video.westeros.helpers.WesterosSoLoader;
import com.kwai.video.westeros.v2.ykitplugin.CpuInfoChecker;
import com.yxcorp.bugly.CrashReporter;

/* JADX INFO: loaded from: classes13.dex */
public class YcnnSoLoader {
    public static void loadNative() {
        WesterosSoLoader.loadNative();
        WesterosSoLoader.loadLibrary("ykit_core_plugin");
    }

    public static void loadYcnnExtraSo() {
        CpuInfoChecker cpuInfo = CpuInfoChecker.getCpuInfo();
        try {
            WesterosSoLoader.loadLibrary("hiai_ir");
            WesterosSoLoader.loadLibrary("hiai_ir_build");
            WesterosSoLoader.loadLibrary("hiai");
            WesterosSoLoader.loadLibrary("hcl");
            WesterosSoLoader.loadLibrary("hiai_session_impl");
        } catch (Throwable th2) {
            CrashReporter.logCheckedException(th2, "YcnnSoLoader.class", "loadYcnnExtraSo", 24);
            th2.printStackTrace();
        }
        try {
            WesterosSoLoader.loadLibrary("tflite_mtk_static");
        } catch (Throwable th3) {
            CrashReporter.logCheckedException(th3, "YcnnSoLoader.class", "loadYcnnExtraSo", 29);
            th3.printStackTrace();
        }
        if (cpuInfo.supportSNPE()) {
            try {
                WesterosSoLoader.loadLibrary("SNPE");
                WesterosSoLoader.loadLibrary("ycnn_ext_snpe");
            } catch (Throwable th4) {
                CrashReporter.logCheckedException(th4, "YcnnSoLoader.class", "loadYcnnExtraSo", 38);
                th4.printStackTrace();
            }
            try {
                WesterosSoLoader.loadLibrary("PlatformValidatorShared");
            } catch (Throwable th5) {
                CrashReporter.logCheckedException(th5, "YcnnSoLoader.class", "loadYcnnExtraSo", 43);
                th5.printStackTrace();
            }
            try {
                WesterosSoLoader.loadLibrary("calculator");
            } catch (Throwable th6) {
                CrashReporter.logCheckedException(th6, "YcnnSoLoader.class", "loadYcnnExtraSo", 48);
                th6.printStackTrace();
            }
            try {
                WesterosSoLoader.loadLibrary("snpe_dsp_domains_v2");
            } catch (Throwable th7) {
                CrashReporter.logCheckedException(th7, "YcnnSoLoader.class", "loadYcnnExtraSo", 53);
                th7.printStackTrace();
            }
            try {
                WesterosSoLoader.loadLibrary("snpe_dsp_domains_v2_system");
            } catch (Throwable th8) {
                CrashReporter.logCheckedException(th8, "YcnnSoLoader.class", "loadYcnnExtraSo", 58);
                th8.printStackTrace();
            }
        }
    }
}
