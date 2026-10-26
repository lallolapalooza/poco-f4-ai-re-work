package com.kwai.kve;

import java.util.Map;
import org.json.JSONException;
import org.json.JSONStringer;

/* JADX INFO: loaded from: classes16.dex */
public class SmartEditResult {
    private Map<MediaAsset, MediaAnalyzeResult> mAnalyzeResult;
    private MediaAnalyzeResult mCoverAnalyzeResult;

    @e.a
    private ErrorInfo mErrorInfo;
    private String mMusicId;
    private String mMusicStyle;
    private String mThemeId;

    SmartEditResult(@e.a ErrorInfo errorInfo) {
        this.mErrorInfo = errorInfo;
    }

    public String getBgmType() {
        return this.mMusicStyle;
    }

    public MediaAnalyzeResult getCoverAnalyzeResult() {
        return this.mCoverAnalyzeResult;
    }

    public ErrorInfo getErrorInfo() {
        return this.mErrorInfo;
    }

    public Map<MediaAsset, MediaAnalyzeResult> getMediaAnalyzeResults() {
        return this.mAnalyzeResult;
    }

    public String getMusicId() {
        return this.mMusicId;
    }

    public String getThemeId() {
        return this.mThemeId;
    }

    public JSONStringer toJson(JSONStringer jSONStringer) throws JSONException {
        if (jSONStringer == null) {
            jSONStringer = new JSONStringer();
        }
        jSONStringer.object();
        jSONStringer.key("errorCode");
        jSONStringer.value(getErrorInfo().getErrorCode().toString());
        if (getErrorInfo().getErrorCode() == ErrorInfo.ErrorCode.OK) {
            jSONStringer.key("features").object();
            for (Map.Entry<MediaAsset, MediaAnalyzeResult> entry : this.mAnalyzeResult.entrySet()) {
                String fileName = entry.getKey().getFileName();
                MediaAnalyzeResult value = entry.getValue();
                jSONStringer.key(fileName);
                value.toJson(jSONStringer);
            }
            jSONStringer.endObject();
            jSONStringer.key("musicStyle").value(this.mMusicStyle);
            jSONStringer.key("musicId").value(this.mMusicId);
            jSONStringer.key("themeId").value(this.mThemeId);
            jSONStringer.key("coverFeature");
            MediaAnalyzeResult mediaAnalyzeResult = this.mCoverAnalyzeResult;
            if (mediaAnalyzeResult != null) {
                mediaAnalyzeResult.toJson(jSONStringer);
            } else {
                jSONStringer.value((Object) null);
            }
        }
        jSONStringer.endObject();
        return jSONStringer;
    }

    SmartEditResult(@e.a ErrorInfo errorInfo, Map<MediaAsset, MediaAnalyzeResult> map, MediaAnalyzeResult mediaAnalyzeResult, String str, String str2, String str3) {
        this.mErrorInfo = errorInfo;
        this.mAnalyzeResult = map;
        this.mCoverAnalyzeResult = mediaAnalyzeResult;
        this.mMusicStyle = str;
        this.mThemeId = str2;
        this.mMusicId = str3;
    }
}
