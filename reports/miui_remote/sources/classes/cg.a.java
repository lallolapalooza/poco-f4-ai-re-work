package cg;

import com.hzy.tvmao.KKACManagerV2;
import com.hzy.tvmao.ir.ac.ACStateV2;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.json.JSONObject;
import p019q0.z1;

/* JADX INFO: loaded from: classes3.dex */
public class a implements l.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f12144d = "ACNLPProcessor";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public KKACManagerV2 f12145a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public af.h f12146b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ee.j f12147c;

    public class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f12148a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f12149b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f12150c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f12151d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f12152e;

        public b() {
            this.f12152e = -1;
        }
    }

    @Override // cg.l.e
    public List<Integer> a() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(3);
        return arrayList;
    }

    @Override // cg.l.e
    public l.d b(ee.j jVar, String str, JSONObject jSONObject) {
        String strM;
        Objects.toString(jSONObject);
        b bVarF = f(jSONObject);
        this.f12145a = jVar.h();
        this.f12146b = jVar.i();
        this.f12147c = jVar;
        l.d dVar = new l.d();
        if (this.f12145a == null || this.f12146b == null) {
            dVar.f12230a = false;
            dVar.f12231b = "遥控设备失败";
            this.f12145a = null;
            this.f12146b = null;
            return dVar;
        }
        str.getClass();
        switch (str) {
            case "turn_up":
                strM = m(bVarF);
                dVar.f12231b = strM;
                break;
            case "turn_down":
                strM = k(bVarF);
                dVar.f12231b = strM;
                break;
            case "set":
                strM = j(bVarF);
                dVar.f12231b = strM;
                break;
            case "open":
                strM = h();
                dVar.f12231b = strM;
                break;
            case "close":
                strM = g();
                dVar.f12231b = strM;
                break;
            default:
                dVar.f12231b = l.f12206m;
                dVar.f12230a = false;
                break;
        }
        this.f12145a = null;
        this.f12146b = null;
        this.f12147c = null;
        return dVar;
    }

    @Override // cg.l.e
    public List<ee.j> c() {
        return yd.k.g.f72699a.C();
    }

    @Override // cg.l.e
    public ee.j d() {
        return null;
    }

    @Override // cg.l.e
    public String e() {
        return "aircondition";
    }

    public b f(JSONObject jSONObject) {
        b bVar = new b();
        try {
            if (jSONObject.has("action")) {
                bVar.f12148a = jSONObject.optString("action");
            }
            if (jSONObject.has("mode")) {
                bVar.f12149b = jSONObject.optString("mode");
            }
            if (jSONObject.has("fan_level")) {
                bVar.f12150c = jSONObject.optString("fan_level");
            }
            if (jSONObject.has("fan_direction")) {
                bVar.f12151d = jSONObject.optString("fan_direction");
            }
            if (jSONObject.has("temperature")) {
                bVar.f12152e = (int) Float.parseFloat(jSONObject.optString("temperature"));
            }
        } catch (Exception e10) {
            e10.printStackTrace();
        }
        return bVar;
    }

    public String g() {
        if (this.f12145a.getPowerState() == 0) {
            this.f12145a.changePowerState();
        }
        i();
        return "关闭" + this.f12147c.l();
    }

    public String h() {
        if (this.f12145a.getPowerState() == 1) {
            this.f12145a.changePowerState();
        }
        i();
        return "打开" + this.f12147c.l();
    }

    public final void i() {
        if (this.f12146b == null || this.f12145a == null) {
            return;
        }
        vd.d.g().j(this.f12146b.e(), this.f12145a.getACIRPatternIntArray(), true, true);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:103:0x0160  */
    /* JADX WARN: Code duplicated, block: B:116:0x0189  */
    /* JADX WARN: Code duplicated, block: B:117:0x0191  */
    /* JADX WARN: Code duplicated, block: B:170:0x0253  */
    /* JADX WARN: Code duplicated, block: B:178:0x0283  */
    /* JADX WARN: Code duplicated, block: B:201:0x02df  */
    /* JADX WARN: Code duplicated, block: B:40:0x009f  */
    /* JADX WARN: Code duplicated, block: B:95:0x0142  */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x024a, code lost:
    
        if (r13.f12145a.getCurUDDirect() == 0) goto L178;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String j(b bVar) {
        String strA;
        boolean z10;
        KKACManagerV2 kKACManagerV2;
        int size;
        KKACManagerV2 kKACManagerV3;
        ACStateV2.UDWindDirectKey uDWindDirectKey;
        Integer num;
        boolean targetUDWindDirect;
        String str;
        byte b10;
        boolean targetWindSpeed;
        String str2;
        boolean z11;
        int i10;
        boolean z12 = true;
        String strA2 = "设置空调";
        if (bVar.f12152e > 0) {
            if (!this.f12145a.isTempCanControl()) {
                this.f12145a.changeACTargetModel(0);
            }
            if (!this.f12145a.setTargetTemp(bVar.f12152e)) {
                return android.support.v4.media.d.a(android.support.v4.media.e.a("空调设置温度"), bVar.f12152e, "度失败");
            }
            strA = android.support.v4.media.d.a(android.support.v4.media.e.a("设置空调温度为"), bVar.f12152e, "度");
            strA2 = android.support.v4.media.d.a(androidx.appcompat.widget.e.a("设置空调", "温度为"), bVar.f12152e, "度");
            z10 = true;
        } else {
            strA = "对不起，我不知道该怎么做";
            z10 = false;
        }
        String str3 = bVar.f12149b;
        if (str3 != null && str3.length() > 0) {
            String str4 = bVar.f12149b;
            str4.getClass();
            switch (str4) {
                case "hot":
                    str2 = "制热";
                    z11 = true;
                    i10 = 1;
                    break;
                case "auto":
                    str2 = "自动";
                    z11 = true;
                    i10 = 4;
                    break;
                case "cold":
                    str2 = "制冷";
                    z11 = true;
                    i10 = 0;
                    break;
                case "wind":
                    str2 = "通风";
                    z11 = true;
                    i10 = 3;
                    break;
                case "dehumidifier":
                    str2 = "除湿";
                    z11 = true;
                    i10 = 4;
                    break;
                default:
                    str2 = "";
                    z11 = false;
                    i10 = 0;
                    break;
            }
            if (!z11) {
                return "对不起，无法设置该模式";
            }
            if (!this.f12145a.changeACTargetModel(i10)) {
                return android.support.v4.media.k.a("对不起，无法设置空调", str2, "模式");
            }
            if (z10) {
                strA2 = k.g.a(strA2, ", ");
            }
            strA2 = android.support.v4.media.k.a(strA2, str2, "模式");
            z10 = true;
        }
        String str5 = bVar.f12150c;
        if (str5 != null && str5.length() > 0) {
            String str6 = bVar.f12150c;
            str6.getClass();
            switch (str6) {
                case "speed_up":
                    b10 = 0;
                    break;
                case "change":
                    b10 = 1;
                    break;
                case "medium":
                    b10 = 2;
                    break;
                case "low":
                    b10 = 3;
                    break;
                case "auto":
                    b10 = 4;
                    break;
                case "high":
                    b10 = 5;
                    break;
                case "speed_down":
                    b10 = 6;
                    break;
                default:
                    b10 = -1;
                    break;
            }
            String str7 = "自动风速";
            switch (b10) {
                case 0:
                    if (this.f12145a.getCurWindSpeed() != 1) {
                        targetWindSpeed = this.f12145a.setTargetWindSpeed(3);
                        str7 = "风速高";
                    } else {
                        targetWindSpeed = this.f12145a.setTargetWindSpeed(2);
                        str7 = "风速中";
                    }
                    break;
                case 1:
                    int iChangeWindSpeed = this.f12145a.changeWindSpeed();
                    if (iChangeWindSpeed != 0) {
                        if (iChangeWindSpeed == 1) {
                            str7 = "风速低";
                        } else if (iChangeWindSpeed != 2) {
                            str7 = iChangeWindSpeed != 3 ? "切换风速" : "风速高";
                        } else {
                            str7 = "风速中";
                        }
                    }
                    targetWindSpeed = true;
                    break;
                case 2:
                    targetWindSpeed = this.f12145a.setTargetWindSpeed(2);
                    str7 = "风速中";
                    break;
                case 3:
                    targetWindSpeed = this.f12145a.setTargetWindSpeed(1);
                    str7 = "风速低";
                    break;
                case 4:
                    targetWindSpeed = this.f12145a.setTargetWindSpeed(0);
                    break;
                case 5:
                    targetWindSpeed = this.f12145a.setTargetWindSpeed(3);
                    str7 = "风速高";
                    break;
                case 6:
                    if (this.f12145a.getCurWindSpeed() != 3) {
                        targetWindSpeed = this.f12145a.setTargetWindSpeed(1);
                        str7 = "风速低";
                    } else {
                        targetWindSpeed = this.f12145a.setTargetWindSpeed(2);
                        str7 = "风速中";
                    }
                    break;
                default:
                    targetWindSpeed = false;
                    break;
            }
            if (targetWindSpeed) {
                if (z10) {
                    strA2 = k.g.a(strA2, ", ");
                }
                strA2 = k.g.a(strA2, str7);
                z10 = true;
            }
        }
        String str8 = bVar.f12151d;
        if (str8 == null || str8.length() <= 0) {
            z12 = z10;
        } else {
            List<Integer> uDWindDirectList = this.f12145a.getUDWindDirectList();
            String str9 = bVar.f12151d;
            str9.getClass();
            str9.hashCode();
            switch (str9) {
                case "dir_up":
                    this.f12145a.changeUDWindDirect(ACStateV2.UDWindDirectKey.UDDIRECT_KEY_FIX);
                    if (uDWindDirectList.size() > 1) {
                        kKACManagerV2 = this.f12145a;
                        size = uDWindDirectList.size() - 1;
                        num = uDWindDirectList.get(size);
                        targetUDWindDirect = kKACManagerV2.setTargetUDWindDirect(num.intValue());
                        break;
                    } else {
                        targetUDWindDirect = true;
                        break;
                    }
                    break;
                case "dir_change":
                    if (uDWindDirectList.size() > 1) {
                        kKACManagerV3 = this.f12145a;
                        uDWindDirectKey = ACStateV2.UDWindDirectKey.UDDIRECT_KEY_FIX;
                    } else {
                        kKACManagerV3 = this.f12145a;
                        uDWindDirectKey = ACStateV2.UDWindDirectKey.UDDIRECT_KEY_SWING;
                    }
                    kKACManagerV3.changeUDWindDirect(uDWindDirectKey);
                    targetUDWindDirect = true;
                    break;
                case "dir_auto":
                    ACStateV2.UDWindDirectType curUDDirectType = this.f12145a.getCurUDDirectType();
                    if (curUDDirectType != ACStateV2.UDWindDirectType.UDDIRECT_ONLY_FIX) {
                        if (curUDDirectType == ACStateV2.UDWindDirectType.UDDIRECT_FULL) {
                            break;
                        }
                        targetUDWindDirect = true;
                        break;
                    }
                    kKACManagerV3 = this.f12145a;
                    uDWindDirectKey = ACStateV2.UDWindDirectKey.UDDIRECT_KEY_SWING;
                    kKACManagerV3.changeUDWindDirect(uDWindDirectKey);
                    targetUDWindDirect = true;
                    break;
                case "dir_down":
                    this.f12145a.changeUDWindDirect(ACStateV2.UDWindDirectKey.UDDIRECT_KEY_FIX);
                    if (uDWindDirectList.size() > 1) {
                        kKACManagerV2 = this.f12145a;
                        num = uDWindDirectList.get(0);
                        targetUDWindDirect = kKACManagerV2.setTargetUDWindDirect(num.intValue());
                        break;
                    } else {
                        targetUDWindDirect = true;
                        break;
                    }
                    break;
                case "dir_fix":
                    kKACManagerV3 = this.f12145a;
                    uDWindDirectKey = ACStateV2.UDWindDirectKey.UDDIRECT_KEY_FIX;
                    kKACManagerV3.changeUDWindDirect(uDWindDirectKey);
                    targetUDWindDirect = true;
                    break;
                case "dir_mid":
                    this.f12145a.changeUDWindDirect(ACStateV2.UDWindDirectKey.UDDIRECT_KEY_FIX);
                    if (uDWindDirectList.size() > 1) {
                        kKACManagerV2 = this.f12145a;
                        size = uDWindDirectList.size() / 2;
                        num = uDWindDirectList.get(size);
                        targetUDWindDirect = kKACManagerV2.setTargetUDWindDirect(num.intValue());
                        break;
                    } else {
                        targetUDWindDirect = true;
                        break;
                    }
                    break;
                default:
                    targetUDWindDirect = false;
                    break;
            }
            if (targetUDWindDirect) {
                if (z10) {
                    strA2 = k.g.a(strA2, ", ");
                }
                ACStateV2.UDWindDirectType curUDDirectType2 = this.f12145a.getCurUDDirectType();
                if (curUDDirectType2 == ACStateV2.UDWindDirectType.UDDIRECT_ONLY_SWING || (curUDDirectType2 == ACStateV2.UDWindDirectType.UDDIRECT_FULL && this.f12145a.getCurUDDirect() == 0)) {
                    str = "自动风向";
                } else if (uDWindDirectList.size() <= 1) {
                    str = "固定风向";
                } else {
                    int curUDDirect = this.f12145a.getCurUDDirect();
                    str = curUDDirect == uDWindDirectList.get(0).intValue() ? "风向下" : curUDDirect == uDWindDirectList.get(uDWindDirectList.size() - 1).intValue() ? "风向上" : "风向中";
                }
                strA2 = k.g.a(strA2, str);
            } else {
                z12 = z10;
            }
        }
        if (!z12) {
            return strA;
        }
        i();
        return strA2;
    }

    public final String k(b bVar) {
        return l(bVar, false);
    }

    public final String l(b bVar, boolean z10) {
        int curTemp = this.f12145a.getCurTemp();
        int i10 = bVar.f12152e;
        if (i10 <= 0) {
            i10 = 1;
        }
        if (!this.f12145a.isTempCanControl()) {
            this.f12145a.changeACTargetModel(0);
        }
        for (int i11 = 0; i11 < i10; i11++) {
            KKACManagerV2 kKACManagerV2 = this.f12145a;
            if (z10) {
                kKACManagerV2.increaseTmp();
            } else {
                kKACManagerV2.decreaseTmp();
            }
        }
        int curTemp2 = this.f12145a.getCurTemp();
        String strA = z1.a(z10 ? curTemp != curTemp2 ? "调高温度，当前温度" : "对不起，无法调高温度，当前温度已经为" : curTemp != curTemp2 ? "调低温度，当前温度" : "对不起，无法调低温度，当前温度已经为", curTemp2, "度");
        if (curTemp2 != curTemp) {
            i();
        }
        return strA;
    }

    public final String m(b bVar) {
        return l(bVar, true);
    }
}
