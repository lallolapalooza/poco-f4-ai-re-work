package com.xiaomi.ai;

import com.xiaomi.ai.c.a;
import com.xiaomi.ai.c.d;
import com.xiaomi.ai.mibrain.MibrainRequest;
import com.xiaomi.ai.utils.Log;
import com.xiaomi.ai.utils.UploadHelper;
import java.util.Arrays;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
class i implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ SpeechEngine.ParamBuilder f17180a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ c f17181b;

    public i(c cVar, SpeechEngine.ParamBuilder paramBuilder) {
        this.f17181b = cVar;
        this.f17180a = paramBuilder;
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.f17181b.f17120o) {
            SpeechEngine.ParamBuilder paramBuilder = this.f17181b.f17123r;
            SpeechEngine.ParamBuilder paramBuilder2 = this.f17180a;
            if (paramBuilder != paramBuilder2) {
                Log.MiLogW("MiSpeechSDK:MiAiEngine", "new task is already begin!\n");
                return;
            }
            if (!paramBuilder2.needAsr && !paramBuilder2.needVpRec && !paramBuilder2.needVpReg) {
                if (!com.xiaomi.ai.utils.f.a(this.f17181b.mContext)) {
                    this.f17181b.a("Network error ");
                    SpeechError speechError = new SpeechError(SpeechEngine.ProcessStage.STAGE_REQUESTING);
                    speechError.errMsg = "Network error ";
                    speechError.errCode = 13;
                    speechError.errType = 2;
                    this.f17181b.notifyError(this.f17180a, speechError);
                    return;
                }
                try {
                    c cVar = this.f17181b;
                    cVar.f17118m = new MibrainRequest(cVar.b(this.f17180a));
                    this.f17181b.f17118m.setGetAnonymousAuthorizationCallback(this.f17181b.f17110a);
                    Log.MiLogD("MiSpeechSDK:MiAiEngine", " new mMibrainRequest = " + this.f17181b.f17118m);
                    c cVar2 = this.f17181b;
                    this.f17181b.f17118m.setRequestListener(cVar2.new d(this.f17180a, cVar2.f17118m));
                    int iStartRequest = this.f17181b.f17118m.startRequest();
                    if (iStartRequest < 0) {
                        String str = "startRequest failed: ret=" + iStartRequest;
                        this.f17181b.a(str);
                        SpeechError speechError2 = new SpeechError(SpeechEngine.ProcessStage.STAGE_REQUESTING);
                        speechError2.errMsg = str;
                        speechError2.errCode = iStartRequest;
                        speechError2.errType = 2;
                        this.f17181b.notifyError(this.f17180a, speechError2);
                        return;
                    }
                } catch (JSONException e10) {
                    e10.printStackTrace();
                    SpeechError speechError3 = new SpeechError(SpeechEngine.ProcessStage.STAGE_REQUESTING);
                    speechError3.errMsg = e10.getMessage();
                    speechError3.errCode = 1;
                    speechError3.errType = 2;
                    this.f17181b.notifyError(this.f17180a, speechError3);
                    return;
                }
            }
            if (this.f17181b.f17116k != null) {
                this.f17181b.f17116k.interrupt();
            }
            if (!com.xiaomi.ai.utils.f.a(this.f17181b.mContext)) {
                this.f17181b.a("Network error ");
                SpeechError speechError4 = new SpeechError(SpeechEngine.ProcessStage.STAGE_REQUESTING);
                speechError4.errMsg = "Network error ";
                speechError4.errCode = 13;
                speechError4.errType = 2;
                this.f17181b.notifyError(this.f17180a, speechError4);
                return;
            }
            try {
                c cVar3 = this.f17181b;
                cVar3.f17118m = new MibrainRequest(cVar3.b(this.f17180a));
                this.f17181b.f17118m.setGetAnonymousAuthorizationCallback(this.f17181b.f17110a);
                Log.MiLogD("MiSpeechSDK:MiAiEngine", " new mMibrainRequest = " + this.f17181b.f17118m);
                c cVar4 = this.f17181b;
                c.d dVar = cVar4.new d(this.f17180a, cVar4.f17118m);
                this.f17181b.f17118m.setRequestListener(dVar);
                if (this.f17181b.f17119n == null) {
                    this.f17181b.a("startRequest released!");
                    SpeechError speechError5 = new SpeechError(SpeechEngine.ProcessStage.STAGE_REQUESTING);
                    speechError5.errMsg = "startRequest released!";
                    speechError5.errCode = -1;
                    speechError5.errType = 2;
                    this.f17181b.notifyError(this.f17180a, speechError5);
                    return;
                }
                this.f17181b.f17119n.a(this.f17181b.f17118m);
                this.f17181b.f17119n.a(dVar);
                int iStartRequest2 = this.f17181b.f17118m.startRequest();
                if (iStartRequest2 < 0) {
                    String str2 = "startRequest failed: ret=" + iStartRequest2;
                    this.f17181b.a(str2);
                    SpeechError speechError6 = new SpeechError(SpeechEngine.ProcessStage.STAGE_REQUESTING);
                    speechError6.errMsg = str2;
                    speechError6.errCode = iStartRequest2;
                    speechError6.errType = 2;
                    this.f17181b.notifyError(this.f17180a, speechError6);
                    return;
                }
                SpeechEngine.ParamBuilder paramBuilder3 = this.f17180a;
                if (paramBuilder3.needWakeupData) {
                    byte[] bArr = paramBuilder3.wakeupData;
                    if (bArr.length <= 32768) {
                        this.f17181b.f17118m.addRawData(this.f17180a.wakeupData, 1, false, true);
                    } else {
                        int length = bArr.length / 32768;
                        int length2 = bArr.length % 32768;
                        int i10 = 0;
                        while (i10 < length) {
                            int i11 = i10 + 1;
                            this.f17181b.f17118m.addRawData(Arrays.copyOfRange(this.f17180a.wakeupData, i10 * 32768, i11 * 32768), 1, false, i10 == length + (-1) && length2 == 0);
                            i10 = i11;
                        }
                        if (length2 != 0) {
                            byte[] bArr2 = this.f17180a.wakeupData;
                            this.f17181b.f17118m.addRawData(Arrays.copyOfRange(bArr2, length * 32768, bArr2.length), 1, false, true);
                        }
                    }
                    this.f17181b.f17118m.addRawData(UploadHelper.generateSpliterJSON().getBytes(), 4, false, false);
                }
                c cVar5 = this.f17181b;
                c cVar6 = this.f17181b;
                cVar5.f17116k = cVar6.new a(cVar6.f17118m, this.f17180a);
                dVar.a(this.f17181b.f17116k);
                this.f17181b.f17116k.start();
            } catch (JSONException e11) {
                e11.printStackTrace();
                this.f17181b.a("buildRequestMsg failed !!: ");
                SpeechError speechError7 = new SpeechError(SpeechEngine.ProcessStage.STAGE_REQUESTING);
                speechError7.errMsg = "buildRequestMsg failed !!: ";
                speechError7.errCode = 1;
                speechError7.errType = 2;
                this.f17181b.notifyError(this.f17180a, speechError7);
            }
        }
    }
}
