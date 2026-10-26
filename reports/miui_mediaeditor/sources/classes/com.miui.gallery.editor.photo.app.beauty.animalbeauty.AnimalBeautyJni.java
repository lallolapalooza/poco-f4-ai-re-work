package com.miui.gallery.editor.photo.app.beauty.animalbeauty;

import android.graphics.Bitmap;
import androidx.annotation.Keep;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Keep
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0011\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0086 J\u0019\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0086 JI\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0002H\u0086 J\t\u0010\u0014\u001a\u00020\u0004H\u0086 ¨\u0006\u0017"}, d2 = {"Lcom/miui/gallery/editor/photo/app/beauty/animalbeauty/AnimalBeautyJni;", "", "", "libDir", "Lyq/d;", "getLibDir", "Landroid/graphics/Bitmap;", "inputBitmap", "maskBitmap", "", "runSegmentModels", "mskBitmap", "outputBitmap", "", "textureProgress", "brightnessProgress", "beautyProgress", "saveFlag", "tag", "adjustAllBeauty", "releaseAll", "<init>", "()V", "animalbeauty_release"}, k = 1, mv = {1, 8, 0})
public final class AnimalBeautyJni {
    public static final AnimalBeautyJni INSTANCE = new AnimalBeautyJni();

    private AnimalBeautyJni() {
    }

    public final native void adjustAllBeauty(Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, int i10, int i11, int i12, boolean z, String str);

    public final native void getLibDir(String str);

    public final native void releaseAll();

    public final native boolean runSegmentModels(Bitmap inputBitmap, Bitmap maskBitmap);
}
