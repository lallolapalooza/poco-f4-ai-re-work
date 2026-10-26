package mo;

import aegon.chrome.net.NetError;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.yxcorp.bugly.CrashReporter;
import java.io.IOException;
import java.util.Objects;

/* JADX INFO: compiled from: RecordingStats.java */
/* JADX INFO: loaded from: classes15.dex */
public final class d1 extends GeneratedMessageLite<d1, b> implements MessageLiteOrBuilder {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final d1 f78596l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private static volatile Parser<d1> f78597m;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f78598a = "";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float f78599b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f78600c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f78601d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f78602e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float f78603f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float f78604g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private long f78605h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private long f78606i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private long f78607j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private long f78608k;

    /* JADX INFO: compiled from: RecordingStats.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f78609a;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f78609a = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e12) {
                CrashReporter.logCheckedException(e12, "RecordingStats$1.class", "<clinit>", -1);
            }
            try {
                f78609a[GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError e13) {
                CrashReporter.logCheckedException(e13, "RecordingStats$1.class", "<clinit>", -1);
            }
            try {
                f78609a[GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE.ordinal()] = 3;
            } catch (NoSuchFieldError e14) {
                CrashReporter.logCheckedException(e14, "RecordingStats$1.class", "<clinit>", -1);
            }
            try {
                f78609a[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 4;
            } catch (NoSuchFieldError e15) {
                CrashReporter.logCheckedException(e15, "RecordingStats$1.class", "<clinit>", -1);
            }
            try {
                f78609a[GeneratedMessageLite.MethodToInvoke.VISIT.ordinal()] = 5;
            } catch (NoSuchFieldError e16) {
                CrashReporter.logCheckedException(e16, "RecordingStats$1.class", "<clinit>", -1);
            }
            try {
                f78609a[GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM.ordinal()] = 6;
            } catch (NoSuchFieldError e17) {
                CrashReporter.logCheckedException(e17, "RecordingStats$1.class", "<clinit>", -1);
            }
            try {
                f78609a[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 7;
            } catch (NoSuchFieldError e18) {
                CrashReporter.logCheckedException(e18, "RecordingStats$1.class", "<clinit>", -1);
            }
            try {
                f78609a[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 8;
            } catch (NoSuchFieldError e19) {
                CrashReporter.logCheckedException(e19, "RecordingStats$1.class", "<clinit>", -1);
            }
        }
    }

    /* JADX INFO: compiled from: RecordingStats.java */
    public static final class b extends GeneratedMessageLite.Builder<d1, b> implements MessageLiteOrBuilder {
        /* synthetic */ b(a aVar) {
            this();
        }

        public b a(String str) {
            copyOnWrite();
            ((d1) this.instance).setPath(str);
            return this;
        }

        private b() {
            super(d1.f78596l);
        }
    }

    static {
        d1 d1Var = new d1();
        f78596l = d1Var;
        d1Var.makeImmutable();
    }

    private d1() {
    }

    public static b h() {
        return f78596l.toBuilder();
    }

    public static d1 i(byte[] bArr) throws InvalidProtocolBufferException {
        return (d1) GeneratedMessageLite.parseFrom(f78596l, bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPath(String str) {
        Objects.requireNonNull(str);
        this.f78598a = str;
    }

    public float c() {
        return this.f78600c;
    }

    public float d() {
        return this.f78599b;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        a aVar = null;
        boolean z12 = false;
        switch (a.f78609a[methodToInvoke.ordinal()]) {
            case 1:
                return new d1();
            case 2:
                return f78596l;
            case 3:
                return null;
            case 4:
                return new b(aVar);
            case 5:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                d1 d1Var = (d1) obj2;
                this.f78598a = visitor.visitString(!this.f78598a.isEmpty(), this.f78598a, !d1Var.f78598a.isEmpty(), d1Var.f78598a);
                float f12 = this.f78599b;
                boolean z13 = f12 != 0.0f;
                float f13 = d1Var.f78599b;
                this.f78599b = visitor.visitFloat(z13, f12, f13 != 0.0f, f13);
                float f14 = this.f78600c;
                boolean z14 = f14 != 0.0f;
                float f15 = d1Var.f78600c;
                this.f78600c = visitor.visitFloat(z14, f14, f15 != 0.0f, f15);
                int i12 = this.f78601d;
                boolean z15 = i12 != 0;
                int i13 = d1Var.f78601d;
                this.f78601d = visitor.visitInt(z15, i12, i13 != 0, i13);
                int i14 = this.f78602e;
                boolean z16 = i14 != 0;
                int i15 = d1Var.f78602e;
                this.f78602e = visitor.visitInt(z16, i14, i15 != 0, i15);
                float f16 = this.f78603f;
                boolean z17 = f16 != 0.0f;
                float f17 = d1Var.f78603f;
                this.f78603f = visitor.visitFloat(z17, f16, f17 != 0.0f, f17);
                float f18 = this.f78604g;
                boolean z18 = f18 != 0.0f;
                float f19 = d1Var.f78604g;
                this.f78604g = visitor.visitFloat(z18, f18, f19 != 0.0f, f19);
                long j12 = this.f78605h;
                boolean z19 = j12 != 0;
                long j13 = d1Var.f78605h;
                this.f78605h = visitor.visitLong(z19, j12, j13 != 0, j13);
                long j14 = this.f78606i;
                boolean z22 = j14 != 0;
                long j15 = d1Var.f78606i;
                this.f78606i = visitor.visitLong(z22, j14, j15 != 0, j15);
                long j16 = this.f78607j;
                boolean z23 = j16 != 0;
                long j17 = d1Var.f78607j;
                this.f78607j = visitor.visitLong(z23, j16, j17 != 0, j17);
                long j18 = this.f78608k;
                boolean z24 = j18 != 0;
                long j19 = d1Var.f78608k;
                this.f78608k = visitor.visitLong(z24, j18, j19 != 0, j19);
                GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                return this;
            case 6:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                while (!z12) {
                    try {
                        try {
                            try {
                                int tag = codedInputStream.readTag();
                                switch (tag) {
                                    case 0:
                                        break;
                                    case 10:
                                        this.f78598a = codedInputStream.readStringRequireUtf8();
                                        continue;
                                    case 21:
                                        this.f78599b = codedInputStream.readFloat();
                                        continue;
                                    case 37:
                                        this.f78600c = codedInputStream.readFloat();
                                        continue;
                                    case 40:
                                        this.f78601d = codedInputStream.readInt32();
                                        continue;
                                    case 48:
                                        this.f78602e = codedInputStream.readInt32();
                                        continue;
                                    case 61:
                                        this.f78603f = codedInputStream.readFloat();
                                        continue;
                                    case 69:
                                        this.f78604g = codedInputStream.readFloat();
                                        continue;
                                    case 72:
                                        this.f78605h = codedInputStream.readInt64();
                                        continue;
                                    case 80:
                                        this.f78606i = codedInputStream.readInt64();
                                        continue;
                                    case 88:
                                        this.f78607j = codedInputStream.readUInt64();
                                        continue;
                                    case 96:
                                        this.f78608k = codedInputStream.readUInt64();
                                        continue;
                                    default:
                                        if (!codedInputStream.skipField(tag)) {
                                            break;
                                        }
                                        break;
                                }
                                z12 = true;
                            } catch (IOException e12) {
                                CrashReporter.logCheckedException(e12, "RecordingStats.class", "dynamicMethod", -124);
                                throw new RuntimeException(new InvalidProtocolBufferException(e12.getMessage()).setUnfinishedMessage(this));
                            }
                        } catch (InvalidProtocolBufferException e13) {
                            CrashReporter.logCheckedException(e13, "RecordingStats.class", "dynamicMethod", NetError.ERR_SSL_BAD_RECORD_MAC_ALERT);
                            throw new RuntimeException(e13.setUnfinishedMessage(this));
                        }
                    } catch (Throwable th2) {
                        CrashReporter.logCheckedException(th2, "RecordingStats.class", "dynamicMethod", -120);
                        throw th2;
                    }
                }
                break;
            case 7:
                break;
            case 8:
                if (f78597m == null) {
                    synchronized (d1.class) {
                        try {
                            if (f78597m == null) {
                                f78597m = new GeneratedMessageLite.DefaultInstanceBasedParser(f78596l);
                            }
                        } catch (Throwable th3) {
                            CrashReporter.logCheckedException(th3, "RecordingStats.class", "dynamicMethod", -1);
                            throw th3;
                        }
                        break;
                    }
                }
                return f78597m;
            default:
                throw new UnsupportedOperationException();
        }
        return f78596l;
    }

    public long e() {
        return this.f78605h;
    }

    public float f() {
        return this.f78604g;
    }

    public float g() {
        return this.f78603f;
    }

    public int getHeight() {
        return this.f78602e;
    }

    public String getPath() {
        return this.f78598a;
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i12 = this.memoizedSerializedSize;
        if (i12 != -1) {
            return i12;
        }
        int iComputeStringSize = this.f78598a.isEmpty() ? 0 : 0 + CodedOutputStream.computeStringSize(1, getPath());
        float f12 = this.f78599b;
        if (f12 != 0.0f) {
            iComputeStringSize += CodedOutputStream.computeFloatSize(2, f12);
        }
        float f13 = this.f78600c;
        if (f13 != 0.0f) {
            iComputeStringSize += CodedOutputStream.computeFloatSize(4, f13);
        }
        int i13 = this.f78601d;
        if (i13 != 0) {
            iComputeStringSize += CodedOutputStream.computeInt32Size(5, i13);
        }
        int i14 = this.f78602e;
        if (i14 != 0) {
            iComputeStringSize += CodedOutputStream.computeInt32Size(6, i14);
        }
        float f14 = this.f78603f;
        if (f14 != 0.0f) {
            iComputeStringSize += CodedOutputStream.computeFloatSize(7, f14);
        }
        float f15 = this.f78604g;
        if (f15 != 0.0f) {
            iComputeStringSize += CodedOutputStream.computeFloatSize(8, f15);
        }
        long j12 = this.f78605h;
        if (j12 != 0) {
            iComputeStringSize += CodedOutputStream.computeInt64Size(9, j12);
        }
        long j13 = this.f78606i;
        if (j13 != 0) {
            iComputeStringSize += CodedOutputStream.computeInt64Size(10, j13);
        }
        long j14 = this.f78607j;
        if (j14 != 0) {
            iComputeStringSize += CodedOutputStream.computeUInt64Size(11, j14);
        }
        long j15 = this.f78608k;
        if (j15 != 0) {
            iComputeStringSize += CodedOutputStream.computeUInt64Size(12, j15);
        }
        this.memoizedSerializedSize = iComputeStringSize;
        return iComputeStringSize;
    }

    public int getWidth() {
        return this.f78601d;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.f78598a.isEmpty()) {
            codedOutputStream.writeString(1, getPath());
        }
        float f12 = this.f78599b;
        if (f12 != 0.0f) {
            codedOutputStream.writeFloat(2, f12);
        }
        float f13 = this.f78600c;
        if (f13 != 0.0f) {
            codedOutputStream.writeFloat(4, f13);
        }
        int i12 = this.f78601d;
        if (i12 != 0) {
            codedOutputStream.writeInt32(5, i12);
        }
        int i13 = this.f78602e;
        if (i13 != 0) {
            codedOutputStream.writeInt32(6, i13);
        }
        float f14 = this.f78603f;
        if (f14 != 0.0f) {
            codedOutputStream.writeFloat(7, f14);
        }
        float f15 = this.f78604g;
        if (f15 != 0.0f) {
            codedOutputStream.writeFloat(8, f15);
        }
        long j12 = this.f78605h;
        if (j12 != 0) {
            codedOutputStream.writeInt64(9, j12);
        }
        long j13 = this.f78606i;
        if (j13 != 0) {
            codedOutputStream.writeInt64(10, j13);
        }
        long j14 = this.f78607j;
        if (j14 != 0) {
            codedOutputStream.writeUInt64(11, j14);
        }
        long j15 = this.f78608k;
        if (j15 != 0) {
            codedOutputStream.writeUInt64(12, j15);
        }
    }
}
