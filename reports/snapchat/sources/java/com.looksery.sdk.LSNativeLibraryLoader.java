package com.looksery.sdk;

import android.content.Context;
import com.snap.nloader.android.NLOader;
import defpackage.AbstractC19915fE3;
import java.io.File;
import java.io.FilenameFilter;

/* JADX INFO: loaded from: classes2.dex */
public final class LSNativeLibraryLoader {
    private static final String TAG = "LSNativeLibraryLoader";
    private static String sLastLoadErrorMessage = "";
    private static Boolean sNativeLibLoadedSuccessfully;

    private LSNativeLibraryLoader() {
        throw new AssertionError("No instances.");
    }

    public static synchronized boolean checkIfLoadedOrTryToLoad() {
        if (sNativeLibLoadedSuccessfully == null) {
            try {
                NLOader.initializeNativeComponent(BuildConfig.LENSCORE_COMPONENT_NAME);
                sNativeLibLoadedSuccessfully = Boolean.TRUE;
            } catch (Throwable th) {
                sLastLoadErrorMessage = th.getMessage();
                sNativeLibLoadedSuccessfully = Boolean.FALSE;
            }
        }
        return sNativeLibLoadedSuccessfully.booleanValue();
    }

    public static void ensureAllAreLoaded() {
        if (checkIfLoadedOrTryToLoad()) {
            return;
        }
        StringBuilder sbD = AbstractC19915fE3.d("Native libraries aren't loaded: ");
        sbD.append(sLastLoadErrorMessage);
        throw new IllegalStateException(sbD.toString());
    }

    public static String getLastLoadError() {
        return sLastLoadErrorMessage;
    }

    public static String[] resolveLibrariesPathnames(Context context) {
        File[] fileArrListFiles = new File(context.getApplicationInfo().nativeLibraryDir).listFiles(new FilenameFilter() { // from class: com.looksery.sdk.LSNativeLibraryLoader.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str) {
                return str.startsWith("lib") && str.endsWith(".so");
            }
        });
        if (fileArrListFiles == null || fileArrListFiles.length == 0) {
            return new String[0];
        }
        int length = fileArrListFiles.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = fileArrListFiles[i].getAbsolutePath();
        }
        return strArr;
    }
}
