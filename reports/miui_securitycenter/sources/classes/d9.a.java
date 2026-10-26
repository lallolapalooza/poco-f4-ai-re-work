package d9;

import a9.b;
import a9.c;
import android.util.Log;
import com.miui.analytics.StatManager;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static volatile a f44130f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b f44131a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c9.a f44132b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c f44133c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a9.a f44134d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f44135e;

    public a(String str) {
        StringBuilder sb2;
        if (!str.endsWith("/")) {
            str = str + "/";
        }
        this.f44131a = new b();
        try {
            this.f44132b = new c9.a(str);
            this.f44133c = new c(str);
            this.f44134d = new a9.a(str);
            this.f44135e = true;
        } catch (IOException e10) {
            e = e10;
            sb2 = new StringBuilder();
            sb2.append("SmsRecognizer init error:");
            sb2.append(Log.getStackTraceString(e));
            Log.e("SmsRecognizer", sb2.toString());
            this.f44135e = false;
        } catch (RuntimeException e11) {
            e = e11;
            sb2 = new StringBuilder();
            sb2.append("SmsRecognizer init error:");
            sb2.append(Log.getStackTraceString(e));
            Log.e("SmsRecognizer", sb2.toString());
            this.f44135e = false;
        }
    }

    public static synchronized a b(String str) {
        if (f44130f == null) {
            synchronized (a.class) {
                if (f44130f == null) {
                    f44130f = new a(str);
                }
            }
        }
        return f44130f;
    }

    public synchronized void a() {
        org.tensorflow.lite.b bVar;
        org.tensorflow.lite.b bVar2;
        Map<String, Float> map;
        b9.b bVar3;
        Map<String, List<b9.a>> map2;
        this.f44135e = false;
        if (f44130f != null) {
            f44130f = null;
        }
        b bVar4 = this.f44131a;
        if (bVar4 != null && (bVar3 = bVar4.f378a) != null && (map2 = bVar3.f5568a) != null) {
            map2.clear();
        }
        c9.a aVar = this.f44132b;
        if (aVar != null && (map = aVar.f5910c) != null) {
            map.clear();
        }
        c cVar = this.f44133c;
        if (cVar != null && (bVar2 = cVar.f381c) != null) {
            bVar2.close();
        }
        a9.a aVar2 = this.f44134d;
        if (aVar2 != null && (bVar = aVar2.f377c) != null) {
            bVar.close();
        }
    }

    public boolean c(String str, String str2) {
        if (!this.f44135e || str2 == null || str2.equals("") || !z8.a.f57388a.matcher(str2).find()) {
            return false;
        }
        if (this.f44131a.a(str, str2).booleanValue()) {
            return true;
        }
        return this.f44134d.a(this.f44132b.a(str2));
    }

    public boolean d(String str) {
        if (!this.f44135e || str == null || str.equals("") || z8.a.f57389b.matcher(str).matches() || z8.a.f57390c.matcher(str).matches() || this.f44131a.a(StatManager.PARAMS_SWITCH_OFF, str).booleanValue()) {
            return false;
        }
        float[][] fArrA = this.f44132b.a(str);
        if (this.f44134d.a(fArrA)) {
            return false;
        }
        return this.f44133c.a(fArrA);
    }
}
