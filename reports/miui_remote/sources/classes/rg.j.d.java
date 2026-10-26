package rg;

import android.text.TextUtils;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public final class j extends e {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int f52951g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f52952h = "methodName";

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final String f52953i = "methodCall";

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final String f52954j = "methodReturn";

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String f52955k = "method";

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final String f52956l = "methodCallID";

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final fg.b.a<j> f52957m = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f52958d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b f52959e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c f52960f;

    public static class a implements fg.b.a<j> {
        @Override // fg.b.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public j a(JSONObject jSONObject) {
            JSONObject jSONObjectOptJSONObject = jSONObject != null ? jSONObject.optJSONObject(j.f52955k) : null;
            if (jSONObjectOptJSONObject == null) {
                return new j(null, null, null);
            }
            String strOptString = jSONObjectOptJSONObject.optString(j.f52952h, null);
            b bVarA = b.f52963d.a(jSONObjectOptJSONObject.optJSONObject(j.f52953i));
            JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject(j.f52954j);
            return new j(strOptString, bVarA, (c) ((jSONObjectOptJSONObject2 == null || !(jSONObjectOptJSONObject2.has(c.f52966e) || jSONObjectOptJSONObject2.has(c.f52968g))) ? d.f52975l : c.f52969h).a(jSONObjectOptJSONObject.optJSONObject(j.f52954j)));
        }
    }

    public static class b implements fg.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final String f52961b = "args";

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final String f52962c = "useDataChannelReturn";

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final fg.b.a<b> f52963d = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object[] f52964a;

        public static class a implements fg.b.a<b> {
            @Override // fg.b.a
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public b a(JSONObject jSONObject) {
                JSONArray jSONArrayOptJSONArray;
                if (jSONObject == null || (jSONArrayOptJSONArray = jSONObject.optJSONArray(b.f52961b)) == null) {
                    return null;
                }
                int length = jSONArrayOptJSONArray.length();
                Object[] objArr = new Object[length];
                for (int i10 = 0; i10 < length; i10++) {
                    Object objOpt = jSONArrayOptJSONArray.opt(i10);
                    if (objOpt instanceof JSONArray) {
                        objArr[i10] = b.d((JSONArray) objOpt);
                    } else {
                        JSONArray jSONArrayG = j.g(objOpt);
                        if (jSONArrayG != null) {
                            objArr[i10] = b.d(jSONArrayG);
                        } else if (objOpt == JSONObject.NULL) {
                            objArr[i10] = null;
                        } else {
                            objArr[i10] = objOpt;
                        }
                    }
                }
                return new b(objArr);
            }
        }

        public b(Object[] objArr) {
            this.f52964a = objArr;
        }

        public static Object d(JSONArray jSONArray) {
            int length = jSONArray.length();
            if (length == 0) {
                return null;
            }
            try {
                Object objNewInstance = Array.newInstance(jSONArray.opt(0).getClass(), length);
                for (int i10 = 0; i10 < length; i10++) {
                    Array.set(objNewInstance, i10, jSONArray.opt(i10));
                }
                return objNewInstance;
            } catch (Exception unused) {
                return jSONArray.toString();
            }
        }

        @Override // fg.b
        public JSONObject a() {
            JSONArray jSONArray;
            JSONArray jSONArray2 = new JSONArray();
            Object[] objArr = this.f52964a;
            if (objArr != null) {
                for (Object obj : objArr) {
                    if (obj.getClass().isArray()) {
                        jSONArray = new JSONArray();
                        for (int i10 = 0; i10 < Array.getLength(obj); i10++) {
                            jSONArray.put(Array.get(obj, i10));
                        }
                    } else {
                        if (obj instanceof Collection) {
                            jSONArray = new JSONArray();
                            Iterator it = ((Collection) obj).iterator();
                            while (it.hasNext()) {
                                jSONArray.put(it.next());
                            }
                        } else {
                            jSONArray2.put(obj);
                        }
                    }
                    jSONArray2.put(jSONArray);
                }
            }
            return new fg.a().f(f52961b, jSONArray2).h(f52962c, true).a();
        }

        public Object[] c() {
            return this.f52964a;
        }
    }

    public static class c implements fg.b {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final int f52965d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final String f52966e = "finish";

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final String f52967f = "status";

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final String f52968g = "dataInfo";

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public static final fg.b.a<c> f52969h = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f52970a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f52971b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public rg.a f52972c;

        public static class a implements fg.b.a<c> {
            @Override // fg.b.a
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public c a(JSONObject jSONObject) {
                if (jSONObject == null) {
                    return null;
                }
                int iOptInt = jSONObject.optInt("status");
                boolean zOptBoolean = jSONObject.optBoolean(c.f52966e, true);
                return iOptInt == 0 ? new c(rg.a.f52904g.a(jSONObject.optJSONObject(c.f52968g)), zOptBoolean) : new c(iOptInt, zOptBoolean);
            }
        }

        public c(int i10, boolean z10) {
            this.f52970a = i10;
            this.f52971b = true;
            this.f52972c = null;
        }

        public c(rg.a aVar, boolean z10) {
            this.f52970a = 0;
            this.f52972c = aVar;
            this.f52971b = z10;
        }

        @Override // fg.b
        public JSONObject a() {
            return new fg.a().c("status", this.f52970a).h(f52966e, this.f52971b).e(f52968g, this.f52972c).a();
        }

        public rg.a c() {
            return this.f52972c;
        }

        public int d() {
            return this.f52970a;
        }

        public boolean e() {
            return this.f52971b;
        }
    }

    public static class d extends c {

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public static final String f52973j = "result";

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public static final String f52974k = "error_msg";

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public static final fg.b.a<d> f52975l = new a();

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public JSONObject f52976i;

        public static class a implements fg.b.a<d> {
            @Override // fg.b.a
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public d a(JSONObject jSONObject) {
                if (jSONObject != null) {
                    return new d(jSONObject.optInt("status"), jSONObject.optJSONObject("result"));
                }
                return null;
            }
        }

        public d(int i10, JSONObject jSONObject) {
            super(i10, true);
            this.f52976i = jSONObject;
        }

        @Override // rg.j.c, fg.b
        public JSONObject a() {
            return new fg.a().c("status", this.f52970a).g(f52974k, "").f("result", this.f52976i).a();
        }

        public JSONObject g() {
            return this.f52976i;
        }
    }

    public j(String str, b bVar) {
        this(str, bVar, null);
    }

    public j(String str, b bVar, c cVar) {
        super(0);
        this.f52960f = cVar;
        this.f52959e = bVar;
        this.f52958d = str;
    }

    public j(String str, c cVar) {
        this(str, null, cVar);
    }

    public static final JSONArray g(Object obj) {
        try {
            if (!(obj instanceof String) || TextUtils.isEmpty(obj.toString())) {
                return null;
            }
            return new JSONArray(obj.toString());
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // rg.e, fg.b
    public JSONObject a() {
        return new fg.a(super.a()).e(f52955k, new fg.a().c(f52956l, 0).g(f52952h, this.f52958d).e(f52953i, this.f52959e).e(f52954j, this.f52960f)).a();
    }

    public b d() {
        return this.f52959e;
    }

    public String e() {
        return this.f52958d;
    }

    public c f() {
        return this.f52960f;
    }
}
