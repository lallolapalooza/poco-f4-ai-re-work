package I;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioRecord;

/* JADX INFO: compiled from: QAudioRecord.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static AudioAttributes a() {
        return new AudioAttributes.Builder().setUsage(1).setContentType(1).setInternalCapturePreset(1999).build();
    }

    public static AudioFormat b() {
        return new AudioFormat.Builder().setEncoding(2).setSampleRate(16000).setChannelMask(16).build();
    }

    public static AudioRecord c(int i2, int i3) {
        return new AudioRecord(a(), b(), i2, i3);
    }
}
