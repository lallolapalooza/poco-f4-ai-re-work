package com.miui.extraphoto.docphoto;

import android.net.Uri;
import android.os.AsyncTask;
import com.miui.extraphoto.common.storage.Storage;
import com.miui.extraphoto.common.utils.MediaUtils;
import com.miui.extraphoto.common.utils.StaticContext;
import com.miui.extraphoto.docphoto.manager.PhotoManager;
import com.miui.mediaeditor.storage.entrance.XStorage;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class ExportTask extends AsyncTask<Void, Void, Boolean> {
    private ExportCallback mExportCallback;
    private Uri mExportUri;
    private boolean mFinish;
    private PhotoManager mPhotoManager;
    private Storage mStorage;

    public interface ExportCallback {
        void onCancelExport();

        void onError();

        void onExport(File file, Uri uri);
    }

    public ExportTask(PhotoManager photoManager) {
        this.mPhotoManager = photoManager;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Boolean doInBackground(Void... voidArr) {
        if (isCancelled()) {
            return Boolean.FALSE;
        }
        Storage exportStorage = this.mPhotoManager.getExportStorage();
        this.mStorage = exportStorage;
        if (exportStorage == null) {
            return Boolean.FALSE;
        }
        boolean zExport = this.mPhotoManager.export(exportStorage);
        if (isCancelled()) {
            XStorage.delete(this.mStorage.getPath(), "ExportTaskCancel");
            return Boolean.FALSE;
        }
        Uri uriUpdateMediaStore = this.mPhotoManager.updateMediaStore();
        this.mExportUri = uriUpdateMediaStore;
        if (uriUpdateMediaStore != null) {
            MediaUtils.saveToCloudAlbum(StaticContext.sGetAndroidContext(), this.mStorage);
        }
        return Boolean.valueOf(zExport);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onCancelled(Boolean bool) {
        super.onCancelled(bool);
        ExportCallback exportCallback = this.mExportCallback;
        if (exportCallback != null) {
            exportCallback.onCancelExport();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Boolean bool) {
        super.onPostExecute(bool);
        this.mFinish = true;
        if (this.mExportCallback != null) {
            if (bool.booleanValue()) {
                this.mExportCallback.onExport(new File(this.mStorage.getPath()), this.mExportUri);
            } else {
                this.mExportCallback.onError();
            }
        }
    }

    public boolean isFinish() {
        return this.mFinish;
    }

    public void setCallback(ExportCallback exportCallback) {
        this.mExportCallback = exportCallback;
    }
}
