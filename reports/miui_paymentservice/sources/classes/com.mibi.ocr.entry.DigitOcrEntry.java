package com.mibi.ocr.entry;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.mibi.ocr.ui.CaptureActivity;
import com.mibi.ocr.ui.CaptureFragment;
import com.mipay.common.data.f;
import com.mipay.common.data.v0;
import com.xiaomi.payment.entry.IEntry;

/* JADX INFO: loaded from: classes.dex */
public class DigitOcrEntry implements IEntry {
    @Override // com.xiaomi.payment.entry.IEntry
    public void a(IEntry.a aVar, Bundle bundle, int i) {
        Intent intent = new Intent();
        intent.setClass(aVar.getContext(), CaptureActivity.class);
        if (bundle == null) {
            bundle = new Bundle();
        }
        intent.putExtra(f.o0, CaptureFragment.class.getName());
        intent.putExtra("payment_fragment_arguments", bundle);
        aVar.a(intent, i);
    }

    @Override // com.xiaomi.payment.entry.IEntry
    public String getId() {
        return com.xiaomi.payment.l.f.Y1;
    }

    @Override // com.xiaomi.payment.entry.IEntry
    public boolean a(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.camera") && !v0.f(context);
    }
}
