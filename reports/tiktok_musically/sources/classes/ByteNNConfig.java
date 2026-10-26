package com.ss.bytenn;

import X.7Xf;
import android.os.Parcel;
import android.os.Parcelable;
import com.bytedance.covode.number.Covode;
import com.ss.android.ugc.effectmanager.common.BuildConfig;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/ttdex/classes6.dex */
public class ByteNNConfig implements Parcelable {
    public static final Parcelable.Creator<ByteNNConfig> CREATOR;
    public 7Xf forwardType;
    public String[] inputNames;
    public ByteBuffer model;
    public String modelName;
    public int numThread;
    public String oclKernelBinPath;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getOrdinalOfForwardType() {
        return this.forwardType.ordinal();
    }

    static {
        Covode.recordClassIndex(116706);
        CREATOR = new Parcelable.Creator<ByteNNConfig>() { // from class: com.ss.bytenn.ByteNNConfig.1
            static {
                Covode.recordClassIndex(116707);
            }

            @Override // android.os.Parcelable.Creator
            public final /* bridge */ /* synthetic */ ByteNNConfig[] newArray(int i) {
                return new ByteNNConfig[i];
            }

            @Override // android.os.Parcelable.Creator
            public final /* synthetic */ ByteNNConfig createFromParcel(Parcel parcel) {
                return new ByteNNConfig(parcel);
            }
        };
    }

    public ByteNNConfig() {
        this.numThread = 1;
        this.forwardType = 7Xf.CPU;
        this.oclKernelBinPath = BuildConfig.VERSION_NAME;
        this.modelName = BuildConfig.VERSION_NAME;
    }

    public void setThreadNum(int i) {
        this.numThread = i;
    }

    public ByteNNConfig(Parcel parcel) {
        this.numThread = 1;
        this.forwardType = 7Xf.values()[parcel.readInt()];
        parcel.readByteArray(null);
        this.model = ByteBuffer.wrap(null);
        parcel.readStringArray(this.inputNames);
        this.oclKernelBinPath = parcel.readString();
        this.modelName = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.forwardType.ordinal());
        parcel.writeByteArray(new byte[this.model.remaining()]);
        parcel.readStringArray(this.inputNames);
        parcel.writeStringArray(this.inputNames);
        parcel.writeString(this.oclKernelBinPath);
        parcel.writeString(this.modelName);
    }

    public void init(7Xf r3, ByteBuffer byteBuffer, String[] strArr, String str, String str2) throws Exception {
        this.forwardType = r3;
        if (strArr != null) {
            this.inputNames = strArr;
        } else {
            this.inputNames = new String[0];
        }
        if (byteBuffer.isDirect()) {
            this.model = byteBuffer;
            this.oclKernelBinPath = str;
            this.modelName = str2;
            return;
        }
        throw new Exception("ByteBuffer must be direct");
    }
}
