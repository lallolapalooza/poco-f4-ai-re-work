package com.yxcorp.gifshow.camerasdk;

import com.yxcorp.gifshow.api.ICameraRecordPlugin;
import com.yxcorp.gifshow.util.i0;

/* JADX INFO: loaded from: classes16.dex */
public class CameraRecordPluginImpl implements ICameraRecordPlugin {
    @Override // com.yxcorp.gifshow.api.ICameraRecordPlugin
    public com.yxcorp.gifshow.entity.j getEncodeConfig() {
        return i0.a();
    }

    @Override // com.yxcorp.utility.plugin.Plugin
    public boolean isAvailable() {
        return true;
    }
}
