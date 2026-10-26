package com.miui.antispam.policy;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import com.miui.securitycenter.R;
import j2.c;
import j2.e;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import miui.os.Build;
import miui.provider.ExtraTelephony;
import miui.yellowpage.YellowPageContract;

/* JADX INFO: loaded from: classes2.dex */
public class SmartSmsFilterPolicy extends a {
    private static Pattern CONTACT_INFO_PTN = Pattern.compile("\\[(.+?)\\]((.|\n)*?);");
    private j2.a mProxy;

    public SmartSmsFilterPolicy(Context context, a.b bVar, j2.b bVar2, e eVar) {
        super(context, bVar, bVar2, eVar);
        this.mProxy = new j2.a(context);
    }

    public static boolean isInSmsWhiteList(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Cursor cursorQuery = context.getContentResolver().query(YellowPageContract.AntispamWhiteList.CONTNET_URI, new String[]{"number"}, "number LIKE '" + str + "%'", null, null);
        if (cursorQuery != null) {
            while (cursorQuery.moveToNext()) {
                try {
                    try {
                        if (TextUtils.equals(str, cursorQuery.getString(0)) || str.startsWith("106")) {
                            cursorQuery.close();
                            return true;
                        }
                    } catch (Exception e10) {
                        e10.printStackTrace();
                    }
                } catch (Throwable th2) {
                    cursorQuery.close();
                    throw th2;
                }
            }
            cursorQuery.close();
        }
        return false;
    }

    private static boolean isMiuiPattern(Context context, String str) {
        HashMap map = new HashMap();
        for (String str2 : context.getResources().getStringArray(R.array.contact_infotype_entries)) {
            map.put(str2, 1);
        }
        Matcher matcher = CONTACT_INFO_PTN.matcher(str);
        int i10 = 0;
        int i11 = 0;
        while (matcher.find()) {
            int iStart = matcher.start();
            int iEnd = matcher.end();
            if (map.containsKey(matcher.group(1).trim()) && (iStart == i10 + 1 || i11 == 0)) {
                i11++;
                if (i11 == 2) {
                    return true;
                }
            } else {
                i11 = 0;
            }
            i10 = iEnd;
        }
        return false;
    }

    @Override // com.miui.antispam.policy.a
    public a.C0115a dbQuery(c cVar) {
        return null;
    }

    @Override // com.miui.antispam.policy.a
    public String getName() {
        return "SmartSmsFilterPolicy";
    }

    @Override // com.miui.antispam.policy.a
    public int getType() {
        return 0;
    }

    @Override // com.miui.antispam.policy.a
    public a.C0115a handleData(c cVar) {
        int iShouldFilter;
        if (Build.IS_INTERNATIONAL_BUILD) {
            return null;
        }
        if ((!(ExtraTelephony.isServiceNumber(cVar.f47835b) && g2.b.a(this.mContext, "service_sms_mode", cVar.f47838e, 1) == 1) && (ExtraTelephony.isServiceNumber(cVar.f47835b) || g2.b.a(this.mContext, "stranger_sms_mode", cVar.f47838e, 1) != 1)) || (iShouldFilter = shouldFilter(this.mContext, cVar.f47835b, cVar.f47840g)) == 0) {
            return null;
        }
        return new a.C0115a(this, true, iShouldFilter == 1 ? 8 : 4);
    }

    public void initSmsEngine(boolean z10) {
        this.mProxy.k(z10);
    }

    public int shouldFilter(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2) || isMiuiPattern(context, str2) || isInSmsWhiteList(context, str)) {
            return 0;
        }
        Log.w(getName(), "start sms engine check");
        return this.mProxy.h(str, str2);
    }
}
