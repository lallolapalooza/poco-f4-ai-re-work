package a9;

import android.util.Log;
import java.io.File;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f379a = "1.bin";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f380b = 0.4f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public org.tensorflow.lite.b f381c;

    public c(String str) {
        this.f381c = new org.tensorflow.lite.b(new File(str + this.f379a));
    }

    public boolean a(float[][] fArr) {
        float[][] fArr2 = (float[][]) Array.newInstance((Class<?>) Float.TYPE, 1, 1);
        this.f381c.b(fArr, fArr2);
        float f10 = fArr2[0][0];
        Log.d("UnlawRecognizer", "unlaw score:" + f10);
        return f10 > this.f380b;
    }
}
