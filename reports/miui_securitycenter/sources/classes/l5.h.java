package l5;

import com.miui.maml.folme.AnimatedProperty;
import miui.cloud.CloudPushConstants;
import org.jetbrains.annotations.NotNull;
import p002kotlin.Metadata;
import p002kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0016\u0018\u00002\u00020\u0001:\u0001\nB'\u0012\u0006\u0010\u0010\u001a\u00020\t\u0012\u0006\u0010\u0017\u001a\u00020\u0011\u0012\u0006\u0010'\u001a\u00020\u0018\u0012\u0006\u0010(\u001a\u00020\u0003¢\u0006\u0004\b)\u0010*J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0003H\u0016R\"\u0010\u0010\u001a\u00020\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\"\u0010\u0017\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\n\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\"\u0010\u001e\u001a\u00020\u00188\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0019\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\"\u0010$\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001f\u0010!\"\u0004\b\"\u0010#R\"\u0010&\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\f\u0010 \u001a\u0004\b\u0012\u0010!\"\u0004\b%\u0010#¨\u0006+"}, d2 = {"Ll5/h;", "Ll5/a;", "model", "", CloudPushConstants.WATERMARK_TYPE.GLOBAL, "f", "checked", "Ljh/t;", "i", "", "a", "I", com.zeus.gmc.sdk.mobileads.columbus.ad.util.recourse.tracker.c.f43114b, "()I", "setTitleRes", "(I)V", "titleRes", "", "b", "Ljava/lang/String;", "()Ljava/lang/String;", "setDescRes", "(Ljava/lang/String;)V", "descRes", "Ll5/h$a;", "c", "Ll5/h$a;", "()Ll5/h$a;", "setModeValue", "(Ll5/h$a;)V", "modeValue", "d", "Z", "()Z", "j", "(Z)V", "selected", AnimatedProperty.PROPERTY_NAME_H, "enable", "modelValue", "select", "<init>", "(ILjava/lang/String;Ll5/h$a;Z)V", "app_globalPhoneRelease"}, k = 1, mv = {1, 7, 1})
public class h extends l5.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    private int titleRes;

    /* JADX INFO: renamed from: b, reason: collision with root package name and from kotlin metadata */
    @NotNull
    private String descRes;

    /* JADX INFO: renamed from: c, reason: collision with root package name and from kotlin metadata */
    @NotNull
    private a modeValue;

    /* JADX INFO: renamed from: d, reason: collision with root package name and from kotlin metadata */
    private boolean selected;

    /* JADX INFO: renamed from: e, reason: collision with root package name and from kotlin metadata */
    private boolean enable;

    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tR\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Ll5/h$a;", "", "", "c", "Ljava/lang/String;", "b", "()Ljava/lang/String;", "value", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "SINGLE", "MULTI", "SURROUND", "OFF", "SMART_FRAMES_INSERTION", "SUPER_RESOLUTION", "app_globalPhoneRelease"}, k = 1, mv = {1, 7, 1})
    public enum a {
        SINGLE("single_on"),
        MULTI("multi_on"),
        SURROUND("surround_on"),
        OFF("off"),
        SMART_FRAMES_INSERTION("smart_frames_insertion"),
        SUPER_RESOLUTION("super_ratio");


        /* JADX INFO: renamed from: c, reason: collision with root package name and from kotlin metadata */
        @NotNull
        private final String value;

        a(String str) {
            this.value = str;
        }

        @NotNull
        /* JADX INFO: renamed from: b, reason: from getter */
        public final String getValue() {
            return this.value;
        }
    }

    public h(int i10, @NotNull String descRes, @NotNull a modelValue, boolean z10) {
        n.h(descRes, "descRes");
        n.h(modelValue, "modelValue");
        this.titleRes = -1;
        this.descRes = "";
        a aVar = a.SINGLE;
        this.titleRes = i10;
        this.descRes = descRes;
        this.modeValue = modelValue;
        this.selected = z10;
        this.enable = this.enable;
    }

    @NotNull
    /* JADX INFO: renamed from: a, reason: from getter */
    public final String getDescRes() {
        return this.descRes;
    }

    /* JADX INFO: renamed from: b, reason: from getter */
    public final boolean getEnable() {
        return this.enable;
    }

    @NotNull
    /* JADX INFO: renamed from: c, reason: from getter */
    public final a getModeValue() {
        return this.modeValue;
    }

    /* JADX INFO: renamed from: d, reason: from getter */
    public final boolean getSelected() {
        return this.selected;
    }

    /* JADX INFO: renamed from: e, reason: from getter */
    public final int getTitleRes() {
        return this.titleRes;
    }

    public boolean f() {
        return false;
    }

    public boolean g(@NotNull l5.a model) {
        n.h(model, "model");
        return (model instanceof h) && this.modeValue == ((h) model).modeValue;
    }

    public final void h(boolean z10) {
        this.enable = z10;
    }

    public void i(boolean z10) {
    }

    public final void j(boolean z10) {
        this.selected = z10;
    }
}
