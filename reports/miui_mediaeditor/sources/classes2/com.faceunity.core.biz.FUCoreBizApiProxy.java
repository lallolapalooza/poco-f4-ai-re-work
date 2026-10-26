package com.faceunity.core.biz;

import a6.b;
import android.util.Log;
import c2.a;
import com.faceunity.core.biz.impl.IFUCoreBeautyImpl;
import com.faceunity.core.biz.impl.IFUCoreEditorImpl;
import com.faceunity.core.biz.impl.IFUCoreVideoImpl;
import yq.c;

/* JADX INFO: loaded from: classes.dex */
public final class FUCoreBizApiProxy extends a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f1257c = "FUCoreBiz";
    public final c d = kotlin.a.a(new fr.a<IFUCoreBeautyImpl>() { // from class: com.faceunity.core.biz.FUCoreBizApiProxy$beauty$2
        @Override // fr.a
        public final IFUCoreBeautyImpl invoke() {
            return new IFUCoreBeautyImpl();
        }
    });

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final c f1258e = kotlin.a.a(new fr.a<IFUCoreEditorImpl>() { // from class: com.faceunity.core.biz.FUCoreBizApiProxy$editor$2
        @Override // fr.a
        public final IFUCoreEditorImpl invoke() {
            return new IFUCoreEditorImpl();
        }
    });
    public final c f = kotlin.a.a(new fr.a<IFUCoreVideoImpl>() { // from class: com.faceunity.core.biz.FUCoreBizApiProxy$video$2
        @Override // fr.a
        public final IFUCoreVideoImpl invoke() {
            return new IFUCoreVideoImpl();
        }
    });

    public final d2.a a(int i10) {
        Log.d(this.f1257c, b.f("getFUCoreBizApi: bizType: ", i10));
        c cVar = this.d;
        if (i10 == 0) {
            return (IFUCoreBeautyImpl) cVar.getValue();
        }
        if (i10 != 1) {
            return i10 != 2 ? (IFUCoreBeautyImpl) cVar.getValue() : (IFUCoreVideoImpl) this.f.getValue();
        }
        return (IFUCoreEditorImpl) this.f1258e.getValue();
    }
}
