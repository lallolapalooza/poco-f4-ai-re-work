/*
 * Ghidra headless decompilation
 * program   : libCGE.so
 * executable: /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/kwai_live/native/libs/lib/arm64-v8a/libCGE.so
 * language  : AARCH64:LE:64:v8A
 * imagebase : 00100000
 * regex     : Liquify
 * max funcs : 40
 * note      : pseudocode from a (possibly stripped) binary is approximate;
 *             indirect calls and register-indirect JNI tables are lost.
 */

/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::restoreMesh
 * Address : 001dca30
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void __thiscall CGE::CGELiquifyFilter::restoreMesh(CGELiquifyFilter *this)

{
  (*(code *)PTR_restoreMesh_00384388)();
  return;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::initWithMesh
 * Address : 001dcb90
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void __thiscall
CGE::CGELiquifyFilter::initWithMesh
          (CGELiquifyFilter *this,float param_1,float param_2,float param_3)

{
  (*(code *)PTR_initWithMesh_00384438)();
  return;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::CGELiquifyFilter
 * Address : 001de460
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void __thiscall CGE::CGELiquifyFilter::CGELiquifyFilter(CGELiquifyFilter *this)

{
  (*(code *)PTR_CGELiquifyFilter_003850a0)();
  return;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::initBuffers
 * Address : 001de7c0
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void __thiscall CGE::CGELiquifyFilter::initBuffers(CGELiquifyFilter *this)

{
  (*(code *)PTR_initBuffers_00385250)();
  return;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::~CGELiquifyFilter
 * Address : 001def40
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void __thiscall CGE::CGELiquifyFilter::~CGELiquifyFilter(CGELiquifyFilter *this)

{
  (*(code *)PTR__CGELiquifyFilter_00385610)();
  return;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::CGELiquifyFilter
 * Address : 001def90
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void __thiscall CGE::CGELiquifyFilter::CGELiquifyFilter(CGELiquifyFilter *this)

{
  (*(code *)PTR_CGELiquifyFilter_00385638)();
  return;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::initWithMesh
 * Address : 001e05d0
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void __thiscall
CGE::CGELiquifyFilter::initWithMesh(CGELiquifyFilter *this,float param_1,float param_2)

{
  (*(code *)PTR_initWithMesh_00386158)();
  return;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::pushMesh
 * Address : 001e0730
 * Size    : 0x10 bytes
 * ------------------------------------------------------------------ */

void __thiscall CGE::CGELiquifyFilter::pushMesh(CGELiquifyFilter *this)

{
  (*(code *)PTR_pushMesh_00386208)();
  return;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::CGELiquifyFilter
 * Address : 00231db8
 * Size    : 0x1b0 bytes
 * ------------------------------------------------------------------ */

/* WARNING: Removing unreachable block (ram,0x00231ea8) */
/* WARNING: Removing unreachable block (ram,0x00231ed8) */
/* WARNING: Removing unreachable block (ram,0x00231f1c) */
/* WARNING: Removing unreachable block (ram,0x00231f00) */
/* WARNING: Removing unreachable block (ram,0x00231f08) */
/* CGE::CGELiquifyFilter::CGELiquifyFilter() */

void __thiscall CGE::CGELiquifyFilter::CGELiquifyFilter(CGELiquifyFilter *this)

{
  ProgramObject *this_00;
  undefined *puVar1;
  undefined8 *puVar2;
  undefined8 *puVar3;
  undefined8 *puVar4;
  ulong uVar5;
  void *pvVar6;
  
  CGEImageFilterInterface::CGEImageFilterInterface((CGEImageFilterInterface *)this);
  this_00 = (ProgramObject *)(this + 0x98);
  puVar1 = PTR_vtable_003869d8 + 0x10;
  this[0x94] = (CGELiquifyFilter)0x0;
  *(undefined8 *)(this + 0x44) = 0;
  *(undefined8 *)(this + 0x54) = 0;
  *(undefined8 *)(this + 0x4c) = 0;
  *(undefined8 *)(this + 0x68) = 0;
  *(undefined8 *)(this + 0x60) = 0;
  *(undefined8 *)(this + 0x78) = 0;
  *(undefined8 *)(this + 0x70) = 0;
  *(undefined **)this = puVar1;
  *(undefined8 *)(this + 0x88) = 0;
  *(undefined8 *)(this + 0x80) = 0;
  ProgramObject::ProgramObject(this_00);
  ProgramObject::bindAttribLocation((ProgramObject *)(this + 8),"vTexture",1);
  ProgramObject::bindAttribLocation(this_00,*(char **)PTR_paramPositionIndexName_00386c10,0);
  ProgramObject::bindAttribLocation(this_00,"vTexture",1);
  uVar5 = ProgramObject::initWithShaderStrings
                    (this_00,
                     "#ifdef GL_ES\nprecision highp float;\n#endif\n\n#ifndef GL_ES\n#define lowp\n#define mediump\n#define highp\n#endif\nattribute vec2 vPosition; attribute vec2 vTexture; varying highp vec2 textureCoordinate; void main() { gl_Position = vec4(vPosition * 2.0 - 1.0, 0.0, 1.0); textureCoordinate = vTexture; }"
                     ,
                     "#ifdef GL_ES\nprecision lowp float;\n#endif\n\n#ifndef GL_ES\n#define lowp\n#define mediump\n#define highp\n#endif\nvarying vec2 textureCoordinate; uniform sampler2D inputImageTexture; void main() { gl_FragColor.rgb = 1.0 - texture2D(inputImageTexture, textureCoordinate).rgb; gl_FragColor.a = 1.0; }"
                    );
  if ((uVar5 & 1) == 0) {
    _cgeLogInfoReport(2,"Init Mesh Program Failed!\n");
  }
  this[200] = (CGELiquifyFilter)0x0;
  *(undefined4 *)(this + 0x40) = 10;
  if (10 < *(uint *)(this + 0x44)) {
    puVar3 = *(undefined8 **)(this + 0x80);
    *(undefined4 *)(this + 0x44) = 10;
    puVar2 = (undefined8 *)(*(long *)(this + 0x78) + 0xf0);
    if (puVar2 != puVar3) {
      if (puVar3 != puVar2) {
        pvVar6 = (void *)puVar3[-3];
        puVar4 = puVar3 + -3;
        while( true ) {
          if (pvVar6 != (void *)0x0) {
            puVar3[-2] = pvVar6;
            operator_delete(pvVar6);
          }
          if (puVar2 == puVar4) break;
          pvVar6 = (void *)puVar4[-3];
          puVar3 = puVar4;
          puVar4 = puVar4 + -3;
        }
      }
      *(undefined8 **)(this + 0x80) = puVar2;
    }
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::setUndoSteps
 * Address : 00231f68
 * Size    : 0x144 bytes
 * ------------------------------------------------------------------ */

/* WARNING: Removing unreachable block (ram,0x00231fb4) */
/* WARNING: Removing unreachable block (ram,0x00231fe4) */
/* WARNING: Removing unreachable block (ram,0x00232060) */
/* WARNING: Removing unreachable block (ram,0x0023200c) */
/* WARNING: Removing unreachable block (ram,0x00232014) */
/* CGE::CGELiquifyFilter::setUndoSteps(unsigned int) */

void __thiscall CGE::CGELiquifyFilter::setUndoSteps(CGELiquifyFilter *this,uint param_1)

{
  undefined8 *puVar1;
  undefined8 *puVar2;
  void *pvVar3;
  undefined8 *puVar4;
  
  *(uint *)(this + 0x40) = param_1;
  if (param_1 == 0) {
    puVar4 = *(undefined8 **)(this + 0x78);
    puVar1 = *(undefined8 **)(this + 0x80);
    if (puVar1 != puVar4) {
      pvVar3 = (void *)puVar1[-3];
      puVar2 = puVar1 + -3;
      while( true ) {
        if (pvVar3 != (void *)0x0) {
          puVar1[-2] = pvVar3;
          operator_delete(pvVar3);
        }
        if (puVar4 == puVar2) break;
        pvVar3 = (void *)puVar2[-3];
        puVar1 = puVar2;
        puVar2 = puVar2 + -3;
      }
    }
  }
  else {
    if (*(uint *)(this + 0x44) <= param_1) {
      return;
    }
    puVar1 = *(undefined8 **)(this + 0x80);
    *(uint *)(this + 0x44) = param_1;
    puVar4 = (undefined8 *)(*(long *)(this + 0x78) + (ulong)param_1 * 0x18);
    if (puVar4 == puVar1) {
      return;
    }
    if (puVar1 != puVar4) {
      pvVar3 = (void *)puVar1[-3];
      puVar2 = puVar1 + -3;
      while( true ) {
        if (pvVar3 != (void *)0x0) {
          puVar1[-2] = pvVar3;
          operator_delete(pvVar3);
        }
        if (puVar4 == puVar2) break;
        pvVar3 = (void *)puVar2[-3];
        puVar1 = puVar2;
        puVar2 = puVar2 + -3;
      }
    }
  }
  *(undefined8 **)(this + 0x80) = puVar4;
  return;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::~CGELiquifyFilter
 * Address : 002320ac
 * Size    : 0x24 bytes
 * ------------------------------------------------------------------ */

/* CGE::CGELiquifyFilter::~CGELiquifyFilter() */

void __thiscall CGE::CGELiquifyFilter::~CGELiquifyFilter(CGELiquifyFilter *this)

{
  ~CGELiquifyFilter(this);
  operator_delete(this);
  return;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::initWithMesh
 * Address : 002320d0
 * Size    : 0x18 bytes
 * ------------------------------------------------------------------ */

/* CGE::CGELiquifyFilter::initWithMesh(float, float, float) */

void __thiscall
CGE::CGELiquifyFilter::initWithMesh
          (CGELiquifyFilter *this,float param_1,float param_2,float param_3)

{
  float fVar1;
  
  fVar1 = param_1 / param_2;
  if (param_1 <= param_2) {
    param_1 = param_2;
  }
  initWithMesh(this,fVar1,param_3 / param_1);
  return;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::initWithMesh
 * Address : 002320e8
 * Size    : 0xfc bytes
 * ------------------------------------------------------------------ */

/* CGE::CGELiquifyFilter::initWithMesh(float, float) */

undefined8 __thiscall
CGE::CGELiquifyFilter::initWithMesh(CGELiquifyFilter *this,float param_1,float param_2)

{
  float fVar1;
  ulong uVar2;
  undefined8 uVar3;
  long lVar4;
  ulong uVar5;
  float fVar6;
  float fVar7;
  
  uVar2 = CGEImageFilterInterface::initShadersFromString
                    ((CGEImageFilterInterface *)this,
                     "#ifdef GL_ES\nprecision highp float;\n#endif\n\n#ifndef GL_ES\n#define lowp\n#define mediump\n#define highp\n#endif\nattribute vec2 vPosition; attribute vec2 vTexture; varying highp vec2 textureCoordinate; void main() { gl_Position = vec4(vPosition * 2.0 - 1.0, 0.0, 1.0); textureCoordinate = vTexture; }"
                     ,
                     "#ifdef GL_ES\nprecision mediump float;\n#endif\n\n#ifndef GL_ES\n#define lowp\n#define mediump\n#define highp\n#endif\nvarying highp vec2 textureCoordinate; uniform sampler2D inputImageTexture; void main() { gl_FragColor = texture2D(inputImageTexture, textureCoordinate); }"
                    );
  if ((uVar2 & 1) == 0) {
    uVar3 = 0;
  }
  else {
    fVar6 = 0.001;
    if ((0.001 <= param_2) && (fVar6 = param_2, 0.2 < param_2)) {
      fVar6 = 0.2;
    }
    lVar4 = *(long *)(this + 0x60);
    fVar6 = 1.0 / fVar6;
    fVar7 = fVar6;
    fVar1 = fVar6 / param_1;
    if (param_1 <= 1.0) {
      fVar7 = fVar6 * param_1;
      fVar1 = fVar6;
    }
    uVar2 = (ulong)((int)fVar7 * (int)fVar1);
    uVar5 = *(long *)(this + 0x68) - lVar4 >> 3;
    *(int *)(this + 0x54) = (int)fVar7;
    *(int *)(this + 0x58) = (int)fVar1;
    if (uVar5 < uVar2) {
      std::__ndk1::vector<CGE::Vec<float,2>,std::__ndk1::allocator<CGE::Vec<float,2>>>::__append
                ((vector<CGE::Vec<float,2>,std::__ndk1::allocator<CGE::Vec<float,2>>> *)
                 (this + 0x60),uVar2 - uVar5);
    }
    else if (uVar5 != uVar2) {
      *(ulong *)(this + 0x68) = lVar4 + uVar2 * 8;
    }
    restoreMesh(this);
    initBuffers(this);
    uVar3 = 1;
  }
  return uVar3;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::restoreMesh
 * Address : 002321e4
 * Size    : 0x1e0 bytes
 * ------------------------------------------------------------------ */

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */
/* CGE::CGELiquifyFilter::restoreMesh() */

void __thiscall CGE::CGELiquifyFilter::restoreMesh(CGELiquifyFilter *this)

{
  ulong uVar1;
  undefined8 *puVar2;
  undefined8 *puVar3;
  undefined8 *puVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  void *pvVar7;
  int iVar8;
  int iVar9;
  int iVar10;
  ulong uVar11;
  long lVar12;
  long lVar13;
  float *pfVar14;
  long lVar15;
  ulong uVar16;
  float fVar17;
  float fVar18;
  int iVar19;
  float fVar20;
  int iVar21;
  int iVar22;
  int iVar23;
  undefined1 auVar24 [16];
  undefined1 auVar25 [16];
  undefined1 auVar26 [16];
  
  iVar8 = *(int *)(this + 0x54);
  iVar9 = *(int *)(this + 0x58);
  if (((long)(iVar9 * iVar8) != *(long *)(this + 0x68) - *(long *)(this + 0x60) >> 3) ||
     (*(long *)(this + 0x60) == *(long *)(this + 0x68))) {
    _cgeLogInfoReport(2,"Invalid Mesh!\n");
    iVar8 = *(int *)(this + 0x54);
    iVar9 = *(int *)(this + 0x58);
  }
  uVar6 = _UNK_002ef1f8;
  uVar5 = _DAT_002ef1f0;
  if (iVar9 != 0) {
    uVar1 = (ulong)(iVar8 - 1) + 1;
    fVar17 = 1.0 / ((float)iVar8 + -1.0);
    iVar10 = 0;
    uVar11 = uVar1 & 0x1fffffff8;
    lVar12 = 0;
    do {
      if (iVar8 != 0) {
        lVar13 = *(long *)(this + 0x60);
        fVar18 = (1.0 / ((float)iVar9 + -1.0)) * (float)iVar10;
        if (uVar1 < 8) {
          iVar19 = 0;
          lVar15 = lVar12;
        }
        else {
          lVar15 = uVar11 + lVar12;
          pfVar14 = (float *)(lVar13 + lVar12 * 8 + 0x20);
          iVar22 = (int)uVar6;
          iVar23 = (int)((ulong)uVar6 >> 0x20);
          iVar19 = (int)uVar5;
          iVar21 = (int)((ulong)uVar5 >> 0x20);
          uVar16 = uVar11;
          do {
            auVar24._0_4_ = iVar19 + 4;
            auVar24._4_4_ = iVar21 + 4;
            auVar24._8_4_ = iVar22 + 4;
            auVar24._12_4_ = iVar23 + 4;
            auVar25._4_4_ = iVar21;
            auVar25._0_4_ = iVar19;
            auVar25._8_4_ = iVar22;
            auVar25._12_4_ = iVar23;
            auVar26 = NEON_scvtf(auVar25,4);
            auVar25 = NEON_scvtf(auVar24,4);
            iVar19 = iVar19 + 8;
            iVar21 = iVar21 + 8;
            iVar22 = iVar22 + 8;
            iVar23 = iVar23 + 8;
            uVar16 = uVar16 - 8;
            pfVar14[-8] = fVar17 * auVar26._0_4_;
            pfVar14[-7] = fVar18;
            pfVar14[-6] = fVar17 * auVar26._4_4_;
            pfVar14[-5] = fVar18;
            pfVar14[-4] = fVar17 * auVar26._8_4_;
            pfVar14[-3] = fVar18;
            pfVar14[-2] = fVar17 * auVar26._12_4_;
            pfVar14[-1] = fVar18;
            *pfVar14 = fVar17 * auVar25._0_4_;
            pfVar14[1] = fVar18;
            pfVar14[2] = fVar17 * auVar25._4_4_;
            pfVar14[3] = fVar18;
            pfVar14[4] = fVar17 * auVar25._8_4_;
            pfVar14[5] = fVar18;
            pfVar14[6] = fVar17 * auVar25._12_4_;
            pfVar14[7] = fVar18;
            pfVar14 = pfVar14 + 0x10;
          } while (uVar16 != 0);
          iVar19 = (int)uVar11;
          if (uVar1 == uVar11) goto LAB_00232284;
        }
        pfVar14 = (float *)(lVar13 + lVar15 * 8 + 4);
        do {
          fVar20 = (float)iVar19;
          iVar19 = iVar19 + 1;
          pfVar14[-1] = fVar17 * fVar20;
          *pfVar14 = fVar18;
          pfVar14 = pfVar14 + 2;
        } while (iVar8 != iVar19);
      }
LAB_00232284:
      iVar10 = iVar10 + 1;
      if (iVar10 == iVar9) break;
      lVar12 = (long)((int)lVar12 + iVar8);
    } while( true );
  }
  puVar2 = *(undefined8 **)(this + 0x78);
  puVar3 = *(undefined8 **)(this + 0x80);
  if (puVar3 != puVar2) {
    pvVar7 = (void *)puVar3[-3];
    puVar4 = puVar3 + -3;
    while( true ) {
      if (pvVar7 != (void *)0x0) {
        puVar3[-2] = pvVar7;
        operator_delete(pvVar7);
      }
      if (puVar2 == puVar4) break;
      pvVar7 = (void *)puVar4[-3];
      puVar3 = puVar4;
      puVar4 = puVar4 + -3;
    }
  }
  *(undefined8 **)(this + 0x80) = puVar2;
  if (*(int *)(this + 0x48) != 0) {
    glBindBuffer(0x8892);
    glBufferData(0x8892,*(long *)(this + 0x68) - *(long *)(this + 0x60),*(long *)(this + 0x60),
                 0x88e0);
    glBindBuffer(0x8892,0);
    return;
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::initBuffers
 * Address : 002323c4
 * Size    : 0x27c bytes
 * ------------------------------------------------------------------ */

/* CGE::CGELiquifyFilter::initBuffers() */

undefined8 __thiscall CGE::CGELiquifyFilter::initBuffers(CGELiquifyFilter *this)

{
  bool bVar1;
  uint uVar2;
  int iVar3;
  short sVar4;
  short sVar5;
  long lVar6;
  int iVar7;
  short sVar8;
  short sVar9;
  short *psVar10;
  int iVar11;
  int iVar12;
  long lVar13;
  uint uVar14;
  int iVar15;
  int iVar16;
  int iVar17;
  CGELiquifyFilter *pCVar18;
  undefined4 local_54;
  void *local_50;
  void *local_48;
  undefined8 uStack_40;
  long local_38;
  
  lVar6 = tpidr_el0;
  local_38 = *(long *)(lVar6 + 0x28);
  pCVar18 = this + 0x48;
  local_50._0_4_ = *(undefined4 *)pCVar18;
  glDeleteBuffers(1,&local_50);
  *(undefined4 *)pCVar18 = 0;
  glGenBuffers(1,pCVar18);
  glBindBuffer(0x8892,*(undefined4 *)pCVar18);
  glBufferData(0x8892,*(long *)(this + 0x68) - *(long *)(this + 0x60),*(long *)(this + 0x60),0x88e0)
  ;
  pCVar18 = this + 0x50;
  local_50 = (void *)CONCAT44(local_50._4_4_,*(undefined4 *)pCVar18);
  glDeleteBuffers(1,&local_50);
  *(undefined4 *)pCVar18 = 0;
  glGenBuffers(1,pCVar18);
  glBindBuffer(0x8892,*(undefined4 *)pCVar18);
  glBufferData(0x8892,*(long *)(this + 0x68) - *(long *)(this + 0x60),*(long *)(this + 0x60),0x88e4)
  ;
  local_48 = (void *)0x0;
  uStack_40 = 0;
  local_50 = (void *)0x0;
  iVar15 = *(int *)(this + 0x58);
  lVar13 = ((long)iVar15 + -1) * ((long)*(int *)(this + 0x54) + -1);
  iVar11 = (int)lVar13;
  *(int *)(this + 0x90) = iVar11 << 1;
  if (iVar11 != 0) {
    std::__ndk1::vector<unsigned_short,std::__ndk1::allocator<unsigned_short>>::__append
              ((vector<unsigned_short,std::__ndk1::allocator<unsigned_short>> *)&local_50,lVar13 * 6
              );
    iVar15 = *(int *)(this + 0x58);
  }
  if (1 < iVar15) {
    iVar3 = *(int *)(this + 0x54);
    iVar12 = 0;
    iVar11 = 0;
    iVar16 = iVar3 * 2 + -2;
    uVar14 = 0;
    do {
      uVar2 = uVar14 + 1;
      sVar9 = (short)iVar12;
      if ((uVar14 & 1) == 0) {
        if (-1 < iVar3 + -2) {
          psVar10 = (short *)((long)local_50 + (long)iVar11 * 2 + 6);
          iVar17 = iVar16;
          iVar7 = iVar3 + -2;
          do {
            sVar8 = sVar9 + (short)iVar7;
            sVar5 = (short)iVar17;
            sVar4 = sVar8 + 1;
            psVar10[-1] = sVar5;
            psVar10[2] = sVar5;
            iVar17 = iVar17 + -1;
            iVar11 = iVar11 + 6;
            psVar10[-2] = sVar5 + 1;
            *psVar10 = sVar8;
            psVar10[-3] = sVar4;
            psVar10[1] = sVar4;
            psVar10 = psVar10 + 6;
            bVar1 = 0 < iVar7;
            iVar7 = iVar7 + -1;
          } while (bVar1);
        }
      }
      else if (0 < iVar3 + -1) {
        iVar17 = 0;
        psVar10 = (short *)((long)local_50 + (long)iVar11 * 2 + 6);
        do {
          sVar5 = sVar9 + (short)iVar17;
          sVar4 = (short)iVar3 + sVar9 + (short)iVar17;
          iVar17 = iVar17 + 1;
          psVar10[-3] = sVar5;
          sVar5 = sVar5 + 1;
          psVar10[-1] = sVar4;
          *psVar10 = sVar4;
          iVar11 = iVar11 + 6;
          psVar10[-2] = sVar5;
          psVar10[1] = sVar5;
          psVar10[2] = sVar4 + 1;
          psVar10 = psVar10 + 6;
        } while (iVar3 + -1 != iVar17);
      }
      iVar12 = iVar12 + iVar3;
      iVar16 = iVar16 + iVar3;
      uVar14 = uVar2;
    } while ((int)uVar2 < iVar15 + -1);
  }
  pCVar18 = this + 0x4c;
  local_54 = *(undefined4 *)pCVar18;
  glDeleteBuffers(1,&local_54);
  *(undefined4 *)pCVar18 = 0;
  glGenBuffers(1,pCVar18);
  glBindBuffer(0x8893,*(undefined4 *)pCVar18);
  glBufferData(0x8893,(long)local_48 - (long)local_50,local_50,0x88e4);
  glBindBuffer(0x8892,0);
  glBindBuffer(0x8893,0);
  if (local_50 != (void *)0x0) {
    local_48 = local_50;
    operator_delete(local_50);
  }
  if (*(long *)(lVar6 + 0x28) != local_38) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  return 1;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::updateBuffers
 * Address : 00232640
 * Size    : 0x58 bytes
 * ------------------------------------------------------------------ */

/* CGE::CGELiquifyFilter::updateBuffers() */

bool __thiscall CGE::CGELiquifyFilter::updateBuffers(CGELiquifyFilter *this)

{
  int iVar1;
  
  iVar1 = *(int *)(this + 0x48);
  if (iVar1 != 0) {
    glBindBuffer(0x8892);
    glBufferData(0x8892,*(long *)(this + 0x68) - *(long *)(this + 0x60),*(long *)(this + 0x60),
                 0x88e0);
    glBindBuffer(0x8892,0);
  }
  return iVar1 != 0;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::restoreMeshWithIntensity
 * Address : 00232698
 * Size    : 0x22c bytes
 * ------------------------------------------------------------------ */

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */
/* CGE::CGELiquifyFilter::restoreMeshWithIntensity(float) */

void __thiscall
CGE::CGELiquifyFilter::restoreMeshWithIntensity(CGELiquifyFilter *this,float param_1)

{
  int iVar1;
  int iVar2;
  uint uVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  long lVar6;
  ulong uVar7;
  float *pfVar8;
  char *pcVar9;
  float *pfVar10;
  ulong uVar11;
  int iVar12;
  long lVar13;
  ulong uVar14;
  long lVar15;
  ulong uVar16;
  undefined8 *puVar17;
  ulong uVar18;
  undefined8 *puVar19;
  float fVar20;
  float fVar21;
  float fVar22;
  float fVar23;
  int iVar24;
  int iVar25;
  int iVar26;
  int iVar27;
  undefined1 auVar28 [16];
  float fVar29;
  float fVar30;
  float fVar31;
  float fVar32;
  float fVar33;
  float fVar34;
  float fVar35;
  
  iVar1 = *(int *)(this + 0x54);
  iVar2 = *(int *)(this + 0x58);
  if (((long)(iVar2 * iVar1) == *(long *)(this + 0x68) - *(long *)(this + 0x60) >> 3) &&
     (*(long *)(this + 0x60) != *(long *)(this + 0x68))) {
    uVar4 = _DAT_002ef1f0;
    uVar5 = _UNK_002ef1f8;
    if (this[0x94] == (CGELiquifyFilter)0x0) {
      uVar7 = pushMesh(this);
      if ((uVar7 & 1) == 0) {
        pcVar9 = "DeformProcessor::restoreMeshWithIntensity failed!\n";
        goto LAB_00232840;
      }
      iVar1 = *(int *)(this + 0x54);
      iVar2 = *(int *)(this + 0x58);
      uVar4 = _DAT_002ef1f0;
      uVar5 = _UNK_002ef1f8;
    }
    _DAT_002ef1f0 = uVar4;
    _UNK_002ef1f8 = uVar5;
    if (iVar2 != 0) {
      lVar13 = *(long *)(this + 0x78);
      uVar3 = *(uint *)(this + 0x44);
      fVar21 = 1.0 - param_1;
      uVar7 = (ulong)(iVar1 - 1) + 1;
      fVar20 = 1.0 / ((float)iVar1 + -1.0);
      iVar12 = 0;
      uVar14 = uVar7 & 0x1fffffffc;
      lVar15 = 0;
      do {
        if (iVar1 != 0) {
          uVar16 = *(ulong *)(lVar13 + (ulong)uVar3 * 0x18);
          uVar18 = *(ulong *)(this + 0x60);
          fVar22 = (1.0 / ((float)iVar2 + -1.0)) * (float)iVar12 * param_1;
          if ((uVar7 < 4) || ((uVar18 < uVar16 + uVar7 * 8 && (uVar16 < uVar18 + uVar7 * 8)))) {
            iVar24 = 0;
            lVar6 = lVar15;
          }
          else {
            lVar6 = uVar14 + lVar15;
            pfVar8 = (float *)(uVar18 + lVar15 * 8);
            pfVar10 = (float *)(uVar16 + lVar15 * 8);
            iVar26 = (int)uVar5;
            iVar27 = (int)((ulong)uVar5 >> 0x20);
            iVar24 = (int)uVar4;
            iVar25 = (int)((ulong)uVar4 >> 0x20);
            uVar11 = uVar14;
            do {
              fVar23 = *pfVar10;
              fVar32 = pfVar10[1];
              fVar29 = pfVar10[2];
              fVar33 = pfVar10[3];
              fVar30 = pfVar10[4];
              fVar34 = pfVar10[5];
              fVar31 = pfVar10[6];
              fVar35 = pfVar10[7];
              pfVar10 = pfVar10 + 8;
              auVar28._4_4_ = iVar25;
              auVar28._0_4_ = iVar24;
              auVar28._8_4_ = iVar26;
              auVar28._12_4_ = iVar27;
              auVar28 = NEON_scvtf(auVar28,4);
              uVar11 = uVar11 - 4;
              *pfVar8 = fVar20 * auVar28._0_4_ * param_1 + fVar21 * fVar23;
              pfVar8[1] = fVar22 + fVar21 * fVar32;
              pfVar8[2] = fVar20 * auVar28._4_4_ * param_1 + fVar21 * fVar29;
              pfVar8[3] = fVar22 + fVar21 * fVar33;
              pfVar8[4] = fVar20 * auVar28._8_4_ * param_1 + fVar21 * fVar30;
              pfVar8[5] = fVar22 + fVar21 * fVar34;
              pfVar8[6] = fVar20 * auVar28._12_4_ * param_1 + fVar21 * fVar31;
              pfVar8[7] = fVar22 + fVar21 * fVar35;
              pfVar8 = pfVar8 + 8;
              iVar24 = iVar24 + 4;
              iVar25 = iVar25 + 4;
              iVar26 = iVar26 + 4;
              iVar27 = iVar27 + 4;
            } while (uVar11 != 0);
            iVar24 = (int)uVar14;
            if (uVar7 == uVar14) goto LAB_0023274c;
          }
          puVar17 = (undefined8 *)(uVar16 + lVar6 * 8);
          puVar19 = (undefined8 *)(uVar18 + lVar6 * 8);
          do {
            fVar23 = (float)iVar24;
            iVar24 = iVar24 + 1;
            *puVar19 = CONCAT44(fVar22 + fVar21 * (float)((ulong)*puVar17 >> 0x20),
                                fVar20 * fVar23 * param_1 + fVar21 * (float)*puVar17);
            puVar17 = puVar17 + 1;
            puVar19 = puVar19 + 1;
          } while (iVar1 != iVar24);
        }
LAB_0023274c:
        iVar12 = iVar12 + 1;
        if (iVar12 == iVar2) break;
        lVar15 = (long)((int)lVar15 + iVar1);
      } while( true );
    }
    if (*(int *)(this + 0x48) != 0) {
      glBindBuffer(0x8892);
      glBufferData(0x8892,*(long *)(this + 0x68) - *(long *)(this + 0x60),*(long *)(this + 0x60),
                   0x88e0);
      glBindBuffer(0x8892,0);
    }
    this[0x94] = (CGELiquifyFilter)0x1;
    return;
  }
  pcVar9 = "Invalid Mesh!\n";
LAB_00232840:
  _cgeLogInfoReport(2,pcVar9);
  return;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::pushMesh
 * Address : 002328c4
 * Size    : 0x2f4 bytes
 * ------------------------------------------------------------------ */

/* CGE::CGELiquifyFilter::pushMesh() */

undefined8 __thiscall CGE::CGELiquifyFilter::pushMesh(CGELiquifyFilter *this)

{
  undefined8 *puVar1;
  undefined4 *puVar2;
  undefined4 *puVar3;
  uint uVar4;
  void *pvVar5;
  undefined4 *puVar6;
  undefined8 *puVar7;
  undefined8 *puVar8;
  ulong uVar9;
  long lVar10;
  undefined4 *puVar11;
  ulong uVar12;
  ulong uVar13;
  undefined8 *puVar14;
  undefined8 uVar15;
  undefined8 uVar16;
  undefined8 uVar17;
  undefined8 uVar18;
  undefined8 uVar19;
  undefined8 uVar20;
  undefined8 uVar21;
  
  if (*(int *)(this + 0x40) == 0) {
    return 0;
  }
  puVar7 = *(undefined8 **)(this + 0x78);
  puVar14 = *(undefined8 **)(this + 0x80);
  puVar8 = puVar7;
  if ((puVar7 != puVar14) &&
     (uVar4 = *(uint *)(this + 0x44), puVar8 = puVar14,
     (ulong)uVar4 < ((long)puVar14 - (long)puVar7 >> 3) * -0x5555555555555555 - 1U)) {
    puVar1 = puVar7 + (ulong)uVar4 * 3 + 3;
    if (puVar1 != puVar14) {
      puVar14 = puVar14 + -3;
      pvVar5 = (void *)*puVar14;
      while( true ) {
        if (pvVar5 != (void *)0x0) {
          puVar14[1] = pvVar5;
          operator_delete(pvVar5);
        }
        puVar14 = puVar14 + -3;
        if (puVar7 + (ulong)uVar4 * 3 == puVar14) break;
        pvVar5 = (void *)*puVar14;
      }
      *(undefined8 **)(this + 0x80) = puVar1;
      puVar8 = puVar1;
    }
  }
  if (puVar8 == *(undefined8 **)(this + 0x88)) {
    FUN_00234298(this + 0x78,this + 0x60);
    puVar8 = *(undefined8 **)(this + 0x80);
    goto LAB_00232a8c;
  }
  *puVar8 = 0;
  puVar8[1] = 0;
  puVar8[2] = 0;
  uVar9 = *(long *)(this + 0x68) - *(long *)(this + 0x60);
  if (uVar9 != 0) {
    if ((ulong)((long)uVar9 >> 3) >> 0x3d != 0) {
                    /* WARNING: Subroutine does not return */
      abort();
    }
    puVar6 = operator_new(uVar9);
    *puVar8 = puVar6;
    puVar8[1] = puVar6;
    puVar8[2] = puVar6 + ((long)uVar9 >> 3) * 2;
    puVar11 = *(undefined4 **)(this + 0x60);
    puVar3 = *(undefined4 **)(this + 0x68);
    if (puVar11 != puVar3) {
      uVar12 = ((long)puVar3 - (long)puVar11) - 8;
      uVar9 = (uVar12 >> 3) + 1;
      if ((7 < uVar9) &&
         (((undefined4 *)((long)puVar11 + (uVar12 & 0xfffffffffffffff8) + 8) <= puVar6 ||
          ((undefined4 *)((long)puVar6 + ((long)puVar3 - (long)puVar11 & 0xfffffffffffffff8U)) <=
           puVar11)))) {
        uVar13 = uVar9 & 0x3ffffffffffffff8;
        puVar14 = (undefined8 *)(puVar6 + 8);
        puVar6 = puVar6 + uVar13 * 2;
        puVar7 = (undefined8 *)(puVar11 + 8);
        uVar12 = uVar13;
        do {
          uVar15 = puVar7[-4];
          uVar17 = puVar7[-1];
          uVar16 = puVar7[-2];
          uVar19 = puVar7[1];
          uVar18 = *puVar7;
          uVar21 = puVar7[3];
          uVar20 = puVar7[2];
          uVar12 = uVar12 - 8;
          puVar14[-3] = puVar7[-3];
          puVar14[-4] = uVar15;
          puVar14[-1] = uVar17;
          puVar14[-2] = uVar16;
          puVar14[1] = uVar19;
          *puVar14 = uVar18;
          puVar14[3] = uVar21;
          puVar14[2] = uVar20;
          puVar7 = puVar7 + 8;
          puVar14 = puVar14 + 8;
        } while (uVar12 != 0);
        puVar11 = puVar11 + uVar13 * 2;
        if (uVar9 == uVar13) goto LAB_00232a80;
      }
      do {
        *puVar6 = *puVar11;
        puVar2 = puVar11 + 2;
        puVar6[1] = puVar11[1];
        puVar6 = puVar6 + 2;
        puVar11 = puVar2;
      } while (puVar2 != puVar3);
    }
LAB_00232a80:
    puVar8[1] = puVar6;
  }
  puVar8 = puVar8 + 3;
  *(undefined8 **)(this + 0x80) = puVar8;
LAB_00232a8c:
  puVar14 = *(undefined8 **)(this + 0x78);
  uVar9 = (ulong)*(uint *)(this + 0x40);
  uVar12 = ((long)puVar8 - (long)puVar14 >> 3) * -0x5555555555555555;
  if (uVar9 <= uVar12 && uVar12 - uVar9 != 0) {
    lVar10 = (long)(puVar8 + uVar9 * -3) - (long)puVar14;
    if (lVar10 != 0) {
      if (puVar8 + uVar9 * -3 != puVar8) {
        lVar10 = lVar10 >> 3;
        pvVar5 = (void *)*puVar14;
        while( true ) {
          if (pvVar5 != (void *)0x0) {
            puVar14[1] = pvVar5;
            operator_delete(pvVar5);
            *puVar14 = 0;
            puVar14[1] = 0;
            puVar14[2] = 0;
          }
          puVar7 = puVar14 + lVar10;
          uVar15 = *puVar7;
          puVar14[1] = puVar7[1];
          *puVar14 = uVar15;
          puVar14[2] = puVar7[2];
          puVar14 = puVar14 + 3;
          *puVar7 = 0;
          puVar7[1] = 0;
          puVar7[2] = 0;
          if (puVar8 + -lVar10 == puVar14) break;
          pvVar5 = (void *)*puVar14;
        }
        puVar8 = *(undefined8 **)(this + 0x80);
      }
      if (puVar8 != puVar14) {
        pvVar5 = (void *)puVar8[-3];
        puVar7 = puVar8 + -3;
        while( true ) {
          puVar1 = puVar7;
          if (pvVar5 != (void *)0x0) {
            puVar8[-2] = pvVar5;
            operator_delete(pvVar5);
          }
          if (puVar14 == puVar1) break;
          pvVar5 = (void *)puVar1[-3];
          puVar7 = puVar1 + -3;
          puVar8 = puVar1;
        }
      }
      *(undefined8 **)(this + 0x80) = puVar14;
      puVar14 = *(undefined8 **)(this + 0x78);
    }
  }
  *(int *)(this + 0x44) =
       (int)((ulong)(*(long *)(this + 0x80) - (long)puVar14) >> 3) * -0x55555555 + -1;
  return 1;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::render2Texture
 * Address : 00232bb8
 * Size    : 0x194 bytes
 * ------------------------------------------------------------------ */

/* CGE::CGELiquifyFilter::render2Texture(CGE::CGEImageHandlerInterface*, unsigned int, unsigned int)
    */

void CGE::CGELiquifyFilter::render2Texture
               (CGEImageHandlerInterface *param_1,uint param_2,uint param_3)

{
  CGEImageHandlerInterface *pCVar1;
  undefined1 auVar2 [16];
  
  pCVar1 = (CGEImageHandlerInterface *)(ulong)param_2;
  if ((*(int *)(param_1 + 0x48) != 0) && (*(long *)(param_1 + 0x60) != *(long *)(param_1 + 0x68))) {
    glBindFramebuffer(0x8d40,*(undefined4 *)(pCVar1 + 0x1c));
    glViewport(0,0,*(undefined4 *)(pCVar1 + 0xc),*(undefined4 *)(pCVar1 + 0x10));
    glUseProgram(*(undefined4 *)(param_1 + 0x30));
    glActiveTexture(0x84c0);
    glBindTexture(0xde1,param_3);
    if (*(UniformParameters **)(param_1 + 0x38) != (UniformParameters *)0x0) {
      UniformParameters::assignUniforms
                (*(UniformParameters **)(param_1 + 0x38),pCVar1,*(uint *)(param_1 + 0x30));
    }
    glBindBuffer(0x8892,*(undefined4 *)(param_1 + 0x48));
    glEnableVertexAttribArray(0);
    glVertexAttribPointer(0,2,0x1406,0,0,0);
    glBindBuffer(0x8892,*(undefined4 *)(param_1 + 0x50));
    glEnableVertexAttribArray(1);
    glVertexAttribPointer(1,2,0x1406,0,0,0);
    glBindBuffer(0x8893,*(undefined4 *)(param_1 + 0x4c));
    glClearColor(ZEXT816(0),0,0,0);
    glClear(0x4000);
    glDrawElements(4,*(int *)(param_1 + 0x90) * 3,0x1403,0);
    if (param_1[200] != (CGEImageHandlerInterface)0x0) {
      glUseProgram(*(undefined4 *)(param_1 + 0xc0));
      glDrawElements(3,*(int *)(param_1 + 0x90) * 3,0x1403,0);
    }
    glBindBuffer(0x8892,0);
    glBindBuffer(0x8893,0);
    return;
  }
  _cgeLogInfoReport(2,"DeformProcessor::render2Texture - Invalid Mesh!\n");
  auVar2 = NEON_rev64(*(undefined1 (*) [16])(pCVar1 + 0x14),4);
  *(long *)(pCVar1 + 0x1c) = auVar2._8_8_;
  *(long *)(pCVar1 + 0x14) = auVar2._0_8_;
  return;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::forwardDeformMesh
 * Address : 00232d4c
 * Size    : 0x1b8 bytes
 * ------------------------------------------------------------------ */

/* CGE::CGELiquifyFilter::forwardDeformMesh(CGE::Vec<float, 2> const&, CGE::Vec<float, 2> const&,
   float, float, float, float) */

void __thiscall
CGE::CGELiquifyFilter::forwardDeformMesh
          (CGELiquifyFilter *this,Vec *param_1,Vec *param_2,float param_3,float param_4,
          float param_5,float param_6)

{
  long lVar1;
  int iVar2;
  int iVar3;
  long lVar4;
  float *pfVar5;
  long lVar6;
  float fVar7;
  float fVar8;
  float fVar9;
  float fVar10;
  float fVar11;
  float fVar12;
  float fVar13;
  float fVar14;
  float fVar15;
  float fVar16;
  
  this[0x94] = (CGELiquifyFilter)0x0;
  fVar13 = *(float *)param_1;
  fVar11 = *(float *)(param_1 + 4);
  fVar14 = *(float *)param_2;
  fVar12 = *(float *)(param_2 + 4);
  fVar9 = -param_5;
  iVar2 = *(int *)(this + 0x58);
  fVar7 = fVar11;
  if (fVar12 <= fVar11) {
    fVar7 = fVar12;
  }
  fVar15 = fVar11;
  if (fVar11 <= fVar12) {
    fVar15 = fVar12;
  }
  fVar8 = fVar7 - param_5;
  if (fVar7 - param_5 <= fVar9) {
    fVar8 = fVar9;
  }
  fVar7 = fVar15 + param_5;
  if (param_4 + param_5 <= fVar15 + param_5) {
    fVar7 = param_4 + param_5;
  }
  fVar15 = fVar13;
  if (fVar14 <= fVar13) {
    fVar15 = fVar14;
  }
  fVar16 = fVar13;
  if (fVar13 <= fVar14) {
    fVar16 = fVar14;
  }
  fVar10 = fVar15 - param_5;
  if (fVar15 - param_5 <= fVar9) {
    fVar10 = fVar9;
  }
  fVar9 = fVar16 + param_5;
  if (param_3 + param_5 <= fVar16 + param_5) {
    fVar9 = param_3 + param_5;
  }
  if (2 < iVar2) {
    iVar3 = *(int *)(this + 0x54);
    lVar4 = 1;
    lVar1 = (long)iVar3 + 1;
    do {
      if (1 < (int)(iVar3 - 1U)) {
        pfVar5 = (float *)(*(long *)(this + 0x60) + lVar1 * 8 + 4);
        lVar6 = (ulong)(iVar3 - 1U) - 1;
        do {
          fVar15 = *pfVar5 * param_4;
          if (fVar15 <= fVar7) {
            fVar16 = pfVar5[-1] * param_3;
            if ((((fVar10 <= fVar16) && (fVar16 <= fVar9)) && (fVar8 <= fVar15)) &&
               (fVar15 = SQRT((fVar16 - *(float *)param_1) * (fVar16 - *(float *)param_1) +
                              (fVar15 - *(float *)(param_1 + 4)) *
                              (fVar15 - *(float *)(param_1 + 4))), fVar15 <= param_5)) {
              fVar15 = 1.0 - fVar15 / param_5;
              fVar15 = fVar15 * fVar15 * (3.0 - (fVar15 + fVar15)) * param_6;
              pfVar5[-1] = pfVar5[-1] + ((fVar14 - fVar13) / param_3) * fVar15;
              *pfVar5 = *pfVar5 + ((fVar12 - fVar11) / param_4) * fVar15;
            }
          }
          lVar6 = lVar6 + -1;
          pfVar5 = pfVar5 + 2;
        } while (lVar6 != 0);
      }
      lVar4 = lVar4 + 1;
      lVar1 = lVar1 + iVar3;
    } while (lVar4 < (long)iVar2 + -1);
  }
  if (*(int *)(this + 0x48) == 0) {
    return;
  }
  glBindBuffer(0x8892);
  glBufferData(0x8892,*(long *)(this + 0x68) - *(long *)(this + 0x60),*(long *)(this + 0x60),0x88e0)
  ;
  glBindBuffer(0x8892,0);
  return;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::pushLeftDeformMesh
 * Address : 00232f04
 * Size    : 0x23c bytes
 * ------------------------------------------------------------------ */

/* CGE::CGELiquifyFilter::pushLeftDeformMesh(CGE::Vec<float, 2> const&, CGE::Vec<float, 2> const&,
   float, float, float, float, float) */

void __thiscall
CGE::CGELiquifyFilter::pushLeftDeformMesh
          (CGELiquifyFilter *this,Vec *param_1,Vec *param_2,float param_3,float param_4,
          float param_5,float param_6,float param_7)

{
  uint uVar1;
  int iVar2;
  long lVar3;
  long lVar4;
  float *pfVar5;
  ulong uVar6;
  float fVar7;
  float fVar8;
  float fVar9;
  float fVar10;
  float fVar11;
  float fVar12;
  float fVar13;
  float fVar14;
  float fVar15;
  float fVar16;
  float local_68;
  float fStack_64;
  
  this[0x94] = (CGELiquifyFilter)0x0;
  fVar13 = *(float *)param_1;
  fVar11 = *(float *)(param_1 + 4);
  fVar14 = *(float *)param_2;
  fVar12 = *(float *)(param_2 + 4);
  fVar7 = -param_5;
  fVar9 = fVar11;
  if (fVar12 <= fVar11) {
    fVar9 = fVar12;
  }
  fVar8 = fVar11;
  if (fVar11 <= fVar12) {
    fVar8 = fVar12;
  }
  fVar15 = fVar9 - param_5;
  if (fVar9 - param_5 <= fVar7) {
    fVar15 = fVar7;
  }
  fVar9 = fVar8 + param_5;
  if (param_4 + param_5 <= fVar8 + param_5) {
    fVar9 = param_4 + param_5;
  }
  fVar8 = fVar13;
  if (fVar14 <= fVar13) {
    fVar8 = fVar14;
  }
  fVar10 = fVar13;
  if (fVar13 <= fVar14) {
    fVar10 = fVar14;
  }
  fVar16 = fVar8 - param_5;
  if (fVar8 - param_5 <= fVar7) {
    fVar16 = fVar7;
  }
  fVar7 = fVar10 + param_5;
  if (param_3 + param_5 <= fVar10 + param_5) {
    fVar7 = param_3 + param_5;
  }
  sincosf(param_7,&fStack_64,&local_68);
  iVar2 = *(int *)(this + 0x58);
  if (0 < iVar2) {
    uVar1 = *(uint *)(this + 0x54);
    lVar3 = 0;
    fVar13 = (fVar14 - fVar13) / param_3;
    fVar11 = (fVar12 - fVar11) / param_4;
    lVar4 = 0;
    do {
      if (0 < (int)uVar1) {
        pfVar5 = (float *)(*(long *)(this + 0x60) + lVar3 * 8 + 4);
        uVar6 = (ulong)uVar1;
        do {
          fVar12 = *pfVar5 * param_4;
          if (fVar12 <= fVar9) {
            fVar14 = pfVar5[-1] * param_3;
            if ((((fVar16 <= fVar14) && (fVar14 <= fVar7)) && (fVar15 <= fVar12)) &&
               (fVar12 = SQRT((fVar14 - *(float *)param_1) * (fVar14 - *(float *)param_1) +
                              (fVar12 - *(float *)(param_1 + 4)) *
                              (fVar12 - *(float *)(param_1 + 4))), fVar12 <= param_5)) {
              fVar12 = 1.0 - fVar12 / param_5;
              fVar12 = fVar12 * fVar12 * (3.0 - (fVar12 + fVar12)) * param_6;
              pfVar5[-1] = pfVar5[-1] + (fStack_64 * fVar11 + local_68 * fVar13) * fVar12;
              *pfVar5 = *pfVar5 + (local_68 * fVar11 - fStack_64 * fVar13) * fVar12;
            }
          }
          uVar6 = uVar6 - 1;
          pfVar5 = pfVar5 + 2;
        } while (uVar6 != 0);
      }
      lVar4 = lVar4 + 1;
      lVar3 = lVar3 + (int)uVar1;
    } while (lVar4 < iVar2);
  }
  if (*(int *)(this + 0x48) == 0) {
    return;
  }
  glBindBuffer(0x8892);
  glBufferData(0x8892,*(long *)(this + 0x68) - *(long *)(this + 0x60),*(long *)(this + 0x60),0x88e0)
  ;
  glBindBuffer(0x8892,0);
  return;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::restoreMeshWithPoint
 * Address : 00233140
 * Size    : 0x164 bytes
 * ------------------------------------------------------------------ */

/* CGE::CGELiquifyFilter::restoreMeshWithPoint(CGE::Vec<float, 2> const&, float, float, float,
   float) */

void __thiscall
CGE::CGELiquifyFilter::restoreMeshWithPoint
          (CGELiquifyFilter *this,Vec *param_1,float param_2,float param_3,float param_4,
          float param_5)

{
  long lVar1;
  int iVar2;
  int iVar3;
  uint uVar4;
  ulong uVar5;
  float *pfVar6;
  ulong uVar7;
  float fVar8;
  float fVar9;
  
  iVar2 = *(int *)(this + 0x58);
  this[0x94] = (CGELiquifyFilter)0x0;
  uVar4 = iVar2 - 1;
  if (1 < (int)uVar4) {
    iVar3 = *(int *)(this + 0x54);
    uVar5 = 1;
    lVar1 = (long)iVar3 + 1;
    do {
      if (1 < (int)(iVar3 - 1U)) {
        uVar7 = 1;
        pfVar6 = (float *)(*(long *)(this + 0x60) + lVar1 * 8 + 4);
        do {
          fVar8 = pfVar6[-1] * param_2 - *(float *)param_1;
          fVar9 = *pfVar6 * param_3 - *(float *)(param_1 + 4);
          fVar8 = SQRT(fVar8 * fVar8 + fVar9 * fVar9);
          if (fVar8 <= param_4) {
            fVar8 = 1.0 - fVar8 / param_4;
            fVar8 = fVar8 * fVar8 * (3.0 - (fVar8 + fVar8)) * param_5;
            pfVar6[-1] = (1.0 / ((float)iVar3 + -1.0)) * (float)(int)uVar7 * fVar8 +
                         pfVar6[-1] * (1.0 - fVar8);
            *pfVar6 = (1.0 / ((float)iVar2 + -1.0)) * (float)(int)uVar5 * fVar8 +
                      *pfVar6 * (1.0 - fVar8);
          }
          uVar7 = uVar7 + 1;
          pfVar6 = pfVar6 + 2;
        } while (iVar3 - 1U != uVar7);
      }
      uVar5 = uVar5 + 1;
      lVar1 = lVar1 + iVar3;
    } while (uVar5 != uVar4);
  }
  if (*(int *)(this + 0x48) == 0) {
    return;
  }
  glBindBuffer(0x8892);
  glBufferData(0x8892,*(long *)(this + 0x68) - *(long *)(this + 0x60),*(long *)(this + 0x60),0x88e0)
  ;
  glBindBuffer(0x8892,0);
  return;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::bloatMeshWithPoint
 * Address : 002332a4
 * Size    : 0x118 bytes
 * ------------------------------------------------------------------ */

/* CGE::CGELiquifyFilter::bloatMeshWithPoint(CGE::Vec<float, 2> const&, float, float, float, float)
    */

void __thiscall
CGE::CGELiquifyFilter::bloatMeshWithPoint
          (CGELiquifyFilter *this,Vec *param_1,float param_2,float param_3,float param_4,
          float param_5)

{
  long lVar1;
  int iVar2;
  int iVar3;
  long lVar4;
  undefined8 *puVar5;
  long lVar6;
  float fVar7;
  float fVar8;
  float fVar9;
  float fVar10;
  float fVar11;
  
  iVar2 = *(int *)(this + 0x58);
  this[0x94] = (CGELiquifyFilter)0x0;
  if (2 < iVar2) {
    iVar3 = *(int *)(this + 0x54);
    lVar4 = 1;
    lVar1 = (long)iVar3 + 1;
    do {
      if (1 < (int)(iVar3 - 1U)) {
        puVar5 = (undefined8 *)(*(long *)(this + 0x60) + lVar1 * 8);
        lVar6 = (ulong)(iVar3 - 1U) - 1;
        do {
          fVar7 = (float)*puVar5;
          fVar8 = (float)((ulong)*puVar5 >> 0x20);
          fVar9 = fVar7 * param_2 - (float)*(undefined8 *)param_1;
          fVar10 = fVar8 * param_3 - (float)((ulong)*(undefined8 *)param_1 >> 0x20);
          fVar11 = SQRT(fVar9 * fVar9 + fVar10 * fVar10);
          if (fVar11 <= param_4) {
            fVar11 = 1.0 - fVar11 / param_4;
            fVar11 = fVar11 * fVar11 * (3.0 - (fVar11 + fVar11)) * param_5;
            *puVar5 = CONCAT44(fVar8 + (fVar10 / param_3) * fVar11,
                               fVar7 + (fVar9 / param_2) * fVar11);
          }
          lVar6 = lVar6 + -1;
          puVar5 = puVar5 + 1;
        } while (lVar6 != 0);
      }
      lVar4 = lVar4 + 1;
      lVar1 = lVar1 + iVar3;
    } while (lVar4 < (long)iVar2 + -1);
  }
  if (*(int *)(this + 0x48) == 0) {
    return;
  }
  glBindBuffer(0x8892);
  glBufferData(0x8892,*(long *)(this + 0x68) - *(long *)(this + 0x60),*(long *)(this + 0x60),0x88e0)
  ;
  glBindBuffer(0x8892,0);
  return;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::wrinkleMeshWithPoint
 * Address : 002333bc
 * Size    : 0x118 bytes
 * ------------------------------------------------------------------ */

/* CGE::CGELiquifyFilter::wrinkleMeshWithPoint(CGE::Vec<float, 2> const&, float, float, float,
   float) */

void __thiscall
CGE::CGELiquifyFilter::wrinkleMeshWithPoint
          (CGELiquifyFilter *this,Vec *param_1,float param_2,float param_3,float param_4,
          float param_5)

{
  long lVar1;
  int iVar2;
  int iVar3;
  long lVar4;
  undefined8 *puVar5;
  long lVar6;
  float fVar7;
  float fVar8;
  float fVar9;
  float fVar10;
  float fVar11;
  
  iVar2 = *(int *)(this + 0x58);
  this[0x94] = (CGELiquifyFilter)0x0;
  if (2 < iVar2) {
    iVar3 = *(int *)(this + 0x54);
    lVar4 = 1;
    lVar1 = (long)iVar3 + 1;
    do {
      if (1 < (int)(iVar3 - 1U)) {
        puVar5 = (undefined8 *)(*(long *)(this + 0x60) + lVar1 * 8);
        lVar6 = (ulong)(iVar3 - 1U) - 1;
        do {
          fVar7 = (float)*puVar5;
          fVar8 = (float)((ulong)*puVar5 >> 0x20);
          fVar9 = (float)*(undefined8 *)param_1 - fVar7 * param_2;
          fVar10 = (float)((ulong)*(undefined8 *)param_1 >> 0x20) - fVar8 * param_3;
          fVar11 = SQRT(fVar9 * fVar9 + fVar10 * fVar10);
          if (fVar11 <= param_4) {
            fVar11 = 1.0 - fVar11 / param_4;
            fVar11 = fVar11 * fVar11 * (3.0 - (fVar11 + fVar11)) * param_5;
            *puVar5 = CONCAT44(fVar8 + (fVar10 / param_3) * fVar11,
                               fVar7 + (fVar9 / param_2) * fVar11);
          }
          lVar6 = lVar6 + -1;
          puVar5 = puVar5 + 1;
        } while (lVar6 != 0);
      }
      lVar4 = lVar4 + 1;
      lVar1 = lVar1 + iVar3;
    } while (lVar4 < (long)iVar2 + -1);
  }
  if (*(int *)(this + 0x48) == 0) {
    return;
  }
  glBindBuffer(0x8892);
  glBufferData(0x8892,*(long *)(this + 0x68) - *(long *)(this + 0x60),*(long *)(this + 0x60),0x88e0)
  ;
  glBindBuffer(0x8892,0);
  return;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::canUndo
 * Address : 002334d4
 * Size    : 0x24 bytes
 * ------------------------------------------------------------------ */

/* CGE::CGELiquifyFilter::canUndo() */

bool __thiscall CGE::CGELiquifyFilter::canUndo(CGELiquifyFilter *this)

{
  if (*(long *)(this + 0x78) != *(long *)(this + 0x80)) {
    return *(int *)(this + 0x44) != 0;
  }
  return false;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::canRedo
 * Address : 002334f8
 * Size    : 0x3c bytes
 * ------------------------------------------------------------------ */

/* CGE::CGELiquifyFilter::canRedo() */

bool __thiscall CGE::CGELiquifyFilter::canRedo(CGELiquifyFilter *this)

{
  if (*(long *)(this + 0x78) != *(long *)(this + 0x80)) {
    return (ulong)*(uint *)(this + 0x44) <
           (*(long *)(this + 0x80) - *(long *)(this + 0x78) >> 3) * -0x5555555555555555 - 1U;
  }
  return false;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::undo
 * Address : 00233534
 * Size    : 0xa0 bytes
 * ------------------------------------------------------------------ */

/* CGE::CGELiquifyFilter::undo() */

undefined8 __thiscall CGE::CGELiquifyFilter::undo(CGELiquifyFilter *this)

{
  long lVar1;
  uint uVar2;
  undefined8 uVar3;
  vector<CGE::Vec<float,2>,std::__ndk1::allocator<CGE::Vec<float,2>>> *pvVar4;
  
  lVar1 = *(long *)(this + 0x78);
  if ((lVar1 == *(long *)(this + 0x80)) || (*(int *)(this + 0x44) == 0)) {
    uVar3 = 0;
  }
  else {
    uVar2 = *(int *)(this + 0x44) - 1;
    pvVar4 = (vector<CGE::Vec<float,2>,std::__ndk1::allocator<CGE::Vec<float,2>>> *)
             (lVar1 + (ulong)uVar2 * 0x18);
    *(uint *)(this + 0x44) = uVar2;
    if ((vector<CGE::Vec<float,2>,std::__ndk1::allocator<CGE::Vec<float,2>>> *)(this + 0x60) !=
        pvVar4) {
      std::__ndk1::vector<CGE::Vec<float,2>,std::__ndk1::allocator<CGE::Vec<float,2>>>::
      assign<CGE::Vec<float,2>*>
                ((vector<CGE::Vec<float,2>,std::__ndk1::allocator<CGE::Vec<float,2>>> *)
                 (this + 0x60),*(Vec **)pvVar4,*(Vec **)(lVar1 + (ulong)uVar2 * 0x18 + 8));
    }
    if (*(int *)(this + 0x48) != 0) {
      glBindBuffer(0x8892);
      glBufferData(0x8892,*(long *)(this + 0x68) - *(long *)(this + 0x60),*(long *)(this + 0x60),
                   0x88e0);
      glBindBuffer(0x8892,0);
    }
    uVar3 = 1;
    this[0x94] = (CGELiquifyFilter)0x0;
  }
  return uVar3;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::redo
 * Address : 002335d4
 * Size    : 0xb8 bytes
 * ------------------------------------------------------------------ */

/* CGE::CGELiquifyFilter::redo() */

undefined8 __thiscall CGE::CGELiquifyFilter::redo(CGELiquifyFilter *this)

{
  uint uVar1;
  long lVar2;
  undefined8 uVar3;
  vector<CGE::Vec<float,2>,std::__ndk1::allocator<CGE::Vec<float,2>>> *pvVar4;
  
  lVar2 = *(long *)(this + 0x78);
  if ((lVar2 == *(long *)(this + 0x80)) ||
     ((*(long *)(this + 0x80) - lVar2 >> 3) * -0x5555555555555555 - 1U <=
      (ulong)*(uint *)(this + 0x44))) {
    uVar3 = 0;
  }
  else {
    uVar1 = *(uint *)(this + 0x44) + 1;
    pvVar4 = (vector<CGE::Vec<float,2>,std::__ndk1::allocator<CGE::Vec<float,2>>> *)
             (lVar2 + (ulong)uVar1 * 0x18);
    *(uint *)(this + 0x44) = uVar1;
    if ((vector<CGE::Vec<float,2>,std::__ndk1::allocator<CGE::Vec<float,2>>> *)(this + 0x60) !=
        pvVar4) {
      std::__ndk1::vector<CGE::Vec<float,2>,std::__ndk1::allocator<CGE::Vec<float,2>>>::
      assign<CGE::Vec<float,2>*>
                ((vector<CGE::Vec<float,2>,std::__ndk1::allocator<CGE::Vec<float,2>>> *)
                 (this + 0x60),*(Vec **)pvVar4,*(Vec **)(lVar2 + (ulong)uVar1 * 0x18 + 8));
    }
    if (*(int *)(this + 0x48) != 0) {
      glBindBuffer(0x8892);
      glBufferData(0x8892,*(long *)(this + 0x68) - *(long *)(this + 0x60),*(long *)(this + 0x60),
                   0x88e0);
      glBindBuffer(0x8892,0);
    }
    uVar3 = 1;
  }
  return uVar3;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyNicerFilter::forwardDeformMesh
 * Address : 0023368c
 * Size    : 0x334 bytes
 * ------------------------------------------------------------------ */

/* CGE::CGELiquifyNicerFilter::forwardDeformMesh(CGE::Vec<float, 2> const&, CGE::Vec<float, 2>
   const&, float, float, float, float) */

void __thiscall
CGE::CGELiquifyNicerFilter::forwardDeformMesh
          (CGELiquifyNicerFilter *this,Vec *param_1,Vec *param_2,float param_3,float param_4,
          float param_5,float param_6)

{
  float fVar1;
  uint uVar2;
  int iVar3;
  float fVar4;
  float fVar5;
  long lVar6;
  long lVar7;
  float *pfVar8;
  ulong uVar9;
  float fVar10;
  float fVar11;
  float fVar12;
  float fVar13;
  float fVar14;
  float fVar15;
  float fVar16;
  float fVar17;
  float fVar18;
  float fVar19;
  float fVar20;
  float fVar21;
  float fVar22;
  float fVar23;
  float fVar24;
  float fVar25;
  float fVar26;
  
  this[0x94] = (CGELiquifyNicerFilter)0x0;
  fVar22 = *(float *)param_1;
  fVar23 = *(float *)(param_1 + 4);
  fVar24 = *(float *)param_2;
  fVar25 = *(float *)(param_2 + 4);
  fVar10 = -param_5;
  fVar13 = fVar23;
  if (fVar25 <= fVar23) {
    fVar13 = fVar25;
  }
  fVar11 = fVar23;
  if (fVar23 <= fVar25) {
    fVar11 = fVar25;
  }
  fVar1 = fVar25;
  fVar4 = fVar23;
  if (fVar23 <= fVar25) {
    fVar1 = fVar23;
    fVar4 = fVar25;
  }
  fVar14 = fVar13 - param_5;
  if (fVar13 - param_5 <= fVar10) {
    fVar14 = fVar10;
  }
  fVar13 = fVar11 + param_5;
  if (param_4 + param_5 <= fVar11 + param_5) {
    fVar13 = param_4 + param_5;
  }
  fVar11 = fVar22;
  if (fVar24 <= fVar22) {
    fVar11 = fVar24;
  }
  fVar19 = fVar22;
  fVar5 = fVar24;
  if (fVar22 <= fVar24) {
    fVar19 = fVar24;
    fVar5 = fVar22;
  }
  fVar15 = fVar11 - param_5;
  if (fVar11 - param_5 <= fVar10) {
    fVar15 = fVar10;
  }
  fVar10 = fVar19 + param_5;
  if (param_3 + param_5 <= fVar19 + param_5) {
    fVar10 = param_3 + param_5;
  }
  fVar11 = fVar22 - fVar24;
  if ((0.001 <= fVar11) || (fVar11 <= -0.001)) {
    fVar20 = (fVar23 - fVar25) / fVar11;
    fVar11 = (fVar25 * fVar22 - fVar23 * fVar24) / fVar11;
    fVar21 = -1.0;
  }
  else {
    fVar11 = -fVar22;
    fVar21 = 0.0;
    fVar20 = 1.0;
  }
  iVar3 = *(int *)(this + 0x58);
  if (0 < iVar3) {
    uVar2 = *(uint *)(this + 0x54);
    fVar26 = fVar21 * fVar21 + fVar20 * fVar20;
    lVar6 = 0;
    lVar7 = 0;
    do {
      if (0 < (int)uVar2) {
        pfVar8 = (float *)(*(long *)(this + 0x60) + lVar6 * 8 + 4);
        uVar9 = (ulong)uVar2;
        do {
          fVar16 = *pfVar8 * param_4;
          if (fVar16 <= fVar13) {
            fVar17 = pfVar8[-1] * param_3;
            if ((((fVar15 <= fVar17) && (fVar17 <= fVar10)) && (fVar14 <= fVar16)) &&
               (fVar18 = ABS(fVar11 + fVar20 * fVar17 + fVar21 * fVar16) / SQRT(fVar26),
               fVar18 <= param_5)) {
              fVar12 = ((fVar20 * fVar20 * fVar16 - fVar11 * fVar21) - fVar21 * fVar20 * fVar17) /
                       fVar26;
              if (((fVar4 < fVar12) || (fVar12 < fVar1)) ||
                 ((fVar12 = ((fVar21 * fVar21 * fVar17 - fVar21 * fVar20 * fVar16) - fVar11 * fVar20
                            ) / fVar26, fVar12 < fVar5 || (fVar19 < fVar12)))) {
                fVar18 = SQRT((fVar17 - *(float *)param_1) * (fVar17 - *(float *)param_1) +
                              (fVar16 - *(float *)(param_1 + 4)) *
                              (fVar16 - *(float *)(param_1 + 4)));
                fVar16 = SQRT((fVar17 - *(float *)param_2) * (fVar17 - *(float *)param_2) +
                              (fVar16 - *(float *)(param_2 + 4)) *
                              (fVar16 - *(float *)(param_2 + 4)));
                if ((param_5 < fVar18) && (param_5 < fVar16)) goto LAB_0023385c;
                if (fVar16 <= fVar18) {
                  fVar18 = fVar16;
                }
              }
              fVar16 = 1.0 - fVar18 / param_5;
              fVar16 = fVar16 * fVar16 * (3.0 - (fVar16 + fVar16)) * param_6;
              pfVar8[-1] = pfVar8[-1] + ((fVar24 - fVar22) / param_3) * fVar16;
              *pfVar8 = *pfVar8 + ((fVar25 - fVar23) / param_4) * fVar16;
            }
          }
LAB_0023385c:
          uVar9 = uVar9 - 1;
          pfVar8 = pfVar8 + 2;
        } while (uVar9 != 0);
      }
      lVar7 = lVar7 + 1;
      lVar6 = lVar6 + (int)uVar2;
    } while (lVar7 < iVar3);
  }
  if (*(int *)(this + 0x48) != 0) {
    glBindBuffer(0x8892);
    glBufferData(0x8892,*(long *)(this + 0x68) - *(long *)(this + 0x60),*(long *)(this + 0x60),
                 0x88e0);
    glBindBuffer(0x8892,0);
    return;
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyNicerFilter::pushLeftDeformMesh
 * Address : 002339c0
 * Size    : 0x3b0 bytes
 * ------------------------------------------------------------------ */

/* CGE::CGELiquifyNicerFilter::pushLeftDeformMesh(CGE::Vec<float, 2> const&, CGE::Vec<float, 2>
   const&, float, float, float, float, float) */

void __thiscall
CGE::CGELiquifyNicerFilter::pushLeftDeformMesh
          (CGELiquifyNicerFilter *this,Vec *param_1,Vec *param_2,float param_3,float param_4,
          float param_5,float param_6,float param_7)

{
  float fVar1;
  float fVar2;
  uint uVar3;
  int iVar4;
  float fVar5;
  float fVar6;
  long lVar7;
  long lVar8;
  float *pfVar9;
  ulong uVar10;
  float fVar11;
  float fVar12;
  float fVar13;
  float fVar14;
  float fVar15;
  float fVar16;
  float fVar17;
  float fVar18;
  float fVar19;
  float fVar20;
  float fVar21;
  float fVar22;
  float fVar23;
  float fVar24;
  float local_48;
  float fStack_44;
  
  this[0x94] = (CGELiquifyNicerFilter)0x0;
  fVar18 = *(float *)param_1;
  fVar15 = *(float *)(param_1 + 4);
  fVar20 = *(float *)param_2;
  fVar17 = *(float *)(param_2 + 4);
  fVar11 = -param_5;
  fVar13 = fVar15;
  if (fVar17 <= fVar15) {
    fVar13 = fVar17;
  }
  fVar12 = fVar15;
  if (fVar15 <= fVar17) {
    fVar12 = fVar17;
  }
  fVar1 = fVar17;
  fVar5 = fVar15;
  if (fVar15 <= fVar17) {
    fVar1 = fVar15;
    fVar5 = fVar17;
  }
  fVar21 = fVar13 - param_5;
  if (fVar13 - param_5 <= fVar11) {
    fVar21 = fVar11;
  }
  fVar13 = fVar12 + param_5;
  if (param_4 + param_5 <= fVar12 + param_5) {
    fVar13 = param_4 + param_5;
  }
  fVar12 = fVar18;
  if (fVar20 <= fVar18) {
    fVar12 = fVar20;
  }
  fVar2 = fVar20;
  fVar6 = fVar18;
  if (fVar18 <= fVar20) {
    fVar2 = fVar18;
    fVar6 = fVar20;
  }
  fVar22 = fVar12 - param_5;
  if (fVar12 - param_5 <= fVar11) {
    fVar22 = fVar11;
  }
  fVar11 = fVar6 + param_5;
  if (param_3 + param_5 <= fVar6 + param_5) {
    fVar11 = param_3 + param_5;
  }
  sincosf(param_7,&fStack_44,&local_48);
  fVar12 = fVar18 - fVar20;
  if ((0.001 <= fVar12) || (fVar12 <= -0.001)) {
    fVar16 = (fVar15 - fVar17) / fVar12;
    fVar12 = (fVar17 * fVar18 - fVar15 * fVar20) / fVar12;
    fVar14 = -1.0;
  }
  else {
    fVar12 = -fVar18;
    fVar14 = 0.0;
    fVar16 = 1.0;
  }
  iVar4 = *(int *)(this + 0x58);
  if (0 < iVar4) {
    uVar3 = *(uint *)(this + 0x54);
    fVar18 = (fVar20 - fVar18) / param_3;
    fVar15 = (fVar17 - fVar15) / param_4;
    fVar17 = fVar14 * fVar14 + fVar16 * fVar16;
    lVar7 = 0;
    lVar8 = 0;
    do {
      if (0 < (int)uVar3) {
        pfVar9 = (float *)(*(long *)(this + 0x60) + lVar7 * 8 + 4);
        uVar10 = (ulong)uVar3;
        do {
          fVar20 = *pfVar9 * param_4;
          if (fVar20 <= fVar13) {
            fVar23 = pfVar9[-1] * param_3;
            if ((((fVar22 <= fVar23) && (fVar23 <= fVar11)) && (fVar21 <= fVar20)) &&
               (fVar24 = ABS(fVar12 + fVar16 * fVar23 + fVar14 * fVar20) / SQRT(fVar17),
               fVar24 <= param_5)) {
              fVar19 = ((fVar16 * fVar16 * fVar20 - fVar12 * fVar14) - fVar14 * fVar16 * fVar23) /
                       fVar17;
              if (((fVar5 < fVar19) || (fVar19 < fVar1)) ||
                 ((fVar19 = ((fVar14 * fVar14 * fVar23 - fVar14 * fVar16 * fVar20) - fVar12 * fVar16
                            ) / fVar17, fVar19 < fVar2 || (fVar6 < fVar19)))) {
                fVar24 = SQRT((fVar23 - *(float *)param_1) * (fVar23 - *(float *)param_1) +
                              (fVar20 - *(float *)(param_1 + 4)) *
                              (fVar20 - *(float *)(param_1 + 4)));
                fVar20 = SQRT((fVar23 - *(float *)param_2) * (fVar23 - *(float *)param_2) +
                              (fVar20 - *(float *)(param_2 + 4)) *
                              (fVar20 - *(float *)(param_2 + 4)));
                if ((param_5 < fVar24) && (param_5 < fVar20)) goto LAB_00233bf4;
                if (fVar20 <= fVar24) {
                  fVar24 = fVar20;
                }
              }
              fVar20 = 1.0 - fVar24 / param_5;
              fVar20 = fVar20 * fVar20 * (3.0 - (fVar20 + fVar20)) * param_6;
              pfVar9[-1] = pfVar9[-1] + (fStack_44 * fVar15 + local_48 * fVar18) * fVar20;
              *pfVar9 = *pfVar9 + (local_48 * fVar15 - fStack_44 * fVar18) * fVar20;
            }
          }
LAB_00233bf4:
          uVar10 = uVar10 - 1;
          pfVar9 = pfVar9 + 2;
        } while (uVar10 != 0);
      }
      lVar8 = lVar8 + 1;
      lVar7 = lVar7 + (int)uVar3;
    } while (lVar8 < iVar4);
  }
  if (*(int *)(this + 0x48) != 0) {
    glBindBuffer(0x8892);
    glBufferData(0x8892,*(long *)(this + 0x68) - *(long *)(this + 0x60),*(long *)(this + 0x60),
                 0x88e0);
    glBindBuffer(0x8892,0);
    return;
  }
  return;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::showMesh
 * Address : 00233d70
 * Size    : 0xc bytes
 * ------------------------------------------------------------------ */

/* CGE::CGELiquifyFilter::showMesh(bool) */

void __thiscall CGE::CGELiquifyFilter::showMesh(CGELiquifyFilter *this,bool param_1)

{
  this[200] = (CGELiquifyFilter)param_1;
  return;
}



/* ------------------------------------------------------------------
 * Function: CGE::CGELiquifyFilter::~CGELiquifyFilter
 * Address : 00233d7c
 * Size    : 0xf4 bytes
 * ------------------------------------------------------------------ */

/* CGE::CGELiquifyFilter::~CGELiquifyFilter() */

void __thiscall CGE::CGELiquifyFilter::~CGELiquifyFilter(CGELiquifyFilter *this)

{
  long lVar1;
  void *pvVar2;
  undefined8 *puVar3;
  undefined8 *puVar4;
  undefined8 *puVar5;
  undefined8 local_48;
  undefined4 local_40;
  long local_38;
  
  lVar1 = tpidr_el0;
  local_38 = *(long *)(lVar1 + 0x28);
  local_48 = *(undefined8 *)(this + 0x48);
  *(undefined **)this = PTR_vtable_003869d8 + 0x10;
  local_40 = *(undefined4 *)(this + 0x50);
  glDeleteBuffers(3,&local_48);
  *(undefined8 *)(this + 0x48) = 0;
  *(undefined4 *)(this + 0x50) = 0;
  ProgramObject::~ProgramObject((ProgramObject *)(this + 0x98));
  puVar5 = *(undefined8 **)(this + 0x78);
  if (puVar5 != (undefined8 *)0x0) {
    puVar4 = *(undefined8 **)(this + 0x80);
    puVar3 = puVar5;
    if (puVar4 != puVar5) {
      pvVar2 = (void *)puVar4[-3];
      puVar3 = puVar4 + -3;
      while( true ) {
        if (pvVar2 != (void *)0x0) {
          puVar4[-2] = pvVar2;
          operator_delete(pvVar2);
        }
        if (puVar5 == puVar3) break;
        pvVar2 = (void *)puVar3[-3];
        puVar4 = puVar3;
        puVar3 = puVar3 + -3;
      }
      puVar3 = *(undefined8 **)(this + 0x78);
    }
    *(undefined8 **)(this + 0x80) = puVar5;
    operator_delete(puVar3);
  }
  pvVar2 = *(void **)(this + 0x60);
  if (pvVar2 != (void *)0x0) {
    *(void **)(this + 0x68) = pvVar2;
    operator_delete(pvVar2);
  }
  CGEImageFilterInterface::~CGEImageFilterInterface((CGEImageFilterInterface *)this);
  if (*(long *)(lVar1 + 0x28) == local_38) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



