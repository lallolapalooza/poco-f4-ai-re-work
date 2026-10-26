package com.yxcorp.gifshow.util.resource.model;

import android.os.Build;
import android.os.SystemClock;
import com.yxcorp.gifshow.api.emoji.EmojiPlugin;
import com.yxcorp.gifshow.util.o1;
import com.yxcorp.gifshow.util.resource.y;
import com.yxcorp.utility.n1;
import com.yxcorp.utility.plugin.PluginManager;
import java.io.File;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: Category.java */
/* JADX INFO: loaded from: classes5.dex */
public abstract class a implements com.yxcorp.gifshow.model.h {
    private static final /* synthetic */ a[] $VALUES;
    public static final a AMAZING_COMMENT_EFFECT;
    public static final a COMMENT_EFFECT;
    public static final a COMMENT_LIKE_EFFECTS_RESOURCE;
    public static final a CUT_MATTING;
    public static final a EDITOR;
    public static final a EMOJI;
    public static final a KWAI_EMOJI;
    public static final a STICKER;
    public static final a THEME;
    public static final a VIDEO_COMMENT_EFFECTS;
    public static final a VIDEO_LIKE_EFFECTS;
    public static final a VOICE_DETECT;
    public String mEventUrl;
    public String mResource;
    com.yxcorp.gifshow.util.resource.g mResourceCleaner;
    String mResourceDir;
    File mResourceTempFile;
    private int mRetryTimes;

    /* JADX INFO: compiled from: Category.java */
    final enum d extends a {
        String mUnzipDir;

        d(String str, int i12, String str2, String str3) {
            super(str, i12, str2, str3, null);
        }

        @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
        public /* bridge */ /* synthetic */ void delete() {
            com.yxcorp.gifshow.model.g.a(this);
        }

        @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
        public /* bridge */ /* synthetic */ void doSomethingAfterUnzip() {
            com.yxcorp.gifshow.model.g.b(this);
        }

        @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
        public String getResourceDir() {
            return getUnzipDir();
        }

        @Override // com.yxcorp.gifshow.util.resource.model.a
        String getResponseName(com.yxcorp.gifshow.model.response.i iVar) {
            return iVar.mAmazingCommentResource;
        }

        @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
        public /* bridge */ /* synthetic */ String getUniqueIdentifier(String str) {
            return com.yxcorp.gifshow.model.g.c(this, str);
        }

        @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
        public String getUnzipDir() {
            if (this.mUnzipDir == null) {
                List<File> listD = com.kscorp.oversea.framework.datastore.file.a.f17509g.d(true);
                Iterator<File> it2 = listD.iterator();
                while (it2.hasNext()) {
                    File file = new File(it2.next(), this.mResource);
                    if (file.exists() && file.isDirectory()) {
                        this.mUnzipDir = o1.i(file.getAbsolutePath());
                        break;
                    }
                }
                if (n1.p(this.mUnzipDir)) {
                    this.mUnzipDir = o1.i(new File(listD.get(0), this.mResource).getAbsolutePath());
                }
            }
            return this.mUnzipDir;
        }

        @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
        public /* bridge */ /* synthetic */ boolean isResourceFileExist() {
            return com.yxcorp.gifshow.model.g.f(this);
        }

        @Override // com.yxcorp.gifshow.util.resource.model.a
        public boolean needDownload(@e.a com.yxcorp.gifshow.model.response.i iVar, @e.a com.yxcorp.gifshow.model.response.i iVar2) {
            if (!n1.p(iVar2.mAmazingCommentResource) && !iVar2.mAmazingCommentResource.equals(iVar.mAmazingCommentResource)) {
                return true;
            }
            File file = new File(getResourceDir());
            return !file.exists() || com.yxcorp.utility.f.e(file.listFiles());
        }

        @Override // com.yxcorp.gifshow.util.resource.model.a
        public void updateResponseName(com.yxcorp.gifshow.model.response.i iVar, com.yxcorp.gifshow.model.response.i iVar2) {
            iVar2.mDefaultResource = getResponseName(iVar);
        }

        @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
        public /* bridge */ /* synthetic */ boolean useYcnnModelConfig() {
            return com.yxcorp.gifshow.model.g.g(this);
        }
    }

    static {
        d dVar = new d("AMAZING_COMMENT_EFFECT", 0, "amazing_comment_effect", "ks://download_amazing_comment_effect");
        AMAZING_COMMENT_EFFECT = dVar;
        a aVar = new a("COMMENT_EFFECT", 1, "comment_effect", "ks://download_comment_effect_resource") { // from class: com.yxcorp.gifshow.util.resource.model.a.e
            String mUnzipDir;

            {
                d dVar2 = null;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ void delete() {
                com.yxcorp.gifshow.model.g.a(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ void doSomethingAfterUnzip() {
                com.yxcorp.gifshow.model.g.b(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public String getResourceDir() {
                return getUnzipDir();
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            String getResponseName(com.yxcorp.gifshow.model.response.i iVar) {
                return iVar.mCommentEffectConfig;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ String getUniqueIdentifier(String str) {
                return com.yxcorp.gifshow.model.g.c(this, str);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public String getUnzipDir() {
                if (this.mUnzipDir == null) {
                    List<File> listD = com.kscorp.oversea.framework.datastore.file.a.f17509g.d(true);
                    Iterator<File> it2 = listD.iterator();
                    while (it2.hasNext()) {
                        File file = new File(it2.next(), this.mResource);
                        if (file.exists() && file.isDirectory()) {
                            this.mUnzipDir = o1.i(file.getAbsolutePath());
                            break;
                        }
                    }
                    if (n1.p(this.mUnzipDir)) {
                        this.mUnzipDir = o1.i(new File(listD.get(0), this.mResource).getAbsolutePath());
                    }
                }
                return this.mUnzipDir;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ boolean isResourceFileExist() {
                return com.yxcorp.gifshow.model.g.f(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            public boolean needDownload(@e.a com.yxcorp.gifshow.model.response.i iVar, @e.a com.yxcorp.gifshow.model.response.i iVar2) {
                if (1 != d20.e.t()) {
                    return false;
                }
                if (!n1.p(iVar2.mCommentEffectConfig) && !iVar2.mCommentEffectConfig.equals(iVar.mCommentEffectConfig)) {
                    return true;
                }
                File file = new File(getResourceDir());
                return !file.exists() || com.yxcorp.utility.f.e(file.listFiles());
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            public void updateResponseName(com.yxcorp.gifshow.model.response.i iVar, com.yxcorp.gifshow.model.response.i iVar2) {
                iVar2.mDefaultResource = getResponseName(iVar);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ boolean useYcnnModelConfig() {
                return com.yxcorp.gifshow.model.g.g(this);
            }
        };
        COMMENT_EFFECT = aVar;
        a aVar2 = new a("EDITOR", 2, "resource", "ks://download_video_music_resource") { // from class: com.yxcorp.gifshow.util.resource.model.a.f
            {
                d dVar2 = null;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ void delete() {
                com.yxcorp.gifshow.model.g.a(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ void doSomethingAfterUnzip() {
                com.yxcorp.gifshow.model.g.b(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public String getResourceDir() {
                return getUnzipDir() + this.mResource + File.separator;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public int getResourceType() {
                return 6;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            String getResponseName(com.yxcorp.gifshow.model.response.i iVar) {
                return iVar.mDefaultResource;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ String getUniqueIdentifier(String str) {
                return com.yxcorp.gifshow.model.g.c(this, str);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public String getUnzipDir() {
                List<File> listD = pd.a.b().d(true);
                for (File file : listD) {
                    File file2 = new File(file, this.mResource);
                    if (file2.exists() && file2.isDirectory()) {
                        return o1.i(file.getAbsolutePath());
                    }
                }
                return com.yxcorp.utility.i.d(listD) ? "" : o1.i(listD.get(0).getAbsolutePath());
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ boolean isResourceFileExist() {
                return com.yxcorp.gifshow.model.g.f(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            boolean needCleanAllResources() {
                return true;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            public void updateResponseName(com.yxcorp.gifshow.model.response.i iVar, com.yxcorp.gifshow.model.response.i iVar2) {
                iVar2.mDefaultResource = getResponseName(iVar);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ boolean useYcnnModelConfig() {
                return com.yxcorp.gifshow.model.g.g(this);
            }
        };
        EDITOR = aVar2;
        a aVar3 = new a("STICKER", 3, "sticker_resource_v2", "ks://sticker_resource_v2") { // from class: com.yxcorp.gifshow.util.resource.model.a.g
            {
                d dVar2 = null;
            }

            private String getFileName() {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(".sticker");
                String str = File.separator;
                sb2.append(str);
                sb2.append(this.mResource);
                sb2.append(str);
                return sb2.toString();
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ void delete() {
                com.yxcorp.gifshow.model.g.a(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ void doSomethingAfterUnzip() {
                com.yxcorp.gifshow.model.g.b(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public int getResourceType() {
                return 6;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            String getResponseName(com.yxcorp.gifshow.model.response.i iVar) {
                return iVar.mStickerResource;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ String getUniqueIdentifier(String str) {
                return com.yxcorp.gifshow.model.g.c(this, str);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public String getUnzipDir() {
                String fileName = getFileName();
                List<File> listD = pd.a.b().d(true);
                Iterator<File> it2 = listD.iterator();
                while (it2.hasNext()) {
                    File file = new File(it2.next(), fileName);
                    if (file.exists() && file.isDirectory()) {
                        return o1.i(file.getAbsolutePath());
                    }
                }
                return com.yxcorp.utility.i.d(listD) ? "" : o1.i(new File(listD.get(0), fileName).getAbsolutePath());
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ boolean isResourceFileExist() {
                return com.yxcorp.gifshow.model.g.f(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            boolean needCleanAllResources() {
                return true;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            public void updateResponseName(com.yxcorp.gifshow.model.response.i iVar, com.yxcorp.gifshow.model.response.i iVar2) {
                iVar2.mStickerResource = getResponseName(iVar);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ boolean useYcnnModelConfig() {
                return com.yxcorp.gifshow.model.g.g(this);
            }
        };
        STICKER = aVar3;
        a aVar4 = new a("KWAI_EMOJI", 4, "kwai_emoji_resource_addition", "ks://download_kwai_emoji_resource_addition") { // from class: com.yxcorp.gifshow.util.resource.model.a.h
            {
                d dVar2 = null;
            }

            private String getFileName() {
                return ".kwai_emoji_resource_addition";
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ void delete() {
                com.yxcorp.gifshow.model.g.a(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public void doSomethingAfterUnzip() {
                ((EmojiPlugin) PluginManager.get(EmojiPlugin.class)).onKwaiEmojiUnzipFinished();
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public int getResourceType() {
                return 7;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            String getResponseName(com.yxcorp.gifshow.model.response.i iVar) {
                return iVar.mKwaiEmojiResource;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ String getUniqueIdentifier(String str) {
                return com.yxcorp.gifshow.model.g.c(this, str);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public String getUnzipDir() {
                String fileName = getFileName();
                List<File> listD = pd.a.b().d(true);
                Iterator<File> it2 = listD.iterator();
                while (it2.hasNext()) {
                    File file = new File(it2.next(), fileName);
                    if (file.exists() && file.isDirectory()) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Emoji表情包文件所在目录:");
                        sb2.append(o1.i(file.getAbsolutePath()));
                        return o1.i(file.getAbsolutePath());
                    }
                }
                if (com.yxcorp.utility.i.d(listD)) {
                    return "";
                }
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Emoji表情包文件所在目录:");
                sb3.append(o1.i(new File(listD.get(0), fileName).getAbsolutePath()));
                return o1.i(new File(listD.get(0), fileName).getAbsolutePath());
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ boolean isResourceFileExist() {
                return com.yxcorp.gifshow.model.g.f(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            boolean needCleanAllResources() {
                return true;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            public void updateResponseName(com.yxcorp.gifshow.model.response.i iVar, com.yxcorp.gifshow.model.response.i iVar2) {
                iVar2.mKwaiEmojiResource = getResponseName(iVar);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ boolean useYcnnModelConfig() {
                return com.yxcorp.gifshow.model.g.g(this);
            }
        };
        KWAI_EMOJI = aVar4;
        a aVar5 = new a("THEME", 5, "theme_resource", "ks://download_theme_resource") { // from class: com.yxcorp.gifshow.util.resource.model.a.i
            {
                d dVar2 = null;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ void delete() {
                com.yxcorp.gifshow.model.g.a(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ void doSomethingAfterUnzip() {
                com.yxcorp.gifshow.model.g.b(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public int getResourceType() {
                return 15;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            String getResponseName(com.yxcorp.gifshow.model.response.i iVar) {
                return iVar.mThemeResource;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ String getUniqueIdentifier(String str) {
                return com.yxcorp.gifshow.model.g.c(this, str);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public String getUnzipDir() {
                List<File> listD = pd.a.b().d(true);
                String str = this.mResource;
                Iterator<File> it2 = listD.iterator();
                while (it2.hasNext()) {
                    File file = new File(it2.next(), str);
                    if (file.exists() && file.isDirectory()) {
                        return o1.i(file.getAbsolutePath());
                    }
                }
                return com.yxcorp.utility.i.d(listD) ? "" : o1.i(new File(listD.get(0), str).getAbsolutePath());
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ boolean isResourceFileExist() {
                return com.yxcorp.gifshow.model.g.f(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            boolean needCleanAllResources() {
                return true;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            public void updateResponseName(com.yxcorp.gifshow.model.response.i iVar, com.yxcorp.gifshow.model.response.i iVar2) {
                iVar2.mThemeResource = getResponseName(iVar);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ boolean useYcnnModelConfig() {
                return com.yxcorp.gifshow.model.g.g(this);
            }
        };
        THEME = aVar5;
        a aVar6 = new a("EMOJI", 6, "system_emoji_resource", "ks://download_system_emoji_resource") { // from class: com.yxcorp.gifshow.util.resource.model.a.j
            {
                d dVar2 = null;
            }

            private String getFileName() {
                return ".emoji";
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ void delete() {
                com.yxcorp.gifshow.model.g.a(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public void doSomethingAfterUnzip() {
                ((EmojiPlugin) PluginManager.get(EmojiPlugin.class)).onEmojiUnzipFinished();
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public int getResourceType() {
                return 7;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            String getResponseName(com.yxcorp.gifshow.model.response.i iVar) {
                return iVar.mEmojiResource;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ String getUniqueIdentifier(String str) {
                return com.yxcorp.gifshow.model.g.c(this, str);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public String getUnzipDir() {
                String fileName = getFileName();
                List<File> listD = pd.a.b().d(true);
                Iterator<File> it2 = listD.iterator();
                while (it2.hasNext()) {
                    File file = new File(it2.next(), fileName);
                    if (file.exists() && file.isDirectory()) {
                        return o1.i(file.getAbsolutePath());
                    }
                }
                return com.yxcorp.utility.i.d(listD) ? "" : o1.i(new File(listD.get(0), fileName).getAbsolutePath());
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ boolean isResourceFileExist() {
                return com.yxcorp.gifshow.model.g.f(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            boolean needCleanAllResources() {
                return true;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            public boolean needDownload(@e.a com.yxcorp.gifshow.model.response.i iVar, @e.a com.yxcorp.gifshow.model.response.i iVar2) {
                if (Build.VERSION.SDK_INT >= 19) {
                    return false;
                }
                return super.needDownload(iVar, iVar2);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            public boolean needUpdateRes(@e.a com.yxcorp.gifshow.model.response.i iVar, @e.a com.yxcorp.gifshow.model.response.i iVar2) {
                if (Build.VERSION.SDK_INT >= 19) {
                    return false;
                }
                return super.needUpdateRes(iVar, iVar2);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            public void updateResponseName(com.yxcorp.gifshow.model.response.i iVar, com.yxcorp.gifshow.model.response.i iVar2) {
                iVar2.mEmojiResource = getResponseName(iVar);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ boolean useYcnnModelConfig() {
                return com.yxcorp.gifshow.model.g.g(this);
            }
        };
        EMOJI = aVar6;
        a aVar7 = new a("VIDEO_LIKE_EFFECTS", 7, "effects_like_motion", "ks://download_model_video_like_effects") { // from class: com.yxcorp.gifshow.util.resource.model.a.k
            {
                d dVar2 = null;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ void delete() {
                com.yxcorp.gifshow.model.g.a(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ void doSomethingAfterUnzip() {
                com.yxcorp.gifshow.model.g.b(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            String getResponseName(com.yxcorp.gifshow.model.response.i iVar) {
                return iVar.mVideoLikeEffectsResource;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ String getUniqueIdentifier(String str) {
                return com.yxcorp.gifshow.model.g.c(this, str);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public String getUnzipDir() {
                return com.yxcorp.gifshow.helper.festival.b.f40963a.d();
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ boolean isResourceFileExist() {
                return com.yxcorp.gifshow.model.g.f(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            public void updateResponseName(com.yxcorp.gifshow.model.response.i iVar, com.yxcorp.gifshow.model.response.i iVar2) {
                iVar2.mVideoLikeEffectsResource = getResponseName(iVar);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ boolean useYcnnModelConfig() {
                return com.yxcorp.gifshow.model.g.g(this);
            }
        };
        VIDEO_LIKE_EFFECTS = aVar7;
        a aVar8 = new a("VIDEO_COMMENT_EFFECTS", 8, "effects_comment_keyword", "ks://download_model_video_comment_effects") { // from class: com.yxcorp.gifshow.util.resource.model.a.l
            {
                d dVar2 = null;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ void delete() {
                com.yxcorp.gifshow.model.g.a(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ void doSomethingAfterUnzip() {
                com.yxcorp.gifshow.model.g.b(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            String getResponseName(com.yxcorp.gifshow.model.response.i iVar) {
                return iVar.mVideoCommentEffectsResource;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ String getUniqueIdentifier(String str) {
                return com.yxcorp.gifshow.model.g.c(this, str);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public String getUnzipDir() {
                return com.yxcorp.gifshow.helper.festival.b.f40963a.c();
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ boolean isResourceFileExist() {
                return com.yxcorp.gifshow.model.g.f(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            public void updateResponseName(com.yxcorp.gifshow.model.response.i iVar, com.yxcorp.gifshow.model.response.i iVar2) {
                iVar2.mVideoCommentEffectsResource = getResponseName(iVar);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ boolean useYcnnModelConfig() {
                return com.yxcorp.gifshow.model.g.g(this);
            }
        };
        VIDEO_COMMENT_EFFECTS = aVar8;
        a aVar9 = new a("COMMENT_LIKE_EFFECTS_RESOURCE", 9, "effects_comment_like_motion", "ks://download_model_comment_like_effects") { // from class: com.yxcorp.gifshow.util.resource.model.a.a
            {
                d dVar2 = null;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ void delete() {
                com.yxcorp.gifshow.model.g.a(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ void doSomethingAfterUnzip() {
                com.yxcorp.gifshow.model.g.b(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            String getResponseName(com.yxcorp.gifshow.model.response.i iVar) {
                return iVar.mCommentLikeEffectsResource;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ String getUniqueIdentifier(String str) {
                return com.yxcorp.gifshow.model.g.c(this, str);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public String getUnzipDir() {
                return com.yxcorp.gifshow.helper.festival.b.f40963a.a();
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ boolean isResourceFileExist() {
                return com.yxcorp.gifshow.model.g.f(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            public void updateResponseName(com.yxcorp.gifshow.model.response.i iVar, com.yxcorp.gifshow.model.response.i iVar2) {
                iVar2.mCommentLikeEffectsResource = getResponseName(iVar);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ boolean useYcnnModelConfig() {
                return com.yxcorp.gifshow.model.g.g(this);
            }
        };
        COMMENT_LIKE_EFFECTS_RESOURCE = aVar9;
        a aVar10 = new a("CUT_MATTING", 10, "model_matting_v1", "ks://download_model_matting") { // from class: com.yxcorp.gifshow.util.resource.model.a.b
            {
                d dVar2 = null;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ void delete() {
                com.yxcorp.gifshow.model.g.a(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ void doSomethingAfterUnzip() {
                com.yxcorp.gifshow.model.g.b(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public int getResourceType() {
                return 4;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            String getResponseName(com.yxcorp.gifshow.model.response.i iVar) {
                return iVar.mModelMatting;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ String getUniqueIdentifier(String str) {
                return com.yxcorp.gifshow.model.g.c(this, str);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public String getUnzipDir() {
                return com.yxcorp.gifshow.util.magic.c.e(this.mResource);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ boolean isResourceFileExist() {
                return com.yxcorp.gifshow.model.g.f(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            public void updateResponseName(com.yxcorp.gifshow.model.response.i iVar, com.yxcorp.gifshow.model.response.i iVar2) {
                iVar2.mModelMatting = getResponseName(iVar);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ boolean useYcnnModelConfig() {
                return com.yxcorp.gifshow.model.g.g(this);
            }
        };
        CUT_MATTING = aVar10;
        a aVar11 = new a("VOICE_DETECT", 11, "voice_detect_mode", "ks://download_voice_detect_mode") { // from class: com.yxcorp.gifshow.util.resource.model.a.c
            {
                d dVar2 = null;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ void delete() {
                com.yxcorp.gifshow.model.g.a(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ void doSomethingAfterUnzip() {
                com.yxcorp.gifshow.model.g.b(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public Charset getCharset() {
                return Charset.defaultCharset();
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public String getResourceName() {
                return "voice_detect_mode.tflite";
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            String getResponseName(com.yxcorp.gifshow.model.response.i iVar) {
                return iVar.mVoiceDetect;
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ String getUniqueIdentifier(String str) {
                return com.yxcorp.gifshow.model.g.c(this, str);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public String getUnzipDir() {
                return com.yxcorp.gifshow.util.magic.c.e(this.mResource);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ boolean isResourceFileExist() {
                return com.yxcorp.gifshow.model.g.f(this);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a
            public void updateResponseName(com.yxcorp.gifshow.model.response.i iVar, com.yxcorp.gifshow.model.response.i iVar2) {
                iVar2.mVoiceDetect = getResponseName(iVar);
            }

            @Override // com.yxcorp.gifshow.util.resource.model.a, com.yxcorp.gifshow.model.h
            public /* bridge */ /* synthetic */ boolean useYcnnModelConfig() {
                return com.yxcorp.gifshow.model.g.g(this);
            }
        };
        VOICE_DETECT = aVar11;
        $VALUES = new a[]{dVar, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10, aVar11};
    }

    /* synthetic */ a(String str, int i12, String str2, String str3, d dVar) {
        this(str, i12, str2, str3);
    }

    private void addNoMediaFileIfNeedInner() {
        o1.a(new File(this.mResourceDir));
    }

    private void cleanRedundantDataInner() {
        File file = this.mResourceTempFile;
        if (file != null) {
            jf1.d.l(file);
            this.mResourceTempFile.delete();
        }
    }

    private void markUselessInner() {
        this.mResourceDir = getResourceDir();
        this.mResourceTempFile = new File(new File(this.mResourceDir).getPath() + "_resource_" + SystemClock.elapsedRealtime());
        new File(this.mResourceDir).renameTo(this.mResourceTempFile);
    }

    public static a valueOf(String str) {
        return (a) Enum.valueOf(a.class, str);
    }

    public static a[] values() {
        return (a[]) $VALUES.clone();
    }

    @Override // com.yxcorp.gifshow.model.h
    public void addNoMediaFileIfNeed() {
        com.yxcorp.gifshow.util.resource.g gVar;
        if (!needCleanAllResources() || (gVar = this.mResourceCleaner) == null) {
            addNoMediaFileIfNeedInner();
        } else {
            gVar.a(this);
        }
    }

    @Override // com.yxcorp.gifshow.model.h
    public final boolean checkMd5() {
        if (!needCheckFileValid()) {
            return true;
        }
        boolean zK = com.yxcorp.gifshow.util.magic.b.k(getResourceDir());
        if (!zK) {
            y.x(this);
        }
        return zK;
    }

    @Override // com.yxcorp.gifshow.model.h
    public void cleanRedundantData() {
        com.yxcorp.gifshow.util.resource.g gVar;
        if (!needCleanAllResources() || (gVar = this.mResourceCleaner) == null) {
            cleanRedundantDataInner();
        } else {
            gVar.b();
        }
    }

    @Override // com.yxcorp.gifshow.model.h
    public /* bridge */ /* synthetic */ void delete() {
        com.yxcorp.gifshow.model.g.a(this);
    }

    @Override // com.yxcorp.gifshow.model.h
    public /* bridge */ /* synthetic */ void doSomethingAfterUnzip() {
        com.yxcorp.gifshow.model.g.b(this);
    }

    public void fixNoMediaFileLost() {
        o1.a(new File(getResourceDir()));
    }

    @Override // com.yxcorp.gifshow.model.h
    public Charset getCharset() {
        return null;
    }

    @Override // com.yxcorp.gifshow.model.h
    public String getDownloadId() {
        return null;
    }

    @Override // com.yxcorp.gifshow.model.h
    public final String getDownloadUrl(com.yxcorp.gifshow.model.response.c cVar) {
        if (!(cVar instanceof com.yxcorp.gifshow.model.response.i)) {
            return null;
        }
        com.yxcorp.gifshow.model.response.i iVar = (com.yxcorp.gifshow.model.response.i) cVar;
        return iVar.mUrlPrefixes.get(0) + File.separator + getResponseName(iVar);
    }

    @Override // com.yxcorp.gifshow.model.h
    public String getInitDownloadUrl(com.yxcorp.gifshow.model.response.c cVar) {
        if (!(cVar instanceof com.yxcorp.gifshow.model.response.i)) {
            return "";
        }
        this.mRetryTimes = 0;
        com.yxcorp.gifshow.model.response.i iVar = (com.yxcorp.gifshow.model.response.i) cVar;
        List<String> list = iVar.mUrlPrefixes;
        if (com.yxcorp.utility.i.d(list)) {
            return "";
        }
        return list.get(0) + File.separator + getResponseName(iVar);
    }

    @Override // com.yxcorp.gifshow.model.h
    public String getResourceDir() {
        return getUnzipDir();
    }

    @Override // com.yxcorp.gifshow.model.h
    public String getResourceName() {
        return this.mResource;
    }

    @Override // com.yxcorp.gifshow.model.h
    public int getResourceType() {
        return 0;
    }

    abstract String getResponseName(com.yxcorp.gifshow.model.response.i iVar);

    @Override // com.yxcorp.gifshow.model.h
    public String getRetryDownloadUrl(com.yxcorp.gifshow.model.response.c cVar) {
        int i12;
        int size;
        int i13;
        if (!(cVar instanceof com.yxcorp.gifshow.model.response.i)) {
            return "";
        }
        com.yxcorp.gifshow.model.response.i iVar = (com.yxcorp.gifshow.model.response.i) cVar;
        List<String> list = iVar.mUrlPrefixes;
        if (com.yxcorp.utility.i.d(list) || (i12 = this.mRetryTimes + 1) >= (size = list.size()) || (i13 = i12 % size) > size) {
            return "";
        }
        this.mRetryTimes++;
        return list.get(i13) + File.separator + getResponseName(iVar);
    }

    @Override // com.yxcorp.gifshow.model.h
    public /* bridge */ /* synthetic */ String getUniqueIdentifier(String str) {
        return com.yxcorp.gifshow.model.g.c(this, str);
    }

    @Override // com.yxcorp.gifshow.model.h
    public String getUnzipDir() {
        return null;
    }

    @Override // com.yxcorp.gifshow.model.h
    public boolean invalidDownloadUrl(com.yxcorp.gifshow.model.response.c cVar) {
        if (cVar instanceof com.yxcorp.gifshow.model.response.i) {
            return n1.p(getResponseName((com.yxcorp.gifshow.model.response.i) cVar));
        }
        return false;
    }

    @Override // com.yxcorp.gifshow.model.h
    public boolean isDirExist() {
        String unzipDir = getUnzipDir();
        if (n1.p(unzipDir)) {
            return false;
        }
        return new File(unzipDir).exists();
    }

    @Override // com.yxcorp.gifshow.model.h
    public /* bridge */ /* synthetic */ boolean isResourceFileExist() {
        return com.yxcorp.gifshow.model.g.f(this);
    }

    @Override // com.yxcorp.gifshow.model.h
    public void markHaveDownloaded(String str) {
        y.i0(this);
    }

    @Override // com.yxcorp.gifshow.model.h
    public void markUseless() {
        com.yxcorp.gifshow.util.resource.g gVar;
        if (!needCleanAllResources() || (gVar = this.mResourceCleaner) == null) {
            markUselessInner();
        } else {
            gVar.c(this.mResource);
        }
    }

    @Override // com.yxcorp.gifshow.model.h
    public boolean needCheckFileValid() {
        return false;
    }

    boolean needCleanAllResources() {
        return false;
    }

    @Override // com.yxcorp.gifshow.model.h
    public boolean needDownload(com.yxcorp.gifshow.model.response.c cVar) {
        return false;
    }

    public boolean needDownload(@e.a com.yxcorp.gifshow.model.response.i iVar, @e.a com.yxcorp.gifshow.model.response.i iVar2) {
        if (needUpdateRes(iVar, iVar2)) {
            return true;
        }
        return !y.r0(this);
    }

    public boolean needUpdateRes(@e.a com.yxcorp.gifshow.model.response.i iVar, @e.a com.yxcorp.gifshow.model.response.i iVar2) {
        return (n1.p(getResponseName(iVar2)) || getResponseName(iVar2).equals(getResponseName(iVar))) ? false : true;
    }

    @Override // com.yxcorp.gifshow.model.h
    public final void removeOutdatedFiles() {
        if (needCheckFileValid()) {
            checkMd5();
        }
    }

    public abstract void updateResponseName(com.yxcorp.gifshow.model.response.i iVar, com.yxcorp.gifshow.model.response.i iVar2);

    @Override // com.yxcorp.gifshow.model.h
    public /* bridge */ /* synthetic */ boolean useYcnnModelConfig() {
        return com.yxcorp.gifshow.model.g.g(this);
    }

    private a(String str, int i12, String str2, String str3) {
        super(str, i12);
        this.mResourceTempFile = null;
        this.mResourceDir = null;
        this.mResource = str2;
        this.mEventUrl = str3;
        if (needCleanAllResources()) {
            this.mResourceCleaner = new com.yxcorp.gifshow.util.resource.g(this);
        }
    }
}
