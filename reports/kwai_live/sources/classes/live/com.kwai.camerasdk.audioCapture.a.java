package com.kwai.camerasdk.audioCapture;

import co.c;
import p000do.f0;

/* JADX INFO: compiled from: AudioController.java */
/* JADX INFO: loaded from: classes15.dex */
public abstract class a extends c {

    /* JADX INFO: renamed from: com.kwai.camerasdk.audioCapture.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AudioController.java */
    public enum EnumC0344a {
        IdleState,
        CapturingState
    }

    /* JADX INFO: compiled from: AudioController.java */
    public interface b {
        void a(EnumC0344a enumC0344a, EnumC0344a enumC0344a2);

        void b(f0 f0Var);
    }

    public abstract void dispose();

    public abstract void setStateCallback(@e.a b bVar);

    public abstract void startCapture();

    public abstract void stopCapture();
}
