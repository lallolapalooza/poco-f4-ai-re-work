package a9;

import android.util.Log;
import java.io.File;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f375a = "2.bin";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f376b = 0.6f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public org.tensorflow.lite.b f377c;

    public a(String str) {
        this.f377c = new org.tensorflow.lite.b(new File(str + this.f375a));
    }

    public boolean a(float[][] fArr) {
        float[][] fArr2 = (float[][]) Array.newInstance((Class<?>) Float.TYPE, 1, 1);
        this.f377c.b(fArr, fArr2);
        float f10 = fArr2[0][0];
        Log.d("ImportanRecognizer", "important score:" + f10);
        return f10 > this.f376b;
    }
}
