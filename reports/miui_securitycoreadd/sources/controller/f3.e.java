package f3;

import com.xiaomi.aiautobrt.IndividualModelEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ f f3612b;

    public e(f fVar) {
        this.f3612b = fVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        gb.b bVar;
        String str;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(this.f3612b.f3613a.getFilesDir(), "userdata.csv")));
            try {
                Thread.sleep(1000L);
                int i10 = 0;
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null || i10 > 20000) {
                        break;
                    }
                    if (i10 != 0) {
                        String[] strArrSplit = line.split(",");
                        IndividualModelEvent.Builder builder = new IndividualModelEvent.Builder();
                        builder.setAmbientLux(Float.parseFloat(strArrSplit[0])).setAmbientLuxSpan(Float.parseFloat(strArrSplit[1])).setMixedOrientationAppId(Float.parseFloat(strArrSplit[2])).setAppCategoryId(Float.parseFloat(strArrSplit[3])).setCurrentBrightness(Float.parseFloat(strArrSplit[4])).setCurrentBrightnessSpan(Float.parseFloat(strArrSplit[5]));
                        this.f3612b.f3614b.f(builder.build());
                    }
                    i10++;
                }
                gb.d.f3839a.d("IndividualBrt-TestModelController", "File reading completed, count = " + i10);
                bufferedReader.close();
            } catch (Throwable th) {
                try {
                    bufferedReader.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (FileNotFoundException e10) {
            e = e10;
            bVar = gb.d.f3839a;
            str = "Test file not found!";
            bVar.c("IndividualBrt-TestModelController", str, e);
        } catch (IOException e11) {
            e = e11;
            bVar = gb.d.f3839a;
            str = "Error reading test file!";
            bVar.c("IndividualBrt-TestModelController", str, e);
        } catch (InterruptedException e12) {
            e = e12;
            bVar = gb.d.f3839a;
            str = "Error thread sleep!";
            bVar.c("IndividualBrt-TestModelController", str, e);
        }
    }
}
