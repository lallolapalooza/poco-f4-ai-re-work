package ah;

import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: classes3.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f516a = "ah.c";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String[] f517b = {"dict", "model", "pattern", "phish"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static volatile boolean f518c = false;

    protected static String a(Context context) {
        try {
            InputStream inputStreamB = b(context, "pattern");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamB));
            StringBuilder sb2 = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    bufferedReader.close();
                    inputStreamB.close();
                    return sb2.toString();
                }
                sb2.append(line + "\n");
            }
        } catch (IOException e10) {
            e10.printStackTrace();
            return null;
        }
    }

    public static InputStream b(Context context, String str) {
        try {
            boolean z10 = false;
            for (String str2 : context.fileList()) {
                if (str2.equals(str)) {
                    z10 = true;
                    break;
                }
            }
            return (!z10 || f518c) ? context.getAssets().open(str) : context.openFileInput(str);
        } catch (IOException e10) {
            e10.printStackTrace();
            Log.e(f516a, "load Model file " + str + " failed.");
            return null;
        }
    }

    public static void c(boolean z10) {
        f518c = z10;
    }
}
