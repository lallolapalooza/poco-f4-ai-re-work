package com.xiaomi.mtb.aiservices;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public interface IAiWeakSignal extends IInterface {
    public static final String DESCRIPTOR = "com.xiaomi.mtb.aiservices.IAiWeakSignal";

    public class Default implements IAiWeakSignal {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaomi.mtb.aiservices.IAiWeakSignal
        public String getForecastRes(String str) {
            return null;
        }
    }

    String getForecastRes(String str);

    public abstract class Stub extends Binder implements IAiWeakSignal {
        static final int TRANSACTION_getForecastRes = 1;

        class Proxy implements IAiWeakSignal {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.xiaomi.mtb.aiservices.IAiWeakSignal
            public String getForecastRes(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAiWeakSignal.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return IAiWeakSignal.DESCRIPTOR;
            }
        }

        public Stub() {
            attachInterface(this, IAiWeakSignal.DESCRIPTOR);
        }

        public static IAiWeakSignal asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(IAiWeakSignal.DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IAiWeakSignal)) ? new Proxy(iBinder) : (IAiWeakSignal) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IAiWeakSignal.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IAiWeakSignal.DESCRIPTOR);
                return true;
            }
            if (i2 != 1) {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            String forecastRes = getForecastRes(parcel.readString());
            parcel2.writeNoException();
            parcel2.writeString(forecastRes);
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }
    }
}
