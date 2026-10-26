package gb;

import android.content.ComponentName;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.j;
import h0.h0;
import java.io.File;
import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import miuix.animation.R;
import o.g;
import p7.k;
import q.e;
import s7.h;
import s7.i;
import u7.m;
import x1.d0;
import x1.o;
import y0.n;

/* JADX INFO: loaded from: classes.dex */
public class c implements h0, d0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile c f3818b;
    public static b c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f3819d = {R.attr.bg_width, R.attr.padding_factor, R.attr.src, R.attr.view_width};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f3820e = {R.attr.button_single_default, R.attr.button_text_left, R.attr.button_text_right, R.attr.button_text_single, R.attr.button_type};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int[] f3821f = {R.attr.checked_color, R.attr.checked_index, R.attr.length, R.attr.point_radius, R.attr.point_space, R.attr.unchecked_color};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int[] f3822g = {android.R.attr.icon, android.R.attr.persistent, android.R.attr.enabled, android.R.attr.layout, android.R.attr.title, android.R.attr.selectable, android.R.attr.key, android.R.attr.summary, android.R.attr.order, android.R.attr.widgetLayout, android.R.attr.dependency, android.R.attr.defaultValue, android.R.attr.shouldDisableView, android.R.attr.fragment, android.R.attr.singleLineTitle, android.R.attr.iconSpaceReserved, R.attr.allowDividerAbove, R.attr.allowDividerBelow, R.attr.defaultValue, R.attr.dependency, R.attr.enableCopying, R.attr.enabled, R.attr.fragment, R.attr.icon, R.attr.iconSpaceReserved, R.attr.isPreferenceVisible, R.attr.key, R.attr.layout, R.attr.order, R.attr.persistent, R.attr.selectable, R.attr.shouldDisableView, R.attr.singleLineTitle, R.attr.summary, R.attr.title, R.attr.widgetLayout};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final int[] f3823h = {R.attr.original_height, R.attr.original_width, R.attr.ratio_mode};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final int[] f3824i = {R.attr.scrollOrientation, R.attr.scrollableView, R.attr.springBackMode};

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final int[] f3825j = {android.R.attr.text, R.attr.arrowFilterSortTabView, R.attr.descending, R.attr.filterSortTabViewTextColor, R.attr.indicatorVisibility};

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final int[] f3826k = {android.R.attr.text, R.attr.arrowFilterSortTabView, R.attr.descending, R.attr.filterSortTabViewBackground, R.attr.filterSortTabViewForeground, R.attr.filterSortTabViewHorizontalPadding, R.attr.filterSortTabViewTabActivatedTextAppearance, R.attr.filterSortTabViewTabTextAppearance, R.attr.filterSortTabViewVerticalPadding, R.attr.indicatorVisibility};
    public static final int[] l = {android.R.attr.enabled, R.attr.filterSortViewBackground, R.attr.layoutConfig};

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final int[] f3827m = {android.R.attr.textColor, android.R.attr.text, R.attr.closable, R.attr.closeBackground};

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final int[] f3828n = {R.attr.activatedAlpha, R.attr.checkedAlpha, R.attr.focusedAlpha, R.attr.hoveredActivatedAlpha, R.attr.hoveredAlpha, R.attr.hoveredCheckedAlpha, R.attr.normalAlpha, R.attr.pressedAlpha, R.attr.tintColor, R.attr.tintRadius};

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final int[] f3829o = {android.R.attr.radius, android.R.attr.topLeftRadius, android.R.attr.topRightRadius, android.R.attr.bottomLeftRadius, android.R.attr.bottomRightRadius, android.R.attr.layerType, R.attr.miuix_strokeColor, R.attr.miuix_strokeWidth, R.attr.miuix_useSmooth};

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final int[] f3830p = {android.R.attr.radius, android.R.attr.topLeftRadius, android.R.attr.topRightRadius, android.R.attr.bottomLeftRadius, android.R.attr.bottomRightRadius, android.R.attr.layerType, R.attr.miuix_solidColor, R.attr.miuix_strokeColor, R.attr.miuix_strokeWidth, R.attr.miuix_useSmooth};

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final int[] f3831q = {android.R.attr.radius, android.R.attr.topLeftRadius, android.R.attr.topRightRadius, android.R.attr.bottomLeftRadius, android.R.attr.bottomRightRadius, android.R.attr.layerType, R.attr.miuix_solidColor, R.attr.miuix_strokeColor, R.attr.miuix_strokeWidth, R.attr.miuix_useSmooth};

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static boolean[] f3832r = new boolean[3];

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final int[] f3833s = {android.R.attr.selectableItemBackground, R.attr.selectableItemBackground};

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final int[] f3834t = {android.R.attr.summaryOn, android.R.attr.summaryOff, android.R.attr.disableDependentsState, R.attr.disableDependentsState, R.attr.summaryOff, R.attr.summaryOn};
    public static final int[] u = {android.R.attr.dialogTitle, android.R.attr.dialogMessage, android.R.attr.dialogIcon, android.R.attr.positiveButtonText, android.R.attr.negativeButtonText, android.R.attr.dialogLayout, R.attr.dialogIcon, R.attr.dialogLayout, R.attr.dialogMessage, R.attr.dialogTitle, R.attr.negativeButtonText, R.attr.positiveButtonText};

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final int[] f3835v = {R.attr.useSimpleSummaryProvider};
    public static final int[] w = {android.R.attr.entries, android.R.attr.entryValues, R.attr.entries, R.attr.entryValues, R.attr.useSimpleSummaryProvider};

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final int[] f3836x = {android.R.attr.entries, android.R.attr.entryValues, R.attr.entries, R.attr.entryValues};

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final int[] f3837y = {android.R.attr.icon, android.R.attr.persistent, android.R.attr.enabled, android.R.attr.layout, android.R.attr.title, android.R.attr.selectable, android.R.attr.key, android.R.attr.summary, android.R.attr.order, android.R.attr.widgetLayout, android.R.attr.dependency, android.R.attr.defaultValue, android.R.attr.shouldDisableView, android.R.attr.fragment, android.R.attr.singleLineTitle, android.R.attr.iconSpaceReserved, R.attr.allowDividerAbove, R.attr.allowDividerBelow, R.attr.defaultValue, R.attr.dependency, R.attr.enableCopying, R.attr.enabled, R.attr.fragment, R.attr.icon, R.attr.iconSpaceReserved, R.attr.isPreferenceVisible, R.attr.key, R.attr.layout, R.attr.order, R.attr.persistent, R.attr.selectable, R.attr.shouldDisableView, R.attr.singleLineTitle, R.attr.summary, R.attr.title, R.attr.widgetLayout};

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final int[] f3838z = {android.R.attr.layout, android.R.attr.divider, android.R.attr.dividerHeight, R.attr.allowDividerAfterLastItem};
    public static final int[] A = {android.R.attr.orderingFromXml, R.attr.initialExpandedChildrenCount, R.attr.orderingFromXml};
    public static final int[] B = {android.R.attr.maxWidth, android.R.attr.maxHeight, R.attr.maxHeight, R.attr.maxWidth};
    public static final int[] C = {android.R.attr.layout, android.R.attr.max, R.attr.adjustable, R.attr.min, R.attr.seekBarIncrement, R.attr.showSeekBarValue, R.attr.updatesContinuously};
    public static final int[] D = {android.R.attr.summaryOn, android.R.attr.summaryOff, android.R.attr.disableDependentsState, android.R.attr.switchTextOn, android.R.attr.switchTextOff, R.attr.disableDependentsState, R.attr.summaryOff, R.attr.summaryOn, R.attr.switchTextOff, R.attr.switchTextOn};
    public static final int[] E = {android.R.attr.summaryOn, android.R.attr.summaryOff, android.R.attr.disableDependentsState, android.R.attr.switchTextOn, android.R.attr.switchTextOff, R.attr.disableDependentsState, R.attr.summaryOff, R.attr.summaryOn, R.attr.switchTextOff, R.attr.switchTextOn};
    public static final c F = new c();

    public /* synthetic */ c() {
    }

    public static void f(e eVar, o.d dVar, q.d dVar2) {
        dVar2.f7114o = -1;
        dVar2.f7116p = -1;
        if (eVar.U[0] != 2 && dVar2.U[0] == 4) {
            int i10 = dVar2.J.f7077g;
            int iR = eVar.r() - dVar2.L.f7077g;
            q.c cVar = dVar2.J;
            cVar.f7079i = dVar.l(cVar);
            q.c cVar2 = dVar2.L;
            cVar2.f7079i = dVar.l(cVar2);
            dVar.e(dVar2.J.f7079i, i10);
            dVar.e(dVar2.L.f7079i, iR);
            dVar2.f7114o = 2;
            dVar2.f7089a0 = i10;
            int i11 = iR - i10;
            dVar2.W = i11;
            int i12 = dVar2.f7094d0;
            if (i11 < i12) {
                dVar2.W = i12;
            }
        }
        if (eVar.U[1] == 2 || dVar2.U[1] != 4) {
            return;
        }
        int i13 = dVar2.K.f7077g;
        int iK = eVar.k() - dVar2.M.f7077g;
        q.c cVar3 = dVar2.K;
        cVar3.f7079i = dVar.l(cVar3);
        q.c cVar4 = dVar2.M;
        cVar4.f7079i = dVar.l(cVar4);
        dVar.e(dVar2.K.f7079i, i13);
        dVar.e(dVar2.M.f7079i, iK);
        if (dVar2.f7092c0 > 0 || dVar2.f7104i0 == 8) {
            q.c cVar5 = dVar2.N;
            cVar5.f7079i = dVar.l(cVar5);
            dVar.e(dVar2.N.f7079i, dVar2.f7092c0 + i13);
        }
        dVar2.f7116p = 2;
        dVar2.f7091b0 = i13;
        int i14 = iK - i13;
        dVar2.X = i14;
        int i15 = dVar2.f7096e0;
        if (i14 < i15) {
            dVar2.X = i15;
        }
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0055  */
    public static final n.a g(Context context) {
        boolean z10;
        boolean z11;
        t.d.f(context, "context");
        if ("AiBrightnessModel.db".length() != 0) {
            Iterable cVar = new f8.c(0, "AiBrightnessModel.db".length() - 1);
            if (!(cVar instanceof Collection) || !((Collection) cVar).isEmpty()) {
                m it = cVar.iterator();
                while (true) {
                    if (!((f8.b) it).f3635d) {
                        z11 = true;
                        break;
                    }
                    char cCharAt = "AiBrightnessModel.db".charAt(it.a());
                    if (!(Character.isWhitespace(cCharAt) || Character.isSpaceChar(cCharAt))) {
                        z11 = false;
                        break;
                    }
                }
            } else {
                z11 = true;
                break;
            }
            if (z11) {
                z10 = true;
            } else {
                z10 = false;
            }
        } else {
            z10 = true;
        }
        if (!(z10)) {
            return new n.a(context);
        }
        throw new IllegalArgumentException("Cannot build a database with null or empty name. If you are trying to create an in memory database, use Room.inMemoryDatabaseBuilder".toString());
    }

    public static final boolean h(int i10, int i11) {
        return (i10 & i11) == i11;
    }

    public static void i(Context context) {
        if (f3818b == null) {
            synchronized (c.class) {
                if (f3818b == null) {
                    f3818b = new c(context);
                }
            }
        }
    }

    public static String j(lb.a aVar) {
        switch (aVar) {
            case FLOAT32:
                return "float";
            case INT32:
                return "int";
            case UINT8:
            case INT8:
                return "byte";
            case INT64:
                return "long";
            case STRING:
                return "string";
            case BOOL:
                return "bool";
            case INT16:
                return "short";
            default:
                throw new IllegalArgumentException("DataType error: DataType " + aVar + " is not supported yet");
        }
    }

    @Override // h0.h0
    public void b(View view) {
    }

    @Override // h0.h0
    public void c() {
    }

    @Override // x1.d0
    public Object d(y1.c cVar, float f10) {
        return Float.valueOf(o.d(cVar) * f10);
    }

    public String e(Context context, String str) throws Exception {
        String string;
        int i10;
        ServiceConnection serviceConnection;
        s7.e eVar;
        Object obj;
        s7.b bVar;
        s7.a aVar;
        int[] iArrC = g.c(16);
        int length = iArrC.length;
        int i11 = 0;
        int i12 = 0;
        while (true) {
            string = null;
            if (i12 >= length) {
                i10 = 0;
                break;
            }
            i10 = iArrC[i12];
            if (androidx.activity.e.l(i10).equals(str)) {
                break;
            }
            i12++;
        }
        String strA = k7.b.a("ro.build.freeme.label");
        if (!TextUtils.isEmpty(strA) && strA.equalsIgnoreCase("FREEMEOS")) {
            i10 = 15;
        }
        String strA2 = k7.b.a("ro.ssui.product");
        int i13 = !TextUtils.isEmpty(strA2) && !strA2.equalsIgnoreCase("unknown") ? 16 : i10;
        if (i13 == 0) {
            throw new Exception(String.format("undefined oaid method of manufacturer %s", str));
        }
        String strN = "";
        switch (s7.d.f7649a[g.b(i13)]) {
            case 1:
                y0.g gVar = new y0.g();
                Intent intent = new Intent();
                intent.setAction("com.asus.msa.action.ACCESS_DID");
                intent.setComponent(new ComponentName("com.asus.msa.SupplementaryDID", "com.asus.msa.SupplementaryDID.SupplementaryDIDService"));
                if (!context.bindService(intent, (ServiceConnection) gVar.f8504b, 1)) {
                    return "";
                }
                try {
                    try {
                        try {
                            IBinder iBinder = (IBinder) ((LinkedBlockingQueue) gVar.f8503a).poll(1L, TimeUnit.SECONDS);
                            if (iBinder == null) {
                                context.unbindService((ServiceConnection) gVar.f8504b);
                                return "";
                            }
                            strN = new r7.a(iBinder, i11).N();
                            serviceConnection = (ServiceConnection) gVar.f8504b;
                        } catch (Exception e10) {
                            f7.a.c("ASUSDeviceIDHelper", e10.getMessage());
                            serviceConnection = (ServiceConnection) gVar.f8504b;
                        }
                        context.unbindService(serviceConnection);
                        return strN;
                    } catch (Exception e11) {
                        f7.a.c("ASUSDeviceIDHelper", e11.getMessage());
                        return strN;
                    }
                } catch (Throwable th) {
                    try {
                        context.unbindService((ServiceConnection) gVar.f8504b);
                        throw th;
                    } catch (Exception e12) {
                        f7.a.c("ASUSDeviceIDHelper", e12.getMessage());
                        throw th;
                    }
                }
            case 2:
                eVar = new s7.e(0);
                return eVar.a(context);
            case 3:
            case 4:
                j jVar = new j();
                context.getPackageName();
                Intent intent2 = new Intent();
                intent2.setClassName("com.zui.deviceidservice", "com.zui.deviceidservice.DeviceidService");
                try {
                    try {
                        if (!context.bindService(intent2, (ServiceConnection) jVar.f690b, 1)) {
                            return "";
                        }
                        try {
                            IBinder iBinder2 = (IBinder) ((LinkedBlockingQueue) jVar.f689a).poll(1L, TimeUnit.SECONDS);
                            if (iBinder2 == null) {
                                obj = jVar.f690b;
                            } else {
                                Parcel parcelObtain = Parcel.obtain();
                                Parcel parcelObtain2 = Parcel.obtain();
                                try {
                                    try {
                                        parcelObtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
                                        iBinder2.transact(1, parcelObtain, parcelObtain2, 0);
                                        parcelObtain2.readException();
                                        string = parcelObtain2.readString();
                                        parcelObtain2.recycle();
                                    } catch (Exception e13) {
                                        e13.printStackTrace();
                                        parcelObtain2.recycle();
                                    }
                                    parcelObtain.recycle();
                                    try {
                                        obj = jVar.f690b;
                                        strN = string;
                                    } catch (Exception unused) {
                                    }
                                } catch (Throwable th2) {
                                    parcelObtain2.recycle();
                                    parcelObtain.recycle();
                                    throw th2;
                                }
                            }
                            break;
                        } catch (Exception e14) {
                            e14.printStackTrace();
                            obj = jVar.f690b;
                        }
                        context.unbindService((ServiceConnection) obj);
                        return strN;
                    } catch (Exception unused2) {
                        return "";
                    }
                } catch (Throwable th3) {
                    try {
                        context.unbindService((ServiceConnection) jVar.f690b);
                        break;
                    } catch (Exception unused3) {
                    }
                    throw th3;
                }
            case 5:
                try {
                    Cursor cursorQuery = context.getContentResolver().query(Uri.parse("content://com.meizu.flyme.openidsdk/"), null, null, new String[]{"oaid"}, null);
                    if (cursorQuery != null && !cursorQuery.isClosed()) {
                        cursorQuery.moveToFirst();
                        int columnIndex = cursorQuery.getColumnIndex("value");
                        string = columnIndex > 0 ? cursorQuery.getString(columnIndex) : null;
                        int columnIndex2 = cursorQuery.getColumnIndex("code");
                        if (columnIndex2 > 0) {
                            cursorQuery.getInt(columnIndex2);
                        }
                        int columnIndex3 = cursorQuery.getColumnIndex("expired");
                        if (columnIndex3 > 0) {
                            cursorQuery.getLong(columnIndex3);
                        }
                        break;
                    }
                    try {
                        cursorQuery.close();
                        return string;
                    } catch (Throwable th4) {
                        th = th4;
                        strN = string;
                        th.printStackTrace();
                        return strN;
                    }
                } catch (Throwable th5) {
                    th = th5;
                }
                break;
            case 6:
                h hVar = new h();
                Intent intent3 = new Intent();
                intent3.setComponent(new ComponentName("com.heytap.openid", "com.heytap.openid.IdentifyService"));
                intent3.setAction("action.com.heytap.openid.OPEN_ID_SERVICE");
                try {
                    try {
                        if (!context.bindService(intent3, hVar.f7661d, 1)) {
                            return "";
                        }
                        try {
                            IBinder iBinderPoll = hVar.c.poll(1L, TimeUnit.SECONDS);
                            if (iBinderPoll == null) {
                                context.unbindService(hVar.f7661d);
                                return "";
                            }
                            int i14 = r7.c.a.f7481b;
                            IInterface iInterfaceQueryLocalInterface = iBinderPoll.queryLocalInterface("com.heytap.openid.IOpenID");
                            hVar.f7660b = (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof r7.c)) ? new r7.c.a.C0155a(iBinderPoll) : (r7.c) iInterfaceQueryLocalInterface;
                            strN = hVar.a(context);
                            bVar = hVar.f7661d;
                        } catch (Exception e15) {
                            f7.a.c("OppoDeviceIDHelper", e15.getMessage());
                            bVar = hVar.f7661d;
                        }
                        context.unbindService(bVar);
                        return strN;
                    } catch (Exception e16) {
                        f7.a.c("OppoDeviceIDHelper", e16.getMessage());
                        return strN;
                    }
                } catch (Throwable th6) {
                    try {
                        context.unbindService(hVar.f7661d);
                        throw th6;
                    } catch (Exception e17) {
                        f7.a.c("OppoDeviceIDHelper", e17.getMessage());
                        throw th6;
                    }
                }
            case 7:
                eVar = new s7.e(1);
                return eVar.a(context);
            case 8:
                try {
                    ContentProviderClient contentProviderClientAcquireContentProviderClient = context.getContentResolver().acquireContentProviderClient(Uri.parse("content://cn.nubia.identity/identity"));
                    Bundle bundleCall = contentProviderClientAcquireContentProviderClient.call("getOAID", null, null);
                    contentProviderClientAcquireContentProviderClient.close();
                    return bundleCall.getInt("code", -1) == 0 ? bundleCall.getString("id") : "";
                } catch (Exception e18) {
                    f7.a.c("NubiaDeviceIDHelper", e18.getMessage());
                    return "";
                }
            case 9:
                try {
                    Cursor cursorQuery2 = context.getContentResolver().query(Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/OAID"), null, null, null, null);
                    if (cursorQuery2 == null) {
                        return "";
                    }
                    strN = cursorQuery2.moveToNext() ? cursorQuery2.getString(cursorQuery2.getColumnIndex("value")) : "";
                    cursorQuery2.close();
                    return strN;
                } catch (Exception e19) {
                    f7.a.c("VivoDeviceIDHelper", e19.getMessage());
                    return strN;
                }
            case 10:
            case 11:
            case 12:
                return k.a(context);
            case 13:
                s7.g gVar2 = new s7.g();
                Intent intent4 = new Intent();
                intent4.setComponent(new ComponentName("com.heytap.openid", "com.heytap.openid.IdentifyService"));
                intent4.setAction("action.com.heytap.openid.OPEN_ID_SERVICE");
                try {
                    if (!context.bindService(intent4, gVar2.f7658d, 1)) {
                        return "";
                    }
                    try {
                        try {
                            IBinder iBinderPoll2 = gVar2.c.poll(1L, TimeUnit.SECONDS);
                            if (iBinderPoll2 == null) {
                                context.unbindService(gVar2.f7658d);
                                return "";
                            }
                            int i15 = r7.b.a.f7479b;
                            IInterface iInterfaceQueryLocalInterface2 = iBinderPoll2.queryLocalInterface("com.heytap.openid.IOpenID");
                            gVar2.f7657b = (iInterfaceQueryLocalInterface2 == null || !(iInterfaceQueryLocalInterface2 instanceof r7.b)) ? new r7.b.a.C0154a(iBinderPoll2) : (r7.b) iInterfaceQueryLocalInterface2;
                            strN = gVar2.a(context);
                            aVar = gVar2.f7658d;
                        } catch (Exception e20) {
                            f7.a.c("OnePlusDeviceIDHelper", e20.getMessage());
                            aVar = gVar2.f7658d;
                        }
                        context.unbindService(aVar);
                        return strN;
                    } catch (Throwable th7) {
                        try {
                            context.unbindService(gVar2.f7658d);
                            throw th7;
                        } catch (Exception e21) {
                            f7.a.c("OnePlusDeviceIDHelper", e21.getMessage());
                            throw th7;
                        }
                    }
                } catch (Exception e22) {
                    f7.a.c("OnePlusDeviceIDHelper", e22.getMessage());
                    return strN;
                }
            case 14:
            case 15:
            case 16:
                i iVar = new i();
                try {
                    context.getPackageManager().getPackageInfo("com.mdid.msa", 0);
                } catch (Exception e23) {
                    f7.a.c("ZTEDeviceIDHelper", e23.getMessage());
                }
                String packageName = context.getPackageName();
                Intent intent5 = new Intent();
                intent5.setClassName("com.mdid.msa", "com.mdid.msa.service.MsaKlService");
                intent5.setAction("com.bun.msa.action.start.service");
                intent5.putExtra("com.bun.msa.param.pkgname", packageName);
                try {
                    intent5.putExtra("com.bun.msa.param.runinset", true);
                    context.startService(intent5);
                } catch (Exception e24) {
                    f7.a.c("ZTEDeviceIDHelper", e24.getMessage());
                }
                Intent intent6 = new Intent();
                intent6.setClassName("com.mdid.msa", "com.mdid.msa.service.MsaIdService");
                intent6.setAction("com.bun.msa.action.bindto.service");
                intent6.putExtra("com.bun.msa.param.pkgname", packageName);
                try {
                    if (!context.bindService(intent6, iVar.f7663b, 1)) {
                        return "";
                    }
                    try {
                        try {
                            try {
                                IBinder iBinderPoll3 = iVar.f7662a.poll(1L, TimeUnit.SECONDS);
                                if (iBinderPoll3 == null) {
                                    context.unbindService(iVar.f7663b);
                                    return "";
                                }
                                Parcel parcelObtain3 = Parcel.obtain();
                                Parcel parcelObtain4 = Parcel.obtain();
                                try {
                                    parcelObtain3.writeInterfaceToken("com.bun.lib.MsaIdInterface");
                                    iBinderPoll3.transact(3, parcelObtain3, parcelObtain4, 0);
                                    parcelObtain4.readException();
                                    string = parcelObtain4.readString();
                                    break;
                                } catch (Throwable unused4) {
                                    parcelObtain4.recycle();
                                    parcelObtain3.recycle();
                                }
                                parcelObtain4.recycle();
                                parcelObtain3.recycle();
                                try {
                                    context.unbindService(iVar.f7663b);
                                    break;
                                } catch (Exception e25) {
                                    f7.a.c("ZTEDeviceIDHelper", e25.getMessage());
                                }
                                return string;
                            } catch (Exception e26) {
                                f7.a.c("ZTEDeviceIDHelper", e26.getMessage());
                                context.unbindService(iVar.f7663b);
                                return "";
                            }
                        } catch (Throwable th8) {
                            context.unbindService(iVar.f7663b);
                            throw th8;
                        }
                        context.unbindService(iVar.f7663b);
                        throw th8;
                    } catch (Exception e27) {
                        f7.a.c("ZTEDeviceIDHelper", e27.getMessage());
                        throw th8;
                    }
                } catch (Exception e28) {
                    f7.a.c("ZTEDeviceIDHelper", e28.getMessage());
                    return "";
                }
                break;
            default:
                return "";
        }
    }

    public /* synthetic */ c(Context context) {
        String str;
        Bundle bundle;
        int iIntValue;
        int iIntValue2;
        String str2 = y9.a.f8870a;
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            str = cacheDir.getPath() + "/debug_log/";
        } else {
            Log.e("Config", "Fail to getCacheDir");
            str = null;
        }
        String str3 = y9.a.f8870a;
        t9.b bVar = new t9.b(str3);
        u9.b bVar2 = new u9.b();
        bVar2.f7981a = new w9.b();
        v9.a aVar = new v9.a();
        try {
            bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
        } catch (PackageManager.NameNotFoundException e10) {
            Log.e("LoggerFactory", e10.getMessage());
            bundle = null;
        }
        if (bundle != null && bundle.containsKey("maxBackup")) {
            Object obj = bundle.get("maxBackup");
            if (obj instanceof Integer) {
                Integer num = (Integer) obj;
                iIntValue = num.intValue() < 20 ? num.intValue() : 4;
            }
            Log.e("LoggerFactory", "Log config error:maxBackup must be int type and smaller than 20");
        }
        int i10 = iIntValue - 1;
        if (i10 < 1) {
            throw new IllegalArgumentException(androidx.activity.e.p("index can't be less than 1: ", i10));
        }
        aVar.f8207a = i10;
        if (bundle != null && bundle.containsKey("maxFileMbSize")) {
            Object obj2 = bundle.get("maxFileMbSize");
            if (obj2 instanceof Integer) {
                Integer num2 = (Integer) obj2;
                iIntValue2 = num2.intValue() <= 10 ? num2.intValue() * 1024 * 1024 : 1048576;
            }
            Log.e("LoggerFactory", "Log config error:maxFileMbSize must be int type and smaller than 10");
        }
        if (iIntValue2 < 1) {
            throw new IllegalArgumentException(androidx.activity.e.p("size can't be less than 1: ", iIntValue2));
        }
        aVar.f8208b = iIntValue2;
        v9.b bVar3 = new v9.b(str, str3);
        synchronized (bVar3) {
            bVar3.f8217j = aVar;
        }
        v9.b bVar4 = bVar2.f7982b;
        if (bVar4 != bVar3) {
            if (bVar4 != null) {
                bVar4.b();
                bVar2.f7982b = null;
            }
            bVar2.f7982b = bVar3;
        }
        bVar.c.addIfAbsent(bVar2);
        bVar.f7814b = fa.b.f3650g ? t9.a.VERBOSE : t9.a.INFO;
        c = new b(bVar);
    }
}
