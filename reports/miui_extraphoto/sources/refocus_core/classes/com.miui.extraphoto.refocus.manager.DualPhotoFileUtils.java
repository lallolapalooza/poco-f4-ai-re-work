package com.miui.extraphoto.refocus.manager;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.Log;
import com.miui.camerainfra.exif.ExifInterface;
import com.miui.extraphoto.common.feature.watermark.WaterMarkPainter;
import com.miui.extraphoto.common.storage.Storage;
import com.miui.extraphoto.common.utils.IOUtils;
import com.miui.extraphoto.common.utils.ImageUtils;
import com.miui.extraphoto.refocus.PhotoInfoProvider;
import com.miui.mediaeditor.storage.entrance.XStorage;
import com.miui.mediaeditor.wcg.WcgUtils;
import java.io.BufferedOutputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class DualPhotoFileUtils {
    public static void writeBitmap(Storage storage, Bitmap bitmap, PhotoInfoProvider photoInfoProvider, Resources resources) throws Throwable {
        writeBitmap(storage, bitmap, photoInfoProvider, resources, false);
    }

    public static void writeBitmap(Storage storage, Bitmap bitmap, PhotoInfoProvider photoInfoProvider, Resources resources, boolean z) throws Throwable {
        WaterMarkPainter waterMarkPainter;
        if (photoInfoProvider.isMirror() && !z) {
            bitmap = ImageUtils.generateMirrorBitmap(bitmap, photoInfoProvider.getDegree());
        }
        OutputStream exifWriterStream = null;
        if (z) {
            waterMarkPainter = null;
        } else {
            waterMarkPainter = new WaterMarkPainter(resources, bitmap.getWidth(), bitmap.getHeight(), photoInfoProvider.getExifWidth(), photoInfoProvider.getExifHeight(), photoInfoProvider.getOrientation(), photoInfoProvider.getWaterMarkManager());
            bitmap = waterMarkPainter.canvasDrawWaterMark(bitmap);
        }
        byte[] iccByteArray = bitmap.getColorSpace().isWideGamut() ? WcgUtils.getIccByteArray(resources) : null;
        try {
            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(XStorage.openOutputStreamSafely(storage.getUri(), true));
                try {
                    ExifInterface exifInterface = new ExifInterface();
                    exifInterface.copyAttributes(photoInfoProvider.getOriginExif());
                    exifInterface.setMimeType(4);
                    exifInterface.setThumbnailBytes(null);
                    exifInterface.setAttribute("ImageWidth", String.valueOf(bitmap.getWidth()));
                    exifInterface.setAttribute("ImageLength", String.valueOf(bitmap.getHeight()));
                    exifInterface.setXiaomiMakerNote(photoInfoProvider.getOriginExif().getXiaomiMakerNote());
                    exifInterface.setAttribute("XiaomiComment", null);
                    exifInterface.setAttribute("UserComment", null);
                    exifInterface.setAttribute("focusPoint", null);
                    exifInterface.setAttribute("depthMapVersion", null);
                    exifInterface.setAttribute("Xmp", null);
                    if (z) {
                        exifInterface.setAttribute("Orientation", null);
                    }
                    exifWriterStream = exifInterface.getExifWriterStream(bufferedOutputStream, iccByteArray);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, exifWriterStream);
                    IOUtils.close("DualPhotoFileUtils", exifWriterStream);
                    if (waterMarkPainter == null) {
                        return;
                    }
                } catch (Exception e) {
                    e = e;
                    exifWriterStream = bufferedOutputStream;
                    Log.e("DualPhotoFileUtils", "writeBitmap: FileNotFoundException");
                    e.printStackTrace();
                    IOUtils.close("DualPhotoFileUtils", exifWriterStream);
                    if (waterMarkPainter == null) {
                        return;
                    }
                } catch (Throwable th) {
                    th = th;
                    exifWriterStream = bufferedOutputStream;
                    IOUtils.close("DualPhotoFileUtils", exifWriterStream);
                    if (waterMarkPainter != null) {
                        waterMarkPainter.release();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
            waterMarkPainter.release();
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
