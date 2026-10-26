package com.miui.voiceassist.mvs.client;

import android.content.Context;
import android.graphics.Bitmap;
import com.miui.voiceassist.mvs.common.MvsAiState;
import com.miui.voiceassist.mvs.common.MvsMsg;
import com.miui.voiceassist.mvs.common.MvsRequest;
import com.miui.voiceassist.mvs.common.MvsResult;
import com.miui.voiceassist.mvs.common.MvsSpeechResult;
import com.miui.voiceassist.mvs.common.card.MvsClickEvent;
import java.util.ArrayList;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class MvsClientManager {
    public static final String ACTION_3RD_LAUNCH = "com.xiaomi.voiceassistant.action_3rd_launch";
    public static final String ACTION_MI_VOICE_SERVICE = "com.miui.voiceassist.mvs.client.ACTION_MI_VOICE_SERVICE";
    public static final String ACTION_REGISTER_MVS = "com.miui.voiceassist.mvs.client.ACTION_RESGISTER_MVS";
    public static final String EXTRA_3RD_FOOTER_ICON = "extra_3rd_footer_icon";
    public static final String EXTRA_3RD_FOOTER_LABEL = "extra_3rd_footer_label";
    public static final String EXTRA_3RD_PROMPT = "extra_3rd_prompt";
    public static final String EXTRA_3RD_QUERIES = "extra_3rd_queries";
    public static final String EXTRA_PKG = "com.miui.voiceassist.mvs.client.EXTRA_PKG";
    public static final String EXTRA_REGISTER_TOKEN = "EXTRA_REGISTER_TOKEN";
    public static final int MVS_VERSION = 1;
    public static final String PERMISSION_MVS_CLIENT = "com.miui.voiceassist.MVS_BROADCAST";
    private static final String TAG = "MvsClientManager";
    public static final String VOICE_ASSIST_START_FROM_KEY = "voice_assist_start_from_key";
    private static MvsClientManager sInstance;
    private a mMvsClientImpl;

    public interface MvsCallback {
        void onAiStateChange(MvsAiState mvsAiState);

        MvsResult onClickEvent(MvsClickEvent mvsClickEvent);

        void onError(int i10, String str);

        JSONObject onGetContext();

        void onInited();

        void onReleased();

        MvsResult onSpeechResult(MvsSpeechResult mvsSpeechResult);
    }

    public MvsClientManager(Context context) {
        this.mMvsClientImpl = new a(context);
    }

    public static MvsClientManager getInstance(Context context) {
        MvsClientManager mvsClientManager;
        synchronized (MvsClientManager.class) {
            if (sInstance == null) {
                sInstance = new MvsClientManager(context);
            }
            mvsClientManager = sInstance;
        }
        return mvsClientManager;
    }

    public void abandonInteract() {
        this.mMvsClientImpl.c();
    }

    public MvsAiState getAiClient() {
        return this.mMvsClientImpl.e();
    }

    public void init() {
        this.mMvsClientImpl.b();
    }

    public void registerMvsServiceCallbackAsync(String str) {
        this.mMvsClientImpl.a(str);
    }

    public void release() {
        this.mMvsClientImpl.d();
    }

    public MvsMsg sendRequest(MvsRequest mvsRequest) {
        return this.mMvsClientImpl.a(mvsRequest);
    }

    public MvsMsg sendResult(MvsResult mvsResult) {
        return this.mMvsClientImpl.a(mvsResult);
    }

    public void setMvsCallback(MvsCallback mvsCallback) {
        this.mMvsClientImpl.a(mvsCallback);
    }

    public void startXiaoAi() {
        this.mMvsClientImpl.a();
    }

    public void startXiaoAi(String str, ArrayList<String> arrayList, Bitmap bitmap, String str2) {
        this.mMvsClientImpl.a(str, arrayList, bitmap, str2);
    }

    public void startXiaoAi(ArrayList<String> arrayList) {
        this.mMvsClientImpl.a(arrayList);
    }

    public void startXiaoAi(ArrayList<String> arrayList, Bitmap bitmap, String str) {
        this.mMvsClientImpl.a(arrayList, bitmap, str);
    }
}
