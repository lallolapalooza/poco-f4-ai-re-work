package mo;

import com.google.protobuf.Internal;

/* JADX INFO: compiled from: FaceDetectInitType.java */
/* JADX INFO: loaded from: classes15.dex */
public enum i0 implements Internal.EnumLite {
    kFaceDetectInitAuto(0),
    kFaceDetectInitSync(1),
    kFaceDetectInitAsync(2),
    UNRECOGNIZED(-1);

    private static final Internal.EnumLiteMap<i0> internalValueMap = new Internal.EnumLiteMap<i0>() { // from class: mo.i0.a
        @Override // com.google.protobuf.Internal.EnumLiteMap
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public i0 findValueByNumber(int i12) {
            return i0.forNumber(i12);
        }
    };
    public static final int kFaceDetectInitAsync_VALUE = 2;
    public static final int kFaceDetectInitAuto_VALUE = 0;
    public static final int kFaceDetectInitSync_VALUE = 1;
    private final int value;

    i0(int i12) {
        this.value = i12;
    }

    public static i0 forNumber(int i12) {
        if (i12 == 0) {
            return kFaceDetectInitAuto;
        }
        if (i12 == 1) {
            return kFaceDetectInitSync;
        }
        if (i12 != 2) {
            return null;
        }
        return kFaceDetectInitAsync;
    }

    public static Internal.EnumLiteMap<i0> internalGetValueMap() {
        return internalValueMap;
    }

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        if (this != UNRECOGNIZED) {
            return this.value;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    @Deprecated
    public static i0 valueOf(int i12) {
        return forNumber(i12);
    }
}
