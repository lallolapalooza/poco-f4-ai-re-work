/*
 * Ghidra headless decompilation
 * program   : libdigit_ocr.so
 * executable: /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_paymentservice/inventory/libs/lib/arm64-v8a/libdigit_ocr.so
 * language  : AARCH64:LE:64:v8A
 * imagebase : 00100000
 * regex     : Java_|CDigitalOCR|CFEATURE_HOG|MainOCR|DigitSegment|svm_load_model|svm_predict|OCR_STR
 * max funcs : 64
 * note      : pseudocode from a (possibly stripped) binary is approximate;
 *             indirect calls and register-indirect JNI tables are lost.
 */

/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_payment_camera_XiaoMIDigitOCR_nativeBeginProcess
 * Address : 00114840
 * Size    : 0xb0 bytes
 * ------------------------------------------------------------------ */

undefined8
Java_com_xiaomi_payment_camera_XiaoMIDigitOCR_nativeBeginProcess
          (long *param_1,undefined8 param_2,MainOCR *param_3,undefined8 param_4,undefined8 param_5)

{
  char *pcVar1;
  char *pcVar2;
  
  pcVar1 = (char *)(**(code **)(*param_1 + 0x548))(param_1,param_4,0);
  pcVar2 = (char *)(**(code **)(*param_1 + 0x548))(param_1,param_5,0);
  MainOCR::beginProcess(param_3,pcVar1,pcVar2);
  (**(code **)(*param_1 + 0x550))(param_1,param_4,pcVar1);
  (**(code **)(*param_1 + 0x550))(param_1,param_5,pcVar2);
  return 1;
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_payment_camera_XiaoMIDigitOCR_nativeSetImage
 * Address : 001148f0
 * Size    : 0x58 bytes
 * ------------------------------------------------------------------ */

void Java_com_xiaomi_payment_camera_XiaoMIDigitOCR_nativeSetImage
               (long *param_1,undefined8 param_2,MainOCR *param_3,undefined8 param_4,int param_5,
               int param_6,int param_7)

{
  uchar *puVar1;
  
  puVar1 = (uchar *)(**(code **)(*param_1 + 0x730))(param_1,param_4);
  MainOCR::setImage(param_3,puVar1,param_5,param_6,param_7);
  return;
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomicode_digitocr_DigitOCR_nativeEndProcess
 * Address : 00114948
 * Size    : 0x34 bytes
 * ------------------------------------------------------------------ */

/* Java_com_xiaomicode_digitocr_DigitOCR_nativeEndProcess(_JNIEnv*, _jclass*, long long) */

void Java_com_xiaomicode_digitocr_DigitOCR_nativeEndProcess
               (_JNIEnv *param_1,_jclass *param_2,longlong param_3)

{
  MainOCR::endProcess();
  if (param_3 != 0) {
    MainOCR::~MainOCR((MainOCR *)param_3);
    operator_delete((void *)param_3);
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_payment_camera_XiaoMIDigitOCR_nativeRecogDigit
 * Address : 0011497c
 * Size    : 0x148 bytes
 * ------------------------------------------------------------------ */

uint Java_com_xiaomi_payment_camera_XiaoMIDigitOCR_nativeRecogDigit
               (long *param_1,undefined8 param_2,MainOCR *param_3,undefined8 param_4)

{
  uint uVar1;
  uint uVar2;
  uint uVar3;
  uint uVar4;
  undefined8 *puVar5;
  undefined8 *puVar6;
  ulong uVar7;
  undefined8 *puVar8;
  undefined8 *puVar9;
  long lVar10;
  undefined8 uVar11;
  
  MainOCR::recogdigitOCR(param_3);
  uVar3 = MainOCR::getDigitResultNum(param_3);
  puVar5 = (undefined8 *)MainOCR::getRecognitionResult(param_3);
  puVar6 = (undefined8 *)(**(code **)(*param_1 + 0x5d8))(param_1,param_4,0);
  if (0 < (int)uVar3) {
    if ((puVar6 < puVar5 + 2 && puVar5 < puVar6 + 2) || (uVar3 < 9)) {
      lVar10 = 0;
      do {
        *(undefined4 *)((long)puVar6 + lVar10 * 4) = *(undefined4 *)((long)puVar5 + lVar10 * 4);
        lVar10 = lVar10 + 1;
      } while ((int)lVar10 < (int)uVar3);
    }
    else {
      uVar1 = (uVar3 - 4 >> 2) + 1;
      uVar4 = 0;
      uVar2 = uVar1 * 4;
      puVar8 = puVar6;
      puVar9 = puVar5;
      do {
        uVar11 = *puVar9;
        uVar4 = uVar4 + 1;
        puVar8[1] = puVar9[1];
        *puVar8 = uVar11;
        puVar8 = puVar8 + 2;
        puVar9 = puVar9 + 2;
      } while (uVar4 < uVar1);
      if (uVar3 != uVar2) {
        uVar7 = -(ulong)((uVar1 & 0x3fffffff) >> 0x1d) & 0xfffffffc00000000 | (ulong)uVar2 << 2;
        *(undefined4 *)((long)puVar6 + uVar7) = *(undefined4 *)((long)puVar5 + uVar7);
        if (((int)(uVar2 + 1) < (int)uVar3) &&
           (*(undefined4 *)((long)puVar6 + uVar7 + 4) = *(undefined4 *)((long)puVar5 + uVar7 + 4),
           (int)(uVar2 + 2) < (int)uVar3)) {
          *(undefined4 *)((long)puVar6 + uVar7 + 8) = *(undefined4 *)((long)puVar5 + uVar7 + 8);
        }
      }
    }
  }
  (**(code **)(*param_1 + 0x618))(param_1,param_4,puVar6,0);
  return uVar3;
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_payment_camera_XiaoMIDigitOCR_nativegetDigitResultString
 * Address : 00114ac4
 * Size    : 0x84 bytes
 * ------------------------------------------------------------------ */

undefined8 Java_com_xiaomi_payment_camera_XiaoMIDigitOCR_nativegetDigitResultString(long *param_1)

{
  int iVar1;
  char cVar2;
  bool bVar3;
  undefined8 uVar4;
  int *piVar5;
  undefined8 local_8;
  
  MainOCR::getBestFrameResultStr();
  uVar4 = (**(code **)(*param_1 + 0x538))(param_1,local_8);
  if ((undefined *)(local_8 + -0x18) != PTR_DAT_001cae30) {
    piVar5 = (int *)(local_8 + -8);
    do {
      iVar1 = *piVar5;
      cVar2 = '\x01';
      bVar3 = (bool)ExclusiveMonitorPass(piVar5,0x10);
      if (bVar3) {
        *piVar5 = iVar1 + -1;
        cVar2 = ExclusiveMonitorsStatus();
      }
    } while (cVar2 != '\0');
    if (iVar1 < 1) {
      operator_delete((undefined *)(local_8 + -0x18));
    }
  }
  return uVar4;
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_payment_camera_XiaoMIDigitOCR_nativeCreateObject
 * Address : 00114b98
 * Size    : 0x5c bytes
 * ------------------------------------------------------------------ */

MainOCR * Java_com_xiaomi_payment_camera_XiaoMIDigitOCR_nativeCreateObject(void)

{
  MainOCR *this;
  
  __android_log_print(3,"libdigit_ocr","DigitOCR_nativeCreateObject begin");
  this = operator_new(0xe48);
  MainOCR::MainOCR(this);
  __android_log_print(3,"libdigit_ocr","DigitOCR_nativeCreateObject end");
  return this;
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_payment_camera_XiaoMIDigitOCR_nativegetmeanConfidence
 * Address : 00114c08
 * Size    : 0x8 bytes
 * ------------------------------------------------------------------ */

void Java_com_xiaomi_payment_camera_XiaoMIDigitOCR_nativegetmeanConfidence
               (undefined8 param_1,undefined8 param_2,MainOCR *param_3)

{
  MainOCR::getAllWordMinConf(param_3);
  return;
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_payment_camera_XiaoMIDigitOCR_nativegetwordConfidences
 * Address : 00114c10
 * Size    : 0x34 bytes
 * ------------------------------------------------------------------ */

void Java_com_xiaomi_payment_camera_XiaoMIDigitOCR_nativegetwordConfidences
               (long *param_1,undefined8 param_2,MainOCR *param_3)

{
  undefined4 uVar1;
  
  uVar1 = MainOCR::getDigitResultNum(param_3);
  (**(code **)(*param_1 + 0x598))(param_1,uVar1);
  return;
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_payment_camera_XiaoMIDigitOCR_nativegetDigitBoundingBoxes
 * Address : 00114c44
 * Size    : 0x2cc bytes
 * ------------------------------------------------------------------ */

undefined8
Java_com_xiaomi_payment_camera_XiaoMIDigitOCR_nativegetDigitBoundingBoxes
          (_jclass *param_1,undefined8 param_2,MainOCR *param_3)

{
  long lVar1;
  undefined8 ***pppuVar2;
  long *plVar3;
  void *pvVar4;
  undefined8 ***pppuVar5;
  undefined8 uVar6;
  _jmethodID *p_Var7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  int iVar14;
  long *plVar15;
  undefined8 **local_10;
  undefined8 **local_8;
  
  plVar3 = (long *)MainOCR::getDigitBoundingBoxes(param_3);
  plVar15 = (long *)*plVar3;
  local_10 = &local_10;
  local_8 = &local_10;
  if (plVar15 == plVar3) {
    iVar14 = 0;
  }
  else {
    do {
      pvVar4 = operator_new(0x28);
      lVar1 = plVar15[3];
      *(long *)((long)pvVar4 + 0x10) = plVar15[2];
      *(long *)((long)pvVar4 + 0x18) = lVar1;
      *(int *)((long)pvVar4 + 0x20) = (int)plVar15[4];
      FUN_00139e14(pvVar4,&local_10);
      plVar15 = (long *)*plVar15;
    } while (plVar3 != plVar15);
    iVar14 = 0;
    for (pppuVar5 = (undefined8 ***)local_10; pppuVar5 != &local_10;
        pppuVar5 = (undefined8 ***)*pppuVar5) {
      iVar14 = iVar14 + 1;
    }
  }
  __android_log_print(4,"libdigit_ocr","ocrdebug len=%d",iVar14);
  uVar6 = (**(code **)(*(long *)param_1 + 0x30))(param_1,"java/lang/Object");
  uVar6 = (**(code **)(*(long *)param_1 + 0x560))(param_1,iVar14,uVar6,0);
  p_Var7 = (_jmethodID *)(**(code **)(*(long *)param_1 + 0x30))(param_1,"android/graphics/Rect");
  uVar8 = (**(code **)(*(long *)param_1 + 0x2f0))(param_1,p_Var7,"right",&DAT_0018f830);
  uVar9 = (**(code **)(*(long *)param_1 + 0x2f0))(param_1,p_Var7,&DAT_0018f838,&DAT_0018f830);
  uVar10 = (**(code **)(*(long *)param_1 + 0x2f0))(param_1,p_Var7,&DAT_0018f840,&DAT_0018f830);
  uVar11 = (**(code **)(*(long *)param_1 + 0x2f0))(param_1,p_Var7,"bottom",&DAT_0018f830);
  uVar12 = (**(code **)(*(long *)param_1 + 0x108))(param_1,p_Var7,"<init>",&DAT_0018f858);
  if ((undefined8 ***)local_10 != &local_10) {
    iVar14 = 0;
    pppuVar5 = (undefined8 ***)local_10;
    do {
      uVar13 = _JNIEnv::NewObject(param_1,p_Var7,uVar12);
      (**(code **)(*(long *)param_1 + 0x360))(param_1,uVar13,uVar9,*(undefined2 *)(pppuVar5 + 2));
      (**(code **)(*(long *)param_1 + 0x360))(param_1,uVar13,uVar8,*(undefined2 *)(pppuVar5 + 3));
      (**(code **)(*(long *)param_1 + 0x360))
                (param_1,uVar13,uVar10,*(undefined2 *)((long)pppuVar5 + 0x14));
      (**(code **)(*(long *)param_1 + 0x360))
                (param_1,uVar13,uVar11,*(undefined2 *)((long)pppuVar5 + 0x1c));
      (**(code **)(*(long *)param_1 + 0x570))(param_1,uVar6,iVar14,uVar13);
      pppuVar5 = (undefined8 ***)*pppuVar5;
      iVar14 = iVar14 + 1;
      pppuVar2 = (undefined8 ***)local_10;
    } while (pppuVar5 != &local_10);
    while (pppuVar2 != &local_10) {
      pppuVar5 = (undefined8 ***)*pppuVar2;
      operator_delete(pppuVar2);
      pppuVar2 = pppuVar5;
    }
  }
  return uVar6;
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_payment_camera_XiaoMIDigitOCR_nativeSetCameraMode
 * Address : 00114f6c
 * Size    : 0xc bytes
 * ------------------------------------------------------------------ */

void Java_com_xiaomi_payment_camera_XiaoMIDigitOCR_nativeSetCameraMode
               (undefined8 param_1,undefined8 param_2,MainOCR *param_3,int param_4)

{
  MainOCR::setCameraMode(param_3,param_4);
  return;
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_payment_camera_XiaoMIDigitOCR_nativeclearResultQueue
 * Address : 00114f78
 * Size    : 0x8 bytes
 * ------------------------------------------------------------------ */

void Java_com_xiaomi_payment_camera_XiaoMIDigitOCR_nativeclearResultQueue
               (undefined8 param_1,undefined8 param_2,MainOCR *param_3)

{
  MainOCR::clearResultQueue(param_3);
  return;
}



/* ------------------------------------------------------------------
 * Function: Java_com_xiaomi_payment_camera_XiaoMIDigitOCR_nativesetDigitRange
 * Address : 00114f80
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void Java_com_xiaomi_payment_camera_XiaoMIDigitOCR_nativesetDigitRange
               (undefined8 param_1,undefined8 param_2,MainOCR *param_3,int param_4,int param_5)

{
  MainOCR::setDigitNumberRange(param_3,param_4,param_5);
  return;
}



/* ------------------------------------------------------------------
 * Function: CFEATURE_HOG::ExtractGradientOrientation
 * Address : 00114fd8
 * Size    : 0x478 bytes
 * ------------------------------------------------------------------ */

/* CFEATURE_HOG::ExtractGradientOrientation(unsigned char*, int, int, std::vector<float,
   std::allocator<float> >&) */

undefined8 __thiscall
CFEATURE_HOG::ExtractGradientOrientation
          (CFEATURE_HOG *this,uchar *param_1,int param_2,int param_3,vector *param_4)

{
  uchar *puVar1;
  uint uVar2;
  byte bVar3;
  byte bVar4;
  int iVar5;
  int iVar6;
  int iVar7;
  double dVar8;
  double dVar9;
  float fVar10;
  int *__s;
  void *__s_00;
  undefined8 *__s_01;
  uchar *puVar11;
  undefined8 uVar12;
  void *__dest;
  undefined8 *puVar13;
  ulong uVar14;
  void *pvVar15;
  size_t __n;
  float *__src;
  int *piVar16;
  long lVar17;
  size_t sVar18;
  int iVar19;
  ulong uVar20;
  long lVar21;
  ulong uVar22;
  byte bVar23;
  byte bVar24;
  byte bVar25;
  byte bVar26;
  byte bVar27;
  byte bVar28;
  byte bVar29;
  byte bVar30;
  byte bVar31;
  byte bVar32;
  float fVar33;
  byte bVar35;
  double dVar34;
  undefined8 uVar36;
  byte bVar37;
  undefined8 uVar38;
  undefined8 uVar39;
  float fVar40;
  
  if ((param_2 == 0x14) && (param_3 == 0x20)) {
    __s = malloc(0x240);
    __s_00 = malloc(0xa00);
    __s_01 = malloc(0xa00);
    memset(__s_00,0,0xa00);
    memset(__s_01,0,0xa00);
    memset(__s,0,0x240);
    puVar11 = param_1;
    do {
      uVar36 = *(undefined8 *)(puVar11 + 8);
      uVar12 = *(undefined8 *)puVar11;
      bVar35 = -(0x50 < (byte)((ulong)uVar12 >> 0x38));
      bVar37 = -(0x50 < (byte)((ulong)uVar36 >> 0x38));
      puVar11[0x10] = -(0x50 < puVar11[0x10]);
      uVar14 = (ulong)CONCAT15(bVar35,CONCAT14(-(0x50 < (byte)((ulong)uVar12 >> 0x30)),
                                               CONCAT13(-(0x50 < (byte)((ulong)uVar12 >> 0x28)),
                                                        CONCAT12(-(0x50 < (byte)((ulong)uVar12 >>
                                                                                0x20)),
                                                                 CONCAT11(-(0x50 < (byte)((ulong)
                                                  uVar12 >> 0x18)),
                                                  -(0x50 < (byte)((ulong)uVar12 >> 0x10))))))) &
               0x10101010101;
      uVar20 = (ulong)CONCAT15(bVar37,CONCAT14(-(0x50 < (byte)((ulong)uVar36 >> 0x30)),
                                               CONCAT13(-(0x50 < (byte)((ulong)uVar36 >> 0x28)),
                                                        CONCAT12(-(0x50 < (byte)((ulong)uVar36 >>
                                                                                0x20)),
                                                                 CONCAT11(-(0x50 < (byte)((ulong)
                                                  uVar36 >> 0x18)),
                                                  -(0x50 < (byte)((ulong)uVar36 >> 0x10))))))) &
               0x10101010101;
      puVar11[0x11] = -(0x50 < puVar11[0x11]);
      puVar11[0x12] = -(0x50 < puVar11[0x12]);
      puVar11[0x13] = -(0x50 < puVar11[0x13]);
      *(ulong *)(puVar11 + 8) =
           CONCAT17(-(bVar37 & 1),
                    CONCAT16(-(char)(uVar20 >> 0x20),
                             CONCAT15(-(char)(uVar20 >> 0x18),
                                      CONCAT14(-(char)(uVar20 >> 0x10),
                                               CONCAT13(-(char)(uVar20 >> 8),
                                                        CONCAT12(-(char)uVar20,
                                                                 CONCAT11(-(-(0x50 < (byte)((ulong)
                                                  uVar36 >> 8)) & 1U),-(-(0x50 < (byte)uVar36) & 1U)
                                                  )))))));
      *(ulong *)puVar11 =
           CONCAT17(-(bVar35 & 1),
                    CONCAT16(-(char)(uVar14 >> 0x20),
                             CONCAT15(-(char)(uVar14 >> 0x18),
                                      CONCAT14(-(char)(uVar14 >> 0x10),
                                               CONCAT13(-(char)(uVar14 >> 8),
                                                        CONCAT12(-(char)uVar14,
                                                                 CONCAT11(-(-(0x50 < (byte)((ulong)
                                                  uVar12 >> 8)) & 1U),-(-(0x50 < (byte)uVar12) & 1U)
                                                  )))))));
      puVar11 = puVar11 + 0x14;
    } while (puVar11 != param_1 + 0x280);
    puVar11 = param_1 + 1;
    puVar13 = (undefined8 *)((long)__s_00 + 4);
    do {
      uVar36 = *(undefined8 *)(puVar11 + 8);
      uVar12 = *(undefined8 *)puVar11;
      uVar39 = *(undefined8 *)(puVar11 + 7);
      uVar38 = *(undefined8 *)(puVar11 + -1);
      puVar1 = puVar11 + 0x14;
      bVar35 = puVar11[0x10];
      bVar27 = (byte)((ulong)uVar12 >> 0x28);
      bVar23 = (byte)((ulong)uVar36 >> 8);
      bVar28 = (byte)((ulong)uVar36 >> 0x28);
      bVar37 = puVar11[0x11];
      bVar25 = (byte)((ulong)uVar38 >> 0x28);
      bVar3 = puVar11[0xf];
      bVar24 = (byte)((ulong)uVar39 >> 8);
      bVar26 = (byte)((ulong)uVar39 >> 0x28);
      bVar4 = puVar11[0x12];
      iVar19 = (uint)bVar27 - (uint)bVar25;
      iVar5 = (uint)(byte)((ulong)uVar12 >> 0x38) - (uint)(byte)((ulong)uVar38 >> 0x38);
      iVar6 = (uint)bVar23 - (uint)bVar24;
      iVar7 = (uint)(byte)((ulong)uVar36 >> 0x18) - (uint)(byte)((ulong)uVar39 >> 0x18);
      puVar13[1] = CONCAT44((uint)(byte)((ulong)uVar12 >> 0x18) -
                            (uint)(byte)((ulong)uVar38 >> 0x18),
                            (uint)(byte)((ulong)uVar12 >> 0x10) -
                            (uint)(byte)((ulong)uVar38 >> 0x10));
      *puVar13 = CONCAT44((uint)(byte)((ulong)uVar12 >> 8) - (uint)(byte)((ulong)uVar38 >> 8),
                          (uint)(byte)uVar12 - (uint)(byte)uVar38);
      puVar13[3] = CONCAT17((char)((uint)iVar5 >> 0x18),
                            CONCAT16((char)((uint)iVar5 >> 0x10),
                                     CONCAT15((char)((uint)iVar5 >> 8),
                                              CONCAT14((char)iVar5,
                                                       (uint)(byte)((ulong)uVar12 >> 0x30) -
                                                       (uint)(byte)((ulong)uVar38 >> 0x30)))));
      puVar13[2] = CONCAT17((char)((uint)iVar19 >> 0x18),
                            CONCAT16((char)((uint)iVar19 >> 0x10),
                                     CONCAT15((char)((uint)iVar19 >> 8),
                                              CONCAT14((char)iVar19,
                                                       (CONCAT12(bVar27,(ushort)(byte)((ulong)uVar12
                                                                                      >> 0x20)) &
                                                       0xffff) - (CONCAT12(bVar25,(ushort)(byte)((
                                                  ulong)uVar38 >> 0x20)) & 0xffff)))));
      puVar13[5] = CONCAT26((short)((uint)iVar7 >> 0x10),
                            CONCAT24((short)iVar7,
                                     (uint)(byte)((ulong)uVar36 >> 0x10) -
                                     (uint)(byte)((ulong)uVar39 >> 0x10)));
      puVar13[4] = CONCAT26((short)((uint)iVar6 >> 0x10),
                            CONCAT24((short)iVar6,
                                     ((CONCAT12(bVar23,(short)uVar36) & 0xff00ff) & 0xffff) -
                                     ((CONCAT12(bVar24,(short)uVar39) & 0xff00ff) & 0xffff)));
      puVar13[7] = CONCAT44((uint)(byte)((ulong)uVar36 >> 0x38) -
                            (uint)(byte)((ulong)uVar39 >> 0x38),
                            (uint)(byte)((ulong)uVar36 >> 0x30) -
                            (uint)(byte)((ulong)uVar39 >> 0x30));
      puVar13[6] = CONCAT44((uint)bVar28 - (uint)bVar26,
                            (CONCAT12(bVar28,(ushort)(byte)((ulong)uVar36 >> 0x20)) & 0xffff) -
                            (CONCAT12(bVar26,(ushort)(byte)((ulong)uVar39 >> 0x20)) & 0xffff));
      *(uint *)(puVar13 + 8) = (uint)bVar35 - (uint)bVar3;
      *(uint *)((long)puVar13 + 0x44) = (uint)bVar37 - (uint)bVar35;
      *(uint *)(puVar13 + 9) = (uint)bVar4 - (uint)bVar37;
      puVar11 = puVar1;
      puVar13 = puVar13 + 10;
    } while (puVar1 != param_1 + 0x281);
    puVar11 = param_1 + 0x14;
    puVar13 = __s_01;
    do {
      uVar36 = *(undefined8 *)(puVar11 + 8);
      uVar12 = *(undefined8 *)puVar11;
      bVar35 = puVar11[0x10];
      uVar39 = *(undefined8 *)(puVar11 + -0xc);
      uVar38 = *(undefined8 *)(puVar11 + -0x14);
      puVar1 = puVar11 + 0x14;
      bVar29 = (byte)((ulong)uVar12 >> 0x28);
      bVar37 = puVar11[-4];
      bVar25 = (byte)((ulong)uVar36 >> 8);
      bVar30 = (byte)((ulong)uVar36 >> 0x28);
      bVar3 = puVar11[0x11];
      bVar31 = (byte)((ulong)uVar38 >> 0x28);
      bVar26 = (byte)((ulong)uVar39 >> 8);
      bVar32 = (byte)((ulong)uVar39 >> 0x28);
      bVar4 = puVar11[-3];
      bVar23 = puVar11[0x12];
      bVar24 = puVar11[-2];
      bVar27 = puVar11[0x13];
      iVar19 = (uint)bVar29 - (uint)bVar31;
      iVar5 = (uint)(byte)((ulong)uVar12 >> 0x38) - (uint)(byte)((ulong)uVar38 >> 0x38);
      bVar28 = puVar11[-1];
      iVar6 = (uint)bVar25 - (uint)bVar26;
      iVar7 = (uint)(byte)((ulong)uVar36 >> 0x18) - (uint)(byte)((ulong)uVar39 >> 0x18);
      puVar13[0xb] = CONCAT44((uint)(byte)((ulong)uVar12 >> 0x18) -
                              (uint)(byte)((ulong)uVar38 >> 0x18),
                              (uint)(byte)((ulong)uVar12 >> 0x10) -
                              (uint)(byte)((ulong)uVar38 >> 0x10));
      puVar13[10] = CONCAT44((uint)(byte)((ulong)uVar12 >> 8) - (uint)(byte)((ulong)uVar38 >> 8),
                             (uint)(byte)uVar12 - (uint)(byte)uVar38);
      puVar13[0xd] = CONCAT17((char)((uint)iVar5 >> 0x18),
                              CONCAT16((char)((uint)iVar5 >> 0x10),
                                       CONCAT15((char)((uint)iVar5 >> 8),
                                                CONCAT14((char)iVar5,
                                                         (uint)(byte)((ulong)uVar12 >> 0x30) -
                                                         (uint)(byte)((ulong)uVar38 >> 0x30)))));
      puVar13[0xc] = CONCAT17((char)((uint)iVar19 >> 0x18),
                              CONCAT16((char)((uint)iVar19 >> 0x10),
                                       CONCAT15((char)((uint)iVar19 >> 8),
                                                CONCAT14((char)iVar19,
                                                         (CONCAT12(bVar29,(ushort)(byte)((ulong)
                                                  uVar12 >> 0x20)) & 0xffff) -
                                                  (CONCAT12(bVar31,(ushort)(byte)((ulong)uVar38 >>
                                                                                 0x20)) & 0xffff))))
                             );
      puVar13[0xf] = CONCAT26((short)((uint)iVar7 >> 0x10),
                              CONCAT24((short)iVar7,
                                       (uint)(byte)((ulong)uVar36 >> 0x10) -
                                       (uint)(byte)((ulong)uVar39 >> 0x10)));
      puVar13[0xe] = CONCAT26((short)((uint)iVar6 >> 0x10),
                              CONCAT24((short)iVar6,
                                       ((CONCAT12(bVar25,(short)uVar36) & 0xff00ff) & 0xffff) -
                                       ((CONCAT12(bVar26,(short)uVar39) & 0xff00ff) & 0xffff)));
      puVar13[0x11] =
           CONCAT44((uint)(byte)((ulong)uVar36 >> 0x38) - (uint)(byte)((ulong)uVar39 >> 0x38),
                    (uint)(byte)((ulong)uVar36 >> 0x30) - (uint)(byte)((ulong)uVar39 >> 0x30));
      puVar13[0x10] =
           CONCAT44((uint)bVar30 - (uint)bVar32,
                    (CONCAT12(bVar30,(ushort)(byte)((ulong)uVar36 >> 0x20)) & 0xffff) -
                    (CONCAT12(bVar32,(ushort)(byte)((ulong)uVar39 >> 0x20)) & 0xffff));
      *(uint *)(puVar13 + 0x12) = (uint)bVar35 - (uint)bVar37;
      *(uint *)((long)puVar13 + 0x94) = (uint)bVar3 - (uint)bVar4;
      *(uint *)(puVar13 + 0x13) = (uint)bVar23 - (uint)bVar24;
      *(uint *)((long)puVar13 + 0x9c) = (uint)bVar27 - (uint)bVar28;
      fVar10 = DAT_00115464;
      fVar40 = DAT_00115460;
      dVar9 = DAT_00115458;
      dVar8 = DAT_00115450;
      puVar11 = puVar1;
      puVar13 = puVar13 + 10;
    } while (puVar1 != param_1 + 0x280);
    lVar17 = 0;
    iVar19 = 0;
    do {
      lVar21 = 0;
      do {
        iVar5 = (int)lVar21;
        dVar34 = atan2((double)*(int *)((long)__s_01 + lVar21 * 4 + lVar17),
                       (double)*(int *)((long)__s_00 + lVar21 * 4 + lVar17));
        lVar21 = lVar21 + 1;
        fVar33 = (float)(dVar9 + dVar8 * dVar34);
        if (fVar40 <= fVar33) {
          fVar33 = fVar33 - fVar40;
        }
        uVar2 = (iVar5 / 5 + (iVar19 >> 3) * 4) * 9 + (int)(fVar33 / fVar10);
        uVar14 = -(ulong)(uVar2 >> 0x1f) & 0xfffffffc00000000 | (ulong)uVar2 << 2;
        *(int *)((long)__s + uVar14) = *(int *)((long)__s + uVar14) + 1;
      } while (lVar21 != 0x14);
      iVar19 = iVar19 + 1;
      lVar17 = lVar17 + 0x50;
    } while (iVar19 != 0x20);
    piVar16 = __s;
    do {
      while( true ) {
        __src = *(float **)(param_4 + 8);
        fVar40 = (float)*piVar16 / fVar10;
        if (__src != *(float **)(param_4 + 0x10)) break;
        pvVar15 = *(void **)param_4;
        sVar18 = (long)__src - (long)pvVar15;
        uVar14 = (long)sVar18 >> 2;
        if (uVar14 == 0) {
          uVar20 = 4;
LAB_001153e8:
          __dest = operator_new(uVar20);
          pvVar15 = *(void **)param_4;
          __n = (long)__src - (long)pvVar15;
          uVar14 = (long)__n >> 2;
        }
        else {
          uVar22 = uVar14 * 2;
          uVar20 = 0xfffffffffffffffc;
          if ((uVar22 < uVar14) || (0x3fffffffffffffff < uVar22)) goto LAB_001153e8;
          uVar20 = uVar14 << 3;
          __dest = (void *)0x0;
          __n = sVar18;
          if (uVar22 != 0) goto LAB_001153e8;
        }
        if ((long)__dest + sVar18 != 0) {
          *(float *)((long)__dest + sVar18) = fVar40;
        }
        if (uVar14 == 0) {
          sVar18 = *(long *)(param_4 + 8) - (long)__src;
        }
        else {
          memmove(__dest,pvVar15,__n);
          sVar18 = *(long *)(param_4 + 8) - (long)__src;
        }
        pvVar15 = (void *)((long)__dest + __n + 4);
        if ((long)sVar18 >> 2 != 0) {
          pvVar15 = memmove(pvVar15,__src,sVar18);
        }
        if (*(void **)param_4 != (void *)0x0) {
          operator_delete(*(void **)param_4);
        }
        piVar16 = piVar16 + 1;
        *(void **)param_4 = __dest;
        *(size_t *)(param_4 + 8) = (long)pvVar15 + sVar18;
        *(ulong *)(param_4 + 0x10) = (long)__dest + uVar20;
        if (piVar16 == __s + 0x90) goto LAB_0011539c;
      }
      if (__src != (float *)0x0) {
        *__src = fVar40;
      }
      piVar16 = piVar16 + 1;
      *(float **)(param_4 + 8) = __src + 1;
    } while (piVar16 != __s + 0x90);
LAB_0011539c:
    free(__s_00);
    free(__s_01);
    free(__s);
    uVar12 = 0;
  }
  else {
    uVar12 = 0xffffffff;
  }
  return uVar12;
}



/* ------------------------------------------------------------------
 * Function: CDigitalOCR::LoadModel
 * Address : 00115468
 * Size    : 0x2c bytes
 * ------------------------------------------------------------------ */

/* CDigitalOCR::LoadModel(std::string) */

int __thiscall CDigitalOCR::LoadModel(CDigitalOCR *this,undefined8 *param_2)

{
  long lVar1;
  
  lVar1 = svm_load_model(*param_2);
  *(long *)this = lVar1;
  return -(uint)(lVar1 == 0);
}



/* ------------------------------------------------------------------
 * Function: CDigitalOCR::LoadModel
 * Address : 00115494
 * Size    : 0x4c bytes
 * ------------------------------------------------------------------ */

/* CDigitalOCR::LoadModel(std::string, std::string) */

int __thiscall CDigitalOCR::LoadModel(CDigitalOCR *this,undefined8 *param_2,undefined8 *param_3)

{
  int iVar1;
  long lVar2;
  
  lVar2 = svm_load_cvt_model(*param_2);
  *(long *)this = lVar2;
  if (lVar2 == 0) {
    iVar1 = -1;
  }
  else {
    lVar2 = svm_load_cvt_model(*param_3);
    *(long *)(this + 8) = lVar2;
    iVar1 = -(uint)(lVar2 == 0);
  }
  return iVar1;
}



/* ------------------------------------------------------------------
 * Function: CDigitalOCR::PredictNegPos
 * Address : 001154e0
 * Size    : 0x114 bytes
 * ------------------------------------------------------------------ */

/* CDigitalOCR::PredictNegPos(unsigned char*, int, int, int&) */

undefined8 __thiscall
CDigitalOCR::PredictNegPos(CDigitalOCR *this,uchar *param_1,int param_2,int param_3,int *param_4)

{
  long lVar1;
  long lVar2;
  double *pdVar3;
  long lVar4;
  undefined1 auVar5 [16];
  CFEATURE_HOG aCStack_980 [8];
  void *local_978;
  long local_970;
  undefined8 local_968;
  double local_960;
  undefined8 uStack_958;
  double local_950;
  undefined8 uStack_948;
  double local_940;
  undefined8 uStack_938;
  double local_930;
  undefined8 uStack_928;
  double local_920;
  undefined8 uStack_918;
  undefined4 auStack_910 [2];
  double dStack_908;
  undefined4 local_900 [572];
  undefined4 local_10;
  
  local_978 = (void *)0x0;
  local_970 = 0;
  local_968 = 0;
  CFEATURE_HOG::ExtractGradientOrientation(aCStack_980,param_1,param_2,param_3,(vector *)&local_978)
  ;
  memset(auStack_910,0,0x910);
  lVar4 = local_970 - (long)local_978 >> 2;
  if (lVar4 != 0) {
    lVar2 = 0;
    auStack_910[0] = 1;
    pdVar3 = &dStack_908;
    while( true ) {
      lVar1 = lVar2 * 4;
      lVar2 = lVar2 + 1;
      *pdVar3 = (double)*(float *)((long)local_978 + lVar1);
      if (lVar4 == lVar2) break;
      *(int *)(pdVar3 + 1) = (int)lVar2 + 1;
      pdVar3 = pdVar3 + 2;
    }
  }
  auVar5 = NEON_fmov(0xbff0000000000000,8);
  uStack_958 = auVar5._8_8_;
  local_960 = auVar5._0_8_;
  local_10 = 0xffffffff;
  local_950 = local_960;
  uStack_948 = uStack_958;
  local_940 = local_960;
  uStack_938 = uStack_958;
  local_930 = local_960;
  uStack_928 = uStack_958;
  local_920 = local_960;
  uStack_918 = uStack_958;
  svm_predict_probability(*(undefined8 *)(this + 8),auStack_910,&local_960);
  *param_4 = (uint)(0.5 < local_960);
  if (local_978 != (void *)0x0) {
    operator_delete(local_978);
  }
  return 0;
}



/* ------------------------------------------------------------------
 * Function: std::vector<OCR_STR,std::allocator<OCR_STR>>::_M_insert_aux
 * Address : 00115610
 * Size    : 0x170 bytes
 * ------------------------------------------------------------------ */

/* std::vector<OCR_STR, std::allocator<OCR_STR>
   >::_M_insert_aux(__gnu_cxx::__normal_iterator<OCR_STR*, std::vector<OCR_STR,
   std::allocator<OCR_STR> > >, OCR_STR const&) */

void __thiscall
std::vector<OCR_STR,std::allocator<OCR_STR>>::_M_insert_aux
          (vector<OCR_STR,std::allocator<OCR_STR>> *this,undefined4 *param_2,undefined8 *param_3)

{
  undefined4 uVar1;
  undefined4 uVar2;
  ulong uVar3;
  void *__dest;
  void *pvVar4;
  undefined8 *puVar5;
  size_t sVar6;
  size_t __n;
  ulong uVar7;
  
  puVar5 = *(undefined8 **)(this + 8);
  if (puVar5 != *(undefined8 **)(this + 0x10)) {
    if (puVar5 != (undefined8 *)0x0) {
      *puVar5 = puVar5[-1];
    }
    sVar6 = (long)puVar5 + (-8 - (long)param_2);
    *(undefined8 **)(this + 8) = puVar5 + 1;
    uVar1 = *(undefined4 *)param_3;
    uVar2 = *(undefined4 *)((long)param_3 + 4);
    if ((long)sVar6 >> 3 != 0) {
      memmove((void *)((long)(puVar5 + 1) + (-8 - sVar6)),param_2,sVar6);
    }
    *param_2 = uVar1;
    param_2[1] = uVar2;
    return;
  }
  pvVar4 = *(void **)this;
  uVar3 = (long)puVar5 - (long)pvVar4 >> 3;
  if (uVar3 == 0) {
    sVar6 = (long)param_2 - (long)pvVar4;
    uVar3 = 8;
  }
  else {
    uVar7 = uVar3 * 2;
    if ((uVar7 < uVar3) || (0x1fffffffffffffff < uVar7)) {
      sVar6 = (long)param_2 - (long)pvVar4;
      uVar3 = 0xfffffffffffffff8;
    }
    else {
      sVar6 = (long)param_2 - (long)pvVar4;
      uVar3 = uVar3 << 4;
      __dest = (void *)0x0;
      __n = sVar6;
      if (uVar7 == 0) goto LAB_001156dc;
    }
  }
  __dest = operator_new(uVar3);
  pvVar4 = *(void **)this;
  __n = (long)param_2 - (long)pvVar4;
LAB_001156dc:
  if ((long)__dest + sVar6 != 0) {
    *(undefined8 *)((long)__dest + sVar6) = *param_3;
  }
  if ((long)__n >> 3 != 0) {
    memmove(__dest,pvVar4,__n);
  }
  pvVar4 = (void *)((long)__dest + __n + 8);
  sVar6 = *(long *)(this + 8) - (long)param_2;
  if ((long)sVar6 >> 3 != 0) {
    memmove(pvVar4,param_2,sVar6);
  }
  if (*(void **)this != (void *)0x0) {
    operator_delete(*(void **)this);
  }
  *(void **)this = __dest;
  *(size_t *)(this + 8) = (long)pvVar4 + sVar6;
  *(ulong *)(this + 0x10) = (long)__dest + uVar3;
  return;
}



/* ------------------------------------------------------------------
 * Function: CDigitalOCR::Predict
 * Address : 00115780
 * Size    : 0x598 bytes
 * ------------------------------------------------------------------ */

/* CDigitalOCR::Predict(unsigned char*, int, int, std::vector<OCR_STR, std::allocator<OCR_STR> >&)
    */

undefined8 __thiscall
CDigitalOCR::Predict(CDigitalOCR *this,uchar *param_1,int param_2,int param_3,vector *param_4)

{
  long lVar1;
  float fVar2;
  bool bVar3;
  undefined8 *puVar4;
  long lVar5;
  double *pdVar6;
  long lVar7;
  uint uVar8;
  uint uVar9;
  float fVar10;
  undefined1 auVar11 [16];
  double dVar12;
  float fVar13;
  float fVar14;
  CFEATURE_HOG aCStack_988 [8];
  uint local_980;
  int iStack_97c;
  void *local_978;
  long local_970;
  undefined8 local_968;
  double local_960;
  double local_958;
  double local_950;
  double dStack_948;
  double local_940;
  double dStack_938;
  double local_930;
  double dStack_928;
  double local_920;
  double dStack_918;
  undefined4 auStack_910 [2];
  double dStack_908;
  undefined4 local_900 [572];
  undefined4 local_10;
  
  local_978 = (void *)0x0;
  local_970 = 0;
  local_968 = 0;
  CFEATURE_HOG::ExtractGradientOrientation(aCStack_988,param_1,param_2,param_3,(vector *)&local_978)
  ;
  memset(auStack_910,0,0x910);
  lVar7 = local_970 - (long)local_978 >> 2;
  if (lVar7 != 0) {
    lVar5 = 0;
    auStack_910[0] = 1;
    pdVar6 = &dStack_908;
    while( true ) {
      lVar1 = lVar5 * 4;
      lVar5 = lVar5 + 1;
      *pdVar6 = (double)*(float *)((long)local_978 + lVar1);
      if (lVar7 == lVar5) break;
      *(int *)(pdVar6 + 1) = (int)lVar5 + 1;
      pdVar6 = pdVar6 + 2;
    }
  }
  auVar11 = NEON_fmov(0xbff0000000000000,8);
  local_958 = auVar11._8_8_;
  local_960 = auVar11._0_8_;
  local_10 = 0xffffffff;
  local_950 = local_960;
  dStack_948 = local_958;
  local_940 = local_960;
  dStack_938 = local_958;
  local_930 = local_960;
  dStack_928 = local_958;
  local_920 = local_960;
  dStack_918 = local_958;
  svm_predict_probability(*(undefined8 *)this,auStack_910,&local_960);
  fVar2 = DAT_00115d34;
  fVar10 = 0.0;
  if (0.0 < local_960) {
    fVar10 = (float)local_960;
  }
  dVar12 = (double)fVar10;
  bVar3 = dVar12 < local_958;
  if (bVar3) {
    fVar10 = (float)local_958;
    dVar12 = (double)fVar10;
  }
  local_980 = (uint)bVar3;
  if (dVar12 < local_950) {
    fVar10 = (float)local_950;
    local_980 = 2;
    dVar12 = (double)fVar10;
  }
  if (dVar12 < dStack_948) {
    fVar10 = (float)dStack_948;
    local_980 = 3;
    dVar12 = (double)fVar10;
  }
  if (dVar12 < local_940) {
    fVar10 = (float)local_940;
    local_980 = 4;
    dVar12 = (double)fVar10;
  }
  if (dVar12 < dStack_938) {
    fVar10 = (float)dStack_938;
    local_980 = 5;
    dVar12 = (double)fVar10;
  }
  if (dVar12 < local_930) {
    fVar10 = (float)local_930;
    local_980 = 6;
    dVar12 = (double)fVar10;
  }
  if (dVar12 < dStack_928) {
    fVar10 = (float)dStack_928;
    local_980 = 7;
    dVar12 = (double)fVar10;
  }
  if (local_920 <= dVar12) {
    if (dVar12 < dStack_918) goto LAB_00115948;
    if (local_980 != 0) goto LAB_00115964;
    fVar14 = 0.0;
LAB_0011597c:
    dVar12 = (double)fVar14;
    bVar3 = dVar12 < local_958;
    if (bVar3) {
      fVar14 = (float)local_958;
      dVar12 = (double)fVar14;
    }
    uVar8 = (uint)bVar3;
    if (local_980 != 2) goto LAB_001159a0;
LAB_001159bc:
    if (dVar12 < dStack_948) {
      fVar14 = (float)dStack_948;
      uVar8 = 3;
      dVar12 = (double)fVar14;
    }
    if (local_980 != 4) goto LAB_001159d8;
LAB_001159f4:
    if (dVar12 < dStack_938) {
      fVar14 = (float)dStack_938;
      uVar8 = 5;
      dVar12 = (double)fVar14;
    }
    if (local_980 != 6) goto LAB_00115a10;
LAB_00115a2c:
    if (dVar12 < dStack_928) {
      fVar14 = (float)dStack_928;
      uVar8 = 7;
      dVar12 = (double)fVar14;
    }
    if (local_980 != 8) goto LAB_00115a48;
  }
  else {
    fVar10 = (float)local_920;
    local_980 = 8;
    if ((double)fVar10 < dStack_918) {
LAB_00115948:
      fVar10 = (float)dStack_918;
      local_980 = 9;
    }
LAB_00115964:
    fVar14 = 0.0;
    if (0.0 < local_960) {
      fVar14 = (float)local_960;
    }
    if (local_980 != 1) goto LAB_0011597c;
    dVar12 = (double)fVar14;
    uVar8 = 0;
LAB_001159a0:
    if (dVar12 < local_950) {
      fVar14 = (float)local_950;
      uVar8 = 2;
      dVar12 = (double)fVar14;
    }
    if (local_980 != 3) goto LAB_001159bc;
LAB_001159d8:
    if (dVar12 < local_940) {
      fVar14 = (float)local_940;
      uVar8 = 4;
      dVar12 = (double)fVar14;
    }
    if (local_980 != 5) goto LAB_001159f4;
LAB_00115a10:
    if (dVar12 < local_930) {
      fVar14 = (float)local_930;
      uVar8 = 6;
      dVar12 = (double)fVar14;
    }
    if (local_980 != 7) goto LAB_00115a2c;
LAB_00115a48:
    if (dVar12 < local_920) {
      fVar14 = (float)local_920;
      uVar8 = 8;
    }
    if (local_980 == 9) goto LAB_00115a74;
    dVar12 = (double)fVar14;
  }
  if (dVar12 < dStack_918) {
    fVar14 = (float)dStack_918;
    uVar8 = 9;
  }
LAB_00115a74:
  if (((uVar8 == 0) || (local_980 == 0)) || (local_960 <= 0.0)) {
    fVar13 = 0.0;
  }
  else {
    fVar13 = (float)local_960;
  }
  if ((uVar8 == 1) || (local_980 == 1)) {
    uVar9 = 0;
  }
  else {
    uVar9 = 0;
    if ((double)fVar13 < local_958) {
      fVar13 = (float)local_958;
      uVar9 = 1;
    }
  }
  if (((uVar8 != 2) && (local_980 != 2)) && ((double)fVar13 < local_950)) {
    fVar13 = (float)local_950;
    uVar9 = 2;
  }
  if (((uVar8 != 3) && (local_980 != 3)) && ((double)fVar13 < dStack_948)) {
    fVar13 = (float)dStack_948;
    uVar9 = 3;
  }
  if (((uVar8 != 4) && (local_980 != 4)) && ((double)fVar13 < local_940)) {
    fVar13 = (float)local_940;
    uVar9 = 4;
  }
  if (((uVar8 != 5) && (local_980 != 5)) && ((double)fVar13 < dStack_938)) {
    fVar13 = (float)dStack_938;
    uVar9 = 5;
  }
  if (((uVar8 != 6) && (local_980 != 6)) && ((double)fVar13 < local_930)) {
    fVar13 = (float)local_930;
    uVar9 = 6;
  }
  if (((uVar8 != 7) && (local_980 != 7)) && ((double)fVar13 < dStack_928)) {
    fVar13 = (float)dStack_928;
    uVar9 = 7;
  }
  if (((uVar8 != 8) && (local_980 != 8)) && ((double)fVar13 < local_920)) {
    fVar13 = (float)local_920;
    uVar9 = 8;
  }
  if (((uVar8 != 9) && (local_980 != 9)) && ((double)fVar13 < dStack_918)) {
    fVar13 = (float)dStack_918;
    uVar9 = 9;
  }
  puVar4 = *(undefined8 **)(param_4 + 8);
  iStack_97c = (int)(fVar10 * DAT_00115d34);
  if (puVar4 == *(undefined8 **)(param_4 + 0x10)) {
    std::vector<OCR_STR,std::allocator<OCR_STR>>::_M_insert_aux
              ((vector<OCR_STR,std::allocator<OCR_STR>> *)param_4,puVar4,&local_980);
  }
  else {
    if (puVar4 != (undefined8 *)0x0) {
      *puVar4 = CONCAT44(iStack_97c,local_980);
    }
    *(undefined8 **)(param_4 + 8) = puVar4 + 1;
  }
  dVar12 = DAT_00115d38;
  if (DAT_00115d38 < (double)fVar14) {
    puVar4 = *(undefined8 **)(param_4 + 8);
    iStack_97c = (int)(fVar14 * fVar2);
    local_980 = uVar8;
    if (puVar4 == *(undefined8 **)(param_4 + 0x10)) {
      std::vector<OCR_STR,std::allocator<OCR_STR>>::_M_insert_aux
                ((vector<OCR_STR,std::allocator<OCR_STR>> *)param_4,puVar4,&local_980);
    }
    else {
      if (puVar4 != (undefined8 *)0x0) {
        *puVar4 = CONCAT44(iStack_97c,uVar8);
      }
      *(undefined8 **)(param_4 + 8) = puVar4 + 1;
    }
  }
  if (dVar12 < (double)fVar13) {
    puVar4 = *(undefined8 **)(param_4 + 8);
    iStack_97c = (int)(fVar13 * fVar2);
    local_980 = uVar9;
    if (puVar4 == *(undefined8 **)(param_4 + 0x10)) {
      std::vector<OCR_STR,std::allocator<OCR_STR>>::_M_insert_aux
                ((vector<OCR_STR,std::allocator<OCR_STR>> *)param_4,puVar4,&local_980);
    }
    else {
      if (puVar4 != (undefined8 *)0x0) {
        *puVar4 = CONCAT44(iStack_97c,uVar9);
      }
      *(undefined8 **)(param_4 + 8) = puVar4 + 1;
    }
  }
  if (local_978 != (void *)0x0) {
    operator_delete(local_978);
  }
  return 0;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::CorrectBiasAngle
 * Address : 00116d9c
 * Size    : 0x268 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::CorrectBiasAngle(unsigned char**, RectRegion, double) */

undefined8 __thiscall
DigitSegment::CorrectBiasAngle(double param_1_00,DigitSegment *this,long *param_1,int *param_4)

{
  int iVar1;
  int iVar2;
  int iVar3;
  int iVar4;
  int iVar5;
  void *__s;
  void *pvVar6;
  int iVar7;
  int iVar8;
  ulong __n;
  int iVar9;
  int iVar10;
  int iVar11;
  int iVar12;
  int iVar13;
  long lVar14;
  double dVar15;
  double dVar16;
  double dVar17;
  double dVar18;
  double dVar19;
  double dVar20;
  double dVar21;
  
  iVar1 = *param_4;
  iVar2 = param_4[3];
  iVar13 = param_4[1];
  iVar3 = param_4[2];
  dVar15 = sin(param_1_00);
  dVar16 = cos(param_1_00);
  lVar14 = *param_1;
  dVar19 = (double)-iVar1;
  dVar18 = (double)iVar13 * dVar15;
  dVar17 = dVar16 * (double)iVar13;
  dVar21 = (double)iVar2 * dVar15;
  dVar20 = dVar16 * (double)iVar2;
  iVar8 = (int)(dVar18 + dVar16 * (double)iVar1 + 0.5);
  iVar7 = (int)(dVar17 + dVar15 * dVar19 + 0.5);
  iVar10 = (int)(dVar21 + dVar16 * (double)iVar1 + 0.5);
  if (iVar8 < 0) {
    iVar8 = 0;
  }
  iVar5 = (int)(dVar20 + dVar15 * dVar19 + 0.5);
  if (iVar7 < 0) {
    iVar7 = 0;
  }
  iVar11 = (int)(dVar18 + dVar16 * (double)iVar3 + 0.5);
  iVar9 = (int)(dVar17 + dVar15 * (double)-iVar3 + 0.5);
  if (iVar8 < iVar10) {
    iVar8 = iVar10;
  }
  iVar10 = (int)(dVar21 + dVar16 * (double)iVar3 + 0.5);
  iVar12 = (int)(dVar20 + dVar15 * (double)-iVar3 + 0.5);
  if (iVar7 < iVar5) {
    iVar7 = iVar5;
  }
  if (iVar8 < iVar11) {
    iVar8 = iVar11;
  }
  if (iVar7 < iVar9) {
    iVar7 = iVar9;
  }
  if (iVar8 < iVar10) {
    iVar8 = iVar10;
  }
  if (iVar7 < iVar12) {
    iVar7 = iVar12;
  }
  iVar10 = iVar8 + 1;
  __n = (ulong)(iVar10 * (iVar7 + 1));
  __s = operator_new__(__n);
  memset(__s,0,__n);
  if (iVar13 <= iVar2) {
    do {
      if (iVar1 <= iVar3) {
        iVar5 = iVar1;
        iVar9 = -iVar1;
        do {
          dVar18 = (double)iVar5;
          dVar17 = (double)iVar9;
          iVar9 = iVar9 + -1;
          iVar4 = iVar5 + iVar13 * *(int *)(this + 0xa8);
          iVar5 = iVar5 + 1;
          iVar12 = (int)(dVar15 * (double)iVar13 + dVar16 * dVar18 + 0.5);
          iVar11 = (int)(dVar16 * (double)iVar13 + dVar15 * dVar17 + 0.5);
          if (iVar12 < 0) {
            iVar12 = 0;
          }
          if (iVar11 < 0) {
            iVar11 = 0;
          }
          if (iVar7 < iVar11) {
            iVar11 = iVar7;
          }
          if (iVar8 < iVar12) {
            iVar12 = iVar8;
          }
          *(undefined1 *)((long)__s + (long)(iVar12 + iVar10 * iVar11)) =
               *(undefined1 *)(lVar14 + iVar4);
        } while (iVar5 != iVar3 + 1);
      }
      iVar13 = iVar13 + 1;
    } while (iVar13 != iVar2 + 1);
  }
  pvVar6 = (void *)*param_1;
  *(int *)(this + 0xa8) = iVar10;
  *(int *)(this + 0xac) = iVar7 + 1;
  if (pvVar6 != (void *)0x0) {
    operator_delete__(pvVar6);
  }
  *param_1 = (long)__s;
  return 1;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::initSplitData
 * Address : 00117004
 * Size    : 0x290 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::initSplitData() */

void __thiscall DigitSegment::initSplitData(DigitSegment *this)

{
  ulong uVar1;
  undefined4 *puVar2;
  int iVar3;
  int iVar4;
  undefined4 uVar5;
  undefined4 uVar6;
  void *pvVar7;
  ulong uVar8;
  ulong uVar9;
  undefined8 *puVar10;
  undefined8 *puVar11;
  undefined4 *puVar12;
  undefined4 *puVar13;
  ulong uVar14;
  ulong uVar15;
  
  iVar3 = *(int *)(this + 0xa8);
  iVar4 = *(int *)(this + 0xac);
  if (*(void **)(this + 0xa0) != (void *)0x0) {
    operator_delete__(*(void **)(this + 0xa0));
  }
  if (*(void **)(this + 0x38) == (void *)0x0) {
    pvVar7 = *(void **)(this + 0x28);
  }
  else {
    operator_delete__(*(void **)(this + 0x38));
    pvVar7 = *(void **)(this + 0x28);
  }
  if (pvVar7 != (void *)0x0) {
    operator_delete__(pvVar7);
  }
  if (*(void **)(this + 0x30) != (void *)0x0) {
    operator_delete__(*(void **)(this + 0x30));
  }
  uVar8 = (ulong)(iVar3 * iVar4);
  *(int *)(this + 0x44) =
       *(int *)(this + 0xa8) + (*(int *)(this + 0xac) / 10) * *(int *)(this + 0xa8);
  if (uVar8 < 0x1fc0000000000001) {
    uVar9 = uVar8 << 2;
  }
  else {
    uVar9 = 0xffffffffffffffff;
  }
  pvVar7 = operator_new__(uVar9);
  uVar15 = (ulong)*(int *)(this + 0x44);
  *(void **)(this + 0xa0) = pvVar7;
  uVar9 = 0xffffffffffffffff;
  if (uVar15 < 0x660000000000001) {
    uVar9 = uVar15 * 0x14;
  }
  puVar10 = operator_new__(uVar9);
  uVar9 = uVar15 - 1;
  if (uVar15 != 0) {
    uVar1 = (uVar15 - 4 >> 2) + 1;
    puVar11 = puVar10;
    if (2 < uVar9) {
      uVar14 = 0;
      do {
        puVar11[1] = 0;
        *puVar11 = 0;
        uVar14 = uVar14 + 1;
        puVar11[3] = 0;
        puVar11[2] = 0;
        puVar11[5] = 0;
        puVar11[4] = 0;
        puVar11[7] = 0;
        puVar11[6] = 0;
        puVar11[9] = 0;
        puVar11[8] = 0;
        puVar11 = puVar11 + 10;
      } while (uVar14 < uVar1);
      uVar9 = uVar9 + uVar1 * -4;
      puVar11 = puVar10 + uVar1 * 10;
      if (uVar15 == uVar1 * 4) goto LAB_00117174;
    }
    *(undefined4 *)puVar11 = 0;
    *(undefined4 *)((long)puVar11 + 4) = 0;
    *(undefined4 *)(puVar11 + 1) = 0;
    *(undefined4 *)((long)puVar11 + 0xc) = 0;
    *(undefined4 *)(puVar11 + 2) = 0;
    if (uVar9 != 0) {
      *(undefined4 *)((long)puVar11 + 0x14) = 0;
      *(undefined4 *)(puVar11 + 3) = 0;
      *(undefined4 *)((long)puVar11 + 0x1c) = 0;
      *(undefined4 *)(puVar11 + 4) = 0;
      *(undefined4 *)((long)puVar11 + 0x24) = 0;
      if (uVar9 != 1) {
        *(undefined4 *)(puVar11 + 5) = 0;
        *(undefined4 *)((long)puVar11 + 0x2c) = 0;
        *(undefined4 *)(puVar11 + 6) = 0;
        *(undefined4 *)((long)puVar11 + 0x34) = 0;
        *(undefined4 *)(puVar11 + 7) = 0;
      }
    }
  }
LAB_00117174:
  *(undefined8 **)(this + 0x38) = puVar10;
  uVar9 = (long)*(int *)(this + 0x44) << 2;
  if (0x1fc0000000000000 < (ulong)(long)*(int *)(this + 0x44)) {
    uVar9 = 0xffffffffffffffff;
  }
  pvVar7 = operator_new__(uVar9);
  *(void **)(this + 0x28) = pvVar7;
  uVar9 = (long)*(int *)(this + 0x44) << 2;
  if (0x1fc0000000000000 < (ulong)(long)*(int *)(this + 0x44)) {
    uVar9 = 0xffffffffffffffff;
  }
  pvVar7 = operator_new__(uVar9);
  *(void **)(this + 0x30) = pvVar7;
  memset(*(void **)(this + 0xa0),0,uVar8 << 2);
  memset(*(void **)(this + 0x30),0,(long)*(int *)(this + 0x44) << 2);
  memset(*(void **)(this + 0x28),0,(long)*(int *)(this + 0x44) << 2);
  iVar3 = *(int *)(this + 0x44);
  if (0 < iVar3) {
    puVar12 = *(undefined4 **)(this + 0x38);
    uVar5 = *(undefined4 *)(this + 0xa8);
    uVar6 = *(undefined4 *)(this + 0xac);
    puVar13 = puVar12;
    do {
      *puVar13 = uVar5;
      puVar2 = puVar13 + 5;
      puVar13[1] = uVar6;
      puVar13[2] = 0;
      puVar13[3] = 0;
      puVar13 = puVar2;
    } while (puVar2 != puVar12 + ((ulong)(iVar3 - 1) + 1) * 5);
  }
  *(undefined4 *)(this + 0x40) = 1;
  if (*(void **)this != (void *)0x0) {
    operator_delete__(*(void **)this);
  }
  uVar8 = (long)*(int *)(this + 0xa8) << 2;
  if (0x1fc0000000000000 < (ulong)(long)*(int *)(this + 0xa8)) {
    uVar8 = 0xffffffffffffffff;
  }
  pvVar7 = operator_new__(uVar8);
  *(void **)this = pvVar7;
  memset(pvVar7,0,(long)*(int *)(this + 0xa8) << 2);
  return;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::CheckIsADigit
 * Address : 00117294
 * Size    : 0x2c bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::CheckIsADigit(int, int) */

bool __thiscall DigitSegment::CheckIsADigit(DigitSegment *this,int param_1,int param_2)

{
  int iVar1;
  bool bVar2;
  
  iVar1 = (param_2 - param_1) + 1;
  bVar2 = false;
  if (iVar1 <= *(int *)(this + 0x60)) {
    bVar2 = *(int *)(this + 0x5c) <= iVar1;
  }
  return bVar2;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::FindIntersectRect
 * Address : 001172c0
 * Size    : 0x364 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::FindIntersectRect(RectRegion, int) */

uint * DigitSegment::FindIntersectRect(uint *param_1,long param_2,uint *param_3,int param_4)

{
  uint uVar1;
  uint uVar2;
  int iVar3;
  int iVar4;
  int iVar5;
  ulong uVar6;
  void *__s;
  int *piVar7;
  int iVar8;
  int iVar9;
  int iVar10;
  ulong uVar11;
  int iVar12;
  int iVar13;
  uint uVar14;
  int iVar15;
  double dVar16;
  double dVar17;
  int local_18;
  int iStack_14;
  undefined4 local_10;
  int local_c;
  uint local_8;
  
  uVar2 = *param_3;
  if ((int)uVar2 < 0) {
    uVar2 = 0;
  }
  uVar1 = *(int *)(param_2 + 0xa8) - 1;
  if ((int)param_3[2] < *(int *)(param_2 + 0xa8)) {
    uVar1 = param_3[2];
  }
  uVar11 = (ulong)uVar2;
  uVar6 = (long)*(int *)(param_2 + 0xac) << 2;
  if (0x1fc0000000000000 < (ulong)(long)*(int *)(param_2 + 0xac)) {
    uVar6 = 0xffffffffffffffff;
  }
  __s = operator_new__(uVar6);
  memset(__s,0,(long)*(int *)(param_2 + 0xac) << 2);
  iVar15 = *(int *)(param_2 + 0xac);
  iVar3 = *(int *)(param_2 + 0x5c);
  iVar5 = *(int *)(param_2 + 0xa8);
  local_10 = 0;
  local_c = 0;
  local_8 = 0;
  local_18 = iVar5;
  iStack_14 = iVar15;
  if ((int)uVar1 < (int)uVar2) {
    uVar14 = 0;
    iVar12 = -1;
    iVar8 = 0;
    iVar10 = -1;
    iVar5 = 1;
    if (iVar15 < 1) goto LAB_00117494;
  }
  else {
    iVar8 = 0;
    uVar6 = -(ulong)(uVar2 >> 0x1f) & 0xfffffff000000000 | uVar11 << 4;
    uVar14 = 0;
    do {
      iVar12 = *(int *)(*(long *)(param_2 + 200) + uVar6);
      iVar10 = *(int *)(*(long *)(param_2 + 200) + uVar6 + 4);
      if (iVar12 < iVar15) {
        iVar15 = iVar12;
      }
      if (iVar8 < iVar10) {
        iVar8 = iVar10;
      }
      iVar13 = (int)uVar11;
      if (iVar12 <= iVar10) {
        piVar7 = (int *)((long)__s + (long)iVar12 * 4);
        iVar9 = iVar12;
        do {
          while (*(char *)(*(long *)(param_2 + 0xc0) + (long)(iVar13 + iVar9 * iVar5)) == '\0') {
LAB_001173e4:
            iVar9 = iVar9 + 1;
            piVar7 = piVar7 + 1;
            if (iVar10 < iVar9) goto LAB_00117464;
          }
          updateRect((RectRegion *)&local_18,piVar7,iVar13,iVar9);
          iVar5 = *(int *)(param_2 + 0xa8);
          iVar4 = *(int *)(*(long *)(param_2 + 0xa0) + (long)(iVar13 + iVar9 * iVar5) * 4);
          if ((iVar4 < 1) || (*(int *)(param_2 + 0x40) <= iVar4)) goto LAB_001173e4;
          iVar9 = iVar9 + 1;
          piVar7 = piVar7 + 1;
          if (*(int *)(*(long *)(param_2 + 0x30) + (long)iVar4 * 4) == 0) {
            uVar14 = 1;
          }
        } while (iVar9 <= iVar10);
      }
LAB_00117464:
      uVar11 = (ulong)(iVar13 + 1U);
      uVar6 = uVar6 + 0x10;
    } while ((int)(iVar13 + 1U) <= (int)uVar1);
    iVar10 = iVar10 + -1;
    iVar5 = iVar12 + 1;
    iVar12 = iVar10 - iVar12;
    if (iVar15 <= iVar8) {
LAB_00117494:
      iVar13 = 0;
      piVar7 = (int *)((long)__s + (long)iVar15 * 4);
      do {
        if (((-1 < iVar15) && (iVar15 < *(int *)(param_2 + 0xac))) && (iVar3 / 0x14 < *piVar7)) {
          iVar13 = iVar13 + 1;
        }
        iVar15 = iVar15 + 1;
        piVar7 = piVar7 + 1;
      } while (iVar15 != iVar8 + 1);
      goto LAB_001174d4;
    }
  }
  iVar13 = 0;
LAB_001174d4:
  local_8 = uVar14;
  operator_delete__(__s);
  dVar16 = (double)iVar12;
  dVar17 = (double)iVar13;
  if (param_4 == 0) {
    if ((dVar16 * DAT_00117628 <= dVar17) ||
       (((dVar16 * DAT_00117630 < dVar17 && (uVar14 != 0)) &&
        ((iVar15 = *(int *)(param_2 + 0x50) / 10, iStack_14 <= iVar15 + iVar5 &&
         (iVar10 - iVar15 <= local_c)))))) {
      param_1[4] = local_8;
      *(ulong *)param_1 = CONCAT44(iStack_14,local_18);
      *(ulong *)(param_1 + 2) = CONCAT44(local_c,local_10);
      return param_1;
    }
  }
  else if (dVar16 * DAT_00117630 <= dVar17) {
    param_1[4] = local_8 + 2;
    *(ulong *)param_1 = CONCAT44(iVar5,local_18);
    *(ulong *)(param_1 + 2) = CONCAT44(iVar10,local_10);
    return param_1;
  }
  *param_1 = uVar2;
  param_1[2] = uVar1;
  param_1[1] = 0xffffffff;
  param_1[3] = 0xffffffff;
  param_1[4] = 0;
  return param_1;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::filterTargetNoiseBinaryImg
 * Address : 00117638
 * Size    : 0xe8 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::filterTargetNoiseBinaryImg() */

void __thiscall DigitSegment::filterTargetNoiseBinaryImg(DigitSegment *this)

{
  int iVar1;
  long lVar2;
  int iVar3;
  long lVar4;
  int iVar5;
  long lVar6;
  int iVar7;
  void *__s;
  
  __s = *(void **)(this + 0xc0);
  memset(__s,0,(long)*(int *)(this + 0xa8) * (long)*(int *)(this + 0xac));
  iVar5 = *(int *)(this + 0xa8);
  if (0 < iVar5) {
    lVar6 = 0;
    lVar4 = *(long *)(this + 200);
    iVar7 = 0;
    do {
      iVar3 = *(int *)(lVar4 + lVar6);
      if (iVar3 <= *(int *)(lVar4 + lVar6 + 4)) {
        do {
          while( true ) {
            iVar1 = iVar3 * iVar5;
            iVar3 = iVar3 + 1;
            lVar2 = (long)(iVar7 + iVar1);
            iVar1 = *(int *)(*(long *)(this + 0xa0) + lVar2 * 4);
            if (((0 < iVar1) && (-1 < *(int *)(*(long *)(this + 0x30) + (long)iVar1 * 4))) ||
               ((int)(uint)*(byte *)(*(long *)(this + 0xb8) + lVar2) <= *(int *)(this + 0xf8)))
            break;
            if (*(int *)(lVar4 + lVar6 + 4) < iVar3) goto LAB_00117700;
          }
          *(undefined1 *)((long)__s + lVar2) = 0xff;
          lVar4 = *(long *)(this + 200);
          iVar5 = *(int *)(this + 0xa8);
        } while (iVar3 <= *(int *)(lVar4 + lVar6 + 4));
      }
LAB_00117700:
      iVar7 = iVar7 + 1;
      lVar6 = lVar6 + 0x10;
    } while (iVar7 < iVar5);
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::updateDigitRectIndex
 * Address : 00117720
 * Size    : 0x140 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::updateDigitRectIndex() */

void __thiscall DigitSegment::updateDigitRectIndex(DigitSegment *this)

{
  int iVar1;
  int iVar2;
  int iVar3;
  int iVar4;
  int iVar5;
  int *piVar6;
  int iVar7;
  int *piVar8;
  int iVar9;
  int *piVar10;
  double dVar11;
  double dVar12;
  double dVar13;
  double dVar14;
  
  iVar1 = *(int *)(this + 0x4c);
  iVar2 = *(int *)(this + 0x50);
  if (1 < *(int *)(this + 0x40)) {
    dVar14 = *(double *)(this + 0x80);
    dVar13 = *(double *)(this + 0x88);
    dVar12 = *(double *)(this + 0x90);
    dVar11 = *(double *)(this + 0x98);
    piVar8 = (int *)(*(long *)(this + 0x30) + 4);
    piVar10 = (int *)(*(long *)(this + 0x28) + 4);
    piVar6 = (int *)(*(long *)(this + 0x38) + 0x18);
    iVar7 = 1;
    do {
      while( true ) {
        iVar4 = piVar6[-1];
        iVar3 = piVar6[1];
        *piVar8 = iVar7;
        iVar9 = (int)(dVar13 + dVar14 * (double)iVar4);
        iVar5 = (int)(dVar13 + dVar14 * (double)iVar3);
        iVar4 = (int)(dVar11 + dVar12 * (double)iVar4);
        iVar3 = (int)(dVar11 + dVar12 * (double)iVar3);
        if (iVar9 < iVar5) {
          iVar5 = iVar9;
        }
        iVar5 = iVar5 - iVar2 / 5;
        if (iVar4 < iVar3) {
          iVar3 = iVar4;
        }
        iVar4 = iVar7;
        if (*piVar10 <= (iVar1 * iVar1) / 0x46) {
          iVar4 = -1;
        }
        *piVar8 = iVar4;
        if (iVar2 / 5 + iVar3 < piVar6[2]) break;
        if (*piVar6 < iVar5) {
LAB_00117810:
          *piVar8 = 0;
        }
        iVar7 = iVar7 + 1;
        piVar8 = piVar8 + 1;
        piVar10 = piVar10 + 1;
        piVar6 = piVar6 + 5;
        if (*(int *)(this + 0x40) <= iVar7) {
          return;
        }
      }
      if (iVar5 <= *piVar6) goto LAB_00117810;
      *piVar8 = -2;
      iVar7 = iVar7 + 1;
      piVar8 = piVar8 + 1;
      piVar10 = piVar10 + 1;
      piVar6 = piVar6 + 5;
    } while (iVar7 < *(int *)(this + 0x40));
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::getDigitBoundingBox
 * Address : 00117860
 * Size    : 0x8 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::getDigitBoundingBox() */

DigitSegment * __thiscall DigitSegment::getDigitBoundingBox(DigitSegment *this)

{
  return this + 0x18;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::FindSegPointFromHistogram
 * Address : 00117870
 * Size    : 0x6f0 bytes
 * ------------------------------------------------------------------ */

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */
/* DigitSegment::FindSegPointFromHistogram(RectRegion, double, int, std::vector<_RangeMinMax,
   std::allocator<_RangeMinMax> >&, float) */

undefined4 __thiscall
DigitSegment::FindSegPointFromHistogram
          (double param_1,float param_2,DigitSegment *this,int *param_4,int param_5,long *param_6)

{
  uint uVar1;
  undefined4 *puVar2;
  undefined8 uVar3;
  int iVar4;
  int iVar5;
  int iVar6;
  int iVar7;
  uint uVar8;
  char cVar9;
  bool bVar10;
  uint uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  undefined8 uVar16;
  int iVar17;
  ulong uVar18;
  undefined4 *__s;
  long lVar19;
  int *piVar20;
  undefined8 *puVar21;
  int iVar22;
  undefined8 *puVar23;
  int iVar24;
  long lVar25;
  int *piVar26;
  undefined8 *puVar27;
  int iVar28;
  long lVar29;
  undefined *puVar30;
  ulong uVar31;
  long lVar32;
  int iVar33;
  int *piVar34;
  uint uVar35;
  undefined4 uVar36;
  ulong uVar37;
  int *piVar38;
  int iVar39;
  undefined8 uVar40;
  int iVar41;
  int iVar42;
  undefined8 uVar43;
  int iVar44;
  undefined1 auStack_10 [8];
  long local_8;
  
  iVar4 = *param_4;
  iVar5 = param_4[2];
  FUN_0016bea4(&local_8,"",auStack_10);
  uVar37 = (ulong)(*(int *)(this + 0xa8) + 1);
  uVar18 = uVar37 << 2;
  if (0x1fc0000000000000 < uVar37) {
    uVar18 = 0xffffffffffffffff;
  }
  __s = operator_new__(uVar18);
  memset(__s,0,uVar37);
  uVar16 = _UNK_00117f98;
  uVar3 = _DAT_00117f90;
  if (iVar4 <= iVar5) {
    lVar19 = (long)iVar4;
    lVar29 = *(long *)this;
    lVar32 = (lVar19 + 3) * 4;
    uVar11 = iVar5 - iVar4;
    puVar2 = __s + lVar19;
    if (((undefined4 *)(lVar29 + lVar32 + -8) < __s + lVar19 + 4 &&
         puVar2 < (undefined4 *)(lVar29 + lVar32)) || (uVar11 + 1 < 5)) {
      piVar20 = (int *)(lVar29 + lVar32 + -0xc);
      lVar19 = 0;
      do {
        iVar39 = *piVar20;
        piVar38 = piVar20 + -1;
        piVar20 = piVar20 + 1;
        puVar2[lVar19] = (iVar39 + *piVar38 + *piVar20) / 3;
        lVar19 = lVar19 + 1;
      } while (lVar19 != (ulong)uVar11 + 1);
    }
    else {
      uVar1 = (uVar11 - 3 >> 2) + 1;
      lVar25 = 0;
      uVar35 = 0;
      uVar8 = uVar1 * 4;
      do {
        puVar27 = (undefined8 *)(lVar29 + lVar25 + lVar32 + -0xc);
        puVar21 = (undefined8 *)(lVar29 + lVar25 + lVar32 + -0x10);
        puVar23 = (undefined8 *)((long)puVar2 + lVar25);
        uVar13 = puVar27[1];
        uVar12 = *puVar27;
        puVar27 = (undefined8 *)(lVar29 + lVar25 + (lVar19 + 1) * 4);
        uVar43 = puVar21[1];
        uVar40 = *puVar21;
        uVar35 = uVar35 + 1;
        uVar15 = puVar27[1];
        uVar14 = *puVar27;
        lVar25 = lVar25 + 0x10;
        iVar39 = (int)uVar12 + (int)uVar40 + (int)uVar14;
        iVar41 = (int)((ulong)uVar12 >> 0x20) + (int)((ulong)uVar40 >> 0x20) +
                 (int)((ulong)uVar14 >> 0x20);
        iVar42 = (int)uVar13 + (int)uVar43 + (int)uVar15;
        iVar44 = (int)((ulong)uVar13 >> 0x20) + (int)((ulong)uVar43 >> 0x20) +
                 (int)((ulong)uVar15 >> 0x20);
        puVar23[1] = CONCAT44((int)((ulong)((long)iVar44 * (long)(int)((ulong)uVar16 >> 0x20)) >>
                                   0x20) - (iVar44 >> 0x1f),
                              (int)((ulong)((long)iVar42 * (long)(int)uVar16) >> 0x20) -
                              (iVar42 >> 0x1f));
        *puVar23 = CONCAT44((int)((ulong)((long)iVar41 * (long)(int)((ulong)uVar3 >> 0x20)) >> 0x20)
                            - (iVar41 >> 0x1f),
                            (int)((ulong)((long)iVar39 * (long)(int)uVar3) >> 0x20) -
                            (iVar39 >> 0x1f));
      } while (uVar35 < uVar1);
      iVar39 = iVar4 + uVar8;
      if (uVar11 + 1 != uVar8) {
        lVar19 = (long)iVar39;
        __s[lVar19] = (*(int *)(lVar29 + lVar19 * 4) + *(int *)(lVar29 + lVar19 * 4 + -4) +
                      *(int *)(lVar29 + (lVar19 + 1) * 4)) / 3;
        if (iVar39 + 1 <= iVar5) {
          lVar19 = (long)(iVar39 + 1);
          __s[lVar19] = (*(int *)(lVar29 + lVar19 * 4) + *(int *)(lVar29 + lVar19 * 4 + -4) +
                        *(int *)(lVar29 + (lVar19 + 1) * 4)) / 3;
          if (iVar39 + 2 <= iVar5) {
            lVar19 = (long)(iVar39 + 2);
            __s[lVar19] = (*(int *)(lVar29 + lVar19 * 4) + *(int *)(lVar29 + lVar19 * 4 + -4) +
                          *(int *)(lVar29 + (lVar19 + 1) * 4)) / 3;
          }
        }
      }
    }
  }
  *__s = 0;
  lVar19 = *param_6;
  __s[(long)*(int *)(this + 0xa8) + -1] = 0;
  param_6[1] = lVar19;
  iVar39 = iVar4;
  do {
    iVar41 = iVar39 + 1;
    if (iVar41 <= iVar5) {
      piVar20 = __s + iVar41;
      iVar44 = -1;
      iVar24 = -1;
      iVar22 = -1;
      iVar28 = -1;
      iVar42 = -1;
      do {
        while( true ) {
          iVar6 = *piVar20;
          iVar7 = piVar20[-1];
          if (iVar7 < iVar6) break;
          if (iVar6 < iVar7) {
            iVar33 = 2;
            iVar17 = 2;
            if (iVar44 != 2) goto LAB_00117af4;
            goto LAB_00117bb0;
          }
joined_r0x00117c08:
          iVar6 = iVar22;
          iVar7 = iVar24;
          iVar17 = iVar28;
joined_r0x00117c30:
          piVar20 = piVar20 + 1;
          iVar41 = iVar41 + 1;
          iVar24 = iVar7;
          iVar22 = iVar6;
          iVar28 = iVar17;
          iVar39 = iVar39 + 1;
          if (iVar5 < iVar41) goto LAB_00117afc;
        }
        iVar33 = 1;
        iVar17 = 1;
        if (iVar44 != 1) {
LAB_00117af4:
          iVar33 = iVar17;
          if (iVar44 != -1) break;
        }
LAB_00117bb0:
        if (iVar44 == -1) {
          iVar44 = iVar33;
        }
        if (iVar33 == 2) {
          iVar22 = iVar6;
          iVar42 = iVar41;
          iVar17 = iVar39;
          if (iVar24 != -1) goto joined_r0x00117c08;
          goto joined_r0x00117c30;
        }
        iVar24 = iVar6;
        iVar28 = iVar41;
        if (iVar22 != -1) goto joined_r0x00117c08;
        iVar41 = iVar41 + 1;
        piVar20 = piVar20 + 1;
        iVar22 = iVar7;
        iVar42 = iVar39;
        iVar39 = iVar39 + 1;
      } while (iVar41 <= iVar5);
LAB_00117afc:
      if ((0 < iVar24) && ((double)iVar22 / (double)iVar24 <= param_1)) {
        iVar39 = 1;
        if ((iVar28 <= iVar42) && (iVar39 = 2, iVar42 <= iVar28)) {
          iVar39 = 3;
        }
        piVar20 = (int *)param_6[1];
        piVar38 = (int *)param_6[2];
        if (iVar42 < 0) {
          iVar42 = 0;
        }
        if (piVar38 == piVar20) {
          piVar20 = (int *)*param_6;
          lVar19 = (long)piVar38 - (long)piVar20;
          uVar18 = lVar19 >> 4;
          if (uVar18 == 0) {
            uVar37 = 0x10;
LAB_00117d38:
            puVar21 = operator_new(uVar37);
            puVar27 = puVar21 + 2;
            piVar20 = (int *)*param_6;
            piVar34 = (int *)param_6[1];
          }
          else {
            uVar31 = uVar18 * 2;
            uVar37 = 0xfffffffffffffff0;
            if ((uVar31 < uVar18) || (uVar37 = 0xfffffffffffffff0, 0xfffffffffffffff < uVar31))
            goto LAB_00117d38;
            uVar37 = uVar18 << 5;
            puVar27 = (undefined8 *)0x10;
            puVar21 = (undefined8 *)0x0;
            piVar34 = piVar38;
            if (uVar31 != 0) goto LAB_00117d38;
          }
          lVar29 = (long)puVar21 + lVar19;
          if (lVar29 != 0) {
            *(int *)((long)puVar21 + lVar19) = iVar42;
            *(int *)(lVar29 + 4) = iVar39;
            *(undefined4 *)(lVar29 + 8) = 0;
            *(undefined4 *)(lVar29 + 0xc) = 0;
          }
          puVar23 = puVar21;
          piVar26 = piVar20;
          if (piVar38 != piVar20) {
            do {
              if (puVar23 != (undefined8 *)0x0) {
                uVar3 = *(undefined8 *)(piVar26 + 2);
                *puVar23 = *(undefined8 *)piVar26;
                puVar23[1] = uVar3;
              }
              piVar26 = piVar26 + 4;
              puVar23 = puVar23 + 2;
            } while (piVar38 != piVar26);
            puVar27 = (undefined8 *)
                      ((long)puVar21 +
                      ((long)piVar38 - (long)(piVar20 + 4) & 0xfffffffffffffff0U) + 0x20);
          }
          puVar23 = puVar27;
          piVar26 = piVar38;
          if (piVar38 != piVar34) {
            do {
              if (puVar23 != (undefined8 *)0x0) {
                uVar3 = *(undefined8 *)(piVar26 + 2);
                *puVar23 = *(undefined8 *)piVar26;
                puVar23[1] = uVar3;
              }
              piVar26 = piVar26 + 4;
              puVar23 = puVar23 + 2;
            } while (piVar26 != piVar34);
            puVar27 = (undefined8 *)
                      ((long)puVar27 +
                      ((long)piVar26 - (long)(piVar38 + 4) & 0xfffffffffffffff0U) + 0x10);
          }
          if (piVar20 != (int *)0x0) {
            operator_delete(piVar20);
          }
          *param_6 = (long)puVar21;
          param_6[1] = (long)puVar27;
          param_6[2] = (long)puVar21 + uVar37;
        }
        else {
          if (piVar20 != (int *)0x0) {
            *piVar20 = iVar42;
            piVar20[1] = iVar39;
            piVar20[2] = 0;
            piVar20[3] = 0;
          }
          param_6[1] = (long)(piVar20 + 4);
        }
      }
    }
    iVar39 = iVar41 + -1;
  } while (iVar39 < iVar5 + -1);
  if (param_5 == 0) {
    lVar19 = param_6[1] - *param_6 >> 4;
    if (lVar19 != 0) {
      uVar37 = 0;
      lVar29 = 0;
      do {
        iVar5 = (*(int *)(*param_6 + uVar37) - iVar4) + 1;
        if ((*(int *)(this + 0x5c) < iVar5) &&
           ((float)iVar5 <= (float)*(int *)(this + 0x60) + param_2 * (float)*(int *)(this + 0x5c)))
        goto LAB_00117e8c;
        lVar29 = lVar29 + 1;
        uVar37 = uVar37 + 0x10;
      } while (lVar19 != lVar29);
    }
  }
  else if (param_5 == 1) {
    uVar11 = (int)(param_6[1] - *param_6 >> 4) - 1;
    uVar18 = (ulong)uVar11;
    if (-1 < (int)uVar11) {
      uVar37 = -(ulong)(uVar11 >> 0x1f) & 0xfffffff000000000 | uVar18 << 4;
      do {
        iVar4 = (iVar5 - *(int *)(*param_6 + uVar37)) + 1;
        if ((*(int *)(this + 0x5c) < iVar4) &&
           ((float)iVar4 <= (float)*(int *)(this + 0x60) + param_2 * (float)*(int *)(this + 0x5c)))
        goto LAB_00117e8c;
        uVar11 = (int)uVar18 - 1;
        uVar18 = (ulong)uVar11;
        uVar37 = uVar37 - 0x10;
      } while (uVar11 != 0xffffffff);
    }
  }
  uVar36 = 0xffffffff;
  operator_delete__(__s);
  puVar30 = (undefined *)(local_8 + -0x18);
  if (puVar30 == PTR_DAT_001cae30) {
    return 0xffffffff;
  }
LAB_00117eb4:
  piVar20 = (int *)(local_8 + -8);
  do {
    iVar4 = *piVar20;
    cVar9 = '\x01';
    bVar10 = (bool)ExclusiveMonitorPass(piVar20,0x10);
    if (bVar10) {
      *piVar20 = iVar4 + -1;
      cVar9 = ExclusiveMonitorsStatus();
    }
  } while (cVar9 != '\0');
  if (iVar4 < 1) {
    operator_delete(puVar30);
  }
  return uVar36;
LAB_00117e8c:
  operator_delete__(__s);
  puVar30 = (undefined *)(local_8 + -0x18);
  uVar36 = *(undefined4 *)(*param_6 + uVar37);
  if (puVar30 == PTR_DAT_001cae30) {
    return uVar36;
  }
  goto LAB_00117eb4;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::getMostDigitEdge
 * Address : 00117fa0
 * Size    : 0x380 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::getMostDigitEdge(RectRegion, int, int) */

uint __thiscall
DigitSegment::getMostDigitEdge(DigitSegment *this,int *param_2,int param_3,int param_4)

{
  int *piVar1;
  undefined8 *puVar2;
  undefined8 *puVar3;
  undefined8 *puVar4;
  int iVar5;
  ulong uVar6;
  undefined4 *__s;
  long lVar7;
  uint uVar8;
  uint uVar9;
  int *piVar10;
  ulong uVar11;
  int *piVar12;
  undefined4 *puVar13;
  int iVar14;
  long lVar15;
  long lVar16;
  uint uVar17;
  uint uVar18;
  int iVar19;
  int iVar20;
  undefined8 uVar21;
  int iVar22;
  int iVar23;
  undefined8 uVar24;
  int iVar25;
  undefined8 uVar26;
  undefined8 uVar27;
  undefined8 uVar28;
  undefined8 uVar29;
  
  uVar6 = (long)*(int *)(this + 0xa8) << 2;
  if (0x1fc0000000000000 < (ulong)(long)*(int *)(this + 0xa8)) {
    uVar6 = 0xffffffffffffffff;
  }
  iVar14 = *param_2;
  iVar5 = param_2[2];
  iVar19 = *(int *)(this + 0xac);
  __s = operator_new__(uVar6);
  memset(__s,0,(long)*(int *)(this + 0xa8));
  puVar13 = *(undefined4 **)this;
  *__s = *puVar13;
  if (iVar14 < iVar5) {
    lVar7 = (long)iVar14;
    uVar8 = iVar5 - iVar14;
    lVar15 = (lVar7 + 3) * 4;
    piVar12 = __s + lVar7;
    if ((puVar13 + lVar7 + 1 < __s + lVar7 + 4 && piVar12 < puVar13 + lVar7 + 3) || (uVar8 < 5)) {
      piVar10 = puVar13 + lVar7;
      do {
        iVar20 = *piVar10;
        piVar1 = piVar10 + -1;
        piVar10 = piVar10 + 1;
        iVar20 = *piVar1 + iVar20 * 2 + *piVar10;
        iVar22 = iVar20 + 3;
        if (-1 < iVar20) {
          iVar22 = iVar20;
        }
        *piVar12 = iVar22 >> 2;
        piVar12 = piVar12 + 1;
      } while (piVar10 != puVar13 + lVar7 + 1 + (ulong)(uint)((iVar5 + -1) - iVar14));
    }
    else {
      uVar18 = (uVar8 - 4 >> 2) + 1;
      uVar9 = uVar18 * 4;
      iVar20 = iVar14;
      if (2 < (uint)((iVar5 + -1) - iVar14)) {
        lVar16 = 0;
        uVar17 = 0;
        do {
          puVar2 = (undefined8 *)((long)puVar13 + lVar16 + lVar15 + -0xc);
          puVar3 = (undefined8 *)((long)puVar13 + lVar16 + lVar15 + -0x10);
          puVar4 = (undefined8 *)((long)piVar12 + lVar16);
          uVar27 = puVar2[1];
          uVar26 = *puVar2;
          puVar2 = (undefined8 *)((long)puVar13 + lVar16 + (lVar7 + 1) * 4);
          uVar24 = puVar3[1];
          uVar21 = *puVar3;
          uVar17 = uVar17 + 1;
          uVar29 = puVar2[1];
          uVar28 = *puVar2;
          lVar16 = lVar16 + 0x10;
          iVar20 = (int)uVar26 * 2 + (int)uVar21 + (int)uVar28;
          iVar22 = (int)((ulong)uVar26 >> 0x20) * 2 + (int)((ulong)uVar21 >> 0x20) +
                   (int)((ulong)uVar28 >> 0x20);
          iVar23 = (int)uVar27 * 2 + (int)uVar24 + (int)uVar29;
          iVar25 = (int)((ulong)uVar27 >> 0x20) * 2 + (int)((ulong)uVar24 >> 0x20) +
                   (int)((ulong)uVar29 >> 0x20);
          puVar4[1] = CONCAT44((int)(iVar25 + (uint)(~-(-1 < iVar25) & 3)) >> 2,
                               (int)(iVar23 + (uint)(~-(-1 < iVar23) & 3)) >> 2);
          *puVar4 = CONCAT44((int)(iVar22 + (uint)(~-(-1 < iVar22) & 3)) >> 2,
                             (int)(iVar20 + (uint)(~-(-1 < iVar20) & 3)) >> 2);
        } while (uVar17 < uVar18);
        iVar20 = iVar14 + uVar9;
        if (uVar8 == uVar9) goto LAB_001181b4;
      }
      iVar22 = iVar20 + 1;
      iVar23 = puVar13[(long)iVar20 + -1] + puVar13[iVar20] * 2 + puVar13[(long)iVar20 + 1];
      iVar25 = iVar23 + 3;
      if (-1 < iVar23) {
        iVar25 = iVar23;
      }
      __s[iVar20] = iVar25 >> 2;
      if (iVar22 < iVar5) {
        iVar20 = iVar20 + 2;
        iVar23 = puVar13[(long)iVar22 + -1] + puVar13[iVar22] * 2 + puVar13[(long)iVar22 + 1];
        iVar25 = iVar23 + 3;
        if (-1 < iVar23) {
          iVar25 = iVar23;
        }
        __s[iVar22] = iVar25 >> 2;
        if (iVar20 < iVar5) {
          iVar22 = puVar13[(long)iVar20 + -1] + puVar13[iVar20] * 2 + puVar13[(long)iVar20 + 1];
          iVar23 = iVar22 + 3;
          if (-1 < iVar22) {
            iVar23 = iVar22;
          }
          __s[iVar20] = iVar23 >> 2;
        }
      }
    }
  }
LAB_001181b4:
  if (param_3 == 0) {
    iVar5 = *(int *)(this + 0x5c);
    uVar8 = iVar5 + iVar14;
    uVar18 = iVar14 + *(int *)(this + 0x60);
    iVar14 = (int)(uVar8 + uVar18) / 2;
    if (param_4 == 0) {
      iVar20 = iVar5 + 3;
      if (-1 < iVar5) {
        iVar20 = iVar5;
      }
      uVar8 = uVar8 - (iVar20 >> 2);
    }
  }
  else {
    iVar20 = *(int *)(this + 0x5c);
    uVar8 = iVar5 - *(int *)(this + 0x60);
    uVar18 = iVar5 - iVar20;
    iVar14 = (int)(uVar8 + uVar18) / 2;
    if (param_4 == 0) {
      iVar5 = iVar20 + 3;
      if (-1 < iVar20) {
        iVar5 = iVar20;
      }
      uVar18 = uVar18 + (iVar5 >> 2);
    }
  }
  iVar5 = *(int *)(this + 0xa8);
  if ((int)uVar8 < 0) {
    uVar8 = 0;
  }
  uVar9 = iVar5 - 1U;
  if ((int)uVar8 < iVar5) {
    uVar9 = uVar8;
  }
  uVar6 = (ulong)uVar9;
  if ((int)uVar18 < 0) {
    uVar18 = 0;
  }
  uVar8 = iVar5 - 1U;
  if ((int)uVar18 < iVar5) {
    uVar8 = uVar18;
  }
  if ((int)uVar9 <= (int)uVar8) {
    uVar11 = -(ulong)(uVar9 >> 0x1f) & 0xfffffffc00000000 | uVar6 << 2;
    uVar18 = uVar8 + 1;
    lVar7 = 0;
    do {
      while( true ) {
        uVar9 = (uint)uVar6;
        iVar5 = *(int *)((long)__s + lVar7 + uVar11);
        if (iVar14 <= (int)uVar9) break;
        if (iVar5 <= iVar19) goto LAB_00118228;
LAB_00118230:
        uVar6 = (ulong)(uVar9 + 1);
        lVar7 = lVar7 + 4;
        if (uVar9 + 1 == uVar18) goto LAB_00118264;
      }
      if (iVar5 < iVar19) {
LAB_00118228:
        iVar19 = *(int *)((long)puVar13 + lVar7 + uVar11);
        uVar8 = uVar9;
        goto LAB_00118230;
      }
      uVar6 = (ulong)(uVar9 + 1);
      lVar7 = lVar7 + 4;
    } while (uVar9 + 1 != uVar18);
  }
LAB_00118264:
  operator_delete__(__s);
  return uVar8;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::SetDigitBoundingBox
 * Address : 00118320
 * Size    : 0xfc8 bytes
 * ------------------------------------------------------------------ */

/* WARNING: Type propagation algorithm not settling */
/* DigitSegment::SetDigitBoundingBox(float, int) */

void __thiscall DigitSegment::SetDigitBoundingBox(DigitSegment *this,float param_1,int param_2)

{
  int iVar1;
  int iVar2;
  uint uVar3;
  char cVar4;
  uint uVar5;
  uint uVar6;
  DigitSegment *pDVar7;
  void *pvVar8;
  ulong uVar9;
  int iVar10;
  DigitSegment *pDVar11;
  long lVar12;
  DigitSegment *pDVar13;
  DigitSegment *pDVar14;
  DigitSegment *pDVar15;
  int iVar16;
  uint uVar17;
  DigitSegment *pDVar18;
  DigitSegment *pDVar19;
  undefined8 *puVar20;
  undefined8 *puVar21;
  void *local_90;
  ulong uStack_88;
  int local_80;
  void *local_70;
  ulong uStack_68;
  uint local_60;
  uint local_48;
  undefined4 uStack_44;
  uint local_40;
  int local_3c;
  undefined4 local_38;
  uint local_30;
  undefined4 uStack_2c;
  uint local_28;
  int local_24;
  uint local_20;
  void *local_18;
  ulong local_10;
  undefined8 local_8;
  
  pDVar13 = this + 8;
  pDVar11 = *(DigitSegment **)pDVar13;
  if (pDVar11 != pDVar13) {
    lVar12 = 0;
    do {
      pDVar11 = *(DigitSegment **)pDVar11;
      lVar12 = lVar12 + 1;
    } while (pDVar13 != pDVar11);
    if (lVar12 != 1) {
      pDVar11 = this + 0x18;
      pDVar13 = *(DigitSegment **)pDVar11;
      if (pDVar11 == pDVar13) {
        __android_log_print(3,"libdigit_ocr","begin SetDigitBoundingBox first step ,digitSize=%d\n",
                            0);
      }
      else {
        lVar12 = 0;
        pDVar7 = pDVar13;
        do {
          pDVar7 = *(DigitSegment **)pDVar7;
          lVar12 = lVar12 + 1;
        } while (pDVar11 != pDVar7);
        __android_log_print(3,"libdigit_ocr","begin SetDigitBoundingBox first step ,digitSize=%d\n",
                            lVar12);
        do {
          while ((iVar10 = *(int *)(pDVar13 + 0x1c), iVar10 != 0 ||
                 (cVar4 = CheckIsADigit(this,*(int *)(pDVar13 + 0x10),*(int *)(pDVar13 + 0x18)),
                 cVar4 == '\0'))) {
            pDVar13 = *(DigitSegment **)pDVar13;
            if (pDVar11 == pDVar13) goto LAB_0011845c;
          }
          local_18 = (void *)(ulong)*(uint *)(pDVar13 + 0x10);
          local_10 = (ulong)*(uint *)(pDVar13 + 0x18);
          local_8 = local_8 & 0xffffffff00000000;
          local_90 = local_18;
          uStack_88 = local_10;
          local_80 = iVar10;
          FindIntersectRect(&local_70,this,&local_90,0);
          *(void **)(pDVar13 + 0x10) = local_70;
          *(ulong *)(pDVar13 + 0x18) = uStack_68;
          *(uint *)(pDVar13 + 0x20) = local_60;
          pDVar13 = *(DigitSegment **)pDVar13;
        } while (pDVar11 != pDVar13);
      }
LAB_0011845c:
      lVar12 = 0;
      for (pDVar13 = *(DigitSegment **)(this + 0x18); pDVar11 != pDVar13;
          pDVar13 = *(DigitSegment **)pDVar13) {
        lVar12 = lVar12 + 1;
      }
      __android_log_print(3,"libdigit_ocr","begin SetDigitBoundingBox second step ,digitSize=%d\n",
                          lVar12);
      pDVar7 = DAT_00119308;
      pDVar13 = *(DigitSegment **)(this + 0x18);
      local_48 = 0;
      uStack_44 = 0;
      local_40 = 0;
      local_3c = 0;
      local_38 = 0;
      if (pDVar11 != *(DigitSegment **)pDVar13) {
        pDVar18 = *(DigitSegment **)pDVar13;
        do {
          if (*(int *)(pDVar13 + 0x1c) < 1) {
LAB_001189b8:
            pDVar13 = *(DigitSegment **)pDVar13;
            pDVar15 = *(DigitSegment **)pDVar18;
          }
          else {
            pDVar19 = pDVar18 + 0x10;
            iVar10 = *(int *)(pDVar18 + 0x1c);
            if (iVar10 != 0) goto LAB_001189b8;
            uVar17 = *(uint *)(pDVar18 + 0x18);
            uVar5 = *(uint *)(pDVar18 + 0x10);
            iVar2 = *(int *)(this + 0x60);
            iVar1 = (uVar17 - uVar5) + 1;
            if (iVar2 < iVar1) {
              iVar16 = *(int *)(this + 0x5c);
              if ((double)iVar1 <= (double)iVar2 + (double)iVar16 * 0.5) {
                local_18 = (void *)(ulong)uVar5;
                local_10 = (ulong)uVar17;
                local_8 = (ulong)local_8._4_4_ << 0x20;
                local_90 = local_18;
                uStack_88 = local_10;
                local_80 = iVar10;
                FindIntersectRect(&local_70,this,&local_90,0);
                pDVar13 = *(DigitSegment **)pDVar13;
                pDVar15 = *(DigitSegment **)pDVar18;
                *(void **)pDVar19 = local_70;
                *(ulong *)(pDVar18 + 0x18) = uStack_68;
                *(uint *)(pDVar18 + 0x20) = local_60;
              }
              else {
                if (iVar1 < iVar16) goto LAB_00118b6c;
                local_70 = *(void **)pDVar19;
                uStack_68 = *(ulong *)(pDVar18 + 0x18);
                local_18 = (void *)0x0;
                local_60 = *(uint *)(pDVar18 + 0x20);
                local_10 = 0;
                local_8 = 0;
                uVar5 = FindSegPointFromHistogram(pDVar7,param_1,this,&local_70,0,&local_18);
                if (uVar5 == 0xffffffff) {
                  local_70 = *(void **)pDVar19;
                  uStack_68 = *(ulong *)(pDVar18 + 0x18);
                  local_60 = *(uint *)(pDVar18 + 0x20);
                  uVar5 = FindSegPointFromHistogram
                                    ((DigitSegment *)0x3fe0000000000000,param_1,this,&local_70,0,
                                     &local_18);
                  if (uVar5 == 0xffffffff) {
                    local_70 = *(void **)pDVar19;
                    uStack_68 = *(ulong *)(pDVar18 + 0x18);
                    local_60 = *(uint *)(pDVar18 + 0x20);
                    uVar5 = getMostDigitEdge(this,&local_70,0,param_2);
                  }
                }
                local_40 = uVar5;
                local_48 = *(uint *)pDVar19;
                uStack_2c = 0;
                local_24 = 0;
                local_70 = (void *)(ulong)local_48;
                uStack_68 = (ulong)local_40;
                local_20 = 0;
                local_60 = 0;
                local_30 = local_48;
                local_28 = local_40;
                FindIntersectRect(&local_48,this,&local_70);
                pDVar15 = pDVar18;
                if ((local_3c < 1) || ((int)(local_40 - local_48) < *(int *)(this + 0x60) / 2)) {
                  iVar10 = *(int *)(this + 0xa8);
                  uVar5 = local_40;
                  if ((int)local_40 < 0) {
                    uVar5 = 0;
                  }
                  uVar17 = iVar10 - 1;
                  if ((int)uVar5 < iVar10) {
                    uVar17 = uVar5;
                  }
                  uVar5 = *(int *)(this + 0xac) - 1;
                  if (1 < *(int *)(this + 0xac)) {
                    uVar5 = 1;
                  }
                  local_40 = local_40 + 1;
                  if ((((int)uVar5 < *(int *)(this + 0x5c) / 3) &&
                      (uVar6 = uVar17 - uVar5, (int)local_48 < (int)uVar6 && -1 < (int)uVar6)) &&
                     ((int)uVar6 < iVar10)) {
                    uVar5 = ~uVar5 + uVar17;
                    while ((uVar3 = uVar6, uVar6 = uVar5,
                           uVar9 = -(ulong)(uVar3 >> 0x1f) & 0xfffffffc00000000 | (ulong)uVar3 << 2,
                           *(int *)(*(long *)this + uVar9) < *(int *)(*(long *)this + uVar9 + 4) &&
                           (local_40 = uVar3, uVar6 != uVar17 - *(int *)(this + 0x5c) / 3))) {
                      if (((int)uVar6 <= (int)local_48 || (int)uVar6 < 0) ||
                         (uVar5 = uVar6 - 1, iVar10 <= (int)uVar6)) break;
                    }
                  }
                  *(uint *)pDVar19 = local_40;
                  local_40 = uVar17;
                }
                else {
                  *(uint *)pDVar19 = local_40 + *(int *)(this + 0x6c);
                  pvVar8 = operator_new(0x28);
                  *(undefined4 *)((long)pvVar8 + 0x20) = local_38;
                  *(ulong *)((long)pvVar8 + 0x10) = CONCAT44(uStack_44,local_48);
                  *(ulong *)((long)pvVar8 + 0x18) = CONCAT44(local_3c,local_40);
                  FUN_00139e14(pvVar8,pDVar18);
                  pDVar13 = *(DigitSegment **)pDVar13;
                  cVar4 = CheckIsADigit(this,*(uint *)pDVar19,*(int *)(pDVar18 + 0x18));
                  if (cVar4 != '\0') {
                    local_28 = *(uint *)(pDVar18 + 0x18);
                    local_30 = *(uint *)pDVar19;
                    uStack_2c = 0;
                    local_24 = 0;
                    local_20 = 0;
                    local_90 = (void *)(ulong)local_30;
                    uStack_88 = (ulong)local_28;
                    local_80 = 0;
                    FindIntersectRect(&local_70,this,&local_90);
                    pDVar15 = *(DigitSegment **)pDVar18;
                    pDVar13 = *(DigitSegment **)pDVar13;
                    *(void **)pDVar19 = local_70;
                    *(ulong *)(pDVar18 + 0x18) = uStack_68;
                    *(uint *)(pDVar18 + 0x20) = local_60;
                  }
                }
                if (local_18 != (void *)0x0) {
                  operator_delete(local_18);
                }
              }
            }
            else {
              iVar16 = *(int *)(this + 0x5c);
              if (iVar1 < iVar16) {
LAB_00118b6c:
                pDVar15 = *(DigitSegment **)pDVar18;
                uVar6 = uVar5 + iVar2;
                local_48 = uVar5;
                if ((pDVar11 != pDVar15) && (*(int *)(pDVar15 + 0x1c) < 1)) {
                  iVar10 = 0;
                  pDVar14 = pDVar15;
                  local_40 = uVar6;
                  do {
                    if ((int)*(uint *)(pDVar14 + 0x18) < (int)(uVar6 + iVar2 / 10)) {
                      iVar10 = iVar10 + 1;
                      local_40 = *(uint *)(pDVar14 + 0x18);
                    }
                    pDVar14 = *(DigitSegment **)pDVar14;
                  } while ((pDVar11 != pDVar14) && (*(int *)(pDVar14 + 0x1c) < 1));
                  if (iVar10 != 0) {
                    local_18 = (void *)(ulong)uVar5;
                    local_10 = (ulong)local_40;
                    local_8 = (ulong)local_8._4_4_ << 0x20;
                    local_60 = 0;
                    local_70 = local_18;
                    uStack_68 = local_10;
                    FindIntersectRect(&local_30,this,&local_70);
                    if (local_24 < 0) {
                      pDVar15 = *(DigitSegment **)pDVar18;
                      FUN_00139e30(pDVar18);
                      operator_delete(pDVar18);
                    }
                    else {
                      *(uint *)(pDVar18 + 0x20) = local_20;
                      *(ulong *)pDVar19 = CONCAT44(uStack_2c,local_30);
                      *(ulong *)(pDVar18 + 0x18) = CONCAT44(local_24,local_28);
                      puVar21 = *(undefined8 **)pDVar18;
                      do {
                        puVar20 = (undefined8 *)*puVar21;
                        FUN_00139e30(puVar21);
                        operator_delete(puVar21);
                        iVar10 = iVar10 + -1;
                        puVar21 = puVar20;
                      } while (iVar10 != 0);
                      pDVar15 = *(DigitSegment **)pDVar18;
                      pDVar13 = *(DigitSegment **)pDVar13;
                    }
                    goto LAB_001186bc;
                  }
                }
                if (iVar1 < iVar16 / 5) {
                  local_40 = uVar6;
                  FUN_00139e30(pDVar18);
                  operator_delete(pDVar18);
                  goto LAB_001186bc;
                }
                if (param_2 == 1) {
                  if (pDVar11 == pDVar15) {
                    iVar10 = *(int *)(this + 0xa8) + -1;
                  }
                  else {
                    iVar10 = *(int *)(pDVar15 + 0x10);
                  }
                  uVar5 = (int)(*(uint *)pDVar19 + *(int *)(pDVar18 + 0x18)) / 2 - iVar16 / 2;
                  if ((int)uVar5 < *(int *)(pDVar13 + 0x18)) {
                    uVar5 = *(int *)(pDVar13 + 0x18) + 1;
                  }
                  *(uint *)pDVar19 = uVar5;
                  uVar17 = uVar5 + (iVar2 + iVar16) / 2;
                  if (iVar10 < (int)uVar17) {
                    uVar17 = iVar10 - 1;
                  }
                  *(uint *)(pDVar18 + 0x18) = uVar17;
                }
                local_80 = 0;
                iVar10 = local_80;
                local_40 = uVar6;
              }
              local_80 = iVar10;
              local_8 = (ulong)local_8._4_4_ << 0x20;
              local_10 = (ulong)uVar17;
              local_18 = (void *)(ulong)uVar5;
              local_90 = local_18;
              uStack_88 = local_10;
              FindIntersectRect(&local_70,this,&local_90,local_80);
              pDVar15 = *(DigitSegment **)pDVar18;
              pDVar13 = *(DigitSegment **)pDVar13;
              *(void **)pDVar19 = local_70;
              *(ulong *)(pDVar18 + 0x18) = uStack_68;
              *(uint *)(pDVar18 + 0x20) = local_60;
            }
          }
LAB_001186bc:
          pDVar18 = pDVar15;
        } while (pDVar11 != pDVar15);
        pDVar13 = *(DigitSegment **)(this + 0x18);
      }
      pDVar7 = *(DigitSegment **)(this + 0x20);
      if (pDVar7 != pDVar13) {
        lVar12 = 0;
        for (; pDVar11 != pDVar13; pDVar13 = *(DigitSegment **)pDVar13) {
          lVar12 = lVar12 + 1;
        }
        pDVar13 = pDVar7 + 0x10;
        __android_log_print(3,"libdigit_ocr","begin SetDigitBoundingBox third step ,digitSize=%d\n",
                            lVar12);
        pDVar18 = DAT_00119308;
LAB_00118728:
        uVar5 = *(uint *)(pDVar13 + 0xc);
        pDVar19 = pDVar7;
        while (pDVar7 = pDVar19, uVar5 != 0) {
          pDVar15 = *(DigitSegment **)(this + 0x18);
joined_r0x00118d74:
          if (pDVar15 == pDVar19) goto LAB_0011892c;
          pDVar19 = *(DigitSegment **)(pDVar19 + 8);
          pDVar13 = pDVar19 + 0x10;
          uVar5 = *(uint *)(pDVar19 + 0x1c);
        }
        uVar17 = *(uint *)(pDVar13 + 8);
        uVar5 = *(uint *)pDVar13;
        iVar1 = *(int *)(this + 0x60);
        iVar2 = *(int *)(this + 0x5c);
        iVar10 = (uVar17 - uVar5) + 1;
        if (iVar10 <= iVar1) {
          if (iVar2 <= iVar10) goto LAB_001189e8;
LAB_00118a84:
          pDVar15 = *(DigitSegment **)(this + 0x18);
          uVar6 = uVar17 - iVar1;
          local_8._4_4_ = (uint)(local_8 >> 0x20);
          local_40 = uVar17;
          if ((pDVar15 == pDVar19) ||
             (pDVar14 = *(DigitSegment **)(pDVar19 + 8), 0 < *(int *)(pDVar14 + 0x1c))) {
LAB_00118b0c:
            if (iVar2 / 5 <= iVar10) {
              if (param_2 == 1) {
                if (pDVar15 == pDVar19) {
                  pDVar15 = *(DigitSegment **)pDVar19;
                  iVar10 = 0;
                  iVar16 = *(int *)(this + 0xa8);
                  if (pDVar11 != pDVar15) goto LAB_00118fdc;
LAB_001192b4:
                  iVar16 = iVar16 + -1;
                }
                else {
                  pDVar15 = *(DigitSegment **)pDVar19;
                  iVar10 = *(int *)(*(long *)(pDVar19 + 8) + 0x18);
                  iVar16 = *(int *)(this + 0xa8);
                  if (pDVar11 == pDVar15) goto LAB_001192b4;
LAB_00118fdc:
                  iVar16 = *(int *)(pDVar15 + 0x10);
                }
                uVar5 = (int)(uVar17 + uVar5) / 2 - iVar2 / 2;
                if ((int)uVar5 < iVar10) {
                  uVar5 = iVar10 + 1;
                }
                *(uint *)pDVar13 = uVar5;
                uVar17 = uVar5 + (iVar1 + iVar2) / 2;
                if (iVar16 < (int)uVar17) {
                  uVar17 = iVar16 - 1;
                }
                *(uint *)(pDVar13 + 8) = uVar17;
              }
              local_8 = (ulong)local_8._4_4_;
              local_48 = uVar6;
LAB_001189fc:
              local_10 = (ulong)uVar17;
              local_18 = (void *)(ulong)uVar5;
              local_8 = local_8 << 0x20;
              local_80 = 0;
              local_90 = local_18;
              uStack_88 = local_10;
              FindIntersectRect(&local_70,this,&local_90,0);
              pDVar15 = *(DigitSegment **)(this + 0x18);
              *(void **)pDVar13 = local_70;
              *(ulong *)(pDVar13 + 8) = uStack_68;
              *(uint *)(pDVar13 + 0x10) = local_60;
              goto joined_r0x00118d74;
            }
            pDVar13 = *(DigitSegment **)pDVar19;
            local_48 = uVar6;
            FUN_00139e30(pDVar19);
            operator_delete(pDVar19);
            pDVar7 = *(DigitSegment **)(this + 0x18);
            if (pDVar7 == pDVar13) goto LAB_0011892c;
            pDVar7 = *(DigitSegment **)(pDVar13 + 8);
            pDVar13 = pDVar7 + 0x10;
          }
          else {
            iVar16 = 0;
            local_48 = uVar6;
            do {
              if ((int)(uVar6 - iVar1 / 10) < (int)*(uint *)(pDVar14 + 0x10)) {
                iVar16 = iVar16 + 1;
                local_48 = *(uint *)(pDVar14 + 0x10);
              }
            } while ((pDVar15 != pDVar14) &&
                    (pDVar14 = *(DigitSegment **)(pDVar14 + 8), *(int *)(pDVar14 + 0x1c) < 1));
            if (iVar16 == 0) goto LAB_00118b0c;
            local_18 = (void *)(ulong)local_48;
            local_10 = (ulong)uVar17;
            local_8 = (ulong)local_8._4_4_ << 0x20;
            local_60 = 0;
            local_70 = local_18;
            uStack_68 = local_10;
            FindIntersectRect(&local_30,this,&local_70);
            if (-1 < local_24) {
              puVar21 = *(undefined8 **)(pDVar19 + 8);
              *(uint *)(pDVar13 + 0x10) = local_20;
              *(ulong *)pDVar13 = CONCAT44(uStack_2c,local_30);
              *(ulong *)(pDVar13 + 8) = CONCAT44(local_24,local_28);
              do {
                pDVar7 = (DigitSegment *)*puVar21;
                FUN_00139e30(puVar21);
                operator_delete(puVar21);
                pDVar13 = *(DigitSegment **)(this + 0x18);
                pDVar15 = pDVar7;
                if (pDVar13 == pDVar7) break;
                iVar16 = iVar16 + -1;
                puVar21 = *(undefined8 **)(pDVar7 + 8);
                pDVar7 = pDVar13;
                pDVar15 = pDVar13;
              } while (iVar16 != 0);
              goto joined_r0x00118d74;
            }
            lVar12 = *(long *)pDVar19;
            FUN_00139e30(pDVar19);
            operator_delete(pDVar19);
            pDVar7 = *(DigitSegment **)(lVar12 + 8);
            pDVar13 = pDVar7 + 0x10;
          }
          goto LAB_00118728;
        }
        if (iVar10 <= iVar1 + iVar2 / 2) {
          __android_log_print(3,"libdigit_ocr","begin  third step a little big\n");
          uVar17 = *(uint *)(pDVar13 + 8);
          uVar5 = *(uint *)pDVar13;
LAB_001189e8:
          local_8 = local_8 >> 0x20;
          goto LAB_001189fc;
        }
        if (iVar10 < iVar2) goto LAB_00118a84;
        local_70 = *(void **)pDVar13;
        uStack_68 = *(ulong *)(pDVar13 + 8);
        local_18 = (void *)0x0;
        local_60 = *(uint *)(pDVar13 + 0x10);
        local_10 = 0;
        local_8 = 0;
        uVar5 = FindSegPointFromHistogram(pDVar18,param_1,this,&local_70,1,&local_18);
        if (uVar5 == 0xffffffff) {
          local_70 = *(void **)pDVar13;
          uStack_68 = *(ulong *)(pDVar13 + 8);
          local_60 = *(uint *)(pDVar13 + 0x10);
          uVar5 = FindSegPointFromHistogram
                            ((DigitSegment *)0x3fe0000000000000,param_1,this,&local_70,1,&local_18);
          if (uVar5 == 0xffffffff) {
            local_70 = *(void **)pDVar13;
            uStack_68 = *(ulong *)(pDVar13 + 8);
            local_60 = *(uint *)(pDVar13 + 0x10);
            uVar5 = getMostDigitEdge(this,&local_70,1,param_2);
          }
        }
        local_48 = uVar5;
        local_40 = *(uint *)(pDVar13 + 8);
        uStack_2c = 0;
        local_24 = 0;
        local_70 = (void *)(ulong)local_48;
        uStack_68 = (ulong)local_40;
        local_20 = 0;
        local_60 = 0;
        local_30 = local_48;
        local_28 = local_40;
        FindIntersectRect(&local_48,this,&local_70);
        if ((local_3c < 1) || ((int)(local_40 - local_48) < *(int *)(this + 0x60) / 2)) {
          iVar10 = *(int *)(this + 0xa8);
          uVar5 = local_48;
          if ((int)local_48 < 0) {
            uVar5 = 0;
          }
          uVar17 = iVar10 - 1;
          if ((int)uVar5 < iVar10) {
            uVar17 = uVar5;
          }
          iVar1 = *(int *)(this + 0xac) + -1;
          if (1 < *(int *)(this + 0xac)) {
            iVar1 = 1;
          }
          local_48 = local_48 - 1;
          if (((iVar1 < *(int *)(this + 0x5c) / 3) &&
              (uVar5 = iVar1 + uVar17, (int)uVar5 < (int)local_40 && -1 < (int)uVar5)) &&
             ((int)uVar5 < iVar10)) {
            uVar6 = uVar17 + iVar1 + 1;
            while ((uVar3 = uVar5, uVar5 = uVar6,
                   uVar9 = -(ulong)(uVar3 >> 0x1f) & 0xfffffffc00000000 | (ulong)uVar3 << 2,
                   *(int *)(*(long *)this + uVar9) < *(int *)(*(long *)this + uVar9 + -4) &&
                   (local_48 = uVar3, uVar5 != uVar17 + *(int *)(this + 0x5c) / 3))) {
              if (((int)local_40 <= (int)uVar5 || (int)uVar5 < 0) ||
                 (uVar6 = uVar5 + 1, iVar10 <= (int)uVar5)) break;
            }
          }
          *(uint *)(pDVar13 + 8) = local_48;
          local_48 = uVar17;
        }
        else {
          lVar12 = *(long *)pDVar19;
          *(uint *)(pDVar13 + 8) = local_48 - *(int *)(this + 0x6c);
          pvVar8 = operator_new(0x28);
          *(undefined4 *)((long)pvVar8 + 0x20) = local_38;
          *(ulong *)((long)pvVar8 + 0x10) = CONCAT44(uStack_44,local_48);
          *(ulong *)((long)pvVar8 + 0x18) = CONCAT44(local_3c,local_40);
          FUN_00139e14(pvVar8,lVar12);
          pDVar7 = *(DigitSegment **)(*(long *)(lVar12 + 8) + 8);
          pDVar13 = pDVar7 + 0x10;
          cVar4 = CheckIsADigit(this,*(int *)(pDVar7 + 0x10),*(int *)(pDVar7 + 0x18));
          if (cVar4 != '\0') {
            local_28 = *(uint *)(pDVar7 + 0x18);
            local_30 = *(uint *)pDVar13;
            uStack_2c = 0;
            local_24 = 0;
            local_20 = 0;
            local_90 = (void *)(ulong)local_30;
            uStack_88 = (ulong)local_28;
            local_80 = 0;
            FindIntersectRect(&local_70,this,&local_90);
            pDVar19 = *(DigitSegment **)(this + 0x18);
            *(void **)pDVar13 = local_70;
            *(ulong *)(pDVar7 + 0x18) = uStack_68;
            *(uint *)(pDVar7 + 0x20) = local_60;
            if (pDVar19 == pDVar7) {
              if (local_18 != (void *)0x0) {
                operator_delete(local_18);
                pDVar7 = *(DigitSegment **)(this + 0x18);
              }
LAB_0011892c:
              lVar12 = 0;
              for (; pDVar11 != pDVar7; pDVar7 = *(DigitSegment **)pDVar7) {
                lVar12 = lVar12 + 1;
              }
              __android_log_print(3,"libdigit_ocr",
                                  "begin SetDigitBoundingBox forth step ,digitSize=%d\n",lVar12);
              pDVar13 = *(DigitSegment **)(this + 0x18);
              if (pDVar11 == pDVar13) {
                return;
              }
              lVar12 = 0;
              pDVar7 = pDVar13;
              do {
                pDVar7 = *(DigitSegment **)pDVar7;
                lVar12 = lVar12 + 1;
              } while (pDVar11 != pDVar7);
              if (lVar12 == 1) {
                return;
              }
              iVar10 = 0;
              iVar1 = *(int *)(pDVar13 + 0x1c);
              pDVar7 = pDVar13;
              while (pDVar18 = pDVar7, iVar1 < 1) {
                pDVar7 = *(DigitSegment **)pDVar7;
                iVar10 = iVar10 + 1;
                pDVar18 = pDVar11;
                if (pDVar11 == pDVar7) break;
                iVar1 = *(int *)(pDVar7 + 0x1c);
              }
              pDVar7 = *(DigitSegment **)(this + 0x20);
              iVar1 = *(int *)(pDVar7 + 0x1c);
              while ((iVar1 < 1 && (iVar10 = iVar10 + 1, pDVar13 != pDVar7))) {
                pDVar7 = *(DigitSegment **)(pDVar7 + 8);
                iVar1 = *(int *)(pDVar7 + 0x1c);
              }
              uVar9 = 0;
              do {
                pDVar13 = *(DigitSegment **)pDVar13;
                uVar9 = uVar9 + 1;
              } while (pDVar11 != pDVar13);
              if (uVar9 <= (ulong)(long)iVar10) {
                return;
              }
              if (pDVar7 == pDVar18) {
                return;
              }
              puVar21 = *(undefined8 **)pDVar18;
              do {
                if (*(int *)(pDVar18 + 0x1c) < 0) {
                  if (*(int *)(puVar21 + 2) < *(int *)(pDVar18 + 0x18)) {
                    *(int *)(pDVar18 + 0x18) = *(int *)(puVar21 + 2);
                  }
                  local_70 = *(void **)(pDVar18 + 0x10);
                  uStack_68 = *(ulong *)(pDVar18 + 0x18);
                  local_60 = *(uint *)(pDVar18 + 0x20);
                  FindIntersectRect(&local_18,this,&local_70,1);
                  *(void **)(pDVar18 + 0x10) = local_18;
                  *(ulong *)(pDVar18 + 0x18) = local_10;
                  *(undefined4 *)(pDVar18 + 0x20) = (undefined4)local_8;
                }
                pDVar18 = *(DigitSegment **)pDVar18;
                puVar21 = (undefined8 *)*puVar21;
              } while (pDVar18 != pDVar7);
              return;
            }
            pDVar7 = *(DigitSegment **)(pDVar7 + 8);
            pDVar13 = pDVar7 + 0x10;
          }
        }
        if (local_18 != (void *)0x0) {
          operator_delete(local_18);
        }
        goto LAB_00118728;
      }
    }
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::getDigitWidth
 * Address : 00119310
 * Size    : 0xc bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::getDigitWidth() */

undefined1  [16] __thiscall DigitSegment::getDigitWidth(DigitSegment *this)

{
  return *(undefined1 (*) [16])(this + 0x5c);
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::ProjectHistogramInX
 * Address : 0011931c
 * Size    : 0x84 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::ProjectHistogramInX() */

void __thiscall DigitSegment::ProjectHistogramInX(DigitSegment *this)

{
  int iVar1;
  int iVar2;
  int iVar3;
  int iVar4;
  int iVar5;
  long lVar6;
  long lVar7;
  int *piVar8;
  
  iVar4 = *(int *)(this + 0xa8);
  if (0 < iVar4) {
    lVar6 = 0;
    piVar8 = *(int **)(this + 200);
    iVar5 = 0;
    do {
      iVar3 = *piVar8;
      iVar1 = piVar8[1];
      if (iVar3 <= iVar1) {
        lVar7 = *(long *)(this + 0xa0);
        do {
          iVar2 = iVar3 * iVar4;
          iVar3 = iVar3 + 1;
          iVar2 = *(int *)(lVar7 + (long)(iVar5 + iVar2) * 4);
          if ((0 < iVar2) && (-1 < *(int *)(*(long *)(this + 0x30) + (long)iVar2 * 4))) {
            *(int *)(*(long *)this + lVar6) = *(int *)(*(long *)this + lVar6) + 1;
            iVar4 = *(int *)(this + 0xa8);
          }
        } while (iVar3 != iVar1 + 1);
      }
      iVar5 = iVar5 + 1;
      piVar8 = piVar8 + 4;
      lVar6 = lVar6 + 4;
    } while (iVar5 < iVar4);
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::getDigitMatBuffer
 * Address : 001193a0
 * Size    : 0x8 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::getDigitMatBuffer() */

DigitSegment * __thiscall DigitSegment::getDigitMatBuffer(DigitSegment *this)

{
  return this + 0xd0;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::Init
 * Address : 001193a8
 * Size    : 0x70 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::Init() */

void __thiscall DigitSegment::Init(DigitSegment *this)

{
  void *pvVar1;
  
  if (*(void **)(this + 0xb8) != (void *)0x0) {
    operator_delete__(*(void **)(this + 0xb8));
    *(undefined8 *)(this + 0xb8) = 0;
  }
  pvVar1 = operator_new__((long)(*(int *)(this + 0xa8) * *(int *)(this + 0xac)));
  *(void **)(this + 0xb8) = pvVar1;
  if (*(void **)(this + 0xc0) != (void *)0x0) {
    operator_delete__(*(void **)(this + 0xc0));
    *(undefined8 *)(this + 0xc0) = 0;
  }
  pvVar1 = operator_new__((long)(*(int *)(this + 0xa8) * *(int *)(this + 0xac)));
  *(void **)(this + 0xc0) = pvVar1;
  initSplitData(this);
  return;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::clear
 * Address : 00119418
 * Size    : 0x98 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::clear() */

void __thiscall DigitSegment::clear(DigitSegment *this)

{
  if (*(void **)(this + 0xb8) != (void *)0x0) {
    operator_delete__(*(void **)(this + 0xb8));
  }
  *(undefined8 *)(this + 0xb8) = 0;
  if (*(void **)(this + 0xc0) != (void *)0x0) {
    operator_delete__(*(void **)(this + 0xc0));
  }
  *(undefined8 *)(this + 0xc0) = 0;
  if (*(void **)(this + 0xa0) != (void *)0x0) {
    operator_delete__(*(void **)(this + 0xa0));
  }
  *(undefined8 *)(this + 0xa0) = 0;
  if (*(void **)this != (void *)0x0) {
    operator_delete__(*(void **)this);
  }
  *(undefined8 *)this = 0;
  if (*(void **)(this + 0x38) != (void *)0x0) {
    operator_delete__(*(void **)(this + 0x38));
  }
  *(undefined8 *)(this + 0x38) = 0;
  if (*(void **)(this + 0x28) != (void *)0x0) {
    operator_delete__(*(void **)(this + 0x28));
  }
  *(undefined8 *)(this + 0x28) = 0;
  if (*(void **)(this + 0x30) != (void *)0x0) {
    operator_delete__(*(void **)(this + 0x30));
  }
  *(undefined8 *)(this + 0x30) = 0;
  if (*(void **)(this + 200) != (void *)0x0) {
    operator_delete__(*(void **)(this + 200));
  }
  *(undefined8 *)(this + 200) = 0;
  return;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::resizeMapBuffer
 * Address : 001194b0
 * Size    : 0x288 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::resizeMapBuffer(RectRegion) */

undefined8 * DigitSegment::resizeMapBuffer(undefined8 *param_1,long param_2,int *param_3)

{
  uchar *puVar1;
  int iVar2;
  int iVar3;
  int iVar4;
  uchar *puVar5;
  int iVar6;
  int iVar7;
  int iVar8;
  undefined8 *puVar9;
  uchar *__s;
  uchar *puVar10;
  uchar *__s_00;
  void *pvVar11;
  void *__dest;
  int iVar12;
  long lVar13;
  uchar *puVar14;
  int iVar15;
  undefined8 *puVar16;
  undefined8 *puVar17;
  undefined8 uVar18;
  
  iVar15 = param_3[1];
  iVar6 = param_3[3];
  iVar7 = *param_3;
  iVar8 = param_3[2];
  iVar2 = (iVar6 - iVar15) + 1;
  iVar3 = (iVar8 - iVar7) + 1;
  if ((iVar2 < 1) || (iVar3 < 1)) {
    __android_log_print(6,"libdigit_ocr","memory new error, w<0,h<0");
    puVar16 = *(undefined8 **)(param_2 + 0xd8);
    puVar17 = *(undefined8 **)(param_2 + 0xd0);
    for (puVar9 = puVar17; puVar9 != puVar16; puVar9 = puVar9 + 2) {
      if ((void *)*puVar9 != (void *)0x0) {
        operator_delete__((void *)*puVar9);
      }
    }
    *(undefined8 **)(param_2 + 0xd8) = puVar17;
    pvVar11 = operator_new__(0);
    *(undefined4 *)(param_1 + 1) = 0;
    *param_1 = pvVar11;
    *(undefined4 *)((long)param_1 + 0xc) = 0;
    return param_1;
  }
  __s = operator_new__((long)(iVar3 * iVar2));
  memset(__s,0,(long)(iVar3 * iVar2));
  if (iVar15 <= iVar6) {
    puVar10 = __s;
    do {
      puVar14 = puVar10;
      iVar12 = iVar7;
      if (iVar7 <= iVar8) {
        do {
          iVar4 = iVar12 + 1;
          *puVar14 = *(uchar *)(*(long *)(param_2 + 0xc0) +
                               (long)(iVar12 + iVar15 * *(int *)(param_2 + 0xa8)));
          puVar14 = puVar14 + 1;
          iVar12 = iVar4;
        } while (iVar4 != iVar8 + 1);
      }
      iVar15 = iVar15 + 1;
      puVar10 = puVar10 + iVar3;
    } while (iVar15 != iVar6 + 1);
  }
  __s_00 = operator_new__(0x21c);
  memset(__s_00,0,0x21c);
  resizeImgWithNearest(__s_00,0x12,0x1e,__s,iVar3,iVar2);
  pvVar11 = operator_new__(0x280);
  memset(pvVar11,0,0x280);
  puVar14 = __s_00;
  puVar10 = (uchar *)((long)pvVar11 + 0x15);
  do {
    while ((puVar14 < puVar10 + 0x10 && (puVar10 < puVar14 + 0x10))) {
      lVar13 = 0;
      do {
        puVar5 = puVar10 + lVar13;
        puVar1 = puVar14 + lVar13;
        lVar13 = lVar13 + 1;
        *puVar5 = *puVar1;
      } while (lVar13 != 0x12);
      puVar10 = puVar10 + 0x14;
      puVar14 = puVar14 + 0x12;
      if (puVar10 == (uchar *)((long)pvVar11 + 0x26d)) goto LAB_00119650;
    }
    uVar18 = *(undefined8 *)puVar14;
    *(undefined8 *)(puVar10 + 8) = *(undefined8 *)(puVar14 + 8);
    *(undefined8 *)puVar10 = uVar18;
    puVar1 = puVar10 + 0x14;
    puVar10[0x10] = puVar14[0x10];
    puVar10[0x11] = puVar14[0x11];
    puVar14 = puVar14 + 0x12;
    puVar10 = puVar1;
  } while (puVar1 != (uchar *)((long)pvVar11 + 0x26d));
LAB_00119650:
  *(undefined4 *)(param_1 + 1) = 0x14;
  *(undefined4 *)((long)param_1 + 0xc) = 0x20;
  __dest = operator_new__(0x280);
  *param_1 = __dest;
  memcpy(__dest,pvVar11,(long)(*(int *)(param_1 + 1) * *(int *)((long)param_1 + 0xc)));
  operator_delete__(pvVar11);
  operator_delete__(__s_00);
  operator_delete__(__s);
  return param_1;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::updateDigitVector
 * Address : 0011976c
 * Size    : 0xb0 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::updateDigitVector() */

void __thiscall DigitSegment::updateDigitVector(DigitSegment *this)

{
  DigitSegment *pDVar1;
  undefined4 uVar2;
  undefined4 uVar3;
  void *pvVar4;
  DigitSegment *pDVar5;
  DigitSegment *pDVar6;
  DigitSegment *pDVar7;
  
  pDVar1 = this + 0x18;
  pDVar7 = *(DigitSegment **)(this + 0x18);
  while (pDVar7 != pDVar1) {
    pDVar5 = *(DigitSegment **)pDVar7;
    operator_delete(pDVar7);
    pDVar7 = pDVar5;
  }
  *(DigitSegment **)(this + 0x18) = pDVar1;
  *(DigitSegment **)(this + 0x20) = pDVar1;
  pDVar5 = this + 8;
  pDVar7 = *(DigitSegment **)pDVar5;
  if (pDVar7 != pDVar5) {
    for (pDVar6 = *(DigitSegment **)pDVar7; pDVar5 != pDVar6; pDVar6 = *(DigitSegment **)pDVar6) {
      uVar2 = *(undefined4 *)(pDVar7 + 0x18);
      uVar3 = *(undefined4 *)(pDVar6 + 0x10);
      pvVar4 = operator_new(0x28);
      *(undefined4 *)((long)pvVar4 + 0x10) = uVar2;
      *(undefined4 *)((long)pvVar4 + 0x14) = 0;
      *(undefined4 *)((long)pvVar4 + 0x18) = uVar3;
      *(undefined4 *)((long)pvVar4 + 0x1c) = 0;
      *(undefined4 *)((long)pvVar4 + 0x20) = 0;
      FUN_00139e14(pvVar4,pDVar1);
      pDVar7 = *(DigitSegment **)pDVar7;
    }
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::setSVMOCRPtr
 * Address : 0011981c
 * Size    : 0x8 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::setSVMOCRPtr(CDigitalOCR&) */

void __thiscall DigitSegment::setSVMOCRPtr(DigitSegment *this,CDigitalOCR *param_1)

{
  *(CDigitalOCR **)(this + 0x100) = param_1;
  return;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::checkIsSegPointRegion
 * Address : 00119824
 * Size    : 0xec bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::checkIsSegPointRegion(int, int, int) */

bool __thiscall
DigitSegment::checkIsSegPointRegion(DigitSegment *this,int param_1,int param_2,int param_3)

{
  int iVar1;
  uint uVar2;
  int iVar3;
  uint uVar4;
  ulong uVar5;
  long lVar6;
  undefined8 *puVar7;
  int iVar8;
  float fVar9;
  int iVar10;
  int iVar11;
  int iVar12;
  
  uVar4 = param_2 - param_1;
  iVar1 = uVar4 + 1;
  if (param_2 < param_1) {
    fVar9 = 0.0;
    goto LAB_001198d4;
  }
  lVar6 = *(long *)this;
  uVar2 = (uVar4 - 3 >> 2) + 1;
  iVar3 = uVar2 * 4;
  if (uVar4 < 4) {
    iVar8 = 0;
LAB_00119884:
    uVar5 = -(ulong)((uint)param_1 >> 0x1f) & 0xfffffffc00000000 | (ulong)(uint)param_1 << 2;
    iVar8 = iVar8 + *(int *)(lVar6 + uVar5);
    if ((int)(param_1 + 1U) <= param_2) {
      lVar6 = lVar6 + uVar5;
      iVar8 = iVar8 + *(int *)(lVar6 + 4);
      if (((int)(param_1 + 2U) <= param_2) &&
         (iVar8 = iVar8 + *(int *)(lVar6 + 8), (int)(param_1 + 3U) <= param_2)) {
        iVar8 = iVar8 + *(int *)(lVar6 + 0xc);
      }
    }
  }
  else {
    iVar8 = 0;
    iVar10 = 0;
    iVar11 = 0;
    iVar12 = 0;
    uVar4 = 0;
    puVar7 = (undefined8 *)(lVar6 + (long)param_1 * 4);
    do {
      uVar4 = uVar4 + 1;
      iVar8 = iVar8 + (int)*puVar7;
      iVar10 = iVar10 + (int)((ulong)*puVar7 >> 0x20);
      iVar11 = iVar11 + (int)puVar7[1];
      iVar12 = iVar12 + (int)((ulong)puVar7[1] >> 0x20);
      puVar7 = puVar7 + 2;
    } while (uVar4 < uVar2);
    iVar8 = iVar8 + iVar10 + iVar11 + iVar12;
    param_1 = param_1 + iVar3;
    if (iVar1 != iVar3) goto LAB_00119884;
  }
  fVar9 = (float)iVar8;
LAB_001198d4:
  if (0 < iVar1) {
    return fVar9 / (float)iVar1 <= (float)param_3;
  }
  return false;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::FindSegPointDP
 * Address : 00119910
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::FindSegPointDP(int, int, std::vector<_RangeMinMax, std::allocator<_RangeMinMax> >&)
    */

undefined1  [16] DigitSegment::FindSegPointDP(int param_1,int param_2,vector *param_3)

{
  undefined1 auVar1 [16];
  undefined1 auStack_18 [24];
  
  auVar1._8_4_ = param_2;
  auVar1._0_8_ = auStack_18;
  auVar1._12_4_ = 0;
  return auVar1;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::DigitSegment
 * Address : 00119988
 * Size    : 0xd4 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::DigitSegment() */

void __thiscall DigitSegment::DigitSegment(DigitSegment *this)

{
  undefined *puVar1;
  
  *(DigitSegment **)(this + 8) = this + 8;
  *(DigitSegment **)(this + 0x10) = this + 8;
  *(DigitSegment **)(this + 0x18) = this + 0x18;
  *(DigitSegment **)(this + 0x20) = this + 0x18;
  *(undefined4 *)(this + 0x54) = 0;
  *(undefined4 *)(this + 0x58) = 0;
  *(undefined4 *)(this + 100) = 0;
  *(undefined4 *)(this + 0x68) = 0;
  *(undefined4 *)(this + 0x74) = 0;
  *(undefined4 *)(this + 0x78) = 0;
  *(undefined8 *)(this + 0xd0) = 0;
  *(undefined8 *)(this + 0xd8) = 0;
  *(undefined8 *)(this + 0xe0) = 0;
  puVar1 = PTR_DAT_001cae30;
  *(undefined8 *)(this + 0xa0) = 0;
  *(undefined **)(this + 0xf0) = puVar1 + 0x18;
  *(undefined8 *)this = 0;
  *(undefined8 *)(this + 0x38) = 0;
  *(undefined8 *)(this + 0x28) = 0;
  *(undefined8 *)(this + 0x30) = 0;
  *(undefined4 *)(this + 0x44) = 1000;
  *(undefined4 *)(this + 0xa8) = 0;
  *(undefined4 *)(this + 0xac) = 0;
  *(undefined8 *)(this + 0xb8) = 0;
  *(undefined8 *)(this + 0xb0) = 0;
  *(undefined8 *)(this + 0xc0) = 0;
  *(undefined8 *)(this + 200) = 0;
  FUN_0016a848(this + 0xf0,"D:/data/tmpdata/",0x10);
  *(undefined8 *)(this + 0x100) = 0;
  *(undefined4 *)(this + 0xf8) = 0x32;
  return;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::~DigitSegment
 * Address : 00119ac4
 * Size    : 0xec bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::~DigitSegment() */

void __thiscall DigitSegment::~DigitSegment(DigitSegment *this)

{
  int iVar1;
  char cVar2;
  bool bVar3;
  DigitSegment *pDVar4;
  undefined *puVar5;
  int *piVar6;
  undefined8 *puVar7;
  DigitSegment *pDVar8;
  undefined8 *puVar9;
  
  clear(this);
  puVar5 = (undefined *)(*(long *)(this + 0xf0) + -0x18);
  if (puVar5 != PTR_DAT_001cae30) {
    piVar6 = (int *)(*(long *)(this + 0xf0) + -8);
    do {
      iVar1 = *piVar6;
      cVar2 = '\x01';
      bVar3 = (bool)ExclusiveMonitorPass(piVar6,0x10);
      if (bVar3) {
        *piVar6 = iVar1 + -1;
        cVar2 = ExclusiveMonitorsStatus();
      }
    } while (cVar2 != '\0');
    if (iVar1 < 1) {
      operator_delete(puVar5);
    }
  }
  puVar7 = *(undefined8 **)(this + 0xd0);
  puVar9 = *(undefined8 **)(this + 0xd8);
  if (puVar7 != puVar9) {
    do {
      if ((void *)*puVar7 != (void *)0x0) {
        operator_delete__((void *)*puVar7);
      }
      puVar7 = puVar7 + 2;
    } while (puVar9 != puVar7);
    puVar9 = *(undefined8 **)(this + 0xd0);
  }
  if (puVar9 != (undefined8 *)0x0) {
    operator_delete(puVar9);
  }
  pDVar4 = *(DigitSegment **)(this + 0x18);
  while (pDVar4 != this + 0x18) {
    pDVar8 = *(DigitSegment **)pDVar4;
    operator_delete(pDVar4);
    pDVar4 = pDVar8;
  }
  pDVar4 = *(DigitSegment **)(this + 8);
  while (pDVar4 != this + 8) {
    pDVar8 = *(DigitSegment **)pDVar4;
    operator_delete(pDVar4);
    pDVar4 = pDVar8;
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::FindMostFreqRangeMinMax
 * Address : 00119d98
 * Size    : 0x1fc bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::FindMostFreqRangeMinMax(std::list<_RangeMinMax, std::allocator<_RangeMinMax> >&,
   int) */

undefined1  [16] __thiscall
DigitSegment::FindMostFreqRangeMinMax(DigitSegment *this,list *param_1,int param_2)

{
  list *plVar1;
  long lVar2;
  long lVar3;
  long lVar4;
  int iVar5;
  int iVar6;
  int iVar7;
  list *plVar8;
  ulong uVar9;
  undefined1 auVar10 [16];
  undefined1 auVar11 [16];
  int local_1c;
  int *local_18;
  int *local_10;
  int *local_8;
  
  lVar4 = 0;
  plVar8 = *(list **)param_1;
  plVar1 = plVar8;
  if (plVar8 == param_1) {
    return ZEXT816(0);
  }
  do {
    plVar1 = *(list **)plVar1;
    lVar4 = lVar4 + 1;
  } while (param_1 != plVar1);
  if (lVar4 == 1) {
    iVar6 = (*(int *)(plVar8 + 0x14) - *(int *)(plVar8 + 0x10)) + 1;
    auVar11._4_4_ = iVar6;
    auVar11._0_4_ = iVar6;
    auVar11._8_8_ = 0;
    return auVar11;
  }
  local_18 = (int *)0x0;
  local_10 = (int *)0x0;
  local_8 = (int *)0x0;
  do {
    local_1c = (*(int *)(plVar8 + 0x14) - *(int *)(plVar8 + 0x10)) + 1;
    if (local_10 == local_8) {
      std::vector<int,std::allocator<int>>::_M_insert_aux
                ((vector<int,std::allocator<int>> *)&local_18,local_10,&local_1c);
      plVar8 = *(list **)plVar8;
    }
    else {
      if (local_10 != (int *)0x0) {
        *local_10 = local_1c;
      }
      plVar8 = *(list **)plVar8;
      local_10 = local_10 + 1;
    }
  } while (param_1 != plVar8);
  lVar4 = (long)local_10 - (long)local_18 >> 2;
  if (lVar4 == 0) {
    iVar6 = *local_18 - param_2;
    if (iVar6 < 0) {
      iVar6 = 0;
    }
    uVar9 = CONCAT44(param_2 + *local_18,iVar6);
    if (local_18 == (int *)0x0) goto LAB_00119efc;
  }
  else {
    lVar3 = 0;
    iVar7 = 0;
    iVar6 = 0;
    do {
      lVar2 = 0;
      iVar5 = 0;
      do {
        if ((((int)lVar3 != (int)lVar2) && (local_18[lVar3] - param_2 <= local_18[lVar2])) &&
           (local_18[lVar2] <= local_18[lVar3] + param_2)) {
          iVar5 = iVar5 + 1;
        }
        lVar2 = lVar2 + 1;
      } while (lVar4 != lVar2);
      if (iVar6 <= iVar5) {
        iVar6 = iVar5;
        iVar7 = (int)lVar3;
      }
      lVar3 = lVar3 + 1;
    } while (lVar4 != lVar3);
    iVar6 = local_18[iVar7] - param_2;
    if (iVar6 < 0) {
      iVar6 = 0;
    }
    uVar9 = CONCAT44(param_2 + local_18[iVar7],iVar6);
  }
  operator_delete(local_18);
LAB_00119efc:
  auVar10._8_8_ = 0;
  auVar10._0_8_ = uVar9;
  return auVar10;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::SegHistogramProjectInX
 * Address : 00119fb0
 * Size    : 0x204 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::SegHistogramProjectInX(int*, int) */

void __thiscall DigitSegment::SegHistogramProjectInX(DigitSegment *this,int *param_1,int param_2)

{
  DigitSegment *pDVar1;
  int iVar2;
  undefined4 uVar3;
  undefined4 uVar4;
  list *plVar5;
  void *pvVar6;
  int iVar7;
  DigitSegment *pDVar8;
  DigitSegment *pDVar9;
  list *plVar10;
  int *piVar11;
  undefined1 auVar12 [16];
  list *local_10;
  list *local_8;
  
  pDVar1 = this + 8;
  pDVar9 = *(DigitSegment **)(this + 8);
  while (pDVar9 != pDVar1) {
    pDVar8 = *(DigitSegment **)pDVar9;
    operator_delete(pDVar9);
    pDVar9 = pDVar8;
  }
  iVar2 = *(int *)(this + 0x50);
  *(DigitSegment **)(this + 8) = pDVar1;
  *(DigitSegment **)(this + 0x10) = pDVar1;
  iVar7 = 1;
  pvVar6 = operator_new(0x28);
  *(undefined4 *)((long)pvVar6 + 0x10) = 0;
  *(undefined4 *)((long)pvVar6 + 0x14) = 0;
  *(undefined4 *)((long)pvVar6 + 0x18) = 0;
  piVar11 = param_1 + 1;
  *(undefined4 *)((long)pvVar6 + 0x1c) = 0;
  *(undefined4 *)((long)pvVar6 + 0x20) = 0;
  FUN_00139e14(pvVar6,pDVar1);
  if (1 < param_2) {
LAB_0011a090:
    do {
      if ((*piVar11 <= iVar2 / 0xc + 1) || (param_2 + -1 == iVar7)) {
        if (iVar7 - *(int *)(*(long *)(this + 0x10) + 0x18) <= iVar2 / 0x14) {
          *(int *)(*(long *)(this + 0x10) + 0x18) = iVar7;
          iVar7 = iVar7 + 1;
          piVar11 = piVar11 + 1;
          if (iVar7 == param_2) break;
          goto LAB_0011a090;
        }
        pvVar6 = operator_new(0x28);
        *(int *)((long)pvVar6 + 0x10) = iVar7;
        *(undefined4 *)((long)pvVar6 + 0x14) = 0;
        *(int *)((long)pvVar6 + 0x18) = iVar7;
        *(undefined4 *)((long)pvVar6 + 0x1c) = 0;
        *(undefined4 *)((long)pvVar6 + 0x20) = 0;
        FUN_00139e14(pvVar6,pDVar1);
      }
      iVar7 = iVar7 + 1;
      piVar11 = piVar11 + 1;
    } while (iVar7 != param_2);
  }
  local_10 = (list *)&local_10;
  local_8 = (list *)&local_10;
  for (pDVar9 = *(DigitSegment **)(this + 8); pDVar9 != pDVar1; pDVar9 = *(DigitSegment **)pDVar9) {
    uVar3 = *(undefined4 *)(pDVar9 + 0x10);
    uVar4 = *(undefined4 *)(pDVar9 + 0x18);
    pvVar6 = operator_new(0x20);
    *(undefined4 *)((long)pvVar6 + 0x10) = uVar3;
    *(undefined4 *)((long)pvVar6 + 0x14) = uVar4;
    *(undefined4 *)((long)pvVar6 + 0x18) = 0;
    *(undefined4 *)((long)pvVar6 + 0x1c) = 0;
    FUN_00139e14(pvVar6,(list *)&local_10);
  }
  auVar12 = FindMostFreqRangeMinMax(this,(list *)&local_10,*(int *)(this + 0x50) / 0x1e + 1);
  *(undefined1 (*) [16])(this + 0x6c) = auVar12;
  plVar5 = local_10;
  while (plVar5 != (list *)&local_10) {
    plVar10 = *(list **)plVar5;
    operator_delete(plVar5);
    plVar5 = plVar10;
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::setDigitTargetRectUpDown
 * Address : 0011a1e4
 * Size    : 0x244 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::setDigitTargetRectUpDown() */

void __thiscall DigitSegment::setDigitTargetRectUpDown(DigitSegment *this)

{
  int iVar1;
  int iVar2;
  undefined4 uVar3;
  int iVar4;
  list *plVar5;
  undefined1 uVar6;
  ulong uVar7;
  void *pvVar8;
  long *plVar9;
  undefined4 *puVar10;
  int iVar11;
  int iVar12;
  long *plVar13;
  list *plVar14;
  int iVar15;
  ulong uVar16;
  long lVar17;
  undefined1 auVar18 [16];
  list *local_10;
  list *local_8;
  
  if (*(void **)(this + 200) != (void *)0x0) {
    operator_delete__(*(void **)(this + 200));
    *(undefined8 *)(this + 200) = 0;
  }
  uVar16 = (ulong)*(int *)(this + 0xa8);
  uVar7 = uVar16 << 4;
  if (0x7f0000000000000 < uVar16) {
    uVar7 = 0xffffffffffffffff;
  }
  pvVar8 = operator_new__(uVar7);
  puVar10 = (undefined4 *)((long)pvVar8 + 8);
  uVar7 = 0;
  if (uVar16 != 0) {
    do {
      uVar7 = uVar7 + 1;
      *puVar10 = 0;
      puVar10[1] = 0;
      puVar10 = puVar10 + 4;
    } while (uVar16 != uVar7);
  }
  iVar12 = *(int *)(this + 0xa8);
  *(void **)(this + 200) = pvVar8;
  local_10 = (list *)&local_10;
  local_8 = (list *)&local_10;
  if (0 < iVar12) {
    lVar17 = 0;
    iVar15 = 0;
    do {
      iVar2 = *(int *)(this + 0xac);
      iVar11 = (int)(*(double *)(this + 0x88) + *(double *)(this + 0x80) * (double)iVar15);
      iVar4 = iVar11 + -2;
      if (iVar11 < 3) {
        iVar4 = 0;
      }
      iVar11 = (int)(*(double *)(this + 0x98) + *(double *)(this + 0x90) * (double)iVar15) + 2;
      iVar1 = iVar2 + -1;
      if (iVar4 < iVar2) {
        iVar1 = iVar4;
      }
      iVar4 = iVar2 + -1;
      if (iVar11 < iVar2) {
        iVar4 = iVar11;
      }
      if (iVar4 < 0) {
        iVar4 = 0;
      }
      iVar2 = iVar4;
      if (iVar1 < iVar4) {
        iVar2 = iVar1;
      }
      *(int *)((long)pvVar8 + lVar17) = iVar2;
      if (iVar4 < iVar1) {
        iVar4 = iVar1;
      }
      *(int *)((long)pvVar8 + lVar17 + 4) = iVar4;
      if (iVar15 == (iVar15 / 10) * 10) {
        uVar3 = *(undefined4 *)((long)pvVar8 + lVar17 + 4);
        pvVar8 = operator_new(0x20);
        *(int *)((long)pvVar8 + 0x10) = iVar2;
        *(undefined4 *)((long)pvVar8 + 0x14) = uVar3;
        *(undefined4 *)((long)pvVar8 + 0x18) = 0;
        *(undefined4 *)((long)pvVar8 + 0x1c) = 0;
        FUN_00139e14(pvVar8,(list *)&local_10);
        iVar12 = *(int *)(this + 0xa8);
        if (iVar12 <= iVar15 + 1) break;
      }
      else if (iVar12 <= iVar15 + 1) break;
      iVar15 = iVar15 + 1;
      lVar17 = lVar17 + 0x10;
      pvVar8 = *(void **)(this + 200);
    } while( true );
  }
  auVar18 = FindMostFreqRangeMinMax(this,(list *)&local_10,1);
  *(undefined1 (*) [16])(this + 0x4c) = auVar18;
  FUN_001509cc(PTR_DAT_001cacc8,&DAT_0018fa00,10);
  plVar9 = (long *)FUN_0015124c(PTR_DAT_001cacc8,*(undefined4 *)(this + 0x50));
  plVar13 = *(long **)((long)plVar9 + *(long *)(*plVar9 + -0x18) + 0xf0);
  if (plVar13 != (long *)0x0) {
    if ((char)plVar13[7] == '\0') {
      FUN_00130710(plVar13);
      uVar6 = 10;
      if (*(code **)(*plVar13 + 0x30) != (code *)PTR_FUN_001cac68) {
        uVar6 = (**(code **)(*plVar13 + 0x30))(plVar13,10);
      }
    }
    else {
      uVar6 = *(undefined1 *)((long)plVar13 + 0x43);
    }
    FUN_00150468(plVar9,uVar6);
    FUN_0014fca4();
    plVar5 = local_10;
    while (plVar5 != (list *)&local_10) {
      plVar14 = *(list **)plVar5;
      operator_delete(plVar5);
      plVar5 = plVar14;
    }
    return;
  }
                    /* WARNING: Subroutine does not return */
  FUN_00167c9c();
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::FindWidthRangeMinMax
 * Address : 0011a458
 * Size    : 0x1d4 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::FindWidthRangeMinMax(int) */

void __thiscall DigitSegment::FindWidthRangeMinMax(DigitSegment *this,int param_1)

{
  undefined4 uVar1;
  undefined4 uVar2;
  list *plVar3;
  undefined1 uVar4;
  long *plVar5;
  void *pvVar6;
  DigitSegment *pDVar7;
  long *plVar8;
  list *plVar9;
  DigitSegment *pDVar10;
  undefined1 auVar11 [16];
  list *local_10;
  list *local_8;
  
  pDVar10 = this + 0x18;
  pDVar7 = *(DigitSegment **)pDVar10;
  local_10 = (list *)&local_10;
  local_8 = (list *)&local_10;
  if (pDVar7 != pDVar10) {
    if (param_1 == 0) {
      do {
        if (-1 < *(int *)(pDVar7 + 0x1c)) {
          uVar1 = *(undefined4 *)(pDVar7 + 0x10);
          uVar2 = *(undefined4 *)(pDVar7 + 0x18);
          pvVar6 = operator_new(0x20);
          *(undefined4 *)((long)pvVar6 + 0x10) = uVar1;
          *(undefined4 *)((long)pvVar6 + 0x14) = uVar2;
          *(undefined4 *)((long)pvVar6 + 0x18) = 0;
          *(undefined4 *)((long)pvVar6 + 0x1c) = 0;
          FUN_00139e14(pvVar6,(list *)&local_10);
        }
        pDVar7 = *(DigitSegment **)pDVar7;
      } while (pDVar7 != pDVar10);
    }
    else if (param_1 == 1) {
      do {
        if (0 < *(int *)(pDVar7 + 0x1c)) {
          uVar1 = *(undefined4 *)(pDVar7 + 0x10);
          uVar2 = *(undefined4 *)(pDVar7 + 0x18);
          pvVar6 = operator_new(0x20);
          *(undefined4 *)((long)pvVar6 + 0x10) = uVar1;
          *(undefined4 *)((long)pvVar6 + 0x14) = uVar2;
          *(undefined4 *)((long)pvVar6 + 0x18) = 0;
          *(undefined4 *)((long)pvVar6 + 0x1c) = 0;
          FUN_00139e14(pvVar6,(list *)&local_10);
        }
        pDVar7 = *(DigitSegment **)pDVar7;
      } while (pDVar7 != pDVar10);
    }
    else {
      do {
        pDVar7 = *(DigitSegment **)pDVar7;
      } while (pDVar7 != pDVar10);
    }
  }
  auVar11 = FindMostFreqRangeMinMax(this,(list *)&local_10,*(int *)(this + 0x50) / 0x1e + 1);
  *(undefined1 (*) [16])(this + 0x5c) = auVar11;
  FUN_001509cc(PTR_DAT_001cacc8,&DAT_0018fa10,10);
  plVar5 = (long *)FUN_0015124c(PTR_DAT_001cacc8,*(undefined4 *)(this + 0x60));
  plVar8 = *(long **)((long)plVar5 + *(long *)(*plVar5 + -0x18) + 0xf0);
  if (plVar8 != (long *)0x0) {
    if ((char)plVar8[7] == '\0') {
      FUN_00130710(plVar8);
      uVar4 = 10;
      if (*(code **)(*plVar8 + 0x30) != (code *)PTR_FUN_001cac68) {
        uVar4 = (**(code **)(*plVar8 + 0x30))(plVar8,10);
      }
    }
    else {
      uVar4 = *(undefined1 *)((long)plVar8 + 0x43);
    }
    FUN_00150468(plVar5,uVar4);
    FUN_0014fca4();
    plVar3 = local_10;
    while (plVar3 != (list *)&local_10) {
      plVar9 = *(list **)plVar3;
      operator_delete(plVar3);
      plVar3 = plVar9;
    }
    return;
  }
                    /* WARNING: Subroutine does not return */
  FUN_00167c9c();
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::FindMostFreqRangeMinMax
 * Address : 0011a65c
 * Size    : 0x240 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::FindMostFreqRangeMinMax(std::vector<_RangeMinMax, std::allocator<_RangeMinMax> >&,
   int) */

undefined1  [16] __thiscall
DigitSegment::FindMostFreqRangeMinMax(DigitSegment *this,vector *param_1,int param_2)

{
  long lVar1;
  long lVar2;
  ulong uVar3;
  long lVar4;
  int iVar5;
  int *piVar6;
  int iVar7;
  int iVar8;
  int *piVar9;
  undefined8 uVar10;
  undefined1 auVar11 [16];
  undefined1 auVar12 [16];
  int local_1c;
  int *local_18;
  int *local_10;
  int *local_8;
  
  piVar9 = *(int **)param_1;
  piVar6 = *(int **)(param_1 + 8);
  lVar1 = (long)piVar6 - (long)piVar9 >> 4;
  if (lVar1 == 0) {
    return ZEXT816(0);
  }
  if (lVar1 == 1) {
    iVar7 = (piVar9[1] - *piVar9) + 1;
    auVar12._4_4_ = iVar7;
    auVar12._0_4_ = iVar7;
    auVar12._8_8_ = 0;
    return auVar12;
  }
  local_18 = (int *)0x0;
  local_10 = (int *)0x0;
  local_8 = (int *)0x0;
  if (piVar9 != piVar6) {
    do {
      local_1c = (piVar9[1] - *piVar9) + 1;
      if (local_10 == local_8) {
        std::vector<int,std::allocator<int>>::_M_insert_aux
                  ((vector<int,std::allocator<int>> *)&local_18,local_10,&local_1c);
        piVar6 = *(int **)(param_1 + 8);
        if (piVar6 == piVar9 + 4) goto LAB_0011a714;
      }
      else {
        if (local_10 != (int *)0x0) {
          *local_10 = local_1c;
        }
        local_10 = local_10 + 1;
        if (piVar6 == piVar9 + 4) goto LAB_0011a714;
      }
      piVar9 = piVar9 + 4;
    } while( true );
  }
  iVar7 = iRam0000000000000000 - param_2;
  if (iVar7 < 0) {
    iVar7 = 0;
  }
  uVar10 = CONCAT44(param_2 + iRam0000000000000000,iVar7);
  uVar3 = (ulong)(uint)((param_2 + iRam0000000000000000 + iVar7 + 1) / 2);
LAB_0011a7c8:
  auVar11._8_8_ = uVar3;
  auVar11._0_8_ = uVar10;
  return auVar11;
LAB_0011a714:
  lVar1 = (long)local_10 - (long)local_18 >> 2;
  if (lVar1 == 0) {
    iVar8 = *local_18 - param_2;
    iVar7 = param_2 + *local_18;
    if (iVar8 < 0) {
      iVar8 = 0;
    }
    uVar10 = CONCAT44(iVar7,iVar8);
    uVar3 = (ulong)(uint)((iVar7 + iVar8 + 1) / 2);
    if (local_18 == (int *)0x0) goto LAB_0011a7c8;
  }
  else {
    lVar4 = 0;
    iVar8 = 0;
    iVar7 = 0;
    do {
      lVar2 = 0;
      iVar5 = 0;
      do {
        if ((((int)lVar4 != (int)lVar2) && (local_18[lVar4] - param_2 <= local_18[lVar2])) &&
           (local_18[lVar2] <= local_18[lVar4] + param_2)) {
          iVar5 = iVar5 + 1;
        }
        lVar2 = lVar2 + 1;
      } while (lVar1 != lVar2);
      if (iVar7 <= iVar5) {
        iVar7 = iVar5;
        iVar8 = (int)lVar4;
      }
      lVar4 = lVar4 + 1;
    } while (lVar4 != lVar1);
    iVar5 = local_18[iVar8] - param_2;
    iVar7 = param_2 + local_18[iVar8];
    if (iVar5 < 0) {
      iVar5 = 0;
    }
    uVar10 = CONCAT44(iVar7,iVar5);
    uVar3 = (ulong)(uint)((iVar7 + iVar5 + 1) / 2);
  }
  operator_delete(local_18);
  goto LAB_0011a7c8;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::SetDigitMatVector
 * Address : 0011ae88
 * Size    : 0x284 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::SetDigitMatVector() */

void __thiscall DigitSegment::SetDigitMatVector(DigitSegment *this)

{
  int iVar1;
  int iVar2;
  int iVar3;
  float fVar4;
  float fVar5;
  void *__dest;
  int iVar6;
  int iVar7;
  int iVar8;
  DigitSegment *pDVar9;
  undefined8 *puVar10;
  undefined8 *puVar11;
  DigitSegment *pDVar12;
  DigitSegment *pDVar13;
  undefined8 *puVar14;
  long lVar15;
  undefined8 local_50;
  undefined8 uStack_48;
  undefined4 local_40;
  void *local_28;
  int local_20;
  int local_1c;
  int local_18;
  int iStack_14;
  int local_10;
  int local_c;
  undefined4 local_8;
  
  puVar10 = *(undefined8 **)(this + 0xd8);
  puVar11 = *(undefined8 **)(this + 0xd0);
  for (puVar14 = puVar11; puVar14 != puVar10; puVar14 = puVar14 + 2) {
    if ((void *)*puVar14 != (void *)0x0) {
      operator_delete__((void *)*puVar14);
    }
  }
  *(undefined8 **)(this + 0xd8) = puVar11;
  local_8 = 0;
  pDVar12 = this + 0x18;
  pDVar13 = *(DigitSegment **)pDVar12;
  do {
    while( true ) {
      if (pDVar13 == pDVar12) {
        return;
      }
      iVar1 = *(int *)(pDVar13 + 0x1c);
      iVar8 = *(int *)(pDVar13 + 0x18);
      iVar6 = *(int *)(pDVar13 + 0x10);
      iVar2 = *(int *)(pDVar13 + 0x14);
      if (((0 < iVar1) && (5 < iVar8 - iVar6)) && (10 < iVar1 - iVar2)) break;
      pDVar9 = *(DigitSegment **)pDVar13;
      FUN_00139e30(pDVar13);
      operator_delete(pDVar13);
      pDVar13 = pDVar9;
    }
    if ((iVar8 - iVar6) + 1 < *(int *)(this + 0x5c)) {
      pDVar9 = *(DigitSegment **)(this + 0x18);
      fVar4 = (float)(iVar8 + iVar6) * 0.5;
      fVar5 = (float)(*(int *)(this + 0x5c) + *(int *)(this + 0x60)) * 0.25;
      iVar6 = (int)(fVar4 - fVar5);
      if (pDVar9 == pDVar13) {
        iVar7 = (int)(fVar4 + fVar5);
      }
      else {
        pDVar9 = (DigitSegment *)**(undefined8 **)(pDVar13 + 8);
        iVar7 = (int)(fVar4 + fVar5);
        iVar8 = *(int *)(*(undefined8 **)(pDVar13 + 8) + 3);
        if (iVar6 <= iVar8) {
          iVar6 = iVar8 + 1;
        }
        pDVar13 = pDVar12;
        iVar8 = iVar7;
        if (pDVar9 == pDVar12) goto LAB_0011afb8;
      }
      pDVar9 = *(DigitSegment **)pDVar9;
      pDVar13 = *(DigitSegment **)(pDVar9 + 8);
      iVar8 = iVar7;
      if ((pDVar9 != pDVar12) &&
         (iVar8 = *(int *)(pDVar9 + 0x10) + -1, iVar7 < *(int *)(pDVar9 + 0x10))) {
        iVar8 = iVar7;
      }
    }
LAB_0011afb8:
    iVar7 = *(int *)(this + 0xa8);
    if (iVar6 < 0) {
      iVar6 = 0;
    }
    iVar3 = *(int *)(this + 0xac);
    local_18 = iVar7 + -1;
    if (iVar6 < iVar7) {
      local_18 = iVar6;
    }
    if (iVar2 < 0) {
      iVar2 = 0;
    }
    iStack_14 = iVar3 + -1;
    if (iVar2 < iVar3) {
      iStack_14 = iVar2;
    }
    if (iVar8 < 0) {
      iVar8 = 0;
    }
    local_10 = iVar7 + -1;
    if (iVar8 < iVar7) {
      local_10 = iVar8;
    }
    if (iVar1 < 0) {
      iVar1 = 0;
    }
    local_c = iVar3 + -1;
    if (iVar1 < iVar3) {
      local_c = iVar1;
    }
    local_50 = CONCAT44(iStack_14,local_18);
    uStack_48 = CONCAT44(local_c,local_10);
    local_40 = local_8;
    resizeMapBuffer(&local_28,this,&local_50);
    puVar14 = *(undefined8 **)(this + 0xd8);
    if (puVar14 == *(undefined8 **)(this + 0xe0)) {
      std::vector<_mapbuffer,std::allocator<_mapbuffer>>::_M_insert_aux
                ((vector<_mapbuffer,std::allocator<_mapbuffer>> *)(this + 0xd0),puVar14,&local_28);
    }
    else {
      lVar15 = 0;
      if (puVar14 != (undefined8 *)0x0) {
        *(int *)(puVar14 + 1) = local_20;
        *(int *)((long)puVar14 + 0xc) = local_1c;
        __dest = operator_new__((long)(local_20 * local_1c));
        *puVar14 = __dest;
        memcpy(__dest,local_28,(long)(*(int *)(puVar14 + 1) * *(int *)((long)puVar14 + 0xc)));
        lVar15 = *(long *)(this + 0xd8);
      }
      *(long *)(this + 0xd8) = lVar15 + 0x10;
    }
    pDVar13 = *(DigitSegment **)pDVar13;
    if (local_28 != (void *)0x0) {
      operator_delete__(local_28);
    }
  } while( true );
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::updateDigitVectorByRecog
 * Address : 0011b128
 * Size    : 0x27c bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::updateDigitVectorByRecog(int, int) */

undefined8 DigitSegment::updateDigitVectorByRecog(int param_1,int param_2)

{
  int iVar1;
  DigitSegment *pDVar2;
  long lVar3;
  undefined8 *puVar4;
  DigitSegment *pDVar5;
  char *pcVar6;
  DigitSegment *pDVar7;
  DigitSegment *pDVar8;
  undefined8 uVar9;
  void *local_e0;
  void *local_d8;
  undefined8 local_d0;
  char local_c8 [200];
  
  pDVar5 = (DigitSegment *)(ulong)(uint)param_1;
  SetDigitMatVector(pDVar5);
  pDVar8 = pDVar5 + 0x18;
  pDVar7 = *(DigitSegment **)pDVar8;
  if (pDVar8 == pDVar7) {
    return 0;
  }
  lVar3 = 0;
  pDVar2 = pDVar7;
  do {
    pDVar2 = *(DigitSegment **)pDVar2;
    lVar3 = lVar3 + 1;
  } while (pDVar8 != pDVar2);
  if (lVar3 == 1) {
    return 0;
  }
  puVar4 = *(undefined8 **)(pDVar5 + 0xd0);
  local_e0 = (void *)0x0;
  local_d8 = (void *)0x0;
  lVar3 = 0;
  local_c8[0] = '\0';
  local_c8[1] = '\0';
  local_c8[2] = '\0';
  local_c8[3] = '\0';
  local_c8[4] = '\0';
  local_c8[5] = '\0';
  local_c8[6] = '\0';
  local_c8[7] = '\0';
  local_c8[8] = '\0';
  local_c8[9] = '\0';
  local_c8[10] = '\0';
  local_c8[0xb] = '\0';
  local_c8[0xc] = '\0';
  local_c8[0xd] = '\0';
  local_c8[0xe] = '\0';
  local_c8[0xf] = '\0';
  local_c8[0x10] = '\0';
  local_c8[0x11] = '\0';
  local_c8[0x12] = '\0';
  local_c8[0x13] = '\0';
  local_c8[0x14] = '\0';
  local_c8[0x15] = '\0';
  local_c8[0x16] = '\0';
  local_c8[0x17] = '\0';
  local_c8[0x18] = '\0';
  local_c8[0x19] = '\0';
  local_c8[0x1a] = '\0';
  local_c8[0x1b] = '\0';
  local_c8[0x1c] = '\0';
  local_c8[0x1d] = '\0';
  local_c8[0x1e] = '\0';
  local_c8[0x1f] = '\0';
  local_c8[0x20] = '\0';
  local_c8[0x21] = '\0';
  local_c8[0x22] = '\0';
  local_c8[0x23] = '\0';
  local_c8[0x24] = '\0';
  local_c8[0x25] = '\0';
  local_c8[0x26] = '\0';
  local_c8[0x27] = '\0';
  local_c8[0x28] = '\0';
  local_c8[0x29] = '\0';
  local_c8[0x2a] = '\0';
  local_c8[0x2b] = '\0';
  local_c8[0x2c] = '\0';
  local_c8[0x2d] = '\0';
  local_c8[0x2e] = '\0';
  local_c8[0x2f] = '\0';
  local_c8[0x30] = '\0';
  local_c8[0x31] = '\0';
  local_c8[0x32] = '\0';
  local_c8[0x33] = '\0';
  local_c8[0x34] = '\0';
  local_c8[0x35] = '\0';
  local_c8[0x36] = '\0';
  local_c8[0x37] = '\0';
  local_c8[0x38] = '\0';
  local_c8[0x39] = '\0';
  local_c8[0x3a] = '\0';
  local_c8[0x3b] = '\0';
  local_c8[0x3c] = '\0';
  local_c8[0x3d] = '\0';
  local_c8[0x3e] = '\0';
  local_c8[0x3f] = '\0';
  local_c8[0x40] = '\0';
  local_c8[0x41] = '\0';
  local_c8[0x42] = '\0';
  local_c8[0x43] = '\0';
  local_c8[0x44] = '\0';
  local_c8[0x45] = '\0';
  local_c8[0x46] = '\0';
  local_c8[0x47] = '\0';
  local_c8[0x48] = '\0';
  local_c8[0x49] = '\0';
  local_c8[0x4a] = '\0';
  local_c8[0x4b] = '\0';
  local_c8[0x4c] = '\0';
  local_c8[0x4d] = '\0';
  local_c8[0x4e] = '\0';
  local_c8[0x4f] = '\0';
  local_c8[0x50] = '\0';
  local_c8[0x51] = '\0';
  local_c8[0x52] = '\0';
  local_c8[0x53] = '\0';
  local_c8[0x54] = '\0';
  local_c8[0x55] = '\0';
  local_c8[0x56] = '\0';
  local_c8[0x57] = '\0';
  local_c8[0x58] = '\0';
  local_c8[0x59] = '\0';
  local_c8[0x5a] = '\0';
  local_c8[0x5b] = '\0';
  local_c8[0x5c] = '\0';
  local_c8[0x5d] = '\0';
  local_c8[0x5e] = '\0';
  local_c8[0x5f] = '\0';
  local_c8[0x60] = '\0';
  local_c8[0x61] = '\0';
  local_c8[0x62] = '\0';
  local_c8[99] = '\0';
  local_c8[100] = '\0';
  local_c8[0x65] = '\0';
  local_c8[0x66] = '\0';
  local_c8[0x67] = '\0';
  local_c8[0x68] = '\0';
  local_c8[0x69] = '\0';
  local_c8[0x6a] = '\0';
  local_c8[0x6b] = '\0';
  local_c8[0x6c] = '\0';
  local_c8[0x6d] = '\0';
  local_c8[0x6e] = '\0';
  local_c8[0x6f] = '\0';
  local_c8[0x70] = '\0';
  local_c8[0x71] = '\0';
  local_c8[0x72] = '\0';
  local_c8[0x73] = '\0';
  local_c8[0x74] = '\0';
  local_c8[0x75] = '\0';
  local_c8[0x76] = '\0';
  local_c8[0x77] = '\0';
  local_c8[0x78] = '\0';
  local_c8[0x79] = '\0';
  local_c8[0x7a] = '\0';
  local_c8[0x7b] = '\0';
  local_c8[0x7c] = '\0';
  local_c8[0x7d] = '\0';
  local_c8[0x7e] = '\0';
  local_c8[0x7f] = '\0';
  local_c8[0x80] = '\0';
  local_c8[0x81] = '\0';
  local_c8[0x82] = '\0';
  local_c8[0x83] = '\0';
  local_c8[0x84] = '\0';
  local_c8[0x85] = '\0';
  local_c8[0x86] = '\0';
  local_c8[0x87] = '\0';
  local_c8[0x88] = '\0';
  local_c8[0x89] = '\0';
  local_c8[0x8a] = '\0';
  local_c8[0x8b] = '\0';
  local_c8[0x8c] = '\0';
  local_c8[0x8d] = '\0';
  local_c8[0x8e] = '\0';
  local_c8[0x8f] = '\0';
  local_c8[0x90] = '\0';
  local_c8[0x91] = '\0';
  local_c8[0x92] = '\0';
  local_c8[0x93] = '\0';
  local_c8[0x94] = '\0';
  local_c8[0x95] = '\0';
  local_c8[0x96] = '\0';
  local_c8[0x97] = '\0';
  local_c8[0x98] = '\0';
  local_c8[0x99] = '\0';
  local_c8[0x9a] = '\0';
  local_c8[0x9b] = '\0';
  local_c8[0x9c] = '\0';
  local_c8[0x9d] = '\0';
  local_c8[0x9e] = '\0';
  local_c8[0x9f] = '\0';
  local_c8[0xa0] = '\0';
  local_c8[0xa1] = '\0';
  local_c8[0xa2] = '\0';
  local_c8[0xa3] = '\0';
  local_c8[0xa4] = '\0';
  local_c8[0xa5] = '\0';
  local_c8[0xa6] = '\0';
  local_c8[0xa7] = '\0';
  local_c8[0xa8] = '\0';
  local_c8[0xa9] = '\0';
  local_c8[0xaa] = '\0';
  local_c8[0xab] = '\0';
  local_c8[0xac] = '\0';
  local_c8[0xad] = '\0';
  local_c8[0xae] = '\0';
  local_c8[0xaf] = '\0';
  local_c8[0xb0] = '\0';
  local_c8[0xb1] = '\0';
  local_c8[0xb2] = '\0';
  local_c8[0xb3] = '\0';
  local_c8[0xb4] = '\0';
  local_c8[0xb5] = '\0';
  local_c8[0xb6] = '\0';
  local_c8[0xb7] = '\0';
  local_c8[0xb8] = '\0';
  local_c8[0xb9] = '\0';
  local_c8[0xba] = '\0';
  local_c8[0xbb] = '\0';
  local_c8[0xbc] = '\0';
  local_c8[0xbd] = '\0';
  local_c8[0xbe] = '\0';
  local_c8[0xbf] = '\0';
  local_d0 = 0;
  local_c8[0xc0] = '\0';
  local_c8[0xc1] = '\0';
  local_c8[0xc2] = '\0';
  local_c8[0xc3] = '\0';
  local_c8[0xc4] = '\0';
  local_c8[0xc5] = '\0';
  local_c8[0xc6] = '\0';
  local_c8[199] = '\0';
  pDVar2 = pDVar7;
  do {
    pDVar2 = *(DigitSegment **)pDVar2;
    lVar3 = lVar3 + 1;
  } while (pDVar8 != pDVar2);
  if (0x32 < lVar3) {
    return 0;
  }
  if (puVar4 != *(undefined8 **)(pDVar5 + 0xd8)) {
    lVar3 = 0;
    do {
      local_d8 = local_e0;
      iVar1 = CDigitalOCR::Predict
                        (*(CDigitalOCR **)(pDVar5 + 0x100),(uchar *)*puVar4,*(int *)(puVar4 + 1),
                         *(int *)((long)puVar4 + 0xc),(vector *)&local_e0);
      if (iVar1 == -1) {
        __android_log_print(6,"libdigit_ocr","model is not loaded!");
        break;
      }
      if (((long)local_d8 - (long)local_e0 >> 3 != 0) && (*(int *)((long)local_e0 + 4) < param_2)) {
        local_c8[lVar3] = '\x01';
      }
      puVar4 = puVar4 + 2;
      lVar3 = lVar3 + 1;
    } while (*(undefined8 **)(pDVar5 + 0xd8) != puVar4);
    pDVar7 = *(DigitSegment **)(pDVar5 + 0x18);
    if (local_c8[0] != '\0') {
      *(undefined4 *)(pDVar7 + 0x14) = 0;
      *(undefined4 *)(pDVar7 + 0x1c) = 0;
    }
    if (pDVar8 == pDVar7) {
      uVar9 = 0;
      goto LAB_0011b318;
    }
  }
  if (pDVar8 == *(DigitSegment **)pDVar7) {
    uVar9 = 0;
  }
  else {
    pcVar6 = local_c8 + 1;
    uVar9 = 0;
    pDVar5 = *(DigitSegment **)pDVar7;
    do {
      while (*pcVar6 == '\0') {
        pDVar5 = *(DigitSegment **)pDVar5;
        pcVar6 = pcVar6 + 1;
        pDVar7 = *(DigitSegment **)pDVar7;
        if (pDVar8 == pDVar5) goto LAB_0011b318;
      }
      if (pcVar6[-1] == '\0') {
        pDVar7 = *(DigitSegment **)pDVar7;
        *(undefined4 *)(pDVar5 + 0x14) = 0;
        *(undefined4 *)(pDVar5 + 0x1c) = 0;
        pDVar2 = *(DigitSegment **)pDVar5;
      }
      else {
        *(undefined4 *)(pDVar7 + 0x18) = *(undefined4 *)(pDVar5 + 0x18);
        pDVar2 = *(DigitSegment **)pDVar5;
        FUN_00139e30(pDVar5);
        operator_delete(pDVar5);
      }
      uVar9 = 1;
      pcVar6 = pcVar6 + 1;
      pDVar5 = pDVar2;
    } while (pDVar8 != pDVar2);
  }
LAB_0011b318:
  if (local_e0 == (void *)0x0) {
    return uVar9;
  }
  operator_delete(local_e0);
  return uVar9;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::FindDigitFromTargetRect
 * Address : 0011b3c0
 * Size    : 0x134 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::FindDigitFromTargetRect(int*, int*, RectRegion*, int) */

void DigitSegment::FindDigitFromTargetRect
               (int *param_1,int *param_2,RectRegion *param_3,int param_4)

{
  char cVar1;
  long *plVar2;
  int *piVar3;
  long lVar4;
  int *piVar5;
  
  ProjectHistogramInX((DigitSegment *)param_1);
  SegHistogramProjectInX((DigitSegment *)param_1,*(int **)param_1,param_1[0x2a]);
  lVar4 = 0;
  for (plVar2 = *(long **)(param_1 + 2); plVar2 != (long *)(param_1 + 2); plVar2 = (long *)*plVar2)
  {
    lVar4 = lVar4 + 1;
  }
  __android_log_print(3,"libdigit_ocr","begin SetDigitBoundingBox process,CrackSize=%d ",lVar4);
  updateDigitVector((DigitSegment *)param_1);
  FindWidthRangeMinMax((DigitSegment *)param_1,0);
  SetDigitBoundingBox((DigitSegment *)param_1,0.5,0);
  piVar5 = param_1 + 6;
  piVar3 = *(int **)piVar5;
  lVar4 = 0;
  if (piVar3 != piVar5) {
    do {
      piVar3 = *(int **)piVar3;
      lVar4 = lVar4 + 1;
    } while (piVar5 != piVar3);
    if ((2 < lVar4) && (cVar1 = updateDigitVectorByRecog((int)param_1,0x5a), cVar1 != '\0')) {
      FindWidthRangeMinMax((DigitSegment *)param_1,1);
      SetDigitBoundingBox((DigitSegment *)param_1,DAT_0011b4f4,1);
      lVar4 = 0;
      for (plVar2 = *(long **)(param_1 + 6); (long *)piVar5 != plVar2; plVar2 = (long *)*plVar2) {
        lVar4 = lVar4 + 1;
      }
      __android_log_print(3,"libdigit_ocr","end SetDigitBoundingBox process,digitSize=%d",lVar4);
      return;
    }
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::FindDigitConnectedReigon
 * Address : 0011b7d4
 * Size    : 0x554 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::FindDigitConnectedReigon(unsigned char*, RectRegion, int*) */

bool __thiscall
DigitSegment::FindDigitConnectedReigon(DigitSegment *this,long param_1,int *param_3,long param_4)

{
  int iVar1;
  int iVar2;
  int iVar3;
  int iVar4;
  int iVar5;
  bool bVar6;
  undefined8 *puVar7;
  undefined8 *puVar8;
  undefined8 *puVar9;
  undefined8 *puVar10;
  undefined8 *puVar11;
  undefined8 *puVar12;
  long lVar13;
  long lVar14;
  int iVar15;
  int iVar16;
  int iVar17;
  int iVar18;
  int *piVar19;
  char *pcVar20;
  int iVar21;
  long local_c0;
  int local_b8;
  void *local_a0;
  undefined8 local_98;
  undefined8 *local_90;
  undefined8 local_88;
  undefined8 *local_80;
  undefined8 *local_78;
  undefined8 *local_70;
  undefined8 *local_68;
  undefined8 *local_60;
  long local_58;
  undefined8 local_50;
  undefined8 local_48;
  undefined8 *local_40;
  undefined8 local_38;
  undefined8 *local_30;
  undefined8 *local_28;
  undefined8 *local_20;
  long local_18;
  undefined8 local_10;
  long local_8;
  
  iVar3 = param_3[2];
  iVar4 = param_3[3];
  iVar5 = *(int *)(this + 0xa8);
  local_50 = (void *)0x0;
  local_48 = 0;
  local_40 = (undefined8 *)0x0;
  local_38 = 0;
  local_30 = (undefined8 *)0x0;
  local_28 = (undefined8 *)0x0;
  local_20 = (undefined8 *)0x0;
  local_18 = 0;
  local_10 = 0;
  local_8 = 0;
  std::_Deque_base<_point,std::allocator<_point>>::_M_initialize_map
            ((_Deque_base<_point,std::allocator<_point>> *)&local_50,0);
  local_a0 = (void *)0x0;
  local_98 = 0;
  local_90 = (undefined8 *)0x0;
  local_88 = 0;
  local_80 = (undefined8 *)0x0;
  local_78 = (undefined8 *)0x0;
  local_70 = (undefined8 *)0x0;
  local_68 = (undefined8 *)0x0;
  local_60 = (undefined8 *)0x0;
  local_58 = 0;
  std::_Deque_base<_point,std::allocator<_point>>::_M_initialize_map
            ((_Deque_base<_point,std::allocator<_point>> *)&local_a0,
             ((long)local_20 - local_18 >> 3) + ((local_8 - (long)local_28 >> 3) + -1) * 0x40 +
             ((long)local_30 - (long)local_40 >> 3));
  puVar7 = local_90;
  puVar8 = local_40;
  puVar9 = local_78 + 1;
  puVar10 = local_28;
  puVar11 = local_80;
  puVar12 = local_30;
  while (local_20 != puVar8) {
    if (puVar7 != (undefined8 *)0x0) {
      *puVar7 = *puVar8;
    }
    puVar8 = puVar8 + 1;
    if (puVar8 == puVar12) {
      puVar8 = (undefined8 *)puVar10[1];
      puVar10 = puVar10 + 1;
      puVar12 = puVar8 + 0x40;
    }
    puVar7 = puVar7 + 1;
    if (puVar11 == puVar7) {
      puVar7 = (undefined8 *)*puVar9;
      puVar11 = puVar7 + 0x40;
      puVar9 = puVar9 + 1;
    }
  }
  if (local_50 != (void *)0x0) {
    puVar8 = (undefined8 *)(local_8 + 8);
    for (puVar7 = local_28; puVar7 < puVar8; puVar7 = puVar7 + 1) {
      operator_delete((void *)*puVar7);
    }
    operator_delete(local_50);
  }
  local_b8 = *param_3;
  local_c0 = (long)local_b8;
  if (local_b8 < iVar3) {
    do {
      iVar21 = param_3[1];
      if (iVar21 < iVar4) {
        lVar13 = local_c0 + iVar5 * iVar21;
        piVar19 = (int *)(param_4 + lVar13 * 4);
        pcVar20 = (char *)(param_1 + lVar13);
        do {
          if ((*piVar19 == 0) && ((*pcVar20 != '\0' || (*piVar19 = -1, *pcVar20 != '\0')))) {
            local_50 = (void *)CONCAT44(iVar21,local_b8);
            if (local_70 == local_60 + -1) {
              std::deque<_point,std::allocator<_point>>::_M_push_back_aux
                        ((deque<_point,std::allocator<_point>> *)&local_a0,(_point *)&local_50);
            }
            else {
              if (local_70 != (undefined8 *)0x0) {
                *local_70 = local_50;
              }
              local_70 = local_70 + 1;
            }
            lVar13 = *(long *)(this + 0x28);
            *piVar19 = *(int *)(this + 0x40);
            updateRect((RectRegion *)(*(long *)(this + 0x38) + (long)*(int *)(this + 0x40) * 0x14),
                       (int *)(lVar13 + (long)*(int *)(this + 0x40) * 4),local_b8,iVar21);
            puVar8 = local_70;
LAB_0011ba48:
            if (local_90 != puVar8) {
              if (local_68 == puVar8) {
                iVar18 = *(int *)(*(long *)(local_58 + -8) + 0x1f8);
                iVar17 = *(int *)(*(long *)(local_58 + -8) + 0x1fc);
                operator_delete(puVar8);
                local_68 = *(undefined8 **)(local_58 + -8);
                local_60 = local_68 + 0x40;
                puVar8 = local_68 + 0x3f;
                local_58 = local_58 + -8;
              }
              else {
                iVar18 = *(int *)(puVar8 + -1);
                iVar17 = *(int *)((long)puVar8 + -4);
                puVar8 = puVar8 + -1;
              }
              iVar16 = 1;
              iVar15 = 0;
              local_70 = puVar8;
              do {
                if (iVar15 != 4) {
                  iVar1 = iVar15 / 3 + iVar17 + -1;
                  iVar2 = iVar15 % 3 + iVar18 + -1;
                  if ((((-1 < iVar1) && (-1 < iVar2)) && (iVar2 < *(int *)(this + 0xa8))) &&
                     (iVar1 < *(int *)(this + 0xac))) {
                    lVar13 = (long)(iVar2 + iVar1 * iVar5);
                    if (*(char *)(param_1 + lVar13) == '\0') {
                      *(undefined4 *)(param_4 + lVar13 * 4) = 0xffffffff;
                    }
                    else if (*(int *)(param_4 + lVar13 * 4) == 0) {
                      local_50 = (void *)CONCAT44(iVar1,iVar2);
                      if (puVar8 == local_60 + -1) {
                        std::deque<_point,std::allocator<_point>>::_M_push_back_aux
                                  ((deque<_point,std::allocator<_point>> *)&local_a0,
                                   (_point *)&local_50);
                      }
                      else {
                        if (puVar8 != (undefined8 *)0x0) {
                          *puVar8 = local_50;
                        }
                        local_70 = puVar8 + 1;
                      }
                      lVar14 = *(long *)(this + 0x28);
                      *(undefined4 *)(param_4 + lVar13 * 4) =
                           *(undefined4 *)(param_4 + (long)(iVar18 + iVar17 * iVar5) * 4);
                      updateRect((RectRegion *)
                                 (*(long *)(this + 0x38) + (long)*(int *)(this + 0x40) * 0x14),
                                 (int *)(lVar14 + (long)*(int *)(this + 0x40) * 4),iVar2,iVar1);
                      puVar8 = local_70;
                    }
                  }
                  if (iVar16 == 9) goto LAB_0011ba48;
                }
                iVar15 = iVar15 + 1;
                iVar16 = iVar16 + 1;
              } while( true );
            }
            iVar18 = *(int *)(this + 0x40);
            *(int *)(this + 0x40) = iVar18 + 1;
            if (*(int *)(this + 0x44) <= iVar18 + 1) {
              bVar6 = false;
              goto LAB_0011bc84;
            }
          }
          piVar19 = piVar19 + iVar5;
          iVar21 = iVar21 + 1;
          pcVar20 = pcVar20 + iVar5;
        } while (iVar21 != iVar4);
      }
      local_c0 = local_c0 + 1;
      local_b8 = local_b8 + 1;
    } while (local_b8 != iVar3);
  }
  bVar6 = *(int *)(this + 0x40) != 0;
LAB_0011bc84:
  if (local_a0 != (void *)0x0) {
    puVar8 = (undefined8 *)(local_58 + 8);
    for (puVar7 = local_78; puVar7 < puVar8; puVar7 = puVar7 + 1) {
      operator_delete((void *)*puVar7);
    }
    operator_delete(local_a0);
  }
  return bVar6;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::RansacLine
 * Address : 0011c2a8
 * Size    : 0x44 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::RansacLine(std::vector<double, std::allocator<double> >&, std::vector<Point2D,
   std::allocator<Point2D> >&) */

void __thiscall DigitSegment::RansacLine(DigitSegment *this,vector *param_1,vector *param_2)

{
  LineParamEstimator aLStack_10 [16];
  
  LineParamEstimator::LineParamEstimator(aLStack_10,0.5);
  Ransac<Point2D,double>::compute(param_1,(ParameterEsitmator *)aLStack_10,param_2,2);
  return;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::FindDigitTargetRect
 * Address : 0011c2f0
 * Size    : 0x634 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::FindDigitTargetRect(RectRegion*, float, float) */

undefined1  [16] __thiscall
DigitSegment::FindDigitTargetRect
          (DigitSegment *this,RectRegion *param_1,float param_2,float param_3)

{
  float fVar1;
  int iVar2;
  list *plVar3;
  undefined1 uVar4;
  ulong uVar5;
  int *__s;
  void *pvVar6;
  int *piVar7;
  long *plVar8;
  int *piVar9;
  int iVar10;
  int iVar11;
  long lVar12;
  long lVar13;
  int iVar14;
  long *plVar15;
  list *plVar16;
  int iVar17;
  RectRegion *pRVar18;
  int iVar19;
  double dVar20;
  double dVar21;
  double dVar22;
  undefined1 auVar23 [16];
  list *local_80;
  list *local_78;
  double local_70;
  double local_68;
  double *local_60;
  long local_58;
  undefined8 local_50;
  double *local_48;
  long local_40;
  undefined8 local_38;
  void *local_30;
  double *local_28;
  double *local_20;
  void *local_18;
  double *local_10;
  double *local_8;
  
  iVar19 = *(int *)(this + 0xa8);
  iVar14 = *(int *)(this + 0xac);
  uVar5 = (long)*(int *)(this + 0x40) << 2;
  if (0x1fc0000000000000 < (ulong)(long)*(int *)(this + 0x40)) {
    uVar5 = 0xffffffffffffffff;
  }
  __s = operator_new__(uVar5);
  memset(__s,0,(long)*(int *)(this + 0x40) << 2);
  dVar21 = DAT_0011c990;
  dVar20 = DAT_0011c988;
  iVar10 = *(int *)(this + 0x40);
  if (1 < iVar10) {
    iVar11 = 1;
    lVar13 = *(long *)(this + 0x28);
    pRVar18 = param_1 + 0x20;
    lVar12 = 0;
    do {
      fVar1 = (float)((*(int *)pRVar18 - *(int *)(pRVar18 + -8)) + 1) /
              (float)((*(int *)(pRVar18 + -4) - *(int *)(pRVar18 + -0xc)) + 1);
      if ((param_2 < fVar1 && fVar1 <= param_3) &&
         ((iVar19 * iVar14) / 1000 < *(int *)(lVar13 + lVar12 + 4))) {
        dVar22 = (double)((*(int *)pRVar18 + *(int *)(pRVar18 + -8)) / 2);
        if (((double)*(int *)(this + 0xac) * dVar20 <= dVar22) &&
           (dVar22 <= (double)*(int *)(this + 0xac) * dVar21)) {
          *(undefined4 *)((long)__s + lVar12 + 4) = 1;
          iVar10 = *(int *)(this + 0x40);
        }
      }
      iVar11 = iVar11 + 1;
      pRVar18 = pRVar18 + 0x14;
      lVar12 = lVar12 + 4;
    } while (iVar11 < iVar10);
  }
  local_60 = (double *)0x0;
  local_58 = 0;
  local_50 = 0;
  local_48 = (double *)0x0;
  local_40 = 0;
  local_38 = 0;
  local_30 = (void *)0x0;
  local_28 = (double *)0x0;
  local_20 = (double *)0x0;
  local_18 = (void *)0x0;
  local_10 = (double *)0x0;
  local_8 = (double *)0x0;
  local_80 = (list *)&local_80;
  local_78 = (list *)&local_80;
  if (1 < iVar10) {
    iVar19 = 1;
    piVar9 = __s;
    do {
      piVar9 = piVar9 + 1;
      pRVar18 = param_1 + 0x14;
      if (0 < *piVar9) {
        iVar14 = *(int *)pRVar18;
        iVar17 = *(int *)(param_1 + 0x1c);
        iVar11 = *(int *)(param_1 + 0x18);
        iVar2 = iVar14;
        if (iVar14 <= iVar17) {
          while( true ) {
            pvVar6 = operator_new(0x20);
            *(int *)((long)pvVar6 + 0x10) = iVar2;
            *(int *)((long)pvVar6 + 0x14) = iVar17;
            *(undefined4 *)((long)pvVar6 + 0x18) = 0;
            *(undefined4 *)((long)pvVar6 + 0x1c) = 0;
            FUN_00139e14(pvVar6,(list *)&local_80);
            if (*(int *)(*(long *)(this + 0xa0) +
                        (long)(iVar14 + iVar11 * *(int *)(this + 0xa8)) * 4) == iVar19) break;
            iVar17 = *(int *)(param_1 + 0x1c);
            iVar14 = iVar14 + 1;
            if (iVar17 < iVar14) goto LAB_0011c564;
            iVar2 = *(int *)pRVar18;
          }
          local_70 = (double)iVar14;
          local_68 = (double)iVar11;
          if (local_20 == local_28) {
            std::vector<Point2D,std::allocator<Point2D>>::_M_insert_aux
                      ((vector<Point2D,std::allocator<Point2D>> *)&local_30,local_20,&local_70);
          }
          else {
            if (local_28 != (double *)0x0) {
              *local_28 = local_70;
              local_28[1] = local_68;
            }
            local_28 = local_28 + 2;
          }
          iVar17 = *(int *)(param_1 + 0x1c);
LAB_0011c564:
          iVar14 = *(int *)pRVar18;
          if (iVar14 <= iVar17) {
            iVar10 = *(int *)(param_1 + 0x20) * *(int *)(this + 0xa8);
            if (*(int *)(*(long *)(this + 0xa0) + (long)(iVar14 + iVar10) * 4) == iVar19) {
LAB_0011c6d8:
              local_70 = (double)iVar14;
              local_68 = (double)*(int *)(param_1 + 0x20);
              if (local_8 != local_10) {
                if (local_10 != (double *)0x0) {
                  *local_10 = local_70;
                  local_10[1] = local_68;
                }
                local_10 = local_10 + 2;
                iVar10 = *(int *)(this + 0x40);
                goto LAB_0011c5c4;
              }
              std::vector<Point2D,std::allocator<Point2D>>::_M_insert_aux
                        ((vector<Point2D,std::allocator<Point2D>> *)&local_18,local_8,&local_70);
            }
            else {
              iVar14 = iVar14 + 1;
              piVar7 = (int *)(*(long *)(this + 0xa0) + ((long)iVar14 + (long)iVar10) * 4);
              for (; iVar14 <= iVar17; iVar14 = iVar14 + 1) {
                if (*piVar7 == iVar19) goto LAB_0011c6d8;
                piVar7 = piVar7 + 1;
              }
            }
          }
          iVar10 = *(int *)(this + 0x40);
        }
      }
LAB_0011c5c4:
      iVar19 = iVar19 + 1;
      param_1 = pRVar18;
    } while (iVar19 < iVar10);
  }
  auVar23 = FindMostFreqRangeMinMax(this,(list *)&local_80,2);
  *(undefined1 (*) [16])(this + 0x5c) = auVar23;
  FUN_001509cc(PTR_DAT_001cacc8,&DAT_0018fa98,0x28);
  plVar8 = (long *)FUN_0015124c(PTR_DAT_001cacc8,*(undefined4 *)(this + 0x60));
  plVar15 = *(long **)((long)plVar8 + *(long *)(*plVar8 + -0x18) + 0xf0);
  if (plVar15 == (long *)0x0) {
                    /* WARNING: Subroutine does not return */
    FUN_00167c9c();
  }
  if ((char)plVar15[7] == '\0') {
    FUN_00130710(plVar15);
    uVar4 = 10;
    if (*(code **)(*plVar15 + 0x30) != (code *)PTR_FUN_001cac68) {
      uVar4 = (**(code **)(*plVar15 + 0x30))(plVar15,10);
    }
  }
  else {
    uVar4 = *(undefined1 *)((long)plVar15 + 0x43);
  }
  FUN_00150468(plVar8,uVar4);
  FUN_0014fca4();
  RansacLine(this,(vector *)&local_60,(vector *)&local_30);
  RansacLine(this,(vector *)&local_48,(vector *)&local_18);
  if (local_58 - (long)local_60 >> 3 == 4) {
    if (local_60[1] == 0.0) {
      *(undefined8 *)(this + 0x80) = 0;
      *(double *)(this + 0x88) = local_60[3] - local_60[2] * 0.0;
    }
    else {
      dVar20 = -*local_60 / local_60[1];
      *(double *)(this + 0x80) = dVar20;
      *(double *)(this + 0x88) = local_60[3] - local_60[2] * dVar20;
    }
  }
  else {
    *(undefined8 *)(this + 0x80) = 0;
    *(undefined8 *)(this + 0x88) = 0;
  }
  if (local_40 - (long)local_48 >> 3 == 4) {
    dVar20 = 0.0;
    if (local_48[1] == 0.0) {
      *(undefined8 *)(this + 0x90) = 0;
      *(double *)(this + 0x98) = local_48[3] - local_48[2] * 0.0;
    }
    else {
      dVar20 = -*local_48 / local_48[1];
      *(double *)(this + 0x90) = dVar20;
      *(double *)(this + 0x98) = local_48[3] - local_48[2] * dVar20;
    }
  }
  else {
    dVar20 = 0.0;
    *(undefined8 *)(this + 0x90) = 0;
    *(undefined8 *)(this + 0x98) = 0;
  }
  dVar21 = *(double *)(this + 0x80);
  if (dVar21 <= dVar20) {
    *(double *)(this + 0x90) = dVar21;
    if (local_40 - (long)local_48 >> 3 == 4) {
      *(double *)(this + 0x98) = local_48[3] - local_48[2] * dVar21;
    }
    else {
      *(undefined8 *)(this + 0x98) = 0;
    }
  }
  else {
    *(double *)(this + 0x80) = dVar20;
    if (local_58 - (long)local_60 >> 3 == 4) {
      *(double *)(this + 0x88) = local_60[3] - dVar20 * local_60[2];
    }
    else {
      *(undefined8 *)(this + 0x88) = 0;
    }
  }
  setDigitTargetRectUpDown(this);
  piVar9 = *(int **)(this + 200);
  iVar19 = piVar9[(long)*(int *)(this + 0xa8) * 4 + -4];
  if (*piVar9 < piVar9[(long)*(int *)(this + 0xa8) * 4 + -4]) {
    iVar19 = *piVar9;
  }
  iVar14 = piVar9[(long)*(int *)(this + 0xa8) * 4 + -3];
  if (piVar9[(long)*(int *)(this + 0xa8) * 4 + -3] < piVar9[1]) {
    iVar14 = piVar9[1];
  }
  operator_delete__(__s);
  auVar23._4_4_ = iVar14;
  auVar23._0_4_ = iVar19;
  plVar3 = local_80;
  while (plVar3 != (list *)&local_80) {
    plVar16 = *(list **)plVar3;
    operator_delete(plVar3);
    plVar3 = plVar16;
  }
  if (local_18 != (void *)0x0) {
    operator_delete(local_18);
  }
  if (local_30 != (void *)0x0) {
    operator_delete(local_30);
  }
  if (local_48 != (double *)0x0) {
    operator_delete(local_48);
  }
  if (local_60 != (double *)0x0) {
    operator_delete(local_60);
  }
  auVar23._8_8_ = 0;
  return auVar23;
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::SegDigitInBinaryImg
 * Address : 0011c998
 * Size    : 0x2e4 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::SegDigitInBinaryImg(unsigned char*) */

char __thiscall DigitSegment::SegDigitInBinaryImg(DigitSegment *this,uchar *param_1)

{
  undefined *puVar1;
  char cVar2;
  undefined1 uVar3;
  ulong uVar4;
  undefined8 uVar5;
  long *plVar6;
  long *plVar7;
  DigitSegment *pDVar8;
  undefined8 local_50;
  undefined8 uStack_48;
  undefined4 local_40;
  undefined4 local_30;
  undefined4 uStack_2c;
  undefined4 local_28;
  undefined4 local_24;
  undefined4 local_20;
  undefined4 local_18;
  undefined4 uStack_14;
  undefined4 local_10;
  undefined4 local_c;
  undefined4 local_8;
  
  local_28 = *(undefined4 *)(this + 0xa8);
  local_24 = *(undefined4 *)(this + 0xac);
  local_30 = 0;
  uStack_2c = 0;
  local_20 = 0;
  __android_log_print(3,"libdigit_ocr","begin FindDigitConnectedReigon process\n");
  local_50 = CONCAT44(uStack_2c,local_30);
  uStack_48 = CONCAT44(local_24,local_28);
  local_40 = local_20;
  cVar2 = FindDigitConnectedReigon(this,param_1,&local_50,*(undefined8 *)(this + 0xa0));
  if (cVar2 != '\0') {
    __android_log_print(3,"libdigit_ocr","end FindDigitConnectedReigon process,Reigon Number=%d\n",
                        *(undefined4 *)(this + 0x40));
    uVar4 = FindDigitTargetRect(this,*(RectRegion **)(this + 0x38),1.0,3.5);
    puVar1 = PTR_DAT_001cacc8;
    FUN_001509cc(PTR_DAT_001cacc8," first;",7);
    uVar5 = FUN_0015124c(puVar1,uVar4 & 0xffffffff);
    FUN_001509cc(uVar5,&DAT_0018fb30,2);
    plVar6 = (long *)FUN_0015124c(uVar5,(long)uVar4 >> 0x20 & 0xffffffff);
    plVar7 = *(long **)((long)plVar6 + *(long *)(*plVar6 + -0x18) + 0xf0);
    if (plVar7 == (long *)0x0) {
                    /* WARNING: Subroutine does not return */
      FUN_00167c9c();
    }
    if ((char)plVar7[7] == '\0') {
      FUN_00130710(plVar7);
      uVar3 = 10;
      if (*(code **)(*plVar7 + 0x30) != (code *)PTR_FUN_001cac68) {
        uVar3 = (**(code **)(*plVar7 + 0x30))(plVar7,10);
      }
    }
    else {
      uVar3 = *(undefined1 *)((long)plVar7 + 0x43);
    }
    FUN_00150468(plVar6,uVar3);
    FUN_0014fca4();
    if ((double)*(int *)(this + 0xac) * 0.25 <= (double)*(int *)(this + 0x50)) {
      updateDigitRectIndex(this);
      filterTargetNoiseBinaryImg(this);
      initSplitData(this);
      local_50 = CONCAT44(uStack_2c,local_30);
      uStack_48 = CONCAT44(local_24,local_28);
      local_40 = local_20;
      cVar2 = FindDigitConnectedReigon
                        (this,*(undefined8 *)(this + 0xc0),&local_50,*(undefined8 *)(this + 0xa0));
      if (cVar2 != '\0') {
        updateDigitRectIndex(this);
        local_18 = 0;
        local_10 = *(undefined4 *)(this + 0xa8);
        local_8 = 0;
        uStack_14 = (undefined4)uVar4;
        local_c = (undefined4)(uVar4 >> 0x20);
        pDVar8 = (DigitSegment *)atan(*(double *)(this + 0x80));
        if (ABS((double)pDVar8) <= DAT_0011cc80) {
          return cVar2;
        }
        __android_log_print(3,"libdigit_ocr","begin CorrectBiasAngle process\n");
        local_50 = CONCAT44(uStack_14,local_18);
        uStack_48 = CONCAT44(local_c,local_10);
        local_40 = local_8;
        CorrectBiasAngle(pDVar8,this,this + 0xc0,&local_50);
        initSplitData(this);
        local_28 = *(undefined4 *)(this + 0xa8);
        local_24 = *(undefined4 *)(this + 0xac);
        uStack_48 = *(undefined8 *)(this + 0xa8);
        local_50 = CONCAT44(uStack_2c,local_30);
        local_40 = local_20;
        cVar2 = FindDigitConnectedReigon
                          (this,*(undefined8 *)(this + 0xc0),&local_50,*(undefined8 *)(this + 0xa0))
        ;
        if (cVar2 != '\0') {
          FindDigitTargetRect(this,*(RectRegion **)(this + 0x38),1.0,3.5);
          updateDigitRectIndex(this);
          __android_log_print(3,"libdigit_ocr","end CorrectBiasAngle process\n");
          return cVar2;
        }
      }
    }
  }
  return '\0';
}



/* ------------------------------------------------------------------
 * Function: DigitSegment::SegDigitInGrayImg
 * Address : 0011cc88
 * Size    : 0x1e8 bytes
 * ------------------------------------------------------------------ */

/* DigitSegment::SegDigitInGrayImg(unsigned char*, int, int, int) */

undefined8 __thiscall
DigitSegment::SegDigitInGrayImg
          (DigitSegment *this,uchar *param_1,int param_2,int param_3,int param_4)

{
  char cVar1;
  void *pvVar2;
  undefined8 *puVar3;
  undefined8 *puVar4;
  undefined8 *puVar5;
  
  *(int *)(this + 0xa8) = param_2;
  *(int *)(this + 0xac) = param_3;
  *(uchar **)(this + 0xb0) = param_1;
  Init(this);
  rgbToGray(*(uchar **)(this + 0xb0),*(uchar **)(this + 0xb8),param_4,*(int *)(this + 0xa8),
            *(int *)(this + 0xac));
  adaptiveThreshold(*(uchar **)(this + 0xb8),(uchar **)(this + 0xc0),*(int *)(this + 0xa8),
                    *(int *)(this + 0xac));
  cVar1 = SegDigitInBinaryImg(this,*(uchar **)(this + 0xc0));
  if (cVar1 == '\0') {
    __android_log_print(3,"libdigit_ocr",&DAT_0018fb78);
    puVar5 = *(undefined8 **)(this + 0xd0);
    puVar4 = *(undefined8 **)(this + 0xd8);
    puVar3 = puVar5;
    if (puVar5 != puVar4) {
      do {
        pvVar2 = (void *)*puVar3;
        puVar3 = puVar3 + 2;
        if (pvVar2 != (void *)0x0) {
          operator_delete__(pvVar2);
        }
      } while (puVar4 != puVar3);
      *(undefined8 **)(this + 0xd8) = puVar5;
      clear(this);
      return 0;
    }
  }
  else {
    __android_log_print(3,"libdigit_ocr","end SegDigitInBinaryImg process\n");
    __android_log_print(3,"libdigit_ocr","begin mergeClass process\n");
    FindDigitFromTargetRect
              ((int *)this,*(int **)(this + 0x28),*(RectRegion **)(this + 0x30),
               (int)*(undefined8 *)(this + 0x38));
    __android_log_print(3,"libdigit_ocr","end mergeClass process\n");
    if (*(int *)(this + 0x50) <= *(int *)(this + 0x60) * 4) {
      SetDigitMatVector(this);
      __android_log_print(3,"libdigit_ocr","end SetDigitMatVector process\n");
      clear(this);
      __android_log_print(3,"libdigit_ocr","end clear process\n");
      return 1;
    }
    __android_log_print(3,"libdigit_ocr",&DAT_0018fb78);
    puVar5 = *(undefined8 **)(this + 0xd0);
    puVar4 = *(undefined8 **)(this + 0xd8);
    puVar3 = puVar5;
    while (puVar3 != puVar4) {
      pvVar2 = (void *)*puVar3;
      puVar3 = puVar3 + 2;
      if (pvVar2 != (void *)0x0) {
        operator_delete__(pvVar2);
      }
    }
  }
  *(undefined8 **)(this + 0xd8) = puVar5;
  clear(this);
  return 0;
}



/* ------------------------------------------------------------------
 * Function: svm_predict_values
 * Address : 00122640
 * Size    : 0x3d0 bytes
 * ------------------------------------------------------------------ */

undefined1  [16] svm_predict_values(svm_parameter *param_1,svm_node *param_2,double *param_3)

{
  uint uVar1;
  int iVar2;
  long lVar3;
  long lVar4;
  int iVar5;
  void *__ptr;
  undefined4 *__ptr_00;
  undefined8 *puVar6;
  long lVar7;
  long lVar8;
  uint uVar9;
  uint uVar10;
  long lVar11;
  ulong uVar12;
  long lVar13;
  long lVar14;
  ulong uVar15;
  long lVar16;
  long lVar17;
  uint uVar18;
  undefined8 *puVar19;
  long lVar20;
  ulong uVar21;
  ulong uVar22;
  long lVar23;
  double dVar24;
  undefined8 uVar25;
  undefined1 auVar26 [16];
  undefined1 auVar27 [16];
  double dVar28;
  double dVar29;
  
  iVar5 = *(int *)param_1;
  if (iVar5 - 2U < 3) {
    dVar28 = 0.0;
    lVar23 = **(long **)(param_1 + 0x78);
    if (0 < *(int *)(param_1 + 0x6c)) {
      lVar20 = 0;
      iVar5 = 0;
      do {
        dVar29 = *(double *)(lVar23 + lVar20);
        iVar5 = iVar5 + 1;
        puVar6 = (undefined8 *)(*(long *)(param_1 + 0x70) + lVar20);
        lVar20 = lVar20 + 8;
        dVar24 = (double)Kernel::k_function(param_2,(svm_node *)*puVar6,param_1);
        dVar28 = dVar28 + dVar24 * dVar29;
      } while (iVar5 < *(int *)(param_1 + 0x6c));
      iVar5 = *(int *)param_1;
    }
    dVar28 = dVar28 - **(double **)(param_1 + 0x80);
    *param_3 = dVar28;
    dVar24 = dVar28;
    if ((iVar5 == 2) && (dVar24 = 1.0, dVar28 <= 0.0)) {
      return ZEXT816(0xbff0000000000000);
    }
    auVar26._8_8_ = 0;
    auVar26._0_8_ = dVar24;
    return auVar26;
  }
  uVar18 = *(uint *)(param_1 + 0x6c);
  uVar1 = *(uint *)(param_1 + 0x68);
  __ptr = malloc(-(ulong)(uVar18 >> 0x1f) & 0xfffffff800000000 | (ulong)uVar18 << 3);
  if (0 < (int)uVar18) {
    lVar23 = 0;
    do {
      uVar25 = Kernel::k_function(param_2,*(svm_node **)(*(long *)(param_1 + 0x70) + lVar23),param_1
                                 );
      *(undefined8 *)((long)__ptr + lVar23) = uVar25;
      lVar23 = lVar23 + 8;
    } while (lVar23 != ((ulong)(uVar18 - 1) + 1) * 8);
  }
  uVar22 = -(ulong)(uVar1 >> 0x1f) & 0xfffffffc00000000 | (ulong)uVar1 << 2;
  __ptr_00 = malloc(uVar22);
  *__ptr_00 = 0;
  if ((int)uVar1 < 2) {
    puVar6 = malloc(uVar22);
    if (uVar1 == 1) goto LAB_001227d8;
  }
  else {
    lVar20 = *(long *)(param_1 + 0xa0);
    lVar23 = 0;
    do {
      lVar13 = lVar23 + 4;
      *(int *)((long)__ptr_00 + lVar23 + 4) =
           *(int *)((long)__ptr_00 + lVar23) + *(int *)(lVar20 + lVar23);
      lVar23 = lVar13;
    } while (lVar13 != ((ulong)(uVar1 - 2) + 1) * 4);
    puVar6 = malloc(uVar22);
LAB_001227d8:
    uVar18 = (uVar1 - 4 >> 2) + 1;
    uVar10 = uVar18 * 4;
    uVar22 = (ulong)uVar10;
    if (uVar1 - 1 < 3) {
      uVar22 = 0;
LAB_00122824:
      uVar15 = -(uVar22 >> 0x1f) & 0xfffffffc00000000 | uVar22 << 2;
      *(undefined4 *)((long)puVar6 + uVar15) = 0;
      if (((int)uVar22 + 1 < (int)uVar1) &&
         (*(undefined4 *)((long)puVar6 + uVar15 + 4) = 0, (int)uVar22 + 2 < (int)uVar1)) {
        *(undefined4 *)((long)puVar6 + uVar15 + 8) = 0;
      }
    }
    else {
      uVar9 = 0;
      puVar19 = puVar6;
      do {
        puVar19[1] = 0;
        *puVar19 = 0;
        uVar9 = uVar9 + 1;
        puVar19 = puVar19 + 2;
      } while (uVar9 < uVar18);
      if (uVar1 != uVar10) goto LAB_00122824;
    }
    lVar23 = 0;
    lVar20 = 4;
    uVar22 = 0;
    puVar19 = puVar6;
    for (uVar18 = uVar1; uVar18 != 1; uVar18 = uVar18 - 1) {
      puVar19 = (undefined8 *)((long)puVar19 + 4);
      lVar11 = *(long *)(param_1 + 0xa0);
      uVar15 = -(uVar22 >> 0x1f) & 0xfffffff800000000 | uVar22 << 3;
      lVar7 = *(long *)(param_1 + 0x80);
      lVar14 = *(long *)(param_1 + 0x78) + lVar20 * 2;
      lVar16 = (long)*(int *)((long)__ptr_00 + lVar23) * 8;
      lVar17 = *(long *)(*(long *)(param_1 + 0x78) + lVar23 * 2);
      iVar5 = *(int *)(lVar11 + lVar23);
      lVar13 = 0;
      do {
        uVar10 = *(uint *)((long)__ptr_00 + lVar13 * 4 + lVar20);
        dVar28 = 0.0;
        iVar2 = *(int *)(lVar11 + lVar20 + lVar13 * 4);
        if (0 < iVar5) {
          lVar8 = 0;
          do {
            lVar3 = lVar8 * 8;
            lVar4 = lVar8 * 8;
            lVar8 = lVar8 + 1;
            dVar28 = dVar28 + *(double *)((long)__ptr + lVar4 + lVar16) *
                              *(double *)(*(long *)(lVar14 + -8) + lVar16 + lVar3);
          } while ((int)lVar8 < iVar5);
        }
        if (0 < iVar2) {
          uVar12 = -(ulong)(uVar10 >> 0x1f) & 0xfffffff800000000 | (ulong)uVar10 << 3;
          lVar8 = 0;
          do {
            lVar3 = lVar8 * 8;
            lVar4 = lVar8 * 8;
            lVar8 = lVar8 + 1;
            dVar28 = dVar28 + *(double *)((long)__ptr + lVar4 + uVar12) *
                              *(double *)(lVar17 + uVar12 + lVar3);
          } while ((int)lVar8 < iVar2);
        }
        dVar28 = dVar28 - *(double *)(lVar7 + uVar15 + lVar13 * 8);
        *(double *)((long)param_3 + lVar13 * 8 + uVar15) = dVar28;
        if (dVar28 <= 0.0) {
          *(int *)((long)puVar19 + lVar13 * 4) = *(int *)((long)puVar19 + lVar13 * 4) + 1;
        }
        else {
          *(int *)((long)puVar6 + lVar23) = *(int *)((long)puVar6 + lVar23) + 1;
        }
        lVar13 = lVar13 + 1;
        lVar14 = lVar14 + 8;
      } while (lVar13 != (ulong)(uVar18 - 2) + 1);
      lVar23 = lVar23 + 4;
      uVar22 = (ulong)((uVar18 + (int)uVar22) - 1);
      lVar20 = lVar20 + 4;
    }
    if (1 < (int)uVar1) {
      uVar22 = 4;
      uVar15 = 0;
      uVar18 = 1;
      do {
        uVar12 = -(uVar15 >> 0x1f) & 0xfffffffc00000000 | uVar15 << 2;
        iVar5 = *(int *)((long)puVar6 + uVar12);
        uVar10 = uVar18;
        if (*(int *)((long)puVar6 + uVar22) <= iVar5) {
          uVar10 = (uint)uVar15;
        }
        uVar15 = (ulong)uVar10;
        uVar18 = uVar18 + 1;
        uVar21 = uVar22;
        if (*(int *)((long)puVar6 + uVar22) <= iVar5) {
          uVar21 = uVar12;
        }
        uVar22 = uVar22 + 4;
      } while (uVar18 != uVar1);
      goto LAB_001229a4;
    }
  }
  uVar21 = 0;
LAB_001229a4:
  free(__ptr);
  free(__ptr_00);
  free(puVar6);
  auVar27._0_8_ = (double)*(int *)(*(long *)(param_1 + 0x98) + uVar21);
  auVar27._8_8_ = 0;
  return auVar27;
}



/* ------------------------------------------------------------------
 * Function: svm_predict
 * Address : 00122a10
 * Size    : 0x88 bytes
 * ------------------------------------------------------------------ */

undefined1  [16] svm_predict(int *param_1,undefined8 param_2)

{
  uint uVar1;
  undefined1 auVar2 [16];
  void *__ptr;
  
  if (*param_1 - 2U < 3) {
    __ptr = malloc(8);
  }
  else {
    uVar1 = ((param_1[0x1a] + -1) * param_1[0x1a]) / 2;
    __ptr = malloc(-(ulong)(uVar1 >> 0x1f) & 0xfffffff800000000 | (ulong)uVar1 << 3);
  }
  auVar2 = svm_predict_values(param_1,param_2,__ptr);
  free(__ptr);
  return auVar2;
}



/* ------------------------------------------------------------------
 * Function: svm_predict_probability
 * Address : 00122a98
 * Size    : 0x648 bytes
 * ------------------------------------------------------------------ */

undefined1  [16] svm_predict_probability(uint *param_1,undefined8 param_2,double *param_3)

{
  uint uVar1;
  uint uVar2;
  uint uVar3;
  uint uVar4;
  int iVar5;
  void *__ptr;
  void *__ptr_00;
  void *pvVar6;
  double *pdVar7;
  void *pvVar8;
  double *pdVar9;
  uint uVar10;
  ulong uVar11;
  uint uVar12;
  long lVar13;
  long lVar14;
  double *pdVar15;
  double *pdVar16;
  double *pdVar17;
  uint uVar18;
  long lVar19;
  long lVar20;
  long lVar21;
  int iVar22;
  ulong uVar23;
  double dVar24;
  undefined1 auVar25 [16];
  double dVar26;
  double dVar27;
  double dVar28;
  double dVar29;
  double dVar30;
  double dVar31;
  double dVar32;
  
  if (((1 < *param_1) || (*(long *)(param_1 + 0x22) == 0)) || (*(long *)(param_1 + 0x24) == 0)) {
    auVar25 = svm_predict();
    return auVar25;
  }
  uVar3 = param_1[0x1a];
  uVar11 = -(ulong)(uVar3 >> 0x1f) & 0xfffffff800000000 | (ulong)uVar3 << 3;
  uVar10 = (int)(uVar3 * (uVar3 - 1)) / 2;
  __ptr = malloc(-(ulong)(uVar10 >> 0x1f) & 0xfffffff800000000 | (ulong)uVar10 << 3);
  svm_predict_values(param_1,param_2,__ptr);
  __ptr_00 = malloc(uVar11);
  if ((int)uVar3 < 1) {
    uVar10 = 100;
    pvVar6 = malloc(uVar11);
    pdVar7 = malloc(uVar11);
    dVar32 = DAT_001230f0 / (double)(int)uVar3;
  }
  else {
    lVar19 = 0;
    do {
      pvVar6 = malloc(uVar11);
      *(void **)((long)__ptr_00 + lVar19 * 8) = pvVar6;
      dVar27 = DAT_001230e8;
      dVar32 = DAT_001230e0;
      lVar19 = lVar19 + 1;
    } while ((int)lVar19 < (int)uVar3);
    iVar22 = 0;
    lVar19 = 0;
    for (uVar10 = uVar3; uVar10 != 1; uVar10 = uVar10 - 1) {
      lVar20 = (long)iVar22 << 3;
      lVar21 = ((long)iVar22 + 1 + (ulong)(uVar10 - 2)) * 8;
      lVar14 = lVar19 + 8;
      do {
        while( true ) {
          pdVar7 = (double *)(*(long *)((long)__ptr_00 + lVar19) + lVar14);
          dVar26 = *(double *)(*(long *)(param_1 + 0x24) + lVar20) +
                   *(double *)(*(long *)(param_1 + 0x22) + lVar20) *
                   *(double *)((long)__ptr + lVar20);
          if (dVar26 < 0.0) {
            dVar26 = exp(dVar26);
            dVar24 = 1.0 / (dVar26 + 1.0);
          }
          else {
            dVar24 = exp(-dVar26);
            dVar26 = exp(-dVar26);
            dVar24 = dVar24 / (dVar26 + 1.0);
          }
          if (dVar27 < dVar24) break;
          lVar13 = *(long *)((long)__ptr_00 + lVar14);
          lVar20 = lVar20 + 8;
          lVar14 = lVar14 + 8;
          *pdVar7 = dVar27;
          *(double *)(lVar13 + lVar19) = 1.0 - dVar27;
          if (lVar20 == lVar21) goto LAB_00122c7c;
        }
        if (dVar32 <= dVar24) {
          dVar24 = dVar32;
        }
        lVar13 = *(long *)((long)__ptr_00 + lVar14);
        lVar20 = lVar20 + 8;
        lVar14 = lVar14 + 8;
        *pdVar7 = dVar24;
        *(double *)(lVar13 + lVar19) = 1.0 - dVar24;
      } while (lVar20 != lVar21);
LAB_00122c7c:
      iVar22 = iVar22 + uVar10 + -1;
      lVar19 = lVar19 + 8;
    }
    uVar10 = uVar3;
    if ((int)uVar3 < 100) {
      uVar10 = 100;
    }
    pvVar6 = malloc(uVar11);
    uVar23 = 0;
    pdVar7 = malloc(uVar11);
    dVar32 = DAT_001230f0 / (double)(int)uVar3;
    lVar19 = 0;
    while( true ) {
      *(double *)((long)param_3 + lVar19) = 1.0 / (double)(int)uVar3;
      pvVar8 = malloc(uVar11);
      *(void **)((long)pvVar6 + lVar19) = pvVar8;
      iVar22 = (int)uVar23;
      pdVar9 = (double *)((long)pvVar8 + lVar19);
      *(undefined8 *)((long)pvVar8 + lVar19) = 0;
      if (0 < iVar22) {
        dVar27 = 0.0;
        lVar20 = 0;
        while( true ) {
          lVar14 = *(long *)((long)pvVar6 + lVar20 * 8);
          dVar26 = *(double *)(*(long *)((long)__ptr_00 + lVar20 * 8) + lVar19);
          *pdVar9 = dVar27 + dVar26 * dVar26;
          *(undefined8 *)((long)pvVar8 + lVar20 * 8) = *(undefined8 *)(lVar14 + lVar19);
          lVar20 = lVar20 + 1;
          if (iVar22 <= (int)lVar20) break;
          dVar27 = *pdVar9;
        }
      }
      if (uVar23 == uVar3 - 1) break;
      lVar21 = *(long *)((long)__ptr_00 + lVar19);
      lVar20 = lVar19 + 8;
      lVar14 = 0;
      do {
        dVar26 = *(double *)(lVar21 + lVar20 + lVar14);
        dVar27 = *(double *)(*(long *)((long)__ptr_00 + lVar14 + lVar20) + lVar19);
        *pdVar9 = *pdVar9 + dVar27 * dVar27;
        *(double *)((long)pvVar8 + lVar14 + lVar20) = -(dVar27 * dVar26);
        lVar14 = lVar14 + 8;
      } while (lVar14 != ((ulong)((uVar3 - 2) - iVar22) + 1) * 8);
      uVar23 = uVar23 + 1;
      lVar19 = lVar20;
    }
  }
  uVar18 = 0;
  uVar2 = uVar3;
  if ((int)uVar3 < 1) {
    uVar2 = 1;
  }
  uVar1 = (uVar2 - 2 >> 1) + 1;
  uVar4 = uVar1 * 2;
  uVar12 = uVar4;
  if ((int)uVar3 < 2) {
    uVar12 = 0;
  }
  uVar11 = -(ulong)(uVar12 >> 0x1f) & 0xfffffff800000000 | (ulong)uVar12 << 3;
  do {
    lVar19 = 0;
    dVar27 = 0.0;
    if ((int)uVar3 < 1) {
      if (0.0 < dVar32) {
        if ((int)uVar10 <= (int)uVar18) {
          FUN_0011d74c("Exceeds max_iter in multiclass_prob\n");
        }
        goto LAB_00123080;
      }
    }
    else {
      do {
        dVar26 = 0.0;
        lVar20 = 0;
        do {
          lVar14 = lVar20 * 8;
          pdVar9 = param_3 + lVar20;
          lVar20 = lVar20 + 1;
          dVar26 = dVar26 + *pdVar9 * *(double *)(*(long *)((long)pvVar6 + lVar19 * 8) + lVar14);
        } while ((int)lVar20 < (int)uVar3);
        dVar24 = param_3[lVar19];
        pdVar7[lVar19] = dVar26;
        lVar19 = lVar19 + 1;
        dVar27 = dVar27 + dVar26 * dVar24;
      } while ((int)lVar19 < (int)uVar3);
      lVar19 = 0;
      dVar26 = 0.0;
      do {
        pdVar9 = pdVar7 + lVar19;
        lVar19 = lVar19 + 1;
        dVar24 = ABS(*pdVar9 - dVar27);
        if (ABS(*pdVar9 - dVar27) <= dVar26) {
          dVar24 = dVar26;
        }
        dVar26 = dVar24;
      } while ((int)lVar19 < (int)uVar3);
      if (dVar24 < dVar32) {
        if ((int)uVar10 <= (int)uVar18) {
          FUN_0011d74c("Exceeds max_iter in multiclass_prob\n");
        }
        goto LAB_00122f9c;
      }
      lVar19 = 0;
      do {
        dVar28 = pdVar7[lVar19];
        pdVar9 = *(double **)((long)pvVar6 + lVar19 * 8);
        dVar24 = pdVar9[lVar19];
        dVar26 = (dVar27 - dVar28) / dVar24;
        dVar29 = dVar26 + 1.0;
        param_3[lVar19] = param_3[lVar19] + dVar26;
        dVar27 = ((dVar27 + (dVar28 + dVar28 + dVar26 * dVar24) * dVar26) / dVar29) / dVar29;
        if (uVar3 == 1) {
LAB_00122f4c:
          dVar24 = *(double *)((long)pdVar7 + uVar11);
          dVar28 = *(double *)((long)pdVar9 + uVar11);
          *(double *)((long)param_3 + uVar11) = *(double *)((long)param_3 + uVar11) / dVar29;
          *(double *)((long)pdVar7 + uVar11) = (dVar24 + dVar28 * dVar26) / dVar29;
        }
        else {
          uVar12 = 0;
          pdVar15 = pdVar7;
          pdVar16 = param_3;
          pdVar17 = pdVar9;
          do {
            dVar28 = pdVar15[1];
            dVar24 = *pdVar15;
            uVar12 = uVar12 + 1;
            dVar31 = pdVar17[1];
            dVar30 = *pdVar17;
            pdVar16[1] = pdVar16[1] / dVar29;
            *pdVar16 = *pdVar16 / dVar29;
            pdVar15[1] = (dVar28 + dVar26 * dVar31) / dVar29;
            *pdVar15 = (dVar24 + dVar26 * dVar30) / dVar29;
            pdVar15 = pdVar15 + 2;
            pdVar16 = pdVar16 + 2;
            pdVar17 = pdVar17 + 2;
          } while (uVar12 < uVar1);
          if (uVar2 != uVar4) goto LAB_00122f4c;
        }
        lVar19 = lVar19 + 1;
      } while ((int)lVar19 < (int)uVar3);
    }
    uVar18 = uVar18 + 1;
  } while (uVar18 != uVar10);
  FUN_0011d74c("Exceeds max_iter in multiclass_prob\n");
  if ((int)uVar3 < 1) {
LAB_00123080:
    lVar19 = 0;
    free(pvVar6);
    free(pdVar7);
  }
  else {
LAB_00122f9c:
    lVar19 = 0;
    do {
      lVar20 = lVar19 * 8;
      lVar19 = lVar19 + 1;
      free(*(void **)((long)pvVar6 + lVar20));
    } while ((int)lVar19 < (int)uVar3);
    free(pvVar6);
    free(pdVar7);
    lVar20 = 1;
    iVar22 = 0;
    if ((int)uVar3 < 2) {
      lVar19 = 0;
    }
    else {
      do {
        lVar19 = lVar20;
        iVar5 = (int)lVar20;
        if (param_3[lVar20] <= param_3[iVar22]) {
          lVar19 = (long)iVar22;
          iVar5 = iVar22;
        }
        iVar22 = iVar5;
        lVar20 = lVar20 + 1;
      } while ((int)lVar20 < (int)uVar3);
      lVar19 = lVar19 << 2;
      if ((int)uVar3 < 1) goto LAB_00123020;
    }
    lVar20 = 0;
    do {
      lVar14 = lVar20 * 8;
      lVar20 = lVar20 + 1;
      free(*(void **)((long)__ptr_00 + lVar14));
    } while ((int)lVar20 < (int)uVar3);
  }
LAB_00123020:
  free(__ptr);
  free(__ptr_00);
  auVar25._0_8_ = (double)*(int *)(*(long *)(param_1 + 0x26) + lVar19);
  auVar25._8_8_ = 0;
  return auVar25;
}



/* ------------------------------------------------------------------
 * Function: svm_load_model
 * Address : 00123578
 * Size    : 0xac8 bytes
 * ------------------------------------------------------------------ */

void svm_load_model(char *param_1)

{
  uint uVar1;
  int iVar2;
  ulong uVar3;
  ulong uVar4;
  uint uVar5;
  double dVar6;
  int iVar7;
  int iVar8;
  FILE *__stream;
  FILE *__stream_00;
  int *__ptr;
  void *pvVar9;
  long lVar10;
  char *pcVar11;
  void *pvVar12;
  long *plVar13;
  long lVar14;
  char *__nptr;
  long lVar15;
  size_t __size;
  uint *puVar16;
  undefined **ppuVar17;
  ulong uVar18;
  long lVar19;
  long lVar20;
  double dVar21;
  char *pcStack_68;
  char acStack_60 [88];
  long local_8;
  
  local_8 = *(long *)PTR___stack_chk_guard_001cad58;
  __stream = fopen(param_1,"rb");
  if (__stream == (FILE *)0x0) {
    __ptr = (int *)0x0;
LAB_001238c8:
    if (local_8 == *(long *)PTR___stack_chk_guard_001cad58) {
      return;
    }
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail(__ptr);
  }
  __stream_00 = fopen("D:\\123.txt","a+");
  __ptr = malloc(0xb0);
  __ptr[0x20] = 0;
  __ptr[0x21] = 0;
  __ptr[0x22] = 0;
  __ptr[0x23] = 0;
  __ptr[0x24] = 0;
  __ptr[0x25] = 0;
  __ptr[0x26] = 0;
  __ptr[0x27] = 0;
  __ptr[0x28] = 0;
  __ptr[0x29] = 0;
LAB_00123634:
  while( true ) {
    while( true ) {
      while( true ) {
        while( true ) {
          while( true ) {
            while( true ) {
              while( true ) {
                fscanf(__stream,"%80s",acStack_60);
                fprintf(__stream_00,"%s ",acStack_60);
                iVar7 = strcmp(acStack_60,"svm_type");
                if (iVar7 != 0) break;
                ppuVar17 = &PTR_s_c_svc_001c5e60;
                fscanf(__stream,"%80s",acStack_60);
                pcVar11 = "c_svc";
                fprintf(__stream_00,"%s\n",acStack_60);
                iVar7 = 0;
                while (iVar8 = strcmp(pcVar11,acStack_60), iVar8 != 0) {
                  ppuVar17 = ppuVar17 + 1;
                  pcVar11 = *ppuVar17;
                  iVar7 = iVar7 + 1;
                  if (pcVar11 == (char *)0x0) {
                    fwrite("unknown svm type.\n",1,0x12,(FILE *)(PTR___sF_001cad30 + 0x130));
                    goto LAB_001238a4;
                  }
                }
                *__ptr = iVar7;
              }
              iVar7 = strcmp(acStack_60,"kernel_type");
              if (iVar7 != 0) break;
              fscanf(__stream,"%80s",acStack_60);
              ppuVar17 = &PTR_s_linear_001c5e90;
              pcVar11 = "linear";
              fprintf(__stream_00,"%s\n",acStack_60);
              iVar7 = 0;
              while (iVar8 = strcmp(pcVar11,acStack_60), iVar8 != 0) {
                ppuVar17 = ppuVar17 + 1;
                pcVar11 = *ppuVar17;
                iVar7 = iVar7 + 1;
                if (pcVar11 == (char *)0x0) {
                  fwrite("unknown kernel function.\n",1,0x19,(FILE *)(PTR___sF_001cad30 + 0x130));
                  goto LAB_001238a4;
                }
              }
              __ptr[1] = iVar7;
            }
            iVar7 = strcmp(acStack_60,"degree");
            if (iVar7 != 0) break;
            fscanf(__stream,"%d",__ptr + 2);
            fprintf(__stream_00,"%d\n",(ulong)(uint)__ptr[2]);
          }
          iVar7 = strcmp(acStack_60,"gamma");
          if (iVar7 != 0) break;
          fscanf(__stream,"%lf",__ptr + 4);
          fprintf(__stream_00,"%lf\n",*(undefined8 *)(__ptr + 4));
        }
        iVar7 = strcmp(acStack_60,"coef0");
        if (iVar7 != 0) break;
        fscanf(__stream,"%lf",__ptr + 6);
        fprintf(__stream_00,"%lf\n",*(undefined8 *)(__ptr + 6));
      }
      iVar7 = strcmp(acStack_60,"nr_class");
      if (iVar7 != 0) break;
      fscanf(__stream,"%d",__ptr + 0x1a);
      fprintf(__stream_00,"%d\n",(ulong)(uint)__ptr[0x1a]);
    }
    iVar7 = strcmp(acStack_60,"total_sv");
    if (iVar7 != 0) break;
    fscanf(__stream,"%d",__ptr + 0x1b);
    fprintf(__stream_00,"%d\n",(ulong)(uint)__ptr[0x1b]);
  }
  iVar7 = strcmp(acStack_60,"rho");
  if (iVar7 == 0) {
    uVar1 = (__ptr[0x1a] * (__ptr[0x1a] + -1)) / 2;
    pvVar9 = malloc(-(ulong)(uVar1 >> 0x1f) & 0xfffffff800000000 | (ulong)uVar1 << 3);
    *(void **)(__ptr + 0x20) = pvVar9;
    if (0 < (int)uVar1) {
      lVar15 = 0;
      while( true ) {
        fscanf(__stream,"%lf",(long)pvVar9 + lVar15);
        fprintf(__stream_00,"%lf ",*(undefined8 *)(*(long *)(__ptr + 0x20) + lVar15));
        if (lVar15 == (ulong)(uVar1 - 1) * 8) break;
        pvVar9 = *(void **)(__ptr + 0x20);
        lVar15 = lVar15 + 8;
      }
      fputc(10,__stream_00);
      goto LAB_00123634;
    }
  }
  else {
    iVar7 = strcmp(acStack_60,"label");
    if (iVar7 == 0) {
      uVar1 = __ptr[0x1a];
      pvVar9 = malloc(-(ulong)(uVar1 >> 0x1f) & 0xfffffffc00000000 | (ulong)uVar1 << 2);
      *(void **)(__ptr + 0x26) = pvVar9;
      if ((int)uVar1 < 1) goto LAB_001237b8;
      lVar15 = 0;
      while( true ) {
        fscanf(__stream,"%d",(long)pvVar9 + lVar15);
        fprintf(__stream_00,"%d ",(ulong)*(uint *)(*(long *)(__ptr + 0x26) + lVar15));
        if (lVar15 == (ulong)(uVar1 - 1) * 4) break;
        pvVar9 = *(void **)(__ptr + 0x26);
        lVar15 = lVar15 + 4;
      }
      fputc(10,__stream_00);
      goto LAB_00123634;
    }
    iVar7 = strcmp(acStack_60,"probA");
    if (iVar7 == 0) {
      uVar1 = (__ptr[0x1a] * (__ptr[0x1a] + -1)) / 2;
      pvVar9 = malloc(-(ulong)(uVar1 >> 0x1f) & 0xfffffff800000000 | (ulong)uVar1 << 3);
      *(void **)(__ptr + 0x22) = pvVar9;
      if (0 < (int)uVar1) {
        lVar15 = 0;
        while( true ) {
          fscanf(__stream,"%lf",(long)pvVar9 + lVar15);
          fprintf(__stream_00,"%lf ",*(undefined8 *)(*(long *)(__ptr + 0x22) + lVar15));
          if (lVar15 == (ulong)(uVar1 - 1) * 8) break;
          pvVar9 = *(void **)(__ptr + 0x22);
          lVar15 = lVar15 + 8;
        }
        fputc(10,__stream_00);
        goto LAB_00123634;
      }
    }
    else {
      iVar7 = strcmp(acStack_60,"probB");
      if (iVar7 != 0) {
        iVar7 = strcmp(acStack_60,"nr_sv");
        if (iVar7 == 0) {
          uVar1 = __ptr[0x1a];
          pvVar9 = malloc(-(ulong)(uVar1 >> 0x1f) & 0xfffffffc00000000 | (ulong)uVar1 << 2);
          *(void **)(__ptr + 0x28) = pvVar9;
          if (0 < (int)uVar1) {
            lVar15 = 0;
            while( true ) {
              fscanf(__stream,"%d",(long)pvVar9 + lVar15);
              fprintf(__stream_00,"%d ",(ulong)*(uint *)(*(long *)(__ptr + 0x28) + lVar15));
              if (lVar15 == (ulong)(uVar1 - 1) * 4) break;
              pvVar9 = *(void **)(__ptr + 0x28);
              lVar15 = lVar15 + 4;
            }
          }
          goto LAB_001237b8;
        }
        iVar7 = strcmp(acStack_60,"SV");
        if (iVar7 == 0) {
          do {
            iVar7 = getc(__stream);
            if (iVar7 == 10) break;
          } while (iVar7 != -1);
          iVar7 = 0;
          fputc(10,__stream_00);
          lVar15 = ftell(__stream);
          DAT_001cb198 = 0x400;
          DAT_001cb190 = malloc(0x400);
          while (lVar10 = FUN_0011d880(__stream), lVar10 != 0) {
            strtok(DAT_001cb190,":");
            while (pcVar11 = strtok((char *)0x0,":"), pcVar11 != (char *)0x0) {
              iVar7 = iVar7 + 1;
            }
          }
          iVar8 = __ptr[0x1b];
          fseek(__stream,lVar15,0);
          iVar2 = __ptr[0x1a];
          uVar1 = __ptr[0x1b];
          uVar5 = iVar2 - 1;
          pvVar9 = malloc(-(ulong)(uVar5 >> 0x1f) & 0xfffffff800000000 | (ulong)uVar5 << 3);
          *(void **)(__ptr + 0x1e) = pvVar9;
          __size = -(ulong)(uVar1 >> 0x1f) & 0xfffffff800000000 | (ulong)uVar1 << 3;
          if (0 < (int)uVar5) {
            __size = -(ulong)(uVar1 >> 0x1f) & 0xfffffff800000000 | (ulong)uVar1 << 3;
            lVar15 = 0;
            while( true ) {
              pvVar12 = malloc(__size);
              *(void **)((long)pvVar9 + lVar15) = pvVar12;
              if (lVar15 == (ulong)(iVar2 - 2) * 8) break;
              pvVar9 = *(void **)(__ptr + 0x1e);
              lVar15 = lVar15 + 8;
            }
          }
          pvVar9 = malloc(__size);
          *(void **)(__ptr + 0x1c) = pvVar9;
          if (0 < (int)uVar1) {
            lVar15 = 0;
            uVar18 = 0;
            pvVar9 = malloc(-(ulong)((uint)(iVar7 + iVar8) >> 0x1f) & 0xfffffff000000000 |
                            (ulong)(uint)(iVar7 + iVar8) << 4);
            dVar6 = DAT_00124040;
            lVar10 = ((ulong)(iVar2 - 3) + 2) * 8;
            do {
              puVar16 = (uint *)((long)pvVar9 + (long)(int)uVar18 * 0x10);
              FUN_0011d880(__stream);
              pcVar11 = DAT_001cb190;
              *(uint **)(*(long *)(__ptr + 0x1c) + lVar15) = puVar16;
              pcVar11 = strtok(pcVar11," \t");
              lVar19 = **(long **)(__ptr + 0x1e);
              dVar21 = strtod(pcVar11,&pcStack_68);
              plVar13 = *(long **)(__ptr + 0x1e);
              *(double *)(lVar19 + lVar15) = dVar21;
              if (dVar6 <= ABS(*(double *)(*plVar13 + lVar15))) {
                fprintf(__stream_00,"%lf ");
              }
              else {
                fwrite(&DAT_0018ffd8,1,2,__stream_00);
              }
              lVar19 = 8;
              if (1 < (int)uVar5) {
                do {
                  while( true ) {
                    pcVar11 = strtok((char *)0x0," \t");
                    lVar20 = *(long *)(*(long *)(__ptr + 0x1e) + lVar19);
                    dVar21 = strtod(pcVar11,&pcStack_68);
                    lVar14 = *(long *)(__ptr + 0x1e);
                    *(double *)(lVar20 + lVar15) = dVar21;
                    if (ABS(*(double *)(*(long *)(lVar14 + lVar19) + lVar15)) < dVar6) break;
                    lVar19 = lVar19 + 8;
                    fprintf(__stream_00,"%lf ");
                    if (lVar19 == lVar10) goto LAB_00123f28;
                  }
                  lVar19 = lVar19 + 8;
                  fwrite(&DAT_0018ffd8,1,2,__stream_00);
                } while (lVar19 != lVar10);
              }
LAB_00123f28:
              while( true ) {
                pcVar11 = strtok((char *)0x0,":");
                __nptr = strtok((char *)0x0," \t");
                if (__nptr == (char *)0x0) break;
                lVar19 = strtol(pcVar11,&pcStack_68,10);
                *puVar16 = (uint)lVar19;
                dVar21 = strtod(__nptr,&pcStack_68);
                *(double *)(puVar16 + 2) = dVar21;
                if (dVar6 < ABS(dVar21)) {
                  fprintf(__stream_00,"%d:%.3f ",(ulong)*puVar16);
                }
                uVar18 = (ulong)((int)uVar18 + 1);
                puVar16 = puVar16 + 4;
              }
              uVar3 = uVar18 << 4;
              uVar4 = uVar18 >> 0x1f;
              lVar15 = lVar15 + 8;
              fputc(10,__stream_00);
              uVar18 = (ulong)((int)uVar18 + 1);
              *(undefined4 *)((long)pvVar9 + (-uVar4 & 0xfffffff000000000 | uVar3)) = 0xffffffff;
            } while (lVar15 != ((ulong)(uVar1 - 1) + 1) * 8);
          }
          free(DAT_001cb190);
          iVar7 = ferror(__stream);
          if ((((iVar7 == 0) && (iVar7 = fclose(__stream), iVar7 == 0)) &&
              (iVar7 = ferror(__stream_00), iVar7 == 0)) &&
             (iVar7 = fclose(__stream_00), iVar7 == 0)) {
            __ptr[0x2a] = 1;
            goto LAB_001238c8;
          }
        }
        else {
          fprintf((FILE *)(PTR___sF_001cad30 + 0x130),"unknown text in model file: [%s]\n",
                  acStack_60);
LAB_001238a4:
          free(*(void **)(__ptr + 0x20));
          free(*(void **)(__ptr + 0x26));
          free(*(void **)(__ptr + 0x28));
          free(__ptr);
        }
        __ptr = (int *)0x0;
        goto LAB_001238c8;
      }
      uVar1 = (__ptr[0x1a] * (__ptr[0x1a] + -1)) / 2;
      pvVar9 = malloc(-(ulong)(uVar1 >> 0x1f) & 0xfffffff800000000 | (ulong)uVar1 << 3);
      *(void **)(__ptr + 0x24) = pvVar9;
      if (0 < (int)uVar1) {
        lVar15 = 0;
        while( true ) {
          fscanf(__stream,"%lf",(long)pvVar9 + lVar15);
          fprintf(__stream_00,"%lf ",*(undefined8 *)(*(long *)(__ptr + 0x24) + lVar15));
          if (lVar15 == (ulong)(uVar1 - 1) * 8) break;
          pvVar9 = *(void **)(__ptr + 0x24);
          lVar15 = lVar15 + 8;
        }
        fputc(10,__stream_00);
        goto LAB_00123634;
      }
    }
  }
LAB_001237b8:
  fputc(10,__stream_00);
  goto LAB_00123634;
}



/* ------------------------------------------------------------------
 * Function: MainOCR::~MainOCR
 * Address : 00128de0
 * Size    : 0x144 bytes
 * ------------------------------------------------------------------ */

/* MainOCR::~MainOCR() */

void __thiscall MainOCR::~MainOCR(MainOCR *this)

{
  int iVar1;
  char cVar2;
  bool bVar3;
  MainOCR *pMVar4;
  undefined *puVar5;
  int *piVar6;
  void *pvVar7;
  MainOCR *pMVar8;
  void *pvVar9;
  
  puVar5 = (undefined *)(*(long *)(this + 0xe30) + -0x18);
  if (puVar5 != PTR_DAT_001cae30) {
    piVar6 = (int *)(*(long *)(this + 0xe30) + -8);
    do {
      iVar1 = *piVar6;
      cVar2 = '\x01';
      bVar3 = (bool)ExclusiveMonitorPass(piVar6,0x10);
      if (bVar3) {
        *piVar6 = iVar1 + -1;
        cVar2 = ExclusiveMonitorsStatus();
      }
    } while (cVar2 != '\0');
    if (iVar1 < 1) {
      operator_delete(puVar5);
    }
  }
  pvVar7 = *(void **)(this + 0x7c0);
  pvVar9 = *(void **)(this + 0x7c8);
  if (pvVar7 != pvVar9) {
    do {
      while( true ) {
        puVar5 = (undefined *)(*(long *)((long)pvVar7 + 0x658) + -0x18);
        if (puVar5 != PTR_DAT_001cae30) break;
LAB_00128e24:
        pvVar7 = (void *)((long)pvVar7 + 0x660);
        if (pvVar9 == pvVar7) goto LAB_00128e78;
      }
      piVar6 = (int *)(*(long *)((long)pvVar7 + 0x658) + -8);
      do {
        iVar1 = *piVar6;
        cVar2 = '\x01';
        bVar3 = (bool)ExclusiveMonitorPass(piVar6,0x10);
        if (bVar3) {
          *piVar6 = iVar1 + -1;
          cVar2 = ExclusiveMonitorsStatus();
        }
      } while (cVar2 != '\0');
      if (0 < iVar1) goto LAB_00128e24;
      pvVar7 = (void *)((long)pvVar7 + 0x660);
      operator_delete(puVar5);
    } while (pvVar9 != pvVar7);
LAB_00128e78:
    pvVar9 = *(void **)(this + 0x7c0);
  }
  if (pvVar9 != (void *)0x0) {
    operator_delete(pvVar9);
  }
  if (*(void **)(this + 0x790) != (void *)0x0) {
    operator_delete(*(void **)(this + 0x790));
  }
  if (*(void **)(this + 0x778) != (void *)0x0) {
    operator_delete(*(void **)(this + 0x778));
  }
  pMVar4 = *(MainOCR **)(this + 0x768);
  while (pMVar4 != this + 0x768) {
    pMVar8 = *(MainOCR **)pMVar4;
    operator_delete(pMVar4);
    pMVar4 = pMVar8;
  }
  if (*(long *)(this + 0x110) != 0) {
    svm_destroy_model();
    *(undefined8 *)(this + 0x110) = 0;
  }
  if (*(long *)(this + 0x118) != 0) {
    svm_destroy_model();
  }
  DigitSegmentManagement::~DigitSegmentManagement((DigitSegmentManagement *)this);
  return;
}



/* ------------------------------------------------------------------
 * Function: MainOCR::beginProcess
 * Address : 00128f38
 * Size    : 0x17c bytes
 * ------------------------------------------------------------------ */

/* MainOCR::beginProcess(char const*, char const*) */

bool __thiscall MainOCR::beginProcess(MainOCR *this,char *param_1,char *param_2)

{
  int iVar1;
  char cVar2;
  bool bVar3;
  int iVar4;
  int *piVar5;
  undefined1 auStack_28 [8];
  undefined1 auStack_20 [8];
  undefined1 auStack_18 [8];
  long local_10;
  long local_8;
  
  if (param_2 == (char *)0x0) {
    FUN_0016bea4(&local_8,param_1,auStack_18);
    iVar4 = CDigitalOCR::LoadModel((CDigitalOCR *)(this + 0x110),&local_8);
    if ((undefined *)(local_8 + -0x18) != PTR_DAT_001cae30) {
      piVar5 = (int *)(local_8 + -8);
      do {
        iVar1 = *piVar5;
        cVar2 = '\x01';
        bVar3 = (bool)ExclusiveMonitorPass(piVar5,0x10);
        if (bVar3) {
          *piVar5 = iVar1 + -1;
          cVar2 = ExclusiveMonitorsStatus();
        }
      } while (cVar2 != '\0');
      if (iVar1 < 1) {
        operator_delete((undefined *)(local_8 + -0x18));
      }
    }
  }
  else {
    FUN_0016bea4(&local_10,param_1,auStack_28);
    FUN_0016bea4(&local_8,param_2,auStack_20);
    iVar4 = CDigitalOCR::LoadModel((CDigitalOCR *)(this + 0x110),&local_10,&local_8);
    if ((undefined *)(local_8 + -0x18) != PTR_DAT_001cae30) {
      piVar5 = (int *)(local_8 + -8);
      do {
        iVar1 = *piVar5;
        cVar2 = '\x01';
        bVar3 = (bool)ExclusiveMonitorPass(piVar5,0x10);
        if (bVar3) {
          *piVar5 = iVar1 + -1;
          cVar2 = ExclusiveMonitorsStatus();
        }
      } while (cVar2 != '\0');
      if (iVar1 < 1) {
        operator_delete((undefined *)(local_8 + -0x18));
      }
    }
    if ((undefined *)(local_10 + -0x18) != PTR_DAT_001cae30) {
      piVar5 = (int *)(local_10 + -8);
      do {
        iVar1 = *piVar5;
        cVar2 = '\x01';
        bVar3 = (bool)ExclusiveMonitorPass(piVar5,0x10);
        if (bVar3) {
          *piVar5 = iVar1 + -1;
          cVar2 = ExclusiveMonitorsStatus();
        }
      } while (cVar2 != '\0');
      if (iVar1 < 1) {
        operator_delete((undefined *)(local_10 + -0x18));
      }
    }
  }
  DigitSegmentManagement::setSVMOCRPtr((DigitSegmentManagement *)this,(CDigitalOCR *)(this + 0x110))
  ;
  if (iVar4 != 0) {
    __android_log_print(6,"libdigit_ocr","model load fail");
  }
  return iVar4 == 0;
}



/* ------------------------------------------------------------------
 * Function: MainOCR::setImage
 * Address : 00129134
 * Size    : 0x68 bytes
 * ------------------------------------------------------------------ */

/* MainOCR::setImage(unsigned char*, int, int, int) */

undefined8 __thiscall
MainOCR::setImage(MainOCR *this,uchar *param_1,int param_2,int param_3,int param_4)

{
  if ((param_4 - 1U < 4) && (param_1 != (uchar *)0x0)) {
    *(uchar **)(this + 0x7a8) = param_1;
    *(int *)(this + 0x7b0) = param_2;
    *(int *)(this + 0x7b4) = param_3;
    *(int *)(this + 0x7b8) = param_4;
    return 1;
  }
  __android_log_print(3,"libdigit_ocr","setImage is failed!");
  return 0;
}



/* ------------------------------------------------------------------
 * Function: MainOCR::getDigitBoundingBoxes
 * Address : 0012919c
 * Size    : 0x8 bytes
 * ------------------------------------------------------------------ */

/* MainOCR::getDigitBoundingBoxes() */

MainOCR * __thiscall MainOCR::getDigitBoundingBoxes(MainOCR *this)

{
  return this + 0x768;
}



/* ------------------------------------------------------------------
 * Function: MainOCR::endProcess
 * Address : 001291a4
 * Size    : 0x8 bytes
 * ------------------------------------------------------------------ */

/* MainOCR::endProcess() */

undefined8 MainOCR::endProcess(void)

{
  return 1;
}



/* ------------------------------------------------------------------
 * Function: MainOCR::getDigitResultNum
 * Address : 001291ac
 * Size    : 0x8 bytes
 * ------------------------------------------------------------------ */

/* MainOCR::getDigitResultNum() */

undefined4 __thiscall MainOCR::getDigitResultNum(MainOCR *this)

{
  return *(undefined4 *)(this + 0xaf8);
}



/* ------------------------------------------------------------------
 * Function: MainOCR::getRecognitionResult
 * Address : 001291b4
 * Size    : 0x8 bytes
 * ------------------------------------------------------------------ */

/* MainOCR::getRecognitionResult() */

MainOCR * __thiscall MainOCR::getRecognitionResult(MainOCR *this)

{
  return this + 0x7d8;
}



