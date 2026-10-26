package com.miui.face.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.face.Face;
import android.util.Slog;
import hidden.compat.FaceCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SharedPrefUtil {
    public static final long DEFAULT_DEVICE_ID = 123456;
    private static final String FACES_INFO = "faces_info";
    private static final String FILE_NAME = "face_unlock";
    private static final String KEY_FACE_ID = "key_face_id";
    private static final String KEY_NAME = "key_name";
    public static final String TAG = "miui.face.FaceService";
    private final Context mCtx;

    public SharedPrefUtil(Context context) {
        this.mCtx = context.getApplicationContext();
    }

    public void saveFacesEnrollSuccess(int i) {
        try {
            List<JSONObject> enrolledFacesJson = getEnrolledFacesJson();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(KEY_NAME, "name1");
            jSONObject.put(KEY_FACE_ID, i);
            enrolledFacesJson.add(jSONObject);
            String string = enrolledFacesJson.toString();
            SharedPreferences.Editor editorEdit = this.mCtx.getSharedPreferences(FILE_NAME, 0).edit();
            editorEdit.putString(FACES_INFO, string);
            editorEdit.apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Integer getFacesEnrollCount() {
        return Integer.valueOf(getEnrolledFacesJson().size());
    }

    public List<Face> getEnrolledFaces() {
        ArrayList arrayList = new ArrayList();
        for (JSONObject jSONObject : getEnrolledFacesJson()) {
            try {
                arrayList.add(FaceCompat.newInstance(jSONObject.getString(KEY_NAME), jSONObject.getInt(KEY_FACE_ID), 123456L));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public List<JSONObject> getEnrolledFacesJson() {
        ArrayList arrayList = new ArrayList();
        String string = this.mCtx.getSharedPreferences(FILE_NAME, 0).getString(FACES_INFO, "");
        if (!"".equalsIgnoreCase(string)) {
            try {
                JSONArray jSONArray = new JSONArray(string);
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    Slog.d("miui.face.FaceService", "getEnrolledFaces[" + i + "] json:" + jSONObject.toString());
                    arrayList.add(jSONObject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public int removeFace(int i) {
        int i2;
        JSONArray jSONArray;
        JSONArray jSONArray2;
        SharedPreferences sharedPreferences = this.mCtx.getSharedPreferences(FILE_NAME, 0);
        if (i == 0) {
            jSONArray2 = new JSONArray();
            i2 = i;
        } else {
            String string = sharedPreferences.getString(FACES_INFO, "");
            i2 = -1;
            if ("".equalsIgnoreCase(string)) {
                jSONArray2 = null;
            } else {
                try {
                    jSONArray = new JSONArray(string);
                    for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                        try {
                            if (i == jSONArray.getJSONObject(i3).getInt(KEY_FACE_ID)) {
                                jSONArray.remove(i3);
                                i2 = i;
                            }
                        } catch (JSONException e) {
                            e = e;
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e2) {
                    e = e2;
                    jSONArray = null;
                }
                jSONArray2 = jSONArray;
            }
        }
        if (jSONArray2 == null) {
            return i2;
        }
        Slog.d("miui.face.FaceService", "removeFace , SharedPreferenceUtil, faceId: " + i + " res:" + jSONArray2);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putString(FACES_INFO, jSONArray2.toString());
        editorEdit.apply();
        return i2;
    }

    public List<Integer> getEnrolledFaceIds() {
        ArrayList arrayList = new ArrayList();
        Iterator<JSONObject> it = getEnrolledFacesJson().iterator();
        while (it.hasNext()) {
            try {
                arrayList.add(Integer.valueOf(it.next().getInt(KEY_FACE_ID)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public void changeFaceId(List<Integer> list) {
        Iterator<Integer> it = getEnrolledFaceIds().iterator();
        while (it.hasNext()) {
            removeFace(it.next().intValue());
        }
        Iterator<Integer> it2 = list.iterator();
        while (it2.hasNext()) {
            saveFacesEnrollSuccess(it2.next().intValue());
        }
    }
}
