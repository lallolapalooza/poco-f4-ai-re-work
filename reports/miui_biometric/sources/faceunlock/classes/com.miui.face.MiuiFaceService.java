package com.miui.face;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.face.Face;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import android.util.Slog;
import android.view.Surface;
import com.android.keyguard.FaceAuthenHelper;
import com.android.keyguard.FaceEnrollHelper;
import com.android.keyguard.KeyguardUtils;
import com.android.keyguard.faceunlock.FaceUnlockManager;
import com.miui.face.util.ClientMonitor;
import com.miui.face.util.PreferencesUtils;
import com.miui.face.util.SharedPrefUtil;
import hidden.HiddenApiBridge;
import hidden.compat.FaceCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MiuiFaceService extends Binder {
    private static final int CODE_ADD_LOCKOUT_RESET_CALLBACK = 16;
    private static final int CODE_AUTHENTICATE = 3;
    private static final int CODE_CANCEL_AUTHENTICATE = 4;
    private static final int CODE_CANCEL_ENROLL = 6;
    private static final int CODE_ENROLL = 5;
    private static final int CODE_EXT_CMD = 101;
    private static final int CODE_GET_AUTHENTICATOR_ID = 14;
    private static final int CODE_GET_ENROLLED_FACE_LIST = 9;

    @Deprecated
    private static final int CODE_GET_VENDOR_INFO = 17;
    private static final int CODE_HAS_ENROLLED_FACES = 12;
    private static final int CODE_POST_ENROLL = 11;
    private static final int CODE_PRE_ENROLL = 10;
    private static final int CODE_PRE_INIT_AUTHEN = 2;
    private static final int CODE_REMOVE = 7;

    @Deprecated
    private static final int CODE_RENAME = 8;
    private static final int CODE_RESET_TIMEOUT = 15;
    public static final int COMMAND_ENROLL_RESET_ENROLL_LOGIC = 1;
    public static final int COMMAND_ENROLL_RESUME_ENROLL_LOGIC = 0;
    public static final int COMMAND_ENROLL_STOP_ENROLL_LOGIC = 2;
    public static final boolean DEBUG = true;
    private static final String FACE_UNLOCK_HAS_FEATURE = "face_unlock_has_feature";
    private static final String FACE_UNLOCK_VALID_FEATURE = "face_unlock_valid_feature";
    private static final String KEY_LAST_START_TIME = "last_start_time";
    private static final String KEY_START_COUNTS = "start_count";
    public static final int MSG_FACE_UNLOCK_RELEASE = 1007;
    public static final int MSG_FACE_UNLOCK_WORK_TIMEOUT = 1008;
    public static final int MSG_ON_ACQUIRED = 2;
    public static final int MSG_ON_AUTHENTICATE = 3;
    public static final int MSG_ON_ENROLL_REUSLT = 1;
    public static final int MSG_ON_ENUMERATED = 6;
    public static final int MSG_ON_ERROR = 4;
    public static final int MSG_ON_EXT_CMD = 100;
    public static final int MSG_ON_LOACKOUTRESET_CALLBACK = 7;
    public static final int MSG_ON_PRE_INIT = 8;
    public static final int MSG_ON_REMOVED = 5;
    private static final String SERVICE_DESCRIPTOR = "miui.face.FaceService";
    private static final String SERVICE_NAME = "miui.face.FaceService";
    public static final String TAG = "miui.face.FaceService";
    private static MiuiFaceService mService;
    private final Context mContext;
    private final FaceUnlockManager mFaceUnlockManager;
    private final ArrayList<IBinder> mLockoutMonitors = new ArrayList<>();
    private final String KEYGUARD_PACK = "com.android.systemui";
    private ClientMonitor mCurrentMonitor = null;
    private final List<ClientMonitor> mPendingClients = new ArrayList();
    private final HandlerThread mWorkerThread = new HandlerThread("serv_worker");
    private Handler mWorkerHandler = null;
    public long mBeginAuth = 0;
    private final Runnable mCheckTemplate = new Runnable() { // from class: com.miui.face.MiuiFaceService.1
        @Override // java.lang.Runnable
        public void run() {
            if (System.currentTimeMillis() - MiuiFaceService.this.getLastStartTime() < 60000) {
                MiuiFaceService.this.addStartCount();
                if (MiuiFaceService.this.getStartCount() > 2) {
                    MiuiFaceService.this.resetStartCount();
                    return;
                }
            }
            MiuiFaceService.this.setLastStartTime();
            int iHasEnrolledFaces = MiuiFaceService.this.hasEnrolledFaces(0, "com.android.systemui");
            if (iHasEnrolledFaces > 0) {
                SharedPrefUtil sharedPrefUtil = new SharedPrefUtil(MiuiFaceService.this.mContext);
                List<Integer> enrolledFaceIds = MiuiFaceService.this.mFaceUnlockManager.getEnrolledFaceIds();
                List<Integer> enrolledFaceIds2 = sharedPrefUtil.getEnrolledFaceIds();
                if (enrolledFaceIds.size() > enrolledFaceIds2.size()) {
                    int size = enrolledFaceIds.size() - enrolledFaceIds2.size();
                    for (int i = 0; i < size; i++) {
                        MiuiFaceService.this.mFaceUnlockManager.deleteFeature(enrolledFaceIds.get(i).intValue());
                    }
                }
                MiuiFaceService.this.mFaceUnlockManager.releaseFaceUnlock();
                Slog.d("miui.face.FaceService", "change face id");
                sharedPrefUtil.changeFaceId(MiuiFaceService.this.mFaceUnlockManager.getEnrolledFaceIds());
                HiddenApiBridge.Settings_Secure_putIntForUser(MiuiFaceService.this.mContext.getContentResolver(), MiuiFaceService.FACE_UNLOCK_HAS_FEATURE, iHasEnrolledFaces, -2);
                if (MiuiFaceService.this.mFaceUnlockManager.hasInit()) {
                    return;
                }
                MiuiFaceService.this.resetStartCount();
                return;
            }
            MiuiFaceService.this.mFaceUnlockManager.deleteFeature();
            MiuiFaceService.this.resetStartCount();
            HiddenApiBridge.Settings_Secure_putIntForUser(MiuiFaceService.this.mContext.getContentResolver(), MiuiFaceService.FACE_UNLOCK_HAS_FEATURE, 0, -2);
            Slog.d("miui.face.FaceService", "no face");
        }
    };
    private final String permStr = "miui.permission.USE_INTERNAL_GENERAL_API";

    public Handler getWorkerHandler() {
        return this.mWorkerHandler;
    }

    private MiuiFaceService(Context context) {
        this.mContext = context;
        this.mFaceUnlockManager = FaceUnlockManager.getInstance(context);
        initWorkerInfo();
        registerReceiver();
    }

    public static synchronized void init(Context context) {
        if (mService == null) {
            MiuiFaceService miuiFaceService = new MiuiFaceService(context);
            mService = miuiFaceService;
            ServiceManager.addService("miui.face.FaceService", miuiFaceService);
        }
    }

    private void initWorkerInfo() {
        this.mWorkerThread.start();
        Handler handler = new Handler(this.mWorkerThread.getLooper()) { // from class: com.miui.face.MiuiFaceService.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    MiuiFaceService.this.onEnrollResult(message.arg1);
                    return;
                }
                if (i == 2) {
                    MiuiFaceService.this.onAcquired(message.arg1);
                    return;
                }
                if (i == 3) {
                    MiuiFaceService.this.onAuthenticate(message);
                    return;
                }
                if (i != 4) {
                    if (i == 5) {
                        MiuiFaceService.this.onRemoved(message.arg1, message.arg2);
                        return;
                    }
                    if (i == 7) {
                        MiuiFaceService.this.onLockoutRest();
                        return;
                    }
                    if (i == 8) {
                        MiuiFaceService.this.onPreInit(((Boolean) message.obj).booleanValue());
                        return;
                    } else if (i == 100) {
                        MiuiFaceService.this.onExtCmd(message.arg1, message.arg2);
                        return;
                    } else if (i != 1008) {
                        return;
                    }
                }
                MiuiFaceService.this.onError(message.arg1, message.arg2);
            }
        };
        this.mWorkerHandler = handler;
        handler.post(this.mCheckTemplate);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v34, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r13v0, types: [com.miui.face.MiuiFaceService] */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX WARN: Type inference failed for: r14v0, types: [int] */
    /* JADX WARN: Type inference failed for: r14v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v13 */
    /* JADX WARN: Type inference failed for: r14v14 */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v5 */
    /* JADX WARN: Type inference failed for: r14v8 */
    /* JADX WARN: Type inference failed for: r14v9 */
    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        ?? r14;
        RectF rectF;
        Rect rect;
        RectF rectF2;
        ?? r13 = this;
        ?? r15 = i;
        try {
            if (r15 == 12) {
                Slog.d("miui.face.FaceService", "CODE_HAS_ENROLLED_FACES");
                parcel.enforceInterface("miui.face.FaceService");
                int iHasEnrolledFaces = r13.hasEnrolledFaces(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(iHasEnrolledFaces);
                return true;
            }
            if (r15 == 101) {
                parcel.enforceInterface("miui.face.FaceService");
                int iExtCmd = extCmd(parcel.readStrongBinder(), parcel.readStrongBinder(), parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(iExtCmd);
                return true;
            }
            try {
                try {
                    switch (r15) {
                        case 2:
                            parcel.enforceInterface("miui.face.FaceService");
                            r13.preInitAuth(parcel.readStrongBinder(), parcel.readString(), parcel.readStrongBinder());
                            parcel2.writeNoException();
                            return true;
                        case 3:
                            Slog.d("miui.face.FaceService", "CODE_AUTHENTICATE");
                            parcel.enforceInterface("miui.face.FaceService");
                            int iAuthenticate = authenticate(parcel.readStrongBinder(), parcel.readLong(), parcel.readInt(), parcel.readStrongBinder(), parcel.readInt(), parcel.readString(), parcel.readInt());
                            parcel2.writeNoException();
                            parcel2.writeInt(iAuthenticate);
                            return true;
                        case 4:
                            Slog.d("miui.face.FaceService", "CODE_CANCEL_AUTHENTICATE");
                            parcel.enforceInterface("miui.face.FaceService");
                            cancelAuthenticate(parcel.readStrongBinder(), parcel.readString());
                            parcel2.writeNoException();
                            return true;
                        case 5:
                            try {
                                Slog.d("miui.face.FaceService", "CODE_ENROLL");
                                parcel.enforceInterface("miui.face.FaceService");
                                IBinder strongBinder = parcel.readStrongBinder();
                                byte[] bArrCreateByteArray = parcel.createByteArray();
                                int i3 = parcel.readInt();
                                IBinder strongBinder2 = parcel.readStrongBinder();
                                int i4 = parcel.readInt();
                                String string = parcel.readString();
                                Surface surface = parcel.readInt() != 0 ? (Surface) Surface.CREATOR.createFromParcel(parcel) : null;
                                if (parcel.readInt() != 0) {
                                    rectF = (RectF) RectF.CREATOR.createFromParcel(parcel);
                                    Log.i("miui.face.FaceService", "detectAreaF: " + rectF);
                                } else {
                                    rectF = null;
                                }
                                if (rectF != null) {
                                    try {
                                        rect = new Rect((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
                                    } catch (RuntimeException e) {
                                        e = e;
                                        r14 = "miui.face.FaceService";
                                        Log.e(r14, "Unexpected remote exception", e);
                                        return super.onTransact(i, parcel, parcel2, i2);
                                    }
                                } else {
                                    rect = null;
                                }
                                try {
                                    if (parcel.readInt() != 0) {
                                        rectF2 = (RectF) RectF.CREATOR.createFromParcel(parcel);
                                        Log.i("miui.face.FaceService", "enrollAreaF: " + rectF2);
                                    } else {
                                        rectF2 = null;
                                    }
                                    int iEnroll = enroll(strongBinder, bArrCreateByteArray, i3, strongBinder2, i4, string, surface, rect, rectF2 != null ? new Rect((int) rectF2.left, (int) rectF2.top, (int) rectF2.right, (int) rectF2.bottom) : null, parcel.readInt(), parcel.readInt());
                                    parcel2.writeNoException();
                                    parcel2.writeInt(iEnroll);
                                    return true;
                                } catch (RuntimeException e2) {
                                    e = e2;
                                    r13 = parcel2;
                                    r15 = "miui.face.FaceService";
                                    r14 = r15;
                                    Log.e(r14, "Unexpected remote exception", e);
                                    return super.onTransact(i, parcel, parcel2, i2);
                                }
                            } catch (RuntimeException e3) {
                                e = e3;
                                r15 = "miui.face.FaceService";
                                r13 = parcel2;
                            }
                            break;
                        case 6:
                            Slog.d("miui.face.FaceService", "CODE_CANCEL_ENROLL");
                            parcel.enforceInterface("miui.face.FaceService");
                            r13.cancelEnrollment(parcel.readStrongBinder());
                            parcel2.writeNoException();
                            return true;
                        case 7:
                            Slog.d("miui.face.FaceService", "CODE_REMOVE");
                            parcel.enforceInterface("miui.face.FaceService");
                            remove(parcel.readStrongBinder(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readStrongBinder());
                            parcel2.writeNoException();
                            return true;
                        case 8:
                            parcel.enforceInterface("miui.face.FaceService");
                            parcel.readInt();
                            parcel.readInt();
                            parcel.readString();
                            parcel2.writeNoException();
                            return true;
                        case 9:
                            Slog.d("miui.face.FaceService", "CODE_GET_ENROLLED_FACE_LIST");
                            parcel.enforceInterface("miui.face.FaceService");
                            List<Face> enrolledFaces = r13.getEnrolledFaces(parcel.readInt(), parcel.readString());
                            parcel2.writeNoException();
                            parcel2.writeTypedList(enrolledFaces);
                            return true;
                        default:
                            switch (r15) {
                                case 15:
                                    Slog.d("miui.face.FaceService", "CODE_RESET_TIMEOUT");
                                    parcel.enforceInterface("miui.face.FaceService");
                                    r13.restTimeout(parcel.createByteArray());
                                    parcel2.writeNoException();
                                    return true;
                                case 16:
                                    Slog.d("miui.face.FaceService", "CODE_ADD_LOCKOUT_RESET_CALLBACK");
                                    parcel.enforceInterface("miui.face.FaceService");
                                    r13.addLockoutResetCallback(parcel.readStrongBinder());
                                    parcel2.writeNoException();
                                    return true;
                                case 17:
                                    parcel.enforceInterface("miui.face.FaceService");
                                    parcel.readString();
                                    parcel2.writeNoException();
                                    parcel2.writeString("null");
                                    return true;
                                default:
                                    Log.e("miui.face.FaceService", "unknown code: " + r15);
                                    return false;
                            }
                    }
                } catch (RuntimeException e4) {
                    e = e4;
                }
            } catch (RuntimeException e5) {
                e = e5;
                r14 = "miui.face.FaceService";
            }
        } catch (RuntimeException e6) {
            e = e6;
            r14 = r15;
        }
    }

    private void checkPermission(String str) {
        this.mContext.enforceCallingOrSelfPermission(str, "Must have " + str + " permission.");
    }

    private void remove(IBinder iBinder, int i, int i2, int i3, IBinder iBinder2) {
        Slog.d("miui.face.FaceService", "remove token:" + iBinder + " faceId:" + i + " groupId:" + i2 + " userId:" + i3 + " receiver:" + iBinder2);
        checkPermission("miui.permission.USE_INTERNAL_GENERAL_API");
        ClientMonitor clientMonitor = new ClientMonitor(iBinder, iBinder2, "", 3);
        this.mCurrentMonitor = clientMonitor;
        clientMonitor.setPreInit(false);
        new FaceAuthenHelper(this, this.mContext).removeFace(i);
    }

    private List<Face> getEnrolledFaces(int i, String str) {
        return new SharedPrefUtil(this.mContext).getEnrolledFaces();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int hasEnrolledFaces(int i, String str) {
        return new SharedPrefUtil(this.mContext).getFacesEnrollCount().intValue();
    }

    private void restTimeout(byte[] bArr) {
        Slog.d("miui.face.FaceService", "restTimeout cryptoToken:" + Arrays.toString(bArr));
        checkPermission("miui.permission.USE_INTERNAL_GENERAL_API");
    }

    private void addLockoutResetCallback(IBinder iBinder) {
        Slog.d("miui.face.FaceService", "addLoackoutResetCallback callback:" + iBinder);
        addLockoutResetMonitor(iBinder);
        getWorkerHandler().sendEmptyMessageDelayed(100, 100L);
    }

    private void addLockoutResetMonitor(IBinder iBinder) {
        if (this.mLockoutMonitors.contains(iBinder)) {
            return;
        }
        this.mLockoutMonitors.add(iBinder);
    }

    private int enroll(IBinder iBinder, byte[] bArr, int i, IBinder iBinder2, int i2, String str, Surface surface, Rect rect, Rect rect2, int i3, int i4) {
        Slog.d("miui.face.FaceService", "enroll, packName: " + str + " receiver:" + iBinder2 + " surface:" + surface + " detectArea:" + rect + ",face++(20, 20, 460, 600),  timeout:" + i4);
        checkPermission("miui.permission.USE_INTERNAL_GENERAL_API");
        ClientMonitor clientMonitor = new ClientMonitor(iBinder, iBinder2, str, 1);
        this.mCurrentMonitor = clientMonitor;
        clientMonitor.setPreInit(false);
        FaceEnrollHelper faceEnrollHelper = new FaceEnrollHelper(this, this.mContext);
        this.mCurrentMonitor.setEnrollHelper(faceEnrollHelper);
        if (i3 == 1) {
            faceEnrollHelper.enroll(surface, rect2, i4);
        } else {
            faceEnrollHelper.enroll(surface, rect, rect2, i3, i4);
        }
        return 0;
    }

    private void cancelEnrollment(IBinder iBinder) {
        Slog.d("miui.face.FaceService", "cancelEnrollment token:" + iBinder);
        checkPermission("miui.permission.USE_INTERNAL_GENERAL_API");
        ClientMonitor clientMonitor = this.mCurrentMonitor;
        if (clientMonitor == null || clientMonitor.getEnrollHelper() == null) {
            return;
        }
        this.mCurrentMonitor.getEnrollHelper().stopFromBp();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAuthenClient(ClientMonitor clientMonitor) {
        if (clientMonitor != null) {
            this.mCurrentMonitor = clientMonitor;
            clientMonitor.startAuthen();
        }
    }

    private void removeClient(ClientMonitor clientMonitor) {
        if (clientMonitor != null) {
            clientMonitor.destroy();
            ClientMonitor clientMonitor2 = this.mCurrentMonitor;
            if (clientMonitor != clientMonitor2 && clientMonitor2 != null) {
                Slog.d("miui.face.FaceService", "Unexpected client: " + clientMonitor.getOwner() + "expected: " + this.mCurrentMonitor.getOwner());
            }
        }
        if (this.mCurrentMonitor != null) {
            Slog.d("miui.face.FaceService", "Done with client: " + (clientMonitor != null ? clientMonitor.getOwner() : "null"));
            this.mCurrentMonitor = null;
        }
        List<ClientMonitor> list = this.mPendingClients;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (ClientMonitor clientMonitor3 : this.mPendingClients) {
            if ("com.android.systemui".equalsIgnoreCase(clientMonitor3.getOwner()) && clientMonitor3.getClientType() == 2) {
                Slog.d("miui.face.FaceService", "start pendingclient com.android.systemui.");
                clientMonitor3.setAuthenHelper(new FaceAuthenHelper(mService, this.mContext));
                startAuthenClient(clientMonitor3);
            } else {
                clientMonitor3.onError(123456L, KeyguardUtils.ERROR_SERVICE_IS_IDLE, 0);
            }
        }
        this.mPendingClients.clear();
    }

    private void preInitAuth(final IBinder iBinder, final String str, final IBinder iBinder2) {
        mService.getWorkerHandler().post(new Runnable() { // from class: com.miui.face.MiuiFaceService.3
            @Override // java.lang.Runnable
            public void run() {
                Slog.d("miui.face.FaceService", "preInitAuth, " + str + (MiuiFaceService.this.mCurrentMonitor == null ? " ok" : " preempt:" + MiuiFaceService.this.mCurrentMonitor.getOwner()));
                if (MiuiFaceService.this.mCurrentMonitor != null) {
                    return;
                }
                MiuiFaceService.this.mCurrentMonitor = new ClientMonitor(iBinder, iBinder2, str, 2);
                MiuiFaceService.this.mCurrentMonitor.setPreInit(true);
                FaceAuthenHelper faceAuthenHelper = new FaceAuthenHelper(MiuiFaceService.mService, MiuiFaceService.this.mContext);
                faceAuthenHelper.tryPreInitAuth();
                MiuiFaceService.this.mCurrentMonitor.setAuthenHelper(faceAuthenHelper);
            }
        });
    }

    private int authenticate(final IBinder iBinder, long j, int i, final IBinder iBinder2, int i2, final String str, final int i3) {
        this.mBeginAuth = System.currentTimeMillis();
        mService.getWorkerHandler().post(new Runnable() { // from class: com.miui.face.MiuiFaceService.4
            @Override // java.lang.Runnable
            public void run() {
                Slog.d("miui.face.FaceService", "authenticate, packName: " + str + " receiver:" + iBinder2 + " timeout:" + i3 + " mCurrentMonitor: " + (MiuiFaceService.this.mCurrentMonitor == null ? null : MiuiFaceService.this.mCurrentMonitor.getOwner()));
                if (MiuiFaceService.this.mCurrentMonitor != null && !MiuiFaceService.this.mCurrentMonitor.isPreInit()) {
                    Slog.d("miui.face.FaceService", "preempt:" + MiuiFaceService.this.mCurrentMonitor.getOwner() + " is authenticating, return");
                    ClientMonitor clientMonitor = new ClientMonitor(iBinder, iBinder2, str, 2);
                    clientMonitor.setTimeout(i3);
                    if (!"com.android.systemui".equalsIgnoreCase(clientMonitor.getOwner())) {
                        clientMonitor.onError(123456L, KeyguardUtils.ERROR_SERVICE_IS_BUSY, 0);
                    }
                    MiuiFaceService.this.mPendingClients.add(clientMonitor);
                    return;
                }
                MiuiFaceService.this.mCurrentMonitor = new ClientMonitor(iBinder, iBinder2, str, 2);
                MiuiFaceService.this.mCurrentMonitor.setAuthenHelper(new FaceAuthenHelper(MiuiFaceService.mService, MiuiFaceService.this.mContext));
                MiuiFaceService.this.mCurrentMonitor.setTimeout(i3);
                MiuiFaceService miuiFaceService = MiuiFaceService.this;
                miuiFaceService.startAuthenClient(miuiFaceService.mCurrentMonitor);
            }
        });
        return 0;
    }

    private void cancelAuthenticate(IBinder iBinder, final String str) {
        mService.getWorkerHandler().post(new Runnable() { // from class: com.miui.face.MiuiFaceService.5
            @Override // java.lang.Runnable
            public void run() {
                Slog.d("miui.face.FaceService", "cancelAuthenticate, packName: " + str + "mCurrentClient: " + (MiuiFaceService.this.mCurrentMonitor != null ? MiuiFaceService.this.mCurrentMonitor.getOwner() : null));
                if (MiuiFaceService.this.mCurrentMonitor == null || !str.equalsIgnoreCase(MiuiFaceService.this.mCurrentMonitor.getOwner()) || MiuiFaceService.this.mCurrentMonitor.getAuthenHelper() == null) {
                    return;
                }
                MiuiFaceService.this.mCurrentMonitor.getAuthenHelper().stopFromBp();
            }
        });
    }

    private int extCmd(IBinder iBinder, IBinder iBinder2, int i, int i2, String str) {
        Slog.d("miui.face.FaceService", "extCmd, cmd: " + i + " param: " + i2 + " " + str + " receiver:" + iBinder2);
        getWorkerHandler().obtainMessage(100, i, i2).sendToTarget();
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLockoutRest() {
        for (int i = 0; i < this.mLockoutMonitors.size(); i++) {
            ClientMonitor.sendLockoutRest(this.mLockoutMonitors.get(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onEnrollResult(int i) {
        Slog.d("miui.face.FaceService", "onEnrollResult resetValidat, " + this.mCurrentMonitor + " faceId:" + i);
        ClientMonitor clientMonitor = this.mCurrentMonitor;
        if (clientMonitor != null) {
            clientMonitor.onEnrollResult(123456L, i, 0, 0);
        }
        if (i != 0) {
            new SharedPrefUtil(this.mContext).saveFacesEnrollSuccess(i);
            HiddenApiBridge.Settings_Secure_putIntForUser(this.mContext.getContentResolver(), FACE_UNLOCK_HAS_FEATURE, hasEnrolledFaces(0, "com.android.systemui"), -2);
            HiddenApiBridge.Settings_Secure_putIntForUser(this.mContext.getContentResolver(), FACE_UNLOCK_VALID_FEATURE, 1, -2);
            Slog.d("miui.face.FaceService", "getFeatureCount = " + this.mFaceUnlockManager.getFeatureCount());
        }
        removeClient(this.mCurrentMonitor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAcquired(int i) {
        Log.d("miui.face.FaceService", "onAcquired  curMonitor:" + this.mCurrentMonitor + " msgId:" + i);
        ClientMonitor clientMonitor = this.mCurrentMonitor;
        if (clientMonitor != null) {
            clientMonitor.onAcquired(123456L, i, 3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAuthenticate(Message message) {
        Slog.d("miui.face.FaceService", "miui.face.timeline, onAuthenticate All Time:" + (System.currentTimeMillis() - this.mBeginAuth) + " , id:" + message.arg1);
        if (this.mCurrentMonitor != null) {
            if (message.arg1 == 0) {
                this.mCurrentMonitor.onAuthenticationFailed(123456L);
            } else {
                this.mCurrentMonitor.onAuthenticationSucceeded(123456L, FaceCompat.newInstance("Miuiface1", 1, message.arg1), 0);
            }
            removeClient(this.mCurrentMonitor);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onError(int i, int i2) {
        Slog.d("miui.face.FaceService", "onError  curMonitor:" + this.mCurrentMonitor + ", vendorCode:" + i2 + ", errorId:" + i);
        ClientMonitor clientMonitor = this.mCurrentMonitor;
        if (clientMonitor != null) {
            clientMonitor.onError(123456L, i, i2);
            removeClient(this.mCurrentMonitor);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRemoved(int i, int i2) {
        Slog.d("miui.face.FaceService", "onRemoved monitor:" + this.mCurrentMonitor + " successId:" + i + ", " + i2);
        new SharedPrefUtil(this.mContext).removeFace(i);
        HiddenApiBridge.Settings_Secure_putIntForUser(this.mContext.getContentResolver(), FACE_UNLOCK_HAS_FEATURE, hasEnrolledFaces(0, "com.android.systemui"), -2);
        ClientMonitor clientMonitor = this.mCurrentMonitor;
        if (clientMonitor != null) {
            clientMonitor.onRemoved(123456L, i, 3, i2);
        }
        removeClient(this.mCurrentMonitor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onExtCmd(int i, int i2) {
        Slog.d("miui.face.FaceService", "onExtCmd  curMonitor:" + this.mCurrentMonitor + ";cmd=" + i);
        ClientMonitor clientMonitor = this.mCurrentMonitor;
        if (clientMonitor != null) {
            if (i == 0) {
                clientMonitor.startNextFaceEnrollStep();
            } else if (i == 1) {
                clientMonitor.resetFaceEnroll();
            }
            this.mCurrentMonitor.onExtCmd(1235);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPreInit(boolean z) {
        StringBuilder sb = new StringBuilder("onPreInit curMonitor:");
        ClientMonitor clientMonitor = this.mCurrentMonitor;
        Slog.d("miui.face.FaceService", sb.append(clientMonitor != null ? clientMonitor.getOwner() : null).append(" hasInit: ").append(z).toString());
        ClientMonitor clientMonitor2 = this.mCurrentMonitor;
        if (clientMonitor2 != null) {
            clientMonitor2.onPreInit(z);
        }
    }

    public long getLastStartTime() {
        return PreferencesUtils.getLong(this.mContext, KEY_LAST_START_TIME, 0L);
    }

    public void setLastStartTime() {
        PreferencesUtils.putLong(this.mContext, KEY_LAST_START_TIME, System.currentTimeMillis());
    }

    public int getStartCount() {
        return PreferencesUtils.getInt(this.mContext, KEY_START_COUNTS, 0);
    }

    public void addStartCount() {
        PreferencesUtils.putInt(this.mContext, KEY_START_COUNTS, getStartCount() + 1);
    }

    public void resetStartCount() {
        PreferencesUtils.putInt(this.mContext, KEY_START_COUNTS, 0);
    }

    private void registerReceiver() {
        ScreenReceiver screenReceiver = new ScreenReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        this.mContext.registerReceiver(screenReceiver, intentFilter);
    }

    public class ScreenReceiver extends BroadcastReceiver {
        public ScreenReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null || !action.equals("android.intent.action.USER_PRESENT")) {
                return;
            }
            Slog.d("miui.face.FaceService", "lock screen passed");
            MiuiFaceService.this.mWorkerHandler.post(new Runnable() { // from class: com.miui.face.MiuiFaceService$ScreenReceiver$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m45lambda$onReceive$0$commiuifaceMiuiFaceService$ScreenReceiver();
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onReceive$0$com-miui-face-MiuiFaceService$ScreenReceiver, reason: not valid java name */
        /* synthetic */ void m45lambda$onReceive$0$commiuifaceMiuiFaceService$ScreenReceiver() {
            if (MiuiFaceService.this.mCurrentMonitor != null && "com.android.systemui".equals(MiuiFaceService.this.mCurrentMonitor.getOwner()) && MiuiFaceService.this.mCurrentMonitor.isPreInit()) {
                MiuiFaceService.this.mCurrentMonitor.getAuthenHelper().releaseOnWorkerThread();
            }
        }
    }
}
