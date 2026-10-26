/*
 * Ghidra headless decompilation
 * program   : libbytenn.so
 * executable: /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/tiktok_trill/native/libbytenn.so
 * language  : AARCH64:LE:64:v8A
 * imagebase : 00100000
 * regex     : EspressoDisassembler|ByteNNEngineImpl4Init|CheckMd5sum
 * max funcs : 12
 * note      : pseudocode from a (possibly stripped) binary is approximate;
 *             indirect calls and register-indirect JNI tables are lost.
 */

/* ------------------------------------------------------------------
 * Function: BYTENN::CheckMd5sum
 * Address : 0012f920
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void BYTENN::CheckMd5sum(uchar *param_1,int param_2)

{
  (*(code *)PTR_CheckMd5sum_002dda30)(param_1,param_2);
  return;
}



/* ------------------------------------------------------------------
 * Function: BYTENN::EspressoDisassembler::disassembleModelBuffer
 * Address : 0012ff50
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void BYTENN::EspressoDisassembler::disassembleModelBuffer(ByteNNInternalConfigExt *param_1)

{
  (*(code *)PTR_disassembleModelBuffer_002ddd48)();
  return;
}



/* ------------------------------------------------------------------
 * Function: BYTENN::EspressoDisassembler::disassemble
 * Address : 00130870
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void BYTENN::EspressoDisassembler::disassemble(ByteNNInternalConfig *param_1)

{
  (*(code *)PTR_disassemble_002de1d8)();
  return;
}



/* ------------------------------------------------------------------
 * Function: BYTENN::EspressoDisassembler::disassembleModels
 * Address : 001308a0
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void BYTENN::EspressoDisassembler::disassembleModels(ByteNNInternalConfigExt *param_1)

{
  (*(code *)PTR_disassembleModels_002de1f0)();
  return;
}



/* ------------------------------------------------------------------
 * Function: BYTENN::CheckMd5sum
 * Address : 00130eb0
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void BYTENN::CheckMd5sum(void)

{
  (*(code *)PTR_CheckMd5sum_002de4f8)();
  return;
}



/* ------------------------------------------------------------------
 * Function: BYTENN::EspressoDisassembler::disassemble
 * Address : 00130f50
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void BYTENN::EspressoDisassembler::disassemble(ByteNNInternalConfigExt *param_1)

{
  (*(code *)PTR_disassemble_002de548)();
  return;
}



/* ------------------------------------------------------------------
 * Function: BYTENN::CheckMd5sum
 * Address : 00142270
 * Size    : 0x1fc bytes
 * ------------------------------------------------------------------ */

/* BYTENN::CheckMd5sum(unsigned char const*, int) */

void BYTENN::CheckMd5sum(uchar *param_1,int param_2)

{
  uint uVar1;
  uchar *puVar2;
  long lVar3;
  undefined8 uVar4;
  ulong uVar5;
  uint uVar6;
  int *piVar7;
  uchar local_38;
  uchar local_37;
  uchar local_36;
  uchar local_35;
  uchar local_34;
  uchar local_33;
  uchar local_32;
  uchar local_31;
  uchar local_30;
  uchar local_2f;
  uchar local_2e;
  uchar local_2d;
  uchar local_2c;
  uchar local_2b;
  uchar local_2a;
  uchar local_29;
  long local_28;
  
  lVar3 = tpidr_el0;
  local_28 = *(long *)(lVar3 + 0x28);
  if (*(uint *)(param_1 + 8) != 0) {
    uVar6 = 0;
    piVar7 = (int *)(param_1 + 0xc);
    uVar5 = 0xc;
    if (param_2 == 2) goto LAB_001422cc;
    do {
      uVar1 = (int)uVar5 + *piVar7 + 8;
      while( true ) {
        uVar5 = (ulong)uVar1;
        uVar6 = uVar6 + 1;
        piVar7 = piVar7 + 2;
        if (*(uint *)(param_1 + 8) <= uVar6) goto LAB_001422e0;
        if (param_2 != 2) break;
LAB_001422cc:
        if (uVar6 != 2) break;
        uVar1 = (int)uVar5 + 0x10;
      }
    } while( true );
  }
  uVar5 = 0xc;
LAB_001422e0:
  if ((int)uVar5 + 0x10 == *(int *)(param_1 + 4)) {
    puVar2 = param_1 + uVar5;
    md5(param_1,uVar5,&local_38);
    if (((((*puVar2 == local_38) && (puVar2[1] == local_37)) && (puVar2[2] == local_36)) &&
        (((puVar2[3] == local_35 && (puVar2[4] == local_34)) &&
         ((puVar2[5] == local_33 && ((puVar2[6] == local_32 && (puVar2[7] == local_31)))))))) &&
       ((puVar2[8] == local_30 &&
        (((((puVar2[9] == local_2f && (puVar2[10] == local_2e)) && (puVar2[0xb] == local_2d)) &&
          ((puVar2[0xc] == local_2c && (puVar2[0xd] == local_2b)))) &&
         ((puVar2[0xe] == local_2a && (puVar2[0xf] == local_29)))))))) {
LAB_00142424:
      uVar4 = 0;
      goto LAB_00142448;
    }
  }
  else if ((int)uVar5 == *(int *)(param_1 + 4)) {
    ByteNNLog(0,"ByteNN","This model has NOT been inserted validation information.");
    goto LAB_00142424;
  }
  ByteNNLog(1,"ByteNN","model size check failed.");
  uVar4 = 10;
LAB_00142448:
  if (*(long *)(lVar3 + 0x28) != local_28) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail(uVar4);
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: BYTENN::CheckMd5sum
 * Address : 0014246c
 * Size    : 0x94 bytes
 * ------------------------------------------------------------------ */

/* BYTENN::CheckMd5sum(std::__ndk1::map<std::__ndk1::basic_string<char,
   std::__ndk1::char_traits<char>, std::__ndk1::allocator<char> >, unsigned char const*,
   std::__ndk1::less<std::__ndk1::basic_string<char, std::__ndk1::char_traits<char>,
   std::__ndk1::allocator<char> > >,
   std::__ndk1::allocator<std::__ndk1::pair<std::__ndk1::basic_string<char,
   std::__ndk1::char_traits<char>, std::__ndk1::allocator<char> > const, unsigned char const*> > >)
    */

undefined8 BYTENN::CheckMd5sum(long *param_1)

{
  bool bVar1;
  undefined8 uVar2;
  long *plVar3;
  long lVar4;
  long *plVar5;
  
  plVar5 = (long *)*param_1;
  while( true ) {
    if (param_1 + 1 == plVar5) {
      return 0;
    }
    uVar2 = CheckMd5sum((uchar *)plVar5[7],(uint)((uchar *)plVar5[7])[3]);
    if ((int)uVar2 != 0) break;
    plVar3 = (long *)plVar5[1];
    if ((long *)plVar5[1] == (long *)0x0) {
      plVar3 = plVar5 + 2;
      bVar1 = (long *)*(long *)*plVar3 != plVar5;
      plVar5 = (long *)*plVar3;
      if (bVar1) {
        do {
          lVar4 = *plVar3;
          plVar3 = (long *)(lVar4 + 0x10);
          plVar5 = (long *)*plVar3;
        } while (*plVar5 != lVar4);
      }
    }
    else {
      do {
        plVar5 = plVar3;
        plVar3 = (long *)*plVar5;
      } while ((long *)*plVar5 != (long *)0x0);
    }
  }
  return uVar2;
}



/* ------------------------------------------------------------------
 * Function: BYTENN::EspressoDisassembler::disassemble
 * Address : 00142500
 * Size    : 0x5f0 bytes
 * ------------------------------------------------------------------ */

/* BYTENN::EspressoDisassembler::disassemble(BYTENN::ByteNNInternalConfig&) */

undefined8 BYTENN::EspressoDisassembler::disassemble(ByteNNInternalConfig *param_1)

{
  char *pcVar1;
  uint uVar2;
  byte bVar3;
  char cVar4;
  bool bVar5;
  undefined *puVar6;
  undefined8 uVar7;
  size_t sVar8;
  char *pcVar9;
  long *plVar10;
  long lVar11;
  ulong uVar12;
  ulong uVar13;
  byte *pbVar14;
  char *pcVar15;
  uchar *puVar16;
  long *plVar17;
  ByteNNInternalConfig *pBVar18;
  void *pvVar19;
  ulong uVar20;
  long lVar21;
  long lVar22;
  uint *puVar23;
  uint *puVar24;
  uint *puVar25;
  undefined8 uVar26;
  undefined8 uVar27;
  undefined8 uVar28;
  undefined8 uVar29;
  undefined8 uVar30;
  undefined8 uVar31;
  undefined8 uVar32;
  ulong local_78;
  size_t sStack_70;
  void *local_68;
  
  puVar16 = *(uchar **)(param_1 + 8);
  if ((puVar16 == (uchar *)0x0) || (*(int *)(param_1 + 0x10) < 1)) {
    ByteNNLog(1,"ByteNN","config error: model buffer is null(%p) or size is invalid(%d).",puVar16);
    return 10;
  }
  if (*(int *)(puVar16 + 4) != *(int *)(param_1 + 0x10)) {
    ByteNNLog(1,"ByteNN","model size check failed: %d!=%d.");
    return 10;
  }
  bVar3 = puVar16[3];
  if ((param_1[0x14] != (ByteNNInternalConfig)0x0) &&
     (uVar7 = CheckMd5sum(puVar16,(uint)bVar3), (int)uVar7 != 0)) {
    return uVar7;
  }
  if (*puVar16 != 'B') {
    return 10;
  }
  if (puVar16[1] != 'M') {
    return 10;
  }
  lVar22 = *(long *)(param_1 + 8);
  if (bVar3 == 1) {
    sStack_70 = 0;
    local_68 = (void *)0x0;
    local_78 = 0;
    pcVar9 = (char *)(lVar22 + (ulong)*(uint *)(lVar22 + 0x10));
    sVar8 = strlen(pcVar9);
    if (0xffffffffffffffef < sVar8) goto LAB_00142aec;
    if (sVar8 < 0x17) {
      pvVar19 = (void *)((ulong)&local_78 | 1);
      local_78 = CONCAT71(local_78._1_7_,(char)((int)sVar8 << 1));
      if (sVar8 != 0) goto LAB_001426f0;
    }
    else {
      uVar20 = sVar8 + 0x10 & 0xfffffffffffffff0;
      pvVar19 = operator_new(uVar20);
      local_78 = uVar20 | 1;
      sStack_70 = sVar8;
      local_68 = pvVar19;
LAB_001426f0:
      memcpy(pvVar19,pcVar9,sVar8);
    }
    *(undefined1 *)((long)pvVar19 + sVar8) = 0;
    if (((byte)param_1[0xf8] & 1) == 0) {
      *(undefined2 *)(param_1 + 0xf8) = 0;
    }
    else {
      **(undefined1 **)(param_1 + 0x108) = 0;
      *(undefined8 *)(param_1 + 0x100) = 0;
      if (((byte)param_1[0xf8] & 1) != 0) {
        operator_delete(*(void **)(param_1 + 0x108));
        *(undefined8 *)(param_1 + 0xf8) = 0;
      }
    }
    puVar23 = (uint *)0x0;
    puVar24 = (uint *)0x0;
    puVar25 = (uint *)0x0;
LAB_00142744:
    *(void **)(param_1 + 0x108) = local_68;
    *(size_t *)(param_1 + 0x100) = sStack_70;
    *(ulong *)(param_1 + 0xf8) = local_78;
  }
  else {
    uVar2 = *(uint *)(puVar16 + 8);
    puVar25 = (uint *)(lVar22 + 0x24);
    puVar24 = (uint *)(lVar22 + 0x2c);
    puVar23 = (uint *)(lVar22 + 0x34);
    if (uVar2 < 4) {
      puVar25 = (uint *)0x0;
    }
    if (uVar2 < 5) {
      puVar24 = (uint *)0x0;
    }
    if (uVar2 < 6) {
      puVar23 = (uint *)0x0;
    }
    if ((bVar3 != 2) && ((bVar3 < 3 || (*(int *)(lVar22 + 0x1c) == 0)))) {
      sStack_70 = 0;
      local_68 = (void *)0x0;
      local_78 = 0;
      pcVar9 = (char *)(lVar22 + (ulong)*(uint *)(lVar22 + 0x10));
      sVar8 = strlen(pcVar9);
      if (0xffffffffffffffef < sVar8) goto LAB_00142aec;
      if (sVar8 < 0x17) {
        pvVar19 = (void *)((ulong)&local_78 | 1);
        local_78 = CONCAT71(local_78._1_7_,(char)((int)sVar8 << 1));
        if (sVar8 != 0) goto LAB_001427bc;
      }
      else {
        uVar20 = sVar8 + 0x10 & 0xfffffffffffffff0;
        pvVar19 = operator_new(uVar20);
        local_78 = uVar20 | 1;
        sStack_70 = sVar8;
        local_68 = pvVar19;
LAB_001427bc:
        memcpy(pvVar19,pcVar9,sVar8);
      }
      *(undefined1 *)((long)pvVar19 + sVar8) = 0;
      if (((byte)param_1[0xf8] & 1) == 0) {
        *(undefined2 *)(param_1 + 0xf8) = 0;
      }
      else {
        **(undefined1 **)(param_1 + 0x108) = 0;
        *(undefined8 *)(param_1 + 0x100) = 0;
        if (((byte)param_1[0xf8] & 1) != 0) {
          operator_delete(*(void **)(param_1 + 0x108));
          *(undefined8 *)(param_1 + 0xf8) = 0;
        }
      }
      goto LAB_00142744;
    }
    uVar2 = *(uint *)(lVar22 + 0x10);
    pcVar9 = malloc((ulong)*(uint *)(lVar22 + 0xc));
    memcpy(pcVar9,(void *)(lVar22 + (ulong)uVar2),(ulong)*(uint *)(lVar22 + 0xc));
    uVar2 = *(uint *)(lVar22 + 0xc);
    uVar20 = (ulong)uVar2;
    if (uVar2 != 0) {
      uVar12 = 0;
      pcVar1 = (char *)(lVar22 + (ulong)*(uint *)(lVar22 + 0x20));
      if (uVar2 < 0x20) goto LAB_0014283c;
      if (7 < uVar20 - 1) goto LAB_0014283c;
      if ((pcVar9 < (char *)(lVar22 + uVar20 + *(uint *)(lVar22 + 0x20))) &&
         (pcVar1 < pcVar9 + uVar20)) {
        uVar12 = 0;
        goto LAB_0014283c;
      }
      uVar26 = *(undefined8 *)(pcVar1 + 8);
      uVar7 = *(undefined8 *)pcVar1;
      uVar28 = *(undefined8 *)(pcVar1 + 0x18);
      uVar27 = *(undefined8 *)(pcVar1 + 0x10);
      uVar12 = uVar20 & 0xffffffe0;
      pcVar15 = pcVar9 + 0x10;
      uVar13 = uVar12;
      do {
        uVar30 = *(undefined8 *)(pcVar15 + -8);
        uVar29 = *(undefined8 *)(pcVar15 + -0x10);
        uVar32 = *(undefined8 *)(pcVar15 + 8);
        uVar31 = *(undefined8 *)pcVar15;
        uVar13 = uVar13 - 0x20;
        *(ulong *)(pcVar15 + -8) =
             CONCAT17((byte)((ulong)uVar26 >> 0x38) ^ (byte)((ulong)uVar30 >> 0x38),
                      CONCAT16((byte)((ulong)uVar26 >> 0x30) ^ (byte)((ulong)uVar30 >> 0x30),
                               CONCAT15((byte)((ulong)uVar26 >> 0x28) ^
                                        (byte)((ulong)uVar30 >> 0x28),
                                        CONCAT14((byte)((ulong)uVar26 >> 0x20) ^
                                                 (byte)((ulong)uVar30 >> 0x20),
                                                 CONCAT13((byte)((ulong)uVar26 >> 0x18) ^
                                                          (byte)((ulong)uVar30 >> 0x18),
                                                          CONCAT12((byte)((ulong)uVar26 >> 0x10) ^
                                                                   (byte)((ulong)uVar30 >> 0x10),
                                                                   CONCAT11((byte)((ulong)uVar26 >>
                                                                                  8) ^
                                                                            (byte)((ulong)uVar30 >>
                                                                                  8),(byte)uVar26 ^
                                                                                     (byte)uVar30)))
                                                ))));
        *(ulong *)(pcVar15 + -0x10) =
             CONCAT17((byte)((ulong)uVar7 >> 0x38) ^ (byte)((ulong)uVar29 >> 0x38),
                      CONCAT16((byte)((ulong)uVar7 >> 0x30) ^ (byte)((ulong)uVar29 >> 0x30),
                               CONCAT15((byte)((ulong)uVar7 >> 0x28) ^ (byte)((ulong)uVar29 >> 0x28)
                                        ,CONCAT14((byte)((ulong)uVar7 >> 0x20) ^
                                                  (byte)((ulong)uVar29 >> 0x20),
                                                  CONCAT13((byte)((ulong)uVar7 >> 0x18) ^
                                                           (byte)((ulong)uVar29 >> 0x18),
                                                           CONCAT12((byte)((ulong)uVar7 >> 0x10) ^
                                                                    (byte)((ulong)uVar29 >> 0x10),
                                                                    CONCAT11((byte)((ulong)uVar7 >>
                                                                                   8) ^
                                                                             (byte)((ulong)uVar29 >>
                                                                                   8),(byte)uVar7 ^
                                                                                      (byte)uVar29))
                                                          )))));
        *(ulong *)(pcVar15 + 8) =
             CONCAT17((byte)((ulong)uVar28 >> 0x38) ^ (byte)((ulong)uVar32 >> 0x38),
                      CONCAT16((byte)((ulong)uVar28 >> 0x30) ^ (byte)((ulong)uVar32 >> 0x30),
                               CONCAT15((byte)((ulong)uVar28 >> 0x28) ^
                                        (byte)((ulong)uVar32 >> 0x28),
                                        CONCAT14((byte)((ulong)uVar28 >> 0x20) ^
                                                 (byte)((ulong)uVar32 >> 0x20),
                                                 CONCAT13((byte)((ulong)uVar28 >> 0x18) ^
                                                          (byte)((ulong)uVar32 >> 0x18),
                                                          CONCAT12((byte)((ulong)uVar28 >> 0x10) ^
                                                                   (byte)((ulong)uVar32 >> 0x10),
                                                                   CONCAT11((byte)((ulong)uVar28 >>
                                                                                  8) ^
                                                                            (byte)((ulong)uVar32 >>
                                                                                  8),(byte)uVar28 ^
                                                                                     (byte)uVar32)))
                                                ))));
        *(ulong *)pcVar15 =
             CONCAT17((byte)((ulong)uVar27 >> 0x38) ^ (byte)((ulong)uVar31 >> 0x38),
                      CONCAT16((byte)((ulong)uVar27 >> 0x30) ^ (byte)((ulong)uVar31 >> 0x30),
                               CONCAT15((byte)((ulong)uVar27 >> 0x28) ^
                                        (byte)((ulong)uVar31 >> 0x28),
                                        CONCAT14((byte)((ulong)uVar27 >> 0x20) ^
                                                 (byte)((ulong)uVar31 >> 0x20),
                                                 CONCAT13((byte)((ulong)uVar27 >> 0x18) ^
                                                          (byte)((ulong)uVar31 >> 0x18),
                                                          CONCAT12((byte)((ulong)uVar27 >> 0x10) ^
                                                                   (byte)((ulong)uVar31 >> 0x10),
                                                                   CONCAT11((byte)((ulong)uVar27 >>
                                                                                  8) ^
                                                                            (byte)((ulong)uVar31 >>
                                                                                  8),(byte)uVar27 ^
                                                                                     (byte)uVar31)))
                                                ))));
        pcVar15 = pcVar15 + 0x20;
      } while (uVar13 != 0);
      for (; uVar12 != uVar20; uVar12 = uVar12 + 1) {
LAB_0014283c:
        pcVar9[uVar12] = pcVar1[uVar12 & 7] ^ pcVar9[uVar12];
      }
      uVar2 = *(uint *)(lVar22 + 0xc);
      uVar20 = (ulong)uVar2;
      if (0 < (int)uVar2) {
        if (uVar2 == 1) {
          uVar13 = 0;
        }
        else {
          uVar13 = uVar20 & 0xfffffffe;
          pbVar14 = (byte *)(pcVar9 + 1);
          uVar12 = uVar13;
          do {
            uVar12 = uVar12 - 2;
            bVar3 = (&DAT_00284b32)[*pbVar14];
            pbVar14[-1] = (&DAT_00284b32)[pbVar14[-1]];
            *pbVar14 = bVar3;
            pbVar14 = pbVar14 + 2;
          } while (uVar12 != 0);
          if (uVar13 == uVar20) goto LAB_001428d0;
        }
        lVar21 = uVar20 - uVar13;
        pbVar14 = (byte *)(pcVar9 + uVar13);
        do {
          lVar21 = lVar21 + -1;
          *pbVar14 = (&DAT_00284b32)[*pbVar14];
          pbVar14 = pbVar14 + 1;
        } while (lVar21 != 0);
      }
    }
LAB_001428d0:
    sStack_70 = 0;
    local_68 = (void *)0x0;
    local_78 = 0;
    sVar8 = strlen(pcVar9);
    if (0xffffffffffffffef < sVar8) {
LAB_00142aec:
                    /* WARNING: Subroutine does not return */
      abort();
    }
    if (sVar8 < 0x17) {
      pvVar19 = (void *)((ulong)&local_78 | 1);
      local_78 = CONCAT71(local_78._1_7_,(char)((int)sVar8 << 1));
      if (sVar8 != 0) goto LAB_0014292c;
    }
    else {
      uVar20 = sVar8 + 0x10 & 0xfffffffffffffff0;
      pvVar19 = operator_new(uVar20);
      local_78 = uVar20 | 1;
      sStack_70 = sVar8;
      local_68 = pvVar19;
LAB_0014292c:
      memcpy(pvVar19,pcVar9,sVar8);
    }
    *(undefined1 *)((long)pvVar19 + sVar8) = 0;
    pBVar18 = param_1 + 0xf8;
    if (((byte)*pBVar18 & 1) == 0) {
      *(undefined2 *)pBVar18 = 0;
    }
    else {
      **(undefined1 **)(param_1 + 0x108) = 0;
      *(undefined8 *)(param_1 + 0x100) = 0;
      if (((byte)param_1[0xf8] & 1) != 0) {
        operator_delete(*(void **)(param_1 + 0x108));
        *(undefined8 *)(param_1 + 0xf8) = 0;
      }
    }
    *(void **)(param_1 + 0x108) = local_68;
    *(size_t *)(param_1 + 0x100) = sStack_70;
    *(ulong *)pBVar18 = local_78;
    free(pcVar9);
  }
  lVar21 = *(long *)(param_1 + 8);
  uVar2 = *(uint *)(lVar22 + 0x18);
  pvVar19 = operator_new__((ulong)*(uint *)(lVar22 + 0x14),(nothrow_t *)PTR_nothrow_002deb70);
  plVar10 = operator_new(0x20);
  puVar6 = PTR_vtable_002dea30;
  plVar10[2] = 0;
  plVar10[3] = (long)pvVar19;
  *plVar10 = (long)(puVar6 + 0x10);
  plVar10[1] = 0;
  plVar17 = *(long **)(param_1 + 200);
  *(void **)(param_1 + 0xc0) = pvVar19;
  *(long **)(param_1 + 200) = plVar10;
  if (plVar17 != (long *)0x0) {
    plVar10 = plVar17 + 1;
    do {
      lVar11 = *plVar10;
      cVar4 = '\x01';
      bVar5 = (bool)ExclusiveMonitorPass(plVar10,0x10);
      if (bVar5) {
        *plVar10 = lVar11 + -1;
        cVar4 = ExclusiveMonitorsStatus();
      }
    } while (cVar4 != '\0');
    if (lVar11 == 0) {
      (**(code **)(*plVar17 + 0x10))(plVar17);
      std::__ndk1::__shared_weak_count::__release_weak();
    }
  }
  if (*(void **)(param_1 + 0xc0) == (void *)0x0) {
LAB_00142ae4:
    uVar7 = 1;
  }
  else {
    memcpy(*(void **)(param_1 + 0xc0),(void *)(lVar21 + (ulong)uVar2),
           (ulong)*(uint *)(lVar22 + 0x14));
    *(ulong *)(param_1 + 0xe0) = (ulong)*(uint *)(lVar22 + 0x14);
    if (puVar25 != (uint *)0x0) {
      lVar22 = *(long *)(param_1 + 8);
      uVar2 = puVar25[1];
      pvVar19 = operator_new__((ulong)*puVar25,(nothrow_t *)PTR_nothrow_002deb70);
      plVar10 = operator_new(0x20);
      *plVar10 = (long)(puVar6 + 0x10);
      plVar10[1] = 0;
      plVar10[2] = 0;
      plVar10[3] = (long)pvVar19;
      plVar17 = *(long **)(param_1 + 0x118);
      *(void **)(param_1 + 0x110) = pvVar19;
      *(long **)(param_1 + 0x118) = plVar10;
      if (plVar17 != (long *)0x0) {
        plVar10 = plVar17 + 1;
        do {
          lVar21 = *plVar10;
          cVar4 = '\x01';
          bVar5 = (bool)ExclusiveMonitorPass(plVar10,0x10);
          if (bVar5) {
            *plVar10 = lVar21 + -1;
            cVar4 = ExclusiveMonitorsStatus();
          }
        } while (cVar4 != '\0');
        if (lVar21 == 0) {
          (**(code **)(*plVar17 + 0x10))(plVar17);
          std::__ndk1::__shared_weak_count::__release_weak();
        }
      }
      if (*(void **)(param_1 + 0x110) == (void *)0x0) goto LAB_00142ae4;
      memcpy(*(void **)(param_1 + 0x110),(void *)(lVar22 + (ulong)uVar2),(ulong)*puVar25);
      *(ulong *)(param_1 + 0x120) = (ulong)*puVar25;
    }
    if (puVar24 != (uint *)0x0) {
      *(ulong *)(param_1 + 0x128) = *(long *)(param_1 + 8) + (ulong)puVar24[1];
      *(ulong *)(param_1 + 0x130) = (ulong)*puVar24;
    }
    if (puVar23 != (uint *)0x0) {
      *(ulong *)(param_1 + 0xf0) = (ulong)*puVar23;
      *(ulong *)(param_1 + 0xe8) = *(long *)(param_1 + 8) + (ulong)puVar23[1];
    }
    uVar7 = ByteNNInternalConfig::CheckFp16AndConvertModel(param_1);
  }
  return uVar7;
}



/* ------------------------------------------------------------------
 * Function: BYTENN::EspressoDisassembler::disassembleModelBuffer
 * Address : 00142af0
 * Size    : 0x928 bytes
 * ------------------------------------------------------------------ */

/* BYTENN::EspressoDisassembler::disassembleModelBuffer(BYTENN::ByteNNInternalConfigExt&) */

int BYTENN::EspressoDisassembler::disassembleModelBuffer(ByteNNInternalConfigExt *param_1)

{
  ByteNNInternalConfigExt *pBVar1;
  __tree<std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::shared_ptr<unsigned_char>>,std::__ndk1::__map_value_compare<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::shared_ptr<unsigned_char>>,std::__ndk1::less<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>>,true>,std::__ndk1::allocator<std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::shared_ptr<unsigned_char>>>>
  *this;
  char *pcVar2;
  uint uVar3;
  byte bVar4;
  char cVar5;
  bool bVar6;
  long lVar7;
  undefined *puVar8;
  __tree_end_node *p_Var9;
  void *pvVar10;
  int iVar11;
  size_t sVar12;
  char *pcVar13;
  undefined8 *puVar14;
  long *plVar15;
  __tree_node_base **pp_Var16;
  undefined8 *puVar17;
  __tree_node_base *p_Var18;
  ulong uVar19;
  ulong uVar20;
  byte *pbVar21;
  char *pcVar22;
  undefined8 *puVar23;
  long *plVar24;
  ulong *puVar25;
  long *plVar26;
  uchar *puVar27;
  void *pvVar28;
  long lVar29;
  __tree_node_base *p_Var30;
  ulong uVar31;
  long *plVar32;
  uint *puVar33;
  long lVar34;
  undefined8 uVar35;
  undefined8 uVar36;
  undefined8 uVar37;
  undefined8 uVar38;
  undefined8 uVar39;
  undefined8 uVar40;
  undefined8 uVar41;
  undefined8 uVar42;
  uint *local_b0;
  uint *local_a8;
  __tree_end_node *local_a0;
  undefined8 uStack_98;
  void *local_90;
  ulong local_88;
  size_t sStack_80;
  void *local_78;
  undefined8 local_70;
  long local_68;
  
  lVar7 = tpidr_el0;
  local_68 = *(long *)(lVar7 + 0x28);
  puVar27 = *(uchar **)(param_1 + 0x10);
  if (*(int *)(puVar27 + 4) == *(int *)(param_1 + 0x18)) {
    bVar4 = puVar27[3];
    if (param_1[0x1c] != (ByteNNInternalConfigExt)0x0) {
      iVar11 = CheckMd5sum(puVar27,(uint)bVar4);
      if (iVar11 != 0) goto LAB_00142bd8;
    }
    if ((*puVar27 == 'B') && (puVar27[1] == 'M')) {
      lVar34 = *(long *)(param_1 + 0x10);
      if (bVar4 == 1) {
        sStack_80 = 0;
        local_78 = (void *)0x0;
        local_88 = 0;
        pcVar13 = (char *)(lVar34 + (ulong)*(uint *)(lVar34 + 0x10));
        sVar12 = strlen(pcVar13);
        if (0xffffffffffffffef < sVar12) goto LAB_00143414;
        if (sVar12 < 0x17) {
          pvVar28 = (void *)((ulong)&local_88 | 1);
          local_88 = CONCAT71(local_88._1_7_,(char)((int)sVar12 << 1));
          if (sVar12 != 0) goto LAB_00142cd8;
        }
        else {
          uVar31 = sVar12 + 0x10 & 0xfffffffffffffff0;
          pvVar28 = operator_new(uVar31);
          local_88 = uVar31 | 1;
          sStack_80 = sVar12;
          local_78 = pvVar28;
LAB_00142cd8:
          memcpy(pvVar28,pcVar13,sVar12);
        }
        pBVar1 = param_1 + 0x198;
        *(undefined1 *)((long)pvVar28 + sVar12) = 0;
        uStack_98 = 0;
        local_90 = (void *)0x0;
        local_a0 = (__tree_end_node *)0x0;
        puVar14 = (undefined8 *)FUN_0013809c(pBVar1,&local_70,&local_a0);
        puVar23 = (undefined8 *)*puVar14;
        if (puVar23 == (undefined8 *)0x0) {
          puVar23 = operator_new(0x50);
          pvVar28 = local_90;
          uVar35 = uStack_98;
          p_Var9 = local_a0;
          uStack_98 = 0;
          local_90 = (void *)0x0;
          local_a0 = (__tree_end_node *)0x0;
          puVar23[8] = 0;
          puVar23[9] = 0;
          *puVar23 = 0;
          puVar23[1] = 0;
          puVar23[5] = uVar35;
          puVar23[4] = p_Var9;
          puVar23[6] = pvVar28;
          puVar23[7] = 0;
          puVar23[2] = local_70;
          *puVar14 = puVar23;
          puVar17 = puVar23;
          if (**(long **)pBVar1 != 0) {
            *(long *)pBVar1 = **(long **)pBVar1;
            puVar17 = (undefined8 *)*puVar14;
          }
          FUN_001352c8(*(undefined8 *)(param_1 + 0x1a0),puVar17);
          *(long *)(param_1 + 0x1a8) = *(long *)(param_1 + 0x1a8) + 1;
        }
        puVar25 = puVar23 + 7;
        if ((*puVar25 & 1) == 0) {
          *(undefined2 *)puVar25 = 0;
        }
        else {
          *(undefined1 *)puVar23[9] = 0;
          puVar23[8] = 0;
          if ((*(byte *)(puVar23 + 7) & 1) != 0) {
            operator_delete((void *)puVar23[9]);
            puVar23[7] = 0;
          }
        }
        puVar23[9] = local_78;
        puVar23[8] = sStack_80;
        *puVar25 = local_88;
        sStack_80 = 0;
        local_78 = (void *)0x0;
        local_88 = 0;
        if ((((ulong)local_a0 & 1) != 0) && (operator_delete(local_90), (local_88 & 1) != 0)) {
          operator_delete(local_78);
        }
        puVar33 = (uint *)0x0;
        local_b0 = (uint *)0x0;
        local_a8 = (uint *)0x0;
      }
      else {
        uVar3 = *(uint *)(puVar27 + 8);
        local_a8 = (uint *)(lVar34 + 0x24);
        local_b0 = (uint *)(lVar34 + 0x2c);
        puVar33 = (uint *)(lVar34 + 0x34);
        if (uVar3 < 4) {
          local_a8 = (uint *)0x0;
        }
        if (uVar3 < 5) {
          local_b0 = (uint *)0x0;
        }
        if (uVar3 < 6) {
          puVar33 = (uint *)0x0;
        }
        if ((bVar4 == 2) || ((2 < bVar4 && (*(int *)(lVar34 + 0x1c) != 0)))) {
          uVar3 = *(uint *)(lVar34 + 0x10);
          pcVar13 = malloc((ulong)*(uint *)(lVar34 + 0xc));
          memcpy(pcVar13,(void *)(lVar34 + (ulong)uVar3),(ulong)*(uint *)(lVar34 + 0xc));
          uVar3 = *(uint *)(lVar34 + 0xc);
          uVar31 = (ulong)uVar3;
          if (uVar3 != 0) {
            uVar19 = 0;
            pcVar2 = (char *)(lVar34 + (ulong)*(uint *)(lVar34 + 0x20));
            if (uVar3 < 0x20) goto LAB_00142f18;
            if (7 < uVar31 - 1) goto LAB_00142f18;
            if ((pcVar13 < (char *)(lVar34 + uVar31 + *(uint *)(lVar34 + 0x20))) &&
               (pcVar2 < pcVar13 + uVar31)) {
              uVar19 = 0;
              goto LAB_00142f18;
            }
            uVar36 = *(undefined8 *)(pcVar2 + 8);
            uVar35 = *(undefined8 *)pcVar2;
            uVar38 = *(undefined8 *)(pcVar2 + 0x18);
            uVar37 = *(undefined8 *)(pcVar2 + 0x10);
            uVar19 = uVar31 & 0xffffffe0;
            pcVar22 = pcVar13 + 0x10;
            uVar20 = uVar19;
            do {
              uVar40 = *(undefined8 *)(pcVar22 + -8);
              uVar39 = *(undefined8 *)(pcVar22 + -0x10);
              uVar42 = *(undefined8 *)(pcVar22 + 8);
              uVar41 = *(undefined8 *)pcVar22;
              uVar20 = uVar20 - 0x20;
              *(ulong *)(pcVar22 + -8) =
                   CONCAT17((byte)((ulong)uVar36 >> 0x38) ^ (byte)((ulong)uVar40 >> 0x38),
                            CONCAT16((byte)((ulong)uVar36 >> 0x30) ^ (byte)((ulong)uVar40 >> 0x30),
                                     CONCAT15((byte)((ulong)uVar36 >> 0x28) ^
                                              (byte)((ulong)uVar40 >> 0x28),
                                              CONCAT14((byte)((ulong)uVar36 >> 0x20) ^
                                                       (byte)((ulong)uVar40 >> 0x20),
                                                       CONCAT13((byte)((ulong)uVar36 >> 0x18) ^
                                                                (byte)((ulong)uVar40 >> 0x18),
                                                                CONCAT12((byte)((ulong)uVar36 >>
                                                                               0x10) ^
                                                                         (byte)((ulong)uVar40 >>
                                                                               0x10),
                                                                         CONCAT11((byte)((ulong)
                                                  uVar36 >> 8) ^ (byte)((ulong)uVar40 >> 8),
                                                  (byte)uVar36 ^ (byte)uVar40)))))));
              *(ulong *)(pcVar22 + -0x10) =
                   CONCAT17((byte)((ulong)uVar35 >> 0x38) ^ (byte)((ulong)uVar39 >> 0x38),
                            CONCAT16((byte)((ulong)uVar35 >> 0x30) ^ (byte)((ulong)uVar39 >> 0x30),
                                     CONCAT15((byte)((ulong)uVar35 >> 0x28) ^
                                              (byte)((ulong)uVar39 >> 0x28),
                                              CONCAT14((byte)((ulong)uVar35 >> 0x20) ^
                                                       (byte)((ulong)uVar39 >> 0x20),
                                                       CONCAT13((byte)((ulong)uVar35 >> 0x18) ^
                                                                (byte)((ulong)uVar39 >> 0x18),
                                                                CONCAT12((byte)((ulong)uVar35 >>
                                                                               0x10) ^
                                                                         (byte)((ulong)uVar39 >>
                                                                               0x10),
                                                                         CONCAT11((byte)((ulong)
                                                  uVar35 >> 8) ^ (byte)((ulong)uVar39 >> 8),
                                                  (byte)uVar35 ^ (byte)uVar39)))))));
              *(ulong *)(pcVar22 + 8) =
                   CONCAT17((byte)((ulong)uVar38 >> 0x38) ^ (byte)((ulong)uVar42 >> 0x38),
                            CONCAT16((byte)((ulong)uVar38 >> 0x30) ^ (byte)((ulong)uVar42 >> 0x30),
                                     CONCAT15((byte)((ulong)uVar38 >> 0x28) ^
                                              (byte)((ulong)uVar42 >> 0x28),
                                              CONCAT14((byte)((ulong)uVar38 >> 0x20) ^
                                                       (byte)((ulong)uVar42 >> 0x20),
                                                       CONCAT13((byte)((ulong)uVar38 >> 0x18) ^
                                                                (byte)((ulong)uVar42 >> 0x18),
                                                                CONCAT12((byte)((ulong)uVar38 >>
                                                                               0x10) ^
                                                                         (byte)((ulong)uVar42 >>
                                                                               0x10),
                                                                         CONCAT11((byte)((ulong)
                                                  uVar38 >> 8) ^ (byte)((ulong)uVar42 >> 8),
                                                  (byte)uVar38 ^ (byte)uVar42)))))));
              *(ulong *)pcVar22 =
                   CONCAT17((byte)((ulong)uVar37 >> 0x38) ^ (byte)((ulong)uVar41 >> 0x38),
                            CONCAT16((byte)((ulong)uVar37 >> 0x30) ^ (byte)((ulong)uVar41 >> 0x30),
                                     CONCAT15((byte)((ulong)uVar37 >> 0x28) ^
                                              (byte)((ulong)uVar41 >> 0x28),
                                              CONCAT14((byte)((ulong)uVar37 >> 0x20) ^
                                                       (byte)((ulong)uVar41 >> 0x20),
                                                       CONCAT13((byte)((ulong)uVar37 >> 0x18) ^
                                                                (byte)((ulong)uVar41 >> 0x18),
                                                                CONCAT12((byte)((ulong)uVar37 >>
                                                                               0x10) ^
                                                                         (byte)((ulong)uVar41 >>
                                                                               0x10),
                                                                         CONCAT11((byte)((ulong)
                                                  uVar37 >> 8) ^ (byte)((ulong)uVar41 >> 8),
                                                  (byte)uVar37 ^ (byte)uVar41)))))));
              pcVar22 = pcVar22 + 0x20;
            } while (uVar20 != 0);
            for (; uVar19 != uVar31; uVar19 = uVar19 + 1) {
LAB_00142f18:
              pcVar13[uVar19] = pcVar2[uVar19 & 7] ^ pcVar13[uVar19];
            }
            uVar3 = *(uint *)(lVar34 + 0xc);
            uVar31 = (ulong)uVar3;
            if (0 < (int)uVar3) {
              if (uVar3 == 1) {
                uVar20 = 0;
              }
              else {
                uVar20 = uVar31 & 0xfffffffe;
                pbVar21 = (byte *)(pcVar13 + 1);
                uVar19 = uVar20;
                do {
                  uVar19 = uVar19 - 2;
                  bVar4 = (&DAT_00284b32)[*pbVar21];
                  pbVar21[-1] = (&DAT_00284b32)[pbVar21[-1]];
                  *pbVar21 = bVar4;
                  pbVar21 = pbVar21 + 2;
                } while (uVar19 != 0);
                if (uVar20 == uVar31) goto LAB_00142fac;
              }
              lVar29 = uVar31 - uVar20;
              pbVar21 = (byte *)(pcVar13 + uVar20);
              do {
                lVar29 = lVar29 + -1;
                *pbVar21 = (&DAT_00284b32)[*pbVar21];
                pbVar21 = pbVar21 + 1;
              } while (lVar29 != 0);
            }
          }
LAB_00142fac:
          sStack_80 = 0;
          local_78 = (void *)0x0;
          local_88 = 0;
          sVar12 = strlen(pcVar13);
          if (0xffffffffffffffef < sVar12) {
LAB_00143414:
                    /* WARNING: Subroutine does not return */
            abort();
          }
          if (sVar12 < 0x17) {
            pvVar28 = (void *)((ulong)&local_88 | 1);
            local_88 = CONCAT71(local_88._1_7_,(char)((int)sVar12 << 1));
            if (sVar12 != 0) goto LAB_00143008;
          }
          else {
            uVar31 = sVar12 + 0x10 & 0xfffffffffffffff0;
            pvVar28 = operator_new(uVar31);
            local_88 = uVar31 | 1;
            sStack_80 = sVar12;
            local_78 = pvVar28;
LAB_00143008:
            memcpy(pvVar28,pcVar13,sVar12);
          }
          *(undefined1 *)((long)pvVar28 + sVar12) = 0;
          pBVar1 = param_1 + 0x198;
          uStack_98 = 0;
          local_90 = (void *)0x0;
          local_a0 = (__tree_end_node *)0x0;
          puVar14 = (undefined8 *)FUN_0013809c(pBVar1,&local_70,&local_a0);
          puVar23 = (undefined8 *)*puVar14;
          if (puVar23 == (undefined8 *)0x0) {
            puVar23 = operator_new(0x50);
            pvVar28 = local_90;
            uVar35 = uStack_98;
            p_Var9 = local_a0;
            uStack_98 = 0;
            local_90 = (void *)0x0;
            local_a0 = (__tree_end_node *)0x0;
            puVar23[8] = 0;
            puVar23[9] = 0;
            *puVar23 = 0;
            puVar23[1] = 0;
            puVar23[5] = uVar35;
            puVar23[4] = p_Var9;
            puVar23[6] = pvVar28;
            puVar23[7] = 0;
            puVar23[2] = local_70;
            *puVar14 = puVar23;
            puVar17 = puVar23;
            if (**(long **)pBVar1 != 0) {
              *(long *)pBVar1 = **(long **)pBVar1;
              puVar17 = (undefined8 *)*puVar14;
            }
            FUN_001352c8(*(undefined8 *)(param_1 + 0x1a0),puVar17);
            *(long *)(param_1 + 0x1a8) = *(long *)(param_1 + 0x1a8) + 1;
          }
          puVar25 = puVar23 + 7;
          if ((*puVar25 & 1) == 0) {
            *(undefined2 *)puVar25 = 0;
          }
          else {
            *(undefined1 *)puVar23[9] = 0;
            puVar23[8] = 0;
            if ((*(byte *)(puVar23 + 7) & 1) != 0) {
              operator_delete((void *)puVar23[9]);
              puVar23[7] = 0;
            }
          }
          puVar23[9] = local_78;
          puVar23[8] = sStack_80;
          *puVar25 = local_88;
          sStack_80 = 0;
          local_78 = (void *)0x0;
          local_88 = 0;
          if ((((ulong)local_a0 & 1) != 0) && (operator_delete(local_90), (local_88 & 1) != 0)) {
            operator_delete(local_78);
          }
          free(pcVar13);
        }
        else {
          sStack_80 = 0;
          local_78 = (void *)0x0;
          local_88 = 0;
          pcVar13 = (char *)(lVar34 + (ulong)*(uint *)(lVar34 + 0x10));
          sVar12 = strlen(pcVar13);
          if (0xffffffffffffffef < sVar12) goto LAB_00143414;
          if (sVar12 < 0x17) {
            pvVar28 = (void *)((ulong)&local_88 | 1);
            local_88 = CONCAT71(local_88._1_7_,(char)((int)sVar12 << 1));
            if (sVar12 != 0) goto LAB_00142dc8;
          }
          else {
            uVar31 = sVar12 + 0x10 & 0xfffffffffffffff0;
            pvVar28 = operator_new(uVar31);
            local_88 = uVar31 | 1;
            sStack_80 = sVar12;
            local_78 = pvVar28;
LAB_00142dc8:
            memcpy(pvVar28,pcVar13,sVar12);
          }
          pBVar1 = param_1 + 0x198;
          *(undefined1 *)((long)pvVar28 + sVar12) = 0;
          uStack_98 = 0;
          local_90 = (void *)0x0;
          local_a0 = (__tree_end_node *)0x0;
          puVar14 = (undefined8 *)FUN_0013809c(pBVar1,&local_70,&local_a0);
          puVar23 = (undefined8 *)*puVar14;
          if (puVar23 == (undefined8 *)0x0) {
            puVar23 = operator_new(0x50);
            pvVar28 = local_90;
            uVar35 = uStack_98;
            p_Var9 = local_a0;
            uStack_98 = 0;
            local_90 = (void *)0x0;
            local_a0 = (__tree_end_node *)0x0;
            puVar23[8] = 0;
            puVar23[9] = 0;
            *puVar23 = 0;
            puVar23[1] = 0;
            puVar23[5] = uVar35;
            puVar23[4] = p_Var9;
            puVar23[6] = pvVar28;
            puVar23[7] = 0;
            puVar23[2] = local_70;
            *puVar14 = puVar23;
            puVar17 = puVar23;
            if (**(long **)pBVar1 != 0) {
              *(long *)pBVar1 = **(long **)pBVar1;
              puVar17 = (undefined8 *)*puVar14;
            }
            FUN_001352c8(*(undefined8 *)(param_1 + 0x1a0),puVar17);
            *(long *)(param_1 + 0x1a8) = *(long *)(param_1 + 0x1a8) + 1;
          }
          puVar25 = puVar23 + 7;
          if ((*puVar25 & 1) == 0) {
            *(undefined2 *)puVar25 = 0;
          }
          else {
            *(undefined1 *)puVar23[9] = 0;
            puVar23[8] = 0;
            if ((*(byte *)(puVar23 + 7) & 1) != 0) {
              operator_delete((void *)puVar23[9]);
              puVar23[7] = 0;
            }
          }
          puVar23[9] = local_78;
          puVar23[8] = sStack_80;
          *puVar25 = local_88;
          sStack_80 = 0;
          local_78 = (void *)0x0;
          local_88 = 0;
          if ((((ulong)local_a0 & 1) != 0) && (operator_delete(local_90), (local_88 & 1) != 0)) {
            operator_delete(local_78);
          }
        }
      }
      lVar29 = *(long *)(param_1 + 0x10);
      uVar3 = *(uint *)(lVar34 + 0x18);
      pvVar28 = operator_new__((ulong)*(uint *)(lVar34 + 0x14),(nothrow_t *)PTR_nothrow_002deb70);
      plVar15 = operator_new(0x20);
      plVar15[2] = 0;
      puVar8 = PTR_vtable_002dea30;
      plVar24 = plVar15 + 1;
      *plVar24 = 0;
      *plVar15 = (long)(puVar8 + 0x10);
      plVar15[3] = (long)pvVar28;
      if (pvVar28 == (void *)0x0) {
        iVar11 = 1;
      }
      else {
        memcpy(pvVar28,(void *)(lVar29 + (ulong)uVar3),(ulong)*(uint *)(lVar34 + 0x14));
        this = (__tree<std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::shared_ptr<unsigned_char>>,std::__ndk1::__map_value_compare<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::shared_ptr<unsigned_char>>,std::__ndk1::less<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>>,true>,std::__ndk1::allocator<std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::shared_ptr<unsigned_char>>>>
                *)(param_1 + 0x150);
        sStack_80 = 0;
        local_78 = (void *)0x0;
        local_88 = 0;
        pp_Var16 = std::__ndk1::
                   __tree<std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::shared_ptr<unsigned_char>>,std::__ndk1::__map_value_compare<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::shared_ptr<unsigned_char>>,std::__ndk1::less<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>>,true>,std::__ndk1::allocator<std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::shared_ptr<unsigned_char>>>>
                   ::
                   __find_equal<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>>
                             (this,&local_a0,(basic_string *)&local_88);
        p_Var30 = *pp_Var16;
        if (p_Var30 == (__tree_node_base *)0x0) {
          p_Var30 = operator_new(0x48);
          pvVar10 = local_78;
          sVar12 = sStack_80;
          uVar31 = local_88;
          sStack_80 = 0;
          local_78 = (void *)0x0;
          local_88 = 0;
          *(undefined8 *)(p_Var30 + 0x38) = 0;
          *(undefined8 *)(p_Var30 + 0x40) = 0;
          *(undefined8 *)p_Var30 = 0;
          *(undefined8 *)(p_Var30 + 8) = 0;
          *(size_t *)(p_Var30 + 0x28) = sVar12;
          *(ulong *)(p_Var30 + 0x20) = uVar31;
          *(void **)(p_Var30 + 0x30) = pvVar10;
          *(__tree_end_node **)(p_Var30 + 0x10) = local_a0;
          *pp_Var16 = p_Var30;
          p_Var18 = p_Var30;
          if (**(long **)this != 0) {
            *(long *)this = **(long **)this;
            p_Var18 = *pp_Var16;
          }
          FUN_001352c8(*(undefined8 *)(param_1 + 0x158),p_Var18);
          *(long *)(param_1 + 0x160) = *(long *)(param_1 + 0x160) + 1;
        }
        do {
          cVar5 = '\x01';
          bVar6 = (bool)ExclusiveMonitorPass(plVar24,0x10);
          if (bVar6) {
            *plVar24 = *plVar24 + 1;
            cVar5 = ExclusiveMonitorsStatus();
          }
        } while (cVar5 != '\0');
        plVar32 = *(long **)(p_Var30 + 0x40);
        *(void **)(p_Var30 + 0x38) = pvVar28;
        *(long **)(p_Var30 + 0x40) = plVar15;
        if (plVar32 != (long *)0x0) {
          plVar26 = plVar32 + 1;
          do {
            lVar29 = *plVar26;
            cVar5 = '\x01';
            bVar6 = (bool)ExclusiveMonitorPass(plVar26,0x10);
            if (bVar6) {
              *plVar26 = lVar29 + -1;
              cVar5 = ExclusiveMonitorsStatus();
            }
          } while (cVar5 != '\0');
          if (lVar29 == 0) {
            (**(code **)(*plVar32 + 0x10))(plVar32);
            std::__ndk1::__shared_weak_count::__release_weak();
          }
        }
        if ((local_88 & 1) != 0) {
          operator_delete(local_78);
        }
        *(ulong *)(param_1 + 0x180) = (ulong)*(uint *)(lVar34 + 0x14);
        if (local_a8 != (uint *)0x0) {
          lVar34 = *(long *)(param_1 + 0x10);
          uVar3 = local_a8[1];
          pvVar28 = operator_new__((ulong)*local_a8,(nothrow_t *)PTR_nothrow_002deb70);
          plVar32 = operator_new(0x20);
          *plVar32 = (long)(puVar8 + 0x10);
          plVar32[1] = 0;
          plVar32[2] = 0;
          plVar32[3] = (long)pvVar28;
          plVar26 = *(long **)(param_1 + 0x1b8);
          *(void **)(param_1 + 0x1b0) = pvVar28;
          *(long **)(param_1 + 0x1b8) = plVar32;
          if (plVar26 != (long *)0x0) {
            plVar32 = plVar26 + 1;
            do {
              lVar29 = *plVar32;
              cVar5 = '\x01';
              bVar6 = (bool)ExclusiveMonitorPass(plVar32,0x10);
              if (bVar6) {
                *plVar32 = lVar29 + -1;
                cVar5 = ExclusiveMonitorsStatus();
              }
            } while (cVar5 != '\0');
            if (lVar29 == 0) {
              (**(code **)(*plVar26 + 0x10))(plVar26);
              std::__ndk1::__shared_weak_count::__release_weak();
            }
          }
          if (*(void **)(param_1 + 0x1b0) == (void *)0x0) {
            iVar11 = 1;
            goto LAB_001433e0;
          }
          memcpy(*(void **)(param_1 + 0x1b0),(void *)(lVar34 + (ulong)uVar3),(ulong)*local_a8);
          *(ulong *)(param_1 + 0x1c0) = (ulong)*local_a8;
        }
        if (local_b0 != (uint *)0x0) {
          *(ulong *)(param_1 + 0x1c8) = *(long *)(param_1 + 0x10) + (ulong)local_b0[1];
          *(ulong *)(param_1 + 0x1d0) = (ulong)*local_b0;
        }
        if (puVar33 != (uint *)0x0) {
          *(ulong *)(param_1 + 400) = (ulong)*puVar33;
          *(ulong *)(param_1 + 0x188) = *(long *)(param_1 + 0x10) + (ulong)puVar33[1];
        }
        ByteNNInternalConfigExt::CheckFp16AndConvertModel(param_1);
        iVar11 = 0;
      }
LAB_001433e0:
      do {
        lVar34 = *plVar24;
        cVar5 = '\x01';
        bVar6 = (bool)ExclusiveMonitorPass(plVar24,0x10);
        if (bVar6) {
          *plVar24 = lVar34 + -1;
          cVar5 = ExclusiveMonitorsStatus();
        }
      } while (cVar5 != '\0');
      if (lVar34 == 0) {
        (**(code **)(*plVar15 + 0x10))(plVar15);
        std::__ndk1::__shared_weak_count::__release_weak();
      }
      goto LAB_00142bd8;
    }
  }
  else {
    ByteNNLog(1,"ByteNN","Invalid model! assert(%d==%d) fails");
  }
  iVar11 = 10;
LAB_00142bd8:
  if (*(long *)(lVar7 + 0x28) == local_68) {
    return iVar11;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: BYTENN::EspressoDisassembler::disassembleModels
 * Address : 00143418
 * Size    : 0xcc8 bytes
 * ------------------------------------------------------------------ */

/* BYTENN::EspressoDisassembler::disassembleModels(BYTENN::ByteNNInternalConfigExt&) */

int BYTENN::EspressoDisassembler::disassembleModels(ByteNNInternalConfigExt *param_1)

{
  ByteNNInternalConfigExt *pBVar1;
  __tree<std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::shared_ptr<unsigned_char>>,std::__ndk1::__map_value_compare<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::shared_ptr<unsigned_char>>,std::__ndk1::less<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>>,true>,std::__ndk1::allocator<std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::shared_ptr<unsigned_char>>>>
  *this;
  undefined *puVar2;
  char *pcVar3;
  uint uVar4;
  byte bVar5;
  bool bVar6;
  char cVar7;
  long lVar8;
  undefined *puVar9;
  bool bVar10;
  size_t sVar11;
  char *__s;
  undefined8 *puVar12;
  void *pvVar13;
  void *pvVar14;
  long *plVar15;
  __tree_node_base **pp_Var16;
  ulong uVar17;
  __tree_node_base *p_Var18;
  undefined8 *puVar19;
  ByteNNInternalConfigExt *pBVar20;
  void *pvVar21;
  ByteNNInternalConfigExt *pBVar22;
  ByteNNInternalConfigExt *pBVar23;
  ulong uVar24;
  byte *pbVar25;
  long lVar26;
  char *pcVar27;
  ByteNNInternalConfigExt *pBVar28;
  char *pcVar29;
  ulong *puVar30;
  long *plVar31;
  long *plVar32;
  ulong uVar33;
  undefined8 *puVar34;
  long *plVar35;
  __tree_node_base *p_Var36;
  undefined8 uVar37;
  undefined8 uVar38;
  undefined8 uVar39;
  undefined8 uVar40;
  undefined8 uVar41;
  undefined8 uVar42;
  undefined8 uVar43;
  undefined8 uVar44;
  uint *local_f8;
  uint *local_f0;
  uint *local_d8;
  int local_cc;
  __tree_end_node *local_c8;
  size_t sStack_c0;
  void *local_b8;
  ulong local_b0;
  ulong uStack_a8;
  void *local_a0;
  __tree_node **local_90;
  __tree_node *local_88;
  undefined8 uStack_80;
  undefined8 local_78;
  long local_70;
  
  lVar8 = tpidr_el0;
  local_70 = *(long *)(lVar8 + 0x28);
  pBVar1 = param_1 + 0xd0;
  pBVar28 = *(ByteNNInternalConfigExt **)(param_1 + 200);
  if (pBVar1 != pBVar28) {
    pBVar20 = pBVar28;
    do {
      if (*(int *)(*(long *)(pBVar20 + 0x38) + 4) != *(int *)(param_1 + 0x18)) {
        ByteNNLog(1,"ByteNN","Invalid model! assert(%d==%d) fails");
        local_cc = 10;
        goto LAB_001440a4;
      }
      pBVar22 = *(ByteNNInternalConfigExt **)(pBVar20 + 8);
      if (*(ByteNNInternalConfigExt **)(pBVar20 + 8) == (ByteNNInternalConfigExt *)0x0) {
        pBVar22 = pBVar20 + 0x10;
        pBVar23 = *(ByteNNInternalConfigExt **)pBVar22;
        if (*(ByteNNInternalConfigExt **)pBVar23 != pBVar20) {
          do {
            lVar26 = *(long *)pBVar22;
            pBVar22 = (ByteNNInternalConfigExt *)(lVar26 + 0x10);
            pBVar23 = *(ByteNNInternalConfigExt **)pBVar22;
          } while (*(long *)pBVar23 != lVar26);
        }
      }
      else {
        do {
          pBVar23 = pBVar22;
          pBVar22 = *(ByteNNInternalConfigExt **)pBVar23;
        } while (*(ByteNNInternalConfigExt **)pBVar23 != (ByteNNInternalConfigExt *)0x0);
      }
      pBVar20 = pBVar23;
    } while (pBVar1 != pBVar23);
  }
  if (param_1[0x1c] != (ByteNNInternalConfigExt)0x0) {
    local_88 = (__tree_node *)0x0;
    uStack_80 = 0;
    local_90 = &local_88;
    while (pBVar1 != pBVar28) {
      std::__ndk1::
      __tree<std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,unsigned_char_const*>,std::__ndk1::__map_value_compare<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,unsigned_char_const*>,std::__ndk1::less<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>>,true>,std::__ndk1::allocator<std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,unsigned_char_const*>>>
      ::
      __emplace_hint_unique_key_args<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::pair<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>const,unsigned_char_const*>const&>
                ((__tree<std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,unsigned_char_const*>,std::__ndk1::__map_value_compare<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,unsigned_char_const*>,std::__ndk1::less<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>>,true>,std::__ndk1::allocator<std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,unsigned_char_const*>>>
                  *)&local_90,&local_88,pBVar28 + 0x20,pBVar28 + 0x20);
      pBVar20 = *(ByteNNInternalConfigExt **)(pBVar28 + 8);
      if (*(ByteNNInternalConfigExt **)(pBVar28 + 8) == (ByteNNInternalConfigExt *)0x0) {
        pBVar20 = pBVar28 + 0x10;
        bVar10 = *(ByteNNInternalConfigExt **)*(ByteNNInternalConfigExt **)pBVar20 != pBVar28;
        pBVar28 = *(ByteNNInternalConfigExt **)pBVar20;
        if (bVar10) {
          do {
            lVar26 = *(long *)pBVar20;
            pBVar20 = (ByteNNInternalConfigExt *)(lVar26 + 0x10);
            pBVar28 = *(ByteNNInternalConfigExt **)pBVar20;
          } while (*(long *)pBVar28 != lVar26);
        }
      }
      else {
        do {
          pBVar28 = pBVar20;
          pBVar20 = *(ByteNNInternalConfigExt **)pBVar28;
        } while (*(ByteNNInternalConfigExt **)pBVar28 != (ByteNNInternalConfigExt *)0x0);
      }
    }
    local_cc = CheckMd5sum(&local_90);
    std::__ndk1::
    __tree<std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,unsigned_char_const*>,std::__ndk1::__map_value_compare<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,unsigned_char_const*>,std::__ndk1::less<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>>,true>,std::__ndk1::allocator<std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,unsigned_char_const*>>>
    ::destroy((__tree<std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,unsigned_char_const*>,std::__ndk1::__map_value_compare<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,unsigned_char_const*>,std::__ndk1::less<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>>,true>,std::__ndk1::allocator<std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,unsigned_char_const*>>>
               *)&local_90,local_88);
    if (local_cc != 0) goto LAB_001440a4;
    pBVar28 = *(ByteNNInternalConfigExt **)(param_1 + 200);
  }
  if (pBVar1 != pBVar28) {
    pBVar20 = param_1 + 0x198;
    this = (__tree<std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::shared_ptr<unsigned_char>>,std::__ndk1::__map_value_compare<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::shared_ptr<unsigned_char>>,std::__ndk1::less<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>>,true>,std::__ndk1::allocator<std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::shared_ptr<unsigned_char>>>>
            *)(param_1 + 0x150);
    pvVar21 = (void *)((ulong)&local_c8 | 1);
    local_cc = 10;
    puVar2 = PTR_vtable_002dea30 + 0x10;
    do {
      pcVar29 = *(char **)(pBVar28 + 0x38);
      uStack_a8 = 0;
      local_a0 = (void *)0x0;
      local_b0 = 0;
      if (((byte)pBVar28[0x20] & 1) == 0) {
        local_a0 = *(void **)(pBVar28 + 0x30);
        uStack_a8 = *(ulong *)(pBVar28 + 0x28);
        local_b0 = *(ulong *)(pBVar28 + 0x20);
      }
      else {
        uVar33 = *(ulong *)(pBVar28 + 0x28);
        if (0xffffffffffffffef < uVar33) goto LAB_001440d8;
        pvVar14 = *(void **)(pBVar28 + 0x30);
        if (uVar33 < 0x17) {
          local_b0 = (ulong)(byte)((int)uVar33 << 1);
          pvVar13 = (void *)((ulong)&local_b0 | 1);
          if (uVar33 != 0) goto LAB_00143644;
        }
        else {
          uVar17 = uVar33 + 0x10 & 0xfffffffffffffff0;
          pvVar13 = operator_new(uVar17);
          local_b0 = uVar17 | 1;
          uStack_a8 = uVar33;
          local_a0 = pvVar13;
LAB_00143644:
          memcpy(pvVar13,pvVar14,uVar33);
        }
        *(undefined1 *)((long)pvVar13 + uVar33) = 0;
      }
      if ((*pcVar29 == 'B') && (pcVar29[1] == 'M')) {
        bVar5 = pcVar29[3];
        if (bVar5 == 1) {
          uVar4 = *(uint *)(pcVar29 + 0x10);
          sStack_c0 = 0;
          local_b8 = (void *)0x0;
          local_c8 = (__tree_end_node *)0x0;
          sVar11 = strlen(pcVar29 + uVar4);
          if (0xffffffffffffffef < sVar11) goto LAB_001440d8;
          if (sVar11 < 0x17) {
            local_c8 = (__tree_end_node *)CONCAT71(local_c8._1_7_,(char)((int)sVar11 << 1));
            pvVar14 = pvVar21;
            if (sVar11 != 0) goto LAB_001437fc;
          }
          else {
            uVar33 = sVar11 + 0x10 & 0xfffffffffffffff0;
            pvVar14 = operator_new(uVar33);
            local_c8 = (__tree_end_node *)(uVar33 | 1);
            sStack_c0 = sVar11;
            local_b8 = pvVar14;
LAB_001437fc:
            memcpy(pvVar14,pcVar29 + uVar4,sVar11);
          }
          *(undefined1 *)((long)pvVar14 + sVar11) = 0;
          puVar12 = (undefined8 *)FUN_0013809c(pBVar20,&local_78,&local_b0);
          puVar34 = (undefined8 *)*puVar12;
          if (puVar34 == (undefined8 *)0x0) {
            puVar34 = operator_new(0x50);
            pvVar14 = local_a0;
            uVar33 = uStack_a8;
            puVar34[4] = 0;
            puVar34[5] = 0;
            puVar34[6] = 0;
            if ((local_b0 & 1) == 0) {
              puVar34[6] = local_a0;
              puVar34[5] = uStack_a8;
              puVar34[4] = local_b0;
            }
            else {
              if (0xffffffffffffffef < uStack_a8) goto LAB_001440d8;
              if (uStack_a8 < 0x17) {
                pvVar13 = (void *)((long)puVar34 + 0x21);
                *(char *)(puVar34 + 4) = (char)((int)uStack_a8 << 1);
                if (uStack_a8 != 0) goto LAB_00143b20;
              }
              else {
                uVar17 = uStack_a8 + 0x10 & 0xfffffffffffffff0;
                pvVar13 = operator_new(uVar17);
                puVar34[5] = uVar33;
                puVar34[6] = pvVar13;
                puVar34[4] = uVar17 | 1;
LAB_00143b20:
                memcpy(pvVar13,pvVar14,uVar33);
              }
              *(undefined1 *)((long)pvVar13 + uVar33) = 0;
            }
            puVar34[7] = 0;
            puVar34[8] = 0;
            puVar34[9] = 0;
            *puVar34 = 0;
            puVar34[1] = 0;
            puVar34[2] = local_78;
            *puVar12 = puVar34;
            puVar19 = puVar34;
            if (**(long **)pBVar20 != 0) {
              *(long *)pBVar20 = **(long **)pBVar20;
              puVar19 = (undefined8 *)*puVar12;
            }
            FUN_001352c8(*(undefined8 *)(param_1 + 0x1a0),puVar19);
            *(long *)(param_1 + 0x1a8) = *(long *)(param_1 + 0x1a8) + 1;
          }
          puVar30 = puVar34 + 7;
          if ((*puVar30 & 1) == 0) {
            *(undefined2 *)puVar30 = 0;
          }
          else {
            *(undefined1 *)puVar34[9] = 0;
            puVar34[8] = 0;
            if ((*(byte *)(puVar34 + 7) & 1) != 0) {
              operator_delete((void *)puVar34[9]);
              puVar34[7] = 0;
            }
          }
          local_f8 = (uint *)0x0;
          local_f0 = (uint *)0x0;
          local_d8 = (uint *)0x0;
LAB_00143bbc:
          puVar30[2] = (ulong)local_b8;
          puVar30[1] = sStack_c0;
          *puVar30 = (ulong)local_c8;
        }
        else {
          uVar4 = *(uint *)(pcVar29 + 8);
          local_d8 = (uint *)(pcVar29 + 0x24);
          local_f8 = (uint *)(pcVar29 + 0x2c);
          local_f0 = (uint *)(pcVar29 + 0x34);
          if (uVar4 < 4) {
            local_d8 = (uint *)0x0;
          }
          if (uVar4 < 5) {
            local_f8 = (uint *)0x0;
          }
          if (uVar4 < 6) {
            local_f0 = (uint *)0x0;
          }
          if ((bVar5 != 2) && ((bVar5 < 3 || (*(int *)(pcVar29 + 0x1c) == 0)))) {
            uVar4 = *(uint *)(pcVar29 + 0x10);
            sStack_c0 = 0;
            local_b8 = (void *)0x0;
            local_c8 = (__tree_end_node *)0x0;
            sVar11 = strlen(pcVar29 + uVar4);
            if (0xffffffffffffffef < sVar11) goto LAB_001440d8;
            if (sVar11 < 0x17) {
              local_c8 = (__tree_end_node *)CONCAT71(local_c8._1_7_,(char)((int)sVar11 << 1));
              pvVar14 = pvVar21;
              if (sVar11 != 0) goto LAB_001438ec;
            }
            else {
              uVar33 = sVar11 + 0x10 & 0xfffffffffffffff0;
              pvVar14 = operator_new(uVar33);
              local_c8 = (__tree_end_node *)(uVar33 | 1);
              sStack_c0 = sVar11;
              local_b8 = pvVar14;
LAB_001438ec:
              memcpy(pvVar14,pcVar29 + uVar4,sVar11);
            }
            *(undefined1 *)((long)pvVar14 + sVar11) = 0;
            puVar12 = (undefined8 *)FUN_0013809c(pBVar20,&local_78,&local_b0);
            puVar34 = (undefined8 *)*puVar12;
            if (puVar34 == (undefined8 *)0x0) {
              puVar34 = operator_new(0x50);
              pvVar14 = local_a0;
              uVar33 = uStack_a8;
              puVar34[4] = 0;
              puVar34[5] = 0;
              puVar34[6] = 0;
              if ((local_b0 & 1) == 0) {
                puVar34[6] = local_a0;
                puVar34[5] = uStack_a8;
                puVar34[4] = local_b0;
              }
              else {
                if (0xffffffffffffffef < uStack_a8) goto LAB_001440d8;
                if (uStack_a8 < 0x17) {
                  pvVar13 = (void *)((long)puVar34 + 0x21);
                  *(char *)(puVar34 + 4) = (char)((int)uStack_a8 << 1);
                  if (uStack_a8 != 0) goto LAB_00143fe4;
                }
                else {
                  uVar17 = uStack_a8 + 0x10 & 0xfffffffffffffff0;
                  pvVar13 = operator_new(uVar17);
                  puVar34[5] = uVar33;
                  puVar34[6] = pvVar13;
                  puVar34[4] = uVar17 | 1;
LAB_00143fe4:
                  memcpy(pvVar13,pvVar14,uVar33);
                }
                *(undefined1 *)((long)pvVar13 + uVar33) = 0;
              }
              puVar34[7] = 0;
              puVar34[8] = 0;
              puVar34[9] = 0;
              *puVar34 = 0;
              puVar34[1] = 0;
              puVar34[2] = local_78;
              *puVar12 = puVar34;
              puVar19 = puVar34;
              if (**(long **)pBVar20 != 0) {
                *(long *)pBVar20 = **(long **)pBVar20;
                puVar19 = (undefined8 *)*puVar12;
              }
              FUN_001352c8(*(undefined8 *)(param_1 + 0x1a0),puVar19);
              *(long *)(param_1 + 0x1a8) = *(long *)(param_1 + 0x1a8) + 1;
            }
            puVar30 = puVar34 + 7;
            if ((*puVar30 & 1) == 0) {
              *(undefined2 *)puVar30 = 0;
            }
            else {
              *(undefined1 *)puVar34[9] = 0;
              puVar34[8] = 0;
              if ((*(byte *)(puVar34 + 7) & 1) != 0) {
                operator_delete((void *)puVar34[9]);
                puVar34[7] = 0;
              }
            }
            goto LAB_00143bbc;
          }
          uVar4 = *(uint *)(pcVar29 + 0x10);
          __s = malloc((ulong)*(uint *)(pcVar29 + 0xc));
          memcpy(__s,pcVar29 + uVar4,(ulong)*(uint *)(pcVar29 + 0xc));
          uVar4 = *(uint *)(pcVar29 + 0xc);
          uVar33 = (ulong)uVar4;
          if (uVar4 != 0) {
            uVar17 = 0;
            pcVar3 = pcVar29 + *(uint *)(pcVar29 + 0x20);
            if (uVar4 < 0x20) goto LAB_00143984;
            if (7 < uVar33 - 1) goto LAB_00143984;
            if ((__s < pcVar29 + uVar33 + *(uint *)(pcVar29 + 0x20)) && (pcVar3 < __s + uVar33)) {
              uVar17 = 0;
              goto LAB_00143984;
            }
            uVar38 = *(undefined8 *)(pcVar3 + 8);
            uVar37 = *(undefined8 *)pcVar3;
            uVar40 = *(undefined8 *)(pcVar3 + 0x18);
            uVar39 = *(undefined8 *)(pcVar3 + 0x10);
            uVar17 = uVar33 & 0xffffffe0;
            pcVar27 = __s + 0x10;
            uVar24 = uVar17;
            do {
              uVar42 = *(undefined8 *)(pcVar27 + -8);
              uVar41 = *(undefined8 *)(pcVar27 + -0x10);
              uVar44 = *(undefined8 *)(pcVar27 + 8);
              uVar43 = *(undefined8 *)pcVar27;
              uVar24 = uVar24 - 0x20;
              *(ulong *)(pcVar27 + -8) =
                   CONCAT17((byte)((ulong)uVar38 >> 0x38) ^ (byte)((ulong)uVar42 >> 0x38),
                            CONCAT16((byte)((ulong)uVar38 >> 0x30) ^ (byte)((ulong)uVar42 >> 0x30),
                                     CONCAT15((byte)((ulong)uVar38 >> 0x28) ^
                                              (byte)((ulong)uVar42 >> 0x28),
                                              CONCAT14((byte)((ulong)uVar38 >> 0x20) ^
                                                       (byte)((ulong)uVar42 >> 0x20),
                                                       CONCAT13((byte)((ulong)uVar38 >> 0x18) ^
                                                                (byte)((ulong)uVar42 >> 0x18),
                                                                CONCAT12((byte)((ulong)uVar38 >>
                                                                               0x10) ^
                                                                         (byte)((ulong)uVar42 >>
                                                                               0x10),
                                                                         CONCAT11((byte)((ulong)
                                                  uVar38 >> 8) ^ (byte)((ulong)uVar42 >> 8),
                                                  (byte)uVar38 ^ (byte)uVar42)))))));
              *(ulong *)(pcVar27 + -0x10) =
                   CONCAT17((byte)((ulong)uVar37 >> 0x38) ^ (byte)((ulong)uVar41 >> 0x38),
                            CONCAT16((byte)((ulong)uVar37 >> 0x30) ^ (byte)((ulong)uVar41 >> 0x30),
                                     CONCAT15((byte)((ulong)uVar37 >> 0x28) ^
                                              (byte)((ulong)uVar41 >> 0x28),
                                              CONCAT14((byte)((ulong)uVar37 >> 0x20) ^
                                                       (byte)((ulong)uVar41 >> 0x20),
                                                       CONCAT13((byte)((ulong)uVar37 >> 0x18) ^
                                                                (byte)((ulong)uVar41 >> 0x18),
                                                                CONCAT12((byte)((ulong)uVar37 >>
                                                                               0x10) ^
                                                                         (byte)((ulong)uVar41 >>
                                                                               0x10),
                                                                         CONCAT11((byte)((ulong)
                                                  uVar37 >> 8) ^ (byte)((ulong)uVar41 >> 8),
                                                  (byte)uVar37 ^ (byte)uVar41)))))));
              *(ulong *)(pcVar27 + 8) =
                   CONCAT17((byte)((ulong)uVar40 >> 0x38) ^ (byte)((ulong)uVar44 >> 0x38),
                            CONCAT16((byte)((ulong)uVar40 >> 0x30) ^ (byte)((ulong)uVar44 >> 0x30),
                                     CONCAT15((byte)((ulong)uVar40 >> 0x28) ^
                                              (byte)((ulong)uVar44 >> 0x28),
                                              CONCAT14((byte)((ulong)uVar40 >> 0x20) ^
                                                       (byte)((ulong)uVar44 >> 0x20),
                                                       CONCAT13((byte)((ulong)uVar40 >> 0x18) ^
                                                                (byte)((ulong)uVar44 >> 0x18),
                                                                CONCAT12((byte)((ulong)uVar40 >>
                                                                               0x10) ^
                                                                         (byte)((ulong)uVar44 >>
                                                                               0x10),
                                                                         CONCAT11((byte)((ulong)
                                                  uVar40 >> 8) ^ (byte)((ulong)uVar44 >> 8),
                                                  (byte)uVar40 ^ (byte)uVar44)))))));
              *(ulong *)pcVar27 =
                   CONCAT17((byte)((ulong)uVar39 >> 0x38) ^ (byte)((ulong)uVar43 >> 0x38),
                            CONCAT16((byte)((ulong)uVar39 >> 0x30) ^ (byte)((ulong)uVar43 >> 0x30),
                                     CONCAT15((byte)((ulong)uVar39 >> 0x28) ^
                                              (byte)((ulong)uVar43 >> 0x28),
                                              CONCAT14((byte)((ulong)uVar39 >> 0x20) ^
                                                       (byte)((ulong)uVar43 >> 0x20),
                                                       CONCAT13((byte)((ulong)uVar39 >> 0x18) ^
                                                                (byte)((ulong)uVar43 >> 0x18),
                                                                CONCAT12((byte)((ulong)uVar39 >>
                                                                               0x10) ^
                                                                         (byte)((ulong)uVar43 >>
                                                                               0x10),
                                                                         CONCAT11((byte)((ulong)
                                                  uVar39 >> 8) ^ (byte)((ulong)uVar43 >> 8),
                                                  (byte)uVar39 ^ (byte)uVar43)))))));
              pcVar27 = pcVar27 + 0x20;
            } while (uVar24 != 0);
            for (; uVar17 != uVar33; uVar17 = uVar17 + 1) {
LAB_00143984:
              __s[uVar17] = pcVar3[uVar17 & 7] ^ __s[uVar17];
            }
            uVar4 = *(uint *)(pcVar29 + 0xc);
            uVar33 = (ulong)uVar4;
            if (0 < (int)uVar4) {
              if (uVar4 == 1) {
                uVar24 = 0;
              }
              else {
                uVar24 = uVar33 & 0xfffffffe;
                pbVar25 = (byte *)(__s + 1);
                uVar17 = uVar24;
                do {
                  uVar17 = uVar17 - 2;
                  bVar5 = (&DAT_00284b32)[*pbVar25];
                  pbVar25[-1] = (&DAT_00284b32)[pbVar25[-1]];
                  *pbVar25 = bVar5;
                  pbVar25 = pbVar25 + 2;
                } while (uVar17 != 0);
                if (uVar24 == uVar33) goto LAB_00143a10;
              }
              lVar26 = uVar33 - uVar24;
              pbVar25 = (byte *)(__s + uVar24);
              do {
                lVar26 = lVar26 + -1;
                *pbVar25 = (&DAT_00284b32)[*pbVar25];
                pbVar25 = pbVar25 + 1;
              } while (lVar26 != 0);
            }
          }
LAB_00143a10:
          sStack_c0 = 0;
          local_b8 = (void *)0x0;
          local_c8 = (__tree_end_node *)0x0;
          sVar11 = strlen(__s);
          if (0xffffffffffffffef < sVar11) goto LAB_001440d8;
          if (sVar11 < 0x17) {
            local_c8 = (__tree_end_node *)CONCAT71(local_c8._1_7_,(char)((int)sVar11 << 1));
            pvVar14 = pvVar21;
            if (sVar11 != 0) goto LAB_00143a68;
          }
          else {
            uVar33 = sVar11 + 0x10 & 0xfffffffffffffff0;
            pvVar14 = operator_new(uVar33);
            local_c8 = (__tree_end_node *)(uVar33 | 1);
            sStack_c0 = sVar11;
            local_b8 = pvVar14;
LAB_00143a68:
            memcpy(pvVar14,__s,sVar11);
          }
          *(undefined1 *)((long)pvVar14 + sVar11) = 0;
          puVar12 = (undefined8 *)FUN_0013809c(pBVar20,&local_78,&local_b0);
          puVar34 = (undefined8 *)*puVar12;
          if (puVar34 == (undefined8 *)0x0) {
            puVar34 = operator_new(0x50);
            pvVar14 = local_a0;
            uVar33 = uStack_a8;
            puVar34[4] = 0;
            puVar34[5] = 0;
            puVar34[6] = 0;
            if ((local_b0 & 1) == 0) {
              puVar34[6] = local_a0;
              puVar34[5] = uStack_a8;
              puVar34[4] = local_b0;
            }
            else {
              if (0xffffffffffffffef < uStack_a8) goto LAB_001440d8;
              if (uStack_a8 < 0x17) {
                pvVar13 = (void *)((long)puVar34 + 0x21);
                *(char *)(puVar34 + 4) = (char)((int)uStack_a8 << 1);
                if (uStack_a8 != 0) goto LAB_00143c2c;
              }
              else {
                uVar17 = uStack_a8 + 0x10 & 0xfffffffffffffff0;
                pvVar13 = operator_new(uVar17);
                puVar34[5] = uVar33;
                puVar34[6] = pvVar13;
                puVar34[4] = uVar17 | 1;
LAB_00143c2c:
                memcpy(pvVar13,pvVar14,uVar33);
              }
              *(undefined1 *)((long)pvVar13 + uVar33) = 0;
            }
            puVar34[7] = 0;
            puVar34[8] = 0;
            puVar34[9] = 0;
            *puVar34 = 0;
            puVar34[1] = 0;
            puVar34[2] = local_78;
            *puVar12 = puVar34;
            puVar19 = puVar34;
            if (**(long **)pBVar20 != 0) {
              *(long *)pBVar20 = **(long **)pBVar20;
              puVar19 = (undefined8 *)*puVar12;
            }
            FUN_001352c8(*(undefined8 *)(param_1 + 0x1a0),puVar19);
            *(long *)(param_1 + 0x1a8) = *(long *)(param_1 + 0x1a8) + 1;
          }
          puVar30 = puVar34 + 7;
          if ((*puVar30 & 1) == 0) {
            *(undefined2 *)puVar30 = 0;
          }
          else {
            *(undefined1 *)puVar34[9] = 0;
            puVar34[8] = 0;
            if ((*(byte *)(puVar34 + 7) & 1) != 0) {
              operator_delete((void *)puVar34[9]);
              puVar34[7] = 0;
            }
          }
          puVar34[9] = local_b8;
          puVar34[8] = sStack_c0;
          *puVar30 = (ulong)local_c8;
          free(__s);
        }
        uVar4 = *(uint *)(pcVar29 + 0x18);
        pvVar14 = operator_new__((ulong)*(uint *)(pcVar29 + 0x14),(nothrow_t *)PTR_nothrow_002deb70)
        ;
        plVar15 = operator_new(0x20);
        plVar15[2] = 0;
        plVar15[3] = (long)pvVar14;
        plVar35 = plVar15 + 1;
        *plVar35 = 0;
        *plVar15 = (long)puVar2;
        if (pvVar14 == (void *)0x0) {
          bVar10 = true;
          local_cc = 1;
        }
        else {
          memcpy(pvVar14,pcVar29 + uVar4,(ulong)*(uint *)(pcVar29 + 0x14));
          pp_Var16 = std::__ndk1::
                     __tree<std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::shared_ptr<unsigned_char>>,std::__ndk1::__map_value_compare<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::shared_ptr<unsigned_char>>,std::__ndk1::less<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>>,true>,std::__ndk1::allocator<std::__ndk1::__value_type<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>,std::__ndk1::shared_ptr<unsigned_char>>>>
                     ::
                     __find_equal<std::__ndk1::basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>>
                               (this,&local_c8,(basic_string *)&local_b0);
          p_Var36 = *pp_Var16;
          if (p_Var36 == (__tree_node_base *)0x0) {
            p_Var36 = operator_new(0x48);
            pvVar13 = local_a0;
            uVar33 = uStack_a8;
            *(undefined8 *)(p_Var36 + 0x20) = 0;
            *(undefined8 *)(p_Var36 + 0x28) = 0;
            *(undefined8 *)(p_Var36 + 0x30) = 0;
            if ((local_b0 & 1) == 0) {
              *(void **)(p_Var36 + 0x30) = local_a0;
              *(ulong *)(p_Var36 + 0x28) = uStack_a8;
              *(ulong *)(p_Var36 + 0x20) = local_b0;
            }
            else {
              if (0xffffffffffffffef < uStack_a8) {
LAB_001440d8:
                    /* WARNING: Subroutine does not return */
                abort();
              }
              if (uStack_a8 < 0x17) {
                p_Var18 = p_Var36 + 0x21;
                p_Var36[0x20] = SUB41((int)uStack_a8 << 1,0);
                if (uStack_a8 != 0) goto LAB_00143de0;
              }
              else {
                uVar17 = uStack_a8 + 0x10 & 0xfffffffffffffff0;
                p_Var18 = operator_new(uVar17);
                *(ulong *)(p_Var36 + 0x28) = uVar33;
                *(__tree_node_base **)(p_Var36 + 0x30) = p_Var18;
                *(ulong *)(p_Var36 + 0x20) = uVar17 | 1;
LAB_00143de0:
                memcpy(p_Var18,pvVar13,uVar33);
              }
              p_Var18[uVar33] = (__tree_node_base)0x0;
            }
            *(undefined8 *)(p_Var36 + 0x38) = 0;
            *(undefined8 *)(p_Var36 + 0x40) = 0;
            *(undefined8 *)p_Var36 = 0;
            *(undefined8 *)(p_Var36 + 8) = 0;
            *(__tree_end_node **)(p_Var36 + 0x10) = local_c8;
            *pp_Var16 = p_Var36;
            p_Var18 = p_Var36;
            if (**(long **)this != 0) {
              *(long *)this = **(long **)this;
              p_Var18 = *pp_Var16;
            }
            FUN_001352c8(*(undefined8 *)(param_1 + 0x158),p_Var18);
            *(long *)(param_1 + 0x160) = *(long *)(param_1 + 0x160) + 1;
          }
          do {
            cVar7 = '\x01';
            bVar10 = (bool)ExclusiveMonitorPass(plVar35,0x10);
            if (bVar10) {
              *plVar35 = *plVar35 + 1;
              cVar7 = ExclusiveMonitorsStatus();
            }
          } while (cVar7 != '\0');
          plVar31 = *(long **)(p_Var36 + 0x40);
          *(void **)(p_Var36 + 0x38) = pvVar14;
          *(long **)(p_Var36 + 0x40) = plVar15;
          if (plVar31 != (long *)0x0) {
            plVar32 = plVar31 + 1;
            do {
              lVar26 = *plVar32;
              cVar7 = '\x01';
              bVar10 = (bool)ExclusiveMonitorPass(plVar32,0x10);
              if (bVar10) {
                *plVar32 = lVar26 + -1;
                cVar7 = ExclusiveMonitorsStatus();
              }
            } while (cVar7 != '\0');
            if (lVar26 == 0) {
              (**(code **)(*plVar31 + 0x10))(plVar31);
              std::__ndk1::__shared_weak_count::__release_weak();
            }
          }
          *(ulong *)(param_1 + 0x180) = (ulong)*(uint *)(pcVar29 + 0x14);
          if (local_d8 != (uint *)0x0) {
            uVar4 = local_d8[1];
            pvVar14 = operator_new__((ulong)*local_d8,(nothrow_t *)PTR_nothrow_002deb70);
            plVar31 = operator_new(0x20);
            puVar9 = PTR_vtable_002dea30;
            plVar31[2] = 0;
            plVar31[3] = (long)pvVar14;
            *plVar31 = (long)(puVar9 + 0x10);
            plVar31[1] = 0;
            plVar32 = *(long **)(param_1 + 0x1b8);
            *(void **)(param_1 + 0x1b0) = pvVar14;
            *(long **)(param_1 + 0x1b8) = plVar31;
            if (plVar32 != (long *)0x0) {
              plVar31 = plVar32 + 1;
              do {
                lVar26 = *plVar31;
                cVar7 = '\x01';
                bVar10 = (bool)ExclusiveMonitorPass(plVar31,0x10);
                if (bVar10) {
                  *plVar31 = lVar26 + -1;
                  cVar7 = ExclusiveMonitorsStatus();
                }
              } while (cVar7 != '\0');
              if (lVar26 == 0) {
                (**(code **)(*plVar32 + 0x10))(plVar32);
                std::__ndk1::__shared_weak_count::__release_weak();
              }
              pvVar14 = *(void **)(param_1 + 0x1b0);
            }
            if (pvVar14 == (void *)0x0) {
              bVar10 = true;
              local_cc = 1;
              goto LAB_00143f80;
            }
            memcpy(pvVar14,pcVar29 + uVar4,(ulong)*local_d8);
            *(ulong *)(param_1 + 0x1c0) = (ulong)*local_d8;
          }
          if (local_f8 != (uint *)0x0) {
            *(char **)(param_1 + 0x1c8) = pcVar29 + local_f8[1];
            *(ulong *)(param_1 + 0x1d0) = (ulong)*local_f8;
          }
          if (local_f0 != (uint *)0x0) {
            *(ulong *)(param_1 + 400) = (ulong)*local_f0;
            *(char **)(param_1 + 0x188) = pcVar29 + local_f0[1];
          }
          ByteNNInternalConfigExt::CheckFp16AndConvertModel(param_1);
          bVar10 = false;
        }
LAB_00143f80:
        do {
          lVar26 = *plVar35;
          cVar7 = '\x01';
          bVar6 = (bool)ExclusiveMonitorPass(plVar35,0x10);
          if (bVar6) {
            *plVar35 = lVar26 + -1;
            cVar7 = ExclusiveMonitorsStatus();
          }
        } while (cVar7 != '\0');
        if (lVar26 == 0) {
          (**(code **)(*plVar15 + 0x10))(plVar15);
          std::__ndk1::__shared_weak_count::__release_weak();
        }
      }
      else {
        local_cc = 10;
        bVar10 = true;
      }
      if ((local_b0 & 1) != 0) {
        operator_delete(local_a0);
      }
      if (bVar10) goto LAB_001440a4;
      pBVar22 = *(ByteNNInternalConfigExt **)(pBVar28 + 8);
      if (*(ByteNNInternalConfigExt **)(pBVar28 + 8) == (ByteNNInternalConfigExt *)0x0) {
        pBVar22 = pBVar28 + 0x10;
        pBVar23 = *(ByteNNInternalConfigExt **)pBVar22;
        if (*(ByteNNInternalConfigExt **)pBVar23 != pBVar28) {
          do {
            lVar26 = *(long *)pBVar22;
            pBVar22 = (ByteNNInternalConfigExt *)(lVar26 + 0x10);
            pBVar23 = *(ByteNNInternalConfigExt **)pBVar22;
          } while (*(long *)pBVar23 != lVar26);
        }
      }
      else {
        do {
          pBVar23 = pBVar22;
          pBVar22 = *(ByteNNInternalConfigExt **)pBVar23;
        } while (*(ByteNNInternalConfigExt **)pBVar23 != (ByteNNInternalConfigExt *)0x0);
      }
      pBVar28 = pBVar23;
    } while (pBVar1 != pBVar23);
  }
  local_cc = 0;
LAB_001440a4:
  if (*(long *)(lVar8 + 0x28) == local_70) {
    return local_cc;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: BYTENN::EspressoDisassembler::disassemble
 * Address : 001440e0
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

/* BYTENN::EspressoDisassembler::disassemble(BYTENN::ByteNNInternalConfigExt&) */

void BYTENN::EspressoDisassembler::disassemble(ByteNNInternalConfigExt *param_1)

{
  if (*(long *)(param_1 + 0x10) != 0) {
    disassembleModelBuffer(param_1);
    return;
  }
  disassembleModels(param_1);
  return;
}



