package com.quicinc.voice.activation.configuration.onevoice;

import G.q;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: OneVoiceModelManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e f392a = new e();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f393b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f394c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList f395d;

    public d(p012m.a aVar) {
        ArrayList arrayListN = n();
        this.f394c = arrayListN;
        this.f393b = m();
        this.f395d = l();
        b(arrayListN, aVar);
        q(aVar);
        q.e(toString());
    }

    public final void a(ArrayList arrayList, O.e eVar) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            for (O.e eVar2 : eVar.h()) {
                if (bVar.b().equals(eVar2.k()) && bVar.a() == null) {
                    bVar.c(eVar2.g());
                }
            }
            if (bVar.a() == null) {
                it.remove();
            }
        }
    }

    public final void b(ArrayList arrayList, p012m.a aVar) {
        O.e eVarD = aVar.d();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            c cVar = (c) it.next();
            O.e eVarO = eVarD.o(cVar.d(), null);
            if (eVarO != null) {
                a(cVar.c(), eVarO);
                a(cVar.e(), eVarO);
            }
        }
    }

    public String c(String str, String str2) {
        c cVarI = i(str);
        if (cVarI == null) {
            return null;
        }
        for (b bVar : cVarI.c()) {
            if (TextUtils.equals(bVar.a(), str2)) {
                return bVar.b();
            }
        }
        return null;
    }

    public String d() {
        return this.f392a.j();
    }

    public String e(String str) {
        for (KwGroupEnum kwGroupEnum : this.f395d) {
            if (kwGroupEnum.b().equals(str)) {
                String strD = kwGroupEnum.d();
                q.e("getGroupByKeyword " + str + ",return " + strD);
                return strD;
            }
        }
        return null;
    }

    public String f(String str) {
        for (KwGroupEnum kwGroupEnum : this.f395d) {
            if (kwGroupEnum.d().equals(str)) {
                String strC = kwGroupEnum.c();
                q.e("getGroupKeyword " + str + ",return " + strC);
                return strC;
            }
        }
        return null;
    }

    public int g(String str) {
        for (KwGroupEnum kwGroupEnum : this.f395d) {
            if (kwGroupEnum.d().equals(str)) {
                int iE = kwGroupEnum.e();
                q.e("getIndexOfClientGroup " + str + ",return " + iE);
                return iE;
            }
        }
        return -1;
    }

    public String h(String str) {
        for (KwGroupEnum kwGroupEnum : this.f395d) {
            if (kwGroupEnum.d().equals(str)) {
                String strB = kwGroupEnum.b();
                q.e("getKeywordByGroup " + str + ",return " + strB);
                return strB;
            }
        }
        return null;
    }

    public final c i(String str) {
        for (c cVar : this.f394c) {
            if (cVar.d().equals(str)) {
                return cVar;
            }
        }
        return null;
    }

    public String j(String str, String str2) {
        c cVarI = i(str);
        if (cVarI == null) {
            return null;
        }
        for (b bVar : cVarI.e()) {
            if (TextUtils.equals(bVar.a(), str2)) {
                return bVar.b();
            }
        }
        return null;
    }

    public String k(String str) {
        c cVarI = i(str);
        if (cVarI != null) {
            return cVarI.f().b();
        }
        return null;
    }

    public final ArrayList l() {
        return KwGroupEnum.f(this.f393b);
    }

    public final ArrayList m() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f394c.iterator();
        while (it.hasNext()) {
            arrayList.add(((c) it.next()).d());
        }
        return arrayList;
    }

    public final ArrayList n() {
        return this.f392a.m();
    }

    public boolean o(String str) {
        if ("com.qualcomm.qti.voice.assist.qvs".equals(str)) {
            return true;
        }
        Iterator it = this.f395d.iterator();
        while (it.hasNext()) {
            if (((KwGroupEnum) it.next()).d().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean p() {
        return this.f394c.size() > 0;
    }

    public final void q(p012m.a aVar) {
        O.e eVarO = aVar.d().o("Overlay_KwGroup", null);
        if (eVarO != null) {
            for (O.e eVar : eVarO.h()) {
                String strD = eVar.d("GroupKwHash");
                int iB = eVar.b("Index");
                for (KwGroupEnum kwGroupEnum : this.f395d) {
                    if (kwGroupEnum.c().equals(strD) && kwGroupEnum.e() == iB) {
                        kwGroupEnum.h(eVar.d("GroupName"));
                        kwGroupEnum.g(eVar.d("ClientKwHash"));
                    }
                }
            }
        }
    }

    public String toString() {
        return "OneVoiceModelManager{GeneralUvBaseModel=" + d() + ", SupportedKwHashList=" + this.f393b + ", SupportedModelCollectionList=" + this.f394c + ", SupportedKwGroupList=" + this.f395d + '}';
    }
}
