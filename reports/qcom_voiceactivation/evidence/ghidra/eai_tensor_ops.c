/*
 * Ghidra headless decompilation
 * program   : libeai_sym.so
 * executable: /tmp/libeai_sym.so
 * language  : AARCH64:LE:64:v8A
 * imagebase : 00100000
 * regex     : get_compiler_version|get_alignment_requriement|^tensor_size$|^dims_size$|tensor_copy_data|layer_load_by_loader|network_forward_from|eai_execute_internal|^do_apply$|gemm_load|gemm_init|^relu_load$|no_op_load|lstm_load|lstm_init|softmax_load|softmax_init|gemm_forward|supergemm_forward|^do_get_property$|^do_set_property$
 * max funcs : 40
 * note      : pseudocode from a (possibly stripped) binary is approximate;
 *             indirect calls and register-indirect JNI tables are lost.
 */

/* ------------------------------------------------------------------
 * Function: network_forward_from
 * Address : 0010b4b0
 * Size    : 0x210 bytes
 * ------------------------------------------------------------------ */

int network_forward_from(long param_1,undefined8 param_2,long param_3,int param_4)

{
  ulong *puVar1;
  long *plVar2;
  int iVar3;
  uint uVar4;
  int iVar5;
  uint *puVar6;
  ulong uVar7;
  int iVar8;
  int *piVar9;
  long lVar10;
  long lVar11;
  ulong *puVar12;
  long lVar13;
  ulong uVar14;
  ulong uVar15;
  
  iVar5 = 6;
  if ((((param_3 != 0) && (param_4 != 0)) && (lVar10 = *(long *)(param_1 + 0x80), lVar10 != 0)) &&
     (lVar11 = *(long *)(param_1 + 0x88), lVar11 != 0)) {
    piVar9 = *(int **)(param_1 + 0xa8);
    iVar8 = param_4;
    do {
      plVar2 = (long *)(param_3 + (ulong)(uint)(param_4 - iVar8) * 0x20);
      uVar15 = (ulong)*(uint *)(plVar2 + 1);
      uVar14 = (ulong)*(uint *)(plVar2 + 3);
      lVar13 = plVar2[2];
      if (((*(uint *)(plVar2 + 1) == 0 || *(uint *)(plVar2 + 3) == 0) || *plVar2 == 0) ||
          lVar13 == 0) {
        return 6;
      }
      puVar12 = (ulong *)(*plVar2 + 8);
      do {
        if (*puVar12 == 0) {
          return 6;
        }
        if (*(uint *)(param_1 + 0x90) <= (uint)puVar12[-1]) {
          return 6;
        }
        uVar7 = puVar12[1];
        uVar4 = tensor_size(*(undefined8 *)(lVar10 + (ulong)(uint)puVar12[-1] * 8));
        if ((uint)uVar7 < uVar4) {
          return 6;
        }
        uVar7 = *puVar12;
        if ((uVar7 & 7) != 0) {
          return 0xe;
        }
        puVar1 = puVar12 + -1;
        puVar12 = puVar12 + 3;
        uVar15 = uVar15 - 1;
        *(ulong *)(*(long *)(lVar10 + (ulong)(uint)*puVar1 * 8) + 0x30) = uVar7;
      } while (uVar15 != 0);
      puVar12 = (ulong *)(lVar13 + 8);
      do {
        if (*puVar12 == 0) {
          return 6;
        }
        if (*(uint *)(param_1 + 0x94) <= (uint)puVar12[-1]) {
          return 6;
        }
        uVar15 = puVar12[1];
        uVar4 = tensor_size(*(undefined8 *)(lVar11 + (ulong)(uint)puVar12[-1] * 8));
        if ((uint)uVar15 < uVar4) {
          return 6;
        }
        uVar15 = *puVar12;
        if ((uVar15 & 7) != 0) {
          return 0xe;
        }
        puVar1 = puVar12 + -1;
        puVar12 = puVar12 + 3;
        uVar14 = uVar14 - 1;
        *(ulong *)(*(long *)(lVar11 + (ulong)(uint)*puVar1 * 8) + 0x30) = uVar15;
      } while (uVar14 != 0);
      iVar3 = *piVar9;
      *(undefined4 *)(param_1 + 0xb0) = 0;
      if (iVar3 < 1) {
        iVar5 = 1;
      }
      else {
        iVar5 = 0;
        do {
          puVar6 = *(uint **)(piVar9 + (long)iVar5 * 2 + 2);
          if ((*(byte *)((long)puVar6 + 9) >> 1 & 1) == 0) {
            iVar5 = (*(code *)(&PTR_conv_forward_0012c008)[*puVar6])();
            if (iVar5 != 0) {
              if (iVar5 != 2) {
                __android_log_print(6,&DAT_00101ae3,"Unexpected result:%d from layer[%d]",iVar5,
                                    *(undefined4 *)(param_1 + 0xb0));
                return 1;
              }
              goto LAB_0010b64c;
            }
            iVar5 = *(int *)(param_1 + 0xb0);
          }
          iVar5 = iVar5 + 1;
          *(int *)(param_1 + 0xb0) = iVar5;
        } while (iVar5 < iVar3);
        iVar5 = 0;
      }
LAB_0010b64c:
      iVar8 = iVar8 + -1;
    } while (iVar8 != 0);
    *(int *)(param_1 + 0xd8) = *(int *)(param_1 + 0xd8) + 1;
  }
  return iVar5;
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
 * Function: dims_size
 * Address : 0010bad8
 * Size    : 0x94 bytes
 * ------------------------------------------------------------------ */

int dims_size(uint param_1,long param_2)

{
  undefined8 *puVar1;
  undefined8 *puVar2;
  undefined8 *puVar3;
  undefined1 auVar4 [16];
  ulong uVar5;
  long lVar6;
  ulong uVar7;
  undefined8 *puVar8;
  int *piVar9;
  ulong uVar10;
  int iVar11;
  int iVar12;
  int iVar13;
  int iVar14;
  int iVar15;
  int iVar17;
  int iVar18;
  int iVar19;
  undefined1 auVar16 [16];
  undefined8 uVar20;
  
  if ((int)param_1 < 1) {
    return 1;
  }
  uVar5 = (ulong)param_1;
  if (param_1 < 8) {
    uVar7 = 0;
    iVar11 = 1;
  }
  else {
    uVar7 = uVar5 & 0xfffffff8;
    puVar8 = (undefined8 *)(param_2 + 0x10);
    iVar11 = 1;
    iVar12 = 1;
    iVar13 = 1;
    iVar14 = 1;
    iVar19 = 1;
    iVar15 = 1;
    iVar17 = 1;
    iVar18 = 1;
    uVar10 = uVar7;
    do {
      puVar1 = puVar8 + -2;
      puVar2 = puVar8 + -1;
      puVar3 = puVar8 + 1;
      uVar20 = *puVar8;
      puVar8 = puVar8 + 4;
      uVar10 = uVar10 - 8;
      iVar11 = (int)*puVar1 * iVar11;
      iVar12 = (int)((ulong)*puVar1 >> 0x20) * iVar12;
      iVar13 = (int)*puVar2 * iVar13;
      iVar14 = (int)((ulong)*puVar2 >> 0x20) * iVar14;
      iVar15 = (int)uVar20 * iVar15;
      iVar17 = (int)((ulong)uVar20 >> 0x20) * iVar17;
      iVar18 = (int)*puVar3 * iVar18;
      iVar19 = (int)((ulong)*puVar3 >> 0x20) * iVar19;
    } while (uVar10 != 0);
    iVar15 = iVar15 * iVar11;
    iVar17 = iVar17 * iVar12;
    auVar16._4_4_ = iVar17;
    auVar16._0_4_ = iVar15;
    auVar16._8_4_ = iVar18 * iVar13;
    auVar16._12_4_ = iVar19 * iVar14;
    auVar4._4_4_ = iVar17;
    auVar4._0_4_ = iVar15;
    auVar4._8_4_ = iVar18 * iVar13;
    auVar4._12_4_ = iVar19 * iVar14;
    auVar16 = NEON_ext(auVar16,auVar4,8,1);
    iVar11 = iVar15 * auVar16._0_4_ * iVar17 * auVar16._4_4_;
    if (uVar7 == uVar5) {
      return iVar11;
    }
  }
  lVar6 = uVar5 - uVar7;
  piVar9 = (int *)(param_2 + uVar7 * 4);
  do {
    lVar6 = lVar6 + -1;
    iVar11 = *piVar9 * iVar11;
    piVar9 = piVar9 + 1;
  } while (lVar6 != 0);
  return iVar11;
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
 * Function: do_apply
 * Address : 0010cbdc
 * Size    : 0x254 bytes
 * ------------------------------------------------------------------ */

int do_apply(long param_1)

{
  int iVar1;
  long lVar2;
  void *__s;
  char *pcVar3;
  uint uVar4;
  uint uVar5;
  
  if (param_1 == 0) {
    return 4;
  }
  uVar4 = *(uint *)(param_1 + 0x98);
  uVar5 = uVar4 & 1;
  if ((*(long *)(param_1 + 0x130) == 0) && (*(int *)(param_1 + 0x128) != 0)) {
    __s = (void *)allocate_memory(*(int *)(param_1 + 0x128),*(undefined4 *)(param_1 + 0x124),
                                  param_1 + (ulong)uVar5 * 0x14 + 0xf8);
    *(void **)(param_1 + 0x130) = __s;
    if (__s == (void *)0x0) {
      pcVar3 = "Memory allocation for persistent_buffer failed.";
      goto LAB_0010ce0c;
    }
    memset(__s,0,(ulong)*(uint *)(param_1 + 0x128));
    __android_log_print(3,&DAT_00101ae3,"Persistent buffer created: %p, %d\n",
                        *(undefined8 *)(param_1 + 0x130),*(undefined4 *)(param_1 + 0x128));
    uVar4 = *(uint *)(param_1 + 0x98);
  }
  if ((uVar4 >> 0x1e & 1) == 0) {
    if (*(long *)(param_1 + 0x140) == 0) {
      return 0xd;
    }
    iVar1 = network_assign_buffer(param_1);
  }
  else {
    *(uint *)(param_1 + 0x98) = uVar4 & 0xbfffffff;
    iVar1 = network_assign_buffer(param_1);
  }
  if (iVar1 != 0) {
    __android_log_print(6,&DAT_00101ae3,"Activation Tensor buffer assignment failed.");
    return iVar1;
  }
  if ((*(uint *)(param_1 + 0x160) != 0) && (*(long *)(param_1 + 0x170) == 0)) {
    lVar2 = allocate_memory((ulong)*(uint *)(param_1 + 0x160) << 2,*(undefined4 *)(param_1 + 0x124),
                            param_1 + (ulong)uVar5 * 0x14 + 0xf8);
    *(long *)(param_1 + 0x170) = lVar2;
    if (lVar2 == 0) {
      pcVar3 = "Memory allocation for layers_gemm_scratch_buffer_a_mem failed.\n";
      goto LAB_0010ce0c;
    }
  }
  if ((*(uint *)(param_1 + 0x164) != 0) && (*(long *)(param_1 + 0x178) == 0)) {
    lVar2 = allocate_memory((ulong)*(uint *)(param_1 + 0x164) << 2,*(undefined4 *)(param_1 + 0x124),
                            param_1 + (ulong)uVar5 * 0x14 + 0xf8);
    *(long *)(param_1 + 0x178) = lVar2;
    if (lVar2 == 0) {
      pcVar3 = "Memory allocation for layers_gemm_scratch_buffer_b_mem failed.\n";
      goto LAB_0010ce0c;
    }
  }
  if ((*(uint *)(param_1 + 0x168) != 0) && (*(long *)(param_1 + 0x180) == 0)) {
    lVar2 = allocate_memory((ulong)*(uint *)(param_1 + 0x168) << 2,*(undefined4 *)(param_1 + 0x124),
                            param_1 + (ulong)uVar5 * 0x14 + 0xf8);
    *(long *)(param_1 + 0x180) = lVar2;
    if (lVar2 == 0) {
      pcVar3 = "Memory allocation for layers_gemm_scratch_buffer_c_mem failed.\n";
LAB_0010ce0c:
      __android_log_print(6,&DAT_00101ae3,pcVar3);
      return 8;
    }
  }
  *(uint *)(param_1 + 0x98) = *(uint *)(param_1 + 0x98) | 0x80000000;
  return 0;
}



/* ------------------------------------------------------------------
 * Function: do_set_property
 * Address : 0010ce90
 * Size    : 0x224 bytes
 * ------------------------------------------------------------------ */

undefined4 do_set_property(long param_1,int param_2,uint *param_3)

{
  bool bVar1;
  uint uVar2;
  long lVar3;
  uint *puVar4;
  undefined4 uVar5;
  int *piVar6;
  
  if (param_1 == 0) {
    return 4;
  }
  if (param_3 == (uint *)0x0) {
    return 6;
  }
  uVar5 = 5;
  switch(param_2) {
  case 0:
    uVar2 = param_3[2];
    if (((uVar2 < *(uint *)(param_1 + 0x148) + *(int *)(param_1 + 0x138)) ||
        ((*(uint *)(param_1 + 0x98) >> 0x1d & 1) == 0)) || (lVar3 = *(long *)param_3, lVar3 == 0)) {
      uVar5 = 0xd;
    }
    else {
      uVar5 = 0;
      *(long *)(param_1 + 0x140) = lVar3;
      *(ulong *)(param_1 + 0x150) = (lVar3 + (ulong)uVar2) - (ulong)*(uint *)(param_1 + 0x148);
      *(uint *)(param_1 + 0x98) = *(uint *)(param_1 + 0x98) | 0x40000000;
    }
    break;
  case 1:
    uVar5 = 7;
    break;
  case 2:
    piVar6 = *(int **)(param_1 + 0xa8);
    if ((*param_3 & 1) == 0) {
      if ((*param_3 >> 1 & 1) != 0) {
        puVar4 = *(uint **)(piVar6 + (ulong)param_3[1] * 2 + 2);
        if ((*puVar4 | 2) == 10) {
          uVar2 = puVar4[2] | 0x4000;
          puVar4[2] = uVar2;
          if ((code *)(&PTR_conv_init_0012c258)[(ulong)*puVar4 * 3] != (code *)0x0) {
            (*(code *)(&PTR_conv_init_0012c258)[(ulong)*puVar4 * 3])(puVar4);
            uVar2 = puVar4[2];
          }
          puVar4[2] = uVar2 & 0xffffbfff;
          return 0;
        }
        __android_log_print(6,&DAT_00101ae3,"This individual layer to reset is not LSTM or GRU!\n");
        return 6;
      }
    }
    else {
      if (0 < *piVar6) {
        lVar3 = 1;
        do {
          puVar4 = *(uint **)(piVar6 + lVar3 * 2);
          uVar2 = puVar4[2] | 0x4000;
          puVar4[2] = uVar2;
          if ((code *)(&PTR_conv_init_0012c258)[(ulong)*puVar4 * 3] != (code *)0x0) {
            (*(code *)(&PTR_conv_init_0012c258)[(ulong)*puVar4 * 3])(puVar4);
            uVar2 = puVar4[2];
          }
          puVar4[2] = uVar2 & 0xffffbfff;
          bVar1 = lVar3 < *piVar6;
          lVar3 = lVar3 + 1;
        } while (bVar1);
      }
      if (*(void **)(param_1 + 0x130) != (void *)0x0) {
        memset(*(void **)(param_1 + 0x130),0,(ulong)*(uint *)(param_1 + 0x128));
      }
    }
  case 10:
code_r0x0010d070:
    uVar5 = 0;
    break;
  case 3:
  case 4:
  case 5:
  case 6:
  case 7:
  case 8:
  case 9:
  case 0xb:
  case 0xc:
    break;
  case 0xd:
    uVar5 = 0;
    *(undefined8 *)(param_1 + 0x9c) = *(undefined8 *)param_3;
    *(undefined4 *)(param_1 + 0xa4) = 3;
    break;
  default:
    if (param_2 != 0x40000066) {
      return 5;
    }
    goto code_r0x0010d070;
  }
  return uVar5;
}



/* ------------------------------------------------------------------
 * Function: do_get_property
 * Address : 0010d114
 * Size    : 0x4b4 bytes
 * ------------------------------------------------------------------ */

undefined4 do_get_property(long param_1,int param_2,uint *param_3)

{
  uint uVar1;
  bool bVar2;
  int iVar3;
  uint uVar4;
  uint uVar5;
  uint uVar6;
  uint *puVar7;
  undefined8 uVar8;
  uint uVar9;
  long *plVar10;
  undefined4 uVar11;
  ulong uVar12;
  
  if (param_3 == (uint *)0x0) {
    if (param_1 != 0) {
      return 6;
    }
    return 4;
  }
  iVar3 = handle_no_context_get_prop_cases(param_2,param_3);
  if (iVar3 == 0) {
    return 0;
  }
  if (param_1 == 0) {
    return 4;
  }
  if (0x40000063 < param_2) {
    if (param_2 == 0x40000064) {
      param_3[0] = 0;
      param_3[1] = 0;
      return 0;
    }
    if (param_2 != 0x40000065) {
      if (param_2 != 0x40000067) {
        return 5;
      }
      if (0x49 < *param_3) {
        puVar7 = *(uint **)(param_1 + 0xa8);
        param_3[0x48] = 0;
        param_3[0x49] = 0;
        param_3[2] = 0;
        param_3[3] = 0;
        param_3[0] = 0;
        param_3[1] = 0;
        param_3[6] = 0;
        param_3[7] = 0;
        param_3[4] = 0;
        param_3[5] = 0;
        param_3[10] = 0;
        param_3[0xb] = 0;
        param_3[8] = 0;
        param_3[9] = 0;
        param_3[0xe] = 0;
        param_3[0xf] = 0;
        param_3[0xc] = 0;
        param_3[0xd] = 0;
        param_3[0x12] = 0;
        param_3[0x13] = 0;
        param_3[0x10] = 0;
        param_3[0x11] = 0;
        param_3[0x16] = 0;
        param_3[0x17] = 0;
        param_3[0x14] = 0;
        param_3[0x15] = 0;
        param_3[0x1a] = 0;
        param_3[0x1b] = 0;
        param_3[0x18] = 0;
        param_3[0x19] = 0;
        param_3[0x1e] = 0;
        param_3[0x1f] = 0;
        param_3[0x1c] = 0;
        param_3[0x1d] = 0;
        param_3[0x22] = 0;
        param_3[0x23] = 0;
        param_3[0x20] = 0;
        param_3[0x21] = 0;
        param_3[0x26] = 0;
        param_3[0x27] = 0;
        param_3[0x24] = 0;
        param_3[0x25] = 0;
        param_3[0x2a] = 0;
        param_3[0x2b] = 0;
        param_3[0x28] = 0;
        param_3[0x29] = 0;
        param_3[0x2e] = 0;
        param_3[0x2f] = 0;
        param_3[0x2c] = 0;
        param_3[0x2d] = 0;
        param_3[0x32] = 0;
        param_3[0x33] = 0;
        param_3[0x30] = 0;
        param_3[0x31] = 0;
        param_3[0x36] = 0;
        param_3[0x37] = 0;
        param_3[0x34] = 0;
        param_3[0x35] = 0;
        param_3[0x3a] = 0;
        param_3[0x3b] = 0;
        param_3[0x38] = 0;
        param_3[0x39] = 0;
        param_3[0x3e] = 0;
        param_3[0x3f] = 0;
        param_3[0x3c] = 0;
        param_3[0x3d] = 0;
        param_3[0x42] = 0;
        param_3[0x43] = 0;
        param_3[0x40] = 0;
        param_3[0x41] = 0;
        param_3[0x46] = 0;
        param_3[0x47] = 0;
        param_3[0x44] = 0;
        param_3[0x45] = 0;
        if (*puVar7 == 0) {
          return 0;
        }
        uVar12 = 1;
        do {
          param_3[(ulong)**(uint **)(puVar7 + uVar12 * 2) + 1] =
               param_3[(ulong)**(uint **)(puVar7 + uVar12 * 2) + 1] + 1;
          bVar2 = uVar12 < *puVar7;
          uVar12 = uVar12 + 1;
        } while (bVar2);
        return 0;
      }
      __android_log_print(6,&DAT_00101ae3,"EAI_LAYER_TYPE_NUM_LAYERS mismatch.\n");
      return 6;
    }
    if ((*param_3 == 0) && (param_3[1] == 0)) {
      *(undefined4 *)(param_1 + 0x158) = 0;
      param_3[3] = *(int *)(param_1 + 0x148) + *(int *)(param_1 + 0x138);
      param_3[9] = *(uint *)(param_1 + 0x138);
      param_3[10] = *(uint *)(param_1 + 0x148);
      param_3[4] = *(int *)(param_1 + 0x158) + *(int *)(param_1 + 0x128);
      param_3[0xb] = *(uint *)(param_1 + 0x128);
      param_3[0xc] = *(uint *)(param_1 + 0x158);
      uVar5 = *(uint *)(param_1 + 0xf8);
      param_3[5] = uVar5;
      uVar6 = *(uint *)(param_1 + 0xfc);
      param_3[0xd] = 0;
      param_3[7] = uVar6;
      uVar4 = *(uint *)(param_1 + 0x10c);
      param_3[6] = uVar4;
      uVar9 = *(uint *)(param_1 + 0x110);
      param_3[0xe] = 0;
      param_3[8] = uVar9;
      uVar1 = *(uint *)(param_1 + 0x98);
      param_3[2] = *(uint *)(param_1 + 0x15c);
      if ((uVar1 & 1) != 0) {
        param_3[0xe] = *(int *)(param_1 + 0x10c) - *(int *)(param_1 + 0x128);
        uVar4 = *(int *)(param_1 + 0x158) + uVar4;
LAB_0010d5b0:
        param_3[6] = uVar4;
        param_3[8] = *(int *)(param_1 + 0x158) + uVar9;
        return 0;
      }
      param_3[0xd] = *(int *)(param_1 + 0xf8) - *(int *)(param_1 + 0x128);
      uVar5 = *(int *)(param_1 + 0x158) + uVar5;
LAB_0010d57c:
      param_3[5] = uVar5;
      param_3[7] = *(int *)(param_1 + 0x158) + uVar6;
      return 0;
    }
    goto LAB_0010d4b4;
  }
  uVar11 = 7;
  switch(param_2) {
  case 0:
    uVar11 = 0;
    param_3[2] = *(int *)(param_1 + 0x148) + *(int *)(param_1 + 0x138);
    break;
  default:
    return 5;
  case 3:
    plVar10 = *(long **)(*(long *)(param_1 + (ulong)param_3[1] * 8 + 0x80) + (ulong)*param_3 * 8);
    if ((plVar10 != (long *)0x0) && (*plVar10 != 0)) {
      memscpy(param_3 + 6,0x14,plVar10 + 3,0x14);
      param_3[5] = *(uint *)((long)plVar10 + 0xc) & 0xf;
      param_3[4] = *(uint *)(*plVar10 + 4) >> 4 & 0xff;
      uVar5 = tensor_size(plVar10);
      param_3[0x10] = uVar5;
      *(long *)(param_3 + 2) = plVar10[6];
      if (param_3[5] == 0) {
        return 0;
      }
      uVar12 = 0;
      do {
        uVar4 = tensor_get_stride(plVar10,uVar12 & 0xffffffff);
        uVar5 = param_3[5];
        param_3[uVar12 + 0xb] = uVar4;
        uVar12 = uVar12 + 1;
      } while (uVar12 < uVar5);
      return 0;
    }
    goto LAB_0010d4b4;
  case 4:
    uVar11 = 0;
    param_3[1] = *(uint *)(param_1 + (ulong)*param_3 * 4 + 0x90);
    break;
  case 5:
    if (*(long *)(*(long *)(param_1 + (ulong)param_3[1] * 8 + 0x80) + (ulong)*param_3 * 8) != 0) {
      uVar5 = tensor_size();
      param_3[2] = uVar5;
      return 0;
    }
    goto LAB_0010d4b4;
  case 7:
    uVar5 = get_alignment_requriement(*(undefined8 *)(param_1 + 8));
    uVar11 = 0;
    *param_3 = uVar5;
    break;
  case 8:
    if ((*param_3 == 0) && (param_3[1] == 0)) {
      *(undefined4 *)(param_1 + 0x158) = 0;
      param_3[3] = *(int *)(param_1 + 0x148) + *(int *)(param_1 + 0x138);
      param_3[4] = *(int *)(param_1 + 0x158) + *(int *)(param_1 + 0x128);
      uVar5 = *(uint *)(param_1 + 0xf8);
      param_3[5] = uVar5;
      uVar6 = *(uint *)(param_1 + 0xfc);
      param_3[7] = uVar6;
      uVar4 = *(uint *)(param_1 + 0x10c);
      param_3[6] = uVar4;
      uVar9 = *(uint *)(param_1 + 0x110);
      param_3[8] = uVar9;
      uVar1 = *(uint *)(param_1 + 0x98);
      param_3[2] = *(uint *)(param_1 + 0x15c);
      if ((uVar1 & 1) != 0) {
        uVar4 = *(int *)(param_1 + 0x158) + uVar4;
        goto LAB_0010d5b0;
      }
      uVar5 = *(int *)(param_1 + 0x158) + uVar5;
      goto LAB_0010d57c;
    }
LAB_0010d4b4:
    uVar11 = 6;
    break;
  case 9:
  case 0xb:
    break;
  case 0xc:
    uVar11 = 0;
    *param_3 = *(uint *)(*(long *)(param_1 + 8) + 0xc) >> 2 & 1;
    param_3[1] = (uint)*(byte *)(*(long *)(param_1 + 8) + 0x20);
    param_3[2] = (uint)*(byte *)(*(long *)(param_1 + 8) + 0x21);
    param_3[3] = *(uint *)(*(long *)(param_1 + 8) + 0x10);
    param_3[4] = **(uint **)(param_1 + 0xa8);
    param_3[5] = *(uint *)(*(long *)(param_1 + 8) + 0xc) & 1;
    param_3[6] = *(uint *)(*(long *)(param_1 + 8) + 0xc) >> 1 & 1;
    param_3[7] = *(uint *)(*(long *)(param_1 + 8) + 0xc) >> 3 & 1;
    param_3[8] = 1;
    break;
  case 0xd:
    uVar11 = 0;
    uVar8 = *(undefined8 *)(param_1 + 0x9c);
    param_3[2] = *(uint *)(param_1 + 0xa4);
    *(undefined8 *)param_3 = uVar8;
  }
  return uVar11;
}



/* ------------------------------------------------------------------
 * Function: layer_load_by_loader
 * Address : 0010ecac
 * Size    : 0x5c bytes
 * ------------------------------------------------------------------ */

void layer_load_by_loader(undefined8 param_1,code *param_2,undefined8 param_3)

{
  long lVar1;
  undefined8 local_30;
  long local_28;
  
  lVar1 = tpidr_el0;
  local_28 = *(long *)(lVar1 + 0x28);
  local_30 = param_1;
  (*param_2)(&local_30,param_3);
  if (*(long *)(lVar1 + 0x28) == local_28) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: get_compiler_version
 * Address : 0010ed08
 * Size    : 0x18 bytes
 * ------------------------------------------------------------------ */

void get_compiler_version(long param_1,uint *param_2,uint *param_3)

{
  *param_2 = (uint)*(byte *)(param_1 + 10);
  *param_3 = (uint)*(byte *)(param_1 + 8);
  return;
}



/* ------------------------------------------------------------------
 * Function: get_alignment_requriement
 * Address : 0010ed38
 * Size    : 0x64 bytes
 * ------------------------------------------------------------------ */

undefined4 get_alignment_requriement(long param_1)

{
  byte bVar1;
  uint uVar2;
  undefined4 uVar3;
  
  bVar1 = *(byte *)(param_1 + 0x21);
  if (bVar1 - 1 < 2) {
    return 0x100;
  }
  if (bVar1 != 0) {
    if (bVar1 == 3) {
      uVar3 = 0x100;
      uVar2 = *(uint *)(param_1 + 8) >> 0x10 & 0xff;
      if (1 < uVar2) {
        uVar3 = 0x100;
        if (0xc < (*(uint *)(param_1 + 8) & 0xff) || uVar2 != 2) {
          uVar3 = 0x10;
        }
        return uVar3;
      }
    }
    else {
      uVar3 = 0x10;
    }
    return uVar3;
  }
  return 8;
}



/* ------------------------------------------------------------------
 * Function: no_op_load2
 * Address : 001100c4
 * Size    : 0x24 bytes
 * ------------------------------------------------------------------ */

undefined8 no_op_load2(long param_1)

{
  if (param_1 != 0) {
    *(code **)(param_1 + 0x20) = no_op_forward;
    return 0;
  }
  return 1;
}



/* ------------------------------------------------------------------
 * Function: supergemm_forward
 * Address : 00113d54
 * Size    : 0x3a4 bytes
 * ------------------------------------------------------------------ */

void supergemm_forward(long param_1)

{
  long *plVar1;
  ushort uVar2;
  long lVar3;
  ulong uVar4;
  long *plVar5;
  long *plVar6;
  long lVar7;
  undefined8 uVar8;
  long *plVar9;
  undefined8 uVar10;
  uint uVar11;
  int iVar12;
  uint uVar13;
  uint uVar14;
  long local_b0;
  int iStack_a8;
  int iStack_a4;
  long *local_a0;
  long *plStack_98;
  long *local_90;
  undefined8 uStack_88;
  long local_80;
  ulong uStack_78;
  int local_70;
  undefined4 uStack_6c;
  ulong local_68;
  long local_58;
  
  lVar3 = tpidr_el0;
  local_58 = *(long *)(lVar3 + 0x28);
  plVar5 = (long *)eai_op_layer_util_get_input(param_1,0);
  plVar6 = (long *)eai_op_layer_util_get_input(param_1,1);
  lVar7 = eai_op_layer_util_get_input(param_1,2);
  uVar8 = eai_op_layer_util_get_input(param_1,3);
  plVar9 = (long *)eai_op_layer_util_get_output(param_1,0);
  if (((plVar5 == (long *)0x0) || (plVar6 == (long *)0x0)) || (plVar9 == (long *)0x0)) {
    __android_log_print(6,&DAT_00101ae3,"invalid pointers: %p %p %p",plVar5,plVar6,plVar9);
    uVar10 = 1;
    goto LAB_00114038;
  }
  plVar1 = plVar5;
  if ((*(uint *)(plVar5 + 1) & 1) != 0) {
    plVar1 = plVar6;
    plVar6 = plVar5;
  }
  plVar5 = plVar9;
  if (((*(ulong *)(*plVar9 + 4) & 0xff0) != 0x10) &&
     (plVar5 = (long *)eai_op_layer_util_get_input(param_1,4), plVar5 == (long *)0x0)) {
    uVar10 = 1;
    goto LAB_00114038;
  }
  if (*(char *)(param_1 + 0x82) == '\0') {
    uVar14 = *(uint *)((long)plVar6 + 0xc) & 0xf;
    if ((*(byte *)((long)plVar6 + 0xb) >> 1 & 1) == 0) {
      if (uVar14 < 2) {
        uVar11 = 1;
        uVar4 = 1;
        if (uVar14 == 0) goto LAB_00113f50;
      }
      else {
        uVar11 = *(uint *)((long)plVar6 + (ulong)(uVar14 - 2) * 4 + 0x18);
        uVar4 = (ulong)uVar11;
      }
      local_b0 = uVar4 << 0x20;
      uVar14 = uVar14 - 1;
LAB_00113f7c:
      iStack_a4 = *(int *)((long)plVar6 + (ulong)uVar14 * 4 + 0x18);
      uVar14 = *(uint *)((long)plVar1 + 0xc) & 0xf;
      uVar13 = uVar14 - 1;
      if (uVar14 != 0 && uVar13 != 0) goto LAB_00113f98;
LAB_00113f64:
      iVar12 = 1;
      if ((iStack_a4 == 1) && (uVar14 != 0)) goto LAB_00113fac;
    }
    else {
      if (uVar14 == 0) {
LAB_00113f50:
        local_b0 = 0x100000000;
        uVar11 = 1;
        iStack_a4 = 1;
        uVar14 = *(uint *)((long)plVar1 + 0xc) & 0xf;
        uVar13 = uVar14 - 1;
        if (uVar14 == 0 || uVar13 == 0) goto LAB_00113f64;
      }
      else {
        uVar11 = *(uint *)((long)plVar6 + (ulong)(uVar14 - 1) * 4 + 0x18);
        local_b0 = (ulong)uVar11 << 0x20;
        if (uVar14 - 1 != 0) {
          uVar14 = uVar14 - 2;
          goto LAB_00113f7c;
        }
        iStack_a4 = 1;
        uVar14 = *(uint *)((long)plVar1 + 0xc) & 0xf;
        uVar13 = uVar14 - 1;
        if (uVar14 == 0 || uVar13 == 0) goto LAB_00113f64;
      }
LAB_00113f98:
      iVar12 = *(int *)((long)plVar1 + (ulong)(uVar14 - 2) * 4 + 0x18);
      if (iVar12 == iStack_a4) goto LAB_00113fac;
    }
  }
  else {
    uVar14 = *(uint *)((long)plVar6 + 0xc) & 0xf;
    if (uVar14 < 2) {
      uVar11 = 1;
      local_b0 = 0x100000000;
      if (uVar14 != 0) goto LAB_00113ecc;
      iStack_a4 = 1;
      uVar14 = *(uint *)((long)plVar1 + 0xc);
    }
    else {
      uVar11 = *(uint *)((long)plVar6 + (ulong)(uVar14 - 2) * 4 + 0x18);
      local_b0 = (ulong)uVar11 << 0x20;
LAB_00113ecc:
      iStack_a4 = *(int *)((long)plVar6 + (ulong)(uVar14 - 1) * 4 + 0x18);
      uVar14 = *(uint *)((long)plVar1 + 0xc);
    }
    uVar13 = (uVar14 & 0xf) - 2;
    if ((uVar14 & 0xf) < 2) {
      iVar12 = 1;
    }
    else {
LAB_00113fac:
      iVar12 = *(int *)((long)plVar1 + (ulong)uVar13 * 4 + 0x18);
    }
  }
  _iStack_a8 = CONCAT44(iStack_a4,iVar12);
  uStack_88 = 0;
  local_80 = (ulong)((*(uint *)(*plVar6 + 4) & 0xf) * iStack_a4) << 0x20;
  uStack_78 = CONCAT44(*(undefined4 *)(*plVar1 + 4),*(undefined4 *)(*plVar6 + 4)) & 0xf0000000f;
  local_68 = (ulong)uVar11;
  _local_70 = CONCAT44(1,(*(uint *)(*plVar1 + 4) & 0xf) * iStack_a4);
  local_a0 = plVar6;
  plStack_98 = plVar1;
  local_90 = plVar5;
  uVar10 = do_gemm_with_helpers(&local_b0,param_1 + 0x88);
  if (((int)uVar10 == 0) && (lVar7 != 0)) {
    uVar10 = gemm_add_bias(plVar5,lVar7);
  }
  if ((int)uVar10 == 0) {
    uVar2 = *(ushort *)(param_1 + 0x7c);
    lVar7 = param_1 + 0x70;
    if ((uVar2 & 0xc) != 0) {
      uVar10 = apply_bn(plVar5,uVar8,lVar7);
      if ((int)uVar10 != 0) goto LAB_00114038;
      uVar2 = *(ushort *)(param_1 + 0x7c);
    }
    if ((uVar2 & 0x1f0) != 0) {
      uVar10 = apply_activation(plVar5,uVar8,lVar7);
      if ((int)uVar10 != 0) goto LAB_00114038;
      uVar2 = *(ushort *)(param_1 + 0x7c);
    }
    if ((uVar2 & 0x600) == 0) {
      uVar10 = 0;
    }
    else {
      uVar10 = apply_quantization(plVar5,plVar9,lVar7,*(ulong *)(*plVar9 + 4) >> 4,
                                  *(undefined2 *)(*plVar9 + 0x10),0,*(ulong *)(*plVar5 + 4) >> 0x28)
      ;
    }
  }
LAB_00114038:
  if (*(long *)(lVar3 + 0x28) != local_58) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail(uVar10);
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: supergemm_load2
 * Address : 001140f8
 * Size    : 0x9c bytes
 * ------------------------------------------------------------------ */

bool supergemm_load2(long param_1)

{
  bool bVar1;
  int iVar2;
  long lVar3;
  long lVar4;
  
  lVar3 = eai_op_layer_util_get_input(param_1,0);
  bVar1 = true;
  lVar4 = eai_op_layer_util_get_input(param_1,1);
  if ((lVar3 != 0) && (lVar4 != 0)) {
    *(code **)(param_1 + 0x20) = supergemm_forward;
    if (*(char *)(param_1 + 8) < '\0') {
      return false;
    }
    iVar2 = get_gemm_helpers(lVar3,lVar4,param_1 + 0x88);
    bVar1 = iVar2 != 0;
  }
  return bVar1;
}



/* ------------------------------------------------------------------
 * Function: logsoftmax_load2
 * Address : 001153a0
 * Size    : 0x44 bytes
 * ------------------------------------------------------------------ */

undefined8 logsoftmax_load2(long param_1)

{
  if (param_1 != 0) {
    *(code **)(param_1 + 0x20) = logsoftmax_forward;
    softmax_vector_ops_assign(param_1 + 0x78);
    return 0;
  }
  return 1;
}



/* ------------------------------------------------------------------
 * Function: lstm_load2
 * Address : 00115fa0
 * Size    : 0xcc bytes
 * ------------------------------------------------------------------ */

undefined8 lstm_load2(long param_1)

{
  int iVar1;
  undefined8 uVar2;
  undefined8 uVar3;
  char *pcVar4;
  
  uVar2 = eai_op_layer_util_get_input(param_1,2);
  uVar3 = eai_op_layer_util_get_output(param_1,0);
  iVar1 = get_gemm_helpers(uVar2,uVar3,param_1 + 0x90);
  if (iVar1 == 0) {
    uVar2 = eai_op_layer_util_get_input(param_1,1);
    uVar3 = eai_op_layer_util_get_input(param_1,0);
    uVar2 = get_gemm_helpers(uVar2,uVar3,param_1 + 0xb0);
    if ((int)uVar2 == 0) {
      *(code **)(param_1 + 0x20) = lstm_forward_vanilla;
      return uVar2;
    }
    pcVar4 = "Failed to get gemm 2 helpers\n";
  }
  else {
    pcVar4 = "Failed to get gemm 1 helpers\n";
  }
  __android_log_print(6,&DAT_00101ae3,pcVar4);
  return 1;
}



/* ------------------------------------------------------------------
 * Function: softmax_load2
 * Address : 00118a68
 * Size    : 0x44 bytes
 * ------------------------------------------------------------------ */

undefined8 softmax_load2(long param_1)

{
  if (param_1 != 0) {
    *(code **)(param_1 + 0x20) = softmax_forward;
    softmax_vector_ops_assign(param_1 + 0x78);
    return 0;
  }
  return 1;
}



/* ------------------------------------------------------------------
 * Function: gemm_forward
 * Address : 001241e4
 * Size    : 0x354 bytes
 * ------------------------------------------------------------------ */

void gemm_forward(long param_1)

{
  long lVar1;
  long lVar2;
  long lVar3;
  int iVar4;
  uint uVar5;
  long lVar6;
  uint3 uVar7;
  bool bVar8;
  bool bVar9;
  int iVar10;
  long *plVar11;
  long *plVar12;
  long lVar13;
  long lVar14;
  undefined8 uVar15;
  uint uVar16;
  uint uVar17;
  undefined4 uVar18;
  uint uVar19;
  undefined8 local_a0;
  undefined8 uStack_98;
  long *local_90;
  long *plStack_88;
  long local_80;
  undefined8 uStack_78;
  long local_70;
  undefined8 uStack_68;
  undefined8 local_60;
  ulong local_58;
  long local_48;
  
  lVar6 = tpidr_el0;
  local_48 = *(long *)(lVar6 + 0x28);
  plVar11 = (long *)eai_op_layer_util_get_input(param_1,0);
  plVar12 = (long *)eai_op_layer_util_get_input(param_1,1);
  lVar13 = eai_op_layer_util_get_input(param_1,2);
  lVar14 = eai_op_layer_util_get_output(param_1,0);
  if (((plVar11 == (long *)0x0) || (plVar12 == (long *)0x0)) || (lVar14 == 0)) {
    __android_log_print(6,&DAT_00101ae3,"invalid pointers: %p %p %p",plVar11,plVar12,lVar14);
    uVar15 = 1;
    goto LAB_00124508;
  }
  plStack_88 = plVar11;
  local_90 = plVar12;
  if ((*(uint *)(plVar11 + 1) & 1) != 0) {
    plStack_88 = plVar12;
    local_90 = plVar11;
  }
  local_80 = lVar14;
  if (*(char *)(param_1 + 0x80) == '\0') {
    bVar8 = *(int *)(param_1 + 0x78) != 0;
    uVar16 = *(uint *)(param_1 + 8) >> 3 & 1;
    lVar1 = 0x1c;
    if (!bVar8) {
      lVar1 = 0x18;
    }
    lVar2 = 0x18;
    if (!bVar8) {
      lVar2 = 0x1c;
    }
    bVar9 = *(int *)(param_1 + 0x7c) != 0;
    lVar3 = 0x18;
    if (!bVar9) {
      lVar3 = 0x1c;
    }
    local_a0._0_2_ = CONCAT11(bVar9,bVar8);
    uVar7 = CONCAT12((char)uVar16,(undefined2)local_a0);
    uVar17 = *(uint *)((long)plVar11 + lVar1);
    local_a0 = CONCAT44(uVar17,(uint)uVar7);
    iVar4 = *(int *)((long)plVar11 + lVar2);
    iVar10 = *(int *)((long)plVar12 + lVar3);
    uStack_78 = 0;
    uStack_98 = CONCAT44(iVar4,iVar10);
    if (*(int *)((long)plVar11 + 0x14) == 0x34210) {
      bVar8 = !bVar8;
      local_a0 = CONCAT71(local_a0._1_7_,bVar8);
      if (*(int *)((long)plVar12 + 0x14) != 0x34210) goto LAB_00124350;
LAB_0012446c:
      bVar9 = !bVar9;
      local_a0._0_2_ = CONCAT11(bVar9,(undefined1)local_a0);
      if (*(int *)(lVar14 + 0x14) != 0x34210) goto LAB_0012435c;
LAB_00124480:
      uVar16 = uVar16 ^ 1;
      local_a0._0_3_ = CONCAT12((char)uVar16,(undefined2)local_a0);
      uVar19 = *(uint *)(*plVar11 + 4) & 0xf;
      if (bVar8) goto LAB_0012436c;
LAB_00124498:
      local_70 = (ulong)(uVar19 * iVar4) << 0x20;
      uVar5 = *(uint *)(*plVar11 + 4) & 0xf;
      uVar19 = *(uint *)(*plVar12 + 4) & 0xf;
      if (bVar9) goto LAB_00124394;
LAB_001244c0:
      uStack_68 = CONCAT44(uVar19 * iVar10,uVar5);
      uVar19 = *(uint *)(*plVar12 + 4) & 0xf;
    }
    else {
      if (*(int *)((long)plVar12 + 0x14) == 0x34210) goto LAB_0012446c;
LAB_00124350:
      if (*(int *)(lVar14 + 0x14) == 0x34210) goto LAB_00124480;
LAB_0012435c:
      uVar19 = *(uint *)(*plVar11 + 4) & 0xf;
      if (!bVar8) goto LAB_00124498;
LAB_0012436c:
      local_70 = (ulong)uVar19 << 0x20;
      uVar5 = (*(uint *)(*plVar11 + 4) & 0xf) * uVar17;
      uVar19 = *(uint *)(*plVar12 + 4) & 0xf;
      if (!bVar9) goto LAB_001244c0;
LAB_00124394:
      uStack_68 = CONCAT44(uVar19,uVar5);
      uVar19 = (*(uint *)(*plVar12 + 4) & 0xf) * iVar4;
    }
    if (uVar16 == 0) {
      uVar17 = 1;
    }
    else {
      iVar10 = 1;
    }
    local_60 = CONCAT44(iVar10,uVar19);
    local_58 = (ulong)uVar17;
    local_90 = plVar11;
    plStack_88 = plVar12;
    uVar15 = do_gemm_with_helpers(&local_a0,param_1 + 0x88);
    iVar10 = (int)uVar15;
  }
  else {
    uVar16 = *(uint *)((long)local_90 + 0xc) & 0xf;
    if (uVar16 < 2) {
      uVar17 = 1;
      iVar10 = 1;
      local_a0 = 0x100000000;
      if (uVar16 != 0) goto LAB_001243b8;
    }
    else {
      uVar17 = *(uint *)((long)local_90 + (ulong)(uVar16 - 2) * 4 + 0x18);
      local_a0 = (ulong)uVar17 << 0x20;
LAB_001243b8:
      iVar10 = *(int *)((long)local_90 + (ulong)(uVar16 - 1) * 4 + 0x18);
    }
    uVar16 = *(uint *)((long)plStack_88 + 0xc) & 0xf;
    uVar18 = 1;
    if (1 < uVar16) {
      uVar18 = *(undefined4 *)((long)plStack_88 + (ulong)(uVar16 - 2) * 4 + 0x18);
    }
    uStack_98 = CONCAT44(iVar10,uVar18);
    uStack_78 = 0;
    local_70 = (ulong)((*(uint *)(*local_90 + 4) & 0xf) * iVar10) << 0x20;
    uStack_68 = CONCAT44(*(undefined4 *)(*plStack_88 + 4),*(undefined4 *)(*local_90 + 4)) &
                0xf0000000f;
    local_60 = CONCAT44(1,(*(uint *)(*plStack_88 + 4) & 0xf) * iVar10);
    local_58 = (ulong)uVar17;
    uVar15 = do_gemm_with_helpers(&local_a0,param_1 + 0x88);
    iVar10 = (int)uVar15;
  }
  if ((iVar10 == 0) && (lVar13 != 0)) {
    uVar15 = gemm_add_bias(lVar14,lVar13);
  }
LAB_00124508:
  if (*(long *)(lVar6 + 0x28) != local_48) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail(uVar15);
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: gemm_load2
 * Address : 00124538
 * Size    : 0x90 bytes
 * ------------------------------------------------------------------ */

undefined8 gemm_load2(long param_1)

{
  int iVar1;
  long lVar2;
  long lVar3;
  
  lVar2 = eai_op_layer_util_get_input(param_1,0);
  lVar3 = eai_op_layer_util_get_input(param_1,1);
  if (((lVar2 != 0) && (lVar3 != 0)) &&
     (iVar1 = get_gemm_helpers(lVar2,lVar3,param_1 + 0x88), iVar1 == 0)) {
    *(code **)(param_1 + 0x20) = gemm_forward;
    return 0;
  }
  return 1;
}



/* ------------------------------------------------------------------
 * Function: gemm_init
 * Address : 00125b8c
 * Size    : 0xb4 bytes
 * ------------------------------------------------------------------ */

undefined4 gemm_init(undefined8 param_1)

{
  undefined4 uVar1;
  long lVar2;
  long lVar3;
  long lVar4;
  
  uVar1 = eai_op_layer_util_init();
  lVar2 = eai_op_layer_util_get_input(param_1,0);
  lVar3 = eai_op_layer_util_get_input(param_1,1);
  lVar4 = eai_op_layer_util_get_output(param_1,0);
  if ((((lVar4 == 0) || (lVar2 == 0)) || (lVar3 == 0)) ||
     (((*(uint *)(lVar2 + 0xc) & 0xf) != 2 || ((*(uint *)(lVar3 + 0xc) & 0xf) != 2)))) {
    __android_log_print(6,&DAT_00101ae3,"Invalid tensors/tensor dimensions: %p, %p.\n",lVar2,lVar3);
    uVar1 = 1;
  }
  return uVar1;
}



/* ------------------------------------------------------------------
 * Function: gemm_load
 * Address : 00125c40
 * Size    : 0xe8 bytes
 * ------------------------------------------------------------------ */

undefined8 gemm_load(long *param_1,undefined8 param_2)

{
  long lVar1;
  long lVar2;
  
  lVar2 = *param_1;
  lVar1 = eai_op_layer_util_load_base(param_1,gemm_init,0,0,param_2);
  if (lVar1 == 0) {
    return 1;
  }
  *(undefined2 *)(lVar1 + 4) = 0xa8;
  if (*(char *)(lVar2 + 0xe) == '\0') {
    *(undefined4 *)(lVar1 + 0x70) = *(undefined4 *)(lVar2 + 0x10);
    *(undefined4 *)(lVar1 + 0x74) = *(undefined4 *)(lVar2 + 0x14);
    *(undefined4 *)(lVar1 + 0x78) = *(undefined4 *)(lVar2 + 0x18);
    *(undefined4 *)(lVar1 + 0x7c) = *(undefined4 *)(lVar2 + 0x1c);
  }
  else {
    if (*(char *)(lVar2 + 0xe) != '\x01') {
      __android_log_print(6,&DAT_00101ae3,"invalid Gemm offline struct, layer ID: %u.\n",
                          *(undefined2 *)(lVar1 + 6));
      return 1;
    }
    *(undefined4 *)(lVar1 + 0x70) = *(undefined4 *)(lVar2 + 0x10);
    *(undefined4 *)(lVar1 + 0x74) = *(undefined4 *)(lVar2 + 0x14);
  }
  *(undefined1 *)(lVar1 + 0x80) = *(undefined1 *)(lVar2 + 0xe);
  return 0;
}



/* ------------------------------------------------------------------
 * Function: relu_load
 * Address : 00125e4c
 * Size    : 0xac bytes
 * ------------------------------------------------------------------ */

undefined8 relu_load(undefined8 param_1,undefined8 param_2)

{
  int iVar1;
  long lVar2;
  long lVar3;
  undefined8 uVar4;
  
  lVar2 = eai_op_layer_util_load_base(param_1,relu_init,0,0,param_2);
  if (lVar2 == 0) {
    return 1;
  }
  *(undefined2 *)(lVar2 + 4) = 0x70;
  lVar3 = eai_op_layer_util_get_input(lVar2,0);
  lVar2 = eai_op_layer_util_get_output(lVar2,0);
  uVar4 = 1;
  if ((lVar3 != 0) && (lVar2 != 0)) {
    iVar1 = tensor_is_same_dimension(lVar3);
    if (iVar1 == 0) {
      __android_log_print(6,&DAT_00101ae3,"Dimensions not same.\n");
      return 1;
    }
    uVar4 = 0;
  }
  return uVar4;
}



/* ------------------------------------------------------------------
 * Function: logsoftmax_init
 * Address : 00127af8
 * Size    : 0x1b0 bytes
 * ------------------------------------------------------------------ */

void logsoftmax_init(long param_1)

{
  long lVar1;
  long *plVar2;
  long *plVar3;
  undefined8 uVar4;
  ulong uVar5;
  int *piVar6;
  ulong uVar7;
  int local_50 [6];
  long local_38;
  
  lVar1 = tpidr_el0;
  local_38 = *(long *)(lVar1 + 0x28);
  plVar2 = (long *)eai_op_layer_util_get_input(param_1,0);
  plVar3 = (long *)eai_op_layer_util_get_output(param_1,0);
  if ((plVar2 == (long *)0x0) || (plVar3 == (long *)0x0)) {
    __android_log_print(6,&DAT_00101ae3,"Invalid tensors: %p, %p.\n",plVar2,plVar3);
    uVar4 = 1;
    if (*(long *)(lVar1 + 0x28) == local_38) {
      return;
    }
  }
  else if (((*(uint *)(*plVar2 + 4) >> 4 & 0xff) == 1) &&
          ((*(uint *)(*plVar3 + 4) >> 4 & 0xff) == 1)) {
    if ((*(int *)((long)plVar2 + 0x14) == 0x43210) && (*(int *)((long)plVar3 + 0x14) == 0x43210)) {
      if ((*(byte *)(plVar3 + 1) >> 2 & 1) != 0) {
        local_50[0] = 0;
        local_50[1] = 0;
        local_50[2] = 0;
        local_50[3] = 0;
        local_50[4] = 0;
        uVar5 = (ulong)*(uint *)((long)plVar2 + 0xc) & 0xf;
        if ((int)uVar5 != 0) {
          plVar2 = plVar2 + 3;
          piVar6 = local_50;
          uVar7 = uVar5;
          do {
            uVar7 = uVar7 - 1;
            *piVar6 = (int)*plVar2;
            plVar2 = (long *)((long)plVar2 + 4);
            piVar6 = piVar6 + 1;
          } while (uVar7 != 0);
        }
        if (*(int *)(param_1 + 0x74) != 1) {
          local_50[0] = *(int *)(param_1 + 0x74);
        }
        tensor_set_dimension(plVar3,uVar5,local_50);
      }
      uVar4 = eai_op_layer_util_init(param_1);
      if (*(long *)(lVar1 + 0x28) == local_38) {
        return;
      }
    }
    else {
      __android_log_print(6,&DAT_00101ae3,"Invalid dims_form for input or output\n");
      uVar4 = 1;
      if (*(long *)(lVar1 + 0x28) == local_38) {
        return;
      }
    }
  }
  else {
    __android_log_print(6,&DAT_00101ae3,"Invalid data type mix: input = %d, output = %d,\n");
    uVar4 = 1;
    if (*(long *)(lVar1 + 0x28) == local_38) {
      return;
    }
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail(uVar4);
}



/* ------------------------------------------------------------------
 * Function: logsoftmax_load
 * Address : 00127ca8
 * Size    : 0xfc bytes
 * ------------------------------------------------------------------ */

void logsoftmax_load(long *param_1,undefined8 param_2)

{
  long lVar1;
  long lVar2;
  undefined8 uVar3;
  long lVar4;
  long local_40;
  long local_38;
  
  lVar1 = tpidr_el0;
  local_38 = *(long *)(lVar1 + 0x28);
  lVar4 = *param_1;
  local_40 = lVar4;
  lVar2 = eai_op_layer_util_load_base(&local_40,logsoftmax_init,0,0,param_2);
  if (lVar2 == 0) {
    eai_op_layer_util_get_input(0,0);
  }
  else {
    *(undefined2 *)(lVar2 + 4) = 0x98;
    *(undefined4 *)(lVar2 + 0x70) = *(undefined4 *)(lVar4 + 0x10);
    *(undefined4 *)(lVar2 + 0x74) = *(undefined4 *)(lVar4 + 0x14);
    lVar4 = eai_op_layer_util_get_input(lVar2,0);
    if ((((lVar4 != 0) && (*(short *)(lVar2 + 4) == 0x98)) && (-1 < (int)*(uint *)(lVar2 + 0x70)))
       && (*(uint *)(lVar2 + 0x70) <= (*(uint *)(lVar4 + 0xc) & 0xf))) {
      uVar3 = 0;
      *param_1 = local_40;
      goto LAB_00127d78;
    }
  }
  __android_log_print(6,&DAT_00101ae3,"Invalid parameters.\n");
  uVar3 = 1;
LAB_00127d78:
  if (*(long *)(lVar1 + 0x28) == local_38) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail(uVar3);
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



/* ------------------------------------------------------------------
 * Function: lstm_load_vanilla
 * Address : 00127f34
 * Size    : 0x1b0 bytes
 * ------------------------------------------------------------------ */

undefined8 lstm_load_vanilla(long *param_1,undefined8 param_2)

{
  short sVar1;
  short sVar2;
  int iVar3;
  long lVar4;
  long lVar5;
  long lVar6;
  uint uVar7;
  long lVar8;
  
  lVar8 = *param_1;
  lVar4 = eai_op_layer_util_load_base(param_1,lstm_init,0,0,param_2);
  if (lVar4 == 0) {
    return 1;
  }
  *(undefined2 *)(lVar4 + 4) = 0xd0;
  lVar5 = eai_op_layer_util_get_input(lVar4,0);
  if ((lVar5 == 0) || (iVar3 = eai_op_layer_util_get_input_count(lVar4), iVar3 != 0xd)) {
    __android_log_print(6,&DAT_00101ae3,"Invalid layer params: %p.\n",lVar4);
    return 1;
  }
  if ((*(byte *)(lVar8 + 0x17) & 6) == 0) {
    *(uint *)(lVar4 + 0x70) = (uint)*(ushort *)(lVar8 + 0x10);
    uVar7 = (uint)*(ushort *)(lVar8 + 0x12);
    *(undefined1 *)(lVar4 + 0x78) = 1;
  }
  else {
    lVar5 = eai_op_layer_util_get_input(lVar4,1);
    lVar6 = eai_op_layer_util_get_input(lVar4,2);
    if (lVar5 == 0) {
      return 1;
    }
    if (lVar6 == 0) {
      return 1;
    }
    *(byte *)(lVar4 + 0x78) = (byte)((uint)*(undefined4 *)(lVar8 + 0x14) >> 0x19) & 3;
    *(undefined4 *)(lVar4 + 0x70) = *(undefined4 *)(lVar6 + 0x20);
    uVar7 = *(uint *)(lVar5 + 0x20);
  }
  *(uint *)(lVar4 + 0x74) = uVar7;
  sVar1 = *(short *)(lVar8 + 0x18);
  *(short *)(lVar4 + 0x80) = sVar1;
  *(byte *)(lVar4 + 0x86) = *(byte *)(lVar8 + 0x14) & 0x1f;
  *(undefined2 *)(lVar4 + 0x82) = *(undefined2 *)(lVar8 + 0x1a);
  uVar7 = *(uint *)(lVar8 + 0x14) >> 5 & 0x1f;
  *(char *)(lVar4 + 0x87) = (char)uVar7;
  sVar2 = *(short *)(lVar8 + 0x1c);
  *(short *)(lVar4 + 0x84) = sVar2;
  *(byte *)(lVar4 + 0x88) = (byte)(*(uint *)(lVar8 + 0x14) >> 10) & 0x1f;
  *(byte *)(lVar4 + 0x89) = (byte)(*(uint *)(lVar8 + 0x14) >> 0xf) & 0x1f;
  *(byte *)(lVar4 + 0x8a) = (byte)(*(uint *)(lVar8 + 0x14) >> 0x14) & 0x1f;
  if (((sVar1 == 0) || (uVar7 == 0)) || (sVar2 == 0)) {
    __android_log_print(6,&DAT_00101ae3,"LSTM requantization scale cannot be zero, layer ID: %u.\n",
                        *(undefined2 *)(lVar4 + 6));
  }
  return 0;
}



/* ------------------------------------------------------------------
 * Function: lstm_load_vanilla_legacy
 * Address : 001280e4
 * Size    : 0x1d0 bytes
 * ------------------------------------------------------------------ */

undefined8 lstm_load_vanilla_legacy(long *param_1,undefined8 param_2)

{
  undefined4 uVar1;
  int iVar2;
  long lVar3;
  long lVar4;
  long lVar5;
  long *plVar6;
  long *plVar7;
  long lVar8;
  
  lVar8 = *param_1;
  if (*(int *)(lVar8 + 0x18) != 1) {
    __android_log_print(6,&DAT_00101ae3,"Unexpected LSTM layout type.\n");
    return 1;
  }
  lVar3 = eai_op_layer_util_load_base(param_1,lstm_init,0,0,param_2);
  if (lVar3 != 0) {
    *(undefined2 *)(lVar3 + 4) = 0xd0;
    lVar4 = eai_op_layer_util_get_input(lVar3,0);
    if ((lVar4 != 0) && (iVar2 = eai_op_layer_util_get_input_count(lVar3), iVar2 == 0xd)) {
      if (*(char *)(lVar8 + 0x1c) == '\0') {
        *(undefined4 *)(lVar3 + 0x70) = *(undefined4 *)(lVar8 + 0x10);
        uVar1 = *(undefined4 *)(lVar8 + 0x14);
        *(undefined1 *)(lVar3 + 0x78) = 1;
      }
      else {
        lVar4 = eai_op_layer_util_get_input(lVar3,1);
        lVar5 = eai_op_layer_util_get_input(lVar3,2);
        if (lVar4 == 0) {
          return 1;
        }
        if (lVar5 == 0) {
          return 1;
        }
        *(undefined1 *)(lVar3 + 0x78) = *(undefined1 *)(lVar8 + 0x1c);
        *(undefined4 *)(lVar3 + 0x70) = *(undefined4 *)(lVar5 + 0x20);
        uVar1 = *(undefined4 *)(lVar4 + 0x20);
      }
      *(undefined4 *)(lVar3 + 0x74) = uVar1;
      plVar6 = (long *)eai_op_layer_util_get_input(lVar3,9);
      plVar7 = (long *)eai_op_layer_util_get_input(lVar3,10);
      if (plVar6 == (long *)0x0) {
        return 1;
      }
      if (plVar7 == (long *)0x0) {
        return 1;
      }
      *(short *)(lVar3 + 0x80) = (short)*(undefined4 *)(lVar8 + 0x20);
      *(char *)(lVar3 + 0x86) = (char)*(undefined4 *)(lVar8 + 0x24);
      uVar1 = *(undefined4 *)(*plVar6 + 0xc);
      *(undefined1 *)(lVar3 + 0x87) = 0;
      *(short *)(lVar3 + 0x82) = (short)uVar1;
      uVar1 = *(undefined4 *)(*plVar7 + 0xc);
      *(undefined2 *)(lVar3 + 0x88) = 0x1400;
      *(undefined1 *)(lVar3 + 0x8a) = 0xf;
      *(short *)(lVar3 + 0x84) = (short)uVar1;
      __android_log_print(6,&DAT_00101ae3,
                          "LSTM requantization scale cannot be zero, layer ID: %u.\n",
                          *(undefined2 *)(lVar3 + 6));
      return 0;
    }
    __android_log_print(6,&DAT_00101ae3,"Invalid layer params: %p.\n",lVar3);
  }
  return 1;
}



/* ------------------------------------------------------------------
 * Function: lstm_load
 * Address : 001282b4
 * Size    : 0x68 bytes
 * ------------------------------------------------------------------ */

undefined8 lstm_load(long *param_1)

{
  undefined8 uVar1;
  long lVar2;
  
  lVar2 = *param_1;
  if (lVar2 != 0) {
    if (*(char *)(lVar2 + 0xe) == '\x01') {
      uVar1 = lstm_load_vanilla();
      return uVar1;
    }
    if (*(char *)(lVar2 + 0xe) == '\0') {
      uVar1 = lstm_load_vanilla_legacy();
      return uVar1;
    }
    __android_log_print(6,&DAT_00101ae3,"invalid LSTM offline struct, layer ID: %u.\n",
                        (long)*(short *)(lVar2 + 0xc));
  }
  return 1;
}



/* ------------------------------------------------------------------
 * Function: no_op_load
 * Address : 00128dc8
 * Size    : 0x58 bytes
 * ------------------------------------------------------------------ */

undefined8 no_op_load(undefined8 param_1,undefined8 param_2)

{
  long lVar1;
  
  lVar1 = eai_op_layer_util_load_base(param_1,PTR_eai_op_layer_util_init_0012cb68,0,0,param_2);
  if (lVar1 != 0) {
    *(undefined2 *)(lVar1 + 4) = 0x70;
    return 0;
  }
  return 1;
}



/* ------------------------------------------------------------------
 * Function: softmax_init
 * Address : 00129b9c
 * Size    : 0x1b0 bytes
 * ------------------------------------------------------------------ */

void softmax_init(long param_1)

{
  long lVar1;
  long *plVar2;
  long *plVar3;
  undefined8 uVar4;
  ulong uVar5;
  int *piVar6;
  ulong uVar7;
  int local_50 [6];
  long local_38;
  
  lVar1 = tpidr_el0;
  local_38 = *(long *)(lVar1 + 0x28);
  plVar2 = (long *)eai_op_layer_util_get_input(param_1,0);
  plVar3 = (long *)eai_op_layer_util_get_output(param_1,0);
  if ((plVar2 == (long *)0x0) || (plVar3 == (long *)0x0)) {
    __android_log_print(6,&DAT_00101ae3,"Invalid tensors: %p, %p.\n",plVar2,plVar3);
    uVar4 = 1;
    if (*(long *)(lVar1 + 0x28) == local_38) {
      return;
    }
  }
  else if (((*(uint *)(*plVar2 + 4) >> 4 & 0xff) == 1) &&
          ((*(uint *)(*plVar3 + 4) >> 4 & 0xff) == 1)) {
    if ((*(int *)((long)plVar2 + 0x14) == 0x43210) && (*(int *)((long)plVar3 + 0x14) == 0x43210)) {
      if ((*(byte *)(plVar3 + 1) >> 2 & 1) != 0) {
        local_50[0] = 0;
        local_50[1] = 0;
        local_50[2] = 0;
        local_50[3] = 0;
        local_50[4] = 0;
        uVar5 = (ulong)*(uint *)((long)plVar2 + 0xc) & 0xf;
        if ((int)uVar5 != 0) {
          plVar2 = plVar2 + 3;
          piVar6 = local_50;
          uVar7 = uVar5;
          do {
            uVar7 = uVar7 - 1;
            *piVar6 = (int)*plVar2;
            plVar2 = (long *)((long)plVar2 + 4);
            piVar6 = piVar6 + 1;
          } while (uVar7 != 0);
        }
        if (*(int *)(param_1 + 0x74) != 1) {
          local_50[0] = *(int *)(param_1 + 0x74);
        }
        tensor_set_dimension(plVar3,uVar5,local_50);
      }
      uVar4 = eai_op_layer_util_init(param_1);
      if (*(long *)(lVar1 + 0x28) == local_38) {
        return;
      }
    }
    else {
      __android_log_print(6,&DAT_00101ae3,"Invalid dims_form for input or output\n");
      uVar4 = 1;
      if (*(long *)(lVar1 + 0x28) == local_38) {
        return;
      }
    }
  }
  else {
    __android_log_print(6,&DAT_00101ae3,"Invalid data type mix: input = %d, output = %d,\n");
    uVar4 = 1;
    if (*(long *)(lVar1 + 0x28) == local_38) {
      return;
    }
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail(uVar4);
}



/* ------------------------------------------------------------------
 * Function: softmax_load
 * Address : 00129d4c
 * Size    : 0x170 bytes
 * ------------------------------------------------------------------ */

void softmax_load(long *param_1,undefined8 param_2)

{
  long lVar1;
  long lVar2;
  undefined8 uVar3;
  long lVar4;
  long local_40;
  long local_38;
  
  lVar1 = tpidr_el0;
  local_38 = *(long *)(lVar1 + 0x28);
  lVar4 = *param_1;
  local_40 = lVar4;
  lVar2 = eai_op_layer_util_load_base(&local_40,softmax_init,0,0,param_2);
  if ((lVar4 == 0) || (lVar2 == 0)) {
    __android_log_print(6,&DAT_00101ae3,"bad pointers softmax_offline %p layer %p\n",lVar4,lVar2);
  }
  else {
    *(undefined2 *)(lVar2 + 4) = 0x98;
    *(undefined4 *)(lVar2 + 0x70) = *(undefined4 *)(lVar4 + 0x10);
    *(undefined4 *)(lVar2 + 0x74) = *(undefined4 *)(lVar4 + 0x14);
    lVar4 = eai_op_layer_util_get_input(lVar2,0);
    if (lVar4 != 0) {
      if (*(short *)(lVar2 + 4) == 0x98) {
        if (((int)*(uint *)(lVar2 + 0x70) < 0) ||
           ((*(uint *)(lVar4 + 0xc) & 0xf) < *(uint *)(lVar2 + 0x70))) {
          __android_log_print(6,&DAT_00101ae3,"Invalid params: %d\n");
          uVar3 = 1;
          if (*(long *)(lVar1 + 0x28) == local_38) {
            return;
          }
        }
        else {
          uVar3 = 0;
          *param_1 = local_40;
          if (*(long *)(lVar1 + 0x28) == local_38) {
            return;
          }
        }
      }
      else {
        __android_log_print(6,&DAT_00101ae3,"p != ((int8_t*)layer) + layer->size\n");
        uVar3 = 1;
        if (*(long *)(lVar1 + 0x28) == local_38) {
          return;
        }
      }
      goto LAB_00129e98;
    }
  }
  uVar3 = 1;
  if (*(long *)(lVar1 + 0x28) == local_38) {
    return;
  }
LAB_00129e98:
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail(uVar3);
}



/* ------------------------------------------------------------------
 * Function: supergemm_init
 * Address : 0012aea8
 * Size    : 0x104 bytes
 * ------------------------------------------------------------------ */

undefined4 supergemm_init(long param_1)

{
  undefined4 uVar1;
  int iVar2;
  long lVar3;
  long lVar4;
  long lVar5;
  char *pcVar6;
  
  uVar1 = eai_op_layer_util_init();
  lVar3 = eai_op_layer_util_get_input(param_1,0);
  lVar4 = eai_op_layer_util_get_input(param_1,1);
  lVar5 = eai_op_layer_util_get_output(param_1,0);
  if (((lVar5 == 0) || (lVar3 == 0)) || (lVar4 == 0)) {
    pcVar6 = "not enought input/output tensors.\n";
  }
  else {
    if (((*(uint *)(lVar3 + 0xc) & 0xf) == 2) && ((*(uint *)(lVar4 + 0xc) & 0xf) == 2)) {
      if ((*(ushort *)(param_1 + 0x7c) & 0x3800) != 0) {
        return 1;
      }
      iVar2 = check_superlayer_mode(param_1 + 0x70);
      if (iVar2 != 0) {
        uVar1 = 1;
      }
      return uVar1;
    }
    pcVar6 = "Invlaid dimensions for input tensors\n";
  }
  __android_log_print(6,&DAT_00101ae3,pcVar6);
  return 1;
}



/* ------------------------------------------------------------------
 * Function: supergemm_load
 * Address : 0012afac
 * Size    : 0x80 bytes
 * ------------------------------------------------------------------ */

undefined8 supergemm_load(long *param_1,undefined8 param_2)

{
  long lVar1;
  long lVar2;
  
  lVar2 = *param_1;
  lVar1 = eai_op_layer_util_load_base(param_1,supergemm_init,0,0,param_2);
  if (lVar1 != 0) {
    *(undefined2 *)(lVar1 + 4) = 0xa8;
    memscpy(lVar1 + 0x70,0x18,lVar2 + 0x10,0x18);
    *(undefined1 *)(lVar1 + 0x82) = *(undefined1 *)(lVar2 + 0xe);
    return 0;
  }
  return 1;
}



