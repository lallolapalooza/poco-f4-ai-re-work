package mo;

import com.google.protobuf.Internal;

/* JADX INFO: compiled from: Gender.java */
/* JADX INFO: loaded from: classes15.dex */
public enum q0 implements Internal.EnumLite {
    kGenderUnknown(0),
    kGenderMale(1),
    kGenderFemale(2),
    UNRECOGNIZED(-1);

    private static final Internal.EnumLiteMap<q0> internalValueMap = new Internal.EnumLiteMap<q0>() { // from class: mo.q0.a
        @Override // com.google.protobuf.Internal.EnumLiteMap
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public q0 findValueByNumber(int i12) {
            return q0.forNumber(i12);
        }
    };
    public static final int kGenderFemale_VALUE = 2;
    public static final int kGenderMale_VALUE = 1;
    public static final int kGenderUnknown_VALUE = 0;
    private final int value;

    q0(int i12) {
        this.value = i12;
    }

    public static q0 forNumber(int i12) {
        if (i12 == 0) {
            return kGenderUnknown;
        }
        if (i12 == 1) {
            return kGenderMale;
        }
        if (i12 != 2) {
            return null;
        }
        return kGenderFemale;
    }

    public static Internal.EnumLiteMap<q0> internalGetValueMap() {
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
    public static q0 valueOf(int i12) {
        return forNumber(i12);
    }
}
