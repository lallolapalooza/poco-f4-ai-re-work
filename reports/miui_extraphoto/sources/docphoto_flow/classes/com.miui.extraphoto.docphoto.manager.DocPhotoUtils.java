package com.miui.extraphoto.docphoto.manager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import com.miui.camerainfra.exif.ExifInterface;
import com.miui.extraphoto.common.storage.Storage;
import com.miui.extraphoto.common.utils.IOUtils;
import com.miui.extraphoto.common.utils.StaticContext;
import com.miui.mediaeditor.storage.entrance.XStorage;
import com.miui.mediaeditor.wcg.WcgUtils;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
class DocPhotoUtils {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r4v0, types: [com.miui.extraphoto.common.storage.Storage] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    static Bitmap decodeBitmapWithSample(int i, String str, Storage storage) throws Throwable {
        InputStream inputStream;
        ?? r0 = 0;
        Bitmap bitmapDecodeStream = null;
        try {
            try {
                inputStream = storage.getInputStream();
                try {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = i;
                    bitmapDecodeStream = BitmapFactory.decodeStream(inputStream, null, options);
                    storage = inputStream;
                } catch (FileNotFoundException e) {
                    e = e;
                    e.printStackTrace();
                    storage = inputStream;
                }
            } catch (Throwable th) {
                th = th;
                r0 = storage;
                IOUtils.close(str, r0);
                throw th;
            }
        } catch (FileNotFoundException e2) {
            e = e2;
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
            IOUtils.close(str, r0);
            throw th;
        }
        IOUtils.close(str, storage);
        return bitmapDecodeStream;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r3v0, types: [android.net.Uri] */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7 */
    static Bitmap decodeBitmap(String str, Uri uri) throws Throwable {
        InputStream inputStreamOpenInputStream;
        ?? r1 = 0;
        Bitmap bitmapDecodeStream = null;
        try {
            try {
                inputStreamOpenInputStream = StaticContext.sGetAndroidContext().getContentResolver().openInputStream(uri);
                try {
                    bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenInputStream, null, null);
                    uri = inputStreamOpenInputStream;
                } catch (FileNotFoundException e) {
                    e = e;
                    e.printStackTrace();
                    uri = inputStreamOpenInputStream;
                }
            } catch (Throwable th) {
                th = th;
                r1 = uri;
                IOUtils.close(str, r1);
                throw th;
            }
        } catch (FileNotFoundException e2) {
            e = e2;
            inputStreamOpenInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            IOUtils.close(str, r1);
            throw th;
        }
        IOUtils.close(str, uri);
        return bitmapDecodeStream;
    }

    static byte[] exportJpegWithExif(Bitmap bitmap, ExifInterface exifInterface) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 97, exifInterface.getExifWriterStream(byteArrayOutputStream, bitmap.getColorSpace().isWideGamut() ? WcgUtils.getIccByteArray(StaticContext.sGetAndroidContext().getResources()) : null));
        return byteArrayOutputStream.toByteArray();
    }

    static boolean writeToFileDirectly(String str, byte[] bArr, Uri uri) throws Throwable {
        boolean z = true;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            try {
                OutputStream outputStreamOpenOutputStreamSafely = XStorage.openOutputStreamSafely(uri, true);
                if (outputStreamOpenOutputStreamSafely != null) {
                    BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(outputStreamOpenOutputStreamSafely);
                    try {
                        bufferedOutputStream2.write(bArr);
                        bufferedOutputStream2.flush();
                        IOUtils.close(str, bufferedOutputStream2);
                    } catch (IOException e) {
                        e = e;
                        bufferedOutputStream = bufferedOutputStream2;
                        e.printStackTrace();
                        IOUtils.close(str, bufferedOutputStream);
                        z = false;
                    } catch (Throwable th) {
                        th = th;
                        bufferedOutputStream = bufferedOutputStream2;
                        IOUtils.close(str, bufferedOutputStream);
                        throw th;
                    }
                    return z;
                }
                IOUtils.close(str, null);
                return false;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
        e.printStackTrace();
        IOUtils.close(str, bufferedOutputStream);
        z = false;
        return z;
    }

    static ExifInterface defaultExportExif(ExifInterface exifInterface) {
        ExifInterface exifInterface2 = new ExifInterface();
        exifInterface2.copyAttributes(exifInterface);
        exifInterface2.setMimeType(4);
        exifInterface2.setThumbnailBytes(null);
        exifInterface2.setAttribute("focusPoint", null);
        exifInterface2.setAttribute("depthMapVersion", null);
        exifInterface2.setAttribute("motionPhoto", null);
        exifInterface2.setAttribute("mtType", null);
        exifInterface2.setAttribute("XiaomiComment", null);
        exifInterface2.setAttribute("UserComment", null);
        exifInterface2.setAttribute("Orientation", "1");
        exifInterface2.setAttribute("Xmp", null);
        exifInterface2.setXiaomiMakerNote(exifInterface.getXiaomiMakerNote());
        return exifInterface2;
    }
}
