package p000do;

import com.google.protobuf.Internal;

/* JADX INFO: compiled from: CameraApiVersion.java */
/* JADX INFO: loaded from: classes15.dex */
public enum i implements Internal.EnumLite {
    kAndroidCameraAuto(0),
    kAndroidCamera1(1),
    kAndroidCamera2(2),
    kAndroidCameraKit(3),
    kAndroidCameraUnit(4),
    kAndroidCameraVivo(5),
    kiOS(10),
    UNRECOGNIZED(-1);

    private static final Internal.EnumLiteMap<i> internalValueMap = new Internal.EnumLiteMap<i>() { // from class: do.i.a
        @Override // com.google.protobuf.Internal.EnumLiteMap
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public i findValueByNumber(int i12) {
            return i.forNumber(i12);
        }
    };
    public static final int kAndroidCamera1_VALUE = 1;
    public static final int kAndroidCamera2_VALUE = 2;
    public static final int kAndroidCameraAuto_VALUE = 0;
    public static final int kAndroidCameraKit_VALUE = 3;
    public static final int kAndroidCameraUnit_VALUE = 4;
    public static final int kAndroidCameraVivo_VALUE = 5;
    public static final int kiOS_VALUE = 10;
    private final int value;

    i(int i12) {
        this.value = i12;
    }

    public static i forNumber(int i12) {
        if (i12 == 0) {
            return kAndroidCameraAuto;
        }
        if (i12 == 1) {
            return kAndroidCamera1;
        }
        if (i12 == 2) {
            return kAndroidCamera2;
        }
        if (i12 == 3) {
            return kAndroidCameraKit;
        }
        if (i12 == 4) {
            return kAndroidCameraUnit;
        }
        if (i12 == 5) {
            return kAndroidCameraVivo;
        }
        if (i12 != 10) {
            return null;
        }
        return kiOS;
    }

    public static Internal.EnumLiteMap<i> internalGetValueMap() {
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
    public static i valueOf(int i12) {
        return forNumber(i12);
    }
}
