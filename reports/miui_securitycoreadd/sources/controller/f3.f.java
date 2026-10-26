package f3;

import android.content.Context;
import android.os.FileObserver;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class f extends FileObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f3613a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f3614b;

    public f(File file, Context context, c cVar) {
        super(file);
        this.f3613a = context;
        this.f3614b = cVar;
    }

    @Override // android.os.FileObserver
    public final void onEvent(int i10, String str) {
        gb.b bVar;
        String str2;
        if (i10 != 256) {
            if (i10 != 512 || !str.equals("userdata.csv")) {
                return;
            }
            this.f3614b.b();
            bVar = gb.d.f3839a;
            str2 = "End test!";
        } else {
            if (!str.contains("userdata.csv")) {
                return;
            }
            if (new File(this.f3613a.getFilesDir(), "userdata.csv").exists()) {
                gb.d.f3839a.d("IndividualBrt-TestModelController", "Start test!");
                new Thread(new e(this)).start();
                return;
            } else {
                bVar = gb.d.f3839a;
                str2 = "File not exist";
            }
        }
        bVar.d("IndividualBrt-TestModelController", str2);
    }
}
