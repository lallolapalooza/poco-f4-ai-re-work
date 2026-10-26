package com.ss.bytenn;

import android.os.Parcel;
import android.os.Parcelable;
import com.bytedance.covode.number.Covode;
import com.ss.android.ugc.effectmanager.common.BuildConfig;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/work/tmp_trill/classes6.dex */
public class Tensor implements Parcelable {
    public static final Parcelable.Creator<Tensor> CREATOR;
    public int channel;
    public DataFormat dataFormat;
    public DataType dataType;
    public int height;
    public String name;
    public ByteBuffer raw_data;
    public int width;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getChannel() {
        return this.channel;
    }

    public ByteBuffer getData() {
        return this.raw_data;
    }

    public int getHeight() {
        return this.height;
    }

    public String getName() {
        return this.name;
    }

    public int getWidth() {
        return this.width;
    }

    public int getOrdinalOfDataFormat() {
        return this.dataFormat.ordinal();
    }

    public int getOrdinalOfDataType() {
        return this.dataType.ordinal();
    }

    static {
        Covode.recordClassIndex(116705);
        CREATOR = new Parcelable.Creator<Tensor>() { // from class: com.ss.bytenn.Tensor.1
            static {
                Covode.recordClassIndex(116706);
            }

            @Override // android.os.Parcelable.Creator
            public final /* bridge */ /* synthetic */ Tensor[] newArray(int i) {
                return new Tensor[i];
            }

            @Override // android.os.Parcelable.Creator
            public final /* synthetic */ Tensor createFromParcel(Parcel parcel) {
                return new Tensor(parcel);
            }
        };
    }

    public Tensor() {
        this.dataFormat = DataFormat.NCHW;
        this.dataType = DataType.Float;
        this.name = BuildConfig.VERSION_NAME;
    }

    public enum DataFormat {
        NCHW,
        NHWC;

        static {
            Covode.recordClassIndex(116707);
        }
    }

    public enum DataType {
        U8,
        Int8,
        Int16,
        Uint16,
        Float,
        Fp16,
        Double;

        static {
            Covode.recordClassIndex(116708);
        }
    }

    public void setChannel(int i) {
        this.channel = i;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public void setData(ByteBuffer byteBuffer) throws Exception {
        if (byteBuffer.isDirect()) {
            this.raw_data = byteBuffer;
            return;
        }
        throw new Exception("ByteBuffer must be direct");
    }

    public Tensor(Parcel parcel) {
        this.dataFormat = DataFormat.values()[parcel.readInt()];
        this.dataType = DataType.values()[parcel.readInt()];
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.channel = parcel.readInt();
        parcel.readByteArray(null);
        this.raw_data = ByteBuffer.wrap(null);
        this.name = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.dataFormat.ordinal());
        parcel.writeInt(this.dataType.ordinal());
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeInt(this.channel);
        parcel.writeByteArray(new byte[this.raw_data.remaining()]);
        parcel.writeString(this.name);
    }

    public void init(DataFormat dataFormat, DataType dataType, int i, int i2, int i3, ByteBuffer byteBuffer, String str) throws Exception {
        this.dataFormat = dataFormat;
        this.dataType = dataType;
        this.width = i;
        this.height = i2;
        this.channel = i3;
        if (byteBuffer.isDirect()) {
            this.raw_data = byteBuffer;
            this.name = str;
            return;
        }
        throw new Exception("ByteBuffer must be direct");
    }
}
