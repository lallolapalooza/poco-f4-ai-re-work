/*
 * Ghidra headless decompilation
 * program   : libqvasml.so
 * executable: /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/qcom_voiceactivation/native/libs/libqvasml.so
 * language  : AARCH64:LE:64:v8A
 * imagebase : 00100000
 * regex     : ListenBigSoundModel::|ListenSoundModel::readFrom|ListenSoundModel::isStripped|getIndividualModelInfo|ModelVersion
 * max funcs : 30
 * note      : pseudocode from a (possibly stripped) binary is approximate;
 *             indirect calls and register-indirect JNI tables are lost.
 */

/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::convertErrorCode
 * Address : 001636c8
 * Size    : 0x24 bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::convertErrorCode(int) */

undefined4 __thiscall ListenBigSoundModel::convertErrorCode(ListenBigSoundModel *this,int param_1)

{
  if ((uint)param_1 < 0x14) {
    return *(undefined4 *)(&DAT_00118844 + (long)param_1 * 4);
  }
  return 1;
}



/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::isInvalidBigSoundModelVersion
 * Address : 001636ec
 * Size    : 0x24 bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::isInvalidBigSoundModelVersion(int) */

bool __thiscall
ListenBigSoundModel::isInvalidBigSoundModelVersion(ListenBigSoundModel *this,int param_1)

{
  return (param_1 != 0x300 && (param_1 & 0xfffffcffU) != 0x400) && param_1 != 0x800;
}



/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::ListenBigSoundModel
 * Address : 00163710
 * Size    : 0x44 bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::ListenBigSoundModel() */

void __thiscall ListenBigSoundModel::ListenBigSoundModel(ListenBigSoundModel *this)

{
  undefined *puVar1;
  undefined8 uVar2;
  
  puVar1 = PTR_vtable_00214570;
  *(undefined8 *)(this + 8) = 0;
  *(undefined8 *)(this + 0x10) = 0;
  *(undefined **)this = puVar1 + 0x10;
  uVar2 = sml_model_create(0x800);
  *(undefined8 *)(this + 8) = uVar2;
  return;
}



/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::dealloc
 * Address : 00163754
 * Size    : 0x4c bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::dealloc() */

void __thiscall ListenBigSoundModel::dealloc(ListenBigSoundModel *this)

{
  if (*(void **)(this + 0x10) != (void *)0x0) {
    operator_delete__(*(void **)(this + 0x10));
  }
  *(undefined8 *)(this + 0x10) = 0;
  if (*(long *)(this + 8) != 0) {
    sml_model_destroy();
    return;
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::ListenBigSoundModel
 * Address : 001637a0
 * Size    : 0x5c bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::ListenBigSoundModel(unsigned int) */

void __thiscall ListenBigSoundModel::ListenBigSoundModel(ListenBigSoundModel *this,uint param_1)

{
  int iVar1;
  uint uVar2;
  undefined *puVar3;
  undefined8 uVar4;
  
  puVar3 = PTR_vtable_00214570;
  uVar2 = (uint)(CONCAT44(param_1,param_1 - 0x300) >> 8);
  *(undefined8 *)(this + 0x10) = 0;
  iVar1 = uVar2 * 0x100 + 0x300;
  if (4 < uVar2) {
    iVar1 = 0x800;
  }
  *(undefined **)this = puVar3 + 0x10;
  *(undefined8 *)(this + 8) = 0;
  uVar4 = sml_model_create(iVar1);
  *(undefined8 *)(this + 8) = uVar4;
  return;
}



/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::~ListenBigSoundModel
 * Address : 001637fc
 * Size    : 0x5c bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::~ListenBigSoundModel() */

void __thiscall ListenBigSoundModel::~ListenBigSoundModel(ListenBigSoundModel *this)

{
  *(undefined **)this = PTR_vtable_00214570 + 0x10;
  if (*(void **)(this + 0x10) != (void *)0x0) {
    operator_delete__(*(void **)(this + 0x10));
  }
  *(undefined8 *)(this + 0x10) = 0;
  if (*(long *)(this + 8) != 0) {
    sml_model_destroy();
    return;
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::~ListenBigSoundModel
 * Address : 00163858
 * Size    : 0x2c bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::~ListenBigSoundModel() */

void __thiscall ListenBigSoundModel::~ListenBigSoundModel(ListenBigSoundModel *this)

{
  ~ListenBigSoundModel(this);
  operator_delete(this);
  return;
}



/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::getBinaryModelSize
 * Address : 00163884
 * Size    : 0xc bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::getBinaryModelSize() const */

void __thiscall ListenBigSoundModel::getBinaryModelSize(ListenBigSoundModel *this)

{
  sml_model_get_size(*(undefined8 *)(this + 8));
  return;
}



/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::readFrom
 * Address : 00163890
 * Size    : 0x220 bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::readFrom(unsigned char*, unsigned int) */

void __thiscall ListenBigSoundModel::readFrom(ListenBigSoundModel *this,uchar *param_1,uint param_2)

{
  uint uVar1;
  undefined4 uVar2;
  long lVar3;
  sml *psVar4;
  void *pvVar5;
  undefined8 uVar6;
  ulong uVar7;
  
  uVar2 = *(undefined4 *)(*(long *)(this + 8) + 8);
  if (*(void **)(this + 0x10) == (void *)0x0) {
    *(undefined8 *)(this + 0x10) = 0;
LAB_001638dc:
    sml_model_destroy();
  }
  else {
    operator_delete__(*(void **)(this + 0x10));
    *(undefined8 *)(this + 0x10) = 0;
    if (*(long *)(this + 8) != 0) goto LAB_001638dc;
  }
  lVar3 = sml_model_create(uVar2);
  *(long *)(this + 8) = lVar3;
  if (lVar3 == 0) {
    psVar4 = (sml *)0x2;
    uVar6 = 0x9c;
  }
  else {
    uVar1 = *(uint *)(lVar3 + 8);
    if ((((uVar1 & 0xfffffcff) == 0x400) || (uVar1 == 0x300)) || (uVar1 == 0x800)) {
      if (param_2 < 0x5f5e101) {
        uVar7 = (ulong)param_2;
        pvVar5 = operator_new__(uVar7);
        *(void **)(this + 0x10) = pvVar5;
        memscpy(pvVar5,uVar7,param_1,uVar7);
        uVar2 = sml_model_decoder(*(undefined8 *)(this + 0x10),*(undefined8 *)(this + 8));
        psVar4 = (sml *)0x1;
        switch(uVar2) {
        case 0:
          if (*(long *)(*(long *)(this + 8) + 0x1b8) == 0) {
            psVar4 = (sml *)0x2;
            uVar6 = 0xa8;
          }
          else {
            psVar4 = (sml *)0x0;
            uVar6 = 0xa9;
          }
          goto LAB_00163a80;
        case 2:
          psVar4 = (sml *)0x6;
          break;
        case 7:
        case 8:
          psVar4 = (sml *)0x2;
          break;
        case 9:
          psVar4 = (sml *)0x17;
          break;
        case 0xb:
          psVar4 = (sml *)0xd;
          break;
        case 0xc:
          psVar4 = (sml *)0x3;
          break;
        case 0xd:
          psVar4 = (sml *)0x4;
          break;
        case 0xf:
          psVar4 = (sml *)0x7;
          break;
        case 0x10:
          psVar4 = (sml *)0x5;
          break;
        case 0x11:
          psVar4 = (sml *)0x13;
          break;
        case 0x12:
        case 0x13:
          psVar4 = (sml *)0x8;
        }
        uVar6 = 0xa6;
      }
      else {
        psVar4 = (sml *)0x2;
        uVar6 = 0x9f;
      }
    }
    else {
      psVar4 = (sml *)0x6;
      uVar6 = 0x9e;
    }
  }
LAB_00163a80:
  sml::sml_debug_print
            (psVar4,
             "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/big_sound_model/ListenBigSoundModel.cpp"
             ,"readFrom",uVar6);
  return;
}



/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::readHeaderFrom
 * Address : 00163ab0
 * Size    : 0x1ac bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::readHeaderFrom(unsigned char const*) */

void __thiscall ListenBigSoundModel::readHeaderFrom(ListenBigSoundModel *this,uchar *param_1)

{
  uint uVar1;
  undefined4 uVar2;
  long lVar3;
  sml *psVar4;
  undefined8 uVar5;
  
  uVar2 = *(undefined4 *)(*(long *)(this + 8) + 8);
  if (*(void **)(this + 0x10) == (void *)0x0) {
    *(undefined8 *)(this + 0x10) = 0;
LAB_00163af8:
    sml_model_destroy();
  }
  else {
    operator_delete__(*(void **)(this + 0x10));
    *(undefined8 *)(this + 0x10) = 0;
    if (*(long *)(this + 8) != 0) goto LAB_00163af8;
  }
  lVar3 = sml_model_create(uVar2);
  *(long *)(this + 8) = lVar3;
  *(undefined8 *)(this + 0x10) = 0;
  if (lVar3 == 0) {
    psVar4 = (sml *)0x2;
    uVar5 = 0xb4;
  }
  else {
    uVar1 = *(uint *)(lVar3 + 8);
    if ((((uVar1 & 0xfffffcff) == 0x400) || (uVar1 == 0x300)) || (uVar1 == 0x800)) {
      uVar2 = sml_model_decoder(param_1,lVar3);
      psVar4 = (sml *)0x1;
      switch(uVar2) {
      case 0:
        psVar4 = (sml *)0x0;
        uVar5 = 0xbd;
        goto LAB_00163c48;
      case 2:
        psVar4 = (sml *)0x6;
        break;
      case 7:
      case 8:
        psVar4 = (sml *)0x2;
        break;
      case 9:
        psVar4 = (sml *)0x17;
        break;
      case 0xb:
        psVar4 = (sml *)0xd;
        break;
      case 0xc:
        psVar4 = (sml *)0x3;
        break;
      case 0xd:
        psVar4 = (sml *)0x4;
        break;
      case 0xf:
        psVar4 = (sml *)0x7;
        break;
      case 0x10:
        psVar4 = (sml *)0x5;
        break;
      case 0x11:
        psVar4 = (sml *)0x13;
        break;
      case 0x12:
      case 0x13:
        psVar4 = (sml *)0x8;
      }
      uVar5 = 0xbb;
    }
    else {
      psVar4 = (sml *)0x6;
      uVar5 = 0xb6;
    }
  }
LAB_00163c48:
  sml::sml_debug_print
            (psVar4,
             "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/big_sound_model/ListenBigSoundModel.cpp"
             ,"readHeaderFrom",uVar5);
  return;
}



/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::mergeFrom
 * Address : 00163c5c
 * Size    : 0x344 bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::mergeFrom(_IndividualSoundModelInfo*, char*, char*) */

undefined8 __thiscall
ListenBigSoundModel::mergeFrom
          (ListenBigSoundModel *this,_IndividualSoundModelInfo *param_1,char *param_2,char *param_3)

{
  uint uVar1;
  undefined4 uVar2;
  int iVar3;
  sml *psVar4;
  ListenBigSoundModel *pLVar5;
  long lVar6;
  undefined8 uVar7;
  ulong uVar8;
  int *piVar9;
  
  lVar6 = *(long *)(this + 8);
  if (lVar6 == 0) {
    lVar6 = sml_model_create(0x800);
    *(long *)(this + 8) = lVar6;
    if (lVar6 == 0) {
      psVar4 = (sml *)0x2;
      uVar7 = 0xc9;
      goto LAB_00163ec4;
    }
  }
  if (param_1 == (_IndividualSoundModelInfo *)0x0) {
    psVar4 = (sml *)0x2;
    uVar7 = 0xca;
  }
  else if (*(long *)param_1 == 0) {
    psVar4 = (sml *)0x2;
    uVar7 = 0xcb;
  }
  else if (*(int *)(param_1 + 8) == 0) {
    psVar4 = (sml *)0x2;
    uVar7 = 0xcc;
  }
  else {
    uVar8 = (ulong)*(uint *)(lVar6 + 0x10);
    if (*(uint *)(lVar6 + 0x10) != 0) {
      piVar9 = (int *)(*(long *)(lVar6 + 0x1b0) + 0xc);
      do {
        if (*piVar9 == *(int *)(param_1 + 0xc)) {
          psVar4 = (sml *)0x19;
          uVar7 = 0xd0;
          goto LAB_00163ec4;
        }
        piVar9 = piVar9 + 4;
        uVar8 = uVar8 - 1;
      } while (uVar8 != 0);
    }
    uVar2 = sml_model_merger();
    psVar4 = (sml *)0x1;
    switch(uVar2) {
    case 0:
      *(undefined2 *)
       (*(long *)(*(long *)(this + 8) + 0x1b0) +
       (ulong)(*(int *)(*(long *)(this + 8) + 0x10) - 1) * 0x10) = *(undefined2 *)(param_1 + 0x10);
      *(undefined2 *)
       (*(long *)(*(long *)(this + 8) + 0x1b0) +
        (ulong)(*(int *)(*(long *)(this + 8) + 0x10) - 1) * 0x10 + 2) =
           *(undefined2 *)(param_1 + 0x12);
      lVar6 = *(long *)(this + 8);
      uVar1 = *(uint *)(lVar6 + 8);
      if ((((uVar1 & 0xfffffcff) == 0x400) || (uVar1 == 0x300)) || (uVar1 == 0x800)) {
        if (param_2 != (char *)0x0) {
          if (*(int *)(lVar6 + 0x14) == 0) {
            pLVar5 = (ListenBigSoundModel *)sml_model_set_keyword_spell(lVar6,param_2);
            if ((int)pLVar5 != 0) {
              psVar4 = (sml *)convertErrorCode(pLVar5,(int)pLVar5);
              uVar7 = 0xde;
              goto LAB_00163ec4;
            }
          }
          else {
            iVar3 = strcmp((char *)(lVar6 + 0x1c),param_2);
            if (iVar3 != 0) {
              psVar4 = (sml *)0x3;
              uVar7 = 0xe2;
              goto LAB_00163ec4;
            }
          }
        }
        if (param_3 == (char *)0x0) {
          return 0;
        }
        lVar6 = *(long *)(this + 8);
        if (*(int *)(lVar6 + 0x18) == 0) {
          pLVar5 = (ListenBigSoundModel *)sml_model_register_user(lVar6,param_3);
          if ((int)pLVar5 == 0) {
            return 0;
          }
          psVar4 = (sml *)convertErrorCode(pLVar5,(int)pLVar5);
          uVar7 = 0xe9;
        }
        else {
          iVar3 = strcmp((char *)(lVar6 + 0xe4),param_3);
          if (iVar3 == 0) {
            return 0;
          }
          psVar4 = (sml *)0x4;
          uVar7 = 0xed;
        }
      }
      else {
        psVar4 = (sml *)0x1;
        uVar7 = 0xd9;
      }
      goto LAB_00163ec4;
    case 2:
      psVar4 = (sml *)0x6;
      break;
    case 7:
    case 8:
      psVar4 = (sml *)0x2;
      break;
    case 9:
      psVar4 = (sml *)0x17;
      break;
    case 0xb:
      psVar4 = (sml *)0xd;
      break;
    case 0xc:
      psVar4 = (sml *)0x3;
      break;
    case 0xd:
      psVar4 = (sml *)0x4;
      break;
    case 0xf:
      psVar4 = (sml *)0x7;
      break;
    case 0x10:
      psVar4 = (sml *)0x5;
      break;
    case 0x11:
      psVar4 = (sml *)0x13;
      break;
    case 0x12:
    case 0x13:
      psVar4 = (sml *)0x8;
    }
    uVar7 = 0xd4;
  }
LAB_00163ec4:
  uVar7 = sml::sml_debug_print
                    (psVar4,
                     "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/big_sound_model/ListenBigSoundModel.cpp"
                     ,"mergeFrom",uVar7);
  return uVar7;
}



/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::writeTo
 * Address : 00163fa0
 * Size    : 0x50 bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::writeTo(unsigned char*) */

void ListenBigSoundModel::writeTo(uchar *param_1)

{
  uint uVar1;
  sml *psVar2;
  
  uVar1 = sml_model_serializer(*(undefined8 *)(param_1 + 8));
  if (uVar1 < 0x14) {
                    /* WARNING: Load size is inaccurate */
    psVar2._0_4_ = *(sml **)(&DAT_00118844 + (long)(int)uVar1 * 4);
  }
  else {
    psVar2._0_4_ = (sml *)0x1;
  }
  sml::sml_debug_print
            (psVar2._0_4_,
             "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/big_sound_model/ListenBigSoundModel.cpp"
             ,"writeTo",0xf7);
  return;
}



/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::getIndicator
 * Address : 00163ff0
 * Size    : 0x9c bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::getIndicator() */

int __thiscall ListenBigSoundModel::getIndicator(ListenBigSoundModel *this)

{
  ulong uVar1;
  uint uVar2;
  ulong uVar3;
  long lVar4;
  long lVar5;
  int *piVar6;
  long lVar7;
  int iVar8;
  int iVar9;
  int iVar10;
  int iVar11;
  int iVar12;
  int iVar13;
  int iVar14;
  int iVar15;
  
  uVar2 = *(uint *)(*(long *)(this + 8) + 0x10);
  uVar3 = (ulong)uVar2;
  if (uVar2 != 0) {
    lVar5 = *(long *)(*(long *)(this + 8) + 0x1b0);
    if (uVar2 < 9) {
      lVar7 = 0;
      iVar8 = 0;
    }
    else {
      uVar1 = 8;
      if ((uVar2 & 7) != 0) {
        uVar1 = uVar3 & 7;
      }
      lVar7 = uVar3 - uVar1;
      iVar8 = 0;
      iVar9 = 0;
      iVar10 = 0;
      iVar11 = 0;
      iVar12 = 0;
      iVar13 = 0;
      iVar14 = 0;
      iVar15 = 0;
      piVar6 = (int *)(lVar5 + 0x4c);
      lVar4 = lVar7;
      do {
        lVar4 = lVar4 + -8;
        iVar12 = *piVar6 + iVar12;
        iVar13 = piVar6[4] + iVar13;
        iVar14 = piVar6[8] + iVar14;
        iVar15 = piVar6[0xc] + iVar15;
        iVar8 = piVar6[-0x10] + iVar8;
        iVar9 = piVar6[-0xc] + iVar9;
        iVar10 = piVar6[-8] + iVar10;
        iVar11 = piVar6[-4] + iVar11;
        piVar6 = piVar6 + 0x20;
      } while (lVar4 != 0);
      iVar8 = iVar12 + iVar8 + iVar13 + iVar9 + iVar14 + iVar10 + iVar15 + iVar11;
    }
    lVar4 = uVar3 - lVar7;
    piVar6 = (int *)(lVar5 + lVar7 * 0x10 + 0xc);
    do {
      lVar4 = lVar4 + -1;
      iVar8 = *piVar6 + iVar8;
      piVar6 = piVar6 + 4;
    } while (lVar4 != 0);
    return iVar8;
  }
  return 0;
}



/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::parseTo
 * Address : 0016408c
 * Size    : 0xd4 bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::parseTo(unsigned short, unsigned int, unsigned char*) */

undefined8 __thiscall
ListenBigSoundModel::parseTo(ListenBigSoundModel *this,ushort param_1,uint param_2,uchar *param_3)

{
  sml *psVar1;
  undefined8 uVar2;
  long lVar3;
  ulong uVar4;
  uint *puVar5;
  
  if (param_3 == (uchar *)0x0) {
    psVar1 = (sml *)0x2;
    uVar2 = 0x107;
  }
  else {
    lVar3 = *(long *)(this + 8);
    uVar4 = (ulong)*(uint *)(lVar3 + 0x10);
    if (*(uint *)(lVar3 + 0x10) != 0) {
      puVar5 = (uint *)(*(long *)(lVar3 + 0x1b0) + 8);
      do {
        if (puVar5[1] == (uint)param_1) {
          if (*puVar5 == param_2) {
            memscpy(param_3,param_2,*(long *)(lVar3 + 0x1b8) + (ulong)puVar5[-1],param_2);
            return 0;
          }
          psVar1 = (sml *)0x2;
          uVar2 = 0x10d;
          goto LAB_00164100;
        }
        puVar5 = puVar5 + 4;
        uVar4 = uVar4 - 1;
      } while (uVar4 != 0);
    }
    psVar1 = (sml *)0x1;
    uVar2 = 0x114;
  }
LAB_00164100:
  uVar2 = sml::sml_debug_print
                    (psVar1,
                     "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/big_sound_model/ListenBigSoundModel.cpp"
                     ,"parseTo",uVar2);
  return uVar2;
}



/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::getIndividualModelSize
 * Address : 00164160
 * Size    : 0x60 bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::getIndividualModelSize(unsigned short, unsigned int*) */

undefined8 __thiscall
ListenBigSoundModel::getIndividualModelSize(ListenBigSoundModel *this,ushort param_1,uint *param_2)

{
  uint uVar1;
  undefined8 uVar2;
  ulong uVar3;
  uint *puVar4;
  
  uVar1 = *(uint *)(*(long *)(this + 8) + 0x10);
  uVar3 = (ulong)uVar1;
  if (uVar1 != 0) {
    puVar4 = (uint *)(*(long *)(*(long *)(this + 8) + 0x1b0) + 0xc);
    do {
      if (*puVar4 == (uint)param_1) {
        *param_2 = puVar4[-1];
        return 0;
      }
      puVar4 = puVar4 + 4;
      uVar3 = uVar3 - 1;
    } while (uVar3 != 0);
  }
  uVar2 = sml::sml_debug_print
                    ((sml *)0x2,
                     "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/big_sound_model/ListenBigSoundModel.cpp"
                     ,"getIndividualModelSize",0x120);
  return uVar2;
}



/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::getIndividualModelInfo
 * Address : 001641c0
 * Size    : 0xbc bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::getIndividualModelInfo(unsigned short, _IndividualSoundModelInfo*) */

undefined8 __thiscall
ListenBigSoundModel::getIndividualModelInfo
          (ListenBigSoundModel *this,ushort param_1,_IndividualSoundModelInfo *param_2)

{
  uint uVar1;
  undefined8 uVar2;
  long lVar3;
  long lVar4;
  
  uVar1 = *(uint *)(*(long *)(this + 8) + 0x10);
  if (uVar1 != 0) {
    lVar3 = 0;
    lVar4 = *(long *)(*(long *)(this + 8) + 0x1b0);
    do {
      if (*(uint *)(lVar4 + lVar3 + 0xc) == (uint)param_1) {
        *(undefined2 *)(param_2 + 0x10) = *(undefined2 *)(lVar4 + lVar3);
        *(undefined2 *)(param_2 + 0x12) =
             *(undefined2 *)(*(long *)(*(long *)(this + 8) + 0x1b0) + lVar3 + 2);
        *(undefined4 *)(param_2 + 0xc) =
             *(undefined4 *)(*(long *)(*(long *)(this + 8) + 0x1b0) + lVar3 + 0xc);
        *(undefined4 *)(param_2 + 8) =
             *(undefined4 *)(*(long *)(*(long *)(this + 8) + 0x1b0) + lVar3 + 8);
        *(ulong *)param_2 =
             *(long *)(*(long *)(this + 8) + 0x1b8) +
             (ulong)*(uint *)(*(long *)(*(long *)(this + 8) + 0x1b0) + lVar3 + 4);
        return 0;
      }
      lVar3 = lVar3 + 0x10;
    } while ((ulong)uVar1 * 0x10 - lVar3 != 0);
  }
  uVar2 = sml::sml_debug_print
                    ((sml *)0x2,
                     "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/big_sound_model/ListenBigSoundModel.cpp"
                     ,"getIndividualModelInfo",0x12f);
  return uVar2;
}



/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::deleteModel
 * Address : 0016427c
 * Size    : 0x7c bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::deleteModel(unsigned short) */

void __thiscall ListenBigSoundModel::deleteModel(ListenBigSoundModel *this,ushort param_1)

{
  uint uVar1;
  sml *psVar2;
  undefined8 uVar3;
  ulong uVar4;
  uint *puVar5;
  
  uVar1 = *(uint *)(*(long *)(this + 8) + 0x10);
  uVar4 = (ulong)uVar1;
  if (uVar1 != 0) {
    puVar5 = (uint *)(*(long *)(*(long *)(this + 8) + 0x1b0) + 0xc);
    do {
      if (*puVar5 == (uint)param_1) {
        psVar2 = (sml *)sml_model_deleter();
        uVar3 = 0x139;
        goto LAB_001642ec;
      }
      puVar5 = puVar5 + 4;
      uVar4 = uVar4 - 1;
    } while (uVar4 != 0);
  }
  psVar2 = (sml *)0x2;
  uVar3 = 0x13c;
LAB_001642ec:
  sml::sml_debug_print
            (psVar2,
             "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/big_sound_model/ListenBigSoundModel.cpp"
             ,"deleteModel",uVar3);
  return;
}



/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::getNumKeywords
 * Address : 001642f8
 * Size    : 0x54 bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::getNumKeywords() */

ulong __thiscall ListenBigSoundModel::getNumKeywords(ListenBigSoundModel *this)

{
  uint uVar1;
  ulong uVar2;
  
  uVar1 = *(uint *)(*(long *)(this + 8) + 8);
  if ((((uVar1 & 0xfffffcff) != 0x400) && (uVar1 != 0x300)) && (uVar1 != 0x800)) {
    uVar2 = sml::sml_debug_print
                      ((sml *)0x1,
                       "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/big_sound_model/ListenBigSoundModel.cpp"
                       ,"getNumKeywords",0x142);
    return uVar2;
  }
  return (ulong)(*(int *)(*(long *)(this + 8) + 0x14) != 0);
}



/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::getKeywordSpell
 * Address : 0016434c
 * Size    : 0xc0 bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::getKeywordSpell(char*) */

undefined8 __thiscall ListenBigSoundModel::getKeywordSpell(ListenBigSoundModel *this,char *param_1)

{
  uint uVar1;
  undefined8 uVar2;
  sml *psVar3;
  long lVar4;
  
  lVar4 = *(long *)(this + 8);
  uVar1 = *(uint *)(lVar4 + 8);
  if ((((uVar1 & 0xfffffcff) == 0x400) || (uVar1 == 0x300)) || (uVar1 == 0x800)) {
    if (param_1 != (char *)0x0) {
      if (*(uint *)(lVar4 + 0x14) < 200) {
        memscpy(param_1,200,lVar4 + 0x1c,200);
        uVar2 = 0;
        param_1[199] = '\0';
      }
      else {
        uVar2 = 0x17;
      }
      return uVar2;
    }
    psVar3 = (sml *)0x2;
    uVar2 = 0x14e;
  }
  else {
    psVar3 = (sml *)0x1;
    uVar2 = 0x14c;
  }
  uVar2 = sml::sml_debug_print
                    (psVar3,
                     "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/big_sound_model/ListenBigSoundModel.cpp"
                     ,"getKeywordSpell",uVar2);
  return uVar2;
}



/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::getNumUsers
 * Address : 0016440c
 * Size    : 0x54 bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::getNumUsers() */

ulong __thiscall ListenBigSoundModel::getNumUsers(ListenBigSoundModel *this)

{
  uint uVar1;
  ulong uVar2;
  
  uVar1 = *(uint *)(*(long *)(this + 8) + 8);
  if ((((uVar1 & 0xfffffcff) != 0x400) && (uVar1 != 0x300)) && (uVar1 != 0x800)) {
    uVar2 = sml::sml_debug_print
                      ((sml *)0x1,
                       "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/big_sound_model/ListenBigSoundModel.cpp"
                       ,"getNumUsers",0x15f);
    return uVar2;
  }
  return (ulong)(*(int *)(*(long *)(this + 8) + 0x18) != 0);
}



/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::getUserSpell
 * Address : 00164460
 * Size    : 0xd4 bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::getUserSpell(char*) */

undefined8 __thiscall ListenBigSoundModel::getUserSpell(ListenBigSoundModel *this,char *param_1)

{
  uint uVar1;
  sml *psVar2;
  undefined8 uVar3;
  long lVar4;
  
  lVar4 = *(long *)(this + 8);
  uVar1 = *(uint *)(lVar4 + 8);
  if ((((uVar1 & 0xfffffcff) == 0x400) || (uVar1 == 0x300)) || (uVar1 == 0x800)) {
    if (param_1 == (char *)0x0) {
      psVar2 = (sml *)0x2;
      uVar3 = 0x16b;
    }
    else {
      if (*(uint *)(lVar4 + 0x18) < 200) {
        memscpy(param_1,200,lVar4 + 0xe4,200);
        param_1[199] = '\0';
        return 0;
      }
      psVar2 = (sml *)0x17;
      uVar3 = 0x170;
    }
  }
  else {
    psVar2 = (sml *)0x1;
    uVar3 = 0x169;
  }
  uVar3 = sml::sml_debug_print
                    (psVar2,
                     "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/big_sound_model/ListenBigSoundModel.cpp"
                     ,"getUserSpell",uVar3);
  return uVar3;
}



/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::findKeywordIdBySpell
 * Address : 00164534
 * Size    : 0x140 bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::findKeywordIdBySpell(char const*) */

void __thiscall ListenBigSoundModel::findKeywordIdBySpell(ListenBigSoundModel *this,char *param_1)

{
  uint uVar1;
  long lVar2;
  int iVar3;
  long lVar4;
  char local_f0 [192];
  ulong local_30;
  long local_28;
  
  lVar2 = tpidr_el0;
  local_28 = *(long *)(lVar2 + 0x28);
  if (param_1 == (char *)0x0) {
    if (*(long *)(lVar2 + 0x28) == local_28) {
      sml::sml_debug_print
                ((sml *)0x2,
                 "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/big_sound_model/ListenBigSoundModel.cpp"
                 ,"findKeywordIdBySpell",0x17d);
      return;
    }
  }
  else {
    lVar4 = *(long *)(this + 8);
    local_30 = 0;
    local_f0[8] = '\0';
    local_f0[9] = '\0';
    local_f0[10] = '\0';
    local_f0[0xb] = '\0';
    local_f0[0xc] = '\0';
    local_f0[0xd] = '\0';
    local_f0[0xe] = '\0';
    local_f0[0xf] = '\0';
    local_f0[0] = '\0';
    local_f0[1] = '\0';
    local_f0[2] = '\0';
    local_f0[3] = '\0';
    local_f0[4] = '\0';
    local_f0[5] = '\0';
    local_f0[6] = '\0';
    local_f0[7] = '\0';
    local_f0[0x18] = '\0';
    local_f0[0x19] = '\0';
    local_f0[0x1a] = '\0';
    local_f0[0x1b] = '\0';
    local_f0[0x1c] = '\0';
    local_f0[0x1d] = '\0';
    local_f0[0x1e] = '\0';
    local_f0[0x1f] = '\0';
    local_f0[0x10] = '\0';
    local_f0[0x11] = '\0';
    local_f0[0x12] = '\0';
    local_f0[0x13] = '\0';
    local_f0[0x14] = '\0';
    local_f0[0x15] = '\0';
    local_f0[0x16] = '\0';
    local_f0[0x17] = '\0';
    local_f0[0x28] = '\0';
    local_f0[0x29] = '\0';
    local_f0[0x2a] = '\0';
    local_f0[0x2b] = '\0';
    local_f0[0x2c] = '\0';
    local_f0[0x2d] = '\0';
    local_f0[0x2e] = '\0';
    local_f0[0x2f] = '\0';
    local_f0[0x20] = '\0';
    local_f0[0x21] = '\0';
    local_f0[0x22] = '\0';
    local_f0[0x23] = '\0';
    local_f0[0x24] = '\0';
    local_f0[0x25] = '\0';
    local_f0[0x26] = '\0';
    local_f0[0x27] = '\0';
    local_f0[0x38] = '\0';
    local_f0[0x39] = '\0';
    local_f0[0x3a] = '\0';
    local_f0[0x3b] = '\0';
    local_f0[0x3c] = '\0';
    local_f0[0x3d] = '\0';
    local_f0[0x3e] = '\0';
    local_f0[0x3f] = '\0';
    local_f0[0x30] = '\0';
    local_f0[0x31] = '\0';
    local_f0[0x32] = '\0';
    local_f0[0x33] = '\0';
    local_f0[0x34] = '\0';
    local_f0[0x35] = '\0';
    local_f0[0x36] = '\0';
    local_f0[0x37] = '\0';
    local_f0[0x48] = '\0';
    local_f0[0x49] = '\0';
    local_f0[0x4a] = '\0';
    local_f0[0x4b] = '\0';
    local_f0[0x4c] = '\0';
    local_f0[0x4d] = '\0';
    local_f0[0x4e] = '\0';
    local_f0[0x4f] = '\0';
    local_f0[0x40] = '\0';
    local_f0[0x41] = '\0';
    local_f0[0x42] = '\0';
    local_f0[0x43] = '\0';
    local_f0[0x44] = '\0';
    local_f0[0x45] = '\0';
    local_f0[0x46] = '\0';
    local_f0[0x47] = '\0';
    local_f0[0x58] = '\0';
    local_f0[0x59] = '\0';
    local_f0[0x5a] = '\0';
    local_f0[0x5b] = '\0';
    local_f0[0x5c] = '\0';
    local_f0[0x5d] = '\0';
    local_f0[0x5e] = '\0';
    local_f0[0x5f] = '\0';
    local_f0[0x50] = '\0';
    local_f0[0x51] = '\0';
    local_f0[0x52] = '\0';
    local_f0[0x53] = '\0';
    local_f0[0x54] = '\0';
    local_f0[0x55] = '\0';
    local_f0[0x56] = '\0';
    local_f0[0x57] = '\0';
    local_f0[0x68] = '\0';
    local_f0[0x69] = '\0';
    local_f0[0x6a] = '\0';
    local_f0[0x6b] = '\0';
    local_f0[0x6c] = '\0';
    local_f0[0x6d] = '\0';
    local_f0[0x6e] = '\0';
    local_f0[0x6f] = '\0';
    local_f0[0x60] = '\0';
    local_f0[0x61] = '\0';
    local_f0[0x62] = '\0';
    local_f0[99] = '\0';
    local_f0[100] = '\0';
    local_f0[0x65] = '\0';
    local_f0[0x66] = '\0';
    local_f0[0x67] = '\0';
    local_f0[0x78] = '\0';
    local_f0[0x79] = '\0';
    local_f0[0x7a] = '\0';
    local_f0[0x7b] = '\0';
    local_f0[0x7c] = '\0';
    local_f0[0x7d] = '\0';
    local_f0[0x7e] = '\0';
    local_f0[0x7f] = '\0';
    local_f0[0x70] = '\0';
    local_f0[0x71] = '\0';
    local_f0[0x72] = '\0';
    local_f0[0x73] = '\0';
    local_f0[0x74] = '\0';
    local_f0[0x75] = '\0';
    local_f0[0x76] = '\0';
    local_f0[0x77] = '\0';
    local_f0[0x88] = '\0';
    local_f0[0x89] = '\0';
    local_f0[0x8a] = '\0';
    local_f0[0x8b] = '\0';
    local_f0[0x8c] = '\0';
    local_f0[0x8d] = '\0';
    local_f0[0x8e] = '\0';
    local_f0[0x8f] = '\0';
    local_f0[0x80] = '\0';
    local_f0[0x81] = '\0';
    local_f0[0x82] = '\0';
    local_f0[0x83] = '\0';
    local_f0[0x84] = '\0';
    local_f0[0x85] = '\0';
    local_f0[0x86] = '\0';
    local_f0[0x87] = '\0';
    local_f0[0x98] = '\0';
    local_f0[0x99] = '\0';
    local_f0[0x9a] = '\0';
    local_f0[0x9b] = '\0';
    local_f0[0x9c] = '\0';
    local_f0[0x9d] = '\0';
    local_f0[0x9e] = '\0';
    local_f0[0x9f] = '\0';
    local_f0[0x90] = '\0';
    local_f0[0x91] = '\0';
    local_f0[0x92] = '\0';
    local_f0[0x93] = '\0';
    local_f0[0x94] = '\0';
    local_f0[0x95] = '\0';
    local_f0[0x96] = '\0';
    local_f0[0x97] = '\0';
    local_f0[0xa8] = '\0';
    local_f0[0xa9] = '\0';
    local_f0[0xaa] = '\0';
    local_f0[0xab] = '\0';
    local_f0[0xac] = '\0';
    local_f0[0xad] = '\0';
    local_f0[0xae] = '\0';
    local_f0[0xaf] = '\0';
    local_f0[0xa0] = '\0';
    local_f0[0xa1] = '\0';
    local_f0[0xa2] = '\0';
    local_f0[0xa3] = '\0';
    local_f0[0xa4] = '\0';
    local_f0[0xa5] = '\0';
    local_f0[0xa6] = '\0';
    local_f0[0xa7] = '\0';
    local_f0[0xb8] = '\0';
    local_f0[0xb9] = '\0';
    local_f0[0xba] = '\0';
    local_f0[0xbb] = '\0';
    local_f0[0xbc] = '\0';
    local_f0[0xbd] = '\0';
    local_f0[0xbe] = '\0';
    local_f0[0xbf] = '\0';
    local_f0[0xb0] = '\0';
    local_f0[0xb1] = '\0';
    local_f0[0xb2] = '\0';
    local_f0[0xb3] = '\0';
    local_f0[0xb4] = '\0';
    local_f0[0xb5] = '\0';
    local_f0[0xb6] = '\0';
    local_f0[0xb7] = '\0';
    uVar1 = *(uint *)(lVar4 + 8);
    if ((((uVar1 & 0xfffffcff) == 0x400) || (uVar1 == 0x300)) || (uVar1 == 0x800)) {
      if (*(uint *)(lVar4 + 0x14) < 200) {
        memscpy(local_f0,200,lVar4 + 0x1c,200);
        local_30 = local_30 & 0xffffffffffffff;
      }
    }
    else {
      sml::sml_debug_print
                ((sml *)0x1,
                 "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/big_sound_model/ListenBigSoundModel.cpp"
                 ,"getKeywordSpell",0x14c);
    }
    iVar3 = strcmp(param_1,local_f0);
    if (iVar3 != 0) {
      sml::sml_debug_print
                ((sml *)0x3,
                 "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/big_sound_model/ListenBigSoundModel.cpp"
                 ,"findKeywordIdBySpell",0x188);
    }
    if (*(long *)(lVar2 + 0x28) == local_28) {
      return;
    }
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::findUserIdByName
 * Address : 00164674
 * Size    : 0x15c bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::findUserIdByName(char const*) */

void __thiscall ListenBigSoundModel::findUserIdByName(ListenBigSoundModel *this,char *param_1)

{
  uint uVar1;
  long lVar2;
  int iVar3;
  sml *psVar4;
  undefined8 uVar5;
  long lVar6;
  char local_f0 [192];
  ulong local_30;
  long local_28;
  
  lVar2 = tpidr_el0;
  local_28 = *(long *)(lVar2 + 0x28);
  if (param_1 == (char *)0x0) {
    if (*(long *)(lVar2 + 0x28) == local_28) {
      sml::sml_debug_print
                ((sml *)0x2,
                 "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/big_sound_model/ListenBigSoundModel.cpp"
                 ,"findUserIdByName",399);
      return;
    }
    goto LAB_001647cc;
  }
  lVar6 = *(long *)(this + 8);
  local_30 = 0;
  local_f0[8] = '\0';
  local_f0[9] = '\0';
  local_f0[10] = '\0';
  local_f0[0xb] = '\0';
  local_f0[0xc] = '\0';
  local_f0[0xd] = '\0';
  local_f0[0xe] = '\0';
  local_f0[0xf] = '\0';
  local_f0[0] = '\0';
  local_f0[1] = '\0';
  local_f0[2] = '\0';
  local_f0[3] = '\0';
  local_f0[4] = '\0';
  local_f0[5] = '\0';
  local_f0[6] = '\0';
  local_f0[7] = '\0';
  local_f0[0x18] = '\0';
  local_f0[0x19] = '\0';
  local_f0[0x1a] = '\0';
  local_f0[0x1b] = '\0';
  local_f0[0x1c] = '\0';
  local_f0[0x1d] = '\0';
  local_f0[0x1e] = '\0';
  local_f0[0x1f] = '\0';
  local_f0[0x10] = '\0';
  local_f0[0x11] = '\0';
  local_f0[0x12] = '\0';
  local_f0[0x13] = '\0';
  local_f0[0x14] = '\0';
  local_f0[0x15] = '\0';
  local_f0[0x16] = '\0';
  local_f0[0x17] = '\0';
  local_f0[0x28] = '\0';
  local_f0[0x29] = '\0';
  local_f0[0x2a] = '\0';
  local_f0[0x2b] = '\0';
  local_f0[0x2c] = '\0';
  local_f0[0x2d] = '\0';
  local_f0[0x2e] = '\0';
  local_f0[0x2f] = '\0';
  local_f0[0x20] = '\0';
  local_f0[0x21] = '\0';
  local_f0[0x22] = '\0';
  local_f0[0x23] = '\0';
  local_f0[0x24] = '\0';
  local_f0[0x25] = '\0';
  local_f0[0x26] = '\0';
  local_f0[0x27] = '\0';
  local_f0[0x38] = '\0';
  local_f0[0x39] = '\0';
  local_f0[0x3a] = '\0';
  local_f0[0x3b] = '\0';
  local_f0[0x3c] = '\0';
  local_f0[0x3d] = '\0';
  local_f0[0x3e] = '\0';
  local_f0[0x3f] = '\0';
  local_f0[0x30] = '\0';
  local_f0[0x31] = '\0';
  local_f0[0x32] = '\0';
  local_f0[0x33] = '\0';
  local_f0[0x34] = '\0';
  local_f0[0x35] = '\0';
  local_f0[0x36] = '\0';
  local_f0[0x37] = '\0';
  local_f0[0x48] = '\0';
  local_f0[0x49] = '\0';
  local_f0[0x4a] = '\0';
  local_f0[0x4b] = '\0';
  local_f0[0x4c] = '\0';
  local_f0[0x4d] = '\0';
  local_f0[0x4e] = '\0';
  local_f0[0x4f] = '\0';
  local_f0[0x40] = '\0';
  local_f0[0x41] = '\0';
  local_f0[0x42] = '\0';
  local_f0[0x43] = '\0';
  local_f0[0x44] = '\0';
  local_f0[0x45] = '\0';
  local_f0[0x46] = '\0';
  local_f0[0x47] = '\0';
  local_f0[0x58] = '\0';
  local_f0[0x59] = '\0';
  local_f0[0x5a] = '\0';
  local_f0[0x5b] = '\0';
  local_f0[0x5c] = '\0';
  local_f0[0x5d] = '\0';
  local_f0[0x5e] = '\0';
  local_f0[0x5f] = '\0';
  local_f0[0x50] = '\0';
  local_f0[0x51] = '\0';
  local_f0[0x52] = '\0';
  local_f0[0x53] = '\0';
  local_f0[0x54] = '\0';
  local_f0[0x55] = '\0';
  local_f0[0x56] = '\0';
  local_f0[0x57] = '\0';
  local_f0[0x68] = '\0';
  local_f0[0x69] = '\0';
  local_f0[0x6a] = '\0';
  local_f0[0x6b] = '\0';
  local_f0[0x6c] = '\0';
  local_f0[0x6d] = '\0';
  local_f0[0x6e] = '\0';
  local_f0[0x6f] = '\0';
  local_f0[0x60] = '\0';
  local_f0[0x61] = '\0';
  local_f0[0x62] = '\0';
  local_f0[99] = '\0';
  local_f0[100] = '\0';
  local_f0[0x65] = '\0';
  local_f0[0x66] = '\0';
  local_f0[0x67] = '\0';
  local_f0[0x78] = '\0';
  local_f0[0x79] = '\0';
  local_f0[0x7a] = '\0';
  local_f0[0x7b] = '\0';
  local_f0[0x7c] = '\0';
  local_f0[0x7d] = '\0';
  local_f0[0x7e] = '\0';
  local_f0[0x7f] = '\0';
  local_f0[0x70] = '\0';
  local_f0[0x71] = '\0';
  local_f0[0x72] = '\0';
  local_f0[0x73] = '\0';
  local_f0[0x74] = '\0';
  local_f0[0x75] = '\0';
  local_f0[0x76] = '\0';
  local_f0[0x77] = '\0';
  local_f0[0x88] = '\0';
  local_f0[0x89] = '\0';
  local_f0[0x8a] = '\0';
  local_f0[0x8b] = '\0';
  local_f0[0x8c] = '\0';
  local_f0[0x8d] = '\0';
  local_f0[0x8e] = '\0';
  local_f0[0x8f] = '\0';
  local_f0[0x80] = '\0';
  local_f0[0x81] = '\0';
  local_f0[0x82] = '\0';
  local_f0[0x83] = '\0';
  local_f0[0x84] = '\0';
  local_f0[0x85] = '\0';
  local_f0[0x86] = '\0';
  local_f0[0x87] = '\0';
  local_f0[0x98] = '\0';
  local_f0[0x99] = '\0';
  local_f0[0x9a] = '\0';
  local_f0[0x9b] = '\0';
  local_f0[0x9c] = '\0';
  local_f0[0x9d] = '\0';
  local_f0[0x9e] = '\0';
  local_f0[0x9f] = '\0';
  local_f0[0x90] = '\0';
  local_f0[0x91] = '\0';
  local_f0[0x92] = '\0';
  local_f0[0x93] = '\0';
  local_f0[0x94] = '\0';
  local_f0[0x95] = '\0';
  local_f0[0x96] = '\0';
  local_f0[0x97] = '\0';
  local_f0[0xa8] = '\0';
  local_f0[0xa9] = '\0';
  local_f0[0xaa] = '\0';
  local_f0[0xab] = '\0';
  local_f0[0xac] = '\0';
  local_f0[0xad] = '\0';
  local_f0[0xae] = '\0';
  local_f0[0xaf] = '\0';
  local_f0[0xa0] = '\0';
  local_f0[0xa1] = '\0';
  local_f0[0xa2] = '\0';
  local_f0[0xa3] = '\0';
  local_f0[0xa4] = '\0';
  local_f0[0xa5] = '\0';
  local_f0[0xa6] = '\0';
  local_f0[0xa7] = '\0';
  local_f0[0xb8] = '\0';
  local_f0[0xb9] = '\0';
  local_f0[0xba] = '\0';
  local_f0[0xbb] = '\0';
  local_f0[0xbc] = '\0';
  local_f0[0xbd] = '\0';
  local_f0[0xbe] = '\0';
  local_f0[0xbf] = '\0';
  local_f0[0xb0] = '\0';
  local_f0[0xb1] = '\0';
  local_f0[0xb2] = '\0';
  local_f0[0xb3] = '\0';
  local_f0[0xb4] = '\0';
  local_f0[0xb5] = '\0';
  local_f0[0xb6] = '\0';
  local_f0[0xb7] = '\0';
  uVar1 = *(uint *)(lVar6 + 8);
  if ((((uVar1 & 0xfffffcff) == 0x400) || (uVar1 == 0x300)) || (uVar1 == 0x800)) {
    if (199 < *(uint *)(lVar6 + 0x18)) {
      psVar4 = (sml *)0x17;
      uVar5 = 0x170;
      goto LAB_00164778;
    }
    memscpy(local_f0,200,lVar6 + 0xe4,200);
    local_30 = local_30 & 0xffffffffffffff;
  }
  else {
    psVar4 = (sml *)0x1;
    uVar5 = 0x169;
LAB_00164778:
    sml::sml_debug_print
              (psVar4,
               "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/big_sound_model/ListenBigSoundModel.cpp"
               ,"getUserSpell",uVar5);
  }
  iVar3 = strcmp(param_1,local_f0);
  if (iVar3 != 0) {
    sml::sml_debug_print
              ((sml *)0x4,
               "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/big_sound_model/ListenBigSoundModel.cpp"
               ,"findUserIdByName",0x19a);
  }
  if (*(long *)(lVar2 + 0x28) == local_28) {
    return;
  }
LAB_001647cc:
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::registerNewUser
 * Address : 001647d0
 * Size    : 0x74 bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::registerNewUser(char*) */

void __thiscall ListenBigSoundModel::registerNewUser(ListenBigSoundModel *this,char *param_1)

{
  uint uVar1;
  sml *psVar2;
  
  if (param_1 != (char *)0x0) {
    uVar1 = sml_model_register_user(*(undefined8 *)(this + 8));
    if (uVar1 < 0x14) {
                    /* WARNING: Load size is inaccurate */
      psVar2._0_4_ = *(sml **)(&DAT_00118844 + (long)(int)uVar1 * 4);
    }
    else {
      psVar2._0_4_ = (sml *)0x1;
    }
    sml::sml_debug_print
              (psVar2._0_4_,
               "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/big_sound_model/ListenBigSoundModel.cpp"
               ,"registerNewUser",0x1a2);
    return;
  }
  sml::sml_debug_print
            ((sml *)0x2,
             "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/big_sound_model/ListenBigSoundModel.cpp"
             ,"registerNewUser",0x1a1);
  return;
}



/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::getBigSoundModelVersion
 * Address : 00164844
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::getBigSoundModelVersion() */

undefined4 __thiscall ListenBigSoundModel::getBigSoundModelVersion(ListenBigSoundModel *this)

{
  return *(undefined4 *)(*(long *)(this + 8) + 8);
}



/* ------------------------------------------------------------------
 * Function: ListenBigSoundModel::setBigSoundModelVersion
 * Address : 00164854
 * Size    : 0x9c bytes
 * ------------------------------------------------------------------ */

/* ListenBigSoundModel::setBigSoundModelVersion(int) */

undefined8 __thiscall
ListenBigSoundModel::setBigSoundModelVersion(ListenBigSoundModel *this,int param_1)

{
  undefined8 uVar1;
  
  if (*(void **)(this + 0x10) != (void *)0x0) {
    operator_delete__(*(void **)(this + 0x10));
  }
  *(undefined8 *)(this + 0x10) = 0;
  if (*(long *)(this + 8) != 0) {
    sml_model_destroy();
  }
  if ((((param_1 & 0xfffffcffU) != 0x400) && (param_1 != 0x300)) && (param_1 != 0x800)) {
    uVar1 = sml::sml_debug_print
                      ((sml *)0x1,
                       "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/big_sound_model/ListenBigSoundModel.cpp"
                       ,"setBigSoundModelVersion",0x1ae);
    return uVar1;
  }
  uVar1 = sml_model_create(param_1);
  *(undefined8 *)(this + 8) = uVar1;
  return 0;
}



/* ------------------------------------------------------------------
 * Function: isInvalidModelVersion
 * Address : 0016be7c
 * Size    : 0x14 bytes
 * ------------------------------------------------------------------ */

/* isInvalidModelVersion(_SVA_ModelType const*) */

bool isInvalidModelVersion(_SVA_ModelType *param_1)

{
  return *(int *)(param_1 + 8) != 5;
}



/* ------------------------------------------------------------------
 * Function: ListenSoundModel::readFrom
 * Address : 0016c054
 * Size    : 0x114 bytes
 * ------------------------------------------------------------------ */

/* ListenSoundModel::readFrom(unsigned char*, unsigned int) */

undefined8 __thiscall ListenSoundModel::readFrom(ListenSoundModel *this,uchar *param_1,uint param_2)

{
  uint uVar1;
  long lVar2;
  undefined8 uVar3;
  void *pvVar4;
  sml *psVar6;
  ulong uVar5;
  
  uVar5 = (ulong)param_2;
  if (*(void **)(this + 0x10) != (void *)0x0) {
    operator_delete__(*(void **)(this + 0x10));
  }
  *(undefined8 *)(this + 0x10) = 0;
  if (*(long *)(this + 8) != 0) {
    sva_model_destroy();
  }
  lVar2 = sva_model_create(5);
  *(long *)(this + 8) = lVar2;
  if (lVar2 != 0) {
    if (param_2 < 0xf4241) {
      pvVar4 = operator_new__(uVar5);
      *(void **)(this + 0x10) = pvVar4;
      memscpy(pvVar4,uVar5,param_1,uVar5);
      uVar1 = sva_model_decoder(*(undefined8 *)(this + 0x10),*(undefined8 *)(this + 8));
      if (*(int *)(*(long *)(this + 8) + 8) == 5) {
        if (uVar1 < 0x14) {
                    /* WARNING: Load size is inaccurate */
          psVar6._0_4_ = *(sml **)(&DAT_00118844 + (long)(int)uVar1 * 4);
        }
        else {
          psVar6._0_4_ = (sml *)0x1;
        }
        uVar3 = 0x8f;
        goto LAB_0016c154;
      }
      uVar3 = 6;
    }
    else {
      uVar3 = 2;
    }
    return uVar3;
  }
  psVar6._0_4_ = (sml *)0x2;
  uVar3 = 0x82;
LAB_0016c154:
  uVar3 = sml::sml_debug_print
                    (psVar6._0_4_,
                     "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/sound_model/ListenSoundModel.cpp"
                     ,"readFrom",uVar3);
  return uVar3;
}



/* ------------------------------------------------------------------
 * Function: ListenSoundModel::isStripped
 * Address : 0016c208
 * Size    : 0x12c bytes
 * ------------------------------------------------------------------ */

/* ListenSoundModel::isStripped() */

void __thiscall ListenSoundModel::isStripped(ListenSoundModel *this)

{
  long lVar1;
  bool bVar2;
  undefined4 uVar3;
  sml *psVar4;
  char local_2c [4];
  long local_28;
  
  lVar1 = tpidr_el0;
  local_28 = *(long *)(lVar1 + 0x28);
  local_2c[0] = '\0';
  uVar3 = sva_model_is_stripped(*(undefined8 *)(this + 8),local_2c);
  psVar4 = (sml *)0x1;
  switch(uVar3) {
  case 0:
    bVar2 = local_2c[0] != '\0';
    goto LAB_0016c30c;
  case 2:
    psVar4 = (sml *)0x6;
    break;
  case 7:
  case 8:
    psVar4 = (sml *)0x2;
    break;
  case 9:
    psVar4 = (sml *)0x17;
    break;
  case 0xb:
    psVar4 = (sml *)0xd;
    break;
  case 0xc:
    psVar4 = (sml *)0x3;
    break;
  case 0xd:
    psVar4 = (sml *)0x4;
    break;
  case 0xf:
    psVar4 = (sml *)0x7;
    break;
  case 0x10:
    psVar4 = (sml *)0x5;
    break;
  case 0x11:
    psVar4 = (sml *)0x13;
    break;
  case 0x12:
  case 0x13:
    psVar4 = (sml *)0x8;
  }
  sml::sml_debug_print
            (psVar4,
             "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/sound_model/ListenSoundModel.cpp"
             ,"isStripped",0xa4);
  bVar2 = false;
LAB_0016c30c:
  if (*(long *)(lVar1 + 0x28) == local_28) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail(bVar2);
}



/* ------------------------------------------------------------------
 * Function: sml::getSoundModelVersion
 * Address : 00187b78
 * Size    : 0x200 bytes
 * ------------------------------------------------------------------ */

/* sml::getSoundModelVersion(listen_model_type*, unsigned char*) */

undefined8 sml::getSoundModelVersion(listen_model_type *param_1,uchar *param_2)

{
  long lVar1;
  int iVar2;
  undefined8 uVar3;
  undefined8 local_68;
  undefined8 uStack_60;
  undefined8 local_58;
  undefined8 local_50;
  undefined8 uStack_48;
  undefined8 local_40;
  long local_38;
  
  lVar1 = tpidr_el0;
  local_38 = *(long *)(lVar1 + 0x28);
  if (*(long *)param_1 == 0) {
    uVar3 = 0x286;
  }
  else if (*(uint *)(param_1 + 8) == 0) {
    uVar3 = 0x287;
  }
  else {
    if (*(uint *)(param_1 + 8) < 0x5f5e101) {
      local_50 = 0;
      uStack_48 = 0;
      local_40 = 0;
      ListenSoundModel::ListenSoundModel((ListenSoundModel *)&local_50);
      iVar2 = ListenSoundModel::readFrom
                        ((ListenSoundModel *)&local_50,*(uchar **)param_1,*(uint *)(param_1 + 8));
      if (iVar2 == 0) {
        uVar3 = 0;
        *param_2 = '\x02';
      }
      else {
        local_68 = 0;
        uStack_60 = 0;
        local_58 = 0;
        ListenBigSoundModel::ListenBigSoundModel((ListenBigSoundModel *)&local_68);
        iVar2 = ListenBigSoundModel::readHeaderFrom
                          ((ListenBigSoundModel *)&local_68,*(uchar **)param_1);
        if (iVar2 == 0) {
          iVar2 = ListenBigSoundModel::getBigSoundModelVersion((ListenBigSoundModel *)&local_68);
          if ((uint)(CONCAT44(iVar2,iVar2 + -0x300) >> 8) < 6) {
            *param_2 = (char)((uint)(iVar2 + -0x300) >> 8) + '\x03';
            ListenBigSoundModel::~ListenBigSoundModel((ListenBigSoundModel *)&local_68);
            uVar3 = 0;
            goto LAB_00187d68;
          }
          __android_log_print(6,"JNILOG",
                              "[Audio-Systems] - [File : %s] [API : %s] [Line : %d] [ERROR - kWrongModel].\n"
                              ,
                              "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/ListenSoundModelLib_v8_Private.cpp"
                              ,"getSoundModelVersion",0x29a);
          uVar3 = 0x17;
        }
        else {
          uVar3 = 6;
          __android_log_print(6,"JNILOG",
                              "[Audio-Systems] - [File : %s] [API : %s] [Line : %d] [ERROR - kSMVersionUnsupported].\n"
                              ,
                              "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/ListenSoundModelLib_v8_Private.cpp"
                              ,"getSoundModelVersion",0x292);
        }
        ListenBigSoundModel::~ListenBigSoundModel((ListenBigSoundModel *)&local_68);
      }
LAB_00187d68:
      ListenSoundModel::~ListenSoundModel((ListenSoundModel *)&local_50);
      goto LAB_00187c48;
    }
    uVar3 = 0x288;
  }
  __android_log_print(6,"JNILOG",
                      "[Audio-Systems] - [File : %s] [API : %s] [Line : %d] [ERROR - kBadParam].\n",
                      "vendor/qcom/proprietary/commonsys/qva-noship/engine/native/qvasml/listen_sound_model/ListenSoundModelLib/ListenSoundModelLib_v8_Private.cpp"
                      ,"getSoundModelVersion",uVar3);
  uVar3 = 2;
LAB_00187c48:
  if (*(long *)(lVar1 + 0x28) == local_38) {
    return uVar3;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



