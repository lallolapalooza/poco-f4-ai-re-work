package com.miui.face.util;

import android.hardware.face.Face;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.util.Slog;
import com.android.keyguard.FaceAuthenHelper;
import com.android.keyguard.FaceEnrollHelper;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public class ClientMonitor implements IBinder.DeathRecipient {
    private static final String RECEIVER_DESCRIPTOR = "receiver.FaceService";
    private static final int RECEIVER_ON_AUTHENTICATION_FAILED = 204;
    private static final int RECEIVER_ON_AUTHENTICATION_SUCCEEDED = 203;
    private static final int RECEIVER_ON_ENROLL_RESULT = 201;
    private static final int RECEIVER_ON_ERROR = 205;
    private static final int RECEIVER_ON_EXT_CMD = 301;
    private static final int RECEIVER_ON_LOCKOUT_RESET = 261;
    private static final int RECEIVER_ON_ON_ACQUIRED = 202;
    private static final int RECEIVER_ON_PRE_INIT = 207;
    private static final int RECEIVER_ON_REMOVED = 206;
    public static final int TYPE_AUTHEN = 2;
    public static final int TYPE_ENROLL = 1;
    public static final int TYPE_EXT_CMD = 4;
    public static final int TYPE_REMOVE = 3;
    private FaceAuthenHelper mAuthenHelper;
    private int mClientType;
    private FaceEnrollHelper mEnrollHelper;
    private final String mOwner;
    private IBinder mReceiver;
    private IBinder mToken;
    private int mTimeout = 0;
    private boolean mIsPreInit = false;

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        this.mToken = null;
        this.mReceiver = null;
    }

    public FaceAuthenHelper getAuthenHelper() {
        return this.mAuthenHelper;
    }

    public int getClientType() {
        return this.mClientType;
    }

    public FaceEnrollHelper getEnrollHelper() {
        return this.mEnrollHelper;
    }

    public String getOwner() {
        return this.mOwner;
    }

    public IBinder getReceiver() {
        return this.mReceiver;
    }

    public IBinder getToken() {
        return this.mToken;
    }

    public boolean isPreInit() {
        return this.mIsPreInit;
    }

    public void setAuthenHelper(FaceAuthenHelper faceAuthenHelper) {
        this.mAuthenHelper = faceAuthenHelper;
    }

    public void setEnrollHelper(FaceEnrollHelper faceEnrollHelper) {
        this.mEnrollHelper = faceEnrollHelper;
    }

    public void setPreInit(boolean z) {
        this.mIsPreInit = z;
    }

    public void setReceiver(IBinder iBinder) {
        this.mReceiver = iBinder;
    }

    public void setTimeout(int i) {
        this.mTimeout = i;
    }

    public ClientMonitor(IBinder iBinder, IBinder iBinder2, String str, int i) {
        this.mToken = iBinder;
        this.mReceiver = iBinder2;
        this.mOwner = str;
        this.mClientType = i;
        if (iBinder != null) {
            try {
                iBinder.linkToDeath(this, 0);
            } catch (RemoteException e) {
                Log.e("miui.face.FaceService", "caught remote exception in linkToDeath: ", e);
            }
        }
    }

    public void destroy() {
        IBinder iBinder = this.mToken;
        if (iBinder != null) {
            try {
                iBinder.unlinkToDeath(this, 0);
            } catch (NoSuchElementException unused) {
                Log.e("miui.face.FaceService", "destroy(): " + this + ":", new Exception("here"));
            }
            this.mToken = null;
        }
        this.mReceiver = null;
    }

    protected void finalize() throws Throwable {
        try {
            if (this.mToken != null) {
                Slog.d("miui.face.FaceService", "removing leaked reference: " + this.mToken);
            }
        } finally {
            super.finalize();
        }
    }

    public void startAuthen() {
        FaceAuthenHelper faceAuthenHelper;
        if (this.mClientType != 2 || (faceAuthenHelper = this.mAuthenHelper) == null) {
            return;
        }
        faceAuthenHelper.authen(this.mTimeout);
    }

    public void startNextFaceEnrollStep() {
        FaceEnrollHelper faceEnrollHelper;
        if (this.mClientType != 1 || (faceEnrollHelper = this.mEnrollHelper) == null) {
            return;
        }
        faceEnrollHelper.startNextFaceEnrollStep();
    }

    public void resetFaceEnroll() {
        FaceEnrollHelper faceEnrollHelper;
        if (this.mClientType != 1 || (faceEnrollHelper = this.mEnrollHelper) == null) {
            return;
        }
        faceEnrollHelper.resetFaceEnroll();
    }

    public void onExtCmd(int i) {
        Slog.d("miui.face.FaceService", "onExtCmd  res:" + i + "  receiver:" + this.mReceiver);
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            try {
                if (this.mReceiver != null) {
                    parcelObtain.writeInterfaceToken(RECEIVER_DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mReceiver.transact(RECEIVER_ON_EXT_CMD, parcelObtain, parcelObtain2, 0);
                }
            } catch (Exception e) {
                Log.e("miui.face.FaceService", "mReceiver transact fail. " + e);
            }
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
        }
    }

    public void onEnrollResult(long j, int i, int i2, int i3) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            try {
                if (this.mReceiver != null) {
                    parcelObtain.writeInterfaceToken(RECEIVER_DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    this.mReceiver.transact(201, parcelObtain, parcelObtain2, 0);
                }
            } catch (Exception e) {
                Log.e("miui.face.FaceService", "mReceiver transact fail. " + e);
            }
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
        }
    }

    public void onAcquired(long j, int i, int i2) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            try {
                if (this.mReceiver != null) {
                    parcelObtain.writeInterfaceToken(RECEIVER_DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    this.mReceiver.transact(202, parcelObtain, parcelObtain2, 0);
                }
            } catch (Exception e) {
                Log.e("miui.face.FaceService", "mReceiver transact fail. " + e);
            }
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
        }
    }

    public void onAuthenticationSucceeded(long j, Face face, int i) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            try {
                if (this.mReceiver != null) {
                    parcelObtain.writeInterfaceToken(RECEIVER_DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (face != null) {
                        parcelObtain.writeInt(1);
                        face.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    this.mReceiver.transact(203, parcelObtain, parcelObtain2, 0);
                }
            } catch (Exception e) {
                Log.e("miui.face.FaceService", "mReceiver transact fail. " + e);
            }
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
        }
    }

    public void onAuthenticationFailed(long j) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            try {
                if (this.mReceiver != null) {
                    parcelObtain.writeInterfaceToken(RECEIVER_DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    this.mReceiver.transact(204, parcelObtain, parcelObtain2, 0);
                }
            } catch (Exception e) {
                Log.e("miui.face.FaceService", "mReceiver transact fail. " + e);
            }
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
        }
    }

    public void onError(long j, int i, int i2) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            try {
                if (this.mReceiver != null) {
                    parcelObtain.writeInterfaceToken(RECEIVER_DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    this.mReceiver.transact(RECEIVER_ON_ERROR, parcelObtain, parcelObtain2, 0);
                }
            } catch (Exception e) {
                Log.e("miui.face.FaceService", "mReceiver transact fail. " + e);
            }
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
        }
    }

    public void onRemoved(long j, int i, int i2, int i3) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            try {
                if (this.mReceiver != null) {
                    parcelObtain.writeInterfaceToken(RECEIVER_DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    this.mReceiver.transact(RECEIVER_ON_REMOVED, parcelObtain, parcelObtain2, 0);
                }
            } catch (Exception e) {
                Log.e("miui.face.FaceService", "mReceiver transact fail. " + e);
            }
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
        }
    }

    public void onPreInit(boolean z) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            try {
                if (this.mReceiver != null) {
                    parcelObtain.writeInterfaceToken(RECEIVER_DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.mReceiver.transact(RECEIVER_ON_PRE_INIT, parcelObtain, parcelObtain2, 0);
                }
            } catch (Exception e) {
                Log.e("miui.face.FaceService", "mReceiver transact fail. " + e);
            }
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
        }
    }

    public static void sendLockoutRest(IBinder iBinder) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        if (iBinder != null) {
            try {
                try {
                    parcelObtain.writeInterfaceToken(RECEIVER_DESCRIPTOR);
                    iBinder.transact(RECEIVER_ON_LOCKOUT_RESET, parcelObtain, parcelObtain2, 0);
                } catch (Exception e) {
                    Log.e("miui.face.FaceService", "mReceiver transact fail. " + e);
                }
            } finally {
                parcelObtain.recycle();
                parcelObtain2.recycle();
            }
        }
    }
}
