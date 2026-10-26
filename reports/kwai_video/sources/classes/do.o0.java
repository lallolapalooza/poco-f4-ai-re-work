package p000do;

import com.google.protobuf.Internal;

/* JADX INFO: compiled from: FrameProcessThread.java */
/* JADX INFO: loaded from: classes15.dex */
public enum o0 implements Internal.EnumLite {
    kUnuseFrameProcessThread(0),
    kCameraFrameProcessThread(1),
    kCpuFrameProcessThread(2),
    kGpuFrameProcessThread(3),
    kEncoderFrameProcessThread(4),
    UNRECOGNIZED(-1);

    private static final Internal.EnumLiteMap<o0> internalValueMap = new Internal.EnumLiteMap<o0>() { // from class: do.o0.a
        @Override // com.google.protobuf.Internal.EnumLiteMap
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public o0 findValueByNumber(int i12) {
            return o0.forNumber(i12);
        }
    };
    public static final int kCameraFrameProcessThread_VALUE = 1;
    public static final int kCpuFrameProcessThread_VALUE = 2;
    public static final int kEncoderFrameProcessThread_VALUE = 4;
    public static final int kGpuFrameProcessThread_VALUE = 3;
    public static final int kUnuseFrameProcessThread_VALUE = 0;
    private final int value;

    o0(int i12) {
        this.value = i12;
    }

    public static o0 forNumber(int i12) {
        if (i12 == 0) {
            return kUnuseFrameProcessThread;
        }
        if (i12 == 1) {
            return kCameraFrameProcessThread;
        }
        if (i12 == 2) {
            return kCpuFrameProcessThread;
        }
        if (i12 == 3) {
            return kGpuFrameProcessThread;
        }
        if (i12 != 4) {
            return null;
        }
        return kEncoderFrameProcessThread;
    }

    public static Internal.EnumLiteMap<o0> internalGetValueMap() {
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
    public static o0 valueOf(int i12) {
        return forNumber(i12);
    }
}
