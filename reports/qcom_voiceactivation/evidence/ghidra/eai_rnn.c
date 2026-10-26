/*
 * Ghidra headless decompilation
 * program   : libeai_sym.so
 * executable: /tmp/libeai_sym.so
 * language  : AARCH64:LE:64:v8A
 * imagebase : 00100000
 * regex     : lstm_forward|lstm_init|gru_forward|gru_init|framer_forward|framer_incremental_forward|matmul_forward|matmul_broadcasting_forward|gather_forward|argmax_forward|argmax_core|softmax_forward|logsoftmax_forward|eai_op_layer_util_
 * max funcs : 40
 * note      : pseudocode from a (possibly stripped) binary is approximate;
 *             indirect calls and register-indirect JNI tables are lost.
 */

/* ------------------------------------------------------------------
 * Function: eai_op_layer_util_init
 * Address : 0010e9c4
 * Size    : 0x40 bytes
 * ------------------------------------------------------------------ */

undefined8 eai_op_layer_util_init(long param_1)

{
  long lVar1;
  
  if ((param_1 != 0) && (lVar1 = eai_op_layer_util_get_output(param_1,0), lVar1 != 0)) {
    tensor_init();
    return 0;
  }
  return 1;
}



/* ------------------------------------------------------------------
 * Function: eai_op_layer_util_init_with_same_output_type
 * Address : 0010ea04
 * Size    : 0x90 bytes
 * ------------------------------------------------------------------ */

undefined8 eai_op_layer_util_init_with_same_output_type(long param_1)

{
  long lVar1;
  long lVar2;
  undefined8 uVar3;
  
  lVar1 = eai_op_layer_util_get_input(param_1,0);
  lVar2 = eai_op_layer_util_get_output(param_1,0);
  uVar3 = 1;
  if ((lVar1 != 0) && (lVar2 != 0)) {
    if ((*(byte *)(lVar2 + 8) >> 2 & 1) != 0) {
      tensor_copy_dimension(lVar2,lVar1);
    }
    if ((param_1 == 0) || (lVar1 = eai_op_layer_util_get_output(param_1,0), lVar1 == 0)) {
      return 1;
    }
    tensor_init();
    uVar3 = 0;
  }
  return uVar3;
}



/* ------------------------------------------------------------------
 * Function: eai_op_layer_util_init_binary_with_broadcast
 * Address : 0010ea94
 * Size    : 0xec bytes
 * ------------------------------------------------------------------ */

undefined4 eai_op_layer_util_init_binary_with_broadcast(long param_1)

{
  bool bVar1;
  bool bVar2;
  int iVar3;
  long lVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined4 uVar7;
  
  if ((param_1 == 0) || (lVar4 = eai_op_layer_util_get_output(param_1,0), lVar4 == 0)) {
    bVar2 = false;
    uVar7 = 1;
    iVar3 = eai_op_layer_util_get_input_count(param_1);
  }
  else {
    tensor_init();
    uVar7 = 0;
    bVar2 = true;
    iVar3 = eai_op_layer_util_get_input_count(param_1);
  }
  if (iVar3 < 2) {
    uVar7 = 1;
  }
  else {
    lVar4 = eai_op_layer_util_get_output(param_1,0);
    bVar1 = false;
    if (lVar4 != 0) {
      bVar1 = bVar2;
    }
    if (bVar1) {
      if ((*(byte *)(lVar4 + 8) >> 2 & 1) != 0) {
        uVar5 = eai_op_layer_util_get_input(param_1,0);
        uVar6 = eai_op_layer_util_get_input(param_1,1);
        tensor_broadcast_dimension(lVar4,uVar5,uVar6);
      }
      return 0;
    }
  }
  return uVar7;
}



/* ------------------------------------------------------------------
 * Function: eai_op_layer_util_load_base
 * Address : 0010eb80
 * Size    : 0x40 bytes
 * ------------------------------------------------------------------ */

undefined4 *
eai_op_layer_util_load_base
          (long *param_1,undefined8 param_2,undefined8 param_3,undefined8 param_4,
          undefined4 *param_5)

{
  int iVar1;
  undefined4 uVar2;
  undefined4 *puVar3;
  
  if (param_5 != (undefined4 *)0x0) {
    puVar3 = (undefined4 *)*param_1;
    iVar1 = puVar3[1];
    *param_5 = *puVar3;
    *(undefined2 *)((long)param_5 + 6) = *(undefined2 *)(puVar3 + 3);
    uVar2 = puVar3[2];
    *(undefined8 *)(param_5 + 6) = param_2;
    *(undefined8 *)(param_5 + 10) = param_3;
    *(undefined8 *)(param_5 + 0xc) = param_4;
    param_5[2] = uVar2;
    *param_1 = (long)puVar3 + (long)iVar1;
  }
  return param_5;
}



/* ------------------------------------------------------------------
 * Function: eai_op_layer_util_get_input_count
 * Address : 0010ec10
 * Size    : 0x18 bytes
 * ------------------------------------------------------------------ */

long eai_op_layer_util_get_input_count(long param_1)

{
  if (param_1 != 0) {
    return (long)*(short *)(param_1 + 0xc);
  }
  return 0xffffffff;
}



/* ------------------------------------------------------------------
 * Function: eai_op_layer_util_get_output_count
 * Address : 0010ec28
 * Size    : 0x18 bytes
 * ------------------------------------------------------------------ */

long eai_op_layer_util_get_output_count(long param_1)

{
  if (param_1 != 0) {
    return (long)*(short *)(param_1 + 0xe);
  }
  return 0xffffffff;
}



/* ------------------------------------------------------------------
 * Function: eai_op_layer_util_get_input
 * Address : 0010ec40
 * Size    : 0x28 bytes
 * ------------------------------------------------------------------ */

undefined8 eai_op_layer_util_get_input(long param_1,int param_2)

{
  undefined8 uVar1;
  
  uVar1 = 0;
  if (param_1 != 0) {
    if (*(short *)(param_1 + 0xc) <= param_2) {
      return 0;
    }
    uVar1 = *(undefined8 *)(*(long *)(param_1 + 0x40) + (long)param_2 * 8);
  }
  return uVar1;
}



/* ------------------------------------------------------------------
 * Function: eai_op_layer_util_get_output
 * Address : 0010ec68
 * Size    : 0x28 bytes
 * ------------------------------------------------------------------ */

undefined8 eai_op_layer_util_get_output(long param_1,int param_2)

{
  undefined8 uVar1;
  
  uVar1 = 0;
  if (param_1 != 0) {
    if (*(short *)(param_1 + 0xe) <= param_2) {
      return 0;
    }
    uVar1 = *(undefined8 *)(*(long *)(param_1 + 0x48) + (long)param_2 * 8);
  }
  return uVar1;
}



/* ------------------------------------------------------------------
 * Function: eai_op_layer_util_get_all_loaders
 * Address : 0010ec90
 * Size    : 0x1c bytes
 * ------------------------------------------------------------------ */

undefined8 eai_op_layer_util_get_all_loaders(undefined8 *param_1)

{
  *param_1 = &DAT_0012dcb0;
  return 0x35;
}



/* ------------------------------------------------------------------
 * Function: argmax_forward
 * Address : 0010f7d8
 * Size    : 0x6c bytes
 * ------------------------------------------------------------------ */

undefined8 argmax_forward(long param_1)

{
  long lVar1;
  long lVar2;
  undefined8 uVar3;
  
  lVar1 = eai_op_layer_util_get_input(param_1,0);
  lVar2 = eai_op_layer_util_get_output(param_1,0);
  if (((param_1 != 0) && (lVar1 != 0)) && (lVar2 != 0)) {
    uVar3 = argmin(lVar1,lVar2,*(undefined4 *)(param_1 + 0x70),*(undefined4 *)(param_1 + 0x74),
                   param_1 + 0x78);
    return uVar3;
  }
  return 1;
}



/* ------------------------------------------------------------------
 * Function: framer_incremental_forward
 * Address : 00113444
 * Size    : 0x29c bytes
 * ------------------------------------------------------------------ */

undefined8 framer_incremental_forward(long param_1)

{
  int iVar1;
  int iVar2;
  int iVar3;
  long lVar4;
  long lVar5;
  undefined8 uVar6;
  undefined4 uVar7;
  uint uVar8;
  uint uVar9;
  uint uVar10;
  int iVar11;
  undefined4 uVar12;
  
  lVar4 = eai_op_layer_util_get_input(param_1,0);
  lVar5 = eai_op_layer_util_get_output(param_1,0);
  if (lVar4 == 0) {
    return 1;
  }
  if (lVar5 == 0) {
    return 1;
  }
  uVar9 = *(uint *)(lVar4 + 0xc) & 0xf;
  if (uVar9 == 0) {
    uVar12 = 1;
    uVar8 = *(uint *)(lVar5 + 0xc);
    uVar9 = uVar8 & 0xf;
    if (*(int *)(param_1 + 0x78) == 0) goto LAB_001134d8;
LAB_001134b8:
    if (uVar9 != 0) {
      iVar11 = -1;
      goto LAB_001134ec;
    }
LAB_001134e0:
    iVar11 = 1;
  }
  else {
    uVar12 = *(undefined4 *)(lVar4 + (ulong)(uVar9 - 1) * 4 + 0x18);
    uVar8 = *(uint *)(lVar5 + 0xc);
    uVar9 = uVar8 & 0xf;
    if (*(int *)(param_1 + 0x78) != 0) goto LAB_001134b8;
LAB_001134d8:
    if (uVar9 < 2) goto LAB_001134e0;
    iVar11 = -2;
LAB_001134ec:
    iVar11 = *(int *)(lVar5 + (ulong)(uVar9 + iVar11) * 4 + 0x18);
  }
  uVar9 = *(uint *)(lVar5 + 0x14);
  if ((uVar9 == 0x34210) || (uVar9 == 0x23410)) {
    uVar10 = uVar9 >> 0x10;
  }
  else {
    uVar10 = uVar9 >> 0xc & 0xf;
  }
  iVar2 = uVar10 + (uVar8 & 0xf) + -5;
  if (iVar2 < 1) {
    iVar2 = 0;
  }
  iVar2 = tensor_get_stride(lVar5,iVar2);
  uVar8 = 0xc;
  if (uVar9 != 0x23410 && uVar9 != 0x34210) {
    uVar8 = 0x10;
  }
  iVar3 = (*(uint *)(lVar5 + 0xc) & 0xf) + (*(uint *)(lVar5 + 0x14) >> (ulong)uVar8 & 0xf) + -5;
  if (iVar3 < 1) {
    iVar3 = 0;
  }
  iVar3 = tensor_get_stride(lVar5,iVar3);
  uVar8 = (uint)(uVar9 == 0x23410 || uVar9 == 0x34210);
  if ((uVar9 == 0x34210) || (uVar9 == 0x23410)) {
    uVar9 = *(uint *)(lVar5 + 0xc) & 0xf;
    if (uVar9 < 2) goto LAB_00113638;
    uVar7 = *(undefined4 *)(lVar5 + (ulong)(uVar9 - 2) * 4 + 0x18);
    uVar9 = *(uint *)(param_1 + 0x78);
joined_r0x00113644:
    if (uVar9 == uVar8) goto LAB_001135d8;
LAB_00113648:
    (**(code **)(param_1 + 0x88))
              (*(undefined8 *)(lVar4 + 0x30),
               *(long *)(lVar5 + 0x30) + (long)*(int *)(param_1 + 0x84) * (long)iVar3,uVar12,uVar7);
    iVar2 = *(int *)(param_1 + 0x84) + 1;
    *(int *)(param_1 + 0x84) = iVar2;
  }
  else {
    uVar9 = *(uint *)(lVar5 + 0xc) & 0xf;
    if (uVar9 == 0) {
LAB_00113638:
      uVar7 = 1;
      uVar9 = *(uint *)(param_1 + 0x78);
      goto joined_r0x00113644;
    }
    uVar7 = *(undefined4 *)(lVar5 + (ulong)(uVar9 - 1) * 4 + 0x18);
    if (*(uint *)(param_1 + 0x78) != uVar8) goto LAB_00113648;
LAB_001135d8:
    memscpy(*(long *)(lVar5 + 0x30) + (long)*(int *)(param_1 + 0x84) * (long)iVar2,(long)iVar2,
            *(undefined8 *)(lVar4 + 0x30),(long)iVar2);
    iVar2 = *(int *)(param_1 + 0x84) + 1;
    *(int *)(param_1 + 0x84) = iVar2;
  }
  if (iVar11 <= iVar2) {
    *(undefined4 *)(param_1 + 0x84) = 0;
  }
  iVar2 = *(int *)(param_1 + 0x80) + 1;
  if (*(int *)(param_1 + 0x7c) == 0) {
    if (iVar11 <= iVar2) {
      iVar3 = *(int *)(param_1 + 0x74);
      iVar1 = 0;
      if (iVar3 != 0) {
        iVar1 = (iVar2 - iVar11) / iVar3;
      }
      if (iVar2 - iVar11 == iVar1 * iVar3) goto LAB_00113694;
    }
    uVar6 = 2;
  }
  else {
LAB_00113694:
    uVar6 = 0;
  }
  *(int *)(param_1 + 0x80) = iVar2;
  return uVar6;
}



/* ------------------------------------------------------------------
 * Function: framer_forward
 * Address : 001136e0
 * Size    : 0x354 bytes
 * ------------------------------------------------------------------ */

ulong framer_forward(long param_1)

{
  int iVar1;
  int iVar2;
  long lVar3;
  long lVar4;
  ulong uVar5;
  uint uVar6;
  int iVar7;
  int iVar8;
  uint uVar9;
  int iVar10;
  int iVar11;
  int iVar12;
  long lVar13;
  long lVar14;
  
  if ((param_1 != 0) && ((*(byte *)(param_1 + 8) & 1) != 0)) {
    uVar5 = framer_incremental_forward(param_1);
    return uVar5;
  }
  lVar3 = eai_op_layer_util_get_input(param_1,0);
  lVar4 = eai_op_layer_util_get_output(param_1,0);
  if (lVar3 == 0) {
    return 1;
  }
  if (lVar4 == 0) {
    return 1;
  }
  uVar9 = *(uint *)(lVar4 + 0xc) & 0xf;
  if (*(int *)(param_1 + 0x78) == 0) {
    if (uVar9 == 0) {
      iVar11 = 1;
    }
    else {
      iVar11 = *(int *)(lVar4 + (ulong)(uVar9 - 1) * 4 + 0x18);
      if (uVar9 - 1 != 0) {
        iVar10 = -2;
        goto LAB_001137b4;
      }
    }
LAB_001137c8:
    iVar10 = 1;
  }
  else {
    if (uVar9 < 2) {
      iVar11 = 1;
      if (uVar9 == 0) goto LAB_001137c8;
    }
    else {
      iVar11 = *(int *)(lVar4 + (ulong)(uVar9 - 2) * 4 + 0x18);
    }
    iVar10 = -1;
LAB_001137b4:
    iVar10 = *(int *)(lVar4 + (ulong)(uVar9 + iVar10) * 4 + 0x18);
  }
  uVar9 = *(uint *)(lVar4 + 0x14);
  if ((uVar9 == 0x34210) || (uVar9 == 0x23410)) {
    uVar6 = uVar9 >> 0x10;
  }
  else {
    uVar6 = uVar9 >> 0xc & 0xf;
  }
  iVar1 = uVar6 + (*(uint *)(lVar4 + 0xc) & 0xf) + -5;
  if (iVar1 < 1) {
    iVar1 = 0;
  }
  iVar1 = tensor_get_stride(lVar4,iVar1);
  uVar6 = 0xc;
  if (uVar9 != 0x23410 && uVar9 != 0x34210) {
    uVar6 = 0x10;
  }
  iVar2 = (*(uint *)(lVar4 + 0xc) & 0xf) + (*(uint *)(lVar4 + 0x14) >> (ulong)uVar6 & 0xf) + -5;
  if (iVar2 < 1) {
    iVar2 = 0;
  }
  iVar2 = tensor_get_stride(lVar4,iVar2);
  if ((uVar9 == 0x34210) || (uVar9 == 0x23410)) {
    uVar6 = *(uint *)(lVar4 + 0xc) & 0xf;
    if (1 < uVar6) {
      iVar12 = *(int *)(lVar4 + (ulong)(uVar6 - 2) * 4 + 0x18);
      uVar6 = *(uint *)(param_1 + 0x78);
      iVar8 = *(int *)(param_1 + 0x84);
      goto joined_r0x00113930;
    }
  }
  else {
    uVar6 = *(uint *)(lVar4 + 0xc) & 0xf;
    if (uVar6 != 0) {
      iVar12 = *(int *)(lVar4 + (ulong)(uVar6 - 1) * 4 + 0x18);
      uVar6 = *(uint *)(param_1 + 0x78);
      iVar8 = *(int *)(param_1 + 0x84);
      goto joined_r0x00113930;
    }
  }
  iVar12 = 1;
  uVar6 = *(uint *)(param_1 + 0x78);
  iVar8 = *(int *)(param_1 + 0x84);
joined_r0x00113930:
  if (uVar6 == (uVar9 == 0x23410 || uVar9 == 0x34210)) {
    if (iVar8 == iVar10) {
      memsmove(*(long *)(lVar4 + 0x30),(long)iVar1 * (long)iVar10,
               *(long *)(lVar4 + 0x30) + (long)*(int *)(param_1 + 0x74) * (long)iVar1,
               ((long)iVar10 - (long)*(int *)(param_1 + 0x74)) * (long)iVar1);
      iVar8 = *(int *)(param_1 + 0x84) - *(int *)(param_1 + 0x74);
      *(int *)(param_1 + 0x84) = iVar8;
    }
    memscpy(*(long *)(lVar4 + 0x30) + (long)iVar8 * (long)iVar1,(long)iVar1,
            *(undefined8 *)(lVar3 + 0x30),(long)iVar1);
  }
  else {
    if (iVar8 == iVar10) {
      iVar7 = *(int *)(param_1 + 0x74);
      iVar8 = iVar10;
      if (0 < iVar11) {
        lVar13 = *(long *)(lVar4 + 0x30);
        lVar14 = (long)((iVar12 - iVar7) * iVar2);
        iVar8 = iVar11;
        do {
          memsmove(lVar13,lVar14,lVar13 + (long)iVar7 * (long)iVar2,lVar14);
          lVar13 = lVar13 + iVar1;
          iVar8 = iVar8 + -1;
        } while (iVar8 != 0);
        iVar7 = *(int *)(param_1 + 0x74);
        iVar8 = *(int *)(param_1 + 0x84);
      }
      iVar8 = iVar8 - iVar7;
      *(int *)(param_1 + 0x84) = iVar8;
    }
    (**(code **)(param_1 + 0x88))
              (*(undefined8 *)(lVar3 + 0x30),*(long *)(lVar4 + 0x30) + (long)iVar8 * (long)iVar2,
               iVar11,iVar12);
  }
  iVar11 = *(int *)(param_1 + 0x84) + 1;
  *(int *)(param_1 + 0x84) = iVar11;
  uVar9 = 2;
  if (iVar10 <= iVar11 || *(int *)(param_1 + 0x7c) != 0) {
    uVar9 = 0;
  }
  return (ulong)uVar9;
}



/* ------------------------------------------------------------------
 * Function: gather_forward
 * Address : 00113ae0
 * Size    : 0x250 bytes
 * ------------------------------------------------------------------ */

undefined4 gather_forward(long param_1)

{
  long lVar1;
  int iVar2;
  int iVar3;
  int iVar4;
  long lVar5;
  long lVar6;
  long *plVar7;
  ulong uVar8;
  int iVar9;
  long lVar10;
  long lVar11;
  uint uVar12;
  undefined4 uVar13;
  long lVar14;
  long lVar15;
  long lVar16;
  int *piVar17;
  long lVar18;
  undefined8 local_a0;
  int local_98 [6];
  undefined8 local_80;
  undefined8 uStack_78;
  undefined4 local_70;
  long local_68;
  
  lVar1 = tpidr_el0;
  local_68 = *(long *)(lVar1 + 0x28);
  lVar5 = eai_op_layer_util_get_input(param_1,0);
  lVar6 = eai_op_layer_util_get_output(param_1,0);
  uVar13 = 1;
  plVar7 = (long *)eai_op_layer_util_get_input(param_1,1);
  local_a0 = lVar5;
  if (((lVar5 != 0) && (lVar6 != 0)) && (plVar7 != (long *)0x0)) {
    uVar12 = *(uint *)(param_1 + 0x70);
    if ((int)uVar12 < 0) {
      uVar12 = (*(uint *)(lVar5 + 0xc) & 0xf) + uVar12;
    }
    piVar17 = (int *)plVar7[6];
    iVar2 = dims_size((ulong)uVar12,lVar5 + 0x18);
    local_80 = 0;
    uStack_78 = 0;
    local_70 = 0;
    local_98[0] = 0;
    local_98[1] = 0;
    local_98[2] = 0;
    local_98[3] = 0;
    local_98[4] = 0;
    tensor_get_strides(lVar5,&local_80);
    tensor_get_strides(lVar6,local_98);
    if (uVar12 == 0) {
      lVar18 = 0;
      lVar5 = 0;
    }
    else {
      uVar8 = -(ulong)(uVar12 >> 0x1f) & 0xfffffffc00000000 | (ulong)uVar12 << 2;
      lVar18 = (long)*(int *)((long)local_98 + uVar8 + 0x14);
      lVar5 = (long)*(int *)((long)&local_a0 + uVar8 + 4);
    }
    lVar10 = *(long *)(lVar6 + 0x30);
    iVar3 = tensor_size(lVar6);
    if (*(ulong *)(*plVar7 + 4) >> 0x28 != 0) {
      if (iVar2 < 1) {
        uVar12 = 0;
        do {
          uVar12 = uVar12 + 1;
          tensor_size(plVar7);
        } while (uVar12 < (uint)((ulong)*(undefined8 *)(*plVar7 + 4) >> 0x28));
      }
      else {
        lVar11 = (long)(int)uVar12;
        uVar12 = 0;
        do {
          lVar14 = *(long *)(local_a0 + 0x30) +
                   (long)*(int *)((long)&local_80 + lVar11 * 4) * (long)*piVar17;
          lVar15 = *(long *)(lVar6 + 0x30) + (long)(int)(local_98[lVar11] * uVar12);
          lVar16 = (lVar10 + iVar3) - lVar15;
          iVar9 = iVar2;
          do {
            memscpy(lVar15,lVar16,lVar14,(long)*(int *)((long)&local_80 + lVar11 * 4));
            lVar14 = lVar14 + lVar18;
            iVar9 = iVar9 + -1;
            lVar15 = lVar15 + lVar5;
            lVar16 = lVar16 - lVar5;
          } while (iVar9 != 0);
          uVar12 = uVar12 + 1;
          iVar4 = tensor_size(plVar7);
          iVar9 = 0;
          if ((int)plVar7[3] != 0) {
            iVar9 = iVar4 / (int)plVar7[3];
          }
          piVar17 = (int *)((long)piVar17 + (long)iVar9);
        } while (uVar12 < (uint)((ulong)*(undefined8 *)(*plVar7 + 4) >> 0x28));
      }
    }
    uVar13 = 0;
  }
  if (*(long *)(lVar1 + 0x28) != local_68) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  return uVar13;
}



/* ------------------------------------------------------------------
 * Function: gru_forward_vanilla_step
 * Address : 0011447c
 * Size    : 0x6c0 bytes
 * ------------------------------------------------------------------ */

undefined8
gru_forward_vanilla_step
          (long param_1,long *param_2,undefined4 param_3,long *param_4,int param_5,int param_6,
          long *param_7,undefined4 param_8,long param_9,long *param_10,int param_11,long param_12,
          long param_13,long *param_14)

{
  long lVar1;
  long lVar2;
  int iVar3;
  uint uVar4;
  uint uVar5;
  uint uVar6;
  uint uVar7;
  long lVar8;
  uint uVar9;
  int iVar10;
  int iVar11;
  int iVar12;
  long lVar13;
  long lVar14;
  long lVar15;
  long lVar16;
  int iVar17;
  long lVar18;
  int iVar19;
  ulong uVar20;
  undefined4 local_c0;
  uint uStack_bc;
  int iStack_b8;
  int iStack_b4;
  long *local_b0;
  long *plStack_a8;
  long *local_a0;
  int local_98;
  int local_94;
  undefined8 local_90;
  ulong uStack_88;
  int local_80;
  undefined4 local_7c;
  ulong local_78;
  long local_70;
  
  lVar8 = tpidr_el0;
  local_70 = *(long *)(lVar8 + 0x28);
  iVar3 = *(int *)(param_1 + 0x70);
  iVar10 = *(int *)(param_1 + 0x74);
  uVar4 = *(uint *)(*param_14 + 4);
  uVar9 = iVar3 * 3;
  uVar5 = *(uint *)(*param_4 + 4);
  iVar19 = *(int *)((long)param_2 + 0x1c);
  uVar6 = *(uint *)(*param_10 + 4);
  _local_c0 = CONCAT44(uVar9,0x10100);
  _iStack_b8 = CONCAT44(iVar10,iVar19);
  local_90 = (ulong)((*(uint *)(*param_7 + 4) & 0xf) * iVar10) << 0x20;
  local_a0 = (long *)param_13;
  _local_98 = CONCAT44(param_3,param_8);
  uStack_88 = CONCAT44(*(uint *)(*param_2 + 4),*(uint *)(*param_7 + 4)) & 0xf0000000f;
  _local_80 = CONCAT44(1,(*(uint *)(*param_2 + 4) & 0xf) * iVar10);
  local_78 = (ulong)uVar9;
  local_b0 = param_7;
  plStack_a8 = param_2;
  do_gemm_with_helpers(&local_c0,param_1 + 0x90);
  if ((param_9 != 0) && (iVar10 = tensor_get_stride(param_13,0), 0 < iVar19)) {
    lVar15 = 0;
    iVar12 = iVar19;
    do {
      q_vector_add_vector_inplace_f(*(long *)(param_13 + 0x30) + lVar15,param_9,uVar9);
      lVar15 = lVar15 + iVar10;
      iVar12 = iVar12 + -1;
    } while (iVar12 != 0);
  }
  lVar15 = (long)iVar3;
  plStack_a8 = param_4;
  if (*(char *)(param_1 + 0x78) == '\0') {
    uVar20 = lVar15 << 1;
    iVar10 = (int)uVar20;
    _local_c0 = CONCAT44(iVar10,local_c0);
    local_b0 = param_10;
    local_a0 = param_14;
    _iStack_b8 = CONCAT44(iVar3,iVar19);
    _local_98 = CONCAT44(param_6,param_11);
    uVar7 = (uVar6 & 0xf) * iVar3;
    uStack_88 = CONCAT44(uVar5,uVar6) & 0xf0000000f;
    _local_80 = CONCAT44(1,(uVar5 & 0xf) * iVar3);
    local_90 = (ulong)uVar7 << 0x20;
    local_78 = CONCAT44(local_78._4_4_,uVar9);
    do_gemm_with_helpers(&local_c0,param_1 + 0xb0);
    if ((param_12 != 0) && (iVar12 = tensor_get_stride(param_14,0), 0 < iVar19)) {
      lVar16 = 0;
      iVar11 = iVar19;
      do {
        q_vector_add_vector_inplace_f(param_14[6] + lVar16,param_12,uVar20 & 0xffffffff);
        lVar16 = lVar16 + iVar12;
        iVar11 = iVar11 + -1;
      } while (iVar11 != 0);
    }
    iVar12 = tensor_get_stride(param_14,0);
    iVar11 = tensor_get_stride(param_4,2);
    if (0 < iVar19) {
      lVar16 = 0;
      lVar18 = (long)param_6;
      iVar17 = iVar19;
      do {
        lVar13 = param_14[6];
        lVar14 = param_4[6];
        lVar1 = *(long *)(param_13 + 0x30) + lVar16;
        q_vector_add_vector_inplace_f(lVar1,lVar13 + lVar16,uVar20 & 0xffffffff);
        q_vector_sigmoid_inplace_f(lVar1,uVar20 & 0xffffffff);
        q_vector_mul_vector_f(lVar13 + lVar16 + lVar15 * 4,lVar1 + lVar15 * 4,lVar14 + lVar18,iVar3)
        ;
        iVar17 = iVar17 + -1;
        lVar16 = lVar16 + iVar12;
        lVar18 = lVar18 + iVar11;
      } while (iVar17 != 0);
    }
    local_b0 = param_10;
    uVar5 = *(uint *)(*param_14 + 4) & 0xf;
    _local_c0 = CONCAT44(iVar3,local_c0);
    _iStack_b8 = CONCAT44(iVar3,iVar19);
    _local_98 = CONCAT44(uVar5 * iVar3,param_11 + iVar10 * iVar3 * (*(uint *)(*param_10 + 4) & 0xf))
    ;
    uStack_88 = CONCAT44(uVar4,uVar6) & 0xf0000000f;
    _local_80 = CONCAT44(1,(uVar4 & 0xf) * iVar3);
    local_90 = CONCAT44(uVar7,uVar5 * iVar10);
    local_78 = CONCAT44(local_78._4_4_,uVar9);
    plStack_a8 = param_14;
    local_a0 = param_14;
    do_gemm(&local_c0);
    if (param_12 != 0) {
      iVar10 = tensor_get_stride(param_14,0);
      if (iVar19 < 1) goto LAB_00114a10;
      lVar16 = lVar15 * 8;
      param_12 = param_12 + lVar16;
      iVar11 = iVar19;
      do {
        q_vector_add_vector_inplace_f(param_14[6] + lVar16,param_12,iVar3);
        lVar16 = lVar16 + iVar10;
        iVar11 = iVar11 + -1;
      } while (iVar11 != 0);
    }
    if (0 < iVar19) {
      uVar20 = -((uVar20 & 0xffffffff) >> 0x1f) & 0xfffffffc00000000 | (uVar20 & 0xffffffff) << 2;
      iVar10 = iVar19;
      do {
        lVar16 = *(long *)(param_13 + 0x30) + uVar20;
        q_vector_add_vector_inplace_f(lVar16,param_14[6] + uVar20,iVar3);
        q_vector_tanh_inplace_f(lVar16,iVar3);
        uVar20 = uVar20 + (long)iVar12;
        iVar10 = iVar10 + -1;
      } while (iVar10 != 0);
    }
  }
  else {
    local_b0 = param_10;
    local_a0 = param_14;
    _local_c0 = CONCAT44(uVar9,local_c0);
    _iStack_b8 = CONCAT44(iVar3,iVar19);
    _local_98 = CONCAT44(param_6,param_11);
    local_90 = (ulong)((uVar6 & 0xf) * iVar3) << 0x20;
    uStack_88 = CONCAT44(uVar5,uVar6) & 0xf0000000f;
    _local_80 = CONCAT44(1,(uVar5 & 0xf) * iVar3);
    local_78 = CONCAT44(local_78._4_4_,uVar9);
    do_gemm_with_helpers(&local_c0,param_1 + 0xb0);
    if (param_12 != 0) {
      iVar10 = tensor_get_stride(param_14,0);
      if (iVar19 < 1) {
        tensor_get_stride(param_14,0);
        goto LAB_00114a10;
      }
      lVar16 = 0;
      iVar12 = iVar19;
      do {
        q_vector_add_vector_inplace_f(param_14[6] + lVar16,param_12,uVar9);
        lVar16 = lVar16 + iVar10;
        iVar12 = iVar12 + -1;
      } while (iVar12 != 0);
    }
    iVar10 = tensor_get_stride(param_14,0);
    if (0 < iVar19) {
      lVar16 = 0;
      iVar12 = iVar19;
      do {
        lVar18 = *(long *)(param_13 + 0x30) + lVar16;
        lVar1 = lVar18 + lVar15 * 4;
        lVar13 = lVar18 + lVar15 * 8;
        lVar14 = param_14[6] + lVar16 + lVar15 * 8;
        q_vector_add_vector_inplace_f(lVar18,param_14[6] + lVar16,iVar3 << 1);
        q_vector_sigmoid_inplace_f(lVar18,iVar3 << 1);
        q_vector_conv_x32_x16_f(lVar18,lVar18,iVar3);
        q_vector_conv_x32_x16_f(lVar1,lVar1,iVar3);
        q_vector_mul_vector_f(lVar14,lVar14,lVar1,iVar3);
        q_vector_add_vector_inplace_f(lVar13,lVar14,iVar3);
        q_vector_tanh_inplace_f(lVar13,iVar3);
        iVar12 = iVar12 + -1;
        lVar16 = lVar16 + iVar10;
      } while (iVar12 != 0);
    }
  }
LAB_00114a10:
  iVar10 = tensor_get_stride(param_14,0);
  iVar12 = tensor_get_stride(param_4,2);
  if (0 < iVar19) {
    lVar16 = 0;
    lVar18 = 0;
    do {
      lVar1 = *(long *)(param_13 + 0x30) + lVar18;
      lVar13 = param_4[6] + param_5 + lVar16;
      lVar14 = param_6 + lVar16;
      lVar2 = lVar1 + lVar15 * 8;
      lVar16 = lVar16 + iVar12;
      q_vector_mul_vector_f(lVar13,param_4[6] + lVar14,lVar1,iVar3);
      q_vector_add_vector_inplace_f(lVar13,lVar2,iVar3);
      q_vector_mul_vector_f(lVar2,lVar2,lVar1,iVar3);
      q_vector_sub_vector_inplace_f(lVar13,lVar2,iVar3);
      iVar19 = iVar19 + -1;
      lVar18 = lVar18 + iVar10;
    } while (iVar19 != 0);
  }
  if (*(long *)(lVar8 + 0x28) != local_70) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  return 0;
}



/* ------------------------------------------------------------------
 * Function: gru_forward_vanilla_core
 * Address : 00114b3c
 * Size    : 0x404 bytes
 * ------------------------------------------------------------------ */

undefined8
gru_forward_vanilla_core
          (long param_1,undefined8 param_2,long param_3,undefined8 param_4,long param_5,long param_6
          ,long param_7,undefined8 param_8,undefined8 param_9,undefined8 param_10,long param_11)

{
  int iVar1;
  int iVar2;
  int iVar3;
  int iVar4;
  long lVar5;
  int iVar6;
  undefined4 uVar7;
  undefined4 uVar8;
  int iVar9;
  int iVar10;
  long lVar11;
  int iVar12;
  int iVar13;
  int iVar14;
  long local_90;
  undefined8 local_80;
  undefined8 uStack_78;
  long local_70;
  
  lVar5 = tpidr_el0;
  local_70 = *(long *)(lVar5 + 0x28);
  iVar3 = *(int *)(param_6 + 0x18);
  iVar1 = *(int *)(param_3 + 0x18);
  iVar2 = *(int *)(param_3 + 0x1c);
  iVar4 = *(int *)(param_6 + 0x20);
  iVar6 = tensor_get_stride(param_3,0);
  local_80 = 0;
  uStack_78 = 0;
  tensor_get_strides(param_2,&local_80);
  if (param_5 == 0) {
    local_90 = 0;
    if (param_7 != 0) goto LAB_00114be4;
LAB_00114c08:
    lVar11 = 0;
  }
  else {
    local_90 = *(long *)(param_5 + 0x30);
    if (param_7 == 0) goto LAB_00114c08;
LAB_00114be4:
    lVar11 = *(long *)(param_7 + 0x30);
  }
  iVar12 = iVar1 + -1;
  if (*(char *)(param_1 + 0x79) != '\x04') {
    if (0 < iVar1) {
      iVar13 = 0;
      iVar14 = iVar1;
      iVar9 = (int)local_80 * iVar12;
      iVar10 = 0;
      do {
        gru_forward_vanilla_step
                  (param_1,param_3,iVar13,param_2,iVar10,iVar9,param_4,0,local_90,param_6,0,lVar11,
                   param_8,param_9);
        iVar13 = iVar13 + iVar6;
        iVar14 = iVar14 + -1;
        iVar9 = iVar10;
        iVar10 = (int)local_80 + iVar10;
      } while (iVar14 != 0);
    }
    if (param_11 != 0) {
      copy_tensor(param_11,0,param_2,(int)local_80 * iVar12,iVar4 * iVar2);
    }
    if (*(char *)(param_1 + 0x79) == '\x02') goto LAB_00114f04;
  }
  iVar13 = (int)local_80 * iVar12;
  if (iVar3 == 2) {
    iVar14 = local_80._4_4_;
    iVar13 = local_80._4_4_ + iVar13;
    uVar7 = tensor_get_stride(param_4,0);
    uVar8 = tensor_get_stride(param_6,0);
    if (local_90 != 0) {
      local_90 = *(long *)(param_5 + 0x30);
      iVar9 = tensor_get_stride(param_5,0);
      local_90 = local_90 + iVar9;
    }
    if (lVar11 == 0) {
      lVar11 = 0;
    }
    else {
      lVar11 = *(long *)(param_7 + 0x30);
      iVar9 = tensor_get_stride(param_7,0);
      lVar11 = lVar11 + iVar9;
    }
  }
  else {
    iVar14 = 0;
    uVar7 = 0;
    uVar8 = 0;
  }
  if (0 < iVar1) {
    iVar12 = iVar6 * iVar12;
    do {
      iVar9 = iVar13;
      gru_forward_vanilla_step
                (param_1,param_3,iVar12,param_2,iVar9,iVar14,param_4,uVar7,local_90,param_6,uVar8,
                 lVar11,param_8,param_9);
      iVar12 = iVar12 - iVar6;
      iVar1 = iVar1 + -1;
      iVar13 = iVar9 - (int)local_80;
      iVar14 = iVar9;
    } while (iVar1 != 0);
  }
  if (param_11 != 0) {
    if (iVar3 == 1) {
      uVar8 = 0;
      uVar7 = 0;
    }
    else {
      uVar8 = tensor_get_stride(param_11,0);
      uVar7 = local_80._4_4_;
    }
    copy_tensor(param_11,uVar8,param_2,uVar7,iVar4 * iVar2);
  }
LAB_00114f04:
  if (*(long *)(lVar5 + 0x28) != local_70) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  return 0;
}



/* ------------------------------------------------------------------
 * Function: gru_forward_vanilla
 * Address : 00114f40
 * Size    : 0x214 bytes
 * ------------------------------------------------------------------ */

undefined4 gru_forward_vanilla(long param_1)

{
  int iVar1;
  int iVar2;
  int iVar3;
  long lVar4;
  long lVar5;
  long lVar6;
  undefined8 uVar7;
  long lVar8;
  undefined8 uVar9;
  long lVar10;
  long lVar11;
  long lVar12;
  
  lVar4 = eai_op_layer_util_get_input(param_1,0);
  lVar5 = eai_op_layer_util_get_output(param_1,0);
  eai_op_layer_util_get_output(param_1,1);
  if (lVar4 == 0) {
    return 1;
  }
  if (lVar5 == 0) {
    return 1;
  }
  lVar6 = eai_op_layer_util_get_input(param_1,1);
  uVar7 = eai_op_layer_util_get_input(param_1,2);
  lVar8 = eai_op_layer_util_get_input(param_1,3);
  uVar9 = eai_op_layer_util_get_input(param_1,4);
  lVar10 = eai_op_layer_util_get_input(param_1,6);
  lVar11 = eai_op_layer_util_get_input(param_1,7);
  eai_op_layer_util_get_input(param_1,8);
  if (lVar10 == 0) {
    return 1;
  }
  if (lVar11 == 0) {
    return 1;
  }
  if (lVar6 == 0) {
    return 1;
  }
  if (lVar8 == 0) {
    return 1;
  }
  if (*(int *)(param_1 + 0x7c) == 0) {
    iVar1 = *(int *)(lVar4 + 0x18);
    iVar3 = *(int *)(lVar4 + 0x1c);
    iVar2 = *(int *)(param_1 + 0x70);
    lVar12 = eai_op_layer_util_get_input(param_1,5);
    if (lVar12 == 0) {
      tensor_set_zero(lVar5);
    }
    else {
      iVar2 = iVar2 * iVar3;
      if (1 < iVar1) {
        tensor_set_zero(lVar5);
      }
      if (*(char *)(param_1 + 0x79) == '\x04') {
        iVar3 = 0;
      }
      else {
        iVar3 = tensor_get_stride(lVar5,0);
        iVar3 = iVar3 * (iVar1 + -1);
        copy_tensor(lVar5,iVar3,lVar12,0,iVar2);
        if (*(char *)(param_1 + 0x79) == '\x02') goto LAB_001150f0;
        if (*(char *)(param_1 + 0x79) == '\b') {
          iVar3 = tensor_get_stride(lVar12,0);
        }
      }
      copy_tensor(lVar5,iVar3,lVar12,iVar3,iVar2);
    }
  }
LAB_001150f0:
  gru_forward_vanilla_core(param_1,lVar5,lVar4,lVar6,uVar7,lVar8,uVar9,lVar10,lVar11);
  *(int *)(param_1 + 0x7c) = *(int *)(param_1 + 0x7c) + 1;
  return 0;
}



/* ------------------------------------------------------------------
 * Function: logsoftmax_forward
 * Address : 00115330
 * Size    : 0x70 bytes
 * ------------------------------------------------------------------ */

undefined8 logsoftmax_forward(long param_1)

{
  long lVar1;
  long lVar2;
  undefined8 uVar3;
  
  lVar1 = eai_op_layer_util_get_output(param_1,0);
  lVar2 = eai_op_layer_util_get_input(param_1,0);
  if (((param_1 != 0) && (lVar1 != 0)) && (lVar2 != 0)) {
    uVar3 = softmax_forward_base
                      (lVar1,lVar2,*(undefined4 *)(param_1 + 0x70),*(undefined4 *)(param_1 + 0x74),1
                       ,param_1 + 0x78);
    return uVar3;
  }
  return 1;
}



/* ------------------------------------------------------------------
 * Function: lstm_forward_vanilla_step
 * Address : 001153e4
 * Size    : 0x3d8 bytes
 * ------------------------------------------------------------------ */

undefined8
lstm_forward_vanilla_step
          (long param_1,long *param_2,undefined4 param_3,long *param_4,int param_5,
          undefined4 param_6,long *param_7,undefined4 param_8,long param_9,long *param_10,
          undefined4 param_11,long param_12,long param_13,long *param_14,long param_15,int param_16)

{
  long lVar1;
  long lVar2;
  long lVar3;
  long lVar4;
  long lVar5;
  int iVar6;
  uint uVar7;
  uint uVar8;
  uint uVar9;
  long lVar10;
  int iVar11;
  int iVar12;
  int iVar13;
  int iVar14;
  long lVar15;
  long lVar16;
  long lVar17;
  long lVar18;
  uint local_108;
  undefined4 local_c0;
  uint uStack_bc;
  int iStack_b8;
  int iStack_b4;
  long *local_b0;
  long *plStack_a8;
  long *local_a0;
  undefined4 uStack_98;
  undefined4 uStack_94;
  long local_90;
  ulong local_88;
  int local_80;
  undefined4 uStack_7c;
  ulong uStack_78;
  long local_70;
  
  lVar10 = tpidr_el0;
  local_70 = *(long *)(lVar10 + 0x28);
  iVar6 = *(int *)(param_1 + 0x70);
  iVar12 = *(int *)(param_1 + 0x74);
  uVar7 = *(uint *)(*param_2 + 4);
  lVar15 = (long)iVar6;
  uVar8 = *(uint *)(*param_7 + 4);
  local_b0 = param_10;
  iVar14 = *(int *)((long)param_2 + 0x1c);
  uVar9 = iVar6 << 2;
  _uStack_98 = CONCAT44(param_6,param_11);
  _iStack_b8 = CONCAT44(iVar6,iVar14);
  local_90 = (ulong)((*(uint *)(*param_10 + 4) & 0xf) * iVar6) << 0x20;
  _local_c0 = CONCAT44(uVar9,0x10100);
  local_a0 = param_14;
  local_108 = uVar7 & 0xf;
  local_88 = CONCAT44(*(uint *)(*param_4 + 4),*(uint *)(*param_10 + 4)) & 0xf0000000f;
  _local_80 = CONCAT44(1,(*(uint *)(*param_4 + 4) & 0xf) * iVar6);
  uStack_78 = (ulong)uVar9;
  plStack_a8 = param_4;
  do_gemm_with_helpers(&local_c0,param_1 + 0x90);
  if ((param_12 != 0) && (iVar11 = tensor_get_stride(param_14,0), 0 < iVar14)) {
    lVar17 = 0;
    iVar13 = iVar14;
    do {
      q_vector_add_vector_inplace_f(param_14[6] + lVar17,param_12,uVar9);
      lVar17 = lVar17 + iVar11;
      iVar13 = iVar13 + -1;
    } while (iVar13 != 0);
  }
  _local_c0 = CONCAT44(uVar9,local_c0);
  _iStack_b8 = CONCAT44(iVar12,iVar14);
  uStack_78 = CONCAT44(uStack_78._4_4_,uVar9);
  _uStack_98 = CONCAT44(param_3,param_8);
  local_a0 = (long *)param_13;
  local_90 = (ulong)((uVar8 & 0xf) * iVar12) << 0x20;
  local_88 = CONCAT44(uVar7,uVar8) & 0xf0000000f;
  _local_80 = CONCAT44(1,local_108 * iVar12);
  local_b0 = param_7;
  plStack_a8 = param_2;
  do_gemm_with_helpers(&local_c0,param_1 + 0xb0);
  if ((param_9 != 0) && (iVar12 = tensor_get_stride(param_13,0), 0 < iVar14)) {
    lVar17 = 0;
    iVar11 = iVar14;
    do {
      q_vector_add_vector_inplace_f(*(long *)(param_13 + 0x30) + lVar17,param_9,uVar9);
      lVar17 = lVar17 + iVar12;
      iVar11 = iVar11 + -1;
    } while (iVar11 != 0);
  }
  q_vector_add_vector_inplace_f
            (*(undefined8 *)(param_13 + 0x30),param_14[6],*(ulong *)(*param_14 + 4) >> 0x28);
  iVar12 = tensor_get_stride(param_13,0);
  iVar11 = tensor_get_stride(param_15,1);
  iVar13 = tensor_get_stride(param_4,2);
  if (0 < iVar14) {
    lVar17 = 0;
    lVar16 = (long)param_5;
    lVar18 = (long)param_16;
    do {
      lVar1 = *(long *)(param_13 + 0x30) + lVar17;
      lVar2 = lVar1 + lVar15 * 4;
      lVar3 = lVar1 + lVar15 * 8;
      lVar4 = lVar1 + lVar15 * 0xc;
      q_vector_sigmoid_inplace_f(lVar1,iVar6);
      q_vector_sigmoid_inplace_f(lVar2,iVar6);
      q_vector_sigmoid_inplace_f(lVar3,iVar6);
      q_vector_tanh_inplace_f(lVar4,iVar6);
      q_vector_conv_x32_x16_f(lVar1,lVar1,iVar6);
      q_vector_conv_x32_x16_f(lVar2,lVar2,iVar6);
      q_vector_conv_x32_x16_f(lVar3,lVar3,iVar6);
      lVar5 = *(long *)(param_15 + 0x30) + lVar18;
      q_vector_mul_vector_f(lVar5,lVar5,lVar3,iVar6);
      q_vector_mul_vector_f(lVar3,lVar4,lVar1,iVar6);
      q_vector_add_vector_f(lVar5,lVar5,lVar3,iVar6);
      q_vector_tanh_f(lVar3,lVar5,iVar6);
      q_vector_mul_vector_f(param_4[6] + lVar16,lVar3,lVar2,iVar6);
      iVar14 = iVar14 + -1;
      lVar17 = lVar17 + iVar12;
      lVar16 = lVar16 + iVar13;
      lVar18 = lVar18 + iVar11;
    } while (iVar14 != 0);
  }
  if (*(long *)(lVar10 + 0x28) == local_70) {
    return 0;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: lstm_forward_vanilla_core
 * Address : 001157bc
 * Size    : 0x464 bytes
 * ------------------------------------------------------------------ */

undefined8
lstm_forward_vanilla_core
          (undefined8 param_1,undefined8 param_2,long param_3,undefined8 param_4,long param_5,
          long param_6,long param_7,long *param_8,undefined8 param_9,undefined8 param_10,
          undefined8 param_11,long param_12,long param_13,uint param_14)

{
  int iVar1;
  int iVar2;
  int iVar3;
  long lVar4;
  int iVar5;
  undefined4 uVar6;
  undefined4 uVar7;
  undefined4 uVar8;
  int iVar9;
  int iVar10;
  long lVar11;
  long lVar12;
  int iVar13;
  int iVar14;
  int iVar15;
  int iVar16;
  undefined8 local_80;
  undefined8 uStack_78;
  long local_70;
  
  lVar4 = tpidr_el0;
  local_70 = *(long *)(lVar4 + 0x28);
  iVar2 = *(int *)(param_6 + 0x18);
  iVar14 = *(int *)(param_3 + 0x18);
  iVar1 = *(int *)(param_3 + 0x1c);
  iVar3 = *(int *)(param_6 + 0x20);
  iVar5 = tensor_get_stride(param_3,0);
  local_80 = 0;
  uStack_78 = 0;
  tensor_get_strides(param_2,&local_80);
  if (param_5 == 0) {
    lVar11 = 0;
  }
  else {
    lVar11 = *(long *)(param_5 + 0x30);
  }
  if (param_7 == 0) {
    lVar12 = 0;
  }
  else {
    lVar12 = *(long *)(param_7 + 0x30);
  }
  if ((param_14 & 1) != 0) {
    if (0 < iVar14) {
      iVar13 = 0;
      iVar15 = 0;
      iVar9 = (int)local_80 * (iVar14 + -1);
      iVar16 = iVar14;
      do {
        iVar10 = iVar15;
        lstm_forward_vanilla_step
                  (param_1,param_3,iVar13,param_2,iVar10,iVar9,param_4,0,lVar11,param_6,0,lVar12,
                   param_9,param_10,param_8,0);
        iVar13 = iVar13 + iVar5;
        iVar16 = iVar16 + -1;
        iVar15 = (int)local_80 + iVar10;
        iVar9 = iVar10;
      } while (iVar16 != 0);
    }
    if (param_12 != 0) {
      copy_tensor(param_12,0,param_2,(int)local_80 * (iVar14 + -1),iVar3 * iVar1);
    }
  }
  if ((param_14 >> 1 & 1) != 0) {
    iVar13 = (int)local_80 * (iVar14 + -1);
    if (iVar2 == 2) {
      iVar16 = local_80._4_4_;
      iVar13 = local_80._4_4_ + iVar13;
      uVar6 = tensor_get_stride(param_4,0);
      uVar7 = tensor_get_stride(param_6,0);
      uVar8 = tensor_get_stride(param_8,0);
      if (lVar11 == 0) {
        lVar11 = 0;
      }
      else {
        lVar11 = *(long *)(param_5 + 0x30);
        iVar9 = tensor_get_stride(param_5,0);
        lVar11 = lVar11 + iVar9;
      }
      if (lVar12 == 0) {
        lVar12 = 0;
      }
      else {
        lVar12 = *(long *)(param_7 + 0x30);
        iVar9 = tensor_get_stride(param_7,0);
        lVar12 = lVar12 + iVar9;
      }
    }
    else {
      iVar16 = 0;
      uVar6 = 0;
      uVar7 = 0;
      uVar8 = 0;
    }
    if (0 < iVar14) {
      iVar9 = iVar5 * (iVar14 + -1);
      do {
        iVar15 = iVar13;
        lstm_forward_vanilla_step
                  (param_1,param_3,iVar9,param_2,iVar15,iVar16,param_4,uVar6,lVar11,param_6,uVar7,
                   lVar12,param_9,param_10,param_8,uVar8);
        iVar9 = iVar9 - iVar5;
        iVar14 = iVar14 + -1;
        iVar13 = iVar15 - (int)local_80;
        iVar16 = iVar15;
      } while (iVar14 != 0);
    }
    if (param_12 != 0) {
      if (iVar2 == 1) {
        uVar8 = 0;
        uVar6 = 0;
      }
      else {
        uVar8 = tensor_get_stride(param_12,0);
        uVar6 = local_80._4_4_;
      }
      copy_tensor(param_12,uVar8,param_2,uVar6,iVar3 * iVar1);
    }
  }
  if (param_13 != 0) {
    copy_tensor(param_13,0,param_8,0,*(ulong *)(*param_8 + 4) >> 0x28);
  }
  if (*(long *)(lVar4 + 0x28) == local_70) {
    return 0;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: lstm_forward_vanilla
 * Address : 00115c20
 * Size    : 0x380 bytes
 * ------------------------------------------------------------------ */

undefined8 lstm_forward_vanilla(long param_1)

{
  int iVar1;
  int iVar2;
  int iVar3;
  byte bVar4;
  int iVar5;
  undefined4 uVar6;
  long lVar7;
  long *plVar8;
  long *plVar9;
  long *plVar10;
  long lVar11;
  long lVar12;
  undefined8 uVar13;
  long lVar14;
  long *plVar15;
  long lVar16;
  long *plVar17;
  long lVar18;
  long lVar19;
  char *pcVar20;
  long lVar21;
  long lVar22;
  ulong uVar23;
  undefined8 uVar24;
  
  lVar7 = eai_op_layer_util_get_input(param_1,0);
  plVar8 = (long *)eai_op_layer_util_get_output(param_1,0);
  uVar24 = 1;
  plVar9 = (long *)eai_op_layer_util_get_output(param_1,1);
  plVar10 = (long *)eai_op_layer_util_get_output(param_1,2);
  if (lVar7 == 0) {
    return 1;
  }
  if (plVar8 == (long *)0x0) {
    return 1;
  }
  lVar11 = eai_op_layer_util_get_input(param_1,1);
  lVar12 = eai_op_layer_util_get_input(param_1,2);
  uVar13 = eai_op_layer_util_get_input(param_1,3);
  lVar14 = eai_op_layer_util_get_input(param_1,4);
  plVar15 = (long *)eai_op_layer_util_get_input(param_1,6);
  lVar16 = eai_op_layer_util_get_input(param_1,5);
  plVar17 = (long *)eai_op_layer_util_get_input(param_1,8);
  lVar18 = eai_op_layer_util_get_input(param_1,9);
  lVar19 = eai_op_layer_util_get_input(param_1,10);
  eai_op_layer_util_get_input(param_1,0xb);
  if (lVar11 == 0) {
    return 1;
  }
  if (lVar12 == 0) {
    return 1;
  }
  if (lVar14 == 0) {
    return 1;
  }
  if (plVar17 == (long *)0x0) {
    return 1;
  }
  if (lVar18 == 0) {
    return 1;
  }
  if (lVar19 == 0) {
    return 1;
  }
  if (plVar9 == (long *)0x0) {
LAB_00115dc4:
    if (plVar10 != (long *)0x0) {
      lVar21 = *plVar10;
      lVar22 = *plVar17;
      uVar23 = *(ulong *)(lVar22 + 4) ^ *(ulong *)(lVar21 + 4);
      if ((((uVar23 & 0xf) != 0) || (*(int *)(lVar21 + 0xc) != *(int *)(lVar22 + 0xc))) ||
         (((uVar23 & 0xff00000000) != 0 || (*(short *)(lVar21 + 0x10) != *(short *)(lVar22 + 0x10)))
         )) {
        pcVar20 = "Cell and output_c have different quantization parameters\n";
        goto LAB_00115df4;
      }
    }
    if (*(int *)(param_1 + 0x7c) == 0) {
      if (lVar16 == 0) {
        tensor_set_zero(plVar8);
      }
      else {
        iVar1 = *(int *)(lVar7 + 0x18);
        iVar2 = *(int *)(lVar7 + 0x1c);
        iVar3 = *(int *)(param_1 + 0x70);
        if (iVar1 < 2) {
          bVar4 = *(byte *)(param_1 + 0x78);
        }
        else {
          tensor_set_zero(plVar8);
          bVar4 = *(byte *)(param_1 + 0x78);
        }
        if ((bVar4 & 1) != 0) {
          iVar5 = tensor_get_stride(plVar8,0);
          copy_tensor(plVar8,iVar5 * (iVar1 + -1),lVar16,0,iVar3 * iVar2);
          bVar4 = *(byte *)(param_1 + 0x78);
        }
        if ((bVar4 >> 1 & 1) != 0) {
          if (bVar4 == 3) {
            uVar6 = tensor_get_stride(lVar16,0);
          }
          else {
            uVar6 = 0;
          }
          copy_tensor(plVar8,uVar6,lVar16,uVar6,iVar3 * iVar2);
        }
      }
      if (plVar15 == (long *)0x0) {
        tensor_set_zero(plVar17);
      }
      else {
        copy_tensor(plVar17,0,plVar15,0,*(ulong *)(*plVar15 + 4) >> 0x28);
      }
    }
    lstm_forward_vanilla_core
              (param_1,plVar8,lVar7,lVar11,uVar13,lVar12,lVar14,plVar17,lVar18,lVar19);
    uVar24 = 0;
    *(int *)(param_1 + 0x7c) = *(int *)(param_1 + 0x7c) + 1;
  }
  else {
    lVar21 = *plVar9;
    lVar22 = *plVar8;
    uVar23 = *(ulong *)(lVar22 + 4) ^ *(ulong *)(lVar21 + 4);
    if (((((uVar23 & 0xf) == 0) && (*(int *)(lVar21 + 0xc) == *(int *)(lVar22 + 0xc))) &&
        ((uVar23 & 0xff00000000) == 0)) && (*(short *)(lVar21 + 0x10) == *(short *)(lVar22 + 0x10)))
    goto LAB_00115dc4;
    pcVar20 = "Output and output_h have different quantization parameters\n";
LAB_00115df4:
    __android_log_print(6,&DAT_00101ae3,pcVar20);
  }
  return uVar24;
}



/* ------------------------------------------------------------------
 * Function: matmul_broadcasting_forward
 * Address : 0011606c
 * Size    : 0x4a0 bytes
 * ------------------------------------------------------------------ */

void matmul_broadcasting_forward(long param_1)

{
  uint uVar1;
  long lVar2;
  int iVar3;
  int iVar4;
  int iVar5;
  int iVar6;
  long lVar7;
  long lVar8;
  undefined8 uVar9;
  uint uVar10;
  int iVar11;
  int iVar12;
  int iVar13;
  long lVar14;
  int iVar15;
  int iVar16;
  undefined *local_d0;
  undefined *puStack_c8;
  byte local_c0;
  byte bStack_bf;
  byte bStack_be;
  undefined1 uStack_bd;
  undefined4 uStack_bc;
  undefined4 uStack_b8;
  int iStack_b4;
  long local_b0;
  long lStack_a8;
  long local_a0;
  undefined8 local_98;
  int local_90;
  undefined4 uStack_8c;
  undefined4 uStack_88;
  undefined4 uStack_84;
  undefined4 uStack_80;
  undefined4 local_7c;
  undefined4 uStack_78;
  undefined4 uStack_74;
  long local_68;
  
  lVar2 = tpidr_el0;
  local_68 = *(long *)(lVar2 + 0x28);
  lVar7 = eai_op_layer_util_get_input(param_1,0);
  lVar14 = *(long *)(param_1 + 0x70);
  lVar8 = eai_op_layer_util_get_output(param_1,0);
  uVar9 = 1;
  if ((((lVar7 != 0) && (lVar14 != 0)) && (lVar8 != 0)) &&
     ((uVar10 = *(uint *)(lVar7 + 0xc) & 0xf, uVar10 != 0 && ((*(byte *)(lVar14 + 0xc) & 0xf) != 0))
     )) {
    if (uVar10 < 3) {
      iVar4 = 0;
      iVar6 = 0;
      uVar10 = *(uint *)(lVar14 + 0xc);
joined_r0x00116504:
      uVar10 = uVar10 & 0xf;
      iVar12 = iVar6;
      if (2 < uVar10) goto LAB_0011641c;
LAB_0011610c:
      iVar5 = 0;
      iVar12 = iVar6;
LAB_00116110:
      iVar6 = 0;
    }
    else {
      if (*(int *)(lVar7 + (ulong)(uVar10 - 3) * 4 + 0x18) == 1) {
        iVar4 = 0;
      }
      else {
        iVar4 = uVar10 + (*(uint *)(lVar7 + 0x14) >> 8 & 0xf) + -5;
        if (iVar4 < 1) {
          iVar4 = 0;
        }
        iVar4 = tensor_get_stride(lVar7,iVar4);
        uVar10 = *(uint *)(lVar7 + 0xc) & 0xf;
      }
      if ((3 < uVar10) && (*(int *)(lVar7 + (ulong)(uVar10 - 4) * 4 + 0x18) != 1)) {
        iVar6 = uVar10 + (*(uint *)(lVar7 + 0x14) >> 4 & 0xf) + -5;
        if (iVar6 < 1) {
          iVar6 = 0;
        }
        iVar6 = tensor_get_stride(lVar7,iVar6);
        uVar10 = *(uint *)(lVar14 + 0xc);
        goto joined_r0x00116504;
      }
      iVar6 = 0;
      iVar12 = 0;
      uVar10 = *(uint *)(lVar14 + 0xc) & 0xf;
      if (uVar10 < 3) goto LAB_0011610c;
LAB_0011641c:
      if (*(int *)(lVar14 + (ulong)(uVar10 - 3) * 4 + 0x18) != 1) {
        iVar6 = uVar10 + (*(uint *)(lVar14 + 0x14) >> 8 & 0xf) + -5;
        if (iVar6 < 1) {
          iVar6 = 0;
        }
        iVar5 = tensor_get_stride(lVar14,iVar6);
        uVar10 = *(uint *)(lVar14 + 0xc) & 0xf;
        iVar6 = iVar5;
        if (3 < uVar10) goto LAB_00116470;
        goto LAB_00116110;
      }
      iVar5 = 0;
      iVar6 = 0;
      if (uVar10 < 4) goto LAB_00116110;
LAB_00116470:
      iVar5 = iVar6;
      if (*(int *)(lVar14 + (ulong)(uVar10 - 4) * 4 + 0x18) == 1) goto LAB_00116110;
      iVar6 = uVar10 + (*(uint *)(lVar14 + 0x14) >> 4 & 0xf) + -5;
      if (iVar6 < 1) {
        iVar6 = 0;
      }
      iVar6 = tensor_get_stride(lVar14,iVar6);
    }
    iVar3 = (*(uint *)(lVar8 + 0xc) & 0xf) + (*(uint *)(lVar8 + 0x14) >> 8 & 0xf) + -5;
    if (iVar3 < 1) {
      iVar3 = 0;
    }
    iVar3 = tensor_get_stride(lVar8,iVar3);
    local_98 = 0;
    local_90 = 0;
    uStack_74 = 0;
    uStack_bd = 0;
    if (*(int *)(lVar7 + 0x14) == 0x43210) {
      local_c0 = *(byte *)(param_1 + 8) >> 1 & 1;
      if (*(int *)(lVar14 + 0x14) != 0x43210) goto LAB_00116180;
LAB_001161d4:
      bStack_bf = *(byte *)(param_1 + 8) >> 2 & 1;
      if (*(int *)(lVar8 + 0x14) == 0x43210) goto LAB_001161f0;
LAB_00116194:
      bStack_be = 1;
    }
    else {
      local_c0 = 1;
      if (*(int *)(lVar14 + 0x14) == 0x43210) goto LAB_001161d4;
LAB_00116180:
      bStack_bf = 1;
      if (*(int *)(lVar8 + 0x14) != 0x43210) goto LAB_00116194;
LAB_001161f0:
      bStack_be = *(byte *)(param_1 + 8) >> 3 & 1;
    }
    iStack_b4 = *(int *)(param_1 + 0x80);
    uStack_bc = (undefined4)*(undefined8 *)(param_1 + 0x78);
    uStack_b8 = (undefined4)((ulong)*(undefined8 *)(param_1 + 0x78) >> 0x20);
    uStack_84 = (undefined4)*(undefined8 *)(param_1 + 0x8c);
    uStack_80 = (undefined4)((ulong)*(undefined8 *)(param_1 + 0x8c) >> 0x20);
    uStack_8c = (undefined4)*(undefined8 *)(param_1 + 0x84);
    uStack_88 = (undefined4)((ulong)*(undefined8 *)(param_1 + 0x84) >> 0x20);
    local_7c = (undefined4)*(undefined8 *)(param_1 + 0x94);
    uStack_78 = (undefined4)((ulong)*(undefined8 *)(param_1 + 0x94) >> 0x20);
    if ((*(byte *)(lVar7 + 8) & 1) != 0) {
      uVar10 = *(uint *)(lVar14 + 0xc) & 0xf;
      if (uVar10 == 0) {
        if (iStack_b4 != 1) goto LAB_00116260;
      }
      else if (iStack_b4 != *(int *)(lVar14 + (ulong)(uVar10 - 1) * 4 + 0x18)) goto LAB_00116260;
      bStack_bf = bStack_bf ^ 1;
      bStack_be = bStack_be ^ 1;
    }
LAB_00116260:
    iVar13 = 0;
    iVar15 = 0;
    iVar16 = 0;
    uVar10 = *(uint *)(lVar8 + 0xc);
    local_b0 = lVar7;
    lStack_a8 = lVar14;
    local_a0 = lVar8;
    do {
      if ((uVar10 & 0xf) < 4) {
        if (0 < iVar16) goto LAB_00116320;
      }
      else if (*(int *)(lVar8 + (ulong)((uVar10 & 0xf) - 4) * 4 + 0x18) <= iVar16)
      goto LAB_00116320;
      iVar11 = 0;
      local_98 = CONCAT44(iVar15,iVar13);
      uVar10 = *(uint *)(lVar8 + 0xc);
      uVar1 = uVar10 & 0xf;
      if (uVar1 < 3) goto LAB_001162d8;
      while (iVar11 < *(int *)(lVar8 + (ulong)(uVar1 - 3) * 4 + 0x18)) {
        while( true ) {
          do_gemm(&local_c0);
          iVar11 = iVar11 + 1;
          local_90 = local_90 + iVar3;
          local_98 = CONCAT44(local_98._4_4_ + iVar5,(int)local_98 + iVar4);
          uVar10 = *(uint *)(lVar8 + 0xc);
          uVar1 = uVar10 & 0xf;
          if (2 < uVar1) break;
LAB_001162d8:
          if (0 < iVar11) goto LAB_00116274;
        }
      }
LAB_00116274:
      iVar13 = iVar13 + iVar12;
      iVar15 = iVar15 + iVar6;
      iVar16 = iVar16 + 1;
    } while( true );
  }
LAB_0011638c:
  if (*(long *)(lVar2 + 0x28) == local_68) {
    return;
  }
LAB_001164cc:
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail(uVar9);
LAB_00116320:
  iVar4 = eai_op_layer_util_get_input_count(param_1);
  if (2 < iVar4) {
    lVar7 = eai_op_layer_util_get_input(param_1,2);
    if (lVar7 == 0) {
      uVar9 = 1;
      if (*(long *)(lVar2 + 0x28) == local_68) {
        return;
      }
    }
    else {
      local_d0 = PTR_vector_add_vector_offset_f32_f32_0012cbc8;
      puStack_c8 = PTR_vector_add_scalar_offset_f32_f32_0012cbd0;
      uVar9 = elem_op_core(lVar8,lVar7,lVar8,&local_d0);
      if (*(long *)(lVar2 + 0x28) == local_68) {
        return;
      }
    }
    goto LAB_001164cc;
  }
  uVar9 = 0;
  goto LAB_0011638c;
}



/* ------------------------------------------------------------------
 * Function: matmul_forward
 * Address : 0011650c
 * Size    : 0x190 bytes
 * ------------------------------------------------------------------ */

void matmul_forward(long param_1)

{
  byte bVar1;
  long lVar2;
  int iVar3;
  long lVar4;
  long lVar5;
  undefined8 uVar6;
  long lVar7;
  undefined *local_b0;
  undefined *puStack_a8;
  byte local_a0;
  byte bStack_9f;
  byte bStack_9e;
  undefined1 uStack_9d;
  undefined4 uStack_9c;
  undefined4 uStack_98;
  undefined4 uStack_94;
  long local_90;
  long lStack_88;
  long local_80;
  undefined8 uStack_78;
  undefined4 local_70;
  undefined4 uStack_6c;
  undefined4 uStack_68;
  undefined4 uStack_64;
  undefined4 uStack_60;
  undefined4 local_5c;
  undefined4 uStack_58;
  undefined4 uStack_54;
  long local_48;
  
  lVar2 = tpidr_el0;
  local_48 = *(long *)(lVar2 + 0x28);
  lVar4 = eai_op_layer_util_get_input(param_1,0);
  lVar7 = *(long *)(param_1 + 0x70);
  lVar5 = eai_op_layer_util_get_output(param_1,0);
  uVar6 = 1;
  if ((((lVar4 != 0) && (lVar7 != 0)) && (lVar5 != 0)) &&
     (((*(byte *)(lVar4 + 0xc) & 0xf) != 0 && ((*(byte *)(lVar7 + 0xc) & 0xf) != 0)))) {
    bVar1 = *(byte *)(param_1 + 8);
    uStack_94 = *(undefined4 *)(param_1 + 0x80);
    uStack_54 = 0;
    local_a0 = bVar1 >> 1 & 1;
    uStack_64 = (undefined4)*(undefined8 *)(param_1 + 0x8c);
    uStack_60 = (undefined4)((ulong)*(undefined8 *)(param_1 + 0x8c) >> 0x20);
    uStack_6c = (undefined4)*(undefined8 *)(param_1 + 0x84);
    uStack_68 = (undefined4)((ulong)*(undefined8 *)(param_1 + 0x84) >> 0x20);
    bStack_9f = bVar1 >> 2 & 1;
    local_5c = (undefined4)*(undefined8 *)(param_1 + 0x94);
    uStack_58 = (undefined4)((ulong)*(undefined8 *)(param_1 + 0x94) >> 0x20);
    bStack_9e = bVar1 >> 3 & 1;
    uStack_9d = 0;
    uStack_9c = (undefined4)*(undefined8 *)(param_1 + 0x78);
    uStack_98 = (undefined4)((ulong)*(undefined8 *)(param_1 + 0x78) >> 0x20);
    if (*(int *)(lVar4 + 0x14) == 0x23410) {
      local_a0 = local_a0 ^ 1;
      bStack_9f = bStack_9f ^ 1;
      bStack_9e = bStack_9e ^ 1;
    }
    uStack_78 = 0;
    local_70 = 0;
    local_90 = lVar4;
    lStack_88 = lVar7;
    local_80 = lVar5;
    do_gemm(&local_a0);
    iVar3 = eai_op_layer_util_get_input_count(param_1);
    uVar6 = 0;
    if (2 < iVar3) {
      lVar4 = eai_op_layer_util_get_input(param_1,2);
      if (lVar4 == 0) {
        uVar6 = 1;
        if (*(long *)(lVar2 + 0x28) == local_48) {
          return;
        }
        goto LAB_00116698;
      }
      local_b0 = PTR_vector_add_vector_offset_f32_f32_0012cbc8;
      puStack_a8 = PTR_vector_add_scalar_offset_f32_f32_0012cbd0;
      uVar6 = elem_op_core(lVar5,lVar4,lVar5,&local_b0);
    }
  }
  if (*(long *)(lVar2 + 0x28) == local_48) {
    return;
  }
LAB_00116698:
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail(uVar6);
}



/* ------------------------------------------------------------------
 * Function: softmax_forward
 * Address : 001189fc
 * Size    : 0x6c bytes
 * ------------------------------------------------------------------ */

undefined8 softmax_forward(long param_1)

{
  long lVar1;
  long lVar2;
  undefined8 uVar3;
  
  lVar1 = eai_op_layer_util_get_input(param_1,0);
  lVar2 = eai_op_layer_util_get_output(param_1,0);
  uVar3 = 1;
  if (((param_1 != 0) && (lVar1 != 0)) && (lVar2 != 0)) {
    softmax_forward_base
              (lVar2,lVar1,*(undefined4 *)(param_1 + 0x70),*(undefined4 *)(param_1 + 0x74),0,
               param_1 + 0x78);
    uVar3 = 0;
  }
  return uVar3;
}



/* ------------------------------------------------------------------
 * Function: softmax_forward_base
 * Address : 0011d1ac
 * Size    : 0x24c bytes
 * ------------------------------------------------------------------ */

undefined8
softmax_forward_base
          (long *param_1,long *param_2,int param_3,int param_4,int param_5,undefined8 *param_6)

{
  int iVar1;
  code *pcVar2;
  int iVar3;
  int iVar4;
  int iVar5;
  int iVar6;
  int iVar7;
  undefined8 uVar8;
  ulong uVar9;
  ulong uVar10;
  undefined8 uVar11;
  long lVar12;
  long *plVar13;
  long lVar14;
  ulong uVar15;
  long lVar16;
  
  iVar1 = (int)param_1[2];
  pcVar2 = softmax;
  if (param_5 != 0) {
    pcVar2 = logsoftmax;
  }
  if (param_4 <= iVar1) {
    iVar1 = 0;
  }
  lVar16 = param_1[6];
  iVar5 = tensor_size(param_2);
  iVar6 = *(int *)(*param_2 + 0xc);
  plVar13 = param_2 + 3;
  lVar14 = *plVar13;
  uVar15 = (ulong)*(byte *)(*param_1 + 8);
  uVar9 = *(ulong *)(*param_2 + 4);
  if ((iVar6 != 1) || (uVar10 = uVar9 >> 0x20 & 0xff, (uint)uVar10 != (uint)*(byte *)(*param_1 + 8))
     ) {
    q_vector_mul_scalar_inplace_f((float)iVar6,param_2[6],uVar9 >> 0x28);
    uVar10 = uVar15;
  }
  iVar6 = dims_size(param_3,plVar13);
  iVar7 = dims_size((*(uint *)((long)param_2 + 0xc) & 0xf) - param_3,
                    (int *)((long)plVar13 + (long)param_3 * 4));
  if (0 < iVar6) {
    iVar4 = 0;
    if ((int)lVar14 != 0) {
      iVar4 = iVar5 / (int)lVar14;
    }
    lVar14 = 0;
    iVar5 = 0;
    lVar12 = param_2[6];
    uVar11 = *param_6;
    iVar3 = (*(uint *)(*param_2 + 4) & 0xf) * iVar7;
    lVar16 = lVar16 + iVar4 * iVar1;
    uVar8 = param_6[2];
    iVar1 = iVar3 * 2;
    do {
      (*pcVar2)(lVar16 + lVar14,lVar12 + lVar14,uVar10,uVar15,iVar7,uVar11,uVar8);
      iVar5 = iVar5 + 2;
      lVar14 = lVar14 + iVar1;
    } while (iVar5 < iVar6);
    if (1 < iVar6) {
      lVar12 = param_2[6];
      iVar5 = 1;
      lVar14 = (long)iVar3;
      do {
        (*pcVar2)(lVar16 + lVar14,lVar12 + lVar14,uVar10,uVar15,iVar7,uVar11,uVar8);
        iVar5 = iVar5 + 2;
        lVar14 = lVar14 + iVar1;
      } while (iVar5 < iVar6);
    }
  }
  *(int *)(param_1 + 2) = (int)param_1[2] + (int)*plVar13;
  return 0;
}



/* ------------------------------------------------------------------
 * Function: gru_init
 * Address : 00127748
 * Size    : 0xc bytes
 * ------------------------------------------------------------------ */

void gru_init(long param_1)

{
  *(undefined4 *)(param_1 + 0x7c) = 0;
  eai_op_layer_util_init();
  return;
}



/* ------------------------------------------------------------------
 * Function: lstm_init
 * Address : 00127e4c
 * Size    : 0xe8 bytes
 * ------------------------------------------------------------------ */

undefined8 lstm_init(long param_1)

{
  long lVar1;
  long lVar2;
  long lVar3;
  long lVar4;
  long lVar5;
  long lVar6;
  long lVar7;
  undefined8 uVar8;
  
  lVar1 = eai_op_layer_util_get_input(param_1,0);
  lVar2 = eai_op_layer_util_get_input(param_1,1);
  lVar3 = eai_op_layer_util_get_input(param_1,2);
  lVar4 = eai_op_layer_util_get_input(param_1,8);
  lVar5 = eai_op_layer_util_get_input(param_1,9);
  lVar6 = eai_op_layer_util_get_input(param_1,10);
  lVar7 = eai_op_layer_util_get_output(param_1,0);
  if ((((lVar1 != 0) && (lVar2 != 0)) && (lVar3 != 0)) &&
     (((lVar4 != 0 && (lVar5 != 0)) && ((lVar6 != 0 && (lVar7 != 0)))))) {
    *(undefined4 *)(param_1 + 0x7c) = 0;
    uVar8 = eai_op_layer_util_init(param_1);
    return uVar8;
  }
  return 1;
}



