package d2;

import android.net.Uri;
import android.provider.BaseColumns;
import com.miui.securitycenter.Application;
import java.io.File;
import miui.cloud.CloudPushConstants;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f44060a = new File(Application.y().getCacheDir(), "debug.log").exists();

    /* JADX INFO: renamed from: d2.a$a, reason: collision with other inner class name */
    public static final class C0395a implements BaseColumns {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final Uri f44061b = Uri.parse("content://antispam/report_sms");
    }

    public static final class b implements BaseColumns {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final Uri f44062b = Uri.parse("content://antispam/report_sms_pending");
    }

    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final int f44063a = Integer.valueOf("1").intValue();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final int f44064b = Integer.valueOf(CloudPushConstants.CHANNEL_ID).intValue();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final int f44065c = Integer.valueOf("3").intValue();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final int f44066d = Integer.valueOf("4").intValue();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final int f44067e = Integer.valueOf("5").intValue();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final int f44068f = Integer.valueOf("6").intValue();

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final int f44069g = Integer.valueOf("7").intValue();
    }
}
