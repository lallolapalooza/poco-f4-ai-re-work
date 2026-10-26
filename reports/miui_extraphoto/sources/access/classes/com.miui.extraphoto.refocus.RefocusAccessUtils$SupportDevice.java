package com.miui.extraphoto.refocus;

import android.os.Build;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miuix.animation.internal.AnimTask;

/* JADX INFO: compiled from: RefocusAccessUtils.kt */
/* JADX INFO: loaded from: classes.dex */
public final class RefocusAccessUtils {
    public static final RefocusAccessUtils INSTANCE = new RefocusAccessUtils();
    private static SupportDevice item;

    private RefocusAccessUtils() {
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'dipper' uses external variables
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
    /* JADX INFO: compiled from: RefocusAccessUtils.kt */
    public static final class SupportDevice {
        private static final /* synthetic */ SupportDevice[] $VALUES = $values();
        public static final SupportDevice XIG02;
        public static final SupportDevice XIG03;
        public static final SupportDevice XIG04;
        public static final SupportDevice XIG05;
        public static final SupportDevice a101xm;
        public static final SupportDevice agate;
        public static final SupportDevice alioth;
        public static final SupportDevice aliothin;
        public static final SupportDevice amber;
        public static final SupportDevice andromeda;
        public static final SupportDevice apollo;
        public static final SupportDevice aquila;
        public static final SupportDevice ares;
        public static final SupportDevice aresin;
        public static final SupportDevice argo;
        public static final SupportDevice aristotle;
        public static final SupportDevice atom;
        public static final SupportDevice babylon;
        public static final SupportDevice begonia;
        public static final SupportDevice begoniain;
        public static final SupportDevice beryllium;
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
        public static final SupportDevice corot;
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
        public static final SupportDevice dipper;
        public static final SupportDevice diting;
        public static final SupportDevice ditingp;
        public static final SupportDevice draco;
        public static final SupportDevice duchamp;
        public static final SupportDevice durandal;
        public static final SupportDevice elish;
        public static final SupportDevice enuma;
        public static final SupportDevice eos;
        public static final SupportDevice equuleus;
        public static final SupportDevice evergo;
        public static final SupportDevice evergreen;
        public static final SupportDevice excalibur;
        public static final SupportDevice fire;
        public static final SupportDevice fleur;
        public static final SupportDevice frost;
        public static final SupportDevice fuxi;
        public static final SupportDevice garnet;
        public static final SupportDevice gauguin;
        public static final SupportDevice gauguininpro;
        public static final SupportDevice gauguinpro;
        public static final SupportDevice gram;
        public static final SupportDevice grus;
        public static final SupportDevice haydn;
        public static final SupportDevice haydnin;
        public static final SupportDevice heat;
        public static final SupportDevice hercules;
        public static final SupportDevice houji;
        public static final SupportDevice ingres;
        public static final SupportDevice iris;
        public static final SupportDevice ishtar;
        public static final SupportDevice joyeuse;
        public static final SupportDevice karna;
        public static final SupportDevice lemon;
        public static final SupportDevice light;
        public static final SupportDevice lilac;
        public static final SupportDevice lime;
        public static final SupportDevice lisa;
        public static final SupportDevice liuqin;
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
        public static final SupportDevice miel;
        public static final SupportDevice mojito;
        public static final SupportDevice mona;
        public static final SupportDevice mondrian;
        public static final SupportDevice monet;
        public static final SupportDevice monetin;
        public static final SupportDevice moonstone;
        public static final SupportDevice munch;
        public static final SupportDevice nuwa;
        public static final SupportDevice ocean;
        public static final SupportDevice odin;
        public static final SupportDevice onc;
        public static final SupportDevice opal;
        public static final SupportDevice pearl;
        public static final SupportDevice perseus;
        public static final SupportDevice peux;
        public static final SupportDevice phoenix;
        public static final SupportDevice phoenixin;
        public static final SupportDevice picasso;
        public static final SupportDevice picassoin;
        public static final SupportDevice pissarro;
        public static final SupportDevice pissarroin;
        public static final SupportDevice pissarroinpro;
        public static final SupportDevice pissarropro;
        public static final SupportDevice platina;
        public static final SupportDevice plato;
        public static final SupportDevice polaris;
        public static final SupportDevice pomelo;
        public static final SupportDevice poussin;
        public static final SupportDevice psyche;
        public static final SupportDevice pyxis;
        public static final SupportDevice qssi;
        public static final SupportDevice rainbow;
        public static final SupportDevice raphael;
        public static final SupportDevice raphaelin;
        public static final SupportDevice redwood;
        public static final SupportDevice redwoodin;
        public static final SupportDevice rembrandt;
        public static final SupportDevice renoir;
        public static final SupportDevice river;
        public static final SupportDevice rock;
        public static final SupportDevice rosemary;
        public static final SupportDevice rosemaryp;
        public static final SupportDevice rubens;
        public static final SupportDevice ruby;
        public static final SupportDevice rubyplus;
        public static final SupportDevice rubypro;
        public static final SupportDevice sea;
        public static final SupportDevice secret;
        public static final SupportDevice selene;
        public static final SupportDevice shennong;
        public static final SupportDevice sirius;
        public static final SupportDevice sky;
        public static final SupportDevice socrates;
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
        public static final SupportDevice tulip;
        public static final SupportDevice umi;
        public static final SupportDevice unicorn;
        public static final SupportDevice ursa;
        public static final SupportDevice vangogh;
        public static final SupportDevice vayu;
        public static final SupportDevice vela;
        public static final SupportDevice venus;
        public static final SupportDevice veux;
        public static final SupportDevice vida;
        public static final SupportDevice vili;
        public static final SupportDevice violet;
        public static final SupportDevice viva;
        public static final SupportDevice xaga;
        public static final SupportDevice xagain;
        public static final SupportDevice xagapro;
        public static final SupportDevice xagaproin;
        public static final SupportDevice yudi;
        public static final SupportDevice yuechu;
        public static final SupportDevice zeus;
        public static final SupportDevice zijin;
        public static final SupportDevice zircon;
        public static final SupportDevice ziyi;
        public static final SupportDevice zizhan;
        private final boolean brainRelight;
        private final boolean forbidRelight;
        private final boolean megvii;
        private final boolean meitu;
        private final boolean mi;
        private final boolean miAlgo;
        private final boolean miDual;
        private final boolean miMtk;

        private static final /* synthetic */ SupportDevice[] $values() {
            return new SupportDevice[]{dipper, polaris, sirius, ursa, beryllium, perseus, equuleus, platina, tulip, cepheus, grus, violet, onc, aquila, andromeda, raphael, raphaelin, pyxis, davinci, davinciin, vela, hercules, crux, begonia, begoniain, tucana, phoenix, phoenixin, draco, qssi, cmi, umi, lmi, lmiin, lmipro, lmiinpro, picasso, picassoin, curtana, curtana_in1, curtanacn, durandal, excalibur, joyeuse, gram, monet, monetin, vangogh, toco, tocoin, atom, bomb, cas, apollo, cezanne, gauguin, gauguinpro, gauguininpro, surya, karna, cannon, cannong, lime, citrus, venus, cetus, star, mars, courbet, courbetin, sweet, sweetin, haydn, alioth, rainbow, mojito, sunny, rosemary, maltose, rosemaryp, secret, renoir, thyme, ares, aresin, lemon, pomelo, haydnin, aliothin, chopin, camellia, camellian, vayu, bhima, biloba, odin, enuma, elish, argo, agate, amber, vili, iris, XIG02, lisa, selene, eos, evergo, evergreen, pissarro, pissarropro, pissarroin, pissarroinpro, lilac, a101xm, mona, zeus, psyche, ingres, poussin, cupid, fleur, miel, veux, peux, viva, vida, spes, spesn, munch, rubens, matisse, thor, loki, light, thunder, zizhan, taoyao, zijin, opal, frost, xaga, xagapro, xagain, xagaproin, mayfly, unicorn, daumier, plato, diting, ditingp, dagu, rock, stone, ziyi, nuwa, fuxi, ruby, rubypro, rubyplus, moonstone, sunstone, redwood, redwoodin, rembrandt, socrates, mondrian, topaz, tapas, marble, marblein, ishtar, liuqin, sea, ocean, yuechu, babylon, fire, heat, sky, river, XIG03, pearl, aristotle, XIG04, corot, yudi, zircon, garnet, XIG05, houji, shennong, duchamp};
        }

        public static SupportDevice[] values() {
            return (SupportDevice[]) $VALUES.clone();
        }

        private SupportDevice(String str, int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
            super(str, i);
            this.miDual = z;
            this.brainRelight = z2;
            this.megvii = z3;
            this.mi = z4;
            this.miMtk = z5;
            this.meitu = z6;
            this.miAlgo = z7;
            this.forbidRelight = z8;
        }

        /* synthetic */ SupportDevice(String str, int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, i, (i2 & 1) != 0 ? false : z, (i2 & 2) != 0 ? false : z2, (i2 & 4) != 0 ? false : z3, (i2 & 8) != 0 ? false : z4, (i2 & 16) != 0 ? false : z5, (i2 & 32) != 0 ? false : z6, (i2 & 64) != 0 ? false : z7, (i2 & 128) != 0 ? false : z8);
        }

        public final boolean getMiDual() {
            return this.miDual;
        }

        public final boolean getBrainRelight() {
            return this.brainRelight;
        }

        public final boolean getMegvii() {
            return this.megvii;
        }

        public final boolean getMi() {
            return this.mi;
        }

        public final boolean getMiMtk() {
            return this.miMtk;
        }

        public final boolean getMeitu() {
            return this.meitu;
        }

        public final boolean getMiAlgo() {
            return this.miAlgo;
        }

        public final boolean getForbidRelight() {
            return this.forbidRelight;
        }

        static {
            boolean z = false;
            boolean z2 = false;
            boolean z3 = false;
            boolean z4 = false;
            boolean z5 = false;
            boolean z6 = false;
            boolean z7 = false;
            dipper = new SupportDevice("dipper", 0, false, z, z2, z3, z4, z5, z6, z7, 255, null);
            boolean z8 = false;
            boolean z9 = false;
            boolean z10 = false;
            boolean z11 = false;
            boolean z12 = false;
            boolean z13 = false;
            boolean z14 = false;
            boolean z15 = false;
            int i = 255;
            DefaultConstructorMarker defaultConstructorMarker = null;
            polaris = new SupportDevice("polaris", 1, z8, z9, z10, z11, z12, z13, z14, z15, i, defaultConstructorMarker);
            boolean z16 = false;
            int i2 = 255;
            DefaultConstructorMarker defaultConstructorMarker2 = null;
            sirius = new SupportDevice("sirius", 2, z, z2, z3, z4, z5, z6, z7, z16, i2, defaultConstructorMarker2);
            ursa = new SupportDevice("ursa", 3, z8, z9, z10, z11, z12, z13, z14, z15, i, defaultConstructorMarker);
            beryllium = new SupportDevice("beryllium", 4, z, z2, z3, z4, z5, z6, z7, z16, i2, defaultConstructorMarker2);
            perseus = new SupportDevice("perseus", 5, z8, z9, z10, z11, z12, z13, z14, z15, i, defaultConstructorMarker);
            equuleus = new SupportDevice("equuleus", 6, z, z2, z3, z4, z5, z6, z7, z16, i2, defaultConstructorMarker2);
            platina = new SupportDevice("platina", 7, z8, z9, z10, z11, z12, z13, z14, z15, i, defaultConstructorMarker);
            tulip = new SupportDevice("tulip", 8, z, z2, z3, z4, z5, z6, z7, z16, i2, defaultConstructorMarker2);
            cepheus = new SupportDevice("cepheus", 9, z8, z9, z10, true, z12, z13, z14, z15, 247, defaultConstructorMarker);
            grus = new SupportDevice("grus", 10, z, z2, z3, true, z5, z6, z7, z16, 247, defaultConstructorMarker2);
            boolean z17 = false;
            int i3 = 255;
            violet = new SupportDevice("violet", 11, z8, z9, z10, z17, z12, z13, z14, z15, i3, defaultConstructorMarker);
            boolean z18 = false;
            int i4 = 255;
            onc = new SupportDevice("onc", 12, z, z2, z3, z18, z5, z6, z7, z16, i4, defaultConstructorMarker2);
            aquila = new SupportDevice("aquila", 13, z8, z9, z10, z17, z12, z13, z14, z15, i3, defaultConstructorMarker);
            andromeda = new SupportDevice("andromeda", 14, z, z2, z3, z18, z5, z6, z7, z16, i4, defaultConstructorMarker2);
            boolean z19 = true;
            int i5 = 247;
            raphael = new SupportDevice("raphael", 15, z8, z9, z10, z19, z12, z13, z14, z15, i5, defaultConstructorMarker);
            boolean z20 = true;
            int i6 = 247;
            raphaelin = new SupportDevice("raphaelin", 16, z, z2, z3, z20, z5, z6, z7, z16, i6, defaultConstructorMarker2);
            pyxis = new SupportDevice("pyxis", 17, z8, z9, z10, z19, z12, z13, z14, z15, i5, defaultConstructorMarker);
            davinci = new SupportDevice("davinci", 18, z, z2, z3, z20, z5, z6, z7, z16, i6, defaultConstructorMarker2);
            davinciin = new SupportDevice("davinciin", 19, z8, z9, z10, z19, z12, z13, z14, z15, i5, defaultConstructorMarker);
            vela = new SupportDevice("vela", 20, z, z2, z3, z20, z5, true, z7, z16, 215, defaultConstructorMarker2);
            hercules = new SupportDevice("hercules", 21, z8, z9, z10, z19, z12, z13, z14, z15, i5, defaultConstructorMarker);
            boolean z21 = false;
            crux = new SupportDevice("crux", 22, z, z2, z3, z20, z5, z21, z7, z16, 247, defaultConstructorMarker2);
            begonia = new SupportDevice("begonia", 23, z8, z9, z10, z19, true, z13, z14, z15, 231, defaultConstructorMarker);
            begoniain = new SupportDevice("begoniain", 24, z, z2, z3, z20, true, z21, z7, z16, 231, defaultConstructorMarker2);
            boolean z22 = false;
            int i7 = 247;
            tucana = new SupportDevice("tucana", 25, z8, z9, z10, z19, z22, z13, z14, z15, i7, defaultConstructorMarker);
            boolean z23 = false;
            int i8 = 247;
            phoenix = new SupportDevice("phoenix", 26, z, z2, z3, z20, z23, z21, z7, z16, i8, defaultConstructorMarker2);
            phoenixin = new SupportDevice("phoenixin", 27, z8, z9, z10, z19, z22, z13, z14, z15, i7, defaultConstructorMarker);
            draco = new SupportDevice("draco", 28, z, z2, z3, z20, z23, z21, z7, z16, i8, defaultConstructorMarker2);
            qssi = new SupportDevice("qssi", 29, true, true, true, z19, z22, z13, z14, z15, 240, defaultConstructorMarker);
            cmi = new SupportDevice("cmi", 30, z, z2, z3, z20, z23, z21, z7, z16, i8, defaultConstructorMarker2);
            boolean z24 = false;
            boolean z25 = false;
            boolean z26 = false;
            int i9 = 247;
            umi = new SupportDevice("umi", 31, z24, z25, z26, z19, z22, z13, z14, z15, i9, defaultConstructorMarker);
            lmi = new SupportDevice("lmi", 32, z, z2, z3, z20, z23, z21, z7, z16, i8, defaultConstructorMarker2);
            lmiin = new SupportDevice("lmiin", 33, z24, z25, z26, z19, z22, z13, z14, z15, i9, defaultConstructorMarker);
            lmipro = new SupportDevice("lmipro", 34, z, z2, z3, z20, z23, z21, z7, z16, i8, defaultConstructorMarker2);
            lmiinpro = new SupportDevice("lmiinpro", 35, z24, z25, z26, z19, z22, z13, z14, z15, i9, defaultConstructorMarker);
            picasso = new SupportDevice("picasso", 36, z, z2, z3, z20, z23, z21, z7, z16, i8, defaultConstructorMarker2);
            picassoin = new SupportDevice("picassoin", 37, z24, z25, z26, z19, z22, z13, z14, z15, i9, defaultConstructorMarker);
            curtana = new SupportDevice("curtana", 38, z, z2, z3, z20, z23, z21, z7, z16, i8, defaultConstructorMarker2);
            curtana_in1 = new SupportDevice("curtana_in1", 39, z24, z25, z26, z19, z22, z13, z14, z15, i9, defaultConstructorMarker);
            curtanacn = new SupportDevice("curtanacn", 40, z, z2, z3, z20, z23, z21, z7, z16, i8, defaultConstructorMarker2);
            durandal = new SupportDevice("durandal", 41, z24, z25, z26, z19, z22, z13, z14, z15, i9, defaultConstructorMarker);
            excalibur = new SupportDevice("excalibur", 42, z, z2, z3, z20, z23, z21, z7, z16, i8, defaultConstructorMarker2);
            joyeuse = new SupportDevice("joyeuse", 43, z24, z25, z26, z19, z22, z13, z14, z15, i9, defaultConstructorMarker);
            gram = new SupportDevice("gram", 44, z, z2, z3, z20, z23, z21, z7, z16, i8, defaultConstructorMarker2);
            monet = new SupportDevice("monet", 45, z24, z25, z26, z19, z22, z13, z14, z15, i9, defaultConstructorMarker);
            monetin = new SupportDevice("monetin", 46, z, z2, z3, z20, z23, z21, z7, z16, i8, defaultConstructorMarker2);
            vangogh = new SupportDevice("vangogh", 47, z24, z25, z26, z19, z22, z13, z14, z15, i9, defaultConstructorMarker);
            toco = new SupportDevice("toco", 48, z, z2, z3, z20, z23, z21, z7, z16, i8, defaultConstructorMarker2);
            tocoin = new SupportDevice("tocoin", 49, z24, z25, z26, z19, z22, z13, z14, z15, i9, defaultConstructorMarker);
            atom = new SupportDevice("atom", 50, z, z2, z3, z20, true, z21, z7, z16, 231, defaultConstructorMarker2);
            bomb = new SupportDevice("bomb", 51, z24, z25, z26, z19, true, z13, z14, z15, 231, defaultConstructorMarker);
            cas = new SupportDevice("cas", 52, true, true, z3, z20, false, z21, z7, z16, 244, defaultConstructorMarker2);
            boolean z27 = false;
            apollo = new SupportDevice("apollo", 53, z24, z25, z26, z19, z27, z13, z14, z15, 247, defaultConstructorMarker);
            boolean z28 = false;
            boolean z29 = false;
            cezanne = new SupportDevice("cezanne", 54, z28, z29, z3, z20, true, z21, z7, z16, 231, defaultConstructorMarker2);
            boolean z30 = true;
            int i10 = 243;
            gauguin = new SupportDevice("gauguin", 55, z24, z25, z30, z19, z27, z13, z14, z15, i10, defaultConstructorMarker);
            boolean z31 = false;
            gauguinpro = new SupportDevice("gauguinpro", 56, z28, z29, true, z20, z31, z21, z7, z16, 243, defaultConstructorMarker2);
            gauguininpro = new SupportDevice("gauguininpro", 57, z24, z25, z30, z19, z27, z13, z14, z15, i10, defaultConstructorMarker);
            surya = new SupportDevice("surya", 58, z28, z29, false, z20, z31, z21, z7, z16, 247, defaultConstructorMarker2);
            karna = new SupportDevice("karna", 59, z24, z25, false, z19, z27, z13, z14, z15, 247, defaultConstructorMarker);
            boolean z32 = true;
            int i11 = 243;
            cannon = new SupportDevice("cannon", 60, z28, z29, z32, z20, z31, z21, z7, z16, i11, defaultConstructorMarker2);
            boolean z33 = true;
            int i12 = 243;
            cannong = new SupportDevice("cannong", 61, z24, z25, z33, z19, z27, z13, z14, z15, i12, defaultConstructorMarker);
            lime = new SupportDevice("lime", 62, z28, z29, z32, z20, z31, z21, z7, z16, i11, defaultConstructorMarker2);
            citrus = new SupportDevice("citrus", 63, z24, z25, z33, z19, z27, z13, z14, z15, i12, defaultConstructorMarker);
            boolean z34 = true;
            boolean z35 = false;
            venus = new SupportDevice("venus", 64, z28, z34, z35, z20, z31, z21, z7, z16, 245, defaultConstructorMarker2);
            boolean z36 = true;
            boolean z37 = false;
            cetus = new SupportDevice("cetus", 65, z24, z36, z37, z19, z27, z13, z14, z15, 245, defaultConstructorMarker);
            star = new SupportDevice("star", 66, true, z34, z35, z20, z31, z21, z7, z16, 244, defaultConstructorMarker2);
            mars = new SupportDevice("mars", 67, true, z36, z37, z19, z27, z13, z14, z15, 244, defaultConstructorMarker);
            boolean z38 = false;
            courbet = new SupportDevice("courbet", 68, z38, z34, z35, z20, z31, z21, z7, z16, 245, defaultConstructorMarker2);
            boolean z39 = false;
            courbetin = new SupportDevice("courbetin", 69, z39, z36, z37, z19, z27, z13, z14, z15, 245, defaultConstructorMarker);
            sweet = new SupportDevice("sweet", 70, z38, z34, z35, z20, z31, z21, true, z16, 181, defaultConstructorMarker2);
            sweetin = new SupportDevice("sweetin", 71, z39, z36, z37, z19, z27, z13, true, z15, 181, defaultConstructorMarker);
            boolean z40 = false;
            haydn = new SupportDevice("haydn", 72, z38, z34, z35, z20, z31, z21, z40, z16, 245, defaultConstructorMarker2);
            boolean z41 = false;
            alioth = new SupportDevice("alioth", 73, z39, z36, z37, z19, z27, z13, z41, z15, 245, defaultConstructorMarker);
            boolean z42 = false;
            boolean z43 = true;
            int i13 = 243;
            rainbow = new SupportDevice("rainbow", 74, z38, z42, z43, z20, z31, z21, z40, z16, i13, defaultConstructorMarker2);
            boolean z44 = true;
            mojito = new SupportDevice("mojito", 75, z39, false, z44, z19, z27, z13, z41, z15, 243, defaultConstructorMarker);
            sunny = new SupportDevice("sunny", 76, z38, z42, z43, z20, z31, z21, z40, z16, i13, defaultConstructorMarker2);
            boolean z45 = true;
            boolean z46 = true;
            int i14 = 225;
            rosemary = new SupportDevice("rosemary", 77, z39, z45, z44, z19, z46, z13, z41, z15, i14, defaultConstructorMarker);
            boolean z47 = true;
            boolean z48 = true;
            int i15 = 225;
            maltose = new SupportDevice("maltose", 78, z38, z47, z43, z20, z48, z21, z40, z16, i15, defaultConstructorMarker2);
            rosemaryp = new SupportDevice("rosemaryp", 79, z39, z45, z44, z19, z46, z13, z41, z15, i14, defaultConstructorMarker);
            secret = new SupportDevice("secret", 80, z38, z47, z43, z20, z48, z21, z40, z16, i15, defaultConstructorMarker2);
            renoir = new SupportDevice("renoir", 81, z39, z45, z44, z19, false, z13, z41, z15, 241, defaultConstructorMarker);
            boolean z49 = false;
            thyme = new SupportDevice("thyme", 82, z38, z47, z49, z20, false, z21, z40, z16, 245, defaultConstructorMarker2);
            ares = new SupportDevice("ares", 83, z39, z45, false, z19, true, z13, z41, z15, 229, defaultConstructorMarker);
            aresin = new SupportDevice("aresin", 84, z38, z47, z49, z20, true, z21, z40, z16, 229, defaultConstructorMarker2);
            boolean z50 = false;
            lemon = new SupportDevice("lemon", 85, z39, false, true, z19, z50, z13, z41, z15, 243, defaultConstructorMarker);
            boolean z51 = false;
            pomelo = new SupportDevice("pomelo", 86, z38, false, true, z20, z51, z21, z40, z16, 243, defaultConstructorMarker2);
            boolean z52 = true;
            boolean z53 = false;
            haydnin = new SupportDevice("haydnin", 87, z39, z52, z53, z19, z50, z13, z41, z15, 245, defaultConstructorMarker);
            boolean z54 = false;
            aliothin = new SupportDevice("aliothin", 88, z38, true, z54, z20, z51, z21, z40, z16, 245, defaultConstructorMarker2);
            boolean z55 = true;
            chopin = new SupportDevice("chopin", 89, z39, z52, z53, z19, z55, z13, z41, z15, 229, defaultConstructorMarker);
            camellia = new SupportDevice("camellia", 90, z38, false, z54, z20, true, z21, z40, z16, 231, defaultConstructorMarker2);
            camellian = new SupportDevice("camellian", 91, z39, false, z53, z19, z55, z13, z41, z15, 231, defaultConstructorMarker);
            vayu = new SupportDevice("vayu", 92, z38, true, z54, z20, false, z21, z40, z16, 245, defaultConstructorMarker2);
            boolean z56 = true;
            boolean z57 = false;
            bhima = new SupportDevice("bhima", 93, z39, z56, z53, z19, z57, z13, z41, z15, 245, defaultConstructorMarker);
            biloba = new SupportDevice("biloba", 94, z38, false, true, z20, true, z21, z40, z16, 227, defaultConstructorMarker2);
            odin = new SupportDevice("odin", 95, true, z56, z53, z19, z57, z13, z41, z15, 244, defaultConstructorMarker);
            boolean z58 = true;
            boolean z59 = false;
            boolean z60 = false;
            int i16 = 245;
            enuma = new SupportDevice("enuma", 96, z38, z58, z59, z20, z60, z21, z40, z16, i16, defaultConstructorMarker2);
            boolean z61 = false;
            elish = new SupportDevice("elish", 97, z61, z56, z53, z19, z57, z13, z41, z15, 245, defaultConstructorMarker);
            argo = new SupportDevice("argo", 98, z38, z58, z59, z20, z60, z21, z40, z16, i16, defaultConstructorMarker2);
            agate = new SupportDevice("agate", 99, z61, z56, true, z19, true, z13, z41, z15, 225, defaultConstructorMarker);
            amber = new SupportDevice("amber", 100, z38, z58, true, z20, true, z21, z40, z16, 225, defaultConstructorMarker2);
            boolean z62 = false;
            boolean z63 = false;
            vili = new SupportDevice("vili", 101, true, z56, z62, z19, z63, z13, z41, z15, 244, defaultConstructorMarker);
            boolean z64 = false;
            iris = new SupportDevice("iris", 102, z38, false, false, z20, z64, z21, z40, z16, 247, defaultConstructorMarker2);
            boolean z65 = false;
            XIG02 = new SupportDevice("XIG02", 103, z65, false, z62, z19, z63, z13, z41, z15, 247, defaultConstructorMarker);
            boolean z66 = true;
            boolean z67 = true;
            lisa = new SupportDevice("lisa", 104, z38, z66, z67, z20, z64, z21, z40, z16, 241, defaultConstructorMarker2);
            boolean z68 = true;
            boolean z69 = true;
            boolean z70 = true;
            int i17 = 225;
            selene = new SupportDevice("selene", 105, z65, z68, z69, z19, z70, z13, z41, z15, i17, defaultConstructorMarker);
            boolean z71 = true;
            int i18 = 225;
            eos = new SupportDevice("eos", 106, z38, z66, z67, z20, z71, z21, z40, z16, i18, defaultConstructorMarker2);
            evergo = new SupportDevice("evergo", 107, z65, z68, z69, z19, z70, z13, z41, z15, i17, defaultConstructorMarker);
            evergreen = new SupportDevice("evergreen", 108, z38, z66, z67, z20, z71, z21, z40, z16, i18, defaultConstructorMarker2);
            boolean z72 = false;
            int i19 = 227;
            pissarro = new SupportDevice("pissarro", 109, z65, z72, z69, z19, z70, z13, z41, z15, i19, defaultConstructorMarker);
            boolean z73 = false;
            int i20 = 227;
            pissarropro = new SupportDevice("pissarropro", 110, z38, z73, z67, z20, z71, z21, z40, z16, i20, defaultConstructorMarker2);
            pissarroin = new SupportDevice("pissarroin", 111, z65, z72, z69, z19, z70, z13, z41, z15, i19, defaultConstructorMarker);
            pissarroinpro = new SupportDevice("pissarroinpro", 112, z38, z73, z67, z20, z71, z21, z40, z16, i20, defaultConstructorMarker2);
            boolean z74 = true;
            boolean z75 = false;
            lilac = new SupportDevice("lilac", 113, z65, z74, z69, z19, z75, z13, z41, z15, 241, defaultConstructorMarker);
            boolean z76 = true;
            boolean z77 = false;
            a101xm = new SupportDevice("a101xm", 114, z38, z76, z67, z20, z77, z21, z40, z16, 241, defaultConstructorMarker2);
            boolean z78 = false;
            mona = new SupportDevice("mona", 115, true, z74, z78, z19, z75, z13, z41, z15, 244, defaultConstructorMarker);
            zeus = new SupportDevice("zeus", 116, true, z76, false, z20, z77, z21, z40, z16, 244, defaultConstructorMarker2);
            boolean z79 = false;
            psyche = new SupportDevice("psyche", 117, z79, z74, z78, z19, z75, z13, z41, z15, 245, defaultConstructorMarker);
            boolean z80 = false;
            ingres = new SupportDevice("ingres", 118, z80, z76, true, z20, z77, z21, z40, z16, 241, defaultConstructorMarker2);
            boolean z81 = true;
            poussin = new SupportDevice("poussin", 119, z79, z74, z81, z19, z75, z13, z41, z15, 241, defaultConstructorMarker);
            cupid = new SupportDevice("cupid", 120, z80, z76, false, z20, z77, z21, z40, z16, 245, defaultConstructorMarker2);
            fleur = new SupportDevice("fleur", 121, z79, z74, z81, z19, true, z13, z41, z15, 225, defaultConstructorMarker);
            boolean z82 = true;
            miel = new SupportDevice("miel", 122, z80, z76, z82, z20, true, z21, z40, z16, 225, defaultConstructorMarker2);
            veux = new SupportDevice("veux", 123, z79, false, z81, z19, false, z13, z41, z15, 243, defaultConstructorMarker);
            peux = new SupportDevice("peux", 124, z80, false, z82, z20, false, z21, z40, z16, 243, defaultConstructorMarker2);
            viva = new SupportDevice("viva", 125, z79, true, z81, z19, true, z13, z41, z15, 225, defaultConstructorMarker);
            vida = new SupportDevice("vida", 126, z80, true, z82, z20, true, z21, z40, z16, 225, defaultConstructorMarker2);
            boolean z83 = false;
            spes = new SupportDevice("spes", 127, z79, false, z81, z19, z83, z13, z41, z15, 243, defaultConstructorMarker);
            spesn = new SupportDevice("spesn", 128, z80, false, z82, z20, false, z21, z40, z16, 243, defaultConstructorMarker2);
            boolean z84 = true;
            munch = new SupportDevice("munch", 129, z79, z84, z81, z19, z83, z13, z41, z15, 241, defaultConstructorMarker);
            boolean z85 = true;
            rubens = new SupportDevice("rubens", 130, z80, z85, z82, z20, true, z21, z40, z16, 225, defaultConstructorMarker2);
            matisse = new SupportDevice("matisse", 131, z79, z84, z81, z19, true, z13, z41, z15, 225, defaultConstructorMarker);
            boolean z86 = false;
            thor = new SupportDevice("thor", 132, true, z85, z86, z20, false, z21, z40, z16, 244, defaultConstructorMarker2);
            boolean z87 = false;
            loki = new SupportDevice("loki", 133, true, z84, z87, z19, false, z13, z41, z15, 244, defaultConstructorMarker);
            light = new SupportDevice("light", 134, false, z85, z86, z20, true, z21, z40, z16, 229, defaultConstructorMarker2);
            thunder = new SupportDevice("thunder", 135, false, z84, z87, z19, true, z13, z41, z15, 229, defaultConstructorMarker);
            boolean z88 = true;
            boolean z89 = false;
            int i21 = 244;
            zizhan = new SupportDevice("zizhan", 136, z88, z85, z86, z20, z89, z21, z40, z16, i21, defaultConstructorMarker2);
            taoyao = new SupportDevice("taoyao", 137, true, z84, z87, z19, false, z13, z41, z15, 244, defaultConstructorMarker);
            zijin = new SupportDevice("zijin", 138, z88, z85, z86, z20, z89, z21, z40, z16, i21, defaultConstructorMarker2);
            boolean z90 = false;
            boolean z91 = true;
            int i22 = 229;
            opal = new SupportDevice("opal", 139, z90, z84, z87, z19, z91, z13, z41, z15, i22, defaultConstructorMarker);
            boolean z92 = false;
            frost = new SupportDevice("frost", 140, z92, z85, true, z20, z89, z21, z40, z16, 241, defaultConstructorMarker2);
            xaga = new SupportDevice("xaga", 141, z90, z84, z87, z19, z91, z13, z41, z15, i22, defaultConstructorMarker);
            boolean z93 = false;
            boolean z94 = true;
            int i23 = 229;
            xagapro = new SupportDevice("xagapro", 142, z92, z85, z93, z20, z94, z21, z40, z16, i23, defaultConstructorMarker2);
            xagain = new SupportDevice("xagain", 143, z90, z84, z87, z19, z91, z13, z41, z15, i22, defaultConstructorMarker);
            xagaproin = new SupportDevice("xagaproin", 144, z92, z85, z93, z20, z94, z21, z40, z16, i23, defaultConstructorMarker2);
            mayfly = new SupportDevice("mayfly", 145, z90, z84, z87, z19, false, z13, z41, z15, 245, defaultConstructorMarker);
            unicorn = new SupportDevice("unicorn", 146, true, z85, z93, z20, false, z21, z40, z16, 244, defaultConstructorMarker2);
            daumier = new SupportDevice("daumier", 147, z90, z84, z87, z19, true, z13, z41, z15, 229, defaultConstructorMarker);
            boolean z95 = false;
            plato = new SupportDevice("plato", 148, z95, z85, z93, z20, true, z21, z40, z16, 229, defaultConstructorMarker2);
            boolean z96 = false;
            diting = new SupportDevice("diting", 149, z90, z84, z87, z19, z96, z13, z41, z15, 245, defaultConstructorMarker);
            ditingp = new SupportDevice("ditingp", AnimTask.MAX_PAGE_SIZE, z95, z85, z93, z20, false, z21, z40, z16, 245, defaultConstructorMarker2);
            dagu = new SupportDevice("dagu", 151, z90, false, z87, z19, z96, z13, z41, z15, 247, defaultConstructorMarker);
            rock = new SupportDevice("rock", 152, z95, z85, z93, z20, true, z21, z40, z16, 229, defaultConstructorMarker2);
            stone = new SupportDevice("stone", 153, z90, true, z87, z19, true, z13, z41, z15, 229, defaultConstructorMarker);
            boolean z97 = true;
            boolean z98 = false;
            ziyi = new SupportDevice("ziyi", 154, z97, z85, z93, z20, z98, z21, z40, z16, 244, defaultConstructorMarker2);
            boolean z99 = false;
            boolean z100 = false;
            boolean z101 = true;
            boolean z102 = true;
            nuwa = new SupportDevice("nuwa", 155, true, z99, z87, z100, false, z13, z101, z102, 62, defaultConstructorMarker);
            boolean z103 = false;
            boolean z104 = false;
            boolean z105 = true;
            boolean z106 = true;
            fuxi = new SupportDevice("fuxi", 156, z97, z103, z93, z104, z98, z21, z105, z106, 62, defaultConstructorMarker2);
            boolean z107 = false;
            boolean z108 = true;
            int i24 = 47;
            ruby = new SupportDevice("ruby", 157, z107, z99, z87, z100, z108, z13, z101, z102, i24, defaultConstructorMarker);
            boolean z109 = false;
            rubypro = new SupportDevice("rubypro", 158, z109, z103, z93, z104, true, z21, z105, z106, 47, defaultConstructorMarker2);
            rubyplus = new SupportDevice("rubyplus", 159, z107, z99, z87, z100, z108, z13, z101, z102, i24, defaultConstructorMarker);
            boolean z110 = false;
            int i25 = 63;
            moonstone = new SupportDevice("moonstone", 160, z109, z103, z93, z104, z110, z21, z105, z106, i25, defaultConstructorMarker2);
            boolean z111 = false;
            int i26 = 63;
            sunstone = new SupportDevice("sunstone", 161, z107, z99, z87, z100, z111, z13, z101, z102, i26, defaultConstructorMarker);
            redwood = new SupportDevice("redwood", 162, z109, z103, z93, z104, z110, z21, z105, z106, i25, defaultConstructorMarker2);
            redwoodin = new SupportDevice("redwoodin", 163, z107, z99, z87, z100, z111, z13, z101, z102, i26, defaultConstructorMarker);
            rembrandt = new SupportDevice("rembrandt", 164, z109, z103, z93, z104, true, z21, z105, z106, 47, defaultConstructorMarker2);
            socrates = new SupportDevice("socrates", 165, z107, z99, z87, z100, z111, z13, z101, z102, i26, defaultConstructorMarker);
            boolean z112 = false;
            int i27 = 63;
            mondrian = new SupportDevice("mondrian", 166, z109, z103, z93, z104, z112, z21, z105, z106, i27, defaultConstructorMarker2);
            topaz = new SupportDevice("topaz", 167, z107, z99, z87, z100, z111, z13, z101, z102, i26, defaultConstructorMarker);
            tapas = new SupportDevice("tapas", 168, z109, z103, z93, z104, z112, z21, z105, z106, i27, defaultConstructorMarker2);
            marble = new SupportDevice("marble", 169, z107, z99, z87, z100, z111, z13, z101, z102, i26, defaultConstructorMarker);
            marblein = new SupportDevice("marblein", 170, z109, z103, z93, z104, z112, z21, z105, z106, i27, defaultConstructorMarker2);
            ishtar = new SupportDevice("ishtar", 171, true, z99, z87, z100, z111, z13, z101, z102, 62, defaultConstructorMarker);
            liuqin = new SupportDevice("liuqin", 172, z109, z103, z93, z104, z112, z21, z105, z106, i27, defaultConstructorMarker2);
            boolean z113 = false;
            boolean z114 = true;
            sea = new SupportDevice("sea", 173, z113, z99, z87, true, z114, z13, false, z102, 103, defaultConstructorMarker);
            ocean = new SupportDevice("ocean", 174, z109, z103, z93, true, true, z21, false, z106, 103, defaultConstructorMarker2);
            boolean z115 = false;
            boolean z116 = true;
            int i28 = 47;
            yuechu = new SupportDevice("yuechu", 175, z113, z99, z87, z115, z114, z13, z116, z102, i28, defaultConstructorMarker);
            boolean z117 = false;
            boolean z118 = true;
            babylon = new SupportDevice("babylon", 176, true, z103, z93, z117, false, z21, z118, z106, 62, defaultConstructorMarker2);
            fire = new SupportDevice("fire", 177, z113, z99, z87, z115, z114, z13, z116, z102, i28, defaultConstructorMarker);
            boolean z119 = false;
            heat = new SupportDevice("heat", 178, z119, z103, z93, z117, true, z21, z118, z106, 47, defaultConstructorMarker2);
            boolean z120 = false;
            int i29 = 63;
            sky = new SupportDevice("sky", 179, z113, z99, z87, z115, z120, z13, z116, z102, i29, defaultConstructorMarker);
            river = new SupportDevice("river", 180, z119, z103, z93, z117, false, z21, z118, z106, 63, defaultConstructorMarker2);
            XIG03 = new SupportDevice("XIG03", 181, z113, z99, z87, z115, z120, z13, z116, z102, i29, defaultConstructorMarker);
            boolean z121 = true;
            pearl = new SupportDevice("pearl", 182, z119, z103, z93, z117, z121, z21, z118, z106, 47, defaultConstructorMarker2);
            boolean z122 = true;
            boolean z123 = true;
            int i30 = 46;
            aristotle = new SupportDevice("aristotle", 183, z122, z99, z87, z115, z123, z13, z116, z102, i30, defaultConstructorMarker);
            XIG04 = new SupportDevice("XIG04", 184, true, z103, z93, z117, z121, z21, z118, z106, 46, defaultConstructorMarker2);
            corot = new SupportDevice("corot", 185, z122, z99, z87, z115, z123, z13, z116, z102, i30, defaultConstructorMarker);
            boolean z124 = false;
            boolean z125 = false;
            int i31 = 63;
            yudi = new SupportDevice("yudi", 186, z124, z103, z93, z117, z125, z21, z118, z106, i31, defaultConstructorMarker2);
            boolean z126 = false;
            zircon = new SupportDevice("zircon", 187, z126, z99, z87, z115, z123, z13, z116, z102, 47, defaultConstructorMarker);
            garnet = new SupportDevice("garnet", 188, z124, z103, z93, z117, z125, z21, z118, z106, i31, defaultConstructorMarker2);
            boolean z127 = false;
            XIG05 = new SupportDevice("XIG05", 189, z126, z99, z87, z115, z127, z13, z116, z102, 63, defaultConstructorMarker);
            boolean z128 = true;
            houji = new SupportDevice("houji", 190, z128, z103, z93, z117, z125, z21, false, z106, 126, defaultConstructorMarker2);
            shennong = new SupportDevice("shennong", 191, true, z99, z87, z115, z127, z13, false, z102, 126, defaultConstructorMarker);
            duchamp = new SupportDevice("duchamp", 192, z128, z103, z93, z117, true, z21, true, z106, 46, defaultConstructorMarker2);
        }
    }

    static {
        SupportDevice[] supportDeviceArrValues = SupportDevice.values();
        int length = supportDeviceArrValues.length;
        int i = 0;
        while (i < length) {
            SupportDevice supportDevice = supportDeviceArrValues[i];
            i++;
            if (Build.DEVICE.equals(supportDevice.name())) {
                item = supportDevice;
                return;
            }
        }
    }

    public static final boolean isSupport() {
        return item != null;
    }

    public static final boolean isMiMTK() {
        SupportDevice supportDevice = item;
        if (supportDevice == null) {
            return false;
        }
        return supportDevice.getMiMtk();
    }

    public static final boolean isMi() {
        SupportDevice supportDevice = item;
        if (supportDevice == null) {
            return false;
        }
        return supportDevice.getMi();
    }

    public static final boolean isMeitu() {
        SupportDevice supportDevice = item;
        if (supportDevice == null) {
            return false;
        }
        return supportDevice.getMeitu();
    }

    public static final boolean isBrianRelight() {
        SupportDevice supportDevice = item;
        if (supportDevice == null) {
            return false;
        }
        return supportDevice.getBrainRelight();
    }

    public static final boolean isMiDual() {
        SupportDevice supportDevice = item;
        if (supportDevice == null) {
            return false;
        }
        return supportDevice.getMiDual();
    }

    public static final boolean isMegvii() {
        SupportDevice supportDevice = item;
        if (supportDevice == null) {
            return false;
        }
        return supportDevice.getMegvii();
    }

    public static final boolean isSupportMiAlgo() {
        SupportDevice supportDevice = item;
        if (supportDevice == null) {
            return false;
        }
        return supportDevice.getMiAlgo();
    }

    public static final boolean isForbidRelight() {
        SupportDevice supportDevice = item;
        if (supportDevice == null) {
            return false;
        }
        return supportDevice.getForbidRelight();
    }
}
