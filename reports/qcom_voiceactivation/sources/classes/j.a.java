package p009j;

import android.os.ParcelFileDescriptor;
import java.io.IOException;

/* JADX INFO: compiled from: AudioSink.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ParcelFileDescriptor[] f776a;

    public a() {
        try {
            this.f776a = ParcelFileDescriptor.createReliableSocketPair();
        } catch (IOException unused) {
        }
    }

    public void a() {
        ParcelFileDescriptor[] parcelFileDescriptorArr = this.f776a;
        if (parcelFileDescriptorArr == null) {
            return;
        }
        try {
            ParcelFileDescriptor parcelFileDescriptor = parcelFileDescriptorArr[0];
            if (parcelFileDescriptor != null) {
                parcelFileDescriptor.close();
            }
            ParcelFileDescriptor parcelFileDescriptor2 = this.f776a[1];
            if (parcelFileDescriptor2 != null) {
                parcelFileDescriptor2.close();
            }
        } catch (IOException unused) {
        } finally {
            this.f776a = null;
        }
    }

    public ParcelFileDescriptor b() {
        ParcelFileDescriptor parcelFileDescriptor;
        ParcelFileDescriptor[] parcelFileDescriptorArr = this.f776a;
        if (parcelFileDescriptorArr == null || (parcelFileDescriptor = parcelFileDescriptorArr[0]) == null) {
            return null;
        }
        return parcelFileDescriptor;
    }

    public ParcelFileDescriptor c() {
        ParcelFileDescriptor parcelFileDescriptor;
        ParcelFileDescriptor[] parcelFileDescriptorArr = this.f776a;
        if (parcelFileDescriptorArr == null || (parcelFileDescriptor = parcelFileDescriptorArr[1]) == null) {
            return null;
        }
        return parcelFileDescriptor;
    }
}
