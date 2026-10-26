package mo;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.yxcorp.bugly.CrashReporter;
import java.io.IOException;

/* JADX INFO: compiled from: FacePose.java */
/* JADX INFO: loaded from: classes15.dex */
public final class n0 extends GeneratedMessageLite<n0, b> implements MessageLiteOrBuilder {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final n0 f78719d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static volatile Parser<n0> f78720e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float f78721a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float f78722b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f78723c;

    /* JADX INFO: compiled from: FacePose.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f78724a;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f78724a = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e12) {
                CrashReporter.logCheckedException(e12, "FacePose$1.class", "<clinit>", -1);
            }
            try {
                f78724a[GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError e13) {
                CrashReporter.logCheckedException(e13, "FacePose$1.class", "<clinit>", -1);
            }
            try {
                f78724a[GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE.ordinal()] = 3;
            } catch (NoSuchFieldError e14) {
                CrashReporter.logCheckedException(e14, "FacePose$1.class", "<clinit>", -1);
            }
            try {
                f78724a[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 4;
            } catch (NoSuchFieldError e15) {
                CrashReporter.logCheckedException(e15, "FacePose$1.class", "<clinit>", -1);
            }
            try {
                f78724a[GeneratedMessageLite.MethodToInvoke.VISIT.ordinal()] = 5;
            } catch (NoSuchFieldError e16) {
                CrashReporter.logCheckedException(e16, "FacePose$1.class", "<clinit>", -1);
            }
            try {
                f78724a[GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM.ordinal()] = 6;
            } catch (NoSuchFieldError e17) {
                CrashReporter.logCheckedException(e17, "FacePose$1.class", "<clinit>", -1);
            }
            try {
                f78724a[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 7;
            } catch (NoSuchFieldError e18) {
                CrashReporter.logCheckedException(e18, "FacePose$1.class", "<clinit>", -1);
            }
            try {
                f78724a[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 8;
            } catch (NoSuchFieldError e19) {
                CrashReporter.logCheckedException(e19, "FacePose$1.class", "<clinit>", -1);
            }
        }
    }

    /* JADX INFO: compiled from: FacePose.java */
    public static final class b extends GeneratedMessageLite.Builder<n0, b> implements MessageLiteOrBuilder {
        /* synthetic */ b(a aVar) {
            this();
        }

        private b() {
            super(n0.f78719d);
        }
    }

    static {
        n0 n0Var = new n0();
        f78719d = n0Var;
        n0Var.makeImmutable();
    }

    private n0() {
    }

    public static n0 b() {
        return f78719d;
    }

    public static Parser<n0> parser() {
        return f78719d.getParserForType();
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        a aVar = null;
        boolean z12 = false;
        switch (a.f78724a[methodToInvoke.ordinal()]) {
            case 1:
                return new n0();
            case 2:
                return f78719d;
            case 3:
                return null;
            case 4:
                return new b(aVar);
            case 5:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                n0 n0Var = (n0) obj2;
                float f12 = this.f78721a;
                boolean z13 = f12 != 0.0f;
                float f13 = n0Var.f78721a;
                this.f78721a = visitor.visitFloat(z13, f12, f13 != 0.0f, f13);
                float f14 = this.f78722b;
                boolean z14 = f14 != 0.0f;
                float f15 = n0Var.f78722b;
                this.f78722b = visitor.visitFloat(z14, f14, f15 != 0.0f, f15);
                float f16 = this.f78723c;
                boolean z15 = f16 != 0.0f;
                float f17 = n0Var.f78723c;
                this.f78723c = visitor.visitFloat(z15, f16, f17 != 0.0f, f17);
                GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                return this;
            case 6:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                while (!z12) {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 13) {
                                    this.f78721a = codedInputStream.readFloat();
                                } else if (tag == 21) {
                                    this.f78722b = codedInputStream.readFloat();
                                } else if (tag == 29) {
                                    this.f78723c = codedInputStream.readFloat();
                                } else if (!codedInputStream.skipField(tag)) {
                                }
                            }
                            z12 = true;
                        } catch (InvalidProtocolBufferException e12) {
                            CrashReporter.logCheckedException(e12, "FacePose.class", "dynamicMethod", 96);
                            throw new RuntimeException(e12.setUnfinishedMessage(this));
                        } catch (IOException e13) {
                            CrashReporter.logCheckedException(e13, "FacePose.class", "dynamicMethod", 98);
                            throw new RuntimeException(new InvalidProtocolBufferException(e13.getMessage()).setUnfinishedMessage(this));
                        }
                    } catch (Throwable th2) {
                        CrashReporter.logCheckedException(th2, "FacePose.class", "dynamicMethod", 102);
                        throw th2;
                    }
                }
                break;
            case 7:
                break;
            case 8:
                if (f78720e == null) {
                    synchronized (n0.class) {
                        try {
                            if (f78720e == null) {
                                f78720e = new GeneratedMessageLite.DefaultInstanceBasedParser(f78719d);
                            }
                        } catch (Throwable th3) {
                            CrashReporter.logCheckedException(th3, "FacePose.class", "dynamicMethod", -1);
                            throw th3;
                        }
                        break;
                    }
                }
                return f78720e;
            default:
                throw new UnsupportedOperationException();
        }
        return f78719d;
    }

    public float getPitch() {
        return this.f78721a;
    }

    public float getRoll() {
        return this.f78723c;
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i12 = this.memoizedSerializedSize;
        if (i12 != -1) {
            return i12;
        }
        float f12 = this.f78721a;
        int iComputeFloatSize = f12 != 0.0f ? 0 + CodedOutputStream.computeFloatSize(1, f12) : 0;
        float f13 = this.f78722b;
        if (f13 != 0.0f) {
            iComputeFloatSize += CodedOutputStream.computeFloatSize(2, f13);
        }
        float f14 = this.f78723c;
        if (f14 != 0.0f) {
            iComputeFloatSize += CodedOutputStream.computeFloatSize(3, f14);
        }
        this.memoizedSerializedSize = iComputeFloatSize;
        return iComputeFloatSize;
    }

    public float getYaw() {
        return this.f78722b;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        float f12 = this.f78721a;
        if (f12 != 0.0f) {
            codedOutputStream.writeFloat(1, f12);
        }
        float f13 = this.f78722b;
        if (f13 != 0.0f) {
            codedOutputStream.writeFloat(2, f13);
        }
        float f14 = this.f78723c;
        if (f14 != 0.0f) {
            codedOutputStream.writeFloat(3, f14);
        }
    }
}
