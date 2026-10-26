package com.kwai.kscnnrenderlib;

import android.content.res.AssetManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes14.dex */
public class YCNNModelInfo {
    public static final int KSLANDMARKS_SMOOTH_NONE = 0;
    public static final int KSLANDMARKS_SMOOTH_TYPE1 = 1;
    public static final int KSLANDMARKS_SMOOTH_TYPE2 = 2;
    public static final int KSLANDMARKS_TRACK_128 = 2;
    public static final int KSLANDMARKS_TRACK_256 = 3;
    public static final int KSLANDMARKS_TRACK_64 = 1;
    public static final int KSLANDMARKS_TRACK_NONE = 0;
    public static final int KSLandmarksType_Fast = 1;
    public static final int KSLandmarksType_Image = 2;
    public static final int KSLandmarksType_RECT = 3;
    public static final int KSLandmarksType_Robust = 0;
    public static final int KSLandmarksType_SuperFast = 4;
    public static final int KSMaskOutType_Norm = 0;
    public static final int KSMaskOutType_Post = 4;
    public static final int KSMaskOutType_Raw = 2;
    public static final int KSMaskOutType_Smooth = 3;
    public static final int KSMaskOutType_Source = 1;
    public static final int YCNNModelBenchmark = 15;
    public static final int YCNNModelDepthCamera = 11;
    public static final int YCNNModelDetector = 21;
    public static final int YCNNModelFaceAttribute = 10;
    public static final int YCNNModelFaceCluster = 33;
    public static final int YCNNModelFaceSeg = 20;
    public static final int YCNNModelFaceSimilarity = 32;
    public static final int YCNNModelFinger = 3;
    public static final int YCNNModelGeneralHandpose = 12;
    public static final int YCNNModelGeneralRecog = 54;
    public static final int YCNNModelHDRNet = 22;
    public static final int YCNNModelHair = 2;
    public static final int YCNNModelHandGesture = 1;
    public static final int YCNNModelHandSeg = 18;
    public static final int YCNNModelHandpose = 6;
    public static final int YCNNModelHeadSeg = 28;
    public static final int YCNNModelHumanKeyPose = 14;
    public static final int YCNNModelHumanMatting = 0;
    public static final int YCNNModelHumanParsing = 31;
    public static final int YCNNModelHumanpose = 4;
    public static final int YCNNModelInpainting = 53;
    public static final int YCNNModelInstanceSeg = 23;
    public static final int YCNNModelLandmarks = 5;
    public static final int YCNNModelLandmarks3D = 13;
    public static final int YCNNModelMakeupSeg = 30;
    public static final int YCNNModelNailSeg = 29;
    public static final int YCNNModelNone = -1;
    public static final int YCNNModelPlane = 17;
    public static final int YCNNModelPortrait = 16;
    public static final int YCNNModelSalient = 8;
    public static final int YCNNModelSceneClass = 7;
    public static final int YCNNModelSky = 9;
    public static final int YCNNModelStyleTransfer = 19;
    public static final int YCNNModelTaskDetect = 24;
    public static final int YCNNModelTaskImage = 26;
    public static final int YCNNModelTaskPoint = 25;
    public static final int YCNNModelTaskSeg = 27;
    public static final int YCNNTaskDetect_Human = 0;

    public static final class KSContourInfo implements Serializable {
        public long index = -1;
        public LinkedList<YCNNComm.KSVec2f> pos = new LinkedList<>();
    }

    public static class KSFaceAttributeParam implements Serializable {
        public int use_feature = 0;
    }

    public static final class KSFaceDetectOut implements Serializable {
        public KSInputInfo inputInfo = new KSInputInfo();
        public LinkedList<KSFaceInfo> faces = new LinkedList<>();
    }

    public static final class KSFaceInfo implements Serializable {
        public long index = -1;
        public LinkedList<YCNNComm.KSPtInfo> pos = new LinkedList<>();
        public float left = 0.0f;
        public float top = 0.0f;
        public float width = 0.0f;
        public float height = 0.0f;
        public float confidence = 0.0f;
        public float yaw = 0.0f;
        public float pitch = 0.0f;
        public float roll = 0.0f;
    }

    public static class KSGeneralRecogOut implements Serializable {
        public KSInputInfo inputInfo = new KSInputInfo();
        public String out_json;
    }

    public static class KSGeneralRecogParam implements Serializable {
        public int sceneOut = 1;
        public int objectOut = 1;
        public int qualityOut = 1;
        public int positionInfo = 1;
        public int topN = -1;
        public int multiSinglePersonMode = 0;
        public int useFaceID = 0;
    }

    public static class KSHeadSegOut {
        public KSInputInfo inputInfo = new KSInputInfo();
        public YCNNComm.KSImage seg = new YCNNComm.KSImage();
    }

    public static class KSHeadSegParam implements Serializable {
        public int chooseMax = 1;
        public int getHeadSegOut = 0;
        public int outAlphaMultiply = 0;
        public int outCutValid = 0;
        public int outGetValidRange = 0;
        public float outExtend = 0.0f;
    }

    public static class KSHumanMattingParam implements Serializable {
        public int showOptions = 3;
        public float borderSrcPos = 18.0f;
        public float borderDstPos = 45.0f;
        public int portraitMode = 0;
        public float strength = 0.0f;
        public float extend = 0.0f;
        public int intervalFrames = -1;
        public int useFace = 0;
        public int getMatting = 0;
        public int getContour = 0;
        public float contourThresh = 0.2f;
        public float contourMinSize = 0.1f;
        public float contourExpand = 0.03f;
        public int outAlphaMultiply = 0;
        public int outCutValid = 0;
        public int outGetValidRange = 0;
        public float outExtend = 0.0f;
    }

    public static class KSHumanPoseOut implements Serializable {
        public KSInputInfo inputInfo = new KSInputInfo();
        public ArrayList<KSPoseInfo> poses = new ArrayList<>();
    }

    public static class KSHumanposeParam implements Serializable {
        public int pose_type = 4;
        public boolean smoothFilterXY = false;
        public boolean smoothFilterZ = true;
        public boolean has3DInfo = false;
        public int numDeferredFrames = 0;
        public int numMotionPredictFrames = 5;
        public boolean fastMode = false;
    }

    public static class KSInpaintingOut implements Serializable {
        public KSInputInfo inputInfo = new KSInputInfo();
        public YCNNComm.KSImage out = new YCNNComm.KSImage();
    }

    public static class KSInpaintingParam implements Serializable {
        public boolean doTrigger = true;
        public int texID = 0;
        public int width = 0;
        public int height = 0;
        public boolean is_need_matting = false;
        public boolean is_need_alpha = true;
    }

    public static class KSInputInfo implements Serializable {
        public static int flipHor;
        public static long frame_num;
        public static int height;
        public static int rotate;
        public static int width;
    }

    public static class KSInstanceSegInfo {
        public int index = -1;
        public YCNNComm.KSImage img = new YCNNComm.KSImage();
    }

    public static class KSInstanceSegOut {
        public KSInputInfo inputInfo = new KSInputInfo();
        public LinkedList<KSInstanceSegInfo> segs = new LinkedList<>();
    }

    public static class KSInstanceSegParam {
        public int getWholeSeg = 0;
        public int outAlphaMultiply = 0;
        public String param;
    }

    public static class KSLandmarksParam implements Serializable {
        public int maxFaceNum = 4;
        public int minFaceSize = 50;
        public int detectIntervals = 5;
        public int detectMode = 4;
        public int detectSync = 0;
        public int firstFrameValid = 0;
        public int detectEar = 0;
        public int detectTongue = 0;
        public int detectEyeball = 0;
        public int useAdjustMode = 1;
        public int useSubEye = 1;
        public int useSubMouth = 1;
        public int useCorrectMode = 1;
        public int useEyeAutoSwitch = 1;
        public int useDetect360 = 1;
        public int useRobust3D = 0;
        public int useSmoothCropRect = 0;
        public boolean stickFaceCropRect = true;
        public boolean stickTrackRect = true;
        public int useSmoothMode = 0;
        public int useImageTrack = 2;
    }

    public static class KSMattingOut {
        public KSInputInfo inputInfo = new KSInputInfo();
        public LinkedList<KSContourInfo> contours = new LinkedList<>();
        public YCNNComm.KSImage mask = new YCNNComm.KSImage();
    }

    public static class KSPoseInfo implements Serializable {
        public ArrayList<YCNNComm.KSPtInfo> pos = new ArrayList<>();
        public YCNNComm.KSRectf range = new YCNNComm.KSRectf();
        public int index = -1;
        public int pose_type = -1;
    }

    public static class KSSceneClassInfo implements Serializable {
        public int type = 0;
        public float conf = 0.0f;
    }

    public static class KSSceneClassOut implements Serializable {
        public KSInputInfo inputInfo = new KSInputInfo();
        public ArrayList<ArrayList<KSSceneClassInfo>> infos = new ArrayList<>();
    }

    public static class KSStyleTransferParam {
        public String param_json;
        public LinkedList<String> tex_paths = new LinkedList<>();
    }

    public static final class KSTaskDetectInfo implements Serializable {
        public long index = -1;
        public float left = 0.0f;
        public float top = 0.0f;
        public float width = 0.0f;
        public float height = 0.0f;
        public float confidence = 0.0f;
        public int type = 0;
    }

    public static final class KSTaskDetectOut implements Serializable {
        public KSInputInfo inputInfo = new KSInputInfo();
        public LinkedList<KSTaskDetectInfo> detects = new LinkedList<>();
    }

    public static class KSTaskDetectParam implements Serializable {
        public int syncDetect = 1;
        public int detectIntervals = 0;
    }

    public static class YCNNModelConfig {
        public int model_type = -1;
        public int model_sub_type = 0;
        public LinkedList<String> model_files = new LinkedList<>();
        public String model_path = "";
        public String param = "";
        public String model_type_string = "";
        public String model_type_param = "";
        public AssetManager assetManager = null;
    }

    public static class YCNNModelIn implements Serializable {
        public byte[] data_0;
        public int colorType = 3;
        public int rotate = 0;
        public int flipHor = 0;
        public long frame_time = 0;
        public boolean single_image = false;
        public int width = 0;
        public int height = 0;
        public LinkedList<YCNNComm.KSRectf> faces = new LinkedList<>();
        public int normOut = 0;
        public int normRotate = 0;
        public int normFlipHor = 0;
        public int normFlipVer = 0;
        public String param_info = "";
    }

    public static class YCNNModelPostIn implements Serializable {
        public int texID = 0;
        public int width = 0;
        public int height = 0;
        public int rotate = 0;
        public int flipHor = 0;
        public int flipVer = 0;
        public long frame_time = 0;
        public int outRotate = 0;
        public int outFlipHor = 0;
        public int outFlipVer = 0;
    }
}
