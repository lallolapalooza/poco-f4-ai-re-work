package com.faceunity.core.cache;

import android.graphics.Bitmap;
import ch.qos.logback.core.CoreConstants;
import com.faceunity.core.entity.ImageBeautyData;
import com.faceunity.core.enumeration.FUInputBufferEnum;
import com.faceunity.core.enumeration.ProcessMode;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.e;

/* JADX INFO: loaded from: classes.dex */
public final class CacheData {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ImageBeautyData f1265a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f1266b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ProcessMode f1267c;

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001¨\u0006\u0002"}, d2 = {"Lcom/faceunity/core/cache/CacheData$CacheType;", "", "fu_core_xiaomiRelease"}, k = 1, mv = {1, 7, 1})
    public enum CacheType {
        ACTION,
        IMAGE
    }

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final FUInputBufferEnum f1269a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final byte[] f1270b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final byte[] f1271c;
        public final byte[] d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Bitmap f1272e;

        public a(FUInputBufferEnum inputBufferType, byte[] bArr, byte[] bArr2, byte[] bArr3, Bitmap bitmap) {
            e.f(inputBufferType, "inputBufferType");
            this.f1269a = inputBufferType;
            this.f1270b = bArr;
            this.f1271c = bArr2;
            this.d = bArr3;
            this.f1272e = bitmap;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.f1269a == aVar.f1269a && e.a(this.f1270b, aVar.f1270b) && e.a(this.f1271c, aVar.f1271c) && e.a(this.d, aVar.d) && e.a(this.f1272e, aVar.f1272e);
        }

        public final int hashCode() {
            int iHashCode = this.f1269a.hashCode() * 31;
            byte[] bArr = this.f1270b;
            int iHashCode2 = (iHashCode + (bArr == null ? 0 : Arrays.hashCode(bArr))) * 31;
            byte[] bArr2 = this.f1271c;
            int iHashCode3 = (iHashCode2 + (bArr2 == null ? 0 : Arrays.hashCode(bArr2))) * 31;
            byte[] bArr3 = this.d;
            int iHashCode4 = (iHashCode3 + (bArr3 == null ? 0 : Arrays.hashCode(bArr3))) * 31;
            Bitmap bitmap = this.f1272e;
            return iHashCode4 + (bitmap != null ? bitmap.hashCode() : 0);
        }

        public final String toString() {
            return "FUCacheImageBuffer(inputBufferType=" + this.f1269a + ", buffer=" + Arrays.toString(this.f1270b) + ", buffer1=" + Arrays.toString(this.f1271c) + ", buffer2=" + Arrays.toString(this.d) + ", bitmap=" + this.f1272e + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }
    }

    public CacheData(CacheType cacheType, ImageBeautyData imageBeautyData, a aVar, ProcessMode processMode) {
        e.f(processMode, "processMode");
        this.f1265a = imageBeautyData;
        this.f1266b = aVar;
        this.f1267c = processMode;
    }
}
