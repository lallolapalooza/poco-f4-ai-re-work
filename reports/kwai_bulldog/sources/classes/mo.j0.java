package mo;

import com.google.protobuf.Internal;

/* JADX INFO: compiled from: FaceDetectMode.java */
/* JADX INFO: loaded from: classes15.dex */
public enum j0 implements Internal.EnumLite {
    kNormal(0),
    kTracking(1),
    kTrackingFast(3),
    kTrackingRobust(4),
    kDetectTrack(5),
    kTrackingRect(6),
    UNRECOGNIZED(-1);

    private static final Internal.EnumLiteMap<j0> internalValueMap = new Internal.EnumLiteMap<j0>() { // from class: mo.j0.a
        @Override // com.google.protobuf.Internal.EnumLiteMap
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public j0 findValueByNumber(int i12) {
            return j0.forNumber(i12);
        }
    };
    public static final int kDetectTrack_VALUE = 5;
    public static final int kNormal_VALUE = 0;
    public static final int kTrackingFast_VALUE = 3;
    public static final int kTrackingRect_VALUE = 6;
    public static final int kTrackingRobust_VALUE = 4;
    public static final int kTracking_VALUE = 1;
    private final int value;

    j0(int i12) {
        this.value = i12;
    }

    public static j0 forNumber(int i12) {
        if (i12 == 0) {
            return kNormal;
        }
        if (i12 == 1) {
            return kTracking;
        }
        if (i12 == 3) {
            return kTrackingFast;
        }
        if (i12 == 4) {
            return kTrackingRobust;
        }
        if (i12 == 5) {
            return kDetectTrack;
        }
        if (i12 != 6) {
            return null;
        }
        return kTrackingRect;
    }

    public static Internal.EnumLiteMap<j0> internalGetValueMap() {
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
    public static j0 valueOf(int i12) {
        return forNumber(i12);
    }
}
