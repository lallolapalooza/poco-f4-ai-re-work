package c9;

import com.google.common.primitives.UnsignedBytes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5908a = "v.txt";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5909b = 128;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map<String, Float> f5910c;

    public a(String str) throws IOException {
        HashMap map = new HashMap();
        this.f5910c = map;
        String str2 = str + this.f5908a;
        map.clear();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(str2))));
        while (bufferedReader.ready()) {
            List listAsList = Arrays.asList(bufferedReader.readLine().split("\t"));
            if (listAsList.size() == 2) {
                this.f5910c.put(((String) listAsList.get(0)).trim(), Float.valueOf(Float.parseFloat((String) listAsList.get(1))));
            }
        }
        bufferedReader.close();
    }

    public float[][] a(String str) {
        int i10;
        float[] fArr = new float[this.f5909b];
        byte[] bytes = str.replace("\n", "").replace("\r", "").replace("\t", "").replace(" ", "").replaceAll("\\s+", "").replaceAll("\"", "").replaceAll("'", "").toLowerCase().getBytes();
        ArrayList<String> arrayList = new ArrayList();
        int i11 = 0;
        while (i11 < bytes.length) {
            int i12 = Integer.parseInt(Integer.toHexString(bytes[i11] & UnsignedBytes.MAX_VALUE), 16);
            if (i12 >= 252) {
                i10 = 6;
            } else if (i12 >= 248) {
                i10 = 5;
            } else if (i12 >= 240) {
                i10 = 4;
            } else if (i12 >= 224) {
                i10 = 3;
            } else {
                i10 = i12 >= 192 ? 2 : 1;
            }
            int i13 = i10 + i11;
            arrayList.add(new String(Arrays.copyOfRange(bytes, i11, i13), StandardCharsets.UTF_8));
            i11 = (i13 - 1) + 1;
        }
        int i14 = 0;
        for (String str2 : arrayList) {
            if (i14 >= this.f5909b) {
                break;
            }
            if (this.f5910c.containsKey(str2)) {
                fArr[i14] = this.f5910c.get(str2).floatValue();
                i14++;
            }
        }
        Arrays.fill(fArr, i14, this.f5909b, 0.0f);
        return new float[][]{fArr};
    }
}
