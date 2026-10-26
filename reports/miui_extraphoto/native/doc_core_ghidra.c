/*
 * Ghidra headless decompilation
 * program   : libdoc_photo_process.so
 * executable: /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/xp/libdoc_photo_process.so
 * language  : AARCH64:LE:64:v8A
 * imagebase : 00100000
 * regex     : FUN_00182f54|FUN_00183798|FUN_00183eb4|FUN_00185118|FUN_0018493c|FUN_001885b30
 * max funcs : 10
 * note      : pseudocode from a (possibly stripped) binary is approximate;
 *             indirect calls and register-indirect JNI tables are lost.
 */

/* ------------------------------------------------------------------
 * Function: FUN_00182f54
 * Address : 00182f54
 * Size    : 0x264 bytes
 * ------------------------------------------------------------------ */

undefined4
FUN_00182f54(long param_1,undefined8 param_2,int param_3,int param_4,int param_5,undefined8 param_6)

{
  long lVar1;
  undefined4 uVar2;
  ulong uVar3;
  long lVar4;
  long lVar5;
  long lVar6;
  undefined8 *puVar7;
  undefined8 uVar8;
  float fVar9;
  undefined8 local_2a8;
  undefined4 local_2a0 [2];
  undefined4 *local_298;
  undefined8 uStack_290;
  undefined4 local_288 [2];
  undefined8 *local_280;
  undefined8 uStack_278;
  undefined8 *local_270;
  undefined8 *puStack_268;
  undefined8 local_260;
  int local_258;
  int iStack_254;
  undefined1 auStack_250 [24];
  undefined1 auStack_238 [264];
  undefined4 local_130;
  undefined8 local_12c;
  undefined8 local_124;
  undefined8 local_11c;
  undefined8 local_114;
  undefined8 local_10c;
  undefined8 local_104;
  undefined8 local_fc;
  undefined4 local_f4;
  long local_f0;
  undefined8 *puStack_e8;
  undefined8 local_e0;
  undefined8 uStack_d8;
  undefined8 local_d0;
  float local_c8;
  undefined4 uStack_c4;
  float local_c0;
  float fStack_bc;
  undefined4 local_b8;
  float local_b4;
  undefined1 auStack_b0 [80];
  
  lVar1 = tpidr_el0;
  lVar6 = *(long *)(lVar1 + 0x28);
  uVar3 = FUN_001831fc(param_1,0);
  if ((uVar3 & 1) == 0) {
    local_c8 = (float)param_3;
    fStack_bc = (float)param_4;
    local_d0 = 0;
    uStack_c4 = 0;
    local_b8 = 0;
    local_c0 = local_c8;
    local_b4 = fStack_bc;
    FUN_001814bc(param_6,&local_d0,auStack_b0);
    uVar2 = 0;
  }
  else {
    lVar4 = FUN_001832d8();
    local_258 = param_3;
    iStack_254 = param_4;
    FUN_00180f34(&local_d0,&local_258,0x10,param_2,0);
    local_270 = (undefined8 *)0x0;
    puStack_268 = (undefined8 *)0x0;
    local_260 = 0;
    if (param_5 == 2) {
      local_130 = 0x42ff0000;
      local_fc = 0;
      local_104 = 0;
      local_10c = 0;
      local_114 = 0;
      local_11c = 0;
      local_124 = 0;
      local_12c = 0;
      local_298 = &local_130;
      local_288[0] = 0x1010000;
      local_f0 = (long)&local_12c + 4;
      puStack_e8 = &local_e0;
      uStack_278 = 0;
      uStack_290 = 0;
      local_f4 = 0;
      local_e0 = 0;
      uStack_d8 = 0;
      local_2a0[0] = 0x2010000;
      local_2a8 = DAT_005367c8;
      local_280 = &local_d0;
      FUN_00483520(0,0,local_288,local_2a0,&local_2a8,1);
      uVar2 = FUN_00187e8c(*(undefined8 *)(param_1 + 8),&local_d0,&local_130,&local_270);
      uVar8 = NEON_rev64(CONCAT44(uStack_c4,local_c8),4);
      uVar8 = NEON_scvtf(uVar8,4);
      fVar9 = (float)DAT_005367d0;
      uVar3 = (ulong)DAT_005367d0 >> 0x20;
      for (puVar7 = local_270; puStack_268 != puVar7; puVar7 = puVar7 + 1) {
        *puVar7 = CONCAT44(((float)((ulong)uVar8 >> 0x20) / (float)uVar3) *
                           (float)((ulong)*puVar7 >> 0x20),((float)uVar8 / fVar9) * (float)*puVar7);
      }
      FUN_00178fe0(&local_130);
    }
    else {
      uVar2 = FUN_0018779c(*(undefined8 *)(param_1 + 8),&local_d0,&local_270);
    }
    FUN_00182a80(param_1,&local_270,param_3,param_4);
    FUN_00176d58(auStack_250,6);
    FUN_00177ee4(auStack_238,"scan total use:");
    lVar5 = FUN_001832d8();
    FUN_00178490(auStack_238,lVar5 - lVar4);
    FUN_00177ee4(auStack_238,"ms");
    FUN_00176e44(auStack_250);
    FUN_00179740(&local_270);
    FUN_00178fe0(&local_d0);
  }
  if (*(long *)(lVar1 + 0x28) == lVar6) {
    return uVar2;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: FUN_00183798
 * Address : 00183798
 * Size    : 0x624 bytes
 * ------------------------------------------------------------------ */

undefined4
FUN_00183798(long *param_1,void *param_2,int param_3,int param_4,long *param_5,undefined8 *param_6,
            int *param_7,int *param_8,undefined4 param_9,byte param_10)

{
  undefined8 *puVar1;
  long lVar2;
  undefined4 uVar3;
  ulong uVar4;
  long lVar5;
  undefined8 uVar6;
  void *__dest;
  undefined1 *puVar7;
  long *plVar8;
  long lVar9;
  size_t __n;
  float fVar10;
  double dVar11;
  float fVar12;
  undefined4 local_a30;
  undefined4 uStack_a2c;
  undefined4 local_a28 [2];
  undefined1 *local_a20;
  undefined8 local_a18;
  undefined4 local_a10 [2];
  undefined8 *local_a08;
  undefined8 uStack_a00;
  undefined4 local_9f8;
  undefined4 uStack_9f4;
  undefined8 *local_9f0;
  undefined8 local_9e8;
  undefined8 local_9e0;
  float *local_9d8;
  float *local_9d0;
  int local_9c8;
  int local_9c4;
  undefined8 local_9c0;
  undefined4 *local_9b8;
  undefined4 *local_9b0;
  int local_9a8;
  int iStack_9a4;
  undefined1 auStack_9a0 [24];
  undefined1 auStack_988 [264];
  undefined1 auStack_880 [24];
  undefined1 auStack_868 [264];
  undefined1 auStack_760 [24];
  undefined1 auStack_748 [264];
  undefined1 auStack_640 [24];
  long local_628 [33];
  undefined1 auStack_520 [24];
  undefined1 auStack_508 [264];
  undefined1 auStack_400 [24];
  undefined1 auStack_3e8 [264];
  undefined1 auStack_2e0 [24];
  undefined1 auStack_2c8 [264];
  undefined8 local_1c0;
  undefined8 *puStack_1b8;
  undefined8 local_1b0;
  undefined8 uStack_1a8;
  undefined1 auStack_1a0 [96];
  undefined8 local_140;
  int local_138;
  int local_134;
  undefined4 *local_100;
  undefined8 uStack_e0;
  int local_d8;
  int local_d4;
  undefined4 *local_a0;
  long local_80;
  
  lVar2 = tpidr_el0;
  local_80 = *(long *)(lVar2 + 0x28);
  uVar4 = FUN_001831fc(param_1,0);
  if ((uVar4 & 1) == 0) {
    __n = (size_t)(param_3 * param_4 * 3);
    __dest = (void *)thunk_FUN_0051d53c(__n);
    *param_6 = __dest;
    memcpy(__dest,param_2,__n);
    uVar3 = 1;
    *param_8 = param_4;
    *param_7 = param_3;
    goto LAB_00183990;
  }
  lVar5 = FUN_001832d8();
  local_9a8 = param_3;
  iStack_9a4 = param_4;
  FUN_00180f34(&uStack_e0,&local_9a8,0x10,param_2,0);
  if (((uint)((int)param_5[1] - (int)*param_5) >> 2 & 1) == 0) {
    uVar4 = (**(code **)(*param_1 + 0x50))(0x41f00000,param_1,param_5);
    if ((uVar4 & 1) == 0) {
      FUN_00176d58(auStack_400,4);
      FUN_00177ee4(auStack_3e8,"invalid pts!");
      puVar7 = auStack_400;
      goto LAB_00183980;
    }
    uVar4 = 0;
    local_9c0 = 0;
    local_9b8 = (undefined4 *)0x0;
    local_9b0 = (undefined4 *)0x0;
    while( true ) {
      if ((ulong)(param_5[1] - *param_5 >> 2) <= uVar4) break;
      puVar1 = (undefined8 *)(*param_5 + uVar4 * 4);
      uVar3 = *(undefined4 *)((long)puVar1 + 4);
      local_140 = *puVar1;
      if (local_9b8 == local_9b0) {
        FUN_00186d10(&local_9c0,&local_140);
      }
      else {
        *local_9b8 = *(undefined4 *)puVar1;
        local_9b8[1] = uVar3;
        local_9b8 = local_9b8 + 2;
      }
      uVar4 = uVar4 + 2;
    }
    uVar6 = FUN_001a3ec0(&local_9c0);
    if (((param_10 & 1) == 0) || (dVar11 = (double)param_1[0xf], dVar11 == -1.0)) {
      dVar11 = (double)FUN_00181e80(uVar6,&local_9c0,param_3,param_4,param_9);
      param_1[0xf] = (long)dVar11;
    }
    else {
      FUN_00176d58(auStack_520,4);
      FUN_00177ee4(auStack_508,"aspectRatio reused......");
      FUN_00176e44(auStack_520);
    }
    FUN_00176d58(auStack_640,4);
    FUN_00177ee4(local_628,"The aspectRatio is: ");
    FUN_00186a0c(dVar11,local_628);
    FUN_004fa914(&local_140,(long)local_628 + *(long *)(local_628[0] + -0x18));
    plVar8 = (long *)FUN_00514620(&local_140,PTR_DAT_018e1638);
    uVar3 = (**(code **)(*plVar8 + 0x38))(plVar8,10);
    FUN_00514534(&local_140);
    FUN_001863f8(local_628,uVar3);
    FUN_001782e4(local_628);
    uVar6 = FUN_00176e44(auStack_640);
    FUN_00182750((float)dVar11,uVar6,&local_9c0,param_3,param_4,&local_9c4,&local_9c8);
    local_9e0 = 0;
    local_9d8 = (float *)0x0;
    local_9d0 = (float *)0x0;
    local_140 = 0;
    FUN_00187074(&local_9e0,&local_140);
    fVar10 = (float)(local_9c4 + -1);
    local_140 = (ulong)(uint)fVar10;
    if (local_9d8 < local_9d0) {
      *local_9d8 = fVar10;
      local_9d8[1] = 0.0;
      local_9d8 = local_9d8 + 2;
    }
    else {
      FUN_00187074(&local_9e0,&local_140);
    }
    fVar12 = (float)(local_9c8 + -1);
    local_140 = (ulong)(uint)fVar12 << 0x20;
    if (local_9d8 < local_9d0) {
      *local_9d8 = 0.0;
      local_9d8[1] = fVar12;
      local_9d8 = local_9d8 + 2;
    }
    else {
      FUN_00187074(&local_9e0,&local_140);
    }
    local_140 = CONCAT44(fVar12,fVar10);
    if (local_9d8 < local_9d0) {
      *local_9d8 = fVar10;
      local_9d8[1] = fVar12;
      local_9d8 = local_9d8 + 2;
    }
    else {
      FUN_00187074(&local_9e0,&local_140);
    }
    lVar9 = (long)(local_9c8 * local_9c4 * 3);
    if (local_9c8 * local_9c4 < 0) {
      lVar9 = -1;
    }
    *param_7 = local_9c4;
    *param_8 = local_9c8;
    uVar6 = thunk_FUN_0051d53c(lVar9);
    *param_6 = uVar6;
    FUN_0017dfe8(&local_140,local_9c8,local_9c4,0x10,uVar6,0);
    puStack_1b8 = &local_9c0;
    local_9f0 = &local_9e0;
    local_1b0 = 0;
    local_1c0 = CONCAT44(local_1c0._4_4_,0x8103000d);
    local_9e8 = 0;
    local_9f8 = 0x8103000d;
    FUN_004710d0(auStack_1a0,&local_1c0,&local_9f8);
    if ((((local_d8 < 0x7fff) && (local_d4 < 0x7fff)) && (local_138 < 0x7fff)) &&
       (local_134 < 0x7fff)) {
      local_9f0 = &uStack_e0;
      local_a10[0] = 0x2010000;
      local_a08 = &local_140;
      uStack_a00 = 0;
      local_9f8 = 0x1010000;
      local_a28[0] = 0x1010000;
      local_a20 = auStack_1a0;
      local_9e8 = 0;
      local_a18 = 0;
      uStack_a2c = *local_100;
      local_a30 = local_100[1];
      for (lVar9 = 0; lVar9 != 0x20; lVar9 = lVar9 + 8) {
        *(undefined8 *)((long)&local_1c0 + lVar9) = 0;
      }
      uStack_1a8 = 0;
      local_1b0 = 0;
      puStack_1b8 = (undefined8 *)0x0;
      local_1c0 = 0;
      FUN_00470b84(&local_9f8,local_a10,local_a28,&local_a30,1,0,&local_1c0);
      FUN_00176d58(auStack_880,4);
      FUN_00177ee4(auStack_868,"input:");
      local_1c0 = CONCAT44(*local_a0,local_a0[1]);
      lVar9 = FUN_00179298(auStack_880,&local_1c0);
      FUN_00177ee4(lVar9 + 0x18,", output:");
      uStack_9f4 = *local_100;
      local_9f8 = local_100[1];
      FUN_00179298(lVar9,&local_9f8);
      uVar6 = FUN_00176e44(auStack_880);
      FUN_001831fc(uVar6,4);
      FUN_00176d58(auStack_9a0,6);
      FUN_00177ee4(auStack_988,"crop total use:");
      lVar9 = FUN_001832d8();
      FUN_00178490(auStack_988,lVar9 - lVar5);
      FUN_00177ee4(auStack_988,"ms");
      FUN_00176e44(auStack_9a0);
      uVar3 = 1;
    }
    else {
      FUN_00176d58(auStack_760,4);
      FUN_00177ee4(auStack_748,"the image is too big to crop.");
      FUN_00176e44(auStack_760);
      uVar3 = 0;
    }
    FUN_00178fe0(auStack_1a0);
    FUN_00178fe0(&local_140);
    FUN_00179740(&local_9e0);
    FUN_00179740(&local_9c0);
  }
  else {
    FUN_00176d58(auStack_2e0,4);
    FUN_00177ee4(auStack_2c8,"coordinate size should be 2x*.");
    puVar7 = auStack_2e0;
LAB_00183980:
    FUN_00176e44(puVar7);
    uVar3 = 0;
  }
  FUN_00178fe0(&uStack_e0);
LAB_00183990:
  if (*(long *)(lVar2 + 0x28) == local_80) {
    return uVar3;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: FUN_00183eb4
 * Address : 00183eb4
 * Size    : 0x620 bytes
 * ------------------------------------------------------------------ */

void FUN_00183eb4(long param_1,void *param_2,int param_3,int param_4,undefined4 param_5,
                 void *param_6,byte *param_7,uint param_8)

{
  byte *pbVar1;
  long lVar2;
  byte bVar3;
  ulong uVar4;
  long lVar5;
  byte *pbVar6;
  undefined8 *puVar7;
  long lVar8;
  long lVar9;
  undefined4 local_400 [2];
  undefined4 *local_3f8;
  undefined8 uStack_3f0;
  undefined4 local_3e8 [2];
  undefined4 *local_3e0;
  undefined8 uStack_3d8;
  undefined8 local_3d0;
  undefined4 *puStack_3c8;
  undefined8 local_3c0;
  int local_3b0;
  int iStack_3ac;
  int local_3a8;
  int iStack_3a4;
  undefined1 auStack_3a0 [28];
  undefined4 local_384;
  undefined1 auStack_380 [24];
  undefined1 auStack_368 [264];
  undefined4 local_260;
  undefined4 uStack_25c;
  undefined8 uStack_258;
  undefined4 uStack_250;
  undefined4 local_24c;
  undefined4 uStack_248;
  undefined8 local_244;
  undefined8 local_23c;
  undefined8 local_234;
  undefined8 local_22c;
  undefined4 local_224;
  undefined8 *local_220;
  undefined8 *local_218;
  undefined8 local_210;
  undefined8 local_208;
  undefined4 local_1f8;
  undefined8 local_1f4;
  undefined8 local_1ec;
  undefined8 local_1e4;
  undefined8 local_1dc;
  undefined8 local_1d4;
  undefined8 local_1cc;
  undefined8 local_1c4;
  undefined4 local_1bc;
  long local_1b8;
  undefined8 *local_1b0;
  undefined8 local_1a8;
  undefined8 local_1a0;
  undefined4 local_198;
  undefined8 local_194;
  undefined8 local_18c;
  undefined8 local_184;
  undefined8 local_17c;
  undefined8 local_174;
  undefined8 local_16c;
  undefined8 local_164;
  undefined4 local_15c;
  long local_158;
  undefined8 *local_150;
  undefined8 local_148;
  undefined8 local_140;
  undefined4 auStack_138 [24];
  undefined4 auStack_d8 [30];
  
  lVar2 = tpidr_el0;
  lVar8 = *(long *)(lVar2 + 0x28);
  local_384 = param_5;
  uVar4 = FUN_001831fc(param_1,1);
  if ((uVar4 & 1) == 0) {
    memcpy(param_6,param_2,(long)(param_3 * param_4 * 3));
    *param_7 = 0;
    goto LAB_001844a0;
  }
  lVar5 = FUN_001832d8();
  FUN_0017b41c(auStack_3a0);
  local_3a8 = param_3;
  iStack_3a4 = param_4;
  FUN_00180f34(auStack_d8,&local_3a8,0x10,param_2,0);
  local_3b0 = param_3;
  iStack_3ac = param_4;
  FUN_00180f34(auStack_138,&local_3b0,0x10,param_6,0);
  uVar4 = FUN_0017b188();
  if ((uVar4 & 1) != 0) {
    FUN_001845b8(&local_1f8,auStack_3a0,"_enhance_model_input_image.jpg");
    puStack_3c8 = &local_198;
    local_260 = 0x1010000;
    uStack_258 = auStack_d8;
    uStack_250 = 0;
    local_24c = 0;
    local_158 = (long)&local_194 + 4;
    local_198 = 0x42ff0000;
    local_164 = 0;
    local_16c = 0;
    local_174 = 0;
    local_17c = 0;
    local_184 = 0;
    local_18c = 0;
    local_194 = 0;
    local_150 = &local_148;
    local_15c = 0;
    local_3c0 = 0;
    local_3d0 = CONCAT44(local_3d0._4_4_,0x2010000);
    local_140 = 0;
    local_148 = 0;
    FUN_0041b978(&local_260,&local_3d0,4,0);
    FUN_0017b1fc(&local_198,&local_1f8);
    FUN_00178fe0(&local_198);
    FUN_00176668(&local_1f8);
  }
  local_198 = 0x42ff0000;
  local_1f8 = 0x42ff0000;
  local_158 = (long)&local_194 + 4;
  local_164 = 0;
  local_16c = 0;
  local_174 = 0;
  local_17c = 0;
  local_184 = 0;
  local_18c = 0;
  local_194 = 0;
  local_150 = &local_148;
  local_1b8 = (long)&local_1f4 + 4;
  local_1b0 = &local_1a8;
  local_15c = 0;
  local_1bc = 0;
  local_1c4 = 0;
  local_1cc = 0;
  local_1d4 = 0;
  local_1dc = 0;
  local_1e4 = 0;
  local_1ec = 0;
  local_1f4 = 0;
  local_140 = 0;
  local_148 = 0;
  local_1a0 = 0;
  local_1a8 = 0;
  *param_7 = 0;
  if ((param_8 & 1) == 0) {
    FUN_001a7a84(*(undefined8 *)(param_1 + 0x18));
  }
  switch(local_384) {
  case 1:
  case 2:
    local_260 = 0x1010000;
    uStack_258 = auStack_d8;
    uStack_250 = 0;
    local_24c = 0;
    local_3d0 = CONCAT44(local_3d0._4_4_,0x2010000);
    puStack_3c8 = auStack_138;
    local_3c0 = 0;
    bVar3 = FUN_001a6e10(*(undefined8 *)(param_1 + 0x18),&local_260,&local_3d0,local_384,param_8 & 1
                         ,1,0);
    goto LAB_001842a0;
  case 3:
    uStack_258 = auStack_d8;
    local_260 = 0x1010000;
    local_3d0._0_4_ = 0x2010000;
    uStack_250 = 0;
    local_24c = 0;
    local_3c0 = 0;
    puStack_3c8 = &local_198;
    FUN_0041b978(&local_260,&local_3d0,7,0);
    puStack_3c8 = auStack_138;
    uStack_250 = 0;
    local_24c = 0;
    local_260 = 0x1010000;
    local_3d0 = CONCAT44(local_3d0._4_4_,0x2010000);
    local_3c0 = 0;
    uStack_258 = &local_198;
    FUN_0041b978(&local_260,&local_3d0,8,0);
    break;
  case 4:
    local_260 = 0x1010000;
    uStack_258 = auStack_d8;
    uStack_250 = 0;
    local_24c = 0;
    local_3d0 = CONCAT44(local_3d0._4_4_,0x2010000);
    puStack_3c8 = auStack_138;
    local_3c0 = 0;
    bVar3 = FUN_001a6e10(*(undefined8 *)(param_1 + 0x18),&local_260,&local_3d0,4,param_8 & 1,1,0);
LAB_001842a0:
    *param_7 = bVar3 & 1;
    break;
  case 5:
    local_260 = 0x42ff0000;
    local_220 = &uStack_258;
    local_22c = 0;
    local_234 = 0;
    local_23c = 0;
    local_244 = 0;
    local_24c = 0;
    uStack_248 = 0;
    uStack_258._4_4_ = 0;
    uStack_250 = 0;
    uStack_25c = 0;
    uStack_258._0_4_ = 0;
    local_218 = &local_210;
    local_224 = 0;
    local_210 = 0;
    local_208 = 0;
    bVar3 = FUN_001aa028(*(undefined8 *)(param_1 + 0x38),auStack_d8,&local_260);
    *param_7 = bVar3 & 1;
    local_3d0 = CONCAT44(local_3d0._4_4_,0x1010000);
    local_3c0 = 0;
    uStack_3d8 = 0;
    local_3e8[0] = 0x2010000;
    local_3e0 = &local_260;
    puStack_3c8 = &local_260;
    bVar3 = FUN_001a6e10(*(undefined8 *)(param_1 + 0x18),&local_3d0,local_3e8,1,0,0,0);
    *param_7 = bVar3 & 1;
    FUN_001a7890(DAT_00536810,0xbf800000,0xbf800000,*(undefined8 *)(param_1 + 0x18),&local_260,
                 &local_260);
    bVar3 = FUN_001aa870(*(undefined8 *)(param_1 + 0x38),auStack_d8,&local_260,auStack_138);
    *param_7 = bVar3 & 1;
    FUN_00178fe0(&local_260);
    break;
  default:
    memcpy(param_6,param_2,(long)(param_3 * param_4 * 3));
  }
  uVar4 = FUN_0017b188();
  if ((uVar4 & 1) != 0) {
    FUN_001845b8(local_3e8,auStack_3a0,"_enhance_model_output_");
    pbVar6 = (byte *)FUN_0017f264(&DAT_018ee0d0,&local_384);
    uVar4 = *(ulong *)(pbVar6 + 8);
    pbVar1 = *(byte **)(pbVar6 + 0x10);
    if ((*pbVar6 & 1) == 0) {
      pbVar1 = pbVar6 + 1;
      uVar4 = (ulong)(*pbVar6 >> 1);
    }
    puVar7 = (undefined8 *)FUN_0017b538(local_3e8,pbVar1,uVar4);
    uStack_250 = (undefined4)puVar7[2];
    local_24c = (undefined4)((ulong)puVar7[2] >> 0x20);
    uStack_258._0_4_ = (undefined4)puVar7[1];
    uStack_258._4_4_ = (undefined4)((ulong)puVar7[1] >> 0x20);
    local_260 = (undefined4)*puVar7;
    uStack_25c = (undefined4)((ulong)*puVar7 >> 0x20);
    for (lVar9 = 0; lVar9 != 0x18; lVar9 = lVar9 + 8) {
      *(undefined8 *)((long)puVar7 + lVar9) = 0;
    }
    puVar7 = (undefined8 *)FUN_00187154(&local_260,"_image.jpg");
    local_3c0 = puVar7[2];
    puStack_3c8 = (undefined4 *)puVar7[1];
    local_3d0 = *puVar7;
    for (lVar9 = 0; lVar9 != 0x18; lVar9 = lVar9 + 8) {
      *(undefined8 *)((long)puVar7 + lVar9) = 0;
    }
    FUN_00176668(&local_260);
    FUN_00176668(local_3e8);
    local_260 = 0x42ff0000;
    local_220 = &uStack_258;
    local_218 = &local_210;
    local_3e0 = auStack_138;
    local_22c = 0;
    local_234 = 0;
    local_23c = 0;
    local_244 = 0;
    local_24c = 0;
    uStack_248 = 0;
    uStack_258._4_4_ = 0;
    uStack_250 = 0;
    local_224 = 0;
    uStack_25c = 0;
    uStack_258._0_4_ = 0;
    local_3e8[0] = 0x1010000;
    uStack_3f0 = 0;
    local_400[0] = 0x2010000;
    local_208 = 0;
    local_210 = 0;
    uStack_3d8 = 0;
    local_3f8 = &local_260;
    FUN_0041b978(local_3e8,local_400,4,0);
    FUN_0017b1fc(&local_260,&local_3d0);
    FUN_00178fe0(&local_260);
    FUN_00176668(&local_3d0);
  }
  FUN_00176d58(auStack_380,6);
  FUN_00177ee4(auStack_368,"enhance total use:");
  lVar9 = FUN_001832d8();
  FUN_00178490(auStack_368,lVar9 - lVar5);
  FUN_00177ee4(auStack_368,"ms");
  FUN_00177ee4(auStack_368,", type:");
  FUN_00179bc8(auStack_368,local_384);
  FUN_00176e44(auStack_380);
  FUN_00178fe0(&local_1f8);
  FUN_00178fe0(&local_198);
  FUN_00178fe0(auStack_138);
  FUN_00178fe0(auStack_d8);
  FUN_00176668(auStack_3a0);
LAB_001844a0:
  if (*(long *)(lVar2 + 0x28) == lVar8) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: FUN_0018493c
 * Address : 0018493c
 * Size    : 0x2d0 bytes
 * ------------------------------------------------------------------ */

undefined4 FUN_0018493c(float param_1,undefined8 param_2,long *param_3)

{
  long *plVar1;
  long lVar2;
  long lVar3;
  ulong uVar4;
  long lVar5;
  ulong uVar6;
  undefined4 uVar7;
  ulong uVar8;
  undefined4 *puVar9;
  float fVar10;
  undefined4 *local_200;
  undefined4 *local_1f8;
  long local_1e8;
  long lStack_1e0;
  undefined8 local_1d8;
  long local_1d0;
  undefined4 *local_1c8;
  undefined4 *local_1c0;
  undefined4 local_1b8 [2];
  long *local_1b0;
  undefined8 uStack_1a8;
  long local_110;
  long *local_108;
  long *local_100;
  long local_68;
  
  lVar2 = tpidr_el0;
  local_68 = *(long *)(lVar2 + 0x28);
  uVar7 = 0;
  lVar3 = *param_3;
  lVar5 = param_3[1];
  if (lVar5 - lVar3 == 0x20) {
    local_1d0 = 0;
    local_1c8 = (undefined4 *)0x0;
    local_1c0 = (undefined4 *)0x0;
    for (uVar8 = 0; uVar8 < (ulong)(lVar5 - lVar3 >> 2); uVar8 = uVar8 + 2) {
      plVar1 = (long *)(lVar3 + uVar8 * 4);
      uVar7 = *(undefined4 *)((long)plVar1 + 4);
      local_110 = *plVar1;
      if (local_1c8 == local_1c0) {
        FUN_00186d10(&local_1d0,&local_110);
        lVar3 = *param_3;
        lVar5 = param_3[1];
      }
      else {
        *local_1c8 = (int)*plVar1;
        local_1c8[1] = uVar7;
        local_1c8 = local_1c8 + 2;
      }
    }
    local_108 = &local_1d0;
    local_1b0 = &local_1e8;
    local_1e8 = 0;
    lStack_1e0 = 0;
    local_1d8 = 0;
    local_110 = CONCAT44(local_110._4_4_,0x8103000d);
    local_100 = (long *)0x0;
    local_1b8[0] = 0x82030004;
    uStack_1a8 = 0;
    FUN_0043ed44(&local_110,local_1b8,0,0);
    uVar7 = 0;
    if (lStack_1e0 - local_1e8 == 0x10) {
      FUN_001796d0(&local_200,4);
      for (lVar3 = 0; lStack_1e0 - local_1e8 >> 2 != lVar3; lVar3 = lVar3 + 1) {
        *(undefined8 *)(local_200 + lVar3 * 2) =
             *(undefined8 *)(local_1d0 + (long)*(int *)(local_1e8 + lVar3 * 4) * 8);
      }
      lVar3 = 0;
      for (uVar8 = 0xffffffffffffffff; uVar4 = (long)local_1f8 - (long)local_200 >> 3,
          uVar8 + 1 < uVar4; uVar8 = uVar8 + 1) {
        uVar6 = 0;
        if (uVar4 != 0) {
          uVar6 = (uVar8 + 2) / uVar4;
        }
        FUN_00184c7c(&local_110,(long)local_200 + lVar3,
                     local_200 + ((uVar8 + 2) - uVar6 * uVar4) * 2);
        uVar6 = (long)local_1f8 - (long)local_200 >> 3;
        uVar4 = 0;
        if (uVar6 != 0) {
          uVar4 = uVar8 / uVar6;
        }
        FUN_00184c7c(local_1b8,(long)local_200 + lVar3,local_200 + (uVar8 - uVar4 * uVar6) * 2);
        FUN_001a3180(&local_110);
        FUN_001a3180(local_1b8);
        fVar10 = (float)FUN_001a3d4c(&local_110,local_1b8);
        FUN_00184d3c(local_1b8);
        FUN_00184d3c(&local_110);
        if (fVar10 < param_1) {
          uVar7 = 0;
          goto LAB_00184bbc;
        }
        lVar3 = lVar3 + 8;
      }
      local_110 = 0;
      local_108 = (long *)0x0;
      local_100 = (long *)0x0;
      for (puVar9 = local_200; local_1f8 != puVar9; puVar9 = puVar9 + 2) {
        if (local_108 == local_100) {
          FUN_00186bb8(&local_110,puVar9);
        }
        else {
          *(undefined4 *)local_108 = *puVar9;
          local_108 = (long *)((long)local_108 + 4);
        }
        if (local_108 == local_100) {
          FUN_00186bb8(&local_110,puVar9 + 1);
        }
        else {
          *(undefined4 *)local_108 = puVar9[1];
          local_108 = (long *)((long)local_108 + 4);
        }
      }
      if (&local_110 != param_3) {
        FUN_00187188(param_3,local_110,local_108);
      }
      FUN_0017ded8(&local_110);
      uVar7 = 1;
LAB_00184bbc:
      FUN_00179740(&local_200);
    }
    FUN_0017a890(&local_1e8);
    FUN_00179740(&local_1d0);
  }
  if (*(long *)(lVar2 + 0x28) == local_68) {
    return uVar7;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: FUN_00185118
 * Address : 00185118
 * Size    : 0x84 bytes
 * ------------------------------------------------------------------ */

void FUN_00185118(undefined8 param_1,int param_2,int param_3,long *param_4)

{
  float *pfVar1;
  long lVar2;
  bool bVar3;
  ulong uVar4;
  float fVar5;
  float fVar6;
  float fVar7;
  float fVar8;
  float fVar9;
  float fVar10;
  
  lVar2 = *param_4;
  if (param_4[1] - lVar2 == 0x20) {
    uVar4 = 0;
    fVar5 = (float)param_2 + -1.0;
    fVar6 = (float)param_3 + -1.0;
    while (uVar4 < 8) {
      pfVar1 = (float *)(lVar2 + uVar4 * 4);
      fVar8 = *pfVar1;
      fVar7 = pfVar1[1];
      uVar4 = uVar4 + 2;
      fVar10 = 0.0;
      if (0.0 <= fVar7) {
        fVar10 = fVar7;
      }
      fVar9 = fVar6;
      if (fVar10 <= fVar6) {
        fVar9 = 0.0;
      }
      bVar3 = true;
      if ((fVar10 <= fVar6) && (bVar3 = false, !NAN(fVar7))) {
        bVar3 = fVar7 < 0.0;
      }
      if (!bVar3) {
        fVar9 = fVar7;
      }
      fVar10 = 0.0;
      if (0.0 <= fVar8) {
        fVar10 = fVar8;
      }
      pfVar1[1] = fVar9;
      fVar7 = fVar5;
      if (fVar10 <= fVar5) {
        fVar7 = 0.0;
      }
      bVar3 = true;
      if ((fVar10 <= fVar5) && (bVar3 = false, !NAN(fVar8))) {
        bVar3 = fVar8 < 0.0;
      }
      if (!bVar3) {
        fVar7 = fVar8;
      }
      *pfVar1 = fVar7;
    }
  }
  return;
}



