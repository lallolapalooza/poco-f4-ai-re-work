package com.quicinc.voice.activation.soundmodel;

import G.q;
import android.os.Bundle;
import com.quicinc.voice.activation.configuration.v4.DeviceConfigurationKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import v.c;
import v.d;
import v.f;
import v.g;
import v.j;

/* JADX INFO: loaded from: classes.dex */
public class BigSoundModel {
    private boolean isConfigurationUpdated;
    private c mSoundModelInfo;
    private j mSoundModelKey = null;
    private g mSoundModelFileInfo = null;
    private String mMagicNumber = null;
    private SVAVersion mVersion = null;
    private String mKeyword = null;
    private String mUserId = null;
    private String mLocale = null;
    private final List mSoundModelList = new ArrayList();
    private Bundle mSoundModelConfiguration = null;

    private boolean contain2ndStageSoundModel() {
        if (getSoundModelCount() > 1) {
            Iterator it = this.mSoundModelList.iterator();
            while (it.hasNext()) {
                if (p007h.c.b((SoundModel) it.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean getBypass() {
        Bundle bundle = this.mSoundModelConfiguration;
        if (bundle != null) {
            return bundle.getBoolean(DeviceConfigurationKey.f409l.b());
        }
        q.j("SoundModelConfiguration null");
        return false;
    }

    public void addSoundModel(SoundModel soundModel) {
        this.mSoundModelList.add(soundModel);
    }

    public String getBigSoundModelLocale() {
        return this.mLocale;
    }

    public String getCryptPrettyName() {
        return this.mSoundModelFileInfo.b();
    }

    public String getKeyword() {
        String str = this.mKeyword;
        if (str != null) {
            return str;
        }
        q.j("getKeyword null");
        return "";
    }

    public String getLocale() {
        return this.mSoundModelKey.c();
    }

    public String getLocaleInFileName() {
        return this.mSoundModelFileInfo.d();
    }

    public String getMagicNumber() {
        String str = this.mMagicNumber;
        if (str != null) {
            return str;
        }
        q.j("getMagicNumber null");
        return "";
    }

    public Bundle getSoundModelConfiguration() {
        Bundle bundle = this.mSoundModelConfiguration;
        if (bundle != null) {
            return bundle;
        }
        q.j("getSoundModelConfiguration null");
        return new Bundle();
    }

    public int getSoundModelCount() {
        return this.mSoundModelList.size();
    }

    public String getSoundModelFileName() {
        j jVar = this.mSoundModelKey;
        if (jVar != null) {
            return jVar.d();
        }
        q.j("getSoundModelFileName null");
        return "";
    }

    public c getSoundModelInfo() {
        if (this.mSoundModelInfo == null || this.isConfigurationUpdated) {
            this.mSoundModelInfo = f.a(this);
            this.isConfigurationUpdated = false;
        }
        return this.mSoundModelInfo;
    }

    public j getSoundModelKey() {
        return this.mSoundModelKey;
    }

    public List getSoundModelList() {
        return this.mSoundModelList;
    }

    public int getSoundModelVersion() {
        return this.mSoundModelFileInfo.f();
    }

    public BigSoundModelType getType() {
        return this.mSoundModelFileInfo.e();
    }

    public String getUserId() {
        String str = this.mUserId;
        if (str != null) {
            return str;
        }
        q.j("getUserId null");
        return "";
    }

    public SVAVersion getVersion() {
        SVAVersion sVAVersion = this.mVersion;
        if (sVAVersion != null) {
            return sVAVersion;
        }
        q.j("getVersion null");
        return SVAVersion.VERSION_UNKNOWN;
    }

    public boolean needEnable2ndAlgorithm() {
        boolean z2 = !getBypass() && contain2ndStageSoundModel();
        q.r("needEnable2ndAlgorithm=" + z2);
        q.s("needEnable2ndAlgorithm=" + z2);
        return z2;
    }

    public void setBigSoundModelLocale(String str) {
        this.mLocale = str;
    }

    public void setKeyword(String str) {
        this.mKeyword = str;
    }

    public void setMagicNumber(String str) {
        this.mMagicNumber = str;
    }

    public void setSoundModelConfiguration(Bundle bundle) {
        this.mSoundModelConfiguration = bundle;
        this.isConfigurationUpdated = true;
    }

    public void setSoundModelKey(j jVar) {
        this.mSoundModelKey = jVar;
        this.mSoundModelFileInfo = new g(jVar.d(), this.mSoundModelKey.b());
    }

    public void setUserId(String str) {
        this.mUserId = str;
    }

    public void setVersion(SVAVersion sVAVersion) {
        this.mVersion = sVAVersion;
    }

    public String toString() {
        Bundle soundModelConfiguration = getSoundModelConfiguration();
        StringBuilder sb = new StringBuilder("BigSoundModel :\n");
        if (getSoundModelKey() != null) {
            sb.append("Package:");
            sb.append(getSoundModelKey().b());
            sb.append("\n");
            sb.append("SoundModelFileName:");
            sb.append(getSoundModelFileName());
            sb.append("\n");
            sb.append("Locale:");
            sb.append(getLocale());
            sb.append("\n");
        }
        sb.append("MagicNumber:");
        sb.append(getMagicNumber());
        sb.append("\n");
        sb.append("Version:");
        sb.append(getVersion().d());
        sb.append("\n");
        sb.append("Keyword:");
        sb.append(getKeyword());
        sb.append("\n");
        sb.append("UserId:");
        sb.append(getUserId());
        sb.append("\n");
        sb.append("Type:");
        sb.append(getType().c());
        sb.append("\n");
        sb.append("Count:");
        sb.append(getSoundModelCount());
        sb.append("\n");
        if (soundModelConfiguration != null) {
            for (p011l.g gVar : d.f1252a) {
                String strB = gVar.b();
                if (soundModelConfiguration.containsKey(strB)) {
                    if (gVar.a().d()) {
                        sb.append(strB);
                        sb.append(":");
                        sb.append(soundModelConfiguration.getBoolean(strB));
                        sb.append("\n");
                    } else if (gVar.a().f()) {
                        sb.append(strB);
                        sb.append(":");
                        sb.append(soundModelConfiguration.getInt(strB));
                        sb.append("\n");
                    } else if (gVar.a().e()) {
                        sb.append(strB);
                        sb.append(":");
                        sb.append(soundModelConfiguration.getFloat(strB));
                        sb.append("\n");
                    } else {
                        sb.append(strB);
                        sb.append(":");
                        sb.append(soundModelConfiguration.getString(strB));
                        sb.append("\n");
                    }
                }
            }
        }
        return sb.toString();
    }

    public void setVersion(int i2) {
        this.mVersion = SVAVersion.b(i2);
    }
}
