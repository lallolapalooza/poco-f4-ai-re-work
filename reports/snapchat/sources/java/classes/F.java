package defpackage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.FrameLayout;
import app.aifactory.ai.face2face.F2FTextAnimator;
import app.aifactory.ai.faceneutrality.FaceNeutrality;
import app.aifactory.ai.faceneutrality.FaceNeutralityResult;
import app.aifactory.ai.facesegmentation.FSFaceSegmentation;
import app.aifactory.ai.scenariossearch.SSScenariosSearch;
import app.aifactory.ai.scenariossearch.SearchContext;
import com.snap.lenses.app.snappable.SnappableMetadataHttpInterface;
import com.snap.music.core.composer.FavoritesService;
import com.snap.previewtools.caption.ui.CaptionEditTextView;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public final class F extends AbstractC17186d18 implements InterfaceC19898fD6 {
    public final /* synthetic */ Object R;
    public final /* synthetic */ Object S;
    public final /* synthetic */ Object T;
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ F(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, int i) {
        super(0);
        this.a = i;
        this.R = obj;
        this.S = obj2;
        this.b = obj3;
        this.c = obj4;
        this.T = obj5;
    }

    public final AbstractC31813oq5 a() {
        switch (this.a) {
            case 10:
                C22739hVe c22739hVe = new C22739hVe();
                VO0 vo0 = (VO0) this.R;
                C43780yVe c43780yVe = (C43780yVe) this.S;
                EnumC14069aVe enumC14069aVe = (EnumC14069aVe) this.b;
                Long l = (Long) this.c;
                ZUe zUe = (ZUe) this.T;
                VO0.m(vo0, c22739hVe, c43780yVe);
                c22739hVe.g0 = enumC14069aVe;
                c22739hVe.i0 = l;
                c22739hVe.h0 = zUe;
                return c22739hVe;
            default:
                C20552fk4 c20552fk4 = (C20552fk4) this.R;
                C4245If8 c4245If8 = new C4245If8();
                C20552fk4.a(c20552fk4, c4245If8, (C13529a4e) this.S, ((C33792qR5) this.b).b, (EB) this.c, (C17376dB) this.T);
                return c4245If8;
        }
    }

    public final FavoritesService b() {
        switch (this.a) {
            case 3:
                return ((C34091qg7) this.R).k((FR7) this.S, (XCd) this.b, (InterfaceC6336Mgc) this.c, (InterfaceC6336Mgc) this.T);
            default:
                return ((C34091qg7) this.R).k((FR7) this.S, ((C24223ii3) this.b).S, (InterfaceC6336Mgc) this.c, (InterfaceC6336Mgc) this.T);
        }
    }

    @Override // defpackage.InterfaceC19898fD6
    public final AbstractC33080pre invoke() {
        final int i = 0;
        final int i2 = 1;
        switch (this.a) {
            case 6:
                InterfaceC3882Hn2 interfaceC3882Hn2 = (InterfaceC3882Hn2) ((InterfaceC6336Mgc) ((C1109Cde) this.R).b).get();
                final C23558iAf c23558iAf = (C23558iAf) ((C1109Cde) this.R).Z;
                final InterfaceC17244d47 interfaceC17244d47 = (InterfaceC17244d47) this.S;
                final int i3 = 3;
                AbstractC18956eS2 abstractC18956eS2I = ((C8165Pua) interfaceC3882Hn2).b(DM8.CONTENT_MANAGER).I(new InterfaceC44463z4() { // from class: Zua
                    @Override // defpackage.InterfaceC44463z4
                    public final void run() {
                        switch (i3) {
                            case 0:
                                Z91.t(c23558iAf, "config_init", interfaceC17244d47);
                                break;
                            case 1:
                                Z91.t(c23558iAf, "support_interface_init", interfaceC17244d47);
                                break;
                            case 2:
                                Z91.t(c23558iAf, "cache_controller_load", interfaceC17244d47);
                                break;
                            case 3:
                                Z91.t(c23558iAf, "native_client_load", interfaceC17244d47);
                                break;
                            case 4:
                                Z91.t(c23558iAf, "cof_prime_cache", interfaceC17244d47);
                                break;
                            case 5:
                                Z91.t(c23558iAf, "total", interfaceC17244d47);
                                break;
                            default:
                                Z91.t(c23558iAf, "graphene_init", interfaceC17244d47);
                                break;
                        }
                    }
                });
                final C23558iAf c23558iAf2 = (C23558iAf) ((C1109Cde) this.R).Z;
                final C47 c47 = (C47) ((InterfaceC17244d47) this.S);
                final int i4 = 6;
                AbstractC33080pre abstractC33080preS0 = abstractC18956eS2I.h(c47.k()).I(new InterfaceC44463z4() { // from class: Zua
                    @Override // defpackage.InterfaceC44463z4
                    public final void run() {
                        switch (i4) {
                            case 0:
                                Z91.t(c23558iAf2, "config_init", c47);
                                break;
                            case 1:
                                Z91.t(c23558iAf2, "support_interface_init", c47);
                                break;
                            case 2:
                                Z91.t(c23558iAf2, "cache_controller_load", c47);
                                break;
                            case 3:
                                Z91.t(c23558iAf2, "native_client_load", c47);
                                break;
                            case 4:
                                Z91.t(c23558iAf2, "cof_prime_cache", c47);
                                break;
                            case 5:
                                Z91.t(c23558iAf2, "total", c47);
                                break;
                            default:
                                Z91.t(c23558iAf2, "graphene_init", c47);
                                break;
                        }
                    }
                }).s0(C38814uUg.a);
                C1109Cde c1109Cde = (C1109Cde) this.R;
                final C23558iAf c23558iAf3 = (C23558iAf) c1109Cde.Z;
                final InterfaceC17244d47 interfaceC17244d48 = (InterfaceC17244d47) this.S;
                int i5 = 9;
                AbstractC33080pre abstractC33080preB = abstractC33080preS0.Q(new O6h((InterfaceC6336Mgc) c1109Cde.R, (C35264rd3) this.b, i5)).B(new InterfaceC44463z4() { // from class: Zua
                    @Override // defpackage.InterfaceC44463z4
                    public final void run() {
                        switch (i2) {
                            case 0:
                                Z91.t(c23558iAf3, "config_init", interfaceC17244d48);
                                break;
                            case 1:
                                Z91.t(c23558iAf3, "support_interface_init", interfaceC17244d48);
                                break;
                            case 2:
                                Z91.t(c23558iAf3, "cache_controller_load", interfaceC17244d48);
                                break;
                            case 3:
                                Z91.t(c23558iAf3, "native_client_load", interfaceC17244d48);
                                break;
                            case 4:
                                Z91.t(c23558iAf3, "cof_prime_cache", interfaceC17244d48);
                                break;
                            case 5:
                                Z91.t(c23558iAf3, "total", interfaceC17244d48);
                                break;
                            default:
                                Z91.t(c23558iAf3, "graphene_init", interfaceC17244d48);
                                break;
                        }
                    }
                });
                C1109Cde c1109Cde2 = (C1109Cde) this.R;
                final C23558iAf c23558iAf4 = (C23558iAf) c1109Cde2.Z;
                final InterfaceC17244d47 interfaceC17244d49 = (InterfaceC17244d47) this.S;
                final int i6 = 4;
                AbstractC33080pre abstractC33080preB2 = abstractC33080preB.F(new C14582ava((InterfaceC6336Mgc) c1109Cde2.V, i)).B(new InterfaceC44463z4() { // from class: Zua
                    @Override // defpackage.InterfaceC44463z4
                    public final void run() {
                        switch (i6) {
                            case 0:
                                Z91.t(c23558iAf4, "config_init", interfaceC17244d49);
                                break;
                            case 1:
                                Z91.t(c23558iAf4, "support_interface_init", interfaceC17244d49);
                                break;
                            case 2:
                                Z91.t(c23558iAf4, "cache_controller_load", interfaceC17244d49);
                                break;
                            case 3:
                                Z91.t(c23558iAf4, "native_client_load", interfaceC17244d49);
                                break;
                            case 4:
                                Z91.t(c23558iAf4, "cof_prime_cache", interfaceC17244d49);
                                break;
                            case 5:
                                Z91.t(c23558iAf4, "total", interfaceC17244d49);
                                break;
                            default:
                                Z91.t(c23558iAf4, "graphene_init", interfaceC17244d49);
                                break;
                        }
                    }
                });
                final C23558iAf c23558iAf5 = (C23558iAf) ((C1109Cde) this.R).Z;
                final InterfaceC17244d47 interfaceC17244d410 = (InterfaceC17244d47) this.S;
                AbstractC33080pre abstractC33080preB3 = abstractC33080preB2.Q(C22786hY2.W).B(new InterfaceC44463z4() { // from class: Zua
                    @Override // defpackage.InterfaceC44463z4
                    public final void run() {
                        switch (i) {
                            case 0:
                                Z91.t(c23558iAf5, "config_init", interfaceC17244d410);
                                break;
                            case 1:
                                Z91.t(c23558iAf5, "support_interface_init", interfaceC17244d410);
                                break;
                            case 2:
                                Z91.t(c23558iAf5, "cache_controller_load", interfaceC17244d410);
                                break;
                            case 3:
                                Z91.t(c23558iAf5, "native_client_load", interfaceC17244d410);
                                break;
                            case 4:
                                Z91.t(c23558iAf5, "cof_prime_cache", interfaceC17244d410);
                                break;
                            case 5:
                                Z91.t(c23558iAf5, "total", interfaceC17244d410);
                                break;
                            default:
                                Z91.t(c23558iAf5, "graphene_init", interfaceC17244d410);
                                break;
                        }
                    }
                });
                C1109Cde c1109Cde3 = (C1109Cde) this.R;
                final C23558iAf c23558iAf6 = (C23558iAf) c1109Cde3.Z;
                final InterfaceC17244d47 interfaceC17244d411 = (InterfaceC17244d47) this.S;
                final int i7 = 2;
                AbstractC33080pre abstractC33080preW = abstractC33080preB3.A(new SBf((InterfaceC6336Mgc) c1109Cde3.X, i5)).B(new InterfaceC44463z4() { // from class: Zua
                    @Override // defpackage.InterfaceC44463z4
                    public final void run() {
                        switch (i7) {
                            case 0:
                                Z91.t(c23558iAf6, "config_init", interfaceC17244d411);
                                break;
                            case 1:
                                Z91.t(c23558iAf6, "support_interface_init", interfaceC17244d411);
                                break;
                            case 2:
                                Z91.t(c23558iAf6, "cache_controller_load", interfaceC17244d411);
                                break;
                            case 3:
                                Z91.t(c23558iAf6, "native_client_load", interfaceC17244d411);
                                break;
                            case 4:
                                Z91.t(c23558iAf6, "cof_prime_cache", interfaceC17244d411);
                                break;
                            case 5:
                                Z91.t(c23558iAf6, "total", interfaceC17244d411);
                                break;
                            default:
                                Z91.t(c23558iAf6, "graphene_init", interfaceC17244d411);
                                break;
                        }
                    }
                }).W(((C44135ync) this.T).d());
                C1109Cde c1109Cde4 = (C1109Cde) this.R;
                C23558iAf c23558iAf7 = (C23558iAf) c1109Cde4.Z;
                final C23558iAf c23558iAf8 = (C23558iAf) c1109Cde4.Y;
                final InterfaceC17244d47 interfaceC17244d412 = (InterfaceC17244d47) this.S;
                final int i8 = 5;
                return abstractC33080preW.Q(new C36598si((InterfaceC6336Mgc) c1109Cde4.W, (InterfaceC6336Mgc) c1109Cde4.X, c23558iAf7, interfaceC17244d412, (InterfaceC6336Mgc) c1109Cde4.S, (InterfaceC6336Mgc) c1109Cde4.T, 10)).B(new InterfaceC44463z4() { // from class: Zua
                    @Override // defpackage.InterfaceC44463z4
                    public final void run() {
                        switch (i8) {
                            case 0:
                                Z91.t(c23558iAf8, "config_init", interfaceC17244d412);
                                break;
                            case 1:
                                Z91.t(c23558iAf8, "support_interface_init", interfaceC17244d412);
                                break;
                            case 2:
                                Z91.t(c23558iAf8, "cache_controller_load", interfaceC17244d412);
                                break;
                            case 3:
                                Z91.t(c23558iAf8, "native_client_load", interfaceC17244d412);
                                break;
                            case 4:
                                Z91.t(c23558iAf8, "cof_prime_cache", interfaceC17244d412);
                                break;
                            case 5:
                                Z91.t(c23558iAf8, "total", interfaceC17244d412);
                                break;
                            default:
                                Z91.t(c23558iAf8, "graphene_init", interfaceC17244d412);
                                break;
                        }
                    }
                }).W(((C44135ync) this.T).d());
            case 7:
                C33924qXh c33924qXh = (C33924qXh) this.R;
                Uri uri = (Uri) this.S;
                Set set = (Set) this.b;
                Set set2 = (Set) this.c;
                return c33924qXh.c.a(uri, set, true, set2).Q(new C35360ri(c33924qXh, set, set2, (String) this.T, 29)).v(new LC2(c33924qXh, c33924qXh.a(uri), 10)).j();
            default:
                C38269u3b c38269u3b = (C38269u3b) ((BVh) this.R).d(C38269u3b.class);
                F3b f3b = (F3b) this.S;
                AbstractC13945aPa abstractC13945aPaT1 = ((InterfaceC44571z97) ((C19758f66) this.b).c).c().N1().t1((ACd) this.c);
                final AbstractC32045p1g abstractC32045p1g = c38269u3b.c;
                final C14561au9 c14561au9 = (C14561au9) this.T;
                Objects.requireNonNull(f3b);
                SMi.c(abstractC13945aPaT1.z0(new I93() { // from class: h3b
                    @Override // defpackage.I93
                    public final void x(Object obj) {
                        switch (i) {
                            case 0:
                                AbstractC32045p1g abstractC32045p1g2 = abstractC32045p1g;
                                C14561au9 c14561au10 = c14561au9;
                                abstractC32045p1g2.p(obj);
                                if (!c14561au10.s0()) {
                                    c14561au10.c(obj);
                                }
                                break;
                            default:
                                AbstractC32045p1g abstractC32045p1g3 = abstractC32045p1g;
                                C14561au9 c14561au11 = c14561au9;
                                Throwable th = (Throwable) obj;
                                abstractC32045p1g3.e(th);
                                if (!c14561au11.s0()) {
                                    c14561au11.e(th);
                                }
                                break;
                        }
                    }
                }).x0(new I93() { // from class: h3b
                    @Override // defpackage.I93
                    public final void x(Object obj) {
                        switch (i2) {
                            case 0:
                                AbstractC32045p1g abstractC32045p1g2 = abstractC32045p1g;
                                C14561au9 c14561au10 = c14561au9;
                                abstractC32045p1g2.p(obj);
                                if (!c14561au10.s0()) {
                                    c14561au10.c(obj);
                                }
                                break;
                            default:
                                AbstractC32045p1g abstractC32045p1g3 = abstractC32045p1g;
                                C14561au9 c14561au11 = c14561au9;
                                Throwable th = (Throwable) obj;
                                abstractC32045p1g3.e(th);
                                if (!c14561au11.s0()) {
                                    c14561au11.e(th);
                                }
                                break;
                        }
                    }
                }).t0(new HTd(abstractC32045p1g, c14561au9, 12)).X0().Y().e0(), ((F3b) this.S).b.b(), null);
                return AbstractC33080pre.P(E3b.c);
        }
    }

    @Override // defpackage.InterfaceC19898fD6
    public final Object invoke() {
        AbstractC13945aPa abstractC13945aPaD;
        TO7 to7;
        switch (this.a) {
            case 0:
                FaceNeutralityResult neutralizedFace = ((FaceNeutrality) this.R).getNeutralizedFace((Bitmap) this.b, (float[]) this.c);
                G g = (G) this.S;
                if (AbstractC19757f65.i(g, 2)) {
                    g.getTag().toString();
                    neutralizedFace.getIsApplied();
                }
                boolean isApplied = neutralizedFace.getIsApplied();
                G g2 = (G) this.S;
                if (AbstractC19757f65.i(g2, 2)) {
                    g2.getTag().toString();
                }
                G g3 = (G) this.S;
                if (AbstractC19757f65.i(g3, 2)) {
                    g3.getTag().toString();
                    Objects.toString((XN7) this.T);
                }
                XN7 xn7 = (XN7) this.T;
                if (xn7 != null) {
                    AbstractC31067oEg.x(xn7, "neutralizationcallscount", Integer.valueOf(isApplied ? 1 : 0), null, false, 12, null);
                }
                return neutralizedFace;
            case 1:
                ReentrantLock reentrantLock = ((C41143wNd) this.R).a;
                reentrantLock.lock();
                try {
                    return ((FSFaceSegmentation) this.S).segmentImage((Bitmap) this.b, (float[]) this.c, (byte[]) this.T);
                } finally {
                    reentrantLock.unlock();
                }
            case 2:
                m27invoke();
                return C38814uUg.a;
            case 3:
                return b();
            case 4:
                m27invoke();
                return C38814uUg.a;
            case 5:
                return AbstractC20686fqi.x(new C43592yM6(10004L, EnumC5080Jv6.BFS), new C43592yM6(10002L, EnumC5080Jv6.BFF), new C43592yM6(10001L, EnumC5080Jv6.SUPER_BFF), new C43592yM6(10003L, EnumC5080Jv6.BESTIES), new C43592yM6(10007L, EnumC5080Jv6.MUTUAL_BFS), new C43592yM6(10006L, EnumC5080Jv6.MUTUAL_BESTIES), new C43592yM6(10005L, EnumC5080Jv6.THEIR_BF_BUT), (E2d) ((InterfaceC6336Mgc) this.R).get(), (E2d) ((InterfaceC6336Mgc) this.S).get(), (E2d) ((InterfaceC6336Mgc) this.b).get(), (E2d) ((InterfaceC6336Mgc) this.c).get(), (E2d) ((InterfaceC6336Mgc) this.T).get());
            case 6:
                return invoke();
            case 7:
                return invoke();
            case 8:
                return b();
            case 9:
                QY3 qy3 = QY3.QUERY;
                Integer num = (Integer) this.R;
                YH yh = num != null ? (YH) ((C26293kNe) this.S).W.remove(num) : null;
                if (yh == null) {
                    yh = (YH) ((InterfaceC19898fD6) this.b).invoke();
                }
                try {
                    Object obj = this.c;
                    if (((InterfaceC22372hD6) obj) != null) {
                        ((InterfaceC22372hD6) obj).invoke(yh);
                    }
                    return ((InterfaceC22372hD6) this.T).invoke(yh);
                } finally {
                    if (yh.getType() != qy3) {
                        ((C26293kNe) this.S).V = yh;
                    }
                    Integer num2 = (Integer) this.R;
                    if (num2 != null) {
                        YH yh2 = (YH) ((C26293kNe) this.S).W.put(num2, yh);
                        if (yh2 != null) {
                            yh2.close();
                        }
                    } else {
                        yh.close();
                    }
                }
            case 10:
                return a();
            case 11:
                return a();
            case 12:
                C32297pE8 c32297pE8 = (C32297pE8) this.R;
                if (c32297pE8 != null) {
                    synchronized (c32297pE8) {
                    }
                }
                C38872uXe c38872uXe = C38872uXe.T;
                Q53 q53B = ((V53) this.S).b(M53.y);
                EnumC43053xv8 enumC43053xv8 = EnumC43053xv8.W;
                if (AbstractC20686fqi.f(Boolean.class, Boolean.TYPE) ? true : AbstractC20686fqi.f(Boolean.class, Boolean.class)) {
                    abstractC13945aPaD = q53B.a(enumC43053xv8);
                } else {
                    if (AbstractC20686fqi.f(Boolean.class, Integer.class) ? true : AbstractC20686fqi.f(Boolean.class, Integer.class)) {
                        abstractC13945aPaD = q53B.e(enumC43053xv8);
                    } else {
                        if (AbstractC20686fqi.f(Boolean.class, Long.TYPE) ? true : AbstractC20686fqi.f(Boolean.class, Long.class)) {
                            abstractC13945aPaD = q53B.c(enumC43053xv8);
                        } else {
                            if (AbstractC20686fqi.f(Boolean.class, Float.TYPE) ? true : AbstractC20686fqi.f(Boolean.class, Float.class)) {
                                abstractC13945aPaD = q53B.f(enumC43053xv8);
                            } else {
                                if (AbstractC20686fqi.f(Boolean.class, Double.TYPE) ? true : AbstractC20686fqi.f(Boolean.class, Double.class)) {
                                    abstractC13945aPaD = q53B.g(enumC43053xv8);
                                } else {
                                    if (AbstractC20686fqi.f(Boolean.class, String.class) ? true : AbstractC20686fqi.f(Boolean.class, String.class)) {
                                        abstractC13945aPaD = q53B.b(enumC43053xv8);
                                    } else {
                                        if (!(AbstractC20686fqi.f(Boolean.class, byte[].class) ? true : AbstractC20686fqi.f(Boolean.class, Byte[].class))) {
                                            throw new IllegalArgumentException(E.i("Unsupported input type: [", Boolean.class, ']'));
                                        }
                                        abstractC13945aPaD = q53B.d(enumC43053xv8);
                                    }
                                }
                            }
                        }
                    }
                }
                AbstractC13945aPa abstractC13945aPaI1 = abstractC13945aPaD.i1(NY7.p(Boolean.class));
                Object obj2 = enumC43053xv8.a.a;
                Objects.requireNonNull(obj2, "null cannot be cast to non-null type kotlin.Boolean");
                return c38872uXe.Q(abstractC13945aPaI1.G0((Boolean) obj2).j(), new UQ6((InterfaceC6336Mgc) this.b, (InterfaceC6336Mgc) this.c, (VK3) this.T, 28));
            case 13:
                AbstractC21868go3 abstractC21868go3H = C37283tG3.R.H(new C11798Wv8((R90) this.S, (XCd) this.b, (Context) this.c, (V53) this.T));
                DTc dTc = (DTc) this.R;
                C36045sG3 c36045sG3 = (C36045sG3) abstractC21868go3H;
                Objects.requireNonNull(dTc);
                c36045sG3.h = dTc;
                return c36045sG3.a().b();
            case 14:
                if (((S53) this.R).a(EnumC43053xv8.K0)) {
                    RO7 ro7 = RO7.a;
                    to7 = new TO7(2, ro7, ro7);
                } else {
                    to7 = new TO7(1, new QO7(), SO7.a);
                }
                return new C44367yz4(new C32213pA4((SnappableMetadataHttpInterface) ((InterfaceC30859o48) this.T).getValue()), (InterfaceC6336Mgc) this.S, ((S53) this.R).a(EnumC43053xv8.M0), (InterfaceC6336Mgc) this.b, to7, C12194Xp8.T, (Context) this.c);
            case 15:
                AbstractC39682vC8 abstractC39682vC8 = (AbstractC39682vC8) ((InterfaceC19898fD6) this.R).invoke();
                R90 r90 = (R90) this.S;
                C21312gM3 c21312gM3 = (C21312gM3) abstractC39682vC8;
                Objects.requireNonNull(c21312gM3);
                Objects.requireNonNull(r90);
                c21312gM3.b = r90;
                QE qe = (QE) this.b;
                Objects.requireNonNull(qe);
                c21312gM3.c = qe;
                InterfaceC11062Vk8 interfaceC11062Vk8 = (InterfaceC11062Vk8) this.c;
                Objects.requireNonNull(interfaceC11062Vk8);
                c21312gM3.T = interfaceC11062Vk8;
                c21312gM3.V = new C9801Sz(C21287gKi.U, C31007oBg.a0);
                return new C10150Tq9(((AbstractC42156xC8) c21312gM3.b()).a(), new C24224ii4((C15007bGe) this.T, 14));
            case 16:
                AbstractC22375hD9 abstractC22375hD9 = (AbstractC22375hD9) new AN3().e((AbstractC8096Pr1) this.R);
                AbstractC13945aPa abstractC13945aPaC0 = AbstractC13945aPa.C0();
                AN3 an3 = (AN3) abstractC22375hD9;
                Objects.requireNonNull(an3);
                an3.c = abstractC13945aPaC0;
                an3.R = PG9.a;
                an3.S = AbstractC13945aPa.C0();
                Boolean bool = Boolean.FALSE;
                an3.T = AbstractC13945aPa.d1(bool);
                an3.U = AbstractC13945aPa.d1(bool);
                an3.V = AbstractC13945aPa.d1(bool);
                an3.X = C43472yG6.a;
                XG9 xg9 = ((C26088kD9) ((AbstractC37717tc0) this.S)).a;
                Objects.requireNonNull(xg9);
                an3.R = xg9;
                AbstractC13945aPa abstractC13945aPa = (AbstractC13945aPa) this.b;
                Objects.requireNonNull(abstractC13945aPa);
                an3.S = abstractC13945aPa;
                an3.T = ((AbstractC13945aPa) this.c).i1(C30911o70.j0);
                AbstractC13945aPa abstractC13945aPa2 = ((C26088kD9) ((AbstractC37717tc0) this.S)).b;
                Objects.requireNonNull(abstractC13945aPa2);
                an3.U = abstractC13945aPa2;
                Boolean boolValueOf = Boolean.valueOf(((C26088kD9) ((AbstractC37717tc0) this.S)).d);
                Objects.requireNonNull(boolValueOf);
                an3.W = boolValueOf;
                AbstractC13945aPa abstractC13945aPa3 = ((C26088kD9) ((AbstractC37717tc0) this.S)).c;
                Objects.requireNonNull(abstractC13945aPa3);
                an3.V = abstractC13945aPa3;
                CG6 cg6 = (CG6) this.T;
                Objects.requireNonNull(cg6);
                an3.X = cg6;
                return an3;
            case 17:
                S70 s70 = (S70) ((InterfaceC19898fD6) this.R).invoke();
                C10422Ue4 c10422Ue4 = new C10422Ue4(new C19919fE7((Context) this.S, (LE5) this.b, (V53) this.c, (QE) this.T, 1));
                c10422Ue4.c.b(s70.i());
                return c10422Ue4;
            case 18:
                C19884fCc c19884fCc = new C19884fCc();
                c19884fCc.a = new C3393Goa();
                C19884fCc c19884fCc2 = new C19884fCc();
                c19884fCc2.a = new C2980Fte(0, 0);
                C19884fCc c19884fCc3 = new C19884fCc();
                C19884fCc c19884fCc4 = new C19884fCc();
                AbstractC19707f3f abstractC19707f3f = (AbstractC19707f3f) c19884fCc3.a;
                c19884fCc4.a = abstractC19707f3f != null ? AbstractC41048wIi.r(abstractC19707f3f) : null;
                C11038Vj4 c11038Vj4 = (C11038Vj4) this.R;
                return new WA4(c11038Vj4, c11038Vj4, (YA4) this.S, c19884fCc, c19884fCc2, (InterfaceC7632Ote) this.b, (I93) this.c, (InterfaceC19898fD6) this.T, c19884fCc3, c19884fCc4);
            case 19:
                return new S40((CG6) this.R, (GD9) this.S, (AbstractC13945aPa) this.b, (AbstractC13945aPa) this.c, (C44135ync) this.T);
            case 20:
                m27invoke();
                return C38814uUg.a;
            case 21:
                m27invoke();
                return C38814uUg.a;
            case 22:
                m27invoke();
                return C38814uUg.a;
            case 23:
                m27invoke();
                return C38814uUg.a;
            case 24:
                return invoke();
            case 25:
                m27invoke();
                return C38814uUg.a;
            case 26:
                m27invoke();
                return C38814uUg.a;
            case 27:
                m27invoke();
                return C38814uUg.a;
            case 28:
                return new C9427Sg2((Context) this.R, (C12544Yh2) this.S, (InterfaceC22695hTa) this.b, (C2708Fg2) this.c);
            default:
                m27invoke();
                return C38814uUg.a;
        }
    }

    /* JADX WARN: Code duplicated, block: B:58:0x0165  */
    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m27invoke() {
        boolean zE;
        float fFloatValue;
        C55 c55D;
        int i = 1;
        int i2 = 0;
        switch (this.a) {
            case 2:
                C39334uv c39334uv = (C39334uv) this.R;
                String str = (String) this.S;
                List list = (List) this.b;
                InterfaceC21821glg interfaceC21821glg = (InterfaceC21821glg) this.c;
                C16303cJd c16303cJd = (C16303cJd) this.T;
                Objects.requireNonNull(c39334uv);
                if (AbstractC19757f65.i(c39334uv, 2)) {
                    Objects.toString(c39334uv.a);
                }
                SSScenariosSearch sSScenariosSearch = new SSScenariosSearch();
                SSScenariosSearch sSScenariosSearch2 = (SSScenariosSearch) c39334uv.b.getAndSet(sSScenariosSearch);
                if (sSScenariosSearch2 != null) {
                    sSScenariosSearch2.close();
                }
                try {
                    if (list == null) {
                        throw new C41126wMg("null cannot be cast to non-null type java.util.Collection<T>");
                    }
                    Object[] array = list.toArray(new String[0]);
                    if (array == null) {
                        throw new C41126wMg("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                    sSScenariosSearch.init(str, (String[]) array, c16303cJd.a, new SearchContext(interfaceC21821glg, Locale.getDefault()));
                    sSScenariosSearch.setTextToLinesSplitterPtr(F2FTextAnimator.textToLinesSplitCaller());
                    return;
                } catch (Throwable th) {
                    SSScenariosSearch sSScenariosSearch3 = (SSScenariosSearch) c39334uv.b.getAndSet(null);
                    if (sSScenariosSearch3 != null) {
                        sSScenariosSearch3.close();
                    }
                    throw new C34857rId(th);
                }
            case 4:
                P32 p32 = ((I32) this.R).q1;
                if (p32 != null) {
                    F22 f22 = (F22) this.S;
                    R32 r32 = p32.a;
                    String str2 = ((C17220d32) p32.b.W).a;
                    long[] jArr = {((C17220d32) f22).b};
                    Objects.requireNonNull((C9696Stc) ((InterfaceC33048pq2) r32.c.get()));
                    r32.a.b(((InterfaceC37642tY3) r32.Z.getValue()).o("CharmsEventDispatcher:softDeleteCharm", new Q32(r32, str2, jArr, System.currentTimeMillis(), 0)).g0(C15469be.B, C27178l61.e0));
                }
                ((List) this.b).remove((F22) this.S);
                if (((List) this.b).isEmpty()) {
                    C33216pya c33216pya = ((I32) this.R).e1;
                    if (c33216pya != null) {
                        c33216pya.z(S32.V, true, false, null);
                        return;
                    } else {
                        AbstractC20686fqi.J("navigationHost");
                        throw null;
                    }
                }
                ((C26039kB0) this.c).p((List) this.b);
                InterfaceC19898fD6 interfaceC19898fD6 = (InterfaceC19898fD6) ((C19884fCc) this.T).a;
                if (interfaceC19898fD6 != null) {
                    interfaceC19898fD6.invoke();
                }
                C33216pya c33216pya2 = ((I32) this.R).e1;
                if (c33216pya2 != null) {
                    c33216pya2.z(F5.T, true, true, null);
                    return;
                } else {
                    AbstractC20686fqi.J("navigationHost");
                    throw null;
                }
            case 20:
                C10758Uv0 c10758Uv0 = (C10758Uv0) this.R;
                Activity activity = (Activity) this.S;
                EnumC29363mrb enumC29363mrb = (EnumC29363mrb) this.b;
                M90 m90 = (M90) this.c;
                InterfaceC9658Sre interfaceC9658Sre = (InterfaceC9658Sre) this.T;
                Objects.requireNonNull(c10758Uv0);
                C55 c55Z1 = O3d.T(new UQa(new CallableC9724Sv0(c10758Uv0, i2))).b2(c10758Uv0.o).J0(new C8175Pv0(c10758Uv0, activity, enumC29363mrb, i2)).t1(c10758Uv0.p).u0(C31012oC1.s).F0(UD4.s0).Z1(new C27851ldh(c10758Uv0, activity, interfaceC9658Sre, 5), new C8941Rhc(interfaceC9658Sre, 4), NY7.S, NY7.T);
                C14023aT8 c14023aT8 = C14023aT8.T;
                Objects.requireNonNull(c14023aT8);
                NY7.j(c55Z1, m90, new L90(c14023aT8, "BaseLocationPermissionsRequester"));
                return;
            case 21:
                C9610Sp6 c9610Sp6 = (C9610Sp6) this.R;
                C4940Jo6 c4940Jo6 = (C4940Jo6) this.S;
                new C2872Fo6(c9610Sp6, c4940Jo6.R, c4940Jo6.S, c4940Jo6.k0, new ST8((String) this.b, (String) this.c), c4940Jo6.l0, (C4423Io6) this.T).f(true);
                return;
            case 22:
                ((YMc) this.R).h((C16032c62) this.S, (String) this.b, (C44135ync) this.c, (C6226Mb2) this.T);
                return;
            case 23:
                C1877Dq5.I((C1877Dq5) this.c, (String) this.R, (C27168l5b) this.S, (C24231iib) this.T);
                return;
            case 25:
                int iC = ((AbstractC34575r4b) this.R).c(((C34436qxc) this.S).a);
                C34436qxc c34436qxc = (C34436qxc) this.S;
                int i3 = c34436qxc.b - iC;
                if (i3 > 0) {
                    InterfaceC19898fD6 interfaceC19898fD7 = c34436qxc.c;
                    M38 m38 = interfaceC19898fD7 == null ? null : (M38) interfaceC19898fD7.invoke();
                    if (m38 instanceof AbstractC38289u4b) {
                        AbstractC34575r4b abstractC34575r4b = (AbstractC34575r4b) this.R;
                        AbstractC38289u4b abstractC38289u4b = (AbstractC38289u4b) m38;
                        C27148l4b c27148l4b = new C27148l4b(abstractC38289u4b);
                        if (!abstractC38289u4b.c) {
                            zE = false;
                        } else {
                            zE = abstractC34575r4b.e(abstractC38289u4b.R, c27148l4b);
                        }
                    } else if (m38 instanceof E86) {
                        AbstractC34575r4b abstractC34575r4b2 = (AbstractC34575r4b) this.R;
                        E86 e86 = (E86) m38;
                        C22277h8b c22277h8b = new C22277h8b(e86);
                        if (!e86.c) {
                            zE = false;
                        } else {
                            zE = abstractC34575r4b2.e(e86.R, c22277h8b);
                        }
                    } else {
                        zE = false;
                    }
                    InterfaceC19898fD6 interfaceC19898fD8 = ((C34436qxc) this.S).d;
                    L38 l38 = interfaceC19898fD8 != null ? (L38) interfaceC19898fD8.invoke() : null;
                    if (zE || FHi.c((AbstractC34575r4b) this.R, l38)) {
                        i2 = 1;
                    }
                }
                C34436qxc c34436qxc2 = (C34436qxc) this.S;
                int i4 = c34436qxc2.e + 1;
                c34436qxc2.e = i4;
                if (i2 == 0 || i3 <= 1 || i4 >= c34436qxc2.b) {
                    return;
                }
                ((C44555z8b) this.b).b((C34436qxc) this.c, (AbstractC34575r4b) this.R, (C3776Hhg) this.T);
                return;
            case 26:
                C25002jL1 c25002jL1 = (C25002jL1) this.R;
                CaptionEditTextView captionEditTextView = (CaptionEditTextView) this.S;
                XK1 xk1 = (XK1) this.b;
                Objects.requireNonNull(c25002jL1);
                if (xk1.e()) {
                    xk1.t = null;
                    c25002jL1.p3(captionEditTextView, xk1);
                } else {
                    C26240kL1 c26240kL1 = (C26240kL1) c25002jL1.R;
                    if (c26240kL1 != null) {
                        Float f = xk1.t;
                        if (f != null && xk1.l == xk1.k) {
                            fFloatValue = f.floatValue();
                        } else {
                            EnumC36163sM1 enumC36163sM1 = xk1.k;
                            int i5 = c26240kL1.a.getResources().getDisplayMetrics().widthPixels;
                            int width = c26240kL1.d.g ? (c26240kL1.a.getWidth() - i5) / 2 : 0;
                            int iOrdinal = enumC36163sM1.ordinal();
                            if (iOrdinal == 1) {
                                i5 /= 2;
                            } else if (iOrdinal != 2) {
                                i5 = 0;
                            }
                            fFloatValue = i5 + width;
                        }
                        xk1.t = Float.valueOf(fFloatValue);
                        c25002jL1.p3(captionEditTextView, xk1);
                        FrameLayout frameLayout = c25002jL1.r0;
                        if (frameLayout != null && (c55D = c25002jL1.d0.d(frameLayout, captionEditTextView, c26240kL1.d)) != null) {
                            AbstractC0441Aw0.S2(c25002jL1, c55D, c25002jL1, null, null, 6, null);
                        }
                    }
                }
                ((CaptionEditTextView) this.S).setVisibility(0);
                ((C25002jL1) this.R).u0++;
                ((C24796jAf) this.c).e();
                C28756mN1 c28756mN1E3 = ((C25002jL1) this.R).e3();
                long jB = ((C24796jAf) this.c).b(TimeUnit.MILLISECONDS);
                InterfaceC17244d47 interfaceC17244d47 = c28756mN1E3.a;
                KJe kJe = KJe.CAPTION_APPEAR_ON_PREVIEW;
                C47 c47 = (C47) interfaceC17244d47;
                Objects.requireNonNull(c47);
                c47.c(XKg.K1(kJe), jB);
                if (((PK1) this.T) == PK1.TIMED_CAPTION) {
                    ((C25002jL1) this.R).l3((XK1) this.b, (CaptionEditTextView) this.S);
                    return;
                }
                return;
            case 27:
                A9c a9c = (A9c) this.R;
                a9c.b.invoke(a9c.h);
                ((InterfaceC13557a62) ((A9c) this.R).c.get()).b((C16032c62) this.S, (String) this.b, (Z0f) this.c);
                ((InterfaceC19898fD6) this.T).invoke();
                return;
            default:
                ((C39737vF3) this.R).n();
                C41234wS4 c41234wS4 = (C41234wS4) ((C39737vF3) this.R).e;
                String str3 = (String) this.S;
                C26075kCg c26075kCg = (C26075kCg) this.b;
                EnumC32941pkf enumC32941pkf = (EnumC32941pkf) this.c;
                EnumC26432kUd enumC26432kUd = (EnumC26432kUd) this.T;
                FR2 fr2 = (FR2) c41234wS4.b.get();
                String str4 = c26075kCg.a;
                String strX = AbstractC37338tIi.X(c26075kCg.b.c);
                AbstractC18956eS2 abstractC18956eS2A = fr2.a(str3, str4, enumC32941pkf, enumC26432kUd, strX != null ? TM5.f(strX, EnumC40487vqg.TOPIC_PAGE) : null);
                C39737vF3 c39737vF3 = (C39737vF3) this.R;
                GUi.U0(abstractC18956eS2A, new KCg(c39737vF3, i), new LCg(c39737vF3, (C26075kCg) this.b, i));
                return;
        }
    }
}
