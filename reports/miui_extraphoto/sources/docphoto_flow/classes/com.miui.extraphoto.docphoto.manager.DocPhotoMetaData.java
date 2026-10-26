package com.miui.extraphoto.docphoto.manager;

import android.text.TextUtils;
import android.util.Xml;
import com.miui.extraphoto.common.utils.ExifUtil;
import com.miui.extraphoto.docphoto.Util;
import com.xiaomi.ocr.sdk.imgprocess.DocumentProcess$EnhanceType;
import java.io.InputStream;
import java.io.StringReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class DocPhotoMetaData {
    float[] mCropPoints;
    DocumentProcess$EnhanceType mEnhanceType;
    boolean mIsDocPhoto;
    long mRawPhotoLength;
    int mVersionCode;
    String mWatermarkText;

    public void parseExifData(InputStream inputStream) {
        byte[] attributeBytes = ExifUtil.getExifInterface(inputStream).getAttributeBytes("docPhoto");
        if ((attributeBytes != null ? attributeBytes[0] : (byte) -1) == 1) {
            this.mIsDocPhoto = true;
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void parseXmpData(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
        try {
            xmlPullParserNewPullParser.setInput(new StringReader(str));
            int eventType = xmlPullParserNewPullParser.getEventType();
            while (eventType != 1) {
                if (eventType == 2) {
                    String name = xmlPullParserNewPullParser.getName();
                    if (!TextUtils.isEmpty(name)) {
                        if (TextUtils.equals(name, "docPhoto")) {
                            int attributeCount = xmlPullParserNewPullParser.getAttributeCount();
                            for (int i = 0; i < attributeCount; i++) {
                                String attributeName = xmlPullParserNewPullParser.getAttributeName(i);
                                byte b = -1;
                                switch (attributeName.hashCode()) {
                                    case -2126241490:
                                        if (attributeName.equals("rawLength")) {
                                            b = 2;
                                        }
                                        break;
                                    case -1475093060:
                                        if (attributeName.equals("privacyWatermark")) {
                                            b = 4;
                                        }
                                        break;
                                    case -1216561400:
                                        if (attributeName.equals("enhanceType")) {
                                            b = 1;
                                        }
                                        break;
                                    case 351608024:
                                        if (attributeName.equals("version")) {
                                            b = 0;
                                        }
                                        break;
                                    case 1024317331:
                                        if (attributeName.equals("cropPoints")) {
                                            b = 3;
                                        }
                                        break;
                                }
                                if (b == 0) {
                                    this.mVersionCode = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(i));
                                } else if (b == 1) {
                                    this.mEnhanceType = Util.convertToEnhanceType(xmlPullParserNewPullParser.getAttributeValue(i));
                                } else if (b == 2) {
                                    this.mRawPhotoLength = Long.parseLong(xmlPullParserNewPullParser.getAttributeValue(i));
                                } else if (b == 3) {
                                    this.mCropPoints = Util.convertToPoints(xmlPullParserNewPullParser.getAttributeValue(i));
                                } else if (b == 4) {
                                    this.mWatermarkText = xmlPullParserNewPullParser.getAttributeValue(i);
                                }
                            }
                        }
                    }
                }
                eventType = xmlPullParserNewPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
