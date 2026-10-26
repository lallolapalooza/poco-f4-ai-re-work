package com.bef.effectsdk;

import X.0eM;
import X.0hy;
import X.0i0;
import X.0i2;
import android.content.Context;
import android.text.TextUtils;
import com.bytedance.covode.number.Covode;
import com.bytedance.ies.abmock.SettingsManager;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/work/tmp_trill/classes2.dex */
public class EffectSDKUtils {
    public static List<String> assetFiles;
    public static Set<File> localFiles;
    public static Set<File> needRemoveFiles;

    public static native String nativeGetSdkVersion();

    public static String getSdkVersion() {
        return nativeGetSdkVersion();
    }

    static {
        Covode.recordClassIndex(2819);
        localFiles = new HashSet();
        assetFiles = ModelsList.list;
        needRemoveFiles = new HashSet();
    }

    public static void deleteNoUseModel() {
        for (File file : localFiles) {
            if (needRemoveFiles.contains(file) && file.exists()) {
                com_bef_effectsdk_EffectSDKUtils_com_ss_android_ugc_aweme_storage_FileLancet_delete(file);
            }
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }

    public static String getAssetRelativePath(String str) {
        int iIndexOf = str.indexOf("model/");
        if (iIndexOf >= 0) {
            return str.substring(iIndexOf + 6, str.length());
        }
        return str;
    }

    public static String getFileName(String str) {
        int iLastIndexOf = str.lastIndexOf("/");
        if (iLastIndexOf != -1) {
            return str.substring(iLastIndexOf + 1, str.length());
        }
        return androidx.multidex.BuildConfig.VERSION_NAME;
    }

    public static boolean com_bef_effectsdk_EffectSDKUtils_com_ss_android_ugc_aweme_storage_FileLancet_delete(File file) {
        0hy r3;
        try {
            if (0eM.LIZ()) {
                r3 = (0hy) SettingsManager.LIZ().LIZ("storage_intercepter_key", 0hy.class, 0i2.LIZ);
            } else {
                r3 = 0i2.LIZ;
            }
            if (0i0.LIZ(file.getAbsolutePath(), r3)) {
                0i0.LIZ(file, new RuntimeException(), "exception_delete_log", 0i0.LIZ(r3));
            }
            if (0i0.LIZJ(file.getAbsolutePath(), r3)) {
                0i0.LIZ(file, new RuntimeException(), "exception_handle", 0i0.LIZ(r3));
                return false;
            }
        } catch (Throwable unused) {
        }
        return file.delete();
    }

    public static void flushAlgorithmModelFiles(Context context, String str) throws Throwable {
        if (!localFiles.isEmpty()) {
            localFiles.clear();
        }
        scanRecursive(str, localFiles);
        copyAssets(context, str, null, false);
        deleteNoUseModel();
        localFiles.clear();
    }

    public static File takeFirstMatchingOrNull(Set<File> set, FileFilter fileFilter) {
        for (File file : set) {
            if (fileFilter.accept(file)) {
                return file;
            }
        }
        return null;
    }

    public static void scanRecursive(String str, Set<File> set) {
        File[] fileArrListFiles;
        File file = new File(str);
        if (file.exists() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                if (file2.isDirectory()) {
                    scanRecursive(file2.getAbsolutePath(), set);
                } else {
                    set.add(file2);
                }
            }
        }
    }

    public static boolean needUpdate(final Context context, String str) {
        if (!localFiles.isEmpty()) {
            localFiles.clear();
        }
        scanRecursive(str, localFiles);
        try {
            if (assetFiles.size() > localFiles.size()) {
                return true;
            }
            for (final String str2 : assetFiles) {
                if (takeFirstMatchingOrNull(localFiles, new FileFilter() { // from class: com.bef.effectsdk.EffectSDKUtils.1
                    static {
                        Covode.recordClassIndex(2820);
                    }

                    @Override // java.io.FileFilter
                    public final boolean accept(File file) {
                        boolean z = false;
                        if (str2.contains(file.getName())) {
                            InputStream inputStreamOpen = null;
                            try {
                                inputStreamOpen = context.getAssets().open(str2);
                                if (file.length() == inputStreamOpen.available()) {
                                    z = true;
                                }
                                return z;
                            } catch (IOException unused) {
                            } finally {
                                EffectSDKUtils.closeQuietly(inputStreamOpen);
                            }
                        }
                        return false;
                    }
                }) == null) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            return true;
        }
    }

    public static void copyFile(Context context, String str, String str2) throws Throwable {
        FileOutputStream fileOutputStream;
        InputStream inputStream = null;
        try {
            InputStream inputStreamOpen = context.getAssets().open(str);
            try {
                String str3 = str2 + str.substring(str.indexOf("model") + 6, str.lastIndexOf("/"));
                File file = new File(str3);
                if (file.exists() || file.mkdirs()) {
                    fileOutputStream = new FileOutputStream(new File(str3 + "/" + getFileName(str)));
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int i = inputStreamOpen.read(bArr);
                            if (i > 0) {
                                fileOutputStream.write(bArr, 0, i);
                            } else {
                                try {
                                    closeQuietly(inputStreamOpen);
                                    return;
                                } finally {
                                    closeQuietly(fileOutputStream);
                                }
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        inputStream = inputStreamOpen;
                        try {
                            closeQuietly(inputStream);
                            throw th;
                        } finally {
                            closeQuietly(fileOutputStream);
                        }
                    }
                } else {
                    throw new IOException("Can not mkdirs " + file.getPath());
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
                inputStream = inputStreamOpen;
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
        }
    }

    public static void flushAlgorithmModelFiles(Context context, String str, String[] strArr, boolean z) throws Throwable {
        if (!localFiles.isEmpty()) {
            localFiles.clear();
        }
        scanRecursive(str, localFiles);
        copyAssets(context, str, strArr, z);
        deleteNoUseModel();
        localFiles.clear();
    }

    public static void copyAssets(Context context, String str, String[] strArr, boolean z) throws Throwable {
        if (!needRemoveFiles.isEmpty()) {
            needRemoveFiles.clear();
        }
        needRemoveFiles.addAll(localFiles);
        if (!str.endsWith("/")) {
            str = str + "/";
        }
        for (String str2 : assetFiles) {
            final String fileName = getFileName(str2);
            File fileTakeFirstMatchingOrNull = takeFirstMatchingOrNull(needRemoveFiles, new FileFilter() { // from class: com.bef.effectsdk.EffectSDKUtils.2
                static {
                    Covode.recordClassIndex(2821);
                }

                @Override // java.io.FileFilter
                public final boolean accept(File file) {
                    return file.getName().contains(fileName);
                }
            });
            if (fileTakeFirstMatchingOrNull != null && new File(str, getAssetRelativePath(str2)).exists()) {
                needRemoveFiles.remove(fileTakeFirstMatchingOrNull);
            } else {
                if (strArr != null && !TextUtils.isEmpty(fileName)) {
                    int length = strArr.length;
                    int i = 0;
                    while (true) {
                        if (i < length) {
                            if (fileName.equals(strArr[i])) {
                                if (z) {
                                    copyFile(context, str2, str);
                                }
                                if (1 == 0) {
                                }
                            } else {
                                i++;
                            }
                        }
                    }
                }
                if (!z) {
                    copyFile(context, str2, str);
                }
            }
        }
    }
}
