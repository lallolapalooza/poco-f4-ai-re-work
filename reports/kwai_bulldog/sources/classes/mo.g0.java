package mo;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.yxcorp.bugly.CrashReporter;
import java.io.IOException;

/* JADX INFO: compiled from: FaceData.java */
/* JADX INFO: loaded from: classes15.dex */
public final class g0 extends GeneratedMessageLite<g0, b> implements MessageLiteOrBuilder {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final g0 f78632i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static volatile Parser<g0> f78633j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float f78634a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float f78635b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f78636c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private e1 f78637d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private n0 f78638e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private m0 f78639f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private e1 f78640g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f78641h;

    /* JADX INFO: compiled from: FaceData.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f78642a;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f78642a = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e12) {
                CrashReporter.logCheckedException(e12, "FaceData$1.class", "<clinit>", -1);
            }
            try {
                f78642a[GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError e13) {
                CrashReporter.logCheckedException(e13, "FaceData$1.class", "<clinit>", -1);
            }
            try {
                f78642a[GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE.ordinal()] = 3;
            } catch (NoSuchFieldError e14) {
                CrashReporter.logCheckedException(e14, "FaceData$1.class", "<clinit>", -1);
            }
            try {
                f78642a[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 4;
            } catch (NoSuchFieldError e15) {
                CrashReporter.logCheckedException(e15, "FaceData$1.class", "<clinit>", -1);
            }
            try {
                f78642a[GeneratedMessageLite.MethodToInvoke.VISIT.ordinal()] = 5;
            } catch (NoSuchFieldError e16) {
                CrashReporter.logCheckedException(e16, "FaceData$1.class", "<clinit>", -1);
            }
            try {
                f78642a[GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM.ordinal()] = 6;
            } catch (NoSuchFieldError e17) {
                CrashReporter.logCheckedException(e17, "FaceData$1.class", "<clinit>", -1);
            }
            try {
                f78642a[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 7;
            } catch (NoSuchFieldError e18) {
                CrashReporter.logCheckedException(e18, "FaceData$1.class", "<clinit>", -1);
            }
            try {
                f78642a[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 8;
            } catch (NoSuchFieldError e19) {
                CrashReporter.logCheckedException(e19, "FaceData$1.class", "<clinit>", -1);
            }
        }
    }

    /* JADX INFO: compiled from: FaceData.java */
    public static final class b extends GeneratedMessageLite.Builder<g0, b> implements MessageLiteOrBuilder {
        /* synthetic */ b(a aVar) {
            this();
        }

        private b() {
            super(g0.f78632i);
        }
    }

    static {
        g0 g0Var = new g0();
        f78632i = g0Var;
        g0Var.makeImmutable();
    }

    private g0() {
    }

    public static g0 f(byte[] bArr) throws InvalidProtocolBufferException {
        return (g0) GeneratedMessageLite.parseFrom(f78632i, bArr);
    }

    public static Parser<g0> parser() {
        return f78632i.getParserForType();
    }

    public m0 b() {
        m0 m0Var = this.f78639f;
        return m0Var == null ? m0.b() : m0Var;
    }

    public e1 c() {
        e1 e1Var = this.f78640g;
        return e1Var == null ? e1.b() : e1Var;
    }

    public n0 d() {
        n0 n0Var = this.f78638e;
        return n0Var == null ? n0.b() : n0Var;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        a aVar = null;
        boolean z12 = false;
        switch (a.f78642a[methodToInvoke.ordinal()]) {
            case 1:
                return new g0();
            case 2:
                return f78632i;
            case 3:
                return null;
            case 4:
                return new b(aVar);
            case 5:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                g0 g0Var = (g0) obj2;
                float f12 = this.f78634a;
                boolean z13 = f12 != 0.0f;
                float f13 = g0Var.f78634a;
                this.f78634a = visitor.visitFloat(z13, f12, f13 != 0.0f, f13);
                float f14 = this.f78635b;
                boolean z14 = f14 != 0.0f;
                float f15 = g0Var.f78635b;
                this.f78635b = visitor.visitFloat(z14, f14, f15 != 0.0f, f15);
                float f16 = this.f78636c;
                boolean z15 = f16 != 0.0f;
                float f17 = g0Var.f78636c;
                this.f78636c = visitor.visitFloat(z15, f16, f17 != 0.0f, f17);
                this.f78637d = (e1) visitor.visitMessage(this.f78637d, g0Var.f78637d);
                this.f78638e = (n0) visitor.visitMessage(this.f78638e, g0Var.f78638e);
                this.f78639f = (m0) visitor.visitMessage(this.f78639f, g0Var.f78639f);
                this.f78640g = (e1) visitor.visitMessage(this.f78640g, g0Var.f78640g);
                int i12 = this.f78641h;
                boolean z16 = i12 != 0;
                int i13 = g0Var.f78641h;
                this.f78641h = visitor.visitInt(z16, i12, i13 != 0, i13);
                GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                return this;
            case 6:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z12) {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 13) {
                                    this.f78634a = codedInputStream.readFloat();
                                } else if (tag == 21) {
                                    this.f78635b = codedInputStream.readFloat();
                                } else if (tag == 29) {
                                    this.f78636c = codedInputStream.readFloat();
                                } else if (tag == 34) {
                                    e1 e1Var = this.f78637d;
                                    e1.b builder = e1Var != null ? e1Var.toBuilder() : null;
                                    e1 e1Var2 = (e1) codedInputStream.readMessage(e1.parser(), extensionRegistryLite);
                                    this.f78637d = e1Var2;
                                    if (builder != null) {
                                        builder.mergeFrom(e1Var2);
                                        this.f78637d = builder.buildPartial();
                                    }
                                } else if (tag == 42) {
                                    n0 n0Var = this.f78638e;
                                    n0.b builder2 = n0Var != null ? n0Var.toBuilder() : null;
                                    n0 n0Var2 = (n0) codedInputStream.readMessage(n0.parser(), extensionRegistryLite);
                                    this.f78638e = n0Var2;
                                    if (builder2 != null) {
                                        builder2.mergeFrom(n0Var2);
                                        this.f78638e = builder2.buildPartial();
                                    }
                                } else if (tag == 50) {
                                    m0 m0Var = this.f78639f;
                                    m0.b builder3 = m0Var != null ? m0Var.toBuilder() : null;
                                    m0 m0Var2 = (m0) codedInputStream.readMessage(m0.parser(), extensionRegistryLite);
                                    this.f78639f = m0Var2;
                                    if (builder3 != null) {
                                        builder3.mergeFrom(m0Var2);
                                        this.f78639f = builder3.buildPartial();
                                    }
                                } else if (tag == 58) {
                                    e1 e1Var3 = this.f78640g;
                                    e1.b builder4 = e1Var3 != null ? e1Var3.toBuilder() : null;
                                    e1 e1Var4 = (e1) codedInputStream.readMessage(e1.parser(), extensionRegistryLite);
                                    this.f78640g = e1Var4;
                                    if (builder4 != null) {
                                        builder4.mergeFrom(e1Var4);
                                        this.f78640g = builder4.buildPartial();
                                    }
                                } else if (tag == 64) {
                                    this.f78641h = codedInputStream.readEnum();
                                } else if (!codedInputStream.skipField(tag)) {
                                }
                            }
                            z12 = true;
                        } catch (Throwable th2) {
                            CrashReporter.logCheckedException(th2, "FaceData.class", "dynamicMethod", -104);
                            throw th2;
                        }
                    } catch (InvalidProtocolBufferException e12) {
                        CrashReporter.logCheckedException(e12, "FaceData.class", "dynamicMethod", -110);
                        throw new RuntimeException(e12.setUnfinishedMessage(this));
                    } catch (IOException e13) {
                        CrashReporter.logCheckedException(e13, "FaceData.class", "dynamicMethod", -108);
                        throw new RuntimeException(new InvalidProtocolBufferException(e13.getMessage()).setUnfinishedMessage(this));
                    }
                }
                break;
            case 7:
                break;
            case 8:
                if (f78633j == null) {
                    synchronized (g0.class) {
                        try {
                            if (f78633j == null) {
                                f78633j = new GeneratedMessageLite.DefaultInstanceBasedParser(f78632i);
                            }
                        } catch (Throwable th3) {
                            CrashReporter.logCheckedException(th3, "FaceData.class", "dynamicMethod", -1);
                            throw th3;
                        }
                        break;
                    }
                }
                return f78633j;
            default:
                throw new UnsupportedOperationException();
        }
        return f78632i;
    }

    public e1 e() {
        e1 e1Var = this.f78637d;
        return e1Var == null ? e1.b() : e1Var;
    }

    public float getConfidence() {
        return this.f78636c;
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i12 = this.memoizedSerializedSize;
        if (i12 != -1) {
            return i12;
        }
        float f12 = this.f78634a;
        int iComputeFloatSize = f12 != 0.0f ? 0 + CodedOutputStream.computeFloatSize(1, f12) : 0;
        float f13 = this.f78635b;
        if (f13 != 0.0f) {
            iComputeFloatSize += CodedOutputStream.computeFloatSize(2, f13);
        }
        float f14 = this.f78636c;
        if (f14 != 0.0f) {
            iComputeFloatSize += CodedOutputStream.computeFloatSize(3, f14);
        }
        if (this.f78637d != null) {
            iComputeFloatSize += CodedOutputStream.computeMessageSize(4, e());
        }
        if (this.f78638e != null) {
            iComputeFloatSize += CodedOutputStream.computeMessageSize(5, d());
        }
        if (this.f78639f != null) {
            iComputeFloatSize += CodedOutputStream.computeMessageSize(6, b());
        }
        if (this.f78640g != null) {
            iComputeFloatSize += CodedOutputStream.computeMessageSize(7, c());
        }
        if (this.f78641h != q0.kGenderUnknown.getNumber()) {
            iComputeFloatSize += CodedOutputStream.computeEnumSize(8, this.f78641h);
        }
        this.memoizedSerializedSize = iComputeFloatSize;
        return iComputeFloatSize;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        float f12 = this.f78634a;
        if (f12 != 0.0f) {
            codedOutputStream.writeFloat(1, f12);
        }
        float f13 = this.f78635b;
        if (f13 != 0.0f) {
            codedOutputStream.writeFloat(2, f13);
        }
        float f14 = this.f78636c;
        if (f14 != 0.0f) {
            codedOutputStream.writeFloat(3, f14);
        }
        if (this.f78637d != null) {
            codedOutputStream.writeMessage(4, e());
        }
        if (this.f78638e != null) {
            codedOutputStream.writeMessage(5, d());
        }
        if (this.f78639f != null) {
            codedOutputStream.writeMessage(6, b());
        }
        if (this.f78640g != null) {
            codedOutputStream.writeMessage(7, c());
        }
        if (this.f78641h != q0.kGenderUnknown.getNumber()) {
            codedOutputStream.writeEnum(8, this.f78641h);
        }
    }
}
