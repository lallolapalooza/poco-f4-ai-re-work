package com.ts.faceauth;

import com.ts.faceapi.model.StRect;
import com.ts.faceauth.bean.AreaInfo;
import com.ts.faceauth.bean.AuthenticationResult;
import com.ts.faceauth.bean.EnrollResult;
import com.ts.faceauth.data.CvPixelFormat;
import com.ts.faceauth.data.FaceIDInfo;
import com.ts.faceauth.data.FaceOrientation;
import com.ts.faceauth.data.STImage;
import com.ts.faceauth.data.StFaceAuthMode;
import com.ts.faceauth.data.StFaceAuthStatus;
import com.ts.faceauth.data.StFaceGpuPriority;
import com.ts.faceauth.data.StFaceLogLevel;
import com.ts.faceauth.util.FileUtil;
import com.ts.faceauth.util.LogUtil;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class FaceAuthManager {
    private static final String AREACODE_FILE_NAME = "area.co";
    private static final String NEW_ENROLL_FILE_NAME = "template_file.data";
    private static FaceAuthManager mInstance;
    private int mFaceLeftThreshold = 0;
    private int mFaceTopThreshold = 0;
    private int mFaceDownThreshold = 0;
    private int mFaceRightThreshold = 0;
    private String mEnrollPath = null;
    private int[] mResultCode = new int[1];
    private CvPixelFormat mImageFormat = CvPixelFormat.NV21;
    private int mFaceAuthEnrollConfig = 0;
    private int mFaceAuthCompareConfig = 0;
    private String mAreaCodeFilePath = null;
    private FaceDetectedCallback mFaceDetectedCallback = null;
    private String mLivenessModePath = "";
    private String LICENSE_STR = "############################################################\n# SenseTime License\n# License Product: FaceAuth\n# Expiration: 19700101~20990101\n# License SN: 0afba29a-8324-4a70-b741-6d59f6d7da7a\n# 本软件产品为商汤科技的核心软件产品，商汤科技对其拥有著作权。严禁将本软\n# 件产品的整体或部分（包括正式包，测试包，授权文件，模型等）以任何形式泄\n# 露给任何未经商汤科技书面授权的第三方，或者发布到任何有可能使未经商汤科\n# 技书面授权的第三方获取到上述软件产品的公开网络平台（如开源社区、第三方\n# 平台的个人空间等）。如发现上述未经允许泄露或者发布上述软件产品的行为，\n# 商汤科技将追究泄露方或发布方的法律责任。\n# This software product (so called “the PRODUCT”) is one of\n# the key software products of Sensetime Corp. Sensetime Corp.\n# owns the copyright of the PRODUCT. It is seriously forbidden\n# to disclose the whole or part of the PRODUCT (including formal\n# packages, test packages, license documents, models, etc.) in\n# any form to any third party that has not been licensed in\n# written by Sensetime Corp. or publish to any public network\n# platforms (such as open source community, personal space of\n# third party platform, etc.) where third parties that have not\n# been licensed in writing by Sensetime Corp. have possibility\n# to access the PRODUCT. In the event that the above-mentioned\n# unauthorized disclosure or publishing of the PRODUCT is found,\n# Sensetime Corp. will investigate the legal liability of the\n# discloser or the publisher.\n############################################################\nsGfdd4hav5Sr5ibX1RavjaiKWVxSLHJA/5YKZoYIpcV/JALNM+kz8fN+ZBR0\nI1kJNyYn29yRbPODLT7oF7KlfLbggDOdQKLdcGFrlHK3yYwD6dzrA3Z8uxC+\nT13bsauNljhbMkvS+dwiIHLv2sYr1jl3ukABpSiDxuFQ68y1aRFY7wcFAQAA\nAAEAAAB3s4Ys1MVEP9lnOEZQtDWlS1vBMc1JF+OATNU1f94oj1oxrUi6kwVx\nyCb630SWuPFD1sm9CobE9l9/N1H/kEbwF7SMmGEMu1EuroQTFppr7GLYbPGs\nA7DbtHzzL9R5S4YcXVSeoaPwddy369S34lXk5vudM3bLY6K4FFCQ44wS5LQw\nLrN92ohemYAzQ9joP4oNofzKf54VZ4LfbVF25NF13n1QbjrTKePHuTIOMLB1\nx6ruSIX7TJRBgIQWM3rkoWgSHYjazkTnaoLOs9NXfZTiIEzV41LgfZ5cKJpR\ngnsju2hou26AuzzR87DjZ/a7znnh8nmjmJOZA/MGnUJ1m0LTAQABAAAAAAAD\nAAAAAAAAAAAAAADsMfv+5EfZVCH2oNzbUqV+Ted0aTTx2R1eahPmMG+4n/Y4\nSXqBwgDsLt6LJwYk6EuJHaY4dxWw2B/bMCj4BkUH6Fwpym9HH1G8kpjU/kh5\nhWdg1aLvrjtZT+IkeCfdKGLGM3UpasJrMHR8qCTSS07Dc2YaQSZSf1u7YTui\nq2urKhata2GVLW910s5bcH/iKaVinksMv7biiEFr11lSwdE1N2FgKUULlRJV\ngQtLGmPAzkWzj0Kt/Rsc0Cz3cn/6/l7SY7y3\n------------------------------------------------------------\nsGfddy4Tki4UExG47TCs6jF4hCWnOUnatBHGa94YGvgn8RVk1UwpHgC/wf2S\n/emsSk/vobgjyokC/BDwachR9LIEYWP6Gnz3GBkmnlgTpjEP1lLPm2z7xMvl\nZtFyMXE2xZzqGbeFLccsKLYhAtN/gql9U4Ld2iHe9Wk++pxcPjp0LJuwAQAA\nAAIAAAC0HOP+EsUbB2k2EcJ7CVWZZbEeHCcw5E71GrJLIdExcGmjpy1DEnZS\ndqLfquRNR8+LSG7YfVRmIrjLZDmVgqdpBXRCcjjUc1tBKqRFm7v9bSAmBGBi\nkhu8wVHBQHgKJ9V0irOVDX0mQsXNgw4wtIfqYLM9ml7RQtwfG8w4VN5+17mV\n4G5YXqhWFm8uqXjoaHTAxCuOMo5o3qRpFPQkbmxvqpi9anLZ6tzGyDe9+cfW\n8CsetgTz0g/6f+ImZr3ke2MfCb7PhV4wxTiqb6zvPXcPYs/WE5spVSk0qKJT\ndh5XfnysjkO/rCoAkHCJRMt2kYD9mHK560Uuq/QLYvMjFM5zAAAAAAAAAAAA\nAAAAAAAAAAAAAAAd1fnSuJRPn2tbwqRGL0wMwuhiifW57FK/GOccCRWVG3zs\n8oHZsUBUP1IfEDmCqakNcMxocsXr8RDnspz20ShkHt6gw2RkL26W98Oc+ZJV\n5dx1RUPBdJH8JXz3IOXWZUl0eC5dVVWg1pxpRVkUR21A2K7u/kMD1TQ9+ndx\nrtnhcKzv4oeTVBji/OWNUWGZc6tMitZKhiMcqHIxE3B4XRZXrSxHk1XK2icR\n0cuYvZL8PDcROlCiNSy3cf2t\n############################################################";

    public CvPixelFormat getImageFormat() {
        return this.mImageFormat;
    }

    public void setImageFormat(CvPixelFormat cvPixelFormat) {
        this.mImageFormat = cvPixelFormat;
    }

    public static FaceAuthManager getInstance() {
        if (mInstance == null) {
            synchronized (FaceAuthManager.class) {
                if (mInstance == null) {
                    mInstance = new FaceAuthManager();
                }
            }
        }
        return mInstance;
    }

    public synchronized void setLogLevel(StFaceLogLevel stFaceLogLevel) {
        FaceAuthNative.setLogLevel(stFaceLogLevel.getValue());
    }

    public synchronized void setGPUPriority(StFaceGpuPriority stFaceGpuPriority) {
        LogUtil.d(LogUtil.TAG, "set GPU Priority to " + stFaceGpuPriority.getValue());
        FaceAuthNative.setGPUPriority(stFaceGpuPriority.getValue());
    }

    public synchronized boolean initHandler(String str) {
        try {
            if (str == null) {
                LogUtil.d(LogUtil.TAG, "the dbpath is null");
                return false;
            }
            File file = new File(str);
            if (file.exists()) {
                if (!file.canRead() || !file.canWrite()) {
                    LogUtil.d(LogUtil.TAG, "can not read or write in the dbpath:" + str);
                    return false;
                }
            } else if (!file.mkdirs()) {
                LogUtil.d(LogUtil.TAG, "mkdir failed for dbpath :" + str);
                return false;
            }
            this.mEnrollPath = str;
            this.mAreaCodeFilePath = str + File.separator + AREACODE_FILE_NAME;
            return true;
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized int getFeatureCount() {
        int i;
        FaceIDInfo[] faceIDInfoInTemplate = FaceAuthNative.getFaceIDInfoInTemplate(this.mResultCode);
        i = 0;
        if (this.mResultCode[0] == 0) {
            if (faceIDInfoInTemplate != null && faceIDInfoInTemplate.length != 0) {
                int i2 = 0;
                while (i < faceIDInfoInTemplate.length) {
                    LogUtil.d(LogUtil.TAG, "the faceId is " + faceIDInfoInTemplate[i].mFaceID);
                    i2 += faceIDInfoInTemplate[i].mFeatureCountOfFace;
                    i++;
                }
                i = i2;
            }
        } else {
            LogUtil.e(LogUtil.TAG, "getFeatureCount failed with internal result status " + this.mResultCode[0] + ", maybe you have not enrolled or the template file is damaged");
        }
        LogUtil.d(LogUtil.TAG, "the featureCount is " + i);
        return i;
    }

    public synchronized int initAll(String str, String str2) {
        int iInitLicense = FaceAuthNative.initLicense(this.LICENSE_STR);
        if (iInitLicense != 0) {
            LogUtil.e(LogUtil.TAG, "initlicense failed with result " + iInitLicense);
            return 3;
        }
        FaceAuthNative.createFaceAuth(this.mEnrollPath, 0, this.mResultCode);
        this.mLivenessModePath = str2;
        if (this.mResultCode[0] == 0) {
            configFaceAuthThresholds(getAreaCode());
        }
        int i = this.mResultCode[0];
        LogUtil.d(LogUtil.TAG, "initAll result is " + i);
        int iStFaceAuthStatusToFaceAuthResult = stFaceAuthStatusToFaceAuthResult(i);
        LogUtil.d(LogUtil.TAG, "current feature count is " + getFeatureCount());
        return iStFaceAuthStatusToFaceAuthResult;
    }

    public synchronized int initAll(String str) {
        if (str == null) {
            return 1;
        }
        int iInitLicense = FaceAuthNative.initLicense(str);
        if (iInitLicense != 0) {
            LogUtil.e(LogUtil.TAG, "initlicense failed with result " + iInitLicense);
            return 3;
        }
        FaceAuthNative.createFaceAuth(this.mEnrollPath, 0, this.mResultCode);
        if (this.mResultCode[0] == 0) {
            configFaceAuthThresholds(getAreaCode());
        }
        int i = this.mResultCode[0];
        LogUtil.d(LogUtil.TAG, "initAll result is " + i);
        int iStFaceAuthStatusToFaceAuthResult = stFaceAuthStatusToFaceAuthResult(i);
        LogUtil.d(LogUtil.TAG, "current feature count is " + getFeatureCount());
        return iStFaceAuthStatusToFaceAuthResult;
    }

    public synchronized void setFaceAuthEnrollConfig(int i) {
        this.mFaceAuthEnrollConfig = i;
    }

    public synchronized void setFaceAuthCompareConfig(int i) {
        this.mFaceAuthCompareConfig = i;
    }

    public synchronized void setDetectArea(int i, int i2, int i3, int i4) {
        this.mFaceLeftThreshold = i;
        this.mFaceTopThreshold = i2;
        this.mFaceRightThreshold = i3;
        this.mFaceDownThreshold = i4;
    }

    public synchronized String getVersion() {
        String sDKVersion;
        sDKVersion = FaceAuthNative.getSDKVersion();
        LogUtil.d(LogUtil.TAG, "the sdk version is " + sDKVersion);
        return sDKVersion;
    }

    public synchronized String getModelInfo() {
        String algoInfo;
        algoInfo = FaceAuthNative.getAlgoInfo();
        LogUtil.d(LogUtil.TAG, "the model info is " + algoInfo);
        return algoInfo;
    }

    public synchronized int setFaceAuthMode(StFaceAuthMode stFaceAuthMode) {
        return stFaceAuthStatusToFaceAuthResult(FaceAuthNative.setFaceAuthMode(stFaceAuthMode.getValue()));
    }

    public synchronized int setAreaCode(String str) {
        LogUtil.d(LogUtil.TAG, "setAreaCode to " + str);
        if (this.mAreaCodeFilePath == null) {
            LogUtil.e(LogUtil.TAG, "Please call initHandler first");
            return 3;
        }
        if (str == null) {
            LogUtil.e(LogUtil.TAG, "the areaCode is null，clean the areacode");
            File file = new File(this.mAreaCodeFilePath);
            if (file.exists()) {
                file.delete();
            }
            return 0;
        }
        if (!configFaceAuthThresholds(str)) {
            return 3;
        }
        FileUtil.saveString(str, this.mAreaCodeFilePath);
        return 0;
    }

    private String getAreaCode() {
        String str = this.mAreaCodeFilePath;
        if (str != null) {
            return FileUtil.readString(str);
        }
        return null;
    }

    private boolean configFaceAuthThresholds(String str) {
        if (str == null) {
            LogUtil.i(LogUtil.TAG, "did not set areaCode yet");
            return true;
        }
        Integer num = AreaInfo.mAreaMap.get(str.toUpperCase());
        if (num == null) {
            LogUtil.i(LogUtil.TAG, "can not find the areaCode " + str);
            return false;
        }
        int areaCode = FaceAuthNative.setAreaCode(num.intValue());
        if (areaCode == 0) {
            LogUtil.i(LogUtil.TAG, "Success to config threshold for area code " + str);
            return true;
        }
        LogUtil.i(LogUtil.TAG, "set threshold for this area code " + str + " failed, the result is " + areaCode);
        return false;
    }

    public synchronized int evaluate(String str, String str2, int i, int i2, int i3, int i4) {
        return stFaceAuthStatusToFaceAuthResult(FaceAuthNative.evaluate(str, str2, this.mImageFormat.getValue(), i, i2, i3, i4));
    }

    public synchronized int setEnrollImageNumberForFace(int i) {
        return stFaceAuthStatusToFaceAuthResult(FaceAuthNative.setEnrollNumberForFace(i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized int saveFeature(byte[] bArr, int i, int i2, int i3, boolean z) {
        Object[] objArr;
        int i4;
        if (bArr == null || i == 0 || i2 == 0) {
            return 1;
        }
        FaceIDInfo[] faceIDInfoInTemplate = FaceAuthNative.getFaceIDInfoInTemplate(this.mResultCode);
        if (this.mResultCode[0] == 0 && faceIDInfoInTemplate != null && faceIDInfoInTemplate.length == 1) {
            LogUtil.w(LogUtil.TAG, "should delete the old face in template when enroll success");
            i4 = faceIDInfoInTemplate[0].mFaceID;
            objArr = true;
        } else {
            objArr = false;
            i4 = -1;
        }
        if (this.mFaceLeftThreshold != 0 || this.mFaceRightThreshold != 0 || this.mFaceTopThreshold != 0 || this.mFaceDownThreshold != 0) {
            FaceAuthNative.setRoi(RotateMaskRect(new StRect(this.mFaceLeftThreshold, this.mFaceTopThreshold, this.mFaceRightThreshold, this.mFaceDownThreshold), i, i2, i3, true), this.mResultCode);
        } else {
            FaceAuthNative.setRoi(null, this.mResultCode);
        }
        STImage sTImage = new STImage(bArr, this.mImageFormat, i, i2, getStride(this.mImageFormat, i));
        FaceAuthNative.setOrientation(getFaceOrientation(i3).getValue(), this.mResultCode);
        int[] iArr = this.mResultCode;
        if (iArr[0] == 0) {
            int i5 = !z ? 1 : 0;
            if (this.mLivenessModePath == null) {
                i5 |= 8;
            }
            FaceAuthNative.enroll(sTImage, i5 | this.mFaceAuthEnrollConfig, iArr);
        }
        int[] iArr2 = this.mResultCode;
        if (iArr2[0] == 0 && objArr != false && i4 != -1) {
            iArr2[0] = FaceAuthNative.deleteFace(i4);
            if (this.mResultCode[0] != 0) {
                LogUtil.e(LogUtil.TAG, "delete failed with result " + this.mResultCode[0]);
                this.mResultCode[0] = deleteFeature();
            }
        }
        LogUtil.d(LogUtil.TAG, "saveFeature native return " + this.mResultCode[0]);
        return stFaceAuthStatusToFaceAuthResult(this.mResultCode[0]);
    }

    public synchronized EnrollResult saveFeature(byte[] bArr, int i, int i2, int i3) {
        try {
            if (bArr == null || i == 0 || i2 == 0) {
                return new EnrollResult(-1, 1);
            }
            if (this.mFaceLeftThreshold != 0 || this.mFaceRightThreshold != 0 || this.mFaceTopThreshold != 0 || this.mFaceDownThreshold != 0) {
                FaceAuthNative.setRoi(RotateMaskRect(new StRect(this.mFaceLeftThreshold, this.mFaceTopThreshold, this.mFaceRightThreshold, this.mFaceDownThreshold), i, i2, i3, true), this.mResultCode);
            } else {
                FaceAuthNative.setRoi(null, this.mResultCode);
            }
            STImage sTImage = new STImage(bArr, this.mImageFormat, i, i2, getStride(this.mImageFormat, i));
            FaceAuthNative.setOrientation(getFaceOrientation(i3).getValue(), this.mResultCode);
            int[] iArr = this.mResultCode;
            int iEnroll = iArr[0] == 0 ? FaceAuthNative.enroll(sTImage, this.mFaceAuthEnrollConfig, iArr) : -1;
            LogUtil.d(LogUtil.TAG, "saveFeature native return " + this.mResultCode[0]);
            int iStFaceAuthStatusToFaceAuthResult = stFaceAuthStatusToFaceAuthResult(this.mResultCode[0]);
            LogUtil.d(LogUtil.TAG, "saveFeature return " + iStFaceAuthStatusToFaceAuthResult);
            EnrollResult enrollResult = new EnrollResult(iEnroll, iStFaceAuthStatusToFaceAuthResult);
            enrollResult.setNativeStatus(this.mResultCode[0]);
            return enrollResult;
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized AuthenticationResult compare(byte[] bArr, int i, int i2, int i3, int[] iArr) {
        int lastUnlockID = -1;
        try {
            if (bArr == null || i == 0 || i2 == 0) {
                return new AuthenticationResult(1, -1);
            }
            FaceAuthNative.setRoi(null, this.mResultCode);
            STImage sTImage = new STImage(bArr, this.mImageFormat, i, i2, getStride(this.mImageFormat, i));
            FaceAuthNative.setOrientation(getFaceOrientation(i3).getValue(), this.mResultCode);
            int[] iArr2 = this.mResultCode;
            if (iArr2[0] == 0) {
                FaceAuthNative.auth(sTImage, this.mFaceAuthCompareConfig, iArr, iArr2);
            }
            LogUtil.d(LogUtil.TAG, "compare native return " + this.mResultCode[0]);
            int[] iArr3 = this.mResultCode;
            if (iArr3[0] == 201) {
                lastUnlockID = FaceAuthNative.getLastUnlockID(iArr3);
                LogUtil.d(LogUtil.TAG, "compare id is " + lastUnlockID);
            }
            int iStFaceAuthStatusToFaceAuthResult = stFaceAuthStatusToFaceAuthResult(this.mResultCode[0]);
            LogUtil.d(LogUtil.TAG, "compare return " + iStFaceAuthStatusToFaceAuthResult);
            AuthenticationResult authenticationResult = new AuthenticationResult(iStFaceAuthStatusToFaceAuthResult, lastUnlockID);
            authenticationResult.setNativeStatus(this.mResultCode[0]);
            return authenticationResult;
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized int deleteFeature(int i) {
        return stFaceAuthStatusToFaceAuthResult(FaceAuthNative.deleteFace(i));
    }

    public synchronized int[] getFaceIDsInTemplate() {
        int[] iArr;
        FaceIDInfo[] faceIDInfoInTemplate = FaceAuthNative.getFaceIDInfoInTemplate(this.mResultCode);
        if (this.mResultCode[0] == 0) {
            if (faceIDInfoInTemplate != null && faceIDInfoInTemplate.length != 0) {
                iArr = new int[faceIDInfoInTemplate.length];
                for (int i = 0; i < faceIDInfoInTemplate.length; i++) {
                    iArr[i] = faceIDInfoInTemplate[i].mFaceID;
                }
            }
        } else {
            LogUtil.e(LogUtil.TAG, "getFaceIDInfoInTemplate failed with internal result status " + this.mResultCode[0]);
        }
        iArr = null;
        return iArr;
    }

    public synchronized int deleteFeature() {
        return stFaceAuthStatusToFaceAuthResult(FaceAuthNative.deleteAllFace());
    }

    public synchronized int compare(byte[] bArr, int i, int i2, int i3, boolean z) {
        if (bArr == null || i == 0 || i2 == 0) {
            return 1;
        }
        FaceAuthNative.setRoi(null, this.mResultCode);
        STImage sTImage = new STImage(bArr, this.mImageFormat, i, i2, getStride(this.mImageFormat, i));
        FaceAuthNative.setOrientation(getFaceOrientation(i3).getValue(), this.mResultCode);
        int[] iArr = this.mResultCode;
        if (iArr[0] == 0) {
            int i4 = !z ? 1 : 0;
            if (this.mLivenessModePath == null) {
                i4 |= 8;
            }
            FaceAuthNative.auth(sTImage, i4 | this.mFaceAuthCompareConfig, null, iArr);
        }
        int i5 = this.mResultCode[0];
        LogUtil.d(LogUtil.TAG, "compare return " + i5);
        return stFaceAuthStatusToFaceAuthResult(i5);
    }

    public synchronized int reset() {
        int iResetFaceAuth;
        iResetFaceAuth = FaceAuthNative.resetFaceAuth();
        LogUtil.d(LogUtil.TAG, "reset native return " + iResetFaceAuth);
        return stFaceAuthStatusToFaceAuthResult(iResetFaceAuth);
    }

    public synchronized void release() {
        LogUtil.d(LogUtil.TAG, "release");
        FaceAuthNative.releaseFaceAuth();
        this.mLivenessModePath = "";
    }

    public synchronized void setStandByMode() {
        LogUtil.d(LogUtil.TAG, "standby");
        FaceAuthNative.setStandByMode();
    }

    public synchronized void setCallback(FaceDetectedCallback faceDetectedCallback) {
        FaceAuthNative.setCallback(faceDetectedCallback);
        if (faceDetectedCallback != null) {
            FaceAuthNative.enableFaceDetectCallback(true);
        } else {
            FaceAuthNative.enableFaceDetectCallback(false);
        }
    }

    private FaceOrientation getFaceOrientation(int i) {
        if (i == 0) {
            return FaceOrientation.UP;
        }
        if (i == 90) {
            return FaceOrientation.RIGHT;
        }
        if (i == 180) {
            return FaceOrientation.DOWN;
        }
        if (i == 270) {
            return FaceOrientation.LEFT;
        }
        return FaceOrientation.UNKNOWN;
    }

    /* JADX INFO: renamed from: com.ts.faceauth.FaceAuthManager$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ts$faceauth$data$CvPixelFormat;

        static {
            int[] iArr = new int[CvPixelFormat.values().length];
            $SwitchMap$com$ts$faceauth$data$CvPixelFormat = iArr;
            try {
                iArr[CvPixelFormat.BGR888.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ts$faceauth$data$CvPixelFormat[CvPixelFormat.RGB888.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ts$faceauth$data$CvPixelFormat[CvPixelFormat.BGRA8888.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$ts$faceauth$data$CvPixelFormat[CvPixelFormat.RGBA8888.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$ts$faceauth$data$CvPixelFormat[CvPixelFormat.GRAY8.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$ts$faceauth$data$CvPixelFormat[CvPixelFormat.YUV420P.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$ts$faceauth$data$CvPixelFormat[CvPixelFormat.NV12.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$ts$faceauth$data$CvPixelFormat[CvPixelFormat.NV21.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private int getStride(CvPixelFormat cvPixelFormat, int i) {
        int i2 = AnonymousClass1.$SwitchMap$com$ts$faceauth$data$CvPixelFormat[cvPixelFormat.ordinal()];
        if (i2 == 1 || i2 == 2) {
            return i * 3;
        }
        return (i2 == 3 || i2 == 4) ? i * 4 : i;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:37:0x006d A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:39:0x0073 A[RETURN, SYNTHETIC] */
    private static int stFaceAuthStatusToFaceAuthResult(int i) {
        if (i == -1008) {
            return 35;
        }
        if (i == -1001) {
            return 34;
        }
        if (i == -2) {
            return 2;
        }
        if (i == 123) {
            return 30;
        }
        if (i == 120) {
            return 6;
        }
        if (i == 121) {
            return 7;
        }
        if (i != 201) {
            if (i == 202) {
                return 12;
            }
            switch (i) {
                case 0:
                    break;
                case 1:
                    return 5;
                case 2:
                    return 14;
                case 3:
                    return 22;
                case 4:
                    return 4;
                case 5:
                    return 19;
                case 6:
                    return 36;
                case 7:
                    return 31;
                default:
                    switch (i) {
                        case StFaceAuthStatus.ST_FACE_AUTH_STATUS_ENROLL_OK /* 101 */:
                            break;
                        case StFaceAuthStatus.ST_FACE_AUTH_STATUS_DETECT_FRAME_SELECT_FAILED /* 102 */:
                            return 4;
                        case StFaceAuthStatus.ST_FACE_AUTH_STATUS_EYE_BLOCKED /* 103 */:
                            return 21;
                        case StFaceAuthStatus.ST_FACE_AUTH_STATUS_MOUTH_BLOCKED /* 104 */:
                            return 23;
                        case StFaceAuthStatus.ST_FACE_AUTH_STATUS_ENROLL_NOT_IN_ROI /* 105 */:
                            return 33;
                        case StFaceAuthStatus.ST_FACE_AUTH_STATUS_ENROLL_NOT_SAME_PERSON /* 106 */:
                            return 36;
                        default:
                            switch (i) {
                                case StFaceAuthStatus.ST_FACE_AUTH_STATUS_FACE_UP /* 113 */:
                                    return 16;
                                case StFaceAuthStatus.ST_FACE_AUTH_STATUS_FACE_DOWN /* 114 */:
                                    return 18;
                                case StFaceAuthStatus.ST_FACE_AUTH_STATUS_FACE_LEFT /* 115 */:
                                    return 15;
                                case StFaceAuthStatus.ST_FACE_AUTH_STATUS_FACE_RIGHT /* 116 */:
                                    return 17;
                                case StFaceAuthStatus.ST_FACE_AUTH_STATUS_QUALITY_SHARP /* 117 */:
                                    return 4;
                                default:
                                    switch (i) {
                                        case StFaceAuthStatus.ST_FACE_AUTH_STATUS_ENROLL_FACE_MULTI_FACE /* 129 */:
                                            return 27;
                                        case StFaceAuthStatus.ST_FACE_AUTH_STATUS_NOSE_BLOCKED /* 130 */:
                                            return 37;
                                        case StFaceAuthStatus.ST_FACE_AUTH_STATUS_OFFSET_LEFT /* 131 */:
                                            return 8;
                                        case StFaceAuthStatus.ST_FACE_AUTH_STATUS_OFFSET_TOP /* 132 */:
                                            return 9;
                                        case StFaceAuthStatus.ST_FACE_AUTH_STATUS_OFFSET_RIGHT /* 133 */:
                                            return 10;
                                        case StFaceAuthStatus.ST_FACE_AUTH_STATUS_OFFSET_BOTTOM /* 134 */:
                                            return 11;
                                        default:
                                            LogUtil.e(LogUtil.TAG, "the internal status is " + i);
                                            return 3;
                                    }
                            }
                    }
                    break;
            }
        }
        return 0;
    }

    private StRect RotateMaskRect(StRect stRect, int i, int i2, int i3, boolean z) {
        if (i3 != 0) {
            if (i3 == 90) {
                int i4 = stRect.left;
                stRect.left = i - stRect.bottom;
                stRect.bottom = stRect.right;
                stRect.right = i - stRect.top;
                stRect.top = i4;
                if (z) {
                    int i5 = stRect.top;
                    stRect.top = i2 - stRect.bottom;
                    stRect.bottom = i2 - i5;
                }
            } else if (i3 == 180) {
                int i6 = stRect.top;
                stRect.top = i2 - stRect.bottom;
                stRect.bottom = i2 - i6;
                if (!z) {
                    int i7 = stRect.left;
                    stRect.left = i - stRect.right;
                    stRect.right = i - i7;
                }
            } else if (i3 == 270) {
                stRect.left = stRect.top;
                stRect.top = i2 - stRect.right;
                stRect.right = stRect.bottom;
                stRect.bottom = i2 - stRect.left;
                if (z) {
                    int i8 = stRect.top;
                    stRect.top = i2 - stRect.bottom;
                    stRect.bottom = i2 - i8;
                }
            }
        } else if (z) {
            int i9 = stRect.left;
            stRect.left = i - stRect.right;
            stRect.right = i - stRect.left;
        }
        return new StRect(stRect.left, stRect.top, stRect.right, stRect.bottom);
    }
}
