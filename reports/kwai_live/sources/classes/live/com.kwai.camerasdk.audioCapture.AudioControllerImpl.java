package com.kwai.camerasdk.audioCapture;

import android.content.Context;
import android.media.AudioRecord;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import androidx.annotation.Keep;
import com.kwai.camerasdk.audio.AudioFrame;
import com.kwai.camerasdk.log.Log;
import com.kwai.camerasdk.utils.g;
import com.kwai.camerasdk.utils.h;
import com.yxcorp.bugly.CrashReporter;
import java.io.FileOutputStream;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import p000do.f0;

/* JADX INFO: loaded from: classes15.dex */
@Keep
public class AudioControllerImpl extends com.kwai.camerasdk.audioCapture.a {
    private static final int AUDIO_PTS_CHECK_INTERVAL = 3000;
    private static final int AUDIO_PTS_MAX_ALLOWED_DIFF = 500;
    private static final int BITS_PER_SAMPLE = 16;
    private static final int BUFFERS_PER_SECOND = 100;
    private static final int BUFFER_SIZE_FACTOR = 2;
    private static final int CALLBACK_BUFFER_SIZE_MS = 10;
    private static final boolean DEBUG = false;
    private static final String TAG = "AudioControllerImpl";
    private byte[] byteArray;
    private ByteBuffer byteBuffer;
    private int channels;
    private WeakReference<Context> contextWeakReference;
    private final Handler handler;
    private int sampleRate;
    private com.kwai.camerasdk.audioCapture.a.EnumC0344a state;
    private FileOutputStream stream;
    private AudioRecord audioRecord = null;
    private d audioThread = null;
    private com.kwai.camerasdk.audioCapture.a.b stateCallback = null;
    private long lastPts = 0;
    private long lastCheckPts = 0;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (AudioControllerImpl.this.state != com.kwai.camerasdk.audioCapture.a.EnumC0344a.IdleState) {
                return;
            }
            AudioControllerImpl audioControllerImpl = AudioControllerImpl.this;
            if (audioControllerImpl.initRecording(audioControllerImpl.sampleRate, AudioControllerImpl.this.channels) < 0) {
                if (AudioControllerImpl.this.stateCallback != null) {
                    AudioControllerImpl.this.stateCallback.b(f0.AUDIO_MICROPHONE_OPEN_FAILED);
                }
            } else {
                if (AudioControllerImpl.this.startRecording() || AudioControllerImpl.this.stateCallback == null) {
                    return;
                }
                AudioControllerImpl.this.stateCallback.b(f0.AUDIO_MICROPHONE_OPEN_FAILED);
            }
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AudioControllerImpl.this.stopRecording();
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AudioControllerImpl.this.stopRecording();
            AudioControllerImpl.this.handler.getLooper().quit();
        }
    }

    private class d extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private volatile boolean f23798a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private long f23799b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private long f23800c;

        public d(String str) {
            super(str);
            this.f23798a = true;
            this.f23799b = 0L;
            this.f23800c = 0L;
        }

        public void b() {
            this.f23798a = false;
            while (isAlive()) {
                try {
                    join();
                } catch (InterruptedException e12) {
                    CrashReporter.logCheckedException(e12, "AudioControllerImpl$AudioRecordThread.class", "joinThread", -28);
                }
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(-19);
            AudioControllerImpl.this.changeState(com.kwai.camerasdk.audioCapture.a.EnumC0344a.CapturingState);
            Log.d(AudioControllerImpl.TAG, "AudioRecordThread" + g.e());
            if (AudioControllerImpl.this.audioRecord.getRecordingState() != 3) {
                Log.e(AudioControllerImpl.TAG, "Wrong recording state.");
            }
            h.a();
            while (this.f23798a) {
                int i12 = AudioControllerImpl.this.audioRecord.read(AudioControllerImpl.this.byteBuffer, AudioControllerImpl.this.byteBuffer.capacity());
                if (i12 > 0) {
                    if (this.f23799b == 0) {
                        long jA = h.a();
                        this.f23799b = jA;
                        AudioControllerImpl.this.lastCheckPts = jA;
                    }
                    this.f23800c += (long) (i12 / 2);
                }
                if (i12 == AudioControllerImpl.this.byteBuffer.capacity()) {
                    long j12 = this.f23799b + ((this.f23800c * 1000) / ((long) AudioControllerImpl.this.sampleRate));
                    AudioControllerImpl.this.byteBuffer.get(AudioControllerImpl.this.byteArray);
                    AudioControllerImpl.this.byteBuffer.position(0);
                    if (AudioControllerImpl.this.lastPts >= j12) {
                        Log.e(AudioControllerImpl.TAG, "AudioControllerImpl error lastPts(" + AudioControllerImpl.this.lastPts + ") >= ts(" + j12 + ")");
                    } else {
                        AudioControllerImpl.this.lastPts = j12;
                        AudioControllerImpl.this.publishMediaFrame(new AudioFrame(AudioControllerImpl.this.byteArray, AudioControllerImpl.this.channels, AudioControllerImpl.this.sampleRate, j12));
                    }
                    if (j12 - AudioControllerImpl.this.lastCheckPts >= 3000) {
                        long jA2 = h.a();
                        AudioControllerImpl.this.lastCheckPts = j12;
                        if (j12 < jA2 && jA2 - j12 > 500) {
                            this.f23799b = 0L;
                            this.f23800c = 0L;
                            Log.e(AudioControllerImpl.TAG, "RecordThread cpu time (" + jA2 + ") lager then audio pts (" + j12 + ") so much !!! maybe audio flinger buffer overflow, reset audio pts");
                        }
                    }
                } else {
                    Log.e(AudioControllerImpl.TAG, "AudioRecord.read failed: " + i12);
                    if (i12 == -3) {
                        if (AudioControllerImpl.this.stateCallback != null) {
                            AudioControllerImpl.this.stateCallback.b(f0.AUDIO_DEVICE_READ_ERROR);
                        }
                        this.f23798a = false;
                    }
                }
            }
            AudioControllerImpl.this.changeState(com.kwai.camerasdk.audioCapture.a.EnumC0344a.IdleState);
            try {
                AudioControllerImpl.this.audioRecord.stop();
            } catch (IllegalStateException e12) {
                CrashReporter.logCheckedException(e12, "AudioControllerImpl$AudioRecordThread.class", "run", -38);
                Log.e(AudioControllerImpl.TAG, "AudioRecord.stop failed: " + e12.getMessage());
            }
        }
    }

    public AudioControllerImpl(@e.a Context context, int i12, int i13) {
        this.sampleRate = 48000;
        this.channels = 1;
        HandlerThread handlerThread = new HandlerThread("AudioThread");
        handlerThread.start();
        this.handler = new Handler(handlerThread.getLooper());
        this.contextWeakReference = new WeakReference<>(context);
        this.sampleRate = i12;
        this.channels = i13;
        this.state = com.kwai.camerasdk.audioCapture.a.EnumC0344a.IdleState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeState(com.kwai.camerasdk.audioCapture.a.EnumC0344a enumC0344a) {
        com.kwai.camerasdk.audioCapture.a.EnumC0344a enumC0344a2;
        com.kwai.camerasdk.audioCapture.a.b bVar = this.stateCallback;
        if (bVar != null && (enumC0344a2 = this.state) != enumC0344a) {
            bVar.a(enumC0344a, enumC0344a2);
        }
        this.state = enumC0344a;
    }

    private int channelCountToConfiguration(int i12) {
        return i12 == 1 ? 16 : 12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int initRecording(int i12, int i13) {
        Log.d(TAG, "initRecording(sampleRate=" + i12 + ", channels=" + i13);
        this.sampleRate = i12;
        WeakReference<Context> weakReference = this.contextWeakReference;
        Context context = weakReference != null ? weakReference.get() : null;
        if (context == null) {
            Log.e(TAG, "Context is null");
            return -1;
        }
        if (!com.kwai.camerasdk.utils.c.a(context, "android.permission.RECORD_AUDIO")) {
            Log.e(TAG, "RECORD_AUDIO permission is missing");
            return -1;
        }
        if (this.audioRecord != null) {
            Log.e(TAG, "InitRecording() called twice without StopRecording()");
            return -1;
        }
        int iChannelCountToConfiguration = channelCountToConfiguration(i13);
        int i14 = i12 / 100;
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i13 * 2 * i14);
        this.byteBuffer = byteBufferAllocateDirect;
        this.byteArray = new byte[byteBufferAllocateDirect.capacity()];
        Log.d(TAG, "byteBuffer.capacity: " + this.byteBuffer.capacity());
        int minBufferSize = AudioRecord.getMinBufferSize(i12, iChannelCountToConfiguration, 2);
        if (minBufferSize == -1 || minBufferSize == -2) {
            Log.e(TAG, "AudioRecord.getMinBufferSize failed: " + minBufferSize);
            return -1;
        }
        Log.d(TAG, "AudioRecord.getMinBufferSize: " + minBufferSize);
        int iMax = Math.max(minBufferSize * 2, this.byteBuffer.capacity());
        Log.d(TAG, "bufferSizeInBytes: " + iMax);
        try {
            this.audioRecord = new AudioRecord(0, i12, iChannelCountToConfiguration, 2, iMax);
            Log.d(TAG, "AudioRecord.getAudioSource: " + this.audioRecord.getAudioSource());
            AudioRecord audioRecord = this.audioRecord;
            if (audioRecord == null || audioRecord.getState() != 1) {
                Log.e(TAG, "Failed to create a new AudioRecord instance");
                return -1;
            }
            Log.d(TAG, "AudioRecord audio format: " + this.audioRecord.getAudioFormat() + ", channels: " + this.audioRecord.getChannelCount() + ", sample rate: " + this.audioRecord.getSampleRate());
            return i14;
        } catch (IllegalArgumentException e12) {
            CrashReporter.logCheckedException(e12, "AudioControllerImpl.class", "initRecording", 36);
            Log.e(TAG, e12.getMessage());
            return -1;
        }
    }

    private boolean isRecording() {
        d dVar = this.audioThread;
        if (dVar != null) {
            return dVar.f23798a;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean startRecording() {
        Log.d(TAG, "startRecording");
        AudioRecord audioRecord = this.audioRecord;
        if (audioRecord == null) {
            return false;
        }
        try {
            audioRecord.startRecording();
            if (this.audioRecord.getRecordingState() != 3) {
                Log.e(TAG, "AudioRecord.startRecording failed");
                return false;
            }
            d dVar = new d("AudioRecordJavaThread");
            this.audioThread = dVar;
            dVar.start();
            return true;
        } catch (IllegalStateException e12) {
            CrashReporter.logCheckedException(e12, "AudioControllerImpl.class", "startRecording", 72);
            Log.e(TAG, "AudioRecord.startRecording failed: " + e12.getMessage());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean stopRecording() {
        Log.d(TAG, "stopRecording");
        d dVar = this.audioThread;
        if (dVar != null) {
            dVar.b();
            this.audioThread = null;
        }
        AudioRecord audioRecord = this.audioRecord;
        if (audioRecord == null) {
            return true;
        }
        audioRecord.release();
        this.audioRecord = null;
        return true;
    }

    @Override // com.kwai.camerasdk.audioCapture.a
    public void dispose() {
        Log.i(TAG, "dispose");
        this.handler.post(new c());
    }

    @Override // com.kwai.camerasdk.audioCapture.a
    public void setStateCallback(@e.a com.kwai.camerasdk.audioCapture.a.b bVar) {
        this.stateCallback = bVar;
    }

    @Override // com.kwai.camerasdk.audioCapture.a
    public void startCapture() {
        Log.i(TAG, "startCapture");
        this.handler.post(new a());
    }

    @Override // com.kwai.camerasdk.audioCapture.a
    public void stopCapture() {
        Log.i(TAG, "startCapture");
        this.handler.post(new b());
    }
}
