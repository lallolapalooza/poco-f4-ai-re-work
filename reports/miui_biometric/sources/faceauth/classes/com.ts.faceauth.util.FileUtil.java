package com.ts.faceauth.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class FileUtil {
    private static SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss");

    public static String getAssertData(Context context, String str) {
        try {
            InputStream inputStreamOpen = context.getAssets().open(str);
            byte[] bArr = new byte[inputStreamOpen.available()];
            inputStreamOpen.read(bArr);
            inputStreamOpen.close();
            return new String(bArr);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void createFile(String str) throws IOException {
        File file = new File(str);
        if (file.isFile() && file.exists()) {
            file.delete();
        }
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        file.createNewFile();
    }

    public static String getUUIDName() {
        String string = UUID.randomUUID().toString();
        return string.substring(0, 8) + string.substring(9, 13) + string.substring(14, 18) + string.substring(19, 23) + string.substring(24);
    }

    public static boolean saveBitmap(Bitmap bitmap, String str) {
        if (bitmap == null) {
            return false;
        }
        File file = new File(str);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean saveBytes(byte[] bArr, String str) {
        File file = new File(str);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bArr);
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean saveString(String str, String str2) {
        File file = new File(str2);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(str);
            bufferedWriter.flush();
            bufferedWriter.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static byte[] readByteArray(String str) throws Throwable {
        BufferedInputStream bufferedInputStream;
        File file = new File(str);
        BufferedInputStream bufferedInputStream2 = null;
        if (!file.exists()) {
            Log.w("FileUtil", "file not exist! path: " + str);
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((int) file.length());
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = bufferedInputStream.read(bArr, 0, 1024);
                        if (-1 == i) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, i);
                        th = th;
                        bufferedInputStream2 = bufferedInputStream;
                        try {
                            bufferedInputStream2.close();
                            byteArrayOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        throw th;
                    }
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    try {
                        bufferedInputStream.close();
                        byteArrayOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    return byteArray;
                } catch (IOException e3) {
                    e = e3;
                    e.printStackTrace();
                    try {
                        bufferedInputStream.close();
                        byteArrayOutputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                bufferedInputStream2 = bufferedInputStream;
            }
        } catch (IOException e5) {
            e = e5;
            bufferedInputStream = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String getFormatTime(long j) {
        return mDateFormat.format(new Date(j));
    }

    public static String getLicenseFileNameInAssets(Context context) throws IOException {
        String[] list = context.getAssets().list("");
        for (int i = 0; i < list.length; i++) {
            if (list[i].endsWith(".lic")) {
                return list[i];
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.io.BufferedInputStream] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v9 */
    public static String readString(String str) throws Throwable {
        StringBuffer stringBuffer = new StringBuffer("");
        File file = new File(str);
        ?? r3 = 0;
        BufferedInputStream bufferedInputStream = null;
        r3 = 0;
        try {
            if (!file.exists()) {
                Log.w("FileUtil", "file not exist! path: " + str);
                return null;
            }
            try {
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = bufferedInputStream2.read(bArr);
                        r3 = -1;
                        if (-1 == i) {
                            break;
                        }
                        stringBuffer.append(new String(bArr, 0, i));
                    }
                    bufferedInputStream2.close();
                } catch (IOException e) {
                    e = e;
                    bufferedInputStream = bufferedInputStream2;
                    e.printStackTrace();
                    bufferedInputStream.close();
                    r3 = bufferedInputStream;
                } catch (Throwable th) {
                    th = th;
                    r3 = bufferedInputStream2;
                    try {
                        r3.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        return stringBuffer.toString();
    }
}
