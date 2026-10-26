package com.miui.antispam.policy;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import ce.j;
import com.google.common.net.HttpHeaders;
import com.xiaomi.miglobaladsdk.bid.BidConstance;
import dd.w;
import j2.c;
import j2.f;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import miui.cloud.CloudPushConstants;
import miui.os.Build;
import org.json.JSONObject;
import x3.h;
import x3.i;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final boolean f7164c = td.a.f54381a;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f7165a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f7166b = "2dcd9s0c-ad3f-2fas-0l3a-abzo301jd0s9";

    public class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f7167a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f7168b;

        public a(boolean z10, int i10) {
            this.f7167a = z10;
            this.f7168b = i10;
        }
    }

    public b(Context context) {
        this.f7165a = context;
    }

    /* JADX WARN: Code duplicated, block: B:38:0x00c7  */
    private String a(String str, i iVar) throws Throwable {
        HttpURLConnection httpURLConnection;
        Exception e10;
        int i10 = -1;
        HttpURLConnection httpURLConnection2 = null;
        try {
            try {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setInstanceFollowRedirects(true);
                    httpURLConnection.setRequestProperty(HttpHeaders.USER_AGENT, "antispam/1.0.0");
                    boolean z10 = f7164c;
                    if (z10) {
                        httpURLConnection.setDoInput(true);
                        Log.d("URLFilterPolicy", "getRedirectURL: " + str);
                    }
                    int responseCode = httpURLConnection.getResponseCode();
                    if (z10) {
                        try {
                            InputStream inputStream = httpURLConnection.getInputStream();
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            byte[] bArr = new byte[4096];
                            while (true) {
                                int i11 = inputStream.read(bArr);
                                if (i11 == -1) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, i11);
                            }
                            Log.d("URLFilterPolicy", "result: " + byteArrayOutputStream.toString());
                        } catch (Exception e11) {
                            e10 = e11;
                            i10 = responseCode;
                            Log.e("URLFilterPolicy", "Exception when get redirect url :" + e10.getMessage(), e10);
                            h.a(iVar, i10, 0);
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            return str;
                        } catch (Throwable th2) {
                            th = th2;
                            i10 = responseCode;
                            httpURLConnection2 = httpURLConnection;
                            h.a(iVar, i10, 0);
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            throw th;
                        }
                    }
                    String headerField = httpURLConnection.getHeaderField(HttpHeaders.LOCATION);
                    if (headerField == null) {
                        headerField = httpURLConnection.getURL().toString();
                    }
                    h.a(iVar, responseCode, 0);
                    httpURLConnection.disconnect();
                    return headerField;
                } catch (Exception e12) {
                    e10 = e12;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Exception e13) {
            httpURLConnection = null;
            e10 = e13;
        } catch (Throwable th4) {
            th = th4;
            h.a(iVar, i10, 0);
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            throw th;
        }
    }

    private int b(String str) throws Throwable {
        int i10 = 0;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            HashMap map = new HashMap();
            map.put("url", str);
            map.put("ts", String.valueOf(System.currentTimeMillis() / 1000));
            map.put("c", CloudPushConstants.CHANNEL_ID);
            map.put(BidConstance.BID_SIGN, j.l(map, "2dcd9s0c-ad3f-2fas-0l3a-abzo301jd0s9"));
            String strT = j.t(map, "", false);
            if (TextUtils.isEmpty(strT)) {
                return 0;
            }
            JSONObject jSONObject = new JSONObject(strT);
            if (jSONObject.getInt("code") == 200) {
                int i11 = ((JSONObject) jSONObject.get("data")).getInt("urlCategoryId");
                if (-1 != i11) {
                    if (i11 == 0) {
                        i10 = 2;
                    } else if (1 == i11 || 2 == i11) {
                        i10 = 1;
                    }
                }
                Log.i("URLFilterPolicy", "check url phish result = " + i11);
            }
        } catch (Exception e10) {
            Log.e("URLFilterPolicy", "Exception when resolve result string :", e10);
        }
        return i10;
    }

    private int e(int i10) {
        return i10 | 128;
    }

    public int c(String str, String str2) throws Throwable {
        if (!w.u()) {
            return -1;
        }
        try {
            e2.a.s();
            boolean zF = o2.i.f(this.f7165a, str);
            String strA = a(str2, new i("antispam_urlfilterpolicy"));
            int iD = new f(this.f7165a).d(str2);
            if (iD != 1) {
                Log.e("URLFilterPolicy", "check ori by browser! === ");
                iD = b(str2);
            }
            if (iD != 1) {
                Log.e("URLFilterPolicy", "check by browser!");
                iD = b(strA);
            }
            Log.i("URLFilterPolicy", "AVL black url check done : result = " + iD);
            if (!zF) {
                if (iD == 1) {
                    return 2;
                }
                return iD == 2 ? 0 : -1;
            }
            if (iD == 1) {
                return 2;
            }
            String strA2 = o2.i.a(str2);
            HashSet<String> hashSetD = o2.i.d(this.f7165a);
            Log.i("URLFilterPolicy", "URL WhiteList Check done ");
            if (hashSetD.contains(strA2)) {
                return 0;
            }
            if (!g2.b.h()) {
                Log.i("URLFilterPolicy", "SecurityCenter is not allowed to access internet, check failed!");
                return -1;
            }
            String strA3 = o2.i.a(strA);
            if (strA2 != null && !strA2.equals(strA3)) {
                e2.a.r();
            }
            return hashSetD.contains(strA3) ? 0 : 1;
        } catch (Exception e10) {
            Log.e("URLFilterPolicy", "exception when get URL Scan Result : ", e10);
            return -1;
        }
    }

    public a d(int i10, c cVar) {
        if (!Build.IS_INTERNATIONAL_BUILD && o2.a.j(this.f7165a, cVar.f47838e)) {
            try {
                ArrayList<String> arrayListB = o2.i.b(cVar.f47840g);
                if (arrayListB.isEmpty()) {
                    return null;
                }
                if (!o2.i.f(this.f7165a, cVar.f47835b)) {
                    return new a(true, e(i10));
                }
                if (i10 >= 3) {
                    return null;
                }
                ArrayList<String> arrayList = new ArrayList();
                Iterator<String> it = arrayListB.iterator();
                while (it.hasNext()) {
                    arrayList.add(o2.i.a(it.next()));
                }
                f fVar = new f(this.f7165a);
                for (String str : arrayListB) {
                    if (fVar.d(str) == 1) {
                        e2.a.t("black");
                        e2.a.o(cVar.f47835b + "; " + str);
                        return new a(true, 8);
                    }
                }
                if (o2.i.c(cVar.f47840g)) {
                    e2.a.t("number");
                    Log.i("URLFilterPolicy", "url marked by phoneNumber in text");
                    return new a(true, e(i10));
                }
                HashSet<String> hashSetD = o2.i.d(this.f7165a);
                for (String str2 : arrayList) {
                    if (!hashSetD.contains(str2)) {
                        e2.a.t(arrayListB.size() == 1 ? "non_white_black_single_url" : "non_white_black_multi_url");
                        e2.a.u(cVar.f47835b + "; " + str2);
                        Log.i("URLFilterPolicy", "url marked by risky url in text");
                        return new a(true, e(i10));
                    }
                }
                e2.a.t("white");
            } catch (Exception e10) {
                Log.e("URLFilterPolicy", "Exception when check message urls ! ", e10);
            }
        }
        return null;
    }
}
