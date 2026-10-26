package defpackage;

import com.snapchat.android.native_specs_crypto_lib.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ZFe extends AbstractC17186d18 implements InterfaceC19898fD6 {
    public final /* synthetic */ int a;
    public final /* synthetic */ AbstractC13769aGe b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ZFe(AbstractC13769aGe abstractC13769aGe, int i) {
        super(0);
        this.a = i;
        this.b = abstractC13769aGe;
    }

    public final C1748Djg a() {
        switch (this.a) {
            case 0:
                return C1748Djg.v.M(this.b.getContext(), R.style.TextAppearance_Subtitle2_Gray50);
            default:
                return C1748Djg.v.M(this.b.getContext(), R.style.TextAppearance_Title1);
        }
    }

    @Override // defpackage.InterfaceC19898fD6
    public final /* bridge */ /* synthetic */ Object invoke() {
        switch (this.a) {
            case 0:
                break;
            default:
                break;
        }
        return a();
    }
}
