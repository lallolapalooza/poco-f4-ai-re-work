/* Ghidra DecompileAt: 6 function(s) */

/* ===== Function: parseNet @ 00240788  size=12376  depth=0
   signature: undefined __cdecl parseNet(basic_string * param_1, uchar * param_2, ulong param_3, IESNet * param_4) ===== */

/* WARNING (jumptable): Heritage AFTER dead removal. Revisit: 0xfffffffffffdeda0 */
/* WARNING: Type propagation algorithm not settling */
/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */
/* WARNING: Restarted to delay deadcode elimination for space: stack */
/* IESNN::parseNet(std::__ndk1::basic_string<char, std::__ndk1::char_traits<char>,
   std::__ndk1::allocator<char> > const&, unsigned char const*, unsigned long, IESNN::IESNet*) */

int IESNN::parseNet(basic_string *param_1,uchar *param_2,ulong param_3,IESNet *param_4)

{
  IESNet *pIVar1;
  long lVar2;
  undefined8 uVar3;
  undefined8 uVar4;
  ulong *puVar5;
  bool bVar6;
  int iVar7;
  int iVar8;
  uint uVar9;
  float *pfVar10;
  void *pvVar11;
  float *__s;
  undefined8 *puVar12;
  int **ppiVar13;
  char *pcVar14;
  size_t sVar15;
  int iVar16;
  undefined8 *puVar17;
  byte *pbVar18;
  ulong *puVar19;
  ulong uVar20;
  long lVar21;
  undefined4 *puVar22;
  byte *pbVar23;
  int iVar24;
  int iVar25;
  int iVar26;
  long lVar27;
  int iVar28;
  IESNet *pIVar29;
  ulong uVar30;
  vector<IESNN::IESLayerTensor,std::__ndk1::allocator<IESNN::IESLayerTensor>> *this;
  long lVar31;
  void *pvVar32;
  float fVar33;
  ulong uVar34;
  IESNet *pIVar35;
  ulong *puVar36;
  ulong uVar37;
  int iStack_31454;
  undefined8 *puStack_31408;
  IESNet *pIStack_31400;
  char *pcStack_313f8;
  undefined8 *puStack_313f0;
  IESNet *pIStack_313e8;
  char *pcStack_313e0;
  float *apfStack_313d8 [2];
  char *pcStack_313c8;
  ulong *puStack_313c0;
  ulong *puStack_313b8;
  ulong *puStack_313b0;
  byte abStack_313a8 [16];
  char *pcStack_31398;
  byte abStack_31390 [16];
  byte *pbStack_31380;
  undefined1 auStack_31378 [4112];
  undefined1 auStack_30368 [4112];
  undefined1 auStack_2f358 [4112];
  undefined1 auStack_2e348 [4112];
  undefined1 auStack_2d338 [4112];
  undefined1 auStack_2c328 [4112];
  undefined1 auStack_2b318 [4112];
  undefined1 auStack_2a308 [4112];
  undefined1 auStack_292f8 [4112];
  undefined1 auStack_282e8 [4112];
  undefined1 auStack_272d8 [4112];
  undefined1 auStack_262c8 [4112];
  undefined1 auStack_252b8 [4112];
  undefined1 auStack_242a8 [4112];
  undefined1 auStack_23298 [4112];
  undefined1 auStack_22288 [4112];
  undefined8 uStack_21278;
  int iStack_21270;
  int iStack_2126c;
  ulong *puStack_21268;
  undefined8 uStack_21260;
  IESNet *pIStack_21258;
  void *pvStack_21250;
  undefined1 auStack_20268 [4112];
  undefined1 auStack_1f258 [4112];
  undefined1 auStack_1e248 [4112];
  undefined1 auStack_1d238 [4112];
  undefined1 auStack_1c228 [4112];
  undefined1 auStack_1b218 [4112];
  undefined1 auStack_1a208 [4112];
  undefined1 auStack_191f8 [4112];
  undefined1 auStack_181e8 [4112];
  undefined1 auStack_171d8 [4112];
  undefined1 auStack_161c8 [4112];
  undefined1 auStack_151b8 [4112];
  undefined1 auStack_141a8 [4112];
  undefined1 auStack_13198 [4112];
  undefined1 auStack_12188 [4112];
  undefined1 auStack_11178 [4112];
  undefined1 auStack_10168 [4112];
  undefined1 auStack_f158 [4112];
  undefined1 auStack_e148 [4112];
  undefined1 auStack_d138 [4112];
  undefined1 auStack_c128 [4112];
  undefined1 auStack_b118 [4112];
  undefined1 auStack_a108 [4112];
  undefined1 auStack_90f8 [4112];
  undefined1 auStack_80e8 [4112];
  undefined1 auStack_70d8 [4112];
  undefined1 auStack_60c8 [4112];
  undefined1 auStack_50b8 [4112];
  undefined1 auStack_40a8 [4112];
  ulong *local_3098;
  undefined8 uStack_3090;
  ulong *local_3088;
  void *pvStack_3080;
  void *local_3078;
  undefined8 uStack_3070;
  undefined4 local_3068;
  byte *local_2088;
  int local_2080;
  basic_string *local_1078;
  undefined4 local_1070;
  long local_68;
  
  iVar16 = (int)param_4;
  lVar2 = tpidr_el0;
  local_68 = *(long *)(lVar2 + 0x28);
  local_1078 = *(basic_string **)(param_1 + 0x10);
  if ((*param_1 & 1) == 0) {
    local_1078 = param_1 + 1;
  }
  local_1070 = 0;
  *(undefined4 *)(param_4 + 0x2e8) = 0;
  pIVar35 = (IESNet *)param_3;
  StringParser::NextLine();
  local_2088 = (byte *)((ulong)abStack_31390 | 1);
  if ((abStack_31390[0] & 1) != 0) {
    local_2088 = pbStack_31380;
  }
  local_2080 = 0;
  StringParser::NextString();
  iVar8 = 1;
  param_4[0x410] = (IESNet)0x0;
  *(undefined4 *)(param_4 + 0x414) = 1;
  param_4[0x24] = (IESNet)0x0;
  pcVar14 = (char *)((ulong)abStack_313a8 | 1);
  if ((abStack_313a8[0] & 1) != 0) {
    pcVar14 = pcStack_31398;
  }
  iVar7 = strcmp(pcVar14,"E");
  if (iVar7 == 0) {
    *(undefined4 *)(param_4 + 0x2e8) = 1;
    StringParser::NextLine();
    uVar30 = (ulong)local_3098 & 0xff;
    puVar19 = (ulong *)((ulong)&local_3098 | 1);
    if (((ulong)local_3098 & 1) != 0) {
      puVar19 = local_3088;
    }
    uVar9 = (uint)(byte)*puVar19;
    if ((byte)*puVar19 != 0) {
LAB_00240a54:
      if ((uVar9 != 0x2d) && (9 < (uVar9 - 0x30 & 0xff))) goto code_r0x00240a70;
      iVar8 = 0;
      if (uVar9 != 0x2d) {
        iVar7 = 1;
        goto LAB_00240bcc;
      }
      iVar7 = -1;
      while( true ) {
        puVar19 = (ulong *)((long)puVar19 + 1);
        uVar9 = (uint)*(byte *)puVar19;
LAB_00240bcc:
        if (9 < (uVar9 - 0x30 & 0xff)) break;
        iVar8 = iVar8 * 10 + uVar9 + -0x30;
      }
      uVar34 = (ulong)(uint)(iVar7 * iVar8);
      while( true ) {
        if (uVar9 == 0) goto LAB_00240c20;
        if ((uVar9 == 0x2d) || ((uVar9 - 0x30 & 0xff) < 10)) break;
        puVar19 = (ulong *)((long)puVar19 + 1);
        uVar9 = (uint)*(byte *)puVar19;
      }
      iStack_31454 = 0;
      if (uVar9 != 0x2d) {
        iVar8 = 1;
        goto LAB_00243744;
      }
      iVar8 = -1;
      while( true ) {
        puVar19 = (ulong *)((long)puVar19 + 1);
        uVar9 = (uint)*(byte *)puVar19;
LAB_00243744:
        if (9 < (uVar9 - 0x30 & 0xff)) break;
        iStack_31454 = iStack_31454 * 10 + uVar9 + -0x30;
      }
      goto LAB_00240d34;
    }
    uVar34 = 0;
    iStack_31454 = 0;
    goto LAB_00240d34;
  }
  iVar8 = strcmp(pcVar14,"D");
  if (((iVar8 == 0) || (iVar8 = strcmp(pcVar14,"C"), iVar8 == 0)) ||
     (iVar8 = strcmp(pcVar14,"S"), iVar8 == 0)) {
    iVar8 = strcmp(pcVar14,"S");
    if (iVar8 == 0) {
      param_4[0x24] = (IESNet)0x1;
    }
    StringParser::NextLine();
    uVar30 = (ulong)local_3098 & 0xff;
    puVar19 = (ulong *)((ulong)&local_3098 | 1);
    if (((ulong)local_3098 & 1) != 0) {
      puVar19 = local_3088;
    }
    uVar9 = (uint)(byte)*puVar19;
    if ((byte)*puVar19 != 0) {
LAB_00240970:
      if ((uVar9 != 0x2d) && (9 < (uVar9 - 0x30 & 0xff))) goto code_r0x0024098c;
      iVar8 = 0;
      if (uVar9 != 0x2d) {
        iVar7 = 1;
        goto LAB_00240b14;
      }
      iVar7 = -1;
      while( true ) {
        puVar19 = (ulong *)((long)puVar19 + 1);
        uVar9 = (uint)*(byte *)puVar19;
LAB_00240b14:
        if (9 < (uVar9 - 0x30 & 0xff)) break;
        iVar8 = iVar8 * 10 + uVar9 + -0x30;
      }
      uVar34 = (ulong)(uint)(iVar7 * iVar8);
      while( true ) {
        if (uVar9 == 0) goto LAB_00240998;
        if ((uVar9 == 0x2d) || ((uVar9 - 0x30 & 0xff) < 10)) break;
        puVar19 = (ulong *)((long)puVar19 + 1);
        uVar9 = (uint)*(byte *)puVar19;
      }
      iStack_31454 = 0;
      if (uVar9 != 0x2d) {
        iVar8 = 1;
        goto LAB_0024376c;
      }
      iVar8 = -1;
      while( true ) {
        puVar19 = (ulong *)((long)puVar19 + 1);
        uVar9 = (uint)*(byte *)puVar19;
LAB_0024376c:
        if (9 < (uVar9 - 0x30 & 0xff)) break;
        iStack_31454 = iStack_31454 * 10 + uVar9 + -0x30;
      }
      goto LAB_002409a0;
    }
LAB_00240994:
    uVar34 = 0;
LAB_00240998:
    iStack_31454 = 0;
    iVar8 = 1;
LAB_002409a0:
    iStack_31454 = iStack_31454 * iVar8;
    do {
      uVar9 = (uint)*local_2088;
      pbVar18 = local_2088 + 1;
      switch(uVar9) {
      case 0:
        goto switchD_002409e4_caseD_0;
      default:
        goto switchD_002409e4_caseD_1;
      case 10:
      case 0xd:
      case 0x20:
        local_2080 = local_2080 + 1;
        local_2088 = local_2088 + 1;
      }
    } while( true );
  }
  iVar8 = strcmp(pcVar14,"B");
  if (iVar8 != 0) {
    uVar9 = atoi(pcVar14);
    uVar34 = (ulong)uVar9;
    *(uint *)(param_4 + 0x390) = uVar9;
    uVar9 = (uint)*local_2088;
    if (*local_2088 != 0) {
LAB_002408dc:
      pbVar18 = local_2088 + 1;
      if ((uVar9 != 0x2d) && (9 < (uVar9 - 0x30 & 0xff))) goto code_r0x002408f8;
      iStack_31454 = 0;
      if (uVar9 != 0x2d) {
        iVar8 = 1;
        goto LAB_00240d10;
      }
      iVar8 = -1;
      while( true ) {
        local_2080 = local_2080 + 1;
        uVar9 = (uint)local_2088[1];
        local_2088 = local_2088 + 1;
LAB_00240d10:
        if (9 < (uVar9 - 0x30 & 0xff)) break;
        iStack_31454 = iStack_31454 * 10 + uVar9 + -0x30;
      }
      goto LAB_00240c48;
    }
LAB_00240ae8:
    iStack_31454 = 0;
    iVar8 = 1;
LAB_00240c48:
    iStack_31454 = iStack_31454 * iVar8;
    do {
      pbVar18 = local_2088 + 1;
      switch(uVar9) {
      case 0:
        goto switchD_00240c88_caseD_0;
      default:
        goto switchD_00240c88_caseD_1;
      case 10:
      case 0xd:
      case 0x20:
        local_2080 = local_2080 + 1;
        uVar9 = (uint)*pbVar18;
        local_2088 = pbVar18;
      }
    } while( true );
  }
  goto LAB_002417cc;
code_r0x00240a70:
  puVar19 = (ulong *)((long)puVar19 + 1);
  uVar9 = (uint)*(byte *)puVar19;
  if (uVar9 == 0) goto code_r0x00240a78;
  goto LAB_00240a54;
code_r0x00240a78:
  uVar34 = 0;
LAB_00240c20:
  iStack_31454 = 0;
  iVar8 = 1;
LAB_00240d34:
  iStack_31454 = iStack_31454 * iVar8;
  do {
    uVar9 = (uint)*local_2088;
    pbVar18 = local_2088 + 1;
    switch(uVar9) {
    case 0:
      goto switchD_002409e4_caseD_0;
    default:
      goto switchD_00240d78_caseD_1;
    case 10:
    case 0xd:
    case 0x20:
      local_2080 = local_2080 + 1;
      local_2088 = local_2088 + 1;
    }
  } while( true );
switchD_00240d78_caseD_1:
  pbVar23 = pbVar18;
  if ((uVar9 != 0x2d) && (9 < (uVar9 - 0x30 & 0xff))) goto code_r0x00240da8;
  iVar8 = 0;
  pbVar23 = pbVar23 + -1;
  if (uVar9 != 0x2d) {
    iVar7 = 1;
    goto LAB_0024371c;
  }
  iVar7 = -1;
  pbVar18 = pbVar23;
  while( true ) {
    pbVar23 = pbVar18 + 1;
    local_2080 = local_2080 + 1;
    uVar9 = (uint)pbVar18[1];
    local_2088 = pbVar23;
LAB_0024371c:
    if (9 < (uVar9 - 0x30 & 0xff)) break;
    iVar8 = iVar8 * 10 + uVar9 + -0x30;
    pbVar18 = pbVar23;
  }
  goto LAB_00240dec;
code_r0x00240da8:
  local_2080 = local_2080 + 1;
  uVar9 = (uint)*pbVar23;
  pbVar18 = pbVar23 + 1;
  local_2088 = pbVar23;
  if (*pbVar23 == 0) goto LAB_00240dc0;
  goto switchD_00240d78_caseD_1;
code_r0x0024098c:
  puVar19 = (ulong *)((long)puVar19 + 1);
  uVar9 = (uint)*(byte *)puVar19;
  if (uVar9 == 0) goto LAB_00240994;
  goto LAB_00240970;
switchD_002409e4_caseD_1:
  pbVar23 = pbVar18;
  if ((uVar9 != 0x2d) && (9 < (uVar9 - 0x30 & 0xff))) goto code_r0x00240a04;
  iVar8 = 0;
  pbVar23 = pbVar23 + -1;
  if (uVar9 != 0x2d) {
    iVar7 = 1;
    goto LAB_00240ba4;
  }
  iVar7 = -1;
  pbVar18 = pbVar23;
  while( true ) {
    pbVar23 = pbVar18 + 1;
    local_2080 = local_2080 + 1;
    uVar9 = (uint)pbVar18[1];
    local_2088 = pbVar23;
LAB_00240ba4:
    if (9 < (uVar9 - 0x30 & 0xff)) break;
    iVar8 = iVar8 * 10 + uVar9 + -0x30;
    pbVar18 = pbVar23;
  }
  goto LAB_00240dec;
code_r0x00240a04:
  local_2080 = local_2080 + 1;
  uVar9 = (uint)*pbVar23;
  pbVar18 = pbVar23 + 1;
  local_2088 = pbVar23;
  if (*pbVar23 == 0) goto LAB_00240dc0;
  goto switchD_002409e4_caseD_1;
LAB_00240dc0:
  iVar8 = 0;
  iVar7 = 1;
LAB_00240dec:
  *(int *)(param_4 + 8) = iVar8 * iVar7;
joined_r0x00240df4:
  if ((uVar30 & 1) != 0) {
    operator_delete(local_3088);
  }
  goto LAB_00240dfc;
switchD_002409e4_caseD_0:
  *(undefined4 *)(param_4 + 8) = 0;
  goto joined_r0x00240df4;
code_r0x002408f8:
  local_2080 = local_2080 + 1;
  uVar9 = (uint)*pbVar18;
  local_2088 = pbVar18;
  if (uVar9 == 0) goto LAB_00240ae8;
  goto LAB_002408dc;
switchD_00240c88_caseD_1:
  pbVar23 = pbVar18;
  if ((uVar9 != 0x2d) && (9 < (uVar9 - 0x30 & 0xff))) goto code_r0x00240cb0;
  iVar8 = 0;
  pbVar23 = pbVar23 + -1;
  if (uVar9 != 0x2d) {
    iVar7 = 1;
    goto LAB_002437bc;
  }
  iVar7 = -1;
  pbVar18 = pbVar23;
  while( true ) {
    pbVar23 = pbVar18 + 1;
    local_2080 = local_2080 + 1;
    uVar9 = (uint)pbVar18[1];
    local_2088 = pbVar23;
LAB_002437bc:
    if (9 < (uVar9 - 0x30 & 0xff)) break;
    iVar8 = iVar8 * 10 + uVar9 + -0x30;
    pbVar18 = pbVar23;
  }
  goto LAB_00243798;
code_r0x00240cb0:
  local_2080 = local_2080 + 1;
  uVar9 = (uint)*pbVar23;
  pbVar18 = pbVar23 + 1;
  local_2088 = pbVar23;
  if (uVar9 == 0) goto code_r0x00240cc8;
  goto switchD_00240c88_caseD_1;
switchD_00240c88_caseD_0:
  iVar8 = 0;
  goto LAB_00243790;
code_r0x0024174c:
  uStack_3090 = (ulong *)CONCAT44(uStack_3090._4_4_,(int)uStack_3090 + 1);
  uVar9 = (uint)*(byte *)puVar19;
  local_3098 = puVar19;
  if (uVar9 == 0) goto LAB_00241764;
  goto LAB_00241730;
code_r0x0024125c:
  iVar26 = 1;
  uStack_3090 = (ulong *)CONCAT44(uStack_3090._4_4_,(int)uStack_3090 + 1);
  uVar9 = (uint)*(byte *)puVar19;
  local_3098 = puVar19;
  if (uVar9 == 0) goto code_r0x00241278;
  goto LAB_00241240;
code_r0x00241278:
  iVar28 = 0;
LAB_002410b8:
  bVar6 = iVar28 * iVar26 == 4;
  if (bVar6) {
    lVar27 = lVar31 * 4;
    *(int *)(*(long *)(pfVar10 + 6) + lVar27) = iVar8;
    *(int *)(*(long *)(pfVar10 + 8) + lVar27) = iVar7;
    *(int *)(*(long *)(pfVar10 + 10) + lVar27) = iVar24;
    *(int *)(*(long *)(pfVar10 + 0xc) + lVar27) = iVar25;
  }
  if (((ulong)puStack_313f0 & 1) != 0) {
    operator_delete(pcStack_313e0);
  }
  if (bVar6) goto LAB_002413a4;
LAB_0024179c:
  bVar6 = true;
joined_r0x002413ac:
  if (((ulong)apfStack_313d8[0] & 1) != 0) {
    operator_delete(pcStack_313c8);
  }
  if (((ulong)uStack_21278 & 1) != 0) {
    operator_delete(puStack_21268);
  }
  if (bVar6) {
    bVar6 = true;
    puVar19 = puStack_313c0;
    goto joined_r0x002417c4;
  }
  lVar31 = lVar31 + 1;
  if ((int)fVar33 <= lVar31) goto LAB_002417ec;
  goto LAB_00241580;
code_r0x00241620:
  uStack_3090 = (ulong *)CONCAT44(uStack_3090._4_4_,(int)uStack_3090 + 1);
  uVar9 = (uint)*(byte *)puVar19;
  local_3098 = puVar19;
  if (uVar9 == 0) goto LAB_00241638;
  goto LAB_00241604;
code_r0x002414fc:
  iVar26 = 1;
  uStack_3090 = (ulong *)CONCAT44(uStack_3090._4_4_,(int)uStack_3090 + 1);
  uVar9 = (uint)*(byte *)puVar19;
  local_3098 = puVar19;
  if (uVar9 == 0) goto code_r0x00241518;
  goto LAB_002414e0;
code_r0x00241518:
  iVar28 = 0;
LAB_00241374:
  if (iVar28 * iVar26 == 4) {
    lVar27 = lVar31 * 4;
    *(int *)(*(long *)(pfVar10 + 6) + lVar27) = iVar8;
    *(int *)(*(long *)(pfVar10 + 8) + lVar27) = iVar7;
    *(int *)(*(long *)(pfVar10 + 10) + lVar27) = iVar24;
    *(int *)(*(long *)(pfVar10 + 0xc) + lVar27) = iVar25;
LAB_002413a4:
    bVar6 = false;
    goto joined_r0x002413ac;
  }
  goto LAB_0024179c;
code_r0x00240cc8:
  iVar8 = 0;
  iVar7 = 1;
LAB_00243798:
  iVar8 = iVar8 * iVar7;
LAB_00243790:
  *(int *)(param_4 + 8) = iVar8;
LAB_00240dfc:
  pfVar10 = malloc(0x38);
  uVar30 = -(uVar34 >> 0x1f) & 0xfffffffc00000000 | uVar34 << 2;
  fVar33 = (float)uVar34;
  pfVar10[1] = fVar33;
  pvVar11 = malloc(uVar30);
  *(void **)(pfVar10 + 4) = pvVar11;
  pvVar11 = malloc(uVar30);
  *(void **)(pfVar10 + 2) = pvVar11;
  pvVar11 = malloc(uVar30);
  *(void **)(pfVar10 + 6) = pvVar11;
  pvVar11 = malloc(uVar30);
  *(void **)(pfVar10 + 8) = pvVar11;
  pvVar11 = malloc(uVar30);
  *(void **)(pfVar10 + 10) = pvVar11;
  pvVar11 = malloc(uVar30);
  *(void **)(pfVar10 + 0xc) = pvVar11;
  puStack_313b8 = (ulong *)0x0;
  puStack_313b0 = (ulong *)0x0;
  *pfVar10 = 1.4013e-45;
  puStack_313c0 = (ulong *)0x0;
  if (0 < (int)fVar33) {
    lVar31 = 0;
LAB_00241580:
    StringParser::NextLine();
    local_3098 = (ulong *)((ulong)&uStack_21278 | 1);
    if (((ulong)uStack_21278 & 1) != 0) {
      local_3098 = puStack_21268;
    }
    uStack_3090 = (ulong *)((ulong)uStack_3090 & 0xffffffff00000000);
    StringParser::NextString();
    pcVar14 = (char *)((ulong)apfStack_313d8 | 1);
    if (((ulong)apfStack_313d8[0] & 1) != 0) {
      pcVar14 = pcStack_313c8;
    }
    iVar8 = strcmp(pcVar14,"DataV2");
    if (iVar8 == 0) {
      StringParser::NextString();
      puVar19 = puStack_313b8;
      pcVar14 = pcStack_313e0;
      pIVar1 = pIStack_313e8;
      if (puStack_313b8 == puStack_313b0) {
        FUN_001416f8(&puStack_313c0,&puStack_313f0);
      }
      else {
        puStack_313b8[1] = 0;
        puStack_313b8[2] = 0;
        *puStack_313b8 = 0;
        if (((ulong)puStack_313f0 & 1) == 0) {
          puStack_313b8[2] = (ulong)pcStack_313e0;
          puStack_313b8[1] = (ulong)pIStack_313e8;
          *puStack_313b8 = (ulong)puStack_313f0;
        }
        else {
          if (pIStack_313e8 < (IESNet *)0x17) {
            pbVar18 = (byte *)((long)puStack_313b8 + 1);
            *(byte *)puStack_313b8 = (byte)((long)pIStack_313e8 << 1);
            if (pIStack_313e8 != (IESNet *)0x0) goto LAB_002416f0;
          }
          else {
            pIVar35 = pIStack_313e8 + 0x10;
            pbVar18 = operator_new((ulong)pIVar35 & 0xfffffffffffffff0);
            puVar19[1] = (ulong)pIVar1;
            puVar19[2] = (ulong)pbVar18;
            *puVar19 = (ulong)pIVar35 & 0xfffffffffffffff0 | 1;
LAB_002416f0:
            pIVar35 = pIVar1;
            memcpy(pbVar18,pcVar14,(size_t)pIVar1);
          }
          pbVar18[(long)pIVar1] = 0;
        }
        puStack_313b8 = puStack_313b8 + 3;
      }
      uVar9 = (uint)(byte)*local_3098;
      if (uVar9 != 0) {
LAB_00241730:
        puVar19 = (ulong *)((long)local_3098 + 1);
        if ((uVar9 != 0x2d) && (9 < (uVar9 - 0x30 & 0xff))) goto code_r0x0024174c;
        iVar8 = 0;
        if (uVar9 != 0x2d) {
          iVar7 = 1;
          goto LAB_00240f90;
        }
        iVar7 = -1;
        while( true ) {
          uStack_3090 = (ulong *)CONCAT44(uStack_3090._4_4_,(int)uStack_3090 + 1);
          uVar9 = (uint)*(byte *)((long)local_3098 + 1);
          local_3098 = (ulong *)((long)local_3098 + 1);
LAB_00240f90:
          if (9 < (uVar9 - 0x30 & 0xff)) break;
          iVar8 = iVar8 * 10 + uVar9 + -0x30;
        }
        iVar8 = iVar8 * iVar7;
        while( true ) {
          if (uVar9 == 0) {
            iVar7 = 0;
            goto LAB_002410ac;
          }
          if ((uVar9 == 0x2d) || ((uVar9 - 0x30 & 0xff) < 10)) break;
          uStack_3090._4_4_ = (undefined4)((ulong)uStack_3090 >> 0x20);
          uStack_3090 = (ulong *)CONCAT44(uStack_3090._4_4_,(int)uStack_3090 + 1);
          uVar9 = (uint)*(byte *)((long)local_3098 + 1);
          local_3098 = (ulong *)((long)local_3098 + 1);
        }
        iVar7 = 0;
        if (uVar9 != 0x2d) {
          iVar24 = 1;
          goto LAB_00241040;
        }
        iVar24 = -1;
        while( true ) {
          uStack_3090 = (ulong *)CONCAT44(uStack_3090._4_4_,(int)uStack_3090 + 1);
          uVar9 = (uint)*(byte *)((long)local_3098 + 1);
          local_3098 = (ulong *)((long)local_3098 + 1);
LAB_00241040:
          if (9 < (uVar9 - 0x30 & 0xff)) break;
          iVar7 = iVar7 * 10 + uVar9 + -0x30;
        }
        iVar7 = iVar7 * iVar24;
        while( true ) {
          if (uVar9 == 0) goto LAB_002410ac;
          if ((uVar9 == 0x2d) || ((uVar9 - 0x30 & 0xff) < 10)) break;
          uStack_3090._4_4_ = (undefined4)((ulong)uStack_3090 >> 0x20);
          uStack_3090 = (ulong *)CONCAT44(uStack_3090._4_4_,(int)uStack_3090 + 1);
          uVar9 = (uint)*(byte *)((long)local_3098 + 1);
          local_3098 = (ulong *)((long)local_3098 + 1);
        }
        iVar24 = 0;
        if (uVar9 != 0x2d) {
          iVar25 = 1;
          goto LAB_00241150;
        }
        iVar25 = -1;
        while( true ) {
          uStack_3090 = (ulong *)CONCAT44(uStack_3090._4_4_,(int)uStack_3090 + 1);
          uVar9 = (uint)*(byte *)((long)local_3098 + 1);
          local_3098 = (ulong *)((long)local_3098 + 1);
LAB_00241150:
          if (9 < (uVar9 - 0x30 & 0xff)) break;
          iVar24 = iVar24 * 10 + uVar9 + -0x30;
        }
        iVar24 = iVar24 * iVar25;
        if (uVar9 == 0) {
          iVar25 = 0;
          goto LAB_002410b4;
        }
        while ((puVar19 = (ulong *)((long)local_3098 + 1), uVar9 != 0x2d &&
               (9 < (uVar9 - 0x30 & 0xff)))) {
          iVar26 = 1;
          uStack_3090 = (ulong *)CONCAT44(uStack_3090._4_4_,(int)uStack_3090 + 1);
          uVar9 = (uint)*(byte *)puVar19;
          local_3098 = puVar19;
          if (uVar9 == 0) {
            iVar25 = 0;
            iVar28 = 0;
            goto LAB_002410b8;
          }
        }
        iVar25 = 0;
        if (uVar9 != 0x2d) {
          iVar26 = 1;
          goto LAB_0024120c;
        }
        iVar26 = -1;
        while( true ) {
          uStack_3090 = (ulong *)CONCAT44(uStack_3090._4_4_,(int)uStack_3090 + 1);
          uVar9 = (uint)*(byte *)((long)local_3098 + 1);
          local_3098 = (ulong *)((long)local_3098 + 1);
LAB_0024120c:
          if (9 < (uVar9 - 0x30 & 0xff)) break;
          iVar25 = iVar25 * 10 + uVar9 + -0x30;
        }
        iVar25 = iVar25 * iVar26;
        if (uVar9 == 0) goto LAB_002410b4;
LAB_00241240:
        puVar19 = (ulong *)((long)local_3098 + 1);
        if ((uVar9 != 0x2d) && (9 < (uVar9 - 0x30 & 0xff))) goto code_r0x0024125c;
        iVar28 = 0;
        if (uVar9 != 0x2d) {
          iVar26 = 1;
          goto LAB_002412bc;
        }
        iVar26 = -1;
        while( true ) {
          uStack_3090 = (ulong *)CONCAT44(uStack_3090._4_4_,(int)uStack_3090 + 1);
          uVar9 = (uint)*(byte *)((long)local_3098 + 1);
          local_3098 = (ulong *)((long)local_3098 + 1);
LAB_002412bc:
          if (9 < (uVar9 - 0x30 & 0xff)) break;
          iVar28 = iVar28 * 10 + uVar9 + -0x30;
        }
        goto LAB_002410b8;
      }
LAB_00241764:
      iVar7 = 0;
      iVar8 = 0;
LAB_002410ac:
      iVar25 = 0;
      iVar24 = 0;
LAB_002410b4:
      iVar28 = 0;
      iVar26 = 1;
      goto LAB_002410b8;
    }
    iVar8 = strcmp(pcVar14,"data");
    if (iVar8 == 0) {
      uVar9 = (uint)(byte)*local_3098;
      if (uVar9 != 0) {
LAB_00241604:
        puVar19 = (ulong *)((long)local_3098 + 1);
        if ((uVar9 != 0x2d) && (9 < (uVar9 - 0x30 & 0xff))) goto code_r0x00241620;
        iVar8 = 0;
        if (uVar9 != 0x2d) {
          iVar7 = 1;
          goto LAB_00240ee0;
        }
        iVar7 = -1;
        while( true ) {
          uStack_3090 = (ulong *)CONCAT44(uStack_3090._4_4_,(int)uStack_3090 + 1);
          uVar9 = (uint)*(byte *)((long)local_3098 + 1);
          local_3098 = (ulong *)((long)local_3098 + 1);
LAB_00240ee0:
          if (9 < (uVar9 - 0x30 & 0xff)) break;
          iVar8 = iVar8 * 10 + uVar9 + -0x30;
        }
        iVar8 = iVar8 * iVar7;
        while( true ) {
          if (uVar9 == 0) {
            iVar7 = 0;
            goto LAB_00241368;
          }
          if ((uVar9 == 0x2d) || ((uVar9 - 0x30 & 0xff) < 10)) break;
          uStack_3090._4_4_ = (undefined4)((ulong)uStack_3090 >> 0x20);
          uStack_3090 = (ulong *)CONCAT44(uStack_3090._4_4_,(int)uStack_3090 + 1);
          uVar9 = (uint)*(byte *)((long)local_3098 + 1);
          local_3098 = (ulong *)((long)local_3098 + 1);
        }
        iVar7 = 0;
        if (uVar9 != 0x2d) {
          iVar24 = 1;
          goto LAB_002412fc;
        }
        iVar24 = -1;
        while( true ) {
          uStack_3090 = (ulong *)CONCAT44(uStack_3090._4_4_,(int)uStack_3090 + 1);
          uVar9 = (uint)*(byte *)((long)local_3098 + 1);
          local_3098 = (ulong *)((long)local_3098 + 1);
LAB_002412fc:
          if (9 < (uVar9 - 0x30 & 0xff)) break;
          iVar7 = iVar7 * 10 + uVar9 + -0x30;
        }
        iVar7 = iVar7 * iVar24;
        while( true ) {
          if (uVar9 == 0) goto LAB_00241368;
          if ((uVar9 == 0x2d) || ((uVar9 - 0x30 & 0xff) < 10)) break;
          uStack_3090._4_4_ = (undefined4)((ulong)uStack_3090 >> 0x20);
          uStack_3090 = (ulong *)CONCAT44(uStack_3090._4_4_,(int)uStack_3090 + 1);
          uVar9 = (uint)*(byte *)((long)local_3098 + 1);
          local_3098 = (ulong *)((long)local_3098 + 1);
        }
        iVar24 = 0;
        if (uVar9 != 0x2d) {
          iVar25 = 1;
          goto LAB_002413f0;
        }
        iVar25 = -1;
        while( true ) {
          uStack_3090 = (ulong *)CONCAT44(uStack_3090._4_4_,(int)uStack_3090 + 1);
          uVar9 = (uint)*(byte *)((long)local_3098 + 1);
          local_3098 = (ulong *)((long)local_3098 + 1);
LAB_002413f0:
          if (9 < (uVar9 - 0x30 & 0xff)) break;
          iVar24 = iVar24 * 10 + uVar9 + -0x30;
        }
        iVar24 = iVar24 * iVar25;
        if (uVar9 == 0) {
          iVar25 = 0;
          goto LAB_00241370;
        }
        while ((puVar19 = (ulong *)((long)local_3098 + 1), uVar9 != 0x2d &&
               (9 < (uVar9 - 0x30 & 0xff)))) {
          iVar26 = 1;
          uStack_3090 = (ulong *)CONCAT44(uStack_3090._4_4_,(int)uStack_3090 + 1);
          uVar9 = (uint)*(byte *)puVar19;
          local_3098 = puVar19;
          if (uVar9 == 0) {
            iVar25 = 0;
            iVar28 = 0;
            goto LAB_00241374;
          }
        }
        iVar25 = 0;
        if (uVar9 != 0x2d) {
          iVar26 = 1;
          goto LAB_002414ac;
        }
        iVar26 = -1;
        while( true ) {
          uStack_3090 = (ulong *)CONCAT44(uStack_3090._4_4_,(int)uStack_3090 + 1);
          uVar9 = (uint)*(byte *)((long)local_3098 + 1);
          local_3098 = (ulong *)((long)local_3098 + 1);
LAB_002414ac:
          if (9 < (uVar9 - 0x30 & 0xff)) break;
          iVar25 = iVar25 * 10 + uVar9 + -0x30;
        }
        iVar25 = iVar25 * iVar26;
        if (uVar9 == 0) goto LAB_00241370;
LAB_002414e0:
        puVar19 = (ulong *)((long)local_3098 + 1);
        if ((uVar9 != 0x2d) && (9 < (uVar9 - 0x30 & 0xff))) goto code_r0x002414fc;
        iVar28 = 0;
        if (uVar9 != 0x2d) {
          iVar26 = 1;
          goto LAB_0024155c;
        }
        iVar26 = -1;
        while( true ) {
          uStack_3090 = (ulong *)CONCAT44(uStack_3090._4_4_,(int)uStack_3090 + 1);
          uVar9 = (uint)*(byte *)((long)local_3098 + 1);
          local_3098 = (ulong *)((long)local_3098 + 1);
LAB_0024155c:
          if (9 < (uVar9 - 0x30 & 0xff)) break;
          iVar28 = iVar28 * 10 + uVar9 + -0x30;
        }
        goto LAB_00241374;
      }
LAB_00241638:
      iVar7 = 0;
      iVar8 = 0;
LAB_00241368:
      iVar25 = 0;
      iVar24 = 0;
LAB_00241370:
      iVar28 = 0;
      iVar26 = 1;
      goto LAB_00241374;
    }
    goto LAB_0024179c;
  }
LAB_002417ec:
  local_3068 = 1;
  iStack_2126c = 1;
  pvStack_21250 = (void *)0x0;
  pvStack_3080 = (void *)0x0;
  local_3088 = (ulong *)0x0;
  uStack_3070 = 0;
  local_3078 = (void *)0x0;
  uStack_3090 = (ulong *)0x0;
  local_3098 = (ulong *)0x0;
  uStack_21260 = 0;
  pIStack_21258 = (IESNet *)0x0;
  if (0 < (int)fVar33) {
    lVar31 = 0;
    uVar30 = 0;
    do {
      lVar27 = uVar30 * 4;
      iStack_2126c = *(int *)(*(long *)(pfVar10 + 6) + lVar27);
      iStack_21270 = *(int *)(*(long *)(pfVar10 + 0xc) + lVar27);
      uVar9 = iStack_21270 * iStack_2126c * *(int *)(*(long *)(pfVar10 + 8) + lVar27) *
              *(int *)(*(long *)(pfVar10 + 10) + lVar27);
      pIVar35 = (IESNet *)(-(ulong)(uVar9 >> 0x1f) & 0xfffffffc00000000 | (ulong)uVar9 << 2);
      uStack_21278 = (undefined8 *)
                     CONCAT44(*(int *)(*(long *)(pfVar10 + 8) + lVar27),
                              *(int *)(*(long *)(pfVar10 + 10) + lVar27));
      __s = malloc((size_t)pIVar35);
      apfStack_313d8[0] = __s;
      memset(__s,0,(size_t)pIVar35);
      if (*(undefined8 **)(param_4 + 1000) == *(undefined8 **)(param_4 + 0x3f0)) {
        std::__ndk1::vector<float*,std::__ndk1::allocator<float*>>::
        __push_back_slow_path<float*const&>
                  ((vector<float*,std::__ndk1::allocator<float*>> *)(param_4 + 0x3e0),apfStack_313d8
                  );
      }
      else {
        **(undefined8 **)(param_4 + 1000) = __s;
        *(long *)(param_4 + 1000) = *(long *)(param_4 + 1000) + 8;
      }
      uVar37 = *(ulong *)(param_4 + 0x400);
      if (uVar37 == *(long *)(param_4 + 0x408) * 0x40) {
        if (uVar37 < 0x3fffffffffffffff) {
          uVar20 = *(long *)(param_4 + 0x408) << 7;
          uVar37 = uVar37 + 0x40 & 0xffffffffffffffc0;
          if (uVar37 <= uVar20) {
            uVar37 = uVar20;
          }
        }
        else {
          uVar37 = 0x7fffffffffffffff;
        }
        std::__ndk1::vector<bool,std::__ndk1::allocator<bool>>::reserve
                  ((vector<bool,std::__ndk1::allocator<bool>> *)(param_4 + 0x3f8),uVar37);
        uVar37 = *(ulong *)(param_4 + 0x400);
      }
      *(ulong *)(param_4 + 0x400) = uVar37 + 1;
      uVar20 = uVar37 >> 3 & 0x1ffffffffffffff8;
      *(ulong *)(*(long *)(param_4 + 0x3f8) + uVar20) =
           *(ulong *)(*(long *)(param_4 + 0x3f8) + uVar20) &
           (1L << (uVar37 & 0x3f) ^ 0xffffffffffffffffU);
      pIVar1 = pIStack_21258;
      if ((byte *)((long)puStack_313c0 + lVar31 + (-0x18 - (long)&uStack_21278)) != (byte *)0x0) {
        pbVar18 = (byte *)((long)puStack_313c0 + lVar31);
        pIVar1 = *(IESNet **)(pbVar18 + 8);
        pbVar23 = *(byte **)(pbVar18 + 0x10);
        if ((*pbVar18 & 1) == 0) {
          pbVar23 = pbVar18 + 1;
          pIVar1 = (IESNet *)(ulong)(*pbVar18 >> 1);
        }
        if ((uStack_21260 & 1) == 0) {
          pIVar29 = (IESNet *)0x16;
          uVar37 = uStack_21260 & 0xff;
        }
        else {
          pIVar29 = (IESNet *)((uStack_21260 & 0xfffffffffffffffe) - 1);
          uVar37 = uStack_21260;
        }
        pvVar11 = (void *)((long)&uStack_21260 + 1);
        if ((uVar37 & 1) != 0) {
          pvVar11 = pvStack_21250;
        }
        if (pIVar29 < pIVar1) {
          if (pIVar29 < (IESNet *)0x7fffffffffffffe7) {
            pIVar35 = (IESNet *)((long)pIVar29 * 2);
            if (pIVar35 < pIVar1 || (long)pIVar35 - (long)pIVar1 == 0) {
              pIVar35 = pIVar1;
            }
            if (pIVar35 < (IESNet *)0x17) {
              uVar37 = 0x17;
            }
            else {
              uVar37 = (ulong)(pIVar35 + 0x10) & 0xfffffffffffffff0;
            }
          }
          else {
            uVar37 = 0xffffffffffffffef;
          }
          pvVar32 = operator_new(uVar37);
          pIVar35 = pIVar1;
          memcpy(pvVar32,pbVar23,(size_t)pIVar1);
          if (pIVar29 != (IESNet *)0x16) {
            operator_delete(pvVar11);
          }
          uStack_21260 = uVar37 | 1;
          *(IESNet *)((long)pvVar32 + (long)pIVar1) = (IESNet)0x0;
          pvStack_21250 = pvVar32;
        }
        else {
          if (pIVar1 != (IESNet *)0x0) {
            pIVar35 = pIVar1;
            memmove(pvVar11,pbVar23,(size_t)pIVar1);
          }
          *(IESNet *)((long)pvVar11 + (long)pIVar1) = (IESNet)0x0;
          if ((uStack_21260 & 1) == 0) {
            uStack_21260 = CONCAT71(uStack_21260._1_7_,(char)((long)pIVar1 << 1));
            pIVar1 = pIStack_21258;
          }
        }
      }
      pIStack_21258 = pIVar1;
      puVar19 = uStack_3090;
      pvVar11 = pvStack_21250;
      pIVar1 = pIStack_21258;
      if (uStack_3090 == local_3088) {
        std::__ndk1::vector<IESNN::IESLayerTensor,std::__ndk1::allocator<IESNN::IESLayerTensor>>::
        __push_back_slow_path<IESNN::IESLayerTensor_const&>
                  ((vector<IESNN::IESLayerTensor,std::__ndk1::allocator<IESNN::IESLayerTensor>> *)
                   &local_3098,(IESLayerTensor *)&uStack_21278);
      }
      else {
        uStack_3090[2] = (ulong)puStack_21268;
        uStack_3090[1] = CONCAT44(iStack_2126c,iStack_21270);
        *uStack_3090 = (ulong)uStack_21278;
        puVar36 = uStack_3090 + 3;
        *puVar36 = 0;
        uStack_3090[4] = 0;
        uStack_3090[5] = 0;
        if ((uStack_21260 & 1) == 0) {
          uStack_3090[5] = (ulong)pvStack_21250;
          uStack_3090[4] = (ulong)pIStack_21258;
          *puVar36 = uStack_21260;
        }
        else {
          if (pIStack_21258 < (IESNet *)0x17) {
            pbVar18 = (byte *)((long)uStack_3090 + 0x19);
            *(byte *)puVar36 = (byte)((long)pIStack_21258 << 1);
            if (pIStack_21258 != (IESNet *)0x0) goto LAB_00241b2c;
          }
          else {
            pIVar35 = pIStack_21258 + 0x10;
            pbVar18 = operator_new((ulong)pIVar35 & 0xfffffffffffffff0);
            puVar19[4] = (ulong)pIVar1;
            puVar19[5] = (ulong)pbVar18;
            puVar19[3] = (ulong)pIVar35 & 0xfffffffffffffff0 | 1;
LAB_00241b2c:
            pIVar35 = pIVar1;
            memcpy(pbVar18,pvVar11,(size_t)pIVar1);
          }
          pbVar18[(long)pIVar1] = 0;
        }
        uStack_3090 = uStack_3090 + 6;
      }
      uVar30 = uVar30 + 1;
      lVar31 = lVar31 + 0x18;
    } while (uVar34 != uVar30);
  }
  this = *(vector<IESNN::IESLayerTensor,std::__ndk1::allocator<IESNN::IESLayerTensor>> **)
          (param_4 + 0x3a0);
  if (this == *(vector<IESNN::IESLayerTensor,std::__ndk1::allocator<IESNN::IESLayerTensor>> **)
               (param_4 + 0x3a8)) {
    std::__ndk1::vector<IESNN::IESGPUTensor,std::__ndk1::allocator<IESNN::IESGPUTensor>>::
    __push_back_slow_path<IESNN::IESGPUTensor_const&>
              ((vector<IESNN::IESGPUTensor,std::__ndk1::allocator<IESNN::IESGPUTensor>> *)
               (param_4 + 0x398),(IESGPUTensor *)&local_3098);
  }
  else {
    std::__ndk1::vector<IESNN::IESLayerTensor,std::__ndk1::allocator<IESNN::IESLayerTensor>>::vector
              (this,(vector *)&local_3098);
    std::__ndk1::vector<IESNN::IESLayerTensor,std::__ndk1::allocator<IESNN::IESLayerTensor>>::vector
              (this + 0x18,(vector *)&pvStack_3080);
    *(undefined4 *)(this + 0x30) = local_3068;
    *(long *)(param_4 + 0x3a0) = *(long *)(param_4 + 0x3a0) + 0x38;
  }
  apfStack_313d8[0] = pfVar10;
  if (*(undefined8 **)(param_4 + 0x3d0) < *(undefined8 **)(param_4 + 0x3d8)) {
    **(undefined8 **)(param_4 + 0x3d0) = pfVar10;
    *(long *)(param_4 + 0x3d0) = *(long *)(param_4 + 0x3d0) + 8;
    puVar19 = puStack_313c0;
  }
  else {
    std::__ndk1::vector<void*,std::__ndk1::allocator<void*>>::__push_back_slow_path<void*>
              ((vector<void*,std::__ndk1::allocator<void*>> *)(param_4 + 0x3c8),apfStack_313d8);
    puVar19 = puStack_313c0;
  }
  while (puVar36 = puStack_313b8, puVar5 = puStack_313c0, puVar36 != puStack_313c0) {
    puStack_313b8 = puVar36 + -3;
    puStack_313c0 = puVar5;
    if ((puVar36[-3] & 1) != 0) {
      puStack_313c0 = puVar19;
      operator_delete((void *)puVar36[-1]);
      puVar19 = puStack_313c0;
      puStack_313c0 = puVar5;
    }
  }
  puStack_313c0 = puVar19;
  puStack_313b8 = puVar36;
  if ((uStack_21260 & 1) != 0) {
    operator_delete(pvStack_21250);
  }
  pvVar11 = pvStack_3080;
  if (pvStack_3080 != (void *)0x0) {
    while (pvVar32 = local_3078, pvVar32 != pvVar11) {
      local_3078 = (void *)((long)pvVar32 + -0x30);
      if ((*(byte *)((long)pvVar32 + -0x18) & 1) != 0) {
        operator_delete(*(void **)((long)pvVar32 + -8));
      }
    }
    local_3078 = pvVar32;
    operator_delete(pvStack_3080);
  }
  puVar19 = local_3098;
  if (local_3098 != (ulong *)0x0) {
    while (puVar36 = uStack_3090, puVar36 != puVar19) {
      uStack_3090 = puVar36 + -6;
      if ((puVar36[-3] & 1) != 0) {
        operator_delete((void *)puVar36[-1]);
      }
    }
    uStack_3090 = puVar36;
    operator_delete(local_3098);
  }
  bVar6 = false;
  puVar19 = puStack_313c0;
joined_r0x002417c4:
  puStack_313c0 = puVar19;
  uVar3 = _DAT_00287a20;
  uVar4 = _UNK_00287a28;
  if (puVar19 != (ulong *)0x0) {
    while (puVar36 = puStack_313b8, puVar36 != puVar19) {
      puStack_313b8 = puVar36 + -3;
      if ((puVar36[-3] & 1) != 0) {
        operator_delete((void *)puVar36[-1]);
      }
    }
    puStack_313b8 = puVar36;
    operator_delete(puStack_313c0);
    uVar3 = _DAT_00287a20;
    uVar4 = _UNK_00287a28;
  }
  _DAT_00287a20 = uVar3;
  _UNK_00287a28 = uVar4;
  if (bVar6) {
LAB_002417cc:
    iVar7 = -2;
  }
  else {
    iVar8 = (int)pIVar35;
    if (0 < iStack_31454) {
      iVar24 = 0;
      iVar7 = -2;
      do {
        StringParser::NextLine();
        local_3098 = (ulong *)((ulong)&puStack_313c0 | 1);
        if (((ulong)puStack_313c0 & 1) != 0) {
          local_3098 = puStack_313b0;
        }
        uStack_3090 = (ulong *)((ulong)uStack_3090 & 0xffffffff00000000);
        StringParser::NextString();
        pcVar14 = (char *)((ulong)apfStack_313d8 | 1);
        if (((ulong)apfStack_313d8[0] & 1) != 0) {
          pcVar14 = pcStack_313c8;
        }
        iVar8 = strcmp(pcVar14,"Convolution");
        if (iVar8 == 0) {
          puVar12 = operator_new(0xe0);
          memset(puVar12,0,0xe0);
          *(undefined4 *)puVar12 = 3;
          *(undefined8 *)((long)puVar12 + 0x74) = 0x7f7fffff;
          *(undefined4 *)((long)puVar12 + 0x24) = 0;
          *(undefined8 *)((long)puVar12 + 0xc) = 0;
          *(undefined8 *)((long)puVar12 + 4) = 0;
          *(undefined8 *)((long)puVar12 + 0x1c) = 0;
          *(undefined8 *)((long)puVar12 + 0x14) = 0;
          puVar12[6] = 0x100000001;
          puVar12[5] = 0x100000001;
          *(undefined4 *)(puVar12 + 7) = 0;
          puVar12[0x15] = 0;
          puVar12[0x14] = 0;
          puVar12[0x12] = 0;
          puVar12[0x13] = puVar12 + 0x14;
          puVar12[0x18] = 0;
          puVar12[0x17] = 0;
          puVar12[0x16] = puVar12 + 0x17;
          puVar12[0x1b] = 0;
          puVar12[0x1a] = 0;
          *(undefined8 *)((long)puVar12 + 0x6c) = 0;
          puVar12[0x19] = puVar12 + 0x1a;
          puVar12[0x11] = 0;
          memcpy(auStack_40a8,&local_3098,0x1010);
          pIVar35 = param_4;
          parseConvLayerV1(auStack_40a8,puVar12);
LAB_0024264c:
          uStack_21278 = puVar12;
          if (*(undefined8 **)(param_4 + 0x3d0) < *(undefined8 **)(param_4 + 0x3d8)) {
            **(undefined8 **)(param_4 + 0x3d0) = puVar12;
            *(long *)(param_4 + 0x3d0) = *(long *)(param_4 + 0x3d0) + 8;
          }
          else {
LAB_00243448:
            std::__ndk1::vector<void*,std::__ndk1::allocator<void*>>::__push_back_slow_path<void*>
                      ((vector<void*,std::__ndk1::allocator<void*>> *)(param_4 + 0x3c8),
                       (void **)&uStack_21278);
          }
LAB_00243458:
          bVar6 = false;
        }
        else {
          iVar8 = strcmp(pcVar14,"DepthwiseSeparableConvolution");
          if (iVar8 == 0) {
            puVar12 = operator_new(0xe0);
            memset(puVar12,0,0xe0);
            *(undefined4 *)puVar12 = 3;
            *(undefined8 *)((long)puVar12 + 0x74) = 0x7f7fffff;
            *(undefined4 *)((long)puVar12 + 0x24) = 0;
            *(undefined8 *)((long)puVar12 + 0xc) = 0;
            *(undefined8 *)((long)puVar12 + 4) = 0;
            *(undefined8 *)((long)puVar12 + 0x1c) = 0;
            *(undefined8 *)((long)puVar12 + 0x14) = 0;
            puVar12[6] = 0x100000001;
            puVar12[5] = 0x100000001;
            *(undefined4 *)(puVar12 + 7) = 0;
            puVar12[0x15] = 0;
            puVar12[0x14] = 0;
            puVar12[0x12] = 0;
            puVar12[0x13] = puVar12 + 0x14;
            puVar12[0x18] = 0;
            puVar12[0x17] = 0;
            puVar12[0x16] = puVar12 + 0x17;
            puVar12[0x1b] = 0;
            puVar12[0x1a] = 0;
            *(undefined8 *)((long)puVar12 + 0x6c) = 0;
            puVar12[0x19] = puVar12 + 0x1a;
            puVar12[0x11] = 0;
            memcpy(auStack_50b8,&local_3098,0x1010);
            pIVar35 = param_4;
            parsedepthwiseConvLayerV1(auStack_50b8,puVar12);
            goto LAB_0024264c;
          }
          iVar8 = strcmp(pcVar14,"DepthwiseSeparableConvolutionV2");
          if (iVar8 == 0) {
            puVar12 = operator_new(0xe0);
            memset(puVar12,0,0xe0);
            *(undefined4 *)puVar12 = 3;
            *(undefined8 *)((long)puVar12 + 0x74) = 0x7f7fffff;
            *(undefined4 *)((long)puVar12 + 0x24) = 0;
            *(undefined8 *)((long)puVar12 + 0xc) = 0;
            *(undefined8 *)((long)puVar12 + 4) = 0;
            *(undefined8 *)((long)puVar12 + 0x1c) = 0;
            *(undefined8 *)((long)puVar12 + 0x14) = 0;
            puVar12[6] = 0x100000001;
            puVar12[5] = 0x100000001;
            *(undefined4 *)(puVar12 + 7) = 0;
            puVar12[0x15] = 0;
            puVar12[0x14] = 0;
            puVar12[0x12] = 0;
            puVar12[0x13] = puVar12 + 0x14;
            puVar12[0x18] = 0;
            puVar12[0x17] = 0;
            puVar12[0x16] = puVar12 + 0x17;
            puVar12[0x1b] = 0;
            puVar12[0x1a] = 0;
            *(undefined8 *)((long)puVar12 + 0x6c) = 0;
            puVar12[0x19] = puVar12 + 0x1a;
            puVar12[0x11] = 0;
            memcpy(auStack_60c8,&local_3098,0x1010);
            pIVar35 = param_4;
            parsedepthwiseConvLayerV2(auStack_60c8,puVar12);
            goto LAB_0024264c;
          }
          iVar8 = strcmp(pcVar14,"DilationSeparableConvolution");
          if (iVar8 == 0) {
            puVar12 = operator_new(0xe0);
            memset(puVar12,0,0xe0);
            *(undefined4 *)puVar12 = 3;
            *(undefined8 *)((long)puVar12 + 0x74) = 0x7f7fffff;
            *(undefined4 *)((long)puVar12 + 0x24) = 0;
            *(undefined8 *)((long)puVar12 + 0xc) = 0;
            *(undefined8 *)((long)puVar12 + 4) = 0;
            *(undefined8 *)((long)puVar12 + 0x1c) = 0;
            *(undefined8 *)((long)puVar12 + 0x14) = 0;
            puVar12[6] = 0x100000001;
            puVar12[5] = 0x100000001;
            *(undefined4 *)(puVar12 + 7) = 0;
            puVar12[0x15] = 0;
            puVar12[0x14] = 0;
            puVar12[0x12] = 0;
            puVar12[0x13] = puVar12 + 0x14;
            puVar12[0x18] = 0;
            puVar12[0x17] = 0;
            puVar12[0x16] = puVar12 + 0x17;
            puVar12[0x1b] = 0;
            puVar12[0x1a] = 0;
            *(undefined8 *)((long)puVar12 + 0x6c) = 0;
            puVar12[0x19] = puVar12 + 0x1a;
            puVar12[0x11] = 0;
            memcpy(auStack_70d8,&local_3098,0x1010);
            pIVar35 = param_4;
            parsedepthwiseConvLayerV3(auStack_70d8,puVar12);
            goto LAB_0024264c;
          }
          iVar8 = strcmp(pcVar14,"ConvolutionV2");
          if (iVar8 == 0) {
            puVar12 = operator_new(0xe0);
            memset(puVar12,0,0xe0);
            *(undefined4 *)puVar12 = 3;
            *(undefined8 *)((long)puVar12 + 0x74) = 0x7f7fffff;
            *(undefined4 *)((long)puVar12 + 0x24) = 0;
            *(undefined8 *)((long)puVar12 + 0xc) = 0;
            *(undefined8 *)((long)puVar12 + 4) = 0;
            *(undefined8 *)((long)puVar12 + 0x1c) = 0;
            *(undefined8 *)((long)puVar12 + 0x14) = 0;
            puVar12[6] = 0x100000001;
            puVar12[5] = 0x100000001;
            *(undefined4 *)(puVar12 + 7) = 0;
            puVar12[0x15] = 0;
            puVar12[0x14] = 0;
            puVar12[0x12] = 0;
            puVar12[0x13] = puVar12 + 0x14;
            puVar12[0x18] = 0;
            puVar12[0x17] = 0;
            puVar12[0x16] = puVar12 + 0x17;
            puVar12[0x1b] = 0;
            puVar12[0x1a] = 0;
            *(undefined8 *)((long)puVar12 + 0x6c) = 0;
            puVar12[0x19] = puVar12 + 0x1a;
            puVar12[0x11] = 0;
            memcpy(auStack_80e8,&local_3098,0x1010);
            pIVar35 = param_4;
            parseConvLayerV2(auStack_80e8,puVar12);
            goto LAB_0024264c;
          }
          iVar8 = strcmp(pcVar14,"Conv2D");
          if (iVar8 == 0) {
            puVar12 = operator_new(0xe0);
            memset(puVar12,0,0xe0);
            *(undefined4 *)puVar12 = 3;
            *(undefined4 *)((long)puVar12 + 0x74) = 0x7f7fffff;
            *(undefined4 *)(puVar12 + 0xf) = 0;
            *(undefined4 *)((long)puVar12 + 0x24) = 0;
            *(undefined8 *)((long)puVar12 + 0xc) = 0;
            *(undefined8 *)((long)puVar12 + 4) = 0;
            *(undefined8 *)((long)puVar12 + 0x1c) = 0;
            *(undefined8 *)((long)puVar12 + 0x14) = 0;
            puVar12[6] = 0x100000001;
            puVar12[5] = 0x100000001;
            *(undefined4 *)(puVar12 + 7) = 0;
            puVar12[0x15] = 0;
            puVar12[0x14] = 0;
            puVar12[0x12] = 0;
            puVar12[0x13] = puVar12 + 0x14;
            puVar12[0x18] = 0;
            puVar12[0x17] = 0;
            puVar12[0x16] = puVar12 + 0x17;
            puVar12[0x1b] = 0;
            puVar12[0x1a] = 0;
            *(undefined8 *)((long)puVar12 + 0x6c) = 0;
            puVar12[0x19] = puVar12 + 0x1a;
            puVar12[0x11] = 0;
            memcpy(auStack_90f8,&local_3098,0x1010);
            pIVar35 = param_4;
            parseConvLayerNew(auStack_90f8,puVar12);
            goto LAB_0024264c;
          }
          iVar8 = strcmp(pcVar14,"Pad2D");
          if ((iVar8 == 0) || (iVar8 = strcmp(pcVar14,"Pad2d"), iVar8 == 0)) {
            puVar12 = malloc(0x60);
            memcpy(auStack_a108,&local_3098,0x1010);
            pIVar35 = param_4;
            parsePadLayer(auStack_a108,puVar12);
            goto LAB_0024264c;
          }
          iVar8 = strcmp(pcVar14,"ConvTranspose2d");
          if (iVar8 == 0) {
            puVar12 = operator_new(0xd0);
            memset(puVar12,0,0xd0);
            *(undefined8 *)((long)puVar12 + 0x2c) = 0;
            *(undefined8 *)((long)puVar12 + 0x24) = 0;
            *(undefined8 *)((long)puVar12 + 0x1c) = 0;
            *(undefined8 *)((long)puVar12 + 0x14) = 0;
            *(undefined8 *)((long)puVar12 + 0xc) = 0;
            *(undefined8 *)((long)puVar12 + 4) = 0;
            *(undefined4 *)puVar12 = 0x43;
            *(undefined4 *)((long)puVar12 + 0x44) = 1;
            *(undefined4 *)(puVar12 + 0x11) = 0;
            *(undefined4 *)((long)puVar12 + 0x8c) = 0x7f7fffff;
            *(undefined8 *)((long)puVar12 + 0x3c) = uVar4;
            *(undefined8 *)((long)puVar12 + 0x34) = uVar3;
            puVar12[0x16] = 0;
            puVar12[0x15] = 0;
            puVar12[0x13] = 0;
            puVar12[0x14] = puVar12 + 0x15;
            puVar12[0x19] = 0;
            puVar12[0x18] = 0;
            puVar12[0x17] = puVar12 + 0x18;
            puVar12[0x12] = 0;
            memcpy(auStack_b118,&local_3098,0x1010);
            pIVar35 = param_4;
            parseConvTranspose2dV1(auStack_b118,puVar12);
            goto LAB_0024264c;
          }
          iVar8 = strcmp(pcVar14,"ConvTranspose2dV2");
          if (iVar8 == 0) {
            puVar12 = operator_new(0xd0);
            memset(puVar12,0,0xd0);
            *(undefined8 *)((long)puVar12 + 0x2c) = 0;
            *(undefined8 *)((long)puVar12 + 0x24) = 0;
            *(undefined8 *)((long)puVar12 + 0x1c) = 0;
            *(undefined8 *)((long)puVar12 + 0x14) = 0;
            *(undefined8 *)((long)puVar12 + 0xc) = 0;
            *(undefined8 *)((long)puVar12 + 4) = 0;
            *(undefined4 *)puVar12 = 0x43;
            *(undefined4 *)((long)puVar12 + 0x44) = 1;
            *(undefined4 *)(puVar12 + 0x11) = 0;
            *(undefined4 *)((long)puVar12 + 0x8c) = 0x7f7fffff;
            *(undefined8 *)((long)puVar12 + 0x3c) = uVar4;
            *(undefined8 *)((long)puVar12 + 0x34) = uVar3;
            puVar12[0x16] = 0;
            puVar12[0x15] = 0;
            puVar12[0x13] = 0;
            puVar12[0x14] = puVar12 + 0x15;
            puVar12[0x19] = 0;
            puVar12[0x18] = 0;
            puVar12[0x17] = puVar12 + 0x18;
            puVar12[0x12] = 0;
            memcpy(auStack_c128,&local_3098,0x1010);
            pIVar35 = param_4;
            parseConvTranspose2dV2(auStack_c128,puVar12);
            goto LAB_0024264c;
          }
          iVar8 = strcmp(pcVar14,"Pooling");
          if ((iVar8 != 0) && (iVar8 = strcmp(pcVar14,"PoolingDown"), iVar8 != 0)) {
            iVar8 = strcmp(pcVar14,"PoolingV2");
            if (iVar8 == 0) {
              puVar12 = malloc(0x5c);
              memcpy(auStack_e148,&local_3098,0x1010);
              pIVar35 = param_4;
              iVar8 = parsePoolingLayerV2(auStack_e148,puVar12);
              goto joined_r0x00242750;
            }
            iVar8 = strcmp(pcVar14,"Eltwise");
            if (iVar8 == 0) {
              puVar12 = malloc(0x68);
              puVar12[6] = 0;
              puVar12[7] = 0;
              puVar12[4] = 0;
              puVar12[5] = 0;
              puVar12[2] = 0;
              puVar12[3] = 0;
              memcpy(auStack_f158,&local_3098,0x1010);
              pIVar35 = param_4;
              parseEltwiseLayer(auStack_f158,puVar12);
              goto LAB_0024264c;
            }
            iVar8 = strcmp(pcVar14,"Concat");
            if (iVar8 == 0) {
              puVar12 = malloc(0x58);
              puVar12[6] = 0;
              puVar12[7] = 0;
              puVar12[4] = 0;
              puVar12[5] = 0;
              puVar12[2] = 0;
              puVar12[3] = 0;
              memcpy(auStack_10168,&local_3098,0x1010);
              pIVar35 = param_4;
              iVar8 = parseConcatLayer(auStack_10168,puVar12);
joined_r0x002428e0:
              if (iVar8 == 0) goto LAB_0024264c;
LAB_00242934:
              bVar6 = true;
              iVar7 = iVar8;
              goto joined_r0x00242940;
            }
            iVar8 = strcmp(pcVar14,"Concat2");
            if (iVar8 == 0) {
              puVar12 = malloc(0x58);
              puVar12[6] = 0;
              puVar12[7] = 0;
              puVar12[4] = 0;
              puVar12[5] = 0;
              puVar12[2] = 0;
              puVar12[3] = 0;
              memcpy(auStack_11178,&local_3098,0x1010);
              pIVar35 = param_4;
              iVar8 = parseConcatV2Layer(auStack_11178,puVar12);
              goto joined_r0x002428e0;
            }
            iVar8 = strcmp(pcVar14,"Upsample");
            if (iVar8 == 0) {
              puVar12 = malloc(0x54);
              memcpy(auStack_12188,&local_3098,0x1010);
              pIVar35 = param_4;
              parseUpsampleLayer(auStack_12188,puVar12);
              goto LAB_0024264c;
            }
            iVar8 = strcmp(pcVar14,"UpSampling");
            if (iVar8 == 0) {
              puVar12 = malloc(0x54);
              memcpy(auStack_13198,&local_3098,0x1010);
              pIVar35 = param_4;
              parseUpsamplingLayer(auStack_13198,puVar12);
              goto LAB_0024264c;
            }
            iVar8 = strcmp(pcVar14,"Resize");
            if (iVar8 == 0) {
              puVar12 = malloc(0x54);
              memcpy(auStack_141a8,&local_3098,0x1010);
              pIVar35 = param_4;
              parseResizeLayer(auStack_141a8,puVar12);
              goto LAB_0024264c;
            }
            iVar8 = strcmp(pcVar14,"Relu");
            if (iVar8 != 0) {
              iVar8 = strcmp(pcVar14,"Tanh");
              if (iVar8 == 0) {
                puVar12 = malloc(0x34);
                memcpy(auStack_161c8,&local_3098,0x1010);
                pIVar35 = param_4;
                parseTanhLayer(auStack_161c8,puVar12);
                goto LAB_002430a0;
              }
              iVar8 = strcmp(pcVar14,"Sigmoid");
              if (iVar8 == 0) {
                puVar12 = malloc(0x34);
                memcpy(auStack_171d8,&local_3098,0x1010);
                pIVar35 = param_4;
                parseSigmoidLayer(auStack_171d8,puVar12);
                goto LAB_002430a0;
              }
              iVar8 = strcmp(pcVar14,"Depth2Space");
              if (iVar8 == 0) {
                puVar12 = malloc(0x38);
                memcpy(auStack_181e8,&local_3098,0x1010);
                pIVar35 = param_4;
                parseDepth2SpaceLayer(auStack_181e8,puVar12);
                goto LAB_002430a0;
              }
              iVar8 = strcmp(pcVar14,"Space2Depth");
              if (iVar8 == 0) {
                puVar12 = malloc(0x38);
                memcpy(auStack_191f8,&local_3098,0x1010);
                pIVar35 = param_4;
                parseSpace2DepthLayer(auStack_191f8,puVar12);
                goto LAB_002430a0;
              }
              iVar8 = strcmp(pcVar14,"Softmax");
              if (iVar8 == 0) {
                puVar12 = malloc(0x34);
                memcpy(auStack_1a208,&local_3098,0x1010);
                pIVar35 = param_4;
                parseSoftmaxLayer(auStack_1a208,puVar12);
                goto LAB_002430a0;
              }
              iVar8 = strcmp(pcVar14,"InstanceNorm");
              if (iVar8 == 0) {
                puVar12 = operator_new(0x90);
                memset(puVar12,0,0x90);
                *(undefined4 *)puVar12 = 500;
                puVar12[0xb] = 0;
                puVar12[10] = 0;
                puVar12[8] = 0;
                puVar12[9] = puVar12 + 10;
                puVar12[0xe] = 0;
                puVar12[0xd] = 0;
                puVar12[0xf] = 0x3f800000;
                puVar12[0x11] = 0x7f7fffff00000000;
                puVar12[0xc] = puVar12 + 0xd;
                puVar12[7] = 0;
                memcpy(auStack_1b218,&local_3098,0x1010);
                pIVar35 = param_4;
                parseInstancenormLayer(auStack_1b218,puVar12);
                goto LAB_002430a0;
              }
              iVar8 = strcmp(pcVar14,"Slice");
              if (iVar8 == 0) {
                puVar12 = malloc(0x60);
                puVar12[9] = 0;
                puVar12[10] = 0;
                puVar12[7] = 0;
                puVar12[8] = 0;
                puVar12[5] = 0;
                puVar12[6] = 0;
                memcpy(auStack_1c228,&local_3098,0x1010);
                pIVar35 = param_4;
                parseSplitLayer(auStack_1c228,puVar12);
                goto LAB_002430a0;
              }
              iVar8 = strcmp(pcVar14,"Crop");
              if (iVar8 == 0) {
                puVar12 = malloc(0x4c);
                memcpy(auStack_1d238,&local_3098,0x1010);
                pIVar35 = param_4;
                parseCropLayer(auStack_1d238,puVar12);
                goto LAB_002430a0;
              }
              iVar8 = strcmp(pcVar14,"SEScale");
              if (iVar8 == 0) {
                puVar12 = malloc(0x60);
                puVar12[5] = 0;
                puVar12[6] = 0;
                puVar12[3] = 0;
                puVar12[4] = 0;
                puVar12[1] = 0;
                puVar12[2] = 0;
                memcpy(auStack_1e248,&local_3098,0x1010);
                pIVar35 = param_4;
                parseSEScaleLayer(auStack_1e248,puVar12);
                goto LAB_002430a0;
              }
              iVar8 = strcmp(pcVar14,"BatchNormalization");
              if (iVar8 == 0) {
                puVar12 = operator_new(0xd8);
                memset(puVar12,0,0xd8);
                *puVar12 = 4;
                *(undefined4 *)(puVar12 + 1) = 0;
                puVar12[8] = 0;
                puVar12[7] = 0;
                puVar12[6] = puVar12 + 7;
                puVar12[0xb] = 0;
                puVar12[10] = 0;
                puVar12[9] = puVar12 + 10;
                puVar12[0xe] = 0;
                puVar12[0xd] = 0;
                puVar12[0xc] = puVar12 + 0xd;
                puVar12[0x11] = 0;
                puVar12[0x10] = 0;
                puVar12[0x12] = 0x3f800000;
                *(undefined4 *)(puVar12 + 0x14) = 0;
                *(undefined4 *)((long)puVar12 + 0xa4) = 0x7f7fffff;
                puVar12[0xf] = puVar12 + 0x10;
                puVar12[3] = 0;
                puVar12[2] = 0;
                puVar12[5] = 0;
                puVar12[4] = 0;
                memcpy(auStack_1f258,&local_3098,0x1010);
                pIVar35 = param_4;
                parseBatchNormLayer(auStack_1f258,puVar12);
                goto LAB_002430a0;
              }
              iVar8 = strcmp(pcVar14,"Reshape");
              if (iVar8 == 0) {
                puVar12 = malloc(0x34);
                memcpy(auStack_20268,&local_3098,0x1010);
                pIVar35 = param_4;
                parseReshapeLayer(auStack_20268,puVar12);
                goto LAB_002430a0;
              }
              iVar8 = strcmp(pcVar14,"OnnxOp1");
              if (iVar8 != 0) {
                iVar8 = strcmp(pcVar14,"InnerProduct");
                if (iVar8 == 0) {
                  puVar12 = operator_new(0x90);
                  memset(puVar12,0,0x50);
                  *(undefined4 *)puVar12 = 9;
                  *(undefined4 *)(puVar12 + 8) = 0x7f7fffff;
                  *(undefined4 *)((long)puVar12 + 0x44) = 0;
                  puVar12[0xe] = 0;
                  puVar12[0xd] = 0;
                  puVar12[0x11] = 0;
                  puVar12[0xb] = 0;
                  puVar12[0xc] = puVar12 + 0xd;
                  puVar12[0x10] = 0;
                  puVar12[0xf] = puVar12 + 0x10;
                  puVar12[10] = 0;
                  memcpy(auStack_262c8,&local_3098,0x1010);
                  pIVar35 = param_4;
                  parseInnerproductLayer(auStack_262c8,puVar12);
                  goto LAB_002430a0;
                }
                iVar8 = strcmp(pcVar14,"Shuffle");
                if (iVar8 == 0) {
                  puVar12 = malloc(0x3c);
                  memcpy(auStack_272d8,&local_3098,0x1010);
                  pIVar35 = param_4;
                  parseShuffleLayer(auStack_272d8,puVar12);
                  goto LAB_002430a0;
                }
                iVar8 = strcmp(pcVar14,"ShuffleNet");
                if (iVar8 == 0) {
                  puVar12 = malloc(0x70);
                  memset(puVar12 + 2,0,0x60);
                  memcpy(auStack_282e8,&local_3098,0x1010);
                  pIVar35 = param_4;
                  parseShuffleNetLayer(auStack_282e8,puVar12);
                  goto LAB_002430a0;
                }
                iVar8 = strcmp(pcVar14,"OnnxOp2");
                if (iVar8 == 0) {
                  puVar12 = malloc(0x68);
                  puVar12[6] = 0;
                  puVar12[7] = 0;
                  puVar12[4] = 0;
                  puVar12[5] = 0;
                  puVar12[2] = 0;
                  puVar12[3] = 0;
                  memcpy(auStack_292f8,&local_3098,0x1010);
                  pIVar35 = param_4;
                  iVar8 = parseOnnxOP2Layer(auStack_292f8,puVar12);
                  if (iVar8 == 0) goto LAB_00243434;
                  goto LAB_00242934;
                }
                iVar8 = strcmp(pcVar14,"Constant");
                if (iVar8 == 0) {
                  puVar12 = operator_new(0x60);
                  memset(puVar12,0,0x60);
                  *(undefined4 *)puVar12 = 0x1fe;
                  puVar12[10] = 0;
                  puVar12[9] = 0;
                  puVar12[7] = 0;
                  puVar12[8] = puVar12 + 9;
                  *(undefined4 *)(puVar12 + 0xb) = 0;
                  memcpy(auStack_2a308,&local_3098,0x1010);
                  pIVar35 = param_4;
                  iVar8 = parseConstantLayer(auStack_2a308,puVar12);
joined_r0x0024332c:
                  if (iVar8 != 0) goto LAB_00242934;
LAB_00243434:
                  puVar17 = *(undefined8 **)(param_4 + 0x3d0);
                  uStack_21278 = puVar12;
                  if (puVar17 < *(undefined8 **)(param_4 + 0x3d8)) goto LAB_002430b4;
                  goto LAB_00243448;
                }
                iVar8 = strcmp(pcVar14,"GridSample");
                if (iVar8 == 0) {
                  puVar12 = malloc(0x60);
                  puVar12[5] = 0;
                  puVar12[6] = 0;
                  puVar12[3] = 0;
                  puVar12[4] = 0;
                  puVar12[1] = 0;
                  puVar12[2] = 0;
                  memcpy(auStack_2b318,&local_3098,0x1010);
                  pIVar35 = param_4;
                  iVar8 = parseGridSampleLayer(auStack_2b318,puVar12);
                  goto joined_r0x0024332c;
                }
                iVar8 = strcmp(pcVar14,"Transpose");
                if (iVar8 == 0) {
                  puVar12 = malloc(0x38);
                  memcpy(auStack_2c328,&local_3098,0x1010);
                  pIVar35 = param_4;
                  iVar8 = parseTransposeLayer(auStack_2c328,puVar12);
                  goto joined_r0x0024332c;
                }
                iVar8 = strcmp(pcVar14,"HardSwish");
                if (iVar8 == 0) {
                  puVar12 = malloc(0x34);
                  memcpy(auStack_2d338,&local_3098,0x1010);
                  pIVar35 = param_4;
                  iVar8 = parseHardSwishLayer(auStack_2d338,puVar12);
                  goto joined_r0x0024332c;
                }
                iVar8 = strcmp(pcVar14,"HardSigmoid");
                if (iVar8 == 0) {
                  puVar12 = malloc(0x34);
                  memcpy(auStack_2e348,&local_3098,0x1010);
                  pIVar35 = param_4;
                  iVar8 = parseHardSigmoidLayer(auStack_2e348,puVar12);
                  goto joined_r0x0024332c;
                }
                iVar8 = strcmp(pcVar14,"ReluHardSwish");
                if (iVar8 == 0) {
                  puVar12 = malloc(0x34);
                  memcpy(auStack_2f358,&local_3098,0x1010);
                  pIVar35 = param_4;
                  iVar8 = parseReluHardSwishLayer(auStack_2f358,puVar12);
                  goto joined_r0x0024332c;
                }
                iVar8 = strcmp(pcVar14,"GlobalPow2Pool");
                if (iVar8 == 0) {
                  puVar12 = malloc(0x5c);
                  memcpy(auStack_30368,&local_3098,0x1010);
                  pIVar35 = param_4;
                  iVar8 = parseGlobalPow2PoolingLayer(auStack_30368,puVar12);
                  if (iVar8 != 0) goto LAB_00242754;
                  goto LAB_00243434;
                }
                iVar8 = strcmp(pcVar14,"SparseConvolution");
                if (iVar8 == 0) {
                  puVar12 = operator_new(0xe0);
                  *(undefined4 *)puVar12 = 0x206;
                  *(undefined4 *)((long)puVar12 + 0x24) = 0;
                  *(undefined8 *)((long)puVar12 + 0xc) = 0;
                  *(undefined8 *)((long)puVar12 + 4) = 0;
                  *(undefined8 *)((long)puVar12 + 0x1c) = 0;
                  *(undefined8 *)((long)puVar12 + 0x14) = 0;
                  puVar12[6] = 0x100000001;
                  puVar12[5] = 0x100000001;
                  *(undefined4 *)(puVar12 + 7) = 0;
                  memset((undefined4 *)((long)puVar12 + 0x94),0,0x4c);
                  *(undefined8 *)((long)puVar12 + 0x84) = 0;
                  *(undefined8 *)((long)puVar12 + 0x7c) = 0;
                  *(undefined8 *)((long)puVar12 + 0x74) = 0;
                  *(undefined8 *)((long)puVar12 + 0x6c) = 0;
                  *(undefined4 *)((long)puVar12 + 0x8c) = 0;
                  *(undefined4 *)(puVar12 + 0x12) = 0x7f7fffff;
                  puVar12[0x13] = 0;
                  memcpy(auStack_31378,&local_3098,0x1010);
                  pIVar35 = param_4;
                  iVar8 = parseSparseConvLayerV1(auStack_31378,puVar12);
                  if (iVar8 != 0) goto LAB_00242934;
                  param_4[0x24] = (IESNet)0x1;
                  goto LAB_00243434;
                }
                goto LAB_00242754;
              }
              memcpy(&uStack_21278,&local_3098,0x1010);
              StringParser::NextString();
              StringParser::NextString();
              if (((ulong)puStack_313f0 & 1) == 0) {
                puStack_313f0 = (undefined8 *)((ulong)puStack_313f0 & 0xffffffffffff0000);
              }
              else {
                *pcStack_313e0 = '\0';
                pIStack_313e8 = (IESNet *)0x0;
              }
              std::__ndk1::
              basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>::
              reserve((basic_string<char,std::__ndk1::char_traits<char>,std::__ndk1::allocator<char>>
                       *)&puStack_313f0,0);
              pIStack_313e8 = pIStack_31400;
              puStack_313f0 = puStack_31408;
              pcStack_313e0 = pcStack_313f8;
              pcVar14 = (char *)((ulong)&puStack_313f0 | 1);
              if (((ulong)puStack_31408 & 1) != 0) {
                pcVar14 = pcStack_313f8;
              }
              iVar8 = strcmp(pcVar14,"LeakyRelu");
              if (iVar8 == 0) {
                puVar12 = operator_new(0x60);
                memset(puVar12,0,0x60);
                *puVar12 = 0x13;
                *(undefined4 *)(puVar12 + 1) = 0;
                puVar12[4] = 0;
                puVar12[3] = 0;
                puVar12[2] = puVar12 + 3;
                *(undefined4 *)(puVar12 + 5) = 0x7f7fffff;
                memcpy(auStack_22288,&local_3098,0x1010);
                pIVar35 = param_4;
                parseLeakyReluLayer(auStack_22288,puVar12);
LAB_002431fc:
                puVar17 = *(undefined8 **)(param_4 + 0x3d0);
                puStack_31408 = puVar12;
                if (*(undefined8 **)(param_4 + 0x3d8) <= puVar17) goto LAB_00243164;
LAB_00243210:
                *puVar17 = puVar12;
                *(long *)(param_4 + 0x3d0) = *(long *)(param_4 + 0x3d0) + 8;
                puStack_31408 = puVar12;
LAB_00243224:
                bVar6 = false;
              }
              else {
                iVar8 = strcmp(pcVar14,"Clip");
                if (iVar8 == 0) {
                  puVar12 = operator_new(0x70);
                  memset(puVar12,0,0x70);
                  *puVar12 = 0xff7fffff00000205;
                  *(undefined4 *)(puVar12 + 1) = 0x7f7fffff;
                  puVar12[4] = 0;
                  puVar12[3] = 0;
                  puVar12[7] = 0;
                  puVar12[2] = puVar12 + 3;
                  puVar12[6] = 0;
                  puVar12[5] = puVar12 + 6;
                  memcpy(auStack_23298,&local_3098,0x1010);
                  pIVar35 = param_4;
                  iVar8 = parseOnnxOP1ClipLayer(auStack_23298,puVar12);
                }
                else {
                  iVar8 = strcmp(pcVar14,"Reshape");
                  if (iVar8 == 0) {
                    puVar12 = malloc(0x34);
                    memcpy(auStack_242a8,&local_3098,0x1010);
                    pIVar35 = param_4;
                    parseOnnxOP1ReshapeLayer(auStack_242a8,puVar12);
                    goto LAB_002431fc;
                  }
                  puVar12 = operator_new(0x80);
                  memset(puVar12,0,0x80);
                  *(undefined4 *)puVar12 = 0x200;
                  puVar12[8] = 0;
                  puVar12[9] = 0;
                  puVar12[7] = 0;
                  memcpy(auStack_252b8,&local_3098,0x1010);
                  pIVar35 = param_4;
                  iVar8 = parseOnnxOP1Layer(auStack_252b8,puVar12);
                }
                if (iVar8 == 0) {
                  puVar17 = *(undefined8 **)(param_4 + 0x3d0);
                  puStack_31408 = puVar12;
                  if (puVar17 < *(undefined8 **)(param_4 + 0x3d8)) goto LAB_00243210;
LAB_00243164:
                  std::__ndk1::vector<void*,std::__ndk1::allocator<void*>>::
                  __push_back_slow_path<void*>
                            ((vector<void*,std::__ndk1::allocator<void*>> *)(param_4 + 0x3c8),
                             &puStack_31408);
                  goto LAB_00243224;
                }
                bVar6 = true;
                iVar7 = iVar8;
              }
              if (((ulong)puStack_313f0 & 1) != 0) {
                operator_delete(pcStack_313e0);
              }
              if (!bVar6) goto LAB_00243458;
              goto LAB_00242758;
            }
            puVar12 = operator_new(0x60);
            memset(puVar12,0,0x60);
            *puVar12 = 0x13;
            *(undefined4 *)(puVar12 + 1) = 0;
            puVar12[4] = 0;
            puVar12[3] = 0;
            puVar12[2] = puVar12 + 3;
            *(undefined4 *)(puVar12 + 5) = 0x7f7fffff;
            memcpy(auStack_151b8,&local_3098,0x1010);
            pIVar35 = param_4;
            parseReluLayer(auStack_151b8,puVar12);
LAB_002430a0:
            puVar17 = *(undefined8 **)(param_4 + 0x3d0);
            uStack_21278 = puVar12;
            if (*(undefined8 **)(param_4 + 0x3d8) <= puVar17) goto LAB_00243448;
LAB_002430b4:
            *puVar17 = puVar12;
            *(long *)(param_4 + 0x3d0) = *(long *)(param_4 + 0x3d0) + 8;
            uStack_21278 = puVar12;
            goto LAB_00243458;
          }
          puVar12 = malloc(0x5c);
          memcpy(auStack_d138,&local_3098,0x1010);
          pIVar35 = param_4;
          iVar8 = parsePoolingLayerV1(auStack_d138,puVar12);
joined_r0x00242750:
          if (iVar8 == 0) goto LAB_0024264c;
LAB_00242754:
          iVar7 = -2;
LAB_00242758:
          bVar6 = true;
        }
joined_r0x00242940:
        if (((ulong)apfStack_313d8[0] & 1) != 0) {
          operator_delete(pcStack_313c8);
        }
        if (((ulong)puStack_313c0 & 1) != 0) {
          operator_delete(puStack_313b0);
        }
        if (bVar6) goto joined_r0x002436f4;
        iVar8 = (int)pIVar35;
        iVar24 = iVar24 + 1;
      } while (iVar24 < iStack_31454);
    }
    *(int *)(param_4 + 4) = iStack_31454 + 1;
    mergeNet(param_4);
    ppiVar13 = (int **)inferNetShape(param_4,param_2,iVar8);
    if ((int)ppiVar13 == 0) {
      iVar7 = readEspressoweight(ppiVar13,param_2,param_4,iVar16);
      if (iVar7 == 0) {
        setNetTensorIDbyTensorName(param_4);
        *(float *)(param_4 + 0x390) = fVar33;
        if (0 < (int)fVar33) {
          lVar31 = 0;
          do {
            puVar19 = *(ulong **)(param_4 + 0x368);
            lVar21 = **(long **)(param_4 + 0x398);
            lVar27 = lVar21 + lVar31;
            if (puVar19 == *(ulong **)(param_4 + 0x370)) {
              FUN_001416f8(param_4 + 0x360,lVar27 + 0x18);
            }
            else {
              puVar19[1] = 0;
              puVar19[2] = 0;
              *puVar19 = 0;
              if ((*(byte *)(lVar27 + 0x18) & 1) == 0) {
                puVar19[2] = *(ulong *)(lVar27 + 0x28);
                uVar30 = *(ulong *)(lVar27 + 0x18);
                puVar19[1] = *(ulong *)(lVar27 + 0x20);
                *puVar19 = uVar30;
              }
              else {
                lVar21 = lVar21 + lVar31;
                uVar30 = *(ulong *)(lVar21 + 0x20);
                pvVar11 = *(void **)(lVar21 + 0x28);
                if (uVar30 < 0x17) {
                  pvVar32 = (void *)((long)puVar19 + 1);
                  *(char *)puVar19 = (char)(uVar30 << 1);
                  if (uVar30 != 0) goto LAB_00243610;
                }
                else {
                  uVar37 = uVar30 + 0x10 & 0xfffffffffffffff0;
                  pvVar32 = operator_new(uVar37);
                  puVar19[1] = uVar30;
                  puVar19[2] = (ulong)pvVar32;
                  *puVar19 = uVar37 | 1;
LAB_00243610:
                  memcpy(pvVar32,pvVar11,uVar30);
                }
                *(undefined1 *)((long)pvVar32 + uVar30) = 0;
              }
              *(long *)(param_4 + 0x368) = *(long *)(param_4 + 0x368) + 0x18;
            }
            puVar22 = *(undefined4 **)(param_4 + 0x308);
            lVar27 = **(long **)(param_4 + 0x398);
            if (puVar22 == *(undefined4 **)(param_4 + 0x310)) {
              FUN_0015fa48(param_4 + 0x300,lVar27 + lVar31 + 0x10);
              lVar27 = **(long **)(param_4 + 0x398);
            }
            else {
              *puVar22 = *(undefined4 *)(lVar27 + lVar31 + 0x10);
              *(undefined4 **)(param_4 + 0x308) = puVar22 + 1;
            }
            lVar27 = lVar27 + lVar31;
            if ((*(byte *)(lVar27 + 0x18) & 1) == 0) {
              pcVar14 = (char *)(lVar27 + 0x19);
            }
            else {
              pcVar14 = *(char **)(lVar27 + 0x28);
            }
            sVar15 = strlen(pcVar14);
            local_3098 = (ulong *)CONCAT44(local_3098._4_4_,(int)sVar15);
            puVar22 = *(undefined4 **)(param_4 + 0x338);
            if (puVar22 < *(undefined4 **)(param_4 + 0x340)) {
              *puVar22 = (int)sVar15;
              *(undefined4 **)(param_4 + 0x338) = puVar22 + 1;
            }
            else {
              FUN_001776e8(param_4 + 0x330,&local_3098);
            }
            uVar34 = uVar34 - 1;
            lVar31 = lVar31 + 0x30;
          } while (uVar34 != 0);
        }
        getIESNetOutNode(param_4);
        *(undefined8 *)(param_4 + 0x14) = 0;
        iVar7 = 0;
        *(int *)(param_4 + 0x20) = (int)param_3;
      }
    }
    else {
      iVar7 = -2;
    }
  }
joined_r0x002436f4:
  if ((abStack_313a8[0] & 1) != 0) {
    operator_delete(pcStack_31398);
  }
  if ((abStack_31390[0] & 1) != 0) {
    operator_delete(pbStack_31380);
  }
  if (*(long *)(lVar2 + 0x28) != local_68) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  return iVar7;
}



/* ===== Function: NextString @ 002438f8  size=332  depth=0
   signature: undefined __cdecl NextString(void) ===== */

/* IESNN::StringParser::NextString() */

void IESNN::StringParser::NextString(void)

{
  char *__s;
  byte bVar1;
  byte *pbVar2;
  long *in_x0;
  size_t __n;
  ulong *in_x8;
  ulong uVar3;
  long lVar4;
  ulong uVar5;
  ulong uVar6;
  void *__dest;
  
  pbVar2 = (byte *)*in_x0;
LAB_00243934:
  uVar3 = (ulong)*pbVar2;
  switch(uVar3) {
  case 0:
    uVar6 = 0;
    break;
  case 10:
  case 0xd:
  case 0x20:
    goto switchD_00243948_caseD_a;
  default:
    if (*pbVar2 == 0x5c) goto switchD_00243948_caseD_a;
  case 1:
  case 2:
  case 3:
  case 4:
  case 5:
  case 6:
  case 7:
  case 8:
  case 9:
  case 0xb:
  case 0xc:
  case 0xe:
  case 0xf:
  case 0x10:
  case 0x11:
  case 0x12:
  case 0x13:
  case 0x14:
  case 0x15:
  case 0x16:
  case 0x17:
  case 0x18:
  case 0x19:
  case 0x1a:
  case 0x1b:
  case 0x1c:
  case 0x1d:
  case 0x1e:
  case 0x1f:
    uVar5 = 0;
    goto LAB_00243968;
  }
LAB_002439cc:
  __s = (char *)((long)in_x0 + 0xc);
  __s[(int)uVar6] = '\0';
  in_x8[1] = 0;
  in_x8[2] = 0;
  *in_x8 = 0;
  __n = strlen(__s);
  if (__n < 0x17) {
    __dest = (void *)((long)in_x8 + 1);
    *(char *)in_x8 = (char)(__n << 1);
    if (__n == 0) goto LAB_00243a30;
  }
  else {
    uVar3 = __n + 0x10 & 0xfffffffffffffff0;
    __dest = operator_new(uVar3);
    in_x8[1] = __n;
    in_x8[2] = (ulong)__dest;
    *in_x8 = uVar3 | 1;
  }
  memcpy(__dest,__s,__n);
LAB_00243a30:
  *(undefined1 *)((long)__dest + __n) = 0;
  return;
switchD_00243948_caseD_a:
  *in_x0 = (long)(pbVar2 + 1);
  *(int *)(in_x0 + 1) = (int)in_x0[1] + 1;
  pbVar2 = pbVar2 + 1;
  goto LAB_00243934;
LAB_00243968:
  if ((((uint)uVar3 < 0x21) && ((1L << (uVar3 & 0x3f) & 0x100002400U) != 0)) ||
     ((uint)uVar3 == 0x5c)) {
    uVar6 = uVar5 & 0xffffffff;
    goto LAB_002439cc;
  }
  *(char *)((long)in_x0 + uVar5 + 0xc) = (char)uVar3;
  lVar4 = *in_x0;
  uVar6 = uVar5 + 1;
  *in_x0 = lVar4 + 1;
  *(int *)(in_x0 + 1) = (int)in_x0[1] + 1;
  if ((0xffd < (long)uVar5) ||
     (bVar1 = *(byte *)(lVar4 + 1), uVar3 = (ulong)bVar1, uVar5 = uVar6, bVar1 == 0))
  goto LAB_002439cc;
  goto LAB_00243968;
}



/* ===== Function: CreateNet @ 0020c99c  size=304  depth=0
   signature: undefined __cdecl CreateNet(basic_string * param_1, uchar * param_2, ulong param_3) ===== */

/* IESNN::Net::CreateNet(std::__ndk1::basic_string<char, std::__ndk1::char_traits<char>,
   std::__ndk1::allocator<char> > const&, unsigned char const*, unsigned long) */

Net * IESNN::Net::CreateNet(basic_string *param_1,uchar *param_2,ulong param_3)

{
  int iVar1;
  IESNet *__s;
  Net *this;
  
  __s = operator_new(0x430);
  memset(__s,0,0x430);
  *(undefined4 *)(__s + 8) = 0;
  *(undefined4 *)(__s + 0x2e8) = 0;
  *(undefined4 *)__s = 1;
  memset(__s + 0x300,0,0x90);
  *(undefined8 *)(__s + 0x1c) = 0;
  *(undefined8 *)(__s + 0x14) = 0;
  __s[0x24] = (IESNet)0x0;
  memset(__s + 0x28,0,0x20b);
  memset(__s + 0x238,0,0xaf);
  *(undefined8 *)(__s + 0x3a8) = 0;
  *(undefined8 *)(__s + 0x390) = 0x100000001;
  *(undefined8 *)(__s + 0x3a0) = 0;
  *(undefined8 *)(__s + 0x398) = 0;
  *(undefined8 *)(__s + 0x3c0) = 0;
  *(undefined8 *)(__s + 0x3b8) = 0;
  *(IESNet **)(__s + 0x3b0) = __s + 0x3b8;
  *(undefined8 *)(__s + 0x428) = 0;
  *(undefined8 *)(__s + 0x420) = 0;
  memset(__s + 0x3c8,0,0x49);
  *(IESNet **)(__s + 0x418) = __s + 0x420;
  this = operator_new(0x38);
  *(IESNet **)this = __s;
  *(undefined8 *)(this + 0x30) = 0;
  *(undefined8 *)(this + 0x28) = 0;
  *(undefined8 *)(this + 0x20) = 0;
  *(undefined8 *)(this + 0x18) = 0;
  *(undefined8 *)(this + 0x10) = 0;
  *(undefined8 *)(this + 8) = 0;
  iVar1 = parseNet(param_1,param_2,param_3,__s);
  if (iVar1 != 0) {
    ~Net(this);
    operator_delete(this);
    this = (Net *)0x0;
  }
  return this;
}



/* ===== Function: FUN_001416f8 @ 001416f8  size=336  depth=1
   signature: undefined FUN_001416f8() ===== */

void FUN_001416f8(long *param_1,undefined8 param_2)

{
  ulong uVar1;
  byte bVar2;
  byte *pbVar3;
  void *pvVar4;
  long lVar5;
  byte *pbVar6;
  long lVar7;
  ulong uVar8;
  byte *pbVar9;
  void *pvVar10;
  byte *pbVar11;
  ulong uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  
  lVar5 = param_1[1] - *param_1 >> 3;
  uVar12 = 0xaaaaaaaaaaaaaaa;
  uVar1 = lVar5 * -0x5555555555555555 + 1;
  if (0xaaaaaaaaaaaaaaa < uVar1) {
                    /* WARNING: Subroutine does not return */
    abort();
  }
  lVar7 = param_1[2] - *param_1 >> 3;
  if ((ulong)(lVar7 * -0x5555555555555555) < 0x555555555555555) {
    uVar8 = lVar7 * 0x5555555555555556;
    uVar12 = uVar1;
    if (uVar1 <= uVar8) {
      uVar12 = uVar8;
    }
    if (uVar12 == 0) {
      pvVar4 = (void *)0x0;
      goto LAB_0014177c;
    }
  }
  pvVar4 = operator_new(uVar12 * 0x18);
LAB_0014177c:
  pvVar10 = (void *)((long)pvVar4 + lVar5 * 8);
  FUN_0013325c(pvVar10,param_2);
  pbVar6 = (byte *)*param_1;
  pbVar9 = (byte *)param_1[1];
  lVar5 = (long)pvVar10 + 0x18;
  pbVar11 = pbVar6;
  if (pbVar9 != pbVar6) {
    do {
      uVar14 = *(undefined8 *)(pbVar9 + -0x10);
      uVar13 = *(undefined8 *)(pbVar9 + -0x18);
      *(undefined8 *)((long)pvVar10 + -8) = *(undefined8 *)(pbVar9 + -8);
      *(undefined8 *)((long)pvVar10 + -0x10) = uVar14;
      *(undefined8 *)((long)pvVar10 + -0x18) = uVar13;
      pbVar9[-0x10] = 0;
      pbVar9[-0xf] = 0;
      pbVar9[-0xe] = 0;
      pbVar9[-0xd] = 0;
      pbVar9[-0xc] = 0;
      pbVar9[-0xb] = 0;
      pbVar9[-10] = 0;
      pbVar9[-9] = 0;
      pbVar9[-8] = 0;
      pbVar9[-7] = 0;
      pbVar9[-6] = 0;
      pbVar9[-5] = 0;
      pbVar9[-4] = 0;
      pbVar9[-3] = 0;
      pbVar9[-2] = 0;
      pbVar9[-1] = 0;
      pbVar9[-0x18] = 0;
      pbVar9[-0x17] = 0;
      pbVar9[-0x16] = 0;
      pbVar9[-0x15] = 0;
      pbVar9[-0x14] = 0;
      pbVar9[-0x13] = 0;
      pbVar9[-0x12] = 0;
      pbVar9[-0x11] = 0;
      pbVar9 = pbVar9 + -0x18;
      pvVar10 = (void *)((long)pvVar10 + -0x18);
    } while (pbVar6 != pbVar9);
    pbVar6 = (byte *)param_1[1];
    pbVar11 = (byte *)*param_1;
  }
  *param_1 = (long)pvVar10;
  param_1[1] = lVar5;
  param_1[2] = (long)((long)pvVar4 + uVar12 * 0x18);
  if (pbVar6 != pbVar11) {
    bVar2 = pbVar6[-0x18];
    pbVar9 = pbVar6 + -0x18;
    while( true ) {
      pbVar3 = pbVar9;
      if ((bVar2 & 1) != 0) {
        operator_delete(*(void **)(pbVar6 + -8));
      }
      if (pbVar11 == pbVar3) break;
      bVar2 = pbVar3[-0x18];
      pbVar9 = pbVar3 + -0x18;
      pbVar6 = pbVar3;
    }
  }
  if (pbVar11 != (byte *)0x0) {
    operator_delete(pbVar11);
    return;
  }
  return;
}



/* ===== Function: FUN_0015fa48 @ 0015fa48  size=276  depth=1
   signature: undefined FUN_0015fa48() ===== */

void FUN_0015fa48(long *param_1,undefined4 *param_2)

{
  long lVar1;
  long lVar2;
  ulong uVar3;
  long lVar4;
  ulong uVar5;
  void *local_70;
  undefined4 *local_68;
  undefined4 *local_60;
  void *local_58;
  long *plStack_50;
  long local_48;
  
  lVar1 = tpidr_el0;
  local_48 = *(long *)(lVar1 + 0x28);
  lVar4 = param_1[1] - *param_1 >> 2;
  uVar5 = lVar4 + 1;
  if (uVar5 >> 0x3e != 0) {
LAB_0015fac0:
                    /* WARNING: Subroutine does not return */
    abort();
  }
  lVar2 = param_1[2] - *param_1;
  plStack_50 = param_1 + 2;
  if ((ulong)(lVar2 >> 2) < 0x1fffffffffffffff) {
    uVar3 = lVar2 >> 1;
    if (uVar5 <= uVar3) {
      uVar5 = uVar3;
    }
    local_58 = (void *)0x0;
    if (uVar5 == 0) {
      local_70 = (void *)0x0;
      goto LAB_0015fad4;
    }
    if (uVar5 >> 0x3e != 0) goto LAB_0015fac0;
  }
  else {
    uVar5 = 0x3fffffffffffffff;
  }
  local_58 = (void *)0x0;
  local_70 = operator_new(uVar5 << 2);
LAB_0015fad4:
  local_68 = (undefined4 *)((long)local_70 + lVar4 * 4);
  local_58 = (void *)((long)local_70 + uVar5 * 4);
  local_60 = local_68 + 1;
  *local_68 = *param_2;
  FUN_0015fb5c(param_1,&local_70);
  if ((long)local_60 - (long)local_68 != 0) {
    local_60 = local_60 + (((long)local_60 - (long)local_68) - 4U >> 2 ^ 0xffffffffffffffff);
  }
  if (local_70 != (void *)0x0) {
    operator_delete(local_70);
  }
  if (*(long *)(lVar1 + 0x28) == local_48) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ===== Function: FUN_001776e8 @ 001776e8  size=276  depth=1
   signature: undefined FUN_001776e8() ===== */

void FUN_001776e8(long *param_1,undefined4 *param_2)

{
  long lVar1;
  long lVar2;
  ulong uVar3;
  long lVar4;
  ulong uVar5;
  void *local_70;
  undefined4 *local_68;
  undefined4 *local_60;
  void *local_58;
  long *plStack_50;
  long local_48;
  
  lVar1 = tpidr_el0;
  local_48 = *(long *)(lVar1 + 0x28);
  lVar4 = param_1[1] - *param_1 >> 2;
  uVar5 = lVar4 + 1;
  if (uVar5 >> 0x3e != 0) {
LAB_00177760:
                    /* WARNING: Subroutine does not return */
    abort();
  }
  lVar2 = param_1[2] - *param_1;
  plStack_50 = param_1 + 2;
  if ((ulong)(lVar2 >> 2) < 0x1fffffffffffffff) {
    uVar3 = lVar2 >> 1;
    if (uVar5 <= uVar3) {
      uVar5 = uVar3;
    }
    local_58 = (void *)0x0;
    if (uVar5 == 0) {
      local_70 = (void *)0x0;
      goto LAB_00177774;
    }
    if (uVar5 >> 0x3e != 0) goto LAB_00177760;
  }
  else {
    uVar5 = 0x3fffffffffffffff;
  }
  local_58 = (void *)0x0;
  local_70 = operator_new(uVar5 << 2);
LAB_00177774:
  local_68 = (undefined4 *)((long)local_70 + lVar4 * 4);
  local_58 = (void *)((long)local_70 + uVar5 * 4);
  local_60 = local_68 + 1;
  *local_68 = *param_2;
  FUN_0015fb5c(param_1,&local_70);
  if ((long)local_60 - (long)local_68 != 0) {
    local_60 = local_60 + (((long)local_60 - (long)local_68) - 4U >> 2 ^ 0xffffffffffffffff);
  }
  if (local_70 != (void *)0x0) {
    operator_delete(local_70);
  }
  if (*(long *)(lVar1 + 0x28) == local_48) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}


