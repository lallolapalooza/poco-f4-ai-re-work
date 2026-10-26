package l2;

import ah.c;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.miui.networkassistant.utils.DeviceUtil;
import com.miui.securitycenter.Application;
import dd.w;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import miui.os.Build;
import org.json.JSONException;
import org.json.JSONObject;
import ti.e;
import x3.d;
import x3.i;
import y2.j;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static volatile a f49047d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f49048e = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f49049a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private SharedPreferences f49050b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile boolean f49051c = false;

    /* JADX INFO: renamed from: l2.a$a, reason: collision with other inner class name */
    private static class AsyncTaskC0491a extends AsyncTask<Void, Integer, Void> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private b f49052a;

        /* JADX INFO: renamed from: l2.a$a$a, reason: collision with other inner class name */
        class C0492a extends BroadcastReceiver {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ long f49053a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ b f49054b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ DownloadManager f49055c;

            C0492a(long j10, b bVar, DownloadManager downloadManager) {
                this.f49053a = j10;
                this.f49054b = bVar;
                this.f49055c = downloadManager;
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                long longExtra = intent.getLongExtra("extra_download_id", -1L);
                if (this.f49053a != longExtra) {
                    return;
                }
                AsyncTaskC0491a.this.new b(this.f49054b, longExtra, this.f49055c).execute(new Void[0]);
                context.getApplicationContext().unregisterReceiver(this);
            }
        }

        /* JADX INFO: renamed from: l2.a$a$b */
        private class b extends AsyncTask<Void, Void, Boolean> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            b f49057a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            long f49058b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            DownloadManager f49059c;

            b(b bVar, long j10, DownloadManager downloadManager) {
                this.f49057a = bVar;
                this.f49058b = j10;
                this.f49059c = downloadManager;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean doInBackground(Void... voidArr) {
                return Boolean.valueOf(AsyncTaskC0491a.this.b(Application.y(), this.f49058b, this.f49059c));
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(Boolean bool) {
                super.onPostExecute(bool);
                a.e(Application.y()).f49051c = false;
                b bVar = this.f49057a;
                if (bVar != null) {
                    bVar.a(bool.booleanValue() ? 4 : 0);
                }
            }
        }

        AsyncTaskC0491a(Context context, b bVar) {
            this.f49052a = bVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code duplicated, block: B:15:0x0033  */
        public boolean b(Context context, long j10, DownloadManager downloadManager) throws Throwable {
            int i10;
            FileOutputStream fileOutputStreamOpenFileOutput;
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(j10);
            try {
                Cursor cursorQuery = downloadManager.query(query);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToNext()) {
                            i10 = cursorQuery.getInt(cursorQuery.getColumnIndex("status"));
                        } else {
                            i10 = 0;
                        }
                    } catch (Throwable th2) {
                        try {
                            cursorQuery.close();
                        } catch (Throwable th3) {
                            th2.addSuppressed(th3);
                        }
                        throw th2;
                    }
                } else {
                    i10 = 0;
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                if (i10 != 8) {
                    Log.e("SmsEngineUpdateManager", "fail: download failed , Version: 2.1.2 , status: " + i10);
                    e2.a.v("2.1.2", "fail_download");
                    return false;
                }
                String str = String.format("%s/%s%s", a.f49048e, "resource.zip", ".tmp");
                String strA = j.a(str);
                if (!TextUtils.equals(strA, "6206c9f17a64246498b52e117d0cff1e")) {
                    Log.e("SmsEngineUpdateManager", "fail: md5 verify failed !  md5: " + strA + " , ver:2.1.2 , local: 6206c9f17a64246498b52e117d0cff1e");
                    c();
                    e2.a.v("2.1.2", "fail_md5");
                    return false;
                }
                FileInputStream fileInputStream = null;
                try {
                    Log.i("SmsEngineUpdateManager", "afterDownload: delete tmpFileName : " + str);
                    context.deleteFile("resource.zip");
                    fileOutputStreamOpenFileOutput = context.openFileOutput("resource.zip", 0);
                    try {
                        try {
                            FileInputStream fileInputStream2 = new FileInputStream(str);
                            try {
                                byte[] bArr = new byte[4096];
                                while (true) {
                                    int i11 = fileInputStream2.read(bArr);
                                    if (i11 == -1) {
                                        downloadManager.remove(j10);
                                        e.b(fileInputStream2);
                                        e.c(fileOutputStreamOpenFileOutput);
                                        c();
                                        try {
                                            c.c(true);
                                            a.e(context).h("2.1.2");
                                            context.getContentResolver().call(Uri.parse("content://antispam"), "initSmsEngine", (String) null, (Bundle) null);
                                            o2.a.B(context, System.currentTimeMillis());
                                            e2.a.v("2.1.2", FirebaseAnalytics.Param.SUCCESS);
                                            Log.i("SmsEngineUpdateManager", "5. xiaomi engine update success !");
                                            return true;
                                        } catch (Exception e10) {
                                            Log.e("SmsEngineUpdateManager", "fail: Exception when Loader sms files ! ", e10);
                                            e2.a.v("2.1.2", "fail_crash_load");
                                            return false;
                                        } finally {
                                            c.c(false);
                                        }
                                    }
                                    fileOutputStreamOpenFileOutput.write(bArr, 0, i11);
                                }
                            } catch (IOException e11) {
                                e = e11;
                                fileInputStream = fileInputStream2;
                                Log.e("SmsEngineUpdateManager", "fail: Exception when copy tmp files ! ", e);
                                e2.a.v("2.1.2", "fail_crash_copy");
                                e.b(fileInputStream);
                                e.c(fileOutputStreamOpenFileOutput);
                                c();
                                return false;
                            } catch (Throwable th4) {
                                th = th4;
                                fileInputStream = fileInputStream2;
                                e.b(fileInputStream);
                                e.c(fileOutputStreamOpenFileOutput);
                                c();
                                throw th;
                            }
                        } catch (IOException e12) {
                            e = e12;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                    }
                } catch (IOException e13) {
                    e = e13;
                    fileOutputStreamOpenFileOutput = null;
                } catch (Throwable th6) {
                    th = th6;
                    fileOutputStreamOpenFileOutput = null;
                }
            } catch (Exception e14) {
                Log.e("SmsEngineUpdateManager", "afterDownload Exception", e14);
                e2.a.v("2.1.2", "fail_crash_afterDownload");
                return false;
            }
        }

        private void c() {
            File file = new File(String.format("%s/%s%s", a.f49048e, "resource.zip", ".tmp"));
            if (file.exists()) {
                file.delete();
            }
        }

        private void e(Context context, String str, b bVar) {
            DownloadManager downloadManager = (DownloadManager) context.getSystemService("download");
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "resource.zip.tmp");
            request.setNotificationVisibility(2);
            context.getApplicationContext().registerReceiver(new C0492a(downloadManager.enqueue(request), bVar, downloadManager), new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
        }

        private String f() {
            HashMap map = new HashMap(2);
            map.put("appVersion", DeviceUtil.getAppVersionCode());
            map.put("ver", "2.1.2");
            String strP = ce.j.p(map, "https://api.sec.intl.miui.com/GuardProviderV1/GetResourceZipFile", "7htr5238-a8cf-3k79-ec73-75382145ns5c", new i("antispam_checkupdate"));
            try {
                JSONObject jSONObject = new JSONObject(strP);
                if (jSONObject.optInt("code") == 200) {
                    return jSONObject.getJSONObject("data").optString("fileUrl");
                }
                return null;
            } catch (JSONException e10) {
                Log.w("SmsEngineUpdateManager", " JSONException:  response: " + strP, e10);
                return null;
            }
        }

        private void g(Context context, b bVar) {
            e2.a.v("2.1.2", "start");
            try {
                c();
                String strF = f();
                if (!TextUtils.isEmpty(strF)) {
                    e(context, strF, bVar);
                    return;
                }
                Log.w("SmsEngineUpdateManager", "fail: downloadUrl is null");
                e2.a.v("2.1.2", "fail_getDownloadUrl");
                a.e(context).f49051c = false;
                b bVar2 = this.f49052a;
                if (bVar2 != null) {
                    bVar2.a(0);
                }
            } catch (Exception e10) {
                Log.e("SmsEngineUpdateManager", "Exception when startCheckUpdate", e10);
                e2.a.v("2.1.2", "fail_crash_startCheckUpdate");
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            g(Application.y(), this.f49052a);
            return null;
        }
    }

    public interface b {
        void a(int i10);
    }

    private a(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f49049a = applicationContext;
        this.f49050b = applicationContext.getSharedPreferences("SmsEnginePreference", 0);
    }

    public static a e(Context context) {
        if (f49047d == null) {
            synchronized (a.class) {
                if (f49047d == null) {
                    f49047d = new a(context);
                }
            }
        }
        return f49047d;
    }

    private String f() {
        return this.f49050b.getString("version", "-1");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(String str) {
        this.f49050b.edit().putString("version", str).apply();
    }

    public synchronized void d(b bVar) {
        if (!Build.IS_INTERNATIONAL_BUILD && !this.f49051c) {
            if (g()) {
                Log.w("SmsEngineUpdateManager", "fail: no need update, current version： 2.1.2");
                if (bVar != null) {
                    bVar.a(1);
                }
                return;
            }
            if (w.u() && d.s(this.f49049a)) {
                Log.i("SmsEngineUpdateManager", " update start!");
                this.f49051c = true;
                new AsyncTaskC0491a(this.f49049a, bVar).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                return;
            }
            Log.w("SmsEngineUpdateManager", "fail : no connected");
            if (bVar != null) {
                bVar.a(0);
            }
            return;
        }
        Log.w("SmsEngineUpdateManager", "fail: mUpdating： " + this.f49051c);
        if (bVar != null) {
            bVar.a(3);
        }
    }

    public boolean g() {
        return TextUtils.equals("2.1.2", f());
    }
}
