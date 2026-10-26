package com.kwai.kscnnrenderlib;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes14.dex */
public class KSCNN {
    public static final String AIEditExample = "AIEditExample";
    public static final String AIEditFaceSeg = "AIEditFaceSeg";
    public static final String AIEditFacialParing = "AIEditFacialParing";
    public static final String AIEditFakeSmile = "AIEditFakeSmile";
    public static final String AIEditFrown = "AIEditFrown";
    public static final String AIEditFrownPout = "AIEditFrownPout";
    public static final String AIEditFrownSmile = "AIEditFrownSmile";
    public static final String AIEditHairSeg = "AIEditHairSeg";
    public static final String AIEditHairSegIns = "AIEditHairSegIns";
    public static final String AIEditLongHair = "AIEditLongHair";
    public static final String AIEditMatting = "AIEditMatting";
    public static final String AIEditMattingIns = "AIEditMattingIns";
    public static final String AIEditNailSeg = "AIEditNailSeg";
    public static final String AIEditPout = "AIEditPout";
    public static final String AIEditSkinSeg = "AIEditSkinSeg";
    public static final String AIEditSkySeg = "AIEditSkySeg";
    public static final String AR = "AR";
    public static final String AnimalLandmarks = "AnimalLandmarks";
    public static final String Benchmark = "Benchmark";
    public static final String ClothSeg = "ClothSeg";
    public static final String DepthCamera = "DepthCamera";
    public static final String DogLandmarks = "DogLandmarks";
    public static final String Face2Drawing = "Face2Drawing";
    public static final String FaceAttribute = "FaceAttribute";
    public static final String FaceCluster = "FaceCluster";
    public static final String FaceMesh = "FaceMesh";
    public static final String FaceSeg = "FaceSeg";
    public static final String FaceSimilarity = "FaceSimilarity";
    public static final String HandSeg = "HandSeg";
    public static final String HeadSeg = "HeadSeg";
    public static final String HumanKeyPoint = "HumanKeyPoint";
    public static final String HumanMesh = "HumanMesh";
    public static final String HumanMeshHalf = "HumanMeshHalf";
    public static final String HumanParsing = "HumanParsing";
    public static final String InpaintingVideo = "InpaintingVideo";
    public static final String KSDetector = "Detector";
    public static final String KSFinger = "Finger";
    public static final String KSGesture = "Gesture";
    public static final String KSHDRNet = "HDRNet";
    public static final String KSHair = "Hair";
    public static final String KSHairDir = "HairDir";
    public static final String KSHandpose = "Handpose";
    public static final String KSHumanPose = "HumanPose";
    public static final String KSLandmarks = "Landmarks";
    public static final String KSMatting = "Matting";
    public static final String KSPlane = "Plane";
    public static final String KSSalient = "Salient";
    public static final String KSSceneClass = "SceneClass";
    public static final String KSSky = "Sky";
    public static final String LivePhoto = "LivePhoto";
    public static final String NailSeg = "NailSeg";
    public static final String ProjectK = "ProjectK";
    public static final String SkinSeg = "SkinSeg";
    public static final String StyleNeo = "StyleNeo";
    public static final String StyleTransfer = "StyleTransfer";
    public static final String TaskDetect = "TaskDetect";
    public static final String TaskPoint = "TaskPoint";

    public static class KSCreateCNNParam implements Serializable {
        public String strType = KSCNN.KSMatting;
        public boolean useNeon = true;
        public boolean useOpenCL = false;
        public boolean useHiAi = false;
        public int numDeferredFrames = 0;
        public boolean fastMode = false;
        public boolean renderFrame = true;
        public boolean renderModel = true;
        public boolean showDetailLog = true;
        public boolean useCamera = false;
        public boolean useHigh = true;
    }

    public static class KSPariObj implements Serializable {
        public int index;
        public Object obj;
    }

    public static List<String> getAIEditModelsList() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(AIEditExample);
        linkedList.add(AIEditFaceSeg);
        linkedList.add(AIEditFacialParing);
        linkedList.add(AIEditHairSeg);
        linkedList.add(AIEditHairSegIns);
        linkedList.add(AIEditMatting);
        linkedList.add(AIEditMattingIns);
        linkedList.add(AIEditNailSeg);
        linkedList.add(AIEditSkySeg);
        linkedList.add(AIEditSkinSeg);
        linkedList.add(AIEditPout);
        linkedList.add(AIEditFakeSmile);
        linkedList.add(AIEditFrown);
        linkedList.add(AIEditFrownPout);
        linkedList.add(AIEditFrownSmile);
        linkedList.add(AIEditLongHair);
        return linkedList;
    }

    public static int getFilterType(String str) {
        if (str.compareToIgnoreCase(KSMatting) == 0) {
            return 0;
        }
        if (str.compareToIgnoreCase(KSSky) == 0) {
            return 9;
        }
        if (str.compareToIgnoreCase(KSGesture) == 0) {
            return 1;
        }
        if (str.compareToIgnoreCase(KSHair) == 0) {
            return 2;
        }
        if (str.compareToIgnoreCase(KSFinger) == 0) {
            return 3;
        }
        if (str.compareToIgnoreCase(KSHumanPose) == 0) {
            return 4;
        }
        if (str.compareToIgnoreCase(KSLandmarks) == 0) {
            return 5;
        }
        if (str.compareToIgnoreCase(KSHandpose) == 0) {
            return 12;
        }
        if (str.compareToIgnoreCase(KSDetector) == 0) {
            return 24;
        }
        return str.compareToIgnoreCase(KSPlane) == 0 ? 17 : 0;
    }

    public static List<String> getModelsList() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(FaceMesh);
        linkedList.add(AR);
        linkedList.add(KSMatting);
        linkedList.add(KSSky);
        linkedList.add(KSGesture);
        linkedList.add(KSHair);
        linkedList.add(KSHairDir);
        linkedList.add(KSFinger);
        linkedList.add(KSHumanPose);
        linkedList.add(KSLandmarks);
        linkedList.add(KSHandpose);
        linkedList.add(KSDetector);
        linkedList.add(KSSceneClass);
        linkedList.add(KSSalient);
        linkedList.add(KSPlane);
        linkedList.add(KSHDRNet);
        linkedList.add(HandSeg);
        linkedList.add(StyleTransfer);
        linkedList.add(FaceSeg);
        linkedList.add(TaskDetect);
        linkedList.add(TaskPoint);
        linkedList.add(HeadSeg);
        linkedList.add(NailSeg);
        linkedList.add(HumanParsing);
        linkedList.add(ClothSeg);
        linkedList.add(FaceSimilarity);
        linkedList.add(FaceCluster);
        linkedList.add(HumanKeyPoint);
        linkedList.add(AnimalLandmarks);
        linkedList.add(DogLandmarks);
        linkedList.add(SkinSeg);
        linkedList.add(FaceAttribute);
        linkedList.add(Face2Drawing);
        linkedList.add(ProjectK);
        linkedList.add(DepthCamera);
        linkedList.add(StyleNeo);
        linkedList.add(HumanMesh);
        linkedList.add(HumanMeshHalf);
        linkedList.add(InpaintingVideo);
        linkedList.add(Benchmark);
        linkedList.add(LivePhoto);
        return linkedList;
    }
}
