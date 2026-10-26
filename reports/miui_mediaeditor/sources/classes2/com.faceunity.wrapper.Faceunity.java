package com.faceunity.wrapper;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class Faceunity {
    public static final int BUNDLETYPE_ARTS_PHOTO = 4;
    public static final int BUNDLETYPE_BEAUTY_TYPE = 1;
    public static final int BUNDLETYPE_ID_PHOTO = 5;
    public static final int BUNDLETYPE_IMAGE_EDITOR = 0;
    public static final int BUNDLETYPE_LIGHT_RECOG = 6;
    public static final int BUNDLETYPE_SCENE_RECOG = 3;
    public static final int BUNDLETYPE_SCENE_SEMANTIC_SEG = 2;
    public static final int DETECT_BODYNUM = 4;
    public static final int DETECT_FACENUM = 2;
    public static final int DETECT_FACESORT = 32;
    public static final int DETECT_LANDMARKS = 16;
    public static final int DETECT_LANDMARKS75 = 64;
    public static final int DETECT_PETNUM = 8;
    public static final int FUAIHUMAN_SEG_COMMON = 1;
    public static final int FUAIHUMAN_SEG_MEETING = 0;
    public static final int FUAITYPE_BACKGROUNDSEGMENTATION = 2;
    public static final int FUAITYPE_BACKGROUNDSEGMENTATION_GREEN = 128;
    public static final int FUAITYPE_FACELANDMARKS209 = 16384;
    public static final int FUAITYPE_FACELANDMARKS239 = 32768;
    public static final int FUAITYPE_FACELANDMARKS75 = 8192;
    public static final int FUAITYPE_FACEPROCESSOR = 256;
    public static final long FUAITYPE_FACEPROCESSOR_ARMESHV2 = 2199023255552L;
    public static final int FUAITYPE_FACEPROCESSOR_BINDI_SEG_PROCESSOR = 67108864;
    public static final long FUAITYPE_FACEPROCESSOR_DELSPOT = 1099511627776L;
    public static final int FUAITYPE_FACEPROCESSOR_DETECT_LANDMARKS = 8388608;
    public static final long FUAITYPE_FACEPROCESSOR_DISNEYGAN = 281474976710656L;
    public static final long FUAITYPE_FACEPROCESSOR_EMOTION_RECOGNIZER = 140737488355328L;
    public static final long FUAITYPE_FACEPROCESSOR_EXPRESSION_RECOGNIZER = 70368744177664L;
    public static final long FUAITYPE_FACEPROCESSOR_FACECAPTURE = 4398046511104L;
    public static final long FUAITYPE_FACEPROCESSOR_FACECAPTURE_TONGUETRACKING = 8796093022208L;
    public static final int FUAITYPE_FACEPROCESSOR_FACEID = 134217728;
    public static final long FUAITYPE_FACEPROCESSOR_FACEOCCUSEGMENT = 274877906944L;
    public static final long FUAITYPE_FACEPROCESSOR_HAIRSEGMENTATION = 17592186044416L;
    public static final long FUAITYPE_FACEPROCESSOR_HEADSEGMENTATION = 35184372088832L;
    public static final int FUAITYPE_FACEPROCESSOR_IMAGE_BEAUTY = 65536;
    public static final long FUAITYPE_FACEPROCESSOR_LIPSOCCUSEGMENT = 137438953472L;
    public static final int FUAITYPE_FACEPROCESSOR_RESTORE = 16777216;
    public static final long FUAITYPE_FACEPROCESSOR_SKINSEGMENT = 549755813888L;
    public static final int FUAITYPE_FACE_ATTRIBUTE_PROCESSOR = 4096;
    public static final int FUAITYPE_FACE_RECOGNIZER = 1024;
    public static final int FUAITYPE_HAIRSEGMENTATION = 4;
    public static final int FUAITYPE_HANDGESTURE = 8;
    public static final int FUAITYPE_HANDPROCESSOR = 16;
    public static final int FUAITYPE_HUMANPOSE2D = 64;
    public static final int FUAITYPE_HUMAN_PROCESSOR = 512;
    public static final long FUAITYPE_HUMAN_PROCESSOR_2D_DANCE = 2147483648L;
    public static final long FUAITYPE_HUMAN_PROCESSOR_2D_IMGSLIM = 34359738368L;
    public static final int FUAITYPE_HUMAN_PROCESSOR_2D_SELFIE = 1073741824;
    public static final long FUAITYPE_HUMAN_PROCESSOR_2D_SLIM = 4294967296L;
    public static final long FUAITYPE_HUMAN_PROCESSOR_3D_DANCE = 17179869184L;
    public static final long FUAITYPE_HUMAN_PROCESSOR_3D_SELFIE = 8589934592L;
    public static final int FUAITYPE_HUMAN_PROCESSOR_DETECT = 268435456;
    public static final int FUAITYPE_HUMAN_PROCESSOR_IMAGE_BEAUTY = 131072;
    public static final int FUAITYPE_HUMAN_PROCESSOR_SEGMENTATION = 536870912;
    public static final int FUAITYPE_IMAGE_BEAUTY = 2048;
    public static final long FUAITYPE_IMAGE_BEAUTY_UNKNOW = 68719476736L;
    public static final int FUAITYPE_ISPPROCESSOR = 262144;
    public static final int FUAITYPE_MAKEUP_TRANSFER_IMAGE_BEAUTY = 524288;
    public static final int FUAITYPE_PET_SEG_PROCESSOR = 33554432;
    public static final int FUAITYPE_PHOTOEDITOR_ROCESSOR = 4194304;
    public static final int FUAITYPE_TONGUETRACKING = 32;
    public static final int FUAITYPE_VIDEOPROCESSOR = 2097152;
    public static final int FUAITYPE_WRINKLE_REMOVER_IMAGE_BEAUTY = 1048576;
    public static final int FUAIVIDEO_CACHE = 2;
    public static final int FUAIVIDEO_EXPORT = 1;
    public static final int FUAIVIDEO_PREVIEW = 0;
    public static final int FUAI_HUMAN_SEG_CPU_COMMON = 0;
    public static final int FUAI_HUMAN_SEG_GPU_COMMON = 1;
    public static final int FUAI_HUMAN_SEG_GPU_MEETING = 2;
    public static final int FU_ADM_FLAG_ENABLE_READBACK = 2;
    public static final int FU_ADM_FLAG_EXTERNAL_OES_TEXTURE = 1;
    public static final int FU_ADM_FLAG_FLIP_X = 32;
    public static final int FU_ADM_FLAG_FLIP_Y = 64;
    public static final int FU_ADM_FLAG_I420_BUFFER = 16;
    public static final int FU_ADM_FLAG_I420_TEXTURE = 8;
    public static final int FU_ADM_FLAG_NV12_BUFFER = 262144;
    public static final int FU_ADM_FLAG_NV12_TEXTURE = 131072;
    public static final int FU_ADM_FLAG_NV21_TEXTURE = 4;
    public static final int FU_ADM_FLAG_RGBA_BUFFER = 128;
    public static final int FU_ADM_FLAG_TEXTURE_AND_READBACK_BUFFER_OPPOSITE_X = 256;
    public static final int FU_ADM_FLAG_TEXTURE_AND_READBACK_BUFFER_OPPOSITE_Y = 512;
    public static final int FU_ADM_FLAG_TEXTURE_AND_READBACK_BUFFER_ROTATE_180 = 2048;
    public static final int FU_ADM_FLAG_TEXTURE_AND_READBACK_BUFFER_ROTATE_270 = 4096;
    public static final int FU_ADM_FLAG_TEXTURE_AND_READBACK_BUFFER_ROTATE_90 = 1024;
    public static final int FU_ADM_FLAG_TEXTURE_ROTATE_180 = 16384;
    public static final int FU_ADM_FLAG_TEXTURE_ROTATE_270 = 32768;
    public static final int FU_ADM_FLAG_TEXTURE_ROTATE_90 = 8192;
    public static final int FU_FORMAT_GL_CURRENT_FRAMEBUFFER = 3;
    public static final int FU_FORMAT_I420_BUFFER = 13;
    public static final int FU_FORMAT_NV12_BUFFER = 8;
    public static final int FU_FORMAT_NV21_BUFFER = 2;
    public static final int FU_FORMAT_RGBA_BUFFER = 4;
    public static final int FU_FORMAT_RGBA_TEXTURE = 1;
    public static final int FU_IMAGE_BEAUTY_MODE_ACENCANCEL = 128;
    public static final int FU_IMAGE_BEAUTY_MODE_AUTO = 8;
    public static final int FU_IMAGE_BEAUTY_MODE_AUTO_WITHOUT_ACEN_AVER = 536870912;
    public static final int FU_IMAGE_BEAUTY_MODE_AVER = 256;
    public static final int FU_IMAGE_BEAUTY_MODE_BLUR = 64;
    public static final int FU_IMAGE_BEAUTY_MODE_BODY_SLIM = 4;
    public static final int FU_IMAGE_BEAUTY_MODE_DARKCIRCLE = 131072;
    public static final int FU_IMAGE_BEAUTY_MODE_DECREEPATTERN = 262144;
    public static final int FU_IMAGE_BEAUTY_MODE_FACE_BEAUTY = 1;
    public static final int FU_IMAGE_BEAUTY_MODE_FACE_BEAUTY_PREPROCESS = 32;
    public static final int FU_IMAGE_BEAUTY_MODE_FACE_WARP = 2;
    public static final int FU_IMAGE_BEAUTY_MODE_JILI = 65536;
    public static final int FU_IMAGE_BEAUTY_MODE_LIGHTEYE = 8192;
    public static final int FU_IMAGE_BEAUTY_MODE_MAKEUP_TRANSFER = 1073741824;
    public static final int FU_IMAGE_BEAUTY_MODE_PET_BEAUTY = Integer.MIN_VALUE;
    public static final int FU_IMAGE_BEAUTY_MODE_RED = 4096;
    public static final int FU_IMAGE_BEAUTY_MODE_RESTORE = Integer.MIN_VALUE;
    public static final int FU_IMAGE_BEAUTY_MODE_SHARPENBROWN = 16384;
    public static final int FU_IMAGE_BEAUTY_MODE_THREED = 32768;
    public static final int FU_IMAGE_BEAUTY_MODE_UNKNOWN = 0;
    public static final int FU_IMAGE_BEAUTY_MODE_UPLOAD = 16;
    public static final int FU_IMAGE_BEAUTY_MODE_WARMCOLD = 2048;
    public static final int FU_IMAGE_BEAUTY_MODE_WHITEBLACK = 1024;
    public static final int FU_IMAGE_BEAUTY_MODE_WRINKLE = 512;
    public static final int FU_IMAGE_BEAUTY_STATUS_BITMAP_8888 = 11;
    public static final int FU_IMAGE_BEAUTY_STATUS_BITMAP_INFO = 10;
    public static final int FU_IMAGE_BEAUTY_STATUS_BITMAP_LOCK = 12;
    public static final int FU_IMAGE_BEAUTY_STATUS_CACHE_DIR_EMPTY = 13;
    public static final int FU_IMAGE_BEAUTY_STATUS_CONTROLLER_NOTFOUND = 3;
    public static final int FU_IMAGE_BEAUTY_STATUS_ERROR_PROCESS_MODE = 14;
    public static final int FU_IMAGE_BEAUTY_STATUS_ERROR_REF_FACE_GREATER_THAN_ONE = 16;
    public static final int FU_IMAGE_BEAUTY_STATUS_ERROR_REF_FACE_ZERO = 15;
    public static final int FU_IMAGE_BEAUTY_STATUS_GL_NOTSUPPORT = 2;
    public static final int FU_IMAGE_BEAUTY_STATUS_INVALID_ARGUMENT = 7;
    public static final int FU_IMAGE_BEAUTY_STATUS_LAYERS_ERROR = 6;
    public static final int FU_IMAGE_BEAUTY_STATUS_MUTIL_FACE_DETECT = 5;
    public static final int FU_IMAGE_BEAUTY_STATUS_NO_CACHE = 17;
    public static final int FU_IMAGE_BEAUTY_STATUS_NO_CONTEXT = 1;
    public static final int FU_IMAGE_BEAUTY_STATUS_NO_FACE_DETECT = 4;
    public static final int FU_IMAGE_BEAUTY_STATUS_NULLPTR = 9;
    public static final int FU_IMAGE_BEAUTY_STATUS_OK = 0;
    public static final int FU_IMAGE_BEAUTY_STATUS_UNINITIALIZED = 8;
    public static final int FU_IMAGE_BEAUTY_STATUS_UNKNOWN = -1;
    public static final int FU_IMAGE_CHINA = 0;
    public static final int FU_IMAGE_EDITOR_MODE_ART_PHOTO = 67108864;
    public static final int FU_IMAGE_EDITOR_MODE_AUTOCROP = 8388608;
    public static final int FU_IMAGE_EDITOR_MODE_GLOBAL_ENHANCE = 1048576;
    public static final int FU_IMAGE_EDITOR_MODE_HDR = 4194304;
    public static final int FU_IMAGE_EDITOR_MODE_ID_PHOTO = 16777216;
    public static final int FU_IMAGE_EDITOR_MODE_LIQUIFY = 268435456;
    public static final int FU_IMAGE_EDITOR_MODE_LOCAL_ENHANCE = 2097152;
    public static final int FU_IMAGE_EDITOR_MODE_PORTRAIT_COLOR_FOCUS = 134217728;
    public static final int FU_IMAGE_EDITOR_MODE_SCENE_BEAUTIFY = 33554432;
    public static final int FU_IMAGE_EDITOR_MODE_TRANSFORM = 524288;
    public static final int FU_IMAGE_INDIA = 1;
    public static final int FU_IMAGE_UNDOREDO_MODE_GAP_EQUAL_TO_ZERO = 1;
    public static final int FU_IMAGE_UNDOREDO_MODE_GAP_GREATER_THAN_ZERO = 0;
    public static final int FU_NOCLEAR_CURRENT_FRAMEBUFFER = 65536;
    public static final int FU_ROTATION_MODE_0 = 0;
    public static final int FU_ROTATION_MODE_180 = 2;
    public static final int FU_ROTATION_MODE_270 = 3;
    public static final int FU_ROTATION_MODE_90 = 1;
    public static final int FU_SETUP_TYPE_OFFLINE_GET_ANDROID_ID_IN_JAVA = 2;
    public static final int FU_SETUP_TYPE_OFFLINE_GET_SERIAL_ID_IN_JNI = 0;
    public static final int FU_SETUP_TYPE_OFFLINE_GET_SERIAL_ID_IN_NATIVE = 1;
    public static final int GLASS_WINDOW = 5;
    public static final int GLOBAL_ENHANCE = 0;
    public static final int LOW_POLY = 7;
    public static final int PHOTOEDITOR_FUAITYPE_ARTS_PHOTO = 2;
    public static final int PHOTOEDITOR_FUAITYPE_ID_PHOTO = 4;
    public static final int PHOTOEDITOR_FUAITYPE_LIGHT_RECOG = 3;
    public static final int PHOTOEDITOR_FUAITYPE_SCENE_RECOG = 1;
    public static final int PHOTOEDITOR_FUAITYPE_SCENE_SEMANTIC_SEG = 0;
    public static final int PHOTOEDITOR_FUAITYPE_UNKNOW = -1;
    public static final int RGB_SHIFT = 0;
    public static final int SCENE_BEAUTY = 1;
    public static final int SHUTTER = 2;
    public static final int SKETCH = 3;
    public static final int WATER_COLOR = 4;
    public static final int WAVE = 1;
    public static final int WOODCUT = 6;

    public static class FliterCache {
        public byte[] cache_data = new byte[0];
    }

    public static class LoadConfig {
        private static boolean sLoadFUAI = true;
        private static boolean sLoadedLibrary = false;

        private LoadConfig() {
        }

        public static void disableLoadFUAI(boolean z) {
            sLoadFUAI = z;
        }

        public static void disableLoadLibrary(boolean z) {
            sLoadedLibrary = z;
        }

        public static void loadLibrary(String str) {
            if (sLoadedLibrary || str == null) {
                return;
            }
            if (sLoadFUAI) {
                System.load(new File(str, "libfuai.so").getAbsolutePath());
            }
            System.load(new File(str, "libCNamaSDK.so").getAbsolutePath());
            sLoadedLibrary = true;
        }

        public static void loadLibraryWithPostfix(String str) {
            if (sLoadedLibrary || str == null) {
                return;
            }
            if (sLoadFUAI) {
                System.loadLibrary("fuai".concat(str));
            }
            System.loadLibrary("CNamaSDK".concat(str));
            sLoadedLibrary = true;
        }
    }

    public interface OnHandGestureListener {
        void OnHandGestureDetect(int i10);
    }

    public interface OnItemTriggerListener {
        void OnItemTrigger(int i10, int i11);
    }

    public static class RotatedImage {
        public byte[] mData = null;
        public int mWidth = 0;
        public int mHeight = 0;
        private byte[] mData1 = null;
        private byte[] mData2 = null;

        static {
            new RotatedImage().initJniFiledIDs();
        }

        private native void initJniFiledIDs();
    }

    public static class SplitViewInfo {
        public float mCropRatioTop;
        public byte[] mImage;
        public boolean mIsImageFirst;
        public boolean mIsVertical;
        public int mMarginInPixel;
        public int mOutH;
        public int mOutW;
        public int mRotationModeBeforeCrop;
        public int mTex;
        public int mUseBlackEdge;
        public float mView0Ratio;

        static {
            new SplitViewInfo().initJniFiledIDs();
        }

        private native void initJniFiledIDs();
    }

    static {
        if (LoadConfig.sLoadedLibrary) {
            return;
        }
        if (LoadConfig.sLoadFUAI) {
            System.loadLibrary("fuai");
        }
        System.loadLibrary("CNamaSDK");
    }

    public static native int AutoCropGetCropParams(Bitmap bitmap, float[] fArr, int[] iArr);

    public static native String AutoCropGetVersion();

    public static native int AutoCropInit(String str);

    public static native int AutoCropRelease();

    public static native int BeautyEnhanceGetresult(Bitmap bitmap, String str);

    public static native int BeautyEnhanceGetscene();

    public static native int BeautyEnhanceInit(String str);

    public static native int BeautyEnhancePreprocess(Bitmap bitmap, int i10, int i11, int i12);

    public static native void BeautyEnhanceRelease();

    public static native int[] IDPhotoCalculateWH(int i10, int i11, int[] iArr, int i12);

    public static native int IDPhotoChangeBgColor(Bitmap bitmap, int i10, int[] iArr, Bitmap bitmap2);

    public static native void IDPhotoDestory();

    public static native int[][] IDPhotoFaceDetect(Bitmap bitmap);

    public static native int IDPhotoInit(String str);

    public static native int IDPhotonativeMerge(Bitmap bitmap, int i10, int[] iArr, Bitmap bitmap2);

    public static native int LoadBundle(AssetManager assetManager, String str, int i10);

    public static native int LoadBundleLocal(String str, int i10);

    public static native int MagicFliterDestory();

    public static native int MagicFliterEnhancer(Bitmap bitmap, int i10);

    public static native int MagicFliterInit(String str);

    public static native int MagicFliterPreprocess(Bitmap bitmap, boolean z, FliterCache fliterCache);

    public static native int PortraitSegmenterInit(String str);

    public static native void PortraitSegmenterRelease();

    public static native int PortraitSegmenterSegEnhance(Bitmap bitmap);

    public static native void PortraitSegmenterUpdateEnhance(Bitmap bitmap, float f);

    public static native int fuAuthCountWithAPIName(String str);

    @Deprecated
    public static native int fuAvatarBindItems(int i10, int[] iArr, int[] iArr2);

    public static native int fuAvatarToCurrentFBO(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, float[] fArr5, int i10, int i11, int i12, int i13, int[] iArr, int i14, int i15);

    public static native int fuAvatarToImage(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, int i10, int i11, int i12, int i13, int[] iArr, int i14, int i15, int i16, byte[] bArr);

    public static native int fuAvatarToImage(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, float[] fArr5, int i10, int i11, int i12, int i13, int[] iArr, int i14, int i15, int i16, byte[] bArr);

    public static native int fuAvatarToTexture(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, int i10, int i11, int i12, int i13, int[] iArr, int i14);

    public static native int fuAvatarToTexture(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, float[] fArr5, int i10, int i11, int i12, int i13, int[] iArr, int i14);

    public static native int fuAvatarToTextureWithTrans(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, float[] fArr5, int i10, int i11, int i12, int i13, int[] iArr, int i14);

    @Deprecated
    public static native int fuAvatarUnbindItems(int i10, int[] iArr);

    public static native int fuBeautifyImage(int i10, int i11, int i12, int i13, int i14, int[] iArr);

    public static native int fuBindItems(int i10, int[] iArr);

    public static native int fuBindItemsToInstance(int i10, int[] iArr);

    public static native int fuBindItemsToScene(int i10, int[] iArr);

    public static native int fuCheckDebugItem(byte[] bArr);

    public static native int fuCheckGLError();

    public static native void fuClearCacheResource();

    public static native void fuClearItemTriggerListener();

    public static native int fuClearReadbackRelated();

    public static native void fuCreateEGLContext();

    public static native int fuCreateInstance(int i10);

    public static native int fuCreateItemFromPackage(byte[] bArr);

    public static native int fuCreateScene();

    public static native int fuCreateTexForItem(int i10, String str, byte[] bArr, int i11, int i12);

    public static native int fuDeleteTexForItem(int i10, String str);

    public static native void fuDestroyAllItems();

    public static native int fuDestroyInstance(int i10);

    public static native void fuDestroyItem(int i10);

    public static native void fuDestroyLibData();

    public static native int fuDestroyScene(int i10);

    public static native void fuDisableBoostWithEGLImage();

    public static native void fuDone();

    public static native void fuDualInputToFBO(byte[] bArr, int i10, int i11, int i12, int i13, int i14, int[] iArr, int i15);

    public static native int fuDualInputToTexture(long j, int i10, int i11, int i12, int i13, int i14, int[] iArr);

    public static native int fuDualInputToTexture(byte[] bArr, int i10, int i11, int i12, int i13, int i14, int[] iArr);

    public static native int fuDualInputToTexture(byte[] bArr, int i10, int i11, int i12, int i13, int i14, int[] iArr, int i15, int i16, byte[] bArr2);

    public static native int fuDualInputToTexture(byte[] bArr, int i10, int i11, int i12, int i13, int i14, int[] iArr, int i15, int i16, byte[] bArr2, int i17, int i18);

    public static native int fuDualInputToTextureMasked(byte[] bArr, int i10, int i11, int i12, int i13, int i14, int[] iArr, int[] iArr2);

    public static native int fuEnableARMode(int i10, boolean z);

    public static native int fuEnableBackgroundAnimationLoop(int i10, int i11, boolean z);

    public static native int fuEnableBackgroundColor(int i10, boolean z);

    public static native int fuEnableBinaryShaderProgram(boolean z);

    public static native int fuEnableBloom(int i10, boolean z);

    public static native int fuEnableCameraAnimation(int i10, boolean z);

    public static native int fuEnableCameraAnimationInternalLerp(int i10, boolean z);

    public static native int fuEnableControlTimeUpdate(int i10, boolean z);

    public static native int fuEnableDynamicBone(int i10, boolean z);

    public static native int fuEnableFaceProcessor(int i10, boolean z);

    public static native int fuEnableGroundReflection(int i10, boolean z);

    public static native int fuEnableHDRRGBA16F(int i10, boolean z);

    public static native int fuEnableHandDetetor(int i10, boolean z);

    public static native int fuEnableHumanProcessor(int i10, boolean z);

    public static native int fuEnableInstanceAnimationInternalLerp(int i10, boolean z);

    public static native int fuEnableInstanceDynamicBoneRootRotationSpeedLimitMode(int i10, boolean z);

    public static native int fuEnableInstanceDynamicBoneRootTranslationSpeedLimitMode(int i10, boolean z);

    public static native int fuEnableInstanceDynamicBoneTeleportMode(int i10, boolean z);

    public static native int fuEnableInstanceExpressionBlend(int i10, boolean z);

    public static native int fuEnableInstanceFaceProcessorRotateHead(int i10, boolean z);

    public static native int fuEnableInstanceFacepupMode(int i10, boolean z);

    public static native int fuEnableInstanceFocusEyeToCamera(int i10, boolean z);

    public static native int fuEnableInstanceHideNeck(int i10, boolean z);

    public static native int fuEnableInstanceModelMatToBone(int i10, boolean z);

    public static native int fuEnableInstanceRotateWithoutAnimationTranslation(int i10, int i11);

    public static native int fuEnableInstanceSelfCollision(int i10, int i11);

    public static native int fuEnableInstanceSingleDynamicBone(int i10, int i11, boolean z);

    public static native int fuEnableInstanceSingleMeshVisible(int i10, int i11, boolean z);

    public static native int fuEnableInstanceUseFaceBeautyOrder(int i10, boolean z);

    public static native int fuEnableInstanceVisible(int i10, int i11);

    public static native int fuEnableInstanceVisible(int i10, boolean z);

    public static native int fuEnableLowQualityLighting(int i10, boolean z);

    public static native int fuEnableLowResolutionTexture(int i10, boolean z);

    public static native int fuEnableOrthogonalProjection(int i10, boolean z);

    public static native int fuEnableOuterMVPMatrix(int i10, boolean z);

    public static native int fuEnableRender(int i10, boolean z);

    public static native int fuEnableRenderCamera(int i10, boolean z);

    public static native int fuEnableRiggingBVHInputProcessor(int i10, boolean z);

    public static native int fuEnableShadow(int i10, boolean z);

    public static native void fuEnableVideoHash(boolean z);

    public static native float fuFaceProcessorGetConfidenceScore(int i10);

    public static native int fuFaceProcessorGetNumResults();

    public static native int fuFaceProcessorGetResultFaceOcclusion(int i10);

    public static native int fuFaceProcessorGetResultHairMask(int i10, float[] fArr);

    public static native int fuFaceProcessorGetResultHairMaskHeight(int i10);

    public static native int fuFaceProcessorGetResultHairMaskWidth(int i10);

    public static native int fuFaceProcessorGetResultHeadMask(int i10, float[] fArr);

    public static native int fuFaceProcessorGetResultHeadMaskHeight(int i10);

    public static native int fuFaceProcessorGetResultHeadMaskWidth(int i10);

    public static native void fuFaceProcessorSetDetectSmallFace(int i10);

    public static native void fuFaceProcessorSetFaceLandmarkQuality(int i10);

    public static native void fuFaceProcessorSetMinFaceRatio(float f);

    public static native int fuFaceProcessorSetUseCaptureEyeLookCam(int i10);

    public static native void fuForceSetInputPbo(boolean z);

    public static native int fuGetCameraAnimationFrameNumber(int i10);

    public static native int fuGetCameraAnimationFrameNumber(int i10, int i11);

    public static native float fuGetCameraAnimationProgress(int i10, int i11);

    public static native float fuGetCameraAnimationTransitionProgress(int i10);

    public static native String fuGetCommitTime();

    public static native int fuGetCurrentRotationMode();

    public static native int fuGetFaceIdentifier(int i10);

    public static native int fuGetFaceInfo(int i10, String str, float[] fArr);

    public static native int fuGetFaceInfo(int i10, String str, int[] iArr);

    public static native int fuGetFaceInfoRotated(int i10, String str, float[] fArr);

    public static native int fuGetFaceInfoRotated(int i10, String str, int[] iArr);

    public static native float fuGetFaceProcessorFov();

    public static native int fuGetFaceTransferTexID();

    public static native int fuGetGetProjectionMatrixZfar(int i10);

    public static native int fuGetInstanceAnimationFrameNumber(int i10, int i11);

    public static native float fuGetInstanceAnimationProgress(int i10, int i11);

    public static native float fuGetInstanceAnimationTransitionProgress(int i10, int i11);

    public static native int fuGetInstanceBoneScreenCoordinate(int i10, String str, float[] fArr);

    public static native int fuGetInstanceBoundingBoxScreenCoordinate(int i10, float[] fArr);

    public static native int fuGetInstanceBoundingBoxScreenCoordinateWithOffset(int i10, float[] fArr, float f, float f10, float f11, float f12, float f13, float f14);

    public static native int fuGetInstanceFaceVertexScreenCoordinate(int i10, int i11, float[] fArr);

    public static native int fuGetInstanceFacepupArray(int i10, float[] fArr);

    public static native float fuGetInstanceFacepupOriginalValue(int i10, String str);

    public static native int fuGetInstanceHeadCenterScreenCoordinate(int i10, float[] fArr);

    public static native int fuGetInstanceLocalBoundingBox(int i10, float[] fArr);

    public static native int fuGetInstancePosition(int i10, float[] fArr);

    public static native int fuGetInstanceSkinColorIndex(int i10);

    public static native int fuGetLogLevel();

    public static native int fuGetModuleCode(int i10);

    public static native int fuGetSystemError();

    public static native String fuGetSystemErrorString(int i10);

    public static native String fuGetVersion();

    public static native int fuHandDetectorGetResultGestureType(int i10);

    public static native int fuHandDetectorGetResultHandRect(int i10, float[] fArr);

    public static native float fuHandDetectorGetResultHandScore(int i10);

    public static native int fuHandDetectorGetResultNumHands();

    public static native int fuHasFace();

    public static native void fuHexagonInitWithPath(String str);

    public static native void fuHexagonTearDown();

    public static native float fuHumanActionMatchDistance(float[] fArr, float[] fArr2);

    public static native float fuHumanActionMatchLeftRightHandDistance(float[] fArr, float[] fArr2, boolean z);

    public static native float fuHumanProcessorGetFov();

    public static native int fuHumanProcessorGetNumResults();

    public static native float fuHumanProcessorGetResultActionScore(int i10);

    public static native int fuHumanProcessorGetResultActionType(int i10);

    public static native int fuHumanProcessorGetResultBVHMotionFrameOutput(int i10, float[] fArr);

    public static native int fuHumanProcessorGetResultHumanMask(int i10, float[] fArr);

    public static native int fuHumanProcessorGetResultHumanMaskHeight(int i10);

    public static native int fuHumanProcessorGetResultHumanMaskWidth(int i10);

    public static native int fuHumanProcessorGetResultJoint2ds(int i10, float[] fArr);

    public static native int fuHumanProcessorGetResultJoint3ds(int i10, float[] fArr);

    public static native int fuHumanProcessorGetResultPofJoint2dScores(int i10, float[] fArr);

    public static native int fuHumanProcessorGetResultPofJoint2ds(int i10, float[] fArr);

    public static native int fuHumanProcessorGetResultRect(int i10, float[] fArr);

    public static native int fuHumanProcessorGetResultTrackId(int i10);

    public static native void fuHumanProcessorReset();

    public static native int fuHumanProcessorSet3DScene(int i10, int i11);

    public static native void fuHumanProcessorSetAvatarAnimFilterParams(int i10, float f, float f10);

    public static native void fuHumanProcessorSetBVHInPlaneMirrorType(int i10, int i11);

    public static native void fuHumanProcessorSetBVHInPlaneRotation(int i10, int i11);

    public static native void fuHumanProcessorSetEnableBVHOutput(int i10, boolean z);

    public static native void fuHumanProcessorSetFov(float f);

    public static native void fuHumanProcessorSetMaxHumans(int i10);

    public static native Bitmap fuImageBeautyBitmap(int i10, int i11);

    public static native void fuImageBeautyBreakMission(int[] iArr);

    public static native int fuImageBeautyClearMemory(int[] iArr);

    public static native int fuImageBeautyConvertRGBA2NV21(int i10, int i11, byte[] bArr, int i12, int i13, byte[] bArr2);

    public static native int fuImageBeautyConvertRGBA2NV21WithBitmap(Bitmap bitmap, int i10, int i11, byte[] bArr);

    public static native int fuImageBeautyCreateTexture(int i10, int i11, int i12, byte[] bArr, int i13, byte[] bArr2, int i14, byte[] bArr3, int i15, float f);

    public static native int fuImageBeautyCreateTextureCoverPreview(int i10, int i11, int i12, byte[] bArr, int i13, byte[] bArr2, int i14, byte[] bArr3, int i15, float f);

    public static native int fuImageBeautyCreateTextureWithBitmap(Bitmap bitmap, float f);

    public static native int fuImageBeautyCreateTextureWithBitmapCoverPreview(Bitmap bitmap, float f);

    public static native Bitmap fuImageBeautyGetCurrentResultBitmap(int i10, int i11, int[] iArr);

    public static native int fuImageBeautyGetCurrentResultWithBitmap(Bitmap bitmap, int[] iArr);

    public static native int fuImageBeautyGetInfo(int i10, String str, float[] fArr);

    public static native int fuImageBeautyGetLastResultTexture();

    public static native int fuImageBeautyGetOriginTexture();

    public static native String fuImageBeautyGetParam(int[] iArr, String str);

    public static native byte[] fuImageBeautyGetPreprocessCache(int[] iArr);

    public static native int fuImageBeautyGetResult(int i10, int i11, int i12, int i13, byte[] bArr, int i14, byte[] bArr2, int i15, byte[] bArr3, int i16, int[] iArr);

    public static native Bitmap fuImageBeautyGetResultBitmap(int i10, int i11, int[] iArr);

    public static native int[] fuImageBeautyGetResultSize(int[] iArr);

    public static native int fuImageBeautyGetResultWithBitmap(Bitmap bitmap, int[] iArr);

    public static native float fuImageBeautyIspBokehGetAttr();

    public static native void fuImageBeautyIspBokehSetAttr(float f);

    public static native int fuImageBeautyIspBokehWithBitmap(Bitmap bitmap, Bitmap bitmap2);

    public static native Bitmap fuImageBeautyLoadBitmapFromPath(String str, int i10, int i11, int i12);

    public static native int fuImageBeautyLoadCache(int[] iArr);

    public static native void fuImageBeautyMakeupTransferLoadCache(int[] iArr, String str);

    public static native String fuImageBeautyMakeupTransferSaveCache(int[] iArr);

    public static native int fuImageBeautyMakeupTransferWithBitmap(Bitmap bitmap, int[] iArr);

    public static native void fuImageBeautyNewPic();

    public static native int fuImageBeautyPreProcess(int i10, int i11, int i12, int i13, byte[] bArr, int i14, byte[] bArr2, int i15, byte[] bArr3, int i16, int[] iArr, int i17, int i18, int i19, float f);

    public static native void fuImageBeautyPreProcessForImageInfo(int i10, int i11, int i12, int i13, byte[] bArr, int i14, byte[] bArr2, int i15, byte[] bArr3, int i16, float f);

    public static native void fuImageBeautyPreProcessForImageInfoWithBitmap(Bitmap bitmap, float f);

    public static native int fuImageBeautyPreProcessWithBitmap(Bitmap bitmap, int[] iArr, int i10, int i11, int i12, float f);

    public static native int fuImageBeautyPreprocessWithBitMapAndCache(Bitmap bitmap, int[] iArr, int i10, int i11, int i12, float f, byte[] bArr);

    public static native int fuImageBeautyPreprocessWithCache(int i10, int i11, int i12, int i13, byte[] bArr, int i14, byte[] bArr2, int i15, byte[] bArr3, int i16, int[] iArr, int i17, int i18, int i19, float f, byte[] bArr4);

    public static native int fuImageBeautyPreview(int[] iArr);

    public static native void fuImageBeautyReStoreMission(int[] iArr);

    public static native void fuImageBeautyResetPic(byte[] bArr, int i10, int i11, int i12, byte[] bArr2, int i13, int i14);

    public static native int fuImageBeautySaveBitmapToPath(Bitmap bitmap, String str, int i10);

    public static native int fuImageBeautySaveCache(int[] iArr);

    public static native int fuImageBeautySaveResultToPath(String str, int[] iArr);

    public static native int fuImageBeautySetAttributePath(String str);

    public static native int fuImageBeautySetCacheDir(String str);

    public static native int fuImageBeautySetFaceBeautyPath(String str);

    public static native int fuImageBeautySetMakeupTransferCacheDir(String str);

    public static native void fuImageBeautySetNation(int i10);

    public static native int fuImageBeautySetParam(int[] iArr, String str, String str2);

    public static native int fuImageBeautySetUndoRedoMode(int[] iArr, int i10);

    public static native float[] fuImageGetImageInfo(Bitmap bitmap, int i10, int i11);

    public static native int[] fuImageGetImageScaleWH();

    public static native int fuIsAIModelLoaded(int i10);

    public static native int fuIsAIModelLoadedPhotoEditor(int i10);

    public static native int fuIsGLPrepared(int[] iArr);

    public static native int fuIsLibraryInit();

    public static native int fuIsTracking();

    public static native double fuItemGetParam(int i10, String str);

    public static native String fuItemGetParamString(int i10, String str);

    public static native double[] fuItemGetParamdv(int i10, String str);

    public static native float[] fuItemGetParamfv(int i10, String str);

    public static native byte[] fuItemGetParamu8v(int i10, String str);

    public static native int fuItemSetParam(int i10, String str, double d);

    public static native int fuItemSetParam(int i10, String str, String str2);

    public static native int fuItemSetParam(int i10, String str, double[] dArr);

    public static native int fuItemSetParamPtr(int i10, String str, float[] fArr);

    public static native int fuItemSetParamu64(int i10, String str, long j);

    public static native int fuItemSetParamu8v(int i10, String str, byte[] bArr, int i11);

    public static native int fuLoadAIModelFromPackage(byte[] bArr, int i10);

    public static native int fuLoadAIModelFromPackagePhotoEditor(byte[] bArr, int i10);

    public static native int fuLoadTongueModel(byte[] bArr);

    public static native void fuOnCameraChange();

    public static native void fuOnDeviceLost();

    public static native void fuOnDeviceLostSafe();

    public static native int fuOpenFileLog(String str, int i10, int i11);

    public static native int fuPauseCameraAnimation(int i10);

    public static native int fuPauseInstanceAnimation(int i10);

    public static native int fuPauseTimeUpdate(int i10, boolean z);

    public static native int fuPlayCameraAnimation(int i10, int i11);

    public static native int fuPlayCameraAnimationOnce(int i10, int i11);

    public static native int fuPlayInstanceAnimation(int i10, int i11);

    public static native int fuPlayInstanceAnimationOnce(int i10, int i11);

    public static native void fuPrepareGLResource(int[] iArr);

    public static native int fuPreprocessAIModelFromPackage(byte[] bArr, int i10);

    public static native void fuProcessorChangeModel(int i10);

    public static native int fuProfileGetNumTimers();

    public static native long fuProfileGetTimerAverage(int i10);

    public static native long fuProfileGetTimerCount(int i10);

    public static native long fuProfileGetTimerMax(int i10);

    public static native long fuProfileGetTimerMin(int i10);

    public static native String fuProfileGetTimerName(int i10);

    public static native int fuProfileResetAllTimers();

    public static native void fuReadPixelsRGBA2NV21(int i10, int i11, int i12, int i13, byte[] bArr);

    public static native int fuRecordMemoryUsage(String str);

    public static native int fuRefreshInstanceDynamicBone(int i10, int i11);

    public static native int fuReleaseAIModel(int i10);

    public static native int fuReleaseAIModelPhotoEditor(int i10);

    public static native void fuReleaseEGLContext();

    public static native void fuReleaseGLResources();

    public static native void fuReleaseGLResourcesSafe();

    public static native int fuRenderBundlesWithCamera(byte[] bArr, int i10, int i11, int i12, int i13, int i14, int[] iArr);

    public static native int fuRenderBundlesWithCamera(byte[] bArr, int i10, int i11, int i12, int[] iArr);

    public static native int fuRenderBundlesWithCamera(byte[] bArr, int i10, int i11, int i12, int[] iArr, byte[] bArr2);

    public static native int fuRenderBundlesWithCameraToCurrentFBO(byte[] bArr, int i10, int i11, int i12, int i13, int i14, int[] iArr, int i15);

    public static native int fuRenderBundlesWithCameraToCurrentFBO(byte[] bArr, int i10, int i11, int i12, int i13, int[] iArr, int i14);

    public static native int fuRenderDualInput(int i10, int i11, int i12, int[] iArr, int i13, int i14, byte[] bArr, int i15, int i16, int i17, byte[] bArr2);

    public static native int fuRenderI420ImageToTexture(byte[] bArr, int i10, int i11, int i12, int[] iArr);

    public static native int fuRenderImg(int i10, int i11, int i12, int[] iArr, int i13, byte[] bArr, int i14, int i15, int i16, byte[] bArr2);

    public static native int fuRenderNV21ImageToTexture(byte[] bArr, int i10, int i11, int i12, int[] iArr);

    public static native int fuRenderTexture(int i10, int i11, int i12, int[] iArr, int i13, int i14);

    public static native int fuRenderToI420Image(long j, int i10, int i11, int i12, int[] iArr, int i13);

    public static native int fuRenderToI420Image(byte[] bArr, int i10, int i11, int i12, int[] iArr);

    public static native int fuRenderToI420Image(byte[] bArr, int i10, int i11, int i12, int[] iArr, int i13);

    public static native int fuRenderToI420Image(byte[] bArr, int i10, int i11, int i12, int[] iArr, int i13, int i14, int i15, byte[] bArr2);

    public static native int fuRenderToI420Image(byte[] bArr, int i10, int i11, int i12, int[] iArr, int i13, int i14, int i15, byte[] bArr2, int i16, int i17);

    public static native int fuRenderToI420ImageMasked(byte[] bArr, int i10, int i11, int i12, int[] iArr, int[] iArr2);

    public static native int fuRenderToNV21Image(long j, int i10, int i11, int i12, int[] iArr, int i13);

    public static native int fuRenderToNV21Image(byte[] bArr, int i10, int i11, int i12, int[] iArr);

    public static native int fuRenderToNV21Image(byte[] bArr, int i10, int i11, int i12, int[] iArr, int i13);

    public static native int fuRenderToNV21Image(byte[] bArr, int i10, int i11, int i12, int[] iArr, int i13, int i14, int i15, byte[] bArr2);

    public static native int fuRenderToNV21Image(byte[] bArr, int i10, int i11, int i12, int[] iArr, int i13, int i14, int i15, byte[] bArr2, int i16, int i17);

    public static native int fuRenderToNV21ImageMasked(byte[] bArr, int i10, int i11, int i12, int[] iArr, int[] iArr2);

    public static native int fuRenderToRgbaImage(long j, int i10, int i11, int i12, int[] iArr, int i13);

    public static native int fuRenderToRgbaImage(byte[] bArr, int i10, int i11, int i12, int[] iArr);

    public static native int fuRenderToRgbaImage(byte[] bArr, int i10, int i11, int i12, int[] iArr, int i13);

    public static native int fuRenderToRgbaImage(byte[] bArr, int i10, int i11, int i12, int[] iArr, int i13, int i14, int i15, byte[] bArr2);

    public static native int fuRenderToRgbaImage(byte[] bArr, int i10, int i11, int i12, int[] iArr, int i13, int i14, int i15, byte[] bArr2, int i16, int i17);

    public static native int fuRenderToTexture(int i10, int i11, int i12, int i13, int[] iArr, int i14);

    public static native int fuRenderToTexture(int i10, int i11, int i12, int i13, int[] iArr, int i14, byte[] bArr, int i15, int i16);

    public static native int fuRenderToTexture(int i10, int i11, int i12, int i13, int[] iArr, int i14, byte[] bArr, int i15, int i16, int i17, int i18);

    public static native int fuRenderToYUVImage(long j, long j10, long j11, int i10, int i11, int i12, int i13, int i14, int i15, int[] iArr);

    public static native int fuRenderToYUVImage(byte[] bArr, byte[] bArr2, byte[] bArr3, int i10, int i11, int i12, int i13, int i14, int i15, int[] iArr);

    public static native int fuRenderToYUVImage(byte[] bArr, byte[] bArr2, byte[] bArr3, int i10, int i11, int i12, int i13, int i14, int i15, int[] iArr, int i16);

    public static native int fuRenderYUV(int i10, int i11, int i12, int[] iArr, int i13, byte[] bArr, byte[] bArr2, byte[] bArr3, int i14, int i15, int i16, boolean z);

    public static native int fuRenderYUV2(int i10, int i11, int i12, int[] iArr, int i13, byte[] bArr, byte[] bArr2, byte[] bArr3, int i14, int i15, int i16, boolean z, byte[] bArr4, byte[] bArr5, byte[] bArr6);

    public static native int fuResetBackgroundAnimation(int i10, int i11);

    public static native int fuResetCameraAnimation(int i10);

    public static native int fuResetInstanceAnimation(int i10);

    public static native int fuResetInstanceDynamicBone(int i10, int i11);

    public static native int fuResetInstanceFaceProcessorFilter(int i10);

    public static native int fuResetInstanceHead(int i10);

    public static native int fuResetLightAnimation(int i10);

    public static native int fuRiggingBVHInputProcessorFeedMotionFrame(int i10, float[] fArr);

    public static native int fuRiggingBVHInputProcessorSetConfig(int i10, byte[] bArr, byte[] bArr2);

    public static native int fuRotateImage(RotatedImage rotatedImage, byte[] bArr, int i10, int i11, int i12, int i13, int i14, int i15);

    public static native void fuRunCache();

    @Deprecated
    public static native int fuSetAsyncTrackFace(int i10);

    public static native int fuSetBackgroundColor(int i10, int i11, int i12, int i13, int i14);

    public static native int fuSetBackgroundParams(int i10, int i11, float f, float f10, float f11, float f12, boolean z, int i12);

    public static native int fuSetBinaryShaderProgramDirectory(String str);

    public static native void fuSetCacheDirectory(String str);

    public static native int fuSetCameraAnimationTransitionTime(int i10, float f);

    public static native int fuSetCropFreePixel(int i10, int i11, int i12, int i13);

    public static native int fuSetCropState(int i10);

    public static native int fuSetCurrentScene(int i10);

    public static native int fuSetCurrentTime(int i10, float f);

    public static native void fuSetDefaultRotationMode(int i10);

    public static native void fuSetDeviceOrientation(int i10);

    public static native void fuSetFaceDelayLeaveEnable(boolean z);

    public static native void fuSetFaceDelayLeaveFrameNum(int i10);

    @Deprecated
    public static native int fuSetFaceDetParam(String str, float f);

    public static native void fuSetFaceProcessorDetectEveryNFramesWhenFace(int i10);

    public static native void fuSetFaceProcessorDetectEveryNFramesWhenNoFace(int i10);

    public static native int fuSetFaceProcessorDetectMode(int i10);

    public static native int fuSetFaceProcessorFov(float f);

    @Deprecated
    public static native int fuSetFaceTrackParam(String str, float f);

    public static native int fuSetForceUseGL2(int i10);

    public static native int fuSetGroundReflectionPrameters(int i10, float f, float f10);

    public static native void fuSetHandDetectEveryNFramesWhenNoHand(int i10);

    public static native void fuSetHandGestrueListener(OnHandGestureListener onHandGestureListener);

    public static native int fuSetHumanProcessorDetectMode(int i10);

    public static native void fuSetHumanSegMode(int i10);

    public static native void fuSetHumanSegScene(int i10);

    public static native void fuSetInputBufferMatrix(int i10);

    public static native void fuSetInputCameraBufferMatrixState(int i10);

    public static native void fuSetInputCameraMatrix(int i10, int i11, int i12);

    public static native void fuSetInputCameraTextureMatrixState(int i10);

    public static native void fuSetInputTextureMatrix(int i10);

    public static native int fuSetInstanceAnimationTransitionTime(int i10, float f);

    public static native int fuSetInstanceBlendExpression(int i10, float[] fArr);

    public static native int fuSetInstanceBodyInvisibleList(int i10, int[] iArr);

    public static native int fuSetInstanceBodyVisibleList(int i10, int[] iArr);

    public static native int fuSetInstanceColor(int i10, String str, int i11, int i12, int i13);

    public static native int fuSetInstanceColorIntensity(int i10, String str, float f);

    public static native int fuSetInstanceDeformation(int i10, String str, float f);

    public static native int fuSetInstanceEnableHumanAnimDriver(int i10, boolean z);

    public static native int fuSetInstanceExpressionWeight0(int i10, float[] fArr);

    public static native int fuSetInstanceExpressionWeight1(int i10, float[] fArr);

    public static native int fuSetInstanceEyeRotationDeltaX(int i10, float f);

    public static native int fuSetInstanceFaceBeautyOrder(int i10, int[] iArr);

    public static native int fuSetInstanceFaceProcessorFaceId(int i10, int i11);

    public static native int fuSetInstanceFaceProcessorFilterSize(int i10, int i11, int i12, int i13);

    public static native int fuSetInstanceFaceProcessorOuterResultPtr(int i10, float[] fArr);

    public static native int fuSetInstanceFaceProcessorType(int i10, int i11);

    public static native int fuSetInstanceFacebeautyColor(int i10, int i11, int i12, int i13, int i14);

    public static native int fuSetInstanceFacepup(int i10, String str, float f);

    public static native int fuSetInstanceFocusEyeToCameraParams(int i10, float f, float f10, float f11);

    public static native int fuSetInstanceHeadRotationDeltaX(int i10, float f);

    public static native int fuSetInstanceHeadRotationZRange(int i10, float f, float f10);

    public static native int fuSetInstanceHumanProcessorType(int i10, int i11);

    public static native int fuSetInstanceInputCameraBufferMatrix(int i10, int i11);

    public static native int fuSetInstanceRiggingRetargeterAvatarFixModeTransScale(int i10, float f, float f10, float f11);

    public static native int fuSetInstanceRiggingRetargeterAvatarFollowMode(int i10, int i11);

    public static native int fuSetInstanceRotDelta(int i10, float f);

    public static native int fuSetInstanceScale(int i10, float f, float f10, float f11, float f12);

    public static native int fuSetInstanceScaleDelta(int i10, float f);

    public static native int fuSetInstanceShadowPCFLevel(int i10, int i11);

    public static native int fuSetInstanceShadowSampleOffset(int i10, int i11);

    public static native int fuSetInstanceTargetAngle(int i10, float f);

    public static native int fuSetInstanceTargetAngleGradually(int i10, float f, int i11);

    public static native int fuSetInstanceTargetPosition(int i10, float f, float f10, float f11);

    public static native int fuSetInstanceTargetPositionGradually(int i10, float f, float f10, float f11, int i11);

    public static native int fuSetInstanceTargetPositionRange(int i10, float f, float f10, float f11, float f12, float f13, float f14);

    public static native int fuSetInstanceTranslateDelta(int i10, float f);

    public static native int fuSetInstanceUVAnimArray(int i10, int[] iArr);

    public static native void fuSetItemTriggerListener(int i10, OnItemTriggerListener onItemTriggerListener);

    public static native int fuSetLoadQuality(int i10);

    public static native int fuSetLogLevel(int i10);

    public static native void fuSetMachineType(int i10);

    public static native void fuSetMakeupCoverResource(boolean z);

    public static native int fuSetMaxFaces(int i10);

    public static native int fuSetMultiSamples(int i10);

    public static native int fuSetOuterModelMatrix(int i10, float[] fArr);

    public static native int fuSetOuterProjectionMatrix(int i10, float[] fArr);

    public static native int fuSetOuterViewMatrix(int i10, float[] fArr);

    public static native void fuSetOutputMatrix(int i10);

    public static native void fuSetOutputMatrixState(int i10);

    public static native void fuSetOutputResolution(int i10, int i11);

    public static native int fuSetProjectionMatrixFov(int i10, float f);

    public static native int fuSetProjectionMatrixOrthoSize(int i10, float f);

    public static native int fuSetProjectionMatrixZfar(int i10, float f);

    public static native int fuSetProjectionMatrixZnear(int i10, float f);

    @Deprecated
    public static native void fuSetQualityTradeoff(float f);

    public static native void fuSetReadbackSync(boolean z);

    public static native void fuSetRenderPauseState(boolean z);

    public static native void fuSetRttCacheState(int i10);

    public static native int fuSetShadowBias(int i10, float f, float f10);

    public static native int fuSetShadowMapSize(int i10, int i11);

    @Deprecated
    public static native void fuSetStrictTracking(int i10);

    public static native int fuSetTongueTracking(int i10);

    public static native void fuSetTrackFaceAIType(int i10);

    public static native int fuSetUseAsyncAIInference(int i10);

    public static native int fuSetUseMultiBuffer(int i10, int i11);

    public static native int fuSetUseTexAsync(int i10);

    public static native void fuSetVideoHash(int i10, String str, int i11);

    public static native void fuSetVideoProcessMode(int i10);

    public static native int fuSetup(byte[] bArr, byte[] bArr2);

    @Deprecated
    public static native int fuSetup(byte[] bArr, byte[] bArr2, byte[] bArr3);

    public static native byte[] fuSetupDeviceLocal(byte[] bArr, byte[] bArr2, byte[] bArr3);

    public static native int fuSetupInternalCheck(byte[] bArr, byte[] bArr2);

    public static native int fuSetupInternalCheckEx(byte[] bArr, byte[] bArr2, byte[] bArr3);

    public static native int fuSetupInternalCheckPackageBind(byte[] bArr, byte[] bArr2);

    public static native byte[] fuSetupLocal(byte[] bArr, byte[] bArr2, byte[] bArr3);

    public static native byte[] fuSetupOffline(int i10, byte[] bArr, byte[] bArr2, byte[] bArr3);

    public static native int fuStartCameraAnimation(int i10);

    public static native int fuStartInstanceAnimation(int i10);

    public static native int fuStopInstanceAnimation(int i10);

    public static native int fuTrackFace(byte[] bArr, int i10, int i11, int i12);

    public static native int fuTrackFaceWithTongue(byte[] bArr, int i10, int i11, int i12);

    public static native int fuUnBindItems(int i10, int[] iArr);

    public static native int fuUnbindAllItems(int i10);

    public static native int fuUnbindItemsFromInstance(int i10, int[] iArr);

    public static native int fuUnbindItemsFromScene(int i10, int[] iArr);

    public static native int fuUpdateBackgroundTexture(int i10, int i11, byte[] bArr, int i12, int i13);
}
