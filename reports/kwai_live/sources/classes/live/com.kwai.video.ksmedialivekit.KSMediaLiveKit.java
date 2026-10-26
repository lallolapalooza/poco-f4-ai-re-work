package com.kwai.video.ksmedialivekit;

import android.graphics.Bitmap;
import com.kwai.camerasdk.Daenerys;
import com.kwai.video.arya.QosInfo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.Map;

/* JADX INFO: loaded from: classes8.dex */
public interface KSMediaLiveKit {

    @Retention(RetentionPolicy.SOURCE)
    public @interface EventType {
        public static final int LIVE_CHAT_START = 2;
        public static final int LIVE_CHAT_STOP = 3;
        public static final int LIVE_PK_START = 4;
        public static final int LIVE_PK_STOP = 5;
        public static final int NETWORK_NOT_GOOD = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LiveType {
        public static final int NORMAL = 1;
        public static final int VOICE = 2;
    }

    public interface MediaLiveActiveSpeakerChangedListener {
        void onActiveSpeakerChanged(Map<String, Integer> map);
    }

    public interface MediaLiveBgmListener {
        void onCompleted(String str);

        void onError(String str, int i12);

        void onProgress(String str, float f12, float f13);

        void onStart(String str);
    }

    public interface MediaLiveDebugInfoListener {
        void onMessage(String str);
    }

    public interface MediaLiveErrorListener {
        void onError(int i12, String str);
    }

    public interface MediaLiveEventListener {
        void onEvent(int i12, String str);
    }

    public interface MediaLiveMediaDataListener {
        void onVideoDecoded(String str, ByteBuffer byteBuffer, int i12, int i13, int i14, int i15);
    }

    public interface MediaLiveQosInfoListener {
        void onQosInfo(yx.a aVar);
    }

    public interface MediaLiveStatusListener {
        void onStatus(int i12, int i13, String str);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PushStatus {
        public static final int ERROR = 4;
        public static final int INIT = 0;
        public static final int PUSH_STOPPED = 5;
        public static final int PUSH_SUCCESS = 2;
        public static final int READY = 1;
        public static final int RETRYING = 3;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PushType {
        public static final int CDN = 1;
        public static final int ORIGIN = 2;
        public static final int UNKNOWN = 0;
    }

    public interface QosExtraInterface {
        String getQosExtra();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RtcMode {
        public static final int LIVE_CHAT = 1;
        public static final int LIVE_PK = 2;
        public static final int VOICE_CHAT = 3;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface StatusCode {
        public static final int LIVE_STREAM_DISCONNECTED = 4;
        public static final int LIVE_STREAM_DISCONNECT_AND_RETRY_FAIL = 5;
        public static final int LIVE_STREAM_DISCONNECT_REQUEST_NETWORK_FAIL = 6;
        public static final int MISSING_PARAMETER = 3;
        public static final int NETWORK_ERROR = 1;
        public static final int STATUS_NOT_READY = 2;
    }

    void addQosInfoListener(MediaLiveQosInfoListener mediaLiveQosInfoListener);

    void addVideoMixSourceWithPosition(String str, sx.c.b bVar);

    void cleanSoundEffectCache();

    void destroy();

    void disableHeadphoneMonitor();

    void enableHeadphoneMonitor(boolean z12);

    void enableInsertActiveSpeakersInLiveStream(boolean z12);

    int getPushType();

    QosInfo getQosInfo();

    int getRetryCount();

    boolean isSupportHeadphoneMonitor(boolean z12);

    void muteRemoteAudioStream(String str, boolean z12);

    void pause();

    void pauseBgm();

    void playSoundEffect(String str, MediaLiveBgmListener mediaLiveBgmListener);

    void prepareToPush();

    void removeQosInfoListener(MediaLiveQosInfoListener mediaLiveQosInfoListener);

    void replaceVideoWithBitmap(Bitmap bitmap);

    void resume();

    void resumeBgm();

    void seekBgm(int i12);

    void setAudioInputVolume(float f12);

    void setBgmPitch(int i12);

    void setBgmVolume(float f12);

    void setEnableNoiseSuppression(boolean z12);

    void setMediaLiveActiveSpeakerListener(MediaLiveActiveSpeakerChangedListener mediaLiveActiveSpeakerChangedListener);

    void setMediaLiveDebugInfoListener(MediaLiveDebugInfoListener mediaLiveDebugInfoListener);

    void setMediaLiveErrorListener(MediaLiveErrorListener mediaLiveErrorListener);

    void setMediaLiveEventListener(MediaLiveEventListener mediaLiveEventListener);

    void setMediaLiveMediaDataListener(MediaLiveMediaDataListener mediaLiveMediaDataListener);

    void setMediaLiveStatusListener(MediaLiveStatusListener mediaLiveStatusListener);

    void setMirror(boolean z12);

    void setMuteBgm(boolean z12);

    void setMuteMicOfAllOthers(boolean z12);

    void setMuteMicOfOtherOne(String str, boolean z12);

    void setQosExtraInterface(QosExtraInterface qosExtraInterface);

    void setRemoteBgmVolume(float f12);

    void setReverbLevel(int i12);

    void startBgm(String str, boolean z12, MediaLiveBgmListener mediaLiveBgmListener);

    void startPush(Daenerys daenerys);

    void startPush(Daenerys daenerys, int i12);

    void startRtc(byte[] bArr, int i12);

    void stopBgm();

    void stopPush(String str);

    void stopRtc();

    boolean updateBgmIndex(int i12, int i13);

    void updateLiveStreamRtmpUrl(String str);

    void updateWallClockTime(long j12);
}
