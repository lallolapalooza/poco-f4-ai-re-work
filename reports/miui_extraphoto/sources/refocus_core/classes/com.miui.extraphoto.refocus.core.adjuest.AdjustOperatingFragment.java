package com.miui.extraphoto.refocus.core.adjuest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.miui.extraphoto.common.storage.Storage;
import com.miui.extraphoto.common.utils.ImageUtils;
import com.miui.extraphoto.common.utils.LinearMotorHelper;
import com.miui.extraphoto.common.utils.MathUtils;
import com.miui.extraphoto.common.utils.MediaStoreUtils;
import com.miui.extraphoto.common.utils.StaticContext;
import com.miui.extraphoto.refocus.DualPhotoOperatingFragment;
import com.miui.extraphoto.refocus.R$id;
import com.miui.extraphoto.refocus.R$layout;
import com.miui.extraphoto.refocus.R$string;
import com.miui.extraphoto.refocus.RefocusActivity;
import com.miui.extraphoto.refocus.core.adjuest.widget.ExtraSlideFNumberAdapter;
import com.miui.extraphoto.refocus.core.adjuest.widget.HorizontalFNumberSlideView;
import com.miui.extraphoto.refocus.core.adjuest.widget.SelectChangeListener;
import com.miui.extraphoto.refocus.manager.DualPhotoFileUtils;
import com.miui.extraphoto.refocus.manager.DualPhotoJni;
import com.miui.extraphoto.refocus.manager.DualPhotoMiMovieUtils;
import com.miui.extraphoto.refocus.manager.DualPhotoNativeContext;
import com.miui.extraphoto.refocus.manager.MeituDataManager;
import com.miui.extraphoto.refocus.model.NativeImage;
import com.miui.mediaeditor.bizlibs.storage.utils.EditorFileUtils;
import com.miui.mediaeditor.storage.entrance.XResult;
import com.miui.mediaeditor.storage.entrance.XStorage;
import com.miui.mediaeditor.storage.utils.BaseMediaUtils;
import com.miui.mediaeditor.storage.utils.BaseStorageUtils;

/* JADX INFO: loaded from: classes.dex */
public class AdjustOperatingFragment extends DualPhotoOperatingFragment {
    private AdjustProcessor mAdjustProcessor;
    private ExtraSlideFNumberAdapter mFNumberAdapter;
    private TextView mFNumberTextView;
    private HorizontalFNumberSlideView mHorizontalFNumberSlideView;
    private boolean mIsUserTouch;
    private SaveRunnable mSaveRunnable;
    private RefocusData mRefocusData = new RefocusData();
    private boolean mIsEmpty = true;
    private boolean mFirstProcess = true;
    private SelectChangeListener mSelectChangeListener = new SelectChangeListener() { // from class: com.miui.extraphoto.refocus.core.adjuest.AdjustOperatingFragment.2
        @Override // com.miui.extraphoto.refocus.core.adjuest.widget.SelectChangeListener
        public void onActionDown() {
        }

        @Override // com.miui.extraphoto.refocus.core.adjuest.widget.SelectChangeListener
        public void onActonUp() {
        }

        @Override // com.miui.extraphoto.refocus.core.adjuest.widget.SelectChangeListener
        public void onSelectChanged(int i, int i2) {
            LinearMotorHelper.performHapticFeedback(AdjustOperatingFragment.this.mHorizontalFNumberSlideView, LinearMotorHelper.HAPTIC_MESH_LIGHT, LinearMotorHelper.HAPTIC_GEAR_LIGHT);
            AdjustOperatingFragment.this.mRefocusData.currentIndex = i2;
            AdjustOperatingFragment.this.mFNumberTextView.setText(AdjustOperatingFragment.this.mAdjustProcessor.getDisplayText(AdjustOperatingFragment.this.mRefocusData.currentIndex));
            AdjustOperatingFragment.this.mHorizontalFNumberSlideView.setContentDescription(String.format(AdjustOperatingFragment.this.getResources().getString(R$string.acc_refocus_slider), AdjustOperatingFragment.this.mAdjustProcessor.getDisplayText(AdjustOperatingFragment.this.mRefocusData.currentIndex)));
            AdjustOperatingFragment.this.processRefocus();
            AdjustOperatingFragment.this.mIsEmpty = false;
        }
    };
    private View.OnClickListener mExitListener = new View.OnClickListener() { // from class: com.miui.extraphoto.refocus.core.adjuest.AdjustOperatingFragment.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((DualPhotoOperatingFragment) AdjustOperatingFragment.this).mViewInterface.onExit();
        }
    };
    private View.OnClickListener mSaveListener = new View.OnClickListener() { // from class: com.miui.extraphoto.refocus.core.adjuest.AdjustOperatingFragment.4
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AdjustOperatingFragment.this.onSave();
        }
    };
    private Runnable mAfterProcessRunnable = new Runnable() { // from class: com.miui.extraphoto.refocus.core.adjuest.AdjustOperatingFragment.5
        @Override // java.lang.Runnable
        public void run() {
            if (AdjustOperatingFragment.this.getActivity() instanceof RefocusActivity) {
                ((RefocusActivity) AdjustOperatingFragment.this.getActivity()).setSaveButtonEnable(true);
            }
            if (AdjustOperatingFragment.this.mFirstProcess) {
                AdjustOperatingFragment.this.mFirstProcess = false;
            }
        }
    };

    @Override // com.miui.extraphoto.refocus.DualPhotoOperatingFragment
    public void notifyInitLoadUi() {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R$layout.refocus_fragment_adjust, viewGroup, false);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:23:0x0063  */
    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        byte b;
        this.mHorizontalFNumberSlideView = (HorizontalFNumberSlideView) view.findViewById(R$id.seek_bar);
        this.mFNumberTextView = (TextView) view.findViewById(R$id.f_number);
        switch (this.mPhotoInfoProvider.getPortraitType()) {
            case "mialgo_portrait":
                b = 0;
                break;
            case "megvii_portrait_google":
                b = 3;
                break;
            case "mi_portrait":
                b = 2;
                break;
            case "soft-portrait-enc":
                b = 1;
                break;
            case "portrait":
                b = 5;
                break;
            case "megvii_portrait":
                b = 4;
                break;
            default:
                b = -1;
                break;
        }
        if (b == 0) {
            this.mAdjustProcessor = new MiAlgoPortraitJniProcessor(this.mDualPhotoNativeContext, this.mPhotoInfoProvider);
        } else if (b == 1) {
            this.mAdjustProcessor = new AdjustProcessorSoftEncrypted(this.mDualPhotoNativeContext, this.mPhotoInfoProvider);
        } else if (b == 2) {
            this.mAdjustProcessor = new MiCameraAdjustProcessor(this.mDualPhotoNativeContext, this.mPhotoInfoProvider);
        } else if (b == 3 || b == 4) {
            this.mAdjustProcessor = new MegviiAdjustProcessor(this.mDualPhotoNativeContext, this.mPhotoInfoProvider);
        } else {
            this.mAdjustProcessor = new AdjustProcessorPortrait(this.mDualPhotoNativeContext, this.mPhotoInfoProvider);
        }
        this.mAdjustProcessor.extractInitData(this.mRefocusData);
        this.mFNumberAdapter = new ExtraSlideFNumberAdapter(getActivity());
        this.mFNumberTextView.setText(this.mAdjustProcessor.getDisplayText(this.mRefocusData.currentIndex));
        this.mHorizontalFNumberSlideView.setContentDescription(String.format(getResources().getString(R$string.acc_refocus_slider), this.mAdjustProcessor.getDisplayText(this.mRefocusData.currentIndex)));
        this.mHorizontalFNumberSlideView.post(new Runnable() { // from class: com.miui.extraphoto.refocus.core.adjuest.AdjustOperatingFragment.1
            @Override // java.lang.Runnable
            public void run() {
                AdjustOperatingFragment.this.mFNumberAdapter.setCount(AdjustOperatingFragment.this.mRefocusData.count);
                AdjustOperatingFragment.this.mFNumberAdapter.setDefaultIndex(AdjustOperatingFragment.this.mRefocusData.currentIndex);
                AdjustOperatingFragment.this.mFNumberAdapter.setCurrentIndex(AdjustOperatingFragment.this.mRefocusData.currentIndex);
                AdjustOperatingFragment.this.mFNumberAdapter.setEnable(true);
                AdjustOperatingFragment.this.mHorizontalFNumberSlideView.setDrawAdapter(AdjustOperatingFragment.this.mFNumberAdapter);
                AdjustOperatingFragment.this.mHorizontalFNumberSlideView.setJustifyEnabled(true);
                AdjustOperatingFragment.this.mHorizontalFNumberSlideView.setOnPositionSelectListener(AdjustOperatingFragment.this.mFNumberAdapter);
                AdjustOperatingFragment.this.mHorizontalFNumberSlideView.setSelection(AdjustOperatingFragment.this.mRefocusData.currentIndex);
                AdjustOperatingFragment.this.mFNumberAdapter.setSelectChangeListener(AdjustOperatingFragment.this.mSelectChangeListener);
            }
        });
        DualPhotoNativeContext dualPhotoNativeContext = this.mDualPhotoNativeContext;
        if (dualPhotoNativeContext != null) {
            this.mRefocusData.focusX = dualPhotoNativeContext.getCurrentFocusPointX();
            this.mRefocusData.focusY = this.mDualPhotoNativeContext.getCurrentFocusPointY();
        }
        if (this.mViewInterface.isInitFinish()) {
            processRefocus();
        }
        if (getActivity() instanceof RefocusActivity) {
            ((RefocusActivity) getActivity()).setSaveButtonEnable(false);
        }
    }

    public void setFocusPoint(float f, float f2) {
        this.mIsUserTouch = true;
        int processWidth = this.mPhotoInfoProvider.getProcessWidth();
        int processHeight = this.mPhotoInfoProvider.getProcessHeight();
        int iRound = Math.round(f);
        int iRound2 = Math.round(f2);
        if (iRound < 0) {
            iRound = 0;
        }
        if (iRound <= processWidth) {
            processWidth = iRound;
        }
        if (iRound2 < 0) {
            iRound2 = 0;
        }
        if (iRound2 <= processHeight) {
            processHeight = iRound2;
        }
        RefocusData refocusData = this.mRefocusData;
        refocusData.focusX = processWidth;
        refocusData.focusY = processHeight;
        processRefocus();
        this.mIsEmpty = false;
    }

    @Override // com.miui.extraphoto.refocus.DualPhotoOperatingFragment
    protected boolean isEmpty() {
        return this.mIsEmpty;
    }

    @Override // com.miui.extraphoto.refocus.DualPhotoOperatingFragment
    protected void onSaveFromConfirmDialog() {
        onSave();
    }

    @Override // com.miui.extraphoto.refocus.DualPhotoOperatingFragment
    public void notifyInitLoadProcess() {
        this.mRefocusData.focusX = this.mDualPhotoNativeContext.getCurrentFocusPointX();
        this.mRefocusData.focusY = this.mDualPhotoNativeContext.getCurrentFocusPointY();
        processRefocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processRefocus() {
        this.mDisplay.getRenderHandler().removeCallbacksAndMessages("refocus");
        this.mRefocusData.userTouch = this.mIsUserTouch;
        this.mDisplay.getRenderHandler().postAtTime(new RefocusRunnable(new RefocusData(this.mRefocusData)), "refocus", 0L);
    }

    @Override // com.miui.extraphoto.refocus.DualPhotoOperatingFragment
    public void onSave() {
        this.mViewInterface.onShowSaveLoading();
        SaveRunnable saveRunnable = new SaveRunnable(new RefocusData(this.mRefocusData));
        this.mSaveRunnable = saveRunnable;
        runOnRenderThread(saveRunnable);
    }

    @Override // com.miui.extraphoto.refocus.DualPhotoOperatingFragment
    public void onExit() {
        this.mViewInterface.onExit();
    }

    private class SaveRunnable implements Runnable {
        final RefocusData mRefocusData;

        private SaveRunnable(RefocusData refocusData) {
            this.mRefocusData = refocusData;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            boolean z = false;
            String strSafeGenerateImagePath = EditorFileUtils.safeGenerateImagePath(BaseStorageUtils.getRelativeFolder(((DualPhotoOperatingFragment) AdjustOperatingFragment.this).mPhotoInfoProvider.getSourcePath()), "jpg", false);
            XResult xResultInsert = XStorage.insert(strSafeGenerateImagePath, "AdjustOperatingFragment");
            Log.d("AdjustOperatingFragment", "run: xResult=" + xResultInsert.toString());
            if (xResultInsert.isSuccess()) {
                Storage storage = new Storage(strSafeGenerateImagePath, xResultInsert.getUri());
                ((DualPhotoOperatingFragment) AdjustOperatingFragment.this).mDualPhotoNativeContext.setCanRefreshDisplay(false);
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(((DualPhotoOperatingFragment) AdjustOperatingFragment.this).mPhotoInfoProvider.getProcessWidth(), ((DualPhotoOperatingFragment) AdjustOperatingFragment.this).mPhotoInfoProvider.getProcessHeight(), Bitmap.Config.ARGB_8888, true, ((DualPhotoOperatingFragment) AdjustOperatingFragment.this).mPhotoInfoProvider.getColorSpace() == null ? ColorSpace.get(ColorSpace.Named.SRGB) : ((DualPhotoOperatingFragment) AdjustOperatingFragment.this).mPhotoInfoProvider.getColorSpace());
                NativeImage effectImage = ((DualPhotoOperatingFragment) AdjustOperatingFragment.this).mDualPhotoNativeContext.getEffectImage();
                if (effectImage == null) {
                    return;
                }
                DualPhotoJni.configBitmapByNativeImage(bitmapCreateBitmap, effectImage.pointer);
                Bitmap bitmapGenerateBitmapWithOrientation = ImageUtils.generateBitmapWithOrientation(bitmapCreateBitmap, ((DualPhotoOperatingFragment) AdjustOperatingFragment.this).mPhotoInfoProvider.getDepthDataOrientation());
                if (((DualPhotoOperatingFragment) AdjustOperatingFragment.this).mPhotoInfoProvider.isMirror()) {
                    bitmapGenerateBitmapWithOrientation = ImageUtils.generateMirrorBitmap(bitmapGenerateBitmapWithOrientation);
                }
                Bitmap bitmap = bitmapGenerateBitmapWithOrientation;
                MeituDataManager meituDataManager = ((DualPhotoOperatingFragment) AdjustOperatingFragment.this).mPhotoInfoProvider.getMeituDataManager();
                if (meituDataManager.shouldProcessImage()) {
                    meituDataManager.processImage(bitmap, true);
                }
                if (((DualPhotoOperatingFragment) AdjustOperatingFragment.this).mPhotoInfoProvider.isMiMovie()) {
                    DualPhotoMiMovieUtils.fillBlackBorderForBitmap(bitmap);
                }
                AdjustOperatingFragment.this.mAdjustProcessor.prepareForExport(this.mRefocusData);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inMutable = true;
                Bitmap bitmapDecodeOriginBitmap = ((DualPhotoOperatingFragment) AdjustOperatingFragment.this).mPhotoInfoProvider.decodeOriginBitmap(options);
                ((DualPhotoOperatingFragment) AdjustOperatingFragment.this).mDualPhotoNativeContext.generateNativeImage(bitmapDecodeOriginBitmap, ((DualPhotoOperatingFragment) AdjustOperatingFragment.this).mPhotoInfoProvider);
                AdjustOperatingFragment.this.mAdjustProcessor.processRefocusOnSave(((DualPhotoOperatingFragment) AdjustOperatingFragment.this).mDualPhotoNativeContext.getEffectImage(), ((DualPhotoOperatingFragment) AdjustOperatingFragment.this).mDualPhotoNativeContext.getOriginImage(), this.mRefocusData);
                DualPhotoJni.configBitmapByNativeImage(bitmapDecodeOriginBitmap, ((DualPhotoOperatingFragment) AdjustOperatingFragment.this).mDualPhotoNativeContext.getEffectImage().pointer);
                Bitmap bitmapGenerateBitmapWithDegree = ImageUtils.generateBitmapWithDegree(bitmapDecodeOriginBitmap, MathUtils.positiveModule(((DualPhotoOperatingFragment) AdjustOperatingFragment.this).mPhotoInfoProvider.getDepthDataDegree() - ((DualPhotoOperatingFragment) AdjustOperatingFragment.this).mPhotoInfoProvider.getDegree(), 360));
                if (meituDataManager.shouldProcessImage()) {
                    bitmapGenerateBitmapWithDegree = ImageUtils.generateBitmapWithOrientation(bitmapGenerateBitmapWithDegree, ((DualPhotoOperatingFragment) AdjustOperatingFragment.this).mPhotoInfoProvider.getOrientation());
                    meituDataManager.processImage(bitmapGenerateBitmapWithDegree, false);
                    z = true;
                }
                if (((DualPhotoOperatingFragment) AdjustOperatingFragment.this).mPhotoInfoProvider.isMiMovie()) {
                    DualPhotoMiMovieUtils.fillBlackBorderForBitmap(bitmapGenerateBitmapWithDegree);
                }
                if (AdjustOperatingFragment.this.isDetached() || AdjustOperatingFragment.this.getContext() == null) {
                    return;
                }
                DualPhotoFileUtils.writeBitmap(storage, bitmapGenerateBitmapWithDegree, ((DualPhotoOperatingFragment) AdjustOperatingFragment.this).mPhotoInfoProvider, AdjustOperatingFragment.this.getResources(), z);
                BaseMediaUtils.triggerScanFile(strSafeGenerateImagePath);
                MediaStoreUtils.upsert(StaticContext.sGetAndroidContext(), strSafeGenerateImagePath);
                AdjustOperatingFragment adjustOperatingFragment = AdjustOperatingFragment.this;
                adjustOperatingFragment.runOnMainThread(new DualPhotoOperatingFragment.SaveFinishRunnable(((DualPhotoOperatingFragment) adjustOperatingFragment).mViewInterface, bitmap, strSafeGenerateImagePath, false, "image", AdjustOperatingFragment.this.mAdjustProcessor.generateStaticString(this.mRefocusData)));
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        runOnRenderThread(new DestroyRunnable(this.mAdjustProcessor));
        SaveRunnable saveRunnable = this.mSaveRunnable;
        if (saveRunnable != null) {
            removeOnRenderThreadRunnable(saveRunnable);
        }
    }

    private class RefocusRunnable implements Runnable {
        final RefocusData mRefocusData;

        RefocusRunnable(RefocusData refocusData) {
            this.mRefocusData = refocusData;
        }

        @Override // java.lang.Runnable
        public void run() {
            AdjustOperatingFragment.this.mAdjustProcessor.processRefocus(((DualPhotoOperatingFragment) AdjustOperatingFragment.this).mDualPhotoNativeContext.getEffectImage(), ((DualPhotoOperatingFragment) AdjustOperatingFragment.this).mDualPhotoNativeContext.getOriginImage(), this.mRefocusData);
            AdjustOperatingFragment.this.refreshDisplay();
            AdjustOperatingFragment adjustOperatingFragment = AdjustOperatingFragment.this;
            adjustOperatingFragment.runOnMainThread(adjustOperatingFragment.mAfterProcessRunnable);
        }
    }

    private static class DestroyRunnable implements Runnable {
        private final AdjustProcessor mAdjustProcessor;

        private DestroyRunnable(AdjustProcessor adjustProcessor) {
            this.mAdjustProcessor = adjustProcessor;
        }

        @Override // java.lang.Runnable
        public void run() {
            AdjustProcessor adjustProcessor = this.mAdjustProcessor;
            if (adjustProcessor != null) {
                adjustProcessor.release();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        if (z || !this.mAdjustProcessor.isHasData()) {
            return;
        }
        processRefocus();
    }

    public static class RefocusData {
        int count;
        int currentIndex;
        int focusX;
        int focusY;
        float scale;
        boolean userTouch;

        RefocusData() {
        }

        RefocusData(RefocusData refocusData) {
            this.focusX = refocusData.focusX;
            this.focusY = refocusData.focusY;
            this.count = refocusData.count;
            this.currentIndex = refocusData.currentIndex;
            this.scale = refocusData.scale;
            this.userTouch = refocusData.userTouch;
        }
    }
}
