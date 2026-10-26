package com.miui.aiautobrt.database;

import android.content.Context;
import android.database.Cursor;
import c1.b;
import h3.c;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import t.d;
import u7.k;
import v7.f;
import y0.h;
import y0.o;

/* JADX INFO: loaded from: classes.dex */
public final class BrightnessModelDatabase_Impl extends BrightnessModelDatabase {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public volatile c f2729m;

    public class a extends o.a {
        public a() {
        }

        /* JADX WARN: Code duplicated, block: B:67:0x0260  */
        /* JADX WARN: Code duplicated, block: B:69:0x0280  */
        /* JADX WARN: Instruction removed from duplicated block: B:67:0x0260, please report this as an issue */
        @Override // y0.o.a
        public final o.b a(b bVar) {
            Map map;
            Set setH;
            a1.a aVar;
            int i10;
            HashMap map2 = new HashMap(8);
            map2.put("mId", new a1.a.C0000a("mId", "INTEGER", true, 1, null, 1));
            map2.put("ambient_lux", new a1.a.C0000a("ambient_lux", "REAL", true, 0, null, 1));
            map2.put("ambient_lux_span", new a1.a.C0000a("ambient_lux_span", "REAL", true, 0, null, 1));
            map2.put("orientation_and_app_industry", new a1.a.C0000a("orientation_and_app_industry", "REAL", true, 0, null, 1));
            map2.put("app_code", new a1.a.C0000a("app_code", "REAL", true, 0, null, 1));
            map2.put("cur_brt", new a1.a.C0000a("cur_brt", "REAL", true, 0, null, 1));
            map2.put("cur_brt_span", new a1.a.C0000a("cur_brt_span", "REAL", true, 0, null, 1));
            map2.put("curve_brt", new a1.a.C0000a("curve_brt", "REAL", true, 0, null, 1));
            a1.a aVar2 = new a1.a(map2, new HashSet(0), new HashSet(0));
            d1.c cVar = (d1.c) bVar;
            Cursor cursorF = cVar.f("PRAGMA table_info(`brightness_model_event`)");
            try {
                if (cursorF.getColumnCount() <= 0) {
                    map = k.f7979b;
                    k7.b.j(cursorF, null);
                } else {
                    int columnIndex = cursorF.getColumnIndex("name");
                    int columnIndex2 = cursorF.getColumnIndex("type");
                    int columnIndex3 = cursorF.getColumnIndex("notnull");
                    int columnIndex4 = cursorF.getColumnIndex("pk");
                    int columnIndex5 = cursorF.getColumnIndex("dflt_value");
                    v7.b bVar2 = new v7.b();
                    while (cursorF.moveToNext()) {
                        String string = cursorF.getString(columnIndex);
                        String string2 = cursorF.getString(columnIndex2);
                        boolean z10 = cursorF.getInt(columnIndex3) != 0;
                        int i11 = cursorF.getInt(columnIndex4);
                        String string3 = cursorF.getString(columnIndex5);
                        d.e(string, "name");
                        d.e(string2, "type");
                        bVar2.put(string, new a1.a.C0000a(string, string2, z10, i11, string3, 2));
                    }
                    bVar2.d();
                    bVar2.f8194m = true;
                    k7.b.j(cursorF, null);
                    map = bVar2;
                }
                Cursor cursorF2 = cVar.f("PRAGMA foreign_key_list(`brightness_model_event`)");
                try {
                    int columnIndex6 = cursorF2.getColumnIndex("id");
                    int columnIndex7 = cursorF2.getColumnIndex("seq");
                    int columnIndex8 = cursorF2.getColumnIndex("table");
                    int columnIndex9 = cursorF2.getColumnIndex("on_delete");
                    int columnIndex10 = cursorF2.getColumnIndex("on_update");
                    List listI = j7.k.I(cursorF2);
                    cursorF2.moveToPosition(-1);
                    f fVar = new f();
                    while (cursorF2.moveToNext()) {
                        if (cursorF2.getInt(columnIndex7) == 0) {
                            int i12 = cursorF2.getInt(columnIndex6);
                            ArrayList arrayList = new ArrayList();
                            ArrayList arrayList2 = new ArrayList();
                            int i13 = columnIndex6;
                            ArrayList<a1.a.c> arrayList3 = new ArrayList();
                            Iterator it = listI.iterator();
                            while (true) {
                                i10 = columnIndex7;
                                if (!it.hasNext()) {
                                    break;
                                }
                                Object next = it.next();
                                List list = listI;
                                if (((a1.a.c) next).f28b == i12) {
                                    arrayList3.add(next);
                                }
                                columnIndex7 = i10;
                                listI = list;
                            }
                            List list2 = listI;
                            for (a1.a.c cVar2 : arrayList3) {
                                arrayList.add(cVar2.f29d);
                                arrayList2.add(cVar2.f30e);
                            }
                            String string4 = cursorF2.getString(columnIndex8);
                            d.e(string4, "cursor.getString(tableColumnIndex)");
                            String string5 = cursorF2.getString(columnIndex9);
                            d.e(string5, "cursor.getString(onDeleteColumnIndex)");
                            String string6 = cursorF2.getString(columnIndex10);
                            d.e(string6, "cursor.getString(onUpdateColumnIndex)");
                            fVar.add(new a1.a.b(string4, string5, string6, arrayList, arrayList2));
                            columnIndex6 = i13;
                            columnIndex7 = i10;
                            listI = list2;
                            columnIndex8 = columnIndex8;
                        }
                    }
                    Set setH2 = k7.b.h(fVar);
                    k7.b.j(cursorF2, null);
                    Cursor cursorF3 = cVar.f("PRAGMA index_list(`brightness_model_event`)");
                    try {
                        int columnIndex11 = cursorF3.getColumnIndex("name");
                        int columnIndex12 = cursorF3.getColumnIndex("origin");
                        int columnIndex13 = cursorF3.getColumnIndex("unique");
                        if (columnIndex11 != -1 && columnIndex12 != -1 && columnIndex13 != -1) {
                            f fVar2 = new f();
                            while (true) {
                                if (!cursorF3.moveToNext()) {
                                    setH = k7.b.h(fVar2);
                                    k7.b.j(cursorF3, null);
                                    break;
                                }
                                if (d.b("c", cursorF3.getString(columnIndex12))) {
                                    String string7 = cursorF3.getString(columnIndex11);
                                    boolean z11 = cursorF3.getInt(columnIndex13) == 1;
                                    d.e(string7, "name");
                                    a1.a.d dVarJ = j7.k.J(bVar, string7, z11);
                                    if (dVarJ == null) {
                                        k7.b.j(cursorF3, null);
                                    } else {
                                        fVar2.add(dVarJ);
                                    }
                                }
                            }
                            aVar = new a1.a(map, setH2, setH);
                            if (!aVar2.equals(aVar)) {
                                return new o.b(true, null);
                            }
                            return new o.b(false, "brightness_model_event(com.miui.aiautobrt.database.BrightnessModelEvent).\n Expected:\n" + aVar2 + "\n Found:\n" + aVar);
                        }
                        k7.b.j(cursorF3, null);
                        setH = null;
                        aVar = new a1.a(map, setH2, setH);
                        if (!aVar2.equals(aVar)) {
                            return new o.b(true, null);
                        }
                        return new o.b(false, "brightness_model_event(com.miui.aiautobrt.database.BrightnessModelEvent).\n Expected:\n" + aVar2 + "\n Found:\n" + aVar);
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            k7.b.j(cursorF3, th);
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    try {
                        throw th3;
                    } catch (Throwable th4) {
                        k7.b.j(cursorF2, th3);
                        throw th4;
                    }
                }
            } catch (Throwable th5) {
                try {
                    throw th5;
                } catch (Throwable th6) {
                    k7.b.j(cursorF, th5);
                    throw th6;
                }
            }
        }
    }

    @Override // y0.n
    public final h d() {
        return new h(this, new HashMap(0), new HashMap(0), "brightness_model_event");
    }

    @Override // y0.n
    public final c1.c e(y0.b bVar) {
        o oVar = new o(bVar, new a());
        Context context = bVar.f8486a;
        d.f(context, "context");
        return bVar.c.a(new c1.c.b(context, bVar.f8487b, oVar));
    }

    @Override // y0.n
    public final List<z0.a> f(Map<Class<Object>, Object> map) {
        return Arrays.asList(new z0.a[0]);
    }

    @Override // y0.n
    public final Set<Class<Object>> h() {
        return new HashSet();
    }

    @Override // y0.n
    public final Map<Class<?>, List<Class<?>>> i() {
        HashMap map = new HashMap();
        map.put(h3.b.class, Collections.emptyList());
        return map;
    }

    @Override // com.miui.aiautobrt.database.BrightnessModelDatabase
    public final h3.b q() {
        c cVar;
        if (this.f2729m != null) {
            return this.f2729m;
        }
        synchronized (this) {
            if (this.f2729m == null) {
                this.f2729m = new c(this);
            }
            cVar = this.f2729m;
        }
        return cVar;
    }
}
