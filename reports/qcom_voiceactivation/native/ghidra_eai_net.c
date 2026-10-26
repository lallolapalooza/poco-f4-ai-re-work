/*
 * Ghidra headless decompilation
 * program   : libeai_floating.so
 * executable: /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libeai_floating.so
 * language  : AARCH64:LE:64:v8A
 * imagebase : 00100000
 * regex     : load_network|op_layer|layer_load|load_layer|parse_
 * max funcs : 25
 * note      : pseudocode from a (possibly stripped) binary is approximate;
 *             indirect calls and register-indirect JNI tables are lost.
 */

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



