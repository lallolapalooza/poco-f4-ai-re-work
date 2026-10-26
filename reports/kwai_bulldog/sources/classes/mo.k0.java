package mo;

import com.google.protobuf.Internal;

/* JADX INFO: compiled from: FaceDetectType.java */
/* JADX INFO: loaded from: classes15.dex */
public enum k0 implements Internal.EnumLite {
    kYcnnFaceDetect(0),
    UNRECOGNIZED(-1);

    private static final Internal.EnumLiteMap<k0> internalValueMap = new Internal.EnumLiteMap<k0>() { // from class: mo.k0.a
        @Override // com.google.protobuf.Internal.EnumLiteMap
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public k0 findValueByNumber(int i12) {
            return k0.forNumber(i12);
        }
    };
    public static final int kYcnnFaceDetect_VALUE = 0;
    private final int value;

    k0(int i12) {
        this.value = i12;
    }

    public static k0 forNumber(int i12) {
        if (i12 != 0) {
            return null;
        }
        return kYcnnFaceDetect;
    }

    public static Internal.EnumLiteMap<k0> internalGetValueMap() {
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
    public static k0 valueOf(int i12) {
        return forNumber(i12);
    }
}
