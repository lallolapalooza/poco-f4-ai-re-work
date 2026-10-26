package f3;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f3608a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f3609b;
    public final g3.a c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public List<h3.a> f3610d = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List<h3.a> f3611e = new ArrayList();

    public interface a {
    }

    public d(Context context, a aVar) {
        IOException e10;
        MappedByteBuffer map;
        this.f3608a = context;
        this.f3609b = aVar;
        try {
            AssetFileDescriptor assetFileDescriptorOpenFd = context.getAssets().openFd("aiautobrt.tflite");
            FileInputStream fileInputStream = new FileInputStream(assetFileDescriptorOpenFd.getFileDescriptor());
            try {
                map = fileInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, assetFileDescriptorOpenFd.getStartOffset(), assetFileDescriptorOpenFd.getDeclaredLength());
                try {
                    fileInputStream.close();
                } catch (IOException e11) {
                    e10 = e11;
                    gb.c.i(this.f3608a);
                    t9.a aVar2 = t9.a.ERROR;
                    gb.d.f3839a.a(aVar2, "IndividualBrt-ModelController", "Failed to get model file!", e10);
                    gb.c.c.a(aVar2, "IndividualBrt-ModelController", "Failed to get model file!", e10);
                }
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException e12) {
            e10 = e12;
            map = null;
        }
        File file = new File(this.f3608a.getFilesDir(), "checkpoint");
        this.c = new g3.a(context, map, file.exists() ? file : null);
    }
}
