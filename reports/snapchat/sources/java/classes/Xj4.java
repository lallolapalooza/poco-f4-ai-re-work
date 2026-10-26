package defpackage;

import android.content.Context;
import com.looksery.sdk.InstrumentationDelegatesFactory;
import com.looksery.sdk.LSCoreManagerWrapper;
import com.looksery.sdk.domain.ComplexEffectDescriptor;
import com.looksery.sdk.domain.CoreConfiguration;
import com.looksery.sdk.domain.Size;
import com.looksery.sdk.listener.LookseryNativeExceptionListener;
import java.util.Objects;

/* JADX INFO: renamed from: Xj4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes4.dex */
public final class C12070Xj4 implements InterfaceC20436fe8 {
    public final Context a;
    public final R90 b;
    public final InterfaceC34329qs5 c;
    public final ComplexEffectDescriptor d;
    public final LookseryNativeExceptionListener e;
    public final InstrumentationDelegatesFactory f;
    public LSCoreManagerWrapper g;

    public C12070Xj4(Context context, R90 r90, InterfaceC34329qs5 interfaceC34329qs5, ComplexEffectDescriptor complexEffectDescriptor, LookseryNativeExceptionListener lookseryNativeExceptionListener) {
        C5883Ljf c5883Ljf = C5883Ljf.a0;
        this.a = context;
        this.b = r90;
        this.c = interfaceC34329qs5;
        this.d = complexEffectDescriptor;
        this.e = lookseryNativeExceptionListener;
        this.f = c5883Ljf;
    }

    @Override // defpackage.InterfaceC20436fe8
    public final void a(int i, int i2) throws C3137Gbe {
        try {
            LSCoreManagerWrapper lSCoreManagerWrapper = new LSCoreManagerWrapper(this.a, new CoreConfiguration.CoreConfigurationBuilder(this.a).setDisableTracking(true).setInstrumentationDelegatesFactory(this.f).build());
            lSCoreManagerWrapper.setShouldCatchNativeExceptions(true);
            lSCoreManagerWrapper.setNativeExceptionListener(this.e);
            lSCoreManagerWrapper.setInputImageSize(new Size(i, i2));
            lSCoreManagerWrapper.setCameraParams(59.0f, -1.0f, 0);
            lSCoreManagerWrapper.applyComplexEffect(this.d);
            this.g = lSCoreManagerWrapper;
        } catch (Exception e) {
            InterfaceC34329qs5 interfaceC34329qs5 = this.c;
            EnumC4171Ibe enumC4171Ibe = EnumC4171Ibe.NORMAL;
            R90 r90 = this.b;
            Objects.requireNonNull(r90);
            interfaceC34329qs5.c(enumC4171Ibe, e, new L90(r90, "DefaultLensCoreRendererProvider"));
            throw new C3137Gbe(e);
        }
    }

    @Override // defpackage.InterfaceC20436fe8
    public final void b(int i, int i2, int i3, float[] fArr) {
        LSCoreManagerWrapper lSCoreManagerWrapper = this.g;
        if (lSCoreManagerWrapper != null) {
            lSCoreManagerWrapper.drawTexture(i, i2, i3, fArr);
        } else {
            AbstractC20686fqi.J("lsCoreManagerWrapper");
            throw null;
        }
    }

    @Override // defpackage.InterfaceC20436fe8
    public final int c(int i, long j, int i2, float[] fArr, float[] fArr2, int i3, int i4) {
        LSCoreManagerWrapper lSCoreManagerWrapper = this.g;
        if (lSCoreManagerWrapper != null) {
            return lSCoreManagerWrapper.processTextureToTexture(i, j, i2, fArr, fArr2, i3, i4);
        }
        AbstractC20686fqi.J("lsCoreManagerWrapper");
        throw null;
    }

    @Override // defpackage.InterfaceC20436fe8
    public final void release() {
        LSCoreManagerWrapper lSCoreManagerWrapper = this.g;
        if (lSCoreManagerWrapper != null) {
            lSCoreManagerWrapper.release();
        } else {
            AbstractC20686fqi.J("lsCoreManagerWrapper");
            throw null;
        }
    }
}
