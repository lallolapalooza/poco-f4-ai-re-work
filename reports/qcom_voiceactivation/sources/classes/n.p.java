package p013n;

import G.q;
import android.content.Context;
import android.content.res.AssetManager;
import com.quicinc.voice.activation.engineservice.OneVoiceModelFileManager$ModelType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;

/* JADX INFO: renamed from: n.p, reason: case insensitive filesystem */
/* JADX INFO: compiled from: OneVoiceModelFileManager.java */
/* JADX INFO: loaded from: classes.dex */
public class C0042p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f1064a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public AssetManager f1065b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f1066c = d();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f1067d = e();

    public C0042p(Context context) {
        this.f1064a = context;
        this.f1065b = context.getAssets();
    }

    public final void a(String str, String str2, OneVoiceModelFileManager$ModelType oneVoiceModelFileManager$ModelType) {
        String str3;
        b(str);
        String str4 = str + "/" + str2;
        int i2 = C0040o.f1062a[oneVoiceModelFileManager$ModelType.ordinal()];
        if (i2 == 1) {
            str3 = "1st_stage/" + str2;
        } else if (i2 == 2) {
            str3 = "1.5_stage/" + str2;
        } else if (i2 != 3) {
            str3 = null;
        } else {
            str3 = "ubm/" + str2;
        }
        q.t("Copying asset file " + str2 + " to " + str4);
        try {
            InputStream inputStreamOpen = this.f1065b.open(str3);
            try {
                Files.copy(inputStreamOpen, Paths.get(str4, new String[0]), StandardCopyOption.REPLACE_EXISTING);
                if (inputStreamOpen != null) {
                    inputStreamOpen.close();
                }
            } catch (Throwable th) {
                if (inputStreamOpen != null) {
                    try {
                        inputStreamOpen.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException e2) {
            q.k("copy asset file error", e2);
        }
    }

    public final void b(String str) {
        File file = new File(str);
        if (file.isDirectory()) {
            return;
        }
        file.mkdirs();
    }

    public final void c() {
        try {
            Files.deleteIfExists(Paths.get(this.f1067d, "uv_temp.udm"));
        } catch (IOException e2) {
            q.k("delete temp general model failed", e2);
        }
    }

    public final String d() {
        return Paths.get(this.f1064a.getFilesDir().getAbsolutePath(), "onevoice_models").toString();
    }

    public final String e() {
        return Paths.get(this.f1064a.getFilesDir().getAbsolutePath(), "general_UV").toString();
    }

    public String f() {
        return Paths.get(this.f1064a.getFilesDir().getAbsolutePath(), "general_UV", "uv.udm").toString();
    }

    public File g(String str, OneVoiceModelFileManager$ModelType oneVoiceModelFileManager$ModelType) {
        String string;
        int i2 = C0040o.f1062a[oneVoiceModelFileManager$ModelType.ordinal()];
        if (i2 == 1) {
            string = Paths.get(this.f1066c, "1st_stage").toString();
        } else if (i2 != 2) {
            string = i2 != 3 ? null : Paths.get(this.f1066c, "ubm").toString();
        } else {
            string = Paths.get(this.f1066c, "1.5_stage").toString();
        }
        File file = Paths.get(string, str + ".uim").toFile();
        if (!file.exists()) {
            a(string, file.getName(), oneVoiceModelFileManager$ModelType);
        }
        return file;
    }

    public String h() {
        ArrayList arrayList = new ArrayList();
        File file = new File(this.f1067d);
        if (file.exists() && file.isDirectory()) {
            try {
                Files.walkFileTree(file.toPath(), new C0038n(this, arrayList));
            } catch (IOException e2) {
                q.k("find genral uv file error", e2);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return (String) arrayList.get(0);
    }

    public boolean i() {
        return h() != null;
    }

    public boolean j() {
        try {
            Path path = Paths.get(this.f1067d, "uv.udm");
            if (!path.toFile().exists()) {
                return false;
            }
            Files.deleteIfExists(path);
            Files.delete(path.getParent());
            return true;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public final boolean k() {
        Path path = Paths.get(this.f1067d, "uv.udm");
        Path path2 = Paths.get(this.f1067d, "uv_temp.udm");
        try {
            Files.deleteIfExists(path);
            Files.move(path2, path2.resolveSibling("uv.udm"), new CopyOption[0]);
            return true;
        } catch (IOException e2) {
            q.k("replace general model failed", e2);
            return false;
        }
    }

    public boolean l(ByteBuffer byteBuffer) {
        q.g("saveGeneralUV isGeneralUVEnrolled()=" + i());
        if (!i()) {
            Path path = Paths.get(this.f1067d, "uv.udm");
            try {
                if (!path.getParent().toFile().exists()) {
                    Files.createDirectories(path.getParent(), new FileAttribute[0]);
                }
                Files.write(path, byteBuffer.array(), StandardOpenOption.CREATE_NEW);
                return true;
            } catch (IOException e2) {
                q.k("save sound model failed", e2);
                return false;
            }
        }
        boolean zM = m(byteBuffer);
        q.g("saveTempFile success=" + zM);
        if (!zM) {
            c();
            return zM;
        }
        boolean zK = k();
        q.g("replaceGeneralUVFile success=" + zK);
        return zK;
    }

    public final boolean m(ByteBuffer byteBuffer) {
        Path path = Paths.get(this.f1067d, "uv_temp.udm");
        try {
            if (!path.getParent().toFile().exists()) {
                Files.createDirectories(path.getParent(), new FileAttribute[0]);
            }
            Files.write(path, byteBuffer.array(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            return true;
        } catch (IOException e2) {
            q.k("save temp general model failed", e2);
            return false;
        }
    }
}
