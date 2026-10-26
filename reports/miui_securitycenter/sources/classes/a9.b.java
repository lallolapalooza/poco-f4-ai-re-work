package a9;

import com.miui.analytics.StatManager;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b9.b f378a = new b9.b();

    /* JADX WARN: Code duplicated, block: B:19:0x0045  */
    /* JADX WARN: Code duplicated, block: B:28:0x0051 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:29:? A[LOOP:1: B:17:0x003f->B:29:?, LOOP_END, SYNTHETIC] */
    public Boolean a(String str, String str2) {
        List<b9.a> list;
        Iterator<b9.a> it;
        b9.b bVar = this.f378a;
        List<b9.a> list2 = bVar.f5568a.get(str);
        if (list2 == null || list2.size() == 0) {
            list = bVar.f5568a.get(StatManager.PARAMS_SWITCH_OFF);
            if (list != null && list.size() != 0) {
                it = list.iterator();
                while (it.hasNext()) {
                    if (it.next().a(str2)) {
                    }
                }
            }
            return Boolean.FALSE;
        }
        Iterator<b9.a> it2 = list2.iterator();
        while (it2.hasNext()) {
            if (it2.next().a(str2)) {
            }
        }
        list = bVar.f5568a.get(StatManager.PARAMS_SWITCH_OFF);
        if (list != null) {
            it = list.iterator();
            while (it.hasNext()) {
                if (it.next().a(str2)) {
                }
            }
        }
        return Boolean.FALSE;
        return Boolean.TRUE;
    }
}
