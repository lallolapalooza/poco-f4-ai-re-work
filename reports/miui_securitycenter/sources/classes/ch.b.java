package ch;

import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class b extends e {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final String f6222k = e.class.getName();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    List<int[]> f6223h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    int[] f6224i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    int[] f6225j;

    b(JSONObject jSONObject) {
        super(jSONObject);
        try {
            if (jSONObject.has("body_len")) {
                JSONArray jSONArray = jSONObject.getJSONArray("body_len");
                for (int i10 = 0; i10 < jSONArray.length(); i10++) {
                    String[] strArrSplit = jSONArray.getString(i10).split("-");
                    if (strArrSplit.length == 2) {
                        int i11 = Integer.parseInt(strArrSplit[0]);
                        int i12 = Integer.parseInt(strArrSplit[1]);
                        if (this.f6223h == null) {
                            this.f6223h = new ArrayList();
                        }
                        this.f6223h.add(new int[]{i11, i12});
                    }
                }
            }
            if (jSONObject.has("num_types")) {
                JSONArray jSONArray2 = jSONObject.getJSONArray("num_types");
                this.f6224i = new int[jSONArray2.length()];
                for (int i13 = 0; i13 < jSONArray2.length(); i13++) {
                    this.f6224i[i13] = jSONArray2.getInt(i13);
                }
            }
            if (jSONObject.has("struct_types")) {
                JSONArray jSONArray3 = jSONObject.getJSONArray("struct_types");
                this.f6225j = new int[jSONArray3.length()];
                for (int i14 = 0; i14 < jSONArray3.length(); i14++) {
                    this.f6225j[i14] = jSONArray3.getInt(i14);
                }
            }
        } catch (JSONException unused) {
            Log.e(f6222k, "JSONException when decode KEY_TYPE features.");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0073, code lost:
    
        if (r1 == false) goto L42;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean b(ah.b bVar) {
        boolean z10;
        int length = bVar.d().length();
        List<int[]> list = this.f6223h;
        boolean z11 = true;
        if (list != null && list.size() > 0) {
            Iterator<int[]> it = this.f6223h.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z10 = false;
                    break;
                }
                int[] next = it.next();
                if (next.length == 2 && next[0] <= length && length <= next[1]) {
                    z10 = true;
                    break;
                }
            }
            if (!z10) {
                return false;
            }
        }
        int[] iArr = this.f6224i;
        if (iArr != null && iArr.length > 0) {
            List<ah.b.a> list2 = bVar.f511n;
            if (list2 != null && list2.size() != 0) {
                boolean z12 = false;
                for (int i10 = 0; i10 < bVar.f511n.size() && !z12; i10++) {
                    ah.b.a aVar = bVar.f511n.get(i10);
                    for (int i11 : this.f6224i) {
                        if (aVar.f514a == i11) {
                            z12 = true;
                            break;
                        }
                    }
                }
            }
            return false;
        }
        int[] iArr2 = this.f6225j;
        if (iArr2 != null && iArr2.length > 0) {
            int iJ = bVar.j();
            int[] iArr3 = this.f6225j;
            int length2 = iArr3.length;
            int i12 = 0;
            while (true) {
                if (i12 >= length2) {
                    z11 = false;
                    break;
                }
                if ((iArr3[i12] & iJ) != 0) {
                    break;
                }
                i12++;
            }
            if (!z11) {
                return false;
            }
        }
        return super.a(bVar.d());
    }
}
