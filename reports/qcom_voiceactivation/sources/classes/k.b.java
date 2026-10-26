package p010k;

import com.quicinc.voice.activation.configuration.v4.ModelConfigurationKey;
import com.quicinc.voice.activation.soundmodel.BigSoundModel;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import p003d.g;
import p017r.h;
import v.j;

/* JADX INFO: compiled from: ClientGroup.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f829a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map f830b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public a f831c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Map f832d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public UUID f833e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public h f834f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f835g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f836h;

    public b(String str) {
        Objects.requireNonNull(str, "clientGroupName should not be null");
        this.f829a = str;
        this.f830b = new HashMap();
        this.f832d = new HashMap();
        this.f836h = g.f().g(str);
    }

    public synchronized void a(BigSoundModel bigSoundModel) {
        this.f832d.put(bigSoundModel.getSoundModelKey().f(), bigSoundModel);
    }

    public synchronized void b(h hVar) {
        this.f834f = hVar;
        this.f833e = e(this.f836h, this.f829a, hVar.c(), this.f834f.f(), this.f835g);
    }

    public synchronized boolean c(String str) {
        if (!this.f830b.containsKey(str)) {
            return false;
        }
        this.f830b.remove(str);
        return true;
    }

    public void d(PrintWriter printWriter) {
        int i2 = 0;
        if (g.f().p(this.f829a)) {
            printWriter.println("\tClientGroup: [" + this.f833e + "]");
            h hVar = this.f834f;
            String strF = hVar == null ? null : hVar.f();
            h hVar2 = this.f834f;
            String strC = hVar2 != null ? hVar2.c() : null;
            h hVar3 = this.f834f;
            printWriter.println("\t\tkeyword=" + this.f836h + ",groupName=" + this.f829a + ",containUv=" + (hVar3 != null && hVar3.g()) + ",userid=" + strF + ",ssFileName=" + strC + ",locale=" + this.f835g);
        } else {
            printWriter.println("\tClientGroup:");
        }
        a aVar = this.f831c;
        printWriter.println("\tPreferred Client: " + (aVar == null ? "null" : aVar.b()));
        printWriter.println("\tRegisterClients: ");
        for (a aVar2 : m()) {
            printWriter.print("\t\t#" + i2 + " ");
            aVar2.a(printWriter);
            i2++;
        }
        for (BigSoundModel bigSoundModel : this.f832d.values()) {
            printWriter.println("\tSound Model Info:");
            printWriter.println("\t\tversion=" + bigSoundModel.getVersion().d() + ", keyword=" + bigSoundModel.getKeyword() + ", userId=" + bigSoundModel.getUserId() + ", locale=" + bigSoundModel.getLocale());
            printWriter.println("\t\tthreshold=" + p(bigSoundModel, ModelConfigurationKey.f442b) + ", " + p(bigSoundModel, ModelConfigurationKey.f444d) + ", " + p(bigSoundModel, ModelConfigurationKey.f445e) + ", " + bigSoundModel.getSoundModelConfiguration().getFloat(ModelConfigurationKey.f446f.b()));
            boolean zD = g.v().d(bigSoundModel.getSoundModelKey());
            StringBuilder sb = new StringBuilder();
            sb.append("\t\tstate=");
            sb.append(zD ? "active" : "deactive");
            printWriter.println(sb.toString());
        }
    }

    public final UUID e(String str, String str2, String str3, String str4, String str5) {
        return UUID.nameUUIDFromBytes((str + "-" + str2 + "-" + str3 + "-" + str4 + "-" + str5).getBytes());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.f829a, ((b) obj).f829a);
    }

    public synchronized BigSoundModel f() {
        return (BigSoundModel) this.f832d.values().stream().findFirst().orElse(null);
    }

    public synchronized BigSoundModel g(UUID uuid) {
        return (BigSoundModel) this.f832d.get(uuid);
    }

    public synchronized BigSoundModel h(j jVar) {
        return (BigSoundModel) this.f832d.get(jVar.f());
    }

    public int hashCode() {
        return Objects.hash(this.f829a);
    }

    public synchronized Map i() {
        return Collections.unmodifiableMap(this.f832d);
    }

    public String j() {
        return this.f829a;
    }

    public synchronized String k() {
        return this.f835g;
    }

    public synchronized a l() {
        return this.f831c;
    }

    public synchronized List m() {
        return new ArrayList(this.f830b.values());
    }

    public synchronized h n() {
        return this.f834f;
    }

    public synchronized UUID o() {
        return this.f833e;
    }

    public final int p(BigSoundModel bigSoundModel, ModelConfigurationKey modelConfigurationKey) {
        return bigSoundModel.getSoundModelConfiguration().getInt(modelConfigurationKey.b());
    }

    public synchronized boolean q(String str) {
        return this.f830b.containsKey(str);
    }

    public synchronized boolean r() {
        return this.f830b.isEmpty();
    }

    public boolean s() {
        h hVar = this.f834f;
        return (hVar == null || (hVar.d() == null && this.f834f.e() == null)) ? false : true;
    }

    public synchronized boolean t(String str, String str2, boolean z2) {
        if (this.f830b.containsKey(str)) {
            return false;
        }
        this.f830b.put(str, new a(str, str2, z2));
        return true;
    }

    public void u() {
        h hVar = this.f834f;
        if (hVar != null) {
            hVar.h();
        }
    }

    public synchronized void v(BigSoundModel bigSoundModel) {
        this.f832d.remove(bigSoundModel.getSoundModelKey().f());
    }

    public synchronized void w(j jVar) {
        this.f832d.remove(jVar.f());
    }

    public synchronized void x() {
        this.f834f = null;
    }

    public synchronized void y(String str) {
        this.f835g = str;
    }

    public synchronized boolean z(String str) {
        if (!this.f830b.containsKey(str)) {
            return false;
        }
        this.f831c = (a) this.f830b.get(str);
        return true;
    }
}
