package com.miui.extraphoto.refocus.core.relighting;

import androidx.fragment.app.Fragment;
import com.miui.extraphoto.refocus.DualPhotoEffectPresenter;
import com.miui.extraphoto.refocus.PhotoInfoProvider;
import com.miui.extraphoto.refocus.R$string;
import com.miui.extraphoto.refocus.manager.AlgoManager;

/* JADX INFO: loaded from: classes.dex */
public class RelightingPresenter extends DualPhotoEffectPresenter {
    @Override // com.miui.extraphoto.refocus.DualPhotoEffectPresenter
    public String getGestureFragmentTag() {
        return "relighting:gesture";
    }

    @Override // com.miui.extraphoto.refocus.DualPhotoEffectPresenter
    public String getOperatingFragmentTag() {
        return "relighting:menu";
    }

    private RelightingPresenter() {
    }

    @Override // com.miui.extraphoto.refocus.DualPhotoEffectPresenter
    public int getTitleStringRes() {
        return R$string.refocus_type_relighting;
    }

    @Override // com.miui.extraphoto.refocus.DualPhotoEffectPresenter
    public Fragment generateOperatingFragment() {
        return new RelightingOperatingFragment();
    }

    @Override // com.miui.extraphoto.refocus.DualPhotoEffectPresenter
    public Fragment generateGestureFragment() {
        return new RelightingGestureFragment();
    }

    @Override // com.miui.extraphoto.refocus.DualPhotoEffectPresenter
    public boolean support(PhotoInfoProvider photoInfoProvider) {
        return AlgoManager.supportRelighting(photoInfoProvider.getPortraitType(), photoInfoProvider.getRelightType());
    }

    @Override // com.miui.extraphoto.refocus.DualPhotoEffectPresenter
    public boolean isMatchEffect(PhotoInfoProvider photoInfoProvider) {
        return photoInfoProvider.getRelightingIndex() > 0;
    }

    public static DualPhotoEffectPresenter generatePresenter() {
        return new RelightingPresenter();
    }
}
