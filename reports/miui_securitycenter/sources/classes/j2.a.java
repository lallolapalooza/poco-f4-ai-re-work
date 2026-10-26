package j2;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import miui.os.Build;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f47821a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f47824d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private d9.a f47825e;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ExecutorService f47823c = Executors.newSingleThreadExecutor();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ah.a f47822b = new ah.a();

    /* JADX INFO: renamed from: j2.a$a, reason: collision with other inner class name */
    class RunnableC0464a implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f47826c;

        RunnableC0464a(boolean z10) {
            this.f47826c = z10;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (this.f47826c && a.this.f47825e != null) {
                    a.this.f47825e.a();
                }
                a aVar = a.this;
                if (!aVar.l(aVar.f47821a)) {
                    a aVar2 = a.this;
                    if (!aVar2.j(aVar2.f47821a, a.this.f47824d)) {
                        return;
                    }
                }
                a aVar3 = a.this;
                aVar3.f47825e = d9.a.b(aVar3.f47824d);
                Log.d("SmsEngineHandler", "initNewSmsEngine success.");
            } catch (Throwable th2) {
                Log.w("SmsEngineHandler", "initNewSmsEngine failed. " + th2);
            }
        }
    }

    public a(Context context) {
        this.f47821a = context.getApplicationContext();
        try {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.f47821a.getFilesDir().getCanonicalPath());
            String str = File.separator;
            sb2.append(str);
            sb2.append("SmsEngineResource");
            sb2.append(str);
            this.f47824d = sb2.toString();
        } catch (IOException e10) {
            Log.w("SmsEngineHandler", e10);
            StringBuilder sb3 = new StringBuilder();
            sb3.append(this.f47821a.getFilesDir().getAbsolutePath());
            String str2 = File.separator;
            sb3.append(str2);
            sb3.append("SmsEngineResource");
            sb3.append(str2);
            this.f47824d = sb3.toString();
        }
    }

    private void g(File file) {
        File[] fileArrListFiles;
        if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null && fileArrListFiles.length > 0) {
            for (File file2 : fileArrListFiles) {
                g(file2);
            }
        }
        file.delete();
    }

    private void i(ZipInputStream zipInputStream, String str) throws IOException {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                return;
            }
            File file2 = new File(str, nextEntry.getName());
            if (!file2.getCanonicalPath().startsWith(str)) {
                throw new SecurityException("unsafe operation.");
            }
            if (nextEntry.isDirectory()) {
                file2.mkdirs();
            } else {
                if (file2.exists() && file2.isFile() && !file2.delete()) {
                    Log.e("SmsEngineHandler", "delete file " + file2.getName() + " failed. ");
                }
                if (file2.createNewFile()) {
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(file2);
                        try {
                            byte[] bArr = new byte[65536];
                            while (true) {
                                int i10 = zipInputStream.read(bArr);
                                if (i10 == -1) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, i10);
                                fileOutputStream.flush();
                                e.printStackTrace();
                            }
                            fileOutputStream.close();
                        } catch (Throwable th2) {
                            try {
                                fileOutputStream.close();
                            } catch (Throwable th3) {
                                th2.addSuppressed(th3);
                            }
                            throw th2;
                        }
                    } catch (FileNotFoundException e10) {
                        e10.printStackTrace();
                    }
                } else {
                    continue;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean j(Context context, String str) {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(context.openFileInput("resource.zip"));
            try {
                i(zipInputStream, str);
                zipInputStream.close();
                return true;
            } catch (Throwable th2) {
                try {
                    zipInputStream.close();
                } catch (Throwable th3) {
                    th2.addSuppressed(th3);
                }
                throw th2;
            }
        } catch (Exception e10) {
            Log.w("SmsEngineHandler", "init resource failed. " + e10);
            try {
                File file = new File(str);
                if (!file.exists()) {
                    return false;
                }
                g(file);
                return false;
            } catch (Exception unused) {
                Log.w("SmsEngineHandler", "delete resource failed. " + e10);
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean l(Context context) {
        File file = new File(context.getFilesDir() + File.separator + "resource.zip");
        if (!file.exists()) {
            return false;
        }
        try {
            ZipInputStream zipInputStream = new ZipInputStream(context.openFileInput("resource.zip"));
            while (true) {
                try {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry == null) {
                        zipInputStream.close();
                        return true;
                    }
                    if (!nextEntry.isDirectory()) {
                        File file2 = new File(this.f47824d, nextEntry.getName());
                        if (!file2.getCanonicalPath().startsWith(this.f47824d)) {
                            throw new SecurityException("unsafe operation.");
                        }
                        if (!file2.exists() || file2.length() != nextEntry.getSize()) {
                            zipInputStream.close();
                            return false;
                        }
                    }
                } catch (Throwable th2) {
                    try {
                        zipInputStream.close();
                    } catch (Throwable th3) {
                        th2.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        } catch (IOException e10) {
            Log.e("SmsEngineHandler", "check resource failed. " + e10);
            return false;
        } catch (SecurityException unused) {
            file.delete();
            return false;
        }
    }

    private boolean m(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.android.mms", 0).versionCode < 11000249;
        } catch (Exception unused) {
            return true;
        }
    }

    public int h(String str, String str2) {
        long jCurrentTimeMillis;
        StringBuilder sb2;
        String string;
        long jCurrentTimeMillis2;
        StringBuilder sb3;
        long jCurrentTimeMillis3;
        StringBuilder sb4;
        StringBuilder sb5 = new StringBuilder();
        sb5.append("thread is main = ");
        int iA = 0;
        sb5.append(Looper.getMainLooper() == Looper.myLooper());
        Log.e("SmsEngineHandler", sb5.toString());
        try {
            long jCurrentTimeMillis4 = System.currentTimeMillis();
            Log.e("SmsEngineHandler", "init start");
            this.f47825e = d9.a.b(this.f47824d);
            Log.e("SmsEngineHandler", "init end, use time(ms) = " + (System.currentTimeMillis() - jCurrentTimeMillis4));
            d9.a aVar = this.f47825e;
            if (aVar == null) {
                jCurrentTimeMillis3 = System.currentTimeMillis();
                Log.e("SmsEngineHandler", "clear start");
                d9.a aVar2 = this.f47825e;
                if (aVar2 != null) {
                    aVar2.a();
                }
                sb4 = new StringBuilder();
            } else {
                if (!aVar.c(str, str2)) {
                    if (m(this.f47821a) && (iA = this.f47822b.a(this.f47821a, str, str2)) > 0) {
                        Log.w("SmsEngineHandler", "old sms engine blocked.");
                        jCurrentTimeMillis2 = System.currentTimeMillis();
                        Log.e("SmsEngineHandler", "clear start");
                        d9.a aVar3 = this.f47825e;
                        if (aVar3 != null) {
                            aVar3.a();
                        }
                        sb3 = new StringBuilder();
                    } else {
                        if (!this.f47825e.d(str2)) {
                            jCurrentTimeMillis = System.currentTimeMillis();
                            Log.e("SmsEngineHandler", "clear start");
                            d9.a aVar4 = this.f47825e;
                            if (aVar4 != null) {
                                aVar4.a();
                            }
                            sb2 = new StringBuilder();
                            sb2.append("clear end, use time(ms) = ");
                            sb2.append(System.currentTimeMillis() - jCurrentTimeMillis);
                            string = sb2.toString();
                            return iA;
                        }
                        Log.w("SmsEngineHandler", "new sms engine blocked.");
                        jCurrentTimeMillis2 = System.currentTimeMillis();
                        Log.e("SmsEngineHandler", "clear start");
                        d9.a aVar5 = this.f47825e;
                        if (aVar5 != null) {
                            aVar5.a();
                        }
                        sb3 = new StringBuilder();
                    }
                    sb3.append("clear end, use time(ms) = ");
                    sb3.append(System.currentTimeMillis() - jCurrentTimeMillis2);
                    string = sb3.toString();
                    return 2;
                }
                Log.w("SmsEngineHandler", "important sms.");
                jCurrentTimeMillis3 = System.currentTimeMillis();
                Log.e("SmsEngineHandler", "clear start");
                d9.a aVar6 = this.f47825e;
                if (aVar6 != null) {
                    aVar6.a();
                }
                sb4 = new StringBuilder();
            }
            sb4.append("clear end, use time(ms) = ");
            sb4.append(System.currentTimeMillis() - jCurrentTimeMillis3);
            string = sb4.toString();
            return 0;
        } catch (Exception e10) {
            Log.e("SmsEngineHandler", "getSMSJudgeResult failed. " + e10);
            jCurrentTimeMillis = System.currentTimeMillis();
            Log.e("SmsEngineHandler", "clear start");
            d9.a aVar7 = this.f47825e;
            if (aVar7 != null) {
                aVar7.a();
            }
            sb2 = new StringBuilder();
        } finally {
            long jCurrentTimeMillis5 = System.currentTimeMillis();
            Log.e("SmsEngineHandler", "clear start");
            d9.a aVar8 = this.f47825e;
            if (aVar8 != null) {
                aVar8.a();
            }
            Log.e("SmsEngineHandler", "clear end, use time(ms) = " + (System.currentTimeMillis() - jCurrentTimeMillis5));
        }
    }

    public void k(boolean z10) {
        Log.e("SmsEngineHandler", "init " + z10);
        if (!Build.IS_INTERNATIONAL_BUILD && l2.a.e(this.f47821a).g()) {
            this.f47823c.execute(new RunnableC0464a(z10));
        }
    }
}
