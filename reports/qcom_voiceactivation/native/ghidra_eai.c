/*
 * Ghidra headless decompilation
 * program   : libeai_floating.so
 * executable: /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libeai_floating.so
 * language  : AARCH64:LE:64:v8A
 * imagebase : 00100000
 * regex     : eai_|model|layer|tensor|tensor_info|parse
 * max funcs : 30
 * note      : pseudocode from a (possibly stripped) binary is approximate;
 *             indirect calls and register-indirect JNI tables are lost.
 */

/* ------------------------------------------------------------------
 * Function: eai_init_ex
 * Address : 0010b350
 * Size    : 0xc0 bytes
 * ------------------------------------------------------------------ */

undefined8
eai_init_ex(undefined8 param_1,undefined8 param_2,undefined4 param_3,uint param_4,long param_5)

{
  undefined8 uVar1;
  
  __android_log_print(3,&DAT_00101ae3,"eai_init_ex() - params: %p, %p, %d, 0x%x\n",param_1,param_2,
                      param_3,param_4);
  if (((param_4 & 1) != 0) && (param_5 == 0)) {
    __android_log_print(3,&DAT_00101ae3,
                        "fail to initialize eai in LPI mode, please specify tcm pool to use\n");
    return 1;
  }
  uVar1 = eai_init_internal(param_1,param_2,param_3,param_4,0xffffffff,param_5);
  return uVar1;
}



/* ------------------------------------------------------------------
 * Function: eai_deinit
 * Address : 0010b410
 * Size    : 0xc bytes
 * ------------------------------------------------------------------ */

void eai_deinit(undefined8 param_1)

{
  eai_deinit_internal(param_1,0xffffffff);
  return;
}



/* ------------------------------------------------------------------
 * Function: eai_preapply
 * Address : 0010b41c
 * Size    : 0x8 bytes
 * ------------------------------------------------------------------ */

void eai_preapply(void)

{
  eai_preapply_internal();
  return;
}



/* ------------------------------------------------------------------
 * Function: eai_apply
 * Address : 0010b424
 * Size    : 0x8 bytes
 * ------------------------------------------------------------------ */

void eai_apply(void)

{
  eai_apply_internal();
  return;
}



/* ------------------------------------------------------------------
 * Function: eai_execute
 * Address : 0010b42c
 * Size    : 0xc bytes
 * ------------------------------------------------------------------ */

void eai_execute(void)

{
  eai_execute_internal();
  return;
}



/* ------------------------------------------------------------------
 * Function: eai_set_property
 * Address : 0010b438
 * Size    : 0xc bytes
 * ------------------------------------------------------------------ */

void eai_set_property(void)

{
  eai_set_property_internal();
  return;
}



/* ------------------------------------------------------------------
 * Function: eai_get_property
 * Address : 0010b444
 * Size    : 0xc bytes
 * ------------------------------------------------------------------ */

void eai_get_property(void)

{
  eai_get_property_internal();
  return;
}



/* ------------------------------------------------------------------
 * Function: eai_get_interface
 * Address : 0010b450
 * Size    : 0x18 bytes
 * ------------------------------------------------------------------ */

undefined * eai_get_interface(undefined8 param_1,int param_2)

{
  undefined *puVar1;
  
  puVar1 = &DAT_0012e000;
  if (param_2 != 0) {
    puVar1 = (undefined *)0x0;
  }
  return puVar1;
}



/* ------------------------------------------------------------------
 * Function: eai_execute_internal
 * Address : 0010b6c0
 * Size    : 0x1c bytes
 * ------------------------------------------------------------------ */

undefined8 eai_execute_internal(long param_1,undefined8 param_2,undefined4 param_3)

{
  undefined8 uVar1;
  
  if (param_1 != 0) {
    uVar1 = network_forward_from(param_1,param_2,param_2,param_3);
    return uVar1;
  }
  return 4;
}



/* ------------------------------------------------------------------
 * Function: tensor_init
 * Address : 0010b6dc
 * Size    : 0xc bytes
 * ------------------------------------------------------------------ */

void tensor_init(long param_1)

{
  *(undefined4 *)(param_1 + 0x10) = 0;
  return;
}



/* ------------------------------------------------------------------
 * Function: tensor_expand_strides
 * Address : 0010b6e8
 * Size    : 0x178 bytes
 * ------------------------------------------------------------------ */

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void tensor_expand_strides(long *param_1,int param_2,long param_3)

{
  long lVar1;
  uint uVar2;
  long lVar3;
  uint *puVar4;
  ulong uVar5;
  ulong uVar6;
  uint *puVar7;
  ulong uVar8;
  ulong uVar9;
  ulong uVar10;
  undefined4 *puVar11;
  undefined4 *puVar12;
  ulong uVar13;
  int *piVar14;
  undefined1 auVar15 [13];
  undefined1 auVar16 [16];
  undefined4 uStack_44;
  undefined8 local_40;
  undefined8 uStack_38;
  undefined4 local_30;
  uint uStack_2c;
  ulong local_28;
  ulong uStack_20;
  long local_18;
  
  lVar3 = tpidr_el0;
  local_18 = *(long *)(lVar3 + 0x28);
  if (param_1 != (long *)0x0) {
    uVar2 = *(uint *)((long)param_1 + 0x14);
    local_40 = 0;
    uStack_38 = 0;
    uVar8 = (ulong)*(uint *)((long)param_1 + 0xc) & 0xf;
    auVar16._4_4_ = uVar2;
    auVar16._0_4_ = uVar2;
    auVar16._8_4_ = uVar2;
    auVar16._12_4_ = uVar2;
    auVar16 = NEON_ushl(auVar16,_DAT_00102590,4);
    local_30 = 0;
    uStack_2c = uVar2 & 0xf;
    auVar15._0_8_ = CONCAT35(0,CONCAT14(auVar16[4],(uint)(auVar16[0] & 0xf)) & 0xfffffffff);
    auVar15[8] = auVar16[8] & 0xf;
    auVar15._9_3_ = 0;
    auVar15[0xc] = auVar16[0xc] & 0xf;
    uStack_20 = (ulong)auVar15._8_5_;
    local_28 = auVar15._0_8_;
    if ((int)uVar8 != 0) {
      uVar13 = *(ulong *)(*param_1 + 4);
      uVar10 = (ulong)(5 - (int)uVar8);
      uVar9 = 0;
      do {
        puVar4 = &uStack_2c + uVar10;
        uVar5 = uVar13 & 0xf;
        uVar6 = uVar8;
        puVar7 = (uint *)(param_1 + 3);
        do {
          if ((&uStack_2c)[uVar9 + uVar10] < *puVar4) {
            uVar5 = uVar5 * *puVar7;
          }
          puVar7 = puVar7 + 1;
          uVar6 = uVar6 - 1;
          puVar4 = puVar4 + 1;
        } while (uVar6 != 0);
        *(int *)((long)&local_40 + uVar9 * 4) = (int)uVar5;
        uVar9 = uVar9 + 1;
      } while (uVar9 != uVar8);
    }
    if (param_2 != 0) {
      uVar10 = (ulong)param_2;
      lVar1 = uVar10 * 4;
      uVar9 = 0;
      if (uVar10 < 2) {
        uVar10 = 1;
      }
      piVar14 = (int *)((long)param_1 + uVar8 * 4 + 0x14);
      puVar11 = (undefined4 *)(param_3 + lVar1 + -4);
      puVar12 = &uStack_44 + uVar8;
      do {
        while ((uVar9 = uVar9 + 1, uVar9 <= uVar8 && (*piVar14 != 1))) {
          piVar14 = piVar14 + -1;
          *puVar11 = *puVar12;
          puVar11 = puVar11 + -1;
          puVar12 = puVar12 + -1;
          if (uVar10 == uVar9) goto LAB_0010b83c;
        }
        puVar12 = puVar12 + -1;
        piVar14 = piVar14 + -1;
        *puVar11 = 0;
        puVar11 = puVar11 + -1;
      } while (uVar10 != uVar9);
    }
  }
LAB_0010b83c:
  if (*(long *)(lVar3 + 0x28) == local_18) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: tensor_get_strides
 * Address : 0010b860
 * Size    : 0x124 bytes
 * ------------------------------------------------------------------ */

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void tensor_get_strides(long *param_1,long param_2)

{
  uint uVar1;
  long lVar2;
  uint *puVar3;
  uint *puVar4;
  ulong uVar5;
  ulong uVar6;
  long lVar7;
  ulong uVar8;
  ulong uVar9;
  ulong uVar10;
  undefined1 auVar11 [13];
  undefined1 auVar12 [16];
  undefined1 auVar13 [16];
  uint local_2c;
  ulong local_28;
  ulong uStack_20;
  long local_18;
  
  lVar2 = tpidr_el0;
  local_18 = *(long *)(lVar2 + 0x28);
  if (param_1 == (long *)0x0) {
    if (*(long *)(lVar2 + 0x28) == local_18) {
      __android_log_print(6,&DAT_00101ae3,"tensor is NULL");
      return;
    }
  }
  else {
    uVar1 = *(uint *)((long)param_1 + 0x14);
    auVar13._8_8_ = _UNK_00102598;
    auVar13._0_8_ = _DAT_00102590;
    auVar12._4_4_ = uVar1;
    auVar12._0_4_ = uVar1;
    auVar12._8_4_ = uVar1;
    auVar12._12_4_ = uVar1;
    uVar6 = (ulong)*(uint *)((long)param_1 + 0xc) & 0xf;
    lVar7 = *param_1;
    auVar13 = NEON_ushl(auVar12,auVar13,4);
    local_2c = uVar1 & 0xf;
    auVar11._0_8_ = CONCAT35(0,CONCAT14(auVar13[4],(uint)(auVar13[0] & 0xf)) & 0xfffffffff);
    auVar11[8] = auVar13[8] & 0xf;
    auVar11._9_3_ = 0;
    auVar11[0xc] = auVar13[0xc] & 0xf;
    uStack_20 = (ulong)auVar11._8_5_;
    local_28 = auVar11._0_8_;
    if ((int)uVar6 != 0) {
      uVar9 = (ulong)(5 - (int)uVar6);
      uVar8 = 0;
      do {
        uVar10 = *(ulong *)(lVar7 + 4) & 0xf;
        puVar3 = (uint *)(param_1 + 3);
        puVar4 = &local_2c + uVar9;
        uVar5 = uVar6;
        do {
          if ((&local_2c)[uVar8 + uVar9] < *puVar4) {
            uVar10 = uVar10 * *puVar3;
          }
          uVar5 = uVar5 - 1;
          puVar4 = puVar4 + 1;
          puVar3 = puVar3 + 1;
        } while (uVar5 != 0);
        *(int *)(param_2 + uVar8 * 4) = (int)uVar10;
        uVar8 = uVar8 + 1;
      } while (uVar8 != uVar6);
    }
    if (*(long *)(lVar2 + 0x28) == local_18) {
      return;
    }
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: tensor_set_zero
 * Address : 0010b984
 * Size    : 0x11c bytes
 * ------------------------------------------------------------------ */

void tensor_set_zero(long *param_1)

{
  void *__s;
  long *plVar1;
  uint uVar2;
  long lVar3;
  ulong uVar4;
  ulong uVar5;
  
  __s = (void *)param_1[6];
  if ((__s != (void *)0x0) && (lVar3 = *param_1, lVar3 != 0)) {
    uVar5 = *(ulong *)(lVar3 + 4);
    if (*(short *)(lVar3 + 0x10) == 0) {
      uVar4 = (ulong)*(uint *)((long)param_1 + 0xc) & 0xf;
      uVar2 = (uint)uVar5 & 0xf;
      if ((int)uVar4 != 0) {
        plVar1 = param_1 + 3;
        do {
          uVar4 = uVar4 - 1;
          uVar2 = (int)*plVar1 * uVar2;
          plVar1 = (long *)((long)plVar1 + 4);
        } while (uVar4 != 0);
      }
      memset(__s,0,(long)(int)uVar2);
      return;
    }
    uVar2 = (uint)uVar5 >> 4 & 0xff;
    if (uVar2 == 3) {
      if (uVar5 >> 0x28 != 0) {
        uVar5 = 0;
        do {
          *(undefined1 *)((long)__s + uVar5) = *(undefined1 *)(lVar3 + 0x10);
          uVar5 = uVar5 + 1;
          lVar3 = *param_1;
        } while (uVar5 < *(ulong *)(lVar3 + 4) >> 0x28);
      }
    }
    else if (uVar2 == 5) {
      if (uVar5 >> 0x28 != 0) {
        uVar5 = 0;
        do {
          *(undefined2 *)((long)__s + uVar5 * 2) = *(undefined2 *)(lVar3 + 0x10);
          uVar5 = uVar5 + 1;
          lVar3 = *param_1;
        } while (uVar5 < *(ulong *)(lVar3 + 4) >> 0x28);
      }
    }
    else {
      if (uVar2 != 6) {
        __android_log_print(6,&DAT_00101ae3,"Invalid tensor data type: %d.\n");
        return;
      }
      if (uVar5 >> 0x28 != 0) {
        uVar5 = 0;
        do {
          *(int *)((long)__s + uVar5 * 4) = (int)*(short *)(lVar3 + 0x10);
          uVar5 = uVar5 + 1;
          lVar3 = *param_1;
        } while (uVar5 < *(ulong *)(lVar3 + 4) >> 0x28);
      }
    }
    return;
  }
  *(uint *)(param_1 + 1) = *(uint *)(param_1 + 1) | 0x200000;
  return;
}



/* ------------------------------------------------------------------
 * Function: tensor_size
 * Address : 0010baa0
 * Size    : 0x38 bytes
 * ------------------------------------------------------------------ */

uint tensor_size(long *param_1)

{
  uint uVar1;
  long *plVar2;
  ulong uVar3;
  
  uVar3 = (ulong)*(uint *)((long)param_1 + 0xc) & 0xf;
  uVar1 = *(uint *)(*param_1 + 4) & 0xf;
  if ((int)uVar3 != 0) {
    plVar2 = param_1 + 3;
    do {
      uVar3 = uVar3 - 1;
      uVar1 = (int)*plVar2 * uVar1;
      plVar2 = (long *)((long)plVar2 + 4);
    } while (uVar3 != 0);
  }
  return uVar1;
}



/* ------------------------------------------------------------------
 * Function: tensor_is_same_dimension
 * Address : 0010bb6c
 * Size    : 0xbc bytes
 * ------------------------------------------------------------------ */

bool tensor_is_same_dimension(long *param_1,long *param_2)

{
  long lVar1;
  ulong uVar2;
  long lVar3;
  
  if ((param_1 == (long *)0x0) || (param_2 == (long *)0x0)) {
    return false;
  }
  if ((((*param_1 != 0) && (*param_2 != 0)) &&
      (((*(uint *)((long)param_2 + 0xc) ^ *(uint *)((long)param_1 + 0xc)) & 0xf) == 0)) &&
     ((uint)((ulong)*(undefined8 *)(*param_1 + 4) >> 0x28) ==
      (uint)((ulong)*(undefined8 *)(*param_2 + 4) >> 0x28))) {
    uVar2 = (ulong)*(uint *)((long)param_1 + 0xc) & 0xf;
    if ((int)uVar2 == 0) {
      return true;
    }
    if ((int)param_1[3] == (int)param_2[3]) {
      lVar1 = 0;
      do {
        lVar3 = lVar1;
        if (uVar2 - 1 == lVar3) break;
        lVar1 = lVar3 + 1;
      } while (*(int *)((long)param_1 + lVar3 * 4 + 0x1c) ==
               *(int *)((long)param_2 + lVar3 * 4 + 0x1c));
      return uVar2 <= lVar3 + 1U;
    }
  }
  return false;
}



/* ------------------------------------------------------------------
 * Function: tensor_expand_dims
 * Address : 0010bc28
 * Size    : 0x78 bytes
 * ------------------------------------------------------------------ */

void tensor_expand_dims(long param_1,uint param_2,long param_3)

{
  bool bVar1;
  int iVar2;
  ulong uVar3;
  ulong uVar4;
  
  if (0 < (int)param_2) {
    uVar3 = (ulong)*(uint *)(param_1 + 0xc) & 0xf;
    iVar2 = (int)uVar3 + -1;
    uVar4 = (ulong)param_2;
    do {
      while ((long)uVar4 <= (long)((long)(int)param_2 - uVar3)) {
        *(undefined4 *)(param_3 + -4 + uVar4 * 4) = 1;
        iVar2 = iVar2 + -1;
        bVar1 = uVar4 < 2;
        uVar4 = uVar4 - 1;
        if (bVar1) {
          return;
        }
      }
      *(undefined4 *)(param_3 + -4 + uVar4 * 4) = *(undefined4 *)(param_1 + (long)iVar2 * 4 + 0x18);
      iVar2 = iVar2 + -1;
      bVar1 = 1 < uVar4;
      uVar4 = uVar4 - 1;
    } while (bVar1);
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: tensor_copy_dimension
 * Address : 0010bca0
 * Size    : 0xc8 bytes
 * ------------------------------------------------------------------ */

void tensor_copy_dimension(long *param_1,long param_2)

{
  ulong uVar1;
  int *piVar2;
  uint uVar3;
  ulong uVar4;
  
  uVar4 = (ulong)*(uint *)(param_2 + 0xc) & 0xf;
  uVar3 = (uint)uVar4;
  if (5 < uVar3) {
    __android_log_print(6,&DAT_00101ae3,"ndim > MAX_NDIM\n");
    return;
  }
  *(uint *)((long)param_1 + 0xc) = *(uint *)((long)param_1 + 0xc) & 0xfffffff0 | uVar3;
  memscpy(param_1 + 3,0x14,(int *)(param_2 + 0x18),uVar4 << 2);
  if (uVar3 == 0) {
    uVar1 = 0x10000000000;
  }
  else {
    uVar1 = 1;
    piVar2 = (int *)(param_2 + 0x18);
    do {
      uVar4 = uVar4 - 1;
      uVar1 = (ulong)(uint)(*piVar2 * (int)uVar1);
      piVar2 = piVar2 + 1;
    } while (uVar4 != 0);
    uVar1 = uVar1 << 0x28;
  }
  *(ulong *)(*param_1 + 4) = *(ulong *)(*param_1 + 4) & 0xffffffffff | uVar1;
  return;
}



/* ------------------------------------------------------------------
 * Function: tensor_set_dimension
 * Address : 0010bd68
 * Size    : 0xdc bytes
 * ------------------------------------------------------------------ */

void tensor_set_dimension(long *param_1,uint param_2,uint *param_3)

{
  uint uVar1;
  ulong uVar2;
  
  if (5 < (int)param_2) {
    __android_log_print(6,&DAT_00101ae3,"ndim > MAX_NDIM\n");
    return;
  }
  *(uint *)((long)param_1 + 0xc) = *(uint *)((long)param_1 + 0xc) & 0xfffffff0 | param_2 & 0xf;
  memscpy(param_1 + 3,0x14,param_3,
          -(ulong)(param_2 >> 0x1f) & 0xfffffffc00000000 | (ulong)param_2 << 2);
  if ((int)param_2 < 1) {
    uVar2 = 0x10000000000;
  }
  else {
    uVar2 = (ulong)*param_3;
    if (param_2 != 1) {
      uVar1 = param_3[1] * *param_3;
      uVar2 = (ulong)uVar1;
      if (param_2 != 2) {
        uVar1 = param_3[2] * uVar1;
        uVar2 = (ulong)uVar1;
        if (param_2 != 3) {
          uVar1 = param_3[3] * uVar1;
          uVar2 = (ulong)uVar1;
          if (param_2 != 4) {
            uVar2 = (ulong)(param_3[4] * uVar1);
          }
        }
      }
    }
    uVar2 = uVar2 << 0x28;
  }
  *(ulong *)(*param_1 + 4) = *(ulong *)(*param_1 + 4) & 0xffffffffff | uVar2;
  return;
}



/* ------------------------------------------------------------------
 * Function: tensor_broadcast_dimension
 * Address : 0010be44
 * Size    : 0x1a8 bytes
 * ------------------------------------------------------------------ */

void tensor_broadcast_dimension(long *param_1,long param_2,long param_3)

{
  ulong uVar1;
  long lVar2;
  uint uVar3;
  uint uVar4;
  int iVar5;
  bool bVar6;
  int iVar7;
  uint uVar8;
  ulong uVar9;
  uint uVar10;
  long *plVar11;
  ulong uVar12;
  
  if (((param_1 == (long *)0x0) || (param_2 == 0)) || (param_3 == 0)) {
    __android_log_print(6,&DAT_00101ae3,"dst && src1 && src2 is true");
    return;
  }
  uVar10 = *(uint *)(param_2 + 0xc) & 0xf;
  uVar3 = *(uint *)(param_3 + 0xc) & 0xf;
  uVar4 = uVar10;
  if (uVar10 <= uVar3) {
    uVar4 = uVar3;
  }
  uVar8 = *(uint *)((long)param_1 + 0xc) & 0xfffffff0 | uVar4;
  *(uint *)((long)param_1 + 0xc) = uVar8;
  if (uVar4 != 0) {
    uVar9 = (ulong)(uVar3 - 1);
    uVar10 = uVar10 - 1;
    uVar12 = (ulong)uVar4;
    do {
      while( true ) {
        iVar7 = (int)uVar9;
        if (((int)uVar10 < 0) || (iVar7 < 0)) break;
        iVar5 = *(int *)(param_2 + 0x18 + (ulong)uVar10 * 4);
        uVar10 = uVar10 - 1;
        lVar2 = uVar9 * 4;
        uVar9 = (ulong)(iVar7 - 1);
        iVar7 = *(int *)(param_3 + 0x18 + lVar2);
        if (iVar5 != 1) {
          iVar7 = iVar5;
        }
        *(int *)((long)param_1 + uVar12 * 4 + 0x14) = iVar7;
joined_r0x0010bf44:
        bVar6 = uVar12 < 2;
        uVar12 = uVar12 - 1;
        if (bVar6) goto LAB_0010bf74;
      }
      if (-1 < (int)uVar10) {
        uVar1 = (ulong)uVar10;
        uVar10 = uVar10 - 1;
        *(undefined4 *)((long)param_1 + uVar12 * 4 + 0x14) =
             *(undefined4 *)(param_2 + 0x18 + uVar1 * 4);
        goto joined_r0x0010bf44;
      }
      if (-1 < iVar7) {
        lVar2 = uVar9 * 4;
        uVar9 = (ulong)(iVar7 - 1);
        uVar10 = 0xffffffff;
        *(undefined4 *)((long)param_1 + uVar12 * 4 + 0x14) = *(undefined4 *)(param_3 + 0x18 + lVar2)
        ;
        goto joined_r0x0010bf44;
      }
      __android_log_print(6,&DAT_00101ae3,"Tensor dimension does not match in broadcasting");
      uVar10 = 0xffffffff;
      uVar9 = 0xffffffff;
      bVar6 = 1 < uVar12;
      uVar12 = uVar12 - 1;
    } while (bVar6);
LAB_0010bf74:
    uVar8 = *(uint *)((long)param_1 + 0xc);
  }
  uVar9 = (ulong)(uVar8 & 0xf);
  if ((uVar8 & 0xf) == 0) {
    uVar12 = 0x10000000000;
  }
  else {
    uVar12 = 1;
    plVar11 = param_1 + 3;
    do {
      uVar9 = uVar9 - 1;
      uVar12 = (ulong)(uint)((int)*plVar11 * (int)uVar12);
      plVar11 = (long *)((long)plVar11 + 4);
    } while (uVar9 != 0);
    uVar12 = uVar12 << 0x28;
  }
  *(ulong *)(*param_1 + 4) = *(ulong *)(*param_1 + 4) & 0xffffffffff | uVar12;
  return;
}



/* ------------------------------------------------------------------
 * Function: tensor_get_inv_slots
 * Address : 0010bfec
 * Size    : 0xa8 bytes
 * ------------------------------------------------------------------ */

void tensor_get_inv_slots(long param_1,long param_2)

{
  uint uVar1;
  uint uVar2;
  ulong uVar3;
  ulong uVar4;
  uint uVar5;
  ulong uVar6;
  ulong uVar7;
  
  if (param_1 == 0) {
    __android_log_print(6,&DAT_00101ae3,"tensor is NULL");
    return;
  }
  uVar3 = (ulong)*(uint *)(param_1 + 0xc);
  uVar6 = uVar3 & 0xf;
  uVar5 = (uint)uVar6;
  uVar2 = 0;
  if (uVar5 < 6) {
    uVar2 = 5 - uVar5;
  }
  if (uVar5 != 0) {
    if (4 < uVar5) {
      uVar5 = 5;
    }
    uVar4 = 0;
    do {
      uVar7 = 0;
      uVar1 = uVar5 * -4 + 0x14;
      do {
        if (uVar4 + uVar2 ==
            (ulong)((*(uint *)(param_1 + 0x14) & 0xf << (ulong)(uVar1 & 0x1f)) >>
                   ((ulong)uVar1 & 0x3f))) {
          *(char *)(param_2 + uVar4) = (char)uVar7;
          uVar3 = (ulong)*(uint *)(param_1 + 0xc);
          break;
        }
        uVar7 = uVar7 + 1;
        uVar1 = uVar1 + 4;
      } while (uVar6 != uVar7);
      uVar4 = uVar4 + 1;
      uVar6 = (ulong)((uint)uVar3 & 0xf);
    } while (uVar4 < uVar6);
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: tensor_get_num_slice
 * Address : 0010c094
 * Size    : 0x3c bytes
 * ------------------------------------------------------------------ */

uint tensor_get_num_slice(long param_1)

{
  uint uVar1;
  uint uVar2;
  uint uVar3;
  
  uVar1 = *(uint *)(param_1 + 0xc) & 0xf;
  if (uVar1 < 3) {
    uVar2 = *(uint *)(param_1 + 0x14) >> 0x14 & 0xf;
    uVar1 = 0;
    if (uVar2 != 0) {
      uVar1 = 1 / uVar2;
    }
    return uVar1;
  }
  uVar3 = *(uint *)(param_1 + 0x14) >> 0x14 & 0xf;
  uVar2 = 0;
  if (uVar3 != 0) {
    uVar2 = *(int *)(param_1 + (ulong)(uVar1 - 3) * 4 + 0x18) / (int)uVar3;
  }
  return uVar2;
}



/* ------------------------------------------------------------------
 * Function: tensor_get_stride
 * Address : 0010c0d0
 * Size    : 0xb4 bytes
 * ------------------------------------------------------------------ */

uint tensor_get_stride(long *param_1,int param_2)

{
  int iVar1;
  uint uVar2;
  long *plVar3;
  uint uVar4;
  ulong uVar5;
  
  if (param_1 == (long *)0x0) {
    __android_log_print(6,&DAT_00101ae3,"tensor is NULL");
    uVar2 = 0;
  }
  else {
    uVar5 = (ulong)*(uint *)((long)param_1 + 0xc) & 0xf;
    uVar4 = (uint)uVar5;
    uVar2 = *(uint *)(*param_1 + 4) & 0xf;
    iVar1 = 0;
    if (uVar4 < 6) {
      iVar1 = 5 - uVar4;
    }
    if (uVar4 != 0) {
      if (4 < uVar4) {
        uVar4 = 5;
      }
      plVar3 = param_1 + 3;
      uVar4 = uVar4 * -4 + 0x14;
      do {
        if ((uint)(iVar1 + param_2) <
            (0xf << (ulong)(uVar4 & 0x1f) & *(uint *)((long)param_1 + 0x14)) >>
            (ulong)(uVar4 & 0x1f)) {
          uVar2 = (int)*plVar3 * uVar2;
        }
        plVar3 = (long *)((long)plVar3 + 4);
        uVar5 = uVar5 - 1;
        uVar4 = uVar4 + 4;
      } while (uVar5 != 0);
    }
  }
  return uVar2;
}



/* ------------------------------------------------------------------
 * Function: tensor_copy_data
 * Address : 0010c184
 * Size    : 0x39c bytes
 * ------------------------------------------------------------------ */

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void tensor_copy_data(long *param_1,long param_2,long param_3)

{
  uint *puVar1;
  int iVar2;
  long lVar3;
  int *piVar4;
  uint *puVar5;
  ulong uVar6;
  undefined8 *puVar7;
  ulong uVar8;
  long lVar9;
  uint *puVar10;
  undefined8 *puVar11;
  uint uVar12;
  long lVar13;
  ulong uVar14;
  ulong uVar15;
  ulong uVar16;
  ulong uVar17;
  int iVar18;
  long lVar19;
  undefined1 auVar20 [13];
  undefined1 auVar21 [16];
  int local_b8 [6];
  long local_a0 [10];
  uint local_50 [6];
  ulong local_38;
  ulong uStack_30;
  long local_28;
  
  lVar3 = tpidr_el0;
  local_28 = *(long *)(lVar3 + 0x28);
  local_50[3] = 0;
  local_50[4] = 0;
  local_a0[7] = 0;
  local_a0[6] = 0;
  local_a0[9] = 0;
  local_a0[8] = 0;
  local_a0[4] = 0;
  local_a0[3] = 0;
  local_a0[5] = param_1[6];
  uVar12 = *(uint *)((long)param_1 + 0x14);
  local_a0[2] = 0;
  local_a0[1] = 0;
  uVar15 = (ulong)*(uint *)((long)param_1 + 0xc) & 0xf;
  local_50[1] = 0;
  local_50[2] = 0;
  puVar1 = (uint *)(param_1 + 3);
  local_50[0] = 0;
  auVar21._4_4_ = uVar12;
  auVar21._0_4_ = uVar12;
  auVar21._8_4_ = uVar12;
  auVar21._12_4_ = uVar12;
  auVar21 = NEON_ushl(auVar21,_DAT_00102590,4);
  local_a0[0] = param_2;
  local_50[5] = uVar12 & 0xf;
  uVar14 = *(ulong *)(*param_1 + 4);
  auVar20._0_8_ = CONCAT35(0,CONCAT14(auVar21[4],(uint)(auVar21[0] & 0xf)) & 0xfffffffff);
  auVar20[8] = auVar21[8] & 0xf;
  auVar20._9_3_ = 0;
  auVar20[0xc] = auVar21[0xc] & 0xf;
  local_b8[0] = 0;
  local_b8[1] = 0;
  local_b8[2] = 0;
  local_b8[3] = 0;
  local_b8[4] = 0;
  uStack_30 = (ulong)auVar20._8_5_;
  local_38 = auVar20._0_8_;
  if ((int)uVar15 != 0) {
    uVar17 = (ulong)(5 - (int)uVar15);
    uVar16 = 0;
    do {
      puVar5 = local_50 + uVar17 + 5;
      uVar6 = uVar14 & 0xf;
      uVar8 = uVar15;
      puVar10 = puVar1;
      do {
        if (local_50[uVar16 + uVar17 + 5] < *puVar5) {
          uVar6 = uVar6 * *puVar10;
        }
        puVar10 = puVar10 + 1;
        uVar8 = uVar8 - 1;
        puVar5 = puVar5 + 1;
      } while (uVar8 != 0);
      local_b8[uVar16] = (int)uVar6;
      uVar16 = uVar16 + 1;
    } while (uVar16 != uVar15);
  }
  if (0 < (int)*puVar1) {
    uVar15 = 0;
LAB_0010c2f0:
    do {
      iVar18 = (int)uVar15;
      if (iVar18 == (*(uint *)((long)param_1 + 0xc) & 0xf) - 1) {
        switch((uint)uVar14 & 0xf) {
        case 1:
          lVar19 = (long)iVar18;
          piVar4 = (int *)((long)param_1 + (long)iVar18 * 4 + 0x18);
          if (0 < *piVar4) {
            lVar9 = lVar19 * 8;
            iVar2 = local_b8[lVar19];
            uVar12 = 0;
            puVar7 = (undefined8 *)local_a0[lVar19];
            puVar11 = (undefined8 *)local_a0[lVar19 + 5];
            do {
              uVar12 = uVar12 + 1;
              *(undefined1 *)puVar11 = *(undefined1 *)puVar7;
              puVar11 = (undefined8 *)((long)puVar11 + (long)iVar2);
              puVar7 = (undefined8 *)((long)puVar7 + (long)*(int *)(param_3 + lVar19 * 4));
            } while ((int)uVar12 < *piVar4);
            break;
          }
LAB_0010c484:
          uVar12 = 0;
          goto LAB_0010c488;
        case 2:
          lVar19 = (long)iVar18;
          piVar4 = (int *)((long)param_1 + (long)iVar18 * 4 + 0x18);
          if (*piVar4 < 1) goto LAB_0010c484;
          lVar9 = lVar19 * 8;
          iVar2 = local_b8[lVar19];
          uVar12 = 0;
          puVar7 = (undefined8 *)local_a0[lVar19];
          puVar11 = (undefined8 *)local_a0[lVar19 + 5];
          do {
            uVar12 = uVar12 + 1;
            *(undefined2 *)puVar11 = *(undefined2 *)puVar7;
            puVar11 = (undefined8 *)((long)puVar11 + (long)iVar2);
            puVar7 = (undefined8 *)((long)puVar7 + (long)*(int *)(param_3 + lVar19 * 4));
          } while ((int)uVar12 < *piVar4);
          break;
        default:
          goto code_r0x0010c490;
        case 4:
          lVar19 = (long)iVar18;
          piVar4 = (int *)((long)param_1 + (long)iVar18 * 4 + 0x18);
          if (*piVar4 < 1) goto LAB_0010c484;
          lVar9 = lVar19 * 8;
          iVar2 = local_b8[lVar19];
          uVar12 = 0;
          puVar7 = (undefined8 *)local_a0[lVar19];
          puVar11 = (undefined8 *)local_a0[lVar19 + 5];
          do {
            uVar12 = uVar12 + 1;
            *(undefined4 *)puVar11 = *(undefined4 *)puVar7;
            puVar11 = (undefined8 *)((long)puVar11 + (long)iVar2);
            puVar7 = (undefined8 *)((long)puVar7 + (long)*(int *)(param_3 + lVar19 * 4));
          } while ((int)uVar12 < *piVar4);
          break;
        case 8:
          lVar19 = (long)iVar18;
          piVar4 = (int *)((long)param_1 + (long)iVar18 * 4 + 0x18);
          if (*piVar4 < 1) goto LAB_0010c484;
          lVar9 = lVar19 * 8;
          iVar2 = local_b8[lVar19];
          uVar12 = 0;
          puVar7 = (undefined8 *)local_a0[lVar19];
          puVar11 = (undefined8 *)local_a0[lVar19 + 5];
          do {
            uVar12 = uVar12 + 1;
            *puVar11 = *puVar7;
            puVar11 = (undefined8 *)((long)puVar11 + (long)iVar2);
            puVar7 = (undefined8 *)((long)puVar7 + (long)*(int *)(param_3 + lVar19 * 4));
          } while ((int)uVar12 < *piVar4);
        }
        *(undefined8 **)((long)local_a0 + lVar9) = puVar7;
        *(undefined8 **)((long)local_a0 + lVar9 + 0x28) = puVar11;
LAB_0010c488:
        local_50[iVar18] = uVar12;
      }
code_r0x0010c490:
      uVar15 = -(uVar15 >> 0x1f & 1) & 0xfffffffc00000000 | (uVar15 & 0xffffffff) << 2;
      lVar19 = (long)iVar18;
      if (*(int *)((long)local_50 + uVar15) == *(int *)((long)param_1 + uVar15 + 0x18)) {
        if (iVar18 == 0) break;
        uVar15 = lVar19 - 1;
        lVar9 = local_a0[lVar19 + 4];
        iVar18 = *(int *)(param_3 + uVar15 * 4);
        lVar13 = local_a0[uVar15];
        local_50[uVar15] = local_50[uVar15] + 1;
        local_a0[lVar19 + 4] = lVar9 + local_b8[uVar15];
        local_a0[uVar15] = lVar13 + iVar18;
        if ((int)*puVar1 <= (int)local_50[0]) break;
        goto LAB_0010c2f0;
      }
      uVar16 = lVar19 + 1;
      lVar9 = local_a0[lVar19 + 5];
      local_50[uVar16] = 0;
      uVar15 = uVar16 & 0xffffffff;
      local_a0[lVar19 + 6] = lVar9;
      local_a0[uVar16] = local_a0[lVar19];
    } while ((int)local_50[0] < (int)*puVar1);
  }
  if (*(long *)(lVar3 + 0x28) == local_28) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: eai_init_internal
 * Address : 0010c654
 * Size    : 0x2b4 bytes
 * ------------------------------------------------------------------ */

int eai_init_internal(undefined8 *param_1,long param_2,int param_3,uint param_4,undefined8 param_5,
                     undefined8 param_6)

{
  uint uVar1;
  long lVar2;
  int iVar3;
  undefined4 uVar4;
  undefined8 *puVar5;
  undefined4 local_64;
  undefined8 local_60;
  long local_58;
  
  lVar2 = tpidr_el0;
  local_58 = *(long *)(lVar2 + 0x28);
  iVar3 = memory_deinit();
  if (iVar3 == 0) {
    if (param_1 == (undefined8 *)0x0) {
      __android_log_print(6,&DAT_00101ae3,"eaih NULL parameter passed\n");
      iVar3 = 4;
    }
    else if ((param_2 == 0) || (param_3 == 0)) {
      iVar3 = 6;
      __android_log_print(6,&DAT_00101ae3,"Invalid parameters passed: %p, %d\n",param_2,param_3);
    }
    else {
      local_64 = 0;
      iVar3 = memory_init(param_6,&local_64);
      if (iVar3 == 0) {
        puVar5 = (undefined8 *)allocate_memory(0x188,local_64,0);
        if (puVar5 == (undefined8 *)0x0) {
          __android_log_print(6,&DAT_00101ae3,"Unable to allocate memory for context\n");
          iVar3 = memory_deinit(local_64);
          if (iVar3 == 0) {
            iVar3 = 8;
          }
          else {
            __android_log_print(6,&DAT_00101ae3,"memory_deinit failure\n");
            iVar3 = 8;
          }
        }
        else {
          uVar1 = param_4 & 1;
          puVar5[0x30] = 0;
          puVar5[1] = 0;
          *puVar5 = 0;
          puVar5[3] = 0;
          puVar5[2] = 0;
          puVar5[5] = 0;
          puVar5[4] = 0;
          puVar5[7] = 0;
          puVar5[6] = 0;
          puVar5[9] = 0;
          puVar5[8] = 0;
          puVar5[0xb] = 0;
          puVar5[10] = 0;
          puVar5[0xd] = 0;
          puVar5[0xc] = 0;
          puVar5[0xf] = 0;
          puVar5[0xe] = 0;
          puVar5[0x11] = 0;
          puVar5[0x10] = 0;
          puVar5[0x13] = 0;
          puVar5[0x12] = 0;
          puVar5[0x15] = 0;
          puVar5[0x14] = 0;
          puVar5[0x17] = 0;
          puVar5[0x16] = 0;
          *(undefined4 *)(puVar5 + 0x16) = 0xffffffff;
          puVar5[0x19] = 0;
          puVar5[0x18] = 0;
          puVar5[0x1b] = 0;
          puVar5[0x1a] = 0;
          puVar5[0x1d] = 0;
          puVar5[0x1c] = 0;
          puVar5[0x1f] = 0;
          puVar5[0x1e] = 0;
          *(undefined4 *)((long)puVar5 + 0xa4) = 3;
          puVar5[0x21] = 0;
          puVar5[0x20] = 0;
          puVar5[0x23] = 0;
          puVar5[0x22] = 0;
          puVar5[0x25] = 0;
          puVar5[0x24] = 0;
          puVar5[0x27] = 0;
          puVar5[0x26] = 0;
          puVar5[0x29] = 0;
          puVar5[0x28] = 0;
          puVar5[0x2b] = 0;
          puVar5[0x2a] = 0;
          puVar5[0x2d] = 0;
          puVar5[0x2c] = 0;
          puVar5[0x2f] = 0;
          puVar5[0x2e] = 0;
          *(undefined4 *)((long)puVar5 + 0x124) = local_64;
          iVar3 = *(int *)((long)puVar5 + (ulong)uVar1 * 0x14 + 0xf8);
          *(undefined8 *)((long)puVar5 + 0xdc) = 0;
          *(int *)((long)puVar5 + (ulong)uVar1 * 0x14 + 0xf8) = iVar3 + 0x188;
          local_60 = 0;
          uVar4 = eai_op_layer_util_get_all_loaders(&local_60);
          iVar3 = load_network(puVar5,param_2,param_3,local_60,uVar4,uVar1);
          if (iVar3 == 0) {
            *param_1 = puVar5;
            *(int *)((long)puVar5 + 0x15c) = param_3;
            *(uint *)(puVar5 + 0x24) = *(uint *)(puVar5 + 0x24) | param_4 >> 1 & 4;
            iVar3 = load_layer_call_table(*param_1);
            if (iVar3 == 0) {
              iVar3 = 0;
              goto LAB_0010c6b8;
            }
          }
          else {
            __android_log_print(6,&DAT_00101ae3,"load_layer_call_table failed: %d\n",iVar3);
          }
          free_memory(puVar5,*(undefined4 *)((long)puVar5 + 0x124),0x188,0);
          iVar3 = memory_deinit(local_64);
          if (iVar3 != 0) {
            __android_log_print(6,&DAT_00101ae3,"memory_deinit failure\n");
          }
          *param_1 = 0;
        }
      }
      else {
        iVar3 = 6;
        __android_log_print(6,&DAT_00101ae3,"fail to initialize memory pool");
      }
    }
  }
  else {
    __android_log_print(6,&DAT_00101ae3,"Init during island mode disallowed\n");
    iVar3 = 0xf;
  }
LAB_0010c6b8:
  if (*(long *)(lVar2 + 0x28) == local_58) {
    return iVar3;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: load_layer_call_table
 * Address : 0010c908
 * Size    : 0xc8 bytes
 * ------------------------------------------------------------------ */

undefined8 load_layer_call_table(long param_1)

{
  bool bVar1;
  int iVar2;
  undefined4 *puVar3;
  uint *puVar4;
  ulong uVar5;
  
  if (param_1 == 0) {
    __android_log_print(6,&DAT_00101ae3,"load_layer_call_table - Invalid handle passed\n");
    return 4;
  }
  puVar4 = *(uint **)(param_1 + 0xa8);
  if (*puVar4 != 0) {
    uVar5 = 1;
    do {
      puVar3 = *(undefined4 **)(puVar4 + uVar5 * 2);
      iVar2 = so_op_intf_load2(puVar3);
      if (iVar2 != 0) {
        __android_log_print(6,&DAT_00101ae3,
                            "load_layer_call_table - load2 function failed for %d layer\n",*puVar3);
        return 1;
      }
      bVar1 = uVar5 < *puVar4;
      uVar5 = uVar5 + 1;
    } while (bVar1);
  }
  return 0;
}



/* ------------------------------------------------------------------
 * Function: eai_deinit_internal
 * Address : 0010c9d0
 * Size    : 0xa0 bytes
 * ------------------------------------------------------------------ */

undefined4 eai_deinit_internal(long param_1)

{
  uint uVar1;
  int iVar2;
  undefined4 uVar3;
  undefined4 uVar4;
  
  iVar2 = memory_deinit();
  if (iVar2 != 0) {
    return 0xf;
  }
  if (param_1 == 0) {
    uVar3 = 0;
    uVar4 = 4;
  }
  else {
    uVar1 = *(uint *)(param_1 + 0x98);
    uVar3 = *(undefined4 *)(param_1 + 0x124);
    cleanup_context(param_1);
    free_memory(param_1,*(undefined4 *)(param_1 + 0x124),0x188,
                param_1 + ((ulong)uVar1 & 1) * 0x14 + 0xf8);
    uVar4 = 0;
  }
  memory_deinit(uVar3);
  return uVar4;
}



/* ------------------------------------------------------------------
 * Function: eai_preapply_internal
 * Address : 0010ca70
 * Size    : 0x128 bytes
 * ------------------------------------------------------------------ */

int eai_preapply_internal(long param_1)

{
  undefined4 uVar1;
  int iVar2;
  int iVar3;
  uint *puVar4;
  int *piVar5;
  long lVar6;
  
  iVar2 = memory_deinit();
  if (iVar2 == 0) {
    if (param_1 == 0) {
      __android_log_print(6,&DAT_00101ae3,"Null handle passed\n");
      iVar2 = 4;
    }
    else {
      piVar5 = *(int **)(param_1 + 0xa8);
      *(undefined4 *)(param_1 + 0x148) = 0;
      iVar3 = *piVar5;
      if (iVar3 < 1) {
        iVar2 = 1;
      }
      else {
        lVar6 = 0;
        iVar2 = 1;
        do {
          puVar4 = *(uint **)(piVar5 + lVar6 * 2 + 2);
          if ((code *)(&PTR_conv_init_0012c258)[(ulong)*puVar4 * 3] != (code *)0x0) {
            iVar2 = (*(code *)(&PTR_conv_init_0012c258)[(ulong)*puVar4 * 3])(puVar4);
            if (iVar2 != 0) {
              __android_log_print(6,&DAT_00101ae3,
                                  "layer init failed, ret = %d, type = %d, index = %d\n",iVar2,
                                  *puVar4,*(undefined2 *)((long)puVar4 + 6));
              return iVar2;
            }
            iVar3 = *piVar5;
            iVar2 = 0;
          }
          lVar6 = lVar6 + 1;
        } while (lVar6 < iVar3);
      }
      *(undefined4 *)(param_1 + 0x128) = *(undefined4 *)(*(long *)(param_1 + 8) + 0x18);
      uVar1 = *(undefined4 *)(*(long *)(param_1 + 8) + 0x14);
      *(uint *)(param_1 + 0x98) = *(uint *)(param_1 + 0x98) | 0x20000000;
      *(undefined4 *)(param_1 + 0x138) = uVar1;
    }
  }
  else {
    iVar2 = 0xf;
  }
  return iVar2;
}



/* ------------------------------------------------------------------
 * Function: eai_apply_internal
 * Address : 0010cb98
 * Size    : 0x44 bytes
 * ------------------------------------------------------------------ */

undefined8 eai_apply_internal(undefined8 param_1)

{
  int iVar1;
  undefined8 uVar2;
  
  iVar1 = memory_deinit();
  if (iVar1 != 0) {
    return 0xf;
  }
  uVar2 = do_apply(param_1);
  return uVar2;
}



/* ------------------------------------------------------------------
 * Function: eai_set_property_internal
 * Address : 0010ce30
 * Size    : 0x60 bytes
 * ------------------------------------------------------------------ */

undefined8 eai_set_property_internal(undefined8 param_1,undefined4 param_2,undefined8 param_3)

{
  int iVar1;
  undefined8 uVar2;
  
  iVar1 = memory_deinit();
  if (iVar1 != 0) {
    return 0xf;
  }
  uVar2 = do_set_property(param_1,param_2,param_3);
  return uVar2;
}



/* ------------------------------------------------------------------
 * Function: eai_get_property_internal
 * Address : 0010d0b4
 * Size    : 0x60 bytes
 * ------------------------------------------------------------------ */

undefined8 eai_get_property_internal(undefined8 param_1,undefined4 param_2,undefined8 param_3)

{
  int iVar1;
  undefined8 uVar2;
  
  iVar1 = memory_deinit();
  if (iVar1 != 0) {
    return 0xf;
  }
  uVar2 = do_get_property(param_1,param_2,param_3);
  return uVar2;
}



