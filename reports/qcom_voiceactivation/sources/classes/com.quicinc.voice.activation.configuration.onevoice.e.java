package com.quicinc.voice.activation.configuration.onevoice;

import G.q;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* JADX INFO: compiled from: SocIdModelMapper.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f396a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ArrayList f397b;

    public e() {
        a(k());
    }

    public static String k() {
        String strL = l("/sys/devices/soc0/soc_id");
        if (TextUtils.isEmpty(strL)) {
            strL = l("/sys/devices/system/soc/soc0/id");
        }
        return TextUtils.isEmpty(strL) ? I.c.a("ro.vendor.qti.soc_id") : strL;
    }

    public static String l(String str) {
        String line = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                try {
                    line = bufferedReader.readLine();
                    bufferedReader.close();
                    fileInputStream.close();
                } catch (Throwable th) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                try {
                    fileInputStream.close();
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                }
                throw th3;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            q.j("get socId failed");
        }
        return line;
    }

    public final void a(String str) {
        str.hashCode();
        switch (str) {
            case "454":
            case "472":
                b();
                break;
            case "506":
            case "547":
            case "564":
                c();
                break;
            case "507":
            case "565":
                d();
                break;
            case "519":
            case "536":
                e();
                break;
            case "530":
            case "531":
            case "540":
            case "591":
                f();
                break;
            case "537":
            case "583":
                g();
                break;
            case "557":
            case "577":
                h();
                break;
            case "568":
            case "602":
                i();
                break;
        }
    }

    public final void b() {
        this.f397b = this.f396a.a();
    }

    public final void c() {
        this.f397b = this.f396a.b();
    }

    public final void d() {
        this.f397b = this.f396a.c();
    }

    public final void e() {
        this.f397b = this.f396a.d();
    }

    public final void f() {
        this.f397b = this.f396a.e();
    }

    public final void g() {
        this.f397b = this.f396a.f();
    }

    public final void h() {
        this.f397b = this.f396a.g();
    }

    public final void i() {
        this.f397b = this.f396a.h();
    }

    public String j() {
        return this.f396a.i();
    }

    public ArrayList m() {
        ArrayList arrayList = this.f397b;
        return arrayList == null ? new ArrayList(0) : arrayList;
    }
}
