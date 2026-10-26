package j2;

import android.graphics.Bitmap;
import ch.qos.logback.core.CoreConstants;
import com.faceunity.core.enumeration.CameraFacingEnum;
import com.faceunity.core.enumeration.FUExternalInputEnum;
import com.faceunity.core.enumeration.FUInputBufferEnum;
import com.faceunity.core.enumeration.FUTransformMatrixEnum;
import com.faceunity.core.enumeration.ProcessMode;
import java.util.Arrays;
import kotlin.jvm.internal.e;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f11714a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f11715b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f11717e;
    public int g;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ProcessMode f11716c = ProcessMode.FU_IMAGE_BEAUTY_MODE_UNKNOWN;
    public boolean d = true;
    public final b f = new b();

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final FUInputBufferEnum f11718a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public byte[] f11719b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final byte[] f11720c;
        public final byte[] d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Bitmap f11721e;

        public a(FUInputBufferEnum inputBufferType, int i10) {
            e.f(inputBufferType, "inputBufferType");
            this.f11718a = inputBufferType;
            this.f11719b = null;
            this.f11720c = null;
            this.d = null;
            this.f11721e = null;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.f11718a == aVar.f11718a && e.a(this.f11719b, aVar.f11719b) && e.a(this.f11720c, aVar.f11720c) && e.a(this.d, aVar.d) && e.a(this.f11721e, aVar.f11721e);
        }

        public final int hashCode() {
            int iHashCode = this.f11718a.hashCode() * 31;
            byte[] bArr = this.f11719b;
            int iHashCode2 = (iHashCode + (bArr == null ? 0 : Arrays.hashCode(bArr))) * 31;
            byte[] bArr2 = this.f11720c;
            int iHashCode3 = (iHashCode2 + (bArr2 == null ? 0 : Arrays.hashCode(bArr2))) * 31;
            byte[] bArr3 = this.d;
            int iHashCode4 = (iHashCode3 + (bArr3 == null ? 0 : Arrays.hashCode(bArr3))) * 31;
            Bitmap bitmap = this.f11721e;
            return iHashCode4 + (bitmap != null ? bitmap.hashCode() : 0);
        }

        public final String toString() {
            return "FUImageBuffer(inputBufferType=" + this.f11718a + ", buffer=" + Arrays.toString(this.f11719b) + ", buffer1=" + Arrays.toString(this.f11720c) + ", buffer2=" + Arrays.toString(this.d) + ", bitmap=" + this.f11721e + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final FUExternalInputEnum f11722a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f11723b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f11724c;
        public final CameraFacingEnum d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final FUTransformMatrixEnum f11725e;
        public final FUTransformMatrixEnum f;
        public final boolean g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final boolean f11726h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final FUTransformMatrixEnum f11727i;

        public b() {
            FUExternalInputEnum fUExternalInputEnum = FUExternalInputEnum.EXTERNAL_INPUT_TYPE_CAMERA;
            CameraFacingEnum cameraFacingEnum = CameraFacingEnum.CAMERA_FRONT;
            FUTransformMatrixEnum fUTransformMatrixEnum = FUTransformMatrixEnum.CCROT0;
            this.f11722a = fUExternalInputEnum;
            this.f11723b = 0;
            this.f11724c = 0;
            this.d = cameraFacingEnum;
            this.f11725e = fUTransformMatrixEnum;
            this.f = fUTransformMatrixEnum;
            this.g = false;
            this.f11726h = false;
            this.f11727i = fUTransformMatrixEnum;
        }
    }

    public c(int i10, int i11) {
        this.f11714a = i10;
        this.f11715b = i11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return this.f11714a == cVar.f11714a && this.f11715b == cVar.f11715b;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f11715b) + (Integer.hashCode(this.f11714a) * 31);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("FURenderInputData(width=");
        sb2.append(this.f11714a);
        sb2.append(", height=");
        return androidx.view.a.i(sb2, this.f11715b, CoreConstants.RIGHT_PARENTHESIS_CHAR);
    }
}
