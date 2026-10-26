package com.yxcorp.gifshow.live.effect;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.yxcorp.gifshow.api.magicemoji.MagicDownloadPlugin;
import com.yxcorp.gifshow.model.MagicEmoji;
import com.yxcorp.gifshow.model.response.m;
import com.yxcorp.utility.plugin.PluginManager;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.w;

/* JADX INFO: compiled from: LiveMagicEffectDownloader.kt */
/* JADX INFO: loaded from: classes4.dex */
public final class LiveMagicEffectDownloader {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final HashMap<String, MagicEmoji.MagicFace> f42912d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ArrayList<MagicEmoji.MagicFace> f42913a = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private HashMap<String, ArrayList<EffectDownloadListener>> f42915c = new HashMap<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f42914b = new b();

    /* JADX INFO: compiled from: LiveMagicEffectDownloader.kt */
    public interface EffectDownloadListener {
        void onComplete(MagicEmoji.MagicFace magicFace);

        void onFailed(int i12, String str);
    }

    /* JADX INFO: compiled from: LiveMagicEffectDownloader.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(p pVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: LiveMagicEffectDownloader.kt */
    @SuppressLint({"HandlerLeak"})
    public final class b extends Handler {
        public b() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            w.f(msg, "msg");
            if (msg.what == 1) {
                Object obj = msg.obj;
                if (obj instanceof MagicEmoji.MagicFace) {
                    LiveMagicEffectDownloader liveMagicEffectDownloader = LiveMagicEffectDownloader.this;
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type com.yxcorp.gifshow.model.MagicEmoji.MagicFace");
                    liveMagicEffectDownloader.g((MagicEmoji.MagicFace) obj, msg.arg1);
                }
            }
        }
    }

    /* JADX INFO: compiled from: LiveMagicEffectDownloader.kt */
    static final class c<T> implements Consumer<Disposable> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f42917a = new c();

        c() {
        }

        @Override // io.reactivex.functions.Consumer
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(Disposable disposable) {
            com.yxcorp.gifshow.api.init.scheduler.h.DOWNLOAD.scheduleNow();
        }
    }

    /* JADX INFO: compiled from: LiveMagicEffectDownloader.kt */
    static final class d<T> implements Consumer<MagicEmoji.MagicFace> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f42919b;

        d(int i12) {
            this.f42919b = i12;
        }

        @Override // io.reactivex.functions.Consumer
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(MagicEmoji.MagicFace magicFace) {
            nk0.b bVar = nk0.b.f82376d;
            w.e(magicFace, "magicFace");
            bVar.h(magicFace, true);
            LiveMagicEffectDownloader.this.j(magicFace, true, 0, null);
            LiveMagicEffectDownloader.this.k(this.f42919b + 1);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("downloadSerial: download magic success, id:");
            sb2.append(magicFace.mId);
            sb2.append(" name:");
            sb2.append(magicFace.mName);
            sb2.append(" index:");
            sb2.append(this.f42919b);
        }
    }

    /* JADX INFO: compiled from: LiveMagicEffectDownloader.kt */
    static final class e<T> implements Consumer<Throwable> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ MagicEmoji.MagicFace f42921b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f42922c;

        e(MagicEmoji.MagicFace magicFace, int i12) {
            this.f42921b = magicFace;
            this.f42922c = i12;
        }

        @Override // io.reactivex.functions.Consumer
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(Throwable th2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("downloadSerial: download magic failed, id:");
            sb2.append(this.f42921b.mId);
            sb2.append(" name:");
            sb2.append(this.f42921b.mName);
            sb2.append(" index:");
            sb2.append(this.f42922c);
            String stackTraceString = Log.getStackTraceString(th2);
            w.e(stackTraceString, "Log.getStackTraceString(error)");
            LiveMagicEffectDownloader.this.j(this.f42921b, false, 2001, stackTraceString);
        }
    }

    /* JADX INFO: compiled from: LiveMagicEffectDownloader.kt */
    static final class f<T> implements Consumer<Disposable> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final f f42923a = new f();

        f() {
        }

        @Override // io.reactivex.functions.Consumer
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(Disposable disposable) {
            com.yxcorp.gifshow.api.init.scheduler.h.DOWNLOAD.scheduleNow();
        }
    }

    /* JADX INFO: compiled from: LiveMagicEffectDownloader.kt */
    static final class g<T> implements Consumer<MagicEmoji.MagicFace> {
        g() {
        }

        @Override // io.reactivex.functions.Consumer
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(MagicEmoji.MagicFace magicFace) {
            nk0.b bVar = nk0.b.f82376d;
            w.e(magicFace, "magicFace");
            bVar.h(magicFace, true);
            LiveMagicEffectDownloader.this.j(magicFace, true, 0, null);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("downloadSerial: download magic success, id:");
            sb2.append(magicFace.mId);
            sb2.append(" name:");
            sb2.append(magicFace.mName);
        }
    }

    /* JADX INFO: compiled from: LiveMagicEffectDownloader.kt */
    static final class h<T> implements Consumer<Throwable> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ MagicEmoji.MagicFace f42926b;

        h(MagicEmoji.MagicFace magicFace) {
            this.f42926b = magicFace;
        }

        @Override // io.reactivex.functions.Consumer
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(Throwable th2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("downloadSingle: download magic failed, id:");
            sb2.append(this.f42926b.mId);
            sb2.append(" name:");
            sb2.append(this.f42926b.mName);
            String stackTraceString = Log.getStackTraceString(th2);
            w.e(stackTraceString, "Log.getStackTraceString(error)");
            LiveMagicEffectDownloader.this.j(this.f42926b, false, 2001, stackTraceString);
        }
    }

    /* JADX INFO: compiled from: LiveMagicEffectDownloader.kt */
    static final class i<T, R> implements Function<m, MagicEmoji.MagicFace> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f42927a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ mk0.a f42928b;

        i(String str, mk0.a aVar) {
            this.f42927a = str;
            this.f42928b = aVar;
        }

        @Override // io.reactivex.functions.Function
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final MagicEmoji.MagicFace apply(m it2) {
            w.f(it2, "it");
            if (it2.mMagicFace != null) {
                HashMap map = LiveMagicEffectDownloader.f42912d;
                String str = this.f42927a;
                MagicEmoji.MagicFace magicFace = it2.mMagicFace;
                w.d(magicFace);
                w.e(magicFace, "it.mMagicFace!!");
                map.put(str, magicFace);
            } else {
                jk0.f.h(this.f42928b.unique(), 2000, "魔表获取失败, id = " + this.f42927a);
            }
            return it2.mMagicFace;
        }
    }

    static {
        new a(null);
        f42912d = new HashMap<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void j(MagicEmoji.MagicFace magicFace, boolean z12, int i12, String str) {
        if (z12) {
            ArrayList<EffectDownloadListener> arrayList = this.f42915c.get(magicFace.mId);
            if (arrayList != null) {
                Iterator<T> it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    ((EffectDownloadListener) it2.next()).onComplete(magicFace);
                }
            }
        } else {
            ArrayList<EffectDownloadListener> arrayList2 = this.f42915c.get(magicFace.mId);
            if (arrayList2 != null) {
                Iterator<T> it3 = arrayList2.iterator();
                while (it3.hasNext()) {
                    ((EffectDownloadListener) it3.next()).onFailed(i12, str);
                }
            }
        }
        this.f42915c.remove(magicFace.mId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void k(int i12) {
        if (i12 < this.f42913a.size()) {
            Message messageObtain = Message.obtain();
            messageObtain.what = 1;
            messageObtain.arg1 = i12;
            messageObtain.obj = this.f42913a.get(i12);
            Handler handler = this.f42914b;
            if (handler != null) {
                handler.sendMessage(messageObtain);
            }
        }
    }

    public final void d(String id2, EffectDownloadListener listener) {
        w.f(id2, "id");
        w.f(listener, "listener");
        ArrayList<EffectDownloadListener> arrayList = this.f42915c.get(id2);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.f42915c.put(id2, arrayList);
        }
        arrayList.add(listener);
    }

    public final void e() {
        Handler handler = this.f42914b;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        f42912d.clear();
        this.f42913a.clear();
        this.f42915c.clear();
    }

    public final void f(List<? extends MagicEmoji.MagicFace> list) {
        this.f42913a.clear();
        if (list == null || list.isEmpty()) {
            dv0.f.f65054f.k("LiveEffectDownloader", "magicFaces is empty", new Object[0]);
            return;
        }
        for (MagicEmoji.MagicFace magicFace : list) {
            HashMap<String, MagicEmoji.MagicFace> map = f42912d;
            String str = magicFace.mId;
            w.e(str, "material.mId");
            map.put(str, magicFace);
            this.f42913a.add(magicFace);
        }
        dv0.f.f65054f.b("LiveEffectDownloader", "resources: " + list, new Object[0]);
        g(list.get(0), 0);
    }

    public final void g(MagicEmoji.MagicFace material, int i12) {
        w.f(material, "material");
        material.cdnResourceType = 29;
        if (!nk0.b.f82376d.f(material)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("downloadSerial start download, id:");
            sb2.append(material.mId);
            sb2.append(" name:");
            sb2.append(material.mName);
            sb2.append(" index:");
            sb2.append(i12);
            ((MagicDownloadPlugin) PluginManager.get(MagicDownloadPlugin.class)).downloadMagicFace(material).doOnSubscribe(c.f42917a).subscribeOn(in.a.f72159f).observeOn(in.a.f72154a).subscribe(new d(i12), new e(material, i12));
            return;
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append("downloadSerial magic already downloaded, id:");
        sb3.append(material.mId);
        sb3.append(" name:");
        sb3.append(material.mName);
        sb3.append(" index:");
        sb3.append(i12);
        j(material, true, 0, null);
        k(i12 + 1);
    }

    public final void h(MagicEmoji.MagicFace material) {
        w.f(material, "material");
        HashMap<String, MagicEmoji.MagicFace> map = f42912d;
        String str = material.mId;
        w.e(str, "material.mId");
        map.put(str, material);
        material.cdnResourceType = 29;
        if (nk0.b.f82376d.f(material)) {
            j(material, true, 0, null);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("downloadSingle: already download magic, id:");
            sb2.append(material.mId);
            sb2.append(" name:");
            sb2.append(material.mName);
            return;
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append("downloadSingle start download, id:");
        sb3.append(material.mId);
        sb3.append(" name:");
        sb3.append(material.mName);
        ((MagicDownloadPlugin) PluginManager.get(MagicDownloadPlugin.class)).downloadMagicFace(material).doOnSubscribe(f.f42923a).subscribeOn(in.a.f72159f).observeOn(in.a.f72154a).subscribe(new g(), new h(material));
    }

    public final Observable<MagicEmoji.MagicFace> i(String id2, mk0.a task) {
        w.f(id2, "id");
        w.f(task, "task");
        HashMap<String, MagicEmoji.MagicFace> map = f42912d;
        if (map.containsKey(id2)) {
            Observable<MagicEmoji.MagicFace> observableJust = Observable.just(map.get(id2));
            w.e(observableJust, "Observable.just(sMagicFaces[id])");
            return observableJust;
        }
        Observable map2 = ((MagicDownloadPlugin) PluginManager.get(MagicDownloadPlugin.class)).getMagicFaceById(id2).map(new i(id2, task));
        w.e(map2, "PluginManager.get(MagicD…    it.mMagicFace\n      }");
        return map2;
    }
}
