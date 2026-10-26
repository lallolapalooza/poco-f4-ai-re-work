package com.kwai.camerasdk.render;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import androidx.annotation.Keep;
import com.kwai.camerasdk.mediarecorder.CaptureOneVideoFrameListener;
import com.kwai.camerasdk.video.VideoFrame;
import com.yxcorp.bugly.CrashReporter;
import eo.e;
import p000do.c0;

/* JADX INFO: loaded from: classes15.dex */
@Keep
public class NativeRenderThread implements e {
    private static final String TAG = "NativeRenderThread";
    private final Object handleLock = new Object();
    private final long nativeDisplayContext;
    private long nativeHandle;

    public NativeRenderThread(long j12) {
        this.nativeHandle = j12;
        nativeBindRenderThread(j12);
        this.nativeDisplayContext = nativeCreateDisplayContext();
    }

    private native void nativeBindDisplayContext(long j12, long j13, Object obj);

    private native void nativeBindRenderThread(long j12);

    private native void nativeCaptureVideoFrame(long j12, CaptureOneVideoFrameListener captureOneVideoFrameListener);

    private native long nativeCreateDisplayContext();

    private native void nativeDestroyDisplayContext(long j12);

    private native void nativeDrawFrame(long j12, VideoFrame videoFrame);

    private native void nativeDrawLastFrame(long j12);

    private native void nativeEnableSaveLastFrame(long j12);

    private native void nativeResizeView(long j12, int i12, int i13);

    private native void nativeSetBackgroundColor(long j12, float f12, float f13, float f14, float f15);

    private native void nativeSetDisplayEnabled(long j12, boolean z12);

    private native void nativeSetDisplayLayout(long j12, int i12);

    private native void nativeSetGlBlendEnabled(long j12, boolean z12);

    private native void nativeSetOnNextFrameRenderedCallback(long j12, Runnable runnable);

    private native void nativeSetRenderThreadListener(long j12, RenderThreadListener renderThreadListener);

    private native void nativeUnbindDisplayContext(long j12, boolean z12);

    @Override // eo.e
    public void captureVideoFrame(CaptureOneVideoFrameListener captureOneVideoFrameListener) {
        synchronized (this.handleLock) {
            try {
                long j12 = this.nativeHandle;
                if (j12 != 0 && captureOneVideoFrameListener != null) {
                    nativeCaptureVideoFrame(j12, captureOneVideoFrameListener);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "NativeRenderThread.class", "captureVideoFrame", -1);
                throw th2;
            }
        }
    }

    @Override // eo.e
    public void createEglSurface(Surface surface) {
        synchronized (this.handleLock) {
            try {
                long j12 = this.nativeHandle;
                if (j12 != 0) {
                    nativeBindDisplayContext(j12, this.nativeDisplayContext, surface);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "NativeRenderThread.class", "createEglSurface", -1);
                throw th2;
            }
        }
    }

    @Override // eo.e
    public void createEglSurfaceTexture(SurfaceTexture surfaceTexture) {
        synchronized (this.handleLock) {
            try {
                long j12 = this.nativeHandle;
                if (j12 != 0) {
                    nativeBindDisplayContext(j12, this.nativeDisplayContext, surfaceTexture);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "NativeRenderThread.class", "createEglSurfaceTexture", -1);
                throw th2;
            }
        }
    }

    @Override // eo.e
    public void drawLastFrame() {
        synchronized (this.handleLock) {
            try {
                long j12 = this.nativeHandle;
                if (j12 != 0) {
                    nativeDrawLastFrame(j12);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "NativeRenderThread.class", "drawLastFrame", -1);
                throw th2;
            }
        }
    }

    @Override // eo.e
    public void enableSaveLastFrame() {
        synchronized (this.handleLock) {
            try {
                long j12 = this.nativeHandle;
                if (j12 != 0) {
                    nativeEnableSaveLastFrame(j12);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "NativeRenderThread.class", "enableSaveLastFrame", -1);
                throw th2;
            }
        }
    }

    @Override // eo.e
    public void onFrameAvailable(VideoFrame videoFrame) {
        synchronized (this.handleLock) {
            try {
                long j12 = this.nativeHandle;
                if (j12 != 0) {
                    nativeDrawFrame(j12, videoFrame);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "NativeRenderThread.class", "onFrameAvailable", -1);
                throw th2;
            }
        }
    }

    @Override // eo.e
    public void release() {
        synchronized (this.handleLock) {
            try {
                long j12 = this.nativeHandle;
                if (j12 != 0) {
                    nativeSetRenderThreadListener(j12, null);
                    nativeUnbindDisplayContext(this.nativeHandle, true);
                    nativeDestroyDisplayContext(this.nativeDisplayContext);
                }
                this.nativeHandle = 0L;
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "NativeRenderThread.class", "release", -1);
                throw th2;
            }
        }
    }

    @Override // eo.e
    public void releaseEglSurface() {
        synchronized (this.handleLock) {
            try {
                long j12 = this.nativeHandle;
                if (j12 != 0) {
                    nativeUnbindDisplayContext(j12, false);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "NativeRenderThread.class", "releaseEglSurface", -1);
                throw th2;
            }
        }
    }

    @Override // eo.e
    public void resize(int i12, int i13) {
        synchronized (this.handleLock) {
            try {
                if (this.nativeHandle != 0) {
                    nativeResizeView(this.nativeDisplayContext, i12, i13);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "NativeRenderThread.class", "resize", -1);
                throw th2;
            }
        }
    }

    @Override // eo.e
    public void setBackgroundColor(float f12, float f13, float f14, float f15) {
        synchronized (this.handleLock) {
            try {
                if (this.nativeHandle != 0) {
                    nativeSetBackgroundColor(this.nativeDisplayContext, f12, f13, f14, f15);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "NativeRenderThread.class", "setBackgroundColor", -1);
                throw th2;
            }
        }
    }

    @Override // eo.e
    public void setDisplayEnabled(boolean z12) {
        synchronized (this.handleLock) {
            try {
                long j12 = this.nativeHandle;
                if (j12 != 0) {
                    nativeSetDisplayEnabled(j12, z12);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "NativeRenderThread.class", "setDisplayEnabled", -1);
                throw th2;
            }
        }
    }

    @Override // eo.e
    public void setDisplayLayout(c0 c0Var) {
        synchronized (this.handleLock) {
            try {
                long j12 = this.nativeHandle;
                if (j12 != 0) {
                    nativeSetDisplayLayout(j12, c0Var.getNumber());
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "NativeRenderThread.class", "setDisplayLayout", -1);
                throw th2;
            }
        }
    }

    @Override // eo.e
    public void setGlBlendEnabled(boolean z12) {
        synchronized (this.handleLock) {
            try {
                long j12 = this.nativeHandle;
                if (j12 != 0) {
                    nativeSetGlBlendEnabled(j12, z12);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "NativeRenderThread.class", "setGlBlendEnabled", -1);
                throw th2;
            }
        }
    }

    @Override // eo.e
    public void setOnNextFrameRenderedCallback(Runnable runnable) {
        synchronized (this.handleLock) {
            try {
                long j12 = this.nativeHandle;
                if (j12 != 0) {
                    nativeSetOnNextFrameRenderedCallback(j12, runnable);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "NativeRenderThread.class", "setOnNextFrameRenderedCallback", -1);
                throw th2;
            }
        }
    }

    @Override // eo.e
    public void setRenderThreadListener(RenderThreadListener renderThreadListener) {
        synchronized (this.handleLock) {
            try {
                long j12 = this.nativeHandle;
                if (j12 != 0) {
                    nativeSetRenderThreadListener(j12, renderThreadListener);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "NativeRenderThread.class", "setRenderThreadListener", -1);
                throw th2;
            }
        }
    }
}
