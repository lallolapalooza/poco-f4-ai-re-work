/* Ghidra DecompileAt: 4 function(s) */

/* ===== Function: FUN_00acf6d8 @ 00acf6d8  size=212  depth=0
   signature: undefined FUN_00acf6d8() ===== */

void FUN_00acf6d8(long param_1)

{
  int iVar1;
  int iVar2;
  int iVar3;
  int iVar4;
  int iVar5;
  float fVar6;
  int iVar7;
  int iVar8;
  
  iVar8 = *(int *)(param_1 + 0x1c);
  iVar7 = *(int *)(param_1 + 0x20);
  iVar1 = *(int *)(param_1 + 0x18);
  if (iVar8 < iVar7) {
    fVar6 = (float)iVar1 / (float)iVar8;
    iVar2 = *(int *)(param_1 + 8);
    *(float *)(param_1 + 0x34) = fVar6;
    iVar8 = (int)((float)iVar7 * fVar6);
    iVar4 = 0;
    if (iVar2 != 0) {
      iVar4 = iVar8 / iVar2;
    }
    iVar3 = iVar4 * iVar2;
    iVar5 = iVar8 - iVar3;
    *(int *)(param_1 + 0x2c) = iVar1;
    *(int *)(param_1 + 0x30) = iVar8;
    *(int *)(param_1 + 0x24) = iVar1;
    *(int *)(param_1 + 0x28) = iVar8;
    if (iVar8 != iVar3) {
      iVar8 = (iVar4 + 1) * iVar2;
      if (iVar5 <= iVar2 / 2) {
        iVar8 = iVar3;
      }
      *(int *)(param_1 + 0x28) = iVar8;
    }
    FUN_00bece64((double)iVar8,(double)iVar7);
    return;
  }
  fVar6 = (float)iVar1 / (float)iVar7;
  iVar2 = *(int *)(param_1 + 8);
  iVar7 = (int)((float)iVar8 * fVar6);
  iVar4 = 0;
  if (iVar2 != 0) {
    iVar4 = iVar7 / iVar2;
  }
  *(int *)(param_1 + 0x24) = iVar7;
  *(int *)(param_1 + 0x28) = iVar1;
  *(int *)(param_1 + 0x2c) = iVar7;
  *(int *)(param_1 + 0x30) = iVar1;
  iVar1 = iVar4 * iVar2;
  iVar3 = iVar7 - iVar1;
  *(float *)(param_1 + 0x38) = fVar6;
  if (iVar7 != iVar1) {
    iVar7 = (iVar4 + 1) * iVar2;
    if (iVar3 <= iVar2 / 2) {
      iVar7 = iVar1;
    }
    *(int *)(param_1 + 0x24) = iVar7;
  }
  FUN_00bece54((double)iVar7,(double)iVar8);
  return;
}



/* ===== Function: FUN_00acf7ac @ 00acf7ac  size=212  depth=0
   signature: undefined FUN_00acf7ac() ===== */

void FUN_00acf7ac(long param_1)

{
  int iVar1;
  int iVar2;
  int iVar3;
  int iVar4;
  int iVar5;
  float fVar6;
  int iVar7;
  int iVar8;
  
  iVar7 = *(int *)(param_1 + 0x1c);
  iVar8 = *(int *)(param_1 + 0x20);
  iVar1 = *(int *)(param_1 + 0x14);
  if (iVar7 < iVar8) {
    fVar6 = (float)iVar1 / (float)iVar8;
    iVar2 = *(int *)(param_1 + 8);
    iVar8 = (int)((float)iVar7 * fVar6);
    iVar3 = 0;
    if (iVar2 != 0) {
      iVar3 = iVar8 / iVar2;
    }
    *(int *)(param_1 + 0x24) = iVar8;
    *(int *)(param_1 + 0x28) = iVar1;
    *(int *)(param_1 + 0x2c) = iVar8;
    *(int *)(param_1 + 0x30) = iVar1;
    iVar1 = iVar3 * iVar2;
    iVar4 = iVar8 - iVar1;
    *(float *)(param_1 + 0x38) = fVar6;
    if (iVar8 != iVar1) {
      iVar8 = (iVar3 + 1) * iVar2;
      if (iVar4 <= iVar2 / 2) {
        iVar8 = iVar1;
      }
      *(int *)(param_1 + 0x24) = iVar8;
    }
    FUN_00bece54((double)iVar8,(double)iVar7);
    return;
  }
  fVar6 = (float)iVar1 / (float)iVar7;
  iVar2 = *(int *)(param_1 + 8);
  *(float *)(param_1 + 0x34) = fVar6;
  iVar7 = (int)((float)iVar8 * fVar6);
  iVar3 = 0;
  if (iVar2 != 0) {
    iVar3 = iVar7 / iVar2;
  }
  iVar4 = iVar3 * iVar2;
  iVar5 = iVar7 - iVar4;
  *(int *)(param_1 + 0x2c) = iVar1;
  *(int *)(param_1 + 0x30) = iVar7;
  *(int *)(param_1 + 0x24) = iVar1;
  *(int *)(param_1 + 0x28) = iVar7;
  if (iVar7 != iVar4) {
    iVar7 = (iVar3 + 1) * iVar2;
    if (iVar5 <= iVar2 / 2) {
      iVar7 = iVar4;
    }
    *(int *)(param_1 + 0x28) = iVar7;
  }
  FUN_00bece64((double)iVar7,(double)iVar8);
  return;
}



/* ===== Function: FUN_00bece54 @ 00bece54  size=16  depth=1
   signature: undefined FUN_00bece54() ===== */

void FUN_00bece54(double param_1,double param_2,long param_3)

{
  *(float *)(param_3 + 0x34) = (float)(param_1 / param_2);
  return;
}



/* ===== Function: FUN_00bece64 @ 00bece64  size=16  depth=1
   signature: undefined FUN_00bece64() ===== */

void FUN_00bece64(double param_1,double param_2,long param_3)

{
  *(float *)(param_3 + 0x38) = (float)(param_1 / param_2);
  return;
}


