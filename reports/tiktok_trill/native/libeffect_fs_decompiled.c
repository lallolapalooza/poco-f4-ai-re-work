/*
 * Ghidra headless decompilation
 * program   : libeffect.so
 * executable: /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_trill/native/libeffect.so
 * language  : AARCH64:LE:64:v8A
 * imagebase : 00100000
 * regex     : Java_com_bef_effectsdk_BEFEffectNative_nativeProcessFrame|Java_com_bef_effectsdk_BEFEffectNative_nativeProcessAlgorithm|FUN_(000)?(68bc04|458d08|696268|68b354|697a2c|697ab4)|bef_effect_process_texture_with_loading_timeout
 * max funcs : 24
 * note      : pseudocode from a (possibly stripped) binary is approximate;
 *             indirect calls and register-indirect JNI tables are lost.
 */

/* ------------------------------------------------------------------
 * Function: bef_effect_process_texture_with_loading_timeout
 * Address : 003c63f0
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void bef_effect_process_texture_with_loading_timeout(void)

{
  (*(code *)PTR_bef_effect_process_texture_with_loading_timeout_0133a4f0)();
  return;
}



/* ------------------------------------------------------------------
 * Function: Java_com_bef_effectsdk_BEFEffectNative_nativeProcessAlgorithm
 * Address : 003eabd4
 * Size    : 0x94 bytes
 * ------------------------------------------------------------------ */

void Java_com_bef_effectsdk_BEFEffectNative_nativeProcessAlgorithm
               (undefined8 param_1,undefined8 param_2,undefined8 param_3,undefined8 param_4,
               undefined4 param_5,undefined4 param_6)

{
  undefined1 in_ZR;
  undefined8 uVar1;
  undefined1 extraout_w8;
  long unaff_x23;
  undefined8 uVar2;
  undefined1 local_b0 [8];
  undefined4 uStack_a8;
  undefined4 uStack_a4;
  undefined1 auStack_a0 [16];
  undefined1 local_90 [16];
  undefined8 local_80;
  undefined1 uStack_78;
  undefined7 uStack_77;
  undefined1 auStack_70 [16];
  undefined1 local_60 [16];
  undefined8 local_50;
  
  uVar2 = FUN_00bd5bec();
  FUN_00bc8904(param_3,param_5,param_6);
  bef_effect_set_width_height();
  uVar1 = FUN_00ba5258();
  auStack_a0 = FUN_00bbadc4(uVar1,local_b0);
  local_50 = 0;
  _uStack_78 = CONCAT71(auStack_a0._9_7_,extraout_w8);
  _uStack_a8 = CONCAT44(auStack_a0._12_4_,param_6);
  local_80 = uVar2;
  local_90 = auStack_a0;
  auStack_70 = auStack_a0;
  local_60 = auStack_a0;
  bef_effect_algorithm_multi_texture_with_params();
  FUN_00b8cc44(*(undefined8 *)(unaff_x23 + 0x28));
  if ((bool)in_ZR) {
    FUN_00b8dd70();
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: Java_com_bef_effectsdk_BEFEffectNative_nativeProcessFrame
 * Address : 003eac68
 * Size    : 0x40 bytes
 * ------------------------------------------------------------------ */

void Java_com_bef_effectsdk_BEFEffectNative_nativeProcessFrame
               (undefined8 param_1,undefined8 param_2,undefined8 param_3,undefined8 param_4,
               undefined8 param_5,undefined8 param_6,undefined4 param_7,undefined4 param_8)

{
  FUN_00bf58d4(param_4,param_7,param_8);
  bef_effect_set_width_height();
  FUN_00b9f91c();
  bef_effect_process_texture(param_1);
  return;
}



/* ------------------------------------------------------------------
 * Function: bef_effect_process_texture_with_loading_timeout
 * Address : 004097f8
 * Size    : 0xc4 bytes
 * ------------------------------------------------------------------ */

void bef_effect_process_texture_with_loading_timeout(void)

{
  undefined4 uVar1;
  long lVar2;
  undefined8 uVar3;
  char *pcVar4;
  int in_w3;
  undefined4 unaff_w19;
  undefined1 auVar5 [16];
  long *in_stack_00000010;
  
  lVar2 = FUN_00ba6bf8();
  if (lVar2 == 0) {
    uVar1 = 0xfffffffb;
  }
  else {
    FUN_00c0a47c(&DAT_0139a000);
    uVar3 = FUN_00bb1c7c();
    if (in_stack_00000010 == (long *)0x0) {
      uVar1 = 0xfffffffa;
    }
    else {
      FUN_00b8db28(uVar3,in_stack_00000010[0x1f]);
      if (in_w3 != 0) {
        auVar5 = FUN_00b476ac();
        pcVar4 = "icker_set_pin_selected_area";
        auVar5 = FUN_00b959d0(auVar5._0_8_,auVar5._8_8_,"icker_set_pin_selected_area");
        uVar3 = FUN_00bd2944(auVar5._0_8_,auVar5._8_8_,pcVar4 + 0xf2c);
        FUN_00b478ec(uVar3,3);
        FUN_00c0fad0(*(undefined8 *)(*in_stack_00000010 + 0xa28));
      }
      auVar5 = FUN_00bb24f0(*(undefined8 *)(*in_stack_00000010 + 0x530));
      uVar1 = FUN_00bb6aa0(auVar5._0_8_,auVar5._8_8_,0,unaff_w19);
      FUN_00b8cefc();
    }
    FUN_00b8df30();
  }
  FUN_00b8b554(uVar1);
  return;
}



