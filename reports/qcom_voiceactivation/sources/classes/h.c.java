package p007h;

import android.os.Bundle;
import com.quicinc.voice.activation.algorithm.Algorithm;
import com.quicinc.voice.activation.soundmodel.AlgorithmType;
import com.quicinc.voice.activation.soundmodel.BigSoundModel;
import com.quicinc.voice.activation.soundmodel.ConfigurationId;
import com.quicinc.voice.activation.soundmodel.SoundModel;
import java.util.ArrayList;
import java.util.List;
import p003d.g;

/* JADX INFO: compiled from: AlgorithmSpliter.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static boolean a(BigSoundModel bigSoundModel) {
        for (SoundModel soundModel : bigSoundModel.getSoundModelList()) {
            if (soundModel.getAlgorithmType() == AlgorithmType.f601c || soundModel.getAlgorithmType() == AlgorithmType.f602d || soundModel.getAlgorithmType() == AlgorithmType.f603e) {
                return true;
            }
        }
        return false;
    }

    public static boolean b(SoundModel soundModel) {
        ConfigurationId configurationId = soundModel.getConfigurationId();
        return (configurationId == ConfigurationId.UNKNOWN || configurationId == ConfigurationId.SML_ID_SVA_F_STAGE || configurationId == ConfigurationId.SML_ID_SVA_F_STAGE_INTERNAL || configurationId == ConfigurationId.SML_ID_SVA_S_STAGE_UBM) ? false : true;
    }

    public static boolean c(SoundModel soundModel) {
        return soundModel != null && soundModel.getConfigurationId() == ConfigurationId.SML_ID_SVA_S_STAGE_PDK;
    }

    public static boolean d(SoundModel soundModel) {
        return soundModel != null && soundModel.getConfigurationId() == ConfigurationId.SML_ID_SVA_S_STAGE_USER;
    }

    public static boolean e(SoundModel soundModel) {
        return soundModel.getConfigurationId() == ConfigurationId.SML_ID_SVA_S_STAGE_USER;
    }

    public static boolean f(SoundModel soundModel, boolean z2) {
        return z2 && soundModel.getConfigurationId() == ConfigurationId.SML_ID_SVA_S_STAGE_USER;
    }

    public static List g(BigSoundModel bigSoundModel) {
        ArrayList arrayList = new ArrayList();
        if (bigSoundModel == null) {
            return arrayList;
        }
        boolean zA = a(bigSoundModel);
        Bundle soundModelConfiguration = bigSoundModel.getSoundModelConfiguration();
        boolean zE = g.e().e(bigSoundModel.getSoundModelKey().b()).l().e();
        for (SoundModel soundModel : bigSoundModel.getSoundModelList()) {
            if (b(soundModel) && (!zE || !e(soundModel))) {
                arrayList.add(new Algorithm(soundModel, soundModelConfiguration, f(soundModel, !zA)));
            }
        }
        return arrayList;
    }
}
