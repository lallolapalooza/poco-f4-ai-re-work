package com.miui.extraphoto.refocus.manager;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.miui.extraphoto.refocus.RefocusAccessUtils;

/* JADX INFO: loaded from: classes.dex */
public class AlgoManager {
    private static Algo sArcSoft = new ArcSoftAlgo();
    private static Algo sMiBrainSingleBokeh = new MiBrainSingleBokehAlgo();
    private static Algo sMiBrainRelighting = new MiBrainRelightingAlgo();
    private static Algo sMiCameraDualBokeh = new MiCameraDualBokehAlgo();
    private static Algo sMegviiBokeh = new MegviiBokehAlgo();
    private static Algo sMiAlgoBokeh = new MiAlgoBokeh();

    public interface Algo {
        boolean integrated();

        void load();
    }

    static {
        sArcSoft.load();
        sMiBrainRelighting.load();
        sMiBrainSingleBokeh.load();
        sMiCameraDualBokeh.load();
        sMegviiBokeh.load();
        sMiAlgoBokeh.load();
    }

    public static boolean supportAdjust(String str) {
        if (TextUtils.isEmpty(str)) {
            return sArcSoft.integrated();
        }
        str.hashCode();
        switch (str) {
            case "mialgo_portrait":
                return sMiAlgoBokeh.integrated();
            case "megvii_portrait_google":
            case "megvii_portrait":
                return sMegviiBokeh.integrated();
            case "mi_portrait":
                return sMiCameraDualBokeh.integrated();
            case "soft-portrait-enc":
                return sMiBrainSingleBokeh.integrated();
            case "portrait":
                return sArcSoft.integrated();
            default:
                return false;
        }
    }

    public static boolean supportDynamic(String str) {
        if (DeviceManager.isMeituDevice() || DeviceManager.isShieldDynamic()) {
            return false;
        }
        str.hashCode();
        if (str.equals("mi_portrait")) {
            return sMiCameraDualBokeh.integrated();
        }
        if (str.equals("portrait")) {
            return sArcSoft.integrated();
        }
        return false;
    }

    public static boolean supportRelighting(String str, int i) {
        if (RefocusAccessUtils.isForbidRelight() || DeviceManager.isMeituDevice()) {
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            return sArcSoft.integrated();
        }
        if (i == 0 || i == 1) {
            str.hashCode();
            switch (str) {
                case "soft-portrait":
                case "portrait":
                    return sArcSoft.integrated();
                case "soft-portrait-enc":
                    return RefocusAccessUtils.isBrianRelight() && sMiBrainSingleBokeh.integrated() && sArcSoft.integrated();
            }
        }
        return false;
    }

    public static boolean supportMovieRelighting(String str, int i) {
        if (!RefocusAccessUtils.isBrianRelight() || TextUtils.isEmpty(str)) {
            return false;
        }
        if (i != 2) {
            return false;
        }
        str.hashCode();
        switch (str) {
            case "megvii_portrait_google":
                return sMegviiBokeh.integrated() && sMiBrainRelighting.integrated();
            case "mi_portrait":
                return sMiCameraDualBokeh.integrated() && sMiBrainRelighting.integrated();
            case "soft-portrait-enc":
                return sMiBrainSingleBokeh.integrated() && sMiBrainRelighting.integrated();
            case "portrait":
                return sArcSoft.integrated() && sMiBrainRelighting.integrated();
            default:
                return false;
        }
    }

    public static boolean isSupportMeituEffect() {
        return ((ArcSoftAlgo) sArcSoft).sSupportMeituEffect;
    }

    public static abstract class AbstractAlgo implements Algo {
        boolean mIntegrated;

        @Override // com.miui.extraphoto.refocus.manager.AlgoManager.Algo
        public boolean integrated() {
            return this.mIntegrated;
        }
    }

    public static class ArcSoftAlgo extends AbstractAlgo {
        public boolean sSupportMeituEffect;

        @Override // com.miui.extraphoto.refocus.manager.AlgoManager.Algo
        public void load() {
            try {
                if (RefocusAccessUtils.isSupport()) {
                    System.loadLibrary("gallery_mpbase");
                    System.loadLibrary("gallery_arcsoft_portrait_lighting_c");
                    System.loadLibrary("gallery_arcsoft_portrait_lighting");
                    System.loadLibrary("gallery_arcsoft_dualcam_refocus");
                    System.loadLibrary("refocus");
                    this.mIntegrated = true;
                }
                if (RefocusAccessUtils.isMeitu()) {
                    System.loadLibrary("refocus_meitu");
                    this.sSupportMeituEffect = true;
                }
            } catch (LinkageError e) {
                Log.e("AlgoManager", String.format("load meitu failed device : %s", Build.DEVICE), e);
            }
        }
    }

    public static class MiBrainSingleBokehAlgo extends AbstractAlgo {
        @Override // com.miui.extraphoto.refocus.manager.AlgoManager.Algo
        public void load() {
            try {
                if (RefocusAccessUtils.isMi()) {
                    if (RefocusAccessUtils.isMiMTK()) {
                        System.loadLibrary("refocus_mibokeh_mtk");
                    } else {
                        System.loadLibrary("refocus_mibokeh");
                    }
                    this.mIntegrated = true;
                }
            } catch (LinkageError e) {
                Log.e("AlgoManager", String.format("load brain single bokeh failed device : %s", Build.DEVICE), e);
            }
        }
    }

    public static class MiBrainRelightingAlgo extends AbstractAlgo {
        @Override // com.miui.extraphoto.refocus.manager.AlgoManager.Algo
        public void load() {
            try {
                if (RefocusAccessUtils.isBrianRelight()) {
                    System.loadLibrary("relight_only_extraphoto");
                    System.loadLibrary("mibrain_relighting_jni");
                    this.mIntegrated = true;
                }
            } catch (LinkageError e) {
                Log.e("AlgoManager", String.format("load brain relighting failed device : %s", Build.DEVICE), e);
            }
        }
    }

    public static class MiCameraDualBokehAlgo extends AbstractAlgo {
        @Override // com.miui.extraphoto.refocus.manager.AlgoManager.Algo
        public void load() {
            try {
                if (RefocusAccessUtils.isMiDual()) {
                    if (RefocusAccessUtils.isMiMTK()) {
                        System.loadLibrary("mialgo_utils_mtk");
                    } else {
                        System.loadLibrary("mialgo_utils_extraphoto");
                    }
                    System.loadLibrary("miphone_bokeh_dynSpot");
                    System.loadLibrary("miphone_refocus_bokeh");
                    System.loadLibrary("micamera_dual_bokeh_jni");
                    this.mIntegrated = true;
                }
            } catch (LinkageError e) {
                Log.e("AlgoManager", String.format("load camera dual bokeh failed device : %s", Build.DEVICE), e);
            }
        }
    }

    public static class MegviiBokehAlgo extends AbstractAlgo {
        @Override // com.miui.extraphoto.refocus.manager.AlgoManager.Algo
        public void load() {
            try {
                if (RefocusAccessUtils.isMegvii()) {
                    System.loadLibrary("wa_refocus_extraphoto");
                    System.loadLibrary("megvii_bokeh_jni");
                    this.mIntegrated = true;
                }
            } catch (LinkageError e) {
                Log.e("AlgoManager", String.format("load camera dual bokeh failed device : %s", Build.DEVICE), e);
            }
        }
    }

    public static class MiAlgoBokeh extends AbstractAlgo {
        @Override // com.miui.extraphoto.refocus.manager.AlgoManager.Algo
        public void load() {
            try {
                Log.w("AlgoManager", "MiAlgoBokeh load:" + RefocusAccessUtils.isMiMTK() + " = " + RefocusAccessUtils.isSupportMiAlgo());
                if (RefocusAccessUtils.isSupportMiAlgo()) {
                    if (RefocusAccessUtils.isMiMTK()) {
                        System.loadLibrary("mialgo_utils_mtk");
                        System.loadLibrary("miphone_bokeh_gpf_mtk");
                        System.loadLibrary("miphone_bokeh_effect_mtk");
                        System.loadLibrary("miphone_bokeh_proc_mtk");
                    } else {
                        System.loadLibrary("mialgo_utils_extraphoto");
                    }
                    System.loadLibrary("mialgo_portrait");
                    this.mIntegrated = true;
                }
            } catch (LinkageError e) {
                Log.e("AlgoManager", String.format("load mialgo dual bokeh failed device : %s", Build.DEVICE), e);
            }
        }
    }
}
