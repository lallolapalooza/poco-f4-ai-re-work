package com.looksery.sdk;

import com.snap.nloader.android.NativeComponentsLayout;
import defpackage.AbstractC19915fE3;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class CamplatPlusAwareComponentLayout implements NativeComponentsLayout {
    @Override // com.snap.nloader.android.NativeComponentsLayout
    public NativeComponentsLayout.ComponentHostInfo getComponentHostInfo(String str) {
        Objects.requireNonNull(str);
        switch (str) {
            case "transcoding":
                return new NativeComponentsLayout.ComponentHostInfo(com.looksery.sdk.nlo.BuildConfig.LIB_CAMPLAT_NAME, "initialize_native_component_transcoding");
            case "labscv":
                return new NativeComponentsLayout.ComponentHostInfo(com.looksery.sdk.nlo.BuildConfig.LIB_CAMPLAT_NAME, "initialize_native_component_labscv");
            case "opencv":
                return new NativeComponentsLayout.ComponentHostInfo(com.looksery.sdk.nlo.BuildConfig.LIB_CAMPLAT_NAME, "");
            case "snapcv":
                return new NativeComponentsLayout.ComponentHostInfo(com.looksery.sdk.nlo.BuildConfig.LIB_CAMPLAT_NAME, "initialize_native_component_snapcv");
            case "previewcv":
                return new NativeComponentsLayout.ComponentHostInfo(com.looksery.sdk.nlo.BuildConfig.LIB_CAMPLAT_NAME, "");
            case "catalyst":
                return new NativeComponentsLayout.ComponentHostInfo(com.looksery.sdk.nlo.BuildConfig.LIB_CAMPLAT_NAME, "initialize_native_component_catalyst");
            case "snapscan":
                return new NativeComponentsLayout.ComponentHostInfo(com.looksery.sdk.nlo.BuildConfig.LIB_CAMPLAT_NAME, "initialize_native_component_snapscan");
            case "lenscore":
                return new NativeComponentsLayout.ComponentHostInfo(com.looksery.sdk.nlo.BuildConfig.LIB_CAMPLAT_NAME, "initialize_native_component_lenscore");
            default:
                throw new IllegalArgumentException(AbstractC19915fE3.c("Unknown component name: ", str));
        }
    }

    @Override // com.snap.nloader.android.NativeComponentsLayout
    public List<String> getRuntimeDependenciesOrdered(String str) {
        Objects.requireNonNull(str);
        if (str.equals(com.looksery.sdk.nlo.BuildConfig.LIB_CAMPLAT_NAME)) {
            return Collections.emptyList();
        }
        throw new IllegalArgumentException(AbstractC19915fE3.c("Unknown library name: ", str));
    }
}
