package org.tensorflow.lite;

import androidx.activity.e;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
class NativeInterpreterWrapper implements AutoCloseable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f6638b;
    public long c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f6639d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ByteBuffer f6641f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Map<Integer, Integer> f6642g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Map<Integer, Integer> f6643h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Map<String, NativeSignatureRunnerWrapper> f6644i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public TensorImpl[] f6645j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public TensorImpl[] f6646k;
    public boolean l;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f6640e = 0;
    private long inferenceDurationNanoseconds = -1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final List<lb.b> f6647m = new ArrayList();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final List<AutoCloseable> f6648n = new ArrayList();

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public NativeInterpreterWrapper(ByteBuffer byteBuffer) {
        lb.b bVar;
        this.l = false;
        TensorFlowLite.a();
        if (byteBuffer == null || !((byteBuffer instanceof MappedByteBuffer) || (byteBuffer.isDirect() && byteBuffer.order() == ByteOrder.nativeOrder()))) {
            throw new IllegalArgumentException("Model ByteBuffer should be either a MappedByteBuffer of the model file, or a direct ByteBuffer using ByteOrder.nativeOrder() which contains bytes of model content.");
        }
        this.f6641f = byteBuffer;
        long jCreateErrorReporter = createErrorReporter(512);
        long jCreateModelWithBuffer = createModelWithBuffer(this.f6641f, jCreateErrorReporter);
        ArrayList arrayList = new ArrayList();
        this.f6638b = jCreateErrorReporter;
        this.f6639d = jCreateModelWithBuffer;
        ArrayList arrayList2 = new ArrayList();
        long jCreateInterpreter = createInterpreter(jCreateModelWithBuffer, jCreateErrorReporter, -1, arrayList2);
        this.c = jCreateInterpreter;
        if (hasUnresolvedFlexOp(jCreateInterpreter)) {
            List listUnmodifiableList = Collections.unmodifiableList(arrayList);
            try {
                Class<?> cls = Class.forName("org.tensorflow.lite.flex.FlexDelegate");
                Iterator it = listUnmodifiableList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        bVar = (lb.b) cls.getConstructor(new Class[0]).newInstance(new Object[0]);
                        break;
                    } else if (cls.isInstance((lb.b) it.next())) {
                        bVar = null;
                        break;
                    }
                }
            } catch (Exception unused) {
            }
            if (bVar != null) {
                this.f6648n.add((AutoCloseable) bVar);
                this.f6647m.add(bVar);
            }
        }
        this.f6647m.addAll(Collections.unmodifiableList(arrayList));
        arrayList2.ensureCapacity(this.f6647m.size());
        Iterator<lb.b> it2 = this.f6647m.iterator();
        while (it2.hasNext()) {
            arrayList2.add(Long.valueOf(it2.next().getNativeHandle()));
        }
        if (!arrayList2.isEmpty()) {
            delete(0L, 0L, this.c);
            this.c = createInterpreter(jCreateModelWithBuffer, jCreateErrorReporter, -1, arrayList2);
        }
        this.f6645j = new TensorImpl[getInputCount(this.c)];
        this.f6646k = new TensorImpl[getOutputCount(this.c)];
        allocateTensors(this.c, jCreateErrorReporter);
        this.l = true;
    }

    private static native long allocateTensors(long j3, long j10);

    private static native long createErrorReporter(int i10);

    private static native long createInterpreter(long j3, long j10, int i10, List<Long> list);

    private static native long createModelWithBuffer(ByteBuffer byteBuffer, long j3);

    private static native void delete(long j3, long j10, long j11);

    private static native long deleteCancellationFlag(long j3);

    private static native int getInputCount(long j3);

    private static native int getInputTensorIndex(long j3, int i10);

    private static native int getOutputCount(long j3);

    private static native int getOutputTensorIndex(long j3, int i10);

    private static native String[] getSignatureKeys(long j3);

    private static native boolean hasUnresolvedFlexOp(long j3);

    private static native boolean resizeInput(long j3, long j10, int i10, int[] iArr, boolean z10);

    private static native void run(long j3, long j10);

    public final TensorImpl a(int i10) {
        if (i10 >= 0) {
            TensorImpl[] tensorImplArr = this.f6645j;
            if (i10 < tensorImplArr.length) {
                TensorImpl tensorImpl = tensorImplArr[i10];
                if (tensorImpl != null) {
                    return tensorImpl;
                }
                long j3 = this.c;
                TensorImpl tensorImplG = TensorImpl.g(j3, getInputTensorIndex(j3, i10));
                tensorImplArr[i10] = tensorImplG;
                return tensorImplG;
            }
        }
        throw new IllegalArgumentException(e.p("Invalid input Tensor index: ", i10));
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.ArrayList, java.util.List<lb.b>] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.ArrayList, java.util.List<java.lang.AutoCloseable>] */
    /* JADX WARN: Type inference failed for: r11v1, types: [java.util.ArrayList, java.util.List<java.lang.AutoCloseable>] */
    @Override // java.lang.AutoCloseable
    public final void close() {
        int i10 = 0;
        while (true) {
            TensorImpl[] tensorImplArr = this.f6645j;
            if (i10 >= tensorImplArr.length) {
                break;
            }
            if (tensorImplArr[i10] != null) {
                tensorImplArr[i10].b();
                this.f6645j[i10] = null;
            }
            i10++;
        }
        int i11 = 0;
        while (true) {
            TensorImpl[] tensorImplArr2 = this.f6646k;
            if (i11 >= tensorImplArr2.length) {
                break;
            }
            if (tensorImplArr2[i11] != null) {
                tensorImplArr2[i11].b();
                this.f6646k[i11] = null;
            }
            i11++;
        }
        delete(this.f6638b, this.f6639d, this.c);
        deleteCancellationFlag(this.f6640e);
        this.f6638b = 0L;
        this.f6639d = 0L;
        this.c = 0L;
        this.f6640e = 0L;
        this.f6641f = null;
        this.l = false;
        this.f6647m.clear();
        Iterator it = this.f6648n.iterator();
        while (it.hasNext()) {
            try {
                ((AutoCloseable) it.next()).close();
            } catch (Exception e10) {
                System.err.println("Failed to close flex delegate: " + e10);
            }
        }
        this.f6648n.clear();
    }

    public final String[] e() {
        return getSignatureKeys(this.c);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.HashMap, java.util.Map<java.lang.String, org.tensorflow.lite.NativeSignatureRunnerWrapper>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.HashMap, java.util.Map<java.lang.String, org.tensorflow.lite.NativeSignatureRunnerWrapper>] */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.util.HashMap, java.util.Map<java.lang.String, org.tensorflow.lite.NativeSignatureRunnerWrapper>] */
    public final NativeSignatureRunnerWrapper f(String str) {
        if (this.f6644i == null) {
            this.f6644i = new HashMap();
        }
        if (!this.f6644i.containsKey(str)) {
            this.f6644i.put(str, new NativeSignatureRunnerWrapper(this.c, this.f6638b, str));
        }
        return (NativeSignatureRunnerWrapper) this.f6644i.get(str);
    }

    /* JADX WARN: Type inference failed for: r9v10, types: [java.util.HashMap, java.util.Map<java.lang.Integer, java.lang.Integer>] */
    /* JADX WARN: Type inference failed for: r9v9, types: [java.util.HashMap, java.util.Map<java.lang.Integer, java.lang.Integer>] */
    public final void g(Map<String, Object> map, Map<String, Object> map2, String str) {
        this.inferenceDurationNanoseconds = -1L;
        if (map.isEmpty()) {
            throw new IllegalArgumentException("Input error: Inputs should not be null or empty.");
        }
        NativeSignatureRunnerWrapper nativeSignatureRunnerWrapperF = f(str);
        if (nativeSignatureRunnerWrapperF.d() != 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    throw new IllegalArgumentException("Invalid input tensor name provided (null)");
                }
                NativeSignatureRunnerWrapper nativeSignatureRunnerWrapperF2 = f(str);
                int[] iArrJ = (nativeSignatureRunnerWrapperF2.d() > 0 ? TensorImpl.h(nativeSignatureRunnerWrapperF2.f6649a, key) : a(nativeSignatureRunnerWrapperF2.b(key))).j(entry.getValue());
                if (iArrJ != null) {
                    nativeSignatureRunnerWrapperF.f(entry.getKey(), iArrJ);
                }
            }
            nativeSignatureRunnerWrapperF.a();
            for (Map.Entry<String, Object> entry2 : map.entrySet()) {
                TensorImpl.h(nativeSignatureRunnerWrapperF.f6649a, entry2.getKey()).m(entry2.getValue());
            }
            long jNanoTime = System.nanoTime();
            nativeSignatureRunnerWrapperF.e();
            long jNanoTime2 = System.nanoTime() - jNanoTime;
            for (Map.Entry<String, Object> entry3 : map2.entrySet()) {
                if (entry3.getValue() != null) {
                    TensorImpl.i(nativeSignatureRunnerWrapperF.f6649a, entry3.getKey()).e(entry3.getValue());
                }
            }
            this.inferenceDurationNanoseconds = jNanoTime2;
            return;
        }
        int i10 = 0;
        if (this.f6642g == null) {
            this.f6642g = new HashMap();
            this.f6643h = new HashMap();
            int length = this.f6645j.length;
            for (int i11 = 0; i11 < length; i11++) {
                this.f6642g.put(Integer.valueOf(getInputTensorIndex(this.c, i11)), Integer.valueOf(i11));
            }
            int length2 = this.f6646k.length;
            for (int i12 = 0; i12 < length2; i12++) {
                this.f6643h.put(Integer.valueOf(getOutputTensorIndex(this.c, i12)), Integer.valueOf(i12));
            }
        }
        int size = map.size();
        Object[] objArr = new Object[size];
        for (Map.Entry<String, Object> entry4 : map.entrySet()) {
            objArr[nativeSignatureRunnerWrapperF.b(entry4.getKey())] = entry4.getValue();
        }
        TreeMap treeMap = new TreeMap();
        for (Map.Entry<String, Object> entry5 : map2.entrySet()) {
            treeMap.put(Integer.valueOf(nativeSignatureRunnerWrapperF.c(entry5.getKey())), entry5.getValue());
        }
        this.inferenceDurationNanoseconds = -1L;
        if (size == 0) {
            throw new IllegalArgumentException("Input error: Inputs should not be null or empty.");
        }
        for (int i13 = 0; i13 < size; i13++) {
            int[] iArrJ2 = a(i13).j(objArr[i13]);
            if (iArrJ2 != null && resizeInput(this.c, this.f6638b, i13, iArrJ2, false)) {
                this.l = false;
                TensorImpl[] tensorImplArr = this.f6645j;
                if (tensorImplArr[i13] != null) {
                    tensorImplArr[i13].l();
                }
            }
        }
        boolean z10 = true;
        if (!this.l) {
            this.l = true;
            allocateTensors(this.c, this.f6638b);
            int i14 = 0;
            while (true) {
                TensorImpl[] tensorImplArr2 = this.f6646k;
                if (i14 >= tensorImplArr2.length) {
                    break;
                }
                if (tensorImplArr2[i14] != null) {
                    tensorImplArr2[i14].l();
                }
                i14++;
            }
        } else {
            z10 = false;
        }
        for (int i15 = 0; i15 < size; i15++) {
            a(i15).m(objArr[i15]);
        }
        long jNanoTime3 = System.nanoTime();
        run(this.c, this.f6638b);
        long jNanoTime4 = System.nanoTime() - jNanoTime3;
        if (z10) {
            while (true) {
                TensorImpl[] tensorImplArr3 = this.f6646k;
                if (i10 >= tensorImplArr3.length) {
                    break;
                }
                if (tensorImplArr3[i10] != null) {
                    tensorImplArr3[i10].l();
                }
                i10++;
            }
        }
        for (Map.Entry entry6 : treeMap.entrySet()) {
            if (entry6.getValue() != null) {
                int iIntValue = ((Integer) entry6.getKey()).intValue();
                if (iIntValue >= 0) {
                    TensorImpl[] tensorImplArr4 = this.f6646k;
                    if (iIntValue < tensorImplArr4.length) {
                        TensorImpl tensorImplG = tensorImplArr4[iIntValue];
                        if (tensorImplG == null) {
                            long j3 = this.c;
                            tensorImplG = TensorImpl.g(j3, getOutputTensorIndex(j3, iIntValue));
                            tensorImplArr4[iIntValue] = tensorImplG;
                        }
                        tensorImplG.e(entry6.getValue());
                    }
                }
                throw new IllegalArgumentException(e.p("Invalid output Tensor index: ", iIntValue));
            }
        }
        this.inferenceDurationNanoseconds = jNanoTime4;
    }
}
