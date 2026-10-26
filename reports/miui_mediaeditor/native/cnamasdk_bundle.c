/*
 * Ghidra headless decompilation
 * program   : libCNamaSDK.so
 * executable: /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_mediaeditor/work/x/lib/arm64-v8a/libCNamaSDK.so
 * language  : AARCH64:LE:64:v8A
 * imagebase : 00100000
 * regex     : DecryptObfuscatedPackage.*|ContainMagicCode|GetBundleType|BundleHelper.*|FUN_0056f2c4|.*AuthUtil.*
 * max funcs : 40
 * note      : pseudocode from a (possibly stripped) binary is approximate;
 *             indirect calls and register-indirect JNI tables are lost.
 */

/* ------------------------------------------------------------------
 * Function: nama::AuthUtil::AuthUtil
 * Address : 0066db28
 * Size    : 0x4 bytes
 * ------------------------------------------------------------------ */

/* nama::AuthUtil::AuthUtil() */

void __thiscall nama::AuthUtil::AuthUtil(AuthUtil *this)

{
  return;
}



/* ------------------------------------------------------------------
 * Function: nama::AuthUtil::~AuthUtil
 * Address : 0066db2c
 * Size    : 0x4 bytes
 * ------------------------------------------------------------------ */

/* nama::AuthUtil::~AuthUtil() */

void __thiscall nama::AuthUtil::~AuthUtil(AuthUtil *this)

{
  return;
}



/* ------------------------------------------------------------------
 * Function: nama::AuthUtil::AuthCheck
 * Address : 0066db30
 * Size    : 0x470 bytes
 * ------------------------------------------------------------------ */

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */
/* nama::AuthUtil::AuthCheck(int) */

int nama::AuthUtil::AuthCheck(int param_1)

{
  undefined8 *puVar1;
  uint uVar2;
  long lVar3;
  undefined *puVar4;
  undefined *puVar5;
  int iVar6;
  int *piVar7;
  ulong uVar8;
  long lVar9;
  int iVar10;
  undefined8 uVar11;
  ulong local_c0;
  undefined8 uStack_b8;
  undefined8 *local_b0;
  char *local_a0;
  undefined4 local_98;
  char *local_90;
  char *local_88;
  undefined4 local_80;
  char *local_78;
  char *local_70;
  undefined4 local_68;
  char *local_60;
  char *local_58;
  undefined4 local_50;
  char *local_48;
  int local_40;
  undefined4 local_3c;
  long local_38;
  
  lVar3 = tpidr_el0;
  local_38 = *(long *)(lVar3 + 0x28);
  local_40 = fuauth_get_palette(0);
  local_3c = fuauth_get_palette(1);
  piVar7 = (int *)palette_translate(&local_40,param_1,0);
  puVar4 = PTR_js_flag_00ea3b48;
  iVar10 = *piVar7;
  iVar6 = piVar7[1];
  *(int *)PTR_js_flag_00ea3b48 = iVar6;
  if (iVar6 == 0 && iVar10 == 0) {
    if (((*PTR_s_instance_00ea2e58 & 1) == 0) &&
       (iVar6 = __cxa_guard_acquire(PTR_s_instance_00ea2e58), iVar6 != 0)) {
      fuspdlog::details::registry::registry((registry *)PTR_s_instance_00ea2e60);
                    /* try { // try from 0066def4 to 0076dfd7 has its CatchHandler @ 0066def4
                       catch() { ... } // from try @ 0066def4 with catch @ 0066def4
                       catch() { ... } // from try @ 0066dfe4 with catch @ 0066def4 */
      __cxa_atexit(PTR__registry_00ea2e68,PTR_s_instance_00ea2e60,&PTR_LOOP_00e7f8f0);
      __cxa_guard_release(PTR_s_instance_00ea2e58);
    }
    local_50 = 0x22;
    local_58 = "/root/CI/builds/eTmYD8xn/0/ruitaocai/CNamaSDK/src/common/AuthUtil.cc";
    local_48 = "AuthCheck";
    fuspdlog::logger::log<char[45],(char(*)[45])0>
              (*(logger **)(PTR_s_instance_00ea2e60 + 0xd0),&local_58,4,
               "invalid palette info and halt whole pipeline");
    iVar6 = *(int *)puVar4;
  }
  *(int *)PTR_palette_js_flag_00ea3b50 = iVar6;
  iVar6 = fuauth_version();
  if ((iVar6 != 0xd) &&
     (iVar6 = *(int *)PTR_auth_init_countdown_00ea3b58,
     *(int *)PTR_auth_init_countdown_00ea3b58 = iVar6 + -1, iVar6 < 1)) {
    iVar10 = 0;
    *(int *)PTR_cert_auth_failed_00ea3b60 = *(int *)PTR_cert_auth_failed_00ea3b60 + 1;
  }
  if (*PTR_debug_key_used_00ea3b68 != '\0') {
    iVar6 = *(int *)PTR_debug_key_countdown2_00ea3b70;
    *(int *)PTR_debug_key_countdown2_00ea3b70 = iVar6 + -1;
    if (iVar6 < 1) {
      iVar10 = 0;
      *(int *)PTR_debug_key_auth_failed_00ea3b78 = *(int *)PTR_debug_key_auth_failed_00ea3b78 + 1;
    }
    fuauth_intel_access();
    puVar5 = PTR_drm_warning_counter_00ea3b80;
    iVar6 = *(int *)PTR_drm_warning_counter_00ea3b80;
    *(int *)PTR_drm_warning_counter_00ea3b80 = iVar6 + -1;
    if (iVar6 < 1) {
      *(undefined4 *)puVar5 = 0x3c;
      iVar6 = fuauth_get_drm_warning();
      if (iVar6 == 2) {
        if (((*PTR_s_instance_00ea2e58 & 1) == 0) &&
           (iVar6 = __cxa_guard_acquire(PTR_s_instance_00ea2e58), iVar6 != 0)) {
          fuspdlog::details::registry::registry((registry *)PTR_s_instance_00ea2e60);
          __cxa_atexit(PTR__registry_00ea2e68,PTR_s_instance_00ea2e60,&PTR_LOOP_00e7f8f0);
          __cxa_guard_release(PTR_s_instance_00ea2e58);
        }
        local_80 = 0x3f;
        local_88 = "/root/CI/builds/eTmYD8xn/0/ruitaocai/CNamaSDK/src/common/AuthUtil.cc";
        local_78 = "AuthCheck";
        fuspdlog::logger::log<char[67],(char(*)[67])0>
                  (*(logger **)(PTR_s_instance_00ea2e60 + 0xd0),&local_88,4,
                   "This is a FaceUnity test license, DO NOT USE IT IN A REAL PRODUCT!");
      }
      else if (iVar6 == 1) {
        if (((*PTR_s_instance_00ea2e58 & 1) == 0) &&
           (iVar6 = __cxa_guard_acquire(PTR_s_instance_00ea2e58), iVar6 != 0)) {
          fuspdlog::details::registry::registry((registry *)PTR_s_instance_00ea2e60);
          __cxa_atexit(PTR__registry_00ea2e68,PTR_s_instance_00ea2e60,&PTR_LOOP_00e7f8f0);
          __cxa_guard_release(PTR_s_instance_00ea2e58);
        }
        local_68 = 0x3a;
        local_70 = "/root/CI/builds/eTmYD8xn/0/ruitaocai/CNamaSDK/src/common/AuthUtil.cc";
        local_60 = "AuthCheck";
        fuspdlog::logger::log<char[36],(char(*)[36])0>
                  (*(logger **)(PTR_s_instance_00ea2e60 + 0xd0),&local_70,4,
                   "YOUR FACEUNITY LICENSE HAS EXPIRED!");
      }
    }
  }
  uVar2 = *(uint *)puVar4;
  Log::Instance();
  if (((byte)PTR_m_log_modules_00ea2e50[1] >> 4 & 1) != 0) {
    if (((*PTR_s_instance_00ea2e58 & 1) == 0) &&
       (iVar6 = __cxa_guard_acquire(PTR_s_instance_00ea2e58), iVar6 != 0)) {
      fuspdlog::details::registry::registry((registry *)PTR_s_instance_00ea2e60);
      __cxa_atexit(PTR__registry_00ea2e68,PTR_s_instance_00ea2e60,&PTR_LOOP_00e7f8f0);
      __cxa_guard_release(PTR_s_instance_00ea2e58);
    }
    uVar11 = *(undefined8 *)(PTR_s_instance_00ea2e60 + 0xd0);
    local_98 = 0x44;
    local_a0 = "/root/CI/builds/eTmYD8xn/0/ruitaocai/CNamaSDK/src/common/AuthUtil.cc";
    local_90 = "AuthCheck";
    local_b0 = operator_new(0x30);
    uStack_b8 = _UNK_0046d7b8;
    local_c0 = _DAT_0046d7b0;
    uVar8 = 0;
    lVar9 = 0x1f;
    *(undefined1 *)(local_b0 + 4) = 0;
    local_b0[1] = 0x3030303030303030;
    *local_b0 = 0x3030303030303030;
    local_b0[3] = 0x3030303030303030;
    local_b0[2] = 0x3030303030303030;
    do {
      if ((uVar2 >> (uVar8 & 0x3f) & 1) != 0) {
        puVar1 = (undefined8 *)((ulong)&local_c0 | 1);
        if ((local_c0 & 1) != 0) {
          puVar1 = local_b0;
        }
        *(undefined1 *)((long)puVar1 + lVar9) = 0x31;
      }
      uVar8 = uVar8 + 1;
      lVar9 = lVar9 + -1;
    } while (uVar8 != 0x20);
    fuspdlog::logger::
    log<int,std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>>
              (uVar11,&local_a0,1,"js_flag:{}-{}",0xd,PTR_js_flag_00ea3b48,&local_c0);
    if ((local_c0 & 1) != 0) {
      operator_delete(local_b0);
    }
  }
  if (*(long *)(lVar3 + 0x28) == local_38) {
    return iVar10;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: nama::AuthUtil::PrintAuthInfo
 * Address : 0066e150
 * Size    : 0x1dc bytes
 * ------------------------------------------------------------------ */

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */
/* nama::AuthUtil::PrintAuthInfo() */

void nama::AuthUtil::PrintAuthInfo(void)

{
  bool bVar1;
  undefined8 *puVar2;
  long lVar3;
  ulong uVar4;
  undefined8 uVar5;
  undefined *puVar6;
  undefined *this;
  uint uVar7;
  int iVar8;
  ulong uVar9;
  long lVar10;
  undefined8 uVar11;
  ulong local_a0;
  undefined8 uStack_98;
  undefined8 *local_90;
  char *local_88;
  undefined4 local_80;
  char *local_78;
  uint local_70;
  int local_6c;
  long local_68;
  
  this = PTR_s_instance_00ea2e60;
  puVar6 = PTR_m_log_modules_00ea2e50;
  uVar5 = _UNK_0046d7b8;
  uVar4 = _DAT_0046d7b0;
  lVar3 = tpidr_el0;
  local_68 = *(long *)(lVar3 + 0x28);
  local_6c = 0;
  do {
    uVar7 = fuauth_get_module_code(local_6c);
    local_70 = uVar7;
    Log::Instance();
    if (((byte)puVar6[1] >> 4 & 1) != 0) {
      if (((*PTR_s_instance_00ea2e58 & 1) == 0) &&
         (iVar8 = __cxa_guard_acquire(PTR_s_instance_00ea2e58), iVar8 != 0)) {
        fuspdlog::details::registry::registry((registry *)this);
        __cxa_atexit(PTR__registry_00ea2e68,this,&PTR_LOOP_00e7f8f0);
        __cxa_guard_release(PTR_s_instance_00ea2e58);
      }
      uVar11 = *(undefined8 *)(this + 0xd0);
      local_88 = "/root/CI/builds/eTmYD8xn/0/ruitaocai/CNamaSDK/src/common/AuthUtil.cc";
      local_80 = 0x74;
      local_78 = "PrintAuthInfo";
      local_90 = operator_new(0x30);
      uVar9 = 0;
      lVar10 = 0x1f;
      uStack_98 = uVar5;
      local_a0 = uVar4;
      local_90[1] = 0x3030303030303030;
      *local_90 = 0x3030303030303030;
      local_90[3] = 0x3030303030303030;
      local_90[2] = 0x3030303030303030;
      *(undefined1 *)(local_90 + 4) = 0;
      do {
        if ((uVar7 >> (uVar9 & 0x3f) & 1) != 0) {
          puVar2 = (undefined8 *)((ulong)&local_a0 | 1);
          if ((uVar4 & 1) != 0) {
            puVar2 = local_90;
          }
          *(undefined1 *)((long)puVar2 + lVar10) = 0x31;
        }
        uVar9 = uVar9 + 1;
        lVar10 = lVar10 + -1;
      } while (uVar9 != 0x20);
      fuspdlog::logger::
      log<int,unsigned_int,std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>>
                (uVar11,&local_88,1,"module[{}]:{}-{}",0x10,&local_6c,&local_70,&local_a0);
      if ((local_a0 & 1) != 0) {
        operator_delete(local_90);
      }
    }
    iVar8 = local_6c + 1;
    bVar1 = local_6c < 4;
    local_6c = iVar8;
  } while (bVar1);
  if (*(long *)(lVar3 + 0x28) == local_68) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: nama::AuthUtil::CheckAccess
 * Address : 0066e700
 * Size    : 0x420 bytes
 * ------------------------------------------------------------------ */

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */
/* nama::AuthUtil::CheckAccess() */

void nama::AuthUtil::CheckAccess(void)

{
  undefined8 *puVar1;
  long lVar2;
  undefined1 auVar3 [16];
  undefined1 auVar4 [16];
  undefined *puVar5;
  undefined *puVar6;
  uint uVar7;
  int iVar8;
  Log *pLVar9;
  ulong uVar10;
  long lVar11;
  undefined8 uVar12;
  undefined1 auVar13 [16];
  undefined1 auVar14 [16];
  undefined1 auVar15 [16];
  undefined1 auVar16 [16];
  undefined1 auVar17 [16];
  undefined1 auVar18 [16];
  undefined1 auVar19 [16];
  undefined1 auVar20 [16];
  undefined1 auVar21 [16];
  undefined1 auVar22 [16];
  undefined1 auVar23 [16];
  undefined1 auVar24 [16];
  byte bVar25;
  undefined1 uVar26;
  undefined1 uVar27;
  undefined1 uVar28;
  char *local_98;
  undefined4 local_90;
  char *local_88;
  ulong local_80;
  undefined8 uStack_78;
  undefined8 *local_70;
  char *local_68;
  undefined4 local_60;
  char *local_58;
  uint local_4c;
  long local_48;
  
  lVar2 = tpidr_el0;
  local_48 = *(long *)(lVar2 + 0x28);
  PrintAuthInfo();
  puVar6 = PTR_makeup_access_00ea3b90;
  puVar5 = PTR_beautification_access_00ea3b88;
  *(undefined8 *)(PTR_beautification_access_00ea3b88 + 0xf) = 0xffffffffffffffff;
  *(undefined8 *)puVar5 = 0xffffffffffffffff;
  *(undefined8 *)(puVar5 + 8) = 0xffffffffffffffff;
  *(undefined8 *)puVar6 = 0xffffffffffffffff;
  *(undefined2 *)(puVar6 + 8) = 0xffff;
  uVar7 = fuauth_get_module_code(3);
  local_4c = uVar7;
  pLVar9 = (Log *)Log::Instance();
  uVar10 = Log::ShouldLog(pLVar9,1);
  if (((uVar10 & 1) != 0) && (Log::Instance(), ((byte)PTR_m_log_modules_00ea2e50[1] >> 4 & 1) != 0))
  {
    if (((*PTR_s_instance_00ea2e58 & 1) == 0) &&
       (iVar8 = __cxa_guard_acquire(PTR_s_instance_00ea2e58), iVar8 != 0)) {
      fuspdlog::details::registry::registry((registry *)PTR_s_instance_00ea2e60);
      __cxa_atexit(PTR__registry_00ea2e68,PTR_s_instance_00ea2e60,&PTR_LOOP_00e7f8f0);
      __cxa_guard_release(PTR_s_instance_00ea2e58);
    }
    uVar12 = *(undefined8 *)(PTR_s_instance_00ea2e60 + 0xd0);
    local_60 = 0x83;
    local_68 = "/root/CI/builds/eTmYD8xn/0/ruitaocai/CNamaSDK/src/common/AuthUtil.cc";
    local_58 = "CheckAccess";
    local_70 = operator_new(0x30);
    auVar14 = _DAT_0046d7b0;
    uVar10 = 0;
    auVar18[8] = 0x30;
    auVar18._0_8_ = 0x3030303030303030;
    auVar18[9] = 0x30;
    auVar18[10] = 0x30;
    auVar18[0xb] = 0x30;
    auVar18[0xc] = 0x30;
    auVar18[0xd] = 0x30;
    auVar18[0xe] = 0x30;
    auVar18[0xf] = 0x30;
    lVar11 = 0x1f;
    *(undefined1 *)(local_70 + 4) = 0;
    local_70[1] = auVar18._8_8_;
    *local_70 = 0x3030303030303030;
    local_70[3] = auVar18._8_8_;
    local_70[2] = 0x3030303030303030;
    uStack_78 = auVar14._8_8_;
    do {
      if ((uVar7 >> (uVar10 & 0x3f) & 1) != 0) {
        local_80._0_1_ = auVar14[0];
        puVar1 = (undefined8 *)((ulong)&local_80 | 1);
        if (((byte)local_80 & 1) != 0) {
          puVar1 = local_70;
        }
        *(undefined1 *)((long)puVar1 + lVar11) = 0x31;
      }
      uVar10 = uVar10 + 1;
      lVar11 = lVar11 + -1;
    } while (uVar10 != 0x20);
    local_80 = auVar14._0_8_;
    fuspdlog::logger::
    log<unsigned_int,std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>>
              (uVar12,&local_68,1,"beautification access code:{}-{}",0x20,&local_4c,&local_80);
    uVar7 = local_4c;
    if ((local_80 & 1) != 0) {
      operator_delete(local_70);
      uVar7 = local_4c;
    }
  }
  if (uVar7 != 0) {
    bVar25 = (byte)uVar7;
    uVar26 = (undefined1)(uVar7 >> 8);
    uVar27 = (undefined1)(uVar7 >> 0x10);
    uVar28 = (undefined1)(uVar7 >> 0x18);
    auVar24._0_4_ = -_DAT_0046d750;
    auVar24._4_4_ = -_UNK_0046d754;
    auVar24._8_4_ = -_UNK_0046d758;
    auVar24._12_4_ = -_UNK_0046d75c;
    auVar23._0_4_ = -_DAT_0046d760;
    auVar23._4_4_ = -_UNK_0046d764;
    auVar23._8_4_ = -_UNK_0046d768;
    auVar23._12_4_ = -_UNK_0046d76c;
    auVar13._0_4_ = -_DAT_0046d6b0;
    auVar13._4_4_ = -_UNK_0046d6b4;
    auVar13._8_4_ = -_UNK_0046d6b8;
    auVar13._12_4_ = -_UNK_0046d6bc;
    auVar14[4] = bVar25;
    auVar14._0_4_ = uVar7;
    auVar14[5] = uVar26;
    auVar14[6] = uVar27;
    auVar14[7] = uVar28;
    auVar14[8] = bVar25;
    auVar14[9] = uVar26;
    auVar14[10] = uVar27;
    auVar14[0xb] = uVar28;
    auVar14[0xc] = bVar25;
    auVar14[0xd] = uVar26;
    auVar14[0xe] = uVar27;
    auVar14[0xf] = uVar28;
    auVar18 = NEON_ushl(auVar14,auVar24,4);
    auVar20._0_4_ = -_DAT_0046cff0;
    auVar20._4_4_ = -_UNK_0046cff4;
    auVar20._8_4_ = -_UNK_0046cff8;
    auVar20._12_4_ = -_UNK_0046cffc;
    auVar21[4] = bVar25;
    auVar21._0_4_ = uVar7;
    auVar21[5] = uVar26;
    auVar21[6] = uVar27;
    auVar21[7] = uVar28;
    auVar21[8] = bVar25;
    auVar21[9] = uVar26;
    auVar21[10] = uVar27;
    auVar21[0xb] = uVar28;
    auVar21[0xc] = bVar25;
    auVar21[0xd] = uVar26;
    auVar21[0xe] = uVar27;
    auVar21[0xf] = uVar28;
    auVar24 = NEON_ushl(auVar21,auVar23,4);
    auVar3[4] = bVar25;
    auVar3._0_4_ = uVar7;
    auVar3[5] = uVar26;
    auVar3[6] = uVar27;
    auVar3[7] = uVar28;
    auVar3[8] = bVar25;
    auVar3[9] = uVar26;
    auVar3[10] = uVar27;
    auVar3[0xb] = uVar28;
    auVar3[0xc] = bVar25;
    auVar3[0xd] = uVar26;
    auVar3[0xe] = uVar27;
    auVar3[0xf] = uVar28;
    auVar14 = NEON_ushl(auVar3,auVar13,4);
    auVar4[4] = bVar25;
    auVar4._0_4_ = uVar7;
    auVar4[5] = uVar26;
    auVar4[6] = uVar27;
    auVar4[7] = uVar28;
    auVar4[8] = bVar25;
    auVar4[9] = uVar26;
    auVar4[10] = uVar27;
    auVar4[0xb] = uVar28;
    auVar4[0xc] = bVar25;
    auVar4[0xd] = uVar26;
    auVar4[0xe] = uVar27;
    auVar4[0xf] = uVar28;
    auVar21 = NEON_ushl(auVar4,auVar20,4);
    *puVar5 = bVar25 & 1;
    puVar5[0x11] = (byte)(uVar7 >> 0x11) & 1;
    puVar5[0x12] = (byte)(uVar7 >> 0x12) & 1;
    puVar5[0x13] = (byte)(uVar7 >> 0x13) & 1;
    puVar5[0x14] = (byte)(uVar7 >> 0x14) & 1;
    auVar15._0_8_ =
         CONCAT17(auVar14[0xc],
                  CONCAT16(auVar14[8],
                           CONCAT15(auVar14[4],
                                    CONCAT14(auVar14[0],
                                             CONCAT13(auVar18[0xc],
                                                      CONCAT12(auVar18[8],
                                                               CONCAT11(auVar18[4],auVar18[0])))))))
         & 0x101010101010101;
    auVar15[8] = auVar24[0] & 1;
    auVar15[9] = auVar24[4] & 1;
    auVar15[10] = auVar24[8] & 1;
    auVar15[0xb] = auVar24[0xc] & 1;
    auVar15[0xc] = auVar21[0] & 1;
    auVar15[0xd] = auVar21[4] & 1;
    auVar15[0xe] = auVar21[8] & 1;
    auVar15[0xf] = auVar21[0xc] & 1;
    puVar5[0x15] = (byte)(uVar7 >> 0x15) & 1;
    *(long *)(puVar5 + 9) = auVar15._8_8_;
    *(ulong *)(puVar5 + 1) = auVar15._0_8_;
    puVar5[0x16] = (byte)(uVar7 >> 0x16) & 1;
  }
  local_4c = fuauth_get_module_code(2);
  pLVar9 = (Log *)Log::Instance();
  uVar10 = Log::ShouldLog(pLVar9,1);
  uVar7 = local_4c;
  if (((uVar10 & 1) != 0) && (Log::Instance(), ((byte)PTR_m_log_modules_00ea2e50[1] >> 4 & 1) != 0))
  {
    if (((*PTR_s_instance_00ea2e58 & 1) == 0) &&
       (iVar8 = __cxa_guard_acquire(PTR_s_instance_00ea2e58), iVar8 != 0)) {
      fuspdlog::details::registry::registry((registry *)PTR_s_instance_00ea2e60);
      __cxa_atexit(PTR__registry_00ea2e68,PTR_s_instance_00ea2e60,&PTR_LOOP_00e7f8f0);
      __cxa_guard_release(PTR_s_instance_00ea2e58);
    }
    uVar12 = *(undefined8 *)(PTR_s_instance_00ea2e60 + 0xd0);
    local_90 = 0xa7;
    local_98 = "/root/CI/builds/eTmYD8xn/0/ruitaocai/CNamaSDK/src/common/AuthUtil.cc";
    local_88 = "CheckAccess";
    local_70 = operator_new(0x30);
    auVar14 = _DAT_0046d7b0;
    uVar10 = 0;
    auVar16[8] = 0x30;
    auVar16._0_8_ = 0x3030303030303030;
    auVar16[9] = 0x30;
    auVar16[10] = 0x30;
    auVar16[0xb] = 0x30;
    auVar16[0xc] = 0x30;
    auVar16[0xd] = 0x30;
    auVar16[0xe] = 0x30;
    auVar16[0xf] = 0x30;
    lVar11 = 0x1f;
    *(undefined1 *)(local_70 + 4) = 0;
    local_70[1] = auVar16._8_8_;
    *local_70 = 0x3030303030303030;
    local_70[3] = auVar16._8_8_;
    local_70[2] = 0x3030303030303030;
    uStack_78 = auVar14._8_8_;
    do {
      if ((uVar7 >> (uVar10 & 0x3f) & 1) != 0) {
        local_80._0_1_ = auVar14[0];
        puVar1 = (undefined8 *)((ulong)&local_80 | 1);
        if (((byte)local_80 & 1) != 0) {
          puVar1 = local_70;
        }
        *(undefined1 *)((long)puVar1 + lVar11) = 0x31;
      }
      uVar10 = uVar10 + 1;
      lVar11 = lVar11 + -1;
    } while (uVar10 != 0x20);
    local_80 = auVar14._0_8_;
    fuspdlog::logger::
    log<unsigned_int,std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>>
              (uVar12,&local_98,1,"makeup access code:{}-{}",0x18,&local_4c,&local_80);
    if ((local_80 & 1) != 0) {
      operator_delete(local_70);
    }
  }
  if (local_4c != 0) {
    auVar22._4_4_ = local_4c;
    auVar22._0_4_ = local_4c;
    auVar22._8_4_ = local_4c;
    auVar22._12_4_ = local_4c;
    auVar19._0_4_ = -_DAT_0046d750;
    auVar19._4_4_ = -_UNK_0046d754;
    auVar19._8_4_ = -_UNK_0046d758;
    auVar19._12_4_ = -_UNK_0046d75c;
    auVar17._0_4_ = -_DAT_0046d6b0;
    auVar17._4_4_ = -_UNK_0046d6b4;
    auVar17._8_4_ = -_UNK_0046d6b8;
    auVar17._12_4_ = -_UNK_0046d6bc;
    auVar18 = NEON_ushl(auVar22,auVar19,4);
    auVar14 = NEON_ushl(auVar22,auVar17,4);
    *puVar6 = (byte)local_4c & 1;
    *(ulong *)(puVar6 + 1) =
         CONCAT17(auVar14[0xc],
                  CONCAT16(auVar14[8],
                           CONCAT15(auVar14[4],
                                    CONCAT14(auVar14[0],
                                             CONCAT13(auVar18[0xc],
                                                      CONCAT12(auVar18[8],
                                                               CONCAT11(auVar18[4],auVar18[0])))))))
         & 0x101010101010101;
    puVar6[9] = (byte)(local_4c >> 9) & 1;
  }
  if (*(long *)(lVar2 + 0x28) != local_48) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: nama::AuthUtil::DebugCountDown
 * Address : 0066eb60
 * Size    : 0x294 bytes
 * ------------------------------------------------------------------ */

/* nama::AuthUtil::DebugCountDown() */

void nama::AuthUtil::DebugCountDown(void)

{
  long lVar1;
  undefined *puVar2;
  undefined *puVar3;
  int iVar4;
  char *local_98;
  undefined4 local_90;
  char *local_88;
  char *local_80;
  undefined4 local_78;
  char *local_70;
  char *local_68;
  undefined4 local_60;
  char *local_58;
  int local_4c;
  long local_48;
  
  puVar3 = PTR_s_instance_00ea2e60;
  puVar2 = PTR_s_instance_00ea2e58;
  lVar1 = tpidr_el0;
  local_48 = *(long *)(lVar1 + 0x28);
  local_4c = *(int *)PTR_debug_key_countdown2_00ea3b70 + -1;
  if (((*PTR_debug_key_used_00ea3b68 != '\0') && (1 < *(int *)PTR_debug_key_countdown2_00ea3b70)) &&
     (local_4c % 100 == 0)) {
    if (((*PTR_s_instance_00ea2e58 & 1) == 0) &&
       (iVar4 = __cxa_guard_acquire(PTR_s_instance_00ea2e58), iVar4 != 0)) {
      fuspdlog::details::registry::registry((registry *)PTR_s_instance_00ea2e60);
      __cxa_atexit(PTR__registry_00ea2e68,PTR_s_instance_00ea2e60,&PTR_LOOP_00e7f8f0);
      __cxa_guard_release(PTR_s_instance_00ea2e58);
    }
    local_68 = "/root/CI/builds/eTmYD8xn/0/ruitaocai/CNamaSDK/src/common/AuthUtil.cc";
    local_60 = 0xbb;
    local_58 = "DebugCountDown";
    fuspdlog::logger::log<int>
              (*(undefined8 *)(puVar3 + 0xd0),&local_68,4,"debug item key used {} frames remaining."
               ,0x28,&local_4c);
  }
  if (*(int *)PTR_cert_auth_failed_00ea3b60 % 100 == 1) {
    if (((*puVar2 & 1) == 0) && (iVar4 = __cxa_guard_acquire(PTR_s_instance_00ea2e58), iVar4 != 0))
    {
      fuspdlog::details::registry::registry((registry *)PTR_s_instance_00ea2e60);
      __cxa_atexit(PTR__registry_00ea2e68,PTR_s_instance_00ea2e60,&PTR_LOOP_00e7f8f0);
      __cxa_guard_release(PTR_s_instance_00ea2e58);
    }
    local_80 = "/root/CI/builds/eTmYD8xn/0/ruitaocai/CNamaSDK/src/common/AuthUtil.cc";
    local_78 = 0xbf;
    local_70 = "DebugCountDown";
    fuspdlog::logger::log<char[55],(char(*)[55])0>
              (*(logger **)(puVar3 + 0xd0),&local_80,4,
               "certification authentication failed, rendering stopped");
  }
  if (*(int *)PTR_debug_key_auth_failed_00ea3b78 % 100 == 1) {
    if (((*puVar2 & 1) == 0) && (iVar4 = __cxa_guard_acquire(PTR_s_instance_00ea2e58), iVar4 != 0))
    {
      fuspdlog::details::registry::registry((registry *)PTR_s_instance_00ea2e60);
      __cxa_atexit(PTR__registry_00ea2e68,PTR_s_instance_00ea2e60,&PTR_LOOP_00e7f8f0);
      __cxa_guard_release(PTR_s_instance_00ea2e58);
    }
    local_98 = "/root/CI/builds/eTmYD8xn/0/ruitaocai/CNamaSDK/src/common/AuthUtil.cc";
    local_90 = 0xc2;
    local_88 = "DebugCountDown";
    fuspdlog::logger::log<char[45],(char(*)[45])0>
              (*(logger **)(puVar3 + 0xd0),&local_98,4,
               "unauthorized item is used, rendering stopped");
  }
  if (*(long *)(lVar1 + 0x28) == local_48) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: CNamaSDK::BundleHelper::BundleHelper
 * Address : 0066f0bc
 * Size    : 0xc bytes
 * ------------------------------------------------------------------ */

/* CNamaSDK::BundleHelper::BundleHelper() */

void __thiscall CNamaSDK::BundleHelper::BundleHelper(BundleHelper *this)

{
  *(undefined8 *)this = 0;
  *(undefined8 *)(this + 8) = 0;
  *(undefined8 *)(this + 0x10) = 0;
  return;
}



/* ------------------------------------------------------------------
 * Function: CNamaSDK::BundleHelper::~BundleHelper
 * Address : 0066f0c8
 * Size    : 0x14 bytes
 * ------------------------------------------------------------------ */

/* CNamaSDK::BundleHelper::~BundleHelper() */

void __thiscall CNamaSDK::BundleHelper::~BundleHelper(BundleHelper *this)

{
  if (((byte)*this & 1) == 0) {
    return;
  }
  operator_delete(*(void **)(this + 0x10));
  return;
}



/* ------------------------------------------------------------------
 * Function: CNamaSDK::BundleHelper::GetBundleType
 * Address : 0066f0dc
 * Size    : 0x5c bytes
 * ------------------------------------------------------------------ */

/* CNamaSDK::BundleHelper::GetBundleType(unsigned char const*, int, CNamaSDK::BundleType&) */

undefined8 CNamaSDK::BundleHelper::GetBundleType(uchar *param_1,int param_2,BundleType *param_3)

{
  *(undefined4 *)param_3 = 0;
  if (param_2 < 0x20) {
    return 0xffffffff;
  }
  if ((((*param_1 == 0xf3) && (param_1[1] == '[')) && (param_1[2] == '\x06')) &&
     (param_1[3] == '\x12')) {
    *(undefined4 *)param_3 = 1;
    return 0;
  }
  return 0;
}



/* ------------------------------------------------------------------
 * Function: CNamaSDK::BundleHelper::DecryptObfuscatedPackageTF
 * Address : 0066f138
 * Size    : 0x168 bytes
 * ------------------------------------------------------------------ */

/* CNamaSDK::BundleHelper::DecryptObfuscatedPackageTF(std::__ndk1::vector<unsigned char,
   std::__ndk1::allocator<unsigned char> > const&, std::__ndk1::vector<unsigned char,
   std::__ndk1::allocator<unsigned char> >&) */

undefined8 CNamaSDK::BundleHelper::DecryptObfuscatedPackageTF(vector *param_1,vector *param_2)

{
  long lVar1;
  long lVar2;
  void *pvVar3;
  int iVar4;
  void *__s;
  ulong uVar5;
  ulong uVar6;
  undefined8 uVar7;
  long lVar8;
  uchar local_74 [4];
  void *local_70;
  void *local_68;
  void *local_60;
  long local_58;
  
  lVar2 = tpidr_el0;
  local_58 = *(long *)(lVar2 + 0x28);
  lVar1 = *(long *)param_1;
  local_70 = (void *)0x0;
  local_68 = (void *)0x0;
  local_60 = (void *)0x0;
  lVar8 = *(long *)(param_1 + 8) - lVar1;
  uVar5 = lVar8 - 8;
  if (uVar5 == 0) {
    __s = (void *)0x0;
  }
  else {
    if ((long)uVar5 < 0) {
                    /* WARNING: Subroutine does not return */
      FUN_00d64f48(&local_70);
    }
    __s = operator_new(uVar5);
    local_70 = __s;
    local_60 = (void *)((long)__s + uVar5);
    memset(__s,0,uVar5);
    local_68 = (void *)((long)__s + uVar5);
  }
  pvVar3 = local_68;
  memcpy((void *)((long)__s + 0x10),(void *)(lVar1 + 0x18),lVar8 - 0x18);
  iVar4 = FUN_0066f2c4(__s,__s,(long)pvVar3 - (long)__s,*(undefined8 *)param_1,
                       *(undefined8 *)PTR__obfuscation_key_00ea3b98);
  local_74[0] = '\0';
  uVar5 = (long)pvVar3 + (-0x20 - (long)__s);
  uVar6 = *(long *)(param_2 + 8) - *(long *)param_2;
  if (uVar5 < uVar6 || uVar5 - uVar6 == 0) {
    if (uVar5 < uVar6) {
      *(ulong *)(param_2 + 8) = *(long *)param_2 + uVar5;
    }
  }
  else {
    std::__ndk1::vector<unsigned_char,std::__ndk1::allocator<unsigned_char>>::__append
              ((vector<unsigned_char,std::__ndk1::allocator<unsigned_char>> *)param_2,uVar5 - uVar6,
               local_74);
  }
  if (iVar4 == 0) {
    memcpy(*(void **)param_2,(void *)((long)local_70 + 0x20),
           *(long *)(param_2 + 8) - (long)*(void **)param_2);
    uVar7 = 0;
  }
  else {
    uVar7 = 0xfffffffe;
  }
  if (local_70 != (void *)0x0) {
    local_68 = local_70;
    operator_delete(local_70);
  }
  if (*(long *)(lVar2 + 0x28) == local_58) {
    return uVar7;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: CNamaSDK::BundleHelper::DecryptObfuscatedPackage
 * Address : 0066fd68
 * Size    : 0x3b8 bytes
 * ------------------------------------------------------------------ */

/* CNamaSDK::BundleHelper::DecryptObfuscatedPackage(unsigned char const*, int,
   std::__ndk1::vector<unsigned char, std::__ndk1::allocator<unsigned char> >&) */

undefined8
CNamaSDK::BundleHelper::DecryptObfuscatedPackage(uchar *param_1,int param_2,vector *param_3)

{
  byte bVar1;
  uint uVar2;
  uint uVar3;
  long lVar4;
  bool bVar5;
  int iVar6;
  void *pvVar7;
  void *__dest;
  size_t __n;
  byte *pbVar8;
  uint *puVar9;
  ulong uVar10;
  uint *puVar11;
  ulong uVar12;
  undefined8 uVar13;
  long lVar14;
  ulong uVar15;
  long lVar16;
  long lVar17;
  byte bVar18;
  byte bVar19;
  byte bVar20;
  byte bVar21;
  undefined8 uVar22;
  undefined8 uVar23;
  undefined8 uVar24;
  void *local_a0;
  void *local_98;
  undefined8 local_90;
  char *local_88;
  undefined4 local_80;
  char *local_78;
  int local_6c;
  long local_68;
  
  lVar4 = tpidr_el0;
  local_68 = *(long *)(lVar4 + 0x28);
  local_6c = param_2;
  nama::Log::Instance();
  if (((byte)PTR_m_log_modules_00ea2e50[1] >> 4 & 1) != 0) {
    if (((*PTR_s_instance_00ea2e58 & 1) == 0) &&
       (iVar6 = __cxa_guard_acquire(PTR_s_instance_00ea2e58), iVar6 != 0)) {
      fuspdlog::details::registry::registry((registry *)PTR_s_instance_00ea2e60);
      __cxa_atexit(PTR__registry_00ea2e68,PTR_s_instance_00ea2e60,&PTR_LOOP_00e7f8f0);
      __cxa_guard_release(PTR_s_instance_00ea2e58);
    }
                    /* try { // try from 0066fdc8 to 0076fdcf has its CatchHandler @ 006700bc */
                    /* try { // try from 0066fde0 to 0076fdeb has its CatchHandler @ 00670098 */
    local_80 = 0x50;
    local_88 = "/root/CI/builds/eTmYD8xn/0/ruitaocai/CNamaSDK/src/common/BundleHelper.cpp";
    local_78 = "DecryptObfuscatedPackage";
    fuspdlog::logger::log<int>
              (*(undefined8 *)(PTR_s_instance_00ea2e60 + 0xd0),&local_88,1,
               "enter DecryptObfuscatedPackage size:{}",0x26,&local_6c);
    param_2 = local_6c;
  }
  if (param_2 < 0x20) {
    uVar13 = 0xffffffff;
    goto LAB_0066ff1c;
  }
                    /* try { // try from 0066fe38 to 0076fe3b has its CatchHandler @ 00670030 */
  if ((((*param_1 == 0xf3) && (param_1[1] == '[')) && (param_1[2] == '\x06')) &&
     (param_1[3] == '\x12')) {
    local_a0 = (void *)0x0;
    local_98 = (void *)0x0;
    local_90 = 0;
                    /* try { // try from 0066fe64 to 0076fe7f has its CatchHandler @ 0067004c */
    FUN_005d4c3c(&local_a0,0x420);
    bVar5 = true;
    lVar16 = 4;
  }
  else {
    local_a0 = (void *)0x0;
    local_98 = (void *)0x0;
    local_90 = 0;
    FUN_005d4c3c(&local_a0,(long)(param_2 + -8));
                    /* try { // try from 0066fe98 to 0076feb3 has its CatchHandler @ 00670048 */
    lVar16 = 0;
    bVar5 = false;
  }
  lVar14 = (long)local_98 - (long)local_a0;
  memcpy((void *)((long)local_a0 + 0x10),param_1 + ((uint)lVar16 | 0x18),lVar14 - 0x10);
                    /* try { // try from 0066fecc to 0076fee7 has its CatchHandler @ 00670044 */
  iVar6 = FUN_0066f2c4(local_a0,local_a0,lVar14,param_1 + lVar16,
                       *(undefined8 *)PTR__obfuscation_key_00ea3b98);
  if (iVar6 == 0) {
    pvVar7 = *(void **)param_3;
    uVar15 = (ulong)(int)((local_6c - (uint)lVar16) + -0x28);
                    /* try { // try from 0066ff68 to 0076ff83 has its CatchHandler @ 00670038 */
    if ((ulong)(*(long *)(param_3 + 0x10) - (long)pvVar7) < uVar15) {
      lVar17 = *(long *)(param_3 + 8);
      __dest = operator_new(uVar15);
      __n = lVar17 - (long)pvVar7;
      if (0 < (long)__n) {
                    /* try { // try from 0066ffa0 to 0076ffbb has its CatchHandler @ 00670034 */
        memcpy(__dest,pvVar7,__n);
        pvVar7 = *(void **)param_3;
      }
      *(void **)param_3 = __dest;
      *(size_t *)(param_3 + 8) = (long)__dest + __n;
      *(ulong *)(param_3 + 0x10) = (long)__dest + uVar15;
      if (pvVar7 != (void *)0x0) {
                    /* try { // try from 0066ffbc to 007700bf has its CatchHandler @ 0066fbfc */
        operator_delete(pvVar7);
      }
    }
    std::__ndk1::vector<unsigned_char,std::__ndk1::allocator<unsigned_char>>::
    insert<std::__ndk1::move_iterator<std::__ndk1::__wrap_iter<unsigned_char*>>>
              ((vector<unsigned_char,std::__ndk1::allocator<unsigned_char>> *)param_3,
               *(undefined8 *)(param_3 + 8),(long)local_a0 + 0x20,local_98);
    if (bVar5) {
      std::__ndk1::vector<unsigned_char,std::__ndk1::allocator<unsigned_char>>::
      insert<unsigned_char_const*>
                ((vector<unsigned_char,std::__ndk1::allocator<unsigned_char>> *)param_3,
                 *(undefined8 *)(param_3 + 8),param_1 + lVar14 + lVar16 + 8,param_1 + local_6c);
      puVar11 = *(uint **)param_3;
      pbVar8 = *(byte **)(param_3 + 8);
      uVar3 = (int)pbVar8 - (int)puVar11;
      iVar6 = (int)uVar3 >> 2;
      bVar1 = param_1[5] ^ param_1[4] ^ param_1[6];
      if (iVar6 != 0) {
                    /* catch(type#1 @ 00000000) { ... } // from try @ 0066fe38 with catch @ 00670030
                        */
                    /* catch(type#1 @ 00000000) { ... } // from try @ 0066ffa0 with catch @ 00670034
                        */
        uVar2 = (uint)bVar1 * 0x1010101;
                    /* catch(type#1 @ 00000000) { ... } // from try @ 0066ff68 with catch @ 00670038
                        */
        if (iVar6 - 1U < 7) {
                    /* catch(type#1 @ 00000000) { ... } // from try @ 0066ff34 with catch @ 0067003c
                        */
          uVar10 = 0;
          puVar9 = puVar11;
                    /* catch(type#1 @ 00000000) { ... } // from try @ 0066fefc with catch @ 00670040
                        */
                    /* catch(type#1 @ 00000000) { ... } // from try @ 0066fecc with catch @ 00670044
                        */
        }
        else {
                    /* catch(type#1 @ 00000000) { ... } // from try @ 0066fe98 with catch @ 00670048
                        */
          uVar15 = (ulong)(iVar6 - 1U) + 1;
                    /* catch(type#1 @ 00000000) { ... } // from try @ 0066fe64 with catch @ 0067004c
                        */
          uVar10 = uVar15 & 0x1fffffff8;
          puVar9 = puVar11 + uVar10;
          puVar11 = puVar11 + 4;
          uVar12 = uVar10;
          do {
            uVar22 = *(undefined8 *)(puVar11 + -2);
            uVar13 = *(undefined8 *)(puVar11 + -4);
            uVar24 = *(undefined8 *)(puVar11 + 2);
            uVar23 = *(undefined8 *)puVar11;
            uVar12 = uVar12 - 8;
            bVar18 = (byte)uVar2;
            bVar19 = (byte)(uVar2 >> 8);
            bVar20 = (byte)(uVar2 >> 0x10);
            bVar21 = (byte)(uVar2 >> 0x18);
            *(ulong *)(puVar11 + -2) =
                 CONCAT17((byte)((ulong)uVar22 >> 0x38) ^ bVar21,
                          CONCAT16((byte)((ulong)uVar22 >> 0x30) ^ bVar20,
                                   CONCAT15((byte)((ulong)uVar22 >> 0x28) ^ bVar19,
                                            CONCAT14((byte)((ulong)uVar22 >> 0x20) ^ bVar18,
                                                     CONCAT13((byte)((ulong)uVar22 >> 0x18) ^ bVar21
                                                              ,CONCAT12((byte)((ulong)uVar22 >> 0x10
                                                                              ) ^ bVar20,
                                                                        CONCAT11((byte)((ulong)
                                                  uVar22 >> 8) ^ bVar19,(byte)uVar22 ^ bVar18)))))))
            ;
            *(ulong *)(puVar11 + -4) =
                 CONCAT17((byte)((ulong)uVar13 >> 0x38) ^ bVar21,
                          CONCAT16((byte)((ulong)uVar13 >> 0x30) ^ bVar20,
                                   CONCAT15((byte)((ulong)uVar13 >> 0x28) ^ bVar19,
                                            CONCAT14((byte)((ulong)uVar13 >> 0x20) ^ bVar18,
                                                     CONCAT13((byte)((ulong)uVar13 >> 0x18) ^ bVar21
                                                              ,CONCAT12((byte)((ulong)uVar13 >> 0x10
                                                                              ) ^ bVar20,
                                                                        CONCAT11((byte)((ulong)
                                                  uVar13 >> 8) ^ bVar19,(byte)uVar13 ^ bVar18)))))))
            ;
            *(ulong *)(puVar11 + 2) =
                 CONCAT17((byte)((ulong)uVar24 >> 0x38) ^ bVar21,
                          CONCAT16((byte)((ulong)uVar24 >> 0x30) ^ bVar20,
                                   CONCAT15((byte)((ulong)uVar24 >> 0x28) ^ bVar19,
                                            CONCAT14((byte)((ulong)uVar24 >> 0x20) ^ bVar18,
                                                     CONCAT13((byte)((ulong)uVar24 >> 0x18) ^ bVar21
                                                              ,CONCAT12((byte)((ulong)uVar24 >> 0x10
                                                                              ) ^ bVar20,
                                                                        CONCAT11((byte)((ulong)
                                                  uVar24 >> 8) ^ bVar19,(byte)uVar24 ^ bVar18)))))))
            ;
            *(ulong *)puVar11 =
                 CONCAT17((byte)((ulong)uVar23 >> 0x38) ^ bVar21,
                          CONCAT16((byte)((ulong)uVar23 >> 0x30) ^ bVar20,
                                   CONCAT15((byte)((ulong)uVar23 >> 0x28) ^ bVar19,
                                            CONCAT14((byte)((ulong)uVar23 >> 0x20) ^ bVar18,
                                                     CONCAT13((byte)((ulong)uVar23 >> 0x18) ^ bVar21
                                                              ,CONCAT12((byte)((ulong)uVar23 >> 0x10
                                                                              ) ^ bVar20,
                                                                        CONCAT11((byte)((ulong)
                                                  uVar23 >> 8) ^ bVar19,(byte)uVar23 ^ bVar18)))))))
            ;
            puVar11 = puVar11 + 8;
          } while (uVar12 != 0);
          if (uVar15 == uVar10) goto LAB_0067009c;
        }
        iVar6 = iVar6 - (int)uVar10;
        do {
          iVar6 = iVar6 + -1;
          *puVar9 = *puVar9 ^ uVar2;
          puVar9 = puVar9 + 1;
                    /* catch(type#1 @ 00000000) { ... } // from try @ 0066fde0 with catch @ 00670098
                        */
        } while (iVar6 != 0);
      }
LAB_0067009c:
      uVar3 = uVar3 & 3;
      if (uVar3 != 0) {
        iVar6 = uVar3 + 1;
        do {
          pbVar8 = pbVar8 + -1;
          iVar6 = iVar6 + -1;
                    /* catch(type#1 @ 00000000) { ... } // from try @ 0066fdc8 with catch @ 006700bc
                        */
          *pbVar8 = *pbVar8 ^ bVar1;
        } while (1 < iVar6);
      }
    }
    uVar13 = 0;
  }
  else {
    pvVar7 = *(void **)param_3;
    if (pvVar7 != (void *)0x0) {
      *(void **)(param_3 + 8) = pvVar7;
      operator_delete(pvVar7);
      *(undefined8 *)param_3 = 0;
      *(undefined8 *)(param_3 + 8) = 0;
                    /* try { // try from 0066fefc to 0076ff17 has its CatchHandler @ 00670040 */
      *(undefined8 *)(param_3 + 0x10) = 0;
    }
    *(undefined8 *)param_3 = 0;
    *(undefined8 *)(param_3 + 8) = 0;
    *(undefined8 *)(param_3 + 0x10) = 0;
    uVar13 = 0xfffffffe;
  }
  if (local_a0 != (void *)0x0) {
    local_98 = local_a0;
    operator_delete(local_a0);
  }
LAB_0066ff1c:
  if (*(long *)(lVar4 + 0x28) == local_68) {
                    /* try { // try from 0066ff34 to 0076ff4f has its CatchHandler @ 0067003c */
    return uVar13;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: CNamaSDK::BundleHelper::VerifySignature
 * Address : 006707e4
 * Size    : 0x97c bytes
 * ------------------------------------------------------------------ */

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */
/* CNamaSDK::BundleHelper::VerifySignature(std::__ndk1::vector<unsigned char,
   std::__ndk1::allocator<unsigned char> > const&, int&) */

ulong CNamaSDK::BundleHelper::VerifySignature(vector *param_1,int *param_2)

{
  int iVar1;
  uint uVar2;
  int iVar3;
  long lVar4;
  undefined1 auVar5 [16];
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 *puVar9;
  undefined *puVar10;
  undefined *puVar11;
  undefined1 auVar12 [8];
  int iVar13;
  undefined8 *__s;
  undefined8 *puVar14;
  void *__s_00;
  undefined8 *puVar15;
  long lVar16;
  long lVar17;
  undefined1 *puVar18;
  ulong uVar19;
  bool bVar20;
  void *pvVar21;
  undefined1 *puVar22;
  ulong uVar24;
  ulong uVar25;
  undefined1 auVar26 [16];
  undefined1 auVar27 [16];
  undefined1 auVar28 [16];
  undefined1 auVar29 [16];
  undefined1 auVar30 [16];
  undefined1 auVar31 [16];
  undefined1 auVar32 [16];
  undefined1 auVar33 [16];
  undefined1 auVar34 [16];
  char *local_260;
  undefined4 local_258;
  char *local_250;
  void *local_248;
  void *local_240;
  undefined8 local_238;
  void *local_230;
  void *local_228;
  void *local_220;
  void *local_218;
  void *local_210;
  undefined8 local_208;
  undefined1 *local_200;
  undefined1 *local_1f8;
  undefined1 *local_1f0;
  undefined1 auStack_1e8 [8];
  void *local_1e0;
  void *local_1d8;
  void *local_1d0;
  undefined1 *local_1c8;
  undefined1 local_1c0 [8];
  undefined8 local_1b8;
  void *local_1b0;
  void *pvStack_1a8;
  void *local_1a0;
  undefined8 uStack_198;
  undefined8 local_190;
  undefined8 uStack_188;
  undefined8 uStack_180;
  undefined8 uStack_178;
  undefined8 local_170;
  undefined8 uStack_168;
  undefined8 uStack_160;
  undefined8 uStack_158;
  undefined8 local_150;
  undefined8 uStack_148;
  undefined8 uStack_140;
  undefined8 local_138;
  undefined8 local_130;
  undefined8 uStack_128;
  undefined8 uStack_120;
  undefined8 uStack_118;
  undefined8 local_110;
  undefined8 uStack_108;
  undefined8 uStack_100;
  undefined8 uStack_f8;
  undefined8 local_f0;
  undefined8 uStack_e8;
  undefined8 uStack_e0;
  undefined8 uStack_d8;
  undefined8 local_d0;
  undefined8 uStack_c8;
  undefined8 uStack_c0;
  undefined8 uStack_b8;
  undefined8 local_b0;
  undefined8 uStack_a8;
  undefined8 uStack_a0;
  undefined8 uStack_98;
  undefined8 local_90;
  undefined8 uStack_88;
  undefined8 uStack_80;
  undefined8 uStack_78;
  long local_70;
  undefined1 *puVar23;
  
  puVar10 = PTR_g_context_00ea2e20;
  lVar4 = tpidr_el0;
  local_70 = *(long *)(lVar4 + 0x28);
  pvVar21 = (void *)0x0;
  PTR_g_context_00ea2e20[0x123] = 0;
  lVar16 = *(long *)param_1;
  uVar24 = *(long *)(param_1 + 8) - lVar16;
  uVar25 = uVar24 - 0x40;
  if (uVar24 < 0x40) goto LAB_00670dac;
  __s = operator_new(0x40);
                    /* try { // try from 00670840 to 00770847 has its CatchHandler @ 00670910 */
                    /* try { // try from 00670848 to 00770923 has its CatchHandler @ 00670710 */
  memset(__s,0,0x40);
  if (*param_2 == 0) {
    uStack_a8 = DAT_00474785._8_8_;
    local_b0 = (undefined8)DAT_00474785;
    uStack_98 = DAT_00474795._8_8_;
    uStack_a0 = (undefined8)DAT_00474795;
    uStack_88 = DAT_004747a5._8_8_;
    local_90 = (undefined8)DAT_004747a5;
    uStack_78 = DAT_004747b5._8_8_;
    uStack_80 = (undefined8)DAT_004747b5;
    FUN_00676b2c(&local_b0,lVar16,uVar25);
    uVar24 = uVar25 & 0x7f;
    uStack_c8 = 0;
    local_d0 = 0;
    uStack_b8 = 0;
    uStack_c0 = 0;
    uStack_e8 = 0;
    local_f0 = 0;
    uStack_d8 = 0;
    uStack_e0 = 0;
    uStack_108 = 0;
    local_110 = 0;
    uStack_f8 = 0;
    uStack_100 = 0;
    uStack_128 = 0;
    local_130 = 0;
    uStack_118 = 0;
    uStack_120 = 0;
    uStack_148 = 0;
    local_150 = 0;
    local_138 = 0;
    uStack_140 = 0;
    uStack_168 = 0;
    local_170 = 0;
    uStack_158 = 0;
    uStack_160 = 0;
    uStack_188 = 0;
    local_190 = 0;
    uStack_178 = 0;
    uStack_180 = 0;
    pvStack_1a8 = (void *)0x0;
    local_1b0 = (void *)0x0;
    uStack_198 = 0;
    local_1a0 = (void *)0x0;
    if (uVar24 != 0) {
      memcpy(&local_1b0,(void *)((lVar16 + uVar25) - uVar24),uVar24);
    }
    *(undefined1 *)((long)&local_1b0 + uVar24) = 0x80;
    lVar16 = 0x80;
    if (0x6f < uVar24) {
      lVar16 = 0x100;
    }
    auVar29._8_8_ = uVar25;
    auVar29._0_8_ = uVar25;
    auVar31._0_8_ = -_DAT_0046d0a0;
    auVar31._8_8_ = -_UNK_0046d0a8;
    auVar34._0_8_ = -_DAT_0046d270;
    auVar34._8_8_ = -_UNK_0046d278;
    auVar32._0_8_ = -_DAT_0046d310;
    auVar32._8_8_ = -_UNK_0046d318;
    auVar31 = NEON_ushl(auVar29,auVar31,8);
    auVar34 = NEON_ushl(auVar29,auVar34,8);
    auVar32 = NEON_ushl(auVar29,auVar32,8);
    auVar27._8_8_ = -_UNK_0046d328;
    auVar27._0_8_ = -_DAT_0046d320;
    auVar27 = NEON_ushl(auVar29,auVar27,8);
    *(char *)((long)&local_1b8 + lVar16 + 7) = (char)((int)uVar25 << 3);
    *(ulong *)(local_1c0 + lVar16 + 7) =
         CONCAT17(auVar32[8],
                  CONCAT16(auVar32[0],
                           CONCAT15(auVar27[8],
                                    CONCAT14(auVar27[0],
                                             CONCAT13(auVar31[8],
                                                      CONCAT12(auVar31[0],
                                                               CONCAT11(auVar34[8],auVar34[0])))))))
    ;
    FUN_00676b2c(&local_b0,&local_1b0);
    __s[5] = uStack_88;
    __s[4] = local_90;
    __s[7] = uStack_78;
    __s[6] = uStack_80;
    __s[1] = uStack_a8;
    *__s = local_b0;
    __s[3] = uStack_98;
    __s[2] = uStack_a0;
  }
  else {
    pvVar21 = operator_new(0x400);
    memset(pvVar21,0,0x400);
    iVar13 = (int)uVar24;
    uVar2 = iVar13 + 0x7beU;
    if (-1 < (int)(iVar13 + 0x3bfU)) {
      uVar2 = iVar13 + 0x3bfU;
    }
    iVar1 = (int)uVar2 >> 10;
    if (iVar13 + 0x7beU < 0x7ff) {
      iVar1 = 1;
    }
    if (0 < (int)(iVar13 - 0x40U)) {
      lVar17 = 0;
      do {
        puVar23 = (undefined1 *)(lVar16 + lVar17);
        iVar3 = 0;
        if (iVar1 != 0) {
          iVar3 = (int)lVar17 / iVar1;
        }
        lVar17 = lVar17 + iVar1;
        *(undefined1 *)((long)pvVar21 + (long)iVar3) = *puVar23;
      } while (lVar17 < (long)(ulong)(iVar13 - 0x40U));
    }
    uStack_a8 = DAT_00474785._8_8_;
    local_b0 = (undefined8)DAT_00474785;
    uStack_98 = DAT_00474795._8_8_;
    uStack_a0 = (undefined8)DAT_00474795;
    uStack_88 = DAT_004747a5._8_8_;
    local_90 = (undefined8)DAT_004747a5;
    uStack_78 = DAT_004747b5._8_8_;
    uStack_80 = (undefined8)DAT_004747b5;
    FUN_00676b2c(&local_b0,pvVar21,0x400);
    pvStack_1a8 = (void *)0x0;
    uStack_198 = 0;
    local_1a0 = (void *)0x0;
    local_1b0 = (void *)0x80;
    uStack_c8 = 0;
    local_d0 = 0;
    uStack_b8 = 0;
    uStack_c0 = 0;
    uStack_e8 = 0;
    local_f0 = 0;
    uStack_d8 = 0;
    uStack_e0 = 0;
    uStack_108 = 0;
    local_110 = 0;
    uStack_f8 = 0;
    uStack_100 = 0;
                    /* catch() { ... } // from try @ 00670840 with catch @ 00670910 */
    uStack_128 = 0;
    local_130 = 0;
    uStack_118 = 0;
    uStack_120 = 0;
    uStack_148 = 0;
    local_150 = 0;
    uStack_168 = 0;
    local_170 = 0;
    uStack_158 = 0;
    uStack_160 = 0;
    uStack_188 = 0;
    local_190 = 0;
    uStack_178 = 0;
    uStack_180 = 0;
    uStack_140 = 0;
    local_138 = 0x20000000000000;
    FUN_00676b2c(&local_b0,&local_1b0,0x80);
    __s[5] = uStack_88;
    __s[4] = local_90;
    __s[7] = uStack_78;
    __s[6] = uStack_80;
    __s[1] = uStack_a8;
    *__s = local_b0;
    __s[3] = uStack_98;
    __s[2] = uStack_a0;
    operator_delete(pvVar21);
  }
  local_1c8 = (undefined1 *)0x0;
  local_1c0 = (undefined1  [8])0x0;
  local_1b8 = 0;
  std::__ndk1::vector<unsigned_char,std::__ndk1::allocator<unsigned_char>>::
  insert<std::__ndk1::__wrap_iter<unsigned_char_const*>>
            ((vector<unsigned_char,std::__ndk1::allocator<unsigned_char>> *)&local_1c8,0,
             *(long *)(param_1 + 8) + -0x40);
  std::__ndk1::vector<unsigned_char,std::__ndk1::allocator<unsigned_char>>::
  insert<std::__ndk1::move_iterator<std::__ndk1::__wrap_iter<char*>>>
            ((vector<unsigned_char,std::__ndk1::allocator<unsigned_char>> *)&local_1c8,local_1c0,__s
             ,__s + 8);
  auVar12 = local_1c0;
  puVar23 = local_1c8;
  local_1d8 = (void *)0x0;
  local_1d0 = (void *)0x0;
  local_1e0 = (void *)0x0;
  uVar25 = (long)local_1c0 - (long)local_1c8;
  if (uVar25 == 0) {
    local_200 = (undefined1 *)0x0;
    local_1f8 = (undefined1 *)0x0;
    local_1f0 = (undefined1 *)0x0;
  }
  else {
    if ((long)uVar25 < 0) {
                    /* WARNING: Subroutine does not return */
      FUN_00d64f48(&local_1e0);
    }
    local_1e0 = operator_new(uVar25);
    pvVar21 = (void *)((long)local_1e0 + uVar25);
    local_1d0 = pvVar21;
    memset(local_1e0,0,uVar25);
    local_1d8 = pvVar21;
    pvVar21 = malloc(uVar25 + 0x18);
    local_200 = (undefined1 *)((long)pvVar21 + 0x18U & 0xfffffffffffffff0);
    local_1f0 = local_200 + uVar25;
    *(void **)(local_200 + -8) = pvVar21;
    local_1f8 = local_200;
    if ((undefined1  [8])puVar23 != auVar12) {
      puVar18 = local_200;
      if ((0x1f < uVar25) &&
         (((ulong)auVar12 <= local_200 ||
          ((undefined1 *)((long)auVar12 + ((long)local_200 - (long)puVar23)) <= puVar23)))) {
        uVar19 = uVar25 & 0xffffffffffffffe0;
        puVar14 = (undefined8 *)(puVar23 + 0x10);
        local_1f8 = local_200 + uVar19;
        puVar23 = puVar23 + uVar19;
        puVar15 = (undefined8 *)(local_200 + 0x10);
        uVar24 = uVar19;
        do {
          uVar8 = puVar14[-2];
          puVar9 = puVar14 + -1;
          uVar6 = *puVar14;
          uVar7 = puVar14[1];
          puVar14 = puVar14 + 4;
          uVar24 = uVar24 - 0x20;
          puVar15[-1] = *puVar9;
          puVar15[-2] = uVar8;
          puVar15[1] = uVar7;
          *puVar15 = uVar6;
          puVar15 = puVar15 + 4;
        } while (uVar24 != 0);
        puVar18 = local_1f8;
        if (uVar19 == uVar25) goto LAB_00670b94;
      }
      do {
        puVar22 = puVar23 + 1;
        local_1f8 = puVar18 + 1;
        *puVar18 = *puVar23;
        puVar18 = local_1f8;
        puVar23 = puVar22;
      } while (auVar12 != (undefined1  [8])puVar22);
    }
  }
LAB_00670b94:
  puVar23 = local_200;
  puVar11 = PTR__final_key_00ea3ba0;
  iVar13 = FUN_006719a8(local_1e0,auStack_1e8,local_200,(long)local_1f8 - (long)local_200,
                        *(undefined8 *)PTR__final_key_00ea3ba0);
  if (iVar13 == 0) {
    pvVar21 = (void *)0x1;
    puVar18 = local_1f8;
  }
  else {
    lVar16 = *(long *)param_1;
    lVar17 = *(long *)(param_1 + 8);
    uVar25 = lVar17 - lVar16;
    if (0xc0 < uVar25) {
      local_218 = (void *)0x0;
      local_210 = (void *)0x0;
      local_208 = 0;
      puVar14 = operator_new(0x40);
      memset(puVar14,0,0x40);
      std::__ndk1::vector<char,std::__ndk1::allocator<char>>::
      insert<std::__ndk1::__wrap_iter<unsigned_char_const*>>
                ((vector<char,std::__ndk1::allocator<char>> *)&local_218,0,lVar16 + uVar25 + -0x80,
                 lVar17);
      pvVar21 = local_218;
      local_228 = (void *)0x0;
      local_220 = (void *)0x0;
      local_230 = (void *)0x0;
      uVar25 = (long)local_210 - (long)local_218;
      if (uVar25 == 0) {
        __s_00 = (void *)0x0;
      }
      else {
        if ((long)uVar25 < 0) {
                    /* WARNING: Subroutine does not return */
          FUN_00d64f48(&local_230);
        }
        __s_00 = operator_new(uVar25);
        local_230 = __s_00;
        local_220 = (void *)((long)__s_00 + uVar25);
        memset(__s_00,0,uVar25);
        local_228 = (void *)((long)__s_00 + uVar25);
      }
      iVar13 = FUN_006719a8(__s_00,auStack_1e8,pvVar21,uVar25,*(undefined8 *)puVar11);
      if (iVar13 == 0) {
        local_248 = (void *)0x0;
        local_240 = (void *)0x0;
        local_238 = 0;
        std::__ndk1::vector<char,std::__ndk1::allocator<char>>::
        insert<std::__ndk1::move_iterator<std::__ndk1::__wrap_iter<char*>>>
                  ((vector<char,std::__ndk1::allocator<char>> *)&local_248,0,(long)local_218 + 0x40,
                   (long)local_218 + 0x60);
        if (*param_2 == 0) {
          lVar16 = *(long *)param_1;
          uVar24 = (*(long *)(param_1 + 8) - lVar16) - 0xc0;
          uStack_a8 = DAT_00474785._8_8_;
          local_b0 = (undefined8)DAT_00474785;
          uStack_98 = DAT_00474795._8_8_;
          uStack_a0 = (undefined8)DAT_00474795;
          uStack_88 = DAT_004747a5._8_8_;
          local_90 = (undefined8)DAT_004747a5;
          uStack_78 = DAT_004747b5._8_8_;
          uStack_80 = (undefined8)DAT_004747b5;
          FUN_00676b2c(&local_b0,lVar16,uVar24);
          uVar25 = uVar24 & 0x7f;
          uStack_c8 = 0;
          local_d0 = 0;
          uStack_b8 = 0;
          uStack_c0 = 0;
          uStack_e8 = 0;
          local_f0 = 0;
          uStack_d8 = 0;
          uStack_e0 = 0;
          uStack_108 = 0;
          local_110 = 0;
          uStack_f8 = 0;
          uStack_100 = 0;
          uStack_128 = 0;
          local_130 = 0;
          uStack_118 = 0;
          uStack_120 = 0;
          uStack_148 = 0;
          local_150 = 0;
          local_138 = 0;
          uStack_140 = 0;
          uStack_168 = 0;
          local_170 = 0;
          uStack_158 = 0;
          uStack_160 = 0;
          uStack_188 = 0;
          local_190 = 0;
          uStack_178 = 0;
          uStack_180 = 0;
          pvStack_1a8 = (void *)0x0;
          local_1b0 = (void *)0x0;
          uStack_198 = 0;
          local_1a0 = (void *)0x0;
          if (uVar25 != 0) {
            memcpy(&local_1b0,(void *)((lVar16 + uVar24) - uVar25),uVar25);
          }
                    /* try { // try from 00670f84 to 00770f8b has its CatchHandler @ 006711f8 */
          *(undefined1 *)((long)&local_1b0 + uVar25) = 0x80;
          lVar16 = 0x80;
          if (0x6f < uVar25) {
            lVar16 = 0x100;
          }
          auVar26._8_8_ = uVar24;
          auVar26._0_8_ = uVar24;
          auVar28._0_8_ = -_DAT_0046d0a0;
          auVar28._8_8_ = -_UNK_0046d0a8;
          auVar30._0_8_ = -_DAT_0046d270;
          auVar30._8_8_ = -_UNK_0046d278;
          auVar33._0_8_ = -_DAT_0046d310;
          auVar33._8_8_ = -_UNK_0046d318;
                    /* try { // try from 00670fd0 to 00770fd7 has its CatchHandler @ 006711f4 */
          auVar29 = NEON_ushl(auVar26,auVar28,8);
          auVar31 = NEON_ushl(auVar26,auVar30,8);
          auVar34 = NEON_ushl(auVar26,auVar33,8);
          auVar5._8_8_ = -_UNK_0046d328;
          auVar5._0_8_ = -_DAT_0046d320;
          auVar27 = NEON_ushl(auVar26,auVar5,8);
          *(char *)((long)&local_1b8 + lVar16 + 7) = (char)((int)uVar24 << 3);
                    /* try { // try from 00670ffc to 00771003 has its CatchHandler @ 006711fc */
                    /* try { // try from 0067100c to 00771017 has its CatchHandler @ 006711d0 */
          *(ulong *)(local_1c0 + lVar16 + 7) =
               CONCAT17(auVar34[8],
                        CONCAT16(auVar34[0],
                                 CONCAT15(auVar27[8],
                                          CONCAT14(auVar27[0],
                                                   CONCAT13(auVar29[8],
                                                            CONCAT12(auVar29[0],
                                                                     CONCAT11(auVar31[8],auVar31[0])
                                                                    ))))));
          FUN_00676b2c(&local_b0,&local_1b0);
          puVar14[5] = uStack_88;
          puVar14[4] = local_90;
                    /* try { // try from 00671018 to 00771143 has its CatchHandler @ 00670ebc */
          puVar14[7] = uStack_78;
          puVar14[6] = uStack_80;
          puVar14[1] = uStack_a8;
          *puVar14 = local_b0;
          puVar14[3] = uStack_98;
          puVar14[2] = uStack_a0;
        }
        else {
          pvVar21 = operator_new(0x400);
                    /* try { // try from 00670e18 to 00770e1f has its CatchHandler @ 00670e34 */
          memset(pvVar21,0,0x400);
                    /* try { // try from 00670e20 to 00770ebb has its CatchHandler @ 00670d9c */
          lVar16 = *(long *)param_1;
          iVar13 = *(int *)(param_1 + 8) - (int)lVar16;
                    /* catch() { ... } // from try @ 00670e18 with catch @ 00670e34 */
          uVar2 = iVar13 + 0x73eU;
          if (-1 < (int)(iVar13 + 0x33fU)) {
            uVar2 = iVar13 + 0x33fU;
          }
          iVar1 = (int)uVar2 >> 10;
          if (iVar13 + 0x73eU < 0x7ff) {
            iVar1 = 1;
          }
          if (0 < (int)(iVar13 - 0xc0U)) {
            lVar17 = 0;
            do {
              puVar23 = (undefined1 *)(lVar16 + lVar17);
              iVar3 = 0;
              if (iVar1 != 0) {
                iVar3 = (int)lVar17 / iVar1;
              }
              lVar17 = lVar17 + iVar1;
              *(undefined1 *)((long)pvVar21 + (long)iVar3) = *puVar23;
            } while (lVar17 < (long)(ulong)(iVar13 - 0xc0U));
          }
          uStack_a8 = DAT_00474785._8_8_;
          local_b0 = (undefined8)DAT_00474785;
          uStack_98 = DAT_00474795._8_8_;
          uStack_a0 = (undefined8)DAT_00474795;
          uStack_88 = DAT_004747a5._8_8_;
          local_90 = (undefined8)DAT_004747a5;
          uStack_78 = DAT_004747b5._8_8_;
          uStack_80 = (undefined8)DAT_004747b5;
          FUN_00676b2c(&local_b0,pvVar21,0x400);
          pvStack_1a8 = (void *)0x0;
          uStack_198 = 0;
          local_1a0 = (void *)0x0;
          local_1b0 = (void *)0x80;
                    /* try { // try from 00670ebc to 00770f83 has its CatchHandler @ 00670ebc
                       catch() { ... } // from try @ 00670ebc with catch @ 00670ebc
                       catch() { ... } // from try @ 00671018 with catch @ 00670ebc
                       catch() { ... } // from try @ 00671180 with catch @ 00670ebc */
          uStack_c8 = 0;
          local_d0 = 0;
          uStack_b8 = 0;
          uStack_c0 = 0;
          uStack_e8 = 0;
          local_f0 = 0;
          uStack_d8 = 0;
          uStack_e0 = 0;
          uStack_108 = 0;
          local_110 = 0;
          uStack_f8 = 0;
          uStack_100 = 0;
          uStack_128 = 0;
          local_130 = 0;
          uStack_118 = 0;
          uStack_120 = 0;
          uStack_148 = 0;
          local_150 = 0;
          uStack_168 = 0;
          local_170 = 0;
          uStack_158 = 0;
          uStack_160 = 0;
          uStack_188 = 0;
          local_190 = 0;
          uStack_178 = 0;
          uStack_180 = 0;
          uStack_140 = 0;
          local_138 = 0x20000000000000;
          FUN_00676b2c(&local_b0,&local_1b0,0x80);
          puVar14[5] = uStack_88;
          puVar14[4] = local_90;
          puVar14[7] = uStack_78;
          puVar14[6] = uStack_80;
          puVar14[1] = uStack_a8;
          *puVar14 = local_b0;
          puVar14[3] = uStack_98;
          puVar14[2] = uStack_a0;
          operator_delete(pvVar21);
        }
        local_1c0 = (undefined1  [8])local_1c8;
        std::__ndk1::vector<unsigned_char,std::__ndk1::allocator<unsigned_char>>::
        insert<std::__ndk1::__wrap_iter<unsigned_char_const*>>
                  ((vector<unsigned_char,std::__ndk1::allocator<unsigned_char>> *)&local_1c8,
                   local_1c8,*(long *)(param_1 + 8) + -0xc0,*(long *)(param_1 + 8) + -0x80);
        std::__ndk1::vector<unsigned_char,std::__ndk1::allocator<unsigned_char>>::
        insert<std::__ndk1::move_iterator<std::__ndk1::__wrap_iter<char*>>>
                  ((vector<unsigned_char,std::__ndk1::allocator<unsigned_char>> *)&local_1c8,
                   local_1c0,puVar14,puVar14 + 8);
        puVar23 = local_1c8;
        pvStack_1a8 = (void *)0x0;
        local_1a0 = (void *)0x0;
        local_1b0 = (void *)0x0;
        uVar25 = (long)local_1c0 - (long)local_1c8;
        if (uVar25 == 0) {
          pvVar21 = (void *)0x0;
        }
        else {
          if ((long)uVar25 < 0) {
                    /* WARNING: Subroutine does not return */
            FUN_00d64f48(&local_1b0);
          }
          pvVar21 = operator_new(uVar25);
          local_1b0 = pvVar21;
          local_1a0 = (void *)((long)pvVar21 + uVar25);
          memset(pvVar21,0,uVar25);
          pvStack_1a8 = (void *)((long)pvVar21 + uVar25);
        }
        iVar13 = FUN_006719a8(pvVar21,auStack_1e8,puVar23,uVar25,local_248);
        if (local_1b0 != (void *)0x0) {
          pvStack_1a8 = local_1b0;
          operator_delete(local_1b0);
        }
        pvVar21 = (void *)((ulong)(iVar13 == 0) << 1);
        if (local_248 != (void *)0x0) {
          local_240 = local_248;
          operator_delete(local_248);
        }
        bVar20 = false;
      }
      else {
        bVar20 = true;
      }
      if (local_230 != (void *)0x0) {
        local_228 = local_230;
        operator_delete(local_230);
      }
      operator_delete(puVar14);
      if (local_218 != (void *)0x0) {
        local_210 = local_218;
        operator_delete(local_218);
      }
      puVar23 = local_200;
      puVar18 = local_1f8;
      if (!bVar20) goto joined_r0x00670c4c;
    }
                    /* try { // try from 00670cb8 to 00770cbf has its CatchHandler @ 00670d88 */
                    /* try { // try from 00670cc0 to 00770d9b has its CatchHandler @ 00670b88 */
    iVar13 = FUN_006719a8(local_1e0,auStack_1e8,local_1c8,(long)local_1c0 - (long)local_1c8,
                          *(undefined8 *)PTR__debug_key_00ea3ba8);
    if ((puVar10[0x122] != '\0') && (iVar13 == 0)) {
      puVar10[0x123] = 1;
    }
    puVar11 = PTR_debug_key_used_00ea3b68;
    if ((iVar13 == 0) && (puVar10[0x122] == '\0')) {
      puVar10[0x124] = 1;
      *puVar11 = 1;
      nama::Log::Instance();
      if ((*PTR_m_log_modules_00ea2e50 & 1) == 0) {
        pvVar21 = (void *)0x3;
        puVar23 = local_200;
        puVar18 = local_1f8;
      }
      else {
        if (((*PTR_s_instance_00ea2e58 & 1) == 0) &&
           (iVar13 = __cxa_guard_acquire(PTR_s_instance_00ea2e58), iVar13 != 0)) {
          fuspdlog::details::registry::registry((registry *)PTR_s_instance_00ea2e60);
          __cxa_atexit(PTR__registry_00ea2e68,PTR_s_instance_00ea2e60,&PTR_LOOP_00e7f8f0);
          __cxa_guard_release(PTR_s_instance_00ea2e58);
        }
        pvVar21 = (void *)0x3;
        local_260 = "/root/CI/builds/eTmYD8xn/0/ruitaocai/CNamaSDK/src/common/BundleHelper.cpp";
        local_258 = 0x126;
        local_250 = "VerifySignature";
        fuspdlog::logger::log<char[15],(char(*)[15])0>
                  (*(logger **)(PTR_s_instance_00ea2e60 + 0xd0),&local_260,3,"debug key used");
        puVar23 = local_200;
        puVar18 = local_1f8;
      }
    }
    else {
      pvVar21 = (void *)(ulong)(iVar13 == 0);
      puVar23 = local_200;
      puVar18 = local_1f8;
    }
  }
joined_r0x00670c4c:
  local_1f8 = puVar23;
  if (local_1f8 != (undefined1 *)0x0) {
    free(*(void **)(local_1f8 + -8));
    puVar18 = local_1f8;
  }
  local_1f8 = puVar18;
                    /* catch() { ... } // from try @ 00670cb8 with catch @ 00670d88 */
  if (local_1e0 != (void *)0x0) {
    local_1d8 = local_1e0;
    operator_delete(local_1e0);
  }
  if (local_1c8 != (undefined1 *)0x0) {
                    /* catch() { ... } // from try @ 00670e20 with catch @ 00670d9c */
    local_1c0 = (undefined1  [8])local_1c8;
    operator_delete(local_1c8);
  }
  operator_delete(__s);
LAB_00670dac:
  if (*(long *)(lVar4 + 0x28) != local_70) {
                    /* WARNING: Subroutine does not return */
                    /* try { // try from 00671144 to 0077114b has its CatchHandler @ 006711f8 */
    __stack_chk_fail();
  }
  return (ulong)pvVar21 & 0xffffffff;
}



/* ------------------------------------------------------------------
 * Function: CNamaSDK::BundleHelper::DecryptObfuscatedPackageAndVerify
 * Address : 00672f8c
 * Size    : 0x4 bytes
 * ------------------------------------------------------------------ */

/* CNamaSDK::BundleHelper::DecryptObfuscatedPackageAndVerify(unsigned char const*, int,
   std::__ndk1::vector<unsigned char, std::__ndk1::allocator<unsigned char> >&) */

void CNamaSDK::BundleHelper::DecryptObfuscatedPackageAndVerify
               (uchar *param_1,int param_2,vector *param_3)

{
  (*(code *)PTR_DecryptObfuscatedPackage_00eac760)(param_1,param_2);
  return;
}



/* ------------------------------------------------------------------
 * Function: CNamaSDK::BundleHelper::ContainMagicCode
 * Address : 00672f90
 * Size    : 0x8c bytes
 * ------------------------------------------------------------------ */

/* CNamaSDK::BundleHelper::ContainMagicCode(std::__ndk1::basic_string<char,
   std::__ndk1::char_traits<char>, std::__ndk1::allocator<char> > const&) */

undefined8 CNamaSDK::BundleHelper::ContainMagicCode(basic_string *param_1)

{
  char cVar1;
  char *pcVar2;
  
  if ((*param_1 & 1) == 0) {
    if ((byte)*param_1 < 8) {
      return 0;
    }
    pcVar2 = param_1 + 1;
    if (*pcVar2 != -0xd) {
      return 0;
    }
    if (param_1[2] != '[') {
      return 0;
    }
    cVar1 = param_1[3];
  }
  else {
    if (*(ulong *)(param_1 + 8) < 4) {
      return 0;
    }
    pcVar2 = *(char **)(param_1 + 0x10);
    if (*pcVar2 != -0xd) {
      return 0;
    }
    if (pcVar2[1] != '[') {
      return 0;
    }
    cVar1 = pcVar2[2];
  }
  if ((cVar1 == '\x06') && (pcVar2[3] == '\x12')) {
    return 1;
  }
  return 0;
}



/* ------------------------------------------------------------------
 * Function: FuAIWrapper::DecryptObfuscatedPackageTF
 * Address : 0075ab04
 * Size    : 0xc7c bytes
 * ------------------------------------------------------------------ */

/* FuAIWrapper::DecryptObfuscatedPackageTF(std::__ndk1::vector<unsigned char,
   std::__ndk1::allocator<unsigned char> >) */

void FuAIWrapper::DecryptObfuscatedPackageTF(undefined8 *param_1,undefined8 param_2,long *param_3)

{
  ulong *puVar1;
  uint uVar2;
  uint uVar3;
  ulong uVar4;
  ulong uVar5;
  ulong uVar6;
  ulong uVar7;
  ulong uVar8;
  ulong uVar9;
  long lVar10;
  undefined1 uVar11;
  long lVar12;
  uint7 uVar13;
  undefined1 *__src;
  bool bVar14;
  byte *pbVar15;
  undefined1 *puVar16;
  ulong uVar17;
  void *__s;
  ulong uVar18;
  ulong uVar19;
  ulong uVar20;
  ulong uVar21;
  ulong uVar22;
  ulong uVar23;
  uint uVar24;
  byte *pbVar25;
  uint uVar26;
  int iVar27;
  ulong uVar28;
  ulong uVar29;
  ulong uVar30;
  ulong uVar31;
  long lVar32;
  ulong *puVar33;
  byte *pbVar34;
  byte bVar35;
  ulong uVar36;
  ulong uVar37;
  ulong uVar38;
  ulong uVar39;
  ulong uVar40;
  ulong uVar41;
  ulong *puVar42;
  ulong uVar43;
  ulong uVar44;
  ulong uVar45;
  ulong uVar46;
  ulong uVar47;
  ulong uVar48;
  ulong uVar49;
  ulong uVar50;
  ulong uVar51;
  ulong uVar52;
  ulong uVar53;
  ulong uVar54;
  long lVar55;
  undefined1 *puVar56;
  byte *pbVar57;
  long lVar58;
  byte *pbVar59;
  ulong uVar60;
  ulong uVar61;
  ulong uVar62;
  size_t __n;
  long lVar63;
  undefined1 *local_2d0;
  undefined1 *local_2c8;
  undefined1 *local_2c0;
  byte *local_2b8;
  byte *local_2b0;
  byte *local_2a8;
  ulong local_2a0 [16];
  undefined8 local_220;
  ulong local_210;
  undefined8 uStack_208;
  ulong local_200;
  ulong uStack_1f8;
  ulong local_1f0;
  ulong uStack_1e8;
  ulong local_1e0;
  ulong uStack_1d8;
  ulong local_1d0;
  ulong uStack_1c8;
  ulong local_1c0;
  ulong local_1b8;
  ulong local_1b0;
  ulong uStack_1a8;
  ulong local_1a0;
  ulong local_198;
  ulong local_190;
  ulong local_180;
  ulong local_178;
  ulong local_170;
  ulong local_168;
  ulong local_160;
  ulong local_158;
  ulong local_150;
  ulong local_148;
  ulong local_140;
  ulong local_138;
  ulong local_130;
  ulong local_128;
  ulong local_120;
  ulong local_118;
  ulong local_110;
  ulong local_108;
  undefined8 local_100;
  byte local_f8 [32];
  long local_d8;
  long lStack_d0;
  long local_c8;
  long lStack_c0;
  long local_b8;
  long lStack_b0;
  long local_a8;
  long lStack_a0;
  long local_98;
  long lStack_90;
  long local_88;
  long lStack_80;
  long local_78;
  long local_70;
  
  lVar12 = tpidr_el0;
  local_70 = *(long *)(lVar12 + 0x28);
  lVar55 = *param_3;
  local_2b8 = (byte *)0x0;
  local_2b0 = (byte *)0x0;
  local_2a8 = (byte *)0x0;
  lVar58 = (ulong)(uint)((int)param_3[1] - (int)lVar55) << 0x20;
  lVar63 = lVar58 + -0x800000000;
  if (lVar63 == 0) {
    pbVar15 = (byte *)0x0;
  }
  else {
    if (lVar63 < 0) {
                    /* WARNING: Subroutine does not return */
      FUN_00d64f48(&local_2b8);
    }
    uVar62 = lVar63 >> 0x20;
    pbVar15 = operator_new(uVar62);
    local_2b8 = pbVar15;
    local_2a8 = pbVar15 + uVar62;
    memset(pbVar15,0,uVar62);
    local_2b0 = pbVar15 + uVar62;
  }
  memcpy(pbVar15 + 0x10,(void *)(lVar55 + 0x18),lVar58 + -0x1800000000 >> 0x20);
  lVar63 = 0;
  local_2d0 = (undefined1 *)0x0;
  local_2c8 = (undefined1 *)0x0;
  local_2c0 = (undefined1 *)0x0;
  puVar16 = (undefined1 *)0x0;
  while( true ) {
    __src = local_2d0;
    uVar11 = (undefined1)*(undefined4 *)((long)&DAT_004784e4 + lVar63);
    if (puVar16 < local_2c0) {
      puVar56 = puVar16 + 1;
      *puVar16 = uVar11;
      local_2c8 = puVar56;
    }
    else {
      __n = (long)puVar16 - (long)local_2d0;
      uVar62 = __n + 1;
      if ((long)uVar62 < 0) {
                    /* WARNING: Subroutine does not return */
        FUN_00d64f48(&local_2d0);
      }
      uVar23 = ((long)local_2c0 - (long)local_2d0) * 2;
      if (uVar62 <= uVar23) {
        uVar62 = uVar23;
      }
      if (0x3ffffffffffffffe < (ulong)((long)local_2c0 - (long)local_2d0)) {
        uVar62 = 0x7fffffffffffffff;
      }
      if (uVar62 == 0) {
        puVar16 = (undefined1 *)0x0;
      }
      else {
        puVar16 = operator_new(uVar62);
      }
      puVar56 = puVar16 + __n + 1;
      puVar16[__n] = uVar11;
      if (0 < (long)__n) {
        memcpy(puVar16,__src,__n);
      }
      local_2d0 = puVar16;
      local_2c8 = puVar56;
      local_2c0 = puVar16 + uVar62;
      if (__src != (undefined1 *)0x0) {
        operator_delete(__src);
      }
    }
    pbVar15 = local_2b8;
    puVar16 = local_2d0;
    if (lVar63 == 0x7c) break;
    lVar63 = lVar63 + 4;
    puVar16 = puVar56;
  }
  uVar62 = (long)local_2b0 - (long)local_2b8;
  if (0x1f < uVar62) {
    lVar55 = *param_3;
    FUN_007750e0(&local_180,lVar55,local_2d0,1);
    local_210 = *(ulong *)(lVar55 + 0x10);
    uStack_208 = 0;
    FUN_007750e0(local_f8,&local_210,&local_180,0);
    bVar35 = local_f8[0x1e];
                    /* catch() { ... } // from try @ 0075aa28 with catch @ 0075ace4 */
    local_180 = (ulong)local_f8[0];
    uVar47 = (ulong)local_f8[0x11];
    local_178 = (ulong)local_f8[1];
    uVar51 = (ulong)local_f8[0x12];
    local_168 = (ulong)local_f8[3] & 0xf;
    uVar48 = (ulong)local_f8[0x13];
                    /* catch() { ... } // from try @ 0075a9bc with catch @ 0075ad04 */
    uVar36 = (ulong)local_f8[0x15];
    local_160 = (ulong)local_f8[4] & 0xfc;
    uVar49 = (ulong)local_f8[0x14];
    uVar28 = (ulong)local_f8[0x17];
                    /* catch() { ... } // from try @ 0075a950 with catch @ 0075ad24 */
    uVar37 = (ulong)local_f8[0x16];
    local_148 = (ulong)local_f8[7] & 0xf;
    uVar38 = (ulong)local_f8[0x18];
    uVar29 = (ulong)local_f8[0x19];
    uVar23 = (ulong)local_f8[0x10];
    local_140 = (ulong)local_f8[8] & 0xfc;
                    /* catch() { ... } // from try @ 0075af54 with catch @ 0075ad64 */
    uVar39 = (ulong)local_f8[0x1a];
    uVar30 = (ulong)local_f8[0x1b];
    local_158 = (ulong)local_f8[5];
    local_150 = (ulong)local_f8[6];
    local_138 = (ulong)local_f8[9];
    local_128 = (ulong)local_f8[0xb] & 0xf;
    uVar40 = (ulong)local_f8[0x1c];
    uVar31 = (ulong)local_f8[0x1d];
    local_130 = (ulong)local_f8[10];
    local_118 = (ulong)local_f8[0xd];
    local_110 = (ulong)local_f8[0xe];
    local_120 = (ulong)local_f8[0xc] & 0xfc;
    lVar63 = uVar62 - 0x20;
    local_170 = (ulong)local_f8[2];
    uVar26 = (uint)local_f8[0x1f];
    local_108 = (ulong)local_f8[0xf] & 0xf;
    local_190 = 0;
    uStack_208 = 0;
    local_210 = 0;
    uStack_1f8 = 0;
    local_200 = 0;
    uStack_1e8 = 0;
    local_1f0 = 0;
    uStack_1d8 = 0;
    local_1e0 = 0;
    uStack_1c8 = 0;
    local_1d0 = 0;
    local_1b8 = 0;
    local_1c0 = 0;
    uStack_1a8 = 0;
    local_1b0 = 0;
    local_198 = 0;
    local_1a0 = 0;
    local_100 = 0;
    if (lVar63 == 0) {
      uVar60 = 5;
      iVar27 = 0xfc;
    }
    else {
      lVar58 = 0;
      uVar41 = 0;
      uVar61 = 0;
      uVar43 = 0;
      uVar44 = 0;
      uVar45 = 0;
      uVar50 = 0;
      uVar52 = 0;
      uVar53 = 0;
      uVar54 = 0;
      uVar17 = 0;
      uVar18 = 0;
      uVar19 = 0;
      uVar20 = 0;
      uVar21 = 0;
      uVar46 = 0;
      uVar22 = 0;
      pbVar25 = pbVar15 + 0x20;
      do {
        lVar10 = lVar63;
        if (0xe < lVar63 - 1U) {
          lVar10 = 0x10;
        }
        local_220 = 0;
                    /* catch() { ... } // from try @ 0075af4c with catch @ 0075b028 */
        local_2a0[0xd] = 0;
        local_2a0[0xc] = 0;
        local_2a0[0xf] = 0;
        local_2a0[0xe] = 0;
        local_2a0[9] = 0;
        local_2a0[8] = 0;
        local_2a0[0xb] = 0;
        local_2a0[10] = 0;
        local_2a0[5] = 0;
        local_2a0[4] = 0;
        local_2a0[7] = 0;
        local_2a0[6] = 0;
        local_2a0[1] = 0;
        local_2a0[0] = 0;
        local_2a0[3] = 0;
        local_2a0[2] = 0;
        lVar32 = lVar10;
        puVar42 = local_2a0;
        pbVar59 = pbVar25;
        do {
          lVar32 = lVar32 + -1;
          *puVar42 = (ulong)*pbVar59;
          puVar42 = puVar42 + 1;
          pbVar59 = pbVar59 + 1;
        } while (lVar32 != 0);
                    /* catch() { ... } // from try @ 0075aecc with catch @ 0075b048 */
        local_2a0[lVar10] = 1;
        uVar60 = 0;
                    /* catch() { ... } // from try @ 0075ae50 with catch @ 0075b068 */
        uVar46 = uVar46 + (local_2a0[0] + uVar22 >> 8) + local_2a0[1];
        uVar21 = local_2a0[2] + uVar21 + (uVar46 >> 8);
        uVar20 = local_2a0[3] + uVar20 + (uVar21 >> 8);
        local_210 = local_2a0[0] + uVar22 & 0xff;
        uStack_208 = uVar46 & 0xff;
        uVar19 = local_2a0[4] + uVar19 + (uVar20 >> 8);
        local_200 = uVar21 & 0xff;
        uStack_1f8 = uVar20 & 0xff;
        uVar18 = local_2a0[5] + uVar18 + (uVar19 >> 8);
        uVar17 = local_2a0[6] + uVar17 + (uVar18 >> 8);
        local_1f0 = uVar19 & 0xff;
        uStack_1e8 = uVar18 & 0xff;
        uVar18 = local_2a0[7] + uVar54 + (uVar17 >> 8);
        uVar19 = local_2a0[8] + uVar53 + (uVar18 >> 8);
        local_1e0 = uVar17 & 0xff;
        uStack_1d8 = uVar18 & 0xff;
        uVar17 = local_2a0[9] + uVar52 + (uVar19 >> 8);
        uVar18 = local_2a0[10] + uVar50 + (uVar17 >> 8);
        local_1d0 = uVar19 & 0xff;
        uVar19 = local_2a0[0xb] + uVar45 + (uVar18 >> 8);
        uVar20 = local_2a0[0xc] + uVar44 + (uVar19 >> 8);
        uVar21 = local_2a0[0xd] + uVar43 + (uVar20 >> 8);
        uVar22 = local_2a0[0xe] + uVar61 + (uVar21 >> 8);
        uStack_1a8 = uVar21 & 0xff;
        uVar21 = local_2a0[0xf] + uVar41 + (uVar22 >> 8);
        local_198 = uVar21 & 0xff;
        puVar42 = &local_180;
        uStack_1c8 = uVar17 & 0xff;
        local_1c0 = uVar18 & 0xff;
        local_1b8 = uVar19 & 0xff;
        local_1b0 = uVar20 & 0xff;
        local_1a0 = uVar22 & 0xff;
        local_190 = (ulong)((int)local_220 + (int)lVar58 + ((uint)uVar21 >> 8)) & 0xff;
        do {
          lVar58 = 0;
          uVar17 = 0;
          pbVar59 = local_f8 + uVar60 * 8;
          pbVar59[0] = 0;
          pbVar59[1] = 0;
          pbVar59[2] = 0;
          pbVar59[3] = 0;
          pbVar59[4] = 0;
          pbVar59[5] = 0;
          pbVar59[6] = 0;
          pbVar59[7] = 0;
          puVar33 = puVar42;
          do {
            puVar1 = &local_210 + uVar17;
            if (uVar60 < uVar17) {
              uVar18 = puVar33[0x11] * 0x140;
            }
            else {
              uVar18 = *puVar33;
            }
            uVar17 = uVar17 + 1;
            lVar58 = lVar58 + uVar18 * *puVar1;
            puVar33 = puVar33 + -1;
          } while (uVar17 != 0x11);
          *(long *)(local_f8 + uVar60 * 8) = lVar58;
          uVar60 = uVar60 + 1;
          puVar42 = puVar42 + 1;
        } while (uVar60 != 0x11);
                    /* try { // try from 0075ae50 to 0085ae57 has its CatchHandler @ 0075b068 */
        uVar13 = CONCAT16(local_f8[6],
                          CONCAT15(local_f8[5],
                                   CONCAT14(local_f8[4],
                                            CONCAT13(local_f8[3],
                                                     CONCAT12(local_f8[2],
                                                              CONCAT11(local_f8[1],local_f8[0]))))))
        ;
        uVar17 = CONCAT17(local_f8[0xf],
                          CONCAT16(local_f8[0xe],
                                   CONCAT15(local_f8[0xd],
                                            CONCAT14(local_f8[0xc],
                                                     CONCAT13(local_f8[0xb],
                                                              CONCAT12(local_f8[10],
                                                                       CONCAT11(local_f8[9],
                                                                                local_f8[8]))))))) +
                 (CONCAT17(local_f8[7],uVar13) >> 8);
        uVar18 = CONCAT17(local_f8[0x17],
                          CONCAT16(local_f8[0x16],
                                   CONCAT15(local_f8[0x15],
                                            CONCAT14(local_f8[0x14],
                                                     CONCAT13(local_f8[0x13],
                                                              CONCAT12(local_f8[0x12],
                                                                       CONCAT11(local_f8[0x11],
                                                                                local_f8[0x10]))))))
                         ) + (uVar17 >> 8);
        uVar19 = CONCAT17(local_f8[0x1f],
                          CONCAT16(local_f8[0x1e],
                                   CONCAT15(local_f8[0x1d],
                                            CONCAT14(local_f8[0x1c],
                                                     CONCAT13(local_f8[0x1b],
                                                              CONCAT12(local_f8[0x1a],
                                                                       CONCAT11(local_f8[0x19],
                                                                                local_f8[0x18]))))))
                         ) + (uVar18 >> 8);
        uVar41 = local_d8 + (uVar19 >> 8);
        uVar43 = lStack_d0 + (uVar41 >> 8);
        uVar44 = local_c8 + (uVar43 >> 8);
        uVar45 = lStack_c0 + (uVar44 >> 8);
        uVar50 = local_b8 + (uVar45 >> 8);
                    /* try { // try from 0075aecc to 0085aed3 has its CatchHandler @ 0075b048 */
        uVar52 = lStack_b0 + (uVar50 >> 8);
        pbVar25 = pbVar25 + lVar10;
        lVar63 = lVar63 - lVar10;
        uVar61 = local_a8 + (uVar52 >> 8);
        uVar4 = lStack_a0 + (uVar61 >> 8);
        uVar5 = local_98 + (uVar4 >> 8);
        uVar6 = lStack_90 + (uVar5 >> 8);
        uVar7 = local_88 + (uVar6 >> 8);
        uVar8 = lStack_80 + (uVar7 >> 8);
        uVar9 = local_78 + (uVar8 >> 8);
        uVar60 = (uVar9 >> 2) * 5 + ((ulong)uVar13 & 0xff);
        uVar22 = uVar60 & 0xff;
        uVar60 = (uVar60 >> 8) + (uVar17 & 0xff);
        uVar46 = uVar60 & 0xff;
        uVar60 = (uVar60 >> 8) + (uVar18 & 0xff);
        uVar21 = uVar60 & 0xff;
        uVar60 = (uVar60 >> 8) + (uVar19 & 0xff);
        uVar20 = uVar60 & 0xff;
        uVar60 = (uVar60 >> 8) + (uVar41 & 0xff);
        uVar19 = uVar60 & 0xff;
                    /* try { // try from 0075af4c to 0085af53 has its CatchHandler @ 0075b028 */
        uVar60 = (uVar60 >> 8) + (uVar43 & 0xff);
        uVar18 = uVar60 & 0xff;
                    /* try { // try from 0075af54 to 0085b0db has its CatchHandler @ 0075ad64 */
        uVar60 = (uVar60 >> 8) + (uVar44 & 0xff);
        uVar17 = uVar60 & 0xff;
        uVar60 = (uVar60 >> 8) + (uVar45 & 0xff);
        uVar54 = uVar60 & 0xff;
        uVar60 = (uVar60 >> 8) + (uVar50 & 0xff);
        uVar53 = uVar60 & 0xff;
        uVar60 = (uVar60 >> 8) + (uVar52 & 0xff);
        uVar52 = uVar60 & 0xff;
        uVar60 = (uVar60 >> 8) + (uVar61 & 0xff);
        uVar50 = uVar60 & 0xff;
        uVar60 = (uVar60 >> 8) + (uVar4 & 0xff);
        uVar45 = uVar60 & 0xff;
        uVar60 = (uVar60 >> 8) + (uVar5 & 0xff);
        uVar44 = uVar60 & 0xff;
        uVar60 = (uVar60 >> 8) + (uVar6 & 0xff);
        uVar43 = uVar60 & 0xff;
        uVar60 = (uVar60 >> 8) + (uVar7 & 0xff);
        uVar61 = uVar60 & 0xff;
        uVar60 = (uVar60 >> 8) + (uVar8 & 0xff);
        uVar41 = uVar60 & 0xff;
        lVar58 = (uVar9 & 3) + (uVar60 >> 8);
      } while (lVar63 != 0);
      uVar60 = uVar22 + 5;
      iVar27 = (int)lVar58 + 0xfc;
      local_210 = uVar22;
      uStack_208 = uVar46;
      local_200 = uVar21;
      uStack_1f8 = uVar20;
      local_1f0 = uVar19;
      uStack_1e8 = uVar18;
      local_1e0 = uVar17;
      uStack_1d8 = uVar54;
      local_1d0 = uVar53;
      uStack_1c8 = uVar52;
      local_1c0 = uVar50;
      local_1b8 = uVar45;
      local_1b0 = uVar44;
      uStack_1a8 = uVar43;
      local_1a0 = uVar61;
      local_198 = uVar41;
      local_190 = lVar58;
    }
    uVar17 = uStack_208 + (uVar60 >> 8);
    uVar18 = local_200 + (uVar17 >> 8);
    uVar19 = uStack_1f8 + (uVar18 >> 8);
    uVar20 = local_1f0 + (uVar19 >> 8);
    uVar21 = uStack_1e8 + (uVar20 >> 8);
    uVar22 = local_1e0 + (uVar21 >> 8);
    uVar41 = uStack_1d8 + (uVar22 >> 8);
    uVar43 = local_1d0 + (uVar41 >> 8);
    uVar44 = uStack_1c8 + (uVar43 >> 8);
    uVar45 = local_1c0 + (uVar44 >> 8);
    uVar46 = local_1b8 + (uVar45 >> 8);
    uVar50 = local_1b0 + (uVar46 >> 8);
    uVar52 = uStack_1a8 + (uVar50 >> 8);
    lVar63 = local_1a0 + (uVar52 >> 8);
    uVar2 = (int)((ulong)lVar63 >> 8) + (uint)local_198;
    uVar53 = -((ulong)(iVar27 + (uVar2 >> 8) >> 7) & 1);
    uVar24 = (uint)uVar53;
    uVar23 = (local_210 & uVar53 | (ulong)((uint)uVar60 & (uVar24 ^ 0xffffffff)) & 0xff) + uVar23;
    uVar47 = (uStack_208 & uVar53 | (ulong)((uint)uVar17 & (uVar24 ^ 0xffffffff)) & 0xff) + uVar47 +
             (uVar23 >> 8);
    uVar51 = (local_200 & uVar53 | (ulong)((uint)uVar18 & (uVar24 ^ 0xffffffff)) & 0xff) + uVar51 +
             (uVar47 >> 8);
    uVar48 = (uStack_1f8 & uVar53 | (ulong)((uint)uVar19 & (uVar24 ^ 0xffffffff)) & 0xff) + uVar48 +
             (uVar51 >> 8);
    uVar49 = (local_1f0 & uVar53 | (ulong)((uint)uVar20 & (uVar24 ^ 0xffffffff)) & 0xff) + uVar49 +
             (uVar48 >> 8);
    uVar36 = (uStack_1e8 & uVar53 | (ulong)((uint)uVar21 & (uVar24 ^ 0xffffffff)) & 0xff) + uVar36 +
             (uVar49 >> 8);
    uVar37 = (local_1e0 & uVar53 | (ulong)((uint)uVar22 & (uVar24 ^ 0xffffffff)) & 0xff) + uVar37 +
             (uVar36 >> 8);
    uVar28 = (uStack_1d8 & uVar53 | (ulong)((uint)uVar41 & (uVar24 ^ 0xffffffff)) & 0xff) + uVar28 +
             (uVar37 >> 8);
    uVar38 = (local_1d0 & uVar53 | (ulong)((uint)uVar43 & (uVar24 ^ 0xffffffff)) & 0xff) + uVar38 +
             (uVar28 >> 8);
    uVar29 = (uStack_1c8 & uVar53 | (ulong)((uint)uVar44 & (uVar24 ^ 0xffffffff)) & 0xff) + uVar29 +
             (uVar38 >> 8);
    uVar39 = (local_1c0 & uVar53 | (ulong)((uint)uVar45 & (uVar24 ^ 0xffffffff)) & 0xff) + uVar39 +
             (uVar29 >> 8);
    uVar30 = (local_1b8 & uVar53 | (ulong)((uint)uVar46 & (uVar24 ^ 0xffffffff)) & 0xff) + uVar30 +
             (uVar39 >> 8);
    uVar40 = (local_1b0 & uVar53 | (ulong)((uint)uVar50 & (uVar24 ^ 0xffffffff)) & 0xff) + uVar40 +
             (uVar30 >> 8);
    lVar58 = (uStack_1a8 & uVar53 | (ulong)((uint)uVar52 & (uVar24 ^ 0xffffffff)) & 0xff) + uVar31 +
             (uVar40 >> 8);
    uVar3 = ((uint)local_1a0 & uVar24 | (uint)lVar63 & (uVar24 ^ 0xffffffff) & 0xff) + (uint)bVar35
            + (int)((ulong)lVar58 >> 8);
    if (((((uint)pbVar15[0x11] ^ (uint)uVar47 | (uint)pbVar15[0x10] ^ (uint)uVar23 |
           (uint)pbVar15[0x12] ^ (uint)uVar51 | (uint)pbVar15[0x13] ^ (uint)uVar48 |
           (uint)pbVar15[0x14] ^ (uint)uVar49 | (uint)pbVar15[0x15] ^ (uint)uVar36 |
           (uint)pbVar15[0x16] ^ (uint)uVar37 | (uint)pbVar15[0x17] ^ (uint)uVar28 |
           (uint)pbVar15[0x18] ^ (uint)uVar38 | (uint)pbVar15[0x19] ^ (uint)uVar29 |
           (uint)pbVar15[0x1a] ^ (uint)uVar39 | (uint)pbVar15[0x1b] ^ (uint)uVar30 |
           (uint)pbVar15[0x1c] ^ (uint)uVar40 | (uint)pbVar15[0x1d] ^ (uint)lVar58 |
           pbVar15[0x1e] ^ uVar3 |
          uVar26 + ((uint)local_198 & uVar24 | uVar2 & (uVar24 ^ 0xffffffff)) + (uVar3 >> 8) ^
          (uint)pbVar15[0x1f]) & 0xff) - 1 >> 8 & 1) != 0) {
      FUN_007750e0(&local_180,lVar55,puVar16,1);
      local_210 = *(ulong *)(lVar55 + 0x10);
      uStack_208 = 0;
      pbVar25 = pbVar15;
      pbVar59 = pbVar15;
      while (0x3f < uVar62) {
        FUN_007750e0(local_f8,&local_210,&local_180,0);
        uVar23 = uStack_208;
        lVar63 = 0;
        do {
          if (pbVar59 == (byte *)0x0) {
            bVar35 = 0;
          }
          else {
            bVar35 = pbVar59[lVar63];
          }
          pbVar25[lVar63] = local_f8[lVar63] ^ bVar35;
          lVar63 = lVar63 + 1;
        } while (lVar63 != 0x40);
        uVar30 = uStack_208 >> 0x10;
        uVar62 = uVar62 - 0x40;
        uVar28 = (uStack_208 & 0xff) + 1;
        uVar29 = (uStack_208 >> 8 & 0xff) + (uVar28 >> 8);
        uVar31 = uStack_208 >> 0x18;
        uVar30 = (uVar30 & 0xff) + (uVar29 >> 8);
        uVar31 = (uVar31 & 0xff) + (uVar30 >> 8);
        uVar36 = (uVar23 >> 0x20 & 0xff) + (uVar31 >> 8);
        uVar37 = (uVar23 >> 0x28 & 0xff) + (uVar36 >> 8);
        uStack_208._7_1_ = SUB81(uVar23,7);
        lVar63 = (uVar23 >> 0x30 & 0xff) + (uVar37 >> 8);
        bVar14 = pbVar59 != (byte *)0x0;
        pbVar34 = pbVar59 + 0x40;
        pbVar59 = (byte *)0x0;
        if (bVar14) {
          pbVar59 = pbVar34;
        }
        pbVar25 = pbVar25 + 0x40;
        uStack_208 = CONCAT17(uStack_208._7_1_ + (char)((ulong)lVar63 >> 8),
                              CONCAT16((char)lVar63,
                                       CONCAT15((char)uVar37,
                                                CONCAT14((char)uVar36,
                                                         CONCAT13((char)uVar31,
                                                                  CONCAT12((char)uVar30,
                                                                           CONCAT11((char)uVar29,
                                                                                    (char)uVar28))))
                                               )));
      }
      if (uVar62 != 0) {
        FUN_007750e0(local_f8,&local_210,&local_180,0);
        pbVar34 = pbVar59;
        pbVar57 = local_f8;
        do {
          if (pbVar59 == (byte *)0x0) {
            bVar35 = 0;
          }
          else {
            bVar35 = *pbVar34;
          }
          uVar62 = uVar62 - 1;
          pbVar34 = pbVar34 + 1;
          *pbVar25 = *pbVar57 ^ bVar35;
          pbVar57 = pbVar57 + 1;
          pbVar25 = pbVar25 + 1;
        } while (uVar62 != 0);
      }
      pbVar15[8] = 0;
      pbVar15[9] = 0;
      pbVar15[10] = 0;
      pbVar15[0xb] = 0;
      pbVar15[0xc] = 0;
      pbVar15[0xd] = 0;
      pbVar15[0xe] = 0;
      pbVar15[0xf] = 0;
      pbVar15[0] = 0;
      pbVar15[1] = 0;
      pbVar15[2] = 0;
      pbVar15[3] = 0;
      pbVar15[4] = 0;
      pbVar15[5] = 0;
      pbVar15[6] = 0;
      pbVar15[7] = 0;
      pbVar15[0x18] = 0;
      pbVar15[0x19] = 0;
      pbVar15[0x1a] = 0;
      pbVar15[0x1b] = 0;
      pbVar15[0x1c] = 0;
      pbVar15[0x1d] = 0;
      pbVar15[0x1e] = 0;
      pbVar15[0x1f] = 0;
      pbVar15[0x10] = 0;
      pbVar15[0x11] = 0;
      pbVar15[0x12] = 0;
      pbVar15[0x13] = 0;
      pbVar15[0x14] = 0;
      pbVar15[0x15] = 0;
      pbVar15[0x16] = 0;
      pbVar15[0x17] = 0;
    }
  }
  pbVar15 = local_2b8;
  lVar63 = ((ulong)(uint)((int)local_2b0 - (int)local_2b8) << 0x20) + -0x2000000000;
  uVar62 = lVar63 >> 0x20;
  *param_1 = 0;
  param_1[1] = 0;
  param_1[2] = 0;
  if (lVar63 == 0) {
    __s = (void *)0x0;
  }
  else {
    if (lVar63 < 0) {
                    /* WARNING: Subroutine does not return */
      FUN_00d64f48(param_1);
    }
    __s = operator_new(uVar62);
    *param_1 = __s;
    param_1[2] = (long)__s + uVar62;
    memset(__s,0,uVar62);
    param_1[1] = (long)__s + uVar62;
  }
  memcpy(__s,pbVar15 + 0x20,uVar62);
  if (local_2d0 != (undefined1 *)0x0) {
    local_2c8 = local_2d0;
    operator_delete(local_2d0);
  }
  if (pbVar15 != (byte *)0x0) {
    local_2b0 = pbVar15;
    operator_delete(pbVar15);
  }
  if (*(long *)(lVar12 + 0x28) != local_70) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: nama::AuthUtil::CheckAccess
 * Address : 00e6a6e0
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void nama::AuthUtil::CheckAccess(void)

{
  (*(code *)PTR_CheckAccess_00eac510)();
  return;
}



/* ------------------------------------------------------------------
 * Function: CNamaSDK::BundleHelper::ContainMagicCode
 * Address : 00e6a850
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void CNamaSDK::BundleHelper::ContainMagicCode(basic_string *param_1)

{
  (*(code *)PTR_ContainMagicCode_00eac5c8)();
  return;
}



/* ------------------------------------------------------------------
 * Function: CNamaSDK::BundleHelper::DecryptObfuscatedPackageAndVerify
 * Address : 00e6a870
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void CNamaSDK::BundleHelper::DecryptObfuscatedPackageAndVerify
               (uchar *param_1,int param_2,vector *param_3)

{
  (*(code *)PTR_DecryptObfuscatedPackageAndVerify_00eac5d8)(param_1,param_2);
  return;
}



/* ------------------------------------------------------------------
 * Function: CNamaSDK::BundleHelper::VerifySignature
 * Address : 00e6a890
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void CNamaSDK::BundleHelper::VerifySignature(vector *param_1,int *param_2)

{
  (*(code *)PTR_VerifySignature_00eac5e8)();
  return;
}



/* ------------------------------------------------------------------
 * Function: CNamaSDK::BundleHelper::DecryptObfuscatedPackage
 * Address : 00e6ab80
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void CNamaSDK::BundleHelper::DecryptObfuscatedPackage(uchar *param_1,int param_2,vector *param_3)

{
                    /* try { // try from 00e6ab8c to 00f6ab97 has its CatchHandler @ 00e6b024 */
  (*(code *)PTR_DecryptObfuscatedPackage_00eac760)(param_1,param_2);
  return;
}



/* ------------------------------------------------------------------
 * Function: nama::AuthUtil::AuthCheck
 * Address : 00e6aed0
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void nama::AuthUtil::AuthCheck(int param_1)

{
                    /* try { // try from 00e6aed4 to 00f6aedf has its CatchHandler @ 00e6afdc */
  (*(code *)PTR_AuthCheck_00eac908)(param_1);
  return;
}



/* ------------------------------------------------------------------
 * Function: nama::AuthUtil::DebugCountDown
 * Address : 00e6b180
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void nama::AuthUtil::DebugCountDown(void)

{
  (*(code *)PTR_DebugCountDown_00eaca60)();
  return;
}



/* ------------------------------------------------------------------
 * Function: CNamaSDK::BundleHelper::DecryptObfuscatedPackageTF
 * Address : 00e6b2a0
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void CNamaSDK::BundleHelper::DecryptObfuscatedPackageTF(vector *param_1,vector *param_2)

{
                    /* try { // try from 00e6b2ac to 00f6b2bf has its CatchHandler @ 00e6b344 */
  (*(code *)PTR_DecryptObfuscatedPackageTF_00eacaf0)();
  return;
}



/* ------------------------------------------------------------------
 * Function: nama::AuthUtil::PrintAuthInfo
 * Address : 00e6de00
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void nama::AuthUtil::PrintAuthInfo(void)

{
  (*(code *)PTR_PrintAuthInfo_00eae0a0)();
  return;
}



/* ------------------------------------------------------------------
 * Function: FuAIWrapper::DecryptObfuscatedPackageTF
 * Address : 00e70c10
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void FuAIWrapper::DecryptObfuscatedPackageTF(void)

{
  (*(code *)PTR_DecryptObfuscatedPackageTF_00eaf7a8)();
  return;
}



