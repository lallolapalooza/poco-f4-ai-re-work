/*
 * Ghidra headless decompilation
 * program   : libeai_sym.so
 * executable: /tmp/libeai_sym.so
 * language  : AARCH64:LE:64:v8A
 * imagebase : 00100000
 * regex     : eai_init_internal|load_network|parse_eai_layers|load_eai_layers|initialize_io_tensor|initialize_tensor_meta|relocate_tensor|validate_eai_model|initialize_eai_model_info|copy_eai_meta_tensor|extract_eai_model_from_buffer|calculate_total_layer_size|so_op_intf_load2|network_assign_buffer|get_layer_tensor_count|tensor_init|tensor_expand_strides
 * max funcs : 40
 * note      : pseudocode from a (possibly stripped) binary is approximate;
 *             indirect calls and register-indirect JNI tables are lost.
 */

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
 * Function: load_network
 * Address : 0010d754
 * Size    : 0x208 bytes
 * ------------------------------------------------------------------ */

int load_network(int *param_1,long param_2,undefined4 param_3,long param_4,undefined4 param_5,
                int param_6)

{
  long lVar1;
  int iVar2;
  long lVar3;
  char *pcVar4;
  undefined4 local_4c;
  long local_48;
  
  lVar1 = tpidr_el0;
  local_48 = *(long *)(lVar1 + 0x28);
  local_4c = param_3;
  if (((param_1 == (int *)0x0) || (param_2 == 0)) || (param_4 == 0)) {
    __android_log_print(6,&DAT_00101ae3,"load_network - NULL passed\n");
    iVar2 = 1;
  }
  else {
    if (param_6 != 0) {
      param_1[0x26] = param_1[0x26] | 1;
    }
    lVar3 = extract_eai_model_from_buffer(param_2,&local_4c);
    if (lVar3 == 0) {
      iVar2 = 1;
      pcVar4 = "fail to find eai model in the buffer\n";
    }
    else {
      iVar2 = validate_eai_model(param_1,lVar3,local_4c);
      if (iVar2 == 0) {
        if ((*param_1 == 0) || (iVar2 = copy_eai_meta_tensor(param_1), iVar2 == 0)) {
          iVar2 = initialize_eai_model_info(param_1,lVar3,local_4c);
          if (iVar2 == 0) {
            if ((*(byte *)(*(long *)(param_1 + 2) + 0xc) >> 2 & 1) == 0) {
              iVar2 = 0xc;
              pcVar4 = "Error: fixed point model, float runtime!\n";
            }
            else {
              iVar2 = initialize_tensor_meta(param_1);
              if (iVar2 == 0) {
                iVar2 = relocate_tensor(param_1,0,*(undefined8 *)(param_1 + 0x1c),param_1[0x1e]);
                if (iVar2 == 0) {
                  iVar2 = parse_eai_layers(param_1);
                  if (iVar2 == 0) {
                    iVar2 = load_eai_layers(param_1,param_4,param_5);
                    if (iVar2 == 0) {
                      iVar2 = initialize_io_tensor(param_1);
                      if (iVar2 == 0) goto LAB_0010d824;
                      pcVar4 = "fail to initialize network i/o tensors\n";
                    }
                    else {
                      pcVar4 = "fail to load eai layers\n";
                    }
                  }
                  else {
                    pcVar4 = "fail to parse the eai layers\n";
                  }
                }
                else {
                  pcVar4 = "fail to relocate tensors\n";
                }
              }
              else {
                pcVar4 = "fail to parse the eai tensors\n";
              }
            }
          }
          else {
            pcVar4 = "fail to parse eai model\n";
          }
        }
        else {
          pcVar4 = "fail to copy eai meta tensor\n";
        }
      }
      else {
        pcVar4 = "fail to validate eai model\n";
      }
    }
    __android_log_print(6,&DAT_00101ae3,pcVar4);
    cleanup_context(param_1);
  }
LAB_0010d824:
  if (*(long *)(lVar1 + 0x28) == local_48) {
    return iVar2;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: network_assign_buffer
 * Address : 0010d95c
 * Size    : 0xfc bytes
 * ------------------------------------------------------------------ */

int network_assign_buffer(long param_1)

{
  ulong uVar1;
  ulong uVar2;
  int iVar3;
  int iVar4;
  int iVar5;
  char *pcVar6;
  ulong uVar7;
  ulong uVar8;
  ulong uVar9;
  
  if (param_1 == 0) {
    return 1;
  }
  iVar5 = *(int *)(*(long *)(param_1 + 8) + 0x14);
  iVar3 = *(int *)(*(long *)(param_1 + 8) + 0x18);
  iVar4 = get_alignment_requriement();
  uVar9 = *(ulong *)(param_1 + 0x130);
  uVar7 = *(ulong *)(param_1 + 0x140);
  uVar8 = (ulong)(iVar4 + -1);
  uVar1 = uVar9;
  if ((uVar9 & uVar8) != 0) {
    uVar1 = (uVar9 + (long)iVar4) - (uVar9 & uVar8);
  }
  uVar2 = uVar7;
  if ((uVar7 & uVar8) != 0) {
    uVar2 = (uVar7 + (long)iVar4) - (uVar7 & uVar8);
  }
  if ((uVar2 == 0) ||
     (iVar5 = relocate_tensor(param_1,1,uVar2,iVar5 + ((int)uVar7 - (int)uVar2)), iVar5 == 0)) {
    if (uVar1 == 0) {
      return 0;
    }
    iVar5 = relocate_tensor(param_1,2,uVar1,iVar3 + ((int)uVar9 - (int)uVar1));
    if (iVar5 == 0) {
      return 0;
    }
    pcVar6 = "fail to relocate persistent tensor\n";
  }
  else {
    pcVar6 = "fail to relocate scratch tensor\n";
  }
  __android_log_print(6,&DAT_00101ae3,pcVar6);
  return iVar5;
}



/* ------------------------------------------------------------------
 * Function: get_layer_tensor_count
 * Address : 0010da58
 * Size    : 0x54 bytes
 * ------------------------------------------------------------------ */

ulong get_layer_tensor_count(long param_1,int param_2,short param_3)

{
  ushort *puVar1;
  uint *puVar2;
  
  if ((((param_1 != 0) && (puVar2 = *(uint **)(param_1 + 0x30), puVar2 != (uint *)0x0)) &&
      (*(long *)(param_1 + 0x18) != 0)) && ((int)param_3 + 1U < *puVar2)) {
    puVar1 = (ushort *)((long)puVar2 + (ulong)puVar2[(long)param_3 + 2]);
    if (param_2 != 1) {
      puVar1 = (ushort *)((long)puVar2 + (ulong)puVar2[(long)param_3 + 2]) + 1;
    }
    return (ulong)*puVar1;
  }
  return 0xffffffff;
}



/* ------------------------------------------------------------------
 * Function: initialize_io_tensor
 * Address : 0010daac
 * Size    : 0x224 bytes
 * ------------------------------------------------------------------ */

undefined8 initialize_io_tensor(long param_1)

{
  ushort *puVar1;
  undefined4 uVar2;
  ushort uVar3;
  long lVar4;
  int *piVar5;
  ulong uVar6;
  long lVar7;
  ulong uVar8;
  ulong uVar9;
  uint uVar10;
  
  if ((param_1 == 0) || (piVar5 = *(int **)(param_1 + 0x30), piVar5 == (int *)0x0)) {
    uVar9 = 0xffffffff;
    uVar8 = 0xffffffff;
  }
  else {
    if ((*(long *)(param_1 + 0x18) == 0) || (*piVar5 == 0)) {
      uVar9 = 0xffffffff;
      lVar7 = *(long *)(param_1 + 0x18);
    }
    else {
      uVar9 = (ulong)*(ushort *)((long)piVar5 + (ulong)(uint)piVar5[1]);
      lVar7 = *(long *)(param_1 + 0x18);
    }
    if ((lVar7 == 0) || (*piVar5 == 0)) {
      uVar8 = 0xffffffff;
    }
    else {
      uVar8 = (ulong)*(ushort *)((long)piVar5 + (ulong)(uint)piVar5[1] + 2);
    }
  }
  *(int *)(param_1 + 0x90) = (int)uVar9;
  *(int *)(param_1 + 0x94) = (int)uVar8;
  uVar10 = *(uint *)(param_1 + 0x98) & 1;
  lVar7 = param_1 + (ulong)uVar10 * 0x14 + 0xf8;
  lVar4 = allocate_memory(-(uVar9 >> 0x1f) & 0xfffffff800000000 | uVar9 << 3,
                          *(undefined4 *)(param_1 + 0x124),lVar7);
  *(long *)(param_1 + 0x80) = lVar4;
  if (lVar4 != 0) {
    uVar2 = *(undefined4 *)(param_1 + 0x124);
    *(int *)(param_1 + (ulong)uVar10 * 0x14 + 0x100) = (int)(uVar9 << 3);
    lVar7 = allocate_memory(-(uVar8 >> 0x1f) & 0xfffffff800000000 | uVar8 << 3,uVar2,lVar7);
    *(long *)(param_1 + 0x88) = lVar7;
    if (lVar7 != 0) {
      *(int *)(param_1 + (ulong)uVar10 * 0x14 + 0x104) = (int)(uVar8 << 3);
      if (0 < (int)uVar9) {
        uVar6 = 0;
        do {
          piVar5 = *(int **)(param_1 + 0x30);
          if (piVar5 == (int *)0x0) {
            return 1;
          }
          if (*(long *)(param_1 + 0x18) == 0) {
            return 1;
          }
          if (*piVar5 == 0) {
            return 1;
          }
          if (*(ushort *)((long)piVar5 + (ulong)(uint)piVar5[1]) <= uVar6) {
            return 1;
          }
          uVar3 = ((ushort *)((long)piVar5 + (ulong)(uint)piVar5[1]))[uVar6 + 2];
          if ((short)uVar3 < 0) {
            return 1;
          }
          lVar7 = *(long *)(param_1 + 0x18);
          if (*(long *)(param_1 + 0x20) != 0) {
            lVar7 = *(long *)(param_1 + 0x20);
          }
          *(ulong *)(*(long *)(param_1 + 0x80) + uVar6 * 8) = lVar7 + (ulong)uVar3 * 0x50 + 0x20;
          uVar6 = uVar6 + 1;
        } while (uVar9 != uVar6);
      }
      if ((int)uVar8 < 1) {
        return 0;
      }
      uVar9 = 0;
      do {
        piVar5 = *(int **)(param_1 + 0x30);
        if ((((piVar5 == (int *)0x0) || (*(long *)(param_1 + 0x18) == 0)) || (*piVar5 == 0)) ||
           ((puVar1 = (ushort *)((long)piVar5 + (ulong)(uint)piVar5[1]), puVar1[1] <= uVar9 ||
            ((short)puVar1[uVar9 + *puVar1 + 2] < 0)))) {
          return 1;
        }
        lVar7 = *(long *)(param_1 + 0x18);
        if (*(long *)(param_1 + 0x20) != 0) {
          lVar7 = *(long *)(param_1 + 0x20);
        }
        *(ulong *)(*(long *)(param_1 + 0x88) + uVar9 * 8) =
             lVar7 + (ulong)puVar1[uVar9 + *puVar1 + 2] * 0x50 + 0x20;
        uVar9 = uVar9 + 1;
      } while (uVar8 != uVar9);
      return 0;
    }
  }
  return 8;
}



/* ------------------------------------------------------------------
 * Function: parse_eai_layers
 * Address : 0010dcd0
 * Size    : 0x10c bytes
 * ------------------------------------------------------------------ */

undefined8 parse_eai_layers(long param_1)

{
  undefined4 uVar1;
  int iVar2;
  void *__s;
  undefined4 *puVar3;
  size_t __n;
  uint uVar4;
  
  uVar1 = **(undefined4 **)(param_1 + 0x10);
  iVar2 = calculate_total_layer_size
                    (param_1,*(undefined4 **)(param_1 + 0x10) + 2,uVar1,
                     *(undefined4 *)(param_1 + 0x40));
  if (iVar2 == 0) {
    __android_log_print(6,&DAT_00101ae3,"fail to parser eai layers\n");
    return 1;
  }
  __n = (size_t)iVar2;
  uVar4 = *(uint *)(param_1 + 0x98) & 1;
  __s = (void *)allocate_memory(__n,*(undefined4 *)(param_1 + 0x124),
                                param_1 + (ulong)uVar4 * 0x14 + 0xf8);
  if (__s != (void *)0x0) {
    *(void **)(param_1 + 0xa8) = __s;
    memset(__s,0,__n);
    puVar3 = *(undefined4 **)(param_1 + 0xa8);
    *(int *)(param_1 + (ulong)uVar4 * 0x14 + 0x108) = iVar2;
    *puVar3 = uVar1;
    return 0;
  }
  __android_log_print(6,&DAT_00101ae3,"Failed to allocate memory of size for layer list: %ld.",__n);
  return 8;
}



/* ------------------------------------------------------------------
 * Function: load_eai_layers
 * Address : 0010dddc
 * Size    : 0x35c bytes
 * ------------------------------------------------------------------ */

undefined8 load_eai_layers(long param_1,long param_2,uint param_3)

{
  ushort *puVar1;
  ushort uVar2;
  short sVar3;
  int iVar4;
  undefined8 uVar5;
  uint uVar6;
  ulong uVar7;
  long *plVar8;
  uint uVar9;
  long lVar10;
  uint uVar11;
  uint *puVar12;
  uint *puVar13;
  ulong uVar14;
  uint *puVar15;
  int iVar16;
  
  uVar6 = **(uint **)(param_1 + 0x10);
  if ((int)uVar6 < 1) {
    uVar5 = 0;
  }
  else {
    puVar15 = *(uint **)(param_1 + 0x10) + 2;
    uVar14 = 0;
    iVar16 = 0;
    lVar10 = *(long *)(param_1 + 0xa8) + (ulong)uVar6 * 8 + 8;
    do {
      puVar12 = *(uint **)(param_1 + 0x30);
      sVar3 = (short)uVar14;
      if ((puVar12 == (uint *)0x0) || (*(long *)(param_1 + 0x18) == 0)) {
        uVar9 = 0xffffffff;
LAB_0010de78:
        uVar11 = 0xffffffff;
      }
      else {
        if (*puVar12 <= (int)sVar3 + 1U) {
          uVar9 = 0xffffffff;
          if (*(long *)(param_1 + 0x18) != 0) goto LAB_0010e08c;
          goto LAB_0010de78;
        }
        uVar9 = (uint)*(ushort *)((long)puVar12 + (ulong)puVar12[(long)sVar3 + 2]);
        if (*(long *)(param_1 + 0x18) == 0) goto LAB_0010de78;
LAB_0010e08c:
        if (*puVar12 <= (int)sVar3 + 1U) goto LAB_0010de78;
        uVar11 = (uint)*(ushort *)((long)puVar12 + (ulong)puVar12[(long)sVar3 + 2] + 2);
      }
      puVar12 = (uint *)(lVar10 + (long)(int)(uVar11 + uVar9) * 8);
      *(long *)(puVar12 + 0x10) = lVar10;
      *(long *)(puVar12 + 0x12) = lVar10 + (long)(int)uVar9 * 8;
      *(short *)(puVar12 + 3) = (short)uVar9;
      *(short *)((long)puVar12 + 0xe) = (short)uVar11;
      if (0 < (short)uVar9) {
        uVar7 = 0;
        do {
          puVar13 = *(uint **)(param_1 + 0x30);
          if (puVar13 == (uint *)0x0) {
LAB_0010deac:
            lVar10 = 0;
          }
          else {
            lVar10 = 0;
            if (*(long *)(param_1 + 0x18) != 0) {
              if (((int)sVar3 + 1U < *puVar13) &&
                 (uVar7 < *(ushort *)((long)puVar13 + (ulong)puVar13[(long)sVar3 + 2]))) {
                uVar2 = ((ushort *)((long)puVar13 + (ulong)puVar13[(long)sVar3 + 2]))[uVar7 + 2];
                lVar10 = *(long *)(param_1 + 0x18);
                if (*(long *)(param_1 + 0x20) != 0) {
                  lVar10 = *(long *)(param_1 + 0x20);
                }
                if (-1 < (short)uVar2) {
                  lVar10 = lVar10 + (ulong)(uint)(int)(short)uVar2 * 0x50 + 0x20;
                  goto LAB_0010deb0;
                }
              }
              goto LAB_0010deac;
            }
          }
LAB_0010deb0:
          *(long *)(*(long *)(puVar12 + 0x10) + uVar7 * 8) = lVar10;
          uVar7 = uVar7 + 1;
        } while ((long)uVar7 < (long)(short)puVar12[3]);
        uVar11 = (uint)*(ushort *)((long)puVar12 + 0xe);
      }
      if (0 < (short)uVar11) {
        uVar7 = 0;
        do {
          puVar13 = *(uint **)(param_1 + 0x30);
          if (puVar13 == (uint *)0x0) {
LAB_0010df4c:
            lVar10 = 0;
          }
          else {
            lVar10 = 0;
            if (*(long *)(param_1 + 0x18) != 0) {
              if (((int)sVar3 + 1U < *puVar13) &&
                 (puVar1 = (ushort *)((long)puVar13 + (ulong)puVar13[(long)(int)sVar3 + 2]),
                 uVar7 < puVar1[1])) {
                lVar10 = *(long *)(param_1 + 0x18);
                if (*(long *)(param_1 + 0x20) != 0) {
                  lVar10 = *(long *)(param_1 + 0x20);
                }
                if (-1 < (short)puVar1[uVar7 + *puVar1 + 2]) {
                  lVar10 = lVar10 + (ulong)(uint)(int)(short)puVar1[uVar7 + *puVar1 + 2] * 0x50 +
                           0x20;
                  goto LAB_0010df50;
                }
              }
              goto LAB_0010df4c;
            }
          }
LAB_0010df50:
          *(long *)(*(long *)(puVar12 + 0x12) + uVar7 * 8) = lVar10;
          uVar7 = uVar7 + 1;
        } while ((long)uVar7 < (long)*(short *)((long)puVar12 + 0xe));
      }
      *puVar12 = *puVar15;
      uVar9 = puVar15[2];
      *(long *)(puVar12 + 4) = param_1;
      puVar12[2] = uVar9;
      puVar12[0x14] = *(uint *)(param_1 + 0x124);
      if ((int)param_3 < 1) {
        iVar16 = 0;
code_r0x0010e0c0:
        __android_log_print(6,&DAT_00101ae3,"Failed to load layer index:%d\n",uVar14 & 0xffffffff);
        __android_log_print(6,&DAT_00101ae3,"Failed to load eai layers.");
        uVar5 = 1;
        goto joined_r0x0010e108;
      }
      plVar8 = (long *)(param_2 + 8);
      uVar7 = (ulong)param_3;
      while (*(uint *)(plVar8 + -1) != *puVar15) {
        plVar8 = plVar8 + 2;
        uVar7 = uVar7 - 1;
        if (uVar7 == 0) goto code_r0x0010e0c0;
      }
      if ((*plVar8 == 0) || (iVar4 = layer_load_by_loader(puVar15,*plVar8,puVar12), iVar4 != 0))
      goto code_r0x0010e0c0;
      if ((*(byte *)(param_1 + 0x98) & 1) != 0) {
        puVar12[2] = puVar12[2] | 0x40000000;
      }
      lVar10 = uVar14 * 8;
      uVar14 = uVar14 + 1;
      *(uint **)(*(long *)(param_1 + 0xa8) + lVar10 + 8) = puVar12;
      lVar10 = (long)puVar12 + (long)(short)puVar12[1];
      iVar16 = (puVar12[2] >> 7 & 1) + iVar16;
      puVar15 = (uint *)((long)puVar15 + (long)(int)puVar15[1]);
    } while (uVar14 != uVar6);
    uVar5 = 0;
joined_r0x0010e108:
    if (iVar16 != 0) {
      uVar6 = *(uint *)(param_1 + 0x120) | 8;
      goto LAB_0010e114;
    }
  }
  uVar6 = *(uint *)(param_1 + 0x120) & 0xfffffff7;
LAB_0010e114:
  *(uint *)(param_1 + 0x120) = uVar6;
  return uVar5;
}



/* ------------------------------------------------------------------
 * Function: initialize_tensor_meta
 * Address : 0010e138
 * Size    : 0x5c bytes
 * ------------------------------------------------------------------ */

undefined8 initialize_tensor_meta(long param_1)

{
  uint uVar1;
  int *piVar2;
  
  if ((param_1 != 0) && (piVar2 = *(int **)(param_1 + 0x18), piVar2 != (int *)0x0)) {
    if (*piVar2 != 0) {
      uVar1 = 0;
      if (*(int **)(param_1 + 0x20) != (int *)0x0) {
        piVar2 = *(int **)(param_1 + 0x20);
      }
      piVar2 = piVar2 + 0x16;
      do {
        *(int **)(piVar2 + -0xe) = piVar2;
        uVar1 = uVar1 + 1;
        piVar2[-2] = 0;
        piVar2[-1] = 0;
        piVar2 = piVar2 + 0x14;
      } while (uVar1 < **(uint **)(param_1 + 0x18));
    }
    return 0;
  }
  return 1;
}



/* ------------------------------------------------------------------
 * Function: relocate_tensor
 * Address : 0010e194
 * Size    : 0x1e0 bytes
 * ------------------------------------------------------------------ */

void relocate_tensor(long param_1,uint param_2,long param_3,uint param_4)

{
  uint uVar1;
  long lVar2;
  int iVar3;
  undefined8 uVar4;
  long *plVar5;
  ulong uVar6;
  long lVar7;
  ulong uVar8;
  undefined8 local_50;
  long local_48;
  
  lVar2 = tpidr_el0;
  local_48 = *(long *)(lVar2 + 0x28);
  if (2 < param_2) {
    uVar4 = 1;
    goto LAB_0010e344;
  }
  lVar7 = *(long *)(param_1 + (ulong)param_2 * 8 + 0x48);
  uVar1 = *(uint *)(param_1 + (ulong)param_2 * 4 + 0x60);
  uVar8 = (ulong)uVar1;
  local_50 = 0;
  get_compiler_version(*(undefined8 *)(param_1 + 8),(long)&local_50 + 4,&local_50);
  if (local_50._4_4_ < 4) {
    if (uVar1 != 0) {
      if (3 < (uint)local_50 && local_50._4_4_ == 3) goto LAB_0010e224;
      if (param_2 - 1 < 2) {
        uVar6 = 0;
        do {
          if (*(uint *)(lVar7 + 0x2c) == 0xffffffff) {
            *(undefined8 *)(lVar7 + 0x30) = 0;
          }
          else {
            *(ulong *)(lVar7 + 0x30) = param_3 + (ulong)*(uint *)(lVar7 + 0x2c);
            iVar3 = tensor_size(lVar7);
            if (param_4 < (uint)(*(int *)(lVar7 + 0x2c) + iVar3)) goto code_r0x0010e270;
          }
          uVar6 = uVar6 + 1;
          lVar7 = lVar7 + 0x50;
        } while (uVar8 != uVar6);
      }
      else {
        uVar6 = 0;
        plVar5 = (long *)(lVar7 + 0x30);
        do {
          uVar1 = *(uint *)((long)plVar5 + -4);
          if (uVar1 == 0xffffffff) {
            *plVar5 = 0;
          }
          else {
            *plVar5 = param_3 + (ulong)uVar1;
            if (param_4 < ((*(int *)plVar5[-6] - ((uint)((int *)plVar5[-6])[1] >> 0xc & 0xffff)) +
                          uVar1) - 0x50) goto code_r0x0010e270;
          }
          uVar6 = uVar6 + 1;
          plVar5 = plVar5 + 10;
        } while (uVar8 != uVar6);
      }
    }
  }
  else if (uVar1 != 0) {
LAB_0010e224:
    uVar6 = 0;
    plVar5 = (long *)(lVar7 + 0x30);
    do {
      uVar1 = *(uint *)((long)plVar5 + -4);
      if (uVar1 == 0xffffffff) {
        *plVar5 = 0;
      }
      else {
        *plVar5 = param_3 + (ulong)uVar1;
        if (param_4 < uVar1 + *(int *)plVar5[-6]) goto code_r0x0010e270;
      }
      uVar6 = uVar6 + 1;
      plVar5 = plVar5 + 10;
    } while (uVar8 != uVar6);
  }
  uVar4 = 0;
LAB_0010e344:
  if (*(long *)(lVar2 + 0x28) == local_48) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail(uVar4);
code_r0x0010e270:
  __android_log_print(6,&DAT_00101ae3,"Tensor out of bounds! Id: %d\n",uVar6 & 0xffffffff);
  uVar4 = 10;
  goto LAB_0010e344;
}



/* ------------------------------------------------------------------
 * Function: validate_eai_model
 * Address : 0010e374
 * Size    : 0x234 bytes
 * ------------------------------------------------------------------ */

void validate_eai_model(uint *param_1,int *param_2,uint param_3)

{
  ulong uVar1;
  uint uVar2;
  long lVar3;
  undefined8 uVar4;
  char *pcVar5;
  uint uVar6;
  ulong uVar7;
  long lVar8;
  int *piVar9;
  long lVar10;
  uint uVar11;
  int iVar12;
  int iVar13;
  int iVar14;
  int iVar15;
  int iVar16;
  int iVar17;
  int iVar18;
  undefined8 local_48;
  uint local_40;
  int local_3c;
  long local_38;
  
  lVar3 = tpidr_el0;
  local_38 = *(long *)(lVar3 + 0x28);
  uVar4 = 1;
  if ((((param_1 != (uint *)0x0) && (param_2 != (int *)0x0)) && (0x27 < param_3)) &&
     ((*param_2 == 0x32696165 && ((uint)param_2[4] <= param_3)))) {
    local_3c = 0;
    local_40 = 0;
    local_48 = 0;
    get_compiler_version(param_2,&local_3c,&local_40);
    get_runtime_version((long)&local_48 + 4,&local_48);
    if ((local_3c == local_48._4_4_) && (local_40 <= (uint)local_48)) {
      uVar2 = param_2[1];
      uVar6 = uVar2 >> 0x10;
      uVar11 = uVar2 & 0xffff;
      if ((uVar2 < 0x20000) || ((uVar6 == 2 && (uVar11 < 2)))) {
        pcVar5 = "Unsupported eai format version! %d - %d";
      }
      else {
        uVar6 = (uint)*(byte *)((long)param_2 + 0x21);
        if (*(byte *)((long)param_2 + 0x21) == 0) {
          if (((ulong)param_2 & 7) == 0) {
            uVar2 = param_2[7];
            uVar7 = (ulong)uVar2;
            uVar11 = uVar2 * 0x10 + 0x28;
            if (uVar2 != 0) {
              if (uVar2 < 9) {
                lVar10 = 0;
              }
              else {
                iVar13 = 0;
                iVar14 = 0;
                uVar1 = 8;
                if ((uVar2 & 7) != 0) {
                  uVar1 = uVar7 & 7;
                }
                lVar10 = uVar7 - uVar1;
                iVar15 = 0;
                iVar16 = 0;
                iVar17 = 0;
                iVar18 = 0;
                iVar12 = 0;
                piVar9 = param_2 + 0x1b;
                lVar8 = lVar10;
                do {
                  lVar8 = lVar8 + -8;
                  iVar15 = *piVar9 + iVar15;
                  iVar16 = piVar9[4] + iVar16;
                  iVar17 = piVar9[8] + iVar17;
                  iVar18 = piVar9[0xc] + iVar18;
                  uVar11 = piVar9[-0x10] + uVar11;
                  iVar12 = piVar9[-0xc] + iVar12;
                  iVar13 = piVar9[-8] + iVar13;
                  iVar14 = piVar9[-4] + iVar14;
                  piVar9 = piVar9 + 0x20;
                } while (lVar8 != 0);
                uVar11 = iVar15 + uVar11 + iVar16 + iVar12 + iVar17 + iVar13 + iVar18 + iVar14;
              }
              lVar8 = uVar7 - lVar10;
              piVar9 = param_2 + lVar10 * 4 + 0xb;
              do {
                lVar8 = lVar8 + -1;
                uVar11 = *piVar9 + uVar11;
                piVar9 = piVar9 + 4;
              } while (lVar8 != 0);
            }
            uVar4 = 1;
            if (uVar11 <= param_3) {
              *(int **)(param_1 + 2) = param_2;
              *param_1 = (uint)*(byte *)((long)param_2 + 0x23);
              *(undefined1 *)((long)param_2 + 0x23) = 1;
              uVar4 = 0;
            }
          }
          else {
            __android_log_print(6,&DAT_00101ae3,"Model buffer %p is not %u aligned\n",param_2,8);
            uVar4 = 0xe;
          }
          goto LAB_0010e478;
        }
        pcVar5 = "Unsupported model enpu version %d, runtime enpu version %d\n";
        uVar11 = 0;
      }
      __android_log_print(6,&DAT_00101ae3,pcVar5,uVar6,uVar11);
    }
    else {
      __android_log_print(6,&DAT_00101ae3,
                          "Model with eai_builder version %d.%d is not supported on runtime %d.%d");
    }
    uVar4 = 9;
  }
LAB_0010e478:
  if (*(long *)(lVar3 + 0x28) == local_38) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail(uVar4);
}



/* ------------------------------------------------------------------
 * Function: initialize_eai_model_info
 * Address : 0010e5a8
 * Size    : 0x264 bytes
 * ------------------------------------------------------------------ */

undefined8 initialize_eai_model_info(int *param_1)

{
  long lVar1;
  uint *puVar2;
  int iVar3;
  char *pcVar4;
  uint *puVar5;
  uint *puVar6;
  long lVar7;
  uint uVar8;
  ulong uVar9;
  
  lVar7 = *(long *)(param_1 + 2);
  iVar3 = get_alignment_requriement(lVar7);
  if (*param_1 == 0) {
    if (*(int *)(lVar7 + 0x14) != 0) {
      *(int *)(lVar7 + 0x14) = *(int *)(lVar7 + 0x14) + iVar3;
    }
    if (*(int *)(lVar7 + 0x18) != 0) {
      *(int *)(lVar7 + 0x18) = *(int *)(lVar7 + 0x18) + iVar3;
    }
  }
  if (*(int *)(lVar7 + 0x1c) == 0) {
    uVar8 = 0;
  }
  else {
    uVar9 = 0;
    uVar8 = 0;
    puVar5 = (uint *)(lVar7 + 0x30);
    do {
      if (puVar5[-2] < 7) {
        lVar1 = lVar7 + (ulong)*puVar5;
        switch(puVar5[-2]) {
        case 0:
          *(long *)(param_1 + 10) = lVar1;
          break;
        case 1:
          *(long *)(param_1 + 6) = lVar1;
          param_1[0x11] = puVar5[-1];
          break;
        case 2:
          *(long *)(param_1 + 0x1c) = lVar1;
          param_1[0x1e] = puVar5[-1];
          break;
        case 3:
          goto code_r0x0010e630;
        case 4:
          *(long *)(param_1 + 4) = lVar1;
          uVar8 = puVar5[-1];
          break;
        case 5:
          *(long *)(param_1 + 0xc) = lVar1;
          break;
        case 6:
          *(long *)(param_1 + 0xe) = lVar1;
        }
      }
      else {
code_r0x0010e630:
        __android_log_print(6,&DAT_00101ae3,"ignore the unrecognized section\n");
      }
      uVar9 = uVar9 + 1;
      puVar5 = puVar5 + 4;
    } while (uVar9 < *(uint *)(lVar7 + 0x1c));
  }
  if (((*(uint **)(param_1 + 4) == (uint *)0x0) || (*(long *)(param_1 + 10) == 0)) || (uVar8 < 8)) {
    pcVar4 = "Invalid eai model\n";
  }
  else {
    puVar5 = *(uint **)(param_1 + 6);
    if (((puVar5 == (uint *)0x0) || ((uint)param_1[0x11] < 0x20)) ||
       ((ulong)*puVar5 * 0x50 + 0x20 != (ulong)(uint)param_1[0x11])) {
      pcVar4 = "Invalid tensor metadata\n";
    }
    else {
      if ((ulong)**(uint **)(param_1 + 4) << 4 <= (ulong)(uVar8 - 8)) {
        param_1[0x10] = uVar8 - 8;
        puVar6 = puVar5;
        if (*(uint **)(param_1 + 8) != (uint *)0x0) {
          puVar6 = *(uint **)(param_1 + 8);
        }
        puVar6 = puVar6 + 8;
        puVar2 = (uint *)0x0;
        if (puVar5[4] != 0) {
          puVar2 = puVar6;
        }
        *(uint **)(param_1 + 0x12) = puVar2;
        param_1[0x18] = puVar5[4];
        uVar8 = puVar5[4];
        puVar2 = (uint *)0x0;
        if (puVar5[5] != 0) {
          puVar2 = puVar6 + (ulong)uVar8 * 0x14;
        }
        *(uint **)(param_1 + 0x14) = puVar2;
        param_1[0x19] = puVar5[5];
        if (puVar5[6] == 0) {
          puVar6 = (uint *)0x0;
        }
        else {
          puVar6 = puVar6 + (ulong)uVar8 * 0x14 + (ulong)puVar5[5] * 0x14;
        }
        *(uint **)(param_1 + 0x16) = puVar6;
        param_1[0x1a] = puVar5[6];
        return 0;
      }
      pcVar4 = "Invalid layer size\n";
    }
  }
  __android_log_print(6,&DAT_00101ae3,pcVar4);
  return 1;
}



/* ------------------------------------------------------------------
 * Function: copy_eai_meta_tensor
 * Address : 0010e80c
 * Size    : 0x110 bytes
 * ------------------------------------------------------------------ */

undefined8 copy_eai_meta_tensor(long param_1)

{
  long lVar1;
  int iVar2;
  uint uVar3;
  long lVar4;
  long lVar5;
  
  lVar5 = *(long *)(param_1 + 8);
  if (*(uint *)(lVar5 + 0x1c) == 0) {
    return 1;
  }
  lVar4 = 0;
  while (lVar1 = lVar5 + lVar4, *(int *)(lVar1 + 0x28) != 1) {
    lVar4 = lVar4 + 0x10;
    if ((ulong)*(uint *)(lVar5 + 0x1c) * 0x10 - lVar4 == 0) {
      return 1;
    }
  }
  if (lVar5 == 0) {
    return 1;
  }
  iVar2 = *(int *)(lVar1 + 0x2c);
  if (iVar2 == 0) {
    return 1;
  }
  uVar3 = *(uint *)(lVar1 + 0x30);
  lVar4 = allocate_memory(iVar2,*(undefined4 *)(param_1 + 0x124),
                          param_1 + (ulong)(*(uint *)(param_1 + 0x98) & 1) * 0x14 + 0xf8);
  if (lVar4 != 0) {
    memscpy(lVar4,iVar2,lVar5 + (ulong)uVar3,iVar2);
    *(long *)(param_1 + 0x20) = lVar4;
    *(int *)(param_1 + 0x44) = iVar2;
    return 0;
  }
  return 8;
}



/* ------------------------------------------------------------------
 * Function: extract_eai_model_from_buffer
 * Address : 0010e91c
 * Size    : 0xa8 bytes
 * ------------------------------------------------------------------ */

int * extract_eai_model_from_buffer(int *param_1,uint *param_2)

{
  uint uVar1;
  
  if ((param_1 != (int *)0x0) && (param_2 != (uint *)0x0)) {
    uVar1 = *param_2;
    while( true ) {
      if (uVar1 < 4) {
        *param_2 = uVar1;
        __android_log_print(6,&DAT_00101ae3,"Invalid data size %u\n");
        return (int *)0x0;
      }
      if (*param_1 == 0x32696165) break;
      param_1 = param_1 + 1;
      uVar1 = uVar1 - 4;
    }
    *param_2 = uVar1;
    return param_1;
  }
  __android_log_print(6,&DAT_00101ae3,"Buffer is null or invalid buffer size\n");
  return (int *)0x0;
}



/* ------------------------------------------------------------------
 * Function: calculate_total_layer_size
 * Address : 0010ed9c
 * Size    : 0x1fc bytes
 * ------------------------------------------------------------------ */

int calculate_total_layer_size(undefined8 param_1,uint *param_2,uint param_3,int param_4)

{
  uint uVar1;
  int iVar2;
  int iVar3;
  uint uVar4;
  int iVar5;
  uint uVar6;
  
  iVar5 = param_3 * 8 + 8;
  if ((param_3 != 0) && (param_4 != 0)) {
    uVar6 = 1;
    do {
      uVar1 = param_2[1];
      iVar2 = get_layer_tensor_count(param_1,1,uVar6 - 1);
      iVar3 = get_layer_tensor_count(param_1,0,uVar6 - 1);
      if (0x46 < *param_2) {
code_r0x0010ef7c:
        __android_log_print(6,&DAT_00101ae3,"Invalid layer type: %d.");
        return 0;
      }
      iVar5 = iVar5 + iVar2 * 8 + iVar3 * 8;
      switch(*param_2) {
      case 0:
        iVar5 = iVar5 + 0xd8;
        break;
      default:
        goto code_r0x0010ef7c;
      case 2:
      case 0x14:
      case 0x19:
      case 0x21:
      case 0x27:
      case 0x2a:
      case 0x2b:
      case 0x2f:
      case 0x3b:
        iVar5 = iVar5 + 0x70;
        break;
      case 3:
      case 0xf:
      case 0x17:
      case 0x20:
      case 0x3f:
      case 0x40:
      case 0x41:
        iVar5 = iVar5 + 0x98;
        break;
      case 4:
      case 0x15:
      case 0x1a:
      case 0x1b:
      case 0x1e:
      case 0x29:
        iVar5 = iVar5 + 0x78;
        break;
      case 5:
      case 8:
      case 10:
      case 0x35:
        iVar5 = iVar5 + 0xd0;
        break;
      case 7:
      case 0xe:
      case 0x11:
      case 0x12:
      case 0x24:
      case 0x25:
        iVar5 = iVar5 + 0xa0;
        break;
      case 0xc:
        iVar5 = iVar5 + param_2[1] + 0x70;
        break;
      case 0x10:
        iVar5 = iVar5 + 200;
        break;
      case 0x18:
        iVar5 = iVar5 + 0xc0;
        break;
      case 0x1c:
        iVar5 = iVar5 + 0xb8;
        break;
      case 0x1d:
        uVar4 = param_2[4] * 0x14;
        iVar2 = uVar4 + 8;
        if ((uVar4 & 4) != 0) {
          iVar2 = (uVar4 & 0xfffffff8) + 0x10;
        }
        iVar5 = iVar5 + param_2[4] * 0x28 + iVar2 + 0x88;
        break;
      case 0x22:
      case 0x26:
      case 0x34:
      case 0x36:
      case 0x37:
      case 0x44:
        iVar5 = iVar5 + 0x80;
        break;
      case 0x23:
        iVar5 = iVar5 + 0xb0;
        break;
      case 0x32:
      case 0x3a:
      case 0x3e:
        iVar5 = iVar5 + 0xa8;
        break;
      case 0x39:
        iVar5 = iVar5 + 0xf8;
        break;
      case 0x3d:
      case 0x45:
      case 0x46:
        iVar5 = iVar5 + 0xe8;
        break;
      case 0x43:
        iVar5 = iVar5 + 0x108;
      }
      if (param_3 <= uVar6) {
        return iVar5;
      }
      param_4 = param_4 - uVar1;
      uVar6 = uVar6 + 1;
      param_2 = (uint *)((long)param_2 + (long)(int)param_2[1]);
    } while (param_4 != 0);
  }
  return iVar5;
}



/* ------------------------------------------------------------------
 * Function: so_op_intf_load2
 * Address : 0010ef98
 * Size    : 0x1dc bytes
 * ------------------------------------------------------------------ */

undefined8 so_op_intf_load2(undefined4 *param_1)

{
  undefined8 uVar1;
  
  switch(*param_1) {
  case 0:
    uVar1 = conv_load2();
    return uVar1;
  default:
    return 1;
  case 2:
    uVar1 = relu_load2();
    return uVar1;
  case 3:
    uVar1 = logsoftmax_load2();
    return uVar1;
  case 4:
    uVar1 = maxout_load2();
    return uVar1;
  case 5:
    uVar1 = maxpool_load2();
    return uVar1;
  case 7:
    uVar1 = reshape_load2();
    return uVar1;
  case 8:
    uVar1 = lstm_load2();
    return uVar1;
  case 10:
    uVar1 = gru_load2();
    return uVar1;
  case 0xc:
    uVar1 = transpose_load2();
    return uVar1;
  case 0xe:
    uVar1 = batchnormalize_load2();
    return uVar1;
  case 0xf:
    uVar1 = quantize_load2();
    return uVar1;
  case 0x10:
    uVar1 = add_load2();
    return uVar1;
  case 0x11:
    uVar1 = matmul_load2();
    return uVar1;
  case 0x12:
    uVar1 = div_load2();
    return uVar1;
  case 0x14:
    uVar1 = no_op_load2();
    return uVar1;
  case 0x15:
    uVar1 = reducemean_load2();
    return uVar1;
  case 0x17:
    uVar1 = framer_load2();
    return uVar1;
  case 0x18:
    uVar1 = slice_load2();
    return uVar1;
  case 0x19:
    uVar1 = shape_load2();
    return uVar1;
  case 0x1a:
    uVar1 = squeeze_load2();
    return uVar1;
  case 0x1b:
    uVar1 = unsqueeze_load2();
    return uVar1;
  case 0x1c:
    uVar1 = mul_load2();
    return uVar1;
  case 0x1d:
    uVar1 = max_load2();
    return uVar1;
  case 0x1e:
    uVar1 = concat_load2();
    return uVar1;
  case 0x20:
    uVar1 = softmax_load2();
    return uVar1;
  case 0x21:
    uVar1 = tanh_load2();
    return uVar1;
  case 0x22:
    uVar1 = reduce_load2();
    return uVar1;
  case 0x23:
    uVar1 = conv1d_load2();
    return uVar1;
  case 0x24:
    uVar1 = sub_load2();
    return uVar1;
  case 0x25:
    uVar1 = pow_load2();
    return uVar1;
  case 0x26:
    uVar1 = clip_load2();
    return uVar1;
  case 0x27:
    uVar1 = sigmoid_load2();
    return uVar1;
  case 0x29:
    uVar1 = gather_load2();
    return uVar1;
  case 0x2a:
    uVar1 = log_load2();
    return uVar1;
  case 0x2b:
    uVar1 = sqrt_load2();
    return uVar1;
  case 0x2f:
    uVar1 = exp_load2();
    return uVar1;
  case 0x32:
    uVar1 = gemm_load2();
    return uVar1;
  case 0x34:
    uVar1 = globalavgpool_load2();
    return uVar1;
  case 0x35:
    uVar1 = averagepool_load2();
    return uVar1;
  case 0x36:
    uVar1 = argmax_load2();
    return uVar1;
  case 0x37:
    uVar1 = argmin_load2();
    return uVar1;
  case 0x39:
    uVar1 = convtranspose_load2();
    return uVar1;
  case 0x3a:
    uVar1 = pad_load2();
    return uVar1;
  case 0x3b:
    uVar1 = prelu_load2();
    return uVar1;
  case 0x3d:
    uVar1 = superconv_load2();
    return uVar1;
  case 0x3e:
    uVar1 = supergemm_load2();
    return uVar1;
  case 0x3f:
    uVar1 = superadd_load2();
    return uVar1;
  case 0x40:
    uVar1 = supersub_load2();
    return uVar1;
  case 0x41:
    uVar1 = supermul_load2();
    return uVar1;
  case 0x43:
    uVar1 = superdeconv_load2();
    return uVar1;
  case 0x44:
    uVar1 = flowcontrol_load2();
    return uVar1;
  case 0x45:
    uVar1 = superavgpool_load2();
    return uVar1;
  case 0x46:
    uVar1 = supermaxpool_load2();
    return uVar1;
  }
}



