package com.xiaomi.ai;

import android.content.Context;
import android.location.Location;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import com.xiaomi.ai.api.common.Instruction;
import com.xiaomi.ai.mibrain.GetAnonymousAuthorizationInterface;
import com.xiaomi.ai.mibrain.MibrainException;
import com.xiaomi.ai.mibrain.MibrainOauthManager;
import com.xiaomi.ai.mibrain.MibrainRequest;
import com.xiaomi.ai.mibrain.MibrainRequestListener;
import com.xiaomi.ai.mibrain.MibrainRequestParams;
import com.xiaomi.ai.mibrain.MibrainRequestParamsBuilder;
import com.xiaomi.ai.mibrain.Mibrainsdk;
import com.xiaomi.ai.utils.Log;
import com.xiaomi.ai.utils.UploadHelper;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class c extends SpeechEngine {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f17106b = "MiSpeechSDK:MiAiEngine";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final int f17107f = 4;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final int f17108g = 10;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final int f17109h = 5;
    private com.xiaomi.ai.streamplayer.k.a A;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public GetAnonymousAuthorizationInterface f17110a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private SpannableStringBuilder f17111c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f17112d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f17113e;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private com.xiaomi.ai.streamplayer.k f17114i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private volatile boolean f17115j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private a f17116k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private AudioManager f17117l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private MibrainRequest f17118m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private e f17119n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private final Object f17120o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final Object f17121p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private String f17122q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private volatile SpeechEngine.ParamBuilder f17123r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private m f17124s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private int f17125t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private String f17126u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private String f17127v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private String f17128w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private int f17129x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private ExecutorService f17130y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private AtomicBoolean f17131z;

    public class a extends Thread {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private MibrainRequest f17133b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f17134c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private SpeechEngine.ParamBuilder f17135d;

        public a(MibrainRequest mibrainRequest, SpeechEngine.ParamBuilder paramBuilder) {
            this.f17133b = mibrainRequest;
            this.f17135d = paramBuilder;
        }

        private void a(b bVar) {
            byte[] bArr;
            if (bVar == null || (bArr = bVar.f17136a) == null) {
                return;
            }
            float fA = com.xiaomi.ai.utils.n.a(bArr, bArr.length);
            if (this.f17135d.needAsr) {
                c.this.notifyAsrBufferReceived(bVar.f17136a);
                c.this.notifyAsrRmsChanged(fA);
            }
            if (this.f17135d.needVpRec) {
                c.this.notifyVoiceRecBufferReceived(bVar.f17136a);
                c.this.notifyVoiceRecRmsChanged(fA);
            }
            if (this.f17135d.needVpReg) {
                c.this.notifyVoiceRegBufferReceived(bVar.f17136a);
                c.this.notifyVoiceRegRmsChanged(fA);
            }
        }

        private void a(e eVar) {
            while (eVar.c()) {
                a(eVar.b());
            }
            eVar.a(c.this.new b(new byte[0], 0, 0, true));
            eVar.f17151b.clear();
        }

        private boolean a() {
            SpeechEngine.ParamBuilder paramBuilder = this.f17135d;
            AsrRequest asrRequest = paramBuilder.asrRequest;
            if (asrRequest != null && asrRequest.f17074d == AsrRequest.DataInputMode.DATA_INPUT_MODE_RECORDER) {
                return true;
            }
            VoiceprintRegisterRequest voiceprintRegisterRequest = paramBuilder.voiceprintRegisterRequest;
            if (voiceprintRegisterRequest != null && voiceprintRegisterRequest.mode == VoiceprintRegisterRequest.DataInputMode.DATA_INPUT_MODE_RECORDER) {
                return true;
            }
            VoiceprintRecognizeRequest voiceprintRecognizeRequest = paramBuilder.voiceprintRecognizeRequest;
            return voiceprintRecognizeRequest != null && voiceprintRecognizeRequest.mode == VoiceprintRecognizeRequest.DataInputMode.DATA_INPUT_MODE_RECORDER;
        }

        private void b(e eVar) {
            synchronized (c.class) {
                if (isInterrupted()) {
                    return;
                }
                c.this.a(com.xiaomi.ai.utils.h.a("recordingToBuffer", -16711936));
                int minBufferSize = AudioRecord.getMinBufferSize(16000, 16, 2);
                AudioRecord audioRecord = new AudioRecord(1, 16000, 16, 2, minBufferSize);
                if (audioRecord.getState() != 1) {
                    synchronized (c.this.f17120o) {
                        if (c()) {
                            return;
                        }
                        c.this.a("recorder init failed");
                        SpeechError speechError = new SpeechError(SpeechEngine.ProcessStage.STAGE_REQUESTING);
                        speechError.errCode = 5;
                        speechError.errType = 2;
                        speechError.errMsg = "Fail to init recorder";
                        c.this.notifyError(this.f17135d, speechError);
                        return;
                    }
                }
                try {
                    audioRecord.startRecording();
                    short[] sArr = new short[minBufferSize];
                    while (c.this.f17115j && !isInterrupted() && eVar.c()) {
                        try {
                            try {
                                int i10 = audioRecord.read(sArr, 0, minBufferSize);
                                if (i10 <= 0) {
                                    c.this.f17115j = false;
                                    Log.MiLogE(c.f17106b, "audio read size =0");
                                    try {
                                        audioRecord.stop();
                                    } catch (IllegalStateException e10) {
                                        c.this.a("IllegalStateException: ", e10);
                                    }
                                    audioRecord.release();
                                    return;
                                }
                                byte[] bArrA = com.xiaomi.ai.utils.e.a(sArr, i10);
                                b bVar = c.this.new b(bArrA, 0, bArrA.length, false);
                                eVar.a(bVar);
                                a(bVar);
                            } catch (Exception e11) {
                                Log.MiLogW(c.f17106b, "InterruptedException at recordingToBuffer: ", e11);
                                try {
                                    audioRecord.stop();
                                } catch (IllegalStateException e12) {
                                    c.this.a("IllegalStateException: ", e12);
                                }
                            }
                        } catch (Throwable th2) {
                            try {
                                audioRecord.stop();
                            } catch (IllegalStateException e13) {
                                c.this.a("IllegalStateException: ", e13);
                            }
                            audioRecord.release();
                            throw th2;
                        }
                    }
                    eVar.a(c.this.new b(new byte[0], 0, 0, true));
                    try {
                        audioRecord.stop();
                    } catch (IllegalStateException e14) {
                        c.this.a("IllegalStateException: ", e14);
                    }
                    audioRecord.release();
                    return;
                } catch (IllegalStateException e15) {
                    synchronized (c.this.f17120o) {
                        if (c()) {
                            return;
                        }
                        c.this.a("IllegalStateException: ", e15);
                        audioRecord.release();
                        SpeechError speechError2 = new SpeechError(SpeechEngine.ProcessStage.STAGE_REQUESTING);
                        speechError2.errMsg = e15.toString();
                        speechError2.errCode = 11;
                        speechError2.errType = 2;
                        c.this.notifyError(this.f17135d, speechError2);
                        return;
                    }
                }
                throw th;
            }
        }

        private boolean b() {
            SpeechEngine.ParamBuilder paramBuilder = this.f17135d;
            AsrRequest asrRequest = paramBuilder.asrRequest;
            if (asrRequest != null && asrRequest.f17074d == AsrRequest.DataInputMode.DATA_INPUT_MODE_BUFFER) {
                return true;
            }
            VoiceprintRegisterRequest voiceprintRegisterRequest = paramBuilder.voiceprintRegisterRequest;
            if (voiceprintRegisterRequest != null && voiceprintRegisterRequest.mode == VoiceprintRegisterRequest.DataInputMode.DATA_INPUT_MODE_BUFFER) {
                return true;
            }
            VoiceprintRecognizeRequest voiceprintRecognizeRequest = paramBuilder.voiceprintRecognizeRequest;
            return voiceprintRecognizeRequest != null && voiceprintRecognizeRequest.mode == VoiceprintRecognizeRequest.DataInputMode.DATA_INPUT_MODE_BUFFER;
        }

        private boolean c() {
            return (c.this.f17123r == this.f17135d && c.this.f17118m == this.f17133b) ? false : true;
        }

        private boolean d() {
            synchronized (c.this.f17120o) {
                if (c()) {
                    return true;
                }
                c.this.notifyAsrReadyForSpeech();
                return false;
            }
        }

        private boolean e() {
            synchronized (c.this.f17120o) {
                if (c()) {
                    return true;
                }
                c.this.notifyAsrBeginningOfSpeech();
                return false;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void f() {
            synchronized (c.this.f17120o) {
                boolean z10 = true;
                if (this.f17134c || c()) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("call failed isNotifyEnd ");
                    sb2.append(this.f17134c);
                    sb2.append("mMibrainRequest==request");
                    if (this.f17133b != c.this.f17118m) {
                        z10 = false;
                    }
                    sb2.append(z10);
                    Log.MiLogW(c.f17106b, sb2.toString());
                } else {
                    c.this.notifyAsrEndOfSpeech();
                    this.f17134c = true;
                }
            }
        }

        private boolean g() {
            synchronized (c.this.f17120o) {
                if (c()) {
                    return true;
                }
                c.this.notifyVoiceRecBeginningOfSpeech();
                return false;
            }
        }

        private void h() {
            synchronized (c.this.f17120o) {
                boolean z10 = true;
                if (this.f17134c || c()) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("call failed isNotifyEnd ");
                    sb2.append(this.f17134c);
                    sb2.append("mMibrainRequest==request");
                    if (this.f17133b != c.this.f17118m) {
                        z10 = false;
                    }
                    sb2.append(z10);
                    Log.MiLogW(c.f17106b, sb2.toString());
                } else {
                    c.this.notifyVoiceRecEndOfSpeech();
                    this.f17134c = true;
                }
            }
        }

        private boolean i() {
            synchronized (c.this.f17120o) {
                if (c()) {
                    return true;
                }
                c.this.notifyVoiceRegBeginningOfSpeech();
                return false;
            }
        }

        private void j() {
            synchronized (c.this.f17120o) {
                boolean z10 = true;
                if (this.f17134c || c()) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("call failed isNotifyEnd ");
                    sb2.append(this.f17134c);
                    sb2.append("mMibrainRequest==request");
                    if (this.f17133b != c.this.f17118m) {
                        z10 = false;
                    }
                    sb2.append(z10);
                    Log.MiLogE(c.f17106b, sb2.toString());
                } else {
                    c.this.notifyVoiceRegEndOfSpeech();
                    this.f17134c = true;
                }
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            e eVar;
            if (isInterrupted() || (eVar = c.this.f17119n) == null) {
                return;
            }
            if (this.f17135d.needAsr && d()) {
                return;
            }
            if (this.f17135d.needAsr && e()) {
                return;
            }
            if (this.f17135d.needVpRec && g()) {
                return;
            }
            if (this.f17135d.needVpReg && i()) {
                return;
            }
            try {
                if (a()) {
                    eVar.d();
                }
                if (a()) {
                    b(eVar);
                } else {
                    if (!b()) {
                        throw new IllegalArgumentException("date input mode error");
                    }
                    a(eVar);
                }
                if (!b()) {
                    eVar.e();
                }
                if (this.f17135d.needAsr) {
                    f();
                }
                if (this.f17135d.needVpRec) {
                    h();
                }
                if (this.f17135d.needVpReg) {
                    j();
                }
            } catch (Throwable th2) {
                if (!b()) {
                    eVar.e();
                }
                throw th2;
            }
        }
    }

    public class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public byte[] f17136a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f17137b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f17138c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public EnumC0168c f17139d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f17140e;

        public b(EnumC0168c enumC0168c) {
            this.f17139d = enumC0168c;
        }

        public b(String str, EnumC0168c enumC0168c) {
            this.f17140e = str;
            this.f17139d = enumC0168c;
        }

        public b(byte[] bArr, int i10, int i11, boolean z10) {
            this.f17136a = bArr;
            if (bArr == null) {
                throw new NullPointerException("addBuffer params error, buffer is null ");
            }
            if (i11 < 0 || i10 < 0) {
                throw new IllegalArgumentException("addBuffer params error,offset < 0 or len <0");
            }
            if (i11 + i10 > bArr.length) {
                throw new IllegalArgumentException("addBuffer params error,offset + len > bytes.length");
            }
            byte[] bArr2 = new byte[i11];
            this.f17136a = bArr2;
            System.arraycopy(bArr, i10, bArr2, 0, i11);
            this.f17137b = z10;
            this.f17139d = EnumC0168c.DATA;
        }

        public b(byte[] bArr, int i10, int i11, boolean z10, boolean z11) {
            this.f17136a = bArr;
            if (bArr == null) {
                throw new NullPointerException("addBuffer params error, buffer is null ");
            }
            if (i11 < 0 || i10 < 0) {
                throw new IllegalArgumentException("addBuffer params error,offset < 0 or len <0");
            }
            if (i11 + i10 > bArr.length) {
                throw new IllegalArgumentException("addBuffer params error,offset + len > bytes.length");
            }
            byte[] bArr2 = new byte[i11];
            this.f17136a = bArr2;
            System.arraycopy(bArr, i10, bArr2, 0, i11);
            this.f17137b = z10;
            this.f17138c = z11;
            this.f17139d = EnumC0168c.DATA;
        }
    }

    /* JADX INFO: renamed from: com.xiaomi.ai.c$c, reason: collision with other inner class name */
    public enum EnumC0168c {
        DATA,
        FLAG_ABANDON_START,
        FLAG_ABANDON_END,
        NLP_DATA,
        VAD_BEGIN
    }

    public class d implements MibrainRequestListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private MibrainRequest f17144b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private a f17145c;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private SpeechEngine.ParamBuilder f17147e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private String f17149g;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f17146d = false;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private volatile SpeechEngine.ProcessStage f17148f = SpeechEngine.ProcessStage.STAGE_REQUESTING;

        public d(SpeechEngine.ParamBuilder paramBuilder, MibrainRequest mibrainRequest) {
            this.f17144b = mibrainRequest;
            this.f17147e = paramBuilder;
        }

        private SpeechError a(JSONObject jSONObject) {
            JSONObject jSONObject2;
            int i10;
            SpeechError speechError;
            String strOptString;
            SpeechEngine.ParamBuilder paramBuilder = this.f17147e;
            if (paramBuilder.needEvent && paramBuilder.eventRequest != null) {
                return null;
            }
            try {
                String string = jSONObject.getJSONObject("meta").getString("type");
                if ("ERROR".equals(string)) {
                    JSONObject jSONObject3 = jSONObject.getJSONObject(com.xiaomi.onetrack.api.b.I);
                    int i11 = jSONObject3.getInt("error_code");
                    String string2 = jSONObject3.getString(rg.j.d.f52974k);
                    SpeechError speechError2 = new SpeechError(this.f17148f);
                    speechError2.errType = 1;
                    speechError2.errCode = i11;
                    speechError2.errMsg = string2;
                    return speechError2;
                }
                if (!"TRANSACTION_BEGIN".equals(string) && !"RESULT_VOR_FINAL".equals(string) && !"RESULT_VOR_QUERY".equals(string) && !"RESULT_VOR_DEL".equals(string) && !"RESULT_VOR_REG".equals(string) && !"RESULT_VOR_REG_PARTIAL".equals(string) && !"RESULT_VOR_REG_FINAL".equals(string) && !"RESULT_ASR_PARTIAL".equals(string) && !"RESULT_ASR_FINAL".equals(string) && !"RESULT_NLP".equals(string) && !"RESULT_TTS_BEGIN".equals(string) && !"RESULT_TTS_END".equals(string) && !"TRANSACTION_END".equals(string) && !"RESULT_FILE_STORED".equals(string)) {
                    Log.MiLogE(c.f17106b, "unkown type result = " + jSONObject);
                    return null;
                }
                boolean z10 = false;
                switch (k.f17183a[this.f17148f.ordinal()]) {
                    case 1:
                        if (!"TRANSACTION_BEGIN".equals(string)) {
                            z10 = true;
                        }
                        break;
                    case 2:
                        if (!"RESULT_ASR_PARTIAL".equals(string) && !"RESULT_ASR_FINAL".equals(string) && !"RESULT_FILE_STORED".equals(string)) {
                            z10 = true;
                        }
                        break;
                    case 3:
                        boolean z11 = !"RESULT_NLP".equals(string);
                        if (!this.f17146d) {
                            z10 = z11;
                        } else {
                            this.f17148f = SpeechEngine.ProcessStage.STAGE_TTS;
                        }
                        break;
                    case 4:
                        if (!"RESULT_VOR_FINAL".equals(string)) {
                            z10 = true;
                        }
                        break;
                    case 5:
                        if (!"RESULT_VOR_REG_FINAL".equals(string) && !"RESULT_VOR_REG_PARTIAL".equals(string) && !"TRANSACTION_END".equals(string)) {
                            z10 = true;
                        }
                        break;
                    case 6:
                        if (!"RESULT_TTS_BEGIN".equals(string) && !"RESULT_TTS_END".equals(string) && !"TRANSACTION_END".equals(string)) {
                            z10 = true;
                        }
                        break;
                    case 7:
                        if (!"RESULT_VOR_DEL".equals(string)) {
                            z10 = true;
                        }
                        break;
                    case 8:
                        if (!"RESULT_VOR_QUERY".equals(string)) {
                            z10 = true;
                        }
                        break;
                }
                if (z10) {
                    speechError = new SpeechError(this.f17148f);
                    speechError.errType = 2;
                    speechError.errCode = 3;
                    strOptString = "Internal state error";
                } else {
                    if (this.f17148f != SpeechEngine.ProcessStage.STAGE_NLP || (i10 = (jSONObject2 = jSONObject.getJSONObject(com.xiaomi.onetrack.api.b.I).getJSONObject("status")).getInt("code")) == 200) {
                        return null;
                    }
                    speechError = new SpeechError(this.f17148f);
                    speechError.errType = 1;
                    speechError.errCode = i10;
                    strOptString = jSONObject2.optString("error_details");
                }
                speechError.errMsg = strOptString;
                return speechError;
            } catch (JSONException e10) {
                c.this.a("JSONException: ", e10);
                SpeechError speechError3 = new SpeechError(this.f17148f);
                speechError3.errType = 2;
                speechError3.errCode = 2;
                speechError3.errMsg = e10.toString();
                return speechError3;
            }
        }

        /* JADX WARN: Code duplicated, block: B:318:0x0576 A[Catch: all -> 0x0581, TryCatch #1 {, blocks: (B:316:0x0570, B:318:0x0576, B:320:0x0578, B:321:0x057f), top: B:327:0x0570 }] */
        /* JADX WARN: Code duplicated, block: B:320:0x0578 A[Catch: all -> 0x0581, TryCatch #1 {, blocks: (B:316:0x0570, B:318:0x0576, B:320:0x0578, B:321:0x057f), top: B:327:0x0570 }] */
        /* JADX WARN: Code duplicated, block: B:327:0x0570 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        private void a(MibrainRequest mibrainRequest, String str) {
            SpeechError speechError;
            c cVar;
            String str2;
            if (b(mibrainRequest)) {
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                a(mibrainRequest, jSONObject);
                SpeechError speechErrorA = a(jSONObject);
                if (speechErrorA != null) {
                    c.this.a(com.xiaomi.ai.utils.h.a("Text received: ERROR!!!"));
                    c.this.a(com.xiaomi.ai.utils.h.a(com.xiaomi.ai.utils.a.a(str), -65536));
                    synchronized (c.this.f17120o) {
                        if (b(mibrainRequest)) {
                            return;
                        }
                        c.this.notifyError(this.f17147e, speechErrorA);
                        return;
                    }
                }
                c.this.a(com.xiaomi.ai.utils.h.a("Text received: "));
                c.this.a(com.xiaomi.ai.utils.a.a(str));
                c cVar2 = c.this;
                if (cVar2.mResultParser == null) {
                    cVar2.mResultParser = new SpeechEngine.ResultParser();
                }
                JSONObject jSONObject2 = jSONObject.getJSONObject("meta");
                String string = jSONObject2.getString("type");
                if ("TRANSACTION_BEGIN".equals(string)) {
                    synchronized (c.this.f17120o) {
                        if (b(mibrainRequest)) {
                            return;
                        }
                        this.f17148f = c.this.getNextStage(this.f17147e, this.f17148f);
                        cVar = c.this;
                        str2 = "TRANSACTION_BEGIN stage: " + this.f17148f;
                    }
                } else {
                    if ("RESULT_ASR_PARTIAL".equals(string)) {
                        SpeechResult asrResult = c.this.mResultParser.getAsrResult(jSONObject);
                        synchronized (c.this.f17120o) {
                            if (asrResult.query != null) {
                                if (b(mibrainRequest)) {
                                    return;
                                }
                                c.this.notifyAsrParticalResults(asrResult);
                                return;
                            } else {
                                SpeechError speechError2 = new SpeechError(SpeechEngine.ProcessStage.STAGE_ASR);
                                speechError2.errCode = 12;
                                speechError2.errType = 2;
                                c.this.notifyError(this.f17147e, speechError2);
                                return;
                            }
                        }
                    }
                    if ("RESULT_ASR_FINAL".equals(string)) {
                        SpeechResult asrResult2 = c.this.mResultParser.getAsrResult(jSONObject);
                        synchronized (c.this.f17120o) {
                            if (asrResult2.query == null) {
                                SpeechError speechError3 = new SpeechError(SpeechEngine.ProcessStage.STAGE_ASR);
                                speechError3.errCode = 12;
                                speechError3.errType = 2;
                                c.this.notifyError(this.f17147e, speechError3);
                                return;
                            }
                            if (b(mibrainRequest)) {
                                return;
                            }
                            this.f17146d = TextUtils.isEmpty(asrResult2.query);
                            c.this.notifyAsrResults(asrResult2);
                            this.f17148f = c.this.getNextStage(this.f17147e, this.f17148f);
                            cVar = c.this;
                            str2 = "TRESULT_ASR_FINAL stage: " + this.f17148f;
                        }
                    } else {
                        SpeechResult nlpResult = null;
                        nlpResult = null;
                        if ("RESULT_FILE_STORED".equals(string)) {
                            String strOptString = jSONObject2.optString("request_id", null);
                            synchronized (c.this.f17120o) {
                                try {
                                    if (strOptString == null) {
                                        SpeechError speechError4 = new SpeechError(SpeechEngine.ProcessStage.STAGE_ASR);
                                        speechError4.errCode = 12;
                                        speechError4.errType = 2;
                                        c.this.notifyError(this.f17147e, speechError4);
                                        return;
                                    }
                                    if (b(mibrainRequest)) {
                                        return;
                                    }
                                    c.this.notifyAsrFileStored(strOptString, jSONObject.toString());
                                    this.f17148f = c.this.getNextStage(this.f17147e, this.f17148f);
                                    cVar = c.this;
                                    str2 = "RESULT_FILE_STORED stage: " + this.f17148f;
                                } catch (Throwable th2) {
                                    throw th2;
                                }
                            }
                        } else {
                            boolean z10 = false;
                            if (!"RESULT_NLP".equals(string)) {
                                if ("RESULT_TTS_BEGIN".equals(string)) {
                                    for (int i10 = 0; i10 < 10 && c.this.f17117l.isBluetoothScoOn(); i10++) {
                                        synchronized (c.this.f17120o) {
                                            c.this.f17120o.wait(100L);
                                        }
                                    }
                                    String strOptString2 = jSONObject.getJSONObject("meta").getJSONObject("tts").optString("codec");
                                    synchronized (c.this.f17120o) {
                                        if (b(mibrainRequest)) {
                                            return;
                                        }
                                        if (c.this.f17131z.get()) {
                                            c cVar3 = c.this;
                                            cVar3.f17114i = new com.xiaomi.ai.streamplayer.k(cVar3.mStreamType);
                                            c.this.f17114i.a(strOptString2);
                                            c.this.f17114i.a(c.this.A);
                                            Log.MiLogD(c.f17106b, "setTTSPlayMode" + c.this.f17129x);
                                            c.this.f17114i.a(c.this.f17129x);
                                            c.this.f17114i.a();
                                        }
                                        c.this.notifyTtsTransStart();
                                        return;
                                    }
                                }
                                if ("RESULT_TTS_END".equals(string) || (this.f17148f == SpeechEngine.ProcessStage.STAGE_TTS && "TRANSACTION_END".equals(string))) {
                                    synchronized (c.this.f17120o) {
                                        if (b(mibrainRequest)) {
                                            return;
                                        }
                                        if (c.this.f17114i != null) {
                                            c.this.f17114i.b();
                                        }
                                        this.f17148f = c.this.getNextStage(this.f17147e, this.f17148f);
                                        c.this.a("RESULT_TTS_END stage: " + this.f17148f);
                                        boolean z11 = "RESULT_TTS_END".equals(string) || this.f17148f == SpeechEngine.ProcessStage.STAGE_TTS;
                                        synchronized (c.this.f17120o) {
                                            if (b(mibrainRequest)) {
                                                return;
                                            }
                                            c.this.notifyTtsTransEnd(z11);
                                            return;
                                        }
                                    }
                                }
                                if ("RESULT_TTS_URL".equals(string)) {
                                    synchronized (c.this.f17120o) {
                                        if (b(mibrainRequest)) {
                                            return;
                                        }
                                        c.this.notifyTtsGotUrl(jSONObject.getJSONObject(com.xiaomi.onetrack.api.b.I).toString());
                                        return;
                                    }
                                }
                                if ("RESULT_VOR_REG_FINAL".equals(string)) {
                                    SpeechResult voiceRegResult = c.this.mResultParser.getVoiceRegResult(jSONObject);
                                    synchronized (c.this.f17120o) {
                                        if (b(mibrainRequest)) {
                                            return;
                                        }
                                        c.this.notifyVoiceRegResults(voiceRegResult);
                                        this.f17148f = c.this.getNextStage(this.f17147e, this.f17148f);
                                        return;
                                    }
                                }
                                if ("RESULT_VOR_FINAL".equals(string)) {
                                    SpeechResult voiceRecResult = c.this.mResultParser.getVoiceRecResult(jSONObject);
                                    synchronized (c.this.f17120o) {
                                        if (b(mibrainRequest)) {
                                            return;
                                        }
                                        c.this.notifyVoiceRecResults(voiceRecResult);
                                        this.f17148f = c.this.getNextStage(this.f17147e, this.f17148f);
                                        return;
                                    }
                                }
                                if ("RESULT_VOR_REG_PARTIAL".equals(string)) {
                                    SpeechResult voiceRegResult2 = c.this.mResultParser.getVoiceRegResult(jSONObject);
                                    synchronized (c.this.f17120o) {
                                        if (b(mibrainRequest)) {
                                            return;
                                        }
                                        c.this.notifyVoiceRegResults(voiceRegResult2);
                                        this.f17148f = c.this.getNextStage(this.f17147e, this.f17148f);
                                        return;
                                    }
                                }
                                if ("RESULT_VOR_DEL".equals(string)) {
                                    SpeechResult voiceDelResult = c.this.mResultParser.getVoiceDelResult(jSONObject);
                                    synchronized (c.this.f17120o) {
                                        if (b(mibrainRequest)) {
                                            return;
                                        }
                                        c.this.notifyVoiceDelResults(voiceDelResult);
                                        this.f17148f = c.this.getNextStage(this.f17147e, this.f17148f);
                                        return;
                                    }
                                }
                                if ("RESULT_VOR_QUERY".equals(string)) {
                                    SpeechResult voiceQryResult = c.this.mResultParser.getVoiceQryResult(jSONObject);
                                    synchronized (c.this.f17120o) {
                                        if (b(mibrainRequest)) {
                                            return;
                                        }
                                        c.this.notifyVoiceQryResults(voiceQryResult);
                                        this.f17148f = c.this.getNextStage(this.f17147e, this.f17148f);
                                        return;
                                    }
                                }
                                if ("SERVICE_EVENT".equals(string)) {
                                    if (jSONObject2.has("event_level")) {
                                        this.f17149g = jSONObject2.getString("event_level");
                                    }
                                    synchronized (c.this.f17120o) {
                                        if (b(mibrainRequest)) {
                                            return;
                                        }
                                        c.this.notifyServiceEvent(new ServiceEvent(this.f17149g, jSONObject.getJSONObject(com.xiaomi.onetrack.api.b.I)));
                                        return;
                                    }
                                }
                                if ("INSTRUCTION".equals(string)) {
                                    Instruction[] instruction = c.this.mResultParser.getInstruction(jSONObject);
                                    try {
                                        if (jSONObject.getJSONObject(com.xiaomi.onetrack.api.b.I).has("answer")) {
                                            nlpResult = c.this.mResultParser.getNlpResult(jSONObject);
                                        }
                                    } catch (JSONException e10) {
                                        e10.printStackTrace();
                                    }
                                    synchronized (c.this.f17120o) {
                                        if (instruction != null) {
                                            c.this.notifyInstruction(instruction, nlpResult);
                                        }
                                    }
                                    return;
                                }
                                return;
                            }
                            SpeechResult nlpResult2 = c.this.mResultParser.getNlpResult(jSONObject);
                            c.this.f17122q = nlpResult2.sessionId;
                            Instruction[] instruction2 = jSONObject.getJSONObject(com.xiaomi.onetrack.api.b.I).has("instructions") ? c.this.mResultParser.getInstruction(jSONObject) : null;
                            boolean z12 = instruction2 != null;
                            NlpRequest nlpRequest = this.f17147e.nlpRequest;
                            if (nlpRequest == null || !nlpRequest.discardInstructionOfNlpResult) {
                                z10 = z12;
                            }
                            synchronized (c.this.f17120o) {
                                if (b(mibrainRequest)) {
                                    return;
                                }
                                if (z10) {
                                    c.this.notifyInstruction(instruction2, nlpResult2);
                                } else {
                                    c.this.notifyNlpResults(nlpResult2);
                                }
                                this.f17148f = c.this.getNextStage(this.f17147e, this.f17148f);
                                cVar = c.this;
                                str2 = "RESULT_NLP stage: " + this.f17148f;
                            }
                        }
                    }
                }
                cVar.a(str2);
                return;
            } catch (Exception e11) {
                c.this.a("Exception in onTextMessage: ", e11);
                speechError = new SpeechError(this.f17148f);
                speechError.errType = 2;
                speechError.errCode = 1;
                speechError.errMsg = e11.toString();
                synchronized (c.this.f17120o) {
                    if (b(mibrainRequest)) {
                        return;
                    }
                    c.this.notifyError(this.f17147e, speechError);
                }
            }
            c.this.a("Exception in onTextMessage: ", e11);
            speechError = new SpeechError(this.f17148f);
            speechError.errType = 2;
            speechError.errCode = 1;
            speechError.errMsg = e11.toString();
            synchronized (c.this.f17120o) {
                if (b(mibrainRequest)) {
                    return;
                }
                c.this.notifyError(this.f17147e, speechError);
            }
        }

        private void a(MibrainRequest mibrainRequest, JSONObject jSONObject) {
            try {
                a aVar = this.f17145c;
                if (aVar == null) {
                    return;
                }
                String string = jSONObject.getJSONObject("meta").getString("type");
                if ("RESULT_ASR_VAD".equals(string)) {
                    if (jSONObject.getJSONObject(com.xiaomi.onetrack.api.b.I).getBoolean("is_final")) {
                        synchronized (c.this.f17120o) {
                            if (!b(mibrainRequest)) {
                                aVar.f();
                            }
                        }
                        return;
                    }
                    return;
                }
                if ("RESULT_ASR_FINAL".equals(string) || "RESULT_FILE_STORED".equals(string)) {
                    synchronized (c.this.f17120o) {
                        if (!b(mibrainRequest)) {
                            aVar.f();
                        }
                    }
                    return;
                }
                return;
                c.this.a("JSONException: ", e);
            } catch (JSONException e10) {
                c.this.a("JSONException: ", e10);
            }
        }

        private void a(MibrainRequest mibrainRequest, byte[] bArr) {
            synchronized (c.this.f17120o) {
                if (b(mibrainRequest)) {
                    return;
                }
                com.xiaomi.ai.streamplayer.k kVar = c.this.f17114i;
                if (bArr != null) {
                    if (kVar != null) {
                        kVar.a(bArr, 0, bArr.length);
                    }
                } else {
                    SpeechError speechError = new SpeechError(this.f17148f);
                    speechError.errType = 2;
                    speechError.errCode = 6;
                    speechError.errMsg = "resolveBinaryResult binary is null";
                    c.this.notifyError(this.f17147e, speechError);
                }
            }
        }

        private boolean a(MibrainRequest mibrainRequest) {
            if (mibrainRequest == this.f17144b) {
                return true;
            }
            SpeechError speechError = new SpeechError(this.f17148f);
            speechError.errType = 2;
            speechError.errCode = -1;
            speechError.errMsg = "FATAL ERROR isResponseCorrect check failed !!";
            c.this.notifyError(this.f17147e, speechError);
            return false;
        }

        private boolean b(MibrainRequest mibrainRequest) {
            boolean z10;
            synchronized (c.this.f17120o) {
                z10 = c.this.hasError() || c.this.f17118m != mibrainRequest;
            }
            return z10;
        }

        public String a() {
            return this.f17149g;
        }

        public void a(a aVar) {
            this.f17145c = aVar;
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onEventTrack(String str) {
            EventTrackListener eventTrackListener = c.this.mEventTrackListener;
            if (eventTrackListener != null) {
                eventTrackListener.onEventTrack(str);
            } else {
                Log.MiLogE(c.f17106b, "no Need onEventTrack callback ,skip");
            }
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onInstruction(MibrainRequest mibrainRequest, String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            a(mibrainRequest, str);
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public String onNeedUpdateToken() {
            if (c.this.mUpdateTokenCallback != null) {
                Log.MiLogE(c.f17106b, "Need UpdateToken");
                return c.this.mUpdateTokenCallback.onNeedUpdateToken();
            }
            Log.MiLogE(c.f17106b, "no Need UpdateToken callback ,skip");
            return null;
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onRequestASRResult(MibrainRequest mibrainRequest, String str, boolean z10) {
            if (!a(mibrainRequest)) {
                Log.MiLogE(c.f17106b, " isResponseCorrect = false ,mibrainRequest = " + mibrainRequest + " this.request = " + this.f17144b);
            }
            if (TextUtils.isEmpty(str)) {
                return;
            }
            a(mibrainRequest, str);
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onRequestError(MibrainRequest mibrainRequest, String str, int i10, int i11) {
            if (!a(mibrainRequest)) {
                Log.MiLogE(c.f17106b, " isResponseCorrect = false ,mibrainRequest = " + mibrainRequest + " this.request = " + this.f17144b);
            }
            synchronized (c.this.f17120o) {
                if (b(mibrainRequest)) {
                    return;
                }
                SpeechError speechError = new SpeechError(this.f17148f);
                if (i10 == 1) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        JSONObject jSONObject2 = jSONObject.getJSONObject(com.xiaomi.onetrack.api.b.I);
                        speechError.errCode = jSONObject2.getInt("error_code");
                        speechError.errMsg = jSONObject2.getString(rg.j.d.f52974k);
                        speechError.requestId = jSONObject.getJSONObject("meta").getString("request_id");
                    } catch (JSONException e10) {
                        speechError.errType = 2;
                        speechError.errCode = -9;
                        str = e10.getMessage();
                        speechError.errMsg = str;
                    }
                    c.this.notifyError(this.f17147e, speechError);
                }
                speechError.errType = i10;
                speechError.errCode = i11;
                speechError.errMsg = str;
                c.this.notifyError(this.f17147e, speechError);
            }
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public String onRequestGetToken(MibrainRequest mibrainRequest, boolean z10) {
            String str;
            if (!a(mibrainRequest)) {
                Log.MiLogE(c.f17106b, " isResponseCorrect = false ,mibrainRequest = " + mibrainRequest + " this.request = " + this.f17144b);
            }
            if (b(mibrainRequest)) {
                return null;
            }
            m mVar = c.this.f17124s;
            if (mVar != null) {
                m.a aVarA = mVar.a(z10);
                if (aVarA != null && (str = aVarA.f17195a) != null) {
                    return str;
                }
                synchronized (c.this.f17120o) {
                    if (b(mibrainRequest)) {
                        return null;
                    }
                    SpeechError speechError = new SpeechError(this.f17148f);
                    speechError.errType = 2;
                    speechError.errCode = -23;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("oauth getToken failed code= ");
                    sb2.append(aVarA == null ? "" : Integer.valueOf(aVarA.f17196b));
                    speechError.errMsg = sb2.toString();
                    c.this.notifyError(this.f17147e, speechError);
                }
            } else {
                Log.MiLogE(c.f17106b, "onRequestGetToken aiOauthHelper = null ");
            }
            return null;
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onRequestNLpResult(MibrainRequest mibrainRequest, String str) {
            if (!a(mibrainRequest)) {
                Log.MiLogE(c.f17106b, " isResponseCorrect = false ,mibrainRequest = " + mibrainRequest + " this.request = " + this.f17144b);
            }
            if (TextUtils.isEmpty(str)) {
                return;
            }
            a(mibrainRequest, str);
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onRequestOtherResult(MibrainRequest mibrainRequest, String str) {
            if (!a(mibrainRequest)) {
                Log.MiLogE(c.f17106b, " isResponseCorrect = false ,mibrainRequest = " + mibrainRequest + " this.request = " + this.f17144b);
            }
            if (TextUtils.isEmpty(str)) {
                return;
            }
            a(mibrainRequest, str);
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onRequestSessionEnd(MibrainRequest mibrainRequest) {
            if (a(mibrainRequest)) {
                return;
            }
            Log.MiLogE(c.f17106b, " isResponseCorrect = false ,mibrainRequest = " + mibrainRequest + " this.request = " + this.f17144b);
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onRequestSessionStart(MibrainRequest mibrainRequest) {
            if (a(mibrainRequest)) {
                return;
            }
            Log.MiLogE(c.f17106b, " isResponseCorrect = false ,mibrainRequest = " + mibrainRequest + " this.request = " + this.f17144b);
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onRequestTTSResult(MibrainRequest mibrainRequest, String str, byte[] bArr, boolean z10) {
            if (!a(mibrainRequest)) {
                Log.MiLogE(c.f17106b, " isResponseCorrect = false ,mibrainRequest = " + mibrainRequest + " this.request = " + this.f17144b);
            }
            if (TextUtils.isEmpty(str)) {
                a(mibrainRequest, bArr);
            } else {
                a(mibrainRequest, str);
            }
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onRequestVoiceprintDeleteResult(MibrainRequest mibrainRequest, String str) {
            if (!a(mibrainRequest)) {
                Log.MiLogE(c.f17106b, " isResponseCorrect = false ,mibrainRequest = " + mibrainRequest + " this.request = " + this.f17144b);
            }
            if (TextUtils.isEmpty(str)) {
                return;
            }
            a(mibrainRequest, str);
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onRequestVoiceprintQueryResult(MibrainRequest mibrainRequest, String str) {
            if (!a(mibrainRequest)) {
                Log.MiLogE(c.f17106b, " isResponseCorrect = false ,mibrainRequest = " + mibrainRequest + " this.request = " + this.f17144b);
            }
            if (TextUtils.isEmpty(str)) {
                return;
            }
            a(mibrainRequest, str);
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onRequestVoiceprintRecognizedResult(MibrainRequest mibrainRequest, String str) {
            if (!a(mibrainRequest)) {
                Log.MiLogE(c.f17106b, " isResponseCorrect = false ,mibrainRequest = " + mibrainRequest + " this.request = " + this.f17144b);
            }
            if (TextUtils.isEmpty(str)) {
                return;
            }
            a(mibrainRequest, str);
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onRequestVoiceprintRegisteredResult(MibrainRequest mibrainRequest, String str, boolean z10) {
            if (!a(mibrainRequest)) {
                Log.MiLogE(c.f17106b, " isResponseCorrect = false ,mibrainRequest = " + mibrainRequest + " this.request = " + this.f17144b);
            }
            if (TextUtils.isEmpty(str)) {
                return;
            }
            a(mibrainRequest, str);
        }
    }

    public class e {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private MibrainRequest f17152c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private SpeechEngine.ParamBuilder f17153d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f17154e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private Thread f17155f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private d f17156g;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private BlockingQueue<b> f17151b = new LinkedBlockingDeque();

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private Runnable f17157h = new l(this);

        public e(SpeechEngine.ParamBuilder paramBuilder) {
            this.f17153d = paramBuilder;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(b bVar) {
            this.f17151b.add(bVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(d dVar) {
            this.f17156g = dVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(MibrainRequest mibrainRequest) {
            this.f17152c = mibrainRequest;
        }

        private boolean a() {
            return (this.f17153d == c.this.f17123r && c.this.f17118m == this.f17152c) ? false : true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public b b() {
            try {
                b bVarTake = this.f17151b.take();
                EnumC0168c enumC0168c = bVarTake.f17139d;
                if (enumC0168c == EnumC0168c.DATA) {
                    byte[] bArr = bVarTake.f17136a;
                    if (bArr != null && !this.f17154e) {
                        int iAddAudioData = this.f17152c.addAudioData(bArr, bVarTake.f17138c, bVarTake.f17137b);
                        if (bVarTake.f17137b) {
                            Log.MiLogW(c.f17106b, "add end flag");
                        }
                        if (iAddAudioData == -5) {
                            this.f17154e = true;
                            return null;
                        }
                        if (iAddAudioData < 0) {
                            SpeechError speechError = new SpeechError(this.f17156g.f17148f);
                            speechError.errMsg = "sdk addAudioData failed ";
                            speechError.errCode = iAddAudioData;
                            speechError.errType = 2;
                            synchronized (c.this.f17120o) {
                                if (!a()) {
                                    c.this.notifyError(this.f17153d, speechError);
                                }
                            }
                            this.f17154e = true;
                            return null;
                        }
                    } else if (bVarTake.f17137b) {
                        Log.MiLogW(c.f17106b, "add end flag");
                        int iAddAudioData2 = this.f17152c.addAudioData(new byte[0], true);
                        if (iAddAudioData2 == -5) {
                            this.f17154e = true;
                            return null;
                        }
                        if (iAddAudioData2 < 0) {
                            SpeechError speechError2 = new SpeechError(this.f17156g.f17148f);
                            speechError2.errMsg = "sdk addAudioData failed ";
                            speechError2.errCode = iAddAudioData2;
                            speechError2.errType = 2;
                            synchronized (c.this.f17120o) {
                                if (!a()) {
                                    c.this.notifyError(this.f17153d, speechError2);
                                }
                            }
                            this.f17154e = true;
                            return null;
                        }
                    }
                } else if (enumC0168c == EnumC0168c.NLP_DATA) {
                    String str = bVarTake.f17140e;
                    if (str != null && !this.f17154e) {
                        int iAddNlpData = this.f17152c.addNlpData(str);
                        if (iAddNlpData == -5) {
                            this.f17154e = true;
                            return null;
                        }
                        if (iAddNlpData < 0) {
                            SpeechError speechError3 = new SpeechError(this.f17156g.f17148f);
                            speechError3.errMsg = "sdk addNlpData failed ";
                            speechError3.errCode = iAddNlpData;
                            speechError3.errType = 2;
                            synchronized (c.this.f17120o) {
                                if (!a()) {
                                    c.this.notifyError(this.f17153d, speechError3);
                                }
                            }
                            this.f17154e = true;
                            return null;
                        }
                    }
                } else if (enumC0168c == EnumC0168c.VAD_BEGIN && !this.f17154e) {
                    int iAddRawData = this.f17152c.addRawData(UploadHelper.generateVADBeginJSON().getBytes(), 4, false, false);
                    if (iAddRawData == -5) {
                        this.f17154e = true;
                        return null;
                    }
                    if (iAddRawData < 0) {
                        SpeechError speechError4 = new SpeechError(this.f17156g.f17148f);
                        speechError4.errMsg = "add VAD_BEGIN failed";
                        speechError4.errCode = iAddRawData;
                        speechError4.errType = 2;
                        synchronized (c.this.f17120o) {
                            if (!a()) {
                                c.this.notifyError(this.f17153d, speechError4);
                            }
                        }
                        this.f17154e = true;
                        return null;
                    }
                }
                if (!bVarTake.f17137b) {
                    return bVarTake;
                }
                this.f17154e = true;
                return null;
            } catch (InterruptedException unused) {
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean c() {
            MibrainRequest mibrainRequest;
            return !Thread.currentThread().isInterrupted() && this.f17153d == c.this.f17123r && this.f17152c.getErrorCode() == 0 && (mibrainRequest = this.f17152c) != null && mibrainRequest == c.this.f17118m && !this.f17154e;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d() {
            Thread thread = new Thread(this.f17157h);
            this.f17155f = thread;
            thread.start();
            Log.MiLogE(c.f17106b, "RecorderCacheQueueHelper start ");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e() {
            this.f17151b.add(c.this.new b(new byte[0], 0, 0, true));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void f() {
            this.f17151b.add(c.this.new b(new byte[0], 0, 0, true));
            this.f17154e = true;
            Thread thread = this.f17155f;
            if (thread != null) {
                thread.interrupt();
            }
        }
    }

    public c(Context context, String str, String str2) {
        super(context, str, str2);
        this.f17112d = 0;
        this.f17113e = null;
        this.f17120o = new Object();
        this.f17121p = new Object();
        this.f17123r = null;
        this.f17124s = null;
        this.f17125t = 0;
        this.f17110a = new h(this);
        this.A = new j(this);
        this.f17117l = (AudioManager) context.getSystemService("audio");
        this.f17129x = 1;
        this.f17130y = Executors.newSingleThreadExecutor();
        this.f17131z = new AtomicBoolean(true);
    }

    private String a(boolean z10) {
        m.a aVarA;
        String str;
        try {
            m mVar = this.f17124s;
            if (mVar == null || (aVarA = mVar.a(z10)) == null || (str = aVarA.f17195a) == null) {
                return null;
            }
            return str;
        } catch (IllegalStateException e10) {
            e10.printStackTrace();
            return null;
        }
    }

    private String a(boolean z10, String str) {
        m.a aVarA;
        String str2;
        if (str == null) {
            return null;
        }
        try {
            m mVar = this.f17124s;
            if (mVar == null || (aVarA = mVar.a(z10)) == null || (str2 = aVarA.f17195a) == null) {
                return null;
            }
            return str2;
        } catch (IllegalStateException e10) {
            e10.printStackTrace();
        }
    }

    private void a(SpeechEngine.ParamBuilder paramBuilder) {
        NlpRequest nlpRequest;
        NlpRequest nlpRequest2;
        int i10 = this.f17125t;
        if (i10 == SpeechEngine.ENGINE_AUTH_MIAI && this.mUseExternalOAuthToken == 0 && this.mOauthListener == null) {
            throw new IllegalStateException("please call setMiAIOauthCallbacks first !!");
        }
        if (i10 == SpeechEngine.ENGINE_AUTH_MIAO && this.mUseExternalOAuthToken == 0 && this.mOauthListener == null) {
            throw new IllegalStateException("please call setMiAOOauthCallbacks first !!");
        }
        if (i10 == SpeechEngine.ENGINE_AUTH_MIOT && this.mToken == null) {
            throw new IllegalStateException("please call updateMiotAuth first !!");
        }
        if (i10 == SpeechEngine.ENGINE_AUTH_TP && this.mToken == null) {
            throw new IllegalStateException("please call updateTPAuth first !!");
        }
        endSpeech();
        endVoiceprintRegister();
        endVoiceprintRecognize();
        boolean z10 = paramBuilder.needTts;
        if (z10 && !paramBuilder.needAsr && !paramBuilder.needNlp && (nlpRequest2 = paramBuilder.ttsRequest.f17102i) != null) {
            paramBuilder.needExtraNlp = true;
            paramBuilder.nlpRequest = nlpRequest2;
        }
        if (!z10 && paramBuilder.needAsr && !paramBuilder.needNlp && (nlpRequest = paramBuilder.asrRequest.f17084n) != null) {
            paramBuilder.needExtraNlp = true;
            paramBuilder.nlpRequest = nlpRequest;
        }
        synchronized (this.f17120o) {
            if (paramBuilder.needAsr || paramBuilder.needVpRec || paramBuilder.needVpReg) {
                this.f17115j = true;
            }
            this.f17119n = new e(paramBuilder);
            this.f17123r = paramBuilder;
        }
        new Thread(new i(this, paramBuilder)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(CharSequence charSequence) {
        a(charSequence, (Throwable) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(CharSequence charSequence, Throwable th2) {
        synchronized (this.f17121p) {
            try {
                if (th2 != null) {
                    Log.MiLogE(f17106b, charSequence.toString(), th2);
                    this.f17111c.append((CharSequence) (((Object) charSequence) + th2.toString()));
                } else {
                    Log.MiLogD(f17106b, charSequence.toString());
                    this.f17111c.append(charSequence);
                }
                this.f17111c.append((CharSequence) "\n");
            } catch (Throwable th3) {
                throw th3;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MibrainRequestParams b(SpeechEngine.ParamBuilder paramBuilder) throws JSONException {
        NlpRequest nlpRequest;
        NlpRequest nlpRequest2;
        JSONObject jSONObject;
        JSONArray jSONArray;
        int i10;
        int i11;
        boolean z10;
        EventRequest eventRequest;
        String str;
        TtsRequest ttsRequest;
        int i12;
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        MibrainRequestParamsBuilder mibrainRequestParamsBuilder = new MibrainRequestParamsBuilder();
        mibrainRequestParamsBuilder.setAppId(this.mAppId).setToken(this.mToken).setApiKey(this.f17126u);
        if (this.mUseExternalOAuthToken == 0 && ((i12 = this.f17125t) == SpeechEngine.ENGINE_AUTH_MIAI || i12 == SpeechEngine.ENGINE_AUTH_MIAO)) {
            mibrainRequestParamsBuilder.setToken(null);
        }
        mibrainRequestParamsBuilder.setProtocolVersion(this.mProtocolVersion);
        mibrainRequestParamsBuilder.setEventLevel(paramBuilder.mEventLevel);
        mibrainRequestParamsBuilder.setApiKey(paramBuilder.apiKey);
        mibrainRequestParamsBuilder.setConnectTimeOut(this.mConnectTimeout);
        mibrainRequestParamsBuilder.setReceiverTimeOut(this.mReceiveTimeout);
        mibrainRequestParamsBuilder.setUploadTimeout(this.mUploadTimeout);
        mibrainRequestParamsBuilder.setActiveTimeout(this.mActiveTimeout);
        mibrainRequestParamsBuilder.setEnv(this.f17112d);
        mibrainRequestParamsBuilder.setAuthMode(this.f17125t);
        mibrainRequestParamsBuilder.setNeedExtraNLP(paramBuilder.needExtraNlp ? 1 : 0);
        mibrainRequestParamsBuilder.setStartEventTrack(this.mStartEventTrack);
        mibrainRequestParamsBuilder.setEventTrackReceiver(this.mEventTrackReceiver);
        mibrainRequestParamsBuilder.setUseExternalOAuthToken(this.mUseExternalOAuthToken);
        if (paramBuilder.needWakeupData) {
            mibrainRequestParamsBuilder.setWakeupInfo(paramBuilder.wakeupInfoString);
            mibrainRequestParamsBuilder.setNeedWakeupData(true);
        }
        if (paramBuilder.needAsr) {
            mibrainRequestParamsBuilder.setChannels(1);
            AsrRequest asrRequest = paramBuilder.asrRequest;
            mibrainRequestParamsBuilder.setMaxEmptyAudioTime((asrRequest == null || !asrRequest.f17072b) ? 4 : 8);
            mibrainRequestParamsBuilder.setAudioBitNum(16);
            mibrainRequestParamsBuilder.setSampleRate(16000);
            mibrainRequestParamsBuilder.setAsrLang(paramBuilder.asrRequest.f17076f);
            AsrRequest asrRequest2 = paramBuilder.asrRequest;
            mibrainRequestParamsBuilder.setIsForSai(asrRequest2 != null && asrRequest2.f17072b);
            mibrainRequestParamsBuilder.setPreAsrTrack(paramBuilder.asrRequest.f17077g);
            mibrainRequestParamsBuilder.setAsrRemoveEndPunc(paramBuilder.asrRequest.f17073c);
            mibrainRequestParamsBuilder.setCodecMode(paramBuilder.asrRequest.f17078h);
            mibrainRequestParamsBuilder.setOpusBitRates(paramBuilder.asrRequest.f17080j);
            mibrainRequestParamsBuilder.setOpusFrameSize(paramBuilder.asrRequest.f17079i);
            mibrainRequestParamsBuilder.setASRDialect(paramBuilder.asrRequest.f17081k);
            mibrainRequestParamsBuilder.setASRDisableTimeout(paramBuilder.asrRequest.f17082l ? 1 : 0);
            mibrainRequestParamsBuilder.setASRAudioMaxTime(paramBuilder.asrRequest.f17083m);
            AsrRequest asrRequest3 = paramBuilder.asrRequest;
            if (asrRequest3 != null) {
                mibrainRequestParamsBuilder.setASRVendor(asrRequest3.a());
            }
        }
        if (paramBuilder.needTts) {
            new JSONObject().put("codec", "mp3");
            mibrainRequestParamsBuilder.setTTSCodec("mp3");
            TtsRequest ttsRequest2 = paramBuilder.ttsRequest;
            if (ttsRequest2 != null) {
                mibrainRequestParamsBuilder.setTTSVendor(ttsRequest2.a());
                mibrainRequestParamsBuilder.setTTSTimeout(paramBuilder.ttsRequest.f17095b);
                mibrainRequestParamsBuilder.setTTSExtendVendor(paramBuilder.ttsRequest.f17101h);
                String packageName = this.mContext.getPackageName();
                if ("com.zmifi.phoneai".equals(packageName) || "com.xiaomi.translator".equals(packageName)) {
                    mibrainRequestParamsBuilder.setTTSLang(paramBuilder.ttsRequest.f17100g);
                }
            }
        }
        AsrRequest asrRequest4 = paramBuilder.asrRequest;
        if (asrRequest4 != null) {
            mibrainRequestParamsBuilder.setUseInternalVadFlag(asrRequest4.f17071a);
        }
        AsrRequest asrRequest5 = paramBuilder.asrRequest;
        if (asrRequest5 != null) {
            mibrainRequestParamsBuilder.setVadTypeLocal(asrRequest5.f17075e == AsrRequest.VadMode.VAD_MODE_LOCAL ? MibrainRequestParamsBuilder.VadMode.VAD_MODE_LOCAL : MibrainRequestParamsBuilder.VadMode.VAD_MODE_CLOUD);
        }
        String strB = com.xiaomi.ai.utils.n.b(this.mContext);
        mibrainRequestParamsBuilder.setScopeData(com.xiaomi.ai.utils.n.b(strB));
        mibrainRequestParamsBuilder.setDeviceId(strB);
        JSONObject jSONObject2 = new JSONObject();
        if (activeNetworkInfo != null) {
            jSONObject2.put("network", activeNetworkInfo.getType() == 0 ? yd.b.j.f72599j : bj.c.f10771k);
        }
        if (!TextUtils.isEmpty(this.mDeviceModel)) {
            jSONObject2.put("model", this.mDeviceModel);
        }
        jSONObject2.put(q8.f.f51156u0, "");
        if ((paramBuilder.needNlp || paramBuilder.needExtraNlp) && (nlpRequest = paramBuilder.nlpRequest) != null) {
            Float f10 = nlpRequest.latitude;
            if (f10 != null && nlpRequest.longitude != null) {
                jSONObject2.put("latitude", f10);
                jSONObject2.put("longitude", paramBuilder.nlpRequest.longitude);
            } else if (nlpRequest.locationEnable) {
                Location locationA = com.xiaomi.ai.utils.b.a(this.mContext);
                if (locationA != null) {
                    jSONObject2.put("latitude", locationA.getLatitude());
                    jSONObject2.put("longitude", locationA.getLongitude());
                } else {
                    Log.MiLogE(f17106b, "location is null");
                }
            }
            String str2 = paramBuilder.nlpRequest.nlpVersion;
            if (str2 == null) {
                str2 = "2.2";
            }
            mibrainRequestParamsBuilder.setCustomNLPVersion(str2);
            jSONObject2.put("time_zone", TimeZone.getDefault().getID());
            jSONObject2.put("time_zone_display_name", TimeZone.getDefault().getDisplayName());
            mibrainRequestParamsBuilder.setIsPendingSend(paramBuilder.nlpRequest.isPendingSend);
        }
        String property = this.mUserAgent;
        if (property == null) {
            property = System.getProperty("http.agent");
        }
        mibrainRequestParamsBuilder.setUserAgent(property);
        mibrainRequestParamsBuilder.setDevice(jSONObject2.toString());
        if (paramBuilder.needTts && (ttsRequest = paramBuilder.ttsRequest) != null) {
            mibrainRequestParamsBuilder.setTTSText(ttsRequest.f17094a);
            mibrainRequestParamsBuilder.setTTSTone(paramBuilder.ttsRequest.f17098e);
            mibrainRequestParamsBuilder.setTTSVolume(paramBuilder.ttsRequest.f17097d);
            mibrainRequestParamsBuilder.setTTSSpeed(paramBuilder.ttsRequest.f17096c);
            mibrainRequestParamsBuilder.setTTSAsFileUrl(paramBuilder.ttsRequest.f17099f ? 1 : 0);
        }
        String str3 = this.f17113e;
        if (str3 != null) {
            if (this.f17112d != 2) {
                Log.MiLogE(f17106b, "only STAGING env  can setCustomNLPApi !!!!");
                return null;
            }
            mibrainRequestParamsBuilder.setCustomNLPApi(str3);
            mibrainRequestParamsBuilder.setCustomNLPAppId(this.mAppId);
            mibrainRequestParamsBuilder.setCustomNLPAppToken(this.mToken);
        }
        if ((paramBuilder.needNlp || paramBuilder.needExtraNlp) && (nlpRequest2 = paramBuilder.nlpRequest) != null) {
            mibrainRequestParamsBuilder.setNLPText(nlpRequest2.getTextToProcess());
            JSONObject jSONObject3 = new JSONObject();
            boolean z11 = paramBuilder.nlpRequest.startNewSession;
            if (z11) {
                this.f17122q = "";
            }
            jSONObject3.put("is_new", z11);
            String str4 = paramBuilder.nlpRequest.mSessionId;
            if (str4 == null) {
                str4 = this.f17122q;
            }
            jSONObject3.put("id", str4);
            mibrainRequestParamsBuilder.setNLPSession(jSONObject3.toString());
            JSONObject jSONObject4 = paramBuilder.nlpRequest.device;
            if (jSONObject4 != null) {
                mibrainRequestParamsBuilder.setDevice(jSONObject4.toString());
            }
            JSONObject jSONObject5 = paramBuilder.nlpRequest.speechData;
            if (jSONObject5 != null) {
                mibrainRequestParamsBuilder.setNLPSpeechData(jSONObject5.toString());
            }
            NlpRequest.UserInfo userInfo = paramBuilder.nlpRequest.userInfo;
            if (userInfo != null) {
                mibrainRequestParamsBuilder.setNLPUserInfoIdType(userInfo.userType);
                mibrainRequestParamsBuilder.setNLPUserInfoId(paramBuilder.nlpRequest.userInfo.userId);
                mibrainRequestParamsBuilder.setNLPUserInfoExtend(paramBuilder.nlpRequest.userInfo.extend);
            }
            NlpRequest nlpRequest3 = paramBuilder.nlpRequest;
            if (nlpRequest3.contextMap != null || nlpRequest3.contextStrMap != null) {
                JSONObject jSONObject6 = new JSONObject();
                ConcurrentHashMap<String, String> concurrentHashMap = paramBuilder.nlpRequest.contextMap;
                if (concurrentHashMap != null) {
                    for (String str5 : concurrentHashMap.keySet()) {
                        try {
                            String strTrim = paramBuilder.nlpRequest.contextMap.get(str5).trim();
                            if (strTrim.startsWith(wc.e.f64196l) && strTrim.endsWith("]")) {
                                jSONArray = new JSONArray(strTrim);
                                jSONObject = null;
                            } else {
                                jSONObject = new JSONObject(strTrim);
                                jSONArray = null;
                            }
                        } catch (JSONException e10) {
                            Log.MiLogE(f17106b, "JSONException when set context for key: " + str5, e10);
                            jSONObject = null;
                            jSONArray = null;
                        }
                        if (jSONArray != null) {
                            jSONObject6.put(str5, jSONArray);
                        } else if (jSONObject != null) {
                            jSONObject6.put(str5, jSONObject);
                        }
                    }
                }
                ConcurrentHashMap<String, String> concurrentHashMap2 = paramBuilder.nlpRequest.contextStrMap;
                if (concurrentHashMap2 != null) {
                    for (String str6 : concurrentHashMap2.keySet()) {
                        jSONObject6.put(str6, paramBuilder.nlpRequest.contextStrMap.get(str6));
                    }
                }
                mibrainRequestParamsBuilder.setNLPContext(jSONObject6.toString());
            }
            JSONObject jSONObject7 = paramBuilder.nlpRequest.context;
            if (jSONObject7 != null) {
                mibrainRequestParamsBuilder.setNLPContext(jSONObject7.toString());
            }
            String str7 = paramBuilder.nlpRequest.requestId;
            if (str7 != null) {
                mibrainRequestParamsBuilder.setRequestId(str7);
            }
        }
        VoiceprintRegisterRequest voiceprintRegisterRequest = paramBuilder.voiceprintRegisterRequest;
        if (voiceprintRegisterRequest != null && paramBuilder.needVpReg) {
            mibrainRequestParamsBuilder.setVoiceprintUserId(voiceprintRegisterRequest.userId);
            mibrainRequestParamsBuilder.setVoiceprintFamilyId(paramBuilder.voiceprintRegisterRequest.family);
            mibrainRequestParamsBuilder.setVoiceprintQueries(paramBuilder.voiceprintRegisterRequest.query);
            mibrainRequestParamsBuilder.setVoiceprintVendor(paramBuilder.voiceprintRegisterRequest.vendorStr);
            mibrainRequestParamsBuilder.setVoiceId(paramBuilder.voiceprintRegisterRequest.voiceId);
            mibrainRequestParamsBuilder.setVoiceprintDisableTextDependent(paramBuilder.voiceprintRegisterRequest.disableTextDependent);
        }
        VoiceprintDeleteRequest voiceprintDeleteRequest = paramBuilder.voiceprintDeleteRequest;
        if (voiceprintDeleteRequest != null && paramBuilder.needVpDel) {
            mibrainRequestParamsBuilder.setVoiceprintDeleteIds(voiceprintDeleteRequest.ids.toString());
            mibrainRequestParamsBuilder.setVoiceprintFamilyId(paramBuilder.voiceprintDeleteRequest.family);
            mibrainRequestParamsBuilder.setVoiceprintVendor(paramBuilder.voiceprintDeleteRequest.vendorStr);
        }
        VoiceprintQueryRequest voiceprintQueryRequest = paramBuilder.voiceprintQueryRequest;
        if (voiceprintQueryRequest != null && paramBuilder.needVpQry) {
            mibrainRequestParamsBuilder.setVoiceprintFamilyId(voiceprintQueryRequest.family);
            mibrainRequestParamsBuilder.setVoiceprintVendor(paramBuilder.voiceprintQueryRequest.vendorStr);
        }
        VoiceprintRecognizeRequest voiceprintRecognizeRequest = paramBuilder.voiceprintRecognizeRequest;
        if (voiceprintRecognizeRequest != null && paramBuilder.needVpRec) {
            mibrainRequestParamsBuilder.setVoiceprintQueries(voiceprintRecognizeRequest.query);
            mibrainRequestParamsBuilder.setVoiceprintFamilyId(paramBuilder.voiceprintRecognizeRequest.family);
            mibrainRequestParamsBuilder.setVoiceprintUserId(paramBuilder.voiceprintRecognizeRequest.f17103id);
            mibrainRequestParamsBuilder.setVoiceprintVendor(paramBuilder.voiceprintRecognizeRequest.vendorStr);
            mibrainRequestParamsBuilder.setVoiceprintDisableTextDependent(paramBuilder.voiceprintRecognizeRequest.disableTextDependent);
        }
        if (paramBuilder.needEvent && (eventRequest = paramBuilder.eventRequest) != null) {
            mibrainRequestParamsBuilder.setEventNameSpace(eventRequest.f17085a);
            mibrainRequestParamsBuilder.setEventName(paramBuilder.eventRequest.f17086b);
            mibrainRequestParamsBuilder.setEventPayload(paramBuilder.eventRequest.f17087c);
            if (!paramBuilder.eventRequest.f17092h && (str = this.f17122q) != null && !str.isEmpty()) {
                mibrainRequestParamsBuilder.setEventSessionId(this.f17122q);
            }
            mibrainRequestParamsBuilder.setEventContextNameSpace(paramBuilder.eventRequest.f17088d);
            mibrainRequestParamsBuilder.setEventContextName(paramBuilder.eventRequest.f17089e);
            mibrainRequestParamsBuilder.setEventContextPayload(paramBuilder.eventRequest.f17090f);
            JSONObject jSONObject8 = paramBuilder.eventRequest.f17093i;
            if (jSONObject8 != null) {
                mibrainRequestParamsBuilder.setDevice(jSONObject8.toString());
            }
        }
        boolean z12 = paramBuilder.needAsr;
        try {
            if (z12 && !(z10 = paramBuilder.needTts) && !paramBuilder.needNlp && !z10 && !paramBuilder.needVpReg && !paramBuilder.needVpRec && !paramBuilder.needVpQry && !paramBuilder.needVpDel) {
                i10 = MibrainRequestParamsBuilder.MI_CMD_ASR;
            } else if (z12 && paramBuilder.needNlp && !paramBuilder.needTts && !paramBuilder.needVpReg && !paramBuilder.needVpRec && !paramBuilder.needVpQry && !paramBuilder.needVpDel) {
                i10 = MibrainRequestParamsBuilder.MI_CMD_ASR_NLP;
            } else if (z12 && paramBuilder.needNlp && paramBuilder.needTts && !paramBuilder.needVpReg && !paramBuilder.needVpRec && !paramBuilder.needVpQry && !paramBuilder.needVpDel) {
                i10 = MibrainRequestParamsBuilder.MI_CMD_ASR_NLP_TTS;
            } else if (!z12 && paramBuilder.needNlp && !paramBuilder.needTts && !paramBuilder.needVpReg && !paramBuilder.needVpRec && !paramBuilder.needVpQry && !paramBuilder.needVpDel) {
                i10 = MibrainRequestParamsBuilder.MI_CMD_NLP;
            } else if (z12 || !paramBuilder.needNlp || !paramBuilder.needTts || paramBuilder.needVpReg || paramBuilder.needVpRec || paramBuilder.needVpQry || paramBuilder.needVpDel) {
                if (z12 || paramBuilder.needNlp || !paramBuilder.needTts || paramBuilder.needVpReg || paramBuilder.needVpRec || paramBuilder.needVpQry || paramBuilder.needVpDel) {
                    boolean z13 = paramBuilder.needVpReg;
                    if (!z13 || z12 || paramBuilder.needNlp || paramBuilder.needTts || paramBuilder.needVpDel || paramBuilder.needVpQry || paramBuilder.needVpRec) {
                        boolean z14 = paramBuilder.needVpRec;
                        if (!z14 || z12 || paramBuilder.needNlp || paramBuilder.needTts || paramBuilder.needVpDel || paramBuilder.needVpQry || z13) {
                            boolean z15 = paramBuilder.needVpDel;
                            if (z15 && !z12 && !paramBuilder.needNlp && !paramBuilder.needTts && !z14 && !paramBuilder.needVpQry && !z13) {
                                i10 = MibrainRequestParamsBuilder.MI_CMD_VOR_DELETE;
                            } else if (!paramBuilder.needVpQry || z12 || paramBuilder.needNlp || paramBuilder.needTts || z14 || z15 || z13) {
                                if (!paramBuilder.needEvent) {
                                    StringBuilder sbA = android.support.v4.media.e.a("needAsr:");
                                    sbA.append(paramBuilder.needAsr);
                                    sbA.append(",needNlp:");
                                    sbA.append(paramBuilder.needNlp);
                                    sbA.append(",needTts:");
                                    sbA.append(paramBuilder.needTts);
                                    sbA.append(",needVpReg:");
                                    sbA.append(paramBuilder.needVpReg);
                                    sbA.append(",needVpDel:");
                                    sbA.append(paramBuilder.needVpDel);
                                    sbA.append(",needVpRec:");
                                    sbA.append(paramBuilder.needVpRec);
                                    sbA.append(",needVpQry:");
                                    sbA.append(paramBuilder.needVpQry);
                                    sbA.append(",needEvent:");
                                    sbA.append(paramBuilder.needEvent);
                                    sbA.append("  combine Illegally ");
                                    throw new IllegalArgumentException(sbA.toString());
                                }
                                i10 = paramBuilder.eventRequest.f17091g ? MibrainRequestParamsBuilder.MI_CMD_EVENT_TTS : MibrainRequestParamsBuilder.MI_CMD_EVENT;
                            } else {
                                i10 = MibrainRequestParamsBuilder.MI_CMD_VOR_QUERY;
                            }
                        } else {
                            i11 = MibrainRequestParamsBuilder.MI_CMD_VOR;
                        }
                    } else {
                        i11 = MibrainRequestParamsBuilder.MI_CMD_VOR_REG;
                    }
                    mibrainRequestParamsBuilder.setRequestCmd(i11);
                    mibrainRequestParamsBuilder.setReceiverTimeOut(this.mReceiveTimeout * 2);
                    return mibrainRequestParamsBuilder.buid();
                }
                i10 = MibrainRequestParamsBuilder.MI_CMD_TTS;
            } else {
                i10 = MibrainRequestParamsBuilder.MI_CMD_NLP_TTS;
            }
            return mibrainRequestParamsBuilder.buid();
        } catch (MibrainException e11) {
            e11.printStackTrace();
            throw new IllegalArgumentException(e11);
        }
        mibrainRequestParamsBuilder.setRequestCmd(i10);
    }

    private String b() {
        String strB;
        Context context = this.mContext;
        return (context == null || (strB = com.xiaomi.ai.utils.n.b(context)) == null) ? "" : com.xiaomi.ai.utils.n.b(strB);
    }

    public CharSequence a() {
        return this.f17111c;
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void addBuffer(byte[] bArr, int i10, int i11) {
        e eVar = this.f17119n;
        if (eVar == null) {
            throw new IllegalStateException("you should call doStartIntegrally first !!");
        }
        eVar.a(new b(bArr, i10, i11, false));
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void addNlpData(JSONObject jSONObject) {
        e eVar = this.f17119n;
        if (eVar == null) {
            throw new IllegalStateException("you should call doStartIntegrally first !!");
        }
        eVar.a(new b(jSONObject.toString(), EnumC0168c.NLP_DATA));
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void addVADBegin() {
        e eVar = this.f17119n;
        if (eVar == null) {
            throw new IllegalStateException("you should call doStartIntegrally first 1!!");
        }
        eVar.a(new b(EnumC0168c.VAD_BEGIN));
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void cancelTTS() {
        synchronized (this.f17120o) {
            com.xiaomi.ai.streamplayer.k kVar = this.f17114i;
            if (kVar != null) {
                kVar.c();
                this.f17114i = null;
            }
        }
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void cleanAllUserLoginData() {
        try {
            m mVar = this.f17124s;
            if (mVar != null) {
                mVar.e();
            }
        } catch (IllegalStateException e10) {
            e10.printStackTrace();
        }
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void cleanUserLoginData() {
        try {
            m mVar = this.f17124s;
            if (mVar != null) {
                if (mVar.a() == null) {
                    Log.MiLogE(f17106b, "oAuth is null");
                } else {
                    this.f17124s.d();
                }
            }
        } catch (IllegalStateException e10) {
            e10.printStackTrace();
        }
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void cleanUserLoginData(String str) {
        try {
            m mVar = this.f17124s;
            if (mVar != null) {
                if (mVar.a(str) != null) {
                    this.f17124s.c(str);
                    return;
                }
                Log.MiLogE(f17106b, "oAuth is null " + str);
            }
        } catch (IllegalStateException e10) {
            e10.printStackTrace();
        }
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void doAsyncTrackUpload(HTTPCallback hTTPCallback, TrackInfo trackInfo, String str) {
        com.xiaomi.ai.utils.k kVar = this.mTrackUtils;
        if (kVar != null) {
            kVar.a(this.f17112d);
            this.mTrackUtils.a(hTTPCallback, trackInfo, str, getAuthorizationValue(), this.f17125t);
        }
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void doAsyncTrackUpload(TrackInfo trackInfo, String str) {
        com.xiaomi.ai.utils.k kVar = this.mTrackUtils;
        if (kVar != null) {
            kVar.a(this.f17112d);
            com.xiaomi.ai.utils.k kVar2 = this.mTrackUtils;
            kVar2.a(kVar2.a(), trackInfo, str, getAuthorizationValue(), this.f17125t);
        }
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void doDeleteVoiceprint(VoiceprintDeleteRequest voiceprintDeleteRequest) {
        SpeechEngine.ParamBuilder paramBuilder = new SpeechEngine.ParamBuilder();
        paramBuilder.needVpDel = true;
        paramBuilder.setApiKey(this.f17126u);
        paramBuilder.voiceprintDeleteRequest = voiceprintDeleteRequest;
        synchronized (this.f17121p) {
            this.f17111c = new SpannableStringBuilder();
        }
        paramBuilder.setApiKey(this.f17126u);
        a(paramBuilder);
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void doListenSpeech(AsrRequest asrRequest) {
        SpeechEngine.ParamBuilder paramBuilder = new SpeechEngine.ParamBuilder();
        paramBuilder.needAsr();
        paramBuilder.setApiKey(this.f17126u);
        paramBuilder.setAsrRequest(asrRequest);
        synchronized (this.f17121p) {
            this.f17111c = new SpannableStringBuilder();
        }
        a(paramBuilder);
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void doQueryVoiceprint(VoiceprintQueryRequest voiceprintQueryRequest) {
        SpeechEngine.ParamBuilder paramBuilder = new SpeechEngine.ParamBuilder();
        paramBuilder.needVpQry = true;
        paramBuilder.setApiKey(this.f17126u);
        paramBuilder.voiceprintQueryRequest = voiceprintQueryRequest;
        synchronized (this.f17121p) {
            this.f17111c = new SpannableStringBuilder();
        }
        paramBuilder.setApiKey(this.f17126u);
        a(paramBuilder);
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void doSemanticsParse(NlpRequest nlpRequest) {
        SpeechEngine.ParamBuilder paramBuilder = new SpeechEngine.ParamBuilder();
        paramBuilder.needNlp();
        paramBuilder.setApiKey(this.f17126u);
        paramBuilder.setNlpRequest(nlpRequest);
        synchronized (this.f17121p) {
            this.f17111c = new SpannableStringBuilder();
        }
        a(paramBuilder);
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void doSpeak(TtsRequest ttsRequest) {
        SpeechEngine.ParamBuilder paramBuilder = new SpeechEngine.ParamBuilder();
        paramBuilder.needTts();
        paramBuilder.setApiKey(this.f17126u);
        paramBuilder.setTtsRequest(ttsRequest);
        synchronized (this.f17121p) {
            this.f17111c = new SpannableStringBuilder();
        }
        a(paramBuilder);
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void doStartIntegrally(SpeechEngine.ParamBuilder paramBuilder) {
        synchronized (this.f17121p) {
            this.f17111c = new SpannableStringBuilder();
        }
        paramBuilder.setApiKey(this.f17126u);
        a(paramBuilder);
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void doVoiceprintRecognize(VoiceprintRecognizeRequest voiceprintRecognizeRequest) {
        SpeechEngine.ParamBuilder paramBuilder = new SpeechEngine.ParamBuilder();
        paramBuilder.needVpRec = true;
        paramBuilder.setApiKey(this.f17126u);
        paramBuilder.voiceprintRecognizeRequest = voiceprintRecognizeRequest;
        synchronized (this.f17121p) {
            this.f17111c = new SpannableStringBuilder();
        }
        a(paramBuilder);
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void doVoiceprintRegister(VoiceprintRegisterRequest voiceprintRegisterRequest) {
        SpeechEngine.ParamBuilder paramBuilder = new SpeechEngine.ParamBuilder();
        paramBuilder.needVpReg = true;
        paramBuilder.setApiKey(this.f17126u);
        paramBuilder.voiceprintRegisterRequest = voiceprintRegisterRequest;
        synchronized (this.f17121p) {
            this.f17111c = new SpannableStringBuilder();
        }
        a(paramBuilder);
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void endSpeech() {
        this.f17115j = false;
        e eVar = this.f17119n;
        if (eVar != null) {
            eVar.a(new b(new byte[0], 0, 0, true));
        }
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void endVoiceprintRecognize() {
        this.f17115j = false;
        e eVar = this.f17119n;
        if (eVar != null) {
            eVar.a(new b(new byte[0], 0, 0, true));
        }
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void endVoiceprintRegister() {
        this.f17115j = false;
        e eVar = this.f17119n;
        if (eVar != null) {
            eVar.a(new b(new byte[0], 0, 0, true));
        }
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void forceStop() {
        synchronized (this.f17120o) {
            super.forceStop();
            com.xiaomi.ai.streamplayer.k kVar = this.f17114i;
            if (kVar != null) {
                kVar.c();
                this.f17114i = null;
            }
            e eVar = this.f17119n;
            if (eVar != null) {
                eVar.f();
                this.f17119n = null;
            }
            a aVar = this.f17116k;
            if (aVar != null) {
                aVar.interrupt();
                this.f17116k = null;
            }
            MibrainRequest mibrainRequest = this.f17118m;
            if (mibrainRequest != null) {
                mibrainRequest.asyncStopRequest();
                this.f17118m = null;
            }
            this.f17123r = null;
            setBluetoothOn(false);
        }
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public String getAuthorizationValue() {
        return getAuthorizationValue(false);
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public String getAuthorizationValue(boolean z10) {
        String str;
        String str2;
        String strA;
        String str3;
        String str4;
        int i10 = this.f17125t;
        if (i10 == SpeechEngine.ENGINE_AUTH_MIOT) {
            String str5 = this.mAppId;
            if (str5 == null || (str4 = this.mToken) == null) {
                return null;
            }
            return String.format("MIOT-TOKEN-V1 app_id:%s,%s", str5, str4);
        }
        if (i10 == SpeechEngine.ENGINE_AUTH_MIAI) {
            String strA2 = a(z10);
            if (strA2 == null) {
                return null;
            }
            return String.format("DO-TOKEN-V1 app_id:%s,scope_data:%s,access_token:%s", this.mAppId, b(), strA2);
        }
        if (i10 == SpeechEngine.ENGINE_AUTH_MIAO) {
            String strA3 = a(z10);
            if (strA3 == null) {
                return null;
            }
            return String.format("AO-TOKEN-V1 dev_app_id:%s,access_token:%s", this.mAppId, strA3);
        }
        if (i10 == SpeechEngine.ENGINE_AUTH_ANONYMOUS) {
            strA = a(z10, this.mAppId);
            if (strA == null) {
                str3 = "anon";
                Log.MiLogD(str3, "failed");
                return null;
            }
            return this.f17124s.a(this.mAppId, this.f17126u, strA);
        }
        if (i10 != SpeechEngine.ENGINE_AUTH_ANONYMOUS_PROXY) {
            if (i10 != SpeechEngine.ENGINE_AUTH_TP || (str = this.mAppId) == null || (str2 = this.mToken) == null) {
                return null;
            }
            return String.format("TP-TOKEN-V1 app_id:%s,access_token:%s", str, str2);
        }
        strA = a(z10, this.mAppId);
        if (strA == null) {
            str3 = "anon proxy";
            Log.MiLogD(str3, "failed");
            return null;
        }
        return this.f17124s.a(this.mAppId, this.f17126u, strA);
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public boolean getOAuthStatus() {
        StringBuilder sb2;
        m mVar = this.f17124s;
        if (mVar == null) {
            return false;
        }
        int i10 = this.f17125t;
        if (i10 == SpeechEngine.ENGINE_AUTH_ANONYMOUS || i10 == SpeechEngine.ENGINE_AUTH_ANONYMOUS_PROXY) {
            if (mVar.a(this.mAppId) != null) {
                return true;
            }
            sb2 = new StringBuilder();
        } else {
            if (mVar.a() != null) {
                return true;
            }
            sb2 = new StringBuilder();
        }
        sb2.append("NO AUTH DATA, mode ");
        sb2.append(this.f17125t);
        Log.MiLogD(f17106b, sb2.toString());
        return false;
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public String getResourceDetails(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        String authorizationValue = getAuthorizationValue();
        if (authorizationValue == null) {
            a(true);
            authorizationValue = getAuthorizationValue();
            if (authorizationValue == null) {
                return null;
            }
        }
        return MibrainRequest.getResourceDetails(str, str2, authorizationValue, this.f17112d);
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public int getTTSMode() {
        StringBuilder sbA = android.support.v4.media.e.a("getTTSMode ");
        sbA.append(this.f17129x);
        Log.MiLogD(f17106b, sbA.toString());
        return this.f17129x;
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public UploadHelper getUploadHelper(SendWakeupDataStatusInterface sendWakeupDataStatusInterface) {
        UploadHelper uploadHelper = new UploadHelper();
        if (uploadHelper.startUploadRequest(sendWakeupDataStatusInterface, this.f17124s, this.f17112d, this.f17125t, this.mAppId, this.mToken, this.f17126u, com.xiaomi.ai.utils.n.b(com.xiaomi.ai.utils.n.b(this.mContext))) != 0) {
            return null;
        }
        return uploadHelper;
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public UploadHelper getUploadHelper(SendWakeupDataStatusInterface sendWakeupDataStatusInterface, String str) {
        UploadHelper uploadHelper = new UploadHelper();
        if (uploadHelper.startUploadRequest(sendWakeupDataStatusInterface, this.f17124s, this.f17112d, this.f17125t, this.mAppId, this.mToken, this.f17126u, com.xiaomi.ai.utils.n.b(com.xiaomi.ai.utils.n.b(this.mContext)), str) != 0) {
            return null;
        }
        return uploadHelper;
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void init(Context context, String str, int i10) {
        super.init(context, str, i10);
        Log.setJNILogHook(true);
        Mibrainsdk.setLogLevel(Mibrainsdk.MIBRAIN_DEBUG_LEVEL_WARNING);
        int i11 = SpeechEngine.ENGINE_AUTH_MIAI;
        if (i10 == i11 || i10 == SpeechEngine.ENGINE_AUTH_MIAO || i10 == SpeechEngine.ENGINE_AUTH_ANONYMOUS || i10 == SpeechEngine.ENGINE_AUTH_ANONYMOUS_PROXY) {
            m mVar = new m(context, str, this.mToken, this.mAppId);
            this.f17124s = mVar;
            mVar.b();
            this.f17124s.a(i10 != i11 ? 0 : 1);
            if (i10 == SpeechEngine.ENGINE_AUTH_ANONYMOUS || i10 == SpeechEngine.ENGINE_AUTH_ANONYMOUS_PROXY) {
                this.f17124s.a(str, com.xiaomi.ai.utils.n.b(context), new com.xiaomi.ai.utils.g(context).d(), i10);
                this.f17126u = str;
            }
        }
        this.f17125t = i10;
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void release() {
        super.release();
        synchronized (this.f17120o) {
            m mVar = this.f17124s;
            if (mVar != null) {
                mVar.c();
                this.f17124s = null;
            }
            e eVar = this.f17119n;
            if (eVar != null) {
                eVar.f();
                this.f17119n = null;
            }
            setBluetoothOn(false);
        }
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public int sendPendingNLPData(NlpRequest nlpRequest) {
        nlpRequest.setIsPendingSend(0);
        SpeechEngine.ParamBuilder paramBuilder = new SpeechEngine.ParamBuilder();
        paramBuilder.needNlp();
        paramBuilder.setApiKey(this.f17126u);
        paramBuilder.setNlpRequest(nlpRequest);
        try {
            MibrainRequestParams mibrainRequestParamsB = b(paramBuilder);
            if (this.f17118m == null) {
                Log.MiLogE(f17106b, "NULL ERROR");
            }
            MibrainRequest mibrainRequest = this.f17118m;
            if (mibrainRequest == null) {
                return -1;
            }
            mibrainRequest.updateRequestParams(mibrainRequestParamsB);
            return this.f17118m.sendPendingData();
        } catch (JSONException e10) {
            e10.printStackTrace();
            return -1;
        }
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void sendWakeupData(SendWakeupDataStatusInterface sendWakeupDataStatusInterface, String str, String str2, String str3, int i10, int i11, String str4, byte[] bArr) {
        g gVar = new g(this, i10, str3, str, str2, i11, str4, bArr, new f(this, sendWakeupDataStatusInterface));
        ExecutorService executorService = this.f17130y;
        if (executorService != null) {
            executorService.execute(gVar);
        } else if (sendWakeupDataStatusInterface != null) {
            sendWakeupDataStatusInterface.onSendError("ThreadPool error");
        }
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void setAnonymousAuthCallbacks(MiAiOauthCallbacks miAiOauthCallbacks) {
        super.setMiAIOauthCallbacks(miAiOauthCallbacks);
        if (miAiOauthCallbacks == null) {
            throw new NullPointerException("aiOauthListener is null");
        }
        int i10 = this.f17125t;
        if (i10 != SpeechEngine.ENGINE_AUTH_ANONYMOUS && i10 != SpeechEngine.ENGINE_AUTH_ANONYMOUS_PROXY) {
            throw new IllegalArgumentException("setMiAOOauthCallbacks is only for ENGINE_AUTH_ANONYMOUS");
        }
        this.f17124s.a(miAiOauthCallbacks);
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public boolean setAuthorizationTokens(String str, String str2, long j10) {
        m mVar = this.f17124s;
        if (mVar != null) {
            return mVar.a(this.f17125t, this.mAppId, str, str2, j10);
        }
        Log.MiLogE(f17106b, "setAuthorizationTokens: mOauthHelper is null\n");
        return false;
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void setCustomNlpUrl(String str) {
        this.f17113e = str;
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void setEnv(int i10) {
        setEnv(i10, this.mAppId, this.mToken);
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void setEnv(int i10, String str) {
        setEnv(i10, str, this.mToken);
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void setEnv(int i10, String str, String str2) {
        m mVar = this.f17124s;
        if (mVar != null) {
            mVar.b(i10);
        }
        this.f17112d = i10;
        this.mAppId = str;
        this.mToken = str2;
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void setExternalOAuthToken(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("OAuth token  is empty");
        }
        setUseExternalOAuthToken(true);
        this.mToken = str;
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void setMiAIOauthCallbacks(MiAiOauthCallbacks miAiOauthCallbacks) {
        super.setMiAIOauthCallbacks(miAiOauthCallbacks);
        if (miAiOauthCallbacks == null) {
            throw new NullPointerException("aiOauthListener is null");
        }
        if (this.f17125t != SpeechEngine.ENGINE_AUTH_MIAI) {
            throw new IllegalArgumentException("setMiAOOauthCallbacks is only for ENGINE_AUTH_MIAO");
        }
        this.f17124s.a(miAiOauthCallbacks);
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void setMiAOOauthCallbacks(MiAiOauthCallbacks miAiOauthCallbacks) {
        super.setMiAIOauthCallbacks(miAiOauthCallbacks);
        if (miAiOauthCallbacks == null) {
            throw new NullPointerException("aiOauthListener is null");
        }
        if (this.f17125t != SpeechEngine.ENGINE_AUTH_MIAO) {
            throw new IllegalArgumentException("setMiAOOauthCallbacks is only for ENGINE_AUTH_MIAO");
        }
        this.f17124s.a(miAiOauthCallbacks);
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void setTTSMode(int i10) {
        Log.MiLogD(f17106b, "setTTSMode " + i10);
        this.f17129x = i10;
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void setTokenUpdatedCallback(MibrainOauthManager.updateTokenCallback updatetokencallback) {
        m mVar = this.f17124s;
        if (mVar != null) {
            mVar.a(updatetokencallback);
        }
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void setTrackUploadCallback(HTTPCallback hTTPCallback) {
        this.mTrackUtils.a(hTTPCallback);
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void setTtsEnable(boolean z10) {
        synchronized (this.f17120o) {
            this.f17131z.set(z10);
        }
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public int startUploadASRRecord(SendWakeupDataStatusInterface sendWakeupDataStatusInterface, UploadHelper.ASRRecordInfo aSRRecordInfo, byte[] bArr) {
        return startUploadASRRecord(sendWakeupDataStatusInterface, aSRRecordInfo, bArr, null);
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public int startUploadASRRecord(SendWakeupDataStatusInterface sendWakeupDataStatusInterface, UploadHelper.ASRRecordInfo aSRRecordInfo, byte[] bArr, String str) {
        if (sendWakeupDataStatusInterface == null) {
            return -1;
        }
        com.xiaomi.ai.e eVar = new com.xiaomi.ai.e(this, sendWakeupDataStatusInterface, str, aSRRecordInfo, bArr);
        ExecutorService executorService = this.f17130y;
        if (executorService != null) {
            executorService.execute(eVar);
            return 0;
        }
        sendWakeupDataStatusInterface.onSendError("ThreadPool error");
        return -1;
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public int startUploadWakeupData(SendWakeupDataStatusInterface sendWakeupDataStatusInterface, UploadHelper.WakeupDataInfo wakeupDataInfo, byte[] bArr) {
        return startUploadWakeupData(sendWakeupDataStatusInterface, wakeupDataInfo, bArr, null);
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public int startUploadWakeupData(SendWakeupDataStatusInterface sendWakeupDataStatusInterface, UploadHelper.WakeupDataInfo wakeupDataInfo, byte[] bArr, String str) {
        if (sendWakeupDataStatusInterface == null) {
            return -1;
        }
        com.xiaomi.ai.d dVar = new com.xiaomi.ai.d(this, sendWakeupDataStatusInterface, str, wakeupDataInfo, bArr);
        ExecutorService executorService = this.f17130y;
        if (executorService != null) {
            executorService.execute(dVar);
            return 0;
        }
        sendWakeupDataStatusInterface.onSendError("ThreadPool error");
        return -1;
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public boolean updateAnonymousProxyArgs(String str, String str2, String str3, String str4, String str5, String str6) {
        if (this.f17125t != SpeechEngine.ENGINE_AUTH_ANONYMOUS_PROXY) {
            StringBuilder sbA = android.support.v4.media.e.a("wrong mode ");
            sbA.append(this.f17125t);
            Log.MiLogE("ERROR", sbA.toString());
            return false;
        }
        m mVar = this.f17124s;
        if (mVar == null || mVar.a(str, str2, str3, str4, str5, str6) != 0) {
            return false;
        }
        this.f17128w = str;
        this.mAppId = str;
        this.mToken = str2;
        this.f17126u = str3;
        return true;
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void updateMiotAuth(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("Miot token or session  is empty");
        }
        this.mToken = String.format(Locale.getDefault(), "session_id:%s,token:%s", str2, str);
    }

    @Override // com.xiaomi.ai.SpeechEngine
    public void updateTPAuth(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(" token  is empty");
        }
        this.mToken = str;
    }
}
