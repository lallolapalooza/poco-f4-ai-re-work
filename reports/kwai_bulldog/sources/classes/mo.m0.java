package mo;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.yxcorp.bugly.CrashReporter;
import java.io.IOException;

/* JADX INFO: compiled from: FaceLandmark.java */
/* JADX INFO: loaded from: classes15.dex */
public final class m0 extends GeneratedMessageLite<m0, b> implements MessageLiteOrBuilder {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final m0 f78703h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static volatile Parser<m0> f78704i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f78705a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Internal.ProtobufList<x0> f78706b = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ByteString f78707c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ByteString f78708d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Internal.ProtobufList<y0> f78709e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ByteString f78710f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private ByteString f78711g;

    /* JADX INFO: compiled from: FaceLandmark.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f78712a;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f78712a = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e12) {
                CrashReporter.logCheckedException(e12, "FaceLandmark$1.class", "<clinit>", -1);
            }
            try {
                f78712a[GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError e13) {
                CrashReporter.logCheckedException(e13, "FaceLandmark$1.class", "<clinit>", -1);
            }
            try {
                f78712a[GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE.ordinal()] = 3;
            } catch (NoSuchFieldError e14) {
                CrashReporter.logCheckedException(e14, "FaceLandmark$1.class", "<clinit>", -1);
            }
            try {
                f78712a[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 4;
            } catch (NoSuchFieldError e15) {
                CrashReporter.logCheckedException(e15, "FaceLandmark$1.class", "<clinit>", -1);
            }
            try {
                f78712a[GeneratedMessageLite.MethodToInvoke.VISIT.ordinal()] = 5;
            } catch (NoSuchFieldError e16) {
                CrashReporter.logCheckedException(e16, "FaceLandmark$1.class", "<clinit>", -1);
            }
            try {
                f78712a[GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM.ordinal()] = 6;
            } catch (NoSuchFieldError e17) {
                CrashReporter.logCheckedException(e17, "FaceLandmark$1.class", "<clinit>", -1);
            }
            try {
                f78712a[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 7;
            } catch (NoSuchFieldError e18) {
                CrashReporter.logCheckedException(e18, "FaceLandmark$1.class", "<clinit>", -1);
            }
            try {
                f78712a[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 8;
            } catch (NoSuchFieldError e19) {
                CrashReporter.logCheckedException(e19, "FaceLandmark$1.class", "<clinit>", -1);
            }
        }
    }

    /* JADX INFO: compiled from: FaceLandmark.java */
    public static final class b extends GeneratedMessageLite.Builder<m0, b> implements MessageLiteOrBuilder {
        /* synthetic */ b(a aVar) {
            this();
        }

        private b() {
            super(m0.f78703h);
        }
    }

    static {
        m0 m0Var = new m0();
        f78703h = m0Var;
        m0Var.makeImmutable();
    }

    private m0() {
        ByteString byteString = ByteString.EMPTY;
        this.f78707c = byteString;
        this.f78708d = byteString;
        this.f78709e = GeneratedMessageLite.emptyProtobufList();
        this.f78710f = byteString;
        this.f78711g = byteString;
    }

    public static m0 b() {
        return f78703h;
    }

    public static Parser<m0> parser() {
        return f78703h.getParserForType();
    }

    public x0 c(int i12) {
        return this.f78706b.get(i12);
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        a aVar = null;
        boolean z12 = false;
        switch (a.f78712a[methodToInvoke.ordinal()]) {
            case 1:
                return new m0();
            case 2:
                return f78703h;
            case 3:
                this.f78706b.makeImmutable();
                this.f78709e.makeImmutable();
                return null;
            case 4:
                return new b(aVar);
            case 5:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                m0 m0Var = (m0) obj2;
                this.f78706b = visitor.visitList(this.f78706b, m0Var.f78706b);
                ByteString byteString = this.f78707c;
                ByteString byteString2 = ByteString.EMPTY;
                boolean z13 = byteString != byteString2;
                ByteString byteString3 = m0Var.f78707c;
                this.f78707c = visitor.visitByteString(z13, byteString, byteString3 != byteString2, byteString3);
                ByteString byteString4 = this.f78708d;
                boolean z14 = byteString4 != byteString2;
                ByteString byteString5 = m0Var.f78708d;
                this.f78708d = visitor.visitByteString(z14, byteString4, byteString5 != byteString2, byteString5);
                this.f78709e = visitor.visitList(this.f78709e, m0Var.f78709e);
                ByteString byteString6 = this.f78710f;
                boolean z15 = byteString6 != byteString2;
                ByteString byteString7 = m0Var.f78710f;
                this.f78710f = visitor.visitByteString(z15, byteString6, byteString7 != byteString2, byteString7);
                ByteString byteString8 = this.f78711g;
                boolean z16 = byteString8 != byteString2;
                ByteString byteString9 = m0Var.f78711g;
                this.f78711g = visitor.visitByteString(z16, byteString8, byteString9 != byteString2, byteString9);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.f78705a |= m0Var.f78705a;
                }
                return this;
            case 6:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z12) {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 10) {
                                    if (!this.f78706b.isModifiable()) {
                                        this.f78706b = GeneratedMessageLite.mutableCopy(this.f78706b);
                                    }
                                    this.f78706b.add((x0) codedInputStream.readMessage(x0.parser(), extensionRegistryLite));
                                } else if (tag == 18) {
                                    this.f78707c = codedInputStream.readBytes();
                                } else if (tag == 26) {
                                    this.f78708d = codedInputStream.readBytes();
                                } else if (tag == 34) {
                                    if (!this.f78709e.isModifiable()) {
                                        this.f78709e = GeneratedMessageLite.mutableCopy(this.f78709e);
                                    }
                                    this.f78709e.add((y0) codedInputStream.readMessage(y0.parser(), extensionRegistryLite));
                                } else if (tag == 42) {
                                    this.f78710f = codedInputStream.readBytes();
                                } else if (tag == 50) {
                                    this.f78711g = codedInputStream.readBytes();
                                } else if (!codedInputStream.skipField(tag)) {
                                }
                            }
                            z12 = true;
                        } catch (InvalidProtocolBufferException e12) {
                            CrashReporter.logCheckedException(e12, "FaceLandmark.class", "dynamicMethod", -122);
                            throw new RuntimeException(e12.setUnfinishedMessage(this));
                        } catch (IOException e13) {
                            CrashReporter.logCheckedException(e13, "FaceLandmark.class", "dynamicMethod", -120);
                            throw new RuntimeException(new InvalidProtocolBufferException(e13.getMessage()).setUnfinishedMessage(this));
                        }
                    } catch (Throwable th2) {
                        CrashReporter.logCheckedException(th2, "FaceLandmark.class", "dynamicMethod", -116);
                        throw th2;
                    }
                }
                break;
            case 7:
                break;
            case 8:
                if (f78704i == null) {
                    synchronized (m0.class) {
                        try {
                            if (f78704i == null) {
                                f78704i = new GeneratedMessageLite.DefaultInstanceBasedParser(f78703h);
                            }
                        } catch (Throwable th3) {
                            CrashReporter.logCheckedException(th3, "FaceLandmark.class", "dynamicMethod", -1);
                            throw th3;
                        }
                        break;
                    }
                }
                return f78704i;
            default:
                throw new UnsupportedOperationException();
        }
        return f78703h;
    }

    public int getPointsCount() {
        return this.f78706b.size();
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i12 = this.memoizedSerializedSize;
        if (i12 != -1) {
            return i12;
        }
        int iComputeBytesSize = 0;
        for (int i13 = 0; i13 < this.f78706b.size(); i13++) {
            iComputeBytesSize += CodedOutputStream.computeMessageSize(1, this.f78706b.get(i13));
        }
        if (!this.f78707c.isEmpty()) {
            iComputeBytesSize += CodedOutputStream.computeBytesSize(2, this.f78707c);
        }
        if (!this.f78708d.isEmpty()) {
            iComputeBytesSize += CodedOutputStream.computeBytesSize(3, this.f78708d);
        }
        for (int i14 = 0; i14 < this.f78709e.size(); i14++) {
            iComputeBytesSize += CodedOutputStream.computeMessageSize(4, this.f78709e.get(i14));
        }
        if (!this.f78710f.isEmpty()) {
            iComputeBytesSize += CodedOutputStream.computeBytesSize(5, this.f78710f);
        }
        if (!this.f78711g.isEmpty()) {
            iComputeBytesSize += CodedOutputStream.computeBytesSize(6, this.f78711g);
        }
        this.memoizedSerializedSize = iComputeBytesSize;
        return iComputeBytesSize;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i12 = 0; i12 < this.f78706b.size(); i12++) {
            codedOutputStream.writeMessage(1, this.f78706b.get(i12));
        }
        if (!this.f78707c.isEmpty()) {
            codedOutputStream.writeBytes(2, this.f78707c);
        }
        if (!this.f78708d.isEmpty()) {
            codedOutputStream.writeBytes(3, this.f78708d);
        }
        for (int i13 = 0; i13 < this.f78709e.size(); i13++) {
            codedOutputStream.writeMessage(4, this.f78709e.get(i13));
        }
        if (!this.f78710f.isEmpty()) {
            codedOutputStream.writeBytes(5, this.f78710f);
        }
        if (this.f78711g.isEmpty()) {
            return;
        }
        codedOutputStream.writeBytes(6, this.f78711g);
    }
}
