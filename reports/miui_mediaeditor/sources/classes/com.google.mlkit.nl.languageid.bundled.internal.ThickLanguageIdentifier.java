package com.google.mlkit.nl.languageid.bundled.internal;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import com.google.android.gms.internal.mlkit_language_id_bundled.a;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.nl.languageid.IdentifiedLanguage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import u6.b;
import x3.i;

/* JADX INFO: loaded from: classes.dex */
public class ThickLanguageIdentifier implements b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f3902c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f3903a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f3904b;

    public ThickLanguageIdentifier(Context context) {
        this.f3903a = context;
    }

    private native void nativeDestroy(long j);

    private native IdentifiedLanguage[] nativeIdentifyPossibleLanguages(long j, byte[] bArr, float f);

    private native long nativeInitFromBuffer(MappedByteBuffer mappedByteBuffer, long j);

    @Override // u6.b
    public final void a() {
        long j = this.f3904b;
        if (j == 0) {
            return;
        }
        nativeDestroy(j);
        this.f3904b = 0L;
    }

    @Override // u6.b
    public final ArrayList b(String str, float f) {
        i.k(this.f3904b != 0);
        IdentifiedLanguage[] identifiedLanguageArrNativeIdentifyPossibleLanguages = nativeIdentifyPossibleLanguages(this.f3904b, str.getBytes(a.f2490a), f);
        ArrayList arrayList = new ArrayList();
        for (IdentifiedLanguage identifiedLanguage : identifiedLanguageArrNativeIdentifyPossibleLanguages) {
            arrayList.add(new IdentifiedLanguage(identifiedLanguage.f3900a, identifiedLanguage.f3901b));
        }
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:55:0x0076 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public final void c() throws MlKitException {
        i.k(this.f3904b == 0);
        synchronized (ThickLanguageIdentifier.class) {
            try {
                if (!f3902c) {
                    try {
                        System.loadLibrary("language_id_l2c_jni");
                        f3902c = true;
                    } catch (UnsatisfiedLinkError e10) {
                        throw new MlKitException(13, "Couldn't load language identification library.", e10);
                    }
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
        try {
            AssetFileDescriptor assetFileDescriptorOpenFd = this.f3903a.getAssets().openFd("tflite_langid.tflite.jpg");
            try {
                FileChannel channel = new FileInputStream(assetFileDescriptorOpenFd.getFileDescriptor()).getChannel();
                try {
                    long jNativeInitFromBuffer = nativeInitFromBuffer(channel.map(FileChannel.MapMode.READ_ONLY, assetFileDescriptorOpenFd.getStartOffset(), assetFileDescriptorOpenFd.getDeclaredLength()), assetFileDescriptorOpenFd.getDeclaredLength());
                    this.f3904b = jNativeInitFromBuffer;
                    if (jNativeInitFromBuffer == 0) {
                        throw new MlKitException("Couldn't load language identification model", 13);
                    }
                    channel.close();
                    assetFileDescriptorOpenFd.close();
                    return;
                } catch (Throwable th3) {
                    if (channel != null) {
                        try {
                            channel.close();
                        } catch (Throwable th4) {
                            th3.addSuppressed(th4);
                        }
                    }
                    throw th3;
                }
            } catch (Throwable th5) {
                if (assetFileDescriptorOpenFd != null) {
                    try {
                        assetFileDescriptorOpenFd.close();
                    } catch (Throwable th6) {
                        th5.addSuppressed(th6);
                    }
                }
                throw th5;
            }
            if (assetFileDescriptorOpenFd != null) {
                assetFileDescriptorOpenFd.close();
            }
            throw th5;
        } catch (IOException e11) {
            throw new MlKitException(13, "Couldn't open language identification model file", e11);
        }
    }
}
