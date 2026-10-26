package com.xiaomi.ai.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaomi.accountsdk.hasheddeviceidlib.HardwareInfo;
import com.xiaomi.ai.mibrain.MibrainUtils;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f17302a = "MiSpeechSDK:VoiceRecognizeUtils";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String f17303b = "aivs_id";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static String f17304c = "_id_";

    public static float a(byte[] bArr, int i10) {
        short[] sArrA = e.a(bArr, i10);
        if (sArrA == null) {
            return -1.0f;
        }
        long jAbs = 0;
        int length = sArrA.length;
        for (short s10 : sArrA) {
            jAbs += (long) Math.abs((int) s10);
        }
        return jAbs / length;
    }

    public static String a() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (!inetAddressNextElement.isLoopbackAddress()) {
                        return inetAddressNextElement.getHostAddress();
                    }
                }
            }
            return "";
        } catch (SocketException e10) {
            Log.MiLogE(f17302a, "SocketException", e10);
            return "";
        }
    }

    public static String a(Context context) {
        String str;
        String string = context.getSharedPreferences(f17303b, 0).getString(f17304c, null);
        String str2 = f17302a;
        if (string == null) {
            str = "READ ID null";
        } else {
            str = "READ ID " + string;
        }
        Log.MiLogD(str2, str);
        return string;
    }

    private static String a(Reader reader) throws IOException {
        StringBuilder sb2 = new StringBuilder();
        char[] cArr = new char[4096];
        while (true) {
            int i10 = reader.read(cArr);
            if (i10 < 0) {
                return sb2.toString();
            }
            sb2.append(cArr, 0, i10);
        }
    }

    public static String a(String str) {
        String str2;
        String str3;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            StringBuilder sb2 = new StringBuilder(bArrDigest.length * 2);
            for (byte b10 : bArrDigest) {
                int i10 = b10 & 255;
                if (i10 < 16) {
                    sb2.append("0");
                }
                sb2.append(Integer.toHexString(i10));
            }
            return sb2.toString();
        } catch (UnsupportedEncodingException e10) {
            e = e10;
            str2 = f17302a;
            str3 = "UnsupportedEncodingException";
            Log.MiLogE(str2, str3, e);
            return null;
        } catch (NoSuchAlgorithmException e11) {
            e = e11;
            str2 = f17302a;
            str3 = "NoSuchAlgorithmException";
            Log.MiLogE(str2, str3, e);
            return null;
        }
    }

    private static void a(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f17303b, 0).edit();
        editorEdit.putString(f17304c, str);
        editorEdit.apply();
        Log.MiLogD(f17302a, "SAVE ID " + str);
    }

    public static String b() {
        String strTrim;
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ").getInputStream()));
            String line = "";
            while (true) {
                if (line != null) {
                    line = lineNumberReader.readLine();
                    if (line != null) {
                        strTrim = line.trim();
                        break;
                    }
                    strTrim = "";
                    break;
                }
                strTrim = "";
                break;
            }
        } catch (Exception e10) {
            e10.printStackTrace();
        }
        if (strTrim == null || "".equals(strTrim)) {
            try {
                return d("/sys/class/net/eth0/address").toUpperCase().substring(0, 17);
            } catch (Exception e11) {
                e11.printStackTrace();
            }
        }
        if (c(strTrim)) {
            return null;
        }
        return strTrim;
    }

    public static String b(Context context) {
        boolean z10;
        String strA = a(context);
        if (TextUtils.isEmpty(strA) || "d41d8cd98f00b204e9800998ecf8427e".equals(strA)) {
            z10 = false;
            strA = a(c(context));
            if (TextUtils.isEmpty(strA)) {
                strA = a(b());
                if (TextUtils.isEmpty(strA)) {
                    strA = a(c());
                }
                if (TextUtils.isEmpty(strA) && Build.VERSION.SDK_INT >= 26) {
                    try {
                        strA = a(Build.getSerial());
                    } catch (Exception e10) {
                        e10.printStackTrace();
                    }
                }
                if (TextUtils.isEmpty(strA)) {
                    strA = a(UUID.randomUUID().toString());
                }
            }
        } else {
            z10 = true;
        }
        if (!TextUtils.isEmpty(strA) && !z10) {
            a(context, strA);
        }
        return strA;
    }

    public static String b(String str) {
        return MibrainUtils.base64(String.format("{\"d\":\"%s\"}", str).getBytes());
    }

    public static String c() {
        String address;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            try {
                address = defaultAdapter.getAddress();
            } catch (Exception e10) {
                e10.printStackTrace();
                address = "";
            }
        } else {
            address = "";
        }
        if (c(address)) {
            return null;
        }
        return address;
    }

    public static String c(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            int i10 = Build.VERSION.SDK_INT;
            if (i10 < 23) {
                return telephonyManager.getDeviceId();
            }
            if (telephonyManager.getPhoneCount() == 0) {
                return null;
            }
            return i10 >= 26 ? telephonyManager.getImei(0) : telephonyManager.getDeviceId(0);
        } catch (Exception e10) {
            e10.printStackTrace();
            return null;
        }
    }

    private static boolean c(String str) {
        return HardwareInfo.FAKE_MAC_ADDRESS.equals(str) || "00:00:00:00:00:00".equals(str) || "ff:ff:ff:ff:ff:ff".equals(str) || "FF:FF:FF:FF:FF:FF".equals(str);
    }

    private static String d(String str) throws IOException {
        FileReader fileReader = new FileReader(str);
        String strA = a(fileReader);
        fileReader.close();
        return strA;
    }
}
