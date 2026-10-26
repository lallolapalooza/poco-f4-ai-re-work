package com.xiaomi.aiautobrt;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_securitycoreadd/sources/framework/miui-framework.dex */
public interface IIndividualCallback extends IInterface {
    public static final String DESCRIPTOR = "com.xiaomi.aiautobrt.IIndividualCallback";

    void onAbTestExperimentUpdated(int i, int i2) throws RemoteException;

    void onPredictFinished(float f, float f2, float f3) throws RemoteException;

    void onTrainFinished() throws RemoteException;

    void onTrainIndicatorsFinished(IndividualTrainEvent individualTrainEvent) throws RemoteException;

    void onUpdateExperiment(int i, boolean z) throws RemoteException;

    public static class Default implements IIndividualCallback {
        @Override // com.xiaomi.aiautobrt.IIndividualCallback
        public void onTrainFinished() throws RemoteException {
        }

        @Override // com.xiaomi.aiautobrt.IIndividualCallback
        public void onPredictFinished(float lux, float appId, float brightness) throws RemoteException {
        }

        @Override // com.xiaomi.aiautobrt.IIndividualCallback
        public void onUpdateExperiment(int expId, boolean enable) throws RemoteException {
        }

        @Override // com.xiaomi.aiautobrt.IIndividualCallback
        public void onTrainIndicatorsFinished(IndividualTrainEvent event) throws RemoteException {
        }

        @Override // com.xiaomi.aiautobrt.IIndividualCallback
        public void onAbTestExperimentUpdated(int expId, int flag) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IIndividualCallback {
        static final int TRANSACTION_onAbTestExperimentUpdated = 5;
        static final int TRANSACTION_onPredictFinished = 2;
        static final int TRANSACTION_onTrainFinished = 1;
        static final int TRANSACTION_onTrainIndicatorsFinished = 4;
        static final int TRANSACTION_onUpdateExperiment = 3;

        public Stub() {
            attachInterface(this, IIndividualCallback.DESCRIPTOR);
        }

        public static IIndividualCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IIndividualCallback.DESCRIPTOR);
            if (iin != null && (iin instanceof IIndividualCallback)) {
                return (IIndividualCallback) iin;
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
                    return "onTrainFinished";
                case 2:
                    return "onPredictFinished";
                case 3:
                    return "onUpdateExperiment";
                case 4:
                    return InterfaceVersionHelper.INTERFACE_NAME_ON_TRAIN_INDICATORS_FINISHED;
                case 5:
                    return InterfaceVersionHelper.INTERFACE_NAME_ON_AB_TEST_EXPERIMENT_UPDATED;
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
                data.enforceInterface(IIndividualCallback.DESCRIPTOR);
            }
            switch (code) {
                case 1598968902:
                    reply.writeString(IIndividualCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            onTrainFinished();
                            return true;
                        case 2:
                            float _arg0 = data.readFloat();
                            float _arg1 = data.readFloat();
                            float _arg2 = data.readFloat();
                            data.enforceNoDataAvail();
                            onPredictFinished(_arg0, _arg1, _arg2);
                            return true;
                        case 3:
                            int _arg3 = data.readInt();
                            boolean _arg4 = data.readBoolean();
                            data.enforceNoDataAvail();
                            onUpdateExperiment(_arg3, _arg4);
                            return true;
                        case 4:
                            IndividualTrainEvent _arg5 = (IndividualTrainEvent) data.readTypedObject(IndividualTrainEvent.CREATOR);
                            data.enforceNoDataAvail();
                            onTrainIndicatorsFinished(_arg5);
                            return true;
                        case 5:
                            int _arg6 = data.readInt();
                            int _arg7 = data.readInt();
                            data.enforceNoDataAvail();
                            onAbTestExperimentUpdated(_arg6, _arg7);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        private static class Proxy implements IIndividualCallback {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IIndividualCallback.DESCRIPTOR;
            }

            @Override // com.xiaomi.aiautobrt.IIndividualCallback
            public void onTrainFinished() throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                try {
                    _data.writeInterfaceToken(IIndividualCallback.DESCRIPTOR);
                    this.mRemote.transact(1, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.xiaomi.aiautobrt.IIndividualCallback
            public void onPredictFinished(float lux, float appId, float brightness) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                try {
                    _data.writeInterfaceToken(IIndividualCallback.DESCRIPTOR);
                    _data.writeFloat(lux);
                    _data.writeFloat(appId);
                    _data.writeFloat(brightness);
                    this.mRemote.transact(2, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.xiaomi.aiautobrt.IIndividualCallback
            public void onUpdateExperiment(int expId, boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                try {
                    _data.writeInterfaceToken(IIndividualCallback.DESCRIPTOR);
                    _data.writeInt(expId);
                    _data.writeBoolean(enable);
                    this.mRemote.transact(3, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.xiaomi.aiautobrt.IIndividualCallback
            public void onTrainIndicatorsFinished(IndividualTrainEvent event) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                try {
                    _data.writeInterfaceToken(IIndividualCallback.DESCRIPTOR);
                    _data.writeTypedObject(event, 0);
                    this.mRemote.transact(4, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.xiaomi.aiautobrt.IIndividualCallback
            public void onAbTestExperimentUpdated(int expId, int flag) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                try {
                    _data.writeInterfaceToken(IIndividualCallback.DESCRIPTOR);
                    _data.writeInt(expId);
                    _data.writeInt(flag);
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
