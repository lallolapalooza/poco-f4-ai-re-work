/* Ghidra DecompileAt: 1 function(s) */

/* ===== Function: FUN_00acf2d4 @ 00acf2d4  size=1028  depth=0
   signature: undefined FUN_00acf2d4() ===== */

void FUN_00acf2d4(undefined8 param_1,undefined8 param_2,undefined8 *param_3,long param_4)

{
  undefined8 *puVar1;
  ulong uVar2;
  undefined1 uVar3;
  undefined1 uVar4;
  long lVar5;
  undefined8 extraout_x8;
  undefined8 uVar6;
  undefined8 extraout_x8_00;
  long extraout_x8_01;
  long extraout_x8_02;
  long extraout_x8_03;
  long extraout_x9;
  long extraout_x9_00;
  code *extraout_x9_01;
  long extraout_x9_02;
  long extraout_x9_03;
  long lVar7;
  long extraout_x10;
  ulong extraout_x10_00;
  ulong uVar8;
  long *unaff_x20;
  long unaff_x24;
  long lVar9;
  long lVar10;
  long lVar11;
  float fVar12;
  float fVar13;
  long lVar14;
  undefined1 auVar15 [16];
  undefined1 auVar16 [16];
  long local_1a0;
  long local_198;
  undefined8 uStack_190;
  undefined8 local_188;
  undefined8 local_180;
  undefined8 uStack_178;
  long local_170;
  undefined8 *local_168;
  long local_160;
  undefined1 local_158 [8];
  undefined1 auStack_150 [24];
  long local_138 [3];
  undefined8 local_120;
  long local_118;
  undefined8 uStack_110;
  long local_108;
  long local_100;
  long lStack_f8;
  long local_f0;
  long lStack_e8;
  
  FUN_00b95158();
  auVar15 = FUN_00bd3268();
  lVar14 = auVar15._0_8_;
  auVar16 = FUN_00bfcec4(*param_3);
  lVar9 = auVar16._0_8_;
  *(undefined8 *)(param_4 + 8) = extraout_x8;
  *(undefined4 *)(lVar9 + 0x20) = *(undefined4 *)(auVar16._8_8_ + 8);
  *(undefined4 *)(lVar9 + 0x1c) = *(undefined4 *)(auVar16._8_8_ + 0xc);
  if (*(int *)(lVar9 + 0x10) == 1) {
    FUN_00acf6d8();
  }
  else {
    FUN_00acf7ac();
  }
  lStack_f8 = 0;
  local_118 = lVar14 + 0x40;
  local_108 = CONCAT44(local_108._4_4_,0x1010000);
  local_120 = CONCAT44(local_120._4_4_,0x2010000);
  uStack_110 = 0;
  local_100 = auVar15._8_8_;
  auVar15 = FUN_00ba4228(*(undefined8 *)(lVar14 + 0x24));
  FUN_00b92824(auVar15._0_8_,auVar15._8_8_,local_158);
  FUN_00cc8734();
  FUN_00bc6fc8();
  FUN_00b97328();
  FUN_00c4c04c();
  FUN_00c04fe8();
  FUN_00acf880();
  FUN_00b9d140();
  lVar10 = (*(long *)(lVar14 + 0xa8) - *(long *)(lVar14 + 0xa0)) / 0x18;
  local_118 = 0;
  uStack_110 = 0;
  local_120 = 0;
  local_180 = 0;
  uStack_178 = 0;
  lVar5 = (*(long *)(lVar14 + 0xc0) - *(long *)(lVar14 + 0xb8)) / 0x18;
  local_188 = 0;
  FUN_007a5f28(local_138,lVar10);
  FUN_00ba8cf4();
  FUN_007a5f28();
  lVar9 = 0;
  uVar6 = local_120;
  for (lVar11 = lVar10; local_120 = uVar6, lVar11 != 0; lVar11 = lVar11 + -1) {
    FUN_00796dd8(&local_108,*(undefined8 *)(*(long *)(lVar14 + 0xd0) + 0x10),
                 *(long *)(lVar14 + 0xa0) + lVar9);
    FUN_00bfa064();
    FUN_007a5408();
    lVar9 = lVar9 + 0x18;
    uVar6 = local_120;
  }
  lVar9 = 0;
  while (lVar10 != lVar9) {
    *(undefined8 *)(local_138[0] + lVar9 * 8) = uVar6;
    FUN_00bd4038();
    uVar6 = extraout_x8_00;
    lVar9 = extraout_x9;
  }
  lVar9 = 0;
  for (lVar10 = lVar5; lVar10 != 0; lVar10 = lVar10 + -1) {
    FUN_00796dd8(&local_108,*(undefined8 *)(*(long *)(lVar14 + 0xd0) + 0x10),
                 *(long *)(lVar14 + 0xb8) + lVar9);
    FUN_007a5408(&local_188,&local_108);
    lVar9 = lVar9 + 0x18;
  }
  lVar9 = 0;
  while (lVar5 != lVar9) {
    FUN_00c00554();
    FUN_00bd4038();
    lVar9 = extraout_x9_00;
  }
  FUN_00b95980();
  FUN_00bf2fbc(*(undefined8 *)(lVar14 + 0xd8));
  FUN_00be13e4();
  (*extraout_x9_01)(&local_108);
  FUN_00793290(&local_1a0);
  uStack_190 = lStack_f8;
  lVar10 = local_100;
  lVar9 = local_108;
  local_100 = 0;
  lStack_f8 = 0;
  local_1a0 = local_108;
  local_198 = lVar10;
  local_108 = 0;
  FUN_003e8658(&local_108);
  uVar2 = (lVar10 - lVar9) / 0x14;
  uVar8 = (long)local_168 - local_170 >> 4;
  if (uVar8 < uVar2) {
    if ((ulong)(local_160 - (long)local_168 >> 4) < uVar2 - uVar8) {
      FUN_00bae348(&local_170);
      lVar11 = FUN_0078e7ec();
      local_f0 = 0;
      lVar5 = 0;
      lStack_e8 = extraout_x8_01 + 0x10;
      if (lVar11 != 0) {
        lVar5 = FUN_0078e820(lVar11);
      }
      local_100 = lVar5 + uVar8 * 0x10;
      local_f0 = lVar5 + lVar11 * 0x10;
      lVar11 = lVar5 + uVar2 * 0x10;
      lVar7 = uVar2 * 0x10 + uVar8 * -0x10;
      lStack_f8 = local_100;
      local_108 = lVar5;
      while (lVar7 != 0) {
        FUN_00bccb40();
        lVar11 = extraout_x9_02;
        lVar7 = extraout_x10;
      }
      lStack_f8 = lVar11;
      FUN_0078e974(&local_170,&local_108);
      FUN_004f9d18(&local_108);
    }
    else {
      puVar1 = local_168 + (uVar2 - uVar8) * 2;
      for (; lVar9 = local_1a0, lVar10 = local_198, puVar1 != local_168; local_168 = local_168 + 2)
      {
        *local_168 = 0;
        local_168[1] = 0;
      }
    }
  }
  else if (uVar2 < uVar8) {
    local_168 = (undefined8 *)(local_170 + uVar2 * 0x10);
  }
  FUN_00bf8fe4();
  fVar13 = *(float *)(lVar14 + 0x38);
  FUN_00b955ac(0,*(undefined4 *)(lVar14 + 0x34),fVar13,0x3f800000);
  fVar12 = (float)FUN_00bc6360();
  lVar14 = extraout_x8_02;
  lVar5 = extraout_x9_03;
  uVar8 = extraout_x10_00;
  while( true ) {
    uVar2 = (lVar10 - lVar9) / 0x14;
    uVar3 = uVar8 <= uVar2;
    uVar4 = uVar2 == uVar8;
    if (!(bool)uVar3 || (bool)uVar4) break;
    lVar11 = lVar9 + lVar14;
    uVar8 = uVar8 + 1;
    *(int *)(local_170 + lVar5 * 4) = (int)(fVar12 * *(float *)(lVar9 + lVar14));
    lVar14 = lVar14 + 0x14;
    *(int *)(local_170 + lVar5 * 4 + 4) = (int)(fVar13 * *(float *)(lVar11 + 4));
    *(int *)(local_170 + lVar5 * 4 + 8) = (int)(fVar12 * *(float *)(lVar11 + 8));
    *(int *)(local_170 + lVar5 * 4 + 0xc) = (int)(fVar13 * *(float *)(lVar11 + 0xc));
    *(undefined4 *)(*unaff_x20 + lVar5) = *(undefined4 *)(lVar11 + 0x10);
    lVar5 = lVar5 + 4;
  }
  FUN_003e8658(&local_1a0);
  FUN_003e8658(auStack_150);
  FUN_003e8658(local_138);
  FUN_003e8658(&local_188);
  FUN_003e8658(&local_120);
  FUN_00b8fa50();
  while (FUN_00ba83d0(local_170), !(bool)uVar3) {
    lVar14 = ((undefined8 *)(extraout_x8_03 + (long)unaff_x20))[1];
    FUN_00bb9dec(*(undefined8 *)(extraout_x8_03 + (long)unaff_x20));
    local_108 = FUN_00bccab4();
    local_100 = lVar14;
    FUN_00acf8c0();
    FUN_00b92d34();
  }
  FUN_003e8658(&local_170);
  FUN_00b8fc6c(*(undefined8 *)(unaff_x24 + 0x28));
  if (!(bool)uVar4) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  FUN_00b9c8dc();
  return;
}


