package defpackage;

import com.looksery.sdk.LSCoreManagerWrapper;
import com.looksery.sdk.domain.ComplexEffectDescriptor;
import com.looksery.sdk.domain.ComplexEffectFormat;
import com.looksery.sdk.domain.CoreConfiguration;
import com.looksery.sdk.domain.Size;
import java.util.Objects;

/* JADX INFO: renamed from: Zfe, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes4.dex */
public final class C13032Zfe implements InterfaceC20436fe8 {
    public final ComplexEffectDescriptor a;
    public final /* synthetic */ C14286age b;

    public C13032Zfe(C14286age c14286age, String str, String str2) {
        this.b = c14286age;
        this.a = ComplexEffectDescriptor.newBuilder(str, str2, ComplexEffectFormat.ARCHIVE).build();
    }

    @Override // defpackage.InterfaceC20436fe8
    public final void a(int i, int i2) throws C3137Gbe {
        C14286age c14286age = this.b;
        int i3 = c14286age.g + 1;
        c14286age.g = i3;
        if (i3 == 1) {
            Objects.requireNonNull(c14286age);
            try {
                LSCoreManagerWrapper lSCoreManagerWrapper = new LSCoreManagerWrapper(c14286age.a, (CoreConfiguration) c14286age.f.getValue());
                lSCoreManagerWrapper.setShouldCatchNativeExceptions(true);
                lSCoreManagerWrapper.setNativeExceptionListener(c14286age.d);
                lSCoreManagerWrapper.setInputImageSize(new Size(i, i2));
                lSCoreManagerWrapper.setCameraParams(59.0f, -1.0f, 0);
                c14286age.h = lSCoreManagerWrapper;
            } catch (Exception e) {
                InterfaceC34329qs5 interfaceC34329qs5 = c14286age.c;
                EnumC4171Ibe enumC4171Ibe = EnumC4171Ibe.NORMAL;
                R90 r90 = c14286age.b;
                Objects.requireNonNull(r90);
                interfaceC34329qs5.c(enumC4171Ibe, e, new L90(r90, "SharedLensCoreRendererProvider"));
                throw new C3137Gbe(e);
            }
        }
    }

    @Override // defpackage.InterfaceC20436fe8
    public final void b(int i, int i2, int i3, float[] fArr) {
        LSCoreManagerWrapper lSCoreManagerWrapper = this.b.h;
        if (lSCoreManagerWrapper == null) {
            return;
        }
        lSCoreManagerWrapper.drawTexture(i, i2, i3, fArr);
    }

    @Override // defpackage.InterfaceC20436fe8
    public final int c(int i, long j, int i2, float[] fArr, float[] fArr2, int i3, int i4) {
        Integer numValueOf;
        LSCoreManagerWrapper lSCoreManagerWrapper = this.b.h;
        if (lSCoreManagerWrapper == null) {
            numValueOf = null;
        } else {
            lSCoreManagerWrapper.applyComplexEffect(this.a);
            numValueOf = Integer.valueOf(lSCoreManagerWrapper.processTextureToTexture(i, j, i2, fArr, fArr2, i3, i4));
        }
        if (numValueOf != null) {
            return numValueOf.intValue();
        }
        throw new IllegalStateException("Renderer must be set up first");
    }

    @Override // defpackage.InterfaceC20436fe8
    public final void release() {
        C14286age c14286age = this.b;
        int i = c14286age.g - 1;
        c14286age.g = i;
        if (i <= 0) {
            LSCoreManagerWrapper lSCoreManagerWrapper = c14286age.h;
            if (lSCoreManagerWrapper != null) {
                lSCoreManagerWrapper.release();
            }
            this.b.h = null;
        }
    }
}
