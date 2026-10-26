package com.kwai.camerasdk.encoder;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.Surface;
import androidx.annotation.Keep;
import com.kwai.camerasdk.log.Log;
import com.kwai.camerasdk.utils.g;
import com.kwai.camerasdk.video.VideoFrame;
import com.kwai.video.player.KsMediaMeta;
import com.yxcorp.bugly.CrashReporter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes15.dex */
@Keep
@TargetApi(18)
public class MediaCodecEncoder {
    private static final int H264_BITRATE_MODE_CBR = 2;
    private static final int H264_BITRATE_MODE_VBR = 1;
    private static final String H264_MIME_TYPE = "video/avc";
    private static final String H265_MIME_TYPE = "video/hevc";
    private static final String TAG = "MediaCodecEncoder";
    public static boolean enableColorSpaceSetting = false;
    private ByteBuffer configData = null;
    private boolean enableH265;
    private int height;
    private int inputBufferIndex;
    private ByteBuffer[] inputBuffers;
    private boolean inputBuffersIsDirect;
    private Surface inputSurface;
    private MediaCodec mediaCodec;
    private Thread mediaCodecThread;
    private ByteBuffer[] ouputBuffers;
    private ByteBuffer[] outputBuffers;
    private int profile;
    private int targetBitrateBps;
    private int targetFps;
    private int width;

    @Keep
    static class OutputBufferInfo {
        public final ByteBuffer buffer;
        public final int index;
        public final boolean isKeyFrame;
        public final long timestamp;

        public OutputBufferInfo(int i12, ByteBuffer byteBuffer, boolean z12, long j12) {
            this.index = i12;
            this.buffer = byteBuffer;
            this.isKeyFrame = z12;
            this.timestamp = TimeUnit.NANOSECONDS.toMicros(j12);
        }
    }

    class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ CountDownLatch f23881a;

        a(CountDownLatch countDownLatch) {
            this.f23881a = countDownLatch;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                MediaCodecEncoder.this.mediaCodec.stop();
                MediaCodecEncoder.this.mediaCodec.release();
            } catch (Exception e12) {
                CrashReporter.logCheckedException(e12, "MediaCodecEncoder$1.class", "run", 13);
                Log.e(MediaCodecEncoder.TAG, "Media codec stop failed.", e12);
            }
            this.f23881a.countDown();
        }
    }

    private enum b {
        BASELINE(0),
        MAIN(1),
        HIGH(2);

        private final int value;

        b(int i12) {
            this.value = i12;
        }

        public int getValue() {
            return this.value;
        }
    }

    public MediaCodecEncoder(boolean z12) {
        this.enableH265 = z12;
    }

    private void forceKeyFrame() {
        if (Build.VERSION.SDK_INT < 19) {
            Log.e(TAG, "forceKeyFrame not supported.");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("request-sync", 0);
        this.mediaCodec.setParameters(bundle);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0095  */
    private boolean initEncode(int i12, int i13, int i14, int i15, int i16, int i17, boolean z12) {
        int i18;
        int i19;
        MediaCodecInfo.EncoderCapabilities encoderCapabilities;
        Log.d(TAG, "Java Init Encode Profile: " + i12 + " : " + i14 + "x" + i15 + " bitrate " + i16 + " fps " + i17 + " enableH265 " + this.enableH265);
        if (this.mediaCodecThread != null) {
            release();
        }
        this.profile = i12;
        this.width = i14;
        this.height = i15;
        this.targetBitrateBps = i16 * 1000;
        this.targetFps = i17;
        this.mediaCodecThread = Thread.currentThread();
        try {
            String str = H264_MIME_TYPE;
            if (this.enableH265) {
                str = H265_MIME_TYPE;
            }
            MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(str, i14, i15);
            mediaFormatCreateVideoFormat.setInteger(KsMediaMeta.KSM_KEY_BITRATE, this.targetBitrateBps);
            if (z12) {
                mediaFormatCreateVideoFormat.setInteger("color-format", 21);
            } else {
                mediaFormatCreateVideoFormat.setInteger("color-format", 2130708361);
            }
            mediaFormatCreateVideoFormat.setInteger("frame-rate", this.targetFps);
            mediaFormatCreateVideoFormat.setInteger("i-frame-interval", i13);
            if (this.enableH265 || i12 == b.BASELINE.getValue()) {
                i18 = 1;
            } else if (i12 == b.MAIN.getValue()) {
                i18 = 2;
            } else if (i12 == b.HIGH.getValue()) {
                i18 = 8;
            } else {
                i18 = 1;
            }
            mediaFormatCreateVideoFormat.setInteger(rr0.b.NEXT_PAGE_PROFILE, i18);
            if (this.enableH265) {
                mediaFormatCreateVideoFormat.setInteger("level", 1);
            } else {
                mediaFormatCreateVideoFormat.setInteger("level", 1);
            }
            MediaCodec mediaCodecCreateEncoderByType = MediaCodec.createEncoderByType(str);
            this.mediaCodec = mediaCodecCreateEncoderByType;
            if (mediaCodecCreateEncoderByType == null) {
                Log.e(TAG, "Cannot create media encoder, mine type = " + str);
                release();
                return false;
            }
            int i22 = Build.VERSION.SDK_INT;
            if (i22 < 21 || (encoderCapabilities = mediaCodecCreateEncoderByType.getCodecInfo().getCapabilitiesForType(str).getEncoderCapabilities()) == null || !encoderCapabilities.isBitrateModeSupported(1)) {
                i19 = 2;
            } else {
                Log.d(TAG, "[MediaCodecEncoder] Use Vbr Bitrate Mode.");
                i19 = 1;
            }
            mediaFormatCreateVideoFormat.setInteger("bitrate-mode", i19);
            if (enableColorSpaceSetting && i22 >= 24) {
                Log.d(TAG, "[MediaCodecEncoder] set KEY_COLOR_RANGE to COLOR_RANGE_FULL.");
                mediaFormatCreateVideoFormat.setInteger("color-range", 1);
                Log.d(TAG, "[MediaCodecEncoder] set KEY_COLOR_STANDARD to COLOR_STANDARD_BT601_PAL.");
                mediaFormatCreateVideoFormat.setInteger("color-standard", 2);
            }
            this.mediaCodec.configure(mediaFormatCreateVideoFormat, (Surface) null, (MediaCrypto) null, 1);
            if (!z12) {
                this.inputSurface = this.mediaCodec.createInputSurface();
            }
            this.mediaCodec.start();
            this.outputBuffers = this.mediaCodec.getOutputBuffers();
            if (z12) {
                ByteBuffer[] inputBuffers = this.mediaCodec.getInputBuffers();
                this.inputBuffers = inputBuffers;
                this.inputBuffersIsDirect = inputBuffers != null && inputBuffers.length > 0;
                for (ByteBuffer byteBuffer : inputBuffers) {
                    if (!byteBuffer.isDirect()) {
                        this.inputBuffersIsDirect = false;
                        break;
                    }
                }
            }
            return true;
        } catch (IOException e12) {
            CrashReporter.logCheckedException(e12, "MediaCodecEncoder.class", "initEncode", -77);
            Log.e(TAG, "Create media codec error " + e12);
            release();
            return false;
        } catch (IllegalArgumentException e13) {
            CrashReporter.logCheckedException(e13, "MediaCodecEncoder.class", "initEncode", -73);
            Log.e(TAG, "Create media codec error " + e13);
            release();
            return false;
        } catch (IllegalStateException e14) {
            CrashReporter.logCheckedException(e14, "MediaCodecEncoder.class", "initEncode", -81);
            Log.e(TAG, "Create media codec error " + e14);
            release();
            return false;
        }
    }

    private void release() {
        Log.d(TAG, "Java release encoder");
        boolean z12 = false;
        if (this.mediaCodec != null) {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            new Thread(new a(countDownLatch), "mediaCodec release").start();
            if (!g.b(countDownLatch, 5000L)) {
                Log.e(TAG, "Media codec release timeout");
                z12 = true;
            }
            this.mediaCodec = null;
        }
        this.mediaCodecThread = null;
        Surface surface = this.inputSurface;
        if (surface != null) {
            surface.release();
            this.inputSurface = null;
        }
        if (z12) {
            Log.e(TAG, "Media codec release timeout!!!!!!");
        }
    }

    private boolean requestEncode(boolean z12) {
        if (!z12) {
            return true;
        }
        try {
            forceKeyFrame();
            return true;
        } catch (RuntimeException e12) {
            CrashReporter.logCheckedException(e12, "MediaCodecEncoder.class", "requestEncode", -16);
            Log.e(TAG, "requestEncodeFromNative failed", e12);
            return false;
        }
    }

    private void setBitrate(int i12) {
        this.targetBitrateBps = i12 * 1000;
        if (Build.VERSION.SDK_INT < 19 || this.mediaCodec == null) {
            Log.e(TAG, "Adjust Bitrate not supported.");
            return;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("video-bitrate", this.targetBitrateBps);
            this.mediaCodec.setParameters(bundle);
        } catch (IllegalStateException e12) {
            CrashReporter.logCheckedException(e12, "MediaCodecEncoder.class", "setBitrate", 44);
            Log.e(TAG, "MediaCodec set bitrate failed", e12);
        }
    }

    public ByteBuffer dequeueInputBuffer() {
        if (this.inputBuffers == null) {
            return null;
        }
        int iDequeueInputBuffer = this.mediaCodec.dequeueInputBuffer(30000L);
        this.inputBufferIndex = iDequeueInputBuffer;
        if (iDequeueInputBuffer >= 0) {
            return this.inputBuffers[iDequeueInputBuffer];
        }
        return null;
    }

    OutputBufferInfo dequeueOutputBuffer(int i12) {
        try {
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            long j12 = i12;
            int iDequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(bufferInfo, j12);
            if (iDequeueOutputBuffer >= 0) {
                if ((bufferInfo.flags & 2) != 0) {
                    this.configData = ByteBuffer.allocateDirect(bufferInfo.size);
                    this.outputBuffers[iDequeueOutputBuffer].position(bufferInfo.offset);
                    this.outputBuffers[iDequeueOutputBuffer].limit(bufferInfo.offset + bufferInfo.size);
                    this.configData.put(this.outputBuffers[iDequeueOutputBuffer]);
                    this.mediaCodec.releaseOutputBuffer(iDequeueOutputBuffer, false);
                    iDequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(bufferInfo, j12);
                }
            }
            if (iDequeueOutputBuffer < 0) {
                if (iDequeueOutputBuffer == -3) {
                    this.outputBuffers = this.mediaCodec.getOutputBuffers();
                    return dequeueOutputBuffer(i12);
                }
                if (iDequeueOutputBuffer == -2) {
                    return dequeueOutputBuffer(i12);
                }
                if (iDequeueOutputBuffer == -1) {
                    return null;
                }
                throw new RuntimeException("dequeue output buffer : " + iDequeueOutputBuffer);
            }
            ByteBuffer byteBufferDuplicate = this.outputBuffers[iDequeueOutputBuffer].duplicate();
            byteBufferDuplicate.position(bufferInfo.offset);
            byteBufferDuplicate.limit(bufferInfo.offset + bufferInfo.size);
            if (bufferInfo.offset <= byteBufferDuplicate.capacity() && bufferInfo.size > 0) {
                boolean z12 = (bufferInfo.flags & 1) != 0;
                if (!z12) {
                    return new OutputBufferInfo(iDequeueOutputBuffer, byteBufferDuplicate.slice(), z12, bufferInfo.presentationTimeUs);
                }
                ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(this.configData.capacity() + bufferInfo.size);
                this.configData.rewind();
                byteBufferAllocateDirect.put(this.configData);
                byteBufferAllocateDirect.put(byteBufferDuplicate);
                byteBufferAllocateDirect.position(0);
                return new OutputBufferInfo(iDequeueOutputBuffer, byteBufferAllocateDirect, z12, bufferInfo.presentationTimeUs);
            }
            Log.e(TAG, "dequeueOutputBuffer error : info.offset = " + bufferInfo.offset + " outputBuffer.capacity() = " + byteBufferDuplicate.capacity() + " info.size = " + bufferInfo.size);
            return new OutputBufferInfo(-2, null, false, -1L);
        } catch (IllegalStateException e12) {
            CrashReporter.logCheckedException(e12, "MediaCodecEncoder.class", "dequeueOutputBuffer", 117);
            Log.e(TAG, "dequeue output buffer failed", e12);
            return new OutputBufferInfo(-1, null, false, -1L);
        }
    }

    public void queueEncodeFrame(VideoFrame videoFrame) {
        if (this.inputBuffers == null) {
            return;
        }
        byte[] byteArray = videoFrame.data.getByteArray();
        long j12 = videoFrame.timestamp * 1000;
        int iDequeueInputBuffer = this.mediaCodec.dequeueInputBuffer(30000L);
        this.inputBufferIndex = iDequeueInputBuffer;
        if (iDequeueInputBuffer >= 0) {
            ByteBuffer byteBuffer = this.inputBuffers[iDequeueInputBuffer];
            byteBuffer.clear();
            byteBuffer.put(byteArray);
            this.mediaCodec.queueInputBuffer(this.inputBufferIndex, 0, byteArray.length, j12, 0);
            return;
        }
        Log.e(TAG, "setInputBufferData error inIndex = " + this.inputBufferIndex);
    }

    public void queueInputBuffer(int i12, long j12) {
        this.mediaCodec.queueInputBuffer(this.inputBufferIndex, 0, i12, j12 * 1000, 0);
    }

    boolean releaseOutputBuffer(int i12) {
        try {
            this.mediaCodec.releaseOutputBuffer(i12, false);
            return true;
        } catch (IllegalStateException e12) {
            CrashReporter.logCheckedException(e12, "MediaCodecEncoder.class", "releaseOutputBuffer", -128);
            return false;
        }
    }
}
