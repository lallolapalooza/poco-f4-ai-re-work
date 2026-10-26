package com.miui.extraphoto.docphoto.document;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.miui.extraphoto.common.BaseActivity;
import com.miui.extraphoto.common.compat.SystemUiUtil;
import com.miui.extraphoto.common.compat.WindowCompat;
import com.miui.extraphoto.common.storage.Storage;
import com.miui.extraphoto.common.utils.ReceiverUtils;
import com.miui.extraphoto.common.utils.ToastUtils;
import com.miui.extraphoto.common.utils.UriUtils;
import com.miui.extraphoto.common.widget.LoadingDialog;
import com.miui.extraphoto.docphoto.ExportFragment;
import com.miui.extraphoto.docphoto.ExportTask;
import com.miui.extraphoto.docphoto.R$id;
import com.miui.extraphoto.docphoto.R$layout;
import com.miui.extraphoto.docphoto.R$string;
import com.miui.extraphoto.docphoto.ScreenBroadcastReceiver;
import com.miui.extraphoto.docphoto.Util;
import com.miui.extraphoto.docphoto.iactivity.DocPhotoCameraActivityInterface;
import com.miui.extraphoto.docphoto.ifragment.RawDocPhotoAdjustFragInterface;
import com.miui.extraphoto.docphoto.manager.DocPhotoManager;
import com.miui.extraphoto.docphoto.manager.DocProcessor;
import com.miui.extraphoto.docphoto.manager.PhotoManager;
import com.xiaomi.ocr.sdk.imgprocess.DocumentProcess$EnhanceType;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class DocPhotoPostProcessingActivity extends BaseActivity implements DocPhotoCameraActivityInterface, PhotoManager.ExtractCallback, ExportTask.ExportCallback {
    private DocPhotoPostProcessingFragment mAdjustFragment;
    private View mContentContainer;
    private DocProcessor mDocProcessor;
    private boolean mHasAdjusted;
    private LoadingDialog mProgressDialog;
    private DocPhotoManager mRawDocPhotoManager;
    private ExportFragment mSaveDialog;
    private ScreenBroadcastReceiver mScreenReceiver;

    @Override // com.miui.extraphoto.docphoto.ExportTask.ExportCallback
    public void onCancelExport() {
    }

    @Override // com.miui.extraphoto.common.BaseActivity, miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        WindowCompat.setCutoutModeShortEdges(getWindow());
        if (getIntent().getBooleanExtra("StartActivityWhenLocked", false)) {
            this.mScreenReceiver = new ScreenBroadcastReceiver(this);
            WindowCompat.setShowWhenLocked(this, true);
            ReceiverUtils.registerReceiver(this, this.mScreenReceiver, "android.intent.action.SCREEN_OFF");
        }
        super.onCreate(null);
        setContentView(R$layout.doc_photo_activity_camera_doc_photo);
        init();
        checkPermission();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        dismissProcessLoading();
        dismissSaveLoading();
        ScreenBroadcastReceiver screenBroadcastReceiver = this.mScreenReceiver;
        if (screenBroadcastReceiver != null) {
            ReceiverUtils.safeUnregisterReceiver(this, screenBroadcastReceiver);
            this.mScreenReceiver.release();
        }
        DocProcessor docProcessor = this.mDocProcessor;
        if (docProcessor != null) {
            docProcessor.release();
        }
        DocPhotoManager docPhotoManager = this.mRawDocPhotoManager;
        if (docPhotoManager != null) {
            docPhotoManager.release();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onAttachedToWindow() {
        if (WindowCompat.isNotch(this)) {
            SystemUiUtil.extendToStatusBar(getWindow().getDecorView());
        }
    }

    @Override // com.miui.extraphoto.common.BaseActivity
    protected void onScreenSizeChanged() {
        super.onScreenSizeChanged();
        this.mContentContainer.post(new Runnable() { // from class: com.miui.extraphoto.docphoto.document.DocPhotoPostProcessingActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onScreenSizeChanged$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onScreenSizeChanged$0() {
        this.mProgressDialog.updateLocation(this.mContentContainer);
    }

    private void init() {
        this.mProgressDialog = new LoadingDialog(this);
        this.mDocProcessor = new DocProcessor(getApplicationContext());
        this.mContentContainer = findViewById(R$id.content_container);
    }

    private void initDialog() {
        ExportFragment exportFragment = new ExportFragment();
        this.mSaveDialog = exportFragment;
        exportFragment.setPhotoManager(this.mRawDocPhotoManager);
        this.mSaveDialog.setExportCallback(this);
    }

    @Override // com.miui.extraphoto.common.BaseActivity, com.miui.extraphoto.common.permission.PermissionCheckHelper.PermissionCheckCallback
    public void onCheckPermissionSucceed(boolean z) {
        super.onCheckPermissionSucceed(z);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        Log.d("DocPhotoPostProcessingActivity", "onCheckPermissionSucceed: " + intent.getData());
        Uri data = intent.getData();
        if (data == null) {
            finish();
            return;
        }
        if (!UriUtils.exist(this, data)) {
            Log.d("DocPhotoPostProcessingActivity", "photo does not exist");
            finish();
            return;
        }
        String path = UriUtils.toPath(data);
        if (UriUtils.isPhotoUri(data)) {
            data = Uri.fromFile(new File(path));
        }
        Storage storage = new Storage(path, data);
        String stringExtra = intent.getStringExtra("privacyWatermark");
        DocumentProcess$EnhanceType documentProcess$EnhanceTypeConvertToEnhanceType = Util.convertToEnhanceType(intent.getStringExtra("com.miui.extraphoto.extra.DOCUMENT_PHOTO_EFFECT"));
        if (documentProcess$EnhanceTypeConvertToEnhanceType == null) {
            Log.d("DocPhotoPostProcessingActivity", "not provide effect type ,use none");
            documentProcess$EnhanceTypeConvertToEnhanceType = DocumentProcess$EnhanceType.RAW;
        }
        DocPhotoManager docPhotoManager = new DocPhotoManager(storage, documentProcess$EnhanceTypeConvertToEnhanceType, false, true, this.mDocProcessor, stringExtra);
        this.mRawDocPhotoManager = docPhotoManager;
        docPhotoManager.setExtractCallback(this);
        this.mRawDocPhotoManager.parsePhotoAsync();
        initDialog();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.FragmentActivity
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof RawDocPhotoAdjustFragInterface) {
            ((RawDocPhotoAdjustFragInterface) fragment).attachRawDocPhoto(this.mRawDocPhotoManager);
        }
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

    @Override // com.miui.extraphoto.docphoto.iactivity.ActivityInterface
    public void showProcessLoading() {
        LoadingDialog loadingDialog = this.mProgressDialog;
        if (loadingDialog != null) {
            loadingDialog.showDelayed(this.mContentContainer);
        }
    }

    @Override // com.miui.extraphoto.docphoto.iactivity.ActivityInterface
    public void dismissProcessLoading() {
        LoadingDialog loadingDialog = this.mProgressDialog;
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    @Override // com.miui.extraphoto.docphoto.iactivity.DocPhotoCameraActivityInterface
    public void onSave() {
        showSaveLoading();
    }

    @Override // com.miui.extraphoto.docphoto.iactivity.DocPhotoCameraActivityInterface
    public void onCancel() {
        finish();
    }

    @Override // com.miui.extraphoto.docphoto.ExportTask.ExportCallback
    public void onExport(File file, Uri uri) {
        Log.d("DocPhotoPostProcessingActivity", "onExport: " + uri);
        if (uri != null) {
            Intent intent = new Intent();
            intent.setData(uri);
            setResult(-1, intent);
        }
        finish();
    }

    @Override // com.miui.extraphoto.docphoto.ExportTask.ExportCallback
    public void onError() {
        finish();
    }

    @Override // com.miui.extraphoto.docphoto.iactivity.DocPhotoCameraActivityInterface
    public void setHasAdjusted() {
        this.mHasAdjusted = true;
    }

    @Override // com.miui.extraphoto.docphoto.manager.PhotoManager.ExtractCallback
    public void onExtractBase() {
        this.mAdjustFragment = new DocPhotoPostProcessingFragment();
        getSupportFragmentManager().beginTransaction().add(R$id.content_container, this.mAdjustFragment).commitNowAllowingStateLoss();
    }

    @Override // com.miui.extraphoto.docphoto.manager.PhotoManager.ExtractCallback
    public void onExtractFinish(boolean z) {
        if (!z) {
            ToastUtils.makeText(this, R$string.doc_photo_not_support);
            finish();
        }
        DocPhotoPostProcessingFragment docPhotoPostProcessingFragment = this.mAdjustFragment;
        if (docPhotoPostProcessingFragment != null) {
            docPhotoPostProcessingFragment.pointsInited();
        }
    }
}
