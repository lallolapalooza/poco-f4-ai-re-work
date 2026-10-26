package org.tensorflow.lite;

import androidx.activity.e;
import gb.c;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
final class TensorImpl {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f6653a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final lb.a f6654b;
    public int[] c;

    public TensorImpl(long j3) {
        lb.a aVar;
        this.f6653a = j3;
        int iDtype = dtype(j3);
        switch (iDtype) {
            case 1:
                aVar = lb.a.FLOAT32;
                break;
            case 2:
                aVar = lb.a.INT32;
                break;
            case 3:
                aVar = lb.a.UINT8;
                break;
            case 4:
                aVar = lb.a.INT64;
                break;
            case 5:
                aVar = lb.a.STRING;
                break;
            case 6:
                aVar = lb.a.BOOL;
                break;
            case 7:
                aVar = lb.a.INT16;
                break;
            case 8:
            default:
                throw new IllegalArgumentException("DataType error: DataType " + iDtype + " is not recognized in Java.");
            case 9:
                aVar = lb.a.INT8;
                break;
        }
        this.f6654b = aVar;
        this.c = shape(j3);
        shapeSignature(j3);
        quantizationScale(j3);
        quantizationZeroPoint(j3);
    }

    private static native ByteBuffer buffer(long j3);

    public static int c(Object obj) {
        if (obj == null || !obj.getClass().isArray()) {
            return 0;
        }
        if (Array.getLength(obj) != 0) {
            return c(Array.get(obj, 0)) + 1;
        }
        throw new IllegalArgumentException("Array lengths cannot be 0.");
    }

    private static native long create(long j3, int i10, int i11);

    private static native long createSignatureInputTensor(long j3, String str);

    private static native long createSignatureOutputTensor(long j3, String str);

    private static native void delete(long j3);

    private static native int dtype(long j3);

    public static void f(Object obj, int i10, int[] iArr) {
        if (i10 == iArr.length) {
            return;
        }
        int length = Array.getLength(obj);
        if (iArr[i10] == 0) {
            iArr[i10] = length;
        } else if (iArr[i10] != length) {
            throw new IllegalArgumentException(String.format("Mismatched lengths (%d and %d) in dimension %d", Integer.valueOf(iArr[i10]), Integer.valueOf(length), Integer.valueOf(i10)));
        }
        int i11 = i10 + 1;
        if (i11 == iArr.length) {
            return;
        }
        for (int i12 = 0; i12 < length; i12++) {
            f(Array.get(obj, i12), i11, iArr);
        }
    }

    public static TensorImpl g(long j3, int i10) {
        return new TensorImpl(create(j3, i10, 0));
    }

    public static TensorImpl h(long j3, String str) {
        return new TensorImpl(createSignatureInputTensor(j3, str));
    }

    private static native boolean hasDelegateBufferHandle(long j3);

    public static TensorImpl i(long j3, String str) {
        return new TensorImpl(createSignatureOutputTensor(j3, str));
    }

    private static native String name(long j3);

    private static native int numBytes(long j3);

    private static native float quantizationScale(long j3);

    private static native int quantizationZeroPoint(long j3);

    private static native void readMultiDimensionalArray(long j3, Object obj);

    private static native int[] shape(long j3);

    private static native int[] shapeSignature(long j3);

    private static native void writeDirectBuffer(long j3, Buffer buffer);

    private static native void writeMultiDimensionalArray(long j3, Object obj);

    private static native void writeScalar(long j3, Object obj);

    public final ByteBuffer a() {
        return buffer(this.f6653a).order(ByteOrder.nativeOrder());
    }

    public final void b() {
        delete(this.f6653a);
        this.f6653a = 0L;
    }

    public final int[] d(Object obj) {
        int iC = c(obj);
        if (this.f6654b == lb.a.STRING) {
            Class<?> componentType = obj.getClass();
            if (componentType.isArray()) {
                while (componentType.isArray()) {
                    componentType = componentType.getComponentType();
                }
                if (Byte.TYPE.equals(componentType)) {
                    iC--;
                }
            }
        }
        int[] iArr = new int[iC];
        f(obj, 0, iArr);
        return iArr;
    }

    public final Object e(Object obj) {
        if (obj == null) {
            if (hasDelegateBufferHandle(this.f6653a)) {
                return obj;
            }
            throw new IllegalArgumentException("Null outputs are allowed only if the Tensor is bound to a buffer handle.");
        }
        n(obj);
        boolean z10 = obj instanceof Buffer;
        if (z10) {
            Buffer buffer = (Buffer) obj;
            int iNumBytes = numBytes(this.f6653a);
            int iCapacity = obj instanceof ByteBuffer ? buffer.capacity() : buffer.capacity() * this.f6654b.a();
            if (iNumBytes > iCapacity) {
                throw new IllegalArgumentException(String.format("Cannot copy from a TensorFlowLite tensor (%s) with %d bytes to a Java Buffer with %d bytes.", k(), Integer.valueOf(iNumBytes), Integer.valueOf(iCapacity)));
            }
        } else {
            int[] iArrD = d(obj);
            if (!Arrays.equals(iArrD, this.c)) {
                throw new IllegalArgumentException(String.format("Cannot copy from a TensorFlowLite tensor (%s) with shape %s to a Java object with shape %s.", k(), Arrays.toString(this.c), Arrays.toString(iArrD)));
            }
        }
        if (z10) {
            Buffer buffer2 = (Buffer) obj;
            if (buffer2 instanceof ByteBuffer) {
                ((ByteBuffer) buffer2).put(a());
            } else if (buffer2 instanceof FloatBuffer) {
                ((FloatBuffer) buffer2).put(a().asFloatBuffer());
            } else if (buffer2 instanceof LongBuffer) {
                ((LongBuffer) buffer2).put(a().asLongBuffer());
            } else if (buffer2 instanceof IntBuffer) {
                ((IntBuffer) buffer2).put(a().asIntBuffer());
            } else {
                if (!(buffer2 instanceof ShortBuffer)) {
                    throw new IllegalArgumentException("Unexpected output buffer type: " + buffer2);
                }
                ((ShortBuffer) buffer2).put(a().asShortBuffer());
            }
        } else {
            readMultiDimensionalArray(this.f6653a, obj);
        }
        return obj;
    }

    public final int[] j(Object obj) {
        if (obj == null || (obj instanceof Buffer)) {
            return null;
        }
        n(obj);
        int[] iArrD = d(obj);
        if (Arrays.equals(this.c, iArrD)) {
            return null;
        }
        return iArrD;
    }

    public final String k() {
        return name(this.f6653a);
    }

    public final void l() {
        this.c = shape(this.f6653a);
    }

    public final void m(Object obj) {
        if (obj == null) {
            if (!hasDelegateBufferHandle(this.f6653a)) {
                throw new IllegalArgumentException("Null inputs are allowed only if the Tensor is bound to a buffer handle.");
            }
            return;
        }
        n(obj);
        boolean z10 = obj instanceof Buffer;
        if (z10) {
            Buffer buffer = (Buffer) obj;
            int iNumBytes = numBytes(this.f6653a);
            int iCapacity = obj instanceof ByteBuffer ? buffer.capacity() : buffer.capacity() * this.f6654b.a();
            if (iNumBytes != iCapacity) {
                throw new IllegalArgumentException(String.format("Cannot copy to a TensorFlowLite tensor (%s) with %d bytes from a Java Buffer with %d bytes.", k(), Integer.valueOf(iNumBytes), Integer.valueOf(iCapacity)));
            }
        } else {
            int[] iArrD = d(obj);
            if (!Arrays.equals(iArrD, this.c)) {
                throw new IllegalArgumentException(String.format("Cannot copy to a TensorFlowLite tensor (%s) with shape %s from a Java object with shape %s.", k(), Arrays.toString(this.c), Arrays.toString(iArrD)));
            }
        }
        if (!z10) {
            if (!(this.f6654b == lb.a.STRING && this.c.length == 0) && obj.getClass().isArray()) {
                writeMultiDimensionalArray(this.f6653a, obj);
                return;
            } else {
                writeScalar(this.f6653a, obj);
                return;
            }
        }
        Buffer buffer2 = (Buffer) obj;
        if (buffer2 instanceof ByteBuffer) {
            ByteBuffer byteBuffer = (ByteBuffer) buffer2;
            if (!byteBuffer.isDirect() || byteBuffer.order() != ByteOrder.nativeOrder()) {
                a().put(byteBuffer);
                return;
            }
        } else if (buffer2 instanceof LongBuffer) {
            LongBuffer longBuffer = (LongBuffer) buffer2;
            if (!longBuffer.isDirect() || longBuffer.order() != ByteOrder.nativeOrder()) {
                a().asLongBuffer().put(longBuffer);
                return;
            }
        } else if (buffer2 instanceof FloatBuffer) {
            FloatBuffer floatBuffer = (FloatBuffer) buffer2;
            if (!floatBuffer.isDirect() || floatBuffer.order() != ByteOrder.nativeOrder()) {
                a().asFloatBuffer().put(floatBuffer);
                return;
            }
        } else if (buffer2 instanceof IntBuffer) {
            IntBuffer intBuffer = (IntBuffer) buffer2;
            if (!intBuffer.isDirect() || intBuffer.order() != ByteOrder.nativeOrder()) {
                a().asIntBuffer().put(intBuffer);
                return;
            }
        } else {
            if (!(buffer2 instanceof ShortBuffer)) {
                throw new IllegalArgumentException("Unexpected input buffer type: " + buffer2);
            }
            ShortBuffer shortBuffer = (ShortBuffer) buffer2;
            if (!shortBuffer.isDirect() || shortBuffer.order() != ByteOrder.nativeOrder()) {
                a().asShortBuffer().put(shortBuffer);
                return;
            }
        }
        writeDirectBuffer(this.f6653a, buffer2);
    }

    /* JADX WARN: Code duplicated, block: B:52:0x0095  */
    /* JADX WARN: Code duplicated, block: B:60:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:65:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:66:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:67:0x00c2  */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0063, code lost:
    
        if (java.lang.String.class.equals(r1) != false) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00b4, code lost:
    
        if (java.lang.String.class.equals(r1) != false) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00b6, code lost:
    
        r1 = lb.a.STRING;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void n(Object obj) {
        lb.a aVar;
        if (obj instanceof ByteBuffer) {
            return;
        }
        if (obj != null) {
            Class<?> componentType = obj.getClass();
            if (componentType.isArray()) {
                while (componentType.isArray()) {
                    componentType = componentType.getComponentType();
                }
                if (Float.TYPE.equals(componentType)) {
                    aVar = lb.a.FLOAT32;
                } else if (Integer.TYPE.equals(componentType)) {
                    aVar = lb.a.INT32;
                } else if (Short.TYPE.equals(componentType)) {
                    aVar = lb.a.INT16;
                } else if (Byte.TYPE.equals(componentType)) {
                    lb.a aVar2 = this.f6654b;
                    aVar = lb.a.STRING;
                    if (aVar2 != aVar) {
                        aVar = lb.a.UINT8;
                    }
                } else if (Long.TYPE.equals(componentType)) {
                    aVar = lb.a.INT64;
                } else if (Boolean.TYPE.equals(componentType)) {
                    aVar = lb.a.BOOL;
                }
            } else if (Float.class.equals(componentType) || (obj instanceof FloatBuffer)) {
                aVar = lb.a.FLOAT32;
            } else if (Integer.class.equals(componentType) || (obj instanceof IntBuffer)) {
                aVar = lb.a.INT32;
            } else if (Short.class.equals(componentType) || (obj instanceof ShortBuffer)) {
                aVar = lb.a.INT16;
            } else if (Byte.class.equals(componentType)) {
                aVar = lb.a.UINT8;
            } else if (Long.class.equals(componentType) || (obj instanceof LongBuffer)) {
                aVar = lb.a.INT64;
            } else if (Boolean.class.equals(componentType)) {
                aVar = lb.a.BOOL;
            }
            if (aVar != this.f6654b && !c.j(aVar).equals(c.j(this.f6654b))) {
                throw new IllegalArgumentException(String.format("Cannot convert between a TensorFlowLite tensor with type %s and a Java object of type %s (which is compatible with the TensorFlowLite type %s).", this.f6654b, obj.getClass().getName(), aVar));
            }
            return;
        }
        StringBuilder sbT = e.t("DataType error: cannot resolve DataType of ");
        sbT.append(obj.getClass().getName());
        throw new IllegalArgumentException(sbT.toString());
    }
}
