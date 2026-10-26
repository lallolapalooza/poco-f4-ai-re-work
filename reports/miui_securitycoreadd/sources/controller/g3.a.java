package g3;

import android.content.Context;
import gb.c;
import gb.d;
import java.io.Closeable;
import java.io.File;
import java.lang.reflect.Array;
import java.nio.FloatBuffer;
import java.nio.MappedByteBuffer;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Closeable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f3665b;
    public boolean c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public File f3666d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public org.tensorflow.lite.a f3667e;

    public a(Context context, MappedByteBuffer mappedByteBuffer, File file) {
        t9.a aVar = t9.a.ERROR;
        this.f3665b = context;
        this.f3666d = file;
        if (mappedByteBuffer == null) {
            c.i(context);
            d.f3839a.a(aVar, "IndividualBrt-BrightnessLiteModel", "Lite Model file is null!", null);
            c.c.a(aVar, "IndividualBrt-BrightnessLiteModel", "Lite Model file is null!", null);
            return;
        }
        try {
            this.f3667e = new org.tensorflow.lite.a(mappedByteBuffer);
            File file2 = this.f3666d;
            if (file2 != null && file2.exists()) {
                e();
            }
            this.c = true;
        } catch (Exception e10) {
            c.i(this.f3665b);
            d.f3839a.a(aVar, "IndividualBrt-BrightnessLiteModel", "Error loading lite model file!", e10);
            c.c.a(aVar, "IndividualBrt-BrightnessLiteModel", "Error loading lite model file!", e10);
        }
    }

    public final float a(String str, h3.a aVar) {
        try {
            float[][] fArr = (float[][]) Array.newInstance((Class<?>) float.class, 1, 3);
            fArr[0][0] = aVar.c;
            fArr[0][1] = aVar.f4046d;
            fArr[0][2] = aVar.f4048f;
            HashMap map = new HashMap();
            map.put("x", fArr);
            HashMap map2 = new HashMap();
            FloatBuffer floatBufferAllocate = FloatBuffer.allocate(1);
            map2.put("pre", floatBufferAllocate);
            this.f3667e.a(map, map2, str);
            return floatBufferAllocate.get(0);
        } catch (IllegalArgumentException e10) {
            c.i(this.f3665b);
            t9.a aVar2 = t9.a.ERROR;
            d.f3839a.a(aVar2, "IndividualBrt-BrightnessLiteModel", "Model prediction error!", e10);
            c.c.a(aVar2, "IndividualBrt-BrightnessLiteModel", "Model prediction error!", e10);
            return -1.0f;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        org.tensorflow.lite.a aVar = this.f3667e;
        if (aVar != null) {
            aVar.close();
        }
    }

    public final void e() {
        try {
            HashMap map = new HashMap();
            String absolutePath = this.f3666d.getAbsolutePath();
            map.put("checkpoint_path", absolutePath);
            this.f3667e.a(map, new HashMap(), "restore");
            c.i(this.f3665b);
            c.c.d("IndividualBrt-BrightnessLiteModel", "Model restore completed, the path: " + absolutePath);
        } catch (IllegalArgumentException e10) {
            c.i(this.f3665b);
            t9.a aVar = t9.a.ERROR;
            d.f3839a.a(aVar, "IndividualBrt-BrightnessLiteModel", "Model restore error!", e10);
            c.c.a(aVar, "IndividualBrt-BrightnessLiteModel", "Model restore error!", e10);
        }
    }
}
