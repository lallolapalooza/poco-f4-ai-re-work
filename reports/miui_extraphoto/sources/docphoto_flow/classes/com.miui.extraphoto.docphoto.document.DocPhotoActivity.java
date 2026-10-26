package com.miui.extraphoto.docphoto.document;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Transition;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.miui.extraphoto.common.BaseActivity;
import com.miui.extraphoto.common.EditorToast;
import com.miui.extraphoto.common.compat.ActivityCompat;
import com.miui.extraphoto.common.compat.SystemUiUtil;
import com.miui.extraphoto.common.compat.WindowCompat;
import com.miui.extraphoto.common.storage.Storage;
import com.miui.extraphoto.common.transition.SimpleTransitionListener;
import com.miui.extraphoto.common.transition.TransitionManager;
import com.miui.extraphoto.common.transition.TransitionPatching;
import com.miui.extraphoto.common.utils.ReceiverUtils;
import com.miui.extraphoto.common.utils.SettingUtils;
import com.miui.extraphoto.common.utils.SpecialTypeMediaUtil;
import com.miui.extraphoto.common.utils.ToastUtils;
import com.miui.extraphoto.common.widget.AlertDialogFragment;
import com.miui.extraphoto.common.widget.AnimateBitmapGestureView;
import com.miui.extraphoto.common.widget.LoadingDialog;
import com.miui.extraphoto.docphoto.DocPhotoTrackingUtil;
import com.miui.extraphoto.docphoto.ExportFragment;
import com.miui.extraphoto.docphoto.ExportTask;
import com.miui.extraphoto.docphoto.R$animator;
import com.miui.extraphoto.docphoto.R$id;
import com.miui.extraphoto.docphoto.R$layout;
import com.miui.extraphoto.docphoto.R$string;
import com.miui.extraphoto.docphoto.ScreenBroadcastReceiver;
import com.miui.extraphoto.docphoto.iactivity.DocPhotoActivityInterface;
import com.miui.extraphoto.docphoto.ifragment.DocPhotoAdjustFragInterface;
import com.miui.extraphoto.docphoto.manager.DocPhotoManager;
import com.miui.extraphoto.docphoto.manager.DocProcessor;
import com.miui.extraphoto.docphoto.manager.PhotoManager;
import com.miui.extraphoto.docphoto.widget.DocCorrectionView;
import com.xiaomi.ocr.sdk.imgprocess.DocumentProcessJNI;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class DocPhotoActivity extends BaseActivity implements DocPhotoActivityInterface, PhotoManager.ExtractCallback, ExportTask.ExportCallback {
    private DocCorrectionView mDocCorrectionView;
    private DocPhotoCropFragment mDocPhotoCropFragment;
    private DocPhotoHandwirteFragment mDocPhotoHandwriteFragment;
    private DocPhotoManager mDocPhotoManager;
    private DocPhotoMenuFragment mDocPhotoMenuFragment;
    private DocProcessor mDocProcessor;
    private EditorToast mEditorToast;
    private long mEnterTime;
    private boolean mExtractEnd;
    private boolean mExtractSuccess;
    private ImageView mGuideView;
    private boolean mIsDocPhoto;
    private boolean mIsProcessing;
    private View mMenuView;
    private View mPreviewContainerView;
    private AnimateBitmapGestureView mPreviewView;
    private LoadingDialog mProgressDialog;
    private View mProgressDialogAnchorView;
    private ExportFragment mSaveDialog;
    private Bundle mSavedInstanceState;
    private ScreenBroadcastReceiver mScreenReceiver;
    private StartEndLinker mStartEndLinker;
    private TextView mTitleView;
    private ImageView mTransitionView;
    private boolean mOriginPreviewSet = false;
    private boolean mFeatureTransition = false;
    private View.OnClickListener mGuideClickListener = new View.OnClickListener() { // from class: com.miui.extraphoto.docphoto.document.DocPhotoActivity.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (DocPhotoActivity.this.getSupportFragmentManager().findFragmentByTag("guide") != null) {
                return;
            }
            DocPhotoGuideFragment docPhotoGuideFragment = new DocPhotoGuideFragment();
            if (docPhotoGuideFragment.isAdded()) {
                return;
            }
            DocPhotoActivity.this.dismissToast();
            FragmentTransaction fragmentTransactionBeginTransaction = DocPhotoActivity.this.getSupportFragmentManager().beginTransaction();
            if (!SettingUtils.isRemoveGlobalAnimate(DocPhotoActivity.this.getApplicationContext())) {
                fragmentTransactionBeginTransaction.setCustomAnimations(R$animator.frag_enter, R$animator.frag_exit, R$animator.frag_pop_enter, R$animator.frag_pop_exit);
            }
            fragmentTransactionBeginTransaction.add(R$id.preview_container, docPhotoGuideFragment, "guide").addToBackStack("DocPhotoActivity").commit();
        }
    };

    interface StartEndLinker {
        void afterSetContentView(Bundle bundle);

        void configTransition(Bundle bundle, boolean z, boolean z2);

        boolean hasTransition();

        void onCheckPermissionSucceed();

        void onDestroy();

        void onExit();

        void onExport(File file);

        void onExtractBase();

        void onExtractFinish();

        void onPause();
    }

    @Override // com.miui.extraphoto.docphoto.ExportTask.ExportCallback
    public void onCancelExport() {
    }

    @Override // com.miui.extraphoto.common.BaseActivity, miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        WindowCompat.setCutoutModeShortEdges(getWindow());
        getWindow().addFlags(134217728);
        this.mSavedInstanceState = bundle;
        this.mFeatureTransition = getWindow().requestFeature(13);
        if (getIntent().getBooleanExtra("StartActivityWhenLocked", false)) {
            this.mScreenReceiver = new ScreenBroadcastReceiver(this);
            WindowCompat.setShowWhenLocked(this, true);
            ReceiverUtils.registerReceiver(this, this.mScreenReceiver, "android.intent.action.SCREEN_OFF");
        }
        super.onCreate(null);
        setContentView(R$layout.doc_photo_activity_doc_photo);
        initView();
        checkPermission();
    }

    private void initView() {
        this.mPreviewView = (AnimateBitmapGestureView) findViewById(R$id.preview_view);
        this.mTransitionView = (ImageView) findViewById(R$id.transition_view);
        this.mMenuView = findViewById(R$id.menu_container);
        this.mPreviewContainerView = findViewById(R$id.preview_container);
        this.mDocCorrectionView = (DocCorrectionView) findViewById(R$id.raw_anim_image);
        TextView textView = (TextView) findViewById(R$id.title);
        this.mTitleView = textView;
        textView.setText(R$string.doc_photo_doc_photo_title);
        this.mGuideView = (ImageView) findViewById(R$id.guide_icon);
        if (DocumentProcessJNI.isSupportMoer()) {
            this.mGuideView.setVisibility(0);
            findViewById(R$id.title_zone).setOnClickListener(this.mGuideClickListener);
        }
        this.mDocProcessor = new DocProcessor(getApplicationContext());
        getWindow().setBackgroundDrawable(new ColorDrawable(-16777216));
        this.mProgressDialog = new LoadingDialog(this);
        this.mSaveDialog = new ExportFragment();
        this.mEditorToast = new EditorToast(this);
        this.mPreviewView.setAnimateListener(new AnimateBitmapGestureView.AnimateListener() { // from class: com.miui.extraphoto.docphoto.document.DocPhotoActivity.1
            @Override // com.miui.extraphoto.common.widget.AnimateBitmapGestureView.AnimateListener
            public void onBegin() {
                DocPhotoActivity.this.detachCrop();
            }
        });
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onAttachedToWindow() {
        if (WindowCompat.isNotch(this)) {
            SystemUiUtil.extendToStatusBar(getWindow().getDecorView());
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        dismissSaveLoading();
        dismissProcessLoading();
        ScreenBroadcastReceiver screenBroadcastReceiver = this.mScreenReceiver;
        if (screenBroadcastReceiver != null) {
            ReceiverUtils.safeUnregisterReceiver(this, screenBroadcastReceiver);
            this.mScreenReceiver.release();
        }
        StartEndLinker startEndLinker = this.mStartEndLinker;
        if (startEndLinker != null) {
            startEndLinker.onDestroy();
        }
        DocPhotoManager docPhotoManager = this.mDocPhotoManager;
        if (docPhotoManager != null) {
            docPhotoManager.release();
        }
        DocProcessor docProcessor = this.mDocProcessor;
        if (docProcessor != null) {
            docProcessor.release();
        }
        EditorToast editorToast = this.mEditorToast;
        if (editorToast != null) {
            editorToast.dismiss();
        }
    }

    @Override // com.miui.extraphoto.common.BaseActivity
    protected void onScreenSizeChanged() {
        super.onScreenSizeChanged();
        this.mProgressDialogAnchorView.post(new Runnable() { // from class: com.miui.extraphoto.docphoto.document.DocPhotoActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onScreenSizeChanged$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onScreenSizeChanged$0() {
        this.mProgressDialog.updateLocation(this.mProgressDialogAnchorView);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        StartEndLinker startEndLinker = this.mStartEndLinker;
        if (startEndLinker != null) {
            startEndLinker.onPause();
        }
    }

    @Override // com.miui.extraphoto.common.BaseActivity, com.miui.extraphoto.common.permission.PermissionCheckHelper.PermissionCheckCallback
    public void onCheckPermissionSucceed(boolean z) {
        super.onCheckPermissionSucceed(z);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        Storage storageFromIntent = Storage.fromIntent(intent);
        if (storageFromIntent == null) {
            Log.e("DocPhotoActivity", "source is null");
            finish();
            return;
        }
        boolean zIsDocPhoto = SpecialTypeMediaUtil.isDocPhoto(storageFromIntent);
        this.mIsDocPhoto = zIsDocPhoto;
        StartEndLinker photoStartEndLinker = !zIsDocPhoto ? new PhotoStartEndLinker() : new DocPhotoStartEndLinker();
        this.mStartEndLinker = photoStartEndLinker;
        photoStartEndLinker.configTransition(this.mSavedInstanceState, this.mFeatureTransition, z);
        this.mStartEndLinker.afterSetContentView(null);
        this.mDocPhotoManager = new DocPhotoManager(storageFromIntent, this.mIsDocPhoto, false, this.mDocProcessor);
        this.mEnterTime = System.currentTimeMillis();
        DocPhotoTrackingUtil.Companion.trackEnter(this.mIsDocPhoto);
        this.mDocPhotoManager.setExtractCallback(this);
        this.mDocPhotoManager.parsePhotoAsync();
        this.mSaveDialog.setPhotoManager(this.mDocPhotoManager);
        this.mStartEndLinker.onCheckPermissionSucceed();
    }

    @Override // com.miui.extraphoto.docphoto.manager.PhotoManager.ExtractCallback
    public void onExtractBase() {
        this.mDocPhotoMenuFragment = new DocPhotoMenuFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("menuFragment:selection", this.mDocPhotoManager.getEnhanceType());
        this.mDocPhotoMenuFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R$id.menu_container, this.mDocPhotoMenuFragment).commitAllowingStateLoss();
        this.mStartEndLinker.onExtractBase();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResult(File file) {
        if (file == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setDataAndType(Uri.fromFile(file), "image/jpeg");
        intent.putExtra("extra_photo_edit_type", "extra_photo_editor_type_doc_photo");
        setResult(-1, intent);
    }

    @Override // android.app.Activity
    public void finishAfterTransition() {
        if (this.mStartEndLinker.hasTransition()) {
            super.finishAfterTransition();
        } else {
            finish();
        }
    }

    private class PhotoStartEndLinker implements StartEndLinker {
        private FinishReceiver mFinishReceiver;
        private Runnable mTimeOutRunnable = new Runnable() { // from class: com.miui.extraphoto.docphoto.document.DocPhotoActivity.PhotoStartEndLinker.1
            @Override // java.lang.Runnable
            public void run() {
                Log.d("DocPhotoActivity", "wait gallery prepare timeout");
                if (DocPhotoActivity.this.mSaveDialog == null || !DocPhotoActivity.this.mSaveDialog.isAdded()) {
                    return;
                }
                DocPhotoActivity.this.finish();
            }
        };
        private Handler mHandler = new Handler();

        @Override // com.miui.extraphoto.docphoto.document.DocPhotoActivity.StartEndLinker
        public void configTransition(Bundle bundle, boolean z, boolean z2) {
        }

        @Override // com.miui.extraphoto.docphoto.document.DocPhotoActivity.StartEndLinker
        public boolean hasTransition() {
            return false;
        }

        @Override // com.miui.extraphoto.docphoto.document.DocPhotoActivity.StartEndLinker
        public void onPause() {
        }

        PhotoStartEndLinker() {
        }

        @Override // com.miui.extraphoto.docphoto.document.DocPhotoActivity.StartEndLinker
        public void afterSetContentView(Bundle bundle) {
            DocPhotoActivity.this.mPreviewView.setVisibility(4);
            DocPhotoActivity.this.mMenuView.setVisibility(4);
        }

        @Override // com.miui.extraphoto.docphoto.document.DocPhotoActivity.StartEndLinker
        public void onCheckPermissionSucceed() {
            DocPhotoActivity docPhotoActivity = DocPhotoActivity.this;
            docPhotoActivity.mProgressDialogAnchorView = docPhotoActivity.mDocCorrectionView;
            DocPhotoActivity.this.showProcessLoading();
        }

        @Override // com.miui.extraphoto.docphoto.document.DocPhotoActivity.StartEndLinker
        public void onExtractBase() {
            DocPhotoActivity.this.mDocCorrectionView.setBitmap(DocPhotoActivity.this.mDocPhotoManager.getRawBitmapDelegate());
            DocPhotoActivity.this.mDocCorrectionView.setVisibility(0);
        }

        @Override // com.miui.extraphoto.docphoto.document.DocPhotoActivity.StartEndLinker
        public void onExtractFinish() {
            DocPhotoActivity.this.dismissProcessLoading();
            DocPhotoActivity docPhotoActivity = DocPhotoActivity.this;
            docPhotoActivity.mProgressDialogAnchorView = docPhotoActivity.mPreviewView;
            if (DocPhotoActivity.this.mExtractSuccess) {
                DocPhotoActivity.this.mDocPhotoMenuFragment.attachDocPhoto(DocPhotoActivity.this.mDocPhotoManager);
                performRecognizeAction();
            } else {
                ToastUtils.makeText(DocPhotoActivity.this.getApplicationContext(), R$string.doc_photo_not_support);
                onExit();
            }
        }

        @Override // com.miui.extraphoto.docphoto.document.DocPhotoActivity.StartEndLinker
        public void onDestroy() {
            this.mHandler.removeCallbacks(this.mTimeOutRunnable);
            unRegisterReceiver();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void unRegisterReceiver() {
            FinishReceiver finishReceiver = this.mFinishReceiver;
            if (finishReceiver != null) {
                DocPhotoActivity.this.unregisterReceiver(finishReceiver);
                this.mFinishReceiver = null;
            }
        }

        @Override // com.miui.extraphoto.docphoto.document.DocPhotoActivity.StartEndLinker
        public void onExport(File file) {
            if (this.mFinishReceiver == null) {
                this.mFinishReceiver = new FinishReceiver();
                DocPhotoActivity.this.registerReceiver(this.mFinishReceiver, new IntentFilter("com.miui.gallery.action.IMAGE_PREPARED"));
            }
            this.mHandler.removeCallbacks(this.mTimeOutRunnable);
            this.mHandler.postDelayed(this.mTimeOutRunnable, 3000L);
            DocPhotoActivity.this.setResult(file);
            Intent intent = new Intent("com.miui.gallery.action.EDITOR_RETURN");
            intent.setPackage("com.miui.gallery");
            intent.setDataAndType(Uri.parse(file.getAbsolutePath()), "image/jpeg");
            DocPhotoActivity.this.sendBroadcast(intent);
            Log.d("DocPhotoActivity", "notify export finish");
        }

        @Override // com.miui.extraphoto.docphoto.document.DocPhotoActivity.StartEndLinker
        public void onExit() {
            DocPhotoActivity.this.finish();
        }

        private class FinishReceiver extends BroadcastReceiver {
            private FinishReceiver() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Log.d("DocPhotoActivity", "receive prepare complete");
                if (DocPhotoActivity.this.mSaveDialog == null || !DocPhotoActivity.this.mSaveDialog.isAdded()) {
                    return;
                }
                PhotoStartEndLinker.this.unRegisterReceiver();
                DocPhotoActivity.this.finish();
            }
        }

        private void performRecognizeAction() {
            DocPhotoActivity.this.mTransitionView.setVisibility(8);
            final Bitmap effectDelegate = DocPhotoActivity.this.mDocPhotoManager.getEffectDelegate();
            DocPhotoActivity.this.mDocCorrectionView.setDocBitmap(effectDelegate, DocPhotoActivity.this.mDocPhotoManager.getPointsDelegate());
            DocPhotoActivity.this.mDocCorrectionView.setCallback(new DocCorrectionView.Callback() { // from class: com.miui.extraphoto.docphoto.document.DocPhotoActivity.PhotoStartEndLinker.2
                @Override // com.miui.extraphoto.docphoto.widget.DocCorrectionView.Callback
                public void onHighlight() {
                }

                @Override // com.miui.extraphoto.docphoto.widget.DocCorrectionView.Callback
                public void onCorrect() {
                    PhotoStartEndLinker photoStartEndLinker = PhotoStartEndLinker.this;
                    photoStartEndLinker.fadeIn(DocPhotoActivity.this.mMenuView);
                    DocPhotoActivity.this.mMenuView.setVisibility(0);
                }

                @Override // com.miui.extraphoto.docphoto.widget.DocCorrectionView.Callback
                public void onFinish() {
                    DocPhotoActivity.this.mDocCorrectionView.setVisibility(4);
                    DocPhotoActivity.this.initOriginBitmap(effectDelegate);
                    DocPhotoActivity.this.mPreviewView.setImageBitmap(effectDelegate);
                    DocPhotoActivity.this.mPreviewView.setVisibility(0);
                }
            });
            DocPhotoActivity.this.mDocCorrectionView.startAnim();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void fadeIn(View view) {
            view.setAlpha(0.0f);
            view.setVisibility(0);
            view.animate().alpha(1.0f).setDuration(300L).start();
        }
    }

    private class DocPhotoStartEndLinker implements StartEndLinker {
        private boolean mDoingTransition;
        private Transition.TransitionListener mEnterListener;
        private boolean mTransitionEnd;
        private TransitionManager mTransitionManager;
        private TransitionPatching mTransitionPatching;

        private DocPhotoStartEndLinker() {
            this.mTransitionPatching = new TransitionPatching();
            this.mDoingTransition = false;
            this.mEnterListener = new SimpleTransitionListener() { // from class: com.miui.extraphoto.docphoto.document.DocPhotoActivity.DocPhotoStartEndLinker.2
                @Override // android.transition.Transition.TransitionListener
                public void onTransitionEnd(Transition transition) {
                    transition.removeListener(this);
                    DocPhotoStartEndLinker.this.performAfterTransitionEnd();
                }
            };
        }

        @Override // com.miui.extraphoto.docphoto.document.DocPhotoActivity.StartEndLinker
        public void configTransition(Bundle bundle, boolean z, boolean z2) {
            TransitionManager transitionManager = new TransitionManager(DocPhotoActivity.this);
            this.mTransitionManager = transitionManager;
            this.mDoingTransition = transitionManager.tryConfigTransitionMode(bundle != null, z);
            this.mTransitionPatching.initEnterTransitionClear(DocPhotoActivity.this);
            if (z2) {
                this.mTransitionEnd = true;
            }
        }

        @Override // com.miui.extraphoto.docphoto.document.DocPhotoActivity.StartEndLinker
        public void afterSetContentView(Bundle bundle) {
            DocPhotoActivity docPhotoActivity = DocPhotoActivity.this;
            docPhotoActivity.mProgressDialogAnchorView = docPhotoActivity.mPreviewView;
            this.mTransitionManager.attachPhotoView(DocPhotoActivity.this.mTransitionView);
            this.mTransitionManager.attachMenuView(DocPhotoActivity.this.mMenuView);
            this.mTransitionManager.attachUselessView(DocPhotoActivity.this.findViewById(R$id.useless_view));
            this.mTransitionManager.postActivityCreate();
        }

        @Override // com.miui.extraphoto.docphoto.document.DocPhotoActivity.StartEndLinker
        public void onCheckPermissionSucceed() {
            DocPhotoActivity.this.showProcessLoading();
        }

        @Override // com.miui.extraphoto.docphoto.document.DocPhotoActivity.StartEndLinker
        public void onExtractBase() {
            DocPhotoActivity.this.dismissProcessLoading();
            DocPhotoActivity.this.mTransitionView.setVisibility(0);
            DocPhotoActivity.this.mTransitionView.setImageBitmap(DocPhotoActivity.this.mDocPhotoManager.getPhotoBitmapDelegate());
            if (this.mTransitionManager.hasTransition() && !this.mTransitionEnd) {
                this.mTransitionManager.startTransition(this.mEnterListener);
            } else {
                performAfterTransitionEnd();
            }
        }

        @Override // com.miui.extraphoto.docphoto.document.DocPhotoActivity.StartEndLinker
        public void onExtractFinish() {
            performAfterTransitionAndExtract();
        }

        @Override // com.miui.extraphoto.docphoto.document.DocPhotoActivity.StartEndLinker
        public void onPause() {
            if (this.mDoingTransition) {
                TransitionManager transitionManager = this.mTransitionManager;
                if (transitionManager != null) {
                    transitionManager.cancelEnterTransition();
                }
                performAfterTransitionEnd();
            }
        }

        @Override // com.miui.extraphoto.docphoto.document.DocPhotoActivity.StartEndLinker
        public void onDestroy() {
            TransitionManager transitionManager = this.mTransitionManager;
            if (transitionManager != null) {
                transitionManager.onDestroy();
            }
            this.mTransitionPatching.onActivityDestroy(DocPhotoActivity.this);
            this.mTransitionPatching.clearAnimationHandler();
        }

        private void onActivityResult() {
            Bitmap bitmap = (DocPhotoActivity.this.mPreviewView == null || DocPhotoActivity.this.mPreviewView.getBitmap() == null) ? null : DocPhotoActivity.this.mPreviewView.getBitmap();
            if (bitmap != null) {
                DocPhotoActivity.this.mTransitionView.setImageBitmap(bitmap);
            }
            DocPhotoActivity.this.mTransitionView.setVisibility(0);
            DocPhotoActivity.this.mPreviewView.setVisibility(8);
            ActivityCompat.setEnterSharedElementCallback(DocPhotoActivity.this, new ActivityCompat.SharedElementCallback() { // from class: com.miui.extraphoto.docphoto.document.DocPhotoActivity.DocPhotoStartEndLinker.1
                @Override // com.miui.extraphoto.common.compat.ActivityCompat.SharedElementCallback
                public void onSharedElementStart() {
                    DocPhotoActivity.this.dismissSaveLoading();
                }
            });
            if (this.mTransitionManager.updateIsDoTransition()) {
                this.mTransitionManager.onExit(bitmap == null, null, false);
                ActivityCompat.finishAfterTransition(DocPhotoActivity.this);
            } else {
                DocPhotoActivity.this.finish();
            }
        }

        @Override // com.miui.extraphoto.docphoto.document.DocPhotoActivity.StartEndLinker
        public void onExport(File file) {
            DocPhotoActivity.this.setResult(file);
            onActivityResult();
        }

        @Override // com.miui.extraphoto.docphoto.document.DocPhotoActivity.StartEndLinker
        public void onExit() {
            onActivityResult();
        }

        @Override // com.miui.extraphoto.docphoto.document.DocPhotoActivity.StartEndLinker
        public boolean hasTransition() {
            return this.mTransitionManager.hasTransition();
        }

        private void performAfterTransitionAndExtract() {
            if (DocPhotoActivity.this.mExtractEnd) {
                if (!DocPhotoActivity.this.mExtractSuccess) {
                    ToastUtils.makeText(DocPhotoActivity.this.getApplicationContext(), R$string.doc_photo_not_support);
                    onExit();
                } else if (this.mTransitionEnd) {
                    DocPhotoActivity.this.showProcessLoading();
                    DocPhotoActivity.this.mDocPhotoMenuFragment.attachDocPhoto(DocPhotoActivity.this.mDocPhotoManager);
                    DocPhotoActivity.this.setPreviewBitmap(DocPhotoActivity.this.mDocPhotoManager.getEffectDelegate());
                    DocPhotoActivity.this.mDocPhotoManager.preview();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void performAfterTransitionEnd() {
            this.mTransitionEnd = true;
            this.mDoingTransition = false;
            performAfterTransitionAndExtract();
        }
    }

    @Override // com.miui.extraphoto.docphoto.manager.PhotoManager.ExtractCallback
    public void onExtractFinish(boolean z) {
        this.mExtractEnd = true;
        this.mExtractSuccess = z;
        this.mStartEndLinker.onExtractFinish();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.FragmentActivity
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof DocPhotoAdjustFragInterface) {
            DocPhotoAdjustFragInterface docPhotoAdjustFragInterface = (DocPhotoAdjustFragInterface) fragment;
            docPhotoAdjustFragInterface.attachDocPhotoMenuPresenter(this.mDocPhotoMenuFragment);
            docPhotoAdjustFragInterface.attachDocPhoto(this.mDocPhotoManager);
        }
        if (fragment instanceof DocPhotoHandwirteFragment) {
            DocPhotoHandwirteFragment docPhotoHandwirteFragment = (DocPhotoHandwirteFragment) fragment;
            docPhotoHandwirteFragment.attachDocPhotoMenuPresenter(this.mDocPhotoMenuFragment);
            docPhotoHandwirteFragment.attachDocPhoto(this.mDocPhotoManager);
        }
        if (fragment instanceof DocPhotoCropFragment) {
            DocPhotoCropFragment docPhotoCropFragment = (DocPhotoCropFragment) fragment;
            docPhotoCropFragment.attachDocPhotoMenuPresenter(this.mDocPhotoMenuFragment);
            docPhotoCropFragment.attachDocPhoto(this.mDocPhotoManager);
        }
    }

    @Override // com.miui.extraphoto.docphoto.iactivity.DocPhotoActivityInterface
    public void attachCrop() {
        if (this.mDocPhotoCropFragment == null || !this.mDocPhotoMenuFragment.isRendering()) {
            if (this.mDocPhotoCropFragment == null) {
                this.mDocPhotoCropFragment = new DocPhotoCropFragment();
            }
            if (this.mDocPhotoCropFragment.isAdded()) {
                return;
            }
            getSupportFragmentManager().beginTransaction().add(R$id.render_zone, this.mDocPhotoCropFragment).commitAllowingStateLoss();
            this.mTitleView.setText(R$string.doc_photo_adjust_section_title);
            this.mGuideView.setVisibility(4);
            findViewById(R$id.title_zone).setOnClickListener(null);
        }
    }

    @Override // com.miui.extraphoto.docphoto.iactivity.DocPhotoActivityInterface
    public void attachHandwrite() {
        DocPhotoMenuFragment docPhotoMenuFragment = this.mDocPhotoMenuFragment;
        if (docPhotoMenuFragment == null || !docPhotoMenuFragment.isRendering()) {
            if (this.mDocPhotoHandwriteFragment == null) {
                this.mDocPhotoHandwriteFragment = new DocPhotoHandwirteFragment();
            }
            if (this.mDocPhotoHandwriteFragment.isAdded()) {
                return;
            }
            getSupportFragmentManager().beginTransaction().add(R$id.render_zone, this.mDocPhotoHandwriteFragment).commitAllowingStateLoss();
            this.mTitleView.setText(R$string.doc_photo_enhance_handmode_remove);
            this.mGuideView.setVisibility(4);
            findViewById(R$id.title_zone).setOnClickListener(null);
        }
    }

    @Override // com.miui.extraphoto.docphoto.iactivity.DocPhotoActivityInterface
    public void detachCrop() {
        DocPhotoCropFragment docPhotoCropFragment = this.mDocPhotoCropFragment;
        if (docPhotoCropFragment != null && docPhotoCropFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(this.mDocPhotoCropFragment).commitAllowingStateLoss();
            this.mTitleView.setText(R$string.doc_photo_doc_photo_title);
            if (DocumentProcessJNI.isSupportMoer()) {
                this.mGuideView.setVisibility(0);
                findViewById(R$id.title_zone).setOnClickListener(this.mGuideClickListener);
            }
        }
    }

    @Override // com.miui.extraphoto.docphoto.iactivity.DocPhotoActivityInterface
    public void detachHandwrite() {
        DocPhotoHandwirteFragment docPhotoHandwirteFragment = this.mDocPhotoHandwriteFragment;
        if (docPhotoHandwirteFragment != null && docPhotoHandwirteFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(this.mDocPhotoHandwriteFragment).commitAllowingStateLoss();
            this.mTitleView.setText(R$string.doc_photo_doc_photo_title);
            if (DocumentProcessJNI.isSupportMoer()) {
                this.mGuideView.setVisibility(0);
                findViewById(R$id.title_zone).setOnClickListener(this.mGuideClickListener);
            }
        }
    }

    @Override // com.miui.extraphoto.docphoto.iactivity.DocPhotoActivityInterface
    public DocPhotoCropFragment getCropFragment() {
        DocPhotoCropFragment docPhotoCropFragment = this.mDocPhotoCropFragment;
        if (docPhotoCropFragment == null || !docPhotoCropFragment.isAdded()) {
            return null;
        }
        return this.mDocPhotoCropFragment;
    }

    @Override // com.miui.extraphoto.docphoto.iactivity.DocPhotoActivityInterface
    public DocPhotoHandwirteFragment getHandwriteFragment() {
        DocPhotoHandwirteFragment docPhotoHandwirteFragment = this.mDocPhotoHandwriteFragment;
        if (docPhotoHandwirteFragment == null || !docPhotoHandwirteFragment.isAdded()) {
            return null;
        }
        return this.mDocPhotoHandwriteFragment;
    }

    @Override // miuix.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        DocPhotoHandwirteFragment docPhotoHandwirteFragment;
        if (getSupportFragmentManager().popBackStackImmediate()) {
            return;
        }
        DocPhotoCropFragment docPhotoCropFragment = this.mDocPhotoCropFragment;
        if ((docPhotoCropFragment != null && docPhotoCropFragment.isAdded()) || ((docPhotoHandwirteFragment = this.mDocPhotoHandwriteFragment) != null && docPhotoHandwirteFragment.isAdded() && this.mDocPhotoMenuFragment != null)) {
            this.mDocPhotoMenuFragment.onBack();
        } else {
            cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initOriginBitmap(Bitmap bitmap) {
        if (this.mOriginPreviewSet) {
            return;
        }
        this.mPreviewView.setOriginBitmap(bitmap);
        this.mOriginPreviewSet = true;
    }

    @Override // com.miui.extraphoto.docphoto.iactivity.DocPhotoActivityInterface
    public void scaleInImage() {
        this.mPreviewView.scaleIn();
    }

    @Override // com.miui.extraphoto.docphoto.iactivity.DocPhotoActivityInterface
    public void updateOrigin() {
        this.mPreviewView.setOriginBitmap(this.mDocPhotoManager.getCurrentCropPreview());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPreviewBitmap(Bitmap bitmap) {
        this.mPreviewView.setImageBitmap(bitmap);
    }

    public void showSaveLoading() {
        ExportFragment exportFragment = this.mSaveDialog;
        if (exportFragment == null || exportFragment.isAdded()) {
            return;
        }
        this.mSaveDialog.setExportCallback(this);
        this.mSaveDialog.showAllowingStateLoss(getSupportFragmentManager(), "saveDialog");
    }

    public void dismissSaveLoading() {
        ExportFragment exportFragment = this.mSaveDialog;
        if (exportFragment == null || !exportFragment.isAdded()) {
            return;
        }
        this.mSaveDialog.dismissSafely();
        this.mSaveDialog.setExportCallback(null);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.mIsProcessing) {
            return false;
        }
        DocPhotoMenuFragment docPhotoMenuFragment = this.mDocPhotoMenuFragment;
        if (docPhotoMenuFragment != null && docPhotoMenuFragment.isHandBubbleToastShowing() && motionEvent.getAction() == 0) {
            this.mDocPhotoMenuFragment.dismissHandBubbleToast();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.miui.extraphoto.docphoto.iactivity.ActivityInterface
    public void showProcessLoading() {
        View view;
        LoadingDialog loadingDialog = this.mProgressDialog;
        if (loadingDialog == null || (view = this.mProgressDialogAnchorView) == null) {
            return;
        }
        this.mIsProcessing = true;
        loadingDialog.showDelayed(view);
    }

    @Override // com.miui.extraphoto.docphoto.iactivity.ActivityInterface
    public void dismissProcessLoading() {
        LoadingDialog loadingDialog = this.mProgressDialog;
        if (loadingDialog != null) {
            this.mIsProcessing = false;
            loadingDialog.dismiss();
        }
    }

    @Override // com.miui.extraphoto.docphoto.iactivity.DocPhotoActivityInterface
    public void showOrigin(boolean z) {
        this.mPreviewView.drawOrigin(z);
    }

    @Override // com.miui.extraphoto.docphoto.iactivity.DocPhotoActivityInterface
    public void onSave() {
        showSaveLoading();
    }

    @Override // com.miui.extraphoto.docphoto.iactivity.DocPhotoActivityInterface
    public void onCancel() {
        cancel();
    }

    @Override // com.miui.extraphoto.docphoto.ExportTask.ExportCallback
    public void onExport(File file, Uri uri) {
        DocPhotoTrackingUtil.Companion.trackSave(this.mDocPhotoManager.getEnhanceTypeString(), this.mDocPhotoManager.isCroped(), this.mDocPhotoManager.isUndistortion(), this.mDocPhotoManager.isDemoire(), this.mDocPhotoManager.isDeHandwrite(), this.mDocPhotoManager.getHandRemoveCount(), this.mDocPhotoManager.getHandRecoverCount(), System.currentTimeMillis() - this.mEnterTime, this.mIsDocPhoto);
        this.mStartEndLinker.onExport(file);
    }

    @Override // com.miui.extraphoto.docphoto.ExportTask.ExportCallback
    public void onError() {
        onExit();
    }

    @Override // com.miui.extraphoto.docphoto.iactivity.DocPhotoActivityInterface
    public void setImage(Bitmap bitmap) {
        this.mTransitionView.setVisibility(4);
        setPreviewBitmap(bitmap);
        initOriginBitmap(bitmap);
        Log.d("DocPhotoActivity", "set bitmap from menu fragment");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onExit() {
        DocPhotoTrackingUtil.Companion.trackCancel(System.currentTimeMillis() - this.mEnterTime);
        StartEndLinker startEndLinker = this.mStartEndLinker;
        if (startEndLinker != null) {
            startEndLinker.onExit();
        }
    }

    private void cancel() {
        DocPhotoManager docPhotoManager = this.mDocPhotoManager;
        if (docPhotoManager != null && !docPhotoManager.isOriginImage()) {
            AlertDialogFragment alertDialogFragmentBuild = new AlertDialogFragment.Builder().setTitle(R$string.exit_alert_title).setMessage(R$string.exit_alert_msg).setPositiveButton(R$string.ok).setNegativeButton(R$string.cancel).setCancellable(true).build();
            alertDialogFragmentBuild.setCallbacks(new AlertDialogFragment.Callbacks() { // from class: com.miui.extraphoto.docphoto.document.DocPhotoActivity.3
                @Override // com.miui.extraphoto.common.widget.AlertDialogFragment.Callbacks
                public void onCancel(AlertDialogFragment alertDialogFragment) {
                }

                @Override // com.miui.extraphoto.common.widget.AlertDialogFragment.Callbacks
                public void onDismiss(AlertDialogFragment alertDialogFragment) {
                }

                @Override // com.miui.extraphoto.common.widget.AlertDialogFragment.Callbacks
                public void onClick(AlertDialogFragment alertDialogFragment, int i) {
                    if (i == -1) {
                        alertDialogFragment.dismissSafely();
                        DocPhotoActivity.this.onExit();
                    } else if (i == -2) {
                        alertDialogFragment.dismissSafely();
                    }
                }
            });
            alertDialogFragmentBuild.showAllowingStateLoss(getSupportFragmentManager(), "alert");
            return;
        }
        onExit();
    }

    @Override // com.miui.extraphoto.docphoto.iactivity.DocPhotoActivityInterface
    public void dismissToast() {
        EditorToast editorToast = this.mEditorToast;
        if (editorToast != null) {
            editorToast.dismiss();
        }
    }
}
