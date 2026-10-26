package com.kwai.kscnnrenderlib;

import android.util.Pair;
import com.yxcorp.bugly.CrashReporter;
import io.reactivex.annotations.SchedulerSupport;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes14.dex */
public class KSRenderObj {
    public static final String TAG = "KSRenderObj";
    public YCNNModelInfo.YCNNModelConfig mModelConfig;
    private uploadStatsCallBack mUploadStatsCallBack;
    private long mKSJNIRenderObj = 0;
    public String mJNIRenderInfo = SchedulerSupport.NONE;
    private volatile long mModelParamIndex = 0;
    private Object mObjSync = new Object();
    private YCNNModelInfo.KSLandmarksParam mLandmarksParam = new YCNNModelInfo.KSLandmarksParam();
    private YCNNModelInfo.KSFaceDetectOut mFaceDetectOut = new YCNNModelInfo.KSFaceDetectOut();
    private YCNNModelInfo.KSTaskDetectParam mTaskDetectParam = new YCNNModelInfo.KSTaskDetectParam();
    private YCNNModelInfo.KSTaskDetectOut mTaskDetectOut = new YCNNModelInfo.KSTaskDetectOut();
    private YCNNModelInfo.KSHumanMattingParam mMattingParam = new YCNNModelInfo.KSHumanMattingParam();
    private YCNNModelInfo.KSMattingOut mMattingOut = new YCNNModelInfo.KSMattingOut();
    private YCNNModelInfo.KSHumanposeParam mHumanposeParam = new YCNNModelInfo.KSHumanposeParam();
    private YCNNModelInfo.KSHumanPoseOut mHumanposeOut = new YCNNModelInfo.KSHumanPoseOut();
    private YCNNModelInfo.KSHeadSegParam mHeadSegParam = new YCNNModelInfo.KSHeadSegParam();
    private YCNNModelInfo.KSHeadSegOut mHeadSegOut = new YCNNModelInfo.KSHeadSegOut();
    private YCNNModelInfo.KSInstanceSegParam mInstanceSegParam = new YCNNModelInfo.KSInstanceSegParam();
    private YCNNModelInfo.KSInstanceSegOut mInstanceSegOut = new YCNNModelInfo.KSInstanceSegOut();
    private YCNNModelInfo.KSSceneClassOut mSceneOut = new YCNNModelInfo.KSSceneClassOut();
    private YCNNModelInfo.KSStyleTransferParam mStyleTransferParam = new YCNNModelInfo.KSStyleTransferParam();
    private YCNNModelInfo.KSGeneralRecogParam mGeneralRecogParam = new YCNNModelInfo.KSGeneralRecogParam();
    private YCNNModelInfo.KSGeneralRecogOut mGeneralRecogOut = new YCNNModelInfo.KSGeneralRecogOut();
    private YCNNModelInfo.KSInpaintingParam mInpaintingParam = new YCNNModelInfo.KSInpaintingParam();
    private YCNNModelInfo.KSInpaintingOut mInpaintingOut = new YCNNModelInfo.KSInpaintingOut();
    private YCNNModelInfo.KSFaceAttributeParam mFaceAttributeParam = new YCNNModelInfo.KSFaceAttributeParam();
    private YCNNModelInfo.YCNNModelIn mModelIn = new YCNNModelInfo.YCNNModelIn();
    private YCNNModelInfo.YCNNModelPostIn mModelPostIn = new YCNNModelInfo.YCNNModelPostIn();
    private KSJNILib mKSJNILib = new KSJNILib();

    public interface uploadStatsCallBack {
        void uploadStats(String str);
    }

    public static ByteBuffer cloneByteBuffer(ByteBuffer byteBuffer) {
        if (byteBuffer == null || byteBuffer.capacity() < 1) {
            return null;
        }
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(byteBuffer.capacity());
        byteBuffer.rewind();
        byteBufferAllocateDirect.put(byteBuffer);
        byteBuffer.rewind();
        byteBufferAllocateDirect.flip();
        byteBufferAllocateDirect.array();
        return byteBufferAllocateDirect;
    }

    public static KSRenderObj createRender(YCNNModelInfo.YCNNModelConfig yCNNModelConfig) {
        KSRenderObj kSRenderObj = new KSRenderObj();
        kSRenderObj.mModelConfig = yCNNModelConfig;
        return kSRenderObj;
    }

    public void YCNNGetConfig2Model(String str) {
        this.mKSJNILib.YCNNGetConfig2Model(str, this.mModelConfig);
        this.mModelConfig.model_path = str;
    }

    public void addKSImage(LinkedList<YCNNComm.KSImage> linkedList, ByteBuffer byteBuffer, int i12, int i13, int i14, int i15, int i16) {
        YCNNComm.KSImage kSImage = new YCNNComm.KSImage();
        kSImage.width = i12;
        kSImage.height = i13;
        kSImage.channel = i14;
        YCNNComm.KSRect kSRect = kSImage.range;
        kSRect.left = i15;
        kSRect.top = i16;
        kSRect.width = i12;
        kSRect.height = i13;
        kSImage.setData(byteBuffer);
        linkedList.add(kSImage);
    }

    public void addTest(int i12) {
    }

    public boolean createCPUModel() {
        this.mKSJNILib.createModel(this);
        return true;
    }

    public boolean createGPUModel() {
        this.mKSJNILib.createModelPost(this);
        return true;
    }

    public void devFun(int i12) {
        this.mKSJNILib.devFun(this, i12);
    }

    public void getContours(YCNNComm.KSImage kSImage, ArrayList<ArrayList<YCNNComm.KSVec2f>> arrayList, float f12, float f13, float f14, float f15) {
        this.mKSJNILib.getContour(this, kSImage, arrayList, f12, f13, f14, f15);
    }

    public int getGeneralRecogOut(YCNNModelInfo.KSGeneralRecogOut kSGeneralRecogOut) {
        synchronized (this) {
            try {
                kSGeneralRecogOut.out_json = this.mGeneralRecogOut.out_json;
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "KSRenderObj.class", "getGeneralRecogOut", -1);
                throw th2;
            }
        }
        return 0;
    }

    public int getHeadSegOut(YCNNModelInfo.KSHeadSegOut kSHeadSegOut) {
        synchronized (this) {
            try {
                YCNNComm.KSImage kSImage = kSHeadSegOut.seg;
                YCNNComm.KSImage kSImage2 = this.mHeadSegOut.seg;
                int i12 = kSImage2.width;
                kSImage.width = i12;
                kSImage.height = kSImage2.height;
                kSImage.channel = kSImage2.channel;
                if (i12 > 0) {
                    kSImage.buffer = cloneByteBuffer(kSImage2.buffer);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "KSRenderObj.class", "getHeadSegOut", -1);
                throw th2;
            }
        }
        return 0;
    }

    public int getHumanMattingOut(YCNNModelInfo.KSMattingOut kSMattingOut) {
        synchronized (this) {
            try {
                kSMattingOut.contours.clear();
                for (YCNNModelInfo.KSContourInfo kSContourInfo : this.mMattingOut.contours) {
                    YCNNModelInfo.KSContourInfo kSContourInfo2 = new YCNNModelInfo.KSContourInfo();
                    kSContourInfo2.index = kSContourInfo.index;
                    for (YCNNComm.KSVec2f kSVec2f : kSContourInfo.pos) {
                        YCNNComm.KSVec2f kSVec2f2 = new YCNNComm.KSVec2f();
                        kSVec2f2.f26298x = kSVec2f.f26298x;
                        kSVec2f2.f26299y = kSVec2f.f26299y;
                        kSContourInfo2.pos.add(kSVec2f2);
                    }
                    kSMattingOut.contours.add(kSContourInfo2);
                }
                YCNNComm.FuncCopyKSImage(this.mMattingOut.mask, kSMattingOut.mask);
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "KSRenderObj.class", "getHumanMattingOut", -1);
                throw th2;
            }
        }
        return 0;
    }

    public int getHumanposeOut(YCNNModelInfo.KSHumanPoseOut kSHumanPoseOut) {
        synchronized (this) {
            try {
                kSHumanPoseOut.poses.clear();
                for (YCNNModelInfo.KSPoseInfo kSPoseInfo : this.mHumanposeOut.poses) {
                    YCNNModelInfo.KSPoseInfo kSPoseInfo2 = new YCNNModelInfo.KSPoseInfo();
                    for (YCNNComm.KSPtInfo kSPtInfo : kSPoseInfo.pos) {
                        YCNNComm.KSPtInfo kSPtInfo2 = new YCNNComm.KSPtInfo();
                        kSPtInfo2.xPos = kSPtInfo.xPos;
                        kSPtInfo2.yPos = kSPtInfo.yPos;
                        kSPtInfo2.valid = kSPtInfo.valid;
                        kSPoseInfo2.pos.add(kSPtInfo2);
                    }
                    YCNNComm.KSRectf kSRectf = kSPoseInfo2.range;
                    YCNNComm.KSRectf kSRectf2 = kSPoseInfo.range;
                    kSRectf.left = kSRectf2.left;
                    kSRectf.top = kSRectf2.top;
                    kSRectf.width = kSRectf2.width;
                    kSRectf.height = kSRectf2.height;
                    kSPoseInfo2.pose_type = kSPoseInfo.pose_type;
                    kSPoseInfo2.index = kSPoseInfo.index;
                    kSHumanPoseOut.poses.add(kSPoseInfo2);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "KSRenderObj.class", "getHumanposeOut", -1);
                throw th2;
            }
        }
        return 0;
    }

    public void getImageColor(YCNNComm.KSImage kSImage, ArrayList<YCNNComm.KSVec3i> arrayList, ArrayList<Pair<YCNNComm.KSVec3i, YCNNComm.KSVec3i>> arrayList2, ArrayList<Pair<YCNNComm.KSVec3i, YCNNComm.KSVec3i>> arrayList3, YCNNComm.KSImage kSImage2) {
        this.mKSJNILib.getImageColor(this, kSImage, arrayList, arrayList2, arrayList3, kSImage2);
    }

    public void getImageConnectedRange(YCNNComm.KSImage kSImage, LinkedList<YCNNComm.KSImage> linkedList, int i12, int i13, int i14, int i15) {
        this.mKSJNILib.getImageConnectedRange(this, kSImage, linkedList, i12, i13, i14, i15);
    }

    public YCNNComm.KSRect getImageValidRange(YCNNComm.KSImage kSImage, int i12, int i13) {
        YCNNComm.KSRect kSRect = new YCNNComm.KSRect();
        int[] iArr = new int[4];
        this.mKSJNILib.getImageValidRange(this, kSImage, i12, i13, iArr);
        kSRect.left = iArr[0];
        kSRect.top = iArr[1];
        kSRect.width = iArr[2];
        kSRect.height = iArr[3];
        return kSRect;
    }

    public String getInfo() {
        return this.mJNIRenderInfo + "\n";
    }

    public int getInpaintingOut(YCNNModelInfo.KSInpaintingOut kSInpaintingOut) {
        synchronized (this) {
            try {
                kSInpaintingOut.out = this.mInpaintingOut.out;
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "KSRenderObj.class", "getInpaintingOut", -1);
                throw th2;
            }
        }
        return 0;
    }

    public int getInstanceSegOut(YCNNModelInfo.KSInstanceSegOut kSInstanceSegOut) {
        synchronized (this) {
            try {
                kSInstanceSegOut.segs.clear();
                for (YCNNModelInfo.KSInstanceSegInfo kSInstanceSegInfo : this.mInstanceSegOut.segs) {
                    YCNNModelInfo.KSInstanceSegInfo kSInstanceSegInfo2 = new YCNNModelInfo.KSInstanceSegInfo();
                    kSInstanceSegInfo2.index = kSInstanceSegInfo.index;
                    YCNNComm.FuncCopyKSImage(kSInstanceSegInfo.img, kSInstanceSegInfo2.img);
                    kSInstanceSegOut.segs.add(kSInstanceSegInfo2);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "KSRenderObj.class", "getInstanceSegOut", -1);
                throw th2;
            }
        }
        return 0;
    }

    public int getLandmarks(YCNNModelInfo.KSFaceDetectOut kSFaceDetectOut) {
        synchronized (this) {
            try {
                kSFaceDetectOut.faces.clear();
                this.mFaceDetectOut.faces.size();
                for (YCNNModelInfo.KSFaceInfo kSFaceInfo : this.mFaceDetectOut.faces) {
                    YCNNModelInfo.KSFaceInfo kSFaceInfo2 = new YCNNModelInfo.KSFaceInfo();
                    kSFaceInfo2.index = kSFaceInfo.index;
                    kSFaceInfo2.left = kSFaceInfo.left;
                    kSFaceInfo2.top = kSFaceInfo.top;
                    kSFaceInfo2.width = kSFaceInfo.width;
                    kSFaceInfo2.height = kSFaceInfo.height;
                    kSFaceInfo2.confidence = kSFaceInfo.confidence;
                    kSFaceInfo2.yaw = kSFaceInfo.yaw;
                    kSFaceInfo2.pitch = kSFaceInfo.pitch;
                    kSFaceInfo2.roll = kSFaceInfo.roll;
                    for (YCNNComm.KSPtInfo kSPtInfo : kSFaceInfo.pos) {
                        YCNNComm.KSPtInfo kSPtInfo2 = new YCNNComm.KSPtInfo();
                        kSPtInfo2.xPos = kSPtInfo.xPos;
                        kSPtInfo2.yPos = kSPtInfo.yPos;
                        kSPtInfo2.zPos = kSPtInfo.zPos;
                        kSPtInfo2.valid = kSPtInfo.valid;
                        kSFaceInfo2.pos.add(kSPtInfo2);
                    }
                    kSFaceDetectOut.faces.add(kSFaceInfo2);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "KSRenderObj.class", "getLandmarks", -1);
                throw th2;
            }
        }
        return 0;
    }

    public YCNNModelInfo.KSLandmarksParam getLandmarksParam() {
        return this.mLandmarksParam;
    }

    public int getSceneClassOut(YCNNModelInfo.KSSceneClassOut kSSceneClassOut) {
        synchronized (this) {
            try {
                kSSceneClassOut.infos.clear();
                for (ArrayList<YCNNModelInfo.KSSceneClassInfo> arrayList : this.mSceneOut.infos) {
                    ArrayList<YCNNModelInfo.KSSceneClassInfo> arrayList2 = new ArrayList<>();
                    for (YCNNModelInfo.KSSceneClassInfo kSSceneClassInfo : arrayList) {
                        YCNNModelInfo.KSSceneClassInfo kSSceneClassInfo2 = new YCNNModelInfo.KSSceneClassInfo();
                        kSSceneClassInfo2.conf = kSSceneClassInfo.conf;
                        kSSceneClassInfo2.type = kSSceneClassInfo.type;
                        arrayList2.add(kSSceneClassInfo2);
                    }
                    kSSceneClassOut.infos.add(arrayList2);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "KSRenderObj.class", "getSceneClassOut", -1);
                throw th2;
            }
        }
        return 0;
    }

    public int getTaskDetectOut(YCNNModelInfo.KSTaskDetectOut kSTaskDetectOut) {
        synchronized (this) {
            try {
                kSTaskDetectOut.detects.clear();
                for (YCNNModelInfo.KSTaskDetectInfo kSTaskDetectInfo : this.mTaskDetectOut.detects) {
                    YCNNModelInfo.KSTaskDetectInfo kSTaskDetectInfo2 = new YCNNModelInfo.KSTaskDetectInfo();
                    kSTaskDetectInfo2.index = kSTaskDetectInfo.index;
                    kSTaskDetectInfo2.left = kSTaskDetectInfo.left;
                    kSTaskDetectInfo2.top = kSTaskDetectInfo.top;
                    kSTaskDetectInfo2.width = kSTaskDetectInfo.width;
                    kSTaskDetectInfo2.height = kSTaskDetectInfo.height;
                    kSTaskDetectInfo2.confidence = kSTaskDetectInfo.confidence;
                    kSTaskDetectOut.detects.add(kSTaskDetectInfo2);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "KSRenderObj.class", "getTaskDetectOut", -1);
                throw th2;
            }
        }
        return 0;
    }

    public int loadImageToRGBA(String str, int i12, boolean z12, YCNNComm.KSImage kSImage) {
        return YCNNComm.loadImageToRGBA(this.mModelConfig.assetManager, str, i12, z12, kSImage);
    }

    public void release() {
        this.mKSJNILib.releaseObj(this);
    }

    public void releaseCPU() {
        this.mKSJNILib.releaseModel(this);
    }

    public void releaseGPU() {
        this.mKSJNILib.releaseModelPost(this);
    }

    public int runModelBuffer(YCNNModelInfo.YCNNModelIn yCNNModelIn) {
        this.mModelIn = yCNNModelIn;
        this.mKSJNILib.renderModel(this, yCNNModelIn.data_0);
        synchronized (this) {
            try {
                this.mKSJNILib.updateModelOut(this);
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "KSRenderObj.class", "runModelBuffer", -1);
                throw th2;
            }
        }
        return 0;
    }

    public int runModelPostGL(YCNNModelInfo.YCNNModelPostIn yCNNModelPostIn) {
        this.mModelPostIn = yCNNModelPostIn;
        return this.mKSJNILib.renderModelPost(this);
    }

    public void setFaceAttributeParam(YCNNModelInfo.KSFaceAttributeParam kSFaceAttributeParam) {
        this.mFaceAttributeParam = kSFaceAttributeParam;
        this.mModelParamIndex++;
    }

    public void setGeneralRecogParam(YCNNModelInfo.KSGeneralRecogParam kSGeneralRecogParam) {
        this.mGeneralRecogParam = kSGeneralRecogParam;
        this.mModelParamIndex++;
    }

    public void setHeadSegParam(YCNNModelInfo.KSHeadSegParam kSHeadSegParam) {
        this.mHeadSegParam = kSHeadSegParam;
        this.mModelParamIndex++;
    }

    public void setHumanMattingParam(YCNNModelInfo.KSHumanMattingParam kSHumanMattingParam) {
        this.mMattingParam = kSHumanMattingParam;
        this.mModelParamIndex++;
    }

    public void setHumanposeParam(YCNNModelInfo.KSHumanposeParam kSHumanposeParam) {
        this.mHumanposeParam = kSHumanposeParam;
        this.mModelParamIndex++;
    }

    public void setInpaintingParam(YCNNModelInfo.KSInpaintingParam kSInpaintingParam) {
        this.mInpaintingParam = kSInpaintingParam;
        this.mModelParamIndex++;
    }

    public void setInstanceSegParam(YCNNModelInfo.KSInstanceSegParam kSInstanceSegParam) {
        this.mInstanceSegParam = kSInstanceSegParam;
        this.mModelParamIndex++;
    }

    public void setLandmarksParam(YCNNModelInfo.KSLandmarksParam kSLandmarksParam) {
        this.mLandmarksParam = kSLandmarksParam;
        this.mModelParamIndex++;
    }

    public void setStyleTransferParam(YCNNModelInfo.KSStyleTransferParam kSStyleTransferParam) {
        this.mStyleTransferParam = kSStyleTransferParam;
        this.mModelParamIndex++;
    }

    public void setTaskDetectParam(YCNNModelInfo.KSTaskDetectParam kSTaskDetectParam) {
        this.mTaskDetectParam = kSTaskDetectParam;
        this.mModelParamIndex++;
    }

    public void setUploadStatsCallBack(uploadStatsCallBack uploadstatscallback, String str) {
        this.mUploadStatsCallBack = uploadstatscallback;
        this.mKSJNILib.setUploadStatsCallBack(this, str);
    }

    public void getContours(YCNNComm.KSImage kSImage, ArrayList<ArrayList<YCNNComm.KSVec2f>> arrayList) {
        this.mKSJNILib.getContour(this, kSImage, arrayList);
    }
}
