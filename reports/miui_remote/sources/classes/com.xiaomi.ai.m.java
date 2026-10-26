package com.xiaomi.ai;

import android.content.Context;
import android.content.SharedPreferences;
import com.xiaomi.ai.mibrain.MibrainOauthManager;
import com.xiaomi.ai.mibrain.MibrainOauthManagerCallbacks;
import com.xiaomi.ai.mibrain.MibrainUtils;
import com.xiaomi.ai.utils.Log;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f17185a = "OauthPreference_xml";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String f17186b = "MiAiOauthHelper";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private MiAiOauthCallbacks f17187c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f17188d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private MibrainOauthManager f17189e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private volatile int f17190f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f17191g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f17192h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final Object f17193i = new Object();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private MibrainOauthManagerCallbacks f17194j = new n(this);

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f17195a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f17196b;
    }

    public m(Context context, String str, String str2, String str3) {
        this.f17188d = context;
        this.f17189e = new MibrainOauthManager(this.f17194j, str, str2, str3, b(a(context)));
    }

    public static String a(Context context) {
        return com.xiaomi.ai.utils.n.b(context);
    }

    public static String b(String str) {
        return MibrainUtils.base64(String.format("{\"d\":\"%s\"}", str).getBytes());
    }

    private void h() {
        synchronized (this.f17193i) {
            this.f17190f++;
        }
    }

    private void i() {
        synchronized (this.f17193i) {
            this.f17190f--;
        }
    }

    private boolean j() {
        synchronized (this.f17193i) {
            if (this.f17191g) {
                return true;
            }
            if (this.f17190f >= 0) {
                return false;
            }
            this.f17189e.release();
            this.f17191g = true;
            Log.w(f17186b, "release MiAiOauthHelper ");
            return true;
        }
    }

    public int a(String str, String str2, String str3, int i10) {
        return this.f17189e.setAnonymousAuth(str, str2, str3, i10);
    }

    public int a(String str, String str2, String str3, String str4, String str5, String str6) {
        return this.f17189e.updateAnonymousArgs(str, str2, str3, str4, str5, str6);
    }

    public a a(boolean z10) {
        a aVar = new a();
        synchronized (this.f17193i) {
            if (!this.f17192h) {
                throw new IllegalStateException("MiAiOauthHelper is not init !");
            }
            h();
            if (j()) {
                return null;
            }
            synchronized (m.class) {
                MibrainOauthManager.AccessTokenResult accessToken = this.f17189e.getAccessToken(z10);
                aVar.f17196b = accessToken.errorCode;
                aVar.f17195a = accessToken.accessToken;
            }
            synchronized (this.f17193i) {
                i();
                if (j()) {
                    return null;
                }
                return aVar;
            }
        }
    }

    public String a() {
        return this.f17188d.getSharedPreferences(f17185a, 0).getString(b(a(this.f17188d)), null);
    }

    public String a(String str) {
        if (str == null) {
            return null;
        }
        return this.f17188d.getSharedPreferences(f17185a, 0).getString(String.format("%s:%s", b(a(this.f17188d)), str), null);
    }

    public String a(String str, String str2, String str3) {
        return this.f17189e.genAnonymousAuthorization(str, str2, str3);
    }

    public void a(int i10) {
        this.f17189e.setOauthPt(i10);
    }

    public void a(MiAiOauthCallbacks miAiOauthCallbacks) {
        this.f17187c = miAiOauthCallbacks;
    }

    public void a(MibrainOauthManager.updateTokenCallback updatetokencallback) {
        this.f17189e.setUpdateTokenCallback(updatetokencallback);
    }

    public synchronized boolean a(int i10, String str, String str2, String str3, long j10) {
        if (str2 == null || str3 == null || j10 < 0) {
            Log.e(f17186b, "setAuthorizationTokens: invalid parameters\n");
            return false;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("accessToken", str2);
            jSONObject.put("refreshToken", str3);
            jSONObject.put("upTime", Long.toString(j10 + (System.currentTimeMillis() / 1000)));
            String str4 = b(a(this.f17188d)) + ":" + str;
            if (i10 != SpeechEngine.ENGINE_AUTH_ANONYMOUS_PROXY && i10 != SpeechEngine.ENGINE_AUTH_ANONYMOUS) {
                this.f17194j.putOauthData(b(a(this.f17188d)), jSONObject.toString());
            } else {
                if (str == null) {
                    Log.e(f17186b, "setAuthorizationTokens: clientId is null\n");
                    return false;
                }
                this.f17194j.putOauthData(str4, jSONObject.toString());
            }
            return true;
        } catch (JSONException e10) {
            e10.printStackTrace();
            return false;
        }
    }

    public void b() {
        synchronized (this.f17193i) {
            this.f17189e.init();
            this.f17192h = true;
            Log.w(f17186b, "init MiAiOauthHelper ");
        }
    }

    public void b(int i10) {
        SharedPreferences sharedPreferences = this.f17188d.getSharedPreferences(f17185a, 0);
        if (sharedPreferences.getInt(sj.f.f53982f, 0) != i10) {
            d();
            sharedPreferences.edit().putInt(sj.f.f53982f, i10).apply();
        }
        this.f17189e.setOauthEnv(i10);
        android.util.Log.e(f17186b, "setEnv" + i10);
    }

    public void c() {
        synchronized (this.f17193i) {
            i();
            j();
        }
    }

    public void c(int i10) {
        this.f17189e.setNetTimeOut(i10);
    }

    public void c(String str) {
        if (str == null) {
            return;
        }
        Log.MiLogD(f17186b, "cleanOauthData clientId " + str);
        String strB = b(a(this.f17188d));
        SharedPreferences.Editor editorEdit = this.f17188d.getSharedPreferences(f17185a, 0).edit();
        editorEdit.remove(String.format("%s:%s", strB, str));
        editorEdit.apply();
    }

    public void d() {
        Log.MiLogD(f17186b, "cleanOauthData");
        String strB = b(a(this.f17188d));
        SharedPreferences.Editor editorEdit = this.f17188d.getSharedPreferences(f17185a, 0).edit();
        editorEdit.remove(strB);
        editorEdit.apply();
    }

    public void e() {
        Log.MiLogD(f17186b, "cleanAllOauthData");
        SharedPreferences.Editor editorEdit = this.f17188d.getSharedPreferences(f17185a, 0).edit();
        editorEdit.clear();
        editorEdit.apply();
    }
}
