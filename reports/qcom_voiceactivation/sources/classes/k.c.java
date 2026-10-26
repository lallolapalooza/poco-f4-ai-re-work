package p010k;

import com.quicinc.voice.activation.soundmodel.BigSoundModel;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import v.j;

/* JADX INFO: compiled from: ClientGroupManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map f837a = new HashMap();

    public synchronized void a(b bVar) {
        this.f837a.put(bVar.j(), bVar);
    }

    public void b(PrintWriter printWriter) {
        printWriter.println("Group List:");
        int i2 = 0;
        for (b bVar : f().values()) {
            printWriter.println("\t#" + i2 + " " + bVar.j());
            bVar.d(printWriter);
            i2++;
        }
    }

    public synchronized BigSoundModel c(String str) {
        b bVar = (b) this.f837a.get(str);
        if (bVar == null) {
            return null;
        }
        return bVar.f();
    }

    public synchronized Map d(String str) {
        b bVar = (b) this.f837a.get(str);
        if (bVar == null) {
            return null;
        }
        return bVar.i();
    }

    public synchronized b e(String str) {
        return (b) this.f837a.get(str);
    }

    public synchronized Map f() {
        return Collections.unmodifiableMap(this.f837a);
    }

    public synchronized b g(String str) {
        for (Map.Entry entry : this.f837a.entrySet()) {
            if (((b) entry.getValue()).q(str)) {
                return (b) entry.getValue();
            }
        }
        return null;
    }

    public synchronized a h(String str) {
        b bVar = (b) this.f837a.get(str);
        if (bVar == null) {
            return null;
        }
        return bVar.l();
    }

    public synchronized void i(String str, j jVar) {
        b bVar = (b) this.f837a.get(str);
        if (bVar != null) {
            bVar.w(jVar);
        }
    }

    public synchronized void j(String str) {
        this.f837a.remove(str);
    }
}
