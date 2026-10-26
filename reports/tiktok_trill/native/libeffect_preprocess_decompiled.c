/* Ghidra DecompileAt: 36 function(s) */

/* ===== Function: FUN_0078fc4c @ 0078fc4c  size=228  depth=0
   signature: undefined FUN_0078fc4c() ===== */

void FUN_0078fc4c(void)

{
  undefined1 uVar1;
  undefined8 uVar2;
  int extraout_w1;
  int extraout_w9;
  long unaff_x19;
  
  FUN_00be01c4();
  if (extraout_w9 == 0) {
    uVar1 = extraout_w1 == 5;
    if (!(bool)uVar1) {
LAB_0078fcc8:
      uVar2 = 0xffffffff;
      goto LAB_0078fd14;
    }
    FUN_00bbd150();
LAB_0078fd0c:
    FUN_00b90d0c();
  }
  else if (extraout_w9 == 2) {
    uVar1 = extraout_w1 == 3;
    if ((bool)uVar1) {
      FUN_00bb5d28();
      goto LAB_0078fd0c;
    }
    uVar1 = extraout_w1 == 2;
    if (!(bool)uVar1) goto LAB_0078fcc8;
    FUN_00be02b8();
  }
  else {
    uVar1 = extraout_w9 == 3;
    if ((bool)uVar1) {
      if (extraout_w1 == 0) {
        FUN_00bb5d18();
      }
      else {
        uVar1 = extraout_w1 == 1;
        if (!(bool)uVar1) goto LAB_0078fcc8;
        FUN_00b98474();
      }
      goto LAB_0078fd0c;
    }
  }
  uVar2 = 0;
LAB_0078fd14:
  FUN_00b90b3c(*(undefined8 *)(unaff_x19 + 0x28),uVar2);
  if ((bool)uVar1) {
    FUN_00b8b640();
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ===== Function: FUN_0078fd30 @ 0078fd30  size=104  depth=0
   signature: undefined FUN_0078fd30() ===== */

void FUN_0078fd30(undefined8 param_1,undefined8 param_2,uint param_3)

{
  bool bVar1;
  code *UNRECOVERED_JUMPTABLE;
  long unaff_x21;
  
  FUN_00c0c000();
  FUN_00b8e400();
  bVar1 = param_3 == 3;
  if (param_3 < 4) {
    FUN_00bbae40();
                    /* WARNING: Could not recover jumptable at 0x0078fd70. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (*UNRECOVERED_JUMPTABLE)();
    return;
  }
  FUN_00b8b5e0(*(undefined8 *)(unaff_x21 + 0x28));
  if (bVar1) {
    FUN_00b8bb88();
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ===== Function: FUN_0078ead4 @ 0078ead4  size=124  depth=0
   signature: undefined FUN_0078ead4() ===== */

void FUN_0078ead4(undefined8 param_1,undefined8 param_2,ulong param_3,undefined8 param_4,
                 uint param_5)

{
  undefined1 in_ZR;
  undefined1 in_CY;
  undefined8 uVar1;
  long unaff_x21;
  undefined1 auVar2 [16];
  
  FUN_00baf270();
  if ((bool)in_CY && !(bool)in_ZR) {
    in_ZR = (param_5 | 1) == 3;
    if ((bool)in_ZR) {
      uVar1 = FUN_00bcec80();
      goto LAB_0078eb08;
    }
    in_ZR = param_5 == 5;
    if (!(bool)in_ZR) goto LAB_0078eb20;
    auVar2 = FUN_00bb4314();
  }
  else {
    uVar1 = FUN_00bcec94();
LAB_0078eb08:
    auVar2._8_8_ = param_3 & 0xffffffff;
    auVar2._0_8_ = uVar1;
  }
  FUN_00bfd3ac(auVar2._0_8_,auVar2._8_8_);
  FUN_00bbc318();
  FUN_00b9e9e4();
LAB_0078eb20:
  FUN_00b8e63c(*(undefined8 *)(unaff_x21 + 0x28));
  if ((bool)in_ZR) {
    FUN_00b8c7cc();
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ===== Function: FUN_0078fbe4 @ 0078fbe4  size=104  depth=0
   signature: undefined FUN_0078fbe4() ===== */

void FUN_0078fbe4(long param_1)

{
  *(undefined8 *)(param_1 + 0x458) = 0;
  *(undefined8 *)(param_1 + 0x338) = *(undefined8 *)(param_1 + 0x330);
  *(undefined8 *)(param_1 + 0x350) = *(undefined8 *)(param_1 + 0x348);
  *(undefined8 *)(param_1 + 0x368) = *(undefined8 *)(param_1 + 0x360);
  *(undefined8 *)(param_1 + 0x380) = *(undefined8 *)(param_1 + 0x378);
  *(undefined8 *)(param_1 + 0x398) = *(undefined8 *)(param_1 + 0x390);
  *(undefined8 *)(param_1 + 0x3c8) = *(undefined8 *)(param_1 + 0x3c0);
  *(undefined8 *)(param_1 + 0x3e0) = *(undefined8 *)(param_1 + 0x3d8);
  *(undefined8 *)(param_1 + 0x3f8) = *(undefined8 *)(param_1 + 0x3f0);
  *(undefined8 *)(param_1 + 0x428) = *(undefined8 *)(param_1 + 0x420);
  *(undefined8 *)(param_1 + 0x440) = *(undefined8 *)(param_1 + 0x438);
  *(undefined8 *)(param_1 + 0x470) = *(undefined8 *)(param_1 + 0x468);
  *(undefined8 *)(param_1 + 0x488) = *(undefined8 *)(param_1 + 0x480);
  return;
}



/* ===== Function: FUN_007a2ac4 @ 007a2ac4  size=316  depth=0
   signature: undefined FUN_007a2ac4() ===== */

undefined8 FUN_007a2ac4(int *param_1,int *param_2,uint *param_3,int param_4,int param_5)

{
  int iVar1;
  undefined8 uVar2;
  int iVar3;
  int iVar4;
  uint uVar5;
  uint uVar6;
  int iVar7;
  int iVar8;
  uint uVar9;
  uint uVar10;
  float fVar11;
  float fVar12;
  
  iVar7 = param_1[2];
  iVar1 = param_1[3];
  iVar8 = iVar1 - iVar7;
  if (iVar8 == 0 || iVar1 < iVar7) {
    iVar3 = (iVar7 - iVar1) / 2;
    iVar4 = 0;
    iVar8 = 0;
    iVar7 = (iVar7 - iVar1) - iVar3;
  }
  else {
    iVar4 = iVar8 / 2;
    iVar7 = 0;
    iVar3 = 0;
    iVar8 = iVar8 - iVar4;
  }
  *param_2 = *param_1 - iVar4;
  param_2[1] = param_1[1] - iVar3;
  iVar1 = iVar8 + iVar4 + param_1[2];
  param_2[2] = iVar1;
  fVar12 = (float)param_5 / (float)iVar1;
  iVar7 = iVar3 + iVar7 + param_1[3];
  uVar5 = (uint)(fVar12 * (float)iVar4);
  fVar11 = (float)param_4 / (float)iVar7;
  uVar5 = uVar5 & ((int)uVar5 >> 0x1f ^ 0xffffffffU);
  uVar6 = (uint)(fVar11 * (float)iVar3);
  uVar6 = uVar6 & ((int)uVar6 >> 0x1f ^ 0xffffffffU);
  param_2[3] = iVar7;
  *param_3 = uVar5;
  param_3[1] = uVar6;
  uVar9 = (uint)(fVar12 * (float)param_1[2]);
  if ((int)(param_5 - uVar5) <= (int)uVar9) {
    uVar9 = param_5 - uVar5;
  }
  param_3[2] = uVar9;
  uVar10 = (uint)(fVar11 * (float)param_1[3]);
  if ((int)(param_4 - uVar6) <= (int)uVar10) {
    uVar10 = param_4 - uVar6;
  }
  param_3[3] = uVar10;
  if (((param_4 < (int)(uVar10 + uVar6) || param_5 < (int)(uVar9 + uVar5)) || (int)uVar9 < 1) ||
      (int)uVar10 < 1) {
    FUN_00b8c554(param_1,"SMASH_E_LOG ","invalid init_box_local in GetSquareBox\n");
    uVar2 = 0xffffff9b;
  }
  else {
    uVar2 = 0;
  }
  return uVar2;
}



/* ===== Function: FUN_00793a8c @ 00793a8c  size=1664  depth=0
   signature: undefined FUN_00793a8c() ===== */

void FUN_00793a8c(undefined1 param_1 [16],undefined4 param_2,undefined8 param_3,undefined8 param_4,
                 float *param_5,undefined8 *param_6,undefined8 param_7,long param_8)

{
  int iVar1;
  undefined8 *puVar2;
  uint uVar3;
  uint uVar4;
  undefined4 *puVar5;
  undefined4 *puVar6;
  undefined1 uVar7;
  undefined1 uVar8;
  long lVar9;
  void *pvVar10;
  ulong uVar11;
  undefined8 extraout_x1;
  undefined8 extraout_x1_00;
  int extraout_w8;
  ulong extraout_x8;
  undefined8 uVar12;
  long extraout_x8_00;
  ulong extraout_x8_01;
  long extraout_x8_02;
  long extraout_x8_03;
  int extraout_w9;
  long extraout_x9;
  undefined8 extraout_x9_00;
  long extraout_x9_01;
  undefined4 *extraout_x9_02;
  uint uVar13;
  long extraout_x10;
  long extraout_x10_00;
  long extraout_x10_01;
  undefined4 *extraout_x10_02;
  uint uVar14;
  int iVar15;
  int iVar16;
  long lVar17;
  long unaff_x19;
  uint *unaff_x20;
  long unaff_x22;
  int iVar18;
  long lVar19;
  undefined4 uVar20;
  undefined8 extraout_d0;
  undefined8 extraout_var;
  float fVar21;
  undefined8 uVar22;
  float fVar23;
  float fVar24;
  float fVar25;
  float fVar26;
  undefined1 auVar27 [16];
  undefined4 local_5b4;
  undefined4 uStack_5b0;
  undefined1 local_5ac;
  undefined1 local_5a8;
  undefined1 local_5a4;
  undefined1 local_5a0 [8];
  undefined8 *local_598;
  undefined8 local_590;
  undefined8 auStack_588 [137];
  int local_140;
  int local_13c;
  undefined8 local_138;
  undefined8 local_130;
  uint local_e0 [2];
  int local_d8;
  int local_d4;
  long local_d0;
  
  FUN_00b8c0b8();
  lVar9 = FUN_00b8e858();
  uVar7 = *(int *)(lVar9 + 0x5b00) == 0x123456;
  uVar8 = 0;
  if ((bool)uVar7) {
    lVar9 = FUN_00b8bba4(*(undefined1 *)(param_8 + 0x11));
    if ((extraout_x8 & 1) != 0) {
      uVar8 = 0;
      uVar7 = 1;
      if (*(int *)((undefined1 *)(lVar9 + extraout_x9) + 300) != 0x1234567) goto LAB_007940f0;
    }
    param_6[1] = *param_6;
    FUN_00bd7b28(*unaff_x20);
    if ((bool)uVar7) {
      uVar12 = *(undefined8 *)(unaff_x19 + 0x2ea4);
      FUN_00c4bf20(local_e0);
      fVar21 = param_5[2];
      fVar25 = param_5[3];
      if (fVar25 <= fVar21) {
        fVar24 = param_5[1];
        fVar23 = (fVar21 + -1.0) * 0.5;
        fVar26 = fVar23 + *param_5;
        if (fVar21 + fVar24 + -1.0 <= (float)(int)(unaff_x20[2] - 1)) {
          fVar23 = (fVar25 + fVar24 + -1.0) - fVar23;
          fVar25 = fVar21;
        }
        else {
          fVar23 = fVar23 + fVar24;
          fVar25 = fVar21;
        }
      }
      else {
        fVar24 = *param_5;
        fVar26 = (fVar25 + -1.0) * 0.5;
        fVar23 = fVar26 + param_5[1];
        if (fVar25 + fVar24 + -1.0 <= (float)(int)(unaff_x20[3] - 1)) {
          fVar26 = (fVar21 + fVar24 + -1.0) - fVar26;
        }
        else {
          fVar26 = fVar26 + fVar24;
        }
      }
      iVar16 = unaff_x20[3] - 1;
      iVar18 = unaff_x20[2] - 1;
      puVar2 = (undefined8 *)(unaff_x19 + 0x30a0);
      fVar21 = (fVar25 * DAT_00d98cbc + -1.0) * 0.5;
      fVar25 = (float)(int)(fVar25 * DAT_00d98cbc);
      uVar13 = (uint)(fVar26 - fVar21);
      uVar14 = (uint)(fVar23 - fVar21);
      *param_5 = (float)(int)uVar13;
      param_5[1] = (float)(int)uVar14;
      iVar15 = (int)(fVar25 + (float)(int)uVar13 + -1.0);
      uVar3 = uVar13 & ((int)uVar13 >> 0x1f ^ 0xffffffffU);
      iVar1 = iVar15;
      if (iVar16 <= iVar15) {
        iVar1 = iVar16;
      }
      iVar16 = (int)(fVar25 + (float)(int)uVar14 + -1.0);
      uVar4 = uVar14 & ((int)uVar14 >> 0x1f ^ 0xffffffffU);
      param_5[2] = (float)(int)((iVar15 - uVar13) + 1);
      param_5[3] = (float)(int)((iVar16 - uVar14) + 1);
      if (iVar18 <= iVar16) {
        iVar16 = iVar18;
      }
      iVar1 = (iVar1 - uVar3) + 1;
      if ((int)unaff_x20[1] < 1) {
        fVar21 = 0.0;
      }
      else {
        fVar21 = (float)*(ulong *)(*(long *)(unaff_x20 + 0x12) + (long)(int)(unaff_x20[1] - 1) * 8);
      }
      iVar18 = (int)(fVar25 * fVar25 * fVar21);
      if (*(int *)(unaff_x19 + 0x30a8) < iVar18) {
        FUN_00790b68(puVar2);
        pvVar10 = malloc((long)iVar18);
        *(void **)(unaff_x19 + 0x30a0) = pvVar10;
        *(int *)(unaff_x19 + 0x30a8) = iVar18;
      }
      FUN_00b8cc70(*puVar2);
      FUN_00b8e56c(&local_598,(int)fVar25,(int)fVar25,*unaff_x20 & 0xfff,*puVar2);
      FUN_00c06d24(local_e0);
      FUN_00ba62ac();
      uVar11 = (ulong)(uint)param_5[1];
      local_138._4_4_ = (iVar16 - uVar4) + 1;
      FUN_00bfd538(*param_5,uVar11,(float)(int)uVar3,(float)(int)uVar4,&local_598,local_e0,
                   &local_140);
      FUN_00bf04a4();
      local_138 = CONCAT44(local_138._4_4_,iVar1);
      local_140 = extraout_w8;
      local_13c = extraout_w9;
      FUN_00c4aa60();
      lVar19 = *(long *)(unaff_x20 + 4);
      iVar18 = 0;
      while( true ) {
        param_2 = (undefined4)uVar11;
        if ((int)(iVar16 - uVar4) < iVar18) break;
        iVar15 = (*unaff_x20 >> 3 & 0x1ff) + 1;
        lVar17 = ((ulong)(local_e0[0] >> 3) & 0x1ff) + 1;
        memcpy((void *)(local_d0 + lVar17 * local_140 +
                       (long)(local_d4 * (local_13c + iVar18) * (int)lVar17)),
               (void *)(lVar19 + (ulong)(iVar15 * uVar3) +
                       (long)(int)(unaff_x20[3] * (uVar4 + iVar18) * iVar15)),(long)(iVar15 * iVar1)
              );
        iVar18 = iVar18 + 1;
      }
      FUN_00ba62ac();
      local_598 = (undefined8 *)CONCAT44(local_598._4_4_,0x1010000);
      local_138 = unaff_x19 + 0x3110;
      local_130 = 0;
      local_140 = 0x2010000;
      FUN_00b92824(uVar12,&local_598,&local_140,local_5a0);
      auStack_588[0] = 0;
      local_590 = extraout_x9_00;
      FUN_00cc8734();
      FUN_00b9c594();
    }
    FUN_00c4c04c(local_e0,unaff_x19 + 0x3110);
    uVar8 = 0;
    if ((local_d4 == *(int *)(unaff_x19 + 0x2ea4)) &&
       (uVar8 = local_d8 == *(int *)(unaff_x19 + 0x2ea8), (bool)uVar8)) {
      FUN_00c09c20();
      FUN_00bdabdc();
      uVar20 = FUN_00bd0eac();
      local_598 = (undefined8 *)0x0;
      local_590 = CONCAT44(param_2,uVar20);
      FUN_00acb460();
      uVar12 = *(undefined8 *)(param_5 + 2);
      uVar22 = NEON_fmov(0xbf800000,4);
      local_598 = (undefined8 *)FUN_00bd0eac(*(undefined8 *)param_5);
      uVar12 = CONCAT44((float)((ulong)local_598 >> 0x20) + (float)((ulong)uVar12 >> 0x20) +
                        (float)((ulong)uVar22 >> 0x20),
                        SUB84(local_598,0) + (float)uVar12 + (float)uVar22);
      local_590 = uVar12;
      FUN_00acb600();
      auVar27 = FUN_00bfc27c();
      local_5a4 = 0;
      local_5a8 = 0;
      local_5ac = 0;
      local_5b4 = 0;
      uStack_5b0 = 0;
      FUN_00b8bb98(&local_5b4,auVar27._0_8_,auVar27._8_8_,local_e0,&local_140,
                   *(undefined8 *)(param_8 + 0x10),1,local_5a0,*(undefined4 *)(param_8 + 0x18));
      uVar11 = FUN_007932ec();
      if ((uVar11 & 1) != 0) {
        FUN_00bf390c(0x25c8,uVar11,&local_140);
        FUN_00acb16c();
        iVar1 = *(int *)(param_8 + 0x1c);
        uVar3 = unaff_x20[3];
        uVar11 = (ulong)uVar3;
        if (iVar1 == 1) {
          lVar19 = 0;
          while (uVar8 = lVar19 == 0x1a8, !(bool)uVar8) {
            lVar17 = *(long *)(unaff_x19 + 0x25d8);
            fVar25 = *(float *)(lVar17 + lVar19);
            *(float *)(lVar17 + lVar19) =
                 (float)(int)*(float *)(lVar17 + **(long **)(unaff_x19 + 0x2610) + lVar19);
            FUN_00bcc954((float)((int)uVar11 + ~(int)fVar25));
            uVar11 = extraout_x8_01;
            lVar19 = extraout_x9_01;
          }
        }
        else {
          uVar13 = unaff_x20[2];
          if (iVar1 == 3) {
            for (lVar19 = 0; uVar8 = lVar19 == 0x1a8, !(bool)uVar8; lVar19 = lVar19 + 4) {
              lVar17 = *(long *)(unaff_x19 + 0x25d8);
              fVar25 = *(float *)(lVar17 + lVar19);
              *(float *)(lVar17 + lVar19) =
                   (float)(int)(uVar13 + ~(int)*(float *)(lVar17 + **(long **)(unaff_x19 + 0x2610) +
                                                         lVar19));
              *(float *)(*(long *)(unaff_x19 + 0x25d8) + **(long **)(unaff_x19 + 0x2610) + lVar19) =
                   (float)(int)(uVar3 + ~(int)fVar25);
            }
          }
          else {
            uVar8 = false;
            if (iVar1 == 2) {
              FUN_00c09c20();
              fVar25 = (float)FUN_00bdabdc();
              lVar19 = extraout_x10_00;
              while (uVar8 = lVar19 == 0x1a8, !(bool)uVar8) {
                *(float *)(*(long *)(unaff_x19 + 0x25d8) + lVar19) =
                     fVar25 - *(float *)(*(long *)(unaff_x19 + 0x25d8) + lVar19);
                fVar25 = (float)FUN_00ba05bc(*(undefined8 *)(unaff_x19 + 0x2610));
                *(float *)(extraout_x8_00 + extraout_x10_01) =
                     (float)uVar12 - *(float *)(extraout_x8_00 + extraout_x10_01);
                lVar19 = extraout_x10_01 + 4;
              }
            }
          }
        }
        uVar12 = FUN_00ba13cc(*(undefined4 *)(param_8 + 0x1c),unaff_x19 + extraout_x10);
        puVar5 = extraout_x10_02;
        if ((bool)uVar8) {
          puVar5 = extraout_x9_02;
        }
        puVar6 = extraout_x9_02;
        if ((bool)uVar8) {
          puVar6 = extraout_x10_02;
        }
        *(undefined4 *)(unaff_x19 + 0x3e28) = *puVar5;
        *(undefined4 *)(unaff_x19 + 0x3e2c) = *puVar6;
        FUN_00acb460(uVar12,unaff_x19 + 0x2eac);
        thunk_FUN_00b9dd80(*(undefined8 *)(unaff_x19 + 0x25d8),**(undefined8 **)(unaff_x19 + 0x2610)
                           ,(undefined8 *)(unaff_x19 + 0x2368));
        FUN_00b95efc();
        FUN_00acb4b4();
        uVar12 = FUN_00bb71a0(0x2558,*(undefined8 *)(unaff_x19 + 0x2368));
        ((undefined8 *)(unaff_x19 + extraout_x8_02))[1] = extraout_var;
        *(undefined8 *)(unaff_x19 + extraout_x8_02) = extraout_d0;
        FUN_0079410c(uVar12,extraout_x1,*(undefined8 *)(param_8 + 0x10));
        FUN_0066d328(param_7,unaff_x19 + 0x31d8);
        uVar12 = FUN_00b8cc70(auStack_588,extraout_x1_00,0x440);
        local_590 = 0x88;
        local_598 = auStack_588;
        FUN_00bbbdf8(&local_598,uVar12,&local_598);
        *(undefined8 *)(extraout_x8_03 + 0x450) = 0;
        FUN_00792cf8();
        FUN_00793a08(unaff_x19 + 0x400,&local_598,unaff_x20[2],unaff_x20[3]);
        *(undefined1 *)(lVar9 + extraout_x9) = 1;
        FUN_0079031c(&local_598);
      }
      FUN_00bb7ddc();
    }
    FUN_00b9c594();
  }
LAB_007940f0:
  FUN_00b8fc6c(*(undefined8 *)(unaff_x22 + 0x28));
  if ((bool)uVar8) {
    FUN_00b8c428();
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ===== Function: FUN_00be02b8 @ 00be02b8  size=12  depth=1
   signature: undefined FUN_00be02b8() ===== */

void FUN_00be02b8(undefined8 param_1,undefined8 param_2,undefined8 param_3,undefined8 param_4)

{
  FUN_00c4a990(param_4,param_1);
  return;
}



/* ===== Function: FUN_00bb5d18 @ 00bb5d18  size=16  depth=1
   signature: undefined FUN_00bb5d18() ===== */

undefined1  [16] FUN_00bb5d18(void)

{
  undefined1 auVar1 [16];
  
  auVar1._8_8_ = &stack0x00000008;
  auVar1._0_8_ = &stack0x00000020;
  return auVar1;
}



/* ===== Function: FUN_00b8b640 @ 00b8b640  size=8  depth=1
   signature: undefined FUN_00b8b640() ===== */

void FUN_00b8b640(void)

{
  return;
}



/* ===== Function: FUN_00b90b3c @ 00b90b3c  size=12  depth=1
   signature: undefined FUN_00b90b3c() ===== */

void FUN_00b90b3c(void)

{
  return;
}



/* ===== Function: FUN_00be01c4 @ 00be01c4  size=28  depth=1
   signature: undefined FUN_00be01c4() ===== */

void FUN_00be01c4(void)

{
  long lVar1;
  long unaff_x29;
  
  lVar1 = tpidr_el0;
  *(undefined8 *)(unaff_x29 + -0x18) = *(undefined8 *)(lVar1 + 0x28);
  return;
}



/* ===== Function: FUN_00b98474 @ 00b98474  size=16  depth=1
   signature: undefined FUN_00b98474() ===== */

undefined1  [16] FUN_00b98474(void)

{
  undefined1 auVar1 [16];
  
  auVar1._8_8_ = &stack0x00000008;
  auVar1._0_8_ = &stack0x00000020;
  return auVar1;
}



/* ===== Function: FUN_00b90d0c @ 00b90d0c  size=8  depth=1
   signature: undefined FUN_00b90d0c() ===== */

void FUN_00b90d0c(void)

{
  FUN_00cf2d98();
  return;
}



/* ===== Function: FUN_00bb5d28 @ 00bb5d28  size=16  depth=1
   signature: undefined FUN_00bb5d28() ===== */

undefined1  [16] FUN_00bb5d28(void)

{
  undefined1 auVar1 [16];
  
  auVar1._8_8_ = &stack0x00000008;
  auVar1._0_8_ = &stack0x00000020;
  return auVar1;
}



/* ===== Function: FUN_00bbd150 @ 00bbd150  size=40  depth=1
   signature: undefined FUN_00bbd150() ===== */

undefined1  [16] FUN_00bbd150(void)

{
  undefined1 auVar1 [16];
  
  auVar1._8_8_ = &stack0x00000008;
  auVar1._0_8_ = &stack0x00000020;
  return auVar1;
}



/* ===== Function: FUN_00bbae40 @ 00bbae40  size=12  depth=1
   signature: undefined FUN_00bbae40() ===== */

void FUN_00bbae40(void)

{
  return;
}



/* ===== Function: FUN_00c0c000 @ 00c0c000  size=20  depth=1
   signature: undefined FUN_00c0c000() ===== */

void FUN_00c0c000(void)

{
  return;
}



/* ===== Function: FUN_00b8e400 @ 00b8e400  size=12  depth=1
   signature: undefined FUN_00b8e400() ===== */

void FUN_00b8e400(void)

{
  undefined8 uVar1;
  
  uVar1 = tpidr_el0;
  return;
}



/* ===== Function: FUN_00b8bb88 @ 00b8bb88  size=8  depth=1
   signature: undefined FUN_00b8bb88() ===== */

void FUN_00b8bb88(void)

{
  return;
}



/* ===== Function: FUN_00b8b5e0 @ 00b8b5e0  size=12  depth=1
   signature: undefined FUN_00b8b5e0() ===== */

void FUN_00b8b5e0(void)

{
  return;
}



/* ===== Function: FUN_00baf270 @ 00baf270  size=32  depth=1
   signature: undefined FUN_00baf270() ===== */

void FUN_00baf270(void)

{
  long lVar1;
  long unaff_x29;
  
  lVar1 = tpidr_el0;
  *(undefined8 *)(unaff_x29 + -0x28) = *(undefined8 *)(lVar1 + 0x28);
  return;
}



/* ===== Function: FUN_00bbc318 @ 00bbc318  size=8  depth=1
   signature: undefined FUN_00bbc318() ===== */

void FUN_00bbc318(void)

{
  FUN_005d8e6c();
  return;
}



/* ===== Function: FUN_00b8e63c @ 00b8e63c  size=12  depth=1
   signature: undefined FUN_00b8e63c() ===== */

void FUN_00b8e63c(void)

{
  return;
}



/* ===== Function: FUN_00b9e9e4 @ 00b9e9e4  size=8  depth=1
   signature: undefined FUN_00b9e9e4() ===== */

void FUN_00b9e9e4(void)

{
  FUN_00c4c350();
  return;
}



/* ===== Function: FUN_00bcec94 @ 00bcec94  size=20  depth=1
   signature: undefined FUN_00bcec94() ===== */

undefined1 * FUN_00bcec94(void)

{
  return &stack0x00000008;
}



/* ===== Function: FUN_00bcec80 @ 00bcec80  size=20  depth=1
   signature: undefined FUN_00bcec80() ===== */

undefined1 * FUN_00bcec80(void)

{
  return &stack0x00000008;
}



/* ===== Function: FUN_00b8c7cc @ 00b8c7cc  size=8  depth=1
   signature: undefined FUN_00b8c7cc() ===== */

void FUN_00b8c7cc(void)

{
  return;
}



/* ===== Function: FUN_00bb4314 @ 00bb4314  size=28  depth=1
   signature: undefined FUN_00bb4314() ===== */

undefined1  [16] FUN_00bb4314(undefined8 param_1,undefined8 param_2,ulong param_3)

{
  undefined1 auVar1 [16];
  
  auVar1._8_8_ = param_3 & 0xffffffff;
  auVar1._0_8_ = &stack0x00000008;
  return auVar1;
}



/* ===== Function: FUN_00bfd3ac @ 00bfd3ac  size=8  depth=1
   signature: undefined FUN_00bfd3ac() ===== */

void FUN_00bfd3ac(void)

{
  FUN_00c4c100();
  return;
}



/* ===== Function: FUN_00b8c554 @ 00b8c554  size=8  depth=1
   signature: undefined FUN_00b8c554() ===== */

void FUN_00b8c554(void)

{
  __android_log_print(6);
  return;
}



/* ===== Function: FUN_00ba62ac @ 00ba62ac  size=8  depth=1
   signature: undefined FUN_00ba62ac() ===== */

void FUN_00ba62ac(void)

{
  FUN_00c4c350(&stack0x00000088);
  return;
}



/* ===== Function: FUN_0079031c @ 0079031c  size=40  depth=1
   signature: undefined FUN_0079031c() ===== */

void FUN_0079031c(void)

{
  undefined1 in_ZR;
  void *pvVar1;
  undefined8 *unaff_x19;
  undefined8 unaff_x20;
  
  pvVar1 = (void *)FUN_00baf834();
  if (!(bool)in_ZR) {
    operator_delete__(pvVar1);
    *unaff_x19 = unaff_x20;
    unaff_x19[1] = 0x88;
  }
  FUN_00b8c70c();
  return;
}



/* ===== Function: FUN_00b92824 @ 00b92824  size=16  depth=1
   signature: undefined FUN_00b92824() ===== */

undefined1  [16] FUN_00b92824(void)

{
  return ZEXT816(0);
}



/* ===== Function: FUN_00b8c0b8 @ 00b8c0b8  size=24  depth=1
   signature: undefined FUN_00b8c0b8() ===== */

void FUN_00b8c0b8(void)

{
  return;
}



/* ===== Function: FUN_00bf390c @ 00bf390c  size=12  depth=1
   signature: undefined FUN_00bf390c() ===== */

void FUN_00bf390c(void)

{
  return;
}



/* ===== Function: FUN_00c4aa60 @ 00c4aa60  size=544  depth=1
   signature: undefined FUN_00c4aa60() ===== */

void FUN_00c4aa60(undefined8 param_1,undefined8 param_2,int *param_3)

{
  undefined1 uVar1;
  uint *puVar2;
  undefined4 *extraout_x1;
  long lVar3;
  int *piVar4;
  uint extraout_w8;
  uint uVar5;
  uint extraout_w9;
  long lVar6;
  uint uVar7;
  int extraout_w10;
  long extraout_x10;
  undefined8 uVar8;
  uint *puVar9;
  uint *unaff_x19;
  long unaff_x20;
  long unaff_x22;
  ulong uVar10;
  undefined1 auVar11 [16];
  
  FUN_00c5f9c0();
  piVar4 = param_3;
  auVar11 = FUN_00c600c8(*extraout_x1);
  lVar3 = auVar11._8_8_;
  puVar2 = auVar11._0_8_;
  *puVar2 = extraout_w8;
  puVar2[1] = extraout_w9;
  *(int *)(extraout_x10 + 8) = piVar4[3];
  puVar2[3] = piVar4[2];
  lVar6 = *(long *)(lVar3 + 0x10) + **(long **)(lVar3 + 0x48) * (long)piVar4[1];
  *(long *)(puVar2 + 4) = lVar6;
  *(undefined8 *)(puVar2 + 6) = *(undefined8 *)(lVar3 + 0x18);
  *(undefined8 *)(puVar2 + 8) = *(undefined8 *)(lVar3 + 0x20);
  *(undefined8 *)(puVar2 + 10) = *(undefined8 *)(lVar3 + 0x28);
  *(undefined8 *)(puVar2 + 0xc) = *(undefined8 *)(lVar3 + 0x30);
  uVar8 = *(undefined8 *)(lVar3 + 0x38);
  puVar9 = puVar2 + 0x14;
  puVar9[0] = 0;
  puVar9[1] = 0;
  *(int **)(puVar2 + 0x10) = (int *)(extraout_x10 + 8);
  *(uint **)(puVar2 + 0x12) = puVar9;
  puVar2[0x16] = 0;
  puVar2[0x17] = 0;
  *(undefined8 *)(puVar2 + 0xe) = uVar8;
  uVar5 = extraout_w8;
  if (2 < *(int *)(lVar3 + 4)) {
    auVar11 = FUN_00c5eca0(puVar2,"m.dims <= 2");
    FUN_00c5ec88(auVar11._0_8_,auVar11._8_8_,&DAT_00e64559,"e");
    FUN_00c2ae9c();
    puVar2 = (uint *)FUN_00c5ec98();
    uVar5 = *unaff_x19;
    lVar6 = *(long *)(unaff_x19 + 4);
  }
  uVar7 = 0xffffbfff;
  if (*(int *)(unaff_x20 + 0xc) <= param_3[2]) {
    uVar7 = 0xffffffff;
  }
  *unaff_x19 = uVar7 & uVar5;
  *unaff_x19 = uVar7 & uVar5 | (uint)(param_3[3] == 1) << 0xe;
  uVar10 = (ulong)((uVar5 >> 3 & 0x1ff) + 1 << (ulong)((uint)(0xfa50L >> ((uVar5 & 7) << 1)) & 3));
  *(ulong *)(unaff_x19 + 4) = lVar6 + uVar10 * (long)*param_3;
  if (((((*param_3 < 0) || (param_3[2] < 0)) || (*(int *)(unaff_x20 + 0xc) < param_3[2] + *param_3))
      || ((param_3[1] < 0 || (param_3[3] < 0)))) ||
     (*(int *)(unaff_x20 + 8) < param_3[3] + param_3[1])) {
    auVar11 = FUN_00c5eca0(puVar2,
                           "0 <= roi.x && 0 <= roi.width && roi.x + roi.width <= m.cols && 0 <= roi.y && 0 <= roi.height && roi.y + roi.height <= m.rows"
                          );
    FUN_00c5ec88(auVar11._0_8_,auVar11._8_8_,&DAT_00e64559,"e");
    FUN_00c2ae9c();
    FUN_00c5ec98();
  }
  if (*(long *)(unaff_x19 + 0xe) != 0) {
    do {
      FUN_00c5fc70();
    } while (extraout_w10 != 0);
  }
  if ((param_3[2] < *(int *)(unaff_x20 + 0xc)) || (param_3[3] < *(int *)(unaff_x20 + 8))) {
    *unaff_x19 = *unaff_x19 | 0x8000;
  }
  **(undefined8 **)(unaff_x19 + 0x12) = **(undefined8 **)(unaff_x20 + 0x48);
  *(ulong *)(*(long *)(unaff_x19 + 0x12) + 8) = uVar10;
  uVar1 = unaff_x19[2] == 1;
  if (((int)unaff_x19[2] < 1) || (uVar1 = unaff_x19[3] == 0, (int)unaff_x19[3] < 1)) {
    FUN_00c5f980();
    unaff_x19[2] = 0;
    unaff_x19[3] = 0;
  }
  FUN_00c5edd8(*(undefined8 *)(unaff_x22 + 0x28));
  if (!(bool)uVar1) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  FUN_00c5f1c4();
  return;
}


