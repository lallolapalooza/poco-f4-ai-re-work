package com.ts.faceauth;

/* JADX INFO: loaded from: classes.dex */
public interface FaceAuthResult {
    public static final int ST_ATTR_EYE_CLOSE = 22;
    public static final int ST_ATTR_EYE_OCCLUSION = 21;
    public static final int ST_ATTR_MOUTH_OCCLUSION = 23;
    public static final int ST_E_COMPARE_FAILURE = 12;
    public static final int ST_E_FACE_BAD_QUALITY = 4;
    public static final int ST_E_FACE_OFFSET_BOTTOM = 11;
    public static final int ST_E_FACE_OFFSET_LEFT = 8;
    public static final int ST_E_FACE_OFFSET_RIGHT = 10;
    public static final int ST_E_FACE_OFFSET_TOP = 9;
    public static final int ST_E_FACE_SCALE_TOO_LARGE = 7;
    public static final int ST_E_FACE_SCALE_TOO_SMALL = 6;
    public static final int ST_E_FAIL = 3;
    public static final int ST_E_HANDLE = 2;
    public static final int ST_E_INVALIDARG = 1;
    public static final int ST_E_LIVENESS_FAILURE = 14;
    public static final int ST_FACE_DOWN = 16;
    public static final int ST_FACE_ENROLL_NOT_SAME_PERSON = 36;
    public static final int ST_FACE_ENROLL_REACH_UPPER_LIMIT = 35;
    public static final int ST_FACE_LIGHT_TOO_DIM = 30;
    public static final int ST_FACE_LIGHT_TOO_HIGH = 31;
    public static final int ST_FACE_MULTI = 27;
    public static final int ST_FACE_NOSE_OCCLUSION = 37;
    public static final int ST_FACE_NOT_COMPLETE = 29;
    public static final int ST_FACE_NOT_FOUND = 5;
    public static final int ST_FACE_NOT_IN_ROI = 33;
    public static final int ST_FACE_RISE = 18;
    public static final int ST_FACE_ROTATED_LEFT = 17;
    public static final int ST_FACE_ROTATED_RIGHT = 15;
    public static final int ST_FACE_TEMPLATE_FILE_DAMAGED = 34;
    public static final int ST_FEATURE_MISS = 24;
    public static final int ST_KEEP = 19;
    public static final int ST_OK = 0;
}
