package com.android.keyguard;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class KeyguardUtils {
    public static final long DEFAULT_DEVICEID = 123456;
    public static final int ERROR_BINDER_CALL = 2100;
    public static final int ERROR_CANCELED = 2000;
    public static final int ERROR_SERVICE_IS_BUSY = 2001;
    public static final int ERROR_SERVICE_IS_IDLE = 2002;
    public static final int ERROR_TIME_OUT = 2003;
    public static final int MG_ATTR_BLUR = 20;
    public static final int MG_ATTR_EYE_CLOSE = 22;
    public static final int MG_ATTR_EYE_OCCLUSION = 21;
    public static final int MG_ATTR_MOUTH_OCCLUSION = 23;
    public static final int MG_OPEN_CAMERA_FAIL = 1000;
    public static final int MG_OPEN_CAMERA_SUCCESS = 1001;
    public static final int MG_UNLOCK_BAD_LIGHT = 26;
    public static final int MG_UNLOCK_COMPARE_FAILURE = 12;
    public static final int MG_UNLOCK_DARKLIGHT = 30;
    public static final int MG_UNLOCK_FACE_BAD_QUALITY = 4;
    public static final int MG_UNLOCK_FACE_BLUR = 28;
    public static final int MG_UNLOCK_FACE_DOWN = 18;
    public static final int MG_UNLOCK_FACE_MULTI = 27;
    public static final int MG_UNLOCK_FACE_NOT_COMPLETE = 29;
    public static final int MG_UNLOCK_FACE_NOT_FOUND = 5;
    public static final int MG_UNLOCK_FACE_OFFSET_BOTTOM = 11;
    public static final int MG_UNLOCK_FACE_OFFSET_LEFT = 8;
    public static final int MG_UNLOCK_FACE_OFFSET_RIGHT = 10;
    public static final int MG_UNLOCK_FACE_OFFSET_TOP = 9;
    public static final int MG_UNLOCK_FACE_RISE = 16;
    public static final int MG_UNLOCK_FACE_ROTATED_LEFT = 15;
    public static final int MG_UNLOCK_FACE_ROTATED_RIGHT = 17;
    public static final int MG_UNLOCK_FACE_SCALE_TOO_LARGE = 7;
    public static final int MG_UNLOCK_FACE_SCALE_TOO_SMALL = 6;
    public static final int MG_UNLOCK_FAILURE = 3;
    public static final int MG_UNLOCK_FEATURE_MISS = 24;
    public static final int MG_UNLOCK_FEATURE_VERSION_ERROR = 25;
    public static final int MG_UNLOCK_HALF_SHADOW = 32;
    public static final int MG_UNLOCK_HIGHLIGHT = 31;
    public static final int MG_UNLOCK_INVALID_ARGUMENT = 1;
    public static final int MG_UNLOCK_INVALID_HANDLE = 2;
    public static final int MG_UNLOCK_KEEP = 19;
    public static final int MG_UNLOCK_LIVENESS_FAILURE = 14;
    public static final int MG_UNLOCK_LIVENESS_WARNING = 13;
    public static final int MG_UNLOCK_OK = 0;
    public static final int ST_FACE_ENROLL_NOT_SAME_PERSON = 36;
    private static List<String> sDeviceUseCamera1 = new ArrayList();
}
