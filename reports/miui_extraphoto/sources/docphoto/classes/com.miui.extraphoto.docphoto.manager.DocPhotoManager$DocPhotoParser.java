package com.miui.extraphoto.docphoto.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.util.Xml;
import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMeta;
import com.miui.camerainfra.exif.ExifInterface;
import com.miui.camerainfra.exif.XmpHelper;
import com.miui.extraphoto.common.storage.Storage;
import com.miui.extraphoto.common.utils.BigBitmapLoadUtils;
import com.miui.extraphoto.common.utils.ExifUtil;
import com.miui.extraphoto.common.utils.FileUtils;
import com.miui.extraphoto.common.utils.IOUtils;
import com.miui.extraphoto.common.utils.ImageUtils;
import com.miui.extraphoto.common.utils.InSampleInfo;
import com.miui.extraphoto.common.utils.MatrixUtils;
import com.miui.extraphoto.common.utils.StaticContext;
import com.miui.extraphoto.common.utils.UriUtils;
import com.miui.extraphoto.docphoto.Util;
import com.miui.extraphoto.docphoto.ui.HandNode;
import com.miui.extraphoto.docphoto.ui.HandRecoverNode;
import com.miui.extraphoto.docphoto.ui.HandRemoveNode;
import com.miui.mediaeditor.bizlibs.storage.utils.EditorFileUtils;
import com.miui.mediaeditor.storage.entrance.XResult;
import com.miui.mediaeditor.storage.entrance.XStorage;
import com.miui.mediaeditor.storage.utils.BaseFileUtils;
import com.miui.mediaeditor.storage.utils.BaseMediaUtils;
import com.miui.mediaeditor.storage.utils.BaseStorageUtils;
import com.xiaomi.ocr.sdk.imgprocess.DocumentProcess$DocumentType;
import com.xiaomi.ocr.sdk.imgprocess.DocumentProcess$EnhanceType;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes.dex */
public class DocPhotoManager extends PhotoManager {
    private boolean mCroped;
    private Bitmap mCurrentCropPreview;
    private boolean mDeHandwrite;
    private boolean mDemoire;
    private DocProcessor mDocProcessor;
    private DocumentProcess$EnhanceType mEnhanceType;
    private Exporter mExporter;
    private List<HandNode> mHandNodeList;
    private DocumentProcess$EnhanceType mInputEnhanceTyle;
    private Bitmap mOriginRecoverBitmap;
    private Parser mParser;
    private float[] mPointsDelegate;
    private int mRotate;
    private Storage mSource;
    private boolean mUndistortion;
    private String mWatermarkText;

    interface Parser {
        float[] delegatePoints();

        Bitmap effectDelegate();

        DocumentProcess$EnhanceType getEnhanceType();

        ExifInterface getOriginExif();

        Matrix getRawScaleOnlyMatrix();

        String getWatermarkText();

        boolean parseBase();

        boolean parseExtension();

        Bitmap photoDelegate();

        Pair<Bitmap, Matrix> raw();

        Bitmap rawDelegate();

        int rawOrientation();
    }

    public DocPhotoManager(Storage storage, boolean z, boolean z2, DocProcessor docProcessor) {
        this(storage, DocumentProcess$EnhanceType.RAW, z, z2, docProcessor, null);
    }

    public DocPhotoManager(Storage storage, DocumentProcess$EnhanceType documentProcess$EnhanceType, boolean z, boolean z2, DocProcessor docProcessor, String str) {
        this.mUndistortion = false;
        this.mDemoire = false;
        this.mDeHandwrite = false;
        this.mCroped = false;
        this.mHandNodeList = new ArrayList();
        this.mSource = storage;
        this.mDocProcessor = docProcessor;
        this.mInputEnhanceTyle = documentProcess$EnhanceType;
        this.mEnhanceType = documentProcess$EnhanceType;
        this.mWatermarkText = str;
        this.mParser = z ? new DocPhotoParser(storage) : new PhotoParser(storage, documentProcess$EnhanceType, docProcessor, str);
        this.mExporter = z2 ? new DocPhotoExporter() : new PhotoExporter();
    }

    public boolean isOriginImage() {
        return (this.mEnhanceType != this.mInputEnhanceTyle || this.mUndistortion || this.mDemoire || this.mDeHandwrite || this.mCroped || this.mRotate != 0) ? false : true;
    }

    public boolean isDoEffect() {
        return (this.mEnhanceType != this.mInputEnhanceTyle || this.mUndistortion || this.mDemoire || this.mDeHandwrite) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Pair<Bitmap, Matrix> render() {
        Pair<Bitmap, Matrix> pairRaw = this.mParser.raw();
        Bitmap bitmapDoEnhance = null;
        if (pairRaw == null || pairRaw.first == null) {
            return null;
        }
        Bitmap bitmapGenerateBitmapWithOrientation = ImageUtils.generateBitmapWithOrientation(this.mDocProcessor.doRotate(this.mDocProcessor.doCrop((Bitmap) pairRaw.first, MatrixUtils.mapPoints(getPointsDelegate(), (Matrix) pairRaw.second), DocumentProcess$DocumentType.DEFAULT), this.mRotate), this.mParser.rawOrientation());
        if (this.mDeHandwrite) {
            Pair<Bitmap, Bitmap> pairDoDeHandwrite = this.mDocProcessor.doDeHandwrite(bitmapGenerateBitmapWithOrientation, bitmapGenerateBitmapWithOrientation.copy(Bitmap.Config.ARGB_8888, true));
            bitmapGenerateBitmapWithOrientation = (Bitmap) pairDoDeHandwrite.first;
            bitmapDoEnhance = (Bitmap) pairDoDeHandwrite.second;
            DocumentProcess$EnhanceType documentProcess$EnhanceType = this.mEnhanceType;
            DocumentProcess$EnhanceType documentProcess$EnhanceType2 = DocumentProcess$EnhanceType.BIN;
            if (documentProcess$EnhanceType == documentProcess$EnhanceType2) {
                bitmapDoEnhance = this.mDocProcessor.doEnhance(bitmapDoEnhance, documentProcess$EnhanceType2, false);
            }
            if (this.mDemoire) {
                bitmapDoEnhance = this.mDocProcessor.doEnhance(bitmapDoEnhance, DocumentProcess$EnhanceType.DEMOIRE, false);
            }
        }
        if (!this.mDeHandwrite && this.mUndistortion) {
            bitmapGenerateBitmapWithOrientation = this.mDocProcessor.doUndistortion(bitmapGenerateBitmapWithOrientation);
        }
        if (!this.mDeHandwrite || this.mEnhanceType != DocumentProcess$EnhanceType.COLOR) {
            bitmapGenerateBitmapWithOrientation = this.mDocProcessor.doEnhance(bitmapGenerateBitmapWithOrientation, this.mEnhanceType, false);
        }
        if (this.mDemoire) {
            bitmapGenerateBitmapWithOrientation = this.mDocProcessor.doEnhance(bitmapGenerateBitmapWithOrientation, DocumentProcess$EnhanceType.DEMOIRE, false);
        }
        if (this.mDeHandwrite && this.mHandNodeList.size() > 0) {
            bitmapGenerateBitmapWithOrientation = this.mDocProcessor.doHandMode(bitmapDoEnhance, bitmapGenerateBitmapWithOrientation, this.mHandNodeList, this.mParser.getRawScaleOnlyMatrix());
        }
        return new Pair<>(this.mDocProcessor.doWatermark(bitmapGenerateBitmapWithOrientation, this.mWatermarkText), (Matrix) pairRaw.second);
    }

    @Override // com.miui.extraphoto.docphoto.manager.PhotoManager
    protected Bitmap renderPreview() {
        Bitmap bitmapDoRotate = this.mDocProcessor.doRotate(this.mDocProcessor.doCrop(getRawBitmapDelegate(), getPointsDelegate(), DocumentProcess$DocumentType.DEFAULT), this.mRotate);
        this.mCurrentCropPreview = bitmapDoRotate.copy(Bitmap.Config.ARGB_8888, true);
        if (this.mDeHandwrite) {
            Bitmap bitmapCopy = bitmapDoRotate.copy(Bitmap.Config.ARGB_8888, true);
            this.mOriginRecoverBitmap = bitmapCopy;
            Pair<Bitmap, Bitmap> pairDoDeHandwrite = this.mDocProcessor.doDeHandwrite(bitmapDoRotate, bitmapCopy);
            Bitmap bitmap = (Bitmap) pairDoDeHandwrite.first;
            Bitmap bitmap2 = (Bitmap) pairDoDeHandwrite.second;
            this.mOriginRecoverBitmap = bitmap2;
            DocumentProcess$EnhanceType documentProcess$EnhanceType = this.mEnhanceType;
            DocumentProcess$EnhanceType documentProcess$EnhanceType2 = DocumentProcess$EnhanceType.BIN;
            if (documentProcess$EnhanceType == documentProcess$EnhanceType2) {
                this.mOriginRecoverBitmap = this.mDocProcessor.doEnhance(bitmap2, documentProcess$EnhanceType2, false);
            }
            if (this.mDemoire) {
                this.mOriginRecoverBitmap = this.mDocProcessor.doEnhance(this.mOriginRecoverBitmap, DocumentProcess$EnhanceType.DEMOIRE, false);
            }
            bitmapDoRotate = bitmap;
        }
        if (!this.mDeHandwrite && this.mUndistortion) {
            bitmapDoRotate = this.mDocProcessor.doUndistortion(bitmapDoRotate);
        }
        if (!this.mDeHandwrite || this.mEnhanceType != DocumentProcess$EnhanceType.COLOR) {
            bitmapDoRotate = this.mDocProcessor.doEnhance(bitmapDoRotate, this.mEnhanceType, false);
        }
        if (this.mDemoire) {
            bitmapDoRotate = this.mDocProcessor.doEnhance(bitmapDoRotate, DocumentProcess$EnhanceType.DEMOIRE, false);
        }
        if (this.mDeHandwrite && this.mHandNodeList.size() > 0) {
            bitmapDoRotate = this.mDocProcessor.doHandMode(this.mOriginRecoverBitmap, bitmapDoRotate, this.mHandNodeList, null);
        }
        this.mCurrentPreview = bitmapDoRotate.copy(Bitmap.Config.ARGB_8888, true);
        return this.mDocProcessor.doWatermark(bitmapDoRotate, this.mWatermarkText);
    }

    public Bitmap getCurrentCropPreview() {
        return this.mCurrentCropPreview;
    }

    public Bitmap getOriginRecoverBitmap() {
        return this.mOriginRecoverBitmap;
    }

    public int getModeColor(Bitmap bitmap) {
        return this.mDocProcessor.getPointsModeColor(bitmap);
    }

    public void setHandNodeList(List<HandNode> list) {
        this.mHandNodeList.addAll(list);
    }

    @Override // com.miui.extraphoto.docphoto.manager.PhotoManager
    protected boolean parseBase() {
        boolean base = this.mParser.parseBase();
        if (base) {
            this.mInputEnhanceTyle = this.mParser.getEnhanceType();
            this.mEnhanceType = this.mParser.getEnhanceType();
            this.mWatermarkText = this.mParser.getWatermarkText();
        }
        return base;
    }

    @Override // com.miui.extraphoto.docphoto.manager.PhotoManager
    protected boolean parseExtension() {
        boolean extension = this.mParser.parseExtension();
        if (extension) {
            this.mPointsDelegate = this.mParser.delegatePoints();
        }
        return extension;
    }

    @Override // com.miui.extraphoto.docphoto.manager.Exporter
    public boolean export(Storage storage) {
        return this.mExporter.export(storage);
    }

    @Override // com.miui.extraphoto.docphoto.manager.Exporter
    public Storage getExportStorage() {
        return this.mExporter.getExportStorage();
    }

    public String getSrcFilePath() {
        return this.mSource.getPath();
    }

    public String getExportFilePath() {
        if (TextUtils.isEmpty(this.mExportFilePath)) {
            this.mExportFilePath = BaseFileUtils.generate(BaseFileUtils.getParentFolderPath(getSrcFilePath()), String.format(Locale.US, "IMG_%s", BaseFileUtils.getTimeStampString()), "jpg");
        }
        return this.mExportFilePath;
    }

    public Bitmap getRawBitmapDelegate() {
        return this.mParser.rawDelegate();
    }

    @Override // com.miui.extraphoto.docphoto.manager.PhotoManager
    protected PhotoManager.CancelableWork<Bitmap> previewWork() {
        return new CancelablePreview();
    }

    @Override // com.miui.extraphoto.docphoto.manager.PhotoManager
    public Uri updateMediaStore() throws Throwable {
        Storage exportStorage = getExportStorage();
        if (UriUtils.isFileUri(exportStorage.getUri())) {
            BaseMediaUtils.triggerScanFile(exportStorage.getPath());
        } else {
            updateImageUri(getExportStorage());
        }
        return getExportStorage().getUri();
    }

    class CancelablePreview implements PhotoManager.CancelableWork<Bitmap> {
        private boolean mIsCancel;

        CancelablePreview() {
        }

        @Override // com.miui.extraphoto.docphoto.manager.PhotoManager.CancelableWork
        public boolean isCancel() {
            return this.mIsCancel;
        }

        @Override // com.miui.extraphoto.docphoto.manager.PhotoManager.CancelableWork
        public void cancel() {
            this.mIsCancel = true;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.miui.extraphoto.docphoto.manager.PhotoManager.CancelableWork
        public Bitmap run() {
            if (this.mIsCancel) {
                return null;
            }
            Bitmap bitmapDoRotate = DocPhotoManager.this.mDocProcessor.doRotate(DocPhotoManager.this.mDocProcessor.doCrop(DocPhotoManager.this.getRawBitmapDelegate(), DocPhotoManager.this.getPointsDelegate(), DocumentProcess$DocumentType.DEFAULT), DocPhotoManager.this.mRotate);
            DocPhotoManager.this.mCurrentCropPreview = bitmapDoRotate.copy(Bitmap.Config.ARGB_8888, true);
            if (this.mIsCancel) {
                return null;
            }
            if (DocPhotoManager.this.mDeHandwrite) {
                DocPhotoManager.this.mOriginRecoverBitmap = bitmapDoRotate.copy(Bitmap.Config.ARGB_8888, true);
                Pair<Bitmap, Bitmap> pairDoDeHandwrite = DocPhotoManager.this.mDocProcessor.doDeHandwrite(bitmapDoRotate, DocPhotoManager.this.mOriginRecoverBitmap);
                Bitmap bitmap = (Bitmap) pairDoDeHandwrite.first;
                DocPhotoManager.this.mOriginRecoverBitmap = (Bitmap) pairDoDeHandwrite.second;
                DocumentProcess$EnhanceType documentProcess$EnhanceType = DocPhotoManager.this.mEnhanceType;
                DocumentProcess$EnhanceType documentProcess$EnhanceType2 = DocumentProcess$EnhanceType.BIN;
                if (documentProcess$EnhanceType == documentProcess$EnhanceType2) {
                    DocPhotoManager docPhotoManager = DocPhotoManager.this;
                    docPhotoManager.mOriginRecoverBitmap = docPhotoManager.mDocProcessor.doEnhance(DocPhotoManager.this.mOriginRecoverBitmap, documentProcess$EnhanceType2, false);
                }
                if (DocPhotoManager.this.mDemoire) {
                    DocPhotoManager docPhotoManager2 = DocPhotoManager.this;
                    docPhotoManager2.mOriginRecoverBitmap = docPhotoManager2.mDocProcessor.doEnhance(DocPhotoManager.this.mOriginRecoverBitmap, DocumentProcess$EnhanceType.DEMOIRE, false);
                }
                bitmapDoRotate = bitmap;
            }
            if (this.mIsCancel) {
                return null;
            }
            if (!DocPhotoManager.this.mDeHandwrite && DocPhotoManager.this.mUndistortion) {
                bitmapDoRotate = DocPhotoManager.this.mDocProcessor.doUndistortion(bitmapDoRotate);
            }
            if (this.mIsCancel) {
                return null;
            }
            if (!DocPhotoManager.this.mDeHandwrite || DocPhotoManager.this.mEnhanceType != DocumentProcess$EnhanceType.COLOR) {
                bitmapDoRotate = DocPhotoManager.this.mDocProcessor.doEnhance(bitmapDoRotate, DocPhotoManager.this.mEnhanceType, false);
            }
            if (this.mIsCancel) {
                return null;
            }
            if (DocPhotoManager.this.mDemoire) {
                bitmapDoRotate = DocPhotoManager.this.mDocProcessor.doEnhance(bitmapDoRotate, DocumentProcess$EnhanceType.DEMOIRE, false);
            }
            if (this.mIsCancel) {
                return null;
            }
            if (DocPhotoManager.this.mDeHandwrite && DocPhotoManager.this.mHandNodeList.size() > 0) {
                bitmapDoRotate = DocPhotoManager.this.mDocProcessor.doHandMode(DocPhotoManager.this.mOriginRecoverBitmap, bitmapDoRotate, DocPhotoManager.this.mHandNodeList, null);
            }
            DocPhotoManager.this.mCurrentPreview = bitmapDoRotate.copy(Bitmap.Config.ARGB_8888, true);
            return DocPhotoManager.this.mDocProcessor.doWatermark(bitmapDoRotate, DocPhotoManager.this.mWatermarkText);
        }
    }

    public float[] getPointsDelegate() {
        return this.mPointsDelegate;
    }

    public void setPointsDelegate(float[] fArr) {
        this.mPointsDelegate = fArr;
    }

    public DocumentProcess$EnhanceType getEnhanceType() {
        DocumentProcess$EnhanceType documentProcess$EnhanceType = this.mEnhanceType;
        return documentProcess$EnhanceType == null ? DocumentProcess$EnhanceType.RAW : documentProcess$EnhanceType;
    }

    public void setEnhanceType(String str) {
        str.hashCode();
        switch (str) {
            case "bin":
                this.mEnhanceType = DocumentProcess$EnhanceType.BIN;
                break;
            case "raw":
                this.mEnhanceType = DocumentProcess$EnhanceType.RAW;
                break;
            case "color":
                this.mEnhanceType = DocumentProcess$EnhanceType.COLOR;
                break;
        }
    }

    public String getEnhanceTypeString() {
        return this.mEnhanceType.toString();
    }

    public Bitmap getPhotoBitmapDelegate() {
        return this.mParser.photoDelegate();
    }

    public Bitmap getEffectDelegate() {
        return this.mParser.effectDelegate();
    }

    static class DocPhotoParser implements Parser {
        private DocPhotoMetaData mDocPhotoMetaData;
        private PhotoMetaData mPhotoMetaData;
        private float[] mPointsDelegate;
        private Bitmap mRawBitmapDelegate;
        private int mRawOrientation;
        private Matrix mRawPhotoDelegateInverseMatrix;
        private Matrix mRawPhotoDelegateInverseMatrixWithoutOrientation;
        private float mScale;
        private long mSeparatePosition;
        private Storage mSource;

        DocPhotoParser(Storage storage) {
            this.mSource = storage;
        }

        @Override // com.miui.extraphoto.docphoto.manager.DocPhotoManager.Parser
        public boolean parseBase() {
            PhotoMetaData photoMetaData = new PhotoMetaData();
            this.mPhotoMetaData = photoMetaData;
            return photoMetaData.extract(this.mSource.getUri()) && parseDocMetaInfo();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v0 */
        /* JADX WARN: Type inference failed for: r3v1 */
        /* JADX WARN: Type inference failed for: r3v14 */
        /* JADX WARN: Type inference failed for: r3v15 */
        /* JADX WARN: Type inference failed for: r3v2, types: [java.io.Closeable] */
        /* JADX WARN: Type inference failed for: r3v4 */
        /* JADX WARN: Type inference failed for: r3v5 */
        @Override // com.miui.extraphoto.docphoto.manager.DocPhotoManager.Parser
        public boolean parseExtension() throws Throwable {
            boolean z = false;
            if (this.mDocPhotoMetaData == null) {
                return false;
            }
            ?? r3 = 0;
            InputStream inputStream = null;
            try {
                try {
                    this.mSeparatePosition = FileUtils.length(this.mSource.getPath()) - this.mDocPhotoMetaData.mRawPhotoLength;
                    InputStream inputStream2 = this.mSource.getInputStream();
                    try {
                        inputStream2.skip(this.mSeparatePosition);
                        this.mRawOrientation = ExifUtil.getOrientation(ExifUtil.getExifInterface(inputStream2));
                        IOUtils.close("DocPhotoManager", inputStream2);
                        InputStream inputStream3 = this.mSource.getInputStream();
                        inputStream3.skip(this.mSeparatePosition);
                        InSampleInfo inSampleInfoComputeInSampleInfo = ImageUtils.computeInSampleInfo(inputStream3);
                        IOUtils.close("DocPhotoManager", inputStream3);
                        inputStream2 = this.mSource.getInputStream();
                        inputStream2.skip(this.mSeparatePosition);
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = inSampleInfoComputeInSampleInfo.inSampleSize;
                        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStream2, null, options);
                        this.mRawBitmapDelegate = bitmapDecodeStream;
                        if (bitmapDecodeStream != null) {
                            this.mRawBitmapDelegate = ImageUtils.generateBitmapWithOrientation(bitmapDecodeStream, this.mRawOrientation);
                            IOUtils.close("DocPhotoManager", inputStream2);
                            Matrix delegateBitmapMatrix = Util.getDelegateBitmapMatrix(inSampleInfoComputeInSampleInfo.outWidth, inSampleInfoComputeInSampleInfo.outHeight, 1.0f / inSampleInfoComputeInSampleInfo.inSampleSize, this.mRawOrientation);
                            Matrix delegateBitmapMatrix2 = Util.getDelegateBitmapMatrix(inSampleInfoComputeInSampleInfo.outWidth, inSampleInfoComputeInSampleInfo.outHeight, 1.0f / inSampleInfoComputeInSampleInfo.inSampleSize, 1);
                            this.mScale = inSampleInfoComputeInSampleInfo.inSampleSize;
                            this.mPointsDelegate = MatrixUtils.mapPoints(this.mDocPhotoMetaData.mCropPoints, delegateBitmapMatrix);
                            this.mRawPhotoDelegateInverseMatrix = MatrixUtils.inverseMatrix(delegateBitmapMatrix);
                            Matrix matrixInverseMatrix = MatrixUtils.inverseMatrix(delegateBitmapMatrix2);
                            this.mRawPhotoDelegateInverseMatrixWithoutOrientation = matrixInverseMatrix;
                            IOUtils.close("DocPhotoManager", inputStream2);
                            z = true;
                            r3 = matrixInverseMatrix;
                        } else {
                            IOUtils.close("DocPhotoManager", inputStream2);
                            return false;
                        }
                    } catch (IOException e) {
                        e = e;
                        inputStream = inputStream2;
                        e.printStackTrace();
                        IOUtils.close("DocPhotoManager", inputStream);
                        r3 = inputStream;
                    } catch (Throwable th) {
                        th = th;
                        r3 = inputStream2;
                        IOUtils.close("DocPhotoManager", r3);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e2) {
                e = e2;
            }
            return z;
        }

        private boolean parseDocMetaInfo() {
            boolean z = false;
            InputStream inputStream = null;
            try {
                try {
                    InputStream inputStream2 = this.mSource.getInputStream();
                    if (inputStream2 == null) {
                        IOUtils.close("DocPhotoManager", inputStream2);
                        return false;
                    }
                    DocPhotoMetaData docPhotoMetaData = new DocPhotoMetaData();
                    this.mDocPhotoMetaData = docPhotoMetaData;
                    docPhotoMetaData.parseExifData(inputStream2);
                    IOUtils.close("DocPhotoManager", inputStream2);
                    if (!this.mDocPhotoMetaData.mIsDocPhoto) {
                        IOUtils.close("DocPhotoManager", inputStream2);
                        return false;
                    }
                    inputStream = this.mSource.getInputStream();
                    String xmpString = getXmpString(inputStream);
                    if (TextUtils.isEmpty(xmpString)) {
                        IOUtils.close("DocPhotoManager", inputStream);
                        return false;
                    }
                    this.mDocPhotoMetaData.parseXmpData(xmpString);
                    IOUtils.close("DocPhotoManager", inputStream);
                    z = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                IOUtils.close("DocPhotoManager", inputStream);
                return z;
            } catch (Throwable th) {
                IOUtils.close("DocPhotoManager", inputStream);
                throw th;
            }
        }

        @Override // com.miui.extraphoto.docphoto.manager.DocPhotoManager.Parser
        public ExifInterface getOriginExif() {
            return this.mPhotoMetaData.mExif;
        }

        @Override // com.miui.extraphoto.docphoto.manager.DocPhotoManager.Parser
        public DocumentProcess$EnhanceType getEnhanceType() {
            return this.mDocPhotoMetaData.mEnhanceType;
        }

        @Override // com.miui.extraphoto.docphoto.manager.DocPhotoManager.Parser
        public String getWatermarkText() {
            return this.mDocPhotoMetaData.mWatermarkText;
        }

        @Override // com.miui.extraphoto.docphoto.manager.DocPhotoManager.Parser
        public Bitmap photoDelegate() {
            return this.mPhotoMetaData.mBitmapDelegate;
        }

        @Override // com.miui.extraphoto.docphoto.manager.DocPhotoManager.Parser
        public Bitmap effectDelegate() {
            return this.mPhotoMetaData.mBitmapDelegate;
        }

        @Override // com.miui.extraphoto.docphoto.manager.DocPhotoManager.Parser
        public Bitmap rawDelegate() {
            return this.mRawBitmapDelegate;
        }

        @Override // com.miui.extraphoto.docphoto.manager.DocPhotoManager.Parser
        public float[] delegatePoints() {
            return this.mPointsDelegate;
        }

        @Override // com.miui.extraphoto.docphoto.manager.DocPhotoManager.Parser
        public Pair<Bitmap, Matrix> raw() throws Throwable {
            InputStream inputStream;
            InputStream inputStream2 = null;
            bitmapDecodeStream = null;
            Bitmap bitmapDecodeStream = null;
            try {
                inputStream = this.mSource.getInputStream();
                if (inputStream != null) {
                    try {
                        try {
                            inputStream.skip(this.mSeparatePosition);
                            bitmapDecodeStream = BitmapFactory.decodeStream(inputStream, null, null);
                        } catch (IOException e) {
                            e = e;
                            e.printStackTrace();
                        }
                    } catch (Throwable th) {
                        th = th;
                        inputStream2 = inputStream;
                        IOUtils.close("DocPhotoManager", inputStream2);
                        throw th;
                    }
                }
            } catch (IOException e2) {
                e = e2;
                inputStream = null;
            } catch (Throwable th2) {
                th = th2;
                IOUtils.close("DocPhotoManager", inputStream2);
                throw th;
            }
            IOUtils.close("DocPhotoManager", inputStream);
            return new Pair<>(bitmapDecodeStream, this.mRawPhotoDelegateInverseMatrix);
        }

        @Override // com.miui.extraphoto.docphoto.manager.DocPhotoManager.Parser
        public int rawOrientation() {
            return this.mRawOrientation;
        }

        @Override // com.miui.extraphoto.docphoto.manager.DocPhotoManager.Parser
        public Matrix getRawScaleOnlyMatrix() {
            return this.mRawPhotoDelegateInverseMatrixWithoutOrientation;
        }

        private String getXmpString(InputStream inputStream) {
            PhotoMetaData photoMetaData = this.mPhotoMetaData;
            if (photoMetaData.mExif == null) {
                photoMetaData.mExif = ExifUtil.getExifInterface(inputStream);
            }
            XMPMeta xMPMeta = this.mPhotoMetaData.mExif.getXMPMeta();
            if (xMPMeta == null) {
                return null;
            }
            try {
                return xMPMeta.getPropertyString("http://ns.xiaomi.com/photos/1.0/camera/", "XMPMeta");
            } catch (XMPException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    static class PhotoParser implements Parser {
        private DocProcessor mDocProcessor;
        private DocumentProcess$EnhanceType mEnhanceType;
        private PhotoMetaData mPhotoMetaData;
        private float[] mPointsDelegate;
        private Matrix mRawScaleReverseMatrix;
        private float mScale;
        private Storage mSource;
        private Bitmap mSubsampledEffectBitmap;
        private String mWatermarkText;

        PhotoParser(Storage storage, DocumentProcess$EnhanceType documentProcess$EnhanceType, DocProcessor docProcessor, String str) {
            this.mEnhanceType = DocumentProcess$EnhanceType.RAW;
            this.mSource = storage;
            this.mDocProcessor = docProcessor;
            this.mEnhanceType = documentProcess$EnhanceType;
            this.mWatermarkText = str;
        }

        @Override // com.miui.extraphoto.docphoto.manager.DocPhotoManager.Parser
        public boolean parseBase() {
            PhotoMetaData photoMetaData = new PhotoMetaData();
            this.mPhotoMetaData = photoMetaData;
            return photoMetaData.extract(this.mSource.getUri());
        }

        @Override // com.miui.extraphoto.docphoto.manager.DocPhotoManager.Parser
        public boolean parseExtension() {
            DocProcessor docProcessor = this.mDocProcessor;
            Bitmap bitmap = this.mPhotoMetaData.mBitmapDelegate;
            DocumentProcess$DocumentType documentProcess$DocumentType = DocumentProcess$DocumentType.DEFAULT;
            float[] fArrDoScan = docProcessor.doScan(bitmap, documentProcess$DocumentType);
            this.mPointsDelegate = fArrDoScan;
            Bitmap bitmapDoCrop = this.mDocProcessor.doCrop(this.mPhotoMetaData.mBitmapDelegate, fArrDoScan, documentProcess$DocumentType);
            this.mSubsampledEffectBitmap = bitmapDoCrop;
            return bitmapDoCrop != null;
        }

        @Override // com.miui.extraphoto.docphoto.manager.DocPhotoManager.Parser
        public ExifInterface getOriginExif() {
            return this.mPhotoMetaData.mExif;
        }

        @Override // com.miui.extraphoto.docphoto.manager.DocPhotoManager.Parser
        public DocumentProcess$EnhanceType getEnhanceType() {
            return this.mEnhanceType;
        }

        @Override // com.miui.extraphoto.docphoto.manager.DocPhotoManager.Parser
        public String getWatermarkText() {
            return this.mWatermarkText;
        }

        @Override // com.miui.extraphoto.docphoto.manager.DocPhotoManager.Parser
        public Bitmap photoDelegate() {
            return this.mPhotoMetaData.mBitmapDelegate;
        }

        @Override // com.miui.extraphoto.docphoto.manager.DocPhotoManager.Parser
        public Bitmap effectDelegate() {
            return this.mSubsampledEffectBitmap;
        }

        @Override // com.miui.extraphoto.docphoto.manager.DocPhotoManager.Parser
        public Bitmap rawDelegate() {
            return this.mPhotoMetaData.mBitmapDelegate;
        }

        @Override // com.miui.extraphoto.docphoto.manager.DocPhotoManager.Parser
        public float[] delegatePoints() {
            return this.mPointsDelegate;
        }

        @Override // com.miui.extraphoto.docphoto.manager.DocPhotoManager.Parser
        public Pair<Bitmap, Matrix> raw() throws Throwable {
            int iCalculateInSampleSize;
            if (this.mPhotoMetaData != null) {
                Context contextSGetAndroidContext = StaticContext.sGetAndroidContext();
                PhotoMetaData photoMetaData = this.mPhotoMetaData;
                iCalculateInSampleSize = BigBitmapLoadUtils.calculateInSampleSize(contextSGetAndroidContext, photoMetaData.mWidth, photoMetaData.mHeight);
            } else {
                iCalculateInSampleSize = 1;
            }
            Bitmap bitmapDecodeBitmapWithSample = DocPhotoUtils.decodeBitmapWithSample(iCalculateInSampleSize, "DocPhotoManager", this.mSource);
            if (bitmapDecodeBitmapWithSample == null) {
                return null;
            }
            int width = bitmapDecodeBitmapWithSample.getWidth();
            int height = bitmapDecodeBitmapWithSample.getHeight();
            float f = iCalculateInSampleSize;
            PhotoMetaData photoMetaData2 = this.mPhotoMetaData;
            Matrix delegateBitmapMatrix = Util.getDelegateBitmapMatrix(width, height, f / photoMetaData2.mDelegateInSampleSize, photoMetaData2.mOrientation);
            Matrix delegateBitmapMatrix2 = Util.getDelegateBitmapMatrix(bitmapDecodeBitmapWithSample.getWidth(), bitmapDecodeBitmapWithSample.getHeight(), f / this.mPhotoMetaData.mDelegateInSampleSize, 1);
            this.mScale = this.mPhotoMetaData.mDelegateInSampleSize / f;
            Matrix matrixInverseMatrix = MatrixUtils.inverseMatrix(delegateBitmapMatrix);
            this.mRawScaleReverseMatrix = MatrixUtils.inverseMatrix(delegateBitmapMatrix2);
            return new Pair<>(bitmapDecodeBitmapWithSample, matrixInverseMatrix);
        }

        @Override // com.miui.extraphoto.docphoto.manager.DocPhotoManager.Parser
        public int rawOrientation() {
            return this.mPhotoMetaData.mOrientation;
        }

        @Override // com.miui.extraphoto.docphoto.manager.DocPhotoManager.Parser
        public Matrix getRawScaleOnlyMatrix() {
            return this.mRawScaleReverseMatrix;
        }
    }

    class PhotoExporter implements Exporter {
        private Storage mExportStorage;

        PhotoExporter() {
        }

        @Override // com.miui.extraphoto.docphoto.manager.Exporter
        public boolean export(Storage storage) throws Throwable {
            Log.d("DocPhotoManager", "PhotoExporter export: " + storage.getUri());
            long jCurrentTimeMillis = System.currentTimeMillis();
            Pair pairRender = DocPhotoManager.this.render();
            if (pairRender == null) {
                return false;
            }
            ExifInterface exifInterfaceDefaultExportExif = DocPhotoUtils.defaultExportExif(DocPhotoManager.this.mParser.getOriginExif());
            exifInterfaceDefaultExportExif.setAttribute("docPhoto", null);
            exifInterfaceDefaultExportExif.setAttribute("ImageWidth", String.valueOf(((Bitmap) pairRender.first).getWidth()));
            exifInterfaceDefaultExportExif.setAttribute("ImageLength", String.valueOf(((Bitmap) pairRender.first).getHeight()));
            exifInterfaceDefaultExportExif.setAttribute("Xmp", null);
            boolean zWriteToFileDirectly = DocPhotoUtils.writeToFileDirectly("DocPhotoManager", DocPhotoUtils.exportJpegWithExif((Bitmap) pairRender.first, exifInterfaceDefaultExportExif), storage.getUri());
            Log.d("DocPhotoManager", String.format("[PhotoExporter] export photo result %b, consume %d", Boolean.valueOf(zWriteToFileDirectly), Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis)));
            return zWriteToFileDirectly;
        }

        @Override // com.miui.extraphoto.docphoto.manager.Exporter
        public Storage getExportStorage() {
            if (this.mExportStorage == null) {
                String strSafeGenerateImagePath = EditorFileUtils.safeGenerateImagePath(BaseStorageUtils.getRelativeFolder(DocPhotoManager.this.mSource.getPath()), "jpg", false);
                Log.d("DocPhotoManager", "PhotoExporter getExportStorage: exportFilePath=" + strSafeGenerateImagePath);
                XResult xResultInsert = XStorage.insert(strSafeGenerateImagePath, "PhotoExporter");
                Log.d("DocPhotoManager", "PhotoExporter getExportStorage: xResult=" + xResultInsert.toString());
                if (xResultInsert.isSuccess()) {
                    this.mExportStorage = new Storage(strSafeGenerateImagePath, xResultInsert.getUri());
                }
            }
            return this.mExportStorage;
        }
    }

    class DocPhotoExporter implements Exporter {
        private Storage mExportStorage;

        DocPhotoExporter() {
        }

        @Override // com.miui.extraphoto.docphoto.manager.Exporter
        public Storage getExportStorage() {
            if (this.mExportStorage == null) {
                String exportFilePath = DocPhotoManager.this.getExportFilePath();
                XResult xResultInsert = XStorage.insert(exportFilePath, "DocPhotoExporter");
                Log.d("DocPhotoManager", "DocPhotoExporter 111 getExportStorage: result=" + xResultInsert.toString());
                this.mExportStorage = new Storage(exportFilePath, xResultInsert.getUri());
            }
            return this.mExportStorage;
        }

        @Override // com.miui.extraphoto.docphoto.manager.Exporter
        public boolean export(Storage storage) throws Throwable {
            byte[] bArrAddXmpData;
            long jCurrentTimeMillis = System.currentTimeMillis();
            Pair pairRender = DocPhotoManager.this.render();
            if (pairRender == null) {
                return false;
            }
            ExifInterface exifInterfaceDefaultExportExif = DocPhotoUtils.defaultExportExif(DocPhotoManager.this.mParser.getOriginExif());
            exifInterfaceDefaultExportExif.setAttributeBytes("docPhoto", new byte[]{1});
            exifInterfaceDefaultExportExif.setAttribute("ImageWidth", String.valueOf(((Bitmap) pairRender.first).getWidth()));
            exifInterfaceDefaultExportExif.setAttribute("ImageLength", String.valueOf(((Bitmap) pairRender.first).getHeight()));
            exifInterfaceDefaultExportExif.setAttribute("Xmp", null);
            byte[] bArrExportJpegWithExif = DocPhotoUtils.exportJpegWithExif((Bitmap) pairRender.first, exifInterfaceDefaultExportExif);
            byte[] bArrRawJpeg = rawJpeg();
            if (bArrRawJpeg == null || (bArrAddXmpData = addXmpData(bArrExportJpegWithExif, bArrRawJpeg.length, MatrixUtils.mapPoints(DocPhotoManager.this.mPointsDelegate, (Matrix) pairRender.second), DocPhotoManager.this.getEnhanceType(), DocPhotoManager.this.mWatermarkText)) == null) {
                return false;
            }
            byte[] bArr = new byte[bArrAddXmpData.length + bArrRawJpeg.length];
            System.arraycopy(bArrAddXmpData, 0, bArr, 0, bArrAddXmpData.length);
            System.arraycopy(bArrRawJpeg, 0, bArr, bArrAddXmpData.length, bArrRawJpeg.length);
            boolean zWriteToFileDirectly = DocPhotoUtils.writeToFileDirectly("DocPhotoManager", bArr, storage.getUri());
            Log.d("DocPhotoManager", String.format("[DocPhoto] export doc photo result %b, consume %d", Boolean.valueOf(zWriteToFileDirectly), Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis)));
            return zWriteToFileDirectly;
        }

        private byte[] rawJpeg() throws Throwable {
            Throwable th;
            InputStream inputStream;
            byte[] inputStreamBytes = null;
            try {
                inputStream = DocPhotoManager.this.mSource.getInputStream();
                try {
                    try {
                        inputStreamBytes = IOUtils.readInputStreamBytes("DocPhotoManager", inputStream);
                    } catch (FileNotFoundException e) {
                        e = e;
                        e.printStackTrace();
                        Log.d("DocPhotoManager", "read raw jpeg data fail");
                    }
                } catch (Throwable th2) {
                    th = th2;
                    IOUtils.close("DocPhotoManager", inputStream);
                    throw th;
                }
            } catch (FileNotFoundException e2) {
                e = e2;
                inputStream = null;
            } catch (Throwable th3) {
                th = th3;
                inputStream = null;
                IOUtils.close("DocPhotoManager", inputStream);
                throw th;
            }
            IOUtils.close("DocPhotoManager", inputStream);
            return inputStreamBytes;
        }

        private byte[] addXmpData(byte[] bArr, long j, float[] fArr, DocumentProcess$EnhanceType documentProcess$EnhanceType, String str) {
            String string;
            byte[] byteArray = null;
            try {
                XmlSerializer xmlSerializerNewSerializer = Xml.newSerializer();
                StringWriter stringWriter = new StringWriter();
                xmlSerializerNewSerializer.setOutput(stringWriter);
                xmlSerializerNewSerializer.startDocument("UTF-8", Boolean.TRUE);
                xmlSerializerNewSerializer.startTag(null, "docPhoto");
                xmlSerializerNewSerializer.attribute(null, "version", String.valueOf(1));
                xmlSerializerNewSerializer.attribute(null, "enhanceType", documentProcess$EnhanceType.toString());
                xmlSerializerNewSerializer.attribute(null, "cropPoints", Util.convertToString(fArr));
                xmlSerializerNewSerializer.attribute(null, "rawLength", String.valueOf(j));
                if (str != null && !str.isEmpty()) {
                    xmlSerializerNewSerializer.attribute(null, "privacyWatermark", str);
                }
                xmlSerializerNewSerializer.endTag(null, "docPhoto");
                xmlSerializerNewSerializer.endDocument();
                string = stringWriter.toString();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("DocPhotoManager", "build xmp string error");
                string = null;
            }
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
                XMPMeta xMPMetaCreateXMPMeta = XmpHelper.createXMPMeta();
                xMPMetaCreateXMPMeta.setProperty("http://ns.xiaomi.com/photos/1.0/camera/", "XMPMeta", string);
                XmpHelper.writeXMPMeta(byteArrayInputStream, byteArrayOutputStream, xMPMetaCreateXMPMeta);
                byteArray = byteArrayOutputStream.toByteArray();
                Log.d("DocPhotoManager", " built xmp data complete");
                return byteArray;
            } catch (XMPException e2) {
                e2.printStackTrace();
                Log.e("DocPhotoManager", "build xmp bytes error");
                return byteArray;
            }
        }
    }

    public void clearHand() {
        this.mHandNodeList.clear();
    }

    public boolean isHanded() {
        return this.mHandNodeList.size() > 0;
    }

    public int getRotate() {
        return this.mRotate;
    }

    public void setRotate(int i) {
        this.mRotate = i;
    }

    public void setCroped(boolean z) {
        if (this.mCroped) {
            return;
        }
        this.mCroped = z;
    }

    public void toggleEffect(String str) {
        str.hashCode();
        switch (str) {
            case "dehandwrite":
                if (this.mDeHandwrite) {
                    this.mHandNodeList.clear();
                }
                this.mDeHandwrite = !this.mDeHandwrite;
                break;
            case "undistortion":
                this.mUndistortion = !this.mUndistortion;
                break;
            case "demoire":
                this.mDemoire = !this.mDemoire;
                break;
        }
    }

    public boolean isEffectOn(String str) {
        str.hashCode();
        switch (str) {
            case "dehandwrite":
                return this.mDeHandwrite;
            case "undistortion":
                return this.mUndistortion;
            case "demoire":
                return this.mDemoire;
            default:
                return false;
        }
    }

    public boolean isCroped() {
        return this.mCroped || this.mRotate != 0;
    }

    public boolean isDeHandwrite() {
        return this.mDeHandwrite;
    }

    public boolean isDemoire() {
        return this.mDemoire;
    }

    public boolean isUndistortion() {
        return this.mUndistortion;
    }

    public int getHandRemoveCount() {
        Iterator<HandNode> it = this.mHandNodeList.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next() instanceof HandRemoveNode) {
                i++;
            }
        }
        return i;
    }

    public int getHandRecoverCount() {
        Iterator<HandNode> it = this.mHandNodeList.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next() instanceof HandRecoverNode) {
                i++;
            }
        }
        return i;
    }
}
