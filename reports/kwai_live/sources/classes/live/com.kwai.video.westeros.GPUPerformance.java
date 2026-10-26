package com.kwai.video.westeros;

import android.opengl.GLES20;
import com.kuaishou.client.log.event.packages.nano.ClientEvent;
import com.kuaishou.weapon.gp.k2;
import com.kwai.camerasdk.render.OpengGL.EglBase;
import com.kwai.yoda.model.LaunchModelInternal;
import com.yxcorp.bugly.CrashReporter;
import com.yxcorp.gifshow.api.search.ISearchPlugin;
import java.util.HashMap;
import java.util.Map;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes13.dex */
public class GPUPerformance {
    static String GPU_HARDWARE_STRING = "Adreno (TM) 130,1;Adreno (TM) 200,2;Adreno (TM) 203,7;Adreno (TM) 205,8;Adreno (TM) 220,17;Adreno (TM) 225,12;Adreno (TM) 302,19;Adreno (TM) 304,19;Adreno (TM) 305,19;Adreno (TM) 306,21;Adreno (TM) 308,27;Adreno (TM) 320,57;Adreno (TM) 330,129;Adreno (TM) 405,59;Adreno (TM) 418,172;Adreno (TM) 420,144;Adreno (TM) 430,324;Adreno (TM) 505,48;Adreno (TM) 506,120;Adreno (TM) 508,160;Adreno (TM) 510,180;Adreno (TM) 512,240;Adreno (TM) 530,407;Adreno (TM) 540,567;Adreno (TM) 608,600;Adreno (TM) 615,600;Adreno (TM) 630,600;Mali-400 MP,5;Mali-400 MP2,10;Mali-400 MP4,20;Mali-450,5;Mali-450 MP2,15;Mali-450 MP3,34;Mali-450 MP4,40;Mali-450 MP6,53;Mali-450 MP8,71;Mali-T604,17;Mali-T604 MP2,34;Mali-T604 MP4,68;Mali-T622,9;Mali-T624,17;Mali-T624 MP4,77;Mali-T628,17;Mali-T628 MP2,38;Mali-T628 MP3,51;Mali-T628 MP4,97;Mali-T628 MP6,102;Mali-T720,8;Mali-T720 MP2,15;Mali-T720 MP3,35;Mali-T720 MP6,70;Mali-T720 MP8,80;Mali-T760,20;Mali-T760 MP2,34;Mali-T760 MP4,75;Mali-T760 MP6,142;Mali-T760 MP8,210;Mali-T820,10;Mali-T820 MP3,30;Mali-T830,20;Mali-T830 MP2,44;Mali-T830 MP3,71;Mali-T860,23;Mali-T860 MP2,23;Mali-T860 MP3,71;Mali-T860 MP4,81;Mali-T880,28;Mali-T880 MP,50;Mali-T880 MP2,61;Mali-T880 MP4,106;Mali-T880 MP10,221;Mali-G71,28;Mali-G71 MP2,52;Mali-G71 MP8,282;Mali-G71 MP12,367;Mali-G71 MP18,334;Mali-G71 MP20,371;Mali-G72,28;Mali-G72,100;Mali-G72 MP12,346;Apple A5 GPU,12;Apple A6 GPU,25;Apple A5X GPU,32;Apple A6X GPU,68;Apple A7 GPU,115;Apple A8 GPU,115;Apple A8X GPU,230;Apple A9 GPU,172;Apple A10 GPU,250;Apple A9X GPU,409;Apple A10X GPU,500;Apple A11 GPU,350;Apple A12 GPU,500;Apple A12X GPU,800";
    static Map<String, Integer> mGPUHardwareList = initChipSetLevelMap();
    static int mScoreForThisDevice = -1;
    static boolean mScoreInTable = false;
    static String mThisGLRenderer;

    public enum Level {
        unknown,
        bad,
        low,
        medium,
        high,
        best
    }

    public static Level convertScoreToLevel(int i12) {
        if (i12 == 0) {
            return Level.unknown;
        }
        if (i12 < 50) {
            return Level.bad;
        }
        if (i12 < 100) {
            return Level.low;
        }
        if (i12 < 200) {
            return Level.medium;
        }
        return i12 < 500 ? Level.high : Level.best;
    }

    public static int getDeviceGPUScore() {
        int i12 = mScoreForThisDevice;
        if (i12 >= 0) {
            return i12;
        }
        String renderer = getRenderer();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("GL_RENDERER: ");
        sb2.append(renderer);
        int deviceGPUScore = getDeviceGPUScore(renderer);
        mScoreForThisDevice = deviceGPUScore;
        return deviceGPUScore;
    }

    public static Map<String, Integer> getGPUHardwareList() {
        return mGPUHardwareList;
    }

    public static String getRenderer() {
        String str = mThisGLRenderer;
        if (str != null) {
            return str;
        }
        String strGlGetString = null;
        try {
            EGL10 egl10 = (EGL10) EGLContext.getEGL();
            EGLContext eGLContextEglGetCurrentContext = egl10 == null ? null : egl10.eglGetCurrentContext();
            GL10 gl10 = (eGLContextEglGetCurrentContext == null || eGLContextEglGetCurrentContext == EGL10.EGL_NO_CONTEXT) ? null : (GL10) eGLContextEglGetCurrentContext.getGL();
            strGlGetString = gl10 != null ? gl10.glGetString(7937) : null;
            if (strGlGetString == null) {
                EglBase eglBaseA = EglBase.a();
                eglBaseA.d();
                eglBaseA.k();
                strGlGetString = GLES20.glGetString(7937);
                eglBaseA.l();
            }
        } catch (Throwable th2) {
            CrashReporter.logCheckedException(th2, "GPUPerformance.class", "getRenderer", 66);
        }
        mThisGLRenderer = strGlGetString;
        return strGlGetString;
    }

    private static Level guessDeviceGPULevel(String str) {
        if (str == null) {
            return Level.unknown;
        }
        String lowerCase = str.toLowerCase();
        try {
            if (lowerCase.contains("adreno")) {
                int iIntValue = Integer.valueOf(lowerCase.replaceAll("adreno([^\\d]*)(\\d*)(.*)", "$2")).intValue();
                int i12 = iIntValue / 100;
                int i13 = (iIntValue / 10) % 10;
                if (i12 < 6 && (i12 < 5 || i13 < 3)) {
                    if (i12 >= 4 && i13 >= 1) {
                        return Level.high;
                    }
                    if (i13 < 3 && i12 < 5) {
                        return Level.low;
                    }
                    return Level.medium;
                }
                return Level.best;
            }
            if (lowerCase.contains("mali")) {
                String strReplaceAll = lowerCase.replaceAll("mali-([a-z]*)(\\d*)(.*)", "$1");
                if (strReplaceAll.isEmpty()) {
                    return Level.low;
                }
                String strReplaceAll2 = lowerCase.replaceAll("mali-([a-z]*)(\\d*)(.*)", "$2");
                String strReplaceAll3 = lowerCase.replaceAll("mali-([a-z]*)(\\d*)([ \\t]*)mp(\\d*)(.*)", "$4");
                int iIntValue2 = Integer.valueOf(strReplaceAll2).intValue() / 100;
                int iIntValue3 = (strReplaceAll3.isEmpty() || strReplaceAll3.equalsIgnoreCase(lowerCase)) ? 0 : Integer.valueOf(strReplaceAll3).intValue();
                if (strReplaceAll.equalsIgnoreCase("t")) {
                    if (iIntValue2 < 8 || iIntValue3 < 10) {
                        return (iIntValue2 < 7 || iIntValue3 < 4) ? Level.low : Level.medium;
                    }
                    return Level.high;
                }
                if (!strReplaceAll.equalsIgnoreCase("g")) {
                    return Level.low;
                }
                if (iIntValue2 >= 7 && iIntValue3 >= 8) {
                    return Level.high;
                }
                if (iIntValue2 < 7 && iIntValue3 < 8) {
                    return Level.low;
                }
                return Level.medium;
            }
            if (!lowerCase.contains("powervr")) {
                return lowerCase.contains("apple") ? Level.medium : Level.unknown;
            }
            int iIntValue4 = Integer.valueOf(lowerCase.replaceAll("powervr([^\\d]*)(\\d*)(.*)", "$2")).intValue();
            int i14 = (iIntValue4 / 100) % 10;
            if (lowerCase.contains("sgx")) {
                return Level.bad;
            }
            if (!lowerCase.contains("rogue") && iIntValue4 / 1000 != 6) {
                if (lowerCase.contains("ge")) {
                    if (iIntValue4 / 1000 > 7 && i14 > 2) {
                        return i14 >= 4 ? Level.medium : Level.low;
                    }
                    return Level.bad;
                }
                if (lowerCase.contains("gt")) {
                    if (i14 >= 6) {
                        return Level.best;
                    }
                    if (i14 >= 5) {
                        return Level.high;
                    }
                    return i14 <= 2 ? Level.low : Level.medium;
                }
                if (!lowerCase.contains("gx")) {
                    return Level.medium;
                }
                if (i14 <= 2) {
                    return Level.low;
                }
                return i14 <= 4 ? Level.medium : Level.high;
            }
            if (i14 == 0) {
                return Level.bad;
            }
            if (i14 <= 2) {
                return Level.low;
            }
            return i14 <= 4 ? Level.medium : Level.high;
        } catch (Exception e12) {
            CrashReporter.logCheckedException(e12, "GPUPerformance.class", "guessDeviceGPULevel", -2);
            e12.printStackTrace();
            return Level.medium;
        }
    }

    private static Map<String, Integer> initChipSetLevelMap() {
        String[] strArrSplit;
        HashMap map = new HashMap();
        if (GPU_HARDWARE_STRING == null) {
            return map;
        }
        setupDefaultRenderers(map);
        for (String str : GPU_HARDWARE_STRING.split(ISearchPlugin.SPLIT_STR)) {
            if (str != null && str.length() != 0 && (strArrSplit = str.split(LaunchModelInternal.HYID_SEPARATOR)) != null && strArrSplit.length == 2) {
                if (map.containsKey(strArrSplit[0].toLowerCase())) {
                    int iIntValue = ((Integer) map.get(strArrSplit[0].toLowerCase())).intValue();
                    int iIntValue2 = Integer.valueOf(strArrSplit[1]).intValue();
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(iIntValue - iIntValue2);
                    sb2.append(":");
                    sb2.append(iIntValue);
                    sb2.append("->");
                    sb2.append(iIntValue2);
                } else {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("new key:");
                    sb3.append(strArrSplit[0].toLowerCase());
                }
                map.put(strArrSplit[0].toLowerCase(), Integer.valueOf(strArrSplit[1]));
            }
        }
        return map;
    }

    public static boolean scoreInTable() {
        return mScoreInTable;
    }

    public static void setGPUHardwarList(String str) {
        GPU_HARDWARE_STRING = str;
        mGPUHardwareList = initChipSetLevelMap();
    }

    private static void setupDefaultRenderers(Map<String, Integer> map) {
        map.put("adreno (tm) 130", 1);
        map.put("adreno (tm) 200", 3);
        map.put("adreno (tm) 203", 8);
        map.put("adreno (tm) 205", 8);
        map.put("adreno (tm) 220", 17);
        map.put("adreno (tm) 225", 19);
        map.put("adreno (tm) 302", 19);
        map.put("adreno (tm) 304", 19);
        map.put("adreno (tm) 305", 20);
        map.put("adreno (tm) 306", 21);
        map.put("adreno (tm) 308", 27);
        map.put("adreno (tm) 320", 80);
        map.put("adreno (tm) 330", 151);
        map.put("adreno (tm) 405", 59);
        map.put("adreno (tm) 418", Integer.valueOf(ClientEvent.UrlPackage.Page.LOGIN_RETRIEVE_PASSWORD));
        map.put("adreno (tm) 420", 158);
        map.put("adreno (tm) 430", 372);
        map.put("adreno (tm) 505", 48);
        map.put("adreno (tm) 506", 125);
        map.put("adreno (tm) 508", Integer.valueOf(ClientEvent.UrlPackage.Page.H5_HOMETOWN_SERVICE_PAGE));
        map.put("adreno (tm) 510", 180);
        map.put("adreno (tm) 512", 240);
        map.put("adreno (tm) 530", Integer.valueOf(ClientEvent.TaskEvent.Action.STORY_EDIT_PREVIEW_CLIP_VIDEO));
        map.put("adreno (tm) 540", Integer.valueOf(ClientEvent.TaskEvent.Action.ENTER_INTO_LIVE_GUESS));
        map.put("bonaire xt", 1792);
        map.put("cape verde pro", Integer.valueOf(ClientEvent.TaskEvent.Action.CLICK_GENDER));
        map.put("cape verde xt", Integer.valueOf(ClientEvent.TaskEvent.Action.CLICK_SENSITIVE));
        map.put("dreamcast", 2);
        map.put("g6020", 4);
        map.put("g6050", 9);
        map.put("g6060", 9);
        map.put("g6100", 38);
        map.put("g6110", 38);
        map.put("g6200", 73);
        map.put("g6230", 72);
        map.put("g6400", 122);
        map.put("g6430", 121);
        map.put("g6630", 201);
        map.put("gamecube", 9);
        map.put("gc1000", 9);
        map.put("gc1000 plus", 10);
        map.put("gc2000", 19);
        map.put("gc3000", 32);
        map.put("gc4000", 30);
        map.put("gc500", 0);
        map.put("gc5000", 64);
        map.put("gc6000", 128);
        map.put("gc7000", 64);
        map.put("gc7000l", 32);
        map.put("gc7000ul", 16);
        map.put("gc7200", 128);
        map.put("gc7400", 256);
        map.put("gc7600", 512);
        map.put("gc800", 4);
        map.put("gc8000", 0);
        map.put("ge7400", 19);
        map.put("ge7800", 38);
        map.put("geforce ulp x 12", 11);
        map.put("geforce ulp x 60", 79);
        map.put("geforce ulp x 72", 96);
        map.put("geforce ulp x 8", 5);
        map.put("gk104", 2823);
        map.put("gk106", Integer.valueOf(ClientEvent.TaskEvent.Action.SHOW_HOT_SITE));
        map.put("gk107", 751);
        map.put("gk110", 4500);
        map.put("gma 4500 series", 26);
        map.put("gt7200", 83);
        map.put("gt7400", 166);
        map.put("gt7400 plus", 204);
        map.put("gt7600", Integer.valueOf(ClientEvent.UrlPackage.Page.LOGIN_RETRIEVE_PASSWORD));
        map.put("gt7600 plus", Integer.valueOf(ClientEvent.UrlPackage.Page.PC_LIVEMATE_WISH_SETTING_PAGE));
        map.put("gt7800", Integer.valueOf(ClientEvent.TaskEvent.Action.ADD_LOCATION));
        map.put("gt7800+", 345);
        map.put("gt7900", 742);
        map.put("gt8525", 192);
        map.put("gx6240", 83);
        map.put("gx6250", 87);
        map.put("gx6450", 134);
        map.put("gx6650", 230);
        map.put("gx6850", 268);
        map.put("hd graphics", 95);
        map.put("hd graphics (broadwell)", 158);
        map.put("hd graphics 3000", 112);
        map.put("hd graphics 4000", 275);
        map.put("hd graphics 405", Integer.valueOf(ClientEvent.UrlPackage.Page.LIVEMATE_LOGIN_PAGE));
        map.put("hd graphics 4200", 352);
        map.put("hd graphics 4400", 235);
        map.put("hd graphics 4400 (mobile)", 352);
        map.put("hd graphics 4600", 352);
        map.put("hd graphics 500", 134);
        map.put("hd graphics 5000", 672);
        map.put("hd graphics 505", Integer.valueOf(ClientEvent.UrlPackage.Page.DYNAMIC_PIC_SHOT_PAGE));
        map.put("hd graphics 510", Integer.valueOf(ClientEvent.UrlPackage.Page.SHARE_USER_LIST));
        map.put("hd graphics 515", 345);
        map.put("hd graphics 520", 393);
        map.put("hd graphics 530", 393);
        map.put("hd graphics 5300", 316);
        map.put("hd graphics 5500", 338);
        map.put("hd graphics 5600", 393);
        map.put("hd graphics 6000", 748);
        map.put("hd graphics 610", Integer.valueOf(ClientEvent.UrlPackage.Page.PHOTO_PREVIEW));
        map.put("hd graphics 615", 364);
        map.put("hd graphics 620", Integer.valueOf(ClientEvent.TaskEvent.Action.RECORD_FEATURE_GUIDE_DIALOG));
        map.put("hd graphics 630", 403);
        map.put("hd graphics p4600", 352);
        map.put("iris graphics 540", 787);
        map.put("iris graphics 550", 787);
        map.put("iris graphics 6100", Integer.valueOf(ClientEvent.TaskEvent.Action.CLICK_NEXT));
        map.put("iris plus graphics 640", Integer.valueOf(ClientEvent.TaskEvent.Action.DOUBLE_CLICK_TO_ROAM_CITY));
        map.put("iris plus graphics 650", Integer.valueOf(ClientEvent.TaskEvent.Action.DOUBLE_CLICK_TO_ROAM_CITY));
        map.put("iris pro 5200", 800);
        map.put("iris pro graphics 580", Integer.valueOf(ClientEvent.TaskEvent.Action.INVITE_FRIEND));
        map.put("iris pro graphics 6200", Integer.valueOf(ClientEvent.TaskEvent.Action.CLICK_NEXT));
        map.put("iris pro graphics p580", Integer.valueOf(ClientEvent.TaskEvent.Action.INVITE_FRIEND));
        map.put("iris pro graphics p6300", Integer.valueOf(ClientEvent.TaskEvent.Action.CLICK_NEXT));
        map.put("kepler cores x 192", 326);
        map.put("mali-400", 3);
        map.put("mali-400 mp2", 7);
        map.put("mali-400 mp4", 15);
        map.put("mali-450", 4);
        map.put("mali-450 mp2", 13);
        map.put("mali-450 mp3", 33);
        map.put("mali-450 mp4", 32);
        map.put("mali-450 mp6", 53);
        map.put("mali-450 mp8", 71);
        map.put("mali-g71", 28);
        map.put("mali-g71 mp12", 367);
        map.put("mali-g71 mp18", 334);
        map.put("mali-g71 mp2", 58);
        map.put("mali-g71 mp20", 371);
        map.put("mali-g71 mp8", 282);
        map.put("mali-g72", 28);
        map.put("mali-g72 mp12", 346);
        map.put("mali-t604", 17);
        map.put("mali-t604 mp2", 34);
        map.put("mali-t604 mp4", 68);
        map.put("mali-t622", 8);
        map.put("mali-t624", 17);
        map.put("mali-t624 mp4", 76);
        map.put("mali-t628", 17);
        map.put("mali-t628 mp2", 38);
        map.put("mali-t628 mp3", 51);
        map.put("mali-t628 mp4", 87);
        map.put("mali-t628 mp6", 108);
        map.put("mali-t720", 7);
        map.put("mali-t720 mp", 103);
        map.put("mali-t720 mp2", 18);
        map.put("mali-t720 mp3", 35);
        map.put("mali-t720 mp8", 81);
        map.put("mali-t760", 20);
        map.put("mali-t760 mp2", 40);
        map.put("mali-t760 mp4", 78);
        map.put("mali-t760 mp6", 142);
        map.put("mali-t760 mp8", Integer.valueOf(ClientEvent.UrlPackage.Page.SEARCH_PAGE));
        map.put("mali-t820", 10);
        map.put("mali-t820 mp3", 30);
        map.put("mali-t830", 20);
        map.put("mali-t830 mp2", 51);
        map.put("mali-t830 mp3", 71);
        map.put("mali-t860", 23);
        map.put("mali-t860 mp2", 40);
        map.put("mali-t860 mp3", 71);
        map.put("mali-t860 mp4", 103);
        map.put("mali-t880", 28);
        map.put("mali-t880 mp10", 221);
        map.put("mali-t880 mp12", Integer.valueOf(ClientEvent.UrlPackage.Page.PERSONAL_HEAD_SETTINGS_PAGE));
        map.put("mali-t880 mp2", 64);
        map.put("mali-t880 mp4", 115);
        map.put("maxwell cores x 256", Integer.valueOf(ClientEvent.TaskEvent.Action.LAUNCH_FACEPP));
        map.put("n3ds", 4);
        map.put("n64", 0);
        map.put("nds", 0);
        map.put("ouya", 12);
        map.put("pascal cores x 256", 750);
        map.put("pitcairn pro", Integer.valueOf(ClientEvent.TaskEvent.Action.SHOW_TITLE_NOTICE_HINT));
        map.put("pitcairn xt", 2560);
        map.put("playstation 2", 6);
        map.put("playstation 3", Integer.valueOf(ClientEvent.UrlPackage.Page.CREATE_NEW_SESSION));
        map.put("playstation 4", Integer.valueOf(ClientEvent.TaskEvent.Action.SHOW_RED_PACKET_RAIN_LIVE_PAGE_DIALOG));
        map.put("playstation 4 pro", 4200);
        map.put("ps vita", 28);
        map.put("psp", 2);
        map.put("sgx530", 1);
        map.put("sgx531", 2);
        map.put("sgx531 ultra", 4);
        map.put("sgx535", 2);
        map.put("sgx540", 6);
        map.put("sgx543", 6);
        map.put("sgx543 mp2", 21);
        map.put("sgx543 mp3", 25);
        map.put("sgx543 mp4", 32);
        map.put("sgx544", 10);
        map.put("sgx544 mp2", 29);
        map.put("sgx544 mp3", 51);
        map.put("sgx545", 6);
        map.put("sgx554", 19);
        map.put("sgx554 mp2", 38);
        map.put("sgx554 mp4", 68);
        map.put("shield portable", 96);
        map.put("shield tv", 512);
        map.put("switch", 275);
        map.put("tahiti le", 2918);
        map.put("tahiti pro", 3024);
        map.put("tahiti xt", 3788);
        map.put("tahiti xt2", 4198);
        map.put("videocore1", 0);
        map.put("videocore2", 0);
        map.put("videocore3", 0);
        map.put("videocore4", 26);
        map.put("volta cores x 512", 0);
        map.put("wii", 12);
        map.put("wii u", Integer.valueOf(ClientEvent.UrlPackage.Page.FEEDBACK_AND_HELP));
        map.put("xbox", 20);
        map.put("xbox 360", 240);
        map.put("xbox one", Integer.valueOf(ClientEvent.TaskEvent.Action.CLICK_GET_MONGY));
        map.put("xbox one x", Integer.valueOf(k2.f22742q6));
    }

    public static int getDeviceGPUScore(String str) {
        if (str == null) {
            return 0;
        }
        String lowerCase = str.toLowerCase();
        Integer num = mGPUHardwareList.get(lowerCase);
        if (num != null) {
            mScoreInTable = true;
            return num.intValue();
        }
        mScoreInTable = false;
        Level levelGuessDeviceGPULevel = guessDeviceGPULevel(lowerCase);
        if (levelGuessDeviceGPULevel == Level.bad) {
            return 50;
        }
        if (levelGuessDeviceGPULevel == Level.low) {
            return 100;
        }
        if (levelGuessDeviceGPULevel == Level.high) {
            return 300;
        }
        return levelGuessDeviceGPULevel == Level.best ? 500 : 200;
    }
}
