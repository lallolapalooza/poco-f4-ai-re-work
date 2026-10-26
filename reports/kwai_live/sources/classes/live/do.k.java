package p000do;

import com.google.protobuf.Internal;

/* JADX INFO: compiled from: CameraOutputDataType.java */
/* JADX INFO: loaded from: classes15.dex */
public enum k implements Internal.EnumLite {
    kCameraOutputDataTypeYuv(0),
    kCameraOutputDataTypeTexture(1),
    kCameraOutputDataTypeBothYuvAndTexture(2),
    UNRECOGNIZED(-1);

    private static final Internal.EnumLiteMap<k> internalValueMap = new Internal.EnumLiteMap<k>() { // from class: do.k.a
        @Override // com.google.protobuf.Internal.EnumLiteMap
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public k findValueByNumber(int i12) {
            return k.forNumber(i12);
        }
    };
    public static final int kCameraOutputDataTypeBothYuvAndTexture_VALUE = 2;
    public static final int kCameraOutputDataTypeTexture_VALUE = 1;
    public static final int kCameraOutputDataTypeYuv_VALUE = 0;
    private final int value;

    k(int i12) {
        this.value = i12;
    }

    public static k forNumber(int i12) {
        if (i12 == 0) {
            return kCameraOutputDataTypeYuv;
        }
        if (i12 == 1) {
            return kCameraOutputDataTypeTexture;
        }
        if (i12 != 2) {
            return null;
        }
        return kCameraOutputDataTypeBothYuvAndTexture;
    }

    public static Internal.EnumLiteMap<k> internalGetValueMap() {
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
    public static k valueOf(int i12) {
        return forNumber(i12);
    }
}
