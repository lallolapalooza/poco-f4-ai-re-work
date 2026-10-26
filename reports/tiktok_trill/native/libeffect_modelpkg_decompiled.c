/* Ghidra DecompileAt: 11 function(s) */

/* ===== Function: FUN_007b2ad4 @ 007b2ad4  size=172  depth=0
   signature: undefined FUN_007b2ad4() ===== */

void FUN_007b2ad4(long param_1,undefined8 param_2,int param_3)

{
  long lVar1;
  undefined1 in_ZR;
  undefined4 uVar2;
  char *pcVar3;
  undefined1 auStack_148 [272];
  undefined8 local_38;
  
  lVar1 = tpidr_el0;
  local_38 = *(undefined8 *)(lVar1 + 0x28);
  if (param_1 == 0) {
    pcVar3 = "Model Package is not inited yet! please init first\n";
  }
  else {
    in_ZR = param_3 == 0x13;
    if (0x13 < param_3) {
      FUN_00c10268(param_1,param_2,(long)param_3);
      FUN_00b9c948();
      FUN_005efb00();
      FUN_00b9848c();
      uVar2 = FUN_007b2ff4();
      FUN_005efb58(auStack_148);
      FUN_00b8b5d8();
      goto LAB_007b2b5c;
    }
    pcVar3 = "your buf data len is far less than exception, please provide the correct buf\n";
  }
  FUN_00b8c554(param_1,"SMASH_E_LOG ",pcVar3);
  uVar2 = 0xffffffff;
LAB_007b2b5c:
  FUN_00b8e63c(*(undefined8 *)(lVar1 + 0x28));
  if ((bool)in_ZR) {
    FUN_00b96d4c(uVar2);
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ===== Function: FUN_00c10268 @ 00c10268  size=8  depth=1
   signature: undefined FUN_00c10268() ===== */

void FUN_00c10268(void)

{
  FUN_00412cd0();
  return;
}



/* ===== Function: FUN_00b9848c @ 00b9848c  size=12  depth=1
   signature: undefined FUN_00b9848c() ===== */

void FUN_00b9848c(void)

{
  return;
}



/* ===== Function: FUN_00b8b5d8 @ 00b8b5d8  size=8  depth=1
   signature: undefined FUN_00b8b5d8() ===== */

void FUN_00b8b5d8(void)

{
  FUN_003e86a0();
  return;
}



/* ===== Function: FUN_005efb58 @ 005efb58  size=44  depth=1
   signature: undefined FUN_005efb58() ===== */

void FUN_005efb58(undefined8 *param_1)

{
  *param_1 = &PTR_FUN_012865d0;
  *(undefined ***)(param_1 + 0xf) = &PTR_FUN_012865f8;
  FUN_003f3128(param_1 + 2);
  std::__ndk1::ios_base::~ios_base((ios_base *)(param_1 + 0xf));
  return;
}



/* ===== Function: FUN_00b96d4c @ 00b96d4c  size=8  depth=1
   signature: undefined FUN_00b96d4c() ===== */

void FUN_00b96d4c(void)

{
  return;
}



/* ===== Function: FUN_00b8c554 @ 00b8c554  size=8  depth=1
   signature: undefined FUN_00b8c554() ===== */

void FUN_00b8c554(void)

{
  __android_log_print(6);
  return;
}



/* ===== Function: FUN_007b2ff4 @ 007b2ff4  size=1836  depth=1
   signature: undefined FUN_007b2ff4() ===== */

void FUN_007b2ff4(void)

{
  char *pcVar1;
  bool bVar2;
  undefined4 uVar3;
  int iVar4;
  uint uVar5;
  undefined1 in_ZR;
  uint uVar6;
  undefined8 uVar7;
  byte *pbVar8;
  long *plVar9;
  int *piVar10;
  undefined4 *puVar11;
  char *pcVar12;
  int extraout_w8;
  long lVar13;
  long extraout_x8;
  ulong extraout_x8_00;
  ulong extraout_x9;
  ulong extraout_x9_00;
  ulong extraout_x9_01;
  ulong extraout_x9_02;
  ulong extraout_x9_03;
  ulong uVar14;
  byte *unaff_x19;
  int iVar15;
  long unaff_x24;
  ulong uVar16;
  undefined1 auVar17 [16];
  undefined1 auStack_190 [24];
  byte local_178 [8];
  ulong local_170;
  char *pcStack_168;
  undefined4 local_160 [6];
  int local_148;
  undefined4 uStack_144;
  undefined1 auStack_12c [4];
  uint local_128;
  int local_124;
  int local_120;
  int local_11c;
  int local_118 [6];
  int local_100;
  int local_fc;
  undefined1 auStack_f8 [24];
  byte local_e0 [16];
  ulong local_d0;
  byte local_c8 [16];
  ulong local_b8;
  int local_b0;
  uint local_ac;
  undefined1 local_a8 [24];
  int local_90;
  ulong local_88 [15];
  
  FUN_00b91524();
  auVar17 = FUN_00b8f1e8();
  FUN_003e866c(auVar17._0_8_ + 0x30);
  FUN_003e866c(unaff_x19 + 0x60);
  FUN_003e866c(unaff_x19 + 0x78);
  uVar7 = FUN_003e866c(unaff_x19 + 0xa8);
  *(undefined8 *)(unaff_x19 + 200) = *(undefined8 *)(unaff_x19 + 0xc0);
  if ((unaff_x19[0x120] & 1) == 0) {
    if (unaff_x19[0x120] >> 1 == 0) {
LAB_007b34f8:
      FUN_00b8c554(uVar7,"SMASH_E_LOG ","please provide the auth_code with correct size\n");
      uVar16 = 0xfffffffb;
      goto LAB_007b3704;
    }
  }
  else if (*(long *)(unaff_x19 + 0x128) == 0) goto LAB_007b34f8;
  FUN_00bb2518();
  FUN_0078a238();
  uVar6 = FUN_0078a2a8();
  FUN_00b94d70();
  FUN_0078a238();
  FUN_003f5c3c(local_c8,0x10,0);
  uVar16 = (ulong)local_c8 | 1;
  if ((local_c8[0] & 1) != 0) {
    uVar16 = local_b8;
  }
  FUN_0078a300(auVar17._8_8_,uVar16,0x10);
  FUN_003f5c3c(local_e0,8,0);
  uVar16 = (ulong)local_e0 | 1;
  lVar13 = 0;
  while (lVar13 != 8) {
    FUN_00bcd444();
    lVar13 = extraout_x8;
  }
  uVar14 = uVar16;
  if ((local_e0[0] & 1) != 0) {
    uVar14 = local_d0;
  }
  FUN_003e9458(local_a8,uVar14);
  pbVar8 = (byte *)FUN_006d3d18(unaff_x19 + 0x170,local_a8);
  uVar7 = FUN_003e86a0(local_a8);
  if (unaff_x19 + 0x178 == pbVar8) {
    in_ZR = (local_e0[0] & 1) == 0;
    if (!(bool)in_ZR) {
      uVar16 = local_d0;
    }
    FUN_00b8c554(uVar7,"SMASH_E_LOG ",
                 "the model type your provide: %s is NOT support in this version\n",uVar16);
    uVar16 = 0xfffffffe;
  }
  else {
    uVar7 = FUN_00be3934(uVar7,local_e0);
    uVar7 = FUN_00b9e308(uVar7,&local_ac);
    in_ZR = local_ac == uVar6;
    if ((bool)in_ZR) {
      uVar7 = FUN_00b9e308(uVar7,&local_b0);
      if (-1 < local_b0) {
        FUN_003f5c3c(auStack_f8,(long)local_b0,0);
        FUN_00bcffc0();
        uVar14 = (ulong)auStack_f8 | 1;
        in_ZR = (extraout_x8_00 & 1) == 0;
        if (!(bool)in_ZR) {
          uVar14 = extraout_x9;
        }
        FUN_0078a300(auVar17._8_8_,uVar14,(long)local_b0);
        uVar7 = FUN_00c07268(unaff_x19 + 0x18);
        uVar7 = FUN_00b9e308(uVar7,&local_fc);
        if (local_fc < 0) {
          pcVar12 = "error num_model %d\n";
LAB_007b35b8:
          __android_log_print(6,"SMASH_E_LOG ",pcVar12);
          uVar16 = 0xfffffffd;
        }
        else {
          pbVar8 = unaff_x19 + 0x108;
          for (iVar15 = 0; in_ZR = iVar15 == local_fc, iVar15 < local_fc; iVar15 = iVar15 + 1) {
            FUN_00b9e308(uVar7,&local_100);
            if (local_100 < 0) {
              pcVar12 = "error name_len %d\n";
              goto LAB_007b35b8;
            }
            auVar17 = FUN_00bc8a58(local_118);
            uVar7 = FUN_00bcb4dc((undefined1)local_118[0],auVar17._0_8_,auVar17._8_8_,
                                 (long)local_100);
            uVar14 = (ulong)local_118 | 1;
            if (!(bool)in_ZR) {
              uVar14 = extraout_x9_00;
            }
            FUN_0078a300(uVar7,uVar14);
            FUN_00bbfa98();
            FUN_003f6744();
            FUN_003e8cf0(local_a8,local_118);
            local_90 = iVar15;
            plVar9 = (long *)FUN_0064591c(pbVar8,&local_148,local_a8);
            if (*plVar9 == 0) {
              FUN_00585b88(local_88,pbVar8,local_a8);
              FUN_00585c10(pbVar8,CONCAT44(uStack_144,local_148),plVar9,local_88[0]);
              local_88[0] = 0;
              FUN_003f68ec(local_88);
            }
            uVar7 = FUN_00ba4110();
            uVar7 = FUN_00b9e308(uVar7,&local_11c);
            if (local_11c < 0) {
              pcVar12 = ".concate";
              auVar17 = FUN_00b96fd0(uVar7,"ion failed: %d\n",".concate");
              __android_log_print(auVar17._0_8_,auVar17._8_8_,pcVar12 + 0xc0f);
              uVar16 = 0xfffffffd;
              bVar2 = true;
            }
            else {
              auVar17 = FUN_00bc8a58(local_a8);
              uVar7 = FUN_00bcb4dc(local_a8[0],auVar17._0_8_,auVar17._8_8_,(long)local_11c);
              uVar14 = (ulong)local_a8 | 1;
              if (!(bool)in_ZR) {
                uVar14 = extraout_x9_01;
              }
              FUN_0078a300(uVar7,uVar14);
              thunk_FUN_00bcb7e4(local_88);
              uVar7 = FUN_007b3720(local_a8,local_88,unaff_x19 + 0x120);
              if ((int)uVar7 == 0) {
                uVar7 = FUN_003f6744(unaff_x19 + 0x78,local_88);
                uVar7 = FUN_00b9e308(uVar7,&local_128);
                uVar7 = FUN_00bbabf0(uVar7,&local_120);
                if (local_120 < 0) {
                  pcVar12 = ".concate";
                  auVar17 = FUN_00b96fd0(uVar7,"ion failed: %d\n",".concate");
                  __android_log_print(auVar17._0_8_,auVar17._8_8_,pcVar12 + 0xc48);
                  uVar16 = 0xfffffffd;
                  goto LAB_007b3310;
                }
                FUN_00bc8a58(&local_148);
                auVar17 = FUN_00bdd30c();
                uVar7 = FUN_00bcb4dc(auVar17._0_8_,auVar17._8_8_,(long)local_120);
                uVar14 = (ulong)&local_148 | 1;
                if (!(bool)in_ZR) {
                  uVar14 = extraout_x9_02;
                }
                FUN_0078a300(uVar7,uVar14);
                FUN_00bc75f0();
                auVar17 = FUN_00bd12c4();
                uVar7 = FUN_007b3720(auVar17._0_8_,auVar17._8_8_,local_88);
                if ((int)uVar7 == 0) {
                  FUN_003f6744(unaff_x19 + 0x60,local_160);
                  FUN_00c08728(local_178);
                  uVar5 = local_128;
                  uVar6 = 0x4e67c6a7;
                  uVar14 = (ulong)(local_178[0] >> 1);
                  pcVar12 = (char *)((ulong)local_178 | 1);
                  if ((local_178[0] & 1) != 0) {
                    uVar14 = local_170;
                    pcVar12 = pcStack_168;
                  }
                  pcVar1 = pcVar12 + uVar14;
                  for (; pcVar1 != pcVar12; pcVar12 = pcVar12 + 1) {
                    uVar6 = (uVar6 >> 2) + uVar6 * 0x20 + (int)*pcVar12 ^ uVar6;
                  }
                  uVar7 = FUN_00b90cb0();
                  in_ZR = uVar6 == uVar5;
                  if (!(bool)in_ZR) {
                    pcVar12 = ".concate";
                    auVar17 = FUN_00b96fd0(uVar7,"ion failed: %d\n",".concate");
                    __android_log_print(auVar17._0_8_,auVar17._8_8_,pcVar12 + 0xc81);
                    uVar16 = 0xfffffffc;
                    goto LAB_007b34c0;
                  }
                  uVar7 = FUN_00b9e308(uVar7,auStack_12c);
                  uVar7 = FUN_00bbabf0(uVar7,&local_124);
                  if (local_124 < 0) {
                    pcVar12 = ".concate";
                    auVar17 = FUN_00b96fd0(uVar7,"ion failed: %d\n",".concate");
                    __android_log_print(auVar17._0_8_,auVar17._8_8_,pcVar12 + 0xcb4);
                    uVar16 = 0xfffffffd;
                    goto LAB_007b34c0;
                  }
                  FUN_00bc8a58(auStack_190);
                  auVar17 = FUN_00ba84e8();
                  uVar7 = FUN_00bcb4dc(auVar17._0_8_,auVar17._8_8_,(long)local_124);
                  uVar14 = (ulong)auStack_190 | 1;
                  if (!(bool)in_ZR) {
                    uVar14 = extraout_x9_03;
                  }
                  FUN_0078a300(uVar7,uVar14);
                  FUN_00bf216c(unaff_x19 + 0xa8);
                  FUN_00b8d6b4();
                  bVar2 = false;
                }
                else {
                  pcVar12 = ".concate";
                  auVar17 = FUN_00b96fd0(uVar7,"ion failed: %d\n",".concate");
                  __android_log_print(auVar17._0_8_,auVar17._8_8_,pcVar12 + 0xc5d);
                  uVar16 = 0xfffffffa;
LAB_007b34c0:
                  bVar2 = true;
                }
                FUN_003e86a0(local_160);
                FUN_00b90c90();
              }
              else {
                pcVar12 = ".concate";
                auVar17 = FUN_00b96fd0(uVar7,"ion failed: %d\n",".concate");
                __android_log_print(auVar17._0_8_,auVar17._8_8_,pcVar12 + 0xc21);
                uVar16 = 0xfffffffb;
LAB_007b3310:
                bVar2 = true;
              }
              FUN_003e86a0(local_88);
              FUN_00ba4110();
            }
            uVar7 = FUN_00b93538();
            if (bVar2) goto LAB_007b36f4;
          }
          if ((*unaff_x19 & 1) == 0) {
            pbVar8 = unaff_x19 + 1;
          }
          else {
            pbVar8 = *(byte **)(unaff_x19 + 0x10);
          }
          uVar7 = FUN_003e9458(local_a8,pbVar8);
          FUN_00ba6548(uVar7,&DAT_00e66f6e);
          uVar16 = FUN_003e8a80(local_a8,local_88);
          FUN_003e86a0(local_88);
          uVar7 = FUN_00ba4110();
          if ((uVar16 & 1) != 0) {
            uVar7 = FUN_00b9e308(uVar7,local_88);
            for (iVar15 = 0; FUN_00bd3064(local_88[0] & 0xffffffff,uVar7,local_118),
                iVar15 < extraout_w8; iVar15 = iVar15 + 1) {
              FUN_0078a300();
              auVar17 = FUN_003f5c3c(local_a8,(long)local_118[0],0);
              uVar7 = FUN_00bfa904(local_a8[0],auVar17._0_8_,auVar17._8_8_,(long)local_118[0]);
              uVar7 = FUN_00bbabf0(uVar7,&local_148);
              iVar4 = local_148;
              piVar10 = (int *)FUN_00ba7398(uVar7,local_a8);
              *piVar10 = iVar4;
              uVar7 = FUN_003e86a0(local_a8);
            }
            uVar7 = FUN_0078a300();
            for (iVar15 = 0; in_ZR = iVar15 == local_118[0], iVar15 < local_118[0];
                iVar15 = iVar15 + 1) {
              FUN_00b9e308(uVar7,&local_148);
              auVar17 = FUN_003f5c3c(local_a8,(long)local_148,0);
              uVar7 = FUN_00bfa904(local_a8[0],auVar17._0_8_,auVar17._8_8_,(long)local_148);
              FUN_00bbabf0(uVar7,local_160);
              uVar3 = local_160[0];
              puVar11 = (undefined4 *)FUN_006458b8(unaff_x19 + 0x150,local_a8);
              *puVar11 = uVar3;
              uVar7 = FUN_003e86a0(local_a8);
            }
          }
          uVar16 = 0;
          unaff_x19[0x168] = 1;
        }
LAB_007b36f4:
        FUN_00b91c04();
        goto LAB_007b36f8;
      }
      FUN_00b8c554(uVar7,"SMASH_E_LOG ","error version_len %d\n");
    }
    else {
      __android_log_print(6,"SMASH_E_LOG ",
                          "model file size in your provide model is %d, but what we expection size is: %d, please checking the model file\n"
                          ,uVar6);
    }
    uVar16 = 0xfffffffd;
  }
LAB_007b36f8:
  FUN_003e86a0(local_e0);
  FUN_00b9624c();
LAB_007b3704:
  FUN_00b8f778(*(undefined8 *)(unaff_x24 + 0x28));
  if (!(bool)in_ZR) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  FUN_00b92f3c(uVar16 & 0xffffffff);
  return;
}



/* ===== Function: FUN_005efb00 @ 005efb00  size=88  depth=1
   signature: undefined FUN_005efb00() ===== */

void FUN_005efb00(undefined8 param_1)

{
  undefined8 *puVar1;
  undefined8 *unaff_x20;
  undefined1 auVar2 [16];
  
  puVar1 = (undefined8 *)FUN_00c0c6bc(param_1,&PTR_PTR_012865a0);
  *puVar1 = &PTR_FUN_012865d0;
  puVar1[0xf] = &PTR_FUN_012865f8;
  FUN_003f3b50();
  *unaff_x20 = &PTR_FUN_012865d0;
  unaff_x20[0xf] = &PTR_FUN_012865f8;
  auVar2 = FUN_00b8f454();
  FUN_005f0284(auVar2._0_8_,auVar2._8_8_,8);
  return;
}



/* ===== Function: FUN_00b8e63c @ 00b8e63c  size=12  depth=1
   signature: undefined FUN_00b8e63c() ===== */

void FUN_00b8e63c(void)

{
  return;
}



/* ===== Function: FUN_00b9c948 @ 00b9c948  size=12  depth=1
   signature: undefined FUN_00b9c948() ===== */

undefined1 * FUN_00b9c948(void)

{
  return &stack0x00000018;
}


