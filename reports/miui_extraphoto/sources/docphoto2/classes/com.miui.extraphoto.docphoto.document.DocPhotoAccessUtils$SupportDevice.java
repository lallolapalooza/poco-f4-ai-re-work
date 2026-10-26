package com.miui.extraphoto.docphoto.document;

import android.os.Build;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miuix.animation.internal.AnimTask;

/* JADX INFO: compiled from: DocPhotoAccessUtils.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DocPhotoAccessUtils {
    private static SupportDevice item;
    private static boolean useCache;
    public static final DocPhotoAccessUtils INSTANCE = new DocPhotoAccessUtils();
    private static final String[] sUseCacheList = {"yunluo", "spes", "spesn", "xun"};

    private DocPhotoAccessUtils() {
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'qssi' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:315)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:288)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: compiled from: DocPhotoAccessUtils.kt */
    public static final class SupportDevice {
        public static final SupportDevice agate;
        public static final SupportDevice alioth;
        public static final SupportDevice aliothin;
        public static final SupportDevice amber;
        public static final SupportDevice apollo;
        public static final SupportDevice ares;
        public static final SupportDevice aresin;
        public static final SupportDevice argo;
        public static final SupportDevice atom;
        public static final SupportDevice bhima;
        public static final SupportDevice biloba;
        public static final SupportDevice bomb;
        public static final SupportDevice camellia;
        public static final SupportDevice camellian;
        public static final SupportDevice cannon;
        public static final SupportDevice cannong;
        public static final SupportDevice cas;
        public static final SupportDevice cepheus;
        public static final SupportDevice cetus;
        public static final SupportDevice cezanne;
        public static final SupportDevice chopin;
        public static final SupportDevice citrus;
        public static final SupportDevice cmi;
        public static final SupportDevice courbet;
        public static final SupportDevice courbetin;
        public static final SupportDevice crux;
        public static final SupportDevice cupid;
        public static final SupportDevice curtana;
        public static final SupportDevice curtana_in1;
        public static final SupportDevice curtanacn;
        public static final SupportDevice dagu;
        public static final SupportDevice daumier;
        public static final SupportDevice davinci;
        public static final SupportDevice davinciin;
        public static final SupportDevice diting;
        public static final SupportDevice ditingp;
        public static final SupportDevice durandal;
        public static final SupportDevice elish;
        public static final SupportDevice enuma;
        public static final SupportDevice evergo;
        public static final SupportDevice evergreen;
        public static final SupportDevice excalibur;
        public static final SupportDevice fleur;
        public static final SupportDevice frost;
        public static final SupportDevice gauguin;
        public static final SupportDevice gauguininpro;
        public static final SupportDevice gauguinpro;
        public static final SupportDevice gram;
        public static final SupportDevice grus;
        public static final SupportDevice haydn;
        public static final SupportDevice haydnin;
        public static final SupportDevice ingres;
        public static final SupportDevice joyeuse;
        public static final SupportDevice karna;
        public static final SupportDevice lemon;
        public static final SupportDevice light;
        public static final SupportDevice lime;
        public static final SupportDevice lisa;
        public static final SupportDevice lmi;
        public static final SupportDevice lmiin;
        public static final SupportDevice lmiinpro;
        public static final SupportDevice lmipro;
        public static final SupportDevice loki;
        public static final SupportDevice maltose;
        public static final SupportDevice marble;
        public static final SupportDevice marblein;
        public static final SupportDevice mars;
        public static final SupportDevice matisse;
        public static final SupportDevice mayfly;
        public static final SupportDevice merlin;
        public static final SupportDevice merlinin;
        public static final SupportDevice miel;
        public static final SupportDevice mojito;
        public static final SupportDevice mona;
        public static final SupportDevice monet;
        public static final SupportDevice monetin;
        public static final SupportDevice moonstone;
        public static final SupportDevice munch;
        public static final SupportDevice nabu;
        public static final SupportDevice ocean;
        public static final SupportDevice odin;
        public static final SupportDevice opal;
        public static final SupportDevice peux;
        public static final SupportDevice phoenix;
        public static final SupportDevice phoenixin;
        public static final SupportDevice picasso;
        public static final SupportDevice picassoin;
        public static final SupportDevice pissarro;
        public static final SupportDevice pissarroin;
        public static final SupportDevice pissarroinpro;
        public static final SupportDevice pissarropro;
        public static final SupportDevice plato;
        public static final SupportDevice pomelo;
        public static final SupportDevice poussin;
        public static final SupportDevice psyche;
        public static final SupportDevice qssi;
        public static final SupportDevice rainbow;
        public static final SupportDevice raphael;
        public static final SupportDevice raphaelin;
        public static final SupportDevice redwood;
        public static final SupportDevice redwoodin;
        public static final SupportDevice renoir;
        public static final SupportDevice rock;
        public static final SupportDevice rosemary;
        public static final SupportDevice rosemaryp;
        public static final SupportDevice rubens;
        public static final SupportDevice ruby;
        public static final SupportDevice rubyplus;
        public static final SupportDevice rubypro;
        public static final SupportDevice sea;
        public static final SupportDevice secret;
        public static final SupportDevice spes;
        public static final SupportDevice spesn;
        public static final SupportDevice star;
        public static final SupportDevice stone;
        public static final SupportDevice sunny;
        public static final SupportDevice sunstone;
        public static final SupportDevice surya;
        public static final SupportDevice sweet;
        public static final SupportDevice sweetin;
        public static final SupportDevice taoyao;
        public static final SupportDevice tapas;
        public static final SupportDevice thor;
        public static final SupportDevice thunder;
        public static final SupportDevice thyme;
        public static final SupportDevice toco;
        public static final SupportDevice tocoin;
        public static final SupportDevice topaz;
        public static final SupportDevice tucana;
        public static final SupportDevice umi;
        public static final SupportDevice unicorn;
        public static final SupportDevice vangogh;
        public static final SupportDevice vayu;
        public static final SupportDevice venus;
        public static final SupportDevice veux;
        public static final SupportDevice vida;
        public static final SupportDevice vili;
        public static final SupportDevice viva;
        public static final SupportDevice xaga;
        public static final SupportDevice xagain;
        public static final SupportDevice xagapro;
        public static final SupportDevice xagaproin;
        public static final SupportDevice xun;
        public static final SupportDevice yunluo;
        public static final SupportDevice zeus;
        public static final SupportDevice zijin;
        public static final SupportDevice ziyi;
        public static final SupportDevice zizhan;
        private final boolean handwrite;
        private final boolean moer;
        public static final SupportDevice nuwa = new SupportDevice("nuwa", 134, true, true);
        public static final SupportDevice fuxi = new SupportDevice("fuxi", 135, true, true);
        public static final SupportDevice rembrandt = new SupportDevice("rembrandt", 143, true, true);
        public static final SupportDevice socrates = new SupportDevice("socrates", 144, true, true);
        public static final SupportDevice mondrian = new SupportDevice("mondrian", 145, true, true);
        public static final SupportDevice ishtar = new SupportDevice("ishtar", AnimTask.MAX_PAGE_SIZE, true, true);
        public static final SupportDevice liuqin = new SupportDevice("liuqin", 151, true, true);
        public static final SupportDevice pipa = new SupportDevice("pipa", 152, true, true);
        public static final SupportDevice yuechu = new SupportDevice("yuechu", 155, true, true);
        public static final SupportDevice babylon = new SupportDevice("babylon", 156, true, true);
        public static final SupportDevice pearl = new SupportDevice("pearl", 157, true, true);
        public static final SupportDevice aristotle = new SupportDevice("aristotle", 159, true, true);
        public static final SupportDevice XIG04 = new SupportDevice("XIG04", 160, true, true);
        public static final SupportDevice corot = new SupportDevice("corot", 161, true, true);
        public static final SupportDevice yudi = new SupportDevice("yudi", 162, true, true);
        public static final SupportDevice zircon = new SupportDevice("zircon", 163, true, true);
        public static final SupportDevice garnet = new SupportDevice("garnet", 164, true, true);
        public static final SupportDevice XIG05 = new SupportDevice("XIG05", 165, true, true);
        public static final SupportDevice houji = new SupportDevice("houji", 166, true, true);
        public static final SupportDevice shennong = new SupportDevice("shennong", 167, true, true);
        public static final SupportDevice duchamp = new SupportDevice("duchamp", 168, true, true);
        private static final /* synthetic */ SupportDevice[] $VALUES = $values();

        private static final /* synthetic */ SupportDevice[] $values() {
            return new SupportDevice[]{qssi, cmi, umi, lmi, lmiin, lmipro, lmiinpro, curtana, curtana_in1, curtanacn, durandal, excalibur, joyeuse, gram, monet, monetin, vangogh, merlin, merlinin, cepheus, crux, tucana, atom, bomb, picasso, picassoin, phoenix, phoenixin, davinci, davinciin, grus, raphael, raphaelin, cas, toco, tocoin, apollo, cezanne, gauguin, gauguinpro, gauguininpro, surya, karna, cannon, cannong, lime, citrus, odin, venus, cetus, star, mars, courbet, courbetin, sweet, sweetin, haydn, alioth, rainbow, mojito, sunny, rosemary, maltose, rosemaryp, secret, renoir, thyme, ares, aresin, lemon, pomelo, haydnin, aliothin, chopin, camellia, camellian, vayu, bhima, biloba, enuma, elish, nabu, argo, agate, amber, vili, lisa, evergo, evergreen, pissarro, pissarropro, pissarroin, pissarroinpro, mona, zeus, psyche, ingres, poussin, cupid, fleur, miel, veux, peux, viva, vida, spes, spesn, munch, rubens, matisse, thor, loki, light, thunder, zizhan, taoyao, zijin, opal, frost, xaga, xagapro, xagain, xagaproin, mayfly, unicorn, daumier, plato, diting, ditingp, dagu, rock, stone, yunluo, ziyi, nuwa, fuxi, ruby, rubypro, rubyplus, moonstone, sunstone, redwood, redwoodin, rembrandt, socrates, mondrian, topaz, tapas, marble, marblein, ishtar, liuqin, pipa, sea, ocean, yuechu, babylon, pearl, xun, aristotle, XIG04, corot, yudi, zircon, garnet, XIG05, houji, shennong, duchamp};
        }

        public static SupportDevice[] values() {
            return (SupportDevice[]) $VALUES.clone();
        }

        private SupportDevice(String str, int i, boolean z, boolean z2) {
            super(str, i);
            this.moer = z;
            this.handwrite = z2;
        }

        /* synthetic */ SupportDevice(String str, int i, boolean z, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, i, (i2 & 1) != 0 ? true : z, (i2 & 2) != 0 ? false : z2);
        }

        public final boolean getHandwrite() {
            return this.handwrite;
        }

        public final boolean getMoer() {
            return this.moer;
        }

        static {
            boolean z = false;
            qssi = new SupportDevice("qssi", 0, false, z, 2, null);
            boolean z2 = false;
            boolean z3 = false;
            int i = 3;
            DefaultConstructorMarker defaultConstructorMarker = null;
            cmi = new SupportDevice("cmi", 1, z2, z3, i, defaultConstructorMarker);
            boolean z4 = false;
            int i2 = 3;
            DefaultConstructorMarker defaultConstructorMarker2 = null;
            umi = new SupportDevice("umi", 2, z, z4, i2, defaultConstructorMarker2);
            lmi = new SupportDevice("lmi", 3, z2, z3, i, defaultConstructorMarker);
            lmiin = new SupportDevice("lmiin", 4, z, z4, i2, defaultConstructorMarker2);
            lmipro = new SupportDevice("lmipro", 5, z2, z3, i, defaultConstructorMarker);
            lmiinpro = new SupportDevice("lmiinpro", 6, z, z4, i2, defaultConstructorMarker2);
            int i3 = 2;
            curtana = new SupportDevice("curtana", 7, z2, z3, i3, defaultConstructorMarker);
            int i4 = 2;
            curtana_in1 = new SupportDevice("curtana_in1", 8, z, z4, i4, defaultConstructorMarker2);
            curtanacn = new SupportDevice("curtanacn", 9, z2, z3, i3, defaultConstructorMarker);
            durandal = new SupportDevice("durandal", 10, z, z4, i4, defaultConstructorMarker2);
            excalibur = new SupportDevice("excalibur", 11, z2, z3, i3, defaultConstructorMarker);
            joyeuse = new SupportDevice("joyeuse", 12, z, z4, i4, defaultConstructorMarker2);
            gram = new SupportDevice("gram", 13, z2, z3, i3, defaultConstructorMarker);
            monet = new SupportDevice("monet", 14, z, z4, i4, defaultConstructorMarker2);
            monetin = new SupportDevice("monetin", 15, z2, z3, i3, defaultConstructorMarker);
            vangogh = new SupportDevice("vangogh", 16, z, z4, i4, defaultConstructorMarker2);
            merlin = new SupportDevice("merlin", 17, z2, z3, i3, defaultConstructorMarker);
            merlinin = new SupportDevice("merlinin", 18, z, z4, i4, defaultConstructorMarker2);
            cepheus = new SupportDevice("cepheus", 19, z2, z3, i3, defaultConstructorMarker);
            crux = new SupportDevice("crux", 20, z, z4, i4, defaultConstructorMarker2);
            tucana = new SupportDevice("tucana", 21, z2, z3, i3, defaultConstructorMarker);
            atom = new SupportDevice("atom", 22, z, z4, i4, defaultConstructorMarker2);
            bomb = new SupportDevice("bomb", 23, z2, z3, i3, defaultConstructorMarker);
            picasso = new SupportDevice("picasso", 24, z, z4, i4, defaultConstructorMarker2);
            picassoin = new SupportDevice("picassoin", 25, z2, z3, i3, defaultConstructorMarker);
            phoenix = new SupportDevice("phoenix", 26, z, z4, i4, defaultConstructorMarker2);
            phoenixin = new SupportDevice("phoenixin", 27, z2, z3, i3, defaultConstructorMarker);
            davinci = new SupportDevice("davinci", 28, z, z4, i4, defaultConstructorMarker2);
            davinciin = new SupportDevice("davinciin", 29, z2, z3, i3, defaultConstructorMarker);
            grus = new SupportDevice("grus", 30, z, z4, i4, defaultConstructorMarker2);
            raphael = new SupportDevice("raphael", 31, z2, z3, i3, defaultConstructorMarker);
            raphaelin = new SupportDevice("raphaelin", 32, z, z4, i4, defaultConstructorMarker2);
            cas = new SupportDevice("cas", 33, z2, z3, 3, defaultConstructorMarker);
            toco = new SupportDevice("toco", 34, z, z4, i4, defaultConstructorMarker2);
            int i5 = 2;
            tocoin = new SupportDevice("tocoin", 35, z2, z3, i5, defaultConstructorMarker);
            apollo = new SupportDevice("apollo", 36, true, z4, i4, defaultConstructorMarker2);
            cezanne = new SupportDevice("cezanne", 37, z2, z3, i5, defaultConstructorMarker);
            boolean z5 = false;
            gauguin = new SupportDevice("gauguin", 38, z5, z4, i4, defaultConstructorMarker2);
            gauguinpro = new SupportDevice("gauguinpro", 39, z2, z3, i5, defaultConstructorMarker);
            gauguininpro = new SupportDevice("gauguininpro", 40, z5, z4, i4, defaultConstructorMarker2);
            surya = new SupportDevice("surya", 41, z2, z3, i5, defaultConstructorMarker);
            karna = new SupportDevice("karna", 42, z5, z4, i4, defaultConstructorMarker2);
            cannon = new SupportDevice("cannon", 43, z2, z3, i5, defaultConstructorMarker);
            cannong = new SupportDevice("cannong", 44, z5, z4, i4, defaultConstructorMarker2);
            lime = new SupportDevice("lime", 45, z2, z3, i5, defaultConstructorMarker);
            citrus = new SupportDevice("citrus", 46, z5, z4, i4, defaultConstructorMarker2);
            odin = new SupportDevice("odin", 47, z2, z3, 3, defaultConstructorMarker);
            int i6 = 3;
            venus = new SupportDevice("venus", 48, z5, z4, i6, defaultConstructorMarker2);
            cetus = new SupportDevice("cetus", 49, z2, z3, 2, defaultConstructorMarker);
            star = new SupportDevice("star", 50, z5, z4, i6, defaultConstructorMarker2);
            mars = new SupportDevice("mars", 51, z2, z3, 3, defaultConstructorMarker);
            int i7 = 2;
            courbet = new SupportDevice("courbet", 52, z5, z4, i7, defaultConstructorMarker2);
            int i8 = 2;
            courbetin = new SupportDevice("courbetin", 53, z2, z3, i8, defaultConstructorMarker);
            sweet = new SupportDevice("sweet", 54, z5, z4, i7, defaultConstructorMarker2);
            sweetin = new SupportDevice("sweetin", 55, z2, z3, i8, defaultConstructorMarker);
            haydn = new SupportDevice("haydn", 56, z5, z4, 3, defaultConstructorMarker2);
            alioth = new SupportDevice("alioth", 57, z2, z3, 3, defaultConstructorMarker);
            int i9 = 2;
            rainbow = new SupportDevice("rainbow", 58, z5, z4, i9, defaultConstructorMarker2);
            int i10 = 2;
            mojito = new SupportDevice("mojito", 59, z2, z3, i10, defaultConstructorMarker);
            sunny = new SupportDevice("sunny", 60, z5, z4, i9, defaultConstructorMarker2);
            rosemary = new SupportDevice("rosemary", 61, z2, z3, i10, defaultConstructorMarker);
            maltose = new SupportDevice("maltose", 62, z5, z4, i9, defaultConstructorMarker2);
            rosemaryp = new SupportDevice("rosemaryp", 63, z2, z3, i10, defaultConstructorMarker);
            secret = new SupportDevice("secret", 64, z5, z4, i9, defaultConstructorMarker2);
            renoir = new SupportDevice("renoir", 65, z2, z3, 3, defaultConstructorMarker);
            thyme = new SupportDevice("thyme", 66, true, z4, i9, defaultConstructorMarker2);
            int i11 = 2;
            ares = new SupportDevice("ares", 67, z2, z3, i11, defaultConstructorMarker);
            boolean z6 = false;
            aresin = new SupportDevice("aresin", 68, z6, z4, i9, defaultConstructorMarker2);
            lemon = new SupportDevice("lemon", 69, z2, z3, i11, defaultConstructorMarker);
            pomelo = new SupportDevice("pomelo", 70, z6, z4, i9, defaultConstructorMarker2);
            haydnin = new SupportDevice("haydnin", 71, z2, z3, 3, defaultConstructorMarker);
            aliothin = new SupportDevice("aliothin", 72, z6, z4, 3, defaultConstructorMarker2);
            int i12 = 2;
            chopin = new SupportDevice("chopin", 73, z2, z3, i12, defaultConstructorMarker);
            int i13 = 2;
            camellia = new SupportDevice("camellia", 74, z6, z4, i13, defaultConstructorMarker2);
            camellian = new SupportDevice("camellian", 75, z2, z3, i12, defaultConstructorMarker);
            vayu = new SupportDevice("vayu", 76, z6, z4, i13, defaultConstructorMarker2);
            bhima = new SupportDevice("bhima", 77, z2, z3, i12, defaultConstructorMarker);
            biloba = new SupportDevice("biloba", 78, z6, z4, i13, defaultConstructorMarker2);
            int i14 = 3;
            enuma = new SupportDevice("enuma", 79, z2, z3, i14, defaultConstructorMarker);
            int i15 = 3;
            elish = new SupportDevice("elish", 80, z6, z4, i15, defaultConstructorMarker2);
            nabu = new SupportDevice("nabu", 81, z2, z3, i14, defaultConstructorMarker);
            argo = new SupportDevice("argo", 82, z6, z4, i15, defaultConstructorMarker2);
            agate = new SupportDevice("agate", 83, z2, z3, 2, defaultConstructorMarker);
            amber = new SupportDevice("amber", 84, z6, z4, 2, defaultConstructorMarker2);
            vili = new SupportDevice("vili", 85, z2, z3, 3, defaultConstructorMarker);
            lisa = new SupportDevice("lisa", 86, z6, z4, 3, defaultConstructorMarker2);
            int i16 = 2;
            evergo = new SupportDevice("evergo", 87, z2, z3, i16, defaultConstructorMarker);
            int i17 = 2;
            evergreen = new SupportDevice("evergreen", 88, z6, z4, i17, defaultConstructorMarker2);
            pissarro = new SupportDevice("pissarro", 89, z2, z3, i16, defaultConstructorMarker);
            pissarropro = new SupportDevice("pissarropro", 90, z6, z4, i17, defaultConstructorMarker2);
            pissarroin = new SupportDevice("pissarroin", 91, z2, z3, i16, defaultConstructorMarker);
            pissarroinpro = new SupportDevice("pissarroinpro", 92, z6, z4, i17, defaultConstructorMarker2);
            int i18 = 3;
            mona = new SupportDevice("mona", 93, z2, z3, i18, defaultConstructorMarker);
            int i19 = 3;
            zeus = new SupportDevice("zeus", 94, z6, z4, i19, defaultConstructorMarker2);
            psyche = new SupportDevice("psyche", 95, z2, z3, i18, defaultConstructorMarker);
            ingres = new SupportDevice("ingres", 96, z6, z4, i19, defaultConstructorMarker2);
            poussin = new SupportDevice("poussin", 97, z2, z3, i18, defaultConstructorMarker);
            cupid = new SupportDevice("cupid", 98, z6, z4, i19, defaultConstructorMarker2);
            int i20 = 2;
            fleur = new SupportDevice("fleur", 99, z2, z3, i20, defaultConstructorMarker);
            int i21 = 2;
            miel = new SupportDevice("miel", 100, z6, z4, i21, defaultConstructorMarker2);
            veux = new SupportDevice("veux", 101, z2, z3, i20, defaultConstructorMarker);
            peux = new SupportDevice("peux", 102, z6, z4, i21, defaultConstructorMarker2);
            viva = new SupportDevice("viva", 103, z2, z3, i20, defaultConstructorMarker);
            vida = new SupportDevice("vida", 104, z6, z4, i21, defaultConstructorMarker2);
            spes = new SupportDevice("spes", 105, z2, z3, i20, defaultConstructorMarker);
            spesn = new SupportDevice("spesn", 106, z6, z4, i21, defaultConstructorMarker2);
            int i22 = 3;
            munch = new SupportDevice("munch", 107, z2, z3, i22, defaultConstructorMarker);
            int i23 = 3;
            rubens = new SupportDevice("rubens", 108, z6, z4, i23, defaultConstructorMarker2);
            matisse = new SupportDevice("matisse", 109, z2, z3, i22, defaultConstructorMarker);
            thor = new SupportDevice("thor", 110, z6, z4, i23, defaultConstructorMarker2);
            loki = new SupportDevice("loki", 111, z2, z3, i22, defaultConstructorMarker);
            light = new SupportDevice("light", 112, z6, z4, 2, defaultConstructorMarker2);
            thunder = new SupportDevice("thunder", 113, z2, z3, 2, defaultConstructorMarker);
            int i24 = 3;
            zizhan = new SupportDevice("zizhan", 114, z6, z4, i24, defaultConstructorMarker2);
            taoyao = new SupportDevice("taoyao", 115, z2, z3, 3, defaultConstructorMarker);
            zijin = new SupportDevice("zijin", 116, z6, z4, i24, defaultConstructorMarker2);
            opal = new SupportDevice("opal", 117, z2, z3, 2, defaultConstructorMarker);
            frost = new SupportDevice("frost", 118, z6, z4, 2, defaultConstructorMarker2);
            int i25 = 3;
            xaga = new SupportDevice("xaga", 119, z2, z3, i25, defaultConstructorMarker);
            int i26 = 3;
            xagapro = new SupportDevice("xagapro", 120, z6, z4, i26, defaultConstructorMarker2);
            xagain = new SupportDevice("xagain", 121, z2, z3, i25, defaultConstructorMarker);
            xagaproin = new SupportDevice("xagaproin", 122, z6, z4, i26, defaultConstructorMarker2);
            mayfly = new SupportDevice("mayfly", 123, z2, z3, i25, defaultConstructorMarker);
            unicorn = new SupportDevice("unicorn", 124, z6, z4, i26, defaultConstructorMarker2);
            daumier = new SupportDevice("daumier", 125, z2, z3, i25, defaultConstructorMarker);
            plato = new SupportDevice("plato", 126, z6, z4, i26, defaultConstructorMarker2);
            diting = new SupportDevice("diting", 127, z2, z3, i25, defaultConstructorMarker);
            ditingp = new SupportDevice("ditingp", 128, z6, z4, i26, defaultConstructorMarker2);
            dagu = new SupportDevice("dagu", 129, z2, z3, i25, defaultConstructorMarker);
            int i27 = 2;
            rock = new SupportDevice("rock", 130, z6, z4, i27, defaultConstructorMarker2);
            stone = new SupportDevice("stone", 131, z2, z3, 2, defaultConstructorMarker);
            yunluo = new SupportDevice("yunluo", 132, z6, z4, i27, defaultConstructorMarker2);
            ziyi = new SupportDevice("ziyi", 133, z2, z3, 3, defaultConstructorMarker);
            boolean z7 = false;
            boolean z8 = false;
            int i28 = 2;
            DefaultConstructorMarker defaultConstructorMarker3 = null;
            ruby = new SupportDevice("ruby", 136, z7, z8, i28, defaultConstructorMarker3);
            boolean z9 = false;
            boolean z10 = false;
            int i29 = 2;
            DefaultConstructorMarker defaultConstructorMarker4 = null;
            rubypro = new SupportDevice("rubypro", 137, z9, z10, i29, defaultConstructorMarker4);
            rubyplus = new SupportDevice("rubyplus", 138, z7, z8, i28, defaultConstructorMarker3);
            moonstone = new SupportDevice("moonstone", 139, z9, z10, i29, defaultConstructorMarker4);
            sunstone = new SupportDevice("sunstone", 140, z7, z8, i28, defaultConstructorMarker3);
            redwood = new SupportDevice("redwood", 141, z9, z10, 3, defaultConstructorMarker4);
            redwoodin = new SupportDevice("redwoodin", 142, z7, z8, 3, defaultConstructorMarker3);
            topaz = new SupportDevice("topaz", 146, z7, z8, 2, defaultConstructorMarker3);
            tapas = new SupportDevice("tapas", 147, z9, z10, 2, defaultConstructorMarker4);
            marble = new SupportDevice("marble", 148, z7, z8, 3, defaultConstructorMarker3);
            marblein = new SupportDevice("marblein", 149, z9, z10, 3, defaultConstructorMarker4);
            int i30 = 2;
            sea = new SupportDevice("sea", 153, z7, z8, i30, defaultConstructorMarker3);
            ocean = new SupportDevice("ocean", 154, z9, z10, 2, defaultConstructorMarker4);
            xun = new SupportDevice("xun", 158, z7, z8, i30, defaultConstructorMarker3);
        }
    }

    static {
        SupportDevice[] supportDeviceArrValues = SupportDevice.values();
        int length = supportDeviceArrValues.length;
        int i = 0;
        int i2 = 0;
        while (i2 < length) {
            SupportDevice supportDevice = supportDeviceArrValues[i2];
            i2++;
            if (Build.DEVICE.equals(supportDevice.name())) {
                item = supportDevice;
                break;
            }
        }
        String[] strArr = sUseCacheList;
        int length2 = strArr.length;
        while (i < length2) {
            String str = strArr[i];
            i++;
            if (Build.DEVICE.equals(str)) {
                useCache = true;
                return;
            }
        }
    }

    public static final boolean isSupport() {
        return item != null;
    }

    public static final boolean isSupportMoer() {
        SupportDevice supportDevice = item;
        if (supportDevice == null) {
            return false;
        }
        return supportDevice.getMoer();
    }

    public static final boolean isSupportHandwrite() {
        SupportDevice supportDevice = item;
        if (supportDevice == null) {
            return false;
        }
        return supportDevice.getHandwrite();
    }

    public static final boolean isNeedUseCache() {
        return useCache;
    }
}
