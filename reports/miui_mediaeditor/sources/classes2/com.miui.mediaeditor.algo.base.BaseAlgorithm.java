package com.miui.mediaeditor.algo.base;

import fr.l;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.e;

/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/miui/mediaeditor/algo/base/BaseAlgorithm;", "Lcom/miui/mediaeditor/algo/base/a;", "<init>", "()V", "base_release"}, k = 1, mv = {1, 8, 0})
public abstract class BaseAlgorithm implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap<Class<?>, b<?, ?>> f7326a = new HashMap<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AtomicBoolean f7327b = new AtomicBoolean(false);

    @Override // com.miui.mediaeditor.algo.base.a
    public <M extends BaseResult> M a(BaseParam baseParam) {
        synchronized (this) {
            if (!this.f7327b.getAndSet(true)) {
                h();
            }
        }
        b<?, ?> bVar = this.f7326a.get(baseParam.getClass());
        e.d(bVar, "null cannot be cast to non-null type com.miui.mediaeditor.algo.base.IProcessor<com.miui.mediaeditor.algo.base.BaseParam, com.miui.mediaeditor.algo.base.BaseResult>");
        M m = (M) bVar.a(baseParam);
        e.d(m, "null cannot be cast to non-null type M of com.miui.mediaeditor.algo.base.BaseAlgorithm.process");
        return m;
    }

    @Override // com.miui.mediaeditor.algo.base.a
    public final <M extends BaseResult> void e(BaseParam baseParam, final d<M> dVar) {
        synchronized (this) {
            if (!this.f7327b.getAndSet(true)) {
                h();
            }
        }
        b<?, ?> bVar = this.f7326a.get(baseParam.getClass());
        e.d(bVar, "null cannot be cast to non-null type com.miui.mediaeditor.algo.base.IProcessor<com.miui.mediaeditor.algo.base.BaseParam, com.miui.mediaeditor.algo.base.BaseResult>");
        bVar.b(baseParam, new l<BaseResult, yq.d>() { // from class: com.miui.mediaeditor.algo.base.BaseAlgorithm$processAsync$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // fr.l
            public final yq.d invoke(BaseResult baseResult) {
                BaseResult it = baseResult;
                e.f(it, "it");
                dVar.l(it);
                return yq.d.f18754a;
            }
        });
    }

    public abstract void h();
}
