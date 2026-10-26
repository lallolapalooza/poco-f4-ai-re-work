/*
 * Ghidra headless decompilation
 * program   : libdoc_photo_process.so
 * executable: /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/xp/libdoc_photo_process.so
 * language  : AARCH64:LE:64:v8A
 * imagebase : 00100000
 * regex     : Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_.*
 * max funcs : 25
 * note      : pseudocode from a (possibly stripped) binary is approximate;
 *             indirect calls and register-indirect JNI tables are lost.
 */

/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeSupportFeatures
 * Address : 0017d6b8
 * Size    : 0x164 bytes
 * ------------------------------------------------------------------ */

void Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeSupportFeatures
               (long *param_1,undefined8 param_2,undefined8 param_3)

{
  long lVar1;
  undefined8 uVar2;
  undefined8 uVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined4 *local_80;
  undefined4 *puStack_78;
  long local_68;
  
  lVar1 = tpidr_el0;
  local_68 = *(long *)(lVar1 + 0x28);
  FUN_0017a790(&local_80);
  uVar2 = (**(code **)(*param_1 + 0x30))(param_1,"java/util/List");
  uVar3 = (**(code **)(*param_1 + 0x108))(param_1,uVar2,"add","(Ljava/lang/Object;)Z");
  uVar4 = (**(code **)(*param_1 + 0x30))(param_1,"java/lang/Integer");
  uVar5 = (**(code **)(*param_1 + 0x108))(param_1,uVar4,"<init>",&DAT_0053624e);
  for (; puStack_78 != local_80; local_80 = local_80 + 1) {
    uVar6 = FUN_0017667c(param_1,uVar4,uVar5,*local_80);
    FUN_0017d840(param_1,param_3,uVar3,uVar6);
  }
  (**(code **)(*param_1 + 0xb8))(param_1,uVar2);
  (**(code **)(*param_1 + 0xb8))(param_1,uVar4);
  FUN_0017a890(&local_80);
  if (*(long *)(lVar1 + 0x28) == local_68) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeIsAvailable
 * Address : 0017d8dc
 * Size    : 0x8 bytes
 * ------------------------------------------------------------------ */

undefined8 Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeIsAvailable(void)

{
  return 1;
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeGetVersion
 * Address : 0017d8e4
 * Size    : 0x24 bytes
 * ------------------------------------------------------------------ */

void Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeGetVersion(long *param_1)

{
  undefined *puVar1;
  
  puVar1 = DAT_018ee0b0;
  if ((DAT_018ee0a0 & 1) == 0) {
    puVar1 = &DAT_018ee0a1;
  }
                    /* WARNING: Could not recover jumptable at 0x0017d904. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (**(code **)(*param_1 + 0x538))(param_1,puVar1);
  return;
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeGlobalInit
 * Address : 0017d908
 * Size    : 0x7c bytes
 * ------------------------------------------------------------------ */

void Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeGlobalInit(void)

{
  long lVar1;
  undefined1 auStack_170 [24];
  undefined1 auStack_158 [24];
  undefined1 auStack_140 [264];
  long local_38;
  
  lVar1 = tpidr_el0;
  local_38 = *(long *)(lVar1 + 0x28);
  FUN_00176d58(auStack_158,4);
  FUN_0017a8a8(auStack_170);
  FUN_00178474(auStack_140,auStack_170);
  FUN_00176668(auStack_170);
  FUN_00176e44(auStack_158);
  if (*(long *)(lVar1 + 0x28) == local_38) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeInitAndRun
 * Address : 0017d9a8
 * Size    : 0xfc bytes
 * ------------------------------------------------------------------ */

void Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeInitAndRun
               (undefined8 param_1,undefined8 param_2,undefined8 param_3,undefined8 param_4)

{
  ulong uVar1;
  ulong uVar2;
  long lVar3;
  int iVar4;
  long *plVar5;
  byte local_188 [16];
  ulong local_178;
  byte local_170 [16];
  ulong local_160;
  undefined1 auStack_158 [24];
  undefined1 auStack_140 [264];
  long local_38;
  
  lVar3 = tpidr_el0;
  local_38 = *(long *)(lVar3 + 0x28);
  FUN_00176534(local_170,param_1,param_3);
  FUN_00176534(local_188,param_1,param_4);
  plVar5 = (long *)FUN_00181c0c();
  uVar1 = (ulong)local_170 | 1;
  if ((local_170[0] & 1) != 0) {
    uVar1 = local_160;
  }
  uVar2 = (ulong)local_188 | 1;
  if ((local_188[0] & 1) != 0) {
    uVar2 = local_178;
  }
  iVar4 = (**(code **)(*plVar5 + 8))(plVar5,uVar1,uVar2);
  if (iVar4 != 0) {
    FUN_00176d58(auStack_158,6);
    FUN_00177ee4(auStack_140,"initAndRun failed.");
    FUN_00176e44(auStack_158);
  }
  (**(code **)(*plVar5 + 0x68))(plVar5);
  FUN_00176668(local_188);
  FUN_00176668(local_170);
  if (*(long *)(lVar3 + 0x28) == local_38) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeInit
 * Address : 0017dad8
 * Size    : 0x120 bytes
 * ------------------------------------------------------------------ */

long * Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeInit
                 (undefined8 param_1,undefined8 param_2,undefined8 param_3,undefined8 param_4)

{
  ulong uVar1;
  ulong uVar2;
  long lVar3;
  int iVar4;
  long *plVar5;
  byte local_198 [16];
  ulong local_188;
  byte local_180 [16];
  ulong local_170;
  undefined1 auStack_168 [24];
  undefined1 auStack_150 [264];
  long local_48;
  
  lVar3 = tpidr_el0;
  local_48 = *(long *)(lVar3 + 0x28);
  FUN_00176d58(auStack_168,4);
  FUN_0017a8a8(local_180);
  FUN_00178474(auStack_150,local_180);
  FUN_00176668(local_180);
  FUN_00176e44(auStack_168);
  FUN_00176534(local_180,param_1,param_3);
  FUN_00176534(local_198,param_1,param_4);
  plVar5 = (long *)FUN_00181c0c();
  uVar1 = (ulong)local_180 | 1;
  if ((local_180[0] & 1) != 0) {
    uVar1 = local_170;
  }
  uVar2 = (ulong)local_198 | 1;
  if ((local_198[0] & 1) != 0) {
    uVar2 = local_188;
  }
  iVar4 = (**(code **)*plVar5)(plVar5,uVar1,uVar2);
  if (iVar4 != 0) {
    (**(code **)(*plVar5 + 0x68))(plVar5);
    plVar5 = (long *)0x0;
  }
  FUN_00176668(local_198);
  FUN_00176668(local_180);
  if (*(long *)(lVar3 + 0x28) == local_48) {
    return plVar5;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeRelease
 * Address : 0017dc3c
 * Size    : 0x18 bytes
 * ------------------------------------------------------------------ */

void Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeRelease
               (undefined8 param_1,undefined8 param_2,long *param_3)

{
  if (param_3 != (long *)0x0) {
                    /* WARNING: Could not recover jumptable at 0x0017dc4c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (**(code **)(*param_3 + 0x68))(param_3);
    return;
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeScanDocumentBitmap
 * Address : 0017dc54
 * Size    : 0x20c bytes
 * ------------------------------------------------------------------ */

undefined4
Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeScanDocumentBitmap
          (long *param_1,undefined8 param_2,long *param_3,undefined8 param_4,undefined4 param_5,
          undefined8 param_6)

{
  long lVar1;
  undefined4 uVar2;
  int iVar3;
  long lVar4;
  ulong uVar5;
  long lVar6;
  long local_2e0;
  long local_2d8;
  undefined8 uStack_2d0;
  int local_2c8;
  int iStack_2c4;
  undefined8 local_2c0;
  undefined1 auStack_2b8 [24];
  undefined1 auStack_2a0 [264];
  undefined1 auStack_198 [24];
  undefined1 auStack_180 [264];
  undefined8 local_78;
  float local_70;
  undefined4 local_6c;
  float local_68;
  float fStack_64;
  undefined4 local_60;
  float local_5c;
  long local_58;
  
  lVar1 = tpidr_el0;
  local_58 = *(long *)(lVar1 + 0x28);
  lVar4 = FUN_0017dea0();
  if ((param_3 == (long *)0x0) ||
     (uVar5 = FUN_00178a00(param_1,param_4,&local_2c0,&iStack_2c4,&local_2c8), (uVar5 & 1) == 0)) {
    uVar2 = 1;
  }
  else {
    local_2d8 = 0;
    uStack_2d0 = 0;
    local_2e0 = 0;
    uVar2 = (**(code **)(*param_3 + 0x10))
                      (param_3,local_2c0,iStack_2c4,local_2c8,param_5,&local_2e0);
    (**(code **)(*param_3 + 0x58))(param_3,iStack_2c4,local_2c8,&local_2e0);
    if (local_2d8 - local_2e0 == 0x20) {
      uVar5 = 8;
    }
    else {
      local_78 = 0;
      local_6c = 0;
      local_70 = (float)iStack_2c4;
      fStack_64 = (float)local_2c8;
      local_60 = 0;
      local_68 = local_70;
      local_5c = fStack_64;
      FUN_001814bc(&local_2e0,&local_78,&local_58);
      uVar5 = local_2d8 - local_2e0 >> 2;
    }
    iVar3 = (**(code **)(*param_1 + 0x558))(param_1,param_6);
    if ((ulong)(long)iVar3 < uVar5) {
      FUN_00176d58(auStack_198,7);
      FUN_00177ee4(auStack_180,"DocumentShape.cpp#ScanDocument fail, pointslen < len ...");
      FUN_00176e44(auStack_198);
      FUN_0051d5a4(local_2c0);
    }
    else {
      (**(code **)(*param_1 + 0x6a8))(param_1,param_6,0,uVar5 & 0xffffffff,local_2e0);
      FUN_0051d5a4(local_2c0);
      FUN_00176d58(auStack_2b8,4);
      FUN_00177ee4(auStack_2a0,"nativeScanDocumentBitmap use:");
      lVar6 = FUN_0017dea0();
      FUN_00178490(auStack_2a0,lVar6 - lVar4);
      FUN_00177ee4(auStack_2a0,"ms");
      FUN_00176e44(auStack_2b8);
    }
    FUN_0017ded8(&local_2e0);
  }
  if (*(long *)(lVar1 + 0x28) == local_58) {
    return uVar2;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeAlignDocumentBitmap
 * Address : 0017e1d0
 * Size    : 0x2d0 bytes
 * ------------------------------------------------------------------ */

void Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeAlignDocumentBitmap
               (long *param_1,undefined8 param_2,long *param_3,undefined8 param_4,undefined8 param_5
               ,undefined4 param_6,undefined4 param_7,undefined8 param_8,undefined8 param_9,
               undefined4 param_10)

{
  long lVar1;
  int iVar2;
  long lVar3;
  undefined8 uVar4;
  ulong uVar5;
  long lVar6;
  undefined1 auStack_378 [24];
  long local_360;
  long local_358;
  undefined8 uStack_350;
  int local_348;
  int iStack_344;
  undefined8 local_340;
  undefined4 local_338;
  undefined4 uStack_334;
  undefined1 auStack_330 [24];
  undefined1 auStack_318 [264];
  undefined1 auStack_210 [24];
  undefined1 auStack_1f8 [264];
  undefined8 local_f0;
  float local_e8;
  undefined4 local_e4;
  float local_e0;
  float fStack_dc;
  undefined4 local_d8;
  float local_d4;
  undefined1 auStack_d0 [16];
  undefined8 local_c0;
  long local_70;
  
  lVar1 = tpidr_el0;
  local_70 = *(long *)(lVar1 + 0x28);
  local_338 = param_7;
  uStack_334 = param_6;
  lVar3 = FUN_0017dea0();
  if (param_3 != (long *)0x0) {
    uVar4 = (**(code **)(*param_1 + 0x5c0))(param_1,param_4,0);
    FUN_0017def0(auStack_d0,uVar4,&uStack_334,&local_338);
    (**(code **)(*param_1 + 0x600))(param_1,param_4,uVar4,0);
    uVar5 = FUN_00178a00(param_1,param_5,&local_340,&iStack_344,&local_348);
    if ((uVar5 & 1) != 0) {
      local_358 = 0;
      uStack_350 = 0;
      local_360 = 0;
      lVar6 = (**(code **)(*param_1 + 0x5e8))(param_1,param_8,0);
      FUN_0017e508(auStack_378,lVar6,lVar6 + 0x20);
      (**(code **)(*param_1 + 0x628))(param_1,param_8,lVar6,0);
      (**(code **)(*param_3 + 0x28))
                (param_3,local_c0,local_340,param_6,param_7,iStack_344,local_348,auStack_378,
                 &local_360,param_10);
      (**(code **)(*param_3 + 0x58))(param_3,iStack_344,local_348,&local_360);
      if (local_358 - local_360 == 0x20) {
        uVar5 = 8;
      }
      else {
        local_f0 = 0;
        local_e4 = 0;
        local_e8 = (float)iStack_344;
        fStack_dc = (float)local_348;
        local_d8 = 0;
        local_e0 = local_e8;
        local_d4 = fStack_dc;
        FUN_001814bc(&local_360,&local_f0,auStack_d0);
        uVar5 = local_358 - local_360 >> 2;
      }
      iVar2 = (**(code **)(*param_1 + 0x558))(param_1,param_9);
      if ((ulong)(long)iVar2 < uVar5) {
        FUN_00176d58(auStack_210,7);
        FUN_00177ee4(auStack_1f8,"DocumentShape.cpp#AlginDocument fail, pointslen < len ...");
        FUN_00176e44(auStack_210);
        FUN_0051d5a4(local_340);
      }
      else {
        (**(code **)(*param_1 + 0x6a8))(param_1,param_9,0,uVar5 & 0xffffffff,local_360);
        FUN_0051d5a4(local_340);
        FUN_00176d58(auStack_330,4);
        FUN_00177ee4(auStack_318,"nativeAlignBitmap use:");
        lVar6 = FUN_0017dea0();
        FUN_00178490(auStack_318,lVar6 - lVar3);
        FUN_00177ee4(auStack_318,"ms");
        FUN_00176e44(auStack_330);
      }
      FUN_0017ded8(auStack_378);
      FUN_0017ded8(&local_360);
    }
    FUN_00178fe0(auStack_d0);
  }
  if (*(long *)(lVar1 + 0x28) == local_70) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeRotateDocumentYUVScanPoints
 * Address : 0017e6cc
 * Size    : 0x2bc bytes
 * ------------------------------------------------------------------ */

void Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeRotateDocumentYUVScanPoints
               (long *param_1,undefined8 param_2,undefined8 param_3,int param_4,int param_5,
               int param_6,undefined8 param_7)

{
  long lVar1;
  int iVar2;
  long lVar3;
  long lVar4;
  long lVar5;
  ulong uVar6;
  float *pfVar7;
  undefined4 *puVar8;
  long local_2c8;
  long lStack_2c0;
  undefined1 auStack_2b0 [24];
  undefined1 auStack_298 [264];
  undefined1 auStack_190 [24];
  undefined1 auStack_178 [264];
  long local_70;
  
  lVar1 = tpidr_el0;
  local_70 = *(long *)(lVar1 + 0x28);
  lVar3 = FUN_0017dea0();
  iVar2 = (**(code **)(*param_1 + 0x558))(param_1,param_3);
  if (iVar2 < 8) {
    FUN_00176d58(auStack_190,7);
    FUN_00177ee4(auStack_178,
                 "DocumentShape.cpp#rotateDocumentYUVScanPoints fail, pointslen < PT_NUMS ...");
    FUN_00176e44(auStack_190);
  }
  else {
    lVar4 = (**(code **)(*param_1 + 0x5e8))(param_1,param_3,0);
    FUN_0017e508(&local_2c8,lVar4,lVar4 + 0x20);
    if (param_6 == 1) {
      puVar8 = (undefined4 *)(local_2c8 + 4);
      for (lVar5 = 0; lVar5 != 4; lVar5 = lVar5 + 1) {
        iVar2 = 3;
        if (lVar5 != 0) {
          iVar2 = -1;
        }
        iVar2 = (int)lVar5 + iVar2;
        puVar8[-1] = ((float)param_5 - *(float *)(lVar4 + (long)(int)(iVar2 * 2 | 1) * 4)) + -1.0;
        *puVar8 = *(undefined4 *)(lVar4 + (long)(iVar2 * 2) * 4);
        puVar8 = puVar8 + 2;
      }
    }
    else if (param_6 == 2) {
      pfVar7 = (float *)(local_2c8 + 4);
      for (uVar6 = 0; uVar6 != 4; uVar6 = uVar6 + 1) {
        iVar2 = 2;
        if (1 < uVar6) {
          iVar2 = -2;
        }
        iVar2 = (int)uVar6 + iVar2;
        pfVar7[-1] = ((float)param_4 - *(float *)(lVar4 + (long)(iVar2 * 2) * 4)) + -1.0;
        *pfVar7 = ((float)param_5 - *(float *)(lVar4 + (long)(int)(iVar2 * 2 | 1) * 4)) + -1.0;
        pfVar7 = pfVar7 + 2;
      }
    }
    else if (param_6 == 3) {
      pfVar7 = (float *)(local_2c8 + 4);
      for (lVar5 = 0; lVar5 != 4; lVar5 = lVar5 + 1) {
        iVar2 = -3;
        if (lVar5 != 3) {
          iVar2 = 1;
        }
        iVar2 = (int)lVar5 + iVar2;
        pfVar7[-1] = *(float *)(lVar4 + (long)(int)(iVar2 * 2 | 1) * 4);
        *pfVar7 = ((float)param_4 - *(float *)(lVar4 + (long)(iVar2 * 2) * 4)) + -1.0;
        pfVar7 = pfVar7 + 2;
      }
    }
    (**(code **)(*param_1 + 0x628))(param_1,param_3,lVar4,0);
    (**(code **)(*param_1 + 0x6a8))(param_1,param_7,0,(ulong)(lStack_2c0 - local_2c8) >> 2);
    FUN_00176d58(auStack_2b0,6);
    FUN_00177ee4(auStack_298,"RotateDocumentYUVScanPoints use:");
    lVar4 = FUN_0017dea0();
    FUN_00178490(auStack_298,lVar4 - lVar3);
    FUN_00177ee4(auStack_298,"ms");
    FUN_00176e44(auStack_2b0);
    FUN_0017ded8(&local_2c8);
  }
  if (*(long *)(lVar1 + 0x28) != local_70) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeScanDocumentYUV
 * Address : 0017e9c4
 * Size    : 0x23c bytes
 * ------------------------------------------------------------------ */

undefined4
Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeScanDocumentYUV
          (long *param_1,undefined8 param_2,long *param_3,undefined8 param_4,int param_5,int param_6
          ,undefined4 param_7,undefined8 param_8)

{
  long lVar1;
  long lVar2;
  undefined4 uVar3;
  int iVar4;
  long lVar5;
  undefined8 uVar6;
  long lVar7;
  undefined1 *puVar8;
  long local_350;
  long local_348;
  undefined8 uStack_340;
  int local_338;
  int iStack_334;
  undefined1 auStack_330 [24];
  undefined1 auStack_318 [264];
  undefined1 auStack_210 [24];
  undefined1 auStack_1f8 [264];
  undefined8 local_f0;
  float local_e8;
  undefined4 local_e4;
  float local_e0;
  float fStack_dc;
  undefined4 local_d8;
  float local_d4;
  undefined1 auStack_d0 [16];
  undefined8 local_c0;
  long local_70;
  
  lVar1 = tpidr_el0;
  local_70 = *(long *)(lVar1 + 0x28);
  local_338 = param_6;
  iStack_334 = param_5;
  lVar5 = FUN_0017dea0();
  if (param_3 == (long *)0x0) {
    uVar3 = 1;
  }
  else {
    uVar6 = (**(code **)(*param_1 + 0x5c0))(param_1,param_4,0);
    FUN_0017def0(auStack_d0,uVar6,&iStack_334,&local_338);
    local_348 = 0;
    uStack_340 = 0;
    local_350 = 0;
    uVar3 = (**(code **)(*param_3 + 0x10))(param_3,local_c0,param_5,param_6,param_7,&local_350);
    (**(code **)(*param_3 + 0x58))(param_3,param_5,param_6,&local_350);
    if (local_348 - local_350 != 0x20) {
      local_e8 = (float)param_5;
      fStack_dc = (float)param_6;
      local_f0 = 0;
      local_e4 = 0;
      local_d8 = 0;
      local_e0 = local_e8;
      local_d4 = fStack_dc;
      FUN_001814bc(&local_350,&local_f0,auStack_d0);
    }
    (**(code **)(*param_1 + 0x600))(param_1,param_4,uVar6,0);
    lVar2 = local_348;
    lVar7 = local_350;
    iVar4 = (**(code **)(*param_1 + 0x558))(param_1,param_8);
    if ((ulong)(long)iVar4 < (ulong)(lVar2 - lVar7 >> 2)) {
      FUN_00176d58(auStack_210,7);
      FUN_00177ee4(auStack_1f8,"DocumentShape.cpp#ScanDocument fail, pointslen < len ...");
      puVar8 = auStack_210;
    }
    else {
      (**(code **)(*param_1 + 0x6a8))(param_1,param_8,0,(ulong)(lVar2 - lVar7) >> 2,local_350);
      FUN_00176d58(auStack_330,4);
      FUN_00177ee4(auStack_318,"nativeScanDocumentYUV use:");
      lVar7 = FUN_0017dea0();
      FUN_00178490(auStack_318,lVar7 - lVar5);
      FUN_00177ee4(auStack_318,"ms");
      puVar8 = auStack_330;
    }
    FUN_00176e44(puVar8);
    FUN_0017ded8(&local_350);
    FUN_00178fe0(auStack_d0);
  }
  if (*(long *)(lVar1 + 0x28) == local_70) {
    return uVar3;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeCropImageBitmap
 * Address : 0017ec48
 * Size    : 0x290 bytes
 * ------------------------------------------------------------------ */

undefined8
Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeCropImageBitmap
          (long *param_1,undefined8 param_2,long *param_3,undefined8 param_4,undefined8 param_5,
          undefined4 param_6)

{
  long lVar1;
  int iVar2;
  long lVar3;
  long lVar4;
  ulong uVar5;
  undefined8 uVar6;
  undefined1 auStack_308 [24];
  undefined8 local_2f0;
  undefined4 local_2e8;
  undefined4 uStack_2e4;
  undefined8 local_2e0;
  undefined4 local_2d8;
  undefined4 uStack_2d4;
  undefined8 local_2d0;
  undefined1 auStack_2c8 [24];
  undefined1 auStack_2b0 [24];
  undefined1 auStack_298 [264];
  undefined1 auStack_190 [24];
  undefined1 auStack_178 [264];
  long local_70;
  
  lVar1 = tpidr_el0;
  local_70 = *(long *)(lVar1 + 0x28);
  lVar3 = FUN_0017dea0();
  if (param_3 == (long *)0x0) {
    uVar6 = 0;
  }
  else {
    FUN_00176d58(auStack_190,4);
    FUN_00177ee4(auStack_178,"enter DocumentShape.cpp#CropImage... documentType:");
    FUN_00179bc8(auStack_178,param_6);
    FUN_00176e44(auStack_190);
    iVar2 = (**(code **)(*param_1 + 0x558))(param_1,param_5);
    lVar4 = (**(code **)(*param_1 + 0x5e8))(param_1,param_5,0);
    FUN_0017e508(auStack_2c8,lVar4,lVar4 + (long)iVar2 * 4);
    local_2e0 = 0;
    uVar5 = FUN_00178a00(param_1,param_4,&local_2d0,&uStack_2d4,&local_2d8);
    uVar6 = local_2d0;
    if ((uVar5 & 1) == 0) {
      (**(code **)(*param_1 + 0x628))(param_1,param_5,lVar4,0);
      uVar6 = 0;
    }
    else {
      FUN_0017ef54(auStack_308,auStack_2c8);
      uVar5 = (**(code **)(*param_3 + 0x18))
                        (param_3,uVar6,uStack_2d4,local_2d8,auStack_308,&local_2f0,&uStack_2e4,
                         &local_2e8,param_6,0);
      FUN_0017ded8(auStack_308);
      if ((uVar5 & 1) == 0) {
        thunk_FUN_0051d5a4(local_2d0);
        (**(code **)(*param_1 + 0x628))(param_1,param_5,lVar4,0);
        uVar6 = 0;
      }
      else {
        uVar6 = FUN_00178be4(param_1,param_4);
        local_2e0 = FUN_00178ce4(param_1,uStack_2e4,local_2e8,uVar6);
        FUN_00178afc(param_1,local_2f0,uStack_2e4,local_2e8,&local_2e0);
        thunk_FUN_0051d5a4(local_2f0);
        thunk_FUN_0051d5a4(local_2d0);
        (**(code **)(*param_1 + 0x628))(param_1,param_5,lVar4,0);
        FUN_00176d58(auStack_2b0,4);
        FUN_00177ee4(auStack_298,"nativeCropImageBitmap use:");
        lVar4 = FUN_0017dea0();
        FUN_00178490(auStack_298,lVar4 - lVar3);
        FUN_00177ee4(auStack_298,"ms");
        FUN_00176e44(auStack_2b0);
        uVar6 = local_2e0;
      }
    }
    FUN_0017ded8(auStack_2c8);
  }
  if (*(long *)(lVar1 + 0x28) != local_70) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  return uVar6;
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeEnhanceBitmap
 * Address : 0017efd0
 * Size    : 0x264 bytes
 * ------------------------------------------------------------------ */

void Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeEnhanceBitmap
               (undefined8 param_1,undefined8 param_2,long *param_3,long param_4,undefined4 param_5,
               char param_6)

{
  long lVar1;
  int iVar2;
  long lVar3;
  undefined8 uVar4;
  long lVar5;
  long lVar6;
  undefined1 auStack_3fc [4];
  undefined4 local_3f8;
  undefined4 local_3f4;
  undefined4 uStack_3f0;
  int local_3ec;
  undefined8 local_3e0;
  undefined4 local_3d4;
  undefined1 auStack_3d0 [24];
  undefined1 auStack_3b8 [264];
  undefined1 auStack_2b0 [24];
  undefined1 auStack_298 [264];
  undefined1 auStack_190 [24];
  undefined1 auStack_178 [280];
  
  lVar1 = tpidr_el0;
  lVar6 = *(long *)(lVar1 + 0x28);
  local_3d4 = param_5;
  lVar3 = FUN_0017dea0();
  if (param_3 != (long *)0x0) {
    FUN_00176d58(auStack_190,4);
    FUN_00177ee4(auStack_178,"enter DocumentShape.cpp#EnhanceImage...");
    FUN_00176e44(auStack_190);
    if ((((param_4 != 0) && (iVar2 = AndroidBitmap_getInfo(param_1,param_4,&local_3f8), -1 < iVar2))
        && (local_3ec == 1)) &&
       (iVar2 = AndroidBitmap_lockPixels(param_1,param_4,&local_3e0), -1 < iVar2)) {
      FUN_00176d58(auStack_2b0,4);
      FUN_00177ee4(auStack_298,"AndroidBitmapInfo width:");
      FUN_00179bc8(auStack_298,local_3f8);
      FUN_00177ee4(auStack_298,", height:");
      FUN_00179bc8(auStack_298,local_3f4);
      FUN_00177ee4(auStack_298,", stride:");
      FUN_00179bc8(auStack_298,uStack_3f0);
      FUN_00176e44(auStack_2b0);
      FUN_0017884c(local_3e0,local_3e0,local_3f8,local_3f4);
      (**(code **)(*param_3 + 0x20))
                (param_3,local_3e0,local_3f8,local_3f4,param_5,local_3e0,auStack_3fc,param_6 != '\0'
                );
      FUN_00178904(local_3e0,local_3e0,local_3f8,local_3f4);
      AndroidBitmap_unlockPixels(param_1,param_4);
      FUN_00176d58(auStack_3d0,4);
      FUN_00177ee4(auStack_3b8,"nativeEnhanceBitmap");
      uVar4 = FUN_0017f264(&DAT_018ee0b8,&local_3d4);
      FUN_00178474(auStack_3b8,uVar4);
      FUN_00177ee4(auStack_3b8," use:");
      lVar5 = FUN_0017dea0();
      FUN_00178490(auStack_3b8,lVar5 - lVar3);
      FUN_00177ee4(auStack_3b8,"ms");
      FUN_00176e44(auStack_3d0);
      uVar4 = 0;
      goto LAB_0017f200;
    }
  }
  uVar4 = 1;
LAB_0017f200:
  if (*(long *)(lVar1 + 0x28) == lVar6) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail(uVar4);
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeCropAndEnhanceBitmap
 * Address : 0017f2c0
 * Size    : 0x450 bytes
 * ------------------------------------------------------------------ */

undefined8
Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeCropAndEnhanceBitmap
          (long *param_1,undefined8 param_2,long *param_3,undefined8 param_4,undefined8 param_5,
          undefined4 param_6,undefined4 param_7,char param_8)

{
  long lVar1;
  int iVar2;
  long lVar3;
  long lVar4;
  ulong uVar5;
  long lVar6;
  long lVar7;
  undefined8 uVar8;
  long lVar9;
  undefined1 auStack_794 [4];
  undefined1 auStack_790 [24];
  undefined8 local_778;
  undefined4 local_770;
  undefined4 local_76c;
  undefined8 local_768;
  undefined4 local_760;
  undefined4 uStack_75c;
  undefined8 local_758;
  undefined1 auStack_750 [28];
  undefined4 local_734;
  undefined1 auStack_730 [24];
  undefined1 auStack_718 [264];
  undefined1 auStack_610 [24];
  undefined1 auStack_5f8 [264];
  undefined1 auStack_4f0 [24];
  undefined1 auStack_4d8 [264];
  undefined1 auStack_3d0 [24];
  undefined1 auStack_3b8 [264];
  undefined1 auStack_2b0 [24];
  undefined1 auStack_298 [264];
  undefined1 auStack_190 [24];
  undefined1 auStack_178 [280];
  
  lVar1 = tpidr_el0;
  lVar9 = *(long *)(lVar1 + 0x28);
  local_734 = param_6;
  lVar3 = FUN_0017dea0();
  if (param_3 == (long *)0x0) {
    uVar8 = 0;
  }
  else {
    FUN_00176d58(auStack_190,4);
    FUN_00177ee4(auStack_178,"enter DocumentShape.cpp#CropAndEnhanceBitmap... documentType:");
    FUN_00179bc8(auStack_178,param_7);
    FUN_00176e44(auStack_190);
    iVar2 = (**(code **)(*param_1 + 0x558))(param_1,param_5);
    lVar4 = (**(code **)(*param_1 + 0x5e8))(param_1,param_5,0);
    FUN_0017e508(auStack_750,lVar4,lVar4 + (long)iVar2 * 4);
    local_768 = 0;
    uVar5 = FUN_00178a00(param_1,param_4,&local_758,&uStack_75c,&local_760);
    uVar8 = 0;
    if ((uVar5 & 1) != 0) {
      lVar6 = FUN_0017dea0();
      uVar8 = local_758;
      FUN_0017ef54(auStack_790,auStack_750);
      uVar5 = (**(code **)(*param_3 + 0x18))
                        (param_3,uVar8,uStack_75c,local_760,auStack_790,&local_778,&local_76c,
                         &local_770,param_7,param_8 != '\0');
      FUN_0017ded8(auStack_790);
      FUN_00176d58(auStack_2b0,4);
      FUN_00177ee4(auStack_298,"nativeCropAndEnhanceBitmap crop out_w:");
      FUN_00179bc8(auStack_298,local_76c);
      FUN_00177ee4(auStack_298,", out_h:");
      FUN_00179bc8(auStack_298,local_770);
      FUN_00177ee4(auStack_298," use:");
      lVar7 = FUN_0017dea0();
      FUN_00178490(auStack_298,lVar7 - lVar6);
      FUN_00177ee4(auStack_298,"ms");
      FUN_00176e44(auStack_2b0);
      FUN_00176d58(auStack_3d0,4);
      FUN_00177ee4(auStack_3b8,"nativeCropAndEnhanceBitmapCROP use:");
      lVar7 = FUN_0017dea0();
      FUN_00178490(auStack_3b8,lVar7 - lVar6);
      FUN_00177ee4(auStack_3b8,"ms");
      FUN_00176e44(auStack_3d0);
      if ((uVar5 & 1) == 0) {
        thunk_FUN_0051d5a4(local_758);
        uVar8 = 0;
      }
      else {
        lVar6 = FUN_0017dea0();
        (**(code **)(*param_3 + 0x20))
                  (param_3,local_778,local_76c,local_770,local_734,local_778,auStack_794,
                   param_8 != '\0');
        FUN_00176d58(auStack_4f0,4);
        FUN_00177ee4(auStack_4d8,"nativeCropAndEnhanceBitmap");
        uVar8 = FUN_0017f264(&DAT_018ee0b8,&local_734);
        FUN_00178474(auStack_4d8,uVar8);
        FUN_00177ee4(auStack_4d8," use:");
        lVar7 = FUN_0017dea0();
        FUN_00178490(auStack_4d8,lVar7 - lVar6);
        FUN_00177ee4(auStack_4d8,"ms");
        FUN_00176e44(auStack_4f0);
        lVar6 = FUN_0017dea0();
        uVar8 = FUN_00178be4(param_1,param_4);
        local_768 = FUN_00178ce4(param_1,local_76c,local_770,uVar8);
        FUN_00178afc(param_1,local_778,local_76c,local_770,&local_768);
        FUN_00176d58(auStack_610,4);
        FUN_00177ee4(auStack_5f8,"nativeCropAndEnhanceBitmap rgb2bitmap use:");
        lVar7 = FUN_0017dea0();
        FUN_00178490(auStack_5f8,lVar7 - lVar6);
        FUN_00177ee4(auStack_5f8,"ms");
        FUN_00176e44(auStack_610);
        (**(code **)(*param_1 + 0x628))(param_1,param_5,lVar4,0);
        thunk_FUN_0051d5a4(local_778);
        thunk_FUN_0051d5a4(local_758);
        FUN_00176d58(auStack_730,4);
        FUN_00177ee4(auStack_718,"nativeCropAndEnhanceBitmap use:");
        lVar4 = FUN_0017dea0();
        FUN_00178490(auStack_718,lVar4 - lVar3);
        FUN_00177ee4(auStack_718,"ms");
        FUN_00176e44(auStack_730);
        uVar8 = local_768;
      }
    }
    FUN_0017ded8(auStack_750);
  }
  if (*(long *)(lVar1 + 0x28) == lVar9) {
    return uVar8;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeCropAndEnhanceYUV
 * Address : 0017f7a4
 * Size    : 0x4e0 bytes
 * ------------------------------------------------------------------ */

undefined8
Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeCropAndEnhanceYUV
          (long *param_1,undefined8 param_2,long *param_3,undefined8 param_4,undefined4 param_5,
          undefined4 param_6,undefined8 param_7,undefined4 param_8,undefined4 param_9,char param_10,
          undefined4 param_11)

{
  long lVar1;
  int iVar2;
  long lVar3;
  undefined8 uVar4;
  long lVar5;
  long lVar6;
  ulong uVar7;
  long lVar8;
  undefined8 local_970;
  undefined1 auStack_964 [4];
  undefined1 auStack_960 [24];
  undefined8 local_948;
  undefined4 local_940;
  undefined4 local_93c;
  undefined1 auStack_938 [28];
  undefined4 local_91c;
  undefined4 local_918;
  undefined4 uStack_914;
  undefined1 auStack_910 [24];
  undefined1 auStack_8f8 [264];
  undefined1 auStack_7f0 [24];
  undefined1 auStack_7d8 [264];
  undefined1 auStack_6d0 [24];
  undefined1 auStack_6b8 [264];
  undefined1 auStack_5b0 [24];
  undefined1 auStack_598 [264];
  undefined1 auStack_490 [24];
  undefined1 auStack_478 [264];
  undefined1 auStack_370 [24];
  undefined1 auStack_358 [264];
  undefined1 auStack_250 [24];
  undefined1 auStack_238 [264];
  undefined1 auStack_130 [16];
  undefined8 local_120;
  undefined1 auStack_d0 [16];
  undefined8 local_c0;
  long local_70;
  
  lVar1 = tpidr_el0;
  local_70 = *(long *)(lVar1 + 0x28);
  local_91c = param_8;
  local_918 = param_6;
  uStack_914 = param_5;
  lVar3 = FUN_0017dea0();
  if (param_3 == (long *)0x0) {
    uVar4 = 0;
  }
  else {
    FUN_00176d58(auStack_250,4);
    FUN_00177ee4(auStack_238,"enter DocumentShape.cpp#CropAndEnhanceYUV... documentType:");
    FUN_00179bc8(auStack_238,param_9);
    FUN_00176e44(auStack_250);
    uVar4 = (**(code **)(*param_1 + 0x5c0))(param_1,param_4,0);
    lVar5 = (**(code **)(*param_1 + 0x5e8))(param_1,param_7,0);
    iVar2 = (**(code **)(*param_1 + 0x558))(param_1,param_7);
    FUN_0017e508(auStack_938,lVar5,lVar5 + (long)iVar2 * 4);
    FUN_0017def0(auStack_d0,uVar4,&uStack_914,&local_918);
    FUN_00176d58(auStack_370,4);
    FUN_00177ee4(auStack_358,"nativeCropAndEnhanceYUV in_w:");
    FUN_00179bc8(auStack_358,param_5);
    FUN_00177ee4(auStack_358,", in_h:");
    FUN_00179bc8(auStack_358,param_6);
    FUN_00176e44(auStack_370);
    lVar6 = FUN_0017dea0();
    FUN_0017ef54(auStack_960,auStack_938);
    uVar7 = (**(code **)(*param_3 + 0x18))
                      (param_3,local_c0,param_5,param_6,auStack_960,&local_948,&local_93c,&local_940
                       ,param_9,param_10 != '\0');
    FUN_0017ded8(auStack_960);
    FUN_00176d58(auStack_490,4);
    FUN_00177ee4(auStack_478,"nativeCropAndEnhanceYUV crop out_w:");
    FUN_00179bc8(auStack_478,local_93c);
    FUN_00177ee4(auStack_478,", out_h:");
    FUN_00179bc8(auStack_478,local_940);
    FUN_00177ee4(auStack_478," use:");
    lVar8 = FUN_0017dea0();
    FUN_00178490(auStack_478,lVar8 - lVar6);
    FUN_00177ee4(auStack_478,"ms");
    FUN_00176e44(auStack_490);
    FUN_00176d58(auStack_5b0,4);
    FUN_00177ee4(auStack_598,"nativeCropAndEnhanceYUVCROP use:");
    lVar8 = FUN_0017dea0();
    FUN_00178490(auStack_598,lVar8 - lVar6);
    FUN_00177ee4(auStack_598,"ms");
    FUN_00176e44(auStack_5b0);
    (**(code **)(*param_1 + 0x600))(param_1,param_4,uVar4,0);
    uVar4 = 0;
    if ((uVar7 & 1) != 0) {
      lVar6 = FUN_0017dea0();
      (**(code **)(*param_3 + 0x20))
                (param_3,local_948,local_93c,local_940,local_91c,local_948,auStack_964,
                 param_10 != '\0');
      FUN_00176d58(auStack_6d0,4);
      FUN_00177ee4(auStack_6b8,"nativeCropAndEnhanceYUV");
      uVar4 = FUN_0017f264(&DAT_018ee0b8,&local_91c);
      FUN_00178474(auStack_6b8,uVar4);
      FUN_00177ee4(auStack_6b8," use:");
      lVar8 = FUN_0017dea0();
      FUN_00178490(auStack_6b8,lVar8 - lVar6);
      FUN_00177ee4(auStack_6b8,"ms");
      FUN_00176e44(auStack_6d0);
      FUN_0017e584(auStack_130,local_948,&local_93c,&local_940,param_11);
      lVar6 = FUN_0017dea0();
      local_970 = FUN_00178ce4(param_1,local_93c,local_940,0);
      FUN_00178afc(param_1,local_120,local_93c,local_940,&local_970);
      FUN_00176d58(auStack_7f0,4);
      FUN_00177ee4(auStack_7d8,"nativeCropAndEnhanceYUV rgb2bitmap use:");
      lVar8 = FUN_0017dea0();
      FUN_00178490(auStack_7d8,lVar8 - lVar6);
      FUN_00177ee4(auStack_7d8,"ms");
      FUN_00176e44(auStack_7f0);
      (**(code **)(*param_1 + 0x628))(param_1,param_7,lVar5,0);
      thunk_FUN_0051d5a4(local_948);
      FUN_00176d58(auStack_910,4);
      FUN_00177ee4(auStack_8f8,"nativeCropAndEnhanceYUV use:");
      lVar5 = FUN_0017dea0();
      FUN_00178490(auStack_8f8,lVar5 - lVar3);
      FUN_00177ee4(auStack_8f8,"ms");
      FUN_00176e44(auStack_910);
      uVar4 = local_970;
      FUN_00178fe0(auStack_130);
    }
    FUN_00178fe0(auStack_d0);
    FUN_0017ded8(auStack_938);
  }
  if (*(long *)(lVar1 + 0x28) == local_70) {
    return uVar4;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeNormalizationPoints
 * Address : 0017fd4c
 * Size    : 0x414 bytes
 * ------------------------------------------------------------------ */

uint Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeNormalizationPoints
               (long *param_1,undefined8 param_2,long *param_3,undefined8 param_4)

{
  undefined *puVar1;
  undefined **ppuVar2;
  undefined **ppuVar3;
  long lVar4;
  undefined *puVar5;
  int iVar6;
  uint uVar7;
  long lVar8;
  long lVar9;
  undefined8 uVar10;
  long lVar11;
  ulong uVar12;
  long lVar13;
  undefined8 local_520;
  undefined8 uStack_518;
  undefined8 local_510;
  long local_508;
  long local_500;
  undefined8 local_4f8;
  undefined1 auStack_4f0 [24];
  undefined1 auStack_4d8 [264];
  undefined1 auStack_3d0 [24];
  undefined1 auStack_3b8 [264];
  undefined1 auStack_2b0 [24];
  undefined1 auStack_298 [264];
  undefined *local_190 [11];
  undefined8 local_138 [4];
  undefined4 local_118;
  undefined *local_110 [17];
  undefined8 local_88;
  undefined4 local_80;
  long local_70;
  
  lVar4 = tpidr_el0;
  local_70 = *(long *)(lVar4 + 0x28);
  lVar8 = FUN_0017dea0();
  if (param_3 == (long *)0x0) {
    uVar7 = 1;
  }
  else {
    lVar9 = (**(code **)(*param_1 + 0x5e8))(param_1,param_4,0);
    iVar6 = (**(code **)(*param_1 + 0x558))(param_1,param_4);
    local_508 = 0;
    local_500 = 0;
    local_4f8 = 0;
    lVar11 = lVar9;
    for (lVar13 = 0; puVar5 = PTR_DAT_018e1e70, lVar13 < iVar6; lVar13 = lVar13 + 1) {
      FUN_001801fc(&local_508,local_500,lVar11);
      lVar11 = lVar11 + 4;
    }
    local_110[0] = PTR_DAT_018e13e0 + 0x40;
    local_190[0] = PTR_DAT_018e13e0 + 0x18;
    puVar1 = PTR_DAT_018e1e70 + 0x40;
    ppuVar2 = local_190 + 3;
    local_190[1] = (undefined *)0x0;
    local_190[2] = puVar1;
    FUN_004fa920(local_110,ppuVar2);
    local_190[0] = puVar5 + 0x18;
    local_110[0] = puVar5 + 0x68;
    local_88 = 0;
    local_80 = 0xffffffff;
    local_190[2] = puVar1;
    FUN_00177484(ppuVar2);
    local_138[2] = 0;
    local_138[1] = 0;
    local_138[0] = 0;
    local_190[3] = PTR_DAT_018e1c60 + 0x10;
    for (lVar13 = 0x58; lVar13 != 0x70; lVar13 = lVar13 + 8) {
      *(undefined8 *)((long)local_190 + lVar13) = 0;
    }
    ppuVar3 = local_190 + 2;
    local_138[3] = 0;
    local_118 = 0x18;
    FUN_00177ee4(ppuVar3,"origin coordinate:");
    for (uVar12 = 0; uVar12 < (ulong)(local_500 - local_508 >> 2); uVar12 = uVar12 + 2) {
      FUN_00177ee4(ppuVar3,"(");
      uVar10 = FUN_00179df0(*(undefined4 *)(local_508 + uVar12 * 4));
      FUN_00177ee4(uVar10,", ");
      uVar10 = FUN_00179df0(*(undefined4 *)(local_508 + uVar12 * 4 + 4));
      FUN_00177ee4(uVar10,") ");
    }
    FUN_00176d58(auStack_2b0,4);
    FUN_00177dcc(&local_520,ppuVar2);
    FUN_00178474(auStack_298,&local_520);
    FUN_00176668(&local_520);
    FUN_00176e44(auStack_2b0);
    uVar7 = (**(code **)(*param_3 + 0x50))(0x41f00000,param_3,&local_508);
    local_520 = 0;
    uStack_518 = 0;
    local_510 = 0;
    FUN_00176718(&local_520,&DAT_005378ad,0);
    FUN_00181954(ppuVar2,&local_520);
    FUN_00176668(&local_520);
    FUN_00177ee4(ppuVar3,"normalized coordinate:");
    uVar7 = ~uVar7 & 1;
    for (uVar12 = 0; uVar12 < (ulong)(local_500 - local_508 >> 2); uVar12 = uVar12 + 2) {
      FUN_00177ee4(ppuVar3,"(");
      uVar10 = FUN_00179df0(*(undefined4 *)(local_508 + uVar12 * 4));
      FUN_00177ee4(uVar10,", ");
      uVar10 = FUN_00179df0(*(undefined4 *)(local_508 + uVar12 * 4 + 4));
      FUN_00177ee4(uVar10,") ");
    }
    FUN_00176d58(auStack_3d0,4);
    FUN_00177dcc(&local_520,ppuVar2);
    FUN_00178474(auStack_3b8,&local_520);
    FUN_00176668(&local_520);
    FUN_00176e44(auStack_3d0);
    for (lVar13 = 0; lVar13 < iVar6; lVar13 = lVar13 + 1) {
      *(undefined4 *)(lVar9 + lVar13 * 4) = *(undefined4 *)(local_508 + lVar13 * 4);
    }
    (**(code **)(*param_1 + 0x628))(param_1,param_4,lVar9,0);
    FUN_00176d58(auStack_4f0,4);
    FUN_00177ee4(auStack_4d8,"nativeNormalizationPoints use:");
    lVar13 = FUN_0017dea0();
    FUN_00178490(auStack_4d8,lVar13 - lVar8);
    FUN_00177ee4(auStack_4d8,"ms");
    FUN_00176e44(auStack_4f0);
    FUN_00177404(local_190);
    FUN_0017ded8(&local_508);
  }
  if (*(long *)(lVar4 + 0x28) == local_70) {
    return uVar7;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeDewarpBitmap
 * Address : 00180420
 * Size    : 0x200 bytes
 * ------------------------------------------------------------------ */

void Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeDewarpBitmap
               (undefined8 param_1,undefined8 param_2,long *param_3,long param_4)

{
  long lVar1;
  int iVar2;
  long lVar3;
  long lVar4;
  undefined8 uVar5;
  undefined4 local_3f0;
  undefined4 uStack_3ec;
  undefined4 local_3e8;
  int local_3e4;
  undefined8 local_3d8;
  undefined1 auStack_3d0 [24];
  undefined1 auStack_3b8 [264];
  undefined1 auStack_2b0 [24];
  undefined1 auStack_298 [264];
  undefined1 auStack_190 [24];
  undefined1 auStack_178 [264];
  long local_70;
  
  lVar1 = tpidr_el0;
  local_70 = *(long *)(lVar1 + 0x28);
  lVar3 = FUN_0017dea0();
  if (param_3 != (long *)0x0) {
    FUN_00176d58(auStack_190,4);
    FUN_00177ee4(auStack_178,"enter DocumentShape.cpp#EnhanceImage...");
    FUN_00176e44(auStack_190);
    if ((((param_4 != 0) && (iVar2 = AndroidBitmap_getInfo(param_1,param_4,&local_3f0), -1 < iVar2))
        && (local_3e4 == 1)) &&
       (iVar2 = AndroidBitmap_lockPixels(param_1,param_4,&local_3d8), -1 < iVar2)) {
      FUN_00176d58(auStack_2b0,4);
      FUN_00177ee4(auStack_298,"AndroidBitmapInfo width:");
      FUN_00179bc8(auStack_298,local_3f0);
      FUN_00177ee4(auStack_298,", height:");
      FUN_00179bc8(auStack_298,uStack_3ec);
      FUN_00177ee4(auStack_298,", stride:");
      FUN_00179bc8(auStack_298,local_3e8);
      FUN_00176e44(auStack_2b0);
      FUN_0017884c(local_3d8,local_3d8,local_3f0,uStack_3ec);
      (**(code **)(*param_3 + 0x30))(param_3,local_3d8,local_3f0,uStack_3ec,local_3d8);
      FUN_00178904(local_3d8,local_3d8,local_3f0,uStack_3ec);
      AndroidBitmap_unlockPixels(param_1,param_4);
      FUN_00176d58(auStack_3d0,4);
      FUN_00177ee4(auStack_3b8,"nativeDewarpBitmap use:");
      lVar4 = FUN_0017dea0();
      FUN_00178490(auStack_3b8,lVar4 - lVar3);
      FUN_00177ee4(auStack_3b8,"ms");
      FUN_00176e44(auStack_3d0);
      uVar5 = 0;
      goto LAB_001805ec;
    }
  }
  uVar5 = 1;
LAB_001805ec:
  if (*(long *)(lVar1 + 0x28) == local_70) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail(uVar5);
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeFingerRemoveBitmap
 * Address : 00180650
 * Size    : 0x240 bytes
 * ------------------------------------------------------------------ */

void Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeFingerRemoveBitmap
               (undefined8 param_1,undefined8 param_2,long *param_3,long param_4,undefined4 param_5)

{
  long lVar1;
  int iVar2;
  long lVar3;
  long lVar4;
  undefined8 uVar5;
  undefined4 local_510;
  undefined4 uStack_50c;
  undefined4 local_508;
  int local_504;
  undefined8 local_4f8;
  undefined1 auStack_4f0 [24];
  undefined1 auStack_4d8 [264];
  undefined1 auStack_3d0 [24];
  undefined1 auStack_3b8 [264];
  undefined1 auStack_2b0 [24];
  undefined1 auStack_298 [264];
  undefined1 auStack_190 [24];
  undefined1 auStack_178 [264];
  long local_70;
  
  lVar1 = tpidr_el0;
  local_70 = *(long *)(lVar1 + 0x28);
  FUN_00176d58(auStack_190,4);
  FUN_00177ee4(auStack_178,"nativeFingerRemoveBitmap inpaintType:");
  FUN_00179bc8(auStack_178,param_5);
  FUN_00176e44(auStack_190);
  lVar3 = FUN_0017dea0();
  if (param_3 != (long *)0x0) {
    FUN_00176d58(auStack_2b0,4);
    FUN_00177ee4(auStack_298,"enter DocumentShape.cpp#EnhanceImage...");
    FUN_00176e44(auStack_2b0);
    if ((((param_4 != 0) && (iVar2 = AndroidBitmap_getInfo(param_1,param_4,&local_510), -1 < iVar2))
        && (local_504 == 1)) &&
       (iVar2 = AndroidBitmap_lockPixels(param_1,param_4,&local_4f8), -1 < iVar2)) {
      FUN_00176d58(auStack_3d0,4);
      FUN_00177ee4(auStack_3b8,"AndroidBitmapInfo width:");
      FUN_00179bc8(auStack_3b8,local_510);
      FUN_00177ee4(auStack_3b8,", height:");
      FUN_00179bc8(auStack_3b8,uStack_50c);
      FUN_00177ee4(auStack_3b8,", stride:");
      FUN_00179bc8(auStack_3b8,local_508);
      FUN_00176e44(auStack_3d0);
      FUN_0017884c(local_4f8,local_4f8,local_510,uStack_50c);
      (**(code **)(*param_3 + 0x38))(param_3,local_4f8,local_510,uStack_50c,local_4f8,param_5);
      FUN_00178904(local_4f8,local_4f8,local_510,uStack_50c);
      AndroidBitmap_unlockPixels(param_1,param_4);
      FUN_00176d58(auStack_4f0,4);
      FUN_00177ee4(auStack_4d8,"nativeFingerRemoveBitmap use:");
      lVar4 = FUN_0017dea0();
      FUN_00178490(auStack_4d8,lVar4 - lVar3);
      FUN_00177ee4(auStack_4d8,"ms");
      FUN_00176e44(auStack_4f0);
      uVar5 = 0;
      goto LAB_0018085c;
    }
  }
  uVar5 = 1;
LAB_0018085c:
  if (*(long *)(lVar1 + 0x28) == local_70) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail(uVar5);
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeHandwriteRemoveBitmap
 * Address : 001808cc
 * Size    : 0x1dc bytes
 * ------------------------------------------------------------------ */

void Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeHandwriteRemoveBitmap
               (undefined8 param_1,undefined8 param_2,long *param_3,long param_4,long param_5)

{
  long lVar1;
  int iVar2;
  long lVar3;
  undefined8 uVar4;
  long lVar5;
  undefined1 auStack_1d0 [12];
  int local_1c4;
  undefined4 local_1b8;
  undefined4 uStack_1b4;
  int local_1ac;
  undefined8 local_1a0;
  undefined8 uStack_198;
  undefined1 auStack_190 [24];
  undefined1 auStack_178 [264];
  long local_70;
  
  lVar1 = tpidr_el0;
  local_70 = *(long *)(lVar1 + 0x28);
  lVar3 = FUN_0017dea0();
  uVar4 = 1;
  if (((param_3 != (long *)0x0) && (param_4 != 0)) && (param_5 != 0)) {
    iVar2 = AndroidBitmap_getInfo(param_1,param_4,&local_1b8);
    if ((((iVar2 < 0) || (iVar2 = AndroidBitmap_getInfo(param_1,param_5,auStack_1d0), iVar2 < 0)) ||
        ((local_1ac != 1 ||
         ((local_1c4 != 1 ||
          (iVar2 = AndroidBitmap_lockPixels(param_1,param_4,&uStack_198), iVar2 < 0)))))) ||
       (iVar2 = AndroidBitmap_lockPixels(param_1,param_5,&local_1a0), iVar2 < 0)) {
      uVar4 = 1;
    }
    else {
      FUN_0017884c(uStack_198,uStack_198,local_1b8,uStack_1b4);
      FUN_0017884c(local_1a0,local_1a0,local_1b8,uStack_1b4);
      (**(code **)(*param_3 + 0x40))(param_3,uStack_198,local_1b8,uStack_1b4,uStack_198,local_1a0);
      FUN_00178904(uStack_198,uStack_198,local_1b8,uStack_1b4);
      FUN_00178904(local_1a0,local_1a0,local_1b8,uStack_1b4);
      AndroidBitmap_unlockPixels(param_1,param_4);
      AndroidBitmap_unlockPixels(param_1,param_5);
      FUN_00176d58(auStack_190,4);
      FUN_00177ee4(auStack_178,"nativeHandwriteRemoveBitmap use:");
      lVar5 = FUN_0017dea0();
      FUN_00178490(auStack_178,lVar5 - lVar3);
      FUN_00177ee4(auStack_178,"ms");
      FUN_00176e44(auStack_190);
      uVar4 = 0;
    }
  }
  if (*(long *)(lVar1 + 0x28) == local_70) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail(uVar4);
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeGetDelegateColor
 * Address : 00180ac0
 * Size    : 0x3cc bytes
 * ------------------------------------------------------------------ */

void Java_com_xiaomi_ocr_sdk_imgprocess_DocumentProcessJNI_nativeGetDelegateColor
               (long *param_1,undefined8 param_2,long *param_3,long param_4,undefined8 param_5)

{
  long lVar1;
  int iVar2;
  long lVar3;
  undefined8 uVar4;
  long lVar5;
  long lVar6;
  long local_570;
  undefined4 *local_568;
  undefined8 local_560;
  undefined4 local_558;
  undefined4 uStack_554;
  long local_550;
  long lStack_548;
  undefined8 local_540;
  long local_530;
  long lStack_528;
  undefined8 local_520;
  undefined4 local_518;
  undefined4 uStack_514;
  undefined4 local_510;
  undefined4 uStack_50c;
  int local_504;
  undefined8 local_4f8;
  undefined1 auStack_4f0 [24];
  undefined1 auStack_4d8 [264];
  undefined1 auStack_3d0 [24];
  undefined1 auStack_3b8 [264];
  undefined1 auStack_2b0 [24];
  undefined1 auStack_298 [264];
  undefined4 local_190 [2];
  long *local_188;
  undefined8 local_180;
  undefined4 local_130 [2];
  undefined1 *local_128;
  undefined8 local_120;
  undefined1 auStack_d0 [96];
  long local_70;
  
  lVar1 = tpidr_el0;
  local_70 = *(long *)(lVar1 + 0x28);
  FUN_00176d58(auStack_2b0,6);
  FUN_00177ee4(auStack_298,"getcolor start");
  FUN_00176e44(auStack_2b0);
  lVar3 = FUN_0017dea0();
  if (param_3 == (long *)0x0) {
    uVar4 = 1;
  }
  else {
    if ((((param_4 != 0) && (iVar2 = AndroidBitmap_getInfo(param_1,param_4,&local_510), -1 < iVar2))
        && (local_504 == 1)) &&
       (iVar2 = AndroidBitmap_lockPixels(param_1,param_4,&local_4f8), -1 < iVar2)) {
      local_518 = local_510;
      uStack_514 = uStack_50c;
      FUN_00180f34(auStack_d0,&local_518,0x18,local_4f8,0);
      FUN_001810e0(&local_530,4);
      local_120 = 0;
      local_180 = 0;
      local_130[0] = 0x1010000;
      local_190[0] = 0x2050000;
      local_188 = &local_530;
      local_128 = auStack_d0;
      FUN_003eed14(local_130,local_190);
      FUN_001799dc(auStack_d0);
      FUN_00181138(&local_550,local_530,local_530 + 0x120);
      local_558 = local_510;
      uStack_554 = uStack_50c;
      FUN_001811b0(local_130,&local_558,0x10);
      local_188 = &local_550;
      local_180 = 0;
      local_560 = 0;
      local_570 = CONCAT44(local_570._4_4_,0x2010000);
      local_190[0] = 0x1050000;
      local_568 = local_130;
      FUN_003d245c(local_190,&local_570);
      uVar4 = FUN_00181200(&local_530,3);
      FUN_00181230(local_190,uVar4);
      lVar5 = local_530;
      while (lVar6 = local_530, lVar5 != lStack_528) {
        lStack_528 = lStack_528 + -0x60;
        FUN_00178fe0(lStack_528);
      }
      local_530 = 0;
      lStack_528 = 0;
      local_570 = lVar6;
      local_568 = (undefined4 *)lVar5;
      local_560 = local_520;
      local_520 = 0;
      FUN_001812b4(&local_570);
      lVar5 = local_550;
      while (lVar6 = local_550, lVar5 != lStack_548) {
        lStack_548 = lStack_548 + -0x60;
        FUN_00178fe0(lStack_548);
      }
      local_550 = 0;
      lStack_548 = 0;
      local_570 = lVar6;
      local_568 = (undefined4 *)lVar5;
      local_560 = local_540;
      local_540 = 0;
      FUN_001812b4(&local_570);
      FUN_00181314(&local_570,3);
      (**(code **)(*param_3 + 0x48))(param_3,local_120,local_180,local_510,uStack_50c,&local_570);
      FUN_00176d58(auStack_3d0,6);
      FUN_00177ee4(auStack_3b8," getcolorjni rgb:");
      lVar5 = FUN_0017ef28(auStack_3d0,local_570);
      FUN_00177ee4(lVar5 + 0x18," ");
      lVar5 = FUN_0017ef28(lVar5,local_570 + 4);
      FUN_00177ee4(lVar5 + 0x18," ");
      FUN_0017ef28(lVar5,local_570 + 8);
      FUN_00176e44(auStack_3d0);
      lVar6 = (**(code **)(*param_1 + 0x5d8))(param_1,param_5,0);
      for (lVar5 = 0; (long)local_568 - local_570 >> 2 != lVar5; lVar5 = lVar5 + 1) {
        *(undefined4 *)(lVar6 + lVar5 * 4) = *(undefined4 *)(local_570 + lVar5 * 4);
      }
      (**(code **)(*param_1 + 0x618))(param_1,param_5,lVar6,0);
      AndroidBitmap_unlockPixels(param_1,param_4);
      FUN_00176d58(auStack_4f0,4);
      FUN_00177ee4(auStack_4d8,"nativeGetColorBitmap use:");
      lVar5 = FUN_0017dea0();
      FUN_00178490(auStack_4d8,lVar5 - lVar3);
      FUN_00177ee4(auStack_4d8,"ms");
      FUN_00176e44(auStack_4f0);
      FUN_0017a890(&local_570);
      FUN_00178fe0(local_190);
      FUN_00178fe0(local_130);
      FUN_001812b4(&local_550);
      FUN_001812b4(&local_530);
      FUN_00178fe0(auStack_d0);
    }
    uVar4 = 0;
  }
  if (*(long *)(lVar1 + 0x28) == local_70) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail(uVar4);
}



