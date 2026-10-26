/* Ghidra DecompileAt: 40 function(s) */

/* ===== Function: FUN_0078bc04 @ 0078bc04  size=3580  depth=0
   signature: undefined FUN_0078bc04() ===== */

/* WARNING: Removing unreachable block (ram,0x0078bd9c) */
/* WARNING: Removing unreachable block (ram,0x0078c2cc) */
/* WARNING: Removing unreachable block (ram,0x0078c2d0) */
/* WARNING: Removing unreachable block (ram,0x0078c2e0) */
/* WARNING: Removing unreachable block (ram,0x0078c334) */
/* WARNING: Removing unreachable block (ram,0x0078c2ec) */
/* WARNING: Removing unreachable block (ram,0x0078c304) */

void FUN_0078bc04(undefined8 param_1,undefined8 param_2,ulong param_3,int param_4,int param_5,
                 int param_6,undefined4 param_7,ulong param_8)

{
  long *plVar1;
  uint *puVar2;
  float *pfVar3;
  ulong *puVar4;
  undefined4 uVar5;
  long lVar6;
  int iVar7;
  char in_NG;
  char cVar8;
  undefined1 in_ZR;
  char in_OV;
  char cVar9;
  undefined1 uVar10;
  bool bVar11;
  int iVar12;
  long *plVar13;
  void *__s;
  int *piVar14;
  undefined1 *puVar15;
  undefined8 extraout_x1;
  uint uVar16;
  uint extraout_w8;
  long lVar17;
  long extraout_x8;
  long extraout_x8_00;
  long lVar18;
  ulong extraout_x8_01;
  ulong extraout_x8_02;
  long *plVar19;
  long extraout_x8_03;
  long extraout_x8_04;
  long extraout_x8_05;
  long extraout_x8_06;
  long extraout_x8_07;
  uint uVar20;
  int iVar21;
  long extraout_x9;
  ulong uVar22;
  long lVar23;
  ulong extraout_x9_00;
  ulong extraout_x9_01;
  long extraout_x9_02;
  long extraout_x9_03;
  long extraout_x9_04;
  long extraout_x9_05;
  long extraout_x9_06;
  undefined8 extraout_x9_07;
  ulong uVar24;
  ulong *puVar25;
  long extraout_x10;
  long extraout_x10_00;
  long extraout_x10_01;
  long extraout_x10_02;
  long extraout_x10_03;
  long extraout_x10_04;
  undefined8 extraout_x10_05;
  long extraout_x11;
  ulong extraout_x11_00;
  long extraout_x11_01;
  long extraout_x12;
  ulong extraout_x13;
  long lVar26;
  long extraout_x13_00;
  uint uVar27;
  ulong uVar28;
  ulong uVar29;
  undefined8 *puVar30;
  long unaff_x29;
  float fVar31;
  float fVar32;
  undefined4 extraout_s0;
  undefined8 uVar33;
  float fVar34;
  undefined4 uVar35;
  undefined8 uVar36;
  undefined1 auVar37 [16];
  int iStack0000000000000190;
  undefined4 uStack0000000000000194;
  int iStack0000000000000198;
  undefined4 uStack000000000000019c;
  undefined4 uStack00000000000001a0;
  int local_42c;
  int local_3d0;
  int local_3cc;
  int local_3c8;
  ulong local_3c0;
  uint local_3b8;
  undefined4 uStack_3b4;
  char local_3b0;
  undefined7 uStack_3af;
  int local_398;
  int local_394;
  int local_390;
  int local_38c;
  undefined4 local_388;
  undefined8 local_384 [98];
  
  FUN_00b8c0b8();
  auVar37 = FUN_00bf2810();
  plVar13 = auVar37._0_8_;
  lVar6 = tpidr_el0;
  *(undefined8 *)(unaff_x29 + -0x70) = *(undefined8 *)(lVar6 + 0x28);
  if (plVar13 == (long *)0x0) {
    iVar21 = -0x6c;
    goto LAB_0078bc6c;
  }
  iVar21 = -0x68;
  if ((auVar37._8_8_ == 0) || (*(long *)(unaff_x29 + 0x10) == 0)) goto LAB_0078bc6c;
  __s = (void *)FUN_00c0ad88();
  in_ZR = in_NG == in_OV && param_6 == 1;
  if (in_NG != in_OV || param_6 < 1) {
    iVar21 = -0x68;
    goto LAB_0078bc6c;
  }
  uVar16 = (uint)param_3;
  in_ZR = uVar16 == 3;
  if (3 < uVar16) {
    iVar21 = -0x6e;
    goto LAB_0078bc6c;
  }
  memset(__s,0,0x6a94);
  local_3c0 = param_8 & 0xffffffff;
  uVar20 = (uint)plVar13[0x2b];
  uVar27 = (uint)param_8;
  in_ZR = false;
  local_3d0 = param_5;
  local_3cc = param_4;
  local_3c8 = param_6;
  local_3b8 = uVar16;
  uStack_3b4 = param_7;
  if (((((uVar27 & (uVar20 ^ 0xffffffff) & 0x7f) == 0) &&
       (in_ZR = (uVar27 & (uVar20 ^ 0xffffffff) & 0x900) == 0, (bool)in_ZR)) &&
      (((uVar27 & (uVar20 ^ 0xffffffff)) >> 0x12 & 1) == 0)) &&
     (in_ZR = ((~uVar27 & 0x300000) != 0) == ((~(uint)plVar13[0xa5] & 0x300000) == 0), !(bool)in_ZR)
     ) {
    in_ZR = false;
    if (((ulong)~uVar20 & param_8 & 0x80000) == 0) {
      if ((param_8 & 0xc0000) == 0) {
        in_ZR = (int)plVar13[0xa6] == 0x666;
        if (((!(bool)in_ZR) || (in_ZR = (int)plVar13[0xa7] == 0x888, !(bool)in_ZR)) ||
           (((uVar27 >> 8 & 1) != 0 &&
            (in_ZR = *(int *)((long)plVar13 + 0x534) == 0x777, !(bool)in_ZR)))) {
          iVar21 = -0x70;
          goto LAB_0078bc6c;
        }
        if (*(char *)((long)plVar13 + 0x53d) != '\0') {
          *(undefined1 *)((long)plVar13 + 0x53d) = 0;
        }
        plVar13[0x28] = local_3c0;
        uVar29 = 0;
        puVar25 = (ulong *)plVar13[0x46];
        while ((ulong *)(plVar13[0x46] + ((ulong)plVar13[0x47] >> 3 & 0x1ffffffffffffff8)) !=
               puVar25 || uVar29 != (plVar13[0x47] & 0x3fU)) {
          uVar16 = 1;
          if ((*puVar25 & 1L << (uVar29 & 0x3f)) != 0) goto LAB_0078c020;
          iVar21 = (int)uVar29;
          bVar11 = uVar29 != 0x3f;
          puVar4 = puVar25 + 1;
          if (bVar11) {
            puVar4 = puVar25;
          }
          uVar29 = 0;
          puVar25 = puVar4;
          if (bVar11) {
            uVar29 = (ulong)(iVar21 + 1);
          }
        }
        uVar16 = 0;
LAB_0078c020:
        lVar17 = plVar13[0x25];
        if ((char)plVar13[0x2d] == '\0') {
          uVar35 = 0x41200000;
          if ((~uVar27 & 0x300000) != 0 && (param_8 & 0x100) != 0) {
            uVar35 = 0x3fc00000;
          }
          *(undefined4 *)(plVar13 + 0x2a) = uVar35;
        }
        lVar18 = lVar17 + 1;
        plVar13[0x25] = lVar18;
        if (uVar16 != 0) {
          lVar26 = (long)(int)plVar13[0x3d];
          *(undefined4 *)(plVar13 + 0x3e) = 0;
          lVar23 = 0;
          if (lVar26 != 0) {
            lVar23 = lVar18 / lVar26;
          }
          bVar11 = lVar18 != lVar23 * lVar26 && -1 < lVar17;
LAB_0078c0e0:
          FUN_00bfd4dc();
          FUN_00bf7ae0(auVar37._8_8_);
          FUN_0078ead4();
          in_ZR = uStack000000000000019c == param_4;
          if (((bool)in_ZR) && (in_ZR = iStack0000000000000198 == param_5, (bool)in_ZR)) {
            fVar31 = (float)param_4;
            fVar34 = (float)param_5;
            fVar32 = fVar34;
            if (fVar34 <= fVar31) {
              fVar32 = fVar31;
            }
            cVar9 = NAN(fVar34) || NAN(fVar31);
            in_ZR = fVar34 == fVar31;
            cVar8 = fVar34 < fVar31;
            if (!(bool)cVar8) {
              fVar34 = fVar31;
            }
            uVar33 = FUN_00b96d28(0x42480000,fVar32 / fVar34);
            if (!(bool)in_ZR && cVar8 == cVar9) goto LAB_0078c138;
            if ((uVar27 >> 0xc & 1) == 0) {
              if (uVar16 == 0 && !bVar11) {
                FUN_00ba9068(uVar33,&stack0x00000190,&local_3d0);
                goto LAB_0078c6a8;
              }
              FUN_00bd4e98(uVar16 ^ 1,uVar33,&stack0x00000190);
              uVar33 = FUN_0078eb50();
              if (!bVar11 && (extraout_w8 & 1) == 0) {
                FUN_00b8b56c(uVar33,&stack0x00000190,&local_3d0);
                goto LAB_0078c6a8;
              }
            }
            else {
              FUN_00bd4e98(uVar33,&stack0x00000190);
              uVar33 = FUN_0078eb50();
              FUN_00b9f460(uVar33,&stack0x00000190,&local_3d0);
LAB_0078c6a8:
              FUN_0078f020();
            }
            FUN_0078f9d4(plVar13);
            iVar21 = 0;
          }
          else {
LAB_0078c138:
            iVar21 = -0x65;
          }
          piVar14 = &stack0x00000190;
          goto LAB_0078c140;
        }
        iVar21 = (int)plVar13[0x3e] + 1;
        *(int *)(plVar13 + 0x3e) = iVar21;
        if (iVar21 < 10) {
          lVar23 = 2;
        }
        else if (iVar21 < 0x1e) {
          lVar23 = 4;
        }
        else {
          lVar23 = (long)((int)plVar13[0x3d] / 3);
        }
        bVar11 = false;
        if (lVar17 < 0) goto LAB_0078c0e0;
        lVar17 = 0;
        if (lVar23 != 0) {
          lVar17 = lVar18 / lVar23;
        }
        if (lVar18 == lVar17 * lVar23) goto LAB_0078c0e0;
        if ((uVar27 >> 0xc & 1) != 0) {
          bVar11 = true;
          goto LAB_0078c0e0;
        }
      }
      else {
        if ((((int)plVar13[0xa6] != 0x666) || ((int)plVar13[0xa7] != 0x888)) ||
           (((uVar27 >> 8 & 1) != 0 && (*(int *)((long)plVar13 + 0x534) != 0x777)))) {
          in_ZR = 0;
          iVar21 = -0x70;
          goto LAB_0078bc6c;
        }
        lVar17 = 0;
        uVar29 = param_8 & 0xf0000000;
        while (lVar17 < (int)plVar13[0x47]) {
          FUN_00b93300();
          *(ulong *)(plVar13[0x49] + extraout_x11) =
               *(ulong *)(plVar13[0x49] + extraout_x11) & (extraout_x13 ^ 0xffffffffffffffff);
          lVar17 = extraout_x8;
        }
        *(undefined1 *)((long)plVar13 + 0x53d) = 1;
        FUN_00c4bf20(&local_398);
        iVar7 = local_3cc;
        iVar21 = local_3d0;
        auVar37 = FUN_00bf5bc8();
        FUN_0078ead4(auVar37._0_8_,auVar37._8_8_,iVar21);
        in_ZR = local_38c == iVar7;
        if (((bool)in_ZR) && (in_ZR = local_390 == iVar21, (bool)in_ZR)) {
          fVar31 = (float)iVar7;
          fVar34 = (float)iVar21;
          fVar32 = fVar34;
          if (fVar34 <= fVar31) {
            fVar32 = fVar31;
          }
          cVar9 = NAN(fVar34) || NAN(fVar31);
          in_ZR = fVar34 == fVar31;
          cVar8 = fVar34 < fVar31;
          if (!(bool)cVar8) {
            fVar34 = fVar31;
          }
          FUN_00b96d28(0x42480000,fVar32 / fVar34);
          if (!(bool)in_ZR && cVar8 == cVar9) goto LAB_0078c010;
          plVar19 = plVar13 + 0x2e;
          plVar13[0x8b] = 0;
          plVar13[0x67] = plVar13[0x66];
          plVar13[0x6a] = plVar13[0x69];
          plVar13[0x8e] = plVar13[0x8d];
          plVar13[0x91] = plVar13[0x90];
          iVar12 = FUN_0078fc4c(&local_398,param_3 & 0xffffffff,plVar19);
          if (((iVar12 != 0) || (in_ZR = *(int *)((long)plVar13 + 0x17c) == iVar7, !(bool)in_ZR)) ||
             (in_ZR = (int)plVar13[0x2f] == iVar21, !(bool)in_ZR)) goto LAB_0078c010;
          FUN_0078fd30(plVar13 + 0x4d,plVar19,param_7,plVar19);
          lVar17 = 0x120;
          if ((param_8 & 0x80000) == 0) {
            lVar17 = 0x118;
          }
          lVar17 = *(long *)((long)plVar13 + lVar17);
          FUN_00bae4e0(&stack0x00000b48);
          auVar37 = FUN_00c4c04c();
          FUN_00b9c0cc(auVar37._0_8_,auVar37._8_8_,plVar13 + 0x8d);
          FUN_00acf2d4();
          FUN_00bf397c();
          if (plVar13[0x8d] == plVar13[0x8e]) {
            iVar21 = 0;
            plVar13[0x26] = plVar13[0x25];
            in_ZR = 1;
          }
          else {
            uVar10 = 0xefffffff < uVar29;
            if (uVar29 == 0xf0000000) {
              FUN_00ba6734();
              lVar17 = 0xc;
              lVar26 = 1;
              lVar18 = extraout_x8_00;
              lVar23 = extraout_x9;
              uVar29 = extraout_x11_00;
              while (uVar22 = lVar23 - lVar18 >> 4, in_ZR = uVar29 == uVar22, uVar29 < uVar22) {
                uVar22 = uVar29 >> 3 & 0x1ffffffffffffff8;
                *(ulong *)(plVar13[0x46] + uVar22) =
                     *(ulong *)(plVar13[0x46] + uVar22) | lVar26 << (uVar29 & 0x3f);
                lVar18 = plVar13[0x8d] + lVar17;
                lVar17 = *plVar13 + lVar17;
                *(undefined4 *)(lVar17 + -0xc) = *(undefined4 *)(lVar18 + -0xc);
                *(undefined4 *)(lVar17 + -8) = *(undefined4 *)(lVar18 + -8);
                *(undefined4 *)(lVar17 + -4) = *(undefined4 *)(lVar18 + -4);
                FUN_00c0019c();
                *(undefined4 *)(plVar13[3] + extraout_x10) =
                     *(undefined4 *)(plVar13[0x90] + extraout_x11_01 * 4);
                *(undefined4 *)(plVar13[3] + extraout_x10 + 4) = 0;
                lVar23 = plVar13[0x8e];
                lVar18 = plVar13[0x8d];
                lVar17 = extraout_x12;
                lVar26 = extraout_x13_00;
                uVar29 = extraout_x11_01 + 1;
              }
            }
            else {
              plVar1 = plVar13 + 0x66;
              lVar18 = 0;
              FUN_00b90020();
              lVar23 = 1;
              for (; (FUN_00bd0fb8(), uVar33 = DAT_00d92a98, !(bool)uVar10 &&
                     ((long)uVar29 < (long)*(int *)((long)plVar13 + 0x1ec))); uVar29 = uVar29 + 1) {
                plVar13[0x8b] = 0;
                plVar13[0x67] = plVar13[0x66];
                FUN_00793a8c(*(undefined8 *)(plVar13[0x43] + uVar29 * 8),plVar19,
                             extraout_x8_03 + lVar18,plVar1,extraout_x10_05,&local_3d0);
                uVar10 = (ulong)plVar13[0x67] <= (ulong)plVar13[0x66];
                if (plVar13[0x66] != plVar13[0x67]) {
                  FUN_00b95ce8(plVar13[0x46]);
                  iVar21 = (int)plVar13[0x27] + 1;
                  *(int *)(plVar13 + 0x27) = iVar21;
                  *(int *)(plVar13[0x40] + uVar29 * 4) = iVar21;
                  *(int *)(plVar13[0x15] + uVar29 * 4) = (int)plVar13[0x27];
                  *(undefined4 *)(plVar13[0x18] + uVar29 * 4) = 1;
                  FUN_00b9f7a8(plVar13[6] + lVar17);
                }
                lVar17 = lVar17 + 0x18;
                lVar18 = lVar18 + 0x10;
              }
              for (local_42c = 0; in_ZR = true, local_42c != 2; local_42c = local_42c + 1) {
                lVar18 = 0;
                uVar36 = FUN_00ba86e4();
                for (uVar29 = 0; (long)uVar29 < (long)*(int *)((long)plVar13 + 0x1ec);
                    uVar29 = uVar29 + 1) {
                  uVar28 = uVar29 >> 6;
                  uVar22 = 1L << (uVar29 & 0x3f);
                  if ((*(ulong *)(plVar13[0x46] + uVar28 * 8) & uVar22) != 0) {
                    plVar13[0x8b] = 0;
                    plVar13[0x67] = plVar13[0x66];
                    FUN_00b97b98(&stack0x000001a8,uVar36,&local_398,plVar1,extraout_x10_05,0,
                                 &local_3d0,plVar13 + 0x93);
                    _uStack000000000000019c = uVar33;
                    FUN_00794194(*(undefined8 *)(plVar13[0x43] + uVar29 * 8));
                    lVar26 = plVar13[0x46];
                    if (plVar13[0x66] == plVar13[0x67]) {
LAB_0078c9b0:
                      uVar22 = *(ulong *)(lVar26 + uVar28 * 8) & (uVar22 ^ 0xffffffffffffffff);
                    }
                    else {
                      *(ulong *)(lVar26 + uVar28 * 8) = *(ulong *)(lVar26 + uVar28 * 8) | uVar22;
                      FUN_00bec7dc(plVar1,extraout_x1,plVar13 + 6);
                      FUN_00bd22c8(extraout_x8_04 + lVar18);
                      auVar37 = FUN_00bec7dc();
                      *(undefined8 *)(extraout_x8_05 + lVar18 + 0x28) = extraout_x9_07;
                      *(undefined1 (*) [16])(extraout_x8_05 + lVar18 + 0x18) = auVar37;
                      uVar36 = FUN_00bec7dc();
                      FUN_00bdd418(extraout_x8_06 + lVar18,uVar36,uVar29 & 0xffffffff);
                      *(undefined4 *)(plVar13[0x15] + uVar29 * 4) =
                           *(undefined4 *)(plVar13[0x40] + uVar29 * 4);
                      *(int *)(plVar13[0x18] + uVar29 * 4) =
                           *(int *)(plVar13[0x18] + uVar29 * 4) + 1;
                      FUN_0079602c(&local_3b0,plVar13[0x66]);
                      auVar37 = FUN_00b9d968(*plVar13,CONCAT71(uStack_3af,local_3b0));
                      *(undefined1 (*) [16])(extraout_x8_07 + uVar29 * 0x10) = auVar37;
                      *(int *)(plVar13[0x12] + lVar23 + 8) = (int)plVar13[0x94];
                      *(int *)(plVar13[0x12] + lVar23) = (int)plVar13[0x93];
                      uVar36 = FUN_0078ff78();
                      *(undefined4 *)(plVar13[0x12] + lVar23 + 4) = extraout_s0;
                      FUN_00bd7da4(*(undefined8 *)(plVar13[0x43] + uVar29 * 8),
                                   *(undefined4 *)(plVar13[0x12] + lVar23 + 4),uVar36,&local_3b0);
                      lVar26 = plVar13[0xf];
                      if (local_3b0 == '\0') {
                        uVar24 = *(ulong *)(lVar26 + uVar28 * 8) & (uVar22 ^ 0xffffffffffffffff);
                      }
                      else {
                        uVar24 = *(ulong *)(lVar26 + uVar28 * 8) | uVar22;
                      }
                      *(ulong *)(lVar26 + uVar28 * 8) = uVar24;
                      FUN_0066d328(plVar13[0xc] + lVar17,plVar13 + 0x96);
                      *(undefined8 *)(plVar13[3] + uVar29 * 8) =
                           *(undefined8 *)((long)plVar13 + 0x4a4);
                      uVar24 = FUN_00795ea4(*(undefined4 *)(plVar13[0x12] + lVar23),plVar13[0x66],
                                            &local_398);
                      lVar26 = plVar13[0x49];
                      if ((uVar24 & 1) == 0) goto LAB_0078c9b0;
                      uVar22 = *(ulong *)(lVar26 + uVar28 * 8) | uVar22;
                    }
                    *(ulong *)(lVar26 + uVar28 * 8) = uVar22;
                    uVar36 = FUN_00422aa8(&stack0x000001a8);
                  }
                  lVar23 = lVar23 + 0xc;
                  lVar17 = lVar17 + 0x18;
                  lVar18 = lVar18 + 0x48;
                }
              }
            }
            FUN_0078f9d4(plVar13);
            iVar21 = 0;
          }
        }
        else {
LAB_0078c010:
          iVar21 = -0x65;
        }
        piVar14 = &local_398;
LAB_0078c140:
        FUN_00c4c350(piVar14);
        if (iVar21 != 0) goto LAB_0078bc6c;
      }
      puVar2 = (uint *)((long)__s + 0x6a90);
      uVar22 = (ulong)(plVar13[1] - *plVar13) >> 4;
      lVar17 = 0;
      uVar29 = 0;
      *puVar2 = (uint)uVar22;
      if ((param_8 & 0xf0000000) == 0) {
        while (_iStack0000000000000190 = _iStack0000000000000190 & 0xffffffff00000000,
              0 < (int)uVar22) {
          FUN_00bed210();
          if (((extraout_x9_01 & 1) != 0) && (((long *)plVar13[6])[1] != *(long *)plVar13[6])) {
            FUN_0078ca00(&stack0x00000b48,&stack0x00000190);
            FUN_0078ca00(&stack0x00000710,plVar13[0x15] + lVar17);
          }
          FUN_00bd2478();
          lVar17 = lVar17 + 4;
          uVar22 = extraout_x8_02;
        }
        for (lVar17 = 0; in_ZR = lVar17 == 0, !(bool)in_ZR; lVar17 = lVar17 + 1) {
          lVar18 = (long)*(int *)(&stack0x00000b58 + lVar17 * 4);
          if ((plVar13[1] != *plVar13) && (plVar13[7] != plVar13[6])) {
            pfVar3 = (float *)(*plVar13 + lVar18 * 0x10);
            local_398 = (int)*pfVar3;
            local_394 = (int)pfVar3[1];
            local_390 = (int)(*pfVar3 + pfVar3[2]);
            local_38c = (int)(pfVar3[1] + pfVar3[3]);
            local_388 = *(undefined4 *)(plVar13[3] + lVar18 * 8);
            plVar19 = (long *)(plVar13[6] + lVar18 * 0x18);
            lVar26 = *plVar19;
            for (lVar23 = 0; lVar23 != 0x350; lVar23 = lVar23 + 8) {
              *(undefined8 *)((long)local_384 + lVar23) = *(undefined8 *)(lVar26 + lVar23);
            }
            NEON_rev64(*(undefined8 *)(plVar13[0x12] + lVar18 * 0xc + 4),4);
            puVar15 = &stack0x00000300;
            if ((uVar27 >> 8 & 1) == 0) {
              iStack0000000000000198 = -1;
              _iStack0000000000000190 = 0xffffffffffffffff;
            }
            else {
              for (lVar18 = 0; lVar18 != 0xb0; lVar18 = lVar18 + 8) {
                *(undefined8 *)((long)&stack0x00000198 + lVar18 + 8) =
                     *(undefined8 *)(*plVar19 + lVar18 + 0x350);
              }
              for (lVar18 = 0; lVar18 != 0xb0; lVar18 = lVar18 + 8) {
                *(undefined8 *)(&stack0x00000250 + lVar18) =
                     *(undefined8 *)(*plVar19 + lVar18 + 0x400);
              }
              _iStack0000000000000190 = CONCAT44(uStack0000000000000194,2);
              lVar18 = 0;
              while (lVar18 != 0x68) {
                puVar15 = (undefined1 *)FUN_00bf0fe8(puVar15);
                *(undefined8 *)(puVar15 + extraout_x9_02) = *(undefined8 *)(extraout_x10_00 + 0x4b0)
                ;
                lVar18 = extraout_x9_02 + 8;
              }
              lVar18 = 0;
              while (lVar18 != 0x68) {
                auVar37 = FUN_00bf0fe8(puVar15);
                puVar15 = auVar37._0_8_;
                *(undefined8 *)(auVar37._8_8_ + extraout_x9_03) =
                     *(undefined8 *)(extraout_x10_01 + 0x518);
                lVar18 = extraout_x9_03 + 8;
              }
              _iStack0000000000000190 = CONCAT44(2,iStack0000000000000190);
              lVar18 = 0;
              while (lVar18 != 0x200) {
                puVar15 = (undefined1 *)FUN_00bf0fe8(puVar15);
                *(undefined8 *)(&stack0x000003d0 + extraout_x9_04) =
                     *(undefined8 *)(extraout_x10_02 + 0x580);
                lVar18 = extraout_x9_04 + 8;
              }
              iStack0000000000000198 = 1;
            }
            if ((uVar27 >> 0xb & 1) == 0) {
              uVar35 = 0xffffffff;
            }
            else {
              lVar18 = 0;
              while (lVar18 != 0xa0) {
                puVar15 = (undefined1 *)FUN_00bf0fe8(puVar15);
                *(undefined8 *)(&stack0x000005d0 + extraout_x9_05) =
                     *(undefined8 *)(extraout_x10_03 + 0x780);
                lVar18 = extraout_x9_05 + 8;
              }
              lVar18 = 0;
              while (lVar18 != 0xa0) {
                puVar15 = (undefined1 *)FUN_00bf0fe8(puVar15);
                *(undefined8 *)(&stack0x00000670 + extraout_x9_06) =
                     *(undefined8 *)(extraout_x10_04 + 0x820);
                lVar18 = extraout_x9_06 + 8;
              }
              uVar35 = 2;
            }
            uStack000000000000019c = uVar35;
          }
          memcpy((void *)((long)__s + lVar17 * 0x580 + 0x3390),&stack0x00000190,0x580);
          FUN_00bd2654((void *)((long)__s + lVar17 * 0x528),&local_398);
        }
        *puVar2 = 0;
        FUN_0078ca3c(&stack0x00000710,&stack0x00000368);
        FUN_0078ca3c(&stack0x00000b48);
        iVar21 = 0;
      }
      else {
        uVar16 = 0;
        for (; in_ZR = uVar29 == (long)(int)uVar22, (long)uVar29 < (long)(int)uVar22;
            uVar29 = uVar29 + 1) {
          FUN_00bed210();
          uVar22 = extraout_x8_01;
          if (((extraout_x9_00 & 1L << (uVar29 & 0x3f)) != 0) && (plVar13[1] != *plVar13)) {
            puVar30 = (undefined8 *)(*plVar13 + lVar17 * 8);
            uVar33 = *puVar30;
            uVar36 = puVar30[1];
            fVar32 = (float)uVar33;
            fVar34 = (float)((ulong)uVar33 >> 0x20);
            puVar30 = (undefined8 *)((long)__s + (long)(int)uVar16 * 0x528);
            uVar35 = *(undefined4 *)(plVar13[3] + lVar17 * 4);
            uVar5 = *(undefined4 *)(plVar13[0x18] + uVar29 * 4);
            *puVar30 = CONCAT44((int)fVar34,(int)fVar32);
            puVar30[1] = CONCAT44((int)(fVar34 + (float)((ulong)uVar36 >> 0x20)),
                                  (int)(fVar32 + (float)uVar36));
            *(undefined4 *)(puVar30 + 2) = uVar35;
            memcpy((void *)((long)puVar30 + 0x14),&stack0x00000190,0x510);
            *(undefined4 *)((long)puVar30 + 0x524) = uVar5;
            uVar16 = uVar16 + 1;
            uVar22 = (ulong)*puVar2;
          }
          lVar17 = lVar17 + 2;
        }
        iVar21 = 0;
        *puVar2 = uVar16;
      }
      goto LAB_0078bc6c;
    }
  }
  iVar21 = -0x6b;
LAB_0078bc6c:
  FUN_00b8fc6c(*(undefined8 *)(lVar6 + 0x28));
  if (!(bool)in_ZR) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  FUN_00b8c428(iVar21);
  return;
}



/* ===== Function: FUN_00ae4a90 @ 00ae4a90  size=16096  depth=0
   signature: undefined FUN_00ae4a90() ===== */

/* WARNING: Removing unreachable block (ram,0x00ae6c58) */
/* WARNING: Removing unreachable block (ram,0x00ae674c) */
/* WARNING: Removing unreachable block (ram,0x00ae6094) */
/* WARNING: Removing unreachable block (ram,0x00ae6fdc) */
/* WARNING: Removing unreachable block (ram,0x00ae5644) */
/* WARNING: Removing unreachable block (ram,0x00ae5648) */
/* WARNING: Removing unreachable block (ram,0x00ae5650) */
/* WARNING: Removing unreachable block (ram,0x00ae5668) */
/* WARNING: Removing unreachable block (ram,0x00ae568c) */
/* WARNING: Removing unreachable block (ram,0x00ae6098) */
/* WARNING: Removing unreachable block (ram,0x00ae60ac) */
/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void FUN_00ae4a90(undefined1 param_1 [16],undefined1 param_2 [16],ulong param_3,ulong param_4,
                 undefined8 param_5,undefined8 param_6,long param_7,undefined8 param_8,
                 undefined8 *param_9)

{
  long *plVar1;
  char *pcVar2;
  char *pcVar3;
  float *pfVar4;
  long lVar5;
  undefined1 auVar6 [16];
  undefined1 auVar7 [16];
  int iVar8;
  undefined1 in_ZR;
  undefined1 in_CY;
  undefined1 uVar9;
  bool bVar10;
  uint uVar11;
  void *pvVar13;
  undefined8 uVar14;
  long lVar15;
  undefined8 uVar16;
  undefined1 *puVar17;
  long *plVar18;
  undefined8 *puVar19;
  int iVar12;
  undefined8 extraout_x1;
  undefined8 extraout_x1_00;
  long extraout_x1_01;
  undefined8 extraout_x1_02;
  undefined8 extraout_x1_03;
  long extraout_x1_04;
  undefined8 extraout_x1_05;
  undefined8 extraout_x1_06;
  undefined8 extraout_x1_07;
  undefined8 extraout_x1_08;
  char *pcVar20;
  size_t __n;
  char *pcVar21;
  char *pcVar22;
  undefined4 *puVar23;
  int extraout_w8;
  int extraout_w8_00;
  int extraout_w8_01;
  undefined4 extraout_w8_02;
  long extraout_x8;
  long extraout_x8_00;
  long extraout_x8_01;
  ulong extraout_x8_02;
  long extraout_x8_03;
  ulong extraout_x8_04;
  long extraout_x8_05;
  long extraout_x8_06;
  long extraout_x8_07;
  long extraout_x8_08;
  long extraout_x8_09;
  ulong extraout_x8_10;
  ulong extraout_x8_11;
  long extraout_x8_12;
  undefined1 (*extraout_x8_13) [16];
  ulong extraout_x8_14;
  long extraout_x8_15;
  double *extraout_x8_16;
  int *extraout_x8_17;
  long extraout_x8_18;
  long extraout_x8_19;
  undefined *puVar24;
  long extraout_x8_20;
  long extraout_x8_21;
  long extraout_x8_22;
  undefined8 extraout_x8_23;
  ulong uVar25;
  ulong extraout_x8_24;
  long *extraout_x8_25;
  long extraout_x8_26;
  long extraout_x8_27;
  long extraout_x8_28;
  long extraout_x8_29;
  long extraout_x8_30;
  long extraout_x8_31;
  long extraout_x8_32;
  ulong extraout_x8_33;
  long extraout_x8_34;
  long extraout_x8_35;
  long extraout_x8_36;
  long extraout_x8_37;
  long extraout_x8_38;
  long extraout_x8_39;
  long extraout_x8_40;
  long extraout_x8_41;
  long extraout_x8_42;
  int *piVar26;
  int *extraout_x8_43;
  long extraout_x8_44;
  long extraout_x8_45;
  long extraout_x8_46;
  long extraout_x8_47;
  undefined4 *extraout_x8_48;
  long extraout_x8_49;
  long extraout_x8_50;
  long extraout_x8_51;
  long extraout_x8_52;
  long extraout_x8_53;
  ulong extraout_x8_54;
  long extraout_x8_55;
  ulong extraout_x8_56;
  int extraout_w9;
  int iVar27;
  uint extraout_w9_00;
  int iVar28;
  uint extraout_w9_01;
  int iVar29;
  int iVar30;
  int *piVar31;
  long extraout_x9;
  long lVar32;
  long extraout_x9_00;
  ulong extraout_x9_01;
  long extraout_x9_02;
  long lVar33;
  long lVar34;
  long lVar35;
  long extraout_x9_03;
  long lVar36;
  long lVar37;
  undefined8 extraout_x9_04;
  undefined8 extraout_x9_05;
  ulong extraout_x9_06;
  ulong extraout_x9_07;
  ulong extraout_x9_08;
  long extraout_x9_09;
  long extraout_x9_10;
  long extraout_x9_11;
  long extraout_x9_12;
  long extraout_x9_13;
  long extraout_x9_14;
  long extraout_x9_15;
  ulong extraout_x9_16;
  ulong extraout_x9_17;
  long extraout_x9_18;
  long extraout_x9_19;
  long extraout_x9_20;
  long extraout_x9_21;
  ulong extraout_x9_22;
  ulong extraout_x9_23;
  long extraout_x9_24;
  ulong extraout_x9_25;
  ulong extraout_x9_26;
  long extraout_x9_27;
  long extraout_x9_28;
  long extraout_x9_29;
  long extraout_x9_30;
  long extraout_x9_31;
  ulong extraout_x9_32;
  float *extraout_x9_33;
  long extraout_x9_34;
  long extraout_x9_35;
  undefined4 *extraout_x9_36;
  long extraout_x9_37;
  long extraout_x9_38;
  long extraout_x9_39;
  long extraout_x9_40;
  long extraout_x9_41;
  long extraout_x9_42;
  int iVar38;
  int extraout_w10;
  int extraout_w10_00;
  ulong uVar39;
  long extraout_x10;
  int *extraout_x10_00;
  long lVar40;
  ulong extraout_x10_01;
  long extraout_x10_02;
  long lVar41;
  char *extraout_x10_03;
  long extraout_x10_04;
  ulong extraout_x10_05;
  double *extraout_x10_06;
  long extraout_x10_07;
  long extraout_x10_08;
  long extraout_x10_09;
  long extraout_x10_10;
  long extraout_x10_11;
  ulong extraout_x10_12;
  int iVar42;
  int extraout_w11;
  int iVar43;
  int extraout_w11_00;
  long extraout_x11;
  long extraout_x11_00;
  ulong uVar44;
  long extraout_x11_01;
  long extraout_x11_02;
  long extraout_x11_03;
  long *extraout_x11_04;
  long extraout_x11_05;
  ulong extraout_x11_06;
  undefined8 extraout_x11_07;
  int extraout_w12;
  int iVar45;
  int iVar46;
  int extraout_w12_00;
  long extraout_x12;
  long extraout_x12_00;
  undefined8 extraout_x12_01;
  long extraout_x12_02;
  long extraout_x13;
  long *plVar47;
  float *extraout_x13_00;
  long extraout_x14;
  long extraout_x15;
  int iVar48;
  long *plVar49;
  long lVar50;
  ulong uVar51;
  long lVar52;
  uint *puVar53;
  long *plVar54;
  float *pfVar55;
  float *pfVar56;
  long *plVar57;
  ulong uVar58;
  long *plVar59;
  float *pfVar60;
  ulong unaff_x28;
  long lVar61;
  long unaff_x29;
  float fVar62;
  float fVar63;
  float fVar64;
  float fVar65;
  float fVar66;
  float fVar67;
  float fVar68;
  undefined4 uVar69;
  double dVar70;
  double dVar71;
  undefined1 extraout_q0 [16];
  undefined1 extraout_q0_00 [16];
  undefined1 extraout_q0_01 [16];
  undefined1 auVar72 [16];
  long extraout_d0;
  undefined8 extraout_var;
  undefined1 auVar73 [16];
  float fVar74;
  float fVar75;
  ulong uVar76;
  uint uVar77;
  float fVar78;
  float fVar79;
  int iVar80;
  undefined1 auVar81 [16];
  undefined1 auVar82 [16];
  undefined1 auVar83 [16];
  undefined1 auVar84 [16];
  undefined1 auVar85 [16];
  int iStack00000000000001a0;
  uint uStack00000000000001a4;
  ulong in_stack_000001a8;
  ulong in_stack_000001b0;
  ulong in_stack_000001b8;
  int in_stack_000001c0;
  undefined8 in_stack_000001c8;
  undefined8 in_stack_000001d0;
  undefined8 in_stack_000001d8;
  undefined4 in_stack_000001e0;
  undefined4 in_stack_000001e8;
  ulong in_stack_00000aa0;
  int iVar86;
  undefined8 in_stack_00000aa8;
  long in_stack_00000ab0;
  undefined8 *in_stack_00000b00;
  undefined8 in_stack_00000b08;
  void *in_stack_00000b60;
  ulong in_stack_00000b80;
  long in_stack_00000b88;
  double *in_stack_00000b90;
  ulong in_stack_00000ca0;
  long in_stack_00000e28;
  double *in_stack_00000e38;
  long *in_stack_00000e70;
  ulong in_stack_00000e88;
  float local_410;
  float fStack_40c;
  undefined8 uStack_408;
  ulong local_3f0;
  ulong local_3e0;
  long local_3a8 [3];
  long local_390;
  int local_384;
  int local_380;
  int local_37c;
  int local_378;
  uint local_374;
  long local_370;
  int local_368;
  int local_364;
  int local_360;
  int local_35c;
  int local_358;
  int local_354;
  int local_350;
  undefined4 uStack_34c;
  int local_344;
  int local_340;
  int local_33c;
  int local_338;
  undefined8 local_330;
  undefined8 uStack_328;
  undefined8 local_308 [3];
  long local_2f0;
  undefined4 local_2e8;
  void *local_2d8 [3];
  undefined4 local_2c0;
  undefined4 uStack_2bc;
  undefined4 local_2a8;
  undefined4 uStack_2a4;
  long local_290;
  undefined4 local_288;
  undefined4 uStack_284;
  long local_280;
  undefined8 local_270;
  undefined4 local_264;
  ulong local_260;
  undefined8 uStack_258;
  long local_250;
  undefined8 uStack_248;
  
  FUN_00b8b700();
  auVar81 = FUN_00be586c();
  lVar50 = auVar81._8_8_;
  plVar18 = auVar81._0_8_;
  lVar5 = tpidr_el0;
  *(undefined8 *)(unaff_x29 + -0xa8) = *(undefined8 *)(lVar5 + 0x28);
  if (param_7 == 0) {
    auVar81._8_8_ = "SMASH_E_LOG ";
    auVar81._0_8_ = plVar18;
  }
  else if ((char)plVar18[0x12e] == '\0') {
    auVar81._8_8_ = "SMASH_E_LOG ";
    auVar81._0_8_ = plVar18;
  }
  else {
    FUN_00ba13cc(*(undefined4 *)(lVar50 + 0x18));
    if ((bool)in_ZR) {
      piVar26 = (int *)(lVar50 + 0x10);
      piVar31 = (int *)(lVar50 + 0xc);
    }
    else {
      piVar26 = (int *)(lVar50 + 0xc);
      piVar31 = (int *)(lVar50 + 0x10);
    }
    iVar12 = *piVar31;
    iVar30 = *piVar26;
    *(int *)(plVar18 + 1) = iVar30;
    *(int *)((long)plVar18 + 0xc) = iVar12;
    if ((int)plVar18[0x12f] != 0) {
      FUN_00bd0184();
      FUN_00be4814(extraout_x8 - extraout_x9);
      if (extraout_w8 == 0) {
        pcVar22 = (char *)((long)plVar18 + 0x9b4);
        pcVar22[0] = '\0';
        pcVar22[1] = '\0';
        pcVar22[2] = '\0';
        pcVar22[3] = '\0';
      }
      _iStack00000000000001a0 = _iStack00000000000001a0 & 0xffffffff00000000;
      FUN_0078514c(plVar18 + 0x138,(long)(int)plVar18[0x136],&stack0x000001a0);
      while( true ) {
        auVar82 = FUN_00b9fd48();
        if ((bool)in_CY) break;
        FUN_00bd7750();
      }
      iVar46 = 0;
      while( true ) {
        if ((int)plVar18[0x137] <= iVar46) break;
        uVar39 = (ulong)(iVar46 + *(int *)((long)plVar18 + 0x9b4));
        uVar51 = plVar18[0x134] - plVar18[0x133] >> 2;
        in_ZR = uVar39 == uVar51;
        if (uVar51 <= uVar39) {
          auVar81._8_8_ = "SMASH_E_LOG ";
          auVar81._0_8_ = auVar82._0_8_;
          goto LAB_00ae4c0c;
        }
        iVar46 = iVar46 + 1;
        *(undefined4 *)(plVar18[0x138] + (long)*(int *)(plVar18[0x133] + uVar39 * 4) * 4) = 1;
      }
      iVar46 = *(int *)((long)plVar18 + 0x9b4) + (int)plVar18[0x137];
      uVar51 = plVar18[0x134] - plVar18[0x133] >> 2;
      iVar80 = 0;
      if (uVar51 != 0) {
        iVar80 = (int)((ulong)(long)iVar46 / uVar51);
      }
      in_ZR = (int *)plVar18[0x139] == (int *)plVar18[0x138];
      *(int *)((long)plVar18 + 0x9b4) = iVar46 - iVar80 * (int)uVar51;
      if ((bool)in_ZR) {
        auVar81 = FUN_00c06648();
        goto LAB_00ae4c0c;
      }
      if (*(int *)plVar18[0x138] == 0) {
        FUN_00b9bed8(auVar82._0_8_,auVar82._8_8_,param_7,(int)plVar18[0x129]);
        FUN_00ae89b0();
        uVar51 = FUN_00ba226c(*(undefined8 *)(lVar5 + 0x28));
        goto joined_r0x00ae78b8;
      }
    }
    fVar62 = (float)iVar12;
    if (*(int *)((long)plVar18 + 0x9fc) != 0) {
LAB_00ae4c68:
      uVar51 = 0;
      plVar1 = plVar18 + 0x13b;
      lVar32 = 0x1c8;
      while( true ) {
        uVar9 = (ulong)((plVar18[0x13c] - plVar18[0x13b]) / 0x230) <= uVar51;
        if ((bool)uVar9) break;
        *(undefined4 *)(plVar18[0x13b] + lVar32) = 0;
        uVar51 = uVar51 + 1;
        lVar32 = lVar32 + 0x230;
      }
      FUN_00beca74();
      iVar46 = *(int *)((long)plVar18 + 0xa04);
      FUN_00bed548(&stack0x000001a0);
      FUN_0050096c();
      FUN_00bd0184();
      FUN_00bbfd44(local_3a8,(extraout_x8_00 - extraout_x9_00) / 0x230);
      while (FUN_00b9fd48(), !(bool)uVar9) {
        FUN_00bd7750();
      }
      for (uVar51 = 0; uVar51 < (ulong)((plVar18[0x13c] - plVar18[0x13b]) / 0x230);
          uVar51 = uVar51 + 1) {
        *(int *)(_iStack00000000000001a0 + uVar51 * 4) = (int)uVar51;
      }
      FUN_00bb7960();
      uVar51 = extraout_x9_01;
      for (lVar32 = extraout_x8_01; lVar32 < iVar46; lVar32 = lVar32 + 1) {
        for (uVar39 = (ulong)(int)uVar51;
            uVar39 < (ulong)((long)(in_stack_000001a8 - _iStack00000000000001a0) >> 2);
            uVar39 = uVar39 + 1) {
          iVar80 = *(int *)(_iStack00000000000001a0 + uVar39 * 4);
          iVar86 = *(int *)(_iStack00000000000001a0 + lVar32 * 4);
          if (*(int *)(*plVar1 + (long)iVar86 * 0x230 + 0x1cc) <
              *(int *)(*plVar1 + (long)iVar80 * 0x230 + 0x1cc)) {
            *(int *)(_iStack00000000000001a0 + lVar32 * 4) = iVar80;
            *(int *)(_iStack00000000000001a0 + uVar39 * 4) = iVar86;
          }
        }
        uVar51 = (ulong)((int)uVar51 + 1);
      }
      FUN_00bc8d10(0);
      iVar80 = extraout_w9;
      if (iVar46 <= extraout_w9) {
        iVar80 = iVar46;
      }
      for (uVar51 = extraout_x8_02; bVar10 = (ulong)(long)iVar80 <= uVar51,
          (long)uVar51 < (long)iVar80; uVar51 = uVar51 + 1) {
        *(undefined4 *)(local_3a8[0] + (long)*(int *)(_iStack00000000000001a0 + uVar51 * 4) * 4) = 1
        ;
      }
      lVar32 = 0;
      while (FUN_00bfbc78(lVar32), !bVar10) {
        lVar32 = extraout_x8_03 + 1;
        bVar10 = true;
        iVar46 = 0;
        if (*(int *)(extraout_x10 + extraout_x8_03 * 4) == 0) {
          iVar46 = *(int *)(*plVar1 + extraout_x9_02) + 1;
        }
        *(int *)(*plVar1 + extraout_x9_02) = iVar46;
      }
      FUN_003e8658(&stack0x000001a0);
      uVar16 = DAT_00d94288;
      pcVar22 = (char *)((long)plVar18 + 0x5c);
      pcVar2 = (char *)((long)plVar18 + 0x544);
      pcVar3 = (char *)((long)plVar18 + 0x844);
      uVar39 = 0;
      while( true ) {
        plVar49 = (long *)plVar18[0x13b];
        uVar51 = (plVar18[0x13c] - (long)plVar49) / 0x230;
        if (uVar51 <= uVar39) break;
        pvVar13 = (void *)FUN_00b8cc70(&local_260,uVar51,0x400);
        if (*(int *)(local_3a8[0] + uVar39 * 4) != 0) {
          if (*(int *)((long)plVar49 + uVar39 * 0x230 + 0x1c4) != 0) {
            if (plVar49[uVar39 * 0x46 + 6] - plVar49[uVar39 * 0x46 + 5] == 0x400) {
              pvVar13 = memcpy(&local_260,(void *)plVar49[uVar39 * 0x46 + 5],0x400);
              goto LAB_00ae4eb8;
            }
            pcVar22 = "wh - incorrect joint num\n";
            in_ZR = false;
LAB_00ae7904:
            FUN_00b8c554(pvVar13,"SMASH_E_LOG ",pcVar22);
            uVar51 = 0xffffff9b;
            goto LAB_00ae8960;
          }
LAB_00ae4eb8:
          iVar46 = (int)plVar18[0x129];
          lVar33 = (long)iVar46;
          lVar32 = plVar18[0xb];
          lVar34 = plVar49[uVar39 * 0x46 + 5];
          lVar35 = plVar49[uVar39 * 0x46 + 10];
          iVar80 = *(int *)(plVar18[9] + 0x88);
          FUN_00bcdbd0(pvVar13,*(undefined4 *)(lVar50 + 0x10),*(undefined4 *)(lVar50 + 0xc));
          lVar36 = plVar49[uVar39 * 0x46 + 0xd];
          lVar37 = plVar49[uVar39 * 0x46 + 0x13];
          FUN_00c4c100();
          FUN_007d90f0(&stack0x000001a0,plVar18[7],&stack0x00000b00,*(undefined4 *)(lVar50 + 0x18));
          uVar14 = FUN_00bcdbd0();
          FUN_005d8e6c(uVar14,&stack0x000001a0);
          FUN_00bb179c();
          FUN_00c4bf20(&stack0x00000aa0);
          local_290 = plVar18[2];
          FUN_00b1f8a0(plVar18[8],&local_290);
          uVar51 = (ulong)*(uint *)((long)plVar49 + uVar39 * 0x230 + 4);
          uVar76 = 0;
          param_3 = (ulong)*(uint *)(plVar49 + uVar39 * 0x46 + 1);
          param_4 = (ulong)*(uint *)((long)plVar49 + uVar39 * 0x230 + 0xc);
          FUN_00b1f320(plVar18[8],&stack0x00000b00,*(undefined4 *)(lVar50 + 8),&stack0x00000aa0);
          lVar52 = plVar18[3];
          uVar14 = FUN_00baa554(&stack0x000001a0,"ion failed: %d\n");
          FUN_00ba5230(&local_350,uVar14,&stack0x000001a0);
          FUN_00bde9e4();
          iVar86 = (int)in_stack_00000aa8;
          param_9 = (undefined8 *)0x437f0000;
          in_ZR = 0;
          if (iVar86 == local_340) {
            iVar29 = (int)((ulong)in_stack_00000aa8 >> 0x20);
            in_ZR = 0;
            if (iVar29 == local_344) {
              lVar15 = (in_stack_00000aa0 >> 3 & 0x1ff) + 1;
              iVar38 = (int)lVar15;
              in_ZR = 0;
              if (iVar38 == local_33c) {
                in_ZR = local_338 == 2;
                if ((bool)in_ZR) {
                  FUN_007d967c(&stack0x00000aa0,CONCAT44(uStack_34c,local_350),0x80);
                }
                else {
                  in_ZR = local_338 == 1;
                  if ((bool)in_ZR) {
                    FUN_007d9564(&stack0x00000aa0,CONCAT44(uStack_34c,local_350),0x80);
                  }
                  else {
                    lVar41 = plVar18[8];
                    iVar42 = 0;
                    for (iVar28 = 0; in_ZR = iVar28 == iVar86, iVar28 < iVar86; iVar28 = iVar28 + 1)
                    {
                      iVar45 = iVar42;
                      for (iVar43 = 0; lVar61 = lVar15, pfVar56 = (float *)(lVar41 + 0x134),
                          iVar48 = iVar45, iVar43 < iVar29; iVar43 = iVar43 + 1) {
                        for (; lVar61 != 0; lVar61 = lVar61 + -1) {
                          param_3 = (ulong)(uint)pfVar56[-3];
                          param_4 = (ulong)(uint)*pfVar56;
                          uVar51 = 0x437f0000;
                          uVar76 = 0;
                          fVar63 = (float)NEON_ucvtf((uint)*(byte *)(in_stack_00000ab0 + iVar48));
                          *(float *)(CONCAT44(uStack_34c,local_350) + (long)iVar48 * 4) =
                               (fVar63 / 255.0 - pfVar56[-3]) / *pfVar56;
                          pfVar56 = pfVar56 + 1;
                          iVar48 = iVar48 + 1;
                        }
                        iVar45 = iVar45 + iVar38;
                      }
                      iVar42 = iVar42 + iVar29 * iVar38;
                    }
                  }
                }
              }
            }
          }
          FUN_007d8d74();
          FUN_00b969e4(plVar18[3]);
          espresso::Thrustor::Inference();
          lVar15 = FUN_007d8d74();
          pcVar20 = "GanRet does not init.\n";
          auVar81 = FUN_00b96fd0(lVar15,"ion failed: %d\n","GanRet does not init.\n",lVar15 - lVar52
                                );
          __android_log_print(auVar81._0_8_,auVar81._8_8_,pcVar20 + 0xf6c);
          if (*(int *)((long)plVar18 + 0x974) == 0) {
            espresso::Thrustor::getOutput();
          }
          else {
            espresso::Thrustor::Extract((basic_string *)plVar18[3]);
          }
          if (local_370 == 0) {
LAB_00ae5378:
            uVar51 = 0xffffff98;
          }
          else {
            in_ZR = *(int *)((long)plVar18 + 0x974) == 0;
            espresso::Thrustor::Extract((basic_string *)plVar18[3]);
            if (local_390 == 0) goto LAB_00ae5378;
            _iStack00000000000001a0 = CONCAT44(uStack00000000000001a4,0xbf800000);
            FUN_004fdf00(&local_2a8,(long)local_37c << 1,&stack0x000001a0);
            uVar14 = FUN_00bdc2e8(&local_2c0,(long)local_37c,&stack0x000001a0);
            iVar29 = local_37c;
            iVar86 = local_384;
            in_ZR = local_380 * (int)plVar18[2] == local_384 * *(int *)((long)plVar18 + 0x14);
            if (!(bool)in_ZR) {
              pcVar20 = "GanRet does not init.\n";
              auVar81 = FUN_00b96fd0(uVar14,"ion failed: %d\n","GanRet does not init.\n");
              __android_log_print(auVar81._0_8_,auVar81._8_8_,pcVar20 + 0xf9a);
              uVar51 = 0xffffff9b;
              goto LAB_00ae626c;
            }
            piVar26 = (int *)((long)plVar49 + uVar39 * 0x230 + 0x1c4);
            pfVar56 = (float *)(plVar49 + uVar39 * 0x46 + 8);
            lVar52 = CONCAT44(uStack_2a4,local_2a8);
            lVar15 = (long)local_37c;
            fVar63 = (float)iVar80;
            iVar80 = 0;
            if (local_384 != 0) {
              iVar80 = (int)plVar18[2] / local_384;
            }
            lVar41 = CONCAT44(uStack_2bc,local_2c0);
            fVar64 = (float)iVar80;
            iVar80 = local_380 * local_384;
            iVar28 = local_384 + -1;
            iVar42 = local_380 + -1;
            iVar38 = -local_384;
            uVar25 = (ulong)(uint)fVar64;
            for (uVar58 = 0; (long)uVar58 < lVar15; uVar58 = uVar58 + 1) {
              fVar74 = 0.0;
              iVar48 = -1;
              uVar51 = uVar58 & 0xffffffff;
              iVar45 = -1;
              for (iVar43 = 0; iVar8 = local_378, lVar61 = local_390, iVar43 < iVar80;
                  iVar43 = iVar43 + 1) {
                auVar81 = FUN_00b9b518();
                fVar65 = (float)FUN_00aecc00(auVar81._0_8_,auVar81._8_8_,uVar51);
                if (fVar74 < fVar65) {
                  iVar48 = 0;
                  if (iVar86 != 0) {
                    iVar48 = iVar43 / iVar86;
                  }
                  iVar45 = iVar43 + iVar38 * iVar48;
                  fVar74 = fVar65;
                }
                uVar51 = (ulong)(uint)((int)uVar51 + iVar29);
              }
              uVar51 = (ulong)(uint)(float)iVar48;
              pfVar60 = (float *)(lVar52 + uVar58 * 8);
              uVar76 = uVar58 << 1 | 1;
              *pfVar60 = (float)iVar45;
              *(float *)(lVar52 + uVar76 * 4) = (float)iVar48;
              *(float *)(lVar41 + uVar58 * 4) = fVar74;
              if (1 < iVar45) {
                if ((iVar48 < iVar42 && 1 < iVar48) && iVar45 < iVar28) {
                  iVar43 = iVar45 + iVar48 * iVar86;
                  iVar27 = (int)uVar58;
                  fVar65 = (float)FUN_00aecc00(local_390,local_378,iVar27 + (iVar43 + 1) * iVar29);
                  fVar66 = (float)FUN_00aecc00(lVar61,iVar8,iVar27 + (iVar43 + -1) * iVar29);
                  fVar67 = (float)FUN_00aecc00(lVar61,iVar8,
                                               iVar27 + (iVar45 + (iVar48 + 1) * iVar86) * iVar29);
                  fVar68 = (float)FUN_00aecc00(lVar61,iVar8,
                                               iVar27 + (iVar45 + (iVar48 + -1) * iVar86) * iVar29);
                  fVar74 = 0.25;
                  if (0.0 < fVar65 - fVar66) {
LAB_00ae5318:
                    param_3 = (ulong)(uint)*pfVar60;
                    fVar74 = *pfVar60 + fVar74;
                    uVar51 = (ulong)(uint)fVar74;
                    *pfVar60 = fVar74;
                  }
                  else {
                    uVar51 = 0xbe800000;
                    fVar74 = -0.25;
                    if (fVar65 - fVar66 < 0.0) goto LAB_00ae5318;
                  }
                  if (fVar67 - fVar68 <= 0.0) {
                    if (0.0 <= fVar67 - fVar68) goto LAB_00ae5354;
                    uVar51 = 0xbe800000;
                    fVar74 = *(float *)(lVar52 + uVar76 * 4) + -0.25;
                  }
                  else {
                    fVar74 = *(float *)(lVar52 + uVar76 * 4) + 0.25;
                  }
                  *(float *)(lVar52 + uVar76 * 4) = fVar74;
                }
LAB_00ae5354:
                fVar74 = *(float *)(lVar41 + uVar58 * 4);
              }
              uVar76 = 0;
              if (0.0 < fVar74) {
                *(ulong *)pfVar60 =
                     CONCAT44((float)((ulong)*(undefined8 *)pfVar60 >> 0x20) * fVar64,
                              (float)*(undefined8 *)pfVar60 * fVar64);
              }
            }
            if (local_378 != 4) {
              for (lVar52 = 0; lVar52 < lVar15; lVar52 = lVar52 + 1) {
                fVar74 = *(float *)(lVar41 + lVar52 * 4) / (float)(1 << (ulong)(local_374 & 0x1f));
                uVar51 = (ulong)(uint)fVar74;
                uVar76 = 0;
                *(float *)(lVar41 + lVar52 * 4) = fVar74;
              }
            }
            *piVar26 = 0;
            in_stack_00000ca0 = 0;
            if (*(char *)(plVar18[9] + 0x60) == '\0') {
              puVar24 = &UNK_00f0c9d0;
            }
            else {
              puVar24 = &UNK_00f0c9ac;
            }
            auVar81 = FUN_00bb36b0(puVar24);
            in_stack_000001a8 = auVar81._8_8_;
            _iStack00000000000001a0 = auVar81._0_8_;
            in_stack_000001b0 = uVar51;
            in_stack_000001b8 = uVar76;
            in_stack_000001c0 = extraout_w8_00;
            FUN_005e2d10(&stack0x00000ca0,&stack0x000001a0,9);
            *piVar26 = 0;
            fVar74 = (float)FUN_00c0be00(0);
            uVar11 = (uint)extraout_x8_04;
            uVar51 = extraout_x8_04;
            piVar31 = extraout_x10_00;
            for (lVar52 = extraout_x12; lVar52 != 0; lVar52 = lVar52 + -1) {
              uVar11 = (int)uVar51 + (uint)(fVar74 <= *(float *)(extraout_x11 + (long)*piVar31 * 4))
              ;
              uVar51 = (ulong)uVar11;
              piVar31 = piVar31 + 1;
            }
            in_ZR = uVar11 == extraout_w9_00;
            if ((int)extraout_w9_00 <= (int)uVar11) {
              *piVar26 = 1;
            }
            uVar14 = FUN_00bd9438();
            iVar42 = local_354;
            iVar28 = local_358;
            iVar38 = local_35c;
            iVar29 = local_360;
            iVar86 = local_364;
            iVar80 = local_368;
            lVar52 = local_370;
            if (*piVar26 == 0) {
LAB_00ae6268:
              in_stack_00000ca0 = 0;
              uVar51 = 0;
            }
            else if (*(int *)((long)plVar18 + 0x974) == 0) {
              in_ZR = plVar18[5] - plVar18[4] == 8;
              if ((bool)in_ZR) {
                memcpy(pcVar22,plVar18 + 0x5a,0x274);
                uVar14 = FUN_00baa554(&stack0x00000ca0,"ion failed: %d\n");
                FUN_00ba8c9c(&stack0x000001a0,uVar14,&stack0x00000ca0);
                FUN_00bd9420();
                FUN_003e86a0();
                if (_iStack00000000000001a0 == 0) {
                  uVar51 = 0xffffff98;
                }
                else {
                  uVar11 = iVar86 * iVar80 * iVar29 * iVar38;
                  FUN_00b951f4(_iStack00000000000001a0 + 0x274,extraout_x1,
                               -(ulong)(uVar11 >> 0x1f) & 0xfffffffc00000000 | (ulong)uVar11 << 2);
                  in_ZR = 0;
                  __n = 0x274;
                  auVar81 = FUN_00b8e58c();
                  memcpy(auVar81._0_8_,auVar81._8_8_,__n);
                  espresso::Thrustor::Inference();
                  espresso::Thrustor::getOutput();
                  uVar51 = 0xffffff98;
                }
              }
              else {
                pcVar20 = "GanRet does not init.\n";
                auVar81 = FUN_00b96fd0(uVar14,"ion failed: %d\n","GanRet does not init.\n");
                __android_log_print(auVar81._0_8_,auVar81._8_8_,pcVar20 + 0xfdf);
                uVar51 = 0xffffff9b;
              }
            }
            else {
              in_ZR = plVar18[5] - plVar18[4] == 0x18;
              if ((bool)in_ZR) {
                lVar61 = (long)local_35c;
                iVar80 = local_360 * local_364;
                FUN_00bdc2e8(&local_2f0,(long)(iVar80 * local_35c),&stack0x000001a0);
                lVar41 = local_2f0;
                FUN_00bcd5d0(-iVar42);
                fVar74 = (float)FUN_00bd6768();
                lVar15 = extraout_x8_05;
                uVar14 = extraout_x9_04;
                while (lVar15 < lVar61) {
                  pfVar60 = (float *)(lVar41 + (long)(int)uVar14 * 4);
                  for (lVar40 = 0; lVar40 < iVar80; lVar40 = lVar40 + 1) {
                    if (iVar28 == 2) {
                      iVar42 = (int)*(short *)(lVar52 + lVar15 * 2);
LAB_00ae554c:
                      *pfVar60 = fVar74 * (float)iVar42;
                    }
                    else {
                      if (iVar28 == 1) {
                        iVar42 = (int)*(char *)(lVar52 + lVar15);
                        goto LAB_00ae554c;
                      }
                      *pfVar60 = *(float *)(lVar52 + lVar15 * 4);
                    }
                    pfVar60 = pfVar60 + 1;
                    lVar15 = lVar15 + lVar61;
                  }
                  fVar74 = (float)FUN_00bf92e4();
                  lVar15 = extraout_x8_06;
                  uVar14 = extraout_x9_05;
                }
                FUN_007a83cc(local_308,lVar33);
                memcpy(pcVar22,plVar18 + 0x5a,0x274);
                lVar52 = 0;
                pcVar20 = (char *)(ulong)(uint)(int)fVar63;
                do {
                  in_ZR = lVar52 == 3;
                  if ((bool)in_ZR) {
                    uVar51 = 0;
                    goto LAB_00ae5b58;
                  }
                  uVar14 = FUN_003e9458(&stack0x000001a0,"data0");
                  FUN_00ba5230(&local_330,uVar14,&stack0x000001a0);
                  FUN_00bde9e4();
                  uVar14 = FUN_003e9458(&stack0x000001a0,"data1");
                  FUN_00ba5230(&stack0x00000b60,uVar14,&stack0x000001a0);
                  FUN_00bde9e4();
                  uVar51 = local_330;
                  if ((local_330 == 0) || (in_stack_00000b60 == (void *)0x0)) break;
                  memcpy(in_stack_00000b60,pcVar22,0x274);
                  auVar81 = FUN_00bd9420();
                  FUN_00b8cc70(auVar81._0_8_,auVar81._8_8_,0x180);
                  auVar81 = FUN_00bc4410();
                  FUN_00b8cc70(auVar81._0_8_,auVar81._8_8_,0x120);
                  FUN_00b8cc70(&stack0x000001a0,extraout_x1_00,0x360);
                  uVar76 = 0;
                  FUN_00b8f6a4();
                  plVar59 = plVar1;
                  for (lVar15 = lVar52; lVar15 < lVar33; lVar15 = lVar15 + 1) {
                    FUN_007da278((char *)((long)plVar18 + (uVar76 & 0xfffffffe) * 4 + 0x5c),
                                 &stack0x00000ca0 + ((ulong)plVar59 & 0xfffffffc) * 4);
                    plVar59 = (long *)((long)plVar59 + 4);
                    uVar76 = uVar76 + 6;
                  }
                  uVar76 = 0;
                  FUN_00b8f6a4();
                  for (; lVar15 < lVar33; lVar15 = lVar15 + 1) {
                    FUN_007da1f4(&stack0x00000ca0 + (uVar76 & 0xfffffffc) * 4,
                                 (long)&stack0x000001a0 + ((ulong)plVar59 & 0xffffffff) * 4);
                    plVar59 = (long *)((long)plVar59 + 9);
                    uVar76 = uVar76 + 4;
                  }
                  FUN_00bdee3c(&stack0x000001a0,pcVar2,pcVar3,&stack0x00000b80,iVar46);
                  FUN_008440ac(local_2d8,lVar33);
                  uVar76 = 0;
                  for (lVar15 = 0; lVar15 < lVar33; lVar15 = lVar15 + 1) {
                    puVar23 = (undefined4 *)((long)local_2d8[0] + uVar76 * 4);
                    iVar28 = (int)uVar76;
                    *puVar23 = *(undefined4 *)(&stack0x00000b80 + (uVar76 & 0xffffffff) * 4);
                    uVar76 = uVar76 + 3;
                    puVar23[1] = *(undefined4 *)(&stack0x00000b80 + (ulong)(iVar28 + 1) * 4);
                    puVar23[2] = *(undefined4 *)(&stack0x00000b80 + (ulong)(iVar28 + 2) * 4);
                  }
                  FUN_00b96e18(&stack0x00000e88,3);
                  lVar15 = FUN_00bcfdb0();
                  FUN_005b95f0(extraout_x8_07 + 0x368,lVar15 + 0x3c8);
                  uVar14 = FUN_00bd9464();
                  iVar28 = (int)plVar18[2];
                  if ((int)plVar18[2] <= *(int *)((long)plVar18 + 0x14)) {
                    iVar28 = *(int *)((long)plVar18 + 0x14);
                  }
                  FUN_00bfc538(iVar28,uVar14,&stack0x00000ac0);
                  param_9 = local_308;
                  local_270 = 0;
                  FUN_00ae3390((float)(int)fVar63,local_2d8,extraout_x1_01 + 0x368,&stack0x00000e88,
                               &local_270,param_9);
                  FUN_00bffb3c();
                  FUN_003e8658(local_2d8);
                  lVar15 = local_2f0;
                  FUN_0079fe98(&local_288,local_308);
                  FUN_00b8d834();
                  iVar28 = 0;
                  if (extraout_w12 != 0) {
                    iVar28 = extraout_w10 / extraout_w12;
                  }
                  iVar42 = 0;
                  if (extraout_w12 != 0) {
                    iVar42 = extraout_w11 / extraout_w12;
                  }
                  fVar74 = (float)iVar28;
                  uVar58 = (ulong)(uint)(float)iVar42;
                  lVar41 = extraout_x8_08;
                  uVar76 = extraout_x9_06;
                  while (uVar76 < (ulong)(local_280 - CONCAT44(uStack_284,local_288) >> 3)) {
                    pfVar60 = (float *)(CONCAT44(uStack_284,local_288) + lVar41);
                    *pfVar60 = ((*pfVar60 / fVar74 + 1.0) * (float)iVar86 + -1.0) * 0.5;
                    fVar74 = pfVar60[1] / (float)uVar58 + 1.0;
                    param_4 = (ulong)(uint)fVar74;
                    param_3 = (ulong)(uint)((fVar74 * (float)iVar29 + -1.0) * 0.5);
                    fVar74 = (float)FUN_00c05b60();
                    lVar41 = extraout_x8_09 + 8;
                    uVar76 = extraout_x9_07;
                  }
                  FUN_00bde480();
                  uVar58 = extraout_x8_10;
                  lVar40 = extraout_x10_02;
                  lVar41 = extraout_x11_00;
                  for (uVar76 = extraout_x9_08; uVar44 = lVar40 - lVar41 >> 3,
                      in_ZR = uVar76 == uVar44, uVar76 < uVar44; uVar76 = uVar76 + 1) {
                    pfVar60 = (float *)(lVar41 + uVar76 * 8);
                    fVar74 = *pfVar60;
                    fVar65 = pfVar60[1];
                    uVar44 = (ulong)fVar74;
                    iVar45 = (int)(long)fVar65;
                    iVar43 = (int)uVar44;
                    iVar28 = iVar43 + 1;
                    iVar42 = iVar45 + 1;
                    fVar66 = (fVar74 - (float)iVar43) * ((float)iVar42 - fVar65);
                    param_3 = (ulong)(uint)fVar66;
                    fVar67 = ((float)iVar28 - fVar74) * (fVar65 - (float)iVar45);
                    param_4 = (ulong)(uint)fVar67;
                    for (lVar41 = 0; lVar41 < lVar61; lVar41 = lVar41 + 1) {
                      fVar68 = 0.0;
                      iVar48 = (int)uVar44;
                      if (-1 < iVar45) {
                        if (iVar43 < iVar86 && (-1 < iVar43 && iVar45 < iVar29)) {
                          fVar68 = ((float)iVar28 - fVar74) * ((float)iVar42 - fVar65) *
                                   *(float *)(lVar15 + (long)(iVar29 * iVar45 + iVar48) * 4) + 0.0;
                        }
                        if (iVar28 < iVar86 && (-2 < iVar43 && iVar45 < iVar29)) {
                          fVar68 = fVar68 + fVar66 * *(float *)(lVar15 + (long)(iVar29 * iVar45 +
                                                                                iVar48 + 1) * 4);
                        }
                      }
                      if (-2 < iVar45) {
                        if (iVar43 < iVar86 && (-1 < iVar43 && iVar42 < iVar29)) {
                          fVar68 = fVar68 + fVar67 * *(float *)(lVar15 + (long)(iVar29 * iVar42 +
                                                                               iVar48) * 4);
                        }
                        if (iVar28 < iVar86 && (-2 < iVar43 && iVar42 < iVar29)) {
                          fVar68 = fVar68 + (fVar74 - (float)iVar43) * (fVar65 - (float)iVar45) *
                                            *(float *)(lVar15 + (long)(iVar29 * iVar42 + iVar48 + 1)
                                                                * 4);
                        }
                      }
                      *(float *)(uVar51 + (long)(int)uVar58 * 4 + lVar41 * 4) = fVar68;
                      uVar44 = (ulong)(uint)(iVar48 + iVar80);
                    }
                    lVar41 = CONCAT44(uStack_284,local_288);
                    uVar58 = (ulong)(uint)((int)uVar58 + iVar38);
                    lVar40 = local_280;
                  }
                  FUN_003e8658(&local_288);
                  espresso::Thrustor::Inference();
                  espresso::Thrustor::getOutput();
                  bVar10 = false;
                  if (_iStack00000000000001a0 != 0) {
                    lVar15 = 0;
                    uVar51 = _iStack00000000000001a0;
                    pcVar20 = pcVar22;
                    while (lVar15 != 0x274) {
                      FUN_00bcc954(*(float *)(uVar51 + lVar15) + *(float *)(pcVar20 + lVar15),
                                   *(float *)(pcVar20 + lVar15));
                      uVar51 = extraout_x8_11;
                      lVar15 = extraout_x9_09;
                      pcVar20 = extraout_x10_03;
                    }
                    bVar10 = true;
                    in_ZR = 1;
                  }
                  lVar52 = lVar52 + 1;
                  pcVar20 = (char *)plVar18;
                  in_stack_00000e88 = extraout_x10_01;
                } while (bVar10);
                uVar51 = 0xffffff98;
LAB_00ae5b58:
                FUN_003e8658(local_308);
                FUN_003e8658(&local_2f0);
                if ((int)uVar51 == 0) {
                  iVar86 = (int)plVar18[0x12a];
                  uVar69 = *(undefined4 *)((long)plVar18 + (long)iVar86 * 4 + 0x5c);
                  fVar74 = *(float *)((long)plVar18 + (long)iVar86 * 4 + 0x60);
                  lVar52 = plVar18[2];
                  fVar65 = *(float *)((long)plVar18 + (long)iVar86 * 4 + 100);
                  *pfVar56 = fVar74;
                  pfVar60 = (float *)((long)plVar49 + uVar39 * 0x230 + 0x44);
                  *pfVar60 = *(float *)((long)plVar18 + (long)iVar86 * 4 + 100);
                  iVar80 = (int)lVar52;
                  iVar29 = (int)((ulong)lVar52 >> 0x20);
                  in_ZR = iVar29 == iVar80;
                  if (iVar80 <= iVar29) {
                    iVar80 = iVar29;
                  }
                  dVar70 = (double)FUN_00c08204(iVar80,*(undefined4 *)
                                                        ((long)plVar18 + (long)iVar86 * 4 + 0x5c),
                                                uVar16);
                  pfVar55 = (float *)(plVar49 + uVar39 * 0x46 + 9);
                  *pfVar55 = (float)dVar70;
                  FUN_00c4bf20(&stack0x00000e28);
                  puVar53 = (uint *)plVar18[8];
                  auVar81 = FUN_00bd6558();
                  pcVar21 = "GetTransMat - empty transform matrix\n";
                  if ((auVar81._0_8_ & 1) == 0) {
                    pcVar21 = "GetTransMat - incorrect data format\n";
                    in_ZR = (*puVar53 & 0xfff) == 6;
                    if (!(bool)in_ZR) goto LAB_00ae5dc4;
                    pcVar21 = "GetTransMat - incorrect matrix shape\n";
                    in_ZR = puVar53[2] == 3;
                    if (!(bool)in_ZR) goto LAB_00ae5dc4;
                    pcVar21 = "GetTransMat - incorrect matrix shape\n";
                    in_ZR = puVar53[3] == 3;
                    if (!(bool)in_ZR) goto LAB_00ae5dc4;
                    FUN_00b9c960(&stack0x000001a0,puVar53);
                    uVar14 = FUN_00b99498(_iStack00000000000001a0);
                    FUN_00c096c4(uVar14,&stack0x000001a0,&stack0x00000e28);
                    FUN_00bc00bc();
                    lVar15 = plVar18[8];
                    dVar70 = *in_stack_00000e38;
                    dVar71 = *(double *)((long)in_stack_00000e38 + *in_stack_00000e70 + 8);
                    uVar14 = NEON_scvtf(lVar52,4);
                    FUN_00c296ec(&stack0x000001a0,1,3,6);
                    FUN_005b95f0(&stack0x00000b80,&stack0x000001a0);
                    FUN_00bc00bc();
                    in_stack_00000b90[1] = (double)((float)((ulong)uVar14 >> 0x20) * 0.5);
                    *in_stack_00000b90 = (double)((float)uVar14 * 0.5);
                    FUN_00b9c960(&stack0x00000e88,lVar15);
                    lVar52 = FUN_00bcfdb0();
                    FUN_00c26c14(extraout_x8_12 + 0x1e0,lVar52 + 0x3c8);
                    auVar81 = FUN_00b9cd04();
                    FUN_00c273a4(&stack0x000001a0,auVar81._0_8_ + 0xc0,auVar81._8_8_ + 0x1e0);
                    uVar14 = FUN_00b99498(_iStack00000000000001a0);
                    FUN_00c096c4(uVar14,&stack0x000001a0,&stack0x00000b80);
                    uVar14 = NEON_rev64(in_stack_00000b08,4);
                    FUN_005904a8(&stack0x000001a0);
                    FUN_00bd942c();
                    FUN_00bd9464();
                    FUN_00bc4410(in_stack_00000b90);
                    auVar81 = *extraout_x8_13;
                    FUN_00c4c350();
                    uVar51 = 0;
                    uVar14 = NEON_scvtf(uVar14,4);
                    fVar66 = *pfVar55 / (float)((dVar70 + dVar71) * 0.5);
                    *pfVar55 = fVar66;
                    fVar67 = (float)auVar81._0_8_ + (float)uVar14 * -0.5;
                    fVar68 = (float)auVar81._8_8_ + (float)((ulong)uVar14 >> 0x20) * -0.5;
                    param_3 = CONCAT44(fVar68,fVar67);
                    param_4 = (ulong)(uint)fVar63;
                    fVar66 = fVar66 / fVar63;
                    *(long *)pfVar56 =
                         CONCAT44((float)((ulong)*(long *)pfVar56 >> 0x20) + fVar68 * fVar66,
                                  (float)*(long *)pfVar56 + fVar67 * fVar66);
                  }
                  else {
LAB_00ae5dc4:
                    pcVar20 = "SMASH_E_LOG ";
                    FUN_00be1a48(auVar81._0_8_,auVar81._8_8_,pcVar21);
                    __android_log_print(6,"SMASH_E_LOG ",
                                        "estimate_origin_3d_ - failed to get trans\n");
                    uVar51 = 0xffffff9b;
                  }
                  FUN_00bffb3c();
                  if ((int)uVar51 == 0) {
                    FUN_00b92894(uVar69,uVar16);
                    auVar81 = FUN_00c08204();
                    lVar52 = 0;
                    uVar14 = extraout_x1_02;
                    while (0 < lVar33) {
                      FUN_007da278((char *)((long)plVar18 + ((ulong)pcVar20 & 0xfffffffe) * 4 + 0x5c
                                           ),lVar34 + (uVar51 & 0xfffffffc) * 4);
                      FUN_00c0a120();
                      pcVar20 = (char *)((long)pcVar20 + 6);
                      uVar14 = extraout_x1_03;
                    }
                    FUN_00b8cc70(&stack0x000001a0,uVar14,0x900);
                    uVar76 = 0;
                    FUN_00b8fa50();
                    for (; lVar52 < lVar33; lVar52 = lVar52 + 1) {
                      FUN_007da1f4(lVar34 + (uVar76 & 0xfffffffc) * 4,
                                   (long)&stack0x000001a0 + (uVar51 & 0xffffffff) * 4);
                      uVar51 = uVar51 + 9;
                      uVar76 = uVar76 + 4;
                    }
                    FUN_00bf57e8(&stack0x000001a0,pcVar2,pcVar3);
                    FUN_00bdee3c();
                    FUN_00c01a24();
                    FUN_008440ac();
                    FUN_007a83cc(&stack0x00000b60,lVar33);
                    FUN_00ba5240();
                    uVar51 = extraout_x8_14;
                    for (lVar34 = extraout_x10_04; lVar34 < lVar33; lVar34 = lVar34 + 1) {
                      pfVar4 = (float *)(extraout_x9_10 + uVar51 * 4);
                      iVar80 = (int)uVar51;
                      *pfVar4 = fVar74 + *(float *)(lVar35 + (uVar51 & 0xffffffff) * 4);
                      uVar51 = uVar51 + 3;
                      pfVar4[1] = fVar65 + *(float *)(lVar35 + (ulong)(iVar80 + 1) * 4);
                      pfVar4[2] = *(float *)(lVar35 + (ulong)(iVar80 + 2) * 4) +
                                  (float)auVar81._0_8_;
                    }
                    FUN_00b96e18(&stack0x00000ca0,3);
                    lVar34 = FUN_00bcfdb0();
                    FUN_005b95f0(extraout_x8_15 + 0x3c8,lVar34 + 0x1e0);
                    FUN_00bd942c();
                    uVar14 = NEON_scvtf(plVar18[2],4);
                    local_308[0] = CONCAT44((float)((ulong)uVar14 >> 0x20) * 0.5,(float)uVar14 * 0.5
                                           );
                    param_9 = (undefined8 *)&stack0x00000b60;
                    local_2e8 = 0;
                    local_2f0 = 0;
                    FUN_00ae3390(&local_330,&stack0x00000e88,&local_2f0,local_308,param_9);
                    for (lVar34 = 0; lVar34 < lVar33; lVar34 = lVar34 + 1) {
                      *(undefined4 *)(extraout_x9_03 + lVar34 * 4) = 1;
                    }
                    if ((int)lVar32 == 0) {
                      FUN_00b9e164(&UNK_00f0ca68);
                      lVar32 = FUN_00b9cd04();
                      in_stack_00000b88 = extraout_q0._8_8_;
                      in_stack_00000b80 = extraout_q0._0_8_;
                      FUN_00446a18(lVar32 + 0x1e0,extraout_x1_04 + 0xc0,6);
                      FUN_00b9d0f4(0);
                      piVar26 = extraout_x8_17;
                      for (lVar32 = extraout_x9_11; lVar32 != 0; lVar32 = lVar32 + -1) {
                        *(undefined4 *)(extraout_x9_03 + (long)*piVar26 * 4) = 0;
                        piVar26 = piVar26 + 1;
                      }
                      FUN_00bd9438();
                      in_stack_00000b90 = extraout_x8_16;
                    }
                    FUN_004fdf00(local_2d8,(long)(iVar46 << 1),&stack0x00000ca0);
                    FUN_00b8d834();
                    lVar32 = extraout_x8_18;
                    lVar34 = extraout_x9_12;
                    while (lVar34 < lVar33) {
                      *(undefined4 *)((long)local_2d8[0] + lVar32) =
                           *(undefined4 *)((long)in_stack_00000b60 + lVar32);
                      FUN_00bf02f8();
                      lVar32 = extraout_x8_19;
                      lVar34 = extraout_x9_13;
                    }
                    FUN_00be5b0c(&UNK_00f0ca80);
                    pvVar13 = local_2d8[0];
                    auVar81 = FUN_00b9cd04();
                    FUN_00c02684(auVar81._0_8_ + 0xc0,auVar81._8_8_ + 0x1e0);
                    FUN_00446a18();
                    in_stack_00000e38 = (double *)0x0;
                    if (*(char *)(plVar18[9] + 0x60) == '\0') {
                      puVar24 = &UNK_00f0cad0;
                    }
                    else {
                      puVar24 = &UNK_00f0caa8;
                    }
                    FUN_00be5b0c(puVar24);
                    FUN_00c02684(&stack0x00000e28,&stack0x00000ca0);
                    FUN_005e2d10();
                    lVar32 = 0;
                    while ((long)(in_stack_00000b88 - in_stack_00000b80) >> 2 != lVar32) {
                      iVar46 = *(int *)(in_stack_00000b80 + lVar32 * 4);
                      fVar74 = *(float *)((long)pvVar13 + (long)(int)(iVar46 << 1 | 1) * 4);
                      iVar80 = (int)(*(float *)((long)pvVar13 + (long)iVar46 * 8) / fVar64);
                      fVar63 = 0.0;
                      *(undefined4 *)(lVar36 + (long)iVar46 * 4) = 0;
                      if (((-1 < iVar80) && (iVar46 = (int)(fVar74 / fVar64), -1 < iVar46)) &&
                         (iVar80 < local_384 && iVar46 < local_380)) {
                        lVar34 = (long)*(int *)(lVar32 * 4) +
                                 (long)local_37c * (long)(iVar80 + local_384 * iVar46);
                        if (local_378 == 1) {
                          iVar46 = (int)*(char *)(local_390 + lVar34);
                        }
                        else {
                          iVar46 = (int)lVar34;
                          if (local_378 != 2) {
                            fVar63 = *(float *)(local_390 + (long)iVar46 * 4);
                            goto LAB_00ae61b4;
                          }
                          iVar46 = (int)*(short *)(local_390 + (long)iVar46 * 2);
                        }
                        fVar63 = (float)iVar46 / (float)(1 << (ulong)(local_374 & 0x1f));
                      }
LAB_00ae61b4:
                      *(float *)(lVar36 + (long)*(int *)(in_stack_00000b80 + lVar32 * 4) * 4) =
                           fVar63;
                      lVar34 = lVar32 * 4;
                      lVar32 = lVar32 + 1;
                      uVar69 = 0;
                      if (*(float *)(plVar18[9] + 0x58) <= fVar63) {
                        uVar69 = 0x3f800000;
                      }
                      *(undefined4 *)(lVar37 + (long)*(int *)(in_stack_00000b80 + lVar34) * 4) =
                           uVar69;
                    }
                    FUN_003e8658(&stack0x00000e28);
                    FUN_00bd9414();
                    uVar11 = 2;
                    for (lVar32 = 0; in_ZR = lVar32 == lVar33, lVar32 < lVar33; lVar32 = lVar32 + 1)
                    {
                      *(float *)(lVar35 + (ulong)(uVar11 - 2) * 4) =
                           *pfVar56 + *(float *)(lVar35 + (ulong)(uVar11 - 2) * 4);
                      *(float *)(lVar35 + (ulong)(uVar11 - 1) * 4) =
                           *pfVar60 + *(float *)(lVar35 + (ulong)(uVar11 - 1) * 4);
                      *(float *)(lVar35 + (ulong)uVar11 * 4) =
                           *pfVar55 + *(float *)(lVar35 + (ulong)uVar11 * 4);
                      uVar11 = uVar11 + 3;
                    }
                    FUN_00bfdb6c();
                    FUN_00bcdbc4();
                    FUN_00c4c350();
                    FUN_003e8658(&stack0x00000b60);
                    FUN_003e8658(&local_330);
                    param_3 = uVar25;
                    goto LAB_00ae6268;
                  }
                }
              }
              else {
                pcVar20 = "r_fc_list size\n";
                auVar81 = FUN_00b96fd0(uVar14,"ion failed: %d\n","r_fc_list size\n");
                __android_log_print(auVar81._0_8_,auVar81._8_8_,pcVar20 + 0x10);
                uVar51 = 0xffffff9b;
              }
            }
LAB_00ae626c:
            FUN_003e8658(&local_2c0);
            FUN_003e8658(&local_2a8);
          }
          FUN_00c4c350(&stack0x00000aa0);
          FUN_00bcdbd0();
          FUN_00c4c350();
          if ((int)uVar51 != 0) goto LAB_00ae8960;
          *(undefined4 *)(plVar18[0x13b] + uVar39 * 0x230 + 0x1c8) = 1;
          FUN_00bed930();
          *(uint *)(extraout_x8_20 + 0x1d0) = extraout_w12_00 == 0 & extraout_w9_01;
          if (((extraout_w12_00 != 0) && ((int)plVar18[0x12f] != 0)) &&
             (*(int *)(*plVar1 + uVar39 * 0x230 + 0x1c4) != 0)) {
            for (lVar32 = 0; lVar32 != 8; lVar32 = lVar32 + 4) {
              auVar81 = *(undefined1 (*) [16])
                         (&local_260 + (long)*(int *)(extraout_x11_01 + lVar32) * 2);
              puVar19 = (undefined8 *)
                        (*(long *)(*plVar1 + uVar39 * 0x230 + 0x28) +
                        (long)*(int *)(extraout_x11_01 + lVar32) * 0x10);
              puVar19[1] = auVar81._8_8_;
              *puVar19 = auVar81._0_8_;
            }
          }
          if (((extraout_w12_00 != 0) && ((int)plVar18[0x14b] != 0)) &&
             (lVar32 = *plVar1, *(int *)(lVar32 + uVar39 * 0x230 + 0x1c4) != 0)) {
            lVar34 = lVar32 + uVar39 * 0x230;
            pvVar13 = *(void **)(lVar34 + 0x28);
            lVar33 = 0x948;
            if ((int)plVar18[0x12f] != 0) {
              lVar33 = 0x944;
            }
            lVar33 = (long)*(int *)((long)plVar18 + lVar33);
            uVar51 = *(long *)(lVar34 + 0x30) - (long)pvVar13 >> 2;
            in_ZR = uVar51 == lVar33 * 4;
            if (uVar51 < (ulong)(lVar33 * 4)) {
              pcVar22 = "cri - incorrect size\n";
              goto LAB_00ae7904;
            }
            lVar34 = 0;
            uVar14 = 0;
            auVar81 = ZEXT416(*(uint *)(plVar18[9] + 0x80));
            uVar51 = (ulong)*(uint *)(plVar18[9] + 0x7c);
            while (lVar34 != 0x10) {
              param_3 = (ulong)*(uint *)((long)&local_260 + lVar34);
              param_4 = (ulong)*(uint *)((long)pvVar13 + lVar34);
              pvVar13 = (void *)FUN_00ba964c();
              lVar32 = extraout_x8_21;
              lVar33 = extraout_x9_14;
              uVar51 = extraout_x10_05;
              lVar34 = extraout_x11_02;
              auVar81 = extraout_q0_00;
            }
            if ((auVar81._0_4_ <= ABS((float)uVar14)) ||
               ((int)uVar51 <= *(int *)(lVar32 + uVar39 * 0x230 + 0x1d4))) {
              *(undefined4 *)(lVar32 + uVar39 * 0x230 + 0x1d4) = 0;
            }
            else {
              memcpy(pvVar13,&local_260,lVar33 << 4);
              FUN_00b99400();
            }
          }
        }
        uVar39 = uVar39 + 1;
      }
      FUN_007b6d44(&local_390);
      fVar63 = DAT_00d99140;
      uVar16 = DAT_00d94280;
      lVar50 = DAT_00d94278;
      uVar58 = 0x3f0000003f000000;
      NEON_scvtf(CONCAT44(iVar12,iVar30),4);
      uVar76 = 0;
      pfVar56 = (float *)0x230;
      uVar51 = uVar39;
      while( true ) {
        fVar64 = (float)param_4;
        fVar65 = (float)param_3;
        fVar74 = (float)uVar58;
        lVar32 = plVar18[0x13b];
        uVar39 = (plVar18[0x13c] - lVar32) / 0x230;
        in_ZR = uVar76 == uVar39;
        if (uVar39 <= uVar76) break;
        if ((*(int *)(lVar32 + uVar76 * 0x230 + 0x1c8) != 0) &&
           (*(int *)(lVar32 + uVar76 * 0x230 + 0x1c4) != 0)) {
          if (*(int *)(plVar18[9] + 0xc) == 0) {
            pcVar22 = pcVar3;
            uVar39 = FUN_00ae8fd8(lVar32 + uVar76 * 0x230,*(undefined4 *)((long)plVar18 + 0x944),
                                  pcVar2,pcVar3);
            if ((int)uVar39 == 0) {
              uVar11 = *(uint *)((long)plVar18 + 0x944);
              uVar51 = (ulong)(int)uVar11;
              uVar9 = 0x40 < uVar11;
              in_ZR = uVar11 == 0x41;
              plVar49 = plVar18;
              if (0x40 < (int)uVar11) {
                pcVar22 = "GanRet does not init.\n";
                auVar81 = FUN_00b96fd0(uVar39,"ion failed: %d\n","GanRet does not init.\n");
                __android_log_print(auVar81._0_8_,auVar81._8_8_,pcVar22 + 0xeec);
                goto LAB_00ae694c;
              }
              lVar32 = plVar18[0x13b];
              iVar46 = *(int *)(plVar18[9] + 0x88);
              in_stack_00000ca0 = 0;
              if (uVar11 != 0) {
                uVar14 = FUN_00bd9420();
                FUN_007a913c(uVar14,uVar51);
                uVar25 = 0;
                uVar39 = uVar51 * 0xc;
                while (uVar9 = uVar25 <= uVar39, uVar39 != uVar25) {
                  FUN_00bdb8f4();
                  uVar25 = extraout_x8_24;
                  uVar39 = extraout_x9_16;
                }
                in_ZR = true;
              }
              FUN_00bffc14();
              _iStack00000000000001a0 = 0;
              FUN_00ae9a1c();
              plVar59 = (long *)(lVar32 + uVar76 * 0x230 + 0x50);
              uVar14 = FUN_00c0ce90(*plVar59);
              if ((bool)uVar9 && !(bool)in_ZR) {
LAB_00ae691c:
                pcVar22 = "GanRet does not init.\n";
                auVar81 = FUN_00b96fd0(uVar14,"ion failed: %d\n","GanRet does not init.\n");
                __android_log_print(auVar81._0_8_,auVar81._8_8_,pcVar22 + 0xf07);
                uVar51 = 0xffffff9b;
              }
              else {
                lVar33 = lVar32 + uVar76 * 0x230;
                plVar57 = (long *)(lVar33 + 0x80);
                uVar39 = *(long *)(lVar33 + 0x88) - *plVar57 >> 2;
                bVar10 = uVar39 <= uVar51;
                in_ZR = uVar51 == uVar39;
                if (bVar10 && !(bool)in_ZR) goto LAB_00ae691c;
                plVar54 = (long *)(lVar32 + uVar76 * 0x230 + 0xb0);
                uVar14 = FUN_00c0ce90(*plVar54);
                if ((bVar10 && !(bool)in_ZR) ||
                   (lVar33 = lVar32 + uVar76 * 0x230,
                   uVar39 = *(long *)(lVar33 + 0xd0) - *(long *)(lVar33 + 200) >> 2,
                   in_ZR = uVar51 == uVar39, uVar39 <= uVar51 && !(bool)in_ZR)) goto LAB_00ae691c;
                lVar33 = lVar32 + uVar76 * 0x230;
                plVar49 = (long *)(lVar33 + 0xe0);
                in_ZR = *(long *)(lVar33 + 0xe8) - *plVar49 == 0xc;
                if (!(bool)in_ZR) goto LAB_00ae691c;
                lVar33 = lVar32 + uVar76 * 0x230;
                plVar47 = (long *)(lVar33 + 0xf8);
                in_ZR = *(long *)(lVar33 + 0x100) - *plVar47 == 4;
                if (((!(bool)in_ZR) ||
                    (lVar33 = lVar32 + uVar76 * 0x230,
                    in_ZR = *(long *)(lVar33 + 0x118) - *(long *)(lVar33 + 0x110) == 8, !(bool)in_ZR
                    )) || (lVar32 = lVar32 + uVar76 * 0x230,
                          in_ZR = *(long *)(lVar32 + 0x130) - *(long *)(lVar32 + 0x128) == 4,
                          !(bool)in_ZR)) goto LAB_00ae691c;
                FUN_00b955ac();
                uVar39 = extraout_x9_17;
                for (lVar32 = extraout_x10_07; lVar32 < (long)uVar51; lVar32 = lVar32 + 1) {
                  *(undefined4 *)(uVar39 * 4) =
                       *(undefined4 *)(*plVar59 + (uVar39 & 0xffffffff) * 4);
                  iVar80 = (int)uVar39;
                  *(undefined4 *)(uVar39 * 4 + 4) =
                       *(undefined4 *)(*plVar59 + (ulong)(iVar80 + 1) * 4);
                  lVar33 = uVar39 * 4;
                  uVar39 = uVar39 + 3;
                  *(undefined4 *)(lVar33 + 8) = *(undefined4 *)(*plVar59 + (ulong)(iVar80 + 2) * 4);
                  *(undefined4 *)(*extraout_x8_25 + lVar32 * 4) =
                       *(undefined4 *)(*plVar57 + lVar32 * 4);
                }
                in_stack_000001a8 = in_stack_000001a8 & 0xffffffff00000000;
                _iStack00000000000001a0 = 0;
                FUN_007a8490(&stack0x00000ca0,&stack0x000001a0);
                param_3 = 0x40a00000;
                uVar69 = FUN_00bece10(0,*(undefined4 *)(*plVar59 + 0xb4),
                                      *(undefined4 *)(*plVar59 + 0x90));
                *(undefined4 *)(extraout_x8_27 + extraout_x9_19 * 4) = uVar69;
                uVar69 = FUN_00bece10(extraout_x9_19 * 4,*(undefined4 *)(*plVar59 + 0xb8),
                                      *(undefined4 *)(*plVar59 + 0x94));
                *(undefined4 *)(extraout_x8_28 + 4) = uVar69;
                uVar58 = (ulong)*(uint *)(*plVar59 + 0x98);
                uVar69 = FUN_00bece10(extraout_x9_20 * 4);
                *(undefined4 *)(extraout_x8_29 + 8) = uVar69;
                *(undefined4 *)*plVar49 = *(undefined4 *)(extraout_x9_21 * 4);
                *(undefined4 *)(*plVar49 + 4) = *(undefined4 *)(extraout_x9_21 * 4 + 4);
                *(undefined4 *)(*plVar49 + 8) = *(undefined4 *)(extraout_x9_21 * 4 + 8);
                if (*(int *)(*plVar57 + 0x30) == 0) {
                  uVar69 = 0;
                }
                else {
                  FUN_00b95ebc(*(undefined4 *)(*plVar57 + 0x3c));
                  uVar69 = extraout_w8_02;
                }
                *(undefined4 *)*plVar47 = uVar69;
                **(undefined4 **)(extraout_x12_00 + 0x128) = *(undefined4 *)*plVar47;
                FUN_00c29780(&stack0x000001a0,3,3,5);
                FUN_005b95f0(&local_260,&stack0x000001a0);
                FUN_00bc00bc();
                puVar17 = &stack0x00000ac0;
                lVar32 = FUN_00c0311c(&stack0x00000ac0,extraout_x1_05,puVar17);
                param_9 = param_9 + 0x79;
                in_stack_00000b80 = 0;
                FUN_00ae3390((float)iVar46,lVar32 + 0x1e0,&local_260,puVar17 + 0xc0,pcVar22 + 0x368,
                             param_9);
                FUN_00b8d834();
                lVar32 = extraout_x8_30;
                uVar39 = extraout_x9_22;
                while (in_ZR = uVar39 == uVar51, (long)uVar39 < (long)uVar51) {
                  *(undefined4 *)(*plVar54 + lVar32) = *(undefined4 *)(in_stack_00000e88 + lVar32);
                  FUN_00bf02f8();
                  lVar32 = extraout_x8_31;
                  uVar39 = extraout_x9_23;
                }
                *(undefined4 *)*extraout_x11_04 = *(undefined4 *)(in_stack_00000e88 + uVar51 * 8);
                *(undefined4 *)(*extraout_x11_04 + 4) =
                     *(undefined4 *)(in_stack_00000e88 + uVar51 * 8 + 4);
                FUN_00c4c350(&local_260);
                uVar51 = 0;
              }
              FUN_00bcdbc4();
              FUN_003e8658();
              FUN_00bd9420();
              FUN_003e8658();
              if ((int)uVar51 == 0) goto LAB_00ae694c;
            }
            else {
              uVar51 = uVar39 & 0xffffffff;
            }
          }
          else {
            _iStack00000000000001a0 = _iStack00000000000001a0 & 0xffffffff00000000;
            FUN_004fdf00(local_2d8,(long)*(int *)((long)plVar18 + 0x944) << 2,&stack0x000001a0);
            memcpy(local_2d8[0],*(void **)(plVar18[0x13b] + uVar76 * 0x230 + 0x28),
                   (long)*(int *)((long)plVar18 + 0x944) << 4);
            lVar32 = *plVar1 + uVar76 * 0x230;
            uVar11 = FUN_00aeb644(*(undefined8 *)(lVar32 + 0x200),*(undefined8 *)(lVar32 + 0x28),
                                  lVar32 + 0x40,*(undefined8 *)(lVar32 + 0x28),lVar32 + 0x40);
            if (uVar11 == 0) {
              uStack_258 = 0;
              local_260 = 0;
              uStack_248 = 0;
              local_250 = 0;
              uVar51 = (ulong)DAT_00ec2190;
              uVar11 = FUN_00ae8e6c(plVar18,*(undefined8 *)(plVar18[0x13b] + uVar76 * 0x230 + 0x220)
                                    ,&local_260,&stack0x00000ca0,4);
              if (uVar11 == 0) {
                iVar12 = *(int *)((long)plVar18 + 0x944);
                lVar32 = *(long *)(plVar18[0x13b] + uVar76 * 0x230 + 0x28);
                local_2f0 = lVar50;
                FUN_00446a18(&stack0x00000b80,&local_2f0,2);
                _iStack00000000000001a0 = CONCAT44(uStack00000000000001a4,0x12);
                FUN_004494d0(&stack0x000001a8,&stack0x00000b80);
                local_308[0] = uVar16;
                FUN_00446a18(&stack0x00000e28,local_308,2);
                in_stack_000001c0 = 0x13;
                FUN_004494d0(&stack0x000001c8,&stack0x00000e28);
                uVar16 = FUN_00bcdbd0();
                local_288 = 0x12;
                FUN_00446a18(uVar16,&local_288,1);
                in_stack_000001e0 = 0x14;
                FUN_004494d0(&stack0x000001e8,&stack0x00000b00);
                local_2a8 = 0x13;
                FUN_00446a18(&stack0x00000aa0,&local_2a8,1);
                FUN_004494d0(&stack0x00000208,&stack0x00000aa0);
                local_2c0 = 4;
                FUN_00446a18(&local_330,&local_2c0,1);
                FUN_004494d0(&stack0x00000228,&local_330);
                local_270 = CONCAT44(local_270._4_4_,5);
                FUN_00446a18(&stack0x00000b60,&local_270,1);
                FUN_004494d0(&stack0x00000248,&stack0x00000b60);
                local_290 = CONCAT44(local_290._4_4_,1);
                FUN_00446a18(&local_350,&local_290,1);
                FUN_004494d0(&stack0x00000268,&local_350);
                local_264 = 2;
                FUN_00446a18(&local_370,&local_264,1);
                FUN_004494d0(&stack0x00000288,&local_370);
                for (lVar50 = 0; lVar50 != 0x100; lVar50 = lVar50 + 0x20) {
                  FUN_004cc968(&stack0x00000e88,&stack0x00000e90,(long)&stack0x000001a0 + lVar50);
                }
                lVar50 = 0xe8;
                do {
                  FUN_003e8658((long)&stack0x000001a0 + lVar50);
                  lVar50 = lVar50 + -0x20;
                } while (lVar50 != -0x18);
                FUN_003e8658(&local_370);
                FUN_00bced40();
                FUN_003e8658(&stack0x00000b60);
                FUN_00bda618();
                FUN_003e8658(&stack0x00000aa0);
                FUN_00bcdbd0();
                FUN_003e8658();
                FUN_003e8658(&stack0x00000e28);
                FUN_00bd9414();
                while( true ) {
                  expf((float)local_260 / fVar63);
                  uVar16 = FUN_00bcdbc4();
                  puVar17 = (undefined1 *)FUN_004cc620(uVar16,uVar51 & 0xffffffff);
                  if (&stack0x00000e90 == puVar17) break;
                  auVar81 = FUN_00b9cd04();
                  plVar18 = (long *)FUN_004cc6fc(auVar81._0_8_ + 0x3c8,auVar81._8_8_ + 0xc0,
                                                 uVar51 & 0xffffffff);
                  if (*plVar18 == 0) {
                    /* WARNING: Subroutine does not return */
                    abort();
                  }
                  FUN_004494d0(&stack0x000001a0,*plVar18 + 0x28);
                  fVar62 = (float)FUN_00b9a128(0);
                  for (lVar50 = extraout_x8_26; lVar50 != extraout_x10_08; lVar50 = lVar50 + 1) {
                    iVar30 = *(int *)(extraout_x9_18 + lVar50 * 4);
                    if (iVar12 <= iVar30) {
                      FUN_003e8658(&stack0x000001a0);
                      goto LAB_00ae6c34;
                    }
                    iVar30 = iVar30 << 2;
                    lVar33 = lVar32 + (long)iVar30 * 4;
                    for (lVar34 = 0; lVar34 != 0x10; lVar34 = lVar34 + 4) {
                      *(float *)(lVar33 + lVar34) =
                           extraout_q0_01._0_4_ * *(float *)(lVar33 + lVar34) +
                           fVar62 * *(float *)((long)local_2d8[0] + lVar34 + (long)iVar30 * 4);
                    }
                  }
                  FUN_00bf7408(&stack0x000001a0);
                }
LAB_00ae6c34:
                uVar11 = 0xffffff9b;
                FUN_004cc79c(0);
                in_ZR = 0;
              }
            }
            FUN_00bfdb6c();
            uVar51 = (ulong)uVar11;
          }
          goto LAB_00ae8954;
        }
LAB_00ae694c:
        uVar76 = uVar76 + 1;
      }
      local_260 = CONCAT44(local_260._4_4_,0xffffffff);
      FUN_0050096c(&stack0x000001a0,2,&local_260);
      FUN_00bb17b0();
      lVar50 = 0x1c4;
      uVar76 = extraout_x8_33;
      uVar39 = extraout_x9_25;
      while( true ) {
        lVar32 = *(long *)(extraout_x14 + 0x9d8);
        uVar11 = (uint)uVar76;
        if ((ulong)((*(long *)(extraout_x14 + 0x9e0) - lVar32) / 0x230) <= uVar39) break;
        if ((*(int *)(lVar32 + lVar50 + 4) != 0) && (*(int *)(lVar32 + lVar50) != 0)) {
          if (uVar11 < 2) {
            *(int *)(_iStack00000000000001a0 + (long)(int)uVar11 * 4) = (int)uVar39;
          }
          uVar76 = (ulong)(uVar11 + 1);
        }
        uVar39 = uVar39 + 1;
        lVar50 = lVar50 + 0x230;
      }
      bVar10 = uVar11 == 2;
      if (bVar10) {
        uVar16 = FUN_00c08910(_iStack00000000000001a0);
        if ((bVar10) ||
           (uVar9 = 0xfffffffe < *(uint *)(extraout_x8_34 + 4),
           *(uint *)(extraout_x8_34 + 4) == 0xffffffff)) {
          FUN_00b8c554(uVar16,"SMASH_E_LOG ","tar_idx1 or tar_idx2 not valid\n");
          plVar49 = (long *)0x0;
          uVar51 = 0xffffff9b;
        }
        else {
          in_stack_00000e88 = in_stack_00000e88 & 0xffffffff00000000;
          FUN_004fdf00(&stack0x00000ca0,4,&stack0x00000e88);
          FUN_005d763c(&local_260,2,&stack0x00000ca0);
          FUN_00bd9438();
          FUN_00b92894();
          uVar16 = FUN_00bb9d08(_iStack00000000000001a0);
          if ((bool)uVar9) {
            FUN_00bd9420(uVar16,local_260);
            FUN_0042b354();
            uVar16 = FUN_00bcdbc4(local_260);
            FUN_0042b354(uVar16,extraout_x8_45 + 0x18);
            FUN_00bcdbc4(in_stack_00000ca0);
            fVar66 = (float)FUN_00bff2a8();
            fVar63 = fVar74;
            if (fVar74 <= fVar66) {
              fVar63 = fVar66;
            }
            fVar67 = fVar74 + fVar64;
            if (fVar66 + fVar65 <= fVar74 + fVar64) {
              fVar67 = fVar66 + fVar65;
            }
            if (fVar65 <= fVar64) {
              fVar64 = fVar65;
            }
            plVar49 = (long *)(ulong)(fVar64 * DAT_00d98dbc < fVar67 - fVar63);
            FUN_003e8658();
            FUN_00bd9438();
            uVar51 = 0;
          }
          else {
            in_stack_00000ca0 = in_stack_00000ca0 & 0xffffffff00000000;
            uVar16 = FUN_00ae9100(*plVar1 + (long)*(int *)(extraout_x8_35 + uVar51 * 4) * 0x230,
                                  &stack0x00000b60,*(undefined8 *)(local_260 + (long)plVar49),
                                  &stack0x00000ca0);
            if ((int)uVar16 == 0) {
              uVar51 = 0;
            }
            else {
              FUN_00b8c554(uVar16,"SMASH_E_LOG ",
                           "check_near_targets - compute_body_box_from_target error\n");
              uVar51 = 0xffffff9b;
            }
            plVar49 = (long *)0x0;
          }
          FUN_005d76a4(&local_260);
        }
      }
      else {
        FUN_00c0ebb0();
      }
      FUN_003e8658(&stack0x000001a0);
      iVar46 = (int)uVar51;
      uVar39 = 0;
      while( true ) {
        lVar32 = plVar18[0x13c];
        lVar50 = plVar18[0x13b];
        if ((ulong)((lVar32 - lVar50) / 0x230) <= uVar39) break;
        if ((*(int *)(lVar50 + uVar39 * 0x230 + 0x1c8) != 0) &&
           (*(int *)(lVar50 + uVar39 * 0x230 + 0x1c4) != 0)) {
          iVar29 = (int)plVar18[0xb];
          lVar32 = plVar18[0x129];
          uVar76 = (ulong)(int)lVar32;
          iVar80 = (int)plVar18[2];
          iVar86 = *(int *)((long)plVar18 + 0x14);
          fVar63 = *(float *)(plVar18[9] + 0x78);
          FUN_00bbcea0();
          lVar33 = *(long *)(extraout_x9_34 + 0x118) - *(long *)(extraout_x9_34 + 0x110);
          uVar51 = lVar33 >> 2;
          in_ZR = false;
          if ((lVar33 * 0x40000000 >> 0x20 & 0xfffffffffffffffeU) != uVar51) goto LAB_00ae87c0;
          lVar33 = lVar50 + uVar39 * extraout_x12_02;
          pfVar56 = (float *)(lVar33 + 0xb0);
          plVar59 = (long *)(lVar33 + 0xb8);
          uVar58 = *plVar59 - *(long *)pfVar56 >> 2;
          in_ZR = uVar58 == uVar76 * 2;
          if (uVar58 < uVar76 * 2) goto LAB_00ae87c0;
          puVar23 = (undefined4 *)(extraout_x8_46 + 0x1c4);
          iVar38 = (int)(uVar51 >> 1);
          lVar33 = uVar76 + (long)iVar38;
          FUN_007a83cc(&stack0x000001a0,lVar33);
          FUN_00500938(&local_260,lVar33);
          uVar16 = FUN_00b8d834();
          lVar33 = extraout_x8_47;
          for (lVar34 = extraout_x9_35; lVar34 < (long)uVar76; lVar34 = lVar34 + 1) {
            *(undefined4 *)(_iStack00000000000001a0 + lVar33) =
                 *(undefined4 *)(*(long *)pfVar56 + lVar33);
            lVar35 = *(long *)pfVar56 + lVar33;
            lVar36 = _iStack00000000000001a0 + lVar33;
            lVar33 = lVar33 + 8;
            *(undefined4 *)(lVar36 + 4) = *(undefined4 *)(lVar35 + 4);
            *(undefined4 *)(local_260 + lVar34 * 4) =
                 *(undefined4 *)(*(long *)(extraout_x10_11 + 200) + lVar34 * 4);
          }
          lVar33 = 0;
          for (lVar34 = 0; lVar34 < iVar38; lVar34 = lVar34 + 1) {
            uVar11 = (int)lVar32 + (int)lVar34;
            *(undefined4 *)
             (_iStack00000000000001a0 +
             (-(ulong)(uVar11 >> 0x1f) & 0xfffffff800000000 | (ulong)uVar11 << 3)) =
                 *(undefined4 *)(*(long *)(extraout_x9_34 + 0x110) + lVar33);
            lVar35 = *(long *)(extraout_x9_34 + 0x110) + lVar33;
            lVar33 = lVar33 + 8;
            *(undefined4 *)(_iStack00000000000001a0 + (long)(int)uVar11 * 8 + 4) =
                 *(undefined4 *)(lVar35 + 4);
            *(undefined4 *)(local_260 + (long)(int)uVar11 * 4) =
                 *(undefined4 *)(*(long *)(lVar50 + uVar39 * 0x230 + 0x128) + lVar34 * 4);
          }
          uVar11 = 0;
          for (lVar32 = 0; lVar32 != 0x10; lVar32 = lVar32 + 4) {
            uVar11 = uVar11 + (*(float *)(*(long *)(lVar50 + uVar39 * 0x230 + 0x98) +
                                         (long)*(int *)((long)&DAT_00d989b0 + lVar32) * 4) != 0.0);
          }
          fVar64 = (float)iVar80;
          fVar74 = (float)iVar86;
          if (iVar12 < iVar30 && uVar11 < 2) {
            for (lVar32 = 0; lVar32 != 0x20; lVar32 = lVar32 + 4) {
              *(undefined4 *)(local_260 + (long)*(int *)((long)&DAT_00ec21b0 + lVar32) * 4) = 0;
            }
            for (lVar32 = 0; lVar32 != 0x40; lVar32 = lVar32 + 4) {
              *(undefined4 *)(local_260 + (long)*(int *)((long)&DAT_00f0ca28 + lVar32) * 4) = 1;
            }
            FUN_0079fe98(&stack0x00000ca0,&stack0x000001a0);
            uVar16 = FUN_00bcdbc4();
            FUN_004494d0(uVar16,&local_260);
            auVar81 = FUN_00b9cd04();
            iVar80 = FUN_00bd14ec(auVar81._0_8_ + 0x1e0,auVar81._8_8_ + 0x3c8,&local_330);
            FUN_003e8658(&stack0x00000e88);
            FUN_00bd9420();
            FUN_003e8658();
            if (iVar80 == 0) {
              uVar51 = 0xffffff9b;
              if (iVar29 != 0) goto LAB_00ae809c;
LAB_00ae7f6c:
              fVar64 = (*(float *)(_iStack00000000000001a0 + 0x80) +
                       *(float *)(_iStack00000000000001a0 + 0x88)) * 0.5;
              *extraout_x13_00 = fVar64;
              lVar50 = lVar50 + uVar39 * 0x230;
              fVar74 = (*(float *)(_iStack00000000000001a0 + 0x84) +
                       *(float *)(_iStack00000000000001a0 + 0x8c)) * 0.5;
              *(float *)(lVar50 + 4) = fVar74;
              fVar66 = (float)(local_330 >> 0x20);
              fVar65 = ABS((float)local_330 - fVar64);
              fVar63 = ABS(fVar66 - fVar74);
              fVar64 = ABS(((float)local_330 + (float)uStack_328) - fVar64);
              fVar74 = ABS((fVar66 + (float)((ulong)uStack_328 >> 0x20)) - fVar74);
              fVar65 = (float)((uint)fVar65 ^
                              ((uint)fVar65 ^ (uint)fVar64) & -(uint)(fVar65 < fVar64));
              fVar63 = (float)((uint)fVar63 ^
                              ((uint)fVar63 ^ (uint)fVar74) & -(uint)(fVar63 < fVar74));
              if (fVar63 <= fVar65) {
                fVar63 = fVar65;
              }
              fVar63 = fVar63 + fVar63;
              *(float *)(lVar50 + 8) = fVar63;
LAB_00ae821c:
              *(float *)(lVar50 + 0xc) = fVar63;
              goto LAB_00ae8238;
            }
            uVar51 = 0xffffff9b;
            *puVar23 = 0;
          }
          else {
            if ((((*(int *)(local_260 + 0x40) == 0) || (*(int *)(local_260 + 0x44) == 0)) ||
                (*(int *)(local_260 + 4) == 0)) || (*(int *)(local_260 + 8) == 0)) {
              uStack_408 = 0;
              local_410 = 0.0;
              fStack_40c = 0.0;
              uVar76 = 0xffffff98;
            }
            else {
              fStack_40c = (*(float *)(_iStack00000000000001a0 + 0xc) +
                           *(float *)(_iStack00000000000001a0 + 0x14)) * 0.5;
              fVar65 = (*(float *)(_iStack00000000000001a0 + 0x80) +
                       *(float *)(_iStack00000000000001a0 + 0x88)) * 0.5;
              fVar67 = (*(float *)(_iStack00000000000001a0 + 0x84) +
                       *(float *)(_iStack00000000000001a0 + 0x8c)) * 0.5;
              fVar66 = (*(float *)(_iStack00000000000001a0 + 8) +
                       *(float *)(_iStack00000000000001a0 + 0x10)) * 0.5 - fVar65;
              fVar66 = SQRT(fVar66 * fVar66 + (fStack_40c - fVar67) * (fStack_40c - fVar67));
              if (iVar80 < iVar86) {
                fVar75 = (fVar74 * fVar66) / fVar64;
                fVar68 = fVar66;
              }
              else {
                fVar68 = (fVar64 * fVar66) / fVar74;
                fVar75 = fVar66;
              }
              if (iVar29 == 0) {
                if (iVar80 != iVar86) {
                  pcVar22 = "GanRet does not init.\n";
                  auVar81 = FUN_00b96fd0(uVar16,"ion failed: %d\n","GanRet does not init.\n");
                  __android_log_print(auVar81._0_8_,auVar81._8_8_,pcVar22 + 0xcef);
                  uVar51 = 0xffffff95;
                  goto LAB_00ae823c;
                }
                fVar66 = fVar75 * 0.5;
                fStack_40c = fVar67;
              }
              else {
                fVar75 = fVar75 * fVar63;
                fVar66 = fVar75 * 0.5;
                fVar68 = fVar68 * fVar63;
                if ((fVar62 <= fStack_40c + fVar66) &&
                   (fStack_40c = fVar67, fVar67 + fVar66 <= fVar62)) {
                  fStack_40c = fVar62 - fVar66;
                }
              }
              fStack_40c = fStack_40c - fVar66;
              auVar73._0_4_ = fVar65 + fVar68 * -0.5;
              auVar73._4_4_ = fStack_40c;
              auVar73._8_4_ = fVar68;
              uVar76 = 0;
              auVar73._12_4_ = fVar75;
              uStack_408 = auVar73._8_8_;
              local_410 = auVar73._0_4_;
            }
            uVar16 = FUN_00bc4410();
            FUN_0079fe98(uVar16,&stack0x000001a0);
            FUN_004494d0(&stack0x00000e28,&local_260);
            auVar81 = FUN_00b9cd04();
            uVar11 = FUN_00bd14ec(auVar81._0_8_ + 0xc0,auVar81._8_8_ + 0x368,&stack0x00000aa0);
            uVar51 = (ulong)uVar11;
            FUN_003e8658(&stack0x00000e28);
            FUN_00bc4410();
            uVar16 = FUN_003e8658();
            if ((uVar11 == 0) || (uVar11 == 0xffffff98)) {
              iVar80 = (int)uVar76;
              if (uVar11 == 0 && iVar80 == 0) {
                if (iVar29 == 1) {
                  local_410 = local_410 + (float)((ulong)uStack_408 >> 0x20);
                  fVar63 = 0.0;
                  if (fStack_40c <= 0.0) {
                    fVar63 = fStack_40c;
                  }
                  fVar65 = 0.0;
                  if (0.0 <= local_410) {
                    fVar65 = local_410;
                  }
                  if (fVar63 <= fVar65) {
                    fVar65 = fVar65 - fVar63;
                    fVar68 = 0.0;
                    fVar67 = fVar65 * 0.5;
                    fVar66 = fVar65 * (fVar64 / fVar74);
                    fVar63 = fVar63 + fVar67;
                    if (0.0 <= fVar66) {
                      fVar68 = fVar66 * 0.5;
                    }
                    else {
                      fVar65 = 0.0 / (fVar64 / fVar74);
                      fVar67 = fVar65 * 0.5;
                      fVar66 = 0.0;
                    }
                    uStack_328 = CONCAT44(fVar65,fVar66);
                    uVar51 = 0;
                    local_330 = CONCAT44(fVar63 - fVar67,0.0 - fVar68);
                    goto LAB_00ae809c;
                  }
                }
                else {
                  auVar7._4_4_ = fStack_40c;
                  auVar7._0_4_ = local_410;
                  auVar7._8_8_ = uStack_408;
                  auVar6._4_4_ = fStack_40c;
                  auVar6._0_4_ = local_410;
                  auVar6._8_8_ = uStack_408;
                  auVar81 = NEON_ext(auVar6,auVar7,8,1);
                  fVar63 = local_410 + auVar81._0_4_;
                  fVar65 = fStack_40c + auVar81._4_4_;
                  uVar51 = CONCAT44(fVar65,fVar63);
                  uVar58 = CONCAT44(fStack_40c,local_410) ^
                           CONCAT44(fStack_40c,local_410) &
                           CONCAT44(-(uint)(0.0 < fStack_40c),-(uint)(0.0 < local_410));
                  uVar51 = uVar51 ^ uVar51 & CONCAT44(-(uint)(fVar65 < 0.0),-(uint)(fVar63 < 0.0));
                  fVar65 = (float)uVar51;
                  fVar63 = (float)(uVar58 >> 0x20);
                  fVar66 = (float)(uVar51 >> 0x20);
                  if ((!(bool)(~((float)uVar58 <= fVar65) & 1)) &&
                     (!(bool)(~(fVar63 <= fVar66) & 1))) {
                    uVar51 = 0;
                    uStack_328 = CONCAT44(fVar66 - fVar63,fVar65 - (float)uVar58);
                    local_330 = uVar58;
                    goto joined_r0x00ae7f38;
                  }
                }
                pcVar22 = "GanRet does not init.\n";
                auVar81 = FUN_00b96fd0(uVar16,"ion failed: %d\n","GanRet does not init.\n");
                __android_log_print(auVar81._0_8_,auVar81._8_8_,pcVar22 + 0xda7);
                uVar51 = 0xffffff9b;
              }
              else {
                if (iVar80 == 0) {
                  local_330 = CONCAT44(fStack_40c,local_410);
                  uVar51 = 0;
                  uStack_328 = uStack_408;
                }
                else {
                  if (uVar11 != 0) {
                    uVar77 = 0xffffff98;
                    *puVar23 = 0;
                    if (uVar11 != 0xffffff98 || iVar80 != -0x68) {
                      uVar77 = 0xffffff9b;
                    }
                    uVar51 = (ulong)uVar77;
                    goto LAB_00ae823c;
                  }
                  local_330 = 0;
                  uStack_328 = 0;
                }
joined_r0x00ae7f38:
                if (iVar29 == 0) goto LAB_00ae7f6c;
LAB_00ae809c:
                if (iVar46 == 0 && (int)plVar49 != 0) {
                  uVar16 = FUN_00bcdbd0();
                  local_350 = 0;
                  FUN_004fdf00(uVar16,4,&local_350);
                  local_350 = 0;
                  lVar34 = lVar50 + uVar39 * 0x230;
                  lVar33 = *(long *)pfVar56;
                  lVar32 = *(long *)(lVar34 + 0x80);
                  lVar34 = *(long *)(lVar34 + 0x88) - lVar32;
                  uVar58 = lVar34 >> 2;
                  bVar10 = *plVar59 - lVar33 >> 2 != lVar34 >> 1;
                  if ((!bVar10 && 0x17 < uVar58) && (bVar10 || uVar58 != 0x18)) {
                    FUN_00ae98a0(lVar33,lVar32,&stack0x00000b60,in_stack_00000b00,&local_350);
                    if (local_350 == 0) {
                      FUN_00c0ebb0(puVar23);
                      *extraout_x8_48 = 0;
                    }
                    else {
                      fVar63 = (float)uStack_328;
                      fVar65 = (float)((ulong)uStack_328 >> 0x20);
                      fVar66 = (float)local_330;
                      fVar67 = (float)(local_330 >> 0x20);
                      fVar68 = fVar66 + (((float)*in_stack_00000b00 +
                                         (float)in_stack_00000b00[1] * 0.5) -
                                        (fVar66 + fVar63 * 0.5));
                      fVar75 = fVar67 + (((float)((ulong)*in_stack_00000b00 >> 0x20) +
                                         (float)((ulong)in_stack_00000b00[1] >> 0x20) * 0.5) -
                                        (fVar67 + fVar65 * 0.5));
                      uVar58 = CONCAT44(fVar67 + fVar65,fVar66 + fVar63);
                      uVar76 = local_330 ^
                               (local_330 ^ CONCAT44(fVar75,fVar68)) &
                               CONCAT44(-(uint)(fVar75 < fVar67),-(uint)(fVar68 < fVar66));
                      fVar78 = (float)uVar76;
                      fVar79 = (float)(uVar76 >> 0x20);
                      uVar58 = uVar58 ^ (uVar58 ^ CONCAT44(fVar65 + fVar75,fVar63 + fVar68)) &
                                        CONCAT44(-(uint)(fVar67 + fVar65 < fVar65 + fVar75),
                                                 -(uint)(fVar66 + fVar63 < fVar63 + fVar68));
                      fVar68 = (float)(uVar58 >> 0x20);
                      fVar67 = (float)uVar58;
                      fVar65 = fVar67 - fVar78;
                      fVar66 = fVar68 - fVar79;
                      fVar75 = fVar66 * (fVar64 / fVar74);
                      lVar50 = lVar50 + uVar39 * 0x230;
                      fVar63 = fVar65 / (fVar64 / fVar74);
                      if (fVar65 <= fVar75) {
                        fVar63 = fVar66;
                        fVar65 = fVar75;
                      }
                      *(float *)(lVar50 + 8) = fVar65;
                      *(float *)(lVar50 + 0xc) = fVar63;
                      uVar76 = 1;
                      *(ulong *)extraout_x13_00 =
                           CONCAT44((fVar79 + fVar68) * 0.5,(fVar78 + fVar67) * 0.5);
                    }
                  }
                  else {
                    pcVar22 = "GanRet does not init.\n";
                    auVar81 = FUN_00b96fd0(lVar33,"ion failed: %d\n","GanRet does not init.\n");
                    __android_log_print(auVar81._0_8_,auVar81._8_8_,pcVar22 + 0xe4d);
                    uVar76 = 0;
                    uVar51 = 0xffffff9b;
                  }
                  FUN_00bffc28();
                  if ((uVar76 & 1) == 0) goto LAB_00ae823c;
                }
                else {
                  fVar65 = (float)uStack_328;
                  fVar63 = (float)((ulong)uStack_328 >> 0x20);
                  fVar66 = (fVar64 / fVar74) * fVar63;
                  *(ulong *)extraout_x13_00 =
                       CONCAT44((float)(local_330 >> 0x20) + fVar63 * 0.5,
                                (float)local_330 + fVar65 * 0.5);
                  if (fVar65 <= fVar66) {
                    lVar50 = lVar50 + uVar39 * 0x230;
                    *(float *)(lVar50 + 8) = fVar66;
                    goto LAB_00ae821c;
                  }
                  lVar50 = lVar50 + uVar39 * 0x230;
                  *(float *)(lVar50 + 8) = fVar65;
                  *(float *)(lVar50 + 0xc) = fVar65 / (fVar64 / fVar74);
                }
LAB_00ae8238:
                uVar51 = 0;
              }
            }
          }
LAB_00ae823c:
          FUN_003e8658(&local_260);
          FUN_003e8658(&stack0x000001a0);
          if (((int)uVar51 != 0) && (in_ZR = 0, (int)uVar51 != -0x68)) goto LAB_00ae8954;
          if ((*(int *)(plVar18[9] + 0x14) != 0) &&
             (*(int *)(*plVar1 + uVar39 * 0x230 + 0x1c4) != 0)) {
            pfVar60 = (float *)(*plVar1 + uVar39 * 0x230);
            pfVar55 = pfVar60 + 2;
            lVar50 = *(long *)(pfVar60 + 6);
            uVar16 = NEON_fmov(0x41200000,4);
            fVar66 = (float)*(long *)pfVar55;
            fVar68 = (float)lVar50;
            fVar67 = (float)((ulong)*(long *)pfVar55 >> 0x20);
            fVar75 = (float)((ulong)lVar50 >> 0x20);
            fVar63 = expf((float)((ulong)uVar16 >> 0x20) - ABS(fVar67 - fVar75));
            fVar64 = expf((float)uVar16 - ABS(fVar66 - fVar68));
            uVar16 = NEON_fmov(0x3f800000,4);
            fVar74 = (float)uVar16;
            fVar65 = (float)((ulong)uVar16 >> 0x20);
            fVar64 = fVar74 - fVar74 / (fVar64 + fVar74);
            fVar63 = fVar65 - fVar65 / (fVar63 + fVar65);
            *(long *)pfVar55 =
                 CONCAT44(fVar75 * fVar63 + fVar67 * (fVar65 - fVar63),
                          fVar68 * fVar64 + fVar66 * (fVar74 - fVar64));
            pfVar56 = pfVar60 + 4;
            fVar63 = (float)FUN_00b9fb14(*pfVar56 - *pfVar60,pfVar60[5] - pfVar60[1],*pfVar60);
            expf(10.0 - fVar63);
            lVar50 = 0;
            while (lVar50 != 8) {
              uVar69 = *(undefined4 *)((long)pfVar60 + lVar50);
              FUN_00bbeb2c(lVar50 + 4);
              *extraout_x9_36 = uVar69;
              lVar50 = extraout_x8_49;
            }
            *(long *)(pfVar60 + 6) = *(long *)pfVar55;
            *(long *)pfVar56 = *(long *)pfVar60;
          }
        }
        uVar39 = uVar39 + 1;
      }
      pfVar60 = (float *)0x0;
      uVar51 = 0;
      lVar33 = 0x1c0;
      lVar34 = 0x230;
      while( true ) {
        uVar39 = (lVar32 - lVar50) / 0x230;
        in_ZR = uVar51 == uVar39;
        iVar12 = (int)pfVar60;
        if (uVar39 <= uVar51) break;
        lVar32 = lVar50 + lVar33;
        if ((*(int *)(lVar32 + 8) == 0) || (*(int *)(lVar32 + 4) != 0)) {
          pfVar60 = (float *)(ulong)(iVar12 + 1);
          FUN_00ae92f4(lVar50 + (long)iVar12 * 0x230,lVar32 + -0x1c0);
          pfVar56 = pfVar60;
        }
        else {
          uVar39 = (ulong)*(int *)(lVar50 + lVar33);
          if (*(int *)(lVar50 + lVar33) < 0) goto LAB_00ae87c0;
          uVar76 = plVar18[0x142] - plVar18[0x141] >> 2;
          in_ZR = uVar39 == uVar76;
          if (uVar76 <= uVar39) goto LAB_00ae87c0;
          *(undefined4 *)(plVar18[0x141] + uVar39 * 4) = 0;
        }
        lVar32 = plVar18[0x13c];
        lVar50 = plVar18[0x13b];
        uVar51 = uVar51 + 1;
        lVar33 = lVar33 + 0x230;
      }
      FUN_00ae9400(plVar1,(long)iVar12);
      iVar12 = 0;
      uVar39 = (plVar18[0x13c] - plVar18[0x13b]) / 0x230;
      lVar50 = 0x1c0;
      *(undefined4 *)(plVar18 + 0x144) = 0;
      for (uVar51 = 0; uVar51 < uVar39; uVar51 = uVar51 + 1) {
        iVar30 = *(int *)(plVar18[0x13b] + lVar50);
        if (iVar12 <= iVar30) {
          iVar12 = iVar30 + 1;
          *(int *)(plVar18 + 0x144) = iVar12;
        }
        lVar50 = lVar50 + 0x230;
      }
      FUN_00bed548(&stack0x000001a0,uVar39 << 2);
      FUN_004fdf00();
      FUN_00c04a38();
      puVar17 = &stack0x00000ac0;
      auVar81 = FUN_00bb1e30(&local_260,extraout_x1_08,puVar17);
      puVar17 = puVar17 + 0x1e0;
      FUN_0050096c(auVar81._0_8_,auVar81._8_8_,puVar17);
      lVar50 = FUN_00bcff08();
      puVar17 = puVar17 + 0x3c8;
      FUN_004fdf00(lVar50 + 0x1e0,(extraout_x8_50 - extraout_x9_37) / 0x230 << 2,puVar17);
      lVar50 = FUN_00bcff08();
      auVar81 = FUN_00bb1e30(lVar50 + 0x3c8);
      puVar17 = puVar17 + 0xc0;
      uVar39 = in_stack_00000b80 & 0xffffffff00000000;
      FUN_0050096c(auVar81._0_8_,auVar81._8_8_,puVar17);
      lVar50 = FUN_00bcff08();
      auVar81 = FUN_00bb1e30(lVar50 + 0xc0);
      FUN_0050096c(auVar81._0_8_,auVar81._8_8_,puVar17 + 0x368);
      uVar51 = 0;
      FUN_00b8ece8();
      lVar50 = 0x188;
      while( true ) {
        FUN_00c04a38();
        pfVar60 = (float *)((extraout_x8_51 - extraout_x9_38) / 0x230);
        in_ZR = pfVar56 == pfVar60;
        if (pfVar60 <= pfVar56) break;
        if (*(int *)(extraout_x9_38 + lVar50 + 0x3c) == 0) {
          uVar16 = *(undefined8 *)(extraout_x9_38 + lVar50);
          ((undefined8 *)(_iStack00000000000001a0 + uVar51))[1] =
               ((undefined8 *)(extraout_x9_38 + lVar50))[1];
          *(undefined8 *)(_iStack00000000000001a0 + uVar51) = uVar16;
          *(undefined4 *)(local_260 + 0x230) = *(undefined4 *)(*plVar1 + lVar50 + 0x10);
          auVar81 = *(undefined1 (*) [16])(*plVar1 + lVar50 + 0x14);
          puVar19 = (undefined8 *)((in_stack_00000ca0 & 0xffffffff00000000) + uVar51);
          puVar19[1] = auVar81._8_8_;
          *puVar19 = auVar81._0_8_;
          *(undefined4 *)((in_stack_00000e88 & 0xffffffff00000000) + 0x230) =
               *(undefined4 *)(*plVar1 + lVar50 + 0x24);
        }
        else {
          iVar12 = FUN_00ae9100(extraout_x9_38 + lVar50 + -0x188,&stack0x00000b60,
                                _iStack00000000000001a0 + uVar51,local_260 + 0x230);
          if (iVar12 != 0) goto LAB_00ae8858;
          lVar32 = *plVar1;
          auVar81 = *(undefined1 (*) [16])(_iStack00000000000001a0 + uVar51);
          *(long *)(lVar32 + lVar50 + 0x30) = auVar81._8_8_;
          *(long *)(lVar32 + lVar50 + 0x28) = auVar81._0_8_;
        }
        FUN_00bba738();
        lVar50 = lVar50 + 0x230;
        uVar51 = uVar51 + 0x10;
      }
      uVar58 = FUN_00bd242c();
      lVar50 = 0x3fc;
      lVar32 = 0x10;
      uVar76 = 1;
      lVar33 = extraout_x8_52;
      lVar35 = extraout_x9_39;
      for (; uVar51 < (ulong)((lVar33 - lVar35) / 0x230); uVar51 = uVar51 + 1) {
        if (*(int *)(uVar39 + uVar51 * 4) == 0) {
          lVar37 = lVar34;
          lVar52 = lVar32;
          lVar36 = lVar50;
          for (uVar25 = uVar76; uVar25 < (ulong)((lVar33 - lVar35) / 0x230); uVar25 = uVar25 + 1) {
            if (*(int *)(uVar39 + lVar37 + 4) == 0) {
              FUN_00ae9170(_iStack00000000000001a0 + uVar51 * 0x10,
                           *(undefined4 *)(local_260 + uVar51 * 4),
                           (in_stack_00000ca0 & 0xffffffff00000000) + uVar51 * 0x10,
                           *(undefined4 *)((in_stack_00000e88 & 0xffffffff00000000) + uVar51 * 4),
                           lVar35 + lVar36 + -0x1cc,_iStack00000000000001a0 + lVar52,
                           *(undefined4 *)(local_260 + lVar37 + 4),&stack0x00000e28);
              lVar33 = _iStack00000000000001a0 + lVar37 * 4;
              if (*(float *)(_iStack00000000000001a0 + (uVar51 << 2 | 2) * 4) *
                  *(float *)(_iStack00000000000001a0 + (uVar51 << 2 | 3) * 4) <=
                  *(float *)(lVar33 + 0x18) * *(float *)(lVar33 + 0x1c)) {
                *(undefined4 *)(uVar39 + uVar51 * 4) = 1;
                FUN_00bda980(plVar18[0x13b]);
                uVar58 = FUN_00c04a38();
                lVar33 = extraout_x8_53;
                lVar35 = extraout_x9_40;
                break;
              }
              *(undefined4 *)(uVar39 + lVar37 + 4) = 1;
              uVar58 = FUN_00bda980(*plVar1);
            }
            lVar36 = lVar36 + 0x230;
            lVar37 = lVar37 + 4;
            lVar52 = lVar52 + 0x10;
            lVar33 = plVar18[0x13c];
            lVar35 = plVar18[0x13b];
          }
        }
        lVar50 = lVar50 + 0x230;
        lVar34 = lVar34 + 4;
        lVar32 = lVar32 + 0x10;
        uVar76 = uVar76 + 1;
      }
      lVar50 = 0;
      auVar85._8_8_ = 0;
      auVar85._0_8_ = uVar58;
      uVar51 = 0;
      while( true ) {
        auVar81 = FUN_00bd3880(auVar85._0_8_,auVar85._8_8_);
        uVar16 = auVar81._0_8_;
        bVar10 = extraout_x8_54 <= uVar51;
        in_ZR = uVar51 == extraout_x8_54;
        if (bVar10) break;
        if (*(int *)(uVar39 + uVar51 * 4) != 0) {
          if (*(int *)(extraout_x9_41 + lVar50 + 0x1c0) < 0) {
LAB_00ae87d4:
            pcVar22 = "remove - invalid id\n";
          }
          else {
            auVar84 = FUN_00b9fd48();
            uVar16 = auVar84._0_8_;
            if (bVar10) goto LAB_00ae87d4;
            if (*(int *)(extraout_x9_42 + extraout_x8_55 * 4) == 1) {
              *(undefined4 *)(extraout_x9_42 + extraout_x8_55 * 4) = 0;
              goto LAB_00ae87a0;
            }
            pcVar22 = "remove - invalid status\n";
            in_ZR = 0;
          }
          FUN_00b8c554(uVar16,"SMASH_E_LOG ",pcVar22);
LAB_00ae8858:
          uVar51 = 0xffffff9b;
          goto LAB_00ae885c;
        }
        uVar16 = FUN_00ae92f4(extraout_x9_41 + auVar81._8_8_ * 0x230,extraout_x9_41 + lVar50);
        auVar84._8_8_ = auVar81._8_8_ + 1;
        auVar84._0_8_ = uVar16;
LAB_00ae87a0:
        auVar85 = FUN_00c04a38(auVar84._0_8_,auVar84._8_8_);
        uVar51 = uVar51 + 1;
        lVar50 = lVar50 + 0x230;
      }
      FUN_00ae9400(plVar1);
      iVar12 = 0;
      lVar50 = 0x1c0;
      uVar39 = (plVar18[0x13c] - plVar18[0x13b]) / 0x230;
      *(undefined4 *)(plVar18 + 0x144) = 0;
      for (uVar51 = 0; in_ZR = uVar51 == uVar39, uVar51 < uVar39; uVar51 = uVar51 + 1) {
        iVar30 = *(int *)(plVar18[0x13b] + lVar50);
        if (iVar12 <= iVar30) {
          iVar12 = iVar30 + 1;
          *(int *)(plVar18 + 0x144) = iVar12;
        }
        lVar50 = lVar50 + 0x230;
      }
      uVar51 = 0;
LAB_00ae885c:
      FUN_00bd9414();
      FUN_003e8658(&stack0x00000e88);
      FUN_00bd9438();
      FUN_003e8658(&local_260);
      FUN_00c097b4();
      if ((int)uVar51 != 0) goto LAB_00ae8954;
      lVar50 = FUN_00be4814(plVar18[0x13c] - plVar18[0x13b]);
      in_ZR = extraout_x8_56 == extraout_x10_12;
      if (extraout_x8_56 < extraout_x10_12) {
LAB_00ae88c4:
        iVar30 = 0;
        *(undefined4 *)(lVar50 + 0x9fc) = 0;
      }
      else {
        iVar12 = *(int *)(lVar50 + 0x9f8);
        *(undefined4 *)(lVar50 + 0x9fc) = 1;
        if (iVar12 < 0) {
          iVar30 = *(int *)(lVar50 + 0xa00);
        }
        else {
          iVar30 = *(int *)(lVar50 + 0xa00);
          in_ZR = iVar12 <= iVar30 && extraout_x8_56 == (long)*(int *)(lVar50 + 0x9f4);
          if (iVar12 <= iVar30 && extraout_x8_56 < (ulong)(long)*(int *)(lVar50 + 0x9f4))
          goto LAB_00ae88c4;
        }
        iVar30 = iVar30 + 1;
      }
      *(int *)(lVar50 + 0xa00) = iVar30;
      uVar11 = FUN_00ae89b0(lVar50,plVar1,param_7,*(undefined4 *)(lVar50 + 0x948));
      uVar51 = (ulong)uVar11;
      if (uVar11 == 0) {
        uVar39 = 0xffffffffffffffff;
        lVar50 = 0x220;
        do {
          uVar39 = uVar39 + 1;
          uVar51 = (plVar18[0x13c] - plVar18[0x13b]) / 0x230;
          in_ZR = uVar39 == uVar51;
          if (uVar51 <= uVar39) {
            uVar51 = 0;
            break;
          }
          puVar19 = (undefined8 *)(plVar18[0x13b] + lVar50);
          lVar50 = lVar50 + 0x230;
          uVar11 = FUN_00aeca74(*puVar19,puVar19[-0x3a],(long)(int)plVar18[0x129] * 3);
          uVar51 = (ulong)uVar11;
        } while (uVar11 == 0);
      }
      goto LAB_00ae8954;
    }
    FUN_00bd0184();
    uVar16 = FUN_00be4814(extraout_x8_22 - extraout_x9_15);
    iVar46 = extraout_w10_00 - extraout_w8_01;
    in_ZR = iVar46 == extraout_w11_00;
    if (extraout_w11_00 <= iVar46) {
      iVar46 = extraout_w11_00;
    }
    if (-1 < iVar46) {
      lVar34 = plVar18[0xb];
      iVar80 = *(int *)((long)plVar18 + 0x944);
      lVar37 = (long)iVar46;
      lVar35 = plVar18[0x129];
      iVar86 = *(int *)((long)plVar18 + 0xa24);
      iStack00000000000001a0 = 1;
      FUN_0050096c(&stack0x00000ca0,lVar37,&stack0x000001a0);
      FUN_00bffc14();
      FUN_00bdc2e8();
      iStack00000000000001a0 = 0;
      FUN_0050096c(&stack0x00000e28,lVar37,&stack0x000001a0);
      uVar16 = FUN_00bcdbd0();
      FUN_00bdc2e8(uVar16,(long)(iVar46 << 2),&stack0x000001a0);
      _iStack00000000000001a0 = (ulong)uStack00000000000001a4 << 0x20;
      FUN_0050096c(&stack0x00000aa0,lVar37,&stack0x000001a0);
      in_stack_00000b60 = (void *)0x0;
      lVar36 = plVar18[9];
      uVar51 = 0;
      uVar39 = 0;
      auVar81 = FUN_00bf0260(plVar18[10]);
      uVar11 = *(uint *)(plVar18 + 0x145);
      puVar24 = &DAT_00f0caf8;
      lVar33 = lVar37;
      lVar32 = lVar5;
      while( true ) {
        if (lVar37 <= lVar32) break;
        in_stack_000001a8 = _UNK_00d989c8;
        _iStack00000000000001a0 = _DAT_00d989c0;
        lVar33 = lVar50 + ((long)uVar39 >> 0x1e) + 0x20;
        puVar23 = (undefined4 *)(in_stack_00000e28 + unaff_x28);
        auVar81 = FUN_00b8ef0c(_DAT_00f0caf8,auVar81._0_8_,auVar81._8_8_,&stack0x000001a0,4,
                               &stack0x00000b60,in_stack_00000e88 + (unaff_x28 & 0xfffffffc) * 4);
        *(long *)(extraout_x15 + 0xde8) = auVar81._8_8_;
        *(long *)(extraout_x15 + 0xde0) = auVar81._0_8_;
        *puVar23 = 0;
        *(undefined4 *)(in_stack_00000aa0 + unaff_x28) = 0;
        FUN_00ae990c();
        param_9 = (undefined8 *)&stack0x00000b60;
        auVar81 = FUN_00ae990c(lVar33,lVar50 + (uVar51 & 0xfffffffe) * 4 + 0x2f0,&stack0x00000b80,6,
                               param_9,extraout_x13 + extraout_x11_03,
                               (undefined4 *)(in_stack_00000aa0 + unaff_x28));
        lVar32 = lVar32 + 1;
        unaff_x28 = unaff_x28 + 4;
        uVar39 = uVar39 + 0x2400000000;
        uVar51 = uVar51 + 0x12;
        in_stack_00000b90 = extraout_x10_06;
      }
      FUN_00bc4410();
      uVar16 = FUN_00bd0184();
      FUN_00bdc2e8(uVar16,(extraout_x8_32 - extraout_x9_24) / 0x230 << 2,&stack0x000001a0);
      FUN_00bd0184();
      _iStack00000000000001a0 = _iStack00000000000001a0 & 0xffffffff00000000;
      FUN_00bb1e30(&local_330,extraout_x1_06,&stack0x000001a0);
      FUN_0050096c();
      FUN_00b8ece8();
      uVar76 = FUN_00b8f6a4();
      lVar32 = lVar50;
      while( true ) {
        uVar51 = (plVar18[0x13c] - plVar18[0x13b]) / 0x230;
        in_ZR = uVar39 == uVar51;
        if (uVar51 <= uVar39) break;
        uVar76 = plVar18[0x13b] + lVar33;
        if ((*(int *)(uVar76 + 0x1c4) != 0) &&
           (uVar76 = FUN_00ae9100(uVar76,&stack0x00000b60,puVar24 + in_stack_00000b80,
                                  local_330 + lVar32), (int)uVar76 != 0)) {
          uVar51 = uVar76 & 0xffffffff;
          goto LAB_00ae7960;
        }
        uVar39 = uVar39 + 1;
        lVar32 = lVar32 + 4;
        puVar24 = puVar24 + 0x10;
        lVar33 = lVar33 + 0x230;
      }
      for (uVar51 = 0; puVar24 = PTR_nothrow_01339be0, fVar63 = DAT_00d98f40, dVar70 = DAT_00d94270,
          (long)uVar51 < lVar37; uVar51 = uVar51 + 1) {
        lVar33 = 0;
        FUN_00b8f6a4(in_stack_00000ca0);
        *(undefined4 *)(extraout_x8_36 + uVar51 * 4) = 1;
        while (uVar76 = FUN_00bc6cfc(plVar18[0x13b]), uVar39 < extraout_x9_26) {
          _iStack00000000000001a0 = CONCAT44(uStack00000000000001a4,1);
          param_9 = (undefined8 *)(extraout_x8_37 + lVar33);
          FUN_00ae9170(in_stack_00000e88 + (uVar51 & 0x3fffffff) * 0x10,
                       *(undefined4 *)(in_stack_00000e28 + uVar51 * 4),
                       in_stack_00000b00 + (uVar51 & 0x3fffffff) * 2,
                       *(undefined4 *)(in_stack_00000aa0 + uVar51 * 4),param_9,
                       in_stack_00000b80 + lVar32,*(undefined4 *)(local_330 + uVar39 * 4),
                       &stack0x000001a0);
          if (iStack00000000000001a0 == 0) {
            *(undefined4 *)(in_stack_00000ca0 + uVar51 * 4) = 0;
          }
          FUN_00b92d40();
          lVar33 = lVar33 + 0x230;
        }
      }
      uVar39 = (ulong)(uint)DAT_00d98f40;
      uVar58 = (ulong)DAT_00d99024;
      for (lVar32 = 0; in_ZR = lVar32 == lVar37, lVar32 < lVar37; lVar32 = lVar32 + 1) {
        if (*(int *)(in_stack_00000ca0 + lVar32 * 4) != 0) {
          if ((int)lVar34 == 0) {
            if ((int)plVar18[2] == *(int *)((long)plVar18 + 0x14)) {
              uVar77 = (int)lVar32 * 9;
              lVar33 = lVar50 + ((long)((ulong)uVar77 << 0x22) >> 0x1e);
              fVar64 = *(float *)(lVar33 + 0x28);
              if (fVar64 < 0.0) {
LAB_00ae72c0:
                local_3f0 = 0;
              }
              else {
                fVar74 = *(float *)(lVar33 + 0x20);
                param_3 = (ulong)(uint)fVar74;
                if (fVar74 < 0.0) goto LAB_00ae72c0;
                fVar65 = *(float *)(lVar33 + 0x2c);
                uVar51 = (ulong)(uint)fVar65;
                fVar66 = *(float *)(lVar33 + 0x30);
                param_4 = (ulong)(uint)fVar66;
                local_3f0 = CONCAT44(fVar65,fVar64);
                if ((0.0 <= fVar66) && (0.0 <= *(float *)(lVar33 + 0x48))) {
                  fVar66 = fVar66 - *(float *)(lVar33 + 0x48);
                  fVar65 = *(float *)(lVar33 + 0x24) - fVar65;
                  fVar67 = *(float *)(lVar33 + 0x34) - *(float *)(lVar33 + 0x4c);
                  fVar66 = SQRT(fVar66 * fVar66 + fVar67 * fVar67);
                  fVar74 = (float)((double)SQRT((fVar74 - fVar64) * (fVar74 - fVar64) +
                                                fVar65 * fVar65) * dVar70);
                  if (fVar74 <= fVar66) {
                    fVar74 = fVar66;
                  }
                  lVar33 = 0;
                  param_3 = (ulong)(uint)(fVar74 * 3.0);
                  pfVar56 = (float *)(lVar50 + 0x24 +
                                     (-((ulong)(uVar77 >> 0x1d) & 1) & 0xffffffff00000000 |
                                     ((ulong)uVar77 & 0x3fffffff) << 2) * 4);
                  uVar16 = 1;
                  uVar14 = 0x3600;
                  uVar76 = uVar58;
                  fVar74 = fVar63;
                  uVar25 = uVar58;
                  uVar44 = uVar39;
                  while( true ) {
                    fVar65 = (float)uVar25;
                    if (lVar33 == 0x12) break;
                    if ((0xd < (uint)lVar33) ||
                       (((int)uVar16 << (ulong)((uint)lVar33 & 0x1f) & (uint)uVar14) == 0)) {
                      fVar75 = pfVar56[-1];
                      fVar68 = *pfVar56;
                      fVar64 = (float)NEON_fminnm(fVar65,fVar75);
                      fVar67 = (float)uVar44;
                      fVar66 = fVar67;
                      if (fVar67 <= fVar75) {
                        fVar66 = fVar75;
                      }
                      if (fVar75 <= 0.0) {
                        fVar64 = fVar65;
                      }
                      uVar25 = (ulong)(uint)fVar64;
                      if (fVar75 <= 0.0) {
                        fVar66 = fVar67;
                      }
                      uVar44 = (ulong)(uint)fVar66;
                      if (0.0 < fVar68) {
                        uVar77 = NEON_fminnm((float)uVar76,fVar68);
                        uVar76 = (ulong)uVar77;
                        if (fVar74 <= fVar68) {
                          fVar74 = fVar68;
                        }
                      }
                    }
                    fVar64 = (float)FUN_00be2c30();
                    lVar33 = extraout_x8_44;
                    pfVar56 = extraout_x9_33;
                    uVar16 = extraout_x11_07;
                    uVar14 = extraout_x12_01;
                  }
                  fVar66 = ABS((float)uVar76 - (float)uVar51);
                  param_4 = (ulong)(uint)fVar66;
                  fVar74 = ABS(fVar65 - fVar64);
                  if (ABS(fVar65 - fVar64) <= fVar66) {
                    fVar74 = fVar66;
                  }
                  auVar82._0_8_ = (double)fVar74;
                  auVar82._8_8_ = 0;
                  FUN_00bf3424(auVar82);
                  local_3e0 = CONCAT44((int)param_3,(int)param_3);
                  goto LAB_00ae72dc;
                }
              }
              local_3e0 = 0;
              goto LAB_00ae72dc;
            }
            uVar51 = 0xffffff95;
            pcVar22 = "Half body config doest not support different scales of height and width\n";
            in_ZR = false;
          }
          else {
            auVar72._0_8_ = CONCAT44(0,DAT_00d99024);
            auVar72._8_8_ = 0;
            uVar51 = (ulong)(uint)DAT_00d98f40;
            lVar33 = 0;
            iVar46 = 0;
            param_3 = uVar51;
            param_4 = auVar72._0_8_;
            while( true ) {
              fVar74 = (float)uVar51;
              fVar65 = (float)param_3;
              fVar66 = (float)param_4;
              fVar64 = auVar72._0_4_;
              if (lVar33 == 0x90) break;
              lVar52 = lVar50 + ((long)((ulong)(uint)((int)lVar32 * 9) << 0x22) >> 0x1e) + lVar33;
              fVar67 = *(float *)(lVar52 + 0x20);
              if (0.0 < fVar67) {
                uVar77 = NEON_fminnm(fVar66,fVar67);
                param_4 = (ulong)uVar77;
                if (fVar65 <= fVar67) {
                  param_3 = (ulong)(uint)fVar67;
                }
                iVar46 = iVar46 + 1;
              }
              fVar66 = *(float *)(lVar52 + 0x24);
              lVar33 = lVar33 + 8;
              fVar65 = (float)NEON_fminnm(fVar64,fVar66);
              uVar76 = uVar51;
              if (fVar74 <= fVar66) {
                uVar76 = (ulong)(uint)fVar66;
              }
              if (fVar66 <= 0.0) {
                fVar65 = fVar64;
                uVar76 = uVar51;
              }
              uVar51 = uVar76;
              auVar72 = ZEXT416((uint)fVar65);
            }
            if (iVar46 < 1) {
              local_3f0 = NEON_fmov(0xbf800000,4);
              local_3e0 = local_3f0;
            }
            else {
              fVar68 = fVar65 - fVar66;
              fVar67 = (float)(int)plVar18[2] / (float)*(int *)((long)plVar18 + 0x14);
              fVar75 = (fVar74 - fVar64) * fVar67;
              param_4 = (ulong)(uint)fVar75;
              fVar67 = fVar68 / fVar67;
              if (fVar68 <= fVar75) {
                fVar67 = fVar74 - fVar64;
                fVar68 = fVar75;
              }
              local_3f0 = CONCAT44((fVar74 + fVar64) * 0.5,(fVar65 + fVar66) * 0.5);
              param_3 = CONCAT44(fVar67,fVar68);
              local_3e0 = param_3;
            }
LAB_00ae72dc:
            in_stack_000001e8 = 0;
            in_stack_000001d0 = 0;
            in_stack_000001c8 = 0;
            in_stack_000001e0 = 0;
            in_stack_000001d8 = 0;
            memset(&stack0x000001f0,0,0x170);
            FUN_00bdd740();
            uVar16 = FUN_00bf09bc(&stack0x000001c8,extraout_x1_07,&local_260);
            local_260 = local_260 & 0xffffffff00000000;
            in_stack_000001a8 = local_3e0;
            _iStack00000000000001a0 = local_3f0;
            in_stack_000001b8 = local_3e0;
            in_stack_000001b0 = local_3f0;
            in_stack_000001c0 = iVar80;
            FUN_0051136c(uVar16,(long)iVar80 << 2);
            FUN_00b8d834();
            lVar33 = extraout_x9_27;
            while (lVar33 < in_stack_000001c0) {
              FUN_00c0e188();
              *(undefined4 *)(extraout_x10_09 + extraout_x11_05 * 4) = 0x3f800000;
              lVar33 = extraout_x9_28;
            }
            in_stack_000001e8 = 0;
            in_stack_000001e0 = 0;
            FUN_00bce820(&stack0x000001f0,(long)in_stack_000001c0 * 3,&local_260);
            FUN_00bce820(&stack0x000002e0,6,&local_260);
            FUN_00bce820(&stack0x000002f8,6,&local_260);
            local_260 = CONCAT44(local_260._4_4_,7);
            FUN_0078514c(&stack0x00000310,2,&local_260);
            FUN_00bce820(&stack0x00000208,(long)in_stack_000001c0,&local_260);
            FUN_00bed548(&stack0x00000220,(long)in_stack_000001c0);
            FUN_0078514c();
            FUN_00bce820(&stack0x00000238,(long)in_stack_000001c0,&local_260);
            uVar16 = FUN_00bed548((long)in_stack_000001c0,&stack0x00000250);
            FUN_0051136c(uVar16,extraout_x8_38 << 1);
            uVar16 = FUN_00bed548((long)in_stack_000001c0,&stack0x00000268);
            FUN_0078514c(uVar16,extraout_x8_39 + 1);
            FUN_00bce820(&stack0x00000380,2,&local_260);
            FUN_00ba5d1c(&stack0x00000280);
            FUN_00bf2f14(&stack0x00000298);
            FUN_00c07bb8(&stack0x000002b0);
            FUN_00bf2f14(&stack0x000002c8);
            pvVar13 = operator_new(0x228,(nothrow_t *)puVar24);
            if (pvVar13 != (void *)0x0) {
              FUN_00aeb53c(pvVar13,lVar36);
            }
            FUN_00bd4bf8();
            uVar16 = FUN_00ae9a5c();
            if (extraout_d0 == 0) {
              pcVar22 = "r_fc_list size\n";
              auVar83 = FUN_00b96fd0(uVar16,"ion failed: %d\n","r_fc_list size\n");
              pcVar22 = pcVar22 + 0x52;
LAB_00ae75f8:
              __android_log_print(auVar83._0_8_,auVar83._8_8_,pcVar22);
            }
            else {
              lVar33 = FUN_00c0d840(0x228);
              if (lVar33 != 0) {
                FUN_00aeb53c(lVar33,extraout_x8_23);
              }
              uVar16 = FUN_00ae9a5c(&stack0x000003b0,lVar33);
              if (extraout_d0 == 0) {
                pcVar22 = "r_fc_list size\n";
                auVar83 = FUN_00b96fd0(uVar16,"ion failed: %d\n","r_fc_list size\n");
                pcVar22 = pcVar22 + 0x6f;
                goto LAB_00ae75f8;
              }
              lVar33 = FUN_00c0d840(0x28);
              if (lVar33 != 0) {
                FUN_00aeca28(lVar33,(long)((int)lVar35 * 6));
              }
              puVar19 = (undefined8 *)FUN_00b8bc50();
              *puVar19 = &PTR____shared_weak_count_01325ec0;
              puVar19[1] = 0;
              puVar19[2] = 0;
              puVar19[3] = lVar33;
              uVar16 = FUN_003f2aac(extraout_var);
              if (extraout_d0 == 0) {
                pcVar22 = "r_fc_list size\n";
                auVar83 = FUN_00b96fd0(uVar16,"ion failed: %d\n","r_fc_list size\n");
                pcVar22 = pcVar22 + 0x92;
                goto LAB_00ae75f8;
              }
              FUN_007da070((char *)((long)plVar18 + 0xa2c),extraout_d0 + 0x208);
              if (1 < uVar11) {
                __android_log_print(6,"SMASH_E_LOG ","set_rotmat - invalid settings of handness\n");
                auVar83._8_8_ = "SMASH_E_LOG ";
                auVar83._0_8_ = 6;
                pcVar22 = "ato - set rotmat error\n";
                goto LAB_00ae75f8;
              }
              *(uint *)(extraout_d0 + 0x218) = uVar11;
            }
            uVar51 = plVar18[0x13c];
            if (uVar51 < (ulong)plVar18[0x13d]) {
              FUN_00c09714();
              plVar18[0x13c] = uVar51 + 0x230;
            }
            else {
              FUN_00bf2088(uVar51 - plVar18[0x13b]);
              FUN_00ae94e0(plVar18 + 0x13b);
              uVar16 = FUN_00bd0184();
              FUN_00ae953c(&local_260,uVar16,(extraout_x8_40 - extraout_x9_29) / 0x230,
                           plVar18 + 0x13d);
              lVar33 = local_250;
              FUN_00c09714();
              local_250 = lVar33 + 0x230;
              FUN_00ae9590(plVar18 + 0x13b,&local_260);
              FUN_00ae95e0(&local_260);
            }
            FUN_00ae3b20(&stack0x000001a0);
            uVar76 = FUN_00bdd4dc(plVar18[0x13b]);
            in_ZR = *(int *)(extraout_x8_41 + (extraout_x9_30 + -1) * 0x230 + 0x1f8) == 1;
            if ((bool)in_ZR) {
              lVar33 = extraout_x8_41 + (extraout_x9_30 + -1) * 0x230;
              if (*(int *)(lVar33 + 0x1c0) < 0) {
                if (iVar86 == 0) {
                  uVar51 = 0;
                  *(int *)(lVar33 + 0x1c0) = (int)plVar18[0x144];
                }
                else {
                  uVar16 = FUN_00bac6e0();
                  for (uVar51 = extraout_x9_32;
                      (uVar76 = extraout_x11_06, extraout_x11_06 != uVar51 &&
                      (uVar76 = uVar51, *(int *)(extraout_x10_10 + uVar51 * 4) != 0));
                      uVar51 = uVar51 + 1) {
                  }
                  in_ZR = extraout_x11_06 == (uVar76 & 0xffffffff);
                  if ((bool)in_ZR) {
                    pcVar22 = "ch_obj";
                    auVar81 = FUN_00b96fd0(uVar16,"ion failed: %d\n","ch_obj");
                    __android_log_print(auVar81._0_8_,auVar81._8_8_,pcVar22 + 0x915);
                    uVar51 = 0xffffff9b;
                  }
                  else {
                    *(undefined4 *)(extraout_x10_10 + (uVar76 & 0xffffffff) * 4) = 1;
                    iVar29 = (int)uVar76;
                    *extraout_x8_43 = iVar29;
                    iVar46 = (int)plVar18[0x144];
                    uVar51 = 0;
                    in_ZR = iVar29 + 1 == iVar46;
                    if (iVar46 <= iVar29 + 1) {
                      iVar46 = iVar29 + 1;
                    }
                    *(int *)(plVar18 + 0x144) = iVar46;
                  }
                }
              }
              else {
                uVar51 = 0;
              }
              uVar76 = FUN_00bd0184();
              piVar26 = (int *)(extraout_x9_31 + ((extraout_x8_42 - extraout_x9_31) / 0x230) * 0x230
                               + -0x70);
              if (*piVar26 < 0) {
                *piVar26 = -1;
              }
              if ((int)uVar51 == 0) goto LAB_00ae76d0;
              goto LAB_00ae7960;
            }
            uVar51 = 0xffffff9b;
            pcVar22 = "ucs - ato error\n";
          }
          FUN_00b8c554(uVar76,"SMASH_E_LOG ",pcVar22);
          goto LAB_00ae7960;
        }
LAB_00ae76d0:
      }
      uVar51 = 0;
LAB_00ae7960:
      FUN_00bda618();
      FUN_00bc4410();
      FUN_003e8658();
      FUN_003e8658(&stack0x00000aa0);
      FUN_00bffc28();
      FUN_003e8658(&stack0x00000e28);
      FUN_00bcdbc4();
      FUN_003e8658();
      FUN_00bd9438();
      if ((int)uVar51 == 0) goto LAB_00ae4c68;
      goto LAB_00ae4c14;
    }
    auVar81._8_8_ = "SMASH_E_LOG ";
    auVar81._0_8_ = uVar16;
  }
LAB_00ae4c0c:
  FUN_00b8c554(auVar81._0_8_,auVar81._8_8_);
  uVar51 = 0xffffff9b;
  goto LAB_00ae4c14;
LAB_00ae87c0:
  FUN_00b8c554();
  uVar51 = 0xffffff9b;
LAB_00ae8954:
  FUN_007a7328(&local_390);
LAB_00ae8960:
  FUN_003e8658(local_3a8);
LAB_00ae4c14:
  FUN_00ba226c(*(undefined8 *)(lVar5 + 0x28));
joined_r0x00ae78b8:
  if (!(bool)in_ZR) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  FUN_00b8b974(uVar51);
  return;
}



/* ===== Function: FUN_00a9f8b0 @ 00a9f8b0  size=2764  depth=0
   signature: undefined FUN_00a9f8b0() ===== */

void FUN_00a9f8b0(undefined1 param_1 [16],undefined1 param_2 [16],undefined1 param_3 [16],
                 undefined1 param_4 [16])

{
  undefined4 *puVar1;
  undefined4 uVar2;
  bool bVar3;
  int iVar4;
  long lVar5;
  long lVar6;
  long lVar7;
  undefined8 uVar8;
  undefined8 *puVar9;
  undefined1 uVar11;
  undefined4 extraout_w8;
  undefined4 extraout_w8_00;
  undefined4 extraout_w8_01;
  undefined4 extraout_w8_02;
  long extraout_x8;
  long extraout_x8_00;
  long extraout_x8_01;
  long extraout_x8_02;
  long extraout_x8_03;
  undefined4 extraout_w9;
  long lVar12;
  ulong extraout_x9;
  long extraout_x9_00;
  undefined4 extraout_w10;
  long extraout_x10;
  long *extraout_x11;
  long *extraout_x11_00;
  long extraout_x11_01;
  long *extraout_x12;
  long extraout_x13;
  long unaff_x25;
  ulong uVar13;
  int *piVar14;
  float fVar15;
  float fVar16;
  float fVar17;
  float fVar18;
  undefined8 uVar20;
  float fVar21;
  undefined4 uVar22;
  undefined8 uVar23;
  float fVar24;
  undefined4 uVar25;
  undefined8 uVar26;
  float fVar27;
  undefined1 auVar28 [16];
  float local_888;
  float fStack_884;
  float local_880;
  float fStack_87c;
  float local_878;
  float fStack_874;
  undefined1 local_870 [8];
  long local_868 [4];
  undefined1 auStack_848 [24];
  undefined1 local_830 [8];
  undefined8 local_828;
  undefined8 *local_818;
  undefined8 local_810;
  undefined8 *local_800;
  undefined8 uStack_7f8;
  undefined1 local_7f0 [8];
  undefined1 *local_7e8;
  undefined8 local_7e0;
  undefined1 local_7d8 [16];
  undefined8 uStack_7c8;
  undefined8 local_7c0;
  uint *puStack_7b8;
  undefined8 local_7b0;
  undefined8 uStack_7a8;
  ulong local_7a0;
  undefined8 local_798;
  undefined8 uStack_790;
  undefined8 uStack_788;
  undefined8 local_780;
  ulong local_778;
  float local_770 [6];
  undefined8 local_758;
  undefined8 local_750;
  undefined1 *local_748;
  undefined8 local_740;
  undefined8 local_738;
  undefined4 local_730;
  undefined4 local_728;
  float local_724 [212];
  undefined8 local_3d4;
  float local_3cc;
  uint local_3c8 [2];
  undefined8 local_3c0;
  undefined8 local_3b8;
  undefined8 local_378;
  undefined1 auStack_368 [96];
  uint local_308 [2];
  undefined8 local_300;
  undefined8 local_2f8;
  undefined8 local_2b8;
  uint local_2a8 [2];
  undefined8 local_2a0;
  undefined8 local_298;
  undefined8 local_258;
  undefined1 auStack_248 [96];
  undefined1 local_1e8 [8];
  long *local_1e0;
  undefined8 local_1d8;
  undefined1 auStack_188 [96];
  undefined8 local_128;
  undefined4 local_120;
  undefined4 uStack_11c;
  undefined4 uStack_118;
  undefined4 local_114;
  Matrix4x4f aMStack_c8 [96];
  undefined8 local_68;
  undefined4 local_60;
  undefined4 local_5c;
  undefined4 local_58;
  undefined4 uStack_54;
  undefined4 local_50;
  undefined4 local_4c;
  undefined8 local_48;
  undefined4 local_40;
  undefined8 local_3c;
  undefined8 local_34;
  undefined4 local_2c;
  long local_8;
  long *plVar10;
  ulong uVar19;
  
  uVar26 = param_4._8_8_;
  uVar25 = param_4._4_4_;
  fVar18 = param_4._0_4_;
  uVar23 = param_3._8_8_;
  uVar22 = param_3._4_4_;
  fVar15 = param_3._0_4_;
  uVar20 = param_2._8_8_;
  uVar19 = param_2._0_8_;
  FUN_00b8b700();
  auVar28 = FUN_00b8f864();
  plVar10 = auVar28._8_8_;
  lVar12 = auVar28._0_8_;
  local_8 = extraout_x8;
  if (*(long *)(lVar12 + 0x118) == 0) {
    lVar12 = FUN_00b90f0c("up_0_bright_high");
    uVar20 = FUN_00b8ec60(lVar12 + 0xfa1);
    FUN_00b8b820(uVar20,0x67,10);
    uVar20 = 4;
    goto LAB_00a9fa68;
  }
  if (*plVar10 == 0) {
    FUN_00b934d4();
    lVar12 = FUN_00b934c4("up_0_bright_high");
    auVar28 = FUN_00bd60cc(lVar12 + 0xfa1);
LAB_00a9fa60:
    FUN_00b8b820(auVar28._0_8_,auVar28._8_8_);
  }
  else {
    lVar5 = FUN_00b9148c(plVar10 + 3);
    if (lVar5 == 0) {
      FUN_00b997d4();
      lVar12 = FUN_00b997a4("up_0_bright_high");
      auVar28 = FUN_00bf2e30(lVar12 + 0xfa1);
      goto LAB_00a9fa60;
    }
    lVar6 = FUN_00b9e338(plVar10[8],plVar10[9]);
    if (lVar6 != 0) {
      lVar5 = *(long *)(lVar5 + 0x18);
      if (lVar5 == 0) {
        FUN_00b997c4();
        lVar12 = FUN_00b99794("up_0_bright_high");
        auVar28._8_8_ = 0x74;
        auVar28._0_8_ = lVar12 + 0xfa1;
      }
      else if (*(long *)(lVar5 + 0x60) == 0) {
        FUN_00b997b4();
        lVar12 = FUN_00b99784("up_0_bright_high");
        auVar28 = FUN_00be4374(lVar12 + 0xfa1);
      }
      else {
        lVar6 = *(long *)(lVar6 + 0x18);
        if (lVar6 != 0) {
          uVar2 = *(undefined4 *)(lVar5 + 0x34);
          FUN_00b91c64(*(undefined4 *)(lVar5 + 0x38));
          auVar28 = FUN_00a325d4();
          FUN_00b8fe5c(auVar28._0_8_,auVar28._8_8_,auVar28._0_8_ & 0xffffffff);
          lVar7 = FUN_00be1ca0();
          FUN_00c05834();
          plVar10 = (long *)(lVar7 + 0x38);
          iVar4 = *(int *)(lVar12 + 0x2b4);
          if (*(int *)(lVar6 + 0x6aa8) <= *(int *)(lVar12 + 0x2b4)) {
            iVar4 = *(int *)(lVar6 + 0x6aa8);
          }
          if (iVar4 == 0) {
            FUN_00b93f48(0xffffffff);
            *(undefined4 *)(lVar12 + 0x2b8) = extraout_w8_00;
            FUN_0072bd68();
            uVar20 = 0x81;
            goto LAB_00a9fa68;
          }
          piVar14 = (int *)(lVar6 + 0x534);
          for (uVar13 = 0; (long)uVar13 < (long)iVar4; uVar13 = uVar13 + 1) {
            if (*(int *)(lVar12 + 0x2b8) == *piVar14) goto LAB_00a9faa4;
            piVar14 = piVar14 + 0x14a;
          }
          uVar13 = 0;
LAB_00a9faa4:
          iVar4 = FUN_00a52704(*(undefined4 *)(lVar5 + 0x34),*(undefined4 *)(lVar5 + 0x40),
                               *(undefined4 *)(lVar5 + 0x44));
          uVar8 = FUN_00c4c100(auStack_368,extraout_w8,uVar2,0x18,*(undefined8 *)(lVar5 + 0x60),
                               (long)iVar4);
          if ((*(int *)(lVar5 + 0x3c) == 0) || (*(int *)(lVar5 + 0x3c) == 1)) {
            FUN_00c4bf20(local_3c8);
            FUN_00b90b48();
            FUN_00bf3d34(&local_750,&local_7c0);
            local_740 = 0;
            local_750 = CONCAT44(local_750._4_4_,extraout_w8_01);
            local_7c0 = CONCAT44(local_7c0._4_4_,extraout_w9);
            local_7b0 = 0;
            puStack_7b8 = local_3c8;
            local_748 = auStack_368;
            FUN_00cf2d98();
            local_750 = local_3b8;
            local_728 = 3;
            local_748 = (undefined1 *)NEON_rev64(local_3c0,4);
            local_738 = local_378;
            lVar5 = lVar6 + 0x18 + (uVar13 & 0xffffffff) * 0x528;
            local_730 = 0;
            local_740 = CONCAT44(local_740._4_4_,(local_3c8[0] >> 3 & 0x1ff) + 1);
            *(undefined4 *)(lVar12 + 0x2b8) = *(undefined4 *)(lVar5 + 0x51c);
            local_3d4 = *(undefined8 *)(lVar5 + 0x50c);
            local_3cc = *(float *)(lVar5 + 0x514);
            for (lVar5 = 0x30; lVar5 != 0x380; lVar5 = lVar5 + 8) {
              puVar1 = (undefined4 *)(lVar6 + uVar13 * 0x528 + lVar5);
              *(undefined4 *)((long)&local_758 + lVar5 + 4) = puVar1[-1];
              *(undefined4 *)((long)&local_750 + lVar5) = *puVar1;
            }
            local_770[2] = 0.0;
            local_770[3] = 0.0;
            local_770[0] = 0.0;
            local_770[1] = 0.0;
            local_758 = 0;
            local_770[4] = 0.0;
            local_770[5] = 0.0;
            uStack_788 = 0;
            uStack_790 = 0;
            local_778 = 0;
            local_780 = 0;
            uStack_7a8 = 0;
            local_7b0 = 0;
            local_798 = 0;
            local_7a0 = 0;
            puStack_7b8 = (uint *)0x0;
            local_7c0 = 0;
            piVar14 = *(int **)(lVar12 + 0x118);
            thunk_FUN_00bbd08c(auStack_248,&local_750);
            fVar24 = local_3cc;
            fVar17 = (float)local_3d4;
            fVar21 = local_3d4._4_4_;
            FUN_00c4bf20(local_2a8);
            FUN_00c4bf20(local_308);
            if ((char)piVar14[0x28] == '\0') {
              FUN_00c0e66c();
LAB_00a9ff1c:
              uVar11 = 0;
            }
            else {
              fVar16 = -(float)piVar14[0xd];
              uVar19 = (ulong)(uint)fVar16;
              uVar20 = 0;
              bVar3 = true;
              if ((fVar17 <= (float)piVar14[0xd]) && (bVar3 = false, !NAN(fVar17) && !NAN(fVar16)))
              {
                bVar3 = fVar17 < fVar16;
              }
              if (bVar3) goto LAB_00a9ff1c;
              fVar17 = -(float)piVar14[0xe];
              uVar19 = (ulong)(uint)fVar17;
              uVar20 = 0;
              bVar3 = true;
              if ((fVar21 <= (float)piVar14[0xe]) && (bVar3 = false, !NAN(fVar21) && !NAN(fVar17)))
              {
                bVar3 = fVar21 < fVar17;
              }
              if (bVar3) goto LAB_00a9ff1c;
              fVar17 = -(float)piVar14[0xf];
              uVar19 = (ulong)(uint)fVar17;
              uVar20 = 0;
              bVar3 = true;
              if ((fVar24 <= (float)piVar14[0xf]) && (bVar3 = false, !NAN(fVar24) && !NAN(fVar17)))
              {
                bVar3 = fVar24 < fVar17;
              }
              if (bVar3) goto LAB_00a9ff1c;
              FUN_00c4bf20(&local_68);
              FUN_00c4bf20(aMStack_c8);
              FUN_00c4c04c(auStack_188,auStack_248);
              FUN_00c03b88(local_724[0xd2] + local_724[0xd0],local_724[0xd3] + local_724[0xd1],
                           local_724[0x62]);
              fVar17 = hypotf(local_724[0xd2] - local_724[0xd0],local_724[0xd3] - local_724[0xd1]);
              fVar18 = (float)piVar14[0xb];
              fVar16 = fVar18 * fVar17;
              fVar15 = (float)FUN_00ba8334();
              hypotf(fVar15,fVar18);
              fVar21 = (local_724[0x3e] + local_724[2]) * 0.5;
              fVar24 = (local_724[0x3f] + local_724[3]) * 0.5;
              fVar15 = (float)piVar14[0x12] + (float)piVar14[0x12] + 1.0;
              fVar27 = (fVar16 * (local_724[0xd2] - local_724[0xd0])) / fVar17;
              fVar17 = (fVar16 * (local_724[0xd3] - local_724[0xd1])) / fVar17;
              fVar16 = (float)piVar14[0x10] + (float)piVar14[0x10] + 1.0;
              fVar18 = fVar21 - fVar27 * fVar15;
              fStack_884 = fVar24 - fVar17 * fVar15;
              local_878 = fVar18 + fVar16 * fVar17;
              fStack_874 = fStack_884 - fVar27 * fVar16;
              fVar16 = (float)piVar14[0x13] + (float)piVar14[0x13] + 1.0;
              fStack_87c = (float)piVar14[0x11] + (float)piVar14[0x11] + 1.0;
              fVar15 = fStack_87c * -fVar17;
              fStack_87c = fStack_87c * fVar27;
              fVar18 = fVar15 + fVar18;
              uVar25 = 0;
              uVar26 = 0;
              fStack_884 = fStack_87c + fStack_884;
              uVar19 = (ulong)(uint)fStack_884;
              uVar20 = 0;
              fVar15 = fVar16 * fVar27 + fVar21 + fVar15;
              uVar22 = 0;
              uVar23 = 0;
              fStack_87c = fVar16 * fVar17 + fVar24 + fStack_87c;
              local_868[1] = 0;
              local_868[2] = 0;
              local_868[0] = 0;
              local_888 = fVar18;
              local_880 = fVar15;
              FUN_00796e24(local_868,3);
              FUN_00bdfc08();
              FUN_0066f950();
              FUN_0066f950(local_868,&local_888);
              FUN_00ba4760();
              FUN_0066f950();
              local_120 = 0;
              local_128 = 0;
              uStack_11c = (undefined4)*(undefined8 *)(piVar14 + 9);
              uStack_118 = (undefined4)((ulong)*(undefined8 *)(piVar14 + 9) >> 0x20);
              local_114 = uStack_11c;
              FUN_0061c928(local_7d8,&local_128,3);
              local_7e0 = FUN_00bd05e8();
              local_7e8 = local_7d8;
              local_1e0 = local_868;
              local_1d8 = local_7e0;
              FUN_00cd0838(&local_128,local_1e8,local_7f0);
              local_810 = FUN_00b90b48(0);
              uStack_7f8 = 0;
              local_828 = *(undefined8 *)piVar14;
              local_818 = &local_128;
              local_800 = &local_68;
              local_7e8 = auStack_188;
              local_7e0 = local_810;
              FUN_0074f284(local_1e8);
              FUN_00c0721c(local_7f0);
              FUN_00b964a0();
              FUN_003e8658(local_7d8);
              FUN_00bdfc14();
              FUN_005d8e6c(aMStack_c8,&local_128);
              FUN_00bf71e4();
              FUN_00bd7360();
              memcpy(*(void **)(piVar14 + 0x22),(void *)CONCAT44(uStack_54,local_58),
                     (long)piVar14[8]);
              if ((piVar14[1] != piVar14[3]) || (*piVar14 != piVar14[2])) {
                FUN_00b8df10(&local_128,local_1e8,local_830);
                uStack_118 = 0;
                local_114 = 0;
                local_128 = CONCAT44(local_128._4_4_,extraout_w10);
                local_120 = SUB84(extraout_x11,0);
                uStack_11c = (undefined4)((ulong)extraout_x11 >> 0x20);
                local_1d8 = 0;
                local_1e0 = extraout_x11;
                FUN_00cc8734();
              }
              iVar4 = piVar14[5];
              for (lVar5 = 0; lVar5 < iVar4; lVar5 = lVar5 + 1) {
                fVar15 = 0.0078125;
                uVar22 = 0;
                uVar23 = 0;
                fVar17 = (float)NEON_ucvtf((uint)*(byte *)(CONCAT44(uStack_54,local_58) + lVar5));
                fVar17 = fVar17 * 0.0078125 + -1.0;
                uVar19 = (ulong)(uint)fVar17;
                uVar20 = 0;
                *(float *)(*(long *)(piVar14 + 0x16) + lVar5 * 4) = fVar17;
              }
              uVar8 = *(undefined8 *)(piVar14 + 0x14);
              FUN_00ba4e10(auStack_848);
              espresso::Thrustor::SetInput
                        (uVar8,auStack_848,*(undefined8 *)(piVar14 + 0x16),piVar14[4] * 0xc,
                         piVar14[2],piVar14[3]);
              FUN_00b8fb9c();
              espresso::Thrustor::Inference();
              espresso::Thrustor::Extract(*(basic_string **)(piVar14 + 0x14));
              lVar5 = local_868[0];
              FUN_00c4bf54(&local_128,piVar14[3],piVar14[2],0x18);
              lVar6 = 0;
              uVar13 = 0x43000000;
              while (lVar6 < piVar14[6]) {
                uVar19 = uVar13 & 0xffffffff;
                uVar20 = 0;
                FUN_00beffd8(*(undefined4 *)(lVar5 + lVar6 * 4));
                FUN_00bc5a50();
                lVar6 = extraout_x8_02;
                uVar13 = extraout_x9;
              }
              if ((piVar14[1] != piVar14[3]) || (*piVar14 != piVar14[2])) {
                FUN_00b8df10(local_1e8,local_7d8,local_870);
                local_1d8 = 0;
                uStack_7c8 = 0;
                local_1e0 = extraout_x11_00;
                FUN_00cc8734();
              }
              memcpy(*(void **)(piVar14 + 0x1c),(void *)CONCAT44(local_114,uStack_118),
                     (long)piVar14[7]);
              FUN_00b8d834();
              lVar6 = extraout_x8_03;
              for (lVar5 = extraout_x9_00; lVar5 < extraout_x11_01; lVar5 = lVar5 + 1) {
                for (lVar7 = 0; lVar7 < (int)extraout_x10; lVar7 = lVar7 + 1) {
                  local_770[(int)lVar6 + (int)lVar7] =
                       (float)*(double *)(extraout_x13 + lVar5 * *extraout_x12 + lVar7 * 8);
                }
                lVar6 = lVar6 + extraout_x10;
              }
              FUN_00b8e56c(local_1e8,piVar14[1],*piVar14,0x18,*(undefined8 *)(piVar14 + 0x1c));
              FUN_005d8e6c(local_2a8,local_1e8);
              FUN_00bf73dc();
              FUN_00b8e56c(local_1e8,piVar14[1],*piVar14,0x10,*(undefined8 *)(piVar14 + 0x22));
              FUN_005d8e6c(local_308,local_1e8);
              FUN_00bf73dc();
              FUN_00bf71e4();
              FUN_00c4c350(aMStack_c8);
              FUN_00c0ec00();
              local_798 = local_298;
              local_780 = local_258;
              uStack_7a8 = local_2b8;
              uStack_790 = NEON_rev64(local_2a0,4);
              local_7c0 = local_2f8;
              puStack_7b8 = (uint *)NEON_rev64(local_300,4);
              uStack_788 = CONCAT44(uStack_788._4_4_,(local_2a8[0] >> 3 & 0x1ff) + 1);
              uVar11 = 1;
              local_778 = local_778 & 0xffffffff00000000;
              local_7a0 = local_7a0 & 0xffffffff00000000;
              local_7b0 = CONCAT44(local_7b0._4_4_,(local_308[0] >> 3 & 0x1ff) + 1);
            }
            local_758 = CONCAT71(local_758._1_7_,uVar11);
            FUN_00c4c350(local_308);
            FUN_00bdb370();
            FUN_00c4c350(auStack_248);
            lVar5 = *plVar10;
            if (lVar5 == 0) {
              puVar9 = (undefined8 *)FUN_00b9f064();
              *(undefined4 *)(puVar9 + 1) = 0;
              puVar9[3] = 0;
              puVar9[4] = 0;
              puVar9[2] = 0;
              *(undefined4 *)(puVar9 + 5) = 0;
              puVar9[7] = 0;
              puVar9[8] = 0;
              puVar9[6] = 0;
              *puVar9 = &PTR_retain_01322d30;
              *(undefined4 *)(puVar9 + 9) = 0;
              FUN_004161ac(puVar9 + 10);
              FUN_00b9b958(&DAT_00f13500);
              auVar28 = FUN_00b8fe5c(0xffffffff);
              *(undefined1 *)(puVar9 + 0x13) = 0;
              *(undefined4 *)((long)puVar9 + 0x9c) = 0;
              *(undefined4 *)(puVar9 + 0x14) = 0;
              puVar9[0x12] = uVar20;
              puVar9[0x11] = uVar19;
              *(undefined1 (*) [16])(puVar9 + 0xf) = auVar28;
              puVar9[0xe] = uVar26;
              puVar9[0xd] = CONCAT44(uVar25,fVar18);
              puVar9[0xc] = uVar23;
              puVar9[0xb] = CONCAT44(uVar22,fVar15);
              *(undefined4 *)((long)puVar9 + 0xa4) = extraout_w8_02;
              FUN_0072bd68();
              lVar5 = *plVar10;
            }
            *(uint **)(lVar5 + 0x18) = puStack_7b8;
            *(undefined8 *)(lVar5 + 0x10) = local_7c0;
            *(undefined4 *)(lVar5 + 0x20) = (undefined4)local_7b0;
            *(int *)(lVar5 + 0x24) = (int)uStack_7a8;
            *(undefined4 *)(lVar5 + 0x28) = (undefined4)local_7a0;
            *(undefined8 *)(lVar5 + 0x38) = uStack_790;
            *(undefined8 *)(lVar5 + 0x30) = local_798;
            *(undefined4 *)(lVar5 + 0x40) = (undefined4)uStack_788;
            *(int *)(lVar5 + 0x44) = (int)local_780;
            *(undefined4 *)(lVar5 + 0x48) = (undefined4)local_778;
            *(undefined1 *)(lVar5 + 0x98) = (undefined1)local_758;
            *(undefined4 *)(lVar5 + 0x9c) = uVar2;
            *(undefined4 *)(lVar5 + 0xa0) = extraout_w8;
            *(undefined4 *)(lVar5 + 0xa4) = *(undefined4 *)(lVar12 + 0x2b8);
            FUN_00c03b74(*(undefined8 *)(lVar5 + 0x50),6);
            puVar9 = *(undefined8 **)(*(long *)(extraout_x8_00 + 0x50) + 0x10);
            for (lVar12 = 0; lVar12 != 0x18; lVar12 = lVar12 + 4) {
              *(undefined4 *)((long)puVar9 + lVar12) = *(undefined4 *)((long)local_770 + lVar12);
            }
            local_68 = *puVar9;
            local_60 = 0;
            local_5c = *(undefined4 *)(puVar9 + 1);
            local_58 = *(undefined4 *)((long)puVar9 + 0xc);
            uStack_54 = *(undefined4 *)(puVar9 + 2);
            local_50 = 0;
            local_4c = *(undefined4 *)((long)puVar9 + 0x14);
            local_40 = 0x3f800000;
            local_48 = 0;
            local_34 = 0;
            local_3c = 0;
            local_2c = 0x3f800000;
            AmazingEngine::Matrix4x4f::Matrix4x4f(aMStack_c8,(float *)&local_68);
            AmazingEngine::Matrix4x4f::Transpose();
            auVar28 = FUN_00b9ff3c(*plVar10);
            *(undefined1 (*) [16])(extraout_x8_01 + 0x58) = auVar28;
            *(undefined8 *)(extraout_x8_01 + 0x70) = uVar20;
            *(ulong *)(extraout_x8_01 + 0x68) = uVar19;
            *(undefined8 *)(extraout_x8_01 + 0x80) = uVar23;
            *(ulong *)(extraout_x8_01 + 0x78) = CONCAT44(uVar22,fVar15);
            *(undefined8 *)(extraout_x8_01 + 0x90) = uVar26;
            *(ulong *)(extraout_x8_01 + 0x88) = CONCAT44(uVar25,fVar18);
            FUN_00c4c350(local_3c8);
            uVar20 = 1;
          }
          else {
            FUN_00bac4cc(uVar8,0xa1);
            uVar20 = FUN_00b8f680();
            FUN_00b9b4d8(uVar20,0xa2,10);
            uVar20 = 0x10;
          }
          FUN_00bd6ed0();
          goto LAB_00a9fa68;
        }
        FUN_00bafa90();
        lVar12 = FUN_00bafa80("up_0_bright_high");
        auVar28 = FUN_00bae368(lVar12 + 0xfa1);
      }
      goto LAB_00a9fa60;
    }
    FUN_00b8c0b0("../../../../../../bach/Bach/src/Bach/Algorithm/FemaleGan/BachAlgorithmFemaleGan.cpp"
                 ,0x6f);
  }
  uVar20 = 0x20;
LAB_00a9fa68:
  if (*(long *)(unaff_x25 + 0x28) != local_8) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  FUN_00b8b974(uVar20);
  return;
}



/* ===== Function: FUN_00796218 @ 00796218  size=1052  depth=0
   signature: undefined FUN_00796218() ===== */

void FUN_00796218(undefined8 param_1,uint param_2)

{
  long lVar1;
  undefined1 uVar2;
  undefined8 uVar3;
  undefined1 *puVar4;
  undefined1 auStack_1e8 [24];
  undefined1 auStack_1d0 [24];
  undefined1 auStack_1b8 [24];
  undefined1 auStack_1a0 [24];
  undefined1 auStack_188 [24];
  undefined1 auStack_170 [24];
  undefined1 auStack_158 [24];
  undefined1 auStack_140 [24];
  undefined1 auStack_128 [24];
  undefined1 auStack_110 [24];
  undefined1 auStack_f8 [24];
  undefined1 auStack_e0 [24];
  undefined1 auStack_c8 [24];
  undefined1 auStack_b0 [96];
  undefined1 auStack_50 [32];
  
  lVar1 = tpidr_el0;
  FUN_00bbc9b4(*(undefined8 *)(lVar1 + 0x28));
  uVar3 = FUN_00796634();
  uVar2 = param_2 == 0x12;
  switch((ulong)param_2) {
  case 0:
    uVar3 = FUN_00bb916c(uVar3,"base120");
    FUN_00b9dbd8(uVar3,"fc_landmark_s1");
    FUN_00ba9510();
    FUN_00bc3678();
    FUN_00bc3668();
    uVar3 = FUN_00bc3658();
    FUN_00b977f0(uVar3,&DAT_00ea48e0);
    FUN_00b90ec0(auStack_f8,auStack_c8);
    FUN_00bee394();
    FUN_00ba555c();
    FUN_003e8630(auStack_f8);
    FUN_00bb002c();
    do {
      FUN_00b9a91c();
    } while (!(bool)uVar2);
    break;
  default:
    goto switchD_00796260_caseD_1;
  case 6:
    FUN_00b9dbd8(uVar3,&DAT_00e66174);
    FUN_003e9458(auStack_50,"pred_landmark");
    uVar3 = FUN_00b90ec0(auStack_e0,auStack_50);
    FUN_00b977f0(uVar3,&DAT_00ea48e0);
    FUN_00b90ec0(auStack_128,auStack_c8);
    FUN_00bd8b54();
    FUN_00ba555c();
    puVar4 = auStack_128;
    goto LAB_007964b4;
  case 8:
    FUN_00b9dbd8(uVar3,"eyecls");
    FUN_00be58cc();
    uVar3 = FUN_00b90ec0(auStack_e0,auStack_50);
    FUN_00b977f0(uVar3,&DAT_00ea48e0);
    FUN_00b90ec0(auStack_140,auStack_c8);
    FUN_00bd8b54();
    FUN_00bb2d38();
    puVar4 = auStack_140;
    goto LAB_007964b4;
  case 9:
    FUN_00b9dbd8(uVar3,&DAT_00e85571);
    FUN_00be58cc();
    uVar3 = FUN_00b90ec0(auStack_e0,auStack_50);
    FUN_00b977f0(uVar3,&DAT_00ea48e0);
    FUN_00b90ec0(auStack_158,auStack_c8);
    FUN_00bd8b54();
    FUN_00bb2d38();
    puVar4 = auStack_158;
LAB_007964b4:
    FUN_003e8630(puVar4);
    FUN_003e86a0(auStack_c8);
    FUN_003e8630(auStack_e0);
    FUN_00bfcf60();
    break;
  case 0xb:
    uVar3 = FUN_00bb916c(uVar3,"base120det");
    FUN_00b9dbd8(uVar3,"fc_landmark_s1");
    FUN_00ba9510();
    FUN_00bc3678();
    FUN_00bc3668();
    uVar3 = FUN_00bc3658();
    FUN_00b977f0(uVar3,&DAT_00ea48e0);
    FUN_00b90ec0(auStack_110,auStack_c8);
    FUN_00bee394();
    FUN_00ba555c();
    FUN_003e8630(auStack_110);
    FUN_00bb002c();
    do {
      FUN_00b9a91c();
    } while (!(bool)uVar2);
    break;
  case 0xd:
    FUN_00b9dbd8(uVar3,"detect");
    FUN_00bb10bc();
    FUN_00b90ec0(auStack_170,auStack_50);
    FUN_00bd8b54();
    FUN_00bb2d38();
    puVar4 = auStack_170;
    goto LAB_00796600;
  case 0xe:
    uVar3 = FUN_00bb916c(uVar3,"extra");
    FUN_00b9dbd8(uVar3,&DAT_00e994c2);
    FUN_00ba9510();
    FUN_00bfcf1c((ulong)param_2 + 0x30,"not equal to 0 or tot_center_num\n");
    uVar3 = FUN_00b9b840(auStack_50,auStack_b0);
    FUN_00b977f0(uVar3,&DAT_00ea48e0);
    FUN_00b90ec0(auStack_1b8,auStack_c8);
    FUN_00bee394();
    FUN_00ba555c();
    FUN_00ba870c();
    FUN_00bb002c();
    do {
      FUN_00b9a91c();
    } while (!(bool)uVar2);
    break;
  case 0xf:
    uVar3 = FUN_00bb916c(uVar3,"partface");
    FUN_00b9dbd8(uVar3,"fc_landmark_s1");
    FUN_00ba9510();
    FUN_00bc3678();
    FUN_00bc3668();
    uVar3 = FUN_00bc3658();
    FUN_00b977f0(uVar3,&DAT_00ea48e0);
    FUN_00b90ec0(auStack_1e8,auStack_c8);
    FUN_00bee394();
    FUN_00ba555c();
    FUN_00ba099c();
    FUN_00bb002c();
    do {
      FUN_00b9a91c();
    } while (!(bool)uVar2);
    break;
  case 0x10:
    FUN_00bdaffc();
    FUN_00b9dbd8();
    FUN_00bb10bc();
    FUN_00b90ec0(auStack_188,auStack_50);
    FUN_00bd8b54();
    FUN_00ba555c();
    puVar4 = auStack_188;
    goto LAB_00796600;
  case 0x11:
    FUN_00b9dbd8(uVar3,"slowimage");
    FUN_00bb10bc();
    FUN_00b90ec0(auStack_1a0,auStack_50);
    FUN_00bd8b54();
    FUN_00ba555c();
    puVar4 = auStack_1a0;
LAB_00796600:
    FUN_003e8630(puVar4);
    FUN_00bfcf60();
    FUN_003e8630(auStack_e0);
    break;
  case 0x12:
    uVar3 = FUN_00bb916c(uVar3,"fastextra");
    FUN_00b9dbd8(uVar3,&DAT_00e994c2);
    FUN_00ba9510();
    uVar3 = FUN_00ba4068(auStack_50,auStack_b0);
    FUN_00b977f0(uVar3,&DAT_00ea48e0);
    FUN_00b90ec0(auStack_1d0,auStack_c8);
    FUN_00bee394();
    FUN_00ba555c();
    FUN_00b97364();
    FUN_00bb002c();
    do {
      FUN_00b9a91c();
    } while (!(bool)uVar2);
  }
  FUN_00b8f7ec();
switchD_00796260_caseD_1:
  FUN_00b8e63c(*(undefined8 *)(lVar1 + 0x28));
  if ((bool)uVar2) {
    FUN_00b9c664();
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ===== Function: FUN_007a2ac4 @ 007a2ac4  size=316  depth=0
   signature: undefined FUN_007a2ac4() ===== */

undefined8 FUN_007a2ac4(int *param_1,int *param_2,uint *param_3,int param_4,int param_5)

{
  int iVar1;
  undefined8 uVar2;
  int iVar3;
  int iVar4;
  uint uVar5;
  uint uVar6;
  int iVar7;
  int iVar8;
  uint uVar9;
  uint uVar10;
  float fVar11;
  float fVar12;
  
  iVar7 = param_1[2];
  iVar1 = param_1[3];
  iVar8 = iVar1 - iVar7;
  if (iVar8 == 0 || iVar1 < iVar7) {
    iVar3 = (iVar7 - iVar1) / 2;
    iVar4 = 0;
    iVar8 = 0;
    iVar7 = (iVar7 - iVar1) - iVar3;
  }
  else {
    iVar4 = iVar8 / 2;
    iVar7 = 0;
    iVar3 = 0;
    iVar8 = iVar8 - iVar4;
  }
  *param_2 = *param_1 - iVar4;
  param_2[1] = param_1[1] - iVar3;
  iVar1 = iVar8 + iVar4 + param_1[2];
  param_2[2] = iVar1;
  fVar12 = (float)param_5 / (float)iVar1;
  iVar7 = iVar3 + iVar7 + param_1[3];
  uVar5 = (uint)(fVar12 * (float)iVar4);
  fVar11 = (float)param_4 / (float)iVar7;
  uVar5 = uVar5 & ((int)uVar5 >> 0x1f ^ 0xffffffffU);
  uVar6 = (uint)(fVar11 * (float)iVar3);
  uVar6 = uVar6 & ((int)uVar6 >> 0x1f ^ 0xffffffffU);
  param_2[3] = iVar7;
  *param_3 = uVar5;
  param_3[1] = uVar6;
  uVar9 = (uint)(fVar12 * (float)param_1[2]);
  if ((int)(param_5 - uVar5) <= (int)uVar9) {
    uVar9 = param_5 - uVar5;
  }
  param_3[2] = uVar9;
  uVar10 = (uint)(fVar11 * (float)param_1[3]);
  if ((int)(param_4 - uVar6) <= (int)uVar10) {
    uVar10 = param_4 - uVar6;
  }
  param_3[3] = uVar10;
  if (((param_4 < (int)(uVar10 + uVar6) || param_5 < (int)(uVar9 + uVar5)) || (int)uVar9 < 1) ||
      (int)uVar10 < 1) {
    FUN_00b8c554(param_1,"SMASH_E_LOG ","invalid init_box_local in GetSquareBox\n");
    uVar2 = 0xffffff9b;
  }
  else {
    uVar2 = 0;
  }
  return uVar2;
}



/* ===== Function: FUN_00bf397c @ 00bf397c  size=8  depth=1
   signature: undefined FUN_00bf397c() ===== */

void FUN_00bf397c(void)

{
  FUN_00c4c350();
  return;
}



/* ===== Function: FUN_00b95ce8 @ 00b95ce8  size=16  depth=1
   signature: undefined FUN_00b95ce8() ===== */

void FUN_00b95ce8(long param_1)

{
  long in_x9;
  ulong in_x11;
  
  *(ulong *)(param_1 + in_x9) = *(ulong *)(param_1 + in_x9) | in_x11;
  return;
}



/* ===== Function: FUN_00bd4e98 @ 00bd4e98  size=12  depth=1
   signature: undefined FUN_00bd4e98() ===== */

void FUN_00bd4e98(void)

{
  return;
}



/* ===== Function: FUN_00b97b98 @ 00b97b98  size=12  depth=1
   signature: undefined FUN_00b97b98() ===== */

void FUN_00b97b98(undefined8 *param_1)

{
  *param_1 = 0;
  param_1[1] = 0;
  param_1[2] = 0;
  return;
}



/* ===== Function: FUN_00422aa8 @ 00422aa8  size=16  depth=1
   signature: undefined FUN_00422aa8() ===== */

void FUN_00422aa8(undefined8 *param_1)

{
  if ((void *)*param_1 != (void *)0x0) {
    operator_delete((void *)*param_1);
    return;
  }
  return;
}



/* ===== Function: FUN_00b8fc6c @ 00b8fc6c  size=12  depth=1
   signature: undefined FUN_00b8fc6c() ===== */

void FUN_00b8fc6c(void)

{
  return;
}



/* ===== Function: FUN_00c4bf20 @ 00c4bf20  size=52  depth=1
   signature: undefined FUN_00c4bf20() ===== */

void FUN_00c4bf20(undefined4 *param_1)

{
  *param_1 = 0x42ff0000;
  *(undefined8 *)(param_1 + 3) = 0;
  *(undefined8 *)(param_1 + 1) = 0;
  *(undefined8 *)(param_1 + 7) = 0;
  *(undefined8 *)(param_1 + 5) = 0;
  *(undefined8 *)(param_1 + 0xb) = 0;
  *(undefined8 *)(param_1 + 9) = 0;
  *(undefined8 *)(param_1 + 0xe) = 0;
  *(undefined8 *)(param_1 + 0xc) = 0;
  *(undefined8 *)(param_1 + 0x14) = 0;
  *(undefined4 **)(param_1 + 0x10) = param_1 + 2;
  *(undefined4 **)(param_1 + 0x12) = param_1 + 0x14;
  *(undefined8 *)(param_1 + 0x16) = 0;
  return;
}



/* ===== Function: FUN_0078ca00 @ 0078ca00  size=60  depth=1
   signature: undefined FUN_0078ca00() ===== */

void FUN_0078ca00(long param_1)

{
  undefined1 in_CY;
  
  FUN_00b8c124(*(undefined8 *)(param_1 + 0x430));
  if ((bool)in_CY) {
    FUN_0078ca64();
  }
  FUN_00bb5310();
  FUN_00b8c70c();
  return;
}



/* ===== Function: FUN_00bfd4dc @ 00bfd4dc  size=8  depth=1
   signature: undefined FUN_00bfd4dc() ===== */

void FUN_00bfd4dc(void)

{
  FUN_00c4bf20(&stack0x000005c0);
  return;
}



/* ===== Function: FUN_00b93300 @ 00b93300  size=32  depth=1
   signature: undefined FUN_00b93300() ===== */

void FUN_00b93300(ulong param_1)

{
  long in_x9;
  long in_x10;
  ulong uVar1;
  
  uVar1 = param_1 >> 3 & 0x1ffffffffffffff8;
  *(ulong *)(in_x10 + uVar1) =
       *(ulong *)(in_x10 + uVar1) & (in_x9 << (param_1 & 0x3f) ^ 0xffffffffffffffffU);
  return;
}



/* ===== Function: FUN_0066d328 @ 0066d328  size=116  depth=1
   signature: undefined FUN_0066d328() ===== */

void FUN_0066d328(void)

{
  undefined1 in_ZR;
  long *extraout_x1;
  ulong uVar1;
  long lVar2;
  undefined8 *unaff_x19;
  
  FUN_00b9132c();
  if (!(bool)in_ZR) {
    uVar1 = extraout_x1[1];
    lVar2 = 0;
    if (uVar1 != 0) {
      if ((ulong)(unaff_x19[2] * 0x40) < uVar1) {
        FUN_0043a680();
        FUN_0043a69c();
        uVar1 = extraout_x1[1];
      }
      FUN_0066d3e0(*extraout_x1,(uVar1 - 1 >> 3 & 0x1ffffffffffffff8) + *extraout_x1 + 8,*unaff_x19)
      ;
      lVar2 = extraout_x1[1];
    }
    unaff_x19[1] = lVar2;
  }
  FUN_00b8b278();
  return;
}



/* ===== Function: FUN_0078f020 @ 0078f020  size=2484  depth=1
   signature: undefined FUN_0078f020() ===== */

/* WARNING: Removing unreachable block (ram,0x0078f4d8) */
/* WARNING: Removing unreachable block (ram,0x0078f4f4) */
/* WARNING: Removing unreachable block (ram,0x0078f4e4) */
/* WARNING: Removing unreachable block (ram,0x0078f4f0) */
/* WARNING: Removing unreachable block (ram,0x0078f280) */
/* WARNING: Removing unreachable block (ram,0x0078f2b0) */
/* WARNING: Removing unreachable block (ram,0x0078f288) */
/* WARNING: Removing unreachable block (ram,0x0078f290) */
/* WARNING: Removing unreachable block (ram,0x0078f298) */
/* WARNING: Removing unreachable block (ram,0x0078f2c4) */
/* WARNING: Removing unreachable block (ram,0x0078f2d0) */
/* WARNING: Removing unreachable block (ram,0x0078f37c) */
/* WARNING: Removing unreachable block (ram,0x0078f398) */
/* WARNING: Removing unreachable block (ram,0x0078f39c) */
/* WARNING: Removing unreachable block (ram,0x0078f3a0) */
/* WARNING: Removing unreachable block (ram,0x0078f3c0) */
/* WARNING: Removing unreachable block (ram,0x0078f3a4) */
/* WARNING: Removing unreachable block (ram,0x0078f3b0) */

void FUN_0078f020(undefined1 param_1 [16],undefined1 param_2 [16],float param_3,undefined8 param_4,
                 undefined8 param_5,long param_6,uint param_7,uint param_8)

{
  long *plVar1;
  long *plVar2;
  float *pfVar3;
  long *plVar4;
  float fVar5;
  float fVar6;
  undefined1 in_ZR;
  undefined1 uVar7;
  bool bVar8;
  int iVar9;
  undefined8 uVar10;
  ulong uVar11;
  undefined8 extraout_x1;
  undefined8 extraout_x1_00;
  undefined8 extraout_x1_01;
  undefined8 extraout_x1_02;
  undefined8 uVar12;
  size_t __n;
  undefined8 extraout_x8;
  ulong extraout_x8_00;
  long extraout_x8_01;
  long extraout_x8_02;
  long extraout_x8_03;
  long extraout_x8_04;
  long extraout_x8_05;
  long extraout_x8_06;
  long extraout_x8_07;
  long extraout_x8_08;
  long extraout_x9;
  ulong uVar13;
  long extraout_x9_00;
  ulong extraout_x9_01;
  undefined8 extraout_x9_02;
  ulong extraout_x9_03;
  ulong extraout_x9_04;
  long extraout_x10;
  long lVar14;
  long extraout_x10_00;
  undefined8 *extraout_x11;
  long unaff_x19;
  long lVar15;
  ulong uVar16;
  long *plVar17;
  ulong uVar18;
  long *unaff_x21;
  long unaff_x23;
  ulong unaff_x24;
  long unaff_x29;
  float fVar19;
  undefined4 uVar20;
  undefined8 uVar22;
  undefined1 auVar21 [16];
  float fVar23;
  ulong uVar24;
  float fVar25;
  float fVar26;
  undefined1 auVar27 [12];
  undefined1 *in_stack_000001f0;
  undefined4 in_stack_000001f8;
  undefined4 in_stack_000001fc;
  undefined8 local_2c0;
  float local_2b8;
  float fStack_2b4;
  undefined1 *local_2b0;
  undefined8 uStack_2a8;
  undefined1 auStack_2a0 [560];
  
  FUN_00b8c0b8();
  FUN_00bf2810();
  FUN_00b8f6b0();
  FUN_00bbbaec();
  *(undefined8 *)(unaff_x29 + -0x70) = extraout_x8;
  FUN_0078fbe4();
  plVar1 = unaff_x21 + 0x2e;
  iVar9 = FUN_0078fc4c();
  if (iVar9 == 0) {
    bVar8 = *(int *)((long)unaff_x21 + 0x17c) == *(int *)(param_6 + 4);
    in_ZR = 0;
    if (bVar8) {
      FUN_00bb10fc((int)unaff_x21[0x2f]);
      in_ZR = 0;
      if (bVar8) {
        FUN_0078fd30(unaff_x21 + 0x4d,plVar1,*(undefined4 *)(param_6 + 0x1c),plVar1);
        lVar15 = unaff_x21[0x22];
        FUN_00bcadb8(&stack0x00000ac8);
        FUN_00acf2d4(lVar15,&stack0x00000ac8,unaff_x21 + 0x8d,unaff_x21 + 0x90);
        FUN_00c4c350(&stack0x00000ac8);
        uVar7 = (ulong)unaff_x21[0x8e] <= (ulong)unaff_x21[0x8d];
        in_ZR = unaff_x21[0x8d] == unaff_x21[0x8e];
        if ((bool)in_ZR) {
          unaff_x21[0x26] = unaff_x21[0x25];
        }
        else {
          if ((param_7 & 1) != 0) {
            in_stack_000001f0 = &stack0x00000200;
            FUN_00b8cc70(in_stack_000001f0,extraout_x1,0x480);
            FUN_00bae500();
            in_stack_000001f8 = 0x48;
            in_stack_000001fc = 0;
            uVar12 = extraout_x1_00;
            plVar17 = plVar1;
            for (; uVar7 = (ulong)(long)*(int *)((long)unaff_x21 + 0x1ec) <= unaff_x24,
                (long)unaff_x24 < (long)*(int *)((long)unaff_x21 + 0x1ec); unaff_x24 = unaff_x24 + 1
                ) {
              FUN_00bddb34(unaff_x21[0x46]);
              uVar12 = extraout_x1_01;
              if ((extraout_x8_00 & 1L << (unaff_x24 & 0x3f)) != 0) {
                FUN_0078fe20(&stack0x000001f0,*unaff_x21 + (long)plVar17);
                uVar12 = extraout_x1_02;
              }
              plVar17 = plVar17 + 2;
            }
            FUN_00b8cc70(auStack_2a0,uVar12,0x480);
            uVar20 = DAT_00d98dc0;
            fVar6 = DAT_00d98c70;
            uStack_2a8 = 0x48;
            lVar15 = 0;
            local_2b0 = auStack_2a0;
            while (FUN_00bdd484(unaff_x21[0x8d]), !(bool)uVar7) {
              __n = 0x440;
              auVar27 = FUN_00b91c64();
              pfVar3 = (float *)(extraout_x8_01 + lVar15 * 0x10);
              memset(auVar27._0_8_,auVar27._8_4_,__n);
              local_2c0 = *(undefined8 *)pfVar3;
              FUN_00790344(&stack0x00000b28,&local_2c0);
              local_2c0 = CONCAT44(pfVar3[1],*pfVar3 + pfVar3[2]);
              FUN_00790344(&stack0x00000b28,&local_2c0);
              local_2c0 = CONCAT44(pfVar3[1] + pfVar3[3],*pfVar3);
              FUN_00790344(&stack0x00000b28,&local_2c0);
              uVar24 = *(ulong *)(pfVar3 + 2);
              local_2c0 = CONCAT44((float)((ulong)*(undefined8 *)pfVar3 >> 0x20) +
                                   (float)(uVar24 >> 0x20),
                                   (float)*(undefined8 *)pfVar3 + (float)uVar24);
              FUN_00790344(&stack0x00000b28,&local_2c0);
              FUN_00bd6778();
              uVar7 = extraout_x9 == 0;
              fVar19 = (float)FUN_00b9ba60(&stack0x00000b38,uVar20);
              fVar23 = (float)uVar24;
              pfVar3 = (float *)(extraout_x8_02 + 4);
              lVar14 = extraout_x10;
              fVar26 = fVar6;
              while (fStack_2b4 = fVar26, lVar14 != 0) {
                fVar25 = pfVar3[-1];
                fVar26 = *pfVar3;
                pfVar3 = pfVar3 + 2;
                lVar14 = lVar14 + -1;
                fVar5 = fVar25;
                if (fVar19 <= fVar25) {
                  fVar5 = fVar19;
                }
                fVar23 = fVar26;
                if ((float)uVar24 <= fVar26) {
                  fVar23 = (float)uVar24;
                }
                uVar24 = (ulong)(uint)fVar23;
                if (fVar25 <= param_3) {
                  fVar25 = param_3;
                }
                uVar7 = fStack_2b4 <= fVar26;
                param_3 = fVar25;
                fVar19 = fVar5;
                if (fVar26 <= fStack_2b4) {
                  fVar26 = fStack_2b4;
                }
              }
              param_3 = param_3 - fVar19;
              fStack_2b4 = fStack_2b4 - fVar23;
              local_2c0 = CONCAT44(fVar23,fVar19);
              local_2b8 = param_3;
              FUN_0079031c(&stack0x00000b28);
              FUN_0078fe20(&local_2b0,&local_2c0);
              lVar15 = lVar15 + 1;
            }
            FUN_0078ca64(&stack0x00000690,0);
            uVar7 = 1;
            in_ZR = 1;
            FUN_0078ff50(&local_2b0);
            FUN_0078ff50(&stack0x000001f0);
          }
          uVar12 = DAT_00d92a98;
          plVar17 = unaff_x21 + 0x84;
          uVar24 = 0;
          plVar2 = unaff_x21 + 0x66;
          while (FUN_00bdd46c(unaff_x21[0x8d]), !(bool)uVar7) {
            uVar13 = (ulong)*(int *)((long)unaff_x21 + 0x1ec);
            in_ZR = uVar24 == uVar13;
            if ((long)uVar13 <= (long)uVar24) break;
            uVar16 = uVar24;
            if ((param_7 & 1) == 0) {
LAB_0078f52c:
              unaff_x21[0x8b] = 0;
              unaff_x21[0x67] = unaff_x21[0x66];
              FUN_00793a8c(*(undefined8 *)(unaff_x21[0x43] + uVar16 * 8),plVar1,
                           extraout_x8_03 + uVar24 * 0x10,plVar2,unaff_x21 + 0x8a,param_6);
              uVar7 = (ulong)unaff_x21[0x67] <= (ulong)unaff_x21[0x66];
              in_ZR = unaff_x21[0x66] == unaff_x21[0x67];
              if ((bool)in_ZR) {
                FUN_00ba11c4(unaff_x21[0x46]);
                FUN_00ba11c4(unaff_x21[0x49]);
              }
              else {
                in_ZR = (*(byte *)(param_6 + 0x11) & 9) == 0;
                uVar7 = 0;
                if ((bool)in_ZR) {
                  FUN_0079727c(*(undefined4 *)((long)unaff_x21 + 0x14c),(int)unaff_x21[0x2a],
                               *(undefined8 *)(unaff_x21[0x43] + uVar16 * 8),
                               *(undefined4 *)(unaff_x23 + 8),*(undefined4 *)(unaff_x23 + 0xc),
                               plVar2);
                }
                else {
                  FUN_00796f88(*(undefined4 *)((long)unaff_x21 + 0x14c),(int)unaff_x21[0x2a],
                               (int)unaff_x21[0x2c]);
                  FUN_0079716c(*(undefined4 *)((long)unaff_x21 + 0x164),
                               *(undefined8 *)(unaff_x21[0x43] + uVar16 * 8),
                               *(undefined4 *)(unaff_x23 + 8),*(undefined4 *)(unaff_x23 + 0xc),
                               plVar2,unaff_x21 + 0x75);
                }
                FUN_00797204(*(undefined8 *)(unaff_x21[0x43] + uVar16 * 8),plVar17,1);
                uVar18 = uVar16 >> 6;
                uVar13 = 1L << (uVar16 & 0x3f);
                *(ulong *)(unaff_x21[0x46] + uVar18 * 8) =
                     *(ulong *)(unaff_x21[0x46] + uVar18 * 8) | uVar13;
                iVar9 = (int)unaff_x21[0x27] + 1;
                *(int *)(unaff_x21 + 0x27) = iVar9;
                *(int *)(unaff_x21[0x15] + uVar16 * 4) = iVar9;
                *(undefined4 *)(unaff_x21[0x18] + uVar16 * 4) = 1;
                *(int *)(unaff_x21[0x40] + uVar16 * 4) = (int)unaff_x21[0x27];
                uVar10 = FUN_00bf091c(unaff_x21[6],&stack0x000001f0);
                *(undefined8 *)(unaff_x21[9] + extraout_x9_00 * 8 + 8) = 0;
                FUN_0079602c(uVar10,unaff_x21[0x66]);
                plVar4 = (long *)(*unaff_x21 + uVar16 * 0x10);
                plVar4[1] = CONCAT44(in_stack_000001fc,in_stack_000001f8);
                *plVar4 = (long)in_stack_000001f0;
                uVar11 = FUN_00795ea4(*(undefined4 *)(unaff_x21[0x12] + uVar16 * 0xc),
                                      unaff_x21[0x66]);
                lVar15 = unaff_x21[0x49];
                if ((uVar11 & 1) == 0) {
                  FUN_00bfd2dc();
                  lVar15 = extraout_x8_04;
                  uVar11 = extraout_x9_01;
                }
                else {
                  uVar11 = *(ulong *)(lVar15 + uVar18 * 8) | uVar13;
                }
                *(ulong *)(lVar15 + uVar18 * 8) = uVar11;
                if ((param_8 & 1) != 0) {
                  unaff_x21[0x8b] = 0;
                  unaff_x21[0x67] = unaff_x21[0x66];
                  FUN_00b97b98(&stack0x00000208);
                  in_stack_000001fc = (undefined4)uVar12;
                  FUN_00794194(*(undefined8 *)(unaff_x21[0x43] + uVar16 * 8));
                  uVar7 = (ulong)unaff_x21[0x67] <= (ulong)unaff_x21[0x66];
                  in_ZR = unaff_x21[0x66] == unaff_x21[0x67];
                  if ((bool)in_ZR) {
                    FUN_00bfd2dc(unaff_x21[0x46]);
                    *(undefined8 *)(extraout_x8_05 + uVar18 * 8) = extraout_x9_02;
                    lVar15 = unaff_x21[0x49];
LAB_0078f990:
                    FUN_00bfd2dc(lVar15);
                    lVar15 = extraout_x8_08;
                    uVar13 = extraout_x9_04;
                  }
                  else {
                    uVar10 = *(undefined8 *)(unaff_x21[0x43] + uVar16 * 8);
                    in_ZR = (*(byte *)(param_6 + 0x11) & 9) == 0;
                    uVar7 = 0;
                    if ((bool)in_ZR) {
                      FUN_00797924(uVar10,plVar2,unaff_x21 + 0x6c,unaff_x21 + 0x6f,unaff_x21 + 0x78,
                                   unaff_x21 + 0x7b);
                    }
                    else {
                      FUN_007972e8(uVar10,plVar2,unaff_x21 + 0x6c,unaff_x21 + 0x6f,unaff_x21 + 0x72,
                                   unaff_x21 + 0x78,unaff_x21 + 0x7b,unaff_x21 + 0x7e);
                      FUN_0079784c(*(undefined8 *)(unaff_x21[0x43] + uVar16 * 8),plVar2,
                                   unaff_x21 + 0x75,unaff_x21 + 0x81);
                    }
                    *(ulong *)(unaff_x21[0x46] + uVar18 * 8) =
                         *(ulong *)(unaff_x21[0x46] + uVar18 * 8) | uVar13;
                    FUN_00bd22c8(unaff_x21[0xa2] + uVar16 * 0x48,unaff_x21[0x99],plVar2,
                                 uVar16 & 0xffffffff,unaff_x21 + 6);
                    uVar22 = extraout_x11[1];
                    uVar10 = *extraout_x11;
                    lVar15 = unaff_x21[0xa2] + extraout_x10_00 * 8;
                    *(undefined8 *)(lVar15 + 0x28) = extraout_x11[2];
                    *(undefined8 *)(lVar15 + 0x20) = uVar22;
                    *(undefined8 *)(lVar15 + 0x18) = uVar10;
                    FUN_00bdd418(unaff_x21[0xa2] + extraout_x10_00 * 8,unaff_x21[0x9f]);
                    FUN_0079613c(unaff_x21 + 0x8a,uVar16 & 0xffffffff,unaff_x21 + 9);
                    *(undefined4 *)(unaff_x21[0x15] + uVar16 * 4) =
                         *(undefined4 *)(unaff_x21[0x40] + uVar16 * 4);
                    *(int *)(unaff_x21[0x18] + uVar16 * 4) =
                         *(int *)(unaff_x21[0x18] + uVar16 * 4) + 1;
                    FUN_0079602c(&local_2b0,unaff_x21[0x66]);
                    auVar21 = FUN_00b9e93c(*unaff_x21,local_2b0);
                    *(undefined1 (*) [16])(extraout_x8_06 + uVar16 * 0x10) = auVar21;
                    *(int *)(unaff_x21[0x12] + uVar16 * 0xc + 8) = (int)unaff_x21[0x94];
                    *(int *)(unaff_x21[0x12] + uVar16 * 0xc) = (int)unaff_x21[0x93];
                    uVar20 = FUN_0078ff78();
                    *(undefined4 *)(unaff_x21[0x12] + uVar16 * 0xc + 4) = uVar20;
                    unaff_x21[0x85] = unaff_x21[0x84];
                    unaff_x21[0x88] = unaff_x21[0x87];
                    FUN_003f9050(plVar17,unaff_x21[0x12] + uVar16 * 0xc);
                    FUN_00be616c(unaff_x21[0x12] + uVar16 * 0xc,plVar17);
                    FUN_003f9050(plVar17,unaff_x21[0x12] + uVar16 * 0xc + 8);
                    uVar10 = FUN_00acfc70(*(long *)(unaff_x21[0x43] + uVar16 * 8) + 0x500,plVar17,
                                          unaff_x21 + 0x87);
                    *(undefined4 *)(unaff_x21[0x12] + uVar16 * 0xc + 8) =
                         *(undefined4 *)(unaff_x21[0x87] + 8);
                    *(undefined4 *)(unaff_x21[0x12] + uVar16 * 0xc) = *(undefined4 *)unaff_x21[0x87]
                    ;
                    *(undefined4 *)(unaff_x21[0x12] + uVar16 * 0xc + 4) =
                         *(undefined4 *)(unaff_x21[0x87] + 4);
                    FUN_00bd7da4(*(undefined8 *)(unaff_x21[0x43] + uVar16 * 8),
                                 *(undefined4 *)(unaff_x21[0x12] + uVar16 * 0xc + 4),uVar10,
                                 &local_2b0);
                    lVar15 = unaff_x21[0xf];
                    if ((char)local_2b0 == '\0') {
                      FUN_00bfd2dc();
                      lVar15 = extraout_x8_07;
                      uVar11 = extraout_x9_03;
                    }
                    else {
                      uVar11 = *(ulong *)(lVar15 + uVar18 * 8) | uVar13;
                    }
                    *(ulong *)(lVar15 + uVar18 * 8) = uVar11;
                    FUN_0066d328(unaff_x21[0xc] + uVar16 * 0x18,unaff_x21 + 0x96);
                    *(undefined8 *)(unaff_x21[3] + uVar16 * 8) =
                         *(undefined8 *)((long)unaff_x21 + 0x4a4);
                    uVar16 = FUN_00795ea4(*(undefined4 *)(unaff_x21[0x12] + uVar16 * 0xc),
                                          unaff_x21[0x66]);
                    lVar15 = unaff_x21[0x49];
                    if ((uVar16 & 1) == 0) goto LAB_0078f990;
                    uVar13 = *(ulong *)(lVar15 + uVar18 * 8) | uVar13;
                  }
                  *(ulong *)(lVar15 + uVar18 * 8) = uVar13;
                  FUN_00422aa8(&stack0x00000208);
                }
              }
            }
            else {
              uVar16 = 0;
              while( true ) {
                uVar7 = uVar13 <= uVar16;
                in_ZR = uVar16 == uVar13;
                if ((long)uVar13 <= (long)uVar16) break;
                if ((*(ulong *)(unaff_x21[0x46] + (uVar16 >> 3 & 0x1ffffffffffffff8)) &
                    1L << (uVar16 & 0x3f)) == 0) goto LAB_0078f52c;
                uVar16 = uVar16 + 1;
              }
            }
            uVar24 = uVar24 + 1;
          }
          FUN_0078ca3c(&stack0x00000690);
        }
      }
    }
  }
  FUN_00b8fc6c(*(undefined8 *)(unaff_x19 + 0x28));
  if (!(bool)in_ZR) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  FUN_00b8d5c4();
  return;
}



/* ===== Function: FUN_0078f9d4 @ 0078f9d4  size=528  depth=1
   signature: undefined FUN_0078f9d4() ===== */

void FUN_0078f9d4(void)

{
  char cVar1;
  char cVar2;
  undefined1 uVar3;
  bool bVar4;
  int iVar5;
  long lVar6;
  ulong uVar7;
  long lVar8;
  long *unaff_x19;
  int iVar9;
  ulong *puVar10;
  ulong unaff_x21;
  ulong uVar11;
  ulong uVar12;
  ulong uVar13;
  
  lVar6 = FUN_00b8c51c();
  iVar9 = *(int *)(lVar6 + 0x1ec);
  FUN_00ba2134();
  for (; (long)unaff_x21 < (long)iVar9; unaff_x21 = unaff_x21 + 1) {
    uVar12 = unaff_x21 >> 6;
    uVar7 = 1L << (unaff_x21 & 0x3f);
    if ((*(ulong *)(unaff_x19[0x46] + uVar12 * 8) & uVar7) != 0) {
      while (1 < iVar9) {
        puVar10 = (ulong *)unaff_x19[0x46];
        uVar11 = *puVar10;
        cVar2 = '\0';
        uVar3 = (uVar11 & 2) == 0;
        cVar1 = '\0';
        if (!(bool)uVar3) {
          FUN_00795e4c(*unaff_x19 + unaff_x21 * 0x10,*unaff_x19 + 0x10);
          FUN_00b9aba0();
          if (!(bool)uVar3 && cVar2 == cVar1) {
            if (*(int *)(unaff_x19[0x15] + unaff_x21 * 4) < *(int *)(unaff_x19[0x15] + 4)) {
              *puVar10 = uVar11 & 0xfffffffffffffffd;
            }
            else {
              puVar10[uVar12] = puVar10[uVar12] & ~uVar7;
            }
          }
        }
        iVar9 = *(int *)((long)unaff_x19 + 0x1ec);
        FUN_00ba26f4();
      }
    }
    FUN_00ba26f4();
  }
  FUN_00bf3ac4();
  do {
    bVar4 = unaff_x21 == (long)iVar9;
    if ((long)iVar9 <= (long)unaff_x21) {
      FUN_00bf3ac4();
      do {
        if ((long)iVar9 <= (long)unaff_x21) {
          FUN_00b8bb88();
          return;
        }
        lVar6 = unaff_x19[0x46];
        uVar11 = 1L << (unaff_x21 & 0x3f);
        uVar12 = *(ulong *)(lVar6 + (unaff_x21 >> 6) * 8);
        uVar7 = unaff_x21;
        if ((uVar12 & uVar11) != 0) {
          do {
            lVar8 = uVar7 << 4;
            do {
              uVar7 = uVar7 + 1;
              if (iVar9 <= (int)uVar7) goto LAB_0078fbbc;
              lVar8 = lVar8 + 0x10;
            } while ((*(ulong *)(lVar6 + (uVar7 >> 3 & 0x1ffffffffffffff8)) & 1L << (uVar7 & 0x3f))
                     == 0);
            iVar5 = FUN_00795f34(*unaff_x19 + unaff_x21 * 0x10,*unaff_x19 + lVar8);
          } while (iVar5 != 1);
          *(ulong *)(lVar6 + (unaff_x21 >> 6) * 8) = uVar12 & (uVar11 ^ 0xffffffffffffffff);
          iVar9 = *(int *)((long)unaff_x19 + 0x1ec);
        }
LAB_0078fbbc:
        unaff_x21 = unaff_x21 + 1;
      } while( true );
    }
    FUN_00beda60(unaff_x19[0x46]);
    uVar7 = unaff_x21;
    if (!bVar4) {
      do {
        uVar12 = uVar7 + 1;
        while( true ) {
          if (iVar9 <= (int)uVar12) goto LAB_0078fb34;
          lVar6 = unaff_x19[0x46];
          uVar13 = 1L << (uVar12 & 0x3f);
          uVar11 = *(ulong *)(lVar6 + (uVar12 >> 6) * 8);
          if (((uVar11 & uVar13) != 0) &&
             (iVar5 = FUN_00795f34(*unaff_x19 + unaff_x21 * 0x10,*unaff_x19 + uVar7 * 0x10 + 0x10),
             iVar5 == -1)) break;
          FUN_00bf3f28();
        }
        *(ulong *)(lVar6 + (uVar12 >> 6) * 8) = uVar11 & (uVar13 ^ 0xffffffffffffffff);
        iVar9 = *(int *)((long)unaff_x19 + 0x1ec);
        uVar7 = uVar12;
      } while( true );
    }
LAB_0078fb34:
    unaff_x21 = unaff_x21 + 1;
  } while( true );
}



/* ===== Function: FUN_00bf5bc8 @ 00bf5bc8  size=12  depth=1
   signature: undefined FUN_00bf5bc8() ===== */

void FUN_00bf5bc8(void)

{
  return;
}



/* ===== Function: FUN_00b9f7a8 @ 00b9f7a8  size=12  depth=1
   signature: undefined FUN_00b9f7a8() ===== */

void FUN_00b9f7a8(undefined8 *param_1)

{
  param_1[1] = *param_1;
  return;
}



/* ===== Function: FUN_00ba9068 @ 00ba9068  size=12  depth=1
   signature: undefined FUN_00ba9068() ===== */

void FUN_00ba9068(void)

{
  return;
}



/* ===== Function: FUN_00793a8c @ 00793a8c  size=1664  depth=1
   signature: undefined FUN_00793a8c() ===== */

void FUN_00793a8c(undefined1 param_1 [16],undefined4 param_2,undefined8 param_3,undefined8 param_4,
                 float *param_5,undefined8 *param_6,undefined8 param_7,long param_8)

{
  int iVar1;
  undefined8 *puVar2;
  uint uVar3;
  uint uVar4;
  undefined4 *puVar5;
  undefined4 *puVar6;
  undefined1 uVar7;
  undefined1 uVar8;
  long lVar9;
  void *pvVar10;
  ulong uVar11;
  undefined8 extraout_x1;
  undefined8 extraout_x1_00;
  int extraout_w8;
  ulong extraout_x8;
  undefined8 uVar12;
  long extraout_x8_00;
  ulong extraout_x8_01;
  long extraout_x8_02;
  long extraout_x8_03;
  int extraout_w9;
  long extraout_x9;
  undefined8 extraout_x9_00;
  long extraout_x9_01;
  undefined4 *extraout_x9_02;
  uint uVar13;
  long extraout_x10;
  long extraout_x10_00;
  long extraout_x10_01;
  undefined4 *extraout_x10_02;
  uint uVar14;
  int iVar15;
  int iVar16;
  long lVar17;
  long unaff_x19;
  uint *unaff_x20;
  long unaff_x22;
  int iVar18;
  long lVar19;
  undefined4 uVar20;
  undefined8 extraout_d0;
  undefined8 extraout_var;
  float fVar21;
  undefined8 uVar22;
  float fVar23;
  float fVar24;
  float fVar25;
  float fVar26;
  undefined1 auVar27 [16];
  undefined4 local_5b4;
  undefined4 uStack_5b0;
  undefined1 local_5ac;
  undefined1 local_5a8;
  undefined1 local_5a4;
  undefined1 local_5a0 [8];
  undefined8 *local_598;
  undefined8 local_590;
  undefined8 auStack_588 [137];
  int local_140;
  int local_13c;
  undefined8 local_138;
  undefined8 local_130;
  uint local_e0 [2];
  int local_d8;
  int local_d4;
  long local_d0;
  
  FUN_00b8c0b8();
  lVar9 = FUN_00b8e858();
  uVar7 = *(int *)(lVar9 + 0x5b00) == 0x123456;
  uVar8 = 0;
  if ((bool)uVar7) {
    lVar9 = FUN_00b8bba4(*(undefined1 *)(param_8 + 0x11));
    if ((extraout_x8 & 1) != 0) {
      uVar8 = 0;
      uVar7 = 1;
      if (*(int *)((undefined1 *)(lVar9 + extraout_x9) + 300) != 0x1234567) goto LAB_007940f0;
    }
    param_6[1] = *param_6;
    FUN_00bd7b28(*unaff_x20);
    if ((bool)uVar7) {
      uVar12 = *(undefined8 *)(unaff_x19 + 0x2ea4);
      FUN_00c4bf20(local_e0);
      fVar21 = param_5[2];
      fVar25 = param_5[3];
      if (fVar25 <= fVar21) {
        fVar24 = param_5[1];
        fVar23 = (fVar21 + -1.0) * 0.5;
        fVar26 = fVar23 + *param_5;
        if (fVar21 + fVar24 + -1.0 <= (float)(int)(unaff_x20[2] - 1)) {
          fVar23 = (fVar25 + fVar24 + -1.0) - fVar23;
          fVar25 = fVar21;
        }
        else {
          fVar23 = fVar23 + fVar24;
          fVar25 = fVar21;
        }
      }
      else {
        fVar24 = *param_5;
        fVar26 = (fVar25 + -1.0) * 0.5;
        fVar23 = fVar26 + param_5[1];
        if (fVar25 + fVar24 + -1.0 <= (float)(int)(unaff_x20[3] - 1)) {
          fVar26 = (fVar21 + fVar24 + -1.0) - fVar26;
        }
        else {
          fVar26 = fVar26 + fVar24;
        }
      }
      iVar16 = unaff_x20[3] - 1;
      iVar18 = unaff_x20[2] - 1;
      puVar2 = (undefined8 *)(unaff_x19 + 0x30a0);
      fVar21 = (fVar25 * DAT_00d98cbc + -1.0) * 0.5;
      fVar25 = (float)(int)(fVar25 * DAT_00d98cbc);
      uVar13 = (uint)(fVar26 - fVar21);
      uVar14 = (uint)(fVar23 - fVar21);
      *param_5 = (float)(int)uVar13;
      param_5[1] = (float)(int)uVar14;
      iVar15 = (int)(fVar25 + (float)(int)uVar13 + -1.0);
      uVar3 = uVar13 & ((int)uVar13 >> 0x1f ^ 0xffffffffU);
      iVar1 = iVar15;
      if (iVar16 <= iVar15) {
        iVar1 = iVar16;
      }
      iVar16 = (int)(fVar25 + (float)(int)uVar14 + -1.0);
      uVar4 = uVar14 & ((int)uVar14 >> 0x1f ^ 0xffffffffU);
      param_5[2] = (float)(int)((iVar15 - uVar13) + 1);
      param_5[3] = (float)(int)((iVar16 - uVar14) + 1);
      if (iVar18 <= iVar16) {
        iVar16 = iVar18;
      }
      iVar1 = (iVar1 - uVar3) + 1;
      if ((int)unaff_x20[1] < 1) {
        fVar21 = 0.0;
      }
      else {
        fVar21 = (float)*(ulong *)(*(long *)(unaff_x20 + 0x12) + (long)(int)(unaff_x20[1] - 1) * 8);
      }
      iVar18 = (int)(fVar25 * fVar25 * fVar21);
      if (*(int *)(unaff_x19 + 0x30a8) < iVar18) {
        FUN_00790b68(puVar2);
        pvVar10 = malloc((long)iVar18);
        *(void **)(unaff_x19 + 0x30a0) = pvVar10;
        *(int *)(unaff_x19 + 0x30a8) = iVar18;
      }
      FUN_00b8cc70(*puVar2);
      FUN_00b8e56c(&local_598,(int)fVar25,(int)fVar25,*unaff_x20 & 0xfff,*puVar2);
      FUN_00c06d24(local_e0);
      FUN_00ba62ac();
      uVar11 = (ulong)(uint)param_5[1];
      local_138._4_4_ = (iVar16 - uVar4) + 1;
      FUN_00bfd538(*param_5,uVar11,(float)(int)uVar3,(float)(int)uVar4,&local_598,local_e0,
                   &local_140);
      FUN_00bf04a4();
      local_138 = CONCAT44(local_138._4_4_,iVar1);
      local_140 = extraout_w8;
      local_13c = extraout_w9;
      FUN_00c4aa60();
      lVar19 = *(long *)(unaff_x20 + 4);
      iVar18 = 0;
      while( true ) {
        param_2 = (undefined4)uVar11;
        if ((int)(iVar16 - uVar4) < iVar18) break;
        iVar15 = (*unaff_x20 >> 3 & 0x1ff) + 1;
        lVar17 = ((ulong)(local_e0[0] >> 3) & 0x1ff) + 1;
        memcpy((void *)(local_d0 + lVar17 * local_140 +
                       (long)(local_d4 * (local_13c + iVar18) * (int)lVar17)),
               (void *)(lVar19 + (ulong)(iVar15 * uVar3) +
                       (long)(int)(unaff_x20[3] * (uVar4 + iVar18) * iVar15)),(long)(iVar15 * iVar1)
              );
        iVar18 = iVar18 + 1;
      }
      FUN_00ba62ac();
      local_598 = (undefined8 *)CONCAT44(local_598._4_4_,0x1010000);
      local_138 = unaff_x19 + 0x3110;
      local_130 = 0;
      local_140 = 0x2010000;
      FUN_00b92824(uVar12,&local_598,&local_140,local_5a0);
      auStack_588[0] = 0;
      local_590 = extraout_x9_00;
      FUN_00cc8734();
      FUN_00b9c594();
    }
    FUN_00c4c04c(local_e0,unaff_x19 + 0x3110);
    uVar8 = 0;
    if ((local_d4 == *(int *)(unaff_x19 + 0x2ea4)) &&
       (uVar8 = local_d8 == *(int *)(unaff_x19 + 0x2ea8), (bool)uVar8)) {
      FUN_00c09c20();
      FUN_00bdabdc();
      uVar20 = FUN_00bd0eac();
      local_598 = (undefined8 *)0x0;
      local_590 = CONCAT44(param_2,uVar20);
      FUN_00acb460();
      uVar12 = *(undefined8 *)(param_5 + 2);
      uVar22 = NEON_fmov(0xbf800000,4);
      local_598 = (undefined8 *)FUN_00bd0eac(*(undefined8 *)param_5);
      uVar12 = CONCAT44((float)((ulong)local_598 >> 0x20) + (float)((ulong)uVar12 >> 0x20) +
                        (float)((ulong)uVar22 >> 0x20),
                        SUB84(local_598,0) + (float)uVar12 + (float)uVar22);
      local_590 = uVar12;
      FUN_00acb600();
      auVar27 = FUN_00bfc27c();
      local_5a4 = 0;
      local_5a8 = 0;
      local_5ac = 0;
      local_5b4 = 0;
      uStack_5b0 = 0;
      FUN_00b8bb98(&local_5b4,auVar27._0_8_,auVar27._8_8_,local_e0,&local_140,
                   *(undefined8 *)(param_8 + 0x10),1,local_5a0,*(undefined4 *)(param_8 + 0x18));
      uVar11 = FUN_007932ec();
      if ((uVar11 & 1) != 0) {
        FUN_00bf390c(0x25c8,uVar11,&local_140);
        FUN_00acb16c();
        iVar1 = *(int *)(param_8 + 0x1c);
        uVar3 = unaff_x20[3];
        uVar11 = (ulong)uVar3;
        if (iVar1 == 1) {
          lVar19 = 0;
          while (uVar8 = lVar19 == 0x1a8, !(bool)uVar8) {
            lVar17 = *(long *)(unaff_x19 + 0x25d8);
            fVar25 = *(float *)(lVar17 + lVar19);
            *(float *)(lVar17 + lVar19) =
                 (float)(int)*(float *)(lVar17 + **(long **)(unaff_x19 + 0x2610) + lVar19);
            FUN_00bcc954((float)((int)uVar11 + ~(int)fVar25));
            uVar11 = extraout_x8_01;
            lVar19 = extraout_x9_01;
          }
        }
        else {
          uVar13 = unaff_x20[2];
          if (iVar1 == 3) {
            for (lVar19 = 0; uVar8 = lVar19 == 0x1a8, !(bool)uVar8; lVar19 = lVar19 + 4) {
              lVar17 = *(long *)(unaff_x19 + 0x25d8);
              fVar25 = *(float *)(lVar17 + lVar19);
              *(float *)(lVar17 + lVar19) =
                   (float)(int)(uVar13 + ~(int)*(float *)(lVar17 + **(long **)(unaff_x19 + 0x2610) +
                                                         lVar19));
              *(float *)(*(long *)(unaff_x19 + 0x25d8) + **(long **)(unaff_x19 + 0x2610) + lVar19) =
                   (float)(int)(uVar3 + ~(int)fVar25);
            }
          }
          else {
            uVar8 = false;
            if (iVar1 == 2) {
              FUN_00c09c20();
              fVar25 = (float)FUN_00bdabdc();
              lVar19 = extraout_x10_00;
              while (uVar8 = lVar19 == 0x1a8, !(bool)uVar8) {
                *(float *)(*(long *)(unaff_x19 + 0x25d8) + lVar19) =
                     fVar25 - *(float *)(*(long *)(unaff_x19 + 0x25d8) + lVar19);
                fVar25 = (float)FUN_00ba05bc(*(undefined8 *)(unaff_x19 + 0x2610));
                *(float *)(extraout_x8_00 + extraout_x10_01) =
                     (float)uVar12 - *(float *)(extraout_x8_00 + extraout_x10_01);
                lVar19 = extraout_x10_01 + 4;
              }
            }
          }
        }
        uVar12 = FUN_00ba13cc(*(undefined4 *)(param_8 + 0x1c),unaff_x19 + extraout_x10);
        puVar5 = extraout_x10_02;
        if ((bool)uVar8) {
          puVar5 = extraout_x9_02;
        }
        puVar6 = extraout_x9_02;
        if ((bool)uVar8) {
          puVar6 = extraout_x10_02;
        }
        *(undefined4 *)(unaff_x19 + 0x3e28) = *puVar5;
        *(undefined4 *)(unaff_x19 + 0x3e2c) = *puVar6;
        FUN_00acb460(uVar12,unaff_x19 + 0x2eac);
        thunk_FUN_00b9dd80(*(undefined8 *)(unaff_x19 + 0x25d8),**(undefined8 **)(unaff_x19 + 0x2610)
                           ,(undefined8 *)(unaff_x19 + 0x2368));
        FUN_00b95efc();
        FUN_00acb4b4();
        uVar12 = FUN_00bb71a0(0x2558,*(undefined8 *)(unaff_x19 + 0x2368));
        ((undefined8 *)(unaff_x19 + extraout_x8_02))[1] = extraout_var;
        *(undefined8 *)(unaff_x19 + extraout_x8_02) = extraout_d0;
        FUN_0079410c(uVar12,extraout_x1,*(undefined8 *)(param_8 + 0x10));
        FUN_0066d328(param_7,unaff_x19 + 0x31d8);
        uVar12 = FUN_00b8cc70(auStack_588,extraout_x1_00,0x440);
        local_590 = 0x88;
        local_598 = auStack_588;
        FUN_00bbbdf8(&local_598,uVar12,&local_598);
        *(undefined8 *)(extraout_x8_03 + 0x450) = 0;
        FUN_00792cf8();
        FUN_00793a08(unaff_x19 + 0x400,&local_598,unaff_x20[2],unaff_x20[3]);
        *(undefined1 *)(lVar9 + extraout_x9) = 1;
        FUN_0079031c(&local_598);
      }
      FUN_00bb7ddc();
    }
    FUN_00b9c594();
  }
LAB_007940f0:
  FUN_00b8fc6c(*(undefined8 *)(unaff_x22 + 0x28));
  if ((bool)uVar8) {
    FUN_00b8c428();
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ===== Function: FUN_0078fd30 @ 0078fd30  size=104  depth=1
   signature: undefined FUN_0078fd30() ===== */

void FUN_0078fd30(undefined8 param_1,undefined8 param_2,uint param_3)

{
  bool bVar1;
  code *UNRECOVERED_JUMPTABLE;
  long unaff_x21;
  
  FUN_00c0c000();
  FUN_00b8e400();
  bVar1 = param_3 == 3;
  if (param_3 < 4) {
    FUN_00bbae40();
                    /* WARNING: Could not recover jumptable at 0x0078fd70. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (*UNRECOVERED_JUMPTABLE)();
    return;
  }
  FUN_00b8b5e0(*(undefined8 *)(unaff_x21 + 0x28));
  if (bVar1) {
    FUN_00b8bb88();
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ===== Function: FUN_00794194 @ 00794194  size=5832  depth=1
   signature: undefined FUN_00794194() ===== */

/* WARNING: Removing unreachable block (ram,0x00794708) */
/* WARNING: Removing unreachable block (ram,0x00794388) */
/* WARNING: Removing unreachable block (ram,0x00794530) */
/* WARNING: Removing unreachable block (ram,0x00794550) */
/* WARNING: Removing unreachable block (ram,0x0079455c) */
/* WARNING: Removing unreachable block (ram,0x00794594) */
/* WARNING: Removing unreachable block (ram,0x007945bc) */
/* WARNING: Removing unreachable block (ram,0x007945c0) */
/* WARNING: Removing unreachable block (ram,0x007945c8) */
/* WARNING: Removing unreachable block (ram,0x0079459c) */
/* WARNING: Removing unreachable block (ram,0x007945f4) */
/* WARNING: Removing unreachable block (ram,0x00794604) */
/* WARNING: Removing unreachable block (ram,0x00794610) */
/* WARNING: Removing unreachable block (ram,0x00794690) */
/* WARNING: Removing unreachable block (ram,0x007943a4) */
/* WARNING: Removing unreachable block (ram,0x007946a4) */
/* WARNING: Removing unreachable block (ram,0x007946e8) */
/* WARNING: Removing unreachable block (ram,0x007946d4) */
/* WARNING: Removing unreachable block (ram,0x007946f8) */
/* WARNING: Removing unreachable block (ram,0x007946fc) */
/* WARNING: Removing unreachable block (ram,0x00794734) */
/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void FUN_00794194(undefined8 param_1,undefined8 param_2,undefined8 *param_3,undefined8 param_4,
                 ulong param_5,long param_6,float *param_7)

{
  char *pcVar1;
  undefined1 *puVar2;
  int iVar3;
  char cVar4;
  bool bVar5;
  undefined1 uVar6;
  uint uVar7;
  uint uVar8;
  undefined8 uVar10;
  ulong uVar11;
  undefined4 *puVar12;
  float *pfVar13;
  float *extraout_x1;
  float *extraout_x1_00;
  undefined8 extraout_x1_01;
  size_t sVar15;
  undefined8 uVar16;
  undefined1 extraout_w8;
  int extraout_w8_00;
  undefined8 extraout_x8;
  long extraout_x8_00;
  undefined8 extraout_x8_01;
  long lVar17;
  long extraout_x8_02;
  ulong *extraout_x8_03;
  ulong *extraout_x8_04;
  ulong *extraout_x8_05;
  ulong *extraout_x8_06;
  ulong *extraout_x8_07;
  ulong *extraout_x8_08;
  ulong *extraout_x8_09;
  ulong *puVar18;
  ulong extraout_x8_10;
  ulong *extraout_x8_11;
  long extraout_x8_12;
  long extraout_x8_13;
  long extraout_x8_14;
  long extraout_x8_15;
  long extraout_x8_16;
  long extraout_x9;
  ulong extraout_x9_00;
  ulong extraout_x9_01;
  ulong extraout_x9_02;
  ulong extraout_x9_03;
  ulong extraout_x9_04;
  ulong extraout_x9_05;
  ulong extraout_x9_06;
  ulong extraout_x9_07;
  long extraout_x9_08;
  long extraout_x9_09;
  long lVar19;
  long extraout_x9_10;
  float *pfVar20;
  float *extraout_x9_11;
  long extraout_x9_12;
  long extraout_x9_13;
  long extraout_x9_14;
  long extraout_x10;
  long extraout_x10_00;
  long extraout_x10_01;
  undefined8 uVar21;
  double dVar22;
  double extraout_x11;
  uint uVar23;
  double dVar24;
  double extraout_x12;
  long extraout_x13;
  undefined8 uVar25;
  long extraout_x14;
  undefined8 uVar26;
  long lVar27;
  int iVar28;
  long unaff_x28;
  long unaff_x29;
  undefined4 extraout_s0;
  float fVar29;
  undefined4 extraout_s0_00;
  undefined4 uVar30;
  ulong extraout_d0;
  undefined8 extraout_d0_00;
  undefined8 extraout_var;
  float fVar31;
  ulong uVar32;
  float fVar33;
  float fVar34;
  float fVar35;
  float fVar36;
  float fVar37;
  float fVar38;
  float fVar39;
  float fVar40;
  float fVar41;
  float fVar42;
  float extraout_s16;
  float fVar43;
  float extraout_s17;
  float extraout_s19;
  float extraout_s21;
  float extraout_s22;
  undefined1 auVar44 [16];
  undefined1 auVar45 [12];
  undefined1 *puVar46;
  undefined1 *puVar47;
  float local_2bc;
  float fStack_2b8;
  char local_2b4;
  char local_2b0;
  char local_2ac;
  undefined8 local_2a8;
  float local_29c;
  float local_298;
  float fStack_294;
  undefined1 auStack_290 [96];
  undefined4 local_230 [2];
  int local_228;
  int local_224;
  long *local_1d0;
  undefined8 uStack_1c8;
  long local_1c0 [7];
  long *local_188;
  long lVar9;
  uint *puVar14;
  
  FUN_00b8b7b4();
  FUN_00be586c();
  auVar44 = FUN_00b92a78();
  puVar14 = auVar44._8_8_;
  lVar9 = auVar44._0_8_;
  *(undefined8 *)(unaff_x29 + -0xa0) = extraout_x8;
  uVar6 = *(int *)(lVar9 + 0x5b00) == 0x123456;
  if ((!(bool)uVar6) ||
     ((pcVar1 = (char *)(lVar9 + 23000), (*(byte *)(param_6 + 0x11) & 1) != 0 &&
      (uVar6 = *(int *)(lVar9 + 0x5b04) == 0x1234567, !(bool)uVar6)))) goto LAB_0079583c;
  uVar7 = *(uint *)(lVar9 + 0x3e28);
  puVar2 = (undefined1 *)(lVar9 + 0x2664);
  lVar17 = lVar9;
  if (uVar7 == puVar14[2]) {
    uVar8 = *(uint *)(lVar9 + 0x3e2c);
    uVar23 = puVar14[3];
    if (uVar8 != uVar23) goto LAB_00794238;
  }
  else {
    uVar23 = puVar14[3];
    uVar8 = *(uint *)(lVar9 + 0x3e2c);
LAB_00794238:
    uVar11 = (ulong)(uint)((float)(int)uVar23 / (float)(int)uVar8);
    uVar32 = (ulong)(uint)((float)(int)puVar14[2] / (float)(int)uVar7);
    lVar27 = 0;
    while (lVar27 < *(int *)(lVar9 + 0x25d4)) {
      *(float *)(*(long *)(lVar9 + 0x25d8) + lVar27 * 4) =
           (float)uVar11 * *(float *)(*(long *)(lVar9 + 0x25d8) + lVar27 * 4);
      lVar17 = FUN_00be0e04(*(undefined8 *)(lVar9 + 0x2610));
      *(float *)(extraout_x8_00 + extraout_x9 * 4) =
           (float)uVar32 * *(float *)(extraout_x8_00 + extraout_x9 * 4);
      uVar11 = extraout_d0;
      lVar27 = extraout_x9 + 1;
    }
    uVar7 = puVar14[2];
  }
  *(uint *)(lVar9 + 0x3e28) = uVar7;
  *(uint *)(lVar9 + 0x3e2c) = puVar14[3];
  if ((*(byte *)(param_6 + 0x12) & 0xc) == 0) {
    sVar15 = 0x440;
    auVar45 = FUN_00b93a0c();
    memset(auVar45._0_8_,auVar45._8_4_,sVar15);
    sVar15 = 0x440;
    auVar45 = FUN_00b93a0c();
    memset(auVar45._0_8_,auVar45._8_4_,sVar15);
    FUN_00792cf8(lVar9 + 0x25c8,&stack0x000006c0,0);
    FUN_00bfd0e0();
    FUN_00b8d834();
    cVar4 = *pcVar1;
    *pcVar1 = '\0';
    if (cVar4 == '\0') {
      if ((param_5 & 1) == 0) {
        local_1d0 = local_1c0;
        uStack_1c8 = 0x108;
        puVar12 = &DAT_013a2554;
        for (lVar17 = 0; lVar17 != 0x1a8; lVar17 = lVar17 + 4) {
          FUN_00bc6108(*(undefined8 *)(lVar9 + 0x25d8),&stack0x00000b18);
          FUN_00bc6108(*(long *)(lVar9 + 0x25d8) + **(long **)(lVar9 + 0x2610),&stack0x00000b18);
          uVar10 = FUN_00bb9864(puVar12[-1],&local_1d0);
          local_230[0] = extraout_s0;
          FUN_007958bc(uVar10,local_230);
          FUN_00bf1a9c(*puVar12);
          local_230[0] = FUN_00bb9864();
          FUN_007958bc();
          puVar12 = puVar12 + 2;
        }
        uVar11 = FUN_00acb248(DAT_00d98c98,lVar9 + 0x3e30,&stack0x00000b18);
        if ((uVar11 & 1) == 0) {
          FUN_00bec310(0x3d68);
          FUN_0079585c();
          FUN_00bec310(0x3dc8);
          FUN_0079588c();
        }
        else {
          uVar10 = FUN_00bfa250(uVar11,&local_1d0);
          FUN_00bfa248(uVar10,&stack0x00000b18);
        }
        FUN_0078ca3c(&local_1d0);
        FUN_00bfd0d0();
      }
      else {
        FUN_00acb460(lVar9 + 0x2378,lVar9 + 0x2eac);
        thunk_FUN_00b9dd80(*(undefined8 *)(lVar9 + 0x25d8),**(undefined8 **)(lVar9 + 0x2610),
                           (undefined8 *)(lVar9 + 0x2368));
        FUN_00ba5130();
        uVar11 = FUN_00acb6fc();
        if ((uVar11 & 1) == 0) {
          FUN_00bec310(0x3d68);
          FUN_0079585c();
          FUN_00bec310(0x3dc8);
          FUN_0079588c();
        }
        else {
          FUN_00c124f8();
          *(undefined8 *)(lVar9 + 0x2560) = *(undefined8 *)(lVar9 + 0x2370);
          *(undefined8 *)(lVar9 + 0x2558) = *(undefined8 *)(lVar9 + 0x2368);
        }
        uVar10 = FUN_00bd61e0(&stack0x00000b18);
        FUN_00ba5158(uVar10,&stack0x00000b18);
        FUN_00be584c();
        uVar10 = FUN_00bdd3d0(0x23d8);
        FUN_00baacc8(0x3dc8,uVar10,&stack0x00000b18);
        FUN_00be584c();
        FUN_00bb4da0(0x3e30);
        FUN_00c0e3f4();
      }
    }
    else {
      uVar10 = FUN_00bdd3d0(0x2378);
      FUN_00ba5158(uVar10,&stack0x00000b18);
      FUN_00be584c();
      uVar10 = FUN_00bdd3d0(0x23d8);
      FUN_00baacc8(0x3dc8,uVar10,&stack0x00000b18);
      FUN_00be584c();
      FUN_00bb4da0(0x3e30);
      FUN_00bf6ed0();
    }
    FUN_0079031c(&stack0x00000268);
    FUN_0079031c(&stack0x000006c0);
  }
  else {
    cVar4 = *pcVar1;
    *pcVar1 = '\0';
    if (cVar4 == '\0') {
      if ((param_5 & 1) == 0) {
        puVar12 = &DAT_013a2554;
        for (lVar27 = 0; lVar27 != 0x1a8; lVar27 = lVar27 + 4) {
          FUN_00bc6108(*(undefined8 *)(lVar9 + 0x25d8),&stack0x000006c0);
          FUN_00bc6108(*(long *)(lVar9 + 0x25d8) + **(long **)(lVar9 + 0x2610),&stack0x000006c0);
          uVar10 = FUN_00bb9864(puVar12[-1],&stack0x00000268);
          FUN_007958bc(uVar10,&stack0x00000b18);
          FUN_00bb9864(*puVar12,&stack0x00000268,&stack0x00000b18);
          lVar17 = FUN_007958bc();
          puVar12 = puVar12 + 2;
        }
        uVar10 = FUN_00bfa250(lVar17,&stack0x00000268);
        FUN_00bfa248(uVar10,&stack0x000006c0);
        FUN_00bfd0d8();
        FUN_0078ca3c(&stack0x000006c0);
      }
      else {
        FUN_00acb460(lVar9 + 0x2378,lVar9 + 0x2eac);
        thunk_FUN_00b9dd80(*(undefined8 *)(lVar9 + 0x25d8),**(undefined8 **)(lVar9 + 0x2610),
                           (undefined8 *)(lVar9 + 0x2368));
        FUN_00c124f8();
        *(undefined8 *)(lVar9 + 0x2560) = *(undefined8 *)(lVar9 + 0x2370);
        *(undefined8 *)(lVar9 + 0x2558) = *(undefined8 *)(lVar9 + 0x2368);
        uVar10 = FUN_005d8e1c(&stack0x000006c0,lVar9 + 0x2378);
        FUN_00ba5158(uVar10,&stack0x000006c0);
        FUN_00bc3a20();
        uVar10 = FUN_00bdd3dc(0x23d8);
        FUN_00baacc8(0x3dc8,uVar10,&stack0x000006c0);
        FUN_00bc3a20();
        FUN_00bb4da0(0x3e30);
        FUN_00c0e3f4();
      }
    }
    else {
      uVar10 = FUN_00bdd3dc(0x2378);
      FUN_00ba5158(uVar10,&stack0x000006c0);
      FUN_00bc3a20();
      uVar10 = FUN_00bdd3dc(0x23d8);
      FUN_00baacc8(0x3dc8,uVar10,&stack0x000006c0);
      FUN_00bc3a20();
      FUN_00bb4da0(0x3e30);
      FUN_00bf6ed0();
    }
  }
  param_3[1] = *param_3;
  uVar10 = *(undefined8 *)(lVar9 + 0x2ea4);
  iVar3 = *(int *)(param_6 + 0x18);
  uVar7 = *puVar14 >> 3 & 0x1ff;
  if (uVar7 == 0) {
    FUN_00bb096c(0x30b0);
    auVar44._8_8_ = &stack0x00000268;
    auVar44._0_8_ = &stack0x000006c0;
    uVar16 = 8;
LAB_00794a84:
    FUN_00b90d0c(auVar44._0_8_,auVar44._8_8_,uVar16);
  }
  else if (uVar7 == 2) {
    if (iVar3 == 3) {
      FUN_00bb096c(0x3110);
      FUN_00b90b48();
      auVar44._8_8_ = &stack0x00000268;
      auVar44._0_8_ = &stack0x000006c0;
      uVar16 = 4;
      goto LAB_00794a84;
    }
    if (iVar3 == 2) {
      FUN_00cc024c(puVar14,lVar9 + 0x3110,lVar9 + 0x3e30,&stack0x00000b18);
    }
  }
  else if (uVar7 == 3) {
    FUN_00bb096c(0x3170);
    if (iVar3 == 0) {
      auVar44 = FUN_00bb082c();
      uVar16 = 3;
    }
    else {
      if (iVar3 != 1) goto LAB_00794a88;
      auVar44 = FUN_00bb082c();
      uVar16 = 1;
    }
    goto LAB_00794a84;
  }
LAB_00794a88:
  FUN_00c4c04c(local_230,lVar9 + 0x3110);
  uVar6 = 0;
  if ((local_224 == *(int *)(lVar9 + 0x2ea4)) &&
     (uVar6 = local_228 == *(int *)(lVar9 + 0x2ea8), (bool)uVar6)) {
    FUN_005d8e1c(&stack0x000006c0,lVar9 + 0x3e30);
    FUN_00c06d68(0x3d68);
    FUN_00bc3a20();
    FUN_005d8e1c(&stack0x000006c0,lVar9 + 0x3e90);
    FUN_00c06d68(0x3dc8);
    FUN_00bc3a20();
    auVar44 = FUN_00c4bf20(auStack_290);
    local_2ac = '\0';
    local_2b0 = '\0';
    local_2b4 = '\0';
    local_2bc = 0.0;
    fStack_2b8 = 0.0;
    FUN_00b9878c(&local_2bc,auVar44._0_8_,auVar44._8_8_,local_230,auStack_290,
                 *(undefined8 *)(param_6 + 0x10));
    uVar11 = FUN_007932ec();
    fVar29 = fStack_2b8;
    fVar31 = local_2bc;
    if ((uVar11 & 1) != 0) {
      *(undefined8 *)(param_7 + 3) = local_2a8;
      *(undefined8 *)(lVar9 + 0x2630) = *(undefined8 *)(lVar9 + 0x2628);
      *(undefined8 *)(lVar9 + 0x2648) = *(undefined8 *)(lVar9 + 0x2640);
      FUN_0079410c(auStack_290,lVar9 + 0x2628,1);
      FUN_00b9e93c(*(undefined8 *)(lVar9 + 0x2640));
      *(undefined8 *)(lVar9 + 0x2648) = extraout_x8_01;
      FUN_007905b0();
      FUN_00795da8(&stack0x000006c0);
      for (lVar17 = 0; lVar17 != 3; lVar17 = lVar17 + 1) {
        *(double *)(&stack0x000006c0 + lVar17 * 8) =
             (double)*(float *)(&stack0x00000268 + lVar17 * 4);
      }
      fVar29 = -fVar29;
      fVar31 = -fVar31;
      param_7[2] = fVar29;
      *param_7 = fVar31;
      param_7[1] = -1.0;
      iVar3 = *(int *)(param_6 + 0x1c);
      pfVar20 = param_7 + 6;
      iVar28 = 8;
      param_7[8] = 0.0;
      param_7[9] = 0.0;
      do {
        FUN_0053d614(pfVar20,&stack0x00000268);
        iVar28 = iVar28 + -1;
      } while (iVar28 != 0);
      if (*(float *)(lVar9 + 0x27fc) != (float)iVar3) {
        *(undefined4 *)(lVar9 + 0x27f8) = 0;
        *puVar2 = 0;
        FUN_005adcf0(lVar9 + 0x2670);
        FUN_005adcf0(lVar9 + 0x26a0);
        FUN_00790a90(lVar9 + 0x2790);
        *(undefined1 *)(lVar9 + 0x27c0) = 0;
      }
      lVar17 = *(long *)(lVar9 + 0x2640);
      fVar34 = (float)*(undefined8 *)(lVar17 + 0x198) - (float)*(undefined8 *)(lVar17 + 0x1c8);
      fVar35 = (float)((ulong)*(undefined8 *)(lVar17 + 0x198) >> 0x20) -
               (float)((ulong)*(undefined8 *)(lVar17 + 0x1c8) >> 0x20);
      fVar38 = ABS(*(float *)(lVar17 + 0x150) - *(float *)(lVar17 + 0x168));
      fVar36 = *(float *)(lVar17 + 0x40) - *(float *)(lVar17 + 0xd8);
      fVar42 = *(float *)(lVar17 + 0x44) - *(float *)(lVar17 + 0xdc);
      fVar39 = (*(float *)(lVar17 + 300) - *(float *)(lVar17 + 0x14c)) + *(float *)(lVar17 + 0x134);
      fVar43 = SQRT(fVar36 * fVar36 + fVar42 * fVar42);
      puVar46 = (undefined1 *)(ulong)(uint)fVar43;
      fVar37 = ABS(((*(float *)(lVar17 + 0x15c) - *(float *)(lVar17 + 0x17c)) +
                   *(float *)(lVar17 + 0x164)) - *(float *)(lVar17 + 0x174));
      fVar42 = (float)*(undefined8 *)(lVar17 + 0x1f0) - (float)*(undefined8 *)(lVar17 + 0x210);
      fVar33 = (float)((ulong)*(undefined8 *)(lVar17 + 0x1f0) >> 0x20) -
               (float)((ulong)*(undefined8 *)(lVar17 + 0x210) >> 0x20);
      fVar40 = (float)*(undefined8 *)(lVar17 + 0xa8) - (float)*(undefined8 *)(lVar17 + 0xd8);
      fVar41 = (float)((ulong)*(undefined8 *)(lVar17 + 0xa8) >> 0x20) -
               (float)((ulong)*(undefined8 *)(lVar17 + 0xd8) >> 0x20);
      puVar47 = (undefined1 *)
                CONCAT44((int)((ulong)uVar10 >> 0x20),
                         SQRT(fVar40 * fVar40 + fVar41 * fVar41) / fVar43);
      fVar35 = SQRT(fVar42 * fVar42 + fVar33 * fVar33) / SQRT(fVar34 * fVar34 + fVar35 * fVar35);
      fVar42 = (float)*(undefined8 *)(*(long *)(lVar9 + 0x2640) + 0xb0) -
               (float)*(undefined8 *)(*(long *)(lVar9 + 0x2640) + 0xd8);
      FUN_00bf2274();
      local_1d0 = (long *)CONCAT44(local_1d0._4_4_,fVar42 / extraout_s17);
      fVar37 = SUB84((double)fVar37 / ((double)fVar38 + (double)fVar38),0);
      fStack_294 = ((fVar36 * (((*(float *)(extraout_x8_02 + 0x148) +
                                *(float *)(extraout_x8_02 + 0x140)) -
                               *(float *)(extraout_x8_02 + 0x128)) -
                              *(float *)(extraout_x8_02 + 0x130)) * 0.5 +
                    extraout_s16 *
                    (((*(float *)(extraout_x8_02 + 0x14c) + *(float *)(extraout_x8_02 + 0x144)) -
                     *(float *)(extraout_x8_02 + 300)) - *(float *)(extraout_x8_02 + 0x134)) * 0.5)
                   / extraout_s17) / extraout_s17;
      local_298 = ((fVar36 * (((*(float *)(extraout_x8_02 + 0x178) +
                               *(float *)(extraout_x8_02 + 0x170)) -
                              *(float *)(extraout_x8_02 + 0x158)) -
                             *(float *)(extraout_x8_02 + 0x160)) * 0.5 +
                   extraout_s16 *
                   (((*(float *)(extraout_x8_02 + 0x17c) + *(float *)(extraout_x8_02 + 0x174)) -
                    *(float *)(extraout_x8_02 + 0x15c)) - *(float *)(extraout_x8_02 + 0x164)) * 0.5)
                  / extraout_s17) / extraout_s17;
      fVar42 = local_298;
      fVar33 = fStack_294;
      fVar34 = (float)FUN_00bbdd2c(*puVar2,(double)ABS(fVar39 - extraout_s19) /
                                           ((double)ABS(extraout_s21 - extraout_s22) +
                                           (double)ABS(extraout_s21 - extraout_s22)));
      if (extraout_w8_00 == 0) {
        fVar34 = (float)FUN_00bfe75c(1);
        *(float *)(lVar9 + 0x2658) = fVar35;
        *(float *)(lVar9 + 0x265c) = fVar37;
        *(float *)(lVar9 + 0x2660) = fVar34;
        *puVar2 = extraout_w8;
        fVar36 = fVar35;
      }
      else {
        fVar42 = *(float *)(lVar9 + 0x265c);
        fVar33 = *(float *)(lVar9 + 0x2660);
        fVar36 = *(float *)(lVar9 + 0x2658);
      }
      fVar38 = *(float *)(lVar9 + 0x266c);
      fVar39 = 1.0 - fVar38;
      fVar36 = fVar35 * fVar38 + fVar39 * fVar36;
      *(float *)(lVar9 + 0x2658) = fVar36;
      *(float *)(lVar9 + 0x265c) = fVar38 * fVar37 + fVar39 * fVar42;
      *(float *)(lVar9 + 0x2660) = fVar38 * fVar34 + fVar39 * fVar33;
      if (*(int *)(lVar9 + 0x27f8) < *(int *)(lVar9 + 0x2668) + -1) {
        FUN_00bdd3bc(0x2670,(double)fVar31);
        FUN_00bdd3bc(0x26a0,(double)fVar29);
        FUN_005af65c(lVar9 + 0x26d0,&stack0x00000b18);
        FUN_005af65c(lVar9 + 0x2700,&local_1d0);
        FUN_005af65c(lVar9 + 0x2730,&fStack_294);
        FUN_005af65c(lVar9 + 0x2760,&local_298);
        uVar10 = FUN_005af65c(lVar9 + 0x27c8,&stack0x00000268);
        *(int *)(lVar9 + 0x27f8) = *(int *)(lVar9 + 0x27f8) + 1;
      }
      else {
        FUN_005af65c(lVar9 + 0x27c8,&stack0x00000268);
        lVar17 = lVar9 + 0x2670;
        local_29c = fVar31;
        FUN_007958ec(lVar17,&local_29c);
        lVar27 = lVar9 + 0x26a0;
        local_29c = fVar29;
        FUN_007958ec(lVar27,&local_29c);
        FUN_005af65c(lVar9 + 0x26d0,&stack0x00000b18);
        lVar19 = lVar9 + 0x2700;
        FUN_005af65c(lVar19,&local_1d0);
        FUN_005af65c(lVar9 + 0x2730,&fStack_294);
        FUN_005af65c(lVar9 + 0x2760,&local_298);
        uVar10 = *(undefined8 *)(lVar9 + 0x26a8);
        uVar16 = *(undefined8 *)(lVar9 + 0x26b0);
        uVar21 = *(undefined8 *)(lVar9 + 0x26c0);
        FUN_005add78(uVar10,uVar16,uVar21);
        auVar44 = FUN_005adda0(lVar27);
        FUN_00ba1b6c(auVar44._0_8_,auVar44._8_8_,auVar44._0_8_,auVar44._8_8_);
        FUN_0079028c();
        fVar29 = *extraout_x1;
        puVar12 = (undefined4 *)FUN_00bbe494(uVar10,uVar16,uVar21);
        uVar10 = *(undefined8 *)(lVar9 + 0x2678);
        fVar31 = (float)FUN_00b927a0(*puVar12,uVar10);
        fVar29 = fVar29 - fVar31;
        FUN_005add78();
        auVar44 = FUN_005adda0(lVar17);
        FUN_00bb6a58(auVar44._0_8_,auVar44._8_8_,auVar44._0_8_,auVar44._8_8_);
        FUN_0079028c();
        fVar42 = *extraout_x1_00;
        FUN_00b927a0(uVar10);
        pfVar13 = (float *)FUN_00bbe494();
        fVar42 = fVar42 - *pfVar13;
        uVar7 = FUN_00795924(lVar19);
        uVar8 = FUN_00795924(lVar9 + 0x26d0);
        FUN_005af690(lVar17);
        FUN_005af690(lVar27);
        FUN_00bcaf34();
        FUN_005af690(lVar19);
        FUN_005af690(lVar9 + 0x2730);
        FUN_005af690(lVar9 + 0x2760);
        FUN_005af690(lVar9 + 0x27c8);
        fVar31 = 18.0;
        if (18.0 <= fVar42) {
          fVar31 = 18.0;
          FUN_00bc7be4();
          *extraout_x8_03 = extraout_x9_00 | 1;
        }
        fVar35 = fVar35 * fVar39;
        fVar38 = fVar38 * fVar36;
        if (fVar31 <= fVar29) {
          fVar35 = (float)FUN_00bc7be4();
          *extraout_x8_04 = extraout_x9_01 | 2;
        }
        fVar31 = DAT_00d98eb4;
        if ((*(int *)(lVar9 + 0x2800) != 1) &&
           (fVar31 = DAT_00d98ebc, *(int *)(lVar9 + 0x2800) == -1)) {
          fVar31 = DAT_00d98eb8;
        }
        if (fVar31 < fVar35 + fVar38) {
          FUN_00bc7be4();
          *extraout_x8_05 = extraout_x9_02 | 4;
        }
        *(uint *)(lVar9 + 0x2800) = **(uint **)pfVar20 >> 2 & 1;
        if (local_2b0 != '\0' || local_2ac != '\0') {
          **(ulong **)pfVar20 = **(ulong **)pfVar20 | 8;
          if (local_2ac != '\0') {
            **(ulong **)pfVar20 = **(ulong **)pfVar20 | 0x40;
          }
          if (local_2b0 != '\0') {
            FUN_00bc7be4();
            *extraout_x8_06 = extraout_x9_03 | 0x80;
          }
        }
        if (((uVar7 | uVar8) & 1) != 0) {
          bVar5 = false;
          if ((fVar29 < 3.0) && (bVar5 = false, !NAN(fVar42))) {
            bVar5 = fVar42 < 5.0;
          }
          if (bVar5) {
            uVar11 = **(ulong **)pfVar20;
            if ((uVar11 & 3) == 0) {
              **(ulong **)pfVar20 = uVar11 | 0x10;
            }
          }
        }
        if (local_2b4 != '\0') {
          FUN_00bc7be4();
          *extraout_x8_07 = extraout_x9_04 | 0x20;
        }
        if (*(ulong *)(lVar9 + 0x27b8) < 3) {
          uVar10 = FUN_00bd2320(lVar9 + 0x2790);
        }
        else {
          FUN_00bd2320(lVar9 + 0x2790);
          uVar11 = *(ulong *)(lVar9 + 0x27b0);
          lVar17 = *(long *)(lVar9 + 0x2798);
          if ((*(char *)(*(long *)(lVar17 + (uVar11 >> 9 & 0x7ffffffffffff8)) + (uVar11 & 0xfff)) ==
               '\0') &&
             ((*(char *)(*(long *)(lVar17 + (uVar11 + 1 >> 9 & 0x7ffffffffffff8)) +
                        (uVar11 + 1 & 0xfff)) == '\0' ||
              (*(char *)(*(long *)(lVar17 + (uVar11 + 2 >> 9 & 0x7ffffffffffff8)) +
                        (uVar11 + 2 & 0xfff)) != '\0')))) {
            bVar5 = false;
            if ((fVar29 < 5.0) && (bVar5 = false, !NAN(fVar42))) {
              bVar5 = fVar42 < 5.0;
            }
            if ((!bVar5) || (FUN_00bc7be4(), ((uint)extraout_x9_05 >> 3 & 1) == 0))
            goto LAB_00795294;
            uVar11 = extraout_x9_05 | 8;
            puVar18 = extraout_x8_08;
          }
          else {
LAB_00795294:
            FUN_00bc7be4();
            uVar11 = extraout_x9_06 & 0xfffffffffffffff7;
            puVar18 = extraout_x8_09;
          }
          *puVar18 = uVar11;
          uVar10 = FUN_00bc3ae4(*(undefined8 *)(lVar9 + 0x27b0),_DAT_00d95020);
          *(undefined8 *)(lVar9 + 0x27b8) = extraout_var;
          *(undefined8 *)(lVar9 + 0x27b0) = extraout_d0_00;
          if (0x1fff < extraout_x8_10) {
            uVar10 = FUN_00b96768(*(undefined8 *)(lVar9 + 0x2798));
            *(long *)(lVar9 + 0x2798) = *(long *)(lVar9 + 0x2798) + 8;
            *(long *)(lVar9 + 0x27b0) = *(long *)(lVar9 + 0x27b0) + -0x1000;
          }
        }
        uVar11 = **(ulong **)pfVar20;
        if ((uVar11 & 3) != 0) {
          **(ulong **)pfVar20 = uVar11 & 0xfffffffffffffff7;
          uVar10 = FUN_00bc7be4();
          *extraout_x8_11 = extraout_x9_07 & 0xffffffffffffffef;
        }
      }
      lVar17 = lVar9 + 0x25c8;
      FUN_00bd23e0(uVar10,auStack_290);
      *(float *)(lVar9 + 0x27fc) = (float)iVar3;
      FUN_00acb16c();
      uVar11 = *(ulong *)(param_6 + 0x10);
      if ((*(int *)(lVar9 + 0x2ea4) == 0xa0) || ((uVar11 & 0x900) == 0)) {
        uVar6 = 1;
        FUN_0079410c(lVar17,param_3,uVar11);
      }
      else {
        FUN_00b8cc70(&stack0x000006d0,extraout_x1_01,0x440);
        FUN_00792cf8(lVar17,&stack0x000006c0,uVar11);
        FUN_00b8fbfc(&stack0x00000268,2,0x6a);
        auVar44 = FUN_00c0eaa4(puVar46,&stack0x00000268);
        FUN_00b8bcb0(auVar44._0_8_,auVar44._8_8_,lVar9 + 0x3370);
        FUN_005904a8(&stack0x00000268);
        uVar10 = FUN_00ba5240();
        lVar27 = extraout_x8_12;
        for (lVar19 = extraout_x10; lVar19 < extraout_x9_08; lVar19 = lVar19 + 1) {
          *(undefined4 *)(*(long *)(lVar9 + 0x3380) + lVar19 * 4) =
               *(undefined4 *)(&stack0x000006d0 + lVar27);
          puVar12 = (undefined4 *)(&stack0x000006d4 + lVar27);
          lVar27 = lVar27 + 8;
          *(undefined4 *)(*(long *)(lVar9 + 0x3380) + **(long **)(lVar9 + 0x33b8) + lVar19 * 4) =
               *puVar12;
        }
        uVar11 = *(ulong *)(param_6 + 0x10);
        if ((uVar11 & 0x900) != 0) {
          puVar46 = &stack0x00000278;
          puVar47 = &stack0x00000b28;
          pfVar20 = (float *)&DAT_013a28a4;
          for (lVar27 = 0; lVar27 != 0x1a8; lVar27 = lVar27 + 4) {
            FUN_00bc6108(*(undefined8 *)(lVar9 + 0x3380),&stack0x00000268);
            FUN_00bc6108(*(long *)(lVar9 + 0x3380) + **(long **)(lVar9 + 0x33b8),&stack0x00000268);
            uVar10 = FUN_00bf5cd4((double)pfVar20[-1] * 0.00390625,&stack0x00000b18);
            local_1d0._0_4_ = extraout_s0_00;
            FUN_007958bc(uVar10,&local_1d0);
            uVar30 = FUN_00bf5cd4((double)*pfVar20 * 0.00390625,&stack0x00000b18,&local_1d0);
            local_1d0 = (long *)CONCAT44(local_1d0._4_4_,uVar30);
            uVar10 = FUN_007958bc();
            pfVar20 = pfVar20 + 2;
          }
          uVar10 = FUN_00bfa250(uVar10,&stack0x00000b18);
          FUN_00bfa248(uVar10,&stack0x00000268);
          FUN_00b8fbfc(&local_1d0,2,0x6a);
          auVar44 = FUN_00c0eaa4(local_1d0,&local_1d0);
          FUN_00b8bcb0(auVar44._0_8_,auVar44._8_8_,lVar9 + 0x3d08);
          FUN_005904a8(&local_1d0);
          auVar44 = FUN_00ba5130();
          auVar44 = FUN_00acb1d8(auVar44._0_8_,auVar44._8_8_,lVar9 + 0x3d08);
          lVar27 = 0;
          while( true ) {
            if (lVar27 == 0x1a8) break;
            *(undefined4 *)(*(long *)(lVar9 + 0x2578) + lVar27) =
                 *(undefined4 *)(*(long *)(lVar9 + 0x3d18) + lVar27);
            auVar44 = FUN_00bd9588();
            lVar27 = extraout_x8_13;
          }
          uVar7 = (uint)*(undefined8 *)(param_6 + 0x10);
          if ((uVar7 >> 8 & 1) != 0) {
            FUN_00b9878c(auVar44._0_8_,auVar44._8_8_,*(undefined4 *)(param_6 + 0x18),
                         (uVar11 & 0x300000) == 0x300000 && (~uVar7 & 0x500) != 0);
            auVar44 = FUN_00791904();
            uVar7 = (uint)*(undefined8 *)(param_6 + 0x10);
          }
          if ((uVar7 >> 0xb & 1) != 0) {
            FUN_00b9878c(auVar44._0_8_,auVar44._8_8_,*(undefined4 *)(param_6 + 0x18));
            FUN_00791608();
          }
          FUN_00bfd0d0();
          FUN_00bfd0d8();
        }
        FUN_00acb16c(lVar9 + 0x33d0,lVar9 + 0x2568,lVar17);
        FUN_00bfd22c(&stack0x00000268);
        FUN_00bfd22c(&stack0x00000b18);
        FUN_00b8d834();
        lVar17 = extraout_x10_00 + 0x390;
        dVar22 = 0.00390625;
        dVar24 = 256.0;
        lVar27 = extraout_x8_14;
        lVar19 = extraout_x9_09;
        while (lVar27 != 0x350) {
          *(undefined4 *)(puVar46 + lVar27) = *(undefined4 *)(*(long *)(lVar9 + 0x25d8) + lVar19);
          *(undefined4 *)(puVar46 + lVar27 + 4) =
               *(undefined4 *)(*(long *)(lVar9 + 0x25d8) + **(long **)(lVar9 + 0x2610) + lVar19);
          uVar30 = FUN_00c06ca4((double)*(float *)(lVar17 + lVar27 + 0x838) * dVar22);
          *(undefined4 *)(extraout_x14 + extraout_x8_15) = uVar30;
          *(float *)(puVar47 + extraout_x8_15 + 4) =
               (float)((double)*(float *)(extraout_x13 + 0x83c) * dVar22 * dVar24);
          lVar19 = extraout_x9_10;
          lVar17 = extraout_x10_01;
          dVar22 = extraout_x11;
          dVar24 = extraout_x12;
          lVar27 = extraout_x8_15 + 8;
        }
        lVar17 = lVar9 + 0x50a0;
        FUN_00792d74(lVar17,&stack0x00000b18);
        FUN_00792dbc(lVar17,&stack0x00000268);
        auVar44 = FUN_00bf1aa8();
        FUN_00b90d3c(auVar44._0_8_,auVar44._8_8_,&local_1d0);
        FUN_00acb1d8();
        lVar27 = 0;
        pfVar20 = (float *)&DAT_013a434c;
        while (lVar27 != 0x1a8) {
          *(float *)(local_1c0[0] + lVar27) = *(float *)(local_1c0[0] + lVar27) - pfVar20[-1];
          FUN_00bbd0ac(*(float *)(local_1c0[0] + *local_188 + lVar27) - *pfVar20);
          lVar27 = extraout_x8_16;
          pfVar20 = extraout_x9_11;
        }
        lVar27 = 0x1a8;
        while (lVar27 != 600) {
          FUN_00b9a0b4();
          lVar27 = extraout_x9_12;
        }
        lVar27 = 600;
        while (lVar27 != 0x2c0) {
          FUN_00b9a0b4();
          lVar27 = extraout_x9_13;
        }
        lVar27 = 0x2c0;
        while (uVar6 = lVar27 == 0x3c0, !(bool)uVar6) {
          FUN_00b9a0b4();
          lVar27 = extraout_x9_14;
        }
        FUN_00acb16c(lVar17,&local_1d0,&local_1d0);
        FUN_0079410c(&local_1d0,param_3,0x900);
        FUN_00b9e0e0();
        FUN_00bfd0d0();
        FUN_00bfd0d8();
        FUN_0079031c(&stack0x000006c0);
      }
      if (*(char *)(lVar9 + 0x2a48) == '\0') {
        uVar10 = 0;
        uVar21 = 0;
        uVar16 = 0;
        fVar31 = 0.0;
        fVar29 = 0.0;
      }
      else {
        uVar16 = *(undefined8 *)(lVar9 + 0x28d8);
        fVar31 = *(float *)(lVar9 + 0x28d0);
        uVar10 = *(undefined8 *)(lVar9 + 0x29f8);
        uVar21 = *(undefined8 *)(lVar9 + 0x2938);
        fVar29 = *(float *)(lVar9 + 0x2930);
      }
      if (*(char *)(lVar9 + 0x2a49) == '\0') {
        uVar25 = 0;
        uVar26 = 0;
        fVar42 = 0.0;
      }
      else {
        uVar25 = *(undefined8 *)(lVar9 + 0x2998);
        fVar42 = *(float *)(lVar9 + 0x2990);
        uVar26 = *(undefined8 *)(lVar9 + 0x29f8);
      }
      *(undefined8 *)(param_7 + 0x10) = uVar10;
      *(undefined8 *)(param_7 + 0x12) = uVar21;
      *(undefined8 *)(param_7 + 0x16) = uVar10;
      *(undefined8 *)(param_7 + 0x18) = uVar25;
      *(undefined8 *)(param_7 + 0xc) = uVar16;
      param_7[0xe] = fVar31;
      param_7[0x14] = fVar29;
      param_7[0x1a] = fVar42;
      *(undefined8 *)(param_7 + 0x1c) = uVar26;
      FUN_0066d328(param_4,lVar9 + 0x31d8);
      *(undefined8 *)(lVar9 + 0x5cf0) = *(undefined8 *)(param_6 + 0x10);
    }
    FUN_00ba1738();
  }
  FUN_00badf54();
LAB_0079583c:
  FUN_00b98f7c(*(undefined8 *)(unaff_x28 + 0x28));
  if ((bool)uVar6) {
    FUN_00b911a8();
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ===== Function: FUN_00bd7da4 @ 00bd7da4  size=12  depth=1
   signature: undefined FUN_00bd7da4() ===== */

void FUN_00bd7da4(long param_1)

{
  FUN_007900ec(param_1 + 0x2320);
  return;
}



/* ===== Function: FUN_00c4c04c @ 00c4c04c  size=180  depth=1
   signature: undefined FUN_00c4c04c() ===== */

void FUN_00c4c04c(undefined4 *param_1,undefined4 *param_2)

{
  long lVar1;
  long lVar2;
  int extraout_w10;
  undefined8 unaff_x30;
  undefined1 auVar3 [16];
  
  auVar3._8_8_ = param_2;
  auVar3._0_8_ = param_1;
  *param_1 = *param_2;
  param_1[1] = param_2[1];
  param_1[2] = param_2[2];
  param_1[3] = param_2[3];
  *(undefined8 *)(param_1 + 4) = *(undefined8 *)(param_2 + 4);
  *(undefined8 *)(param_1 + 6) = *(undefined8 *)(param_2 + 6);
  *(undefined8 *)(param_1 + 8) = *(undefined8 *)(param_2 + 8);
  *(undefined8 *)(param_1 + 10) = *(undefined8 *)(param_2 + 10);
  *(undefined8 *)(param_1 + 0xc) = *(undefined8 *)(param_2 + 0xc);
  lVar2 = *(long *)(param_2 + 0xe);
  *(undefined8 *)(param_1 + 0x14) = 0;
  *(undefined4 **)(param_1 + 0x10) = param_1 + 2;
  *(undefined4 **)(param_1 + 0x12) = param_1 + 0x14;
  *(undefined8 *)(param_1 + 0x16) = 0;
  *(long *)(param_1 + 0xe) = lVar2;
  if (lVar2 != 0) {
    do {
      auVar3 = FUN_00c5fc70(auVar3._0_8_,auVar3._8_8_,unaff_x30);
    } while (extraout_w10 != 0);
  }
  lVar1 = auVar3._8_8_;
  lVar2 = auVar3._0_8_;
  if (2 < *(int *)(lVar1 + 4)) {
    *(undefined4 *)(lVar2 + 4) = 0;
    FUN_00c4a5b8();
    return;
  }
  **(undefined8 **)(lVar2 + 0x48) = **(undefined8 **)(lVar1 + 0x48);
  *(undefined8 *)(*(long *)(lVar2 + 0x48) + 8) = *(undefined8 *)(*(long *)(lVar1 + 0x48) + 8);
  return;
}



/* ===== Function: FUN_0078fc4c @ 0078fc4c  size=228  depth=1
   signature: undefined FUN_0078fc4c() ===== */

void FUN_0078fc4c(void)

{
  undefined1 uVar1;
  undefined8 uVar2;
  int extraout_w1;
  int extraout_w9;
  long unaff_x19;
  
  FUN_00be01c4();
  if (extraout_w9 == 0) {
    uVar1 = extraout_w1 == 5;
    if (!(bool)uVar1) {
LAB_0078fcc8:
      uVar2 = 0xffffffff;
      goto LAB_0078fd14;
    }
    FUN_00bbd150();
LAB_0078fd0c:
    FUN_00b90d0c();
  }
  else if (extraout_w9 == 2) {
    uVar1 = extraout_w1 == 3;
    if ((bool)uVar1) {
      FUN_00bb5d28();
      goto LAB_0078fd0c;
    }
    uVar1 = extraout_w1 == 2;
    if (!(bool)uVar1) goto LAB_0078fcc8;
    FUN_00be02b8();
  }
  else {
    uVar1 = extraout_w9 == 3;
    if ((bool)uVar1) {
      if (extraout_w1 == 0) {
        FUN_00bb5d18();
      }
      else {
        uVar1 = extraout_w1 == 1;
        if (!(bool)uVar1) goto LAB_0078fcc8;
        FUN_00b98474();
      }
      goto LAB_0078fd0c;
    }
  }
  uVar2 = 0;
LAB_0078fd14:
  FUN_00b90b3c(*(undefined8 *)(unaff_x19 + 0x28),uVar2);
  if ((bool)uVar1) {
    FUN_00b8b640();
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ===== Function: FUN_00c4c350 @ 00c4c350  size=40  depth=1
   signature: undefined FUN_00c4c350() ===== */

void FUN_00c4c350(long param_1)

{
  FUN_00c4a260();
  if (*(long *)(param_1 + 0x48) != param_1 + 0x50) {
    FUN_00c602c4();
  }
  return;
}



/* ===== Function: FUN_00ba6734 @ 00ba6734  size=12  depth=1
   signature: undefined FUN_00ba6734() ===== */

void FUN_00ba6734(void)

{
  return;
}



/* ===== Function: FUN_0079602c @ 0079602c  size=212  depth=1
   signature: undefined FUN_0079602c() ===== */

void FUN_0079602c(float *param_1,long param_2)

{
  float fVar1;
  float fVar2;
  float fVar3;
  long lVar4;
  float fVar5;
  float fVar6;
  undefined4 extraout_s0;
  float fVar7;
  float fVar8;
  float fVar9;
  float fVar10;
  float fVar11;
  float fVar12;
  float fVar13;
  
  lVar4 = 0;
  fVar8 = -1.0;
  fVar10 = -1.0;
  fVar13 = -1.0;
  fVar11 = DAT_00d98e4c;
  fVar3 = 100000.0;
  fVar5 = 100000.0;
  fVar1 = -1.0;
  fVar9 = DAT_00d98e4c;
  while (fVar7 = fVar1, fVar6 = fVar13, lVar4 != 0x350) {
    fVar12 = *(float *)(param_2 + lVar4);
    fVar13 = ((float *)(param_2 + lVar4))[1];
    lVar4 = lVar4 + 8;
    fVar1 = fVar12;
    fVar2 = fVar12;
    if (fVar11 <= fVar12) {
      fVar1 = fVar3;
      fVar2 = fVar11;
    }
    fVar11 = fVar2;
    fVar2 = fVar13;
    fVar3 = fVar13;
    if (fVar9 <= fVar13) {
      fVar2 = fVar5;
      fVar3 = fVar9;
    }
    fVar9 = fVar3;
    fVar3 = fVar12;
    if (fVar12 <= fVar8) {
      fVar12 = fVar10;
      fVar3 = fVar8;
    }
    fVar8 = fVar3;
    fVar10 = fVar12;
    fVar3 = fVar1;
    fVar5 = fVar2;
    fVar1 = fVar13;
    if (fVar13 <= fVar6) {
      fVar13 = fVar6;
      fVar1 = fVar7;
    }
  }
  fVar13 = 0.0;
  if (0.0 <= fVar11) {
    fVar13 = fVar3;
  }
  fVar11 = 0.0;
  if (0.0 <= fVar9) {
    fVar11 = fVar5;
  }
  fVar9 = 0.0;
  if (0.0 <= fVar8) {
    fVar9 = fVar10;
  }
  fVar8 = 0.0;
  if (0.0 <= fVar6) {
    fVar8 = fVar7;
  }
  *param_1 = fVar13;
  param_1[1] = fVar11;
  lVar4 = FUN_00bdabdc((int)(fVar9 - fVar13),fVar8 - fVar11);
  *(undefined4 *)(lVar4 + 8) = extraout_s0;
  *(float *)(lVar4 + 0xc) = fVar8;
  return;
}



/* ===== Function: FUN_00795ea4 @ 00795ea4  size=144  depth=1
   signature: undefined FUN_00795ea4() ===== */

void FUN_00795ea4(float param_1,long param_2,long param_3)

{
  long lVar1;
  int iVar2;
  int iVar3;
  int iVar4;
  undefined8 uVar5;
  float fVar6;
  int iVar7;
  float fVar8;
  int iVar9;
  int iVar10;
  int iVar11;
  
  uVar5 = NEON_rev64(*(undefined8 *)(param_3 + 8),4);
  lVar1 = 0;
  iVar3 = 0;
  iVar4 = 0;
  iVar2 = 10;
  if (-30.0 <= param_1 && param_1 <= 30.0) {
    iVar2 = 0x1e;
  }
  uVar5 = NEON_scvtf(uVar5,4);
  for (; lVar1 != 0x350; lVar1 = lVar1 + 8) {
    fVar6 = (float)*(undefined8 *)(param_2 + lVar1);
    iVar10 = -(uint)(fVar6 < 0.0);
    fVar8 = (float)((ulong)*(undefined8 *)(param_2 + lVar1) >> 0x20);
    iVar11 = -(uint)(fVar8 < 0.0);
    iVar7 = -(uint)((float)uVar5 <= fVar6);
    iVar9 = -(uint)((float)((ulong)uVar5 >> 0x20) <= fVar8);
    iVar7 = CONCAT13((byte)((uint)iVar7 >> 0x18) | (byte)((uint)iVar10 >> 0x18),
                     CONCAT12((byte)((uint)iVar7 >> 0x10) | (byte)((uint)iVar10 >> 0x10),
                              CONCAT11((byte)((uint)iVar7 >> 8) | (byte)((uint)iVar10 >> 8),
                                       (byte)iVar7 | (byte)iVar10)));
    iVar3 = iVar3 - iVar7;
    iVar4 = iVar4 - (int)(CONCAT17((byte)((uint)iVar9 >> 0x18) | (byte)((uint)iVar11 >> 0x18),
                                   CONCAT16((byte)((uint)iVar9 >> 0x10) |
                                            (byte)((uint)iVar11 >> 0x10),
                                            CONCAT15((byte)((uint)iVar9 >> 8) |
                                                     (byte)((uint)iVar11 >> 8),
                                                     CONCAT14((byte)iVar9 | (byte)iVar11,iVar7))))
                         >> 0x20);
  }
  iVar7 = 0x1e;
  if (-30.0 <= param_1 && param_1 <= 30.0) {
    iVar7 = 0x32;
  }
  FUN_00b99bb4(-(uint)(iVar2 <= iVar3 || iVar7 <= iVar4));
  return;
}



/* ===== Function: FUN_00acf2d4 @ 00acf2d4  size=1028  depth=1
   signature: undefined FUN_00acf2d4() ===== */

void FUN_00acf2d4(undefined8 param_1,undefined8 param_2,undefined8 *param_3,long param_4)

{
  undefined8 *puVar1;
  ulong uVar2;
  undefined1 uVar3;
  undefined1 uVar4;
  long lVar5;
  undefined8 extraout_x8;
  undefined8 uVar6;
  undefined8 extraout_x8_00;
  long extraout_x8_01;
  long extraout_x8_02;
  long extraout_x8_03;
  long extraout_x9;
  long extraout_x9_00;
  code *extraout_x9_01;
  long extraout_x9_02;
  long extraout_x9_03;
  long lVar7;
  long extraout_x10;
  ulong extraout_x10_00;
  ulong uVar8;
  long *unaff_x20;
  long unaff_x24;
  long lVar9;
  long lVar10;
  long lVar11;
  float fVar12;
  float fVar13;
  long lVar14;
  undefined1 auVar15 [16];
  undefined1 auVar16 [16];
  long local_1a0;
  long local_198;
  undefined8 uStack_190;
  undefined8 local_188;
  undefined8 local_180;
  undefined8 uStack_178;
  long local_170;
  undefined8 *local_168;
  long local_160;
  undefined1 local_158 [8];
  undefined1 auStack_150 [24];
  long local_138 [3];
  undefined8 local_120;
  long local_118;
  undefined8 uStack_110;
  long local_108;
  long local_100;
  long lStack_f8;
  long local_f0;
  long lStack_e8;
  
  FUN_00b95158();
  auVar15 = FUN_00bd3268();
  lVar14 = auVar15._0_8_;
  auVar16 = FUN_00bfcec4(*param_3);
  lVar9 = auVar16._0_8_;
  *(undefined8 *)(param_4 + 8) = extraout_x8;
  *(undefined4 *)(lVar9 + 0x20) = *(undefined4 *)(auVar16._8_8_ + 8);
  *(undefined4 *)(lVar9 + 0x1c) = *(undefined4 *)(auVar16._8_8_ + 0xc);
  if (*(int *)(lVar9 + 0x10) == 1) {
    FUN_00acf6d8();
  }
  else {
    FUN_00acf7ac();
  }
  lStack_f8 = 0;
  local_118 = lVar14 + 0x40;
  local_108 = CONCAT44(local_108._4_4_,0x1010000);
  local_120 = CONCAT44(local_120._4_4_,0x2010000);
  uStack_110 = 0;
  local_100 = auVar15._8_8_;
  auVar15 = FUN_00ba4228(*(undefined8 *)(lVar14 + 0x24));
  FUN_00b92824(auVar15._0_8_,auVar15._8_8_,local_158);
  FUN_00cc8734();
  FUN_00bc6fc8();
  FUN_00b97328();
  FUN_00c4c04c();
  FUN_00c04fe8();
  FUN_00acf880();
  FUN_00b9d140();
  lVar10 = (*(long *)(lVar14 + 0xa8) - *(long *)(lVar14 + 0xa0)) / 0x18;
  local_118 = 0;
  uStack_110 = 0;
  local_120 = 0;
  local_180 = 0;
  uStack_178 = 0;
  lVar5 = (*(long *)(lVar14 + 0xc0) - *(long *)(lVar14 + 0xb8)) / 0x18;
  local_188 = 0;
  FUN_007a5f28(local_138,lVar10);
  FUN_00ba8cf4();
  FUN_007a5f28();
  lVar9 = 0;
  uVar6 = local_120;
  for (lVar11 = lVar10; local_120 = uVar6, lVar11 != 0; lVar11 = lVar11 + -1) {
    FUN_00796dd8(&local_108,*(undefined8 *)(*(long *)(lVar14 + 0xd0) + 0x10),
                 *(long *)(lVar14 + 0xa0) + lVar9);
    FUN_00bfa064();
    FUN_007a5408();
    lVar9 = lVar9 + 0x18;
    uVar6 = local_120;
  }
  lVar9 = 0;
  while (lVar10 != lVar9) {
    *(undefined8 *)(local_138[0] + lVar9 * 8) = uVar6;
    FUN_00bd4038();
    uVar6 = extraout_x8_00;
    lVar9 = extraout_x9;
  }
  lVar9 = 0;
  for (lVar10 = lVar5; lVar10 != 0; lVar10 = lVar10 + -1) {
    FUN_00796dd8(&local_108,*(undefined8 *)(*(long *)(lVar14 + 0xd0) + 0x10),
                 *(long *)(lVar14 + 0xb8) + lVar9);
    FUN_007a5408(&local_188,&local_108);
    lVar9 = lVar9 + 0x18;
  }
  lVar9 = 0;
  while (lVar5 != lVar9) {
    FUN_00c00554();
    FUN_00bd4038();
    lVar9 = extraout_x9_00;
  }
  FUN_00b95980();
  FUN_00bf2fbc(*(undefined8 *)(lVar14 + 0xd8));
  FUN_00be13e4();
  (*extraout_x9_01)(&local_108);
  FUN_00793290(&local_1a0);
  uStack_190 = lStack_f8;
  lVar10 = local_100;
  lVar9 = local_108;
  local_100 = 0;
  lStack_f8 = 0;
  local_1a0 = local_108;
  local_198 = lVar10;
  local_108 = 0;
  FUN_003e8658(&local_108);
  uVar2 = (lVar10 - lVar9) / 0x14;
  uVar8 = (long)local_168 - local_170 >> 4;
  if (uVar8 < uVar2) {
    if ((ulong)(local_160 - (long)local_168 >> 4) < uVar2 - uVar8) {
      FUN_00bae348(&local_170);
      lVar11 = FUN_0078e7ec();
      local_f0 = 0;
      lVar5 = 0;
      lStack_e8 = extraout_x8_01 + 0x10;
      if (lVar11 != 0) {
        lVar5 = FUN_0078e820(lVar11);
      }
      local_100 = lVar5 + uVar8 * 0x10;
      local_f0 = lVar5 + lVar11 * 0x10;
      lVar11 = lVar5 + uVar2 * 0x10;
      lVar7 = uVar2 * 0x10 + uVar8 * -0x10;
      lStack_f8 = local_100;
      local_108 = lVar5;
      while (lVar7 != 0) {
        FUN_00bccb40();
        lVar11 = extraout_x9_02;
        lVar7 = extraout_x10;
      }
      lStack_f8 = lVar11;
      FUN_0078e974(&local_170,&local_108);
      FUN_004f9d18(&local_108);
    }
    else {
      puVar1 = local_168 + (uVar2 - uVar8) * 2;
      for (; lVar9 = local_1a0, lVar10 = local_198, puVar1 != local_168; local_168 = local_168 + 2)
      {
        *local_168 = 0;
        local_168[1] = 0;
      }
    }
  }
  else if (uVar2 < uVar8) {
    local_168 = (undefined8 *)(local_170 + uVar2 * 0x10);
  }
  FUN_00bf8fe4();
  fVar13 = *(float *)(lVar14 + 0x38);
  FUN_00b955ac(0,*(undefined4 *)(lVar14 + 0x34),fVar13,0x3f800000);
  fVar12 = (float)FUN_00bc6360();
  lVar14 = extraout_x8_02;
  lVar5 = extraout_x9_03;
  uVar8 = extraout_x10_00;
  while( true ) {
    uVar2 = (lVar10 - lVar9) / 0x14;
    uVar3 = uVar8 <= uVar2;
    uVar4 = uVar2 == uVar8;
    if (!(bool)uVar3 || (bool)uVar4) break;
    lVar11 = lVar9 + lVar14;
    uVar8 = uVar8 + 1;
    *(int *)(local_170 + lVar5 * 4) = (int)(fVar12 * *(float *)(lVar9 + lVar14));
    lVar14 = lVar14 + 0x14;
    *(int *)(local_170 + lVar5 * 4 + 4) = (int)(fVar13 * *(float *)(lVar11 + 4));
    *(int *)(local_170 + lVar5 * 4 + 8) = (int)(fVar12 * *(float *)(lVar11 + 8));
    *(int *)(local_170 + lVar5 * 4 + 0xc) = (int)(fVar13 * *(float *)(lVar11 + 0xc));
    *(undefined4 *)(*unaff_x20 + lVar5) = *(undefined4 *)(lVar11 + 0x10);
    lVar5 = lVar5 + 4;
  }
  FUN_003e8658(&local_1a0);
  FUN_003e8658(auStack_150);
  FUN_003e8658(local_138);
  FUN_003e8658(&local_188);
  FUN_003e8658(&local_120);
  FUN_00b8fa50();
  while (FUN_00ba83d0(local_170), !(bool)uVar3) {
    lVar14 = ((undefined8 *)(extraout_x8_03 + (long)unaff_x20))[1];
    FUN_00bb9dec(*(undefined8 *)(extraout_x8_03 + (long)unaff_x20));
    local_108 = FUN_00bccab4();
    local_100 = lVar14;
    FUN_00acf8c0();
    FUN_00b92d34();
  }
  FUN_003e8658(&local_170);
  FUN_00b8fc6c(*(undefined8 *)(unaff_x24 + 0x28));
  if (!(bool)uVar4) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  FUN_00b9c8dc();
  return;
}



/* ===== Function: FUN_00bed210 @ 00bed210  size=12  depth=1
   signature: undefined FUN_00bed210() ===== */

void FUN_00bed210(void)

{
  return;
}



/* ===== Function: FUN_00bec7dc @ 00bec7dc  size=16  depth=1
   signature: undefined FUN_00bec7dc() ===== */

undefined1  [16] FUN_00bec7dc(void)

{
  undefined1 (*in_x10) [16];
  
  return *in_x10;
}



/* ===== Function: FUN_00bf7ae0 @ 00bf7ae0  size=16  depth=1
   signature: undefined FUN_00bf7ae0() ===== */

void FUN_00bf7ae0(void)

{
  return;
}



/* ===== Function: FUN_00bdd418 @ 00bdd418  size=12  depth=1
   signature: undefined FUN_00bdd418() ===== */

void FUN_00bdd418(long param_1,undefined1 param_2 [16])

{
  undefined8 in_x9;
  
  *(undefined8 *)(param_1 + 0x40) = in_x9;
  *(long *)(param_1 + 0x38) = param_2._8_8_;
  *(long *)(param_1 + 0x30) = param_2._0_8_;
  FUN_00796100();
  return;
}



/* ===== Function: FUN_00c0019c @ 00c0019c  size=12  depth=1
   signature: undefined FUN_00c0019c() ===== */

void FUN_00c0019c(undefined4 *param_1)

{
  undefined4 *in_x9;
  
  *in_x9 = *param_1;
  return;
}



/* ===== Function: FUN_00bd0fb8 @ 00bd0fb8  size=12  depth=1
   signature: undefined FUN_00bd0fb8() ===== */

void FUN_00bd0fb8(void)

{
  return;
}



/* ===== Function: FUN_00b9f460 @ 00b9f460  size=16  depth=1
   signature: undefined FUN_00b9f460() ===== */

void FUN_00b9f460(void)

{
  return;
}



/* ===== Function: FUN_00b9c0cc @ 00b9c0cc  size=12  depth=1
   signature: undefined FUN_00b9c0cc() ===== */

void FUN_00b9c0cc(void)

{
  return;
}


