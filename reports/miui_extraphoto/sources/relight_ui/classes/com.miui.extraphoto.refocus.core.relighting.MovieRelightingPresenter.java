package com.miui.extraphoto.refocus.core.relighting;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.miui.extraphoto.refocus.DualPhotoEffectPresenter;
import com.miui.extraphoto.refocus.PhotoInfoProvider;
import com.miui.extraphoto.refocus.R$string;
import com.miui.extraphoto.refocus.manager.AlgoManager;

/* JADX INFO: loaded from: classes.dex */
public class MovieRelightingPresenter extends DualPhotoEffectPresenter {
    @Override // com.miui.extraphoto.refocus.DualPhotoEffectPresenter
    public String getGestureFragmentTag() {
        return "movie_relighting:gesture";
    }

    @Override // com.miui.extraphoto.refocus.DualPhotoEffectPresenter
    public String getOperatingFragmentTag() {
        return "movie_relighting:menu";
    }

    private MovieRelightingPresenter() {
    }

    @Override // com.miui.extraphoto.refocus.DualPhotoEffectPresenter
    public int getTitleStringRes() {
        return R$string.refocus_type_movie_relighting;
    }

    @Override // com.miui.extraphoto.refocus.DualPhotoEffectPresenter
    public Fragment generateOperatingFragment() {
        RelightingOperatingFragment relightingOperatingFragment = new RelightingOperatingFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("isMovieRelighting", true);
        relightingOperatingFragment.setArguments(bundle);
        return relightingOperatingFragment;
    }

    @Override // com.miui.extraphoto.refocus.DualPhotoEffectPresenter
    public Fragment generateGestureFragment() {
        return new RelightingGestureFragment();
    }

    @Override // com.miui.extraphoto.refocus.DualPhotoEffectPresenter
    public boolean support(PhotoInfoProvider photoInfoProvider) {
        return AlgoManager.supportMovieRelighting(photoInfoProvider.getPortraitType(), photoInfoProvider.getRelightType());
    }

    @Override // com.miui.extraphoto.refocus.DualPhotoEffectPresenter
    public boolean isMatchEffect(PhotoInfoProvider photoInfoProvider) {
        return photoInfoProvider.getRelightingIndex() > 0;
    }

    public static DualPhotoEffectPresenter generatePresenter() {
        return new MovieRelightingPresenter();
    }
}
