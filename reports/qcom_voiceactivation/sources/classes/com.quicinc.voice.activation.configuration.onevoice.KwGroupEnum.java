package com.quicinc.voice.activation.configuration.onevoice;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
enum KwGroupEnum {
    KW5_HASH1_GROUPNAME0("63ff0ae5", "groupname0", "26468981", 0),
    KW5_HASH1_GROUPNAME1("63ff0ae5", "com.quicinc.voiceassistant.reference", "db4000a1", 1),
    KW5_HASH1_GROUPNAME2("63ff0ae5", "groupname2", "196682d4", 2),
    KW5_HASH1_GROUPNAME3("63ff0ae5", "groupname3", "5baa4906", 3),
    KW5_HASH1_GROUPNAME4("63ff0ae5", "groupname4", "4876db05", 4),
    KW5_HASH2_GROUPNAME0("85d40f70", "groupname5", "b4100609", 0),
    KW5_HASH2_GROUPNAME1("85d40f70", "groupname6", "cd9c57a1", 1),
    KW5_HASH2_GROUPNAME2("85d40f70", "groupname7", "22455772", 2),
    KW5_HASH2_GROUPNAME3("85d40f70", "groupname8", "ce63b186", 3),
    KW5_HASH2_GROUPNAME4("85d40f70", "groupname9", "b4ba47f5", 4);

    String mClientKwHash;
    String mGroupKwHash;
    String mGroupName;
    int mIndex;

    KwGroupEnum(String str, String str2, String str3, int i2) {
        this.mGroupKwHash = str;
        this.mGroupName = str2;
        this.mClientKwHash = str3;
        this.mIndex = i2;
    }

    public static ArrayList f(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            for (KwGroupEnum kwGroupEnum : values()) {
                if (kwGroupEnum.mGroupKwHash.equals(str)) {
                    arrayList2.add(kwGroupEnum);
                }
            }
        }
        return arrayList2;
    }

    public String b() {
        return this.mClientKwHash;
    }

    public String c() {
        return this.mGroupKwHash;
    }

    public String d() {
        return this.mGroupName;
    }

    public int e() {
        return this.mIndex;
    }

    public void g(String str) {
        this.mClientKwHash = str;
    }

    public void h(String str) {
        this.mGroupName = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "KwGroupEnum{GroupKwHash='" + this.mGroupKwHash + "', GroupName='" + this.mGroupName + "', ClientKwHash='" + this.mClientKwHash + "', Index=" + this.mIndex + '}';
    }
}
