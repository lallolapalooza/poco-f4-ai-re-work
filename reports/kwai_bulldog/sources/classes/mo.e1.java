package mo;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.yxcorp.bugly.CrashReporter;
import java.io.IOException;

/* JADX INFO: compiled from: Rect.java */
/* JADX INFO: loaded from: classes15.dex */
public final class e1 extends GeneratedMessageLite<e1, b> implements MessageLiteOrBuilder {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final e1 f78610e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static volatile Parser<e1> f78611f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float f78612a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float f78613b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f78614c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f78615d;

    /* JADX INFO: compiled from: Rect.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f78616a;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f78616a = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e12) {
                CrashReporter.logCheckedException(e12, "Rect$1.class", "<clinit>", -1);
            }
            try {
                f78616a[GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError e13) {
                CrashReporter.logCheckedException(e13, "Rect$1.class", "<clinit>", -1);
            }
            try {
                f78616a[GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE.ordinal()] = 3;
            } catch (NoSuchFieldError e14) {
                CrashReporter.logCheckedException(e14, "Rect$1.class", "<clinit>", -1);
            }
            try {
                f78616a[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 4;
            } catch (NoSuchFieldError e15) {
                CrashReporter.logCheckedException(e15, "Rect$1.class", "<clinit>", -1);
            }
            try {
                f78616a[GeneratedMessageLite.MethodToInvoke.VISIT.ordinal()] = 5;
            } catch (NoSuchFieldError e16) {
                CrashReporter.logCheckedException(e16, "Rect$1.class", "<clinit>", -1);
            }
            try {
                f78616a[GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM.ordinal()] = 6;
            } catch (NoSuchFieldError e17) {
                CrashReporter.logCheckedException(e17, "Rect$1.class", "<clinit>", -1);
            }
            try {
                f78616a[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 7;
            } catch (NoSuchFieldError e18) {
                CrashReporter.logCheckedException(e18, "Rect$1.class", "<clinit>", -1);
            }
            try {
                f78616a[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 8;
            } catch (NoSuchFieldError e19) {
                CrashReporter.logCheckedException(e19, "Rect$1.class", "<clinit>", -1);
            }
        }
    }

    /* JADX INFO: compiled from: Rect.java */
    public static final class b extends GeneratedMessageLite.Builder<e1, b> implements MessageLiteOrBuilder {
        /* synthetic */ b(a aVar) {
            this();
        }

        private b() {
            super(e1.f78610e);
        }
    }

    static {
        e1 e1Var = new e1();
        f78610e = e1Var;
        e1Var.makeImmutable();
    }

    private e1() {
    }

    public static e1 b() {
        return f78610e;
    }

    public static Parser<e1> parser() {
        return f78610e.getParserForType();
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        a aVar = null;
        boolean z12 = false;
        switch (a.f78616a[methodToInvoke.ordinal()]) {
            case 1:
                return new e1();
            case 2:
                return f78610e;
            case 3:
                return null;
            case 4:
                return new b(aVar);
            case 5:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                e1 e1Var = (e1) obj2;
                float f12 = this.f78612a;
                boolean z13 = f12 != 0.0f;
                float f13 = e1Var.f78612a;
                this.f78612a = visitor.visitFloat(z13, f12, f13 != 0.0f, f13);
                float f14 = this.f78613b;
                boolean z14 = f14 != 0.0f;
                float f15 = e1Var.f78613b;
                this.f78613b = visitor.visitFloat(z14, f14, f15 != 0.0f, f15);
                float f16 = this.f78614c;
                boolean z15 = f16 != 0.0f;
                float f17 = e1Var.f78614c;
                this.f78614c = visitor.visitFloat(z15, f16, f17 != 0.0f, f17);
                float f18 = this.f78615d;
                boolean z16 = f18 != 0.0f;
                float f19 = e1Var.f78615d;
                this.f78615d = visitor.visitFloat(z16, f18, f19 != 0.0f, f19);
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
                                    this.f78612a = codedInputStream.readFloat();
                                } else if (tag == 21) {
                                    this.f78613b = codedInputStream.readFloat();
                                } else if (tag == 29) {
                                    this.f78614c = codedInputStream.readFloat();
                                } else if (tag == 37) {
                                    this.f78615d = codedInputStream.readFloat();
                                } else if (!codedInputStream.skipField(tag)) {
                                }
                            }
                            z12 = true;
                        } catch (Throwable th2) {
                            CrashReporter.logCheckedException(th2, "Rect.class", "dynamicMethod", -94);
                            throw th2;
                        }
                    } catch (InvalidProtocolBufferException e12) {
                        CrashReporter.logCheckedException(e12, "Rect.class", "dynamicMethod", -100);
                        throw new RuntimeException(e12.setUnfinishedMessage(this));
                    } catch (IOException e13) {
                        CrashReporter.logCheckedException(e13, "Rect.class", "dynamicMethod", -98);
                        throw new RuntimeException(new InvalidProtocolBufferException(e13.getMessage()).setUnfinishedMessage(this));
                    }
                }
                break;
            case 7:
                break;
            case 8:
                if (f78611f == null) {
                    synchronized (e1.class) {
                        try {
                            if (f78611f == null) {
                                f78611f = new GeneratedMessageLite.DefaultInstanceBasedParser(f78610e);
                            }
                        } catch (Throwable th3) {
                            CrashReporter.logCheckedException(th3, "Rect.class", "dynamicMethod", -1);
                            throw th3;
                        }
                        break;
                    }
                }
                return f78611f;
            default:
                throw new UnsupportedOperationException();
        }
        return f78610e;
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i12 = this.memoizedSerializedSize;
        if (i12 != -1) {
            return i12;
        }
        float f12 = this.f78612a;
        int iComputeFloatSize = f12 != 0.0f ? 0 + CodedOutputStream.computeFloatSize(1, f12) : 0;
        float f13 = this.f78613b;
        if (f13 != 0.0f) {
            iComputeFloatSize += CodedOutputStream.computeFloatSize(2, f13);
        }
        float f14 = this.f78614c;
        if (f14 != 0.0f) {
            iComputeFloatSize += CodedOutputStream.computeFloatSize(3, f14);
        }
        float f15 = this.f78615d;
        if (f15 != 0.0f) {
            iComputeFloatSize += CodedOutputStream.computeFloatSize(4, f15);
        }
        this.memoizedSerializedSize = iComputeFloatSize;
        return iComputeFloatSize;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        float f12 = this.f78612a;
        if (f12 != 0.0f) {
            codedOutputStream.writeFloat(1, f12);
        }
        float f13 = this.f78613b;
        if (f13 != 0.0f) {
            codedOutputStream.writeFloat(2, f13);
        }
        float f14 = this.f78614c;
        if (f14 != 0.0f) {
            codedOutputStream.writeFloat(3, f14);
        }
        float f15 = this.f78615d;
        if (f15 != 0.0f) {
            codedOutputStream.writeFloat(4, f15);
        }
    }
}
