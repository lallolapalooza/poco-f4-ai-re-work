package com.xiaomi.aiautobrt;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_securitycoreadd/sources/framework/miui-framework.dex */
public interface IIndividualBrightnessService extends IInterface {
    public static final String DESCRIPTOR = "com.xiaomi.aiautobrt.IIndividualBrightnessService";

    void attach(IIndividualCallback iIndividualCallback) throws RemoteException;

    void detach() throws RemoteException;

    void onEventChanged(IndividualModelEvent individualModelEvent) throws RemoteException;

    void predictBrightness(IndividualModelEvent individualModelEvent) throws RemoteException;

    void provideInterfaceVersion(int i) throws RemoteException;

    public static class Default implements IIndividualBrightnessService {
        @Override // com.xiaomi.aiautobrt.IIndividualBrightnessService
        public void onEventChanged(IndividualModelEvent event) throws RemoteException {
        }

        @Override // com.xiaomi.aiautobrt.IIndividualBrightnessService
        public void predictBrightness(IndividualModelEvent event) throws RemoteException {
        }

        @Override // com.xiaomi.aiautobrt.IIndividualBrightnessService
        public void attach(IIndividualCallback callback) throws RemoteException {
        }

        @Override // com.xiaomi.aiautobrt.IIndividualBrightnessService
        public void detach() throws RemoteException {
        }

        @Override // com.xiaomi.aiautobrt.IIndividualBrightnessService
        public void provideInterfaceVersion(int version) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IIndividualBrightnessService {
        static final int TRANSACTION_attach = 3;
        static final int TRANSACTION_detach = 4;
        static final int TRANSACTION_onEventChanged = 1;
        static final int TRANSACTION_predictBrightness = 2;
        static final int TRANSACTION_provideInterfaceVersion = 5;

        public Stub() {
            attachInterface(this, IIndividualBrightnessService.DESCRIPTOR);
        }

        public static IIndividualBrightnessService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IIndividualBrightnessService.DESCRIPTOR);
            if (iin != null && (iin instanceof IIndividualBrightnessService)) {
                return (IIndividualBrightnessService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onEventChanged";
                case 2:
                    return "predictBrightness";
                case 3:
                    return "attach";
                case 4:
                    return "detach";
                case 5:
                    return "provideInterfaceVersion";
                default:
                    return null;
            }
        }

        public String getTransactionName(int transactionCode) {
            return getDefaultTransactionName(transactionCode);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code >= 1 && code <= 16777215) {
                data.enforceInterface(IIndividualBrightnessService.DESCRIPTOR);
            }
            switch (code) {
                case 1598968902:
                    reply.writeString(IIndividualBrightnessService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            IndividualModelEvent _arg0 = (IndividualModelEvent) data.readTypedObject(IndividualModelEvent.CREATOR);
                            data.enforceNoDataAvail();
                            onEventChanged(_arg0);
                            return true;
                        case 2:
                            IndividualModelEvent _arg1 = (IndividualModelEvent) data.readTypedObject(IndividualModelEvent.CREATOR);
                            data.enforceNoDataAvail();
                            predictBrightness(_arg1);
                            return true;
                        case 3:
                            IIndividualCallback _arg2 = IIndividualCallback.Stub.asInterface(data.readStrongBinder());
                            data.enforceNoDataAvail();
                            attach(_arg2);
                            return true;
                        case 4:
                            detach();
                            return true;
                        case 5:
                            int _arg3 = data.readInt();
                            data.enforceNoDataAvail();
                            provideInterfaceVersion(_arg3);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        private static class Proxy implements IIndividualBrightnessService {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IIndividualBrightnessService.DESCRIPTOR;
            }

            @Override // com.xiaomi.aiautobrt.IIndividualBrightnessService
            public void onEventChanged(IndividualModelEvent event) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                try {
                    _data.writeInterfaceToken(IIndividualBrightnessService.DESCRIPTOR);
                    _data.writeTypedObject(event, 0);
                    this.mRemote.transact(1, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.xiaomi.aiautobrt.IIndividualBrightnessService
            public void predictBrightness(IndividualModelEvent event) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                try {
                    _data.writeInterfaceToken(IIndividualBrightnessService.DESCRIPTOR);
                    _data.writeTypedObject(event, 0);
                    this.mRemote.transact(2, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.xiaomi.aiautobrt.IIndividualBrightnessService
            public void attach(IIndividualCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                try {
                    _data.writeInterfaceToken(IIndividualBrightnessService.DESCRIPTOR);
                    _data.writeStrongInterface(callback);
                    this.mRemote.transact(3, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.xiaomi.aiautobrt.IIndividualBrightnessService
            public void detach() throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                try {
                    _data.writeInterfaceToken(IIndividualBrightnessService.DESCRIPTOR);
                    this.mRemote.transact(4, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.xiaomi.aiautobrt.IIndividualBrightnessService
            public void provideInterfaceVersion(int version) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                try {
                    _data.writeInterfaceToken(IIndividualBrightnessService.DESCRIPTOR);
                    _data.writeInt(version);
                    this.mRemote.transact(5, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }

        public int getMaxTransactionId() {
            return 4;
        }
    }
}
