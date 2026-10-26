package com.miui.mediaeditor.photo.remover.model;

import android.os.MemoryFile;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import androidx.annotation.Keep;
import bp.b;
import com.miui.gallery.util.g;
import java.io.FileInputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
@Keep
public class RemoverNNFData implements Parcelable {
    public static final Parcelable.Creator<RemoverNNFData> CREATOR = new a();
    int count;
    ParcelFileDescriptor fileDescriptor;
    int height;
    int index;
    int length;
    MemoryFile memoryFile;
    byte[] nnf;
    int width;

    public class a implements Parcelable.Creator<RemoverNNFData> {
        @Override // android.os.Parcelable.Creator
        public final RemoverNNFData createFromParcel(Parcel parcel) {
            return new RemoverNNFData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final RemoverNNFData[] newArray(int i10) {
            return new RemoverNNFData[i10];
        }
    }

    public RemoverNNFData() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void getDataFromParcel() {
        FileInputStream fileInputStreamB = b.B(this.fileDescriptor);
        try {
            try {
                try {
                    int i10 = this.count;
                    byte[] bArr = new byte[i10];
                    this.nnf = bArr;
                    fileInputStreamB.read(bArr, 0, i10);
                } catch (IOException e10) {
                    e10.printStackTrace();
                }
            } catch (Exception e11) {
                e11.printStackTrace();
            }
        } finally {
            g.a(fileInputStreamB);
        }
    }

    public void releaseMemoryFile() {
        MemoryFile memoryFile = this.memoryFile;
        if (memoryFile != null) {
            memoryFile.close();
        }
    }

    public void saveDataForParcel() {
        MemoryFile memoryFile;
        int length = this.nnf.length;
        this.count = length;
        this.length = length;
        try {
            memoryFile = new MemoryFile("remove", length);
        } catch (IOException e10) {
            e10.printStackTrace();
            memoryFile = null;
        }
        this.memoryFile = memoryFile;
        try {
            byte[] bArr = this.nnf;
            memoryFile.writeBytes(bArr, 0, 0, bArr.length);
            this.memoryFile.allowPurging(false);
            this.fileDescriptor = b.E(this.memoryFile);
        } catch (IOException e11) {
            e11.printStackTrace();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        saveDataForParcel();
        parcel.writeInt(this.height);
        parcel.writeInt(this.width);
        parcel.writeInt(this.index);
        parcel.writeParcelable(this.fileDescriptor, i10);
        parcel.writeInt(this.length);
        parcel.writeInt(this.count);
    }

    public RemoverNNFData(Parcel parcel) {
        this.height = parcel.readInt();
        this.width = parcel.readInt();
        this.index = parcel.readInt();
        this.fileDescriptor = (ParcelFileDescriptor) parcel.readParcelable(ParcelFileDescriptor.class.getClassLoader());
        this.length = parcel.readInt();
        this.count = parcel.readInt();
        getDataFromParcel();
    }
}
