package com.miui.extraphoto.refocus;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import androidx.fragment.app.Fragment;
import com.miui.extraphoto.common.widget.AlertDialogFragment;
import com.miui.extraphoto.common.widget.recyclerview.CenterSmoothScrollerController;
import com.miui.extraphoto.common.widget.recyclerview.ScrollLinearLayoutManager;
import com.miui.extraphoto.common.widget.recyclerview.SimpleRecyclerViewMiuix;
import com.miui.extraphoto.refocus.DualPhotoGestureFragment;
import com.miui.extraphoto.refocus.manager.DualPhotoNativeContext;

/* JADX INFO: loaded from: classes.dex */
public abstract class DualPhotoOperatingFragment<T extends DualPhotoGestureFragment> extends Fragment implements MetaDataProviderAttachable {
    protected CoordinateProvider mCoordinateProvider;
    protected IDisplay mDisplay;
    protected DualPhotoNativeContext mDualPhotoNativeContext;
    protected T mGestureFragment;
    private Handler mMainHandler;
    protected PhotoInfoProvider mPhotoInfoProvider;
    protected ViewInterface mViewInterface;

    protected abstract boolean isEmpty();

    public void notifyInitLoadProcess() {
    }

    public void notifyInitLoadUi() {
    }

    public void onExit() {
    }

    public void onSave() {
    }

    protected abstract void onSaveFromConfirmDialog();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mMainHandler = new Handler();
        if (context instanceof ViewInterface) {
            this.mViewInterface = (ViewInterface) context;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.mViewInterface = null;
    }

    @Override // com.miui.extraphoto.refocus.MetaDataProviderAttachable
    public void attachPhotoInfoProvider(PhotoInfoProvider photoInfoProvider) {
        this.mPhotoInfoProvider = photoInfoProvider;
    }

    @Override // com.miui.extraphoto.refocus.MetaDataProviderAttachable
    public void attachCoordinateProvider(CoordinateProvider coordinateProvider) {
        this.mCoordinateProvider = coordinateProvider;
    }

    @Override // com.miui.extraphoto.refocus.MetaDataProviderAttachable
    public void attachDualPhotoNativeContext(DualPhotoNativeContext dualPhotoNativeContext) {
        this.mDualPhotoNativeContext = dualPhotoNativeContext;
    }

    @Override // com.miui.extraphoto.refocus.MetaDataProviderAttachable
    public void attachDisplay(IDisplay iDisplay) {
        this.mDisplay = iDisplay;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void attachGestureFragment(DualPhotoGestureFragment dualPhotoGestureFragment) {
        this.mGestureFragment = dualPhotoGestureFragment;
    }

    protected void setSmothScrollToCenterLinearLayoutManager(SimpleRecyclerViewMiuix simpleRecyclerViewMiuix) {
        ScrollLinearLayoutManager scrollLinearLayoutManager = new ScrollLinearLayoutManager(getActivity(), 0, false);
        scrollLinearLayoutManager.setSmoothScroller(new CenterSmoothScrollerController(getActivity()));
        simpleRecyclerViewMiuix.setLayoutManager(scrollLinearLayoutManager);
    }

    protected void smoothScrollToCenter(SimpleRecyclerViewMiuix simpleRecyclerViewMiuix, int i) {
        simpleRecyclerViewMiuix.setSpringEnabled(false);
        simpleRecyclerViewMiuix.smoothScrollToPosition(i);
    }

    protected void runOnRenderThread(Runnable runnable) {
        this.mDisplay.getRenderHandler().post(runnable);
    }

    protected void removeOnRenderThreadRunnable(Runnable runnable) {
        this.mDisplay.getRenderHandler().removeCallbacks(runnable);
    }

    protected void removeRunnable(Runnable runnable) {
        this.mMainHandler.removeCallbacks(runnable);
    }

    protected void runOnMainThread(Runnable runnable) {
        this.mMainHandler.post(runnable);
    }

    protected void runOnMainThreadDelayed(Runnable runnable, long j) {
        this.mMainHandler.postDelayed(runnable, j);
    }

    protected void refreshDisplay() {
        this.mDualPhotoNativeContext.configDisplayData(this.mDisplay);
        this.mDisplay.refreshDataAndRender();
    }

    public boolean onBackPressed() {
        if (isEmpty()) {
            return false;
        }
        showConfirmDialog();
        return true;
    }

    private void showConfirmDialog() {
        AlertDialogFragment alertDialogFragmentBuild = new AlertDialogFragment.Builder().setMessage(R$string.refocus_discard_confirm_message).setPositiveButton(R$string.refocus_discard_positive_btn).setNegativeButton(R$string.refocus_discard_negative_btn).setCancellable(true).build();
        alertDialogFragmentBuild.setCallbacks(new AlertDialogFragment.Callbacks() { // from class: com.miui.extraphoto.refocus.DualPhotoOperatingFragment.1
            @Override // com.miui.extraphoto.common.widget.AlertDialogFragment.Callbacks
            public void onCancel(AlertDialogFragment alertDialogFragment) {
            }

            @Override // com.miui.extraphoto.common.widget.AlertDialogFragment.Callbacks
            public void onDismiss(AlertDialogFragment alertDialogFragment) {
            }

            @Override // com.miui.extraphoto.common.widget.AlertDialogFragment.Callbacks
            public void onClick(AlertDialogFragment alertDialogFragment, int i) {
                if (i == -1) {
                    DualPhotoOperatingFragment.this.onSaveFromConfirmDialog();
                } else {
                    DualPhotoOperatingFragment.this.mViewInterface.onExit();
                }
            }
        });
        alertDialogFragmentBuild.showAllowingStateLoss(getFragmentManager(), "dialog_confirm");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static class SaveFinishRunnable implements Runnable {
        private final boolean mIsVideo;
        private final String mPath;
        private final Bitmap mPreviewBitmap;
        private final String mStatisticSaveType;
        private final String mStatisticSaveValue;
        private final ViewInterface mViewInterface;

        public SaveFinishRunnable(ViewInterface viewInterface, Bitmap bitmap, String str, boolean z, String str2, String str3) {
            this.mPreviewBitmap = bitmap;
            this.mPath = str;
            this.mStatisticSaveType = str2;
            this.mStatisticSaveValue = str3;
            this.mViewInterface = viewInterface;
            this.mIsVideo = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewInterface viewInterface = this.mViewInterface;
            if (viewInterface != null) {
                viewInterface.onSaveEnd(this.mPreviewBitmap, this.mPath, this.mIsVideo, this.mStatisticSaveType, this.mStatisticSaveValue);
            }
        }
    }
}
