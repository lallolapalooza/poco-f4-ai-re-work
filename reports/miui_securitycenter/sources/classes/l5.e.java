package l5;

import org.jetbrains.annotations.NotNull;
import p002kotlin.Metadata;
import p002kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001:\u0001\u0006B\u001f\u0012\u0006\u0010\u0011\u001a\u00020\u0005\u0012\u0006\u0010\u001d\u001a\u00020\u0012\u0012\u0006\u0010\u001e\u001a\u00020\u0003¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0016R\u001a\u0010\n\u001a\u00020\u00058\u0006X\u0086D¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\tR\u001a\u0010\r\u001a\u00020\u00058\u0006X\u0086D¢\u0006\f\n\u0004\b\u000b\u0010\u0007\u001a\u0004\b\f\u0010\tR\"\u0010\u0011\u001a\u00020\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u0007\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u0010R\"\u0010\u0017\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\u0013\u001a\u0004\b\u0006\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\"\u0010\u001c\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u000b\u0010\u001a\"\u0004\b\u0018\u0010\u001b¨\u0006!"}, d2 = {"Ll5/e;", "Ll5/a;", "model", "", "d", "", "a", "I", "getLIGHT_NATURE", "()I", "LIGHT_NATURE", "b", "getLIGHT_COLD", "LIGHT_COLD", "c", "setTitleRes", "(I)V", "titleRes", "Ll5/e$a;", "Ll5/e$a;", "()Ll5/e$a;", "setModeValue", "(Ll5/e$a;)V", "modeValue", com.zeus.gmc.sdk.mobileads.columbus.ad.util.recourse.tracker.c.f43114b, "Z", "()Z", "(Z)V", "selected", "modelValue", "select", "<init>", "(ILl5/e$a;Z)V", "app_globalPhoneRelease"}, k = 1, mv = {1, 7, 1})
public final class e extends l5.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    private final int LIGHT_NATURE;

    /* JADX INFO: renamed from: b, reason: collision with root package name and from kotlin metadata */
    private final int LIGHT_COLD;

    /* JADX INFO: renamed from: c, reason: collision with root package name and from kotlin metadata */
    private int titleRes;

    /* JADX INFO: renamed from: d, reason: collision with root package name and from kotlin metadata */
    @NotNull
    private a modeValue;

    /* JADX INFO: renamed from: e, reason: collision with root package name and from kotlin metadata */
    private boolean selected;

    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\u000b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tR\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Ll5/e$a;", "", "", "c", "I", "b", "()I", "value", "<init>", "(Ljava/lang/String;II)V", "LIGHT_WARM", "LIGHT_NATURE", "LIGHT_COLD", "app_globalPhoneRelease"}, k = 1, mv = {1, 7, 1})
    public enum a {
        LIGHT_WARM(0),
        LIGHT_NATURE(1),
        LIGHT_COLD(2);


        /* JADX INFO: renamed from: c, reason: collision with root package name and from kotlin metadata */
        private final int value;

        a(int i10) {
            this.value = i10;
        }

        /* JADX INFO: renamed from: b, reason: from getter */
        public final int getValue() {
            return this.value;
        }
    }

    public e(int i10, @NotNull a modelValue, boolean z10) {
        n.h(modelValue, "modelValue");
        this.LIGHT_NATURE = 1;
        this.LIGHT_COLD = 2;
        this.titleRes = -1;
        a aVar = a.LIGHT_WARM;
        this.titleRes = i10;
        this.modeValue = modelValue;
        this.selected = z10;
    }

    @NotNull
    /* JADX INFO: renamed from: a, reason: from getter */
    public final a getModeValue() {
        return this.modeValue;
    }

    /* JADX INFO: renamed from: b, reason: from getter */
    public final boolean getSelected() {
        return this.selected;
    }

    /* JADX INFO: renamed from: c, reason: from getter */
    public final int getTitleRes() {
        return this.titleRes;
    }

    public boolean d(@NotNull l5.a model) {
        n.h(model, "model");
        return (model instanceof e) && this.modeValue == ((e) model).modeValue;
    }

    public final void e(boolean z10) {
        this.selected = z10;
    }
}
