package com.kwai.video.ksmedialivekit;

/* JADX INFO: loaded from: classes8.dex */
public class LiveKitSoLoader {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile SoLoader f31353a = new DefaultSoLoader();

    private static class DefaultSoLoader implements SoLoader {
        private DefaultSoLoader() {
        }

        @Override // com.kwai.video.ksmedialivekit.LiveKitSoLoader.SoLoader
        public void loadLibrary(String str) {
            System.loadLibrary(str);
        }
    }

    public interface SoLoader {
        void loadLibrary(String str);
    }

    public static void loadLibrary(String str) {
        if (f31353a != null) {
            f31353a.loadLibrary(str);
        }
    }

    public static void setSoLoader(SoLoader soLoader) {
        f31353a = soLoader;
    }
}
