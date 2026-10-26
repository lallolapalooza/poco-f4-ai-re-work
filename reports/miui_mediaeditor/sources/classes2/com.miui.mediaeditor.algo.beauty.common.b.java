package com.miui.mediaeditor.algo.beauty.common;

import com.faceunity.core.enumeration.ProcessMode;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ProcessMode f7371a;

    public static final class a extends b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final a f7372b = new a();

        public a() {
            super(ProcessMode.FU_IMAGE_BEAUTY_MODE_AUTO);
        }
    }

    /* JADX INFO: renamed from: com.miui.mediaeditor.algo.beauty.common.b$b, reason: collision with other inner class name */
    public static final class C0098b extends b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final C0098b f7373b = new C0098b();

        public C0098b() {
            super(ProcessMode.FU_IMAGE_BEAUTY_MODE_BODY_SLIM);
        }
    }

    public static final class c extends b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final c f7374b = new c();

        public c() {
            super(ProcessMode.FU_IMAGE_BEAUTY_MODE_FACE_BEAUTY);
        }
    }

    public static final class d extends b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final d f7375b = new d();

        public d() {
            super(ProcessMode.FU_IMAGE_BEAUTY_MODE_FACE_WARP);
        }
    }

    public static final class e extends b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final e f7376b = new e();

        public e() {
            super(ProcessMode.FU_IMAGE_BEAUTY_MODE_FACE_BEAUTY);
        }
    }

    public b(ProcessMode processMode) {
        this.f7371a = processMode;
    }
}
