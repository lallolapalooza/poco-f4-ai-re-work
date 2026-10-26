package com.yxcorp.gifshow.model.response;

import com.kuaishou.weapon.gp.c5;
import com.yxcorp.bugly.CrashReporter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: ConfigResponse.java */
/* JADX INFO: loaded from: classes4.dex */
public class i implements com.yxcorp.gifshow.model.response.c, Serializable, Cloneable {
    private static final long serialVersionUID = -3467331090557395647L;

    @lb.c("amazing_comment_resource")
    public String mAmazingCommentResource;

    @lb.c("commentEffectsConfig")
    public String mCommentEffectConfig;

    @lb.c("comment_like_motion_resource")
    public String mCommentLikeEffectsResource;

    @lb.c("default_resource_v2")
    public String mDefaultResource;

    @lb.c("system_emoji_resource")
    public String mEmojiResource;

    @lb.c("filter_resource_v3")
    public String mFilterResource;

    @lb.c("kwai_emoji_resource_addition")
    public String mKwaiEmojiResource;

    @lb.c("kwaiPreloadModels")
    public a mMagicPreloadModel;

    @lb.c("model_matting_v1")
    public String mModelMatting;

    @lb.c("modelResources")
    public Map<String, String> mModelResources;

    @lb.c("christmasPhotoLikeConf")
    public c mPhotoLikeConfig;

    @lb.c("sticker_resource_v2")
    public String mStickerResource;

    @lb.c("theme_resource_v2")
    public String mThemeResource;

    @lb.c("prefixes")
    public List<String> mUrlPrefixes;

    @lb.c("comment_keyword_motion_resource")
    public String mVideoCommentEffectsResource;

    @lb.c("like_motion_resource")
    public String mVideoLikeEffectsResource;

    @lb.c("voice_detect_mode_v1")
    public String mVoiceDetect;

    @lb.c("origin_name_on")
    public boolean mOriginNameOn = false;

    @lb.c("auto_origin_name_on")
    public boolean mAutoOriginNameOn = false;

    @lb.c("upload_contacts_interval")
    public long mUploadContactsInterval = -1;

    @lb.c("new_upload_contacts_percentage")
    public float mUploadContactsPercentage = 0.0f;

    /* JADX INFO: compiled from: ConfigResponse.java */
    public static class a {

        @lb.c("config")
        public b mMagicPreloadModelConfig;
    }

    /* JADX INFO: compiled from: ConfigResponse.java */
    public static class b {

        @lb.c("4G")
        public List<String> m4G;

        @lb.c("2G")
        public List<String> mOther;

        @lb.c(c5.f21816b)
        public List<String> mWifi;
    }

    /* JADX INFO: compiled from: ConfigResponse.java */
    public static class c {

        @lb.c("begin_time")
        public long mBeginTime;

        @lb.c("end_time")
        public long mEndTime;

        @lb.c("material_url")
        public String mMaterialName;
    }

    @Override // com.yxcorp.gifshow.model.response.c
    public int getCdnCount(String str) {
        return 0;
    }

    @Override // com.yxcorp.gifshow.model.response.c
    public String getDownloadUrlSuffix(String str, int i12) {
        return null;
    }

    public boolean is4GEmpty() {
        b bVar;
        List<String> list;
        a aVar = this.mMagicPreloadModel;
        return aVar == null || (bVar = aVar.mMagicPreloadModelConfig) == null || (list = bVar.m4G) == null || list.isEmpty();
    }

    public boolean isOtherEmpty() {
        b bVar;
        List<String> list;
        a aVar = this.mMagicPreloadModel;
        return aVar == null || (bVar = aVar.mMagicPreloadModelConfig) == null || (list = bVar.mOther) == null || list.isEmpty();
    }

    public boolean isWifiEmpty() {
        b bVar;
        List<String> list;
        a aVar = this.mMagicPreloadModel;
        return aVar == null || (bVar = aVar.mMagicPreloadModelConfig) == null || (list = bVar.mWifi) == null || list.isEmpty();
    }

    public void resetResourceVersion() {
        this.mDefaultResource = null;
        this.mEmojiResource = null;
        this.mFilterResource = null;
        this.mStickerResource = null;
        this.mThemeResource = null;
        this.mCommentEffectConfig = null;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public i m183clone() {
        try {
            i iVar = (i) super.clone();
            iVar.mUrlPrefixes = new ArrayList(this.mUrlPrefixes);
            return iVar;
        } catch (CloneNotSupportedException e12) {
            CrashReporter.logCheckedException(e12, "ConfigResponse.class", "clone", -107);
            return null;
        }
    }
}
