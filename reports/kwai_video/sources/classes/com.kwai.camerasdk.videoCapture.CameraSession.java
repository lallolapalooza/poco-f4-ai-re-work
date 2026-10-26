package com.kwai.camerasdk.videoCapture;

import com.kwai.camerasdk.monitor.FrameMonitor;
import com.kwai.camerasdk.video.VideoFrame;
import com.kwai.camerasdk.videoCapture.cameras.ZoomController;
import p000do.f0;
import p000do.m;
import p000do.n;
import p000do.x;
import p000do.y;

/* JADX INFO: loaded from: classes15.dex */
public interface CameraSession {

    public interface CameraDataListener {
        void onReportCameraFunctionFailed(f0 f0Var, int i12);

        void onVideoFrameCaptured(@e.a CameraSession cameraSession, @e.a VideoFrame videoFrame);
    }

    public interface a {
        void a(long j12, long j13);

        void b(@e.a c cVar, f0 f0Var, Exception exc);

        void c();

        void d(@e.a CameraSession cameraSession);

        void e(long j12);
    }

    public interface b {
        void a(int i12, int i13);
    }

    public enum c {
        ERROR,
        DISCONNECTED
    }

    com.kwai.camerasdk.utils.f C();

    float D();

    void E(com.kwai.camerasdk.utils.f fVar);

    boolean F();

    void H(boolean z12);

    void J(int i12, int i13, int i14);

    com.kwai.camerasdk.utils.f K();

    @e.a
    com.kwai.camerasdk.videoCapture.cameras.c L();

    @e.a
    com.kwai.camerasdk.videoCapture.cameras.a O();

    void P(b bVar);

    float Q();

    boolean a();

    void c(m mVar, x xVar, boolean z12);

    void d(boolean z12);

    int f();

    void g(FrameMonitor frameMonitor);

    com.kwai.camerasdk.utils.f[] h();

    boolean isFrontCamera();

    void j(boolean z12);

    int k();

    boolean l(int i12, int i13);

    void m(boolean z12);

    com.kwai.camerasdk.utils.f[] n();

    void o(CameraController.e eVar, boolean z12);

    @e.a
    ZoomController p();

    com.kwai.camerasdk.utils.f[] q();

    void r(n nVar);

    y s();

    void stop();

    void t(int i12, int i13, boolean z12);

    com.kwai.camerasdk.utils.f u();

    void v(boolean z12);

    n w();

    void x(long j12, int i12);

    boolean y(int i12, int i13);

    boolean z();
}
