package com.miui.extraphoto.docphoto.manager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import com.miui.camerainfra.exif.ExifInterface;
import com.miui.extraphoto.common.storage.Storage;
import com.miui.extraphoto.common.utils.ExifUtil;
import com.miui.extraphoto.common.utils.IOUtils;
import com.miui.extraphoto.common.utils.MediaUtils;
import com.miui.mediaeditor.storage.entrance.XStorage;
import java.io.File;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public abstract class PhotoManager implements Exporter {
    protected Bitmap mCurrentPreview;
    protected String mExportFilePath;
    private ExtractCallback mExtractCallback;
    private ParseTask mParseTask;
    private PreviewCallback mPreviewCallback;
    private PreviewTask mPreviewTask;

    interface CancelableWork<T> {
        void cancel();

        boolean isCancel();

        T run();
    }

    public interface ExtractCallback {
        void onExtractBase();

        void onExtractFinish(boolean z);
    }

    public interface PreviewCallback {
        void onRefresh(Bitmap bitmap);
    }

    protected abstract boolean parseBase();

    protected abstract boolean parseExtension();

    protected abstract Bitmap renderPreview();

    public abstract Uri updateMediaStore();

    public void parsePhotoAsync() {
        cancelExtract();
        ParseTask parseTask = new ParseTask();
        this.mParseTask = parseTask;
        parseTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void preview() {
        cancelPreview();
        PreviewTask previewTask = new PreviewTask();
        this.mPreviewTask = previewTask;
        previewTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    protected CancelableWork<Bitmap> previewWork() {
        return new CancelableWork<Bitmap>() { // from class: com.miui.extraphoto.docphoto.manager.PhotoManager.1
            @Override // com.miui.extraphoto.docphoto.manager.PhotoManager.CancelableWork
            public void cancel() {
            }

            @Override // com.miui.extraphoto.docphoto.manager.PhotoManager.CancelableWork
            public boolean isCancel() {
                return false;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.miui.extraphoto.docphoto.manager.PhotoManager.CancelableWork
            public Bitmap run() {
                return PhotoManager.this.renderPreview();
            }
        };
    }

    public void release() {
        cancelExtract();
        cancelPreview();
        setExtractCallback(null);
        setPreviewCallback(null);
        Bitmap bitmap = this.mCurrentPreview;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        this.mCurrentPreview.recycle();
    }

    public void setPreviewCallback(PreviewCallback previewCallback) {
        this.mPreviewCallback = previewCallback;
    }

    public Bitmap getCurrentPreview() {
        return this.mCurrentPreview;
    }

    private void cancelPreview() {
        PreviewTask previewTask = this.mPreviewTask;
        if (previewTask != null) {
            previewTask.cancel(true);
            if (this.mPreviewTask.mCancelableWork != null) {
                this.mPreviewTask.mCancelableWork.cancel();
            }
        }
    }

    public void setExtractCallback(ExtractCallback extractCallback) {
        this.mExtractCallback = extractCallback;
    }

    private void cancelExtract() {
        ParseTask parseTask = this.mParseTask;
        if (parseTask != null) {
            parseTask.cancel(true);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [int] */
    protected Uri updateImageUri(Storage storage) throws Throwable {
        InputStream inputStreamOpenInputStreamSafely;
        String str = "PhotoManager";
        File file = new File(storage.getPath());
        long jLastModified = file.lastModified() / 1000;
        String name = file.getName();
        ExifInterface exifInterface = ExifUtil.getExifInterface(storage);
        long takenTime = ExifUtil.getTakenTime(exifInterface);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        InputStream inputStream = null;
        try {
            try {
                inputStreamOpenInputStreamSafely = XStorage.openInputStreamSafely(storage.getUri(), true);
                try {
                    BitmapFactory.decodeStream(inputStreamOpenInputStreamSafely, null, options);
                } catch (Exception e) {
                    e = e;
                    e.printStackTrace();
                }
            } catch (Throwable th) {
                th = th;
                inputStream = inputStreamOpenInputStreamSafely;
                IOUtils.close(str, inputStream);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            inputStreamOpenInputStreamSafely = null;
        } catch (Throwable th2) {
            th = th2;
            IOUtils.close(str, inputStream);
            throw th;
        }
        IOUtils.close("PhotoManager", inputStreamOpenInputStreamSafely);
        int i = options.outWidth;
        str = options.outHeight;
        return MediaUtils.updateUri(storage.getUri(), name, name, takenTime, jLastModified, "image/jpeg", ExifUtil.getOrientation(exifInterface), file.getPath(), file.length(), i, str, exifInterface != null ? exifInterface.getLatLong() : null);
    }

    private class ParseTask extends AsyncTask<Void, Void, Boolean> {
        private ParseTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Void... voidArr) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            boolean base = PhotoManager.this.parseBase();
            Log.d("PhotoManager", "parse base consume " + (System.currentTimeMillis() - jCurrentTimeMillis) + base);
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            if (base) {
                publishProgress(new Void[0]);
                base = PhotoManager.this.parseExtension();
                Log.d("PhotoManager", "parse extension consume " + (System.currentTimeMillis() - jCurrentTimeMillis2) + base);
            }
            return Boolean.valueOf(base);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(Void... voidArr) {
            super.onProgressUpdate((Object[]) voidArr);
            if (PhotoManager.this.mExtractCallback != null) {
                PhotoManager.this.mExtractCallback.onExtractBase();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            if (PhotoManager.this.mExtractCallback != null) {
                PhotoManager.this.mExtractCallback.onExtractFinish(bool.booleanValue());
            }
        }
    }

    private class PreviewTask extends AsyncTask<Void, Void, Bitmap> {
        private CancelableWork<Bitmap> mCancelableWork;

        private PreviewTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (PhotoManager.this.mPreviewCallback == null || this.mCancelableWork.isCancel()) {
                return;
            }
            PhotoManager.this.mPreviewCallback.onRefresh(bitmap);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Bitmap doInBackground(Void... voidArr) {
            CancelableWork<Bitmap> cancelableWorkPreviewWork = PhotoManager.this.previewWork();
            this.mCancelableWork = cancelableWorkPreviewWork;
            return cancelableWorkPreviewWork.run();
        }
    }
}
