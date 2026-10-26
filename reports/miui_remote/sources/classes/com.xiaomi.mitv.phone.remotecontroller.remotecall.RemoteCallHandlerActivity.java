package com.xiaomi.mitv.phone.remotecontroller.remotecall;

import af.l;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.duokan.phone.remotecontroller.R;
import com.xiaomi.mitv.phone.remotecontroller.HoriWidgetMainActivityV2;
import com.xiaomi.mitv.phone.remotecontroller.ir.activity.BrandListActivityV2;
import com.xiaomi.mitv.phone.remotecontroller.ir.activity.LineupSelectActivity;
import com.xiaomi.mitv.phone.remotecontroller.ir.activity.RoomActivity;
import ee.j;
import java.util.HashMap;
import nf.c;
import sf.e;
import sf.f;
import vd.d;
import vd.n;
import wf.e0;
import wf.l0;
import yd.k;

/* JADX INFO: loaded from: classes2.dex */
public class RemoteCallHandlerActivity extends Activity {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f20300b = "com.xiaomi.mitv.phone.remotecontroller.remotecall";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f20301c = "device_type";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f20302d = "RemoteCallHandlerActivity";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f20303e = "intent_extra";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f20304f = "query";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f20305g = "cmd";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final int f20306h = 84;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final int f20307i = 7;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final int f20308j = 26;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final int f20309k = 24;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final int f20310l = 25;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f20311a = null;

    public interface a {
        void a(Intent intent);
    }

    /* JADX WARN: Code duplicated, block: B:20:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:21:0x00a8  */
    public final void a(int i10) {
        Intent intent;
        Resources resources;
        int i11;
        l lVar = new l();
        lVar.f1658d = i10;
        switch (i10) {
            case 1:
                lVar.f1665k = 2;
                resources = getResources();
                i11 = R.string.ir_device_tv;
                break;
            case 2:
                lVar.f1665k = 1;
                resources = getResources();
                i11 = R.string.ir_device_stb;
                break;
            case 3:
                lVar.f1665k = 7;
                resources = getResources();
                i11 = R.string.ir_device_air_condition;
                break;
            case 4:
                lVar.f1665k = 3;
                resources = getResources();
                i11 = R.string.ir_device_dvd;
                break;
            case 5:
                lVar.f1665k = 4;
                resources = getResources();
                i11 = R.string.ir_device_iptv;
                break;
            case 6:
                lVar.f1665k = 6;
                resources = getResources();
                i11 = R.string.ir_device_fan;
                break;
            case 7:
            case 9:
            default:
                if (lVar.f1658d == 2) {
                    intent = new Intent(this, (Class<?>) LineupSelectActivity.class);
                } else {
                    intent = new Intent(this, (Class<?>) BrandListActivityV2.class);
                }
                intent.putExtra(l.S6, lVar);
                startActivity(intent);
                finish();
            case 8:
                lVar.f1665k = 13;
                resources = getResources();
                i11 = R.string.ir_device_amp;
                break;
            case 10:
                lVar.f1665k = 5;
                resources = getResources();
                i11 = R.string.ir_device_prj;
                break;
            case 11:
                lVar.f1665k = 11;
                resources = getResources();
                i11 = R.string.ir_device_dvb_s;
                break;
            case 12:
                lVar.f1665k = 10;
                resources = getResources();
                i11 = R.string.ir_device_box;
                break;
            case 13:
                lVar.f1665k = 14;
                resources = getResources();
                i11 = R.string.ir_device_camera;
                break;
            case 14:
                lVar.f1665k = 8;
                resources = getResources();
                i11 = R.string.ir_device_lamp;
                break;
        }
        lVar.f1655a = resources.getString(i11);
        if (lVar.f1658d == 2) {
            intent = new Intent(this, (Class<?>) LineupSelectActivity.class);
        } else {
            intent = new Intent(this, (Class<?>) BrandListActivityV2.class);
        }
        intent.putExtra(l.S6, lVar);
        startActivity(intent);
        finish();
    }

    public final void b() {
        Intent intent = new Intent(this, (Class<?>) HoriWidgetMainActivityV2.class);
        intent.addFlags(603979776);
        startActivity(intent);
        finish();
    }

    public final void c(Intent intent) {
        intent.setClass(this, HoriWidgetMainActivityV2.class);
        intent.addFlags(603979776);
        startActivity(intent);
        finish();
    }

    public final int d(int i10) {
        if (i10 == 1000000) {
            l0.p(this, RoomActivity.class);
            finish();
            return 1000000;
        }
        j jVarJ = k.g.f72699a.J(i10);
        if (jVarJ != null) {
            if (!d.x() || (!(jVarJ.d().b() == 1 || jVarJ.d().b() == 2) || k.g.f72699a.e0() == 0)) {
                k.Y0(this, jVarJ, -1, true, false);
                finish();
                return jVarJ.e();
            }
            for (j jVar : k.g.f72699a.f0()) {
                String str = ((ee.k) jVar.d()).c().f45706b;
                if (str.contains(":") && String.valueOf(i10).equalsIgnoreCase(str.split(":")[1])) {
                    c.M(this, jVar);
                    finish();
                    return jVar.e();
                }
            }
        }
        startActivity(new Intent(this, (Class<?>) HoriWidgetMainActivityV2.class));
        finish();
        return 0;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:31:0x007b  */
    /* JADX WARN: Code duplicated, block: B:54:0x00b7  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.e(f20302d, "onCreate");
        Intent intent = getIntent();
        byte b10 = 1;
        if (e0.u(this) != 1) {
            if (intent != null && intent.hasExtra(d.f61123g)) {
                try {
                    this.f20311a = intent.getStringExtra(d.f61123g);
                } catch (Exception e10) {
                    e10.printStackTrace();
                }
            }
            if (TextUtils.isEmpty(this.f20311a) || !this.f20311a.equals(d.f61132p)) {
                l0.z(getIntent());
            } else {
                Log.e(f20302d, "from miui titl");
            }
            finish();
            return;
        }
        n.v();
        k.g.f72699a.v0();
        if (intent != null) {
            if (intent.hasExtra(d.f61123g)) {
                try {
                    this.f20311a = intent.getStringExtra(d.f61123g);
                } catch (Exception e11) {
                    e11.printStackTrace();
                }
                String str = this.f20311a;
                if (str != null) {
                    str.getClass();
                    switch (str.hashCode()) {
                        case -1074211717:
                            if (!str.equals(d.f61129m)) {
                                b10 = -1;
                            } else {
                                b10 = 0;
                            }
                            break;
                        case -381820416:
                            if (!str.equals(d.f61127k)) {
                                b10 = -1;
                            }
                            break;
                        case -342500282:
                            if (!str.equals("shortcut")) {
                                b10 = -1;
                            } else {
                                b10 = 2;
                            }
                            break;
                        case 651549921:
                            if (!str.equals(d.f61128l)) {
                                b10 = -1;
                            } else {
                                b10 = 3;
                            }
                            break;
                        case 1071009127:
                            if (!str.equals(d.f61132p)) {
                                b10 = -1;
                            } else {
                                b10 = 4;
                            }
                            break;
                        default:
                            b10 = -1;
                            break;
                    }
                    switch (b10) {
                        case 0:
                        case 1:
                        case 2:
                            if (intent.hasExtra("device_type")) {
                                a(intent.getIntExtra("device_type", -1));
                            } else if (intent.hasExtra(d.f61120d)) {
                                d(intent.getIntExtra(d.f61120d, -1));
                            } else if (!intent.hasExtra("room_id")) {
                                b();
                            } else {
                                startActivity(new Intent(this, (Class<?>) RoomActivity.class));
                                finish();
                            }
                            break;
                        case 3:
                        default:
                            b();
                            break;
                        case 4:
                            finish();
                            break;
                    }
                    HashMap map = new HashMap();
                    map.put("class", this.f20311a);
                    f.a().d(e.Y, map);
                    return;
                }
            }
            b();
        }
    }
}
