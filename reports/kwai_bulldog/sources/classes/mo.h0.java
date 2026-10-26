package mo;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.yxcorp.bugly.CrashReporter;
import java.io.IOException;
import java.util.Objects;

/* JADX INFO: compiled from: FaceDetectConfig.java */
/* JADX INFO: loaded from: classes15.dex */
public final class h0 extends GeneratedMessageLite<h0, b> implements MessageLiteOrBuilder {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private static final h0 f78643p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private static volatile Parser<h0> f78644q;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f78645a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float f78646b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f78647c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f78648d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f78649e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f78650f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f78651g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f78652h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f78653i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private String f78654j = "";

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private boolean f78655k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f78656l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f78657m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private boolean f78658n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private int f78659o;

    /* JADX INFO: compiled from: FaceDetectConfig.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f78660a;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f78660a = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e12) {
                CrashReporter.logCheckedException(e12, "FaceDetectConfig$1.class", "<clinit>", -1);
            }
            try {
                f78660a[GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError e13) {
                CrashReporter.logCheckedException(e13, "FaceDetectConfig$1.class", "<clinit>", -1);
            }
            try {
                f78660a[GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE.ordinal()] = 3;
            } catch (NoSuchFieldError e14) {
                CrashReporter.logCheckedException(e14, "FaceDetectConfig$1.class", "<clinit>", -1);
            }
            try {
                f78660a[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 4;
            } catch (NoSuchFieldError e15) {
                CrashReporter.logCheckedException(e15, "FaceDetectConfig$1.class", "<clinit>", -1);
            }
            try {
                f78660a[GeneratedMessageLite.MethodToInvoke.VISIT.ordinal()] = 5;
            } catch (NoSuchFieldError e16) {
                CrashReporter.logCheckedException(e16, "FaceDetectConfig$1.class", "<clinit>", -1);
            }
            try {
                f78660a[GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM.ordinal()] = 6;
            } catch (NoSuchFieldError e17) {
                CrashReporter.logCheckedException(e17, "FaceDetectConfig$1.class", "<clinit>", -1);
            }
            try {
                f78660a[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 7;
            } catch (NoSuchFieldError e18) {
                CrashReporter.logCheckedException(e18, "FaceDetectConfig$1.class", "<clinit>", -1);
            }
            try {
                f78660a[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 8;
            } catch (NoSuchFieldError e19) {
                CrashReporter.logCheckedException(e19, "FaceDetectConfig$1.class", "<clinit>", -1);
            }
        }
    }

    /* JADX INFO: compiled from: FaceDetectConfig.java */
    public static final class b extends GeneratedMessageLite.Builder<h0, b> implements MessageLiteOrBuilder {
        /* synthetic */ b(a aVar) {
            this();
        }

        public b a(float f12) {
            copyOnWrite();
            ((h0) this.instance).i(f12);
            return this;
        }

        public b b(float f12) {
            copyOnWrite();
            ((h0) this.instance).j(f12);
            return this;
        }

        public b c(float f12) {
            copyOnWrite();
            ((h0) this.instance).k(f12);
            return this;
        }

        public b d(j0 j0Var) {
            copyOnWrite();
            ((h0) this.instance).l(j0Var);
            return this;
        }

        public b e(boolean z12) {
            copyOnWrite();
            ((h0) this.instance).m(z12);
            return this;
        }

        private b() {
            super(h0.f78643p);
        }
    }

    static {
        h0 h0Var = new h0();
        f78643p = h0Var;
        h0Var.makeImmutable();
    }

    private h0() {
    }

    public static b h() {
        return f78643p.toBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(float f12) {
        this.f78648d = f12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(float f12) {
        this.f78647c = f12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k(float f12) {
        this.f78646b = f12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l(j0 j0Var) {
        Objects.requireNonNull(j0Var);
        this.f78645a = j0Var.getNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(boolean z12) {
        this.f78649e = z12;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        a aVar = null;
        boolean z12 = false;
        switch (a.f78660a[methodToInvoke.ordinal()]) {
            case 1:
                return new h0();
            case 2:
                return f78643p;
            case 3:
                return null;
            case 4:
                return new b(aVar);
            case 5:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                h0 h0Var = (h0) obj2;
                int i12 = this.f78645a;
                boolean z13 = i12 != 0;
                int i13 = h0Var.f78645a;
                this.f78645a = visitor.visitInt(z13, i12, i13 != 0, i13);
                float f12 = this.f78646b;
                boolean z14 = f12 != 0.0f;
                float f13 = h0Var.f78646b;
                this.f78646b = visitor.visitFloat(z14, f12, f13 != 0.0f, f13);
                float f14 = this.f78647c;
                boolean z15 = f14 != 0.0f;
                float f15 = h0Var.f78647c;
                this.f78647c = visitor.visitFloat(z15, f14, f15 != 0.0f, f15);
                float f16 = this.f78648d;
                boolean z16 = f16 != 0.0f;
                float f17 = h0Var.f78648d;
                this.f78648d = visitor.visitFloat(z16, f16, f17 != 0.0f, f17);
                boolean z17 = this.f78649e;
                boolean z18 = h0Var.f78649e;
                this.f78649e = visitor.visitBoolean(z17, z17, z18, z18);
                boolean z19 = this.f78650f;
                boolean z22 = h0Var.f78650f;
                this.f78650f = visitor.visitBoolean(z19, z19, z22, z22);
                boolean z23 = this.f78651g;
                boolean z24 = h0Var.f78651g;
                this.f78651g = visitor.visitBoolean(z23, z23, z24, z24);
                boolean z25 = this.f78652h;
                boolean z26 = h0Var.f78652h;
                this.f78652h = visitor.visitBoolean(z25, z25, z26, z26);
                boolean z27 = this.f78653i;
                boolean z28 = h0Var.f78653i;
                this.f78653i = visitor.visitBoolean(z27, z27, z28, z28);
                this.f78654j = visitor.visitString(!this.f78654j.isEmpty(), this.f78654j, !h0Var.f78654j.isEmpty(), h0Var.f78654j);
                boolean z29 = this.f78655k;
                boolean z32 = h0Var.f78655k;
                this.f78655k = visitor.visitBoolean(z29, z29, z32, z32);
                boolean z33 = this.f78656l;
                boolean z34 = h0Var.f78656l;
                this.f78656l = visitor.visitBoolean(z33, z33, z34, z34);
                boolean z35 = this.f78657m;
                boolean z36 = h0Var.f78657m;
                this.f78657m = visitor.visitBoolean(z35, z35, z36, z36);
                boolean z37 = this.f78658n;
                boolean z38 = h0Var.f78658n;
                this.f78658n = visitor.visitBoolean(z37, z37, z38, z38);
                int i14 = this.f78659o;
                boolean z39 = i14 != 0;
                int i15 = h0Var.f78659o;
                this.f78659o = visitor.visitInt(z39, i14, i15 != 0, i15);
                GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                return this;
            case 6:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                while (!z12) {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            switch (tag) {
                                case 0:
                                    break;
                                case 8:
                                    this.f78645a = codedInputStream.readEnum();
                                    continue;
                                case 21:
                                    this.f78646b = codedInputStream.readFloat();
                                    continue;
                                case 29:
                                    this.f78647c = codedInputStream.readFloat();
                                    continue;
                                case 37:
                                    this.f78648d = codedInputStream.readFloat();
                                    continue;
                                case 40:
                                    this.f78649e = codedInputStream.readBool();
                                    continue;
                                case 48:
                                    this.f78650f = codedInputStream.readBool();
                                    continue;
                                case 56:
                                    this.f78651g = codedInputStream.readBool();
                                    continue;
                                case 64:
                                    this.f78652h = codedInputStream.readBool();
                                    continue;
                                case 72:
                                    this.f78653i = codedInputStream.readBool();
                                    continue;
                                case 82:
                                    this.f78654j = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 88:
                                    this.f78655k = codedInputStream.readBool();
                                    continue;
                                case 96:
                                    this.f78656l = codedInputStream.readBool();
                                    continue;
                                case 104:
                                    this.f78657m = codedInputStream.readBool();
                                    continue;
                                case 112:
                                    this.f78658n = codedInputStream.readBool();
                                    continue;
                                case 120:
                                    this.f78659o = codedInputStream.readInt32();
                                    continue;
                                default:
                                    if (!codedInputStream.skipField(tag)) {
                                        break;
                                    }
                                    break;
                            }
                            z12 = true;
                        } catch (Throwable th2) {
                            CrashReporter.logCheckedException(th2, "FaceDetectConfig.class", "dynamicMethod", 126);
                            throw th2;
                        }
                    } catch (InvalidProtocolBufferException e12) {
                        CrashReporter.logCheckedException(e12, "FaceDetectConfig.class", "dynamicMethod", 120);
                        throw new RuntimeException(e12.setUnfinishedMessage(this));
                    } catch (IOException e13) {
                        CrashReporter.logCheckedException(e13, "FaceDetectConfig.class", "dynamicMethod", 122);
                        throw new RuntimeException(new InvalidProtocolBufferException(e13.getMessage()).setUnfinishedMessage(this));
                    }
                }
                break;
            case 7:
                break;
            case 8:
                if (f78644q == null) {
                    synchronized (h0.class) {
                        try {
                            if (f78644q == null) {
                                f78644q = new GeneratedMessageLite.DefaultInstanceBasedParser(f78643p);
                            }
                        } catch (Throwable th3) {
                            CrashReporter.logCheckedException(th3, "FaceDetectConfig.class", "dynamicMethod", -1);
                            throw th3;
                        }
                        break;
                    }
                }
                return f78644q;
            default:
                throw new UnsupportedOperationException();
        }
        return f78643p;
    }

    public String g() {
        return this.f78654j;
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i12 = this.memoizedSerializedSize;
        if (i12 != -1) {
            return i12;
        }
        int iComputeEnumSize = this.f78645a != j0.kNormal.getNumber() ? 0 + CodedOutputStream.computeEnumSize(1, this.f78645a) : 0;
        float f12 = this.f78646b;
        if (f12 != 0.0f) {
            iComputeEnumSize += CodedOutputStream.computeFloatSize(2, f12);
        }
        float f13 = this.f78647c;
        if (f13 != 0.0f) {
            iComputeEnumSize += CodedOutputStream.computeFloatSize(3, f13);
        }
        float f14 = this.f78648d;
        if (f14 != 0.0f) {
            iComputeEnumSize += CodedOutputStream.computeFloatSize(4, f14);
        }
        boolean z12 = this.f78649e;
        if (z12) {
            iComputeEnumSize += CodedOutputStream.computeBoolSize(5, z12);
        }
        boolean z13 = this.f78650f;
        if (z13) {
            iComputeEnumSize += CodedOutputStream.computeBoolSize(6, z13);
        }
        boolean z14 = this.f78651g;
        if (z14) {
            iComputeEnumSize += CodedOutputStream.computeBoolSize(7, z14);
        }
        boolean z15 = this.f78652h;
        if (z15) {
            iComputeEnumSize += CodedOutputStream.computeBoolSize(8, z15);
        }
        boolean z16 = this.f78653i;
        if (z16) {
            iComputeEnumSize += CodedOutputStream.computeBoolSize(9, z16);
        }
        if (!this.f78654j.isEmpty()) {
            iComputeEnumSize += CodedOutputStream.computeStringSize(10, g());
        }
        boolean z17 = this.f78655k;
        if (z17) {
            iComputeEnumSize += CodedOutputStream.computeBoolSize(11, z17);
        }
        boolean z18 = this.f78656l;
        if (z18) {
            iComputeEnumSize += CodedOutputStream.computeBoolSize(12, z18);
        }
        boolean z19 = this.f78657m;
        if (z19) {
            iComputeEnumSize += CodedOutputStream.computeBoolSize(13, z19);
        }
        boolean z22 = this.f78658n;
        if (z22) {
            iComputeEnumSize += CodedOutputStream.computeBoolSize(14, z22);
        }
        int i13 = this.f78659o;
        if (i13 != 0) {
            iComputeEnumSize += CodedOutputStream.computeInt32Size(15, i13);
        }
        this.memoizedSerializedSize = iComputeEnumSize;
        return iComputeEnumSize;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.f78645a != j0.kNormal.getNumber()) {
            codedOutputStream.writeEnum(1, this.f78645a);
        }
        float f12 = this.f78646b;
        if (f12 != 0.0f) {
            codedOutputStream.writeFloat(2, f12);
        }
        float f13 = this.f78647c;
        if (f13 != 0.0f) {
            codedOutputStream.writeFloat(3, f13);
        }
        float f14 = this.f78648d;
        if (f14 != 0.0f) {
            codedOutputStream.writeFloat(4, f14);
        }
        boolean z12 = this.f78649e;
        if (z12) {
            codedOutputStream.writeBool(5, z12);
        }
        boolean z13 = this.f78650f;
        if (z13) {
            codedOutputStream.writeBool(6, z13);
        }
        boolean z14 = this.f78651g;
        if (z14) {
            codedOutputStream.writeBool(7, z14);
        }
        boolean z15 = this.f78652h;
        if (z15) {
            codedOutputStream.writeBool(8, z15);
        }
        boolean z16 = this.f78653i;
        if (z16) {
            codedOutputStream.writeBool(9, z16);
        }
        if (!this.f78654j.isEmpty()) {
            codedOutputStream.writeString(10, g());
        }
        boolean z17 = this.f78655k;
        if (z17) {
            codedOutputStream.writeBool(11, z17);
        }
        boolean z18 = this.f78656l;
        if (z18) {
            codedOutputStream.writeBool(12, z18);
        }
        boolean z19 = this.f78657m;
        if (z19) {
            codedOutputStream.writeBool(13, z19);
        }
        boolean z22 = this.f78658n;
        if (z22) {
            codedOutputStream.writeBool(14, z22);
        }
        int i12 = this.f78659o;
        if (i12 != 0) {
            codedOutputStream.writeInt32(15, i12);
        }
    }
}
