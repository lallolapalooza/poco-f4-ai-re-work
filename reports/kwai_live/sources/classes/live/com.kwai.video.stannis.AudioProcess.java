package com.kwai.video.stannis;

import com.kuaishou.weapon.gp.k2;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes8.dex */
public class AudioProcess {
    public static final int kPostEffectClassic = 3;
    public static final int kPostEffectHeavy = 0;
    public static final int kPostEffectPitch = 2;
    public static final int kPostEffectStereoEnhance = 4;
    public static final int kPostEffectSurround = 5;
    public static final int kPostEffectVoice = 1;
    public static final int kReverbAmazing = 14;
    public static final int kReverbAmazing2 = 15;
    public static final int kReverbBathRoom = 7;
    public static final int kReverbChorus = 1;
    public static final int kReverbClassic = 2;
    public static final int kReverbConcert = 11;
    public static final int kReverbHeavy = 4;
    public static final int kReverbKTV = 6;
    public static final int kReverbLight = 12;
    public static final int kReverbNone = 0;
    public static final int kReverbOldTimeRadio = 16;
    public static final int kReverbPop = 3;
    public static final int kReverbProfessional = 18;
    public static final int kReverbRecord = 8;
    public static final int kReverbReverb = 5;
    public static final int kReverbStage = 10;
    public static final int kReverbStudio = 9;
    public static final int kReverbSuperStar = 13;
    public static final int kReverbUserDefine = 17;
    public static final int kVeoBadBoy = 7;
    public static final int kVeoCute = 13;
    public static final int kVeoDenon = 10;
    public static final int kVeoDieFat = 6;
    public static final int kVeoEcho = 1;
    public static final int kVeoHeavyMechinery = 11;
    public static final int kVeoHeavyMetal = 9;
    public static final int kVeoLorie = 4;
    public static final int kVeoNone = 0;
    public static final int kVeoPilot = 14;
    public static final int kVeoPowerCurrent = 12;
    public static final int kVeoRobot = 3;
    public static final int kVeoThriller = 2;
    public static final int kVeoUncle = 5;
    public static final int kVeoUserDefine = 15;
    public static final int kVeoXiaoHuangRen = 8;

    public class AgcInterface {
        private int channel_num;
        private long native_instance;
        private int sample_rate;

        public AgcInterface(int i12, int i13) {
            this.native_instance = 0L;
            this.sample_rate = i12;
            this.channel_num = i13;
            this.native_instance = AudioProcess.this.nativeAgcCreate(i12, i13);
        }

        public boolean AgcProcess(byte[] bArr, byte[] bArr2) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                return AudioProcess.this.nativeAgcProcess(j12, bArr, bArr2);
            }
            return false;
        }

        public int GetVadStatus() {
            long j12 = this.native_instance;
            if (j12 != 0) {
                return AudioProcess.this.nativeAgcGetVadStatus(j12);
            }
            return 0;
        }

        public boolean NsProcess(byte[] bArr, byte[] bArr2) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                return AudioProcess.this.nativeNsProcess(j12, bArr, bArr2);
            }
            return false;
        }

        public void ReleaseNative() {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeAgcDestroy(j12);
                this.native_instance = 0L;
            }
        }

        public void SetAgcLevel(int i12, int i13, int i14) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeAgcSetAgcLevel(j12, i12, i13, i14);
            }
        }

        public void SetNsEnable(boolean z12) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeAgcSetNsEnable(j12, z12);
            }
        }

        public void SetNsLevel(int i12) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeAgcSetNsLevel(j12, i12);
            }
        }

        public void SetQuality(int i12) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeAgcSetQuality(j12, i12);
            }
        }
    }

    public class AudioChangeParam {
        public float semitone = 0.0f;
        public float timbre = 0.0f;
        public float param3 = 0.0f;
        public float param4 = 0.0f;
        public float param5 = 0.0f;
        public float param6 = 0.0f;
        public float param7 = 0.0f;
        public float param8 = 0.0f;
        public float param9 = 0.0f;
        public float param10 = 0.0f;

        public AudioChangeParam() {
        }
    }

    public class AudioEffectParam {
        public static final int kEqBandCount = 10;
        public float compressor_gain;
        public float compressor_threshold;
        public float drylevel;
        public float earlylevel;
        public float[] equalizer_gain = new float[10];
        public float predelay;
        public float reverb_damping;
        public float reverb_space;
        public float reverb_time;
        public float taillevel;

        public AudioEffectParam(float f12, float f13, float f14, float f15, float f16, float f17, float f18, float f19, float f22, float[] fArr) {
            this.reverb_space = f12;
            this.predelay = f13;
            this.reverb_time = f14;
            this.reverb_damping = f15;
            this.drylevel = f16;
            this.earlylevel = f17;
            this.taillevel = f18;
            this.compressor_threshold = f19;
            this.compressor_gain = f22;
            for (int i12 = 0; i12 < 10; i12++) {
                this.equalizer_gain[i12] = fArr[i12];
            }
        }
    }

    public class AudioMixInterface {
        private int channel_num;
        private long native_instance;
        private int sample_rate;

        public AudioMixInterface(int i12, int i13) {
            this.native_instance = 0L;
            this.sample_rate = i12;
            this.channel_num = i13;
            this.native_instance = AudioProcess.this.nativeAudioMixerCreate(i12, i13);
        }

        public void Clear() {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeAudioMixerClear(j12);
            }
        }

        public int Process(byte[] bArr, byte[] bArr2, byte[] bArr3, int i12) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                return AudioProcess.this.nativeAudioMixerProcess(j12, bArr, bArr2, bArr3, i12);
            }
            return 0;
        }

        public void ReleaseNative() {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeAudioMixerDestroy(j12);
                this.native_instance = 0L;
            }
        }

        public boolean SetGain(float f12, float f13) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                return AudioProcess.this.nativeAudioMixerSetGain(j12, f12, f13);
            }
            return false;
        }
    }

    public class AudioPitchParam {
        public float average_pitch;
        public float current_pitch;
        public int pitch_decision;

        public AudioPitchParam(int i12, float f12, float f13) {
            this.pitch_decision = i12;
            this.current_pitch = f12;
            this.average_pitch = f13;
        }
    }

    public class AudioPostEffectInterface {
        private int channel_num;
        private long native_instance;
        private int sample_rate;

        public AudioPostEffectInterface(int i12, int i13) {
            this.native_instance = 0L;
            this.native_instance = AudioProcess.this.nativeAudioPostEffectInterface(i12, i13);
            this.sample_rate = i12;
            this.channel_num = i13;
        }

        public void ReleaseNative() {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeAudioPostEffectDestroy(j12);
                this.native_instance = 0L;
            }
        }

        public void clear() {
            AudioProcess.this.nativeAudioPostEffectClear(this.native_instance);
        }

        public int process(short[] sArr, int i12) {
            return AudioProcess.this.nativeAudioPostEffectProcess(this.native_instance, sArr, i12);
        }

        public int setHeadsetMode(int i12) {
            return AudioProcess.this.nativeAudioPostEffectSetHeadsetMode(this.native_instance, i12);
        }

        public int setOutputChannel(int i12) {
            return AudioProcess.this.nativeAudioPostEffectSetOutputChannel(this.native_instance, i12);
        }

        public int setPitch(int i12) {
            return AudioProcess.this.nativeAudioPostEffectSetPitch(this.native_instance, i12);
        }

        public boolean setVoiceEffectOption(int i12) {
            return AudioProcess.this.nativeAudioPostEffectSetOption(this.native_instance, i12);
        }
    }

    public class AudioVoiceDetectInterface {
        private int channel_num;
        private long native_instance;
        private int sample_rate;

        public class AudioVoiceDetectResult {
            public int result_available = 0;
            public float backgroud_prob = 0.0f;
            public float speech_prob = 0.0f;
            public float music_prob = 0.0f;

            public AudioVoiceDetectResult() {
            }
        }

        public AudioVoiceDetectInterface(int i12, int i13, String str) {
            this.native_instance = 0L;
            this.native_instance = AudioProcess.this.nativeAudioVoiceDetectInterface(i12, i13, str);
            this.sample_rate = i12;
            this.channel_num = i13;
        }

        public void ReleaseNative() {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeAudioVoiceDetectDestroy(j12);
                this.native_instance = 0L;
            }
        }

        public AudioVoiceDetectResult process(byte[] bArr, int i12) {
            AudioVoiceDetectResult audioVoiceDetectResult = new AudioVoiceDetectResult();
            float[] fArr = new float[3];
            int iNativeAudioVoiceDetectProcess = AudioProcess.this.nativeAudioVoiceDetectProcess(this.native_instance, bArr, i12, fArr);
            audioVoiceDetectResult.result_available = iNativeAudioVoiceDetectProcess;
            if (iNativeAudioVoiceDetectProcess == 1) {
                audioVoiceDetectResult.backgroud_prob = fArr[0];
                audioVoiceDetectResult.speech_prob = fArr[1];
                audioVoiceDetectResult.music_prob = fArr[2];
            }
            return audioVoiceDetectResult;
        }
    }

    public class AudioWatermarkDecodeInterface {
        private int channel_num;
        private long native_instance;
        private int sample_rate;

        public AudioWatermarkDecodeInterface(int i12, int i13) {
            this.native_instance = 0L;
            this.native_instance = AudioProcess.this.nativeAudioWatermarkDecodeInterface(i12, i13);
            this.sample_rate = i12;
            this.channel_num = i13;
        }

        public void ReleaseNative() {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeAudioWatermarkDecodeDestroy(j12);
                this.native_instance = 0L;
            }
        }

        public int getWaterMark(byte[] bArr) {
            return AudioProcess.this.nativeAudioWatermarkDecodeGetWaterMark(this.native_instance, bArr) > 0 ? 10 : 0;
        }

        public int process(short[] sArr, int i12) {
            return AudioProcess.this.nativeAudioWatermarkDecodeProcess(this.native_instance, sArr, i12);
        }
    }

    public class AudioWatermarkInterface {
        private int channel_num;
        private long native_instance;
        private int sample_rate;

        public AudioWatermarkInterface(int i12, int i13, byte[] bArr) {
            this.native_instance = 0L;
            this.native_instance = AudioProcess.this.nativeAudioWatermarkInterface(i12, i13, bArr);
            this.sample_rate = i12;
            this.channel_num = i13;
        }

        public void ReleaseNative() {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeAudioWatermarkDestroy(j12);
                this.native_instance = 0L;
            }
        }

        public int addProcess(short[] sArr, int i12) {
            return AudioProcess.this.nativeAudioWatermarkAddProcess(this.native_instance, sArr, i12);
        }

        public int fetchRestAduioData(short[] sArr, int i12) {
            return AudioProcess.this.nativeAudioWatermarkFetchRestAduioData(this.native_instance, sArr, i12);
        }
    }

    public class BeatsTrackResults {
        public int bpm;
        public float[] position;
        public float[] strength;

        public BeatsTrackResults() {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface POST_EFFECT_OPTION {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface REVERB_LEVEL {
    }

    public class SpectrumInterface {
        private int channel_num;
        private long native_instance;
        private int sample_rate;

        public SpectrumInterface(int i12, int i13) {
            this.native_instance = 0L;
            this.sample_rate = i12;
            this.channel_num = i13;
            this.native_instance = AudioProcess.this.nativeCreateSpectrumInterface(i12, i13);
        }

        public void ReleaseNative() {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeSpectrumDestroy(j12);
                this.native_instance = 0L;
            }
        }

        public float[] spectrumProcess(byte[] bArr) {
            float[] fArr = new float[256];
            if (AudioProcess.this.nativeSpectrumProcess(this.native_instance, fArr, bArr, bArr.length / (this.channel_num * 2)) == 1) {
                return fArr;
            }
            return null;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VOICE_EFFECT_OPTION {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public native long nativeAgcCreate(int i12, int i13);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAgcDestroy(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeAgcGetVadStatus(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean nativeAgcProcess(long j12, byte[] bArr, byte[] bArr2);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAgcSetAgcLevel(long j12, int i12, int i13, int i14);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAgcSetNsEnable(long j12, boolean z12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAgcSetNsLevel(long j12, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAgcSetQuality(long j12, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAudioGainControlDestroy(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native long nativeAudioGainControlInterface(int i12, int i13, float f12);

    /* JADX INFO: Access modifiers changed from: private */
    public native long nativeAudioGainControlInterface(int i12, int i13, float f12, float f13);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeAudioGainControlProcess(long j12, byte[] bArr, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAudioMixerClear(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native long nativeAudioMixerCreate(int i12, int i13);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAudioMixerDestroy(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeAudioMixerProcess(long j12, byte[] bArr, byte[] bArr2, byte[] bArr3, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean nativeAudioMixerSetGain(long j12, float f12, float f13);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAudioPostEffectClear(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAudioPostEffectDestroy(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native long nativeAudioPostEffectInterface(int i12, int i13);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeAudioPostEffectProcess(long j12, short[] sArr, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeAudioPostEffectSetHeadsetMode(long j12, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean nativeAudioPostEffectSetOption(long j12, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeAudioPostEffectSetOutputChannel(long j12, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeAudioPostEffectSetPitch(long j12, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAudioVoiceDetectDestroy(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native long nativeAudioVoiceDetectInterface(int i12, int i13, String str);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeAudioVoiceDetectProcess(long j12, byte[] bArr, int i12, float[] fArr);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeAudioWatermarkAddProcess(long j12, short[] sArr, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAudioWatermarkDecodeDestroy(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeAudioWatermarkDecodeGetWaterMark(long j12, byte[] bArr);

    /* JADX INFO: Access modifiers changed from: private */
    public native long nativeAudioWatermarkDecodeInterface(int i12, int i13);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeAudioWatermarkDecodeProcess(long j12, short[] sArr, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAudioWatermarkDestroy(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeAudioWatermarkFetchRestAduioData(long j12, short[] sArr, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native long nativeAudioWatermarkInterface(int i12, int i13, byte[] bArr);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAutoTuneDestroy(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeAutuTuneGetResult(long j12, float[] fArr, String str, float f12, float f13);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeAutuTuneGetResultByData(long j12, float[] fArr, byte[] bArr, int i12, int i13, float f12, float f13);

    /* JADX INFO: Access modifiers changed from: private */
    public native long nativeAutuTuneInterface(int i12, int i13);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeBeatsTrackDestroy(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeBeatsTrackGetBeats(long j12, float[] fArr, float[] fArr2, int[] iArr, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeBeatsTrackProcess(long j12, byte[] bArr, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native long nativeCreateBeatsTrackInterface(int i12, int i13);

    /* JADX INFO: Access modifiers changed from: private */
    public native long nativeCreatePreciseVadInterface(int i12, int i13);

    /* JADX INFO: Access modifiers changed from: private */
    public native long nativeCreateSpectrumInterface(int i12, int i13);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeDspClear(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native long nativeDspCreate(int i12, int i13);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeDspDestroy(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeDspFreePitch(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native AudioEffectParam nativeDspGetParameters(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native AudioPitchParam nativeDspGetPitch(long j12, byte[] bArr, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeDspInitPitch(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeDspProcess(long j12, byte[] bArr, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeDspSetOutChannels(long j12, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeDspSetParameters(long j12, AudioEffectParam audioEffectParam);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeDspSetPitch(long j12, float f12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeDspSetVcoMode(long j12, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeDspSetVcoParameters(long j12, AudioChangeParam audioChangeParam);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeDspSetVeoMode(long j12, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean nativeNsProcess(long j12, byte[] bArr, byte[] bArr2);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativePitchChangeClear(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native long nativePitchChangeCreate(int i12, int i13);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativePitchChangeDestroy(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativePitchChangeProcess(long j12, byte[] bArr, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean nativePitchChangeSetPitch(long j12, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativePreciseVadDestroy(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativePreciseVadProcess(long j12, byte[] bArr, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeSpectrumDestroy(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeSpectrumProcess(long j12, float[] fArr, byte[] bArr, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeTempoBpmDetect(long j12, byte[] bArr, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native long nativeTempoCreate(int i12, int i13);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeTempoDestroy(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeTempoGetBeatsNum(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeTempoGetBeatsResult(long j12, float[] fArr, float[] fArr2, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeTempoProcess(long j12, byte[] bArr, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeTempoSetRate(long j12, float f12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeTempoSetTempo(long j12, float f12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeTimbreChangeClear(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native long nativeTimbreChangeCreate(int i12, int i13);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeTimbreChangeDestroy(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeTimbreChangeProcess(long j12, byte[] bArr, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean nativeTimbreChangeSetTimbre(long j12, int i12);

    public class AutoTuneInterface {
        private long native_instance;
        private int sample_rate = k2.Z1;
        private int channel_num = 1;

        public AutoTuneInterface() {
            this.native_instance = 0L;
            this.native_instance = AudioProcess.this.nativeAutuTuneInterface(k2.Z1, 1);
        }

        public int getResult(float[] fArr, String str, float f12, float f13) {
            int iNativeAutuTuneGetResult = AudioProcess.this.nativeAutuTuneGetResult(this.native_instance, fArr, str, f12, f13);
            AudioProcess.this.nativeAutoTuneDestroy(this.native_instance);
            return iNativeAutuTuneGetResult;
        }

        public int getResult(float[] fArr, byte[] bArr, int i12, int i13, float f12, float f13) {
            int iNativeAutuTuneGetResultByData = AudioProcess.this.nativeAutuTuneGetResultByData(this.native_instance, fArr, bArr, i12, i13, f12, f13);
            AudioProcess.this.nativeAutoTuneDestroy(this.native_instance);
            return iNativeAutuTuneGetResultByData;
        }
    }

    public class DspInterface {
        private int channel_num;
        private long native_instance;
        private int sample_rate;

        public DspInterface(int i12, int i13) {
            this.native_instance = 0L;
            this.native_instance = AudioProcess.this.nativeDspCreate(i12, i13);
            this.sample_rate = i12;
            this.channel_num = i13;
        }

        public void Clear() {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeDspClear(j12);
            }
        }

        public void FreePitch() {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeDspFreePitch(j12);
            }
        }

        public AudioEffectParam GetParameters() {
            long j12 = this.native_instance;
            if (j12 != 0) {
                return AudioProcess.this.nativeDspGetParameters(j12);
            }
            return null;
        }

        public AudioPitchParam GetPitch(byte[] bArr) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                return AudioProcess.this.nativeDspGetPitch(j12, bArr, bArr.length / (this.channel_num * 2));
            }
            return null;
        }

        public int InitPitch() {
            long j12 = this.native_instance;
            if (j12 != 0) {
                return AudioProcess.this.nativeDspInitPitch(j12);
            }
            return -1;
        }

        public int Process(byte[] bArr) {
            long j12 = this.native_instance;
            if (j12 == 0) {
                return 0;
            }
            return AudioProcess.this.nativeDspProcess(j12, bArr, bArr.length / (this.channel_num * 2));
        }

        public void ReleaseNative() {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeDspDestroy(j12);
                this.native_instance = 0L;
            }
        }

        public void SetOutChannels(int i12) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeDspSetOutChannels(j12, i12);
            }
        }

        public void SetParameters(AudioEffectParam audioEffectParam) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeDspSetParameters(j12, audioEffectParam);
            }
        }

        public void SetPitch(float f12) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeDspSetPitch(j12, f12);
            }
        }

        public void SetVcoMode(int i12) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeDspSetVcoMode(j12, i12);
            }
        }

        public void SetVcoParameters(AudioChangeParam audioChangeParam) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeDspSetVcoParameters(j12, audioChangeParam);
            }
        }

        public void SetVeoMode(int i12) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeDspSetVeoMode(j12, i12);
            }
        }

        public AudioPitchParam GetPitch(byte[] bArr, int i12) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                return AudioProcess.this.nativeDspGetPitch(j12, bArr, i12);
            }
            return null;
        }

        public int Process(byte[] bArr, int i12) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                return AudioProcess.this.nativeDspProcess(j12, bArr, i12);
            }
            return 0;
        }
    }

    public class AudioGainControlInterface {
        private int channel_num;
        private long native_instance;
        private int sample_rate;

        public AudioGainControlInterface(int i12, int i13, float f12) {
            this.native_instance = 0L;
            this.native_instance = AudioProcess.this.nativeAudioGainControlInterface(i12, i13, f12);
            this.sample_rate = i12;
            this.channel_num = i13;
        }

        public void ReleaseNative() {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeAudioGainControlDestroy(j12);
                this.native_instance = 0L;
            }
        }

        public int process(byte[] bArr) {
            long j12 = this.native_instance;
            if (j12 == 0) {
                return 0;
            }
            return AudioProcess.this.nativeAudioGainControlProcess(j12, bArr, bArr.length / (this.channel_num * 2));
        }

        public int process(byte[] bArr, int i12) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                return AudioProcess.this.nativeAudioGainControlProcess(j12, bArr, i12);
            }
            return 0;
        }

        public AudioGainControlInterface(int i12, int i13, float f12, float f13) {
            this.native_instance = 0L;
            this.native_instance = AudioProcess.this.nativeAudioGainControlInterface(i12, i13, f12, f13);
            this.sample_rate = i12;
            this.channel_num = i13;
        }
    }

    public class BeatsTrackInterface {
        private int channel_num;
        private long native_instance;
        private int sample_rate;

        public BeatsTrackInterface(int i12, int i13) {
            this.native_instance = 0L;
            this.native_instance = AudioProcess.this.nativeCreateBeatsTrackInterface(i12, i13);
            this.sample_rate = i12;
            this.channel_num = i13;
        }

        public void ReleaseNative() {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeBeatsTrackDestroy(j12);
                this.native_instance = 0L;
            }
        }

        public BeatsTrackResults getBeats(int i12) {
            long j12 = this.native_instance;
            if (j12 == 0) {
                return null;
            }
            float[] fArr = new float[i12];
            float[] fArr2 = new float[i12];
            int[] iArr = new int[1];
            int iNativeBeatsTrackGetBeats = AudioProcess.this.nativeBeatsTrackGetBeats(j12, fArr, fArr2, iArr, i12);
            if (iNativeBeatsTrackGetBeats <= 0) {
                return null;
            }
            BeatsTrackResults beatsTrackResults = AudioProcess.this.new BeatsTrackResults();
            beatsTrackResults.position = new float[iNativeBeatsTrackGetBeats];
            beatsTrackResults.strength = new float[iNativeBeatsTrackGetBeats];
            for (int i13 = 0; i13 < iNativeBeatsTrackGetBeats; i13++) {
                beatsTrackResults.position[i13] = fArr[i13];
                beatsTrackResults.strength[i13] = fArr2[i13];
            }
            beatsTrackResults.bpm = iArr[0];
            return beatsTrackResults;
        }

        public int process(byte[] bArr) {
            long j12 = this.native_instance;
            if (j12 == 0) {
                return 0;
            }
            return AudioProcess.this.nativeBeatsTrackProcess(j12, bArr, bArr.length / (this.channel_num * 2));
        }

        public int process(byte[] bArr, int i12) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                return AudioProcess.this.nativeBeatsTrackProcess(j12, bArr, i12);
            }
            return 0;
        }
    }

    public class PitchChangeInterface {
        private int channel_num;
        private long native_instance;
        private int sample_rate;

        public PitchChangeInterface(int i12, int i13) {
            this.native_instance = 0L;
            this.sample_rate = i12;
            this.channel_num = i13;
            this.native_instance = AudioProcess.this.nativePitchChangeCreate(i12, i13);
        }

        public void Clear() {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativePitchChangeClear(j12);
            }
        }

        public int Process(byte[] bArr) {
            long j12 = this.native_instance;
            if (j12 == 0) {
                return 0;
            }
            return AudioProcess.this.nativePitchChangeProcess(j12, bArr, bArr.length / (this.channel_num * 2));
        }

        public void ReleaseNative() {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativePitchChangeDestroy(j12);
                this.native_instance = 0L;
            }
        }

        public boolean SetPitch(int i12) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                return AudioProcess.this.nativePitchChangeSetPitch(j12, i12);
            }
            return false;
        }

        public int Process(byte[] bArr, int i12) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                return AudioProcess.this.nativePitchChangeProcess(j12, bArr, i12);
            }
            return 0;
        }
    }

    public class PreciseVadInterface {
        private int channel_num;
        private long native_instance;
        private int sample_rate;

        public PreciseVadInterface(int i12, int i13) {
            this.native_instance = 0L;
            this.sample_rate = i12;
            this.channel_num = i13;
            this.native_instance = AudioProcess.this.nativeCreatePreciseVadInterface(i12, i13);
        }

        public int Process(byte[] bArr) {
            long j12 = this.native_instance;
            if (j12 == 0) {
                return -1;
            }
            return AudioProcess.this.nativePreciseVadProcess(j12, bArr, bArr.length / (this.channel_num * 2));
        }

        public void ReleaseNative() {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativePreciseVadDestroy(j12);
                this.native_instance = 0L;
            }
        }

        public int Process(byte[] bArr, int i12) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                return AudioProcess.this.nativePreciseVadProcess(j12, bArr, i12);
            }
            return -1;
        }
    }

    public class TempoInterface {
        private int channel_num;
        private long native_instance;
        private int sample_rate;

        public TempoInterface(int i12, int i13) {
            this.native_instance = 0L;
            this.native_instance = AudioProcess.this.nativeTempoCreate(i12, i13);
            this.sample_rate = i12;
            this.channel_num = i13;
        }

        public int DetectBpm(byte[] bArr) {
            long j12 = this.native_instance;
            if (j12 == 0) {
                return 0;
            }
            return AudioProcess.this.nativeTempoBpmDetect(j12, bArr, bArr.length / (this.channel_num * 2));
        }

        public float[][] GetBeats() {
            int iNativeTempoGetBeatsNum;
            long j12 = this.native_instance;
            if (j12 == 0 || (iNativeTempoGetBeatsNum = AudioProcess.this.nativeTempoGetBeatsNum(j12)) <= 0) {
                return null;
            }
            float[][] fArr = (float[][]) Array.newInstance((Class<?>) float.class, 2, iNativeTempoGetBeatsNum);
            AudioProcess.this.nativeTempoGetBeatsResult(this.native_instance, fArr[0], fArr[1], iNativeTempoGetBeatsNum);
            return fArr;
        }

        public int Process(byte[] bArr) {
            long j12 = this.native_instance;
            if (j12 == 0) {
                return 0;
            }
            return AudioProcess.this.nativeTempoProcess(j12, bArr, bArr.length / (this.channel_num * 2));
        }

        public void ReleaseNative() {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeTempoDestroy(j12);
                this.native_instance = 0L;
            }
        }

        public void SetRate(float f12) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeTempoSetRate(j12, f12);
            }
        }

        public void SetTempo(float f12) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeTempoSetTempo(j12, f12);
            }
        }

        public int DetectBpm(byte[] bArr, int i12) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                return AudioProcess.this.nativeTempoBpmDetect(j12, bArr, i12);
            }
            return 0;
        }

        public int Process(byte[] bArr, int i12) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                return AudioProcess.this.nativeTempoProcess(j12, bArr, i12);
            }
            return 0;
        }
    }

    public class TimbreChangeInterface {
        private int channel_num;
        private long native_instance;
        private int sample_rate;

        public TimbreChangeInterface(int i12, int i13) {
            this.native_instance = 0L;
            this.sample_rate = i12;
            this.channel_num = i13;
            this.native_instance = AudioProcess.this.nativeTimbreChangeCreate(i12, i13);
        }

        public void Clear() {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeTimbreChangeClear(j12);
            }
        }

        public int Process(byte[] bArr) {
            long j12 = this.native_instance;
            if (j12 == 0) {
                return 0;
            }
            return AudioProcess.this.nativeTimbreChangeProcess(j12, bArr, bArr.length / (this.channel_num * 2));
        }

        public void ReleaseNative() {
            long j12 = this.native_instance;
            if (j12 != 0) {
                AudioProcess.this.nativeTimbreChangeDestroy(j12);
                this.native_instance = 0L;
            }
        }

        public boolean SetTimbre(int i12) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                return AudioProcess.this.nativeTimbreChangeSetTimbre(j12, i12);
            }
            return false;
        }

        public int Process(byte[] bArr, int i12) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                return AudioProcess.this.nativeTimbreChangeProcess(j12, bArr, i12);
            }
            return 0;
        }
    }
}
