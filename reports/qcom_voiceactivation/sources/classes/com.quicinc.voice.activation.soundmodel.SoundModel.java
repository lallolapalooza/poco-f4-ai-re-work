package com.quicinc.voice.activation.soundmodel;

/* JADX INFO: loaded from: classes.dex */
public class SoundModel implements Comparable {
    private AlgorithmType mAlgorithmType;
    private ConfigurationId mConfigurationId;
    private byte[] mData;

    public AlgorithmType getAlgorithmType() {
        return this.mAlgorithmType;
    }

    public ConfigurationId getConfigurationId() {
        return this.mConfigurationId;
    }

    public byte[] getData() {
        return this.mData;
    }

    public int getSize() {
        byte[] bArr = this.mData;
        if (bArr == null) {
            return 0;
        }
        return bArr.length;
    }

    public void setAlgorithmType(AlgorithmType algorithmType) {
        this.mAlgorithmType = algorithmType;
        this.mConfigurationId = algorithmType.d();
    }

    public void setData(byte[] bArr) {
        this.mData = bArr;
    }

    @Override // java.lang.Comparable
    public int compareTo(SoundModel soundModel) {
        return Integer.compare(getConfigurationId().c(), soundModel.getConfigurationId().c());
    }

    public void setAlgorithmType(int i2, int i3, int i4) {
        ConfigurationId configurationIdB = ConfigurationId.b(i2);
        this.mConfigurationId = configurationIdB;
        this.mAlgorithmType = AlgorithmType.b(configurationIdB, i3, i4);
    }
}
