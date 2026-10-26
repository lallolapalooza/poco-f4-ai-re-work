package com.yxcorp.gifshow.util.resource.model;

import android.os.SystemClock;
import com.kwai.imsdk.internal.constants.KwaiConstants;
import com.yxcorp.gifshow.api.magicemoji.MagicEmojiPlugin;
import com.yxcorp.gifshow.model.g;
import com.yxcorp.gifshow.model.h;
import com.yxcorp.gifshow.util.o1;
import com.yxcorp.gifshow.util.resource.b0;
import com.yxcorp.gifshow.util.resource.y;
import com.yxcorp.utility.f;
import com.yxcorp.utility.n1;
import com.yxcorp.utility.plugin.PluginManager;
import java.io.File;
import java.nio.charset.Charset;
import jf1.d;

/* JADX INFO: compiled from: MagicModel.java */
/* JADX INFO: loaded from: classes5.dex */
public class c implements h {
    public final String mEventUrl;
    public final String mResource;
    private int mRetryTimes;
    private long mStartDownloadTime;
    private File mResourceTempFile = null;
    private String mResourceDir = null;
    private boolean mReportDownloadCnt = false;

    public c(String str) {
        this.mResource = str;
        this.mEventUrl = "ks://download_" + str;
    }

    private void a() {
        o1.a(new File(this.mResourceDir));
    }

    private void b() {
        File file = this.mResourceTempFile;
        if (file != null) {
            d.l(file);
            this.mResourceTempFile.delete();
        }
    }

    private String c() {
        String strI = b0.i(this.mResource);
        return !n1.p(strI) ? strI : getUniqueIdentifier(b0.j(this.mResource));
    }

    private void d() {
        this.mResourceDir = getResourceDir();
        this.mResourceTempFile = new File(new File(this.mResourceDir).getPath() + "_resource_" + SystemClock.elapsedRealtime());
        new File(this.mResourceDir).renameTo(this.mResourceTempFile);
    }

    @Override // com.yxcorp.gifshow.model.h
    public void addNoMediaFileIfNeed() {
        a();
    }

    public c addToMagicModelsResourceList() {
        com.yxcorp.gifshow.util.magic.b.b(this);
        return this;
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
        b();
    }

    @Override // com.yxcorp.gifshow.model.h
    public /* bridge */ /* synthetic */ void delete() {
        g.a(this);
    }

    @Override // com.yxcorp.gifshow.model.h
    public /* bridge */ /* synthetic */ void doSomethingAfterUnzip() {
        g.b(this);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof c)) {
            return n1.h(this.mResource, ((c) obj).mResource);
        }
        return false;
    }

    @Override // com.yxcorp.gifshow.model.h
    public Charset getCharset() {
        return Charset.defaultCharset();
    }

    @Override // com.yxcorp.gifshow.model.h
    public String getDownloadId() {
        return getResourceName() + KwaiConstants.KEY_SEPARATOR + this.mStartDownloadTime;
    }

    @Override // com.yxcorp.gifshow.model.h
    public String getDownloadUrl(com.yxcorp.gifshow.model.response.c cVar) {
        return getInitDownloadUrl(cVar);
    }

    @Override // com.yxcorp.gifshow.model.h
    public String getInitDownloadUrl(com.yxcorp.gifshow.model.response.c cVar) {
        if (cVar == null) {
            return "";
        }
        this.mStartDownloadTime = System.currentTimeMillis();
        this.mRetryTimes = 0;
        return cVar.getCdnCount(this.mResource) == 0 ? "" : cVar.getDownloadUrlSuffix(this.mResource, 0);
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
        return 4;
    }

    @Override // com.yxcorp.gifshow.model.h
    public String getRetryDownloadUrl(com.yxcorp.gifshow.model.response.c cVar) {
        int i12;
        if (cVar == null) {
            return "";
        }
        int i13 = this.mRetryTimes + 1;
        int cdnCount = cVar.getCdnCount(this.mResource);
        if (i13 >= cdnCount || (i12 = i13 % cdnCount) > cVar.getCdnCount(this.mResource)) {
            return null;
        }
        this.mRetryTimes++;
        return cVar.getDownloadUrlSuffix(this.mResource, i12);
    }

    @Override // com.yxcorp.gifshow.model.h
    public /* bridge */ /* synthetic */ String getUniqueIdentifier(String str) {
        return g.c(this, str);
    }

    @Override // com.yxcorp.gifshow.model.h
    public String getUnzipDir() {
        return com.yxcorp.gifshow.util.magic.c.e(this.mResource);
    }

    @Override // com.yxcorp.gifshow.model.h
    public boolean invalidDownloadUrl(com.yxcorp.gifshow.model.response.c cVar) {
        return false;
    }

    @Override // com.yxcorp.gifshow.model.h
    public boolean isDirExist() {
        return new File(getUnzipDir()).exists();
    }

    @Override // com.yxcorp.gifshow.model.h
    public /* bridge */ /* synthetic */ boolean isResourceFileExist() {
        return g.f(this);
    }

    @Override // com.yxcorp.gifshow.model.h
    public void markHaveDownloaded(String str) {
        String uniqueIdentifier = getUniqueIdentifier(str);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("markHaveDownloaded, uniqueId is: ");
        sb2.append(uniqueIdentifier);
        b0.r(this.mResource, uniqueIdentifier);
        if (this.mReportDownloadCnt) {
            b0.b(this.mResource, uniqueIdentifier);
        }
    }

    @Override // com.yxcorp.gifshow.model.h
    public void markUseless() {
        d();
    }

    @Override // com.yxcorp.gifshow.model.h
    public boolean needCheckFileValid() {
        return true;
    }

    @Override // com.yxcorp.gifshow.model.h
    public boolean needDownload(com.yxcorp.gifshow.model.response.c cVar) {
        if (((MagicEmojiPlugin) PluginManager.get(MagicEmojiPlugin.class)).isBlock()) {
            return false;
        }
        String uniqueIdentifier = getUniqueIdentifier(getInitDownloadUrl(cVar));
        String strC = c();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("newUniqueId, uniqueId is: ");
        sb2.append(uniqueIdentifier);
        StringBuilder sb3 = new StringBuilder();
        sb3.append("oldUniqueId, uniqueId is: ");
        sb3.append(strC);
        if (!n1.p(uniqueIdentifier) && !uniqueIdentifier.equals(strC)) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(this.mResource);
            sb4.append(" newUniqueId: ");
            sb4.append(uniqueIdentifier);
            StringBuilder sb5 = new StringBuilder();
            sb5.append(this.mResource);
            sb5.append(" oldUniqueId: ");
            sb5.append(strC);
            return true;
        }
        File file = new File(getResourceDir());
        StringBuilder sb6 = new StringBuilder();
        sb6.append("magic model resourceDir is ");
        sb6.append(file.toString());
        StringBuilder sb7 = new StringBuilder();
        sb7.append("magic model resourceDir exists? ");
        sb7.append(file.exists());
        StringBuilder sb8 = new StringBuilder();
        sb8.append("magic model resourceDir is empty ");
        sb8.append(f.e(file.listFiles()));
        if (!file.exists() || f.e(file.listFiles())) {
            StringBuilder sb9 = new StringBuilder();
            sb9.append(this.mResource);
            sb9.append(" resourceLose");
            return true;
        }
        StringBuilder sb10 = new StringBuilder();
        sb10.append(this.mResource);
        sb10.append(" newUniqueId == oldUniqueId: ");
        sb10.append(uniqueIdentifier);
        return false;
    }

    @Override // com.yxcorp.gifshow.model.h
    public void removeOutdatedFiles() {
        checkMd5();
    }

    public c reportDownloadCnt() {
        this.mReportDownloadCnt = true;
        return this;
    }

    @Override // com.yxcorp.gifshow.model.h
    public boolean useYcnnModelConfig() {
        return true;
    }
}
