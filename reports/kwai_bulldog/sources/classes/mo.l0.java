package mo;

import com.google.protobuf.Internal;

/* JADX INFO: compiled from: FaceDetectorName.java */
/* JADX INFO: loaded from: classes15.dex */
public enum l0 implements Internal.EnumLite {
    kVideoDetector(0),
    kImageDetector(1),
    UNRECOGNIZED(-1);

    private static final Internal.EnumLiteMap<l0> internalValueMap = new Internal.EnumLiteMap<l0>() { // from class: mo.l0.a
        @Override // com.google.protobuf.Internal.EnumLiteMap
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public l0 findValueByNumber(int i12) {
            return l0.forNumber(i12);
        }
    };
    public static final int kImageDetector_VALUE = 1;
    public static final int kVideoDetector_VALUE = 0;
    private final int value;

    l0(int i12) {
        this.value = i12;
    }

    public static l0 forNumber(int i12) {
        if (i12 == 0) {
            return kVideoDetector;
        }
        if (i12 != 1) {
            return null;
        }
        return kImageDetector;
    }

    public static Internal.EnumLiteMap<l0> internalGetValueMap() {
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
    public static l0 valueOf(int i12) {
        return forNumber(i12);
    }
}
