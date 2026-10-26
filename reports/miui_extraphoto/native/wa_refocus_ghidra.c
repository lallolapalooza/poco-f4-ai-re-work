/*
 * Ghidra headless decompilation
 * program   : libwa_refocus_extraphoto.so
 * executable: /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/xp/libwa_refocus_extraphoto.so
 * language  : AARCH64:LE:64:v8A
 * imagebase : 00100000
 * regex     : .*Refocus.*(run|setImages|init|setFaces|setBokeh).*
 * max funcs : 12
 * note      : pseudocode from a (possibly stripped) binary is approximate;
 *             indirect calls and register-indirect JNI tables are lost.
 */

/* ------------------------------------------------------------------
 * Function: wa::Refocus::init
 * Address : 002f0510
 * Size    : 0xdc bytes
 * ------------------------------------------------------------------ */

/* wa::Refocus::init(WRefocusConfig const&) */

void __thiscall wa::Refocus::init(Refocus *this,WRefocusConfig *param_1)

{
  long lVar1;
  int iVar2;
  undefined8 uVar3;
  byte local_28a0 [16];
  void *local_2890;
  undefined1 auStack_2888 [10336];
  long local_28;
  
  lVar1 = tpidr_el0;
  local_28 = *(long *)(lVar1 + 0x28);
  if (*(long *)this == 0) {
LAB_002f0554:
    uVar3 = 0;
    if (*(long *)(lVar1 + 0x28) != local_28) {
LAB_002f05e8:
                    /* WARNING: Subroutine does not return */
      __stack_chk_fail(uVar3);
    }
  }
  else {
    if ((*(long *)param_1 == 0) && (*(int *)(param_1 + 8) == 0)) {
      iVar2 = FUN_00771620();
      if (iVar2 < 5) {
        FUN_0076cf30(local_28a0,"don\'t set mecp data, use default config.");
        FUN_0076f560(auStack_2888,&DAT_001bf2e1,0x49,4,&DAT_001fda14,local_28a0);
        FUN_0076fb84(auStack_2888);
        if ((local_28a0[0] & 1) != 0) {
          free(local_2890);
        }
      }
    }
    else {
      iVar2 = FUN_002f5894(*(undefined8 *)(*(long *)this + 8));
      if (iVar2 != 0) goto LAB_002f0554;
    }
    uVar3 = 1;
    if (*(long *)(lVar1 + 0x28) != local_28) goto LAB_002f05e8;
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: wa::Refocus::setFaces
 * Address : 002f06e4
 * Size    : 0xc0 bytes
 * ------------------------------------------------------------------ */

/* wa::Refocus::setFaces(WFace const*, int, wa::Image::Rotation) */

void wa::Refocus::setFaces(long *param_1)

{
  long lVar1;
  int iVar2;
  undefined8 uVar3;
  byte local_28a0 [16];
  void *local_2890;
  undefined1 auStack_2888 [10336];
  long local_28;
  
  lVar1 = tpidr_el0;
  local_28 = *(long *)(lVar1 + 0x28);
  if (*param_1 == 0) {
    uVar3 = 0;
    if (*(long *)(lVar1 + 0x28) != local_28) goto LAB_002f07a0;
  }
  else {
    iVar2 = FUN_00771620();
    if (iVar2 < 4) {
      FUN_0076cf30(local_28a0,"[RF] not support setFaces anymore.");
      FUN_0076f560(auStack_2888,&DAT_001bf2e1,0x5b,3,&DAT_001fda14,local_28a0);
      FUN_0076fb84(auStack_2888);
      if ((local_28a0[0] & 1) != 0) {
        free(local_2890);
      }
    }
    uVar3 = 1;
    if (*(long *)(lVar1 + 0x28) != local_28) {
LAB_002f07a0:
                    /* WARNING: Subroutine does not return */
      __stack_chk_fail(uVar3);
    }
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: wa::Refocus::setImages
 * Address : 002f07c0
 * Size    : 0x14 bytes
 * ------------------------------------------------------------------ */

/* wa::Refocus::setImages(wa::Image const&, wa::Image const&) */

void wa::Refocus::setImages(Image *param_1,Image *param_2)

{
  if (*(long *)param_1 != 0) {
    FUN_002f5994(*(undefined8 *)(*(long *)param_1 + 8));
    return;
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: wa::Refocus::setImages
 * Address : 002f07d4
 * Size    : 0x14 bytes
 * ------------------------------------------------------------------ */

/* wa::Refocus::setImages(wa::Image const&, wa::Image const&, wa::Image const&) */

void wa::Refocus::setImages(Image *param_1,Image *param_2,Image *param_3)

{
  if (*(long *)param_1 != 0) {
    FUN_002f9af4(*(undefined8 *)(*(long *)param_1 + 8));
    return;
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: wa::Refocus::setBokehSpotType
 * Address : 002f07e8
 * Size    : 0x14 bytes
 * ------------------------------------------------------------------ */

/* wa::Refocus::setBokehSpotType(_WBokehSpotType) */

void wa::Refocus::setBokehSpotType(long *param_1)

{
  if (*param_1 != 0) {
    FUN_002fdcb4(*(undefined8 *)(*param_1 + 8));
    return;
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: wa::Refocus::setBokehStyle
 * Address : 002f07fc
 * Size    : 0x30 bytes
 * ------------------------------------------------------------------ */

/* wa::Refocus::setBokehStyle(_WBokehStyle) */

bool wa::Refocus::setBokehStyle(long *param_1)

{
  int iVar1;
  
  if (*param_1 != 0) {
    iVar1 = FUN_002fdea0(*(undefined8 *)(*param_1 + 8));
    return iVar1 == 0;
  }
  return false;
}



/* ------------------------------------------------------------------
 * Function: wa::Refocus::run
 * Address : 002f082c
 * Size    : 0x30 bytes
 * ------------------------------------------------------------------ */

/* wa::Refocus::run(int, int, float, wa::Image&) */

bool wa::Refocus::run(int param_1,int param_2,float param_3,Image *param_4)

{
  int iVar1;
  
  if (*(long *)(ulong)(uint)param_1 != 0) {
    iVar1 = FUN_002fe090(*(undefined8 *)(*(long *)(ulong)(uint)param_1 + 8),param_2);
    return iVar1 == 0;
  }
  return false;
}



